package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class WaitStrategies {
    public static void waitPresentFn(WebDriver driver , By elementCatcher){
        FluentWait wait = new FluentWait(driver);
        wait.withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(elementCatcher));
    }

}
