package com.roadmapERP;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 10, 2025
 */
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.POAmendmentFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class POAmendmentTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static POAmendmentFunctions poAmendmentFunc = new POAmendmentFunctions();

	/**
	 * 112 Direct PO - Domestic Supplier - General Items with Similar Stock and
	 * Supplier UOM
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Test(priority = 1, groups = {
			"Purchase Order Amendment" }, description = "112 Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM")
	public void VerifyPoAmendDirectPODomesticGeneralSimilarStockAndSupplierUOM() {
		poAmendmentFunc.verifyDirectPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItem();
	}

	/**
	 * 113 Direct PO - Domestic Supplier - Material Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 2, groups = {
			"Purchase Order Amendment" }, description = "113 Direct PO - Domestic Supplier - Material Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPODomesticMaterialSimilarStockAndSupplierUOM() {
		poAmendmentFunc.verifyDirectPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItem();
	}

	/**
	 * 
	 * 114 Direct PO - Domestic Supplier - Service Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 3, groups = {
			"Purchase Order Amendment" }, description = "114 Direct PO - Domestic Supplier - Service Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPODomesticServiceSimilarStockAndSupplierUOM() {
		poAmendmentFunc.verifyDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItem();
	}

	/**
	 * From PR to PO - Domestic Supplier - General Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 4, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendPRToPODomesticGeneralSimilarStockAndSupplierUOM() {
		poAmendmentFunc.verifyPRToPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItem();
	}

	/**
	 * 115 Direct PO - Import Supplier - General Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 5, groups = {
			"Purchase Order Amendment" }, description = "115 Direct PO - Import Supplier - General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOImportGeneralSimilarStockAndSupplierUOM() {
		poAmendmentFunc.verifyDirectPOImportGeneralWithSimilarStockAndSupplierUOMWithMultipleItem();
	}

	/**
	 * 116 Direct PO - Import Supplier - Material Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 6, groups = {
			"Purchase Order Amendment" }, description = "116 Direct PO - Import Supplier - Material Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOImportMaterialSimilarStockAndSupplierUOM() {
		poAmendmentFunc.verifyDirectPOImportMaterialWithSimilarStockAndSupplierUOMWithMultipleItem();
	}

	/**
	 * From PR to PO - Domestic Supplier - Service Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 7, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Domestic Supplier - Service Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendPRToPODomesticServiceSimilarStockAndSupplierUOM() {
		poAmendmentFunc.verifyPRToPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItem();
	}

	/**
	 * From PR to PO - Import Supplier - General Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 8, groups = {
			"Purchase Order Amendment" }, description = "From PR to PO - Import Supplier - General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendPRToPOImportGeneralSimilarStockAndSupplierUOM() {
		poAmendmentFunc.verifyPRToPOImportGeneralWithSimilarStockAndSupplierUOMWithMultipleItem();
	}

	/**
	 * Direct PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Test(priority = 9, groups = {
			"Purchase Order Amendment" }, description = "122 Direct PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPODomesticGeneralDissimilarStockAndSupplierUOM() {
		poAmendmentFunc.verifyDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItem();
	}

	/**
	 * Direct PO - Domestic Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Test(priority = 10, groups = {
			"Purchase Order Amendment" }, description = "123 Direct PO - Domestic Supplier - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPODomesticMaterialDissimilarStockAndSupplierUOM() {
		poAmendmentFunc.verifyDirectPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItem();
	}

	/**
	 * Direct PO - Import Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Test(priority = 11, groups = {
			"Purchase Order Amendment" }, description = "124 Direct PO - Import Supplier - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOImportGeneralDissimilarStockAndSupplierUOM() {
		poAmendmentFunc.verifyDirectPOImportGeneralWithDissimilarStockAndSupplierUOMWithMultipleItem();
	}

	/**
	 * Direct PO - Import Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Test(priority = 12, groups = {
			"Purchase Order Amendment" }, description = "125 Direct PO - Import Supplier - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendDirectPOImportMaterialDissimilarStockAndSupplierUOM() {
		poAmendmentFunc.verifyDirectPOImportMaterialWithDissimilarStockAndSupplierUOMWithMultipleItem();
	}

	/**
	 * From PR to PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Test(priority = 13, groups = {
			"Purchase Order Amendment" }, description = "126 From PR to PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendPRToPODomesticGeneralDissimilarStockAndSupplierUOM() {
		poAmendmentFunc.verifyPRToPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItem();
	}

	/**
	 * From PR to PO - Import Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Test(priority = 14, groups = {
			"Purchase Order Amendment" }, description = "128 From PR to PO - Import Supplier - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPRToPOAmendImportGeneralDissimilarStockAndSupplierUOM() {
		poAmendmentFunc.verifyPRToPOImportGeneralWithDissimilarStockAndSupplierUOMWithMultipleItem();
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
