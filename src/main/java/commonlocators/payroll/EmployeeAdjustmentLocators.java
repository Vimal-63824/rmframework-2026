package commonlocators.payroll;

import commonfunctions.CommonFunctions;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks May 13, 2025
 */
public class EmployeeAdjustmentLocators {

	/** @locatorType Xpath */
	public static final String FindEmployeeAdjustmentHeader = "//h2[text()='Find Employee Adjustments']";
	/** @locatorType Xpath */
	public static final String FindEmployeeNameLovBtn = "//input[@data-testid='EMP_ADJ_Input']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindDesignationLovBtn = "//input[@data-testid='DESI_ADJ_Input']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindDepartmentLovBtn = "//input[@data-testid='DEPT_ADJ_Input']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindUnitLovBtn = "//input[@data-testid='UNIT_ADJ_Input']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindElementLovBtn = "//input[@data-testid='ELEMNT_ADJ_Input']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindYearLovBtn = "//input[@data-testid='YEAR_ADJ_Input']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindPeriodLovBtn = "//input[@data-testid='PERIOD_ADJ_Input']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String FindSearchBtn = "//button[@data-testid='REPORT_EADJ_btn']";
	/** @locatorType Xpath */
	public static final String EA_MigrationBtn = "//button[@data-testid='Migration_btn']";
	/** @locatorType Xpath */
	public static final String TableResultFirstEmpId = "(//table[contains(@aria-label,'Result(s)')]//tr/td[2])[1]";
	/** @locatorType Xpath */
	public static final String AdjustmentDisplayEmpId = "//span[@data-testid='EMPAI_EMP_ID_Input']";
	/** @locatorType Xpath */
	public static final String AdjustmentAddIcon = "//button[@data-testid='Add_btn']";
	/** @locatorType Xpath */
	public static final String AdjustmentSaveIcon = "//button[@title='Save']";
	/** @locatorType Xpath */
	public static final String AdjustmentAddTableLocator = "(//div[@id='ig_line_ig_grid_vc']//table)[2]";

	/** @locatorType Xpath */
	public static String AdjustmentTableElementData() {
		return checkAndReturnIndexForEmployeeAdjustmentAddLocatorWithHeaderValue(AdjustmentAddTableLocator, "Element");
	}

	/** @locatorType Xpath */
	public static String AdjustmentTableElementLovBtn() {
		return checkAndReturnIndexForEmployeeAdjustmentAddLocatorWithHeaderValue(AdjustmentAddTableLocator, "Element")
				+ "//button";
	}

	/** @locatorType Xpath */
	public static String AdjustmentTableStartYearData() {
		return checkAndReturnIndexForEmployeeAdjustmentAddLocatorWithHeaderValue(AdjustmentAddTableLocator,
				"Start Year");
	}

	/** @locatorType Xpath */
	public static String AdjustmentTableStartYearLovBtn() {
		return checkAndReturnIndexForEmployeeAdjustmentAddLocatorWithHeaderValue(AdjustmentAddTableLocator,
				"Start Year") + "//button";
	}

	/** @locatorType Xpath */
	public static String AdjustmentTableStartPeriodData() {
		return checkAndReturnIndexForEmployeeAdjustmentAddLocatorWithHeaderValue(AdjustmentAddTableLocator,
				"Start Period");
	}

	/** @locatorType Xpath */
	public static String AdjustmentTableStartPeriodLovBtn() {
		return checkAndReturnIndexForEmployeeAdjustmentAddLocatorWithHeaderValue(AdjustmentAddTableLocator,
				"Start Period") + "//button";
	}

	/** @locatorType Xpath */
	public static String AdjustmentTableTotalPeriodData() {
		return checkAndReturnIndexForEmployeeAdjustmentAddLocatorWithHeaderValue(AdjustmentAddTableLocator,
				"Total Period");
	}

	/** @locatorType Xpath */
	public static String AdjustmentTableTotalPeriodInput() {
		return checkAndReturnIndexForEmployeeAdjustmentAddLocatorWithHeaderValue(AdjustmentAddTableLocator,
				"Total Period") + "//input";
	}

	/** @locatorType Xpath */
	public static String AdjustmentTablePeriodicAmntData() {
		return checkAndReturnIndexForEmployeeAdjustmentAddLocatorWithHeaderValue(AdjustmentAddTableLocator,
				"Periodic Amount");
	}

