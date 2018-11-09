package uiActionKarvyMfs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

public class LoginPage {

    public static final Logger log = Logger.getLogger(LoginPage.class.getName());

    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "txtUserId")
    private WebElement loginId;

    @FindBy(id = "txtPassword")
    private  WebElement password;

    @FindBy(xpath = "//input[@id='btnSubmit' and @type='submit']")
    private WebElement submitButton;

    public void loginToKarvy(String email, String password)
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
        String expectedTitle = "Karvy Mutual Fund Services";

        if(actualTitle.equalsIgnoreCase(expectedTitle))
        {
            return true;
        }
        else return false;
    }
}
