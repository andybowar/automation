import com.sun.javafx.iio.ios.IosDescriptor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.datatransfer.*;
import java.awt.Toolkit;
import java.io.IOException;

public class Actions implements ClipboardOwner {
    public void playGame() {
        WebDriver driver = new FirefoxDriver();
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

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
        String load = "load";
        String cancelLoad = "Nevermind";
        String codeText = "textareaPrompt";
        //String result = "";

        driver.get(baseURL);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(cookie)));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(options)));

        driver.findElement(By.id(options)).click();
        driver.findElement(By.linkText(importSave)).click();

        String saveCode = null;
        try {
            saveCode = (String) clipboard.getContents(null).getTransferData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(saveCode);

        //Transferable t = clipboard.getContents(this);
        //if (t == null) {
        //    driver.findElement(By.linkText(cancelLoad));
        //} else {
        //    driver.findElement(By.linkText(load)).click();
        //}


        while (x < 500) {
            driver.findElement(By.id(cookie)).click();

            if ((driver.getPageSource().contains(numCookies1) ||
                    driver.getPageSource().contains(numCookies2) ||
                    driver.getPageSource().contains(numCookies3) ||
                    driver.getPageSource().contains(numCookies4)) && driver.getPageSource().contains(productClass)) {

                driver.findElement(By.id(grandma)).click();

            } else if(driver.getPageSource().contains(productClass)) {

                driver.findElement(By.id(cursor)).click();

            }


            x++;
        }

        driver.findElement(By.id(options)).click();
        driver.findElement(By.linkText(export)).click();
        String saveCodeOnExit = driver.findElement(By.id(codeText)).getText();
        StringSelection stringSelection = new StringSelection(saveCodeOnExit);
        System.out.println(saveCode);


        clipboard.setContents(stringSelection, this);

        driver.close();

    }

    public void lostOwnership(Clipboard clipboard, Transferable contents) {

    }
}
