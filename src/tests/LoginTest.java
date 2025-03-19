import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/login?from=%2F"); // Cập nhật URL Jenkins
    }

    @Test
    public void testValidLogin() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        
        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("j_username")));
        WebElement password = driver.findElement(By.name("j_password"));
        WebElement loginButton = driver.findElement(By.name("Submit"));

        username.sendKeys("admin");
        password.sendKeys("admin123");
        loginButton.click();

        // Chờ trang load xong
        wait.until(ExpectedConditions.urlContains("/"));

        // Kiểm tra đăng nhập thành công
        assertTrue(driver.getCurrentUrl().contains("/"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
