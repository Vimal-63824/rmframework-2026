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
import commonfunctions.supplychainmanagement.SubcontractSetUpFunctions;
import utils.WebDriverBase;

public class SubcontractSetUpTest extends RoadMapERPTest {

	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static WebDriverBase webDB = new WebDriverBase();
	static SubcontractSetUpFunctions SubcontractSetupFunc = new SubcontractSetUpFunctions();
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
	 * Create Open Subcontract Order (Rate Contract) for an Item with Type Supplier
	 * +Item + Process
	 * 
	 * @author Vimal - RMITS @
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 1, groups = {
			"Open SCO" }, description = "Create Open Subcontract Order (Rate Contract) for an Item with Type Supplier +Item + Process")
	public void creatingOpenSCOpriceForSupplier_Item_Process() {
		flag = SubcontractSetupFunc.creatingOpenSCOpriceForSupplier_Item_Process();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Open Subcontract Order (Rate Contract) for an Item with Type Item +
	 * Process
	 * 
	 * @author Vimal - RMITS @
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 2, groups = {
			"Open SCO" }, description = "Create Open Subcontract Order (Rate Contract) for an Item with Type Item + Process")
	public void creatingOpenSCOpriceForItem_Process() {
		flag = SubcontractSetupFunc.creatingOpenSCOpriceForItem_Process();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Open Subcontract Order (Rate Contract) for an Item with Type Supplier
	 * + Process
	 * 
	 * @author Vimal - RMITS @
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 3, groups = {
			"Open SCO" }, description = "Create Open Subcontract Order (Rate Contract) for an Item with Type Supplier + Process")
	public void creatingOpenSCOpriceForSupplier_Process() {
		flag = SubcontractSetupFunc.creatingOpenSCOpriceForSupplier_Process();
		Assert.assertTrue(flag);
	}

	/**
	 * Create Open Subcontract Order (Rate Contract) for an Item with Type Process
	 * 
	 * @author Vimal - RMITS @
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 4, groups = {
			"Open SCO" }, description = "Create Open Subcontract Order (Rate Contract) for an Item with Type Process")
	public void creatingOpenSCOpriceForProcess() {
		flag = SubcontractSetupFunc.creatingOpenSCOpriceForProcess();
		Assert.assertTrue(flag);
	}

	/**
	 * Revise The (Rate Contract) for an Item with Type Process
	 * 
	 * @author Vimal - RMITS @
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 5, groups = {
			"Open SCO" }, description = "Revise The (Rate Contract) for an Item with Type Process")
	public void reversingTheProcessTypeDocument() {
		flag = SubcontractSetupFunc.reversingTheProcessTypeDocument();
		Assert.assertTrue(flag);
	}

	/**
	 * Revise The (Rate Contract) for an Item with Type Item + Process
	 * 
	 * @author Vimal - RMITS @
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 6, groups = {
			"Open SCO" }, description = "Revise The (Rate Contract) for an Item with Type Item + Process")
	public void reveringItem_ProcessTypeDocument() {
		flag = SubcontractSetupFunc.reveringItem_ProcessTypeDocument();
		Assert.assertTrue(flag);
	}

	/**
	 * Revise The (Rate Contract) for an Item with Type Supplier +Item + Process
	 * 
	 * @author Vimal - RMITS @
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 7, groups = {
			"Open SCO" }, description = "Revise The (Rate Contract) for an Item with Type Supplier +Item + Process")
	public void reveringSupplier_Item_ProcessTypeDocument() {
		flag = SubcontractSetupFunc.reveringSupplier_Item_ProcessTypeDocument();
		Assert.assertTrue(flag);
	}

	/**
	 * Revise The (Rate Contract) for an Item with Type Supplier + Process
	 * 
	 * @author Vimal - RMITS @
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 8, groups = {
			"Open SCO" }, description = "Revise The (Rate Contract) for an Item with Type Supplier + Process")
	public void reveringSupplier_Process_TypeDocument() {
		flag = SubcontractSetupFunc.reveringSupplier_Process_TypeDocument();
		Assert.assertTrue(flag);
	}

	/**
	 * Cancelled The (Rate Contract) for an Item with Type Process
	 * 
	 * @author Vimal - RMITS @
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 9, groups = {
			"Open SCO" }, description = "Cancelled The (Rate Contract) for an Item with Type Process")
	public void creatingOpenSCOpriceForProcessTypeAndCancelledTheDocument() {
		flag = SubcontractSetupFunc.creatingOpenSCOpriceForProcessTypeAndCancelledTheDocument();
		Assert.assertTrue(flag);
	}

	/**
	 * Cancelled The (Rate Contract) for an Item with Type Supplier +Item + Process
	 * 
	 * @author Vimal - RMITS @
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 10, groups = {
			"Open SCO" }, description = "Cancelled The (Rate Contract) for an Item with Type Supplier +Item + Process")
	public void creatingOpenSCOpriceForSupplier_Item_ProcessTypeAndCancelledTheDocument() {
		flag = SubcontractSetupFunc.creatingOpenSCOpriceForSupplier_Item_ProcessTypeAndCancelledTheDocument();
		Assert.assertTrue(flag);
	}

	/**
	 * Cancelled The (Rate Contract) for an Item with Type Item + Process
	 * 
	 * @author Vimal - RMITS @
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 11, groups = {
			"Open SCO" }, description = "Cancelled The (Rate Contract) for an Item with Type Item + Process")
	public void creatingOpenSCOpriceForItem_ProcessTypeAndCancelledTheDocument() {
		flag = SubcontractSetupFunc.creatingOpenSCOpriceForItem_ProcessTypeAndCancelledTheDocument();
		Assert.assertTrue(flag);
	}

	/**
	 * Cancelled The (Rate Contract) for an Item with Type Supplier + Process
	 * 
	 * @author Vimal - RMITS @
	 */
	@Author("Vimal-RMITS")
	@Test(priority = 12, groups = {
			"Open SCO" }, description = "Cancelled The (Rate Contract) for an Item with Type Supplier + Process")
	public void creatingOpenSCOpriceForSupplier_ProcessTypeAndCancelledTheDocument() {
		flag = SubcontractSetupFunc.creatingOpenSCOpriceForSupplier_ProcessTypeAndCancelledTheDocument();
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
