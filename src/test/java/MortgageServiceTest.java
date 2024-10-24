package onlinebank.services;

import onlinebank.dao.MortgageDAO;
import onlinebank.models.Mortgage;
import onlinebank.models.MortgageTerm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
    void testIndex() {
        // Arrange
        List<Mortgage> expectedMortgages = Arrays.asList(
                new Mortgage(250000, 250000, MortgageTerm.FIFTEENYEARS, 5895256),
                new Mortgage(280000, 280000, MortgageTerm.FIFTEENYEARS, 8532954),
                new Mortgage(230000, 230000, MortgageTerm.TENYEARS, 7581599),
                new Mortgage(300000, 300000, MortgageTerm.TWENTYYEARS, 8523965)
        );

        when(mortgageDAO.getAllMortgages()).thenReturn(expectedMortgages);

        // Act
        List<Mortgage> actualMortgages = mortgageService.getAllMortgages();

        // Assert
        assertEquals(expectedMortgages, actualMortgages, "The list of mortgages should match the expected list.");
    }
}
