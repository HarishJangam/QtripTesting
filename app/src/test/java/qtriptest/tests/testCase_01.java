package qtriptest.tests;

import qtriptest.DP;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class testCase_01 {

        static RemoteWebDriver driver;
        RegisterPage registerPage;
        LoginPage loginPage;
        HomePage homePage;
        String lastGenerateduser="";
        String lastUserPassword="";
        

        public static void logStatus(String type, String message, String status){

                System.out.println(String.format("%s | %s | %s | %s", String.valueOf(java.time.LocalDateTime.now()),type,message,status));
        }
        

        @Test(priority=1,dataProvider = "data-provider",dataProviderClass = DP.class,description = "this registration login verifiying here",groups = {"Login Flow"})
	public void TestCase01(String userName,String password) throws MalformedURLException, InterruptedException {
                // System.out.println("driver "+ driver);
                driver=singletonDriver.getIntsance();
                driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
                registerPage= new RegisterPage(driver);
                registerPage.navigateToRegister();
                logStatus("registration",userName,password);
                registerPage.registerNewUser(userName, password, password, true);
                lastGenerateduser= registerPage.lastgeneratedUsername;
                lastUserPassword=registerPage.lastUserPassword;
                System.out.println("Registraion done successfully..");
                logStatus("Login",userName,password);
                loginPage= new LoginPage(driver);
                loginPage.PerformLogin(lastGenerateduser,password);
                System.out.println("Login done successfully..");
                loginPage.performLogout();
       }


//        @Test(priority=2)
// 	public void verifyLogin() throws MalformedURLException, InterruptedException {
//         lastGenerateduser= registerPage.lastgeneratedUsername;
//         lastUserPassword=registerPage.lastUserPassword;
//         loginPage= new LoginPage(driver);
//         loginPage.PerformLogin(lastGenerateduser,lastUserPassword);
// 	System.out.println("Login done successfully..");
//         }


        // @Test(priority=1,enabled=true)
        // public void verifySearchAndFilters() throws InterruptedException{
        //  driver.get("https://qtripdynamic-qa-frontend.vercel.app/");   
        //  homePage= new HomePage(driver);
        //  homePage.searchCity("XYZ");
        //  homePage.selectCity("XYZ");
        //  homePage.searchCity("GOA");
        //  homePage.selectCity("GOA");

        // }




    
//         @AfterClass
//         public static void closDriver() throws MalformedURLException {
    
//             driver.close();
//             logStatus("driver", "closing Driver...", "Success");
//     }
    

       
}