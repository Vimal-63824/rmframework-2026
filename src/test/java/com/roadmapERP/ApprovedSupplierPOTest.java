package com.roadmapERP;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
/**
 * @author Deepan-GWL
 * @company Galaxy Weblinks May 20, 2025
 */
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.ApprovedSupplierPOFunctions;
import utils.ReportLoger;

public class ApprovedSupplierPOTest extends RoadMapERPTest {
	private boolean flag = false;
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	static ApprovedSupplierPOFunctions approvedSupplierPOFunc = new ApprovedSupplierPOFunctions();
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
	 * Configuring Approved suppliers for the Item
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 1, groups = {
			"Approved Supplier PO" }, description = "Configuring Approved suppliers for the Item")
	public void VerifyConfigureApprovedSupplierForItem() {
		approvedSupplierPOFunc.verifyCreateApprovedSupplierForCreatedItem();
	}

	/**
	 * Logout The Portal If Testcases Failed
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
