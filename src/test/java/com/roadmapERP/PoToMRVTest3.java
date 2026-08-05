package com.roadmapERP;

import java.io.IOException;
/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks December 17, 2025
 */
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.GrnPageFunctions;
import commonfunctions.supplychainmanagement.PurchaseOrderFunctions;
import commonfunctions.supplychainmanagement.SupplierScheduleRequestFunctions;
import utils.Mailer;
import utils.ReportLoger;
import utils.WebDriverBase;

public class PoToMRVTest3 extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();
	static GrnPageFunctions grnPageFunc = new GrnPageFunctions();
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
	 * Verify Grn Reverse With Domestic Supplier
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 1, groups = { "PurchaseOrder" }, description = "Verify Grn Reverse With Domestic Supplier")
	public void VerifyGrnReverseWithDomesticSupplier() {
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithFreePassWithGateEntryWithReverse();
	}

	/**
	 * Verify Grn Reverse With Import Supplier
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 2, groups = { "PurchaseOrder" }, description = "Verify Grn Reverse With Import Supplier")
	public void VerifyGrnReverseWithImportcSupplier() {
		grnPageFunc
				.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithFreePassWithGateEntryWithReverseWithImportSupplier();
	}

	/**
	 * Verify Grn Reverse With Po Amendment
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 3, groups = { "PurchaseOrder" }, description = "Verify Grn Reverse With Po Amendment")
	public void VerifyGrnReverseWithPOA() {
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithFreePassWithGateEntryWithReentryPOA();
	}

	/**
	 * Verify Grn Reverse With Po Amendment
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 4, groups = { "PurchaseOrder" }, description = "Verify Grn Reverse With Po Amendment")
	public void VerifyGrnReverseWithPOAImport() {
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithFreePassWithGateEntryWithReentryPOAWithImport();
	}

	/**
	 * Verify Grn Reverse With Domestic Supplier WithOut Gate Entry
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 5, groups = {
			"PurchaseOrder" }, description = "Verify Grn Reverse With Domestic Supplier WithOut Gate Entry")
	public void VerifyGrnReverseWithDomesticSupplierWithOutGateEntry() {
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithFreePassWithReverseWithoutGateEntry();
	}

	/**
	 * Verify Grn Reverse With Import Supplier WithOut Gate Entry
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 6, groups = {
			"PurchaseOrder" }, description = "Verify Grn Reverse With Import Supplier WithOut Gate Entry")
	public void VerifyGrnReverseWithWithOutGateEntryImport() {
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithFreePassWithReverseWithoutGateEntryImport();
	}

	/**
	 * Verify Grn Reverse With Po Amendment Without Gate Entry
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 7, groups = {
			"PurchaseOrder" }, description = "Verify Grn Reverse With Po Amendment Without Gate Entry")
	public void VerifyGrnReverseWithPOAWithoutGateEntry() {
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithFreePassWithOutGateEntryWithReentryPOA();
	}

	/**
	 * Verify Grn Reverse With Po Amendment Without Gate Entry Import Supplier
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 8, groups = {
			"PurchaseOrder" }, description = "Verify Grn Reverse With Po Amendment Without Gate Entry Import Supplier")
	public void VerifyGrnReverseWithPOAWithoutGateEntryImp() {
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithFreePassWithOutGateEntryWithReentryPOAImp();
	}

	/**
	 * Verify Reverse Grn With PR Amendment
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 9, groups = { "PoToGrn" }, description = "Verify Reverse Grn With PR Amendment")
	public void VerifyReverseGrnWithPRAmendment() {
		grnPageFunc.verifyGrnReverseWithPRAmendmentWithGateEntry();
	}

	/**
	 * Verify Reverse GRN With PR Amendment Without Gate Entry
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 10, groups = {
			"PoToGrn" }, description = "Verify Reverse GRN With PR Amendment Without Gate Entry")
	public void VerifyReverseGrnWithPRAmendmentWithOutGateEntry() {
		grnPageFunc.verifyGrnReverseWithPRAmendmentWithOutGateEntry();
	}

	/**
	 * Verify Reverse Grn With PR Amendment With Lower Quantity
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 11, groups = {
			"PoToGrn" }, description = "Verify Reverse Grn With PR Amendment With Lower Quantity")
	public void VerifyReverseGrnWithPRAmendmentWithLowerQuantity() {
		grnPageFunc.verifyGrnReverseWithPRAmendmentWithGateEntryWithLowerQuantity();
	}

	/**
	 * Verify Reverse GRN With PR Amendment Without Gate Entry With Lower
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 12, groups = {
			"PoToGrn" }, description = "Verify Reverse GRN With PR Amendment Without Gate Entry With Lower")
	public void VerifyReverseGrnWithPRAmendmentWithOutGateEntryWithLower() {
		grnPageFunc.verifyGrnReverseWithPRAmendmentWithOutGateEntryWithLowerQty();
	}

	/**
	 * Verify Po To MRV With MOQ
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 13, groups = { "PoToGrn" }, description = "Verify Po To MRV With MOQ")
	public void VerifyPoToMrvWithMoq() {
		grnPageFunc.verifyMoqQuantityFromPoToMrv();
	}

	/**
	 * Verify Pr To MRV With MOQ
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 14, groups = { "PoToGrn" }, description = "Verify Pr To MRV With MOQ")
	public void VerifyPrToMrvWithMoq() {
		grnPageFunc.verifyMoqQuantityFromPrToMrv();
	}

	/**
	 * Verify Reverse GRN With PR Amendment With Material Type
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 15, groups = {
			"PoToGrn" }, description = "Verify Reverse GRN With PR Amendment With Material Type")
	public void VerifyReverseGrnWithPRAmendmentWithMaterialType() {
		grnPageFunc.verifyGrnReverseWithPRAmendmentWithGateEntryWithMaterialType();
	}

	/**
	 * Verify Reverse GRN With PR Amendment With Service Type
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 16, groups = { "PoToGrn" }, description = "Verify Reverse GRN With PR Amendment With Service Type")
	public void VerifyReverseGrnWithPRAmendmentWithServiceType() {
		grnPageFunc.verifyGrnReverseWithPRAmendmentWithGateEntryWithServiceType();
	}

	/**
	 * Verify Reverse GRN Without GateEntry Material Type
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 17, groups = { "PoToGrn" }, description = "Verify Reverse GRN Without GateEntry Material Type")
	public void VerifyReverseGRNWithoutGateEntryMaterialType() {
		grnPageFunc.poToGrnMaterialUserDefinedSameStockAndSupplierUomWithFreePassWithReverseWithoutGateEntry();
	}

	/**
	 * Verify Reverse GRN WIthout Gate Entry Service Type
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 18, groups = { "PoToGrn" }, description = "Verify Reverse GRN WIthout Gate Entry Service Type")
	public void VerifyReverseGRNWithoutGateEntryServiceType() {
		grnPageFunc.poToGrnServiceUserDefinedSameStockAndSupplierUomWithFreePassWithReverseWithoutGateEntry();
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
