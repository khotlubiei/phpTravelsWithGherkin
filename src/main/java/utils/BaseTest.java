package utils;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class BaseTest {
    protected static WebDriver driver;
    private static Properties properties = new Properties();
    protected static String APP_URL;


    @Before
    protected void initDriver() {
        WebDriverManager.chromedriver().setup();
        loadProperties();
        if (driver == null) {
            driver = new ChromeDriver();
        }
        driver.manage().window().fullscreen();
        driver.manage().timeouts()
                .setScriptTimeout(10, TimeUnit.SECONDS)
                .pageLoadTimeout(10, TimeUnit.SECONDS)
                .implicitlyWait(10, TimeUnit.SECONDS);
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private void loadProperties() {

        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("tests.properties"));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }


        APP_URL = properties.getProperty("appUrl");


    }
}
