package org.exoplatform.platform.qa.ui.platform.social.functional;

import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_PASS2;
import static org.exoplatform.platform.qa.ui.core.PLFData.DATA_USER1;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static org.exoplatform.platform.qa.ui.selenium.testbase.LocatorTestBase.ELEMENT_ACCOUNT_NAME_LINK;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Condition;
import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.ActivityStream;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.ManageLogInOut;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceHomePage;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceSettingManagement;
import org.exoplatform.platform.qa.ui.social.pageobject.*;

@Tag("functional")
@Tag("social")
public class SOCNotificationIntranetUserSettingsTestIT extends Base {
  NavigationToolbar      navigationToolbar;

  AddUsers               addUsers;

  ManageLogInOut         manageLogInOut;

  SpaceManagement        spaceManagement;

  SpaceHomePage          spaceHomePage;

  SpaceSettingManagement spaceSettingManagement;

  HomePagePlatform       homePagePlatform;

  UserAddManagement      userAddManagement;

  ActivityStream         activityStream;

  IntranetNotification   intranetNotification;

  MyNotificationsSetting myNotificationsSetting;

  NotificationActivity   notificationActivity;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");
    navigationToolbar = new NavigationToolbar(this);
    addUsers = new AddUsers(this);
    manageLogInOut = new ManageLogInOut(this);
    homePagePlatform = new HomePagePlatform(this);
    spaceManagement = new SpaceManagement(this);
    userAddManagement = new UserAddManagement(this);
    activityStream = new ActivityStream(this);
    manageLogInOut.signInCas(DATA_USER1, DATA_PASS2);
    intranetNotification = new IntranetNotification(this);
    spaceHomePage = new SpaceHomePage(this);
    myNotificationsSetting = new MyNotificationsSetting(this);
    spaceSettingManagement = new SpaceSettingManagement(this);
    notificationActivity = new NotificationActivity(this);

  }

  /**
   * <li>Case ID:125160.</li>
   * <li>Test Case Name: Check Email Notifications User settings.</li>
   * <li>Pre-Condition:</li>ju
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1 : Go to My Notifications Step Description: -
   * Login - Click the user menu and go to My Notifications Input Data: Expected
   * Outcome: - The user settings page is displayed
   */
  /*
   * Step number: 2 Step Name: Step 2 : Check Email Notifications toggle Step
   * Description: - Check the toggle related to email notifications Input Data:
   * Expected Outcome: - The toggle is displayed at the top of the table - The
   * toggle is labeled :Notify me by email - The toggle if ON by default
   */
  /*
   * Step number: 3 Step Name: Step 3 : Update Email Notifications toggle Step
   * Description: - Switch OFF email notifications Input Data: Expected Outcome: -
   * All settings related to Email Notifications are disabled (instant
   * notification and digest) and the settings of the user are remembere
   */
  /*
   * Step number: 4 Step Name: Step 4 : Update Email Notifications toggle Step
   * Description: - Switch ON emails notifications Input Data: Expected Outcome: -
   * The user finds back the same configuration
   */
  @Test
  public void test01_CheckEmailNotificationsUserSettings() throws Exception {
    info("Test 1: Check Email Notifications User settings");

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String password = "123456";

    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, "123456", email1, username1, username1);
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.verifyTilePage();

    myNotificationsSetting.checkEmailNotifiToggle();
    homePagePlatform.refreshUntil($(ELEMENT_ACCOUNT_NAME_LINK), Condition.visible, 1000);
    myNotificationsSetting.turnOnOffNotiEmail(false);
    myNotificationsSetting.veriftyAllEmailNotiDefaultDisable();

    info("the settings of the user are remembered");

    info("User A login");
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.verifyTilePage();
    myNotificationsSetting.veriftyAllEmailNotiDefaultDisable();
    homePagePlatform.refreshUntil($(ELEMENT_ACCOUNT_NAME_LINK), Condition.visible, 1000);
    myNotificationsSetting.turnOnOffNotiEmail(true);
    myNotificationsSetting.veriftyAllEmailNotiDefaultEnabled();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }

  /**
   * <li>Case ID:125161.</li>
   * <li>Test Case Name: Check Intranet Notifications User settings.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step 1 : Go to My Notifications Step Description: -
   * Login - Click the user menu and go to My Notifications Input Data: Expected
   * Outcome: - The user settings page is displayed
   */
  /*
   * Step number: 2 Step Name: Step 2 : Check Intranet Notifications toggle Step
   * Description: - Check the toggle related to Intranet Notifications Input Data:
   * Expected Outcome: - The toggle is displayed at the top of the table - The
   * toggle is labeled : Get Intranet Notifications - The toggle if ON by default
   */
  /*
   * Step number: 3 Step Name: Step 3 : Update Intranet Notifications toggle Step
   * Description: - Switch OFF Intranet Notifications Input Data: Expected
   * Outcome: - All settings related to Intranet Notifications are disabled and
   * the settings of the user are remembered.
   */
  /*
   * Step number: 4 Step Name: Step 4 : Update Intranet Notifications toggle Step
   * Description: - Switch ON Intranet Notifications Input Data: Expected Outcome:
   * - The user finds back the same configuration
   */
  @Test
  public void test02_CheckIntranetNotificationsUserSettings() {
    info("Test 2: Check Intranet Notifications User settings");

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String password = "123456";

    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.verifyTilePage();
    myNotificationsSetting.checkIntranetNotifiToggle();
    myNotificationsSetting.turnOnOffNotiIntranet(false);
    myNotificationsSetting.veriftyAllIntranetNotiDefaultDisable();

    info("the settings of the user are remembered");

    info("User A login");
    manageLogInOut.signIn(username1, password);
    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.verifyTilePage();
    myNotificationsSetting.veriftyAllIntranetNotiDefaultDisable();
    myNotificationsSetting.turnOnOffNotiIntranet(true);
    myNotificationsSetting.veriftyAllIntranetNotiDefaultEnabled();
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);

  }

  /**
   * <li>Case ID:125166.</li>
   * <li>Test Case Name: Check default user settings.</li>
   * <li>Pre-Condition: - The user connects for the first time to the platform (or
   * haven't done any change in the user settings since his first connection)</li>
   * <li>Post-Condition:</li>
   */
  /*
   * Step Number: 1 Step Name: Step Description: - Login - Go to User Menu and
   * click [My Notifications] - Check the Intranet Notification setings Input
   * Data: Expected Outcome: The default Intranet Notification settings are : New
   * User : No Notifications Connection Request : Intranet Notification * Space
   * Invitation : Intranet Notification * Space Join Request: Intranet
   * Notification * Mention : Intranet Notification * Comment : Intranet
   * Notification * Like : No notifications * Post on my Steam : Intranet
   * Notification * Post on my Space : Intranet Notification
   */
  @Test
  public void test03_CheckDefaultUserSettings() {
    info("Test 3: Check default user settings");

    String username1 = "usernamea" + getRandomString();
    String email1 = username1 + "@gmail.com";
    String password = "123456";

    info("Add new user");
    navigationToolbar.goToAddUser();
    addUsers.addUser(username1, password, email1, username1, username1);
    manageLogInOut.signIn(username1, password);

    navigationToolbar.goToMyNotifications();
    myNotificationsSetting.verifyTilePage();
    myNotificationsSetting.verifyNotificationDefault(NotificationsAdminSeting.notificationType.NewUser_intranet);
    myNotificationsSetting.verifyNotificationDefault(NotificationsAdminSeting.notificationType.ConnectionRequest_intranet);
    myNotificationsSetting.verifyNotificationDefault(NotificationsAdminSeting.notificationType.Space_Invitation_intranet);
    myNotificationsSetting.verifyNotificationDefault(NotificationsAdminSeting.notificationType.Space_Join_intranet);
    myNotificationsSetting.verifyNotificationDefault(NotificationsAdminSeting.notificationType.AS_Mention_intranet);
    myNotificationsSetting.verifyNotificationDefault(NotificationsAdminSeting.notificationType.AS_Comment_intranet);
    myNotificationsSetting.verifyNotificationDefault(NotificationsAdminSeting.notificationType.AS_Like_intranet);
    myNotificationsSetting.verifyNotificationDefault(NotificationsAdminSeting.notificationType.AS_Post_intranet);
    myNotificationsSetting.verifyNotificationDefault(NotificationsAdminSeting.notificationType.Space_Post_intranet);
    manageLogInOut.signIn(DATA_USER1, DATA_PASS2);
    navigationToolbar.goToManageCommunity();
    addUsers.deleteUser(username1);
  }
}
