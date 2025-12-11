package com.roadmapERP;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks December 5, 2025
 */
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.GrnPageFunctions2;

public class GrnTest3 extends RoadMapERPTest {
	private boolean flag = false;
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static GrnPageFunctions2 grn2Func = new GrnPageFunctions2();

	/**
	 * Direct GRN-General-User Defined-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 1, groups = {
			"Direct GRN" }, description = "643 Direct GRN-General-User Defined-Same Stock and Supplier UOM")
	public void VerifyCreationOfDirectGrnUserDefinedSameUomGeneralTypeImportSupplier() {
		grn2Func.verifyCreationOfDirectGrnUserDefinedSameUomGeneralTypeImportSupplier();
	}

	/**
	 * Direct GRN-Material-User Defined-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 2, groups = {
			"Direct GRN" }, description = "644 Direct GRN-Material-User Defined-Same Stock and Supplier UOM")
	public void VerifyCreationOfDirectGrnUserDefinedSameUomMaterialTypeImportSupplier() {
		grn2Func.verifyCreationOfDirectGrnUserDefinedSameUomMaterialTypeImportSupplier();
	}

	/**
	 * Direct GRN-Service-User Defined-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 3, groups = {
			"Direct GRN" }, description = "645 Direct GRN-Service-User Defined-Same Stock and Supplier UOM")
	public void VerifyCreationOfDirectGrnUserDefinedSameUomServiceTypeImportSupplier() {
		grn2Func.verifyCreationOfDirectGrnUserDefinedSameUomServiceTypeImportSupplier();
	}

	/**
	 * Direct GRN-General-Rate Contract-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 4, groups = {
			"Direct GRN" }, description = "647 Direct GRN-General-Rate Contract-Same Stock and Supplier UOM")
	public void VerifyCreationOfDirectGrnRateContractSameUomGeneralTypeImportSupplier() {
		grn2Func.verifyCreationOfDirectGrnRateContractSameUomGeneralTypeImportSupplier();
	}

	/**
	 * Direct GRN-Material-Rate Contract-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 5, groups = {
			"Direct GRN" }, description = "648 Direct GRN-Material-Rate Contract-Same Stock and Supplier UOM")
	public void VerifyCreationOfDirectGrnRateContractSameUomMaterialTypeImportSupplier() {
		grn2Func.verifyCreationOfDirectGrnRateContractSameUomMaterialTypeImportSupplier();
	}

	/**
	 * Direct GRN-Service-Rate Contract-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 6, groups = {
			"Direct GRN" }, description = "649 Direct GRN-Service-Rate Contract-Same Stock and Supplier UOM")
	public void VerifyCreationOfDirectGrnRateContractSameUomServiceTypeImportSupplier() {
		grn2Func.verifyCreationOfDirectGrnRateContractSameUomServiceTypeImportSupplier();
	}

	/**
	 * Direct GRN-General-Price List-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 7, groups = {
			"Direct GRN" }, description = "651 Direct GRN-General-Price List-Same Stock and Supplier UOM")
	public void VerifyCreationOfDirectGrnPriceListSameUomGeneralTypeImportSupplier() {
		grn2Func.verifyCreationOfDirectGrnPriceListSameUomGeneralTypeImportSupplier();
	}

	/**
	 * Direct GRN-Material-Price List-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 8, groups = {
			"Direct GRN" }, description = "652 Direct GRN-Material-Price List-Same Stock and Supplier UOM")
	public void VerifyCreationOfDirectGrnPriceListSameUomMaterialTypeImportSupplier() {
		grn2Func.verifyCreationOfDirectGrnPriceListSameUomMaterialTypeImportSupplier();
	}

	/**
	 * Direct GRN-Service-Price List-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 9, groups = {
			"Direct GRN" }, description = "653 Direct GRN-Service-Price List-Same Stock and Supplier UOM")
	public void VerifyCreationOfDirectGrnPriceListSameUomServiceTypeImportSupplier() {
		grn2Func.verifyCreationOfDirectGrnPriceListSameUomServiceTypeImportSupplier();
	}
	
	/**
	 * Direct GRN-General-User Defined-Different Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 10, groups = {
	"Direct GRN" }, description = "655 Direct GRN-General-User Defined-Different Stock and Supplier UOM")
	public void VerifyCreationOfDirectGrnUserDefinedDifferentUomGeneralTypeImportSupplier() {
		grn2Func.verifyCreationOfDirectGrnUserDefinedDifferentUomGeneralTypeImportSupplier();
	}
	
	/**
	 * Direct GRN-Material-User Defined-Different Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 11, groups = {
	"Direct GRN" }, description = "656 Direct GRN-Material-User Defined-Different Stock and Supplier UOM")
	public void VerifyCreationOfDirectGrnUserDefinedDifferentUomMaterialTypeImportSupplier() {
		grn2Func.verifyCreationOfDirectGrnUserDefinedDifferentUomMaterialTypeImportSupplier();
	}

	/**
	 * Direct GRN-General-Rate Contract-Different Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 12, groups = {
			"Direct GRN" }, description = "657 Direct GRN-General-Rate Contract-Different Stock and Supplier UOM")
	public void VerifyCreationOfDirectGrnRateContractDifferentUomGeneralTypeImportSupplier() {
		grn2Func.verifyCreationOfDirectGrnRateContractDifferentUomGeneralTypeImportSupplier();
	}

	/**
	 * Direct GRN-Material-Rate Contract-Different Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 13, groups = {
			"Direct GRN" }, description = "658 Direct GRN-Material-Rate Contract-Different Stock and Supplier UOM")
	public void VerifyCreationOfDirectGrnRateContractDifferentUomMaterialTypeImportSupplier() {
		grn2Func.verifyCreationOfDirectGrnRateContractDifferentUomMaterialTypeImportSupplier();
	}

	/**
	 * Direct GRN-General-Price List-Different Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 14, groups = {
			"Direct GRN" }, description = "661 Direct GRN-General-Price List-Different Stock and Supplier UOM")
	public void VerifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeImportSupplier() {
		grn2Func.verifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeImportSupplier();
	}

	/**
	 * Direct GRN-Material-Price List-Different Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 15, groups = {
			"Direct GRN" }, description = "662 Direct GRN-Material-Price List-Different Stock and Supplier UOM")
	public void VerifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeImportSupplier() {
		grn2Func.verifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeImportSupplier();
	}

	/**
	 * PO to GRN-General-User Defined-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 16, groups = {
			"Direct GRN" }, description = "663 PO to GRN-General-User Defined-Same Stock and Supplier UOM")
	public void VerifyPOtoGRNWithFPsGateEntryGeneralUDSameStockAndSupplierUomImport() {
		grn2Func.verifyPOtoGRNWithFPsGateEntryGeneralUDSameStockAndSupplierUomImport();
	}

	/**
	 * PO to GRN-Material-User Defined-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 17, groups = {
			"Direct GRN" }, description = "664 PO to GRN-Material-User Defined-Same Stock and Supplier UOM")
	public void VerifyPOtoGRNWithFPsGateEntryMaterialUDSameStockAndSupplierUomImport() {
		grn2Func.verifyPOtoGRNWithFPsGateEntryMaterialUDSameStockAndSupplierUomImport();
	}

	/**
	 * PO to GRN-Service-User Defined-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 18, groups = {
			"Direct GRN" }, description = "665 PO to GRN-Service-User Defined-Same Stock and Supplier UOM")
	public void VerifyPOtoGRNWithFPsGateEntryServiceUDSameStockAndSupplierUomImport() {
		grn2Func.verifyPOtoGRNWithFPsGateEntryServiceUDSameStockAndSupplierUomImport();
	}

	/**
	 * PO to GRN-General-Rate Contract-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 19, groups = {
			"Direct GRN" }, description = "667 PO to GRN-General-Rate Contract-Same Stock and Supplier UOM")
	public void VerifyPOtoGRNWithFPsGateEntryGeneralRCSameStockAndSupplierUomImport() {
		grn2Func.verifyPOtoGRNWithFPsGateEntryGeneralRCSameStockAndSupplierUomImport();
	}

	/**
	 * PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 20, groups = {
			"Direct GRN" }, description = "668 PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM")
	public void VerifyPOtoGRNWithFPsGateEntryMaterialRCSameStockAndSupplierUomImport() {
		grn2Func.verifyPOtoGRNWithFPsGateEntryMaterialRCSameStockAndSupplierUomImport();
	}

	/**
	 * PO to GRN-Service-Rate Contract-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 21, groups = {
			"Direct GRN" }, description = "669 PO to GRN-Service-Rate Contract-Same Stock and Supplier UOM")
	public void VerifyPOtoGRNWithFPsGateEntryServiceRCSameStockAndSupplierUomImport() {
		grn2Func.verifyPOtoGRNWithFPsGateEntryServiceRCSameStockAndSupplierUomImport();
	}

	/**
	 * PO to GRN-General-Price List-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 22, groups = {
			"Direct GRN" }, description = "671 PO to GRN-General-Price List-Same Stock and Supplier UOM")
	public void VerifyPOtoGRNWithFPsGateEntryGeneralPLSameStockAndSupplierUomImport() {
		grn2Func.verifyPOtoGRNWithFPsGateEntryGeneralPLSameStockAndSupplierUomImport();
	}

	/**
	 * PO to GRN-Material-Price List-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 23, groups = {
			"Direct GRN" }, description = "672 PO to GRN-Material-Price List-Same Stock and Supplier UOM")
	public void VerifyPOtoGRNWithFPsGateEntryMaterialPLSameStockAndSupplierUomImport() {
		grn2Func.verifyPOtoGRNWithFPsGateEntryMaterialPLSameStockAndSupplierUomImport();
	}

	/**
	 * PO to GRN-Service-Price List-Same Stock and Supplier UOM
	 * 
	 * @author Sakthi-GWL
	 */
	@Author("Sakthi-GWL")
	@Test(priority = 24, groups = {
			"Direct GRN" }, description = "673 PO to GRN-Service-Price List-Same Stock and Supplier UOM")
	public void VerifyPOtoGRNWithFPsGateEntryServicePLSameStockAndSupplierUomImport() {
		grn2Func.verifyPOtoGRNWithFPsGateEntryServicePLSameStockAndSupplierUomImport();
	}

	/**
	 * Verify After Method
	 * 
	 * @author Sakthi-GWL
	 * @param result
	 * @param method
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result, Method method) {
		commonFunc.verifyAfterMethod();
		commonFunc.enterAuthorNamesInExtentReport(method);
	}
}
