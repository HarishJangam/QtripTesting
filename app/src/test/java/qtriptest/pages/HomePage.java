package qtriptest.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

public class HomePage {

    RemoteWebDriver driver;

    @FindBy(id="autocomplete")
    private WebElement searchMyCity;
     
    @FindBy(xpath="//ul[@id='results']/a/li")
    private WebElement selectResult;

    @FindBy(xpath="//ul[@id='results']/h5")
    private WebElement noCityFound;

    public HomePage(RemoteWebDriver driver){
        
        this.driver=driver;
        PageFactory.initElements(driver, this); //new AjaxElementLocatorFactory(driver,15)
    }

    public void clickRegister(){

    }

    public Boolean isUserLoggedIn(){
        return null;        
    }

    public void logOutUser(){

    }

    public void searchCity(String city) throws InterruptedException{

        Thread.sleep(2000);
        searchMyCity.sendKeys(Keys.CONTROL,"a",Keys.DELETE);
        searchMyCity.sendKeys(city);
       

     }

     public void selectCity(String city)throws InterruptedException{

        Thread.sleep(2000);
        if(noCityFound.isDisplayed()){

            System.out.println( "Unable to found the city found : " + noCityFound.getText());
           
        }
        else if( selectResult.getText().equalsIgnoreCase(city)){
            selectResult.click();
            System.out.println("Searched city found and clicked");
        }

     }

}
