package commonfunctions.payroll;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 5, 2025
 */
import java.io.File;
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
			VariableHandler.setEmpFirstName(commonFunc.getDataFromTestDataSheet("Emp_FName"));
			VariableHandler.setEmpLastName(commonFunc.getDataFromTestDataSheet("Emp_LName"));
			VariableHandler.setEmpSalution(commonFunc.getDataFromTestDataSheet("Emp_Salution"));
			VariableHandler.setEmpGender(commonFunc.getDataFromTestDataSheet("Emp_MaleGender"));
			VariableHandler.setEmpDOB(commonFunc.getDataFromTestDataSheet("Emp_DOB"));
			VariableHandler.setEmpDOJ(commonFunc.getDataFromTestDataSheet("Emp_DOJ"));
			VariableHandler.setEmpShift(commonFunc.getDataFromTestDataSheet("Emp_GeneralShift"));
			VariableHandler.setEmpCalendar(commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"));
			VariableHandler.setEmpZone(commonFunc.getDataFromTestDataSheet("Emp_GeneralZone"));
			VariableHandler.setEmpCompanyCategory(commonFunc.getDataFromTestDataSheet("Emp_SalesCompanyCategory"));
			VariableHandler.setEmpAccountCategory(commonFunc.getDataFromTestDataSheet("Emp_SalaryAcc-AccountCategory"));
			VariableHandler.setEmpPayrollGroup(commonFunc.getDataFromTestDataSheet("Emp_CleanerPayrollGroup"));
			VariableHandler.setEmpNoticePeriod(commonFunc.getDataFromTestDataSheet("Emp_NoticePeriod-30"));
			// Test Step Starts
			commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
					System.getProperty("HRMSelfApprovePassword"), "HRMSelfApproveUser");
			commonFunc.navigateEmployeeMasterScreenInPayroll();
			clickAddIconAddEmployee();
			clickEmployeeMasterSideNavBar();
			selectSalutionEnterFirstAndLastName(VariableHandler.getEmpSalution(), VariableHandler.getEmpFirstName(),
					VariableHandler.getEmpLastName());
			selectGenderEnterDOBAndDOJ(VariableHandler.getEmpGender(), VariableHandler.getEmpDOB(),
					VariableHandler.getEmpDOJ());
			selectShiftEnterCalerdarAndZone(VariableHandler.getEmpShift(), VariableHandler.getEmpCalendar(),
					VariableHandler.getEmpZone());
			selectCompanyAndAccountCatecory(VariableHandler.getEmpCompanyCategory(),
					VariableHandler.getEmpAccountCategory());
			selectPayrollGroupAndEnterNoticePeriod(VariableHandler.getEmpPayrollGroup(),
					VariableHandler.getEmpNoticePeriod());
			clickSaveIconAndStoreEmpID();
			VariableHandler.setAddEmployeDefaultExecution(true);
			return true;
		} catch (Exception e) {
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
			VariableHandler.setEmpContactName(RandomStringUtils.randomAlphabetic(15));
			VariableHandler.setEmpContactRelationship(RandomStringUtils.randomAlphabetic(12));
			VariableHandler.setEmpContactMobileNo("31245678" + random.nextInt(99));
			VariableHandler.setEmpContactAddress(RandomStringUtils.randomAlphabetic(12));
			VariableHandler.setEmpContactCity(commonFunc.getDataFromTestDataSheet("Emp_City"));
			VariableHandler.setEmpQualification(commonFunc.getDataFromTestDataSheet("Emp_Qualification"));
			VariableHandler.setEmpQualBranchOfStudy(commonFunc.getDataFromTestDataSheet("Emp_BranchofStudy"));
			VariableHandler.setEmpQualInstitute(RandomStringUtils.randomAlphabetic(22));
			VariableHandler.setEmpQualTotalYears(String.valueOf(random.nextInt(4)));
			VariableHandler.setEmpQualYearOfPassing(commonFunc.getDataFromTestDataSheet("Emp_YearOfPassing"));
			VariableHandler.setEmpQualGradeClass(RandomStringUtils.randomAlphabetic(12));
			VariableHandler.setEmpQualAGG(RandomStringUtils.randomAlphabetic(12));
			VariableHandler.setEmpSkill(commonFunc.getDataFromTestDataSheet("Emp_Skill"));
			VariableHandler.setEmpSkillLevel(commonFunc.getDataFromTestDataSheet("Emp_Level"));
			VariableHandler.setEmpFirstName(commonFunc.getDataFromTestDataSheet("Emp_FName"));
			VariableHandler.setEmpLastName(commonFunc.getDataFromTestDataSheet("Emp_LName"));
			VariableHandler.setEmpSalution(commonFunc.getDataFromTestDataSheet("Emp_Salution"));
			VariableHandler.setEmpGender(commonFunc.getDataFromTestDataSheet("Emp_MaleGender"));
			VariableHandler.setEmpDOB(commonFunc.getDataFromTestDataSheet("Emp_DOB"));
			VariableHandler.setEmpDOJ(commonFunc.getDataFromTestDataSheet("Emp_DOJ"));
			VariableHandler.setEmpShift(commonFunc.getDataFromTestDataSheet("Emp_GeneralShift"));
			VariableHandler.setEmpCalendar(commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"));
			VariableHandler.setEmpZone(commonFunc.getDataFromTestDataSheet("Emp_GeneralZone"));
			VariableHandler.setEmpCompanyCategory(commonFunc.getDataFromTestDataSheet("Emp_SalesCompanyCategory"));
			VariableHandler.setEmpAccountCategory(commonFunc.getDataFromTestDataSheet("Emp_SalaryAcc-AccountCategory"));
			VariableHandler.setEmpPayrollGroup(commonFunc.getDataFromTestDataSheet("Emp_CleanerPayrollGroup"));
			VariableHandler.setEmpNoticePeriod(commonFunc.getDataFromTestDataSheet("Emp_NoticePeriod-30"));
			// Test Step Starts
			if (!VariableHandler.getAddEmployeDefaultExecution()) {
				commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
						System.getProperty("HRMSelfApprovePassword"), "HRMSelfApproveUser");
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(VariableHandler.getEmpSalution(), VariableHandler.getEmpFirstName(),
						VariableHandler.getEmpLastName());
				selectGenderEnterDOBAndDOJ(VariableHandler.getEmpGender(), VariableHandler.getEmpDOB(),
						VariableHandler.getEmpDOJ());
				selectShiftEnterCalerdarAndZone(VariableHandler.getEmpShift(), VariableHandler.getEmpCalendar(),
						VariableHandler.getEmpZone());
				selectCompanyAndAccountCatecory(VariableHandler.getEmpCompanyCategory(),
						VariableHandler.getEmpAccountCategory());
				selectPayrollGroupAndEnterNoticePeriod(VariableHandler.getEmpPayrollGroup(),
						VariableHandler.getEmpNoticePeriod());
				clickSaveIconAndStoreEmpID();
				VariableHandler.setAddEmployeDefaultExecution(true);
			}
			clickQualificationSkillSideNavBar();
			addNewContactDetailsInQualificationTab(VariableHandler.getEmpContactName(),
					VariableHandler.getEmpContactRelationship(), VariableHandler.getEmpContactMobileNo(),
					VariableHandler.getEmpContactAddress(), VariableHandler.getEmpContactCity(), true);
			addNewQualificationDetailsInQualificationTab(VariableHandler.getEmpQualification(),
					VariableHandler.getEmpQualBranchOfStudy(), VariableHandler.getEmpQualInstitute(),
					VariableHandler.getEmpQualTotalYears(), VariableHandler.getEmpQualYearOfPassing(),
					VariableHandler.getEmpQualGradeClass(), VariableHandler.getEmpQualAGG(), true);
			addNewSkillDetailsInQualificationTab(VariableHandler.getEmpSkill(), VariableHandler.getEmpSkillLevel(),
					true);
			return true;
		} catch (Exception e) {
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
			VariableHandler.setEmpSalaryDesignation(commonFunc.getDataFromTestDataSheet("Emp_SalaryDesignation"));
			VariableHandler.setEmpSalaryDepartment(commonFunc.getDataFromTestDataSheet("Emp_SalaryDepartment"));
			VariableHandler.setEmpSalaryGrade(commonFunc.getDataFromTestDataSheet("Emp_SalaryGrade"));
			VariableHandler.setEmpFirstName(commonFunc.getDataFromTestDataSheet("Emp_FName"));
			VariableHandler.setEmpLastName(commonFunc.getDataFromTestDataSheet("Emp_LName"));
			VariableHandler.setEmpSalution(commonFunc.getDataFromTestDataSheet("Emp_Salution"));
			VariableHandler.setEmpGender(commonFunc.getDataFromTestDataSheet("Emp_MaleGender"));
			VariableHandler.setEmpDOB(commonFunc.getDataFromTestDataSheet("Emp_DOB"));
			VariableHandler.setEmpDOJ(commonFunc.getDataFromTestDataSheet("Emp_DOJ"));
			VariableHandler.setEmpShift(commonFunc.getDataFromTestDataSheet("Emp_GeneralShift"));
			VariableHandler.setEmpCalendar(commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"));
			VariableHandler.setEmpZone(commonFunc.getDataFromTestDataSheet("Emp_GeneralZone"));
			VariableHandler.setEmpCompanyCategory(commonFunc.getDataFromTestDataSheet("Emp_SalesCompanyCategory"));
			VariableHandler.setEmpAccountCategory(commonFunc.getDataFromTestDataSheet("Emp_SalaryAcc-AccountCategory"));
			VariableHandler.setEmpPayrollGroup(commonFunc.getDataFromTestDataSheet("Emp_CleanerPayrollGroup"));
			VariableHandler.setEmpNoticePeriod(commonFunc.getDataFromTestDataSheet("Emp_NoticePeriod-30"));
			// Test Step Starts
			if (!VariableHandler.getAddEmployeDefaultExecution()) {
				commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
						System.getProperty("HRMSelfApprovePassword"), "HRMSelfApproveUser");
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(VariableHandler.getEmpSalution(), VariableHandler.getEmpFirstName(),
						VariableHandler.getEmpLastName());
				selectGenderEnterDOBAndDOJ(VariableHandler.getEmpGender(), VariableHandler.getEmpDOB(),
						VariableHandler.getEmpDOJ());
				selectShiftEnterCalerdarAndZone(VariableHandler.getEmpShift(), VariableHandler.getEmpCalendar(),
						VariableHandler.getEmpZone());
				selectCompanyAndAccountCatecory(VariableHandler.getEmpCompanyCategory(),
						VariableHandler.getEmpAccountCategory());
				selectPayrollGroupAndEnterNoticePeriod(VariableHandler.getEmpPayrollGroup(),
						VariableHandler.getEmpNoticePeriod());
				clickSaveIconAndStoreEmpID();
				VariableHandler.setAddEmployeDefaultExecution(true);
			}
			clickSalarySideNavBar();
			addDesignationDepartmentAndGradeInSalaryDetails(VariableHandler.getEmpSalaryDesignation(),
					VariableHandler.getEmpSalaryDepartment(), VariableHandler.getEmpSalaryGrade());
			clickAfterEmpCreationSaveIcon();
			clickLoadAllowanceButtonInSalaryDetails();
			fillAllowanceAmountsAndVerifyTotalInSalaryDetails(new String[] { "10000", "5000", "7000", "12344" });
			return true;
		} catch (Exception e) {
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
			VariableHandler.setEmpSalaryAdjmntElement(commonFunc.getDataFromTestDataSheet("Emp_AdjmntElement"));
			VariableHandler.setEmpSalaryAdjmntStartYear(commonFunc.getDataFromTestDataSheet("Emp_AdjmntStartYear"));
			VariableHandler.setEmpSalaryAdjmntPeriod(commonFunc.getDataFromTestDataSheet("Emp_AdjmntPeriod"));
			VariableHandler.setEmpSalaryAdjmntTotalPeriod(commonFunc.getDataFromTestDataSheet("Emp_AdjmntTotalPeriod"));
			VariableHandler.setEmpSalaryAdjmntPeriodAmnt(commonFunc.getDataFromTestDataSheet("Emp_AdjmntPeriodAmnt"));
			VariableHandler.setEmpFirstName(commonFunc.getDataFromTestDataSheet("Emp_FName"));
			VariableHandler.setEmpLastName(commonFunc.getDataFromTestDataSheet("Emp_LName"));
			VariableHandler.setEmpSalution(commonFunc.getDataFromTestDataSheet("Emp_Salution"));
			VariableHandler.setEmpGender(commonFunc.getDataFromTestDataSheet("Emp_MaleGender"));
			VariableHandler.setEmpDOB(commonFunc.getDataFromTestDataSheet("Emp_DOB"));
			VariableHandler.setEmpDOJ(commonFunc.getDataFromTestDataSheet("Emp_DOJ"));
			VariableHandler.setEmpShift(commonFunc.getDataFromTestDataSheet("Emp_GeneralShift"));
			VariableHandler.setEmpCalendar(commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"));
			VariableHandler.setEmpZone(commonFunc.getDataFromTestDataSheet("Emp_GeneralZone"));
			VariableHandler.setEmpCompanyCategory(commonFunc.getDataFromTestDataSheet("Emp_SalesCompanyCategory"));
			VariableHandler.setEmpAccountCategory(commonFunc.getDataFromTestDataSheet("Emp_SalaryAcc-AccountCategory"));
			VariableHandler.setEmpPayrollGroup(commonFunc.getDataFromTestDataSheet("Emp_CleanerPayrollGroup"));
			VariableHandler.setEmpNoticePeriod(commonFunc.getDataFromTestDataSheet("Emp_NoticePeriod-30"));
			// Test Step Starts
			if (!VariableHandler.getAddEmployeDefaultExecution()) {
				commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
						System.getProperty("HRMSelfApprovePassword"), "HRMSelfApproveUser");
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(VariableHandler.getEmpSalution(), VariableHandler.getEmpFirstName(),
						VariableHandler.getEmpLastName());
				selectGenderEnterDOBAndDOJ(VariableHandler.getEmpGender(), VariableHandler.getEmpDOB(),
						VariableHandler.getEmpDOJ());
				selectShiftEnterCalerdarAndZone(VariableHandler.getEmpShift(), VariableHandler.getEmpCalendar(),
						VariableHandler.getEmpZone());
				selectCompanyAndAccountCatecory(VariableHandler.getEmpCompanyCategory(),
						VariableHandler.getEmpAccountCategory());
				selectPayrollGroupAndEnterNoticePeriod(VariableHandler.getEmpPayrollGroup(),
						VariableHandler.getEmpNoticePeriod());
				clickSaveIconAndStoreEmpID();
				VariableHandler.setAddEmployeDefaultExecution(true);
			}
			clickSalarySideNavBar();
			clickAddButtonInAdjustmentSalaryDetails();
			selectAdjustmentSalaryElementStartYearAndPeriodDetails(VariableHandler.getEmpSalaryAdjmntElement(),
					VariableHandler.getEmpSalaryAdjmntStartYear(), VariableHandler.getEmpSalaryAdjmntPeriod());
			fillAdjustmentSalaryTotalPeriodAndPeriodAmount(VariableHandler.getEmpSalaryAdjmntTotalPeriod(),
					VariableHandler.getEmpSalaryAdjmntPeriodAmnt());
			clickSaveIconInSalaryAdjustment();
			clickActivateIconInLastAddedAdjustment();
			clickDeleteIconInLastAddedAdjustment();
			enablePFAdjustmentFlagsInSalaryDetails();
			enableESIAdjustmentFlagsInSalaryDetails();
			clickPostButtonAndVerifyEmployeePosted();
			return true;
		} catch (Exception e) {
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
			VariableHandler.setEmpLeaveElgblLeave(commonFunc.getDataFromTestDataSheet("LeaveElgblLeave"));
			VariableHandler.setEmpLeaveElgblDays(commonFunc.getDataFromTestDataSheet("LeaveElgblDays"));
			VariableHandler.setEmpLeaveElgblEvery(commonFunc.getDataFromTestDataSheet("EmpLeaveElgblEvery"));
			VariableHandler
					.setEmpLeaveElgblEligibileDays(commonFunc.getDataFromTestDataSheet("EmpLeaveElgblEligibileDays"));
			VariableHandler.setEmpLeaveElgblRoundOff(commonFunc.getDataFromTestDataSheet("EmpLeaveElgblRoundOff"));
			VariableHandler
					.setEmpLeaveElgblMaxCarryFreq(commonFunc.getDataFromTestDataSheet("EmpLeaveElgblMaxCarryFreq"));
			VariableHandler
					.setEmpLeaveElgblMaxCarryDay(commonFunc.getDataFromTestDataSheet("EmpLeaveElgblMaxCarryDay"));
			VariableHandler.setEmpLeaveElgblEffYear(commonFunc.getDataFromTestDataSheet("EmpLeaveElgblEffYear"));
			VariableHandler.setEmpLeaveElgblMaxDay(commonFunc.getDataFromTestDataSheet("EmpLeaveElgblMaxDay"));
			VariableHandler.setEmpLeaveElgblMinDay(commonFunc.getDataFromTestDataSheet("EmpLeaveElgblMinDay"));
			VariableHandler.setEmpFirstName(commonFunc.getDataFromTestDataSheet("Emp_FName"));
			VariableHandler.setEmpLastName(commonFunc.getDataFromTestDataSheet("Emp_LName"));
			VariableHandler.setEmpSalution(commonFunc.getDataFromTestDataSheet("Emp_Salution"));
			VariableHandler.setEmpGender(commonFunc.getDataFromTestDataSheet("Emp_MaleGender"));
			VariableHandler.setEmpDOB(commonFunc.getDataFromTestDataSheet("Emp_DOB"));
			VariableHandler.setEmpDOJ(commonFunc.getDataFromTestDataSheet("Emp_DOJ"));
			VariableHandler.setEmpShift(commonFunc.getDataFromTestDataSheet("Emp_GeneralShift"));
			VariableHandler.setEmpCalendar(commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"));
			VariableHandler.setEmpZone(commonFunc.getDataFromTestDataSheet("Emp_GeneralZone"));
			VariableHandler.setEmpCompanyCategory(commonFunc.getDataFromTestDataSheet("Emp_SalesCompanyCategory"));
			VariableHandler.setEmpAccountCategory(commonFunc.getDataFromTestDataSheet("Emp_SalaryAcc-AccountCategory"));
			VariableHandler.setEmpPayrollGroup(commonFunc.getDataFromTestDataSheet("Emp_CleanerPayrollGroup"));
			VariableHandler.setEmpNoticePeriod(commonFunc.getDataFromTestDataSheet("Emp_NoticePeriod-30"));
			VariableHandler.setEmpSalaryDesignation(commonFunc.getDataFromTestDataSheet("Emp_SalaryDesignation"));
			VariableHandler.setEmpSalaryDepartment(commonFunc.getDataFromTestDataSheet("Emp_SalaryDepartment"));
			VariableHandler.setEmpSalaryGrade(commonFunc.getDataFromTestDataSheet("Emp_SalaryGrade"));
			// Test Step Starts
			if (!VariableHandler.getAddEmployeDefaultExecution()) {
				commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
						System.getProperty("HRMSelfApprovePassword"), "HRMSelfApproveUser");
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(VariableHandler.getEmpSalution(), VariableHandler.getEmpFirstName(),
						VariableHandler.getEmpLastName());
				selectGenderEnterDOBAndDOJ(VariableHandler.getEmpGender(), VariableHandler.getEmpDOB(),
						VariableHandler.getEmpDOJ());
				selectShiftEnterCalerdarAndZone(VariableHandler.getEmpShift(), VariableHandler.getEmpCalendar(),
						VariableHandler.getEmpZone());
				selectCompanyAndAccountCatecory(VariableHandler.getEmpCompanyCategory(),
						VariableHandler.getEmpAccountCategory());
				selectPayrollGroupAndEnterNoticePeriod(VariableHandler.getEmpPayrollGroup(),
						VariableHandler.getEmpNoticePeriod());
				clickSaveIconAndStoreEmpID();
				clickSalarySideNavBar();
				addDesignationDepartmentAndGradeInSalaryDetails(VariableHandler.getEmpSalaryDesignation(),
						VariableHandler.getEmpSalaryDepartment(), VariableHandler.getEmpSalaryGrade());
				clickAfterEmpCreationSaveIcon();
				clickLoadAllowanceButtonInSalaryDetails();
				fillAllowanceAmountsAndVerifyTotalInSalaryDetails(new String[] { "10000", "5000", "7000", "12344" });
				enablePFAdjustmentFlagsInSalaryDetails();
				enableESIAdjustmentFlagsInSalaryDetails();
				clickPostButtonAndVerifyEmployeePosted();
				VariableHandler.setAddEmployeDefaultExecution(true);
			}
			commonPayrollFunc.openLeaveLoanSetupInNewTabAndStoreLeaveEntitlement();
			clickLeaveDetailsSideNavBar();
			validateUpdatedLeaveDetailsWithSetupScreenEntitlement();
			clickAddIconLeaveEligibilityInLeaveDetails();
			fillLeaveAndDaysInLeaveEligibility(VariableHandler.getEmpLeaveElgblLeave(),
					VariableHandler.getEmpLeaveElgblDays());
			fillEveryAndEligibleDaysInLeaveEligibility(VariableHandler.getEmpLeaveElgblEvery(),
					VariableHandler.getEmpLeaveElgblEligibileDays());
			fillRoundOffDaysAndMaxCarryFrequencyInLeaveEligibility(VariableHandler.getEmpLeaveElgblRoundOff(),
					VariableHandler.getEmpLeaveElgblMaxCarryFreq());
			fillMaxCarryDayAndEffectiveYearInLeaveEligibility(VariableHandler.getEmpLeaveElgblMaxCarryDay(),
					VariableHandler.getEmpLeaveElgblEffYear());
			fillMaxDaysAndMinDaysInLeaveEligibility(VariableHandler.getEmpLeaveElgblMaxDay(),
					VariableHandler.getEmpLeaveElgblMinDay());
			clickSaveIconInLeaveEligibility();
			return true;
		} catch (Exception e) {
			logs.logging("INFO", "Exception Occurs: " + e);
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
			VariableHandler.setEmpFirstName(commonFunc.getDataFromTestDataSheet("Emp_FName"));
			VariableHandler.setEmpLastName(commonFunc.getDataFromTestDataSheet("Emp_LName"));
			VariableHandler.setEmpSalution(commonFunc.getDataFromTestDataSheet("Emp_Salution"));
			VariableHandler.setEmpGender(commonFunc.getDataFromTestDataSheet("Emp_MaleGender"));
			VariableHandler.setEmpDOB(commonFunc.getDataFromTestDataSheet("Emp_DOB"));
			VariableHandler.setEmpDOJ(commonFunc.getDataFromTestDataSheet("Emp_DOJ"));
			VariableHandler.setEmpShift(commonFunc.getDataFromTestDataSheet("Emp_GeneralShift"));
			VariableHandler.setEmpCalendar(commonFunc.getDataFromTestDataSheet("Emp_1to31Calendar"));
			VariableHandler.setEmpZone(commonFunc.getDataFromTestDataSheet("Emp_GeneralZone"));
			VariableHandler.setEmpCompanyCategory(commonFunc.getDataFromTestDataSheet("Emp_SalesCompanyCategory"));
			VariableHandler.setEmpAccountCategory(commonFunc.getDataFromTestDataSheet("Emp_SalaryAcc-AccountCategory"));
			VariableHandler.setEmpPayrollGroup(commonFunc.getDataFromTestDataSheet("Emp_CleanerPayrollGroup"));
			VariableHandler.setEmpNoticePeriod(commonFunc.getDataFromTestDataSheet("Emp_NoticePeriod-30"));
			VariableHandler.setEmpSalaryDesignation(commonFunc.getDataFromTestDataSheet("Emp_SalaryDesignation"));
			VariableHandler.setEmpSalaryDepartment(commonFunc.getDataFromTestDataSheet("Emp_SalaryDepartment"));
			VariableHandler.setEmpSalaryGrade(commonFunc.getDataFromTestDataSheet("Emp_SalaryGrade"));
			// Test Step Starts
			if (!VariableHandler.getAddEmployeDefaultExecution()) {
				commonFunc.validCommonLogin(System.getProperty("HRMSelfApproveUser"),
						System.getProperty("HRMSelfApprovePassword"), "HRMSelfApproveUser");
				commonFunc.navigateEmployeeMasterScreenInPayroll();
				clickAddIconAddEmployee();
				clickEmployeeMasterSideNavBar();
				selectSalutionEnterFirstAndLastName(VariableHandler.getEmpSalution(), VariableHandler.getEmpFirstName(),
						VariableHandler.getEmpLastName());
				selectGenderEnterDOBAndDOJ(VariableHandler.getEmpGender(), VariableHandler.getEmpDOB(),
						VariableHandler.getEmpDOJ());
				selectShiftEnterCalerdarAndZone(VariableHandler.getEmpShift(), VariableHandler.getEmpCalendar(),
						VariableHandler.getEmpZone());
				selectCompanyAndAccountCatecory(VariableHandler.getEmpCompanyCategory(),
						VariableHandler.getEmpAccountCategory());
				selectPayrollGroupAndEnterNoticePeriod(VariableHandler.getEmpPayrollGroup(),
						VariableHandler.getEmpNoticePeriod());
				clickSaveIconAndStoreEmpID();
				clickSalarySideNavBar();
				addDesignationDepartmentAndGradeInSalaryDetails(VariableHandler.getEmpSalaryDesignation(),
						VariableHandler.getEmpSalaryDepartment(), VariableHandler.getEmpSalaryGrade());
				clickAfterEmpCreationSaveIcon();
				clickLoadAllowanceButtonInSalaryDetails();
				fillAllowanceAmountsAndVerifyTotalInSalaryDetails(new String[] { "10000", "5000", "7000", "12344" });
				clickPostButtonAndVerifyEmployeePosted();
				VariableHandler.setAddEmployeDefaultExecution(true);
			}
			uploadSignImageInAddEmployeeDetails("RoadMapImage.png");
			uploadProfileImageInAddEmployeeDetails("RoadMapImage.png");
			return true;
		} catch (Exception e) {
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
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_FirstName, ElementType.Xpath);
		if (flag) {
			webDB.selectDropDownByText(EmployeeMasterLocators.AddEmp_SelectSalution, SalutionValue, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_FirstName, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Salution Selected for the Employee: " + VariableHandler.getEmpSalution());
				webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_FirstName, FName, ElementType.Xpath);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LastName, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "First Name of the Employee Sent: " + VariableHandler.getEmpFirstName());
					flag = webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_LastName, LName, ElementType.Xpath);
					if (flag) {
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
				logs.logging("INFO", "Gender Selected for the Employee: " + VariableHandler.getEmpGender());
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_DOBInput, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(EmployeeMasterLocators.AddEmp_DOBInput, ElementType.Xpath);
					webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_DOBInput, DOB, ElementType.Xpath);
					webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LastName, ElementType.Xpath);
					flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_DOJInput, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Date of Birth of the Employee Sent: " + VariableHandler.getEmpDOB());
						flag = webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_DOJInput, DOJ,
								ElementType.Xpath);
						if (flag) {
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
			logs.logging("INFO", "Shift Selected for the Employee: " + VariableHandler.getEmpShift());
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
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AccountCategry, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_AccountCategry, AccountCategory);
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
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_NoticePeriod, ElementType.Xpath);
			if (flag) {
				flag = webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_NoticePeriod, NoticePeriod,
						ElementType.Xpath);
				if (flag) {
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
		commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_RelationshipData,
				EmployeeMasterLocators.AddEmp_RelationshipDataInput, Relationship);
		commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_MobileNoData,
				EmployeeMasterLocators.AddEmp_MobileNoDataInput, MobileNo);
		commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_AddressData,
				EmployeeMasterLocators.AddEmp_AddressDataInput, Address);
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_City, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_City, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_CityLovBtn, City);
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
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_BranchOfStudy, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(EmployeeMasterLocators.AddEmp_BranchOfStudy, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_BranchOfStudyLovBtn,
						branchOfStudy);
				commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_InstituteData,
						EmployeeMasterLocators.AddEmp_InstituteDataInput, Institute);
				commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_TotalYearsData,
						EmployeeMasterLocators.AddEmp_TotalYearsDataInput, Totalyears);
				commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_YearOfPassingData,
						EmployeeMasterLocators.AddEmp_YearOfPassingDataInput, YearofPassing);
				commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_GradeClassData,
						EmployeeMasterLocators.AddEmp_GradeClassDataInput, GradeClass);
				commonPayrollFunc.addDetailInTableWithoutLovButton(EmployeeMasterLocators.AddEmp_AggData,
						EmployeeMasterLocators.AddEmp_AggDataInput, Agg);
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
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LevelData, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LevelData, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_LevelDataLov, Level);
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
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_DepartmentLovBtn, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_DepartmentLovBtn, department);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_GradeLovBtn, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(EmployeeMasterLocators.AddEmp_GradeLovBtn, grade);
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
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_ESI_ElegibleInputBox, ElementType.Xpath);
		if (flag) {
			String value = webDB.getDomPropertyFromElement(EmployeeMasterLocators.AddEmp_ESI_ElegibleInputBox,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			if (value.equalsIgnoreCase("N")) {
				webDB.waitForElement(EmployeeMasterLocators.AddEmp_ESI_ElegibleLabel, ElementType.Xpath);
				webDB.clickAnElement(EmployeeMasterLocators.AddEmp_ESI_ElegibleLabel, ElementType.Xpath);
				logs.logging("INFO", "ESI flag Defaultly Disabled");
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(500);
				value = webDB.getDomPropertyFromElement(EmployeeMasterLocators.AddEmp_ESI_ElegibleInputBox,
						ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			}
			flag = value.equalsIgnoreCase("Y");
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
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_PF_ElegibleInputBox, ElementType.Xpath);
		if (flag) {
			String value = webDB.getDomPropertyFromElement(EmployeeMasterLocators.AddEmp_PF_ElegibleInputBox,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			if (value.equalsIgnoreCase("N")) {
				webDB.waitForElement(EmployeeMasterLocators.AddEmp_PF_ElegibleLabel, ElementType.Xpath);
				webDB.clickAnElement(EmployeeMasterLocators.AddEmp_PF_ElegibleLabel, ElementType.Xpath);
				logs.logging("INFO", "PF flag Defaultly Disabled");
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(500);
				value = webDB.getDomPropertyFromElement(EmployeeMasterLocators.AddEmp_PF_ElegibleInputBox,
						ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			}
			flag = value.equalsIgnoreCase("Y");
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
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_AdjustmentStartYearData, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.clickAnElement(EmployeeMasterLocators.AddEmp_AdjustmentStartYearData, ElementType.Xpath);
				flag = webDB.selectDropDownByText(EmployeeMasterLocators.AddEmp_AdjustmentStartYearSelect, StartYear,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Adjustment Start Year Selected Text: " + StartYear);
					flag = webDB.waitForClickElement(EmployeeMasterLocators.AddEmp_AdjustmentPeriodData,
							ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.moveToElement(EmployeeMasterLocators.AddEmp_AdjustmentPeriodData, ElementType.Xpath);
						webDB.clickAnElement(EmployeeMasterLocators.AddEmp_AdjustmentPeriodData, ElementType.Xpath);
						flag = webDB.selectDropDownByText(EmployeeMasterLocators.AddEmp_AdjustmentPeriodSelect, Period,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Adjustment Period Selected Text: " + Period);
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
		flag = webDB.waitForClickElement(EmployeeMasterLocators.AddEmp_AdjustmentTotalPeriodData, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_AdjustmentTotalPeriodData, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_AdjustmentTotalPeriodData, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			webDB.clearText(EmployeeMasterLocators.AddEmp_AdjustmentTotalPeriodDataInput, ElementType.Xpath);
			flag = webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_AdjustmentTotalPeriodDataInput, TotalPeriod,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Adjustment Total Period Sent: " + TotalPeriod);
				flag = webDB.waitForClickElement(EmployeeMasterLocators.AddEmp_AdjustmentPeriodAmountData,
						ElementType.Xpath);
				if (flag) {
					commonFunc.checkRefreshCellsDisappear();
					webDB.moveToElement(EmployeeMasterLocators.AddEmp_AdjustmentPeriodAmountData, ElementType.Xpath);
					webDB.clickAnElement(EmployeeMasterLocators.AddEmp_AdjustmentPeriodAmountData, ElementType.Xpath);
					webDB.clearTextField(EmployeeMasterLocators.AddEmp_AdjustmentPeriodAmountDataInput,
							ElementType.Xpath);
					flag = webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_AdjustmentPeriodAmountDataInput,
							PeriodAmount, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Adjustment Period Amount Sent: " + PeriodAmount);
					}
				}
			}
		}
		Assert.assertTrue(flag);
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
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityDescription, ElementType.Xpath);
			flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityDaysData, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityDaysData, ElementType.Xpath);
				webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityDaysInput, ElementType.Xpath);
				flag = webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityDaysInput, Days,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Days Entered in Leave Eligibility: " + Days);
				}
			}
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
				logs.logging("INFO", "Leave Eligibility Every Data Selected Text: " + Every);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityEligibleDaysData,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityEligibleDaysData,
							ElementType.Xpath);
					webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityEligibleDaysInput,
							ElementType.Xpath);
					flag = webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityEligibleDaysInput,
							EligibleDays, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Eligible Days Entered in Leave Eligibility: " + EligibleDays);
					}
				}
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
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMaxCarryDayData, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMaxCarryDayData, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMaxCarryDayData, ElementType.Xpath);
			webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMaxCarryDayInput, ElementType.Xpath);
			flag = webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMaxCarryDayInput,
					MaxCarryDay, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Max Carry Day Entered in Leave Eligibility: " + MaxCarryDay);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityEffYearData,
						ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityEffYearData, ElementType.Xpath);
					webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityEffYearData, ElementType.Xpath);
					webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityEffYearSelect,
							ElementType.Xpath);
					flag = webDB.selectDropDownByText(EmployeeMasterLocators.AddEmp_LeaveEligibilityEffYearSelect,
							EffYear, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Leave Eligibility Effective Year Selected Text: " + EffYear);
					}
				}
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
		flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMaxDaysData, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMaxDaysData, ElementType.Xpath);
			webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMaxDaysData, ElementType.Xpath);
			webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMaxDaysInput, ElementType.Xpath);
			flag = webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMaxDaysInput, MaxDay,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Leave Eligibility Max Day Enetred Text: " + MaxDay);
				flag = webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMinDaysData,
						ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMinDaysData, ElementType.Xpath);
					webDB.clickAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMinDaysData, ElementType.Xpath);
					webDB.waitForElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMinDaysInput, ElementType.Xpath);
					flag = webDB.sendTextToAnElement(EmployeeMasterLocators.AddEmp_LeaveEligibilityMinDaysInput, MinDay,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Leave Eligibility Min Day Enetred Text: " + MinDay);
					}
				}
			}
		}
		Assert.assertTrue(flag);
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
	
}
