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

@DisplayName("Change social info testing")
public class ChangeSocialInfoTest {

    public abstract class BaseChangeSocialInfoTest {

        protected static WebDriver driver;
        protected static String browser;

        @AfterEach
        public void tearDown() {
            driver.quit();
        }

        @DisplayName("Social change info in russian test")
        @ParameterizedTest(name = "values = {0},{1},{2},{3},{4},{5}")
        @CsvFileSource(resources = "/csv/social_info.csv")
        public void ChangeSocialInfoRussianTest(String nameEn, String surnameEn, String nameRu, String surnameRu,
                                                String country, String city, String expectedRu, String expectedEn) {
            driver.get(getProperty("authPage"));
            LoginPage loginPage = new LoginPage(driver);
            loginPage.auth(getProperty("login"), getProperty("password"));

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.urlToBe(getProperty("mainPage")));

            driver.get(getProperty("settingsSocialPage"));
            UserSettingsSocialPage userSettingsSocialPage = new UserSettingsSocialPage(driver);

            userSettingsSocialPage.changeLocaleToRussian();
            userSettingsSocialPage.setNewValues(nameEn, surnameEn, nameRu, surnameRu, country, city);
            wait.until(ExpectedConditions.urlContains(getProperty("settingsSocialPage")));

            driver.get(getProperty("myProfilePage"));
            LoggedUserProfilePage loggedUserProfilePage = new LoggedUserProfilePage(driver);
            String actualRu = loggedUserProfilePage.checkUserInfo();

            assertEquals(expectedRu, actualRu);
        }

        @DisplayName("Social change info in english test")
        @ParameterizedTest(name = "values = {0},{1},{2},{3},{4},{5}")
        @CsvFileSource(resources = "/csv/social_info.csv")
        public void ChangeSocialInfoEnglishTest(String nameEn, String surnameEn, String nameRu, String surnameRu,
                                                String country, String city, String expectedRu, String expectedEn) {
            driver.get(getProperty("authPage"));
            LoginPage loginPage = new LoginPage(driver);
            loginPage.auth(getProperty("login"), getProperty("password"));

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.urlToBe(getProperty("mainPage")));

            driver.get(getProperty("settingsSocialPage"));
            UserSettingsSocialPage userSettingsSocialPage = new UserSettingsSocialPage(driver);

            userSettingsSocialPage.changeLocaleToEnglish();
            userSettingsSocialPage.setNewValues(nameEn, surnameEn, nameRu, surnameRu, country, city);
            wait.until(ExpectedConditions.urlContains(getProperty("settingsSocialPage")));

            driver.get(getProperty("myProfilePage"));
            LoggedUserProfilePage loggedUserProfilePage = new LoggedUserProfilePage(driver);
            String actualEn = loggedUserProfilePage.checkUserInfo();

            assertEquals(expectedEn, actualEn);
        }

    }

    @Nested
    @DisplayName("Chrome testing")
    public class ChromeChangeSocialInfoTest extends BaseChangeSocialInfoTest {
        @BeforeEach
        public void setUp() {
            browser = "chrome";
            driver = getWebDriver(browser);
        }
    }

    @Nested
    @DisplayName("FireFox testing")
    public class FirefoxChangeSocialInfoTest extends BaseChangeSocialInfoTest {
        @BeforeEach
        public void setUp() {
            browser = "firefox";
            driver = getWebDriver(browser);
        }
    }


}