package commonfunctions;

import static org.testng.Assert.assertTrue;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks January 15, 2025
 */
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import commonfunctions.supplychainmanagement.PurchaseOrderFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.financials.BankPaymentLocators;
import commonlocators.payroll.AttendanceLeaveLocators;
import commonlocators.payroll.CommonPayrollLocators;
import commonlocators.payroll.EmployeeAdjustmentLocators;
import commonlocators.payroll.EmployeeMasterLocators;
import commonlocators.payroll.PreparePayrollLocators;
import commonlocators.productionplanning.BillOfMaterialsLocators;
import commonlocators.productionplanning.CompletionProductionOrderLocators;
import commonlocators.productionplanning.MRPLocators;
import commonlocators.productionplanning.ProductionOrderLocators;
import commonlocators.qualitycontrol.InspectionPlanLocators;
import commonlocators.qualitycontrol.InspectionRequestLocators;
import commonlocators.supplychainmanagement.ApprovalPageLocators;
import commonlocators.supplychainmanagement.CustomerItemsLocators;
import commonlocators.supplychainmanagement.CustomerScheduleLocators;
import commonlocators.supplychainmanagement.GateEntryPageLocators;
import commonlocators.supplychainmanagement.GrnLocators;
import commonlocators.supplychainmanagement.MaterialReturnLocators;
import commonlocators.supplychainmanagement.OpenSalesOrderLocators;
import commonlocators.supplychainmanagement.OpenSoAmdLocators;
import commonlocators.supplychainmanagement.POAmendmentLocators;
import commonlocators.supplychainmanagement.PricelistPageLocators;
import commonlocators.supplychainmanagement.PurchaseRequestLocators;
import commonlocators.supplychainmanagement.RfqLocators;
import commonlocators.supplychainmanagement.SalesOrderLocators;
import commonlocators.supplychainmanagement.SalesPriceListLocators;
import commonlocators.supplychainmanagement.SsoPageLocators;
import commonlocators.supplychainmanagement.StockAdjustmentLocators;
import commonlocators.supplychainmanagement.StocksPageLocators;
import io.restassured.http.ContentType;
import utils.Listener;
import utils.ReportLoger;
import utils.RestUtils;
import utils.RestUtils.methodType;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class CommonFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();
	private static RestUtils restApi = new RestUtils();

	/**
	 * This method used to open browser with platformName that was set in maven goal
	 * 
	 * @author Sakthi-GWL
	 */
	public void browserOpen() {
		try {
			webDB.Setup(System.getProperty("platformName"));
			System.out.println("Chrome arguments: " + WebDriverBase.chromeOptions.get().asMap());
			enterUrl();
		} catch (Exception e) {
			logs.loggingconsole("INFO", "Browser not Setup with the Exception: " + e);
			logs.loggingconsole("INFO", "Retrying Browser Open Again....!!!");
		}
	}

	/**
	 * This method used to navigate url that was set in Maven Goal
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean enterUrl() {
		try {
			webDB.enterURL(System.getProperty("url"));
			flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.LoginForm, ElementType.Xpath, 45);
			VariableHandler.setSiteWorked(flag);
		} catch (Exception e) {
			logs.loggingconsole("INFO", "Enter URL Failed with the Exception: " + e);
			logs.loggingconsole("INFO", "Retrying Navigate To URL");
		}
		return flag;
	}

	/**
	 * This method used to wait 10 seconds till disappear table refreshing cells
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	@SuppressWarnings("null")
	public boolean checkRefreshCellsDisappear() {
		hardWait(500);
//		if (webDB.verifyIsElementDisplayed("(" + CommonUiLocators.EntryTableRefreshCellImage + ")[1]",
//				ElementType.Xpath)) {
		List<WebElement> refreshElements = WebDriverBase.getDriver()
				.findElements(By.xpath(CommonUiLocators.EntryTableRefreshCellImage));
		List<WebElement> AfterWaitrefreshElements = null;
		try {
			for (WebElement element : refreshElements) {
				WebDriverWait wait = new WebDriverWait(WebDriverBase.getDriver(), Duration.ofSeconds(10));
				wait.until(ExpectedConditions.invisibilityOf(element));
				if (element.isDisplayed()) {
					AfterWaitrefreshElements.add(element);
				}
			}
			flag = AfterWaitrefreshElements.isEmpty();
			if (!flag) {
				for (WebElement element : AfterWaitrefreshElements) {
					WebDriverWait wait = new WebDriverWait(WebDriverBase.getDriver(), Duration.ofSeconds(10));
					wait.until(ExpectedConditions.invisibilityOf(element));
				}
			}
		} catch (Exception e) {

		}
//		}
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
		WebDriverBase.getDriver().manage().deleteAllCookies();
		enterUrl();
		VariableHandler.setSessionID(null);
		VariableHandler.setSessionOpened(false);
		return true;
	}

	/**
	 * This method used to Login with valid credentials
	 * 
	 * @author Sakthi-GWL
	 * @author Harikrishna - RMITS
	 * @throws InterruptedException
	 */
	public void validCommonLogin(String Username, String Password, String... Name) {
		enterUrl();
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
					checkRefreshCellsDisappear();
					webDB.pressEnterKey();
					checkRefreshCellsDisappear();
					if (webDB.verifyIsElementDisplayed(CommonUiLocators.LoginBtn, ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(CommonUiLocators.LoginBtn, ElementType.Xpath);
						webDB.clickAnElementWithoutException(CommonUiLocators.LoginBtn, ElementType.Xpath);
						webDB.pressEnterKey();
					}
					flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.PageHeaderText, ElementType.Xpath, 15);
					if (!flag) {
						flag = webDB.isElementDisplayed(CommonUiLocators.LoginBtn, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Retrying Login....");
							webDB.pressEnterKey();
							flag = webDB.waitForElement(CommonUiLocators.PageHeaderText, ElementType.Xpath);
						}
					}
				}
			}
		}
		if (flag) {
			flag = webDB.waitForElement(CommonUiLocators.GetTextUserName, ElementType.Xpath);
			if (flag) {
				checkRefreshCellsDisappear();
				String GetTextUserName = webDB.getTextFromElement(CommonUiLocators.GetTextUserName, ElementType.Xpath);
				logs.logging("INFO", GetTextUserName + " User Logged In Successfully");
				VariableHandler.setSignInUserName(GetTextUserName);
				String URL = webDB.getDriver().getCurrentUrl();
				String SessionID = URL.split("session=")[1].toString();
				VariableHandler.setSessionID(SessionID);
				VariableHandler.setSessionOpened(true);
				String HomePageUrl = System.getProperty("url").replace("loginpage/login", "loginpage/menu?session=")
						.concat(VariableHandler.getSessionID());
				VariableHandler.setHomePageURL(HomePageUrl);
				logs.logging("INFO", "Current Username: " + VariableHandler.getSignInUserName());
			}
		}
//		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Navigate Purchase Order Page
	 * 
	 * @author Deepan-GWL
	 */
	public void navigatePurchaseOrderPage() {
		flag = webDB.waitForElement(CommonUiLocators.BusFunctionSearchBox, ElementType.Xpath);
		if (flag) {
			WebElement inputField = webDB.getDriver().findElement(By.xpath(CommonUiLocators.BusFunctionSearchBox));
			inputField.clear();
			inputField.sendKeys("URCHASE ORDERS");
			inputField.sendKeys(Keys.HOME);
			inputField.sendKeys("P");
		}
		webDB.pressEnterKey();
		webDB.waitUntilElementToNotBeStale(CommonUiLocators.PurchaseAddIcon, ElementType.Xpath);
		flag = webDB.waitForElement(CommonUiLocators.PurchaseAddIcon, ElementType.Xpath);
		Assert.assertTrue(flag);

	}

	/**
	 * This Method used to navigate Purchase Request Page
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigatePurchaseRequestPage() {
		flag = webDB.waitForElement(CommonUiLocators.BusFunctionSearchBox, ElementType.Xpath);
		if (flag) {
			WebElement inputField = webDB.getDriver().findElement(By.xpath(CommonUiLocators.BusFunctionSearchBox));
			inputField.clear();
			inputField.sendKeys("URCHASE REQUESTS");
			inputField.sendKeys(Keys.HOME);
			inputField.sendKeys("P");
		}
		webDB.pressEnterKey();
		webDB.waitUntilElementToNotBeStale(CommonUiLocators.LeftSideAddIcon, ElementType.Xpath);
		flag = webDB.waitForElement(CommonUiLocators.LeftSideAddIcon, ElementType.Xpath);
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
		String DisplayText = null;
		checkRefreshCellsDisappear();
		boolean Displayflag = webDB.verifyIsElementDisplayed((locator + "/preceding-sibling::input"),
				ElementType.Xpath);
		if (Displayflag) {
			DisplayText = webDB.getDomPropertyFromElement((locator + "/preceding-sibling::input"), ElementType.Xpath,
					getDataFromTestDataSheet("Value"));
		}
		if (DisplayText == null || DisplayText.isBlank() || !DisplayText.equalsIgnoreCase(Value)) {
			flag = webDB.waitForElement(locator, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(locator, ElementType.Xpath);
				webDB.clickAnElement(locator, ElementType.Xpath);
				webDB.switchToDefaultContent();
				checkRefreshCellsDisappear();
				WebElement search = new WebDriverWait(WebDriverBase.getDriver(), Duration.ofSeconds(10))
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CommonUiLocators.searchBar)));
				search.clear();
				search.sendKeys(Value);
				checkRefreshCellsDisappear();
				checkRefreshCellsDisappear();
				webDB.waitForElementUntilGivenTime(CommonUiLocators.searchBarResults, ElementType.Xpath, 5);
				checkRefreshCellsDisappear();
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.SearchBarIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.SearchBarIcon, ElementType.Xpath);
					webDB.clickAnElementWithoutException(CommonUiLocators.SearchBarIcon, ElementType.Xpath);
				}
				checkRefreshCellsDisappear();
				checkRefreshCellsDisappear();
				checkRefreshCellsDisappear();
				try {
					List<WebElement> Elements = WebDriverBase.getDriver()
							.findElements(By.xpath(CommonUiLocators.convertListOfXPath(Value)));
					if (Elements.size() > 0) {
						for (WebElement webElement : Elements) {
							if (webElement.isDisplayed()
									&& webElement.getText().trim().equalsIgnoreCase(Value.trim())) {
								((JavascriptExecutor) WebDriverBase.getDriver())
										.executeScript("arguments[0].scrollIntoView(true);", webElement);
								try {
									Actions action = new Actions(WebDriverBase.getDriver());
									action.moveToElement(webElement).build().perform();
								} catch (Exception e) {
								}
								webElement.click();
								flag = true;
							}
						}
					}
				} catch (Exception e) {
					webDB.pressEnterKey();
					checkRefreshCellsDisappear();
					logs.logging("INFO",
							"Search bar Still Displayed Not Clicked Values in Lov Btn with Exception: " + e);
				}
			}
			checkRefreshCellsDisappear();
			checkRefreshCellsDisappear();
			assertTrue(flag);
		} else {
			logs.logging("INFO", "Already the '" + Value + "' selected in Lov");
			webDB.switchToDefaultContent();
		}
	}

	public boolean fluentWaitClickElement(WebElement element) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(WebDriverBase.getDriver()).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(StaleElementReferenceException.class);
		return wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
	}

	/**
	 * This Method used to click create icon - left add icon
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickCreateIcon() {
		flag = webDB.waitForElement(CommonUiLocators.PurchaseAddIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CommonUiLocators.PurchaseAddIcon, ElementType.Xpath);
			webDB.clickAnElement(CommonUiLocators.PurchaseAddIcon, ElementType.Xpath);
			logs.logging("INFO", "Left Side Add Icon is Displayed and Clicked");
			flag = webDB.waitForElement(CommonUiLocators.CreatePoButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CommonUiLocators.CreatePoButton, ElementType.Xpath);
				checkRefreshCellsDisappear();
//				webDB.waitForElement(CommonUiLocators.PoCreateFrame, ElementType.Xpath);
				webDB.switchToFrame(CommonUiLocators.PoCreateFrame, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.CreatePoTickIcon, ElementType.Xpath);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Navigates Open So Amd
	 * 
	 * @author Kowshik-RITS
	 * @return
	 */
	public boolean navigatesOpenSoAmdScreen() {
		navigateToSearchTab(webDB.getDataFromProperties("OpenSOScreen"), OpenSoAmdLocators.OpenSoAmdRadio);
		return flag;
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
			checkRefreshCellsDisappear();
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
		checkRefreshCellsDisappear();
//		flag = webDB.waitForElement(CommonUiLocators.UsernameIcon, ElementType.Xpath);
//		if (flag) {
//			webDB.clickAnElement(CommonUiLocators.UsernameIcon, ElementType.Xpath);
//			logs.logging("INFO", "Username Icon is Clicked");
		flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.LeftNavigationBar, ElementType.Xpath, 30);
		if (flag) {
			String value = webDB.getDomAttributeFromElement(CommonUiLocators.LeftNavigationBar, ElementType.Xpath,
					"aria-expanded");
			if (value.equalsIgnoreCase("false")) {
				webDB.javaScriptClickAnElement(CommonUiLocators.LeftNavigationBar, ElementType.Xpath);
			}
			flag = webDB.waitForElement(CommonUiLocators.TransactionTab, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Left Navigation Bar Is clicked");
			}
		}
