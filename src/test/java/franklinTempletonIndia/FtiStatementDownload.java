package franklinTempletonIndia;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testBase.TestBase;
import uiActionFTI.Dashboard;
import uiActionFTI.LoginPage;
import uiActionFTI.MyAccount;
import uiActionFTI.MyInvestors;

import java.io.IOException;
import java.util.Properties;

public class FtiStatementDownload extends TestBase {

    public static final Logger log = Logger.getLogger(FtiStatementDownload.class.getName());

    @BeforeTest
    public void setup()
    {
        init();
    }

    LoginPage loginPage;
    Dashboard dashboard;
    MyAccount myAccount;
    MyInvestors myInvestors;

    public String email= "ARN112358";
    public String pass="Fincash@01";
    public String folioNo="";

    public void loadUserProperty() throws IOException {
        java.io.InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("my.properties");
        java.util.Properties properties = new Properties();
        properties.load(inputStream);
        this.email = properties.getProperty("fti.email");
        this.pass = properties.getProperty("fti.pass");
        this.folioNo=properties.getProperty("fti.folio");
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
        //dashboard.clickOnMyAccount();

        myAccount = new MyAccount(driver);
        myAccount.clickOnMyInvestor();

        myInvestors = new MyInvestors(driver);
        myInvestors.searchFolio(this.folioNo);

        myInvestors.clickOnValuationStatement();
        myInvestors.isToaster();
        myInvestors.sendEmailToClient();

        Assert.assertTrue(myInvestors.isMailSent());

        dashboard.logout();
    }


    @AfterTest
    public void closeBrowser()
    {
        driver.close();
    }
}