	/** @locatorType Xpath */
	public static String AdjustmentTablePeriodicAmntInput() {
		return checkAndReturnIndexForEmployeeAdjustmentAddLocatorWithHeaderValue(AdjustmentAddTableLocator,
				"Periodic Amount") + "//input";
	}

	/** @locatorType Xpath */
	public static String AdjustmentTableApproveIcon() {
		return checkAndReturnIndexForEmployeeAdjustmentAddLocatorWithHeaderValue(AdjustmentAddTableLocator, "Approved")
				+ "/a";
	}

	/** @locatorType Xpath */
	public static String AdjustmentTableEndIcon() {
		return checkAndReturnIndexForEmployeeAdjustmentAddLocatorWithHeaderValue(AdjustmentAddTableLocator, "End")
				+ "/a";
	}

	/** @locatorType Xpath */
	public static String AdjustmentTableProcessHoldData() {
		return checkAndReturnIndexForEmployeeAdjustmentAddLocatorWithHeaderValue(AdjustmentAddTableLocator,
				"Process / Hold");
	}

	/** @locatorType Xpath */
	public static String AdjustmentTablePeriodData() {
		return checkAndReturnIndexForEmployeeAdjustmentAddLocatorWithHeaderValue(AdjustmentAddTableLocator, "Period");
	}

	/** @locatorType Xpath */
	public static String AdjustmentTableAdjustmentNoData() {
		return checkAndReturnIndexForEmployeeAdjustmentAddLocatorWithHeaderValue(AdjustmentAddTableLocator,
				"Adjustment No.");
	}

	/** @locatorType Xpath */
	public static String AdjustmentTableDocNoData() {
		return checkAndReturnIndexForEmployeeAdjustmentAddLocatorWithHeaderValue(AdjustmentAddTableLocator, "Doc. No.");
	}

	/** @locatorType Xpath */
	public static String AdjustmentTableProcessHoldSelect() {
		return checkAndReturnIndexForEmployeeAdjustmentAddLocatorWithHeaderValue(AdjustmentAddTableLocator,
				"Process / Hold") + "//select";
	}

	/** @locatorType Xpath */
	public static String AdjustmentTableCancelIcon() {
		return checkAndReturnIndexForEmployeeAdjustmentAddLocatorWithHeaderValue(AdjustmentAddTableLocator, "Cancel")
				+ "/a";
	}

	/** @locatorType Xpath */
	public static String AdjustmentTableStatusText() {
		return checkAndReturnIndexForEmployeeAdjustmentAddLocatorWithHeaderValue(AdjustmentAddTableLocator, "Status");
	}

	/** @locatorType Xpath */
	public static final String AdjustmentTableCancelOkBtn = "//button[text()='OK']";
	/** @locatorType Xpath */
	public static final String EndNarrationIframe = "//iframe[@title='Narration']";
	/** @locatorType Xpath */
	public static final String EndNarrationInputBox = "//div[contains(@id,'NARRATION_CONTAINER')]//input[contains(@id,'NARRATION')]";
	/** @locatorType Xpath */
	public static final String EndNarrationTickIcon = "//button[@title='Ok']";
	/** @locatorType Xpath */
	public static final String AdjustmentReportBtn = "//button[@data-testid='Print_btn']";
	/** @locatorType Xpath */
	public static final String FindLinesEmployeeAdjustmentScreen = "//table[contains(@aria-label,'Result(s)')]//tr/th";

