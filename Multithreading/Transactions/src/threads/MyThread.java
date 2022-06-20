package threads;

import bank.Bank;

public class MyThread implements Runnable {

    private Bank bank;

    public MyThread(Bank bank) {
        this.bank = bank;
        new Thread(this).start();
    }

    //инициализация транзакций
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            double randomCash = Math.floor(Math.random() * 100_000);
            int randomId1 = (int) (Math.random() * bank.getCountAccounts());
            int randomId2 = (int) (Math.random() * bank.getCountAccounts());
            bank.transaction(bank.getSomeAccount(randomId1), bank.getSomeAccount(randomId2),randomCash);
        }
    }
}
