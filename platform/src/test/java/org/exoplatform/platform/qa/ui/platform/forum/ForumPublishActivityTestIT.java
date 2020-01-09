package org.exoplatform.platform.qa.ui.platform.forum;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.password;
import static org.exoplatform.platform.qa.ui.core.PLFData.username;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_ACTIVITY_COMMENT;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_ACTIVITY_TOPIC_VIEW_LAST_REPLY;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.ELEMENT_COMMENT_BLOC;
import static org.exoplatform.platform.qa.ui.selenium.locator.NavigationToolBarLocator.ELEMENT_TOOLBAR_ADMINISTRATION;
import static org.exoplatform.platform.qa.ui.selenium.locator.forum.ForumLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumCategoryManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumHomePage;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumTopicManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;

/**
 * @author eXo
 */
@Tag("forum")
@Tag("sniff")
public class ForumPublishActivityTestIT extends Base {

  HomePagePlatform        homePagePlatform;

  ForumForumManagement    forumForumManagement;

  ForumTopicManagement    forumTopicManagement;

  ForumHomePage           forumHomePage;

  ForumCategoryManagement forumCategoryManagement;

  ActivityStream          activityStream;

  ManageLogInOut          manageLogInOut;

  String                  nameCat;

  String                  nameForum;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    forumForumManagement = new ForumForumManagement(this);
    forumTopicManagement = new ForumTopicManagement(this);
    forumHomePage = new ForumHomePage(this);
    forumCategoryManagement = new ForumCategoryManagement(this);
    activityStream = new ActivityStream(this);
    manageLogInOut = new ManageLogInOut(this);
    if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
      $(ELEMENT_SKIP_BUTTON).click();
    }
     manageLogInOut.signInCas(username, password);
  }



  /**
   * Prepare data test Create a new category,a new forum
   */
  public void prepareDataTest() {
    info("Create a forum for preparing testing");
    nameCat = "Category" + getRandomNumber() + "des";
    nameForum = "Forum" + getRandomNumber() + "des";
    homePagePlatform.goToForum();
    forumCategoryManagement.addCategorySimple(nameCat, "", nameCat);
    forumForumManagement.addForumSimple(nameForum, "", nameForum);
    info("Fnished preparing data test");
  }

  /**
   * Delete all data test Delete category, forum and topic
   */
  public void deleteDataTest() {
    info("Delete data test");
    $(ELEMENT_TOOLBAR_ADMINISTRATION).waitUntil(Condition.visible,Configuration.timeout).click();
    executeJavaScript("window.scrollBy(0,-550)");
    sleep(Configuration.timeout);
    homePagePlatform.goToForum();
    forumHomePage.goToHomeCategory();
    info("Delete catefory");
    $(byText(nameCat)).waitUntil(Condition.visible,Configuration.timeout).click();
    forumCategoryManagement.deleteCategory(nameCat);
    info("Finished deleting data test");
  }

  /**
   * <li>Case ID:116778.</li>
   * <li>Test Case Name: Check activity after move a topic.</li>
   * <li>Pre-Condition: Topic activity is existed</li>
   * <li>Post-Condition:</li>
   * <li>Done with OSs and browsers :</li>
   * <li>Generated by rosso at 2015/01/22 14:38:18</li> Step Number: 1 Step Name:
   * Create a category, forum, topic Step Description: - Create a category -
   * Create a forum - Create a topic Input Data: Expected Outcome: Category,
   * forum, topic are created successfully Step number: 2 Step Name: Move a topic
   * Step Description: - Open topic above - Click More Action >’ Move - Choose the
   * destination forum Input Data: Expected Outcome: - Topic is moved to
   * destination Forum successfully - In activity stream, a comment is added into
   * activity,message is "Topic has been moved to: $value.Where $value is
   * :Space>Category>Forum...
   */

  @Test
  public void test01_CheckActivityAfterMoveATopic() throws Exception {
    info("Test 01: Check activity after move a topic");
    info("Create data test");
    prepareDataTest();
    String Forum = "Forum" + getRandomNumber();
    String Cat = "Category" + getRandomNumber();
    String Topic = "Topic" + getRandomNumber();
    String comment = "Topic have been moved to: " + nameCat + ">" + nameForum;
    info("Finishing creating data test");

    homePagePlatform.goToForum();
    forumHomePage.goToHomeCategory();
    info("Create a second Category");
    forumCategoryManagement.addCategorySimple(Cat, "", Cat);
    info("Create a second forum");
    forumForumManagement.addForumSimple(Forum, "", Forum);

    info("Start a topic in second forum of second Category");
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(Topic, Topic, "", "");

    info("Move Topic:" + Topic);
    forumHomePage.goToTopic(Topic);
    forumTopicManagement.selectItemMoreActionMenuTopic(ForumTopicManagement.specifMoreActionMenuTopic.MOVE);
    info("Move the topic to a forum");
    forumTopicManagement.moveTopicToForum(nameCat, nameForum);
    info("Verify that the forum is moved to new category");
    $(byText(Forum)).should(Condition.exist);
    $(byText(nameCat)).should(Condition.exist);
    $(byText(Topic)).should(Condition.exist);
    info("Verify that the topic's activity is shown on intranet");
    homePagePlatform.goToHomePage();
    homePagePlatform.refreshUntil($(byText(comment)),Condition.exist,1000);
    $(byText(comment)).should(Condition.exist);
    info("The activity is shown successfully");
    $(byText(Topic)).should(Condition.exist);
    info("Delete Catégorie");
    homePagePlatform.goToForum();
    forumHomePage.goToHomeCategory();
    $(byText(Cat)).click();
    forumCategoryManagement.deleteCategory(Cat);
    $(byText(nameCat)).click();
    forumCategoryManagement.deleteCategory(nameCat);
    info("Test01: finished testing");
  }

  /**
   * <li>Case ID:116779.</li>
   * <li>Test Case Name: Check topic activity when creating new topic.</li>
   * <li>Pre-Condition: - Topic activity is existed</li> Step Number: 1 Step Name:
   * - Create new category Step Description: - Login and goto Forum application -
   * Click [Add Category] - Fill the information and click [Save] Input Data:
   * Expected Outcome: - New category is created - No activity is added in
   * activity stream Step number: 2 Step Name: - Create new Forum Step
   * Description: - Click [Add Forum] - Fill the information and click [Save]
   * Input Data: Expected Outcome: - New forum is created - No activity is added
   * in activity stream Step number: 3 Step Name: - Create new Topic Step
   * Description: - Click [start Topic] - input the information and click [Save]
   * Input Data: Expected Outcome: - New Topic is created - An activity is added
   * into activity stream - Informations that are displayed in the featured
   * content :1. Topic's title2. Rating average over the Topic3. First 4 lines of
   * the topic content4. Number of replies
   */

  @Test
  public void test02_CheckTopicActivityWhenCreatingNewTopic() throws Exception {
    info("Test 2: Check topic activity when creating new topic");
    info("Create data test for test 2");
    String topic1 = "Topic" + getRandomNumber();
    info("Finish creating data test for test 2");

    prepareDataTest();
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic1, topic1, "", "");
    homePagePlatform.goToHomePage();
    info("clear cache and reconnect");
    homePagePlatform.refreshUntil($(byText(topic1)),Condition.exist,1000);
    activityStream.checkActivity(topic1);
    deleteDataTest();
    info("Test 2: Finished testing");

  }

  /**
   * <li>Case ID:116780.</li>
   * <li>Test Case Name: Check activity after update topic title.</li>
   * <li>Pre-Condition: - Topic activity is existed</li>
   */
  @Test
  public void test03_CheckTopicActivityAfterUpdateTopicTitle() throws Exception {
    info("Test 3: Check activity after update topic title");
    info("Create data test for test 3");
    String topic1 = "Topic1" + getRandomNumber();
    String topicNewName = "TopicName" + getRandomNumber();
    String comment = "Title has been updated to: ";
    info("Finished Creating data test for test 3");
    prepareDataTest();
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic1, topic1, "", "");
    info("Edit topic:" + topic1);
    forumHomePage.goToTopic(topic1);
    forumTopicManagement.editTopic(topicNewName, "");
    executeJavaScript("window.scrollBy(0,-400);", "");
    homePagePlatform.goToHomePage();
    homePagePlatform.refreshUntil($(byText(topicNewName)),Condition.exist,1000);
    info("Verify that the topic's activity is updated");
    $(byText(topicNewName)).should(Condition.exist);
    info("The topic's activity is updated successfully");
    deleteDataTest();
    info("Test 3: Finish testing");
  }

  /**
   * <li>Case ID:116781.</li>
   * <li>Test Case Name: Check activity after update topic content.</li>
   * <li>Pre-Condition: - Topic activity is existed</li>
   */
  @Test
  public void test04_CheckTopicActivityAfterUpdateTopicConent() throws Exception {
    info("Test 4: Check activity after update topic content");
    info("Create data test for test 4");
    String topic1 = "Topic1" + getRandomNumber();
    String newContent = "NewContent" + getRandomNumber();
    String comment2 = "Content has been edited.";
    info("Finish Creating data test for test 4");
    prepareDataTest();
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic1, topic1, "", "");
    info("Edit topic:" + topic1);
    forumHomePage.goToTopic(topic1);
    forumTopicManagement.editTopic("", newContent);
    executeJavaScript("window.scrollBy(0,-400);", "");
    homePagePlatform.goToHomePage();
    homePagePlatform.refreshUntil($(byText(topic1)),Condition.exist,1000);
    info("Verify that the new topic's activity is shown");
    $(byText(topic1)).should(Condition.exist);
    $(byText(comment2)).should(Condition.exist);
    info("the new topic's activity is shown successfully");
    deleteDataTest();
    info("Test 04: finished testing");
  }

  /**
   * <li>Case ID:116782.</li>
   * <li>Test Case Name: Check topic activity after lock/unlock a topic.</li>
   * <li>Pre-Condition: Topic activity is existed</li>
   */
  @Test
  public void test05_CheckTopicActivityAfterLockUnlockTopic() throws Exception {
    info("Test 5: Check topic activity after lock/unlock a topic");
    info("Create data test for test 5");
    String topic1 = "Topic1" + getRandomNumber();
    String comment3 = "Topic has been unlocked.";
    String comment4 = "Topic has been locked.";
    info("Finished Creating data test for test 5");
    prepareDataTest();
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic1, topic1, "", "");
    info("Lock topic:" + topic1);
    forumHomePage.goToTopic(topic1);
    forumTopicManagement.selectItemMoreActionMenuTopic(ForumTopicManagement.specifMoreActionMenuTopic.LOCK);
    info("Verify that Post reply button is not shown when the topic is locked");
    $(ELEMENT_POST_REPLY).should(Condition.exist);
    info("The topic is locked successfully");
    homePagePlatform.goToHomePage();
    homePagePlatform.refreshUntil($(byText(topic1)),Condition.exist,1000);
    info("Verify that the topic's activity is shown");
    $(byText(comment4)).should(Condition.exist);
    info("The topic's activity is shown successfully");
    homePagePlatform.goToForum();
    $(byText(nameCat)).click();
    $(byText(nameForum)).click();
    forumHomePage.goToTopic(topic1);
    forumTopicManagement.selectItemMoreActionMenuTopic(ForumTopicManagement.specifMoreActionMenuTopic.UNLOCK);
    info("Verify that Post reply button is shown when the topic is unlocked");
    $(ELEMENT_POST_REPLY).should(Condition.exist);
    info("The topic is unlocked successfully");
    homePagePlatform.goToHomePage();
    homePagePlatform.refreshUntil($(byText(topic1)),Condition.exist,1000);
    info("Verify that topic's activity is shown");
    $(By.xpath(ELEMENT_ACTIVITY_COMMENT.replace("${title}", topic1).replace("${comment}", comment3))).should(Condition.exist);
    info("The topic's activity is shown successfully");
    deleteDataTest();
    info("Test 05: Finish testing");
  }

  /**
   * <li>Case ID:116783.</li>
   * <li>Test Case Name: Check topic activity after delete topic.</li>
   */
  @Test
  public void test06_CheckActivityAfterDeleteTopic() throws Exception {
    info("Test 6: Check topic activity after delete topic");
    info("Create data test for test 6");
    String topic1 = "Topic1" + getRandomNumber();
    info("Finished Creating data test for test 6");
    prepareDataTest();
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic1, topic1, "", "");
    info("Delete topic:" + topic1);
    forumHomePage.goToTopic(topic1);
    forumTopicManagement.deleteTopic();
    deleteDataTest();
    homePagePlatform.goToHomePage();
    homePagePlatform.refreshUntil($(byText(topic1)),Condition.not(Condition.exist),2000);
    sleep(Configuration.timeout);
    info("Verify that the topic's activity is deleted after the topic is deleted");
    $(byText(topic1)).shouldNot(Condition.exist);
    info("the topic's activity is deleted sucessfully");
    info("Test 06: Finish testing");
  }

  /**
   * <li>Case ID:116785.</li>
   * <li>Test Case Name: Add a new poll.</li> Step Number: 1 Step Name: - Add new
   * poll Step Description: - Connect to Intranet - Open a Forum - Add a new topic
   * - Goto topic => More Action => add Poll - Fill the infomation and click
   * [save] - Back to the Homepage Input Data: Expected Outcome: - Poll is added
   * to topic - A Poll's activity is added to the activity stream
   */
  @Test
  public void test07_AddPoll() throws Exception {
    info("Test 7: Add a new poll");
    info("Create data test for test 7");
    String topic1 = "Topic1" + getRandomNumber();
    String question = "Question" + getRandomNumber();
    String option1 = "Option1" + getRandomNumber();
    String option2 = "Option2" + getRandomNumber();
    String comment = "A poll has been added to the topic.";
    info("Finish Creating data test for test 7");

    prepareDataTest();
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic1, topic1, "", "");
    forumHomePage.goToTopic(topic1);
    info("Add a new poll to the topic");
    forumTopicManagement.addPoll(question, option1, option2);
    info("clear cache and recconnect");
    homePagePlatform.goToHomePage();
    homePagePlatform.refreshUntil($(byText(comment)),Condition.exist,1000);
    info("Verify that topic's activity is added to the stream");
    sleep(Configuration.timeout);
    $(byText(comment)).should(Condition.exist);
    info("The topic's activity is added to the stream successfully");
    deleteDataTest();
    info("Test 07: Finished testing");

  }

  /**
   * <li>Case ID:116784.</li>
   ** <li>Test Case Name: Redirect to the poll by clicking on "Vote".</li>
   */
  @Test
  public void test08_RedirectToThePollByClickingOnVote() throws Exception {
    info("Test 08:Redirect to the poll by clicking on Vote");
    info("Create data test for test 8");
    String topic1 = "Topic1" + getRandomNumber();
    String question = "Question" + getRandomNumber();
    String option1 = "Option1" + getRandomNumber();
    String option2 = "Option2" + getRandomNumber();
    info("Finish Creating data test for test 8");
    prepareDataTest();
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic1, topic1, "", "");
    forumHomePage.goToTopic(topic1);
    info("Add a new poll to the topic");
    forumTopicManagement.addPoll(question, option1, option2);
    info("clear cache and recconnect");
    homePagePlatform.goToHomePage();
    info("Click on Vote of Poll's activity on the stream");
    homePagePlatform.refreshUntil($(byText(question)),Condition.exist,1000);
    $(byText(question)).click();
    info("Verify that the page redirects to the poll");
    $(ELEMENT_MORE_ACTIONS_POLL).should(Condition.exist);
    info("the page redirects to the poll successfully");
    deleteDataTest();
    info("Test 08: Finish testing");
  }

  /**
   * <li>Case ID:116787.</li>
   * <li>Test Case Name: Edit a poll.</li>
   */
  @Test
  public void test09_EditPoll() throws Exception {
    info("Test 09: Edit a poll");
    info("Create data test for test 9");
    String topic1 = "Topic1" + getRandomNumber();
    String question = "Question" + getRandomNumber();
    String option1 = "Option1" + getRandomNumber();
    String option2 = "Option2" + getRandomNumber();
    String option3 = "Option3" + getRandomNumber();
    String comment2 = "Poll has been updated.";
    info("Finished Creating data test for test 9");
    prepareDataTest();

    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic1, topic1, "", "");
    forumHomePage.goToTopic(topic1);
    info("Add a new poll to the topic");
    forumTopicManagement.addPoll(question, option1, option2);
    info("Edit a poll");
    forumTopicManagement.editPoll(question, option1, option3);
    info("clear cache and recconnect");
    homePagePlatform.goToHomePage();
    homePagePlatform.refreshUntil($(byText(question)),Condition.exist,1000);
    info("Verify that the poll's comment is shown on the stream");
    $(byText(question)).should(Condition.exist);
    $(byText(comment2)).should(Condition.exist);
    info("the poll's comment is shown on the stream successfully");
    deleteDataTest();
    info("Test 09: Testing finished");
  }

  /**
   * <li>Case ID:116786.</li>
   * <li>Test Case Name: Delete a poll.</li>
   */
  @Test
  public void test10_DeletePoll() throws Exception {
    info("Test 10: Delete a poll");
    info("Create data test for test 10");
    String topic1 = "Topic1" + getRandomNumber();
    String question = "Question" + getRandomNumber();
    String option1 = "Option1" + getRandomNumber();
    String option2 = "Option2" + getRandomNumber();
    String comment3 = "Poll has been removed.";
    info("Finished Creating  data test for test 10");
    prepareDataTest();

    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic1, topic1, "", "");
    forumHomePage.goToTopic(topic1);
    info("Add a new poll to the topic");
    forumTopicManagement.addPoll(question, option1, option2);

    info("Delete the poll");
    forumTopicManagement.deletePoll();
    homePagePlatform.goToHomePage();
    info("Verify that the comment is added to the topic on the stream after deleted poll");
    homePagePlatform.refreshUntil($(byText(topic1)),Condition.exist,1000);
    $(byText(topic1)).should(Condition.exist);
    $(byText(comment3)).should(Condition.exist);
    info("The comment is added to the topic on the stream successfully after deleted poll");
    deleteDataTest();
    info("Test 10: finshed testing");
  }

  /**
   * <li>Case ID:116788.</li>
   * <li>Test Case Name: Jump into Reply form by clicking on "Reply" action.</li>
   * <li>Pre-Condition: a forum activity is already shared in the activity
   * stream</li>
   * <li>Post-Condition:</li>
   * <li>Done with OSs and browsers :</li>
   * <li>Generated by rosso at 2015/01/22 14:38:18</li> Step Number: 1 Step Name:
   * Step Description: - Connect to Intranet Homepage - From the activity stream,
   * click on "Reply" action in Forum activity Input Data: Expected Outcome: - The
   * forum application is displayed with the reply form opened
   */
  @Test
  public void test11_JumpIntoReplyFormByClickingOnReplyAction() throws Exception {
    info("Test 11 Jump into Reply form by clicking on Reply action");

    info("Create test data");
    String topic1 = "Topic1" + getRandomNumber();
    info("Finished test data");
    prepareDataTest();

    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic1, topic1, "", "");
    homePagePlatform.goToHomePage();
    homePagePlatform.refreshUntil($(byText(topic1)),Condition.exist,1000);
    info("Click on Reply button of the topic:" + topic1);
    $(byText(topic1)).parent().parent().parent().parent().find(ELEMENT_ICON_REPLAY_POST_FROM_ACTIVITY).click();
    info("Verify that Reply popup of the topic is shown");
    $(ELEMENT_TOPIC_POST_A_REPLY_TITLE).should(Condition.exist);
    info("Reply popup of the topic is shown successfully");
    info("Click on cancel button of the post");
    $(ELEMENT_TOPIC_CANCEL_A_POST).click();
    deleteDataTest();
    info("Test 11: finsihed testing");
  }

  /**
   * <li>Case ID:116789.</li>
   * <li>Test Case Name: Jump into last Reply of Topic.</li>
   * <li>Pre-Condition: - a forum activity is already shared in the activity
   * stream - there are some comments on a topic of forum</li>
   * <li>Post-Condition:</li>
   * <li>Done with OSs and browsers :</li>
   * <li>Generated by rosso at 2015/01/22 14:38:18</li> Step Number: 1 Step Name:
   * Step Description: - Connect to Intranet - From the activity stream, click on
   * " View last Reply" action in Forum activity Input Data: Expected Outcome: -
   * The lastest reply of the topic is displayed
   */
  @Test
  public void test12_JumpIntoLastReplyOfTopic() throws Exception {
    info("Test 12 Jump into last Reply of Topic");

    info("Create test data for test 12");
    String topic = "Topic" + getRandomNumber();
    String reply = "Reply" + getRandomNumber();
    info("Finished test data for test 12");
    prepareDataTest();

    info("Create a topic");
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic, topic, "", "");
    info("Finished Creating a topic");
    info("Open the topic:" + topic);
    forumHomePage.goToTopic(topic);
    info("Reply the topic");
    forumTopicManagement.replyTopic(reply, reply, "", "");
    executeJavaScript("window.scrollBy(0,-600);", "");
    homePagePlatform.goToHomePage();
    homePagePlatform.refreshUntil($(byText(topic)),Condition.exist,1000);
    info("Verify that topic's activity with View Last reply icon is shown");
    $(byText(topic)).should(Condition.exist);
    info("The topic's activity with View Last reply icon is shown successfully");
    info("Click on View Last Reply icon");
    $(By.xpath(ELEMENT_ACTIVITY_TOPIC_VIEW_LAST_REPLY.replace("${topic}", topic))).click();
    info("Verify that the last reply is shown in forum");
    $(byText(reply)).should(Condition.exist);
    info("the last reply is shown in forum successfully");
    deleteDataTest();
    info("Test 12: Finish testing");

  }

  /**
   * <li>Case ID:116790.</li>
   * <li>Test Case Name: Jump to related reply.</li>
   * <li>Pre-Condition: a forum activity is already shared in the activity
   * stream</li>
   * <li>Post-Condition:</li>
   * <li>Done with OSs and browsers :</li>
   * <li>Generated by rosso at 2015/01/22 14:38:18</li> Step Number: 1 Step Name:
   * Step Description: - Connect to Intranet - From the activity stream, add a
   * comment to a forum activity - Move the mouse over the comment Input Data:
   * Expected Outcome: The "View" action is displayed Step number: 2 Step Name:
   * Step Description: - Click on the action "View" Input Data: Expected Outcome:
   * - The related reply in the forum is displayed
   */
  @Test
  public void test13_JumpToRelatedReply() throws Exception {
    info("Test 13 Jump to related reply");

    info("Create test data for test 13");
    String topic = "Topic" + getRandomNumber();
    String comment = "Comment" + getRandomNumber();
    info("Finished test data for test 13");
    prepareDataTest();
    info("Create a topic");
    forumForumManagement.goToStartTopic();
    forumTopicManagement.startTopic(topic, topic, "", "");
    info("Finished Creating a topic");
    homePagePlatform.goToHomePage();
    homePagePlatform.refreshUntil($(byText(topic)),Condition.exist,1000);
    info("Add a comment to the topic's activity");
    activityStream.checkActivity(topic);
    String id = $(byText(topic)).parent().parent().parent().parent().getAttribute("id").split("ActivityContextBox")[1];
    activityStream.addcomment_to_activity(id, comment);
    info("Mouse over on the comment");
    $(byId(ELEMENT_COMMENT_BLOC.replace("{id}", id))).waitUntil(Condition.visible, Configuration.timeout).click();
    $(byId("dropDownEditCommentcomment{id}".replace("{id}", String.valueOf(Integer.valueOf(id) + 1)))).waitUntil(Condition.visible, Configuration.timeout).click();
    $(byId("ViewCommentcomment{id}".replace("{id}", String.valueOf(Integer.valueOf(id) + 1)))).waitUntil(Condition.visible, Configuration.timeout).click();
    info("Verifyt that View is shown");
    info("Click on the View icon");
    info("Verify that the page redirects to related reply in the forum");
    executeJavaScript("window.scrollBy(0,-550)");
    $(byText(topic)).should(Condition.appears);
    info("The related reply is shown in forum successfully");
    deleteDataTest();
    info("Test 13: Finish testing");
  }
}
