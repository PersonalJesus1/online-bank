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
    void testIndex() {
        // Arrange
        List<DebitCard> expectedDebitCards = Arrays.asList(
                new DebitCard("1222 0255 5369 4585", LocalDate.of(2022, 6, 12),
                        LocalDate.of(2025, 6, 12), 552, 10000, 25668812),
                new DebitCard("2594 8569 9512 7548", LocalDate.of(2021, 4, 1),
                        LocalDate.of(2024, 4, 1), 885, 15000, 52985512),
                new DebitCard("5268 8594 7588 7419", LocalDate.of(2021, 4, 3),
                        LocalDate.of(2024, 4, 3), 917, 13000, 1269855),
                new DebitCard("8539 0024 7482 0219", LocalDate.of(2023, 1, 29),
                        LocalDate.of(2026, 1, 29), 722, 16000, 4286159)
        );

        when(debitCardDAO.getAllDebitcards()).thenReturn(expectedDebitCards);

        // Act
        List<DebitCard> actualDebitCards = debitCardService.getAllDebitcards();

        // Assert
        assertEquals(expectedDebitCards, actualDebitCards, "The list of debit cards should match the expected list.");
    }
}
