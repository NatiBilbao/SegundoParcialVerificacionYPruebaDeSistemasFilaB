package webUITodoly.testSuites;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import webUITodoly.pages.HomePage;
import webUITodoly.pages.LoginContextMenu;
import webUITodoly.pages.MainPage;
import webUITodoly.pages.SettingsContextMenu;
import webUITodoly.singletonSession.Session;

import java.time.Duration;

public class TestBase {
    protected MainPage mainPage = new MainPage();

    protected LoginContextMenu loginContextMenu = new LoginContextMenu();

    protected HomePage homePage = new HomePage();

    protected SettingsContextMenu settingsContextMenu = new SettingsContextMenu();

    @BeforeEach
    public void openBrowser() {
        Session.getInstance().initBrowser("chrome");
        Session.getInstance().getBrowser().manage().window().maximize();
        Session.getInstance().getBrowser().manage().deleteAllCookies();
        Session.getInstance().getBrowser().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        Session.getInstance().goTo("https://todo.ly/");
    }

    @AfterEach
    public void closeBrowser(){
        Session.getInstance().closeBrowser();
    }
}
