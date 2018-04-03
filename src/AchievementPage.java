import org.openqa.selenium.By;

public class AchievementPage extends BasePageObject {
    private final By COOKIE_WARNING = By.linkText("Got it!");
    private final By CLOSE_CHEEVO = By.cssSelector("div.close");

    public void removeWarning() {
        this.waitForElements(COOKIE_WARNING, 20);
        this.elementFinder(COOKIE_WARNING).click();
    }

    public void closeCheevos() {
        this.waitForElements(CLOSE_CHEEVO, 20);
        this.elementFinder(CLOSE_CHEEVO).click();
    }
}
