package com.roadmapERP;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.StockAdjustmentFunctions;
import utils.WebDriverBase;

public class StockAdjustmentTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static WebDriverBase webDB = new WebDriverBase();
	static StockAdjustmentFunctions stockAdjFunc = new StockAdjustmentFunctions();

	/**
	 * create StockAdjustment
	 * 
	 * @author Harikrishna - RMITS
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 1, groups = { "StockAdjustment" }, description = " Create Stock Adjustment")
	public void CreateStockAdjustmentOB() {
		flag = stockAdjFunc.createStockAdjustmentOB();
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
