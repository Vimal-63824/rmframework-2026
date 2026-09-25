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

public class PurchaseBillGetDocumenTest extends RoadMapERPTest {
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
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 1, groups = { "GRN" }, description = "148  GRN Landed cost in bill booking from Get document")
	public void VerifyToCreateGrnLandedCostInBillBookingFromGetDocument() throws IOException {
		purBillGetDoc.verifyToCreateGrnLandedCostInBillBookingFromGetDocument();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 2, groups = { "GRN" }, description = "149  GRN Landed cost in bill booking from Get document")
	public void VerifyToCreateGrnLandedCostInBillBookingFromGetDocumentWithTds() throws IOException {
		purBillGetDoc.verifyToCreateGrnLandedCostInBillBookingFromGetDocumentWithTds();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 3, groups = { "GRN" }, description = "150  GRN Landed cost in bill booking from Get document")
	public void VerifyToCreateGrnLandedCostInBillBookingFromGetDocumentWithTdsAndEsi() throws IOException {
		purBillGetDoc.verifyToCreateGrnLandedCostInBillBookingFromGetDocumentWithTdsAndEsi();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 4, groups = {
			"GRN" }, description = "151  GRN Landed cost in bill booking from Get document With Tax addition")
	public void VerifyToCreateGrnLandedCostInBillbookingFromGetDocumentWithTaxAddition() throws IOException {
		purBillGetDoc.verifyToCreateGrnLandedCostInBillBookingFromGetDocumentWithTaxAddition();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 5, groups = {
			"GRN" }, description = "152  GRN Landed cost in bill booking from Get document With New Charges addition")
	public void VerifyToCreateGrnLandedCostInBillbookingFromGetDocumentWithNewChargesAddition() throws IOException {
		purBillGetDoc.verifyToCreateGrnLandedCostInBillbookingFromGetDocumentWithNewChargesAddition();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 6, groups = {
			"GRN" }, description = "153 GRN Landed cost in bill booking from Get document With Value addition")
	public void VerifyToCreateGrnLandedCostInBillbookingFromGetDocumentWithValueAddition() throws IOException {
		purBillGetDoc.verifyToCreateGrnLandedCostInBillbookingFromGetDocumentWithValueAddition();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 7, groups = {
			"GRN" }, description = "154 GRN Landed cost in bill booking from Get document With Value Dedection")
	public void VerifyToCreateGrnLandedCostInBillbookingFromGetDocumentWithValueDeduction() throws IOException {
		purBillGetDoc.verifyToCreateGrnLandedCostInBillbookingFromGetDocumentWithValueDeduction();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 8, groups = { "GRN" }, description = "156 GRN Landed cost with Deviation (Round off Var.)")
	public void VerifyToCreateGrnLandedCostInBillbookingFromGetDocumentWithDeviation() throws IOException {
		purBillGetDoc.verifyToCreateGrnLandedCostInBillbookingFromGetDocumentWithDeviation();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 9, groups = { "GRN" }, description = "157  GRN Landed cost in bill booking from Get document")
	public void VerifyToCreateGrnLandedCostInBillBookingAgainstPending() throws IOException {
		purBillGetDoc.verifyToCreateGrnLandedCostInBillBookingAgainstPending();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 10, groups = {
			"GRN" }, description = "158  GRN Landed cost in bill booking from Against Pending With TDS")
	public void VerifyToCreateGrnLandedCostInBillBookingAgainstPendingWithTds() throws IOException {
		purBillGetDoc.verifyToCreateGrnLandedCostInBillBookingAgainstPendingWithTds();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 11, groups = {
			"GRN" }, description = "159  GRN Landed cost in bill booking from Against Pending With TDS and ESI")
	public void VerifyToCreateGrnLandedCostInBillBookingAgainstPendingWithTdsAndEsi() throws IOException {
		purBillGetDoc.verifyToCreateGrnLandedCostInBillBookingAgainstPendingWithTdsAndEsi();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 12, groups = {
			"GRN" }, description = "160  GRN Landed cost in bill booking from Against Pending With Tax addition")
	public void VerifyToCreateGrnLandedCostInBillBookingAgainstPendingTaxAddition() throws IOException {
		purBillGetDoc.verifyToCreateGrnLandedCostInBillBookingAgainstPendingWithTaxAddition();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 13, groups = {
			"GRN" }, description = "161 GRN Landed cost in bill booking from Against Pending With New Charges addition")
	public void VerifyToCreateGrnLandedCostInBillBookingAgainstPendingWithNewChargeItem() throws IOException {
		purBillGetDoc.verifyToCreateGrnLandedCostInBillBookingAgainstPendingWithNewChargeItem();
	}

