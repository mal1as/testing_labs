package com.itmo.tpo.pages;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.itmo.tpo.utils.ConfProperties.getProperty;
import static com.itmo.tpo.utils.DriverSetUp.getWebDriver;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Finding user testing")
public class FindUserTest {

    public abstract class BaseFindUserTest {

        protected static WebDriver driver;
        protected static String browser;

        @AfterEach
        public void tearDown() {
            driver.quit();
        }

        @Test
        @DisplayName("Find existing user")
        public void findExistingUser() {
            driver.get(getProperty("mainPage"));
            MainPage mainPage = new MainPage(driver);

            mainPage.searchUserByName(getProperty("login"));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.urlToBe(getProperty("myProfilePage")));

            assertEquals("seleniumTest - Codeforces", driver.getTitle());
        }

        @Test
        @DisplayName("Find non-existing user")
        public void findNonExistingUser() {
            driver.get(getProperty("mainPage"));
            MainPage mainPage = new MainPage(driver);

            mainPage.searchUserByName(getProperty("falseLogin"));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.urlToBe(getProperty("mainPage")));

            assertEquals("Codeforces", driver.getTitle());
        }
    }

    @Nested
    @DisplayName("Chrome testing")
    public class ChromeFindUserTest extends BaseFindUserTest {
        @BeforeEach
        public void setUp() {
            browser = "chrome";
            driver = getWebDriver(browser);
        }
    }

    @Nested
    @DisplayName("FireFox testing")
    public class FirefoxFindUserTest extends BaseFindUserTest {
        @BeforeEach
        public void setUp() {
            browser = "firefox";
            driver = getWebDriver(browser);
        }
    }


}