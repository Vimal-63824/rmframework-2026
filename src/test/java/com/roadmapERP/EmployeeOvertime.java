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

public class EmployeeOvertime extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static AttendanceLeaveFunctions attendanceLeave = new AttendanceLeaveFunctions();

	/**
	 * To Check Overtime based on Employee Wise
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 1, groups = { "Employee Overtime" }, description = "145.To Check Overtime based on Employee Wise")
	public void checkOvertimeBasedOnEmployeeWise() {
		flag = attendanceLeave.CheckOvertimeBasedOnEmployeeWise();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Overtime Find page with all line wise record
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 2, groups = {
			"Employee Overtime" }, description = "144.To Check Overtime Find page with all line wise record")
	public void checkOvertimeFindPageWithAllLineWiseRecord() {
		// TODO Covered in checkOvertimeBasedOnEmployeeWise() Method
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Overtime Entry based on Forward To Approval
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 3, groups = {
			"Employee Overtime" }, description = "147.To Check Overtime Entry based on Forward To Approval")
	public void checkOvertimeEntryBasedOnForwardToApproval() {
		flag = attendanceLeave.CheckOvertimeEntryBasedOnForwardToApproval();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Overtime Entry report fetches values correctly
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 4, groups = {
			"Employee Overtime" }, description = "150.To Check Overtime Entry report fetches values correctly")
	public void CheckOvertimeEntryReportFetchesValuesCorrectly() {
		// TODO Covered in CheckOvertimeEntryBasedOnForwardToApproval() Method
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Overtime Entry based on Return to approval record
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 5, groups = {
			"Employee Overtime" }, description = "148.To Check Overtime Entry based on Return to approval record")
	public void checkOvertimeEntryBasedOnReturnToApprovalRecord() {
		flag = attendanceLeave.CheckOvertimeEntryBasedOnReturnToApprovalRecord();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Overtime Entry Based on Cancelled Document
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 6, groups = {
			"Employee Overtime" }, description = "149.To Check Overtime Entry Based on Cancelled Document")
	public void checkOvertimeEntryBasedOnCancelledDocument() {
		flag = attendanceLeave.CheckOvertimeEntryBasedOnCancelledDocument();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Overtime Entry Based on Ratio Wise
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 7, groups = {
			"Employee Overtime" }, description = "146.To Check Overtime Entry Based on Ratio Wise")
	public void checkOvertimeEntryBasedOnRatioWise() {
		// TODO Covered in CheckOvertimeEntryBasedOnCancelledDocument() Method
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Travel Request Based on Employee Wise
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 8, groups = {
			"Employee Overtime" }, description = "160.To Check Travel Request Based on Employee Wise")
	public void checkTravelRequestBasedOnEmployeeWise() {
		flag = attendanceLeave.CheckTravelRequestBasedOnEmployeeWise();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Travel Request Based on the adding Additional Travellers
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 9, groups = {
			"Employee Overtime" }, description = "164.To Check Travel Request Based on the adding Additional Travellers")
	public void checkTravelRequestBasedOnTheAddingAdditionalTravellers() {
		// TODO Covered in CheckTravelRequestBasedOnEmployeeWise() Method
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Travel Request Find page with all line wise record
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 10, groups = {
			"Employee Overtime" }, description = "159.To Check Travel Request Find page with all line wise record")
	public void checkTravelRequestFindPageWithAllLineWiseRecord() {
		// TODO Covered in CheckTravelRequestBasedOnEmployeeWise() Method
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Travel Request Based on Forward To Approval
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 11, groups = {
			"Employee Overtime" }, description = "161.To Check Travel Request Based on Forward To Approval")
	public void checkTravelRequestBasedOnForwardToApproval() {
		flag = attendanceLeave.CheckTravelRequestBasedOnForwardToApproval();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Travel Request Based on all the Required Fields
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 12, groups = {
			"Employee Overtime" }, description = "166.To Check Travel Request Based on all the Required Fields")
	public void checkTravelRequestBasedOnAllTheRequiredFields() {
		// TODO Covered in CheckTravelRequestBasedOnForwardToApproval() Method
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Travel Request Based on the Expenses details
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 13, groups = {
			"Employee Overtime" }, description = "163.To Check Travel Request Based on the Expenses details")
	public void checkTravelRequestBasedOnTheExpensesDetails() {
		// TODO Covered in CheckTravelRequestBasedOnForwardToApproval() Method
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Travel Request Based on Return To Approval
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 14, groups = {
			"Employee Overtime" }, description = "162.To Check Travel Request Based on Return To Approval")
	public void checkTravelRequestBasedOnReturnToApproval() {
		flag = attendanceLeave.CheckTravelRequestBasedOnReturnToApproval();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Travel Request Based on Cancelling The Travel Request
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 15, groups = {
			"Employee Overtime" }, description = "165.To Check Travel Request Based on Cancelling The Travel Request")
	public void checkTravelRequestBasedOnCancellingTheTravelRequest() {
		flag = attendanceLeave.CheckTravelRequestBasedOnCancellingTheTravelRequest();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Travel Reimbursement Based on Employee Wise
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 16, groups = {
			"Travel Reimbursement" }, description = "168.To Check Travel Reimbursement Based on Employee Wise ")
	public void checkTravelReimbursementBasedOnEmployeeWise() {
		flag = attendanceLeave.CheckTravelReimbursementBasedOnEmployeeWise();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Travel Reimbursement Find page with all line wise record
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 17, groups = {
			"Travel Reimbursement" }, description = "168.To Check Travel Reimbursement Find page with all line wise record")
	public void checkTravelReimbursementFindPageWithAllLineWiseRecord() {
		// TODO Covered in CheckTravelReimbursementBasedOnEmployeeWise() Method
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Travel Reimbursement Based on the Expenses details
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 18, groups = {
			"Travel Reimbursement" }, description = "168.To Check Travel Reimbursement Based on the Expenses details")
	public void checkTravelReimbursementBasedOnTheExpensesDetails() {
		// TODO Covered in CheckTravelReimbursementBasedOnEmployeeWise() Method
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Travel Reimbursement Based on Forward To Approval
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 19, groups = {
			"Travel Reimbursement" }, description = "168.To Check Travel Reimbursement Based on Forward To Approval")
	public void checkTravelReimbursementBasedOnForwardToApproval() {
		flag = attendanceLeave.CheckTravelReimbursementBasedOnForwardToApproval();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Travel Reimbursement Based on the adding Additional Travellers
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 20, groups = {
			"Travel Reimbursement" }, description = "170.To Check Travel Reimbursement Based on the adding Additional Travellers")
	public void checkTravelReimbursementBasedOnTheAddingAdditionalTravellers() {
		// TODO Covered in CheckTravelReimbursementBasedOnForwardToApproval() Method
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Travel Reimbursement Based on all the Required Fields
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 21, groups = {
			"Travel Reimbursement" }, description = "171.To Check Travel Reimbursement Based on all the Required Fields")
	public void checkTravelReimbursementBasedOnAllTheRequiredFields() {
		// TODO Covered in CheckTravelReimbursementBasedOnForwardToApproval() Method
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Travel Reimbursement Based on Return To Approval
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 22, groups = {
			"Travel Reimbursement" }, description = "168.To Check Travel Reimbursement Based on Return To Approval")
	public void checkTravelReimbursementBasedOnReturnToApproval() {
		flag = attendanceLeave.CheckTravelReimbursementBasedOnReturnToApproval();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check General Expense Based on Employee Wise
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 23, groups = {
			"General Expenses" }, description = "177.To Check General Expense Based on Employee Wise ")
	public void checkGeneralExpenseBasedOnEmployeeWise() {
		flag = attendanceLeave.CheckGeneralExpenseBasedOnEmployeeWise();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check General Expense Based on the Expenses details
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 24, groups = {
			"General Expenses" }, description = "178.To Check General Expense Based on the Expenses details")
	public void checkGeneralExpenseBasedOnTheExpensesDetails() {
		// TODO Covered in CheckGeneralExpenseBasedOnEmployeeWise() Method
		Assert.assertTrue(flag);
	}

	/**
	 * To Check General Expense Find page with all line wise record
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 25, groups = {
			"General Expenses" }, description = "176.To Check General Expense Find page with all line wise record")
	public void checkGeneralExpenseFindPageWithAllLineWiseRecord() {
		// TODO Covered in CheckGeneralExpenseBasedOnEmployeeWise() Method
		Assert.assertTrue(flag);
	}

	/**
	 * To Check General Expense Based on Forward To Approval
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 26, groups = {
			"General Expenses" }, description = "176.To Check General Expense Based on Forward To Approval")
	public void checkGeneralExpenseBasedOnForwardToApproval() {
		flag = attendanceLeave.CheckGeneralExpenseBasedOnForwardToApproval();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check General Expense Based on Return To Approval
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 27, groups = {
			"General Expenses" }, description = "181.To Check General Expense Based on Return To Approval")
	public void checkGeneralExpenseBasedOnReturnToApproval() {
		flag = attendanceLeave.CheckGeneralExpenseBasedOnReturnToApproval();
		Assert.assertTrue(flag);
	}
	
	/**
	 * To Check General Expense Based on all the Required Fields
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 28, groups = {
			"General Expenses" }, description = "174.To Check General Expense Based on all the Required Fields")
	public void checkGeneralExpenseBasedOnAllTheRequiredFields() {
		// TODO Covered in CheckGeneralExpenseBasedOnReturnToApproval() Method
		Assert.assertTrue(flag);
	}
	
	/**
	 * To Check General Expense fetches values correctly
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 29, groups = {
			"General Expenses" }, description = "178.To Check General Expense fetches values correctly")
	public void checkGeneralExpenseFetchesvaluesCorrectly() {
		// TODO Covered in CheckGeneralExpenseBasedOnReturnToApproval() Method
		Assert.assertTrue(flag);
	}
	
	/**
	 * To Check General Expense With all the field validations and Note alerts
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 30, groups = {
			"General Expenses" }, description = "178.To Check General Expense With all the field validations and Note alerts")
	public void checkGeneralExpenseWithAllTheFieldValidationsAndNoteAlerts() {
		// TODO Covered in CheckGeneralExpenseBasedOnReturnToApproval() Method
		Assert.assertTrue(flag);
	}
	
	/**
	 * To Check General Expense Based on Cancelling The Document
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 31, groups = {
			"General Expenses" }, description = "178.To Check General Expense Based on Cancelling The Document")
	public void checkGeneralExpenseBasedOnCancellingTheDocument() {
		flag = attendanceLeave.CheckGeneralExpenseBasedOnCancellingTheDocument();
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
		commonFunc.enterAuthorNamesInExtentReport(method);
		if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP) {
			VariableHandler.setCheckEmployeeDataPermissionDefaultExecution(false);
			commonFunc.verifyAfterMethod();
		}
	}

}
