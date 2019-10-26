/*
 * Author: Jiatong Hao
 *
 * This class represents the user interface of the bank system
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankUI extends JFrame {
    int width = 500;
    int height = 600;
    StartPanel startPanel;
    CustomerPanel customerPanel;
    CustomerAccountPanel customerAccountPanel;
    ManagerPanel managerPanel;
    ManagerActionPanel managerActionPanel;

    EventValidator evtValidator = new EventValidator();


    public BankUI() {
        super("Fancy Bank");
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        displayStartPanel();
        getContentPane().add(startPanel);

        setVisible(true);
    }

    // generate the launching view
    private void displayStartPanel() {
        startPanel = new StartPanel();
        addIdentityListener();
    }

    //  generate view for customer login or register
    private void displayCustomerPanel() {
        customerPanel = new CustomerPanel();
        addCustomerListener();
    }

    //  generate view for manager login or register
    private void displayManagerPanel() {
        managerPanel = new ManagerPanel();
        addManagerListener();
    }

    // generate view to display a customer's accounts
    private void displayCustomerAccountPanel(String username) {
        customerAccountPanel = new CustomerAccountPanel(username, evtValidator);
        addCustomerLogoutListener();
        addCustomerRefreshListener();
    }

    // generate view for manager actions
    private void displayManagerActionPanel() {
        managerActionPanel = new ManagerActionPanel();
        addManagerIncreaseDateListener();
        addManagerReportListener();
        addManagerLogoutListener();
    }

    // button event: user acts as a manager or a customer
    private void addIdentityListener() {
        startPanel.getCustomerBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayCustomerPanel();
                getContentPane().removeAll();
                getContentPane().invalidate();
                getContentPane().add(customerPanel);
                getContentPane().revalidate();
            }
        });
        startPanel.getManagerBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayManagerPanel();
                getContentPane().removeAll();
                getContentPane().invalidate();
                getContentPane().add(managerPanel);
                getContentPane().revalidate();
            }
        });
    }

    // button event: customer login or registration
    private void addCustomerListener() {
        customerPanel.getLoginBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = customerPanel.getUsernameField1().getText();
                String pwd = customerPanel.getPwdField1().getText();
                int result = evtValidator.validateCustomerLogin(username, pwd);
                if (result == 1) {
                    JOptionPane.showMessageDialog(customerPanel, "Customer doesn't exist. Please try again.");
                } else if (result == 2) {
                    JOptionPane.showMessageDialog(customerPanel, "Wrong password. Please try again.");
                } else {
                    displayCustomerAccountPanel(username);
                    getContentPane().removeAll();
                    getContentPane().invalidate();
                    getContentPane().add(customerAccountPanel);
                    getContentPane().revalidate();
                }
            }
        });

        customerPanel.getRegisterBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = customerPanel.getUsernameField2().getText();
                String pwd = customerPanel.getPwdField2().getText();
                int result = evtValidator.validateCustomerRegister(username, pwd);
                if (result == 1) {
                    JOptionPane.showMessageDialog(customerPanel, "Please fill in both fields.");
                } else if (result == 2) {
                    JOptionPane.showMessageDialog(customerPanel, "Customer name already exists. Please use another name.");
                } else {
                    Bank.getBankInstance().addCustomer(username, pwd);
                    displayCustomerAccountPanel(username);
                    getContentPane().removeAll();
                    getContentPane().invalidate();
                    getContentPane().add(customerAccountPanel);
                    getContentPane().revalidate();
                }
            }
        });
    }

    // button event: manager login or registration
    private void addManagerListener() {
        managerPanel.getLoginBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = managerPanel.getUsernameField1().getText();
                String pwd = managerPanel.getPwdField1().getText();
                int result = evtValidator.validateManagerLogin(username, pwd);
                if (result == 1) {
                    JOptionPane.showMessageDialog(managerPanel, "Manager doesn't exist. Please try again.");
                } else if (result == 2) {
                    JOptionPane.showMessageDialog(managerPanel, "Wrong password. Please try again.");
                } else {
                    displayManagerActionPanel();
                    getContentPane().removeAll();
                    getContentPane().invalidate();
                    getContentPane().add(managerActionPanel);
                    getContentPane().revalidate();
                }
            }
        });

        managerPanel.getRegisterBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = managerPanel.getUsernameField2().getText();
                String pwd = managerPanel.getPwdField2().getText();
                int result = evtValidator.validateManagerRegister(username, pwd);
                if (result == 1) {
                    JOptionPane.showMessageDialog(managerPanel, "Please fill in both fields.");
                } else if (result == 2) {
                    JOptionPane.showMessageDialog(managerPanel, "Manager name already exists. Please use another name.");
                } else {
                    Bank.getBankInstance().addManager(username, pwd);
                    displayManagerActionPanel();
                    getContentPane().removeAll();
                    getContentPane().invalidate();
                    getContentPane().add(managerActionPanel);
                    getContentPane().revalidate();
                }
            }
        });
    }

    // button event: log out and go back to the launching view
    private void addCustomerLogoutListener() {
        customerAccountPanel.getLogoutBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayStartPanel();
                getContentPane().removeAll();
                getContentPane().invalidate();
                getContentPane().add(startPanel);
                getContentPane().revalidate();
            }
        });
    }

    // button event: display latest data associated with the current customer
    private void addCustomerRefreshListener() {
        customerAccountPanel.getRefreshBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer customer = customerAccountPanel.getCustomer();
                displayCustomerAccountPanel(customer.getUsername());
                getContentPane().removeAll();
                getContentPane().invalidate();
                getContentPane().add(customerAccountPanel);
                getContentPane().revalidate();
            }
        });
    }

    // button event: display report
    private void addManagerReportListener() {
        managerActionPanel.getReportBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Report report = Bank.getBankInstance().getReport();
                managerActionPanel.setReportLabelText(report.toString());
            }
        });
    }

    // button event: increase date by 1 in the bank system
    private void addManagerIncreaseDateListener() {
        managerActionPanel.getIncreaseDateBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bank.getBankInstance().increaseDate();
            }
        });
    }

    // button event: log out and go back to the launching view
    private void addManagerLogoutListener() {
        managerActionPanel.getLogoutBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayStartPanel();
                getContentPane().removeAll();
                getContentPane().invalidate();
                getContentPane().add(startPanel);
                getContentPane().revalidate();
            }
        });
    }

    public static void main(String[] args) {
        new BankUI();
    }
}
