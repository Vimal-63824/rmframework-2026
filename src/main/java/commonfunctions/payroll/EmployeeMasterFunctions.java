package commonfunctions.payroll;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 5, 2025
 */
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.payroll.EmployeeMasterLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class EmployeeMasterFunctions {
	private static boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static CommonPayrollFunctions commonPayrollFunc = new CommonPayrollFunctions();
	private static Random random = new Random();

	/**
	 * This Method used To Check the Employee Creation with Particulars details
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyEmployeeCreationParticularsDetails() {
		try {
			commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
					System.getProperty("HRMSelfApprovePassword"), "HRMSelfApproveUser");
			commonFunc.navigateEmployeeMasterScreenInPayroll();
			clickAddIconAddEmployee();
			clickEmployeeMasterSideNavBar();
			selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
					commonFunc.getDataFromTestDataSheet("Emp_FName"), commonFunc.getDataFromTestDataSheet("Emp_LName"));
			selectGenderEnterDOBAndDOJ(commonFunc.getDataFromTestDataSheet("Emp_MaleGender"),
					commonFunc.getDataFromTestDataSheet("Emp_DOB"), commonFunc.getDataFromTestDataSheet("Emp_DOJ"));
			selectShiftEnterCalerdarAndZone(commonFunc.getDataFromTestDataSheet("Emp_GeneralShift"),
					commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"),
					commonFunc.getDataFromTestDataSheet("Emp_GeneralZone"));
			selectCompanyAndAccountCatecory(commonFunc.getDataFromTestDataSheet("Emp_SalesCompanyCategory"),
					commonFunc.getDataFromTestDataSheet("Emp_SalaryAcc-AccountCategory"));
			selectPayrollGroupAndEnterNoticePeriod(commonFunc.getDataFromTestDataSheet("Emp_CleanerPayrollGroup"),
					commonFunc.getDataFromTestDataSheet("Emp_NoticePeriod-30"));
			clickSaveIconAndStoreEmpID();
			VariableHandler.setAddEmployeDefaultExecution(true);
			return true;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyEmployeeCreationParticularsDetails': " + e);
			return false;
		}
	}

	/**
	 * This Method used To check Employee Creation with Qualification / Skills
	 * details
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyEmployeeCreationQualificationSkillDetails() {
		try {
			if (!VariableHandler.getAddEmployeDefaultExecution()) {
				commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
						System.getProperty("HRMSelfApprovePassword"), "HRMSelfApproveUser");
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
						commonFunc.getDataFromTestDataSheet("Emp_FName"),
						commonFunc.getDataFromTestDataSheet("Emp_LName"));
				selectGenderEnterDOBAndDOJ(commonFunc.getDataFromTestDataSheet("Emp_MaleGender"),
						commonFunc.getDataFromTestDataSheet("Emp_DOB"), commonFunc.getDataFromTestDataSheet("Emp_DOJ"));
				selectShiftEnterCalerdarAndZone(commonFunc.getDataFromTestDataSheet("Emp_GeneralShift"),
						commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"),
						commonFunc.getDataFromTestDataSheet("Emp_GeneralZone"));
				selectCompanyAndAccountCatecory(commonFunc.getDataFromTestDataSheet("Emp_SalesCompanyCategory"),
						commonFunc.getDataFromTestDataSheet("Emp_SalaryAcc-AccountCategory"));
				selectPayrollGroupAndEnterNoticePeriod(commonFunc.getDataFromTestDataSheet("Emp_CleanerPayrollGroup"),
						commonFunc.getDataFromTestDataSheet("Emp_NoticePeriod-30"));
				clickSaveIconAndStoreEmpID();
				VariableHandler.setAddEmployeDefaultExecution(true);
			}
			clickQualificationSkillSideNavBar();
			addNewContactDetailsInQualificationTab(RandomStringUtils.randomAlphabetic(15),
					RandomStringUtils.randomAlphabetic(12), commonFunc.randomMobileNumberGenerator(),
					RandomStringUtils.randomAlphabetic(12), commonFunc.getDataFromTestDataSheet("Emp_City"), true);
			addNewQualificationDetailsInQualificationTab(commonFunc.getDataFromTestDataSheet("Emp_Qualification"),
					commonFunc.getDataFromTestDataSheet("Emp_BranchofStudy"), RandomStringUtils.randomAlphabetic(22),
					String.valueOf(random.nextInt(4)), commonFunc.getDataFromTestDataSheet("Emp_YearOfPassing"),
					RandomStringUtils.randomAlphabetic(1), RandomStringUtils.randomNumeric(2), true);
			addNewSkillDetailsInQualificationTab(commonFunc.getDataFromTestDataSheet("Emp_Skill"),
					commonFunc.getDataFromTestDataSheet("Emp_Level"), true);
			return true;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyEmployeeCreationQualificationSkillDetails': " + e);
			return false;
		}
	}

	/**
	 * This Method used To check Employee creation with salary details With
	 * Allowance Values
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyEmployeeCreationSalaryDetailsAllowanceValues() {
		try {
			if (!VariableHandler.getAddEmployeDefaultExecution()) {
				commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
						System.getProperty("HRMSelfApprovePassword"), "HRMSelfApproveUser");
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
						commonFunc.getDataFromTestDataSheet("Emp_FName"),
						commonFunc.getDataFromTestDataSheet("Emp_LName"));
				selectGenderEnterDOBAndDOJ(commonFunc.getDataFromTestDataSheet("Emp_MaleGender"),
						commonFunc.getDataFromTestDataSheet("Emp_DOB"), commonFunc.getDataFromTestDataSheet("Emp_DOJ"));
				selectShiftEnterCalerdarAndZone(commonFunc.getDataFromTestDataSheet("Emp_GeneralShift"),
						commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"),
						commonFunc.getDataFromTestDataSheet("Emp_GeneralZone"));
				selectCompanyAndAccountCatecory(commonFunc.getDataFromTestDataSheet("Emp_SalesCompanyCategory"),
						commonFunc.getDataFromTestDataSheet("Emp_SalaryAcc-AccountCategory"));
				selectPayrollGroupAndEnterNoticePeriod(commonFunc.getDataFromTestDataSheet("Emp_CleanerPayrollGroup"),
						commonFunc.getDataFromTestDataSheet("Emp_NoticePeriod-30"));
				clickSaveIconAndStoreEmpID();
				VariableHandler.setAddEmployeDefaultExecution(true);
			}
			clickSalarySideNavBar();
			addDesignationDepartmentAndGradeInSalaryDetails(
					commonFunc.getDataFromTestDataSheet("Emp_SalaryDesignation"),
					commonFunc.getDataFromTestDataSheet("Emp_SalaryDepartment"),
					commonFunc.getDataFromTestDataSheet("Emp_SalaryGrade"));
			clickAfterEmpCreationSaveIcon();
			clickLoadAllowanceButtonInSalaryDetails();
			fillAllowanceAmountsAndVerifyTotalInSalaryDetails(
					new String[] { RandomStringUtils.randomNumeric(5), RandomStringUtils.randomNumeric(4),
							RandomStringUtils.randomNumeric(4), RandomStringUtils.randomNumeric(4) });
			return true;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyEmployeeCreationSalaryDetailsAllowanceValues': " + e);
			return false;
		}
	}

	/**
	 * This Method used To check Employee creation with salary details With
	 * Adjustment Values
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyEmployeeCreationSalaryDetailsAdjustmentValues() {
		try {
			if (!VariableHandler.getAddEmployeDefaultExecution()) {
				commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
						System.getProperty("HRMSelfApprovePassword"), "HRMSelfApproveUser");
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
						commonFunc.getDataFromTestDataSheet("Emp_FName"),
						commonFunc.getDataFromTestDataSheet("Emp_LName"));
				selectGenderEnterDOBAndDOJ(commonFunc.getDataFromTestDataSheet("Emp_MaleGender"),
						commonFunc.getDataFromTestDataSheet("Emp_DOB"), commonFunc.getDataFromTestDataSheet("Emp_DOJ"));
				selectShiftEnterCalerdarAndZone(commonFunc.getDataFromTestDataSheet("Emp_GeneralShift"),
						commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"),
						commonFunc.getDataFromTestDataSheet("Emp_GeneralZone"));
				selectCompanyAndAccountCatecory(commonFunc.getDataFromTestDataSheet("Emp_SalesCompanyCategory"),
						commonFunc.getDataFromTestDataSheet("Emp_SalaryAcc-AccountCategory"));
				selectPayrollGroupAndEnterNoticePeriod(commonFunc.getDataFromTestDataSheet("Emp_CleanerPayrollGroup"),
						commonFunc.getDataFromTestDataSheet("Emp_NoticePeriod-30"));
				clickSaveIconAndStoreEmpID();
				VariableHandler.setAddEmployeDefaultExecution(true);
			}
			clickSalarySideNavBar();
			clickAddButtonInAdjustmentSalaryDetails();
			selectAdjustmentSalaryElementStartYearAndPeriodDetails(
					commonFunc.getDataFromTestDataSheet("Emp_AdjmntElement"),
					commonFunc.getDataFromTestDataSheet("Emp_AdjmntStartYear"),
					commonFunc.getDataFromTestDataSheet("Emp_AdjmntPeriod"));
			fillAdjustmentSalaryTotalPeriodAndPeriodAmount(commonFunc.getDataFromTestDataSheet("Emp_AdjmntTotalPeriod"),
					commonFunc.getDataFromTestDataSheet("Emp_AdjmntPeriodAmnt"));
			clickSaveIconInSalaryAdjustment();
			clickActivateIconInLastAddedAdjustment();
			clickDeleteIconInLastAddedAdjustment();
			enablePFAdjustmentFlagsInSalaryDetails();
			enableESIAdjustmentFlagsInSalaryDetails();
			clickPostButtonAndVerifyEmployeePosted();
			return true;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyEmployeeCreationSalaryDetailsAdjustmentValues': " + e);
			return false;
		}
	}

	/**
	 * This Method used To Check Employee Creation with Leave Details
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyEmployeeCreationLeaveDetails() {
		try {
			if (!VariableHandler.getAddEmployeDefaultExecution()) {
				commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
						System.getProperty("HRMSelfApprovePassword"), "HRMSelfApproveUser");
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
						commonFunc.getDataFromTestDataSheet("Emp_FName"),
						commonFunc.getDataFromTestDataSheet("Emp_LName"));
				selectGenderEnterDOBAndDOJ(commonFunc.getDataFromTestDataSheet("Emp_MaleGender"),
						commonFunc.getDataFromTestDataSheet("Emp_DOB"), commonFunc.getDataFromTestDataSheet("Emp_DOJ"));
				selectShiftEnterCalerdarAndZone(commonFunc.getDataFromTestDataSheet("Emp_GeneralShift"),
						commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"),
						commonFunc.getDataFromTestDataSheet("Emp_GeneralZone"));
				selectCompanyAndAccountCatecory(commonFunc.getDataFromTestDataSheet("Emp_SalesCompanyCategory"),
						commonFunc.getDataFromTestDataSheet("Emp_SalaryAcc-AccountCategory"));
				selectPayrollGroupAndEnterNoticePeriod(commonFunc.getDataFromTestDataSheet("Emp_CleanerPayrollGroup"),
						commonFunc.getDataFromTestDataSheet("Emp_NoticePeriod-30"));
				clickSaveIconAndStoreEmpID();
				clickSalarySideNavBar();
				addDesignationDepartmentAndGradeInSalaryDetails(
						commonFunc.getDataFromTestDataSheet("Emp_SalaryDesignation"),
						commonFunc.getDataFromTestDataSheet("Emp_SalaryDepartment"),
						commonFunc.getDataFromTestDataSheet("Emp_SalaryGrade"));
				clickAfterEmpCreationSaveIcon();
				clickLoadAllowanceButtonInSalaryDetails();
				fillAllowanceAmountsAndVerifyTotalInSalaryDetails(
						new String[] { RandomStringUtils.randomNumeric(5), RandomStringUtils.randomNumeric(4),
								RandomStringUtils.randomNumeric(4), RandomStringUtils.randomNumeric(4) });
				enablePFAdjustmentFlagsInSalaryDetails();
				enableESIAdjustmentFlagsInSalaryDetails();
				clickPostButtonAndVerifyEmployeePosted();
				VariableHandler.setAddEmployeDefaultExecution(true);
			}
			commonPayrollFunc.openLeaveLoanSetupInNewTabAndStoreLeaveEntitlement();
			clickLeaveDetailsSideNavBar();
			validateUpdatedLeaveDetailsWithSetupScreenEntitlement();
			clickAddIconLeaveEligibilityInLeaveDetails();
			fillLeaveAndDaysInLeaveEligibility(commonFunc.getDataFromTestDataSheet("LeaveElgblLeave"),
					commonFunc.getDataFromTestDataSheet("LeaveElgblDays"));
			fillEveryAndEligibleDaysInLeaveEligibility(commonFunc.getDataFromTestDataSheet("EmpLeaveElgblEvery"),
					commonFunc.getDataFromTestDataSheet("EmpLeaveElgblEligibileDays"));
			fillRoundOffDaysAndMaxCarryFrequencyInLeaveEligibility(
					commonFunc.getDataFromTestDataSheet("EmpLeaveElgblRoundOff"),
					commonFunc.getDataFromTestDataSheet("EmpLeaveElgblMaxCarryFreq"));
			fillMaxCarryDayAndEffectiveYearInLeaveEligibility(
					commonFunc.getDataFromTestDataSheet("EmpLeaveElgblMaxCarryDay"),
					commonFunc.getDataFromTestDataSheet("EmpLeaveElgblEffYear"));
			fillMaxDaysAndMinDaysInLeaveEligibility(commonFunc.getDataFromTestDataSheet("EmpLeaveElgblMaxDay"),
					commonFunc.getDataFromTestDataSheet("EmpLeaveElgblMinDay"));
			clickSaveIconInLeaveEligibility();
			return true;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyEmployeeCreationLeaveDetails': " + e);
			return false;
		}
	}

	/**
	 * This Method used To Check the Employee Creation with Particulars details
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProfileImageAndSignUploadWithAttachments() {
		try {
			if (!VariableHandler.getAddEmployeDefaultExecution()) {
				commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
						System.getProperty("HRMSelfApprovePassword"), "HRMSelfApproveUser");
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
						commonFunc.getDataFromTestDataSheet("Emp_FName"),
						commonFunc.getDataFromTestDataSheet("Emp_LName"));
				selectGenderEnterDOBAndDOJ(commonFunc.getDataFromTestDataSheet("Emp_MaleGender"),
						commonFunc.getDataFromTestDataSheet("Emp_DOB"), commonFunc.getDataFromTestDataSheet("Emp_DOJ"));
				selectShiftEnterCalerdarAndZone(commonFunc.getDataFromTestDataSheet("Emp_GeneralShift"),
						commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"),
						commonFunc.getDataFromTestDataSheet("Emp_GeneralZone"));
				selectCompanyAndAccountCatecory(commonFunc.getDataFromTestDataSheet("Emp_SalesCompanyCategory"),
						commonFunc.getDataFromTestDataSheet("Emp_SalaryAcc-AccountCategory"));
				selectPayrollGroupAndEnterNoticePeriod(commonFunc.getDataFromTestDataSheet("Emp_CleanerPayrollGroup"),
						commonFunc.getDataFromTestDataSheet("Emp_NoticePeriod-30"));
				clickSaveIconAndStoreEmpID();
				VariableHandler.setAddEmployeDefaultExecution(true);
			}
			uploadSignImageInAddEmployeeDetails(webDB.getDataFromProperties("RoadMapImage"));
			uploadProfileImageInAddEmployeeDetails(webDB.getDataFromProperties("RoadMapImage"));
			return true;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyProfileImageAndSignUploadWithAttachments': " + e);
			return false;
		}
	}

	/**
	 * This method used To check Employee Creation with Additional info details
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyEmployeeCreationAdditionalInfoDetails() {
		try {
			if (!VariableHandler.getAddEmployeDefaultExecution()) {
				commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
						System.getProperty("HRMSelfApprovePassword"), "HRMSelfApproveUser");
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
						commonFunc.getDataFromTestDataSheet("Emp_FName"),
						commonFunc.getDataFromTestDataSheet("Emp_LName"));
				selectGenderEnterDOBAndDOJ(commonFunc.getDataFromTestDataSheet("Emp_MaleGender"),
						commonFunc.getDataFromTestDataSheet("Emp_DOB"), commonFunc.getDataFromTestDataSheet("Emp_DOJ"));
				selectShiftEnterCalerdarAndZone(commonFunc.getDataFromTestDataSheet("Emp_GeneralShift"),
						commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"),
						commonFunc.getDataFromTestDataSheet("Emp_GeneralZone"));
				selectCompanyAndAccountCatecory(commonFunc.getDataFromTestDataSheet("Emp_SalesCompanyCategory"),
						commonFunc.getDataFromTestDataSheet("Emp_SalaryAcc-AccountCategory"));
				selectPayrollGroupAndEnterNoticePeriod(commonFunc.getDataFromTestDataSheet("Emp_CleanerPayrollGroup"),
						commonFunc.getDataFromTestDataSheet("Emp_NoticePeriod-30"));
				clickSaveIconAndStoreEmpID();
				VariableHandler.setAddEmployeDefaultExecution(true);
			}
			clickAdditionalInfoButton();
			submitHomeTypeAndPreviousExperienceInAdditionalInfo(
					commonFunc.getDataFromTestDataSheet("EmpAddInfo_HomeType"), String.valueOf(random.nextInt(4)),
					String.valueOf(random.nextInt(12)));
			submitStayTypeAndVehicleDetailsInAdditionalInfo(commonFunc.getDataFromTestDataSheet("EmpAddInfo_StayType"),
					commonFunc.getDataFromTestDataSheet("EmpAddInfo_VehicleFlag"));
			submitOfficeDetailsInAdditionalInfo(String.valueOf(random.nextInt(100)), String.valueOf(random.nextInt(31)),
					String.valueOf(random.nextInt(9999)));
			submitMobileEmailOfcAndPersonalInAdditionalInfo(commonFunc.randomMobileNumberGenerator(),
					commonFunc.randomEmailIdGenerator(), commonFunc.randomEmailIdGenerator());
			submitBuildingSectionAndRoomInAdditionalInfo(String.valueOf(random.nextInt(40)),
					String.valueOf(random.nextInt(4)), String.valueOf(random.nextInt(20)));
			submitPersonalAlterMobileSimNoInAdditionalInfo(commonFunc.randomMobileNumberGenerator(),
					commonFunc.randomMobileNumberGenerator(), String.valueOf(random.nextInt(100)));
			submitPhoneExtensionAndFaxNoInAdditionalInfo(commonFunc.randomMobileNumberGenerator(),
					String.valueOf(random.nextInt(100)), String.valueOf(random.nextInt(5)));
			clickTickIconInAdditionalInfo();
			return true;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyEmployeeCreationAdditionalInfoDetails': " + e);
			return false;
		}
	}

	/**
	 * This method used To check Employee Creation with Dependent details
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCheckEmployeeCreationDependentDetails() {
		try {
			if (!VariableHandler.getAddEmployeDefaultExecution()) {
				commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
						System.getProperty("HRMSelfApprovePassword"), "HRMSelfApproveUser");
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
						commonFunc.getDataFromTestDataSheet("Emp_FName"),
						commonFunc.getDataFromTestDataSheet("Emp_LName"));
				selectGenderEnterDOBAndDOJ(commonFunc.getDataFromTestDataSheet("Emp_MaleGender"),
						commonFunc.getDataFromTestDataSheet("Emp_DOB"), commonFunc.getDataFromTestDataSheet("Emp_DOJ"));
				selectShiftEnterCalerdarAndZone(commonFunc.getDataFromTestDataSheet("Emp_GeneralShift"),
						commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"),
						commonFunc.getDataFromTestDataSheet("Emp_GeneralZone"));
				selectCompanyAndAccountCatecory(commonFunc.getDataFromTestDataSheet("Emp_SalesCompanyCategory"),
						commonFunc.getDataFromTestDataSheet("Emp_SalaryAcc-AccountCategory"));
				selectPayrollGroupAndEnterNoticePeriod(commonFunc.getDataFromTestDataSheet("Emp_CleanerPayrollGroup"),
						commonFunc.getDataFromTestDataSheet("Emp_NoticePeriod-30"));
				clickSaveIconAndStoreEmpID();
				VariableHandler.setAddEmployeDefaultExecution(true);
			}
			clickDependentDetailsSideNavBar();
			clickAddIconInDependentDetails();
			fillRequiredDetailsInDependentDetails(RandomStringUtils.randomAlphabetic(6),
					commonFunc.getDataFromTestDataSheet("EmpDependentRelation"),
					commonFunc.getDataFromTestDataSheet("EmpDependentRelationDOB"), RandomStringUtils.randomNumeric(12),
					commonFunc.randomEmailIdGenerator(), commonFunc.randomMobileNumberGenerator(),
					RandomStringUtils.randomNumeric(5));
			clickSaveIconInDependentDetails();
			return true;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyCheckEmployeeCreationDependentDetails': " + e);
			return false;
		}
	}

	/**
	 * This method used To check Employee Creation With Update Employee Particular
	 * Details
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean verifyUpdateEmployeeParticularDetails() {
		try {
			if (!VariableHandler.getAddEmployeDefaultExecution()) {
				commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
						System.getProperty("HRMSelfApprovePassword"), "HRMSelfApproveUser");
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
						commonFunc.getDataFromTestDataSheet("Emp_FName"),
						commonFunc.getDataFromTestDataSheet("Emp_LName"));
				selectGenderEnterDOBAndDOJ(commonFunc.getDataFromTestDataSheet("Emp_MaleGender"),
						commonFunc.getDataFromTestDataSheet("Emp_DOB"), commonFunc.getDataFromTestDataSheet("Emp_DOJ"));
				selectShiftEnterCalerdarAndZone(commonFunc.getDataFromTestDataSheet("Emp_GeneralShift"),
						commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"),
						commonFunc.getDataFromTestDataSheet("Emp_GeneralZone"));
				selectCompanyAndAccountCatecory(commonFunc.getDataFromTestDataSheet("Emp_SalesCompanyCategory"),
						commonFunc.getDataFromTestDataSheet("Emp_SalaryAcc-AccountCategory"));
				selectPayrollGroupAndEnterNoticePeriod(commonFunc.getDataFromTestDataSheet("Emp_CleanerPayrollGroup"),
						commonFunc.getDataFromTestDataSheet("Emp_NoticePeriod-30"));
				clickSaveIconAndStoreEmpID();
				clickSalarySideNavBar();
				addDesignationDepartmentAndGradeInSalaryDetails(
						commonFunc.getDataFromTestDataSheet("Emp_SalaryDesignation"),
						commonFunc.getDataFromTestDataSheet("Emp_SalaryDepartment"),
						commonFunc.getDataFromTestDataSheet("Emp_SalaryGrade"));
				clickAfterEmpCreationSaveIcon();
				clickLoadAllowanceButtonInSalaryDetails();
				fillAllowanceAmountsAndVerifyTotalInSalaryDetails(
						new String[] { RandomStringUtils.randomNumeric(5), RandomStringUtils.randomNumeric(4),
								RandomStringUtils.randomNumeric(4), RandomStringUtils.randomNumeric(4) });
				enablePFAdjustmentFlagsInSalaryDetails();
				enableESIAdjustmentFlagsInSalaryDetails();
				clickPostButtonAndVerifyEmployeePosted();
				VariableHandler.setAddEmployeDefaultExecution(true);
			}
			clickUpdateButtonInAddEmployee();
			clickEmployeeParticularTabInUpdateEmployee();
			updateEmployeeGenderFirstAndLastNameInParticularTab(commonFunc.getDataFromTestDataSheet("Emp_MaleGender"),
					commonFunc.getDataFromTestDataSheet("Emp_FName"), commonFunc.getDataFromTestDataSheet("Emp_LName"));
			updateEmployeeDOBAndCalenderInParticularTab(commonFunc.getDataFromTestDataSheet("Emp_DOB"),
					commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"));
			updateEmployeeShiftMaritalStatusAndZone(commonFunc.getDataFromTestDataSheet("Emp_GeneralShift"),
					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"),
					commonFunc.getDataFromTestDataSheet("Emp_GeneralZone"));
			updateEmployeeCategoryAndAccountCategory(commonFunc.getDataFromTestDataSheet("Emp_SalesCompanyCategory"),
					commonFunc.getDataFromTestDataSheet("Emp_SalaryAcc-AccountCategory"));
			updateEmployeePayrollGrpAndNoticePeriod(commonFunc.getDataFromTestDataSheet("Emp_CleanerPayrollGroup"),
					commonFunc.getDataFromTestDataSheet("Emp_NoticePeriod-30"));
			clickSaveIconInEmployeeParticular();
			return true;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyUpdateEmployeeParticularDetails': " + e);
			return false;
		}
	}

	/**
	 * This method used To check Employee Creation With Update Address Details
	 * Details
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean verifyEmployeeCreationUpdateAddressDetails() {
		try {
			if (!VariableHandler.getAddEmployeDefaultExecution()) {
				commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
						System.getProperty("HRMSelfApprovePassword"), "HRMSelfApproveUser");
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
						commonFunc.getDataFromTestDataSheet("Emp_FName"),
						commonFunc.getDataFromTestDataSheet("Emp_LName"));
				selectGenderEnterDOBAndDOJ(commonFunc.getDataFromTestDataSheet("Emp_MaleGender"),
						commonFunc.getDataFromTestDataSheet("Emp_DOB"), commonFunc.getDataFromTestDataSheet("Emp_DOJ"));
				selectShiftEnterCalerdarAndZone(commonFunc.getDataFromTestDataSheet("Emp_GeneralShift"),
						commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"),
						commonFunc.getDataFromTestDataSheet("Emp_GeneralZone"));
				selectCompanyAndAccountCatecory(commonFunc.getDataFromTestDataSheet("Emp_SalesCompanyCategory"),
						commonFunc.getDataFromTestDataSheet("Emp_SalaryAcc-AccountCategory"));
				selectPayrollGroupAndEnterNoticePeriod(commonFunc.getDataFromTestDataSheet("Emp_CleanerPayrollGroup"),
						commonFunc.getDataFromTestDataSheet("Emp_NoticePeriod-30"));
				clickSaveIconAndStoreEmpID();
				clickSalarySideNavBar();
				addDesignationDepartmentAndGradeInSalaryDetails(
						commonFunc.getDataFromTestDataSheet("Emp_SalaryDesignation"),
						commonFunc.getDataFromTestDataSheet("Emp_SalaryDepartment"),
						commonFunc.getDataFromTestDataSheet("Emp_SalaryGrade"));
				clickAfterEmpCreationSaveIcon();
				clickLoadAllowanceButtonInSalaryDetails();
				fillAllowanceAmountsAndVerifyTotalInSalaryDetails(
						new String[] { RandomStringUtils.randomNumeric(5), RandomStringUtils.randomNumeric(4),
								RandomStringUtils.randomNumeric(4), RandomStringUtils.randomNumeric(4) });
				enablePFAdjustmentFlagsInSalaryDetails();
				enableESIAdjustmentFlagsInSalaryDetails();
				clickPostButtonAndVerifyEmployeePosted();
				VariableHandler.setAddEmployeDefaultExecution(true);
			}
			clickUpdateButtonInAddEmployee();
			clickAddressTabInUpdateEmployee();
			updateEmployeeAllAddressInAddressTab(RandomStringUtils.randomAlphabetic(6),
					RandomStringUtils.randomAlphabetic(8), RandomStringUtils.randomAlphabetic(3));
			updateEmpCityZipCodeAndTelephoneInAddressTab(commonFunc.getDataFromTestDataSheet("Emp_City"),
					RandomStringUtils.randomNumeric(6), commonFunc.randomMobileNumberGenerator());
			clickSaveIconInAddress();
			return true;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyEmployeeCreationUpdateAddressDetails': " + e);
			return false;
		}
	}

	// --------------------- Private Methods ---------------------

	/**
	 * This Method used To click Add Icon in Employee Master
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickAddIconAddEmployee() {
		flag = webDB.waitForElement(EmployeeMasterLocators.EmployeeMasterAddIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.EmployeeMasterAddIcon, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_EmpployeeMasterSideNav, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Employee Screen Add Icon Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To select salution First Name and Last name in Add Employee
	 * 
	 * @author Sakthi-GWL
	 * @param SalutionValue
	 * @param FName
	 * @param LName
	 */
	private void selectSalutionEnterFirstAndLastName(String SalutionValue, String FName, String LName) {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_SelectSalution, ElementType.Xpath);
		if (flag) {
			webDB.selectDropDownByText(EmployeeMasterLocators.AddEmp_SelectSalution, SalutionValue, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_FirstName, ElementType.Xpath);
			if (flag) {
				VariableHandler.setEmpSalution(SalutionValue);
				logs.logging("INFO", "Salution Selected for the Employee: " + VariableHandler.getEmpSalution());
				webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_FirstName, FName, ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LastName, ElementType.Xpath);
				if (flag) {
					VariableHandler.setEmpFirstName(FName);
					logs.logging("INFO", "First Name of the Employee Sent: " + VariableHandler.getEmpFirstName());
					flag = webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_LastName, LName, ElementType.Xpath);
					if (flag) {
						VariableHandler.setEmpLastName(LName);
						logs.logging("INFO", "Last Name of the Employee Sent: " + VariableHandler.getEmpLastName());
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To select Gender DOB and DOJ in Add Employee
	 * 
	 * @author Sakthi-GWL
	 * @param Gender
	 * @param DOB
	 * @param DOJ
	 */
	private void selectGenderEnterDOBAndDOJ(String Gender, String DOB, String DOJ) {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_SelectGender, ElementType.Xpath);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.selectDropDownByText(EmployeeMasterLocators.AddEmp_SelectGender, Gender, ElementType.Xpath);
			if (flag) {
				VariableHandler.setEmpGender(Gender);
				logs.logging("INFO", "Gender Selected for the Employee: " + VariableHandler.getEmpGender());
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_DOBInput, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(EmployeeMasterLocators.AddEmp_DOBInput, ElementType.Xpath);
					webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_DOBInput, DOB, ElementType.Xpath);
					webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LastName, ElementType.Xpath);
					flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_DOJInput, ElementType.Xpath);
					if (flag) {
						VariableHandler.setEmpDOB(DOB);
						logs.logging("INFO", "Date of Birth of the Employee Sent: " + VariableHandler.getEmpDOB());
						flag = webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_DOJInput, DOJ,
								ElementType.Xpath);
						if (flag) {
							VariableHandler.setEmpDOJ(DOJ);
							logs.logging("INFO",
									"Date of Joining of the Employee Sent: " + VariableHandler.getEmpDOJ());
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To select Shift Calendar and Zone in Add Employee
	 * 
	 * @author Sakthi-GWL
	 * @param Shift
	 * @param CalenderValue
	 * @param Zone
	 */
	private void selectShiftEnterCalerdarAndZone(String Shift, String CalenderValue, String Zone) {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_SelectShift, ElementType.Xpath);
		if (flag) {
			webDB.selectDropDownByText(EmployeeMasterLocators.AddEmp_SelectShift, Shift, ElementType.Xpath);
			VariableHandler.setEmpShift(Shift);
			logs.logging("INFO", "Shift Selected for the Employee: " + Shift);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_DOBInput, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_ChooseCalender, CalenderValue);
				VariableHandler.setEmpCalendar(CalenderValue);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_DOJInput, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_ChooseZone, Zone);
					VariableHandler.setEmpZone(Zone);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To select Company and Account Category in Add Employee
	 * 
	 * @author Sakthi-GWL
	 * @param CompanyCategory
	 * @param AccountCategory
	 */
	private void selectCompanyAndAccountCatecory(String CompanyCategory, String AccountCategory) {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_CompanyCategry, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_CompanyCategry, CompanyCategory);
			VariableHandler.setEmpCompanyCategory(CompanyCategory);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AccountCategry, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_AccountCategry, AccountCategory);
				VariableHandler.setEmpAccountCategory(AccountCategory);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To select payroll group and Notice Period in Add Employee
	 * 
	 * @author Sakthi-GWL
	 * @param PayrollGrp
	 * @param NoticePeriod
	 */
	private void selectPayrollGroupAndEnterNoticePeriod(String PayrollGrp, String NoticePeriod) {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_PayrollGroupCategry, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_PayrollGroupCategry, PayrollGrp);
			VariableHandler.setEmpPayrollGroup(PayrollGrp);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_NoticePeriod, ElementType.Xpath);
			if (flag) {
				flag = webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_NoticePeriod, NoticePeriod,
						ElementType.Xpath);
				if (flag) {
					VariableHandler.setEmpNoticePeriod(NoticePeriod);
					logs.logging("INFO",
							"Notice Period Sent for the Employee: " + VariableHandler.getEmpNoticePeriod());
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To click save icon in Add Employee and store Employee ID
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickSaveIconAndStoreEmpID() {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_SaveButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_SaveButton, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_SaveButton, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
			if (flag) {
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				logs.logging("INFO", "Add Employee - Save Icon Clicked and Success Toast Displayed");
				commonFunc.checkRefreshCellsDisappear();
				String text = webDB.getTextFromElement(EmployeeMasterLocators.AddEmp_DisplayEmpID, ElementType.Xpath);
				VariableHandler.setEmpID(text);
				logs.logging("INFO", "Auto Generated Emp ID: " + VariableHandler.getEmpID());
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To click Save Icon that was appear after successfully
	 * created in Add Employee
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickAfterEmpCreationSaveIcon() {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AfterCreationSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_AfterCreationSaveIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_AfterCreationSaveIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
			if (flag) {
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				logs.logging("INFO", "Add Employee - Save Icon Clicked and Success Toast Displayed");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To Click Employee master navigation bar in Add Employee
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickEmployeeMasterSideNavBar() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_EmpployeeMasterSideNav, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_EmpployeeMasterSideNav, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_FirstName, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Employee Page Employee Master Side Nav Bar Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To Click Qualification Details navigation bar in Add
	 * Employee
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickQualificationSkillSideNavBar() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_QualificationSkillsSideNav, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_QualificationSkillsSideNav, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_ContactAddLinesIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Employee Page Qualification Side Nav Bar Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To Click Salary Details navigation bar in Add Employee
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickSalarySideNavBar() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_SalarySideNav, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_SalarySideNav, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_SaveAllowanceTableBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Employee Page Qualification Side Nav Bar Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To Click leave Details navigation bar in Add Employee
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickLeaveDetailsSideNavBar() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveDetailsSideNav, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LeaveDetailsSideNav, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityAddBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Employee Leave Details Side Nav Bar Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To add New Contacts Details in Qualification Details in Add
	 * Employee
	 * 
	 * @author Sakthi-GWL
	 * @param ContactName
	 * @param Relationship
	 * @param MobileNo
	 * @param Address
	 * @param City
	 * @param addlineflag
	 */
	private void addNewContactDetailsInQualificationTab(String ContactName, String Relationship, String MobileNo,
			String Address, String City, boolean... addlineflag) {
		if (addlineflag != null && addlineflag[0] != false) {
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_ContactAddLinesIcon, ElementType.Xpath);
			if (flag) {
				flag = webDB.clickAnElement(EmployeeMasterLocators.AddEmp_ContactAddLinesIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Contacts Add Icon Clicked");
				}
			}
		}
		commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_ContactNameData,
				EmployeeMasterLocators.AddEmp_ContactNameDataInput, ContactName);
		VariableHandler.setEmpContactName(ContactName);
		commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_RelationshipData,
				EmployeeMasterLocators.AddEmp_RelationshipDataInput, Relationship);
		VariableHandler.setEmpContactRelationship(Relationship);
		commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_MobileNoData,
				EmployeeMasterLocators.AddEmp_MobileNoDataInput, MobileNo);
		VariableHandler.setEmpContactMobileNo(MobileNo);
		commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_AddressData,
				EmployeeMasterLocators.AddEmp_AddressDataInput, Address);
		VariableHandler.setEmpContactAddress(Address);
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_City, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_City, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_CityLovBtn, City);
			VariableHandler.setEmpContactCity(City);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_ContactSaveLinesIcon, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(EmployeeMasterLocators.AddEmp_ContactSaveLinesIcon, ElementType.Xpath);
				webDB.clickAnElement(EmployeeMasterLocators.AddEmp_ContactSaveLinesIcon, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To used to add new Qualifications in Qualification Details
	 * in Add Employee
	 * 
	 * @author Sakthi-GWL
	 * @param Qualification
	 * @param branchOfStudy
	 * @param Institute
	 * @param Totalyears
	 * @param YearofPassing
	 * @param GradeClass
	 * @param Agg
	 * @param addlineflag
	 */
	private void addNewQualificationDetailsInQualificationTab(String Qualification, String branchOfStudy,
			String Institute, String Totalyears, String YearofPassing, String GradeClass, String Agg,
			boolean... addlineflag) {
		if (addlineflag != null && addlineflag[0] != false) {
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_QualificationAddLinesIcon, ElementType.Xpath);
			if (flag) {
				flag = webDB.clickAnElement(EmployeeMasterLocators.AddEmp_QualificationAddLinesIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Qualification Add Icon Clicked");
				}
			}
		}
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_Qualification, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_Qualification, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_QualificationLovBtn, Qualification);
			VariableHandler.setEmpQualification(Qualification);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_BranchOfStudy, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(EmployeeMasterLocators.AddEmp_BranchOfStudy, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_BranchOfStudyLovBtn,
						branchOfStudy);
				VariableHandler.setEmpQualBranchOfStudy(branchOfStudy);
				commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_InstituteData,
						EmployeeMasterLocators.AddEmp_InstituteDataInput, Institute);
				VariableHandler.setEmpQualInstitute(Institute);
				commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_TotalYearsData,
						EmployeeMasterLocators.AddEmp_TotalYearsDataInput, Totalyears);
				VariableHandler.setEmpQualTotalYears(Totalyears);
				commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_YearOfPassingData,
						EmployeeMasterLocators.AddEmp_YearOfPassingDataInput, YearofPassing);
				VariableHandler.setEmpQualYearOfPassing(YearofPassing);
				commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_GradeClassData,
						EmployeeMasterLocators.AddEmp_GradeClassDataInput, GradeClass);
				VariableHandler.setEmpQualGradeClass(GradeClass);
				commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_AggData,
						EmployeeMasterLocators.AddEmp_AggDataInput, Agg);
				VariableHandler.setEmpQualAGG(Agg);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_QualificationSaveLinesIcon,
						ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(EmployeeMasterLocators.AddEmp_QualificationSaveLinesIcon, ElementType.Xpath);
					webDB.clickAnElement(EmployeeMasterLocators.AddEmp_QualificationSaveLinesIcon, ElementType.Xpath);
					flag = webDB.waitForElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
					if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To add new Skills and Levels in Qualification Details in Add
	 * Employee
	 * 
	 * @author Sakthi-GWL
	 * @param Skill
	 * @param Level
	 * @param addlineflag
	 */
	private void addNewSkillDetailsInQualificationTab(String Skill, String Level, boolean... addlineflag) {
		if (addlineflag != null && addlineflag[0] != false) {
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_SkillAddLinesIcon, ElementType.Xpath);
			if (flag) {
				flag = webDB.clickAnElement(EmployeeMasterLocators.AddEmp_SkillAddLinesIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Skill Add Icon Clicked");
				}
			}
		}
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_SkillData, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_SkillData, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_SkillDataLov, Skill);
			VariableHandler.setEmpSkill(Skill);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LevelData, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LevelData, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_LevelDataLov, Level);
				VariableHandler.setEmpSkillLevel(Level);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_SkillSaveLinesIcon, ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(EmployeeMasterLocators.AddEmp_SkillSaveLinesIcon, ElementType.Xpath);
					webDB.clickAnElement(EmployeeMasterLocators.AddEmp_SkillSaveLinesIcon, ElementType.Xpath);
					flag = webDB.waitForElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Skill Save Icon Clicked and Toast Message Displayed");
					}
					if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used used to add Designations Departments and Grades in Salary
	 * Details in Add Employee
	 * 
	 * @author Sakthi-GWL
	 * @param designation
	 * @param department
	 * @param grade
	 */
	private void addDesignationDepartmentAndGradeInSalaryDetails(String designation, String department, String grade) {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_DesignationLovBtn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_DesignationLovBtn, designation);
			VariableHandler.setEmpSalaryDesignation(designation);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_DepartmentLovBtn, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_DepartmentLovBtn, department);
				VariableHandler.setEmpSalaryDepartment(department);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_GradeLovBtn, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_GradeLovBtn, grade);
					VariableHandler.setEmpSalaryGrade(grade);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To Click Load Allowances in Add Employee
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickLoadAllowanceButtonInSalaryDetails() {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LoadAllowanceBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LoadAllowanceBtn, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AllowanceTableFirstTrashIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Load Allowance Button Clicked and Allowances Displayed");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To fill Allowances Amounts and Verify Totals in Allowance -
	 * Salary Details in Add Employee
	 * 
	 * @author Sakthi-GWL
	 * @param amounts
	 */
	private void fillAllowanceAmountsAndVerifyTotalInSalaryDetails(String[] amounts) {
		int totalAllowanceAmounts = 0;
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AllowanceTableAmountData, ElementType.Xpath);
		if (flag) {
			List<WebElement> elements = webDB.getDriver()
					.findElements(By.xpath(EmployeeMasterLocators.AddEmp_AllowanceTableAmountData));
			for (int i = 0; i < elements.size() - 1; i++) {
				WebElement element = elements.get(i);
				commonFunc.checkRefreshCellsDisappear();
				Actions action = new Actions(webDB.getDriver());
				action.doubleClick(element).build().perform();
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AllowanceTableAmountDataInput,
						ElementType.Xpath);
				if (flag) {
					try {
						JavascriptExecutor js = (JavascriptExecutor) webDB.getDriver();
						WebElement InputElement = webDB.getDriver()
								.findElement(By.xpath(EmployeeMasterLocators.AddEmp_AllowanceTableAmountDataInput));
						js.executeScript("arguments[0].value='" + (amounts[i].toString()) + "';", InputElement);
						totalAllowanceAmounts += Integer.parseInt(amounts[i].toString());
						logs.logging("INFO",
								"Amount Sent in allowances table - " + amounts[i] + " for index: " + (i + 1));
					} catch (Exception e) {
						logs.logging("INFO", "Failed while sent amount in allowances table - " + amounts[i]);
						flag = false;
					}
				}
			}
			saveAllowanceTableInSalary();
			commonFunc.checkRefreshCellsDisappear();
			webDB.waitForElement(EmployeeMasterLocators.AddEmp_AllowanceTableTotalAmountData, ElementType.Xpath);
			String displayingTotalData = webDB
					.getTextFromElement(EmployeeMasterLocators.AddEmp_AllowanceTableTotalAmountData, ElementType.Xpath);
			flag = Integer.parseInt(displayingTotalData) == totalAllowanceAmounts;
			logs.logging("INFO", "Total Allowance Amount Displayed: " + displayingTotalData);
			logs.logging("INFO", "Expected Total Amount by adding sent text: " + totalAllowanceAmounts);
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To save Allowance Tables in Add Employee
	 * 
	 * @author Sakthi-GWL
	 */
	private void saveAllowanceTableInSalary() {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_SaveAllowanceTableBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_SaveAllowanceTableBtn, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AllowanceTableTotalAmountData, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Allowance Save Icon Clicked and Toast Message Displayed");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To click Add Button in Adjustment - Salary Details in Add
	 * Employee
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickAddButtonInAdjustmentSalaryDetails() {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AdjustmentAddIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_AdjustmentAddIcon, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AdjustmentPeriodAmountData, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Adjustment Button is Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To Enable ESI Adjustment Flag in Salary Details in Add
	 * Employee
	 * 
	 * @author Sakthi-GWL
	 */
	private void enableESIAdjustmentFlagsInSalaryDetails() {
//		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_ESI_ElegibleInputBox, ElementType.Xpath);
//		if (flag) {
//			flag = webDB.IsElementSelected(EmployeeMasterLocators.AddEmp_ESI_ElegibleInputBox, ElementType.Xpath);
//			if (!flag) {
//				webDB.waitForElement(EmployeeMasterLocators.AddEmp_ESI_ElegibleLabel, ElementType.Xpath);
//				webDB.clickAnElement(EmployeeMasterLocators.AddEmp_ESI_ElegibleLabel, ElementType.Xpath);
//				logs.logging("INFO", "ESI flag Defaultly Disabled and clicked to enable");
//				commonFunc.checkRefreshCellsDisappear();
//				commonFunc.hardWait(1000);
//				flag = webDB.IsElementSelected(EmployeeMasterLocators.AddEmp_ESI_ElegibleInputBox, ElementType.Xpath);
//				if (!flag) {
//					logs.logging("INFO", "Failed to Enable after Clicked ESI Flag");
//				}
//			}
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_ESI_ElegibleLabel, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_ESI_ElegibleLabel, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (flag) {
				logs.logging("INFO", "ESI flag Enabled Successfully");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To Enable PF Adjustment Flag in Salary Details in Add
	 * Employee
	 * 
	 * @author Sakthi-GWL
	 */
	private void enablePFAdjustmentFlagsInSalaryDetails() {
//		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_PF_ElegibleInputBox, ElementType.Xpath);
//		if (flag) {
//			flag = webDB.IsElementSelected(EmployeeMasterLocators.AddEmp_PF_ElegibleInputBox, ElementType.Xpath);
//			if (!flag) {
//				webDB.waitForElement(EmployeeMasterLocators.AddEmp_PF_ElegibleLabel, ElementType.Xpath);
//				webDB.clickAnElement(EmployeeMasterLocators.AddEmp_PF_ElegibleLabel, ElementType.Xpath);
//				logs.logging("INFO", "PF flag Defaultly Disabled and clicked to Enabled");
//				commonFunc.checkRefreshCellsDisappear();
//				commonFunc.hardWait(500);
//				flag = webDB.IsElementSelected(EmployeeMasterLocators.AddEmp_PF_ElegibleInputBox, ElementType.Xpath);
//				if (!flag) {
//					logs.logging("INFO", "Failed to Enable after Clicked PF Flag");
//				}
//			}
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_PF_ElegibleLabel, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_PF_ElegibleLabel, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (flag) {
				logs.logging("INFO", "PF flag Enabled Successfully");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To Select Element Startyear and period in Adjustment -
	 * Salary Details in Add Employee
	 * 
	 * @author Sakthi-GWL
	 * @param Element
	 * @param StartYear
	 * @param Period
	 */
	private void selectAdjustmentSalaryElementStartYearAndPeriodDetails(String Element, String StartYear,
			String Period) {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AdjustmentElementLovBtn, ElementType.Xpath);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_AdjustmentElementLovBtn, Element);
			VariableHandler.setEmpSalaryAdjmntElement(Element);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AdjustmentStartYearData, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.clickAnElement(EmployeeMasterLocators.AddEmp_AdjustmentStartYearData, ElementType.Xpath);
				flag = webDB.selectDropDownByText(EmployeeMasterLocators.AddEmp_AdjustmentStartYearSelect, StartYear,
						ElementType.Xpath);
				if (flag) {
					VariableHandler.setEmpSalaryAdjmntStartYear(StartYear);
					logs.logging("INFO",
							"Adjustment Start Year Selected Text: " + VariableHandler.getEmpSalaryAdjmntStartYear());
					flag = webDB.waitForClickElement(EmployeeMasterLocators.AddEmp_AdjustmentPeriodData,
							ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.moveToElement(EmployeeMasterLocators.AddEmp_AdjustmentPeriodData, ElementType.Xpath);
						webDB.clickAnElement(EmployeeMasterLocators.AddEmp_AdjustmentPeriodData, ElementType.Xpath);
						flag = webDB.selectDropDownByText(EmployeeMasterLocators.AddEmp_AdjustmentPeriodSelect, Period,
								ElementType.Xpath);
						if (flag) {
							VariableHandler.setEmpSalaryAdjmntPeriod(Period);
							logs.logging("INFO",
									"Adjustment Period Selected Text: " + VariableHandler.getEmpSalaryAdjmntPeriod());
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To fill TotalPeriod and Period Amount in Adjustment - Salary
	 * Details in Add Employee
	 * 
	 * @author Sakthi-GWL
	 * @param TotalPeriod
	 * @param PeriodAmount
	 */
	private void fillAdjustmentSalaryTotalPeriodAndPeriodAmount(String TotalPeriod, String PeriodAmount) {
		commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_AdjustmentTotalPeriodData,
				EmployeeMasterLocators.AddEmp_AdjustmentTotalPeriodDataInput, TotalPeriod);
		VariableHandler.setEmpSalaryAdjmntTotalPeriod(TotalPeriod);
		commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_AdjustmentPeriodAmountData,
				EmployeeMasterLocators.AddEmp_AdjustmentPeriodAmountDataInput, PeriodAmount);
		VariableHandler.setEmpSalaryAdjmntPeriodAmnt(PeriodAmount);
	}

	/**
	 * This Method used To Click Activate Icon in Last Added Adjustment in Salary
	 * Details in Add Employee
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickActivateIconInLastAddedAdjustment() {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AdjustmentActivateIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_AdjustmentActivateIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_AdjustmentActivateIcon, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AdjustmentOkBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(EmployeeMasterLocators.AddEmp_AdjustmentOkBtn, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Salary Tab Adjustment Activate Icon Clicked and Toast Message Displayed");
				}
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To Click Delete Icon in Last Added Adjustment in Salary
	 * Details in Add Employee
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickDeleteIconInLastAddedAdjustment() {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AdjustmentTableFirstTrashIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_AdjustmentActivateIcon, ElementType.Xpath);
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_AdjustmentTableFirstTrashIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_AdjustmentTableFirstTrashIcon, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AdjustmentOkBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(EmployeeMasterLocators.AddEmp_AdjustmentOkBtn, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Salary Tab Adjustment Delete Icon Clicked And Toast Message Displayed");
				}
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To Click Save icon in Adjustments - Salary Details in Add
	 * Employee
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickSaveIconInSalaryAdjustment() {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_SalaryAdjustmentSaveBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_SalaryAdjustmentSaveBtn, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AdjustmentActivateIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Salary Tab Adjustment Save Icon Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To Click Add icon Leave Eligibility - Leave Details in Add
	 * Employee
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickAddIconLeaveEligibilityInLeaveDetails() {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityAddBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityAddBtn, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityAddBtn, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityLeaveLovBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Leave Eligibility Add Icon Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To fill Leave and Days in Leave Eligibility - Leave Details
	 * in Add Employee
	 * 
	 * @author Sakthi-GWL
	 * @param Leave
	 * @param Days
	 */
	private void fillLeaveAndDaysInLeaveEligibility(String Leave, String Days) {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityLeaveLovBtn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_LeaveEligibilityLeaveLovBtn, Leave);
			VariableHandler.setEmpLeaveElgblLeave(Leave);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityDescription, ElementType.Xpath);
			commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_LeaveEligibilityDaysData,
					EmployeeMasterLocators.AddEmp_LeaveEligibilityDaysInput, Days);
			VariableHandler.setEmpLeaveElgblDays(Days);
			logs.logging("INFO", "Days Entered in Leave Eligibility: " + Days);
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To fill Every and Eligible Days in Leave Eligibility in
	 * Leave Details in Add Employee
	 * 
	 * @author Sakthi-GWL
	 * @param Every
	 * @param EligibleDays
	 */
	private void fillEveryAndEligibleDaysInLeaveEligibility(String Every, String EligibleDays) {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityEveryData, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityEveryData, ElementType.Xpath);
			webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityEveryData, ElementType.Xpath);
			flag = webDB.selectDropDownByText(EmployeeMasterLocators.AddEmp_LeaveEligibilityEveryData, Every,
					ElementType.Xpath);
			if (flag) {
				VariableHandler.setEmpLeaveElgblEvery(Every);
				logs.logging("INFO",
						"Leave Eligibility Every Data Selected Text: " + VariableHandler.getEmpLeaveElgblEvery());
				commonPayrollFunc.addDetailInTableWithoutLovButton(
						EmployeeMasterLocators.AddEmp_LeaveEligibilityEligibleDaysData,
						EmployeeMasterLocators.AddEmp_LeaveEligibilityEligibleDaysInput, EligibleDays);
				VariableHandler.setEmpLeaveElgblEligibileDays(EligibleDays);
				logs.logging("INFO", "Eligible Days Entered in Leave Eligibility: " + EligibleDays);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To RoundOff and Max Carry Frequency in Leave Eligibility -
	 * Leave Details in Add Employee
	 * 
	 * @author Sakthi-GWL
	 * @param RoundOff
	 * @param MaxCarryFreq
	 */
	private void fillRoundOffDaysAndMaxCarryFrequencyInLeaveEligibility(String RoundOff, String MaxCarryFreq) {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityRoundOffData, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityRoundOffData, ElementType.Xpath);
			webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityRoundOffSelect, ElementType.Xpath);
			flag = webDB.selectDropDownByText(EmployeeMasterLocators.AddEmp_LeaveEligibilityRoundOffSelect, RoundOff,
					ElementType.Xpath);
			if (flag) {
				VariableHandler.setEmpLeaveElgblRoundOff(RoundOff);
				logs.logging("INFO", "Leave Eligibility RoundOff Selected Text: " + RoundOff);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMaxCarryFreqData,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMaxCarryFreqData,
							ElementType.Xpath);
					webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMaxCarryFreqSelect,
							ElementType.Xpath);
					flag = webDB.selectDropDownByText(EmployeeMasterLocators.AddEmp_LeaveEligibilityMaxCarryFreqSelect,
							MaxCarryFreq, ElementType.Xpath);
					if (flag) {
						VariableHandler.setEmpLeaveElgblMaxCarryFreq(MaxCarryFreq);
						logs.logging("INFO", "Leave Eligibility Max Carry Frequency Selected Text: " + MaxCarryFreq);
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To fill Max Carry Day and Effective year in Leave
	 * Eligibility in Leave Details in Add Employee
	 * 
	 * @author Sakthi-GWL
	 * @param MaxCarryDay
	 * @param EffYear
	 */
	private void fillMaxCarryDayAndEffectiveYearInLeaveEligibility(String MaxCarryDay, String EffYear) {
		commonPayrollFunc.addDetailInTableWithoutLovButton(
				EmployeeMasterLocators.AddEmp_LeaveEligibilityMaxCarryDayData,
				EmployeeMasterLocators.AddEmp_LeaveEligibilityMaxCarryDayInput, MaxCarryDay);
		VariableHandler.setEmpLeaveElgblMaxCarryDay(MaxCarryDay);
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityEffYearData, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityEffYearData, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityEffYearData, ElementType.Xpath);
			webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityEffYearSelect, ElementType.Xpath);
			flag = webDB.selectDropDownByText(EmployeeMasterLocators.AddEmp_LeaveEligibilityEffYearSelect, EffYear,
					ElementType.Xpath);
			if (flag) {
				VariableHandler.setEmpLeaveElgblEffYear(EffYear);
				logs.logging("INFO", "Leave Eligibility Effective Year Selected Text: " + EffYear);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To fill Min and Max Days in Leave Eligibility in Leave
	 * Details in Add Employee
	 * 
	 * @author Sakthi-GWL
	 * @param MaxDay
	 * @param MinDay
	 */
	private void fillMaxDaysAndMinDaysInLeaveEligibility(String MaxDay, String MinDay) {
		commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_LeaveEligibilityMaxDaysData,
				EmployeeMasterLocators.AddEmp_LeaveEligibilityMaxDaysInput, MaxDay);
		VariableHandler.setEmpLeaveElgblMaxDay(MaxDay);
		commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_LeaveEligibilityMinDaysData,
				EmployeeMasterLocators.AddEmp_LeaveEligibilityMinDaysInput, MinDay);
		VariableHandler.setEmpLeaveElgblMinDay(MinDay);
	}

	/**
	 * This Method used To click Save icon in Leave Eligibility in Leave Details in
	 * Add Employee
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickSaveIconInLeaveEligibility() {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilitySaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_LeaveEligibilitySaveIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilitySaveIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			if (flag) {
				logs.logging("INFO", "Leave Eligibility Save Icon Clicked and Toast Message Displayed");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To Click Post button And Verify Employee Posted in Add
	 * Employee
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickPostButtonAndVerifyEmployeePosted() {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmployeePostButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmployeePostButton, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmployeePostButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmployeeUpdateButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Leave Eligibility Post Icon Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To validated Updated Leave Details after Employee posted
	 * with Leave Entitlement in Setup Screen in Add Employee
	 * 
	 * @author Sakthi-GWL
	 */
	private void validateUpdatedLeaveDetailsWithSetupScreenEntitlement() {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityLeaveData, ElementType.Xpath);
		if (flag) {
			int a = 0;
			List<WebElement> LeaveIDs = webDB.getDriver()
					.findElements(By.xpath(EmployeeMasterLocators.AddEmp_LeaveEligibilityLeaveData));
			for (int i = 0; i < LeaveIDs.size(); i++) {
				if (LeaveIDs.size() == VariableHandler.getEmpSetupScreenGreenFlagLeaveIDs().size()) {
					for (String leaveentitle : VariableHandler.getEmpSetupScreenGreenFlagLeaveIDs()) {
						if (LeaveIDs.get(i).getText().equals(leaveentitle)) {
							flag = true;
							a++;
						} else {
							flag = false;
						}
					}
				} else {
					flag = false;
				}
			}
			flag = (a == VariableHandler.getEmpSetupScreenGreenFlagLeaveIDs().size());
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Validated the Updated Leave Details With Setup Screen Entitlement Leave Ids");
	}

	/**
	 * This Method used To upload Sign Image and verify its Uploaded in Add Employee
	 * 
	 * @author Sakthi-GWL
	 * @param ImageName
	 */
	private void uploadSignImageInAddEmployeeDetails(String ImageName) {
		String ImageDir = VariableHandler.testdataDir + ImageName;
		File file = new File(ImageDir);
		flag = webDB.waitForClickElement(EmployeeMasterLocators.AddEmp_SignUploadbtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_SignUploadbtn, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_SignUploadbtn, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LoadIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sign Upload Button Clicked Load iframe Displayed");
				webDB.switchToFrame(EmployeeMasterLocators.AddEmp_LoadIframe, ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_SignUploadInput, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_SignUploadInput, file.getAbsolutePath(),
							ElementType.Xpath);
					flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_SignUploadFileNameText,
							ElementType.Xpath);
					if (flag) {
						flag = webDB.getTextFromElement(EmployeeMasterLocators.AddEmp_SignUploadFileNameText,
								ElementType.Xpath).equalsIgnoreCase(ImageName);
						if (flag) {
							logs.logging("INFO", ImageName + " - was uploaded and FileName Displayed");
							webDB.waitForElement(EmployeeMasterLocators.AddEmp_UploadBtn, ElementType.Xpath);
							webDB.clickAnElement(EmployeeMasterLocators.AddEmp_UploadBtn, ElementType.Xpath);
							flag = checkSignImageUploadedWithClearButton();
							if (flag) {
								logs.logging("INFO", "Upload Button Clicked and File Uploaded SuccessFully");
							}
						}
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To check sign image upload or not in Add Employee
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	private boolean checkSignImageUploadedWithClearButton() {
		webDB.switchToDefaultContent();
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForClickElement(EmployeeMasterLocators.AddEmp_SignUploadbtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_SignUploadbtn, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_SignUploadbtn, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LoadIframe, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(EmployeeMasterLocators.AddEmp_LoadIframe, ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_ClearUploadBtn, ElementType.Xpath);
				if (flag) {
					webDB.switchToDefaultContent();
					flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_CloseFrameIcon, ElementType.Xpath);
					if (flag) {
						webDB.moveToElement(EmployeeMasterLocators.AddEmp_CloseFrameIcon, ElementType.Xpath);
						webDB.clickAnElement(EmployeeMasterLocators.AddEmp_CloseFrameIcon, ElementType.Xpath);
					}
				}
			}
		}
		return flag;
	}

	/**
	 * This Method used To Upload Profile Image and verify its uploaded in Add
	 * Employee
	 * 
	 * @author Sakthi-GWL
	 * @param ImageName
	 */
	private void uploadProfileImageInAddEmployeeDetails(String ImageName) {
		String ImageDir = VariableHandler.testdataDir + ImageName;
		File file = new File(ImageDir);
		flag = webDB.waitForClickElement(EmployeeMasterLocators.AddEmp_ProfileImg, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_ProfileImg, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LoadIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Profile Image Clicked Load iframe Displayed");
				webDB.switchToFrame(EmployeeMasterLocators.AddEmp_LoadIframe, ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_ProfileImgUploadInput, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_ProfileImgUploadInput,
							file.getAbsolutePath(), ElementType.Xpath);
					flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_ProfileUploadFileNameText,
							ElementType.Xpath);
					if (flag) {
						flag = webDB.getTextFromElement(EmployeeMasterLocators.AddEmp_ProfileUploadFileNameText,
								ElementType.Xpath).equalsIgnoreCase(ImageName);
						if (flag) {
							logs.logging("INFO", ImageName + " - was uploaded and File Name Displayed");
							webDB.waitForElement(EmployeeMasterLocators.AddEmp_UploadBtn, ElementType.Xpath);
							webDB.clickAnElement(EmployeeMasterLocators.AddEmp_UploadBtn, ElementType.Xpath);
							flag = checkProfileImageUploadedWithClearButton();
							if (flag) {
								logs.logging("INFO", "Upload Button Clicked and File Uploaded SuccessFully");
							}
						}
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To check profile image uploaded or not in Add Employee
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	private boolean checkProfileImageUploadedWithClearButton() {
		webDB.switchToDefaultContent();
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForClickElement(EmployeeMasterLocators.AddEmp_ProfileImg, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_ProfileImg, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_ProfileImg, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LoadIframe, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(EmployeeMasterLocators.AddEmp_LoadIframe, ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_ClearUploadBtn, ElementType.Xpath);
				if (flag) {
					webDB.switchToDefaultContent();
					flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_CloseFrameIcon, ElementType.Xpath);
					if (flag) {
						webDB.moveToElement(EmployeeMasterLocators.AddEmp_CloseFrameIcon, ElementType.Xpath);
						webDB.clickAnElement(EmployeeMasterLocators.AddEmp_CloseFrameIcon, ElementType.Xpath);
					}
				}
			}
		}
		return flag;
	}

	/**
	 * This method used to click Additional info button
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickAdditionalInfoButton() {
		flag = webDB.waitForClickElement(EmployeeMasterLocators.AddEmp_AdditionalInfoBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_AdditionalInfoBtn, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_AdditionalInfoBtn, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AddInfoIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Additional Info Button Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to switch to add info iframe
	 * 
	 * @author Sakthi-GWL
	 */
	private void addInfoIframeSwitch() {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AddInfoIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(EmployeeMasterLocators.AddEmp_AddInfoIframe, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AddInfoPrevExpYear, ElementType.Xpath);
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to submit Home type and Previous Experience Year and Months
	 * in Additional Info
	 * 
	 * @author Sakthi-GWL
	 * @param Year
	 * @param Month
	 */
	private void submitHomeTypeAndPreviousExperienceInAdditionalInfo(String HouseType, String Year, String Month) {
		addInfoIframeSwitch();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AddInfoPrevExpYear, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_AddInfoPrevExpYear, Year, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AddInfoPrevExpMonth, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Previous Year Sent in Additional Info: " + Year);
				webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_AddInfoPrevExpMonth, Month, ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AddInfoHouseType, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Previous Month Sent in Additional Info: " + Month);
					flag = webDB.selectDropDownByText(EmployeeMasterLocators.AddEmp_AddInfoHouseType, HouseType,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "House Type Selected in Additional Info: " + HouseType);
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to submit vehicle Details and Stay type in Additional Info
	 * 
	 * @author Sakthi-GWL
	 * @param StayType
	 * @param VehicleFlag
	 * @param VehicleNo
	 */
	private void submitStayTypeAndVehicleDetailsInAdditionalInfo(String StayType, String VehicleFlag,
			String... VehicleNo) {
		addInfoIframeSwitch();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AddInfoStayType, ElementType.Xpath);
		if (flag) {
			webDB.selectDropDownByText(EmployeeMasterLocators.AddEmp_AddInfoStayType, StayType, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AddInfoVehicleSelect, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Stay Type Selected: " + StayType);
				flag = webDB.selectDropDownByText(EmployeeMasterLocators.AddEmp_AddInfoVehicleSelect, VehicleFlag,
						ElementType.Xpath);
				if (flag && VehicleFlag.equalsIgnoreCase("Yes") && VehicleNo != null) {
					logs.logging("INFO", "Two Wheeler Selection: " + VehicleFlag);
					flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AddInfoVehicleNoInput, ElementType.Xpath);
					if (flag) {
						flag = webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_AddInfoVehicleNoInput,
								Arrays.toString(VehicleNo).replace("[", "").replace("]", ""), ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Two Wheeler Number Sent: " + Arrays.toString(VehicleNo));
						}
					}
				} else if (flag && VehicleFlag.equalsIgnoreCase("No")) {
					logs.logging("INFO", "Two Wheeler Selection: " + VehicleFlag);
				}
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to submit Office Details in Additional Info
	 * 
	 * @author Sakthi-GWL
	 * @param ApplNo
	 * @param IDCardNo
	 * @param PrevEmpID
	 */
	private void submitOfficeDetailsInAdditionalInfo(String ApplNo, String IDCardNo, String PrevEmpID) {
		addInfoIframeSwitch();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AddInfoOfcApplID, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_AddInfoOfcApplID, ApplNo, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AddInfoOfcIDCardNo, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Office Application Id Entered: " + ApplNo);
				webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_AddInfoOfcIDCardNo, IDCardNo,
						ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AddInfoOfcPrevEmpID, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Office Id Card No Entered: " + IDCardNo);
					flag = webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_AddInfoOfcPrevEmpID, PrevEmpID,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Office Previous Employee Id Entered: " + PrevEmpID);
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to submit Mobile Offcie Email office and Personal in
	 * Additional Info
	 * 
	 * @author Sakthi-GWL
	 * @param MobileOfc
	 * @param EmailOfc
	 * @param EmailPer
	 */
	private void submitMobileEmailOfcAndPersonalInAdditionalInfo(String MobileOfc, String EmailOfc, String EmailPer) {
		addInfoIframeSwitch();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AddInfoWorkLocationMobileOfc, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_AddInfoWorkLocationMobileOfc, MobileOfc,
					ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AddInfoWorkLocationEmailOfc, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Mobile Office Entered in Work Location: " + MobileOfc);
				webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_AddInfoWorkLocationEmailOfc, EmailOfc,
						ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AddInfoWorkLocationEmailPersonal,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Email Office Entered in Work Location: " + EmailOfc);
					flag = webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_AddInfoWorkLocationEmailPersonal,
							EmailPer, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Email Personal Entered in Work Location: " + EmailPer);
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to submit Building Section and Room Numbers in Additional
	 * Info
	 * 
	 * @author Sakthi-GWL
	 * @param BuildingNo
	 * @param SectionNo
	 * @param SimCardNo
	 */
	private void submitBuildingSectionAndRoomInAdditionalInfo(String BuildingNo, String SectionNo, String SimCardNo) {
		addInfoIframeSwitch();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AddInfoWorkLocationBuildingNo, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_AddInfoWorkLocationBuildingNo, BuildingNo,
					ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AddInfoWorkLocationSectionNo, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Building Number Entered in Work Location: " + BuildingNo);
				webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_AddInfoWorkLocationSectionNo, SectionNo,
						ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AddInfoWorkLocationSimCardNo,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Section Number Entered in Work Location: " + SectionNo);
					flag = webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_AddInfoWorkLocationSimCardNo,
							SimCardNo, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Room Number Entered in Work Location: " + SimCardNo);
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to submit Personal and Alternate Mobile and SimCard No in
	 * Additional Info
	 * 
	 * @author Sakthi-GWL
	 * @param MobilePersonal
	 * @param AlterNo
	 * @param SimCardNo
	 */
	private void submitPersonalAlterMobileSimNoInAdditionalInfo(String MobilePersonal, String AlterNo,
			String SimCardNo) {
		addInfoIframeSwitch();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AddInfoWorkLocationMobilePersonalNo,
				ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_AddInfoWorkLocationMobilePersonalNo, MobilePersonal,
					ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AddInfoWorkLocationAlterMobileNo,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Mobile Personal Number Entered in Work Location: " + MobilePersonal);
				webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_AddInfoWorkLocationAlterMobileNo, AlterNo,
						ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AddInfoWorkLocationSimCardNo,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Alternate Mobile Number Entered in Work Location: " + AlterNo);
					flag = webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_AddInfoWorkLocationSimCardNo,
							SimCardNo, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Sim Card Number Entered in Work Location: " + SimCardNo);
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to submit Phone Extension and FAX Numbers in Additional Info
	 * 
	 * @author Sakthi-GWL
	 * @param PhoneNo
	 * @param ExtensionNo
	 * @param FaxNo
	 */
	private void submitPhoneExtensionAndFaxNoInAdditionalInfo(String PhoneNo, String ExtensionNo, String FaxNo) {
		addInfoIframeSwitch();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AddInfoWorkLocationPhoneNo, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_AddInfoWorkLocationPhoneNo, PhoneNo,
					ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AddInfoWorkLocationExtensionNo,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Phone Number Entered in Work Location: " + PhoneNo);
				webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_AddInfoWorkLocationExtensionNo, ExtensionNo,
						ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AddInfoWorkLocationFaxNo, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Extension Number Entered in Work Location: " + ExtensionNo);
					flag = webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_AddInfoWorkLocationFaxNo, FaxNo,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Fax Number Entered in Work Location: " + FaxNo);
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click Tick icon in Additional Info
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickTickIconInAdditionalInfo() {
		addInfoIframeSwitch();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AddInfoSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_AddInfoSaveButton, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_AddInfoSaveButton, ElementType.Xpath);
			webDB.switchToDefaultContent();
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To Click Dependent Details navigation bar in Add Employee
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickDependentDetailsSideNavBar() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_DependentSideNav, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_DependentSideNav, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_DependentAddIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Employee Dependent Details Side Nav Bar Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To Click Add Icon in Dependent Details Add Employee
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickAddIconInDependentDetails() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_DependentAddIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_DependentAddIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_DependentAddIcon, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_DependentNameData, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Employee Dependent Details Add Icon Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To fill required Details in Dependent Details in Add
	 * Employee
	 * 
	 * @author Sakthi-GWL
	 * @param DependentName
	 * @param RelationShip
	 * @param DOB
	 * @param AadharNo
	 * @param EmailId
	 * @param ContactNo
	 * @param MonthlyEarning
	 */
	private void fillRequiredDetailsInDependentDetails(String DependentName, String RelationShip, String DOB,
			String AadharNo, String EmailId, String ContactNo, String MonthlyEarning) {
		commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_DependentNameData,
				EmployeeMasterLocators.AddEmp_DependentNameInput, DependentName);
		commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_DependentAadharNoData,
				EmployeeMasterLocators.AddEmp_DependentAadharNoInput, AadharNo);
		commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_DependentContactNoData,
				EmployeeMasterLocators.AddEmp_DependentContactNoInput, ContactNo);
		commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_DependentEmailIdData,
				EmployeeMasterLocators.AddEmp_DependentEmailIdInput, EmailId);
		commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_DependentDOBData,
				EmployeeMasterLocators.AddEmp_DependentDOBInput, DOB);
		commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_DependentMonthEarningData,
				EmployeeMasterLocators.AddEmp_DependentMonthEarningInput, MonthlyEarning);
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_DependentRelationshipData, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_DependentRelationshipData, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_DependentRelationshipSelect, ElementType.Xpath);
			if (flag) {
				flag = webDB.selectDropDownByText(EmployeeMasterLocators.AddEmp_DependentRelationshipSelect,
						RelationShip, ElementType.Xpath);
				if (flag) {
					logs.logging("Relationship Selected in Dependent Details: ", RelationShip);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To Click Save Icon in Dependent Details Add Employee
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickSaveIconInDependentDetails() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_DependentSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_DependentSaveIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_DependentSaveIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			if (flag) {
				logs.logging("INFO", "Dependent Details Saved Successfully Toast Message Displayed");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click update button in Add Employee
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickUpdateButtonInAddEmployee() {
		flag = webDB.waitForClickElement(EmployeeMasterLocators.AddEmp_UpdateButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_UpdateButton, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_UpdateButton, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_UpdateIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Update Button Clicked in Add Employee");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to switch to update iframe
	 * 
	 * @author Sakthi-GWL
	 */
	private void switchToUpdateIframe() {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_UpdateIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(EmployeeMasterLocators.AddEmp_UpdateIframe, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_UpdateParticularsTab, ElementType.Xpath);
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click Employee Particular Tab in update Employee
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickEmployeeParticularTabInUpdateEmployee() {
		switchToUpdateIframe();
		flag = webDB.waitForClickElement(EmployeeMasterLocators.AddEmp_UpdateParticularsTab, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_UpdateParticularsTab, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_UpdateParticularsTab, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_UpdateFirstName, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Employee Particular Tab Clicked in Update Employee");
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click Save Icon inEmployee Particular Tab in update
	 * Employee
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickSaveIconInEmployeeParticular() {
		switchToUpdateIframe();
		flag = webDB.waitForClickElement(EmployeeMasterLocators.AddEmp_UpdateEmpParticularSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_UpdateEmpParticularSaveIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_UpdateEmpParticularSaveIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			if (flag) {
				logs.logging("INFO", "Employee Particular Tab Clicked in Update Employee");
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To update Gender First Name and Last name in update Employee
	 * 
	 * @author Sakthi-GWL
	 * @param Gender
	 * @param FName
	 * @param LName
	 */
	private void updateEmployeeGenderFirstAndLastNameInParticularTab(String Gender, String FName, String LName) {
		switchToUpdateIframe();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_UpdateGender, ElementType.Xpath);
		if (flag) {
			webDB.selectDropDownByText(EmployeeMasterLocators.AddEmp_UpdateGender, Gender, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_UpdateFirstName, ElementType.Xpath);
			if (flag) {
				VariableHandler.setEmpGender(Gender);
				logs.logging("INFO", "Gender Updated for the Employee: " + Gender);
				webDB.clearText(EmployeeMasterLocators.AddEmp_UpdateFirstName, ElementType.Xpath);
				webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_UpdateFirstName, FName, ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_UpdateLastName, ElementType.Xpath);
				if (flag) {
					VariableHandler.setEmpFirstName(FName);
					logs.logging("INFO", "First Name of the Employee Updated: " + FName);
					webDB.clearText(EmployeeMasterLocators.AddEmp_UpdateLastName, ElementType.Xpath);
					flag = webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_UpdateLastName, LName,
							ElementType.Xpath);
					if (flag) {
						VariableHandler.setEmpLastName(LName);
						logs.logging("INFO", "Last Name of the Employee Updated: " + LName);
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To update DOB and Calender in update Employee
	 * 
	 * @author Sakthi-GWL
	 * @param DOB
	 * @param Calender
	 */
	private void updateEmployeeDOBAndCalenderInParticularTab(String DOB, String Calender) {
		switchToUpdateIframe();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_UpdateDOB, ElementType.Xpath);
		if (flag) {
			webDB.clearText(EmployeeMasterLocators.AddEmp_UpdateDOB, ElementType.Xpath);
			webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_UpdateDOB, DOB, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_UpdateCalenderLovbtn, ElementType.Xpath);
			if (flag) {
				VariableHandler.setEmpDOB(DOB);
				logs.logging("INFO", "Date of Birth of the Employee Updated: " + DOB);
				commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_UpdateCalenderLovbtn, Calender);
				VariableHandler.setEmpCalendar(Calender);
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To update Shift Marital Status and Zone in update Employee
	 * 
	 * @author Sakthi-GWL
	 * @param Shift
	 * @param Zone
	 * @param MaritalStatus
	 */
	private void updateEmployeeShiftMaritalStatusAndZone(String Shift, String MaritalStatus, String Zone) {
		switchToUpdateIframe();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_UpdateShift, ElementType.Xpath);
		if (flag) {
			webDB.selectDropDownByText(EmployeeMasterLocators.AddEmp_UpdateShift, Shift, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_UpdateZoneLovbtn, ElementType.Xpath);
			if (flag) {
				VariableHandler.setEmpShift(Shift);
				logs.logging("INFO", "Shift Updated for the Employee: " + Shift);
				commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_UpdateZoneLovbtn, Zone);
				VariableHandler.setEmpZone(Zone);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_UpdateMaritalStatus, ElementType.Xpath);
				if (flag) {
					flag = webDB.selectDropDownByText(EmployeeMasterLocators.AddEmp_UpdateMaritalStatus, MaritalStatus,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Marital Status Updated for the Employee: " + MaritalStatus);
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To update Category and Account Category in update Employee
	 * 
	 * @author Sakthi-GWL
	 * @param Category
	 * @param AccountCategory
	 */
	private void updateEmployeeCategoryAndAccountCategory(String Category, String AccountCategory) {
		switchToUpdateIframe();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_UpdateCategoryLovbtn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_UpdateCategoryLovbtn, Category);
			VariableHandler.setEmpCompanyCategory(Category);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_UpdateAccountCategoryLovbtn, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_UpdateAccountCategoryLovbtn,
						AccountCategory);
				VariableHandler.setEmpAccountCategory(AccountCategory);
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To update Payroll Group and Notice Period in update Employee
	 * 
	 * @author Sakthi-GWL
	 * @param PayrollGrp
	 * @param NoticePeriod
	 */
	private void updateEmployeePayrollGrpAndNoticePeriod(String PayrollGrp, String NoticePeriod) {
		switchToUpdateIframe();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_UpdatePayrollGroupLovbtn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_UpdatePayrollGroupLovbtn,
					PayrollGrp);
			VariableHandler.setEmpPayrollGroup(PayrollGrp);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_UpdateNoticePeriod, ElementType.Xpath);
			if (flag) {
				webDB.clearText(EmployeeMasterLocators.AddEmp_UpdateNoticePeriod, ElementType.Xpath);
				flag = webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_UpdateNoticePeriod, NoticePeriod,
						ElementType.Xpath);
				if (flag) {
					VariableHandler.setEmpNoticePeriod(NoticePeriod);
					logs.logging("INFO", "Notice Period Updated for the Employee: " + NoticePeriod);
				}
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click Address Tab in update Employee
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickAddressTabInUpdateEmployee() {
		switchToUpdateIframe();
		flag = webDB.waitForClickElement(EmployeeMasterLocators.AddEmp_UpdateAddressTab, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_UpdateAddressTab, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_UpdateAddressTab, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_UpdateAddress1, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Address Tab Clicked in Update Employee");
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to fill Address 1,2 and 3 in Address Tab update Employee
	 * 
	 * @author Sakthi-GWL
	 * @param Address1
	 * @param Address2
	 * @param Address3
	 */
	private void updateEmployeeAllAddressInAddressTab(String Address1, String Address2, String Address3) {
		switchToUpdateIframe();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_UpdateAddress1, ElementType.Xpath);
		if (flag) {
			webDB.clearText(EmployeeMasterLocators.AddEmp_UpdateAddress1, ElementType.Xpath);
			webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_UpdateAddress1, Address1, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_UpdateAddress2, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Address 1 Updated for the Employee: " + Address1);
				webDB.clearText(EmployeeMasterLocators.AddEmp_UpdateAddress2, ElementType.Xpath);
				webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_UpdateAddress2, Address2, ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_UpdateAddress3, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Address 2 Updated for the Employee: " + Address2);
					webDB.clearText(EmployeeMasterLocators.AddEmp_UpdateAddress3, ElementType.Xpath);
					flag = webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_UpdateAddress3, Address3,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Address 3 Updated for the Employee: " + Address3);
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to fill City ZipCode and Telephone in Address Tab update
	 * Employee
	 * 
	 * @author Sakthi-GWL
	 * @param City
	 * @param ZipCode
	 * @param Telephone
	 */
	private void updateEmpCityZipCodeAndTelephoneInAddressTab(String City, String ZipCode, String Telephone) {
		switchToUpdateIframe();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_UpdateAddressCityLovBtn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_UpdateAddressCityLovBtn, City);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_UpdateAddressZipCode, ElementType.Xpath);
			if (flag) {
				webDB.clearText(EmployeeMasterLocators.AddEmp_UpdateAddressZipCode, ElementType.Xpath);
				webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_UpdateAddressZipCode, ZipCode,
						ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_UpdateAddressTelephone, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Zip Code Updated for the Employee: " + ZipCode);
					webDB.clearText(EmployeeMasterLocators.AddEmp_UpdateAddressTelephone, ElementType.Xpath);
					flag = webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_UpdateAddressTelephone, Telephone,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Telephone Updated for the Employee: " + Telephone);
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click Save Icon in Address Tab in update Employee
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickSaveIconInAddress() {
		switchToUpdateIframe();
		flag = webDB.waitForClickElement(EmployeeMasterLocators.AddEmp_UpdateEmpAddressSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_UpdateEmpAddressSaveIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_UpdateEmpAddressSaveIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			if (flag) {
				logs.logging("INFO", "Address Tab Save Icon Clicked in Update Employee");
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}
}
