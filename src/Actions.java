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
        final By COOKIE = By.id("bigCookie");
        final By OPTIONS = By.id("prefsButton");
        final By LOAD = By.id("promptOption0");
        final By CANCEL_LOAD = By.id("promptOption1");
        final By CODE_TEXT = By.id("textareaPrompt");
        final By CLOSE_MENU = By.className("menuClose");
        final By COOKIE_WARNING = By.linkText("Got it!");
        final By EXPORT = By.linkText("Export save");
        final By IMPORT_SAVE = By.linkText("Import save");
        final By ENABLED = By.cssSelector("div.product.unlocked.enabled");
        final By CLOSE_CHEEVO = By.cssSelector("div.close");
        String saveCodeOnExit = "Mi4wMDQ1fHwxNTIxMDU4MDg5MzUxOzE1MjEwNTgwODkzNTE7MTUyMjA5NjE5MDIxMDtNY1NhdXNhZ2U7dWltY2J8w6fCvcKkDHw2NTE3NS43NjMzMzMwMzE0MzsyMzg1NTIxLjc2MzMzMzM1NTs0MDg4OzA7NDA4ODsyNDc7MDswOzA7MDswOzA7MDswOzA7MDswOzA7MDswOzA7MDtlYXN0ZXI7MDswOzA7MDswOzA7MDstMTstMTstMTstMTstMTswOzA7MDswOzUwOzA7MDswOzA7MTUyMTA1ODA4OTM1MTt8NjEsNjEsMTQxNDMsMDs0Nyw0Nyw5NzgxMSwwOzMwLDMwLDQyNDE0NywwOzEzLDEzLDk1MTA1OCwwOzMsMyw4OTQyNzAsMDswLDAsMCwwOzAsMCwwLDA7MCwwLDAsMDswLDAsMCwwOzAsMCwwLDA7MCwwLDAsMDswLDAsMCwwOzAsMCwwLDA7MCwwLDAsMDswLDAsMCwwO3zDpsKqwqDDpsKqwqrDpMKKwoDDpMKAwoDDpMKCworDpsKAwoDDpMKAwoDDpMKAwoDDpMKgwoDDpMKAwoDDpMKAwojDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDpMKAwoDDhMKAfMOnwrDCgMOkwrzCgMOkwqPCosOlwonCgMOkwoDCgMOkwqDCgMOkwoDCgMOkwoDCgMOkwoDCgMOkwoDCgMOkwoDCgMOkwoDCgMOkwoDCgMOkwoDCgMOkwoDCgMOkwoDCgMOkwoDCgMOkwoDCgMOkwoDCgMOkwoDCgMOkwoDCgMOkwoDCgMOkwoDCgMOkwoDCgAR8%21END%21";

        driver.get(BASE_URL);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(COOKIE));
        wait.until(ExpectedConditions.elementToBeClickable(OPTIONS));

        driver.findElement(OPTIONS).click();
        driver.findElement(IMPORT_SAVE).click();

        // Currently, there's nothing implemented to fetch the save code from anywhere, so this is always null.
        if (saveCodeOnExit == null) {
            driver.findElement(CANCEL_LOAD).click();
            driver.findElement(CLOSE_MENU).click();
        } else {
            paste.sendKeys(saveCodeOnExit).build().perform();
            driver.findElement(LOAD).click();
            driver.findElement(CLOSE_MENU).click();
        }

        // Get rid of cookie warning
        driver.findElement(COOKIE_WARNING).click();

        while (x < 999999999) {
            driver.findElement(COOKIE).click();

            // Try clicking any available product, don't worry about exceptions
            try {
                driver.findElement(ENABLED).click();
            } catch(Exception expected) {

            }

            // Try clicking the 'x' on any displayed achievement, don't worry about exceptions
            try {
                driver.findElement(CLOSE_CHEEVO).click();
            } catch (Exception expected) {

            }

            x++;
        }

        // Open options menu
        driver.findElement(OPTIONS).click();
        driver.findElement(EXPORT).click();

        // Store save code in variable
        saveCodeOnExit = driver.findElement(CODE_TEXT).getText();
        System.out.println(saveCodeOnExit);

        // Close save code dialog and menu
        driver.findElement(LOAD).click();
        driver.findElement(CLOSE_MENU).click();

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
        driver.findElement(OPTIONS).click();
        driver.findElement(IMPORT_SAVE).click();

        paste.sendKeys(saveCodeOnExit).build().perform();
        driver.findElement(LOAD).click();
        driver.findElement(CLOSE_MENU).click();
    }

    public void lostOwnership(Clipboard clipboard, Transferable contents) {

    }
}
