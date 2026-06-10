package commonfunctions.financials;

/**
 * @@author Harikrishna - RMITS
 * @company RoadMap IT Solutions October 16, 2025
 */
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonfunctions.supplychainmanagement.DirectGrnPageFunctions;
import commonfunctions.supplychainmanagement.SalesInvoiceFunctions;
import commonfunctions.supplychainmanagement.SalesOrderFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.financials.BankPaymentLocators;
import commonlocators.financials.BankReceiptLocators;
import commonlocators.financials.CashReceiptLocators;
import commonlocators.financials.CashReceiptLocators;
import commonlocators.financials.CashReceiptLocators;
import commonlocators.financials.PaymentReceiptsLocators;
import commonlocators.financials.PurchaseBillsLocators;
import commonlocators.supplychainmanagement.MaterialIssuanceVoucherLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import commonlocators.supplychainmanagement.SalesOrderLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class CashReceiptFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();
	public CommonFunctions commonFunc = new CommonFunctions();
	static ApprovalPageFunctions approvalPageFunc = new ApprovalPageFunctions();
	static SalesOrderFunctions salesOrdFunc = new SalesOrderFunctions();
	static BankPaymentFunctions bankpaymentfunc = new BankPaymentFunctions();
	static SalesInvoiceFunctions salesInvoiceFunc = new SalesInvoiceFunctions();
	static DirectGrnPageFunctions Grnfunc = new DirectGrnPageFunctions();
	static BankReceiptFunctions bankreceiptfunc = new BankReceiptFunctions();

	LocalDate date = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	String Today = date.format(formatter);
	LocalDateTime myDateObj = LocalDateTime.now().plusDays(1);
	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	String tomorrowDate = myDateObj.format(myFormatObj);
	String[][] CashReceipts = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
	ExcelUtils excelUtils = new ExcelUtils();
	String CRFilepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
			+ "Financials.xlsx";
	ArrayList<String> PBDocumentNumber = new ArrayList<String>();
