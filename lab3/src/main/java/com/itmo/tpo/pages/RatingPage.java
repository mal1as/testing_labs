package com.itmo.tpo.pages;

import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

// page_url = https://codeforces.com/ratings
public class RatingPage extends AbstractPage{

    @FindBy(xpath = "//table[.//img]")
    public WebElement ratingTable;

    public RatingPage(WebDriver driver) {
        super(driver);
    }

    public int getTableRowsCount() {
        return ratingTable.findElements(By.xpath("./tbody/tr")).size() - 1;
    }
    
    public Pair<Long, String> getRowRatingAndTitleByIndex(int index) {
        WebElement row = ratingTable.findElement(By.xpath("//tbody/tr[" + (index + 1) + "]"));
        List<WebElement> columns = row.findElements(By.xpath("./td"));
        Long rating = Long.parseLong(columns.get(columns.size() - 1).getText());
        String title = columns.get(1).findElement(By.xpath("./a")).getAttribute("title");
        return Pair.of(rating, title);
    }
}