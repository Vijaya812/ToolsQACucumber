package seleniumTests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class E2E_Test {
    public static WebDriver driver;
    static String url = "https://shop.demoqa.com/";

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +
                "\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    //    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(url);

        driver.findElement(By.cssSelector(".noo-search")).click();
        WebElement name = driver.findElement(By.name("s"));
        name.sendKeys("dress");
        name.sendKeys(Keys.ENTER);
        List<WebElement> lisOfDresses = driver.findElements(By.cssSelector("div h3"));
        lisOfDresses.get(0).click();
        //color
        WebElement dropDownForColor = driver.findElement(By.id("pa_color"));
        Select select1 = new Select(dropDownForColor);
        select1.selectByValue("white");
        //size
        WebElement dropDownForSize = driver.findElement(By.id("pa_size"));
        Select select2 = new Select(dropDownForSize);
        select2.selectByVisibleText("Medium");
        //for 3 dresses click 2 times
        for (int i = 0; i < 2; i++) {
            driver.findElement(By.xpath("//i[@class='icon_plus']")).click();
        }
        //add to cart
        driver.findElement(By.cssSelector(".single_add_to_cart_button")).click();
        //click cart
        driver.findElement(By.cssSelector(".cart-name-and-total")).click();
        //proceed to checkout
        driver.findElement(By.cssSelector(".checkout-button.button.alt.wc-forward")).click();
        //Billing details
        driver.findElement(By.id("billing_first_name")).sendKeys("Sam");
        driver.findElement(By.id("billing_last_name")).sendKeys("Daniel");
        //country
        WebElement country = driver.findElement(By.id("billing_country"));
        Select cntryselect = new Select(country);
        cntryselect.selectByVisibleText("India");
        //street add
        driver.findElement(By.id("billing_address_1")).sendKeys("lansen St");
        driver.findElement(By.id("billing_city")).sendKeys("Hyderabad");
        WebElement state = driver.findElement(By.id("billing_state"));
        Select stateSelect = new Select(state);
        stateSelect.selectByVisibleText("Telangana");
        //zipcode
        driver.findElement(By.id("billing_postcode")).sendKeys("500002");
        driver.findElement(By.id("billing_phone")).sendKeys("9855003456");
        driver.findElement(By.id("billing_email")).sendKeys("abc@gmail.com");
        //terms
        WebElement terms = driver.findElement(By.id("terms"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", terms);
        //place_order
        driver.findElement(By.id("place_order")).click();
String expMessg="Thank you. Your order has been received.";
String actMessg=driver.findElement(By.xpath("//div/p[text()='Thank you. Your order has been received.']")).getText();
   if(actMessg.equalsIgnoreCase(expMessg)){
       System.out.println("E2E test scenario passed");
   }
   else
       System.out.println("E2E test scenario failed");
    }
}
