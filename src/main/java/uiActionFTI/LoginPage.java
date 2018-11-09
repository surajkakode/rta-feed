package uiActionFTI;


import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
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

    @FindBy(css = "div[class^='dropdown fti-login']")
    private WebElement loginDropDownButton;

    @FindBy(xpath = "//div[contains(@class,'desktop')]//input[@name='loginName']")
    private WebElement loginId;

    @FindBy(xpath = "//div[contains(@class,'desktop')]//input[@name='loginPwd']")
    private  WebElement password;

    @FindBy(xpath = "//div[contains(@class,'desktop')]//button[contains(text(),'Login')]")
    private WebElement submitButton;



    public void login(String email, String password)
    {
        loginDropDownButton.click();

//        this.loginId.clear();
        this.loginId.sendKeys(email);

 //       this.password.clear();
        this.password.sendKeys(password);

        submitButton.click();

    }

    public boolean isSucessful()
    {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Franklin Templeton India - Invest in Mutual Funds Online - Home";

        if(actualTitle.equalsIgnoreCase(expectedTitle))
        {
            return true;
        }
        else return false;
    }
}
