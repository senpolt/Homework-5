package tests.activities;


import pages.activities.CalendarEventsPage;
import tests.AbstractTestBase;
import utilities.BrowserUtils;
import utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import java.util.List;

public class CalendarEventsTests extends AbstractTestBase {
    public WebDriver driver = Driver.getDriver();

    SoftAssert sa= new SoftAssert();



    /**
     * Test Case #1
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Hover on three dots “…” for “Testers meeting”
     * calendar event
     * 5. Verify that “view”, “edit” and “delete” options
     * are available
     */
    @Test
    public void test1(){
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        test = report.createTest("Verify that “view”, “edit” and “delete” options");

        calendarEventsPage.navigateTo("Activities","Calendar Events");
        calendarEventsPage.hoverOverTreeDots();
        Assert.assertTrue(calendarEventsPage.view.isDisplayed());
        Assert.assertTrue(calendarEventsPage.edit.isDisplayed());
        Assert.assertTrue(calendarEventsPage.delete.isDisplayed());
        test.pass("view, edit and delete verified");

    }

    /**
     * Test Case #2
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Grid Options” button
     * 5. Deselect all options except “Title”
     * 6. Verify that “Title” column still displayed
     */
    @Test
    public void test2(){
        WebDriver driver = Driver.getDriver();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        test = report.createTest("Verify that “Title” column still displayed");

        calendarEventsPage.navigateTo("Activities","Calendar Events");
        calendarEventsPage.gridIcon.click();
        List<WebElement>  gridOptions = driver.findElements(By.xpath("//input[@data-role='renderable']"));
        for (int i = 1; i < gridOptions.size(); i++) {
            gridOptions.get(i).click();
            BrowserUtils.wait(1);
        }
        System.out.println(gridOptions.size());
        BrowserUtils.wait(3);
        Assert.assertTrue(calendarEventsPage.titleColumn.isDisplayed());
        test.pass("Title column verified");
    }

    /**
     * Test Case #3
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Expand “Save And Close” menu
     * 6. Verify that “Save And Close”, “Save And New”
     * and “Save” options are available
     */

