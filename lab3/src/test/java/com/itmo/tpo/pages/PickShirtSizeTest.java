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

@DisplayName("Picking shirt size testing")
public class PickShirtSizeTest {

    public abstract class BasePickShirtSizeTest {

        protected static WebDriver driver;
        protected static String browser;

        @AfterEach
        public void tearDown() {
            driver.quit();
        }

        @DisplayName("Pick shirt size test")
        @ParameterizedTest(name = "size = {0}")
        @CsvFileSource(resources = "/csv/shirt_sizes.csv")
        public void PickShirtSizeTest(String size, String description) {
            driver.get(getProperty("authPage"));
            LoginPage loginPage = new LoginPage(driver);
            loginPage.auth(getProperty("login"), getProperty("password"));

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.urlToBe(getProperty("mainPage")));

            driver.get(getProperty("myProfilePage"));
            LoggedUserProfilePage loggedUserProfilePage = new LoggedUserProfilePage(driver);
            loggedUserProfilePage.changeLocaleToRussian();
            String actualDescription = loggedUserProfilePage.setShirtSize(size);


            assertEquals(description, actualDescription);
        }

    }

    @Nested
    @DisplayName("Chrome testing")
    public class ChromePickShirtSizeTest extends BasePickShirtSizeTest {
        @BeforeEach
        public void setUp() {
            browser = "chrome";
            driver = getWebDriver(browser);
        }
    }

    @Nested
    @DisplayName("FireFox testing")
    public class FirefoxPickShirtSizeTest extends BasePickShirtSizeTest {
        @BeforeEach
        public void setUp() {
            browser = "firefox";
            driver = getWebDriver(browser);
        }
    }


}