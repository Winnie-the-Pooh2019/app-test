package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends Page {
    private final String skip = "//android.widget.Button[@resource-id=\"org.wikipedia:id/fragment_onboarding_skip_button\"]";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void skip() {
        waitElementAndClick(By.xpath(skip));
    }
}
