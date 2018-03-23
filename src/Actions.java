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
        String baseURL = "http://orteil.dashnet.org/cookieclicker/";
        By cookie = By.id("bigCookie");
        By cursor = By.id("product0");
        By grandma = By.id("product1");
        By options = By.id("prefsButton");
        By load = By.id("promptOption0");
        By cancelLoad = By.id("promptOption1");
        By codeText = By.id("textareaPrompt");
        By closeMenu = By.className("menuClose");
        By closecheevo = By.className("close");
        By cookieWarning = By.linkText("Got it!");
        By export = By.linkText("Export save");
        By importSave = By.linkText("Import save");
        String productClass = "product unlocked enabled";
        String cheevo0 = "note-0";
        String cheevo1 = "note-1";
        String cheevo2 = "note-2";
        String numCookies1 = "100 cookies";
        String numCookies2 = "115 cookies";
        String numCookies3 = "133 cookies";
        String numCookies4 = "153 cookies";
        String clearAllcheevos = "sidenote";
        
        driver.get(baseURL);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(cookie));
        wait.until(ExpectedConditions.elementToBeClickable(options));

        driver.findElement(options).click();
        driver.findElement(importSave).click();

        // Check to see if save code exists
        String saveCodeOnExit = null;

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


        while (x < 1) {
            driver.findElement(cookie).click();

            if ((driver.getPageSource().contains(numCookies1) ||
                    driver.getPageSource().contains(numCookies2) ||
                    driver.getPageSource().contains(numCookies3) ||
                    driver.getPageSource().contains(numCookies4)) && driver.getPageSource().contains(productClass)) {

                // If there are exactly the right number of cookies available and the product class exists, click grandma
                driver.findElement(grandma).click();

            } else if (driver.getPageSource().contains(productClass)) {

                // Otherwise, if the product class exists, just buy more cookies
                driver.findElement(cursor).click();
            }

            // For achievement IDs from 0-2, click to close them.
            if (driver.getPageSource().contains(cheevo0) ||
                    driver.getPageSource().contains(cheevo1) ||
                    driver.getPageSource().contains(cheevo2)) {
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
