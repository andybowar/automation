import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.awt.datatransfer.*;
import java.awt.Toolkit;

public class saveGame {
    void copyCode() {
        WebDriver driver = new FirefoxDriver();
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        String options = "prefsButton";
        String export = "Export save";
        String codeText = "textareaPrompt";
        String saveCode = "";

        StringSelection stringSelection = new StringSelection(saveCode);



        driver.findElement(By.id(options)).click();
        driver.findElement(By.linkText(export)).click();
        saveCode = String.valueOf(driver.findElement(By.id(codeText)));
        clipboard.setContents(saveCode, );

    }
}
