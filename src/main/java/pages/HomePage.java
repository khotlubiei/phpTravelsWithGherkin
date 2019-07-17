package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.BasePage;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }
    public static By from = By.xpath("//input[@class='form input-lg dpd1']");
    public static By to = By.xpath("//input[@class='form input-lg dpd2']");
    private static By searchButton = By.xpath("//*[@id=\"hotels\"]//button[text()[contains(.,'Search')]]");
    private By fromHider = By.id("dpd1");
    private By toHider = By.id("dpd2");
    private By destinationHider = By.xpath("//*[@id=\"s2id_autogen3\"]/a/span[1]");
    private By firstOfferedItem = By.xpath("//*[@id=\"select2-drop\"]/ul/li[1]/ul/li/div");

    private static By destinationInput = By.xpath("//*[@id=\"select2-drop\"]/div/input");

    private  By guestsInput = By.id("travellersInput");
    private By addAdult = By.id("adultPlusBtn");
    private By removeAdult = By.id("adultMinusBtn");

    private static By addChildren = By.id("childPlusBtn");
    private static By removeChildren = By.id("childMinusBtn");


    public HomePage SetDate (By dateInput, int daysFromToday) {
        if (dateInput.equals(from))
        {moveAndClick(fromHider);}
        if (dateInput.equals(to))
        {moveAndClick(toHider);}

        typeText(dateInput,AddDaysToYYYYMMDD(Today(),daysFromToday));
        return PageFactory.initElements(driver, HomePage.class);
    }

    public HotelsSearchPage SubmitSearch (){
        clickOn(searchButton);
        return PageFactory.initElements(driver, HotelsSearchPage.class);
    }

    public HomePage SelectDestination (String destination) {
        System.out.println(destination);
        clickOn(destinationHider);
        typeText(destinationInput,destination);
        clickOn(firstOfferedItem);
        return PageFactory.initElements(driver, HomePage.class);
    }

    public HomePage SetGuests (int adultAmount, int childrenAmount) {
        clickOn(guestsInput);

        int actualAdultInput = ToInt(getInputText(By.id("adultInput")));
        int actualChildrenInput = ToInt(getInputText(By.id("childInput")));


        while (actualAdultInput>adultAmount) {
            clickOn(removeAdult);
            actualAdultInput--;
        }
        while (actualAdultInput<adultAmount) {
            clickOn(addAdult);
            actualAdultInput++;
        }
        while (actualChildrenInput>childrenAmount) {
            clickOn(removeChildren);
            actualChildrenInput--;

        }
        while (actualChildrenInput<childrenAmount) {
            clickOn(addChildren);
            actualChildrenInput++;
        }

        return PageFactory.initElements(driver, HomePage.class);
    }


}