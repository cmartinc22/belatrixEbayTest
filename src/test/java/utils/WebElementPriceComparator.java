package utils;

import org.openqa.selenium.WebElement;

import java.util.Comparator;

import static utils.Utils.extractPriceFromItemAsDouble;

public class WebElementPriceComparator implements Comparator<WebElement> {
    private int sort;

    public WebElementPriceComparator(int sort) {
        this.sort = sort;
    }

    @Override
    public int compare(WebElement w1, WebElement w2) {
        Double productPrice1 = extractPriceFromItemAsDouble(w1, true);
        Double productPrice2 = extractPriceFromItemAsDouble(w2, true);

        if (sort == 1) {
            // ascending order By Price
            return productPrice1.compareTo(productPrice2);
        } else if (sort == 2) {
            // descending order By Price
            return productPrice2.compareTo(productPrice1);
        } else {
            return 0;
        }
    }
}


