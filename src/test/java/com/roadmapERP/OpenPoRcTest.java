package com.roadmapERP;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks May 01, 2025
 */
import static org.testng.Assert.assertTrue;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.OpenPoRcFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class OpenPoRcTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static OpenPoRcFunctions openPoRcFunctions = new OpenPoRcFunctions();

	/**
	 * Create - Standard - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 1, groups = {
			"Open PO/RC" }, description = "Create - Standard - Domestic Supplier with Stock and Supplier UOM similar")

	public void VerifyCreateStandardDomesticWithSimilarStockAndSupplierUOM() {
		openPoRcFunctions.verifyCreateStandardDomesticWithStockAndSupplierSimilarUOM();
	}

	/**
	 * Create - Standard - Domestic Supplier with Create - Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 2, groups = {
			"Open PO/RC" }, description = "Create - Standard - Domestic Supplier with Create - Stock and Supplier UOM dissimilar")
	public void VerifyCreateStandardDomesticWithDissimilarStockAndSupplierUOM() {
		openPoRcFunctions.verifyCreateStandardDomesticWithStockAndSupplierDissimilarUOM();
	}

	/**
	 * Create - Standard - Import Supplier with Create - Stock and Supplier UOM
	 * similar
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 3, groups = {
			"Open PO/RC" }, description = "Create - Standard - Import Supplier with Create - Stock and Supplier UOM similar")

	public void VerifyCreateStandardImportWithSimilarStockAndSupplierUOM() {
		openPoRcFunctions.verifyCreateStandardImportWithStockAndSupplierSimilarUOM();
	}

	/**
	 * Create - Standard - Import Supplier with Create - Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 4, groups = {
			"Open PO/RC" }, description = "Create - Standard - Import Supplier with Create - Stock and Supplier UOM dissimilar")

	public void VerifyCreateStandardImportWithDissimilarStockAndSupplierUOM() {
		openPoRcFunctions.verifyCreateStandardImportWithStockAndSupplierDissimilarUOM();
	}

	/**
	 * Create - Stock transfer - Domestic Supplier with Stock and Supplier UOM
	 * similar
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 5, groups = {
			"Open PO/RC" }, description = "Create - Stock transfer - Domestic Supplier with Stock and Supplier UOM similar")
	public void VerifyCreateStockTransferDomesticWithSimilarStockAndSupplierUOM() {
		openPoRcFunctions.verifyCreateStockTransferDomesticWithStockAndSupplierSimilarUOM();
	}

	/**
	 * Create - Stock transfer - Domestic Supplier with Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 6, groups = {
			"Open PO/RC" }, description = "Create - Stock transfer - Domestic Supplier with Stock and Supplier UOM dissimilar")
	public void VerifyCreateStockTransferDomesticWithDissimilarStockAndSupplierUOM() {
		openPoRcFunctions.verifyCreateStockTransferDomesticWithStockAndSupplierDissimilarUOM();
	}

	/**
	 * Create - Service - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 7, groups = {
			"Open PO/RC" }, description = "Create - Service - Domestic Supplier with Stock and Supplier UOM similar")

	public void VerifyCreateServiceDomesticWithSimilarStockAndSupplierUOM() {
		openPoRcFunctions.verifyCreateServiceDomesticWithStockAndSupplierSimilarUOM();
	}

	/**
	 * Open Purchase Order - Line Wise Cancel
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 8, groups = { "Open PO/RC" }, description = "Open Purchase Order - Line Wise Cancel")

	public void VerifyOpenPOLineWiseAndDocumentsCancel() {
		openPoRcFunctions.verifyCancelOpenPODocuments();
		flag = true;
	}

	/**
	 * Open Purchase Order document Cancel
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 9, groups = { "Open PO/RC" }, description = "Open Purchase Order document Cancel")

	public void VerifyOpenPODocumentsCancel() {
		// TODO Covered in
		// VerifyOpenPOLineWiseAndDocumentsCancel()
		assertTrue(flag);
	}

	/**
	 * Check whether all the filter fields are working?
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 10, groups = { "Open PO/RC" }, description = "Check whether all the filter fields are working?")

	public void VerifyFilterOpenPoTableData() {
		openPoRcFunctions.verifyFilterOpenPOTableData();
	}

	/**
	 * Amend - Standard - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 11, groups = {
			" Amendment Open PO/RC Addition" }, description = "Amend - Standard - Domestic Supplier with Stock and Supplier UOM similar")

	public void VerifyAmendStandardDomesticSupplierWithStockAndSupplierUOMSimilar() {
		openPoRcFunctions.verifyAmendStandardDomesticWithStockAndSupplierSimilarUOM();
	}

	/**
	 * Amend - Standard - Domestic Supplier with Amend - Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 12, groups = {
			" Amendment Open PO/RC Addition" }, description = "Amend - Standard - Domestic Supplier with Amend - Stock and Supplier UOM dissimilar")

	public void VerifyAmendStandardDomesticSupplierWithStockAndSupplierUOMDissimilar() {
		openPoRcFunctions.verifyAmendStandardDomesticWithStockAndSupplierDissimilarUOM();
	}

	/**
	 * Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM
	 * similar
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 13, groups = {
			" Amendment Open PO/RC Addition" }, description = "Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM similar")

	public void VerifyAmendStandardImportSupplierWithStockAndSupplierUOMSimilar() {
		openPoRcFunctions.verifyAmendStandardImportWithStockAndSupplierSimilarUOM();
	}

	/**
	 * Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 14, groups = {
			" Amendment Open PO/RC Addition" }, description = "Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM dissimilar")

	public void VerifyAmendStandardImportSupplierWithStockAndSupplierUOMDissimilar() {
		openPoRcFunctions.verifyAmendStandardImportWithStockAndSupplierDissimilarUOM();
	}

	/**
	 * Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM
	 * similar
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 15, groups = {
			" Amendment Open PO/RC Addition" }, description = "Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM similar")

	public void VerifyAmendStockTransferDomesticSupplierWithStockAndSupplierUOMSimilar() {
		openPoRcFunctions.verifyAmendStockTransferDomesticWithStockAndSupplierSimilarUOM();
	}

	/**
	 * Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 16, groups = {
			" Amendment Open PO/RC Addition" }, description = "Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM dissimilar")

	public void VerifyAmendStockTransferDomesticSupplierWithStockAndSupplierUOMDissimilar() {
		openPoRcFunctions.verifyAmendStockTransferDomesticWithStockAndSupplierDissimilarUOM();
	}

	/**
	 * Amend - Service - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	@Test(priority = 17, groups = {
			" Amendment Open PO/RC Addition" }, description = "Amend - Service - Domestic Supplier with Stock and Supplier UOM similar")

	public void VerifyAmendSeviceDomesticSupplierWithStockAndSupplierUOMSimilar() {
		openPoRcFunctions.verifyAmendSeviceDomesticWithStockAndSupplierSimilarUOM();
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
