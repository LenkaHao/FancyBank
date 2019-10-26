/*
 * Author: Jiatong Hao
 *
 * This class represents the launching view of the user interface.
 */

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel{
    JRadioButton customerBtn;
    JRadioButton managerBtn;

    public StartPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // welcome message
        JLabel welcome = new JLabel("Welcome to Fancy Bank!");
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        // identity buttons
        customerBtn = new JRadioButton("I am a customer");
        managerBtn = new JRadioButton("I am a manager");
        customerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        managerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // layout
        add(Box.createVerticalGlue());
        add(welcome);
        add(Box.createVerticalGlue());
        add(customerBtn);
        add(managerBtn);
        add(Box.createVerticalGlue());
    }

    public JRadioButton getCustomerBtn() {
        return customerBtn;
    }

    public JRadioButton getManagerBtn() {
        return managerBtn;
    }

}
