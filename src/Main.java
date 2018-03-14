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
        String numCookies1 = "100 cookies";
        String numCookies2 = "115 cookies";
        String numCookies3 = "133 cookies";
        String numCookies4 = "153 cookies";
        String cursor = "product0";
        String grandma = "product1";


        driver.get(baseURL);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(cookie)));

        while (x < 500) {
            driver.findElement(By.id(cookie)).click();

            if ((driver.getPageSource().contains(numCookies1) ||
                    driver.getPageSource().contains(numCookies2) ||
                    driver.getPageSource().contains(numCookies3) ||
                    driver.getPageSource().contains(numCookies4)) && driver.getPageSource().contains(productClass)) {

                driver.findElement(By.id(grandma)).click();

            } else if(driver.getPageSource().contains(productClass)) {

                driver.findElement(By.id(cursor)).click();

            }


            x++;
        }

        driver.close();

    }
}