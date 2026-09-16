package commonlocators.payroll;

import commonfunctions.CommonFunctions;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 5, 2025
 */
public class EmployeeMasterLocators {
	/** @locatorType XPATH */
	public static final String EmployeeMasterLabel = "//input[@aria-label='Employee Master']/following-sibling::label";
	/** @locatorType XPATH */
	public static final String EmployeeMasterAddIcon = "//*[@data-testid=' EmployeeMasterAddIcon']";
	/** @locatorType XPATH */
	public static final String AddEmp_EmpployeeMasterSideNav = "//*[@data-testid=' AddEmp_EmpployeeMasterSideNav']";
	/** @locatorType XPATH */
	public static final String AddEmp_QualificationSkillsSideNav = "//*[@data-testid=' AddEmp_QualificationSkillsSideNav']";
	/** @locatorType XPATH */
	public static final String AddEmp_SalarySideNav = "//*[@data-testid=' AddEmp_SalarySideNav']";
	/** @locatorType XPATH */
	public static final String AddEmp_LeaveDetailsSideNav = "//*[@data-testid=' AddEmp_LeaveDetailsSideNav']";
	/** @locatorType XPATH */
	public static final String AddEmp_DependentSideNav = "//*[@data-testid=' AddEmp_DependentSideNav']";
	/** @locatorType XPATH */
	public static final String AddEmp_SelectSalution = "//*[@data-testid=' AddEmp_SelectSalution']";
	/** @locatorType XPATH */
	public static final String AddEmp_SelectGender = "//*[@data-testid=' AddEmp_SelectGender']";
	/** @locatorType XPATH */
	public static final String AddEmp_SelectShift = "//*[@data-testid=' AddEmp_SelectShift']";
	/** @locatorType XPATH */
	public static final String AddEmp_FirstName = "//*[@data-testid=' AddEmp_FirstName']";
	/** @locatorType XPATH */
	public static final String AddEmp_LastName = "//*[@data-testid=' AddEmp_LastName']";
	/** @locatorType XPATH */
	public static final String AddEmp_DOBInput = "//*[@data-testid=' AddEmp_DOBInput']/input";
	/** @locatorType XPATH */
	public static final String AddEmp_DOJInput = "//*[@data-testid=' AddEmp_DOJInput']/input";
	/** @locatorType XPATH */
	public static final String AddEmp_ChooseCalender = "//*[@data-testid=' AddEmp_ChooseCalender']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String AddEmp_ChooseZone = "//*[@data-testid=' AddEmp_ChooseZone']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String AddEmp_CompanyCategry = "//*[@data-testid=' AddEmp_CompanyCategry']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String AddEmp_AccountCategry = "//*[@data-testid=' AddEmp_AccountCategry']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String AddEmp_PayrollGroupCategry = "//*[@data-testid=' AddEmp_PayrollGroupCategry']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String AddEmp_NoticePeriod = "//*[@data-testid=' AddEmp_NoticePeriod']";
	/** @locatorType XPATH */
	public static final String AddEmp_SaveButton = "//*[@data-testid=' AddEmp_SaveButton']";
	/** @locatorType XPATH */
	public static final String AddEmp_DisplayEmpID = "//div[@id='emp_dtl']//b";
	/** @locatorType XPATH */
	public static final String AddEmp_ContactAddLinesIcon = "//button[@data-testid=' AddEmp_ContactSaveLinesIcon']/preceding-sibling::button";
	/** @locatorType XPATH */
	public static final String AddEmp_QualificationAddLinesIcon = "//*[@data-testid=',AddEmp_QualificationAddLinesIcon']";
	/** @locatorType XPATH */
	public static final String AddEmp_SkillAddLinesIcon = "//*[@data-testid=' AddEmp_SkillAddLinesIcon']";
	/** @locatorType XPATH */
	public static final String AddEmp_ContactSaveLinesIcon = "//*[@data-testid=' AddEmp_ContactSaveLinesIcon']";
	/** @locatorType XPATH */
	public static final String AddEmp_QualificationSaveLinesIcon = "//*[@data-testid=' AddEmp_QualificationSaveLinesIcon']";
	/** @locatorType XPATH */
	public static final String AddEmp_SkillSaveLinesIcon = "//*[@data-testid=' AddEmp_SkillSaveLinesIcon']";
	/** @locatorType XPATH */
	public static final String AddEmpContactDetailsTable = "(//*[text()='Contact Name']/ancestor::table)[2]";
	/** @locatorType XPATH */
	public static final String AddEmpQualificationDetailsTable = "(//*[text()='Qualification']/ancestor::table)[2]";
	/** @locatorType XPATH */
	public static final String AddEmpSkillsDetailsTable = "(//*[text()='Skill']/ancestor::table)[2]";

