import onlinebank.dao.MortgageDAO;
import onlinebank.models.Mortgage;
import onlinebank.models.MortgageTerm;
import onlinebank.services.MortgageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MortgageServiceTest {

    @Mock
    private MortgageDAO mortgageDAO;

    @InjectMocks
    private MortgageService mortgageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMortgages() {
        // Arrange
        List<Mortgage> expectedMortgages = Arrays.asList(
                new Mortgage(200000.0, 150000.0, MortgageTerm.TENYEARS, 123456),
                new Mortgage(300000.0, 250000.0, MortgageTerm.FIFTEENYEARS, 654321)
        );

        when(mortgageDAO.getAllMortgages()).thenReturn(expectedMortgages);

        // Act
        List<Mortgage> actualMortgages = mortgageService.getAllMortgages();

        // Assert
        assertEquals(expectedMortgages, actualMortgages, "The list of mortgages should match the expected list.");
    }

    @Test
    void testShowMortgage() {
        // Arrange
        int passportNumber = 123456;
        double mortgageSumm = 200000.0;
        Mortgage expectedMortgage = new Mortgage(mortgageSumm, 150000.0, MortgageTerm.TENYEARS, passportNumber);

        when(mortgageDAO.show(passportNumber, mortgageSumm)).thenReturn(expectedMortgage);

        // Act
        Mortgage actualMortgage = mortgageService.show(passportNumber, mortgageSumm);

        // Assert
        assertEquals(expectedMortgage, actualMortgage, "The mortgage should match the expected mortgage.");
    }

    @Test
    void testSaveMortgage() {
        // Arrange
        Mortgage mortgage = new Mortgage(200000.0, 150000.0, MortgageTerm.TENYEARS, 123456);

        // Act
        mortgageService.save(mortgage);

        // Assert
        verify(mortgageDAO, times(1)).save(mortgage);
    }

    @Test
    void testUpdateMortgage() {
        // Arrange
        int passportNumber = 123456;
        double mortgageSumm = 200000.0;
        Mortgage updatedMortgage = new Mortgage(mortgageSumm, 140000.0, MortgageTerm.TWENTYYEARS, passportNumber);

        // Act
        mortgageService.update(passportNumber, mortgageSumm, updatedMortgage);

        // Assert
        verify(mortgageDAO, times(1)).update(passportNumber, mortgageSumm, updatedMortgage);
    }

    @Test
    void testDeleteMortgage() {
        // Arrange
        int passportNumber = 123456;
        double mortgageSumm = 200000.0;

        // Act
        mortgageService.delete(passportNumber, mortgageSumm);

        // Assert
        verify(mortgageDAO, times(1)).delete(passportNumber, mortgageSumm);
    }
}
