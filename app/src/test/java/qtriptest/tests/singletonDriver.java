package qtriptest.tests;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class singletonDriver {
    static RemoteWebDriver driver;
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
}
