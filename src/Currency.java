/*
 * Author: Jiatong Hao
 *
 * This class represents a type of currency
 */

public class Currency {
    private String name;
    private String symbol;

    public Currency(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String toString() {
        return getName() + " (" + getSymbol() + ") ";
    }
}
