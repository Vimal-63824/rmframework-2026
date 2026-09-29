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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
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
	 * This method used to login while start of the test class
	 * 
	 * @author Sakthi-GWL
	 */
	@BeforeMethod(alwaysRun = true)
	private void VerifyLogin() {
		if (VariableHandler.getSessionID() == null || VariableHandler.getSessionID().equalsIgnoreCase("")
				|| VariableHandler.getSessionOpened() == false || VariableHandler.getSessionID().equals(null)) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername-Admin");
		} else {
			commonFunc.navigateToHomePage();
		}
	}

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
	 * Purchase Order-With Free Of Cost - Direct PO - Domestic Supplier - General
	 * Items with Dissimilar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 7, groups = {
			"Direct PO" }, description = "705 Purchase Order-With Free Of Cost - Direct PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM")
	public void VerifyFOCForDirectPODomesticSuplrGeneralItemDissimilarUOM() {
		flag = purchaseFocFunc.verifyFOCForDirectPODomesticSuplrGeneralItemDissimilarUOM();
		assertTrue(flag);
	}

	/**
	 * /** Purchase Order-With Free Of Cost - Direct PO - Domestic Supplier -
	 * Material Items with Dissimilar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 8, groups = {
			"Direct PO" }, description = "706 Purchase Order-With Free Of Cost - Direct PO - Domestic Supplier - Material Items with Dissimilar Stock and Supplier UOM")
	public void VerifyFOCForDirectPODomesticSuplrMaterialItemDissimilarUOM() {
		flag = purchaseFocFunc.verifyFOCForDirectPODomesticSuplrMaterialItemDissimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Import Supplier - General
	 * Items with Dissimilar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 9, groups = {
			"Direct PO" }, description = "707 Purchase Order-With Free Of Cost - Direct PO - Import Supplier - General Items with Dissimilar Stock and Supplier UOM")
	public void VerifyFOCForDirectPOImportSuplrGeneralItemDissimilarUOM() {
		flag = purchaseFocFunc.verifyFOCForDirectPOImportSuplrGeneralItemDissimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Import Supplier - Material
	 * Items with Dissimilar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 10, groups = {
			"Direct PO" }, description = "708 Purchase Order-With Free Of Cost - Direct PO - Import Supplier - Material Items with Dissimilar Stock and Supplier UOM")
	public void VerifyFOCForDirectPOImportSuplrMaterialItemDissimilarUOM() {
		flag = purchaseFocFunc.verifyFOCForDirectPOImportSuplrMaterialItemDissimilarUOM();
		assertTrue(flag);
	}

	/**
	 * From PR to PO - Domestic Supplier - General Items with Similar Stock and
	 * Supplier UOM - Free of Cost
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 11, groups = {
			"From PR to PO" }, description = "699 From PR to PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM  - Free of Cost")
	public void VerifyFromPRtoPOFOCForDomesticSuplrGeneralItemSimilarUOM() {
		flag = purchaseFocFunc.verifyFromPRtoPOFOCForDomesticSuplrGeneralItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * From PR to PO - Domestic Supplier - Material Items with Similar Stock and
	 * Supplier UOM - Free of Cost
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 12, groups = {
			"From PR to PO" }, description = "700 From PR to PO - Domestic Supplier - Material Items with Similar Stock and Supplier UOM  - Free of Cost")
	public void VerifyFromPRtoPOFOCForDomesticSuplrMaterialItemSimilarUOM() {
		flag = purchaseFocFunc.verifyFromPRtoPOFOCForDomesticSuplrMaterialItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * From PR to PO - Domestic Supplier - Service Items with Similar Stock and
	 * Supplier UOM - Free of Cost
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 13, groups = {
			"From PR to PO" }, description = "701 From PR to PO - Domestic Supplier - Service Items with Similar Stock and Supplier UOM  - Free of Cost")
	public void VerifyFromPRtoPOFOCForDomesticSuplrServiceItemSimilarUOM() {
		flag = purchaseFocFunc.verifyFromPRtoPOFOCForDomesticSuplrServiceItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * From PR to PO - Import Supplier - General Items with Similar Stock and
	 * Supplier UOM - Free of Cost
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 14, groups = {
			"From PR to PO" }, description = "702 From PR to PO - Import Supplier - General Items with Similar Stock and Supplier UOM  - Free of Cost")
	public void VerifyFromPRtoPOFOCForImportSuplrGeneralItemSimilarUOM() {
		flag = purchaseFocFunc.verifyFromPRtoPOFOCForImportSuplrGeneralItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * From PR to PO - Import Supplier - Material Items with Similar Stock and
	 * Supplier UOM - Free of Cost
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 15, groups = {
			"From PR to PO" }, description = "703 From PR to PO - Import Supplier - Material Items with Similar Stock and Supplier UOM  - Free of Cost")
	public void VerifyFromPRtoPOFOCForImportSuplrMaterialItemSimilarUOM() {
		flag = purchaseFocFunc.verifyFromPRtoPOFOCForImportSuplrMaterialItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * From PR to PO - Import Supplier - Service Items with Similar Stock and
	 * Supplier UOM - Free of Cost
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 16, groups = {
			"From PR to PO" }, description = "704 From PR to PO - Import Supplier - Service Items with Similar Stock and Supplier UOM  - Free of Cost")
	public void VerifyFromPRtoPOFOCForImportSuplrServiceItemSimilarUOM() {
		flag = purchaseFocFunc.verifyFromPRtoPOFOCForImportSuplrServiceItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * From PR to PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM - Free of Cost
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 17, groups = {
			"From PR to PO" }, description = "709 From PR to PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM  - Free of Cost")
	public void VerifyFromPRtoPOFOCForDomesticSuplrGeneralItemDissimilarUOM() {
		flag = purchaseFocFunc.verifyFromPRtoPOFOCForDomesticSuplrGeneralItemDissimilarUOM();
		assertTrue(flag);
	}

	/**
	 * From PR to PO - Domestic Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM - Free of Cost
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 18, groups = {
			"From PR to PO" }, description = "710 From PR to PO - Domestic Supplier - Material Items with Dissimilar Stock and Supplier UOM  - Free of Cost")
	public void VerifyFromPRtoPOFOCForDomesticSuplrMaterialItemDissimilarUOM() {
		flag = purchaseFocFunc.verifyFromPRtoPOFOCForDomesticSuplrMaterialItemDissimilarUOM();
		assertTrue(flag);
	}

	/**
	 * From PR to PO - Import Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM - Free of Cost
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 19, groups = {
			"From PR to PO" }, description = "711 From PR to PO - Import Supplier - General Items with Dissimilar Stock and Supplier UOM  - Free of Cost")
	public void VerifyFromPRtoPOFOCForImportSuplrGeneralItemDissimilarUOM() {
		flag = purchaseFocFunc.verifyFromPRtoPOFOCForImportSuplrGeneralItemDissimilarUOM();
		assertTrue(flag);
	}

	/**
	 * From PR to PO - Import Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM - Free of Cost
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 20, groups = {
			"From PR to PO" }, description = "712 From PR to PO - Import Supplier - Material Items with Dissimilar Stock and Supplier UOM  - Free of Cost")
	public void VerifyFromPRtoPOFOCForImportSuplrMaterialItemDissimilarUOM() {
		flag = purchaseFocFunc.verifyFromPRtoPOFOCForImportSuplrMaterialItemDissimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Purchase Order-With Free Of Cost - Direct PO - Domestic Supplier - Stock
	 * Transfer Items with Similar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
//	@Test(priority = 21, groups = {
//			"Direct PO" }, description = "695 Purchase Order-With Free Of Cost - Direct PO - Domestic Supplier - Stock Transfer Items with Similar Stock and Supplier UOM")
	public void VerifyFOCForDirectPODomesticSuplrStockTransferItemSimilarUOM() {
		flag = purchaseFocFunc.verifyFOCForDirectPODomesticSuplrStockTransferItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Find the Purchase Orders with all possible search parameters
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 22, groups = {
			"PurchaseOrder" }, description = "691 Find the Purchase Orders with all possible search parameters")
	public void VerifyPoFilterFieldVerification() {
		purchaseOrderFunc.poFilterAllFieldWorking();
	}

	/**
	 * How to create PO with Charges and how PO amount is calculated?
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 23, groups = {
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
	@Test(priority = 24, groups = { "PurchaseOrder" }, description = "714 How to cancel Purchase Order Document?")
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
	@Test(priority = 25, groups = { "PurchaseOrder" }, description = "715 How to cancel the Purchase Order Lines?")
	public void VerifyCancelPurchaseOrderCancelLines() {
		logs.logging("INFO", "This test covered in 'VerifyCancelPurchaseOrderDocument'  Test");
		if (previousStatus != ITestResult.SUCCESS) {
			logs.logging("INFO", "Previous test (VerifyCancelPurchaseOrderDocument) failed. So this fails too.");
			Assert.fail();
		}
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
