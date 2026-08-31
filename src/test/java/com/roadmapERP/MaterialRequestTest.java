package com.roadmapERP;

import java.lang.reflect.Method;

/**
 * @author Harikrishna - RMITS
 * @Company RoadMap It Solutions October 07, 2025
 */
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonfunctions.supplychainmanagement.MaterialRequestFunctions;
import commonfunctions.supplychainmanagement.SalesOrderFunctions;
import utils.Mailer;
import utils.ReportLoger;
import utils.WebDriverBase;

public class MaterialRequestTest extends RoadMapERPTest {

	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static SalesOrderFunctions SalesOrdFunc = new SalesOrderFunctions();
	public ApprovalPageFunctions approvalPageFunc = new ApprovalPageFunctions();
	public MaterialRequestFunctions materialReqFunc = new MaterialRequestFunctions();
	private static ReportLoger logs = new ReportLoger();
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
	 * Checking The Material Request Find Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 1, groups = { "Material Request" }, description = "Material Request Customer Type")
	public void verifyMRFindScreen() {
		flag = materialReqFunc.createMRCustomerType();
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
