/*
 * Author: Jiatong Hao
 *
 * This class represents an opening account transaction
 */

public class CardTransaction extends Transaction {

    public CardTransaction(Customer customer, BankAccount account) {
        super(customer, account);
        setType("Open/Delete Account");
    }

    @Override
    public void saveTransaction() {
        Report report = Bank.getBankInstance().getReport();
        report.addContent(toString());
        report.addRevenue(Bank.getBankInstance().getAccountFee(), "USD");
    }

    @Override
    public void makeTransaction() {
        return;
    }

}