//	SOMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
//	for (int i = 4; i < SOMultipleline.length; i++) {

	/**
	 * This Method Is Used To Create The Cash Receipt Header Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param DocumentMode
	 * @param CashierName
	 * @param VoucherDate
	 * @param Prefix
	 */
	public void createCashReceiptEntry(String DocumentMode, String CashierName, String VoucherDate, String Prefix) {
		flag = webDB.waitForElement(CashReceiptLocators.CashModeDropDown, ElementType.Xpath);
		if (flag) {
			webDB.selectDropDownByText(CashReceiptLocators.CashModeDropDown, DocumentMode, ElementType.Xpath);
			logs.logging("INFO", "Document Mode Changed Successfully to " + DocumentMode);
			commonFunc.searchAndSelectValueInFilters(CashReceiptLocators.CashLovbutton, CashierName);
			webDB.pressTabKey();
			String GetTextVoucherDate = webDB.getDomPropertyFromElement(CashReceiptLocators.CashVoucherDateinputField,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			if (!GetTextVoucherDate.equals(VoucherDate)) {
				webDB.clearTextField(CashReceiptLocators.CashVoucherDateinputField, ElementType.Xpath);
				webDB.sendTextToAnElement(CashReceiptLocators.CashVoucherDateinputField, VoucherDate,
						ElementType.Xpath);
				logs.logging("INFO", "Cash Receipt Voucher Date Is inserted Successfully");
				webDB.pressTabKey();
			}
			commonFunc.searchAndSelectValueInFilters(CashReceiptLocators.CashPrefixLovButton, Prefix);
			webDB.pressTabKey();
			flag = webDB.waitForElement(CashReceiptLocators.CashSaveButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CashReceiptLocators.CashSaveButton, ElementType.Xpath);
				flag = webDB.waitForElement(CashReceiptLocators.CashPostButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Save Button Is Clicked And Cash Receipt Document is Created Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method is Used To Get the Cash Payment / Receipt Document Number
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void getCashPaymentOrReceiptDocumentNumber() {
		flag = webDB.waitForElement(CashReceiptLocators.CashDocumentNumberField, ElementType.Xpath);
		if (flag) {
			String GetTextBankPaymentOrReceiptDocNumber = webDB
					.getTextFromElement(CashReceiptLocators.CashDocumentNumberField, ElementType.Xpath);
			VariableHandler.setCashPaymentOrReceiptDocNumber(GetTextBankPaymentOrReceiptDocNumber);
			logs.logging("INFO", "Get Text Cash Payment / Receipt Document Number Is "
					+ VariableHandler.getCashPaymentOrReceiptDocNumber());
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used to Verify Bank Payment Header details With DataBase
	 * Values
	 * 
	 * @author Harikrishna - RMITS
	 * 
	 * @param BankName
	 * @param Prefix
	 * @param Curcy
	 */
	public void verifyCashReceiptDetailsWithDB(String CashierName, String Prefix, String Curcy) {
		salesOrdFunc.verifyAssertionBothValues(
				dbCommonFunctions.BankPaymentHeaderDetails(VariableHandler.getCashPaymentOrReceiptDocNumber(),
						commonFunc.getDataFromTestDataSheet("BPDBBankColumnName")),
				CashierName);

		salesOrdFunc.verifyAssertionBothValues(
				dbCommonFunctions.BankPaymentHeaderDetails(VariableHandler.getCashPaymentOrReceiptDocNumber(),
						commonFunc.getDataFromTestDataSheet("BPDBPrefixColumnName")),
				Prefix);

		salesOrdFunc.verifyAssertionBothValues(
				dbCommonFunctions.BankPaymentHeaderDetails(VariableHandler.getCashPaymentOrReceiptDocNumber(),
						commonFunc.getDataFromTestDataSheet("BPDBCurcyColumnName")),
				Curcy);
	}

	/**
	 * This Method Is Used To Click the Line Wise Add Button In The Bank Payment
	 * Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickCRLineAddButton() {
		flag = webDB.waitForElement(CashReceiptLocators.BankReceiptLineWiseAddButton, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(CashReceiptLocators.BankReceiptLineWiseAddButton, ElementType.Xpath);
			flag = webDB.waitForElement(CashReceiptLocators.BankLineGLAccountField, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Bank Payment Line Wise Add Button Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add the Bank Payment Line Details
	 * 
	 * @author Harikrishna - RMITS
	 * @param GLAccount
	 * @param CPCcode
	 */
	public void addCashReceiptLineDetails(String GLAccount, String CPCcode) {
		flag = webDB.waitForElement(CashReceiptLocators.BankLineGLAccountField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashReceiptLocators.BankLineGLAccountField, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(CashReceiptLocators.BankLineGLAccountLovButton, GLAccount);
			webDB.pressTabKey();
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(CashReceiptLocators.BankLineCPCField, ElementType.Xpath);
			if (flag) {
//				commonFunc.checkRefreshCellsDisappear();
				String GetTextCPC = webDB.getTextFromElement(CashReceiptLocators.BankLineCPCField, ElementType.Xpath);
				logs.logging("INFO", "Get Text CPC Code Is : " + GetTextCPC);
				if (!GetTextCPC.equals(CPCcode)) {
					webDB.clickAnElement(CashReceiptLocators.BankLineCPCField, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(CashReceiptLocators.BankLineCPCLovButton, CPCcode);
					commonFunc.checkRefreshCellsDisappear();
					webDB.pressTabKey();
				}
				commonFunc.checkRefreshCellsDisappear();
				webDB.pressTabKey();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methos Is Used To Enter Value In The Amount In TC Field
	 * 
	 * @param ChargeAmount
	 */
	public void enterValueInAmtInTCField(String ChargeAmount) {
		salesOrdFunc.verifyClickAndEnterValueInTableFields(CashReceiptLocators.CashReceiptLineATCField,
				CashReceiptLocators.BankReceiptAmountInTcInputField, ChargeAmount);
		webDB.pressTabKey();
	}

	public void chooseLineWiseDRCRInCR(String DRCR) {
		flag = webDB.waitForElement(CashReceiptLocators.CashReceiptCRDRField, ElementType.Xpath);
		if (flag) {
//			webDB.clickAnElement(BankReceiptLocators.BanReceiptCRDRField, ElementType.Xpath);
			commonFunc.hardWait(200);
			webDB.selectDropDownByText(CashReceiptLocators.BankReceiptLineCRDRDropDown, DRCR, ElementType.Xpath);
			webDB.pressTabKey();
//			commonFunc.checkRefreshCellsDisappear();
			String GetTextDRCR = webDB.getTextFromElement(CashReceiptLocators.CashReceiptCRDRField, ElementType.Xpath);
			if (DRCR.equals(GetTextDRCR)) {
				logs.logging("INFO", "Line Wise DR / CR Is Choosen Successfully In Bank Receipt");
			}
		}
	}

	/**
	 * This Method is Used to Click The Line Wise Save Button In the Bank Payment
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickCRLineWiseSaveButton() {
		flag = webDB.waitForElement(CashReceiptLocators.BankLineWiseSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashReceiptLocators.BankLineWiseSaveButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(CashReceiptLocators.BankLineWiseDeleteButton, ElementType.CssSelector);
			if (flag) {
				logs.logging("INFO", "Bank Receipt Line Wise Save button Is clicked Successfully");
				commonFunc.hardWait(500);
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Line Wise Add Button In The Bank Payment
	 * Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickCRLineWiseAddButton(int i) {
		flag = webDB.waitForElement(CashReceiptLocators.CashLineAddButton(i), ElementType.CssSelector);
		if (flag) {
			webDB.clickAnElement(CashReceiptLocators.CashLineAddButton(i), ElementType.CssSelector);
			flag = webDB.waitForElement(CashReceiptLocators.BankPendingReceivablesButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Bank Receipt Line Wise Add Button Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Pending Party Documents In Bank Payment
	 * Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickPendingReceivablesInCR() {
		flag = webDB.waitForElement(CashReceiptLocators.BankReceiptPendingReceivablesButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashReceiptLocators.BankReceiptPendingReceivablesButton, ElementType.Xpath);
			flag = webDB.waitForElement(CashReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Bank Receipt Pending Receivables Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Check GLM Control Of BPV Auto Clear Flag And verify
	 * Bank Payment Document Status
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void VerifyCRVDocumentStatus() {
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("CRVApprovedStatus"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		logs.logging("INFO",
				"Verified Cash Receipt Voucher Document Status Is Approved Against This Cash Receipt Voucher Document "
						+ VariableHandler.getCashPaymentOrReceiptDocNumber());
		;
	}

	/**
	 * This Method Is Used to Select the Sales Invoice pending Records In the
	 * Pending Receivables In the bank Receipt Screen
	 * 
	 * @author Harikrishna _ RMITS
	 * @param PartyName
	 * @param VoucherNumber.get(i)
	 */
	public void selectSalesInvoiceDocumentInPendingBankReceipt(String PartyName, ArrayList<String> VoucherNumber,
			String SheetName) {
		flag = webDB.switchToFrame(CashReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
		if (flag) {
			String GetTextPartyName = webDB.getDomPropertyFromElement(
					CashReceiptLocators.BankReceiptPendingReceivablesPartyNameInputField, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			if (GetTextPartyName.equals(PartyName)) {
				for (int i = 1; i <= VoucherNumber.size(); i++) {
					commonFunc.searchAndSelectValueInFilters(CashReceiptLocators.BankReceiptDocNumberLovButton,
							VoucherNumber.get(i - 1));
					webDB.switchToFrame(CashReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
					flag = webDB.waitForElement(CashReceiptLocators.BankReceiptPendingReceivablesSearchButton,
							ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.doubleClickAnElement(CashReceiptLocators.BankReceiptPendingReceivablesSearchButton,
								ElementType.Xpath);
						flag = webDB.waitForElement(CashReceiptLocators.BRPendingReceivablesDocumentSelectCheckBox(
								VoucherNumber.get(i - 1)), ElementType.Xpath);
						// TODO
//						verifyPendingReceivablesSIDocumentWithDB(SheetName, VoucherNumber.get(i));
						verifyPendingReceivablesSIDocumentThroughGetDocWithDB(SheetName, VoucherNumber.get(i - 1), i);
						// TODO
						if (flag) {
							commonFunc.checkRefreshCellsDisappear();
							webDB.clickAnElement(CashReceiptLocators.BRPendingReceivablesDocumentSelectCheckBox(
									VoucherNumber.get(i - 1)), ElementType.Xpath);
							commonFunc.hardWait(6000);
							flag = webDB.waitForElement(CashReceiptLocators.BankReceiptPendingReceivableScreenOkButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "In Pending Receivables Documents This Voucher Number "
										+ VoucherNumber.get(i - 1) + " Is Selected Successfully");
							}
						}
					}
				}
				webDB.moveToElement(CashReceiptLocators.BankReceiptPendingReceivableScreenOkButton, ElementType.Xpath);
				webDB.javaScriptClickAnElement(CashReceiptLocators.BankReceiptPendingReceivableScreenOkButton,
						ElementType.Xpath);
				commonFunc.hardWait(3000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(CashReceiptLocators.CashReceiptHeaderCreateJournalButton,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO",
							"OK Button Is Clicked Successfully In The Bank Receipt Pending Receivables Documents");
				}
			} else {
				logs.logging("INFO",
						"Bank Receipt Line Wise Party Name And Pending Receivables Party Name Is not Getting Matched");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Pending Receivables Through get Document With
	 * DB
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param VoucherNumber
	 * @param i1
	 */
	public void verifyPendingReceivablesSIDocumentThroughGetDocWithDB(String SheetName, String VoucherNumber, int i1) {
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String PartyID = CashReceipts[i][2];
			String PartyName = CashReceipts[i][3];
			String Entity = CashReceipts[i][5];
			String CPCDesc = CashReceipts[i][6];
			String Curcy = CashReceipts[i][10];
			String EXRate = CashReceipts[i][11];
			String Location = CashReceipts[i][23];
			String Unit = CashReceipts[i][24];
			String DrCr = CashReceipts[i][25];
			if (!PartyID.equals("")) {
				salesOrdFunc
						.verifyAssertionBothValues(
								dbCommonFunctions.BankReceiptPendingReceivablesDetails(VoucherNumber,
										commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesPartyName")),
								PartyName);
				logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
						+ " PartyName Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankReceiptPendingReceivablesDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesLocationID")),
						dbCommonFunctions.SIHeaderDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("CRDBLocationColumn")));
				logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
						+ " Location ID Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankReceiptPendingReceivablesDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesUnitID")),
						dbCommonFunctions.SIHeaderDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("CRDBUnitColumn")));
				logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
						+ " Unit ID Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankReceiptPendingReceivablesDetails(
						VoucherNumber, commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesCurcy")), Curcy);
				logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
						+ " Currency Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankReceiptPendingReceivablesDetails(
						VoucherNumber, commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesExRate")), EXRate);
				logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
						+ " EX.Rate Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankReceiptPendingReceivablesDetails(
						VoucherNumber, commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesDROrCR")), DrCr);
				logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
						+ " Dr / Cr Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankReceiptPendingReceivablesDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesInvAmt")),
						dbCommonFunctions.SIHeaderDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
						+ " Invoice Amount Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankReceiptPendingReceivablesDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesDueDays")),
						dbCommonFunctions.SOPaymentTermAdvPercentage(
								commonFunc.getDataFromTestDataSheet("SOPaymentTerm"),
								commonFunc.getDataFromTestDataSheet("PaymentTermDueDays")));
				logs.logging("INFO", "In Pending Receivables For This Document Number "
						+ VariableHandler.getMultipleSIDocs().get(i - 1) + " Due Days Is getting Matched With DB");

				salesOrdFunc
						.verifyAssertionBothValues(
								dbCommonFunctions.BankReceiptPendingReceivablesDetails(VoucherNumber,
										commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesDocNumber")),
								VoucherNumber);
				logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
						+ " Source Document Is getting Matched With DB");
			}
		}
	}

	/**
	 * This Method Is Used To Verify Bank Payments Line Details With DB
	 * 
	 * @author Harikrishna - RMITS
	 * @param GLAccountDesc
	 * @param PartyName
	 * @param Entity
	 * @param CPCDesc
	 * @param AmountInTC
	 * @param DebitorsORCreditors
	 * @param AmountInBC
	 * @param AssesableValue
	 */
	public void verifyCashReceiptLineDetailsWithDB(String SheetName, int i1) {

		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String GLAccountDesc = CashReceipts[i][1];
			String PartyID = CashReceipts[i][2];
			String PartyName = CashReceipts[i][3];
			String Entity = CashReceipts[i][5];
			String CPCDesc = CashReceipts[i][6];
			String LineDrCr = CashReceipts[i][9];
			String Curcy = CashReceipts[i][10];
			String EXRate = CashReceipts[i][11];
			String AmtInTC = CashReceipts[i][12];
			String AmtInBC = CashReceipts[i][13];
			String HSNCode = CashReceipts[i][14];
			String AssesableValue = CashReceipts[i][16];
			String CGST = CashReceipts[i][17];
			String SGST = CashReceipts[i][18];
			String IGST = CashReceipts[i][19];
			String UTGST = CashReceipts[i][20];
			String CESSPCT = CashReceipts[i][21];
			String CESSAMT = CashReceipts[i][22];
			String Location = CashReceipts[i][23];
			String Unit = CashReceipts[i][24];
			String PendingDrCr = CashReceipts[i][25];
			String TotAmt = CashReceipts[i][28];
			if (!PartyID.equals("")) {
				String SeqNo = String.valueOf(i);
				System.out.println(SeqNo);
				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getCashPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBGLAccountDescColumnName")),
						GLAccountDesc);
				logs.logging("INFO",
						" Line Wise GL Account Desc Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getCashPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getCashPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBSupplierDescColumnName")),
						PartyName);
				logs.logging("INFO",
						" Line Wise Party Name Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getCashPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getCashPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBEntityColumnName")),
						Entity);
				logs.logging("INFO",
						" Line Wise Entity Name Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getCashPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getCashPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBCPCcodeDescColumnName")),
						CPCDesc);
				logs.logging("INFO",
						" Line Wise CPC Desc Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getCashPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getCashPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBAmountTCColumnName")),
						AmtInTC);
				logs.logging("INFO",
						" Line Wise Amount In Transaction Currency Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getCashPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getCashPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBDCColumnName")),
						LineDrCr);
				logs.logging("INFO",
						" Line Wise Dr / Cr Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getCashPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getCashPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBBCColumnName")),
						AmtInBC);
				logs.logging("INFO",
						" Line Wise Amount In Base Currency Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getCashPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getCashPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBAssesableColumnName")),
						AssesableValue);
				logs.logging("INFO",
						" Line Wise Amount In Tax Assesable Value Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getCashPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getCashPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBExhangeRateColumnName")),
						EXRate);
				logs.logging("INFO",
						" Line Wise Exchange Rate Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getCashPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getCashPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBLineCurrencyColumnName")),
						Curcy);
				logs.logging("INFO",
						" Line Wise Currency Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getCashPaymentOrReceiptDocNumber());
				if (SheetName.equals("BankReceiptRevExpGST")) {
					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getCashPaymentOrReceiptDocNumber(),
									SeqNo, commonFunc.getDataFromTestDataSheet("BRDBLineHSNCode")),
							HSNCode);
					logs.logging("INFO",
							" Line Wise HSN Code Is Matched With DataBase Values Against This Bank Receipt Document "
									+ VariableHandler.getCashPaymentOrReceiptDocNumber());

					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getCashPaymentOrReceiptDocNumber(),
									SeqNo, commonFunc.getDataFromTestDataSheet("BRDBLineIGSTAmt")),
							IGST);
					logs.logging("INFO",
							" Line Wise IGST Amount Is Matched With DataBase Values Against This Bank Receipt Document "
									+ VariableHandler.getCashPaymentOrReceiptDocNumber());

					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getCashPaymentOrReceiptDocNumber(),
									SeqNo, commonFunc.getDataFromTestDataSheet("BRDBLineCGSTAmt")),
							CGST);
					logs.logging("INFO",
							" Line Wise CGST Amount Is Matched With DataBase Values Against This Bank Receipt Document "
									+ VariableHandler.getCashPaymentOrReceiptDocNumber());

					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getCashPaymentOrReceiptDocNumber(),
									SeqNo, commonFunc.getDataFromTestDataSheet("BRDBLineSGSTAmt")),
							SGST);
					logs.logging("INFO",
							" Line Wise SGST Amount Is Matched With DataBase Values Against This Bank Receipt Document "
									+ VariableHandler.getCashPaymentOrReceiptDocNumber());

					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getCashPaymentOrReceiptDocNumber(),
									SeqNo, commonFunc.getDataFromTestDataSheet("BRDBLineUTGSTAmt")),
							UTGST);
					logs.logging("INFO",
							" Line Wise UTGST Amount Is Matched With DataBase Values Against This Bank Receipt Document "
									+ VariableHandler.getCashPaymentOrReceiptDocNumber());

					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getCashPaymentOrReceiptDocNumber(),
									SeqNo, commonFunc.getDataFromTestDataSheet("BRDBLineCESSPercent")),
							CESSPCT);
					logs.logging("INFO",
							" Line Wise CESS Percentage Is Matched With DataBase Values Against This Bank Receipt Document "
									+ VariableHandler.getCashPaymentOrReceiptDocNumber());

					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getCashPaymentOrReceiptDocNumber(),
									SeqNo, commonFunc.getDataFromTestDataSheet("BRDBLineCESSAmt")),
							CESSAMT);
					logs.logging("INFO",
							" Line Wise CESS Amount Is Matched With DataBase Values Against This Bank Receipt Document "
									+ VariableHandler.getCashPaymentOrReceiptDocNumber());
				}
			}
		}
	}

	/**
	 * This Method Is Used To Click The Create JOurnal Button In The Header Of the
	 * Bank Receipt Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickCreateJournalButtonInCR() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(CashReceiptLocators.CashReceiptHeaderCreateJournalButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashReceiptLocators.CashReceiptHeaderCreateJournalButton, ElementType.Xpath);
			commonFunc.hardWait(500);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(CashReceiptLocators.BankHeaderViewJournalButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Create Journal Button Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Post Button In The Bank Payment Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickPostButton() {
		flag = webDB.waitForElement(CashReceiptLocators.CashPostButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashReceiptLocators.CashPostButton, ElementType.Xpath);
			flag = webDB.waitForElement(CashReceiptLocators.BankWorkFlowApprovalFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Post Button Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Check The Bank Payment Document Status With DB
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void checkCRDocumentStatusWithDB(String ExpectedStatus) {
		String BPVStatus = dbCommonFunctions.BankPaymentHeaderDetails(
				VariableHandler.getCashPaymentOrReceiptDocNumber(),
				commonFunc.getDataFromTestDataSheet("BPDBHeaderStatusColumnName"));
		salesOrdFunc.verifyAssertionBothValues(BPVStatus, ExpectedStatus);
		logs.logging("INFO", "Verified Bank Payment Voucher Document Status Is " + BPVStatus
				+ " Against This Bank Payment Document " + VariableHandler.getCashPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Click The Get Document Button In The Header of The
	 * Bank Payment Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickGetDocumentInCRHeader() {
		flag = webDB.waitForElement(CashReceiptLocators.BankPaymentGetDocumentButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashReceiptLocators.BankPaymentGetDocumentButton, ElementType.Xpath);
			flag = webDB.waitForElement(CashReceiptLocators.CashReceiptPendingReceivablesButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Get Document Button In The Bank Receipt Header Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Pending Party Documents In Bank Payment
	 * Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickPendingReceivablesInGetDocumentCR() {
		flag = webDB.waitForElement(CashReceiptLocators.CashReceiptPendingReceivablesButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashReceiptLocators.CashReceiptPendingReceivablesButton, ElementType.Xpath);
			flag = webDB.waitForElement(CashReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Bank Receipt Pending Receivables Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methos Is Used To Choose Party Name And Select The Purchase Bills In The
	 * Pending Party Documents
	 * 
	 * @author Harikrishna - RMITS
	 * @param PartyName
	 * @param VoucherNumber
	 */
	public void chooseDocumentNumberAndClickSearchButtonInPendingReceivables(ArrayList<String> VoucherNumber,
			String SheetName) {
		flag = webDB.switchToFrame(CashReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
		if (flag) {
			for (int i = 1; i <= VoucherNumber.size(); i++) {
//				commonFunc.searchAndSelectValueInFilters(
//						BankPaymentLocators.BankPaymentPendingPartyDocumentPartyLovButton, PartyName);
//				webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
//				flag = webDB.waitForElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
//						ElementType.Xpath);
//				if (flag) {
//					webDB.clickAnElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
//							ElementType.Xpath);
//					commonFunc.checkRefreshCellsDisappear();
				commonFunc.searchAndSelectValueInFilters(CashReceiptLocators.BankReceiptDocNumberLovButton,
						VoucherNumber.get(i - 1));
				webDB.switchToFrame(CashReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(CashReceiptLocators.BankReceiptPendingReceivablesSearchButton,
						ElementType.Xpath);
				if (flag) {
					commonFunc.hardWait(500);
					commonFunc.checkRefreshCellsDisappear();
					webDB.clickAnElement(CashReceiptLocators.BankReceiptPendingReceivablesSearchButton,
							ElementType.Xpath);
					commonFunc.hardWait(1000);
					String GetTextDocumentNumber = webDB.getTextFromElement(
							CashReceiptLocators.PendingReceivablesDocumentNumberField, ElementType.Xpath);
					logs.logging("INFO", "Get Text Document Number Is " + GetTextDocumentNumber);
					if (GetTextDocumentNumber.equals(VoucherNumber.get(i - 1))) {
						flag = webDB.waitForElement(CashReceiptLocators.BRPendingReceivablesDocumentSelectCheckBox(
								VoucherNumber.get(i - 1)), ElementType.Xpath);
//						verifyPendingReceivablesSIDocumentWithDB(SheetName, VoucherNumber.get(i));
						verifyPendingReceivablesSIDocumentThroughGetDocWithDB(SheetName, VoucherNumber.get(i - 1), i);
//						verifyPendingReceivablesSIDocumentThroughGetDocWithDB123(SheetName, VoucherNumber.get(i-1),i);
						if (flag) {
							webDB.clickAnElement(CashReceiptLocators.BRPendingReceivablesDocumentSelectCheckBox(
									VoucherNumber.get(i - 1)), ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(3000);
							flag = webDB.waitForElement(
									CashReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "In Pending Receivables Documents This Voucher Number "
										+ VoucherNumber.get(i - 1) + " Is Selected Successfully");
							}
						}
					} else {
						logs.logging("INFO",
								"Bank Receipt Pending Receivables Line Wise Document Number And Pending Receivables Documents Number Is not Getting Matched");
					}
				}
			}
			commonFunc.hardWait(1500);
			flag = webDB.waitForElement(CashReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
					ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(CashReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
						ElementType.Xpath);
				webDB.javaScriptClickAnElement(CashReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
						ElementType.Xpath);
				commonFunc.hardWait(500);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(CashReceiptLocators.CashReceiptHeaderCreateJournalButton,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Ok Button Is Clicked Successfully In The Pending Receivables Screen");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Pending CR Docs Button In The Payment /
	 * Receipts Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickPendingDRDocsButton() {
		flag = webDB.waitForElement(CashReceiptLocators.PendingDRDocsButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashReceiptLocators.PendingDRDocsButton, ElementType.Xpath);
			flag = webDB.waitForElement(CashReceiptLocators.PendingReceivables, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending DR Docs Button Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The pending payables Button In The Pending CR
	 * Docs
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickPendingRecivablesButtonInPendDRDocs() {
		flag = webDB.waitForElement(CashReceiptLocators.PendingReceivables, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashReceiptLocators.PendingReceivables, ElementType.Xpath);
			flag = webDB.waitForElement(CashReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Payables Button Is Clicked Successfully In The Pending CR Docs");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is USed To Select One Document And Convert Into Bank / Cash
	 * Payment
	 * 
	 * @author Harikrishna - RMITS
	 * @param DocumentNumber
	 * @param Type
	 * @param PayMode
	 * @param BankName
	 */
	public void chooseDocumentNumberInPendingReceivablesScreen(ArrayList<String> DocumentNumber, String Type,
			String PayMode, String BankName, String CreationType, String SheetName) {
		flag = webDB.waitForElement(CashReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(CashReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
			for (int i = 1; i <= DocumentNumber.size(); i++) {
				commonFunc.searchAndSelectValueInFilters(
						CashReceiptLocators.PendingReceivablesDRDocumentNumberLOVButton, DocumentNumber.get(i - 1));
				webDB.switchToFrame(CashReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(CashReceiptLocators.BankReceiptPendingReceivablesSearchButton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CashReceiptLocators.BankReceiptPendingReceivablesSearchButton,
							ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					String GetTextDocumentNumber = webDB.getTextFromElement(
							CashReceiptLocators.PendingReceivablesDocumentNumberField, ElementType.Xpath);
//					String GetTextNoOfRows = webDB.getTextFromElement(CashReceiptLocators.RowSelectedButton,
//							ElementType.Xpath);
//					logs.logging("INFO", "Get Text No. Of Rows Is : " + GetTextNoOfRows);
//					if(GetTextNoOfRows.contains("1") || GetTextNoOfRows >)
					if (GetTextDocumentNumber.equals(DocumentNumber.get(i - 1))) {
//						verifyPendingReceivablesSIDocumentWithDB(SheetName, DocumentNumber.get(i));
//						verifyPendingReceivablesSIDocumentThroughGetDocWithDB(SheetName, DocumentNumber.get(i - 1), i);
						verifyPendingReceivablesSIDocumentThroughGetDocWithDB123(SheetName, DocumentNumber.get(i - 1),
								i);

						commonFunc.hardWait(1000);
						webDB.clickAnElement(CashReceiptLocators.BRPendingReceivablesDocumentSelectCheckBox(
								DocumentNumber.get(i - 1)), ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(1500);
						flag = webDB.waitForElement(CashReceiptLocators.PendingPayablesCreateButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "This Sales Invoice Document " + DocumentNumber.get(i - 1)
									+ " Is Selected Successfully");
							if (DocumentNumber.size() > 1) {
								commonFunc.checkRefreshCellsDisappear();
								webDB.clickAnElement(CashReceiptLocators.BankPendingPartyDocumentClearButton,
										ElementType.Xpath);
								flag = webDB.waitForElement(
										CashReceiptLocators.BankReceiptPendingReceivablesSearchButton,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(BankPaymentLocators.BankReceiptPendingReceivablesSearchButton,
											ElementType.Xpath);
									commonFunc.hardWait(1500);
									commonFunc.checkRefreshCellsDisappear();
								}
							}
						}
					} else {
						logs.logging("INFO", "Some Other Document Is Filtered");
					}
				}
			}
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(2000);
			webDB.moveToElement(CashReceiptLocators.PendingPayablesCreateButton, ElementType.Xpath);
			webDB.javaScriptClickAnElement(CashReceiptLocators.PendingPayablesCreateButton, ElementType.Xpath);
			logs.logging("INFO", "Create Button Is Clicked Successfully");
			commonFunc.checkRefreshCellsDisappear();
			if (!CreationType.equals("Single")) {
				flag = webDB.waitForElement(CashReceiptLocators.PendingReceivablesMergeLabelInPending,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CashReceiptLocators.PendingReceivablesMergeLabelInPending, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
				}
			}
			flag = webDB.waitForElement(CashReceiptLocators.PendingReceivablesTypeDropDown, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownByText(CashReceiptLocators.PendingReceivablesTypeDropDown, Type, ElementType.Xpath);
				logs.logging("INFO", "Type Successfully Choosen As " + Type);
				if (Type.equals("Bank")) {
					flag = webDB.waitForElement(CashReceiptLocators.PendingReceivablesPayModeDropDown,
							ElementType.Xpath);
					if (flag) {
						webDB.selectDropDownByText(CashReceiptLocators.PendingReceivablesPayModeDropDown, PayMode,
								ElementType.Xpath);
						logs.logging("INFO", "Pay Mode Successfully Choosen As " + PayMode);
					}
				}
				commonFunc.searchAndSelectValueInFilters(CashReceiptLocators.PendingReceivablesBankNameLOVBUtton,
						BankName);
				webDB.switchToFrame(CashReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(CashReceiptLocators.PendingReceivablesOkButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CashReceiptLocators.PendingReceivablesOkButton, ElementType.Xpath);
					webDB.switchToDefaultContent();
					if (Type.equals("Bank")) {
						flag = webDB.waitForElement(CashReceiptLocators.BankReceiptScreenName, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"OK Button Is Clicked Successfully And Navigated To Bank Receipt Screen");
						}
					} else {
						flag = webDB.waitForElement(CashReceiptLocators.CashPaymentScreenName, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"OK Button Is Clicked Successfully And Navigated To Cash Receipt Screen");
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is USed To Select One Document And Convert Into Bank / Cash
	 * Payment
	 * 
	 * @author Harikrishna - RMITS
	 * @param DocumentNumber
	 * @param Type
	 * @param PayMode
	 * @param BankName
	 */
	public void chooseDocumentNumberInPendingReceivablesScreenMPSI(ArrayList<String> DocumentNumber, String Type,
			String PayMode, String BankName, String CreationType, String SheetName) {
		flag = webDB.waitForElement(CashReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(CashReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
			for (int i = 1; i <= DocumentNumber.size(); i++) {
				commonFunc.searchAndSelectValueInFilters(
						CashReceiptLocators.PendingReceivablesDRDocumentNumberLOVButton, DocumentNumber.get(i - 1));
				webDB.switchToFrame(CashReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(CashReceiptLocators.BankReceiptPendingReceivablesSearchButton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CashReceiptLocators.BankReceiptPendingReceivablesSearchButton,
							ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					String GetTextDocumentNumber = webDB.getTextFromElement(
							CashReceiptLocators.PendingReceivablesDocumentNumberField, ElementType.Xpath);
					if (GetTextDocumentNumber.equals(DocumentNumber.get(i - 1))) {
//						verifyPendingReceivablesSIDocumentWithDB(SheetName, DocumentNumber.get(i));
						verifyPendingReceivablesSIDocumentThroughGetDocWithDB(SheetName, DocumentNumber.get(i - 1), i);
//						verifyPendingReceivablesSIDocumentThroughGetDocWithDB123(SheetName, DocumentNumber.get(i - 1),
//								i);

						commonFunc.hardWait(1000);
						webDB.clickAnElement(CashReceiptLocators.BRPendingReceivablesDocumentSelectCheckBox(
								DocumentNumber.get(i - 1)), ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(1500);
						flag = webDB.waitForElement(CashReceiptLocators.PendingPayablesCreateButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "This Sales Invoice Document " + DocumentNumber.get(i - 1)
									+ " Is Selected Successfully");
							if (DocumentNumber.size() > 1) {
								commonFunc.checkRefreshCellsDisappear();
								webDB.clickAnElement(CashReceiptLocators.BankPendingPartyDocumentClearButton,
										ElementType.Xpath);
								flag = webDB.waitForElement(
										CashReceiptLocators.BankReceiptPendingReceivablesSearchButton,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(BankPaymentLocators.BankReceiptPendingReceivablesSearchButton,
											ElementType.Xpath);
									commonFunc.hardWait(1500);
									commonFunc.checkRefreshCellsDisappear();
								}
							}
						}
					} else {
						logs.logging("INFO", "Some Other Document Is Filtered");
					}
				}
			}
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(2000);
			webDB.moveToElement(CashReceiptLocators.PendingPayablesCreateButton, ElementType.Xpath);
			webDB.javaScriptClickAnElement(CashReceiptLocators.PendingPayablesCreateButton, ElementType.Xpath);
			logs.logging("INFO", "Create Button Is Clicked Successfully");
			commonFunc.checkRefreshCellsDisappear();
			if (!CreationType.equals("Single")) {
				flag = webDB.waitForElement(CashReceiptLocators.PendingReceivablesMergeLabelInPending,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CashReceiptLocators.PendingReceivablesMergeLabelInPending, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
				}
			}
			flag = webDB.waitForElement(CashReceiptLocators.PendingReceivablesTypeDropDown, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownByText(CashReceiptLocators.PendingReceivablesTypeDropDown, Type, ElementType.Xpath);
				logs.logging("INFO", "Type Successfully Choosen As " + Type);
				if (Type.equals("Bank")) {
					flag = webDB.waitForElement(CashReceiptLocators.PendingReceivablesPayModeDropDown,
							ElementType.Xpath);
					if (flag) {
						webDB.selectDropDownByText(CashReceiptLocators.PendingReceivablesPayModeDropDown, PayMode,
								ElementType.Xpath);
						logs.logging("INFO", "Pay Mode Successfully Choosen As " + PayMode);
					}
				}
				commonFunc.searchAndSelectValueInFilters(CashReceiptLocators.PendingReceivablesBankNameLOVBUtton,
						BankName);
				webDB.switchToFrame(CashReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(CashReceiptLocators.PendingReceivablesOkButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CashReceiptLocators.PendingReceivablesOkButton, ElementType.Xpath);
					webDB.switchToDefaultContent();
					if (Type.equals("Bank")) {
						flag = webDB.waitForElement(CashReceiptLocators.BankReceiptScreenName, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"OK Button Is Clicked Successfully And Navigated To Bank Receipt Screen");
						}
					} else {
						flag = webDB.waitForElement(CashReceiptLocators.CashPaymentScreenName, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"OK Button Is Clicked Successfully And Navigated To Cash Receipt Screen");
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used to Select the Sales Invoice pending Records In the
	 * Pending Receivables In the bank Receipt Screen
	 * 
	 * @author Harikrishna _ RMITS
	 * @param PartyName
	 * @param VoucherNumber.get(i)
	 */
	public void selectSalesInvoiceDocumentInPendingCashReceiptMPSI(String PartyName, String VoucherNumber,
			String SheetName, int i) {
		flag = webDB.switchToFrame(CashReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
		if (flag) {
			String GetTextPartyName = webDB.getDomPropertyFromElement(
					CashReceiptLocators.BankReceiptPendingReceivablesPartyNameInputField, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			if (GetTextPartyName.equals(PartyName)) {
//				for (int i = 0; i < VoucherNumber.size(); i++) {
				commonFunc.searchAndSelectValueInFilters(CashReceiptLocators.BankReceiptDocNumberLovButton,
						VoucherNumber);
				webDB.switchToFrame(CashReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(CashReceiptLocators.BankReceiptPendingReceivablesSearchButton,
						ElementType.Xpath);
				if (flag) {
					commonFunc.checkRefreshCellsDisappear();
					webDB.doubleClickAnElement(CashReceiptLocators.BankReceiptPendingReceivablesSearchButton,
							ElementType.Xpath);
					flag = webDB.waitForElement(
							CashReceiptLocators.BRPendingReceivablesDocumentSelectCheckBox(VoucherNumber),
							ElementType.Xpath);
					verifyPendingReceivablesSIDocumentMPSIWithDB(SheetName, VoucherNumber, i);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.clickAnElement(
								CashReceiptLocators.BRPendingReceivablesDocumentSelectCheckBox(VoucherNumber),
								ElementType.Xpath);
						commonFunc.hardWait(6000);
						flag = webDB.waitForElement(CashReceiptLocators.BankReceiptPendingReceivableScreenOkButton,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "In Pending Receivables Documents This Voucher Number " + VoucherNumber
									+ " Is Selected Successfully");
						}
					}
				}
//				}
				webDB.moveToElement(CashReceiptLocators.BankReceiptPendingReceivableScreenOkButton, ElementType.Xpath);
				webDB.javaScriptClickAnElement(CashReceiptLocators.BankReceiptPendingReceivableScreenOkButton,
						ElementType.Xpath);
				commonFunc.hardWait(3000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(CashReceiptLocators.CashReceiptHeaderCreateJournalButton,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO",
							"OK Button Is Clicked Successfully In The Bank Receipt Pending Receivables Documents");
				}
			} else {
				logs.logging("INFO",
						"Bank Receipt Line Wise Party Name And Pending Receivables Party Name Is not Getting Matched");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method IS USed To Verify Pending Receivables SI Document MPSI With DB
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param VoucherNumber
	 * @param i1
	 */
	public void verifyPendingReceivablesSIDocumentMPSIWithDB(String SheetName, String VoucherNumber, int i1) {
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String PartyID = CashReceipts[i][2];
			String PartyName = CashReceipts[i][3];
			String Entity = CashReceipts[i][5];
			String CPCDesc = CashReceipts[i][6];
			String Curcy = CashReceipts[i][10];
			String EXRate = CashReceipts[i][11];
			String Location = CashReceipts[i][23];
			String Unit = CashReceipts[i][24];
			String DrCr = CashReceipts[i][25];
			if (!PartyID.equals("")) {
				salesOrdFunc
						.verifyAssertionBothValues(
								dbCommonFunctions.BankReceiptPendingReceivablesDetails(VoucherNumber,
										commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesPartyName")),
								PartyName);
				logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
						+ " PartyName Is getting Matched With DB");

				salesOrdFunc
						.verifyAssertionBothValues(
								dbCommonFunctions.BankReceiptPendingReceivablesDetails(VoucherNumber,
										commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesLocationID")),
								Location);
				logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
						+ " Location ID Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankReceiptPendingReceivablesDetails(
						VoucherNumber, commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesUnitID")), Unit);
				logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
						+ " Unit ID Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankReceiptPendingReceivablesDetails(
						VoucherNumber, commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesCurcy")), Curcy);
				logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
						+ " Currency Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankReceiptPendingReceivablesDetails(
						VoucherNumber, commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesExRate")), EXRate);
				logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
						+ " EX.Rate Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankReceiptPendingReceivablesDetails(
						VoucherNumber, commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesDROrCR")), DrCr);
				logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
						+ " Dr / Cr Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankReceiptPendingReceivablesDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesInvAmt")),
						dbCommonFunctions.SIHeaderDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
						+ " Invoice Amount Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankReceiptPendingReceivablesDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesDueDays")),
						dbCommonFunctions.SOPaymentTermAdvPercentage(
								commonFunc.getDataFromTestDataSheet("SOPaymentTerm"),
								commonFunc.getDataFromTestDataSheet("PaymentTermDueDays")));
				logs.logging("INFO", "In Pending Receivables For This Document Number "
						+ VariableHandler.getMultipleSIDocs().get(i - 1) + " Due Days Is getting Matched With DB");

				salesOrdFunc
						.verifyAssertionBothValues(
								dbCommonFunctions.BankReceiptPendingReceivablesDetails(VoucherNumber,
										commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesDocNumber")),
								VoucherNumber);
				logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
						+ " Source Document Is getting Matched With DB");
			}
		}
	}

	/**
	 * This Method Is used To Add The Bank Receipt Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return
	 */
	public void CashReceiptThroughAddButton(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		createCashReceiptEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCR"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashReceiptDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"), Currency);
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String GLAccountDesc = CashReceipts[i][1];
			String PartyName = CashReceipts[i][3];
			String EntityName = CashReceipts[i][5];
			String CPCCodeDesc = CashReceipts[i][6];
			String BillNumber = CashReceipts[i][7];
			String PayAmount = CashReceipts[i][8];
			String DRCR = CashReceipts[i][9];
			String CURCY = CashReceipts[i][10];
			String EXRate = CashReceipts[i][11];
			String TransCURCY = CashReceipts[i][12];
			String BaseCURCY = CashReceipts[i][13];
			String AssesableValue = CashReceipts[i][16];
			if (!GLAccountDesc.equals("")) {
				clickCRLineAddButton();
				addCashReceiptLineDetails(GLAccountDesc, CPCCodeDesc);
				clickCRLineWiseSaveButton();
				clickCRLineWiseAddButton(i);
				clickPendingReceivablesInCR();
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, "1");
				}
				selectSalesInvoiceDocumentInPendingBankReceipt(PartyName, VariableHandler.getMultipleSIDocs(),
						SheetName);
				verifyCashReceiptLineDetailsWithDB(SheetName, i);
				for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
					excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournal"), 1, 5 + j,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournal"), 2, 5 + j,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				}
			}
		}
		clickCreateJournalButtonInCR();
		commonFunc.checkRefreshCellsDisappear();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CRJournal"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), CRFilepath);
		for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
			excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournal"), 1, 5 + j, "0");
			excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournal"), 2, 5 + j, "0");
		}
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCRVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method is Used To Add The Document Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param Currency
	 */
	public void CashReceiptThroughGetDocument(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		createCashReceiptEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCR"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashReceiptDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"), Currency);
		clickGetDocumentInCRHeader();
		clickPendingReceivablesInGetDocumentCR();
		chooseDocumentNumberAndClickSearchButtonInPendingReceivables(VariableHandler.getMultipleSIDocs(), SheetName);
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String GLAccountDesc = CashReceipts[i][1];
			String BaseCURCY = CashReceipts[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, "1");
				}
				verifyCashReceiptLineDetailsWithDB(SheetName, i);
				for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
					excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournal"), 1, 5 + j,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournal"), 2, 5 + j,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				}
			}
		}
		clickCreateJournalButtonInCR();
		commonFunc.checkRefreshCellsDisappear();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CRJournal"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), CRFilepath);
		for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
			excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournal"), 1, 5 + j, "0");
			excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournal"), 2, 5 + j, "0");
		}
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCRVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The Document Through pending Documents
	 * 
	 * @author Harikrishna - RMITS
	 * @param CreationType
	 * @param SheetName
	 * @param Currency
	 */
	public void CashReceiptThroughPendingDocs(String CreationType, String SheetName, String Currency,
			String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingDRDocsButton();
		clickPendingRecivablesButtonInPendDRDocs();
		chooseDocumentNumberInPendingReceivablesScreen(VariableHandler.getMultipleSIDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentTypeCash"),
				commonFunc.getDataFromTestDataSheet("BankPayMode"), CashierName, CreationType, SheetName);
		getCashPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		for (int i = 1; i < CashReceipts.length; i++) {
			String GLAccountDesc = CashReceipts[i][1];
			String BaseCURCY = CashReceipts[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, "1");
				}
				commonFunc.hardWait(1000);
				for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
					excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournal"), 1, 5 + j,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournal"), 2, 5 + j,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				}
			}
		}
		clickCreateJournalButtonInCR();
		commonFunc.checkRefreshCellsDisappear();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CRJournal"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), CRFilepath);
		for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
			excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournal"), 1, 5 + j, "0");
			excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournal"), 2, 5 + j, "0");
		}
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCRVDocumentStatus();
		}
	}

	/**
	 * This Method is Used To Add The Document Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param Currency
	 */
	public void CashReceiptThroughGetDocumentMPSI(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		createCashReceiptEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCR"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashReceiptDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"), Currency);
		clickGetDocumentInCRHeader();
		clickPendingReceivablesInGetDocumentCR();
		chooseDocumentNumberAndClickSearchButtonInPendingReceivables(VariableHandler.getMultipleSIDocs(), SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String GLAccountDesc = CashReceipts[i][1];
			String BaseCURCY = CashReceipts[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, "1");
				}
				verifyCashReceiptLineDetailsWithDB(SheetName, i);
				excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalMulti"), 1, 5 + i,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(i - 1),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
					excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalMulti"), j + 1, 6,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				}
			}
		}
		clickCreateJournalButtonInCR();
		commonFunc.checkRefreshCellsDisappear();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CRJournalMulti"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), CRFilepath);
		for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
			excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalMulti"), 1, 5 + j, "0");
			excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalMulti"), j + 1, 6, "0");
			excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalMulti"), j + 1, 6, "0");
		}
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCRVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The Document Through pending Documents
	 * 
	 * @author Harikrishna - RMITS
	 * @param CreationType
	 * @param SheetName
	 * @param Currency
	 */
	public void CashReceiptThroughPendingDocsMPSI(String CreationType, String SheetName, String Currency,
			String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingDRDocsButton();
		clickPendingRecivablesButtonInPendDRDocs();
		chooseDocumentNumberInPendingReceivablesScreenMPSI(VariableHandler.getMultipleSIDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentTypeCash"),
				commonFunc.getDataFromTestDataSheet("BankPayMode"), CashierName, CreationType, SheetName);
		getCashPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		for (int i = 1; i < CashReceipts.length; i++) {
			String GLAccountDesc = CashReceipts[i][1];
			String BaseCURCY = CashReceipts[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, "1");
				}
				commonFunc.hardWait(1000);
				verifyCashReceiptLineDetailsWithDB(SheetName, i);
				excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalMulti"), 1, 5 + i,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(i - 1),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
					excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalMulti"), j + 1, 6,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				}
			}
		}
		clickCreateJournalButtonInCR();
		commonFunc.checkRefreshCellsDisappear();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CRJournalMulti"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), CRFilepath);
		for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
			excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalMulti"), 1, 5 + j, "0");
			excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalMulti"), j + 1, 6, "0");
			excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalMulti"), j + 1, 6, "0");
		}
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCRVDocumentStatus();
		}
	}

	/**
	 * This Method Is used To Add The Bank Receipt Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return
	 */
	public void BankReceiptThroughAddButtonMPSI(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		createCashReceiptEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCR"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashReceiptDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"), Currency);
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String GLAccountDesc = CashReceipts[i][1];
			String PartyName = CashReceipts[i][3];
			String EntityName = CashReceipts[i][5];
			String CPCCodeDesc = CashReceipts[i][6];
			String BillNumber = CashReceipts[i][7];
			String PayAmount = CashReceipts[i][8];
			String DRCR = CashReceipts[i][9];
			String CURCY = CashReceipts[i][10];
			String EXRate = CashReceipts[i][11];
			String TransCURCY = CashReceipts[i][12];
			String BaseCURCY = CashReceipts[i][13];
			String AssesableValue = CashReceipts[i][16];
			if (!GLAccountDesc.equals("")) {
				clickCRLineAddButton();
				addCashReceiptLineDetails(GLAccountDesc, CPCCodeDesc);
				clickCRLineWiseSaveButton();
				clickCRLineWiseAddButton(i);
				clickPendingReceivablesInCR();
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, "1");
				}
				selectSalesInvoiceDocumentInPendingCashReceiptMPSI(PartyName,
						VariableHandler.getMultipleSIDocs().get(i - 1), SheetName, i);
				verifyCashReceiptLineDetailsWithDB(SheetName, i);
			}
