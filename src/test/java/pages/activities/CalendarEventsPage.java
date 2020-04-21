package pages.activities;


import pages.AbstractPageBase;
import utilities.BrowserUtils;
import utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CalendarEventsPage extends AbstractPageBase {
    public WebDriver driver = Driver.getDriver();


    @FindBy(css = "[title='Create Calendar event']")
    private WebElement createCalendarEvent;

    @FindBy(className = "select2-chosen")
    private WebElement owner;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_start']")
    public WebElement startTime;

    @FindBy(xpath = "//li[text()='9:00 PM']")
    public WebElement time900Pm;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_end']")
    public WebElement endTime;

    @FindBy(className = "grid-header-cell__label")
    private List<WebElement> columnNames;

    @FindBy(css = "iframe[id^='oro_calendar_event_form_description-uid']")
    private WebElement descriptionFrame;

    @FindBy(css = "[id^='oro_calendar_event_form_title-uid']")
    private WebElement title;

    @FindBy(id = "tinymce")
    private WebElement descriptionTextArea;

    @FindBy(css = "[class='btn-group pull-right'] > button")
    private WebElement saveAndClose;

    @FindBy(xpath = "(//div[@class='control-label'])[1]")
    private WebElement generalInfoTitle;

    @FindBy(xpath = "//label[text()='Description']/following-sibling::div//div")
    private WebElement generalInfoDescription;


    public void enterCalendarEventTitle(String titleValue) {
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(title)).sendKeys(titleValue);
    }

    public void enterCalendarEventDescription(String description) {
        //wait until frame is available and switch to it
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(descriptionFrame));
        descriptionTextArea.sendKeys(description);
        driver.switchTo().defaultContent();//exit from the frame
    }

    public void clickOnSaveAndClose() {

        wait.until(ExpectedConditions.elementToBeClickable(saveAndClose)).click();
    }

    public String getGeneralInfoTitleText() {
        BrowserUtils.waitForPageToLoad(20);
        return generalInfoTitle.getText();
    }

    public String getGeneralInfoDescriptionText() {
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[text()='Description']/following-sibling::div//div")));
        return generalInfoDescription.getText();
    }

    //#############################################################
    public List<String> getColumnNames() {
        BrowserUtils.waitForPageToLoad(20);
        return BrowserUtils.getTextFromWebElements(columnNames);
    }

    public String getStartTime() {
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[id^='time_selector_oro_calendar_event_form_start']")));
        wait.until(ExpectedConditions.visibilityOf(startTime));
        return startTime.getAttribute("value");
    }

    public String getEndTime() {
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(endTime));
        return endTime.getAttribute("value");
    }

    public String getOwnerName() {
        BrowserUtils.waitForPageToLoad(20);
        //wait for element to be present in DOM
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("select2-chosen")));
        wait.until(ExpectedConditions.visibilityOf(owner));
        return owner.getText().trim();
    }

    public void clickToCreateCalendarEvent() {
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[title='Create Calendar event']")));
        wait.until(ExpectedConditions.elementToBeClickable(createCalendarEvent)).click();
        BrowserUtils.waitForPageToLoad(20);
        BrowserUtils.wait(2);
    }

    public String getStartDate() {
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(startDate));
        BrowserUtils.scrollTo(startDate);
        return startDate.getAttribute("value");
    }


    @FindBy(xpath ="//*[@href='/calendar/event/view/1846']")
    public WebElement view;
    @FindBy(xpath = "//*[@href='/calendar/event/update/1846']")
    public WebElement edit;
    @FindBy(xpath = "//*[@href='#']")
    public WebElement delete;


    @FindBy(xpath = "//*[@title=\"Grid Settings\"]")
    public WebElement gridIcon;



    @FindBy(xpath = "//span[text()='Title']")
    public WebElement titleColumn;

    @FindBy(xpath = "(//*[@data-toggle='dropdown'])[4]")
    public WebElement saveAndCloseDropDown;

    @FindBy(xpath = "//ul//li//button[contains(text(),'Save and Close')]")
    public WebElement saveAndClose1;
    @FindBy(xpath = "//ul//li//button[contains(text(),'Save and New')]")
    public WebElement saveAndNews2;
    @FindBy(xpath = "(//ul//li//button[contains(text(),Save)])[3]")
    public WebElement save3;

    @FindBy(xpath = "//*[@title='Cancel']")
    public WebElement cancelButton;

    @FindBy(className = "oro-subtitle")
    public WebElement allCalendarEvents;


    @FindBy(xpath = "(//label[@class='required'])[3]")
    public WebElement start;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_start']")
    public WebElement startDate;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_end']")
    public WebElement endDate;
    @FindBy (css ="[id^='oro_calendar_event_form_allDay']")
    public WebElement allDayCheckBox;
    @FindBy(css ="[id^='recurrence-repeat-view']")
    public WebElement repeatCheckBox;

    @FindBy(css ="[id^='recurrence-repeats-view']")
    public WebElement repeatsSelector;

    @FindBy(xpath = "//input[@checked='checked']")
    public WebElement repeatEveryDays;

    @FindBy(xpath = "(//input[@type='radio'])[3]")
    public WebElement endsNever;

    @FindBy(xpath = "(//input[@type='radio'])[4]")
    public WebElement endsAfter;

    @FindBy(xpath = "(//*[@class=\"recurrence-subview-control__number\"])[7]")
    public WebElement afterOccurrences;

    @FindBy(xpath = "//label[text()='Summary:']")
    public WebElement summary;
    @FindBy(xpath = "//div[@data-name='recurrence-summary']//div/span[1]")
    public WebElement summary1;

    @FindBy ( xpath= "//div[@data-name='recurrence-summary']//div/span[2]")
    public WebElement summary2;

    @FindBy(xpath = "(//input[@type=\"radio\"])[5]")
    public WebElement byDatePicker;
    @FindBy(xpath = "//*[@class='datepicker-input hasDatepicker']")
    public WebElement datePicker;
    @FindBy(className = "ui-datepicker-month")
    public WebElement datePickerMonth;
    @FindBy(className = "ui-datepicker-year")
    public WebElement datePickerYear;

    @FindBy(xpath = "//div//span[text()='Daily every 1 day']/following-sibling::span")
    public WebElement byDatePickerOccurrence;

    public WebElement datePickerDay(String dayNum){
        String xpath = "//a[text()='"+dayNum+"']";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    public WebElement weekDayCheckBox(String firstLetterOfDay){
        return Driver.getDriver().findElement(By.xpath("//span[text()='"+firstLetterOfDay+"']"));
    }
    @FindBy(xpath = "//td[text()='Testers Meeting']//following-sibling::td//a[text()='...']")
    public WebElement  treeDots;
    public void hoverOverTreeDots(){

        Actions actions = new Actions(driver);
        BrowserUtils.wait(3);
        actions.moveToElement(treeDots).
                pause(2000).perform();

    }

    public Integer timeDiff(){
        String startTime = getStartTime();
        String endTime =getEndTime();

        String [] start =startTime.split(":");
        int startValue = Integer.parseInt(start[0]);

        String [] end =endTime.split(":");
        int endValue = Integer.parseInt(end[0]);
        if (startValue==12){
            startValue-=12;
        }

        return endValue-startValue;
    }
}
