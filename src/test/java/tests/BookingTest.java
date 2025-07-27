package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.HomePage;
import Pages.HotelPage;
import utils.ExcelUtil;

public class BookingTest extends BaseTest {

    @Test
    public void testTolipHotelBookingFlow() throws InterruptedException {
        // Read dates from Excel
        String filePath = "C:\\Users\\fziad\\Downloads\\Booking\\src\\test\\resources\\data\\dates.xlsx";
        String checkIn = ExcelUtil.getCellValue(filePath, 1, 0);   // Row 1, Column 0
        String checkOut = ExcelUtil.getCellValue(filePath, 1, 1);  // Row 1, Column 1
        String location = ExcelUtil.getCellValue(filePath, 1, 2);  // Row 1, Column 2

        HomePage home = new HomePage(driver);
        HotelPage hotel = new HotelPage(driver);

        home.open();
        home.selectDates(checkIn, checkOut);
        home.setDestination(location);
        home.submitSearch();
        home.scrollToLoadHotels();
        home.clickHotel("Tolip Hotel Alexandria");

        hotel.switchToNewTab();
        hotel.configureRoomSelection();


        Assert.assertEquals(hotel.getHotelName(), "Tolip Hotel Alexandria", "Hotel name mismatch!");
        Assert.assertTrue(hotel.isCheckInDateVisible("Wed, Oct 1, 2025"), "Check-in date not visible!");
        Assert.assertTrue(hotel.isCheckOutDateVisible("Tue, Oct 14, 2025"), "Check-out date not visible!");
    }
}
