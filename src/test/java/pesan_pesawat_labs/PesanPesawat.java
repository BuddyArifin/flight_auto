package pesan_pesawat_labs;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class PesanPesawat {
  private WebDriver driver;

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

    @FindBy(css = "div._1SnbR.mtlc7.tvat-FLIGHT")
    private WebElement tiketPesawat;

    @FindBy(css = "._2vpmC.iY6Z8:nth-child(1) ._2UQTQ > a:nth-child(1)")
    private WebElement tiketPesawatLabel;

    @FindBy(css = "input[placeholder='Destination']")
    private WebElement destinations;

    @FindBy(css = ".MeYMF > div:nth-child(3)")
    private WebElement chooseOptionSurabaya;

    @FindBy(css = "input[value='1 Adult, 0 Child, 0 Infant']")
    private WebElement choosePassanger;

    @FindBy(css = ".mMmI2._2zaav > div:nth-child(1) ._1ffS0:nth-child(3)")
    private WebElement adult;

    @FindBy(css = ".mMmI2._2zaav > div:nth-child(2) ._1ffS0:nth-child(3)")
    private WebElement child;

    @FindBy(css = ".mMmI2._2zaav > div:nth-child(3) ._1ffS0:nth-child(3)")
    private WebElement infant;

    @FindBy(css = ".mMmI2._2zaav > div:nth-child(4) > span")
    private WebElement doneButton;

    @FindBy(css = "button._3-JID._22K0g.gLbQ-._90_75")
    private WebElement searchButton;

    @FindBy(css = ".css-1dbjc4n .css-1dbjc4n.r-18u37iz.r-14gqq1x > div:nth-child(1)")
    private WebElement passengerInfo;

    @FindBy(css = ".css-1dbjc4n .css-1dbjc4n.r-18u37iz.r-14gqq1x > div:nth-child(3)")
    private WebElement seatInfo;


    public CariFlight(WebDriver driver) {
      this.driver = driver;
      PageFactory.initElements(driver, this);
    }

    public void navigatePage() {
      driver.get(BASE_URL);
    }

    public void clickTiketPesawat() {
      tiketPesawat.click();
    }

    public void clickTiketPesawatLabel() {
      tiketPesawatLabel.click();
    }

    public void clickDestinations() {
      waitElement(destinations);
      destinations.click();
    }

    public void chooseOption() {
      waitElement(chooseOptionSurabaya);
      chooseOptionSurabaya.click();
    }

    public void clickPassanger() {
      choosePassanger.click();
    }

    public void setChoosePassanger() {
      adult.click();
      child.click();
      infant.click();
      doneButton.click();
    }

    public void clickSearchButton() {
      waitElement(searchButton);
      searchButton.click();
    }

    public void assertPassengerAndSeat() {
      Assert.assertEquals(passengerInfo.getText(), "2 Adults, 1 Child, 1 Infant");
      Assert.assertEquals(seatInfo.getText(), "Economy");
    }

    public void waitElement(WebElement element) {
      (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(element));
    }
  }
  
  @BeforeClass
  public void setup() {
    PesanPesawat.Browser browser = new PesanPesawat().new Browser();
    this.driver = browser.getBrowser();
    this.driver.manage().window().maximize();
    this.driver.manage().deleteAllCookies();
    this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }
  
  @Test 
  public void runTest() {

    PesanPesawat.CariFlight cariFlight = new PesanPesawat().new CariFlight(this.driver);
    cariFlight.navigatePage();
    cariFlight.clickTiketPesawat();
    cariFlight.clickTiketPesawatLabel();
    cariFlight.clickDestinations();


    /**
     * 1. Choose Surabaya
     * 2. Choose Date
     * 3. Choose Adults
     * 4. Choose Seat
     * */
    cariFlight.chooseOption();
    cariFlight.clickPassanger();
    cariFlight.setChoosePassanger();
    cariFlight.clickSearchButton();
    cariFlight.assertPassengerAndSeat();
  }

  @AfterClass
  public void tearDown() {
    if (this.driver != null) {
      this.driver.quit();
    }
  }
}