//		}
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
//		if (VariableHandler.getTestData() == null) {
//			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
//					+ "TestDatas.xlsx";
//			VariableHandler.setTestData(
//					excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("TestDataSheetName")));
//		} else {
//		}
//		for (int i = 0; i < VariableHandler.getTestData().length; i++) {
//			if (VariableHandler.getTestData()[i][0].equals(Key)) {
//				value = VariableHandler.getTestData()[i][1];
//			}
//		}
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QC-data.json";
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try (FileReader reader = new FileReader(Filepath)) {
			Object obj = parser.parse(reader);
			jsonObject = (JSONObject) obj;
			value = jsonObject.get(Key).toString();
		} catch (Exception e) {
			logs.logging("INFO", "Please check JSON file that the given key value is present or not: " + Key);
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
		webDB.switchToDefaultContent();
		webDB.switchToMainTab();
		VariableHandler.setSessionID(null);
	}

	/**
	 * This method used to Navigate Purchase Request Close Short Page
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigatePurchaseRequestCloseShortPage() {
//		clickLeftNavigationBar();
//		clickSupplyChainTab();
//		clickPurchaseTab();
//		clickPurchaseTransactionTab();
//		flag = webDB.waitForElement(CommonUiLocators.PurchaseRequestCloseShortTab, ElementType.Xpath);
//		if (flag) {
//			webDB.clickAnElement(CommonUiLocators.PurchaseRequestCloseShortTab, ElementType.Xpath);
//			flag = webDB.waitForElement(PurchaseRequestLocators.PRCloseShort_AddNewBtn, ElementType.Xpath);
//			if (flag) {
//				logs.logging("INFO", "Navigated To Purchase Request Close Short page Successfully");
//			}
//		}
		navigateToSearchTab(webDB.getDataFromProperties("PRAmendment"), PurchaseRequestLocators.AddButtonPRCs);
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
//		clickLeftNavigationBar();
//		clickSupplyChainTab();
//		clickSalesTab();
//		clickSalesTransactionTab();
//		flag = webDB.waitForElement(SalesOrderLocators.SalesOrderTab, ElementType.Xpath);
//		if (flag) {
//			webDB.clickAnElement(SalesOrderLocators.SalesOrderTab, ElementType.Xpath);
//			flag = webDB.waitForElement(SalesOrderLocators.AddButtonSalesOrder, ElementType.Xpath);
//			if (flag) {
//				logs.logging("INFO", "Navigated to Sales Order Screen Sucessfully");
//			}
//		}
		navigateToSearchTab(webDB.getDataFromProperties("SaleOrderScreen"), SalesOrderLocators.SOSearchButton);

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
			WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(5));
			wait.until(ExpectedConditions.invisibilityOf(ToastElements));
			flag = !ToastElements.isDisplayed();
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
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
		flag = webDB.waitForElement(CommonUiLocators.BusFunctionSearchBox, ElementType.Xpath);
		if (flag) {
			WebElement inputField = webDB.getDriver().findElement(By.xpath(CommonUiLocators.BusFunctionSearchBox));
			inputField.clear();
			inputField.sendKeys("URCHASE ORDERS AMENDMENT");
			inputField.sendKeys(Keys.HOME);
			inputField.sendKeys("P");
		}
		webDB.pressEnterKey();
		webDB.waitUntilElementToNotBeStale(POAmendmentLocators.POPendingIcon, ElementType.Xpath);
		flag = webDB.waitForElement(POAmendmentLocators.POPendingIcon, ElementType.Xpath);
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
//		clickLeftNavigationBar();
//		clickPayrollTab();
//		clickPayrollTransactionTab();
//		clickPayrollEmployeeMasterTab();
//		flag = webDB.waitForElement(CommonUiLocators.PayrollEmployeeMasterScreen, ElementType.Xpath);
//		if (flag) {
//			webDB.moveToElement(CommonUiLocators.PayrollEmployeeMasterScreen, ElementType.Xpath);
//			webDB.clickAnElement(CommonUiLocators.PayrollEmployeeMasterScreen, ElementType.Xpath);
//			flag = webDB.waitForElement(EmployeeMasterLocators.EmployeeMasterAddIcon, ElementType.Xpath);
//			if (flag) {
//				logs.logging("INFO", "Navigated To Employee Master Screen Successfully");
//			}
//		}
		navigateToSearchTab(webDB.getDataFromProperties("EmployeeMasterScreen"),
				EmployeeMasterLocators.EmployeeMasterLabel);
		webDB.clickAnElement(EmployeeMasterLocators.EmployeeMasterLabel, ElementType.Xpath);
		flag = webDB.waitForElement(EmployeeMasterLocators.EmployeeMasterAddIcon, ElementType.Xpath);
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Navigate Price List Page
	 * 
	 * @author Durai
	 */
	public void navigatePricelistPage() {
//		clickLeftNavigationBar();
//		clickSupplyChainTab();
//		clickPurchaseTab();
//		clickPricelistTransactionTab();
		navigateToSearchTab(webDB.getDataFromProperties("PRICELISTENTRY"), PricelistPageLocators.AddPriceList);
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
//		clickPayrollTab();
//		clickPayrollTransactionTab();
//		clickAttendanceLeaveTab();
//		flag = webDB.waitForElement(CommonUiLocators.EmployeeLeaveRequestScreen, ElementType.Xpath);
//		if (flag) {
//			webDB.clickAnElement(CommonUiLocators.EmployeeLeaveRequestScreen, ElementType.Xpath);
//			flag = webDB.waitForElement(AttendanceLeaveLocators.EmployeePermissionButton, ElementType.Xpath);
//			if (flag) {
//				logs.logging("INFO", "Leave Request Tab Is Clicked");
//			}
//		}
		navigateToSearchTab(webDB.getDataFromProperties("LeaveRequestScreen"),
				AttendanceLeaveLocators.EmployeePermissionButton);
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to Navigate Sales Invoice Screen
	 * 
	 * @author Vimal - RMITS
	 * @return Boolean Flag
	 */
	public void navigateSalesInvoicePage() {
		navigateToSearchTab(webDB.getDataFromProperties("SIScreen"), CommonUiLocators.Sales_Inv_Pend_Btn);
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
		String Email = "testauto".concat(RandomStringUtils.randomAlphanumeric(4)) + "@mail.com";
		return Email;
	}

	/**
	 * This Method used to Navigate GRN Page
	 * 
	 * @author PremKumar.K-RMIT
	 */
	public void naviagteGrnPage() {
//		clickLeftNavigationBar();
//		clickSupplyChainTab();
//		clickPurchaseTab();
//		clickPurchaseTransactionTab();
//		ClickGrnPurchase();
		navigateToSearchTab(webDB.getDataFromProperties("GRNPurchse"), GrnLocators.Lov);

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
//		clickLeftNavigationBar();
//		navigateToQualityTab();
//		navigateToQualityTransactionTab();
//		navigateToInspectionDocuments();
		navigateToSearchTab(webDB.getDataFromProperties("InspectionDocument"),
				CommonUiLocators.PendingInspectionDocuments);
	}

	/**
	 * Navigate To MAterial Receipt Voucher
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToMaterialReceiptVoucher() {
//		clickLeftNavigationBar();
//		clickSupplyChainTab();
//		navigateToInventoryTab();
//		navigateToInventoryTranscationTab();
//		navigateToMaterialReceiptVocherTab();
		navigateToSearchTab(webDB.getDataFromProperties("MaterialReceiptVoucher"),
				CommonUiLocators.PendingReceiptVoucherButton);
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
//		clickLeftNavigationBar();
//		navigateToFinancialTab();
//		navigateToFinancialTransactionTab();
//		navigateToFinancialPartyDocumentsTab();
//		navigateToFinancialPurchaseBillTab();
		navigateToSearchTab(webDB.getDataFromProperties("PurchaseBillTab"), CommonUiLocators.PendingPurchaseBill);
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
//		clickLeftNavigationBar();
//		navigateToFinancialTab();
//		navigateToFinancialTransactionTab();
//		navigateToPaymentReceiptTab();
//		navigateToPaymentReceipt();
		navigateToSearchTab(webDB.getDataFromProperties("PaymentReceipt"), CommonUiLocators.PendingCrDocs);
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
	 * This method is used to Navigate Material Request Screen
	 * 
	 * @author Vimal - RMITS
	 */
	public void navigateToMaterialRequestScreen() {
		navigateToSearchTab(webDB.getDataFromProperties("MaterialRequestScreen"), CommonUiLocators.MaterialReq_AddIcon);
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
//		clickLeftNavigationBar();
//		clickSupplyChainTab();
//		clickSalesTab();
//		clickSalesTransactionTab();
//		flag = webDB.waitForClickElement(CommonUiLocators.OpenSOTab, ElementType.Xpath);
//		if (flag) {
//			webDB.clickAnElement(CommonUiLocators.OpenSOTab, ElementType.Xpath);
//			flag = webDB.waitForClickElement(OpenSalesOrderLocators.OpenSORadioBtn, ElementType.Xpath);
//			if (flag) {
//				logs.logging("INFO", "navigated open so screen successfully");
//			}
//		}
		navigateToSearchTab(webDB.getDataFromProperties("OpenSOScreen"), OpenSalesOrderLocators.OpenSORadioBtn);
//		Assert.assertTrue(flag);
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
				webDB.pressTabKey();
				checkRefreshCellsDisappear();
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
//		clickLeftNavigationBar();
//		clickSupplyChainTab();
//		navigateToInventoryTab();
//		navigateToInventoryTranscationTab();
//		navigateToMaterialRequest();

		navigateToSearchTab(webDB.getDataFromProperties("MaterialRequestScreen"),
				CommonUiLocators.CreateMaterialRequestButton);
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
//		clickLeftNavigationBar();
//		clickSupplyChainTab();
//		navigateToInventoryTab();
//		navigateToInventoryReportsTab();
//		navigateToStocksTab();
		navigatePurchaseOrderPage();
		navigateToSearchTab(webDB.getDataFromProperties("CurrentStocks"), CommonUiLocators.StocksCheckBox);
	}

	/**
	 * This method is used to Navigate Proforma Invoice Screen
	 * 
	 * @author Vimal - RMITS
	 */
	public void navigateProformaInvoice() {
		navigateToSearchTab(webDB.getDataFromProperties("PIScreen"), CommonUiLocators.Proforma_FindPage_Add_Btn);
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
//		clickLeftNavigationBar();
//		clickProductionPlanningTab();
//		clickProductionPlanningTransactionTab();
//		flag = webDB.waitForElement(CommonUiLocators.ProdPlan_Transaction_MRPScreen, ElementType.Xpath);
//		if (flag) {
//			webDB.clickAnElement(CommonUiLocators.ProdPlan_Transaction_MRPScreen, ElementType.Xpath);
//			flag = webDB.waitForElement(MRPLocators.FindMRPNoLovBtn, ElementType.Xpath);
//			if (flag) {
//				logs.logging("INFO", "Production Planning MRP Screen Navigated Successfully");
//			}
//		}
		navigateToSearchTab(webDB.getDataFromProperties("MRPScreen"), MRPLocators.FindMRPNoLovBtn);
		Assert.assertTrue(flag);
	}

	/**
	 * To Navigate Production Order Screen In Production Planning
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToProductionOrderInProdPlanning() {
//		clickLeftNavigationBar();
//		clickProductionPlanningTab();
//		clickProductionPlanningTransactionTab();
//		flag = webDB.waitForElement(CommonUiLocators.ProdPlan_Transaction_ProductionOrder, ElementType.Xpath);
//		if (flag) {
//			webDB.clickAnElement(CommonUiLocators.ProdPlan_Transaction_ProductionOrder, ElementType.Xpath);
//			flag = webDB.waitForElement(ProductionOrderLocators.FindMRPNoLovBtn, ElementType.Xpath);
//			if (flag) {
//				logs.logging("INFO", "Production Planning - Production Order Screen Navigated Successfully");
//			}
//		}
		navigateToSearchTab(webDB.getDataFromProperties("ProductionOrderScreen"),
				ProductionOrderLocators.FindProdOrderNoLovBtn);
		clickShowMoreFindTable();
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
//		clickLeftNavigationBar();
//		clickSupplyChainTab();
//		navigateToInventoryTab();
//		clickInventorySetupTab();
//		clickItemmaster();
		navigateToSearchTab(webDB.getDataFromProperties("Items"), CommonUiLocators.ItemMasterAdd);
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
//		clickLeftNavigationBar();
//		clickSupplyChainTab();
//		navigateToInventoryTab();
//		navigateToInventoryTranscationTab();
//		navigateToMivTab();
		navigateToSearchTab(webDB.getDataFromProperties("MaterialIssuanceVoucher"), CommonUiLocators.PendingMIV);
	}

	/**
	 * To Navigate Completion Production Order Screen In Production Planning
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToCompletionProductionOrderInProdPlanning() {
//		clickLeftNavigationBar();
//		clickProductionPlanningTab();
//		clickProductionPlanningTransactionTab();
//		flag = webDB.waitForElement(CommonUiLocators.ProdPlan_Transaction_CompletionProductionOrder, ElementType.Xpath);
//		if (flag) {
//			webDB.clickAnElement(CommonUiLocators.ProdPlan_Transaction_CompletionProductionOrder, ElementType.Xpath);
//			flag = webDB.waitForElement(CompletionProductionOrderLocators.CompletionProcessPendingIcon,
//					ElementType.Xpath);
//			if (flag) {
//				logs.logging("INFO", "Production Planning - Completion Production Order Screen Navigated Successfully");
//			}
//		}
		navigateToSearchTab(webDB.getDataFromProperties("CompProdOrderSingleProcessScreen"),
				CompletionProductionOrderLocators.CompletionProcessSingleLabel);
		webDB.clickAnElement(CompletionProductionOrderLocators.CompletionProcessSingleLabel, ElementType.Xpath);
		flag = webDB.waitForElement(CompletionProductionOrderLocators.CompletionProcessPendingIcon, ElementType.Xpath);
		Assert.assertTrue(flag);
	}

	/**
	 * Navigate To Material Issuance Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToMaterialIssuanceVoucherTab() {
//		clickLeftNavigationBar();
//		clickSupplyChainTab();
//		navigateToInventoryTab();
//		navigateToInventoryTranscationTab();
//		flag = webDB.waitForElement(CommonUiLocators.MIVTab, ElementType.Xpath);
//		if (flag) {
//			webDB.clickAnElement(CommonUiLocators.MIVTab, ElementType.Xpath);
//			flag = webDB.waitForElement(CommonUiLocators.LeftSidePendingIcon, ElementType.Xpath);
//			if (flag) {
//				logs.logging("INFO", "Material Issuance Voucher Tab Is Clicked");
//
//			}
//		}

//		Assert.assertTrue(flag);
		navigateToSearchTab(webDB.getDataFromProperties("MaterialIssuanceVoucher"), CommonUiLocators.PendingMIV);
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
		webDB.switchToDefaultContent();
		if (webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 10)) {
			VariableHandler.setApprovalFlag(true);
			logs.logging("INFO", "Toast Message Displayed & Documents Is Posted.");
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			return true;
		} else if (webDB.waitForElement(ApprovalPageLocators.ApprovalTitle, ElementType.Xpath)) {
			VariableHandler.setApprovalFlag(false);
			logs.logging("INFO", "Workflow forwarder iframe Displayed");
			return true;
		} else {
			if (webDB.getTextFromElement(CommonUiLocators.PostToastMessage, ElementType.Xpath).contains("Posted")
					|| webDB.getTextFromElement(CommonUiLocators.PostToastMessage, ElementType.Xpath)
							.contains("posted")) {
				VariableHandler.setApprovalFlag(true);
				return true;
			} else {
				logs.logging("INFO", "Toast Message or Workflow Iframe both not displayed");
				return false;
			}
		}
	}

	/*
	 * Navigate To Inventory Stocks Transaction Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToInventoryStocksTransactionTab() {
//		clickLeftNavigationBar();
//		clickSupplyChainTab();
//		navigateToInventoryTab();
//		navigateToInventoryReportsTab();
//		navigateToStockTransactionTab();
		navigateToSearchTab(webDB.getDataFromProperties("StockTransaction"), CommonUiLocators.StockTransactionCheckBox);
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
//		clickLeftNavigationBar();
//		clickSupplyChainTab();
//		navigateToGateEntryTab();
//		navigateToGateEntryTransactionTab();
//		navigateToDeliverChallanTab();
		navigateToSearchTab(webDB.getDataFromProperties("DeliveryChallan"), CommonUiLocators.PendingChallan);
	}

	/**
	 * To Navigate Bill of Materials Screen In Production Planning
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToBillOfMaterialsPage() {
//		clickLeftNavigationBar();
//		clickProductionPlanningTab();
//		clickProductionPlanningTransactionTab();
//		flag = webDB.waitForElement(CommonUiLocators.ProdPlan_Transaction_BillOfMaterials, ElementType.Xpath);
//		if (flag) {
//			webDB.clickAnElement(CommonUiLocators.ProdPlan_Transaction_BillOfMaterials, ElementType.Xpath);
//			flag = webDB.waitForElement(BillOfMaterialsLocators.BOM_FindSearchBtn, ElementType.Xpath);
//			if (flag) {
//				logs.logging("INFO", "Production Planning - Completion Production Order Screen Navigated Successfully");
//			}
//		}
		navigateToSearchTab(webDB.getDataFromProperties("BOMScreen"), BillOfMaterialsLocators.BOM_FindSearchBtn);
		expandFindTable();
	}

	/**
	 * This Method Is Used To Navigate To SubCntarct Order
	 * 
	 * @author Vimal - RMITS
	 * 
	 */
	public void navigateToSubcontractOrder() {
		navigateToSearchTab(webDB.getDataFromProperties("SubcontractScreen"),
				CommonUiLocators.Subcontract_Pending_Icon);
	}

	/**
	 * This Methods Is Used To Navigate MRV Tab For ADMIN User
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void navigateToMaterialReceiptVoucherAdminUser() {
		navigateToSearchTab(webDB.getDataFromProperties("MATERIALRECEIPTVOUCHER"),
				CommonUiLocators.PendingReceiptVoucherButton);
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
//		clickLeftNavigationBar();
//		clickSupplyChainTab();
//		navigateToSubcontracting();
//		navigateToSubcontractingTransaction();
//		navigateToGrnScoTab();
		navigateToSearchTab(webDB.getDataFromProperties("GrnSCo"), CommonUiLocators.PendingGrn);
	}

	/**
	 * This method used to Navigate Rfq Transaction Tab
	 * 
	 * @author Durai-RMITS
	 */
	public void navigateRfqPage() {
		// clickLeftNavigationBar();
//		clickSupplyChainTab();
//		clickPurchaseTab();
//		clickRfqTransactionTab();
		navigateToSearchTab(webDB.getDataFromProperties("RFQ"), RfqLocators.PendBTN);
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

	/**
	 * This Method used to navigate Open PO/RC Page
	 * 
	 * @author Deepan-GWL
	 */
	public void clickOpenPORCTab() {
		flag = webDB.waitForElement(CommonUiLocators.OpenPO_RC, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.OpenPO_RC, ElementType.Xpath);
			flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.OpenPO_RCButton, ElementType.Xpath, 45);
			if (flag) {
				logs.logging("INFO", "Open PO/RC Tab Is CLicked and navigated successfully");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used to navigate Open PO/RC Page
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateOpenPORCPage() {
//		clickLeftNavigationBar();
//		clickSupplyChainTab();
//		clickPurchaseTab();
//		clickPurchaseTransactionTab();
//		clickOpenPORCTab();
		navigateToSearchTab(webDB.getDataFromProperties("OpenPORC"), CommonUiLocators.OpenPO_RCButton);
	}

	/**
	 * This method used to Navigate Setup Inventory Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateSetupInventoryPage() {
//		clickLeftNavigationBar();
//		clickSupplyChainTab();
//		navigateToInventoryTab();
//		clickInventorySetupTab();
//		clickSetupInventoryTab();
		navigateToSearchTab(webDB.getDataFromProperties("SetUpInventory"), CommonUiLocators.ApplControlLabel);
	}

	/**
	 * This Method Is Used To Click SetUp Inventory Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void clickSetupInventoryTab() {
		flag = webDB.waitForElement(CommonUiLocators.SetupInventoryTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.SetupInventoryTab, ElementType.Xpath);
			flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.ApplControlLabel, ElementType.Xpath, 45);
			if (flag) {
				logs.logging("INFO", "Setup Inventory Tab Is CLicked and navigated successfully");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Used To Navigate Leave Entry Tab
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateLeaveEntryScreen() {
//		clickLeftNavigationBar();
//		clickPayrollTab();
//		clickPayrollTransactionTab();
//		clickAttendanceLeaveTab();
//		flag = webDB.waitForElement(CommonUiLocators.EmployeeLeaveEntryScreen, ElementType.Xpath);
//		if (flag) {
//			webDB.clickAnElement(CommonUiLocators.EmployeeLeaveEntryScreen, ElementType.Xpath);
//			flag = webDB.waitForElement(AttendanceLeaveLocators.LeaveRadioBtnLeaveEntry, ElementType.Xpath);
//			if (flag) {
//				logs.logging("INFO", "Leave Entry Tab Navigated Successfully");
//			}
//		}
		navigateToSearchTab(webDB.getDataFromProperties("LeaveEntryScreen"),
				AttendanceLeaveLocators.LeaveRadioBtnLeaveEntry);
		Assert.assertTrue(flag);
	}

	/**
	 * This method is used to Navigate Sales Order Amendment Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void navigateSalesOrderAmendmentPage() {
		navigateToSearchTab(webDB.getDataFromProperties("SOAmd"), SalesOrderLocators.SOAmendmentPendingButton);
	}

	/**
	 * This method is used to Navigate Setup Payroll Screen
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigatePayrollApplicationScreen() {
		navigateToSearchTab(webDB.getDataFromProperties("PayrollApplicaionCrtlScreen"),
				AttendanceLeaveLocators.SetupLeaveLabel);
	}

	/**
	 * Navigate To Credit Note Tab
	 * 
	 * @author Vimal-RMITS
	 */
	public void navigateToCreditNoteScreen() {
		navigateToSearchTab(webDB.getDataFromProperties("CRScreen"), CommonUiLocators.CreditNote_Pending_Icon);
	}

	/**
	 * Navigate To Gate Entry Tab
	 * 
	 * @author Vimal-RMITS
	 */
	public void navigateTogateEntry() {
		navigateToSearchTab(webDB.getDataFromProperties("GEScreen"), CommonUiLocators.GateEntryPendingIcon);
	}

	/**
	 * This method used to Navigate Open PO Order Transaction Tab
	 * 
	 * @author Durai-RMITS
	 */
	public void navigateOpenPoOrderPage() {
		// clickLeftNavigationBar();
//		clickSupplyChainTab();
//		clickPurchaseTab();
//		clickOpenPoTransactionTab();
		navigateToSearchTab(webDB.getDataFromProperties("OPENPO"), SsoPageLocators.OPORadioBtn);
	}

	/**
	 * This method used to Navigate Open PO Order Transaction Tab
	 * 
	 * @author Durai-RMITS
	 */
	private void clickOpenPoTransactionTab() {
		flag = webDB.waitForElement(CommonUiLocators.PurchaseTransactionTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.PurchaseTransactionTab, ElementType.Xpath);
			logs.logging("INFO", "Purchase Transaction tab is clicked");
			flag = webDB.waitForElement(CommonUiLocators.OPOTab, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CommonUiLocators.OPOTab, ElementType.Xpath);
				logs.logging("INFO", "OPO Tab Is Clicked");
				flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.OPOClose, ElementType.Xpath, 45);
				if (flag) {
					logs.logging("INFO", "OPO clicked and navigated successfully");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Navigate Supplier Schedule Order Transaction Tab
	 * 
	 * @author Durai-RMITS
	 */
	public void navigateSupplierScheduleOrderPage() {
		// clickLeftNavigationBar();
//		clickSupplyChainTab();
//		clickPurchaseTab();
//		clickSSOTransactionTab();
		navigateToSearchTab(webDB.getDataFromProperties("PurchaseOrderTab"), SsoPageLocators.SsoPendingBtnNew);
	}

	/**
	 * This method used to Navigate Supplier Schedule Order Transaction Tab
	 * 
	 * @author Durai-RMITS
	 */
	private void clickSSOTransactionTab() {
		flag = webDB.waitForElement(CommonUiLocators.PurchaseTransactionTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.PurchaseTransactionTab, ElementType.Xpath);
			logs.logging("INFO", "Purchase Transaction tab is clicked");
			flag = webDB.waitForElement(CommonUiLocators.SSOTab, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CommonUiLocators.SSOTab, ElementType.Xpath);
				logs.logging("INFO", "Rfq Tab Is Clicked");
				flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.LeftSideAddIcon, ElementType.Xpath, 45);
				if (flag) {
					logs.logging("INFO", "Rfq clicked and navigated successfully");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Navigate Gate Entry Transaction Tab
	 * 
	 * @author Durai-RMITS
	 */
	public void navigateToGateEntryDocument() {
//			clickLeftNavigationBar();
//			clickGateEntryTab();
		navigateToSearchTab(webDB.getDataFromProperties("GATEENTRY"), GateEntryPageLocators.GENPendBtn);
	}

	/**
	 * This method used to Navigate Navigate Gate Entry Tab
	 * 
	 * @author Durai-RMITS
	 */
	private void clickGateEntryTab() {
		flag = webDB.waitForElement(CommonUiLocators.SupplyChainTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.SupplyChainTab, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.GateEntry, ElementType.Xpath);
			if (flag) {
				flag = webDB.clickAnElement(CommonUiLocators.GateEntry, ElementType.Xpath);
				logs.logging("INFO", "Supply Chain Management Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Navigate Customer Schedule Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void navigateCustomerScheduleScreen() {
		navigateToSearchTab(webDB.getDataFromProperties("CustomerSchedule"), CustomerScheduleLocators.CSAddButton);
	}

	/**
	 * This method used to Navigate Setup Purchase Tab
	 * 
	 * @author Deepan-GWL
	 */
	private void clickSetupPurchaseTab() {
		flag = webDB.waitForElement(CommonUiLocators.SupplyChainPurchaseSetup, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.SupplyChainPurchaseSetup, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.SetupPurchase, ElementType.Xpath);
			if (flag) {
				flag = webDB.clickAnElement(CommonUiLocators.SetupPurchase, ElementType.Xpath);
				logs.logging("INFO", "Setup Purchase Tab Is Clicked");
				flag = webDB.waitForElement(CommonUiLocators.BuyerTab, ElementType.Xpath);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * 
	 * This method used to click Payroll - Transaction tab Payroll
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickPayrollTransactionPayrollTab() {
		flag = webDB.waitForElement(CommonUiLocators.PayrollTransactionPayroll, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CommonUiLocators.PayrollTransactionPayroll, ElementType.Xpath);
			webDB.clickAnElement(CommonUiLocators.PayrollTransactionPayroll, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PrepareProcessPayrollScreen, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Payroll Transaction Payroll Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Navigate To Purchase Setup Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToPurchaseSetupTab() {
//		clickLeftNavigationBar();
//		clickSupplyChainTab();
//		clickPurchaseTab();
//		clickSetupPurchaseTab();
		navigateToSearchTab(webDB.getDataFromProperties("SetUpPurchaseTab"), CommonUiLocators.BuyerTab);
	}

	/**
	 * This Method Is Used To Navigate To Sys. Administration
	 * 
	 * @author Deepan-GWL
	 */
	public void clickSystemAdministrationTab() {
		flag = webDB.waitForElement(CommonUiLocators.SysAdminstrationTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.SysAdminstrationTab, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.SysAdminTransaction, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sys Administration Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to navigate to prepare Payroll screen
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToPrepareProcessPayrollTab() {
//		clickLeftNavigationBar();
//		clickPayrollTab();
//		clickPayrollTransactionTab();
//		clickPayrollTransactionPayrollTab();
//		flag = webDB.waitForElement(CommonUiLocators.PrepareProcessPayrollScreen, ElementType.Xpath);
//		if (flag) {
//			webDB.moveToElement(CommonUiLocators.PrepareProcessPayrollScreen, ElementType.Xpath);
//			webDB.clickAnElement(CommonUiLocators.PrepareProcessPayrollScreen, ElementType.Xpath);
//			flag = webDB.waitForElement(PreparePayrollLocators.PayrollFindSearchBtn, ElementType.Xpath);
//			if (flag) {
//				logs.logging("INFO", "Navigated Successfully to Prepare Process Payroll Screen");
//			}
//		}
		navigateToSearchTab(webDB.getDataFromProperties("PrepareProcessPayrollScreen"),
				PreparePayrollLocators.PreparePayrollLabel);
		webDB.clickAnElement(PreparePayrollLocators.PreparePayrollLabel, ElementType.Xpath);
		flag = webDB.waitForElement(PreparePayrollLocators.PayrollFindSearchBtn, ElementType.Xpath);
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Navigate To Sys. Administration Transaction
	 * 
	 * @author Deepan-GWL
	 */
	public void clickSystemAdministrationTransactionTab() {
		flag = webDB.waitForElement(CommonUiLocators.SysAdminTransaction, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.SysAdminTransaction, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.EmployeeQuickEntryTab, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sys Administration Transaction Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Navigate To Employee Quick Entry Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void clickEmployeeQuickEntryTab() {
		flag = webDB.waitForElement(CommonUiLocators.EmployeeQuickEntryTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.EmployeeQuickEntryTab, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.AddEmployeeButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Employee Quick Entry Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Navigate To Employee Quick Entry Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToEmployeeQuickEntryTab() {
//		clickLeftNavigationBar();
//		clickSystemAdministrationTab();
//		clickSystemAdministrationTransactionTab();
//		clickEmployeeQuickEntryTab();
		navigateToSearchTab(webDB.getDataFromProperties("EmployeeQuickEntry"), CommonUiLocators.AddEmployeeButton);
	}

	/**
	 * This method used to Navigate Approved Supplier PO Tab
	 * 
	 * @author Deepan-GWL
	 */
	private void clickApprovedSupplierPoTab() {
		flag = webDB.waitForElement(CommonUiLocators.SupplyChainPurchaseSetup, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.SupplyChainPurchaseSetup, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.ApprovedSupplierPo, ElementType.Xpath);
			if (flag) {
				flag = webDB.clickAnElement(CommonUiLocators.ApprovedSupplierPo, ElementType.Xpath);
				logs.logging("INFO", "Setup Purchase Tab Is Clicked");
				flag = webDB.waitForElement(CommonUiLocators.AddSupplierButton, ElementType.Xpath);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Navigate To Approved Supplier PO Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToApprovedSupplierPOTab() {
//		clickLeftNavigationBar();
//		clickSupplyChainTab();
//		clickPurchaseTab();
//		clickApprovedSupplierPoTab();
		navigateToSearchTab(webDB.getDataFromProperties("ApprovedSupplierPOTab"), CommonUiLocators.AddSupplierButton);
	}

	/**
	 * This Method Is Used To Navigate To Share Of Business Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void clickShareOfBusinessTab() {
		flag = webDB.waitForElement(CommonUiLocators.ShareOfBusiness, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.ShareOfBusiness, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.AddShareOfBusiness, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Share Of Business Tab Is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Navigate Share Of Business Page
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateShareOfBusinessPage() {
		clickLeftNavigationBar();
		clickSupplyChainTab();
		clickPurchaseTab();
		clickPurchaseTransactionTab();
		clickShareOfBusinessTab();
	}

	/**
	 * This Method Is Used To Navigate To Search Tab
	 * 
	 * @author Deepan-GWL
	 * @param TabName
	 * @param PageLocators
	 */
	public void navigateToSearchTab(String TabName, String PageLocators) {
		if (VariableHandler.getSiteWorked()) {
			webDB.waitForPageReady(20);
			// TODO will comment the hard script lines once loading issue resolved
			webDB.waitForElement("//a[@class='t-Header-logo-link']", ElementType.Xpath);
//			if (webDB.verifyIsElementDisplayed(PageLocators, ElementType.Xpath)) {
//				logs.logging("INFO", "Already In Navigated Tab: " + TabName);
//				return;
//			}
			webDB.clickAnElement("//a[@class='t-Header-logo-link']", ElementType.Xpath);
			checkRefreshCellsDisappear();
			webDB.waitForPageReady(20);
			flag = webDB.waitForElement(CommonUiLocators.BusFunctionSearchBox, ElementType.Xpath);
			if (flag) {
				webDB.waitUntilElementToBeClickable(CommonUiLocators.BusFunctionSearchBox, ElementType.Xpath);
				webDB.waitUntilElementToNotBeStale(CommonUiLocators.BusFunctionSearchBox, ElementType.Xpath);
				webDB.clearTextField(CommonUiLocators.BusFunctionSearchBox, ElementType.Xpath);
				webDB.sendTextToAnElement(CommonUiLocators.BusFunctionSearchBox, TabName.toUpperCase(),
						ElementType.Xpath);
				checkRefreshCellsDisappear();
				webDB.waitForPageReady(30);
				webDB.verifyIsElementDisplayed(PageLocators, ElementType.Xpath);
				checkRefreshCellsDisappear();
				flag = webDB.waitForElementUntilGivenTime(PageLocators, ElementType.Xpath, 15);
				if (flag) {
					logs.logging("INFO", "Navigated To " + TabName);
				} else {
					webDB.pressDownArrowKey();
					webDB.pressEnterKey();
					webDB.waitForPageReady(30);
					flag = webDB.waitForElement(PageLocators, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Navigated To " + TabName);
					}
				}
			}
		} else {
			flag = false;
		}
		assertTrue(flag);
	}

	/**
	 * Navigate To Rework Order Tab
	 * 
	 * @author Vimal-RMITS
	 */
	public void navigateToReworkOrder() {
		navigateToSearchTab(webDB.getDataFromProperties("RWScreen"), CommonUiLocators.Rework_Pending_Icon);
	}

	/**
	 * Navigate To Rework Order Completion Tab
	 * 
	 * @author Vimal-RMITS
	 */
	public void navigateToReworkOrderCompletion() {
		navigateToSearchTab(webDB.getDataFromProperties("RWCScreen"), CommonUiLocators.ReworkOrd_Completion_Pend_Icon);
	}

	/**
	 * Navigate To Debit Note Tab
	 * 
	 * @author Vimal-RMITS
	 */
	public void navigateToDebitNoteScreen() {
		navigateToSearchTab(webDB.getDataFromProperties("DBScreen"), CommonUiLocators.DebitNote_Add_Icon);
	}

	/***
	 * Navigates to Items
	 * 
	 * @author Kowshik-RMITS
	 */
	public void navigateItemPage1() {
		navigateToSearchTab(webDB.getDataFromProperties("Items"), CommonUiLocators.FindScreenItemField);
	}

	/***
	 * Navigates to Items Asso
	 * 
	 * @author Kowshik-RMITS
	 */
	public void navigateItemAssoScreen() {
		navigateToSearchTab(webDB.getDataFromProperties("ItemUnitAssoScreen"), CommonUiLocators.unitLov);
	}

	/***
	 * Navigates to Customer Items
	 * 
	 * @author Kowshik-RMITS
	 */
	public void navigateCustomerItemScreen() {
		navigateToSearchTab(webDB.getDataFromProperties("CustomerItemScreen"), CommonUiLocators.AddPLusButton);
	}

	/**
	 * Navigates to Price List , Std Price , Catalogue Price
	 * 
	 * @author Kowshik-RMITS
	 */
	public void navigatePriceList_StdPrice_CataloguePrice() {
		navigateToSearchTab(webDB.getDataFromProperties("PCS"), CommonUiLocators.AddButton);
	}

	/**
	 * This method used to navigate to Employee Adjustment screen
	 *
	 * @author Sakthi-GWL
	 */
	public void navigateToEmployeeAdjustmentTab() {
//		clickLeftNavigationBar();
//		clickPayrollTab();
//		clickPayrollTransactionTab();
//		clickPayrollTransactionPayrollTab();
//		flag = webDB.waitForElement(CommonUiLocators.EmployeeAdjustmentScreen, ElementType.Xpath);
//		if (flag) {
//			webDB.moveToElement(CommonUiLocators.EmployeeAdjustmentScreen, ElementType.Xpath);
//			webDB.clickAnElement(CommonUiLocators.EmployeeAdjustmentScreen, ElementType.Xpath);
//			flag = webDB.waitForElement(EmployeeAdjustmentLocators.FindEmployeeAdjustmentHeader, ElementType.Xpath);
//			if (flag) {
//				logs.logging("INFO", "Navigated Successfully to Employee Adjustment Screen");
//			}
//		}
		navigateToSearchTab(webDB.getDataFromProperties("EmployeeAdjustmentScreen"),
				EmployeeAdjustmentLocators.EA_MigrationBtn);
	}

	/**
	 * This method used to navigate to BOM Migration screen
	 *
	 * @author Sakthi-GWL
	 */
	public void navigateToBOMMigrationTab() {
		navigateToSearchTab(webDB.getDataFromProperties("BOMMigrationStdScreen"),
				BillOfMaterialsLocators.BOMMigrationAddBtn);
	}

	/**
	 * Navigates Setup Sales
	 * 
	 * @author Kowshik-RMITS
	 */
	public void navigateSetup_Sales() {
		navigateToSearchTab(webDB.getDataFromProperties("Sales"), CommonUiLocators.salesRegion);
	}

	/**
	 * Verify Add Table Specs
	 * 
	 * @author Deepan-GWL
	 * @param Tablespec
	 * @param InputParameter
	 * @param Enteredvalue
	 */
	public void verifyAddTableSpecs(String Tablespec, String InputParameter, String Enteredvalue) {
		checkRefreshCellsDisappear();
		flag = webDB.waitForElement(Tablespec, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(Tablespec, ElementType.Xpath);
			webDB.doubleClickAnElement(Tablespec, ElementType.Xpath);
			webDB.waitForElement(InputParameter, ElementType.Xpath);
			webDB.clearText(InputParameter, ElementType.Xpath);
			checkRefreshCellsDisappear();
			webDB.waitForElement(InputParameter, ElementType.Xpath);
			flag = webDB.sendTextToAnElement(InputParameter, Enteredvalue, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Entered Value Is: " + Enteredvalue + " in 'verifyAddTableSpecs'");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to approve documents using API
	 * 
	 * @author Sakthi-GWL
	 * @param BU
	 * @param DocNo
	 * @param EmpId
	 * @param Unit
	 * @param User
	 */
	public void approveDocuments(String BU, String DocNo, String EmpId, String Unit, String User) {
		hardWait(3000);
		RestUtils.resetRestAssured();
		restApi.userSetsThebaseUrlAs(webDB.getDataFromProperties("ApproveBaseUrl"));
		restApi.setQueryParam("p_bu", BU);
		restApi.setQueryParam("p_doc_no", DocNo);
		restApi.setQueryParam("p_from_empid", EmpId);
		restApi.setQueryParam("p_plnt", Unit);
		restApi.setQueryParam("p_user", User);
		restApi.setQueryParam("p_wfdc_act", "A");
		restApi.userHitsTheRequestAs(methodType.Post, ContentType.ANY);
		String Value = restApi.response.getHeader("p_out_msg");
		VariableHandler.setLastApprovalAPIHeaderText(Value);
		logs.logging("INFO", "Displayed Approval Response Header: " + Value);
		logs.logging("INFO", "Displayed Approval Response Status Code: " + restApi.response.getStatusCode());
		flag = Value.contains("Approved");
		if (flag) {
			logs.logging("INFO", "Verified Approval Response Header");
			flag = restApi.response.getStatusCode() == 200;
			if (flag) {
				logs.logging("INFO", "Verified Approval Response Status Code");
			}
		} else {
			logs.logging("INFO", "'Doc No'" + DocNo + " Not Approved Check Response Header");
		}
		assertTrue(flag);
	}

	/**
	 * This method used to approve Item Documents documents using API
	 * 
	 * @author Sakthi-GWL
	 * @param BU
	 * @param ItemId
	 * @param EmpId
	 * @param Unit
	 * @param User
	 */
	public void approveItemIdDocuments(String BU, String ItemId, String EmpId, String Unit, String User) {
		restApi.userSetsThebaseUrlAs(webDB.getDataFromProperties("ApproveBaseUrl"));
		restApi.setQueryParam("p_bu", BU);
		restApi.setQueryParam("p_doc_no", "");
		restApi.setQueryParam("p_from_empid", EmpId);
		restApi.setQueryParam("p_plnt", Unit);
		restApi.setQueryParam("p_user", User);
		restApi.setQueryParam("p_wfdc_act", "A");
		restApi.setQueryParam("p_item_id", ItemId);
		restApi.userHitsTheRequestAs(methodType.Post, ContentType.ANY);
		String Value = restApi.response.getHeader("p_out_msg");
		logs.logging("INFO", "Displayed Approval Response Header: " + Value);
		logs.logging("INFO", "Displayed Approval Response Status Code: " + restApi.response.getStatusCode());
		flag = Value.contains("Approved");
		if (flag) {
			logs.logging("INFO", "Verified Approval Response Header");
			flag = restApi.response.getStatusCode() == 200;
			if (flag) {
				logs.logging("INFO", "Verified Approval Response Status Code");
			}
		}
		assertTrue(flag);
		RestUtils.resetRestAssured();
	}

	/**
	 * This method used to return documents using API
	 * 
	 * @author Sakthi-GWL
	 * @param BU
	 * @param DocNo
	 * @param EmpId
	 * @param Unit
	 * @param User
	 * @param ReturnUserEmpId
	 */
	public void returnDocuments(String BU, String DocNo, String EmpId, String Unit, String User,
			String ReturnUserEmpId) {
		RestUtils.resetRestAssured();
		restApi.userSetsThebaseUrlAs(webDB.getDataFromProperties("ApproveBaseUrl"));
		restApi.setQueryParam("p_bu", BU);
		restApi.setQueryParam("p_doc_no", DocNo);
		restApi.setQueryParam("p_from_empid", EmpId);
		restApi.setQueryParam("p_plnt", Unit);
		restApi.setQueryParam("p_user", User);
		restApi.setQueryParam("p_wfdc_act", "R");
		restApi.setQueryParam("p_nxt_fwd_person", ReturnUserEmpId);
		restApi.userHitsTheRequestAs(methodType.Post, ContentType.ANY);
		String Value = restApi.response.getHeader("p_out_msg");
		logs.logging("INFO", "Displayed Returned Response Header: " + Value);
		logs.logging("INFO", "Displayed Returned Response Status Code: " + restApi.response.getStatusCode());
		flag = Value.contains("Returned");
		if (flag) {
			logs.logging("INFO", "Verified Returned Response Header");
			flag = restApi.response.getStatusCode() == 200;
			if (flag) {
				logs.logging("INFO", "Verified Returned Response Status Code");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Check Assertion Both Values
	 * 
	 * @author Deepan-GWL
	 * @param Actual
	 * @param Expected
	 */
	public void verifyAssertionBothValues(String Actual, String Expected) {
		if ((Expected.equalsIgnoreCase("WORK IN PROGRESS WAREHOUSE") && Actual.equalsIgnoreCase("WIP WH"))
				|| (Actual.equalsIgnoreCase("WORK IN PROGRESS WAREHOUSE") && Expected.equalsIgnoreCase("WIP WH"))) {
			Actual = Expected;
		}
		if ((Expected.equalsIgnoreCase("RAW MATERIAL WAREHOUSE") && Actual.equalsIgnoreCase("RM WH"))
				|| (Actual.equalsIgnoreCase("RAW MATERIAL WAREHOUSE") && Expected.equalsIgnoreCase("RM WH"))) {
			Actual = Expected;
		}
		if ((Expected.equalsIgnoreCase("FINISHED GOODS WAREHOUSE") && Actual.equalsIgnoreCase("FG WH"))
				|| (Actual.equalsIgnoreCase("FINISHED GOODS WAREHOUSE") && Expected.equalsIgnoreCase("FG WH"))) {
			Actual = Expected;
		}
		if ((Expected.equalsIgnoreCase("RM KD WH") && Actual.equalsIgnoreCase("RM KD"))
				|| (Actual.equalsIgnoreCase("RM KD WH") && Expected.equalsIgnoreCase("RM KD"))) {
			Actual = Expected;
		}
		try {
			flag = (Actual.equalsIgnoreCase(Expected));
		} catch (Exception e) {
			logs.logging("INFO", "Exception occurs while asserting: " + e);
		}
		if (flag) {
			logs.logging("INFO", "Verified Actual And Expected Values");
		} else {
			logs.logging("INFO", "Actual Value Is: " + Actual);
			logs.logging("INFO", "Expected Value Is: " + Expected);
			logs.logging("INFO", "Mismatch With Actual And Expected");
		}
		assertTrue(flag);
	}

	/**
	 * To Navigate Completion Production Order Multiple Process Screen In Production
	 * Planning
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToCompletionProductionOrderMultipleProcessInProdPlanning() {
		navigateToSearchTab(webDB.getDataFromProperties("ProductionCompletionMultiple"),
				CompletionProductionOrderLocators.CompletionProcessMultipleLabel);
		webDB.clickAnElement(CompletionProductionOrderLocators.CompletionProcessMultipleLabel, ElementType.Xpath);
		flag = webDB.waitForElement(CompletionProductionOrderLocators.CompletionProcessPendingIcon, ElementType.Xpath);
	}

	/**
	 * This method used to Click The Setup Tab
	 * 
	 * @author Harikrishna - RMITS
	 */
	private void clickSetupTab() {
		flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.SetupTab, ElementType.Xpath, 30);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.SetupTab, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.ExpandAll, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Tranaction tab Is Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to Click The Transaction Tab
	 * 
	 * @author Harikrishna - RMITS
	 */
	private void clickSetupExpandAllButton() {
		flag = webDB.waitForElement(CommonUiLocators.ExpandAll, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.ExpandAll, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.Items, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Setup Expand All Button Is clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	public void clickCustomerItemsScreen() {
		flag = webDB.waitForElement(CommonUiLocators.CustomerItems, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.CustomerItems, ElementType.Xpath);
			flag = webDB.waitForElement(CustomerItemsLocators.AddPLusButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated To Customer Items Screen");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to Click The Transaction Tab
	 * 
	 * @author Harikrishna - RMITS
	 */
	private void clickTransactionTab() {
		flag = webDB.waitForElement(CommonUiLocators.TransactionTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.TransactionTab, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.ExpandAll, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Tranaction tab Is Clicked Sucessfully");
				checkRefreshCellsDisappear();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to Click The Transaction Tab
	 * 
	 * @author Harikrishna - RMITS
	 */
	private void clickTransactionExpandAllButton() {
		flag = webDB.waitForElement(CommonUiLocators.ExpandAll, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.ExpandAll, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.CustomerSchedule, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Expand All Button Is clicked Sucessfully");
				checkRefreshCellsDisappear();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is used To Click The Open SO Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickOpenSOScreen() {
		flag = webDB.waitForElement(CommonUiLocators.OpenSO, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.OpenSO, ElementType.Xpath);
			flag = webDB.waitForElement(OpenSalesOrderLocators.OpenSOAmendRadioBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated To Open SO Screen");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is used To Click The Open SO Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickPriceList_Standard_Catalog_Screen() {
		flag = webDB.waitForElement(CommonUiLocators.PriceList, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.PriceList, ElementType.Xpath);
			flag = webDB.waitForElement(SalesPriceListLocators.AddButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated To Price List Screen");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is used To Click The Customer Schedule Amendment Entry Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickCustomerScheduleAmendmentEntry() {
		flag = webDB.waitForElement(CommonUiLocators.CustomerScheduleAmendment, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.CustomerScheduleAmendment, ElementType.Xpath);
			flag = webDB.waitForElement(CustomerScheduleLocators.CSAmdAddButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated To Customer Schedule Amendment Entry Screen");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to Navigate Customer Items Page
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void navigateCustomerItemsPage() {
//		clickLeftNavigationBar();
//		clickSetupTab();
//		clickSetupExpandAllButton();
//		clickCustomerItemsScreen();
		navigateToSearchTab(webDB.getDataFromProperties("CustomerItems"), CustomerItemsLocators.AddPLusButton);
	}

	/**
	 * This Method Is Used To Navigate Open So Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void navigateOpenSOScreen() {
//		clickLeftNavigationBar();
//		clickTransactionTab();
//		clickTransactionExpandAllButton();
//		clickOpenSOScreen();
		navigateToSearchTab(webDB.getDataFromProperties("OpenSOScreen"), OpenSalesOrderLocators.OpenSOAmendRadioBtn);
	}

	/**
	 * This Method Is Used To Navigate PriceList Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void navigatePriceList_Standard_Catalog_Screen() {
		navigateToSearchTab(webDB.getDataFromProperties("PCS"), SalesPriceListLocators.AddButton);
	}

	/**
	 * This Method Is Used To Navigate Customer Schedule Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void navigateCustomerScheduleAmendmentScreen() {
		navigateToSearchTab(webDB.getDataFromProperties("CustSchAmd"), CustomerScheduleLocators.CSAmdAddButton);
	}

	/**
	 * This Method Is Used To Add Table Lov Details
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAddTableLovDetails(String TableLocator, String LovButton, String LovValue,
			String LovInputLocator) {
		flag = webDB.waitForElement(TableLocator, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(TableLocator, ElementType.Xpath);
			webDB.doubleClickAnElement(TableLocator, ElementType.Xpath);
			webDB.clickAnElementWithoutException(TableLocator, ElementType.Xpath);
			if (!webDB.verifyIsElementDisplayed(LovButton, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(TableLocator, ElementType.Xpath);
				webDB.clickAnElementWithoutException(TableLocator, ElementType.Xpath);
			}
			logs.logging("INFO", "Table Is Clicked");
			searchAndSelectValueInFilters(LovButton, LovValue);
			webDB.pressEscapeKey();
//			flag = webDB.waitForElement(LovInputLocator, ElementType.Xpath);
//			if (flag) {
//				String value = webDB.getAttributeFromElement(LovInputLocator, ElementType.Xpath,
//						getDataFromTestDataSheet("Value"));
//				logs.logging("INFO", "Get Text Value from Table Is: " + value);
//				flag = value.contains(LovValue);
//				if (flag) {
//					logs.logging("INFO", "Verified Value Successfully");
//				}
//			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Employee Loan Request Screen
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToLoanRequestScreen() {
		navigateToSearchTab(webDB.getDataFromProperties("EmpLoanRequestScreen"),
				AttendanceLeaveLocators.LoanRequestAddButton);
	}

	/**
	 * This Method Is Used To Employee Loan Payment Screen
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToLoanPaymentScreen() {
		navigateToSearchTab(webDB.getDataFromProperties("EmployeeLoanPayment"),
				AttendanceLeaveLocators.LoanPaymentFindSearch);
	}

	/**
	 * This Method Is Used To Navigate TDS Declaration Screen
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToTDSDeclarationScreen() {
		navigateToSearchTab(webDB.getDataFromProperties("TDSDeclaration"), AttendanceLeaveLocators.TDSLabel);
		webDB.clickAnElement(AttendanceLeaveLocators.TDSLabel, ElementType.Xpath);
		flag = webDB.waitForElement(AttendanceLeaveLocators.TDSFindSearchButton, ElementType.Xpath);
	}

	/**
	 * This Method Is Used To Navigate Employee Overtime
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToEmployeeOvertime() {
		navigateToSearchTab(webDB.getDataFromProperties("Overtime"), AttendanceLeaveLocators.OvertimeFindSearchButton);
	}

	/**
	 * This Method Is Used To Navigate Travel Request
	 * 
	 * @author Kishore-RMITS
	 */
	public void navigateToTravelRequest() {
		navigateToSearchTab(webDB.getDataFromProperties("Travel"), AttendanceLeaveLocators.TravelRequestFindAddButton);
	}

	/**
	 * This Method Is Used To Navigate Travel Reimbursement
	 * 
	 * @author Kishore-RMITS
	 */
	public void navigateToTravelReimbursement() {
		navigateToSearchTab(webDB.getDataFromProperties("TravelReimbursement"),
				AttendanceLeaveLocators.TravelReimbFindSearchButton);
	}

	/**
	 * This Method Is Used To Navigate General Expense
	 * 
	 * @author Kishore-RMITS
	 */
	public void navigateToGeneralExpense() {
		navigateToSearchTab(webDB.getDataFromProperties("GeneralExpense"),
				AttendanceLeaveLocators.GeneralExpFindAddButton);
	}

	/**
	 * This Method Is Used To Navigate Resignation Request
	 * 
	 * @author Kishore-RMITS
	 */
	public void navigateToResignationRequest() {
		navigateToSearchTab(webDB.getDataFromProperties("ResignationRequest"),
				AttendanceLeaveLocators.ResigRequestFindAddButton);
	}

	/**
	 * This Method Is Used To Navigate Resignation Request
	 * 
	 * @author Kishore-RMITS
	 */
	public void navigateToFullAndFinalSettlement() {
		navigateToSearchTab(webDB.getDataFromProperties("FullAndFinalSettlement"),
				AttendanceLeaveLocators.FFSFindPendingForFNF);
	}

	/**
	 * This Method Is Used To Navigate To Setup Leave Loan Screen
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToSetupLeaveLoanScreen() {
		navigateToSearchTab(webDB.getDataFromProperties("LeaveLoanSetupScreen"),
				CommonPayrollLocators.LeaveAndLoadScreen_LeaveSubTab);
	}

	/**
	 * This method used to click clear icons in find page for all screen
	 * 
	 * @author Sakthi-GWL
	 */
	public void clearFiltersInFindPage() {
		checkRefreshCellsDisappear();
		flag = webDB.waitForElement(CommonUiLocators.ClearFindFilterIcon, ElementType.Xpath);
		if (flag) {
			List<WebElement> Clearicon = webDB.getListOfElements(CommonUiLocators.ClearFindFilterIcon,
					ElementType.Xpath);
			for (WebElement icon : Clearicon) {
				try {
					icon.click();
					checkRefreshCellsDisappear();
				} catch (Exception e) {

				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Navigate Customer Master Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void navigateCustomerMasterScreen() {
		navigateToSearchTab(webDB.getDataFromProperties("CustomerMaster"), SalesOrderLocators.CustomerMasterAddButton);
	}

	/**
	 * This Method Is Used To Navigate Customer Master Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void navigateStockAdjustmentScreen() {
		navigateToSearchTab(webDB.getDataFromProperties("StockAdjustment"), StockAdjustmentLocators.FindAddButton);
	}

	/**
	 * Navigate To Production Screen
	 * 
	 * @author Vimal-RMITS
	 */
	public void navigateToProductionOrderScreen() {
		navigateToSearchTab(webDB.getDataFromProperties("ProductionOrderScreen"),
				CommonUiLocators.CreateProdOrderAddIcon);

	}

	/**
	 * Navigate To Open SCO Tab
	 * 
	 * @author Vimal-RMITS
	 */
	public void navigateToOpenPriceSCO() {
		navigateToSearchTab(webDB.getDataFromProperties("OpenscoScreen"), CommonUiLocators.Open_SCO_Find_AddIcon);
	}

	/**
	 * This Method used to click PR create icon - left add icon
	 * 
	 * @author Deepan-GWL
	 */
	public void clickPRCreateIcon() {
		flag = webDB.waitForElement(CommonUiLocators.PrCreateButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.PrCreateButton, ElementType.Xpath);
			checkRefreshCellsDisappear();
			checkRefreshCellsDisappear();
			flag = webDB.waitForElement(CommonUiLocators.CreatePrButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CommonUiLocators.CreatePrButton, ElementType.Xpath);
				webDB.waitForElement(CommonUiLocators.PrCreateFrame, ElementType.Xpath);
				webDB.switchToFrame(CommonUiLocators.PrCreateFrame, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.CreatePRTickIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Left Side Add Icon is Displayed and Clicked");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Navigate To Production And Shop Floor Setup Tab
	 * 
	 * @author Vimal-RMITS
	 */
	public void navigateToSetupPlanningandManufacturing() {
		navigateToSearchTab(webDB.getDataFromProperties("PPShopFloorSetupScreen"), CommonUiLocators.ProductionPlanning);

	}

	/**
	 * Navigate To Production And Shop Floor Setup Tab
	 * 
	 * @author Vimal-RMITS
	 */
	public void navigateToMachineScreen() {
		navigateToSearchTab(webDB.getDataFromProperties("MachineScreen"), CommonUiLocators.MachineAdd_Icon);

	}

	/**
	 * Navigate To Manpower Screen
	 * 
	 * @author Vimal-RMITS
	 */
	public void navigateToManPowerScreen() {
		navigateToSearchTab(webDB.getDataFromProperties("ManpowerScreen"), CommonUiLocators.ManpowerGrp_Add_Icon);

	}

	/**
	 * This method used to add author names in extent report
	 * 
	 * @param method
	 */
	public void enterAuthorNamesInExtentReport(Method method) {
		Author author = method.getAnnotation(Author.class);
		String ClassName = method.getDeclaringClass().getSimpleName();
		if (author != null) {
			Listener.test.get().assignAuthor(author.value());
		}
		if (ClassName != null) {
			Listener.test.get().assignCategory(ClassName);
		}
	}

	/**
	 * This method used to Navigate Quality Control Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateInspectionPlan() {
		navigateToSearchTab(webDB.getDataFromProperties("InspectionPlanScreen"),
				InspectionPlanLocators.AddIconInFindPage);
	}

	/**
	 * This method used to Navigate Quality Control Inspection Request
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateInspectionRequest() {
		navigateToSearchTab(webDB.getDataFromProperties("InspectionRequestScreen"),
				InspectionRequestLocators.InspectionRequestAddIcon);
	}

	/**
	 * This method used to Navigate Material Return
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateMaterialReturn() {
//		hardWait(10000);
		navigateToSearchTab(webDB.getDataFromProperties("MaterialReturnScreen"),
				MaterialReturnLocators.MaterialReturnAddIcon);
	}

	/**
	 * This method used to approve documents using API
	 * 
	 * @author Sakthi-GWL
	 * @param BU
	 * @param DocNo
	 * @param EmpId
	 * @param Unit
	 * @param User
	 */
	public String approveDocumentsWithDocumentNumber(String BU, String DocNo, String EmpId, String Unit, String User) {
		RestUtils.resetRestAssured();
		restApi.userSetsThebaseUrlAs(webDB.getDataFromProperties("ApproveBaseUrl"));
		restApi.setQueryParam("p_bu", BU);
		restApi.setQueryParam("p_doc_no", DocNo);
		restApi.setQueryParam("p_from_empid", EmpId);
		restApi.setQueryParam("p_plnt", Unit);
		restApi.setQueryParam("p_user", User);
		restApi.setQueryParam("p_wfdc_act", "A");
		restApi.userHitsTheRequestAs(methodType.Post, ContentType.ANY);
		String Value = restApi.response.getHeader("p_out_msg");
		VariableHandler.setLastApprovalAPIHeaderText(Value);
		logs.logging("INFO", "Displayed Approval Response Header: " + Value);
		logs.logging("INFO", "Displayed Approval Response Status Code: " + restApi.response.getStatusCode());
		flag = Value.contains("Approved");
		if (flag) {
			logs.logging("INFO", "Verified Approval Response Header");
			flag = restApi.response.getStatusCode() == 200;
			if (flag) {
				logs.logging("INFO", "Verified Approval Response Status Code");
			}
		} else {
			logs.logging("INFO", "'Doc No'" + DocNo + " Not Approved Check Response Header");
		}
		assertTrue(flag);
		return Value;
	}

	/**
	 * This Method Is Used To Navigate To SubCntarct Order CloseShort
	 * 
	 * @author Vimal - RMITS
	 * 
	 */
	public void navigateToSubcontractOrderCloseShort() {
		navigateToSearchTab(webDB.getDataFromProperties("SubcontractCSScreen"),
				CommonUiLocators.SubcontractCSFindAddIcon);
	}

	/**
	 * Navigate To Financial Payment Receipt
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToPostDatedCheque() {
		navigateToSearchTab(webDB.getDataFromProperties("PostDatedCheque"),
				BankPaymentLocators.PDCVoucherNumberLovButton);
	}

	/**
	 * This method used to Send and Select Lov Button in table data
	 *
	 * @author Sakthi-GWL
	 * @param DataLocator
	 * @param LOVLocator
	 * @param Value
	 */
	public void verifyAddTableLovValue(String DataLocator, String LOVLocator, String Value) {
		flag = webDB.waitForElement(DataLocator, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(DataLocator, ElementType.Xpath);
			webDB.doubleClickAnElement(DataLocator, ElementType.Xpath);
			searchAndSelectValueInFilters(LOVLocator, Value);
		}
		assertTrue(flag);
	}

	/**
	 * Verify Add Table Specs
	 * 
	 * @author Deepan-GWL
	 * @param Tablespec
	 * @param InputParameter
	 * @param Enteredvalue
	 */
	public void verifyAddTableSpecsClearField(String Tablespec, String InputParameter, String Enteredvalue) {
		checkRefreshCellsDisappear();
		flag = webDB.waitForElement(Tablespec, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(Tablespec, ElementType.Xpath);
			webDB.doubleClickAnElement(Tablespec, ElementType.Xpath);
			webDB.waitForElement(InputParameter, ElementType.Xpath);
			webDB.clearTextField(InputParameter, ElementType.Xpath);
			flag = webDB.sendTextToAnElement(InputParameter, Enteredvalue, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Entered Value Is: " + Enteredvalue + " in 'verifyAddTableSpecs'");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to navigate home menu page
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToHomePage() {
		try {
			webDB.navigateToUrl(VariableHandler.getHomePageURL());
			try {
				Alert alert = WebDriverBase.getDriver().switchTo().alert();
				alert.accept();
			} catch (Exception e) {
			}
			flag = webDB.waitForElement(CommonUiLocators.GetTextUserName, ElementType.Xpath);
			if (!flag) {
				VariableHandler.setSessionID(null);
				enterUrl();
				VariableHandler.setSessionOpened(false);
			}
		} catch (Exception e) {
			logs.loggingconsole("INFO", "Exception Occurs while switch home page: " + e);
			VariableHandler.setSessionID(null);
			VariableHandler.setSessionOpened(false);
			enterUrl();
		}
	}

	/**
	 * This method used to check header with text and return index values
	 * dynamically in add tables
	 * 
	 * @author Sakthi-GWL
	 * @param Locator
	 * @param HeaderValue
	 * @return String Locator
	 */
	public static String checkAndReturnIndexForLocatorWithHeaderValue(String Locator, String HeaderValue) {
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "TableHeaders.json";
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try (FileReader reader = new FileReader(Filepath)) {
			Object obj = parser.parse(reader);
			jsonObject = (JSONObject) obj;
			HeaderValue = jsonObject.get(HeaderValue).toString();
//			System.out.println("Header Value1 " + HeaderValue);
		} catch (Exception e) {
//			logs.logging("INFO", "No Data Found In Table.json file: " + HeaderValue);
		}

		Locator = Locator + "/tbody/tr/*[count(" + Locator + "/thead/tr[1]/th[normalize-space()='" + HeaderValue
				+ "']/preceding-sibling::th) + 1]";
		return Locator;
//		List<WebElement> elements = webDB.getListOfElements(Locator, ElementType.Xpath);
//		System.out.println("Header Value2 " + HeaderValue + elements.size());
//		if (elements.size() == 0) {
//			hardWait(3000);
//			elements = webDB.getListOfElements(Locator, ElementType.Xpath);
//		}
//		for (int i = 0; i < elements.size(); i++) {
//			System.out.println("Header Value3.1 ");
//			String text = (String) ((JavascriptExecutor) webDB.getDriver())
//					.executeScript("return arguments[0].textContent.trim();", elements.get(i));
//			System.out.println("Header Value 3.2 " + text);
//			System.out.println("Header Value3 " + HeaderValue + " i " + i + " Element " + elements.get(i).getText()
//					+ " javascript gettext " + text);
//			if (text.equals(HeaderValue)) {
//				System.out.println("Header Value4 " + HeaderValue + " i " + i);
//				if (Locator.contains("thead")) {
//					Locator = Locator.replace("thead", "tbody");
//					System.out.println("Header Value5 " + HeaderValue + " i " + i + " Locators " + Locator);
//				}
//				if (Locator.contains("/th")) {
//					Locator = Locator.replace("/th", "/td").concat("[").concat(String.valueOf(i + 1)).concat("]");
//					System.out.println("Header Value6 " + HeaderValue + " i " + i + " Locators " + Locator);
//				}
//				System.out.println("Header Value7 " + HeaderValue + " i " + i + " Locators " + Locator);
//				break;
//			}
//		}
////		if (Locator.contains("thead")) {
////			System.out.println("Locators1 " + Locator + " header " + HeaderValue);
////			return Locator.replace("thead", "tbody").replace("/th", "/td").concat("[1]");
////		} else {
////			System.out.println("Locators2 " + Locator + " header " + HeaderValue);
////			return Locator.replace("/th", "/td").concat("[1]");
////		}
//
//		return Locator;
	}

	/**
	 * This method used to check header with text and return index values
	 * dynamically in find page tables
	 * 
	 * @author Sakthi-GWL
	 * @param Locator
	 * @param HeaderValue
	 * @return String Locator
	 */
	public static String checkAndReturnLocatorForFindPageTable(String Locator, String HeaderValue) {
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "TableHeaders.json";
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try (FileReader reader = new FileReader(Filepath)) {
			Object obj = parser.parse(reader);
			jsonObject = (JSONObject) obj;
//			System.out.println("Header value1 " + HeaderValue);
			HeaderValue = jsonObject.get(HeaderValue).toString();
//			System.out.println("Header value2 " + HeaderValue);
		} catch (Exception e) {
		}
//		System.out.println(Locator);
		List<WebElement> elements = webDB.getListOfElements(Locator, ElementType.Xpath);
//		System.out.println("Before for " + elements.size() + "  " + HeaderValue);
		if (elements.isEmpty()) {
			try {
				Thread.sleep(3000);
//				System.out.println("thread wait");
			} catch (InterruptedException e) {
			}
			elements = webDB.getListOfElements(Locator, ElementType.Xpath);
		}
		for (int i = 0; i < elements.size(); i++) {
			String text = (String) ((JavascriptExecutor) WebDriverBase.getDriver())
					.executeScript("return arguments[0].textContent.trim();", elements.get(i));
			if (text.equals(null) || text.equals("")) {
				text = elements.get(i).getText();
//				System.out.println("getText: " + text);
			}
			if (text.equals(HeaderValue)) {
				return Locator.replace("/th", "/td").concat("[").concat(String.valueOf(i + 1)).concat("]");
			}
		}
//		System.out.println("Locators " + Locator);
		return Locator;
	}

	/**
	 * This Method Is Used To Remove Filter Stock
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void removeFilterStock() {
		List<WebElement> removeFilter = WebDriverBase.getDriver()
				.findElements(By.xpath(StocksPageLocators.RemoveFilter));
		for (WebElement element : removeFilter) {
			if (element.isDisplayed()) {
				checkRefreshCellsDisappear();
				element.click();
			}
		}
		checkRefreshCellsDisappear();
		flag = webDB.isElementNotDisplayed(StocksPageLocators.RemoveFilter, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Verified Filter Removed Successfully");
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Click Fully selected
	 *
	 * @author Sakthi-GWL
	 */
	public void expandAllInLineDetails() {
		checkRefreshCellsDisappear();
		if (!webDB.verifyIsElementDisplayed(CommonUiLocators.CollapseAllLinesBtn, ElementType.Xpath)) {
			flag = webDB.waitForElement(CommonUiLocators.ExpandAllLinesBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CommonUiLocators.ExpandAllLinesBtn, ElementType.Xpath);
			}
		}
		flag = webDB.waitForElement(CommonUiLocators.CollapseAllLinesBtn, ElementType.Xpath);
	}

	/**
	 * This Methods Is Used To Click Fully selected
	 *
	 * @author PremKumar.K-RMITS
	 */
	public void expandFindTable() {
		webDB.waitForElement(CommonUiLocators.ShowMoreIcon, ElementType.Xpath);
		List<WebElement> elements = webDB.getListOfElements(CommonUiLocators.ShowMoreIcon, ElementType.Xpath);
		for (WebElement element : elements) {
			try {
				if (element.getDomAttribute("aria-expanded").equals("false") && element.isDisplayed()) {
					Actions action = new Actions(WebDriverBase.getDriver());
					action.moveToElement(element).build().perform();
					element.click();
				}
			} catch (Exception e) {
				if (element.getDomAttribute("aria-expanded").equals("false") && element.isDisplayed()) {
					JavascriptExecutor js = (JavascriptExecutor) WebDriverBase.getDriver();
					js.executeScript("arguments[0].click();", element);
				}
			}
		}
	}

	/**
	 * This Methods Is Used To Click Show More
	 *
	 * @author Sakthi-GWL
	 */
	public void clickShowMoreFindTable() {
		checkRefreshCellsDisappear();
		if (webDB.verifyIsElementDisplayed(CommonUiLocators.FindShowMore, ElementType.Xpath)) {
			webDB.clickAnElementWithoutException(CommonUiLocators.FindShowMore, ElementType.Xpath);
		}
	}

	/**
	 * This method used to unselect all through show my record in pending screens
	 * 
	 * @author Sakthi-GWL
	 * @param ShowBtn
	 * @param HideBtn
	 * @param SelectAllBtn
	 */
	public void checkShowMyRecordAndUnselectAll(String ShowBtn, String HideBtn, String SelectAllBtn) {
		flag = webDB.waitForElement(ShowBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(ShowBtn, ElementType.Xpath);
			webDB.clickAnElement(ShowBtn, ElementType.Xpath);
			if (webDB.waitForElement(HideBtn, ElementType.Xpath)) {
				logs.logging("INFO", "Show all record button selected");
			}
			checkRefreshCellsDisappear();
			checkRefreshCellsDisappear();
			flag = webDB.verifyIsElementDisplayed(CompletionProductionOrderLocators.Pending_TableNoDataSelected,
					ElementType.Xpath);
			if (flag)
				logs.logging("INFO", "No Pending document already selected");
			if (!flag) {
				webDB.waitForElementUntilGivenTime(SelectAllBtn, ElementType.Xpath, 5);
				if (webDB.isElementSelected(SelectAllBtn, ElementType.Xpath)) {
					logs.logging("INFO", "Already Pending document selected");
					webDB.moveToElement(SelectAllBtn, ElementType.Xpath);
					webDB.clickAnElement(SelectAllBtn, ElementType.Xpath);
					checkRefreshCellsDisappear();
					checkRefreshCellsDisappear();
					flag = webDB.verifyIsElementDisplayed(CompletionProductionOrderLocators.Pending_TableNoDataSelected,
							ElementType.Xpath);
					if (flag)
						logs.logging("INFO", "Uncheck all clicked");
				}
			}
			if (flag) {
				webDB.moveToElement(HideBtn, ElementType.Xpath);
				webDB.clickAnElement(HideBtn, ElementType.Xpath);
				flag = webDB.waitForElement(ShowBtn, ElementType.Xpath);
				if (flag)
					logs.logging("INFO", "Show all record button unselected");
			}
		}
	}

}
