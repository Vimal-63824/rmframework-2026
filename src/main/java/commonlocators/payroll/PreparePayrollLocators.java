package commonlocators.payroll;

import commonfunctions.CommonFunctions;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks April 30, 2025
 */
public class PreparePayrollLocators {

	/** @locatorType Xpath */
	public static final String PreparePayrollLabel = "//input[@aria-label='Prepare / Process Payroll']/following-sibling::label";
	/** @locatorType Xpath */
	public static final String PayrollFindSearchBtn = "//button[@data-testid='PRE_REPORT_Button']";
	/** @locatorType Xpath */
	public static final String PreparePayrollAddIcon = "//button[@data-testid='PRE_ADD_Button']";
	/** @locatorType Xpath */
	public static final String CreatePayrollCalendarLovBtn = "//*[@data-testid='PAY_CALENDER']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String CreatePayrollYearLovBtn = "//*[@data-testid='PAY_YEAR']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String CreatePayrollPeriodLovBtn = "//*[@data-testid='PAY_PERIOD']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String CreatePayrollNarrationInput = "//input[@data-testid='PAY_NAR']";
	/** @locatorType Xpath */
	public static final String CreatePayrollTickIcon = "//button[@data-testid='PAY_SAVE_Button']";
	/** @locatorType Xpath */
	public static final String PayrollProcessDisplayDocNo = "//*[contains(@class,'title')]/b";
	/** @locatorType Xpath */
	public static final String UnitGroupLabel = "//label[text()='Unit / Group']";
	/** @locatorType Xpath */
	public static final String EmployeeLabel = "//label[text()='Employee']";
	/** @locatorType Xpath */
	public static final String ExceptionLabel = "//label[text()='Exception']";
	/** @locatorType Xpath */
	public static final String AttendanceLabel = "//label[text()='Attendance']";
	/** @locatorType Xpath */
	public static final String PayrollLabel = "//label[text()='Payroll']";
	/** @locatorType Xpath */
	public static final String LoadUnitGroupBtn = "//button[@data-testid='HPPH_LOADU_Button']";
	/** @locatorType Xpath */
	public static final String UnloadUnitGroupBtn = "//button[@data-testid='HPPH_UNLOADU_Button']";
	/** @locatorType Xpath */
	public static final String UnitContentContainer = "//div[@id='unit_content']";
	/** @locatorType Xpath */
	public static final String GroupContentContainer = "//div[@id='group_content']";
	/** @locatorType Xpath */
	public static final String AttendanceContentContainer = "//div[@id='ATTD_ig_grid_vc']";

	/** @locatorType Xpath */
	public static final String UnitGroupCheckBoxInput(String UnitGroupName) {
		return "//table/tbody/tr//*[text()='" + UnitGroupName + "']/../..//input";
	}

	/** @locatorType Xpath */
	public static final String LoadEmployeeBtn = "//button[@data-testid='HPPH_LOAD1_Button']";
	/** @locatorType Xpath */
	public static final String UnloadEmployeeBtn = "//button[@data-testid='HPPH_UNLOADE_Button']";
	/** @locatorType Xpath */
	public static final String EmployeeSearchBarInput = "//input[@id='TAB2_search_field']";
	/** @locatorType Xpath */
	public static final String EmployeeSearchBarGoBtn = "//button[@id='TAB2_search_button']";
	/** @locatorType Xpath */
	public static final String AllEmployeeCheckBoxInput = "//table/tbody//th//input[@id='check_all']";

	/** @locatorType Xpath */
	public static final String EmployeeCheckBoxInput(String Employee) {
		return "//table/tbody/tr//*[text()='" + Employee + "']/../td/input";
	}

	/** @locatorType Xpath */
	public static final String AttendanceTable = "//div[@id='ATTD_ig_grid_vc']//table/thead/tr/th";

