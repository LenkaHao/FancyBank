/*
 * Author: Jiatong Hao
 *
 * This class represents a bank object
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Bank {
    private static Bank bankInstance = null;
    private String name;
    private List<Manager> managers;
    private List<Customer> customers;

    private List<SavingAccount> savings;
    private List<CheckingAccount> checkings;
    private List<Currency> currencies;
    private List<Loan> loans;

    private double savingDailyInterest = 0.001;
    private double loanDailyInterest = 0.01;
    private double operationFee = 5;
    private double accountFee = 10;
    private double loanFee = 20;

    private int daysOpen;
    private Report report;


    private Bank() {
        name = "Fancy Bank";
        managers = new ArrayList<Manager>();
        customers = new ArrayList<Customer>();
        savings = new ArrayList<SavingAccount>();
        checkings = new ArrayList<CheckingAccount>();
        loans = new ArrayList<Loan>();

        currencies = new ArrayList<Currency>(3);
        currencies.add(new Currency("USD", "\u0024"));
        currencies.add(new Currency("EURO", "\u20ac"));
        currencies.add(new Currency("RMB", "\u00a5"));

        daysOpen = 1;
        report = new Report();
    }

    public static Bank getBankInstance() {
        if (bankInstance == null) {
            bankInstance = new Bank();
        }

        return bankInstance;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public List<Customer> getCustomers() {
        return customers;
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

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public int getDaysOpen() {
        return daysOpen;
    }

    public void setDaysOpen(int days) {
        daysOpen = days;
    }

    public Report getReport() {
        return report;
    }

    public double getOperationFee() {
        return operationFee;
    }

    public double getAccountFee() {
        return accountFee;
    }

    public double getLoanFee() {
        return loanFee;
    }

    // generate a unique account number
    public String generateAccountNumber() {
        Random rand = new Random();
        int number = 0;
        while (number <= 0 || getBankInstance().isAccountExisted(String.format("%04d", number))) {
            number = rand.nextInt(1000) + 1;
        }
        return String.format("%04d", number);
    }

    public boolean isAccountExisted(String number) {
        for (int i = 0; i < getSavings().size(); i++) {
            if (number == getSavings().get(i).getAccountNumber()) {
                return true;
            }
        }
        for (int i = 0; i < getCheckings().size(); i++) {
            if (number == getCheckings().get(i).getAccountNumber()) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserExisted(String username) {
        for (int i = 0; i < getBankInstance().getManagers().size(); i++) {
            if (username.compareTo(getBankInstance().getManagers().get(i).getUsername()) == 0) {
                return true;
            }
        }
        for (int i = 0; i < getBankInstance().getCustomers().size(); i++) {
            if (username.compareTo(getBankInstance().getCustomers().get(i).getUsername()) == 0) {
                return true;
            }
        }
        return false;
    }

    public Customer getCustomerByUsername(String username) {
        Customer customer;
        for (int i = 0; i < getCustomers().size(); i++) {
            customer = getCustomers().get(i);
            if (username.compareTo(customer.getUsername()) == 0) {
                return customer;
            }
        }
        return null;
    }

    public Manager getManagerByUsername(String username) {
        Manager manager;
        for (int i = 0; i < getManagers().size(); i++) {
            manager = getManagers().get(i);
            if (username.compareTo(manager.getUsername()) == 0) {
                return manager;
            }
        }
        return null;
    }

    public void addCustomer(String username, String pwd) {
        Customer customer = new Customer(username, pwd);
        getBankInstance().getCustomers().add(customer);
    }

    public void addManager(String username, String pwd) {
        Manager manager = new Manager(username, pwd);
        getBankInstance().getManagers().add(manager);
    }

    public void openSavingAccount(Customer customer, Currency currency) {
        SavingAccount newAccount = new SavingAccount(currency);
        newAccount.setBalance(1000);
        savings.add(newAccount);
        customer.addSavingAccount(newAccount);
        CardTransaction transaction = new CardTransaction(customer, newAccount);
        transaction.saveTransaction();
    }

    public void openCheckingAccount(Customer customer, Currency currency) {
        CheckingAccount newAccount = new CheckingAccount(currency);
        checkings.add(newAccount);
        customer.addCheckingAccount(newAccount);
        CardTransaction transaction = new CardTransaction(customer, newAccount);
        transaction.saveTransaction();
    }

    public void openLoanAccount(Customer customer, Currency currency, Double amount) {
        Loan loan = new Loan(currency);
        loans.add(loan);
        customer.addLoan(loan);
        LoanTransaction transaction = new LoanTransaction(customer, loan, amount);
        transaction.makeTransaction();
        transaction.saveTransaction();
    }

    public void makeDeposit(Customer customer, BankAccount account, Double amount) {
        DepositTransaction transaction = new DepositTransaction(customer, account, amount);
        transaction.makeTransaction();
        transaction.saveTransaction();
    }

    public void makeWithdraw(Customer customer, BankAccount account, Double amount) {
        WithdrawTransaction transaction = new WithdrawTransaction(customer, account, amount);
        transaction.makeTransaction();
        transaction.saveTransaction();
    }

    public void makeLoanDeposit(Customer customer, Loan account, Double amount) {
        LoanDepositTransaction transaction = new LoanDepositTransaction(customer, account, amount);
        transaction.makeTransaction();
        transaction.saveTransaction();
    }

    public boolean isEnoughBalance(BankAccount account, Double amount) {
        if (amount > account.getBalance()) {
            return false;
        }
        return true;
    }

    // increase system date; update interests
    public void increaseDate() {
        setDaysOpen(getDaysOpen() + 1);
        computeBalance();
        double loanTotal1 = 0, loanTotal2 = 0, loanTotal3 = 0;
        for (int i = 0; i < getLoans().size(); i++) {
            Loan loan = getLoans().get(i);
            switch (loan.getCurrency().getName()) {
                case "USD":
                    loanTotal1 += loan.getBalance();
                    break;
                case "EURO":
                    loanTotal2 += loan.getBalance();
                    break;
                case "RMB":
                    loanTotal3 += loan.getBalance();
                    break;
            }
        }
        report.addRevenue(loanTotal1 * loanDailyInterest, "USD");
        report.addRevenue(loanTotal2 * loanDailyInterest, "EURO");
        report.addRevenue(loanTotal3 * loanDailyInterest, "RMB");
        report.addLoan(loanTotal1 * loanDailyInterest, "USD");
        report.addLoan(loanTotal2 * loanDailyInterest, "EURO");
        report.addLoan(loanTotal3 * loanDailyInterest, "RMB");
    }

    // update daily interests for saving accounts and loans
    public void computeBalance() {
        for (int i = 0; i < getSavings().size(); i++) {
            SavingAccount account = getSavings().get(i);
            double prevBalance = account.getBalance();
            account.setBalance(prevBalance * (1 + savingDailyInterest));
        }

        for (int i = 0; i < getLoans().size(); i++) {
            Loan account = getLoans().get(i);
            double prevBalance = account.getBalance();
            account.setBalance(prevBalance * (1 + loanDailyInterest));
        }
    }
}
