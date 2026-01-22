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
	 * Purchase Order-With Free Of Cost - Direct PO - Domestic Supplier - General
	 * Items with Dissimilar Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 11, groups = {
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
	@Test(priority = 12, groups = {
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
	@Test(priority = 13, groups = {
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
	@Test(priority = 14, groups = {
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
	@Test(priority = 15, groups = {
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
	@Test(priority = 16, groups = {
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
	@Test(priority = 17, groups = {
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
	@Test(priority = 18, groups = {
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
	@Test(priority = 19, groups = {
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
	@Test(priority = 20, groups = {
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
	@Test(priority = 21, groups = {
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
	@Test(priority = 22, groups = {
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
	@Test(priority = 23, groups = {
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
	@Test(priority = 24, groups = {
			"From PR to PO" }, description = "712 From PR to PO - Import Supplier - Material Items with Dissimilar Stock and Supplier UOM  - Free of Cost")
	public void VerifyFromPRtoPOFOCForImportSuplrMaterialItemDissimilarUOM() {
		flag = purchaseFocFunc.verifyFromPRtoPOFOCForImportSuplrMaterialItemDissimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-General-User Defined-Same Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 25, groups = {
			"Direct GRN" }, description = "758 Direct GRN-General-User Defined-Same Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForUDGeneralItemSimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForUDGeneralItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-Material-User Defined-Same Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 26, groups = {
			"Direct GRN" }, description = "759 Direct GRN-Material-User Defined-Same Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForUDMaterialItemSimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForUDMaterialItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-Service-User Defined-Same Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 27, groups = {
			"Direct GRN" }, description = "760 Direct GRN-Service-User Defined-Same Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForUDServiceItemSimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForUDServiceItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-General-User Defined-Dissimilar Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 28, groups = {
			"Direct GRN" }, description = "770 Direct GRN-General-User Defined-Dissimilar Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForUDGeneralItemDissimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForUDGeneralItemDissimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-Material-User Defined-Dissimilar Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 29, groups = {
			"Direct GRN" }, description = "771 Direct GRN-Material-User Defined-Dissimilar Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForUDMaterialItemDissimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForUDMaterialItemDissimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-General-Rate Contract-Same Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 30, groups = {
			"Direct GRN" }, description = "763 Direct GRN-General-Rate Contract-Same Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForRCGeneralItemSimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForRCGeneralItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-Material-Rate Contract-Same Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 31, groups = {
			"Direct GRN" }, description = "764 Direct GRN-Material-Rate Contract-Same Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForRCMaterialItemSimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForRCMaterialItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-Service-Rate Contract-Same Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 32, groups = {
			"Direct GRN" }, description = "764 Direct GRN-Service-Rate Contract-Same Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForRCServiceItemSimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForRCServiceItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-General-Rate Contract-Dissimilar Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 33, groups = {
			"Direct GRN" }, description = "772 Direct GRN-General-Rate Contract-Dissimilar Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForRCGeneralItemDissimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForRCGeneralItemDissimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-Material-Rate Contract-Dissimilar Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 34, groups = {
			"Direct GRN" }, description = "773 Direct GRN-Material-Rate Contract-Dissimilar Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForRCMaterialItemDissimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForRCMaterialItemDissimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-General-Price List-Same Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 35, groups = {
			"Direct GRN" }, description = "766 Direct GRN-General-Price List-Same Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForPLGeneralItemSimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForPLGeneralItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-Material-Price List-Same Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 36, groups = {
			"Direct GRN" }, description = "767 Direct GRN-Material-Price List-Same Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForPLMaterialItemSimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForPLMaterialItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-Service-Price List-Same Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 37, groups = {
			"Direct GRN" }, description = "768 Direct GRN-Service-Price List-Same Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForPLServiceItemSimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForPLServiceItemSimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-General-Price List-Dissimilar Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 38, groups = {
			"Direct GRN" }, description = "776 Direct GRN-General-Price List-Dissimilar Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForPLGeneralItemDissimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForPLGeneralItemDissimilarUOM();
		assertTrue(flag);
	}

	/**
	 * Direct GRN-Material-Price List-Dissimilar Stock andSupplier UOM - FOC
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 39, groups = {
			"Direct GRN" }, description = "777 Direct GRN-Material-Price List-Dissimilar Stock andSupplier UOM - FOC")
	public void VerifyDirectGRNFOCForPLMaterialItemDissimilarUOM() {
		flag = purchaseFocFunc.verifyDirectGRNFOCForPLMaterialItemDissimilarUOM();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection
	 * Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 40, groups = {
			"Po to GRN" }, description = "716 PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMGeneralUDWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMGeneralUDWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-General-User Defined-Same Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 41, groups = {
			"Po to GRN" }, description = "717 PO to GRN-General-User Defined-Same Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMGeneralUDWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMGeneralUDWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection
	 * Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 42, groups = {
			"Po to GRN" }, description = "718 PO to GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMMaterialUDWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMMaterialUDWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Material-User Defined-Same Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 43, groups = {
			"Po to GRN" }, description = "719 PO to GRN-Material-User Defined-Same Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMMaterialUDWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMMaterialUDWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-General-User Defined-Different Stock and Supplier UOM - With
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 44, groups = {
			"Po to GRN" }, description = "737 PO to GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMGeneralUDWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMGeneralUDWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-General-User Defined-Different Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 45, groups = {
			"Po to GRN" }, description = "738 PO to GRN-General-User Defined-Different Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMGeneralUDWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMGeneralUDWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Material-User Defined-Different Stock and Supplier UOM - With
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 46, groups = {
			"Po to GRN" }, description = "739 PO to GRN-Material-User Defined-Different Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMMaterialUDWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMMaterialUDWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Material-User Defined-Different Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 47, groups = {
			"Po to GRN" }, description = "740 PO to GRN-Material-User Defined-Different Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMMaterialUDWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMMaterialUDWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - With Inspection
	 * Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 48, groups = {
			"Po to GRN" }, description = "723 PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMGeneralRCWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMGeneralRCWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 49, groups = {
			"Po to GRN" }, description = "724 PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMGeneralRCWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMGeneralRCWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - With
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 50, groups = {
			"Po to GRN" }, description = "725 PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMMaterialRCWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMMaterialRCWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 51, groups = {
			"Po to GRN" }, description = "726 PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMMaterialRCWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMMaterialRCWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 52, groups = {
			"Po to GRN" }, description = "744 PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMGeneralRCWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMGeneralRCWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 53, groups = {
			"Po to GRN" }, description = "745 PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMGeneralRCWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMGeneralRCWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - With
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 54, groups = {
			"Po to GRN" }, description = "746 PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMMaterialRCWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMMaterialRCWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 55, groups = {
			"Po to GRN" }, description = "747 PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMMaterialRCWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMMaterialRCWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection
	 * Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 56, groups = {
			"Po to GRN" }, description = "730 PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMGeneralPLWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMGeneralPLWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-General-Price List-Same Stock and Supplier UOM - Without Inspection
	 * Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 57, groups = {
			"Po to GRN" }, description = "731 PO to GRN-General-Price List-Same Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMGeneralPLWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMGeneralPLWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Material-Price List-Same Stock and Supplier UOM - With Inspection
	 * Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 58, groups = {
			"Po to GRN" }, description = "732 PO to GRN-Material-Price List-Same Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMMaterialPLWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMMaterialPLWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Material-Price List-Same Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 59, groups = {
			"Po to GRN" }, description = "733 PO to GRN-Material-Price List-Same Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticSameUOMMaterialPLWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticSameUOMMaterialPLWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-General-Price List-Different Stock and Supplier UOM - With
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 60, groups = {
			"Po to GRN" }, description = "751 PO to GRN-General-Price List-Different Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMGeneralPLWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMGeneralPLWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-General-Price List-Different Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 61, groups = {
			"Po to GRN" }, description = "752 PO to GRN-General-Price List-Different Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMGeneralPLWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMGeneralPLWithoutInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Material-Price List-Different Stock and Supplier UOM - With
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 62, groups = {
			"Po to GRN" }, description = "753 PO to GRN-Material-Price List-Different Stock and Supplier UOM - With Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMMaterialPLWithInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMMaterialPLWithInspectionGEFOC();
		assertTrue(flag);
	}

	/**
	 * PO to GRN-Material-Price List-Different Stock and Supplier UOM - Without
	 * Inspection Plan - FOC
	 *
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 63, groups = {
			"Po to GRN" }, description = "754 PO to GRN-Material-Price List-Different Stock and Supplier UOM - Without Inspection Plan - FOC")
	public void VerifyPoToGRNDomesticDiffUOMMaterialPLWithoutInspectionGEFOC() {
		flag = purchaseFocFunc.verifyPoToGRNDomesticDiffUOMMaterialPLWithoutInspectionGEFOC();
		assertTrue(flag);
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
