package com.itmo.tpo.pages;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import static com.itmo.tpo.utils.ConfProperties.getProperty;
import static com.itmo.tpo.utils.DriverSetUp.getWebDriver;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Rating page testing")
public class RatingPageTest {

    private abstract class BaseRatingPageTest {

        private static final NavigableMap<Long, String> GRANDMASTER_MAP = new TreeMap<>(Map.of(
                0L, "Новичок",
                1200L, "Ученик",
                1400L, "Специалист",
                1600L, "Эксперт",
                1900L, "Кандидат в мастера",
                2100L, "Мастер",
                2300L, "Международный мастер",
                2400L, "Гроссмейстер",
                2600L, "Международный гроссмейстер",
                3000L, "Легендарный гроссмейстер"
        ));

        protected static WebDriver driver;
        protected static String browser;

        @AfterEach
        public void tearDown() {
            driver.quit();
        }

        @DisplayName("Compare rating and title")
        @Test
        public void ratingTest() {
            driver.get(getProperty("ratingPage"));
            RatingPage ratingPage = new RatingPage(driver);
            ratingPage.changeLocaleToRussian();
            for (int i = 1; i <= ratingPage.getTableRowsCount(); i++) {
                Pair<Long, String> ratingAndTitle = ratingPage.getRowRatingAndTitleByIndex(i);
                String expected = GRANDMASTER_MAP.floorEntry(ratingAndTitle.getLeft()).getValue();
                String message = "Expected: " + expected + ", actual: " + ratingAndTitle.getRight() + ", rating: " + ratingAndTitle.getLeft();
                assertTrue(ratingAndTitle.getRight().startsWith(expected),message);
            }
        }

        @DisplayName("Filter by country")
        @ParameterizedTest(name = "country = {0}")
        @CsvFileSource(resources = "/csv/country.csv")
        public void filterByCountryTest(String country, String countryInRussian) {
            driver.get(getProperty("ratingPage") + "/country/" + country);
            List<WebElement> rows = driver.findElements(By.xpath("//table[.//img]/tbody/tr"));
            rows.remove(0);

            for (WebElement row : rows) {
                String actual = row.findElement(By.xpath("./td[2]/img")).getAttribute("alt");
                String message = "Expected: " + country + " or " + countryInRussian + ", actual: " + actual;
                assertTrue(List.of(country, countryInRussian).contains(actual), message);
            }
        }

        @DisplayName("Filter by country and city")
        @ParameterizedTest(name = "country = {0}, city = {2}")
        @CsvFileSource(resources = "/csv/country_city.csv")
        public void filterByCountryAndCityTest(String country, String countryInRussian, String city, String cityInRussian) {
            driver.get(getProperty("ratingPage") + "/country/" + country + "/city/" + city);
            List<WebElement> rows = driver.findElements(By.xpath("//table[.//img]/tbody/tr"));
            if(rows.size() > 1) {
                String actual = rows.get(1).findElement(By.xpath("./td[2]/img")).getAttribute("alt");
                String message = "Expected: " + country + " or " + countryInRussian + ", actual: " + actual;
                assertTrue(List.of(country, countryInRussian).contains(actual), message);

                String username = rows.get(1).findElement(By.xpath("./td[2]/a")).getText();
                driver.get(getProperty("profilePage") + "/" + username);
                actual = driver.findElement(By.xpath("//a[contains(@href, \"city\")]")).getText();
                message = "Expected: " + city + " or " + cityInRussian + ", actual: " + actual;
                assertTrue(List.of(city, cityInRussian).contains(actual), message);
            }
        }

        @DisplayName("Filter by organization")
        @ParameterizedTest(name = "organization = {0}")
        @CsvFileSource(resources = "/csv/organization.csv")
        public void filterByOrganizationTest(String organization) {
            driver.get(getProperty("organizationsPage"));
            List<WebElement> links = driver.findElements(By.xpath("//div[@class=\"datatable\"]/div/table/tbody/tr/td[2]/a"));
            String organizationId = links.stream()
                    .filter(a -> a.getText().equals(organization))
                    .map(a -> a.getAttribute("href"))
                    .map(url -> url.substring(url.lastIndexOf("/") + 1))
                    .findFirst()
                    .orElse(null);

            driver.get(getProperty("ratingPage") + "/organization/" + organizationId);
            List<WebElement> rows = driver.findElements(By.xpath("//table[.//img]/tbody/tr"));
            if(rows.size() > 1) {
                String username = rows.get(1).findElement(By.xpath("./td[2]/a")).getText();
                driver.get(getProperty("profilePage") + "/" + username);
                String actual = driver.findElement(By.xpath("//a[contains(@href, \"organization/\")]")).getText();
                String message = "Expected: " + organization + ", actual: " + actual;
                assertEquals(organization, actual, message);
            }
        }
    }

    @Nested
    @DisplayName("Chrome testing")
    public class ChromeRatingPageTest extends BaseRatingPageTest {
        @BeforeEach
        public void setUp() {
            browser = "chrome";
            driver = getWebDriver(browser);
        }
    }

    @Nested
    @DisplayName("Firefox testing")
    public class FirefoxRatingPageTest extends BaseRatingPageTest {
        @BeforeEach
        public void setUp() {
            browser = "firefox";
            driver = getWebDriver(browser);
        }
    }
}