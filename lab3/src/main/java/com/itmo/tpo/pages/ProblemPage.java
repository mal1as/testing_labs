package com.itmo.tpo.pages;

import com.itmo.tpo.dto.ProblemRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

// page_url = https://codeforces.com/problemset
public class ProblemPage extends AbstractPage{
    

    @FindBy(xpath = "//input[@name=\"minDifficulty\"]")
    public WebElement minDifficulty;

    @FindBy(xpath = "//input[@name=\"maxDifficulty\"]")
    public WebElement maxDifficulty;

    @FindBy(xpath = "//input[@type=\"submit\"]")
    public WebElement submitButton;

    @FindBy(xpath = "//a[@href=\"#\"]")
    public WebElement addTagFilterTags;

    @FindBy(xpath = "//select")
    public WebElement dropdownListOfTags;

    @FindBy(xpath = "//div[@class=\"_FilterByTagsFrame_tagBoxes\"]")
    public WebElement filterTagsFrame;

    @FindBy(xpath = "//table[@class=\"problems\"]")
    public WebElement problemsTable;

    public ProblemPage(WebDriver driver) {
        super(driver);
    }


    public void setMinDifficulty(String difficulty){
        minDifficulty.sendKeys(difficulty);
    }
    
    public void setMaxDifficulty(String difficulty){
        maxDifficulty.sendKeys(difficulty);
    }
    
    public void setRangeDifficulty(String minDiff, String maxDiff){
        setMinDifficulty(minDiff);
        setMaxDifficulty(maxDiff);
    }
    
    public void submitFilter(){
        submitButton.click();
    }

    public void selectTag(String tag){
        addTagFilterTags.click();
        dropdownListOfTags.findElement(By.xpath("//option[@title=\"" + tag + "\"]")).click();
    }

    public void removeTag(String tag){
        filterTagsFrame.findElement(By.xpath("//span[@title=\"" + tag + "\"]/img")).click();
    }
    
    public ProblemRow getProblemRowByIndex(int index) {
        WebElement row = problemsTable.findElement(By.xpath("//tbody/tr[" + (index + 1) + "]"));
        List<WebElement> columns = row.findElements(By.xpath("./td"));
        return ProblemRow.builder()
                .problemNum(columns.get(0).findElement(By.xpath("./a")).getText())
                .name(columns.get(1).findElement(By.xpath("./div[1]/a")).getText())
                .tags(columns.get(1).findElements(By.xpath("./div[2]/a"))
                        .stream().map(WebElement::getText).collect(Collectors.toList()))
                .difficulty(Long.parseLong(columns.get(3).findElement(By.xpath("./span")).getText()))
                .solutionNumber(Long.parseLong(columns.get(4).findElement(By.xpath("./a")).getText().substring(2)))
                .build();
    }

    public int getTableRowsCount() {
        return problemsTable.findElements(By.xpath("./tbody/tr")).size() - 1;
    }
}
