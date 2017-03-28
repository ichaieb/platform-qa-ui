package org.exoplatform.platform.qa.ui.commons.plf;

import static org.exoplatform.platform.qa.ui.selenium.Utils.getRandomNumber;
import static org.exoplatform.platform.qa.ui.selenium.locator.social.SocialLocator.*;
import static org.exoplatform.platform.qa.ui.selenium.logger.Logger.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import org.exoplatform.platform.qa.ui.commons.Base;
import org.exoplatform.platform.qa.ui.selenium.platform.HomePagePlatform;
import org.exoplatform.platform.qa.ui.selenium.platform.social.SpaceManagement;

/**
 * @author eXo
 */
public class Plf_Navigation_SpaceNavigation extends Base {

  HomePagePlatform homePagePlatform;

  SpaceManagement  spaceManagement;

  @BeforeEach
  public void setupBeforeMethod() {
    info("Start setUpBeforeMethod");

    homePagePlatform = new HomePagePlatform(this);
    spaceManagement = new SpaceManagement(this);
  }

  /**
   * <li>Case ID:120901.</li>
   * <li>Test Case Name: Show the space menu.</li>
   * <li>Pre-Condition: a space is created</li>
   * <li>Case ID:120902.</li>
   * <li>Test Case Name: Remove application of space's toolbar.</li>
   */
  @Test
  @Tag("smoke")
  public void test01_ShowTheSpaceMenu() {
    info("Test 1: Show the space menu");
    String space1 = "space" + getRandomNumber();
    homePagePlatform.goToMySpaces();
    spaceManagement.addNewSpaceSimple(space1, space1);

    /*
     * Step Number: 1 Step Name: Show space applications Step Description: -
     * Connect to Intranet - Open a Space Input Data: Expected Outcome: - The
     * Horizontal toolbar is displayed - On the left of the Space toolbar, we
     * display the icon and name of the current space. - All applications
     * dedicated to the space are shown on space menu - Click on each
     * applications, the application will show up in the main page
     */
    info("Verify the expected outcome");
    waitForAndGetElement(ELEMENT_SPACE_MENU_ACTIVITY_STREAM).isDisplayed();
    waitForAndGetElement(ELEMENT_SPACE_MENU_AGENDA);
    waitForAndGetElement(ELEMENT_SPACE_MENU_WIKI).isDisplayed();
    waitForAndGetElement(ELEMENT_SPACE_MENU_DOCUMENTS).isDisplayed();
    waitForAndGetElement(ELEMENT_SPACE_MENU_SETTINGS).isDisplayed();
    waitForAndGetElement(ELEMENT_SPACE_MENU_ANSWER).isDisplayed();
    waitForAndGetElement(ELEMENT_SPACE_MENU_FORUMS).isDisplayed();

    info("Delete the space");
    homePagePlatform.goToMySpaces();
    // spaceManagement.deleteSpace(space1, false);

  }

}
