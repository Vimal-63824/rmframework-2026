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

public class EmployeeOvertime extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
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
		logs.logging("INFO", "This test covered in 'checkOvertimeBasedOnEmployeeWise'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (checkOvertimeBasedOnEmployeeWise) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
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
		logs.logging("INFO", "This test covered in 'checkOvertimeEntryBasedOnForwardToApproval'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (checkOvertimeEntryBasedOnForwardToApproval) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
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
		logs.logging("INFO", "This test covered in 'checkOvertimeEntryBasedOnCancelledDocument'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (checkOvertimeEntryBasedOnCancelledDocument) failed. So this fails too.");
			Assert.fail();
		}
		Assert.assertTrue(true);
	}

	/**
	 * To Check Travel Request Based on Employee Wise
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 8, groups = {
			"Travel Request" }, description = "160.To Check Travel Request Based on Employee Wise")
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
			"Travel Request" }, description = "164.To Check Travel Request Based on the adding Additional Travellers")
	public void checkTravelRequestBasedOnTheAddingAdditionalTravellers() {
		logs.logging("INFO", "This test covered in 'checkTravelRequestBasedOnEmployeeWise'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (checkTravelRequestBasedOnEmployeeWise) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * To Check Travel Request Find page with all line wise record
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 10, groups = {
			"Travel Request" }, description = "159.To Check Travel Request Find page with all line wise record")
	public void checkTravelRequestFindPageWithAllLineWiseRecord() {
		logs.logging("INFO", "This test covered in 'checkTravelRequestBasedOnEmployeeWise'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (checkTravelRequestBasedOnEmployeeWise) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * To Check Travel Request Based on Forward To Approval
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 11, groups = {
			"Travel Request" }, description = "161.To Check Travel Request Based on Forward To Approval")
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
			"Travel Request" }, description = "166.To Check Travel Request Based on all the Required Fields")
	public void checkTravelRequestBasedOnAllTheRequiredFields() {
		logs.logging("INFO", "This test covered in 'checkTravelRequestBasedOnForwardToApproval'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (checkTravelRequestBasedOnForwardToApproval) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * To Check Travel Request Based on the Expenses details
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 13, groups = {
			"Travel Request" }, description = "163.To Check Travel Request Based on the Expenses details")
	public void checkTravelRequestBasedOnTheExpensesDetails() {
		logs.logging("INFO", "This test covered in 'checkTravelRequestBasedOnForwardToApproval'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (checkTravelRequestBasedOnForwardToApproval) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * To Check Travel Request Based on Return To Approval
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 14, groups = {
			"Travel Request" }, description = "162.To Check Travel Request Based on Return To Approval")
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
			"Travel Request" }, description = "165.To Check Travel Request Based on Cancelling The Travel Request")
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
		logs.logging("INFO", "This test covered in 'checkTravelReimbursementBasedOnEmployeeWise'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (checkTravelReimbursementBasedOnEmployeeWise) failed. So this fails too.");
			Assert.fail();
		}
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
		logs.logging("INFO", "This test covered in 'checkTravelReimbursementBasedOnEmployeeWise'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (checkTravelReimbursementBasedOnEmployeeWise) failed. So this fails too.");
			Assert.fail();
		}
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
		logs.logging("INFO", "This test covered in 'checkTravelReimbursementBasedOnForwardToApproval'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (checkTravelReimbursementBasedOnForwardToApproval) failed. So this fails too.");
			Assert.fail();
		}
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
		logs.logging("INFO", "This test covered in 'checkTravelReimbursementBasedOnForwardToApproval'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (checkTravelReimbursementBasedOnForwardToApproval) failed. So this fails too.");
			Assert.fail();
		}
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
	 * To Check Travel Reimbursement Based on Cancelling The Document
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 23, groups = {
			"Travel Reimbursement" }, description = "169.To Check Travel Reimbursement Based on Cancelling The Document")
	public void checkTravelReimbursementBasedOnCancellingTheDocument() {
		flag = attendanceLeave.CheckTravelReimbursementBasedOnCancellingTheDocument();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Travel Reimbursement fetches values correctly
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 24, groups = {
			"Travel Reimbursement" }, description = "170.To Check Travel Reimbursement fetches values correctly")
	public void checkTravelReimbursementFetchesValuesCorrectly() {
		logs.logging("INFO", "This test covered in 'checkTravelReimbursementBasedOnCancellingTheDocument'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (checkTravelReimbursementBasedOnCancellingTheDocument) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * To Check General Expense Based on Employee Wise
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 25, groups = {
			"General Expenses" }, description = "177.To Check General Expense Based on Employee Wise ")
	public void checkGeneralExpenseBasedOnEmployeeWise() {
		flag = attendanceLeave.checkGeneralExpenseBasedOnEmployeeWise();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check General Expense Based on the Expenses details
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 26, groups = {
			"General Expenses" }, description = "178.To Check General Expense Based on the Expenses details")
	public void checkGeneralExpenseBasedOnTheExpensesDetails() {
		logs.logging("INFO", "This test covered in 'checkGeneralExpenseBasedOnEmployeeWise'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (checkGeneralExpenseBasedOnEmployeeWise) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * To Check General Expense Find page with all line wise record
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 27, groups = {
			"General Expenses" }, description = "179.To Check General Expense Find page with all line wise record")
	public void checkGeneralExpenseFindPageWithAllLineWiseRecord() {
		logs.logging("INFO", "This test covered in 'checkGeneralExpenseBasedOnEmployeeWise'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (checkGeneralExpenseBasedOnEmployeeWise) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * To Check General Expense Based on Forward To Approval
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 28, groups = {
			"General Expenses" }, description = "180.To Check General Expense Based on Forward To Approval")
	public void checkGeneralExpenseBasedOnForwardToApproval() {
		flag = attendanceLeave.checkGeneralExpenseBasedOnForwardToApproval();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check General Expense Based on Return To Approval
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 29, groups = {
			"General Expenses" }, description = "181.To Check General Expense Based on Return To Approval")
	public void checkGeneralExpenseBasedOnReturnToApproval() {
		flag = attendanceLeave.checkGeneralExpenseBasedOnReturnToApproval();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check General Expense Based on all the Required Fields
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 30, groups = {
			"General Expenses" }, description = "174.To Check General Expense Based on all the Required Fields")
	public void checkGeneralExpenseBasedOnAllTheRequiredFields() {
		logs.logging("INFO", "This test covered in 'checkGeneralExpenseBasedOnReturnToApproval'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (checkGeneralExpenseBasedOnReturnToApproval) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * To Check General Expense fetches values correctly
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 31, groups = {
			"General Expenses" }, description = "178.To Check General Expense fetches values correctly")
	public void checkGeneralExpenseFetchesvaluesCorrectly() {
		logs.logging("INFO", "This test covered in 'checkGeneralExpenseBasedOnReturnToApproval'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (checkGeneralExpenseBasedOnReturnToApproval) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * To Check General Expense With all the field validations and Note alerts
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 32, groups = {
			"General Expenses" }, description = "178.To Check General Expense With all the field validations and Note alerts")
	public void checkGeneralExpenseWithAllTheFieldValidationsAndNoteAlerts() {
		logs.logging("INFO", "This test covered in 'checkGeneralExpenseBasedOnReturnToApproval'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (checkGeneralExpenseBasedOnReturnToApproval) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * To Check General Expense Based on Cancelling The Document
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 33, groups = {
			"General Expenses" }, description = "178.To Check General Expense Based on Cancelling The Document")
	public void checkGeneralExpenseBasedOnCancellingTheDocument() {
		flag = attendanceLeave.checkGeneralExpenseBasedOnCancellingTheDocument();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Resign/Termination Request Based on Request Date
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 34, groups = {
			"Resignation Request" }, description = "178.To Check Resign/Termination Request Based on Request Date")
	public void CheckResignTerminationRequestBasedOnRequestDate() {
		flag = attendanceLeave.checkResignTerminationRequestBasedOnRequestDate();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Resign/Termination Request Based on Employee Wise
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 35, groups = {
			"Resignation Request" }, description = "178.To Check Resign/Termination Request Based on Employee Wise ")
	public void CheckResignTerminationRequestBasedOnEmployeeWise() {
		logs.logging("INFO", "This test covered in 'CheckResignTerminationRequestBasedOnRequestDate'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (CheckResignTerminationRequestBasedOnRequestDate) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * To Check Resign/Termination Request Find page with all line wise record
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 36, groups = {
			"Resignation Request" }, description = "178.To Check Resign/Termination Request Find page with all line wise record")
	public void CheckResignTerminationRequesFindpagewithAllLineWiseRecord() {
		logs.logging("INFO", "This test covered in 'CheckResignTerminationRequestBasedOnRequestDate'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (CheckResignTerminationRequestBasedOnRequestDate) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * To Check Resign/Termination Request Based on Forward To Approval
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 37, groups = {
			"Resignation Request" }, description = "178.To Check Resign/Termination Request Based on Forward To Approval")
	public void CheckResignTerminationRequestBasedOnForwardToApproval() {
		flag = attendanceLeave.checkResignTerminationRequestBasedOnForwardToApproval();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Resign/Termination Request Based on Employee Pay
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 38, groups = {
			"Resignation Request" }, description = "178.To Check Resign/Termination Request Based on Employee Pay")
	public void CheckResignTerminationRequestBasedOnEmployeePay() {
		flag = attendanceLeave.checkResignTerminationRequestBasedOnEmployeePay();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Resign/Termination Request Based on Adjust Encash Leave
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 39, groups = {
			"Resignation Request" }, description = "178.To Check Resign/Termination Request Based on Adjust Encash Leave")
	public void CheckResignTerminationRequestBasedOnAdjustEncashLeave() {
		flag = attendanceLeave.checkResignTerminationRequestBasedOnAdjustEncashLeave();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Resign/Termination Request Based on Planned Date & Optional date
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 40, groups = {
			"Resignation Request" }, description = "178.To Check Resign/Termination Request Based on Planned Date & Optional date")
	public void CheckResignTerminationRequestBasedOnPlannedDateOptionalDate() {
		logs.logging("INFO", "This test covered in 'CheckResignTerminationRequestBasedOnAdjustEncashLeave'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (CheckResignTerminationRequestBasedOnAdjustEncashLeave) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * To Check Resign/Termination Request Based on Reverse the Resignation Request
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 41, groups = {
			"Resignation Request" }, description = "178.To Check Resign/Termination Request Based on Reverse the Resignation Request")
	public void CheckResignTerminationRequestBasedOnReverseTheResignationRequest() {
		logs.logging("INFO", "This test covered in 'CheckResignTerminationRequestBasedOnAdjustEncashLeave'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (CheckResignTerminationRequestBasedOnAdjustEncashLeave) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * To Check Resign/Termination Request Based on Return To Approval
	 * 
	 * @author Kishore-RMITSv
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 42, groups = {
			"Resignation Request" }, description = "178.To Check Resign/Termination Request Based on Return To Approval")
	public void CheckResignTerminationRequestBasedOnReturnToApproval() {
		flag = attendanceLeave.checkResignTerminationRequestBasedOnReturnToApproval();
		Assert.assertTrue(flag);
	}

	/**
	 * To Check Resign/Termination Request With all the field validations and Note
	 * alerts
	 * 
	 * @author Kishore-RMITS
	 */
	@Author("Kishore-RMITS")
	@Test(priority = 43, groups = {
			"Resignation Request" }, description = "179.To Check Resign/Termination Request With all the field validations and Note alerts")
	public void CheckResignTerminationRequestWithAllTheFieldValidationsAndNoteAlerts() {
		logs.logging("INFO", "This test covered in 'CheckResignTerminationRequestBasedOnReturnToApproval'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO",
					"Previous test (CheckResignTerminationRequestBasedOnReturnToApproval) failed. So this fails too.");
			Assert.fail();
		}
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
			VariableHandler.setCheckEmployeeDataPermissionDefaultExecution(false);
			commonFunc.verifyAfterMethod();
		}
	}
}
