import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;

public class Main extends BasePageObject {
    public static void main(String[] args) {

        CookiePage cookieSection = new CookiePage();
        AchievementPage cheevos = new AchievementPage();
        ProductsPage product = new ProductsPage();

        int x = 0;

        cookieSection.goToUrl();

        cheevos.removeWarning();

        while (x < 999999999){

            cookieSection.clickCookie();

            try {
                cheevos.closeCheevos();
            } catch (NoSuchElementException expected) {

            }

            try {
                product.buyProduct();
            } catch (NoSuchElementException expected) {

            }

            try {
                product.buyFromStore();
            } catch (NoSuchElementException expected) {

            }

            x++;
        }
    }
}