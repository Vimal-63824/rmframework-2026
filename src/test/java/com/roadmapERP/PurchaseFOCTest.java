package com.roadmapERP;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks December 18, 2025
 */
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.PurchaseOrderFunctions;
import commonfunctions.supplychainmanagement.Purchase_GRN_FOCFunctions;
import utils.ReportLoger;

public class PurchaseFOCTest extends RoadMapERPTest {
	private boolean flag = false;
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static Purchase_GRN_FOCFunctions purchaseFocFunc = new Purchase_GRN_FOCFunctions();
	private static PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();
	private static ReportLoger logs = new ReportLoger();
	private static int previousStatus = ITestResult.SUCCESS;

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Domestic Supplier - General
	 * Items with Similar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 1, groups = {
			"Direct PO" }, description = "692 Purchase Order-With Free Of Cost - Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM")
	public void VerifyFOCForDirectPODomesticSuplrGeneralItemSimilarUOM() {
		flag = purchaseFocFunc.verifyFOCForDirectPODomesticSuplrGeneralItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * /** Purchase Order-With Free Of Cost - Direct PO - Domestic Supplier -
	 * Material Items with Similar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 2, groups = {
			"Direct PO" }, description = "693 Purchase Order-With Free Of Cost - Direct PO - Domestic Supplier - Material Items with Similar Stock and Supplier UOM")
	public void VerifyFOCForDirectPODomesticSuplrMaterialItemSimilarUOM() {
		flag = purchaseFocFunc.verifyFOCForDirectPODomesticSuplrMaterialItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Domestic Supplier - Service
	 * Items with Similar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 3, groups = {
			"Direct PO" }, description = "694 Purchase Order-With Free Of Cost - Direct PO - Domestic Supplier - Service Items with Similar Stock and Supplier UOM")
	public void VerifyFOCForDirectPODomesticSuplrServiceItemSimilarUOM() {
		flag = purchaseFocFunc.verifyFOCForDirectPODomesticSuplrServiceItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Import Supplier - General
	 * Items with Similar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 4, groups = {
			"Direct PO" }, description = "696 Purchase Order-With Free Of Cost - Direct PO - Import Supplier - General Items with Similar Stock and Supplier UOM")
	public void VerifyFOCForDirectPOImportSuplrGeneralItemSimilarUOM() {
		flag = purchaseFocFunc.verifyFOCForDirectPOImportSuplrGeneralItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Import Supplier - Material
	 * Items with Similar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 5, groups = {
			"Direct PO" }, description = "697 Purchase Order-With Free Of Cost - Direct PO - Import Supplier - Material Items with Similar Stock and Supplier UOM")
	public void VerifyFOCForDirectPOImportSuplrMaterialItemSimilarUOM() {
		flag = purchaseFocFunc.verifyFOCForDirectPOImportSuplrMaterialItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Import Supplier - Service
	 * Items with Similar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 6, groups = {
			"Direct PO" }, description = "698 Purchase Order-With Free Of Cost - Direct PO - Import Supplier - Service Items with Similar Stock and Supplier UOM")
	public void VerifyFOCForDirectPOImportSuplrServiceItemSimilarUOM() {
		flag = purchaseFocFunc.verifyFOCForDirectPOImportSuplrServiceItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * How to create PO with Charges and how PO amount is calculated?
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 7, groups = {
			"PurchaseOrder" }, description = "713 How to create PO with Charges and how PO amount is calculated?")
	public void VerifyDirectPOWithItemChargesCalculations() {
		flag = purchaseOrderFunc.verifyDirectPOWithItemChargesCalculations();
		Assert.assertTrue(flag);
	}

	/**
	 * How to cancel Purchase Order Document
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 8, groups = { "PurchaseOrder" }, description = "714 How to cancel Purchase Order Document?")
	public void VerifyCancelPurchaseOrderDocument() {
		flag = purchaseOrderFunc.VerifyCancelPurchaseOrderDocument();
		Assert.assertTrue(flag);
	}

	/**
	 * How to cancel the Purchase Order Lines
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 9, groups = { "PurchaseOrder" }, description = "715 How to cancel the Purchase Order Lines?")
	public void VerifyCancelPurchaseOrderCancelLines() {
		logs.logging("INFO", "This test covered in 'VerifyCancelPurchaseOrderDocument'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (VerifyCancelPurchaseOrderDocument) failed. So this fails too.");
			Assert.fail();
		}
	}

	/**
	 * Find the Purchase Orders with all possible search parameters
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 10, groups = {
			"PurchaseOrder" }, description = "691 Find the Purchase Orders with all possible search parameters")
	public void VerifyPoFilterFieldVerification() {
		purchaseOrderFunc.poFilterAllFieldWorking();
	}

	/**
	 * Verify After Method
	 * 
	 * @author Sakthi-GWL
	 * @param result
	 * @param method
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result, Method method) {
		previousStatus = result.getStatus();
		commonFunc.verifyAfterMethod();
		commonFunc.enterAuthorNamesInExtentReport(method);
	}
}
