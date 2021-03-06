package org.exoplatform.platform.qa.ui.platform.social;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_ALL_CONNECTIONS_TAB;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_CONTENT_PEOPLE;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_ABOUT_ME_PORTLET;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.ELEMENT_EDIT_MY_PROFILE_LINK;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.ConnectionsManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.UserProfilePage;
import org.exoplatform.platform.qa.ui.social.pageobject.AddUsers;
@Tag("social")
@Tag("sniff")
public class SOCPeopleProfileAboutMeTestIT extends Base {
  AddUsers              addUsers;

  NavigationToolbar     navigationToolbar;

  ManageLogInOut        manageLogInOut;

  UserProfilePage       userProfilePage;

  ConnectionsManagement connectionsManagement;

  HomePagePlatform      homePagePlatform;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    addUsers = new AddUsers(this);
    navigationToolbar = new NavigationToolbar(this);
    manageLogInOut = new ManageLogInOut(this);
    userProfilePage = new UserProfilePage(this);
    connectionsManagement = new ConnectionsManagement(this);
    homePagePlatform = new HomePagePlatform(this);
    manageLogInOut.signInCas(DATA_USER1, "gtngtn");
  }

  /**
   * <li>Case ID:122949.</li>
   * <li>Test Case Name: Check my About Me section.</li>
   * <li>Case ID:122950.</li>
   * <li>Test Case Name: Check About Me section of another user.</li>
   * <li>Pre-Condition: User has informed the about me section</li>
   * <li>Post-Condition:</li> Step Number: 1 Step Name: Step 1 : Go to my profile
   * page Step Description: - Login - Go to User Menu > [My Profile] Input Data:
   * Expected Outcome: - The section About me is displayed at the top of mid
   * -column of the page - The section is composed of 1 text area allowing the
   * user to introduce about himself. - The content of the section must fit in the
   * area Step Number: 1 Step Name: Step 1 : Go to other's profile page Step
   * Description: - Login with User A - Go to Connections and go to the profile of
   * user B Input Data: Expected Outcome: - The section About me is displayed at
   * the top of mid -column of the page - The section is composed of 1 text area
   * allowing the user to introduce about himself. - The content of the section
   * must fit in the area
   */
  @Test
  public void test01_02_CheckAboutMeSection() {
    String aboutMe = "aboutMe" + getRandomNumber();

    /* Create data test */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String password = "123456";
    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username1, password);

    info("Test 1: Check my About Me section");

    navigationToolbar.goToMyProfile();

    info("edit about me");
    click(ELEMENT_EDIT_MY_PROFILE_LINK);
    userProfilePage.updateAboutMe(aboutMe);
    userProfilePage.saveCancelUpdateInfo(true);
    $(byId("UIExperienceProfilePortlet")).find(byText(aboutMe)).should(Condition.exist);
    info("Test 2: Check About Me section of another user");
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  @Test
  public void test02_CheckAboutMeSectionOfOtherUser() {
    String aboutMe = "aboutMe" + getRandomNumber();

    /* Create data test */
    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@test.com";
    String username2 = "usernameb" + getRandomString();
    String email2 = username2 + "@test.com";
    String password = "123456";

    info("Add user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    addUsers.addUser(username2, password, email2, username2, username2);
    manageLogInOut.signIn(username1, password);

    info("Test 1: Check my About Me section");

    navigationToolbar.goToMyProfile();

    info("edit about me");
    click(ELEMENT_EDIT_MY_PROFILE_LINK);
    userProfilePage.updateAboutMe(aboutMe);
    userProfilePage.saveCancelUpdateInfo(true);
    ELEMENT_ABOUT_ME_PORTLET.find(byText(aboutMe)).should(Condition.exist);
    info("Test 2: Check About Me section of another user");

    info("Test 1: Check my About Me section");
    manageLogInOut.signIn(username2, password);
    info("goto profile of user 1");
    homePagePlatform.goToConnections();
    click(ELEMENT_ALL_CONNECTIONS_TAB);
    connectionsManagement.searchPeople(username1, null, null, null);
    ELEMENT_CONTENT_PEOPLE.find(byText(username1 + " " + username1)).click();
    ELEMENT_ABOUT_ME_PORTLET.find(byText(aboutMe));
    manageLogInOut.signIn(DATA_USER1, "gtngtn");
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
    addUsers.deleteUser(username2);
  }
}
