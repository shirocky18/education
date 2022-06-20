package bank;

import accounts.Account;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private final int SUSPICIOUS_PAYMENT = 50_000;

    private List<Account> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }
    public double getBalance(Account account) {
        return account.getCash();
    }
    public void transaction(Account accFrom, Account accTo, double cash) {
        synchronized (getAccWithMoreAccNum(accFrom, accTo)) {
            synchronized (getAccWithLessAccNum(accFrom, accTo)) {
                boolean fraud = false;
                boolean enoughCash = isEnoughCash(accFrom, cash);
                boolean toMyself = isToMyself(accFrom, accTo);
                //System.out.println("TRANSACTION: " + accFrom.getAccNumber() + " to " + accTo.getAccNumber() + " cash = " + cash);
                if (accFrom.getBlock() || accTo.getBlock()) {
                    System.out.println("One of accounts is blocked\n");
                    return;
                } else if (toMyself) {
                    System.out.println("Transaction to yourself\n");
                    return;
                } else if (!enoughCash) {
                    System.out.println("Account " + accFrom.getAccNumber() + ", you have not enough cash\n");
                    return;
                }
                if ((cash >= SUSPICIOUS_PAYMENT))
                    fraud = isFraud();
                if (!fraud) {
                    accFrom.subtractCash(cash);
                    accTo.addCash(cash);
                    System.out.println("Account " + accFrom.getAccNumber() + " transfer to account " + accTo.getAccNumber() + " cash = "
                            + cash);
                } else if (fraud)
                    block(accFrom, accTo);
                //System.out.println("Account " + accFrom.getAccNumber() + " balance is " + getBalance(accFrom));
                //System.out.println("Account " + accTo.getAccNumber() + " balance is " + getBalance(accTo) + "\n");
            }
        }
    }
    private boolean isFraud() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int[] arr = new int[] {0, 0, 0, 1, 1, 1, 1, 1, 1, 1};
        int random = arr[(int) (Math.random() * arr.length)];
        return random == 0;
    }
    private void block(Account accFrom, Account accTo) {
        accFrom.setBlock();
        accTo.setBlock();
        System.out.println("Account " + accFrom.getAccNumber() + " and account " + accTo.getAccNumber() + " are blocked");
    }
    private boolean isEnoughCash(Account accFrom, double cash) {
        return getBalance(accFrom) > cash;
    }
    public void putAccount(Account account) {
        accounts.add(account);
    }
    public Account getSomeAccount(int accNumber) {
        return accounts.get(accNumber);
    }
    public int getCountAccounts() {
        return accounts.size();
    }
    public boolean isToMyself(Account accFrom, Account accTo) {
        return accFrom.getAccNumber() == accTo.getAccNumber();
    }
    public double getAllCash() {
        double sum = 0;
        for(Account acc : accounts)
            sum += acc.getCash();
        return sum;
    }
    private Account getAccWithMoreAccNum(Account acc1, Account acc2) {
        if(acc1.compareTo(acc2) > 0)
            return acc1;
        return acc2;
    }
    private Account getAccWithLessAccNum(Account acc1, Account acc2) {
        if(acc1.compareTo(acc2) <= 0)
            return acc1;
        return acc2;
    }
}
