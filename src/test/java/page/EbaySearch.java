package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EbaySearch {


    WebDriver driver;
    String url = "http://www.ebay.com";

    By txtSearchBar = By.id("gh-ac");
    By btnSearch = By.id("gh-btn");

    public EbaySearch(WebDriver driver){
        this.driver = driver;
    }
    public EbaySearch goToEbay(){
        driver.get(url);
        return this;
    }

    //Searchs an item
    public ResultPage searchItem(String strItemName){
        driver.findElement(txtSearchBar).sendKeys(strItemName);
        driver.findElement(btnSearch).click();
        return new ResultPage(driver);
    }
}
