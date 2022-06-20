import Goods.Candle;
import Goods.Good;
import Goods.Vase;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {

    public Parser(String path) {
        try {
            Document doc = Jsoup.connect(path).get();
            String shopURL = doc.select("a.js--shop-categories-link").attr("href");
            shopParse(shopURL);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void shopParse(String path) {
        try {
            Document shop = Jsoup.connect(path).get();
            for(Element element : shop.select("a.f__3")) {
                goodParse(element.attr("href"));
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    private void goodParse(String path) {
        if(path.endsWith("svechi") | path.endsWith("vazy")) {
            try {
                Document goodsURL = Jsoup.connect(path).get();
                ArrayList<String> pages = searchCountOfPages(goodsURL);
                pages.add("pre-end");
                pages.add("end");
                do {
                    for (Element candle : goodsURL.select("a.product")) {
                        Document doc = Jsoup.connect(candle.attr("href")).get();
                        String name = doc.select("h1.name").text();
                        String price = doc.select("div.price").select("span.product-price-min").text();
                        if (path.endsWith("vazy")) {
                            new Vase(name, price);
                        }
                        if (path.endsWith("svechi")) {
                            new Candle(name, price);
                        }
                    }
                    //проход по страницам (если есть)
                    if (pages.size() > 2)
                        goodsURL = parsePages(goodsURL, pages);
                    else
                        pages.set(0, "end");
                }
                while (!pages.get(0).equals("end"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
}

    private ArrayList<String> searchCountOfPages(Document doc) {
        ArrayList<String> pages = new ArrayList<>();
        Elements selectorsA = doc.select("a.nav-paginator__link");
        int maxPage = 0;
        for (Element element : selectorsA) {
            if (!element.text().equals("")) {
                maxPage = Integer.parseInt(element.text());
                if (Integer.parseInt(element.text()) > maxPage)
                    maxPage = Integer.parseInt(element.text());
            }
        }
        for(int i = 2; i <= maxPage; i++)
            pages.add(String.valueOf(i));
        return pages;
    }

    private Document parsePages(Document goods, ArrayList<String> pages) {
        if(goods.select("a.nav-paginator__link").hasText()) {
            for(Element element : goods.select("a.nav-paginator__link")) {
                if(element.text().equals(pages.get(0))) {
                    try {
                        goods = Jsoup.connect(element.attr("href")).get();
                        pages.remove(0);
                        break;
                    }
                    catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                if(pages.get(0).equals("pre-end")) {
                    pages.set(0, "end");
                }
            }
            return goods;
        }
        else {
            pages.set(0, "end");
            return null;
        }
    }
}
