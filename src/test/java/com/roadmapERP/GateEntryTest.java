package com.roadmapERP;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks May 02, 2025
 */
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.GateEntryPageFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class GateEntryTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static GateEntryPageFunctions Gatefunc = new GateEntryPageFunctions();
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
	 * Create gate entry document for purchase Order
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 1, groups = { "Gate Entry" }, description = "Create gate entry document for purchase Order")
	public void VerifyToCreateGateEntryFromPendingPo() {
		Gatefunc.verifyToCreateGateEntryFromPo();
	}

	/**
	 * Create single gate entry document for Multiple Purchase Orders
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 2, groups = {
			"Gate Entry" }, description = "Create single gate entry document for Multiple Purchase Orders ")
	public void VerifyToCreateGateEntryFromPendingMultiplePo() {
		Gatefunc.verifyToCreateGateEntryFromMultiplePo();
	}

	/**
	 * Create single gate entry document for Multiple Supplier Schedule Orders
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 3, groups = {
			"Gate Entry" }, description = "Create single gate entry document for Multiple Supplier Schedule Orders")
	public void VerifyToCreateGateEntryFromMultipleSso() {
		Gatefunc.verifyToCreateGateEntryFromMultipleSso();
	}

	/**
	 * create Gate document for Inwarding materials without PO
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 4, groups = {
			"Gate Entry" }, description = " create Gate document for Inwarding materials without PO")
	public void VerifyToCreateDirectGateEntry() {
		Gatefunc.verifyToCreateDirectGateEntry();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 5, groups = { "GRN" }, description = "Find Gate Entry inward with all possible search parameters")
	public void verifyCreationOfGENFilterAllFieldWorking() {
		Gatefunc.verifyCreationOfGENFilterAllFieldWorking();
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
