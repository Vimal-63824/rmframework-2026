package commonfunctions.payroll;

import static org.testng.Assert.assertTrue;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 5, 2025
 */
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.payroll.EmployeeMasterLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class EmployeeMasterFunctions {
	private boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static CommonPayrollFunctions commonPayrollFunc = new CommonPayrollFunctions();
	private static ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	private static Random random = new Random();

	/**
	 * This Method used To Check the Employee Creation with Particulars details
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyEmployeeCreationParticularsDetails() {
		try {
			commonFunc.navigateEmployeeMasterScreenInPayroll();
			clickAddIconAddEmployee();
			clickEmployeeMasterSideNavBar();
			selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
					commonFunc.getDataFromTestDataSheet("Emp_FName"),
					(commonFunc.getDataFromTestDataSheet("Emp_LName") + RandomStringUtils.randomAlphanumeric(5)));
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
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
						commonFunc.getDataFromTestDataSheet("Emp_FName"),
						(commonFunc.getDataFromTestDataSheet("Emp_LName") + RandomStringUtils.randomAlphanumeric(5)));
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
			addNewContactDetailsInQualificationTab(commonFunc.getDataFromTestDataSheet("EmpDependentName"),
					commonFunc.getDataFromTestDataSheet("EmpDependentRelation"),
					commonFunc.randomMobileNumberGenerator(), commonFunc.getDataFromTestDataSheet("EmpAddress"),
					commonFunc.getDataFromTestDataSheet("Emp_City"), true);
			addNewQualificationDetailsInQualificationTab(commonFunc.getDataFromTestDataSheet("Emp_Qualification"),
					commonFunc.getDataFromTestDataSheet("Emp_BranchofStudy"),
					commonFunc.getDataFromTestDataSheet("EmpInstitute"), String.valueOf(random.nextInt(4)),
					commonFunc.getDataFromTestDataSheet("Emp_YearOfPassing"), RandomStringUtils.randomAlphabetic(1),
					RandomStringUtils.randomNumeric(2), true);
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
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
						commonFunc.getDataFromTestDataSheet("Emp_FName"),
						(commonFunc.getDataFromTestDataSheet("Emp_LName") + RandomStringUtils.randomAlphanumeric(5)));
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
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
						commonFunc.getDataFromTestDataSheet("Emp_FName"),
						(commonFunc.getDataFromTestDataSheet("Emp_LName") + RandomStringUtils.randomAlphanumeric(5)));
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
				VariableHandler.setAddEmployeDefaultExecution(true);
			}
			clickSalarySideNavBar();
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
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
						commonFunc.getDataFromTestDataSheet("Emp_FName"),
						(commonFunc.getDataFromTestDataSheet("Emp_LName") + RandomStringUtils.randomAlphanumeric(5)));
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
				clickQualificationSkillSideNavBar();
				addNewContactDetailsInQualificationTab(commonFunc.getDataFromTestDataSheet("EmpDependentName"),
						commonFunc.getDataFromTestDataSheet("EmpDependentRelation"),
						commonFunc.randomMobileNumberGenerator(), commonFunc.getDataFromTestDataSheet("EmpAddress"),
						commonFunc.getDataFromTestDataSheet("Emp_City"), true);
				addNewQualificationDetailsInQualificationTab(commonFunc.getDataFromTestDataSheet("Emp_Qualification"),
						commonFunc.getDataFromTestDataSheet("Emp_BranchofStudy"),
						commonFunc.getDataFromTestDataSheet("EmpInstitute"), String.valueOf(random.nextInt(4)),
						commonFunc.getDataFromTestDataSheet("Emp_YearOfPassing"), RandomStringUtils.randomAlphabetic(1),
						RandomStringUtils.randomNumeric(2), true);
				addNewSkillDetailsInQualificationTab(commonFunc.getDataFromTestDataSheet("Emp_Skill"),
						commonFunc.getDataFromTestDataSheet("Emp_Level"), true);
				clickDependentDetailsSideNavBar();
				clickAddIconInDependentDetails();
				fillRequiredDetailsInDependentDetails(commonFunc.getDataFromTestDataSheet("EmpDependentName"),
						commonFunc.getDataFromTestDataSheet("EmpDependentRelation"),
						commonFunc.getDataFromTestDataSheet("EmpDependentRelationDOB"),
						RandomStringUtils.randomNumeric(12), commonFunc.randomEmailIdGenerator(),
						commonFunc.randomMobileNumberGenerator(), RandomStringUtils.randomNumeric(5));
				clickSaveIconInDependentDetails();
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
					commonFunc.getDataFromTestDataSheet("EmpLeaveElgblMinDay"),
					commonFunc.getDataFromTestDataSheet("EmpLeaveElgblMinEncashDay"));
			clickSaveIconInLeaveEligibility();
			clickActiveIconForLeaveType(commonFunc.getDataFromTestDataSheet("LeaveElgblLeave"));
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
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
						commonFunc.getDataFromTestDataSheet("Emp_FName"),
						(commonFunc.getDataFromTestDataSheet("Emp_LName") + RandomStringUtils.randomAlphanumeric(5)));
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
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
						commonFunc.getDataFromTestDataSheet("Emp_FName"),
						(commonFunc.getDataFromTestDataSheet("Emp_LName") + RandomStringUtils.randomAlphanumeric(5)));
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
				clickQualificationSkillSideNavBar();
				addNewContactDetailsInQualificationTab(commonFunc.getDataFromTestDataSheet("EmpDependentName"),
						commonFunc.getDataFromTestDataSheet("EmpDependentRelation"),
						commonFunc.randomMobileNumberGenerator(), commonFunc.getDataFromTestDataSheet("EmpAddress"),
						commonFunc.getDataFromTestDataSheet("Emp_City"), true);
				addNewQualificationDetailsInQualificationTab(commonFunc.getDataFromTestDataSheet("Emp_Qualification"),
						commonFunc.getDataFromTestDataSheet("Emp_BranchofStudy"),
						commonFunc.getDataFromTestDataSheet("EmpInstitute"), String.valueOf(random.nextInt(4)),
						commonFunc.getDataFromTestDataSheet("Emp_YearOfPassing"), RandomStringUtils.randomAlphabetic(1),
						RandomStringUtils.randomNumeric(2), true);
				addNewSkillDetailsInQualificationTab(commonFunc.getDataFromTestDataSheet("Emp_Skill"),
						commonFunc.getDataFromTestDataSheet("Emp_Level"), true);
				clickDependentDetailsSideNavBar();
				clickAddIconInDependentDetails();
				fillRequiredDetailsInDependentDetails(commonFunc.getDataFromTestDataSheet("EmpDependentName"),
						commonFunc.getDataFromTestDataSheet("EmpDependentRelation"),
						commonFunc.getDataFromTestDataSheet("EmpDependentRelationDOB"),
						RandomStringUtils.randomNumeric(12), commonFunc.randomEmailIdGenerator(),
						commonFunc.randomMobileNumberGenerator(), RandomStringUtils.randomNumeric(5));
				clickSaveIconInDependentDetails();
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
		String aadhar = commonFunc.getDataFromTestDataSheet("EmpAadharNum");
		try {
			if (!VariableHandler.getAddEmployeDefaultExecution()) {
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
						commonFunc.getDataFromTestDataSheet("Emp_FName"),
						(commonFunc.getDataFromTestDataSheet("Emp_LName") + RandomStringUtils.randomAlphanumeric(5)));
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
			fillRequiredDetailsInDependentDetails(commonFunc.getDataFromTestDataSheet("EmpDependentName"),
					commonFunc.getDataFromTestDataSheet("EmpDependentRelation"),
					commonFunc.getDataFromTestDataSheet("EmpDependentRelationDOB"), aadhar,
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
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
						commonFunc.getDataFromTestDataSheet("Emp_FName"),
						(commonFunc.getDataFromTestDataSheet("Emp_LName") + RandomStringUtils.randomAlphanumeric(5)));
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
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
					"HrRqtUsername");
			commonFunc.navigateEmployeeMasterScreenInPayroll();
			searchAndClickEmpIdInFindEmployeePage(VariableHandler.getEmpID());
			clickUpdateButtonInAddEmployee();
			clickEmployeeParticularTabInUpdateEmployee();
			updateEmployeeGenderFirstAndLastNameInParticularTab(commonFunc.getDataFromTestDataSheet("Emp_MaleGender"),
					commonFunc.getDataFromTestDataSheet("Emp_FName"),
					(commonFunc.getDataFromTestDataSheet("Emp_LName") + RandomStringUtils.randomAlphanumeric(5)));
			updateEmployeeDOBAndCalenderInParticularTab(commonFunc.getDataFromTestDataSheet("Emp_DOB"),
					commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"));
			updateEmployeeShiftMaritalStatusAndZone(commonFunc.getDataFromTestDataSheet("Emp_GeneralShift"),
					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"),
					commonFunc.getDataFromTestDataSheet("Emp_GeneralZone"));
			updateEmployeeCategoryAndAccountCategory(commonFunc.getDataFromTestDataSheet("Emp_SalesCompanyCategory"),
					commonFunc.getDataFromTestDataSheet("Emp_SalaryAcc-AccountCategory"));
			updateEmployeePayrollGrpAndNoticePeriod(commonFunc.getDataFromTestDataSheet("Emp_CleanerPayrollGroup"),
					commonFunc.getDataFromTestDataSheet("Emp_NoticePeriod-30"));
			clickSaveIconAndPostInEmployeeParticular();
			commonFunc.hardWait(2000);
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getEmpUpdateDocNo(),
						webDB.getDataFromProperties("ROAUAdminEmpId"), null, System.getProperty("HariUsername"));
			}
			return true;
		} catch (

		Exception e) {
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
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
						commonFunc.getDataFromTestDataSheet("Emp_FName"),
						(commonFunc.getDataFromTestDataSheet("Emp_LName") + RandomStringUtils.randomAlphanumeric(5)));
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
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
					"HrRqtUsername");
			commonFunc.navigateEmployeeMasterScreenInPayroll();
			searchAndClickEmpIdInFindEmployeePage(VariableHandler.getEmpID());
			clickUpdateButtonInAddEmployee();
			clickAddressTabInUpdateEmployee();
			updateEmployeeAllAddressInAddressTab(commonFunc.getDataFromTestDataSheet("EmpAddress"),
					commonFunc.getDataFromTestDataSheet("EmpAddress"),
					commonFunc.getDataFromTestDataSheet("EmpAddress"));
			updateEmpCityZipCodeAndTelephoneInAddressTab(commonFunc.getDataFromTestDataSheet("Emp_City"),
					RandomStringUtils.randomNumeric(6), commonFunc.randomMobileNumberGenerator());
			clickSaveIconAndPostInAddress();
			commonFunc.hardWait(2000);
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getEmpUpdateDocNo(),
						webDB.getDataFromProperties("ROAUAdminEmpId"), null, System.getProperty("HariUsername"));
			}
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyEmployeeCreationUpdateAddressDetails': " + e);
			return false;
		}
	}

	/**
	 * This method used To check Employee Creation With Update Dependent Details
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean verifyEmployeeCreationUpdateDependentDetails() {
		try {
			if (!VariableHandler.getAddEmployeDefaultExecution()) {
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
						commonFunc.getDataFromTestDataSheet("Emp_FName"),
						(commonFunc.getDataFromTestDataSheet("Emp_LName") + RandomStringUtils.randomAlphanumeric(5)));
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
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
					"HrRqtUsername");
			commonFunc.navigateEmployeeMasterScreenInPayroll();
			searchAndClickEmpIdInFindEmployeePage(VariableHandler.getEmpID());
			clickUpdateButtonInAddEmployee();
			clickDependentTabInUpdateEmployee();
			selectDependentTypeToUpdate("Add");
			clickAddIconInUpdateDependentDetails();
			fillDetailsInUpdateDependentDetails(commonFunc.getDataFromTestDataSheet("EmpDependentName"),
					commonFunc.getDataFromTestDataSheet("EmpDependentRelation"),
					commonFunc.getDataFromTestDataSheet("EmpDependentRelationDOB"), RandomStringUtils.randomNumeric(12),
					commonFunc.randomEmailIdGenerator(), commonFunc.randomMobileNumberGenerator(),
					RandomStringUtils.randomNumeric(5));
			clickSaveAndPostInUpdateDependentDetails();
			commonFunc.hardWait(2000);
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getEmpUpdateDocNo(),
						webDB.getDataFromProperties("ROAUAdminEmpId"), null, System.getProperty("HariUsername"));
			}
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyEmployeeCreationUpdateDependentDetails': " + e);
			return false;
		}
	}

	/**
	 * This method used To check Employee Creation With Update Contact Details
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean verifyEmployeeCreationUpdateContactDetails() {
		try {
			if (!VariableHandler.getAddEmployeDefaultExecution()) {
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
						commonFunc.getDataFromTestDataSheet("Emp_FName"),
						(commonFunc.getDataFromTestDataSheet("Emp_LName") + RandomStringUtils.randomAlphanumeric(5)));
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
				commonFunc.logoutUser();
				VariableHandler.setAddEmployeDefaultExecution(true);
			}
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
					"HrRqtUsername");
			commonFunc.navigateEmployeeMasterScreenInPayroll();
			searchAndClickEmpIdInFindEmployeePage(VariableHandler.getEmpID());
			clickUpdateButtonInAddEmployee();
			clickContactTabInUpdateEmployee();
			selectContactTypeToUpdate("Add");
			clickAddIconInUpdateContactDetails();
			fillDetailsInUpdateContactDetails(
					commonFunc.getDataFromTestDataSheet("EmpDependentName")
							.concat(" " + commonFunc.getDataFromTestDataSheet("EmpDependentName")),
					commonFunc.getDataFromTestDataSheet("EmpDependentRelation"),
					commonFunc.randomMobileNumberGenerator(), commonFunc.getDataFromTestDataSheet("EmpAddress"),
					commonFunc.getDataFromTestDataSheet("Emp_City"));
			clickSaveAndPostInUpdateContactDetails();
			commonFunc.hardWait(2000);
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getEmpUpdateDocNo(),
						webDB.getDataFromProperties("ROAUAdminEmpId"), null, System.getProperty("HariUsername"));
			}
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyEmployeeCreationUpdateContactDetails': " + e);
			return false;
		}
	}

	/**
	 * This method used To check Employee Creation With Update Qualification Details
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean verifyEmployeeCreationUpdateQualificationDetails() {
		try {
			if (!VariableHandler.getAddEmployeDefaultExecution()) {
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
						commonFunc.getDataFromTestDataSheet("Emp_FName"),
						(commonFunc.getDataFromTestDataSheet("Emp_LName") + RandomStringUtils.randomAlphanumeric(5)));
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
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
					"HrRqtUsername");
			commonFunc.navigateEmployeeMasterScreenInPayroll();
			searchAndClickEmpIdInFindEmployeePage(VariableHandler.getEmpID());
			clickUpdateButtonInAddEmployee();
			clickQualificationTabInUpdateEmployee();
			selectQualificationTypeToUpdate("Add");
			clickAddIconInUpdateQualificationDetails();
			fillDetailsInUpdateQualificationDetails(commonFunc.getDataFromTestDataSheet("Emp_QualificationBCOM"),
					commonFunc.getDataFromTestDataSheet("Emp_BranchofStudy"),
					commonFunc.getDataFromTestDataSheet("EmpInstitute"), String.valueOf(random.nextInt(4)),
					commonFunc.getDataFromTestDataSheet("Emp_YearOfPassing"), RandomStringUtils.randomAlphabetic(1),
					RandomStringUtils.randomNumeric(2));
			clickSaveAndPostInUpdateQualificationDetails();
			commonFunc.hardWait(2000);
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getEmpUpdateDocNo(),
						webDB.getDataFromProperties("ROAUAdminEmpId"), null, System.getProperty("HariUsername"));
			}
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyEmployeeCreationUpdateQualificationDetails': " + e);
			return false;
		}
	}

	/**
	 * This method used To check Employee Creation With Update Skill Details
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean verifyEmployeeCreationUpdateSkillsDetails() {
		try {
			if (!VariableHandler.getAddEmployeDefaultExecution()) {
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
						commonFunc.getDataFromTestDataSheet("Emp_FName"),
						(commonFunc.getDataFromTestDataSheet("Emp_LName") + RandomStringUtils.randomAlphanumeric(5)));
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
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
					"HrRqtUsername");
			commonFunc.navigateEmployeeMasterScreenInPayroll();
			searchAndClickEmpIdInFindEmployeePage(VariableHandler.getEmpID());
			clickUpdateButtonInAddEmployee();
			clickSkillTabInUpdateEmployee();
			selectSkillTypeToUpdate("Add");
			clickAddIconInUpdateSkillDetails();
			fillDetailsInUpdateSkillDetails(commonFunc.getDataFromTestDataSheet("Emp_Skill"),
					commonFunc.getDataFromTestDataSheet("Emp_Level"));
			clickSaveAndPostInUpdateSkillDetails();
			commonFunc.hardWait(2000);
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getEmpUpdateDocNo(),
						webDB.getDataFromProperties("ROAUAdminEmpId"), null, System.getProperty("HariUsername"));
			}
			commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyEmployeeCreationUpdateSkillsDetails': " + e);
			return false;
		}
	}

	/**
	 * This method used To check the Employee Creation with Employee History data
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean verifyEmployeeCreationEmployeeHistory() {
		try {
			commonFunc.navigateEmployeeMasterScreenInPayroll();
			clickAddIconAddEmployee();
			clickEmployeeMasterSideNavBar();
			selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
					commonFunc.getDataFromTestDataSheet("Emp_FName"),
					(commonFunc.getDataFromTestDataSheet("Emp_LName") + RandomStringUtils.randomAlphanumeric(5)));
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
			clickEmployeeHistoryAndCheckNotNull();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyEmployeeCreationEmployeeHistory': " + e);
			return false;
		}
	}

	/**
	 * This method used To Check Employee profile Reverse before the Payroll Process
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean verifyEmployeeCreationEmployeeProfileReverse() {
		try {
			commonFunc.navigateEmployeeMasterScreenInPayroll();
			clickAddIconAddEmployee();
			clickEmployeeMasterSideNavBar();
			selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
					commonFunc.getDataFromTestDataSheet("Emp_FName"),
					(commonFunc.getDataFromTestDataSheet("Emp_LName") + RandomStringUtils.randomAlphanumeric(5)));
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
			clickViewProfileAndStoreProfileAndBatchNo();
			clickEmployeeProfileNoAndReverse();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyEmployeeCreationEmployeeProfileReverse': " + e);
			return false;
		}
	}

	/**
	 * This method used To Check Employee Migration With Templates
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean verifyEmployeeMigrationWithTemplates() {
		try {
			commonFunc.navigateEmployeeMasterScreenInPayroll();
			clickAndNavigateEmployeeMigration();
			clickAddAndFillDetailsInEmployeeMigration(commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"),
					commonFunc.getDataFromTestDataSheet("SalaryOption"),
					commonFunc.getDataFromTestDataSheet("SalaryOption"),
					commonFunc.getDataFromTestDataSheet("RefText_Test"));
			uploadEmployeeMigrationTemplate();
			checkExceptionAndSaveHeaderInEmpMigration();
			postUploadedTemplateInEmployeeMigration();
			return true;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyEmployeeMigrationWithTemplates': " + e);
			return false;
		}
	}

	/**
	 * This method used To Check Employee Details Migration With Templates
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean verifyEmployeeDetailsMigrationWithTemplates() {
		try {
			commonFunc.navigateEmployeeMasterScreenInPayroll();
			clickAndNavigateEmployeeDetailsMigration();
			createAndFillDetailsInEmpDetailsMigration(commonFunc.getDataFromTestDataSheet("RefText_Test"));
			clickSelectFieldOptionsInEmpDetailsMigration();
			clickAddSelectFieldAndSelectSequence(commonFunc.getDataFromTestDataSheet("FirstNameOptionText"), "1");
			clickAddSelectFieldAndSelectSequence(commonFunc.getDataFromTestDataSheet("LastNameOptionText"), "2");
			clickAddSelectFieldAndSelectSequence(commonFunc.getDataFromTestDataSheet("NationalityOptionText"), "3");
			uploadEmployeeDetailsMigrationTemplate();
			checkExceptionAndSaveHeaderInEmpDetailsMigration();
			commonFunc.hardWait(3000);
			postUploadedTemplateInEmployeeDetailsMigration();
			return true;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyEmployeeDetailsMigrationWithTemplates': " + e);
			return false;
		}
	}

	/**
	 * This method used To Check Employee Creation based on forward to approval
	 * record
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean verifyEmployeeCreationForwardApproval() {
		try {
			commonFunc.navigateEmployeeMasterScreenInPayroll();
			clickAddIconAddEmployee();
			clickEmployeeMasterSideNavBar();
			selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
					commonFunc.getDataFromTestDataSheet("Emp_FName"),
					(commonFunc.getDataFromTestDataSheet("Emp_LName") + RandomStringUtils.randomAlphanumeric(5)));
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
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
						"HariUsername");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getEmpID());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getEmpID());
			}
			return true;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyEmployeeCreationForwardApproval': " + e);
			return false;
		}
	}

	/**
	 * This method used To Check Employee Creation based on Return to approval
	 * record
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean verifyEmployeeCreationReturnApproval() {
		try {
			commonFunc.navigateEmployeeMasterScreenInPayroll();
			clickAddIconAddEmployee();
			clickEmployeeMasterSideNavBar();
			selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
					commonFunc.getDataFromTestDataSheet("Emp_FName"),
					(commonFunc.getDataFromTestDataSheet("Emp_LName") + RandomStringUtils.randomAlphanumeric(5)));
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
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
						"HariUsername");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getEmpID());
				approvalFunc.verifyReturnWithDocNumber(VariableHandler.getEmpID(),
						webDB.getDataFromProperties("HRuserEmpId"));
			}
			commonFunc.logoutUser();
			return true;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyEmployeeCreationReturnApproval': " + e);
			return false;
		}
	}

	/**
	 * This method used To Check Employee Creation With Probation Complete
	 * 
	 * @author Sakthi-GWL
	 * @return boolean
	 */
	public boolean verifyEmployeeCreationProbationComplete() {
		try {
			commonFunc.navigateEmployeeMasterScreenInPayroll();
			clickAddIconAddEmployee();
			clickEmployeeMasterSideNavBar();
			selectSalutionEnterFirstAndLastName(commonFunc.getDataFromTestDataSheet("Emp_Salution"),
					commonFunc.getDataFromTestDataSheet("Emp_FName"),
					(commonFunc.getDataFromTestDataSheet("Emp_LName") + RandomStringUtils.randomAlphanumeric(5)));
			selectGenderEnterDOBAndDOJ(commonFunc.getDataFromTestDataSheet("Emp_MaleGender"),
					commonFunc.getDataFromTestDataSheet("Emp_DOB"), commonFunc.getDataFromTestDataSheet("Emp_DOJ"));
			selectShiftEnterCalerdarAndZone(commonFunc.getDataFromTestDataSheet("Emp_GeneralShift"),
					commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"),
					commonFunc.getDataFromTestDataSheet("Emp_GeneralZone"));
			selectCompanyAndAccountCatecory(commonFunc.getDataFromTestDataSheet("Emp_SalesCompanyCategory"),
					commonFunc.getDataFromTestDataSheet("Emp_SalaryAcc-AccountCategory"));
			selectPayrollGroupAndEnterNoticePeriod(commonFunc.getDataFromTestDataSheet("Emp_CleanerPayrollGroup"),
					commonFunc.getDataFromTestDataSheet("Emp_NoticePeriod-30"));
			enterProbationDetailsAndSaveHeader(commonFunc.getDataFromTestDataSheet("Emp_ProbationDuration"),
					commonFunc.getDataFromTestDataSheet("Emp_ProbationType"));
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
			enablePTAdjustmentFlagsInSalaryDetails();
			clickProbationCompleteAndCheckProbationStatus();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyEmployeeCreationProbationComplete': " + e);
			return false;
		}
	}

	/**
	 * This method used To Check the Employee Creation Find page with all line wise
	 * record
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyEmployeeCreationFindWithLineWiseRecord() {
		try {
			commonFunc.navigateEmployeeMasterScreenInPayroll();
			commonFunc.clickShowMoreFindTable();
			findWithZone(commonFunc.getDataFromTestDataSheet("Emp_GeneralZone"));
			webDB.navigateToRefresh();
			commonFunc.clickShowMoreFindTable();
			findWithCategory(commonFunc.getDataFromTestDataSheet("Emp_SalesCompanyCategory"));
			webDB.navigateToRefresh();
			commonFunc.clickShowMoreFindTable();
			findWithDesignation(commonFunc.getDataFromTestDataSheet("Emp_SalaryDesignation"));
			webDB.navigateToRefresh();
			commonFunc.clickShowMoreFindTable();
			findWithDepartment(commonFunc.getDataFromTestDataSheet("Emp_SalaryDepartment"));
			webDB.navigateToRefresh();
			commonFunc.clickShowMoreFindTable();
			findWithUnit(commonFunc.getDataFromTestDataSheet("Unit1"));
			webDB.navigateToRefresh();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyEmployeeCreationFindWithLineWiseRecord': " + e);
			return false;
		}
	}

	/**
	 * This Method used To click Add Icon in Employee Master
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAddIconAddEmployee() {
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
	public void selectSalutionEnterFirstAndLastName(String SalutionValue, String FName, String LName) {
		VariableHandler.setEmployeeFullName(FName + LName);
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
	public void selectGenderEnterDOBAndDOJ(String Gender, String DOB, String DOJ) {
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
	public void selectShiftEnterCalerdarAndZone(String Shift, String CalenderValue, String Zone) {
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
	public void selectCompanyAndAccountCatecory(String CompanyCategory, String AccountCategory) {
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
	public void selectPayrollGroupAndEnterNoticePeriod(String PayrollGrp, String NoticePeriod) {
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
	public void clickSaveIconAndStoreEmpID() {
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
				text = text.replace("Employee : ", " ").trim();
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
	public void clickAfterEmpCreationSaveIcon() {
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
	public void clickEmployeeMasterSideNavBar() {
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
	public void clickQualificationSkillSideNavBar() {
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
	public void clickSalarySideNavBar() {
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
	public void clickLeaveDetailsSideNavBar() {
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
	public void addNewContactDetailsInQualificationTab(String ContactName, String Relationship, String MobileNo,
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
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.AddEmp_ContactNameData(),
				EmployeeMasterLocators.AddEmp_ContactNameDataInput(), ContactName);
		VariableHandler.setEmpContactName(ContactName);
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.AddEmp_RelationshipData(),
				EmployeeMasterLocators.AddEmp_RelationshipDataInput(), Relationship);
		VariableHandler.setEmpContactRelationship(Relationship);
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.AddEmp_MobileNoData(),
				EmployeeMasterLocators.AddEmp_MobileNoDataInput(), MobileNo);
		VariableHandler.setEmpContactMobileNo(MobileNo);
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.AddEmp_AddressData(),
				EmployeeMasterLocators.AddEmp_AddressDataInput(), Address);
		VariableHandler.setEmpContactAddress(Address);
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_City(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_City(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_CityLovBtn(), City);
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
	public void addNewQualificationDetailsInQualificationTab(String Qualification, String branchOfStudy,
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
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_Qualification(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_Qualification(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_QualificationLovBtn(),
					Qualification);
			VariableHandler.setEmpQualification(Qualification);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_BranchOfStudy(), ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(EmployeeMasterLocators.AddEmp_BranchOfStudy(), ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_BranchOfStudyLovBtn(),
						branchOfStudy);
				VariableHandler.setEmpQualBranchOfStudy(branchOfStudy);
				commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.AddEmp_InstituteData(),
						EmployeeMasterLocators.AddEmp_InstituteDataInput(), Institute);
				VariableHandler.setEmpQualInstitute(Institute);
				commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.AddEmp_TotalYearsData(),
						EmployeeMasterLocators.AddEmp_TotalYearsDataInput(), Totalyears);
				VariableHandler.setEmpQualTotalYears(Totalyears);
				commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.AddEmp_YearOfPassingData(),
						EmployeeMasterLocators.AddEmp_YearOfPassingDataInput(), YearofPassing);
				VariableHandler.setEmpQualYearOfPassing(YearofPassing);
				commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.AddEmp_GradeClassData(),
						EmployeeMasterLocators.AddEmp_GradeClassDataInput(), GradeClass);
				VariableHandler.setEmpQualGradeClass(GradeClass);
				commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.AddEmp_AggData(),
						EmployeeMasterLocators.AddEmp_AggDataInput(), Agg);
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
	public void addNewSkillDetailsInQualificationTab(String Skill, String Level, boolean... addlineflag) {
		if (addlineflag != null && addlineflag[0] != false) {
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_SkillAddLinesIcon, ElementType.Xpath);
			if (flag) {
				flag = webDB.clickAnElement(EmployeeMasterLocators.AddEmp_SkillAddLinesIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Skill Add Icon Clicked");
				}
			}
		}
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_SkillData(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_SkillData(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_SkillDataLov(), Skill);
			VariableHandler.setEmpSkill(Skill);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LevelData(), ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LevelData(), ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_LevelDataLov(), Level);
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
	public void addDesignationDepartmentAndGradeInSalaryDetails(String designation, String department, String grade) {
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
	public void clickLoadAllowanceButtonInSalaryDetails() {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LoadAllowanceBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LoadAllowanceBtn, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AllowanceTableTotalAmountData(),
					ElementType.Xpath);
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
	public void fillAllowanceAmountsAndVerifyTotalInSalaryDetails(String[] amounts) {
		int totalAllowanceAmounts = 0;
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AllowanceTableAmountData(), ElementType.Xpath);
		if (flag) {
			List<WebElement> elements = WebDriverBase.getDriver()
					.findElements(By.xpath(EmployeeMasterLocators.AddEmp_AllowanceTableAmountData()));
			for (int i = 0; i < elements.size() - 1; i++) {
				WebElement element = elements.get(i);
				commonFunc.checkRefreshCellsDisappear();
				Actions action = new Actions(WebDriverBase.getDriver());
				action.doubleClick(element).build().perform();
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AllowanceTableAmountDataInput(),
						ElementType.Xpath);
				if (flag) {
					try {
						JavascriptExecutor js = (JavascriptExecutor) WebDriverBase.getDriver();
						WebElement InputElement = WebDriverBase.getDriver()
								.findElement(By.xpath(EmployeeMasterLocators.AddEmp_AllowanceTableAmountDataInput()));
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
			webDB.waitForElement(EmployeeMasterLocators.AddEmp_AllowanceTableTotalAmountData(), ElementType.Xpath);
			String displayingTotalData = webDB.getTextFromElement(
					EmployeeMasterLocators.AddEmp_AllowanceTableTotalAmountData(), ElementType.Xpath);
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
	public void saveAllowanceTableInSalary() {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_SaveAllowanceTableBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_SaveAllowanceTableBtn, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_SaveAllowanceTableBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
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
	public void clickAddButtonInAdjustmentSalaryDetails() {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AdjustmentAddIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_AdjustmentAddIcon, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AdjustmentPeriodAmountData(), ElementType.Xpath);
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
	public void enableESIAdjustmentFlagsInSalaryDetails() {
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
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AdjustmentTable, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "ESI flag Enabled Successfully");
				List<WebElement> elements = webDB.getListOfElements(EmployeeMasterLocators.AddEmp_AdjustmentTable,
						ElementType.Xpath);
				flag = elements.size() >= 2;
				for (WebElement element : elements) {
					if (element.getText().contains("PF") || element.getText().contains("ESI")) {
						flag = true;
					} else {
						flag = false;
						break;
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used To Enable PT Adjustment Flag in Salary Details in Add
	 * Employee
	 * 
	 * @author Sakthi-GWL
	 */
	public void enablePTAdjustmentFlagsInSalaryDetails() {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_PT_ElegibleLabel, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_PT_ElegibleLabel, ElementType.Xpath);
			flag = webDB.clickAnElement(EmployeeMasterLocators.AddEmp_PT_ElegibleLabel, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (flag) {
				logs.logging("INFO", "PT flag Enabled Successfully");
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
	public void enablePFAdjustmentFlagsInSalaryDetails() {
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
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AdjustmentTable, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "PF flag Enabled Successfully");
				List<WebElement> elements = webDB.getListOfElements(EmployeeMasterLocators.AddEmp_AdjustmentTable,
						ElementType.Xpath);
				flag = elements.size() >= 2;
				for (WebElement element : elements) {
					if (element.getText().contains("PF") || element.getText().contains("ESI")) {
						flag = true;
					} else {
						flag = false;
						break;
					}
				}
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
	public void selectAdjustmentSalaryElementStartYearAndPeriodDetails(String Element, String StartYear,
			String Period) {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AdjustmentElementLovBtn, ElementType.Xpath);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_AdjustmentElementLovBtn, Element);
			VariableHandler.setEmpSalaryAdjmntElement(Element);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AdjustmentStartYearData(), ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.clickAnElement(EmployeeMasterLocators.AddEmp_AdjustmentStartYearData(), ElementType.Xpath);
				webDB.waitForElement(EmployeeMasterLocators.AddEmp_AdjustmentStartYearSelect, ElementType.Xpath);
				flag = webDB.selectDropDownByText(EmployeeMasterLocators.AddEmp_AdjustmentStartYearSelect, StartYear,
						ElementType.Xpath);
				if (flag) {
					VariableHandler.setEmpSalaryAdjmntStartYear(StartYear);
					logs.logging("INFO",
							"Adjustment Start Year Selected Text: " + VariableHandler.getEmpSalaryAdjmntStartYear());
					flag = webDB.waitForClickElement(EmployeeMasterLocators.AddEmp_AdjustmentPeriodData(),
							ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.moveToElement(EmployeeMasterLocators.AddEmp_AdjustmentPeriodData(), ElementType.Xpath);
						webDB.clickAnElement(EmployeeMasterLocators.AddEmp_AdjustmentPeriodData(), ElementType.Xpath);
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
	public void fillAdjustmentSalaryTotalPeriodAndPeriodAmount(String TotalPeriod, String PeriodAmount) {
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.AddEmp_AdjustmentTotalPeriodData(),
				EmployeeMasterLocators.AddEmp_AdjustmentTotalPeriodDataInput(), TotalPeriod);
		VariableHandler.setEmpSalaryAdjmntTotalPeriod(TotalPeriod);
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.AddEmp_AdjustmentPeriodAmountData(),
				EmployeeMasterLocators.AddEmp_AdjustmentPeriodAmountDataInput(), PeriodAmount);
		VariableHandler.setEmpSalaryAdjmntPeriodAmnt(PeriodAmount);
	}

	/**
	 * This Method used To Click Activate Icon in Last Added Adjustment in Salary
	 * Details in Add Employee
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickActivateIconInLastAddedAdjustment() {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_SalaryAdjustmentsMaximizeIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_SalaryAdjustmentsMaximizeIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_SalaryAdjustmentsMaximizeIcon, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_SalaryAdjustmentsRestoreIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Salary Adjustment Maximize Icon Clicked Successfully");
				webDB.moveToElement(EmployeeMasterLocators.AddEmp_AdjustmentActivateIcon(), ElementType.Xpath);
				webDB.clickAnElement(EmployeeMasterLocators.AddEmp_AdjustmentActivateIcon(), ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AdjustmentOkBtn, ElementType.Xpath);
				if (flag) {
					flag = webDB.clickAnElement(EmployeeMasterLocators.AddEmp_AdjustmentOkBtn, ElementType.Xpath);
//					flag = webDB.waitForElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
					if (flag) {
//						logs.logging("INFO", "Salary Tab Adjustment Activate Icon Clicked and Toast Message Displayed");
						logs.logging("INFO", "Salary Tab Adjustment Activate Icon Clicked");
						if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
							webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
						}
					}
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
	public void clickDeleteIconInLastAddedAdjustment() {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AdjustmentTableFirstTrashIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_AdjustmentTotalPeriodData(), ElementType.Xpath);
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_AdjustmentActivateIcon(), ElementType.Xpath);
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
				commonFunc.hardWait(3000);
				commonFunc.checkSuccessToastMessageDisappear();
				if (webDB.verifyIsElementDisplayed(EmployeeMasterLocators.AddEmp_SalaryAdjustmentsRestoreIcon,
						ElementType.Xpath)) {
					webDB.waitForClickElement(EmployeeMasterLocators.AddEmp_SalaryAdjustmentsRestoreIcon,
							ElementType.Xpath);
					webDB.clickAnElement(EmployeeMasterLocators.AddEmp_SalaryAdjustmentsRestoreIcon, ElementType.Xpath);
					flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_SalaryAdjustmentsMaximizeIcon,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Restore Button Clicked and Minimized Successfully");
					}
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
	public void clickSaveIconInSalaryAdjustment() {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_SalaryAdjustmentSaveBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_SalaryAdjustmentSaveBtn, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AdjustmentActivateIcon(), ElementType.Xpath);
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
	public void clickAddIconLeaveEligibilityInLeaveDetails() {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMaximizeIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMaximizeIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMaximizeIcon, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityRestoreIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Maximize Button Clicked and Maximized Successfully");
				webDB.moveToElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityAddBtn, ElementType.Xpath);
				webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityAddBtn, ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityLeaveLovBtn(),
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Leave Eligibility Add Icon Clicked");
				}
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
	public void fillLeaveAndDaysInLeaveEligibility(String Leave, String Days) {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityLeaveLovBtn(), ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_LeaveEligibilityLeaveLovBtn(),
					Leave);
			VariableHandler.setEmpLeaveElgblLeave(Leave);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityDescription(), ElementType.Xpath);
			commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.AddEmp_LeaveEligibilityDaysData(),
					EmployeeMasterLocators.AddEmp_LeaveEligibilityDaysInput(), Days);
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
	public void fillEveryAndEligibleDaysInLeaveEligibility(String Every, String EligibleDays) {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityEveryData(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityEveryData(), ElementType.Xpath);
			webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityEverySelect(), ElementType.Xpath);
			flag = webDB.selectDropDownByText(EmployeeMasterLocators.AddEmp_LeaveEligibilityEverySelect(), Every,
					ElementType.Xpath);
			if (flag) {
				VariableHandler.setEmpLeaveElgblEvery(Every);
				logs.logging("INFO",
						"Leave Eligibility Every Data Selected Text: " + VariableHandler.getEmpLeaveElgblEvery());
				commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.AddEmp_LeaveEligibilityEligibleDaysData(),
						EmployeeMasterLocators.AddEmp_LeaveEligibilityEligibleDaysInput(), EligibleDays);
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
	public void fillRoundOffDaysAndMaxCarryFrequencyInLeaveEligibility(String RoundOff, String MaxCarryFreq) {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityRoundOffData(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityRoundOffData(), ElementType.Xpath);
			webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityRoundOffSelect(), ElementType.Xpath);
			flag = webDB.selectDropDownByText(EmployeeMasterLocators.AddEmp_LeaveEligibilityRoundOffSelect(), RoundOff,
					ElementType.Xpath);
			if (flag) {
				VariableHandler.setEmpLeaveElgblRoundOff(RoundOff);
				logs.logging("INFO", "Leave Eligibility RoundOff Selected Text: " + RoundOff);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMaxCarryFreqData(),
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMaxCarryFreqData(),
							ElementType.Xpath);
					webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMaxCarryFreqSelect(),
							ElementType.Xpath);
					flag = webDB.selectDropDownByText(
							EmployeeMasterLocators.AddEmp_LeaveEligibilityMaxCarryFreqSelect(), MaxCarryFreq,
							ElementType.Xpath);
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
	public void fillMaxCarryDayAndEffectiveYearInLeaveEligibility(String MaxCarryDay, String EffYear) {
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.AddEmp_LeaveEligibilityMaxCarryDayData(),
				EmployeeMasterLocators.AddEmp_LeaveEligibilityMaxCarryDayInput(), MaxCarryDay);
		VariableHandler.setEmpLeaveElgblMaxCarryDay(MaxCarryDay);
		logs.logging("INFO", "" + "Max Carry Days Entered in Leave Eligibility: " + MaxCarryDay);
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityEffYearData(), ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityEffYearData(), ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityEffYearData(), ElementType.Xpath);
			webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityEffYearSelect(), ElementType.Xpath);
			flag = webDB.selectDropDownByText(EmployeeMasterLocators.AddEmp_LeaveEligibilityEffYearSelect(), EffYear,
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
	public void fillMaxDaysAndMinDaysInLeaveEligibility(String MaxDay, String MinDay, String MinEncashDay) {
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.AddEmp_LeaveEligibilityMaxDaysData(),
				EmployeeMasterLocators.AddEmp_LeaveEligibilityMaxDaysInput(), MaxDay);
		VariableHandler.setEmpLeaveElgblMaxDay(MaxDay);
		logs.logging("INFO", "" + "Max Days Entered in Leave Eligibility: " + MaxDay);
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.AddEmp_LeaveEligibilityMinDaysData(),
				EmployeeMasterLocators.AddEmp_LeaveEligibilityMinDaysInput(), MinDay);
		VariableHandler.setEmpLeaveElgblMinDay(MinDay);
		logs.logging("INFO", "" + "Min Days Entered in Leave Eligibility: " + MinDay);
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.AddEmp_LeaveEligibilityMinEncashData(),
				EmployeeMasterLocators.AddEmp_LeaveEligibilityMinEncashInput(), MinEncashDay);
		logs.logging("INFO", "" + "Min Encash Days Entered in Leave Eligibility: " + MinEncashDay);
	}

	/**
	 * This Method used To click Save icon in Leave Eligibility in Leave Details in
	 * Add Employee
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickSaveIconInLeaveEligibility() {
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
	public void clickPostButtonAndVerifyEmployeePosted() {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmployeePostButton, ElementType.Xpath);
		if (flag) {
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			webDB.moveToElement(EmployeeMasterLocators.AddEmployeePostButton, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmployeePostButton, ElementType.Xpath);
			flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
			if (flag) {
				logs.logging("INFO", "Employee Post Icon Clicked");
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
	public void validateUpdatedLeaveDetailsWithSetupScreenEntitlement() {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityLeaveData(), ElementType.Xpath);
		if (flag) {
			int a = 0;
			List<WebElement> LeaveIDs = WebDriverBase.getDriver()
					.findElements(By.xpath(EmployeeMasterLocators.AddEmp_LeaveEligibilityLeaveData()));
			flag = LeaveIDs.size() == VariableHandler.getEmpSetupScreenGreenFlagLeaveIDs().size();
			if (flag) {
				aa: for (int i = 0; i < LeaveIDs.size(); i++) {
					if (flag) {
						bb: for (int j = 0; j < VariableHandler.getEmpSetupScreenGreenFlagLeaveIDs().size(); j++) {
							if (VariableHandler.getEmpSetupScreenGreenFlagLeaveIDs().get(j)
									.equalsIgnoreCase(LeaveIDs.get(i).getText())) {
								logs.logging("INFO",
										"Verified With Entitled Leave and Displayed Leave ID in Leave Details: "
												+ LeaveIDs.get(i).getText());
								flag = true;
								a++;
								break bb;
							} else {
								flag = false;
							}
						}
					} else {
						flag = false;
						break aa;
					}
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
	public void uploadSignImageInAddEmployeeDetails(String ImageName) {
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
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_SignUploadFileNameText,
							ElementType.Xpath);
					if (flag) {
						flag = webDB.getTextFromElement(EmployeeMasterLocators.AddEmp_SignUploadFileNameText,
								ElementType.Xpath).equalsIgnoreCase(ImageName);
						if (flag) {
							logs.logging("INFO", ImageName + " - was uploaded and FileName Displayed");
							webDB.waitForElement(EmployeeMasterLocators.AddEmp_UploadBtn, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
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
	public boolean checkSignImageUploadedWithClearButton() {
		webDB.switchToDefaultContent();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.checkSuccessToastMessageDisappear();
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
	public void uploadProfileImageInAddEmployeeDetails(String ImageName) {
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
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_ProfileUploadFileNameText,
							ElementType.Xpath);
					if (flag) {
						flag = webDB.getTextFromElement(EmployeeMasterLocators.AddEmp_ProfileUploadFileNameText,
								ElementType.Xpath).equalsIgnoreCase(ImageName);
						if (flag) {
							logs.logging("INFO", ImageName + " - was uploaded and File Name Displayed");
							webDB.waitForElement(EmployeeMasterLocators.AddEmp_ProfileUploadIcon, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							webDB.moveToElement(EmployeeMasterLocators.AddEmp_ProfileUploadIcon, ElementType.Xpath);
							webDB.clickAnElement(EmployeeMasterLocators.AddEmp_ProfileUploadIcon, ElementType.Xpath);
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
	public boolean checkProfileImageUploadedWithClearButton() {
		webDB.switchToDefaultContent();
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForClickElement(EmployeeMasterLocators.AddEmp_ProfileImg, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_ProfileImg, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_ProfileImg, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LoadIframe, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(EmployeeMasterLocators.AddEmp_LoadIframe, ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_ProfileClearIcon, ElementType.Xpath);
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
	public void clickAdditionalInfoButton() {
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
	public void addInfoIframeSwitch() {
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
	public void submitHomeTypeAndPreviousExperienceInAdditionalInfo(String HouseType, String Year, String Month) {
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
	public void submitStayTypeAndVehicleDetailsInAdditionalInfo(String StayType, String VehicleFlag,
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
	public void submitOfficeDetailsInAdditionalInfo(String ApplNo, String IDCardNo, String PrevEmpID) {
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
	public void submitMobileEmailOfcAndPersonalInAdditionalInfo(String MobileOfc, String EmailOfc, String EmailPer) {
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
	public void submitBuildingSectionAndRoomInAdditionalInfo(String BuildingNo, String SectionNo, String SimCardNo) {
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
	public void submitPersonalAlterMobileSimNoInAdditionalInfo(String MobilePersonal, String AlterNo,
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
	public void submitPhoneExtensionAndFaxNoInAdditionalInfo(String PhoneNo, String ExtensionNo, String FaxNo) {
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
	public void clickTickIconInAdditionalInfo() {
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
	public void clickDependentDetailsSideNavBar() {
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
	public void clickAddIconInDependentDetails() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_DependentAddIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_DependentAddIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_DependentAddIcon, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_DependentNameData(), ElementType.Xpath);
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
	public void fillRequiredDetailsInDependentDetails(String DependentName, String RelationShip, String DOB,
			String AadharNo, String EmailId, String ContactNo, String MonthlyEarning) {
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.AddEmp_DependentNameData(),
				EmployeeMasterLocators.AddEmp_DependentNameInput(), DependentName);
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.AddEmp_DependentAadharNoData(),
				EmployeeMasterLocators.AddEmp_DependentAadharNoInput(), AadharNo);
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.AddEmp_DependentContactNoData(),
				EmployeeMasterLocators.AddEmp_DependentContactNoInput(), ContactNo);
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.AddEmp_DependentEmailIdData(),
				EmployeeMasterLocators.AddEmp_DependentEmailIdInput(), EmailId);
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.AddEmp_DependentDOBData(),
				EmployeeMasterLocators.AddEmp_DependentDOBInput(), DOB);
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.AddEmp_DependentMonthEarningData(),
				EmployeeMasterLocators.AddEmp_DependentMonthEarningInput(), MonthlyEarning);
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_DependentRelationshipData(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_DependentRelationshipData(), ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_DependentRelationshipSelect(), ElementType.Xpath);
			if (flag) {
				flag = webDB.selectDropDownByText(EmployeeMasterLocators.AddEmp_DependentRelationshipSelect(),
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
	public void clickSaveIconInDependentDetails() {
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
	public void clickUpdateButtonInAddEmployee() {
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
	public void switchToUpdateIframe() {
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
	public void clickEmployeeParticularTabInUpdateEmployee() {
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
	public void clickSaveIconAndPostInEmployeeParticular() {
		switchToUpdateIframe();
		flag = webDB.waitForClickElement(EmployeeMasterLocators.AddEmp_UpdateEmpParticularSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_UpdateEmpParticularSaveIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_UpdateEmpParticularSaveIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
			if (flag) {
				String Value = webDB.getTextFromElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
				String Values[] = Value.split("Doc. No. =", Value.length());
				VariableHandler.setEmpUpdateDocNo(Values[1].trim());
				logs.logging("INFO", "Employee Update Document No: " + Values[1]);
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_EmpParticularPostBtn, ElementType.Xpath);
				if (flag) {
					commonFunc.checkSuccessToastMessageDisappear();
					webDB.moveToElement(EmployeeMasterLocators.UpdateEmp_EmpParticularPostBtn, ElementType.Xpath);
					webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_EmpParticularPostBtn, ElementType.Xpath);
					flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
					if (flag) {
						logs.logging("INFO", "Employee Particular Update Post Button Clicked");
					}
				}
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
	public void updateEmployeeGenderFirstAndLastNameInParticularTab(String Gender, String FName, String LName) {
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
	public void updateEmployeeDOBAndCalenderInParticularTab(String DOB, String Calender) {
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
	public void updateEmployeeShiftMaritalStatusAndZone(String Shift, String MaritalStatus, String Zone) {
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
				switchToUpdateIframe();
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
	public void updateEmployeeCategoryAndAccountCategory(String Category, String AccountCategory) {
		switchToUpdateIframe();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_UpdateCategoryLovbtn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_UpdateCategoryLovbtn, Category);
			VariableHandler.setEmpCompanyCategory(Category);
			switchToUpdateIframe();
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
	public void updateEmployeePayrollGrpAndNoticePeriod(String PayrollGrp, String NoticePeriod) {
		switchToUpdateIframe();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_UpdatePayrollGroupLovbtn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_UpdatePayrollGroupLovbtn,
					PayrollGrp);
			VariableHandler.setEmpPayrollGroup(PayrollGrp);
			switchToUpdateIframe();
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
	public void clickAddressTabInUpdateEmployee() {
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
	public void updateEmployeeAllAddressInAddressTab(String Address1, String Address2, String Address3) {
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
	public void updateEmpCityZipCodeAndTelephoneInAddressTab(String City, String ZipCode, String Telephone) {
		switchToUpdateIframe();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_UpdateAddressCityLovBtn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_UpdateAddressCityLovBtn, City);
			switchToUpdateIframe();
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
	public void clickSaveIconAndPostInAddress() {
		switchToUpdateIframe();
		flag = webDB.waitForClickElement(EmployeeMasterLocators.AddEmp_UpdateEmpAddressSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_UpdateEmpAddressSaveIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_UpdateEmpAddressSaveIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
			if (flag) {
				String Value = webDB.getTextFromElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
				String Values[] = Value.split("Doc. No. =", Value.length());
				VariableHandler.setEmpUpdateDocNo(Values[1].trim());
				logs.logging("INFO", "Employee Update Document No: " + Values[1]);
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_AddressPostBtn, ElementType.Xpath);
				if (flag) {
					commonFunc.checkSuccessToastMessageDisappear();
					webDB.moveToElement(EmployeeMasterLocators.UpdateEmp_AddressPostBtn, ElementType.Xpath);
					webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_AddressPostBtn, ElementType.Xpath);
					flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
					if (flag) {
						logs.logging("INFO", "Employee Address Update Post Button Clicked");
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click close Icon in update Employee
	 * 
	 * @author Sakthi-GWL
	 */
	public void closeUpdateIframe() {
		flag = webDB.waitForClickElement(EmployeeMasterLocators.AddEmp_UpdateIframeCloseIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_UpdateIframeCloseIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_UpdateIframeCloseIcon, ElementType.Xpath);
			flag = webDB.waitForClickElement(EmployeeMasterLocators.AddEmp_UpdateButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Update Frame Closed Successfully");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used to click Active Icon in Leave Details with Leave Type
	 * 
	 * @author Sakthi-GWL
	 * @param LeaveType
	 */
	public void clickActiveIconForLeaveType(String LeaveType) {
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityActiveIcon(LeaveType),
				ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityEffYearData(), ElementType.Xpath);
			webDB.scrollToAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMinDaysData(), ElementType.Xpath);
			webDB.scrollToAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityActiveIcon(LeaveType),
					ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityActiveIcon(LeaveType),
					ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
			if (!flag) {
				webDB.moveToElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityEffYearData(), ElementType.Xpath);
				webDB.scrollToAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMinDaysData(), ElementType.Xpath);
				webDB.scrollToAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityActiveIcon(LeaveType),
						ElementType.Xpath);
				webDB.javaScriptClickAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityActiveIcon(LeaveType),
						ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
			}
			if (flag) {
				commonFunc.hardWait(3000);
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				logs.logging("INFO", "Active Icon Clicked and Toast Message Displayed");
				if (webDB.verifyIsElementDisplayed(EmployeeMasterLocators.AddEmp_LeaveEligibilityRestoreIcon,
						ElementType.Xpath)) {
					webDB.waitForClickElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityRestoreIcon,
							ElementType.Xpath);
					webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityRestoreIcon, ElementType.Xpath);
					flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMaximizeIcon,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Restore Button Clicked and Minimized Successfully");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click dependent tab in update employee
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickDependentTabInUpdateEmployee() {
		switchToUpdateIframe();
		flag = webDB.waitForClickElement(EmployeeMasterLocators.AddEmp_UpdateDependentTab, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_UpdateDependentTab, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_UpdateDependentTab, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_DependentAddLabel, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Dependent Tab Clicked in Update Employee");
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to select radio button in dependent details to update
	 * employee
	 * 
	 * @author Sakthi-GWL
	 * @param Type
	 */
	public void selectDependentTypeToUpdate(String Type) {
		switchToUpdateIframe();
		flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_DependentAddLabel, ElementType.Xpath);
		if (flag) {
			if (Type.equalsIgnoreCase("Add")) {
				flag = webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_DependentAddLabel, ElementType.Xpath);
			} else if (Type.equalsIgnoreCase("Modify")) {
				flag = webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_DependentModifyLabel, ElementType.Xpath);
			} else if (Type.equalsIgnoreCase("Remove")) {
				flag = webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_DependentRemoveLabel, ElementType.Xpath);
			} else {
				logs.logging("INFO", "Invalid Dependent Type entered: " + Type);
				Assert.fail("Invalid Dependent Type entered: " + Type);
			}
		}
		if (flag) {
			logs.logging("INFO", "Radio Button Clicked: " + Type);
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click add icon in update dependent details
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAddIconInUpdateDependentDetails() {
		switchToUpdateIframe();
		flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_DependentAddIcon, ElementType.Xpath);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			webDB.moveToElement(EmployeeMasterLocators.UpdateEmp_DependentAddIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_DependentAddIcon, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_DependentNameData(), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Icon Clicked in Update Dependent Details");
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click save and post icon in update dependent details
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickSaveAndPostInUpdateDependentDetails() {
		switchToUpdateIframe();
		flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_DependentSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.UpdateEmp_DependentSaveIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_DependentSaveIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
			if (flag) {
				String Value = webDB.getTextFromElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
				String Values[] = Value.split("Doc. No. =", Value.length());
				VariableHandler.setEmpUpdateDocNo(Values[1].trim());
				logs.logging("INFO", "Employee Update Document No: " + Values[1]);
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_DependentPostBtn, ElementType.Xpath);
				if (flag) {
					commonFunc.checkSuccessToastMessageDisappear();
					webDB.moveToElement(EmployeeMasterLocators.UpdateEmp_DependentPostBtn, ElementType.Xpath);
					webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_DependentPostBtn, ElementType.Xpath);
					flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
					if (flag) {
						logs.logging("INFO", "Employee Dependent Update Post Button Clicked");
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to fill details in update dependent details in update
	 * employee
	 * 
	 * @author Sakthi-GWL
	 * @param DependentName
	 * @param Relation
	 * @param DOB
	 * @param Aadhar
	 * @param Email
	 * @param Contact
	 * @param MonthEarn
	 */
	public void fillDetailsInUpdateDependentDetails(String DependentName, String Relation, String DOB, String Aadhar,
			String Email, String Contact, String MonthEarn) {
		switchToUpdateIframe();
		commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.UpdateEmp_DependentNameData(),
				EmployeeMasterLocators.UpdateEmp_DependentNameInput(), DependentName);
		logs.logging("INFo", "Dependent Name Entered: " + DependentName);
		commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.UpdateEmp_DependentEmailData(),
				EmployeeMasterLocators.UpdateEmp_DependentEmailInput(), Email);
		logs.logging("INFo", "Email Entered: " + Email);
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.UpdateEmp_DependentAadharData(),
				EmployeeMasterLocators.UpdateEmp_DependentAadharInput(), Aadhar);
		logs.logging("INFo", "Aadhar No Entered: " + Aadhar);
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.UpdateEmp_DependentContactData(),
				EmployeeMasterLocators.UpdateEmp_DependentContactInput(), Contact);
		logs.logging("INFo", "Contact Entered: " + Contact);
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.UpdateEmp_DependentMonthlyEarnData(),
				EmployeeMasterLocators.UpdateEmp_DependentMonthlyEarnInput(), MonthEarn);
		logs.logging("INFo", "Monthly Earn Entered: " + MonthEarn);
		commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.UpdateEmp_DependentDOBData(),
				EmployeeMasterLocators.UpdateEmp_DependentDOBInput(), DOB);
		logs.logging("INFo", "DOB Entered: " + DOB);
		flag = webDB.waitForClickElement(EmployeeMasterLocators.UpdateEmp_DependentRelationData(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_DependentRelationData(), ElementType.Xpath);
			flag = webDB.waitForClickElement(EmployeeMasterLocators.UpdateEmp_DependentRelationSelect(),
					ElementType.Xpath);
			if (flag) {
				flag = webDB.selectDropDownByText(EmployeeMasterLocators.UpdateEmp_DependentRelationSelect(), Relation,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFo", "Relation Selected: " + Relation);
				}
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click contact tab in update employee
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickContactTabInUpdateEmployee() {
		switchToUpdateIframe();
		flag = webDB.waitForClickElement(EmployeeMasterLocators.AddEmp_UpdateContactTab, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_UpdateContactTab, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_UpdateContactTab, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_ContactAddLabel, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Contact Tab Clicked in Update Employee");
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to select radio button in contact details to update employee
	 * 
	 * @author Sakthi-GWL
	 * @param Type
	 */
	public void selectContactTypeToUpdate(String Type) {
		switchToUpdateIframe();
		flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_ContactAddLabel, ElementType.Xpath);
		if (flag) {
			if (Type.equalsIgnoreCase("Add")) {
				flag = webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_ContactAddLabel, ElementType.Xpath);
			} else if (Type.equalsIgnoreCase("Modify")) {
				flag = webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_ContactModifyLabel, ElementType.Xpath);
			} else if (Type.equalsIgnoreCase("Remove")) {
				flag = webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_ContactRemoveLabel, ElementType.Xpath);
			} else {
				logs.logging("INFO", "Invalid Contact Type entered: " + Type);
				Assert.fail("Invalid Contact Type entered: " + Type);
			}
		}
		if (flag) {
			logs.logging("INFO", "Radio Button Clicked: " + Type);
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click add icon in update contact details
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAddIconInUpdateContactDetails() {
		switchToUpdateIframe();
		flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_ContactAddIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.UpdateEmp_ContactAddIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_ContactAddIcon, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_ContactNameData(), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Icon Clicked in Update contact Details");
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click save and post icon in update contact details
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickSaveAndPostInUpdateContactDetails() {
		switchToUpdateIframe();
		flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_ContactSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.UpdateEmp_ContactSaveIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_ContactSaveIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
			if (flag) {
				String Value = webDB.getTextFromElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
				String Values[] = Value.split("Doc. No. =", Value.length());
				VariableHandler.setEmpUpdateDocNo(Values[1].trim());
				logs.logging("INFO", "Employee Update Document No: " + Values[1]);
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_ContactPostBtn, ElementType.Xpath);
				if (flag) {
					commonFunc.checkSuccessToastMessageDisappear();
					webDB.moveToElement(EmployeeMasterLocators.UpdateEmp_ContactPostBtn, ElementType.Xpath);
					webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_ContactPostBtn, ElementType.Xpath);
					flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
					if (flag) {
						logs.logging("INFO", "Employee Contacts Update Post Button Clicked");
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to fill details in update contact details tab
	 * 
	 * @author Sakthi-GWL
	 * @param ContactName
	 * @param Relation
	 * @param MblNo
	 * @param Address
	 * @param City
	 */
	public void fillDetailsInUpdateContactDetails(String ContactName, String Relation, String MblNo, String Address,
			String City) {
		switchToUpdateIframe();
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.UpdateEmp_ContactNameData(),
				EmployeeMasterLocators.UpdateEmp_ContactNameInput(), ContactName);
		logs.logging("INFo", "Contact Name Entered: " + ContactName);
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.UpdateEmp_ContactRelationData(),
				EmployeeMasterLocators.UpdateEmp_ContactRelationInput(), Relation);
		logs.logging("INFo", "Relation Entered: " + Relation);
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.UpdateEmp_ContactMobileData(),
				EmployeeMasterLocators.UpdateEmp_ContactMobileInput(), MblNo);
		logs.logging("INFo", "Mobile No Entered: " + MblNo);
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.UpdateEmp_ContactAddressData(),
				EmployeeMasterLocators.UpdateEmp_ContactAddressInput(), Address);
		logs.logging("INFo", "Address Entered: " + Address);
		flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_ContactCityData(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_ContactCityData(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.UpdateEmp_ContactCityLovBtn(), City);
			logs.logging("INFo", "City Selected: " + City);
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click qualification tab in update employee
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickQualificationTabInUpdateEmployee() {
		switchToUpdateIframe();
		flag = webDB.waitForClickElement(EmployeeMasterLocators.AddEmp_UpdateQualificationTab, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_UpdateQualificationTab, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_UpdateQualificationTab, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_QualificationAddLabel, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Qualification Tab Clicked in Update Employee");
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to select radio button in qualification details to update
	 * employee
	 * 
	 * @author Sakthi-GWL
	 * @param Type
	 */
	public void selectQualificationTypeToUpdate(String Type) {
		switchToUpdateIframe();
		flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_QualificationAddLabel, ElementType.Xpath);
		if (flag) {
			if (Type.equalsIgnoreCase("Add")) {
				flag = webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_QualificationAddLabel, ElementType.Xpath);
			} else if (Type.equalsIgnoreCase("Modify")) {
				flag = webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_QualificationModifyLabel,
						ElementType.Xpath);
			} else if (Type.equalsIgnoreCase("Remove")) {
				flag = webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_QualificationRemoveLabel,
						ElementType.Xpath);
			} else {
				logs.logging("INFO", "Invalid Qualification Type entered: " + Type);
				Assert.fail("Invalid Qualification Type entered: " + Type);
			}
		}
		if (flag) {
			logs.logging("INFO", "Radio Button Clicked: " + Type);
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click add icon in update qualification details
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAddIconInUpdateQualificationDetails() {
		switchToUpdateIframe();
		flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_QualificationAddIcon, ElementType.Xpath);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			webDB.moveToElement(EmployeeMasterLocators.UpdateEmp_QualificationAddIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_QualificationAddIcon, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_QualificationTableData(), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Icon Clicked in Update qualification Details");
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click save and post icon in update qualification details
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickSaveAndPostInUpdateQualificationDetails() {
		switchToUpdateIframe();
		flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_QualificationSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.UpdateEmp_QualificationSaveIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_QualificationSaveIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
			if (flag) {
				String Value = webDB.getTextFromElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
				String Values[] = Value.split("Doc. No. =", Value.length());
				VariableHandler.setEmpUpdateDocNo(Values[1].trim());
				logs.logging("INFO", "Employee Update Document No: " + Values[1]);
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_QualificationPostBtn, ElementType.Xpath);
				if (flag) {
					commonFunc.checkSuccessToastMessageDisappear();
					webDB.moveToElement(EmployeeMasterLocators.UpdateEmp_QualificationPostBtn, ElementType.Xpath);
					webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_QualificationPostBtn, ElementType.Xpath);
					flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
					if (flag) {
						logs.logging("INFO", "Employee Qualification Update Post Button Clicked");
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to fill details in update qualification details tab
	 * 
	 * @author Sakthi-GWL
	 * @param Qualification
	 * @param BranchOfStudy
	 * @param Institute
	 * @param TotalYear
	 * @param YearOfPass
	 * @param Grade
	 * @param Aggregate
	 */
	public void fillDetailsInUpdateQualificationDetails(String Qualification, String BranchOfStudy, String Institute,
			String TotalYear, String YearOfPass, String Grade, String Aggregate) {
		switchToUpdateIframe();
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.UpdateEmp_QualInstituteData(),
				EmployeeMasterLocators.UpdateEmp_QualInstituteInput(), Institute);
		logs.logging("INFo", "Institute Entered: " + Institute);
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.UpdateEmp_QualTotalYearData(),
				EmployeeMasterLocators.UpdateEmp_QualTotalYearInput(), TotalYear);
		logs.logging("INFo", "Total Year Entered: " + TotalYear);
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.UpdateEmp_QualYearOfPassData(),
				EmployeeMasterLocators.UpdateEmp_QualYearOfPassInput(), YearOfPass);
		logs.logging("INFo", "Year Of Passing Entered: " + YearOfPass);
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.UpdateEmp_QualGradeData(),
				EmployeeMasterLocators.UpdateEmp_QualGradeInput(), Grade);
		logs.logging("INFo", "Grade Entered: " + Grade);
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.UpdateEmp_QualAggregateData(),
				EmployeeMasterLocators.UpdateEmp_QualAggregateInput(), Aggregate);
		logs.logging("INFo", "Aggregate percent Entered: " + Aggregate);
		flag = webDB.waitForClickElement(EmployeeMasterLocators.UpdateEmp_QualificationTableData(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_QualificationTableData(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.UpdateEmp_QualificationTableLovBtn(),
					Qualification);
			logs.logging("INFo", "Qualification Selected: " + Qualification);
			switchToUpdateIframe();
			flag = webDB.waitForClickElement(EmployeeMasterLocators.UpdateEmp_QualBranchOfStudyData(),
					ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_QualBranchOfStudyData(), ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.UpdateEmp_QualBranchOfStudyLovBtn(),
						BranchOfStudy);
				logs.logging("INFo", "Branch Of Study Selected: " + BranchOfStudy);
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click skill tab in update employee
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickSkillTabInUpdateEmployee() {
		switchToUpdateIframe();
		flag = webDB.waitForClickElement(EmployeeMasterLocators.AddEmp_UpdateSkillTab, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_UpdateSkillTab, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_UpdateSkillTab, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_SkillAddLabel, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Skill Tab Clicked in Update Employee");
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to select radio button in skill details to update employee
	 * 
	 * @author Sakthi-GWL
	 * @param Type
	 */
	public void selectSkillTypeToUpdate(String Type) {
		switchToUpdateIframe();
		flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_SkillAddLabel, ElementType.Xpath);
		if (flag) {
			if (Type.equalsIgnoreCase("Add")) {
				flag = webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_SkillAddLabel, ElementType.Xpath);
			} else if (Type.equalsIgnoreCase("Modify")) {
				flag = webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_SkillModifyLabel, ElementType.Xpath);
			} else if (Type.equalsIgnoreCase("Remove")) {
				flag = webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_SkillRemoveLabel, ElementType.Xpath);
			} else {
				logs.logging("INFO", "Invalid Skill Type entered: " + Type);
				Assert.fail("Invalid Skill Type entered: " + Type);
			}
		}
		if (flag) {
			logs.logging("INFO", "Radio Button Clicked: " + Type);
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click add icon in update skill details
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAddIconInUpdateSkillDetails() {
		switchToUpdateIframe();
		flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_SkillAddIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.UpdateEmp_SkillAddIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_SkillAddIcon, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_SkillTableData(), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Icon Clicked in Update Skill Details");
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click save and post icon in update skill details
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickSaveAndPostInUpdateSkillDetails() {
		switchToUpdateIframe();
		flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_SkillSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.UpdateEmp_SkillSaveIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_SkillSaveIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
			if (flag) {
				String Value = webDB.getTextFromElement(CommonUiLocators.SuccessToastMessage, ElementType.Xpath);
				String Values[] = Value.split("Doc. No. =", Value.length());
				VariableHandler.setEmpUpdateDocNo(Values[1].trim());
				logs.logging("INFO", "Employee Update Document No: " + Values[1]);
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				flag = webDB.waitForElement(EmployeeMasterLocators.UpdateEmp_SkillPostBtn, ElementType.Xpath);
				if (flag) {
					commonFunc.checkSuccessToastMessageDisappear();
					webDB.moveToElement(EmployeeMasterLocators.UpdateEmp_SkillPostBtn, ElementType.Xpath);
					webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_SkillPostBtn, ElementType.Xpath);
					flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
					if (flag) {
						logs.logging("INFO", "Employee Skills Update Post Button Clicked");
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to fill details in update skill details tab
	 * 
	 * @author Sakthi-GWL
	 * @param Skill
	 * @param Level
	 */
	public void fillDetailsInUpdateSkillDetails(String Skill, String Level) {
		switchToUpdateIframe();
		flag = webDB.waitForClickElement(EmployeeMasterLocators.UpdateEmp_SkillTableData(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_SkillTableData(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.UpdateEmp_SkillTableLovBtn(), Skill);
			logs.logging("INFo", "Skill Selected: " + Skill);
			switchToUpdateIframe();
			flag = webDB.waitForClickElement(EmployeeMasterLocators.UpdateEmp_SkillLevelData(), ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(EmployeeMasterLocators.UpdateEmp_SkillLevelData(), ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.UpdateEmp_SkillLevelLovBtn(), Level);
				logs.logging("INFo", "Skill Level Selected: " + Level);
			}
		}
		webDB.switchToDefaultContent();
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to search and select employee id in find page
	 * 
	 * @author Sakthi-GWL
	 * @param EmpId
	 */
	public void searchAndClickEmpIdInFindEmployeePage(String EmpId) {
		flag = webDB.waitForElement(EmployeeMasterLocators.Find_EmpNameLovBtn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.Find_EmpNameLovBtn, EmpId);
			flag = webDB.waitForElement(EmployeeMasterLocators.FindSearchBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(EmployeeMasterLocators.FindSearchBtn, ElementType.Xpath);
				logs.logging("INFO", "Search Button Clicked in Find Employee Page");
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.FindSearchBar,
						EmployeeMasterLocators.FindSearchBar, EmpId);
				commonFunc.checkRefreshCellsDisappear();
				webDB.clickAnElement(EmployeeMasterLocators.FindSearchGoBtn, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(EmployeeMasterLocators.FindResult_TableEmpId(EmpId), ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "EmpId Displayed after search: " + EmpId);
					webDB.clickAnElement(EmployeeMasterLocators.FindResult_TableEmpId(EmpId), ElementType.Xpath);
					flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_EmpployeeMasterSideNav,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Employee Page Navigated for EmpId: " + EmpId);
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click Emplyee History and check not null and close
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickEmployeeHistoryAndCheckNotNull() {
		flag = webDB.waitForElement(EmployeeMasterLocators.EmployeeHistoryBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.EmployeeHistoryBtn, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.EmployeeHistoryBtn, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.EmployeeHistoryIframe, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(EmployeeMasterLocators.EmployeeHistoryIframe, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				List<WebElement> elements = webDB.getListOfElements(EmployeeMasterLocators.EmployeeHistoryTableRowCount,
						ElementType.Xpath);
				flag = elements.size() > 0;
				if (flag) {
					logs.logging("INFO", "Employee History data not null, Displayed History size: " + elements.size());
				}
			}
		}
		webDB.switchToDefaultContent();
		if (webDB.verifyIsElementDisplayed(EmployeeMasterLocators.EmpHistIframeCloseIcon, ElementType.Xpath)) {
			webDB.moveToElement(EmployeeMasterLocators.EmpHistIframeCloseIcon, ElementType.Xpath);
			webDB.clickAnElementWithoutException(EmployeeMasterLocators.EmpHistIframeCloseIcon, ElementType.Xpath);
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click view profile and store profile and Batch No
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickViewProfileAndStoreProfileAndBatchNo() {
		flag = webDB.waitForClickElement(EmployeeMasterLocators.ViewProfileBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.ViewProfileBtn, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.ViewProfileBtn, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.ViewProf_ProfileNo, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				String ProfNo = webDB.getTextFromElement(EmployeeMasterLocators.ViewProf_ProfileNo, ElementType.Xpath);
				logs.logging("INFO", "Displayed Profile No: " + ProfNo);
				VariableHandler.setEmpProfileNo(ProfNo);
				String BatchNo = webDB.getTextFromElement(EmployeeMasterLocators.ViewProf_BatchNo, ElementType.Xpath);
				logs.logging("INFO", "Displayed Batch No: " + BatchNo);
				VariableHandler.setEmpBatchNo(BatchNo);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click profile no and reverse the employee profile
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickEmployeeProfileNoAndReverse() {
		flag = webDB.waitForClickElement(EmployeeMasterLocators.ViewProf_ProfileNo, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.ViewProf_ProfileNo, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.ViewProf_ProfileNo, ElementType.Xpath);
			flag = webDB.waitForClickElement(EmployeeMasterLocators.ViewProf_ReverseBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Profile No Clicked and Reverse Button Displayed");
				webDB.moveToElement(EmployeeMasterLocators.ViewProf_ReverseBtn, ElementType.Xpath);
				webDB.clickAnElement(EmployeeMasterLocators.ViewProf_ReverseBtn, ElementType.Xpath);
				flag = webDB.waitForClickElement(EmployeeMasterLocators.ViewProf_ReverseOkBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Reverse Button Clicked and Ok Button Displayed");
					webDB.moveToElement(EmployeeMasterLocators.ViewProf_ReverseOkBtn, ElementType.Xpath);
					webDB.clickAnElement(EmployeeMasterLocators.ViewProf_ReverseOkBtn, ElementType.Xpath);
					flag = webDB.waitForClickElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Ok Button Clicked and Toast Message Displayed.. Employee Reversed");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Navigate to Employee Migration
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAndNavigateEmployeeMigration() {
		flag = webDB.waitForElement(EmployeeMasterLocators.MigrationBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.MigrationBtn, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.MigrationIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Migration Button Clicked");
				webDB.switchToFrame(EmployeeMasterLocators.MigrationIframe, ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeMasterLocators.EmpMigrationLabel, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(EmployeeMasterLocators.EmpMigrationLabel, ElementType.Xpath);
					webDB.switchToDefaultContent();
					commonFunc.checkRefreshCellsDisappear();
					webDB.waitUntilElementToNotBeStale(EmployeeMasterLocators.EmpMigrationAddButton, ElementType.Xpath);
					flag = webDB.waitForElement(EmployeeMasterLocators.EmpMigrationAddButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Employee Migration Radio Button Clicked");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click Add and Fill Details in Employee Migration
	 * 
	 * @author Sakthi-GWL
	 * @param Calendar
	 * @param Payment
	 * @param PaymentType
	 * @param Ref
	 */
	public void clickAddAndFillDetailsInEmployeeMigration(String Calendar, String Payment, String PaymentType,
			String Ref) {
		webDB.waitUntilElementToNotBeStale(EmployeeMasterLocators.EmpMigrationAddButton, ElementType.Xpath);
		flag = webDB.waitForElement(EmployeeMasterLocators.EmpMigrationAddButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.EmpMigrationAddButton, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.EmpMigrationAddButton, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.EmpMigrationCalendarLov, Calendar);
			logs.logging("INFO", "Employee Migration Calendar Selected: " + Calendar);
			flag = webDB.waitForElement(EmployeeMasterLocators.EmpMigrationPaymentSelect, ElementType.Xpath);
			if (flag) {
				flag = webDB.selectDropDownByText(EmployeeMasterLocators.EmpMigrationPaymentSelect, Payment,
						ElementType.Xpath);
				webDB.waitForElement(EmployeeMasterLocators.EmpMigrationPaymentTypeSelect, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Employee Migration Payment Selected: " + Payment);
					flag = webDB.selectDropDownByText(EmployeeMasterLocators.EmpMigrationPaymentTypeSelect, PaymentType,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Employee Migration Payment Type Selected: " + PaymentType);
						flag = webDB.sendTextToAnElement(EmployeeMasterLocators.EmpMigrationReferenceInput, Ref,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Employee Migration Reference Entered: " + Ref);
							webDB.moveToElement(EmployeeMasterLocators.EmpMigrationCreateSaveIcon, ElementType.Xpath);
							webDB.clickAnElement(EmployeeMasterLocators.EmpMigrationCreateSaveIcon, ElementType.Xpath);
							flag = webDB.waitForElement(EmployeeMasterLocators.EmpMigrationUploadTemplateBtn,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Employee Migration Create Save Icon Clicked");
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to upload template in Employee Migration
	 * 
	 * @author Sakthi-GWL
	 */
	public void uploadEmployeeMigrationTemplate() {
		flag = webDB.waitForElement(EmployeeMasterLocators.EmpMigrationUploadTemplateBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.EmpMigrationUploadTemplateBtn, ElementType.Xpath);
			File file = new File(VariableHandler.testdataDir + webDB.getDataFromProperties("EmpMigrationFileName"));
			flag = webDB.waitForElement(EmployeeMasterLocators.EmpMigrationUploadInput, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Upload Icon Clicked for Employee Migration");
				webDB.sendTextToAnElement(EmployeeMasterLocators.EmpMigrationUploadInput, file.getAbsolutePath(),
						ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(EmployeeMasterLocators.EmpMigrationUploadBtn, ElementType.Xpath);
				if (flag) {
					commonFunc.checkRefreshCellsDisappear();
					webDB.clickAnElement(EmployeeMasterLocators.EmpMigrationUploadBtn, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "File Uploaded Succesfully for Employee Migration");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to check Exception and Save Header in Employee Migration
	 * 
	 * @author Sakthi-GWL
	 */
	public void checkExceptionAndSaveHeaderInEmpMigration() {
		flag = webDB.waitForElement(EmployeeMasterLocators.EmpMigrationCheckExceptionBtn, ElementType.Xpath);
		if (flag) {
			commonFunc.checkSuccessToastMessageDisappear();
			webDB.moveToElement(EmployeeMasterLocators.EmpMigrationCheckExceptionBtn, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.EmpMigrationCheckExceptionBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				commonFunc.checkSuccessToastMessageDisappear();
				logs.logging("INFO", "No Exception Found for Employee Migration");
				if (webDB.verifyIsElementDisplayed(EmployeeMasterLocators.EmpMigrationHeaderSaveIcon,
						ElementType.Xpath)) {
					webDB.clickAnElement(EmployeeMasterLocators.EmpMigrationHeaderSaveIcon, ElementType.Xpath);
				}
				logs.logging("INFO", "Header Saved Succesfully for Employee Migration");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Post uploaded template in Employee Migration
	 * 
	 * @author Sakthi-GWL
	 */
	public void postUploadedTemplateInEmployeeMigration() {
		flag = webDB.waitForElement(EmployeeMasterLocators.EmpMigrationPostBtn, ElementType.Xpath);
		if (flag) {
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			webDB.moveToElement(EmployeeMasterLocators.EmpMigrationPostBtn, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.EmpMigrationPostBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				logs.logging("INFO", "Employee Migration uploaded template Posted");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click and Navigate Employee Details Migration
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAndNavigateEmployeeDetailsMigration() {
		flag = webDB.waitForElement(EmployeeMasterLocators.MigrationBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.MigrationBtn, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.MigrationIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Migration Button Clicked");
				webDB.switchToFrame(EmployeeMasterLocators.MigrationIframe, ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeMasterLocators.EmpDetailsMigrationLabel, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(EmployeeMasterLocators.EmpDetailsMigrationLabel, ElementType.Xpath);
					webDB.switchToDefaultContent();
					webDB.waitUntilElementToNotBeStale(EmployeeMasterLocators.EmpDetailsMigrationCreateAddIcon,
							ElementType.Xpath);
					flag = webDB.waitForElement(EmployeeMasterLocators.EmpDetailsMigrationCreateAddIcon,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Employee Details Migration Radio Button Clicked");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Enter Reference in Employee Details Migration
	 * 
	 * @author Sakthi-GWL
	 * @param Ref
	 */
	public void createAndFillDetailsInEmpDetailsMigration(String Ref) {
		webDB.waitUntilElementToNotBeStale(EmployeeMasterLocators.EmpDetailsMigrationCreateAddIcon, ElementType.Xpath);
		flag = webDB.waitForElement(EmployeeMasterLocators.EmpDetailsMigrationCreateAddIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.EmpDetailsMigrationCreateAddIcon, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.EmpDetailsMigrationReferenceInput, ElementType.Xpath);
			if (flag) {
				webDB.sendTextToAnElement(EmployeeMasterLocators.EmpDetailsMigrationReferenceInput, Ref,
						ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeMasterLocators.EmpDetailsMigrationCreateSaveButton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(EmployeeMasterLocators.EmpDetailsMigrationCreateSaveButton, ElementType.Xpath);
					flag = webDB.waitForElement(EmployeeMasterLocators.EmpDetailsMigrationUploadIcon,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Employee Details Migration Reference entered and Save Button Clicked");
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click Select Field Options In Employee Details Migration
	 * Page
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickSelectFieldOptionsInEmpDetailsMigration() {
		flag = webDB.waitForElement(EmployeeMasterLocators.EmpDetailsMigrationSelectFieldLabel, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.EmpDetailsMigrationSelectFieldLabel, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.EmpDetailsMigrationSelectFieldAddIcon,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Select Field Clicked in Employee Details Migration");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Select Field and enter Sequence in Employee Details
	 * Migration Page
	 * 
	 * @author Sakthi-GWL
	 * @param Option
	 * @param Seq
	 */
	public void clickAddSelectFieldAndSelectSequence(String Option, String Seq) {
		flag = webDB.waitForElement(EmployeeMasterLocators.EmpDetailsMigrationSelectFieldAddIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.EmpDetailsMigrationSelectFieldAddIcon, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.EmpDetailsSelectFieldDataTypeTableData,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Select Field Add Icon Clicked Successfully");
				webDB.clickAnElement(EmployeeMasterLocators.EmpDetailsSelectFieldDataTypeTableData, ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeMasterLocators.EmpDetailsSelectFieldDataTypeSelect,
						ElementType.Xpath);
				if (flag) {
					flag = webDB.selectDropDownByText(EmployeeMasterLocators.EmpDetailsSelectFieldDataTypeSelect,
							Option, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Selected Value in dropdown: " + Option);
						commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.EmpDetailsSelectFieldSequenceTableData,
								EmployeeMasterLocators.EmpDetailsSelectFieldSequenceInput, Seq);
						flag = webDB.waitForElement(EmployeeMasterLocators.EmpDetailsMigrationSelectFieldSaveIcon,
								ElementType.Xpath);
						if (flag) {
							flag = webDB.clickAnElement(EmployeeMasterLocators.EmpDetailsMigrationSelectFieldSaveIcon,
									ElementType.Xpath);
							webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
							if (flag) {
								if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon,
										ElementType.Xpath)) {
									webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
											ElementType.Xpath);
								}
								logs.logging("INFO", "Select Field Saved Successfully");
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to upload Employee Details Template
	 * 
	 * @author Sakthi-GWL
	 */
	public void uploadEmployeeDetailsMigrationTemplate() {
		flag = webDB.waitForElement(EmployeeMasterLocators.EmpDetailsMigrationUploadIcon, ElementType.Xpath);
		if (flag) {
			commonFunc.checkSuccessToastMessageDisappear();
			webDB.moveToElement(EmployeeMasterLocators.EmpDetailsMigrationUploadIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.EmpDetailsMigrationUploadIcon, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.EmpDetailsMigrationUploadIframe, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(EmployeeMasterLocators.EmpDetailsMigrationUploadIframe, ElementType.Xpath);
				File file = new File(
						VariableHandler.testdataDir + webDB.getDataFromProperties("EmpDetailsMigrationFileName"));
				flag = webDB.waitForElement(EmployeeMasterLocators.EmpDetailsMigrationUploadInput, ElementType.Xpath);
				if (flag) {
					commonFunc.checkRefreshCellsDisappear();
					logs.logging("INFO", "Upload Icon Clicked for Employee Details Migration");
					webDB.sendTextToAnElement(EmployeeMasterLocators.EmpDetailsMigrationUploadInput,
							file.getAbsolutePath(), ElementType.Xpath);
					flag = webDB.waitForElement(EmployeeMasterLocators.EmpDetailsMigrationUploadBtn, ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.clickAnElement(EmployeeMasterLocators.EmpDetailsMigrationUploadBtn, ElementType.Xpath);
						webDB.switchToDefaultContent();
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
						if (flag) {
							if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
								webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
										ElementType.Xpath);
							}
							logs.logging("INFO", "File Uploaded Succesfully for Employee Migration");
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to check Exception and Save Header in Employee Details
	 * Migration
	 * 
	 * @author Sakthi-GWL
	 */
	public void checkExceptionAndSaveHeaderInEmpDetailsMigration() {
		flag = webDB.waitForElement(EmployeeMasterLocators.EmpDetailsMigrationCheckExceptionBtn, ElementType.Xpath);
		if (flag) {
			commonFunc.checkSuccessToastMessageDisappear();
			webDB.moveToElement(EmployeeMasterLocators.EmpDetailsMigrationCheckExceptionBtn, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.EmpDetailsMigrationCheckExceptionBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "No Exception Found for Employee Details Migration");
//				if (webDB.verifyIsElementDisplayed(EmployeeMasterLocators.EmpDetailsMigrationHeaderSaveIcon,
//						ElementType.Xpath)) {
//					webDB.clickAnElement(EmployeeMasterLocators.EmpDetailsMigrationHeaderSaveIcon, ElementType.Xpath);
//				}
//				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
//				if (flag) {
//					if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
//						webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
//					}
//					logs.logging("INFO", "Header Saved Succesfully for Employee Details Migration");
//				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to Post uploaded template in Employee Details Migration
	 * 
	 * @author Sakthi-GWL
	 */
	public void postUploadedTemplateInEmployeeDetailsMigration() {
		webDB.waitUntilElementToNotBeStale(EmployeeMasterLocators.EmpDetailsMigrationPostBtn, ElementType.Xpath);
		flag = webDB.waitForElement(EmployeeMasterLocators.EmpDetailsMigrationPostBtn, ElementType.Xpath);
		if (flag) {
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			webDB.waitUntilElementToNotBeStale(EmployeeMasterLocators.EmpDetailsMigrationPostBtn, ElementType.Xpath);
			webDB.waitUntilElementToBeClickable(EmployeeMasterLocators.EmpDetailsMigrationPostBtn, ElementType.Xpath);
			webDB.moveToElement(EmployeeMasterLocators.EmpDetailsMigrationPostBtn, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.EmpDetailsMigrationPostBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Employee Details Migration uploaded template Posted");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used to enter probation start and end date
	 * 
	 * @author Sakthi-GWL
	 * @param duration
	 */
	public void enterProbationFromAndToDateWithDayDuration(String duration) {
		String fromDate = VariableHandler.getEmpDOJ();
		String toDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date = null;
		try {
			date = sdf.parse(fromDate);

		} catch (Exception e) {
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, Integer.parseInt(duration));
		toDate = sdf.format(cal.getTime());
		logs.logging("INFO", "Employee Joined date: " + fromDate);
		logs.logging("INFO", "for Probation Duration: " + duration + ", End date calculated: " + toDate);
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_ProbationDateFrom, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_ProbationDateFrom, ElementType.Xpath);
			flag = webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_ProbationDateFrom, fromDate,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Probation Start Date Entered: " + fromDate);
				webDB.clickAnElement(EmployeeMasterLocators.AddEmp_ProbationDurationInput, ElementType.Xpath);
				webDB.waitForElement(EmployeeMasterLocators.AddEmp_ProbationDateTo, ElementType.Xpath);
				webDB.moveToElement(EmployeeMasterLocators.AddEmp_ProbationDateTo, ElementType.Xpath);
				String ValueDisplayed = webDB.getAttributeFromElement(EmployeeMasterLocators.AddEmp_ProbationDateTo,
						ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
				logs.logging("INFO", "Displayed toDate in Probation: " + ValueDisplayed);
				flag = toDate.equalsIgnoreCase(ValueDisplayed);
				if (flag) {
					logs.logging("INFO", "Probation End Date Verified");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to enter probation details in Employee Master
	 * 
	 * @author Sakthi-GWL
	 * @param duration
	 * @param type
	 */
	public void enterProbationDetailsAndSaveHeader(String duration, String type) {
		commonFunc.verifyAddTableSpecs(EmployeeMasterLocators.AddEmp_ProbationDurationInput,
				EmployeeMasterLocators.AddEmp_ProbationDurationInput, duration);
		logs.logging("INFO", "Duration entered in Probation: " + duration);
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_ProbationSelect, ElementType.Xpath);
		if (flag) {
			flag = webDB.selectDropDownByText(EmployeeMasterLocators.AddEmp_ProbationSelect, type, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Probation Type Selected: " + type);
				enterProbationFromAndToDateWithDayDuration(duration);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				if (webDB.verifyIsElementDisplayed(EmployeeMasterLocators.AddEmp_AfterCreationSaveIcon2,
						ElementType.Xpath)) {
					webDB.scrollToAnElement(EmployeeMasterLocators.AddEmp_AfterCreationSaveIcon2, ElementType.Xpath);
					webDB.clickAnElement(EmployeeMasterLocators.AddEmp_AfterCreationSaveIcon2, ElementType.Xpath);
				} else if (webDB.verifyIsElementDisplayed(EmployeeMasterLocators.AddEmp_AfterCreationSaveIcon,
						ElementType.Xpath)) {
					webDB.scrollToAnElement(EmployeeMasterLocators.AddEmp_AfterCreationSaveIcon, ElementType.Xpath);
					webDB.clickAnElement(EmployeeMasterLocators.AddEmp_AfterCreationSaveIcon, ElementType.Xpath);
				} else if (webDB.verifyIsElementDisplayed(EmployeeMasterLocators.AddEmp_SaveButton,
						ElementType.Xpath)) {
					webDB.scrollToAnElement(EmployeeMasterLocators.AddEmp_SaveButton, ElementType.Xpath);
					webDB.clickAnElement(EmployeeMasterLocators.AddEmp_SaveButton, ElementType.Xpath);
				}
				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				if (flag) {
					if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
					logs.logging("INFO", "Save Icon Clicked after Probation entered");
					commonFunc.checkRefreshCellsDisappear();
					String text = webDB.getTextFromElement(EmployeeMasterLocators.AddEmp_DisplayEmpID,
							ElementType.Xpath);
					text = text.replace("Employee : ", " ").trim();
					VariableHandler.setEmpID(text);
					logs.logging("INFO", "Auto Generated Emp ID: " + VariableHandler.getEmpID());
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click probation complete and verify probation completed
	 * status in employee history
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickProbationCompleteAndCheckProbationStatus() {
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.checkSuccessToastMessageDisappear();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_ProbationCompleteBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_ProbationCompleteBtn, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_ProbationCompleteBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(2000);
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			logs.logging("INFO", "Probation Completed Button Clicked");
			flag = webDB.waitForElement(EmployeeMasterLocators.EmployeeHistoryBtn, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(EmployeeMasterLocators.EmployeeHistoryBtn, ElementType.Xpath);
				webDB.clickAnElement(EmployeeMasterLocators.EmployeeHistoryBtn, ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeMasterLocators.EmployeeHistoryIframe, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Employee History Button Clicked");
					webDB.switchToFrame(EmployeeMasterLocators.EmployeeHistoryIframe, ElementType.Xpath);
					if (flag) {
						flag = webDB.waitForElement(EmployeeMasterLocators.ProbationStatusVerificationTableData,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Probation Status Table Data Displayed");
							String DisplayStatus = webDB.getTextFromElement(
									EmployeeMasterLocators.ProbationStatusVerificationTableData, ElementType.Xpath);
							logs.logging("INFO", "Displayed Probation Status: " + DisplayStatus);
							flag = DisplayStatus.equalsIgnoreCase("Completed");
							if (flag) {
								logs.logging("INFO", "Probation Status Verified");
								webDB.switchToDefaultContent();
								webDB.clickAnElement(EmployeeMasterLocators.EmpHistIframeCloseIcon, ElementType.Xpath);
								flag = webDB.isElementNotDisplayed(EmployeeMasterLocators.EmployeeHistoryIframe,
										ElementType.Xpath);
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to store CL balance in Leave Details
	 * 
	 * @author Sakthi-GWL
	 */
	public void checkCLBalanceInLeaveDetailsAndStore() {
		flag = webDB.waitForElement(EmployeeMasterLocators.BalanceLeaveDetailsTable, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.BalanceLeaveDetailsTable, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.CLBalanceLeaveDetailsTableData, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(EmployeeMasterLocators.CLBalanceLeaveDetailsTableData, ElementType.Xpath);
				String Balance = webDB.getTextFromElement(EmployeeMasterLocators.CLBalanceLeaveDetailsTableData,
						ElementType.Xpath);
				VariableHandler.setCasualLeaveBalance(Balance);
				logs.logging("INFO", "Displayed Casual Leave Balance: " + Balance);
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to verify CL balance added or not in Leave Details
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyCLBalanceAddedInLeaveDetails(String leaveCount) {
		logs.logging("INFO", "Added Casual Leave: " + leaveCount);
		logs.logging("INFO", "Previous Casual Leave Count: " + VariableHandler.getCasualLeaveBalance());
		flag = webDB.waitForElement(EmployeeMasterLocators.BalanceLeaveDetailsTable, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.BalanceLeaveDetailsTable, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.CLBalanceLeaveDetailsTableData, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(EmployeeMasterLocators.CLBalanceLeaveDetailsTableData, ElementType.Xpath);
				String Balance = webDB.getTextFromElement(EmployeeMasterLocators.CLBalanceLeaveDetailsTableData,
						ElementType.Xpath);
				logs.logging("INFO", "Displayed Casual Leave Balance: " + Balance);
				flag = Integer.parseInt(Balance) == (Integer.parseInt(VariableHandler.getCasualLeaveBalance())
						+ Integer.parseInt(leaveCount));
				if (flag) {
					logs.logging("INFO", "Verified Balance Added in Employee Master");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This method used to click find Search Button
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickFindSearchButton() {
		flag = webDB.waitForElement(EmployeeMasterLocators.FindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.FindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(EmployeeMasterLocators.FindSearchBar, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to find Employee Master using Zone
	 * 
	 * @author Sakthi-GWL
	 * @param Zone
	 */
	public void findWithZone(String Zone) {
		commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.FindZoneLovBtn, Zone);
		clickFindSearchButton();
		commonFunc.checkRefreshCellsDisappear();
		List<WebElement> elements = webDB.getListOfElements(EmployeeMasterLocators.FindZoneResultTable(),
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().contains(Zone)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Zone: " + Zone);
	}

	/**
	 * This method used to find Employee Master using Category
	 * 
	 * @author Sakthi-GWL
	 * @param Category
	 */
	public void findWithCategory(String Category) {
		commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.FindCategoryLovBtn, Category);
		clickFindSearchButton();
		commonFunc.checkRefreshCellsDisappear();
		List<WebElement> elements = webDB.getListOfElements(EmployeeMasterLocators.FindCategoryResultTable(),
				ElementType.Xpath);
		a: for (WebElement webElement : elements) {
			if (webElement.getText().contains(Category)) {
				flag = true;
			} else {
				flag = false;
				break a;
			}
		}
		Assert.assertTrue(flag);
		logs.logging("INFO", "Verified Find Search Functions with Category: " + Category);
	}

	/**
	 * This method used to find Employee Master using Designation
	 * 
	 * @author Sakthi-GWL
	 * @param Designation
	 */
	public void findWithDesignation(String Designation) {
		commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.FindDesignationLovBtn, Designation);
		clickFindSearchButton();
		commonFunc.checkRefreshCellsDisappear();
		List<WebElement> elements = webDB.getListOfElements(EmployeeMasterLocators.FindDesignationResultTable(),
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
		logs.logging("INFO", "Verified Find Search Functions with Designation: " + Designation);
	}

	/**
	 * This method used to find Employee Master using Department
	 * 
	 * @author Sakthi-GWL
	 * @param Department
	 */
	public void findWithDepartment(String Department) {
		commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.FindDepartmentLovBtn, Department);
		clickFindSearchButton();
		commonFunc.checkRefreshCellsDisappear();
		List<WebElement> elements = webDB.getListOfElements(EmployeeMasterLocators.FindDepartmentResultTable(),
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
		logs.logging("INFO", "Verified Find Search Functions with Department: " + Department);
	}

	/**
	 * This method used to find Employee Master using Unit
	 * 
	 * @author Sakthi-GWL
	 * @param Unit
	 */
	public void findWithUnit(String Unit) {
		commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.FindUnitLovBtn, Unit);
		clickFindSearchButton();
		commonFunc.checkRefreshCellsDisappear();
		List<WebElement> elements = webDB.getListOfElements(EmployeeMasterLocators.FindUnitResultTable(),
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
		logs.logging("INFO", "Verified Find Search Functions with Unit: " + Unit);
	}

	/**
	 * This method used to delete SPL Allowance in Salary Details
	 * 
	 * @author Sakthi-GWL
	 */
	public void verifyDeleteSPLAllowanceInSalaryDetails() {
		clickSalarySideNavBar();
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AllowanceTableTotalAmountData(), ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AllowanceSPLDeleteIcon, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AllowanceSPLDeleteIcon, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.ViewProf_ReverseOkBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Delete Icon Clicked and Confirm Delete Displayed");
				webDB.clickAnElement(EmployeeMasterLocators.ViewProf_ReverseOkBtn, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Allowance SPL Deleted Successfully");
					if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

}
