package uiActionKarvyMfs;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TransactionReport {

    public static final Logger log = Logger.getLogger(TransactionReport.class.getName());
    WebDriver driver;


    public TransactionReport(WebDriver driver)
    {
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@id='ctl00_MiddleContent_fromdatetodate1_txtDate' and @type='text']")
    private WebElement fromDate;

    @FindBy(xpath = "//input[@id='ctl00_MiddleContent_fromdatetodate1_txtToDt' and @type='text']")
    private WebElement toDate;

    @FindBy(xpath = "//table[@id='ctl00_MiddleContent_FundsSchemes1_selFunds']/tbody/tr[1]/td[1]/input")
    private WebElement allFunds;

    @FindBy(xpath = "//input[@id='ctl00_MiddleContent_rdWithLoad' and @type='radio']")
    private WebElement loadOptionWithSplitRadioButton;

    @FindBy(xpath = "//input[@id='chkmailid0' and @type='checkbox']")
    private WebElement emailIdCheckbox;

    @FindBy(xpath = "//input[@id='ctl00_MiddleContent_rdXls' and @type='radio']")
    private WebElement formatExcel;

    @FindBy(xpath = "//input[@id='ctl00_MiddleContent_rdencrypt' and @type='radio']")
    private WebElement compressionTypeZip;

    @FindBy(xpath = "//input[@id='ctl00_MiddleContent_ZipPassword1_txtZipPwd' and @type='password']")
    private WebElement selfExtractionPassword;

    @FindBy(xpath = "//input[@id='ctl00_MiddleContent_ZipPassword1_txtconfirmzippwd' and @type='password']")
    private WebElement selfExtractionConfirmPassword;

    @FindBy(xpath = "//input[@id='ctl00_MiddleContent_BtnReq' and @type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//p[@align='justify']/child::b[2]")
    private WebElement referenceNumber;

    private String previousDay;
    private String today;
    public String tranReferenceNumber;

    public void selectDate() {
        Calendar cal = Calendar.getInstance();
        DateFormat dd = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat time = new SimpleDateFormat("kk");
        this.today = dd.format(cal.getTime());

        if (Integer.parseInt(time.format(cal.getTime())) < 15) {

            if (cal.get(Calendar.DAY_OF_WEEK) == 2) {
                cal.add(Calendar.DATE, -3);
                this.previousDay = dd.format(cal.getTime());
            } else {
                cal.add(Calendar.DATE, -1);
                this.previousDay = dd.format(cal.getTime());
            }
        }else {
            this.previousDay = dd.format(cal.getTime());
        }

    }

    public void fillForm(String filePass)
    {
       selectDate();

       fromDate.clear();
       fromDate.sendKeys(previousDay);

       toDate.clear();
       toDate.sendKeys(today);

       allFunds.click();

       loadOptionWithSplitRadioButton.click();

       emailIdCheckbox.click();

       formatExcel.click();

       compressionTypeZip.click();

       selfExtractionPassword.clear();
       selfExtractionPassword.sendKeys(filePass);

       selfExtractionConfirmPassword.clear();
       selfExtractionConfirmPassword.sendKeys(filePass);

       submitButton.click();

       this.tranReferenceNumber= referenceNumber.getText();

    }


    public boolean isRequestSubmitted()
    {
        try {
            String expectedText = "Transaction Report";
            String actualText = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='subheading']//td[contains(text(),'Transaction Report')]"))).getText();

            if (actualText.contains(expectedText))
                return true;
            else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
