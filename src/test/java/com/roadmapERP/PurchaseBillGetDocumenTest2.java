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

public class PurchaseBillGetDocumenTest2 extends RoadMapERPTest {
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
	@Test(priority = 31, groups = {
			"GRN" }, description = "206 Registered Supplier with Interstate GST (Allow RCM tax)- Against Stock Transfer With Deviation (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithInterstateGstAllowRCMTaxAgainstStockTransferWithDeviationStockitemserviceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithInterstateGstAllowRCMTaxAgainstStockTransferWithDeviationStockitemserviceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 32, groups = {
			"GRN" }, description = "207 Registered Supplier with Interstate GST (Allow RCM tax)- Against Stock Transfer With Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithInterstateGstAllowRcmTaxAgainstStockTransferWithDeviationAndExpensesAccountStockItemserviceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithInterstateGstAllowRcmTaxAgainstStockTransferWithDeviationAndExpensesAccountStockItemserviceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 33, groups = {
			"GRN" }, description = "209 Registered Supplier with Interstate GST (Allow RCM tax)- Against Stock Transfer Without Deviation (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithInterstateGstAllowRCMTaxAgainstStockTransferWithoutDeviationStockItemServiceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithInterstateGstAllowRCMTaxAgainstStockTransferWithoutDeviationStockItemServiceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 34, groups = {
			"GRN" }, description = "210 Registered Supplier with Interstate GST (Allow RCM tax)- Against Stock Transfer Without Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithInterstateGstAllowRcmTaxAgainstStockTransferWithoutDeviationAndExpensesAccountStockitemServiceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithInterstateGstAllowRcmTaxAgainstStockTransferWithoutDeviationAndExpensesAccountStockitemServiceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 35, groups = {
			"GRN" }, description = "195 Registered Supplier with Local GST (Allow RCM tax) - Against Stock Transfer With Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithLocalGstAllowRcmTaxAgainstStockTransferWithDeviationandExpensesAccountStockItemServiceItemAndchargeItem()
			throws Exception {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithLocalGstAllowRcmTaxAgainstStockTransferWithDeviationandExpensesAccountStockItemServiceItemAndchargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 36, groups = {
			"GRN" }, description = "197 Registered Supplier with Local GST (Allow RCM tax) - Against Stock Transfer Without Deviation (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithLocalGstAllowRcmTaxAgainstStockTransferWithoutDeviationStockitemServiceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithLocalGstAllowRcmTaxAgainstStockTransferWithoutDeviationStockitemServiceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 37, groups = {
			"GRN" }, description = "198 Registered Supplier with Local GST (Allow RCM tax) - Against Stock Transfer Without Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithLocalGstAllowRcmTaxAgainstStockTransferWithoutDeviationAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithLocalGstAllowRcmTaxAgainstStockTransferWithoutDeviationAndExpensesAccountStockItemServiceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 38, groups = {
			"GRN" }, description = "212 Unregistered Supplier with Local GST- Against Stock Transfer With Deviation (Stock item ,service item And charge item )")
	public void VerifyToCreateUnregisteredSupplierWithLocalGstAgainstStockTransferWithDeviationStockitemServiceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.verifyToCreateUnregisteredSupplierWithLocalGstAgainstStockTransferWithDeviationStockitemServiceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 39, groups = {
			"GRN" }, description = "213 Unregistered Supplier with Local GST- Against Stock Transfer With Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyToCreateUnregisteredSupplierWithLocalGstAgainstStockTransferWithDeviationAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.verifyToCreateUnregisteredSupplierWithLocalGstAgainstStockTransferWithDeviationAndExpensesAccountStockItemServiceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 40, groups = {
			"GRN" }, description = "215 Unregistered Supplier with Local GST- Against Stock Transfer Without Deviation (Stock item ,service item And charge item )")
	public void VerifyToCreateUnregisteredSupplierWithLocalGstAgainstStockTransferWithoutDeviationStockItemServiceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.verifyToCreateUnregisteredSupplierWithLocalGstAgainstStockTransferWithoutDeviationStockItemServiceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 41, groups = {
			"GRN" }, description = "216 Unregistered Supplier with Local GST- Against Stock Transfer Without Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyToCreateUnregisteredSupplierWithLocalGstAgainstStockTransferWithoutDeviationAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.verifyToCreateUnregisteredSupplierWithLocalGstAgainstStockTransferWithoutDeviationAndExpensesAccountStockItemServiceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 42, groups = {
			"GRN" }, description = "218 Unregistered Supplier with Local GST (Allow IGST tax)- Against Stock Transfer With Deviation (Stock item ,service item And charge item )")
	public void VerifyToCreateUnregisteredSupplierWithLocalGSTAllowIgstTaxAgainstStockTransferWithDeviationStockItemServiceItemAndChargeitem()
			throws Exception {
		purBillGetDoc
				.verifyToCreateUnregisteredSupplierWithLocalGSTAllowIgstTaxAgainstStockTransferWithDeviationStockItemServiceItemAndChargeitem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 43, groups = {
			"GRN" }, description = "219 Unregistered Supplier with Local GST (Allow IGST tax)- Against Stock Transfer With Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyToUnregisteredSupplierWithLocalGstAllowIgstTaxAgainstStockTransferWithDeviationAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.verifyToUnregisteredSupplierWithLocalGstAllowIgstTaxAgainstStockTransferWithDeviationAndExpensesAccountStockItemServiceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 44, groups = {
			"GRN" }, description = "221 Unregistered Supplier with Local GST (Allow IGST tax)- Against Stock Transfer Without Deviation (Stock item ,service item And charge item )")
	public void VerifyToUnregisteredSupplierWithLocalGstAllowIgstTaxAgainstStockTransferWithoutDeviationStockItemServiceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.verifyToUnregisteredSupplierWithLocalGstAllowIgstTaxAgainstStockTransferWithoutDeviationStockItemServiceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 45, groups = {
			"GRN" }, description = "222 Unregistered Supplier with Local GST (Allow IGST tax)- Against Stock Transfer Without Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyToUnregisteredSupplierWithLocalGstAllowIgstTaxAgainstStockTransferWithoutDeviationAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.verifyToUnregisteredSupplierWithLocalGstAllowIgstTaxAgainstStockTransferWithoutDeviationAndExpensesAccountStockItemServiceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 46, groups = {
			"GRN" }, description = "224 Unregistered Supplier with Interstate GST- Against Stock Transfer With Deviation (Stock item ,service item And charge item )")
	public void VerifyToUnregisteredSupplierWithInterstateGstAgainstStockTransferWithDeviationStockItemserviceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.verifyToUnregisteredSupplierWithInterstateGstAgainstStockTransferWithDeviationStockItemserviceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 47, groups = {
			"GRN" }, description = "225 Unregistered Supplier with Interstate GST- Against Stock Transfer With Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyToUnregisteredSupplierWithInterstateGstAgainstStockTransferWithDeviationAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.verifyToUnregisteredSupplierWithInterstateGstAgainstStockTransferWithDeviationAndExpensesAccountStockItemServiceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 48, groups = {
			"GRN" }, description = "227 Unregistered Supplier with Interstate GST- Against Stock Transfer Without Deviation (Stock item ,service item And charge item )")
	public void VerifyToUnregisteredSupplierWithInterstateGstAgainstStockTransferWithoutDeviationtockItemserviceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.verifyToUnregisteredSupplierWithInterstateGstAgainstStockTransferWithoutDeviationtockItemserviceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 49, groups = {
			"GRN" }, description = "228 Unregistered Supplier with Interstate GST- Against Stock Transfer Without Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyToUnregisteredSupplierWithInterstateGstAgainstStockTransferWithoutDeviationAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.verifyToUnregisteredSupplierWithInterstateGstAgainstStockTransferWithoutDeviationAndExpensesAccountStockItemServiceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 50, groups = { "GRN" }, description = "104 DomesticRegisteredSupplierWithLocalFreightGst")
	public void VerifyToDomesticRegisteredSupplierWithLocalFreightGst() throws Exception {
		purBillGetDoc.verifyToDomesticRegisteredSupplierWithLocalFreightGst();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 51, groups = { "GRN" }, description = "105 Domestic Registered Supplier - With SEZ Unit GST")
	public void VerifyToDomesticRegisteredSupplierWithSezUnitGst() throws Exception {
		purBillGetDoc.verifyToDomesticRegisteredSupplierWithSezUnitGst();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 52, groups = {
			"GRN" }, description = "106 Domestic Unregistered Supplier - With Local Freight GST")
	public void VerifyToCreateDomesticUnregisteredSupplierWithLocalFreightGST() throws Exception {
		purBillGetDoc.verifyToCreateDomesticUnregisteredSupplierWithLocalFreightGST();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 53, groups = { "GRN" }, description = "107 Domestic UnRegistered Supplier - With SEZ Unit GST")
	public void VerifyToCreateDomesticUnRegisteredSupplierWithSezUnitGst() throws Exception {
		purBillGetDoc.verifyToCreateDomesticUnRegisteredSupplierWithSezUnitGst();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 54, groups = {
			"GRN" }, description = "108 Domestic Unregistered Supplier - With union Territory GST")
	public void VerifyToCreateDomesticUnregisteredSupplierWithUnionTerritoryGstSt() throws Exception {
		purBillGetDoc.verifyToCreateDomesticUnregisteredSupplierWithUnionTerritoryGst();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 55, groups = { "GRN" }, description = "109 Domestic Composition Supplier - With Local Freight GST")
	public void VerifyToCreateDomesticCompositionSupplierWithLocalFreightGst() throws Exception {
		purBillGetDoc.verifyToCreateDomesticCompositionSupplierWithLocalFreightGst();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 56, groups = { "GRN" }, description = "110 Domestic Composition Supplier - With Local GST")
	public void VerifyToDomesticCompositionSupplierWithLocalGst() throws Exception {
		purBillGetDoc.verifyToDomesticCompositionSupplierWithLocalGst();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 57, groups = { "GRN" }, description = "111 Domestic Composition Supplier - With Inter state GST")
	public void VerifyToDomesticCompositionSupplierWithInterStateGst() throws Exception {
		purBillGetDoc.verifyToDomesticCompositionSupplierWithInterStateGst();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 58, groups = {
			"GRN" }, description = "112 Domestic Composition Supplier - With union Territory GST")
	public void VerifyToDomesticCompositionSupplierWithUnionTerritoryGst() throws Exception {
		purBillGetDoc.verifyToDomesticCompositionSupplierWithUnionTerritoryGst();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 59, groups = { "GRN" }, description = "113 Domestic Casual person Supplier - With Local GST")
	public void VerifyToDomesticCasualPersonSupplierWithLocalGst() throws Exception {
		purBillGetDoc.verifyToDomesticCasualPersonSupplierWithLocalGst();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 60, groups = {
			"GRN" }, description = "114 Domestic Casual person Supplier - With Local Freight GST")
	public void VerifyToCreateDomesticCasualPersonSupplierWithLocalFreightGst() throws Exception {
		purBillGetDoc.verifyToCreateDomesticCasualPersonSupplierWithLocalFreightGst();
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
