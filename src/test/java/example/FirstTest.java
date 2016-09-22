package example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Created by Roman Kuziv on 9/20/2016.
 */
public class FirstTest {
    private static final String GECKODRIVER_PATH = "geckodriver.exe";

    private WebDriver driver;

    //@BeforeSuite
    // Runs this method before the first test method in the current class is invoked
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", GECKODRIVER_PATH);
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        driver = new FirefoxDriver(capabilities);
    }

    //@AfterTest
    // Runs this method after all the test methods in the current class have been run
    public void tearDown() {
        // Close all browser windows and safely end the session

    }

    //@Test
    // Marking this method as part of the test
    public void getFacebook() {
        driver.get("https://facebook.com");
        // Find the title
        String title = driver.findElement(By.id("pageTitle")).getText();
        // Verify that title equals "Facebook - Log In or Sign Up"
        Assert.assertEquals(title, "Facebook - Log In or Sign Up");

    }

    //@Test
    public void login_invalid() {
        driver.get("https://facebook.com");
        driver.findElement(By.id("email")).sendKeys("Lili");
        driver.findElement(By.id("pass")).sendKeys("123456789");

        WebElement dynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("loginbutton")));
        driver.findElement(By.id("loginbutton")).click();
        dynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/div[1]/div[2]/div[1]/div/div/div[1]/span")));
        // Find the title
        String title = driver.findElement(By.xpath("html/body/div[1]/div[2]/div[1]/div/div/div[1]/span")).getText();
        //Verify that title equals "Log into Facebook"
        Assert.assertEquals(title, "Log into Facebook");
    }

    //@Test
    public void login_and_out() throws InterruptedException {
        //Get page
        driver.get("https://facebook.com");
        //Email
        driver.findElement(By.id("email")).sendKeys("sviatoslav.kuziv@gmail.com");
        //Password
        driver.findElement(By.id("pass")).sendKeys("qwert12345");
        WebElement dynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("loginbutton")));
        //Login Button
        driver.findElement(By.id("loginbutton")).click();
        dynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("userNavigationLabel")));
        //Panel user
        driver.findElement(By.id("userNavigationLabel")).click();
        dynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("html/body/div[20]/div/div/div/div/div[1]/div/div/ul/li[12]/a/span/span")));
        //Log out
        driver.findElement(By.xpath("html/body/div[20]/div/div/div/div/div[1]/div/div/ul/li[12]/a/span/span")).click();
        Thread.sleep(2000);
        // Find the title
        String title = driver.findElement(By.id("pageTitle")).getText();
        // Verify that title equals "Facebook - Log In or Sign Up"
        Assert.assertEquals(title, "Facebook - Log In or Sign Up");
    }
}
