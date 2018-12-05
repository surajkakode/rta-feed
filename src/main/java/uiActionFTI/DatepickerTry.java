package uiActionFTI;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class DatepickerTry {
    public static final Logger log = Logger.getLogger(DatepickerTry.class.getName());

    WebDriver driver;

    public DatepickerTry(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    ///////////////////////datepicker/////////////////////////////////////
    @FindBy(xpath = "//input[@type='text' and @uib-datepicker-popup='dd/MM/yyyy']/parent::*//child::button")
    private ArrayList<WebElement> dpButtons;

    @FindBy(xpath = "//*[contains(@id,'datepicker') and contains(@id,'title')]")
    private WebElement dpMonth;

    @FindBy(xpath = "//*[contains(@id,'datepicker') and contains(@id,'title')]")
    private WebElement dpDay;


    public void selectDateFromDatePicker()
    {
        dpButtons.get(1).click();
        log.info(dpMonth.getText());
        String text = dpMonth.getText();
        //identifyMonth(text);
    }

//    private void identifyMonth(String text)
//    {
//        if(text.contains(this.monthName) && text.contains(this.year))
//        {
//
//        }
//    }
}
