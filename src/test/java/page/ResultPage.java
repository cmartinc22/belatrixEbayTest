package page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static utils.utils.extractNameFromResultItemSearch;
import static utils.utils.extractPriceFromItemAsString;
import static utils.utils.extractPriceItemsInArray;

public class ResultPage {

    WebDriver driver;
    Actions action;
    List<String> list = new ArrayList<String>();
    List<WebElement> top5Items = new ArrayList<>();
    List<WebElement> allResults = new ArrayList<>();

    By txtBrandFilter = By.className("x-searchable-list__textbox__aspect-Brand");
    By lblResultsCount = By.className("srp-controls__count-heading");
    By btnOrderBy = By.className("srp-controls__control--legacy");
    By resultItems = By.className("s-item");
    By resultTop5Items = By.xpath("//div[@id='srp-river-main']/div/ul/li[position() <= 5]");

    public ResultPage(WebDriver driver) {
        this.driver = driver;
        this.action = new Actions(driver);
    }

    //Set brand in Brand's filter
    private void setBrandFilter(String strBrand) {
        driver.findElement(txtBrandFilter).sendKeys(strBrand);
    }

    private void selectFilteredBrand(String strBrand){
        driver.findElement(By.xpath("//ul[@class='x-searchable-list__fieldset x-refine__main__value']/li/descendant::span[text()='"+strBrand+"']/preceding-sibling::input")).click();
    }

    private void selectStatusFilter(String strStatus){
        driver.findElement(By.xpath("//input[@type='checkbox']/following-sibling::span[text()='"+strStatus+"']")).click();
    }

    private void selectOrderBy(String strOrderName){
        //WebElement orderFilter;
        //orderFilter = driver.findElement(btnOrderBy);
        //ction.moveToElement(orderFilter).build().perform();
        driver.findElement(btnOrderBy).click();
        driver.findElement(By.xpath("//ul[@class='srp-sort__menu']/li/descendant::span[text()='"+strOrderName+"']")).click();
    }

    public ResultPage getTop5Results(){
        this.top5Items = driver.findElements(resultTop5Items);
        return this;
    }

    public ResultPage getResults(){
        this.allResults = driver.findElements(resultItems);
        return this;
    }

    public ResultPage filterByBrand(String strBrand){
        this.setBrandFilter(strBrand);
        selectFilteredBrand(strBrand);
        return this;
    }

    public ResultPage filterByStatus(String strStatus){
        this.selectStatusFilter(strStatus);
        return this;
    }

    public ResultPage orderBy(String strOrderName){
        this.selectOrderBy(strOrderName);
        return this;
    }

    public ResultPage printResultsCount(){
        String results = driver.findElement(lblResultsCount).getText();
        System.out.println(results);
        return this;
    }

    private ResultPage printElements(List<WebElement> items){
        if(items.size()>=0){
            for (WebElement item : items){
                System.out.println("Item: "+ extractNameFromResultItemSearch(item) + "  Price: "+ extractPriceFromItemAsString(item));
            }
        } else{
            System.out.println("NO RESULTS");
        }

        return this;
    }

    public ResultPage printTop5Results(){
        getTop5Results();
        printElements(top5Items);
        return this;
    }

    public ResultPage printResults(){
        getResults();
        printElements(allResults);
        return this;
    }

    private void assertAscendantOrderPrice(List<Double> prices){
        List<Double> orderedListPrices = Arrays.asList(new Double[prices.size()]);
        Collections.copy(orderedListPrices, prices);
        Collections.sort(orderedListPrices);

        Assert.assertArrayEquals(orderedListPrices.toArray(), prices.toArray());
    }

    public ResultPage assertTop5AscendantPrice (){
        getTop5Results();
        assertAscendantOrderPrice(extractPriceItemsInArray(top5Items));
        return this;
    }
}
