public class Main extends BasePageObject {
    public static void main(String[] args) {

        CookiePage cookieSection = new CookiePage();
        AchievementPage cheevos = new AchievementPage();
        ProductsPage product = new ProductsPage();

        cookieSection.goToUrl();

        cheevos.removeWarning();

        cookieSection.getCookies();

        while (cookieSection.numCookies < 1000000000){

            cookieSection.clickCookie();
            cookieSection.getCookies();
            cheevos.closeCheevos();

            product.buyProduct();
            product.buyFromStore();
        }
    }
}