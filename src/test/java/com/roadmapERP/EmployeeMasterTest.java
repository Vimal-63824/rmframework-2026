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
	boolean flag = false;
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static WebDriverBase webDB = new WebDriverBase();
	private static EmployeeMasterFunctions empMasterFunc = new EmployeeMasterFunctions();

	@Test(priority = 1, groups = {
			"EmployeeMaster" }, description = "2 To Check the Employee Creation with Particulars details")
	public void VerifyEmployeeCreationParticularsDetails() {
		flag = empMasterFunc.verifyEmployeeCreationParticularsDetails();
		Assert.assertTrue(flag);
	}

	@Test(priority = 2, groups = {
			"EmployeeMaster" }, description = "3 To Check the Employee Creation with Qualification / Skills details")
	public void VerifyEmployeeCreationQualificationSkillDetails() {
		flag = empMasterFunc.verifyEmployeeCreationQualificationSkillDetails();
		Assert.assertTrue(flag);
	}

	@Test(priority = 3, groups = {
			"EmployeeMaster" }, description = "4 To Check the Employee creation with salary details With Allowance Values")
	public void VerifyEmployeeCreationSalaryDetailsAllowanceValues() {
		flag = empMasterFunc.verifyEmployeeCreationSalaryDetailsAllowanceValues();
		Assert.assertTrue(flag);
	}

	@Test(priority = 4, groups = {
			"EmployeeMaster" }, description = "5 To check Employee creation with salary details With Adjustment Values")
	public void VerifyEmployeeCreationSalaryDetailsAdjustmentValues() {
		flag = empMasterFunc.verifyEmployeeCreationSalaryDetailsAdjustmentValues();
		Assert.assertTrue(flag);
	}

	@Test(priority = 5, groups = { "EmployeeMaster" }, description = "6 To Check Employee Creation with Leave Details")
	public void VerifyEmployeeCreationLeaveDetails() {
		flag = empMasterFunc.verifyEmployeeCreationLeaveDetails();
		Assert.assertTrue(flag);
	}

	@Test(priority = 6, groups = {
			"EmployeeMaster" }, description = "8 To check profile image and sign upload image and attachment")
	public void VerifyProfileImageAndSignUploadWithAttachments() {
		flag = empMasterFunc.verifyProfileImageAndSignUploadWithAttachments();
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
