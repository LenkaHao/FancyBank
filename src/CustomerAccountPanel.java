/*
 * Author: Jiatong Hao
 *
 * This class represents the view to display a customer's accounts
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerAccountPanel extends JPanel {
    private Customer customer;
    private CreateAccountPanel createAccountPanel = new CreateAccountPanel();
    private CreateLoanPanel createLoanPanel = new CreateLoanPanel();
    JTabbedPane panes = new JTabbedPane();
    private JButton logoutBtn = new JButton("Log out");
    private JButton refreshBtn = new JButton("Refresh");
    private EventValidator evtValidator;

    public CustomerAccountPanel(String username, EventValidator eventValidator) {
        this.evtValidator = eventValidator;
        setLayout(null);

        // create tabbed pane for each account
        customer = Bank.getBankInstance().getCustomerByUsername(username);
        for (int i = 0; i < customer.getCheckings().size(); i++) {
            CheckingAccount account = customer.getCheckings().get(i);
            panes.addTab("Checking", new CheckingAccountPanel(customer, account, evtValidator));
        }
        for (int i = 0; i < customer.getSavings().size(); i++) {
            SavingAccount account = customer.getSavings().get(i);
            panes.addTab("Saving", new SavingAccountPanel(customer, account, evtValidator));
        }
        for (int i = 0; i < customer.getLoans().size(); i++) {
            Loan account = customer.getLoans().get(i);
            panes.addTab("Loan", new LoanAccountPanel(customer, account, eventValidator));
        }

        // tabbed panes for all accounts and loans
        panes.setBounds(0, 0, 500, 550);
        panes.addTab("New Account", createAccountPanel);
        addCreateAccountListener();
        panes.addTab("New Loan", createLoanPanel);
        addCreateLoanListener();

        // logout and refresh button
        logoutBtn.setBounds(100, 550, 100, 25);
        refreshBtn.setBounds(300, 550, 100, 25);

        add(panes);
        add(logoutBtn);
        add(refreshBtn);
    }

    public void addCreateAccountListener() {
        createAccountPanel.getCreateBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Currency selectedCurrency = (Currency) createAccountPanel.getCurrencyBox().getSelectedItem();
                String accountType = createAccountPanel.getAccountBtnGroup().getSelection().getActionCommand();
                switch (accountType) {
                    case "saving":
                        Bank.getBankInstance().openSavingAccount(customer, selectedCurrency);
                        break;
                    case "checking":
                        Bank.getBankInstance().openCheckingAccount(customer, selectedCurrency);
                        break;
                }
            }
        });
    }

    public void addCreateLoanListener() {
        createLoanPanel.getCreateBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Currency selectedCurrency = (Currency) createLoanPanel.getCurrencyBox().getSelectedItem();
                String amount = createLoanPanel.getLoanAmount().getText();
                double result = evtValidator.validateLoan(amount);
                if (result < 0) {
                    JOptionPane.showMessageDialog(createLoanPanel, "Invalid loan amount");
                } else {
                    Bank.getBankInstance().openLoanAccount(customer, selectedCurrency, result);
                }
            }
        });
    }

    public JButton getLogoutBtn() {
        return logoutBtn;
    }

    public JButton getRefreshBtn() {
        return refreshBtn;
    }

    public Customer getCustomer() {
        return customer;
    }
}
