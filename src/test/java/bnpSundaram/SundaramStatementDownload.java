package bnpSundaram;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testBase.TestBase;
import uiActionBNPSundaram.Dashboard;
import uiActionBNPSundaram.InvestorSOA;
import uiActionBNPSundaram.LoginPage;

import java.io.IOException;
import java.util.Properties;

public class SundaramStatementDownload extends TestBase {

    public static final Logger log = Logger.getLogger(SundaramStatementDownload.class.getName());

    @BeforeTest
    public void setup() {
        init();
    }

    LoginPage loginPage;
    Dashboard dashboard;
    InvestorSOA investorSOA;

    public String email="ARN-112358";
    public String pass="Fincash@111";
    public String[] folio;

    public void loadUserProperty() throws IOException {
        java.io.InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("my.properties");
        java.util.Properties properties = new Properties();
        properties.load(inputStream);
        this.email = properties.getProperty("sund.email");
        this.pass = properties.getProperty("sund.pass");
        this.folio= properties.getProperty("sund.folio").split(",");
    }

    @Test
    public void sundaramStatementDownload() throws InterruptedException {
        driver.get("https://www.sundarambnpparibasfs.in/distributorservices/?amcid=bnpmf");

        try {
            loadUserProperty();
        } catch (IOException e) {
            e.printStackTrace();
        }

        loginPage = new LoginPage(driver);
        loginPage.login(this.email,this.pass);

        dashboard = new Dashboard(driver);
        dashboard.setAmcSundaram();
        dashboard.clickOnReports();

        investorSOA = new InvestorSOA(driver);
        investorSOA.clickOnInvestorSOA();
        investorSOA.clickOnMultipleSOA();
        investorSOA.inputMultipleFolio(this.folio);
        investorSOA.selectRadioButtons(investorSOA.SinceInception);
        investorSOA.selectRadioButtons(investorSOA.EmailToInvestor);
        investorSOA.clickOnProceed();

        Assert.assertTrue(investorSOA.isRequestSubmitted());
    }

//    @AfterTest
//    public void close()
//    {
//        driver.close();
//    }
}