//			excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("CRJournalMulti"), 1, 5 + i,
//					dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(i - 1),
//							commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
//			for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
//				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("CRJournalMulti"), j + 1, 6,
//						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
//								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
//			}
		}
		clickCreateJournalButtonInCR();
		commonFunc.checkRefreshCellsDisappear();
//		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CRJournalMulti"),
//				VariableHandler.getCashPaymentOrReceiptDocNumber());
//		for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
//			excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("CRJournalMulti"), 1, 5 + j, "0");
//			excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("CRJournalMulti"), j + 1, 6, "0");
//			excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("CRJournalMulti"), j + 1, 6, "0");
//		}
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCRVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is used To Add The Bank Receipt Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void CashReceiptThroughAddButtonMPMI(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		createCashReceiptEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCR"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashReceiptDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"), Currency);
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String GLAccountDesc = CashReceipts[i][1];
			String PartyName = CashReceipts[i][3];
			String EntityName = CashReceipts[i][5];
			String CPCCodeDesc = CashReceipts[i][6];
			String BillNumber = CashReceipts[i][7];
			String PayAmount = CashReceipts[i][8];
			String DRCR = CashReceipts[i][9];
			String CURCY = CashReceipts[i][10];
			String EXRate = CashReceipts[i][11];
			String TransCURCY = CashReceipts[i][12];
			String BaseCURCY = CashReceipts[i][13];
			String AssesableValue = CashReceipts[i][16];
			if (!GLAccountDesc.equals("")) {
				clickCRLineAddButton();
				addCashReceiptLineDetails(GLAccountDesc, CPCCodeDesc);
				clickCRLineWiseSaveButton();
				clickCRLineWiseAddButton(i);
				clickPendingReceivablesInCR();
				selectSalesInvoiceDocumentInPendingBankReceiptMPMI(PartyName, VariableHandler.getMultipleSIDocs(), i);
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, "1");
				}
				verifyCashReceiptLineDetailsWithDB(SheetName, i);
			}
		}
		clickCreateJournalButtonInCR();
		commonFunc.checkRefreshCellsDisappear();
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCRVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used to Select the Sales Invoice pending Records In the
	 * Pending Receivables In the bank Receipt Screen
	 * 
	 * @author Harikrishna _ RMITS
	 * @param PartyName
	 * @param VoucherNumber.get(i)
	 */
	public void selectSalesInvoiceDocumentInPendingBankReceiptMPMI(String PartyName, ArrayList<String> VoucherNumber,
			int i1) {
		flag = webDB.switchToFrame(CashReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
		if (flag) {
			String GetTextPartyName = webDB.getDomPropertyFromElement(
					CashReceiptLocators.BankReceiptPendingReceivablesPartyNameInputField, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			int a = i1 * 2;
			if (GetTextPartyName.equals(PartyName)) {
				for (int i = a - 1; i <= a; i++) {
					commonFunc.searchAndSelectValueInFilters(CashReceiptLocators.BankReceiptDocNumberLovButton,
							VoucherNumber.get(i - 1));
					webDB.switchToFrame(CashReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
					flag = webDB.waitForElement(CashReceiptLocators.BankReceiptPendingReceivablesSearchButton,
							ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.doubleClickAnElement(CashReceiptLocators.BankReceiptPendingReceivablesSearchButton,
								ElementType.Xpath);
						flag = webDB.waitForElement(CashReceiptLocators.BRPendingReceivablesDocumentSelectCheckBox(
								VoucherNumber.get(i - 1)), ElementType.Xpath);
						if (flag) {
							commonFunc.checkRefreshCellsDisappear();
							webDB.clickAnElement(CashReceiptLocators.BRPendingReceivablesDocumentSelectCheckBox(
									VoucherNumber.get(i - 1)), ElementType.Xpath);
							commonFunc.hardWait(6000);
							flag = webDB.waitForElement(CashReceiptLocators.BankReceiptPendingReceivableScreenOkButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "In Pending Receivables Documents This Voucher Number "
										+ VoucherNumber.get(i - 1) + " Is Selected Successfully");
							}
						}
					}
				}
				webDB.moveToElement(CashReceiptLocators.BankReceiptPendingReceivableScreenOkButton, ElementType.Xpath);
				webDB.javaScriptClickAnElement(CashReceiptLocators.BankReceiptPendingReceivableScreenOkButton,
						ElementType.Xpath);
				commonFunc.hardWait(3000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(CashReceiptLocators.CashReceiptHeaderCreateJournalButton,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO",
							"OK Button Is Clicked Successfully In The Bank Receipt Pending Receivables Documents");
				}
			} else {
				logs.logging("INFO",
						"Bank Receipt Line Wise Party Name And Pending Receivables Party Name Is not Getting Matched");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used to Click The Line Wise View Button
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickLineWiseViewButton(int i) {
		flag = webDB.waitForElement(CashReceiptLocators.CashLineViewButton(i), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashReceiptLocators.CashLineViewButton(i), ElementType.Xpath);
			flag = webDB.waitForElementUntilGivenTime(CashReceiptLocators.BankReceiptViewFrame, ElementType.Xpath, 20);
			if (flag) {
				webDB.switchToFrame(CashReceiptLocators.BankReceiptViewFrame, ElementType.Xpath);
				logs.logging("INFO", "Switch To Receipt Details Frame Successfully");
				flag = webDB.waitForElement(CashReceiptLocators.BankReceiptViewAddButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Cash Receipt Line Wise View Button Is clicked Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Add Button In the Bank Receipt View Details
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickAddButtonInViewDetails() {
		flag = webDB.waitForElement(CashReceiptLocators.BankReceiptViewAddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashReceiptLocators.BankReceiptViewAddButton, ElementType.Xpath);
			flag = webDB.waitForElement(CashReceiptLocators.BankReceiptAgainstRefFieldName, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Button Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The Bill Number And Pay Amount In The Bank Receipt
	 * View Details
	 * 
	 * @author Harikrishna - RMITS
	 * @param BillNumber
	 * @param PayAmount
	 */
	public void addBillNumberAndPayAmount(String BillNumber, String PayAmount) {
		salesOrdFunc.verifyClickAndEnterValueInTableFields(CashReceiptLocators.BankReceiptBillDocField,
				CashReceiptLocators.BankReceiptBillNumberInputField, BillNumber);
		webDB.pressTabKey();
		salesOrdFunc.verifyClickAndEnterValueInTableFields(CashReceiptLocators.BankReceiptPayAmountField,
				CashReceiptLocators.BankReceiptPayAmountInputField, PayAmount);
		webDB.pressTabKey();
	}

	/**
	 * This Method Is Used to Click The View Details save Button In The Bank Receipt
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickViewDetailsSaveButton() {
		flag = webDB.waitForElement(CashReceiptLocators.BankReceiptViewSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashReceiptLocators.BankReceiptViewSaveButton, ElementType.Xpath);
			flag = webDB.waitForElement(CashReceiptLocators.CashReceiptViewDeleteButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Cash View Save Button Is Clicked Successfully");
//				webDB.switchToDefaultContent();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Bank View Details Screen Close Button
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickBankViewDetailsCloseButton() {
		webDB.switchToDefaultContent();
		flag = webDB.waitForElement(CashReceiptLocators.BankReceiptViewScreenCloseButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashReceiptLocators.BankReceiptViewScreenCloseButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(CashReceiptLocators.CashReceiptHeaderCreateJournalButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Cash Receipt View details Is Closed Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The Documents Through Get Document With MPMI
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 * @param Currency
	 */
	public void CashReceiptThroughGetDocumentMPMI(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		createCashReceiptEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCR"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashReceiptDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"), Currency);
		clickGetDocumentInCRHeader();
		clickPendingReceivablesInGetDocumentCR();
		chooseDocumentNumberAndClickSearchButtonInPendingReceivablesMPMI(VariableHandler.getMultipleSIDocs(),
				SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String GLAccountDesc = CashReceipts[i][1];
			String BaseCURCY = CashReceipts[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, "1");
				}
				verifyCashReceiptLineDetailsWithDB(SheetName, i);
				excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalMPMI"), 2, 6,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalMPMI"), 2, 7,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(1),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalMPMI"), 3, 6,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(2),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalMPMI"), 3, 7,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(3),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			}
		}
		clickCreateJournalButtonInCR();
		commonFunc.checkRefreshCellsDisappear();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CRJournalMPMI"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), CRFilepath);
		excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalMPMI"), 2, 6, "0");
		excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalMPMI"), 2, 7, "0");
		excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalMPMI"), 3, 6, "0");
		excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalMPMI"), 3, 7, "0");
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCRVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Methos Is Used To Choose Party Name And Select The Purchase Bills In The
	 * Pending Party Documents
	 * 
	 * @author Harikrishna - RMITS
	 * @param PartyName
	 * @param VoucherNumber
	 */
	public void chooseDocumentNumberAndClickSearchButtonInPendingReceivablesMPMI(ArrayList<String> VoucherNumber,
			String SheetName) {
		flag = webDB.switchToFrame(BankReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
		if (flag) {
			for (int i = 1; i <= VoucherNumber.size(); i++) {
//				commonFunc.searchAndSelectValueInFilters(
//						BankPaymentLocators.BankPaymentPendingPartyDocumentPartyLovButton, PartyName);
//				webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
//				flag = webDB.waitForElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
//						ElementType.Xpath);
//				if (flag) {
//					webDB.clickAnElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
//							ElementType.Xpath);
//					commonFunc.checkRefreshCellsDisappear();
				commonFunc.searchAndSelectValueInFilters(CashReceiptLocators.BankReceiptDocNumberLovButton,
						VoucherNumber.get(i - 1));
				webDB.switchToFrame(CashReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(CashReceiptLocators.BankReceiptPendingReceivablesSearchButton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CashReceiptLocators.BankReceiptPendingReceivablesSearchButton,
							ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(1000);
					String GetTextDocumentNumber = webDB.getTextFromElement(
							BankReceiptLocators.PendingReceivablesDocumentNumberField, ElementType.Xpath);
					logs.logging("INFO", "Get Text Document Number Is " + GetTextDocumentNumber);
					if (GetTextDocumentNumber.equals(VoucherNumber.get(i - 1))) {
						flag = webDB.waitForElement(CashReceiptLocators.BRPendingReceivablesDocumentSelectCheckBox(
								VoucherNumber.get(i - 1)), ElementType.Xpath);
//						verifyPendingReceivablesSIDocumentWithDB(SheetName, VoucherNumber.get(i));
//						verifyPendingReceivablesSIDocumentThroughGetDocWithDB(SheetName, VoucherNumber.get(i-1),i);
						verifyPendingReceivablesSIDocumentThroughGetDocWithDB123(SheetName, VoucherNumber.get(i - 1),
								i);
						if (flag) {
							webDB.clickAnElement(CashReceiptLocators.BRPendingReceivablesDocumentSelectCheckBox(
									VoucherNumber.get(i - 1)), ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(3000);
							flag = webDB.waitForElement(
									CashReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "In Pending Receivables Documents This Voucher Number "
										+ VoucherNumber.get(i - 1) + " Is Selected Successfully");
							}
						}
					} else {
						logs.logging("INFO",
								"Bank Receipt Pending Receivables Line Wise Document Number And Pending Receivables Documents Number Is not Getting Matched");
					}
				}
			}
			commonFunc.hardWait(1500);
			flag = webDB.waitForElement(CashReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
					ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(CashReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
						ElementType.Xpath);
				webDB.javaScriptClickAnElement(CashReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
						ElementType.Xpath);
				commonFunc.hardWait(500);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(CashReceiptLocators.CashReceiptHeaderCreateJournalButton,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Ok Button Is Clicked Successfully In The Pending Receivables Screen");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Pending Receivables Through Get Document With
	 * DB
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param VoucherNumber
	 * @param i1
	 */
	public void verifyPendingReceivablesSIDocumentThroughGetDocWithDB123(String SheetName, String VoucherNumber,
			int i1) {
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
//		int a= i1*1;
		for (int i = (i1 + 1) / 2; i <= (i1 + 1) / 2; i++) {
			System.out.println(i);
			String PartyID = CashReceipts[i][2];
			String PartyName = CashReceipts[i][3];
			String Entity = CashReceipts[i][5];
			String CPCDesc = CashReceipts[i][6];
			String Curcy = CashReceipts[i][10];
			String EXRate = CashReceipts[i][11];
			String Location = CashReceipts[i][23];
			String Unit = CashReceipts[i][24];
			String DrCr = CashReceipts[i][25];
			if (!PartyID.equals("")) {
				salesOrdFunc
						.verifyAssertionBothValues(
								dbCommonFunctions.BankReceiptPendingReceivablesDetails(VoucherNumber,
										commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesPartyName")),
								PartyName);
				logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
						+ " PartyName Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankReceiptPendingReceivablesDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesLocationID")),
						dbCommonFunctions.SIHeaderDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("CRDBLocationColumn")));
				logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
						+ " Location ID Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankReceiptPendingReceivablesDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesUnitID")),
						dbCommonFunctions.SIHeaderDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("CRDBUnitColumn")));
				logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
						+ " Unit ID Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankReceiptPendingReceivablesDetails(
						VoucherNumber, commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesCurcy")), Curcy);
				logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
						+ " Currency Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankReceiptPendingReceivablesDetails(
						VoucherNumber, commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesExRate")), EXRate);
				logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
						+ " EX.Rate Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankReceiptPendingReceivablesDetails(
						VoucherNumber, commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesDROrCR")), DrCr);
				logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
						+ " Dr / Cr Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankReceiptPendingReceivablesDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesInvAmt")),
						dbCommonFunctions.SIHeaderDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
						+ " Invoice Amount Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankReceiptPendingReceivablesDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesDueDays")),
						dbCommonFunctions.SOPaymentTermAdvPercentage(
								commonFunc.getDataFromTestDataSheet("SOPaymentTerm"),
								commonFunc.getDataFromTestDataSheet("PaymentTermDueDays")));
				logs.logging("INFO", "In Pending Receivables For This Document Number "
						+ VariableHandler.getMultipleSIDocs().get(i - 1) + " Due Days Is getting Matched With DB");

				salesOrdFunc
						.verifyAssertionBothValues(
								dbCommonFunctions.BankReceiptPendingReceivablesDetails(VoucherNumber,
										commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesDocNumber")),
								VoucherNumber);
				logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
						+ " Source Document Is getting Matched With DB");
			}
		}
	}

	/**
	 * This Method Is Used To Add The Documents With Pending Docs With MPMI
	 * 
	 * @author Harikrishna -RMITS
	 * @param CreationType
	 * @param SheetName
	 * @param Currency
	 */
	public void CashReceiptThroughPendingDocsMPMI(String CreationType, String SheetName, String Currency,
			String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingDRDocsButton();
		clickPendingRecivablesButtonInPendDRDocs();
		chooseDocumentNumberInPendingReceivablesScreen(VariableHandler.getMultipleSIDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentTypeCash"),
				commonFunc.getDataFromTestDataSheet("BankPayMode"), CashierName, CreationType, SheetName);
		getCashPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		for (int i = 1; i < CashReceipts.length; i++) {
			String GLAccountDesc = CashReceipts[i][1];
			String BaseCURCY = CashReceipts[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, "1");
				}
				verifyCashReceiptLineDetailsWithDB(SheetName, i);
				excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalMPMI"), 2, 6,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalMPMI"), 2, 7,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(1),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalMPMI"), 3, 6,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(2),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalMPMI"), 3, 7,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(3),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			}
		}
		clickCreateJournalButtonInCR();
		commonFunc.checkRefreshCellsDisappear();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CRJournalMPMI"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), CRFilepath);
		excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalMPMI"), 2, 6, "0");
		excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalMPMI"), 2, 7, "0");
		excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalMPMI"), 3, 6, "0");
		excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalMPMI"), 3, 7, "0");
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCRVDocumentStatus();
		}
	}

	/**
	 * This Method Is used To Add The Bank Receipt Through Add Button MPMI With New
	 * Reference
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void CashReceiptThroughAddButtonMPMIWithNewRef(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		createCashReceiptEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCR"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashReceiptDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"), Currency);
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String GLAccountDesc = CashReceipts[i][1];
			String PartyName = CashReceipts[i][3];
			String EntityName = CashReceipts[i][5];
			String CPCCodeDesc = CashReceipts[i][6];
			String BillNumber = CashReceipts[i][7];
			String PayAmount = CashReceipts[i][8];
			String DRCR = CashReceipts[i][9];
			String CURCY = CashReceipts[i][10];
			String EXRate = CashReceipts[i][11];
			String TransCURCY = CashReceipts[i][12];
			String BaseCURCY = CashReceipts[i][13];
			String AssesableValue = CashReceipts[i][16];
			if (!GLAccountDesc.equals("")) {
				clickCRLineAddButton();
				addCashReceiptLineDetails(GLAccountDesc, CPCCodeDesc);
				clickCRLineWiseSaveButton();
				clickCRLineWiseAddButton(i);
				clickPendingReceivablesInCR();
				selectSalesInvoiceDocumentInPendingBankReceiptMPMI(PartyName, VariableHandler.getMultipleSIDocs(), i);
				clickLineWiseViewButton(i);
				clickAddButtonInViewDetails();
				addBillNumberAndPayAmount(BillNumber, PayAmount);
				clickViewDetailsSaveButton();
				clickBankViewDetailsCloseButton();
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, "1");
				}
				verifyCashReceiptLineDetailsWithDB(SheetName, i);
			}
//			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
//				enterRealizedAmountInBRHeader(BaseCURCY);
		}
		clickCreateJournalButtonInCR();
		commonFunc.checkRefreshCellsDisappear();
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCRVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The Documents Through Get Documents With New
	 * reference
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param Currency
	 */
	public void CashReceiptThroughGetDocumentWithNewRef(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		createCashReceiptEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCR"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashReceiptDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"), Currency);
		clickGetDocumentInCRHeader();
		clickPendingReceivablesInGetDocumentCR();
		chooseDocumentNumberAndClickSearchButtonInPendingReceivablesMPMI(VariableHandler.getMultipleSIDocs(),
				SheetName);
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String GLAccountDesc = CashReceipts[i][1];
			String PartyName = CashReceipts[i][3];
			String EntityName = CashReceipts[i][5];
			String CPCCodeDesc = CashReceipts[i][6];
			String BillNumber = CashReceipts[i][7];
			String PayAmount = CashReceipts[i][8];
			String DRCR = CashReceipts[i][9];
			String CURCY = CashReceipts[i][10];
			String EXRate = CashReceipts[i][11];
			String TransCURCY = CashReceipts[i][12];
			String BaseCURCY = CashReceipts[i][13];
			String AssesableValue = CashReceipts[i][16];
			if (!GLAccountDesc.equals("")) {
				clickLineWiseViewButton(i);
				clickAddButtonInViewDetails();
				addBillNumberAndPayAmount(BillNumber, PayAmount);
				clickViewDetailsSaveButton();
				clickBankViewDetailsCloseButton();
				for (int j = 1; j < CashReceipts.length; j++) {
					String GLAccountDesc1 = CashReceipts[j][1];
					if (!GLAccountDesc1.equals("")) {
						if (!Currency.equals("INR")) {
							excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11,
									dbCommonFunctions.ExchangeRateDetails(Currency,
											commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
						} else {
							excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, "1");
						}
						verifyCashReceiptLineDetailsWithDB(SheetName, i);
						excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalNR"), 2, 6,
								dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
										commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
						excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalNR"), 2, 7,
								dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(1),
										commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
						excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalNR"), 3, 6,
								dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(2),
										commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
						excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalNR"), 3, 7,
								dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(3),
										commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					}
				}
			}
		}
		clickCreateJournalButtonInCR();
		commonFunc.checkRefreshCellsDisappear();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CRJournalNR"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), CRFilepath);
		excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalNR"), 2, 6, "0");
		excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalNR"), 2, 7, "0");
		excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalNR"), 3, 6, "0");
		excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalNR"), 3, 7, "0");
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCRVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The Documents Through Pending Docs With New
	 * Reference
	 * 
	 * @author Harikrishna -RMITS
	 * @param CreationType
	 * @param SheetName
	 * @param Currency
	 */
	public void CashReceiptThroughPendingDocsWithNewRef(String CreationType, String SheetName, String Currency,
			String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingDRDocsButton();
		clickPendingRecivablesButtonInPendDRDocs();
		chooseDocumentNumberInPendingReceivablesScreen(VariableHandler.getMultipleSIDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentTypeCash"),
				commonFunc.getDataFromTestDataSheet("BankPayMode"), CashierName, CreationType, SheetName);
		getCashPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String GLAccountDesc = CashReceipts[i][1];
			String PartyName = CashReceipts[i][3];
			String EntityName = CashReceipts[i][5];
			String CPCCodeDesc = CashReceipts[i][6];
			String BillNumber = CashReceipts[i][7];
			String PayAmount = CashReceipts[i][8];
			String DRCR = CashReceipts[i][9];
			String CURCY = CashReceipts[i][10];
			String EXRate = CashReceipts[i][11];
			String TransCURCY = CashReceipts[i][12];
			String BaseCURCY = CashReceipts[i][13];
			String AssesableValue = CashReceipts[i][16];
			if (!GLAccountDesc.equals("")) {
				clickLineWiseViewButton(i);
				clickAddButtonInViewDetails();
				addBillNumberAndPayAmount(BillNumber, PayAmount);
				clickViewDetailsSaveButton();
				clickBankViewDetailsCloseButton();
				for (int j = 1; j < CashReceipts.length; j++) {
					String GLAccountDesc1 = CashReceipts[j][1];
					if (!GLAccountDesc1.equals("")) {
						if (!Currency.equals("INR")) {
							excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11,
									dbCommonFunctions.ExchangeRateDetails(Currency,
											commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
						} else {
							excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, "1");
						}
						verifyCashReceiptLineDetailsWithDB(SheetName, i);
						excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalNR"), 2, 6,
								dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
										commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
						excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalNR"), 2, 7,
								dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(1),
										commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
						excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalNR"), 3, 6,
								dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(2),
										commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
						excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalNR"), 3, 7,
								dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(3),
										commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					}
				}
			}
		}
		clickCreateJournalButtonInCR();
		commonFunc.checkRefreshCellsDisappear();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CRJournalNR"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), CRFilepath);
		excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalNR"), 2, 6, "0");
		excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalNR"), 2, 7, "0");
		excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalNR"), 3, 6, "0");
		excelUtils.writeDataInExcel(CRFilepath, webDB.getDataFromProperties("CRJournalNR"), 3, 7, "0");
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCRVDocumentStatus();
		}
	}

	/**
	 * This Method Is used To Add The Bank Receipt Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return
	 */
	public void CashReceiptWithExpAndRevenueWithoutGST(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		createCashReceiptEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCR"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashReceiptDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"), Currency);
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String GLAccountDesc = CashReceipts[i][1];
			String PartyName = CashReceipts[i][3];
			String EntityName = CashReceipts[i][5];
			String CPCCodeDesc = CashReceipts[i][6];
			String BillNumber = CashReceipts[i][7];
			String PayAmount = CashReceipts[i][8];
			String DRCR = CashReceipts[i][9];
			String CURCY = CashReceipts[i][10];
			String EXRate = CashReceipts[i][11];
			String TransCURCY = CashReceipts[i][12];
			String BaseCURCY = CashReceipts[i][13];
			String HSNCode = CashReceipts[i][14];
			String AssesableValue = CashReceipts[i][16];
			String LineWiseDRCR = CashReceipts[i][25];
			String ChargesFlag = CashReceipts[i][29];
			String StateCode = CashReceipts[i][30];
			String GSTSupplierName = CashReceipts[i][31];
			String GSTIN = CashReceipts[i][32];
			if (!GLAccountDesc.equals("")) {
				clickCRLineAddButton();
				addCashReceiptLineDetails(GLAccountDesc, CPCCodeDesc);
				enterValueInAmtInTCField(PayAmount);
				chooseLineWiseDRCRInCR(LineWiseDRCR);
				clickCRLineWiseSaveButton();
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, "1");
				}
				verifyCashReceiptLineDetailsWithDB(SheetName, i);
			}
		}
		clickCreateJournalButtonInCR();
		commonFunc.checkRefreshCellsDisappear();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CRJournalREV"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), CRFilepath);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCRVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is used To Add The Bank Receipt Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return
	 */
	public void CashReceiptWithExpAndRevenueWithGST(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		createCashReceiptEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCR"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashReceiptDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"), Currency);
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String GLAccountDesc = CashReceipts[i][1];
			String PartyName = CashReceipts[i][3];
			String EntityName = CashReceipts[i][5];
			String CPCCodeDesc = CashReceipts[i][6];
			String BillNumber = CashReceipts[i][7];
			String PayAmount = CashReceipts[i][8];
			String DRCR = CashReceipts[i][9];
			String CURCY = CashReceipts[i][10];
			String EXRate = CashReceipts[i][11];
			String TransCURCY = CashReceipts[i][12];
			String BaseCURCY = CashReceipts[i][13];
			String HSNCode = CashReceipts[i][14];
			String AssesableValue = CashReceipts[i][16];
			String LineWiseDRCR = CashReceipts[i][25];
			String ChargesFlag = CashReceipts[i][29];
			String StateCode = CashReceipts[i][30];
			String GSTSupplierName = CashReceipts[i][31];
			String GSTIN = CashReceipts[i][32];
			if (!GLAccountDesc.equals("")) {
				clickCRLineAddButton();
				addCashReceiptLineDetails(GLAccountDesc, CPCCodeDesc);
				enterValueInAmtInTCField(PayAmount);
				chooseLineWiseDRCRInCR(LineWiseDRCR);
				chooseHSNCodeInCR(HSNCode);
				clickCRLineWiseSaveButton();
				clickLineWiseGSTIconInCR(i);
				enterValuesInGSTScreen(StateCode, GSTSupplierName, GSTIN, Today, BillNumber);
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, "1");
				}
				verifyCashReceiptLineDetailsWithDB(SheetName, i);
			}
