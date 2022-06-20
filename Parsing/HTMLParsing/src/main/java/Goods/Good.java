package Goods;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Good {

    private String name;
    private String price;
    private static ArrayList<Good> goods = new ArrayList<>();

    public Good(String name, String price) {
        this.name = name;
        this.price = price;
        goods.add(this);
    }

    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Good: " + this.getClass().getName() + ": " + name + "\nPrice: " + price + "\n";
    }

    public static void addGood(Good good) {
        goods.add(good);
    }
    public static void showGoods() {
        goods.forEach(System.out::println);
    }
    public static int getSizeList() {
        return goods.size();
    }
    public static List<Good> getList() {
        return goods;
    }
}
