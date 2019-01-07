package uiActionBSEsettlementCalender;

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

    public DownloadPage (WebDriver driver)
    {
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[@id='btnText' or @name='btnText']")
    private WebElement exportToTextButton;


    public void clickOnExportToTextButton()
    {
        new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(this.exportToTextButton));
        this.exportToTextButton.click();
    }
}
