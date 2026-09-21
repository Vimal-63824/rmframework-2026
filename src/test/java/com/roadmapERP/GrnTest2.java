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

public class GrnTest2 extends RoadMapERPTest {
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
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for Material Type Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
	@Test(priority = 53, groups = {
			"GRN" }, description = "319 PO to GRN-Material-User Defined-Different Stock and Supplier UOM - With Inspection Plan Without Quality Enabled")
	public void PoToGrnMaterialUserDefinedStockAndSupplierDisUomWithInspectionPlanWithoutQualityEnabled()
			throws Exception {
		grnPageFunc
				.poToGRNMaterialUserDefinedDifferentStockAndSupplierUOMWithAndWithOutInspectionPlanWithoutQualityEnabled();

	}

	/**
	 * This Methods Is Used To Create GRN Document from PO withOut Inspection For
	 * Material UserDefined Item
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
	@Test(priority = 73, groups = {
			"GRN" }, description = "367 PO to GRN-General-Rate Contract-Different Stock and Supplier UOM")
	public void POtoGRNGeneralRateContractDifferentStockAndSupplierUOMWForFreePassItem() throws Exception {
		grnPageFunc
				.poToGrnGeneralRateContractDifferentStockAndSupplierUOMWithAndWithoutInspectionGeneralForFreePassItem();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection For
	 * General Rate Contract Item Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 74, groups = {
			"GRN" }, description = "368 PO to GRN-Material-Rate Contract-Different Stock and Supplier UOM")
	public void POtoGRNMaterialRateContractDifferentStockAndSupplierUOMWForFreePassItem() throws Exception {
		grnPageFunc
				.poToGrnMaterialRateContractDifferentStockAndSupplierUOMWithAndWithoutInspectionGeneralForFreePassItem();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for General Type Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
	@Test(priority = 80, groups = {
			"GRN" }, description = "265 PO to GRN-Stock Transfer-Price List-Different Stock and Supplier UOM - WO Inspection Plan")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
	@Test(priority = 86, groups = {
			"GRN" }, description = "315 PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM - With Inspection Plan Without Quality Enabled")
	public void POToGrnStockTransferPriceListSameStockandSupplierUOMWithInspectionPlanWithoutQualityEnabled()
			throws Exception {
		grnPageFunc.poToGrnStockTransferPriceListSameStockandSupplierUOMWithAndWOInspectionWithoutQualityEnabled();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * PriceList Document for Material Type Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
	@Test(priority = 89, groups = {
			"GRN" }, description = "366 PO to GRN-Stock Transfer-Price List-Same Stock and Supplier UOM")
	public void POToGrnStockTransferPriceListSameStockandSupplierUOMWithInspectionPlanWithFreePassItem()
			throws Exception {
		grnPageFunc.poToGrnStockTransferPriceListSameStockandSupplierUOMWithAndWOInspectionForFreePass();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with and without
	 * Inspection Stock Transfer Rate Contract Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 90, groups = {
			"GRN" }, description = "308 PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan Without Quality Enabled")
	public void PoToGrnStockTransferRateContractSameUomWithAndWithoutInspectionForWithoutQualityEnabledItem()
			throws Exception {
		grnPageFunc.PoToGrnStockTransferRateContractSameUomWithAndWithoutInspectionForWithoutQualityEnabledItem();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for Material Type Without Quality Enabled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 91, groups = { "GRN" }, description = "332 Check  the process of GRN with Tolerance Quantity")
	public void PotoGrnMateriallPriceListDifferentStockAndSupplierUOMWithInspectionPlanWithoutQualityEnabledAndToleranceQty()
			throws Exception {
		grnPageFunc
				.poToGrnGeneralRateContractSameStockAndSupplierUOMWithAndWithoutInspectionGeneralTypeCheckingWithToleranceQtyKp();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for Material Type Without Quality Line wise Cancelled
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
	@Test(priority = 94, groups = {
			"GRN" }, description = "362 PO to GRN-Stock Transfer-Rate Contract-Same Stock and Supplier UOM")
	public void PoToGrnStockTransferRateContractSameUomWithFreePassItem() throws Exception {
		grnPageFunc.PoToGrnStockTransferRateContractSameUomForFreePassItem();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection PriceList
	 * Document for Material Type Without Quality Enabled For Landed Cost And Other
	 * Charges
	 * 
	 * @author PremKumar.K-RMITS Direct GRN
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 95, groups = { "GRN" }, description = "334  Inward GRN with Landed cost and other charges")
	public void poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCostAnd()
			throws IOException {

		grnPageFunc.poToGrnGeneralUserDefinedSameStockAndSupplierUomWithAndWithoutInspectionPlanWithLandedCostLatest();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection Stock
	 * Transfer Price List Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 96, groups = {
			"GRN" }, description = "370 PO to GRN-Stock Transfer-Rate Contract-Different Stock and Supplier UOM")
	public void POToGrnStockTransferRateContractDifferentStockandSupplierUOMForFreePassItem() throws Exception {
		grnPageFunc.poToGrnStockTransferRateContractDifferentUOMForFreePassItem();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO with Inspection Stock
	 * Transfer Price List Type
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 97, groups = {
			"GRN" }, description = "371 PO to GRN-Stock Transfer-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void POToGrnStockTransferUserdefinedforDifferentStockandSupplierUOMFreepassItem() throws Exception {
		grnPageFunc.PoToGRNStockTransferUserDefinedDifferentStockAndSupplierUOMForFreePass();
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
