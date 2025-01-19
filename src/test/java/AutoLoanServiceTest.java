import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import onlinebank.dao.AutoLoanDAO;
import onlinebank.dao.UserDAO;
import onlinebank.models.AutoLoan;
import onlinebank.models.AutoloanPayment;
import onlinebank.services.AutoLoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class AutoLoanServiceTest {

    @Mock
    private AutoLoanDAO autoLoanDAO;

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private AutoLoanService autoLoanService;

    private AutoLoan autoLoan;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        autoLoan = new AutoLoan(1000.0, 500.0, 24, 123456);
    }

    @Test
    void getAllAutoloans_shouldReturnAllAutoloans() {
        List<AutoLoan> autoLoans = Arrays.asList(autoLoan, new AutoLoan(2000.0, 1500.0, 36, 654321));
        when(autoLoanDAO.getAllAutoloans()).thenReturn(autoLoans);

        List<AutoLoan> result = autoLoanService.getAllAutoloans();

        assertEquals(2, result.size());
        verify(autoLoanDAO, times(1)).getAllAutoloans();
    }

    @Test
    void save_shouldSaveAutoLoanSuccessfully() {
        when(userDAO.existsByPassportNumber(autoLoan.getPassportNumber())).thenReturn(true);
        when(userDAO.countLoansAndMortgagesByPassportNumber(autoLoan.getPassportNumber())).thenReturn(2);

        assertDoesNotThrow(() -> autoLoanService.save(autoLoan));
        verify(autoLoanDAO, times(1)).save(autoLoan);
    }

    @Test
    void save_shouldThrowExceptionWhenUserNotFound() {
        when(userDAO.existsByPassportNumber(autoLoan.getPassportNumber())).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> autoLoanService.save(autoLoan));
        assertEquals("No user found with passport number 123456", exception.getMessage());
        verify(autoLoanDAO, never()).save(any(AutoLoan.class));
    }

    @Test
    void save_shouldThrowExceptionWhenUserHasTooManyLoans() {
        when(userDAO.existsByPassportNumber(autoLoan.getPassportNumber())).thenReturn(true);
        when(userDAO.countLoansAndMortgagesByPassportNumber(autoLoan.getPassportNumber())).thenReturn(3);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> autoLoanService.save(autoLoan));
        assertEquals("The user already has too many loans or mortgages.", exception.getMessage());
        verify(autoLoanDAO, never()).save(any(AutoLoan.class));
    }

    @Test
    void update_shouldUpdateAutoLoanSuccessfully() {
        AutoLoan updatedAutoLoan = new AutoLoan(1200.0, 800.0, 12, 123456);

        assertDoesNotThrow(() -> autoLoanService.update(123456, 1000.0, updatedAutoLoan));
        verify(autoLoanDAO, times(1)).update(123456, 1000.0, updatedAutoLoan);
    }

    @Test
    void show_shouldReturnAutoLoan() {
        when(autoLoanDAO.show(123456, 1000.0)).thenReturn(autoLoan);

        AutoLoan result = autoLoanService.show(123456, 1000.0);

        assertNotNull(result);
        assertEquals(123456, result.getPassportNumber());
        verify(autoLoanDAO, times(1)).show(123456, 1000.0);
    }

    @Test
    void delete_shouldDeleteAutoLoanSuccessfully() {
        assertDoesNotThrow(() -> autoLoanService.delete(123456, 1000.0));
        verify(autoLoanDAO, times(1)).delete(123456, 1000.0);
    }

    @Test
    void makePayment_shouldUpdateBalanceSuccessfully() {
        AutoloanPayment payment = new AutoloanPayment(1L, 200.0);
        when(autoLoanDAO.findById(payment.getId())).thenReturn(autoLoan);

        assertDoesNotThrow(() -> autoLoanService.makePayment(payment));
        verify(autoLoanDAO, times(1)).update(eq(autoLoan.getId()), any(AutoLoan.class));
    }

    @Test
    void makePayment_shouldThrowExceptionWhenAutoLoanNotFound() {
        AutoloanPayment payment = new AutoloanPayment(1L, 200.0);
        when(autoLoanDAO.findById(payment.getId())).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> autoLoanService.makePayment(payment));
        assertEquals("This autoloan doesn't exist", exception.getMessage());
        verify(autoLoanDAO, never()).update(anyLong(), any(AutoLoan.class));
    }

    @Test
    void makePayment_shouldThrowExceptionWhenPaymentAmountIsZeroOrNegative() {
        AutoloanPayment payment = new AutoloanPayment(1L, -200.0);
        when(autoLoanDAO.findById(payment.getId())).thenReturn(autoLoan);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> autoLoanService.makePayment(payment));
        assertEquals("Payment amount must be greater than zero", exception.getMessage());
        verify(autoLoanDAO, never()).update(anyLong(), any(AutoLoan.class));
    }

    @Test
    void makePayment_shouldThrowExceptionWhenPaymentExceedsBalance() {
        AutoloanPayment payment = new AutoloanPayment(1L, 600.0);
        when(autoLoanDAO.findById(payment.getId())).thenReturn(autoLoan);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> autoLoanService.makePayment(payment));
        assertEquals("Payment exceeds the remaining loan balance", exception.getMessage());
        verify(autoLoanDAO, never()).update(anyLong(), any(AutoLoan.class));
    }
}