package com.roadmapERP;

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
}
