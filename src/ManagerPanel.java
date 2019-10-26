/*
 * Author: Jiatong Hao
 *
 * This class represents the view for customer login or registration
 */

import javax.swing.*;

public class ManagerPanel extends UserPanel {

    public ManagerPanel() {
        super();

        // welcome message
        JLabel welcome = new JLabel("Dear manager, welcome to Fancy Bank!");
        welcome.setBounds(130, 180, 300, 25);
        add(welcome);
    }

}
