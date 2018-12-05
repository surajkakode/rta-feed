package uiActionFTI;


import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class LoginPage {

    public static final Logger log = Logger.getLogger(LoginPage.class.getName());

    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[contains(@class,'fti-loginBtn') and contains(@class,'dropdown') and @type='button']")
    private ArrayList <WebElement> loginDropDownButton;

    @FindBy(xpath = "//div[contains(@class,'desktop')]//input[@name='loginName']")
    private WebElement loginId;

    @FindBy(xpath = "//div[contains(@class,'desktop')]//input[@name='loginPwd']")
    private  WebElement password;

    @FindBy(xpath = "//div[contains(@class,'desktop')]//button[contains(text(),'Login')]")
    private WebElement submitButton;

    public void clickOnLoginButton()
    {
            for (int i=0;i<loginDropDownButton.size();i++)
            {
                try {
                    loginDropDownButton.get(i).click();
                } catch (ElementNotInteractableException e) {
                    log.info(e);
                }
            }
    }

    public void login(String email, String password)
    {
        clickOnLoginButton();

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
