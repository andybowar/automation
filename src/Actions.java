import java.awt.datatransfer.*;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Actions implements ClipboardOwner {
    public void playGame() {
        WebDriver driver = new FirefoxDriver();
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        org.openqa.selenium.interactions.Actions paste = new org.openqa.selenium.interactions.Actions(driver);

        // Strings are used where getPageSource().contains() or get() must be used. In any other case, strings have been changed to By variables
        int x = 0;
        final String BASE_URL = "http://orteil.dashnet.org/cookieclicker/";
        By cookie = By.id("bigCookie");
        By options = By.id("prefsButton");
        By load = By.id("promptOption0");
        By cancelLoad = By.id("promptOption1");
        By codeText = By.id("textareaPrompt");
        By enabled = By.cssSelector("div.product.unlocked.enabled");
        By closeMenu = By.className("menuClose");
        By closecheevo = By.className("close");
        By cookieWarning = By.linkText("Got it!");
        By export = By.linkText("Export save");
        By importSave = By.linkText("Import save");
        String cheevo0 = "note-0";
        String cheevo1 = "note-1";
        String cheevo2 = "note-2";
        String clearAllcheevos = "sidenote";
        String saveCodeOnExit = null;

        driver.get(BASE_URL);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(cookie));
        wait.until(ExpectedConditions.elementToBeClickable(options));

        driver.findElement(options).click();
        driver.findElement(importSave).click();

        // Currently, there's nothing implemented to fetch the save code from anywhere, so this is always null.
        if (saveCodeOnExit == null) {
            driver.findElement(cancelLoad).click();
            driver.findElement(closeMenu).click();
        } else {
            paste.sendKeys(saveCodeOnExit).build().perform();
            driver.findElement(load).click();
            driver.findElement(closeMenu).click();
        }

        // Get rid of cookie warning
        driver.findElement(cookieWarning).click();


        while (x < 999) {
            driver.findElement(cookie).click();

            try {
                driver.findElement(enabled).click();
            } catch(Exception e) {

            }

            // For achievement IDs from 0-2, click to close them.
            if (driver.getPageSource().contains(cheevo0)
                    || driver.getPageSource().contains(cheevo1)
                    || driver.getPageSource().contains(cheevo2)) {
                driver.findElement(closecheevo).click();
            // If they somehow start stacking up, look for the big X and clear them all
            } else if (driver.getPageSource().contains(clearAllcheevos)) {
                driver.findElement(By.className(clearAllcheevos)).click();
            }


            x++;
        }

        // Open options menu
        driver.findElement(options).click();
        driver.findElement(export).click();

        // Store save code in variable
        saveCodeOnExit = driver.findElement(codeText).getText();
        System.out.println(saveCodeOnExit);

        // Close save code dialog and menu
        driver.findElement(load).click();
        driver.findElement(closeMenu).click();

        // Copy save code to clipboard
        StringSelection stringSelection = new StringSelection(saveCodeOnExit);
        clipboard.setContents(stringSelection, this);

        // Print save code to file
        PrintWriter out = null;
        try {
            out = new PrintWriter("saveCode.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        out.println(saveCodeOnExit);

        //driver.close();

        // Reload using save code
        driver.findElement(options).click();
        driver.findElement(importSave).click();

        paste.sendKeys(saveCodeOnExit).build().perform();
        driver.findElement(load).click();
        driver.findElement(closeMenu).click();
    }

    public void lostOwnership(Clipboard clipboard, Transferable contents) {

    }
}
