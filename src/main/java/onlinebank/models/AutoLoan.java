
package onlinebank.models;


public class AutoLoan {
    private double mortgageSumm;
    private double currentMortgageSumm;
    private int mortgageMonthsTerm;
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

