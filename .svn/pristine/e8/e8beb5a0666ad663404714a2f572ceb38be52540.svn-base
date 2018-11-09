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

public class Dashboard {
    public static final Logger log= Logger.getLogger(Dashboard.class.getName());

    WebDriver driver;

    public Dashboard(WebDriver driver)
    {
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }


    @FindBy(xpath = "//a[contains(text(),'CHANGE AMC')]")
    private WebElement changeAMC;

    @FindBy(xpath = "//select[@class='iceSelOneMnu']")
    private WebElement amcDropdown;

    @FindBy(xpath = "//span[contains(text(),'SUNDARAM ASSET MANAGEMENT COMPANY LTD')]")
    private WebElement sundaram;

    @FindBy(xpath = "//span[contains(text(),'BNP PARIBAS ASSET MANAGEMENT INDIA PRIVATE LIMITED')]")
    private WebElement bnpParibas;

    @FindBy(xpath = "//a[contains(text(),'Reports')]")
    private WebElement reports;

    public void waitForLoad()
    {
        new WebDriverWait(driver,10).until(ExpectedConditions.invisibilityOfElementLocated(By.className("dumbBoxOverlay")));
    }

    public void setAmcBNPParibas()
    {
        changeAMC.click();

        Select select = new Select(amcDropdown);
        select.selectByIndex(0);

        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(bnpParibas));
    }

    public void setAmcSundaram()
    {
        changeAMC.click();

        Select select = new Select(amcDropdown);
        select.selectByIndex(1);
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(sundaram));
    }

    public void clickOnReports()
    {
        new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(this.reports)).click();
        waitForLoad();
    }








}
