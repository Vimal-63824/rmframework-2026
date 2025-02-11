package commonfunctions;

import java.io.File;
import java.math.BigDecimal;
/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks January 15, 2025
 */
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import commonfunctions.supplychainmanagement.SalesOrderFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.POAmendmentLocators;
import commonlocators.payroll.AttendanceLeaveLocators;
import commonlocators.payroll.EmployeeMasterLocators;
import commonlocators.supplychainmanagement.PurchaseRequestLocators;
import commonlocators.supplychainmanagement.SalesOrderLocators;
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
	 * @return boolean
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
			hardWait(500);
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
					hardWait(2000);
					webDB.doubleClickAnElement(CommonUiLocators.LoginBtn, ElementType.Xpath);
					checkRefreshCellsDisappear();
					flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.PageHeaderText, ElementType.Xpath, 15);
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
			checkRefreshCellsDisappear();
			List<WebElement> searchBar = webDB.getDriver().findElements(By.xpath(CommonUiLocators.searchBar));
			a: for (int i = 0; i < searchBar.size(); i++) {
				WebElement element = searchBar.get(i);
				try {
					if (element.isDisplayed()) {
						element.clear();
						// logs.logging("INFO", "Search Field Is Cleared");
						try {
							flag = fluentWaitClickElement(element);
							if (flag) {
								element.sendKeys(Value);
							}
						} catch (Exception e) {
							logs.logging("INFO", "Exception is: " + e);
						}
						hardWait(500);
						// logs.logging("INFO", "Entered Value In Search Field Is: " + Value);
						webDB.clickAnElementWithoutException(CommonUiLocators.SearchBarIcon, ElementType.Xpath);
						// TODO WAIT_LOAD_TIME (will remove future)
						hardWait(2000);
						checkRefreshCellsDisappear();
						if (webDB.verifyIsElementDisplayed(CommonUiLocators.searchHighlightText(Value),
								ElementType.Xpath)) {
							flag = webDB.clickAnElementWithoutException(CommonUiLocators.searchHighlightText(Value),
									ElementType.Xpath);
						} else if (webDB.verifyIsElementDisplayed(CommonUiLocators.convertXPathWithLi(Value),
								ElementType.Xpath)) {
							flag = webDB.clickAnElementWithoutException(CommonUiLocators.convertXPathWithLi(Value),
									ElementType.Xpath);
						} else if (webDB.verifyIsElementDisplayed(CommonUiLocators.convertXPath(Value),
								ElementType.Xpath)) {
							flag = webDB.clickAnElementWithoutException(CommonUiLocators.convertXPath(Value),
									ElementType.Xpath);
						} else {
							flag = false;
						}
						if (flag) {
							logs.logging("INFO", " Searched and selected '" + Value + "' in Filter");
							break a;
						}
					}
				} catch (Exception e) {
					logs.logging("INFO", "Exception Is: " + e);
					flag = false;
					continue;
				}
			}
		}
		Assert.assertTrue(flag);
	}

	public boolean fluentWaitClickElement(WebElement element) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(webDB.getDriver()).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(StaleElementReferenceException.class);
		return wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
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
			if (flag) {
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
			checkRefreshCellsDisappear();
			webDB.moveToElement(CommonUiLocators.LeftSidePendingIcon, ElementType.Xpath);
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
	public void clickLeftNavigationBar() {
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
		VariableHandler.setTotalLineAmount(BigDecimal.ZERO);
		VariableHandler.setTotalDiscountAmount(BigDecimal.ZERO);
		VariableHandler.setTaxAmount(BigDecimal.ZERO);
		webDB.navigateToRefresh();
		webDB.switchToDefaultContent();
		enterUrl();
	}

	/**
	 * This method used to Navigate Purchase Request Close Short Page
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigatePurchaseRequestCloseShortPage() {
		clickLeftNavigationBar();
		clickSupplyChainTab();
		clickPurchaseTab();
		clickPurchaseTransactionTab();
		flag = webDB.waitForElement(CommonUiLocators.PurchaseRequestCloseShortTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.PurchaseRequestCloseShortTab, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseRequestLocators.PRCloseShort_AddNewBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated To Purchase Request Close Short page Successfully");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To click Sales Tab
	 * 
	 * @author Harikrishna -RMITS
	 * @return boolean flag
	 */
	private void clickSalesTab() {
		flag = webDB.waitForElement(SalesOrderLocators.SalesTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.SalesTab, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.SalesTransactionTab, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sales Tab is Clicked");
			}
		}
		Assert.assertTrue(flag);

	}

	/**
	 * This method is used to click the Sales Transaction Tab
	 * 
	 * @author Harikrishna - RMITS
	 * @return Boolean Flag
	 */

	private void clickSalesTransactionTab() {
		flag = webDB.waitForElement(SalesOrderLocators.SalesTransactionTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.SalesTransactionTab, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.SalesOrderTab, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sales Transaction Tab is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to Navigate Sales Order Screen
	 * 
	 * @author Harikrishna - RMITS
	 * @return Boolean Flag
	 */
	public void navigateSalesOrderPage() {
		clickLeftNavigationBar();
		clickSupplyChainTab();
		clickSalesTab();
		clickSalesTransactionTab();
		flag = webDB.waitForElement(SalesOrderLocators.SalesOrderTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.SalesOrderTab, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.AddButtonSalesOrder, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated to Sales Order Screen Sucessfully");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to wait 10 seconds till disappear success toast message
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean checkSuccessToastMessageDisappear() {
		try {
			WebElement ToastElements = webDB.getDriver().findElement(By.xpath(CommonUiLocators.PostToastMessage));
			WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(20));
			wait.until(ExpectedConditions.invisibilityOf(ToastElements));
			hardWait(500);
			flag = !ToastElements.isDisplayed();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * Hard Wait
	 * 
	 * @author Deepan-GWL
	 */
	public void hardWait(long value) {
		try {
			// TODO WAIT_LOAD_TIME (will remove future)
			Thread.sleep(value);
		} catch (Exception e) {
		}
	}

	/**
	 * This method used to Navigate PO Amendment Page
	 * 
	 * @author Deepan-GWL
	 */
	public void navigatePoAmendmentPage() {
		clickLeftNavigationBar();
		clickSupplyChainTab();
		clickPurchaseTab();
		clickPurchaseTransactionTab();
		flag = webDB.waitForElement(CommonUiLocators.POAmendmentTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.POAmendmentTab, ElementType.Xpath);
			flag = webDB.waitForElement(POAmendmentLocators.POPendingIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated To PO Amendment page Successfully");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to payroll Tab
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickPayrollTab() {
		flag = webDB.waitForElement(CommonUiLocators.PayrollTab, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CommonUiLocators.PayrollTab, ElementType.Xpath);
			webDB.clickAnElement(CommonUiLocators.PayrollTab, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PayrollTransactionTab, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Payroll Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Payroll Transaction Tab
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickPayrollTransactionTab() {
		flag = webDB.waitForElement(CommonUiLocators.PayrollTransactionTab, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CommonUiLocators.PayrollTransactionTab, ElementType.Xpath);
			webDB.clickAnElement(CommonUiLocators.PayrollTransactionTab, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PayrollEmployeeMasterTab, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Payroll Transaction Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Payroll Setup Tab
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickPayrollSetupTab() {
		flag = webDB.waitForElement(CommonUiLocators.PayrollSetupTab, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CommonUiLocators.PayrollSetupTab, ElementType.Xpath);
			webDB.clickAnElement(CommonUiLocators.PayrollSetupTab, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PayrollLeaveAndLoanTab, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Payroll Setup Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Payroll Employee Master tab
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickPayrollEmployeeMasterTab() {
		flag = webDB.waitForElement(CommonUiLocators.PayrollEmployeeMasterTab, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CommonUiLocators.PayrollEmployeeMasterTab, ElementType.Xpath);
			webDB.clickAnElement(CommonUiLocators.PayrollEmployeeMasterTab, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PayrollEmployeeMasterScreen, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Payroll Employee Master Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Navigate Employee Master Screen
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateEmployeeMasterScreenInPayroll() {
		clickLeftNavigationBar();
		clickPayrollTab();
		clickPayrollTransactionTab();
		clickPayrollEmployeeMasterTab();
		flag = webDB.waitForElement(CommonUiLocators.PayrollEmployeeMasterScreen, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CommonUiLocators.PayrollEmployeeMasterScreen, ElementType.Xpath);
			webDB.clickAnElement(CommonUiLocators.PayrollEmployeeMasterScreen, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.EmployeeMasterAddIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated To Employee Master Screen Successfully");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to opening AttendanceLeave Tab
	 * 
	 * @author Kishore-RMITS
	 */

	private void clickAttendanceLeaveTab() {
		flag = webDB.waitForElement(CommonUiLocators.EmployeePayrollAttendanceLeave, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.EmployeePayrollAttendanceLeave, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.EmployeeLeaveRequestScreen, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "AttendanceLeave Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Navigate to the Permission Screen
	 * 
	 * @author Kishore-RMITS
	 */
	public void navigateLeaveEncashmentScreen() {
		clickLeftNavigationBar();
		clickPayrollTab();
		clickPayrollTransactionTab();
		clickAttendanceLeaveTab();
		flag = webDB.waitForElement(CommonUiLocators.EmployeeLeaveRequestScreen, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.EmployeeLeaveRequestScreen, ElementType.Xpath);
			flag = webDB.waitForElement(AttendanceLeaveLocators.EmployeePermissionButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "AttendanceLeave Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

}
