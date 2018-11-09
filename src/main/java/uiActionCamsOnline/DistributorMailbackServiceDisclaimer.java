package uiActionCamsOnline;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

public class DistributorMailbackServiceDisclaimer {

    public static final Logger log = Logger.getLogger(DistributorMailbackServiceDisclaimer.class.getName());
    WebDriver driver;


    public DistributorMailbackServiceDisclaimer(WebDriver driver)
    {
        this.driver= driver;
        PageFactory.initElements(driver,this);

    }

    @FindBy(xpath = "//div[@class='gridbg']/child::h3[contains(text(),'Disclaimer')]")
    public WebElement disclaimer;

    @FindBy(xpath = "//input[@id='rdo_accept' and @type='radio']")
    public WebElement acceptButton;

    @FindBy(xpath = "//input[@id='rdo_decline' and @type='radio']")
    public WebElement declineButton;

    @FindBy(xpath = "//input[@id='btn_Proceed' and @type='submit']")
    public WebElement proceedButton;

    public void acceptDisclaimer()
    {
        try{

            new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(disclaimer));
            acceptButton.click();
            proceedButton.click();


        }catch (NoSuchElementException e)
        {
            log.info(e,e);
        }
    }

}