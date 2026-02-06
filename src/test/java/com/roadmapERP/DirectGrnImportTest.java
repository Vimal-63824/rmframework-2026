package com.roadmapERP;

import java.io.IOException;
import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.DirectGrnPageFunctions;
import commonfunctions.supplychainmanagement.GrnPageFunctions;
import utils.ReportLoger;
import utils.WebDriverBase;

public class DirectGrnImportTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static WebDriverBase webDB = new WebDriverBase();
	static DirectGrnPageFunctions directGrnFunc = new DirectGrnPageFunctions();
	private static ReportLoger logs = new ReportLoger();
	static GrnPageFunctions grnPageFunc = new GrnPageFunctions();
	private static int previousStatus = ITestResult.SUCCESS;

	/**
	 * Create Direct GRN Same UOM General Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 1, groups = {
			"GRN" }, description = "Direct GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionD() {
		directGrnFunc.verifyCreationOfDirectGrnSameUomGeneralTypeImportWithInspectionD();
	}

	/**
	 * Create Direct GRN Same UOM General Type WithOut Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 2, groups = {
			"GRN" }, description = "Direct GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyCreationOfDirectGrnSameUomGeneralTypeWithOutInspectionD() {
		logs.logging("INFO", "PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * Create Direct GRN Same UOM General Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 3, groups = {
			"GRN" }, description = "Direct GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyCreationOfDirectGrnSameUomMaterialTypeWithInspectionD() {
		directGrnFunc.verifyCreationOfDirectGrnSameUomMaterialTypeImportWithInspectionD();
	}

	/**
	 * Create Direct GRN Same UOM General Type WithOut Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 4, groups = {
			"GRN" }, description = "Direct GRN-Material-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyCreationOfDirectGrnSameUomMaterialTypeWithOutInspectionD() {
		logs.logging("INFO", "PO to GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * Create Direct GRN Same UOM General Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 5, groups = {
			"GRN" }, description = "Direct GRN-General-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyCreationOfDirectGrnRateContractSameUomGeneralTypeWithInspectionD() {
		directGrnFunc.verifyCreationOfDirectGrnSameUomRateContractGeneralTypeImportWithInspectionD();
	}

	/**
	 * Create Direct GRN Same UOM General Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 6, groups = {
			"GRN" }, description = "Direct GRN-General-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyCreationOfDirectGrnRateContractSameUomGeneralTypeWithOutInspectionD() {
		logs.logging("INFO", "PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * Create Direct GRN Same UOM Material Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 7, groups = {
			"GRN" }, description = "Direct GRN-Material-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyCreationOfDirectGrnRateContractSameUomMaterialTypeWithInspectionD() {
		directGrnFunc.verifyCreationOfDirectGrnSameUomRateContractMaterialTypeImportWithInspectionD();
	}

	/**
	 * Create Direct GRN Same UOM Material Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 8, groups = {
			"GRN" }, description = "Direct GRN-Material-Rate Contract-Same Stock and Supplier UOM - WithOut Inspection Plan")
	public void VerifyCreationOfDirectGrnRateContractSameUomMaterialTypeWithOutInspectionD() {
		logs.logging("INFO", "PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * Create Direct GRN Same UOM General Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 9, groups = {
			"GRN" }, description = "Direct GRN-General-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyCreationOfDirectGrnPriceListSameUomGeneralTypeImportWithInspectionD() {
		directGrnFunc.verifyCreationOfDirectGrnPriceListSameUomGeneralTypeImportWithInspectionD();
	}

	/**
	 * Create Direct GRN Same UOM General Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 10, groups = {
			"GRN" }, description = "Direct GRN-General-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyCreationOfDirectGrnPriceListSameUomGeneralTypeImportWithOutInspectionD() {
		logs.logging("INFO", "PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * Create Direct GRN Same UOM General Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 11, groups = {
			"GRN" }, description = "Direct GRN-Material-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyCreationOfDirectGrnPriceListSameUomMaterialTypeImportWithInspectionD() {
		directGrnFunc.verifyCreationOfDirectGrnPriceListSameUomMaterialTypeImportWithInspectionD();
	}

	/**
	 * Create Direct GRN Same UOM General Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 12, groups = {
			"GRN" }, description = "Direct GRN-Material-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyCreationOfDirectGrnPriceListSameUomMaterialTypeImportWithOutInspectionD() {
		logs.logging("INFO", "PO to GRN-Material-Price List-Same Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * Create Direct GRN Same UOM Service Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 13, groups = {
			"GRN" }, description = "Direct GRN-Service-Price List-Same Stock and Supplier UOM - WithOut Inspection Plan")
	public void VerifyCreationOfDirectGrnUserDefSameUomServiceTypeImportWithInspectionD() {
		directGrnFunc.verifyCreationOfDirectGrnSameUomServiceTypeImportWithoutInspectionD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from Direct GRN-Service-Rate
	 * Contract-Same Stock and Supplier UOM - WO Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 14, groups = {
			"GRN" }, description = "Direct GRN-Service-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyCreationOfDirectGrnRateContractSameUomServiceTypeImportWithInspectionD() throws IOException {
		directGrnFunc.verifyCreationOfDirectGrnRateContractSameUomServiceTypeImportWithInspectionD();
	}

	/**
	 * This Methods Is Used To Create Direct GRN Document With Service & Import
	 * withOut Inspection Plan
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 15, groups = {
			"GRN" }, description = "Direct GRN-Service-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyCreationOfDirectGrnPriceListSameUomServiceTypeImportWithInspectionD() throws IOException {
		directGrnFunc.verifyCreationOfDirectGrnPriceListSameUomServiceTypeImportWithInspectionD();
	}

	/**
	 * Create Direct GRN Different UOM General Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 16, groups = {
			"GRN" }, description = "Direct GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyCreationOfDirectGrnDifferentUomGeneralTypeImportWithInspectionD() {
		directGrnFunc.verifyCreationOfDirectGrnDifferentUomGeneralTypeImportWithInspectionD();
	}

	/**
	 * Create Direct GRN Different UOM General Type WithOut Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 17, groups = {
			"GRN" }, description = "Direct GRN-General-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyCreationOfDirectGrnDifferentUomGeneralTypeImportWithOutInspectionD() {
		logs.logging("INFO", "PO to GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * Create Direct GRN Different UOM Material Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 18, groups = {
			"GRN" }, description = "Direct GRN-Material-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyCreationOfDirectGrnDifferentUomMaterialTypeImportWithInspectionD() {
		directGrnFunc.verifyCreationOfDirectGrnDifferentUomMaterialTypeImportWithInspectionD();
	}

	/**
	 * Create Direct GRN Different UOM Material Type WithOut Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 19, groups = {
			"GRN" }, description = "Direct GRN-Material-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyCreationOfDirectGrnDifferentUomMaterialTypeImportWithOutInspectionD() {
		logs.logging("INFO", "PO to GRN-Material-User Defined-Different Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * Create Direct GRN Different UOM General Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 20, groups = {
			"GRN" }, description = "Direct GRN-General-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyCreationOfDirectGrnRateContractDifferentUomGeneralTypeImportWithInspectionD() {
		directGrnFunc.verifyCreationOfDirectGrnDifferentUomRateContractGeneralTypeImportWithInspectionD();
	}

	/**
	 * Create Direct GRN Different UOM General Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 21, groups = {
			"GRN" }, description = "Direct GRN-General-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyCreationOfDirectGrnRateContractDifferentUomGeneralTypeimportWithOutInspectionD() {
		logs.logging("INFO", "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * Create Direct GRN Different UOM Material Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 22, groups = {
			"GRN" }, description = "Direct GRN-Material-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyCreationOfDirectGrnRateContractDifferentUomMaterialTypeWithInspectionD() {
		directGrnFunc.verifyCreationOfDirectGrnDifferentUomRateContractMaterialTypeImportWithInspectionD();
	}

	/**
	 * Create Direct GRN Different UOM General Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 23, groups = {
			"GRN" }, description = "Direct GRN-General-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyCreationOfDirectGrnRateContractDifferentUomMaterialTypeimportWithOutInspectionD() {
		logs.logging("INFO", "PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * Create Direct GRN Different UOM General Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 24, groups = {
			"GRN" }, description = "Direct GRN-General-Price List-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeImportWithInspectionD() {
		directGrnFunc.verifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeImportWithInspectionD();
	}

	/**
	 * Create Direct GRN Same UOM General Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 25, groups = {
			"GRN" }, description = "Direct GRN-General-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeImportWithOutInspectionD() {
		logs.logging("INFO", "PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * Create Direct GRN Different UOM General Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 24, groups = {
			"GRN" }, description = "Direct GRN-General-Price List-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeImportWithInspectionD() {
		directGrnFunc.verifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeImportWithInspectionD();
	}

	/**
	 * Create Direct GRN Same UOM General Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 25, groups = {
			"GRN" }, description = "Direct GRN-General-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeImportWithOutInspectionD() {
		logs.logging("INFO", "PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * Create Direct GRN Same UOM General Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 26, groups = {
			"GRN" }, description = "Direct GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionWithoutQuality() {
		directGrnFunc.verifyCreationOfDirectGrnSameUomGeneralTypeImportWithInspectionWithOutQuality();
	}

	/**
	 * Create Direct GRN Same UOM General Type WithOut Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 2, groups = {
			"GRN" }, description = "Direct GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyCreationOfDirectGrnSameUomGeneralTypeWithOutInspectionWithOutQuality() {
		logs.logging("INFO", "PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * Create Direct GRN Same UOM Material Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 26, groups = {
			"GRN" }, description = "Direct GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyCreationOfDirectGrnSameUomMaterialTypeWithInspectionWithoutQuality() {
		directGrnFunc.verifyCreationOfDirectGrnSameUomMaterialTypeImportWithInspectionWithOutQuality();
	}

	/**
	 * Create Direct GRN Same UOM Material Type WithOut Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 27, groups = {
			"GRN" }, description = "Direct GRN-Material-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyCreationOfDirectGrnSameUomMaterialTypeWithOutInspectionWithOutQuality() {
		logs.logging("INFO", "PO to GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * Create Direct GRN Same UOM General Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 28, groups = {
			"GRN" }, description = "Direct GRN-General-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyCreationOfDirectGrnRateContractSameUomGeneralTypeWithInspectionWithOutQuality() {
		directGrnFunc.verifyCreationOfDirectGrnSameUomRateContractGeneralTypeImportWithInspectionWithOutQuality();
	}

	/**
	 * Create Direct GRN Same UOM General Type With Inspection Plan WithOut Quality
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 29, groups = {
			"GRN" }, description = "Direct GRN-General-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyCreationOfDirectGrnRateContractSameUomGeneralTypeWithOutInspectionWithOutQuality() {
		logs.logging("INFO", "PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * Create Direct GRN Same UOM Material Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 30, groups = {
			"GRN" }, description = "Direct GRN-Material-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyCreationOfDirectGrnRateContractSameUomMaterialTypeWithInspectionWithOutQuality() {
		directGrnFunc.verifyCreationOfDirectGrnSameUomRateContractMaterialTypeImportWithInspectionWithOutQuality();
	}

	/**
	 * Create Direct GRN Same UOM General Type With Inspection Plan WithOut Quality
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 31, groups = {
			"GRN" }, description = "Direct GRN-General-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyCreationOfDirectGrnRateContractSameUomMaterialTypeWithOutInspectionWithOutQuality() {
		logs.logging("INFO", "PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * Create Direct GRN Same UOM General Type PriceList With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 32, groups = {
			"GRN" }, description = "Direct GRN-General-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyCreationOfDirectGrnPriceListSameUomGeneralTypeImportWithInspectionWithOutQuality() {
		directGrnFunc.verifyCreationOfDirectGrnPriceListSameUomGeneralTypeImportWithInspectionWithOutQuality();
	}

	/**
	 * Create Direct GRN Same UOM General Type With Inspection Plan WithOut Quality
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 33, groups = {
			"GRN" }, description = "Direct GRN-General-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyCreationOfDirectGrnPriceListSameUomGeneralTypeImportWithOutInspectionWithOutQuality() {
		logs.logging("INFO", "PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * Create Direct GRN Same UOM Material Type PriceList With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 34, groups = {
			"GRN" }, description = "Direct GRN-Material-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void VerifyCreationOfDirectGrnPriceListSameUomMaterialTypeImportWithInspectionWithOutQuality() {
		directGrnFunc.verifyCreationOfDirectGrnPriceListSameUomMaterialTypeImportWithInspectionWithOutQuality();
	}

	/**
	 * Create Direct GRN Same UOM Material Type With Inspection Plan WithOut Quality
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 35, groups = {
			"GRN" }, description = "Direct GRN-Material-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyCreationOfDirectGrnPriceListSameUomMaterialTypeImportWithOutInspectionWithOutQuality() {
		logs.logging("INFO", "PO to GRN-Material-Price List-Same Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * Create Direct GRN Same UOM Service Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 36, groups = {
			"GRN" }, description = "Direct GRN-Service-Price List-Same Stock and Supplier UOM - WithOut Inspection Plan")
	public void VerifyCreationOfDirectGrnUserDefSameUomServiceTypeImportWithInspectionWithOutQuality() {
		directGrnFunc.verifyCreationOfDirectGrnSameUomServiceTypeImportWithoutInspectionWithOutQuality();
	}

	/**
	 * This Methods Is Used To Create GRN Document from Direct GRN-Service-Rate
	 * Contract-Same Stock and Supplier UOM - WO Inspection Plan WithOut Quality
	 * Enabled
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 37, groups = {
			"GRN" }, description = "Direct GRN-Service-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyCreationOfDirectGrnRateContractSameUomServiceTypeImportWithInspectionWithOutQuality()
			throws IOException {
		directGrnFunc.verifyCreationOfDirectGrnRateContractSameUomServiceTypeImportWithInspectionWithOutQuality();
	}


	/**
	 * Create Direct GRN Different UOM General Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 39, groups = {
			"GRN" }, description = "Direct GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyCreationOfDirectGrnDifferentUomGeneralTypeImportWithInspectionWithOutQuality() {
		directGrnFunc.verifyCreationOfDirectGrnDifferentUomGeneralTypeImportWithInspectionWithOutQuality();
	}

	/**
	 * Create Direct GRN Different UOM General Type WithOut Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 40, groups = {
			"GRN" }, description = "Direct GRN-General-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyCreationOfDirectGrnDifferentUomGeneralTypeImportWithOutInspectionWithOutQuality() {
		logs.logging("INFO", "PO to GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}

	/**
	 * Create Direct GRN Different UOM Material Type With Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 41, groups = {
			"GRN" }, description = "Direct GRN-Material-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void VerifyCreationOfDirectGrnDifferentUomMaterialTypeImportWithInspectionWithOutQuality() {
		directGrnFunc.verifyCreationOfDirectGrnDifferentUomMaterialTypeImportWithInspectionWithOutQuality();
	}

	/**
	 * Create Direct GRN Different UOM General Type WithOut Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	@Author("Deepan-GWL")
	@Test(priority = 42, groups = {
			"GRN" }, description = "Direct GRN-General-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void VerifyCreationOfDirectGrnDifferentUomMaterialTypeImportWithOutInspectionWithOutQuality() {
		logs.logging("INFO", "PO to GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection Plan");
		if (previousStatus != ITestResult.SUCCESS) {
			Assert.fail();
		}
	}
	
//	/**
//	 * This Methods Is Used To Create Direct GRN Document With Service & Import
//	 * PriceList withOut Inspection Plan
//	 * 
//	 * @author Durai-RMITS
//	 */
//	@Author("Durai-RMITS")
//	@Test(priority = 38, groups = {
//			"GRN" }, description = "Direct GRN-Service-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
//	public void VerifyCreationOfDirectGrnPriceListSameUomServiceTypeImportWithInspectionWithOutQuality()
//			throws IOException {
//		directGrnFunc.verifyCreationOfDirectGrnPriceListSameUomServiceTypeImportWithInspectionWithOutQuality();
//	}

	/**
	 * Verify After Method
	 * 
	 * @author Deepan-GWL
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result, Method method) {
		previousStatus = result.getStatus();
		commonFunc.verifyAfterMethod();
		commonFunc.enterAuthorNamesInExtentReport(method);
	}

}
