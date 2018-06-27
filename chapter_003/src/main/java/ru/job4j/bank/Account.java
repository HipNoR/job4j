package ru.job4j.bank;

/**
 * Class for accounts in bank.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class Account {
    private double value;
    private String requisites;

    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public double getValue() {
        return value;
    }

    public String getRequisites() {
        return requisites;
    }

    /**
     * Subtracts money from the account.
     * @param amount will be subtracted.
     */
    public void subAmount(double amount) {
            this.value -= amount;
    }

    /**
     * Add money to account.
     * @param amount will be added.
     */
    public void addAmount(double amount) {
        this.value += amount;
    }

    @Override
    public String toString() {
        return String.format("Account [Requisites: %s. Value: %s.]", requisites, value);
    }

    @Override
    public boolean equals(Object obj) {
        boolean valid = false;
        if (obj != null) {
            if (this == obj) {
                valid = true;
            }
            if (!valid && getClass() == obj.getClass()) {
                Account account = (Account) obj;
                if (this.requisites != null && account.requisites != null) {
                    valid = this.requisites.equals(account.requisites);
                }
            }
        }
        return valid;
    }

    @Override
    public int hashCode() {
        return this.requisites.hashCode();
    }
}
