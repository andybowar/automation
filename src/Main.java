import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();


        int x = 0;
        String baseURL = "http://orteil.dashnet.org/cookieclicker/";
        String cookie = "bigCookie";
        String productClass = "product unlocked enabled";
        String cookiesOwned = "owned: 5";
        String cursor = "product0";
        String grandma = "product1";

        driver.get(baseURL);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(cookie)));

        while (x < 999999999) {
            driver.findElement(By.id(cookie)).click();


            if (driver.getPageSource().contains(cookiesOwned)) {

                driver.findElement(By.id(grandma) && By.className(productClass)).click();

            } else {

                driver.findElement(By.id(cursor)).click();

            }




            x++;
        }

        driver.close();
    }
}