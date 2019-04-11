package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import page.EbaySearch;
import page.ResultPage;
import utils.Enums;

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
    public void test_in_spanish() {
        //Create Ebay Page object
        pageEbay = new EbaySearch(driver);

        //Navigate to ebay
        pageEbay
                .goToEbay()
                .setLanguage(Enums.LANGUAGE.SPANISH.getName())
                .searchItem("shoes"); //Search for @Shoes

        pageResults = new ResultPage(driver);

        pageResults
                .filterByBrand("PUMA")
                .filterByStatus(Enums.STATUS.NEW_WITH_TAGS.getSpanish())
                .printResultsCount()
                .orderBy(Enums.SORT.PRICE_ASC.getSpanish())
                .assertTop5AscendantPrice()
                .printTop5Results()

                /*
                Al ejercicio tome dos formas de interpretarlo
                1) Primero se ordene a traves de la web los resultados y luego imprimir los resultados
                2) Ya tomados los 5 elementos al ordernar por precio ascendente en la web, utilizando estos mismos
                para ordenarlos e imprimirlos en consola en ordenes de precio y nombre

                Este Test toma la segunda interpretacion (ejecucion con el sitio en Español)
                El siquiente test toma la primer interpretación (ejecución con el sitio en Ingles)

                .orderBy("Mejor resultado")
                .printResults()
                .orderBy("Precio + Envío: más alto primero")
                .printResults()
                */
                .printResultByNameAscendant()
                .printResultsByPriceDescendant();
    }

    @Test
    public void test_in_english() {
        //Create Ebay Page object
        pageEbay = new EbaySearch(driver);

        //Navigate to ebay
        pageEbay
                .goToEbay()
                .setLanguage(Enums.LANGUAGE.ENGLISH.getName())
                .searchItem("shoes"); //Search for @Shoes

        //filterBy Brand can be chain to searchItem, since searchItem return a ResultPage instance
        pageResults = new ResultPage(driver);

        pageResults
                .filterByBrand("PUMA")
                .filterByStatus(Enums.STATUS.NEW_WITH_TAGS.getEnglish())
                .printResultsCount()
                .orderBy(Enums.SORT.PRICE_ASC.getEnglish())
                .assertTop5AscendantPrice()
                .printTop5Results()
                .orderBy(Enums.SORT.BEST_MATCH.getEnglish())
                .printResults()
                .orderBy(Enums.SORT.PRICE_DESC.getEnglish())
                .printResults();
    }
}
