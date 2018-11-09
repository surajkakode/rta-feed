package uiActionBNPSundaram;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.NoSuchElementException;

public class BrokerReports {

    public static final Logger log= Logger.getLogger(BrokerReports.class.getName());

    WebDriver driver;

    public BrokerReports(WebDriver driver)
    {
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[contains(text(),'Reports')]")
    private WebElement reports;

    @FindBy(xpath = "//div[@class='iceMnuBarVrt']//span[contains(text(),'Broker Reports')]")
    private WebElement brokerReports;

    @FindBy(xpath = "//select[@title='Please select the Report Name']")
    private WebElement reportName;

    @FindBy(xpath = "//input[@title='Please select the From Date']")
    private WebElement fromDate;

    @FindBy(xpath = "//input[@title='Please select the To Date']")
    private WebElement toDate;

    @FindBy(id = "brokerFields1:mailidBroker")
    private WebElement emailId;

    @FindBy(id = "brokerFields1:idPrimaryMail")
    private WebElement emailIdCheckBox;

    @FindBy(xpath = "//input[@id='brokerFields1:idSecondaryMail' and @type='checkbox']")
    private WebElement alternameEmailIdCheckBox;

    @FindBy(xpath = "//select[@title='Please select the File Type']")
    private WebElement fileType;

    @FindBy(xpath = "//input[@id='brokerFields1:j_idt199' and @type='image']")
    private WebElement sendMailButton;

    public void waitForLoad()
    {
        new WebDriverWait(driver,10).until(ExpectedConditions.invisibilityOfElementLocated(By.className("dumbBoxOverlay")));
    }

    private String previousDay;
    private String today;
    public String tranReferenceNumber;

    private void selectDate()
    {
        Calendar cal = Calendar.getInstance();
        DateFormat dd = new SimpleDateFormat("dd/MM/yyyy");

        this.today = dd.format(cal.getTime());

        if(cal.get(Calendar.DAY_OF_WEEK)==2) {
            cal.add(Calendar.DATE, -3);
            this.previousDay = dd.format(cal.getTime());
        }else {
            cal.add(Calendar.DATE, -1);
            this.previousDay = dd.format(cal.getTime());
        }
    }

    public void goToBrokerReports() throws InterruptedException {

        do {
            waitForLoad();
            new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(this.brokerReports)).click();
            waitForLoad();
        }while (isBrokerReportOpened()==false);

    }

    public boolean isRequestSubmitted()
    {
        waitForLoad();
        try {
            String actualText = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='brokerFields1:saveMsgErrorsBrokerReaportsSearch' and contains(text(),'Thank you for submitting your request')]"))).getText();
            String expectedText = "Thank you for submitting your request";

            if (actualText.contains(expectedText))
                return true;
            else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean isBrokerReportOpened()
    {
        try {
            return new WebDriverWait(driver,2).until(ExpectedConditions.elementToBeClickable(this.reportName)).isDisplayed();
        } catch (Exception e) {
            log.info("Not Found");
            return false;
        }
    }
    public void emailReport() throws InterruptedException {


        new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(this.reportName));
        Select select = new Select(this.reportName);
        select.selectByIndex(2);
        waitForLoad();

        selectDate();

        try {
            fromDate.clear();
            fromDate.sendKeys(previousDay);
            waitForLoad();

            toDate.clear();
            toDate.sendKeys(today);
            waitForLoad();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            select.selectByIndex(1);
            select.selectByIndex(2);
            fromDate.sendKeys(previousDay);
            toDate.sendKeys(today);
        }


        try {
            waitForLoad();
            emailIdCheckBox.click(); // this click is for focus out from toDate.
            waitForLoad();
            new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(emailIdCheckBox));
            if (emailIdCheckBox.isSelected()==false)emailIdCheckBox.click();
        } catch (Exception e) {
            e.printStackTrace();
            emailIdCheckBox.click(); // this click is for focus out from toDate.
            new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(emailIdCheckBox));
            if (emailIdCheckBox.isSelected()==false)emailIdCheckBox.click();
        }


        Select file = new Select(fileType);
        file.selectByValue("EXCEL");

        sendMailButton.click();
        //sendMailButton.click();



    }

}
