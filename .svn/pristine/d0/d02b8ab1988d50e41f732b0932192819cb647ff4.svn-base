package uiActionKarvyMfs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.logging.Logger;

public class AccountStatementDownload {

    public static final Logger log= Logger.getLogger(Dashboard.class.getName());

    WebDriver driver;

    public AccountStatementDownload(WebDriver driver)
    {
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    public String summary = "Summary";
    public String detailed = "Detailed";
    public String financialYear = "Financial Year";
    private String parentWindowHandle;

    @FindBy(xpath = "//input[@name='txtFromDate']")
    private WebElement fromDate;

    @FindBy(xpath = "//input[@name='txtToDate']")
    private WebElement toDate;

    @FindBy(xpath = ".//*[contains(text(),'Scheme Description')]//parent::tr//input")
    private WebElement selectAllScheme;


    @FindBy(xpath = "//input[@type='submit' and @value='View Statement']")
    private WebElement viewStatement;

    @FindBy(xpath = "//*[@class='loader']")
    private WebElement loader;

    @FindBy(xpath = "//*[contains(text(),'CSV')]//parent::span//following-sibling::a//*[@alt='Mail']")
    private WebElement mailIconButton;

    @FindBy(xpath = "//input[@name='txtTo' and @type='text']")
    private WebElement sendMailTo;

    @FindBy(xpath = "//*[@value='Send Mail' and @type='button']")
    private WebElement sendMailButton;

    public void clickOnSelectOption(String option)
    {
        new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='"+option+"']"))).click();
        //driver.findElement(By.xpath("//label[text()='"+option+"']"));
    }

    //optional method, By default the date is selected from inception to today
    public void selectDate(String from, String to)
    {
        this.fromDate.clear();
        this.fromDate.sendKeys(from);

        this.toDate.clear();
        this.toDate.sendKeys(to);
    }

    public void selectAllSchemes()
    {
        selectAllScheme.click();
    }

    public void clickOnViewStatement()
    {
        this.viewStatement.click();
        new WebDriverWait(driver,10).until(ExpectedConditions.invisibilityOf(loader));
    }

    public void sendMail(String userEmail)
    {

        this.parentWindowHandle= driver.getWindowHandle();

        new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(this.mailIconButton)).click();

        Set <String> allWindowHandles = driver.getWindowHandles();

        for(String handle : allWindowHandles)
        {
            System.out.println("Window handle - > " + handle);
            if (!handle.equals(parentWindowHandle))
            {
                driver.switchTo().window(handle);
                try {
                    new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(this.sendMailTo));
                    break;
                } catch (Exception e) {
                    log.info("Going  to next window");
                }

            }
        }

        driver.manage().window().maximize();
        this.sendMailTo.sendKeys(userEmail);
        this.sendMailButton.click();

    }

    public boolean isMailSent()
    {
        try {
            new WebDriverWait(driver,10).until(ExpectedConditions.alertIsPresent()).accept();
            driver.switchTo().window(parentWindowHandle);
            return true;
        } catch (Exception e) {

            driver.switchTo().window(parentWindowHandle);
            return false;
        }


    }

}
