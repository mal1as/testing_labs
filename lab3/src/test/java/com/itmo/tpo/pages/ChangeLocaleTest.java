package com.itmo.tpo.pages;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import static com.itmo.tpo.utils.ConfProperties.getProperty;
import static com.itmo.tpo.utils.DriverSetUp.getWebDriver;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Change locale testing")
public class ChangeLocaleTest {

    public abstract class BaseChangeLocaleTest {

        protected static WebDriver driver;
        protected static String browser;

        @AfterEach
        public void tearDown() {
            driver.quit();
        }

        @Test
        @DisplayName("Change locale to russian")
        public void changeLocaleToRussianTest() {
            driver.get(getProperty("mainPage"));
            MainPage mainPage = new MainPage(driver);

            mainPage.changeLocaleToRussian();

            assertEquals("ГЛАВНАЯ", mainPage.getCurrentPageText());
        }

        @Test
        @DisplayName("Change locale to english")
        public void changeLocaleToEnglishTest() {
            driver.get(getProperty("mainPage"));
            MainPage mainPage = new MainPage(driver);

            mainPage.changeLocaleToEnglish();

            assertEquals("HOME", mainPage.getCurrentPageText());
        }
    }

    @Nested
    @DisplayName("Chrome testing")
    public class ChromeChangeLocaleTest extends BaseChangeLocaleTest {
        @BeforeEach
        public void setUp() {
            browser = "chrome";
            driver = getWebDriver(browser);
        }
    }

    @Nested
    @DisplayName("FireFox testing")
    public class FirefoxChangeLocaleTest extends BaseChangeLocaleTest {
        @BeforeEach
        public void setUp() {
            browser = "firefox";
            driver = getWebDriver(browser);
        }
    }


}