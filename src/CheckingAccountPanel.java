/*
 * Author: Jiatong Hao
 *
 * This class represents a tab that shows info and get user action on a checking account.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckingAccountPanel extends JPanel {
    private Customer customer;
    private CheckingAccount account;
    private JTextField depositAmount;
    private JButton depositBtn;
    private JTextField withdrawAmount;
    private JButton withdrawBtn;
    private EventValidator evtValidator;

    public CheckingAccountPanel(Customer customer, CheckingAccount account, EventValidator eventValidator) {
        this.customer = customer;
        this.account = account;
        this.evtValidator = eventValidator;

        setLayout(null);

        JLabel accountDescription = new JLabel("Checking Account #" + account.getAccountNumber());
        accountDescription.setBounds(80, 80, 200, 25);
        JLabel accountBalance = new JLabel("Balance: " + account.getCurrency() + account.getBalance());
        accountBalance.setBounds(80, 120, 200, 25);

        depositAmount = new JTextField();
        depositAmount.setBounds(80, 220, 150, 25);
        depositBtn = new JButton("Deposit");
        depositBtn.setBounds(300, 220, 120, 25);
        addDepositListener();

        withdrawAmount = new JTextField();
        withdrawAmount.setBounds(80, 260, 150, 25);
        withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setBounds(300, 260, 120, 25);
        addWithdrawListener();

        JLabel notes = new JLabel("A $5 operation fee is required for each operation.");
        notes.setBounds(90, 300, 350, 25);

        add(accountDescription);
        add(accountBalance);
        add(depositAmount);
        add(depositBtn);
        add(withdrawAmount);
        add(withdrawBtn);
        add(notes);
    }

    private void addDepositListener() {
        depositBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = depositAmount.getText();
                double result = evtValidator.validateDeposit(amount);
                if (result < 0) {
                    JOptionPane.showMessageDialog(depositBtn.getRootPane(), "Invalid deposit input");
                } else {
                    Bank.getBankInstance().makeDeposit(customer, account, result);
                }
            }
        });
    }

    public void addWithdrawListener() {
        withdrawBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = withdrawAmount.getText();
                double result = evtValidator.validateWithdraw(account, amount);
                if (result < 0) {
                    JOptionPane.showMessageDialog(withdrawBtn.getRootPane(), "Invalid withdraw input");
                } else {
                    Bank.getBankInstance().makeWithdraw(customer, account, result);
                }
            }
        });
    }
}
