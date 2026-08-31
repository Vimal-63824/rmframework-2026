package com.roadmapERP;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks May 01, 2025
 */
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.OpenPoRcFunctions;
import utils.Mailer;
import utils.ReportLoger;
import utils.WebDriverBase;

public class OpenPoRcTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static OpenPoRcFunctions openPoRcFunctions = new OpenPoRcFunctions();
	static ReportLoger logs = new ReportLoger();
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
	 * Create - Standard - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
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
	@Author("Deepan-GWL")
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
	@Author("Deepan-GWL")
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
	@Author("Deepan-GWL")
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
	@Author("Deepan-GWL")
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
	@Author("Deepan-GWL")
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
	@Author("Deepan-GWL")
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
	@Author("Deepan-GWL")
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
	@Author("Deepan-GWL")
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
	@Author("Deepan-GWL")
	@Test(priority = 10, groups = { "Open PO/RC" }, description = "Check whether all the filter fields are working?")
	public void VerifyFilterOpenPoTableData() {
		openPoRcFunctions.verifyFilterOpenPOTableData();
	}

	/**
	 * Amend - Standard - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
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
	@Author("Deepan-GWL")
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
	@Author("Deepan-GWL")
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
	@Author("Deepan-GWL")
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
	@Author("Deepan-GWL")
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
	@Author("Deepan-GWL")
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
	@Author("Deepan-GWL")
	@Test(priority = 17, groups = {
			" Amendment Open PO/RC Addition" }, description = "Amend - Service - Domestic Supplier with Stock and Supplier UOM similar")
	public void VerifyAmendSeviceDomesticSupplierWithStockAndSupplierUOMSimilar() {
		openPoRcFunctions.verifyAmendSeviceDomesticWithStockAndSupplierSimilarUOM();
	}

	/**
	 * Amend - Standard - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 18, groups = {
			" Amendment Open PO/RC Deletion" }, description = "Amend - Standard - Domestic Supplier with Stock and Supplier UOM similar")
	public void VerifyDOIAmendStandardDomesticSupplierWithStockAndSupplierUOMSimilar() {
		openPoRcFunctions.verifyDOIAmendStandardDomesticWithStockAndSupplierSimilarUOM();
	}

	/**
	 * Amend - Standard - Domestic Supplier with Amend - Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 19, groups = {
			" Amendment Open PO/RC Deletion" }, description = "Amend - Standard - Domestic Supplier with Amend - Stock and Supplier UOM dissimilar")
	public void VerifyDOIAmendStandardDomesticSupplierWithStockAndSupplierUOMDissimilar() {
		openPoRcFunctions.verifyDOIAmendStandardDomesticWithStockAndSupplierDissimilarUOM();
	}

	/**
	 * Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM
	 * similar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 20, groups = {
			" Amendment Open PO/RC Deletion" }, description = "Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM similar")
	public void VerifyDOIAmendStandardImportSupplierWithStockAndSupplierUOMSimilar() {
		openPoRcFunctions.verifyDOIAmendStandardImportWithStockAndSupplierSimilarUOM();
	}

	/**
	 * Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 21, groups = {
			" Amendment Open PO/RC Deletion" }, description = "Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM dissimilar")
	public void VerifyDOIAmendStandardImportSupplierWithStockAndSupplierUOMDissimilar() {
		openPoRcFunctions.verifyDOIAmendStandardImportWithStockAndSupplierDissimilarUOM();
	}

	/**
	 * Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM
	 * similar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 22, groups = {
			" Amendment Open PO/RC Deletion" }, description = "Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM similar")
	public void VerifyDOIAmendStockTransferDomesticSupplierWithStockAndSupplierUOMSimilar() {
		openPoRcFunctions.verifyDOIAmendStockTransferDomesticWithStockAndSupplierSimilarUOM();
	}

	/**
	 * Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 23, groups = {
			" Amendment Open PO/RC Deletion" }, description = "Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM dissimilar")
	public void VerifyDOIAmendStockTransferDomesticSupplierWithStockAndSupplierUOMDissimilar() {
		openPoRcFunctions.verifyDOIAmendStockTransferDomesticWithStockAndSupplierDissimilarUOM();
	}

	/**
	 * Amend - Service - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 24, groups = {
			" Amendment Open PO/RC Deletion" }, description = "Amend - Service - Domestic Supplier with Stock and Supplier UOM similar")
	public void VerifyDOIAmendSeviceDomesticSupplierWithStockAndSupplierUOMSimilar() {
		openPoRcFunctions.verifyDOIAmendServiceDomesticWithStockAndSupplierSimilarUOM();
	}

	/**
	 * Amend - Standard - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 25, groups = {
			" Amendment Open PO/RC PriceChange" }, description = "Amend - Standard - Domestic Supplier with Stock and Supplier UOM similar")
	public void VerifyPCAmendStandardDomesticSupplierWithStockAndSupplierUOMSimilar() {
		openPoRcFunctions.verifyPCAmendStandardDomesticWithStockAndSupplierSimilarUOM();
	}

	/**
	 * Amend - Standard - Domestic Supplier with Amend - Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 26, groups = {
			" Amendment Open PO/RC PriceChange" }, description = "Amend - Standard - Domestic Supplier with Amend - Stock and Supplier UOM dissimilar")
	public void VerifyPCAmendStandardDomesticSupplierWithStockAndSupplierUOMDissimilar() {
		openPoRcFunctions.verifyPCAmendStandardDomesticWithStockAndSupplierDissimilarUOM();
	}

	/**
	 * Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM
	 * similar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 27, groups = {
			" Amendment Open PO/RC PriceChange" }, description = "Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM similar")
	public void VerifyPCAmendStandardImportSupplierWithStockAndSupplierUOMSimilar() {
		openPoRcFunctions.verifyPCAmendStandardImportWithStockAndSupplierSimilarUOM();
	}

	/**
	 * Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 28, groups = {
			" Amendment Open PO/RC PriceChange" }, description = "Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM dissimilar")
	public void VerifyPCAmendStandardImportSupplierWithStockAndSupplierUOMDissimilar() {
		openPoRcFunctions.verifyPCAmendStandardImportWithStockAndSupplierDissimilarUOM();
	}

	/**
	 * Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM
	 * similar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 29, groups = {
			" Amendment Open PO/RC PriceChange" }, description = "Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM similar")
	public void VerifyPCAmendStockTransferDomesticSupplierWithStockAndSupplierUOMSimilar() {
		openPoRcFunctions.verifyPCAmendStockTransferDomesticWithStockAndSupplierSimilarUOM();
	}

	/**
	 * Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 30, groups = {
			" Amendment Open PO/RC PriceChange" }, description = "Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM dissimilar")
	public void VerifyPCAmendStockTransferDomesticSupplierWithStockAndSupplierUOMDissimilar() {
		openPoRcFunctions.verifyPCAmendStockTransferDomesticWithStockAndSupplierDissimilarUOM();
	}

	/**
	 * Amend - Service - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 31, groups = {
			" Amendment Open PO/RC PriceChange" }, description = "Amend - Service - Domestic Supplier with Stock and Supplier UOM similar")
	public void VerifyPCAmendSeviceDomesticSupplierWithStockAndSupplierUOMSimilar() {
		openPoRcFunctions.verifyPCAmendServiceDomesticWithStockAndSupplierSimilarUOM();
	}

	/**
	 * Open PO Amendment document - Cancel
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 32, groups = {
			" Amendment Open PO/RC Cancel" }, description = "Open PO Amendment document - Cancel")
	public void VerifyOpenPOAmendCancelDocumentsAndItemLineWise() {
		openPoRcFunctions.verifyOpenPOAmendCancelDocumentAndLineWiseCancel();
		flag = true;
	}

	/**
	 * Open PO Amendment document - Cancel
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 33, groups = {
			" Amendment Open PO/RC Cancel" }, description = "Open PO Amendment document - Cancel")
	public void VerifyOpenPOAmendCancelLineWie() {
		logs.logging("INFO",
				"This Test Sceanrio Result Is Based On This Test Scenarios VerifyOpenPOAmendCancelDocumentsAndItemLineWise");
		assertTrue(flag);
	}

	/**
	 * Amend - Standard - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 34, groups = {
			" Amendment Open PO/RC Term Change" }, description = "Amend - Standard - Domestic Supplier with Stock and Supplier UOM similar")
	public void VerifyTermChangeAmendStandardDomesticSupplierWithStockAndSupplierUOMSimilar() {
		openPoRcFunctions.verifyTermChangeAmendStandardDomesticWithStockAndSupplierSimilarUOM();
	}

	/**
	 * Amend - Standard - Domestic Supplier with Amend - Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 35, groups = {
			" Amendment Open PO/RC TermChange" }, description = "Amend - Standard - Domestic Supplier with Amend - Stock and Supplier UOM dissimilar")
	public void VerifyTermChangeAmendStandardDomesticSupplierWithStockAndSupplierUOMDissimilar() {
		openPoRcFunctions.verifyTermChangeAmendStandardDomesticWithStockAndSupplierDissimilarUOM();
	}

	/**
	 * Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM
	 * similar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 36, groups = {
			" Amendment Open PO/RC TermChange" }, description = "Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM similar")
	public void VerifyTermChangeAmendStandardImportSupplierWithStockAndSupplierUOMSimilar() {
		openPoRcFunctions.verifyTermChangeAmendStandardImportWithStockAndSupplierSimilarUOM();
	}

	/**
	 * Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 37, groups = {
			" Amendment Open PO/RC TermChange" }, description = "Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM dissimilar")
	public void VerifyTermChangeAmendStandardImportSupplierWithStockAndSupplierUOMDissimilar() {
		openPoRcFunctions.verifyTermChangeAmendStandardImportWithStockAndSupplierDissimilarUOM();
	}

	/**
	 * Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM
	 * similar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 38, groups = {
			" Amendment Open PO/RC TermChange" }, description = "Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM similar")
	public void VerifyTermChangeAmendStockTransferDomesticSupplierWithStockAndSupplierUOMSimilar() {
		openPoRcFunctions.verifyTermChangeAmendStockTransferDomesticWithStockAndSupplierSimilarUOM();
	}

	/**
	 * Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 39, groups = {
			" Amendment Open PO/RC TermChange" }, description = "Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM dissimilar")
	public void VerifyTermChangeAmendStockTransferDomesticSupplierWithStockAndSupplierUOMDissimilar() {
		openPoRcFunctions.verifyTermChangeAmendStockTransferDomesticWithStockAndSupplierDissimilarUOM();
	}

	/**
	 * Amend - Service - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 40, groups = {
			" Amendment Open PO/RC TermChange" }, description = "Amend - Service - Domestic Supplier with Stock and Supplier UOM similar")
	public void VerifyTermChangeAmendSeviceDomesticSupplierWithStockAndSupplierUOMSimilar() {
		openPoRcFunctions.verifyTermChangeAmendServiceDomesticWithStockAndSupplierSimilarUOM();
	}

	/**
	 * Amend - Standard - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 41, groups = {
			" Amendment Open PO/RC Other Change" }, description = "Amend - Standard - Domestic Supplier with Stock and Supplier UOM similar")
	public void VerifyOtherChangeAmendStandardDomesticSupplierWithStockAndSupplierUOMSimilar() {
		openPoRcFunctions.verifyOtherChangeAmendStandardDomesticWithStockAndSupplierSimilarUOM();
	}

	/**
	 * Amend - Standard - Domestic Supplier with Amend - Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 42, groups = {
			" Amendment Open PO/RC OtherChange" }, description = "Amend - Standard - Domestic Supplier with Amend - Stock and Supplier UOM dissimilar")
	public void VerifyOtherChangeAmendStandardDomesticSupplierWithStockAndSupplierUOMDissimilar() {
		openPoRcFunctions.verifyOtherChangeAmendStandardDomesticWithStockAndSupplierDissimilarUOM();
	}

	/**
	 * Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM
	 * similar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 43, groups = {
			" Amendment Open PO/RC OtherChange" }, description = "Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM similar")
	public void VerifyOtherChangeAmendStandardImportSupplierWithStockAndSupplierUOMSimilar() {
		openPoRcFunctions.verifyOtherChangeAmendStandardImportWithStockAndSupplierSimilarUOM();
	}

	/**
	 * Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 44, groups = {
			" Amendment Open PO/RC OtherChange" }, description = "Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM dissimilar")
	public void VerifyOtherChangeAmendStandardImportSupplierWithStockAndSupplierUOMDissimilar() {
		openPoRcFunctions.verifyOtherChangeAmendStandardImportWithStockAndSupplierDissimilarUOM();
	}

	/**
	 * Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM
	 * similar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 45, groups = {
			" Amendment Open PO/RC OtherChange" }, description = "Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM similar")
	public void VerifyOtherChangeAmendStockTransferDomesticSupplierWithStockAndSupplierUOMSimilar() {
		openPoRcFunctions.verifyOtherChangeAmendStockTransferDomesticWithStockAndSupplierSimilarUOM();
	}

	/**
	 * Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 46, groups = {
			" Amendment Open PO/RC OtherChange" }, description = "Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM dissimilar")
	public void VerifyOtherChangeAmendStockTransferDomesticSupplierWithStockAndSupplierUOMDissimilar() {
		openPoRcFunctions.verifyOtherChangeAmendStockTransferDomesticWithStockAndSupplierDissimilarUOM();
	}

	/**
	 * Amend - Service - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 47, groups = {
			" Amendment Open PO/RC OtherChange" }, description = "Amend - Service - Domestic Supplier with Stock and Supplier UOM similar")
	public void VerifyOtherChangeAmendSeviceDomesticSupplierWithStockAndSupplierUOMSimilar() {
		openPoRcFunctions.verifyOtherChangeAmendServiceDomesticWithStockAndSupplierSimilarUOM();
	}

	/**
	 * Check whether all the filter fields are working?
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 48, groups = {
			" Amendment Open PO/RC OtherChange" }, description = "Check whether all the filter fields are working?")
	public void VerifyCheckWetherAllFieldWorkingInOpenPOAmend() {
		openPoRcFunctions.verifyFilterOpenPOAmendmentTableData();
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
