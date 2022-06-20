package main;

import accounts.Account;
import bank.Bank;
import threads.MyThread;

public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank();
        //добавление аккаунтов в банк
        for (int i = 0; i < 1_000_000; i++) {
            double randomCash = Math.floor(Math.random() * 200_000);
            new Account(bank, randomCash);
        }
        System.out.println("1 sum = " + bank.getAllCash());
        Thread thread = new Thread();
        for (int i = 0; i < 100; i++) {
            thread = new Thread(new MyThread(bank));
        }
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("2 sum = " + bank.getAllCash());
    }
}
