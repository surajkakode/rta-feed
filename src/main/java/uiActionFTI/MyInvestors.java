package uiActionFTI;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyInvestors {

    public static final Logger log = Logger.getLogger(InstantReports.class.getName());

    WebDriver driver;
    String emailID;

    public MyInvestors(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[contains(@class,'loader')]//*[contains(text(),'Please wait')]")
    private WebElement pleaseWait;

    @FindBy(xpath = "//*[@channel-search-options='channelSearchOptions']//*[contains(@class,'dropdown') and @type='button']")
    private WebElement dropdownButton;

    @FindBy(xpath = "//*[@channel-search-options='channelSearchOptions']//*[contains(@class,'dropdown') and @role='menu']//*[contains(text(),'Folio')]")
    private WebElement folioNoOption;

    @FindBy(xpath = "//*[@channel-search-options='channelSearchOptions']//input[@type='text' and contains(@placeholder,'search')]")
    private WebElement searchBox;

    @FindBy(xpath = "//*[@channel-search-options='channelSearchOptions']//button[@type='button' and contains(@ng-click,'investorSearch')]")
    private WebElement submitButton;

    @FindBy(xpath = "//*[@role='rowgroup']//*[@role='row']//*[@popover-trigger]")
    private WebElement popoverPlusButton;

    @FindBy(xpath = "//*[contains(@class,'popover')]//*[contains(text(),'Valuation')]")
    private WebElement valuationStatement;

    @FindBy(xpath = "//*[@mail-back-options='mailbackOptions']//button")
    private WebElement emailButton;

    @FindBy(xpath = "//*[@type = 'button' and contains(text(),'EMAIL TO CLIENT')]")
    private WebElement emailToClient;

    @FindBy(xpath = "//*[contains(text(),'The statement you have requested for is emailed')]")
    private WebElement sentMesage;

    @FindBy(xpath = "//*[contains(@ng-repeat,'toaster in toasters')]//*[@type='button']")
    private WebElement closeToasters;

    @FindBy(xpath = "//*[contains(@ng-repeat,'toaster in toasters')]")
    private WebElement toastersMsg;


    private void invisiblityOfPleaseWait()
    {
        new WebDriverWait(driver,30).until(ExpectedConditions.invisibilityOf(this.pleaseWait));
    }

    private void elementToBeClicable(WebElement webElement)
    {
        new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(webElement));
    }

    private void elementToBeVisible(WebElement webElement)
    {
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(webElement));
    }

    public void selectFolio()
    {
        elementToBeClicable(dropdownButton);
        dropdownButton.click();

        elementToBeVisible(folioNoOption);
        elementToBeClicable(folioNoOption);
        folioNoOption.click();
    }

    public void searchFolio(String folio)
    {
        selectFolio();

        elementToBeClicable(searchBox);
        searchBox.sendKeys(folio);
        submitButton.click();
        invisiblityOfPleaseWait();
    }

    public void clickOnValuationStatement()
    {
        elementToBeClicable(this.popoverPlusButton);
        this.popoverPlusButton.click();

        elementToBeClicable(this.valuationStatement);
        valuationStatement.click();

        invisiblityOfPleaseWait();

    }

    public boolean isToaster()
    {
        try {
            elementToBeVisible(toastersMsg);
            log.info(toastersMsg.getText());
            closeToasters.click();
            return true;
        } catch (Exception e) {
           return false;
        }
    }

    public void sendEmailToClient()
    {
        invisiblityOfPleaseWait();

        elementToBeClicable(this.emailButton);
        emailButton.click();

        elementToBeClicable(this.emailToClient);
        emailToClient.click();

        invisiblityOfPleaseWait();
    }

    public boolean isMailSent()
    {
        invisiblityOfPleaseWait();

        elementToBeVisible(sentMesage);
        String actualMsg = sentMesage.getText().toLowerCase();
        log.info(actualMsg);
        String expectedMsg = "The statement you have requested for is emailed".toLowerCase();
        closeToasters.click();
        if(actualMsg.contains(expectedMsg))
            return true;
        else return false;
    }
}
