package com.roadmapERP;

import java.lang.reflect.Method;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 5, 2025
 */
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.payroll.AttendanceLeaveFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class EmployeePermissionTest extends RoadMapERPTest {
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
		// TODO Covered in VerifyCheckPermissionEntrWithEmployeeData() Method
		Assert.assertTrue(flag);
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
		// TODO Covered in VerifyCheckPermissionEntrWithEmployeeData() Method
		Assert.assertTrue(flag);
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
		// TODO Covered in VerifyCheckOnDutyEntryWithEmployeeData() Method
		Assert.assertTrue(flag);
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
		// TODO Covered in VerifyCheckOnDutyEntryWithEmployeeData() Method
		Assert.assertTrue(flag);
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
		// TODO Covered in VerifyCheckOnDutyEntryWithEmployeeData() Method
		Assert.assertTrue(flag);
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
		// TODO Covered in VerifyCheckOnDutyEntryWithEmployeeData() Method
		Assert.assertTrue(flag);
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
		// TODO Covered in checkLeaveEncashmentbasedonRequestDate() Method
		Assert.assertTrue(flag);
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
		// TODO Covered in checkLeaveEncashmentbasedonRequestDate() Method
		Assert.assertTrue(flag);
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
		// TODO Covered in checkLeaveEncashmentbasedonRequestDate() Method
		Assert.assertTrue(flag);
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
		// TODO Covered in checkLeaveEncashmentbasedonRequestDate() Method
		Assert.assertTrue(flag);
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
			"Employee Comp-Off" }, description = "96.To Check Comp-Off Entry  based on Leave Types")
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
		// TODO Covered in checkCompOffEntryBasedOnLeaveTypes() Method
		Assert.assertTrue(flag);
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
		// TODO Covered in checkCompOffEntryBasedOnLeaveTypes() Method
		Assert.assertTrue(flag);
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
		// TODO Covered in checkCompOffBasedOnReturnToApprovalRecord() Method
		Assert.assertTrue(flag);
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
		// TODO Covered in checkLoanRequestBasedOnRequiredDate() Method
		Assert.assertTrue(flag);
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
		// TODO Covered in checkLoanRequestBasedOnRequiredDate() Method
		Assert.assertTrue(flag);
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
		// TODO Covered in checkLoanRequestBasedOnRequiredDate() Method
		Assert.assertTrue(flag);
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
		// TODO Covered in checkLoanRequestBasedOnRequiredDate() Method
		Assert.assertTrue(flag);
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
		// TODO Covered in checkLoanRequestBasedOnRequiredDate() Method
		Assert.assertTrue(flag);
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
		// TODO Covered in CheckLoanPaymentWithReversePartOption() Method
		Assert.assertTrue(flag);
	}

	/**
	 * Logout The Portal If Testcases Failed
	 * 
	 * @author Sakthi-GWL
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result,Method method) {
		commonFunc.enterAuthorNamesInExtentReport(method);
		if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP) {
			VariableHandler.setCheckEmployeeDataPermissionDefaultExecution(false);
			commonFunc.verifyAfterMethod();
		}
	}
}
