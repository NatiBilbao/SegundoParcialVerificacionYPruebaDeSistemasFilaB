package webUITodoist.session.pages;

import org.openqa.selenium.By;
import webUITodoist.controls.Button;
import webUITodoist.controls.TextBox;

public class AddProjectSection {
    public TextBox nametextBox = new TextBox(By.id("edit_project_modal_field_name"));
    public Button addButton = new Button(By.xpath("//button[@type=\"submit\"]"));
}
