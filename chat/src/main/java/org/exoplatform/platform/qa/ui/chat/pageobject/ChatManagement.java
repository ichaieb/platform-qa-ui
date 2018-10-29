package org.exoplatform.platform.qa.ui.chat.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static junit.framework.TestCase.assertEquals;
import static org.exoplatform.platform.qa.ui.selenium.locator.chat.ChatLocator.*;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.TestBase;

public class ChatManagement {
  private final TestBase testBase;

  public ChatManagement(TestBase testBase) {
    this.testBase = testBase;
  }

  public void sendMessageInRoomOrSpace(String room, String message) {
    $(byText(room)).click();
    ELEMENT_CHAT_MESSAGE_INPUT.setValue(message).pressEnter();
    ELEMENT_CHAT_LIST_MSG.find(byText(message)).should(Condition.exist);
  }

  public void uploadFile(String file) {
    ELEMENT_COLLABORATION_ACTIONS.click();
    ELEMENT_CHAT_UPLOAD_FILE.click();
    ELEMENT_CHAT_INPUT_UPLOAD.uploadFromClasspath(file);
    ELEMENT_CONTAINER_LIST_MESSAGES.find(byText(file)).waitUntil(Condition.appear,Configuration.timeout);
    ELEMENT_CONTAINER_LIST_MESSAGES.find(byClassName("uiIconChatUpload")).waitUntil(Condition.appear, Configuration.timeout);
  }

  public void changeStatus(String status) {
    ELEMENT_ICON_CHAT.click();
    switch (status) {
    case "Available":
      ELEMENT_CHAT_STATUS_AVAILABLE.click();
      break;
    case "Do not disturb":
      ELEMENT_CHAT_STATUS_DONOTDISTURB.click();
      break;
    case "Away":
      ELEMENT_CHAT_STATUS_AWAY.click();
      break;
    case "Invisible":
      ELEMENT_CHAT_STATUS_INVISIBLE.click();
      break;
    }
  }

  public void assignTaskInChat(String taskName, String DueDate, String... user) {
    ELEMENT_CHAT_MEETTING_ACTIONS.click();
    $(byClassName("meeting-action-task")).waitUntil(Condition.visible, Configuration.timeout).click();
    if (taskName != null || taskName != "")
      ELEMENT_CHAT_INPUT_TASKNAME.setValue(taskName);
    if (user.length > 0) {
      for (int i = 0; i < user.length; i++) {
        ELEMENT_CHAT_INPUT_USERNAME_IN_ASSIGN_TASK.setValue(user[i]).pressEnter();
      }
    }
    if (DueDate != null || DueDate != "")
      ELEMENT_CHAT_INPUT_DUE_TASK_DATE.setValue(DueDate);
    ELEMENT_BUTTON_ADD_TASK.click();
    ELEMENT_CONTAINER_LIST_MESSAGES.find(byLinkText(taskName)).shouldBe(Condition.visible);
  }

  public void addEventInChat(String event, String location){

    ELEMENT_COLLABORATION_ACTIONS.click();
    ELEMENT_CHAT_ADD_EVENT.click();
    ELEMENT_ADD_EVENT_WINDOW.waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_ADD_EVENT.setValue(event);
    ELEMENT_CHAT_EVENT_FROM_DATE_.click();
    $(byClassName("today")).click();
    ELEMENT_CHAT_EVENT_TO_DATE_.click();
    $(byClassName("today")).click();
    ELEMENT_CHAT_EVENT_LOCATION.setValue(location);
    ELEMENT_CHAT_POST_EVENT.click();
    ELEMENT_CONTAINER_LIST_MESSAGES.find(byText(event)).waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_CONTAINER_LIST_MESSAGES.find(byClassName("uiIconChatCreateEvent")).waitUntil(Condition.appear, Configuration.timeout);
  }