//	/**
//	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
//	 * document
//	 * 
//	 * @author PremKumar.K-RMITS
//	 * @throws IOException
//	 */
//	@Author("Prem-RMITS")
//	@Test(priority = 14, groups = { "FIN" }, description = "165  Material Receipt Landed cost in bill booking from Get document.")
//	public void verifyToCreateMaterialReceiptLandedCostInBillBookingFromGetDocument() throws IOException {
//		purBillGetDoc.verifyToCreateMaterialReceiptLandedCostInBillBookingFromGetDocument();
//	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 15, groups = {
			"GRN" }, description = "162  GRN Landed cost in bill booking from Against Pending With Value addition")
	public void VerifyToCreateGrnLandedCostInBillBookingAgainstPendingWithValueAddition() throws IOException {
		purBillGetDoc.verifyToCreateGrnLandedCostInBillBookingAgainstPendingWithValueAddition();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 16, groups = {
			"GRN" }, description = "163  GRN Landed cost in bill booking from Against Pending With Value Dedection")
	public void VerifyToCreateGrnLandedCostInBillBookingAgainstPendingWithValueDeduction() throws IOException {
		purBillGetDoc.verifyToCreateGrnLandedCostInBillBookingAgainstPendingWithValueDeduction();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 17, groups = {
			"GRN" }, description = "161 GRN Landed cost in bill booking from Against Pending With New Charges addition")
	public void VerifyToCreateGrnLandedCostInBillbookingFromAgainstPendingWithNewChargesAddition() throws IOException {
		purBillGetDoc.verifyToCreateGrnLandedCostInBillbookingFromAgainstPendingWithNewChargesAddition();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 18, groups = {
			"GRN" }, description = "182 Registered Supplier with Local GST- Against Stock Transfer With Deviation (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithLocalGstAgainstStockTransferWithDeviationForStockitem()
			throws Exception {
		purBillGetDoc.verifyToCreateRegisteredSupplierWithLocalGstAgainstStockTransferWithDeviationStockitem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 19, groups = {
			"GRN" }, description = "183 Registered Supplier with Local GST- Against Stock Transfer With Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithLocalGstAgainstStockTransferWithDeviationAndExpenseAccountForStockitem()
			throws Exception {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithLocalGstAgainstStockTransferWithDeviationAndExpenseAccountForStockitem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 20, groups = {
			"GRN" }, description = "185 Registered Supplier with Local GST- Against Stock Transfer Without Deviation (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithLocalGstAgainstStockTransferWithOutDeviationForStockitem()
			throws Exception {
		purBillGetDoc.verifyToCreateRegisteredSupplierWithLocalGstAgainstStockTransferWithOutDeviationStockitem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 21, groups = {
			"GRN" }, description = "186 Registered Supplier with Local GST- Against Stock Transfer Without Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithLocalGstAgainstStockTransferWithoutDeviationAndExpenseAccountForStockitem()
			throws Exception {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithLocalGstAgainstStockTransferWithoutDeviationAndExpenseAccountForStockitem();

	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 22, groups = {
			"GRN" }, description = "188 Registered Supplier with Local GST (Allow IGST tax) - Against Stock Transfer With Deviation (Stock item ,service item And charge item ))")
	public void VerifyToCreateRegisteredSupplierWithLocalGstAllowIgsttaxAgainstStockTransferWithDeviationStockitemserviceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithLocalGstAllowIgstTaxAgainstStockTransferWithDeviationStockItemServiceItemAndChargeItemAutoCheck();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 23, groups = {
			"GRN" }, description = "189 Registered Supplier with Local GST (Allow IGST tax) - Against Stock Transfer With Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithLocalGstAllowIgsttaxAgainstStockTransferWithDeviationAndExpenseAccountStockitemserviceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithLocalGstAllowIgsttaxAgainstStockTransferWithDeviationAndExpenseAccountStockitemserviceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 24, groups = {
			"GRN" }, description = "191 Registered Supplier with Local GST (Allow IGST tax) - Against Stock Transfer Without Deviation (Stock item ,service item And charge item ) )")
	public void VerifyToCreateRegisteredSupplierWithLocalGstAllowIgsttaxAgainstStockTransferWithOutDeviationStockitemserviceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithLocalGstAllowIgsttaxAgainstStockTransferWithOutDeviationStockitemserviceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 25, groups = {
			"GRN" }, description = "192 Registered Supplier with Local GST (Allow IGST tax) - Against Stock Transfer Without Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithLocalGstAllowIgsttaxAgainstStockTransferWithOutDeviationAndExpenseAccountStockitemserviceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithLocalGstAllowIgsttaxAgainstStockTransferWithOutDeviationAndExpenseAccountStockitemserviceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 26, groups = {
			"GRN" }, description = "194 Registered Supplier with Local GST (Allow RCM tax) - Against Stock Transfer With Deviation (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithLocalGstAllowRcmtaxAgainstStockTransferWithDeviationStockItemserviceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithLocalGstAllowRcmtaxAgainstStockTransferWithDeviationStockItemserviceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 27, groups = {
			"GRN" }, description = "200 Registered Supplier with Interstate GST- Against Stock Transfer With Deviation (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithInterStateGstAgainstStockTransferWithDeviationForStockitem()
			throws Exception {
		purBillGetDoc.verifyToCreateRegisteredSupplierWithInterStateGstAgainstStockTransferWithDeviationForStockitem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 28, groups = {
			"GRN" }, description = "201 Registered Supplier with Interstate GST- Against Stock Transfer With Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithInterstateGstAgainstStockTransferWithDeviationAndExpensesAccountStockitemServiceitemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithInterstateGstAgainstStockTransferWithDeviationAndExpensesAccountStockitemServiceitemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 29, groups = {
			"GRN" }, description = "203 Registered Supplier with Interstate GST- Against Stock Transfer Without Deviation (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithInterstateGstAgainstStockTransferWithoutDeviationStockItemServiceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithInterstateGstAgainstStockTransferWithoutDeviationStockItemServiceItemAndChargeItem();
	}

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws Exception
	 */
	@Author("Prem-RMITS")
	@Test(priority = 30, groups = {
			"GRN" }, description = "204 Registered Supplier with Interstate GST- Against Stock Transfer Without Deviation and Expenses Account (Stock item ,service item And charge item )")
	public void VerifyToCreateRegisteredSupplierWithInterstateGstAgainstStockTransferWithoutDeviationAndExpensesAccountStockitemServiceitemAndchargeitem()
			throws Exception {
		purBillGetDoc
				.verifyToCreateRegisteredSupplierWithInterstateGstAgainstStockTransferWithoutDeviationStockItemServiceItemAndChargeItem();
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
