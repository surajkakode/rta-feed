package karvy;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testBase.TestBase;
import uiActionConsolidatedAccountStatement.ConsolidatedAccountStatement;

import java.io.IOException;
import java.util.Properties;

public class ConsolidatedAccStatement extends TestBase {

    public static final Logger log = Logger.getLogger(KarvyFileDownload.class.getName());
    ConsolidatedAccountStatement accountStatement;
    @BeforeTest
    public void setup() {
        init();
    }


    public String email= "surajkakode@gmail.com";
    public String pan="cgcpk5110m";
    public String filePass="Fincash@01";
    public String statementType= "summary";
    public String toDate="";                         //  use 10/10/2018 as a string format
    public String fromDate="";                       //  use 10/10/2018 as a string format

    public void loadUserProperty() throws IOException {
        java.io.InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("my.properties");
        java.util.Properties properties = new Properties();
        properties.load(inputStream);
        this.email = properties.getProperty("karvy.email");
        this.pan = properties.getProperty("karvy.pan").toUpperCase();
        this.filePass = properties.getProperty("karvy.filePass");
        this.statementType = properties.getProperty("karvy.statementType");
        this.toDate = properties.getProperty("karvy.toDate");
        this.fromDate = properties.getProperty("karvy.fromDate");
    }

    @Test
    public void downloadKarvyFile() {
        driver.get("https://www.karvymfs.com/platformservice/");

        try {
            loadUserProperty();
        } catch (IOException e) {
            e.printStackTrace();
        }

         accountStatement = new ConsolidatedAccountStatement(driver);


         if (statementType.contains("specific"))
         {
            accountStatement.selectSpecifiedPeriod(this.fromDate,this.toDate);
         }
         else {
             accountStatement.selectStatementType(statementType);
         }
         accountStatement.requestToConsolidatedStatement(email,pan,filePass);

         accountStatement.submitForm();


        Assert.assertTrue(accountStatement.isRequestSubmitted());
    }

    @AfterTest
    public void closeBrowser()
    {
        driver.close();
    }

}
