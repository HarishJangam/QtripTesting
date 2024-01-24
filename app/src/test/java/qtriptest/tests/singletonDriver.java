package qtriptest.tests;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class singletonDriver {
    static RemoteWebDriver driver=null;
    @BeforeClass(alwaysRun = true)
    public static RemoteWebDriver getIntsance() throws MalformedURLException
    {
        if( driver == null )
        {
           DesiredCapabilities capabilities = new DesiredCapabilities();
           capabilities.setBrowserName(BrowserType.CHROME);
           driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
           System.out.println("Initialising the remote web driver || Success ");
           driver.manage().window().maximize();
        }
        
        return driver;
    }
    // @BeforeClass(alwaysRun = true)
    // public static void createDriver() throws MalformedURLException {
    //     // Launch Browser using Zalenium
    //     final DesiredCapabilities capabilities = new DesiredCapabilities();
    //     capabilities.setBrowserName(BrowserType.CHROME);
    //     driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
    //     driver.manage().window().maximize();
       
    //     System.out.println("driver"+ "initializating Driver..."+"Success");
    // }


    @AfterClass
        public static void closDriver() throws MalformedURLException {
    
            driver.close();
    }
}
