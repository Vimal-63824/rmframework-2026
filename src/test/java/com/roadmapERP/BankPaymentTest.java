package com.roadmapERP;

/**
 * @author Harikrishna - RMITS
 * @company RoadMap IT Solutions October 16, 2025
 */
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.financials.BankPaymentFunctions;
import commonfunctions.supplychainmanagement.ShareOfBusinessFunctions;
import commonfunctions.supplychainmanagement.StockAdjustmentFunctions;
import utils.Mailer;
import utils.ReportLoger;
import utils.WebDriverBase;

public class BankPaymentTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static WebDriverBase webDB = new WebDriverBase();
	static BankPaymentFunctions bankPaymentFunc = new BankPaymentFunctions();
	private static ReportLoger logs = new ReportLoger();
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
	 * Payment Against single Party, Single Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 16, groups = {
			"Bank Payments" }, description = " Payment Against single Party, Single Bill (Through Add button)")
	public void CreateBankPaymentThroughAddButton() throws IOException {
		bankPaymentFunc
				.CreateBankPaymentThroughLineAddButtonSingleBills(webDB.getDataFromProperties("BankPaymentSingle"));
//	Assert.assertTrue(flag);
	}

	/**
	 * Payment Against single Party, Single Bill (Get document )
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 17, groups = {
			"Bank Payments" }, description = " Payment Against single Party, Single Bill (Get document )")
	public void CreateBankPaymentThroughGetDocument() throws IOException {
		flag = bankPaymentFunc
				.CreateBankPaymentThroughGetDocumentSingleBills(webDB.getDataFromProperties("BankPaymentSingle"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against single Party, Single Bill (Pending )
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 18, groups = {
			"Bank Payments" }, description = " Payment Against single Party, Single Bill (Pending )")
	public void CreateBankPaymentThroughPendingDocs() throws IOException {
		flag = bankPaymentFunc
				.CreateBankPaymentThroughPendingDocsSingleBills(webDB.getDataFromProperties("BankPaymentSingle"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against single Party, Multi Bill (Dr/Cr) (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 19, groups = {
			"Bank Payments" }, description = " Payment Against single Party, Multi Bill (Dr/Cr) (Through Add button)")
	public void CreateBankPaymentSinglePartyMultiBillsThroughAddButton() throws IOException {
		flag = bankPaymentFunc.CreateBankPaymentThroughLineAddButtonMultiBills(webDB.getDataFromProperties("BPSPMB"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against single Party, Multi Bill (Dr/Cr) (Through Get Document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 20, groups = {
			"Bank Payments" }, description = " Payment Against single Party, Multi Bill (Dr/Cr) (Through Get Document)")
	public void CreateBankPaymentSinglePartyMultiBillsThroughGetDocument() throws IOException {
		flag = bankPaymentFunc.CreateBankPaymentThroughGetDocumentMultiBills(webDB.getDataFromProperties("BPSPMB"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against single Party, Multi Bill (Dr/Cr) (Through Pending)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 21, groups = {
			"Bank Payments" }, description = " Payment Against single Party, Multi Bill (Dr/Cr) (Through Pending)")
	public void CreateBankPaymentSinglePartyMultiBillsThroughPending() throws IOException {
		flag = bankPaymentFunc.CreateBankPaymentThroughPendingDocsMultiBills(webDB.getDataFromProperties("BPSPMB"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against Multi Party, Single Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 22, groups = {
			"Bank Payments" }, description = " Payment Against Multi Party, Single Bill (Through Add button)")
	public void CreateBankPaymentThroughAddButtonMultiPartySingleBill() throws IOException {
		flag = bankPaymentFunc.CreateBankPaymentThroughLineAddButtonMultiPartySingleBill(
				webDB.getDataFromProperties("BankPaymentINRMPSB"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against Multi Party, Single Bill (Get document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 23, groups = {
			"Bank Payments" }, description = "Payment Against Multi Party, Single Bill (Get document)")
	public void CreateBankPaymentThroughGetDocumentMultiPartySingleBill() throws IOException {
		flag = bankPaymentFunc.CreateBankPaymentThroughGetDocumentMultiPartySingleBill(
				webDB.getDataFromProperties("BankPaymentINRMPSB"));
		Assert.assertTrue(flag);
	}

//	/**
//	 * Payment Against Multi Party, Single Bill (Pending)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws IOException
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 24, groups = {
//			"Bank Payments" }, description = " Payment Against Multi Party, Single Bill (Pending)")
//	public void CreateBankPaymentThroughPendingDocsMultiPartySingleBill() throws IOException {
//		flag = bankPaymentFunc.CreateBankPaymentThroughPendingDocumentMultiPartySingleBill(
//				webDB.getDataFromProperties("BankPaymentINRMPSB"));
//		Assert.assertTrue(flag);
//	}

	/**
	 * Payment Against Multi Party, Single Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 25, groups = {
			"Bank Payments" }, description = " Payment Against Multi Party, Single Bill (Through Add button)")
	public void CreateBankPaymentThroughAddButtonMultiPartyMultiBill() throws IOException {
		flag = bankPaymentFunc.CreateBankPaymentThroughLineAddButtonMultiPartyMultiBill(
				webDB.getDataFromProperties("BankPaymentINRMPMB"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against Multi Party, Single Bill (Get document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 26, groups = {
			"Bank Payments" }, description = "Payment Against Multi Party, Single Bill (Get document)")
	public void CreateBankPaymentThroughGetDocumentMultiPartyMultiBill() throws IOException {
		flag = bankPaymentFunc.CreateBankPaymentThroughGetDocumentMultiPartyMultiBill(
				webDB.getDataFromProperties("BankPaymentINRMPMB"));
		Assert.assertTrue(flag);
	}

//	/**
//	 * Payment Against Multi Party, Single Bill (Pending)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws IOException
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 27, groups = {
//			"Bank Payments" }, description = " Payment Against Multi Party, Single Bill (Pending)")
//	public void CreateBankPaymentThroughPendingDocsMultiPartyMultiBill() throws IOException {
//		flag = bankPaymentFunc
//				.CreateBankPaymentThroughPendingMultiPartyMultiBill(webDB.getDataFromProperties("BankPaymentINRMPMB"));
//		Assert.assertTrue(flag);
//	}

	/**
	 * Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through
	 * Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 28, groups = {
			"Bank Payments" }, description = "Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through Add button)")
	public void CreateBankPaymentThroughGetDocumentMultiPartyMultiBillWithNewRef() throws IOException {
		flag = bankPaymentFunc.CreateBankPaymentThroughGetDocumentMultiPartyMultiBillWithNewRef(
				webDB.getDataFromProperties("BankPaymentINRMPMBNR"));
		Assert.assertTrue(flag);
	}

//	/**
//	 * Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through
//	 * Add button)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws IOException
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 29, groups = {
//			"Bank Payments" }, description = "Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through Add button)")
//	public void CreateBankPaymentThroughPendingMultiPartyMultiBillWithNewRef() throws IOException {
//		flag = bankPaymentFunc.CreateBankPaymentThroughPendingDocumentMultiPartyMultiBill(
//				webDB.getDataFromProperties("BankPaymentINRMPMBNR"));
//		Assert.assertTrue(flag);
//	}

	/**
	 * Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through
	 * Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 30, groups = {
			"Bank Payments" }, description = "Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through Add button)")
	public void CreateBankPaymentThroughAddButtonMultiPartyMultiBillWithNewRef() throws IOException {
		flag = bankPaymentFunc.CreateBankPaymentThroughAddButtonMultiPartyMultiBillWithNewRef(
				webDB.getDataFromProperties("BankPaymentINRMPMBNR"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against single Party, Single Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 1, groups = {
			"Bank Payments" }, description = " Payment Against single Party, Single Bill - Payment - Bank, Supplier and Base Currency: Same (USD) (Through Add button)")
	public void CreateBankPaymentThroughAddButtonUSD() throws IOException {
		flag = bankPaymentFunc
				.CreateBankPaymentThroughLineAddButtonSingleBillsUSD(webDB.getDataFromProperties("BankPaymentUSD"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against single Party, Single Bill (Get document )
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 2, groups = {
			"Bank Payments" }, description = " Payment Against single Party, Single Bill - Payment - Bank, Supplier and Base Currency: Same (USD) (Get document )")
	public void CreateBankPaymentThroughGetDocumentUSD() throws IOException {
		flag = bankPaymentFunc
				.CreateBankPaymentThroughGetDocumentSingleBillsUSD(webDB.getDataFromProperties("BankPaymentUSD"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against single Party, Single Bill (Pending )
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 3, groups = {
			"Bank Payments" }, description = " Payment Against single Party, Single Bill - Payment - Bank, Supplier and Base Currency: Same (USD) (Pending )")
	public void CreateBankPaymentThroughPendingDocsUSD() throws IOException {
		flag = bankPaymentFunc
				.CreateBankPaymentThroughPendingDocsSingleBillsUSD(webDB.getDataFromProperties("BankPaymentUSD"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against single Party, Multi Bill (Dr/Cr) (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 4, groups = {
			"Bank Payments" }, description = " Payment Against single Party, Multi Bill (Dr/Cr) - Payment - Bank, Supplier and Base Currency: Same (USD) (Through Add button)")
	public void CreateBankPaymentSinglePartyMultiBillsThroughAddButtonUSD() throws IOException {
		flag = bankPaymentFunc
				.CreateBankPaymentThroughLineAddButtonMultiBillsUSD(webDB.getDataFromProperties("BankPaymentUSDMulti"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against single Party, Multi Bill (Dr/Cr) (Through Get Document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 5, groups = {
			"Bank Payments" }, description = " Payment Against single Party, Multi Bill (Dr/Cr) - Payment - Bank, Supplier and Base Currency: Same (USD)(Through Get Document)")
	public void CreateBankPaymentSinglePartyMultiBillsThroughGetDocumentUSD() throws IOException {
		flag = bankPaymentFunc
				.CreateBankPaymentThroughGetDocumentMultiBillsUSD(webDB.getDataFromProperties("BankPaymentUSDMulti"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against single Party, Multi Bill (Dr/Cr) (Through Pending)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 6, groups = {
			"Bank Payments" }, description = " Payment Against single Party, Multi Bill (Dr/Cr) - Payment - Bank, Supplier and Base Currency: Same (USD) (Through Pending)")
	public void CreateBankPaymentSinglePartyMultiBillsThroughPendingUSD() throws IOException {
		flag = bankPaymentFunc
				.CreateBankPaymentThroughPendingDocsMultiBillsUSD(webDB.getDataFromProperties("BankPaymentUSDMulti"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against Multi Party, Single Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 7, groups = {
			"Bank Payments" }, description = " Payment Against Multi Party, Single Bill - Payment - Bank, Supplier and Base Currency: Same (USD) (Through Add button)")
	public void CreateBankPaymentThroughAddButtonMultiPartySingleBillUSD() throws IOException {
		flag = bankPaymentFunc.CreateBankPaymentThroughLineAddButtonMultiPartySingleBillUSD(
				webDB.getDataFromProperties("BankPaymentUSDMPSB"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against Multi Party, Single Bill (Get document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 8, groups = {
			"Bank Payments" }, description = "Payment Against Multi Party, Single Bill - Payment - Bank, Supplier and Base Currency: Same (USD) (Get document)")
	public void CreateBankPaymentThroughGetDocumentMultiPartySingleBillUSD() throws IOException {
		flag = bankPaymentFunc.CreateBankPaymentThroughGetDocumentMultiPartySingleBillUSD(
				webDB.getDataFromProperties("BankPaymentUSDMPSB"));
		Assert.assertTrue(flag);
	}

//	/**
//	 * Payment Against Multi Party, Single Bill (Pending)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws IOException
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 9, groups = {
//			"Bank Payments" }, description = " Payment Against Multi Party, Single Bill - Payment - Bank, Supplier and Base Currency: Same (USD) (Pending)")
//	public void CreateBankPaymentThroughPendingDocsMultiPartySingleBillUSD() throws IOException {
//		flag = bankPaymentFunc.CreateBankPaymentThroughPendingDocumentMultiPartySingleBill(
//				webDB.getDataFromProperties("BankPaymentUSDMPSB"));
//		Assert.assertTrue(flag);
//	}

	/**
	 * Payment Against Multi Party, Single Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 10, groups = {
			"Bank Payments" }, description = " Payment Against Multi Party, Multi Bill - Payment - Bank, Supplier and Base Currency: Same (USD)(Through Add button)")
	public void CreateBankPaymentThroughAddButtonMultiPartyMultiBillUSD() throws IOException {
		flag = bankPaymentFunc.CreateBankPaymentThroughLineAddButtonMultiPartyMultiBillUSD(
				webDB.getDataFromProperties("BankPaymentUSDMPMB"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against Multi Party, Single Bill (Get document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 11, groups = {
			"Bank Payments" }, description = "Payment Against Multi Party, Multi Bill - Payment - Bank, Supplier and Base Currency: Same (USD) (Get document)")
	public void CreateBankPaymentThroughGetDocumentMultiPartyMultiBillUSD() throws IOException {
		flag = bankPaymentFunc.CreateBankPaymentThroughGetDocumentMultiPartyMultiBillUSD(
				webDB.getDataFromProperties("BankPaymentUSDMPMB"));
		Assert.assertTrue(flag);
	}

//	/**
//	 * Payment Against Multi Party, Single Bill (Pending)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws IOException
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 12, groups = {
//			"Bank Payments" }, description = " Payment Against Multi Party, Multi Bill - Payment - Bank, Supplier and Base Currency: Same (USD)(Pending)")
//	public void CreateBankPaymentThroughPendingDocsMultiPartyMultiBillUSD() throws IOException {
//		flag = bankPaymentFunc.CreateBankPaymentThroughPendingMultiPartyMultiBillUSD(
//				webDB.getDataFromProperties("BankPaymentUSDMPMB"));
//		Assert.assertTrue(flag);
//	}

	/**
	 * Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through
	 * Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 13, groups = {
			"Bank Payments" }, description = "Payment Against Multi Party, Multi Bill with New reference (Dr/Cr) - Payment - Bank, Supplier and Base Currency: Same (USD)(Through Get Document)")
	public void CreateBankPaymentThroughGetDocumentMultiPartyMultiBillWithNewRefUSD() throws IOException {
		flag = bankPaymentFunc.CreateBankPaymentThroughGetDocumentMultiPartyMultiBillWithNewRefUSD(
				webDB.getDataFromProperties("BankPaymentUSDMPMBNR"));
		Assert.assertTrue(flag);
	}

//	/**
//	 * Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through
//	 * Add button)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws IOException
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 14, groups = {
//			"Bank Payments" }, description = "Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through Pending)")
//	public void CreateBankPaymentThroughPendingMultiPartyMultiBillWithNewRefUSD() throws IOException {
//		flag = bankPaymentFunc.CreateBankPaymentThroughPendingDocumentMultiPartyMultiBillUSD(
//				webDB.getDataFromProperties("BankPaymentUSDMPMBNR"));
//		Assert.assertTrue(flag);
//	}

	/**
	 * Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through
	 * Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 15, groups = {
			"Bank Payments" }, description = "Payment Against Multi Party, Multi Bill with New reference - Payment - Bank, Supplier and Base Currency: Same (USD) (Dr/Cr)(Through Add button)")
	public void CreateBankPaymentThroughAddButtonMultiPartyMultiBillWithNewRefUSD() throws IOException {
		flag = bankPaymentFunc.CreateBankPaymentThroughAddButtonMultiPartyMultiBillWithNewRefUSD(
				webDB.getDataFromProperties("BankPaymentUSDMPMBNR"));
		Assert.assertTrue(flag);
	}

	/**
	 * Bank Payment With Post Dated Cheque
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 31, groups = { "Bank Payments" }, description = "Bank Payment With Post Dated Cheque")
	public void CreateBankPaymentWithPostDatedCheque() throws IOException {
		bankPaymentFunc.bankPaymentWithPostDatedCheque(webDB.getDataFromProperties("BankPaymentPDC"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"));
	}

	/**
	 * Payment With Expenses Account without Gst
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 32, groups = { "Bank Payments" }, description = "Payment With Expenses Account without Gst")
	public void CreateBankPaymentWithExpenseAndWithoutGST() throws IOException {
		bankPaymentFunc.BankPaymentWithExpAndRevenueWithoutGST(webDB.getDataFromProperties("BankPaymentExp"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"), commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * Payment With Expenses Account with Gst
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 33, groups = { "Bank Payments" }, description = "Payment With Expenses Account with Gst")
	public void CreateBankPaymentWithExpenseAndWithGST() throws IOException {
		bankPaymentFunc.BankPaymentWithExpAndRevenueWithGST(webDB.getDataFromProperties("BankPaymentExpGST"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"));
	}

	/**
	 * Payment With Expenses Account without Gst
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 34, groups = { "Bank Payments" }, description = "Payment With Expenses Account without Gst - USD")
	public void CreateBankPaymentWithExpenseAndWithoutGSTUSD() throws IOException {
		bankPaymentFunc.BankPaymentWithExpAndRevenueWithoutGST(webDB.getDataFromProperties("BankPaymentExpUSD"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * Bank Payment With Charges
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 35, groups = { "Bank Receipt" }, description = "Bank receipt With Charges")
	public void CreateBankPaymentWithCharges() throws IOException {
		bankPaymentFunc.BankPaymentWithCharges(webDB.getDataFromProperties("BankPaymentCharges"));
	}

	/**
	 * Bank Payment With Charges
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 36, groups = { "Bank Receipt" }, description = "Bank receipt With Charges")
	public void CreateBankPaymentWithChargesUSD() throws IOException {
		bankPaymentFunc.BankPaymentWithChargesUSD(webDB.getDataFromProperties("BankPaymentChargesUSD"));
	}

	/**
	 * Payment With Expenses Account with Gst
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 37, groups = { "Bank Payments" }, description = "Payment With Expenses Account with Gst And TDS")
	public void CreateBankPaymentWithExpenseAndWithGSTAndTDS() throws IOException {
		bankPaymentFunc.BankPaymentWithExpAndRevenueWithGSTAndTDS(webDB.getDataFromProperties("BankPaymentExpGSTTDS"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"));
	}

	/**
	 * Payment With Expenses Account without Gst And TDS
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 38, groups = {
			"Bank Payments" }, description = "Payment With Expenses Account without Gst And TDS")
	public void CreateBankPaymentWithExpenseAndWithoutGSTAndTDS() throws IOException {
		// TODO Covered in CreateBankPaymentWithExpenseAndWithoutGST() Method
		logs.logging("INFO",
				"This Scenario Is Already Covered In This Method CreateBankPaymentWithExpenseAndWithoutGST() ");
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against single Party, Single Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 39, groups = {
			"Bank Payments" }, description = " Payment Against single Party, Single Bill (Through Add button)")
	public void CreateBankPaymentThroughAddButtonBankCurcyUSD() throws IOException {
		flag = bankPaymentFunc.CreateBankPaymentThroughLineAddButtonSingleBillsBankCurcyUSD(
				webDB.getDataFromProperties("BankPaymentSingle"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against single Party, Single Bill (Get document )
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 40, groups = {
			"Bank Payments" }, description = " Payment Against single Party, Single Bill (Get document )")
	public void CreateBankPaymentThroughGetDocumentBankCurcyUSD() throws IOException {
		flag = bankPaymentFunc.CreateBankPaymentThroughGetDocumentSingleBillsBankCurcyUSD(
				webDB.getDataFromProperties("BankPaymentSingle"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against single Party, Single Bill (Pending )
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 41, groups = {
			"Bank Payments" }, description = " Payment Against single Party, Single Bill (Pending )")
	public void CreateBankPaymentThroughPendingDocsBankCurcyUSD() throws IOException {
		flag = bankPaymentFunc.CreateBankPaymentThroughPendingDocsSingleBillsBankCurcyUSD(
				webDB.getDataFromProperties("BankPaymentSingle"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against single Party, Multi Bill (Dr/Cr) (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 42, groups = {
			"Bank Payments" }, description = " Payment Against single Party, Multi Bill (Dr/Cr) (Through Add button)")
	public void CreateBankPaymentSinglePartyMultiBillsThroughAddButtonBankCurcyUSD() throws IOException {
		flag = bankPaymentFunc
				.CreateBankPaymentThroughLineAddButtonMultiBillsBankCurcyUSD(webDB.getDataFromProperties("BPSPMB"));
		Assert.assertTrue(flag);
	}

	/**
	 * // * Payment Against single Party, Multi Bill (Dr/Cr) (Through Get Document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 43, groups = {
			"Bank Payments" }, description = " Payment Against single Party, Multi Bill (Dr/Cr) (Through Get Document)")
	public void CreateBankPaymentSinglePartyMultiBillsThroughGetDocumentBankCurcyUSD() throws IOException {
		flag = bankPaymentFunc
				.CreateBankPaymentThroughGetDocumentMultiBillsBankCurcyUSD(webDB.getDataFromProperties("BPSPMB"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against single Party, Multi Bill (Dr/Cr) (Through Pending)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 44, groups = {
			"Bank Payments" }, description = " Payment Against single Party, Multi Bill (Dr/Cr) (Through Pending)")
	public void CreateBankPaymentSinglePartyMultiBillsThroughPendingBankCurcyUSD() throws IOException {
		flag = bankPaymentFunc
				.CreateBankPaymentThroughPendingDocsMultiBillsBankCurcyUSD(webDB.getDataFromProperties("BPSPMB"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against Multi Party, Single Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 45, groups = {
			"Bank Payments" }, description = " Payment Against Multi Party, Single Bill (Through Add button)")
	public void CreateBankPaymentThroughAddButtonMultiPartySingleBillBankCurcyUSD() throws IOException {
		flag = bankPaymentFunc.CreateBankPaymentThroughLineAddButtonMultiPartySingleBillBankCurcyUSD(
				webDB.getDataFromProperties("BankPaymentINRMPSB"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against Multi Party, Single Bill (Get document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 46, groups = {
			"Bank Payments" }, description = "Payment Against Multi Party, Single Bill (Get document)")
	public void CreateBankPaymentThroughGetDocumentMultiPartySingleBillBankCurcyUSD() throws IOException {
		flag = bankPaymentFunc.CreateBankPaymentThroughGetDocumentMultiPartySingleBillBankCurcyUSD(
				webDB.getDataFromProperties("BankPaymentINRMPSB"));
		Assert.assertTrue(flag);
	}

//	/**
//	 * Payment Against Multi Party, Single Bill (Pending)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws IOException
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 47, groups = {
//			"Bank Payments" }, description = " Payment Against Multi Party, Single Bill (Pending)")
//	public void CreateBankPaymentThroughPendingDocsMultiPartySingleBillBankCurcyUSD() throws IOException {
//		flag = bankPaymentFunc.CreateBankPaymentThroughPendingDocumentMultiPartySingleBillBankCurcyUSD(
//				webDB.getDataFromProperties("BankPaymentINRMPSB"));
//		Assert.assertTrue(flag);
//	}

	/**
	 * Payment Against Multi Party, Single Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 48, groups = {
			"Bank Payments" }, description = " Payment Against Multi Party, Single Bill (Through Add button)")
	public void CreateBankPaymentThroughAddButtonMultiPartyMultiBillBankCurcyUSD() throws IOException {
		flag = bankPaymentFunc.CreateBankPaymentThroughLineAddButtonMultiPartyMultiBillBankCurcyUSD(
				webDB.getDataFromProperties("BankPaymentINRMPMB"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against Multi Party, Single Bill (Get document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 49, groups = {
			"Bank Payments" }, description = "Payment Against Multi Party, Single Bill (Get document)")
	public void CreateBankPaymentThroughGetDocumentMultiPartyMultiBillBankCurcyUSD() throws IOException {
		flag = bankPaymentFunc.CreateBankPaymentThroughGetDocumentMultiPartyMultiBillBankCurcyUSD(
				webDB.getDataFromProperties("BankPaymentINRMPMB"));
		Assert.assertTrue(flag);
	}

//	/**
//	 * Payment Against Multi Party, Single Bill (Pending)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws IOException
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 50, groups = {
//			"Bank Payments" }, description = " Payment Against Multi Party, Single Bill (Pending)")
//	public void CreateBankPaymentThroughPendingDocsMultiPartyMultiBillBankCurcyUSD() throws IOException {
//		flag = bankPaymentFunc.CreateBankPaymentThroughPendingMultiPartyMultiBillBankCurcyUSD(
//				webDB.getDataFromProperties("BankPaymentINRMPMB"));
//		Assert.assertTrue(flag);
//	}

	/**
	 * Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through
	 * Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 51, groups = {
			"Bank Payments" }, description = "Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through Add button)")
	public void CreateBankPaymentThroughAddButtonMultiPartyMultiBillWithNewRefBankCurcyUSD() throws IOException {
		flag = bankPaymentFunc.CreateBankPaymentThroughAddButtonMultiPartyMultiBillWithNewRefBankCurcyUSD(
				webDB.getDataFromProperties("BankPaymentINRMPMBNR"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through
	 * Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 52, groups = {
			"Bank Payments" }, description = "Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through Add button)")
	public void CreateBankPaymentThroughGetDocumentMultiPartyMultiBillWithNewRefBankCurcyUSD() throws IOException {
		flag = bankPaymentFunc.CreateBankPaymentThroughGetDocumentMultiPartyMultiBillWithNewRefBankCurcyUSD(
				webDB.getDataFromProperties("BankPaymentINRMPMBNR"));
		Assert.assertTrue(flag);
	}

//	/**
//	 * Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through
//	 * Add button)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws IOException
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 53, groups = {
//			"Bank Payments" }, description = "Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through Add button)")
//	public void CreateBankPaymentThroughPendingMultiPartyMultiBillWithNewRefBankCurcyUSD() throws IOException {
//		flag = bankPaymentFunc.CreateBankPaymentThroughPendingDocumentMultiPartyMultiBillBankCurcyUSD(
//				webDB.getDataFromProperties("BankPaymentINRMPMBNR"));
//		Assert.assertTrue(flag);
//	}

	/**
	 * Bank Payment With Charges
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 54, groups = { "Bank Receipt" }, description = "Bank Receipt With Charges")
	public void CreateBankPaymentWithChargesBankCurcyUSD() throws IOException {
		bankPaymentFunc.BankPaymentWithChargesBankCurcyUSD(webDB.getDataFromProperties("BankPaymentChargesINR"));
	}

	/**
	 * Payment With Expenses Account without Gst
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 55, groups = { "Bank Payments" }, description = "Payment With Expenses Account without Gst")
	public void CreateBankPaymentWithExpenseAndWithoutGSTBankCurcyUSD() throws IOException {
		bankPaymentFunc.BankPaymentWithExpAndRevenueWithoutGST(webDB.getDataFromProperties("BankPaymentExp"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
	}

	/**
	 * Advance against New reference (without TDS)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 56, groups = { "Bank Payments" }, description = "Advance against New reference (without TDS)")
	public void CreateBankPaymentAdvWithNewRefWithoutTDS() throws IOException {
		bankPaymentFunc.BPAdvNewRefWithAndWithoutTDS(webDB.getDataFromProperties("CPAdvanceWithNewRef"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"), commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * Advance against New reference (with TDS Single under section)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 57, groups = {
			"Bank Payments" }, description = "Advance against New reference (with TDS Single under section)")
	public void CreateBankPaymentAdvWithNewRefWithTDS() throws IOException {
		bankPaymentFunc.BPAdvNewRefWithAndWithoutTDS(webDB.getDataFromProperties("TDSCPAdvanceWithNewRef"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"), commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * Advance against New reference (with TDS Single under section)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 58, groups = {
			"Bank Payments" }, description = "Advance against New reference (with TDS Single under section)")
	public void CreateBankPaymentAdvWithNewRefWithMultiTDS() throws IOException {
		bankPaymentFunc.BPAdvNewRefWithMultiTDS(webDB.getDataFromProperties("TDSCPAdvanceMultiNewRef"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"), commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * Advance against New reference (without TDS)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 59, groups = { "Bank Payments" }, description = "Advance against New reference (without TDS)")
	public void CreateBankPaymentAdvWithNewRefUSD() throws IOException {
		bankPaymentFunc.BPAdvWithNewRef(webDB.getDataFromProperties("BankPaymentAdvUSD"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * Advance against New reference (without TDS)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 60, groups = { "Bank Payments" }, description = "Advance against New reference (without TDS)")
	public void CreateBankPaymentAdvWithNewRefBankCurcyUSD() throws IOException {
		bankPaymentFunc.BPAdvWithNewRef(webDB.getDataFromProperties("BankPaymentAdv"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
	}

	/**
	 * Advance against PO single purchase order (without TDS)(Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Test(priority = 61, groups = {
			"Bank Payments" }, description = " Advance against PO single purchase order (without TDS)(Through Add button)")
	public void CreateBankPaymentAdvPOThroughAddButton() throws IOException {
		bankPaymentFunc.BPadvancePOThroughAddButton(webDB.getDataFromProperties("BPAdvance"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Advance against PO single purchase order (without TDS) (Get document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Test(priority = 62, groups = {
			"Bank Payments" }, description = " Advance against PO single purchase order (without TDS) (Get document)")
	public void CreateBankPaymentAdvPOThroughGetDocument() throws IOException {
		bankPaymentFunc.BPadvancePOThroughGetDocument(webDB.getDataFromProperties("BPAdvance"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Advance against PO single purchase order (without TDS) (Pending)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Test(priority = 63, groups = {
			"Bank Payments" }, description = " Advance against PO single purchase order (without TDS) (Pending)")
	public void CreateBankPaymentAdvPOThroughPendingDocument() throws IOException {
		bankPaymentFunc.BPadvancePOThroughPendingDocument(webDB.getDataFromProperties("BPAdvance"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Advance against PO Multiple purchase order (without TDS)(Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Test(priority = 64, groups = {
			"Bank Payments" }, description = " Advance against PO Multiple purchase order (without TDS)(Through Add button)")
	public void CreateBankPaymentMultipleAdvPOThroughAddButton() throws IOException {
		bankPaymentFunc.BPadvancePOMultipleThroughAddButton(webDB.getDataFromProperties("BPAdvance"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Advance against PO Multiple purchase order (without TDS) (Get document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Test(priority = 65, groups = {
			"Bank Payments" }, description = " Advance against PO Multiple purchase order (without TDS) (Get document)")
	public void CreateBankPaymentMultipleAdvPOThroughGetDocument() throws IOException {
		bankPaymentFunc.BPadvancePOMultipleThroughGetDocument(webDB.getDataFromProperties("BPAdvance"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Advance against PO Multiple purchase order (without TDS) (Pending)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Test(priority = 66, groups = {
			"Bank Payments" }, description = "Advance against PO Multiple purchase order (without TDS) (Pending)")
	public void CreateBankPaymentMultipleAdvPOThroughPendingDocument() throws IOException {
		bankPaymentFunc.BPadvancePOMultipleThroughPendingDocument(webDB.getDataFromProperties("BPAdvance"));
//		Assert.assertTrue(flag);
	}

//
	/**
	 * Advance against PO single purchase order (With TDS single Under
	 * section)(Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Test(priority = 67, groups = {
			"Bank Payments" }, description = " Advance against PO single purchase order (With TDS single Under section)(Through Add button)")
	public void CreateBankPaymentAdvPOThroughAddButtonWithSingleTDS() throws IOException {
		bankPaymentFunc.BPadvancePOThroughAddButton(webDB.getDataFromProperties("TDSBPAdvance"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Advance against PO single purchase order (With TDS single Under section) (Get
	 * document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Test(priority = 68, groups = {
			"Bank Payments" }, description = " Advance against PO single purchase order (With TDS single Under section) (Get document)")
	public void CreateBankPaymentAdvPOThroughGetDocumentWithSingleTDS() throws IOException {
		bankPaymentFunc.BPadvancePOThroughGetDocument(webDB.getDataFromProperties("TDSBPAdvance"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Advance against PO single purchase order (With TDS single Under section)
	 * (Pending)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Test(priority = 69, groups = {
			"Bank Payments" }, description = " Advance against PO single purchase order (With TDS single Under section) (Pending))")
	public void CreateBankPaymentAdvPOThroughPendingDocumentWithSingleTDS() throws IOException {
		bankPaymentFunc.BPadvancePOThroughPendingDocument(webDB.getDataFromProperties("TDSBPAdvance"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Advance against PO Multiple purchase order (With TDS multiple Under
	 * section)(Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Test(priority = 70, groups = {
			"Bank Payments" }, description = " Advance against PO Multiple purchase order (With TDS multiple Under section)(Through Add button)")
	public void CreateBankPaymentMultipleAdvPOThroughAddButtonWithMultiTDS() throws IOException {
		bankPaymentFunc
				.BPadvancePOMultipleThroughAddButtonWithMultipleTDS(webDB.getDataFromProperties("TDSBPAdvanceMulti"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Advance against PO Multiple purchase order (With TDS multiple Under section)
	 * (Get document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Test(priority = 71, groups = {
			"Bank Payments" }, description = " Advance against PO Multiple purchase order (With TDS multiple Under section) (Get document)")
	public void CreateBankPaymentMultipleAdvPOThroughGetDocumentWithMultiTDS() throws IOException {
		bankPaymentFunc
				.BPadvancePOMultipleThroughGetDocumentWithMultipleTDS(webDB.getDataFromProperties("TDSBPAdvanceMulti"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Advance against PO Multiple purchase order (With TDS multiple Under section)
	 * (Pending)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Test(priority = 72, groups = {
			"Cash Payments" }, description = " Advance against PO Multiple purchase order (With TDS multiple Under section) (Pending)")
	public void CreateCashPaymentMultipleAdvPOThroughPendingDocumentWithMultiTDS() throws IOException {
		bankPaymentFunc.BPadvancePOMultipleThroughPendingDocumentWithMultipleTDS(
				webDB.getDataFromProperties("TDSBPAdvanceMulti"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Advance against PO single purchase order (without TDS)(Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Test(priority = 73, groups = {
			"Bank Payments" }, description = " Advance against PO single purchase order (without TDS)(Through Add button)")
	public void CreateBankPaymentAdvPOThroughAddButtonBankCurcyUSD() throws IOException {
		bankPaymentFunc.BPadvancePOThroughAddButtonBankCurcyUSD(webDB.getDataFromProperties("BPAdvance"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Advance against PO single purchase order (without TDS) (Get document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Test(priority = 74, groups = {
			"Bank Payments" }, description = " Advance against PO single purchase order (without TDS) (Get document)")
	public void CreateBankPaymentAdvPOThroughGetDocumentBankCurcyUSD() throws IOException {
		bankPaymentFunc.BPadvancePOThroughGetDocumentBankCurcyUSD(webDB.getDataFromProperties("BPAdvance"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Advance against PO single purchase order (without TDS) (Pending)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Test(priority = 75, groups = {
			"Bank Payments" }, description = " Advance against PO single purchase order (without TDS) (Pending)")
	public void CreateBankPaymentAdvPOThroughPendingDocumentBankCurcyUSD() throws IOException {
		bankPaymentFunc.BPadvancePOThroughPendingDocumentBankCurcyUSD(webDB.getDataFromProperties("BPAdvance"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Advance against PO Multiple purchase order (without TDS)(Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Test(priority = 76, groups = {
			"Bank Payments" }, description = " Advance against PO Multiple purchase order (without TDS)(Through Add button)")
	public void CreateBankPaymentMultipleAdvPOThroughAddButtonBankCurcyUSD() throws IOException {
		bankPaymentFunc.BPadvancePOMultipleThroughAddButtonBankCurcyUSD(webDB.getDataFromProperties("BPAdvance"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Advance against PO Multiple purchase order (without TDS) (Get document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Test(priority = 77, groups = {
			"Bank Payments" }, description = " Advance against PO Multiple purchase order (without TDS) (Get document)")
	public void CreateBankPaymentMultipleAdvPOThroughGetDocumentBankCurcyUSD() throws IOException {
		bankPaymentFunc.BPadvancePOMultipleThroughGetDocumentBankCurcyUSD(webDB.getDataFromProperties("BPAdvance"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Advance against PO Multiple purchase order (without TDS) (Pending)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Test(priority = 78, groups = {
			"Bank Payments" }, description = "Advance against PO Multiple purchase order (without TDS) (Pending)")
	public void CreateBankPaymentMultipleAdvPOThroughPendingDocumentBankCurcyUSD() throws IOException {
		bankPaymentFunc.BPadvancePOMultipleThroughPendingDocumentBankCurcyUSD(webDB.getDataFromProperties("BPAdvance"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Advance against PO single purchase order (without TDS)(Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Test(priority = 79, groups = {
			"Bank Payments" }, description = " Advance against PO single purchase order (without TDS)(Through Add button)")
	public void CreateBankPaymentAdvPOThroughAddButtonUSD() throws IOException {
		bankPaymentFunc.BPadvancePOThroughAddButtonUSD(webDB.getDataFromProperties("BPAdvanceUSD"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Advance against PO single purchase order (without TDS) (Get document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Test(priority = 80, groups = {
			"Bank Payments" }, description = " Advance against PO single purchase order (without TDS) (Get document)")
	public void CreateBankPaymentAdvPOThroughGetDocumentUSD() throws IOException {
		bankPaymentFunc.BPadvancePOThroughGetDocumentUSD(webDB.getDataFromProperties("BPAdvanceUSD"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Advance against PO single purchase order (without TDS) (Pending)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Test(priority = 81, groups = {
			"Bank Payments" }, description = " Advance against PO single purchase order (without TDS) (Pending)")
	public void CreateBankPaymentAdvPOThroughPendingDocumentUSD() throws IOException {
		bankPaymentFunc.BPadvancePOThroughPendingDocumentUSD(webDB.getDataFromProperties("BPAdvanceUSD"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Advance against PO Multiple purchase order (without TDS)(Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Test(priority = 82, groups = {
			"Bank Payments" }, description = " Advance against PO Multiple purchase order (without TDS)(Through Add button)")
	public void CreateBankPaymentMultipleAdvPOThroughAddButtonUSD() throws IOException {
		bankPaymentFunc.BPadvancePOMultipleThroughAddButtonUSD(webDB.getDataFromProperties("BPAdvanceUSD"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Advance against PO Multiple purchase order (without TDS) (Get document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Test(priority = 83, groups = {
			"Bank Payments" }, description = " Advance against PO Multiple purchase order (without TDS) (Get document)")
	public void CreateBankPaymentMultipleAdvPOThroughGetDocumentBankUSD() throws IOException {
		bankPaymentFunc.BPadvancePOMultipleThroughGetDocumentUSD(webDB.getDataFromProperties("BPAdvanceUSD"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Advance against PO Multiple purchase order (without TDS) (Pending)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Test(priority = 84, groups = {
			"Bank Payments" }, description = "Advance against PO Multiple purchase order (without TDS) (Pending)")
	public void CreateBankPaymentMultipleAdvPOThroughPendingDocumentUSD() throws IOException {
		bankPaymentFunc.BPadvancePOMultipleThroughPendingDocumentUSD(webDB.getDataFromProperties("BPAdvanceUSD"));
//		Assert.assertTrue(flag);
	}

//	/**
//	 * Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through
//	 * Add button)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws Exception
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 85, groups = {
//			"Bank Payments" }, description = "Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through Add button)")
//	public void CreateBankPaymentThroughAddButtonMultiPartyMultiBillAdvanceWithNewRef() throws Exception {
//		flag = bankPaymentFunc.CreateBankPaymentThroughAddButtonMultiPartyMultiBillAdvanceWithNewRef(
//				webDB.getDataFromProperties("BPMPMBADVNR"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
//				commonFunc.getDataFromTestDataSheet("BankName"));
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through
//	 * Add button)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws Exception
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 86, groups = {
//			"Bank Payments" }, description = "Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through Add button)")
//	public void CreateBankPaymentThroughGetDocumentMultiPartyMultiBillAdvanceWithNewRef() throws Exception {
//		flag = bankPaymentFunc.CreateBankPaymentThroughGetDocumentMultiPartyMultiBillAdvanceWithNewRef(
//				webDB.getDataFromProperties("BPMPMBADVNR"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
//				commonFunc.getDataFromTestDataSheet("BankName"));
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through
//	 * Add button)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws Exception
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 87, groups = {
//			"Bank Payments" }, description = "Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through Add button)")
//	public void CreateBankPaymentThroughPendingDocumentMultiPartyMultiBillAdvanceWithNewRef() throws Exception {
//		flag = bankPaymentFunc.CreateBankPaymentThroughPendingDocumentMultiPartyMultiBillAdvanceWithNewRef(
//				webDB.getDataFromProperties("BPMPMBADVNR"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
//				commonFunc.getDataFromTestDataSheet("BankName"));
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through
//	 * Add button)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws Exception
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 88, groups = {
//			"Bank Payments" }, description = "Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through Add button)")
//	public void CreateBankPaymentThroughAddButtonMultiPartyMultiBillAdvanceWithNewRefBankCurcyUSD() throws Exception {
//		flag = bankPaymentFunc.CreateBankPaymentThroughAddButtonMultiPartyMultiBillAdvanceWithNewRef(
//				webDB.getDataFromProperties("BPMPMBADVNR"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
//				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through
//	 * Add button)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws Exception
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 89, groups = {
//			"Bank Payments" }, description = "Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through Add button)")
//	public void CreateBankPaymentThroughGetDocumentMultiPartyMultiBillAdvanceWithNewRefBankCurcyUSD() throws Exception {
//		flag = bankPaymentFunc.CreateBankPaymentThroughGetDocumentMultiPartyMultiBillAdvanceWithNewRef(
//				webDB.getDataFromProperties("BPMPMBADVNR"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
//				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through
//	 * Add button)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws Exception
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 90, groups = {
//			"Bank Payments" }, description = "Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through Add button)")
//	public void CreateBankPaymentThroughPendingDocumentMultiPartyMultiBillAdvanceWithNewRefBankCurcyUSD()
//			throws Exception {
//		flag = bankPaymentFunc.CreateBankPaymentThroughPendingDocumentMultiPartyMultiBillAdvanceWithNewRef(
//				webDB.getDataFromProperties("BPMPMBADVNR"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
//				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through
//	 * Add button)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws Exception
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 91, groups = {
//			"Bank Payments" }, description = "Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through Add button)")
//	public void CreateBankPaymentThroughAddButtonMultiPartyMultiBillAdvanceWithNewRefUSD() throws Exception {
//		flag = bankPaymentFunc.CreateBankPaymentThroughAddButtonMultiPartyMultiBillAdvanceWithNewRef(
//				webDB.getDataFromProperties("BPMPMBADVNRUSD"), commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
//				commonFunc.getDataFromTestDataSheet("BankName"));
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through
//	 * Add button)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws Exception
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 92, groups = {
//			"Bank Payments" }, description = "Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through Add button)")
//	public void CreateBankPaymentThroughGetDocumentMultiPartyMultiBillAdvanceWithNewRefUSD() throws Exception {
//		flag = bankPaymentFunc.CreateBankPaymentThroughGetDocumentMultiPartyMultiBillAdvanceWithNewRef(
//				webDB.getDataFromProperties("BPMPMBADVNRUSD"), commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
//				commonFunc.getDataFromTestDataSheet("BankName"));
//		Assert.assertTrue(flag);
//	}
//
//	/**
//	 * Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through
//	 * Add button)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws Exception
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 93, groups = {
//			"Bank Payments" }, description = "Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through Add button)")
//	public void CreateBankPaymentThroughPendingDocumentMultiPartyMultiBillAdvanceWithNewRefUSD() throws Exception {
//		flag = bankPaymentFunc.CreateBankPaymentThroughPendingDocumentMultiPartyMultiBillAdvanceWithNewRef(
//				webDB.getDataFromProperties("BPMPMBADVNRUSD"), commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
//				commonFunc.getDataFromTestDataSheet("BankName"));
//		Assert.assertTrue(flag);
//	}

	/**
	 * This Is Used to set the Variable Handler To Zero After Finishing One Test
	 * Scenarios
	 * 
	 * @author Harikrishna -RMITS
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result, Method method) {
		bankPaymentFunc.verifyAfterMethod();
		previousStatus = result.getStatus();
		if (previousStatus == ITestResult.FAILURE || previousStatus == ITestResult.SKIP) {
			commonFunc.verifyAfterMethod();
		}
		commonFunc.enterAuthorNamesInExtentReport(method);
	}

}
