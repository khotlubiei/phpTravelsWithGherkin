package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public abstract class BasePage {

    protected static WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    private static WebElement myElement(By by) {
        return driver.findElement(by);
    }

    protected static List<WebElement> listOfElements (By by)
    {
        return driver.findElements(by);
    }

    protected void clickOn (By by) {
        myElement(by).click();
    }

    protected void scroller (int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy("+x+","+y+")");
    }


    public Calendar Today () {
        return Calendar.getInstance(); //getting current date
    }

    protected String AddDaysToYYYYMMDD(Calendar cal, int days) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                cal.add(Calendar.DATE, days); //adding days
                return dateFormat.format(cal.getTime());
            }

    public static int ToInt(String stringtToInt){
        stringtToInt.replaceAll("\\D", "");
        return Integer.parseInt(stringtToInt);
    }
            protected void typeText (By by, String text){
        myElement(by).clear();
        myElement(by).sendKeys(text);

    }

    protected static String getElementText(By by){
        return myElement(by).getText();
    }


    private static void waitForElement(By by) {
        myWaitSeconds(4); // REWORK THIS WORKAROUND
        int secondsWaited;
        for (secondsWaited = 1; myElement(by).isDisplayed() == false || secondsWaited == 30; secondsWaited++) {
                myWaitSeconds(1);
        }
        if (secondsWaited >= 30) {
            System.out.println("Element is not displayed after more than 30 seconds of wait");
        }
    }

    private static void myWaitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    protected static Boolean isElementPresented(By by){
        try {
            waitForElement(by);
            return myElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected static String getInputText(By by) {
        return myElement(by).getAttribute("value");
    }

    protected void moveAndClick(By by) {
        Action moveAndClickAction = new Actions(driver).moveToElement(myElement(by)).click().build();
        moveAndClickAction.perform();
    }





}
