/*
 * Author: Jiatong Hao
 *
 * This class represents a report
 */

import java.util.ArrayList;
import java.util.List;

public class Report {
    private List<String> contents;
    private List<Double> revenues;
    private List<Double> pendingLoans;

    public Report() {
        contents = new ArrayList<String>();
        revenues = new ArrayList<Double>();
        revenues.add(0.0); revenues.add(0.0); revenues.add(0.0);
        pendingLoans = new ArrayList<Double>();
        pendingLoans.add(0.0); pendingLoans.add(0.0); pendingLoans.add(0.0);
    }


    public void addContent(String content) {
        contents.add(content);
    }

    public void addRevenue(double amount, String currencyName) {
        for (int i = 0; i < Bank.getBankInstance().getCurrencies().size(); i++) {
            if (currencyName == Bank.getBankInstance().getCurrencies().get(i).getName()) {
                revenues.set(i, revenues.get(i) + amount);
            }
        }
    }

    public void addLoan(double amount, String currencyName) {
        for (int i = 0; i < Bank.getBankInstance().getCurrencies().size(); i++) {
            if (currencyName == Bank.getBankInstance().getCurrencies().get(i).getName()) {
                pendingLoans.set(i, pendingLoans.get(i) + amount);
            }
        }
    }

    public String toString() {
        String columnMsg = "TransactionID   Date   Customer   Account#  Description\n";
        String contentMsg = "";
        for (int i = 0; i < contents.size(); i++) {
            contentMsg = contentMsg + contents.get(i) + "\n";
        }
        String revenueMsg = "Revenue\n";
        for (int i = 0; i < revenues.size(); i++) {
            revenueMsg = revenueMsg + Bank.getBankInstance().getCurrencies().get(i).getSymbol() + revenues.get(i) + "\n";
        }
        String loanMsg = "Pending loan\n";
        for (int i = 0; i < pendingLoans.size(); i++) {
            loanMsg = loanMsg + Bank.getBankInstance().getCurrencies().get(i).getSymbol() + pendingLoans.get(i) + "\n";
        }
        return columnMsg + contentMsg + revenueMsg + loanMsg;
    }
}
