package uiActionFTI;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccount {

    public static final Logger log = Logger.getLogger(MyAccount.class.getName());

    WebDriver driver;

    public MyAccount(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//h4//span[contains(text(),'Reports')]")
    public WebElement reportsSideNavBar;

    @FindBy(linkText = "Instant")
    public WebElement instantReports;




}
