package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultPage extends Page {
    private final String titlePath = "//android.widget.TextView[@text=\"{SUBSTRING}\"]";
    private final String menu = "//android.widget.TextView[@content-desc=\"Сохранить\"]";
    private final String snackbarButton = "//android.widget.Button[@resource-id=\"org.wikipedia:id/snackbar_action\"]";
    private final String titleInput = "//android.widget.EditText[@resource-id=\"org.wikipedia:id/text_input\"]";

    public ResultPage(WebDriver driver) {
        super(driver);
    }

    public String getTitleText(String substring) {
        return waitElementPresent(By.xpath(getResultSearchElement(titlePath, substring))).getText();
    }

    public void addToBookmarks(String bookmarkName) {
        waitElementAndClick(By.xpath(menu));
        waitElementAndClick(By.xpath(snackbarButton));
        waitElementAndPrint(By.xpath(titleInput), bookmarkName);
        waitElementAndClick(By.xpath(okButton));
        waitElementAndClick(By.xpath(snackbarButton));
    }
}
