package com.roadmapERP;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks Feburary 05, 2025
 */
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.qualitycontrol.InspectionDocumentFunctions;
import utils.Mailer;
import utils.ReportLoger;
import utils.WebDriverBase;
import commonfunctions.supplychainmanagement.GrnPageFunctions;
import commonfunctions.supplychainmanagement.MaterialReceiptVoucherFunctions;
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
	InspectionDocumentFunctions InsFunc = new InspectionDocumentFunctions();
	MaterialReceiptVoucherFunctions MrvFunc = new MaterialReceiptVoucherFunctions();

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
	 * @throws Exception 
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
	 * @throws Exception 
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
	 * This Methods Is Used To Create GRN Document from PO with and without
	 * Inspection Stock Transfer Rate Contract Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
	 */
	@Test(priority = 13, groups = {
			"GRN" }, description = "236 PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void PoToGrnStockTransferRateContractSameStockAndSupplierUomWithInspection() throws Exception {
		grnPageFunc.PoToGrnStockTransferRateContractSameStockAndSupplierUomWithAndWithoutInspection();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 14, groups = {
			"GRN" }, description = "237 PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM -Without Inspection Plan")
	public void PoToGrnStockTransferRateContractSameStockAndSupplierUomWithoutInspection() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under PoToGrnStockTransferRateContractSameStockAndSupplierUomWithInspection");
		// TODO Covered
		// PoToGrnStockTransferRateContractSameStockAndSupplierUomWithInspection()
		// Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection Stock
	 * Transfer User Defined Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
	 */
	@Test(priority = 15, groups = {
			"GRN" }, description = "229 PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void POtoGRNStockTransferUserDefinedSameStockandSupplierUOMWithInspection() throws Exception {
		grnPageFunc.poToGRNStockTransferUserDefinedSameStockandSupplierUOMWithAndWithoutInspection();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer User Defined Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 16, groups = {
			"GRN" }, description = "230 PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM -Without Inspection Plan")
	public void POtoGRNStockTransferUserDefinedSameStockandSupplierUOMWithoutInspection() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under POtoGRNStockTransferUserDefinedSameStockandSupplierUOMWithInspection");
		// TODO Covered
		// POtoGRNStockTransferUserDefinedSameStockandSupplierUOMWithInspection() Method
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
	 * This Methods Is Used To Create GRN Document from PO with Inspection Stock
	 * Transfer Price List Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
	 */
	@Test(priority = 18, groups = {
			"GRN" }, description = "243 PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void POToGrnStockTransferPriceListSameStockandSupplierUOMWithInspectionPlanQualityEnabled() throws Exception {
		grnPageFunc.poToGrnStockTransferPriceListSameStockandSupplierUOMWithAndWOInspection();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Price List Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 19, groups = {
			"GRN" }, description = "244 PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM -Without Inspection Plan")
	public void POToGrnStockTransferPriceListSameStockandSupplierUOMWOInspectionPlan() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under POToGrnStockTransferPriceListSameStockandSupplierUOMWithInspectionPlan");
		// TODO Covered
		// POToGrnStockTransferPriceListSameStockandSupplierUOMWithInspectionPlan()
		// Method
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
	 * This Methods Is Used To Create GRN Document from PO withOut Inspection For
	 * Material UserDefined Item
	 * 
	 * @author PremKumar.K-RMITS  issue reported
	 * @throws Exception 
	 */
	@Test(priority = 24, groups = {
			"GRN" }, description = "250 PO to GRN-Stock Transfer-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void POtoGRNStockTransferUserDefinedDifferentStockAndSupplierUOMWithInspectionPlan() throws Exception {
		grnPageFunc.poToGRNStockTransferUserDefinedDissimilarUOMWithAndWithoutInspection();
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO withOut Inspection For
	 * Material UserDefined Item
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 25, groups = {
			"GRN" }, description = "251 PO to GRN-Stock Transfer-User Defined-Different Stock and Supplier UOM - Without Inspection Plan")
	public void POtoGRNMaterialUserDefinedDifferentStockAndSupplierUOMWithoutInspectionPlanStockTransferType() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under POtoGRNMaterialUserDefinedDifferentStockAndSupplierUOMWithInspectionPlan");
		// TODO Covered in
		// POtoGRNMaterialUserDefinedDifferentStockAndSupplierUOMWithInspectionPlan()
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
	 * @throws Exception 
	 */
	@Test(priority = 28, groups = {
			"GRN" }, description = "254 PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void POToGRNMaterialRateContractDifferentStockAndSupplierUOMWithInspectionPlan() throws Exception {
		grnPageFunc.poToGrnlRateContractStockAndSupplierDifferentUOMWithAndWithoutInspectionMaterial();
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection For
	 * Mat Rate Contract Item
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
		// poToGrnMaterialPriceListSameStockAndSupplierUOMWithandWithoutInspectionPlan() Method
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
		// poToGrnGeneralPriceListDifferentStockAndSupplierUOMWithandWithoutInspectionPlan() Method
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
		// poToGrnMaterialPriceListDifferentStockAndSupplierUOMWithandWithoutInspectionPlan() Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection for
	 * General Type(Without Quality Enabled)
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
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
	 * @throws Exception 
	 */
	@Test(priority = 38, groups = {
			"GRN" }, description = "298 PO to GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void PoToGrnMaterialUserDefinedSameStockAndSupplierUomWithInspectionPlanWithoutQualityEnabled() throws Exception {
		grnPageFunc.poToGrnMaterialUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithoutQualityEnabled();
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
	public void PoToGrnGeneralRateContractSameStockAndSupplierUOMWithInspectionMaterialWithoutQualityEnabled() throws Exception {
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
	 RateContract Service Without Quality Enabled
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
	public void PotoGrnGeneralPriceListSameStockAndSupplierUOMWithInspectionPlanWithoutQualityEnabled() throws Exception {
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
	 * This Methods Is Used To Create GRN Document from PO with Inspection 
	 * Document for General Type Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
	 */
	@Test(priority = 51, groups = {
			"GRN" }, description = "317 PO to GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection PlanWithoutQualityEnabled")
	public void PoToGrnGeneralUserDefinedStockAndSupplierDisUomWithInspectionPlanWithoutEnabledQuality() throws Exception {
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
		// poToGrnGeneralUserDefinedStockAndSupplierDisUomWithAndWithoutInspectionPlanWithoutQualityEnabled() Method
	}
	
	
	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection
	 * Document for Material Type Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
	 */
	@Test(priority = 53, groups = {
			"GRN" }, description = "319 PO to GRN-Material-User Defined-Different Stock and Supplier UOM - With Inspection Plan Without Quality Enabled")
	public void PoToGrnMaterialUserDefinedStockAndSupplierDisUomWithInspectionPlanWithoutQualityEnabled() throws Exception {
		grnPageFunc.poToGRNMaterialUserDefinedDifferentStockAndSupplierUOMWithAndWithOutInspectionPlanWithoutQualityEnabled();
		
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
	public void POtoGRNGeneralRateContractDifferentStockAndSupplierUOMWithInspectionPlanWithoutQualityEnabled() throws Exception {
		grnPageFunc.poToGrnGeneralRateContractDifferentStockAndSupplierUOMWithAndWithoutInspectionGeneralTypeWithoutQualityEnabled();
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection For
	 * General Rate Contract Item Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException 
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
	public void POToGRNMaterialRateContractDifferentStockAndSupplierUOMWithInspectionPlanWithoutQualityEnabled() throws Exception {
		grnPageFunc.poToGrnlRateContractStockAndSupplierDifferentUOMWithAndWithoutInspectionMaterialWithoutQualityEnabled();
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection For
	 * Mat Rate Contract Item
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException 
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
		grnPageFunc.poToGrnMaterialPriceListDifferentStockAndSupplierUOMWithandWithoutInspectionPlanWithoutQualityEnabled();
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
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for Material Type Without Quality Document wise cancelled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
	 */
	@Test(priority = 63, groups = {
			"GRN" }, description = "355  PO to GRN-General-User Defined-Same Stock and Supplier UOM")
	public void poToGrnGeneralUserDefinedSameStockAndSupplierUOM() throws Exception {

		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomFreePassItemInterstate();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection for
	 * Material Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
	 */
	@Test(priority = 64, groups = {
			"GRN" }, description = "356 PO to GRN-Material-User Defined-Same Stock and Supplier UOM")
	public void PoToGrnMaterialUserDefinedSameStockAndSupplierUomFreePassItemWithIgstSupplier() throws Exception {
		grnPageFunc.poToGrnMaterialUserDefinedSameStockAndSupplierUomFreepassItemIgst();
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * Service Type Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 65, groups = {
			"GRN" }, description = "357 PO to GRN-Service-User Defined-Same Stock and Supplier UOM")
	public void PoToGrnServiceUserDefinedSameStockAndSupplierFreePassIgstSupplier() {
		grnPageFunc.verifyGrnPODomesticServiceSimilarUOMfromPoWithIgstSupplier();
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection
	 * RateContract Document For General Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
	 */
	@Test(priority = 66, groups = {
			"GRN" }, description = "359 PO to GRN-General-Rate Contract-Same Stock and Supplier UOM")
	public void PoToGrnGeneralRateContractSameStockAndSupplierUomGeneralTypeForFreePassItem() throws Exception {
		grnPageFunc.poToGrnGeneralRateContractSameStockAndSupplierUOMGeneralTypeFreePass();
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection
	 * RateContract Document for Material Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
	 */
	@Test(priority = 67, groups = {
			"GRN" }, description = "360 PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM")
	public void PoToGrnMaterialRateContractSameStockAndSupplierUOMWithFreePassIgstSupplier() throws Exception {
		grnPageFunc.poToGrnlRateContractSameStockAndSupplierUOMWithFreePassIgstSupplier();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * RateContract Document for Service Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException 
	 */
	@Test(priority = 68, groups = {
			"GRN" }, description = "361 PO to GRN-Service-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void PoToGrnServiceRateContractSameStockandSupplierUOMWithFreePassIgstSupplier() throws IOException {
		grnPageFunc.poToGrnServiceRateContractSameStockAndSupplierUOMWoInspectionPlan();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for General Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
	 */
	@Test(priority = 69, groups = {
			"GRN" }, description = "363 PO to GRN-General-Price List-Same Stock and Supplier UOM")
	public void PotoGrnGeneralPriceListSameStockAndSupplierUOMWithFreePassIgstSupplier() throws Exception {
		grnPageFunc.poToGrnGeneralPriceListSameStockAndSupplierUOMWithandWithFreePassIgstSupplier();
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for Material Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
	 */
	@Test(priority = 70, groups = {
			"GRN" }, description = "364 PO to GRN-Material-Price List-Same Stock and Supplier UOM")
	public void PotoGrnMateriallPriceListSameStockAndSupplierUOMWithFreePassIgstSupplier() throws Exception {
		grnPageFunc.poToGrnMaterialPriceListSameStockAndSupplierUOMWithandWithFreePassIgstSupplier();
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * Service Type(Price List Type)
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Test(priority = 71, groups = {
			"GRN" }, description = "240 PO to GRN-Service-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void POToGRNServicePriceListSameStockaAndSupplierUOMWithFreePassIgstSupplier() throws IOException {
		grnPageFunc.poToGRNServicePriceListSameStockAndSupplierUOMWOWithFreePassAndIgstSupplier();
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * Service Type(Price List Type)
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Test(priority = 72, groups = {
			"GRN" }, description = "365 PO to GRN-Service-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void POToGRNServicePriceListSameStockaAndSupplierUOMWithFreePassIgstSupplierFreePass() throws IOException {
		grnPageFunc.poToGRNServicePriceListSameStockAndSupplierUOMWOWithFreePassAndIgstSupplier();
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection For
	 * General Rate Contract Item Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
	 */
	@Test(priority = 73, groups = {
			"GRN" }, description = "367 PO to GRN-General-Rate Contract-Different Stock and Supplier UOM")
	public void POtoGRNGeneralRateContractDifferentStockAndSupplierUOMWForFreePassItem() throws Exception {
		grnPageFunc.poToGrnGeneralRateContractDifferentStockAndSupplierUOMWithAndWithoutInspectionGeneralForFreePassItem();
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection For
	 * General Rate Contract Item Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
	 */
	@Test(priority = 74, groups = {
			"GRN" }, description = "368 PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM")
	public void POtoGRNMaterialRateContractDifferentStockAndSupplierUOMWForFreePassItem() throws Exception {
		grnPageFunc.poToGrnMaterialRateContractDifferentStockAndSupplierUOMWithAndWithoutInspectionGeneralForFreePassItem();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for General Type Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
	 */
	@Test(priority = 75, groups = {
			"GRN" }, description = "371 PO to GRN-General-Price List-Different Stock and Supplier UOM")
	public void PotoGrnGeneralPriceListDifferentStockAndSupplierUOMWithInspectionPlanWithoutQualityEnabledForFreePassItem()
			throws Exception {
		grnPageFunc.poToGrnGeneralPriceListDifferentStockAndSupplierUOMForFreePassItem();
	}
	
	
	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for General Type Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
	 */
	@Test(priority = 76, groups = {
			"GRN" }, description = "372 PO to GRN-Material-Price List-Different Stock and Supplier UOM")
	public void PotoGrnMaterialPriceListDifferentStockAndSupplierUOMWithInspectionPlanWithoutQualityEnabledForFreePassItem()
			throws Exception {
		grnPageFunc.poToGrnMaterialPriceListDifferentStockAndSupplierUOMForFreePassItem();
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO with and without
	 * Inspection Stock Transfer Rate Contract Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
	 */
	@Test(priority = 77, groups = {
			"GRN" }, description = "257 PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void PoToGrnStockTransferRateContractDifferentUomWithInspection() throws Exception {
		grnPageFunc.PoToGrnStockTransferRateContractDifferentUomWithAndWithoutInspection();
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * PriceList Document for Material Type Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 78, groups = {
			"GRN" }, description = "258 PO to GRN-Stock Transfer-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan")
	public void PoToGrnStockTransferRateContractDifferentUomWithInspectionWithoutInspectionPlan() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under PoToGrnStockTransferRateContractDifferentUomWithAndWithoutInspection");
		// TODO Covered
		// PoToGrnStockTransferRateContractDifferentUomWithInspectionWithoutInspectionPlan()
		// Method
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection Stock
	 * Transfer Price List Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
	 */
	@Test(priority = 79, groups = {
			"GRN" }, description = "264 PO to GRN-Stock Transfer-Price List-Different Stock and Supplier UOM - With Inspection Plan")
	public void POToGrnStockTransferPriceListSameStockandSupplierUOMWithInspectionPlan() throws Exception {
		grnPageFunc.poToGrnStockTransferPriceListDifferentUOMWithAndWOInspection();
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * PriceList Document for Material Type Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 80, groups = {"GRN" }, description = "265 PO to GRN-Stock Transfer-Price List-Different Stock and Supplier UOM - WO Inspection Plan")
	public void POToGrnStockTransferPriceListSameStockandSupplierUOMWithOutInspectionPlan() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under POToGrnStockTransferPriceListSameStockandSupplierUOMWithInspectionPlan");
		// TODO Covered
		// POToGrnStockTransferPriceListSameStockandSupplierUOMWithInspectionPlan()
		// Method
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection Stock
	 * Transfer Price List Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
	 */
	@Test(priority = 81, groups = {
			"GRN" }, description = "370 PO to GRN-Stock Transfer-Rate Contract-Different Stock and Supplier UOM")
	public void POToGrnStockTransferPriceListdifferentStockandSupplierUOMForFreePassItem() throws Exception {
		grnPageFunc.poToGrnStockTransferPriceListDifferentUOMForFreePassItem();
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection Stock
	 * Transfer Price List Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
	 */
	@Test(priority = 82, groups = {
			"GRN" }, description = "301 PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void POToGrnStockTransferUserdefinedSameStockandSupplierUOMForWithoutQualityEnabled() throws Exception {
		grnPageFunc.PoToGRNStockTransferUserDefinedSameStockAndSupplierUOMWithInspectionPlan();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * PriceList Document for Material Type Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 83, groups = {
			"GRN" }, description = "302 PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void POToGrnStockTransferUserDefinedSameStockandSupplierUOMForWithAndWithoutQualityEnabled() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under PoToGRNStockTransferUserDefinedSameStockAndSupplierUOMWithInspectionPlan");
		// TODO Covered
		// PoToGRNStockTransferUserDefinedSameStockAndSupplierUOMWithInspectionPlan()
		// Method
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO with and without
	 * Inspection Stock Transfer Rate Contract Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
	 */
	@Test(priority = 84, groups = {
			"GRN" }, description = "308 PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan Without Quality Enabled")
	public void PoToGrnStockTransferRateContractSameUomWithInspectionWithoutQualityEnabled() throws Exception {
		grnPageFunc.PoToGrnStockTransferRateContractSameUomWithAndWithoutInspection();
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * PriceList Document for Material Type Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 85, groups = {
			"GRN" }, description = "309 PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void PoToGrnStockTransferRateContractSameUomWithInspectionWithoutQualityEnabledWithInspection() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under PoToGrnStockTransferRateContractSameUomWithAndWithoutInspection");
		// TODO Covered
		// PoToGrnStockTransferRateContractSameUomWithAndWithoutInspection()
		// Method
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection Stock
	 * Transfer Price List Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
	 */
	@Test(priority = 86, groups = {
			"GRN" }, description = "315 PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM - With Inspection Plan Without Quality Enabled")
	public void POToGrnStockTransferPriceListSameStockandSupplierUOMWithInspectionPlanWithoutQualityEnabled() throws Exception {
		grnPageFunc.poToGrnStockTransferPriceListSameStockandSupplierUOMWithAndWOInspectionWithoutQualityEnabled();
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * PriceList Document for Material Type Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 87, groups = {
			"GRN" }, description = "316 PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM - WO Inspection Plan Without Quality Enabled")
	public void PoToGRNStockTransferPriceListSameStockAndSupplierUomWOInspectionPlan() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under poToGrnStockTransferPriceListSameStockandSupplierUOMWithAndWOInspectionWithoutQualityEnabled");
		// TODO Covered
		// poToGrnStockTransferPriceListSameStockandSupplierUOMWithAndWOInspectionWithoutQualityEnabled()
		// Method
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection Stock
	 * Transfer Price List Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
	 */
	@Test(priority = 88, groups = {
			"GRN" }, description = "358 PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void POToGrnStockTransferUserdefinedSameStockandSupplierUOMForFreePassItem() throws Exception {
		grnPageFunc.PoToGRNStockTransferUserDefinedSameStockAndSupplierUOMWithInspectionPlanForFreePassItem();
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection Stock
	 * Transfer Price List Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
	 */
	@Test(priority = 89, groups = {
			"GRN" }, description = "366 PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM")
	public void POToGrnStockTransferPriceListSameStockandSupplierUOMWithInspectionPlanWithFreePassItem() throws Exception {
		grnPageFunc.poToGrnStockTransferPriceListSameStockandSupplierUOMWithAndWOInspectionForFreePass();
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO with and without
	 * Inspection Stock Transfer Rate Contract Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
	 */
	@Test(priority = 90, groups = {
			"GRN" }, description = "308 PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan Without Quality Enabled")
	public void PoToGrnStockTransferRateContractSameUomWithAndWithoutInspectionForWithoutQualityEnabledItem() throws Exception {
		grnPageFunc.PoToGrnStockTransferRateContractSameUomWithAndWithoutInspectionForWithoutQualityEnabledItem();
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for Material Type Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
	 */
	@Test(priority = 91, groups = {
			"GRN" }, description = "332 Check  the process of GRN with Tolerance Quantity")
	public void PotoGrnMateriallPriceListDifferentStockAndSupplierUOMWithInspectionPlanWithoutQualityEnabledAndToleranceQty()
			throws Exception {
		 grnPageFunc
		 .poToGrnGeneralRateContractSameStockAndSupplierUOMWithAndWithoutInspectionGeneralTypeCheckingWithToleranceQty();
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for Material Type Without Quality Line wise Cancelled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Test(priority = 92, groups = { "GRN" }, description = "335  cancel the GRN lines")
	public void poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanLinewiseCancelled()
			throws IOException {

		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLinewiseCancelled();
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for Material Type Without Quality Document wise cancelled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Test(priority = 93, groups = { "GRN" }, description = "336  Cancel GRN Document")
	public void poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanDocumentwiseCancelled()
			throws IOException {

		grnPageFunc
				.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithDocumentwiseCancelled();
	}
	
	/**
	 * This Methods Is Used To Create GRN Document from PO with and without
	 * Inspection Stock Transfer Rate Contract Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception 
	 */
	@Test(priority = 94, groups = {
			"GRN" }, description = "362 PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM")
	public void PoToGrnStockTransferRateContractSameUomWithFreePassItem() throws Exception {
		grnPageFunc.PoToGrnStockTransferRateContractSameUomForFreePassItem();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for Material Type Without Quality Enabled For Landed Cost And Other Charges
	 * 
	 * @author PremKumar.K-RMITS Direct GRN
	 * @throws IOException
	 */
	@Test(priority = 95, groups = { "GRN" }, description = "334  Inward GRN with Landed cost and other charges")
	public void poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCostAnd()
			throws IOException {

		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCost();
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
