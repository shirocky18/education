package parking;

public class Parking {
    private volatile int countCar = 0;
    private final int MAX_COUNT_CAR = 500;
    private volatile boolean comingIn;
    private volatile boolean isNowSell;

    public int getCountCar() {
        return countCar;
    }

    public void setCountCar(int countCar) {
        this.countCar = countCar;
    }

    public boolean isComingIn() {
        return comingIn;
    }

    public void setComingIn(boolean comingIn) {
        this.comingIn = comingIn;
    }
    public boolean isNowSell() {
        return isNowSell;
    }

    public void setNowSell(boolean nowSell) {
        isNowSell = nowSell;
    }

    public synchronized void in(int count) {
        try {
            while(this.isComingIn()) {
                wait();
            }
            while(this.isNowSell()) {
                wait();
            }
            while((MAX_COUNT_CAR - this.getCountCar()) <= count) {
                System.out.println("Not enough places. Now count of places = " + (MAX_COUNT_CAR - this.getCountCar())
                        + ". Bye!");
                return;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.setComingIn(true);
        this.setCountCar(this.getCountCar() + count);
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("IMPORT: " + count + " cars was imported. Now count of cars = " + this.getCountCar());
        this.setComingIn(false);
        notifyAll();
    }

    public synchronized void out(int count) {
        try {
            while (this.isComingIn()) {
                wait();
            }
            while (this.isNowSell()) {
                wait();
            }
            while (this.getCountCar() <= count) {
                System.out.println("Not enough cars. Now count of cars = " + this.getCountCar()
                        + ". Bye!");
                return;
            }
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.setNowSell(true);
        this.setCountCar(this.getCountCar() - count);
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("SELL: " + count + " cars was sold. Now count of cars = " + this.getCountCar());
        this.setNowSell(false);
        notifyAll();
    }
}
