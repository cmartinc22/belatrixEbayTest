package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EbaySearch {


    private WebDriver driver;

    private By txtSearchBar = By.id("gh-ac");
    private By btnSearch = By.id("gh-btn");
    private By lblCurrentLanguage = By.cssSelector("#gh-eb-Geo-a-default>span:nth-child(2)");
    private By btnLanguage = By.id("gh-eb-Geo-a-default");

    public EbaySearch(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Navigate to ebay URL
     *
     * @return this to chain calls
     */
    public EbaySearch goToEbay() {
        driver.get("http://www.ebay.com");
        return this;
    }

    public EbaySearch setLanguage(String language) {
        String currentLanguage;
        currentLanguage = driver.findElement(lblCurrentLanguage).getText();
        if (language.equals(currentLanguage)) {
            driver.findElement(btnLanguage).click();
            driver.findElement(By.xpath("//span[@class='gh-eb-Geo-txt' and text()='" + language + "']")).click();
        }
        return this;
    }

    /**
     * Perform a search in ebay site
     *
     * @param strItemName Search Criteria
     * @return an instance of ResultPage to chain calls
     */
    public ResultPage searchItem(String strItemName) {
        driver.findElement(txtSearchBar).sendKeys(strItemName);
        driver.findElement(btnSearch).click();
        return new ResultPage(driver);
    }
}
