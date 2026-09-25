package com.roadmapERP;

import java.lang.reflect.Method;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks June 2, 2025
 */
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.VariableHandler;
import commonfunctions.productionplanning.ProductionOrderFunctions;

public class ProductionOrderTest extends RoadMapERPTest {
	boolean flag = false;
	private static ProductionOrderFunctions prodOrderFunc = new ProductionOrderFunctions();
	private static int previousStatus = ITestResult.STARTED;

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
	 * How to create Manual Non Standard Production order?
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 1, groups = {
			"Prod Order" }, description = "25 How to create Manual Non Standard Production order?")
	public void VerifyCreateManualNonStandardProductionOrder() {
		flag = prodOrderFunc.verifyCreateManualNonStandardProductionOrder();
		Assert.assertTrue(flag);
	}

	/**
	 * How to create Manual Standard Production order?
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 2, groups = { "Prod Order" }, description = "26 How to create Manual Standard Production order?")
	public void VerifyCreateManualStandardProductionOrder() {
		flag = prodOrderFunc.verifyCreateManualStandardProductionOrder();
		Assert.assertTrue(flag);
	}

	/**
	 * How to create Production order from MRP?
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 3, groups = { "Prod Order" }, description = "27 How to create Production order from MRP?")
	public void VerifyCreateProductionOrderFromMRP() {
		flag = prodOrderFunc.verifyCreateProductionOrderFromMRP();
		Assert.assertTrue(flag);
	}

	/**
	 * How to cancel the Production order?
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 4, groups = { "Prod Order" }, description = "32 How to cancel the Production order?")
	public void VerifyCancelProductionOrder() {
		flag = prodOrderFunc.verifyCancelProductionOrder();
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
