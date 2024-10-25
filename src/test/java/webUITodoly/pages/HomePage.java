package webUITodoly.pages;

import org.openqa.selenium.By;
import webUITodoly.controls.Button;

public class HomePage {
    public Button settingsButton = new Button(By.xpath("//a[text()='Settings']"));

    public Button logoutButton = new Button(By.xpath("//a[text()='Logout']"));
}
