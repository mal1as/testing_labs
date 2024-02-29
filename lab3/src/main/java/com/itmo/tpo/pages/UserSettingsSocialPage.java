package com.itmo.tpo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// page_url = https://codeforces.com/settings/social
public class UserSettingsSocialPage extends AbstractPage{

    public UserSettingsSocialPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@id=\"settings-form\"]/table/tbody/tr[1]/td[2]/input")
    public WebElement nameInEnglishField;

    @FindBy(xpath = "//*[@id=\"settings-form\"]/table/tbody/tr[3]/td[2]/input")
    public WebElement surnameInEnglishField;

    @FindBy(xpath = "//*[@id=\"settings-form\"]/table/tbody/tr[5]/td[2]/input")
    public WebElement nameInRussianField;

    @FindBy(xpath = "//*[@id=\"settings-form\"]/table/tbody/tr[7]/td[2]/input")
    public WebElement surnameInRussianField;

    @FindBy(xpath = "//*[@id=\"settings-form\"]/table/tbody/tr[10]/td[2]/input[2]")
    public WebElement countryField;

    @FindBy(xpath = "//*[@id=\"settings-form\"]/table/tbody/tr[12]/td[2]/input[2]")
    public WebElement cityField;

    @FindBy(xpath = "//*[@id=\"settings-form\"]/table/tbody/tr[16]/td/div/input")
    public WebElement saveChangesButton;

    public void setDefaultValues(){
        clearAllValues();
        nameInEnglishField.sendKeys("Selenium");
        surnameInEnglishField.sendKeys("Test");
        nameInRussianField.sendKeys("Селениум");
        surnameInRussianField.sendKeys("Тест");
        countryField.sendKeys("Нигер");
        cityField.sendKeys("Ниамей");
        saveChangesButton.click();
    }

    public void setNewValues(String nameInEnglish, String surnameInEnglish, String nameInRussian,
                             String surnameInRussian, String country, String city) {
        clearAllValues();
        nameInEnglishField.sendKeys(nameInEnglish);
        surnameInEnglishField.sendKeys(surnameInEnglish);
        nameInRussianField.sendKeys(nameInRussian);
        surnameInRussianField.sendKeys(surnameInRussian);
        countryField.sendKeys(country);
        cityField.sendKeys(city);
        saveChangesButton.click();
    }

    private void clearAllValues(){
        nameInEnglishField.clear();
        surnameInEnglishField.clear();
        nameInRussianField.clear();
        surnameInRussianField.clear();
        countryField.clear();
        cityField.clear();
    }
}
