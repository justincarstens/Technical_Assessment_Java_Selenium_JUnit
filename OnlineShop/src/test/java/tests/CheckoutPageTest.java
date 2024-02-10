package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WebDriverManager;

import java.util.ArrayList;
import java.util.List;

public class CheckoutPageTest {

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
    public void testCheckOutProcess() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Justin");
        driver.findElement(By.id("last-name")).sendKeys("Carstens");
        driver.findElement(By.id("postal-code")).sendKeys("7550");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();

        WebElement element = driver.findElement(By.className("complete-header"));
        Assertions.assertEquals("Thank you for your order!", element.getText());
    }

    @Test
    public void testCheckOutTotalBalance() {

        List<WebElement> listItems = driver.findElements(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory"));
        List<WebElement> itemPriceComponent = driver.findElements(By.cssSelector(".inventory_item_price"));
        float total = (float) 0;

        for (WebElement webElement : itemPriceComponent) {
            total += Float.parseFloat(webElement.getText().substring(1));
        }

        for (WebElement webElement : listItems) {
            webElement.click();
        }

        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Justin");
        driver.findElement(By.id("last-name")).sendKeys("Carstens");
        driver.findElement(By.id("postal-code")).sendKeys("7550");
        driver.findElement(By.id("continue")).click();

        String component = driver.findElement(By.className("summary_subtotal_label")).getText();
        String checkoutTotal = component.substring(component.indexOf("$") + 1);

        Assertions.assertEquals(String.valueOf(total), checkoutTotal);
    }

    @Test
    public void testFirstNameMandatory() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("last-name")).sendKeys("Carstens");
        driver.findElement(By.id("postal-code")).sendKeys("7550");
        driver.findElement(By.id("continue")).click();

        String error = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();

        Assertions.assertEquals("Error: First Name is required", error);
    }

    @Test
    public void testLastNameMandatory() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Justin");
        driver.findElement(By.id("postal-code")).sendKeys("7550");
        driver.findElement(By.id("continue")).click();

        String error = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();

        Assertions.assertEquals("Error: Last Name is required", error);
    }

    @Test
    public void testPostalCodeMandatory() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Justin");
        driver.findElement(By.id("last-name")).sendKeys("Carstens");
        driver.findElement(By.id("continue")).click();

        String error = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();

        Assertions.assertEquals("Error: Postal Code is required", error);
    }

    @AfterAll
    public static void tearDown() {
        WebDriverManager.closeDriver();
    }
}
