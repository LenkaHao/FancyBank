/*
 * Author: Jiatong Hao
 *
 * This class represents a customer
 */

import java.util.List;
import java.util.ArrayList;

public class Customer extends User{
    private List<SavingAccount> savings;
    private List<CheckingAccount> checkings;
    private List<Loan> loans;

    public Customer(String username, String password) {
        super(username, password);
        savings = new ArrayList<SavingAccount>();
        checkings = new ArrayList<CheckingAccount>();
        loans = new ArrayList<Loan>();
    }

    public List<SavingAccount> getSavings() {
        return savings;
    }

    public List<CheckingAccount> getCheckings() {
        return checkings;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void addSavingAccount(SavingAccount account) {
        savings.add(account);
    }

    public void addCheckingAccount(CheckingAccount account) {
        checkings.add(account);
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }
}
