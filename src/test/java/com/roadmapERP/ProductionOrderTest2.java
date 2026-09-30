package com.roadmapERP;

import java.lang.reflect.Method;
/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks May 12, 2026
 */
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import commonfunctions.Author;
import commonfunctions.VariableHandler;
import commonfunctions.productionplanning.ProductionOrderFunctions;

public class ProductionOrderTest2 extends RoadMapERPTest {
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
	 * Manual production order without Sales Order reference (ALL PROCESS - INSIDE)
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 1, groups = {
			"Prod Order" }, description = "43 Manual Production order with Move Type - Single (ALL INSIDE)")
	public void VerifyProdOrderWithoutSOAllInsideMoveTypeSingle() {
		flag = prodOrderFunc.verifyProdOrderWithoutSOAllInsideMoveTypeSingle();
		Assert.assertTrue(flag);
	}

	/**
	 * Manual production order without Sales Order reference (ALL PROCESS - INSIDE)
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 2, groups = {
			"Prod Order" }, description = "44 Manual Production order with Move Type - Multiple (ALL INSIDE)")
	public void VerifyProdOrderWithoutSOAllInsideMoveTypeMultiple() {
		flag = prodOrderFunc.verifyProdOrderWithoutSOAllInsideMoveTypeMultiple();
		Assert.assertTrue(flag);
	}

	/**
	 * Manual production order without Sales Order reference (ALL PROCESS - INSIDE)
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 3, groups = {
			"Prod Order" }, description = "45 Manual Production order with Move Type - Item (ALL INSIDE)")
	public void VerifyProdOrderWithoutSOAllInsideMoveTypeItem() {
		flag = prodOrderFunc.verifyProdOrderWithoutSOAllInsideMoveTypeItem();
		Assert.assertTrue(flag);
	}

	/**
	 * Manual production order without Sales Order reference (ALL PROCESS - OUTSIDE)
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 4, groups = {
			"Prod Order" }, description = "46 Manual Production order with Move Type - Single (ALL OUTSIDE)")
	public void VerifyProdOrderWithoutSOAllOutsideMoveTypeSingle() {
		flag = prodOrderFunc.verifyProdOrderWithoutSOAllOutsideMoveTypeSingle();
		Assert.assertTrue(flag);
	}

	/**
	 * Manual production order without Sales Order reference (ALL PROCESS - OUTSIDE)
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 5, groups = {
			"Prod Order" }, description = "47 Manual Production order with Move Type - Multiple (ALL OUTSIDE)")
	public void VerifyProdOrderWithoutSOAllOutsideMoveTypeMultiple() {
		flag = prodOrderFunc.verifyProdOrderWithoutSOAllOutsideMoveTypeMultiple();
		Assert.assertTrue(flag);
	}

	/**
	 * Manual production order without Sales Order reference (ALL PROCESS - OUTSIDE)
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 6, groups = {
			"Prod Order" }, description = "48 Manual Production order with Move Type - Item (ALL OUTSIDE)")
	public void VerifyProdOrderWithoutSOAllOutsideMoveTypeItem() {
		flag = prodOrderFunc.verifyProdOrderWithoutSOAllOutsideMoveTypeItem();
		Assert.assertTrue(flag);
	}

	/**
	 * Manual production order Without Sales Order Reference(First Process - Inside
	 * - subsequent process- Outside)
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 7, groups = {
			"Prod Order" }, description = "49 Manual Production order with Move Type - Single (FIRST IN REST OUT)")
	public void VerifyProdOrderWithoutSOFirstInsideRestOutsideMoveTypeSingle() {
		flag = prodOrderFunc.verifyProdOrderWithoutSOFirstInsideRestOutsideMoveTypeSingle();
		Assert.assertTrue(flag);
	}

	/**
	 * Manual production order Without Sales Order Reference(First Process - Inside
	 * - subsequent process- Outside)
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 8, groups = {
			"Prod Order" }, description = "50 Manual Production order with Move Type - Multiple (FIRST IN REST OUT)")
	public void VerifyProdOrderWithoutSOFirstInsideRestOutsideMoveTypeMultiple() {
		flag = prodOrderFunc.verifyProdOrderWithoutSOFirstInsideRestOutsideMoveTypeMultiple();
		Assert.assertTrue(flag);
	}

	/**
	 * Manual production order Without Sales Order Reference(First Process - Inside
	 * - subsequent process- Outside)
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 9, groups = {
			"Prod Order" }, description = "51 Manual Production order with Move Type - Item (FIRST IN REST OUT)")
	public void VerifyProdOrderWithoutSOFirstInsideRestOutsideMoveTypeItem() {
		flag = prodOrderFunc.verifyProdOrderWithoutSOFirstInsideRestOutsideMoveTypeItem();
		Assert.assertTrue(flag);
	}

	/**
	 * Manual production order Without Sales Order Reference(First Process - Outside
	 * - subsequent process- Inside)
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 10, groups = {
			"Prod Order" }, description = "52 Manual Production order with Move Type - Single (FIRST OUT REST IN)")
	public void VerifyProdOrderWithoutSOFirstOutsideRestInsideMoveTypeSingle() {
		flag = prodOrderFunc.verifyProdOrderWithoutSOFirstOutsideRestInsideMoveTypeSingle();
		Assert.assertTrue(flag);
	}

	/**
	 * Manual production order Without Sales Order Reference(First Process - Outside
	 * - subsequent process- Inside)
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 11, groups = {
			"Prod Order" }, description = "53 Manual Production order with Move Type - Multiple (FIRST OUT REST IN)")
	public void VerifyProdOrderWithoutSOFirstOutsideRestInsideMoveTypeMultiple() {
		flag = prodOrderFunc.verifyProdOrderWithoutSOFirstOutsideRestInsideMoveTypeMultiple();
		Assert.assertTrue(flag);
	}

	/**
	 * Manual production order Without Sales Order Reference(First Process - Outside
	 * - subsequent process- Inside)
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 12, groups = {
			"Prod Order" }, description = "54 Manual Production order with Move Type - Item (FIRST OUT REST IN)")
	public void VerifyProdOrderWithoutSOFirstOutsideRestInsideMoveTypeItem() {
		flag = prodOrderFunc.verifyProdOrderWithoutSOFirstOutsideRestInsideMoveTypeItem();
		Assert.assertTrue(flag);
	}

//	/**
//	 * Manual production order Without Sales Order reference (First Process -
//	 * Outside- subsequent process- Inside / Outside(Comb. of Both))
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 13, groups = {
//			"Prod Order" }, description = "55 Manual Production order with Move Type - Single (FIRST OUT REST BOTH)")
//	public void VerifyProdOrderWithoutSOFirstOutsideRestBothMoveTypeSingle() {
//		flag = prodOrderFunc.verifyProdOrderWithoutSOFirstOutsideRestBothMoveTypeSingle();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual production order Without Sales Order reference (First Process -
//	 * Outside- subsequent process- Inside / Outside(Comb. of Both))
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 14, groups = {
//			"Prod Order" }, description = "56 Manual Production order with Move Type - Multiple (FIRST OUT REST BOTH)")
//	public void VerifyProdOrderWithoutSOFirstOutsideRestBothMoveTypeMultiple() {
//		flag = prodOrderFunc.verifyProdOrderWithoutSOFirstOutsideRestBothMoveTypeMultiple();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual production order Without Sales Order reference (First Process -
//	 * Outside- subsequent process- Inside / Outside(Comb. of Both))
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 15, groups = {
//			"Prod Order" }, description = "57 Manual Production order with Move Type - Item (FIRST OUT REST BOTH)")
//	public void VerifyProdOrderWithoutSOFirstOutsideRestBothMoveTypeItem() {
//		flag = prodOrderFunc.verifyProdOrderWithoutSOFirstOutsideRestBothMoveTypeItem();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual production order Without Sales Order reference (Both - Partial Qty
//	 * Inside / Partial Outside)
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 16, groups = {
//			"Prod Order" }, description = "58 Manual Production order with Move Type - Single (ALL BOTH PARTIAL QTY)")
//	public void VerifyProdOrderWithoutSOAllBothPartialQuantityMoveTypeSingle() {
//		flag = prodOrderFunc.verifyProdOrderWithoutSOAllBothPartialQuantityMoveTypeSingle();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual production order Without Sales Order reference (Both - Partial Qty
//	 * Inside / Partial Outside)
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 17, groups = {
//			"Prod Order" }, description = "59 Manual Production order with Move Type - Multiple (ALL BOTH PARTIAL QTY)")
//	public void VerifyProdOrderWithoutSOAllBothPartialQuantityMoveTypeMultiple() {
//		flag = prodOrderFunc.verifyProdOrderWithoutSOAllBothPartialQuantityMoveTypeMultiple();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual production order Without Sales Order reference (Both - Partial Qty
//	 * Inside / Partial Outside)
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 18, groups = {
//			"Prod Order" }, description = "60 Manual Production order with Move Type - Item (ALL BOTH PARTIAL QTY)")
//	public void VerifyProdOrderWithoutSOAllBothPartialQuantityMoveTypeItem() {
//		flag = prodOrderFunc.verifyProdOrderWithoutSOAllBothPartialQuantityMoveTypeItem();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual production order without Sales Order reference (ALL PROCESS - INSIDE)
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 19, groups = {
//			"Prod Order" }, description = "63 Manual Production order with Move Type - Single (ALL INSIDE)")
//	public void VerifyProdOrderAgainstSOAllInsideMoveTypeSingle() {
//		flag = prodOrderFunc.verifyProdOrderAgainstSOAllInsideMoveTypeSingle();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual production order without Sales Order reference (ALL PROCESS - INSIDE)
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 20, groups = {
//			"Prod Order" }, description = "64 Manual Production order with Move Type - Multiple (ALL INSIDE)")
//	public void VerifyProdOrderAgainstSOAllInsideMoveTypeMultiple() {
//		flag = prodOrderFunc.verifyProdOrderAgainstSOAllInsideMoveTypeMultiple();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual production order without Sales Order reference (ALL PROCESS - INSIDE)
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 21, groups = {
//			"Prod Order" }, description = "65 Manual Production order with Move Type - Item (ALL INSIDE)")
//	public void VerifyProdOrderAgainstSOAllInsideMoveTypeItem() {
//		flag = prodOrderFunc.verifyProdOrderAgainstSOAllInsideMoveTypeItem();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual production order without Sales Order reference (ALL PROCESS - OUTSIDE)
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 22, groups = {
//			"Prod Order" }, description = "66 Manual Production order with Move Type - Single (ALL OUTSIDE)")
//	public void VerifyProdOrderAgainstSOAllOutsideMoveTypeSingle() {
//		flag = prodOrderFunc.verifyProdOrderAgainstSOAllOutsideMoveTypeSingle();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual production order without Sales Order reference (ALL PROCESS - OUTSIDE)
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 23, groups = {
//			"Prod Order" }, description = "67 Manual Production order with Move Type - Multiple (ALL OUTSIDE)")
//	public void VerifyProdOrderAgainstSOAllOutsideMoveTypeMultiple() {
//		flag = prodOrderFunc.verifyProdOrderAgainstSOAllOutsideMoveTypeMultiple();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual production order without Sales Order reference (ALL PROCESS - OUTSIDE)
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 24, groups = {
//			"Prod Order" }, description = "68 Manual Production order with Move Type - Item (ALL OUTSIDE)")
//	public void VerifyProdOrderAgainstSOAllOutsideMoveTypeItem() {
//		flag = prodOrderFunc.verifyProdOrderAgainstSOAllOutsideMoveTypeItem();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual production order Against Sales Order Reference(First Process - Inside
//	 * - subsequent process- Outside)
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 25, groups = {
//			"Prod Order" }, description = "69 Manual Production order with Move Type - Single (FIRST IN REST OUT)")
//	public void VerifyProdOrderAgainstSOFirstInsideRestOutsideMoveTypeSingle() {
//		flag = prodOrderFunc.verifyProdOrderAgainstSOFirstInsideRestOutsideMoveTypeSingle();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual production order Against Sales Order Reference(First Process - Inside
//	 * - subsequent process- Outside)
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 26, groups = {
//			"Prod Order" }, description = "70 Manual Production order with Move Type - Multiple (FIRST IN REST OUT)")
//	public void VerifyProdOrderAgainstSOFirstInsideRestOutsideMoveTypeMultiple() {
//		flag = prodOrderFunc.verifyProdOrderAgainstSOFirstInsideRestOutsideMoveTypeMultiple();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual production order Against Sales Order Reference(First Process - Inside
//	 * - subsequent process- Outside)
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 27, groups = {
//			"Prod Order" }, description = "71 Manual Production order with Move Type - Item (FIRST IN REST OUT)")
//	public void VerifyProdOrderAgainstSOFirstInsideRestOutsideMoveTypeItem() {
//		flag = prodOrderFunc.verifyProdOrderAgainstSOFirstInsideRestOutsideMoveTypeItem();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual production order Against Sales Order Reference(First Process - Outside
//	 * - subsequent process- Inside)
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 28, groups = {
//			"Prod Order" }, description = "72 Manual Production order with Move Type - Single (FIRST OUT REST IN)")
//	public void VerifyProdOrderAgainstSOFirstOutsideRestInsideMoveTypeSingle() {
//		flag = prodOrderFunc.verifyProdOrderAgainstSOFirstOutsideRestInsideMoveTypeSingle();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual production order Against Sales Order Reference(First Process - Outside
//	 * - subsequent process- Inside)
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 29, groups = {
//			"Prod Order" }, description = "73 Manual Production order with Move Type - Multiple (FIRST OUT REST IN)")
//	public void VerifyProdOrderAgainstSOFirstOutsideRestInsideMoveTypeMultiple() {
//		flag = prodOrderFunc.verifyProdOrderAgainstSOFirstOutsideRestInsideMoveTypeMultiple();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual production order Against Sales Order Reference(First Process - Outside
//	 * - subsequent process- Inside)
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 30, groups = {
//			"Prod Order" }, description = "74 Manual Production order with Move Type - Item (FIRST OUT REST IN)")
//	public void VerifyProdOrderAgainstSOFirstOutsideRestInsideMoveTypeItem() {
//		flag = prodOrderFunc.verifyProdOrderAgainstSOFirstOutsideRestInsideMoveTypeItem();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual production order Against Sales Order reference (First Process -
//	 * Outside- subsequent process- Inside / Outside(Comb. of Both))
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 31, groups = {
//			"Prod Order" }, description = "75 Manual Production order with Move Type - Single (FIRST OUT REST BOTH)")
//	public void VerifyProdOrderAgainstSOFirstOutsideRestBothMoveTypeSingle() {
//		flag = prodOrderFunc.verifyProdOrderAgainstSOFirstOutsideRestBothMoveTypeSingle();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual production order Against Sales Order reference (First Process -
//	 * Outside- subsequent process- Inside / Outside(Comb. of Both))
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 32, groups = {
//			"Prod Order" }, description = "76 Manual Production order with Move Type - Multiple (FIRST OUT REST BOTH)")
//	public void VerifyProdOrderAgainstSOFirstOutsideRestBothMoveTypeMultiple() {
//		flag = prodOrderFunc.verifyProdOrderAgainstSOFirstOutsideRestBothMoveTypeMultiple();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual production order Against Sales Order reference (First Process -
//	 * Outside- subsequent process- Inside / Outside(Comb. of Both))
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 33, groups = {
//			"Prod Order" }, description = "77 Manual Production order with Move Type - Item (FIRST OUT REST BOTH)")
//	public void VerifyProdOrderAgainstSOFirstOutsideRestBothMoveTypeItem() {
//		flag = prodOrderFunc.verifyProdOrderAgainstSOFirstOutsideRestBothMoveTypeItem();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual production order against Sales Order (Both - Partial Qty Inside /
//	 * Partial Outside)
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 34, groups = {
//			"Prod Order" }, description = "78 Manual Production order with Move Type - Single (Both - Partial Qty Inside / Partial Outside)")
//	public void VerifyProdOrderAgainstSOAllBothPartialQuantityMoveTypeSingle() {
//		flag = prodOrderFunc.verifyProdOrderAgainstSOAllBothPartialQuantityMoveTypeSingle();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual production order against Sales Order (Both - Partial Qty Inside /
//	 * Partial Outside)
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 35, groups = {
//			"Prod Order" }, description = "79 Manual Production order with Move Type - Multiple (Both - Partial Qty Inside / Partial Outside)")
//	public void VerifyProdOrderAgainstSOAllBothPartialQuantityMoveTypeMultiple() {
//		flag = prodOrderFunc.verifyProdOrderAgainstSOAllBothPartialQuantityMoveTypeMultiple();
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Manual production order against Sales Order (Both - Partial Qty Inside /
//	 * Partial Outside)
//	 * 
//	 * @author Sakthi-GWL
//	 */
//	@Author("Sakthi-GWL")
//	@Test(priority = 36, groups = {
//			"Prod Order" }, description = "80 Manual Production order with Move Type - Item (Both - Partial Qty Inside / Partial Outside)")
//	public void VerifyProdOrderAgainstSOAllBothPartialQuantityMoveTypeItem() {
//		flag = prodOrderFunc.verifyProdOrderAgainstSOAllBothPartialQuantityMoveTypeItem();
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
