package bnpSundaram;


import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import testBase.TestBase;
import uiActionBNPSundaram.BrokerReports;
import uiActionBNPSundaram.Dashboard;
import uiActionBNPSundaram.LoginPage;

import java.io.IOException;

public class BNPPribasFileDownload extends TestBase {

    public static final Logger log= Logger.getLogger(BNPPribasFileDownload.class.getName());

    @BeforeMethod
    public void setup() {
        init();
    }

    LoginPage loginPage;
    Dashboard dashboard;
    BrokerReports brokerReports;

    public String email;
    public String pass;
    public String fromDate;             //format should be dd/mm/yyyy
    public String toDate;               //format should be dd/mm/yyyy

    public void loadUserProperty() throws IOException {

        this.email = System.getProperty("bnp.email");
        this.pass = System.getProperty("bnp.pass");
        this.fromDate=System.getProperty("bnp.fromDate");
        this.toDate=System.getProperty("bnp.toDate");
    }

    @Test
    public void downloadKarvyFile() throws InterruptedException {
        driver.get("https://www.sundarambnpparibasfs.in/distributorservices/?amcid=bnpmf");

        try {
            loadUserProperty();
        } catch (IOException e) {
            e.printStackTrace();
        }

        loginPage = new LoginPage(driver);
        loginPage.login(this.email,this.pass);

        dashboard = new Dashboard(driver);
        dashboard.waitForLoad();
        dashboard.setAmcBNPParibas();
        dashboard.clickOnReports();

        brokerReports = new BrokerReports(driver);
        brokerReports.goToBrokerReports();

        brokerReports.setER02Report();
        if(this.fromDate==null || this.fromDate.equals(null) || this.fromDate.equals("${camsFromDate}") || this.fromDate.equals(" ")) {

            brokerReports.selectAutoDate();
        }
        else {
            brokerReports.inputDate(this.fromDate,this.toDate);  //format should be dd/mm/yyyy
        }
        brokerReports.setEmailIdCheckBox();
        brokerReports.setFileType();
        brokerReports.sendMail();


        Assert.assertTrue(brokerReports.isRequestSubmitted());
        //log.info("The transaction Report Reference No is : "+ transactionReport.tranReferenceNumber);
    }

    @AfterMethod
    public void closeBrowser()
    {
        for (String handle : driver.getWindowHandles()) {
            if (handle != null && handle.trim().length() > 0) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.quit();
    }

}
