package uiActionCamsOnline;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationOfRequest {

    public static final Logger log = Logger.getLogger(ConfirmationOfRequest.class.getName());

    WebDriver driver;

    public ConfirmationOfRequest(WebDriver driver)
    {
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    public boolean isRequestSubmitted()
    {
        try {
            String actualText = driver.findElement(By.xpath("//div[@id='divDisMailSend']//div//h3[contains(text(),'Confirmation of Request')]")).getText();
            String expectedText = "Confirmation of Request";

            if (actualText.contains(expectedText))
                return true;
            else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
