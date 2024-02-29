package com.itmo.tpo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://codeforces.com/
public abstract class AbstractPage {
    protected WebDriver driver;

    public AbstractPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//img[@alt=\"In English\"]")
    public WebElement switchEnglishLocale;

    @FindBy(xpath = "//img[@alt=\"По-русски\"]")
    public WebElement switchRussianLocale;

    @FindBy(xpath = "//li[@class='current']/a")
    public WebElement currentPage;

    @FindBy(xpath = "//img[@alt=\"ИТМО\"]")
    public WebElement sponsorLink;



    public void changeLocaleToRussian(){
        switchRussianLocale.click();
    }

    public void changeLocaleToEnglish(){
        switchEnglishLocale.click();
    }

    public String getCurrentPageText(){
        return currentPage.getText();
    }

    public void goToSponsorPage(){
        sponsorLink.click();
    }
}
