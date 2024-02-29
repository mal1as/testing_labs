package com.itmo.tpo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://codeforces.com/
public class MainPage extends AbstractPage{

    public MainPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//input[@type=\"text\"]")
    private WebElement searchField;
    
    @FindBy(xpath = "//input[@type=\"submit\"]")
    private WebElement findButton;

    public void searchUserByName(String username){
        searchField.sendKeys(username);
        findButton.click();
    }

}
