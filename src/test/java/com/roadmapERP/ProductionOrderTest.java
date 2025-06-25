package com.roadmapERP;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks June 2, 2025
 */
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import commonfunctions.CommonFunctions;
import commonfunctions.productionplanning.ProductionOrderFunctions;

public class ProductionOrderTest extends RoadMapERPTest {
	boolean flag = false;
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static ProductionOrderFunctions prodOrderFunc = new ProductionOrderFunctions();

	/**
	 * How to create Manual Non Standard Production order?
	 * 
	 * @author Sakthi-GWL
	 */
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
	@Test(priority = 4, groups = { "Prod Order" }, description = "32 How to cancel the Production order?")
	public void VerifyCancelProductionOrder() {
		flag = prodOrderFunc.verifyCancelProductionOrder();
		Assert.assertTrue(flag);
	}

	/**
	 * Manual Production order with first Operation MR Event - OR and Rest Op. MR
	 * Event as OR - Move Type - Single
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 5, groups = {
			"Prod Order" }, description = "33 Manual Production order with first Operation MR Event - OR and Rest Op. MR Event as OR - Move Type - Single")
	public void VerifyProdOrderWithoutSOAllInsideOperORORMoveTypeSingle() {
		flag = prodOrderFunc.verifyProdOrderWithoutSOAllInsideOperORORMoveTypeSingle();
		Assert.assertTrue(flag);
	}

	/**
	 * Manual Production order with first Operation MR Event - OR and Rest Op. MR
	 * Event as CQ - Move Type - Single
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 6, groups = {
			"Prod Order" }, description = "34 Manual Production order with first Operation MR Event - OR and Rest Op. MR Event as CQ - Move Type - Single")
	public void VerifyProdOrderWithoutSOAllInsideOperORCQMoveTypeSingle() {
		flag = prodOrderFunc.verifyProdOrderWithoutSOAllInsideOperORCQMoveTypeSingle();
		Assert.assertTrue(flag);
	}

	/**
	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
	 * Event as OR - Move Type - Single
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 7, groups = {
			"Prod Order" }, description = "35 Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as OR - Move Type - Single")
	public void VerifyProdOrderWithoutSOAllInsideOperCQORMoveTypeSingle() {
		flag = prodOrderFunc.verifyProdOrderWithoutSOAllInsideOperCQORMoveTypeSingle();
		Assert.assertTrue(flag);
	}

	/**
	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
	 * Event as CQ - Move type Single
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 8, groups = {
			"Prod Order" }, description = "36 Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as CQ - Move type Single")
	public void VerifyProdOrderWithoutSOAllInsideOperCQCQMoveTypeSingle() {
		flag = prodOrderFunc.verifyProdOrderWithoutSOAllInsideOperCQCQMoveTypeSingle();
		Assert.assertTrue(flag);
	}

	/**
	 * Logout The Portal If Testcases Failed
	 * 
	 * @author Sakthi-GWL
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			commonFunc.verifyAfterMethod();
		}
	}
}
