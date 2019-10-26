/*
 * Author: Jiatong Hao
 *
 * This class represents a saving account
 */

public class SavingAccount extends BankAccount {
    public SavingAccount(Currency currency) {
        super(currency);
        setBalance(1000);
    }
}
