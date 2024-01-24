package qtriptest.tests;

import qtriptest.DP;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;


public class testCase_04 {
    static RemoteWebDriver driver;
    RegisterPage registerPage;
    LoginPage loginPage;
    HomePage homePage;
    AdventurePage adventurePage;
    HistoryPage historyPage;
    String lastGenerateduser="";

    @Test(priority=4,dataProvider="data-provider",dataProviderClass=DP.class,description = "adding group of reservations and cheking",groups = {"Reliability Flow"})
    public void TestCase04(String userName,String password,String dataset1,String dataset2,String dataset3) throws MalformedURLException, InterruptedException{

        driver=singletonDriver.getIntsance();
        driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
        registerPage=new RegisterPage(driver);
        registerPage.registerNewUser(userName, password, password, true);
        lastGenerateduser=registerPage.lastgeneratedUsername;
        loginPage=new LoginPage(driver);
        loginPage.PerformLogin(lastGenerateduser, password);
        adventurePage=new AdventurePage(driver);
        // System.out.println("before perform");
        perform(dataset1);
        homePage.navigateToHomePage();
        perform(dataset2);
        homePage.navigateToHomePage();
        perform(dataset3);
    //    homePage.navigateToHomePage();
        historyPage=new HistoryPage(driver);
        historyPage.getReservation();
        loginPage.performLogout();
        
    }

    public void perform(String dataset) throws InterruptedException{
            String [] data=dataset.split(";");
            System.out.println("hi perform");
            homePage=new HomePage(driver);
            homePage.searchCity(data[0]);
            homePage.selectCity(data[0]);
            System.out.println(data[0]+" "+data[1]);
            System.out.println("before selectAdventure");
            adventurePage.selectAdventure(data[1], data[2], data[3], data[4],false);
            System.out.println("after selectAdventure");
    }
}
