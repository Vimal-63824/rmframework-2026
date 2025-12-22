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
	@Test(priority = 9, groups = {
			"Employee Overtime" }, description = "164.To Check Travel Request Based on the adding Additional Travellers")
	public void checkTravelRequestBasedOnTheAddingAdditionalTravellers() {
		// TODO Covered in CheckTravelRequest BasedOnEmployeeWise() Method
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
