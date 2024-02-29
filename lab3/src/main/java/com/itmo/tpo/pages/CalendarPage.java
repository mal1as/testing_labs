package com.itmo.tpo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.model.Frame;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// page_url = https://codeforces.com/calendar
public class CalendarPage extends AbstractPage{

    public CalendarPage(WebDriver driver) {
        super(driver);
    }

    public void printCalendar(){
        WebElement frame = driver.findElement(By.xpath("//iframe"));
        driver.switchTo().frame(frame);
        driver.findElement(By.xpath("//td[6]/div")).click();
    }
}
