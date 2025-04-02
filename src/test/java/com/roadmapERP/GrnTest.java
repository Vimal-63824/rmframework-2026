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

public class GrnTest {
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
	 */
	@Test(priority = 2, groups = {
			"GRN" }, description = "231 PO to GRN-General-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void PoToGrnGeneralRateContractSameStockAndSupplierUOMWithInspectionGeneralType() {
		grnPageFunc.poToGrnGeneralRateContractSameStockAndSupplierUOMWithAndWithoutInspectionGeneralType();
		flag = true;
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
	 */
	@Test(priority = 4, groups = {
			"GRN" }, description = "233 PO to GRN-Material-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void PoToGrnGeneralRateContractSameStockAndSupplierUOMWithInspectionMaterial() {
		grnPageFunc.poToGrnGeneralRateContractSameStockAndSupplierUOMWithAndWithoutInspectionMaterial();
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
	 */
	@Test(priority = 10, groups = {
			"GRN" }, description = "235 PO to GRN-Service-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void PoToGrnServiceRateContractSameStockandSupplierUOMWoInspectionPlan() {
		grnPageFunc.poToGrnServiceRateContractSameStockAndSupplierUOMWoInspectionPlan();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for General Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 11, groups = {
			"GRN" }, description = "238 PO to GRN-General-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void PotoGrnGeneralPriceListSameStockAndSupplierUOMWithInspectionPlan() {
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
	 */
	@Test(priority = 13, groups = {
			"GRN" }, description = "236 PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void PoToGrnStockTransferRateContractSameStockAndSupplierUomWithInspection() {
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
	 */
	@Test(priority = 15, groups = {
			"GRN" }, description = "229 PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void POtoGRNStockTransferUserDefinedSameStockandSupplierUOMWithInspection() {
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
	 */
	@Test(priority = 17, groups = {
			"GRN" }, description = "240 PO to GRN-Service-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void POToGRNServicePriceListSameStockaAndSupplierUOMWOInspectionPlan() {
		grnPageFunc.poToGRNServicePriceListSameStockAndSupplierUOMWOInspectionPlan();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection Stock
	 * Transfer Price List Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 18, groups = {
			"GRN" }, description = "243 PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void POToGrnStockTransferPriceListSameStockandSupplierUOMWithInspectionPlan() {
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
	 */
	@Test(priority = 20, groups = {
			"GRN" }, description = "245 PO to GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void PoToGrnGeneralUserDefinedStockAndSupplierDisUomWithInspectionPlan() {
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
	 * This Methods Is Used To Create GRN Document from PO with Inspection For
	 * Material UserDefined Item
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Test(priority = 22, groups = {
			"GRN" }, description = "247 PO to GRN-Material-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void POtoGRNMaterialUserDefinedDifferentStockAndSupplierUOMWithInspectionPlan() {
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
