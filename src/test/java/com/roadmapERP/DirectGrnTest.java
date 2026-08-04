package com.roadmapERP;

/**
 * @author Durai
 * @company Roadmap May 02, 2025
 */
import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
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
	 * Create Request For Quotation from approved purchase request lines
	 * 
	 * @author Durai-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
	@Author("Prem-RMITS")
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
