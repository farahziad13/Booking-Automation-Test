package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PopupHandler;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://www.booking.com/");
        driver.manage().window().maximize();
    }

    public void setDestination(String location) {
        try {
            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=':rh:']")));
            input.sendKeys(location);
        } catch (ElementClickInterceptedException e) {
            PopupHandler.dismissPopup(driver);
            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=':rh:']")));
            input.sendKeys(location);
        }

        By suggestionLocator = By.cssSelector("li[id='autocomplete-result-0'] div[role='button']");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(suggestionLocator)).click();
        } catch (ElementClickInterceptedException e) {
            PopupHandler.dismissPopup(driver);
            wait.until(ExpectedConditions.elementToBeClickable(suggestionLocator)).click();
        }
        try {
            // Wait until the suggestion is both clickable and contains "Alexandria"
            wait.until(ExpectedConditions.textToBePresentInElementLocated(suggestionLocator, "Alexandria"));
            wait.until(ExpectedConditions.elementToBeClickable(suggestionLocator)).click();
        } catch (ElementClickInterceptedException e) {
            PopupHandler.dismissPopup(driver);
            wait.until(ExpectedConditions.textToBePresentInElementLocated(suggestionLocator, "Alexandria"));
            wait.until(ExpectedConditions.elementToBeClickable(suggestionLocator)).click();
        }
    }

    public void selectDates(String checkInDate, String checkOutDate) {
        try {
            driver.findElement(By.xpath("//button[@data-testid='searchbox-dates-container']")).click();
        } catch (ElementClickInterceptedException e) {
            PopupHandler.dismissPopup(driver);
            driver.findElement(By.xpath("//button[@data-testid='searchbox-dates-container']")).click();
        }

        try {
            driver.findElement(By.xpath("//button[@aria-label='Next month']")).click();
            driver.findElement(By.xpath("//button[@aria-label='Next month']")).click();
        } catch (ElementClickInterceptedException e) {
            PopupHandler.dismissPopup(driver);
            driver.findElement(By.xpath("//button[@aria-label='Next month']")).click();
            driver.findElement(By.xpath("//button[@aria-label='Next month']")).click();
        }

        try {
            driver.findElement(By.xpath("//span[@data-date='" + checkInDate + "']")).click();
            driver.findElement(By.xpath("//span[@data-date='" + checkOutDate + "']")).click();
        } catch (ElementClickInterceptedException e) {
            PopupHandler.dismissPopup(driver);
            driver.findElement(By.xpath("//span[@data-date='" + checkInDate + "']")).click();
            driver.findElement(By.xpath("//span[@data-date='" + checkOutDate + "']")).click();
        }
    }

    public void submitSearch() {
        try {
            driver.findElement(By.xpath("//button[@type='submit']")).click();
        } catch (ElementClickInterceptedException e) {
            PopupHandler.dismissPopup(driver);
            driver.findElement(By.xpath("//button[@type='submit']")).click();
        }
    }

    public void scrollToLoadHotels() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 5; i++) {
            js.executeScript("window.scrollBy(0, 1000)");

            try {
                wait.until(driver1 -> ((JavascriptExecutor) driver1)
                        .executeScript("return document.readyState").equals("complete"));
            } catch (Exception ignored) {}
        }
    }

    public void clickHotel(String hotelName) {
        By hotelLocator = By.xpath("//div[@data-testid='title' and contains(text(), '" + hotelName + "')]");
        try {
            WebElement hotel = wait.until(ExpectedConditions.elementToBeClickable(hotelLocator));
            hotel.click();
        } catch (ElementClickInterceptedException e) {
            PopupHandler.dismissPopup(driver);
            WebElement hotel = wait.until(ExpectedConditions.elementToBeClickable(hotelLocator));
            hotel.click();
        }
    }
}
