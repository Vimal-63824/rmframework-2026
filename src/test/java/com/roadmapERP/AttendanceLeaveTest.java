package com.roadmapERP;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks April 21, 2025
 */
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import commonfunctions.CommonFunctions;
import commonfunctions.payroll.AttendanceLeaveFunctions;
import utils.ReportLoger;

public class AttendanceLeaveTest extends RoadMapERPTest {
	private static boolean flag = false;
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static AttendanceLeaveFunctions attendanceLeaveFunc = new AttendanceLeaveFunctions();

	/**
	 * To Check Leave Entry Based on the Request date
	 * 
	 * @author Sakthi-GWL
	 */
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
	@Test(priority = 2, groups = {
			"Leave" }, description = "26 To Check Leave Entry Based on Leave types in Leave Master")
	public void VerifyLeaveEntryBasedLeaveTypesLeaveMaster() {
		// This test covered in 'VerifyLeaveEntryBasedRequestDate' Test
		logs.logging("INFO", "This test covered in 'VerifyLeaveEntryBasedRequestDate' Test");
		Assert.assertTrue(flag);
	}

	/**
	 * To check Leave Entry Based on the Leave Balances
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 3, groups = { "Leave" }, description = "27 To check Leave Entry Based on the Leave Balances")
	public void VerifyLeaveEntryBasedLeaveBalances() {
		// This test covered in 'VerifyLeaveEntryBasedRequestDate' Test
		logs.logging("INFO", "This test covered in 'VerifyLeaveEntryBasedRequestDate' Test");
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Leave Entry Based on different Start session and End session
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 4, groups = {
			"Leave" }, description = "28 To Check Leave Entry Based on different Start session and End session")
	public void VerifyLeaveEntryBasedDifferentStartEndSession() {
		// This test covered in 'VerifyLeaveEntryBasedRequestDate' Test
		logs.logging("INFO", "This test covered in 'VerifyLeaveEntryBasedRequestDate' Test");
		Assert.assertTrue(flag);
	}

	/**
	 * Cancel the leave data and checking the Leave status
	 * 
	 * @author Sakthi-GWL
	 */
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
	@Test(priority = 6, groups = { "Leave" }, description = "32 To Check the Leave Entry with the attachment field")
	public void VerifyLeaveEntryWithAttachmentField() {
		// This test covered in 'VerifyCancelLeaveDataCheckLeaveStatus' Test
		logs.logging("INFO", "This test covered in 'VerifyCancelLeaveDataCheckLeaveStatus' Test");
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Leave report fetches values correctly
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 7, groups = { "Leave" }, description = "33 To Check Leave report fetches values correctly")
	public void VerifyLeaveEntryReportFetchesValues() {
		// This test covered in 'VerifyCancelLeaveDataCheckLeaveStatus' Test
		logs.logging("INFO", "This test covered in 'VerifyCancelLeaveDataCheckLeaveStatus' Test");
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Leave Lop Upload Migration with Templates
	 * 
	 * @author Sakthi-GWL
	 */
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
	@Test(priority = 11, groups = {
			"Leave Adjustment" }, description = "61 To Check Leave Adjustment based on the Addition and Deduction Prefix")
	public void VerifyLeaveAdjustmentBasedAdditionDeductionPrefix() {
		// This test covered in 'VerifyLeaveAdjustmentBasedLeaveTypes' Test
		logs.logging("INFO", "This test covered in 'VerifyLeaveAdjustmentBasedLeaveTypes' Test");
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Leave Adjustment Based on the Request date
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 12, groups = {
			"Leave Adjustment" }, description = "62 To Check Leave Adjustment Based on the Request date")
	public void VerifyLeaveAdjustmentBasedRequestDate() {
		// This test covered in 'VerifyLeaveAdjustmentBasedLeaveTypes' Test
		logs.logging("INFO", "This test covered in 'VerifyLeaveAdjustmentBasedLeaveTypes' Test");
		Assert.assertTrue(flag);
	}

	/**
	 * To Check the Leave Adjustment with the attachment field
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 13, groups = {
			"Leave Adjustment" }, description = "64 To Check the Leave Adjustment with the attachment field")
	public void VerifyLeaveAdjustmentWithAttachmentField() {
		// This test covered in 'VerifyLeaveAdjustmentBasedLeaveTypes' Test
		logs.logging("INFO", "This test covered in 'VerifyLeaveAdjustmentBasedLeaveTypes' Test");
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Leave Adjustment report fetches values correctly
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 14, groups = {
			"Leave Adjustment" }, description = "66 To Check Leave Adjustment report fetches values correctly")
	public void VerifyLeaveAdjustmentReportFetchesValuesCorrectly() {
		// This test covered in 'VerifyLeaveAdjustmentBasedLeaveTypes' Test
		logs.logging("INFO", "This test covered in 'VerifyLeaveAdjustmentBasedLeaveTypes' Test");
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Leave Balances Reflecting in Employee Master
	 * 
	 * @author Sakthi-GWL
	 */
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
	@Test(priority = 17, groups = {
			"Leave Adjustment" }, description = "65 To Check Accrual Leave Balance Migration With Templates")
	public void VerifyAccrualLeaveBalanceMigrationWithTemplates() {
		flag = attendanceLeaveFunc.verifyAccrualLeaveBalanceMigrationWithTemplates();
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
