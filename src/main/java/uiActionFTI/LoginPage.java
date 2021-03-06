package uiActionFTI;


import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class LoginPage {

    public static final Logger log = Logger.getLogger(LoginPage.class.getName());

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(@class,'fti-loginBtn') and contains(@class,'dropdown') and @type='button']")
    private List<WebElement> loginDropDownButton;

    @FindBy(xpath = "//div[contains(@class,'desktop')]//input[@name='loginName']")
    private WebElement loginId;

    @FindBy(xpath = "//div[contains(@class,'desktop')]//input[@name='loginPwd']")
    private WebElement password;

    @FindBy(xpath = "//div[contains(@class,'desktop')]//button[contains(text(),'Login')]")
    private WebElement submitButton;

    @FindBy(xpath = "//form//*[contains(@class,'login-form-error') and contains(text(),'Incorrect username')]")
    private WebElement errorElem;

    @FindBy(xpath = "//ftic-custom-loader//*[contains(text(),'Please wait')]")
    public WebElement pleaseWait;

    public void clickOnLoginButton() {
        for (int i = 0; i < loginDropDownButton.size(); i++) {
            try {
                if (loginDropDownButton.get(i).isDisplayed()) {
                    loginDropDownButton.get(i).click();
                }

            } catch (ElementNotInteractableException e) {
                log.error(e);
            }
        }
    }

    public void login(String email, String password) {
        clickOnLoginButton();

//        this.loginId.clear();
        this.loginId.sendKeys(email);

        //       this.password.clear();
        this.password.sendKeys(password);

        submitButton.click();

        isLoginSucessful();


    }

    public void isLoginSucessful() {
        try {
            waitTillInvisiblityOfPleaseWait();
            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(errorElem));
            Assert.fail("Incorrect username and password combination or username has been locked. or Old session is still running. Kindly try after 20 min");
        } catch (Exception e) {

        }
    }
    private void waitTillInvisiblityOfPleaseWait() {
        try {
            new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOf(this.pleaseWait));
        } catch (Exception e) {
            log.error("Please wait not invisible. Ignoring it.");
        }
    }
    public boolean isSucessful() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Franklin Templeton India - Invest in Mutual Funds Online - Home";

        if (actualTitle.equalsIgnoreCase(expectedTitle)) {
            return true;
        } else return false;
    }
}
