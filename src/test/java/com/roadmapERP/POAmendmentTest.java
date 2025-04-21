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

public class POAmendmentTest extends RoadMapERPTest {
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
	 * 130 Direct PO - Domestic Supplier - General Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Test(priority = 15, groups = {
			"Purchase Order Amendment" }, description = "130 Direct PO - Domestic Supplier - General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyDirectPODomesticGeneralSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * 131 Direct PO - Domestic Supplier - Material Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Test(priority = 16, groups = {
			"Purchase Order Amendment" }, description = "131 Direct PO - Domestic Supplier - Material Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyDirectPODomesticMaterialSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * 132 Direct PO - Domestic Supplier - Service Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Test(priority = 17, groups = {
			"Purchase Order Amendment" }, description = "132 Direct PO - Domestic Supplier - Service Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyDirectPODomesticServiceSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * 133 Direct PO - Import Supplier - General Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Test(priority = 18, groups = {
			"Purchase Order Amendment" }, description = "133 Direct PO - Import Supplier - General Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyDirectPOImportGeneralSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPOImportGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * 134 Direct PO - Import Supplier - Material Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Test(priority = 19, groups = {
			"Purchase Order Amendment" }, description = "134 Direct PO - Import Supplier - Material Items with Similar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyDirectPOImportMaterialSimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPOImportMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * Direct PO - Domestic Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Test(priority = 20, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyDirectPODomesticGeneralDissimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * Direct PO - Domestic Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Test(priority = 21, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyDirectPODomesticMaterialDissimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * Direct PO - Import Supplier - General Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Test(priority = 22, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Import Supplier - General Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyDirectPOImportGeneralDissimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPOImportGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * Direct PO - Import Supplier - Material Items with Dissimilar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Test(priority = 23, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Import Supplier - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendModifyDirectPOImportMaterialDissimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendModifyDirectPOImportMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
	}

	/**
	 * 130 Direct PO - Domestic Supplier - General Items with Similar Stock and
	 * Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	@Test(priority = 24, groups = {
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
	@Test(priority = 25, groups = {
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
	@Test(priority = 26, groups = {
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
	@Test(priority = 27, groups = {
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
	@Test(priority = 28, groups = {
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
	@Test(priority = 29, groups = {
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
	@Test(priority = 30, groups = {
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
	@Test(priority = 31, groups = {
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
	@Test(priority = 32, groups = {
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
	@Test(priority = 33, groups = {
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
	@Test(priority = 34, groups = {
			"Purchase Order Amendment" }, description = "Direct PO - Domestic Supplier - Material Items with Dissimilar Stock and Supplier UOM?")
	public void VerifyPoAmendCloseshortDirectPOImportMaterialDissimilarStockAndSupplierUOM() {
		poAmendmentFunc
				.verifyPoAmendCloseshortDirectPOImportMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput();
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
