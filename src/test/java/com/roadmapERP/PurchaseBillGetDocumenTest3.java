package com.roadmapERP;

import commonfunctions.Author;
/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks December 17, 2025
 */
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.financials.PurchaseBillsFunctions;
import commonfunctions.financials.PurchaseBillsFunctionsGetDocument;
import commonfunctions.qualitycontrol.InspectionDocumentFunctions;
import utils.Mailer;
import utils.ReportLoger;
import utils.WebDriverBase;
import commonfunctions.supplychainmanagement.GrnPageFunctions;
import commonfunctions.supplychainmanagement.MaterialReceiptVoucherFunctions;
import commonfunctions.supplychainmanagement.PurchaseOrderFunctions;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PurchaseBillGetDocumenTest3 extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static Mailer mail = new Mailer();
	static WebDriverBase webDB = new WebDriverBase();
	static GrnPageFunctions grnPageFunc = new GrnPageFunctions();
	PurchaseOrderFunctions POfunc = new PurchaseOrderFunctions();
	private static ReportLoger logs = new ReportLoger();
	InspectionDocumentFunctions InsFunc = new InspectionDocumentFunctions();
	MaterialReceiptVoucherFunctions MrvFunc = new MaterialReceiptVoucherFunctions();
	PurchaseBillsFunctions PurBillFunc = new PurchaseBillsFunctions();
	PurchaseBillsFunctionsGetDocument purBillGetDoc = new PurchaseBillsFunctionsGetDocument();
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
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 61, groups = {
			"GRN" }, description = "115 Domestic Casual person Supplier - With union Territory GST")
	public void VerifyToCreateDomesticCasualPersonSupplierWithUnionTerritoryGST() throws Exception {
		purBillGetDoc.verifyToCreateDomesticCasualPersonSupplierWithUnionTerritoryGST();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 62, groups = {
			"GRN" }, description = "116 Domestic Non-resident Supplier - With Local Freight GST")
	public void VerifyToCreateDomesticNonResidentSupplierWithLocalFreightGst() throws Exception {
		purBillGetDoc.verifyToCreateDomesticNonResidentSupplierWithLocalFreightGst();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 63, groups = {
			"GRN" }, description = "117 Domestic Non-resident Supplier - With union Territory GST")
	public void VerifyToCreateDomesticNonResidentSupplierWithUnionTerritoryGst() throws Exception {
		purBillGetDoc.verifyToCreateDomesticNonResidentSupplierWithUnionTerritoryGst();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 64, groups = {
			"GRN" }, description = "153 Registered Supplier with Local GST- Stock Transfer Direct (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithLocalGstStockTransferDirectStockItemServiceItemAndChargeItem()
			throws IOException {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithLocalGstStockTransferDirectStockItemServiceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 65, groups = {
			"GRN" }, description = "154 Registered Supplier with Local GST- Stock Transfer Direct With Expenses Account (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithLocalGstStockTransferDirectWithExpensesAccountStockItemServiceItemAndChargeItem()
			throws IOException {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithLocalGstStockTransferDirectWithExpensesAccountStockItemServiceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 66, groups = {
			"GRN" }, description = "156 Registered Supplier with Local GST (Allow IGST tax) - Stock Transfer Direct (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithLocalGstAllowIgstTaxStockTransferDirectStockItemServiceItemAndChargeItem()
			throws IOException {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithLocalGstAllowIgstTaxStockTransferDirectStockItemServiceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 67, groups = {
			"GRN" }, description = "157 Registered Supplier with Local GST (Allow IGST tax) - Stock Transfer Direct and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithLocalGstAllowIgstTaxStockTransferDirectAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws IOException {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithLocalGstAllowIgstTaxStockTransferDirectAndExpensesAccountStockItemServiceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 68, groups = {
			"GRN" }, description = "158 Registered Supplier with Local GST (Allow RCM tax) - Stock Transfer Direct (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithLocalGstAllowRcmtaxStockTransferDirectStockItemServiceItemAndChargeItem()
			throws IOException {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithLocalGstAllowRcmtaxStockTransferDirectStockItemServiceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 69, groups = {
			"GRN" }, description = "159 Registered Supplier with Local GST (Allow RCM tax) - Stock Transfer Direct and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithLocalGstAllowRcmTaxStockTransferDirectAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws IOException {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithLocalGstAllowRcmTaxStockTransferDirectAndExpensesAccountStockItemServiceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 70, groups = {
			"GRN" }, description = "160 Registered Supplier with Interstate GST- Stock Transfer Direct (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithInterstateGstStockTransferDirectStockItemserviceItemAndChargeItem()
			throws IOException {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithInterstateGstStockTransferDirectStockItemserviceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 71, groups = {
			"GRN" }, description = "160 Registered Supplier with Interstate GST- Stock Transfer Direct and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithInterstateGstStockTransferDirectAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws IOException {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithInterstateGstStockTransferDirectAndExpensesAccountStockItemServiceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 72, groups = {
			"GRN" }, description = "161 Registered Supplier with Interstate GST (Allow RCM tax)- Stock Transfer Direct (Stock item ,service item And charge item ) )")
	public void VerifyToCreateRegisteredSupplierWithInterstateGstAllowRCMTaxStockTransferDirectStockItemServiceitemAndchargeItem()
			throws IOException {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithInterstateGstAllowRCMTaxStockTransferDirectStockItemServiceitemAndchargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 73, groups = {
			"GRN" }, description = "161 Registered Supplier with Interstate GST (Allow RCM tax)- Stock Transfer Direct and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithInterstateGstAllowRcmTaxStockTransferDirectAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws IOException {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithInterstateGstAllowRcmTaxStockTransferDirectAndExpensesAccountStockItemServiceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 74, groups = {
			"GRN" }, description = "162 Unregistered Supplier with Local GST- Stock Transfer Direct (Stock item ,service item And charge item )")
	public void VerifyToCreateUnregisteredSupplierwithLocalGstStockTransferDirectStockItemserviceItemAndChargeItem()
			throws IOException {
		purBillGetDoc
				.verifyToCreateUnregisteredSupplierwithLocalGstStockTransferDirectStockItemserviceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 75, groups = {
			"GRN" }, description = "163 Unregistered Supplier with Local GST- Stock Transfer Direct and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyToCreateUnregisteredSupplierWithLocalGstStockTransferDirectAndExpensesAccountStockItemserviceItemAndChargeItem()
			throws IOException {
		purBillGetDoc
				.verifyToCreateUnregisteredSupplierWithLocalGstStockTransferDirectAndExpensesAccountStockItemserviceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 76, groups = {
			"GRN" }, description = "164 Unregistered Supplier with Local GST (Allow IGST tax)- Stock Transfer Direct (Stock item ,service item And charge item )")
	public void VerifyToCreateUnregisteredSupplierWithLocalGstAllowIgsttTaxStockTransferDirectStockItemServiceItemAndchargeItem()
			throws IOException {
		purBillGetDoc
				.verifyToCreateUnregisteredSupplierWithLocalGstAllowIgsttTaxStockTransferDirectStockItemServiceItemAndchargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 77, groups = {
			"GRN" }, description = "165 Unregistered Supplier with Local GST (Allow IGST tax)- Stock Transfer Direct and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyToCreateUnregisteredSupplierWithLocalGstAllowIGSTTaxStockTransferDirectAndExpensesAccountStockItemServiceItemAndchargeItem()
			throws IOException {
		purBillGetDoc
				.verifyToCreateUnregisteredSupplierWithLocalGstAllowIgstTaxStockTransferDirectAndExpensesAccountStockItemServiceItemAndchargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 78, groups = {
			"GRN" }, description = "165 Unregistered Supplier with Interstate GST- Stock Transfer Direct (Stock item ,service item And charge item )")
	public void VerifyToCreateUnregisteredSupplierWithInterstateGstStockTransferDirectStockItemServiceItemAndChargeItem()
			throws IOException {
		purBillGetDoc
				.verifyToCreateUnregisteredSupplierWithInterstateGstStockTransferDirectStockItemServiceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 79, groups = {
			"GRN" }, description = "165 Unregistered Supplier with Interstate GST- Stock Transfer Direct and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyToCreateUnregisteredSupplierWithInterstateGstStockTransferDirectAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws IOException {
		purBillGetDoc
				.verifyToCreateUnregisteredSupplierWithInterstateGstStockTransferDirectAndExpensesAccountStockItemServiceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 80, groups = { "GRN" }, description = "165 Domestic Registered Supplier - With Local Freight GST")
	public void VerifyToCreateDomesticRegisteredSupplierWithLocalFreightGst() throws IOException {
		purBillGetDoc.verifyToCreateDomesticRegisteredSupplierWithLocalFreightGst();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 81, groups = { "GRN" }, description = "165 Domestic Registered Supplier - With SEZ Unit GST")
	public void VerifyToCreateDomesticRegisteredSupplierWithSezUnitGst() throws IOException {
		purBillGetDoc.verifyToCreateDomesticRegisteredSupplierWithSezUnitGst();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 82, groups = {
			"GRN" }, description = "165 Domestic Registered Supplier - With union Territory GST")
	public void VerifyToCreateDomesticRegisteredSupplierWithUnionTerritoryGst() throws IOException {
		purBillGetDoc.verifyToCreateDomesticRegisteredSupplierWithUnionTerritoryGst();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 83, groups = {
			"GRN" }, description = "165 Domestic Unregistered Supplier - With Local Freight GST")
	public void VerifyToCreateDomesticUnregisteredSupplierWithLocalFreightGst() throws IOException {
		purBillGetDoc.verifyToCreateDomesticUnregisteredSupplierWithLocalFreightGst();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 84, groups = { "GRN" }, description = "165 Domestic UnRegistered Supplier - With SEZ Unit GST")
	public void VerifyToCreateDomesticUnRegisteredSupplierWithSezUnitGST() throws IOException {
		purBillGetDoc.verifyToCreateDomesticUnRegisteredSupplierWithSezUnitGST();
	}

	/**
	 * This Method Is Used To Logout After Method
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
