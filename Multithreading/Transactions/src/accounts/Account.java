package accounts;

import bank.Bank;

public class Account implements Comparable {

    private int accNumber;
    private static int id = 0;
    private double cash;
    private boolean block;
    private Bank bank;

    public Account(Bank bank, double cash) {
        this.cash = cash;
        accNumber = id;
        id++;
        this.bank = bank;
        putToBank();
    }

    public double getCash() {
        return cash;
    }
    public int getAccNumber() {
        return accNumber;
    }
    public void addCash(double cash) {
        this.cash = this.getCash() + cash;
    }
    public void subtractCash(double cash) {
        this.cash = this.getCash() - cash;
    }
    public void setBlock() {
        block = true;
    }
    public boolean getBlock() {
        return block;
    }
    private void putToBank() {
        bank.putAccount(this);
    }

    @Override
    public int compareTo(Object o) {
        if(this.getAccNumber() > ((Account) o).getAccNumber())
            return 1;
        return 0;
    }
}
