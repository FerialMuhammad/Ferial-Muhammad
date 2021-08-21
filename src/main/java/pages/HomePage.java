package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import utils.WaitStrategies;

public class HomePage {
    private WebDriver driver;
    private By searchBox = By.name("q");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public SearchResultPage searchWithKeyword(String keyWord){
        WaitStrategies.waitPresentFn(driver,searchBox);
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(keyWord);
        driver.findElement(searchBox).sendKeys(Keys.ENTER);
        return new SearchResultPage(driver);
    }

    public SearchResultPage searchWithLink(String url){
        By searchBtn = By.name("btnK");
        WaitStrategies.waitPresentFn(driver,searchBox);
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(url);
        WaitStrategies.waitPresentFn(driver,searchBtn);
        driver.findElement(searchBtn).submit();
        return new SearchResultPage(driver);
    }

    public String get1stSuggestion(String keyWord){
        WaitStrategies.waitPresentFn(driver,searchBox);
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(keyWord);
        By suggestion = By.xpath("//body[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[2]/div[2]/ul[1]/li[1]/div[1]/div[2]/div[1]/span[1]");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WaitStrategies.waitPresentFn(driver,suggestion);
        return driver.findElement(suggestion).getText();
    }

}
