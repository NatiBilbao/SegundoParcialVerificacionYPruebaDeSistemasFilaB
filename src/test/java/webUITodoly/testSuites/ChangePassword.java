package webUITodoly.testSuites;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webUITodoly.utils.Properties;

import java.util.Date;

public class ChangePassword extends TestBase{
    String newPassword = "Pass123." + new Date().getTime();

    @BeforeEach
    public void setup() throws InterruptedException {
        mainPage.loginButton.click();
        Thread.sleep(2000);

        loginContextMenu.emailTextBox.setText(Properties.pageUser);
        loginContextMenu.passwordTextBox.setText(Properties.pagePassword);

        loginContextMenu.loginButton.click();

    }

    @Test
    public void verifyCambiarPass() throws InterruptedException {

        homePage.settingsButton.click();

        String newPassword = "Pass123." + new Date().getTime();
        settingsContextMenu.oldPasswordTextBox.setText(Properties.pagePassword);
        settingsContextMenu.newPasswordTextBox.setText(newPassword);
        settingsContextMenu.okButton.click();

        homePage.logoutButton.click();

        mainPage.loginButton.click();
        Thread.sleep(2000);

        loginContextMenu.emailTextBox.setText(Properties.pageUser);
        loginContextMenu.passwordTextBox.setText(newPassword);

        loginContextMenu.loginButton.click();

        Thread.sleep(2000);

        System.out.println("newPassword: " + newPassword);

        Assertions.assertTrue(homePage.logoutButton.isControlDisplayed(), "ERROR la contraseña no fue cambiada");

    }
}
