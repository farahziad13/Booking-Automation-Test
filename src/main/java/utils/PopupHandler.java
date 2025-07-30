package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class PopupHandler {

    public static void dismissPopup(WebDriver driver) {
        try {
            new Actions(driver).moveByOffset(10, 10).click().perform();
            System.out.println("Popup dismissed.");
        } catch (Exception e) {
            System.out.println("No popup to dismiss or dismissal failed: " + e.getMessage());
        }
    }
}
