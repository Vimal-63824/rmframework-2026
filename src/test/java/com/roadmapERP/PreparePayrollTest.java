package com.roadmapERP;

import java.lang.reflect.Method;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks April 30, 2025
 */
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.payroll.PreparePayrollFunctions;
import utils.ReportLoger;

public class PreparePayrollTest extends RoadMapERPTest {
	private boolean flag = false;
	private static ReportLoger logs = new ReportLoger();
	private static int previousStatus = ITestResult.SUCCESS;
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static PreparePayrollFunctions procPayrollFunc = new PreparePayrollFunctions();

	/**
	 * This method used to login while start of the test class
	 * 
	 * @author Sakthi-GWL
	 */
	@BeforeMethod(alwaysRun = true)
	private void VerifyLogin() {
		if (VariableHandler.getSessionID() == null || VariableHandler.getSessionID().equalsIgnoreCase("")
				|| VariableHandler.getSessionOpened() == false || VariableHandler.getSessionID().equals(null)) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
		} else {
			commonFunc.navigateToHomePage();
		}
	}

	/**
	 * To CheckPrepare/Process payroll based on Unit/Group Loading
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
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
	@Author("Sakthi-GWL")
	@Test(priority = 2, groups = {
			"Prepare/Process payroll" }, description = "38 To CheckPrepare/Process payroll based on Load Employee")
	public void VerifyPrepareProcessPayrollBasedOnLoadEmployee() {
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (VerifyPrepareProcessPayrollBasedOnUnitGroupLoading) failed. So this is second try.");
			flag = procPayrollFunc.verifyPayrollProcessFromLoadUnitToProcessPayroll();
			Assert.assertTrue(flag);
		}
	}

	/**
	 * To CheckPrepare/Process payroll based on Unit/Group Select Flag
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 3, groups = {
			"Prepare/Process payroll" }, description = "39 To CheckPrepare/Process payroll based on Unit/Group Select Flag")
	public void VerifyPrepareProcessPayrollBasedOnUnitGroupSelectFlag() {
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous two test (VerifyPrepareProcessPayrollBasedOnUnitGroupLoading, VerifyPrepareProcessPayrollBasedOnLoadEmployee) failed. So this is third try.");
			flag = procPayrollFunc.verifyPayrollProcessFromLoadUnitToProcessPayroll();
			Assert.assertTrue(flag);
		}
	}

	/**
	 * To CheckPrepare/Process payroll based on Employee Select Flag
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 4, groups = {
			"Prepare/Process payroll" }, description = "40 To CheckPrepare/Process payroll based on Employee Select Flag")
	public void VerifyPrepareProcessPayrollBasedOnEmployeeSelectFlag() {
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous three tries in (VerifyPrepareProcessPayrollBasedOnUnitGroupLoading, VerifyPrepareProcessPayrollBasedOnLoadEmployee, VerifyPrepareProcessPayrollBasedOnUnitGroupSelectFlag) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * To CheckPrepare/Process payroll based on Allowance Data
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 5, groups = {
			"Prepare/Process payroll" }, description = "41 To CheckPrepare/Process payroll based on Allowance Data")
	public void VerifyPrepareProcessPayrollBasedOnAllowanceData() {
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous three tries in (VerifyPrepareProcessPayrollBasedOnUnitGroupLoading, VerifyPrepareProcessPayrollBasedOnLoadEmployee, VerifyPrepareProcessPayrollBasedOnUnitGroupSelectFlag) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * To CheckPrepare/Process payroll based on Adjustment Data
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 6, groups = {
			"Prepare/Process payroll" }, description = "42 To CheckPrepare/Process payroll based on Adjustment Data")
	public void VerifyPrepareProcessPayrollBasedOnAdjustmentData() {
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous three tries in (VerifyPrepareProcessPayrollBasedOnUnitGroupLoading, VerifyPrepareProcessPayrollBasedOnLoadEmployee, VerifyPrepareProcessPayrollBasedOnUnitGroupSelectFlag) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * To CheckPrepare/Process payroll based on Overtime Data
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 7, groups = {
			"Prepare/Process payroll" }, description = "43 To CheckPrepare/Process payroll based on Overtime Data")
	public void VerifyPrepareProcessPayrollBasedOnOvertimeData() {
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous three tries in (VerifyPrepareProcessPayrollBasedOnUnitGroupLoading, VerifyPrepareProcessPayrollBasedOnLoadEmployee, VerifyPrepareProcessPayrollBasedOnUnitGroupSelectFlag) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * To CheckPrepare/Process payroll based on Permission / On Duty Data
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 8, groups = {
			"Prepare/Process payroll" }, description = "44 To CheckPrepare/Process payroll based on Permission / On Duty Data")
	public void VerifyPrepareProcessPayrollBasedOnPermissionOnDutyData() {
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous three tries in (VerifyPrepareProcessPayrollBasedOnUnitGroupLoading, VerifyPrepareProcessPayrollBasedOnLoadEmployee, VerifyPrepareProcessPayrollBasedOnUnitGroupSelectFlag) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * To CheckPrepare/Process payroll based on Paid Leave And Unpaid Leave Data
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 9, groups = {
			"Prepare/Process payroll" }, description = "48 To CheckPrepare/Process payroll based on Paid Leave And Unpaid Leave Data")
	public void VerifyPrepareProcessPayrollBasedOnPaidAndUnpaidLeaveData() {
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous three tries in (VerifyPrepareProcessPayrollBasedOnUnitGroupLoading, VerifyPrepareProcessPayrollBasedOnLoadEmployee, VerifyPrepareProcessPayrollBasedOnUnitGroupSelectFlag) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * To CheckPrepare/Process payroll based on Exception Data
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 10, groups = {
			"Prepare/Process payroll" }, description = "49 To CheckPrepare/Process payroll based on Exception Data")
	public void VerifyPrepareProcessPayrollBasedOnExceptionData() {
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous three tries in (VerifyPrepareProcessPayrollBasedOnUnitGroupLoading, VerifyPrepareProcessPayrollBasedOnLoadEmployee, VerifyPrepareProcessPayrollBasedOnUnitGroupSelectFlag) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * To CheckPrepare/Process payroll based on Attendance Data
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 11, groups = {
			"Prepare/Process payroll" }, description = "50 To CheckPrepare/Process payroll based on Attendance Data")
	public void VerifyPrepareProcessPayrollBasedOnAttendanceData() {
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous three tries in (VerifyPrepareProcessPayrollBasedOnUnitGroupLoading, VerifyPrepareProcessPayrollBasedOnLoadEmployee, VerifyPrepareProcessPayrollBasedOnUnitGroupSelectFlag) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * To CheckPrepare/Process payroll based on Unit/Group select Flag(Unload and
	 * load)
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 12, groups = {
			"Prepare/Process payroll" }, description = "52 To CheckPrepare/Process payroll based on Unit/Group select Flag(Unload and load)")
	public void VerifyPrepareProcessPayrollBasedOnUnitGroupUnload() {
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous three tries in (VerifyPrepareProcessPayrollBasedOnUnitGroupLoading, VerifyPrepareProcessPayrollBasedOnLoadEmployee, VerifyPrepareProcessPayrollBasedOnUnitGroupSelectFlag) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * To CheckPrepare/Process payroll based on Load Employee select Flag(Unload and
	 * load)
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 13, groups = {
			"Prepare/Process payroll" }, description = "53 To CheckPrepare/Process payroll based on Load Employee select Flag(Unload and load)")
	public void VerifyPrepareProcessPayrollBasedOnLoadEmployeeUnload() {
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous three tries in (VerifyPrepareProcessPayrollBasedOnUnitGroupLoading, VerifyPrepareProcessPayrollBasedOnLoadEmployee, VerifyPrepareProcessPayrollBasedOnUnitGroupSelectFlag) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * To CheckPrepare/Process payroll based on Review Completed
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 14, groups = {
			"Prepare/Process payroll" }, description = "54 To CheckPrepare/Process payroll based on Review Completed")
	public void VerifyPrepareProcessPayrollBasedOnReviewCompleted() {
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous three tries in (VerifyPrepareProcessPayrollBasedOnUnitGroupLoading, VerifyPrepareProcessPayrollBasedOnLoadEmployee, VerifyPrepareProcessPayrollBasedOnUnitGroupSelectFlag) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * To CheckPrepare/Process payroll based on Process Payroll
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 15, groups = {
			"Prepare/Process payroll" }, description = "54 To CheckPrepare/Process payroll based on Process Payroll")
	public void VerifyPrepareProcessPayrollBasedOnProcessPayroll() {
		logs.logging("INFO", "This test covered in 'VerifyPrepareProcessPayrollBasedOnUnitGroupLoading'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous three tries in (VerifyPrepareProcessPayrollBasedOnUnitGroupLoading, VerifyPrepareProcessPayrollBasedOnLoadEmployee, VerifyPrepareProcessPayrollBasedOnUnitGroupSelectFlag) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * To Check Prepare/Process payroll Find page with all Line wise record
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 16, groups = {
			"Prepare/Process payroll" }, description = "35 To Check Prepare/Process payroll Find page with all Line wise record")
	public void VerifyPreparePayrollFindWithLineWiseRecord() {
		flag = procPayrollFunc.verifyPreparePayrollFindWithLineWiseRecord();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Prepare/Process payroll Find page with all Header wise record
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 17, groups = {
			"Prepare/Process payroll" }, description = "36 To Check Prepare/Process payroll Find page with all Header wise record")
	public void VerifyPreparePayrollFindWithHeaderWiseRecord() {
		flag = procPayrollFunc.verifyPreparePayrollFindWithHeaderWiseRecord();
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
			commonFunc.verifyAfterMethod();
		}
	}
}
