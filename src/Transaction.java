/*
 * Author: Jiatong Hao
 *
 * This class represents a transaction
 */

import java.util.Random;

public abstract class Transaction {
    private String transactionId;
    private int date;
    private Customer customer;
    private BankAccount account;
    private String type;

    public Transaction(Customer customer, BankAccount account) {
        date = Bank.getBankInstance().getDaysOpen();
        Random rand = new Random();
        transactionId = String.format("%07d", rand.nextInt(1000) + 1) + String.format("%02d", date);
        this.customer = customer;
        this.account = account;
    }

    // save this transaction to the report
    public abstract void saveTransaction();

    // update data related to this transaction
    public abstract void makeTransaction();

    public BankAccount getAccount() {
        return account;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public int getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        String description = getTransactionId() + " " + getDate() + " " + getCustomer().getUsername() + " " +
                getAccount().getAccountNumber() + " " + getType();
        return description;
    }
}
