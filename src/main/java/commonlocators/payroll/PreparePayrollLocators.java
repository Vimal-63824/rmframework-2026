package commonlocators.payroll;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks April 30, 2025
 */
public class PreparePayrollLocators {

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
	public static final String AttendanceEmpNameList = "//div[@id='ATTD_ig_grid_vc']//table/tbody/tr/td[2]";
	/** @locatorType Xpath */
	public static final String AttendanceCalDaysList = "//div[@id='ATTD_ig_grid_vc']//table/tbody/tr/td[6]";
	/** @locatorType Xpath */
	public static final String AttendanceWorkDaysList = "//div[@id='ATTD_ig_grid_vc']//table/tbody/tr/td[7]";
	/** @locatorType Xpath */
	public static final String AttendanceHolidaysList = "//div[@id='ATTD_ig_grid_vc']//table/tbody/tr/td[8]";
	/** @locatorType Xpath */
	public static final String AttendanceWeekendsList = "//div[@id='ATTD_ig_grid_vc']//table/tbody/tr/td[9]";
	/** @locatorType Xpath */
	public static final String AttendancePaidDaysList = "//div[@id='ATTD_ig_grid_vc']//table/tbody/tr/td[12]";
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
	public static final String ExceptionDataView = "//div[@aria-label='Data view of Exception']";
	/** @locatorType Xpath */
	public static final String ExceptionDataViewTable = "//div[@aria-label='Data view of Exception']//table";
	/** @locatorType Xpath */
	public static final String PayrollContentContainer = "//div[@id='TAB4_ig_grid_vc']";

	/** @locatorType Xpath */
	public static final String PayrollEmpIdTableData(String EmpId) {
		return "//div[@id='TAB4_ig_grid_vc']//table/tbody/tr/td/a[text()='" + EmpId + "']";
	}

	/** @locatorType Xpath */
	public static final String PayrollEmpIdDisplay = "//input[@id='P16251310506_EMP_ID']";
	/** @locatorType Xpath */
	public static final String PayrollAllowanceTable = "//table[contains(@aria-label,'Addition')]";
	/** @locatorType Xpath */
	public static final String PayrollAllowanceDescriptionList = "//table[contains(@aria-label,'Addition')]//tr/td[2]/span[last()]";
	/** @locatorType Xpath */
	public static final String PayrollAllowanceActualAmntList = "//table[contains(@aria-label,'Addition')]//tr/td[3]/span[last()]";
	/** @locatorType Xpath */
	public static final String PayrollAllowanceAmountList = "//table[contains(@aria-label,'Addition')]//tr/td[4]/span[last()]";
	/** @locatorType Xpath */
	public static final String PayrollAdjustmentTable = "//table[contains(@aria-label,'Deduction')]";
	/** @locatorType Xpath */
	public static final String PayrollAdjustmentDescriptionList = "//table[contains(@aria-label,'Deduction')]//tr/td[2]/span[last()]";
	/** @locatorType Xpath */
	public static final String PayrollAdjustmentAmountList = "//table[contains(@aria-label,'Deduction')]//tr/td[3]/span[last()]";
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
	public static final String FindYearHeaderResultTable = "//table[contains(@aria-label,'Result(s)h')]//tr/td[3]";
	/** @locatorType Xpath */
	public static final String FindPeriodHeaderResultTable = "//table[contains(@aria-label,'Result(s)h')]//tr/td[4]";
	/** @locatorType Xpath */
	public static final String FindCalenderHeaderResultTable = "//table[contains(@aria-label,'Result(s)h')]//tr/td[2]";
	/** @locatorType Xpath */
	public static final String FindYearLineResultTable = "//table[contains(@aria-label,'Result(s)l')]//tr/td[2]";
	/** @locatorType Xpath */
	public static final String FindPeriodLineResultTable = "//table[contains(@aria-label,'Result(s)l')]//tr/td[3]";
	/** @locatorType Xpath */
	public static final String FindDesignationLineResultTable = "//table[contains(@aria-label,'Result(s)l')]//tr/td[7]";
	/** @locatorType Xpath */
	public static final String FindDepartmentLineResultTable = "//table[contains(@aria-label,'Result(s)l')]//tr/td[8]";
	/** @locatorType Xpath */
	public static final String FindUnitLineResultTable = "//table[contains(@aria-label,'Result(s)l')]//tr/td[9]";

}
