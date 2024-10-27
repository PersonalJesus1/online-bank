package onlinebank.Extractors;

import onlinebank.models.DebitCard;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DebitcardExtractor implements ResultSetExtractor<List<DebitCard>> {

    @Override
    public List<DebitCard> extractData(ResultSet rs) throws SQLException {
        List<DebitCard> debitcards = new ArrayList<>();
        while (rs.next()) {
            DebitCard debitCard = new DebitCard();
            debitCard.setCardNumber(rs.getString("cardNumber"));
            debitCard.setIssueCardDate(rs.getDate("issueCardDate").toLocalDate());
            debitCard.setCardExpirationDate(rs.getDate("cardExpirationDate").toLocalDate());
            debitCard.setCvvCode(rs.getInt("cvvCode"));
            debitCard.setCardBalance(rs.getInt("cardBalance"));
            debitCard.setPassportNumber(rs.getInt("passportNumber"));
            debitcards.add(debitCard);
        }
        return debitcards;
    }
}
