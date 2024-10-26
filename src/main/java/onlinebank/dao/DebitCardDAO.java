package onlinebank.dao;

import onlinebank.Extractors.DebitcardExtractor;
import onlinebank.Extractors.UserExtractor;
import onlinebank.models.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DebitCardDAO {

    private JdbcTemplate jdbcTemplate;

    public DebitCardDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<DebitCard> getAllDebitcards() {
        return jdbcTemplate.query("select * from debitcard", new DebitcardExtractor());
    }

    public void save(DebitCard debitCard) {
        jdbcTemplate.update("INSERT INTO debitcard VALUES(?, ?, ?, ?,?,?)",
                debitCard.getCardNumber(),
                java.sql.Date.valueOf(debitCard.getIssueCardDate()),
                java.sql.Date.valueOf(debitCard.getCardExpirationDate()),
                debitCard.getCvvCode(),
                debitCard.getCardBalance(),
                debitCard.getPassportNumber());
    }
    public DebitCard show(String cardNumber) {
        return jdbcTemplate.query("SELECT * FROM debitcard WHERE cardNumber=?", new Object[]{cardNumber}, new DebitcardExtractor())
                .stream().findAny().orElse(null);
    }
    public void update(String cardNumber, DebitCard  updatedDebitCard) {
        jdbcTemplate.update("UPDATE debitcard SET issuecarddate=?, cardexpirationdate=?,  " +
                        "cvvcode=?,cardbalance=?  WHERE cardNumber=?",
                updatedDebitCard.getIssueCardDate(), updatedDebitCard.getCardExpirationDate(),  updatedDebitCard.getCvvCode(),
                updatedDebitCard.getCardBalance(),cardNumber);
    }

    public void delete(String cardNumber) {
        jdbcTemplate.update("DELETE FROM debitcard WHERE cardNumber=?", cardNumber);
    }

}
