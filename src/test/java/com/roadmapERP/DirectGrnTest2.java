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

public class DirectGrnTest2 extends RoadMapERPTest {
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
		previousStatus = result.getStatus();
		if (previousStatus == ITestResult.FAILURE || previousStatus == ITestResult.SKIP) {
			commonFunc.verifyAfterMethod();
		}
		commonFunc.enterAuthorNamesInExtentReport(method);
	}

}
