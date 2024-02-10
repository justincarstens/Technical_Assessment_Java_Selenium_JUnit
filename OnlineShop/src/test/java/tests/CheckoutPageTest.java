package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import utils.WebDriverManager;

public class CheckoutPageTest {

    public static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        driver = WebDriverManager.getDriver();
    }

    @AfterAll
    public static void tearDown() {
        WebDriverManager.closeDriver();
    }
}
