package org.exoplatform.platform.qa.ui.platform.social;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.PLFData;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumCategoryManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumForumManagement;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumHomePage;
import org.exoplatform.platform.qa.ui.forum.pageobject.ForumTopicManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.*;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
import org.exoplatform.platform.qa.ui.task.pageobject.ProjectsManagement;
import org.exoplatform.platform.qa.ui.task.pageobject.TasksManagement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER2;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.ActivityStreamLocator.ELEMENT_VIEW_ALL_REPLIES_LINK;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_INPUT_USERNAME_CAS;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_SKIP_BUTTON;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by dmardassi on 16/10/2017.
 */
@Tag("social")
@Tag("sniff")
public class ReplyToCommentTestIT extends Base {

    ForumTopicManagement forumTopicManagement;
    TasksManagement tasksManagement;

    ForumForumManagement forumForumManagement;

    ForumCategoryManagement forumCategoryManagement;

    NavigationToolbar navigationToolbar;

    AddUsers addUsers;

    ManageLogInOut manageLogInOut;

    HomePagePlatform homePagePlatform;

    ConnectionsManagement connectionsManagement;

    ActivityStream activityStream;

    ForumHomePage forumHomePage;

    ProjectsManagement projectsManagement;

    @BeforeEach
    public void setupBeforeMethod() {
        info("Start setUpBeforeMethod");
        forumHomePage = new ForumHomePage(this);
        forumForumManagement = new ForumForumManagement(this);
        forumCategoryManagement = new ForumCategoryManagement(this);
        navigationToolbar = new NavigationToolbar(this);
        forumTopicManagement = new ForumTopicManagement(this);
        homePagePlatform = new HomePagePlatform(this);
        addUsers = new AddUsers(this);
        manageLogInOut = new ManageLogInOut(this);
        connectionsManagement = new ConnectionsManagement(this);
        activityStream = new ActivityStream(this);
        projectsManagement = new ProjectsManagement(this);
        tasksManagement = new TasksManagement(this);
        if ($(ELEMENT_SKIP_BUTTON).is(Condition.exist)) {
            $(ELEMENT_SKIP_BUTTON).click();
        }
        if ($(ELEMENT_INPUT_USERNAME_CAS).is(Condition.not(Condition.exist))) {
            manageLogInOut.signOut();
        }
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
    }

    @AfterEach
    public void signout() {
        manageLogInOut.signOut();
    }


    @Test
    public void test01_ReplyToCommentInAS() {
        String activity1 = "activity1" + getRandomNumber();
        String comment = "comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity1, "");
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.commentActivity(activity1, comment);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToHomePage();
        activityStream.replytocomment(comment, reply);

        activityStream.deleteactivity(activity1);
        executeJavaScript("window.scrollBy(0,-2000)", "");
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
    }

