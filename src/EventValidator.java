public class EventValidator {
    private double loanUpperBound = 2000;

    public int validateCustomerLogin(String username, String password) {
        if (!Bank.getBankInstance().isUserExisted(username)) {
            return 1;
        } else {
            Customer customer = Bank.getBankInstance().getCustomerByUsername(username);
            if (customer.getPassword().compareTo(password) != 0) {
                return 2;
            }
            return 0;
        }
    }

    public int validateCustomerRegister(String username, String password) {
        if (username.length() == 0 || password.length() == 0) {
            return 1;
        }
        if (Bank.getBankInstance().isUserExisted(username)) {
            return 2;
        }
        return 0;
    }

    public int validateManagerLogin(String username, String password) {
        if (!Bank.getBankInstance().isUserExisted(username)) {
            return 1;
        } else {
            Manager manager = Bank.getBankInstance().getManagerByUsername(username);
            if (manager.getPassword().compareTo(password) != 0) {
                return 2;
            }
            return 0;
        }
    }

    public int validateManagerRegister(String username, String password) {
        if (username.length() == 0 || password.length() == 0) {
            return 1;
        }
        if (Bank.getBankInstance().isUserExisted(username)) {
            return 2;
        }
        return 0;
    }

    public double validateDeposit(String amount) {
        if (amount.length() == 0) {
            return -1;
        }
        double value = 0;
        try {
            value = Double.parseDouble(amount);
        } catch (Exception e) {
            return -1;
        }
        if (value <= 0) {
            return -1;
        }

        return value;
    }

    public double validateWithdraw(BankAccount account, String amount) {
        if (amount.length() == 0) {
            return -1;
        }
        double value = 0;
        try {
            value = Double.parseDouble(amount);
        } catch (Exception e) {
            return -1;
        }
        if (value <= 0 || !Bank.getBankInstance().isEnoughBalance(account, value)) {
            return -1;
        }
        return value;
    }

    public double validateLoan(String amount) {
        if (amount.length() == 0) {
            return -1;
        }
        double value = 0;
        try {
            value = Double.parseDouble(amount);
        } catch (Exception e) {
            return -1;
        }
        if (value <= 0 || value > 2000) {
            return -1;
        }
        return value;
    }

}
