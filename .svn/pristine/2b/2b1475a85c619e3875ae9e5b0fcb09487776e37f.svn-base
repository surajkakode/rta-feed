package karvy;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testBase.TestBase;
import uiActionKarvyMfs.AccountStatementDownload;
import uiActionKarvyMfs.Dashboard;
import uiActionKarvyMfs.InvestorQuery;
import uiActionKarvyMfs.LoginPage;

import java.io.IOException;
import java.util.Properties;

public class KarvyStatementDownload extends TestBase {

    public static final Logger log = Logger.getLogger(KarvyFileDownload.class.getName());

    @BeforeTest
    public void setup() {
        init();
    }

    LoginPage loginPage;
    Dashboard dashboard;
    InvestorQuery investorQuery;
    AccountStatementDownload asd;


    public String email= "FINCASH";
    public String pass="Fincash@01";
    public String filePass="Fincash@01";
    public String[] folio;
    public String amc = "aditya birla";
    public String userEmail="suraj.kakode@fincash.com";

    public void loadUserProperty() throws IOException {
        java.io.InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("my.properties");
        java.util.Properties properties = new Properties();
        properties.load(inputStream);
        this.email = properties.getProperty("karvy.email");
        this.pass = properties.getProperty("karvy.pass");
        this.filePass = properties.getProperty("karvy.filePass");
        this.folio = properties.getProperty("karvy.folio").split(",");
        this.amc= properties.getProperty("karvy.amc");
        this.userEmail= properties.getProperty("karvy.userEmail");
    }

    @Test
    public void downloadKarvyFile() {
        driver.get("https://www.karvymfs.com/karvy/Distributor/Distributor_Login.aspx");

        try {
            loadUserProperty();
        } catch (IOException e) {
            e.printStackTrace();
        }

        loginPage = new LoginPage(driver);
        loginPage.loginToKarvy(this.email,this.pass);

        dashboard = new Dashboard(driver);
        dashboard.queries.click();

        investorQuery= new InvestorQuery(driver);
        investorQuery.setSelectFund(this.amc);
        investorQuery.setFolio(this.folio);
        investorQuery.submit();
        investorQuery.clickOnSOA();

        asd = new AccountStatementDownload(driver);
        asd.clickOnSelectOption(asd.detailed);
        asd.selectAllSchemes();
        asd.clickOnViewStatement();
        asd.sendMail(this.userEmail);
        Assert.assertTrue(asd.isMailSent());

    }

//    @AfterTest
//    public void close()
//    {
//        driver.close();
//    }

}
