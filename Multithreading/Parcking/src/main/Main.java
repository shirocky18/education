package main;

import parking.Parking;
import threads.Buyer;
import threads.Seller;

public class Main {

    public static void main(String[] args) {
        Parking parking = new Parking();
        new Thread(new Seller(parking)).start();
        new Thread(new Buyer(parking)).start();
    }
}
