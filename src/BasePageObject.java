import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject {
    public static WebDriver driver = new FirefoxDriver();

    public void getUrl(String baseUrl) {
        try {
            driver.get(baseUrl);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    public void waitForElements(By element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement elementFinder(By locator) {
        return driver.findElement(locator);
    }





}