  public void checkMenuCollaborativeAction(){

    ELEMENT_COLLABORATION_ACTIONS.should(Condition.exist).click();
    ELEMENT_CHAT_COLLABORATION_MENU.waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_CHAT_ADD_EVENT.should(Condition.visible);
    ELEMENT_CHAT_SHARE_LINK.should(Condition.visible);
    ELEMENT_CHAT_UPLOAD_FILE.should(Condition.visible);
    ELEMENT_CHAT_ASK_QUESTION.should(Condition.visible);
    ELEMENT_CHAT_RAISE_HAND.should(Condition.visible);
    ELEMENT_CHAT_CREATE_TASK.should(Condition.visible);
  }

  public void checkAskQuestionPopUp(){
    ELEMENT_POPUP_CONTAINER.find(byText("Ask a Question")).waitUntil(Condition.appear,Configuration.timeout);
    ELEMENT_POPUP_CONTAINER.findElement(byXpath("//input[@placeholder='What is your question?']")).getAttribute("placeholder");
    assertEquals("Ask",ELEMENT_CHAT_ASK_BUTTON.getText());
    assertEquals("Cancel", ELEMENT_CHAT_CANCEL_BUTTON.getText());
    ELEMENT_CHAT_CANCEL_BUTTON.click();
    ELEMENT_POPUP_CONTAINER.find(byText("Ask a Question")).shouldNot(Condition.appear);
  }

  public void addQuestionInChat(String question){
    ELEMENT_COLLABORATION_ACTIONS.click();
    ELEMENT_CHAT_ASK_QUESTION.click();
    ELEMENT_POPUP_CONTAINER.findElement(byXpath("//input[@placeholder='What is your question?']")).sendKeys(question+"?");
    ELEMENT_CHAT_ASK_BUTTON.click();
    ELEMENT_CONTAINER_LIST_MESSAGES.find(byText(question+"?")).waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_CONTAINER_LIST_MESSAGES.find(byClassName("uiIconChatQuestion")).waitUntil(Condition.appear, Configuration.timeout);
  }


  public void checkAddEventPopUp(){
    ELEMENT_POPUP_CONTAINER.find(byText("Add Event")).waitUntil(Condition.appear,Configuration.timeout);
    ELEMENT_POPUP_CONTAINER.findElement(byXpath("//input[@placeholder='Event Title']")).getAttribute("placeholder");
    ELEMENT_POPUP_CONTAINER.find(byClassName("event-item")).parent().findAll(byClassName("action-label")).get(0)
            .shouldHave(Condition.text("From"))
            .findElement(byXpath("//input[@placeholder='mm/dd/yyyy']")).getAttribute("placeholder");
    ELEMENT_POPUP_CONTAINER.find(byClassName("event-item")).parent().findAll(byClassName("action-label")).get(0)
            .findElement(byXpath("//input[@placeholder='mm/dd/yyyy']")).click();
    ELEMENT_CHAT_EVENT_CALENDAR.should(Condition.appear);
    ELEMENT_POPUP_CONTAINER.find(byClassName("event-item")).parent().findAll(byClassName("action-label")).get(0)
            .findElement(byXpath("//input[@placeholder='mm/dd/yyyy']")).click();
    $(byClassName("chat-app-event")).click();
    ELEMENT_POPUP_CONTAINER.find(byClassName("event-item")).parent().findAll(byClassName("action-label")).get(1)
            .shouldHave(Condition.text("To"))
            .findElement(byXpath("//input[@placeholder='mm/dd/yyyy']")).getAttribute("placeholder");
    ELEMENT_POPUP_CONTAINER.find(byClassName("event-item")).parent().findAll(byClassName("action-label")).get(1)
            .findElement(byXpath("//input[@placeholder='mm/dd/yyyy']")).click();
    ELEMENT_CHAT_EVENT_CALENDAR.should(Condition.appear);
    ELEMENT_POPUP_CONTAINER.find(byClassName("event-item")).parent().findAll(byClassName("action-label")).get(1)
            .findElement(byXpath("//input[@placeholder='mm/dd/yyyy']")).click();
    ELEMENT_POPUP_CONTAINER.findElement(byXpath("//input[@placeholder='Location']")).getAttribute("placeholder");
    assertEquals("All Day",$("select[name=startTime]").getText());
    assertEquals("All Day",$("select[name=endTime]").getText());
    assertEquals("Post",ELEMENT_CHAT_POST_EVENT.getText());
    assertEquals("Cancel", ELEMENT_CHAT_CANCEL_BUTTON.getText());
    ELEMENT_CHAT_CANCEL_BUTTON.click();
    ELEMENT_POPUP_CONTAINER.find(byText("Add Event")).shouldNot(Condition.appear);
  }




