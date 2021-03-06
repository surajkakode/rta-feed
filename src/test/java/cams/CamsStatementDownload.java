package cams;


import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import testBase.TestBase;
import uiActionCamsOnline.*;

import java.io.IOException;
import java.util.Properties;

public class CamsStatementDownload extends TestBase {

    public static final Logger log = Logger.getLogger(CamsStatementDownload.class.getName());


    @BeforeMethod
    public void setup() {
        init();
    }

    DistributorMailbackServiceDisclaimer distributorMailbackServiceDisclaimer;
    DistributorMailbackService distributorMailbackService;
    ReportSelection reportSelection;
    QueryParameters queryParameters;
    QueryConfirmation queryConfirmation;
    ConfirmationOfRequest confirmationOfRequest;

    public String email;
    public String pass;
    public String[] folio;
    public String amc;
    public String type;

    public void loadUserProperty() throws IOException {

        this.email = System.getProperty("cams.email");
        this.folio = System.getProperty("cams.folio").split(",");
        this.amc = System.getProperty("cams.amc");
        this.pass = System.getProperty("cams.filePass");
        this.type = System.getProperty("cams.type");
    }

    @Test
    public void camsStatementDownload() {
        driver.get("https://www.camsonline.com/DistributorServices/COL_Mailbackservice.aspx");

        try {
            loadUserProperty();
        } catch (IOException e) {
            e.printStackTrace();
        }

        distributorMailbackServiceDisclaimer = new DistributorMailbackServiceDisclaimer(driver);
        distributorMailbackServiceDisclaimer.acceptDisclaimer();

        distributorMailbackService = new DistributorMailbackService(driver);
        distributorMailbackService.setEmailId(this.email);
        distributorMailbackService.setAmc(this.amc);

        reportSelection = new ReportSelection(driver);
        reportSelection.setWBR7Report();
        reportSelection.selectXcelWithHeader();
        if (type == null || type.equalsIgnoreCase("link") || type.equalsIgnoreCase("undefined")) {
            reportSelection.selectEmailADownloadLink();
        } else {
            reportSelection.selectEmailADownloadLink();
        }

        reportSelection.clickOnNextButton();

        queryParameters = new QueryParameters(driver);
        queryParameters.setFolio(this.folio);

        queryConfirmation = new QueryConfirmation(driver);
        queryConfirmation.getQueryConfirmationText();
        queryConfirmation.setPassword(this.pass, this.pass);

        confirmationOfRequest = new ConfirmationOfRequest(driver);
        Assert.assertTrue(confirmationOfRequest.isRequestSubmitted());
        confirmationOfRequest.requestNumber();
    }

    @AfterMethod
    public void closeBrowser() {
        for (String handle : driver.getWindowHandles()) {
            if (handle != null && handle.trim().length() > 0) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.quit();
    }

}
