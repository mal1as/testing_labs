package com.itmo.tpo.utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

import static com.itmo.tpo.utils.ConfProperties.getProperty;

public class DriverSetUp {

    private static final int TIMEOUT = 1;
    private static final Dimension DIMENSION = new Dimension(640,480);
    public static WebDriver getWebDriver(String browser){
        return switch (browser) {
            case "chrome" -> returnChromeDriver();
            case "firefox" -> returnFirefoxDriver();
            default -> throw new BrowserNotSupportedException(browser);
        };
    }

    private static WebDriver returnChromeDriver(){
        System.setProperty("webdriver.chrome.driver",getProperty("chromeDriver"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().setSize(DIMENSION);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        return driver;
    }

    private static WebDriver returnFirefoxDriver(){
        System.setProperty("webdriver.gecko.driver",getProperty("geckoDriver"));
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        WebDriver driver = new FirefoxDriver(options);
        driver.manage().window().setSize(DIMENSION);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        return driver;
    }
}
