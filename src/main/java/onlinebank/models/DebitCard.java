
package onlinebank.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "debitcard")
public class DebitCard extends BaseEntity  {
    @Column(name = "cardnumber")
    private String cardNumber;
    @Column(name = "issuecarddate")
    private LocalDate issueCardDate;
    @Column(name = "cardexpirationdate")
    private LocalDate cardExpirationDate; // 3 years
    @Column(name = "cvvcode")
    private int cvvCode;
    @Column(name = "cardbalance")
    private double cardBalance;
    @Column(name = "passportnumber")
    private int passportNumber;

    public DebitCard(String cardNumber, LocalDate issueCardDate, LocalDate cardExpirationDate, int cvvCode, double cardBalance, int passportNumber) {
        this.cardNumber = cardNumber;
        this.issueCardDate = issueCardDate;
        this.cardExpirationDate = cardExpirationDate;
        this.cvvCode = cvvCode;
        this.cardBalance = cardBalance;
        this.passportNumber = passportNumber;
    }
    public DebitCard() {
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getIssueCardDate() {
        return issueCardDate;
    }

    public void setIssueCardDate(LocalDate issueCardDate) {
        this.issueCardDate = issueCardDate;
    }

    public LocalDate getCardExpirationDate() {
        return cardExpirationDate;
    }

    public void setCardExpirationDate(LocalDate cardExpirationDate) {
        this.cardExpirationDate = cardExpirationDate;
    }

    public int getCvvCode() {
        return cvvCode;
    }

    public void setCvvCode(int cvvCode) {
        this.cvvCode = cvvCode;
    }

    public double getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(double cardBalance) {
        this.cardBalance = cardBalance;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }
}
