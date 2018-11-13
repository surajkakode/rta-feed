package uiActionFTI;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Dashboard {

    public static final Logger log = Logger.getLogger(Dashboard.class.getName());

    WebDriver driver;

    public Dashboard(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[contains(@class,'loader')]//*[contains(text(),'Please wait')]")
    private WebElement pleaseWait;

    @FindBy(linkText = "My Account")
    private WebElement myAccount;

    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    private WebElement logout;

    @FindBy(xpath = "//button[contains(text(),'Continue to Logout')]")
    private WebElement continueToLogout;

    public void clickOnMyAccount() {
        invisiblityOfPleaseWait();
        elementToBeClicable(this.myAccount);
        this.myAccount.click();
        invisiblityOfPleaseWait();
    }

    private void elementToBeClicable(WebElement webElement)
    {
        new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(webElement));
    }

    private void invisiblityOfPleaseWait()
    {
        new WebDriverWait(driver,30).until(ExpectedConditions.invisibilityOf(this.pleaseWait));
    }

    public void logout()
    {
        logout.click();
        continueToLogout.click();

    }
}
