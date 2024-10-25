package webUITodoist.testSuites;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webUITodoist.session.pages.LoginPage;
import webUITodoist.session.pages.MenuSection;
import webUITodoist.session.pages.SettingsSection;
import webUITodoist.session.Session;

public class UpdateNameAccount {
    LoginPage loginPage = new LoginPage();
    MenuSection menuSection = new MenuSection();
    SettingsSection settingsSection = new SettingsSection();

    @BeforeEach
    public void open() {
        Session.getInstance().getBrowser().get("https://todoist.com/app/");
    }

    @AfterEach
    public void close() {
        Session.getInstance().closeSession();
    }

    @Test
    public void changeName() throws InterruptedException {

        loginPage.emailTextBox.setText("nataliabilbaocano19@gmail.com");
        loginPage.pwdTextBox.setText("Admin123.");
        loginPage.loginButton.click();

        menuSection.informationButton.click();
        menuSection.settingsButton.click();

        String name = "Natalia Bilbao Cano";
        settingsSection.nameTextBox.clearSetText(name);
        settingsSection.updateButton.click();

        Thread.sleep(3000);
        settingsSection.closeButton.click();

        menuSection.informationButton.click();
        menuSection.closeSession.click();
        loginPage.mainLoginButton.click();

        loginPage.emailTextBox.setText("nataliabilbao@gmail.com");
        loginPage.pwdTextBox.setText("Pass123.");
        loginPage.loginButton.click();

        menuSection.informationButton.click();
        menuSection.settingsButton.click();
        Assertions.assertEquals(settingsSection.nameTextBox.getValue(), name, "Error! no se cambio el nombre");
    }
}
