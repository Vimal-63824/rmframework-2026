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

//	/**
//	 * Manual Production order with first Operation MR Event - OR and Rest Op. MR
//	 * Event as OR - Move Type - Single
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 5, groups = {
//			"Prod Order" }, description = "33 Manual Production order with first Operation MR Event - OR and Rest Op. MR Event as OR - Move Type - Single")
//	public void VerifyProdOrderWithoutSOAllInsideOperORORMoveTypeSingle() {
//		flag = prodOrderFunc.verifyProdOrderWithoutSOAllInsideOperORORMoveTypeSingle();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - OR and Rest Op. MR
//	 * Event as CQ - Move Type - Single
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 6, groups = {
//			"Prod Order" }, description = "34 Manual Production order with first Operation MR Event - OR and Rest Op. MR Event as CQ - Move Type - Single")
//	public void VerifyProdOrderWithoutSOAllInsideOperORCQMoveTypeSingle() {
//		flag = prodOrderFunc.verifyProdOrderWithoutSOAllInsideOperORCQMoveTypeSingle();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
//	 * Event as OR - Move Type - Single
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 7, groups = {
//			"Prod Order" }, description = "35 Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as OR - Move Type - Single")
//	public void VerifyProdOrderWithoutSOAllInsideOperCQORMoveTypeSingle() {
//		flag = prodOrderFunc.verifyProdOrderWithoutSOAllInsideOperCQORMoveTypeSingle();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
//	 * Event as CQ - Move type Single
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 8, groups = {
//			"Prod Order" }, description = "36 Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as CQ - Move type Single")
//	public void VerifyProdOrderWithoutSOAllInsideOperCQCQMoveTypeSingle() {
//		flag = prodOrderFunc.verifyProdOrderWithoutSOAllInsideOperCQCQMoveTypeSingle();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
//	 * Event as OR - Move Type - Single
//	 * 
//	 * @author Deepan-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 9, groups = {
//			"Production Order WithOut Sale Order Reference" }, description = "Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as OR - Move Type - Single")
//	public void VerifyPOWithoutSORFirstOutsideOtherInsideWithCQORMoveTypeSingle() {
//		prodOrderFunc.verifyManualProductionOrderWithoutSoFirstProcessOutsideRemainingInsideCQOR();
//	}
//
//	/**
//	 * @author Deepan-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 10, groups = {
//			"Production Order WithOut Sale Order Reference" }, description = "Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as CQ - Move type Single")
//	public void VerifyPOWithoutSORFirstOutsideOtherInsideWithCQCQMoveTypeSingle() {
//		prodOrderFunc.verifyManualProductionOrderWithoutSoFirstProcessOutsideRemainingInsideWithCQCQMoveTypeSingle();
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
//	 * Event as OR - Move Type - Multiple
//	 * 
//	 * @author Deepan-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 11, groups = {
//			"Production Order WithOut Sale Order Reference" }, description = "Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as OR - Move Type - Multiple")
//	public void VerifyPOWithoutSORFirstOutsideOtherInsideWithFoCqRoOrMoveTypeMultiple() {
//		prodOrderFunc
//				.verifyManualProductionOrderWithoutSoFirstProcessOutsideRemainingInsideWithFoCqRoOrMoveTypeMultiple();
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
//	 * Event as CQ - Move type Multiple
//	 * 
//	 * @author Deepan-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 12, groups = {
//			"Production Order WithOut Sale Order Reference" }, description = "Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as CQ - Move type Multiple")
//	public void VerifyPOWithoutSORFirstOutsideOtherInsideWithFoCqRoCqMoveTypeMultiple() {
//		prodOrderFunc
//				.verifyManualProductionOrderWithoutSoFirstProcessOutsideRemainingInsideWithFoCqRoCqMoveTypeMultiple();
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
//	 * Event as OR - Move Type - Item
//	 * 
//	 * @author Deepan-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 13, groups = {
//			"Production Order WithOut Sale Order Reference" }, description = "Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as OR - Move Type - Item")
//	public void VerifyPOWithoutSORFirstOutsideOtherInsideWithFoCqRoOrMoveTypeItem() {
//		prodOrderFunc.verifyManualProductionOrderWithoutSoFirstProcessOutsideRemainingInsideWithFoCqRoOrMoveTypeItem();
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
//	 * Event as CQ - Move type Item
//	 * 
//	 * @author Deepan-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 14, groups = {
//			"Production Order WithOut Sale Order Reference" }, description = "Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as CQ - Move type Item")
//	public void VerifyPOWithoutSORFirstOutsideOtherInsideWithFoCqRoCqMoveTypeItem() {
//		prodOrderFunc.verifyManualProductionOrderWithoutSoFirstProcessOutsideRemainingInsideWithFoCqRoCqMoveTypeItem();
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
//	 * Event as CQ - Move type Single
//	 * 
//	 * @author Deepan-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 15, groups = {
//			"Production Order WithOut Sale Order Reference" }, description = "Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as CQ - Move type Single")
//	public void VerifyPOWithoutSORFirstOutsideOtherBothWithCQCQMoveTypeSingle() {
//		prodOrderFunc.verifyManualProductionOrderWithoutSoFirstProcessOutsideRemainingBothWithCQCQMoveTypeSingle();
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
//	 * Event as CQ - Move type Multiple
//	 * 
//	 * @author Deepan-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 16, groups = {
//			"Production Order WithOut Sale Order Reference" }, description = "Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as CQ - Move type Multiple")
//	public void VerifyPOWithoutSORFirstOutsideOtherBothWithFoCqRoCqMoveTypeMultiple() {
//		prodOrderFunc
//				.verifyManualProductionOrderWithoutSoFirstProcessOutsideRemainingBothWithFoCqRoCqMoveTypeMultiple();
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
//	 * Event as CQ - Move type Single
//	 * 
//	 * @author Deepan-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 17, groups = {
//			"Production Order WithOut Sale Order Reference" }, description = "Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as CQ - Move type Single")
//	public void VerifyPOWithoutSORAllProcessBothWithFoCQCQMoveTypeSingle() {
//		prodOrderFunc.verifyManualProductionOrderWithoutSoAllProcessBothCQCQWithSingleType();
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
//	 * Event as CQ - Move type Multiple
//	 * 
//	 * @author Deepan-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 18, groups = {
//			"Production Order WithOut Sale Order Reference" }, description = "Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as CQ - Move type Multiple")
//	public void VerifyPOWithoutSORAllProcessBothWithFoCqRoCqMoveTypeMultiple() {
//		prodOrderFunc.verifyManualProductionOrderWithoutSoAllProcessBothFOCQRoCqWithMultipleType();
//	}
//
//	/**
//	 * How Pend. Prod. Queue (IS) is updated?
//	 * 
//	 * @author Deepan-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 19, groups = {
//			"Pending Production Queue" }, description = "How Pend. Prod. Queue (IS) is updated?")
//	public void VerifyManualProductionOrderPendingQueueForInside() {
//		flag = prodOrderFunc.verifyManualProductionOrderPendingQueueInsideAndOutside();
//		assertTrue(flag);
//	}
//
//	/**
//	 * How Pend. Prod. Queue (OS) is updated?
//	 * 
//	 * @author Deepan-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 20, groups = {
//			"Pending Production Queue" }, description = "How Pend. Prod. Queue (OS) is updated?")
//	public void VerifyManualProductionOrderPendingQueueForOutside() {
//		assertTrue(flag);
//		// This Test Scenarios Covered In
//		// VerifyManualProductionOrderPendingQueueForInside
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - OR and Rest Op. MR
//	 * Event as OR - Move Type - Multiple
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 21, groups = {
//			"Prod Order" }, description = "37 Manual Production order with first Operation MR Event - OR and Rest Op. MR Event as OR - Move Type - Multiple")
//	public void VerifyProdOrderWithoutSOAllInsideOperORORMoveTypeMultiple() {
//		flag = prodOrderFunc.verifyProdOrderWithoutSOAllInsideOperORORMoveTypeMultiple();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - OR and Rest Op. MR
//	 * Event as CQ - Move Type - Multiple
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 22, groups = {
//			"Prod Order" }, description = "38 Manual Production order with first Operation MR Event - OR and Rest Op. MR Event as CQ - Move Type - Multiple")
//	public void VerifyProdOrderWithoutSOAllInsideOperORCQMoveTypeMultiple() {
//		flag = prodOrderFunc.verifyProdOrderWithoutSOAllInsideOperORCQMoveTypeMultiple();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
//	 * Event as OR - Move Type - Multiple
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 23, groups = {
//			"Prod Order" }, description = "39 Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as OR - Move Type - Multiple")
//	public void VerifyProdOrderWithoutSOAllInsideOperCQORMoveTypeMultiple() {
//		flag = prodOrderFunc.verifyProdOrderWithoutSOAllInsideOperCQORMoveTypeMultiple();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
//	 * Event as CQ - Move type Multiple
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 24, groups = {
//			"Prod Order" }, description = "40 Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as CQ - Move type Multiple")
//	public void VerifyProdOrderWithoutSOAllInsideOperCQCQMoveTypeMultiple() {
//		flag = prodOrderFunc.verifyProdOrderWithoutSOAllInsideOperCQCQMoveTypeMultiple();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - OR and Rest Op. MR
//	 * Event as OR - Move Type - Item
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 25, groups = {
//			"Prod Order" }, description = "41 Manual Production order with first Operation MR Event - OR and Rest Op. MR Event as OR - Move Type - Item")
//	public void VerifyProdOrderWithoutSOAllInsideOperORORMoveTypeItem() {
//		flag = prodOrderFunc.verifyProdOrderWithoutSOAllInsideOperORORMoveTypeItem();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - OR and Rest Op. MR
//	 * Event as CQ - Move Type - Item
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 26, groups = {
//			"Prod Order" }, description = "42 Manual Production order with first Operation MR Event - OR and Rest Op. MR Event as CQ - Move Type - Item")
//	public void VerifyProdOrderWithoutSOAllInsideOperORCQMoveTypeItem() {
//		flag = prodOrderFunc.verifyProdOrderWithoutSOAllInsideOperORCQMoveTypeItem();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
//	 * Event as OR - Move Type - Item
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 27, groups = {
//			"Prod Order" }, description = "43 Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as OR - Move Type - Item")
//	public void VerifyProdOrderWithoutSOAllInsideOperCQORMoveTypeItem() {
//		flag = prodOrderFunc.verifyProdOrderWithoutSOAllInsideOperCQORMoveTypeItem();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
//	 * Event as CQ - Move type Item
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 28, groups = {
//			"Prod Order" }, description = "44 Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as CQ - Move type Item")
//	public void VerifyProdOrderWithoutSOAllInsideOperCQCQMoveTypeItem() {
//		flag = prodOrderFunc.verifyProdOrderWithoutSOAllInsideOperCQCQMoveTypeItem();
//		Assert.assertTrue(flag);
//	}
//
//	//-----------New Scenarios------------
//
//	/**
//	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
//	 * Event as CQ - Move type Single
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 29, groups = {
//			"Prod Order" }, description = "51 Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as CQ - Move type Single")
//	public void VerifyProdOrderWithoutSOAllOutsideOperCQCQMoveTypeSingle() {
//		flag = prodOrderFunc.verifyProdOrderWithoutSOAllOutsideOperCQCQMoveTypeSingle();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - OR and Rest Op. MR
//	 * Event as OR - Move Type - Single
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 30, groups = {
//			"Prod Order" }, description = "151 Manual Production order with first Operation MR Event - OR and Rest Op. MR Event as OR - Move Type - Single")
//	public void verifyManualProdOrderWithSOAllInsideORORMoveTypeSingle() {
//		flag = prodOrderFunc.verifyManualProdOrderWithSOAllInsideORORMoveTypeSingle();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - OR and Rest Op. MR
//	 * Event as CQ - Move Type - Single
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 31, groups = {
//			"Prod Order" }, description = "152 Manual Production order with first Operation MR Event - OR and Rest Op. MR Event as CQ - Move Type - Single")
//	public void verifyManualProdOrderWithSOAllInsideORCQMoveTypeSingle() {
//		flag = prodOrderFunc.verifyManualProdOrderWithSOAllInsideORCQMoveTypeSingle();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
//	 * Event as OR - Move Type - Single
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 32, groups = {
//			"Prod Order" }, description = "153 Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as OR - Move Type - Single")
//	public void verifyManualProdOrderWithSOAllInsideCQORMoveTypeSingle() {
//		flag = prodOrderFunc.verifyManualProdOrderWithSOAllInsideCQORMoveTypeSingle();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
//	 * Event as CQ - Move Type - Single
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 33, groups = {
//			"Prod Order" }, description = "154 Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as CQ - Move Type - Single")
//	public void verifyManualProdOrderWithSOAllInsideCQCQMoveTypeSingle() {
//		flag = prodOrderFunc.verifyManualProdOrderWithSOAllInsideCQCQMoveTypeSingle();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - OR and Rest Op. MR
//	 * Event as CQ - Move Type - Single
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 34, groups = {
//			"Prod Order" }, description = "61 Manual Production order with first Operation MR Event - OR and Rest Op. MR Event as CQ - Move Type - Single")
//	public void VerifyProdOrderWithoutSOFirstInsideRestOutsideOperORCQMoveTypeSingle() {
//		flag = prodOrderFunc.verifyProdOrderWithoutSOFirstInsideRestOutsideOperORCQMoveTypeSingle();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
//	 * Event as CQ - Move type Single
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 35, groups = {
//			"Prod Order" }, description = "63 Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as CQ - Move type Single")
//	public void VerifyProdOrderWithoutSOFirstInsideRestOutsideOperCQCQMoveTypeSingle() {
//		flag = prodOrderFunc.verifyProdOrderWithoutSOFirstInsideRestOutsideOperCQCQMoveTypeSingle();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
//	 * Event as CQ - Move type Single
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 36, groups = {
//			"Prod Order" }, description = "166 Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as CQ - Move type Single")
//	public void VerifyManualProdOrderWithSOAllOutsideCQCQMoveTypeSingle() {
//		flag = prodOrderFunc.verifyManualProdOrderWithSOAllOutsideCQCQMoveTypeSingle();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - OR and Rest Op. MR
//	 * Event as CQ - Move Type - Single
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 37, groups = {
//			"Prod Order" }, description = "176 Manual Production order with first Operation MR Event - OR and Rest Op. MR Event as CQ - Move Type - Single")
//	public void verifyManualProdOrderWithSOFirstInsideRestOutsideORCQMoveTypeSingle() {
//		flag = prodOrderFunc.verifyManualProdOrderWithSOFirstInsideRestOutsideORCQMoveTypeSingle();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
//	 * Event as CQ - Move Type - Single
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 38, groups = {
//			"Prod Order" }, description = "177 Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as CQ - Move Type - Single")
//	public void verifyManualProdOrderWithSOFirstInsideRestOutsideCQCQMoveTypeSingle() {
//		flag = prodOrderFunc.verifyManualProdOrderWithSOFirstInsideRestOutsideCQCQMoveTypeSingle();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
//	 * Event as OR - Move Type - Single
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 39, groups = {
//			"Prod Order" }, description = "189 Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as OR - Move Type - Single")
//	public void VerifyManualProdOrderWithSOFirstOutsideRestInsideCQORMoveTypeSingle() {
//		flag = prodOrderFunc.verifyManualProdOrderWithSOFirstOutsideRestInsideCQORMoveTypeSingle();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
//	 * Event as CQ - Move Type - Single
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 40, groups = {
//			"Prod Order" }, description = "190 Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as CQ - Move Type - Single")
//	public void VerifyManualProdOrderWithSOFirstOutsideRestInsideCQCQMoveTypeSingle() {
//		flag = prodOrderFunc.verifyManualProdOrderWithSOFirstOutsideRestInsideCQCQMoveTypeSingle();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
//	 * Event as OR - Move Type - Single
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 41, groups = {
//			"Prod Order" }, description = "190 Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as OR - Move Type - Single")
//	public void VerifyManualProdOrderWithSOFirstOutsideRestBothCQORMoveTypeSingle() {
//		flag = prodOrderFunc.verifyManualProdOrderWithSOFirstOutsideRestBothCQORMoveTypeSingle();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
//	 * Event as CQ - Move Type - Single
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 42, groups = {
//			"Prod Order" }, description = "190 Manual Production order with first Operation MR Event - CQ and Rest Op. MR Event as CQ - Move Type - Single")
//	public void VerifyManualProdOrderWithSOFirstOutsideRestBothCQCQMoveTypeSingle() {
//		flag = prodOrderFunc.verifyManualProdOrderWithSOFirstOutsideRestBothCQCQMoveTypeSingle();
//		Assert.assertTrue(flag);
//	}

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
