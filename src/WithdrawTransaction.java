/*
 * Author: Jiatong Hao
 *
 * This class represents a withdraw transaction on a saving/checking account
 */

public class WithdrawTransaction extends Transaction {
    private double amount;

    public WithdrawTransaction(Customer customer, BankAccount account, double amount) {
        super(customer, account);
        this.amount = amount;
        setType("Withdraw");
    }

    public void makeTransaction() {
        BankAccount account = getAccount();
        account.setBalance(account.getBalance() - amount);
    }

    public void saveTransaction() {
        Report report = Bank.getBankInstance().getReport();
        report.addContent(toString());
        report.addRevenue(Bank.getBankInstance().getOperationFee(), "USD");
    }
}
