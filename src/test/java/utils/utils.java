package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class utils {
    public static double extractPriceFromItemAsDouble(WebElement item, boolean fromPrice){
        double dPrice;
        String extractedPrice = extractPriceFromItemAsString(item).split(" a | to")[fromPrice ? 0:1];
        dPrice = Double.parseDouble(extractedPrice.split("[$]|USD")[1]);
        return dPrice;
    }

    public static String extractPriceFromItemAsString(WebElement item){
        String strPrice;
        strPrice= item.findElement(By.className("s-item__price")).getText();
        return strPrice;
    }

    public static String extractNameFromResultItemSearch(WebElement item){
        String strTitle;
        strTitle= item.findElement(By.className("s-item__title")).getText();
        return strTitle;
    }

    public static List<Double> extractPriceItemsInArray(List<WebElement> items){
        List<Double> prices = new ArrayList<>();
        if (items.size() > 0) {
            for (WebElement item : items){
                prices.add(extractPriceFromItemAsDouble(item, true));
            }
        }
        return prices;
    }
    public static List<String> extractNameItemsInArray(List<WebElement> items) {
        List<String> names = new ArrayList<>();
        if (items.size() > 0) {
            for (WebElement item : items) {
                names.add(extractNameFromResultItemSearch(item));
            }
        }
        return names;
    }
}
