package commonfunctions.payroll;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks April 30, 2025
 */
import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.WebElement;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.payroll.PreparePayrollLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class PreparePayrollFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private CommonFunctions commonFunc = new CommonFunctions();
	public ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	private static CommonPayrollFunctions commonPayrollFunctions = new CommonPayrollFunctions();

	/**
	 * This method used to click Add icon In Prepare Payroll
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickAddIconInPreparePayroll() {
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
	private void fillCreationFormInPreparePayroll(String Calendar, String Year, String Period, String Narration) {
		commonFunc.searchAndSelectValueInFilters(PreparePayrollLocators.CreatePayrollCalendarLovBtn, Calendar);
		logs.logging("INFO", "Calendar Selected in Prepare Payroll: " + Calendar);
		commonFunc.searchAndSelectValueInFilters(PreparePayrollLocators.CreatePayrollYearLovBtn, Year);
		logs.logging("INFO", "Year Selected in Prepare Payroll: " + Year);
		commonFunc.searchAndSelectValueInFilters(PreparePayrollLocators.CreatePayrollPeriodLovBtn, Period);
		logs.logging("INFO", "Period Selected in Prepare Payroll: " + Period);
		commonPayrollFunctions.addDetailInTableWithoutLovButton(PreparePayrollLocators.CreatePayrollNarrationInput,
				PreparePayrollLocators.CreatePayrollNarrationInput, Narration);
		logs.logging("INFO", "Narration Entered in Prepare Payroll: " + Narration);
		flag = webDB.waitForElement(PreparePayrollLocators.CreatePayrollTickIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PreparePayrollLocators.CreatePayrollTickIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Tick Icon Clicked in Prepare Payroll Creation");
				commonFunc.checkRefreshCellsDisappear();
				String DocNo = webDB.getTextFromElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				DocNo = (DocNo.split("No.:")[1]).trim();
				VariableHandler.setPayrollDocNo(DocNo);
				logs.logging("INFO", "Displayed Payroll Document Number: " + DocNo);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to Load Unit and Group In Prepare Payroll
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickLoadUnitGroupInPreparePayroll() {
		flag = webDB.waitForElement(PreparePayrollLocators.LoadUnitGroupBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PreparePayrollLocators.LoadUnitGroupBtn, ElementType.Xpath);
			webDB.clickAnElement(PreparePayrollLocators.LoadUnitGroupBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElementUntilGivenTime(PreparePayrollLocators.UnloadUnitGroupBtn, ElementType.Xpath, 60);
			if (flag) {
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
	private void clickUnloadUnitGroupInPreparePayroll() {
		flag = webDB.waitForElement(PreparePayrollLocators.UnloadUnitGroupBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PreparePayrollLocators.UnloadUnitGroupBtn, ElementType.Xpath);
			webDB.clickAnElement(PreparePayrollLocators.UnloadUnitGroupBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElementUntilGivenTime(PreparePayrollLocators.LoadUnitGroupBtn, ElementType.Xpath, 60);
			if (flag) {
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
	private void clickUnitGroupLabelInPreparePayroll() {
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
	 * @Optional_param boolean DeselectOption(if you given in param Deselect unit
	 *                 and group)
	 */
	private void selectUnitAndGroupWithNameInLoadPayroll(String Name, boolean... DeselectOption) {
		flag = webDB.waitForElement(PreparePayrollLocators.UnitGroupCheckBoxInput(Name), ElementType.Xpath);
		if (flag) {
			if (DeselectOption != null) {
				flag = webDB.isElementSelected(PreparePayrollLocators.UnitGroupCheckBoxInput(Name), ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(PreparePayrollLocators.UnitGroupCheckBoxInput(Name), ElementType.Xpath);
					webDB.clickAnElement(PreparePayrollLocators.UnitGroupCheckBoxInput(Name), ElementType.Xpath);
					flag = !webDB.isElementSelected(PreparePayrollLocators.UnitGroupCheckBoxInput(Name),
							ElementType.Xpath);
				}
			} else {
				flag = !webDB.isElementSelected(PreparePayrollLocators.UnitGroupCheckBoxInput(Name), ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(PreparePayrollLocators.UnitGroupCheckBoxInput(Name), ElementType.Xpath);
					webDB.clickAnElement(PreparePayrollLocators.UnitGroupCheckBoxInput(Name), ElementType.Xpath);
				}
				flag = webDB.isElementSelected(PreparePayrollLocators.UnitGroupCheckBoxInput(Name), ElementType.Xpath);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to load Employee In Prepare Payroll
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickLoadEmployeeInPreparePayroll() {
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
	private void clickUnloadEmployeeInPreparePayroll() {
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
	private void clickEmployeeLabelInPreparePayroll() {
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
	private void unSelectedAllAndSearchAndSelectEmployee(String EmpId) {
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
		assertTrue(flag);
	}

	/**
	 * This method used to click Employee Input Check Box In Prepare Payroll
	 * 
	 * @author Sakthi-GWL
	 * @param Name
	 * @Optional_param boolean DeselectOption(if you given in param Deselect unit
	 *                 and group)
	 */
	private void selectEmployeeWithNameInLoadPayroll(String Name, boolean... DeselectOption) {
		flag = webDB.waitForElement(PreparePayrollLocators.EmployeeCheckBoxInput(Name), ElementType.Xpath);
		if (flag) {
			if (DeselectOption != null) {
				flag = webDB.isElementSelected(PreparePayrollLocators.EmployeeCheckBoxInput(Name), ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(PreparePayrollLocators.EmployeeCheckBoxInput(Name), ElementType.Xpath);
					webDB.clickAnElement(PreparePayrollLocators.EmployeeCheckBoxInput(Name), ElementType.Xpath);
					flag = !webDB.isElementSelected(PreparePayrollLocators.EmployeeCheckBoxInput(Name),
							ElementType.Xpath);
				}
			} else {
				flag = !webDB.isElementSelected(PreparePayrollLocators.EmployeeCheckBoxInput(Name), ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(PreparePayrollLocators.EmployeeCheckBoxInput(Name), ElementType.Xpath);
					webDB.clickAnElement(PreparePayrollLocators.EmployeeCheckBoxInput(Name), ElementType.Xpath);
				}
				flag = webDB.isElementSelected(PreparePayrollLocators.EmployeeCheckBoxInput(Name), ElementType.Xpath);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click Attendance label In Prepare Payroll
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickAttendanceLabelInPreparePayroll() {
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
	private void updatePaidAndUnpaidLeaveInAttendancePayroll(String EmpName, String PL, String UL) {
		webDB.waitForElement(PreparePayrollLocators.AttendanceContentContainer, ElementType.Xpath);
		flag = webDB.waitForElement(PreparePayrollLocators.AttendancePaidLeaveData(EmpName), ElementType.Xpath);
		if (flag) {
			commonPayrollFunctions.addDetailInTableWithoutLovButton(
					PreparePayrollLocators.AttendancePaidLeaveData(EmpName),
					PreparePayrollLocators.AttendancePaidLeaveInput(EmpName), PL);
			logs.logging("INFO", "Paid Leave Updated - '" + PL + "' for EmpName: " + EmpName);
			commonPayrollFunctions.addDetailInTableWithoutLovButton(
					PreparePayrollLocators.AttendanceUnpaidLeaveData(EmpName),
					PreparePayrollLocators.AttendanceUnpaidLeaveInput(EmpName), UL);
			logs.logging("INFO", "Unpaid Leave Updated - '" + UL + "' for EmpName: " + EmpName);
			flag = webDB.waitForElement(PreparePayrollLocators.AttendanceSaveIcon, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(PreparePayrollLocators.AttendanceSaveIcon, ElementType.Xpath);
				webDB.clickAnElement(PreparePayrollLocators.AttendanceSaveIcon, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				if (flag) {
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
	private void verifyAttendanceDataValuesInPreparePayroll() {
		flag = webDB.waitForElement(PreparePayrollLocators.AttendanceContentContainer, ElementType.Xpath);
		if (flag) {
			List<WebElement> EmpName = webDB.getListOfElements(PreparePayrollLocators.AttendanceEmpNameList,
					ElementType.Xpath);
			List<WebElement> CalDays = webDB.getListOfElements(PreparePayrollLocators.AttendanceCalDaysList,
					ElementType.Xpath);
			List<WebElement> WorkDays = webDB.getListOfElements(PreparePayrollLocators.AttendanceWorkDaysList,
					ElementType.Xpath);
			List<WebElement> Holidays = webDB.getListOfElements(PreparePayrollLocators.AttendanceHolidaysList,
					ElementType.Xpath);
			List<WebElement> Weekends = webDB.getListOfElements(PreparePayrollLocators.AttendanceWeekendsList,
					ElementType.Xpath);
			List<WebElement> PaidDays = webDB.getListOfElements(PreparePayrollLocators.AttendancePaidDaysList,
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
	private void verifyOTHrsOnDutyAndPermissionHrsInAttendance(String EmpName) {
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
	private void clickPreparePayrollButton() {
		flag = webDB.waitForElement(PreparePayrollLocators.PreparePayrollBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PreparePayrollLocators.PreparePayrollBtn, ElementType.Xpath);
			webDB.clickAnElement(PreparePayrollLocators.PreparePayrollBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 60);
			if (flag) {
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
	private void clickExceptionLabelAndVerifyNoException() {
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
	private void clickPayrollLabelInPreparePayroll() {
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
	private void clickEmpIdInPayrollTabAndVerifyNavigated(String EmpId) {
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
	private void verifyAllowanceValuesInEmpIdAdditionPage() {
		flag = webDB.waitForElement(PreparePayrollLocators.PayrollAllowanceTable, ElementType.Xpath);
		if (flag) {
			List<WebElement> Desc = webDB.getListOfElements(PreparePayrollLocators.PayrollAllowanceDescriptionList,
					ElementType.Xpath);
			List<WebElement> ActualAmnt = webDB.getListOfElements(PreparePayrollLocators.PayrollAllowanceActualAmntList,
					ElementType.Xpath);
			List<WebElement> Amnt = webDB.getListOfElements(PreparePayrollLocators.PayrollAllowanceAmountList,
					ElementType.Xpath);
			for (int i = 0; i < Amnt.size(); i++) {
				if (i <= Desc.size()) {
					logs.logging("INFO", "Displayed Description in Allowances: " + Desc.get(i).getText());
				}
				if (i <= ActualAmnt.size()) {
					logs.logging("INFO", "Displayed Actual Amount in Allowances: " + ActualAmnt.get(i).getText());
				}
				if (i <= ActualAmnt.size()) {
					logs.logging("INFO", "Displayed Total Amount in Allowances: " + Amnt.get(i).getText());
				} else {
					logs.logging("INFO", "Displayed Amount in Allowances: " + Amnt.get(i).getText());
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
	private void verifyAdjustmentValuesInEmpIdAdditionPage() {
		flag = webDB.waitForElement(PreparePayrollLocators.PayrollAdjustmentTable, ElementType.Xpath);
		if (flag) {
			List<WebElement> Desc = webDB.getListOfElements(PreparePayrollLocators.PayrollAdjustmentDescriptionList,
					ElementType.Xpath);
			List<WebElement> Amnt = webDB.getListOfElements(PreparePayrollLocators.PayrollAdjustmentAmountList,
					ElementType.Xpath);
			for (int i = 0; i < Amnt.size(); i++) {
				if (i <= Desc.size()) {
					logs.logging("INFO", "Displayed Description in Adjustment: " + Desc.get(i).getText());
				}
				if (i <= Desc.size()) {
					logs.logging("INFO", "Displayed Total Amount in Adjustment: " + Amnt.get(i).getText());
				} else {
					logs.logging("INFO", "Displayed Amount in Adjustment: " + Amnt.get(i).getText());
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
	private void verifyTotalAdditionDeductionAndNetSalary() {
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
	private void clickReviewCompletedInPreparePayroll() {
		flag = webDB.waitForElement(PreparePayrollLocators.ReviewCompletedBtn, ElementType.Xpath);
		if (flag) {
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
	private void clickProcessPayrollButtonInPreparePayroll() {
		flag = webDB.waitForElement(PreparePayrollLocators.ProcessPayrollBtn, ElementType.Xpath);
		if (flag) {
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM");
		String Month = date.format(formatter).toUpperCase();
		try {
			commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
					System.getProperty("HRMSelfApprovePassword"), "HRMSelfApproveUser");
			commonFunc.navigateToPrepareProcessPayrollTab();
			clickAddIconInPreparePayroll();
			fillCreationFormInPreparePayroll(commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"),
					commonFunc.getDataFromTestDataSheet("Year202526"), Month,
					commonFunc.getDataFromTestDataSheet("RefText_Test"));
			clickLoadUnitGroupInPreparePayroll();
			clickUnloadUnitGroupInPreparePayroll();
			clickLoadUnitGroupInPreparePayroll();
			clickUnitGroupLabelInPreparePayroll();
			selectUnitAndGroupWithNameInLoadPayroll(commonFunc.getDataFromTestDataSheet("Unit1"), true);
			selectUnitAndGroupWithNameInLoadPayroll(commonFunc.getDataFromTestDataSheet("Unit1"));
			clickLoadEmployeeInPreparePayroll();
			clickUnloadEmployeeInPreparePayroll();
			clickLoadEmployeeInPreparePayroll();
			clickEmployeeLabelInPreparePayroll();
			unSelectedAllAndSearchAndSelectEmployee(commonFunc.getDataFromTestDataSheet("Emp_GWP0001"));
			selectEmployeeWithNameInLoadPayroll(commonFunc.getDataFromTestDataSheet("Emp_GWP0001"));
			clickAttendanceLabelInPreparePayroll();
			updatePaidAndUnpaidLeaveInAttendancePayroll(commonFunc.getDataFromTestDataSheet("Emp_GWP0001"), "1", "1");
			verifyAttendanceDataValuesInPreparePayroll();
			verifyOTHrsOnDutyAndPermissionHrsInAttendance(commonFunc.getDataFromTestDataSheet("Emp_GWP0001"));
			clickPreparePayrollButton();
			clickExceptionLabelAndVerifyNoException();
			clickPayrollLabelInPreparePayroll();
			clickEmpIdInPayrollTabAndVerifyNavigated(commonFunc.getDataFromTestDataSheet("Emp_GWP0001"));
			verifyAllowanceValuesInEmpIdAdditionPage();
			verifyAdjustmentValuesInEmpIdAdditionPage();
			verifyTotalAdditionDeductionAndNetSalary();
			webDB.navigateToBack();
			clickReviewCompletedInPreparePayroll();
			clickProcessPayrollButtonInPreparePayroll();
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyPayrollProcessFromLoadUnitToProcessPayroll': " + e);
			return false;
		}
	}
}
