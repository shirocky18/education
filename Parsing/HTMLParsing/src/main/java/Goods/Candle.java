package Goods;

public class Candle extends Good {

    String material;
    String fireTime;

    public Candle(String name, String price) {
        super(name, price);
    }

    public String getMaterial() {
        return material;
    }
    public String getFireTime() {
        return fireTime;
    }
}