    @Test
    public void test03_ReplyToCommentShortenSection() {
        String activity1 = "activity1" + getRandomNumber();
        String comment = "comment" + getRandomNumber();
        String reply1 = "Reply" + getRandomNumber();
        String reply2 = "Reply" + getRandomNumber();
        String reply3 = "Reply" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity1, "");
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.commentActivity(activity1, comment);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToHomePage();
        activityStream.replytocomment(comment, reply1);
        activityStream.replytocomment(comment, reply2);
        activityStream.replytocomment(comment, reply3);
        refresh();
        //Check that the replies number in show reply link
        activityStream.showallreplies(comment);
        //Check that replies are well added
        $(byText(reply1)).waitUntil(Condition.appears, Configuration.timeout);
        $(byText(reply2)).waitUntil(Condition.appears, Configuration.timeout);
        $(byText(reply3)).waitUntil(Condition.appears, Configuration.timeout);
        //Check that replies are displayed under the parent comment
        String comment_parent_id = $(byText(comment)).parent().parent().parent().parent().getAttribute("data-comment-id");
        assertEquals($(byText(reply1)).parent().parent().parent().parent().getAttribute("data-parent-comment"), comment_parent_id);
        assertEquals($(byText(reply2)).parent().parent().parent().parent().getAttribute("data-parent-comment"), comment_parent_id);
        assertEquals($(byText(reply3)).parent().parent().parent().parent().getAttribute("data-parent-comment"), comment_parent_id);
        activityStream.deleteactivity(activity1);
        executeJavaScript("window.scrollBy(0,-2000)", "");
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
    }

    @Test
    public void test04_CheckReplyNumberInAS() {
        String activity1 = "activity1" + getRandomNumber();
        String comment = "comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity1, "");
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.commentActivity(activity1, comment);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToHomePage();
        activityStream.replytocomment(comment, reply);
        activityStream.replytocomment(comment, reply);
        activityStream.replytocomment(comment, reply);
        activityStream.replytocomment(comment, reply);
        refresh();
        //Check that the replies number in show reply link
        String idBlocComment = $(byText(comment)).parent()
                .parent()
                .parent()
                .find(byText(comment))
                .parent()
                .parent()
                .parent()
                .parent()
                .getAttribute("id")
                .split("commentContainercomment")[1];
        $(byId(ELEMENT_VIEW_ALL_REPLIES_LINK.replace("{id}", idBlocComment))).waitUntil(Condition.appears, Configuration.timeout).shouldHave(Condition.text("4"));
        activityStream.deleteactivity(activity1);
        executeJavaScript("window.scrollBy(0,-2000)", "");
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
    }

    @Test
    public void test05_Checktworepliesnotcollapsed() {
        String activity1 = "activity1" + getRandomNumber();
        String comment = "comment" + getRandomNumber();
        String reply1 = "Reply" + getRandomNumber();
        String reply2 = "Reply" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity1, "");
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.commentActivity(activity1, comment);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToHomePage();
        activityStream.replytocomment(comment, reply1);
        activityStream.replytocomment(comment, reply2);
        refresh();
        $(byText(reply1)).waitUntil(Condition.appears, Configuration.timeout);
        $(byText(reply2)).waitUntil(Condition.appears, Configuration.timeout);
        activityStream.deleteactivity(activity1);
        executeJavaScript("window.scrollBy(0,-2000)", "");
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
    }

    @Test
    public void test06_ReplyToReply() {

        String activity1 = "activity1" + getRandomNumber();
        String comment = "comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        String replytoreply = "ReplyToReply" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity1, "");
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.commentActivity(activity1, comment);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToHomePage();
        activityStream.replytocomment(comment, reply);
        $(byText(reply)).waitUntil(Condition.appears, Configuration.timeout);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        activityStream.replytoreply(activity1, reply, replytoreply);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        activityStream.deleteactivity(activity1);
        executeJavaScript("window.scrollBy(0,-2000)", "");
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
    }

    @Test
    public void test07_CheckReplyAfterDeleteParentComment() {

        String activity1 = "activity1" + getRandomNumber();
        String comment = "comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        String replytoreply = "ReplyToReply" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        homePagePlatform.goToHomePage();
        activityStream.addActivity(activity1, "");
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.commentActivity(activity1, comment);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToHomePage();
        activityStream.replytocomment(comment, reply);
        $(byText(reply)).waitUntil(Condition.appears, Configuration.timeout);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        activityStream.replytoreply(activity1, reply, replytoreply);
        activityStream.deletecomment(activity1, comment);
        $(byText(reply)).shouldNot(Condition.exist);
        $(byText(replytoreply)).shouldNot(Condition.exist);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        activityStream.deleteactivity(activity1);
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
    }

    //Check that quote a post in forum turns to reply to a comment in activity stream
    @Test
    public void test08_CheckQuoteInForumTurnsToReplyToCommentInAS() {
        String title = "Title" + getRandomNumber();
        String content = "Content" + getRandomNumber();
        String nameCat = "Category" + getRandomNumber();
        String nameForum = "Forum" + getRandomNumber();
        String nameTopic = "Topic" + getRandomNumber();
        String description = "Description" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        info("Open forum portlet");
        homePagePlatform.goToForum();
        info("Add a new category");
        forumCategoryManagement.addCategorySimple(nameCat, "", description);
        info("Add a new forum");
        forumForumManagement.addForumSimple(nameForum, "", description);
        info("Add a new topic");
        forumForumManagement.goToStartTopic();
        forumTopicManagement.startTopic(nameTopic, description, "", "");
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToForum();
        forumHomePage.goToTopic(nameTopic);
        forumTopicManagement.postReply(title, content);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToForum();
        forumHomePage.goToTopic(nameTopic);
        forumTopicManagement.quotePost(title, content);
        homePagePlatform.goToHomePage();
        String comment_parent_id = $(byText(content)).parent().parent().parent().parent().getAttribute("data-comment-id");
        assertEquals($(byText(content + "quote")).parent().parent().parent().parent().getAttribute("data-parent-comment"), comment_parent_id);
        homePagePlatform.goToForum();
        info("Go to Forum home page");
        forumHomePage.goToHomeCategory();
        $(byText(nameCat)).click();
        info("Delete category");
        forumCategoryManagement.deleteCategory(nameCat);
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
    }

    //Check attachment
    @Test
    public void test09_CheckReplyToCommentInForum() {
        String nameCat = "Category" + getRandomNumber();
        String nameForum = "Forum" + getRandomNumber();
        String nameTopic = "Topic" + getRandomNumber();
        String description = "Description" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        info("Open forum portlet");
        homePagePlatform.goToForum();
        info("Add a new category");
        forumCategoryManagement.addCategorySimple(nameCat, "", description);
        info("Add a new forum");
        forumForumManagement.addForumSimple(nameForum, "", description);
        info("Add a new topic");
        forumForumManagement.goToStartTopic();
        forumTopicManagement.startTopic(nameTopic, description, "", "");
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.commentTopicActivity(description, comment);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        activityStream.replytocomment(comment, reply);
        $(byText(nameTopic)).click();
        $(byText(reply)).parent().shouldHave(Condition.text(comment));
        forumHomePage.goToHomeCategory();
        $(byText(nameCat)).click();
        info("Delete category");
        forumCategoryManagement.deleteCategory(nameCat);
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
    }

    //Check reply turns to quote
    @Test
    public void test10_CheckReplyToCommentInForum() {
        String nameCat = "Category" + getRandomNumber();
        String nameForum = "Forum" + getRandomNumber();
        String nameTopic = "Topic" + getRandomNumber();
        String description = "Description" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        info("Open forum portlet");
        homePagePlatform.goToForum();
        info("Add a new category");
        forumCategoryManagement.addCategorySimple(nameCat, "", description);
        info("Add a new forum");
        forumForumManagement.addForumSimple(nameForum, "", description);
        info("Add a new topic");
        forumForumManagement.goToStartTopic();
        forumTopicManagement.startTopic(nameTopic, description, "", "");
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.commentTopicActivity(description, comment);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        activityStream.replytocomment(comment, reply);
        $(byText(nameTopic)).click();
        $(byClassName("contentQuote")).shouldHave(Condition.text(comment));
        $(byClassName("contentQuote")).shouldHave(Condition.text(DATA_USER2));
        homePagePlatform.goToForum();
        forumHomePage.goToHomeCategory();
        $(byText(nameCat)).click();
        info("Delete category");
        forumCategoryManagement.deleteCategory(nameCat);
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
    }

    //Check that each reply is attached to its parent comment
    @Test
    public void test11_CheckReplyToCommentInForum() {
        String nameCat = "Category" + getRandomNumber();
        String nameForum = "Forum" + getRandomNumber();
        String nameTopic = "Topic" + getRandomNumber();
        String description = "Description" + getRandomNumber();
        String reply1 = "Reply1" + getRandomNumber();
        String reply2 = "Reply2" + getRandomNumber();
        String comment1 = "Comment1" + getRandomNumber();
        String comment2 = "Comment2" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        info("Open forum portlet");
        homePagePlatform.goToForum();
        info("Add a new category");
        forumCategoryManagement.addCategorySimple(nameCat, "", description);
        info("Add a new forum");
        forumForumManagement.addForumSimple(nameForum, "", description);
        info("Add a new topic");
        forumForumManagement.goToStartTopic();
        forumTopicManagement.startTopic(nameTopic, description, "", "");
        forumHomePage.goToTopic(nameTopic);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        activityStream.commentTopicActivity(description, comment1);
        activityStream.commentTopicActivity(description, comment2);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        activityStream.replytocomment(comment1, reply1);
        activityStream.replytocomment(comment2, reply2);
        $(byText(nameTopic)).click();
        $(byText(reply1)).parent().shouldHave(Condition.text(comment1));
        $(byText(reply2)).parent().shouldHave(Condition.text(comment2));
        forumHomePage.goToHomeCategory();
        $(byText(nameCat)).click();
        info("Delete category");
        forumCategoryManagement.deleteCategory(nameCat);
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
    }

    //Check when replying to same comment for two times
    @Test
    public void test12_CheckReplyToCommentInForum() {
        String nameCat = "Category" + getRandomNumber();
        String nameForum = "Forum" + getRandomNumber();
        String nameTopic = "Topic" + getRandomNumber();
        String description = "Description" + getRandomNumber();
        String reply1 = "Reply1" + getRandomNumber();
        String reply2 = "Reply2" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        info("Finished creating data test");
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        info("Open forum portlet");
        homePagePlatform.goToForum();
        info("Add a new category");
        forumCategoryManagement.addCategorySimple(nameCat, "", description);
        info("Add a new forum");
        forumForumManagement.addForumSimple(nameForum, "", description);
        info("Add a new topic");
        forumForumManagement.goToStartTopic();
        forumTopicManagement.startTopic(nameTopic, description, "", "");
        forumHomePage.goToTopic(nameTopic);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        activityStream.commentTopicActivity(description, comment);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        activityStream.replytocomment(comment, reply1);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        activityStream.replytocomment(comment, reply2);
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        $(byText(nameTopic)).click();
        $(byText(reply1)).parent().shouldHave(Condition.text(comment));
        $(byText(reply2)).parent().shouldHave(Condition.text(comment));
        forumHomePage.goToHomeCategory();
        $(byText(nameCat)).click();
        info("Delete category");
        forumCategoryManagement.deleteCategory(nameCat);
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
    }


    @Test
    public void test13_CheckReplyToCommentInPreviewMode() {
        String activity1 = "Activity1" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        ELEMENT_TAB_LINK.click();
        refresh();
        ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
        ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
        ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
        activityStream.addActivity(activity1, "");
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        String id = $(byText(activity1)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).click();
        ELEMENT_INPUT_COMMENT_IN_DOCUMENT_PREVIEW.click();
        executeJavaScript("CKEDITOR.instances.commentInput. insertText(\"" + comment + "\")", "");
        ELEMENT_BUTTON_COMMENT_IN_DOCUMENT_PREVIEW.waitUntil(Condition.enabled, Configuration.timeout).click();
        $(byText(comment)).should(Condition.exist);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).click();
        activityStream.replytocommentinPreview(comment, reply);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        activityStream.deleteactivity(activity1);
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
    }

    @Test
    public void test14_CheckReplyToCommentInPreviewMode() {
        String activity1 = "Activity1" + getRandomNumber();
        String comment = "Comment" + getRandomNumber();
        String reply = "Reply" + getRandomNumber();
        String replytoreply = "ReplyToReply" + getRandomNumber();
        ELEMENT_TAB_LINK.click();
        refresh();
        ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
        ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
        ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
        activityStream.addActivity(activity1, "");
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        String id = $(byText(activity1)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).click();
        ELEMENT_INPUT_COMMENT_IN_DOCUMENT_PREVIEW.click();
        executeJavaScript("CKEDITOR.instances.commentInput. insertText(\"" + comment + "\")", "");
        ELEMENT_BUTTON_COMMENT_IN_DOCUMENT_PREVIEW.waitUntil(Condition.enabled, Configuration.timeout).click();
        $(byText(comment)).should(Condition.exist);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).click();
        activityStream.replytocommentinPreview(comment, reply);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(DATA_USER2, PLFData.DATA_PASS);
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).click();
        activityStream.replytoreplyinpreviewmode(reply);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        activityStream.deleteactivity(activity1);
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
    }


    @Test
    public void test15_ReplyToCommentShortenSectionInPreview() {
        String activity1 = "activity1" + getRandomNumber();
        String comment = "comment" + getRandomNumber();
        String reply1 = "Reply" + getRandomNumber();
        String reply2 = "Reply" + getRandomNumber();
        String reply3 = "Reply" + getRandomNumber();
        ELEMENT_TAB_LINK.click();
        refresh();
        ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
        ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
        ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
        activityStream.addActivity(activity1, "");
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        //mary comments
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        String id = $(byText(activity1)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).click();
        ELEMENT_INPUT_COMMENT_IN_DOCUMENT_PREVIEW.click();
        executeJavaScript("CKEDITOR.instances.commentInput. insertText(\"" + comment + "\")", "");
        ELEMENT_BUTTON_COMMENT_IN_DOCUMENT_PREVIEW.waitUntil(Condition.enabled, Configuration.timeout).click();
        $(byText(comment)).should(Condition.exist);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        manageLogInOut.signOut();
        //john replys
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).click();
        activityStream.replytocommentinPreview(comment, reply1);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        //mary replys
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).click();
        activityStream.replytocommentinPreview(comment, reply2);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        //john replys
        manageLogInOut.signOut();
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).click();
        activityStream.replytocommentinPreview(comment, reply3);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).click();
        //Check that the replies number in show reply link
        activityStream.showallreplies(comment);
        //Check that replies are well added
        $(byText(reply1)).waitUntil(Condition.appears, Configuration.timeout);
        $(byText(reply2)).waitUntil(Condition.appears, Configuration.timeout);
        $(byText(reply3)).waitUntil(Condition.appears, Configuration.timeout);
        //Check that replies are displayed under the parent comment
        String data_comment = $(byText(comment)).parent().parent().parent().getAttribute("data-comment");
        assertEquals($(byText(reply1)).parent().parent().parent().getAttribute("data-parent-comment"), data_comment);
        assertEquals($(byText(reply2)).parent().parent().parent().getAttribute("data-parent-comment"), data_comment);
        assertEquals($(byText(reply3)).parent().parent().parent().getAttribute("data-parent-comment"), data_comment);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        activityStream.deleteactivity(activity1);
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
    }

    @Test
    public void test16_CheckReplyNumberInPreview() {
        String activity1 = "activity1" + getRandomNumber();
        String comment = "comment" + getRandomNumber();
        String reply1 = "Reply1" + getRandomNumber();
        String reply2 = "Reply2" + getRandomNumber();
        String reply3 = "Reply3" + getRandomNumber();
        String reply4 = "Reply4" + getRandomNumber();
        homePagePlatform.goToConnections();
        connectionsManagement.connectToAUser(DATA_USER2);
        homePagePlatform.goToHomePage();
        ELEMENT_TAB_LINK.click();
        refresh();
        ELEMENT_CONTAINER_DOCUMENT.waitUntil(Condition.be(Condition.visible), Configuration.timeout);
        ELEMENT_INPUT_DOCUMENT.uploadFromClasspath("eXo-Platform.png");
        ELEMENT_BAR_PROGRESS.waitUntil(Condition.disappears, Configuration.timeout);
        activityStream.addActivity(activity1, "");
        //login mary comment
        manageLogInOut.signIn(DATA_USER2, PLFData.DATA_PASS);
        homePagePlatform.goToConnections();
        connectionsManagement.acceptAConnection(DATA_USER1);
        homePagePlatform.goToHomePage();
        String id = $(byText(activity1)).parent().parent().getAttribute("id").split("ActivityContextBox")[1];
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).click();
        ELEMENT_INPUT_COMMENT_IN_DOCUMENT_PREVIEW.click();
        executeJavaScript("CKEDITOR.instances.commentInput. insertText(\"" + comment + "\")", "");
        ELEMENT_BUTTON_COMMENT_IN_DOCUMENT_PREVIEW.waitUntil(Condition.enabled, Configuration.timeout).click();
        $(byText(comment)).should(Condition.exist);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        manageLogInOut.signOut();
        //login john
        manageLogInOut.signInCas(PLFData.DATA_USER1, "gtngtn");
        homePagePlatform.goToHomePage();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).click();
        activityStream.replytocommentinPreview(comment, reply1);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).click();
        activityStream.replytocommentinPreview(comment, reply2);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).click();
        activityStream.replytocommentinPreview(comment, reply3);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).click();
        activityStream.replytocommentinPreview(comment, reply4);
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        refresh();
        $(byId(ELEMENT_DOCUMENT_PREVIEW.replace("{id}", id))).click();
        //Check that the replies number in show reply link
        $(byId("commentArea")).find(byClassName("subCommentShowAllLink")).shouldHave(Condition.text("4"));
        ELEMENT_CLOSE_DOCUMENT_PREVIEW.click();
        activityStream.deleteactivity(activity1);
        executeJavaScript("window.scrollBy(0,-2000)", "");
        homePagePlatform.goToConnections();
        connectionsManagement.removeConnection(DATA_USER2);
    }

}
