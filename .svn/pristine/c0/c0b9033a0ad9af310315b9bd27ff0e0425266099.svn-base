package uiActionCamsOnline;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QueryConfirmation {

    public static final Logger log = Logger.getLogger(QueryConfirmation.class.getName());
    WebDriver driver;


    public QueryConfirmation(WebDriver driver)
    {
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@id='txtqrypass' and @type='password']")
    public WebElement password;

    @FindBy(xpath = "//input[@id='txtqryrepass' and @type='password']")
    public WebElement confirmPassword;

    @FindBy(xpath = "//input[@id='imgbtnImmediate']")
    public WebElement immediate;

    @FindBy(xpath = "//input[@id='imgbtnOvernight']")
    public WebElement overNight;


    public void setPassword(String pass, String confirmPass)
    {
        password.clear();
        password.sendKeys(pass);

        confirmPassword.clear();
        confirmPassword.sendKeys(confirmPass);

        immediate.click();
    }
}
