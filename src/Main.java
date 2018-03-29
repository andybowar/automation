import org.openqa.selenium.Cookie;

public class Main {
    public static void main(String[] args) {
        Actions actions = new Actions();
        actions.playGame();

        CookieObject cookie = new CookieObject();
        cookie.clickCookie();
    }

}