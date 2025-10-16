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
