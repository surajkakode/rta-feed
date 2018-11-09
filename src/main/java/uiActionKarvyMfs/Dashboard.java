package uiActionKarvyMfs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

public class Dashboard {

    public static final Logger log= Logger.getLogger(Dashboard.class.getName());

    WebDriver driver;

    public Dashboard(WebDriver driver)
    {
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[@title='Mail Back Reports' and @target='_parent' and contains(text(),'Mail Back Reports')]")
    public WebElement mailBackReports;

    @FindBy(xpath = "//a[@title='Query AccountStatement' and @target='_parent' and contains(text(),'Queries')]")
    public WebElement queries;


}