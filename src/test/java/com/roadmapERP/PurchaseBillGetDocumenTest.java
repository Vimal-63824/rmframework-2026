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
		if (VariableHandler.getSessionID() == null) {
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
