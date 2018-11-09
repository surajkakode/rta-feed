package bnpSundaram;


import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testBase.TestBase;
import uiActionBNPSundaram.BrokerReports;
import uiActionBNPSundaram.Dashboard;
import uiActionBNPSundaram.LoginPage;

import java.io.IOException;
import java.util.Properties;

public class BNPPribasFileDownload extends TestBase {

    public static final Logger log= Logger.getLogger(BNPPribasFileDownload.class.getName());

    @BeforeTest
    public void setup() {
        init();
    }

    LoginPage loginPage;
    Dashboard dashboard;
    BrokerReports brokerReports;

    public String email="ARN-112358";
    public String pass="Fincash@111";

    public void loadUserProperty() throws IOException {
        java.io.InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("my.properties");
        java.util.Properties properties = new Properties();
        properties.load(inputStream);
        this.email = properties.getProperty("bnp.email");
        this.pass = properties.getProperty("bnp.pass");
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

        brokerReports.emailReport();

        Assert.assertTrue(brokerReports.isRequestSubmitted());
        //log.info("The transaction Report Reference No is : "+ transactionReport.tranReferenceNumber);
    }

}
