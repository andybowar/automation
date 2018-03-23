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
        String cheevo0 = "note-0";
        String cheevo1 = "note-1";
        String cheevo2 = "note-2";
        String closecheevo = "close";
        String cookieWarning = "Got it!";
        String clearAllcheevos = "sidenote";
        
        driver.get(baseURL);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(cookie)));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(options)));

        driver.findElement(By.id(options)).click();
        driver.findElement(By.linkText(importSave)).click();

        // Check to see if save code exists
        String saveCodeOnExit = null;

        // Currently, there's nothing implemented to fetch the save code from anywhere, so this is always null.
        if (saveCodeOnExit == null) {
            driver.findElement(By.id(cancelLoad)).click();
            driver.findElement(By.className(closeMenu)).click();
        } else {
            paste.sendKeys(saveCodeOnExit).build().perform();
            driver.findElement(By.id(load)).click();
            driver.findElement(By.className(closeMenu)).click();
        }

        // Get rid of cookie warning
        driver.findElement(By.linkText(cookieWarning)).click();


        while (x < 1) {
            driver.findElement(By.id(cookie)).click();

            if ((driver.getPageSource().contains(numCookies1) ||
                    driver.getPageSource().contains(numCookies2) ||
                    driver.getPageSource().contains(numCookies3) ||
                    driver.getPageSource().contains(numCookies4)) && driver.getPageSource().contains(productClass)) {

                // If there are exactly the right number of cookies available and the product class exists, click grandma
                driver.findElement(By.id(grandma)).click();

            } else if (driver.getPageSource().contains(productClass)) {

                // Otherwise, if the product class exists, just buy more cookies
                driver.findElement(By.id(cursor)).click();
            }

            // For achievement IDs from 0-2, click to close them.
            if (driver.getPageSource().contains(cheevo0) ||
                    driver.getPageSource().contains(cheevo1) ||
                    driver.getPageSource().contains(cheevo2)) {
                driver.findElement(By.className(closecheevo)).click();

            // If they somehow start stacking up, look for the big X and clear them all
            } else if (driver.getPageSource().contains(clearAllcheevos)) {
                driver.findElement(By.className(clearAllcheevos)).click();
            }


            x++;
        }

        // Open options menu
        driver.findElement(By.id(options)).click();
        driver.findElement(By.linkText(export)).click();

        // Store save code in variable
        saveCodeOnExit = driver.findElement(By.id(codeText)).getText();
        System.out.println(saveCodeOnExit);

        // Close save code dialog and menu
        driver.findElement(By.id(load)).click();
        driver.findElement(By.className(closeMenu)).click();

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
        driver.findElement(By.id(options)).click();
        driver.findElement(By.linkText(importSave)).click();

        paste.sendKeys(saveCodeOnExit).build().perform();
        driver.findElement(By.id(load)).click();
        driver.findElement(By.className(closeMenu)).click();
    }

    public void lostOwnership(Clipboard clipboard, Transferable contents) {

    }
}
