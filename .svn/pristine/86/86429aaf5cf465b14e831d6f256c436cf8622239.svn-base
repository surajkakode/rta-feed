package uiActionBNPSundaram;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class LoginPage {

    public static final Logger log = Logger.getLogger(LoginPage.class.getName());

    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@title='Enter your User Id' and @type='text']")
    private WebElement loginId;

    @FindBy(xpath = "//input[@title='Enter your Password' and @type='password']")
    private  WebElement password;

    @FindBy(xpath = "//input[@value='Login' and @type='submit']")
    private WebElement submitButton;

    public void login(String email, String password)
    {

        this.loginId.clear();
        this.loginId.sendKeys(email);

        this.password.clear();
        this.password.sendKeys(password);

        submitButton.click();

    }

    public boolean isSucessful()
    {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Sundaram BNP Paribas Fund Services Limited";

        if(actualTitle.equalsIgnoreCase(expectedTitle))
        {
            return true;
        }
        else return false;
    }
}
