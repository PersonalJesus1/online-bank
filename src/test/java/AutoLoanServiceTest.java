import onlinebank.dao.AutoLoanDAO;
import onlinebank.models.AutoLoan;
import onlinebank.services.AutoLoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AutoLoanServiceTest {

    @Mock
    private AutoLoanDAO autoLoanDAO;

    @InjectMocks
    private AutoLoanService autoLoanService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAutoloans() {
        // Arrange
        List<AutoLoan> expectedLoans = Arrays.asList(
                new AutoLoan(85000, 85000, 12, 2585944),
                new AutoLoan(120000, 120000, 15, 4185257),
                new AutoLoan(100000, 100000, 13, 8591258),
                new AutoLoan(115000, 115000, 14, 8529524)
        );

        when(autoLoanDAO.getAllAutoloans()).thenReturn(expectedLoans);

        // Act
        List<AutoLoan> actualLoans = autoLoanService.getAllAutoloans();

        // Assert
        assertEquals(expectedLoans, actualLoans, "The list of auto loans should match the expected list.");
    }

    @Test
    void testSaveAutoLoan() {
        // Arrange
        AutoLoan newAutoLoan = new AutoLoan(90000, 90000, 12, 1234567);

        // Act
        autoLoanService.save(newAutoLoan);

        // Assert
        verify(autoLoanDAO, times(1)).save(newAutoLoan);
    }

    @Test
    void testUpdateAutoLoan() {
        // Arrange
        int passportNumber = 1234567;
        double mortgageSumm = 90000;
        AutoLoan updatedAutoLoan = new AutoLoan(95000, 95000, 15, passportNumber);

        // Act
        autoLoanService.update(passportNumber, mortgageSumm, updatedAutoLoan);

        // Assert
        verify(autoLoanDAO, times(1)).update(passportNumber, mortgageSumm, updatedAutoLoan);
    }

    @Test
    void testShowAutoLoan() {
        // Arrange
        int passportNumber = 1234567;
        double mortgageSumm = 90000;
        AutoLoan expectedAutoLoan = new AutoLoan(mortgageSumm, 90000, 12, passportNumber);

        when(autoLoanDAO.show(passportNumber, mortgageSumm)).thenReturn(expectedAutoLoan);

        // Act
        AutoLoan actualAutoLoan = autoLoanService.show(passportNumber, mortgageSumm);

        // Assert
        assertEquals(expectedAutoLoan, actualAutoLoan, "The auto loan returned should match the expected loan.");
    }

    @Test
    void testDeleteAutoLoan() {
        // Arrange
        int passportNumber = 1234567;
        double mortgageSumm = 90000;

        // Act
        autoLoanService.delete(passportNumber, mortgageSumm);

        // Assert
        verify(autoLoanDAO, times(1)).delete(passportNumber, mortgageSumm);
    }
}
