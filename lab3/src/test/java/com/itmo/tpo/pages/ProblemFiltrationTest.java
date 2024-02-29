package com.itmo.tpo.pages;

import com.itmo.tpo.dto.ProblemRow;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.itmo.tpo.utils.ConfProperties.getProperty;
import static com.itmo.tpo.utils.DriverSetUp.getWebDriver;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Filter problems testing")
public class ProblemFiltrationTest {

    public abstract class BaseProblemFiltrationTest {

        protected static WebDriver driver;
        protected static String browser;

        @AfterEach
        public void tearDown() {
            driver.quit();
        }

        @Test
        @DisplayName("Problem difficult filter")
        public void problemDifficultFilterTest() {
            driver.get(getProperty("problemPage"));
            ProblemPage problemPage = new ProblemPage(driver);

            long minDifficulty = 1000L;
            long maxDifficulty = 1500L;
            problemPage.setMinDifficulty(Long.toString(minDifficulty));
            problemPage.setMaxDifficulty(Long.toString(maxDifficulty));
            problemPage.submitFilter();

            for (int i = 1; i <= problemPage.getTableRowsCount(); i++) {
                ProblemRow problemRow = problemPage.getProblemRowByIndex(i);
                String message = "Actual: " + problemRow.getDifficulty() + ", problem name: " + problemRow.getName();
                assertTrue(problemRow.getDifficulty() >= minDifficulty && problemRow.getDifficulty() <= maxDifficulty, message);
            }
        }


        @Test
        @DisplayName("Add tags test")
        public void addTagTest(){
            driver.get((getProperty("problemPage")));
            ProblemPage problemPage = new ProblemPage(driver);
            problemPage.changeLocaleToRussian();

            String tag = "Графы";
            String expectedTag = "графы";
            problemPage.selectTag(tag);

            assertEquals(expectedTag, problemPage.filterTagsFrame.findElement(By.xpath("//span[@title=\"" + tag + "\"]")).getText());
        }

        @Test
        @DisplayName("Remove tags test")
        public void removeTagTest(){
            driver.get((getProperty("problemPage")));
            ProblemPage problemPage = new ProblemPage(driver);
            problemPage.changeLocaleToRussian();

            String tag = "Графы";

            problemPage.selectTag(tag);
            problemPage.removeTag(tag);

            assertTrue(driver.findElements(By.xpath("//span[@title=\"" + tag + "\"]/img")).isEmpty());
        }
    }

    @Nested
    @DisplayName("Chrome testing")
    public class ChromeProblemFiltrationTest extends BaseProblemFiltrationTest {
        @BeforeEach
        public void setUp() {
            browser = "chrome";
            driver = getWebDriver(browser);
        }
    }

    @Nested
    @DisplayName("FireFox testing")
    public class FirefoxProblemFiltrationTest extends BaseProblemFiltrationTest {
        @BeforeEach
        public void setUp() {
            browser = "firefox";
            driver = getWebDriver(browser);
        }
    }


}