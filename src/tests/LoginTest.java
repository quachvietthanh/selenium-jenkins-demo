import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JenkinsLoginTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private String jenkinsURL = "http://localhost:8080/login";
    private String username = "quachvietthanh"; // 🔴 Thay bằng username của bạn
    private String password = "Tranthanh100";  // 🔴 Thay bằng mật khẩu thật

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver.exe");

        // Thiết lập Chrome với tùy chọn
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--incognito"); 
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Đợi tối đa 10 giây

        // Mở trang Jenkins
        driver.get(jenkinsURL);
        System.out.println("🔗 Mở trang: " + driver.getCurrentUrl());
    }

    @Test
    public void testJenkinsLogin() {
        // 1️⃣ Đợi đến khi ô nhập username xuất hiện
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("j_username")));
        usernameField.sendKeys(username);

        // 2️⃣ Nhập mật khẩu
        WebElement passwordField = driver.findElement(By.name("j_password"));
        passwordField.sendKeys(password);

        // 3️⃣ Nhấn nút "Sign in"
        WebElement loginButton = driver.findElement(By.name("Submit"));
        loginButton.click();
        System.out.println("🔹 Đã nhấn Sign in");

        // 4️⃣ Kiểm tra URL sau đăng nhập
        wait.until(ExpectedConditions.urlContains("/"));
        String currentURL = driver.getCurrentUrl();
        String actualTitle = driver.getTitle();

        // 5️⃣ Kiểm tra đăng nhập thành công
        assertTrue("❌ Đăng nhập thất bại!", currentURL.contains("/") || actualTitle.contains("Dashboard"));
        System.out.println("✔️ Đăng nhập thành công!");
    }

    @After
    public void tearDown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(5000); // ⏳ Chờ 5 giây trước khi đóng trình duyệt
            driver.quit();
            System.out.println("🚀 Đã đóng trình duyệt sau 5 giây.");
        }
    }
}
