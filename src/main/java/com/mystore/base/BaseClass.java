package com.mystore.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {
    public static Properties prop;

    private static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

//    public void beforeSuite(){
//        DOMConfigurator.configure("log4j.xml");
//    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    @BeforeSuite(groups={"Smoke","Sanity","Regression"})
    public void loadConfig() {
        try {
            prop = new Properties();
            System.out.println("super constructor invoked");
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + "\\Configuration\\config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launchApp(String browserName) {
        WebDriverManager.chromedriver().setup();

        //browserName = prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            // Set Browser to ThreadLocalMap
            ChromeOptions options = new ChromeOptions();

            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            driver.set(new ChromeDriver(options));
        } else if (browserName.equalsIgnoreCase("FireFox")) {
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());
        } else if (browserName.equalsIgnoreCase("IE")) {
            WebDriverManager.iedriver().setup();
            driver.set(new InternetExplorerDriver());
        }

        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();

        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        getDriver().get(prop.getProperty("url"));
    }
}
