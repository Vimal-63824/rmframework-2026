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
import org.testng.annotations.Test;

import commonfunctions.Author;
import commonfunctions.CommonFunctions;
import commonfunctions.financials.BankReceiptFunctions;
import commonfunctions.financials.CashReceiptFunctions;
import commonfunctions.supplychainmanagement.ShareOfBusinessFunctions;
import commonfunctions.supplychainmanagement.StockAdjustmentFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class CashReceiptTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static WebDriverBase webDB = new WebDriverBase();
	static CashReceiptFunctions cashReceiptFunc = new CashReceiptFunctions();

//	/**
//	 * Receipt Against single Party, Single invoice(Through Add button)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws IOException
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 1, groups = {
//			"Cash Receipt" }, description = "Receipt Against single Party, Single invoice(Through Add button)")
//	public void CreateCashReceiptSinglePartySingleInvoiceThroughAddButton() throws IOException {
//		cashReceiptFunc.CRSinglePartySingleInvoiceThroughAddButton(webDB.getDataFromProperties("BankReceipt"));
//	}

	/**
	 * Receipt Against single Party, Single Invoice (Get document )
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 2, groups = {
			"Cash Receipt" }, description = "Receipt Against single Party, Single Invoice (Get document )")
	public void CreateCashReceiptSinglePartySingleInvoiceThroughGetDocument() throws IOException {
		cashReceiptFunc.CRSinglePartySingleInvoiceThroughGetDocument(webDB.getDataFromProperties("BankReceipt"));
	}

	/**
	 * Receipt Against single Party, Single Invoice (Pending )
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 3, groups = {
			"Cash Receipt" }, description = "Receipt Against single Party, Single Invoice (Pending )")
	public void CreateCashReceiptSinglePartySingleInvoiceThroughPendingDocs() throws IOException {
		cashReceiptFunc.CRSinglePartySingleInvoiceThroughPendingDocs(webDB.getDataFromProperties("BankReceipt"));
	}

//	/**
//	 * Receipt Against single Party, Multi Invoice (Dr/Cr) (Through Add button)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws IOException
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 4, groups = {
//			"Cash Receipt" }, description = "Receipt Against single Party, Multi Invoice (Dr/Cr) (Through Add button)")
//	public void CreateCashReceiptSinglePartyMultiInvoiceThroughAddButton() throws IOException {
//		cashReceiptFunc.CRSinglePartyMultiInvoiceThroughAddButton(webDB.getDataFromProperties("BankReceipt"));
//	}

	/**
	 * Receipt Against single Party, Multi Invoice (Dr/Cr) (Get document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 5, groups = {
			"Cash Receipt" }, description = "Receipt Against single Party, Multi Invoice (Dr/Cr) (Get document)")
	public void CreateCashReceiptSinglePartyMultiInvoiceThroughGetDocument() throws IOException {
		cashReceiptFunc.CRSinglePartyMultiInvoiceThroughGetDocument(webDB.getDataFromProperties("BankReceipt"));
	}

	/**
	 * Receipt Against single Party, Multi Invoice (Dr/Cr) (Pending)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 6, groups = {
			"Cash Receipt" }, description = "Receipt Against single Party, Multi Invoice (Dr/Cr) (Pending)")
	public void CreateCashReceiptSinglePartyMultiInvoiceThroughPendingDocs() throws IOException {
		cashReceiptFunc.CRSinglePartyMultiInvoiceThroughPendingDocs(webDB.getDataFromProperties("BankReceipt"));
	}

//	/**
//	 * Receipt Against Multi Party, Single Invoice (Through Add button)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws IOException
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 7, groups = {
//			"Cash Receipt" }, description = "Receipt Against Multi Party, Single Invoice (Through Add button)")
//	public void CreateCashReceiptMultiPartySingleInvoiceThroughAddButton() throws IOException {
//		cashReceiptFunc.CRMultiPartySingleInvoiceThroughAddButton(webDB.getDataFromProperties("BankReceiptMulti"));
//	}

	/**
	 * Receipt Against Multi Party, Single Invoice (Get document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 8, groups = {
			"Cash Receipt" }, description = "Receipt Against Multi Party, Single Invoice (Get document)")
	public void CreateCashReceiptMultiPartySingleInvoiceThroughGetDocument() throws IOException {
		cashReceiptFunc.CRMultiPartySingleInvoiceThroughGetDocument(webDB.getDataFromProperties("BankReceiptMulti"));
	}

	/**
	 * Receipt Against Multi Party, Single Invoice (Pending)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 9, groups = {
			"Cash Receipt" }, description = "Receipt Against Multi Party, Single Invoice (Pending)")
	public void CreateCashReceiptMultiPartySingleInvoiceThroughPendingDocs() throws IOException {
		cashReceiptFunc.CRMultiPartySingleInvoiceThroughPendingDocs(webDB.getDataFromProperties("BankReceiptMulti"));
	}

//	/**
//	 * Receipt Against Multi Party, Multi Invoice (Dr/Cr)(Through Add button)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws IOException
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 10, groups = {
//			"Cash Receipt" }, description = "Receipt Against Multi Party, Multi Invoice (Dr/Cr)(Through Add button)")
//	public void CreateCashReceiptMultiPartyMultiInvoiceThroughAddButton() throws IOException {
//		cashReceiptFunc.CRMultiPartyMultiInvoiceThroughAddButton(webDB.getDataFromProperties("BankReceiptMulti"));
//	}

	/**
	 * Receipt Against Multi Party, Multi Invoice (Dr/Cr) (Get document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 11, groups = {
			"Cash Receipt" }, description = "Receipt Against Multi Party, Multi Invoice (Dr/Cr) (Get document)")
	public void CreateCashReceiptMultiPartyMultiInvoiceThroughGetDocument() throws IOException {
		cashReceiptFunc.CRMultiPartyMultiInvoiceThroughGetDocument(webDB.getDataFromProperties("BankReceiptMulti"));
	}

	/**
	 * Receipt Against Multi Party, Multi Invoice (Dr/Cr) (Pending)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 12, groups = {
			"Cash Receipt" }, description = "Receipt Against Multi Party, Multi Invoice (Dr/Cr) (Pending)")
	public void CreateCashReceiptMultiPartyMultiInvoiceThroughPendingDocs() throws IOException {
		cashReceiptFunc.CRMultiPartyMultiInvoiceThroughPendingDocs(webDB.getDataFromProperties("BankReceiptMulti"));
	}

//	/**
//	 * Receipt Against Multi Party, Multi Invoice with New reference (Dr/Cr)(Through
//	 * Add button)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws IOException
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 13, groups = {
//			"Cash Receipt" }, description = "Receipt Against Multi Party, Multi Invoice with New reference (Dr/Cr)(Through Add button)")
//	public void CreateCashReceiptMultiPartyMultiInvoiceThroughAddButtonWithNewRef() throws IOException {
//		cashReceiptFunc.CRMultiPartyMultiInvoiceThroughAddButtonWithNewRef(
//				webDB.getDataFromProperties("BankReceiptMultiNewRef"));
//	}
//
//	/**
//	 * Receipt Against Multi Party, Multi Invoice with New reference (Dr/Cr)(Get
//	 * document)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws IOException
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 14, groups = {
//			"Bank Receipt" }, description = "Receipt Against Multi Party, Multi Invoice with New reference (Dr/Cr)(Get document)")
//	public void CreateCashReceiptMultiPartyMultiInvoiceThroughGetDocumentWithNewRef() throws IOException {
//		cashReceiptFunc.CRMultiPartyMultiInvoiceThroughGetDocumentWithNewref(
//				webDB.getDataFromProperties("BankReceiptMultiNewRef"));
//	}
//
//	/**
//	 * Receipt Against Multi Party, Multi Invoice with New reference
//	 * (Dr/Cr)(Pending)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws IOException
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 15, groups = {
//			"Cash Receipt" }, description = "Receipt Against Multi Party, Multi Invoice with New reference (Dr/Cr)(Pending)")
//	public void CreateCashReceiptMultiPartyMultiInvoiceThroughPendingDocsWithNewRef() throws IOException {
//		cashReceiptFunc.CRMultiPartyMultiInvoiceThroughPendingDocsWithNewRef(
//				webDB.getDataFromProperties("BankReceiptMultiNewRef"));
//	}
//
	/**
	 * Receipt With Expenses/Revenue Account without Gst
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 16, groups = { "Cash Receipt" }, description = "Receipt With Expenses/Revenue Account without Gst")
	public void CreateCashReceiptWithExpenseAndRevenueWithoutGST() throws IOException {
		cashReceiptFunc.CashReceiptWithExpAndRevenueWithoutGST(webDB.getDataFromProperties("BankReceiptRevExp"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
	}

	/**
	 * Receipt With Expenses/Revenue Account with Gst
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 17, groups = { "Cash Receipt" }, description = "Receipt With Expenses/Revenue Account with Gst")
	public void CreateCashReceiptWithExpenseAndRevenueWithGST() throws IOException {
		cashReceiptFunc.CashReceiptWithExpAndRevenueWithGST(webDB.getDataFromProperties("BankReceiptRevExpGST"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
	}

//	/**
//	 * Receipt Against single Party, Multi Invoice (Dr/Cr) (Through Add button)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws IOException
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 18, groups = {
//			"Cash Receipt" }, description = "Receipt Against single Party, Multi Invoice (Dr/Cr) (Through Add button)")
//	public void CreateCashReceiptSinglePartyMultiInvoiceThroughAddButtonMultiUnit() throws IOException {
//		cashReceiptFunc.CRSinglePartyMultiInvoiceThroughAddButtonMultiUnits(webDB.getDataFromProperties("BankReceipt"));
//	}

	/**
	 * Receipt Against single Party, Multi Invoice (Dr/Cr) (Get document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 19, groups = {
			"Cash Receipt" }, description = "Receipt Against single Party, Multi Invoice (Dr/Cr) (Get document)")
	public void CreateCashReceiptSinglePartyMultiInvoiceThroughGetDocumentMultiUnit() throws IOException {
		cashReceiptFunc
				.CRSinglePartyMultiInvoiceThroughGetDocumentMultiUnits(webDB.getDataFromProperties("BankReceipt"));
	}

	/**
	 * Receipt Against single Party, Multi Invoice (Dr/Cr) (Pending)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 20, groups = {
			"Cash Receipt" }, description = "Receipt Against single Party, Multi Invoice (Dr/Cr) (Pending)")
	public void CreateCashReceiptSinglePartyMultiInvoiceThroughPendingDocsMultiUnit() throws IOException {
		cashReceiptFunc
				.CRSinglePartyMultiInvoiceThroughPendingDocsMultiUnits(webDB.getDataFromProperties("BankReceipt"));
	}

//	/**
//	 * Receipt Against Multi Party, Multi Invoice (Dr/Cr)(Through Add button)
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @throws IOException
//	 */
//	@Author("Harikrishna-RMITS")
//	@Test(priority = 21, groups = {
//			"Cash Receipt" }, description = "Receipt Against Multi Party, Multi Invoice (Dr/Cr)(Through Add button)")
//	public void CreateCashReceiptMultiPartyMultiInvoiceThroughAddButtonMultiUnits() throws IOException {
//		cashReceiptFunc
//				.CRMultiPartyMultiInvoiceThroughAddButtonMultiUnits(webDB.getDataFromProperties("BankReceiptMulti"));
//	}

	/**
	 * Receipt Against Multi Party, Multi Invoice (Dr/Cr) (Get document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 22, groups = {
			"Cash Receipt" }, description = "Receipt Against Multi Party, Multi Invoice (Dr/Cr) (Get document)")
	public void CreateCashReceiptMultiPartyMultiInvoiceThroughGetDocumentMultiUnits() throws IOException {
		cashReceiptFunc
				.CRMultiPartyMultiInvoiceThroughGetDocumentMultiUnits(webDB.getDataFromProperties("BankReceiptMulti"));
	}

	/**
	 * Receipt Against Multi Party, Multi Invoice (Dr/Cr) (Pending)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 23, groups = {
			"Cash Receipt" }, description = "Receipt Against Multi Party, Multi Invoice (Dr/Cr) (Pending)")
	public void CreateCashReceiptMultiPartyMultiInvoiceThroughPendingDocsMultiUnits() throws IOException {
		cashReceiptFunc
				.CRMultiPartyMultiInvoiceThroughPendingDocsMultiUnits(webDB.getDataFromProperties("BankReceiptMulti"));
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
		cashReceiptFunc.verifyAfterMethod();
		commonFunc.enterAuthorNamesInExtentReport(method);
	}

}
