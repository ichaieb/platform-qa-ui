package org.exoplatform.platform.qa.ui.selenium.platform.administration;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.administration.AdministrationLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.locator.administration.AdministrationLocator;
import org.exoplatform.platform.qa.ui.selenium.platform.PlatformPermission;
import org.exoplatform.platform.qa.ui.selenium.platform.ecms.ECMS_Permission;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class ContentAdministration {

  private final TestBase       testBase;

  public ECMS_Permission       ecmsPerm;

  public PlatformPermission    plfPerm;

  // Permission
  public ManageAlert           alert;

  private ElementEventTestBase evt;

  public ContentAdministration(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.alert = new ManageAlert(testBase);
    this.ecmsPerm = new ECMS_Permission(testBase);
    this.plfPerm = new PlatformPermission(testBase);
  }

  /**
   * Go to Explorer function
   * 
   * @param main
   */
  public void goToSpecificMainFunctions(mainEcmFunctions main) {
    info("Go to select a main function");
    switch (main) {
    case EXPLORER:
      info("Select Explorer tab");
      if ($(ELEMENT_ECMS_FUNCTIONS_DRIVES).is(Condition.not(Condition.visible))) {
        ELEMENT_EXPLORER_CATEGORIES_ECM_FUNCTIONS.click();
      }
      break;

    case ADVANCED:
      info("Select Advanced tab");
      if ($(ELEMENT_ADVANCED_CATEGORIES_ECM_FUNCTIONS).is(Condition.visible))
        $(ELEMENT_ADVANCED_CATEGORIES_ECM_FUNCTIONS).click();
      break;
    case TEMPLATES:
      info("Select Templates tab");
      if ($(ELEMENT_TEMPLATE_CATEGORIES_ECM_FUNCTIONS).is(Condition.visible))
        $(ELEMENT_TEMPLATE_CATEGORIES_ECM_FUNCTIONS).click();
      break;
    case REPOSITORY:
      info("Select Repository tab");
      if (evt.waitForAndGetElement(ELEMENT_RESPONSITORY_CATEGORIES_ECM_FUNCTIONS, 5000, 0) != null)
        evt.click(ELEMENT_RESPONSITORY_CATEGORIES_ECM_FUNCTIONS);
      break;
    }
    info("The main function is selected successfully");
  }

  /**
   * Select a sub function in Advance function
   * 
   * @param spec
   */
  public void goToSpecificFunctions(specificEcmFunctions spec) {
    info("Go to select a function in Advance tab");
    switch (spec) {
    case ACTIONS:
      info("Select Actions function");
      $(ELEMENT_ECMS_FUNCTIONS_ACTIONS).click();
      break;
    case SCRIPTS:
      info("Select Scripts function");
      $(ELEMENT_ECMS_FUNCTIONS_SCRIPTS).click();
      break;
    case QUERIES:
      info("Select Queries function");
      $(ELEMENT_ECMS_FUNCTIONS_QUERIES).click();
      break;
    case CATEGORIES:
      info("Select Categories function");
      $(ELEMENT_ECMS_FUNCTIONS_CATEGORIES).click();
      break;
    case DRIVES:
      info("Select Drives function");
      $(ELEMENT_ECMS_FUNCTIONS_DRIVES).click();
      break;
    case VIEW:
      info("Select Views function");
      $(ELEMENT_ECMS_FUNCTIONS_VIEWS).click();
      break;
    case TAGS:
      info("Select Tags function");
      evt.click(ELEMENT_ECMS_FUNCTIONS_TAGS);
      break;
    case NODESTYPES:
      info("Select NodeStypes function");
      $(ELEMENT_ECMS_FUNCTIONS_NODES).click();
      break;
    case NAMESPACES:
      info("Select NameSpace function");
      evt.click(ELEMENT_ECMS_FUNCTIONS_NAMESPACES);
      break;
    case LOCKS:
      info("Select Locks function");
      evt.click(ELEMENT_ECMS_FUNCTIONS_LOCKS);
      break;
    case DOCUMENTS:
      info("Select Documents function");
      if ($(ELEMENT_ECMS_FUNCTIONS_DOCUMENTS).isDisplayed()) {
        $(ELEMENT_ECMS_FUNCTIONS_DOCUMENTS).click();
      } else {
        $(byId("accordion")).find(byText("Templates")).click();
        $(ELEMENT_ECMS_FUNCTIONS_DOCUMENTS).click();

      }
      break;
    case LIST:
      info("Select List function");
      $(ELEMENT_ECMS_FUNCTIONS_LIST).click();
      break;
    case METADATA:
      info("Select Metadata function");
      $(ELEMENT_ECMS_FUNCTIONS_METADATA).click();
      break;
    }
    info("End of selecting a function in Advance tab");
  }

  /**
   * Add a new View
   * 
   * @param name
   * @param tabName
   * @param tab
   * @param perm
   */
  public void addView(String name, String tabName, String[] tab, String... perm) {
    $(ELEMENT_ECM_EXPLORER_VIEWS_ADD_VIEWS).click();
    $(ELEMENT_ECM_EXPLORER_NAME_VIEW_FORM).setValue(name);
    $(ELEMENT_ECM_EXPLORER_GO_TO_ACTION_FORM).click();
    $(ELEMENT_ECM_EXPLORER_ADD_ACTION_VIEW_FORM).click();
    $(ELEMENT_ECM_EXPLORE_TAB_NAME_VIEW_FORM).setValue(tabName);
    for (String arrayElement : tab) {
      $(byText(arrayElement)).click();
    }
    $(ELEMENT_ECM_EXPLORE_SAVE_TAB_VIEW_FORM).click();
    $(ELEMENT_ECM_EXPLORER_GO_TO_PERMISSION_FORM).click();
    if (perm.length < 2) {
      $(ELEMENT_ECM_EXPLORER_USER_PERMISSION_ADD).click();
      $(byId("Quick Search")).setValue(perm[0]);
      $(byClassName("btnSearchUser")).click();
      $(byClassName("uiIconPlus")).click();
    } else {
      ecmsPerm.selectGroupMembershipOfTag(perm[0], perm[1]);
    }
    $(ELEMENT_ECM_EXPLORER_ADD_PERMISSION_FORM).click();
    $(ELEMENT_ECM_EXPLORER_SAVE_FORM_ADD_VIEW).click();
  }

  /**
   * Edit a view with Permission User
   * 
   * @param viewName
   * @param oldName
   * @param newName
   */
  public void editViewPermissionUser(String viewName, String oldName, String newName) {
    $(byXpath(ELEMENT_ECM_EXPLORER_VIEW_EDIT_LIST.replace("{$name}",viewName))).click();
    $(ELEMENT_ECM_EXPLORER_GO_TO_PERMISSION_FORM).click();
    $(ELEMENT_ECM_EXPLORER_USER_PERMISSION_ADD).click();
    $(byXpath(ELEMENT_ECM_EXPLORER_SELECT_USER_LIST_PERMISSION.replace("{$user}",newName))).click();
    $(ELEMENT_ECM_EXPLORER_ADD_PERMISSION_FORM).click();
    $(byXpath(ELEMENT_ECM_EXPLORER_DELETE_PERMISSION_USER.replace("{$name}",oldName))).click();
    alert.acceptAlert();
    $(ELEMENT_ECM_EXPLORER_SAVE_FORM_ADD_VIEW).click();
  }

  /**
   * Delete a View
   * 
   * @param viewName
   */
  public void deleteView(String viewName) {
    $(By.xpath(ELEMENT_ECM_EXPLORER_VIEW_DELETE_LIST.replace("{$name}", viewName))).click();
    alert.acceptAlert();
    $(byText(viewName)).shouldNot(Condition.exist);
  }

  /**
   * Add a new drive
   * 
   * @param name
   * @param permission
   * @param applyViews
   */
  public void addDrives(String name, String permission, specificView[] applyViews) {
    info("Click on Add button of the drive in the list");
    ELEMENT_ECM_EXPLORER_DRIVES_ADD_DRIVES.scrollTo().waitUntil(Condition.visible,Configuration.collectionsTimeout).click();
    ELEMENT_TAB_ADD_DRIVE_POPUP.waitUntil(Condition.visible,Configuration.timeout).click();
    info("Type a name for the drive");
    $(ELEMENT_ECM_EXPLORER_NAME_DRIVES_FORM).waitUntil(Condition.visible,Configuration.timeout).setValue(name);
    info("Click on Add Permission button for the drive");
    $(ELEMENT_ECM_COMMON_ADD_PERMISSION_BUTTON).waitUntil(Condition.visible,Configuration.timeout).click();
    if (permission == "any") {
      info("Set 'any' permission for the drive");
      $(ELEMENT_ECM_COMMON_ANY_PERMISSION).waitUntil(Condition.visible,Configuration.timeout).click();
    }
    info("Click on Aplly Views tab");
    ELEMENT_ECM_EXPLORER_APPLY_VIEWS_FORM.waitUntil(Condition.visible,Configuration.timeout).click();
    for (specificView arrayElement : applyViews) {
      info("Select a view type for the drive");
      switch (arrayElement) {
      case ADMIN:
        info("Add Admin view type");
        ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_ADMIN.click();
        break;
      case CATEGORY:
        info("Add Category view type");
        evt.clickByJavascript(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_CATEGORIES, 2);
        break;
      case ICON:
        info("Add Icon view type");
        evt.clickByJavascript(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_ICONS, 2);
        break;
      case LIST:
        info("Add List view type");
        evt.clickByJavascript(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_LIST, 2);
        break;
      case WEB:
        info("Add WEB view type");
        evt.clickByJavascript(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_WEB, 2);
        break;
      default:
        info("Add WEB view type");
        evt.clickByJavascript(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_WEB, 2);
        break;
      }
    }
    info("Save all changes");
    ELEMENT_ECM_EXPLORER_DRIVES_SAVE_FORM.click();
    info("Finished adding a drive");
  }

  /**
   * @param name
   * @param applyViews (All the view want to be check have to be specified, the
   *          other will be uncheck)
   */
  public void editDrives(String name, specificView[] applyViews, String... views) {
    executeJavaScript("window.scrollBy(0,-250);");
    $(byText(name)).parent().parent().find(byClassName("uiIconEditInfo")).click();
    ELEMENT_ECM_EXPLORER_APPLY_VIEWS_FORM_EDIT.click();
    $(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_ADMIN).click();
    evt.uncheck(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_CATEGORIES, 2);
    evt.uncheck(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_ICONS, 2);
    evt.uncheck(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_LIST, 2);
    evt.uncheck(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_WEB, 2);
    for (specificView arrayElement : applyViews) {
      switch (arrayElement) {
      case ADMIN:
        evt.check(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_ADMIN, 2);
        break;
      case CATEGORY:
        evt.check(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_CATEGORIES, 2);
        break;
      case ICON:
        evt.check(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_ICONS, 2);
        break;
      case LIST:
        evt.check(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_LIST, 2);
        break;
      case WEB:
        evt.check(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_WEB, 2);
        break;
      case ITEM:
        evt.check(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_ITEM.replace("$item", views[0]), 2);
        break;
      default:
        evt.check(ELEMENT_ECM_EXPLORER_APPLY_VIEWS_CHECKBOX_WEB, 2);
        break;
      }
    }
    ELEMENT_ECM_EXPLORER_DRIVES_SAVE_FORM.click();
  }

  /**
   * Delete a drive
   * 
   * @param name
   */
  public void deleteDrives(String name) {
    $(byText(name)).parent().parent().find((ELEMENT_BTN_DELETE_DRIVE)).click();

    alert.acceptAlert();
    $(byText(name)).shouldNot(Condition.exist);
  }

  /**
   * Add a Tags
   * 
   * @param tagName
   * @param occurenceNumber
   * @param htmlStyle
   */
  public void addTags(String tagName, String occurenceNumber, String htmlStyle) {
    evt.click(ELEMENT_ECM_EXPLORER_TAGS_ADD_STYLE_BUTTON);
    evt.type(ELEMENT_ECM_EXPLORER_TAGS_ADD_NAME_FORM, tagName, true);
    evt.type(ELEMENT_ECM_EXPLORER_TAGS_NUMBER_OCCURENCE_FORM, occurenceNumber, true);
    evt.type(ELEMENT_ECM_EXPLORER_TAGS_HTML_STYLE_FORM, htmlStyle, true);
    evt.click(ELEMENT_ECM_EXPLORER_TAGS_UPDATE_FORM);
  }

  /**
   * Update a Tag
   * 
   * @param tagName
   * @param occurences
   * @param html
   */
  public void updateTags(String tagName, String occurences, String html) {
    evt.click(By.xpath(ELEMENT_ECM_EXPLORER_TAGS_EDIT_LIST.replace("{$name}", tagName)));
    if (occurences != null)
      evt.type(ELEMENT_ECM_EXPLORER_TAGS_NUMBER_OCCURENCE_FORM, occurences, true);
    if (html != null)
      evt.type(ELEMENT_ECM_EXPLORER_TAGS_HTML_STYLE_FORM, html, true);
    evt.click(ELEMENT_ECM_EXPLORER_TAGS_UPDATE_FORM);
  }

  /**
   * Delete a tag
   * 
   * @param tagName
   */
  public void deleteTags(String tagName) {
    evt.click(By.xpath(ELEMENT_ECM_EXPLORER_TAGS_DELETE_LIST.replace("{$name}", tagName)));
    alert.acceptAlert();
    evt.waitForElementNotPresent(By.xpath(ELEMENT_ECM_EXPLORER_TAGS_DELETE_LIST.replace("{$name}", tagName)));

  }

  /**
   * Delete a category by name
   * 
   * @param name
   */
  public void deleteCategories(String name) {
    info("Delete the category");
    info("Click on Delete button of the category");

    $(byText(name)).parent().parent().find(byClassName("uiIconDelete")).click();
    info("Click on Ok button of the alert popup");
    alert.acceptAlert();
    info("Verify that the category is deleted");
    $(byText(name)).shouldNot(Condition.exist);
    info("The category is deleted succcessfully");
  }

  /**
   * Add a new category
   * 
   * @param name
   * @param nameAction
   * @param lifeCycle
   * @param targetPath
   */
  public void addCategories(String name, String nameAction, String lifeCycle, String targetPath, Object... params) {
    info("Add a category");
    $(ELEMENT_ECM_ADVANCED_CATEGORIES_ADD_CATEGORIES).click();
    info("Type a name for the category");
    $(ELEMENT_ECM_ADVANCED_CATEGORIES_NAME_FORM).setValue(name);
    if (params.length > 0) {
      String workspace = (String) params[0];
      String pathWorkspace = (String) params[1];
      info("Select the workspace:" + workspace + " for the category");
      $(ELEMENT_ECM_ADVANCED_CATEGORIES_WORKSPACE_SELECT_FORM).selectOption(workspace);
      info("Click on Add button of home path form");
      $(ELEMENT_ECM_ADVANCED_CATEGORIES_ADD_HOME_PATH_FORM).click();
      if (pathWorkspace == "root") {
        info("if workspace is root, click on ROOT node");
        $(ELEMENT_ECM_ADVANCED_CATEGORIES_ROOT_NODE_ACTION_FORM).click();
      }
    }
    info("Click on Next button of the step 1");
   $(ELEMENT_ECM_ADVANCED_CATEGORIES_NEXT_1STPAGE_FORM).click();

    info("select permission");
    if (params.length > 2) {
      Boolean read = (Boolean) params[2];
      Boolean modify = (Boolean) params[3];
      Boolean remove = (Boolean) params[4];
      String group = (String) params[5];
      String mem = (String) params[6];
      $(ELEMENT_PERMISSION_SELECTMEMBERSHIP).click();
      ecmsPerm.selectGroupMembershipOfCat(group, mem);
      ecmsPerm.selectCheckBoxRight(read, modify, remove);
      info("Click on Save button");
      evt.click(ELEMENT_PERMISSION_SAVE);
      ecmsPerm.deletePermissionNode("any");
      ecmsPerm.deletePermissionNode("*:/platform/users");
    }
    info("Click on Next button of the step 2");
    $(ELEMENT_ECM_ADVANCED_CATEGORIES_NEXT_2NDPAGE_FORM).click();
    info("Type a name for the action of the category");
    $(ELEMENT_ECM_ADVANCED_CATEGORIES_NAME_ACTION_FORM).setValue(nameAction);
    info("Select a lifeCycle");
    $(ELEMENT_ECM_ADVANCED_CATEGORIES_LIFECYCLE_FORM).selectOption(lifeCycle);
    info("Click on target path on action form");
    $(ELEMENT_ECM_ADVANCED_CATEGORIES_OPEN_TARGETPATH_ACTION_FORM).click();
    if (targetPath == "root") {
      info("if targetPath is root, select ROOT node");
      $(ELEMENT_ECM_ADVANCED_CATEGORIES_ROOT_NODE_ACTION_FORM).click();
    }
    info("Save all changes");
    $(ELEMENT_ECM_ADVANCED_CATEGORIES_SAVE_FORM).click();
    info("Close the form");
    $(ELEMENT_ECM_ADVANCED_CATEGORIES_CLOSE_FORM).click();
    info("Finish adding the category");
  }

  /**
   * Edit a category
   * 
   * @param name
   * @param nameAction
   * @param lifeCycle
   * @param targetPath
   * @param workspace
   * @param pathWorkspace
   */
  public void editCategories(String name,
                             String nameAction,
                             String lifeCycle,
                             String targetPath,
                             String workspace,
                             String pathWorkspace) {
    info("Edit the Category");
    info("Click on Edit button of the category in the list");
    $(byXpath(ELEMENT_ECM_ADVANCED_CATEGORIES_EDIT_FORM.replace("{$name}", name))).click();
    info("Click on Previous button of step4");
    $(ELEMENT_ECM_ADVANCED_CATEGORIES_PREVIOUS_4THPAGE).click();
    info("Click on Previous button of step3");
    $(ELEMENT_ECM_ADVANCED_CATEGORIES_PREVIOUS_3RDPAGE).click();
    info("Click on Previous button of step2");
    $(ELEMENT_ECM_ADVANCED_CATEGORIES_PREVIOUS_2NDPAGE).click();
    if (!workspace.isEmpty()) {
      info("Select the workspace:" + workspace + " for the category");
      $(ELEMENT_ECM_ADVANCED_CATEGORIES_WORKSPACE_SELECT_FORM).selectOption(workspace);
      info("Click on Add button of home path form");
      $(ELEMENT_ECM_ADVANCED_CATEGORIES_ADD_HOME_PATH_FORM).click();
      if (pathWorkspace == "root") {
        info("if workspace is root, click on ROOT node");
        $(ELEMENT_ECM_ADVANCED_CATEGORIES_ROOT_NODE_ACTION_FORM).click();
      }
    }
    info("Click on Next butotn of step 1");
    $(ELEMENT_ECM_ADVANCED_CATEGORIES_NEXT_1STPAGE_FORM).click();
    info("Click on Next button of step 2");
    $(ELEMENT_ECM_ADVANCED_CATEGORIES_NEXT_2NDPAGE_FORM).click();
    info("Click on Save button");
    $(ELEMENT_ECM_ADVANCED_CATEGORIES_SAVE_FORM).click();
    info("Close the form");
    $(ELEMENT_ECM_ADVANCED_CATEGORIES_CLOSE_FORM).click();
    info("Finished editing the category");
  }

  /**
   * Add a new action type
   * 
   * @param name
   * @param script
   * @param variables
   */
  public void addActionType(String name, String script, String variables) {
    info("Add a action type");
    $(ELEMENT_ADD_ACTION_TYPE).click();
    info("Type a name");
    $(ELEMENT_ECM_ACTION_NAME_FORM).setValue(name);
    if (!script.isEmpty()) {
      info("Select a script in form");
      $(ELEMENT_ECM_ACTION_SCRIPT_FORM).selectOption(script);
    }
    if (!variables.isEmpty()) {
      info("Type a variable in form");
      $(ELEMENT_ECM_ACTION_VARIABLES_FORM).setValue(variables);
    }
    info("Save all changes");
    $(ELEMENT_ECM_ACTION_SAVE_FORM).waitUntil(Condition.visible,Configuration.timeout).click();
  }

  /**
   * Edit a action type
   * 
   * @param name
   * @param newName
   * @param script
   * @param variables
   */
  public void editActionType(String name, String newName, String script, String variables) {
    info("Edit a action type");
    info("Select a action in the list");
    $(byXpath(ELEMENT_ECM_ACTION_EDIT_LIST.replace("{$name}", name))).click();
    info("Add a action type");
    $(ELEMENT_ECM_ACTION_NAME_FORM).setValue(newName);
    if (!script.isEmpty()) {
      info("Select a script in form");
      $(ELEMENT_ECM_ACTION_SCRIPT_FORM).selectOption(script);
    }
    if (!variables.isEmpty()) {
      info("Type a variable in form");
      $(ELEMENT_ECM_ACTION_VARIABLES_FORM).setValue(variables);
    }
    info("Save all changes");
    $(ELEMENT_ECM_ACTION_SAVE_FORM).waitUntil(Condition.visible,Configuration.timeout).click();
    info("Verify that the new action is added");
    $(byXpath(ELEMENT_ECM_ACTION_LIST.replace("{$name}", newName))).waitUntil(Condition.visible,Configuration.timeout);
    info("Verify that the old action is replaced");
    $(byXpath(ELEMENT_ECM_ACTION_LIST.replace("{$name}", name))).shouldNot(Condition.visible);
    info("End of Editing action type");
  }

  /**
   * Delete a action type
   * 
   * @param name
   */
  public void deleteAction(String name) {
    info("Delete a action");
    info("Click on Delete button of the action");
    $(byXpath(ELEMENT_ECM_ACTION_DELETE_LIST.replace("{$name}", name))).click();
    info("Click on OK button on Alert popup");
    alert.acceptAlert();
    info("Verify that the action is deleted");
    $(byXpath(ELEMENT_ECM_ACTION_LIST.replace("{$name}", name))).shouldNot(Condition.visible);
    info("The action is deleted successfully");
  }

  /**
   * Add a new script
   * 
   * @param name
   * @param content
   * @param script
   */
  public void addScripts(String name, String content, String script) {
    info("Add a script");
    $(ELEMENT_ECM_ADVANCED_SCRIPT_ADD_SCRIPT).click();
    info("Type a name for the script");
    $(ELEMENT_ECM_ADVANCED_SCRIPT_NAME_FORM).setValue(name);
    if (!script.isEmpty()) {
      info("Type a script on the form");
      $(ELEMENT_ECM_ADVANCED_SCRIPT_SCRIPT_FORM).setValue(script);
    }
    if (!content.isEmpty()) {
      info("Type a content for the script");
      $(ELEMENT_ECM_ADVANCED_SCRIPT_CONTENT_FORM).setValue(content);
    }
    info("Save all changes");
    $(ELEMENT_ECM_ADVANCED_SCRIPT_SAVE_FORM).click();
    info("Finish adding the script");
    $(byXpath(ELEMENT_ECM_ADVANCED_SCRIPT_LIST.replace("{$name}", name))).waitUntil(Condition.visible,Configuration.timeout);
  }

  /**
   * Edit a Script
   * 
   * @param oldname
   * @param name
   * @param content
   * @param script
   */
  public void EditScripts(String oldname, String name, String content, String script) {
    info("Edit the script");
    info("Click on Edit button of the script on the list");
    $(byXpath(ELEMENT_ECM_ADVANCED_SCRIPT_EDIT_LIST.replace("{$name}", oldname))).click();
    info("Type the mame for the script");
    $(ELEMENT_ECM_ADVANCED_SCRIPT_NAME_FORM).setValue(name);
    if (!script.isEmpty()) {
      info("Type a script: " + script + " on the script form");
      $(ELEMENT_ECM_ADVANCED_SCRIPT_SCRIPT_FORM).setValue(script);
    }
    if (!content.isEmpty()) {
      info("Type a content: " + content + " for the script");
      $(ELEMENT_ECM_ADVANCED_SCRIPT_CONTENT_FORM).setValue(content);
    }
    info("Save all changes");
    $(ELEMENT_ECM_ADVANCED_SCRIPT_SAVE_FORM).click();
    info("Verify that the script is saved with new changes");
    $(byXpath(ELEMENT_ECM_ADVANCED_SCRIPT_LIST.replace("{$name}", name))).waitUntil(Condition.visible,Configuration.timeout);
    info("Verify that old script is changed");
    $(byXpath(ELEMENT_ECM_ADVANCED_SCRIPT_LIST.replace("{$name}", oldname))).shouldNot(Condition.visible);
    info("Editing the script is changed successfully");
  }

  /**
   * Delete a script by name
   * 
   * @param name
   */
  public void deleteScripts(String name) {
    info("Click on Delete button of the script");
    $(byXpath(ELEMENT_ECM_ADVANCED_SCRIPT_DELETE_LIST.replace("{$name}", name))).click();
    info("Click on OK button of the alert popup");
    alert.acceptAlert();
    info("Verify that the script is deleted");
    $(byXpath(ELEMENT_ECM_ADVANCED_SCRIPT_LIST.replace("{$name}", name))).shouldNot(Condition.visible);
    info("The script is deleted successfullyy");
  }

  /**
   * Add a new query
   * 
   * @param name
   * @param queryType
   * @param statement
   * @param permissions
   */
  public void addQueries(String name, String queryType, String statement, String... permissions) {
    info("Add a query");
    info("Click on Add button");
    $(ELEMENT_ECM_ADVANCED_QUERIES_ADD_QUERIES).click();
    info("Type a name for the query");
    $(ELEMENT_ECM_ADVANCED_QUERIES_NAME_FORM).setValue(name);
    if (!queryType.isEmpty()) {
      info("Select the type:" + queryType + "for the query");
      $(ELEMENT_ECM_ADVANCED_QUERIES_QUERY_TYPE_FORM).selectOption(queryType);
    }
    if (!statement.isEmpty()) {
      info("Type a text: " + statement + "for the statement of the query");
      $(ELEMENT_ECM_ADVANCED_QUERIES_STATEMENT_FORM).setValue(statement);
    }
    info("Select permission form of the query");
    $(ELEMENT_ECM_ADVANCED_QUERIES_PERMISSION_FORM).click();
    if (permissions.length < 2) {
      info("Set permission of the query is:" + permissions[0]);
      $(AdministrationLocator.ELEMENT_PERMISSION_ANY).click();
    } else {
      info("select permission");
      ecmsPerm.selectGroupMembershipOfQuery(permissions[0], permissions[1]);
    }
    info("Save all changes");
    $(ELEMENT_ECM_ADVANCED_QUERIES_SAVE_FORM).click();
    info("Adding the query is success");
  }

  /**
   * Edit a query
   * 
   * @param name
   * @param queryType
   * @param statement
   * @param permission
   */
  public void editQueries(String name, String queryType, String statement, String permission) {
    info("Click on Edit button of the query");
    $(byXpath(ELEMENT_ECM_ADVANCED_QUERIES_EDIT_BUTTON.replace("{$name}", name))).click();
    if (!queryType.isEmpty()) {
      info("Select a type for the query");
      $(ELEMENT_ECM_ADVANCED_QUERIES_QUERY_TYPE_FORM).selectOption(queryType);
    }
    if (!statement.isEmpty()) {
      info("Type the statement: " + statement + " for the query");
      $(ELEMENT_ECM_ADVANCED_QUERIES_STATEMENT_FORM).setValue(statement);
    }
    if (!permission.isEmpty()) {
      info("Select permission for the query");
      $(ELEMENT_ECM_ADVANCED_QUERIES_PERMISSION_FORM).click();
    }
    info("Save all changes");
    $(ELEMENT_ECM_ADVANCED_QUERIES_SAVE_FORM).click();
    info("Editing is finished");
  }

  /**
   * Delete a queri
   * 
   * @param name
   */
  public void deleteQueries(String name) {
    info("Delete a query");
    info("Click on Delete button of the query");
    $(byXpath(ELEMENT_ECM_ADVANCED_QUERIES_DELETE_BUTTON.replace("{$name}", name))).click();
    info("Click on OK button of the alert popup");
    alert.acceptAlert();
    info("Verify that the query is deleted");
    $(byXpath(ELEMENT_ECM_ADVANCED_QUERIES_LIST.replace("{$name}", name))).shouldNot(Condition.visible);
    info("The query is deleted successfully");
  }

  /**
   * Add a node type
   * 
   * @param name
   * @param superTypes
   * @param opParams
   */
  public void addNodeType(String name, String superTypes, Object... opParams) {
    String mixinType = (String) (opParams.length > 0 ? opParams[0] : null);
    $(ELEMENT_ECM_REPOSITORY_NODES_ADD).click();
    $(ELEMENT_ECM_REPOSITORY_NODES_NAME_FORM).setValue(name);
    $(byXpath("//span[@class='PopupTitle popupTitle' and text()='Add/Edit Node Type Definitions']")).dragAndDropTo($(byXpath("//div[@class='UITableColumnContainer']")));
    $(ELEMENT_ECM_REPOSITORY_NODES_SUPER_TYPES_FORM).setValue(superTypes);
    if (mixinType != null)
      $(ELEMENT_ECM_REPOSITORY_NODES_MIXIN_TYPES).selectOption(mixinType);
    $(byXpath("//span[@class='PopupTitle popupTitle' and text()='Add/Edit Node Type Definitions']")).dragAndDropTo($(byXpath("//div[@class='UITableColumnContainer']")));
    $(ELEMENT_ECM_REPOSITORY_NODES_SAVE_FORM).waitUntil(Condition.visible,Configuration.timeout).click();
    $(ELEMENT_ECM_REPOSITORY_NODES_OK_FORM).waitUntil(Condition.visible,Configuration.timeout).click();
  }

  /**
   * Search a Node and check it
   * 
   * @param name
   * @param types
   */
  public void searchNodeAndCheckIt(String name, String types) {
    evt.type(ELEMENT_ECM_REPOSITORY_NODES_SEARCH_NODE, name, true);
    evt.waitForAndGetElement(ELEMENT_ECM_REPOSITORY_NODES_SEARCH_NODE).sendKeys(Keys.ENTER);
    evt.click(By.xpath(ELEMENT_ECM_REPOSITORY_NODES_SHOW_SPECIFIC_NODE.replace("{$node}", name)));
    evt.waitForAndGetElement(By.xpath(ELEMENT_ECM_REPOSITORY_NODES_CHECK_SUPER_TYPES.replace("{$types}", types)));
    evt.click(ELEMENT_ECM_REPOSITORY_NODES_CLOSE_FORM);
  }

  /**
   * register Name space
   * 
   * @param nameSpacePrefix
   * @param url
   */
  public void registerNamespace(String nameSpacePrefix, String url) {
    evt.click(ELEMENT_ECM_REPOSITORY_NAMESPACES_ADD);
    evt.type(ELEMENT_ECM_REPOSITORY_NAMESPACES_FORM_NAME, nameSpacePrefix, true);
    evt.type(ELEMENT_ECM_REPOSITORY_NAMESPACES_URI_FORM, url, true);
    evt.click(ELEMENT_ECM_REPOSITORY_NAMESPACES_SAVE_FORM);
  }

  /**
   * Add a document in Template
   * 
   * @param label
   * @param permission
   */
  public void addDocumentInTemplates(String label, String... permission) {
    ELEMENT_ECM_TEMPLATES_DOCUMENTS_ADD_DOCUMENT.scrollTo().click();
    $(ELEMENT_ECM_TEMPLATES_DOCUMENTS_LABEL_FORM).setValue(label);
    $(ELEMENT_ECM_COMMON_ADD_PERMISSION_BUTTON).click();
    if (permission[0] == "any")
      $(AdministrationLocator.ELEMENT_PERMISSION_ANY).click();
    else {
      ecmsPerm.selectGroupMembershipOfLock(permission[0], permission[1]);

    }
    ELEMENT_ECM_TEMPLATES_DOCUMENTS_SAVE_FORM.scrollTo().click();
  }

  /**
   * Edita Document in Templates
   * 
   * @param oldName
   * @param newName
   */
  public void editDocumentInTemplates(String oldName, String newName) {
    $(byClassName("uiIconEdit ")).click();
    $(ELEMENT_ECM_TEMPLATES_DOCUMENTS_LABEL_FORM).setValue(newName);
    $(ELEMENT_ECM_TEMPLATES_DOCUMENTS_SAVE_EDIT_FORM).scrollTo().click();
    $(byText(newName)).waitUntil(Condition.appears, Configuration.timeout);

  }

  /**
   * Add a template into list
   * 
   * @param name
   * @param nameTemplate
   * @param content
   */
  public void addTemplateInList(String name, String nameTemplate, String content) {
    $(ELEMENT_ECM_TEMPLATES_LIST_ADD_LIST).click();
    $(ELEMENT_ECM_TEMPLATES_LIST_TEMPLATE_NAME_FORM).setValue(nameTemplate);
    $(ELEMENT_ECM_TEMPLATES_LIST_NAME_FORM).setValue(name);
    $(ELEMENT_ECM_TEMPLATES_LIST_CONTENT_FORM).setValue(content);
    $(byXpath("//span[@class='PopupTitle popupTitle' and text()='Add List Template']")).dragAndDropTo($(byXpath("//div[@class='UITableColumnContainer']")));
    $(ELEMENT_ECM_TEMPLATES_LIST_SAVE_FORM).click();
    $(ELEMENT_ECM_TEMPLATES_LIST_SAVE_FORM).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
    /*
     * This loop is for checking if template is not exist in current page, click on
     * next button to display the next page until finding the template added
     */
    final int NUMBER_TOTAL_PAGE_OF_LIST_TEMPLATE = 9;
    for (int i = 0; i < NUMBER_TOTAL_PAGE_OF_LIST_TEMPLATE; i++) {
      if ($(byXpath(ELEMENT_ECM_TEMPLATES_LIST_CHECK_LIST.replace("{$name}",
                                                                  name)
                                                         .replace("{$template}", nameTemplate)))
                                                                                                .is(Condition.not(Condition.visible))) {
        $(ELEMENT_ICON_NEXT_ARROW).click();
      }
      if ($(byXpath(ELEMENT_ECM_TEMPLATES_LIST_CHECK_LIST.replace("{$name}", name)
                                                         .replace("{$template}", nameTemplate)))
                                                                                                .waitUntil(Condition.visible,
                                                                                                           Configuration.timeout)
                                                                                                .isDisplayed()) {
        break;
      }
    }
    $(byXpath(ELEMENT_ECM_TEMPLATES_LIST_CHECK_LIST.replace("{$name}", name)
                                                   .replace("{$template}", nameTemplate))).waitUntil(Condition.visible,
                                                                                                     Configuration.timeout);

  }

  /**
   * Edit a Template in List
   * 
   * @param oldTem
   * @param newName
   */
  public void editTemplateNameInList(String oldTem, String newName) {
    $(byXpath(ELEMENT_ECM_TEMPLATES_LIST_EDIT_LIST.replace("{$name}", oldTem))).click();
    $(ELEMENT_ECM_TEMPLATES_LIST_NAME_FORM).setValue(newName);
    $(byXpath("//span[@class='PopupTitle popupTitle' and text()='Edit List Template']")).dragAndDropTo($(byXpath("//div[@class='UITableColumnContainer']")));
    $(ELEMENT_ECM_TEMPLATES_LIST_SAVE_FORM).waitUntil(Condition.visible,Configuration.timeout).click();
    $(byXpath(ELEMENT_ECM_TEMPLATES_LIST_CHECK_BY_NAME.replace("{$name}", newName))).waitUntil(Condition.visible,
                                                                                               Configuration.timeout);
  }

  /**
   * Delete a Template in List
   * 
   * @param name
   */
  public void deleteTemplateList(String name) {
    $(byXpath(ELEMENT_ECM_TEMPLATES_LIST_DELETE_LIST.replace("{$name}", name))).click();
    alert.acceptAlert();
    $(byXpath(ELEMENT_ECM_TEMPLATES_LIST_DELETE_LIST.replace("{$name}", name))).waitUntil(Condition.not(Condition.visible),
                                                                                          Configuration.timeout);
  }

  /**
   * Edit a Metadata name and Permission
   * 
   * @param oldName
   * @param newName
   * @param permission
   */
  public void editeMetadataNameAndPermission(String oldName, String newName, String permission) {
    $(byXpath(ELEMENT_ECM_TEMPLATES_METADATA_FORM_EDIT.replace("{$name}", oldName))).click();
    $(ELEMENT_ECM_TEMPLATES_METEDATA_FORM_EDIT_LABEL).setValue(newName);
    $(ELEMENT_ECM_COMMON_ADD_PERMISSION_BUTTON).click();
    if (permission == "any")
      $(AdministrationLocator.ELEMENT_PERMISSION_ANY).click();
    $(ELEMENT_ECM_TEMPLATES_METADATA_FORM_APPLY).click();
    $(byXpath(ELEMENT_ECM_TEMPLATES_LIST_DELETE_LIST.replace("{$name}", oldName))).waitUntil(Condition.not(Condition.visible),
                                                                                             Configuration.timeout);
  }

  /**
   * Delete a Metadata
   * 
   * @param name
   */
  public void deleteMetadata(String name) {
    $(byXpath(ELEMENT_ECM_TEMPLATES_METADATA_FORM_DELETE.replace("{$name}", name))).click();
    alert.acceptAlert();
    $(byXpath(ELEMENT_ECM_TEMPLATES_METADATA_FORM_DELETE.replace("{$name}", name))).waitUntil(Condition.not(Condition.visible),
                                                                                              Configuration.timeout);
    $(ELEMENT_ECM_TEMPLATES_METADATA_FORM_OK_FORM).click();
  }

  /**
   * Select a function as: Explorer, Advanced, Template and Repositoty
   */
  public enum mainEcmFunctions {
    EXPLORER, ADVANCED, TEMPLATES, REPOSITORY;
  }

  /**
   * Select a subfunction as Documents, List, Metadata, Drives, Tags,
   * NameSpaces,Nodestypes,Locks, Categories, Queries, Scripts Actions
   */
  public enum specificEcmFunctions {
    DOCUMENTS, LIST, METADATA, VIEW, DRIVES, TAGS, NAMESPACES, NODESTYPES, LOCKS, CATEGORIES, QUERIES, SCRIPTS, ACTIONS;
  }

  /**
   * view option
   */
  public enum specificView {
    ADMIN, CATEGORY, LIST, ICON, WEB, ITEM
  }

}
