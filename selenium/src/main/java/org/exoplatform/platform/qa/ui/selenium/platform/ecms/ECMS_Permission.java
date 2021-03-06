package org.exoplatform.platform.qa.ui.selenium.platform.ecms;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class ECMS_Permission {

  private final TestBase       testBase;

  public ManageAlert           mngAlert;

  private ElementEventTestBase evt;

  public ECMS_Permission(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.mngAlert = new ManageAlert(testBase);
  }

  /**
   * Delete permission of a node
   * 
   * @param name String
   */
  public void deletePermissionNode(String name) {
    if ($(byXpath(ELEMENT_PERMISSION_USER_OR_GROUP_NAME.replace("${name}", name))).is(Condition.visible)) {
      info("Click on Delete button of the node:" + name);
      $(byXpath((ELEMENT_PERMISSION_DELETE).replace("${name}", name))).click();
      info("click on OK button of alert popup");
      mngAlert.acceptAlert();
      info("Finished deleting permission of the node");
    }
  }

  /**
   * Change right
   * 
   * @param user String
   * @param name String
   * @param read boolean
   * @param modify boolean
   * @param remove boolean
   * @param opt String
   */
  public void changeRight(String user, String name, boolean read, boolean modify, boolean remove, String... opt) {
    if (user == "user") {
      info("User is a user");
      info("Click on Select User button");
      $(ELEMENT_PERMISSION_SELECTUSER).waitUntil(Condition.visible, Configuration.timeout).click();
      info("Click on Add User button");
      sleep(2000);
      $(ELEMENT_SEARCH_USER_INPUT).setValue(name);
      $(byXpath("//span[@class='searchByUser']//a[@data-original-title='Quick Search']")).waitUntil(visible,timeout).click();
      $(By.xpath((ELEMENT_PERMISSION_USER_ADDUSER).replace("${name}", name))).waitUntil(visible, timeout).click();
    }
    if (user == "membership") {
      info("User is a membership");
      info("Type a mebership for textbox user");
      $(ELEMENT_PERMISSION_TEXTBOXUSER).setValue( "" + opt[0] + ":/" + opt[1] + "");
    }
    if (user == "all") {
      info("User is all");
      info("Click on Select everyone button");
      $(ELEMENT_PERMISSION_SELECTEVERYONE).click();
    }
    info("Check on checkbox for reading, modifying and removing");
    selectCheckBoxRight(read, modify, remove);
    info("Click on Save button");
    $(ELEMENT_PERMISSION_SAVE).waitUntil(visible, timeout).click();
    info("Finished changing right");
  }

  /**
   * Select a check box about right
   * 
   * @param read boolean
   * @param modify boolean
   * @param remove boolean
   */
  public void selectCheckBoxRight(boolean read, boolean modify, boolean remove) {
    info("Select check boxes right");
    if (read == true) {
      info("Read right is true, click on Read checkbox");
      evt.check(ELEMENT_PERMISSION_CHECKBOXREAD, 2);
    }
    if (modify == true) {
      info("Modify right is true, click on Modify checkbox");
      evt.check(ELEMENT_PERMISSION_CHECKBOXMODIFY, 2);
    }
    if (remove == true) {
      info("Remove right is true, click on Remove checkbox");
      evt.check(ELEMENT_PERMISSION_CHECKBOXREMOVE, 2);
    }

    info("Finished selecting right checkbox");
  }

  /**
   * Close permission form
   */
  public void closePermission() {
    info("Close permission form");
    $(ELEMENT_PERMISSION_CLOSE).click();
  }

  /**
   * Select group membership
   * 
   * @param groupPath path group: (Ex: Organization/Employees)
   * @param membership membership: (Ex: author)
   */
  public void selectGroupMembershipOfCat(String groupPath, String membership) {
    String[] temp;
    info("select permission for category");
    evt.waitForAndGetElement(ELEMENT_CAT_SELECT_MEMBERSHIP_POPUP);
    temp = groupPath.split("/");
    for (int i = 0; i < temp.length; i++) {
      $(byXpath(ELEMENT_CAT_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]))).click();
    }
    $(byXpath(ELEMENT_CAT_SELECT_RIGHT_PARENT_GROUP.replace("$group", membership))).click();
    $(ELEMENT_CAT_SELECT_MEMBERSHIP_POPUP).shouldNot(Condition.visible);
  }

  /**
   * Select group membership
   * 
   * @param groupPath path group: (Ex: Organization/Employees)
   * @param membership membership: (Ex: author)
   */
  public void selectGroupMembershipOfLock(String groupPath, String membership) {
    String[] temp;
    info("select permission for lock");
    evt.waitForAndGetElement(ELEMENT_LOCK_SELECT_MEMBERSHIP_POPUP);
    temp = groupPath.split("/");
    for (int i = 0; i < temp.length; i++) {
      evt.click(ELEMENT_LOCK_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]));
    }
    evt.click(ELEMENT_LOCK_SELECT_RIGHT_PARENT_GROUP.replace("$group", membership));
  }

  /**
   * Select group membership
   * 
   * @param groupPath path group: (Ex: Organization/Employees)
   * @param membership membership: (Ex: author)
   */
  public void selectGroupMembershipOfTag(String groupPath, String membership) {
    String[] temp;
    info("select permission for tag");
    evt.click(ELEMENT_PERMISSION_SELECTMEMBERSHIP, 0, true);
    evt.waitForAndGetElement(ELEMENT_TAG_SELECT_MEMBERSHIP_POPUP);
    temp = groupPath.split("/");
    for (int i = 0; i < temp.length; i++) {
      evt.click(ELEMENT_TAG_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]));
    }
    evt.click(ELEMENT_TAG_SELECT_RIGHT_PARENT_GROUP.replace("$group", membership));

  }

  /**
   * Select group membership
   * 
   * @param groupPath path group: (Ex: Organization/Employees)
   * @param membership membership: (Ex: author)
   */
  public void selectGroupMembershipOfQuery(String groupPath, String membership) {
    String[] temp;
    info("select permission for query");
    evt.click(ELEMENT_PERMISSION_ADD, 0, true);
    evt.waitForAndGetElement(ELEMENT_TAG_SELECT_MEMBERSHIP_POPUP);
    temp = groupPath.split("/");
    for (int i = 0; i < temp.length; i++) {
      evt.click(ELEMENT_TAG_SELECT_RIGHT_PARENT_GROUP.replace("$group", temp[i]));
    }
    evt.click(ELEMENT_TAG_SELECT_RIGHT_PARENT_GROUP.replace("$group", membership));

  }

}
