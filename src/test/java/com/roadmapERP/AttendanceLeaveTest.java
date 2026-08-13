package com.roadmapERP;

import java.lang.reflect.Method;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks April 21, 2025
 */
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.payroll.AttendanceLeaveFunctions;
import utils.ReportLoger;

public class AttendanceLeaveTest extends RoadMapERPTest {
	private boolean flag = false;
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static AttendanceLeaveFunctions attendanceLeaveFunc = new AttendanceLeaveFunctions();
	private static int previousStatus = ITestResult.SUCCESS;

	/**
	 * This method used to login while start of the test class
	 * 
	 * @author Sakthi-GWL
	 */
	@BeforeMethod(alwaysRun = true)
	private void VerifyLogin() {
		if (VariableHandler.getSessionID() == null || VariableHandler.getSessionID().equalsIgnoreCase("")
				|| VariableHandler.getSessionOpened() == false || VariableHandler.getSessionID().equals(null)) {
			commonFunc.validCommonLogin(System.getProperty("HrRqtUsername"), System.getProperty("LoginPassword"),
					"HrRqtUsername");
		} else {
			commonFunc.navigateToHomePage();
		}
	}

	/**
	 * To Check Leave Entry Based on the Request date
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 1, groups = { "Leave" }, description = "25 To Check Leave Entry Based on the Request date")
	public void VerifyLeaveEntryBasedRequestDate() {
		flag = attendanceLeaveFunc.verifyLeaveEntryDateTypeBalanceSession();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Leave Entry Based on Leave types in Leave Master
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 2, groups = {
			"Leave" }, description = "26 To Check Leave Entry Based on Leave types in Leave Master")
	public void VerifyLeaveEntryBasedLeaveTypesLeaveMaster() {
		logs.logging("INFO", "This test covered in 'VerifyLeaveEntryBasedRequestDate'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (VerifyLeaveEntryBasedRequestDate) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To check Leave Entry Based on the Leave Balances
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 3, groups = { "Leave" }, description = "27 To check Leave Entry Based on the Leave Balances")
	public void VerifyLeaveEntryBasedLeaveBalances() {
		logs.logging("INFO", "This test covered in 'VerifyLeaveEntryBasedRequestDate'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (VerifyLeaveEntryBasedRequestDate) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check Leave Entry Based on different Start session and End session
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 4, groups = {
			"Leave" }, description = "28 To Check Leave Entry Based on different Start session and End session")
	public void VerifyLeaveEntryBasedDifferentStartEndSession() {
		logs.logging("INFO", "This test covered in 'VerifyLeaveEntryBasedRequestDate'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (VerifyLeaveEntryBasedRequestDate) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * Cancel the leave data and checking the Leave status
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 5, groups = { "Leave" }, description = "31 Cancel the leave data and checking the Leave status")
	public void VerifyCancelLeaveDataCheckLeaveStatus() {
		flag = attendanceLeaveFunc.verifyCancelLeaveCheckLeaveStatus();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check the Leave Entry with the attachment field
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 6, groups = { "Leave" }, description = "32 To Check the Leave Entry with the attachment field")
	public void VerifyLeaveEntryWithAttachmentField() {
		logs.logging("INFO", "This test covered in 'VerifyCancelLeaveDataCheckLeaveStatus'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (VerifyCancelLeaveDataCheckLeaveStatus) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check Leave report fetches values correctly
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 7, groups = { "Leave" }, description = "33 To Check Leave report fetches values correctly")
	public void VerifyLeaveEntryReportFetchesValues() {
		logs.logging("INFO", "This test covered in 'VerifyCancelLeaveDataCheckLeaveStatus'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (VerifyCancelLeaveDataCheckLeaveStatus) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check Leave Lop Upload Migration with Templates
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 8, groups = { "Leave" }, description = "29 To Check Leave Lop Upload Migration with Templates")
	public void VerifyLeaveLopUploadMigrationWithTemplates() {
		flag = attendanceLeaveFunc.verifyLeaveLOPUploadMigrationWithTemplates();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Leave Accrual Upload Migration with Templates
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 9, groups = { "Leave" }, description = "30 To Check Leave Accrual Upload Migration with Templates")
	public void VerifyLeaveAccrualUploadMigrationWithTemplates() {
		flag = attendanceLeaveFunc.verifyLeaveAccrualUploadMigrationWithTemplates();
		Assert.assertTrue(flag);
	}

	// --------------- Leave Adjustment Tests --------------

	/**
	 * To Check Leave Adjustment Based on Leave types in Leave Master
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 10, groups = {
			"Leave Adjustment" }, description = "60 To Check Leave Adjustment Based on Leave types in Leave Master")
	public void VerifyLeaveAdjustmentBasedLeaveTypes() {
		flag = attendanceLeaveFunc.verifyAdjustmentBasedLeaveTypesPrefixReqDate();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Leave Adjustment based on the Addition and Deduction Prefix
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 11, groups = {
			"Leave Adjustment" }, description = "61 To Check Leave Adjustment based on the Addition and Deduction Prefix")
	public void VerifyLeaveAdjustmentBasedAdditionDeductionPrefix() {
		logs.logging("INFO", "This test covered in 'VerifyLeaveAdjustmentBasedLeaveTypes'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (VerifyLeaveAdjustmentBasedLeaveTypes) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check Leave Adjustment Based on the Request date
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 12, groups = {
			"Leave Adjustment" }, description = "62 To Check Leave Adjustment Based on the Request date")
	public void VerifyLeaveAdjustmentBasedRequestDate() {
		logs.logging("INFO", "This test covered in 'VerifyLeaveAdjustmentBasedLeaveTypes'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (VerifyLeaveAdjustmentBasedLeaveTypes) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check the Leave Adjustment with the attachment field
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 13, groups = {
			"Leave Adjustment" }, description = "64 To Check the Leave Adjustment with the attachment field")
	public void VerifyLeaveAdjustmentWithAttachmentField() {
		logs.logging("INFO", "This test covered in 'VerifyLeaveAdjustmentBasedLeaveTypes'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (VerifyLeaveAdjustmentBasedLeaveTypes) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check Leave Adjustment report fetches values correctly
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 14, groups = {
			"Leave Adjustment" }, description = "66 To Check Leave Adjustment report fetches values correctly")
	public void VerifyLeaveAdjustmentReportFetchesValuesCorrectly() {
		logs.logging("INFO", "This test covered in 'VerifyLeaveAdjustmentBasedLeaveTypes'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (VerifyLeaveAdjustmentBasedLeaveTypes) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check Leave Balances Reflecting in Employee Master
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 15, groups = {
			"Leave Adjustment" }, description = "68 To Check Leave Balances Reflecting in Employee Master")
	public void VerifyLeaveAdjustmentBalancesReflectingInEmployeeMaster() {
		flag = attendanceLeaveFunc.verifyLeaveAdjustmentBalancesReflectingInEmployeeMaster();
		Assert.assertTrue(flag);
	}

	/**
	 * Cancel the leave Adjustment and checking the status of Leave Adjustment
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 16, groups = {
			"Leave Adjustment" }, description = "63 Cancel the leave Adjustment and checking the status of Leave Adjustment")
	public void VerifyCancelCheckStatusLeaveAdjustment() {
		flag = attendanceLeaveFunc.verifyCancelCheckStatusLeaveAdjustment();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Accrual Leave Balance Migration With Templates
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 17, groups = {
			"Leave Adjustment" }, description = "65 To Check Accrual Leave Balance Migration With Templates")
	public void VerifyAccrualLeaveBalanceMigrationWithTemplates() {
		flag = attendanceLeaveFunc.verifyAccrualLeaveBalanceMigrationWithTemplates();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Leave Entry Find page with all line wise record
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 18, groups = {
			"Leave" }, description = "24 To Check Leave Entry Find page with all line wise record")
	public void VerifyLeaveEntryFindWithLineWiseRecord() {
		flag = attendanceLeaveFunc.verifyLeaveEntryFindWithLineWiseRecord();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Leave Adjustment Find page with all line wise record
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 19, groups = {
			"Leave Adjustment" }, description = "59 To Check Leave Adjustment Find page with all line wise record")
	public void VerifyLeaveAdjustmentFindWithLineWiseRecord() {
		flag = attendanceLeaveFunc.verifyLeaveAdjustmentFindWithLineWiseRecord();
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

	@AfterClass
	public void VerifyLogout() {
		commonFunc.logoutUser();
	}

}