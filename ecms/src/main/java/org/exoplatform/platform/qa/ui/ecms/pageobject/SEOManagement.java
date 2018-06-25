package org.exoplatform.platform.qa.ui.ecms.pageobject;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.exoplatform.platform.qa.ui.selenium.locator.ecms.ECMSLocator.*;

import org.exoplatform.platform.qa.ui.selenium.ManageAlert;
import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.testbase.ElementEventTestBase;

public class SEOManagement {

  private final TestBase       testBase;

  public ManageAlert           magAlert;

  private ElementEventTestBase evt;

  public SEOManagement(TestBase testBase) {
    this.testBase = testBase;
    this.evt = testBase.getElementEventTestBase();
    this.magAlert = new ManageAlert(testBase);
  }

  /**
   * Delete a added language
   * 
   * @param language String
   */
  public void deleteLanguage(String language) {
    $(byXpath(ELEMENT_SEO_SELECTED_LANGUAGE.replace("${language}", language))).click();
    $(ELEMENT_SEO_DELETE).click();
    magAlert.acceptAlert();
    $(ELEMENT_SEO_CLOSE).click();
  }

}
