package commonlocators.payroll;

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
	public static final String AdjustmentSaveIcon = "//button[@data-testid='Save_btn']";
	/** @locatorType Xpath */
	public static final String AdjustmentTableElementData = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//tr/td[1])[1]";
	/** @locatorType Xpath */
	public static final String AdjustmentTableElementLovBtn = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//tr/td[1])[1]//button";
	/** @locatorType Xpath */
	public static final String AdjustmentTableStartYearData = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//tr/td[5])[1]";
	/** @locatorType Xpath */
	public static final String AdjustmentTableStartYearLovBtn = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//tr/td[5])[1]//button";
	/** @locatorType Xpath */
	public static final String AdjustmentTableStartPeriodData = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//tr/td[6])[1]";
	/** @locatorType Xpath */
	public static final String AdjustmentTableStartPeriodLovBtn = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//tr/td[6])[1]//button";
	/** @locatorType Xpath */
	public static final String AdjustmentTableTotalPeriodData = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//tr/td[7])[1]";
	/** @locatorType Xpath */
	public static final String AdjustmentTableTotalPeriodInput = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//tr/td[7])[1]//input";
	/** @locatorType Xpath */
	public static final String AdjustmentTablePeriodicAmntData = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//tr/td[8])[1]";
	/** @locatorType Xpath */
	public static final String AdjustmentTablePeriodicAmntInput = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//tr/td[8])[1]//input";
	/** @locatorType Xpath */
	public static final String AdjustmentTableApproveIcon = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//tr/td[9])[1]/a";
	/** @locatorType Xpath */
	public static final String AdjustmentTableEndIcon = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//tr/td[10])[1]/a";
	/** @locatorType Xpath */
	public static final String AdjustmentTableProcessHoldData = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//tr/td[13])[1]";
	/** @locatorType Xpath */
	public static final String AdjustmentTablePeriodData = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//tr/td[14])[1]";
	/** @locatorType Xpath */
	public static final String AdjustmentTableAdjustmentNoData = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//tr/td[18])[1]";
	/** @locatorType Xpath */
	public static final String AdjustmentTableDocNoData = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//tr/td[19])[1]";
	/** @locatorType Xpath */
	public static final String AdjustmentTableProcessHoldSelect = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//tr/td[13])[1]//select";
	/** @locatorType Xpath */
	public static final String AdjustmentTableCancelIcon = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//tr/td[25])[1]/a";
	/** @locatorType Xpath */
	public static final String AdjustmentTableStatusText = "(//div[@id='ig_line_ig_grid_vc']//table/tbody//tr/td[12])[1]";
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
	public static final String FindDesignationResultTable = "//table[contains(@aria-label,'Result(s)')]//tr/td[4]";
	/** @locatorType Xpath */
	public static final String FindDepartmentResultTable = "//table[contains(@aria-label,'Result(s)')]//tr/td[5]";
	/** @locatorType Xpath */
	public static final String FindUnitResultTable = "//table[contains(@aria-label,'Result(s)')]//tr/td[6]";
	/** @locatorType Xpath */
	public static final String FindElementResultTable = "//table[contains(@aria-label,'Result(s)')]//tr/td[7]";
	/** @locatorType Xpath */
	public static final String FindYearResultTable = "//table[contains(@aria-label,'Result(s)')]//tr/td[12]";
	/** @locatorType Xpath */
	public static final String EmpAdjMigrationIframe = "//iframe[@title='Migration Type']";
	/** @locatorType Xpath */
	public static final String EmpAdjustmentMigrationRadioBtn = "//input[contains(@aria-label,'Adjustment')]/following-sibling::label";
	/** @locatorType Xpath */
	public static final String EmpAdjMigrationAddNewBtn = "//div[contains(@aria-label,'Employee Adjustment Migration')]/button[@title='Add']";
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
	public static final String EmpAllowanceMigrationAddNewBtn = "//div[contains(@aria-label,'Employee Allowances Migration')]/button[@title='Add']";
	/** @locatorType Xpath */
	public static final String EmpAllowanceMigrationDateFrom = "//input[@id='P36701362101_EAMHD_EFF_FROM_input']";
	/** @locatorType Xpath */
	public static final String EmpAllowanceMigrationDateTo = "//input[@id='P36701362101_EAMHD_EFF_TO_input']";
	/** @locatorType Xpath */
	public static final String EmpAllowanceMigrationRefInput = "//input[@id='P36701362101_EAMHD_REF']";
	/** @locatorType Xpath */
	public static final String EmpAllowanceMigrationCreateSaveIcon = "//button[@title='Save']";
	/** @locatorType Xpath */
	public static final String EmpAllowanceMigrationUploadIcon = "//button[@title='Load']";
	/** @locatorType Xpath */
	public static final String EmpAllowanceMigrationUploadInput = "//a-file-upload[contains(@id,'LOAD')]/input";
	/** @locatorType Xpath */
	public static final String EmpAllowanceMigrationUploadBtn = "//button[@id='B4374410308584209826']";
	/** @locatorType Xpath */
	public static final String EmpAllowanceMigrationCheckExcptionBtn = "//*[normalize-space()='Check Exception']/parent::button";
	/** @locatorType Xpath */
	public static final String EmpAllowanceMigrationHeaderSaveIcon = "//button[@title='Save']";
	/** @locatorType Xpath */
	public static final String EmpAllowanceMigrationPostBtn = "//*[normalize-space()='Post']/parent::button";
}
