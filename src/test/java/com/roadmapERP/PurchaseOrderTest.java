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
	 * UOM?
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	@Test(priority = 1, groups = {
			"PurchaseOrder" }, description = "Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM?")
	public void VerifyDirectPODomesticGeneralStockAndSupplierSimilar() throws Exception {
		flag = purchaseOrderFunc.verifyDirectPODomesticGeneralSimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier - Material Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	@Test(priority = 2, groups = {
			"PurchaseOrder" }, description = "Direct PO - Domestic Supplier - Material Items with Similar Stock and Supplier UOM?")
	public void VerifyDirectPODomesticMaterialStockAndSupplierSimilar() throws Exception {
		flag = purchaseOrderFunc.verifyDirectPODomesticMaterialSimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier - Material Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	@Test(priority = 3, groups = {
			"PurchaseOrder" }, description = "Direct PO - Domestic Supplier - Service Items with Similar Stock and Supplier UOM?")
	public void VerifyDirectPODomesticServiceStockAndSupplierSimilar() throws Exception {
		flag = purchaseOrderFunc.verifyDirectPODomesticServiceSimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM?\r\n
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	@Test(priority = 4, groups = {
			"PurchaseOrder" }, description = "Direct PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyDirectPODomesticGeneralStockAndSupplierUOMDissimilar() throws Exception {
		flag = purchaseOrderFunc.verifyDirectPODomesticGeneralDissimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	@Test(priority = 5, groups = {
			"PurchaseOrder" }, description = "Direct PO - Domestic Supplier - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyDirectPODomesticMaterialStockAndSupplierUOMDisSimilar() throws Exception {
		flag = purchaseOrderFunc.verifyDirectPODomesticMaterialDissimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Import Supplier - General Items with Similar Stock and Supplier
	 * UOM?
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	@Test(priority = 6, groups = {
			"PurchaseOrder" }, description = "Direct PO - Import Supplier - General Items with Similar Stock and Supplier UOM?")
	public void VerifyDirectPOImportGeneralStockAndSupplierSimilar() throws Exception {
		flag = purchaseOrderFunc.verifyDirectPOImportGeneralSimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Import Supplier - Material Items with Similar Stock and Supplier
	 * UOM?
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	@Test(priority = 7, groups = {
			"PurchaseOrder" }, description = "Direct PO - Import Supplier - Material Items with Similar Stock and Supplier UOM?")
	public void VerifyDirectPOImportMaterialStockAndSupplierSimilar() throws Exception {
		flag = purchaseOrderFunc.verifyDirectPOImportMaterialSimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Import Supplier - Service Items with Similar Stock and Supplier
	 * UOM?
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	@Test(priority = 8, groups = {
			"PurchaseOrder" }, description = "Direct PO - Import Supplier - Service Items with Similar Stock and Supplier UOM?")
	public void VerifyDirectPOImportServiceStockAndSupplierSimilar() throws Exception {
		flag = purchaseOrderFunc.verifyDirectPOImportServiceSimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Import Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	@Test(priority = 9, groups = {
			"PurchaseOrder" }, description = "Direct PO - Import Supplier - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyDirectPOImportGeneralStockAndSupplierUOMDissimilar() throws Exception {
		flag = purchaseOrderFunc.verifyDirectPOImportGeneralDissimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Import Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @throws Exception
	 */
	@Test(priority = 10, groups = {
			"PurchaseOrder" }, description = "Direct PO - Import Supplier - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyDirectPOImportMaterialStockAndSupplierUOMDissimilar() throws Exception {
		flag = purchaseOrderFunc.verifyDirectPOImportMaterialDissimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier - Service Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	@Test(priority = 11, groups = {
			"PurchaseOrder" }, description = "Direct PO - Domestic Supplier - Service Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyDirectPODomesticServiceStockAndSupplierUOMDissimilar() throws Exception {
		flag = purchaseOrderFunc.verifyDirectPODomesticServiceDissimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Import Supplier - Service Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * @throws Exception
	 */
	@Test(priority = 12, groups = {
			"PurchaseOrder" }, description = "Direct PO - Import Supplier - Service Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyDirectPOImportServiceStockAndSupplierUOMDissimilar() throws Exception {
		flag = purchaseOrderFunc.verifyDirectPOImportServiceDissimilarUOM();
		Assert.assertTrue(flag);
	}

	/**
	 * Logout The Portal If Testcases Failed
	 * 
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			commonFunc.verifyAfterMethod();
		}
	}

}
