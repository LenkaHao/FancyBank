/*
 * Author: Jiatong Hao
 *
 * This class represents a bank account
 */

public class BankAccount {
    private String accountNumber;
    private double balance;
    private Currency currency;

    public BankAccount(Currency currency) {
        accountNumber = Bank.getBankInstance().generateAccountNumber();
        balance = 0;
        this.currency = currency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setBalance(double newBalance) {
        balance = newBalance;
        balance = Math.round(balance * 100.0) / 100.0;
    }

    public double getBalance() {
        return balance;
    }

    public Currency getCurrency() {
        return currency;
    }
}
