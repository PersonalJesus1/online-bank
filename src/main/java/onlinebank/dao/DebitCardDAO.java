package onlinebank.dao;

import onlinebank.models.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DebitCardDAO {

    private List<DebitCard> debitCards;

    {
        debitCards = new ArrayList<>();
        debitCards.add(new DebitCard("1222 0255 5369 4585", LocalDate.of(2022, 6, 12),
                LocalDate.of(2025, 6, 12), 552, 10000, 25668812));
        debitCards.add(new DebitCard("2594 8569 9512 7548", LocalDate.of(2021, 4, 1),
                LocalDate.of(2024, 4, 1), 885, 15000, 52985512));
        debitCards.add(new DebitCard("5268 8594 7588 7419", LocalDate.of(2021, 4, 3),
                LocalDate.of(2024, 4, 3), 917, 13000, 1269855));
        debitCards.add(new DebitCard("8539 0024 7482 0219", LocalDate.of(2023, 1, 29),
                LocalDate.of(2026, 1, 29), 722, 16000, 4286159));
    }

    public List<DebitCard> getAllDebitcards() {
        return debitCards;
    }
}
