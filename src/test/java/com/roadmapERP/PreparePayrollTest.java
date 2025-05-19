package com.roadmapERP;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks April 30, 2025
 */
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import commonfunctions.CommonFunctions;
import commonfunctions.payroll.PreparePayrollFunctions;
import utils.ReportLoger;

public class PreparePayrollTest extends RoadMapERPTest {
	private static boolean flag = false;
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static PreparePayrollFunctions procPayrollFunc = new PreparePayrollFunctions();

	/**
	 * To CheckPrepare/Process payroll based on Unit/Group Loading
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 1, groups = {
			"Prepare/Process payroll" }, description = "37 To CheckPrepare/Process payroll based on Unit/Group Loading")
	public void VerifyPrepareProcessPayrollBasedOnUnitGroupLoading() {
		flag = procPayrollFunc.verifyPayrollProcessFromLoadUnitToProcessPayroll();
		Assert.assertTrue(flag);
	}

	/**
	 * To CheckPrepare/Process payroll based on Load Employee
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 2, groups = {
			"Prepare/Process payroll" }, description = "38 To CheckPrepare/Process payroll based on Load Employee")
	public void VerifyPrepareProcessPayrollBasedOnLoadEmployee() {
		// This test covered in 'VerifyPayrollProcessFromLoadUnitToProcessPayroll' Test
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading' Test");
		Assert.assertTrue(flag);
	}

	/**
	 * To CheckPrepare/Process payroll based on Unit/Group Select Flag
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 3, groups = {
			"Prepare/Process payroll" }, description = "39 To CheckPrepare/Process payroll based on Unit/Group Select Flag")
	public void VerifyPrepareProcessPayrollBasedOnUnitGroupSelectFlag() {
		// This test covered in 'VerifyPayrollProcessFromLoadUnitToProcessPayroll' Test
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading' Test");
		Assert.assertTrue(flag);
	}

	/**
	 * To CheckPrepare/Process payroll based on Employee Select Flag
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 4, groups = {
			"Prepare/Process payroll" }, description = "40 To CheckPrepare/Process payroll based on Employee Select Flag")
	public void VerifyPrepareProcessPayrollBasedOnEmployeeSelectFlag() {
		// This test covered in 'VerifyPayrollProcessFromLoadUnitToProcessPayroll' Test
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading' Test");
		Assert.assertTrue(flag);
	}

	/**
	 * To CheckPrepare/Process payroll based on Allowance Data
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 5, groups = {
			"Prepare/Process payroll" }, description = "41 To CheckPrepare/Process payroll based on Allowance Data")
	public void VerifyPrepareProcessPayrollBasedOnAllowanceData() {
		// This test covered in 'VerifyPayrollProcessFromLoadUnitToProcessPayroll' Test
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading' Test");
		Assert.assertTrue(flag);
	}

	/**
	 * To CheckPrepare/Process payroll based on Adjustment Data
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 6, groups = {
			"Prepare/Process payroll" }, description = "42 To CheckPrepare/Process payroll based on Adjustment Data")
	public void VerifyPrepareProcessPayrollBasedOnAdjustmentData() {
		// This test covered in 'VerifyPayrollProcessFromLoadUnitToProcessPayroll' Test
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading' Test");
		Assert.assertTrue(flag);
	}

	/**
	 * To CheckPrepare/Process payroll based on Overtime Data
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 7, groups = {
			"Prepare/Process payroll" }, description = "43 To CheckPrepare/Process payroll based on Overtime Data")
	public void VerifyPrepareProcessPayrollBasedOnOvertimeData() {
		// This test covered in 'VerifyPayrollProcessFromLoadUnitToProcessPayroll' Test
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading' Test");
		Assert.assertTrue(flag);
	}

	/**
	 * To CheckPrepare/Process payroll based on Permission / On Duty Data
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 8, groups = {
			"Prepare/Process payroll" }, description = "44 To CheckPrepare/Process payroll based on Permission / On Duty Data")
	public void VerifyPrepareProcessPayrollBasedOnPermissionOnDutyData() {
		// This test covered in 'VerifyPayrollProcessFromLoadUnitToProcessPayroll' Test
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading' Test");
		Assert.assertTrue(flag);
	}

	/**
	 * To CheckPrepare/Process payroll based on Paid Leave And Unpaid Leave Data
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 9, groups = {
			"Prepare/Process payroll" }, description = "48 To CheckPrepare/Process payroll based on Paid Leave And Unpaid Leave Data")
	public void VerifyPrepareProcessPayrollBasedOnPaidAndUnpaidLeaveData() {
		// This test covered in 'VerifyPayrollProcessFromLoadUnitToProcessPayroll' Test
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading' Test");
		Assert.assertTrue(flag);
	}

	/**
	 * To CheckPrepare/Process payroll based on Exception Data
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 10, groups = {
			"Prepare/Process payroll" }, description = "49 To CheckPrepare/Process payroll based on Exception Data")
	public void VerifyPrepareProcessPayrollBasedOnExceptionData() {
		// This test covered in 'VerifyPayrollProcessFromLoadUnitToProcessPayroll' Test
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading' Test");
		Assert.assertTrue(flag);
	}

	/**
	 * To CheckPrepare/Process payroll based on Attendance Data
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 11, groups = {
			"Prepare/Process payroll" }, description = "50 To CheckPrepare/Process payroll based on Attendance Data")
	public void VerifyPrepareProcessPayrollBasedOnAttendanceData() {
		// This test covered in 'VerifyPayrollProcessFromLoadUnitToProcessPayroll' Test
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading' Test");
		Assert.assertTrue(flag);
	}

	/**
	 * To CheckPrepare/Process payroll based on Unit/Group select Flag(Unload and
	 * load)
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 12, groups = {
			"Prepare/Process payroll" }, description = "52 To CheckPrepare/Process payroll based on Unit/Group select Flag(Unload and load)")
	public void VerifyPrepareProcessPayrollBasedOnUnitGroupUnload() {
		// This test covered in 'VerifyPayrollProcessFromLoadUnitToProcessPayroll' Test
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading' Test");
		Assert.assertTrue(flag);
	}

	/**
	 * To CheckPrepare/Process payroll based on Load Employee select Flag(Unload and
	 * load)
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 13, groups = {
			"Prepare/Process payroll" }, description = "53 To CheckPrepare/Process payroll based on Load Employee select Flag(Unload and load)")
	public void VerifyPrepareProcessPayrollBasedOnLoadEmployeeUnload() {
		// This test covered in 'VerifyPayrollProcessFromLoadUnitToProcessPayroll' Test
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading' Test");
		Assert.assertTrue(flag);
	}

	/**
	 * To CheckPrepare/Process payroll based on Review Completed
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 14, groups = {
			"Prepare/Process payroll" }, description = "54 To CheckPrepare/Process payroll based on Review Completed")
	public void VerifyPrepareProcessPayrollBasedOnReviewCompleted() {
		// This test covered in 'VerifyPayrollProcessFromLoadUnitToProcessPayroll' Test
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading' Test");
		Assert.assertTrue(flag);
	}

	/**
	 * To CheckPrepare/Process payroll based on Process Payroll
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 15, groups = {
			"Prepare/Process payroll" }, description = "54 To CheckPrepare/Process payroll based on Process Payroll")
	public void VerifyPrepareProcessPayrollBasedOnProcessPayroll() {
		// This test covered in 'VerifyPayrollProcessFromLoadUnitToProcessPayroll' Test
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading' Test");
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
		if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP) {
			commonFunc.verifyAfterMethod();
		}
	}
}