	/** @locatorType Xpath */
	public static String AttendanceEmpNameList() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AttendanceTable, "Emp. Name");
	}

	/** @locatorType Xpath */
	public static String AttendanceCalDaysList() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AttendanceTable, "Cal. Days");
	}

	/** @locatorType Xpath */
	public static String AttendanceWorkDaysList() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AttendanceTable, "Wrk. Days");
	}

	/** @locatorType Xpath */
	public static String AttendanceHolidaysList() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AttendanceTable, "Holidays");
	}

	/** @locatorType Xpath */
	public static String AttendanceWeekendsList() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AttendanceTable, "Weekends");
	}

	/** @locatorType Xpath */
	public static String AttendancePaidDaysList() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AttendanceTable, "Paid Days");
	}

	/** @locatorType Xpath */
	public static final String AttendanceSaveIcon = "//button[@data-testid='HPPH_SAVE_Button']";

	/** @locatorType Xpath */
	public static final String AttendancePaidLeaveData(String Name) {
		return "//table/tbody/tr/td[contains(text(),'" + Name + "')]/../td[10]";
	}

	/** @locatorType Xpath */
	public static final String AttendancePaidLeaveInput(String Name) {
		return "//table/tbody/tr/td[contains(text(),'" + Name + "')]/../td[10]//input";
	}

	/** @locatorType Xpath */
	public static final String AttendanceUnpaidLeaveData(String Name) {
		return "//table/tbody/tr/td[contains(text(),'" + Name + "')]/../td[11]";
	}

	/** @locatorType Xpath */
	public static final String AttendanceUnpaidLeaveInput(String Name) {
		return "//table/tbody/tr/td[contains(text(),'" + Name + "')]/../td[11]//input";
	}

	/** @locatorType Xpath */
	public static final String AttendanceOnDutyLeaveData(String Name) {
		return "//table/tbody/tr/td[contains(text(),'" + Name + "')]/../td[13]";
	}

	/** @locatorType Xpath */
	public static final String AttendanceOnDutyLeaveInput(String Name) {
		return "//table/tbody/tr/td[contains(text(),'" + Name + "')]/../td[13]//input";
	}

	/** @locatorType Xpath */
	public static final String AttendancePermHrsLeaveData(String Name) {
		return "//table/tbody/tr/td[contains(text(),'" + Name + "')]/../td[14]";
	}

	/** @locatorType Xpath */
	public static final String AttendancePermHrsLeaveInput(String Name) {
		return "//table/tbody/tr/td[contains(text(),'" + Name + "')]/../td[14]//input";
	}

	/** @locatorType Xpath */
	public static final String AttendanceOTHrsLeaveData(String Name) {
		return "//table/tbody/tr/td[contains(text(),'" + Name + "')]/../td[15]";
	}

	/** @locatorType Xpath */
	public static final String AttendanceOTHrsLeaveInput(String Name) {
		return "//table/tbody/tr/td[contains(text(),'" + Name + "')]/../td[15]//input";
	}

	/** @locatorType Xpath */
	public static final String PreparePayrollBtn = "//button[@data-testid='HPPH_PREPARE_Button']";
	/** @locatorType Xpath */
	public static final String ExceptionDataView = "//div[@aria-label='Data view']/div[@class='a-IRR-noDataMsg']";
	/** @locatorType Xpath */
	public static final String ExceptionDataViewTable = "//div[@aria-label='Data view']//table";
	/** @locatorType Xpath */
	public static final String PayrollContentContainer = "//div[@id='TAB4_ig_grid_vc']";

	/** @locatorType Xpath */
	public static final String PayrollEmpIdTableData(String EmpId) {
		return "//div[@id='TAB4_ig_grid_vc']//table/tbody/tr/td/a[text()='" + EmpId + "']";
	}

	/** @locatorType Xpath */
	public static final String PayrollEmpIdDisplay = "//input[@id='P16251310506_EMP_ID']";
	/** @locatorType Xpath */
	public static final String PayrollAllowanceTable = "//table[contains(@aria-label,'Addition')]//tbody/tr/th";

	/** @locatorType Xpath */
	public static String PayrollAllowanceDescriptionList() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PayrollAllowanceTable, "Description")
				+ "/span[last()]";
	}

	/** @locatorType Xpath */
	public static String PayrollAllowanceActualAmntList() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PayrollAllowanceTable, "Actual Amount")
				+ "/span[last()]";
	}

	/** @locatorType Xpath */
	public static String PayrollAllowanceAmountList() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PayrollAllowanceTable, "Amount")
				+ "/span[last()]";
	}

	/** @locatorType Xpath */
	public static final String PayrollAdjustmentTable = "//table[contains(@aria-label,'Deduction')]//tbody/tr/th";

	/** @locatorType Xpath */
	public static String PayrollAdjustmentDescriptionList() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PayrollAdjustmentTable, "Description")
				+ "/span[last()]";
	}

	/** @locatorType Xpath */
	public static String PayrollAdjustmentAmountList() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PayrollAdjustmentTable, "Amount")
				+ "/span[last()]";
	}

	/** @locatorType Xpath */
	public static final String PayrollTotalAdditionDisplayText = "//span[@id='P16251310506_TOT_ADD_DISPLAY']";
	/** @locatorType Xpath */
	public static final String PayrollTotalDeductionDisplayText = "//span[@id='P16251310506_TOT_DED_DISPLAY']";
	/** @locatorType Xpath */
	public static final String PayrollNetSalaryDisplayText = "//span[@id='P16251310506_NET_SAL1_DISPLAY']";
	/** @locatorType Xpath */
	public static final String ReviewCompletedBtn = "//button[@data-testid='HPPH_REVIEW_Button']";
	/** @locatorType Xpath */
	public static final String ProcessPayrollBtn = "//button[@data-testid='HPPH_PROCESS_Button']";
	/** @locatorType Xpath */
	public static final String FindHeaderRadioBtn = "//label[text()='Header']";
	/** @locatorType Xpath */
	public static final String FindHeaderRadioInput = "//label[text()='Header']/preceding-sibling::input";
	/** @locatorType Xpath */
	public static final String FindLinesRadioInput = "//label[text()='Line(s)']/preceding-sibling::input";
	/** @locatorType Xpath */
	public static final String FindLinesRadioBtn = "//label[text()='Line(s)']";
	/** @locatorType Xpath */
	public static final String FindResultSearchBar = "//input[@id='detail_search_field']";
	/** @locatorType Xpath */
	public static final String FindHeaderResultSearchBar = "//input[@id='header_search_field']";
	/** @locatorType Xpath */
	public static final String FindYearLovBtn = "//*[@data-testid='PRE_YEAR']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindPeriodLovBtn = "//*[@data-testid='PRE_PERIOD']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindCalenderLovBtn = "//*[@data-testid='PRE_CALANDER']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindDesignationLovBtn = "//*[@data-testid='PRE_DESIG']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindDepartmentLovBtn = "//*[@data-testid='PRE_DEPT']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindUnitLovBtn = "//*[@data-testid='PRE_UNIT']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindHeaderResultTable = "//table[contains(@aria-label,'Result(s)h')]//tr/th";
	/** @locatorType Xpath */
	public static final String FindLinesResultTable = "//table[contains(@aria-label,'Result(s)l')]//tr/th";

	/** @locatorType Xpath */
	public static String FindYearHeaderResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindHeaderResultTable, "Year");
	}

	/** @locatorType Xpath */
	public static String FindPeriodHeaderResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindHeaderResultTable, "Period");
	}

	/** @locatorType Xpath */
	public static String FindCalenderHeaderResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindHeaderResultTable, "Calendar");
	}

	/** @locatorType Xpath */
	public static String FindYearLineResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindLinesResultTable, "Year");
	}

	/** @locatorType Xpath */
	public static String FindPeriodLineResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindLinesResultTable, "Period");
	}

	/** @locatorType Xpath */
	public static String FindDesignationLineResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindLinesResultTable, "Designation");
	}

	/** @locatorType Xpath */
	public static String FindDepartmentLineResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindLinesResultTable, "Department");
	}

	/** @locatorType Xpath */
	public static String FindUnitLineResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindLinesResultTable, "Unit");
	}
}
