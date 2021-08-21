package pages;

import org.openqa.selenium.*;
import utils.WaitStrategies;

import java.util.List;

public class SearchResultPage {
    private WebDriver driver;
    By searchBox = By.name("q");
    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getResults(String keyWord){
        By searchResults;
        try {
         searchResults = By.xpath("//h3[contains(text(),'" + keyWord + "')]");
        WaitStrategies.waitPresentFn(driver,searchResults);}
        catch (TimeoutException e){
            searchResults = By.xpath("//span[contains(text(),'" + keyWord + "')]");
            WaitStrategies.waitPresentFn(driver,searchResults);
        }
        return driver.findElements(searchResults);
    }

    public String openResult(String keyWord){
        By resultLink = By.partialLinkText(keyWord);
        WaitStrategies.waitPresentFn(driver,resultLink);
        driver.findElement(resultLink).click();
        driver.navigate().back();

        WaitStrategies.waitPresentFn(driver,searchBox);
        return driver.findElement(searchBox).getAttribute("value");
    }

    public String clearSearchbox(){
        By clearButton = By.xpath("//body/div[@id='searchform']/div[2]/form[1]/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/span[1]");
        WaitStrategies.waitPresentFn(driver,clearButton);
        driver.findElement(clearButton).click();
        By searchValue = By.xpath("//body/div[@id='searchform']/div[2]/form[1]/div[1]/div[1]/div[2]/div[1]/div[2]/input[1]");
        WaitStrategies.waitPresentFn(driver,searchValue);
        return driver.findElement(searchValue).getText();
    }

    public String getNumberOfResults(){
        By resultStats = By.xpath("//div[@id='result-stats']");
        WaitStrategies.waitPresentFn(driver,resultStats);
        return driver.findElement(resultStats).getText();
    }

    public String autoCorrectSearchResult(){
        By autoCorrected = By.tagName("i");
        WaitStrategies.waitPresentFn(driver,autoCorrected);
        String completeText = "";
        for (WebElement e:driver.findElements(autoCorrected)){
            completeText += e.getText();}
        return completeText;
    }

    public boolean checkPresenceOfLink(String url){
        By link = By.xpath("//a[@href='"+url+"']");
        try {
            WaitStrategies.waitPresentFn(driver,link);
            driver.findElement(link);
            return true;
        }
        catch (ElementNotInteractableException e){
        return false;
        }
    }
    public String getUnmatchedResult(){
        By unmatchedResult = By.tagName("p");
        WaitStrategies.waitPresentFn(driver,unmatchedResult);
        return driver.findElement(unmatchedResult).getText();
    }

}
