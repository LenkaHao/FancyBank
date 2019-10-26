/*
 * Author: Jiatong Hao
 *
 * This class represents the view for customer login or registration
 */

import javax.swing.*;

public class CustomerPanel extends UserPanel {

    public CustomerPanel() {
        super();

        // welcome message
        JLabel welcome = new JLabel("Dear customer, welcome to Fancy Bank!");
        welcome.setBounds(130, 180, 300, 25);
        add(welcome);
    }

}
