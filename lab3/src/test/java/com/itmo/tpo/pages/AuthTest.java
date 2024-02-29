package com.itmo.tpo.pages;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.itmo.tpo.utils.ConfProperties.getProperty;
import static com.itmo.tpo.utils.DriverSetUp.getWebDriver;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Authentication testing")
public class AuthTest {

    public abstract class BaseAuthTest {

        protected static WebDriver driver;
        protected static String browser;

        @AfterEach
        public void tearDown() {
            driver.quit();
        }

        @Test
        @DisplayName("Authentication with login")
        public void authTestWithLogin() {
            driver.get(getProperty("authPage"));
            LoginPage loginPage = new LoginPage(driver);

            loginPage.auth(getProperty("login"), getProperty("password"));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.urlToBe(getProperty("mainPage")));

            assertEquals(getProperty("mainPage"), driver.getCurrentUrl());
        }

        @Test
        @DisplayName("Authentication with email")
        public void authTestWithEmail() {
            driver.get(getProperty("authPage"));
            LoginPage loginPage = new LoginPage(driver);

            loginPage.auth(getProperty("email"), getProperty("password"));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.urlToBe(getProperty("mainPage")));

            assertEquals(getProperty("mainPage"), driver.getCurrentUrl());
        }
    }

    @Nested
    @DisplayName("Chrome testing")
    public class ChromeAuthTest extends BaseAuthTest {
        @BeforeEach
        public void setUp() {
            browser = "chrome";
            driver = getWebDriver(browser);
        }
    }

    @Nested
    @DisplayName("FireFox testing")
    public class FirefoxAuthTest extends BaseAuthTest {
        @BeforeEach
        public void setUp() {
            browser = "firefox";
            driver = getWebDriver(browser);
        }
    }


}