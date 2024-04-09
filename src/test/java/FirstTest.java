import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ui.BookmarkPage;
import ui.MainPage;
import ui.ResultPage;
import ui.SearchPage;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class FirstTest {
    private final SearchPage searchPage = new SearchPage(driver);
    private final ResultPage resultPage = new ResultPage(driver);
    private final BookmarkPage bookmarkPage = new BookmarkPage(driver);
    private final MainPage mainPage = new MainPage(driver);

    private static WebDriver driver;
    private static final String appiumUrl = "http://localhost:4723/wd/hub";

    @BeforeAll
    public static void setUp() {
        var desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("appium:deviceName", "avd");
        desiredCapabilities.setCapability("appium:platformVersion", "13");
        desiredCapabilities.setCapability("appium:appPackage", "org.wikipedia");
        desiredCapabilities.setCapability("appium:appActivity", ".main.MainActivity");

        try {
            driver = new RemoteWebDriver(new URL(appiumUrl), desiredCapabilities);
        } catch (MalformedURLException e) {
            System.out.println("error");
        }
    }
    
    @AfterAll
    public static void down() {
        driver.quit();
    }

    @Test
    void firstTest() {
        mainPage.skip();
        searchPage.initSearch();
        searchPage.typeSearchLine("Кемеровский государственный университет");
        searchPage.waitForResult("высшее учебное заведение в Кемерове");

        var titleText = resultPage.getTitleText("Кемеровский государственный университет");

        assertEquals("Кемеровский государственный университет", titleText, "Titles are not the same");
    }
    
    @Test
    void removeBookmark() {
        var hobbit = "Хоббит, или Туда и обратно";

        searchPage.initSearch();
        searchPage.typeSearchLine(hobbit);
        searchPage.chooseResult(hobbit);

        var title = resultPage.getTitleText(hobbit);
        assertEquals(hobbit, title, "Titles are not same");

        var hobbitBookmark = "Хоббит";
        resultPage.addToBookmarks(hobbitBookmark);
        bookmarkPage.removeBookmark();
        bookmarkPage.notNow();

        title = bookmarkPage.getEmptyTitle();
        assertEquals("Ещё нет списков для чтения", title, "Titles are not the same");
    }
}
