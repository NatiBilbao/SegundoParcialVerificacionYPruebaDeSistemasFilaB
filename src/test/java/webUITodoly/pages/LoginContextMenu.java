package webUITodoly.pages;

import org.openqa.selenium.By;
import webUITodoly.controls.Button;
import webUITodoly.controls.TextBox;


public class LoginContextMenu {
    public TextBox emailTextBox = new TextBox(By.id("ctl00_MainContent_LoginControl1_TextBoxEmail"));
    public TextBox passwordTextBox = new TextBox(By.id("ctl00_MainContent_LoginControl1_TextBoxPassword"));
    public Button loginButton = new Button(By.id("ctl00_MainContent_LoginControl1_ButtonLogin"));
}
