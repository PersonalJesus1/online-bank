package onlinebank.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "autoloan")
public class AutoLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "mortgagesumm")
    @Min(value = 100, message = "Summ must be at least 100")
    private double mortgageSumm;
    @Column(name = "currentmortgagesumm")
    private double currentMortgageSumm;

    @Min(value = 1, message = "Mortgage months term must be at least 1")
    @Column(name = "mortgagemonthsterm")
    private int mortgageMonthsTerm;
    @Column(name = "passportnumber")
    private int passportNumber;

    public AutoLoan(double mortgageSumm, double currentMortgageSumm, int mortgageMonthsTerm, int passportNumber) {
        this.mortgageSumm = mortgageSumm;
        this.currentMortgageSumm = currentMortgageSumm;
        this.mortgageMonthsTerm = mortgageMonthsTerm;
        this.passportNumber = passportNumber;
    }

    public AutoLoan() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMortgageSumm() {
        return mortgageSumm;
    }

    public void setMortgageSumm(double mortgageSumm) {
        this.mortgageSumm = mortgageSumm;
    }

    public double getCurrentMortgageSumm() {
        return currentMortgageSumm;
    }

    public void setCurrentMortgageSumm(double currentMortgageSumm) {
        this.currentMortgageSumm = currentMortgageSumm;
    }

    public int getMortgageMonthsTerm() {
        return mortgageMonthsTerm;
    }

    public void setMortgageMonthsTerm(int mortgageMonthsTerm) {
        this.mortgageMonthsTerm = mortgageMonthsTerm;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }
}