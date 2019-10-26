/*
 * Author: Jiatong Hao
 *
 * This class represents a loan deposit transaction
 */

public class LoanDepositTransaction extends Transaction {
    private double amount;

    public LoanDepositTransaction(Customer customer, BankAccount account, double amount) {
        super(customer, account);
        this.amount = amount;
        setType("Loan deposit");
    }

    public void makeTransaction() {
        BankAccount account = getAccount();
        account.setBalance(account.getBalance() - amount);
    }

    public void saveTransaction() {
        Report report = Bank.getBankInstance().getReport();
        report.addContent(toString());
        report.addRevenue(Bank.getBankInstance().getOperationFee(), "USD");
        report.addLoan(-amount, getAccount().getCurrency().getName());
    }
}
