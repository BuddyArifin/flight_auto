package pesan_pesawat_labs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

public class PesanPesawat {

  public class Browser {

    public Browser() {
      System.setProperty("webdriver.chrome.driver", "src/main/resources/mac/chromedriver");
    }

    public WebDriver getBrowser() {

      WebDriver driver = new ChromeDriver();
      driver.manage().window().maximize();
      driver.manage().deleteAllCookies();
      return driver;  
    }
  }
  
  public class CariFlight {

    public WebDriver driver;
    public static final String BASE_URL = "https://www.traveloka.com/";

    @FindBy(className = "#04b712bf4c33")
    private WebElement destinations;

    public CariFlight(WebDriver driver) {
      this.driver = driver;
    }

    public void navigatePage() {
      driver.get(BASE_URL);
    }

    public void clickDestinations() {
      destinations.click();
    }
  }
  
  // public static void main(String[] args) {
    
  //   PesanPesawat.Browser browser = new PesanPesawat().new Browser();
  //   WebDriver driver = browser.getBrowser();

  //   PesanPesawat.CariFlight cariFlight = new PesanPesawat().new CariFlight(driver);
  //   cariFlight.navigatePage();
  //   cariFlight.clickDestinations();
  // }
  
  @Test public void runTest() {
    
  }
}