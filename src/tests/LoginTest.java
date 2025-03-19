import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\ChromeDriver\\chromedriver.exe"); // Kiểm tra lại đường dẫn
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/login"); // Đổi sang Jenkins URL
    }

    @Test
    public void testJenkinsLogin() {
        WebElement username = driver.findElement(By.name("j_username"));
        WebElement password = driver.findElement(By.name("j_password"));
        WebElement loginButton = driver.findElement(By.name("Submit"));

        // Kiểm tra auto điền nếu cần
        String autoUsername = username.getAttribute("value");
        String autoPassword = password.getAttribute("value");

        System.out.println("Tài khoản tự động điền: " + autoUsername);
        System.out.println("Mật khẩu tự động điền: " + autoPassword);

        // Kiểm tra nếu trường nhập có giá trị sẵn
        assertTrue("Truong username khong nên trong!", !autoUsername.isEmpty());
        assertTrue("Truong password khong nen trong!", !autoPassword.isEmpty());

        username.sendKeys("admin"); // Thay bằng user thật
        password.sendKeys("yourpassword"); // Thay bằng mật khẩu thật
        loginButton.click();

        // Kiểm tra đăng nhập thành công
        assertEquals("http://localhost:8080/", driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
