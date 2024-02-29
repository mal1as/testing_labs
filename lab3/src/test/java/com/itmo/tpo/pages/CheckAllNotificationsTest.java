package com.itmo.tpo.pages;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static com.itmo.tpo.utils.ConfProperties.getProperty;
import static com.itmo.tpo.utils.DriverSetUp.getWebDriver;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Check notification history testing")
public class CheckAllNotificationsTest {

    public abstract class BaseCheckAllNotificationsTest {

        protected static WebDriver driver;
        protected static String browser;

        @AfterEach
        public void tearDown() {
            driver.quit();
        }

        @DisplayName("Check notification history test")
        @Test
        public void CheckAllNotificationsTest() {
            driver.get(getProperty("authPage"));
            LoginPage loginPage = new LoginPage(driver);
            loginPage.auth(getProperty("login"), getProperty("password"));

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.urlToBe(getProperty("mainPage")));

            driver.get(getProperty("myProfilePage"));
            LoggedUserProfilePage loggedUserProfilePage = new LoggedUserProfilePage(driver);
            loggedUserProfilePage.viewNotificationHistory();

            String expectedUrl = "https://codeforces.com/notifications";

            assertEquals(expectedUrl, driver.getCurrentUrl());
        }

    }

    @Nested
    @DisplayName("Chrome testing")
    public class ChromeCheckAllNotificationsTest extends BaseCheckAllNotificationsTest {
        @BeforeEach
        public void setUp() {
            browser = "chrome";
            driver = getWebDriver(browser);
        }
    }

    @Nested
    @DisplayName("FireFox testing")
    public class FirefoxCheckAllNotificationsTest extends BaseCheckAllNotificationsTest {
        @BeforeEach
        public void setUp() {
            browser = "firefox";
            driver = getWebDriver(browser);
        }
    }


}