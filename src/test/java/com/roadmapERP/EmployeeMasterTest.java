package com.roadmapERP;

import java.lang.reflect.Method;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 5, 2025
 */
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.payroll.EmployeeAdjustmentFunctions;
import commonfunctions.payroll.EmployeeMasterFunctions;
import utils.ReportLoger;

public class EmployeeMasterTest extends RoadMapERPTest {
	boolean flag = false;
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static EmployeeMasterFunctions empMasterFunc = new EmployeeMasterFunctions();
	private static EmployeeAdjustmentFunctions empAdjFunc = new EmployeeAdjustmentFunctions();
	private static ReportLoger logs = new ReportLoger();
	private static int previousStatus = ITestResult.STARTED;
	private static String GroupNames = null;

	/**
	 * This method used to login while start of the test class
	 * 
	 * @author Sakthi-GWL
	 */
	@BeforeMethod(alwaysRun = true)
	private void VerifyLogin(ITestResult result) {
		String[] groups = result.getMethod().getGroups();
		for (String string : groups) {
			GroupNames = string;
		}
		if (VariableHandler.getSessionID() == null || previousStatus == ITestResult.STARTED) {
			commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
					"HariUsername");
		} else if (VariableHandler.getSessionID() == null || VariableHandler.getSignInUserName() == null
				|| !VariableHandler.getSignInUserName().equals("HARIKRISHNA")) {
			if (!VariableHandler.getSignInUserName().equals("HARIKRISHNA")) {
				commonFunc.enterUrl();
			}
			commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
					"HariUsername");
		} else if (previousStatus == ITestResult.SUCCESS && GroupNames.contains("EmployeeMaster")) {
			logs.logging("INFO", "Current Username: " + VariableHandler.getSignInUserName());
		} else {
			logs.logging("INFO", "Current Username: " + VariableHandler.getSignInUserName());
			commonFunc.navigateToHomePage();
		}
	}

	/**
	 * To Check the Employee Creation with Particulars details
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 1, groups = {
			"EmployeeMaster" }, description = "2 To Check the Employee Creation with Particulars details")
	public void VerifyEmployeeCreationParticularsDetails() {
		flag = empMasterFunc.verifyEmployeeCreationParticularsDetails();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check the Employee Creation with Qualification / Skills details
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 2, groups = {
			"EmployeeMaster" }, description = "3 To Check the Employee Creation with Qualification / Skills details")
	public void VerifyEmployeeCreationQualificationSkillDetails() {
		flag = empMasterFunc.verifyEmployeeCreationQualificationSkillDetails();
		Assert.assertTrue(flag);
	}

	/**
	 * To check Employee Creation with Dependent details
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 3, groups = {
			"EmployeeMaster" }, description = "7 To check Employee Creation with Dependent details")
	public void VerifyCheckEmployeeCreationWithDependentDetails() {
		flag = empMasterFunc.verifyCheckEmployeeCreationDependentDetails();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check the Employee creation with salary details With Allowance Values
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 4, groups = {
			"EmployeeMaster" }, description = "4 To Check the Employee creation with salary details With Allowance Values")
	public void VerifyEmployeeCreationSalaryDetailsAllowanceValues() {
		flag = empMasterFunc.verifyEmployeeCreationSalaryDetailsAllowanceValues();
		Assert.assertTrue(flag);
	}

	/**
	 * To check Employee creation with salary details With Adjustment Values
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 5, groups = {
			"EmployeeMaster" }, description = "5 To check Employee creation with salary details With Adjustment Values")
	public void VerifyEmployeeCreationSalaryDetailsAdjustmentValues() {
		flag = empMasterFunc.verifyEmployeeCreationSalaryDetailsAdjustmentValues();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Employee Creation with Leave Details
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 6, groups = { "EmployeeMaster" }, description = "6 To Check Employee Creation with Leave Details")
	public void VerifyEmployeeCreationLeaveDetails() {
		flag = empMasterFunc.verifyEmployeeCreationLeaveDetails();
		Assert.assertTrue(flag);
	}

	/**
	 * To check profile image and sign upload image and attachment
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 7, groups = {
			"EmployeeMaster" }, description = "8 To check profile image and sign upload image and attachment")
	public void VerifyProfileImageAndSignUploadWithAttachments() {
		flag = empMasterFunc.verifyProfileImageAndSignUploadWithAttachments();
		Assert.assertTrue(flag);
	}

	/**
	 * To check Employee Creation with Additional info details
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 8, groups = {
			"EmployeeMaster" }, description = "9 To check Employee Creation with Additional info details")
	public void VerifyEmployeeCreationWithAdditionalInfoDetails() {
		flag = empMasterFunc.verifyEmployeeCreationAdditionalInfoDetails();
		Assert.assertTrue(flag);
	}

	/**
	 * To check Employee Creation With Update Employee Particular Details
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 9, groups = {
			"EmployeeMaster" }, description = "11 To check Employee Creation With Update Employee Particular Details")
	public void VerifyUpdateEmployeeParticularDetails() {
		flag = empMasterFunc.verifyUpdateEmployeeParticularDetails();
		Assert.assertTrue(flag);
	}

	/**
	 * To check Employee Creation With Update Address Details
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 10, groups = {
			"EmployeeMaster" }, description = "12 To check Employee Creation With Update Address Details")
	public void VerifyEmployeeCreationUpdateAddressDetails() {
		flag = empMasterFunc.verifyEmployeeCreationUpdateAddressDetails();
		Assert.assertTrue(flag);
	}

	/**
	 * To check Employee Creation With Update Dependent Details
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 11, groups = {
			"EmployeeMaster" }, description = "13 To check Employee Creation With Update Dependent Details")
	public void VerifyEmployeeCreationUpdateDependentDetails() {
		flag = empMasterFunc.verifyEmployeeCreationUpdateDependentDetails();
		Assert.assertTrue(flag);
	}

	/**
	 * To check Employee Creation With Update Contact Details
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 12, groups = {
			"EmployeeMaster" }, description = "14 To check Employee Creation With Update Contact Details")
	public void VerifyEmployeeCreationUpdateContactDetails() {
		flag = empMasterFunc.verifyEmployeeCreationUpdateContactDetails();
		Assert.assertTrue(flag);
	}

	/**
	 * To check Employee Creation With Update Qualification Details
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 13, groups = {
			"EmployeeMaster" }, description = "15 To check Employee Creation With Update Qualification Details")
	public void VerifyEmployeeCreationUpdateQualificationDetails() {
		flag = empMasterFunc.verifyEmployeeCreationUpdateQualificationDetails();
		Assert.assertTrue(flag);
	}

	/**
	 * To check Employee Creation With Update Qualification Details
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 14, groups = {
			"EmployeeMaster" }, description = "16 To check Employee Creation With Update Skills Details")
	public void VerifyEmployeeCreationUpdateSkillsDetails() {
		flag = empMasterFunc.verifyEmployeeCreationUpdateSkillsDetails();
		Assert.assertTrue(flag);
	}

	/**
	 * To check the Employee Creation with Employee History data
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 15, groups = {
			"EmployeeMaster" }, description = "10 To check the Employee Creation with Employee History data")
	public void VerifyEmployeeCreationEmployeeHistory() {
		flag = empMasterFunc.verifyEmployeeCreationEmployeeHistory();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Employee profile Reverse before the Payroll Process
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 16, groups = {
			"EmployeeMaster" }, description = "17 To Check Employee profile Reverse before the Payroll Process")
	public void VerifyEmployeeCreationEmployeeProfileReverse() {
		flag = empMasterFunc.verifyEmployeeCreationEmployeeProfileReverse();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Employee Migration With Templates
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 17, groups = { "EmployeeMaster" }, description = "18 To Check Employee Migration With Templates")
	public void VerifyEmployeeMigrationWithTemplates() {
		flag = empMasterFunc.verifyEmployeeMigrationWithTemplates();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Employee Details Migration With Templates
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 18, groups = {
			"EmployeeMaster" }, description = "19 To Check Employee Details Migration With Templates")
	public void VerifyEmployeeDetailsMigrationWithTemplates() {
		flag = empMasterFunc.verifyEmployeeDetailsMigrationWithTemplates();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Employee Creation based on Return to approval record
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 19, groups = {
			"EmployeeMaster" }, description = "20 To Check Employee Creation based on Return to approval record")
	public void VerifyEmployeeCreationForwardApproval() {
		flag = empMasterFunc.verifyEmployeeCreationForwardApproval();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Employee Creation based on Return to approval record
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 20, groups = {
			"EmployeeMaster" }, description = "21 To Check Employee Creation based on Return to approval record")
	public void VerifyEmployeeCreationReturnApproval() {
		flag = empMasterFunc.verifyEmployeeCreationReturnApproval();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check The Employee Creation With Probation Complete
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 21, groups = {
			"EmployeeMaster" }, description = "22 To Check The Employee Creation With Probation Complete")
	public void VerifyEmployeeCreationProbationComplete() {
		flag = empMasterFunc.verifyEmployeeCreationProbationComplete();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check the Employee Creation Find page with all line wise record
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 22, groups = {
			"EmployeeMaster" }, description = "22 To Check the Employee Creation Find page with all line wise record")
	public void VerifyEmployeeCreationFindWithLineWiseRecord() {
		flag = empMasterFunc.verifyEmployeeCreationFindWithLineWiseRecord();
		Assert.assertTrue(flag);
	}

	// -----------Employee Adjustment Test cases-----------

	/**
	 * To CheckEmployee Adjustment Find page with all line wise record
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 23, groups = {
			"Employee Adjustment" }, description = "69 To CheckEmployee Adjustment Find page with all line wise record")
	public void VerifyEmployeeAdjustmentFindWithLineWiseRecord() {
		flag = empAdjFunc.verifyEmployeeAdjustmentFindWithLineWiseRecord();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Adjustment based on different elements
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 24, groups = {
			"Employee Adjustment" }, description = "70 To Check Adjustment based on different elements")
	public void VerifyEmployeeAdjustmentBasedOnDifferentElements() {
		flag = empAdjFunc.verifyEmpAdjustmentBasedOnAllAndReportFetchValue();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Adjustment based on End option
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 25, groups = { "Employee Adjustment" }, description = "71 To Check Adjustment based on End option")
	public void VerifyEmployeeAdjustmentBasedOnEndOption() {
		if (previousStatus != ITestResult.SUCCESS) {
			flag = empAdjFunc.verifyEmpAdjustmentBasedOnAllAndReportFetchValue();
		} else {
			logs.logging("INFO", "This test covered in 'VerifyEmployeeAdjustmentBasedOnDifferentElements'  Test");
			flag = true;
		}
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Adjustment based on Delete option
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 26, groups = {
			"Employee Adjustment" }, description = "72 To Check Adjustment based on Delete option")
	public void VerifyEmployeeAdjustmentBasedOnDeleteOption() {
		logs.logging("INFO", "This test covered in 'VerifyEmployeeAdjustmentBasedOnDifferentElements'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (VerifyEmployeeAdjustmentBasedOnDifferentElements) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check Adjustment based on Hold option
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 27, groups = {
			"Employee Adjustment" }, description = "73 To Check Adjustment based on Hold option")
	public void VerifyEmployeeAdjustmentBasedOnHoldOption() {
		logs.logging("INFO", "This test covered in 'VerifyEmployeeAdjustmentBasedOnDifferentElements'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (VerifyEmployeeAdjustmentBasedOnDifferentElements) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check Employee Adjustment report fetches values correctly
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 28, groups = {
			"Employee Adjustment" }, description = "74 To Check Employee Adjustment report fetches values correctly")
	public void VerifyEmployeeAdjustmentReportFetchesValues() {
		logs.logging("INFO", "This test covered in 'VerifyEmployeeAdjustmentBasedOnDifferentElements'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (VerifyEmployeeAdjustmentBasedOnDifferentElements) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check Employee Adjustment Migration With Templates
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 29, groups = {
			"Employee Adjustment" }, description = "75 To Check Employee Adjustment Migration With Templates")
	public void VerifyEmployeeAdjustmentMigrationWithTemplates() {
		flag = empAdjFunc.verifyEmployeeAdjustmentMigrationWithTemplates();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Employee Allowance Migration With Templates
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 30, groups = {
			"Employee Adjustment" }, description = "76 To Check Employee Allowance Migration With Templates")
	public void VerifyEmployeeAllowanceMigrationWithTemplates() {
		flag = empAdjFunc.verifyEmployeeAllowanceMigrationWithTemplates();
		Assert.assertTrue(flag);
	}

	/**
	 * Logout The Portal If Testcases Failed
	 * 
	 * @author Sakthi-GWL
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result, Method method) {
		previousStatus = result.getStatus();
		commonFunc.enterAuthorNamesInExtentReport(method);
		if (previousStatus == ITestResult.FAILURE || previousStatus == ITestResult.SKIP) {
			VariableHandler.setAddEmployeDefaultExecution(false);
			commonFunc.verifyAfterMethod();
		}
	}

}
