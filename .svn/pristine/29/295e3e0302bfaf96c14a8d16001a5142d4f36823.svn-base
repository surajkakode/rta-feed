package uiActionBNPSundaram;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DownloadPage {

    public static final Logger log = Logger.getLogger(DownloadPage.class.getName());

    WebDriver driver;

    public DownloadPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

    @FindBy(id="lgin:pass")
    private WebElement password;

    @FindBy(id = "lgin:j_idt20")
    private WebElement submit;

    public void downloadFile(String pass)
    {
        new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(password));
        password.sendKeys(pass);

        submit.click();

    }
}