  public void checkRaiseHandPopUp(){
    ELEMENT_POPUP_CONTAINER.find(byText("Raise Hand")).waitUntil(Condition.appear,Configuration.timeout);
    ELEMENT_POPUP_CONTAINER.findElement(byXpath("//input[@placeholder='Optional comment']")).getAttribute("placeholder");
    assertEquals("Raise your hand",ELEMENT_RAISE_HAND_BUTTON.getText());
    assertEquals("Cancel", ELEMENT_CHAT_CANCEL_BUTTON.getText());
    ELEMENT_CHAT_CANCEL_BUTTON.click();
    ELEMENT_POPUP_CONTAINER.find(byText("Raise Hand")).shouldNot(Condition.appear);
  }

  public void raiseHandInChat(String comment){
    ELEMENT_COLLABORATION_ACTIONS.click();
    ELEMENT_CHAT_RAISE_HAND.click();
    ELEMENT_POPUP_CONTAINER.findElement(byXpath("//input[@placeholder='Optional comment']")).sendKeys(comment);
    ELEMENT_RAISE_HAND_BUTTON.click();
    ELEMENT_CONTAINER_LIST_MESSAGES.find(byText(comment)).waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_CONTAINER_LIST_MESSAGES.find(byClassName("uiIconChatRaiseHand")).waitUntil(Condition.appear, Configuration.timeout);
  }

  public void checkUploadFilePopUp(){
    ELEMENT_POPUP_CONTAINER.find(byText("Upload File")).waitUntil(Condition.appear,Configuration.timeout);
    assertEquals("DROP YOUR FILE HERE",$(byClassName(" label-inner")).getText());
    assertEquals("Select Manually",ELEMENT_CHAT_SELECT_FILE.getText());
    ELEMENT_CHAT_CANCEL_UPLOAD_FILE_BUTTON.click();
    ELEMENT_POPUP_CONTAINER.find(byText("Upload File")).shouldNot(Condition.appear);
  }

  public void checkShareLinPopUp(){
    ELEMENT_POPUP_CONTAINER.find(byText("Share Link")).waitUntil(Condition.appear,Configuration.timeout);
    ELEMENT_POPUP_CONTAINER.findElement(byXpath("//input[@placeholder='E.g: http://www.exoplatform.com']")).getAttribute("placeholder");
    assertEquals("Share",ELEMENT_CHAT_SHARE_LINK_BUTTON.getText());
    assertEquals("Cancel", ELEMENT_CHAT_CANCEL_BUTTON.getText());
    ELEMENT_CHAT_CANCEL_BUTTON.click();
    ELEMENT_POPUP_CONTAINER.find(byText("Share Link")).shouldNot(Condition.appear);
  }

  public void shareLinkInChat(String link){
    ELEMENT_COLLABORATION_ACTIONS.click();
    ELEMENT_CHAT_SHARE_LINK.click();
    ELEMENT_POPUP_CONTAINER.findElement(byXpath("//input[@placeholder='E.g: http://www.exoplatform.com']")).sendKeys(link);
    ELEMENT_CHAT_SHARE_LINK_BUTTON.click();
    ELEMENT_CONTAINER_LIST_MESSAGES.find(byText(link)).waitUntil(Condition.appear, Configuration.timeout);
    ELEMENT_CONTAINER_LIST_MESSAGES.find(byClassName("uiIconChatLink")).waitUntil(Condition.appear, Configuration.timeout);
  }
}
