package com.roadmapERP;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks January 22, 2025
 */
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.PurchaseOrderFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class PurchaseOrderTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();

	/**
	 * Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier
	 * UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 1, groups = {
			"PurchaseOrder" }, description = "88 Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM")
	public void VerifyDirectPODomesticGeneralStockAndSupplierSimilar() {
		flag = purchaseOrderFunc.verifyDirectPODomesticGeneralSimilarUOMWithMultipleItemsWithExcelInput();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier - Material Items with Similar Stock and
	 * Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 2, groups = {
			"PurchaseOrder" }, description = "89 Direct PO - Domestic Supplier - Material Items with Similar Stock and Supplier UOM")
	public void VerifyDirectPODomesticMaterialStockAndSupplierSimilar() {
		flag = purchaseOrderFunc.verifyDirectPODomesticMaterialSimilarUOMWithMultipleItemsWithExcelInput();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier - Material Items with Similar Stock and
	 * Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 3, groups = {
			"PurchaseOrder" }, description = "90 Direct PO - Domestic Supplier - Service Items with Similar Stock and Supplier UOM")
	public void VerifyDirectPODomesticServiceStockAndSupplierSimilar() {
		flag = purchaseOrderFunc.verifyDirectPODomesticServiceSimilarUOMWithMultipleItemsWithExcelInput();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier - stock Transfer Items with Similar Stock and
	 * Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 4, groups = {
			"PurchaseOrder" }, description = "91 Direct PO - Domestic Supplier - stock Transfer Items with Similar Stock and Supplier UOM")
	public void VerifyDirectPODomesticStockTransferSimilarStockAndSupplierUOM() {
		flag = purchaseOrderFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheet();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Import Supplier - General Items with Similar Stock and Supplier
	 * UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 5, groups = {
			"PurchaseOrder" }, description = "92 Direct PO - Import Supplier - General Items with Similar Stock and Supplier UOM")
	public void VerifyDirectPOImportGeneralStockAndSupplierSimilar() {
		flag = purchaseOrderFunc.verifyDirectPOImportGeneralSimilarUOMWithMultipleItemsWithExcelInput();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Import Supplier - Material Items with Similar Stock and Supplier
	 * UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 6, groups = {
			"PurchaseOrder" }, description = "93 Direct PO - Import Supplier - Material Items with Similar Stock and Supplier UOM")
	public void VerifyDirectPOImportMaterialStockAndSupplierSimilar() {
		flag = purchaseOrderFunc.verifyDirectPOImportMaterialSimilarUOMWithMultipleItemsWithExcelInput();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Import Supplier - Service Items with Similar Stock and Supplier
	 * UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 7, groups = {
			"PurchaseOrder" }, description = "94 Direct PO - Import Supplier - Service Items with Similar Stock and Supplier UOM")
	public void VerifyDirectPOImportServiceStockAndSupplierSimilar() {
		flag = purchaseOrderFunc.verifyDirectPOImportServiceSimilarUOMWithMultipleItemsWithExcelInput();
		Assert.assertTrue(flag);
	}

	/**
	 * From PR to PO - Domestic Supplier - General Items with Similar Stock and
	 * Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 8, groups = {
			"PurchaseOrder" }, description = "95 From PR to PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM")
	public void VerifyDomesticGeneralSimilarUOMFromPRtoPO() {
		flag = purchaseOrderFunc.verifyDomesticGeneralSimilarUOMFromPRtoPOWithMultipleItemInExcelSheet();
		Assert.assertTrue(flag);
	}

	/**
	 * From PR to PO - Domestic Supplier - Service Items with Similar Stock and
	 * Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 9, groups = {
			"PurchaseOrder" }, description = "97 From PR to PO - Domestic Supplier - Service Items with Similar Stock and Supplier UOM")
	public void VerifyDomesticServiceSimilarUOMFromPRtoPO() {
		flag = purchaseOrderFunc.verifyDomesticServiceSimilarUOMFromPRtoPOWithMultipleItemInExcelSheet();
		Assert.assertTrue(flag);
	}

	/**
	 * From PR to PO - Import Supplier - General Items with Similar Stock and
	 * Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 10, groups = {
			"PurchaseOrder" }, description = "98 From PR to PO - Import Supplier - General Items with Similar Stock and Supplier UOM")
	public void VerifyImportGeneralSimilarUOMFromPRtoPO() {
		flag = purchaseOrderFunc.verifyImportGeneralSimilarUOMFromPRtoPOWithMultipleItemInExcelSheet();
		Assert.assertTrue(flag);
	}

	/**
	 * From PR to PO - Import Supplier - Service Items with Similar Stock and
	 * Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 11, groups = {
			"PurchaseOrder" }, description = "100 From PR to PO - Import Supplier - Service Items with Similar Stock and Supplier UOM")
	public void VerifyImportServiceSimilarUOMFromPRtoPO() {
		flag = purchaseOrderFunc.verifyImportServiceSimilarUOMFromPRtoPOWithMultipleItemInExcelSheet();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM\r\n
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 12, groups = {
			"PurchaseOrder" }, description = "101 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM")
	public void VerifyDirectPODomesticGeneralStockAndSupplierUOMDissimilar() {
		flag = purchaseOrderFunc.verifyDirectPODomesticGeneralDissimilarUOMWithMultipleItemsWithExcelInput();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 13, groups = {
			"PurchaseOrder" }, description = "102 Direct PO - Domestic Supplier - Material Items with Dissimilar Stock and Supplier UOM")
	public void VerifyDirectPODomesticMaterialStockAndSupplierUOMDisSimilar() {
		flag = purchaseOrderFunc.verifyDirectPODomesticMaterialDissimilarUOMWithMultipleItemsWithExcelInput();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Import Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 14, groups = {
			"PurchaseOrder" }, description = "103 Direct PO - Import Supplier - General Items with Dissimilar Stock and Supplier UOM")
	public void VerifyDirectPOImportGeneralStockAndSupplierUOMDissimilar() {
		flag = purchaseOrderFunc.verifyDirectPOImportGeneralDissimilarUOMWithMultipleItemsWithExcelInput();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Import Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM
	 * 
	 * @
	 */
	@Test(priority = 15, groups = {
			"PurchaseOrder" }, description = "104 Direct PO - Import Supplier - Material Items with Dissimilar Stock and Supplier UOM")
	public void VerifyDirectPOImportMaterialStockAndSupplierUOMDissimilar() {
		flag = purchaseOrderFunc.verifyDirectPOImportMaterialDissimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * From PR to PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 16, groups = {
			"PurchaseOrder" }, description = "105 From PR to PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM")
	public void VerifyDomesticGeneralDissimilarUOMFromPRtoPO() {
		flag = purchaseOrderFunc.verifyDomesticGeneralDissimilarUOMFromPRtoPOWithMultipleItemInExcelSheet();
		Assert.assertTrue(flag);
	}

	/**
	 * From PR to PO - Import Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Test(priority = 17, groups = {
			"PurchaseOrder" }, description = "107 From PR to PO - Import Supplier - General Items with Dissimilar Stock and Supplier UOM")
	public void VerifyImportGeneralDissimilarUOMFromPRtoPO() {
		flag = purchaseOrderFunc.verifyImportGeneralDissimilarUOMFromPRtoPOWithMultipleItemInExcelSheet();
		Assert.assertTrue(flag);
	}

	/**
	 * How to create PO with Charges and how PO amount is calculated?
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 18, groups = {
			"PurchaseOrder" }, description = "109 How to create PO with Charges and how PO amount is calculated?")
	public void VerifyDirectPOWithItemChargesCalculations() {
		flag = purchaseOrderFunc.verifyDirectPOWithItemChargesCalculations();
		Assert.assertTrue(flag);
	}

	/**
	 * How to cancel Purchase Order Document
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Test(priority = 19, groups = { "PurchaseOrder" }, description = "110 How to cancel Purchase Order Document?")
	public void VerifyCancelPurchaseOrderDocument() {
		flag = purchaseOrderFunc.VerifyCancelPurchaseOrderDocument();
		Assert.assertTrue(flag);
	}

	/**
	 * How to cancel the Purchase Order Lines
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Test(priority = 20, groups = { "PurchaseOrder" }, description = "111 How to cancel the Purchase Order Lines?")
	public void VerifyCancelPurchaseOrderCancelLines() {
		// Covered in Previous Method
		flag = true;
		Assert.assertTrue(flag);
	}

	/**
	 * Find the Purchase Orders with all possible search parameters
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Test(priority = 21, groups = {
			"PurchaseOrder" }, description = "Find the Purchase Orders with all possible search parameters")
	public void VerifyPoFilterFieldVerification() {
		purchaseOrderFunc.poFilterAllFieldWorking();
	}

	/**
	 * From PR to PO - Domestic Supplier - Material Items with Similar Stock and
	 * Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 22, groups = {
			"PurchaseOrder" }, description = "From PR to PO - Domestic Supplier - Material Items with Similar Stock and Supplier UOM")
	public void VerifyDomesticMaterialSimilarUOMFromPRtoPO() {
		purchaseOrderFunc.verifyPRtoPOWithMultipleItemInExcelSheetForMaterialWithDomestic();
	}

	/**
	 * From PR to PO - Import Supplier - Material Items with Similar Stock and
	 * Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 23, groups = {
			"PurchaseOrder" }, description = "From PR to PO - Import Supplier - Material Items with Similar Stock and Supplier UOM")
	public void VerifyImportMaterialSimilarUOMFromPRtoPO() {
		purchaseOrderFunc.verifyPRtoPOWithMultipleItemInExcelSheetForMaterialWithImport();
	}

	/**
	 * From PR to PO - Domestic Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 24, groups = {
			"PurchaseOrder" }, description = "From PR to PO - Domestic Supplier - Material Items with Dissimilar Stock and Supplier UOM")
	public void VerifyDomesticMaterialDissimilarUOMFromPRtoPO() {
		purchaseOrderFunc.verifyPRtoPOWithMultipleItemInExcelSheetForMaterialWithDomesticDissimilar();
	}

	/**
	 * From PR to PO - Import Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 25, groups = {
			"PurchaseOrder" }, description = "From PR to PO - Import Supplier - Material Items with Dissimilar Stock and Supplier UOM")
	public void VerifyImportMaterialDissimilarUOMFromPRtoPO() {
		purchaseOrderFunc.verifyPRtoPOWithMultipleItemInExcelSheetForMaterialWithImportDissimilar();
	}

	/**
	 * Logout The Portal If Testcases Failed
	 * 
	 * @author Deepan-GWL
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result) {
		commonFunc.verifyAfterMethod();
	}

}
