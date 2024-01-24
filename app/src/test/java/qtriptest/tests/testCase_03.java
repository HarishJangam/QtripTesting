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

public class testCase_03 {
    static RemoteWebDriver driver;
    RegisterPage registerPage;
    LoginPage loginPage;
    HomePage homePage;
    AdventurePage adventurePage;
    HistoryPage historyPage;
    String lastGenerateduser="";
    String lastUserPassword="";

    public static void logStatus(String type, String message, String status){

        System.out.println(String.format("%s | %s | %s | %s", String.valueOf(java.time.LocalDateTime.now()),type,message,status));
    }
    @Test(priority=3,dataProvider = "data-provider",dataProviderClass = DP.class,description = "Booking and Cancellation Flow",groups = {"Booking and Cancellation Flow"})
    public void TestCase03(String userName,String password,String cityName,String adventureName,String guestName,String date,String count) throws MalformedURLException, InterruptedException{

        logStatus("TestCase03 ", "Started", "done");
        driver=singletonDriver.getIntsance();
        driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
        registerPage=new RegisterPage(driver);
        registerPage.navigateToRegister();
        registerPage.registerNewUser(userName, password, password, true);
        lastGenerateduser=registerPage.lastgeneratedUsername;
        loginPage=new LoginPage(driver);
        loginPage.PerformLogin(lastGenerateduser, password);
        homePage=new HomePage(driver);
        homePage.searchCity(cityName);
        homePage.selectCity(cityName);
        // homePage.addFeatures(category, duration, filter, withoutFilter);
        adventurePage=new AdventurePage(driver);
        System.out.println("into selectAdventures");
        adventurePage.selectAdventure(adventureName, guestName, date, count,true);
        System.out.println("into historypage");
        historyPage=new HistoryPage(driver);
        String str=historyPage.getReservation();
        System.out.println(str);
        historyPage.cancelReservation();
        loginPage.performLogout();
        Thread.sleep(3000);
    }

}
