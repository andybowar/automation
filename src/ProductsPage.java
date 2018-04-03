import org.openqa.selenium.By;

public class ProductsPage extends BasePageObject {
    private static final By AVAILABLE_PRODUCT = By.cssSelector("div.product.unlocked.enabled");
    private static final By STORE_PRODUCT = By.cssSelector("div.crate.upgrade.enabled");

    public void buyProduct() {
        this.elementFinder(AVAILABLE_PRODUCT).click();
    }

    public void buyFromStore() {
        this.elementFinder(STORE_PRODUCT).click();
    }
}
