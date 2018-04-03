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

            cheevos.closeCheevos();

            product.buyProduct();
            product.buyFromStore();

            x++;
        }
    }
}