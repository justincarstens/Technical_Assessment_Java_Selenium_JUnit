package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverManager;

import java.time.Duration;
import java.util.List;


public class StorePageTest {

    public static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        driver = WebDriverManager.getDriver();
        driver.get("https://qa-challenge.codesubmit.io/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @Test
    public void testTwitterRedirect() {
        String link = driver.findElement(By.className("social_twitter")).findElement(By.tagName("a")).getAttribute("href");
        Assertions.assertEquals("https://twitter.com/saucelabs", link);
    }

    @Test
    public void testFacebookRedirect() {
        String link = driver.findElement(By.className("social_facebook")).findElement(By.tagName("a")).getAttribute("href");
        Assertions.assertEquals("https://www.facebook.com/saucelabs", link);
    }

    @Test
    public void testLinkedinRedirect() {
        String link = driver.findElement(By.className("social_linkedin")).findElement(By.tagName("a")).getAttribute("href");
        Assertions.assertEquals("https://www.linkedin.com/company/sauce-labs/", link);
    }

    @Test
    public void testAboutRedirect() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        String link = driver.findElement(By.id("about_sidebar_link")).getAttribute("href");
        Assertions.assertEquals("https://saucelabs.com/", link);
    }

    @Test
    public void addAllItemsToCart() {

        List<WebElement> listItems = driver.findElements(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory"));

        for (WebElement webElement : listItems) {
            webElement.click();
        }

        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        List<WebElement> listCart = driver.findElements(By.cssSelector(".cart_item"));

        Assertions.assertEquals(listCart.size(), listItems.size());

    }

    @Test
    public void testItemsInCartAfterRefresh() throws InterruptedException {

        List<WebElement> listItems = driver.findElements(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory"));

        for (WebElement webElement : listItems) {
            webElement.click();
        }

        driver.navigate().refresh();

        /* I was unable to find an Explicit Wait condition relating to the page loading to completion, so I used a good old Thread.sleep.
            The test passes without it but logic says that there should be some kind of wait after refreshing. */
        Thread.sleep(1500);

        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        List<WebElement> listCart = driver.findElements(By.cssSelector(".cart_item"));

        Assertions.assertEquals(listCart.size(), listItems.size());
    }

    @AfterAll
    public static void tearDown() {
        WebDriverManager.closeDriver();
    }

}