	/** @locatorType XPATH */
	public static String AddEmp_ContactNameData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpContactDetailsTable, "ContactName");
	}

	/** @locatorType XPATH */
	public static String AddEmp_ContactNameDataInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpContactDetailsTable, "ContactName")
				+ "//input";
	}

	/** @locatorType XPATH */
	public static String AddEmp_RelationshipData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpContactDetailsTable, "Relationship");
	}

	/** @locatorType XPATH */
	public static String AddEmp_RelationshipDataInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpContactDetailsTable, "Relationship")
				+ "//input";
	}

	/** @locatorType XPATH */
	public static String AddEmp_MobileNoData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpContactDetailsTable, "MobileNo");
	}

	/** @locatorType XPATH */
	public static String AddEmp_MobileNoDataInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpContactDetailsTable, "MobileNo")
				+ "//input";
	}

	/** @locatorType XPATH */
	public static String AddEmp_AddressData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpContactDetailsTable, "Address");
	}

	/** @locatorType XPATH */
	public static String AddEmp_AddressDataInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpContactDetailsTable, "Address")
				+ "//input";
	}

	/** @locatorType XPATH */
	public static final String AddEmp_City() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpContactDetailsTable, "City");
	}

	/** @locatorType XPATH */
	public static String AddEmp_CityLovBtn() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpContactDetailsTable, "City")
				+ "//button";
	}

	/** @locatorType XPATH */
	public static String AddEmp_StateData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpContactDetailsTable, "State");
	}

	/** @locatorType XPATH */
	public static final String AddEmp_StateDataInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpContactDetailsTable, "State")
				+ "//input";
	}

	/** @locatorType XPATH */
	public static String AddEmp_Qualification() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpQualificationDetailsTable,
				"Qualification");
	}

	/** @locatorType XPATH */
	public static String AddEmp_QualificationLovBtn() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpQualificationDetailsTable,
				"Qualification") + "//button";
	}

	/** @locatorType XPATH */
	public static String AddEmp_BranchOfStudy() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpQualificationDetailsTable,
				"BranchOfStudy");
	}

	/** @locatorType XPATH */
	public static String AddEmp_BranchOfStudyLovBtn() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpQualificationDetailsTable,
				"BranchOfStudy") + "//button";
	}

	/** @locatorType XPATH */
	public static String AddEmp_InstituteData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpQualificationDetailsTable,
				"Institute");
	}

	/** @locatorType XPATH */
	public static String AddEmp_InstituteDataInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpQualificationDetailsTable,
				"Institute") + "//input";
	}

	/** @locatorType XPATH */
	public static String AddEmp_TotalYearsData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpQualificationDetailsTable,
				"TotalYears");
	}

	/** @locatorType XPATH */
	public static String AddEmp_TotalYearsDataInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpQualificationDetailsTable,
				"TotalYears") + "//input";
	}

	/** @locatorType XPATH */
	public static String AddEmp_YearOfPassingData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpQualificationDetailsTable,
				"YearsOfPassing");
	}

	/** @locatorType XPATH */
	public static String AddEmp_YearOfPassingDataInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpQualificationDetailsTable,
				"YearsOfPassing") + "//input";
	}

	/** @locatorType XPATH */
	public static String AddEmp_GradeClassData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpQualificationDetailsTable,
				"GradeClass");
	}

	/** @locatorType XPATH */
	public static String AddEmp_GradeClassDataInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpQualificationDetailsTable,
				"GradeClass") + "//input";
	}

	/** @locatorType XPATH */
	public static String AddEmp_AggData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpQualificationDetailsTable,
				"AggregatePercent");
	}

	/** @locatorType XPATH */
	public static String AddEmp_AggDataInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpQualificationDetailsTable,
				"AggregatePercent") + "//input";
	}

	/** @locatorType XPATH */
	public static String AddEmp_SkillData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpSkillsDetailsTable, "Skill");
	}

	/** @locatorType XPATH */
	public static String AddEmp_SkillDataLov() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpSkillsDetailsTable, "Skill")
				+ "//button";
	}

	/** @locatorType XPATH */
	public static String AddEmp_LevelData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpSkillsDetailsTable, "Level");
	}

	/** @locatorType XPATH */
	public static String AddEmp_LevelDataLov() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpSkillsDetailsTable, "Level")
				+ "//button";
	}

	/** @locatorType XPATH */
	public static final String AddEmp_DesignationLovBtn = "//*[@data-testid=' AddEmp_DesignationLovBtn']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String AddEmp_DepartmentLovBtn = "//*[@data-testid=' AddEmp_DepartmentLovBtn']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String AddEmp_GradeLovBtn = "//*[@data-testid=' AddEmp_GradeLovBtn']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String AddEmp_EffectiveFromInput = "//*[@data-testid=' AddEmp_EffectiveFromInput']";
	/** @locatorType XPATH */
	public static final String AddEmp_PF_ElegibleLabel = "//*[@data-testid=' AddEmp_PF_ElegibleInputBox']/following-sibling::label";
	/** @locatorType XPATH */
	public static final String AddEmp_PF_ElegibleInputBox = "//*[@data-testid=' AddEmp_PF_ElegibleInputBox']";
	/** @locatorType XPATH */
	public static final String AddEmp_ESI_ElegibleLabel = "//*[@data-testid=' AddEmp_ESI_ElegibleInputBox']/following-sibling::label";
	/** @locatorType XPATH */
	public static final String AddEmp_ESI_ElegibleInputBox = "//*[@data-testid=' AddEmp_ESI_ElegibleInputBox']";
	/** @locatorType XPATH */
	public static final String AddEmp_PT_ElegibleLabel = "//*[contains(@id,'PT_ELGBL_FLAG')]/following-sibling::label";
	/** @locatorType XPATH */
	public static final String AddEmp_PT_ElegibleInputBox = "//input[@aria-label='PT Eligible']";
	/** @locatorType XPATH */
	public static final String AddEmp_OT_ElegibleLabel = "//*[@data-testid=' AddEmp_OT_ElegibleLabel']/following-sibling::label";
	/** @locatorType XPATH */
	public static final String AddEmp_LoadAllowanceBtn = "//*[@data-testid=' AddEmp_LoadAllowanceBtn']";
	/** @locatorType XPATH */
	public static final String AddEmp_SaveAllowanceTableBtn = "//b[text()='Allowances']/../../following-sibling::div/button[@title='Save']";
	/** @locatorType XPATH */
	public static final String AddEmpAllowanceDetailsTable = "(//div[contains(@aria-label,'Allowances')]//table)[2]";
	/** @locatorType XPATH */
	public static final String AddEmpAdjustmentsDetailsTable = "(//div[contains(@aria-label,'Adjustments')]//table)[2]";

	/** @locatorType XPATH */
	public static String AddEmp_AllowanceTableAmountData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpAllowanceDetailsTable,
				"AllowanceAmount");
	}

	/** @locatorType XPATH */
	public static String AddEmp_AllowanceTableTotalAmountData() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpAllowanceDetailsTable,
				"AllowanceAmount") + ")[last()]";
	}

	/** @locatorType XPATH */
	public static String AddEmp_AllowanceTableAmountDataInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpAllowanceDetailsTable,
				"AllowanceAmount") + "//input";
	}

	/** @locatorType XPATH */
	public static String AddEmp_AllowanceTableFirstTrashIcon() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpAllowanceDetailsTable,
				"AllowanceTrash") + "//span[@class='fa fa-trash'])[1]";
	}

	/** @locatorType XPATH */
	public static final String AddEmp_AdjustmentAddIcon = "//*[@data-testid=' AddEmp_AdjustmentAddIcon']";
	/** @locatorType XPATH */
	public static final String AddEmp_AdjustmentElementLovBtn = "//*[@data-testid=' AddEmp_AdjustmentElementLovBtn']/following-sibling::button";

	/** @locatorType XPATH */
	public static String AddEmp_AdjustmentStartYearData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpAdjustmentsDetailsTable,
				"AdjustmentStartYear");
	}

	/** @locatorType XPATH */
	public static final String AddEmp_AdjustmentStartYearSelect = "//*[@data-testid=' AddEmp_AdjustmentStartYearData']";

	/** @locatorType XPATH */
	public static String AddEmp_AdjustmentPeriodData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpAdjustmentsDetailsTable,
				"AdjustmentPeriod");
	}

	/** @locatorType XPATH */
	public static final String AddEmp_AdjustmentPeriodSelect = "//*[@data-testid=' AddEmp_AdjustmentPeriodData']";

	/** @locatorType XPATH */
	public static String AddEmp_AdjustmentTotalPeriodData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpAdjustmentsDetailsTable,
				"AdjustmentTotalPeriod");
	}

	/** @locatorType XPATH */
	public static final String AddEmp_AdjustmentPeriodAmountData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpAdjustmentsDetailsTable,
				"AdjustmentPeriodAmount");
	}

	/** @locatorType XPATH */
	public static String AddEmp_AdjustmentTotalPeriodDataInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpAdjustmentsDetailsTable,
				"AdjustmentTotalPeriod") + "//input";
	}

	/** @locatorType XPATH */
	public static String AddEmp_AdjustmentPeriodAmountDataInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpAdjustmentsDetailsTable,
				"AdjustmentPeriodAmount") + "//input";
	}

	/** @locatorType XPATH */
	public static String AddEmp_AdjustmentActivateIcon() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpAdjustmentsDetailsTable,
				"AdjustmentActiveIcon") + "//span";
	}

	/** @locatorType XPATH */
	public static final String AddEmp_AdjustmentOkBtn = "//button[text()='OK']";
	/** @locatorType XPATH */
	public static final String AddEmp_AdjustmentTableFirstTrashIcon = "((//b[text()='Adjustments']/following::table)[2]//tr/td//span[@class='fa fa-trash'])[1]";
	/** @locatorType XPATH */
	public static final String AddEmp_AfterCreationSaveIcon = "//*[@data-testid='BTN_save_sal_emp_empIcon']";
	/** @locatorType XPATH */
	public static final String AddEmp_AfterCreationSaveIcon2 = "//*[@data-testid='BTN_Save_emp_dtl_empIcon']";
	/** @locatorType XPATH */
	public static final String AddEmp_SalaryAdjustmentSaveBtn = "//*[@data-testid=' AddEmp_SalaryAdjustmentSaveBtn']";
	/** @locatorType XPATH */
	public static final String AddEmp_LeaveEligibilityAddBtn = "//*[@data-testid=' AddEmp_LeaveEligibilityAddBtn']";
	/** @locatorType XPATH */
	public static final String AddEmpLeaveDetailsTable = "(//*[text()='Leave']/ancestor::table)[2]";

	/** @locatorType XPATH */
	public static String AddEmp_LeaveEligibilityLeaveLovBtn() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpLeaveDetailsTable,
				"LeaveEligibleLeave") + "//button";
	}

	/** @locatorType XPATH */
	public static String AddEmp_LeaveEligibilityLeaveData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpLeaveDetailsTable,
				"LeaveEligibleLeave");
	}

	/** @locatorType XPATH */
	public static String AddEmp_LeaveEligibilityDescription() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpLeaveDetailsTable,
				"LeaveEligibleDesc");
	}

	/** @locatorType XPATH */
	public static String AddEmp_LeaveEligibilityDaysData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpLeaveDetailsTable,
				"LeaveEligibleDays");
	}

	/** @locatorType XPATH */
	public static String AddEmp_LeaveEligibilityDaysInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpLeaveDetailsTable,
				"LeaveEligibleDays") + "//input";
	}

	/** @locatorType XPATH */
	public static String AddEmp_LeaveEligibilityEveryData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpLeaveDetailsTable,
				"LeaveEligibleEvery");
	}

	/** @locatorType XPATH */
	public static String AddEmp_LeaveEligibilityEverySelect() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpLeaveDetailsTable,
				"LeaveEligibleEvery") + "//select";
	}

	/** @locatorType XPATH */
	public static String AddEmp_LeaveEligibilityEligibleDaysData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpLeaveDetailsTable,
				"LeaveEligible-EligibleDays");
	}

	/** @locatorType XPATH */
	public static String AddEmp_LeaveEligibilityEligibleDaysInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpLeaveDetailsTable,
				"LeaveEligible-EligibleDays") + "//input";
	}

	/** @locatorType XPATH */
	public static String AddEmp_LeaveEligibilityRoundOffData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpLeaveDetailsTable,
				"LeaveEligibleRoundOff");
	}

	/** @locatorType XPATH */
	public static String AddEmp_LeaveEligibilityRoundOffSelect() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpLeaveDetailsTable,
				"LeaveEligibleRoundOff") + "//select";
	}

	/** @locatorType XPATH */
	public static String AddEmp_LeaveEligibilityMaxCarryFreqData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpLeaveDetailsTable,
				"LeaveEligibleMaxCarryFreq");
	}

	/** @locatorType XPATH */
	public static String AddEmp_LeaveEligibilityMaxCarryFreqSelect() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpLeaveDetailsTable,
				"LeaveEligibleMaxCarryFreq") + "//select";
	}

	/** @locatorType XPATH */
	public static String AddEmp_LeaveEligibilityMaxCarryDayData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpLeaveDetailsTable,
				"LeaveEligibleMaxCarryDay");
	}

	/** @locatorType XPATH */
	public static String AddEmp_LeaveEligibilityMaxCarryDayInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpLeaveDetailsTable,
				"LeaveEligibleMaxCarryDay") + "//input";
	}

	/** @locatorType XPATH */
	public static String AddEmp_LeaveEligibilityEffYearData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpLeaveDetailsTable,
				"LeaveEligibleEffectiveYear");
	}

	/** @locatorType XPATH */
	public static String AddEmp_LeaveEligibilityEffYearSelect() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpLeaveDetailsTable,
				"LeaveEligibleEffectiveYear") + "//select";
	}

	/** @locatorType XPATH */
	public static String AddEmp_LeaveEligibilityMaxDaysData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpLeaveDetailsTable,
				"LeaveEligibleMaxDays");
	}

	/** @locatorType XPATH */
	public static String AddEmp_LeaveEligibilityMaxDaysInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpLeaveDetailsTable,
				"LeaveEligibleMaxDays") + "//input";
	}

	/** @locatorType XPATH */
	public static String AddEmp_LeaveEligibilityMinDaysData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpLeaveDetailsTable,
				"LeaveEligibleMinDays");
	}

	/** @locatorType XPATH */
	public static String AddEmp_LeaveEligibilityMinDaysInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpLeaveDetailsTable,
				"LeaveEligibleMinDays") + "//input";
	}

	/** @locatorType XPATH */
	public static final String AddEmp_LeaveEligibilitySaveIcon = "//*[@data-testid=' AddEmp_LeaveEligibilitySaveIcon']";
	/** @locatorType XPATH */
	public static final String AddEmployeePostButton = "//*[@data-testid=' AddEmployeePostButton']";
	/** @locatorType XPATH */
	public static final String AddEmployeeUpdateButton = "//*[@data-testid=' AddEmployeeUpdateButton']";
	/** @locatorType XPATH */
	public static final String LeaveEntitlementLists = "//table[contains(@id,'orig')]//tr/td[9]/span";
	/** @locatorType XPATH */
	public static final String AddEmp_SignUploadbtn = "//*[@data-testid='BTN_Sign_Upload_empIcon']";
	/** @locatorType XPATH */
	public static final String AddEmp_LoadIframe = "//iframe[@title='LOAD']";
	/** @locatorType XPATH */
	public static final String AddEmp_SignUploadInput = "//input[@id='P6_DM_BLOB_SG_input']";
	/** @locatorType XPATH */
	public static final String AddEmp_SignUploadFileNameText = "//span[@class='a-FileDrop-heading']";
	/** @locatorType XPATH */
	public static final String AddEmp_UploadBtn = "//span[text()='Upload']/parent::button";
	/** @locatorType XPATH */
	public static final String AddEmp_ClearUploadBtn = "//span[text()='Clear']/parent::button";
	/** @locatorType XPATH */
	public static final String AddEmp_ProfileClearIcon = "//button[@title='Clear']";
	/** @locatorType XPATH */
	public static final String AddEmp_ProfileUploadIcon = "//button[@title='Upload']";
	/** @locatorType XPATH */
	public static final String AddEmp_ProfileImg = "//div[@class='t-Card-titleWrap']//center/img";
	/** @locatorType XPATH */
	public static final String AddEmp_ProfileImgUploadInput = "//input[@id='P6_DM_BLOB_input']";
	/** @locatorType XPATH */
	public static final String AddEmp_ProfileUploadFileNameText = "//span[@class='a-FileDrop-heading']";
	/** @locatorType XPATH */
	public static final String AddEmp_CloseFrameIcon = "//h1[text()='LOAD']/following-sibling::button[@title='Close']";
	/** @locatorType XPATH */
	public static final String AddEmp_AdditionalInfoBtn = "//*[@data-testid='BTN_Additional_Info_empIcon']";
	/** @locatorType XPATH */
	public static final String AddEmp_AddInfoVehicleSelect = "//*[@data-testid='P8186111102_EMP_TWO_WHLR_FLAG_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_AddInfoVehicleNoInput = "//*[@data-testid='P8186111102_EMP_VEHICLE_NO_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_AddInfoPrevExpYear = "//*[@data-testid='P8186111102_EMP_PREV_EXP_YEARS_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_AddInfoPrevExpMonth = "//*[@data-testid='P8186111102_EMP_PREV_EXP_MONTH_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_AddInfoHouseType = "//*[@data-testid='P8186111102_EMP_HOME_TYPE_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_AddInfoStayType = "//*[@data-testid='P8186111102_EMP_STAY_TYPE_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_AddInfoOfcApplID = "//*[@data-testid='P8186111102_EMP_RECRUIT_APPL_N_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_AddInfoOfcPrevEmpID = "//*[@data-testid='P8186111102_EMP_PREV_EMP_ID_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_AddInfoOfcIDCardNo = "//*[@data-testid='P8186111102_EMP_ID_CARD_NO_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_AddInfoWorkLocationMobileOfc = "//*[@data-testid='P8186111102_EMP_OFF_MOBILE_NO_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_AddInfoWorkLocationEmailOfc = "//*[@data-testid='P8186111102_EMP_OFF_EMAIL_ID_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_AddInfoWorkLocationEmailPersonal = "//*[@data-testid='P8186111102_EMP_EMAIL_ID_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_AddInfoWorkLocationBuildingNo = "//*[@data-testid='P8186111102_EMP_BULDING_NO_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_AddInfoWorkLocationSectionNo = "//*[@data-testid='P8186111102_EMP_SECTION_NO_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_AddInfoWorkLocationRoomNo = "//*[@data-testid='P8186111102_EMP_ROOM_NO_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_AddInfoWorkLocationMobilePersonalNo = "//*[@data-testid='P8186111102_EMP_MOBILE_NO_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_AddInfoWorkLocationAlterMobileNo = "//*[@data-testid='P8186111102_EMP_ALT_MOBILE_NO_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_AddInfoWorkLocationSimCardNo = "//*[@data-testid='P8186111102_EMP_SIM_CARD_NO_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_AddInfoWorkLocationPhoneNo = "//*[@data-testid='P8186111102_EMP_PHONE_ID_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_AddInfoWorkLocationExtensionNo = "//*[@data-testid='P8186111102_EMP_EXTENSION_NO_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_AddInfoWorkLocationFaxNo = "//*[@data-testid='P8186111102_EMP_FAX_NO_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_AddInfoSaveButton = "//*[@data-testid='BTN_Add_save_additinfor']";
	/** @locatorType XPATH */
	public static final String AddEmp_AddInfoIframe = "//button[@title='Close']/following::iframe";
	/** @locatorType XPATH */
	public static final String AddEmp_DependentAddIcon = "//*[@data-testid='add_dep_Icon']";
	/** @locatorType XPATH */
	public static final String AddEmp_DependentSaveIcon = "//*[@data-testid='Save_dep_Icon']";
	/** @locatorType XPATH */
	public static final String AddEmpDependentDetailsTable = "(//*[text()='Dependent Name']/ancestor::table)[2]";

	/** @locatorType XPATH */
	public static String AddEmp_DependentNameData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpDependentDetailsTable,
				"DependentName");
	}

	/** @locatorType XPATH */
	public static String AddEmp_DependentNameInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpDependentDetailsTable,
				"DependentName") + "//input";
	}

	/** @locatorType XPATH */
	public static String AddEmp_DependentRelationshipData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpDependentDetailsTable,
				"DependentRelationship");
	}

	/** @locatorType XPATH */
	public static String AddEmp_DependentRelationshipSelect() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpDependentDetailsTable,
				"DependentRelationship") + "//select";
	}

	/** @locatorType XPATH */
	public static String AddEmp_DependentDOBData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpDependentDetailsTable,
				"DependentDOB");
	}

	/** @locatorType XPATH */
	public static String AddEmp_DependentDOBInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpDependentDetailsTable, "DependentDOB")
				+ "//input";
	}

	/** @locatorType XPATH */
	public static String AddEmp_DependentAadharNoData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpDependentDetailsTable,
				"DependentAadharNo");
	}

	/** @locatorType XPATH */
	public static String AddEmp_DependentAadharNoInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpDependentDetailsTable,
				"DependentAadharNo") + "//input";
	}

	/** @locatorType XPATH */
	public static String AddEmp_DependentEmailIdData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpDependentDetailsTable,
				"DependentEmailId");
	}

	/** @locatorType XPATH */
	public static String AddEmp_DependentEmailIdInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpDependentDetailsTable,
				"DependentEmailId") + "//input";
	}

	/** @locatorType XPATH */
	public static String AddEmp_DependentContactNoData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpDependentDetailsTable,
				"DependentContactNo");
	}

	/** @locatorType XPATH */
	public static String AddEmp_DependentContactNoInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpDependentDetailsTable,
				"DependentContactNo") + "//input";
	}

	/** @locatorType XPATH */
	public static String AddEmp_DependentMonthEarningData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpDependentDetailsTable,
				"DependentMonthEarning");
	}

	/** @locatorType XPATH */
	public static String AddEmp_DependentMonthEarningInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpDependentDetailsTable,
				"DependentMonthEarning") + "//input";
	}

	/** @locatorType XPATH */
	public static final String AddEmp_UpdateButton = "//*[@data-testid=' AddEmployeeUpdateButton']";
	/** @locatorType XPATH */
	public static final String AddEmp_UpdateIframe = "//iframe[@title='Update Employee Details']";
	/** @locatorType XPATH */
	public static final String AddEmp_UpdateParticularsTab = "//label[text()='Employee Particulars']";
	/** @locatorType XPATH */
	public static final String AddEmp_UpdateAddressTab = "//label[text()='Address']";
	/** @locatorType XPATH */
	public static final String AddEmp_UpdateFirstName = "//*[@data-testid='P9001_EE_EMP_FIRST_NAME_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_UpdateLastName = "//*[@data-testid='P9001_EE_EMP_LAST_NAME_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_UpdateGender = "//*[@data-testid='P9001_EE_EMP_GENDER_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_UpdateDOB = "//*[@data-testid='P9001_EE_EMP_DOB_iteminpu']/input";
	/** @locatorType XPATH */
	public static final String AddEmp_UpdateCalenderLovbtn = "//*[@data-testid='P9001_EE_EMP_CLNDR_ID_iteminpu']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String AddEmp_UpdateZoneLovbtn = "//*[@data-testid='P9001_EE_EMP_ZONE_iteminpu']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String AddEmp_UpdateCategoryLovbtn = "//*[@data-testid='P9001_EE_EMP_CAT_ID_iteminpu']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String AddEmp_UpdateAccountCategoryLovbtn = "//*[@data-testid='P9001_EE_EMP_ACCT_CAT_ID_iteminpu']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String AddEmp_UpdatePayrollGroupLovbtn = "//*[@data-testid='P9001_EE_EMP_GROUP_iteminpu']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String AddEmp_UpdateNoticePeriod = "//*[@data-testid='P9001_EE_EMP_NOTICE_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_UpdateShift = "//*[@data-testid='P9001_EE_EMP_SHIFT_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_UpdateMaritalStatus = "//*[@data-testid='P9001_EE_EMP_MARITAL_STATUS_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_UpdateEmpParticularSaveIcon = "//*[@data-testid='BTN_Save_Icon']";
	/** @locatorType XPATH */
	public static final String AddEmp_UpdateEmpAddressSaveIcon = "//*[@data-testid='BTN_Address_save_Icon']";
	/** @locatorType XPATH */
	public static final String AddEmp_UpdateAddress1 = "//*[@data-testid='P9001_EACR_ADDRES1_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_UpdateAddress3 = "//*[@data-testid='P9001_EACR_ADDRES3_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_UpdateAddress2 = "//*[@data-testid='P9001_EACR_ADDRES2_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_UpdateAddressZipCode = "//*[@data-testid='P9001_EACR_ZIP_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_UpdateAddressTelephone = "//*[@data-testid='P9001_EACR_TELE1_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_UpdateAddressCityLovBtn = "//*[@data-testid='P9001_EACR_CITY_iteminpu']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String AddEmp_UpdateAddressSameAsPermBtn = "//*[@data-testid='BTN_Same_addres_Icon']";

	/** @locatorType XPATH */
	public static final String LeaveEntitlementIcon(int i) {
		return "//table[contains(@id,'orig')]//tr[" + i + "]/td[9]/span";
	}

	/** @locatorType XPATH */
	public static final String LeaveTypeTableLeaveIDTexts(int i) {
		return "//table[contains(@id,'orig')]//tr[" + i + "]/td[1]/a";
	}

	/** @locatorType XPATH */
	public static final String AddEmp_UpdateIframeCloseIcon = "//iframe[@title='Update Employee Details']/preceding::button[@title='Close'][1]";

	/** @locatorType XPATH */
	public static String AddEmp_LeaveEligibilityMinEncashData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpLeaveDetailsTable,
				"LeaveEligibleMinEncash");
	}

	/** @locatorType XPATH */
	public static String AddEmp_LeaveEligibilityMinEncashInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddEmpLeaveDetailsTable,
				"LeaveEligibleMinEncash") + "//input";
	}

	/** @locatorType XPATH */
	public static final String AddEmp_LeaveEligibilityMaximizeIcon = "//div[@aria-label='Leave Eligibility']//button[@title='Maximize']";
	/** @locatorType XPATH */
	public static final String AddEmp_LeaveEligibilityRestoreIcon = "//div[@aria-label='Leave Eligibility']//button[@title='Restore']";
	/** @locatorType XPATH */
	public static final String AddEmp_SalaryAdjustmentsMaximizeIcon = "//div[contains(@aria-label,'Adjustments')]//button[@title='Maximize']";
	/** @locatorType XPATH */
	public static final String AddEmp_SalaryAdjustmentsRestoreIcon = "//div[contains(@aria-label,'Adjustments')]//button[@title='Restore']";

	/** @locatorType XPATH */
	public static final String AddEmp_LeaveEligibilityActiveIcon(String LeaveType) {
		return "//div[@id='ig_line7_ig_grid_vc']//table//tr/td[text()='" + LeaveType + "']/../td[11]//span";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_EmpParticularPostBtn = "//*[@data-testid='BTN_Post_Icon']";
	/** @locatorType XPATH */
	public static final String UpdateEmp_AddressPostBtn = "//*[@data-testid='BTN_Address_post_Icon']";
	/** @locatorType XPATH */
	public static final String AddEmp_UpdateDependentTab = "//label[text()='Dependent']";
	/** @locatorType XPATH */
	public static final String UpdateEmp_DependentAddLabel = "//div[@id='P9001_DEPE_RADIO']//label[text()='Add']";
	/** @locatorType XPATH */
	public static final String UpdateEmp_DependentModifyLabel = "//div[@id='P9001_DEPE_RADIO']//label[text()='Modify']";
	/** @locatorType XPATH */
	public static final String UpdateEmp_DependentRemoveLabel = "//div[@id='P9001_DEPE_RADIO']//label[text()='Remove']";
	/** @locatorType XPATH */
	public static final String UpdateEmp_DependentAddIcon = "//*[@data-testid='BTN_Dependent_Add_Icon']";
	/** @locatorType XPATH */
	public static final String UpdateEmp_DependentSaveIcon = "//*[@data-testid='BTN_Dependent_Save_Icon']";
	/** @locatorType XPATH */
	public static final String UpdateEmp_DependentPostBtn = "//*[@data-testid='BTN_Dependent_Post_Icon']";
	/** @locatorType XPATH */
	public static final String UpdateEmpDependentTable = "(//div[@id='ig_depent_ig_grid_vc']//table)[2]";
	/** @locatorType XPATH */
	public static final String TodayDateButton = "//button[contains(@class,'a-DatePicker--today')]";

	/** @locatorType XPATH */
	public static final String UpdateEmp_DependentNameData() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpDependentTable,
				"Dependent Name") + ")[1]";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_DependentNameInput() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpDependentTable,
				"Dependent Name") + ")[1]//input";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_DependentRelationData() {
		return "("
				+ CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpDependentTable, "Relationship")
				+ ")[1]";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_DependentRelationSelect() {
		return "("
				+ CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpDependentTable, "Relationship")
				+ ")[1]//select";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_DependentDOBData() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpDependentTable, "DOB")
				+ ")[1]";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_DependentDOBInput() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpDependentTable, "DOB")
				+ ")[1]//input";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_DependentAadharData() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpDependentTable, "Aadhar No")
				+ ")[1]";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_DependentAadharInput() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpDependentTable, "Aadhar No")
				+ ")[1]//input";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_DependentEmailData() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpDependentTable, "E-mail ID")
				+ ")[1]";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_DependentEmailInput() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpDependentTable, "E-mail ID")
				+ ")[1]//input";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_DependentContactData() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpDependentTable, "Contact No")
				+ ")[1]";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_DependentContactInput() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpDependentTable, "Contact No")
				+ ")[1]//input";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_DependentMonthlyEarnData() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpDependentTable,
				"Monthly Earning") + ")[1]";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_DependentMonthlyEarnInput() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpDependentTable,
				"Monthly Earning") + ")[1]//input";
	}

	/** @locatorType XPATH */
	public static final String AddEmp_UpdateContactTab = "//label[text()='Contacts']";
	/** @locatorType XPATH */
	public static final String UpdateEmp_ContactAddLabel = "//div[@id='P9001_CONT_RADIO']//label[text()='Add']";
	/** @locatorType XPATH */
	public static final String UpdateEmp_ContactModifyLabel = "//div[@id='P9001_CONT_RADIO']//label[text()='Modify']";
	/** @locatorType XPATH */
	public static final String UpdateEmp_ContactRemoveLabel = "//div[@id='P9001_CONT_RADIO']//label[text()='Remove']";
	/** @locatorType XPATH */
	public static final String UpdateEmp_ContactAddIcon = "//*[@data-testid='BTN_Contact_Add_Icon']";
	/** @locatorType XPATH */
	public static final String UpdateEmp_ContactSaveIcon = "//*[@data-testid='BTN_Contact_Save_Icon']";
	/** @locatorType XPATH */
	public static final String UpdateEmp_ContactPostBtn = "//*[@data-testid='BTN_Contact_Post_Icon']";
	/** @locatorType XPATH */
	public static final String UpdateEmpContactTable = "(//div[@id='ig_contact_ig_grid_vc']//table)[2]";

	/** @locatorType XPATH */
	public static final String UpdateEmp_ContactNameData() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpContactTable, "Contact Name")
				+ ")[1]";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_ContactNameInput() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpContactTable, "Contact Name")
				+ ")[1]//input";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_ContactRelationData() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpContactTable, "Relationship")
				+ ")[1]";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_ContactRelationInput() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpContactTable, "Relationship")
				+ ")[1]//input";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_ContactMobileData() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpContactTable, "Mobile No")
				+ ")[1]";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_ContactMobileInput() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpContactTable, "Mobile No")
				+ ")[1]//input";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_ContactAddressData() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpContactTable, "Addres")
				+ ")[1]";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_ContactAddressInput() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpContactTable, "Addres")
				+ ")[1]//input";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_ContactCityData() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpContactTable, "City")
				+ ")[1]";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_ContactCityLovBtn() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpContactTable, "City")
				+ ")[1]//button";
	}

	/** @locatorType XPATH */
	public static final String AddEmp_UpdateQualificationTab = "//label[text()='Qualification']";
	/** @locatorType XPATH */
	public static final String UpdateEmp_QualificationAddLabel = "//div[@id='P9001_QUALI_RADIO']//label[text()='Add']";
	/** @locatorType XPATH */
	public static final String UpdateEmp_QualificationModifyLabel = "//div[@id='P9001_QUALI_RADIO']//label[text()='Modify']";
	/** @locatorType XPATH */
	public static final String UpdateEmp_QualificationRemoveLabel = "//div[@id='P9001_QUALI_RADIO']//label[text()='Remove']";
	/** @locatorType XPATH */
	public static final String UpdateEmp_QualificationAddIcon = "//*[@data-testid='BTN_Qualification_Add_Icon']";
	/** @locatorType XPATH */
	public static final String UpdateEmp_QualificationSaveIcon = "//*[@data-testid='BTN_Qualification_Save_Icon']";
	/** @locatorType XPATH */
	public static final String UpdateEmp_QualificationPostBtn = "//*[@data-testid='BTN_Qulification_Post_Icon']";
	/** @locatorType XPATH */
	public static final String UpdateEmpQualificationTable = "(//div[@id='ig_qualification_ig_grid_vc']//table)[2]";

	/** @locatorType XPATH */
	public static final String UpdateEmp_QualificationTableData() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpQualificationTable,
				"Qualification") + ")[1]";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_QualificationTableLovBtn() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpQualificationTable,
				"Qualification") + ")[1]//button";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_QualBranchOfStudyData() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpQualificationTable,
				"Branch of Stduy") + ")[1]";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_QualBranchOfStudyLovBtn() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpQualificationTable,
				"Branch of Stduy") + ")[1]//button";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_QualInstituteData() {
		return "("
				+ CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpQualificationTable, "Institute")
				+ ")[1]";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_QualInstituteInput() {
		return "("
				+ CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpQualificationTable, "Institute")
				+ ")[1]//input";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_QualTotalYearData() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpQualificationTable,
				"Total Year") + ")[1]";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_QualTotalYearInput() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpQualificationTable,
				"Total Year") + ")[1]//input";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_QualYearOfPassData() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpQualificationTable,
				"Year of Passing") + ")[1]";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_QualYearOfPassInput() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpQualificationTable,
				"Year of Passing") + ")[1]//input";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_QualGradeData() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpQualificationTable, "Grade")
				+ ")[1]";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_QualGradeInput() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpQualificationTable, "Grade")
				+ ")[1]//input";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_QualAggregateData() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpQualificationTable,
				"Aggregate Pct(%)") + ")[1]";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_QualAggregateInput() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpQualificationTable,
				"Aggregate Pct(%)") + ")[1]//input";
	}

	/** @locatorType XPATH */
	public static final String AddEmp_UpdateSkillTab = "//label[text()='Skills']";
	/** @locatorType XPATH */
	public static final String UpdateEmp_SkillAddLabel = "//div[@id='P9001_SKILL_RADIO']//label[text()='Add']";
	/** @locatorType XPATH */
	public static final String UpdateEmp_SkillModifyLabel = "//div[@id='P9001_SKILL_RADIO']//label[text()='Modify']";
	/** @locatorType XPATH */
	public static final String UpdateEmp_SkillRemoveLabel = "//div[@id='P9001_SKILL_RADIO']//label[text()='Remove']";
	/** @locatorType XPATH */
	public static final String UpdateEmp_SkillAddIcon = "//*[@data-testid='BTN_Skill_Add_Icon']";
	/** @locatorType XPATH */
	public static final String UpdateEmp_SkillSaveIcon = "//*[@data-testid='BTN_Skill_Save_Icon']";
	/** @locatorType XPATH */
	public static final String UpdateEmp_SkillPostBtn = "//*[@data-testid='BTN_Skill_Post_Icon']";
	/** @locatorType XPATH */
	public static final String UpdateEmpSkillTable = "(//div[@id='ig_skill_ig_grid_vc']//table)[2]";

	/** @locatorType XPATH */
	public static final String UpdateEmp_SkillTableData() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpSkillTable, "Skill")
				+ ")[1]";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_SkillTableLovBtn() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpSkillTable, "Skill")
				+ ")[1]//button";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_SkillLevelData() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpSkillTable, "Level")
				+ ")[1]";
	}

	/** @locatorType XPATH */
	public static final String UpdateEmp_SkillLevelLovBtn() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(UpdateEmpSkillTable, "Level")
				+ ")[1]//button";
	}

	/** @locatorType XPATH */
	public static final String EmployeeHistoryBtn = "//*[@data-testid='BTN_Emp_Hist_empIcon']";
	/** @locatorType XPATH */
	public static final String EmployeeHistoryIframe = "//iframe[@title='Emp Hist']";
	/** @locatorType XPATH */
	public static final String EmpHistIframeCloseIcon = "//iframe[@title='Emp Hist']/preceding::button[@title='Close'][1]";
	/** @locatorType XPATH */
	public static final String EmployeeHistoryTableRowCount = "//table[contains(@aria-label,'Emp Hist')]/tbody//td/parent::tr";
	/** @locatorType XPATH */
	public static final String Find_EmpNameLovBtn = "//*[@data-testid='P22_EMPLOYEE_iteminpu']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String FindSearchBtn = "//*[@data-testid='BTN_Go_Report_clcon']";
	/** @locatorType XPATH */
	public static final String FindSearchBar = "//input[@class='a-IRR-search-field']";
	/** @locatorType XPATH */
	public static final String FindSearchGoBtn = "//*[text()='Go']/parent::button";

	/** @locatorType XPATH */
	public static final String FindResult_TableEmpId(String id) {
		return "//div[@aria-label='Data view']//table/tbody/tr/td/a[text()='" + id + "']";
	}

	/** @locatorType XPATH */
	public static final String FindResult_TableStatus(String id) {
		return "//div[@aria-label='Data view']//table/tbody/tr/td/a[text()='" + id + "']/../../td[14]";
	}

	/** @locatorType XPATH */
	public static final String ViewProfileBtn = "//*[@data-testid='BTN_View_Profile_empIcon']";
	/** @locatorType XPATH */
	public static final String ViewProf_ProfileNo = "//table[contains(@id,'orig')]/tbody/tr/td[1]/a";
	/** @locatorType XPATH */
	public static final String ViewProf_BatchNo = "//table[contains(@id,'orig')]/tbody/tr/td[2]";
	/** @locatorType XPATH */
	public static final String ViewProf_ReverseBtn = "//button[@title='Print Profile']/preceding-sibling::button";
	/** @locatorType XPATH */
	public static final String ViewProf_ReverseOkBtn = "//button[text()='OK']";
	/** @locatorType XPATH */
	public static final String MigrationBtn = "//*[@data-testid='BTN_Migration_clcon']";
	/** @locatorType XPATH */
	public static final String MigrationIframe = "//iframe[@title='Select The Type']";
	/** @locatorType XPATH */
	public static final String MigrationIframeCloseIcon = "//*[text()='Select The Type']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String EmpMigrationLabel = "//label[text()='Emp.Migration']";
	/** @locatorType XPATH */
	public static final String EmpMigrationAddButton = "//div[contains(@aria-label,'Employee Migration')]//button[@title='Add']";
	/** @locatorType XPATH */
	public static final String EmpMigrationCalendarLov = "//*[@data-testid='P367011401_EMHD_CLNDR_ID_iteminpu']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String EmpMigrationPaymentSelect = "//*[@data-testid='P367011401_EMHD_PAY_BASIS_iteminpu']";
	/** @locatorType XPATH */
	public static final String EmpMigrationPaymentTypeSelect = "//*[@data-testid='P367011401_EMHD_PAY_SAL_WAGE_iteminpu']";
	/** @locatorType XPATH */
	public static final String EmpMigrationReferenceInput = "//*[@data-testid='P367011401_EMHD_REF_iteminpu']";
	/** @locatorType XPATH */
	public static final String EmpMigrationDocNoDisplay = "//*[@data-testid='P367011401_EMHD_DOC_NO_iteminpu']";
	/** @locatorType XPATH */
	public static final String EmpMigrationCreateSaveIcon = "//*[@data-testid='BTN_CREATE_Icon']";
	/** @locatorType XPATH */
	public static final String EmpMigrationHeaderSaveIcon = "//*[@data-testid='BTN_SAVE_Icon']";
	/** @locatorType XPATH */
	public static final String EmpMigrationUploadTemplateBtn = "//*[@data-testid='BTN_Load_Icon']";
	/** @locatorType XPATH */
	public static final String EmpMigrationUploadInput = "//a-file-upload[contains(@id,'LOAD')]/input";
	/** @locatorType XPATH */
	public static final String EmpMigrationUploadBtn = "//*[@data-testid='BTN_UploadIcon']";
	/** @locatorType XPATH */
	public static final String EmpMigrationCheckExceptionBtn = "//*[@data-testid='BTN_Check_excep_N_Icon']";
	/** @locatorType XPATH */
	public static final String EmpMigrationPostBtn = "//*[@data-testid='BTN_Post_Icon']";
	/** @locatorType XPATH */
	public static final String EmpDetailsMigrationLabel = "//label[text()='Emp.Details Migration']";
	/** @locatorType XPATH */
	public static final String EmpDetailsMigrationCreateAddIcon = "//div[@aria-label='Employee Details Migration']//button[@title='Add ']";
	/** @locatorType XPATH */
	public static final String EmpDetailsMigrationSelectFieldLabel = "//label[text()='Select Field']";
	/** @locatorType XPATH */
	public static final String EmpDetailsMigrationUploadLabel = "//label[text()='Upload']";
	/** @locatorType XPATH */
	public static final String EmpDetailsMigrationReferenceInput = "//*[@data-testid='P74_EDMHD_REF_iteminpu']";
	/** @locatorType XPATH */
	public static final String EmpDetailsMigrationCreateSaveButton = "//*[@data-testid='BTN_CREATEIcon']";
	/** @locatorType XPATH */
	public static final String EmpDetailsMigrationDocNoDisplay = "//*[@data-testid='P74_EDMHD_DOC_NO_iteminpu']";
	/** @locatorType XPATH */
	public static final String EmpDetailsMigrationSelectFieldAddIcon = "//*[@data-testid='BTN_Add_sectionlcon']";
	/** @locatorType XPATH */
	public static final String EmpDetailsMigrationSelectFieldSaveIcon = "//*[@data-testid='BTN_Save_sectionlcon']";
	/** @locatorType XPATH */
	public static final String EmpDetailsMigrationHeaderSaveIcon = "//*[@data-testid='BTN_CREATEIcon']";
	/** @locatorType XPATH */
	public static final String EmpDetailsMigrationUploadIcon = "//*[@data-testid='BTN_LoadIcon']";
	/** @locatorType XPATH */
	public static final String EmpDetailsSelectFieldDataTypeTableData = "//div[@id='ig_line_ig_grid_vc']//table/tbody/tr[1]/td[2]";
	/** @locatorType XPATH */
	public static final String EmpDetailsSelectFieldDataTypeSelect = "//div[@id='ig_line_ig_grid_vc']//table/tbody/tr[1]/td[2]//select";
	/** @locatorType XPATH */
	public static final String EmpDetailsSelectFieldSequenceTableData = "//div[@id='ig_line_ig_grid_vc']//table/tbody/tr[1]/td[3]";
	/** @locatorType XPATH */
	public static final String EmpDetailsSelectFieldSequenceInput = "//div[@id='ig_line_ig_grid_vc']//table/tbody/tr[1]/td[3]//input";
	/** @locatorType XPATH */
	public static final String EmpDetailsMigrationUploadIframe = "//iframe[@title='Upload']";
	/** @locatorType XPATH */
	public static final String EmpDetailsMigrationUploadIframeCloseIcon = "//h1[text()='Upload']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String EmpDetailsMigrationUploadInput = "//a-file-upload[contains(@id,'LOAD')]/input";
	/** @locatorType XPATH */
	public static final String EmpDetailsMigrationUploadBtn = "//div[@aria-label='Upload']//span[text()='Upload']/parent::button";
	/** @locatorType XPATH */
	public static final String EmpDetailsMigrationCheckExceptionBtn = "//*[@data-testid='BTN_Check_yIcon']";
	/** @locatorType XPATH */
	public static final String EmpDetailsMigrationPostBtn = "//*[@data-testid='BTN_PostIcon']";
	/** @locatorType XPATH */
	public static final String AddEmp_ProbationDurationInput = "//input[@data-testid='P38_EMP_PROB_DURE_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_ProbationSelect = "//select[@data-testid='P38_EMP_PROB_FREQ_ID_iteminpu']";
	/** @locatorType XPATH */
	public static final String AddEmp_ProbationDateFrom = "//*[@data-testid='P38_EMP_PROB_START_DATE_iteminpu']/input";
	/** @locatorType XPATH */
	public static final String AddEmp_ProbationDateTo = "//*[@data-testid='P38_EMP_PROB_END_DATE_iteminpu']/input";
	/** @locatorType XPATH */
	public static final String AddEmp_ProbationCompleteBtn = "//*[@data-testid='BTN_Complete_empIcon']";
	/** @locatorType XPATH */
	public static final String ProbationStatusVerificationTableData = "//td[text()='Probation Status']/../td[3]";
	/** @locatorType XPATH */
	public static final String BalanceLeaveDetailsTable = "//div[@id='balance']//table/tbody";
	/** @locatorType XPATH */
	public static final String CLBalanceLeaveDetailsTableData = "//div[@id='balance']//table/tbody//td[text()='CL']/../td[3]";
	/** @locatorType XPATH */
	public static final String FindZoneLovBtn = "//*[@data-testid='P22_EMP_ZONE_iteminpu']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String FindCategoryLovBtn = "//*[@data-testid='P22_CATEGORY_iteminpu']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String FindDesignationLovBtn = "//*[@data-testid='P22_DESIGNATION_iteminpu']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String FindDepartmentLovBtn = "//*[@data-testid='P22_DEPT_iteminpu']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String FindUnitLovBtn = "//*[@data-testid='P22_UNIT_iteminpu']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String FindTableEmployeeMasterScreen = "//table[contains(@aria-label,'Result(s)')]//tr/th";

	/** @locatorType XPATH */
	public static String FindZoneResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindTableEmployeeMasterScreen, "Zone");
	}

	/** @locatorType XPATH */
	public static String FindCategoryResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindTableEmployeeMasterScreen, "Category");
	}

	/** @locatorType XPATH */
	public static String FindDesignationResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindTableEmployeeMasterScreen, "Designation");
	}

	/** @locatorType XPATH */
	public static String FindDepartmentResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindTableEmployeeMasterScreen, "Department");
	}

	/** @locatorType XPATH */
	public static String FindUnitResultTable() {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(FindTableEmployeeMasterScreen, "Unit");
	}

	/** @locatorType XPATH */
	public static final String AddEmp_AdjustmentTable = "(//b[text()='Adjustments']/following::table)[2]//tr/td[1]";
	/** @locatorType XPATH */
	public static final String AllowanceSPLDeleteIcon = "//div[@id='ig_line_ig_grid_vc']//table//tr/td[text()='SPL']/../td[last()]/a";
}
