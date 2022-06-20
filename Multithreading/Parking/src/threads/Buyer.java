package threads;

import parking.Parking;

public class Buyer implements Runnable {

    private Parking parking;

    public Buyer (Parking parking) {
        this.parking = parking;
    }

    @Override
    public void run() {
        parking.out(10);
        parking.out(200);
        parking.out(40);
        parking.out(230);
        parking.out(20);
        parking.out(100);
    }
}
