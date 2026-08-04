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
import commonfunctions.financials.BankReceiptFunctions;
import commonfunctions.supplychainmanagement.ShareOfBusinessFunctions;
import commonfunctions.supplychainmanagement.StockAdjustmentFunctions;
import utils.Mailer;
import utils.WebDriverBase;

public class BankReceiptTest extends RoadMapERPTest {
	boolean flag = false;
	static CommonFunctions commonFunc = new CommonFunctions();
	static WebDriverBase webDB = new WebDriverBase();
	static BankReceiptFunctions bankReceiptFunc = new BankReceiptFunctions();
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
	 * Receipt Against single Party, Single invoice(Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 1, groups = {
			"Bank Receipts" }, description = "Receipt Against single Party, Single invoice(Through Add button)")
	public void CreateBankReceiptSinglePartySingleInvoiceThroughAddButton() throws IOException {
		bankReceiptFunc.BRSinglePartySingleInvoiceThroughAddButton(webDB.getDataFromProperties("BankReceipt"));
	}

//
	/**
	 * Receipt Against single Party, Single Invoice (Get document )
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 2, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Single Invoice (Get document )")
	public void CreateBankReceiptSinglePartySingleInvoiceThroughGetDocument() throws IOException {
		bankReceiptFunc.BRSinglePartySingleInvoiceThroughGetDocument(webDB.getDataFromProperties("BankReceipt"));
	}

	/**
	 * Receipt Against single Party, Single Invoice (Pending )
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 3, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Single Invoice (Pending )")
	public void CreateBankReceiptSinglePartySingleInvoiceThroughPendingDocs() throws IOException {
		bankReceiptFunc.BRSinglePartySingleInvoiceThroughPendingDocs(webDB.getDataFromProperties("BankReceipt"));
	}

	/**
	 * Receipt Against single Party, Multi Invoice (Dr/Cr) (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 4, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Multi Invoice (Dr/Cr) (Through Add button)")
	public void CreateBankReceiptSinglePartyMultiInvoiceThroughAddButton() throws IOException {
		bankReceiptFunc.BRSinglePartyMultiInvoiceThroughAddButton(webDB.getDataFromProperties("BankReceipt"));
	}

	/**
	 * Receipt Against single Party, Multi Invoice (Dr/Cr) (Get document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 5, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Multi Invoice (Dr/Cr) (Get document)")
	public void CreateBankReceiptSinglePartyMultiInvoiceThroughGetDocument() throws IOException {
		bankReceiptFunc.BRSinglePartyMultiInvoiceThroughGetDocument(webDB.getDataFromProperties("BankReceipt"));
	}

	/**
	 * Receipt Against single Party, Multi Invoice (Dr/Cr) (Pending)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 6, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Multi Invoice (Dr/Cr) (Pending)")
	public void CreateBankReceiptSinglePartyMultiInvoiceThroughPendingDocs() throws IOException {
		bankReceiptFunc.BRSinglePartyMultiInvoiceThroughPendingDocs(webDB.getDataFromProperties("BankReceipt"));
	}

	/**
	 * Receipt Against Multi Party, Single Invoice (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 7, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Single Invoice (Through Add button)")
	public void CreateBankReceiptMultiPartySingleInvoiceThroughAddButton() throws IOException {
		bankReceiptFunc.BRMultiPartySingleInvoiceThroughAddButton(webDB.getDataFromProperties("BankReceiptMulti"));
	}

	/**
	 * Receipt Against Multi Party, Single Invoice (Get document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 8, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Single Invoice (Get document)")
	public void CreateBankReceiptMultiPartySingleInvoiceThroughGetDocument() throws IOException {
		bankReceiptFunc.BRMultiPartySingleInvoiceThroughGetDocument(webDB.getDataFromProperties("BankReceiptMulti"));
	}

	/**
	 * Receipt Against Multi Party, Single Invoice (Pending)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 9, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Single Invoice (Pending)")
	public void CreateBankReceiptMultiPartySingleInvoiceThroughPendingDocs() throws IOException {
		bankReceiptFunc.BRMultiPartySingleInvoiceThroughPendingDocs(webDB.getDataFromProperties("BankReceiptMulti"));
	}

	/**
	 * Receipt Against Multi Party, Multi Invoice (Dr/Cr)(Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 10, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Multi Invoice (Dr/Cr)(Through Add button)")
	public void CreateBankReceiptMultiPartyMultiInvoiceThroughAddButton() throws IOException {
		bankReceiptFunc.BRMultiPartyMultiInvoiceThroughAddButton(webDB.getDataFromProperties("BankReceiptMulti"));
	}

	/**
	 * Receipt Against Multi Party, Multi Invoice (Dr/Cr) (Get document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 11, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Multi Invoice (Dr/Cr) (Get document)")
	public void CreateBankReceiptMultiPartyMultiInvoiceThroughGetDocument() throws IOException {
		bankReceiptFunc.BRMultiPartyMultiInvoiceThroughGetDocument(webDB.getDataFromProperties("BankReceiptMulti"));
	}

	/**
	 * Receipt Against Multi Party, Multi Invoice (Dr/Cr) (Pending)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 12, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Multi Invoice (Dr/Cr) (Pending)")
	public void CreateBankReceiptMultiPartyMultiInvoiceThroughPendingDocs() throws IOException {
		bankReceiptFunc.BRMultiPartyMultiInvoiceThroughPendingDocs(webDB.getDataFromProperties("BankReceiptMulti"));
	}

	/**
	 * Receipt Against Multi Party, Multi Invoice with New reference (Dr/Cr)(Through
	 * Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 13, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Multi Invoice with New reference (Dr/Cr)(Through Add button)")
	public void CreateBankReceiptMultiPartyMultiInvoiceThroughAddButtonWithNewRef() throws IOException {
		bankReceiptFunc.BRMultiPartyMultiInvoiceThroughAddButtonWithNewRef(
				webDB.getDataFromProperties("BankReceiptMultiNewRef"));
	}

	/**
	 * Receipt Against Multi Party, Multi Invoice with New reference (Dr/Cr)(Get
	 * document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 14, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Multi Invoice with New reference (Dr/Cr)(Get document)")
	public void CreateBankReceiptMultiPartyMultiInvoiceThroughGetDocumentWithNewRef() throws IOException {
		bankReceiptFunc.BRMultiPartyMultiInvoiceThroughGetDocumentWithNewref(
				webDB.getDataFromProperties("BankReceiptMultiNewRef"));
	}

	/**
	 * Receipt Against Multi Party, Multi Invoice with New reference
	 * (Dr/Cr)(Pending)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 15, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Multi Invoice with New reference (Dr/Cr)(Pending)")
	public void CreateBankReceiptMultiPartyMultiInvoiceThroughPendingDocsWithNewRef() throws IOException {
		bankReceiptFunc.BRMultiPartyMultiInvoiceThroughPendingDocsWithNewRef(
				webDB.getDataFromProperties("BankReceiptMultiNewRef"));
	}

	/**
	 * Receipt - Bank, Customer and Base Currency: Same (USD) Party, Single
	 * invoice(Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 16, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Single invoice(Through Add button) - Receipt - Bank, Customer and Base Currency: Same (USD)")
	public void CreateBankReceiptSinglePartySingleInvoiceThroughAddButtonUSD() throws IOException {
		bankReceiptFunc.BRSinglePartySingleInvoiceThroughAddButtonUSD(webDB.getDataFromProperties("BankReceiptUSD"));
	}

	/**
	 * Receipt - Bank, Customer and Base Currency: Same (USD) Receipt Against single
	 * Party, Single Invoice (Get document )
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 17, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Single Invoice (Get document ) - Receipt - Bank, Customer and Base Currency: Same (USD)")
	public void CreateBankReceiptSinglePartySingleInvoiceThroughGetDocumentUSD() throws IOException {
		bankReceiptFunc.BRSinglePartySingleInvoiceThroughGetDocumentUSD(webDB.getDataFromProperties("BankReceiptUSD"));
	}

	/**
	 * Receipt - Bank, Customer and Base Currency: Same (USD) Receipt Against single
	 * Party, Single Invoice (Pending )
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 18, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Single Invoice (Pending ) - Receipt - Bank, Customer and Base Currency: Same (USD)")
	public void CreateBankReceiptSinglePartySingleInvoiceThroughPendingDocsUSD() throws IOException {
		bankReceiptFunc.BRSinglePartySingleInvoiceThroughPendingDocsUSD(webDB.getDataFromProperties("BankReceiptUSD"));
	}

	/**
	 * Receipt - Bank, Customer and Base Currency: Same (USD) Party, Multi Invoice
	 * (Dr/Cr) (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 19, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Multi Invoice (Dr/Cr) (Through Add button) - Receipt - Bank, Customer and Base Currency: Same (USD)")
	public void CreateBankReceiptSinglePartyMultiInvoiceThroughAddButtonUSD() throws IOException {
		bankReceiptFunc.BRSinglePartyMultiInvoiceThroughAddButtonUSD(webDB.getDataFromProperties("BankReceiptUSD"));
	}

	/**
	 * Receipt - Bank, Customer and Base Currency: Same (USD) Receipt Against single
	 * Party, Multi Invoice (Dr/Cr) (Get document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 20, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Multi Invoice (Dr/Cr) (Get document) - Receipt - Bank, Customer and Base Currency: Same (USD)")
	public void CreateBankReceiptSinglePartyMultiInvoiceThroughGetDocumentUSD() throws IOException {
		bankReceiptFunc.BRSinglePartyMultiInvoiceThroughGetDocumentUSD(webDB.getDataFromProperties("BankReceiptUSD"));
	}

	/**
	 * Receipt - Bank, Customer and Base Currency: Same (USD) Receipt Against single
	 * Party, Multi Invoice (Dr/Cr) (Pending)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 21, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Multi Invoice (Dr/Cr) (Pending) - Receipt - Bank, Customer and Base Currency: Same (USD)")
	public void CreateBankReceiptSinglePartyMultiInvoiceThroughPendingDocsUSD() throws IOException {
		bankReceiptFunc.BRSinglePartyMultiInvoiceThroughPendingDocsUSD(webDB.getDataFromProperties("BankReceiptUSD"));
	}

	/**
	 * Receipt - Bank, Customer and Base Currency: Same (USD) Receipt Against Multi
	 * Party, Single Invoice (Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 22, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Single Invoice (Through Add button) - Receipt - Bank, Customer and Base Currency: Same (USD)")
	public void CreateBankReceiptMultiPartySingleInvoiceThroughAddButtonUSD() throws IOException {
		bankReceiptFunc
				.BRMultiPartySingleInvoiceThroughAddButtonUSD(webDB.getDataFromProperties("BankReceiptMultiUSD"));
	}

	/**
	 * Receipt - Bank, Customer and Base Currency: Same (USD) Receipt Against Multi
	 * Party, Single Invoice (Get document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 23, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Single Invoice (Get document) - Receipt - Bank, Customer and Base Currency: Same (USD)")
	public void CreateBankReceiptMultiPartySingleInvoiceThroughGetDocumentUSD() throws IOException {
		bankReceiptFunc
				.BRMultiPartySingleInvoiceThroughGetDocumentUSD(webDB.getDataFromProperties("BankReceiptMultiUSD"));
	}

	/**
	 * Receipt - Bank, Customer and Base Currency: Same (USD) Receipt Against Multi
	 * Party, Single Invoice (Pending)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 24, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Single Invoice (Pending) - Receipt - Bank, Customer and Base Currency: Same (USD)")
	public void CreateBankReceiptMultiPartySingleInvoiceThroughPendingDocsUSD() throws IOException {
		bankReceiptFunc
				.BRMultiPartySingleInvoiceThroughPendingDocsUSD(webDB.getDataFromProperties("BankReceiptMultiUSD"));
	}

	/**
	 * Receipt - Bank, Customer and Base Currency: Same (USD) Receipt Against Multi
	 * Party, Multi Invoice (Dr/Cr)(Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 25, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Multi Invoice (Dr/Cr)(Through Add button) - Receipt - Bank, Customer and Base Currency: Same (USD)")
	public void CreateBankReceiptMultiPartyMultiInvoiceThroughAddButtonUSD() throws IOException {
		bankReceiptFunc.BRMultiPartyMultiInvoiceThroughAddButtonUSD(webDB.getDataFromProperties("BankReceiptMultiUSD"));
	}

	/**
	 * Receipt - Bank, Customer and Base Currency: Same (USD) Receipt Against Multi
	 * Party, Multi Invoice (Dr/Cr) (Get document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 26, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Multi Invoice (Dr/Cr) (Get document) - Receipt - Bank, Customer and Base Currency: Same (USD)")
	public void CreateBankReceiptMultiPartyMultiInvoiceThroughGetDocumentUSD() throws IOException {
		bankReceiptFunc
				.BRMultiPartyMultiInvoiceThroughGetDocumentUSD(webDB.getDataFromProperties("BankReceiptMultiUSD"));
	}

	/**
	 * Receipt - Bank, Customer and Base Currency: Same (USD) Receipt Against Multi
	 * Party, Multi Invoice (Dr/Cr) (Pending)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 27, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Multi Invoice (Dr/Cr) (Pending) - Receipt - Bank, Customer and Base Currency: Same (USD)")
	public void CreateBankReceiptMultiPartyMultiInvoiceThroughPendingDocsUSD() throws IOException {
		bankReceiptFunc
				.BRMultiPartyMultiInvoiceThroughPendingDocsUSD(webDB.getDataFromProperties("BankReceiptMultiUSD"));
	}

	/**
	 * Receipt - Bank, Customer and Base Currency: Same (USD) Receipt Against Multi
	 * Party, Multi Invoice with New reference (Dr/Cr)(Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 28, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Multi Invoice with New reference (Dr/Cr)(Through Add button) - Receipt - Bank, Customer and Base Currency: Same (USD)")
	public void CreateBankReceiptMultiPartyMultiInvoiceThroughAddButtonWithNewRefUSD() throws IOException {
		bankReceiptFunc.BRMultiPartyMultiInvoiceThroughAddButtonWithNewRefUSD(
				webDB.getDataFromProperties("BankReceiptMultiNewRefUSD"));
	}

	/**
	 * Receipt - Bank, Customer and Base Currency: Same (USD) Receipt Against Multi
	 * Party, Multi Invoice with New reference (Dr/Cr)(Get document)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 29, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Multi Invoice with New reference (Dr/Cr)(Get document) - Receipt - Bank, Customer and Base Currency: Same (USD)")
	public void CreateBankReceiptMultiPartyMultiInvoiceThroughGetDocumentWithNewRefUSD() throws IOException {
		bankReceiptFunc.BRMultiPartyMultiInvoiceThroughGetDocumentWithNewrefUSD(
				webDB.getDataFromProperties("BankReceiptMultiNewRefUSD"));
	}

	/**
	 * Receipt - Bank, Customer and Base Currency: Same (USD) Receipt Against Multi
	 * Party, Multi Invoice with New reference (Dr/Cr)(Pending)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 30, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Multi Invoice with New reference (Dr/Cr)(Pending) - Receipt - Bank, Customer and Base Currency: Same (USD)")
	public void CreateBankReceiptMultiPartyMultiInvoiceThroughPendingDocsWithNewRefUSD() throws IOException {
		bankReceiptFunc.BRMultiPartyMultiInvoiceThroughPendingDocsWithNewRefUSD(
				webDB.getDataFromProperties("BankReceiptMultiNewRefUSD"));
	}

	/**
	 * Bank receipt With Post Dated Cheque
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 31, groups = { "Bank Receipt" }, description = "Bank receipt With Post Dated Cheque")
	public void CreateBankReceiptWithPostDatedCheque() throws IOException {
		bankReceiptFunc.bankReceiptWithPostDatedCheque(webDB.getDataFromProperties("BankReceiptPDC"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"));
	}

	/**
	 * Bank receipt With Charges
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 32, groups = { "Bank Receipt" }, description = "Bank receipt With Charges")
	public void CreateBankReceiptWithCharges() throws IOException {
		bankReceiptFunc.BankReceiptSIWithCharges(webDB.getDataFromProperties("BankReceiptCharges"));
	}

	/**
	 * Receipt With Expenses/Revenue Account without Gst
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 33, groups = { "Bank Receipt" }, description = "Receipt With Expenses/Revenue Account without Gst")
	public void CreateBankReceiptWithExpenseAndRevenueWithoutGST() throws IOException {
		bankReceiptFunc.BankReceiptWithExpAndRevenueWithoutGST(webDB.getDataFromProperties("BankReceiptRevExp"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"), commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * Receipt With Expenses/Revenue Account with Gst
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 34, groups = { "Bank Receipt" }, description = "Receipt With Expenses/Revenue Account with Gst")
	public void CreateBankReceiptWithExpenseAndRevenueWithGST() throws IOException {
		bankReceiptFunc.BankReceiptWithExpAndRevenueWithGST(webDB.getDataFromProperties("BankReceiptRevExpGST"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"));
	}

	/**
	 * Receipt With Expenses/Revenue Account without Gst - USD
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 35, groups = {
			"Bank Receipt" }, description = "Receipt With Expenses/Revenue Account without Gst - USD")
	public void CreateBankReceiptWithExpenseAndRevenueWithoutGSTUSD() throws IOException {
		bankReceiptFunc.BankReceiptWithExpAndRevenueWithoutGST(webDB.getDataFromProperties("BankReceiptRevExp"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * Bank receipt With Charges - USD
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 36, groups = { "Bank Receipt" }, description = "Bank receipt With Charges - USD")
	public void CreateBankReceiptWithChargesUSD() throws IOException {
		bankReceiptFunc.BankReceiptSIWithChargesUSD(webDB.getDataFromProperties("BankReceiptChargesUSD"));
	}

	/**
	 * Receipt Against single Party, Single invoice(Through Add button) - Receipt -
	 * Bank foreign Customer INR and Base Currency INR: Difference
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 37, groups = {
			"Bank Receipts" }, description = "Receipt Against single Party, Single invoice(Through Add button) - Receipt - Bank foreign Customer INR and Base Currency INR: Difference")
	public void CreateBankReceiptSinglePartySingleInvoiceThroughAddButtonBankCurcyUSD() throws IOException {
		bankReceiptFunc
				.BRSinglePartySingleInvoiceThroughAddButtonBankCurcyUSD(webDB.getDataFromProperties("BankReceipt"));
	}

	/**
	 * Receipt Against single Party, Single Invoice (Get document ) - Receipt - Bank
	 * foreign Customer INR and Base Currency INR: Difference
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 38, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Single Invoice (Get document ) - Receipt - Bank foreign Customer INR and Base Currency INR: Difference")
	public void CreateBankReceiptSinglePartySingleInvoiceThroughGetDocumentBankCurcyUSD() throws IOException {
		bankReceiptFunc
				.BRSinglePartySingleInvoiceThroughGetDocumentBankCurcyUSD(webDB.getDataFromProperties("BankReceipt"));
	}

	/**
	 * Receipt Against single Party, Single Invoice (Pending ) - Receipt - Bank
	 * foreign Customer INR and Base Currency INR: Difference
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 39, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Single Invoice (Pending ) - Receipt - Bank foreign Customer INR and Base Currency INR: Difference")
	public void CreateBankReceiptSinglePartySingleInvoiceThroughPendingDocsBankCurcyUSD() throws IOException {
		bankReceiptFunc
				.BRSinglePartySingleInvoiceThroughPendingDocsBankCurcyUSD(webDB.getDataFromProperties("BankReceipt"));
	}

	/**
	 * Receipt Against single Party, Multi Invoice (Dr/Cr) (Through Add button) -
	 * Receipt - Bank foreign Customer INR and Base Currency INR: Difference
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 40, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Multi Invoice (Dr/Cr) (Through Add button) - Receipt - Bank foreign Customer INR and Base Currency INR: Difference")
	public void CreateBankReceiptSinglePartyMultiInvoiceThroughAddButtonBankCurcyUSD() throws IOException {
		bankReceiptFunc
				.BRSinglePartyMultiInvoiceThroughAddButtonBankCurcyUSD(webDB.getDataFromProperties("BankReceipt"));
	}

	/**
	 * Receipt Against single Party, Multi Invoice (Dr/Cr) (Get document) - Receipt
	 * - Bank foreign Customer INR and Base Currency INR: Difference
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 41, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Multi Invoice (Dr/Cr) (Get document) - Receipt - Bank foreign Customer INR and Base Currency INR: Difference")
	public void CreateBankReceiptSinglePartyMultiInvoiceThroughGetDocumentBankCurcyUSD() throws IOException {
		bankReceiptFunc
				.BRSinglePartyMultiInvoiceThroughGetDocumentBankCurcyUSD(webDB.getDataFromProperties("BankReceipt"));
	}

	/**
	 * Receipt Against single Party, Multi Invoice (Dr/Cr) (Pending) - Receipt -
	 * Bank foreign Customer INR and Base Currency INR: Difference
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 42, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Multi Invoice (Dr/Cr) (Pending) - Receipt - Bank foreign Customer INR and Base Currency INR: Difference")
	public void CreateBankReceiptSinglePartyMultiInvoiceThroughPendingDocsBankCurcyUSD() throws IOException {
		bankReceiptFunc
				.BRSinglePartyMultiInvoiceThroughPendingDocsBankCurcyUSD(webDB.getDataFromProperties("BankReceipt"));
	}

	/**
	 * Receipt Against Multi Party, Single Invoice (Through Add button) - Receipt -
	 * Bank foreign Customer INR and Base Currency INR: Difference
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 43, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Single Invoice (Through Add button) - Receipt - Bank foreign Customer INR and Base Currency INR: Difference")
	public void CreateBankReceiptMultiPartySingleInvoiceThroughAddButtonBankCurcyUSD() throws IOException {
		bankReceiptFunc
				.BRMultiPartySingleInvoiceThroughAddButtonBankCurcyUSD(webDB.getDataFromProperties("BankReceiptMulti"));
	}

	/**
	 * Receipt Against Multi Party, Single Invoice (Get document) - Receipt - Bank
	 * foreign Customer INR and Base Currency INR: Difference
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 44, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Single Invoice (Get document) - Receipt - Bank foreign Customer INR and Base Currency INR: Difference")
	public void CreateBankReceiptMultiPartySingleInvoiceThroughGetDocumentBankCurcyUSD() throws IOException {
		bankReceiptFunc.BRMultiPartySingleInvoiceThroughGetDocumentBankCurcyUSD(
				webDB.getDataFromProperties("BankReceiptMulti"));
	}

	/**
	 * Receipt Against Multi Party, Single Invoice (Pending) - Receipt - Bank
	 * foreign Customer INR and Base Currency INR: Difference
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 45, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Single Invoice (Pending) - Receipt - Bank foreign Customer INR and Base Currency INR: Difference")
	public void CreateBankReceiptMultiPartySingleInvoiceThroughPendingDocsBankCurcyUSD() throws IOException {
		bankReceiptFunc.BRMultiPartySingleInvoiceThroughPendingDocsBankCurcyUSD(
				webDB.getDataFromProperties("BankReceiptMulti"));
	}

	/**
	 * Receipt Against Multi Party, Multi Invoice (Dr/Cr)(Through Add button) -
	 * Receipt - Bank foreign Customer INR and Base Currency INR: Difference
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 46, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Multi Invoice (Dr/Cr)(Through Add button) - Receipt - Bank foreign Customer INR and Base Currency INR: Difference")
	public void CreateBankReceiptMultiPartyMultiInvoiceThroughAddButtonBankCurcyUSD() throws IOException {
		bankReceiptFunc
				.BRMultiPartyMultiInvoiceThroughAddButtonBankCurcyUSD(webDB.getDataFromProperties("BankReceiptMulti"));
	}

	/**
	 * Receipt Against Multi Party, Multi Invoice (Dr/Cr) (Get document) - Receipt -
	 * Bank foreign Customer INR and Base Currency INR: Difference
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 47, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Multi Invoice (Dr/Cr) (Get document) - Receipt - Bank foreign Customer INR and Base Currency INR: Difference")
	public void CreateBankReceiptMultiPartyMultiInvoiceThroughGetDocumentBankCurcyUSD() throws IOException {
		bankReceiptFunc
				.BRMultiPartyMultiInvoiceThroughGetDocumentBankUSD(webDB.getDataFromProperties("BankReceiptMulti"));
	}

	/**
	 * Receipt Against Multi Party, Multi Invoice (Dr/Cr) (Pending) - Receipt - Bank
	 * foreign Customer INR and Base Currency INR: Difference
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 48, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Multi Invoice (Dr/Cr) (Pending) - Receipt - Bank foreign Customer INR and Base Currency INR: Difference")
	public void CreateBankReceiptMultiPartyMultiInvoiceThroughPendingDocsBankCurcyUSD() throws IOException {
		bankReceiptFunc.BRMultiPartyMultiInvoiceThroughPendingDocsBankCurcyUSD(
				webDB.getDataFromProperties("BankReceiptMulti"));
	}

	/**
	 * Receipt Against Multi Party, Multi Invoice with New reference (Dr/Cr)(Through
	 * - Receipt - Bank foreign Customer INR and Base Currency INR: Difference Add
	 * button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 49, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Multi Invoice with New reference (Dr/Cr)(Through Add button) - Receipt - Bank foreign Customer INR and Base Currency INR: Difference")
	public void CreateBankReceiptMultiPartyMultiInvoiceThroughAddButtonWithNewRefBankCurcyUSD() throws IOException {
		bankReceiptFunc.BRMultiPartyMultiInvoiceThroughAddButtonWithNewRefBankCurcyUSD(
				webDB.getDataFromProperties("BankReceiptMultiNewRef"));
	}

	/**
	 * Receipt Against Multi Party, Multi Invoice with New reference (Dr/Cr)(Get
	 * document) - Receipt - Bank foreign Customer INR and Base Currency INR:
	 * Difference
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 50, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Multi Invoice with New reference (Dr/Cr)(Get document) - Receipt - Bank foreign Customer INR and Base Currency INR: Difference")
	public void CreateBankReceiptMultiPartyMultiInvoiceThroughGetDocumentWithNewRefBankCurcyUSD() throws IOException {
		bankReceiptFunc.BRMultiPartyMultiInvoiceThroughGetDocumentWithNewrefBankCurcyUSD(
				webDB.getDataFromProperties("BankReceiptMultiNewRef"));
	}

	/**
	 * Receipt Against Multi Party, Multi Invoice with New reference - Receipt -
	 * Bank foreign Customer INR and Base Currency INR: Difference (Dr/Cr)(Pending)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 51, groups = {
			"Bank Receipt" }, description = "Receipt Against Multi Party, Multi Invoice with New reference (Dr/Cr)(Pending) - Receipt - Bank foreign Customer INR and Base Currency INR: Difference")
	public void CreateBankReceiptMultiPartyMultiInvoiceThroughPendingDocsWithNewRefBankCurcyUSD() throws IOException {
		bankReceiptFunc.BRMultiPartyMultiInvoiceThroughPendingDocsWithNewRefBankCurcyUSD(
				webDB.getDataFromProperties("BankReceiptMultiNewRef"));
	}

	/**
	 * Bank receipt With Charges - Receipt - Bank foreign Customer INR and Base
	 * Currency INR: Difference
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 52, groups = {
			"Bank Receipt" }, description = "Bank receipt With Charges - Receipt - Bank foreign Customer INR and Base Currency INR: Difference")
	public void CreateBankReceiptWithChargesBankCurcyUSD() throws IOException {
		bankReceiptFunc.BankReceiptSIWithChargesBankCurcyUSD(webDB.getDataFromProperties("BankReceiptCharges"));
	}

	/**
	 * Receipt With Expenses/Revenue Account without Gst
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 53, groups = { "Bank Receipt" }, description = "Receipt With Expenses/Revenue Account without Gst")
	public void CreateBankReceiptWithExpenseAndRevenueWithoutGSTBankCurcyUSD() throws IOException {
		bankReceiptFunc.BankReceiptWithExpAndRevenueWithoutGST(webDB.getDataFromProperties("BankReceiptRevExp"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
	}

	/**
	 * Receipt Against single Party, Single invoice(Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 54, groups = {
			"Bank Receipts" }, description = "Receipt Against single Party, Single invoice(Through Add button)")
	public void CreateAdvanceBankReceiptSinglePartySingleSOThroughAddButton() throws IOException {
		bankReceiptFunc.ABRSinglePartySingleSOThroughAddButton(webDB.getDataFromProperties("BankReceipt"));
	}

	/**
	 * Receipt Against single Party, Single Invoice (Get document )
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 55, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Single Invoice (Get document )")
	public void CreateAdvanceBankReceiptSinglePartySingleSOThroughGetDocument() throws IOException {
		bankReceiptFunc.ABRSinglePartySingleSOThroughGetDocument(webDB.getDataFromProperties("BankReceipt"));
	}

	/**
	 * Receipt Against single Party, Single Invoice (Pending )
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 56, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Single Invoice (Pending )")
	public void CreateAdvanceBankReceiptSinglePartySingleSOThroughPendingDocs() throws IOException {
		bankReceiptFunc.ABRSinglePartySingleSOThroughPendingDocs(webDB.getDataFromProperties("BankReceiptAdv"));
	}

	/**
	 * Receipt Against single Party, Single invoice(Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 57, groups = {
			"Bank Receipts" }, description = "Receipt Against single Party, Single invoice(Through Add button)")
	public void CreateAdvanceBankReceiptSinglePartyMultipleSOThroughAddButton() throws IOException {
		bankReceiptFunc.ABRSinglePartyMultiSOThroughAddButton(webDB.getDataFromProperties("BankReceipt"));
	}

	/**
	 * Receipt Against single Party, Single Invoice (Get document )
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 58, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Single Invoice (Get document )")
	public void CreateAdvanceBankReceiptSinglePartyMultipleSOThroughGetDocument() throws IOException {
		bankReceiptFunc.ABRSinglePartyMultiSOThroughGetDocument(webDB.getDataFromProperties("BankReceipt"));
	}

	/**
	 * Receipt Against single Party, Single Invoice (Pending )
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 59, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Single Invoice (Pending )")
	public void CreateAdvanceBankReceiptSinglePartyMultipleSOThroughPendingDocs() throws IOException {
		bankReceiptFunc.ABRSinglePartyMultiSOThroughPendingDocs(webDB.getDataFromProperties("BankReceiptAdv"));
	}

	/**
	 * Receipt - Bank, Customer and Base Currency: Same (USD) Party, Single
	 * invoice(Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 60, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Single invoice(Through Add button) - Receipt - Bank, Customer and Base Currency: Same (USD)")
	public void CreateAdvanceBankReceiptSinglePartySingleSOThroughAddButtonUSD() throws IOException {
		bankReceiptFunc.ABRSinglePartySingleSOThroughAddButtonUSD(webDB.getDataFromProperties("BankReceiptUSD"));
	}

	/**
	 * Receipt - Bank, Customer and Base Currency: Same (USD) Receipt Against single
	 * Party, Single Invoice (Get document )
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 61, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Single Invoice (Get document ) - Receipt - Bank, Customer and Base Currency: Same (USD)")
	public void CreateAdvanceBankReceiptSinglePartySingleSOThroughGetDocumentUSD() throws IOException {
		bankReceiptFunc.ABRSinglePartySingleSOThroughGetDocumentUSD(webDB.getDataFromProperties("BankReceiptUSD"));
	}

	/**
	 * Receipt - Bank, Customer and Base Currency: Same (USD) Receipt Against single
	 * Party, Single Invoice (Pending )
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 62, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Single Invoice (Pending ) - Receipt - Bank, Customer and Base Currency: Same (USD)")
	public void CreateAdvanceBankReceiptSinglePartySingleSOThroughPendingDocsUSD() throws IOException {
		bankReceiptFunc.ABRSinglePartySingleSOThroughPendingDocsUSD(webDB.getDataFromProperties("BankReceiptUSD"));
	}

	/**
	 * Receipt - Bank, Customer and Base Currency: Same (USD) Party, Single
	 * invoice(Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 63, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Single invoice(Through Add button) - Receipt - Bank, Customer and Base Currency: Same (USD)")
	public void CreateAdvanceBankReceiptSinglePartyMultiSOThroughAddButtonUSD() throws IOException {
		bankReceiptFunc.ABRSinglePartyMultiSOThroughAddButtonUSD(webDB.getDataFromProperties("BankReceiptUSD"));
	}

	/**
	 * Receipt - Bank, Customer and Base Currency: Same (USD) Receipt Against single
	 * Party, Single Invoice (Get document )
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 64, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Single Invoice (Get document ) - Receipt - Bank, Customer and Base Currency: Same (USD)")
	public void CreateAdvanceBankReceiptSinglePartyMultiSOThroughGetDocumentUSD() throws IOException {
		bankReceiptFunc.ABRSinglePartyMultiSOThroughGetDocumentUSD(webDB.getDataFromProperties("BankReceiptUSD"));
	}

	/**
	 * Receipt - Bank, Customer and Base Currency: Same (USD) Receipt Against single
	 * Party, Single Invoice (Pending )
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 65, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Single Invoice (Pending ) - Receipt - Bank, Customer and Base Currency: Same (USD)")
	public void CreateAdvanceBankReceiptSinglePartyMultiSOThroughPendingDocsUSD() throws IOException {
		bankReceiptFunc.ABRSinglePartyMultiSOThroughPendingDocsUSD(webDB.getDataFromProperties("BankReceiptUSD"));
	}

	/**
	 * Receipt Against single Party, Single invoice(Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 66, groups = {
			"Bank Receipts" }, description = "Advance against SO single Sales order (Through Add button)")
	public void CreateAdvanceBankReceiptSinglePartySingleSOThroughAddButtonBankUSD() throws IOException {
		bankReceiptFunc.ABRSinglePartySingleSOThroughAddButtonBankUSD(webDB.getDataFromProperties("BankReceiptAdv"));
	}

	/**
	 * Receipt Against single Party, Single Invoice (Get document )
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 67, groups = {
			"Bank Receipt" }, description = "Advance against SO single Sales order (Get document)")
	public void CreateAdvanceBankReceiptSinglePartySingleSOThroughGetDocumentBankUSD() throws IOException {
		bankReceiptFunc.ABRSinglePartySingleSOThroughGetDocumentBankUSD(webDB.getDataFromProperties("BankReceiptAdv"));
	}

	/**
	 * Receipt Against single Party, Single Invoice (Pending )
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 68, groups = { "Bank Receipt" }, description = "Advance against SO single Sales order (Pending)")
	public void CreateAdvanceBankReceiptSinglePartySingleSOThroughPendingDocsBankUSD() throws IOException {
		bankReceiptFunc.ABRSinglePartySingleSOThroughPendingDocsBankUSD(webDB.getDataFromProperties("BankReceiptAdv"));
	}

	/**
	 * Receipt Against single Party, Single invoice(Through Add button)
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 69, groups = {
			"Bank Receipts" }, description = "Receipt Against single Party, Single invoice(Through Add button)")
	public void CreateAdvanceBankReceiptSinglePartyMultipleSOThroughAddButtonBankUSD() throws IOException {
		bankReceiptFunc.ABRSinglePartyMultiSOThroughAddButtonBankUSD(webDB.getDataFromProperties("BankReceiptAdv"));
	}

	/**
	 * Receipt Against single Party, Single Invoice (Get document )
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 70, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Single Invoice (Get document )")
	public void CreateAdvanceBankReceiptSinglePartyMultipleSOThroughGetDocumentBankUSD() throws IOException {
		bankReceiptFunc.ABRSinglePartyMultiSOThroughGetDocumentBankUSD(webDB.getDataFromProperties("BankReceiptAdv"));
	}

	/**
	 * Receipt Against single Party, Single Invoice (Pending )
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 71, groups = {
			"Bank Receipt" }, description = "Receipt Against single Party, Single Invoice (Pending )")
	public void CreateAdvanceBankReceiptSinglePartyMultipleSOThroughPendingDocsBankUSD() throws IOException {
		bankReceiptFunc.ABRSinglePartyMultiSOThroughPendingDocsBankUSD(webDB.getDataFromProperties("BankReceiptAdv"));
	}

	/**
	 * Advance Against New Reference
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 72, groups = { "Bank Receipt" }, description = "Advance Against New Reference")
	public void CreateAdvanceBankReceiptWithNewRef() throws IOException {
		bankReceiptFunc.AdvBankReceiptWithNewRef(webDB.getDataFromProperties("BankReceiptNewRef"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"), commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * Advance Against New Reference USD
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 73, groups = { "Bank Receipt" }, description = "Advance Against New Reference")
	public void CreateAdvanceBankReceiptWithNewRefUSD() throws IOException {
		bankReceiptFunc.AdvBankReceiptWithNewRef(webDB.getDataFromProperties("BRAdvUSDNR"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * Advance Against New Reference Bank Curcy USD
	 * 
	 * @author Harikrishna - RMITS
	 * @throws IOException
	 */
	@Author("Harikrishna-RMITS")
	@Test(priority = 74, groups = { "Bank Receipt" }, description = "Advance Against New Reference Bank Curcy USD")
	public void CreateAdvanceBankReceiptWithNewRefBankCurcyUSD() throws IOException {
		bankReceiptFunc.AdvBankReceiptWithNewRef(webDB.getDataFromProperties("BankReceiptNewRef"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
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
		bankReceiptFunc.verifyAfterMethod();
		previousStatus = result.getStatus();
		if (previousStatus == ITestResult.FAILURE || previousStatus == ITestResult.SKIP) {
			commonFunc.verifyAfterMethod();
		}
		commonFunc.enterAuthorNamesInExtentReport(method);
	}

}
