package org.exoplatform.platform.qa.ui.platform.calendar;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.calender.CalendarLocator.ELEMENT_NEXT_RIGHT_LIST_DAY_BUTTON;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarManagement;
import org.exoplatform.platform.qa.ui.calendar.pageobject.EventManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;

/**
 * Created by ilyes on 06/11/17.
 */
@Tag("calendar")
@Tag("sniff")
public class CalendarBasicTestsWithUserTestIT extends Base {
  HomePagePlatform   homePagePlatform;

  CalendarManagement calendarManagement;

  ManageLogInOut     manageLogInOut;

  CalendarHomePage   calendarHomePage;

  EventManagement    eventManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    homePagePlatform = new HomePagePlatform(this);
    calendarManagement = new CalendarManagement(this);
    calendarHomePage = new CalendarHomePage(this);
    eventManagement = new EventManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(PLFData.DATA_USER2, PLFData.DATA_PASS);
  }

  @Test
  public void test02_AddPersonalCalendar() {
    String calendarName = "calendarName" + getRandomNumber();
    String calendarColor = "light_purple";
    info("Test 2: Add Personal Calendar");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendarName, calendarName, calendarColor);
    calendarManagement.saveAddCalendar();
    $(byText(calendarName)).waitUntil(Condition.appears, 10000);
    homePagePlatform.goToCalendarPage();
    calendarManagement.deleteCalendar(calendarName, true);
  }

  @Test
  public void test_03_EditPersonalCalendar() {
    String calendarName = "calendarName" + getRandomNumber();
    String newCalendar = "newCalendar" + getRandomNumber();
    String calendarColor = "light_purple";

    info("Test 2: Add Personal Calendar");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendarName, calendarName, calendarColor);
    calendarManagement.saveAddCalendar();
    $(byText(calendarName)).waitUntil(Condition.appears, 10000);
    info("Test 3: Edit Personal Calendar");
    calendarManagement.editCalendar(calendarName, newCalendar, newCalendar, calendarColor, null);
    calendarManagement.saveAddCalendar();
    $(byText(newCalendar)).waitUntil(Condition.appears, 10000);
    info("Test 4 Delete Personal Calendar");
    homePagePlatform.goToCalendarPage();
    calendarManagement.deleteCalendar(newCalendar, true);
  }

  @Test
  public void test04_DeletePersonalCalendar() {
    String calendarName = "calendarName" + getRandomNumber();
    String calendarColor = "light_purple";
    info("Test 2: Add Personal Calendar");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendarName, calendarName, calendarColor);
    calendarManagement.saveAddCalendar();
    $(byText(calendarName)).waitUntil(Condition.appears, Configuration.timeout);
    info("Test 4 Delete Personal Calendar");
    homePagePlatform.goToCalendarPage();
    calendarManagement.deleteCalendar(calendarName, true);
  }

  @Test
  public void test05_AddGroupCalendar() {
    String groupCalendar = "/platform/users";
    String calendarName = "calendarName" + getRandomNumber();
    String calendarColor = "light_purple";
    info("Test 5: Add Group Calendar");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendarName, calendarName, calendarColor);
    calendarManagement.selectGroupInGroupTabCalendarForm(groupCalendar, true);
    calendarManagement.saveAddCalendar();
    $(byText(calendarName)).waitUntil(Condition.appears, 10000);
    info("Test 7 Delete Group Calendar");
    homePagePlatform.goToCalendarPage();
    calendarManagement.deleteCalendar(calendarName, true);
  }

  @Test
  public void test06_EditGroupCalendar() {
    String groupCalendar = "/platform/users";
    String calendarName = "calendarName" + getRandomNumber();
    String newCalendar = "newCalendar" + getRandomNumber();
    String calendarColor = "light_purple";

    info("Test 5: Add Group Calendar");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendarName, calendarName, calendarColor);
    calendarManagement.selectGroupInGroupTabCalendarForm(groupCalendar, true);
    calendarManagement.saveAddCalendar();
    $(byText(calendarName)).waitUntil(Condition.appears, 10000);
    info("Test 6: Edit Group Calendar");
    calendarManagement.editCalendar(calendarName, newCalendar, newCalendar, calendarColor, null);
    calendarManagement.saveAddCalendar();
    $(byText(newCalendar)).waitUntil(Condition.appears, 10000);
    info("Test 7 Delete Group Calendar");
    homePagePlatform.goToCalendarPage();
    calendarManagement.deleteCalendar(newCalendar, true);
  }

  @Test
  public void test_07_DeleteGroupCalendar() {
    String groupCalendar = "/platform/users";
    String calendarName = "calendarName" + getRandomNumber();
    String calendarColor = "light_purple";

    info("Test 5: Add Group Calendar");
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendarName, calendarName, calendarColor);
    calendarManagement.selectGroupInGroupTabCalendarForm(groupCalendar, true);
    calendarManagement.saveAddCalendar();
    $(byText(calendarName)).waitUntil(Condition.appears, 10000);
    info("Test 7 Delete Group Calendar");
    homePagePlatform.goToCalendarPage();
    calendarManagement.deleteCalendar(calendarName, true);
  }

  @Test
  public void test13_AddnEventInPersonalCalendar() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String calendar = "calendar" + getRandomNumber();
    String contentEvent = "contentEvent" + getRandomNumber();
    info("Test 13 Add an event in personal calendar");

    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendar, calendar, null);
    calendarManagement.saveAddCalendar();
    calendarManagement.executeActionCalendar(calendar, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    info("Check default date");
    eventManagement.checkSuggestionEventTimeInQuickForm(null, null, 60);
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              contentEvent,
                                              getDate(0, "MM/dd/yyyy"),
                                              getDate(0, "MM/dd/yyyy"),
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.DETAILTIME);

    info("Test 15 Delete an Event in personal calendar");

    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.LIST,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, "MM/dd/yyyy"));
    calendarManagement.deleteCalendar(calendar, false);
  }

  @Test
  public void test14_EditAnEventInPersonalCalendar() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String calendar = "calendar" + getRandomNumber();
    String contentEvent = "contentEvent" + getRandomNumber();
    String titleEvent2 = "titleEvent2" + getRandomNumber();
    String contentEvent2 = "contentEvent2" + getRandomNumber();
    info("Test 13 Add an event in personal calendar");

    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendar, calendar, null);
    calendarManagement.saveAddCalendar();
    calendarManagement.executeActionCalendar(calendar, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    info("Check default date");
    eventManagement.checkSuggestionEventTimeInQuickForm(null, null, 60);
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              contentEvent,
                                              getDate(0, "MM/dd/yyyy"),
                                              getDate(0, "MM/dd/yyyy"),
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.DETAILTIME);

    info("Test 14 Edit an Event in personal calendar");

    calendarHomePage.goToEditEventTaskFormByRightClick(titleEvent,
                                                       CalendarHomePage.selectViewOption.WEEK,
                                                       CalendarHomePage.selectDayOption.DETAILTIME,
                                                       getDate(0, "MMM dd yyyy"));
    eventManagement.checkSuggestionEventTimeInDetailForm(null, null, 60);
    eventManagement.inputDataEventInDetailForm(titleEvent2,
                                               contentEvent2,
                                               getDate(0, "MM/dd/yyyy"),
                                               getDate(0, "MM/dd/yyyy"),
                                               false);
    eventManagement.saveAddEventDetails();
    calendarHomePage.verifyIsPresentEventTask(titleEvent2,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.DETAILTIME);

    info("Test 15 Delete an Event in personal calendar");

    calendarHomePage.deleteEventTask(titleEvent2,
                                     CalendarHomePage.selectViewOption.LIST,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, "MM/dd/yyyy"));
    calendarManagement.deleteCalendar(calendar, false);
  }

  @Test
  public void test15_DeletenEventInPersonalCalendar() {
    String titleEvent = "titleEvent" + getRandomNumber();
    String calendar = "calendar" + getRandomNumber();
    String contentEvent = "contentEvent" + getRandomNumber();
    info("Test 13 Add an event in personal calendar");

    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendar, calendar, null);
    calendarManagement.saveAddCalendar();
    calendarManagement.executeActionCalendar(calendar, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    info("Check default date");
    eventManagement.checkSuggestionEventTimeInQuickForm(null, null, 60);
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              contentEvent,
                                              getDate(0, "MM/dd/yyyy"),
                                              getDate(0, "MM/dd/yyyy"),
                                              false);
    eventManagement.saveQuickAddEvent();
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.DETAILTIME);

    info("Test 15 Delete an Event in personal calendar");

    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.LIST,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, "MM/dd/yyyy"));
    calendarManagement.deleteCalendar(calendar, false);
  }

  @Test
  public void test_AddnEventInPersonalCalendarWithSpecialCharacter() {
    String titleEvent = "l'event" + getRandomNumber();
    String calendar = "calendar" + getRandomNumber();
    String contentEvent = "contentEvent" + getRandomNumber();
    info("Test 13 Add an event in personal calendar");

    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendar, calendar, null);
    calendarManagement.saveAddCalendar();
    calendarManagement.executeActionCalendar(calendar, CalendarManagement.menuOfCalendarOption.ADDEVENT);
    info("Check default date");
    eventManagement.checkSuggestionEventTimeInQuickForm(null, null, 60);
    eventManagement.inputDataEventInQuickForm(titleEvent,
                                              contentEvent,
                                              getDate(0, "MM/dd/yyyy"),
                                              getDate(0, "MM/dd/yyyy"),
                                              false);
    eventManagement.saveQuickAddEvent();
    $(byText(titleEvent)).should(Condition.exist);
    calendarHomePage.verifyIsPresentEventTask(titleEvent,
                                              CalendarHomePage.selectViewOption.LIST,
                                              CalendarHomePage.selectDayOption.DETAILTIME);

    info("Test 15 Delete an Event in personal calendar");

    calendarHomePage.deleteEventTask(titleEvent,
                                     CalendarHomePage.selectViewOption.LIST,
                                     CalendarHomePage.selectDayOption.DETAILTIME,
                                     getDate(0, "MM/dd/yyyy"));
    calendarManagement.deleteCalendar(calendar, false);
  }

  @Test
  public void test19_EditEventWithValidData() {
    info("Test 19 Edit event with valid data");

    info("Create a new category");
    String categoryName = "categoryName" + getRandomNumber();
    homePagePlatform.goToCalendarPage();
    calendarManagement.addEventCategory(categoryName);

    info("Create a new calendar");
    String calendar = "calendar" + getRandomNumber();
    homePagePlatform.goToCalendarPage();
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendar, calendar, null);
    calendarManagement.saveAddCalendar();
    homePagePlatform.goToCalendarPage();
    $(byText(calendar)).waitUntil(Condition.appears, 10000);
    info("Add an Event");
    String titleEvent = "titleEvent" + getRandomNumber();
    String content = "content" + getRandomNumber();
    eventManagement.goToAddEventFromActionBar();
    eventManagement.inputDataEventInQuickForm(titleEvent, content, null, null, false, calendar);
    eventManagement.saveQuickAddEvent();

    String titleEvent2 = "titleEvent2" + getRandomNumber();
    calendarManagement.openEditEventTaskPopup(titleEvent, CalendarHomePage.selectViewOption.LIST);
    eventManagement.inputDataEventInDetailForm(titleEvent2, titleEvent2, null, null, false, null, categoryName);
    eventManagement.saveAddEventDetails();
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.LIST);
    if (($(byText(titleEvent2)).is(Condition.not(Condition.exist)))) {
      ELEMENT_NEXT_RIGHT_LIST_DAY_BUTTON.click();
      $(byText(titleEvent2)).waitUntil(Condition.appears, Configuration.timeout);
    } else {
      $(byText(titleEvent2)).waitUntil(Condition.appears, Configuration.timeout);
    }
    calendarManagement.deleteCalendar(calendar);
    calendarManagement.deleteEventCategory(categoryName);
  }

  @Test
  public void test03_AddNewCalendarWithValidInformation() {
    info("Test 3: Add new calendar with valid information");

    info("Create a new calendar");
    String calendar = "calendar" + getRandomNumber();
    homePagePlatform.goToCalendarPage();
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.WEEK);
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendar, calendar, null);
    calendarManagement.saveAddCalendar();
    homePagePlatform.goToCalendarPage();
    $(byText(calendar)).waitUntil(Condition.appears, Configuration.timeout);
    calendarManagement.deleteCalendar(calendar, true);
  }

  @Test
  public void test04_EditAPersonalCalendarWithValidValue() {
    info("Test 4: Edit a personal calendar with valid value");
    info("Create a new calendar");
    String calendar = "calendar" + getRandomNumber();
    homePagePlatform.goToCalendarPage();
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.WEEK);
    calendarManagement.goToMenuFromMainCalendar(CalendarManagement.menuOfMainCalendar.ADDCAL);
    calendarManagement.inputDataInDetailTabCalendarForm(calendar, calendar, null);
    calendarManagement.saveAddCalendar();
    homePagePlatform.goToCalendarPage();
    $(byText(calendar)).waitUntil(Condition.appears, Configuration.timeout);
    String calendar1 = "calendar1" + getRandomNumber();
    calendarManagement.editCalendar(calendar, calendar1, calendar1, "light_blue", null);
    calendarManagement.saveAddCalendar();
    homePagePlatform.goToCalendarPage();
    $(byText(calendar1)).waitUntil(Condition.appears, Configuration.timeout);
    $(byClassName("light_blue")).waitUntil(Condition.appears, Configuration.timeout);
    calendarManagement.deleteCalendar(calendar1, true);
  }
}