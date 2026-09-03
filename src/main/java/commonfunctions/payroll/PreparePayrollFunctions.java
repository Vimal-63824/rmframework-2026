package commonfunctions.payroll;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks April 30, 2025
 */
import static org.testng.Assert.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.payroll.PreparePayrollLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class PreparePayrollFunctions {
	private boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private CommonFunctions commonFunc = new CommonFunctions();
	public ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	private static CommonPayrollFunctions commonPayrollFunctions = new CommonPayrollFunctions();
	private static EmployeeMasterFunctions empMasterFunc = new EmployeeMasterFunctions();

	/**
	 * This method used to click Add icon In Prepare Payroll
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAddIconInPreparePayroll() {
		flag = webDB.waitForElement(PreparePayrollLocators.PreparePayrollAddIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PreparePayrollLocators.PreparePayrollAddIcon, ElementType.Xpath);
			flag = webDB.waitForElement(PreparePayrollLocators.CreatePayrollCalendarLovBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Create Icon Clicked in Prepare Payroll Process");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to fill details In creation of Prepare Payroll
	 * 
	 * @author Sakthi-GWL
	 * @param Calendar
	 * @param Year
	 * @param Period
	 * @param Narration
	 */
	public void fillCreationFormInPreparePayroll(String Calendar, String Year, String Period, String Narration) {
		commonFunc.searchAndSelectValueInFilters(PreparePayrollLocators.CreatePayrollCalendarLovBtn, Calendar);
		logs.logging("INFO", "Calendar Selected in Prepare Payroll: " + Calendar);
		commonFunc.searchAndSelectValueInFilters(PreparePayrollLocators.CreatePayrollYearLovBtn, Year);
		logs.logging("INFO", "Year Selected in Prepare Payroll: " + Year);
		commonFunc.searchAndSelectValueInFilters(PreparePayrollLocators.CreatePayrollPeriodLovBtn, Period);
		logs.logging("INFO", "Period Selected in Prepare Payroll: " + Period);
		commonFunc.verifyAddTableSpecs(PreparePayrollLocators.CreatePayrollNarrationInput,
				PreparePayrollLocators.CreatePayrollNarrationInput, Narration);
		logs.logging("INFO", "Narration Entered in Prepare Payroll: " + Narration);
		flag = webDB.waitForElement(PreparePayrollLocators.CreatePayrollTickIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PreparePayrollLocators.CreatePayrollTickIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Tick Icon Clicked in Prepare Payroll Creation");
				commonFunc.checkRefreshCellsDisappear();
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				flag = webDB.waitForElement(PreparePayrollLocators.PayrollProcessDisplayDocNo, ElementType.Xpath);
				if (flag) {
					String DocNo = webDB.getTextFromElement(PreparePayrollLocators.PayrollProcessDisplayDocNo,
							ElementType.Xpath);
					int startBracket = DocNo.indexOf('(');
					int HiphenBracket = DocNo.indexOf('-') + 1;
					DocNo = DocNo.substring(HiphenBracket, startBracket).trim();
//					DocNo = (DocNo.split("No.:")[1]).trim();
					VariableHandler.setPayrollDocNo(DocNo);
					logs.logging("INFO", "Displayed Payroll Document Number: " + DocNo);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to Load Unit and Group In Prepare Payroll
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickLoadUnitGroupInPreparePayroll() {
		flag = webDB.waitForElement(PreparePayrollLocators.LoadUnitGroupBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PreparePayrollLocators.LoadUnitGroupBtn, ElementType.Xpath);
			webDB.clickAnElement(PreparePayrollLocators.LoadUnitGroupBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElementUntilGivenTime(PreparePayrollLocators.UnloadUnitGroupBtn, ElementType.Xpath, 60);
			if (flag) {
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				logs.logging("INFO", "Load Unit Group Clicked in Prepare Payroll Process");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to Unload Unit and Group In Prepare Payroll
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickUnloadUnitGroupInPreparePayroll() {
		commonFunc.checkSuccessToastMessageDisappear();
		flag = webDB.waitForElement(PreparePayrollLocators.UnloadUnitGroupBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PreparePayrollLocators.UnloadUnitGroupBtn, ElementType.Xpath);
			webDB.clickAnElement(PreparePayrollLocators.UnloadUnitGroupBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElementUntilGivenTime(PreparePayrollLocators.LoadUnitGroupBtn, ElementType.Xpath, 60);
			if (flag) {
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				logs.logging("INFO", "Unload Unit Group Clicked in Prepare Payroll Process");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click Unit and Group Label In Prepare Payroll
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickUnitGroupLabelInPreparePayroll() {
		flag = webDB.waitForElement(PreparePayrollLocators.UnitGroupLabel, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PreparePayrollLocators.UnitGroupLabel, ElementType.Xpath);
			webDB.clickAnElement(PreparePayrollLocators.UnitGroupLabel, ElementType.Xpath);
			flag = webDB.waitForElement(PreparePayrollLocators.GroupContentContainer, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Unit Group Label Clicked in Prepare Payroll Process");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click Unit Group Input Check Box In Prepare Payroll
	 * 
	 * @author Sakthi-GWL
	 * @param Name
	 */
	public void unselectUnitAndGroupWithNameInLoadPayroll(String Name) {
		flag = webDB.waitForElement(PreparePayrollLocators.UnitGroupCheckBoxInput(Name), ElementType.Xpath);
		if (flag) {
			if (webDB.isElementSelected(PreparePayrollLocators.UnitGroupCheckBoxInput(Name), ElementType.Xpath)) {
				webDB.moveToElement(PreparePayrollLocators.UnitGroupCheckBoxInput(Name), ElementType.Xpath);
				webDB.clickAnElement(PreparePayrollLocators.UnitGroupCheckBoxInput(Name), ElementType.Xpath);
				commonFunc.hardWait(2000);
			}
			flag = !webDB.isElementSelected(PreparePayrollLocators.UnitGroupCheckBoxInput(Name), ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click Unit Group Input Check Box In Prepare Payroll
	 * 
	 * @author Sakthi-GWL
	 * @param Name
	 */
	public void selectUnitAndGroupWithNameInLoadPayroll(String Name) {
		flag = webDB.waitForElement(PreparePayrollLocators.UnitGroupCheckBoxInput(Name), ElementType.Xpath);
		if (flag) {
			if (!webDB.isElementSelected(PreparePayrollLocators.UnitGroupCheckBoxInput(Name), ElementType.Xpath)) {
				webDB.moveToElement(PreparePayrollLocators.UnitGroupCheckBoxInput(Name), ElementType.Xpath);
				webDB.clickAnElement(PreparePayrollLocators.UnitGroupCheckBoxInput(Name), ElementType.Xpath);
				commonFunc.hardWait(2000);
			}
			flag = webDB.isElementSelected(PreparePayrollLocators.UnitGroupCheckBoxInput(Name), ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This method used to load Employee In Prepare Payroll
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickLoadEmployeeInPreparePayroll() {
		flag = webDB.waitForElement(PreparePayrollLocators.LoadEmployeeBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PreparePayrollLocators.LoadEmployeeBtn, ElementType.Xpath);
			webDB.clickAnElement(PreparePayrollLocators.LoadEmployeeBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElementUntilGivenTime(PreparePayrollLocators.UnloadEmployeeBtn, ElementType.Xpath, 60);
			if (flag) {
				logs.logging("INFO", "Load Employee Clicked in Prepare Payroll Process");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to Unload Employee In Prepare Payroll
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickUnloadEmployeeInPreparePayroll() {
		flag = webDB.waitForElement(PreparePayrollLocators.UnloadEmployeeBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PreparePayrollLocators.UnloadEmployeeBtn, ElementType.Xpath);
			webDB.clickAnElement(PreparePayrollLocators.UnloadEmployeeBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElementUntilGivenTime(PreparePayrollLocators.LoadEmployeeBtn, ElementType.Xpath, 60);
			if (flag) {
				logs.logging("INFO", "Unload Employee Clicked in Prepare Payroll Process");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click Employee Label In Prepare Payroll
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickEmployeeLabelInPreparePayroll() {
		flag = webDB.waitForElement(PreparePayrollLocators.EmployeeLabel, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PreparePayrollLocators.EmployeeLabel, ElementType.Xpath);
			webDB.clickAnElement(PreparePayrollLocators.EmployeeLabel, ElementType.Xpath);
			flag = webDB.waitForElement(PreparePayrollLocators.EmployeeSearchBarInput, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Employee Label Clicked in Prepare Payroll Process");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to unselect all employee and search Empid in search bar In
	 * Prepare Payroll
	 * 
	 * @author Sakthi-GWL
	 * @param EmpId
	 */
	public void unSelectedAllAndSearchAndSelectEmployee(String EmpId) {
		flag = webDB.waitForElement(PreparePayrollLocators.AllEmployeeCheckBoxInput, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PreparePayrollLocators.AllEmployeeCheckBoxInput, ElementType.Xpath);
			webDB.clickAnElement(PreparePayrollLocators.AllEmployeeCheckBoxInput, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(PreparePayrollLocators.EmployeeSearchBarInput, ElementType.Xpath);
			if (flag) {
				webDB.sendTextToAnElement(PreparePayrollLocators.EmployeeSearchBarInput, EmpId, ElementType.Xpath);
				logs.logging("INFO", "Search bar EmpId Sent: " + EmpId);
				flag = webDB.waitForElement(PreparePayrollLocators.EmployeeSearchBarGoBtn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PreparePayrollLocators.EmployeeSearchBarGoBtn, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(PreparePayrollLocators.EmployeeCheckBoxInput(EmpId), ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Employee Searched Successfully: " + EmpId);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click Employee Input Check Box In Prepare Payroll
	 * 
	 * @author Sakthi-GWL
	 * @param Name
	 */
	public void selectEmployeeWithNameInLoadPayroll(String Name) {
		flag = webDB.waitForElement(PreparePayrollLocators.EmployeeCheckBoxInput(Name), ElementType.Xpath);
		if (flag) {
			if (!webDB.isElementSelected(PreparePayrollLocators.EmployeeCheckBoxInput(Name), ElementType.Xpath)) {
				webDB.moveToElement(PreparePayrollLocators.EmployeeCheckBoxInput(Name), ElementType.Xpath);
				webDB.clickAnElement(PreparePayrollLocators.EmployeeCheckBoxInput(Name), ElementType.Xpath);
				commonFunc.hardWait(2000);
			}
			flag = webDB.isElementSelected(PreparePayrollLocators.EmployeeCheckBoxInput(Name), ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click Attendance label In Prepare Payroll
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAttendanceLabelInPreparePayroll() {
		flag = webDB.waitForElement(PreparePayrollLocators.AttendanceLabel, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PreparePayrollLocators.AttendanceLabel, ElementType.Xpath);
			webDB.clickAnElement(PreparePayrollLocators.AttendanceLabel, ElementType.Xpath);
			flag = webDB.waitForElement(PreparePayrollLocators.AttendanceContentContainer, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Attendance Label Clicked in Prepare Payroll Process");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to Update paid and unpaid leaves for Employee In Prepare
	 * Payroll
	 * 
	 * @author Sakthi-GWL
	 * @param EmpName
	 * @param PL
	 * @param UL
	 */
	public void updatePaidAndUnpaidLeaveInAttendancePayroll(String EmpName, String PL, String UL) {
		webDB.waitForElement(PreparePayrollLocators.AttendanceContentContainer, ElementType.Xpath);
		flag = webDB.waitForElement(PreparePayrollLocators.AttendancePaidLeaveData(EmpName), ElementType.Xpath);
		if (flag) {
			commonFunc.verifyAddTableSpecs(PreparePayrollLocators.AttendancePaidLeaveData(EmpName),
					PreparePayrollLocators.AttendancePaidLeaveInput(EmpName), PL);
			logs.logging("INFO", "Paid Leave Updated - '" + PL + "' for EmpName: " + EmpName);
			commonFunc.verifyAddTableSpecs(PreparePayrollLocators.AttendanceUnpaidLeaveData(EmpName),
					PreparePayrollLocators.AttendanceUnpaidLeaveInput(EmpName), UL);
			logs.logging("INFO", "Unpaid Leave Updated - '" + UL + "' for EmpName: " + EmpName);
			flag = webDB.waitForElement(PreparePayrollLocators.AttendanceSaveIcon, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(PreparePayrollLocators.AttendanceSaveIcon, ElementType.Xpath);
				webDB.clickAnElement(PreparePayrollLocators.AttendanceSaveIcon, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				if (flag) {
					if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
					logs.logging("INFO", "Attendance Paid and Unpaid Leave Updated and Save Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to verify attendance data values in table In Prepare Payroll
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyAttendanceDataValuesInPreparePayroll() {
		flag = webDB.waitForElement(PreparePayrollLocators.AttendanceContentContainer, ElementType.Xpath);
		if (flag) {
			List<WebElement> EmpName = webDB.getListOfElements(PreparePayrollLocators.AttendanceEmpNameList(),
					ElementType.Xpath);
			List<WebElement> CalDays = webDB.getListOfElements(PreparePayrollLocators.AttendanceCalDaysList(),
					ElementType.Xpath);
			List<WebElement> WorkDays = webDB.getListOfElements(PreparePayrollLocators.AttendanceWorkDaysList(),
					ElementType.Xpath);
			List<WebElement> Holidays = webDB.getListOfElements(PreparePayrollLocators.AttendanceHolidaysList(),
					ElementType.Xpath);
			List<WebElement> Weekends = webDB.getListOfElements(PreparePayrollLocators.AttendanceWeekendsList(),
					ElementType.Xpath);
			List<WebElement> PaidDays = webDB.getListOfElements(PreparePayrollLocators.AttendancePaidDaysList(),
					ElementType.Xpath);
			int count = EmpName.size();
			if ((count == CalDays.size()) && (count == WorkDays.size()) && (count == Holidays.size())
					&& (count == Weekends.size()) && (count == PaidDays.size())) {
				logs.logging("INFO", "Attendance table count verified: " + count);
				for (int i = 0; i < count; i++) {
					logs.logging("INFO", "Attendance Displayed - Emp Name: " + EmpName.get(i).getText());
					logs.logging("INFO", "Attendance Displayed - Cal Days: " + CalDays.get(i).getText());
					logs.logging("INFO", "Attendance Displayed - Work Days: " + WorkDays.get(i).getText());
					logs.logging("INFO", "Attendance Displayed - Holidays: " + Holidays.get(i).getText());
					logs.logging("INFO", "Attendance Displayed - Weekends: " + Weekends.get(i).getText());
					logs.logging("INFO", "Attendance Displayed - Paid Days: " + PaidDays.get(i).getText());
				}
				flag = true;
			} else {
				logs.logging("INFO", "EmpName Count and other table values count mismatched");
				flag = false;
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to verify OD days OT and Permission Hrs In Prepare Payroll
	 * 
	 * @author Sakthi-GWL
	 * @param EmpName
	 */
	public void verifyOTHrsOnDutyAndPermissionHrsInAttendance(String EmpName) {
		webDB.waitForElement(PreparePayrollLocators.AttendanceContentContainer, ElementType.Xpath);
		flag = webDB.waitForElement(PreparePayrollLocators.AttendancePermHrsLeaveData(EmpName), ElementType.Xpath);
		if (flag) {
			String prms = webDB.getTextFromElement(PreparePayrollLocators.AttendancePermHrsLeaveData(EmpName),
					ElementType.Xpath);
			logs.logging("INFO", "Displayed Permission Hrs - '" + prms + "' for EmpName: " + EmpName);
			flag = webDB.waitForElement(PreparePayrollLocators.AttendanceOTHrsLeaveData(EmpName), ElementType.Xpath);
			if (flag) {
				String Ot = webDB.getTextFromElement(PreparePayrollLocators.AttendanceOTHrsLeaveData(EmpName),
						ElementType.Xpath);
				logs.logging("INFO", "Displayed Over Time Hrs - '" + Ot + "' for EmpName: " + EmpName);
				flag = webDB.waitForElement(PreparePayrollLocators.AttendanceOnDutyLeaveData(EmpName),
						ElementType.Xpath);
				if (flag) {
					String OD = webDB.getTextFromElement(PreparePayrollLocators.AttendanceOnDutyLeaveData(EmpName),
							ElementType.Xpath);
					logs.logging("INFO", "Displayed On Duty Days - '" + OD + "' for EmpName: " + EmpName);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click Prepare Payroll Button In Prepare Payroll
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickPreparePayrollButton() {
		flag = webDB.waitForElement(PreparePayrollLocators.PreparePayrollBtn, ElementType.Xpath);
		if (flag) {
			commonFunc.checkSuccessToastMessageDisappear();
			webDB.moveToElement(PreparePayrollLocators.PreparePayrollBtn, ElementType.Xpath);
			webDB.clickAnElement(PreparePayrollLocators.PreparePayrollBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 60);
			if (flag) {
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				logs.logging("INFO", "Prepare Payroll Button Clicked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click Exception Label And verify No Exception In Prepare
	 * Payroll
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickExceptionLabelAndVerifyNoException() {
		flag = webDB.waitForElement(PreparePayrollLocators.ExceptionLabel, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PreparePayrollLocators.ExceptionLabel, ElementType.Xpath);
			webDB.clickAnElement(PreparePayrollLocators.ExceptionLabel, ElementType.Xpath);
			flag = webDB.waitForElement(PreparePayrollLocators.ExceptionDataView, ElementType.Xpath)
					&& webDB.isElementNotDisplayed(PreparePayrollLocators.ExceptionDataViewTable, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Exception Label Clicked and verify No Exception Occurs");
			} else {
				flag = false;
				logs.logging("INFO", "Exception Label Clicked and Exception Occurs");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click Payroll label In Prepare Payroll
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickPayrollLabelInPreparePayroll() {
		flag = webDB.waitForElement(PreparePayrollLocators.PayrollLabel, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PreparePayrollLocators.PayrollLabel, ElementType.Xpath);
			webDB.clickAnElement(PreparePayrollLocators.PayrollLabel, ElementType.Xpath);
			flag = webDB.waitForElement(PreparePayrollLocators.PayrollContentContainer, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Payroll Label Clicked in Prepare Payroll Process");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click Employee id in Payroll tab
	 * 
	 * @author Sakthi-GWL
	 * @param EmpId
	 */
	public void clickEmpIdInPayrollTabAndVerifyNavigated(String EmpId) {
		webDB.waitForElement(PreparePayrollLocators.PayrollContentContainer, ElementType.Xpath);
		flag = webDB.waitForElement(PreparePayrollLocators.PayrollEmpIdTableData(EmpId), ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PreparePayrollLocators.PayrollEmpIdTableData(EmpId), ElementType.Xpath);
			webDB.clickAnElement(PreparePayrollLocators.PayrollEmpIdTableData(EmpId), ElementType.Xpath);
			flag = webDB.waitForElement(PreparePayrollLocators.PayrollEmpIdDisplay, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Emp Id Clicked in Payroll table and Navigated to Addition Deduction Page");
				String DisplayEmpId = webDB.getAttributeFromElement(PreparePayrollLocators.PayrollEmpIdDisplay,
						ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
				flag = DisplayEmpId.equalsIgnoreCase(EmpId);
				logs.logging("INFO", "Displayed Emp Id: " + DisplayEmpId);
				logs.logging("INFO", "Clicked Emp Id: " + EmpId);
				if (flag) {
					logs.logging("INFO", "Verified Navigated Emp Id Addition Deduction Page");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to verify allowances data values in Addition page
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyAllowanceValuesInEmpIdAdditionPage() {
		flag = webDB.waitForElement(PreparePayrollLocators.PayrollAllowanceTable, ElementType.Xpath);
		if (flag) {
			List<WebElement> Desc = webDB.getListOfElements(PreparePayrollLocators.PayrollAllowanceDescriptionList(),
					ElementType.Xpath);
			List<WebElement> ActualAmnt = webDB
					.getListOfElements(PreparePayrollLocators.PayrollAllowanceActualAmntList(), ElementType.Xpath);
			List<WebElement> Amnt = webDB.getListOfElements(PreparePayrollLocators.PayrollAllowanceAmountList(),
					ElementType.Xpath);
			for (int i = 1; i <= Amnt.size(); i++) {
				if (i <= Desc.size()) {
					logs.logging("INFO", "Displayed Description in Allowances: " + Desc.get(i - 1).getText());
				}
				if (i <= ActualAmnt.size()) {
					logs.logging("INFO", "Displayed Actual Amount in Allowances: " + ActualAmnt.get(i - 1).getText());
				}
				if (i < Amnt.size()) {
					logs.logging("INFO", "Displayed Amount in Allowances: " + Amnt.get(i - 1).getText());
				} else {
					logs.logging("INFO", "Total Amount in Allowances: " + Amnt.get(i - 1).getText());
				}
			}
			logs.logging("INFO", "Allowances Displayed and Verified in Emp id Addition Page");
		}
		assertTrue(flag);
	}

	/**
	 * This method used to verify Adjustment data values in Deduction page
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyAdjustmentValuesInEmpIdAdditionPage() {
		flag = webDB.waitForElement(PreparePayrollLocators.PayrollAdjustmentTable, ElementType.Xpath);
		if (flag) {
			List<WebElement> Desc = webDB.getListOfElements(PreparePayrollLocators.PayrollAdjustmentDescriptionList(),
					ElementType.Xpath);
			List<WebElement> Amnt = webDB.getListOfElements(PreparePayrollLocators.PayrollAdjustmentAmountList(),
					ElementType.Xpath);
			for (int i = 1; i <= Amnt.size(); i++) {
				if (i <= Desc.size()) {
					logs.logging("INFO", "Displayed Description in Adjustment: " + Desc.get(i - 1).getText());
				}
				if (i == Desc.size()) {
					logs.logging("INFO", "Displayed Total Amount in Adjustment: " + Amnt.get(i - 1).getText());
				} else {
					logs.logging("INFO", "Displayed Amount in Adjustment: " + Amnt.get(i - 1).getText());
				}
			}
			logs.logging("INFO", "Adjustment Displayed and Verified in Emp id Addition Page");
		}
		assertTrue(flag);
	}

	/**
	 * This method used to verify Total Addition Deduction and Net Salary in Emp Id
	 * page
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyTotalAdditionDeductionAndNetSalary() {
		flag = webDB.waitForElement(PreparePayrollLocators.PayrollTotalAdditionDisplayText, ElementType.Xpath);
		if (flag) {
			String addText = webDB.getTextFromElement(PreparePayrollLocators.PayrollTotalAdditionDisplayText,
					ElementType.Xpath);
			logs.logging("INFO", "Payroll Total Addition Displayed Text: " + addText);
			flag = webDB.waitForElement(PreparePayrollLocators.PayrollTotalDeductionDisplayText, ElementType.Xpath);
			if (flag) {
				String deducText = webDB.getTextFromElement(PreparePayrollLocators.PayrollTotalDeductionDisplayText,
						ElementType.Xpath);
				logs.logging("INFO", "Payroll Total Deduction Displayed Text: " + deducText);
				flag = webDB.waitForElement(PreparePayrollLocators.PayrollNetSalaryDisplayText, ElementType.Xpath);
				if (flag) {
					String NetSalText = webDB.getTextFromElement(PreparePayrollLocators.PayrollNetSalaryDisplayText,
							ElementType.Xpath);
					logs.logging("INFO", "Payroll Net Salary Displayed Text: " + NetSalText);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click Review Completed Button in Prepare Payroll
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickReviewCompletedInPreparePayroll() {
		flag = webDB.waitForElement(PreparePayrollLocators.ReviewCompletedBtn, ElementType.Xpath);
		if (flag) {
			commonFunc.checkSuccessToastMessageDisappear();
			webDB.moveToElement(PreparePayrollLocators.ReviewCompletedBtn, ElementType.Xpath);
			webDB.clickAnElement(PreparePayrollLocators.ReviewCompletedBtn, ElementType.Xpath);
			flag = webDB.waitForElement(PreparePayrollLocators.ProcessPayrollBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Review Completed Clicked in Prepare Payroll Process");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click Process Payroll Button in Prepare Payroll
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickProcessPayrollButtonInPreparePayroll() {
		flag = webDB.waitForElement(PreparePayrollLocators.ProcessPayrollBtn, ElementType.Xpath);
		if (flag) {
			commonFunc.checkSuccessToastMessageDisappear();
			webDB.moveToElement(PreparePayrollLocators.ProcessPayrollBtn, ElementType.Xpath);
			webDB.clickAnElement(PreparePayrollLocators.ProcessPayrollBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Process Payroll Clicked and Document Posted");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to verify scenarios in Prepare/Process payroll Module HRM
	 * Test Scenarios
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean verifyPayrollProcessFromLoadUnitToProcessPayroll() {
		LocalDate date = LocalDate.now();
		DayOfWeek day = date.getDayOfWeek();
		if (day == DayOfWeek.SATURDAY) {
			date = date.minusDays(1);
		} else if (day == DayOfWeek.SUNDAY) {
			date = date.minusDays(2);
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM");
		String PayrollMonth = date.format(formatter).toUpperCase();
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String JoinedDate = date.format(formatter1);
		try {
			commonFunc.navigateEmployeeMasterScreenInPayroll();
			empMasterFunc.clickAddIconAddEmployee();
			empMasterFunc.clickEmployeeMasterSideNavBar();
			empMasterFunc.selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
					commonFunc.getDataFromTestDataSheet("Emp_FName"),
					(commonFunc.getDataFromTestDataSheet("Emp_LName") + RandomStringUtils.randomAlphanumeric(5)));
			empMasterFunc.selectGenderEnterDOBAndDOJ(commonFunc.getDataFromTestDataSheet("Emp_MaleGender"),
					commonFunc.getDataFromTestDataSheet("Emp_DOB"), JoinedDate);
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
			commonFunc.navigateToPrepareProcessPayrollTab();
			clickAddIconInPreparePayroll();
			fillCreationFormInPreparePayroll(commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"),
					commonFunc.getDataFromTestDataSheet("Year202627"), PayrollMonth,
					commonFunc.getDataFromTestDataSheet("RefText_Test"));
			clickLoadUnitGroupInPreparePayroll();
			clickUnloadUnitGroupInPreparePayroll();
			clickLoadUnitGroupInPreparePayroll();
			clickUnitGroupLabelInPreparePayroll();
			unselectUnitAndGroupWithNameInLoadPayroll(commonFunc.getDataFromTestDataSheet("Unit1Text"));
			selectUnitAndGroupWithNameInLoadPayroll(commonFunc.getDataFromTestDataSheet("Unit1Text"));
			clickLoadEmployeeInPreparePayroll();
			clickUnloadEmployeeInPreparePayroll();
			clickLoadEmployeeInPreparePayroll();
			clickEmployeeLabelInPreparePayroll();
			unSelectedAllAndSearchAndSelectEmployee(VariableHandler.getEmpID());
			selectEmployeeWithNameInLoadPayroll(VariableHandler.getEmpID());
			clickAttendanceLabelInPreparePayroll();
			updatePaidAndUnpaidLeaveInAttendancePayroll(VariableHandler.getEmpID(), "1", "1");
			verifyAttendanceDataValuesInPreparePayroll();
			verifyOTHrsOnDutyAndPermissionHrsInAttendance(VariableHandler.getEmpID());
			clickPreparePayrollButton();
			clickExceptionLabelAndVerifyNoException();
			clickPayrollLabelInPreparePayroll();
			clickEmpIdInPayrollTabAndVerifyNavigated(VariableHandler.getEmpID());
			verifyAllowanceValuesInEmpIdAdditionPage();
			verifyAdjustmentValuesInEmpIdAdditionPage();
			verifyTotalAdditionDeductionAndNetSalary();
			webDB.navigateToBack();
			clickReviewCompletedInPreparePayroll();
			clickProcessPayrollButtonInPreparePayroll();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyPayrollProcessFromLoadUnitToProcessPayroll': " + e);
			return false;
		}
	}

	/**
	 * This method used To Check Prepare/Process payroll Find page with all line
	 * wise record
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyPreparePayrollFindWithLineWiseRecord() {
		try {
			commonFunc.navigateToPrepareProcessPayrollTab();
			clickLinesRadioButtonInFind();
			findWithUnitInLineWise(commonFunc.getDataFromTestDataSheet("Unit1"));
			webDB.navigateToRefresh();
			clickLinesRadioButtonInFind();
			findWithYearInLineWise(commonFunc.getDataFromTestDataSheet("Year202526"));
			webDB.navigateToRefresh();
			clickLinesRadioButtonInFind();
			findWithDesignationInLineWise(commonFunc.getDataFromTestDataSheet("Emp_SalaryDesignation"));
			webDB.navigateToRefresh();
			clickLinesRadioButtonInFind();
			findWithDepartmentInLineWise(commonFunc.getDataFromTestDataSheet("QualityText"));
			webDB.navigateToRefresh();
			clickLinesRadioButtonInFind();
			commonFunc.searchAndSelectValueInFilters(PreparePayrollLocators.FindYearLovBtn,
					commonFunc.getDataFromTestDataSheet("Year202526"));
			findWithPeriodInLineWise(commonFunc.getDataFromTestDataSheet("DecemberMonth"));
			webDB.navigateToRefresh();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyPreparePayrollFindWithLineWiseRecord': " + e);
			return false;
		}
	}

	/**
	 * This method used to click search icon in Prepare Payroll Find Page
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickSearchButtonInPreparePayroll() {
		flag = webDB.waitForElement(PreparePayrollLocators.PayrollFindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PreparePayrollLocators.PayrollFindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.verifyIsElementDisplayed(PreparePayrollLocators.FindResultSearchBar, ElementType.Xpath)
					|| webDB.verifyIsElementDisplayed(PreparePayrollLocators.FindHeaderResultSearchBar,
							ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Icon Clicked in Prepare Payroll");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to find Payroll using Unit in Linewise
	 * 
	 * @author Sakthi-GWL
	 * @param Unit
	 */
	public void findWithUnitInLineWise(String Unit) {
		commonFunc.searchAndSelectValueInFilters(PreparePayrollLocators.FindUnitLovBtn, Unit);
		clickSearchButtonInPreparePayroll();
		List<WebElement> elements = webDB.getListOfElements(PreparePayrollLocators.FindUnitLineResultTable(),
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().equalsIgnoreCase(Unit)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Unit in Payroll Linewise: " + Unit);
	}

	/**
	 * This method used to find Payroll using Designation in Linewise
	 * 
	 * @author Sakthi-GWL
	 * @param Designation
	 */
	public void findWithDesignationInLineWise(String Designation) {
		commonFunc.searchAndSelectValueInFilters(PreparePayrollLocators.FindDesignationLovBtn, Designation);
		clickSearchButtonInPreparePayroll();
		List<WebElement> elements = webDB.getListOfElements(PreparePayrollLocators.FindDesignationLineResultTable(),
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().equalsIgnoreCase(Designation)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Designation in Payroll Linewise: " + Designation);
	}

	/**
	 * This method used to find Payroll using Department in Linewise
	 * 
	 * @author Sakthi-GWL
	 * @param Department
	 */
	public void findWithDepartmentInLineWise(String Department) {
		commonFunc.searchAndSelectValueInFilters(PreparePayrollLocators.FindDepartmentLovBtn, Department);
		clickSearchButtonInPreparePayroll();
		List<WebElement> elements = webDB.getListOfElements(PreparePayrollLocators.FindDepartmentLineResultTable(),
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().equalsIgnoreCase(Department)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Department in Payroll Linewise: " + Department);
	}

	/**
	 * This method used to find Payroll using Year in Linewise
	 * 
	 * @author Sakthi-GWL
	 * @param Year
	 */
	public void findWithYearInLineWise(String Year) {
		commonFunc.searchAndSelectValueInFilters(PreparePayrollLocators.FindYearLovBtn, Year);
		clickSearchButtonInPreparePayroll();
		List<WebElement> elements = webDB.getListOfElements(PreparePayrollLocators.FindYearLineResultTable(),
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().equalsIgnoreCase(Year)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Year in Payroll Linewise: " + Year);
	}

	/**
	 * This method used to find Payroll using Period in Linewise
	 * 
	 * @author Sakthi-GWL
	 * @param Period
	 */
	public void findWithPeriodInLineWise(String Period) {
		commonFunc.searchAndSelectValueInFilters(PreparePayrollLocators.FindPeriodLovBtn, Period);
		clickSearchButtonInPreparePayroll();
		List<WebElement> elements = webDB.getListOfElements(PreparePayrollLocators.FindPeriodLineResultTable(),
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().equalsIgnoreCase(Period)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Period in Payroll Linewise: " + Period);
	}

	/**
	 * This method used To Check Prepare/Process payroll Find page with all header
	 * wise record
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyPreparePayrollFindWithHeaderWiseRecord() {
		String SearchMonth = "December";
		String verifyMonth = "DEC";
		try {
			commonFunc.navigateToPrepareProcessPayrollTab();
			clickHeaderRadioButtonInFind();
			findWithYearInHeaderWise(commonFunc.getDataFromTestDataSheet("Year202526"));
			webDB.navigateToRefresh();
			clickHeaderRadioButtonInFind();
			commonFunc.searchAndSelectValueInFilters(PreparePayrollLocators.FindYearLovBtn,
					commonFunc.getDataFromTestDataSheet("Year202526"));
			findWithPeriodInHeaderWise(SearchMonth, verifyMonth);
			webDB.navigateToRefresh();
			clickHeaderRadioButtonInFind();
			findWithCalenderInHeaderWise(commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"));
			webDB.navigateToRefresh();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyPreparePayrollFindWithHeaderWiseRecord': " + e);
			return false;
		}
	}

	/**
	 * This method used to find Payroll using Year in Headerwise
	 * 
	 * @author Sakthi-GWL
	 * @param Year
	 */
	public void findWithYearInHeaderWise(String Year) {
		commonFunc.searchAndSelectValueInFilters(PreparePayrollLocators.FindYearLovBtn, Year);
		clickSearchButtonInPreparePayroll();
		List<WebElement> elements = webDB.getListOfElements(PreparePayrollLocators.FindYearHeaderResultTable(),
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().equalsIgnoreCase(Year)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Year in Payroll Headerwise: " + Year);
	}

	/**
	 * This method used to find Payroll using Calender in Headerwise
	 * 
	 * @author Sakthi-GWL
	 * @param Calender
	 */
	public void findWithCalenderInHeaderWise(String Calender) {
		commonFunc.searchAndSelectValueInFilters(PreparePayrollLocators.FindCalenderLovBtn, Calender);
		clickSearchButtonInPreparePayroll();
		List<WebElement> elements = webDB.getListOfElements(PreparePayrollLocators.FindCalenderHeaderResultTable(),
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().equalsIgnoreCase(Calender)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Calender in Payroll Headerwise: " + Calender);
	}

	/**
	 * This method used to find Payroll using Period in Headerwise
	 * 
	 * @author Sakthi-GWL
	 * @param Period
	 */
	public void findWithPeriodInHeaderWise(String searchvalue, String verifyValue) {
		commonFunc.searchAndSelectValueInFilters(PreparePayrollLocators.FindPeriodLovBtn, searchvalue);
		clickSearchButtonInPreparePayroll();
		List<WebElement> elements = webDB.getListOfElements(PreparePayrollLocators.FindPeriodHeaderResultTable(),
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().equalsIgnoreCase(verifyValue)
					|| webElement.getText().equalsIgnoreCase(searchvalue)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Period in Payroll Headerwise: " + searchvalue);
	}

	/**
	 * This Method used to click header in find page
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickHeaderRadioButtonInFind() {
		flag = webDB.waitForElement(PreparePayrollLocators.FindHeaderRadioBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PreparePayrollLocators.FindHeaderRadioBtn, ElementType.Xpath);
			webDB.clickAnElement(PreparePayrollLocators.FindHeaderRadioBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(2000);
			flag = webDB.isElementSelected(PreparePayrollLocators.FindHeaderRadioInput, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Header Radio Button Clicked in Find Page");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used to click lines in find page
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickLinesRadioButtonInFind() {
		flag = webDB.waitForElement(PreparePayrollLocators.FindLinesRadioBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PreparePayrollLocators.FindLinesRadioBtn, ElementType.Xpath);
			webDB.clickAnElement(PreparePayrollLocators.FindLinesRadioBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(2000);
			flag = webDB.isElementSelected(PreparePayrollLocators.FindLinesRadioInput, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Lines Radio Button Clicked in Find Page");
			}
		}
		Assert.assertTrue(flag);
	}
}
