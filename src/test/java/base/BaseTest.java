package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pages.HomePage;

public class BaseTest {
    private WebDriver driver;
    protected HomePage homePage;
    protected String url = "https://www.google.com/ncr";

    @Parameters({"browser"})
    @BeforeClass
    public void setUp(String param){
        if(param.equals("Chrome")){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--lang=en");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        }
        if(param.equals("Firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        if(param.equals("Edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }


    }
    @BeforeMethod
    public void openHomePage(){
        driver.get(url);
        homePage = new HomePage(driver);
    }


}
