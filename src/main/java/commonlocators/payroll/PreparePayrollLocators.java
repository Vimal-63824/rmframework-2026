package commonlocators.payroll;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks April 30, 2025
 */
public class PreparePayrollLocators {

	/** @locatorType Xpath */
	public static final String PayrollFindSearchBtn = "//button[@id='B1861433735867800630']";
	/** @locatorType Xpath */
	public static final String PreparePayrollAddIcon = "//button[@id='B1861433669887800629']";
	/** @locatorType Xpath */
	public static final String CreatePayrollCalendarLovBtn = "//button[@id='P1625131050_PAY_CALENDAR_lov_btn']";
	/** @locatorType Xpath */
	public static final String CreatePayrollYearLovBtn = "//button[@id='P1625131050_PAY_YEAR_lov_btn']";
	/** @locatorType Xpath */
	public static final String CreatePayrollPeriodLovBtn = "//button[@id='P1625131050_PAY_PERIOD_lov_btn']";
	/** @locatorType Xpath */
	public static final String CreatePayrollNarrationInput = "//input[@id='P1625131050_PAY_NAR']";
	/** @locatorType Xpath */
	public static final String CreatePayrollTickIcon = "//button[@id='B1862487255790819706']";
	/** @locatorType Xpath */
	public static final String PayrollProcessDisplayDocNo = "//*[@id='a_Collapsible1_R6108407611166170106_heading']/b";
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
	public static final String LoadUnitGroupBtn = "//button[@id='B3422386613938126250']";
	/** @locatorType Xpath */
	public static final String UnloadUnitGroupBtn = "//button[@id='B3422387008305126250']";
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
	public static final String LoadEmployeeBtn = "//button[@id='B3422387428419126252']";
	/** @locatorType Xpath */
	public static final String UnloadEmployeeBtn = "//button[@id='B3422387854589126252']";
	/** @locatorType Xpath */
	public static final String EmployeeSearchBarInput = "//input[@id='TAB2_search_field']";
	/** @locatorType Xpath */
	public static final String EmployeeSearchBarGoBtn = "//input[@id='TAB2_search_button']";
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
	public static final String AttendanceSaveIcon = "//button[@id='B1627237676758904020']";

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
	public static final String PreparePayrollBtn = "//button[@id='B3422388199030126252']";
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
	public static final String PayrollAllowanceTable = "//table[@id='5845881610404069955']";
	/** @locatorType Xpath */
	public static final String PayrollAllowanceDescriptionList = "//table[@id='5845881610404069955']//tr/td[2]/span[last()]";
	/** @locatorType Xpath */
	public static final String PayrollAllowanceActualAmntList = "//table[@id='5845881610404069955']//tr/td[3]/span[last()]";
	/** @locatorType Xpath */
	public static final String PayrollAllowanceAmountList = "//table[@id='5845881610404069955']//tr/td[4]/span[last()]";
	/** @locatorType Xpath */
	public static final String PayrollAdjustmentTable = "//table[@id='5845886181251070911']";
	/** @locatorType Xpath */
	public static final String PayrollAdjustmentDescriptionList = "//table[@id='5845886181251070911']//tr/td[2]/span[last()]";
	/** @locatorType Xpath */
	public static final String PayrollAdjustmentAmountList = "//table[@id='5845886181251070911']//tr/td[3]/span[last()]";
	/** @locatorType Xpath */
	public static final String PayrollTotalAdditionDisplayText = "//span[@id='P16251310506_TOT_ADD_DISPLAY']";
	/** @locatorType Xpath */
	public static final String PayrollTotalDeductionDisplayText = "//span[@id='P16251310506_TOT_DED_DISPLAY']";
	/** @locatorType Xpath */
	public static final String PayrollNetSalaryDisplayText = "//span[@id='P16251310506_NET_SAL1_DISPLAY']";
	/** @locatorType Xpath */
	public static final String ReviewCompletedBtn = "//button[@id='B3422389009058126254']";
	/** @locatorType Xpath */
	public static final String ProcessPayrollBtn = "//button[@id='B3422388658127126254']";

}
