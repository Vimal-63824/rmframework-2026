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

public class PurchaseOrderTest {
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
		flag = purchaseOrderFunc.verifyDirectPODomesticGeneralSimilarUOM();
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
		flag = purchaseOrderFunc.verifyDirectPODomesticMaterialSimilarUOM();
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
		flag = purchaseOrderFunc.verifyDirectPODomesticServiceSimilarUOM();
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
		flag = purchaseOrderFunc.verifyDirectPODomesticStockTransferSimilarUOM();
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
		flag = purchaseOrderFunc.verifyDirectPOImportGeneralSimilarUOM();
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
		flag = purchaseOrderFunc.verifyDirectPOImportMaterialSimilarUOM();
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
		flag = purchaseOrderFunc.verifyDirectPOImportServiceSimilarUOM();
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
		flag = purchaseOrderFunc.verifyDomesticGeneralSimilarUOMFromPRtoPO();
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
		flag = purchaseOrderFunc.verifyDomesticServiceSimilarUOMFromPRtoPO();
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
		flag = purchaseOrderFunc.verifyImportGeneralSimilarUOMFromPRtoPO();
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
		flag = purchaseOrderFunc.verifyImportServiceSimilarUOMFromPRtoPO();
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
		flag = purchaseOrderFunc.verifyDirectPODomesticGeneralDissimilarUOM();
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
		flag = purchaseOrderFunc.verifyDirectPODomesticMaterialDissimilarUOM();
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
		flag = purchaseOrderFunc.verifyDirectPOImportGeneralDissimilarUOM();
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
		flag = purchaseOrderFunc.verifyDomesticGeneralDissimilarUOMFromPRtoPO();
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
		flag = purchaseOrderFunc.verifyImportGeneralDissimilarUOMFromPRtoPO();
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
		purchaseOrderFunc.verifyDirectPOWithItemChargesCalculations();
	}

	/**
	 * How to cancel Purchase Order Document? How to cancel the Purchase Order
	 * Lines?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Test(priority = 19, groups = { "PurchaseOrder" }, description = "110 How to cancel Purchase Order Document?"
			+ "111 How to cancel the Purchase Order Lines?")
	public void VerifyCancelPurchaseOrderDocumentAndCancelItem() {
		purchaseOrderFunc.VerifyCancelPurchaseOrderDocument();
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
