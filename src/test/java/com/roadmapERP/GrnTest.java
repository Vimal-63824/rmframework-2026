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
	 * @throws Exception
	 */
	@Test(priority = 2, groups = {
			"GRN" }, description = "231 PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void PoToGrnGeneralRateContractSameStockAndSupplierUOMWithInspectionGeneralType() throws Exception {
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
	 * @throws Exception
	 */
	@Test(priority = 4, groups = {
			"GRN" }, description = "233 PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void PoToGrnGeneralRateContractSameStockAndSupplierUOMWithInspectionMaterial() throws Exception {
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
	public void PoToGrnMaterialUserDefinedSameStockAndSupplierUomWithInspectionPlan() throws Exception {
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
	public void PoToGrnMaterialUserDefinedSameStockAndSupplierUomWithoutInspectionPlan() throws Exception {
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

	public void PoToGrnGeneralUserDefinedSameStockAndSupplierUomWithInspectionPlan() throws Exception {
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

	public void PoToGrnGeneralUserDefinedSameStockAndSupplierUomWithoutInspectionPlan() throws Exception {
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
	 * @throws Exception
	 */
	@Test(priority = 10, groups = {
			"GRN" }, description = "235 PO to GRN-Service-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void PoToGrnServiceRateContractSameStockandSupplierUOMWoInspectionPlan() throws Exception {
		grnPageFunc.poToGrnServiceRateContractSameStockAndSupplierUOMWoInspectionPlan();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for General Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Test(priority = 11, groups = {
			"GRN" }, description = "238 PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void PotoGrnGeneralPriceListSameStockAndSupplierUOMWithInspectionPlan() throws Exception {
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
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * Service Type(Price List Type)
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Test(priority = 17, groups = {
			"GRN" }, description = "240 PO to GRN-Service-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void POToGRNServicePriceListSameStockaAndSupplierUOMWOInspectionPlan() throws Exception {
		grnPageFunc.poToGRNServicePriceListSameStockAndSupplierUOMWOInspectionPlan();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for General Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Test(priority = 20, groups = {
			"GRN" }, description = "245 PO to GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void PoToGrnGeneralUserDefinedStockAndSupplierDisUomWithInspectionPlan() throws Exception {
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
	 * @throws Exception
	 */
	@Test(priority = 22, groups = {
			"GRN" }, description = "247 PO to GRN-Material-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void PoToGrnMaterialUserDefinedStockAndSupplierDisUomWithInspectionPlan() throws Exception {
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
	 * @throws Exception
	 */
	@Test(priority = 26, groups = {
			"GRN" }, description = "252 PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void POtoGRNGeneralRateContractDifferentStockAndSupplierUOMWithInspectionPlan() throws Exception {
		grnPageFunc.poToGrnGeneralRateContractDifferentStockAndSupplierUOMWithAndWithoutInspectionGeneralType();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection For
	 * General Rate Contract Item
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
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
	 * @throws Exception
	 */
	@Test(priority = 28, groups = {
			"GRN" }, description = "254 PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void POToGRNMaterialRateContractDifferentStockAndSupplierUOMWithInspectionPlan() throws Exception {
		grnPageFunc.poToGrnlRateContractStockAndSupplierDifferentUOMWithAndWithoutInspectionMaterial();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection For Mat
	 * Rate Contract Item
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
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
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for Material Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Test(priority = 30, groups = {
			"GRN" }, description = "240 PO to GRN-Material-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void PotoGrnMateriallPriceListSameStockAndSupplierUOMWithInspectionPlan() throws Exception {
		grnPageFunc.poToGrnMaterialPriceListSameStockAndSupplierUOMWithandWithoutInspectionPlan();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * PriceList Document for Material Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 31, groups = {
			"GRN" }, description = "241 PO to GRN-Material-Price List-Same Stock and Supplier UOM - Without Inspection Plan")
	public void PotoGrnMateriallPriceListSameStockAndSupplierUOMWithOutInspectionPlan() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under poToGrnMaterialPriceListSameStockAndSupplierUOMWithandWithoutInspectionPlan");
		// TODO Covered
		// poToGrnMaterialPriceListSameStockAndSupplierUOMWithandWithoutInspectionPlan()
		// Method

	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for General Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Test(priority = 32, groups = {
			"GRN" }, description = "259 PO to GRN-General-Price List-Different Stock and Supplier UOM - With Inspection Plan")
	public void PotoGrnGeneralPriceListDifferentStockAndSupplierUOMWithInspectionPlan() throws Exception {
		grnPageFunc.poToGrnGeneralPriceListDifferentStockAndSupplierUOMWithandWithoutInspectionPlan();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * PriceList Document for General Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 33, groups = {
			"GRN" }, description = "260 PO to GRN-General-Price List-Different Stock and Supplier UOM - WO Inspection Plan")
	public void PotoGrnGeneralPriceListDifferentStockAndSupplierUOMWithOutInspection() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under poToGrnGeneralPriceListDifferentStockAndSupplierUOMWithandWithoutInspectionPlan");
		// TODO Covered
		// poToGrnGeneralPriceListDifferentStockAndSupplierUOMWithandWithoutInspectionPlan()
		// Method

	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for Material Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Test(priority = 34, groups = {
			"GRN" }, description = "261 PO to GRN-Material-Price List-Different Stock and Supplier UOM - With Inspection Plan")
	public void PotoGrnMateriallPriceListDifferentStockAndSupplierUOMWithInspectionPlan() throws Exception {
		grnPageFunc.poToGrnMaterialPriceListDifferentStockAndSupplierUOMWithandWithoutInspectionPlan();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * PriceList Document for Material Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 35, groups = {
			"GRN" }, description = "262 PO to GRN-Material-Price List-Different Stock and Supplier UOM - WO Inspection Plan")
	public void PotoGrnMateriallPriceListDifferentStockAndSupplierUOMWithOutInspectionPlan() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under poToGrnMaterialPriceListDifferentStockAndSupplierUOMWithandWithoutInspectionPlan");
		// TODO Covered
		// poToGrnMaterialPriceListDifferentStockAndSupplierUOMWithandWithoutInspectionPlan()
		// Method

	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection for
	 * General Type(Without Quality Enabled)
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 36, groups = {
			"GRN" }, description = "296 PO to GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection Plan")

	public void PoToGrnGeneralUserDefinedSameStockAndSupplierUomWithInspectionPlanWithoutQualityEnabled()
			throws Exception {
		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithoutQualityEnabled();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * General Type(Without Quality Enabled)
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 37, groups = {
			"GRN" }, description = "297 PO to GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void PoToGrnGeneralUserDefinedSameStockAndSupplierUomWithOutInspectionPlanWithoutQualityEnabled() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithoutQualityEnabled");
		// TODO Covered
		// poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithoutQualityEnabled()
		// Method

	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection for
	 * Material Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 38, groups = {
			"GRN" }, description = "298 PO to GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void PoToGrnMaterialUserDefinedSameStockAndSupplierUomWithInspectionPlanWithoutQualityEnabled()
			throws Exception {
		grnPageFunc
				.poToGrnMaterialUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithoutQualityEnabled();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * General Type(Without Quality Enabled)
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 39, groups = {
			"GRN" }, description = "299 PO to GRN-Material-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void PoToGrnMaterialUserDefinedSameStockAndSupplierUomWithOutInspectionPlanWithoutQualityEnabled() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under poToGrnMaterialUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithoutQualityEnabled");
		// TODO Covered
		// poToGrnMaterialUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithoutQualityEnabled()
		// Method

	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * Service Type(Without Quality Enabled)
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 40, groups = {
			"GRN" }, description = "300 PO to GRN-Service-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void PoToGrnServiceUserDefinedSameStockAndSupplierUOMWOInspectionPlanWithoutQualityEnabled() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under PoToGrnServiceUserDefinedSameStockAndSupplierUOMWOInspectionPlan");
		// TODO Covered
		// PoToGrnServiceUserDefinedSameStockAndSupplierUOMWOInspectionPlan()
		// Method

	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection
	 * RateContract Document For General Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Test(priority = 41, groups = {
			"GRN" }, description = "303 PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void PoToGrnGeneralRateContractSameStockAndSupplierUOMWithoutQualityEnabledWithInspectionGeneralType()
			throws Exception {
		grnPageFunc
				.poToGrnGeneralRateContractSameStockAndSupplierUOMWithAndWithoutInspectionGeneralTypeForWithoutQualityEnabled();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * General Type(Without Quality Enabled)
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 42, groups = {
			"GRN" }, description = "304 PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void PoToGrnGeneralRateContractSameStockAndSupplierUOMWithoutQualityEnabledWithOutInspectionGeneralType() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under poToGrnGeneralRateContractSameStockAndSupplierUOMWithAndWithoutInspectionGeneralTypeForWithoutQualityEnabled");
		// TODO Covered
		// poToGrnGeneralRateContractSameStockAndSupplierUOMWithAndWithoutInspectionGeneralTypeForWithoutQualityEnabled()
		// Method

	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection
	 * RateContract Document for Material Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Test(priority = 43, groups = {
			"GRN" }, description = "305 PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan Without Quality Enabled")
	public void PoToGrnGeneralRateContractSameStockAndSupplierUOMWithInspectionMaterialWithoutQualityEnabled()
			throws Exception {
		grnPageFunc.poToGrnlRateContractSameStockAndSupplierUOMWithAndWithoutInspectionMaterialWithoutQualityEnabled();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * General Type(Without Quality Enabled)
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 44, groups = {
			"GRN" }, description = "306 PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan Without Quality Enabled")
	public void PoToGrnGeneralRateContractSameStockAndSupplierUOMWithOutInspectionMaterialWithoutQualityEnabled() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under poToGrnlRateContractSameStockAndSupplierUOMWithAndWithoutInspectionMaterialWithoutQualityEnabled");
		// TODO Covered
		// poToGrnlRateContractSameStockAndSupplierUOMWithAndWithoutInspectionMaterialWithoutQualityEnabled()
		// Method

	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * RateContract Service Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 45, groups = {
			"GRN" }, description = "307 PO to GRN-Service-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void PoToGrnServiceRateContractSameStockandSupplierUOMWoInspectionPlanWithoutQualityEnabled() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under poToGrnServiceRateContractSameStockAndSupplierUOMWoInspectionPlan");
		// TODO Covered
		// poToGrnServiceRateContractSameStockAndSupplierUOMWoInspectionPlan() Method

	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for General Type Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Test(priority = 46, groups = {
			"GRN" }, description = "310 PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void PotoGrnGeneralPriceListSameStockAndSupplierUOMWithInspectionPlanWithoutQualityEnabled()
			throws Exception {
		grnPageFunc.poToGrnGeneralPriceListSameStockAndSupplierUOMWithandWithoutInspectionPlanWithoutQualityEnabled();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * PriceList Document for General Type Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 47, groups = {
			"GRN" }, description = "311 PO to GRN-General-Price List-Same Stock and Supplier UOM - WithOut Inspection Plan")
	public void PotoGrnGeneralPriceListSameStockAndSupplierUOMWithOutInspectionWithoutQualityEnabled() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under poToGrnGeneralPriceListSameStockAndSupplierUOMWithandWithoutInspectionPlanWithoutQualityEnabled");
		// TODO Covered
		// poToGrnGeneralPriceListSameStockAndSupplierUOMWithandWithoutInspectionPlanWithoutQualityEnabled()
		// Method

	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for Material Type Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Test(priority = 48, groups = {
			"GRN" }, description = "312 PO to GRN-Material-Price List-Same Stock and Supplier UOM - With Inspection Plan Without Quality Enabled")
	public void PotoGrnMateriallPriceListSameStockAndSupplierUOMWithInspectionPlanWithoutQualityEnabled()
			throws Exception {
		grnPageFunc.poToGrnMaterialPriceListSameStockAndSupplierUOMWithandWithoutInspectionPlanWithoutQualityEnabled();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * PriceList Document for Material Type withoutQuality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 49, groups = {
			"GRN" }, description = "313 PO to GRN-Material-Price List-Same Stock and Supplier UOM - WO Inspection Plan withoutQualityEnabled")
	public void PotoGrnMateriallPriceListSameStockAndSupplierUOMWithOutInspectionPlanWithoutQualityEnabled() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under poToGrnMaterialPriceListSameStockAndSupplierUOMWithandWithoutInspectionPlanWithoutQualityEnabled");
		// TODO Covered
		// poToGrnMaterialPriceListSameStockAndSupplierUOMWithandWithoutInspectionPlanWithoutQualityEnabled()
		// Method

	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * PriceList Document for Material Type withoutQuality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 50, groups = {
			"GRN" }, description = "314 PO to GRN-Material-Price List-Same Stock and Supplier UOM - WO Inspection Plan withoutQualityEnabled")
	public void POToGRNServicePriceListSameStockaAndSupplierUOMWOInspectionPlanWithoutQualityEnabled() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under POToGRNServicePriceListSameStockaAndSupplierUOMWOInspectionPlan");
		// TODO Covered
		// POToGRNServicePriceListSameStockaAndSupplierUOMWOInspectionPlan()
		// Method

	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection Document
	 * for General Type Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Test(priority = 51, groups = {
			"GRN" }, description = "317 PO to GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection PlanWithoutQualityEnabled")
	public void PoToGrnGeneralUserDefinedStockAndSupplierDisUomWithInspectionPlanWithoutEnabledQuality()
			throws Exception {
		grnPageFunc.poToGrnGeneralUserDefinedStockAndSupplierDisUomWithAndWithoutInspectionPlanWithoutQualityEnabled();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * Document for General Type Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 52, groups = {
			"GRN" }, description = "318 PO to GRN-General-User Defined-Different Stock and Supplier UOM - Without Inspection Plan")
	public void PoToGrnGeneralUserDefinedStockAndSupplierDisUomWithoutInspectionPlanWithoutQualityEnabled() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under poToGrnGeneralUserDefinedStockAndSupplierDisUomWithAndWithoutInspectionPlanWithoutQualityEnabled");
		// TODO Covered in
		// poToGrnGeneralUserDefinedStockAndSupplierDisUomWithAndWithoutInspectionPlanWithoutQualityEnabled()
		// Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection Document
	 * for Material Type Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Test(priority = 53, groups = {
			"GRN" }, description = "319 PO to GRN-Material-User Defined-Different Stock and Supplier UOM - With Inspection Plan Without Quality Enabled")
	public void PoToGrnMaterialUserDefinedStockAndSupplierDisUomWithInspectionPlanWithoutQualityEnabled()
			throws Exception {
		grnPageFunc
				.poToGRNMaterialUserDefinedDifferentStockAndSupplierUOMWithAndWithOutInspectionPlanWithoutQualityEnabled();
		// MrvFunc.verifyPostMaterialReceiptWithAndWithoutLocator();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO withOut Inspection For
	 * Material UserDefined Item
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 54, groups = {
			"GRN" }, description = "320 PO to GRN-Material-User Defined-Different Stock and Supplier UOM - WO Inspection Plan Without Quality Enabled")
	public void POtoGRNMaterialUserDefinedDifferentStockAndSupplierUOMWithoutInspectionPlanWithoutQualityEnabled() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under poToGRNMaterialUserDefinedDifferentStockAndSupplierUOMWithAndWithOutInspectionPlanWithoutQualityEnabled");
		// TODO Covered in
		// poToGRNMaterialUserDefinedDifferentStockAndSupplierUOMWithAndWithOutInspectionPlanWithoutQualityEnabled()
		// Method

	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection For
	 * General Rate Contract Item Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Test(priority = 55, groups = {
			"GRN" }, description = "322 PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - With Inspection PlanWithout Quality Enabled")
	public void POtoGRNGeneralRateContractDifferentStockAndSupplierUOMWithInspectionPlanWithoutQualityEnabled()
			throws Exception {
		grnPageFunc
				.poToGrnGeneralRateContractDifferentStockAndSupplierUOMWithAndWithoutInspectionGeneralTypeWithoutQualityEnabled();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection For
	 * General Rate Contract Item Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Test(priority = 56, groups = {
			"GRN" }, description = "323 PO to GRN-General-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan Without Quality Enabled")
	public void POtoGRNGeneralRateContractDifferentStockAndSupplierUOMWithoutInspectionPlanWithoutQualityEnabled() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under poToGrnGeneralRateContractDifferentStockAndSupplierUOMWithAndWithoutInspectionGeneralTypeWithoutQualityEnabled");
		// TODO Covered in
		// poToGrnGeneralRateContractDifferentStockAndSupplierUOMWithAndWithoutInspectionGeneralTypeWithoutQualityEnabled()
		// Method

	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection For
	 * Material Rate Contract Item Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Test(priority = 57, groups = {
			"GRN" }, description = "324 PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan WithoutQuality Enabled")
	public void POToGRNMaterialRateContractDifferentStockAndSupplierUOMWithInspectionPlanWithoutQualityEnabled()
			throws Exception {
		grnPageFunc
				.poToGrnlRateContractStockAndSupplierDifferentUOMWithAndWithoutInspectionMaterialWithoutQualityEnabled();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection For Mat
	 * Rate Contract Item
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Test(priority = 58, groups = {
			"GRN" }, description = "325 PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan Without Quality Enabled")
	public void POtoGRNMaterialRateContractDifferentStockAndSupplierUOMWithoutInspectionPlanWithoutQualityEnabled() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under poToGrnlRateContractStockAndSupplierDifferentUOMWithAndWithoutInspectionMaterialWithoutQualityEnabled");
		// TODO Covered in
		// poToGrnlRateContractStockAndSupplierDifferentUOMWithAndWithoutInspectionMaterialWithoutQualityEnabled()
		// Method

	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for General Type Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Test(priority = 59, groups = {
			"GRN" }, description = "327 PO to GRN-General-Price List-Different Stock and Supplier UOM - With Inspection PlanWithout Quality Enabled")
	public void PotoGrnGeneralPriceListDifferentStockAndSupplierUOMWithInspectionPlanWithoutQualityEnabled()
			throws Exception {
		grnPageFunc
				.poToGrnGeneralPriceListDifferentStockAndSupplierUOMWithandWithoutInspectionPlanWithoutQualityEnabled();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * PriceList Document for General Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 60, groups = {
			"GRN" }, description = "328 PO to GRN-General-Price List-Different Stock and Supplier UOM - WO Inspection PlanWithoutQuality Enabled")
	public void PotoGrnGeneralPriceListDifferentStockAndSupplierUOMWithOutInspectionWithoutQualityEnabled() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under poToGrnGeneralPriceListDifferentStockAndSupplierUOMWithandWithoutInspectionPlanWithoutQualityEnabled");
		// TODO Covered
		// poToGrnGeneralPriceListDifferentStockAndSupplierUOMWithandWithoutInspectionPlanWithoutQualityEnabled()
		// Method

	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for Material Type Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Test(priority = 61, groups = {
			"GRN" }, description = "329 PO to GRN-Material-Price List-Different Stock and Supplier UOM - With Inspection Plan Without Quality Enabled")
	public void PotoGrnMateriallPriceListDifferentStockAndSupplierUOMWithInspectionPlanWithoutQualityEnabled()
			throws Exception {
		grnPageFunc
				.poToGrnMaterialPriceListDifferentStockAndSupplierUOMWithandWithoutInspectionPlanWithoutQualityEnabled();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * PriceList Document for Material Type Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 62, groups = {
			"GRN" }, description = "330 PO to GRN-Material-Price List-Different Stock and Supplier UOM - WO Inspection Plan Without Quality Enabled")
	public void PotoGrnMateriallPriceListDifferentStockAndSupplierUOMWithOutInspectionPlanWithoutQualityEnabled() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under poToGrnMaterialPriceListDifferentStockAndSupplierUOMWithandWithoutInspectionPlanWithoutQualityEnabled");
		// TODO Covered
		// poToGrnMaterialPriceListDifferentStockAndSupplierUOMWithandWithoutInspectionPlanWithoutQualityEnabled()
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
