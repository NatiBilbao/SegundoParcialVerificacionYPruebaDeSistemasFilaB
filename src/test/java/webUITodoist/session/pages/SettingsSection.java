package webUITodoist.session.pages;

import org.openqa.selenium.By;
import webUITodoist.controls.Button;
import webUITodoist.controls.TextBox;

public class SettingsSection {
    public TextBox nameTextBox =  new TextBox(By.xpath("//span[text()=\"Nombre\"]/../../..//input"));
    public Button closeButton = new Button(By.xpath("//button[@class=\"\"_3930afa0 aa19cb97 _7246d092 abd5766f\"\"]"));
    public Button updateButton = new Button(By.xpath("//button[span[text()='Actualizar']]"));
}
