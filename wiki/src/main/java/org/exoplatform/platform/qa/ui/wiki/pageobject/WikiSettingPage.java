package org.exoplatform.platform.qa.ui.wiki.pageobject;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.wiki.WikiLocators.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.codeborne.selenide.Condition;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class WikiSettingPage {


  private final TestBase       testBase;

  public ManageAlert           alert;

  private ElementEventTestBase evt;

  public WikiSettingPage(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    alert = new ManageAlert(testBase);
  }

  /**
   * Search a template
   *
   * @param template String
   */
  public void searchTemplate(String template) {

    info("Input a template's name");
    $(ELEMENT_TEMPLATE_SEARCH_TEXTBOX).setValue(template);

    info("Press Enter key");
    testBase.getExoWebDriver().getWebDriver().findElement(ELEMENT_TEMPLATE_SEARCH_TEXTBOX).sendKeys(Keys.ENTER);

    info("Verify that the search results is shown that matchs with keyword");

    $(byId("UIWikiTemplateGrid")).find(byText(template)).should(Condition.exist);

  }

  /**
   * Edit a wiki template
   *
   * @param template String
   * @param newTitle String
   * @param newContent  String
   * @param newDes String
   */
  public void editTemplate(String template, String newTitle, String newDes, String newContent) {
    $(byXpath(ELEMENT_EDIT_TEMPLATE.replace("{$template}", template))).click();
    if (!newTitle.isEmpty()) {
      info("Input the title for the template");
      $(ELEMENT_TITLE_TEMPLATE).setValue(newTitle);
    }

    if (!newDes.isEmpty()) {
      info("Input the description for the template");
      $(ELEMENT_DESCRIPTION_TEMPLATE).setValue(newDes);
    }

    if (!newContent.isEmpty()) {
      info("Input the content for the template");
      $(ELEMENT_CONTENT_TEMPLATE).setValue(newContent);
    }
    saveTemplate();
  }

  /**
   * Delete a template
   *
   * @param template String
   */
  public void deleteTemplate(String template) {
      info("Delete template " + template);
      $(byXpath(ELEMENT_DELETE_TEMPLATE.replace("{$template}", template))).click();
      alert.acceptAlert();
      $(By.xpath(ELEMENT_DELETE_TEMPLATE.replace("{$template}", template))).waitUntil(Condition.not(Condition.visible),Configuration.timeout);
  }

  /**
   * Cancel deleting a template
   *
   * @param template String
   */
  public void deleteTemplateWithCanceling(String template) {
    info("Delete template " + template);
    $(byXpath(ELEMENT_DELETE_TEMPLATE.replace("{$template}", template))).click();
    alert.cancelAlert();
    $(byXpath(ELEMENT_DELETE_TEMPLATE.replace("{$template}", template))).waitUntil(Condition.visible,Configuration.timeout);
  }

  /**
   * Open template tab
   */
  public void goToTemplateTab() {
    info("click on the template tab");
    evt.click(ELEMENT_WIKI_SETTING_TEMPLATE_TAB);

  }

  /**
   * Open Permission tab
   */
  public void goToPermissionTab() {
    info("Click on Permission tab");
    $(ELEMENT_WIKI_SETTING_PERMISSION_TAB).click();

  }

  /**
   * Save all changes for the template
   */
  public void saveTemplate() {
    info("Click on Save template");
    $(ELEMENT_SAVE_TEMPLATE).click();
    $(ELEMENT_SAVE_TEMPLATE).waitUntil(Condition.not(Condition.visible), Configuration.timeout);
  }

  /**
   * Cancel all changes for the template
   */
  public void cancelTemplate() {
    info("Click on Cancel template");
    evt.click(ELEMENT_CANCEL_TEMPLATE);

  }

  /**
   * Add new a template
   *
   * @param title String
   * @param des String
   * @param content String
   */
  public void addTemplate(String title, String des, String content) {
    info("Click on Add more Template button");
    $(ELEMENT_WIKI_SETTING_ADD_MORE_TEMPALTE).click();

    if (!title.isEmpty()) {
      info("Input the title for the template");
      $(ELEMENT_TITLE_TEMPLATE).setValue(title);
    }

    if (!des.isEmpty()) {
      info("Input the description for the template");
      $(ELEMENT_DESCRIPTION_TEMPLATE).setValue(des);
    }

    if (!content.isEmpty()) {
      info("Input the content for the template");
      $(ELEMENT_CONTENT_TEMPLATE).setValue(content);
    }
  }

}
