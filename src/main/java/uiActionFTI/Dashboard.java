package uiActionFTI;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Dashboard {

    public static final Logger log = Logger.getLogger(Dashboard.class.getName());

    WebDriver driver;

    public Dashboard(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(linkText = "My Account")
    private WebElement myAccount;

    public void clickOnMyAccount() {
        this.myAccount.click();
    }
}
