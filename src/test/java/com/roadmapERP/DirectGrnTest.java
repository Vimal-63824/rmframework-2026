package com.roadmapERP;

/**
 * @author Durai
 * @company Roadmap May 02, 2025
 */
import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.supplychainmanagement.DirectGrnPageFunctions;
import commonfunctions.supplychainmanagement.GrnPageFunctions;
import commonfunctions.supplychainmanagement.MaterialReceiptVoucherFunctions;
import utils.Mailer;
import utils.ReportLoger;
import utils.WebDriverBase;

public class DirectGrnTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static DirectGrnPageFunctions Grnfunc = new DirectGrnPageFunctions();
	private static ReportLoger logs = new ReportLoger();
	MaterialReceiptVoucherFunctions MrvFunc = new MaterialReceiptVoucherFunctions();
	static GrnPageFunctions grnPageFunc = new GrnPageFunctions();

	/**
	 * Create Request For Quotation from approved purchase request lines
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 1, groups = {
			"GRN" }, description = "337 Direct GRN-General-User Defined-Same Stock and Supplier UOM")
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionFreePassItem() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspection();
	}

	/**
	 * Create Request For Quotation from approved purchase request lines
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 2, groups = {
			"GRN" }, description = "338 Direct GRN-Material-User Defined-Same Stock and Supplier UOM")
	public void verifyCreationOfDirectGrnSameUomMaterialTypeWithInspectionFreePassItem() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnSameUomMaterialTypeWithInspection();
	}

	/**
	 * Create Request For Quotation from approved purchase request lines
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 3, groups = {
			"GRN" }, description = "339 Direct GRN-Service-User Defined-Same Stock and Supplier UOM")

	public void verifyCreationOfDirectGrnSameUomServiceTypeWithInspectionFreePassItem() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnSameUomServiceTypeWithInspection();
	}

	/**
	 * Create Request For Quotation from approved purchase request lines
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 4, groups = {
			"GRN" }, description = "341 Direct GRN-General-Rate Contract-Same Stock and Supplier UOM")
	public void verifyCreationOfDirectGrnSameUomGeneralRateContractWithInspectionFreePassItem() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractSameUomGeneralTypeWithInspection();
	}

	/**
	 * Create Request For Quotation from approved purchase request lines
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 5, groups = {
			"GRN" }, description = "342 Direct GRN-Material-Rate Contract-Same Stock and Supplier UOM")
	public void verifyCreationOfDirectGrnSameUomMaterialRateContractWithInspectionFreePassItem() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractSameUomMaterialTypeWithInspection();
	}

	/**
	 * Create Request For Quotation from approved purchase request lines
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 6, groups = {
			"GRN" }, description = "343 Direct GRN-Service-Rate Contract-Same Stock and Supplier UOM")
	public void verifyCreationOfDirectGrnSameUomServiceRateContractWithInspectionFreePassItem() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractSameUomServiceTypeWithInspection();
	}

	/**
	 * Create Request For Quotation from approved purchase request lines
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 7, groups = {
			"GRN" }, description = "345 Direct GRN-General-Price List-Same Stock and Supplier UOM")
	public void verifyCreationOfDirectGrnSameUomGeneralPricelistWithInspectionFreePassItem() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListSameUomGeneralTypeWithInspection();
	}

	/**
	 * Create Request For Quotation from approved purchase request lines
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 8, groups = {
			"GRN" }, description = "346 Direct GRN-Material-Price List-Same Stock and Supplier UOM")
	public void verifyCreationOfDirectGrnSameUomMaterialPricelistWithInspectionFreePassItem() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListSameUomMaterialTypeWithInspection();
	}

	/**
	 * Create Request For Quotation from approved purchase request lines
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 9, groups = {
			"GRN" }, description = "347 Direct GRN-Service-Price List-Same Stock and Supplier UOM")
	public void verifyCreationOfDirectGrnSameUomServicePricelistWithInspectionFreePassItem() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListSameUomServiceTypeWithInspection();
	}

	/**
	 * Create Request For Quotation from approved purchase request lines
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 10, groups = {
			"GRN" }, description = "349 Direct GRN-General-Rate Contract-Different Stock and Supplier UOM")
	public void verifyCreationOfDirectGRNGeneralRateContractDifferentStockandSupplierUOM() throws IOException {
		Grnfunc.verifyCreationOfDirectGRNGeneralRateContractDifferentStockAndSupplierUOM();
	}

	/**
	 * Create Request For Quotation from approved purchase request lines
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 11, groups = {
			"GRN" }, description = "350 Direct GRN-Material-Rate Contract-Different Stock and Supplier UOM")
	public void verifyCreationOfDirectGRNMaterialRateContractDifferentStockandSupplierUOM() throws IOException {
		Grnfunc.verifyCreationOfDirectGRNMaterialRateContractDifferentStockAndSupplierUOM();

	}
	

	/**
	 * Create Request For Quotation from approved purchase request lines
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 12, groups = {
			"GRN" }, description = "351 Direct GRN-General-Price List-Different Stock and Supplier UOM")
	public void verifyCreationOfDirectGRNGeneralPriceListDifferentStockandSupplierUOM() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeWithInspection();
	}

	/**
	 * Create Request For Quotation from approved purchase request lines
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 13, groups = {
			"GRN" }, description = "354 Direct GRN-General-Price List-Different Stock and Supplier UOM")
	public void verifyCreationOfDirectGRNMaterialPriceListDifferentStockandSupplierUOM() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeWithInspection();
	}

	/**
	 * Create Request For Quotation from approved purchase request lines
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 14, groups = {
			"GRN" }, description = "375 Check for  Auto creation MRV based on Appln. ctrl -for GRN WO Quality")
	public void VerifyToCheckforAutoCreationMrvBasedOnApplicationctrlforGrnwithOutQuality() throws IOException {
		Grnfunc.CheckforAutoCreationMrvBasedOnApplicationctrlforGrnwithOutQuality();
	}

	/**
	 * Create Request For Quotation from approved purchase request lines
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 15, groups = {
			"GRN" }, description = "374 Check for  Auto creation MRV based on Appln. ctrl -for GRN with Quality")
	public void VerifyToCheckforAutoCreationMrvBasedOnApplicationctrlforGrnwithQuality() throws IOException {
		Grnfunc.CheckforAutoCreationMrvBasedOnApplicationctrlforGrnwithOutQuality();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * RateContract Document For General Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Author("Prem-RMITS")
	@Test(priority = 16, groups = {
			"GRN" }, description = "376 Check for  Auto Post MRV based on Appln. ctrl -for GRN with Quality")
	public void CheckforAutoPostMRVbasedonApplnctrlforGRNwithQuality() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under CheckforAutoCreationMrvBasedOnApplicationctrlforGrnwithOutQuality");

		// TODO Covered
		// CheckforAutoCreationMrvBasedOnApplicationctrlforGrnwithOutQuality()
		// Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection
	 * RateContract Document For General Type
	 * 
	 * @author PremKumar.K-RMITS
	 */
	@Author("Prem-RMITS")
	@Test(priority = 17, groups = {
			"GRN" }, description = "377 Check for  Auto Post MRV based on Appln. ctrl -for GRN WO Quality")
	public void CheckForAutoPostMrvBasedOnApplnctrlforGrnWoQuality() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under CheckforAutoCreationMrvBasedOnApplicationctrlforGrnwithOutQuality");

		// TODO Covered
		// CheckforAutoCreationMrvBasedOnApplicationctrlforGrnwithOutQuality()
		// Method
	}

	/**
	 * Create Request For Quotation from approved purchase request lines
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 18, groups = { "GRN" }, description = "379 CancelMrvDocument")
	public void VerifyToCheckforCancelMrvDocument() throws IOException {
		Grnfunc.VerifyToCancelMrvDocument();
	}

	/**
	 * Create Request For Quotation from approved purchase request lines
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 19, groups = { "GRN" }, description = "373 Find MRV with all possible search parameters")
	public void FindMRVwithallpossiblesearchparameters() throws IOException {
		MrvFunc.verifyCreationOfGrnFilterAllFieldWorking();
	}

	/**
	 * Create Request For Quotation from approved purchase request lines
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 20, groups = {
			"GRN" }, description = "Direct GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 22, groups = {
			"GRN" }, description = "Direct GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionWOQcD() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionD");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionD()
		// Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 23, groups = {
			"GRN" }, description = "Direct GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithoutInspectionD() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionD");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionD()
		// Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 24, groups = {
			"GRN" }, description = "Direct GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection Plans")
	public void verifyCreationOfDirectGrnSameUomMaterialTypeWithInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnSameUomMaterialTypeWithInspectionD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 25, groups = {
			"GRN" }, description = "Direct GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnSameUomMaterialTypeWithInspectionWOQcD() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnSameUomMaterialTypeWithInspectionD");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionD()
		// Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 26, groups = {
			"GRN" }, description = "Direct GRN-Material-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnSameUomMaterialTypeWithoutInspectionD() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnSameUomMaterialTypeWithInspectionD");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomMaterialTypeWithInspectionD()
		// Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 27, groups = {
			"GRN" }, description = "Direct GRN-Service-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnSameUomServiceTypeWithoutInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnSameUomServiceTypeWithoutInspectionD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 28, groups = {
			"GRN" }, description = "Direct GRN-General-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractSameUomGeneralTypeWithInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractSameUomGeneralTypeWithInspectionD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 29, groups = {
			"GRN" }, description = "Direct GRN-General-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractSameUomGeneralTypeWithInspectionWOQcD() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnRateContractSameUomGeneralTypeWithInspectionD");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionD()
		// Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 30, groups = {
			"GRN" }, description = "Direct GRN-Material-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractSameUomMaterialTypeWithInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractSameUomMaterialTypeWithInspectionD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 31, groups = {
			"GRN" }, description = "Direct GRN-Material-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractSameUomMaterialTypeWithInspectionWOQcD() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnRateContractSameUomMaterialTypeWithInspectionD");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionD()
		// Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 32, groups = {
			"GRN" }, description = "Direct GRN-Service-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractSameUomServiceTypeWithInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractSameUomServiceTypeWithInspectionD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 33, groups = {
			"GRN" }, description = "Direct GRN-General-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListSameUomGeneralTypeWithInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListSameUomGeneralTypeWithInspectionD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 34, groups = {
			"GRN" }, description = "Direct GRN-General-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListSameUomGeneralTypeWithInspectionWOQCD() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnPriceListSameUomGeneralTypeWithInspectionD");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionD()
		// Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 35, groups = {
			"GRN" }, description = "Direct GRN-Material-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListSameUomMaterialTypeWithInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListSameUomMaterialTypeWithInspectionD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 36, groups = {
			"GRN" }, description = "Direct GRN-Material-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListSameUomMaterialTypeWithInspectionWOQcD() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnPriceListSameUomMaterialTypeWithInspectionD");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionD()
		// Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 37, groups = {
			"GRN" }, description = "Direct GRN-Service-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListSameUomServiceTypeWithInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListSameUomServiceTypeWithInspectionD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 38, groups = { "GRN" }, description = "Find GRN with all possible search parameters")
	public void verifyCreationOfGrnFilterAllFieldWorking() {
		Grnfunc.verifyCreationOfGrnFilterAllFieldWorking();
	}

	/**
	 * NEW This Methods Is Used To Create GRN Document from PO without Inspection
	 * Stock Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 39, groups = {
			"GRN" }, description = "Direct GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnUserDefinedSameUomGeneralTypeWithoutInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnUserDefinedSameUomGeneralTypeWithoutInspectionD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 40, groups = {
			"GRN" }, description = "Direct GRN-Material-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnUserDefinedSameUomMaterialTypeWithoutInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnUserDefinedSameUomMaterialTypeWithoutInspectionD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 41, groups = {
			"GRN" }, description = "Direct GRN-Service-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnUserDefinedSameUomServiceTypeWithoutInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnUserDefinedSameUomServiceTypeWithoutInspectionD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 42, groups = {
			"GRN" }, description = "Direct GRN-General-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractSameUomGeneralTypeWithoutInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractSameUomGeneralTypeWithoutInspectionD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 43, groups = {
			"GRN" }, description = "Direct GRN-Material-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractSameUomMaterialTypeWithoutInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractSameUomMaterialTypeWithoutInspectionD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 44, groups = {
			"GRN" }, description = "Direct GRN-Service-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractSameUomServiceTypeWithoutInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractSameUomServiceTypeWithoutInspectionD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 45, groups = {
			"GRN" }, description = "Direct GRN-General-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListSameUomGeneralTypeWithoutInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListSameUomGeneralTypeWithoutInspectionD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 46, groups = {
			"GRN" }, description = "Direct GRN-Material-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListSameUomMaterialTypeWithoutInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListSameUomMaterialTypeWithoutInspectionD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 47, groups = {
			"GRN" }, description = "Direct GRN-Service-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListSameUomServiceTypeWithoutInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListSameUomServiceTypeWithoutInspectionD();
	}

	/**
	 * Create Request For Quotation from approved purchase request lines
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 48, groups = {
			"GRN" }, description = "Direct GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnDifferentUomGeneralTypeWithInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnDifferentUomGeneralTypeWithInspectionD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 49, groups = {
			"GRN" }, description = "Direct GRN-General-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnDifferentUomGeneralTypeWithoutInspectionD() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnDifferentUomGeneralTypeWithoutInspectionD");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionD()
		// Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 50, groups = {
			"GRN" }, description = "Direct GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection Plans")
	public void verifyCreationOfDirectGrnDifferentUomMaterialTypeWithInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnDifferentUomMaterialTypeWithInspectionD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 51, groups = {
			"GRN" }, description = "Direct GRN-Material-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnDifferentUomMaterialTypeWithoutInspectionD() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnDifferentUomMaterialTypeWithoutInspectionD");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionD()
		// Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 52, groups = {
			"GRN" }, description = "Direct GRN-Service-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnDifferentUomServiceTypeWithoutInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnDifferentUomServiceTypeWithoutInspectionD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 53, groups = {
			"GRN" }, description = "Direct GRN-General-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractDifferentUomGeneralTypeWithInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractDifferentUomGeneralTypeWithInspectionD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 54, groups = {
			"GRN" }, description = "Direct GRN-General-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractDifferentUomGeneralTypeWithoutInspectionD() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnRateContractDifferentUomGeneralTypeWithoutInspectionD");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionD()
		// Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 55, groups = {
			"GRN" }, description = "Direct GRN-Material-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractDifferentUomMaterialTypeWithInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractDifferentUomMaterialTypeWithInspectionD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 56, groups = {
			"GRN" }, description = "Direct GRN-Material-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractDifferentUomMaterialTypeWithoutInspectionD() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnRateContractDifferentUomMaterialTypeWithoutInspectionD");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionD()
		// Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 57, groups = {
			"GRN" }, description = "Direct GRN-Service-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractDifferentUomServiceTypeWithInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractDifferentUomServiceTypeWithInspectionD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 58, groups = {
			"GRN" }, description = "Direct GRN-General-Price List-Different Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeWithInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeWithInspectionD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 59, groups = {
			"GRN" }, description = "Direct GRN-General-Price List-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeWithoutInspectionD() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeWithoutInspectionD");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionD()
		// Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 60, groups = {
			"GRN" }, description = "Direct GRN-Material-Price List-Different Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeWithInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeWithInspectionD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 61, groups = {
			"GRN" }, description = "Direct GRN-Material-Price List-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeWithoutInspectionD() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeWithoutInspectionD");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionD()
		// Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 62, groups = {
			"GRN" }, description = "Direct GRN-Service-Price List-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListDifferentUomServiceTypeWithInspectionD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListDifferentUomServiceTypeWithInspectionD();
	}

	/**
	 * Create Request For Quotation from approved purchase request lines
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Durai-RMITS")
	@Test(priority = 63, groups = {
			"GRN" }, description = "Direct GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnDifferentUomGeneralTypeWithInspectionWOQcD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnDifferentUomGeneralTypeWithInspectionWOQcD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 64, groups = {
			"GRN" }, description = "Direct GRN-General-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnDifferentUomGeneralTypeWithoutInspectionWOQcD() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnDifferentUomGeneralTypeWithoutInspectionWOQcD");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionD()
		// Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 65, groups = {
			"GRN" }, description = "Direct GRN-Material-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnDifferentUomMaterialTypeWithInspectionWOQcD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnDifferentUomMaterialTypeWithInspectionWOQcD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 66, groups = {
			"GRN" }, description = "Direct GRN-Material-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnDifferentUomMaterialTypeWithoutInspectionWOQcD() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnDifferentUomMaterialTypeWithoutInspectionWOQcD");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionD()
		// Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 67, groups = {
			"GRN" }, description = "Direct GRN-Service-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnDifferentUomServiceTypeWithoutInspectionWOQcD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnDifferentUomServiceTypeWithoutInspectionWOQcD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 68, groups = {
			"GRN" }, description = "Direct GRN-General-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractDifferentUomGeneralTypeWithInspectionWOQcD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractDifferentUomGeneralTypeWithInspectionWOQcD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 69, groups = {
			"GRN" }, description = "Direct GRN-General-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractDifferentUomGeneralTypeWithoutInspectionWOQcD() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnRateContractDifferentUomGeneralTypeWithoutInspectionWOQcD");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionD()
		// Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 70, groups = {
			"GRN" }, description = "Direct GRN-Material-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractDifferentUomMaterialTypeWithInspectionWOQcD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractDifferentUomMaterialTypeWithInspectionWOQcD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 71, groups = {
			"GRN" }, description = "Direct GRN-Material-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractDifferentUomMaterialTypeWithoutInspectionWOQcD() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnRateContractDifferentUomMaterialTypeWithoutInspectionWOQcD");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionD()
		// Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 72, groups = {
			"GRN" }, description = "Direct GRN-Service-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractDifferentUomServiceTypeWithInspectionWOQcD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractDifferentUomServiceTypeWithInspectionWOQcD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 73, groups = {
			"GRN" }, description = "Direct GRN-General-Price List-Different Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeWithInspectionWOQcD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeWithInspectionWOQcD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 74, groups = {
			"GRN" }, description = "Direct GRN-General-Price List-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeWithoutInspectionWOQcD() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeWithoutInspectionWOQcD");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionD()
		// Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 75, groups = {
			"GRN" }, description = "Direct GRN-Material-Price List-Different Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeWithInspectionWOQcD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeWithInspectionWOQcD();
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 76, groups = {
			"GRN" }, description = "Direct GRN-Material-Price List-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeWithoutInspectionWOQcD() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeWithoutInspectionWOQcD");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionD()
		// Method
	}

	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Author("Durai-RMITS")
	@Test(priority = 77, groups = {
			"GRN" }, description = "Direct GRN-Service-Price List-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListDifferentUomServiceTypeWithInspectionWOQcD() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListDifferentUomServiceTypeWithInspectionWOQcD();
	}
	
	/**
	 * Verify After Method
	 * 
	 * @author Deepan-GWL
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result, Method method) {
		commonFunc.enterAuthorNamesInExtentReport(method);
	}

}
