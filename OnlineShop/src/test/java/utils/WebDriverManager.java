package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {

        ChromeOptions options = new ChromeOptions();

            //Add local Chrome for Testing path to the WebDriver
        options.setBinary("J:\\Users\\Justin\\Documents\\JAR_FILES\\Selenium\\chrome-win64\\chrome.exe");
        System.setProperty("webdriver.chrome.driver", "J:\\Users\\Justin\\Documents\\JAR_FILES\\Selenium\\chromedriver-win64\\chromedriver.exe");

        driver = new ChromeDriver(options);
        return driver;

    }

    public static void closeDriver() {
        driver.quit();
    }
}
