/*
 * Author: Jiatong Hao
 *
 * This class represents a loan creation transaction
 */

public class LoanTransaction extends Transaction {
    private double amount;

    public LoanTransaction(Customer customer, BankAccount account, Double amount) {
        super(customer, account);
        this.amount = amount;
        setType("Create loan");
    }

    @Override
    public void saveTransaction() {
        Report report = Bank.getBankInstance().getReport();
        report.addContent(toString());
        report.addRevenue(Bank.getBankInstance().getLoanFee(), "USD");
        report.addLoan(amount, getAccount().getCurrency().getName());
    }

    @Override
    public void makeTransaction() {
        BankAccount account = getAccount();
        account.setBalance(amount);
    }
}
