import org.openqa.selenium.By;

public class ProductsPage extends BasePageObject {
    private static final By AVAILABLE_PRODUCT = By.cssSelector("div.product.unlocked.enabled");
    private static final By STORE_PRODUCT = By.cssSelector("div.crate.upgrade.enabled");

    public void buyProduct() {
        try {
            this.elementFinder(AVAILABLE_PRODUCT).click();
        } catch (Exception e) {

        }
    }

    public void buyFromStore() {
        try {
            this.elementFinder(STORE_PRODUCT).click();
        } catch (Exception e) {

        }
    }
}
