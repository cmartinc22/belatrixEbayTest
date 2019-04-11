package page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Enums;
import utils.WebElementNameComparator;
import utils.WebElementPriceComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static utils.Utils.extractNameFromResultItemSearch;
import static utils.Utils.extractPriceFromItemAsString;

public class ResultPage {

    private WebDriver driver;
    private List<WebElement> top5Items = new ArrayList<>();
    private List<WebElement> allResults = new ArrayList<>();

    private By txtBrandFilter = By.className("x-searchable-list__textbox__aspect-Brand");
    private By lblResultsCount = By.className("srp-controls__count-heading");
    private By btnOrderBy = By.className("srp-controls__control--legacy");
    private By resultItems = By.className("s-item");
    private By resultTop5Items = By.xpath("//div[@id='srp-river-main']/div/ul/li[position() <= 5]");

    public ResultPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Set brand in Brand's filter
     *
     * @param strBrand Brand's Name
     */
    private void setBrandFilter(String strBrand) {
        driver.findElement(txtBrandFilter).clear();
        driver.findElement(txtBrandFilter).sendKeys(strBrand);
    }

    /**
     * Select a Filtered Brand
     *
     * @param strBrand Brand's Name
     */
    private void selectFilteredBrand(String strBrand) {
        driver.findElement(By.xpath("//ul[@class='x-searchable-list__fieldset x-refine__main__value']/li/descendant::span[text()='" + strBrand + "']/preceding-sibling::input")).click();
    }

    /**
     * Select Status Filter
     *
     * @param strStatus Name of the Status Filter
     */
    private void selectStatusFilter(String strStatus) {
        driver.findElement(By.xpath("//input[@type='checkbox']/following-sibling::span[text()='" + strStatus + "']")).click();
    }

    /**
     * Select an Order By Option
     *
     * @param strOrderName Order By method
     */
    private void selectOrderBy(String strOrderName) {
        driver.findElement(btnOrderBy).click();
        driver.findElement(By.xpath("//ul[@class='srp-sort__menu']/li/descendant::span[text()='" + strOrderName + "']")).click();
    }

    /**
     * Print the information of the search's results items
     *
     * @param items Items to print
     */
    private void printElements(List<WebElement> items) {
        if (items.size() > 0) {
            for (WebElement item : items) {
                System.out.println("Item: " + extractNameFromResultItemSearch(item) + "  Price: " + extractPriceFromItemAsString(item));
            }
        } else {
            System.out.println("NO RESULTS");
        }
    }

    /**
     * Get the first 5 results from search and store in array
     */
    private void getTop5Results() {
        this.top5Items = driver.findElements(resultTop5Items);
    }

    /**
     * Get the first 5 results from search and store iun array
     */
    private void getResults() {
        this.allResults = driver.findElements(resultItems);
    }

    /**
     * Filter the search's results by Brand
     *
     * @param strBrand Brand's Name
     * @return this to chain calls
     */
    public ResultPage filterByBrand(String strBrand) {
        this.setBrandFilter(strBrand);
        selectFilteredBrand(strBrand);
        return this;
    }

    /**
     * Filter the search's results by Product's status
     *
     * @param strStatus Status's Name
     * @return this to chain calls
     */
    public ResultPage filterByStatus(String strStatus) {
        this.selectStatusFilter(strStatus);
        return this;
    }

    /**
     * Order the search's results by Product's status
     *
     * @param strOrderName Order method's Name
     * @return this to chain calls
     */
    public ResultPage orderBy(String strOrderName) {
        this.selectOrderBy(strOrderName);
        return this;
    }

    /**
     * Print The search's results count
     *
     * @return this to chain calls
     */
    public ResultPage printResultsCount() {
        String results = driver.findElement(lblResultsCount).getText();
        System.out.println(results);
        return this;
    }

    /**
     * Print only the first 5 results
     *
     * @return this to chain calls
     */
    public ResultPage printTop5Results() {
        getTop5Results();
        printElements(top5Items);
        return this;
    }

    /**
     * Print all the search's results
     *
     * @return this to chain calls
     */
    public ResultPage printResults() {
        getResults();
        printElements(allResults);
        return this;
    }

    /**
     * Assert the ascendant order of a list of prices
     */
    private void assertAscendantOrderPrice(List<WebElement> items) {
        List<WebElement> orderedListItems = Arrays.asList(new WebElement[items.size()]);
        Collections.copy(orderedListItems, items);
        orderedListItems.sort(new WebElementPriceComparator(Enums.ORDER_BY.ASC.getValue()));
        Assert.assertArrayEquals("The Order of first 5 results aren´t in ascending order", orderedListItems.toArray(), items.toArray());
    }

    /**
     * Assert the order of the first five results in ascendant order
     *
     * @return this to chain calls
     */
    public ResultPage assertTop5AscendantPrice() {
        getTop5Results();
        assertAscendantOrderPrice(top5Items);
        return this;
    }

    public ResultPage printResultByNameAscendant() {
        getTop5Results();
        top5Items.sort(new WebElementNameComparator(Enums.ORDER_BY.ASC.getValue()));
        System.out.println("-------------SORTED BY ASCENDING NAME------------");
        printElements(top5Items);
        return this;
    }

    public ResultPage printResultsByPriceDescendant() {
        getTop5Results();
        top5Items.sort(new WebElementPriceComparator(Enums.ORDER_BY.DESC.getValue()));
        System.out.println("-------------SORTED BY DESCENDING PRICE------------");
        printElements(top5Items);
        return this;
    }
}
