package com.roadmapERP;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.DebitNoteFunctions;
import utils.WebDriverBase;

public class DebitNoteTest extends RoadMapERPTest {

	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static WebDriverBase webDB = new WebDriverBase();
	static DebitNoteFunctions DebitNoteFunc = new DebitNoteFunctions();
	private static int previousStatus = ITestResult.SUCCESS;

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
	 * Create Debit Memo With GRN (General)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 1, groups = { "DebitNote" }, description = "      381 - Create Debit Memo With  GRN (General)")
	public void CreateDebitMemoWithGRNGeneral() {
		flag = DebitNoteFunc.createDebitMemoWithGRNGeneral();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Debit Memo With GRN (General)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 2, groups = { "DebitNote" }, description = "      384 - Create Debit Memo without GRN reference")
	public void CreateDebitMemoWithoutGRNreference() {
		flag = DebitNoteFunc.createDebitMemoWithoutGRNreference();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Debit Memo With GRN (General)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 3, groups = { "DebitNote" }, description = "      386 - Cancel Debit Memo document")
	public void CancelDebitMemodocument() {
		flag = DebitNoteFunc.cancelDebitMemodocument();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Debit Memo With GRN (General)
	 * 
	 * @author Vimal - RMITS
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 4, groups = { "DebitNote" }, description = "      385 - Cancel Debit Memo Line")
	public void CancelDebitMemoLine() {
		flag = DebitNoteFunc.cancelDebitMemoLine();
		Assert.assertTrue(flag);
	}

	/**
	 * Verify After Method
	 * 
	 * @author Deepan-GWL
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result, Method method) {
		previousStatus = result.getStatus();
		if (previousStatus == ITestResult.FAILURE || previousStatus == ITestResult.SKIP) {
			commonFunc.verifyAfterMethod();
		}
		commonFunc.enterAuthorNamesInExtentReport(method);
	}
}
