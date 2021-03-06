package org.exoplatform.platform.qa.ui.gatein.pageobject;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.gatein.GateinLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class PortalGroupNavigation {

  private final TestBase       testBase;

  public ManageAlert           alert;

  public PortalManagePages     portMg;

  private ElementEventTestBase evt;

  public PortalGroupNavigation(TestBase testBase) {
    this.testBase = testBase;
    this.alert = new ManageAlert(testBase);
    this.portMg = new PortalManagePages(testBase);
    this.evt = testBase.getElementEventTestBase();
  }

  /**
   * function: add new navigation for group
   *
   * @param groupName name of group you want to add navigation
   */
  public void addNewNavigationForGroup(String groupName) {
    info("Add navigation for group " + groupName);
    $(byText("Add Navigation")).click();
    $(byText("/" + groupName)).waitUntil(Condition.appears, Configuration.timeout);
    /*
     * the order of the groups in the add navigation page is random, so we defined
     * this condition to fix the location of the linkText "add navigation" that we
     * should clic on it
     */

    $(byText("/" + groupName)).parent().find(byText("Add Navigation")).click();

    $(ELEMENT_CANCEL_BUTON).click();
    $(byText(groupName)).waitUntil(Condition.appears, Configuration.timeout);
  }

  /**
   * function delete navigation for group
   *
   * @param groupName name of Group
   */
  public void deleteNavigationForGroup(String groupName) {

    /*
     * info("Delete navigation of group " + groupName);
     *
     */
    $(byText(groupName)).parent().find(byXpath("//*[@id=\"UIGroupNavigationGrid\"]/table[1]/tbody/tr[1]/td[4]/a")).click();
    alert.acceptAlert();
    $(byText(groupName)).shouldNot(Condition.exist);
  }
}
