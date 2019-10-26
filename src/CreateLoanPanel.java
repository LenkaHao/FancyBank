import javax.swing.*;

public class CreateLoanPanel extends JPanel {
    private JComboBox currencyBox;
    private JTextField loanAmount;
    private JButton createBtn;

    public CreateLoanPanel() {
        setLayout(null);

        // welcome message
        JLabel welcome = new JLabel("Apply for a new loan");
        welcome.setBounds(170, 100, 150, 25);

        // currency selection box
        currencyBox = new JComboBox(Bank.getBankInstance().getCurrencies().toArray());
        currencyBox.setBounds(160, 150, 150, 30);

        loanAmount = new JTextField();
        loanAmount.setBounds(160, 200, 150, 25);

        // create button
        JLabel amountNotes = new JLabel("You can apply for at most 2000 in the currency you chose.");
        amountNotes.setBounds(60, 320, 380, 25);
        JLabel feeNotes = new JLabel("A $20 operation fee is required when you apply for a loan.");
        feeNotes.setBounds(60, 350, 380, 25);
        createBtn = new JButton("Create Loan");
        createBtn.setBounds(160, 380, 150, 25);

        add(welcome);
        add(currencyBox);
        add(loanAmount);
        add(amountNotes);
        add(feeNotes);
        add(createBtn);
    }

    public JButton getCreateBtn() {
        return createBtn;
    }

    public JComboBox getCurrencyBox() {
        return currencyBox;
    }

    public JTextField getLoanAmount() {
        return loanAmount;
    }
}
