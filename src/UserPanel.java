/*
 * Author: Jiatong Hao
 *
 * This class represents the view for user login or registration
 */

import javax.swing.*;

public class UserPanel extends JPanel {
    private JButton loginBtn;
    private JButton registerBtn;
    private JTextField usernameField1;
    private JPasswordField pwdField1;
    private JTextField usernameField2;
    private JTextField pwdField2;

    public UserPanel() {
        setLayout(null);

        // login username
        JLabel usernameLabel1 = new JLabel("Username");
        usernameLabel1.setBounds(20, 300, 120, 25);
        add(usernameLabel1);

        usernameField1 = new JTextField();
        usernameField1.setBounds(82, 300, 120, 25);
        add(usernameField1);

        // login password
        JLabel pwdLabel1 = new JLabel("Password");
        pwdLabel1.setBounds(210, 300, 120, 25);
        add(pwdLabel1);

        pwdField1 = new JPasswordField();
        pwdField1.setBounds(270, 300, 120, 25);
        add(pwdField1);

        // login button
        loginBtn = new JButton("Login");
        loginBtn.setBounds(410, 300, 80, 25);
        add(loginBtn);

        // register username
        JLabel usernameLabel2 = new JLabel("Username");
        usernameLabel2.setBounds(20, 350, 120, 25);
        add(usernameLabel2);

        usernameField2 = new JTextField();
        usernameField2.setBounds(82, 350, 120, 25);
        add(usernameField2);

        // register password
        JLabel pwdLabel2 = new JLabel("Password");
        pwdLabel2.setBounds(210, 350, 120, 25);
        add(pwdLabel2);

        pwdField2 = new JTextField();
        pwdField2.setBounds(270, 350, 120, 25);
        add(pwdField2);

        // register button
        registerBtn = new JButton("Register");
        registerBtn.setBounds(410, 350, 80, 25);
        add(registerBtn);
    }

    public JButton getLoginBtn() {
        return loginBtn;
    }

    public JTextField getUsernameField1() {
        return usernameField1;
    }

    public JTextField getPwdField1() {
        return pwdField1;
    }

    public JButton getRegisterBtn() {
        return registerBtn;
    }

    public JTextField getUsernameField2() {
        return usernameField2;
    }

    public JTextField getPwdField2() {
        return pwdField2;
    }
}