//			if (!Currency.equals("INR")) {
//				enterRealizedAmountInBRHeader(BaseCURCY);
//			}
		}
		clickCreateJournalButtonInCR();
		commonFunc.checkRefreshCellsDisappear();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CRJournalREVGST"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), CRFilepath);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCRVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Choose The HSN Code In CR
	 * 
	 * @author Harikrishna - RMITS
	 * @param HSNCode
	 */
	public void chooseHSNCodeInCR(String HSNCode) {
		flag = webDB.waitForElement(CashReceiptLocators.CashReceiptHSNCodeField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashReceiptLocators.CashReceiptHSNCodeField, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(CashReceiptLocators.CashReceiptLineHsnCodeLov, HSNCode);
			webDB.pressTabKey();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click Line Wise GST Icon In BR
	 * 
	 * @author Harikrishna - RMITS
	 * @param i
	 */
	public void clickLineWiseGSTIconInCR(int i) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(CashReceiptLocators.CashLineGSTButton(i), ElementType.CssSelector);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			webDB.javaScriptClickAnElement(CashReceiptLocators.CashLineGSTButton(i), ElementType.CssSelector);
			flag = webDB.waitForElement(CashReceiptLocators.BankReceiptGSTDetailsHeaderName, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Line Wise GST Details Button Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is used To Enter values In The GST Screen
	 * 
	 * @author Harikrishna - RMITS
	 * @param StateCode
	 * @param GSTSupplierName
	 * @param GSTIN
	 * @param BillDate
	 * @param BillNumber
	 */
	public void enterValuesInGSTScreen(String StateCode, String GSTSupplierName, String GSTIN, String BillDate,
			String BillNumber) {
		flag = webDB.waitForElement(CashReceiptLocators.BankReceiptGSTFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(CashReceiptLocators.BankReceiptGSTFrame, ElementType.Xpath);
			flag = webDB.waitForElement(CashReceiptLocators.BankReceiptStateCodeLov, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Switched To Frame");
				commonFunc.searchAndSelectValueInFilters(CashReceiptLocators.BankReceiptStateCodeLov, StateCode);
				webDB.pressTabKey();
				webDB.switchToFrame(CashReceiptLocators.BankReceiptGSTFrame, ElementType.Xpath);
				flag = webDB.waitForElement(CashReceiptLocators.BankReceiptSupplierNameGST, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(CashReceiptLocators.BankReceiptSupplierNameGST, GSTSupplierName,
							ElementType.Xpath);
					webDB.pressTabKey();
					logs.logging("INFO", "GST Supplier Name " + GSTSupplierName + " Is Inserted Successfully");
					flag = webDB.waitForElement(CashReceiptLocators.BankReceiptGSTINFieldInput, ElementType.Xpath);
					if (flag) {
						webDB.sendTextToAnElement(CashReceiptLocators.BankReceiptGSTINFieldInput, GSTIN,
								ElementType.Xpath);
						webDB.pressTabKey();
						logs.logging("INFO", "GSTIN Number " + GSTIN + " Is Inserted Successfully");
						flag = webDB.waitForElement(CashReceiptLocators.BankReceiptBillDateGSTInput, ElementType.Xpath);
						if (flag) {
							webDB.sendTextToAnElement(CashReceiptLocators.BankReceiptBillDateGSTInput, BillDate,
									ElementType.Xpath);
							webDB.pressTabKey();
							logs.logging("INFO", "Bill Date " + BillDate + " Is Inserted Successfully");
							flag = webDB.waitForElement(CashReceiptLocators.BankReceiptGStBillNumberInputField,
									ElementType.Xpath);
							if (flag) {
								webDB.sendTextToAnElement(CashReceiptLocators.BankReceiptGStBillNumberInputField,
										BillNumber, ElementType.Xpath);
								webDB.pressTabKey();
								logs.logging("INFO", "Bill Number " + BillNumber + " Is inserted Successfully");
								flag = webDB.waitForElement(CashReceiptLocators.BankReceiptGSTDetailsSaveButton,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(CashReceiptLocators.BankReceiptGSTDetailsSaveButton,
											ElementType.Xpath);
									webDB.switchToDefaultContent();
									flag = webDB.waitForElement(CashReceiptLocators.BankReceiptGSTCloseButton,
											ElementType.Xpath);
									if (flag) {
										logs.logging("INFO",
												"Line Wise GST Details Save Button Is Clicked Successfully");
										webDB.clickAnElement(CashReceiptLocators.BankReceiptGSTCloseButton,
												ElementType.Xpath);
										flag = webDB.waitForElement(CashReceiptLocators.BankHeaderCreateJournalButton,
												ElementType.Xpath);
										if (flag) {
											logs.logging("INFO", "GST Details Close Button Is Clicked Successfully");
										}
									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used to Create BR SPSI Through Add Button
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 */
	public void CRSinglePartySingleInvoiceThroughAddButton(String SheetName) {
		excelUtils.writeDataInExcel(CRFilepath, SheetName, 1, 26, "0");
		salesInvoiceFunc.verifyDirectSalesInvoiceBR(webDB.getDataFromProperties("BRSPSI"),
				commonFunc.getDataFromTestDataSheet("CustomerName"));
		excelUtils.writeDataInExcel(CRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
		CashReceiptThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
	}

	/**
	 * This Method Is Used To Create BR SPSI Through Get Document
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 */
	public void CRSinglePartySingleInvoiceThroughGetDocument(String SheetName) {
		excelUtils.writeDataInExcel(CRFilepath, SheetName, 1, 26, "0");
		salesInvoiceFunc.verifyDirectSalesInvoiceBR(webDB.getDataFromProperties("BRSPSI"),
				commonFunc.getDataFromTestDataSheet("CustomerName"));
		excelUtils.writeDataInExcel(CRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		CashReceiptThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getCashPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR SPSI Through pending Docs
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 */
	public void CRSinglePartySingleInvoiceThroughPendingDocs(String SheetName) {
		excelUtils.writeDataInExcel(CRFilepath, SheetName, 1, 26, "0");
		salesInvoiceFunc.verifyDirectSalesInvoiceBR(webDB.getDataFromProperties("BRSPSI"),
				commonFunc.getDataFromTestDataSheet("CustomerName"));
		excelUtils.writeDataInExcel(CRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		CashReceiptThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getCashPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR SPMI Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void CRSinglePartyMultiInvoiceThroughAddButton(String SheetName) {
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String Customer = CashReceipts[i][2];
			String SISheetName = CashReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		CashReceiptThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getCashPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR SPMI Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void CRSinglePartyMultiInvoiceThroughGetDocument(String SheetName) {
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String Customer = CashReceipts[i][2];
			String SISheetName = CashReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		CashReceiptThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getCashPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR SPMI Through Pending Docs
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void CRSinglePartyMultiInvoiceThroughPendingDocs(String SheetName) {
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String Customer = CashReceipts[i][2];
			String SISheetName = CashReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		CashReceiptThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getCashPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR MPSI Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void CRMultiPartySingleInvoiceThroughAddButton(String SheetName) {
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String Customer = CashReceipts[i][2];
			String SISheetName = CashReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBR(SISheetName, Customer);
				excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 25 + i,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(i - 1),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			}
		}
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughAddButtonMPSI(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getCashPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR MPSI Through Get Document
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 */
	public void CRMultiPartySingleInvoiceThroughGetDocument(String SheetName) {
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String Customer = CashReceipts[i][2];
			String SISheetName = CashReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBR(SISheetName, Customer);
				excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 25 + i,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(i - 1),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			}
		}
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		CashReceiptThroughGetDocumentMPSI(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getCashPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR MPSI Through Pending Docs
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void CRMultiPartySingleInvoiceThroughPendingDocs(String SheetName) {
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String Customer = CashReceipts[i][2];
			String SISheetName = CashReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBR(SISheetName, Customer);
				excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 25 + i,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(i - 1),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			}
		}
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		CashReceiptThroughPendingDocsMPSI(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getCashPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR With MPMI Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void CRMultiPartyMultiInvoiceThroughAddButton(String SheetName) {
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String Customer = CashReceipts[i][2];
			String SISheetName = CashReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		CashReceiptThroughAddButtonMPMI(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getCashPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is USed to Create BR MPMI Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void CRMultiPartyMultiInvoiceThroughGetDocument(String SheetName) {
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String Customer = CashReceipts[i][2];
			String SISheetName = CashReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		CashReceiptThroughGetDocumentMPMI(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getCashPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is USed To Create BR MPMI Through Penidng Docs
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void CRMultiPartyMultiInvoiceThroughPendingDocs(String SheetName) {
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String Customer = CashReceipts[i][2];
			String SISheetName = CashReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		CashReceiptThroughPendingDocsMPMI(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getCashPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR MPMI Through Add Button With New Reference
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void CRMultiPartyMultiInvoiceThroughAddButtonWithNewRef(String SheetName) {
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String Customer = CashReceipts[i][2];
			String SISheetName = CashReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		CashReceiptThroughAddButtonMPMIWithNewRef(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getCashPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR MPMI Through Get Document With New Reference
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void CRMultiPartyMultiInvoiceThroughGetDocumentWithNewref(String SheetName) {
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String Customer = CashReceipts[i][2];
			String SISheetName = CashReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		CashReceiptThroughGetDocumentWithNewRef(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getCashPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR MPMI Through Pending Docs With New Reference
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void CRMultiPartyMultiInvoiceThroughPendingDocsWithNewRef(String SheetName) {
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String Customer = CashReceipts[i][2];
			String SISheetName = CashReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		CashReceiptThroughPendingDocsWithNewRef(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getCashPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR SPMI Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void CRSinglePartyMultiInvoiceThroughAddButtonMultiUnits(String SheetName) {
//		CashReceipts = excelUtils.readAllDataInExcel(Filepath, SheetName);
		salesInvoiceFunc.verifyDirectSalesInvoiceBRMultiUnits(webDB.getDataFromProperties("BRSPSI"),
				commonFunc.getDataFromTestDataSheet("CustomerName"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		excelUtils.writeDataInExcel(CRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));

		salesInvoiceFunc.verifyDirectSalesInvoiceBRMultiUnits(webDB.getDataFromProperties("BRSPSI"),
				commonFunc.getDataFromTestDataSheet("CustomerName"), commonFunc.getDataFromTestDataSheet("Loc_49"),
				commonFunc.getDataFromTestDataSheet("InvoicePrefixU3"));
		excelUtils.writeDataInExcel(CRFilepath, SheetName, 1, 27,
				dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(1),
						commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));

		bankreceiptfunc.verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		CashReceiptThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getCashPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR SPMI Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void CRSinglePartyMultiInvoiceThroughGetDocumentMultiUnits(String SheetName) {

		salesInvoiceFunc.verifyDirectSalesInvoiceBRMultiUnits(webDB.getDataFromProperties("BRSPSI"),
				commonFunc.getDataFromTestDataSheet("CustomerName"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		excelUtils.writeDataInExcel(CRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));

		salesInvoiceFunc.verifyDirectSalesInvoiceBRMultiUnits(webDB.getDataFromProperties("BRSPSI"),
				commonFunc.getDataFromTestDataSheet("CustomerName"), commonFunc.getDataFromTestDataSheet("Loc_49"),
				commonFunc.getDataFromTestDataSheet("InvoicePrefixU3"));
		excelUtils.writeDataInExcel(CRFilepath, SheetName, 1, 27,
				dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(1),
						commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));

		bankreceiptfunc.verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		CashReceiptThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getCashPaymentOrReceiptDocNumber());

	}

	/**
	 * This Method Is Used To Create BR SPMI Through Pending Docs
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void CRSinglePartyMultiInvoiceThroughPendingDocsMultiUnits(String SheetName) {

		salesInvoiceFunc.verifyDirectSalesInvoiceBRMultiUnits(webDB.getDataFromProperties("BRSPSI"),
				commonFunc.getDataFromTestDataSheet("CustomerName"), commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("InvoicePrefix"));
		excelUtils.writeDataInExcel(CRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));

		salesInvoiceFunc.verifyDirectSalesInvoiceBRMultiUnits(webDB.getDataFromProperties("BRSPSI"),
				commonFunc.getDataFromTestDataSheet("CustomerName"), commonFunc.getDataFromTestDataSheet("Loc_49"),
				commonFunc.getDataFromTestDataSheet("InvoicePrefixU3"));
		excelUtils.writeDataInExcel(CRFilepath, SheetName, 1, 27,
				dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(1),
						commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		CashReceiptThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getCashPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR SPMI Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void CRMultiPartyMultiInvoiceThroughAddButtonMultiUnits(String SheetName) {
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String Customer = CashReceipts[i][2];
			String SISheetName = CashReceipts[i][4];
			if (!Customer.equals("")) {
				int Index = (i - 1) * 2;
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMultiUnits(SISheetName, Customer,
						commonFunc.getDataFromTestDataSheet("Loc_100"),
						commonFunc.getDataFromTestDataSheet("InvoicePrefix"));

				excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 26,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(Index),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));

				salesInvoiceFunc.verifyDirectSalesInvoiceBRMultiUnits(SISheetName, Customer,
						commonFunc.getDataFromTestDataSheet("Loc_49"),
						commonFunc.getDataFromTestDataSheet("InvoicePrefixU3"));

				excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 27,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(Index + 1),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			}
		}
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		CashReceiptThroughAddButtonMPMI(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getCashPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR SPMI Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void CRMultiPartyMultiInvoiceThroughGetDocumentMultiUnits(String SheetName) {

		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String Customer = CashReceipts[i][2];
			String SISheetName = CashReceipts[i][4];
			if (!Customer.equals("")) {
				int Index = (i - 1) * 2;
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMultiUnits(SISheetName, Customer,
						commonFunc.getDataFromTestDataSheet("Loc_100"),
						commonFunc.getDataFromTestDataSheet("InvoicePrefix"));

				excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 26,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(Index),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));

				salesInvoiceFunc.verifyDirectSalesInvoiceBRMultiUnits(SISheetName, Customer,
						commonFunc.getDataFromTestDataSheet("Loc_49"),
						commonFunc.getDataFromTestDataSheet("InvoicePrefixU3"));

				excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 27,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(Index + 1),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			}
		}
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		CashReceiptThroughGetDocumentMPMI(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getCashPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR SPMI Through Pending Docs
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void CRMultiPartyMultiInvoiceThroughPendingDocsMultiUnits(String SheetName) {

		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String Customer = CashReceipts[i][2];
			String SISheetName = CashReceipts[i][4];
			if (!Customer.equals("")) {
				int Index = (i - 1) * 2;
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMultiUnits(SISheetName, Customer,
						commonFunc.getDataFromTestDataSheet("Loc_100"),
						commonFunc.getDataFromTestDataSheet("InvoicePrefix"));

				excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 26,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(Index),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));

				salesInvoiceFunc.verifyDirectSalesInvoiceBRMultiUnits(SISheetName, Customer,
						commonFunc.getDataFromTestDataSheet("Loc_49"),
						commonFunc.getDataFromTestDataSheet("InvoicePrefixU3"));

				excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 27,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(Index + 1),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			}
		}
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		CashReceiptThroughPendingDocsMPMI(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
		bankreceiptfunc.verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getCashPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Clear Data In The Excel
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void clearDataInExcel(String SheetName) {
		for (int i = 1; i < CashReceipts.length; i++) {
			String GLAccountDesc = CashReceipts[i][1];
			if (!GLAccountDesc.equals("")) {
				for (int j = 1; j <= 2; j++) {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 25 + j, "0");
				}
			}
		}
	}

	/**
	 * This Method Is Used To Click The Pending Party Documents In Bank Payment
	 * Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickPendingAdvanceReceivablesInCR() {
		flag = webDB.waitForElement(CashReceiptLocators.CashReceiptAdvancebutton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashReceiptLocators.CashReceiptAdvancebutton, ElementType.Xpath);
			flag = webDB.waitForElement(CashReceiptLocators.CashReceiptAdvanceFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Bank Receipt Pending Advance Receivables Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Pending Party Documents In Bank Payment
	 * Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickPendingAdvanceReceivablesGetDocInCR() {
		flag = webDB.waitForElement(CashReceiptLocators.CashReceiptAdvpendingReceivablesgetDocument, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashReceiptLocators.CashReceiptAdvpendingReceivablesgetDocument, ElementType.Xpath);
			flag = webDB.waitForElement(CashReceiptLocators.CashReceiptAdvanceFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Bank Receipt Pending Advance Receivables Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The pending payables Button In The Pending CR
	 * Docs
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickPendingAdvanceRecivablesButtonInPendDRDocs() {
		flag = webDB.waitForElement(CashReceiptLocators.PendingReceivablesAdvancePendingDRDocs1, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashReceiptLocators.PendingReceivablesAdvancePendingDRDocs1, ElementType.Xpath);
			flag = webDB.waitForElement(CashReceiptLocators.CashReceiptAdvanceFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Payables Button Is Clicked Successfully In The Pending CR Docs");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used to Select the Sales Invoice pending Records In the
	 * Pending Receivables In the bank Receipt Screen
	 * 
	 * @author Harikrishna _ RMITS
	 * @param PartyName
	 * @param VoucherNumber.get(i)
	 */
	public void selectAdvancereceiptDocumentInPendingBankReceipt(String PartyName, ArrayList<String> VoucherNumber,
			String SheetName) {
		flag = webDB.switchToFrame(CashReceiptLocators.CashReceiptAdvanceFrame, ElementType.Xpath);
		if (flag) {
			String GetTextPartyName = webDB.getDomPropertyFromElement(
					CashReceiptLocators.BankReceiptAdvPendingReceivablesPartyNameInputField, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			if (GetTextPartyName.equals(PartyName)) {
				for (int i = 1; i <= VoucherNumber.size(); i++) {
					commonFunc.searchAndSelectValueInFilters(
							CashReceiptLocators.AdvPendingReceivablesDocumentNumberLovButton, VoucherNumber.get(i - 1));
					webDB.switchToFrame(CashReceiptLocators.CashReceiptAdvanceFrame, ElementType.Xpath);
					flag = webDB.waitForElement(CashReceiptLocators.BankReceiptAdvPendingReceivablesSearchButton,
							ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.doubleClickAnElement(CashReceiptLocators.BankReceiptAdvPendingReceivablesSearchButton,
								ElementType.Xpath);
						flag = webDB.waitForElement(CashReceiptLocators.BRAdvPendingReceivablesDocumentSelectCheckBox(
								VoucherNumber.get(i - 1)), ElementType.Xpath);
						// TODO
//						verifyPendingReceivablesSIDocumentThroughGetDocWithDB(SheetName, VoucherNumber.get(i - 1), i);
						// TODO
						if (flag) {
							commonFunc.checkRefreshCellsDisappear();
							webDB.clickAnElement(CashReceiptLocators.BRAdvPendingReceivablesDocumentSelectCheckBox(
									VoucherNumber.get(i - 1)), ElementType.Xpath);
							commonFunc.hardWait(6000);
							flag = webDB.waitForElement(
									CashReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "In Advance Pending Receivables Documents This Voucher Number "
										+ VoucherNumber.get(i - 1) + " Is Selected Successfully");
							}
						}
					}
				}
				webDB.moveToElement(CashReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
						ElementType.Xpath);
				webDB.javaScriptClickAnElement(CashReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
						ElementType.Xpath);
				commonFunc.hardWait(3000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(CashReceiptLocators.CashReceiptHeaderCreateJournalButton,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO",
							"OK Button Is Clicked Successfully In The Bank Receipt Pending Receivables Documents");
				}
			} else {
				logs.logging("INFO",
						"Bank Receipt Line Wise Party Name And Pending Receivables Party Name Is not Getting Matched");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methos Is Used To Choose Party Name And Select The Purchase Bills In The
	 * Pending Party Documents
	 * 
	 * @author Harikrishna - RMITS
	 * @param PartyName
	 * @param VoucherNumber
	 */
	public void chooseAdvDocumentNumberAndClickSearchButtonInAdvPendingReceivables(ArrayList<String> VoucherNumber,
			String SheetName) {
		flag = webDB.switchToFrame(CashReceiptLocators.CashReceiptAdvanceFrame, ElementType.Xpath);
		if (flag) {
			for (int i = 1; i <= VoucherNumber.size(); i++) {
//				commonFunc.searchAndSelectValueInFilters(
//						BankPaymentLocators.BankPaymentPendingPartyDocumentPartyLovButton, PartyName);
//				webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
//				flag = webDB.waitForElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
//						ElementType.Xpath);
//				if (flag) {
//					webDB.clickAnElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
//							ElementType.Xpath);
//					commonFunc.checkRefreshCellsDisappear();
				commonFunc.searchAndSelectValueInFilters(
						CashReceiptLocators.AdvPendingReceivablesDocumentNumberLovButton, VoucherNumber.get(i - 1));
				webDB.switchToFrame(CashReceiptLocators.CashReceiptAdvanceFrame, ElementType.Xpath);
				flag = webDB.waitForElement(CashReceiptLocators.BankReceiptAdvPendingReceivablesSearchButton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CashReceiptLocators.BankReceiptAdvPendingReceivablesSearchButton,
							ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(1000);
					String GetTextDocumentNumber = webDB.getTextFromElement(
							CashReceiptLocators.AdvPendingReceivablesDocumentNumberField, ElementType.Xpath);
					logs.logging("INFO", "Get Text Document Number Is " + GetTextDocumentNumber);
					if (GetTextDocumentNumber.equals(VoucherNumber.get(i - 1))) {
						flag = webDB.waitForElement(CashReceiptLocators.BRAdvPendingReceivablesDocumentSelectCheckBox(
								VoucherNumber.get(i - 1)), ElementType.Xpath);
//						verifyPendingReceivablesSIDocumentThroughGetDocWithDB(SheetName, VoucherNumber.get(i - 1), i);
						if (flag) {
							webDB.clickAnElement(CashReceiptLocators.BRAdvPendingReceivablesDocumentSelectCheckBox(
									VoucherNumber.get(i - 1)), ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(3000);
							flag = webDB.waitForElement(
									CashReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "In Pending Receivables Documents This Voucher Number "
										+ VoucherNumber.get(i - 1) + " Is Selected Successfully");
							}
						}
					} else {
						logs.logging("INFO",
								"Bank Receipt Pending Receivables Line Wise Document Number And Pending Receivables Documents Number Is not Getting Matched");
					}
				}
			}
			commonFunc.hardWait(1500);
			flag = webDB.waitForElement(CashReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
					ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(CashReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
						ElementType.Xpath);
				webDB.javaScriptClickAnElement(CashReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
						ElementType.Xpath);
				commonFunc.hardWait(500);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(CashReceiptLocators.CashReceiptHeaderCreateJournalButton,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Ok Button Is Clicked Successfully In The Pending Receivables Screen");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is USed To Select One Document And Convert Into Bank / Cash
	 * Payment
	 * 
	 * @author Harikrishna - RMITS
	 * @param DocumentNumber
	 * @param Type
	 * @param PayMode
	 * @param BankName
	 */
	public void chooseAdvDocumentNumberInPendingAdvReceivablesScreen(ArrayList<String> DocumentNumber, String Type,
			String PayMode, String BankName, String CreationType, String SheetName) {
		flag = webDB.waitForElement(CashReceiptLocators.CashReceiptAdvanceFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(CashReceiptLocators.CashReceiptAdvanceFrame, ElementType.Xpath);
			for (int i = 1; i <= DocumentNumber.size(); i++) {
				commonFunc.searchAndSelectValueInFilters(
						CashReceiptLocators.AdvPendingReceivablesDocumentNumberLovButton, DocumentNumber.get(i - 1));
				webDB.switchToFrame(CashReceiptLocators.CashReceiptAdvanceFrame, ElementType.Xpath);
				flag = webDB.waitForElement(CashReceiptLocators.BankReceiptAdvPendingReceivablesSearchButton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CashReceiptLocators.BankReceiptAdvPendingReceivablesSearchButton,
							ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					String GetTextDocumentNumber = webDB.getTextFromElement(
							CashReceiptLocators.AdvPendingReceivablesDocumentNumberField, ElementType.Xpath);
					if (GetTextDocumentNumber.equals(DocumentNumber.get(i - 1))) {
//						verifyPendingReceivablesSIDocumentThroughGetDocWithDB123(SheetName, DocumentNumber.get(i - 1),
//								i);
						commonFunc.hardWait(1000);
						webDB.clickAnElement(CashReceiptLocators.BRAdvPendingReceivablesDocumentSelectCheckBox(
								DocumentNumber.get(i - 1)), ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(1500);
						flag = webDB.waitForElement(CashReceiptLocators.PendingReceivanlesAdvanceCreateButton,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "This Sales Invoice Document " + DocumentNumber.get(i - 1)
									+ " Is Selected Successfully");
							if (DocumentNumber.size() > 1) {
								commonFunc.checkRefreshCellsDisappear();
								webDB.clickAnElement(CashReceiptLocators.BankPendingAdvPartyDocumentClearButton,
										ElementType.Xpath);
								flag = webDB.waitForElement(
										CashReceiptLocators.BankReceiptAdvPendingReceivablesSearchButton,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(
											CashReceiptLocators.BankReceiptAdvPendingReceivablesSearchButton,
											ElementType.Xpath);
									commonFunc.hardWait(1500);
									commonFunc.checkRefreshCellsDisappear();
								}
							}
						}
					} else {
						logs.logging("INFO", "Some Other Document Is Filtered");
					}
				}
			}
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(2000);
			webDB.moveToElement(CashReceiptLocators.PendingReceivanlesAdvanceCreateButton, ElementType.Xpath);
			webDB.javaScriptClickAnElement(CashReceiptLocators.PendingReceivanlesAdvanceCreateButton,
					ElementType.Xpath);
			logs.logging("INFO", "Create Button Is Clicked Successfully");
			commonFunc.checkRefreshCellsDisappear();
			if (!CreationType.equals("Single")) {
				flag = webDB.waitForElement(CashReceiptLocators.PendingReceivablesAdvMergeLabelInPending,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CashReceiptLocators.PendingReceivablesAdvMergeLabelInPending,
							ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
				}
			}
			flag = webDB.waitForElement(CashReceiptLocators.AdvancePendingReceivablesTypeDropDown, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownByText(CashReceiptLocators.AdvancePendingReceivablesTypeDropDown, Type,
						ElementType.Xpath);
				logs.logging("INFO", "Type Successfully Choosen As " + Type);
				if (Type.equals("Bank")) {
					flag = webDB.waitForElement(CashReceiptLocators.PendingReceivablesPayModeDropDown,
							ElementType.Xpath);
					if (flag) {
						webDB.selectDropDownByText(CashReceiptLocators.PendingReceivablesPayModeDropDown, PayMode,
								ElementType.Xpath);
						logs.logging("INFO", "Pay Mode Successfully Choosen As " + PayMode);
					}
				}
				commonFunc.searchAndSelectValueInFilters(CashReceiptLocators.AdvPendingReceivablesBankNameLOVBUtton,
						BankName);
				webDB.switchToFrame(CashReceiptLocators.CashReceiptAdvanceFrame, ElementType.Xpath);
				flag = webDB.waitForElement(CashReceiptLocators.AdvPendingReceivablesOkButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CashReceiptLocators.AdvPendingReceivablesOkButton, ElementType.Xpath);
					webDB.switchToDefaultContent();
					if (Type.equals("Bank")) {
						flag = webDB.waitForElement(CashReceiptLocators.BankReceiptScreenName, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"OK Button Is Clicked Successfully And Navigated To Bank Receipt Screen");
						}
					} else {
						flag = webDB.waitForElement(CashReceiptLocators.CashPaymentScreenName, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"OK Button Is Clicked Successfully And Navigated To Cash Receipt Screen");
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is used To Add The Bank Receipt Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return
	 */
	public void AdvCashReceiptThroughAddButton(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		createCashReceiptEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCR"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashReceiptDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"), Currency);
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String GLAccountDesc = CashReceipts[i][1];
			String PartyName = CashReceipts[i][3];
			String EntityName = CashReceipts[i][5];
			String CPCCodeDesc = CashReceipts[i][6];
			String BillNumber = CashReceipts[i][7];
			String PayAmount = CashReceipts[i][8];
			String DRCR = CashReceipts[i][9];
			String CURCY = CashReceipts[i][10];
			String EXRate = CashReceipts[i][11];
			String TransCURCY = CashReceipts[i][12];
			String BaseCURCY = CashReceipts[i][13];
			String AssesableValue = CashReceipts[i][16];
			if (!GLAccountDesc.equals("")) {
				clickCRLineAddButton();
				addCashReceiptLineDetails(GLAccountDesc, CPCCodeDesc);
				clickCRLineWiseSaveButton();
				clickCRLineWiseAddButton(i);
				clickPendingAdvanceReceivablesInCR();
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, "1");
				}
				selectAdvancereceiptDocumentInPendingBankReceipt(PartyName, VariableHandler.getMultipleAdvRecDocs(),
						SheetName);
//				verifyCashReceiptLineDetailsWithDB(SheetName, i);
			}
		}
		clickCreateJournalButtonInCR();
		commonFunc.checkRefreshCellsDisappear();
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCRVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method is Used To Add The Document Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param Currency
	 */
	public void AdvCashReceiptThroughGetDocument(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		createCashReceiptEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCR"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashReceiptDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"), Currency);
		clickGetDocumentInCRHeader();
		clickPendingAdvanceRecivablesButtonInPendDRDocs();
//		clickPendingReceivablesInGetDocumentCR();
		chooseAdvDocumentNumberAndClickSearchButtonInAdvPendingReceivables(VariableHandler.getMultipleAdvRecDocs(),
				SheetName);
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String GLAccountDesc = CashReceipts[i][1];
			String BaseCURCY = CashReceipts[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, "1");
				}
//				verifyCashReceiptLineDetailsWithDB(SheetName, i);
			}
		}
		clickCreateJournalButtonInCR();
		commonFunc.checkRefreshCellsDisappear();
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCRVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The Document Through pending Documents
	 * 
	 * @author Harikrishna - RMITS
	 * @param CreationType
	 * @param SheetName
	 * @param Currency
	 */
	public void AdvCashReceiptThroughPendingDocs(String CreationType, String SheetName, String Currency,
			String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingDRDocsButton();
		clickPendingAdvanceRecivablesButtonInPendDRDocs();
		chooseAdvDocumentNumberInPendingAdvReceivablesScreen(VariableHandler.getMultipleAdvRecDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentTypeCash"),
				commonFunc.getDataFromTestDataSheet("BankPayMode"), CashierName, CreationType, SheetName);
		getCashPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String GLAccountDesc = CashReceipts[i][1];
			String BaseCURCY = CashReceipts[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, "1");
				}
				commonFunc.hardWait(1000);
//				verifyCashReceiptLineDetailsWithDB(SheetName, i);
//			}
			}
		}
		clickCreateJournalButtonInCR();
		commonFunc.checkRefreshCellsDisappear();
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCRVDocumentStatus();
		}
	}

	public void CRAdvanceThroughAddButton(String SheetName) throws IOException {
		salesOrdFunc.advanceSalesOrder();
		VariableHandler.setMultipleAdvRecDocs(salesOrdFunc.MultiAdvReceiptDocumentNumber);
		AdvCashReceiptThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));

	}

	/**
	 * This Method Is Used To Create BR SPSI Through Get Document
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 * @throws IOException
	 */
	public void CRAdvanceThroughGetDocument(String SheetName) throws IOException {
		salesOrdFunc.advanceSalesOrder();
		VariableHandler.setMultipleAdvRecDocs(salesOrdFunc.MultiAdvReceiptDocumentNumber);
		AdvCashReceiptThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
	}

	/**
	 * This Method Is Used To Create BR SPSI Through pending Docs
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 * @throws IOException
	 */
	public void CRAdvanceThroughPendingDocs(String SheetName) throws IOException {
		salesOrdFunc.advanceSalesOrder();
		VariableHandler.setMultipleAdvRecDocs(salesOrdFunc.MultiAdvReceiptDocumentNumber);
		AdvCashReceiptThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
	}

	public void CRAdvanceMultiSOThroughAddButton(String SheetName) throws IOException {
		for (int i = 1; i <= 2; i++) {
			salesOrdFunc.advanceSalesOrder();
		}
		VariableHandler.setMultipleAdvRecDocs(salesOrdFunc.MultiAdvReceiptDocumentNumber);
		AdvCashReceiptThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));

	}

	/**
	 * This Method Is Used To Create BR SPSI Through Get Document
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 * @throws IOException
	 */
	public void CRAdvanceMultiSOThroughGetDocument(String SheetName) throws IOException {
		for (int i = 1; i <= 2; i++) {
			salesOrdFunc.advanceSalesOrder();
		}
		VariableHandler.setMultipleAdvRecDocs(salesOrdFunc.MultiAdvReceiptDocumentNumber);
		AdvCashReceiptThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
	}

	/**
	 * This Method Is Used To Create BR SPSI Through pending Docs
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 * @throws IOException
	 */
	public void CRAdvanceMultiSOThroughPendingDocs(String SheetName) throws IOException {
		for (int i = 1; i <= 2; i++) {
			salesOrdFunc.advanceSalesOrder();
		}
		VariableHandler.setMultipleAdvRecDocs(salesOrdFunc.MultiAdvReceiptDocumentNumber);
		AdvCashReceiptThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
	}

	public void CRAdvanceWithNewRef(String SheetName) throws IOException {
		AdvCashReceiptWithNewRef(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));

	}

	public void CRAdvanceWithMultiRef(String SheetName) throws IOException {
		AdvCashReceiptWithMultiRef(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));

	}

	/**
	 * This Method Is used To Add The Bank Receipt Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return
	 */
	public void AdvCashReceiptWithNewRef(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		createCashReceiptEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCR"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashReceiptDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"), Currency);
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String GLAccountDesc = CashReceipts[i][1];
			String PartyName = CashReceipts[i][3];
			String EntityName = CashReceipts[i][5];
			String CPCCodeDesc = CashReceipts[i][6];
			String BillNumber = CashReceipts[i][7];
			String PayAmount = CashReceipts[i][8];
			String DRCR = CashReceipts[i][9];
			String CURCY = CashReceipts[i][10];
			String EXRate = CashReceipts[i][11];
			String TransCURCY = CashReceipts[i][12];
			String BaseCURCY = CashReceipts[i][13];
			String AssesableValue = CashReceipts[i][16];
			if (!GLAccountDesc.equals("")) {
				clickCRLineAddButton();
				addCashReceiptLineDetails(GLAccountDesc, CPCCodeDesc);
				clickCRLineWiseSaveButton();
				clickLineWiseViewButton(i);
				clickAddButtonInViewDetails();
				addBillNumberAndPayAmount(BillNumber, PayAmount);
				clickViewDetailsSaveButton();
				clickBankViewDetailsCloseButton();
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, "1");
				}
				verifyCashReceiptLineDetailsWithDB(SheetName, i);
			}
		}
		clickCreateJournalButtonInCR();
		commonFunc.checkRefreshCellsDisappear();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CRJournalNewRef"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), CRFilepath);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCRVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is used To Add The Bank Receipt Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return
	 */
	public void AdvCashReceiptWithMultiRef(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		createCashReceiptEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCR"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashReceiptDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashReceiptPrefix"), Currency);
		CashReceipts = excelUtils.readAllDataInExcel(CRFilepath, SheetName);
		for (int i = 1; i < CashReceipts.length; i++) {
			String GLAccountDesc = CashReceipts[i][1];
			String PartyName = CashReceipts[i][3];
			String EntityName = CashReceipts[i][5];
			String CPCCodeDesc = CashReceipts[i][6];
			String BillNumber = CashReceipts[i][7];
			String PayAmount = CashReceipts[i][8];
			String DRCR = CashReceipts[i][9];
			String CURCY = CashReceipts[i][10];
			String EXRate = CashReceipts[i][11];
			String TransCURCY = CashReceipts[i][12];
			String BaseCURCY = CashReceipts[i][13];
			String AssesableValue = CashReceipts[i][16];
			if (!GLAccountDesc.equals("")) {
				clickCRLineAddButton();
				addCashReceiptLineDetails(GLAccountDesc, CPCCodeDesc);
				clickCRLineWiseSaveButton();
				clickLineWiseViewButton(i);
				for (int j = 0; j < 2; j++) {
					clickAddButtonInViewDetails();
					addBillNumberAndPayAmount(BillNumber, PayAmount);
					clickViewDetailsSaveButton();
					commonFunc.checkRefreshCellsDisappear();
				}
				clickBankViewDetailsCloseButton();
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(CRFilepath, SheetName, i, 11, "1");
				}
				verifyCashReceiptLineDetailsWithDB(SheetName, i);
			}
		}
		clickCreateJournalButtonInCR();
		commonFunc.checkRefreshCellsDisappear();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CRJournalMultiRef"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), CRFilepath);
//		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCRVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used to set the Variable Handler To Zero After Finishing One
	 * Test Scenarios
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void verifyAfterMethod() {
		salesInvoiceFunc.MultiSIDocNumber.clear();
//		Grnfunc.MultiGRNDocs.clear();
		PBDocumentNumber.clear();
		VariableHandler.setMultipleSIDocs(null);
		webDB.navigateToRefresh();
		webDB.switchToDefaultContent();
		commonFunc.enterUrl();
	}

}