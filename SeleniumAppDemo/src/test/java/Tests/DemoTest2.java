package Tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class DemoTest2 {

    public WebDriver driver;

    @Before
    public void setupDriver(){
        System.setProperty("webdriver.chrome.driver","properties/driver/chromedriver");
        driver = new ChromeDriver();
        String url = "https://www.beymen.com/";
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
    }
    @Test
    public void TestSearch(){

        /* Arama çubuğunda 'Pantolon' ifadesinin aranması */
        WebElement searchBox = driver.findElement(By.xpath("/html/body/header/div/div/div[2]/div/div/div/input"));
        searchBox.click();
        searchBox.sendKeys("Pantolon");
        driver.findElement(By.xpath("/html/body/header/div/div/div[2]/div/button")).click();

        /* Arama sonuç sayfalarında 2. sayfanın açılması ve rastgele bir ürünün açılması */
        driver.findElement(By.id("moreContentButton")).click();
        driver.findElement(By.xpath("/html/body/div[5]/div/div[1]/div[2]/div[22]/div/div/div[1]/a/div[1]/div[2]")).click();

        WebElement price= driver.findElement(By.id("priceNew"));
        String priceText= price.getText();

        /* Açılan ürün sayfasında ürünün sepete eklenmesi*/
        WebElement quantityBox = driver.findElement(By.id("addBasket"));
        quantityBox.click();
        quantityBox.clear();
        quantityBox.sendKeys("1");

        WebElement basketBtn = driver.findElement(By.className("btnAddBasket"));
        basketBtn.click();
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.findElement(By.className("iconBasket")).click();

        /* Ürün sayfasındaki fiyat ile sepetteki fiyatın karşılaştırılması */
        WebElement priceBasket= driver.findElement(By.xpath("/html/body/header/div/div/div[3]/div/a[3]/span"));
        String priceText2= priceBasket.getText();
        if(priceText.compareTo(priceText2)>0){

            /* Sepetteki ürün adetinin artırılması */
            WebElement quantityBasket = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/div[2]/div[1]/div/div/div[1]/div[2]/ul/li[3]/div[2]/div/select/option[2]"));
            quantityBasket.click();
            quantityBasket.clear();
            quantityBasket.sendKeys("1");
        }
        /* Sepetteki ürünlerin boşaltılması */
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.findElement(By.id("removeCartItemBtn0")).click();
    }
    @After
    public void quitDriver(){
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.quit();
    }
}
