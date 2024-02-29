package com.itmo.tpo.pages;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import static com.itmo.tpo.utils.ConfProperties.getProperty;
import static com.itmo.tpo.utils.DriverSetUp.getWebDriver;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Change locale testing")
public class VisitSponsorPageTest {

    public abstract class BaseVisitSponsorPageTest {

        protected static WebDriver driver;
        protected static String browser;

        @AfterEach
        public void tearDown() {
            driver.quit();
        }

        @Test
        @DisplayName("Visit sponsor page in english")
        public void VisitSponsorPageInEnglishTest() {
            driver.get(getProperty("mainPage"));
            MainPage mainPage = new MainPage(driver);
            mainPage.changeLocaleToEnglish();
            mainPage.goToSponsorPage();

            assertEquals(getProperty("bestSponsorEverEN"), driver.getCurrentUrl());
        }

        @Test
        @DisplayName("Visit sponsor page in russian")
        public void VisitSponsorPageInRussianTest() {
            driver.get(getProperty("mainPage"));
            MainPage mainPage = new MainPage(driver);
            mainPage.changeLocaleToRussian();
            mainPage.goToSponsorPage();

            assertEquals(getProperty("bestSponsorEverRU"), driver.getCurrentUrl());
        }
    }

    @Nested
    @DisplayName("Chrome testing")
    public class ChromeVisitSponsorPageTest extends BaseVisitSponsorPageTest {
        @BeforeEach
        public void setUp() {
            browser = "chrome";
            driver = getWebDriver(browser);
        }
    }

    @Nested
    @DisplayName("FireFox testing")
    public class FirefoxVisitSponsorPageTest extends BaseVisitSponsorPageTest {
        @BeforeEach
        public void setUp() {
            browser = "firefox";
            driver = getWebDriver(browser);
        }
    }


}