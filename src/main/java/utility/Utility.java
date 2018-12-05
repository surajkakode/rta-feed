package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {


    WebDriver driver;

    public Utility(WebDriver driver)
    {
        this.driver =driver;
    }

    protected void waitTillElementToBeVisible(WebElement element)
    {
        new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(element));
    }
    protected void waitTillElementToBecliable(WebElement element)
    {
        new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(element));
    }
    protected void waitTillElementToBeInVisible(WebElement element)
    {
        new WebDriverWait(driver,10).until(ExpectedConditions.stalenessOf(element));
    }
    protected void waitTillElementToBeVisibleAndCliable(WebElement element)
    {
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(element));
        new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(element));
    }
}
