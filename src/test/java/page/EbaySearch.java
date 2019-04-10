package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EbaySearch {


    WebDriver driver;
    String url = "http://www.ebay.com";

    By txtSearchBar = By.id("gh-ac");
    By btnSearch = By.id("gh-btn");

    public EbaySearch(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Navigate to ebay URL
     * @return
     */
    public EbaySearch goToEbay() {
        driver.get(url);
        return this;
    }

    /**
     * Perform a search in ebay site
     * @param strItemName Search Criteria
     * @return an instance of ResultPage to chain calls
     */
    public ResultPage searchItem(String strItemName) {
        driver.findElement(txtSearchBar).sendKeys(strItemName);
        driver.findElement(btnSearch).click();
        return new ResultPage(driver);
    }
}
