package utils;

import org.openqa.selenium.WebElement;

import java.util.Comparator;

import static utils.Utils.extractNameFromResultItemSearch;

public class WebElementNameComparator implements Comparator<WebElement> {
    private int sort;

    public WebElementNameComparator(int sort) {
        this.sort = sort;
    }

    @Override
    public int compare(WebElement w1, WebElement w2) {
        String productName1 = extractNameFromResultItemSearch(w1);
        String productName2 = extractNameFromResultItemSearch(w2);

        if (sort == 1) {
            //Ascending
            return productName1.compareTo(productName2);
        } else if (sort == 2) {
            //Descending
            return productName2.compareTo(productName1);
        } else {
            return 0;
        }
    }
}
