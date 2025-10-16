package com.roadmapERP;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

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
	@Test(priority = 20, groups = {
			"GRN" }, description = "Direct GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspection() throws IOException { 
		Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspection();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 21, groups = {
			"GRN" }, description = "Direct GRN-General-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionWOQc() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnSameUomGeneralTypeWithInspection");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspection()
		// Method
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 22, groups = {
			"GRN" }, description = "Direct GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnSameUomGeneralTypeWithoutInspection() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnSameUomGeneralTypeWithInspection");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspection()
		// Method
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 23,groups = {
	"GRN" }, description = "Direct GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection Plans")
	public void verifyCreationOfDirectGrnSameUomMaterialTypeWithInspection() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnSameUomMaterialTypeWithInspection();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 24, groups = {
			"GRN" }, description = "Direct GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnSameUomMaterialTypeWithInspectionWOQc() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnSameUomMaterialTypeWithInspection");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspection()
		// Method
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 25, groups = {
			"GRN" }, description = "Direct GRN-Material-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnSameUomMaterialTypeWithoutInspection() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnSameUomMaterialTypeWithInspection");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomMaterialTypeWithInspection()
		// Method
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 26,groups = {
	"GRN" }, description = "Direct GRN-Service-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnSameUomServiceTypeWithoutInspection() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnSameUomServiceTypeWithoutInspection();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 27,groups = {
	"GRN" }, description = "Direct GRN-General-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractSameUomGeneralTypeWithInspection() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractSameUomGeneralTypeWithInspection();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 28, groups = {
			"GRN" }, description = "Direct GRN-General-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractSameUomGeneralTypeWithInspectionWOQc() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnRateContractSameUomGeneralTypeWithInspection");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspection()
		// Method
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 29,groups = {
	"GRN" }, description = "Direct GRN-Material-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractSameUomMaterialTypeWithInspection() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractSameUomMaterialTypeWithInspection();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 30, groups = {
			"GRN" }, description = "Direct GRN-Material-Rate Contract-Same Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractSameUomMaterialTypeWithInspectionWOQc() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnRateContractSameUomMaterialTypeWithInspection");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspection()
		// Method
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 31,groups = {
	"GRN" }, description = "Direct GRN-Service-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractSameUomServiceTypeWithInspection() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractSameUomServiceTypeWithInspection();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 32,groups = {
	"GRN" }, description = "Direct GRN-General-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListSameUomGeneralTypeWithInspection() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListSameUomGeneralTypeWithInspection();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 33, groups = {
			"GRN" }, description = "Direct GRN-General-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListSameUomGeneralTypeWithInspectionWOQC() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnPriceListSameUomGeneralTypeWithInspection");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspection()
		// Method
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 34,groups = {
	"GRN" }, description = "Direct GRN-Material-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListSameUomMaterialTypeWithInspection() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListSameUomMaterialTypeWithInspection();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 35, groups = {
			"GRN" }, description = "Direct GRN-Material-Price List-Same Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListSameUomMaterialTypeWithInspectionWOQc() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnPriceListSameUomMaterialTypeWithInspection");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspection()
		// Method
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 36,groups = {
	"GRN" }, description = "Direct GRN-Service-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListSameUomServiceTypeWithInspection() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListSameUomServiceTypeWithInspection();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 37,groups = {
	"GRN" }, description = "Find GRN with all possible search parameters")
	public void verifyCreationOfGrnFilterAllFieldWorking(){
		Grnfunc.verifyCreationOfGrnFilterAllFieldWorking();
	}
	
	/**
	 * NEW
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 38,groups = {
	"GRN" }, description = "Direct GRN-General-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnUserDefinedSameUomGeneralTypeWithoutInspection() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnUserDefinedSameUomGeneralTypeWithoutInspection();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 39,groups = {
	"GRN" }, description = "Direct GRN-Material-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnUserDefinedSameUomMaterialTypeWithoutInspection() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnUserDefinedSameUomMaterialTypeWithoutInspection();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 40,groups = {
	"GRN" }, description = "Direct GRN-Service-User Defined-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnUserDefinedSameUomServiceTypeWithoutInspection() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnUserDefinedSameUomServiceTypeWithoutInspection();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 41,groups = {
	"GRN" }, description = "Direct GRN-General-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractSameUomGeneralTypeWithoutInspection() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractSameUomGeneralTypeWithoutInspection();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 42,groups = {
	"GRN" }, description = "Direct GRN-Material-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractSameUomMaterialTypeWithoutInspection() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractSameUomMaterialTypeWithoutInspection();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 43,groups = {
	"GRN" }, description = "Direct GRN-Service-Rate Contract-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractSameUomServiceTypeWithoutInspection() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractSameUomServiceTypeWithoutInspection();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 44,groups = {
	"GRN" }, description = "Direct GRN-General-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListSameUomGeneralTypeWithoutInspection() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListSameUomGeneralTypeWithoutInspection();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 45,groups = {
	"GRN" }, description = "Direct GRN-Material-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListSameUomMaterialTypeWithoutInspection() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListSameUomMaterialTypeWithoutInspection();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 46,groups = {
	"GRN" }, description = "Direct GRN-Service-Price List-Same Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListSameUomServiceTypeWithoutInspection() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListSameUomServiceTypeWithoutInspection();
	}
	/**
	 * Create Request For Quotation from approved purchase request lines
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Test(priority = 47, groups = {
			"GRN" }, description = "Direct GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnDifferentUomGeneralTypeWithInspection() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnDifferentUomGeneralTypeWithInspection();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 48, groups = {
			"GRN" }, description = "Direct GRN-General-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnDifferentUomGeneralTypeWithoutInspection() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnDifferentUomGeneralTypeWithoutInspection");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspection()
		// Method
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 49,groups = {
	"GRN" }, description = "Direct GRN-Material-User Defined-Same Stock and Supplier UOM - With Inspection Plans")
	public void verifyCreationOfDirectGrnDifferentUomMaterialTypeWithInspection() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnDifferentUomMaterialTypeWithInspection();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 50, groups = {
			"GRN" }, description = "Direct GRN-Material-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnDifferentUomMaterialTypeWithoutInspection() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnDifferentUomMaterialTypeWithoutInspection");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspection()
		// Method
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 51,groups = {
	"GRN" }, description = "Direct GRN-Service-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnDifferentUomServiceTypeWithoutInspection() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnDifferentUomServiceTypeWithoutInspection();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 52,groups = {
	"GRN" }, description = "Direct GRN-General-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractDifferentUomGeneralTypeWithInspection() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractDifferentUomGeneralTypeWithInspection();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 53, groups = {
			"GRN" }, description = "Direct GRN-General-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractDifferentUomGeneralTypeWithoutInspection() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnRateContractDifferentUomGeneralTypeWithoutInspection");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspection()
		// Method
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 54,groups = {
	"GRN" }, description = "Direct GRN-Material-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractDifferentUomMaterialTypeWithInspection() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractDifferentUomMaterialTypeWithInspection();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 55, groups = {
			"GRN" }, description = "Direct GRN-Material-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractDifferentUomMaterialTypeWithoutInspection() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnRateContractDifferentUomMaterialTypeWithoutInspection");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspection()
		// Method
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 56,groups = {
	"GRN" }, description = "Direct GRN-Service-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractDifferentUomServiceTypeWithInspection() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractDifferentUomServiceTypeWithInspection();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 57,groups = {
	"GRN" }, description = "Direct GRN-General-Price List-Different Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeWithInspection() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeWithInspection();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 58, groups = {
			"GRN" }, description = "Direct GRN-General-Price List-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeWithoutInspection() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeWithoutInspection");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspection()
		// Method
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 59,groups = {
	"GRN" }, description = "Direct GRN-Material-Price List-Different Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeWithInspection() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeWithInspection();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 60, groups = {
			"GRN" }, description = "Direct GRN-Material-Price List-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeWithoutInspection() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeWithoutInspection");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspection()
		// Method
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 61,groups = {
	"GRN" }, description = "Direct GRN-Service-Price List-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListDifferentUomServiceTypeWithInspection() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListDifferentUomServiceTypeWithInspection();
	}
	/**
	 * Create Request For Quotation from approved purchase request lines
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Test(priority = 62, groups = {
			"GRN" }, description = "Direct GRN-General-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnDifferentUomGeneralTypeWithInspectionWOQc() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnDifferentUomGeneralTypeWithInspectionWOQc();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 63, groups = {
			"GRN" }, description = "Direct GRN-General-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnDifferentUomGeneralTypeWithoutInspectionWOQc() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnDifferentUomGeneralTypeWithoutInspectionWOQc");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspection()
		// Method
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 64,groups = {
	"GRN" }, description = "Direct GRN-Material-User Defined-Different Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnDifferentUomMaterialTypeWithInspectionWOQc() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnDifferentUomMaterialTypeWithInspectionWOQc();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 65, groups = {
			"GRN" }, description = "Direct GRN-Material-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnDifferentUomMaterialTypeWithoutInspectionWOQc() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnDifferentUomMaterialTypeWithoutInspectionWOQc");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspection()
		// Method
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 66,groups = {
	"GRN" }, description = "Direct GRN-Service-User Defined-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnDifferentUomServiceTypeWithoutInspectionWOQc() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnDifferentUomServiceTypeWithoutInspectionWOQc();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 67,groups = {
	"GRN" }, description = "Direct GRN-General-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractDifferentUomGeneralTypeWithInspectionWOQc() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractDifferentUomGeneralTypeWithInspectionWOQc();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 68, groups = {
			"GRN" }, description = "Direct GRN-General-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractDifferentUomGeneralTypeWithoutInspectionWOQc() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnRateContractDifferentUomGeneralTypeWithoutInspectionWOQc");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspection()
		// Method
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 69,groups = {
	"GRN" }, description = "Direct GRN-Material-Rate Contract-Different Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractDifferentUomMaterialTypeWithInspectionWOQc() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractDifferentUomMaterialTypeWithInspectionWOQc();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 70, groups = {
			"GRN" }, description = "Direct GRN-Material-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractDifferentUomMaterialTypeWithoutInspectionWOQc() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnRateContractDifferentUomMaterialTypeWithoutInspectionWOQc");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspection()
		// Method
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 71,groups = {
	"GRN" }, description = "Direct GRN-Service-Rate Contract-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnRateContractDifferentUomServiceTypeWithInspectionWOQc() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnRateContractDifferentUomServiceTypeWithInspectionWOQc();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 72,groups = {
	"GRN" }, description = "Direct GRN-General-Price List-Different Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeWithInspectionWOQc() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeWithInspectionWOQc();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 73, groups = {
			"GRN" }, description = "Direct GRN-General-Price List-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeWithoutInspectionWOQc() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnPriceListDifferentUomGeneralTypeWithoutInspectionWOQc");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspection()
		// Method
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 74,groups = {
	"GRN" }, description = "Direct GRN-Material-Price List-Different Stock and Supplier UOM - With Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeWithInspectionWOQc() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeWithInspectionWOQc();
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 75, groups = {
			"GRN" }, description = "Direct GRN-Material-Price List-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeWithoutInspectionWOQc() {
		logs.logging("INFO",
				"This Test Scenarios Is Covered Under verifyCreationOfDirectGrnPriceListDifferentUomMaterialTypeWithoutInspectionWOQc");
		// TODO Covered
		// verifyCreationOfDirectGrnSameUomGeneralTypeWithInspection()
		// Method
	}
	/**
	 * This Methods Is Used To Create GRN Document from PO without Inspection Stock
	 * Transfer Rate Contract Type
	 * 
	 * @author Durai-RMITS
	 */
	@Test(priority = 76,groups = {
	"GRN" }, description = "Direct GRN-Service-Price List-Different Stock and Supplier UOM - WO Inspection Plan")
	public void verifyCreationOfDirectGrnPriceListDifferentUomServiceTypeWithInspectionWOQc() throws IOException {
		Grnfunc.verifyCreationOfDirectGrnPriceListDifferentUomServiceTypeWithInspectionWOQc();
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
