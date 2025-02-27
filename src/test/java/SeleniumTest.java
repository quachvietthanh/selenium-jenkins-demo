import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertEquals;

public class SeleniumTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        try {
            System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver.exe");

            // Kiem tra xem chromedriver co hop le khong
            System.out.println("Kiem tra phien ban ChromeDriver...");
            Process process = Runtime.getRuntime().exec("D:\\selenium\\chromedriver.exe --version");
            process.waitFor(); // Đoi tien trinh ket thuc
            
            driver = new ChromeDriver();
            System.out.println("ChromeDriver khoi dong thanh cong!");
        } catch (Exception e) {
            System.err.println("❌ Loi khi khoi dong ChromeDriver: " + e.getMessage());
            e.printStackTrace();
            System.exit(1); // Thoat chuong trinh neu loi
        }
    }

    @Test
    public void testGoogleTitle() throws InterruptedException {
        if (driver == null) {
            System.err.println("⚠️ Test bi bo qua vi driver chua khoi tao thanh cong.");
            return;
        }

        driver.get("https://www.google.com");

        // Giu trinh duyet mo trong 5 giay de quan sat
        Thread.sleep(5000);

        String title = driver.getTitle();
        System.out.println("Tieu de trang: " + title);
        assertEquals("Google", title);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("✔️ da dong trinh duyet.");
        } else {
            System.err.println("⚠️ Driver da bi null, bo qua viec quit().");
        }
    }
}

