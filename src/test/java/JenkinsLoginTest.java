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
    private String username = "quachvietthanh"; //  Thay bằng username của mik
    private String password = "Tranthanh100";  //  Thay bằng mật khẩu thật

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
        System.out.println(" open page: " + driver.getCurrentUrl());
    }

    @Test
    public void testJenkinsLogin() throws InterruptedException {
        // Đợi đến khi ô nhập username xuất hiện
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("j_username")));

        //  Nhập từng ký tự của username để mô phỏng nhập tay
        for (char ch : username.toCharArray()) {
            usernameField.sendKeys(String.valueOf(ch));
            Thread.sleep(200); //  Chờ 200ms giữa mỗi ký tự
        }

        //  Đợi đến khi ô nhập password xuất hiện
        WebElement passwordField = driver.findElement(By.name("j_password"));

        //  Nhập từng ký tự của password để mô phỏng nhập tay
        for (char ch : password.toCharArray()) {
            passwordField.sendKeys(String.valueOf(ch));
            Thread.sleep(200); //  Chờ 200ms giữa mỗi ký tự
        }

        //  Nhấn nút "Sign in"
        WebElement loginButton = driver.findElement(By.name("Submit"));
        Thread.sleep(500); // Chờ 500ms để nhìn thấy form đã được nhập đầy đủ
        loginButton.click();
        System.out.println(" Đã nhấn Sign in");

        //  Kiểm tra URL sau đăng nhập
        wait.until(ExpectedConditions.urlContains("/"));
        String currentURL = driver.getCurrentUrl();
        String actualTitle = driver.getTitle();

        //  Kiểm tra đăng nhập thành công
        assertTrue(" Đăng nhập thất bại!", currentURL.contains("/") || actualTitle.contains("Dashboard"));
        System.out.println(" Đăng nhập thành công!");
    }

    @After
    public void tearDown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(10000); //  Chờ 10 giây trước khi đóng trình duyệt
            driver.quit();
            System.out.println(" Đã đóng trình duyệt sau 10 giây.");
        }
    }
}
