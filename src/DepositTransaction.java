/*
 * Author: Jiatong Hao
 *
 * This class represents a deposit transaction on a saving/checking account
 */

public class DepositTransaction extends Transaction {
    private double amount;

    public DepositTransaction(Customer customer, BankAccount account, double amount) {
        super(customer, account);
        this.amount = amount;
        setType("Deposit");
    }

    public void makeTransaction() {
        BankAccount account = getAccount();
        account.setBalance(account.getBalance() + amount);
    }

    public void saveTransaction() {
        Report report = Bank.getBankInstance().getReport();
        report.addContent(toString());
        report.addRevenue(Bank.getBankInstance().getOperationFee(), "USD");
    }
}
