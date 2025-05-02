package com.roadmapERP;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks Feburary 05, 2025
 */
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import utils.Mailer;
import utils.ReportLoger;
import utils.WebDriverBase;
import commonfunctions.supplychainmanagement.GrnPageFunctions;
import commonfunctions.supplychainmanagement.PurchaseOrderFunctions;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.io.IOException;

public class GrnTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static GrnPageFunctions grnPageFunc = new GrnPageFunctions();
	PurchaseOrderFunctions POfunc = new PurchaseOrderFunctions();
	private static ReportLoger logs = new ReportLoger();

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * Service Type Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 1, groups = {
			"GRN" }, description = "228 PO to GRN-Service-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void PoToGrnServiceUserDefinedSameStockAndSupplierUOMWOInspectionPlan() {
		grnPageFunc.verifyGrnPODomesticServiceSimilarUOMfromPo();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection
	 * RateContract Document For General Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Test(priority = 2, groups = {
			"GRN" }, description = "231 PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void PoToGrnGeneralRateContractSameStockAndSupplierUOMWithInspectionGeneralType() throws IOException {
		grnPageFunc.poToGrnGeneralRateContractSameStockAndSupplierUOMWithAndWithoutInspectionGeneralType();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * RateContract Document For General Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 3, groups = {
			"GRN" }, description = "232 PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - Without Inspection Plan")
	public void PoToGrnGeneralRateContractSameStockAndSupplierUOMWithoutInspectionGeneralType() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under PoToGrnGeneralRateContractSameStockAndSupplierUOMWithInspectionGeneralType");

		// TODO Covered
		// PoToGrnGeneralRateContractSameStockAndSupplierUOMWithInspectionGeneralType()
		// Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection
	 * RateContract Document for Material Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Test(priority = 4, groups = {
			"GRN" }, description = "233 PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void PoToGrnGeneralRateContractSameStockAndSupplierUOMWithInspectionMaterial() throws IOException {
		grnPageFunc.poToGrnlRateContractSameStockAndSupplierUOMWithAndWithoutInspectionMaterial();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with and without
	 * Inspection RateContract Document for Material Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 5, groups = {
			"GRN" }, description = "234 PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - Without Inspection Plan")
	public void PoToGrnGeneralRateContractSameStockAndSupplierUOMWithoutInspectionMaterial() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under PoToGrnGeneralRateContractSameStockAndSupplierUOMWithInspectionMaterial");
		// TODO Covered
		// PoToGrnGeneralRateContractSameStockAndSupplierUOMWithInspectionMaterial()
		// Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection for
	 * Material Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 6, groups = {
			"GRN" }, description = "226 PO to GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void PoToGrnMaterialUserDefinedSameStockAndSupplierUomWithInspectionPlan() throws IOException {
		grnPageFunc.poToGrnMaterialUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlan();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO Without Inspection for
	 * Material Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 7, groups = {
			"GRN" }, description = "227 PO to GRN-Material-User Defined-Same Stock and Supplier UOM - WithOut Inspection Plan")
	public void PoToGrnMaterialUserDefinedSameStockAndSupplierUomWithoutInspectionPlan() throws IOException {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under PoToGrnMaterialUserDefinedSameStockAndSupplierUomWithInspectionPlan");
		// TODO Covered
		// PoToGrnMaterialUserDefinedSameStockAndSupplierUomWithInspectionPlan() Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection for
	 * General Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 8, groups = {
			"GRN" }, description = "224 PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection Plan")

	public void PoToGrnGeneralUserDefinedSameStockAndSupplierUomWithInspectionPlan() throws IOException {
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlan();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO Without Inspection for
	 * General Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 9, groups = {
			"GRN" }, description = "225 PO to GRN-General-User Defined-Same Stock and Supplier UOM - Without Inspection Plan")

	public void PoToGrnGeneralUserDefinedSameStockAndSupplierUomWithoutInspectionPlan() throws IOException {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under PoToGrnGeneralUserDefinedSameStockAndSupplierUomWithInspectionPlan");
		// TODO Covered
		// PoToGrnGeneralUserDefinedSameStockAndSupplierUomWithInspectionPlan() Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * RateContract Document for Service Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Test(priority = 10, groups = {
			"GRN" }, description = "235 PO to GRN-Service-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void PoToGrnServiceRateContractSameStockandSupplierUOMWoInspectionPlan() throws IOException {
		grnPageFunc.poToGrnServiceRateContractSameStockAndSupplierUOMWoInspectionPlan();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for General Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Test(priority = 11, groups = {
			"GRN" }, description = "238 PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void PotoGrnGeneralPriceListSameStockAndSupplierUOMWithInspectionPlan() throws IOException {
		grnPageFunc.poToGrnGeneralPriceListSameStockAndSupplierUOMWithandWithoutInspectionPlan();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * PriceList Document for General Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 12, groups = {
			"GRN" }, description = "239 PO to GRN-General-Price List-Same Stock and Supplier UOM - WithOut Inspection Plan")
	public void PotoGrnGeneralPriceListSameStockAndSupplierUOMWithOutInspection() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under PotoGrnGeneralPriceListSameStockAndSupplierUOMWithInspectionPlan");
		// TODO Covered
		// PotoGrnGeneralPriceListSameStockAndSupplierUOMWithInspectionPlan() Method

	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for General Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Test(priority = 20, groups = {
			"GRN" }, description = "245 PO to GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void PoToGrnGeneralUserDefinedStockAndSupplierDisUomWithInspectionPlan() throws IOException {
		grnPageFunc.poToGrnGeneralUserDefinedStockAndSupplierDisUomWithAndWithoutInspectionPlan();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * PriceList Document for General Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 21, groups = {
			"GRN" }, description = "246 PO to GRN-General-User Defined-Different Stock and Supplier UOM - Without Inspection Plan")
	public void PoToGrnGeneralUserDefinedStockAndSupplierDisUomWithoutInspectionPlan() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under PoToGrnGeneralUserDefinedStockAndSupplierDisUomWithInspectionPlan");
		// TODO Covered in
		// PoToGrnGeneralUserDefinedStockAndSupplierDisUomWithInspectionPlan() Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for Material Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Test(priority = 22, groups = {
			"GRN" }, description = "247 PO to GRN-Material-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void PoToGrnMaterialUserDefinedStockAndSupplierDisUomWithInspectionPlan() throws IOException {
		grnPageFunc.poToGRNMaterialUserDefinedDifferentStockAndSupplierUOMWithAndWithOutInspectionPlan();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO withOut Inspection For
	 * Material UserDefined Item
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 23, groups = {
			"GRN" }, description = "248 PO to GRN-Material-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void POtoGRNMaterialUserDefinedDifferentStockAndSupplierUOMWithoutInspectionPlan() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under POtoGRNMaterialUserDefinedDifferentStockAndSupplierUOMWithInspectionPlan");
		// TODO Covered in
		// poToGRNMaterialUserDefinedDifferentStockAndSupplierUOMWithAndWithOutInspectionPlan()
		// Method

	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection For
	 * General Rate Contract Item
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Test(priority = 26, groups = {
			"GRN" }, description = "252 PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void POtoGRNGeneralRateContractDifferentStockAndSupplierUOMWithInspectionPlan() throws IOException {
		grnPageFunc.poToGrnGeneralRateContractDifferentStockAndSupplierUOMWithAndWithoutInspectionGeneralType();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection For
	 * General Rate Contract Item
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Test(priority = 27, groups = {
			"GRN" }, description = "253 PO to GRN-Stock Transfer-User Defined-Different Stock and Supplier UOM - Without Inspection Plan")
	public void POtoGRNGeneralRateContractDifferentStockAndSupplierUOMWithoutInspectionPlan() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under poToGrnGeneralRateContractDifferentStockAndSupplierUOMWithAndWithoutInspectionGeneralType");
		// TODO Covered in
		// poToGrnGeneralRateContractDifferentStockAndSupplierUOMWithAndWithoutInspectionGeneralType()
		// Method

	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection For
	 * General Rate Contract Item
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Test(priority = 28, groups = {
			"GRN" }, description = "254 PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void POToGRNMaterialRateContractDifferentStockAndSupplierUOMWithInspectionPlan() throws IOException {
		grnPageFunc.poToGrnlRateContractStockAndSupplierDifferentUOMWithAndWithoutInspectionMaterial();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection For Mat
	 * Rate Contract Item
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Test(priority = 29, groups = {
			"GRN" }, description = "255 PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void POtoGRNMaterialRateContractDifferentStockAndSupplierUOMWithoutInspectionPlan() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under poToGrnlRateContractStockAndSupplierDifferentUOMWithAndWithoutInspectionMaterial");
		// TODO Covered in
		// poToGrnlRateContractStockAndSupplierDifferentUOMWithAndWithoutInspectionMaterial()
		// Method

	}

	/**
	 * This Method Is Used To Logout After Method
	 * 
	 * @author Deepan-GWL
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result) {
		commonFunc.verifyAfterMethod();
	}

}
