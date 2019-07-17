package StepDefinition;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.HotelsSearchPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static pages.HomePage.from;
import static pages.HomePage.to;
import static utils.BasePage.ToInt;

public class Steps  {
    private static WebDriver driver;


    @Before
    public void driverSetup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.manage().timeouts()
                .setScriptTimeout(10, TimeUnit.SECONDS)
                .pageLoadTimeout(10, TimeUnit.SECONDS)
                .implicitlyWait(10, TimeUnit.SECONDS);

    }


    @Given("^I am on the \"([^\"]+)\" page$")
    public void iAmOnTheHomePage(String pageName) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        if (pageName.equals("home")) {
            driver.get("https://www.phptravels.net/");
        } else {
            System.out.println("This Page is not supported yet. Please contact the QA Team");
        }

    }


    @Then("^search succeeds$")
    public void searchSucceeds() throws Throwable {
        HomePage homePage = new HomePage(driver);
        homePage.SubmitSearch();

    }


    @When("^I search for hotels using following details$")
    public void iSearchForHotelsUsingFollowingDetails(DataTable table) throws Throwable {
        List<List<String>> data = table.raw();
        HomePage homePage = new HomePage(driver);

        String destinationName = data.get(1).get(0);
        int startDateFromToday = ToInt(data.get(1).get(1));
        int endDateFromToday = ToInt(data.get(1).get(2));
        int amountOfAdults = ToInt(data.get(1).get(3));
        int amountOfChildren = ToInt(data.get(1).get(4));

        homePage.SelectDestination(destinationName).SetDate(from,startDateFromToday).SetDate(to,endDateFromToday).SetGuests(amountOfAdults,amountOfChildren);

    }


    @And("^i can see the reviews from people and \"([^\"]*)\"$")
    public void iCanSeeTheReviewsFromPeopleAnd(String reviewerName) throws Throwable {
        HotelsSearchPage hotelsSearchPage = new HotelsSearchPage(driver);
        Assert.assertTrue(hotelsSearchPage.FeedbacksPresented());
        Assert.assertTrue(hotelsSearchPage.ReviewerFeedbackPresented(reviewerName));

    }

    @And("^i can see the price of the \"([^\"]*)\" is \"([^\"]*)\"$")
    public void iCanSeeThePriceOfTheIs(String roomName, String pricePerNight) throws Throwable {
        HotelsSearchPage hotelsSearchPage = new HotelsSearchPage(driver);
        Assert.assertTrue(hotelsSearchPage.RoomLocator(roomName));

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
