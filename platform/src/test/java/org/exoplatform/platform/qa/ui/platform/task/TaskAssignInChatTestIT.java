package org.exoplatform.platform.qa.ui.platform.task;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.chat.pageobject.RoomManagement;
import org.exoplatform.platform.qa.ui.core.context.BugInPLF;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.chat.pageobject.ChatManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.task.pageobject.TasksManagement;

/**
 * Created by exo on 06/03/18.
 */
@Tag("task")
@Tag("sniff")
public class TaskAssignInChatTestIT extends Base {
  HomePagePlatform homePagePlatform;

  ManageLogInOut   manageLogInOut;

  ChatManagement   chatManagement;

  TasksManagement  tasksManagement;
  RoomManagement   roomManagement;
  UserAndGroupManagement userandgroupmanagement;
  UserAddManagement     userAddManagement;
  NavigationToolbar     navigationToolbar;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut = new ManageLogInOut(this);
    manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
    chatManagement = new ChatManagement(this);
    tasksManagement = new TasksManagement(this);
    roomManagement = new RoomManagement(this);
    userAddManagement = new UserAddManagement(this);
    userandgroupmanagement = new UserAndGroupManagement(this);
    navigationToolbar= new NavigationToolbar(this);
  }

  @Test
  @Tag("TA-609")
  public void test01_checkAssignTaskInChatContainLinkToTaskApp() {
    String taskName = "task" + getRandomNumber();
    manageLogInOut.signIn(PLFData.DATA_USER2, PLFData.password);
    homePagePlatform.goToChat();
    manageLogInOut.signIn(PLFData.DATA_USER1, "gtngtn");
    homePagePlatform.goToChat();
    switchTo().window(1);
    ELEMENT_CHAT_INPUT_SEARCH_USER.setValue("@" + PLFData.DATA_USER2);
    $(byText(PLFData.DATA_NAME_USER2)).click();
    chatManagement.assignTaskInChat(taskName, "", PLFData.DATA_USER2);
    ELEMENT_CONTAINER_LIST_MESSAGES.find(byLinkText(taskName)).click();
    switchTo().window(2);
    assertEquals("Tasks", title());
    $(byText(taskName)).shouldBe(Condition.visible);
    tasksManagement.deleteTask(taskName);

  }
  @Test
  public void test02_AddTaskWithNoAssigneeInMessageComposerArea(){
    String taskName = "task" + getRandomNumber();
    String room= "room"+ getRandomNumber();
    homePagePlatform.goToChat();
    switchTo().window(1);
    roomManagement.addRoom(room);
    chatManagement.sendMessageAssignTaskInRoom(room,taskName);
    switchToParentWindow();
    homePagePlatform.goToTaskPage();
    $(byText(room)).should(Condition.exist);
    $(byText(room)).click();
    ELEMENT_TABLE_PROJECT.parent().parent().parent().find(byText(taskName)).should(Condition.exist).click();
    homePagePlatform.goToChat();
    switchTo().window(1);
    roomManagement.deleteRomm(room);
  }



  @BugInPLF("CHAT-985")
  public void test03_AddTaskInChatRoom(){
    String taskName = "task" + getRandomNumber();
    String room= "room"+ getRandomNumber();
    String username = "username" + getRandomString();
    String password = "123456";
    String email = "email" + getRandomNumber() + "@test.com";
    String FirstName = "FirstName"+getRandomString();
    String LastName = "LastName"+getRandomString();
    navigationToolbar.goToAddUser();
    userAddManagement.addUser(username, password, email, FirstName, LastName);
    homePagePlatform.goToChat();
    switchTo().window(1);
    roomManagement.addRoom(room,username);
    chatManagement.sendMessageAssignTaskInRoom(room,taskName,username);
    switchToParentWindow();
    homePagePlatform.goToTaskPage();
    $(byText(room)).should(Condition.exist);
    $(byText(room)).click();
    ELEMENT_TABLE_PROJECT.parent().parent().parent().find(byText(taskName)).should(Condition.exist).click();
    ELEMENT_ASSIGNEE_TASK.shouldHave(Condition.text(FirstName));
    manageLogInOut.signOut();
    manageLogInOut.signInCas(username,password);
    $(byClassName("uiIconStatus")).click();
    ELEMENT_CHAT_NOTIFICATION.find(byText(taskName)).should(Condition.appears);
    homePagePlatform.goToTaskPage();
    $(byText(room)).should(Condition.exist);
    $(byText(room)).click();
    ELEMENT_TABLE_PROJECT.parent().parent().parent().find(byText(taskName)).should(Condition.exist).click();
    ELEMENT_ASSIGNEE_TASK.shouldHave(Condition.text(FirstName));
  }

   @Test
  public void test04_AddTaskWithHighPriority(){
    String room= "room"+ getRandomNumber();
    String taskName = "task" + getRandomNumber();
    String priority= "High";
    String username = "username" + getRandomString();
    String password = "123456";
    String email = "email" + getRandomNumber() + "@test.com";
    String FirstName = "FirstName"+getRandomString();
    String LastName = "LastName"+getRandomString();
    navigationToolbar.goToAddUser();
    userAddManagement.addUser(username, password, email, FirstName, LastName);
    homePagePlatform.goToChat();
    switchTo().window(1);
    roomManagement.addRoom(room,username);
    chatManagement.sendMessageTaskPriority(room,taskName,priority);
    switchToParentWindow();
    homePagePlatform.goToTaskPage();
    $(byText(room)).should(Condition.exist);
    $(byText(room)).click();
    ELEMENT_TABLE_PROJECT.parent().parent().parent().find(byText(taskName)).should(Condition.exist).click();
    ELEMENT_PRIORITY_TASK.shouldHave(Condition.text("High"));
    switchTo().window(1);
    roomManagement.deleteRomm(room);
    switchToParentWindow();
    navigationToolbar.goToManageCommunity();
    userandgroupmanagement.deleteUser(username);
  }

  @Test
  public void test05_AddTaskWithLowPriority(){
    String room= "room"+ getRandomNumber();
    String taskName = "task" + getRandomNumber();
    String priority= "Low";
    String username = "username" + getRandomString();
    String password = "123456";
    String email = "email" + getRandomNumber() + "@test.com";
    String FirstName = "FirstName"+getRandomString();
    String LastName = "LastName"+getRandomString();
    navigationToolbar.goToAddUser();
    userAddManagement.addUser(username, password, email, FirstName, LastName);
    homePagePlatform.goToChat();
    switchTo().window(1);
    roomManagement.addRoom(room,username);
    chatManagement.sendMessageTaskPriority(room,taskName,priority);
    switchToParentWindow();
    homePagePlatform.goToTaskPage();
    $(byText(room)).should(Condition.exist);
    $(byText(room)).click();
    ELEMENT_TABLE_PROJECT.parent().parent().parent().find(byText(taskName)).should(Condition.exist).click();
    ELEMENT_PRIORITY_TASK.shouldHave(Condition.text("Low"));
    switchTo().window(1);
    roomManagement.deleteRomm(room);
    switchToParentWindow();
    navigationToolbar.goToManageCommunity();
    userandgroupmanagement.deleteUser(username);
  }


  @Test
  public void test06_AddTaskWithNormalPriority() {
    String room = "room" + getRandomNumber();
    String taskName = "task" + getRandomNumber();
    String priority = "Normal";
    String username = "username" + getRandomString();
    String password = "123456";
    String email = "email" + getRandomNumber() + "@test.com";
    String FirstName = "FirstName" + getRandomString();
    String LastName = "LastName" + getRandomString();
    navigationToolbar.goToAddUser();
    userAddManagement.addUser(username, password, email, FirstName, LastName);
    homePagePlatform.goToChat();
    switchTo().window(1);
    roomManagement.addRoom(room, username);
    chatManagement.sendMessageTaskPriority(room, taskName, priority);
    switchToParentWindow();
    homePagePlatform.goToTaskPage();
    $(byText(room)).should(Condition.exist);
    $(byText(room)).click();
    ELEMENT_TABLE_PROJECT.parent().parent().parent().find(byText(taskName)).should(Condition.exist).click();
    ELEMENT_PRIORITY_TASK.shouldHave(Condition.text("Normal"));
    switchTo().window(1);
    roomManagement.deleteRomm(room);
    switchToParentWindow();
    navigationToolbar.goToManageCommunity();
    userandgroupmanagement.deleteUser(username);
  }

  @BugInPLF("CHAT-985")
  public void test07_AddTaskWithAssigneeCoworkerInMessageComposerArea(){
    String taskName = "task" + getRandomNumber();
    String room= "room"+ getRandomNumber();
    String usernamea = "username" + getRandomString();
    String usernameb = "username" + getRandomString();
    String usernamec = "username" + getRandomString();
    String password = "123456";
    String emaila = "emaila" + getRandomNumber() + "@test.com";
    String FirstNameA = "FirstNameA"+getRandomString();
    String LastNameA = "LastNameA"+getRandomString();
    String emailb = "emailb" + getRandomNumber() + "@test.com";
    String FirstNameB = "FirstNameB"+getRandomString();
    String LastNameB = "LastNameB"+getRandomString();
    String emailc = "emailc" + getRandomNumber() + "@test.com";
    String FirstNameC = "FirstNameC"+getRandomString();
    String LastNameC = "LastNameC"+getRandomString();

    navigationToolbar.goToAddUser();
    userAddManagement.addUser(usernamea, password, emaila, FirstNameA, LastNameA);
    userAddManagement.addUser(usernameb, password, emailb, FirstNameB, LastNameB);
    userAddManagement.addUser(usernamec, password, emailc, FirstNameC, LastNameC);
    homePagePlatform.goToChat();
    switchTo().window(1);
    roomManagement.addRoom(room,usernamea,usernameb,usernamec);
    ELEMENT_CHAT_MESSAGE_INPUT.setValue("++"+taskName+" @"+usernamea+ " @"+usernameb+ " @"+usernamec).pressEnter();
    ELEMENT_CHAT_MESSAGE_INPUT.pressEnter();
    ELEMENT_CHAT_LIST_MSG.find(byText(taskName)).should(Condition.exist);
    switchToParentWindow();
    homePagePlatform.goToTaskPage();
    $(byText(room)).should(Condition.exist);
    $(byText(room)).click();
    ELEMENT_TABLE_PROJECT.parent().parent().parent().find(byText(taskName)).should(Condition.exist).click();
    ELEMENT_ASSIGNEE_TASK.shouldHave(Condition.text("    +2 coworkers")).click();
    ELEMENT_POPUP_ASSIGNEE_COWORKER.waitUntil(Condition.appears,Configuration.timeout);
    ELEMENT_CONTENT_ASSIGNEE_COWORKER.parent().parent().parent().find(byText(FirstNameA+" "+LastNameA))
        .should(Condition.exist);
    ELEMENT_CONTENT_ASSIGNEE_COWORKER.parent().parent().find(byText(FirstNameB+" "+LastNameB))
            .should(Condition.exist);
    ELEMENT_CONTENT_ASSIGNEE_COWORKER.parent().parent().find(byText(FirstNameC+" "+LastNameC))
            .should(Condition.exist);
    manageLogInOut.signOut();
    manageLogInOut.signInCas(usernamea,password);
    $(byClassName("uiIconStatus")).click();
    ELEMENT_CHAT_NOTIFICATION.find(byText(taskName)).should(Condition.appears);
    homePagePlatform.goToTaskPage();
    $(byText(room)).should(Condition.exist);
    $(byText(room)).click();
    ELEMENT_TABLE_PROJECT.parent().parent().parent().find(byText(taskName)).should(Condition.exist).click();
    ELEMENT_ASSIGNEE_TASK.shouldHave(Condition.text(FirstNameA));
  }
  }



