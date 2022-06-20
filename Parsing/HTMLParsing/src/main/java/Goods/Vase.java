package Goods;

public class Vase extends Good {

    String color;
    String material;

    public Vase(String name, String price) {
        super(name, price);
    }

    public String getColor() {
        return color;
    }
    public String getMaterial() {
        return material;
    }
}
