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

	/**
	 * This Methods Is Used To Create GRN Landed cost in bill booking from Get
	 * document
	 * 
	 * @author PremKumar.K-RMITS
	 * @throws IOException
	 */
	@Author("Prem-RMITS")
	@Test(priority = 1, groups = { "GRN" }, description = "148  GRN Landed cost in bill booking from Get document")
	public void verifyToCreateGrnLandedCostInBillBookingFromGetDocument() throws IOException {
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
	public void verifyToCreateGrnLandedCostInBillBookingFromGetDocumentWithTds() throws IOException {
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
	public void verifyToCreateGrnLandedCostInBillBookingFromGetDocumentWithTdsAndEsi() throws IOException {
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
	public void verifyToCreateGrnLandedCostInBillbookingFromGetDocumentWithTaxAddition() throws IOException {
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
	public void verifyToCreateGrnLandedCostInBillbookingFromGetDocumentWithNewChargesAddition() throws IOException {
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
	public void verifyToCreateGrnLandedCostInBillbookingFromGetDocumentWithValueAddition() throws IOException {
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
	public void verifyToCreateGrnLandedCostInBillbookingFromGetDocumentWithValueDeduction() throws IOException {
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
	public void verifyToCreateGrnLandedCostInBillbookingFromGetDocumentWithDeviation() throws IOException {
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
	public void verifyToCreateGrnLandedCostInBillBookingAgainstPending() throws IOException {
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
	public void verifyToCreateGrnLandedCostInBillBookingAgainstPendingWithTds() throws IOException {
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
	public void verifyToCreateGrnLandedCostInBillBookingAgainstPendingWithTdsAndEsi() throws IOException {
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
	public void verifyToCreateGrnLandedCostInBillBookingAgainstPendingTaxAddition() throws IOException {
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
	public void verifyToCreateGrnLandedCostInBillBookingAgainstPendingWithNewChargeItem() throws IOException {
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
	public void verifyToCreateGrnLandedCostInBillBookingAgainstPendingWithValueAddition() throws IOException {
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
	public void verifyToCreateGrnLandedCostInBillBookingAgainstPendingWithValueDeduction() throws IOException {
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
	public void verifyToCreateGrnLandedCostInBillbookingFromAgainstPendingWithNewChargesAddition() throws IOException {
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
	public void verifyToCreateRegisteredSupplierWithLocalGstAgainstStockTransferWithDeviationForStockitem()
			throws Exception {
		purBillGetDoc.VerifyToCreateRegisteredSupplierWithLocalGstAgainstStockTransferWithDeviationStockitem();
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
	public void verifyToCreateRegisteredSupplierWithLocalGstAgainstStockTransferWithDeviationAndExpenseAccountForStockitem()
			throws Exception {
		purBillGetDoc
				.VerifyToCreateRegisteredSupplierWithLocalGstAgainstStockTransferWithDeviationAndExpenseAccountForStockitem();
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
	public void verifyToCreateRegisteredSupplierWithLocalGstAgainstStockTransferWithOutDeviationForStockitem()
			throws Exception {
		purBillGetDoc.VerifyToCreateRegisteredSupplierWithLocalGstAgainstStockTransferWithOutDeviationStockitem();
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
	public void verifyToCreateRegisteredSupplierWithLocalGstAgainstStockTransferWithoutDeviationAndExpenseAccountForStockitem()
			throws Exception {
		purBillGetDoc
				.VerifyToCreateRegisteredSupplierWithLocalGstAgainstStockTransferWithoutDeviationAndExpenseAccountForStockitem();

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
	public void verifyToCreateRegisteredSupplierWithLocalGstAllowIgsttaxAgainstStockTransferWithDeviationStockitemserviceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.VerifyToCreateRegisteredSupplierWithLocalGstAllowIgstTaxAgainstStockTransferWithDeviationStockItemServiceItemAndChargeItemAutoCheck();
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
	public void verifyToCreateRegisteredSupplierWithLocalGstAllowIgsttaxAgainstStockTransferWithDeviationAndExpenseAccountStockitemserviceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.VerifyToCreateRegisteredSupplierWithLocalGstAllowIgsttaxAgainstStockTransferWithDeviationAndExpenseAccountStockitemserviceItemAndChargeItem();
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
	public void verifyToCreateRegisteredSupplierWithLocalGstAllowIgsttaxAgainstStockTransferWithOutDeviationStockitemserviceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.VerifyToCreateRegisteredSupplierWithLocalGstAllowIgsttaxAgainstStockTransferWithOutDeviationStockitemserviceItemAndChargeItem();
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
	public void verifyToCreateRegisteredSupplierWithLocalGstAllowIgsttaxAgainstStockTransferWithOutDeviationAndExpenseAccountStockitemserviceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.VerifyToCreateRegisteredSupplierWithLocalGstAllowIgsttaxAgainstStockTransferWithOutDeviationAndExpenseAccountStockitemserviceItemAndChargeItem();
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
	public void verifyToCreateRegisteredSupplierWithLocalGstAllowRcmtaxAgainstStockTransferWithDeviationStockItemserviceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.VerifyToCreateRegisteredSupplierWithLocalGstAllowRcmtaxAgainstStockTransferWithDeviationStockItemserviceItemAndChargeItem();
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
	public void verifyToCreateRegisteredSupplierWithInterStateGstAgainstStockTransferWithDeviationForStockitem()
			throws Exception {
		purBillGetDoc.VerifyToCreateRegisteredSupplierWithInterStateGstAgainstStockTransferWithDeviationForStockitem();
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
	public void verifyToCreateRegisteredSupplierWithInterstateGstAgainstStockTransferWithDeviationAndExpensesAccountStockitemServiceitemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.VerifyToCreateRegisteredSupplierWithInterstateGstAgainstStockTransferWithDeviationAndExpensesAccountStockitemServiceitemAndChargeItem();
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
	public void verifyToCreateRegisteredSupplierWithInterstateGstAgainstStockTransferWithoutDeviationStockItemServiceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.VerifyToCreateRegisteredSupplierWithInterstateGstAgainstStockTransferWithoutDeviationStockItemServiceItemAndChargeItem();
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
	public void verifyToCreateRegisteredSupplierWithInterstateGstAgainstStockTransferWithoutDeviationAndExpensesAccountStockitemServiceitemAndchargeitem()
			throws Exception {
		purBillGetDoc
				.VerifyToCreateRegisteredSupplierWithInterstateGstAgainstStockTransferWithoutDeviationStockItemServiceItemAndChargeItem();
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
	public void verifyToCreateRegisteredSupplierWithInterstateGstAllowRCMTaxAgainstStockTransferWithDeviationStockitemserviceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.VerifyToCreateRegisteredSupplierWithInterstateGstAllowRCMTaxAgainstStockTransferWithDeviationStockitemserviceItemAndChargeItem();
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
	public void verifyToCreateRegisteredSupplierWithInterstateGstAllowRcmTaxAgainstStockTransferWithDeviationAndExpensesAccountStockItemserviceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.VerifyToCreateRegisteredSupplierWithInterstateGstAllowRcmTaxAgainstStockTransferWithDeviationAndExpensesAccountStockItemserviceItemAndChargeItem();
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
	public void verifyToCreateRegisteredSupplierWithInterstateGstAllowRCMTaxAgainstStockTransferWithoutDeviationStockItemServiceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.VerifyToCreateRegisteredSupplierWithInterstateGstAllowRCMTaxAgainstStockTransferWithoutDeviationStockItemServiceItemAndChargeItem();
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
	public void verifyToCreateRegisteredSupplierWithInterstateGstAllowRcmTaxAgainstStockTransferWithoutDeviationAndExpensesAccountStockitemServiceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.VerifyToCreateRegisteredSupplierWithInterstateGstAllowRcmTaxAgainstStockTransferWithoutDeviationAndExpensesAccountStockitemServiceItemAndChargeItem();
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
	public void verifyToCreateRegisteredSupplierWithLocalGstAllowRcmTaxAgainstStockTransferWithDeviationandExpensesAccountStockItemServiceItemAndchargeItem()
			throws Exception {
		purBillGetDoc
				.VerifyToCreateRegisteredSupplierWithLocalGstAllowRcmTaxAgainstStockTransferWithDeviationandExpensesAccountStockItemServiceItemAndchargeItem();
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
	public void verifyToCreateRegisteredSupplierWithLocalGstAllowRcmTaxAgainstStockTransferWithoutDeviationStockitemServiceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.VerifyToCreateRegisteredSupplierWithLocalGstAllowRcmTaxAgainstStockTransferWithoutDeviationStockitemServiceItemAndChargeItem();
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
	public void verifyToCreateRegisteredSupplierWithLocalGstAllowRcmTaxAgainstStockTransferWithoutDeviationAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.VerifyToCreateRegisteredSupplierWithLocalGstAllowRcmTaxAgainstStockTransferWithoutDeviationAndExpensesAccountStockItemServiceItemAndChargeItem();
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
	public void verifyToCreateUnregisteredSupplierWithLocalGstAgainstStockTransferWithDeviationStockitemServiceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.VerifyToCreateUnregisteredSupplierWithLocalGstAgainstStockTransferWithDeviationStockitemServiceItemAndChargeItem();
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
	public void verifyToCreateUnregisteredSupplierWithLocalGstAgainstStockTransferWithDeviationAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.VerifyToCreateUnregisteredSupplierWithLocalGstAgainstStockTransferWithDeviationAndExpensesAccountStockItemServiceItemAndChargeItem();
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
	public void verifyToCreateUnregisteredSupplierWithLocalGstAgainstStockTransferWithoutDeviationStockItemServiceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.VerifyToCreateUnregisteredSupplierWithLocalGstAgainstStockTransferWithoutDeviationStockItemServiceItemAndChargeItem();
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
	public void verifyToCreateUnregisteredSupplierWithLocalGstAgainstStockTransferWithoutDeviationAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.VerifyToCreateUnregisteredSupplierWithLocalGstAgainstStockTransferWithoutDeviationAndExpensesAccountStockItemServiceItemAndChargeItem();
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
	public void verifyToCreateUnregisteredSupplierWithLocalGSTAllowIgstTaxAgainstStockTransferWithDeviationStockItemServiceItemAndChargeitem()
			throws Exception {
		purBillGetDoc
				.VerifyToCreateUnregisteredSupplierWithLocalGSTAllowIgstTaxAgainstStockTransferWithDeviationStockItemServiceItemAndChargeitem();
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
	public void verifyToUnregisteredSupplierWithLocalGstAllowIgstTaxAgainstStockTransferWithDeviationAndExpensesAccountStockItemServiceItemAndChargeItem()
			throws Exception {
		purBillGetDoc
				.VerifyToUnregisteredSupplierWithLocalGstAllowIgstTaxAgainstStockTransferWithDeviationAndExpensesAccountStockItemServiceItemAndChargeItem();
	}


	/**
	 * This Method Is Used To Logout After Method
	 * 
	 * @author Deepan-GWL
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result, Method method) {
		commonFunc.verifyAfterMethod();
		commonFunc.enterAuthorNamesInExtentReport(method);

	}

}