	/** @locatorType Xpath */
	public static String FindDesignationResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindLinesEmployeeAdjustmentScreen, "Designation");
	}

	/** @locatorType Xpath */
	public static String FindDepartmentResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindLinesEmployeeAdjustmentScreen, "Department");
	}

	/** @locatorType Xpath */
	public static String FindUnitResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindLinesEmployeeAdjustmentScreen, "Unit");
	}

	/** @locatorType Xpath */
	public static String FindElementResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindLinesEmployeeAdjustmentScreen, "Element");
	}

	/** @locatorType Xpath */
	public static String FindYearResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindLinesEmployeeAdjustmentScreen, "Year");
	}

	/** @locatorType Xpath */
	public static final String EmpAdjMigrationIframe = "//iframe[contains(@title,'Migration Type')]";
	/** @locatorType Xpath */
	public static final String EmpAdjustmentMigrationRadioBtn = "//input[contains(@aria-label,'Adjustment')]/following-sibling::label";
	/** @locatorType Xpath */
	public static final String EmpAdjMigrationAddNewBtn = "//div[contains(@aria-label,'Employee Adjustment Migration')]//button[@title='Add']";
	/** @locatorType Xpath */
	public static final String EmpAdjMigrationRefInput = "//input[@data-testid='EAJMHD_REF_Input']";
	/** @locatorType Xpath */
	public static final String EmpAdjMigrationModeSelect = "//select[@data-testid='EAJMHD_TYPE_Input']";
	/** @locatorType Xpath */
	public static final String EmpAdjMigrationCreateSaveIcon = "//button[@data-testid='EmpAdjust_SAVEbtn']";
	/** @locatorType Xpath */
	public static final String EmpAdjMigrationUploadIcon = "//button[@data-testid='EmpAdjust_Upload_01btn']";
	/** @locatorType Xpath */
	public static final String EmpAdjMigrationUploadInput = "//a-file-upload[contains(@id,'LOAD')]/input";
	/** @locatorType Xpath */
	public static final String EmpAdjMigrationUploadBtn = "//button[@data-testid='EmpAdjust_Uploadbtn']";
	/** @locatorType Xpath */
	public static final String EmpAdjMigrationCheckExcptionBtn = "//button[@data-testid='EmpAdjust_Chk_exe_Nbtn']";
	/** @locatorType Xpath */
	public static final String EmpAdjMigrationHeaderSaveIcon = "//button[@data-testid='EmpAdjust_UPDATEbtn']";
	/** @locatorType Xpath */
	public static final String EmpAdjMigrationPostBtn = "//button[@data-testid='EmpAdjust_Postbtn']";
	/** @locatorType Xpath */
	public static final String EmpAllowanceMigrationRadioBtn = "//input[contains(@aria-label,'Allowance')]/following-sibling::label";
	/** @locatorType Xpath */
	public static final String EmpAllowanceMigrationAddNewBtn = "//div[contains(@aria-label,'Employee Allowances Migration')]//button[@title='Add']";
	/** @locatorType Xpath */
	public static final String EmpAllowanceMigrationDateFrom = "//input[contains(@id,'EAMHD_EFF_FROM_input')]";
	/** @locatorType Xpath */
	public static final String EmpAllowanceMigrationDateTo = "//input[contains(@id,'EAMHD_EFF_TO_input')]";
	/** @locatorType Xpath */
	public static final String EmpAllowanceMigrationRefInput = "//input[contains(@id,'EAMHD_REF')]";
	/** @locatorType Xpath */
	public static final String EmpAllowanceMigrationCreateSaveIcon = "//button[@title='Save']";
	/** @locatorType Xpath */
	public static final String EmpAllowanceMigrationUploadIcon = "//button[@title='Load']";
	/** @locatorType Xpath */
	public static final String EmpAllowanceMigrationUploadInput = "//a-file-upload[contains(@id,'LOAD')]/input";
	/** @locatorType Xpath */
	public static final String EmpAllowanceMigrationUploadBtn = "//span[text()='Upload']/parent::button";
	/** @locatorType Xpath */
	public static final String EmpAllowanceMigrationCheckExcptionBtn = "//*[normalize-space()='Check Exception']/parent::button";
	/** @locatorType Xpath */
	public static final String EmpAllowanceMigrationHeaderSaveIcon = "//button[@title='Save']";
	/** @locatorType Xpath */
	public static final String EmpAllowanceMigrationPostBtn = "//*[normalize-space()='Post']/parent::button";

	/**
	 * This method used to check header with text and return index values
	 * dynamically in add tables
	 * 
	 * @author Sakthi-GWL
	 * @param Locator
	 * @param HeaderValue
	 * @return String Locator
	 */
	public static String checkAndReturnIndexForEmployeeAdjustmentAddLocatorWithHeaderValue(String Locator,
			String HeaderValue) {
		Locator = Locator + "/tbody/tr/*[count(" + Locator + "/thead/tr[2]/th[normalize-space()='" + HeaderValue
				+ "']/preceding-sibling::th) + 1]";
		return Locator;
	}

}
