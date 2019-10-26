/*
 * Author: Jiatong Hao
 *
 * This class represents a tab that shows info and get user action on a loan account.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoanAccountPanel extends JPanel {
    private Customer customer;
    private Loan account;
    private JTextField depositAmount;
    private JButton depositBtn;
    private EventValidator evtValidator;

    public LoanAccountPanel(Customer customer, Loan account, EventValidator eventValidator) {
        this.customer = customer;
        this.account = account;
        this.evtValidator = eventValidator;

        setLayout(null);

        JLabel accountDescription = new JLabel("Loan Account #" + account.getAccountNumber());
        accountDescription.setBounds(80, 80, 200, 25);
        JLabel accountBalance = new JLabel("Debt: " + account.getCurrency() + account.getBalance());
        accountBalance.setBounds(80, 120, 200, 25);

        depositAmount = new JTextField();
        depositAmount.setBounds(80, 220, 150, 25);
        depositBtn = new JButton("Deposit");
        depositBtn.setBounds(300, 220, 120, 25);
        addDepositListener();

        JLabel notes = new JLabel("A $5 operation fee is required for each operation.");
        notes.setBounds(90, 300, 350, 25);

        add(accountDescription);
        add(accountBalance);
        add(depositAmount);
        add(depositBtn);
        add(notes);
    }

    private void addDepositListener() {
        depositBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = depositAmount.getText();
                double result = evtValidator.validateWithdraw(account, amount);
                if (result < 0) {
                    JOptionPane.showMessageDialog(depositBtn.getRootPane(), "Invalid deposit input");
                } else {
                    Bank.getBankInstance().makeLoanDeposit(customer, account, result);
                }
            }
        });
    }
}
