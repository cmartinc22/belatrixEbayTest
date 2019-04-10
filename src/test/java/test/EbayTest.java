package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import page.EbaySearch;
import page.ResultPage;

import java.util.concurrent.TimeUnit;

public class EbayTest {
    private WebDriver driver;
    private EbaySearch pageEbay;
    private ResultPage pageResults;

    @Before
    public void setup() {
        System.setProperty("webdriver.gecko.driver", "src/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test_bellatrix() {
        //Create Ebay Page object
        pageEbay = new EbaySearch(driver);

        //Navigate to ebay
        pageEbay
                .goToEbay()
                .searchItem("shoes"); //Search for @Shoes

        pageResults = new ResultPage(driver);

        pageResults
                .filterByBrand("PUMA")
                .filterByStatus("Nuevo")
                .printResultsCount()
                .orderBy("Precio + Envío: más bajo primero")
                .assertTop5AscendantPrice()
                .printTop5Results()
                .orderBy("Mejor resultado")
                .printResults()
                .orderBy("Precio + Envío: más alto primero")
                .printResults();
    }
}
