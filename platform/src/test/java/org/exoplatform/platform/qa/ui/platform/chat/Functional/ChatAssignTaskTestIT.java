package org.exoplatform.platform.qa.ui.platform.chat.Functional;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.chat.pageobject.RoomManagement;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.task.pageobject.ProjectsManagement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.ELEMENT_CHAT_RESULT_SEARCH_USER;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.taskmanagement.TaskManagementLocator.ELEMENT_TABLE_PROJECT;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

@Tag("chat")
@Tag("sniff")
public class ChatAssignTaskTestIT extends Base {

    HomePagePlatform       homePagePlatform;
    ManageLogInOut         manageLogInOut;
    UserAndGroupManagement userandgroupmanagement;
    UserAddManagement      userAddManagement;
    RoomManagement         roomManagement;
    ProjectsManagement     projectsManagement;


    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        homePagePlatform = new HomePagePlatform(this);
        userAddManagement = new UserAddManagement(this);
        userandgroupmanagement = new UserAndGroupManagement(this);
        roomManagement= new RoomManagement(this);
        projectsManagement=new ProjectsManagement(this);
        manageLogInOut = new ManageLogInOut(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        manageLogInOut.signInCas(PLFData.DATA_USER1, PLFData.DATA_PASS2 );
    }

     @Test
     public void test01_CheckTaskWithNoAssignee() throws InterruptedException {
        String room =  "room" +getRandomNumber();
        String task =  "task" +getRandomNumber();

         homePagePlatform.goToChat();
         switchTo().window(1);
         ELEMENT_CREATE_ROOM.waitUntil(Condition.appears, Configuration.timeout);
         ELEMENT_CREATE_ROOM.click();
         ELEMENT_POPUP_ROOM.waitUntil(Condition.appear, Configuration.timeout);
         ELEMENT_ROOM_NAME.setValue(room);
         ELEMENT_PEOPLE_ROOM.sendKeys("root");
         ELEMENT_BUTTON_SAVE_ROOM.click();
         ELEMENT_CONTACT_LIST.find(byText(room)).click();
         ELEMENT_COLLABORATION_ACTIONS.click();
         $(byClassName("uiIconChatCreateTask")).click();
         $(byId("taskTitle")).setValue(task);
         $(byXpath("//*[@id=\"appComposerForm\"]/div[2]/button")).click();
         switchToParentWindow();
         homePagePlatform.goToTaskPage();
         $(byText(room)).should(Condition.exist);
         $(byText(room)).click();
         ELEMENT_TABLE_PROJECT.parent().parent().parent().find(byText(task)).should(Condition.exist);
         projectsManagement.deleteProject(room);
         switchTo().window(1);
         roomManagement.deleteRomm(room);
     }

     @Test
    public void test02_CheckTaskWithAssignee(){

         String room =  "room" +getRandomNumber();
         String task =  "task" +getRandomNumber();

         homePagePlatform.goToChat();
         switchTo().window(1);
         ELEMENT_CREATE_ROOM.waitUntil(Condition.appears, Configuration.timeout);
         ELEMENT_CREATE_ROOM.click();
         ELEMENT_POPUP_ROOM.waitUntil(Condition.appear, Configuration.timeout);
         ELEMENT_ROOM_NAME.setValue(room);
         $(byXpath("//*[@id=\"chat-application\"]/div[1]/div[2]/div[4]/div[1]/div/div[2]/div[1]/div[1]/div/input")).setValue("root");
      //   ELEMENT_PEOPLE_ROOM.sendKeys("root");
         ELEMENT_CHAT_RESULT_SEARCH_USER.waitUntil(Condition.visible,Configuration.timeout);
         $(byXpath("//*[@id=\"chat-application\"]/div[1]/div[2]/div[4]/div[1]/div/div[2]/div[1]/div[1]/div/input")).pressEnter();
         ELEMENT_BUTTON_SAVE_ROOM.click();
         ELEMENT_CONTACT_LIST.find(byText(room)).click();
         ELEMENT_COLLABORATION_ACTIONS.click();
         $(byClassName("uiIconChatCreateTask")).click();
         $(byId("taskTitle")).setValue(task);
         $(byId("taskAssignee")).parent().parent().find(byClassName("selectize-input")).find(by("type","text")).sendKeys("root");

         $(byId("taskDueDate")).click();
         $(byClassName("today")).click();
         $(byXpath("//*[@id=\"appComposerForm\"]/div[2]/button")).click();
     }
}