package onlinebank.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "autoloan")
public class AutoLoan extends BaseEntity  {
    @Column(name = "mortgagesumm")
    private double mortgageSumm;
    @Column(name = "currentmortgagesumm")
    private double currentMortgageSumm;
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