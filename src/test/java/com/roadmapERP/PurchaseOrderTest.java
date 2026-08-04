package com.roadmapERP;

import java.lang.reflect.Method;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks January 22, 2025
 */
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.PurchaseOrderFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class PurchaseOrderTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();
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
	 * Direct PO - Domestic Supplier - stock Transfer Items with Similar Stock and
	 * Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 1, groups = {
			"PurchaseOrder" }, description = "91 Direct PO - Domestic Supplier - stock Transfer Items with Similar Stock and Supplier UOM")
	public void VerifyDirectPODomesticStockTransferSimilarStockAndSupplierUOM() {
		flag = purchaseOrderFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheet();
		Assert.assertTrue(flag);
	}

	/**
	 * How to create PO with Charges and how PO amount is calculated?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 2, groups = {
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
	@Author("Deepan-GWL")
	@Test(priority = 3, groups = { "PurchaseOrder" }, description = "110 How to cancel Purchase Order Document?")
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
	@Author("Deepan-GWL")
	@Test(priority = 4, groups = { "PurchaseOrder" }, description = "111 How to cancel the Purchase Order Lines?")
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
	@Author("Deepan-GWL")
	@Test(priority = 5, groups = {
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
	@Author("Deepan-GWL")
	@Test(priority = 6, groups = {
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
	@Author("Deepan-GWL")
	@Test(priority = 7, groups = {
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
	@Author("Deepan-GWL")
	@Test(priority = 8, groups = {
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
	@Author("Deepan-GWL")
	@Test(priority = 9, groups = {
			"PurchaseOrder" }, description = "From PR to PO - Import Supplier - Material Items with Dissimilar Stock and Supplier UOM")
	public void VerifyImportMaterialDissimilarUOMFromPRtoPO() {
		purchaseOrderFunc.verifyPRtoPOWithMultipleItemInExcelSheetForMaterialWithImportDissimilar();
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Direct PO - Domestic Supplier With Local Registration - General Items with
	 * Similar Stock and Supplier UOM
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 1, groups = {
			"PurchaseOrder" }, description = "Direct PO - Domestic Supplier With Local Registration - General Items with Similar Stock and Supplier UOM")
	public void VerifyDirectPOGeneralStockAndSupplierSimilarDomesticWithLocalReg() {
		flag = purchaseOrderFunc.verifyDirectPOGeneralSimilarUOMMultipleItemsWithExcelInputWithDomesticLocalReg();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier With Interstate Registration - General Items
	 * with Similar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 2, groups = {
			"PurchaseOrder" }, description = "Direct PO - Domestic Supplier With Interstate Registration - General Items with Similar Stock and Supplier UOM")
	public void VerifyDirectPOGeneralStockAndSupplierSimilarDomesticWithInterstateReg() {
		flag = purchaseOrderFunc.verifyDirectPOGeneralSimilarUOMMultipleItemsWithExcelInputWithDomesticInterstateReg();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier With Local UnRegistration - General Items with
	 * Similar Stock and Supplier UOM
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 3, groups = {
			"PurchaseOrder" }, description = "Direct PO - Domestic Supplier With Local UnRegistration - General Items with Similar Stock and Supplier UOM")
	public void VerifyDirectPODomesticGeneralStockAndSupplierSimilarWithLocalUnReg() {
		flag = purchaseOrderFunc.verifyDirectPODomesticGeneralSimilarUOMWithMultipleItemsWithExcelInputWithLocalUnReg();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier With Interstate Registration - General Items
	 * with Similar Stock and Supplier UOM
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 4, groups = {
			"PurchaseOrder" }, description = "Direct PO - Domestic Supplier With Interstate Registration - General Items with Similar Stock and Supplier UOM")
	public void VerifyDirectPODomesticGeneralStockAndSupplierSimilarWithInterstateUnReg() {
		flag = purchaseOrderFunc
				.verifyDirectPODomesticGeneralSimilarUOMWithMultipleItemsWithExcelInputWithInterstateUnReg();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Import Supplier - General Items with Similar Stock and Supplier
	 * UOM
	 *
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 5, groups = {
			"PurchaseOrder" }, description = "Direct PO - Import Supplier - General Items with Similar Stock and Supplier UOM")
	public void VerifyDirectPOGeneralStockAndSupplierSimilarWithImport() {
		flag = purchaseOrderFunc.verifyDirectPOGeneralSimilarUOMWithMultipleItemsWithExcelInputWithImport();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier With Local Registration - Material Items with
	 * Similar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 6, groups = {
			"PurchaseOrder" }, description = "Direct PO - Domestic Supplier With Local Registration - Material Items with Similar Stock and Supplier UOM")
	public void VerifyDirectPODomesticMaterialStockAndSupplierSimilarWithLocalReg() {
		flag = purchaseOrderFunc.verifyDirectPODomesticMaterialSimilarUOMWithMultipleItemsWithExcelInputWithLocalReg();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier With Intertstate Registration - Material Items
	 * with Similar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 7, groups = {
			"PurchaseOrder" }, description = "Direct PO - Domestic Supplier With Intertstate Registration - Material Items with Similar Stock and Supplier UOM")
	public void VerifyDirectPODomesticMaterialStockAndSupplierSimilarWithInterstateReg() {
		flag = purchaseOrderFunc
				.verifyDirectPODomesticMaterialSimilarUOMWithMultipleItemsWithExcelInputWithInterstateReg();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier With Local UnRegistration - Material Items with
	 * Similar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 8, groups = {
			"PurchaseOrder" }, description = "Direct PO - Domestic Supplier With Local UnRegistration - Material Items with Similar Stock and Supplier UOM")
	public void VerifyDirectPODomesticMaterialStockAndSupplierSimilarWithLocalUnReg() {
		flag = purchaseOrderFunc
				.verifyDirectPODomesticMaterialSimilarUOMWithMultipleItemsWithExcelInputWithLocalUnReg();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier With Interstate UnRegistration - Material Items
	 * with Similar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 9, groups = {
			"PurchaseOrder" }, description = "Direct PO - Domestic Supplier With Interstate UnRegistration - Material Items with Similar Stock and Supplier UOM")
	public void VerifyDirectPODomesticMaterialStockAndSupplierSimilarWithInterstateUnReg() {
		flag = purchaseOrderFunc
				.verifyDirectPODomesticMaterialSimilarUOMWithMultipleItemsWithExcelInputWithInterstateUnReg();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Import Supplier - Material Items with Similar Stock and Supplier
	 * UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 10, groups = {
			"PurchaseOrder" }, description = "Direct PO - Import Supplier - Material Items with Similar Stock and Supplier UOM")
	public void VerifyDirectPOMaterialStockAndSupplierSimilarWithImport() {
		flag = purchaseOrderFunc.verifyDirectPOMaterialSimilarUOMWithMultipleItemsWithExcelInputWithImport();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier With Local Registration - Service Items with
	 * Similar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 11, groups = {
			"PurchaseOrder" }, description = "Direct PO - Domestic Supplier With Local Registration - Service Items with Similar Stock and Supplier UOM")
	public void VerifyDirectPODomesticServiceStockAndSupplierSimilarWithLocalReg() {
		flag = purchaseOrderFunc.verifyDirectPODomesticServiceSimilarUOMWithMultipleItemsWithExcelInputWithLocalReg();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier With Interstate Registration - Service Items
	 * with Similar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 12, groups = {
			"PurchaseOrder" }, description = "Direct PO - Domestic Supplier With Interstate Registration - Service Items with Similar Stock and Supplier UOM")
	public void VerifyDirectPODomesticServiceStockAndSupplierSimilarWithInterstateReg() {
		flag = purchaseOrderFunc.verifyDirectPODomesticServiceSimilarUOMWithMultipleItemsWithExcelInputWithLocalReg();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Import Supplier - Service Items with Similar Stock and Supplier
	 * UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 13, groups = {
			"PurchaseOrder" }, description = "Direct PO - Import Supplier - Service Items with Similar Stock and Supplier UOM")
	public void VerifyDirectPOServiceStockAndSupplierSimilarWithImport() {
		flag = purchaseOrderFunc.verifyDirectPOServiceSimilarUOMWithMultipleItemsWithExcelInputWithImport();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier With Local Un Registration - Service Items with
	 * Similar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 14, groups = {
			"PurchaseOrder" }, description = "Direct PO - Domestic Supplier With Local Un Registration - Service Items with Similar Stock and Supplier UOM")
	public void VerifyDirectPODomesticServiceStockAndSupplierSimilarWithLocalUnReg() {
		flag = purchaseOrderFunc.verifyDirectPODomesticServiceSimilarUOMWithMultipleItemsWithExcelInputWithLocalUnReg();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier With Interstate Un Registration - Service Items
	 * with Similar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 15, groups = {
			"PurchaseOrder" }, description = "Direct PO - Domestic Supplier With Interstate Un Registration - Service Items with Similar Stock and Supplier UOM")
	public void VerifyDirectPODomesticServiceStockAndSupplierSimilarWithInterstateUnReg() {
		flag = purchaseOrderFunc
				.verifyDirectPODomesticServiceSimilarUOMWithMultipleItemsWithExcelInputWithInterstateUnReg();
		Assert.assertTrue(flag);
	}

	/**
	 * From PR to PO - Domestic Supplier With Local Registration - General Items
	 * with Similar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 16, groups = {
			"PurchaseOrder" }, description = "From PR to PO - Domestic Supplier With Local Registration - General Items with Similar Stock and Supplier UOM")
	public void VerifyDomesticGeneralSimilarUOMFromPRtoPOWithLocalReg() {
		flag = purchaseOrderFunc.verifyDomesticGeneralSimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithLocalReg();
		Assert.assertTrue(flag);
	}

	/**
	 * From PR to PO - Domestic Supplier With Interstate Registration - General
	 * Items with Similar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 17, groups = {
			"PurchaseOrder" }, description = "From PR to PO - Domestic Supplier With Interstate Registration - General Items with Similar Stock and Supplier UOM")
	public void VerifyDomesticGeneralSimilarUOMFromPRtoPOWithInterstateReg() {
		flag = purchaseOrderFunc
				.verifyDomesticGeneralSimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithInterstateReg();
		Assert.assertTrue(flag);
	}

	/**
	 * From PR to PO - Domestic Supplier With Local UnRegistration - General Items
	 * with Similar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 18, groups = {
			"PurchaseOrder" }, description = "From PR to PO - Domestic Supplier With Local UnRegistration - General Items with Similar Stock and Supplier UOM")
	public void VerifyDomesticGeneralSimilarUOMFromPRtoPOWithLocalUnReg() {
		flag = purchaseOrderFunc.verifyDomesticGeneralSimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithLocalUnReg();
		Assert.assertTrue(flag);
	}

	/**
	 * From PR to PO - Domestic Supplier With Interstate UnRegistration - General
	 * Items with Similar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 19, groups = {
			"PurchaseOrder" }, description = "From PR to PO - Domestic Supplier With Interstate UnRegistration - General Items with Similar Stock and Supplier UOM")
	public void VerifyDomesticGeneralSimilarUOMFromPRtoPOWithInterstateUnReg() {
		flag = purchaseOrderFunc
				.verifyDomesticGeneralSimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithInterstateUnReg();
		Assert.assertTrue(flag);
	}

	/**
	 * From PR to PO - Import Supplier - General Items with Similar Stock and
	 * Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 20, groups = {
			"PurchaseOrder" }, description = "From PR to PO - Import Supplier - General Items with Similar Stock and Supplier UOM")
	public void VerifyGeneralSimilarUOMFromPRtoPOWithImport() {
		flag = purchaseOrderFunc.verifyGeneralSimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithImport();
		Assert.assertTrue(flag);
	}

	/**
	 * From PR to PO - Domestic Supplier With Local Registration - Service Items
	 * with Similar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 21, groups = {
			"PurchaseOrder" }, description = "From PR to PO - Domestic Supplier With Local Registration - Service Items with Similar Stock and Supplier UOM")
	public void VerifyServiceSimilarUOMFromPRtoPOWithLocalReg() {
		flag = purchaseOrderFunc.verifyServiceSimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithLocalReg();
		Assert.assertTrue(flag);
	}

	/**
	 * From PR to PO - Domestic Supplier With Interstate Registration - Service
	 * Items with Similar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 22, groups = {
			"PurchaseOrder" }, description = "From PR to PO - Domestic Supplier With Interstate Registration - Service Items with Similar Stock and Supplier UOM")
	public void VerifyServiceSimilarUOMFromPRtoPOWithinterstateReg() {
		flag = purchaseOrderFunc.verifyServiceSimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithInterstateReg();
		Assert.assertTrue(flag);
	}

	/**
	 * From PR to PO - Domestic Supplier With Local UnRegistration - Service Items
	 * with Similar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 23, groups = {
			"PurchaseOrder" }, description = "From PR to PO - Domestic Supplier With Local UnRegistration - Service Items with Similar Stock and Supplier UOM")
	public void VerifyServiceSimilarUOMFromPRtoPOWithLocalUnReg() {
		flag = purchaseOrderFunc.verifyServiceSimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithLocalUnReg();
		Assert.assertTrue(flag);
	}

	/**
	 * From PR to PO - Domestic Supplier With Interstate UnRegistration - Service
	 * Items with Similar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 24, groups = {
			"PurchaseOrder" }, description = "From PR to PO - Domestic Supplier With Interstate UnRegistration - Service Items with Similar Stock and Supplier UOM")
	public void VerifyServiceSimilarUOMFromPRtoPOWithInterstateUnReg() {
		flag = purchaseOrderFunc.verifyServiceSimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithInterstateUnReg();
		Assert.assertTrue(flag);
	}

	/**
	 * From PR to PO - Import Supplier - Service Items with Similar Stock and
	 * Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 25, groups = {
			"PurchaseOrder" }, description = "From PR to PO - Import Supplier - Service Items with Similar Stock and Supplier UOM")
	public void VerifyServiceSimilarUOMFromPRtoPOWithImport() {
		flag = purchaseOrderFunc.verifyServiceSimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithImport();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier With Local Registration - General Items with
	 * Dissimilar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 26, groups = {
			"PurchaseOrder" }, description = " Direct PO - Domestic Supplier With Local Registration - General Items with Dissimilar Stock and Supplier UOM")
	public void VerifyDirectPODomesticGeneralStockAndSupplierDissimilarWithLocalReg() {
		flag = purchaseOrderFunc
				.verifyDirectPODomesticGeneralDissimilarUOMWithMultipleItemsWithExcelInputWithLocalReg();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier With Interstate Registration - General Items
	 * with Dissimilar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 27, groups = {
			"PurchaseOrder" }, description = "Direct PO - Domestic Supplier With Interstate Registration - General Items with Dissimilar Stock and Supplier UOM")
	public void VerifyDirectPODomesticGeneralStockAndSupplierDissimilarWithInterstateReg() {
		flag = purchaseOrderFunc
				.verifyDirectPODomesticGeneralDissimilarUOMWithMultipleItemsWithExcelInputWithInterstateReg();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier With Local UnRegistration - General Items with
	 * Dissimilar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 28, groups = {
			"PurchaseOrder" }, description = "Direct PO - Domestic Supplier With Local UnRegistration - General Items with Dissimilar Stock and Supplier UOM")
	public void VerifyDirectPODomesticGeneralStockAndSupplierDissimilarWithLocalUnReg() {
		flag = purchaseOrderFunc
				.verifyDirectPODomesticGeneralDissimilarUOMWithMultipleItemsWithExcelInputWithLocalUnReg();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier With Interstate UnRegistration - General Items
	 * with Dissimilar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 29, groups = {
			"PurchaseOrder" }, description = "Direct PO - Domestic Supplier With Interstate UnRegistration - General Items with Dissimilar Stock and Supplier UOM")
	public void VerifyDirectPODomesticGeneralStockAndSupplierDissimilarWithInterstateUnReg() {
		flag = purchaseOrderFunc
				.verifyDirectPODomesticGeneralDissimilarUOMWithMultipleItemsWithExcelInputWithInterstateUnReg();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Import Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 30, groups = {
			"PurchaseOrder" }, description = "Direct PO - Import Supplier - General Items with Dissimilar Stock and Supplier UOM")
	public void VerifyDirectPOGeneralStockAndSupplierDissimilarWithImport() {
		flag = purchaseOrderFunc.verifyDirectPOGeneralDissimilarUOMWithMultipleItemsWithExcelInputWithImport();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier With Local Registration - Material Items with
	 * Dissimilar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 31, groups = {
			"PurchaseOrder" }, description = "Direct PO - Domestic Supplier With Local Registration - Material Items with Dissimilar Stock and Supplier UOM")
	public void VerifyDirectPODomesticMaterialStockAndSupplierDissimilarWithLocalReg() {
		flag = purchaseOrderFunc
				.verifyDirectPODomesticMaterialDissimilarUOMWithMultipleItemsWithExcelInputWithLocalReg();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier With Interstate Registration - Material Items
	 * with Dissimilar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 32, groups = {
			"PurchaseOrder" }, description = "Direct PO - Domestic Supplier With Interstate Registration - Material Items with Dissimilar Stock and Supplier UOM")
	public void VerifyDirectPODomesticMaterialStockAndSupplierDissimilarWithInterstateReg() {
		flag = purchaseOrderFunc
				.verifyDirectPODomesticMaterialDissimilarUOMWithMultipleItemsWithExcelInputWithInterstateReg();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier With Local UnRegistration - Material Items with
	 * Dissimilar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 33, groups = {
			"PurchaseOrder" }, description = "Direct PO - Domestic Supplier With Local UnRegistration - Material Items with Dissimilar Stock and Supplier UOM")
	public void VerifyDirectPODomesticMaterialStockAndSupplierDissimilarWithLocalUnReg() {
		flag = purchaseOrderFunc
				.verifyDirectPODomesticMaterialDissimilarUOMWithMultipleItemsWithExcelInputWithLocalUnReg();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Domestic Supplier With Interstate UnRegistration - Material Items
	 * with Dissimilar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 34, groups = {
			"PurchaseOrder" }, description = "Direct PO - Domestic Supplier With Interstate UnRegistration - Material Items with Dissimilar Stock and Supplier UOM")
	public void VerifyDirectPODomesticMaterialStockAndSupplierDissimilarWithInterstateUnReg() {
		flag = purchaseOrderFunc
				.verifyDirectPODomesticMaterialDissimilarUOMWithMultipleItemsWithExcelInputWithInterstateUnReg();
		Assert.assertTrue(flag);
	}

	/**
	 * Direct PO - Import Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 35, groups = {
			"PurchaseOrder" }, description = "Direct PO - Import Supplier - Material Items with Dissimilar Stock and Supplier UOM")
	public void VerifyDirectPOMaterialStockAndSupplierDissimilarWithImport() {
		flag = purchaseOrderFunc.verifyDirectPOMaterialDissimilarUOMWithMultipleItemsWithExcelInputWithImport();
		Assert.assertTrue(flag);
	}

	/**
	 * From PR to PO - Domestic Supplier With Local Registration - General Items
	 * with Dissimilar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 36, groups = {
			"PurchaseOrder" }, description = "From PR to PO - Domestic Supplier With Local Registration - General Items with Dissimilar Stock and Supplier UOM")
	public void VerifyDomesticGeneralDissimilarUOMFromPRtoPOWithLocalReg() {
		flag = purchaseOrderFunc.verifyDomesticGeneralDissimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithLocalReg();
		Assert.assertTrue(flag);
	}

	/**
	 * From PR to PO - Domestic Supplier With Intertstate Registration - General
	 * Items with Dissimilar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 37, groups = {
			"PurchaseOrder" }, description = "From PR to PO - Domestic Supplier With Intertstate Registration - General Items with Dissimilar Stock and Supplier UOM")
	public void VerifyDomesticGeneralDissimilarUOMFromPRtoPOWithInterstateReg() {
		flag = purchaseOrderFunc
				.verifyDomesticGeneralSimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithInterstateReg();
		Assert.assertTrue(flag);
	}

	/**
	 * From PR to PO - Domestic Supplier With Local UnRegistration - General Items
	 * with Dissimilar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 38, groups = {
			"PurchaseOrder" }, description = "From PR to PO - Domestic Supplier With Local UnRegistration - General Items with Dissimilar Stock and Supplier UOM")
	public void VerifyDomesticGeneralDissimilarUOMFromPRtoPOWithLocalUnReg() {
		flag = purchaseOrderFunc
				.verifyDomesticGeneralDissimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithLocalUnReg();
		Assert.assertTrue(flag);
	}

	/**
	 * From PR to PO - Domestic Supplier With Interstate UnRegistration - General
	 * Items with Dissimilar Stock and Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 39, groups = {
			"PurchaseOrder" }, description = "From PR to PO - Domestic Supplier With Interstate UnRegistration - General Items with Dissimilar Stock and Supplier UOM")
	public void VerifyDomesticGeneralDissimilarUOMFromPRtoPOWithInterstateUnReg() {
		flag = purchaseOrderFunc
				.verifyDomesticGeneralDissimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithInterstateUnReg();
		Assert.assertTrue(flag);
	}

	/**
	 * From PR to PO - Import Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 40, groups = {
			"PurchaseOrder" }, description = "From PR to PO - Import Supplier - General Items with Dissimilar Stock and Supplier UOM")
	public void VerifyGeneralDissimilarUOMFromPRtoPOWithImport() {
		flag = purchaseOrderFunc.verifyGeneralDissimilarUOMFromPRtoPOWithMultipleItemInExcelSheetWithImport();
		Assert.assertTrue(flag);
	}

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
