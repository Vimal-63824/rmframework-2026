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
import commonfunctions.financials.CashPaymentFunctions;
import commonfunctions.supplychainmanagement.ShareOfBusinessFunctions;
import commonfunctions.supplychainmanagement.StockAdjustmentFunctions;
import utils.Mailer;
import utils.ReportLoger;
import utils.WebDriverBase;

public class CashPaymentTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static WebDriverBase webDB = new WebDriverBase();
	static CashPaymentFunctions cashPaymentFunc = new CashPaymentFunctions();
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
	@Test(priority = 1, groups = {
			"Cash Payments" }, description = " Payment Against single Party, Single Bill (Through Add button)")
	public void CreateCashPaymentThroughAddButton() throws IOException {
		flag = cashPaymentFunc
				.CreateBankPaymentThroughLineAddButtonSingleBills(webDB.getDataFromProperties("BankPaymentSingle"));
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
			"Cash Payments" }, description = " Payment Against single Party, Single Bill (Get document )")
	public void CreateCashPaymentThroughGetDocument() throws IOException {
		flag = cashPaymentFunc
				.CreateCashPaymentThroughGetDocumentSingleBills(webDB.getDataFromProperties("BankPaymentSingle"));
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
			"Cash Payments" }, description = " Payment Against single Party, Single Bill (Pending )")
	public void CreateCashPaymentThroughPendingDocs() throws IOException {
		flag = cashPaymentFunc
				.CreateCashPaymentThroughPendingDocsSingleBills(webDB.getDataFromProperties("BankPaymentSingle"));
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
			"Cash Payments" }, description = " Payment Against single Party, Multi Bill (Dr/Cr) (Through Add button)")
	public void CreateCashPaymentSinglePartyMultiBillsThroughAddButton() throws IOException {
		flag = cashPaymentFunc.CreateCashPaymentThroughLineAddButtonMultiBills(webDB.getDataFromProperties("BPSPMB"));
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
			"Cash Payments" }, description = " Payment Against single Party, Multi Bill (Dr/Cr) (Through Get Document)")
	public void CreateCashPaymentSinglePartyMultiBillsThroughGetDocument() throws IOException {
		flag = cashPaymentFunc.CreateCashPaymentThroughGetDocumentMultiBills(webDB.getDataFromProperties("BPSPMB"));
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
			"Cash Payments" }, description = " Payment Against single Party, Multi Bill (Dr/Cr) (Through Pending)")
	public void CreateCashPaymentSinglePartyMultiBillsThroughPending() throws IOException {
		flag = cashPaymentFunc.CreateCashPaymentThroughPendingDocsMultiBills(webDB.getDataFromProperties("BPSPMB"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against Multi Party, Single Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 20, groups = {
			"Cash Payments" }, description = " Payment Against Multi Party, Single Bill (Through Add button)")
	public void CreateCashPaymentThroughAddButtonMultiPartySingleBill() throws IOException {
		flag = cashPaymentFunc.CreateCashPaymentThroughLineAddButtonMultiPartySingleBill(
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
	@Test(priority = 21, groups = {
			"Cash Payments" }, description = "Payment Against Multi Party, Single Bill (Get document)")
	public void CreateCashPaymentThroughGetDocumentMultiPartySingleBill() throws IOException {
		flag = cashPaymentFunc.CreateCashPaymentThroughGetDocumentMultiPartySingleBill(
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
//	@Test(priority = 22, groups = {
//			"Cash Payments" }, description = " Payment Against Multi Party, Single Bill (Pending)")
//	public void CreateCashPaymentThroughPendingDocsMultiPartySingleBill() throws IOException {
//		flag = cashPaymentFunc.CreateCashPaymentThroughPendingDocumentMultiPartySingleBill(
//				webDB.getDataFromProperties("BankPaymentINRMPSB"));
//		Assert.assertTrue(flag);
//	}

	/**
	 * Payment Against Multi Party, Multi Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 10, groups = {
			"Cash Payments" }, description = " Payment Against Multi Party, Single Bill (Through Add button)")
	public void CreateCashPaymentThroughAddButtonMultiPartyMultiBill() throws IOException {
		flag = cashPaymentFunc.CreateCashPaymentThroughLineAddButtonMultiPartyMultiBill(
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
	@Test(priority = 11, groups = {
			"Cash Payments" }, description = "Payment Against Multi Party, Single Bill (Get document)")
	public void CreateCashPaymentThroughGetDocumentMultiPartyMultiBill() throws IOException {
		flag = cashPaymentFunc.CreateCashPaymentThroughGetDocumentMultiPartyMultiBill(
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
//	@Test(priority = 12, groups = {
//			"Cash Payments" }, description = " Payment Against Multi Party, Single Bill (Pending)")
//	public void CreateCashPaymentThroughPendingDocsMultiPartyMultiBill() throws IOException {
//		flag = cashPaymentFunc
//				.CreateCashPaymentThroughPendingMultiPartyMultiBill(webDB.getDataFromProperties("BankPaymentINRMPMB"));
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
			"Cash Payments" }, description = "Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through Add button)")
	public void CreateCashPaymentThroughGetDocumentMultiPartyMultiBillWithNewRef() throws IOException {
		flag = cashPaymentFunc.CreateCashPaymentThroughGetDocumentMultiPartyMultiBillWithNewRef(
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
//	@Test(priority = 14, groups = {
//			"Cash Payments" }, description = "Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through Add button)")
//	public void CreateCashPaymentThroughPendingMultiPartyMultiBillWithNewRef() throws IOException {
//		flag = cashPaymentFunc.CreateCashPaymentThroughPendingDocumentMultiPartyMultiBill(
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
	@Test(priority = 15, groups = {
			"Cash Payments" }, description = "Payment Against Multi Party, Multi Bill with New reference (Dr/Cr)(Through Add button)")
	public void CreateCashPaymentThroughAddButtonMultiPartyMultiBillWithNewRef() throws IOException {
		flag = cashPaymentFunc.CreateCashPaymentThroughAddButtonMultiPartyMultiBillWithNewRef(
				webDB.getDataFromProperties("BankPaymentINRMPMBNR"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment With Expenses Account without Gst
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 16, groups = { "Cash Payments" }, description = "Payment With Expenses Account without Gst")
	public void CreateCashPaymentWithExpenseAndWithoutGST() throws IOException {
		cashPaymentFunc.CashPaymentWithExpAndRevenueWithoutGST(webDB.getDataFromProperties("BankPaymentExp"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
	}

	/**
	 * Payment With Expenses Account without Gst
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 17, groups = { "Cash Payments" }, description = "Payment With Expenses Account without Gst")
	public void CreateCashPaymentWithExpenseAndWithoutGSTAndTDS() throws IOException {
		// TODO Covered in CreateCashPaymentWithExpenseAndWithoutGST() Method
		logs.logging("INFO",
				"This Scenario Is Already Covered In This Method CreateCashPaymentWithExpenseAndWithoutGST() ");
//		Assert.assertTrue(flag);
	}

	/**
	 * Payment With Expenses Account with Gst
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 18, groups = { "Cash Payments" }, description = "Payment With Expenses Account with Gst")
	public void CreateCashPaymentWithExpenseAndWithGST() throws IOException {
		cashPaymentFunc.CashPaymentWithExpAndRevenueWithGST(webDB.getDataFromProperties("BankPaymentExpGST"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
	}

	/**
	 * Payment With Expenses Account with Gst
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 19, groups = { "Cash Payments" }, description = "Payment With Expenses Account with Gst And TDS")
	public void CreateCashPaymentWithExpenseAndWithGSTAndTDS() throws IOException {
		cashPaymentFunc.CashPaymentWithExpAndRevenueWithGSTAndTDS(webDB.getDataFromProperties("BankPaymentExpGSTTDS"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
	}

	/**
	 * Payment Against single Party, Multi Bill (Dr/Cr) (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 7, groups = {
			"Cash Payments" }, description = " Payment Against single Party, Multi Bill (Dr/Cr) (Through Add button)")
	public void CreateaBnkPaymentSinglePartyMultiBillsThroughAddButtonWithMultiUnits() throws IOException {
		flag = cashPaymentFunc
				.CreateCashPaymentThroughLineAddButtonSPMBMultiUnits(webDB.getDataFromProperties("BPSPMB"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against single Party, Multi Bill with multi unit (Dr/Cr) (Get
	 * document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 8, groups = {
			"Cash Payments" }, description = " Payment Against single Party, Multi Bill with multi unit (Dr/Cr) (Get document)")
	public void CreateBankPaymentSinglePartyMultiBillsThroughGetDocumentWithMultiUnits() throws IOException {
		flag = cashPaymentFunc.CreateCashPaymentThroughGetDocumentSPMBMultiUnits(webDB.getDataFromProperties("BPSPMB"));
		Assert.assertTrue(flag);
	}

//	/**
//	 * Payment Against single Party, Multi Bill with multi unit Dr/Cr) (Against
//	 * Pending)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws IOException
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 9, groups = {
//			"Cash Payments" }, description = "Payment Against single Party, Multi Bill with multi unit Dr/Cr) (Against Pending)")
//	public void CreateBankPaymentSinglePartyMultiBillsThroughPendingDocsWithMultiUnits() throws IOException {
//		flag = cashPaymentFunc.CreateCashPaymentThroughPendingDocsSPMBMultiUnits(webDB.getDataFromProperties("BPSPMB"));
//		Assert.assertTrue(flag);
//	}

	/**
	 * Payment Against Multi Party, Single Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 23, groups = {
			"Cash Payments" }, description = " Payment Against Multi Party, Single Bill (Through Add button)")
	public void CreateCashPaymentThroughAddButtonMultiPartyMultiBillWithMultiUnits() throws IOException {
		flag = cashPaymentFunc.CreateCashPaymentThroughLineAddButtonMultiBillsMultiUnits(
				webDB.getDataFromProperties("BankPaymentINRMPMBMU"));
		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against Multi Party, Single Bill (Get document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 24, groups = {
			"Cash Payments" }, description = "Payment Against Multi Party, Single Bill (Get document)")
	public void CreateCashPaymentThroughGetDocumentMultiPartyMultiBillMultiUnits() throws IOException {
		flag = cashPaymentFunc.CreateCashPaymentThroughGetDocumentMultiBillsMultiUnits(
				webDB.getDataFromProperties("BankPaymentINRMPMBMU"));
		Assert.assertTrue(flag);
	}

//	/**
//	 * Payment Against Multi Party, Single Bill (Pending)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws IOException
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 25, groups = {
//			"Cash Payments" }, description = " Payment Against Multi Party, Single Bill (Pending)")
//	public void CreateCashPaymentThroughPendingDocsMultiPartyMultiBillMultiUnits() throws IOException {
//		flag = cashPaymentFunc.CreateCashPaymentThroughPendingDocsMultiBillsMultiUnits(
//				webDB.getDataFromProperties("BankPaymentINRMPMBMU"));
//		Assert.assertTrue(flag);
//	}

	/**
	 * Payment Against Multi Party, Single Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 26, groups = {
			"Cash Payments" }, description = " Payment Against Multi Party, Single Bill (Through Add button)")
	public void CreateCashPaymentAdvPOThroughAddButton() throws IOException {
		cashPaymentFunc.advancePOThroughAddButton(webDB.getDataFromProperties("CPAdvance"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against Multi Party, Single Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 27, groups = {
			"Cash Payments" }, description = " Payment Against Multi Party, Single Bill (Through Add button)")
	public void CreateCashPaymentAdvPOThroughGetDocument() throws IOException {
		cashPaymentFunc.advancePOThroughGetDocument(webDB.getDataFromProperties("CPAdvance"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against Multi Party, Single Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 28, groups = {
			"Cash Payments" }, description = " Payment Against Multi Party, Single Bill (Through Add button)")
	public void CreateCashPaymentAdvPOThroughPendingDocument() throws IOException {
		cashPaymentFunc.advancePOThroughPendingDocument(webDB.getDataFromProperties("CPAdvance"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against Multi Party, Single Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 29, groups = {
			"Cash Payments" }, description = " Payment Against Multi Party, Single Bill (Through Add button)")
	public void CreateCashPaymentMultipleAdvPOThroughAddButton() throws IOException {
		cashPaymentFunc.advancePOMultipleThroughAddButton(webDB.getDataFromProperties("CPAdvance"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against Multi Party, Single Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 30, groups = {
			"Cash Payments" }, description = " Payment Against Multi Party, Single Bill (Through Add button)")
	public void CreateCashPaymentMultipleAdvPOThroughGetDocument() throws IOException {
		cashPaymentFunc.advancePOMultipleThroughGetDocument(webDB.getDataFromProperties("CPAdvance"));
//		Assert.assertTrue(flag);
	}

//
	/**
	 * Payment Against Multi Party, Single Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 31, groups = {
			"Cash Payments" }, description = " Payment Against Multi Party, Single Bill (Through Add button)")
	public void CreateCashPaymentMultipleAdvPOThroughPendingDocument() throws IOException {
		cashPaymentFunc.advancePOMultipleThroughPendingDocument(webDB.getDataFromProperties("CPAdvance"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against Multi Party, Single Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 32, groups = {
			"Cash Payments" }, description = " Payment Against Multi Party, Single Bill (Through Add button)")
	public void CreateCashPaymentAdvPOThroughAddButtonWithSingleTDS() throws IOException {
		cashPaymentFunc.advancePOThroughAddButton(webDB.getDataFromProperties("TDSCPAdvance"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against Multi Party, Single Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 33, groups = {
			"Cash Payments" }, description = " Payment Against Multi Party, Single Bill (Through Add button)")
	public void CreateCashPaymentAdvPOThroughGetDocumentWithSingleTDS() throws IOException {
		cashPaymentFunc.advancePOThroughGetDocument(webDB.getDataFromProperties("TDSCPAdvance"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against Multi Party, Single Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 34, groups = {
			"Cash Payments" }, description = " Payment Against Multi Party, Single Bill (Through Add button)")
	public void CreateCashPaymentAdvPOThroughPendingDocumentWithSingleTDS() throws IOException {
		cashPaymentFunc.advancePOThroughPendingDocument(webDB.getDataFromProperties("TDSCPAdvance"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against Multi Party, Single Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 35, groups = {
			"Cash Payments" }, description = " Payment Against Multi Party, Single Bill (Through Add button)")
	public void CreateCashPaymentMultipleAdvPOThroughAddButtonWithMultiTDS() throws IOException {
		cashPaymentFunc
				.advancePOMultipleThroughAddButtonWithMultipleTDS(webDB.getDataFromProperties("TDSCPAdvanceMulti"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against Multi Party, Single Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 36, groups = {
			"Cash Payments" }, description = " Payment Against Multi Party, Single Bill (Through Add button)")
	public void CreateCashPaymentMultipleAdvPOThroughGetDocumentWithMultiTDS() throws IOException {
		cashPaymentFunc
				.advancePOMultipleThroughGetDocumentWithMultipleTDS(webDB.getDataFromProperties("TDSCPAdvanceMulti"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against Multi Party, Single Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 37, groups = {
			"Cash Payments" }, description = " Payment Against Multi Party, Single Bill (Through Add button)")
	public void CreateCashPaymentMultipleAdvPOThroughPendingDocumentWithMultiTDS() throws IOException {
		cashPaymentFunc.advancePOMultipleThroughPendingDocumentWithMultipleTDS(
				webDB.getDataFromProperties("TDSCPAdvanceMulti"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against Multi Party, Single Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 38, groups = {
			"Cash Payments" }, description = " Payment Against Multi Party, Single Bill (Through Add button)")
	public void CreateCashPaymentAdvWithNewRef() throws IOException {
		cashPaymentFunc.advanceWithNewRef(webDB.getDataFromProperties("CPAdvanceWithNewRef"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against Multi Party, Single Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 39, groups = {
			"Cash Payments" }, description = " Payment Against Multi Party, Single Bill (Through Add button)")
	public void CreateCashPaymentAdvWithNewRefWithTDS() throws IOException {
		cashPaymentFunc.advanceWithNewRef(webDB.getDataFromProperties("TDSCPAdvanceWithNewRef"));
//		Assert.assertTrue(flag);
	}

	/**
	 * Payment Against Multi Party, Single Bill (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 40, groups = {
			"Cash Payments" }, description = " Payment Against Multi Party, Single Bill (Through Add button)")
	public void CreateCashPaymentAdvWithNewRefWithMultiTDS() throws IOException {
		cashPaymentFunc.advanceWithNewRefMultiTDS(webDB.getDataFromProperties("TDSCPAdvanceMultiNewRef"));
//		Assert.assertTrue(flag);
	}

	/**
	 * This Is Used to set the Variable Handler To Zero After Finishing One Test
	 * Scenarios
	 * 
	 * @author Harikrishna -RMITS
	 * @param result
	 */
	@AfterMethod
	public void VerifyAfterMethod(ITestResult result, Method method) {
		cashPaymentFunc.verifyAfterMethod();
		previousStatus = result.getStatus();
		if (previousStatus == ITestResult.FAILURE || previousStatus == ITestResult.SKIP) {
			commonFunc.verifyAfterMethod();
		}
		commonFunc.enterAuthorNamesInExtentReport(method);
	}

}
