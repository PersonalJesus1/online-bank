import onlinebank.dao.DebitCardDAO;
import onlinebank.models.DebitCard;
import onlinebank.services.DebitCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DebitCardServiceTest {

    @Mock
    private DebitCardDAO debitCardDAO;

    @InjectMocks
    private DebitCardService debitCardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDebitcards() {
        // Arrange
        List<DebitCard> expectedDebitCards = Arrays.asList(
                new DebitCard("1234567890123456", LocalDate.of(2021, 1, 1), LocalDate.of(2024, 1, 1), 123, 1000.0, 5895256),
                new DebitCard("6543210987654321", LocalDate.of(2020, 5, 15), LocalDate.of(2023, 5, 15), 456, 2000.0, 8532954)
        );

        when(debitCardDAO.getAllDebitcards()).thenReturn(expectedDebitCards);

        // Act
        List<DebitCard> actualDebitCards = debitCardService.getAllDebitcards();

        // Assert
        assertEquals(expectedDebitCards, actualDebitCards, "The list of debit cards should match the expected list.");
    }

    @Test
    void testShowDebitCard() {
        // Arrange
        String cardNumber = "1234567890123456";
        DebitCard expectedDebitCard = new DebitCard(cardNumber, LocalDate.of(2021, 1, 1), LocalDate.of(2024, 1, 1), 123, 1000.0, 5895256);

        when(debitCardDAO.show(cardNumber)).thenReturn(expectedDebitCard);

        // Act
        DebitCard actualDebitCard = debitCardService.show(cardNumber);

        // Assert
        assertEquals(expectedDebitCard, actualDebitCard, "The debit card should match the expected card.");
    }

    @Test
    void testSaveDebitCard() {
        // Arrange
        DebitCard debitCard = new DebitCard("1234567890123456", LocalDate.of(2021, 1, 1), LocalDate.of(2024, 1, 1), 123, 1000.0, 5895256);

        // Act
        debitCardService.save(debitCard);

        // Assert
        // Verify that the DAO save method is called (you can add a verification step here if necessary)
    }

    @Test
    void testUpdateDebitCard() {
        // Arrange
        String cardNumber = "1234567890123456";
        DebitCard updatedDebitCard = new DebitCard(cardNumber, LocalDate.of(2021, 1, 1), LocalDate.of(2024, 1, 1), 456, 1500.0, 5895256);

        // Act
        debitCardService.update(cardNumber, updatedDebitCard);

        // Assert
        // Verify that the DAO update method is called (you can add a verification step here if necessary)
    }

    @Test
    void testDeleteDebitCard() {
        // Arrange
        String cardNumber = "1234567890123456";

        // Act
        debitCardService.delete(cardNumber);

        // Assert
        // Verify that the DAO delete method is called (you can add a verification step here if necessary)
    }
}
