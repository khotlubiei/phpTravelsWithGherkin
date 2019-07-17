import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;
import utils.BaseTest;

import java.util.concurrent.TimeUnit;

import static pages.HomePage.from;
import static pages.HomePage.to;

public class Tests extends BaseTest {
    private HomePage homePage;


    @org.junit.Before
    public void driverSetup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.manage().timeouts()
                .setScriptTimeout(10, TimeUnit.SECONDS)
                .pageLoadTimeout(10, TimeUnit.SECONDS)
                .implicitlyWait(10, TimeUnit.SECONDS);

    }
@Test
    public void main() {
        String roomName = "Junior Suite";
        int roomPricePerStay = 1250;
        String destinationName = "Rendezvous Hotel Singapore";
        int startDaysFromToday = 5;
        int endDaysFromToday = 10;
        int adultNumber = 2;
        int childrenNumber = 1;
        homePage = PageFactory.initElements(driver, HomePage.class);
        driver.get(APP_URL);

    Assert.assertTrue(roomPricePerStay==homePage.SelectDestination(destinationName)
            .SetDate(from,startDaysFromToday)
            .SetDate(to,endDaysFromToday)
            .SetGuests(adultNumber,childrenNumber)
            .SubmitSearch()
            .GetPricePerStay(roomName));
    }
}

