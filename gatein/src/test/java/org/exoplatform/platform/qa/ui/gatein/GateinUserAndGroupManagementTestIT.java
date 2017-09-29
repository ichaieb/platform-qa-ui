package org.exoplatform.platform.qa.ui.gatein;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomString;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_CONTENT_PEOPLE;
import static org.exoplatform.platform.qa.ui.selenium.locator.ConnectionsLocator.ELEMENT_NAME_OF_PEOPLE;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAddManagement;
import org.exoplatform.platform.qa.ui.gatein.pageobject.UserAndGroupManagement;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.NavigationToolbar;

/**
 * @date March 10 2015
 * @author tult
 */
@Tag("gatein")
@Tag("smoke")
public class GateinUserAndGroupManagementTestIT extends Base {

  NavigationToolbar      navigationToolbar;

  UserAndGroupManagement userandgroupmanagement;

  UserAddManagement      useraddmanagement;

  HomePagePlatform       homePagePlatform;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    navigationToolbar = new NavigationToolbar(this);
    userandgroupmanagement = new UserAndGroupManagement(this);
    useraddmanagement = new UserAddManagement(this);
    homePagePlatform = new HomePagePlatform(this);
  }

  /**
   * <li>Case ID:123072.</li>
   * <li>Test Case Name: Add new group.</li>
   * <li>Case ID:123094.</li>
   * <li>Test Case Name: Edit Group.</li>
   * <li>Case ID:123073.</li>
   * <li>Test Case Name: Add user into group.</li>
   * <li>Case ID:123095.</li>
   * <li>Test Case Name: Delete group.</li>
   */
  public void test01_02_03_04_AddEditDeleteGroupAddUsersToGroup() {
    String groupName = "groupName" + getRandomNumber();
    String groupLabel = "groupLabel" + getRandomNumber();
    String groupDesc = "groupDesc" + getRandomNumber();

    String newLabel = "newLabel" + getRandomNumber();
    String newDesc = "newDesc" + getRandomNumber();

    String membership1 = "author";
    String membership2 = "editor";
    /*
     * Step Number: 1 Step Name: Step 1: Add new Group Step Description: - Go to
     * Group/Organization/ User and group management - Click Add new group icon -
     * Input some filed required and click Save Input Data: Expected Outcome: - Add
     * group successfully
     */
    info("Test Case 01: Add new group");
    navigationToolbar.goToUsersAndGroupsManagement();
    userandgroupmanagement.goToGroupTab();
    userandgroupmanagement.addGroup(groupName, groupLabel, groupDesc, true);

    /*
     * Step Number: 2 Step Name: Step 2: Edit Group Step Description: - Go to
     * Administration/Users/Groups and Roles - Select a group and click [Edit
     * selected group] - Change some fields and click Save Input Data: Expected
     * Outcome: - The group is updated with the change value
     */
    info("Test Case 02: Edit group");
    userandgroupmanagement.editGroup(groupLabel, null, newLabel, newDesc, true);

    /*
     * Step Number: 3 Step Name: Step 3: Add user to Group Step Description: -
     * Choose Group management form - Select a group from list - Input or search
     * user from list - Click Add button - Choose membership type and Save Input
     * Data: Expected Outcome: - Add user into group successfully
     */
    info("Test Case 03: Add some users into group");
    userandgroupmanagement.addUsersToGroup("mary", membership1, true, true);
    userandgroupmanagement.addUsersToGroup("demo", membership2, false, true);

    /*
     * Step Number: 4 Step Name: Step 4: Delete Group Step Description: - Go to
     * Group/Organization/ User and group management - Select a group and click
     * [Delete selected group] icon - Click Save Input Data: Expected Outcome: - The
     * group is removed from the list
     */
    info("Test Case 04: Delete group");
    userandgroupmanagement.deleteGroup(groupLabel, true);
  }

}
