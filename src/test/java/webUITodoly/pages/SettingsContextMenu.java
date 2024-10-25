package webUITodoly.pages;

import org.openqa.selenium.By;
import webUITodoly.controls.Button;
import webUITodoly.controls.TextBox;

public class SettingsContextMenu {
    public TextBox oldPasswordTextBox = new TextBox(By.id("TextPwOld"));
    public TextBox newPasswordTextBox = new TextBox(By.id("TextPwNew"));

    public Button okButton = new Button(By.xpath("//span[text()='Ok']//parent::button"));
}
