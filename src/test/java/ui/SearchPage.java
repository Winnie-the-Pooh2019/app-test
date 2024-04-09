package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends Page {
    private final String searchButton = "//android.widget.TextView[@text=\"Поиск по Википедии\"]";
    private final String searchField = "//android.widget.AutoCompleteTextView[@resource-id=\"org.wikipedia:id/search_src_text\"]";
    private final String searchResult = "//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_description\" and @text=\"{SUBSTRING}\"]";
    private final String searchTitle = "//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_title\" and @text=\"{SUBSTRING}\"]";

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void initSearch() {
        waitElementAndClick(By.xpath(searchButton));
    }

    public void typeSearchLine(String request) {
        waitElementAndPrint(By.xpath(searchField), request);
    }

    public void waitForResult(String substring) {
        var resultXPath = getResultSearchElement(searchResult, substring);
        waitElementAndClick(By.xpath(resultXPath));
    }

    public void chooseResult(String substring) {
        var resultXPath = getResultSearchElement(searchTitle, substring);
        waitElementAndClick(By.xpath(resultXPath));
    }
}
