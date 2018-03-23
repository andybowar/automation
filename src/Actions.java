import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.datatransfer.*;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class Actions implements ClipboardOwner {
    public void playGame() {
        WebDriver driver = new FirefoxDriver();
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        org.openqa.selenium.interactions.Actions paste = new org.openqa.selenium.interactions.Actions(driver);

        int x = 0;
        String baseURL = "http://orteil.dashnet.org/cookieclicker/";
        String cookie = "bigCookie";
        String productClass = "product unlocked enabled";
        String numCookies1 = "100 cookies";
        String numCookies2 = "115 cookies";
        String numCookies3 = "133 cookies";
        String numCookies4 = "153 cookies";
        String cursor = "product0";
        String grandma = "product1";
        String options = "prefsButton";
        String importSave = "Import save";
        String export = "Export save";
        String load = "promptOption0";
        String cancelLoad = "promptOption1";
        String codeText = "textareaPrompt";
        String closeMenu = "menuClose";
        String cheevie0 = "note-0";
        String cheevie1 = "note-1";
        String cheevie2 = "note-2";
        String closeCheevie = "close";
        String cookieWarning = "Got it!";
        String clearAllCheevies = "sidenote";


        driver.get(baseURL);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(cookie)));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(options)));

        driver.findElement(By.id(options)).click();
        driver.findElement(By.linkText(importSave)).click();


        String saveCodeOnExit = null;

        if (saveCodeOnExit == null) {
            driver.findElement(By.id(cancelLoad)).click();
            driver.findElement(By.className(closeMenu)).click();
        } else {
            paste.sendKeys(saveCodeOnExit).build().perform();
            driver.findElement(By.id(load)).click();
            driver.findElement(By.className(closeMenu)).click();
        }


        driver.findElement(By.linkText(cookieWarning)).click();

        while (x < 1) {
            driver.findElement(By.id(cookie)).click();

            if ((driver.getPageSource().contains(numCookies1) ||
                    driver.getPageSource().contains(numCookies2) ||
                    driver.getPageSource().contains(numCookies3) ||
                    driver.getPageSource().contains(numCookies4)) && driver.getPageSource().contains(productClass)) {

                driver.findElement(By.id(grandma)).click();

            } else if (driver.getPageSource().contains(productClass)) {

                driver.findElement(By.id(cursor)).click();

            }
            if (driver.getPageSource().contains(cheevie0) ||
                    driver.getPageSource().contains(cheevie1) ||
                    driver.getPageSource().contains(cheevie2)) {
                driver.findElement(By.className(closeCheevie)).click();
            } else if (driver.getPageSource().contains(clearAllCheevies)) {
                driver.findElement(By.className(clearAllCheevies)).click();
            }


            x++;
        }

        driver.findElement(By.id(options)).click();
        driver.findElement(By.linkText(export)).click();
        saveCodeOnExit = driver.findElement(By.id(codeText)).getText();
        StringSelection stringSelection = new StringSelection(saveCodeOnExit);
        System.out.println(saveCodeOnExit);
        driver.findElement(By.id(load)).click();
        driver.findElement(By.className(closeMenu)).click();

        clipboard.setContents(stringSelection, this);

        PrintWriter out = null;
        try {
            out = new PrintWriter("saveCode.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        out.println(saveCodeOnExit);

        //driver.close();

        /*
        driver.get(baseURL);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(cookie)));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(options)));
*/
        driver.findElement(By.id(options)).click();
        driver.findElement(By.linkText(importSave)).click();


        paste.sendKeys(saveCodeOnExit);
        //driver.findElement(By.id(load)).click();
        //driver.findElement(By.className(closeMenu)).click();


    }

    public void lostOwnership(Clipboard clipboard, Transferable contents) {

    }
}
