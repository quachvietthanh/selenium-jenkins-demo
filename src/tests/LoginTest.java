import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LoginTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://example.com/login");
    }

    @Test
    public void testValidLogin() {
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login"));

        username.sendKeys("testuser");
        password.sendKeys("password");
        loginButton.click();

        String expectedUrl = "https://example.com/dashboard";
        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

