package uiActionKarvyMfs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

public class ListOfReports {

    public static final Logger log = Logger.getLogger(ListOfReports.class.getName());

    WebDriver driver;

    public ListOfReports(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[contains(text(),'MFSD201 - Transaction Report')]")
    public WebElement MFSD201TransactionReport;


}
