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
import commonfunctions.payroll.AttendanceLeaveFunctions;
import utils.Mailer;
import utils.ReportLoger;
import utils.WebDriverBase;

public class EmployeePermissionTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static ReportLoger logs = new ReportLoger();
	static AttendanceLeaveFunctions attendanceLeave = new AttendanceLeaveFunctions();
	private static int previousStatus = ITestResult.STARTED;

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
	 * To Check Permission based on Employee Data
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
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
	@Author("Kishore-RMITS")
	@Test(priority = 2, groups = {
			"Employee Permission" }, description = "81.To Check Permission Based on Time And Hours")
	public void VerifyBasedOnTimeAndHoursCheckPermission() {
		logs.logging("INFO", "This test covered in 'VerifyCheckPermissionEntrWithEmployeeData'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (VerifyCheckPermissionEntrWithEmployeeData) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check Permission report fetches values correctly
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 3, groups = {
			"Employee Permission" }, description = "83.To Check Permission report fetches values correctly")
	public void VerifyFetchValuesOfCheckPermission() {
		logs.logging("INFO", "This test covered in 'VerifyCheckPermissionEntrWithEmployeeData'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (VerifyCheckPermissionEntrWithEmployeeData) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check On-Duty based on Employee Data
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
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
	@Author("Kishore-RMITS")
	@Test(priority = 5, groups = { "Employee On-Duty" }, description = "82.To Check On-Duty Based on DateWise")
	public void VerifyDateWiseCheckOnDutyEntryWithEmployeeData() {
		logs.logging("INFO", "This test covered in 'VerifyCheckOnDutyEntryWithEmployeeData'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (VerifyCheckOnDutyEntryWithEmployeeData) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check On-Duty report fetches values correctly
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 6, groups = {
			"Employee On-Duty" }, description = "84.To Check On-Duty report fetches values correctly")
	public void VerifyFetchValuesOfCheckOnDutyEntryWithEmployeeData() {
		logs.logging("INFO", "This test covered in 'VerifyCheckOnDutyEntryWithEmployeeData'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (VerifyCheckOnDutyEntryWithEmployeeData) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check Permission & On Duty based on forward to approval record
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 7, groups = {
			"Employee Permission & On-Duty" }, description = "85.To Check Permission & On Duty based on forward to approval record")
	public void CheckPermissionOnDutybasedonFowardtoAprovalReord() {
		flag = attendanceLeave.checkPermissionOnDutybasedonFowardtoApprovalReord();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check On-Duty field validations and Note alerts
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 8, groups = {
			"Employee Permission & On-Duty" }, description = "84.To Check Permission & On Duty With all the field validations and Note alerts")
	public void VerifyFieldValidationsNoteAlertWithEmployeeData() {
		logs.logging("INFO", "This test covered in 'CheckPermissionOnDutybasedonFowardtoAprovalReord'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (CheckPermissionOnDutybasedonFowardtoAprovalReord) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check Permission & On Duty based on Return to Request record
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 9, groups = {
			"Employee Permission & On-Duty" }, description = "86.To Check Permission & On Duty based on Return to Request record")
	public void CheckPermissionOnDutyBasedOnReturnToRequestRecord() {
		flag = attendanceLeave.checkPermissionOnDutyBasedOnReturnToRequestRecord();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Permission Find page with all line wise record
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 10, groups = {
			"Employee Permission & On-Duty" }, description = "77.To Check Permission Find page with all line wise record")
	public void VerifyPermissionFindPageWithAllLineWiseRecord() {
		logs.logging("INFO", "This test covered in 'CheckPermissionOnDutyBasedOnReturnToRequestRecord'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (CheckPermissionOnDutyBasedOnReturnToRequestRecord) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * Check Leave Encashment based on Request Date
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 11, groups = {
			"Employee Encashment" }, description = "88.To Check Leave Encashment based on Request Date")
	public void checkLeaveEncashmentbasedonRequestDate() {
		flag = attendanceLeave.CheckLeaveEncashmentbasedonRequestDate();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Leave Encashment Find page with all line wise record
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 12, groups = {
			"Employee Encashment" }, description = "87.To Check Leave Encashment Find page with all line wise record")
	public void VerifyLeaveEncashmentFindPageWithAllLineWiseRecord() {
		logs.logging("INFO", "This test covered in 'checkLeaveEncashmentbasedonRequestDate'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (checkLeaveEncashmentbasedonRequestDate) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check Leave Encashment report fetches values correctly
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 13, groups = {
			"Employee Encashment" }, description = "92.To Check Leave Encashment report fetches values correctly")
	public void VerifyLeaveEncashmentReportFetchesValuesCorrectly() {
		logs.logging("INFO", "This test covered in 'checkLeaveEncashmentbasedonRequestDate'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (checkLeaveEncashmentbasedonRequestDate) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check Leave Encashment based on pending Encashment
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 14, groups = {
			"Employee Encashment" }, description = "91.To Check Leave Encashment based on pending Encashment")
	public void VerifyLeaveEncashmentBasedOnPendingEncashment() {
		logs.logging("INFO", "This test covered in 'checkLeaveEncashmentbasedonRequestDate'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (checkLeaveEncashmentbasedonRequestDate) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * Check Leave Encashment based on Forward to Approval Record
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 15, groups = {
			"Employee Encashment" }, description = "89.To Check Leave Encashment based on Forward to Approval Record")
	public void checkLeaveEncashmentBasedOnForwardToApprovalRecord() {
		flag = attendanceLeave.CheckLeaveEncashmentBasedOnForwardToApprovalRecord();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Leave Encashment based on Encash in payroll
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 16, groups = {
			"Employee Encashment" }, description = "90.To Check Leave Encashment based on Encash in payroll")
	public void VerifyLeaveEncashmentBasedOnEncashInPayroll() {
		logs.logging("INFO", "This test covered in 'checkLeaveEncashmentBasedOnForwardToApprovalRecord'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (checkLeaveEncashmentBasedOnForwardToApprovalRecord) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * Check Leave Encashment Based on Return To Request Record
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 17, groups = {
			"Employee Encashment" }, description = "93.To Check Leave Encashment Based on Return To Request Record")
	public void CheckLeaveEncashmentBasedOnReturnToRequestRecord() {
		flag = attendanceLeave.CheckLeaveEncashmentBasedOnReturnToRequestRecord();
		Assert.assertTrue(flag);
	}

	/**
	 * Check Leave Encashment based on Forward to Approval Record
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 18, groups = {
			"Employee Comp-Off" }, description = "96.To Check Comp-Off Entry based on Leave Types")
	public void checkCompOffEntryBasedOnLeaveTypes() {
		flag = attendanceLeave.CheckCompOffEntryBasedOnLeaveTypes();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Comp-Off Based on DateWise
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 19, groups = { "Employee Comp-Off" }, description = "98.To Check Comp-Off Based on DateWise")
	public void checkCompOffBasedonDatewise() {
		logs.logging("INFO", "This test covered in 'checkCompOffEntryBasedOnLeaveTypes'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (checkCompOffEntryBasedOnLeaveTypes) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check Comp-Off Based on DateWise
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 20, groups = {
			"Employee Comp-Off" }, description = "99.To Check Comp-Off based on forward to approval record")
	public void filterDocumentsforPermissionApproval() {
		logs.logging("INFO", "This test covered in 'checkCompOffEntryBasedOnLeaveTypes'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (checkCompOffEntryBasedOnLeaveTypes) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check Comp-Off based on Return to approval record
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 21, groups = {
			"Employee Comp-Off" }, description = "100.To Check Comp-Off based on Return to approval record")
	public void checkCompOffBasedOnReturnToApprovalRecord() {
		flag = attendanceLeave.CheckCompOffBasedOnReturnToApprovalRecord();
		Assert.assertTrue(flag);
	}

	/**
	 * Check Comp-Off Find page with all line wise record
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 22, groups = {
			"Employee Comp-Off" }, description = "95.To Check Comp-Off Find page with all line wise record ")
	public void CheckCompOffFindPageWithAllLineWiseRecord() {
		logs.logging("INFO", "This test covered in 'checkCompOffBasedOnReturnToApprovalRecord'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (checkCompOffBasedOnReturnToApprovalRecord) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check Loan Request based on Required Date
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 23, groups = {
			"Employee Loan Request" }, description = "102.To Check Loan Request based on Required Date")
	public void checkLoanRequestBasedOnRequiredDate() {
		flag = attendanceLeave.CheckLoanRequestBasedOnRequiredDate();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Loan Request based on No of installment and Installment Amount
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 24, groups = {
			"Employee Loan Request" }, description = "103.To Check Loan Request based on No of installment and Installment Amount")
	public void CheckLoanRequestBasedOnNoOfInstallmentAndInstallmentAmount() {
		logs.logging("INFO", "This test covered in 'checkLoanRequestBasedOnRequiredDate'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (checkLoanRequestBasedOnRequiredDate) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To CheckLoan Request Find page with all line wise record
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 25, groups = {
			"Employee Loan Request" }, description = "103.To CheckLoan Request Find page with all line wise record")
	public void ToCheckLoanRequestFindpageWithAllLineWiseRecord() {
		logs.logging("INFO", "This test covered in 'checkLoanRequestBasedOnRequiredDate'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (checkLoanRequestBasedOnRequiredDate) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check Loan Request entry with adding surety employee
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 26, groups = {
			"Employee Loan Request" }, description = "104.To Check Loan Request entry with adding surety employee")
	public void CheckLoanRequestEntryWithAddingSuretyEmployee() {
		logs.logging("INFO", "This test covered in 'checkLoanRequestBasedOnRequiredDate'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (checkLoanRequestBasedOnRequiredDate) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check Loan Request With all the field validations and Note alerts
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 27, groups = {
			"Employee Loan Request" }, description = "To Check Loan Request With all the field validations and Note alerts")
	public void CheckLoanRequestWithAllTheFieldValidationsAndNoteAlerts() {
		logs.logging("INFO", "This test covered in 'checkLoanRequestBasedOnRequiredDate'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (checkLoanRequestBasedOnRequiredDate) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check Loan Request based on forward to approval record
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 28, groups = {
			"Employee Loan Request" }, description = "104.To Check Loan Request based on forward to approval record")
	public void CheckLoanRequestBasedOnForwardToApprovalRecord() {
		logs.logging("INFO", "This test covered in 'checkLoanRequestBasedOnRequiredDate'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (checkLoanRequestBasedOnRequiredDate) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check Loan Request based on Return to approval record
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 29, groups = {
			"Employee Loan Request" }, description = "104.To Check Loan Request based on Return to approval record")
	public void CheckLoanRequestBasedonReturnToApprovalRecord() {
		flag = attendanceLeave.CheckLoanRequestBasedonReturnToApprovalRecord();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Loan Request based on Return to approval record
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 30, groups = {
			"Employee Loan Payment" }, description = "108.To CheckLoan Payment based on Create loan payment part")
	public void checkLoanPaymentbasedonCreateLoanPaymentPart() {
		flag = attendanceLeave.CheckLoanPaymentEntry();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Loan Request based on Return to approval record
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 31, groups = {
			"Employee Loan Payment" }, description = "110.To CheckLoan Payment with reverse part option")
	public void checkLoanPaymentWithReversePartOption() {
		flag = attendanceLeave.CheckLoanPaymentWithReversePartOption();
		Assert.assertTrue(flag);
	}

	/**
	 * To CheckLoan Payment Find page with all line wise record
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 32, groups = {
			"Employee Loan Payment" }, description = "106.To CheckLoan Payment Find page with all line wise record")
	public void CheckLoanPaymentFindPageWithAllLineWiseRecord() {
		logs.logging("INFO", "This test covered in 'checkLoanPaymentWithReversePartOption'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (checkLoanPaymentWithReversePartOption) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To CheckTDS Declaration based on TDS Revision
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 33, groups = {
			"Employee TDS Declaration" }, description = "126.To Check TDS Declaration based on regime wise checking")
	public void checkTDSDeclarationBasedOnTDSRevision() {
		flag = attendanceLeave.CheckTDSDeclarationBasedOnTDSRevision();
		Assert.assertTrue(flag);
	}

	/**
	 * CheckTDS Declaration Find page with all line wise record
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 34, groups = {
			"Employee TDS Declaration" }, description = "124.To CheckTDS Declaration Find page with all line wise record")
	public void checkTDSDeclarationFindPageWithAllLineWiseRecord() {
		logs.logging("INFO", "This test covered in 'checkTDSDeclarationBasedOnTDSRevision'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (checkTDSDeclarationBasedOnTDSRevision) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check TDS Declaration based on forward to approval record
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 35, groups = {
			"Employee TDS Declaration" }, description = "130.To Check TDS Declaration based on forward to approval record")
	public void checkTDSDeclarationBasedOnForwardToApprovalRecord() {
		flag = attendanceLeave.CheckTDSDeclarationBasedOnForwardToApprovalRecord();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check TDS Declaration report fetches values correctly
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 36, groups = {
			"Employee TDS Declaration" }, description = "129.To Check TDS Declaration report fetches values correctly")
	public void CheckTDSDeclarationReportFetchesValuesCorrectly() {
		logs.logging("INFO", "This test covered in 'checkTDSDeclarationBasedOnForwardToApprovalRecord'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (checkTDSDeclarationBasedOnForwardToApprovalRecord) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check TDS Declaration based on Return to approval record
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 37, groups = {
			"Employee TDS Declaration" }, description = "131.To Check TDS Declaration based on Return to approval record")
	public void checkTDSDeclarationBasedOnReturnToApprovalRecord() {
		flag = attendanceLeave.CheckTDSDeclarationBasedOnReturnToApprovalRecord();
		Assert.assertTrue(flag);
	}

	/**
	 * To CheckTDS Declaration based on TDS Existing Regime and New Regime
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 38, groups = {
			"Employee TDS Declaration" }, description = "128.To CheckTDS Declaration based on TDS Existing Regme and New Regime")
	public void checkTDSDeclarationBasedOnTDSExistingRegimeAndNewRegime() {
		flag = attendanceLeave.CheckTDSDeclarationBasedOnTDSExistingRegimeAndNewRegime();
		Assert.assertTrue(flag);
	}

	/**
	 * To CheckTDS Declaration based on section value assigning.
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 39, groups = {
			"Employee TDS Declaration" }, description = "125.To CheckTDS Declaration based on section value assigning")
	public void checkTDSDeclarationBasedOnSectionValueAssigning() {
		flag = attendanceLeave.CheckTDSDeclarationBasedOnSectionValueAssigning();
		Assert.assertTrue(flag);
	}

	/**
	 * To CheckTDS Declaration based on regime wise checking
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 40, groups = {
			"Employee TDS Declaration" }, description = "126.To CheckTDS Declaration based on regime wise checking")
	public void checkTDSDeclarationBasedOnRegimeWiseChecking() {
		flag = attendanceLeave.CheckTDSDeclarationBasedOnRegimeWiseChecking();
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
		if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP) {
			VariableHandler.setCheckEmployeeDataPermissionDefaultExecution(false);
			commonFunc.verifyAfterMethod();
		}
	}
}
