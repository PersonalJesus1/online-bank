
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
import static org.mockito.Mockito.when;

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
    void testIndex() {
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
}
