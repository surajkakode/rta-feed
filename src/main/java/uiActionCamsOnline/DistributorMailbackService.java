package uiActionCamsOnline;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DistributorMailbackService {

    public static final Logger log = Logger.getLogger(DistributorMailbackService.class.getName());
    WebDriver driver;


    public DistributorMailbackService(WebDriver driver)
    {
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@id='txtemail' and @type='text']")
    public WebElement emailId;

    @FindBy(id = "ddlamc")
    public WebElement multiSelect;

    @FindBy(id = "btnNext")
    public WebElement nextButton;


    public void distributorMailbackService(String email)
    {
        this.emailId.sendKeys(email);

        Select select = new Select(multiSelect);
        for (int i=2;i<=18;i++)
        select.selectByIndex(i);

        select.deselectByIndex(6);
        select.deselectByIndex(16);

        nextButton.click();
    }

    public void setEmailId(String email)
    {
        this.emailId.sendKeys(email);
    }

    public void setAmc(String amc)
    {
        Select select = new Select(multiSelect);

        List <WebElement> alloption = select.getOptions();
            for (int i=0;i<alloption.size();i++)
            {
                String name = alloption.get(i).getText().toLowerCase();
                if(name.contains(amc.toLowerCase()))
                {
                    alloption.get(i).click();
                    break;
                }
            }

        nextButton.click();

    }

}
