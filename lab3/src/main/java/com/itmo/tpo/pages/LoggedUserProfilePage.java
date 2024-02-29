package com.itmo.tpo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// page_url = https://codeforces.com/profile/seleniumTest
public class LoggedUserProfilePage extends AbstractPage{

    public LoggedUserProfilePage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"header\"]/div[2]/div[1]/div/div[1]")
    public WebElement notificationIcon;

    @FindBy(xpath = "//*[@id=\"header\"]/div[2]/div[1]/div/div[2]/div[5]/a")
    public WebElement notificationHistoryLink;

    @FindBy(xpath = "//*[@id=\"pageContent\"]/div[1]/ul/li[3]")
    public WebElement settingsPage;

    @FindBy(xpath = "//*[@id=\"pageContent\"]/div[2]/div/div[2]/div/div[2]/div")
    public WebElement userInfo;

    public void viewNotificationHistory(){
        notificationIcon.click();
        notificationHistoryLink.click();
    }

    public String checkUserInfo(){
        try {
            return userInfo.getText();
        } catch (Exception e) {
            return "empty";
        }
    }

    public String setShirtSize(String size){
        settingsPage.click();
        driver.findElement(By.xpath("//*[@id=\"tShirtSize\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"tShirtSize\"]/option[@value=\""+size+"\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"settings-form\"]/table[3]/tbody/tr/td/div/input")).click();
        return !size.equals("") ? driver.findElement(By.xpath("//*[@id=\"tShirtSize\"]/option[@selected]")).getText()
                : "unselected";
    }




}