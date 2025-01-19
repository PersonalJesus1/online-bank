import onlinebank.dao.MortgageDAO;
import onlinebank.dao.UserDAO;
import onlinebank.models.Mortgage;
import onlinebank.models.MortgagePayment;
import onlinebank.models.MortgageTerm;
import onlinebank.services.MortgageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MortgageServiceTest {

    @Mock
    private MortgageDAO mortgageDAO;

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private MortgageService mortgageService;

    private Mortgage testMortgage;

    @BeforeEach
    public void setup() {
        testMortgage = new Mortgage(1000, 500, MortgageTerm.FIFTEENYEARS, 123456);
        testMortgage.setId(1L);
    }

    @Test
    public void testGetAllMortgages() {
        when(mortgageDAO.getAllMortgages()).thenReturn(Arrays.asList(testMortgage));

        List<Mortgage> result = mortgageService.getAllMortgages();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(mortgageDAO, times(1)).getAllMortgages();
    }

    @Test
    public void testSaveValidMortgage() {
        when(userDAO.existsByPassportNumber(testMortgage.getPassportNumber())).thenReturn(true);
        when(userDAO.countLoansAndMortgagesByPassportNumber(testMortgage.getPassportNumber())).thenReturn(2);

        assertDoesNotThrow(() -> mortgageService.save(testMortgage));

        verify(mortgageDAO, times(1)).save(testMortgage);
    }

    @Test
    public void testSaveNonExistentUser() {
        when(userDAO.existsByPassportNumber(testMortgage.getPassportNumber())).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> mortgageService.save(testMortgage));

        assertEquals("No user found with passport number 123456", exception.getMessage());
        verify(mortgageDAO, never()).save(any(Mortgage.class));
    }

    @Test
    public void testSaveTooManyLoans() {
        when(userDAO.existsByPassportNumber(testMortgage.getPassportNumber())).thenReturn(true);
        when(userDAO.countLoansAndMortgagesByPassportNumber(testMortgage.getPassportNumber())).thenReturn(3);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> mortgageService.save(testMortgage));

        assertEquals("The user already has too many loans or mortgages.", exception.getMessage());
        verify(mortgageDAO, never()).save(any(Mortgage.class));
    }

    @Test
    public void testUpdate() {
        doNothing().when(mortgageDAO).update(anyInt(), anyDouble(), any(Mortgage.class));

        assertDoesNotThrow(() -> mortgageService.update(123456, 1000, testMortgage));

        verify(mortgageDAO, times(1)).update(123456, 1000, testMortgage);
    }

    @Test
    public void testShow() {
        when(mortgageDAO.show(123456, 1000)).thenReturn(testMortgage);

        Mortgage result = mortgageService.show(123456, 1000);

        assertNotNull(result);
        assertEquals(testMortgage, result);
        verify(mortgageDAO, times(1)).show(123456, 1000);
    }

    @Test
    public void testDelete() {
        doNothing().when(mortgageDAO).delete(anyInt(), anyDouble());

        assertDoesNotThrow(() -> mortgageService.delete(123456, 1000));

        verify(mortgageDAO, times(1)).delete(123456, 1000);
    }

    @Test
    public void testMakePaymentValid() {
        MortgagePayment payment = new MortgagePayment(1L, 200);
        when(mortgageDAO.findById(payment.getId())).thenReturn(testMortgage);

        assertDoesNotThrow(() -> mortgageService.makePayment(payment));

        verify(mortgageDAO, times(1)).update(eq(1L), any(Mortgage.class));
    }

    @Test
    public void testMakePaymentExceedsBalance() {
        MortgagePayment payment = new MortgagePayment(1L, 600);
        when(mortgageDAO.findById(payment.getId())).thenReturn(testMortgage);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> mortgageService.makePayment(payment));

        assertEquals("Payment exceeds the remaining loan balance", exception.getMessage());
        verify(mortgageDAO, never()).update(anyLong(), any(Mortgage.class));
    }

    @Test
    public void testExistsByPassportNumber() {
        when(mortgageDAO.existsByPassportNumber(123456)).thenReturn(true);

        boolean exists = mortgageService.existsByPassportNumber(123456);

        assertTrue(exists);
        verify(mortgageDAO, times(1)).existsByPassportNumber(123456);
    }
}