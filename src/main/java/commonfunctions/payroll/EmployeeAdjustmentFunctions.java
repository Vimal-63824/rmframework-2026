package commonfunctions.payroll;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks May 13, 2025
 */
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.payroll.EmployeeAdjustmentLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class EmployeeAdjustmentFunctions {
	private boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private CommonFunctions commonFunc = new CommonFunctions();
	public ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	private static EmployeeMasterFunctions empMasterFunc = new EmployeeMasterFunctions();

	/**
	 * This Method used To Check Adjustment based on different elements To Check
	 * Adjustment based on End Option To Check Adjustment based on Delete Option To
	 * Check Adjustment based on Hold OptionTo Check Employee Adjustment report
	 * fetches values correctly
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyEmpAdjustmentBasedOnAllAndReportFetchValue() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM");
		String Month = date.format(formatter).toUpperCase();
		String TotalPeriod = RandomStringUtils.randomNumeric(1);
		if (TotalPeriod.equalsIgnoreCase("0")) {
			TotalPeriod = TotalPeriod.replace("0", "1");
		}
		try {
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
					"HrRqtUsername");
			commonFunc.navigateToEmployeeAdjustmentTab();
			searchAndClickEmpIdUsingEmpNameLovBtn(commonFunc.getDataFromTestDataSheet("Emp_GWP0001"));
			clickAddIconInAdjustmentTable();
			fillElementStartYearAndPeriodInAdjustment(commonFunc.getDataFromTestDataSheet("BonusText"),
					commonFunc.getDataFromTestDataSheet("Year202627"), Month);
			fillTotalPeriodAndPeriodicAmountInAdjustment(TotalPeriod, RandomStringUtils.randomNumeric(4));
			clickSaveIconInAdjustmentTable();
			clickCancelIconForLastAddedAdjustment();
			clickAddIconInAdjustmentTable();
			fillElementStartYearAndPeriodInAdjustment(commonFunc.getDataFromTestDataSheet("BonusText"),
					commonFunc.getDataFromTestDataSheet("Year202627"), Month);
			fillTotalPeriodAndPeriodicAmountInAdjustment(TotalPeriod, RandomStringUtils.randomNumeric(4));
			clickSaveIconInAdjustmentTable();
			clickApproveIconForLastAddedAdjustment();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"),
						VariableHandler.getEmpAdjustmentVouNo(), webDB.getDataFromProperties("ROAUAdminEmpId"), null,
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToEmployeeAdjustmentTab();
			searchAndClickEmpIdUsingEmpNameLovBtn(commonFunc.getDataFromTestDataSheet("Emp_GWP0001"));
			checkTableStatusForLastAddedAdjustment(commonFunc.getDataFromTestDataSheet("PostedStatus"));
			changeProcessTypeForLastAddedAdjustment(commonFunc.getDataFromTestDataSheet("HoldStatus"));
			changeProcessTypeForLastAddedAdjustment(commonFunc.getDataFromTestDataSheet("ProcessStatus"));
			clickEndAndEnterNarrationForLastAddedAdjustment(commonFunc.getDataFromTestDataSheet("RefText_Test"));
			checkTableStatusForLastAddedAdjustment(commonFunc.getDataFromTestDataSheet("EndedStatus"));
			clickReportAndVerifyDisplayedInEmployeeAdjustment();
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
					"HariUsername");
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyPayrollProcessFromLoadUnitToProcessPayroll': " + e);
			return false;
		}
	}

	/**
	 * This Method used To To Check Employee Adjustment Find page with all line wise
	 * record
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyEmployeeAdjustmentFindWithLineWiseRecord() {
		try {
			commonFunc.navigateToEmployeeAdjustmentTab();
			findWithElementInAdjustment(commonFunc.getDataFromTestDataSheet("BonusText"));
			webDB.navigateToRefresh();
			findWithYearInAdjustment(commonFunc.getDataFromTestDataSheet("Year202526"));
			webDB.navigateToRefresh();
			findWithDesignationInAdjustment(commonFunc.getDataFromTestDataSheet("ChairmanText"));
			webDB.navigateToRefresh();
			findWithDepartmentInAdjustment(commonFunc.getDataFromTestDataSheet("QualityText"));
			webDB.navigateToRefresh();
			findWithUnitInAdjustment(commonFunc.getDataFromTestDataSheet("Unit1"));
			webDB.navigateToRefresh();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyLeaveAdjustmentFindWithLineWiseRecord': " + e);
			return false;
		}
	}

	/**
	 * This Method used to search Emp Id and click t navigate Adjustment screen
	 * 
	 * @author Sakthi-GWL
	 * @param EmpId
	 */
	private void searchAndClickEmpIdUsingEmpNameLovBtn(String EmpId) {
		commonFunc.searchAndSelectValueInFilters(EmployeeAdjustmentLocators.FindEmployeeNameLovBtn, EmpId);
		logs.logging("INFO", "Employee Name Selected: " + EmpId);
		flag = webDB.waitForElement(EmployeeAdjustmentLocators.FindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeAdjustmentLocators.FindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(EmployeeAdjustmentLocators.TableResultFirstEmpId, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Icon Clicked in Employee Adjustment find page");
				String DisplayEmpId = webDB.getTextFromElement(EmployeeAdjustmentLocators.TableResultFirstEmpId,
						ElementType.Xpath);
				flag = DisplayEmpId.equalsIgnoreCase(EmpId);
				if (flag) {
					logs.logging("INFO", "Verified Employee Id Displayed");
					webDB.clickAnElement(EmployeeAdjustmentLocators.TableResultFirstEmpId, ElementType.Xpath);
					flag = webDB.waitForElement(EmployeeAdjustmentLocators.AdjustmentDisplayEmpId, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Employee Id Clicked and Navigated to Adjustment Screen");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click search button in Employee Adjustment
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickSearchButtonInAdjustment() {
		flag = webDB.waitForElement(EmployeeAdjustmentLocators.FindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeAdjustmentLocators.FindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(EmployeeAdjustmentLocators.TableResultFirstEmpId, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Icon Clicked in Employee Adjustment");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Add Icon in Adjustment Table
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickAddIconInAdjustmentTable() {
		flag = webDB.waitForElement(EmployeeAdjustmentLocators.AdjustmentAddIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeAdjustmentLocators.AdjustmentAddIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeAdjustmentLocators.AdjustmentAddIcon, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeAdjustmentLocators.AdjustmentTableElementLovBtn(), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Adjustment table Add Icon Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to fill Element Start Year and Start Period in Add New
	 * Adjustment
	 * 
	 * @author Sakthi-GWL
	 * @param Element
	 * @param Year
	 * @param Period
	 */
	private void fillElementStartYearAndPeriodInAdjustment(String Element, String Year, String Period) {
		commonFunc.searchAndSelectValueInFilters(EmployeeAdjustmentLocators.AdjustmentTableElementLovBtn(), Element);
		logs.logging("INFO", "Element Selected in Adjustment: " + Element);
		flag = webDB.waitForElement(EmployeeAdjustmentLocators.AdjustmentTableStartYearData(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeAdjustmentLocators.AdjustmentTableStartYearData(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(EmployeeAdjustmentLocators.AdjustmentTableStartYearLovBtn(), Year);
			logs.logging("INFO", "Start Year Selected in Adjustment: " + Year);
			flag = webDB.waitForElement(EmployeeAdjustmentLocators.AdjustmentTableStartPeriodData(), ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(EmployeeAdjustmentLocators.AdjustmentTableStartPeriodData(), ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(EmployeeAdjustmentLocators.AdjustmentTableStartPeriodLovBtn(),
						Period);
				logs.logging("INFO", "Start Period Selected in Adjustment: " + Period);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to fill Element Total Period and Periodic Amount in Add New
	 * Adjustment
	 * 
	 * @author Sakthi-GWL
	 * @param TotalPeriod
	 * @param Amount
	 */
	private void fillTotalPeriodAndPeriodicAmountInAdjustment(String TotalPeriod, String Amount) {
		commonFunc.verifyAddTableSpecs(EmployeeAdjustmentLocators.AdjustmentTableTotalPeriodData(),
				EmployeeAdjustmentLocators.AdjustmentTableTotalPeriodInput(), TotalPeriod);
		logs.logging("INFO", "Total Period Entered in Adjustment: " + TotalPeriod);
		commonFunc.verifyAddTableSpecs(EmployeeAdjustmentLocators.AdjustmentTablePeriodicAmntData(),
				EmployeeAdjustmentLocators.AdjustmentTablePeriodicAmntInput(), Amount);
		logs.logging("INFO", "Periodic Amount Entered in Adjustment: " + Amount);
		webDB.pressEscapeKey();
	}

	/**
	 * This Method used to click cancel Icon for Last added Adjustment
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickCancelIconForLastAddedAdjustment() {
		flag = webDB.waitForElement(EmployeeAdjustmentLocators.AdjustmentTableCancelIcon(), ElementType.Xpath);
		if (flag) {
			webDB.scrollToAnElement(EmployeeAdjustmentLocators.AdjustmentTableApproveIcon(), ElementType.Xpath);
			webDB.moveToElement(EmployeeAdjustmentLocators.AdjustmentTableCancelIcon(), ElementType.Xpath);
			webDB.clickAnElement(EmployeeAdjustmentLocators.AdjustmentTableCancelIcon(), ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeAdjustmentLocators.AdjustmentTableCancelOkBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Cancel Button Moved and Clicked Successfully in Adjustment");
				webDB.clickAnElement(EmployeeAdjustmentLocators.AdjustmentTableCancelOkBtn, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Toast Message Displayed..Adjustment Cancelled Successfully");
					if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Approve Icon for Last added Adjustment
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickApproveIconForLastAddedAdjustment() {
		flag = webDB.waitForElement(EmployeeAdjustmentLocators.AdjustmentTableApproveIcon(), ElementType.Xpath);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			webDB.waitUntilElementToNotBeStale(EmployeeAdjustmentLocators.AdjustmentTableAdjustmentNoData(),
					ElementType.Xpath);
			webDB.scrollToAnElement(EmployeeAdjustmentLocators.AdjustmentTableAdjustmentNoData(), ElementType.Xpath);
			webDB.scrollToAnElement(EmployeeAdjustmentLocators.AdjustmentTableProcessHoldData(), ElementType.Xpath);
			webDB.scrollToAnElement(EmployeeAdjustmentLocators.AdjustmentTableApproveIcon(), ElementType.Xpath);
			webDB.clickAnElement(EmployeeAdjustmentLocators.AdjustmentTableApproveIcon(), ElementType.Xpath);
			flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
			if (flag) {
				logs.logging("INFO", "Approve Icon Clicked Successfully in Adjustment");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to check status for Last added Adjustment
	 * 
	 * @author Sakthi-GWL
	 * @param Status
	 */
	private void checkTableStatusForLastAddedAdjustment(String Status) {
		flag = webDB.waitForElement(EmployeeAdjustmentLocators.AdjustmentTableStatusText(), ElementType.Xpath);
		if (flag) {
			String DisplayedStatus = webDB.getTextFromElement(EmployeeAdjustmentLocators.AdjustmentTableStatusText(),
					ElementType.Xpath);
			logs.logging("INFO", "Displayed Adjustment Status: " + DisplayedStatus);
			logs.logging("INFO", "Expected Adjustment Status: " + Status);
			flag = DisplayedStatus.equalsIgnoreCase(Status);
			if (flag) {
				logs.logging("INFO", "Verified Status Last Added Adjustment: " + Status);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to change process type for Last added Adjustment
	 * 
	 * @author Sakthi-GWL
	 * @param ProcessType
	 */
	private void changeProcessTypeForLastAddedAdjustment(String ProcessType) {
		commonFunc.checkRefreshCellsDisappear();
		webDB.waitUntilElementToNotBeStale(EmployeeAdjustmentLocators.AdjustmentTableProcessHoldData(),
				ElementType.Xpath);
		flag = webDB.waitForElement(EmployeeAdjustmentLocators.AdjustmentTableProcessHoldData(), ElementType.Xpath);
		if (flag) {
			webDB.scrollToAnElement(EmployeeAdjustmentLocators.AdjustmentTablePeriodData(), ElementType.Xpath);
			webDB.moveToElement(EmployeeAdjustmentLocators.AdjustmentTableProcessHoldData(), ElementType.Xpath);
			webDB.doubleClickAnElement(EmployeeAdjustmentLocators.AdjustmentTableProcessHoldData(), ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(EmployeeAdjustmentLocators.AdjustmentTableProcessHoldSelect(),
					ElementType.Xpath);
			if (flag) {
				flag = webDB.selectDropDownByText(EmployeeAdjustmentLocators.AdjustmentTableProcessHoldSelect(),
						ProcessType, ElementType.Xpath);
				if (flag) {
					commonFunc.checkRefreshCellsDisappear();
					webDB.pressTabKey();
					webDB.pressTabKey();
					clickSaveIconInAdjustmentTable();
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(EmployeeAdjustmentLocators.AdjustmentTableProcessHoldData(),
							ElementType.Xpath);
					if (flag) {
						String DisplayedType = webDB.getTextFromElement(
								EmployeeAdjustmentLocators.AdjustmentTableProcessHoldData(), ElementType.Xpath);
						logs.logging("INFO", "Displayed Process Type: " + DisplayedType);
						logs.logging("INFO", "Expected Process Type: " + ProcessType);
						flag = DisplayedType.equalsIgnoreCase(ProcessType);
						if (flag) {
							logs.logging("INFO", "Verified Process Type for Last Added Adjustment: " + ProcessType);
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click End And Enter Narration for Last added Adjustment
	 * 
	 * @author Sakthi-GWL
	 * @param Narration
	 */
	private void clickEndAndEnterNarrationForLastAddedAdjustment(String Narration) {
		flag = webDB.waitForElement(EmployeeAdjustmentLocators.AdjustmentTableEndIcon(), ElementType.Xpath);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			webDB.scrollToAnElement(EmployeeAdjustmentLocators.AdjustmentTablePeriodData(), ElementType.Xpath);
			webDB.scrollToAnElement(EmployeeAdjustmentLocators.AdjustmentTableProcessHoldData(), ElementType.Xpath);
			webDB.scrollToAnElement(EmployeeAdjustmentLocators.AdjustmentTableEndIcon(), ElementType.Xpath);
			webDB.clickAnElement(EmployeeAdjustmentLocators.AdjustmentTableEndIcon(), ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeAdjustmentLocators.EndNarrationIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "End Icon Clicked Successfully in Adjustment");
				webDB.switchToFrame(EmployeeAdjustmentLocators.EndNarrationIframe, ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeAdjustmentLocators.EndNarrationInputBox, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(EmployeeAdjustmentLocators.EndNarrationInputBox, Narration,
							ElementType.Xpath);
					logs.logging("INFO", "Narration Entered in End Narration: " + Narration);
					flag = webDB.waitForElement(EmployeeAdjustmentLocators.EndNarrationTickIcon, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(EmployeeAdjustmentLocators.EndNarrationTickIcon, ElementType.Xpath);
						webDB.switchToDefaultContent();
						flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
						if (flag) {
							if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
								webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
										ElementType.Xpath);
							}
							logs.logging("INFO", "Toast Message Displayed.. Adjustment Ended");
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click save Icon for Last added Adjustment
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickSaveIconInAdjustmentTable() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForClickElement(EmployeeAdjustmentLocators.AdjustmentSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeAdjustmentLocators.AdjustmentSaveIcon, ElementType.Xpath);
			webDB.javaScriptClickAnElement(EmployeeAdjustmentLocators.AdjustmentSaveIcon, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 8);
			if (flag) {
				logs.logging("INFO", "Adjustment table Save Icon Clicked Successfully");
				commonFunc.checkSuccessToastMessageDisappear();
				flag = webDB.waitForElement(EmployeeAdjustmentLocators.AdjustmentTableApproveIcon(), ElementType.Xpath);
				if (flag) {
					webDB.scrollToAnElement(EmployeeAdjustmentLocators.AdjustmentTableApproveIcon(), ElementType.Xpath);
					webDB.waitUntilElementToNotBeStale(EmployeeAdjustmentLocators.AdjustmentTableAdjustmentNoData(),
							ElementType.Xpath);
					webDB.moveToElement(EmployeeAdjustmentLocators.AdjustmentTableAdjustmentNoData(),
							ElementType.Xpath);
					String voucherNo = webDB.getTextFromElement(
							EmployeeAdjustmentLocators.AdjustmentTableAdjustmentNoData(), ElementType.Xpath);
					logs.logging("INFO", "Displayed Adjustment voucher number: " + voucherNo);
					VariableHandler.setEmpAdjustmentVouNo(voucherNo);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click report button in Employee Adjustment
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickReportAndVerifyDisplayedInEmployeeAdjustment() {
		flag = webDB.waitForElement(EmployeeAdjustmentLocators.AdjustmentReportBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeAdjustmentLocators.AdjustmentReportBtn, ElementType.Xpath);
			webDB.clickAnElement(EmployeeAdjustmentLocators.AdjustmentReportBtn, ElementType.Xpath);
			commonFunc.hardWait(5000);
			ArrayList<String> tabs = new ArrayList<String>(WebDriverBase.getDriver().getWindowHandles());
			flag = tabs.size() > 1;
			logs.loggingconsole("INFO",
					"After Clicking Report Icon in Employee Adjustment.. tabs size: " + tabs.size());
			if (flag) {
				logs.logging("INFO", "Employee Adjustment Report Icon Clicked... New tab Opened");
				webDB.switchToTabByIndex(1);
				commonFunc.hardWait(2000);
				String Title = WebDriverBase.getDriver().getTitle();
				logs.logging("INFO", "Switched to Report Page... Displayed Title: " + Title);
				if (flag) {
					webDB.closeExistingTab();
					webDB.switchToMainTab();
					flag = webDB.waitForElement(EmployeeAdjustmentLocators.AdjustmentReportBtn, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Existing tab closed and Switch to main window");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to find Employee Adjustment using Unit
	 * 
	 * @author Sakthi-GWL
	 * @param Unit
	 */
	private void findWithUnitInAdjustment(String Unit) {
		commonFunc.clearFiltersInFindPage();
		commonFunc.searchAndSelectValueInFilters(EmployeeAdjustmentLocators.FindUnitLovBtn, Unit);
		clickSearchButtonInAdjustment();
		commonFunc.checkRefreshCellsDisappear();
		List<WebElement> elements = webDB.getListOfElements(EmployeeAdjustmentLocators.FindUnitResultTable(),
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().contains(Unit)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Unit in Employee Adjustment: " + Unit);
	}

	/**
	 * This method used to find Employee Adjustment using Designation
	 * 
	 * @author Sakthi-GWL
	 * @param Designation
	 */
	private void findWithDesignationInAdjustment(String Designation) {
		commonFunc.clearFiltersInFindPage();
		commonFunc.searchAndSelectValueInFilters(EmployeeAdjustmentLocators.FindDesignationLovBtn, Designation);
		clickSearchButtonInAdjustment();
		commonFunc.checkRefreshCellsDisappear();
		List<WebElement> elements = webDB.getListOfElements(EmployeeAdjustmentLocators.FindDesignationResultTable(),
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().contains(Designation)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Designation in Employee Adjustment: " + Designation);
	}

	/**
	 * This method used to find Employee Adjustment using Year
	 * 
	 * @author Sakthi-GWL
	 * @param Year
	 */
	private void findWithYearInAdjustment(String Year) {
		commonFunc.clearFiltersInFindPage();
		commonFunc.searchAndSelectValueInFilters(EmployeeAdjustmentLocators.FindYearLovBtn, Year);
		clickSearchButtonInAdjustment();
		commonFunc.checkRefreshCellsDisappear();
		List<WebElement> elements = webDB.getListOfElements(EmployeeAdjustmentLocators.FindYearResultTable(),
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().contains(Year)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Year in Employee Adjustment: " + Year);
	}

	/**
	 * This method used to find Employee Adjustment using Department
	 * 
	 * @author Sakthi-GWL
	 * @param Department
	 */
	private void findWithDepartmentInAdjustment(String Department) {
		commonFunc.clearFiltersInFindPage();
		commonFunc.searchAndSelectValueInFilters(EmployeeAdjustmentLocators.FindDepartmentLovBtn, Department);
		clickSearchButtonInAdjustment();
		commonFunc.checkRefreshCellsDisappear();
		List<WebElement> elements = webDB.getListOfElements(EmployeeAdjustmentLocators.FindDepartmentResultTable(),
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().contains(Department)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Department in Employee Adjustment: " + Department);
	}

	/**
	 * This method used to find Employee Adjustment using Element
	 * 
	 * @author Sakthi-GWL
	 * @param Element
	 */
	private void findWithElementInAdjustment(String Element) {
		commonFunc.clearFiltersInFindPage();
		commonFunc.searchAndSelectValueInFilters(EmployeeAdjustmentLocators.FindElementLovBtn, Element);
		clickSearchButtonInAdjustment();
		commonFunc.checkRefreshCellsDisappear();
		List<WebElement> elements = webDB.getListOfElements(EmployeeAdjustmentLocators.FindElementResultTable(),
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().contains(Element)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Element in Employee Adjustment: " + Element);
	}

	/**
	 * 
	 * This method used To Check Employee Adjustment Migration With Templates
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyEmployeeAdjustmentMigrationWithTemplates() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MMM-dd");
		String Today = date.format(formatter);
		try {
			commonFunc.navigateToEmployeeAdjustmentTab();
			clickMigrationAndSelectEmpAdjustment();
			clickAddNewInEmployeeAdjustmentMigration();
			fillHeaderDetailsInEmployeeAdjustmentMigration(commonFunc.getDataFromTestDataSheet("RefText_Test"),
					commonFunc.getDataFromTestDataSheet("AdditionText"));
			uploadTemplateInEmployeeAdjustmentMigration(Today);
			checkExceptionAndSaveHeaderInEmployeeAdjustmentMigration();
			clickPostEmployeeAdjustmentButton();
			webDB.navigateToRefresh();
			commonFunc.navigateToEmployeeAdjustmentTab();
			searchAndClickEmpIdUsingEmpNameLovBtn(commonFunc.getDataFromTestDataSheet("Emp_GWP0001"));
			clickCancelIconForLastAddedAdjustment();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyEmployeeAdjustmentMigrationWithTemplates': " + e);
			return false;
		}
	}

	/**
	 * 
	 * This method used To Check Employee Allowance Migration With Templates
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyEmployeeAllowanceMigrationWithTemplates() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);
		try {
			commonFunc.navigateEmployeeMasterScreenInPayroll();
			empMasterFunc.clickAddIconAddEmployee();
			empMasterFunc.clickEmployeeMasterSideNavBar();
			empMasterFunc.selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
					commonFunc.getDataFromTestDataSheet("Emp_FName"), commonFunc.getDataFromTestDataSheet("Emp_LName"));
			empMasterFunc.selectGenderEnterDOBAndDOJ(commonFunc.getDataFromTestDataSheet("Emp_MaleGender"),
					commonFunc.getDataFromTestDataSheet("Emp_DOB"), commonFunc.getDataFromTestDataSheet("Emp_DOJ"));
			empMasterFunc.selectShiftEnterCalerdarAndZone(commonFunc.getDataFromTestDataSheet("Emp_GeneralShift"),
					commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"),
					commonFunc.getDataFromTestDataSheet("Emp_GeneralZone"));
			empMasterFunc.selectCompanyAndAccountCatecory(
					commonFunc.getDataFromTestDataSheet("Emp_SalesCompanyCategory"),
					commonFunc.getDataFromTestDataSheet("Emp_SalaryAcc-AccountCategory"));
			empMasterFunc.selectPayrollGroupAndEnterNoticePeriod(
					commonFunc.getDataFromTestDataSheet("Emp_CleanerPayrollGroup"),
					commonFunc.getDataFromTestDataSheet("Emp_NoticePeriod-30"));
			empMasterFunc.clickSaveIconAndStoreEmpID();
			empMasterFunc.clickSalarySideNavBar();
			empMasterFunc.addDesignationDepartmentAndGradeInSalaryDetails(
					commonFunc.getDataFromTestDataSheet("Emp_SalaryDesignation"),
					commonFunc.getDataFromTestDataSheet("Emp_SalaryDepartment"),
					commonFunc.getDataFromTestDataSheet("Emp_SalaryGrade"));
			empMasterFunc.clickAfterEmpCreationSaveIcon();
			empMasterFunc.clickLoadAllowanceButtonInSalaryDetails();
			empMasterFunc.fillAllowanceAmountsAndVerifyTotalInSalaryDetails(
					new String[] { RandomStringUtils.randomNumeric(5), RandomStringUtils.randomNumeric(4),
							RandomStringUtils.randomNumeric(4), RandomStringUtils.randomNumeric(4) });
			empMasterFunc.enablePFAdjustmentFlagsInSalaryDetails();
			empMasterFunc.enableESIAdjustmentFlagsInSalaryDetails();
			empMasterFunc.clickPostButtonAndVerifyEmployeePosted();
			commonFunc.navigateToEmployeeAdjustmentTab();
			clickMigrationAndSelectEmpAllowance();
			clickAddNewInEmployeeAllowanceMigration();
			fillHeaderDetailsInEmployeeAllowanceMigration(Today, Today,
					commonFunc.getDataFromTestDataSheet("RefText_Test"));
			uploadTemplateInEmployeeAllowanceMigration();
			checkExceptionAndSaveHeaderInEmployeeAllowanceMigration();
			clickPostEmployeeAllowanceButton();
			commonFunc.navigateEmployeeMasterScreenInPayroll();
			empMasterFunc.searchAndClickEmpIdInFindEmployeePage(VariableHandler.getEmpID());
			empMasterFunc.verifyDeleteSPLAllowanceInSalaryDetails();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyEmployeeAllowanceMigrationWithTemplates': " + e);
			return false;
		}
	}

	/**
	 * This method used to click migration and select Employee Adjustment type
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickMigrationAndSelectEmpAdjustment() {
		flag = webDB.waitForElement(EmployeeAdjustmentLocators.EA_MigrationBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeAdjustmentLocators.EA_MigrationBtn, ElementType.Xpath);
			webDB.clickAnElement(EmployeeAdjustmentLocators.EA_MigrationBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(EmployeeAdjustmentLocators.EmpAdjMigrationIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Migration Button Clicked Successfully");
				webDB.switchToFrame(EmployeeAdjustmentLocators.EmpAdjMigrationIframe, ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeAdjustmentLocators.EmpAdjustmentMigrationRadioBtn,
						ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(EmployeeAdjustmentLocators.EmpAdjustmentMigrationRadioBtn, ElementType.Xpath);
					webDB.clickAnElement(EmployeeAdjustmentLocators.EmpAdjustmentMigrationRadioBtn, ElementType.Xpath);
					webDB.switchToDefaultContent();
					flag = webDB.waitForElement(EmployeeAdjustmentLocators.EmpAdjMigrationAddNewBtn, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Migration - Employee Adjustment Clicked Successfully");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click add new button in Employee Adjustment migration
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickAddNewInEmployeeAdjustmentMigration() {
		flag = webDB.waitForClickElement(EmployeeAdjustmentLocators.EmpAdjMigrationAddNewBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeAdjustmentLocators.EmpAdjMigrationAddNewBtn, ElementType.Xpath);
			webDB.clickAnElement(EmployeeAdjustmentLocators.EmpAdjMigrationAddNewBtn, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeAdjustmentLocators.EmpAdjMigrationRefInput, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Migration - Employee Adjustment Add Button is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to fill header details in Employee Adjustment Migration
	 * 
	 * @author Sakthi-GWL
	 * @param Ref
	 * @param Mode
	 */
	private void fillHeaderDetailsInEmployeeAdjustmentMigration(String Ref, String Mode) {
		commonFunc.verifyAddTableSpecs(EmployeeAdjustmentLocators.EmpAdjMigrationRefInput,
				EmployeeAdjustmentLocators.EmpAdjMigrationRefInput, Ref);
		logs.logging("INFO", "Reference Entered in Employee Adjustment: " + Ref);
		flag = webDB.waitForElement(EmployeeAdjustmentLocators.EmpAdjMigrationModeSelect, ElementType.Xpath);
		if (flag) {
			flag = webDB.selectDropDownByText(EmployeeAdjustmentLocators.EmpAdjMigrationModeSelect, Mode,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Mode Selected in Employee Adjustment: " + Mode);
				webDB.moveToElement(EmployeeAdjustmentLocators.EmpAdjMigrationCreateSaveIcon, ElementType.Xpath);
				webDB.clickAnElement(EmployeeAdjustmentLocators.EmpAdjMigrationCreateSaveIcon, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Header Details Filled and Saved in Employee Adjustment");
					if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to upload template in Employee Adjustment migration
	 * 
	 * @author Sakthi-GWL
	 */
	private void uploadTemplateInEmployeeAdjustmentMigration(String date) {
		flag = webDB.waitForElement(EmployeeAdjustmentLocators.EmpAdjMigrationUploadIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeAdjustmentLocators.EmpAdjMigrationUploadIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeAdjustmentLocators.EmpAdjMigrationUploadIcon, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeAdjustmentLocators.EmpAdjMigrationUploadInput, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Upload Icon Clicked and Input Box Displayed");
				String file1 = webDB.getDataFromProperties("EmployeeAdjustmentMigrationFileName");
				String Path = VariableHandler.testdataDir + file1;
//				ExcelUtils excel = new ExcelUtils();
//				excel.writeDataInExcel(Path, "Sheet1", 1, 1, date);
				File file = new File(Path);
				flag = webDB.sendTextToAnElement(EmployeeAdjustmentLocators.EmpAdjMigrationUploadInput,
						file.getAbsolutePath(), ElementType.Xpath);
				if (flag) {
					flag = webDB.waitForClickElement(EmployeeAdjustmentLocators.EmpAdjMigrationUploadBtn,
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(EmployeeAdjustmentLocators.EmpAdjMigrationUploadBtn, ElementType.Xpath);
						commonFunc.hardWait(2000);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.isElementDisplayed(EmployeeAdjustmentLocators.EmpAdjMigrationCheckExcptionBtn,
								ElementType.Xpath)
								&& webDB.isElementNotDisplayed(EmployeeAdjustmentLocators.EmpAdjMigrationUploadBtn,
										ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "File Uploaded Succesfully for Employee Adjustment Migration");
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to check Exception and Save Header in Employee Adjustment
	 * 
	 * @author Sakthi-GWL
	 */
	private void checkExceptionAndSaveHeaderInEmployeeAdjustmentMigration() {
		flag = webDB.waitForElement(EmployeeAdjustmentLocators.EmpAdjMigrationCheckExcptionBtn, ElementType.Xpath);
		if (flag) {
			commonFunc.checkSuccessToastMessageDisappear();
			webDB.moveToElement(EmployeeAdjustmentLocators.EmpAdjMigrationCheckExcptionBtn, ElementType.Xpath);
			webDB.clickAnElement(EmployeeAdjustmentLocators.EmpAdjMigrationCheckExcptionBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Check Exception Button Clicked");
				String toastText = webDB.getTextFromElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				flag = !toastText.contains("Exception exists");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				logs.logging("INFO", "Displayed Toast Text for Check Exception: " + toastText);
				if (flag) {
					logs.logging("INFO", "No Exception Found for Employee Adjustment Migration");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click post button in Employee Adjustment migration
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickPostEmployeeAdjustmentButton() {
		flag = webDB.waitForElement(EmployeeAdjustmentLocators.EmpAdjMigrationPostBtn, ElementType.Xpath);
		if (flag) {
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			webDB.moveToElement(EmployeeAdjustmentLocators.EmpAdjMigrationPostBtn, ElementType.Xpath);
			webDB.clickAnElement(EmployeeAdjustmentLocators.EmpAdjMigrationPostBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Migration - Employee Adjustment Posted Successfully");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click migration and select Employee Allowance type
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickMigrationAndSelectEmpAllowance() {
		flag = webDB.waitForElement(EmployeeAdjustmentLocators.EA_MigrationBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeAdjustmentLocators.EA_MigrationBtn, ElementType.Xpath);
			webDB.clickAnElement(EmployeeAdjustmentLocators.EA_MigrationBtn, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeAdjustmentLocators.EmpAdjMigrationIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Migration Button Clicked Successfully");
				webDB.switchToFrame(EmployeeAdjustmentLocators.EmpAdjMigrationIframe, ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeAdjustmentLocators.EmpAllowanceMigrationRadioBtn,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(EmployeeAdjustmentLocators.EmpAllowanceMigrationRadioBtn, ElementType.Xpath);
					webDB.switchToDefaultContent();
					flag = webDB.waitForElement(EmployeeAdjustmentLocators.EmpAllowanceMigrationAddNewBtn,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Migration - Employee Allowance Clicked Successfully");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click add new button in Employee Allowance migration
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickAddNewInEmployeeAllowanceMigration() {
		flag = webDB.waitForClickElement(EmployeeAdjustmentLocators.EmpAllowanceMigrationAddNewBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeAdjustmentLocators.EmpAllowanceMigrationAddNewBtn, ElementType.Xpath);
			webDB.clickAnElement(EmployeeAdjustmentLocators.EmpAllowanceMigrationAddNewBtn, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeAdjustmentLocators.EmpAllowanceMigrationRefInput, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Migration - Employee Allowance Add Button is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to fill header details in Employee Allowance Migration
	 * 
	 * @author Sakthi-GWL
	 * @param DateFrom
	 * @param DateTo
	 * @param Ref
	 */
	private void fillHeaderDetailsInEmployeeAllowanceMigration(String DateFrom, String DateTo, String Ref) {
		commonFunc.verifyAddTableSpecs(EmployeeAdjustmentLocators.EmpAllowanceMigrationDateFrom,
				EmployeeAdjustmentLocators.EmpAllowanceMigrationDateFrom, DateFrom);
		logs.logging("INFO", "Date From Entered in Employee Allowance: " + DateFrom);
		commonFunc.verifyAddTableSpecs(EmployeeAdjustmentLocators.EmpAllowanceMigrationDateTo,
				EmployeeAdjustmentLocators.EmpAllowanceMigrationDateTo, DateTo);
		logs.logging("INFO", "Date To Entered in Employee Allowance: " + DateTo);
		commonFunc.verifyAddTableSpecs(EmployeeAdjustmentLocators.EmpAllowanceMigrationRefInput,
				EmployeeAdjustmentLocators.EmpAllowanceMigrationRefInput, Ref);
		logs.logging("INFO", "Reference Entered in Employee Allowance: " + Ref);
		flag = webDB.waitForElement(EmployeeAdjustmentLocators.EmpAllowanceMigrationCreateSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeAdjustmentLocators.EmpAllowanceMigrationCreateSaveIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeAdjustmentLocators.EmpAllowanceMigrationCreateSaveIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Header Details Filled and Saved in Employee Allowance");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to upload template in Employee Allowance migration
	 * 
	 * @author Sakthi-GWL
	 */
	private void uploadTemplateInEmployeeAllowanceMigration() {
		flag = webDB.waitForElement(EmployeeAdjustmentLocators.EmpAllowanceMigrationUploadIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeAdjustmentLocators.EmpAllowanceMigrationUploadIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeAdjustmentLocators.EmpAllowanceMigrationUploadIcon, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeAdjustmentLocators.EmpAllowanceMigrationUploadInput, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Upload Icon Clicked and Input Box Displayed");
				String file1 = webDB.getDataFromProperties("EmployeeAllowanceMigrationFileName");
				String Path = VariableHandler.testdataDir + file1;
				ExcelUtils excel = new ExcelUtils();
				excel.writeDataInExcel(Path, "Sheet1", 1, 0, VariableHandler.getEmpID());
				logs.logging("INFO",
						"Employee Id Edited in file for " + file1 + " with value: " + VariableHandler.getEmpID());
				File file = new File(Path);
				flag = webDB.sendTextToAnElement(EmployeeAdjustmentLocators.EmpAllowanceMigrationUploadInput,
						file.getAbsolutePath(), ElementType.Xpath);
				if (flag) {
					flag = webDB.waitForClickElement(EmployeeAdjustmentLocators.EmpAllowanceMigrationUploadBtn,
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(EmployeeAdjustmentLocators.EmpAllowanceMigrationUploadBtn,
								ElementType.Xpath);
						commonFunc.hardWait(2000);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.isElementDisplayed(
								EmployeeAdjustmentLocators.EmpAllowanceMigrationCheckExcptionBtn, ElementType.Xpath)
								&& webDB.isElementNotDisplayed(
										EmployeeAdjustmentLocators.EmpAllowanceMigrationUploadBtn, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "File Uploaded Succesfully for Employee Allowance Migration");
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to check Exception and Save Header in Employee Allowance
	 * 
	 * @author Sakthi-GWL
	 */
	private void checkExceptionAndSaveHeaderInEmployeeAllowanceMigration() {
		flag = webDB.waitForElement(EmployeeAdjustmentLocators.EmpAllowanceMigrationCheckExcptionBtn,
				ElementType.Xpath);
		if (flag) {
			commonFunc.checkSuccessToastMessageDisappear();
			webDB.moveToElement(EmployeeAdjustmentLocators.EmpAllowanceMigrationCheckExcptionBtn, ElementType.Xpath);
			webDB.clickAnElement(EmployeeAdjustmentLocators.EmpAllowanceMigrationCheckExcptionBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Check Exception Button Clicked");
				String toastText = webDB.getTextFromElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				flag = !toastText.contains("Exception exists");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				logs.logging("INFO", "Displayed Toast Text for Check Exception: " + toastText);
				if (flag) {
					logs.logging("INFO", "No Exception Found for Employee Allowance Migration");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click post button in Employee Allowance migration
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickPostEmployeeAllowanceButton() {
		flag = webDB.waitForClickElement(EmployeeAdjustmentLocators.EmpAllowanceMigrationPostBtn, ElementType.Xpath);
		if (flag) {
			commonFunc.checkSuccessToastMessageDisappear();
			webDB.moveToElement(EmployeeAdjustmentLocators.EmpAllowanceMigrationPostBtn, ElementType.Xpath);
			webDB.clickAnElement(EmployeeAdjustmentLocators.EmpAllowanceMigrationPostBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Migration - Employee Allowance Posted Successfully");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		Assert.assertTrue(flag);
	}
}
