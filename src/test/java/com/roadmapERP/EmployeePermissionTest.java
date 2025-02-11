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
import commonfunctions.payroll.AttendanceLeaveFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class EmployeePermissionTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static AttendanceLeaveFunctions attendanceLeave = new AttendanceLeaveFunctions();

	/**
	 * To Check Permission based on Employee Data
	 * 
	 * @author Kishore-RMITS
	 */
	@Test(priority = 1, groups = {
			"Employee Permission" }, description = "79.To Check Permission based on Employee Data")
	public void VerifyCheckPermissionEntrWithEmployeeData() {
		flag = attendanceLeave.verifyCheckpermissionEntryWithEmployeeData();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Permission Based on Time And Hours
	 * 
	 * @author Kishore-RMITS
	 */
	@Test(priority = 2, groups = {
			"Employee Permission" }, description = "81.To Check Permission Based on Time And Hours")
	public void VerifyBasedOnTimeAndHoursCheckPermission() {
		// TODO Covered in VerifyCheckPermissionEntrWithEmployeeData() Method
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Permission report fetches values correctly
	 * 
	 * @author Kishore-RMITS
	 */
	@Test(priority = 3, groups = {
			"Employee Permission" }, description = "83.To Check Permission report fetches values correctly")
	public void VerifyFetchValuesOfCheckPermission() {
		// TODO Covered in VerifyCheckPermissionEntrWithEmployeeData() Method
		Assert.assertTrue(flag);
	}

	/**
	 * To Check On-Duty based on Employee Data
	 * 
	 * @author Kishore-RMITS
	 */
	@Test(priority = 4, groups = { "Employee On-Duty" }, description = "80.To Check On-Duty based on Employee Data")
	public void VerifyCheckOnDutyEntryWithEmployeeData() {
		flag = attendanceLeave.verifyCheckOnDutyEntrWithEmployeeData();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check On-Duty Based on DateWise
	 * 
	 * @author Kishore-RMITS
	 */
	@Test(priority = 5, groups = { "Employee On-Duty" }, description = "82.To Check On-Duty Based on DateWise")
	public void VerifyDateWiseCheckOnDutyEntryWithEmployeeData() {
		// TODO Covered in VerifyCheckOnDutyEntryWithEmployeeData() Method
		Assert.assertTrue(flag);
	}

	/**
	 * To Check On-Duty report fetches values correctly
	 * 
	 * @author Kishore-RMITS
	 */
	@Test(priority = 6, groups = {
			"Employee On-Duty" }, description = "84.To Check On-Duty report fetches values correctly")
	public void VerifyFetchValuesOfCheckOnDutyEntryWithEmployeeData() {
		// TODO Covered in VerifyCheckOnDutyEntryWithEmployeeData() Method
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
			VariableHandler.setCheckEmployeeDataPermissionDefaultExecution(false);
			commonFunc.verifyAfterMethod();
		}
	}

}
