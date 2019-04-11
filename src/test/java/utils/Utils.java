package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Utils {

    /**
     * Extract the price as Double Number. In case of range of prices, take de from o to depending of boolean variable
     * fromPrice
     *
     * @param item      The result item
     * @param fromPrice true return the lower range price (from), false return the higer price (to)
     * @return Price
     */
    static double extractPriceFromItemAsDouble(WebElement item, boolean fromPrice) {
        double dPrice;
        String extractedPrice = extractPriceFromItemAsString(item).split(" a | to")[fromPrice ? 0 : 1];
        dPrice = Double.parseDouble(extractedPrice.split("[$]|USD")[1]);
        return dPrice;
    }

    /**
     * Extract from an Item the Price as String
     *
     * @param item Search Result Item
     * @return String price
     */
    public static String extractPriceFromItemAsString(WebElement item) {
        String strPrice;
        strPrice = item.findElement(By.className("s-item__price")).getText();
        return strPrice;
    }

    /**
     * Extract from an Item the Name of the product
     *
     * @param item Result Item
     * @return The Product Name
     */
    public static String extractNameFromResultItemSearch(WebElement item) {
        String strTitle;
        strTitle = item.findElement(By.className("s-item__title")).getText();
        return strTitle;
    }
}
