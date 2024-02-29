package com.itmo.tpo.pages;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.itmo.tpo.utils.ConfProperties.getProperty;
import static com.itmo.tpo.utils.DriverSetUp.getWebDriver;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Calendar print testing")
public class CalendarPrintTest {

    public abstract class BaseCalendarPrintTest {

        protected static WebDriver driver;
        protected static String browser;

        @AfterEach
        public void tearDown() {
            driver.quit();
        }

        @Test
        @DisplayName("Calendar print test")
        public void CalendarPrintTest() {
            driver.get(getProperty("calendarPage"));
            CalendarPage calendarPage = new CalendarPage(driver);
            calendarPage.printCalendar();

            List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(browserTabs .get(1));

            String expectedUrl = "https://calendar.google.com/calendar/print_preview";

            assertEquals(expectedUrl, driver.getCurrentUrl().split("[?]")[0]);
        }

    }

    @Nested
    @DisplayName("Chrome testing")
    public class ChromeCalendarPrintTest extends BaseCalendarPrintTest {
        @BeforeEach
        public void setUp() {
            browser = "chrome";
            driver = getWebDriver(browser);
        }
    }

    @Nested
    @DisplayName("FireFox testing")
    public class FirefoxCalendarPrintTest extends BaseCalendarPrintTest {
        @BeforeEach
        public void setUp() {
            browser = "firefox";
            driver = getWebDriver(browser);
        }
    }


}