package com.itmo.tpo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

import static com.itmo.tpo.utils.ConfProperties.getProperty;

// page_url = https://codeforces.com/enter
public class LoginPage  extends AbstractPage{

    @FindBy(xpath = "//*[@id='handleOrEmail']")
    public WebElement loginField;

    @FindBy(xpath = "//*[@id='password']")
    public WebElement passwordField;

    @FindBy(xpath = "//input[@type=\"submit\"]")
    public WebElement enterButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public void auth(String login, String password){
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        enterButton.click();
    }

}