package com.roadmapERP;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 5, 2025
 */
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.payroll.EmployeeMasterFunctions;
import utils.WebDriverBase;

public class EmployeeMasterTest {
	private static boolean flag = false;
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static WebDriverBase webDB = new WebDriverBase();
	private static EmployeeMasterFunctions empMasterFunc = new EmployeeMasterFunctions();

	/**
	 * To Check the Employee Creation with Particulars details
	 * 
	 * @author Sakthi-GWL
	 */
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
	@Test(priority = 2, groups = {
			"EmployeeMaster" }, description = "3 To Check the Employee Creation with Qualification / Skills details")
	public void VerifyEmployeeCreationQualificationSkillDetails() {
		flag = empMasterFunc.verifyEmployeeCreationQualificationSkillDetails();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check the Employee creation with salary details With Allowance Values
	 * 
	 * @author Sakthi-GWL
	 */
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
	@Test(priority = 8, groups = {
			"EmployeeMaster" }, description = "9 To check Employee Creation with Additional info details")
	public void VerifyEmployeeCreationWithAdditionalInfoDetails() {
		flag = empMasterFunc.verifyEmployeeCreationAdditionalInfoDetails();
		Assert.assertTrue(flag);
	}

	/**
	 * To check Employee Creation with Dependent details
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 3, groups = {
			"EmployeeMaster" }, description = "7 To check Employee Creation with Dependent details")
	public void VerifyCheckEmployeeCreationWithDependentDetails() {
		flag = empMasterFunc.verifyCheckEmployeeCreationDependentDetails();
		Assert.assertTrue(flag);
	}

	/**
	 * To check Employee Creation With Update Employee Particular Details
	 * 
	 * @author Sakthi-GWL
	 */
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
	@Test(priority = 10, groups = {
			"EmployeeMaster" }, description = "12 To check Employee Creation With Update Address Details")
	public void VerifyEmployeeCreationUpdateAddressDetails() {
		flag = empMasterFunc.verifyEmployeeCreationUpdateAddressDetails();
		Assert.assertTrue(flag);
	}

	/**
	 * Logout The Portal If Testcases Failed
	 * 
	 * @author Sakthi-GWL
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			VariableHandler.setAddEmployeDefaultExecution(false);
			commonFunc.verifyAfterMethod();
		}
	}

}
