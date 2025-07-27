package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public void setDestination(String location) throws InterruptedException {
        WebElement input = driver.findElement(By.xpath("//input[@id=':rh:']"));
        input.sendKeys(location);
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[id='autocomplete-result-0'] div[role='button']"))).click();
    }

    public void selectDates(String checkInDate, String checkOutDate) {
        driver.findElement(By.xpath("//button[@data-testid='searchbox-dates-container']")).click();
        driver.findElement(By.xpath("//button[@aria-label='Next month']")).click();
        driver.findElement(By.xpath("//button[@aria-label='Next month']")).click();
        driver.findElement(By.xpath("//span[@data-date='" + checkInDate + "']")).click();
        driver.findElement(By.xpath("//span[@data-date='" + checkOutDate + "']")).click();
    }

    public void submitSearch() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    public void scrollToLoadHotels() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 5; i++) {
            js.executeScript("window.scrollBy(0, 1000)");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}
        }
    }

    public void clickHotel(String hotelName) {
        WebElement hotel = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@data-testid='title' and contains(text(), '" + hotelName + "')]")));
        hotel.click();
    }
}
