package com.roadmapERP;

import java.lang.reflect.Method;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks November 14, 2025
 */
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.POAmendmentFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class POAmendmentTest2 extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static POAmendmentFunctions poAmendmentFunc = new POAmendmentFunctions();
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
	 * 130 Direct PO - Domestic Supplier - Service Items with Similar Stock and
	 * Supplier UOM With Local Registered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 1, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - Service Items with Similar Stock and Supplier UOM With Local Registered?")
	public void VerifyPoAmendModifyDirectPODomesticServiceSimilarStockAndSupplierUOMWithLocalReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputLocalReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - Service Items with Similar Stock and
	 * Supplier UOM With Interstate Registered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 2, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM With Intestate Registered?")
	public void VerifyPoAmendModifyDirectPODomesticServiceSimilarStockAndSupplierUOMWithInterstateReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - Service Items with Similar Stock and
	 * Supplier UOM With Local UnRegistered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 3, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM With Local UnRegistered?")
	public void VerifyPoAmendModifyDirectPODomesticServicelSimilarStockAndSupplierUOMWithLocalUnReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputLocalUnReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - Service Items with Similar Stock and
	 * Supplier UOM With Interstate UnRegistered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 4, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM With Interstate UnRegistered?")
	public void VerifyPoAmendModifyDirectPODomesticServiceSimilarStockAndSupplierUOMWithInterstateUnReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateUnReg();
	}

	/**
	 * 130 Direct PO - Import Supplier - General Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 5, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM With Interstate UnRegistered?")
	public void VerifyPoAmendModifyDirectPOGeneralSimilarStockAndSupplierUOMImport() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPOGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputImport();
	}

	/**
	 * 130 Direct PO - Import Supplier - General Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 6, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM With Interstate UnRegistered?")
	public void VerifyPoAmendModifyDirectPOMaterialSimilarStockAndSupplierUOMImport() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPOMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputImport();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM With Local Registered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 7, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM With Local Registered?")
	public void VerifyPoAmendModifyDirectPODomesticGeneralDissimilarStockAndSupplierUOMWithLocalReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputLocalReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM With Interstate Registered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 8, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM With Intestate Registered?")
	public void VerifyPoAmendModifyDirectPODomesticGeneralDissimilarStockAndSupplierUOMWithInterstateReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM With Local UnRegistered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 9, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM With Local UnRegistered?")
	public void VerifyPoAmendModifyDirectPODomesticGeneralDissimilarStockAndSupplierUOMWithLocalUnReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputLocalUnReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM With Interstate UnRegistered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 10, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM With Interstate UnRegistered?")
	public void VerifyPoAmendModifyDirectPODomesticGeneralDissimilarStockAndSupplierUOMWithInterstateUnReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateUnReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM With Local Registered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 11, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - Material Items with Dissimilar Stock and Supplier UOM With Local Registered?")
	public void VerifyPoAmendModifyDirectPODomesticMaterialDissimilarStockAndSupplierUOMWithLocalReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputLocalReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM With Interstate Registered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 12, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM With Intestate Registered?")
	public void VerifyPoAmendModifyDirectPODomesticMaterialDissimilarStockAndSupplierUOMWithInterstateReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM With Local UnRegistered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 13, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM With Local UnRegistered?")
	public void VerifyPoAmendModifyDirectPODomesticMaterialDissimilarStockAndSupplierUOMWithLocalUnReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputLocalUnReg();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM With Interstate UnRegistered?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 14, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM With Interstate UnRegistered?")
	public void VerifyPoAmendModifyDirectPODomesticMaterialDissimilarStockAndSupplierUOMWithInterstateUnReg() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateUnReg();
	}

	/**
	 * 130 Direct PO - Import Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 15, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM With Interstate UnRegistered?")
	public void VerifyPoAmendModifyDirectPOGeneralDissimilarStockAndSupplierUOMImport() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPOGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputImport();
	}

	/**
	 * 130 Direct PO - Import Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 16, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM With Interstate UnRegistered?")
	public void VerifyPoAmendModifyDirectPOMaterialDissimilarStockAndSupplierUOMImport() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPOMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputImport();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - General Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 17, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendCloseshortDirectPODomesticGeneralSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortDirectPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * 131 Direct PO - Domestic Supplier - Material Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 18, groups = {
			"Purchase Order Amendment" }, description = "131 Direct PO - Domestic Supplier - Material Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendCloseshortDirectPODomesticMaterialSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortDirectPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * 132 Direct PO - Domestic Supplier - Service Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 19, groups = {
			"Purchase Order Amendment" }, description = "132 Direct PO - Domestic Supplier - Service Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendCloseshortDirectPODomesticServiceSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * 133 Direct PO - Import Supplier - General Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 20, groups = {
			"Purchase Order Amendment" }, description = "133 Direct PO - Import Supplier - General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendCloseshortDirectPOImportGeneralSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortDirectPOImportGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * 134 Direct PO - Import Supplier - Material Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 21, groups = {
			"Purchase Order Amendment" }, description = "134 Direct PO - Import Supplier - Material Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendCloseshortDirectPOImportMaterialSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortDirectPOImportMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Domestic Supplier - General Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 22, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendCloseshortPrToPoDomesticGeneralSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortPRToPODomesticGenaeralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Domestic Supplier - Service Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 23, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier - Service Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendCloseshortPrToPoDomesticServiceSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortPRToPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Import Supplier - General Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 24, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Import Supplier - General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendCloseshortPrToPoImportGeneralSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortPRToPOImportGenaeralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * Direct PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 25, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendCloseshortDirectPODomesticGeneralDissimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * Direct PO - Domestic Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 26, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendCloseshortDirectPODomesticMaterialDissimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortDirectPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * Direct PO - Domestic Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 27, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendCloseshortDirectPOImportMaterialDissimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortDirectPOImportMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Import Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 28, groups = {
			"Purchase Order Amendment" }, description = "128 From PR to PO - Import Supplier - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyCloseshortAmendPRToPOImportGeneralDissimilarStockAndSupplierUOM() {
		poAmendmentFunc.verifyPRToPOImportGeneralWithDissimilarStockAndSupplierUOMWithMultipleItem();
	}

	/**
	 * cancel the purchase Order amendment document?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 29, groups = {
			"Purchase Order Amendment" }, description = "cancel the purchase Order amendment document?")
	public void VerifyPoAmendmentCancelLineAndCancelDocuments() {
		poAmendmentFunc.verifyPoAmendmentCancelDocumentsAndCancelLine();
	}

	/**
	 * From PR to PO - Domestic Supplier - General Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 30, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyPrToPoDomesticGeneralSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyPrToPoDomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Domestic Supplier - Service Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 31, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier - Service Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyPrToPoDomesticServiceSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyPrToPoDomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Import Supplier - General Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 32, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Import Supplier - General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyPrToPoImportGeneralSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyPrToPoImportGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 33, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyPrToPoDomesticGeneralDissimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyPrToPoDomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Import Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 34, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Import Supplier - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyPrToPoImportGeneralDissimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyPrToPoImportGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 35, groups = {
			"Purchase Order Amendment" }, description = "126 From PR to PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyCloseshortPoAmendPRToPODomesticGeneralDissimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortPRToPODomesticGenaeralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Domestic Supplier - Material Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 36, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier - Material Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendPRToPODomesticMaterialSimilarStockAndSupplierUOM() {
		poAmendmentFunc.verifyPRToPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItem();
	}

	/**
	 * From PR to PO - Domestic Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 37, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendPRToPODomesticMaterialDissimilarStockAndSupplierUOM() {
		poAmendmentFunc.verifyPRToPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItem();
	}

	/**
	 * From PR to PO - Import Supplier - Material Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 38, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Import Supplier - Material Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendPRToPOImportMaterialSimilarStockAndSupplierUOM() {
		poAmendmentFunc.verifyPRToPOImportMaterialWithSimilarStockAndSupplierUOMWithMultipleItem();
	}

	/**
	 * From PR to PO - Import Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 39, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Import Supplier - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendPRToPOImportMaterialDissimilarStockAndSupplierUOM() {
		poAmendmentFunc.verifyPRToPOImportMaterialWithDissimilarStockAndSupplierUOMWithMultipleItem();
	}

	/**
	 * From PR to PO - Domestic Supplier - Material Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 40, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier - Material Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyPrToPoDomesticMaterialSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyPrToPoDomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Import Supplier - Material Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 41, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Import Supplier - Material Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyPrToPoImportMaterialSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyPrToPoImportMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Domestic Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 42, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyPrToPoDomesticMaterialDissimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyPrToPoDomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Import Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 43, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Import Supplier - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyPrToPoImportMaterialDissimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyPrToPoImportMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Import Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 44, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Import Supplier - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyCloseshortPoAmendPRToPODomesticMaterialSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortPRToPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Import Supplier - Material Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 45, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Import Supplier - Material Items with Similar Stock and Supplier UOM?")
	public void VerifyCloseshortPoAmendPRToPOImportMaterialSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortPRToPOImportMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Domestic Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 46, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyCloseshortPoAmendPRToPODomesticMaterialDissimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortPRToPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * From PR to PO - Import Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 47, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Import Supplier - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyCloseshortPoAmendPRToPOImportMaterialDissimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortPRToPOImportMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * Direct PO - Import Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Author("Deepan-GWL")
	@Test(priority = 48, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Import Supplier - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyCloseshortPoAmendDirectPOImportGeneralDissimilarStockAndSupplierUom() {
		poAmendmentFunc
				.verifyPoAmendCloseshortDirectPOImportGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
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
