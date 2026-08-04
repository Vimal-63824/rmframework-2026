package com.roadmapERP;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks Feburary 05, 2025
 */
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.qualitycontrol.InspectionDocumentFunctions;
import commonfunctions.supplychainmanagement.GrnPageFunctions;
import commonfunctions.supplychainmanagement.MaterialReceiptVoucherFunctions;
import commonfunctions.supplychainmanagement.PurchaseOrderFunctions;
import utils.Mailer;
import utils.ReportLoger;
import utils.WebDriverBase;

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
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * Service Type Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
	@Test(priority = 18, groups = {
			"GRN" }, description = "243 PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void POToGrnStockTransferPriceListSameStockandSupplierUOMWithInspectionPlanQualityEnabled()
			throws Exception {
		grnPageFunc.poToGrnStockTransferPriceListSameStockandSupplierUOMWithAndWOInspection();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Price List Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	 * @author PremKumar.K-RMITS issue reported
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
