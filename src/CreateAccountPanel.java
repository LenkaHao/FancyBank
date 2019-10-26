import javax.swing.*;

public class CreateAccountPanel extends JPanel {
    private ButtonGroup accountBtnGroup;
    private JComboBox currencyBox;
    private JButton createBtn;

    public CreateAccountPanel() {
        setLayout(null);

        // welcome message
        JLabel welcome = new JLabel("Create a new account");
        welcome.setBounds(170, 100, 150, 25);

        // currency selection box
        currencyBox = new JComboBox(Bank.getBankInstance().getCurrencies().toArray());
        currencyBox.setBounds(160, 150, 150, 30);

        // account type selection
        JRadioButton savingBtn = new JRadioButton("Open a saving account: deposit 1000 immediately", true);
        savingBtn.setActionCommand("saving");
        savingBtn.setBounds(60, 230, 380, 25);

        JRadioButton checkingBtn = new JRadioButton("Open a checking account");
        checkingBtn.setActionCommand("checking");
        checkingBtn.setBounds(60, 270, 200, 25);

        accountBtnGroup = new ButtonGroup();
        accountBtnGroup.add(savingBtn);
        accountBtnGroup.add(checkingBtn);

        // create button
        JLabel feeNotes = new JLabel("A $10 operation fee is required when you create an account.");
        feeNotes.setBounds(60, 350, 380, 25);
        createBtn = new JButton("Create Account");
        createBtn.setBounds(160, 380, 150, 25);

        // layout
        add(welcome);
        add(currencyBox);
        add(savingBtn);
        add(checkingBtn);
        add(feeNotes);
        add(createBtn);
    }

    public JButton getCreateBtn() {
        return createBtn;
    }

    public JComboBox getCurrencyBox() {
        return currencyBox;
    }

    public ButtonGroup getAccountBtnGroup() {
        return accountBtnGroup;
    }
}
