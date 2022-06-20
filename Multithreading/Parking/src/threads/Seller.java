package threads;

import parking.Parking;

public class Seller implements Runnable {

    private Parking parking;

    public Seller (Parking parking) {
        this.parking = parking;
    }

    @Override
    public void run() {
        parking.in(300);
        parking.in(50);
        parking.in(150);
        parking.in(350);
        parking.in(170);
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        parking.in(100);
    }
}
