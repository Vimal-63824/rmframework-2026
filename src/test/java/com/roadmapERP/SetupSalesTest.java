package com.roadmapERP;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.supplychainmanagement.SetupSalesFunctions;

public class SetupSalesTest extends RoadMapERPTest {

	private static SetupSalesFunctions SetUpSales = new SetupSalesFunctions();

	/**
	 * Create Catalog Price with Similar Stock and Customer UOM - Dom. Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	@Author("Kowshik-RMITS")
	@Test(priority = 1, groups = { "Setup Sales Screen" }, description = "Sales Master Configurations")
	public void verifySetupSalesCreates() {
		SetUpSales.CreatesSalesSetup();
	}
	
	/**
	 * Verify After Method
	 * 
	 * @author Deepan-GWL
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result, Method method) {
		commonFunc.enterAuthorNamesInExtentReport(method);
	}
}
