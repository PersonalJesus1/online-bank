package onlinebank.models;

public class TransferRequest {
    private String senderCardNumber;
    private String receiverCardNumber;
    private double amount;

    public TransferRequest() {
    }

    public TransferRequest(String senderCardNumber, String receiverCardNumber, double amount) {
        this.senderCardNumber = senderCardNumber;
        this.receiverCardNumber = receiverCardNumber;
        this.amount = amount;
    }

    public String getSenderCardNumber() {
        return senderCardNumber;
    }

    public void setSenderCardNumber(String senderCardNumber) {
        this.senderCardNumber = senderCardNumber;
    }

    public String getReceiverCardNumber() {
        return receiverCardNumber;
    }

    public void setReceiverCardNumber(String receiverCardNumber) {
        this.receiverCardNumber = receiverCardNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}