import Goods.Good;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        Parser parser = new Parser("https://maisonettestore.ru/");
        Good.showGoods();
            try {
                PrintWriter writer = new PrintWriter("src/data/file.txt");
                Good.getList().forEach(g -> writer.write(g.toString()));
                writer.flush();
                writer.close();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
    }
}
