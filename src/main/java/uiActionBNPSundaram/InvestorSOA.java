package uiActionBNPSundaram;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InvestorSOA {

    public static final Logger log= Logger.getLogger(InvestorSOA.class.getName());

    WebDriver driver;

    public InvestorSOA(WebDriver driver)
    {
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    String [] folio;

    @FindBy(xpath = "//a[contains(text(),'Reports')]")
    private WebElement reports;

    @FindBy(xpath = "//div[@class='iceMnuBarVrt']//span[contains(text(),'Investor')]")
    private WebElement investorSOA;

    @FindBy(xpath = "//*[@type='radio']/following::*[contains(text(),'Multiple(SOA generation)')]")
    private WebElement multipleSOA;

    @FindBy(xpath = "//*[@type='radio']/following::*[contains(text(),'Single Investor Search')]")
    private WebElement singleSOA;

    @FindBy(xpath = "//textarea[@id='searchBrokF:textfolio']")
    private WebElement folioTextBox;

    @FindBy(xpath = "//*[@type='submit' and @value='Search']")
    private WebElement searchFolioButton;

    @FindBy(xpath = "//*[contains(text(),'Statements')]/following::*[@type='image'][1]")
    private WebElement proceed;

    public void waitForLoad()
    {
        new WebDriverWait(driver,10).until(ExpectedConditions.invisibilityOfElementLocated(By.className("dumbBoxOverlay")));
    }

    public void clickOnInvestorSOA()
    {
        waitForLoad();
        new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(this.investorSOA));
        this.investorSOA.click();
    }

    public void clickOnMultipleSOA()
    {
        waitForLoad();
        new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(this.multipleSOA));
        this.multipleSOA.click();
    }

    public void inputMultipleFolio(String[] folio )
    {
        this.folio = folio;
        new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(this.folioTextBox));
        for (String i:folio)
        {
            this.folioTextBox.sendKeys(i);
            this.folioTextBox.sendKeys(Keys.ENTER);
        }

        clickOnSearch();

    }

    private boolean isFolioFound()
    {
        try {
            return new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Folio']/following::*[text()='Message']/following::*[text()='"+folio[0]+"']"))).isDisplayed();
        } catch (Exception e) {
            log.info("Not found");
            return false;
        }
    }

    private void clickOnSearch()
    {
        do {
            this.searchFolioButton.click();
            waitForLoad();
        }while (isFolioFound()==false);
    }

    public final String  PreviousFinancialYear = "Previous Financial Year";
    public final String  CurentAndPreviousFinancialYear = "Current  and Previous Financial Year";
    public final String  CurrentFinancialYear = "Current Financial Year";
    public final String  SinceInception = "Since Inception";
    public final String  CustomDate = "Custom Date";
    public final String  Pdf ="PDF";
    public final String  EmailToInvestor ="Email to Investor";
    public final String  SeparatePdfFilesAndCompressedZipFile= "Separate pdf files and compressed zip file";
    public final String  SinglePdfFile = "Single pdf file";


    public void selectRadioButtons(String timePeriod)
    {
        waitForLoad();
        new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Statements')]/following::*[@type='radio']/following::*[contains(text(),'"+timePeriod+"')]"))).click();
    }

    public void clickOnProceed()
    {
        waitForLoad();
        this.proceed.click();
        waitForLoad();
    }

    public boolean isRequestSubmitted()
    {
        waitForLoad();
        try {
            String actualText = new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Statements')]/following::*[contains(text(),'sent successfully')]"))).getText();
            String expectedText = "sent successfully";

            if (actualText.contains(expectedText))
                return true;
            else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



}
