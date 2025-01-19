package onlinebank.models;

public class AutoloanPayment {
    private Long id;
    private double amount;

    public AutoloanPayment() {
    }

    public AutoloanPayment(Long id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
