package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Page {
    private WebDriver driver;

    protected final String okButton = "//android.widget.Button[@resource-id=\"android:id/button1\"]";
    protected final String negativeButton = "//android.widget.Button[@resource-id=\"org.wikipedia:id/negativeButton\"]";

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    protected String getResultSearchElement(String source, String substring) {
        return source.replace("{SUBSTRING}", substring);
    }

    protected WebElement waitElementPresent(By by) {
        var wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.withMessage("No element found");

        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected WebElement waitElementAndClick(By by) {
        var element = waitElementPresent(by);
        element.click();

        return element;
    }

    protected WebElement waitElementAndPrint(By by, String value) {
        var element = waitElementPresent(by);
        element.sendKeys(value);

        return element;
    }

    protected boolean noElement(By by) {
        var list
    }
}
