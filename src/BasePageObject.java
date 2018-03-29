import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject {
    public static WebDriver driver;
    public static WebDriverWait wait;

    public WebElement element(By locator) {
        return driver.findElement(locator);
    }





}

