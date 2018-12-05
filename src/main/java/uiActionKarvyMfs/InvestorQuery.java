package uiActionKarvyMfs;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class InvestorQuery {

    public static final Logger log = Logger.getLogger(InvestorQuery.class.getName());

    WebDriver driver;

    public InvestorQuery (WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[@class='loader']")
    private WebElement loader;

    @FindBy(xpath = "//*[@id='ctl00_MiddleContent_ddlfunds']")
    private WebElement selectFund;

    @FindBy(xpath = "//*[@id='ctl00_MiddleContent_rdAcNo' and @type='radio']")
    private WebElement accountNumberRadioButton;

    @FindBy(xpath = "//*[@id='ctl00_MiddleContent_txtAcNo' and @type='text']")
    private WebElement accountNumberInputBox;

    @FindBy(xpath = "//*[@id='ctl00_MiddleContent_btnQuery' and @type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//*[@id='ctl00_MiddleContent_dgQueryDets']//child::a[contains(@href,'Accountstatement')]")
    private WebElement soa;


    public void setSelectFund(String amc)
    {
        new WebDriverWait(driver,10).until(ExpectedConditions.invisibilityOf(loader));
        new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(selectFund));
        Select select = new Select(selectFund);

        List<WebElement> alloption = select.getOptions();
        for (int i=0;i<alloption.size();i++)
        {
            String name = alloption.get(i).getText().toLowerCase();
            if(name.contains(amc.toLowerCase()))
            {
                alloption.get(i).click();
                break;
            }
        }
    }

    public void setFolio(String[] folio)
    {
        new WebDriverWait(driver,10).until(ExpectedConditions.invisibilityOf(loader));
        accountNumberRadioButton.click();
        accountNumberInputBox.sendKeys(folio[0]);
    }

    public void submit()
    {
        submitButton.click();
        new WebDriverWait(driver,10).until(ExpectedConditions.invisibilityOf(loader));
    }

    public void clickOnSOA()
    {
        new WebDriverWait(driver,10).until(ExpectedConditions.invisibilityOf(loader));
        soa.click();
    }


}
