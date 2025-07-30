package com.roadmapERP;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks June 2, 2025
 */
import org.testng.Assert;
import org.testng.ITestResult;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import commonfunctions.productionplanning.ProductionOrderFunctions;

public class ProductionOrderTest extends RoadMapERPTest {
	boolean flag = false;
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
	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
	 * Event as OR - Move Type - Single
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 9, groups = {
			"Production Order WithOut Sale Order Reference" }, description = "Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as OR - Move Type - Single")
	public void VerifyPOWithoutSORFirstOutsideOtherInsideWithFoCqRoOrMoveTypeSingle() {
		prodOrderFunc.verifyManualProductionOrderWithoutSoFirstProcessOutsideRemainingInside();
	}

	/**
	 * @author Deepan-GWL
	 */
	@Test(priority = 10, groups = {
			"Production Order WithOut Sale Order Reference" }, description = "Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as CQ - Move type Single")
	public void VerifyPOWithoutSORFirstOutsideOtherInsideWithFoCqRoCqMoveTypeSingle() {
		prodOrderFunc
				.verifyManualProductionOrderWithoutSoFirstProcessOutsideRemainingInsideWithFoCqRoCqMoveTypeSingle();
	}

	/**
	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
	 * Event as OR - Move Type - Multiple
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 11, groups = {
			"Production Order WithOut Sale Order Reference" }, description = "Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as OR - Move Type - Multiple")
	public void VerifyPOWithoutSORFirstOutsideOtherInsideWithFoCqRoOrMoveTypeMultiple() {
		prodOrderFunc
				.verifyManualProductionOrderWithoutSoFirstProcessOutsideRemainingInsideWithFoCqRoOrMoveTypeMultiple();
	}

	/**
	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
	 * Event as CQ - Move type Multiple
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 12, groups = {
			"Production Order WithOut Sale Order Reference" }, description = "Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as CQ - Move type Multiple")
	public void VerifyPOWithoutSORFirstOutsideOtherInsideWithFoCqRoCqMoveTypeMultiple() {
		prodOrderFunc
				.verifyManualProductionOrderWithoutSoFirstProcessOutsideRemainingInsideWithFoCqRoCqMoveTypeMultiple();
	}

	/**
	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
	 * Event as OR - Move Type - Item
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 13, groups = {
			"Production Order WithOut Sale Order Reference" }, description = "Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as OR - Move Type - Item")
	public void VerifyPOWithoutSORFirstOutsideOtherInsideWithFoCqRoOrMoveTypeItem() {
		prodOrderFunc.verifyManualProductionOrderWithoutSoFirstProcessOutsideRemainingInsideWithFoCqRoOrMoveTypeItem();
	}

	/**
	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
	 * Event as CQ - Move type Item
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 14, groups = {
			"Production Order WithOut Sale Order Reference" }, description = "Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as CQ - Move type Item")
	public void VerifyPOWithoutSORFirstOutsideOtherInsideWithFoCqRoCqMoveTypeItem() {
		prodOrderFunc.verifyManualProductionOrderWithoutSoFirstProcessOutsideRemainingInsideWithFoCqRoCqMoveTypeItem();
	}

	/**
	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
	 * Event as CQ - Move type Single
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 15, groups = {
			"Production Order WithOut Sale Order Reference" }, description = "Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as CQ - Move type Single")
	public void VerifyPOWithoutSORFirstOutsideOtherBothWithFoCqRoCqMoveTypeSingle() {
		prodOrderFunc.verifyManualProductionOrderWithoutSoFirstProcessOutsideRemainingBothWithFoCqRoCqMoveTypeSingle();
	}

	/**
	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
	 * Event as CQ - Move type Multiple
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 16, groups = {
			"Production Order WithOut Sale Order Reference" }, description = "Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as CQ - Move type Multiple")
	public void VerifyPOWithoutSORFirstOutsideOtherBothWithFoCqRoCqMoveTypeMultiple() {
		prodOrderFunc
				.verifyManualProductionOrderWithoutSoFirstProcessOutsideRemainingBothWithFoCqRoCqMoveTypeMultiple();
	}

	/**
	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
	 * Event as CQ - Move type Single
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 17, groups = {
			"Production Order WithOut Sale Order Reference" }, description = "Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as CQ - Move type Single")
	public void VerifyPOWithoutSORAllProcessBothWithFoCqRoCqMoveTypeSingle() {
		prodOrderFunc.verifyManualProductionOrderWithoutSoAllProcessBothFOCQRoCqWithSingleType();
	}

	/**
	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
	 * Event as CQ - Move type Multiple
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 18, groups = {
			"Production Order WithOut Sale Order Reference" }, description = "Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as CQ - Move type Multiple")
	public void VerifyPOWithoutSORAllProcessBothWithFoCqRoCqMoveTypeMultiple() {
		prodOrderFunc.verifyManualProductionOrderWithoutSoAllProcessBothFOCQRoCqWithMultipleType();
	}

	/**
	 * How Pend. Prod. Queue (IS) is updated?
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 19, groups = {
			"Pending Production Queue" }, description = "How Pend. Prod. Queue (IS) is updated?")
	public void VerifyManualProductionOrderPendingQueueForInside() {
		prodOrderFunc.verifyManualProductionOrderPendingQueueInsideAndOutside();
		flag = true;
	}

	/**
	 * How Pend. Prod. Queue (OS) is updated?
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 20, groups = {
			"Pending Production Queue" }, description = "How Pend. Prod. Queue (OS) is updated?")
	public void VerifyManualProductionOrderPendingQueueForOutside() {
		assertTrue(flag);
		// This Test Scenarios Covered In
		// VerifyManualProductionOrderPendingQueueForInside
	}

	/**
	 * Verify After Method
	 * 
	 * @author Deepan-GWL
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result) {
		commonFunc.verifyAfterMethod();
	}
}
