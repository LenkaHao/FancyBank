import javax.swing.*;

public class ManagerActionPanel extends JPanel {
    private JButton reportBtn = new JButton("Generate Report");
    private JButton increaseDateBtn = new JButton("Increase date by 1");
    private JButton logoutBtn = new JButton("Log out");
    private JLabel reportLabel = new JLabel();

    public ManagerActionPanel() {
        setLayout(null);

        reportBtn.setBounds(50, 100, 130, 25);
        increaseDateBtn.setBounds(200, 100, 150, 25);

        reportLabel.setBounds(50, 130, 350, 250);
        logoutBtn.setBounds(200, 550, 100, 25);

        add(reportBtn);
        add(increaseDateBtn);
        add(reportLabel);
        add(logoutBtn);
    }

    public JButton getReportBtn() {
        return reportBtn;
    }

    public JButton getIncreaseDateBtn() {
        return increaseDateBtn;
    }

    public JButton getLogoutBtn() {
        return logoutBtn;
    }

    public JLabel getReportLabel() {
        return reportLabel;
    }

    public void setReportLabelText(String str) {
        String text = getTextForReportLabel(str);
        reportLabel.setText(text);
    }

    public String getTextForReportLabel(String str) {
        String[] contents = str.split("\n");
        String text = "<html>";
        for (int i = 0; i < contents.length; i++) {
            text = text + "<p>" + contents[i] + "</p>";
        }
        text += "</html>";
        return text;
    }
}
