package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;

public class HotelPage {
    WebDriver driver;
    WebDriverWait wait;

    public HotelPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void switchToNewTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public void configureRoomSelection() {
        wait.until(webDriver -> {
            ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0, 1000)");
            return true;
        });

        By bedPreferenceLocator = By.cssSelector("input[value='1'][name='bedPreference_78883120']");
        WebElement bedPreference = wait.until(ExpectedConditions.elementToBeClickable(bedPreferenceLocator));
        bedPreference.click();

        By quantityDropdown = By.cssSelector("#hprt_nos_select_78883120_386871369_0_33_0_131741");
        WebElement selectField = wait.until(ExpectedConditions.elementToBeClickable(quantityDropdown));
        selectField.click();
        selectField.sendKeys("1");
        selectField.sendKeys(Keys.ENTER);

        By reserveButton = By.xpath("//button[contains(@class,'hp_rt_input px--fw-cta js-reservation-button')]");
        WebElement reserve = wait.until(ExpectedConditions.elementToBeClickable(reserveButton));
        reserve.click();
    }

    public String getHotelName() {
        By locator = By.xpath("//h1[@class='e7addce19e']");
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, "Tolip Hotel Alexandria"));
        return driver.findElement(locator).getText().trim();
    }

    public boolean isCheckInDateVisible(String date) {
        return driver.findElement(By.xpath("//time[@datetime='" + date + "']")).isDisplayed();
    }

    public boolean isCheckOutDateVisible(String date) {
        return driver.findElement(By.xpath("//time[@datetime='" + date + "']")).isDisplayed();
    }
}
