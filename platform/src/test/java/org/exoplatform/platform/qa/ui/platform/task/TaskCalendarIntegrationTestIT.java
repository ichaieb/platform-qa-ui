package org.exoplatform.platform.qa.ui.platform.task;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.calendar.CalendarLocator.ELEMENT_CALENDAR_CONTAINER_WEEK_VIEW;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.calendar.pageobject.CalendarHomePage;
import org.exoplatform.platform.qa.ui.calendar.pageobject.EventManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.task.pageobject.ProjectsManagement;
import org.exoplatform.platform.qa.ui.task.pageobject.TasksManagement;

/**
 * Created by ilyes on 05/03/18.
 */
@Tag("task")
@Tag("sniff")
public class TaskCalendarIntegrationTestIT extends Base {
  HomePagePlatform   homePagePlatform;

  TasksManagement    tasksManagement;

  ManageLogInOut     manageLogInOut;

  ProjectsManagement projectsManagement;

  EventManagement    eventManagement;

  CalendarHomePage   calendarHomePage;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    tasksManagement = new TasksManagement(this);
    projectsManagement = new ProjectsManagement(this);
    manageLogInOut = new ManageLogInOut(this);
    calendarHomePage = new CalendarHomePage(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
    eventManagement = new EventManagement(this);
  }

  @Test
  @Tag("TA-617")
  public void test01_checkTaskAddedInProjectAppearsInCalendar() {

    String project = "project" + getRandomNumber();
    String taskName = "task" + getRandomNumber();
    homePagePlatform.goToTaskPage();
    info("add project");
    projectsManagement.addProject(project, "", true);
    $(byText(project)).click();
    tasksManagement.addTask(taskName);
    ELEMENT_WORK_PLANED_FIELD.click();
    if (ELEMENT_CHECKBOX_ALL_DAY.is(Condition.selected))
      ELEMENT_CHECKBOX_ALL_DAY.parent().waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).click();
    do {
      ELEMENT_INPUT_FROM_TIME.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(getDate(0, "HH" + ":00"));
      ELEMENT_INPUT_TO_TIME.waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).setValue(getDate(0, "HH" + ":30"));
      $(byXpath("(//*[@class='highLight today'])[1]")).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).pressEnter();
      $(byXpath("(//*[@class='highLight today'])[2]")).waitUntil(Condition.visible, Configuration.openBrowserTimeoutMs).pressEnter();
    }while (!$(byXpath("//*[text()=\"Work planned for \"]")).waitUntil(Condition.visible,Configuration.openBrowserTimeoutMs).isDisplayed());
    homePagePlatform.goToCalendarPage();
    calendarHomePage.goToView(CalendarHomePage.selectViewOption.WEEK);
    ELEMENT_CALENDAR_CONTAINER_WEEK_VIEW.find(byText(taskName)).parent().shouldHave((Condition.text(getDate(0, "HH" + ":00"))));
    homePagePlatform.goToTaskPage();
    projectsManagement.deleteProject(project);
  }
}