    @Test
    public void test3(){
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        test = report.createTest("Verify that “Save And Close”, “Save And New  and “Save” ");


        calendarEventsPage.navigateTo("Activities","Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.saveAndCloseDropDown.click();

        Assert.assertEquals(calendarEventsPage.saveAndClose1.getText().trim(),"Save And Close");
        Assert.assertEquals(calendarEventsPage.saveAndNews2.getText().trim(),"Save And New");
        Assert.assertEquals(calendarEventsPage.save3.getText().trim(),"Save");

        test.pass("“Save And Close”, “Save And New  and “Save” verified");
    }

    /**
     * Test Case #4
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Then, click on “Cancel” button
     * 6. Verify that “All Calendar Events” page subtitle is
     * displayed
     */


    @Test
    public void test4(){
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.navigateTo("Activities","Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.cancelButton.click();
        Assert.assertTrue(calendarEventsPage.allCalendarEvents.isDisplayed());

    }

    /**
     * Test Case #5
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Verify that difference between end and start time
     * is exactly 1 hour
     */

    @Test
    public void test5(){
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.navigateTo("Activities","Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        Assert.assertTrue(calendarEventsPage.timeDiff()==1);
    }

    /**
     * Test Case #6
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “9:00 PM” as a start time
     * 6. Verify that end time is equals to “10:00 PM”
     */

    @Test
    public void test6(){
        WebDriver driver = Driver.getDriver();
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.navigateTo("Activities","Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.startTime.click();
        Actions actions = new Actions(driver);
        BrowserUtils.wait(4);
        actions.moveToElement(calendarEventsPage.time900Pm).click().perform();
        BrowserUtils.wait(2);
        Assert.assertEquals(calendarEventsPage.getEndTime(),"10:00 PM");
    }

    /**
     * Test Case #7
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “All-Day Event” checkbox
     * 6. Verify that “All-Day Event” checkbox is selected
     * 7. Verify that start and end time input boxes are
     * not displayed
     * 8. Verify that start and end date input boxes are
     * displayed
     */

    @Test
    public void test7() {
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.allDayCheckBox.click();
        Assert.assertTrue(calendarEventsPage.allDayCheckBox.isSelected());
        Assert.assertTrue(calendarEventsPage.startTime.isDisplayed());
        Assert.assertTrue(calendarEventsPage.endTime.isDisplayed());
        Assert.assertTrue(calendarEventsPage.startDate.isEnabled());
        Assert.assertTrue(calendarEventsPage.endDate.isEnabled());
    }


    /**
     * Test Case #8
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “Repeat” checkbox
     * 6. Verify that “Repeat” checkbox is selected
     * 7. Verify that “Daily” is selected by default and
     * following options are available in
     * “Repeats” drop-down:
     */

    @Test
    public void test8(){
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.repeatCheckBox.click();

        Assert.assertTrue(calendarEventsPage.repeatCheckBox.isSelected());

        Select select = new Select(calendarEventsPage.repeatsSelector);
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Daily");
        List<WebElement> repeatsOptions = select.getOptions();

        Assert.assertEquals(repeatsOptions.get(0).getText(),"Daily");
        Assert.assertEquals(repeatsOptions.get(1).getText(),"Weekly");
        Assert.assertEquals(repeatsOptions.get(2).getText(),"Monthly");
        Assert.assertEquals(repeatsOptions.get(3).getText(),"Yearly");

    }


    /**
     * Test Case #9
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “Repeat” checkbox
     * 6. Verify that “Repeat” checkbox is selected
     * 7. Verify that “Repeat Every” radio button is
     * selected
     * 8. Verify that “Never” radio button is selected as an
     * “Ends” option.
     * 9. Verify that following message as a summary is
     * displayed: “Summary: Daily every 1 day”
     */

    @Test
    public void test9(){
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.repeatCheckBox.click();

        Assert.assertTrue(calendarEventsPage.repeatCheckBox.isSelected());
        Assert.assertTrue(calendarEventsPage.repeatEveryDays.isSelected());
        Assert.assertTrue(calendarEventsPage.endsNever.isSelected());
        String expected ="Summary: Daily every 1 day";
        String actual = calendarEventsPage.summary.getText()+" "+calendarEventsPage.summary1.getText();
        Assert.assertEquals(actual,expected);

    }

    /**
     * Test Case #10
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “Repeat” checkbox
     * 6. Select “After 10 occurrences” as an “Ends”
     * option.
     * 7. Verify that following message as a summary is
     * displayed: “Summary: Daily every 1 day, end
     * after 10 occurrences”
     */

    @Test
    public void test10(){
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        test = report.createTest("Verify Summary: Daily every 1 day, end after 10 occurrences");


        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.repeatCheckBox.click();

        calendarEventsPage.endsAfter.click();
        calendarEventsPage.afterOccurrences.sendKeys("10",Keys.ENTER);

        String expected ="Summary: Daily every 1 day, end after 10 occurrences";
        String actual = calendarEventsPage.summary.getText()+
                " "+calendarEventsPage.summary1.getText()+
                calendarEventsPage.summary2.getText();
        Assert.assertEquals(actual,expected);

        test.pass("Verified");
    }


    /**
     * Test Case #11
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “Repeat” checkbox
     * 6. Select “By Nov 18, 2021” as an “Ends” option.
     * 7. Verify that following message as a summary is
     * displayed: “Summary: Daily every 1 day, end by
     * Nov 18, 2021”
     */


    @Test
    public void test11(){
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.repeatCheckBox.click();

        calendarEventsPage.byDatePicker.click();
        calendarEventsPage.datePicker.click();
        Select selectMonth = new Select(calendarEventsPage.datePickerMonth);
        selectMonth.selectByVisibleText("Nov");
        Select selectYear = new Select(calendarEventsPage.datePickerYear);
        selectYear.selectByVisibleText("2021");
        calendarEventsPage.datePickerDay("18").click();

        String expected ="Summary: Daily every 1 day, end by Nov 18, 2021";

        String actual =         calendarEventsPage.summary.getText()+
                " "+    calendarEventsPage.summary1.getText()+
                calendarEventsPage.byDatePickerOccurrence.getText();

        Assert.assertEquals(actual,expected);
    }

    /**
     * Test Case #12
     * 1. Go to “https://qa1.vytrack.com/"
     * 2. Login as a store manager
     * 3. Navigate to “Activities -> Calendar Events”
     * 4. Click on “Create Calendar Event” button
     * 5. Select “Repeat” checkbox
     * 6. Select “Weekly” options as a “Repeat” option
     * 7. Select “Monday and Friday” options as a
     * “Repeat On” options
     * 8. Verify that “Monday and Friday” options are
     * selected
     * 9. Verify that following message as a summary is
     * displayed: “Summary: Weekly every 1 week on
     * Monday, Friday”
     */

    @Test
    public void test12(){
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.repeatCheckBox.click();
        Select select = new Select(calendarEventsPage.repeatsSelector);
        select.selectByVisibleText("Weekly");
        calendarEventsPage.weekDayCheckBox("M").click();
        calendarEventsPage.weekDayCheckBox("F").click();
        String expected = "Summary: Weekly every 1 week on Monday, Friday";
        String actual = calendarEventsPage.summary.getText()
                +" " +calendarEventsPage.summary1.getText();
        Assert.assertEquals(actual,expected);


    }

}
