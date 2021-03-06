/*
 * Copyright (C) 2003-2017 eXo Platform SAS.
 *
 * This file is part of eXo PLF:: QA - UI - Selenium (Legacy Code).
 *
 * eXo PLF:: QA - UI - Selenium (Legacy Code) is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * eXo PLF:: QA - UI - Selenium (Legacy Code) software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with eXo PLF:: QA - UI - Selenium (Legacy Code); if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.platform.qa.ui.selenium.testbase;

import static com.codeborne.selenide.Selenide.$;

import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import org.exoplatform.platform.qa.ui.selenium.TestBase;
import org.exoplatform.platform.qa.ui.selenium.logger.Logger;

public class ElementEventTestBase {

  private final TestBase testBase;

  private int            WAIT_INTERVAL = 1000; // milliseconds

  public ElementEventTestBase(TestBase testBase) {
    this.testBase = testBase;
  }

  /**
   * Scroll to a element on the website
   *
   * @param element webElement
   * @param driver webDriver
   */
  public static void scrollToElement(WebElement element, WebDriver driver) {
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    jse.executeScript("arguments[0].scrollIntoView(true);", element);
  }

  /**
   * Scroll to bottom of the page of website
   *
   * @param driver webDriver
   */
  public static void scrollToBottomPage(WebDriver driver) {
    Logger.info("Scroll to the bottom of the page");
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,"
        + "document.body.scrollHeight,document.documentElement.clientHeight));");
  }

  /**
   * Get element
   *
   * @param locator object
   * @param opParams object
   * @return an element
   */
  public WebElement getElement(Object locator, Object... opParams) {
    By by = locator instanceof By ? (By) locator : By.xpath(locator.toString());
    WebElement elem = null;
    try {
      elem = testBase.getExoWebDriver().getWebDriver().findElement(by);
    } catch (NoSuchElementException e) {

    }
    return elem;
  }

  /**
   * get an element
   *
   * @param locator object
   * @param opParams object
   * @return element
   */
  public WebElement getDisplayedElement(Object locator, Object... opParams) {
    By by = locator instanceof By ? (By) locator : By.xpath(locator.toString());

    WebElement e = null;
    try {
      if (by != null)
        e = testBase.getExoWebDriver().getWebDriver().findElement(by);
      if (e != null) {
        if (isDisplay(by))
          return e;
      }
    } catch (NoSuchElementException ex) {
    } catch (StaleElementReferenceException ex) {
      checkCycling(ex, 10);

      getDisplayedElement(locator);
    } finally {
      testBase.setLoopCount(0);
    }
    return null;
  }

  /**
   * verify element exists or not
   *
   * @param locator Object
   * @return true if element exists false if element doesn't exist
   */
  public boolean isElementPresent(Object locator) {
    return getElement(locator) != null;
  }

  /**
   * verify element exists or not
   *
   * @param locator Object
   * @return true if element doesn't exists false if element exist
   */
  public boolean isElementNotPresent(Object locator) {
    return !isElementPresent(locator);
  }

  /**
   * Get element
   *
   * @param locator locator of element
   * @param opParams opPram[0]: timeout opPram[1]: 0,1 0: No Assert 1: Assert
   * @return an element
   */
  public WebElement waitForAndGetElement(Object locator, Object... opParams) {
    return $((By) locator);
  }

  /**
   * Get element
   *
   * @param locator locator of element
   * @param opParams opPram[0]: timeout opPram[1]: 0,1 0: No Assert 1: Assert
   * @return an element
   */
  public WebElement waitForElementNotPresent(Object locator, int... opParams) {
    WebElement elem = null;
    int timeout = opParams.length > 0 ? opParams[0] : testBase.getDefaultTimeout();
    int isAssert = opParams.length > 1 ? opParams[1] : 1;
    int notDisplayE = opParams.length > 2 ? opParams[2] : 0;

    for (int tick = 0; tick < timeout / WAIT_INTERVAL; tick++) {
      if (notDisplayE == 2) {
        elem = getElement(locator);
        // elem = getDisplayedElement(locator);
      } else {
        elem = getDisplayedElement(locator);
      }
      if (null == elem)
        return null;

    }

    if (isAssert == 1)
      assert false : ("Timeout after " + timeout + "ms waiting for element not present: " + locator);
    Logger.info("Element doesn't disappear after " + timeout / 1000 + "s.");
    return elem;
  }

  /**
   * @param text string
   * @param opts int
   * @return true if text exist false if test is not exist
   */
  public boolean isTextPresent(String text, int... opts) {
    int display = opts.length > 0 ? opts[0] : 1;

    String allVisibleTexts = getText(By.xpath("//body"), display);
    return allVisibleTexts.contains(text);
  }

  /**
   * get text of element
   *
   * @param locator Object
   * @param opts int
   * @return text of element
   */
  public String getText(Object locator, int... opts) {
    WebElement element = null;
    int display = opts.length > 0 ? opts[0] : 1;
    try {
      element = waitForAndGetElement(locator, testBase.getDefaultTimeout(), 1, display);
      return element.getText();
    } catch (StaleElementReferenceException e) {
      checkCycling(e, testBase.getDefaultTimeout() / WAIT_INTERVAL);

      return getText(locator);
    } finally {
      testBase.setLoopCount(0);
    }
  }

  /**
   * get list of element
   *
   * @param xpath string
   * @return list of elements
   */
  public List<WebElement> getElements(String xpath) {
    try {
      return testBase.getExoWebDriver().getWebDriver().findElements(By.xpath(xpath));
    } catch (StaleElementReferenceException e) {
      checkCycling(e, 5);

      return getElements(xpath);
    } finally {
      testBase.setLoopCount(0);
    }
  }

  /**
   * verify text exists or noet
   *
   * @param text string
   * @return true if text exists false if text doesn't exits
   */
  public boolean isTextNotPresent(String text) {
    return !isTextPresent(text);
  }

  /**
   * drag and drop element
   *
   * @param sourceLocator Object
   * @param targetLocator Object
   */
  public void dragAndDropToObject(Object sourceLocator, Object targetLocator) {
    Logger.info("--Drag and drop to object--");
    Actions action = new Actions(testBase.getExoWebDriver().getWebDriver());
    try {
      WebElement source = waitForAndGetElement(sourceLocator);
      WebElement target = waitForAndGetElement(targetLocator);

      action.dragAndDrop(source, target).build().perform();
    } catch (StaleElementReferenceException e) {
      checkCycling(e, testBase.getDefaultTimeout() / WAIT_INTERVAL);

      dragAndDropToObject(sourceLocator, targetLocator);
    } catch (UnhandledAlertException e) {
      try {
        Alert alert = testBase.getExoWebDriver().getWebDriver().switchTo().alert();
        alert.accept();
        switchToParentWindow();
      } catch (NoAlertPresentException eNoAlert) {
      }
    } finally {
      testBase.setLoopCount(0);
    }

  }

  /**
   * Click by using javascript
   *
   * @param locator object
   * @param opParams object
   */
  public void clickByJavascript(Object locator, Object... opParams) {
    int notDisplay = (Integer) (opParams.length > 0 ? opParams[0] : 0);
    WebElement e = null;
    if (locator instanceof WebElement) {
      e = (WebElement) locator;
    } else {
      Logger.info("wait and get element");
      e = waitForAndGetElement(locator, testBase.getDefaultTimeout(), 1, notDisplay);
    }
    ((JavascriptExecutor) testBase.getExoWebDriver().getWebDriver()).executeScript("arguments[0].click();", e);
  }

  /**
   * click action
   *
   * @param locator Object
   * @param opParams Object
   * @deprecated
   */
  public void click(Object locator, Object... opParams) {
    $((By) locator).click();
  }

  /**
   * click action
   *
   * @param locator Object
   */
  public void click(Object locator) {
    $((By) locator).click();
  }

  /**
   * Use this function to verify if a check-box is checked (using when creating a
   * portal/publicMode)
   *
   * @param locator Object
   * @param opParams int
   */
  public void check(Object locator, int... opParams) {
    int notDisplayE = opParams.length > 0 ? opParams[0] : 0;
    Actions actions = new Actions(testBase.getExoWebDriver().getWebDriver());
    try {
      WebElement element = waitForAndGetElement(locator, testBase.getDefaultTimeout(), 1, notDisplayE);
      if (testBase.getBrowser().contains("chrome")) {
        scrollToElement(element, testBase.getExoWebDriver().getWebDriver());
      }
      if (!element.isSelected()) {
        actions.click(element).perform();
        if (waitForAndGetElement(locator, testBase.getDefaultTimeout(), 1, notDisplayE).getAttribute("type") != null
            && waitForAndGetElement(locator, testBase.getDefaultTimeout(), 1, notDisplayE).getAttribute("type") != "checkbox") {
          Logger.info("Checkbox is not checked");
          if (!element.isSelected()) {
            Logger.info("check by javascript");
            waitForAndGetElement(locator, testBase.getDefaultTimeout(), 1, notDisplayE);
            // mouseOver(locator, true);
            clickByJavascript(locator, notDisplayE);
          }
        }
      } else {
        Logger.info("Element " + locator + " is already checked.");
      }
    } catch (StaleElementReferenceException e) {
      checkCycling(e, testBase.getDefaultTimeout() / WAIT_INTERVAL);

      check(locator, opParams);
    } finally {
      testBase.setLoopCount(0);
    }

  }

  /**
   * get value attribute
   *
   * @param locator Object
   * @return value of element
   */
  public String getValue(Object locator) {
    try {
      return waitForAndGetElement(locator).getAttribute("value");
    } catch (StaleElementReferenceException e) {
      checkCycling(e, testBase.getDefaultTimeout() / WAIT_INTERVAL);

      return getValue(locator);
    } finally {
      testBase.setLoopCount(0);
    }
  }

  /**
   * mouse over action
   *
   * @param locator Object
   * @param safeToSERE boolean
   * @param opParams Object
   */
  public void mouseOver(Object locator, boolean safeToSERE, Object... opParams) {
    WebElement element;
    Actions actions = new Actions(testBase.getExoWebDriver().getWebDriver());
    int notDisplay = (Integer) (opParams.length > 0 ? opParams[0] : 0);
    try {
      if (safeToSERE) {
        for (int i = 1; i < testBase.ACTION_REPEAT; i++) {
          if (!locator.getClass().getName().contains("WebElement")) {
            element = waitForAndGetElement(locator, 5000, 0, notDisplay);
          } else {
            element = (WebElement) locator;
          }
          if (element == null) {

          } else {
            actions.moveToElement(element).perform();
            break;
          }
        }
      } else {
        if (!locator.getClass().getName().contains("WebElement")) {
          element = waitForAndGetElement(locator);
        } else {
          element = (WebElement) locator;
        }
        actions.moveToElement(element).perform();
      }
    } catch (StaleElementReferenceException e) {
      checkCycling(e, testBase.getDefaultTimeout() / WAIT_INTERVAL);

      mouseOver(locator, safeToSERE);
    } finally {
      testBase.setLoopCount(0);
    }
  }

  /**
   * mouse over and clic
   *
   * @param locator Object
   */
  public void mouseOverAndClick(Object locator) {
    WebElement element;
    Actions actions = new Actions(testBase.getExoWebDriver().getWebDriver());
    if (testBase.getExoWebDriver().isIEDriver()) {
      element = getDisplayedElement(locator);
    } else {
      element = waitForAndGetElement(locator);
    }
    actions.moveToElement(element).click(element).build().perform();
  }

  /**
   * wait for text not present
   *
   * @param text string
   * @param wait int
   */
  public void waitForTextNotPresent(String text, int... wait) {
    int waitTime = wait.length > 0 ? wait[0] : testBase.getDefaultTimeout();
    for (int second = 0;; second++) {
      if (second >= waitTime / WAIT_INTERVAL) {
        Assert.fail("Timeout at waitForTextNotPresent: " + text);
      }
      if (isTextNotPresent(text)) {
        break;
      }

    }
  }

  /**
   * type to textbox
   *
   * @param locator Object
   * @param value string
   * @param validate boolean
   * @param opParams object
   * @deprecated
   */
  public void type(Object locator, String value, boolean validate, Object... opParams) {
    $((By) locator).val(value);
  }

  /**
   * type to textbox
   *
   * @param locator Object
   * @param value string
   */
  public void type(Object locator, String value) {
    $((By) locator).val(value);
  }

  /**
   * Select option from combo box
   *
   * @param locator Object
   * @param option string
   * @param display int
   */
  public void select(Object locator, String option, int... display) {
    int isDisplay = display.length > 0 ? display[0] : 1;
    try {
      for (int second = 0;; second++) {
        if (second >= testBase.getDefaultTimeout() / WAIT_INTERVAL) {
          Assert.fail("Timeout at select: " + option + " into " + locator);
        }
        Select select = new Select(waitForAndGetElement(locator, testBase.getDefaultTimeout(), 1, isDisplay));
        select.selectByVisibleText(option);
        if (option.equals(select.getFirstSelectedOption().getText())) {
          break;
        }

      }
    } catch (StaleElementReferenceException e) {
      checkCycling(e, testBase.getDefaultTimeout() / WAIT_INTERVAL);

      select(locator, option);
    } finally {
      testBase.setLoopCount(0);
    }

  }

  /**
   * un-check a checked-box
   *
   * @param locator Object
   * @param opParams int
   */
  public void uncheck(Object locator, int... opParams) {
    int notDisplayE = opParams.length > 0 ? opParams[0] : 0;
    Actions actions = new Actions(testBase.getExoWebDriver().getWebDriver());
    try {
      WebElement element = waitForAndGetElement(locator, testBase.getDefaultTimeout(), 1, notDisplayE);

      if (element.isSelected()) {
        actions.click(element).perform();
        if (element.isSelected()) {
          Logger.info("uncheck by javascript");
          waitForAndGetElement(locator, testBase.getDefaultTimeout(), 1, notDisplayE);
          clickByJavascript(locator, notDisplayE);
        }
      } else {
        Logger.info("Element " + locator + " is already unchecked.");
      }
    } catch (StaleElementReferenceException e) {
      checkCycling(e, 5);

      uncheck(locator, opParams);
    } finally {
      testBase.setLoopCount(0);
    }

  }

  /**
   * rightClickOnElement
   *
   * @param locator Object
   * @param opParams int
   */
  public void rightClickOnElement(Object locator, int... opParams) {
    int display = opParams.length > 0 ? opParams[0] : 0;
    Actions actions = new Actions(testBase.getExoWebDriver().getWebDriver());

    try {
      WebElement element = waitForAndGetElement(locator, testBase.getDefaultTimeout(), 1, display);
      actions.contextClick(element).perform();
    } catch (StaleElementReferenceException e) {
      checkCycling(e, testBase.getDefaultTimeout() / WAIT_INTERVAL);

      rightClickOnElement(locator);
    } catch (ElementNotVisibleException e) {
      checkCycling(e, testBase.getDefaultTimeout() / WAIT_INTERVAL);

      click(locator);
    } finally {
      testBase.setLoopCount(0);
    }
  }

  /**
   * doubleClickOnElement
   *
   * @param locator object
   */
  public void doubleClickOnElement(Object locator) {
    Actions actions = new Actions(testBase.getExoWebDriver().getWebDriver());
    WebElement element;
    try {
      if (!locator.getClass().getName().contains("WebElement")) {
        element = waitForAndGetElement(locator);
      } else {
        element = (WebElement) locator;
      }
      actions.doubleClick(element).perform();
    } catch (StaleElementReferenceException e) {
      checkCycling(e, 5);

      doubleClickOnElement(locator);
    } finally {
      testBase.setLoopCount(0);
    }
  }

  /**
   * checkCycling
   *
   * @param e exception
   * @param loopCountAllowed int
   */
  public void checkCycling(Exception e, int loopCountAllowed) {
    Logger.info("Exception:" + e.getClass().getName());
    if (testBase.getLoopCount() > loopCountAllowed) {
      Assert.fail("Cycled: " + e.getMessage());
    }
    Logger.info("Repeat... " + testBase.getLoopCount() + "time(s)");
    testBase.setLoopCount(testBase.getLoopCount() + 1);
  }

  /**
   * function to switch to parent windows
   */
  public void switchToParentWindow() {
    try {
      Set<String> availableWindows = testBase.getExoWebDriver().getWebDriver().getWindowHandles();
      String WindowIdParent = null;
      int counter = 1;
      for (String windowId : availableWindows) {
        if (counter == 1) {
          WindowIdParent = windowId;
        }
        counter++;
      }
      testBase.getExoWebDriver().getWebDriver().switchTo().window(WindowIdParent);

    } catch (WebDriverException e) {
      e.printStackTrace();
    }
  }

  /**
   * check element displays or net
   *
   * @param locator Object
   * @return true if element displays false if element doesn't display
   */
  public boolean isDisplay(Object locator) {
    boolean bool = false;
    WebElement e = getElement(locator);
    try {
      if (e != null)
        bool = e.isDisplayed();
    } catch (StaleElementReferenceException ex) {
      checkCycling(ex, 10);

      isDisplay(locator);
    } finally {
      testBase.setLoopCount(0);
    }
    return bool;
  }

  /**
   * function get an element from link text when cannot get by text in xpath
   *
   * @param text string
   * @return an element from link text
   */
  public WebElement getElementFromTextByJquery(String text) {

    JavascriptExecutor js = (JavascriptExecutor) testBase.getExoWebDriver().getWebDriver();

    try {
      WebElement web = (WebElement) js.executeScript("return $(\"a:contains('" + text + "')\").get(0);");
      return web;
    } catch (WebDriverException e) {
      WebElement web = (WebElement) js.executeScript("return $(\"a:contains('" + text + "')\").get(0);");
      return web;
    }
  }

  /**
   * scrollBarToGetElement
   *
   * @param object By
   * @param opParams int
   */
  public void scrollBarToGetElement(By object, int... opParams) {
    int display = opParams.length > 0 ? opParams[0] : 0;
    WebElement element = waitForAndGetElement(object, 5000, 1, display);
    JavascriptExecutor jse;
    jse = (JavascriptExecutor) testBase.getExoWebDriver().getWebDriver();
    jse.executeScript("arguments[0].scrollIntoView(true);", element);
  }

  /**
   * Press End Key
   *
   * @param driver WebDriver
   */
  public void pressEndKey(WebDriver driver) {
    Logger.info("Press End key");
    testBase.setAction(new Actions(driver));
    testBase.getAction().sendKeys(Keys.END).perform();
    testBase.getAction().release();
  }

  /**
   * This function will try to get an element. if after timeout, the element is
   * not found. The function will refresh the page and find the element again.
   *
   * @param element Object
   * @param isClicked boolean
   */
  public void waitElementAndTryGetElement(Object element, Boolean... isClicked) {
    Logger.info("-- Starting finding element --");

    for (int repeat = 0;; repeat++) {
      if (repeat > 1) {
        if (waitForAndGetElement(element, 3000, 0) != null)
          ;
        break;
      }
      if (waitForAndGetElement(element, 5000, 0) != null) {
        Logger.info("Element " + element + " is displayed");
        if (isClicked.length > 0 && isClicked[0] == true)
          click(element);
        break;
      }
      Logger.info("Retry...[" + repeat + "]");
      testBase.getExoWebDriver().getWebDriver().navigate().refresh();
    }

    Logger.info("The elemnt is shown successfully");
  }

  public int getWaitInterval() {
    return WAIT_INTERVAL;
  }
}
