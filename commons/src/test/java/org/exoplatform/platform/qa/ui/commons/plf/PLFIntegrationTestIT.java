package org.exoplatform.platform.qa.ui.commons.plf;

import static com.codeborne.selenide.Condition.visible;
import static org.exoplatform.platform.qa.ui.selenium.locator.HomePageLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.core.context.Smoke;

public class PLFIntegrationTestIT extends Base {

  /**
   * <li>Case ID:120872.</li>
   * <li>Test Case Name: Check Home page.</li>
   * <li>Pre-Condition:</li>
   * <li>Post-Condition:</li>
   */
  @Test
  @Smoke
  @Tag("smoke")
  @Tag("debug")
  public void test01_CheckHomePage() {
    info("Test 1: Check Home page");
    /*
     * Step Number: 1 Step Name: Step 1: Show Intranet Home page Step Description: -
     * Login intranet site by root Input Data: Expected Outcome: Home page is show
     * properly, inlcuding activity stream at the center, gadgets that are well
     * displayed at the right
     */
    info("Verify that Home page is shown");
    ELEMENT_PLF_HOMEPAGE_DISPLAY.shouldBe(visible);

    info("Verify that Activity stream is shown on the home page");
    ELEMENT_PLF_HOMEPAGE_ACTIVITY_PORTLET.shouldBe(visible);

    info("Verify that Gadgets is shown on right of the page");
    ELEMENT_PLF_HOMEPAGE_GADGET_PORTLET.shouldBe(visible);
  }

}
