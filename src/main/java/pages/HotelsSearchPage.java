package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BasePage;

import java.util.List;

public class HotelsSearchPage extends BasePage {
    public HotelsSearchPage(WebDriver driver) {
        super(driver);
    }
    private By Review = By.xpath("//*[@id=\"tab-newtopic\"]/div/div/table/tbody/tr");
    private List<WebElement> reviewsList = listOfElements(Review);

    public boolean RoomLocator (String roomName){
        scroller(0,1000);
        By roomLocation = By.xpath("//*[text()[contains(.,'"+roomName+"')]]");
        return isElementPresented(roomLocation);
    }

    public int GetPricePerStay (String roomName){
        String rawRoomPrice = getElementText(By.xpath("//*[text()[contains(.,'"+roomName+"')]]/ancestor::tr/td/div/div/div/div/h2//*[text()[contains(.,'$')]]"));
        return ToInt(rawRoomPrice);
    }

    public boolean FeedbacksPresented (){
return !reviewsList.isEmpty();
    }

    public boolean ReviewerFeedbackPresented (String reviewerName) {
        return isElementPresented(By.xpath("//*[@id=\"tab-newtopic\"]//strong[text()[contains(.,'"+reviewerName+"')]]"));
    }
}
