package commonfunctions;

import java.io.File;
/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks January 15, 2025
 */
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import commonlocators.CommonUiLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class CommonFunctions {
	private static boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static ExcelUtils excelUtils = new ExcelUtils();

	/**
	 * This method used to open browser with platformName that was set in maven goal
	 * 
	 * @author Sakthi-GWL
	 */
	public void browserOpen() {
		webDB.Setup(System.getProperty("platformName"));
	}

	/**
	 * This method used to navigate url that was set in Maven Goal
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean enterUrl() {
		webDB.enterURL(System.getProperty("url"));
		flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.LoginForm, ElementType.Xpath, 45);
		return flag;
	}

	/**
	 * This method used to wait 10 seconds till disappear table refreshing cells
	 * 
	 * @author Sakthi-GWL
	 * @return purchaseId
	 */
	public boolean checkRefreshCellsDisappear() {
		List<WebElement> refreshElements = webDB.getDriver()
				.findElements(By.xpath(CommonUiLocators.EntryTableRefreshCellImage));
		try {
			for (WebElement element : refreshElements) {
				WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(20));
				wait.until(ExpectedConditions.invisibilityOf(element));
			}
			// TODO WAIT_LOAD_TIME (will remove future)
			Thread.sleep(500);
		} catch (Exception e) {

		}
		List<WebElement> AfterWaitrefreshElements = webDB.getDriver()
				.findElements(By.xpath(CommonUiLocators.EntryTableRefreshCellImage));
		flag = AfterWaitrefreshElements.isEmpty();
		return flag;
	}

	/**
	 * This method used to logout from the current user
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 * @throws InterruptedException
	 */
	public boolean logoutUser() {
		flag = webDB.waitForClickElement(CommonUiLocators.LogoutIcon, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(CommonUiLocators.LogoutIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.LoginForm, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Logout Successfully");
			}
		}
		return flag;
	}

	/**
	 * This method used to Login with valid credentials
	 * 
	 * @author Sakthi-GWL
	 * @throws InterruptedException
	 */
	public void validCommonLogin(String Username, String Password, String... Name) {
		flag = webDB.waitForElement(CommonUiLocators.LoginEmailTextBox, ElementType.Xpath);
		if (flag) {
			webDB.clearTextField(CommonUiLocators.LoginEmailTextBox, ElementType.Xpath);
			webDB.sendTextToAnElement(CommonUiLocators.LoginEmailTextBox, Username, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.LoginPasswordTextBox, ElementType.Xpath);
			if (flag) {
				webDB.clearTextField(CommonUiLocators.LoginPasswordTextBox, ElementType.Xpath);
				webDB.sendTextToAnElement(CommonUiLocators.LoginPasswordTextBox, Password, ElementType.Xpath);
				flag = webDB.waitForClickElement(CommonUiLocators.LoginBtn, ElementType.Xpath);
				if (flag) {
					webDB.doubleClickAnElement(CommonUiLocators.LoginBtn, ElementType.Xpath);
					flag = webDB.waitForElement(CommonUiLocators.PageHeaderText, ElementType.Xpath);
					if (!flag) {
						flag = webDB.isElementDisplayed(CommonUiLocators.LoginBtn, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Retrying Login....");
							webDB.doubleClickAnElement(CommonUiLocators.LoginBtn, ElementType.Xpath);
							flag = webDB.waitForElement(CommonUiLocators.PageHeaderText, ElementType.Xpath);
						}
					}
				}
			}
		}
		if (flag) {
			if (Name.length > 0) {
				logs.logging("INFO", "'" + Arrays.toString(Name) + "' User Logged In Successfully");

			} else {
				logs.logging("INFO", "User Logged In Successfully");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Navigate Purchase Order Page
	 * 
	 * @author Deepan-GWL
	 */
	public void navigatePurchaseOrderPage() {
		clickLeftNavigationBar();
		clickSupplyChainTab();
		clickPurchaseTab();
		clickPurchaseTransactionTab();
		flag = webDB.waitForElement(CommonUiLocators.PurchaseOrderTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.PurchaseOrderTab, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.LeftSideAddIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated To Purchase Order page Successfully");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used to navigate Purchase Request Page
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigatePurchaseRequestPage() {
		clickLeftNavigationBar();
		clickSupplyChainTab();
		clickPurchaseTab();
		clickPurchaseTransactionTab();
		flag = webDB.waitForElement(CommonUiLocators.PurchaseRequestTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.PurchaseRequestTab, ElementType.Xpath);
			flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.LeftSideAddIcon, ElementType.Xpath, 45);
			if (flag) {
				logs.logging("INFO", "Purchase Request Clicked and navigated successfully");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used to search and select values for all filters
	 * 
	 * @author Sakthi-GWL
	 * @param locator
	 * @param Value
	 */
	public void searchAndSelectValueInFilters(String locator, String Value) {
		flag = webDB.waitForElement(locator, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(locator, ElementType.Xpath);
			webDB.switchToDefaultContent();
			List<WebElement> searchBar = webDB.getDriver().findElements(By.xpath(CommonUiLocators.searchBar));
			a: for (int i = 0; i < searchBar.size(); i++) {
				try {
					if (searchBar.get(i).isDisplayed()) {
						searchBar.get(i).clear();
						searchBar.get(i).sendKeys(Value);
						// TODO WAIT_LOAD_TIME (will remove future)
						Thread.sleep(2000);
						flag = checkRefreshCellsDisappear();
						if (flag) {
							flag = webDB.waitForClickElement(CommonUiLocators.convertXPath(Value), ElementType.Xpath);
							if (flag) {
								flag = webDB.clickAnElement(CommonUiLocators.convertXPath(Value), ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", " Searched and selected '" + Value + "' in Filter");
								}
								break a;
							}
						}
					}
				} catch (Exception e) {
					flag = false;
					continue;
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used to click create icon - left add icon
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickCreateIcon() {
		flag = webDB.waitForElement(CommonUiLocators.LeftSideAddIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.LeftSideAddIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.CreateTickIcon, ElementType.Xpath);
			if(flag) {
				logs.logging("INFO", "Left Side Add Icon is Displayed and Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used to click pending icon - left watch icon
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickPendingIcon() {
		checkRefreshCellsDisappear();
		flag = webDB.waitForElement(CommonUiLocators.LeftSidePendingIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.LeftSidePendingIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PendingText, ElementType.Xpath);
			if (flag)
				logs.logging("INFO", "Pending Icon is Displayed and Clicked");
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Opening Left Navigation bar
	 * 
	 * @author Deepan-GWL
	 */
	private void clickLeftNavigationBar() {
		flag = webDB.waitForElement(CommonUiLocators.LeftNavigationBar, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.LeftNavigationBar, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.SupplyChainTab, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Left Navigation Bar Is clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Opening SCM Tab
	 * 
	 * @author Deepan-GWL
	 */
	private void clickSupplyChainTab() {
		flag = webDB.waitForElement(CommonUiLocators.SupplyChainTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.SupplyChainTab, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PurchaseTab, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Supply Chain Management Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to opening Purchase Tab
	 * 
	 * @author Deepan-GWL
	 */
	private void clickPurchaseTab() {
		flag = webDB.waitForElement(CommonUiLocators.PurchaseTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.PurchaseTab, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PurchaseTransactionTab, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Purchase Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Opening Purchase Transaction Tab
	 * 
	 * @author Deepan-GWL
	 */
	private void clickPurchaseTransactionTab() {
		flag = webDB.waitForElement(CommonUiLocators.PurchaseTransactionTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.PurchaseTransactionTab, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PurchaseOrderTab, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Purchase Transaction Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Get Data From Test Data Sheet
	 * 
	 * @author Deepan-GWL
	 * @param Key
	 * @return String value
	 */
	public String getDataFromTestDataSheet(String Key) {
		String value = null;
		if (VariableHandler.getTestData() == null) {
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
					+ "TestDatas.xlsx";
			VariableHandler.setTestData(
					excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("TestDataSheetName")));
		} else {
		}
		for (int i = 0; i < VariableHandler.getTestData().length; i++) {
			if (VariableHandler.getTestData()[i][0].equals(Key)) {
				value = VariableHandler.getTestData()[i][1];
			}
		}
		return value;
	}

	/**
	 * This Method Is Used To After Method In TestNG
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAfterMethod() {
		webDB.navigateToRefresh();
		webDB.switchToDefaultContent();
		logoutUser();
	}
}
