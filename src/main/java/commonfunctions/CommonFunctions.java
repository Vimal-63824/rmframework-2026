package commonfunctions;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks January 15, 2025
 */
import java.io.File;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import commonfunctions.supplychainmanagement.PurchaseOrderFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.ApprovalPageLocators;
import commonlocators.supplychainmanagement.CompletionProductionOrderLocators;
import commonlocators.supplychainmanagement.GrnLocators;
import commonlocators.supplychainmanagement.OpenSalesOrderLocators;
import commonlocators.supplychainmanagement.OpenSoAmdLocators;
import commonlocators.supplychainmanagement.POAmendmentLocators;
import commonlocators.payroll.AttendanceLeaveLocators;
import commonlocators.payroll.EmployeeMasterLocators;
import commonlocators.productionplanning.BillOfMaterialsLocators;
import commonlocators.productionplanning.MRPLocators;
import commonlocators.productionplanning.ProductionOrderLocators;
import commonlocators.supplychainmanagement.PricelistPageLocators;
import commonlocators.supplychainmanagement.PurchaseRequestLocators;
import commonlocators.supplychainmanagement.SalesInvoiceLocators;
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
	private static PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();

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
		int i = 1;
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
		if (!flag && (i < 2)) {
			i++;
			checkRefreshCellsDisappear();
		}
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
		enterUrl();
		flag = true;
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
			webDB.javaScriptClickAnElement(locator, ElementType.Xpath);
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
								checkRefreshCellsDisappear();
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
						List<WebElement> Elements = webDB.getDriver()
								.findElements(By.xpath(CommonUiLocators.convertListOfXPath(Value)));
						for (WebElement webElement : Elements) {
							try {
								if (webElement.isDisplayed()) {
									webElement.click();
									flag = true;
									logs.logging("INFO", " Searched and selected '" + Value + "' in Filter");
									break;
								} else {
									flag = false;
								}
							} catch (Exception e) {
								flag = false;
							}
//						if (webDB.verifyIsElementDisplayed(CommonUiLocators.searchHighlightText(Value),
//								ElementType.Xpath)) {
//							flag = webDB.clickAnElementWithoutException(CommonUiLocators.searchHighlightText(Value),
//									ElementType.Xpath);
//						} else if (webDB.verifyIsElementDisplayed(CommonUiLocators.convertXPathWithLi(Value),
//								ElementType.Xpath)) {
//							flag = webDB.clickAnElementWithoutException(CommonUiLocators.convertXPathWithLi(Value),
//									ElementType.Xpath);
//						} else if (webDB.verifyIsElementDisplayed(CommonUiLocators.convertXPath(Value),
//								ElementType.Xpath)) {
//							flag = webDB.clickAnElementWithoutException(CommonUiLocators.convertXPath(Value),
//									ElementType.Xpath);
//						} else {
//							flag = false;
//						}
							if (flag) {
								logs.logging("INFO", " Searched and selected '" + Value + "' in Filter");
								break a;
							}
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
			checkRefreshCellsDisappear();
			hardWait(5000);
			List<WebElement> elements = webDB.getDriver().findElements(By.xpath(CommonUiLocators.PendingText));
			for (WebElement webElement : elements) {
				hardWait(500);
				try {
					if (webElement.isDisplayed()) {
						flag = true;
						break;
					} else {
						flag = false;
					}
				} catch (Exception e) {
				}
			}
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
			String value = webDB.getDomAttributeFromElement(CommonUiLocators.LeftNavigationBar, ElementType.Xpath,
					"aria-expanded");
			if (value.equalsIgnoreCase("false")) {
				webDB.clickAnElement(CommonUiLocators.LeftNavigationBar, ElementType.Xpath);
			}
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
			String value = webDB.getDomAttributeFromElement(CommonUiLocators.SupplyChainTabCheck, ElementType.Xpath,
					"aria-expanded");
			if (value.equalsIgnoreCase("false")) {
				webDB.clickAnElement(CommonUiLocators.SupplyChainTab, ElementType.Xpath);
			}
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
			String value = webDB.getDomAttributeFromElement(CommonUiLocators.PurchaseTabCheck, ElementType.Xpath,
					"aria-expanded");
			if (value.equalsIgnoreCase("false")) {
				webDB.clickAnElement(CommonUiLocators.PurchaseTab, ElementType.Xpath);
			}
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
		VariableHandler.setIndividualAmount(BigDecimal.ZERO);
		VariableHandler.setDiscountAmount(BigDecimal.ZERO);
		VariableHandler.setLineAmount(BigDecimal.ZERO);
		VariableHandler.getStockQty().clear();
		VariableHandler.getStockCost().clear();
		VariableHandler.getAfterStockCost().clear();
		VariableHandler.getAfterStockQty().clear();
		webDB.navigateToRefresh();
		webDB.switchToDefaultContent();
		webDB.switchToMainTab();
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
	 * This method used to Navigate Price List Page
	 * 
	 * @author Durai
	 */
	public void navigatePricelistPage() {
		clickLeftNavigationBar();
		clickSupplyChainTab();
		clickPurchaseTab();
		clickPricelistTransactionTab();
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.PricelistTab, ElementType.Xpath);
			flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.LeftSideAddIcon, ElementType.Xpath, 45);
			if (flag) {
				logs.logging("INFO", "Pricelist clicked and navigated successfully");
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
	 * This method used to Click Add Icon in Price List Creation
	 * 
	 * @author Durai
	 */

	public void clickAddIconPricelist() {
		flag = webDB.waitForElement(PricelistPageLocators.AddPriceList, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.AddPriceList, ElementType.Xpath);
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Navigate Price List Transaction Tab
	 * 
	 * @author Durai
	 */
	private void clickPricelistTransactionTab() {
		flag = webDB.waitForElement(CommonUiLocators.PurchaseTransactionTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.PurchaseTransactionTab, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PricelistTab, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pricelist Transaction Tab Is Clicked");
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

	/**
	 * This method is used to Navigate Sales Invoice Screen
	 * 
	 * @author Vimal - RMITS
	 * @return Boolean Flag
	 */
	public void navigateSalesInvoicePage() {
		clickLeftNavigationBar();
		clickSupplyChainTab();
		clickSalesTab();
		clickSalesTransactionTab();
		flag = webDB.waitForElementUntilGivenTime(SalesInvoiceLocators.Sales_Inv, ElementType.Xpath, 45);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Sales_Inv, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.Sales_Inv_Pend_Btn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated to Sales Invoice Screen Sucessfully");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used to return random Mobile Numbers
	 * 
	 * @author Sakthi-GWL
	 * @return Number
	 */
	public String randomMobileNumberGenerator() {
		String Number = "99".concat(RandomStringUtils.randomNumeric(8));
		return Number;
	}

	/**
	 * This Method used to return random Email
	 * 
	 * @author Sakthi-GWL
	 * @return Mail
	 */
	public String randomEmailIdGenerator() {
		String Email = "testauto".concat(RandomStringUtils.randomNumeric(4)) + "@mail.com";
		return Email;
	}

	/**
	 * This Method used to Navigate GRN Page
	 * 
	 * @author PremKumar.K-RMIT
	 */
	public void naviagteGrnPage() {
		clickLeftNavigationBar();
		clickSupplyChainTab();
		clickPurchaseTab();
		clickPurchaseTransactionTab();
		ClickGrnPurchase();

	}

	/**
	 * Click Grn Purchase
	 * 
	 * @author PremKumar.K-RM IT
	 */
	private void ClickGrnPurchase() {
		flag = webDB.waitForElement(CommonUiLocators.PurchaseTransactionTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(GrnLocators.ClickGrnPurchase, ElementType.Xpath);
			flag = webDB.waitForElement(GrnLocators.Lov, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Purchase Transaction Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);

	}

	/**
	 * This Methd Used To navigate To Quality Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToQualityTab() {
		flag = webDB.waitForElement(CommonUiLocators.QualityControl, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.QualityControl, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.QualityTransactionTab, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Quality Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Navigate To Quality Transaction Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToQualityTransactionTab() {
		flag = webDB.waitForElement(CommonUiLocators.QualityTransactionTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.QualityTransactionTab, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.QualityInspectionDocuments, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Quality Transaction Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Navigate To Inspection Documents
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToInspectionDocuments() {
		flag = webDB.waitForElement(CommonUiLocators.QualityInspectionDocuments, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.QualityInspectionDocuments, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PendingInspectionDocuments, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Quality Inspection Documents Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Navigate Quality Control Inspection Document Page
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateInspectionDocumentsPage() {
		clickLeftNavigationBar();
		navigateToQualityTab();
		navigateToQualityTransactionTab();
		navigateToInspectionDocuments();
	}

	/**
	 * Navigate To MAterial Receipt Voucher
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToMaterialReceiptVoucher() {
		clickLeftNavigationBar();
		clickSupplyChainTab();
		navigateToInventoryTab();
		navigateToInventoryTranscationTab();
		navigateToMaterialReceiptVocherTab();
	}

	/**
	 * Navigate To Inventory Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToInventoryTab() {
		flag = webDB.waitForElement(CommonUiLocators.Inventory, ElementType.Xpath);
		if (flag) {
			String value = webDB.getDomAttributeFromElement(CommonUiLocators.InventoryCheck, ElementType.Xpath,
					"aria-expanded");
			if (value.equalsIgnoreCase("false")) {
				webDB.clickAnElement(CommonUiLocators.Inventory, ElementType.Xpath);
			}
			flag = webDB.waitForElement(CommonUiLocators.InventoryTransactionTab, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Inventory Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Navigate To Inventory Transaction Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToInventoryTranscationTab() {
		flag = webDB.waitForElement(CommonUiLocators.InventoryTransactionTab, ElementType.Xpath);
		if (flag) {
			String value = webDB.getDomAttributeFromElement(CommonUiLocators.InventoryTransactionTab, ElementType.Xpath,
					"class");
			if (value.contains("is-expandable")) {
				webDB.clickAnElement(CommonUiLocators.InventoryTransactionTab, ElementType.Xpath);
			}
			flag = webDB.waitForElement(CommonUiLocators.MaterialReceiptVoucherTab, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Inventory Transaction Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Navigate To MAterial Receipt Voucher Tab
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToMaterialReceiptVocherTab() {
		flag = webDB.waitForElement(CommonUiLocators.MaterialReceiptVoucherTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.MaterialReceiptVoucherTab, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PendingReceiptVoucherButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Material Receipt Voucher Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Navigate To Financial Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToFinancialTab() {
		flag = webDB.waitForElement(CommonUiLocators.FinancialTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.FinancialTab, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.FinancialTransactionTab, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Financial Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Navigate To Financial Transaction Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToFinancialTransactionTab() {
		flag = webDB.waitForElement(CommonUiLocators.FinancialTransactionTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.FinancialTransactionTab, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.FinancialPartyDocuments, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Financial Transaction Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Navigate To Financial Party Documents Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToFinancialPartyDocumentsTab() {
		flag = webDB.waitForElement(CommonUiLocators.FinancialPartyDocuments, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.FinancialPartyDocuments, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.FinancialPurchaseBill, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Financial Party Documents Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Navigate To Financial Purchase Bill Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToFinancialPurchaseBillTab() {
		flag = webDB.waitForElement(CommonUiLocators.FinancialPurchaseBill, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.FinancialPurchaseBill, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PendingPurchaseBill, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Financial Purchase Bill Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Navigate To Financial Purchase Bill Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToFinancialPurchaseBill() {
		clickLeftNavigationBar();
		navigateToFinancialTab();
		navigateToFinancialTransactionTab();
		navigateToFinancialPartyDocumentsTab();
		navigateToFinancialPurchaseBillTab();
	}

	/**
	 * Navigate To Payment Receipt Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToPaymentReceiptTab() {
		flag = webDB.waitForElement(CommonUiLocators.PaymentReceiptTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.PaymentReceiptTab, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PaymentsReceipts, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Payment Receipt Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Navigate To Financial Payment Receipt
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToPaymentReceipt() {
		flag = webDB.waitForElement(CommonUiLocators.PaymentsReceipts, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.PaymentsReceipts, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PendingCrDocs, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Payment Receipt Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Navigate To Financial Payment Receipt
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToFinancialPaymentReceipt() {
		clickLeftNavigationBar();
		navigateToFinancialTab();
		navigateToFinancialTransactionTab();
		navigateToPaymentReceiptTab();
		navigateToPaymentReceipt();
	}

	/**
	 * This method used to click sales setup tab
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickSalesSetupTab() {
		flag = webDB.waitForClickElement(CommonUiLocators.SalesSetupTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.SalesSetupTab, ElementType.Xpath);
			flag = webDB.waitForClickElement(CommonUiLocators.SalesSetup_SalesScreen, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sales Setup tab Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Navigate To Material Request
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToMaterialRequest() {
		flag = webDB.waitForElement(CommonUiLocators.MaterialRequest, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.MaterialRequest, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.CreateMaterialRequestButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Material Request Button Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Navigates to SupplyChain Node
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean navigateSalesSetupScreen() {
		clickLeftNavigationBar();
		clickSupplyChainTab();
		clickSalesTab();
		clickSalesSetupTab();
		flag = webDB.waitForElement(CommonUiLocators.SalesSetup_SalesScreen, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.SalesSetup_SalesScreen, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated to Setup sales screen Successfully");
			}
		}
		return flag;
	}

	/**
	 * To Click a Open So Screen
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public void navigateOpenSO() {
		clickLeftNavigationBar();
		clickSupplyChainTab();
		clickSalesTab();
		clickSalesTransactionTab();
		flag = webDB.waitForClickElement(CommonUiLocators.OpenSOTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.OpenSOTab, ElementType.Xpath);
			flag = webDB.waitForClickElement(OpenSalesOrderLocators.OpenSORadioBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "navigated open so screen successfully");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to check Discounts in Line Details
	 * 
	 * @author Sakthi-GWL
	 * @param AssblLocator
	 * @param PerctLocator
	 * @param AmountLocator
	 * @param Discount
	 */
	public void checkDiscountInLineDetails(String AssblLocator, String PerctLocator, String AmountLocator,
			String Discount) {
		flag = webDB.waitForElement(AssblLocator, ElementType.Xpath);
		if (flag) {
			String AssVal = webDB.getDomPropertyFromElement(AssblLocator, ElementType.Xpath,
					getDataFromTestDataSheet("Value"));
			BigDecimal BeforeDiscountvalue = purchaseOrderFunc
					.verifyRoundOffBigDecimalValueWithReturnBigDecimal(new BigDecimal(AssVal));
			logs.logging("INFO", "Assbl.. Value Displayed: " + BeforeDiscountvalue);
			flag = webDB.waitForElement(PerctLocator, ElementType.Xpath);
			if (flag) {
				webDB.doubleClickAnElement(PerctLocator, ElementType.Xpath);
				webDB.sendTextToAnElement(PerctLocator, Discount, ElementType.Xpath);
				webDB.pressEnterKey();
				checkRefreshCellsDisappear();
				BigDecimal percentage = purchaseOrderFunc
						.verifyRoundOffBigDecimalValueWithReturnBigDecimal(new BigDecimal(Discount));
				logs.logging("INFO", "Percentage Value Entered: " + Discount + "%");
				String AmountVal = webDB.getDomPropertyFromElement(AmountLocator, ElementType.Xpath,
						getDataFromTestDataSheet("Value"));
				BigDecimal AfterDiscountvalue = purchaseOrderFunc
						.verifyRoundOffBigDecimalValueWithReturnBigDecimal(new BigDecimal(AmountVal));
				logs.logging("INFO", "Amount Value Displayed: " + AfterDiscountvalue);
				BigDecimal Result = (BeforeDiscountvalue.multiply(percentage)).divide(new BigDecimal("100"));
				Result = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(Result);
				logs.logging("INFO", "Expected Amount value: " + Result + " for Assbl: " + BeforeDiscountvalue
						+ " with Percentage: " + percentage);
				flag = Result.equals(AfterDiscountvalue);
				if (flag) {
					logs.logging("INFO", "Discount calculation verified");
				}
			}
		}
		Assert.assertTrue(flag);
	}
	/*
	 * Navigate To Inventory Material Request
	 * 
	 * @author Deepan-GWL
	 */

	public void navigateToInventoryMaterialRequest() {
		clickLeftNavigationBar();
		clickSupplyChainTab();
		navigateToInventoryTab();
		navigateToInventoryTranscationTab();
		navigateToMaterialRequest();
	}

	/**
	 * Navigate To Reports Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToInventoryReportsTab() {
		flag = webDB.waitForElement(CommonUiLocators.InventoryReportsTab, ElementType.Xpath);
		if (flag) {
			String value = webDB.getDomAttributeFromElement(CommonUiLocators.InventoryReportsTabCheck,
					ElementType.Xpath, "aria-expanded");
			if (value.equalsIgnoreCase("false")) {
				webDB.clickAnElement(CommonUiLocators.InventoryReportsTab, ElementType.Xpath);
			}
			flag = webDB.waitForElement(CommonUiLocators.StocksTab, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Reports Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Navigate To Stocks Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToStocksTab() {
		flag = webDB.waitForElement(CommonUiLocators.StocksTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.StocksTab, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.StocksCheckBox, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Stocks Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Navigate To Inventory Stocks Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToInventoryStocksTab() {
		clickLeftNavigationBar();
		clickSupplyChainTab();
		navigateToInventoryTab();
		navigateToInventoryReportsTab();
		navigateToStocksTab();
	}

	/**
	 * This method is used to Navigate Proforma Invoice Screen
	 * 
	 * @author Vimal - RMITS
	 */
	public void navigateProformaInvoice() {
		clickLeftNavigationBar();
		clickSupplyChainTab();
		clickSalesTab();
		clickSalesTransactionTab();
		flag = webDB.waitForElement(SalesInvoiceLocators.Proforma_Inv_Node, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesInvoiceLocators.Proforma_Inv_Node, ElementType.Xpath);
			flag = webDB.waitForElement(SalesInvoiceLocators.Proforma_Inv_Pend_Btn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated to Proforma Invoice Screen Sucessfully");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Navigate Purchase Order Page By Using Purchase User
	 * 
	 * @authorPremkumar.K-RMITS
	 */
	public void navigatePurchaseOrderPageForPurchaseUser() {
		clickLeftNavigationBar();
		clickSupplyChainTab();
		clickPurchaseTab();
		clickPurchaseTransactionTab();
		flag = webDB.waitForElement(CommonUiLocators.PurchaseOrderTabForPurchaseUser, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.PurchaseOrderTabForPurchaseUser, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.LeftSideAddIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated To Purchase Order page Successfully");

			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * @author Kowshik This Method IS used To Click the Sales Node
	 * @return Boolean
	 */
	public boolean clicksalesnode() {
		flag = webDB.waitForClickElement(SalesOrderLocators.sales, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.sales, ElementType.Xpath);
			flag = webDB.waitForClickElement(SalesOrderLocators.setup, ElementType.Xpath);
		}
		return flag;
	}

	/**
	 * @author Kowshik This Method Is used To Navigate Open Sales Order Main Screen
	 * @return boolean
	 */
	public boolean clickOpensoNode() {
		clickLeftNavigationBar();
		clickSupplyChainTab();
		clicksalesnode();
		flag = webDB.waitForClickElement(OpenSalesOrderLocators.Transactions, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSalesOrderLocators.Transactions, ElementType.Xpath);
			flag = webDB.waitForClickElement(OpenSalesOrderLocators.OpensoScreens, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(OpenSalesOrderLocators.OpensoScreens, ElementType.Xpath);
				flag = webDB.waitForElement(OpenSoAmdLocators.OpenSoAmdRadio, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "navigated open so screen successfully");
				}
			}
		}
		return flag;
	}

	/**
	 * To Click a Production Planning Tab
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickProductionPlanningTab() {
		flag = webDB.waitForElement(CommonUiLocators.ProductionPlanningTab, ElementType.Xpath);
		if (flag) {
			String value = webDB.getDomAttributeFromElement(CommonUiLocators.ProductionPlanningTab, ElementType.Xpath,
					"class");
			if (value.contains("is-expandable")) {
				webDB.clickAnElement(CommonUiLocators.ProductionPlanningTab, ElementType.Xpath);
			}
			flag = webDB.waitForElement(CommonUiLocators.ProdPlan_TransactionTab, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Production Planning Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * To Click a Production Planning Transaction Tab
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickProductionPlanningTransactionTab() {
		flag = webDB.waitForElement(CommonUiLocators.ProdPlan_TransactionTab, ElementType.Xpath);
		if (flag) {
			String value = webDB.getDomAttributeFromElement(CommonUiLocators.ProdPlan_TransactionTab, ElementType.Xpath,
					"class");
			if (value.contains("is-expandable")) {
				webDB.clickAnElement(CommonUiLocators.ProdPlan_TransactionTab, ElementType.Xpath);
			}
			flag = webDB.waitForElement(CommonUiLocators.ProdPlan_Transaction_MRPScreen, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Production Planning Tansaction Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * To Navigate MRP Screen In Production Planning
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToMRPScreenInProdPlanning() {
		clickLeftNavigationBar();
		clickProductionPlanningTab();
		clickProductionPlanningTransactionTab();
		flag = webDB.waitForElement(CommonUiLocators.ProdPlan_Transaction_MRPScreen, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.ProdPlan_Transaction_MRPScreen, ElementType.Xpath);
			flag = webDB.waitForElement(MRPLocators.FindMRPNoLovBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Production Planning MRP Screen Navigated Successfully");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * To Navigate Production Order Screen In Production Planning
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToProductionOrderInProdPlanning() {
		clickLeftNavigationBar();
		clickProductionPlanningTab();
		clickProductionPlanningTransactionTab();
		flag = webDB.waitForElement(CommonUiLocators.ProdPlan_Transaction_ProductionOrder, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.ProdPlan_Transaction_ProductionOrder, ElementType.Xpath);
			flag = webDB.waitForElement(ProductionOrderLocators.FindMRPNoLovBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Production Planning - Production Order Screen Navigated Successfully");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Navigate To Material Issuance Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToMivTab() {
		flag = webDB.waitForElement(CommonUiLocators.MIVTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.MIVTab, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PendingMIV, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Material Issuance Voucher Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Navigate Rfq Transaction Tab
	 * 
	 * @author Durai-RMITS
	 */
	public void navigateItemPage() {
		clickLeftNavigationBar();
		clickSupplyChainTab();
		navigateToInventoryTab();
		clickInventorySetupTab();
		clickItemmaster();
	}

	/**
	 * This method used to Inventory setup tab
	 * 
	 * @author Durai-RMITS
	 */
	private void clickInventorySetupTab() {
		flag = webDB.waitForElement(CommonUiLocators.InvSetupTab, ElementType.Xpath);
		if (flag) {
			String value = webDB.getDomAttributeFromElement(CommonUiLocators.InvSetupTab, ElementType.Xpath, "class");
			if (value.contains("is-expandable")) {
				webDB.clickAnElement(CommonUiLocators.InvSetupTab, ElementType.Xpath);
			}
			flag = webDB.waitForElement(CommonUiLocators.ItemTab, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Item Tab is Checked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Navigate Item master tab
	 * 
	 * @author Durai-RMITS
	 */
	private void clickItemmaster() {
		flag = webDB.waitForElement(CommonUiLocators.ItemTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.ItemTab, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.ItemMasterAdd, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Item Add Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Navigate To Material Issuance Voucher
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToMaterialIssuanceVoucher() {
		clickLeftNavigationBar();
		clickSupplyChainTab();
		navigateToInventoryTab();
		navigateToInventoryTranscationTab();
		navigateToMivTab();
	}

	/**
	 * To Navigate Completion Production Order Screen In Production Planning
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToCompletionProductionOrderInProdPlanning() {
		clickLeftNavigationBar();
		clickProductionPlanningTab();
		clickProductionPlanningTransactionTab();
		flag = webDB.waitForElement(CommonUiLocators.ProdPlan_Transaction_CompletionProductionOrder, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.ProdPlan_Transaction_CompletionProductionOrder, ElementType.Xpath);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.CompletionProcessPendingIcon,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Production Planning - Completion Production Order Screen Navigated Successfully");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Navigate To Material Issuance Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToMaterialIssuanceVoucherTab() {
		clickLeftNavigationBar();
		clickSupplyChainTab();
		navigateToInventoryTab();
		navigateToInventoryTranscationTab();
		flag = webDB.waitForElement(CommonUiLocators.MIVTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.MIVTab, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.LeftSidePendingIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Material Issuance Voucher Tab Is Clicked");

			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Navigate To Stock Transaction Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToStockTransactionTab() {
		flag = webDB.waitForElement(CommonUiLocators.StockTransactionTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.StockTransactionTab, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.StockTransactionCheckBox, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Stock Transaction Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to verify and set Approval Flag while click Post Icon
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean checkAutoApprovalOrForwardedWhilePost() {
		if (webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 10)) {
			VariableHandler.setApprovalFlag(true);
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			logs.logging("INFO", "Toast Message Displayed Approved successfully");
			return true;
		} else if (webDB.waitForElement(ApprovalPageLocators.ApprovalTitle, ElementType.Xpath)) {
			VariableHandler.setApprovalFlag(false);
			logs.logging("INFO", "Workflow forwarder iframe Displayed");
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Navigate To Inventory Stocks Transaction Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToInventoryStocksTransactionTab() {
		clickLeftNavigationBar();
		clickSupplyChainTab();
		navigateToInventoryTab();
		navigateToInventoryReportsTab();
		navigateToStockTransactionTab();
	}

	/**
	 * This Method Is Used To Navigate To Gate Entry Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToGateEntryTab() {
		flag = webDB.waitForElement(CommonUiLocators.GateEntry, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.GateEntry, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.GateEntryTransaction, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Gate Entry Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Navigate To Gate Entry Transaction Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToGateEntryTransactionTab() {
		flag = webDB.waitForElement(CommonUiLocators.GateEntryTransaction, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.GateEntryTransaction, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.DeliveryChallan, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Gate Entry Transaction Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Navigate To Delivery Challan Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToDeliverChallanTab() {
		flag = webDB.waitForElement(CommonUiLocators.DeliveryChallan, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.DeliveryChallan, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PendingChallan, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Delivery Challan Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Navigate To Inventory Stocks Transaction Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToGateEntryDeliveryChallan() {
		clickLeftNavigationBar();
		clickSupplyChainTab();
		navigateToGateEntryTab();
		navigateToGateEntryTransactionTab();
		navigateToDeliverChallanTab();
	}

	/**
	 * To Navigate Bill of Materials Screen In Production Planning
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToBillOfMaterialsPage() {
		clickLeftNavigationBar();
		clickProductionPlanningTab();
		clickProductionPlanningTransactionTab();
		flag = webDB.waitForElement(CommonUiLocators.ProdPlan_Transaction_BillOfMaterials, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.ProdPlan_Transaction_BillOfMaterials, ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.BOM_FindSearchBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Production Planning - Completion Production Order Screen Navigated Successfully");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Navigate To Subcontracting
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToSubcontracting() {
		flag = webDB.waitForElement(CommonUiLocators.Subcontracting, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.Subcontracting, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.SubcontractingTransaction, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Subcontracting Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Navigate MRV Tab For ADMIN User
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void navigateToMaterialReceiptVoucherAdminUser() {
		clickLeftNavigationBar();
		navigateToInventoryTabForAdminUser();
	}

	/**
	 * This Methods Is Used To Navigate Inventory Tab for ADMIN User
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void navigateToInventoryTabForAdminUser() {
		flag = webDB.waitForElement(CommonUiLocators.ClickSupplyChaintabForAdminUser, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.ClickSupplyChaintabForAdminUser, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.InventoryTabForAdminUser, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CommonUiLocators.InventoryTabForAdminUser, ElementType.Xpath);
				logs.logging("INFO", "Inventory Tab Is Clicked");

				flag = webDB.waitForElement(CommonUiLocators.InventoryTransactionTabForAdminUser, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CommonUiLocators.InventoryTransactionTabForAdminUser, ElementType.Xpath);
					logs.logging("INFO", "Transaction Tab Is Clicked");
					flag = webDB.waitForElement(CommonUiLocators.MaterialReceiptVoucherTabForAdminUser,
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(CommonUiLocators.MaterialReceiptVoucherTabForAdminUser, ElementType.Xpath);
						logs.logging("INFO", "Material Voucher Tab Is Clicked");
						flag = webDB.waitForElement(CommonUiLocators.PendingReceiptVoucherButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Material Receipt Voucher Tab Is Clicked");
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Navigate To Subcontracting Transaction
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToSubcontractingTransaction() {
		flag = webDB.waitForElement(CommonUiLocators.SubcontractingTransaction, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.SubcontractingTransaction, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.SubcontractOrder, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Subcontract Transaction Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Navigate To Subcontract Order
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToSubcontractOrderTab() {
		flag = webDB.waitForElement(CommonUiLocators.SubcontractOrder, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.SubcontractOrder, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PendingSubcontractOrder, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Subcontract Order Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Navigate To SubCntarct Order
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void navigateToSubContractOrder() {
		clickLeftNavigationBar();
		clickSupplyChainTab();
		navigateToSubcontracting();
		navigateToSubcontractingTransaction();
		navigateToSubcontractOrderTab();
	}

	/**
	 * This Method Is Used To Navigate To Grn Sco Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToGrnScoTab() {
		flag = webDB.waitForElement(CommonUiLocators.GrnSco, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.GrnSco, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PendingGrn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Grn Sco Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Navigate To Grn SCO Tab
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void navigateToGrnSco() {
		clickLeftNavigationBar();
		clickSupplyChainTab();
		navigateToSubcontracting();
		navigateToSubcontractingTransaction();
		navigateToGrnScoTab();
	}

	/**
	 * This method used to Navigate Rfq Transaction Tab
	 * 
	 * @author Durai-RMITS
	 */
	public void navigateRfqPage() {
		// clickLeftNavigationBar();
		clickSupplyChainTab();
		clickPurchaseTab();
		clickRfqTransactionTab();
	}

	/**
	 * This method used to Navigate RFQ Transaction Tab
	 * 
	 * @author Durai-RMITS
	 */
	private void clickRfqTransactionTab() {
		flag = webDB.waitForElement(CommonUiLocators.PurchaseTransactionTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.PurchaseTransactionTab, ElementType.Xpath);
			logs.logging("INFO", "Purchase Transaction tab is clicked");
			flag = webDB.waitForElement(CommonUiLocators.RfqTab, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CommonUiLocators.RfqTab, ElementType.Xpath);
				logs.logging("INFO", "Rfq Tab Is Clicked");
				flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.LeftSideAddIcon, ElementType.Xpath, 45);
				if (flag) {
					logs.logging("INFO", "Rfq clicked and navigated successfully");

				}

			}
		}
		Assert.assertTrue(flag);
	}
}
