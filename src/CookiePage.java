import org.openqa.selenium.By;

public class CookiePage extends BasePageObject {

    private final By COOKIE = By.id("bigCookie");
    private final By NUMBER_OF_COOKIES = By.id("cookies");
    private final String BASE_URL = "http://orteil.dashnet.org/cookieclicker/";

    public void goToUrl() {
        this.getUrl(BASE_URL);
    }

    public void clickCookie() {
        this.waitForElements(COOKIE, 20);
        this.elementFinder(COOKIE).click();
    }

    public void getCookies() {
        String numCookies = this.elementFinder(NUMBER_OF_COOKIES).getText();
    }


}