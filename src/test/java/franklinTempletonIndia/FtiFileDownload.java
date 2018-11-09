package franklinTempletonIndia;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testBase.TestBase;
import uiActionFTI.Dashboard;
import uiActionFTI.InstantReports;
import uiActionFTI.LoginPage;
import uiActionFTI.MyAccount;

import java.io.IOException;
import java.util.Properties;

public class FtiFileDownload extends TestBase{

    public static final Logger log = Logger.getLogger(FtiFileDownload.class.getName());

    @BeforeTest
    public void setup()
    {
        init();
    }

    LoginPage loginPage;
    Dashboard dashboard;
    MyAccount myAccount;

    public String email= "ARN112358";
    public String pass="Fincash@01";
    public String distributerEmail="operations@fincash.com";

    public void loadUserProperty() throws IOException {
        java.io.InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("my.properties");
        java.util.Properties properties = new Properties();
        properties.load(inputStream);
        this.email = properties.getProperty("fti.email");
        this.pass = properties.getProperty("fti.pass");
        this.distributerEmail = properties.getProperty("fti.distributerEmail");
    }

    @Test
    public void downloadFTIFile()
    {
        driver.get("https://www.franklintempletonindia.com");

        try {
            loadUserProperty();
        } catch (IOException e) {
            e.printStackTrace();
        }

         loginPage = new LoginPage(driver);
         loginPage.login(this.email,this.pass);

         dashboard= new Dashboard(driver);
         dashboard.clickOnMyAccount();

         myAccount = new MyAccount(driver);
         myAccount.reportsSideNavBar.click();
         myAccount.instantReports.click();

        InstantReports instantReports = new InstantReports(driver);
        instantReports.setEmailID(this.distributerEmail);

        instantReports.getInstatReport();

        Assert.assertTrue(instantReports.isSuccessful());

        instantReports.logout();






    }

}
