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
import commonlocators.financials.PaymentReceiptsLocators;
import commonlocators.financials.PurchaseBillsLocators;
import commonlocators.supplychainmanagement.MaterialIssuanceVoucherLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import commonlocators.supplychainmanagement.SalesOrderLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class BankReceiptFunctions {
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

	LocalDate date = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	String Today = date.format(formatter);
	LocalDateTime myDateObj = LocalDateTime.now().plusDays(1);
	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	String tomorrowDate = myDateObj.format(myFormatObj);
	String[][] BankReceipts = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
	ExcelUtils excelUtils = new ExcelUtils();
	String BRFilepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
			+ "Financials.xlsx";
	ArrayList<String> PBDocumentNumber = new ArrayList<String>();
//	SOMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
//	for (int i = 4; i < SOMultipleline.length; i++) {

	/**
	 * This Method Is Used To Click The Pending CR Docs Button In The Payment /
	 * Receipts Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickPendingDRDocsButton() {
		flag = webDB.waitForElement(BankReceiptLocators.PendingDRDocsButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankReceiptLocators.PendingDRDocsButton, ElementType.Xpath);
			flag = webDB.waitForElement(BankReceiptLocators.PendingReceivables, ElementType.Xpath);
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
		flag = webDB.waitForElement(BankReceiptLocators.PendingReceivables, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankReceiptLocators.PendingReceivables, ElementType.Xpath);
			flag = webDB.waitForElement(BankReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Payables Button Is Clicked Successfully In The Pending CR Docs");
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
	public void clickPendingReceivablesInBR() {
		flag = webDB.waitForElement(BankReceiptLocators.BankReceiptPendingReceivablesButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankReceiptLocators.BankReceiptPendingReceivablesButton, ElementType.Xpath);
			flag = webDB.waitForElement(BankReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Bank Receipt Pending Receivables Is Clicked Successfully");
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
	public void selectSalesInvoiceDocumentInPendingBankReceipt(String PartyName, ArrayList<String> VoucherNumber,
			String SheetName) {
		flag = webDB.switchToFrame(BankReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
		if (flag) {
			String GetTextPartyName = webDB.getDomPropertyFromElement(
					BankReceiptLocators.BankReceiptPendingReceivablesPartyNameInputField, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			if (GetTextPartyName.equals(PartyName)) {
				for (int i = 1; i <= VoucherNumber.size(); i++) {
					commonFunc.searchAndSelectValueInFilters(BankReceiptLocators.BankReceiptDocNumberLovButton,
							VoucherNumber.get(i - 1));
					webDB.switchToFrame(BankReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
					flag = webDB.waitForElement(BankReceiptLocators.BankReceiptPendingReceivablesSearchButton,
							ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.doubleClickAnElement(BankReceiptLocators.BankReceiptPendingReceivablesSearchButton,
								ElementType.Xpath);
						flag = webDB.waitForElement(BankReceiptLocators.BRPendingReceivablesDocumentSelectCheckBox(
								VoucherNumber.get(i - 1)), ElementType.Xpath);
						// TODO
//						verifyPendingReceivablesSIDocumentWithDB(SheetName, VoucherNumber.get(i));
						verifyPendingReceivablesSIDocumentThroughGetDocWithDB(SheetName, VoucherNumber.get(i - 1), i);
						// TODO
						if (flag) {
							commonFunc.checkRefreshCellsDisappear();
							webDB.clickAnElement(BankReceiptLocators.BRPendingReceivablesDocumentSelectCheckBox(
									VoucherNumber.get(i - 1)), ElementType.Xpath);
							commonFunc.hardWait(6000);
							flag = webDB.waitForElement(BankReceiptLocators.BankReceiptPendingReceivableScreenOkButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "In Pending Receivables Documents This Voucher Number "
										+ VoucherNumber.get(i - 1) + " Is Selected Successfully");
							}
						}
					}
				}
				webDB.moveToElement(BankReceiptLocators.BankReceiptPendingReceivableScreenOkButton, ElementType.Xpath);
				webDB.javaScriptClickAnElement(BankReceiptLocators.BankReceiptPendingReceivableScreenOkButton,
						ElementType.Xpath);
				commonFunc.hardWait(3000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(BankReceiptLocators.BankReceiptHeaderCreateJournalButton,
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
	 * This Method Is Used to Select the Sales Invoice pending Records In the
	 * Pending Receivables In the bank Receipt Screen
	 * 
	 * @author Harikrishna _ RMITS
	 * @param PartyName
	 * @param VoucherNumber.get(i)
	 */
	public void selectSalesInvoiceDocumentInPendingBankReceiptMPSI(String PartyName, String VoucherNumber,
			String SheetName, int i) {
		flag = webDB.switchToFrame(BankReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
		if (flag) {
			String GetTextPartyName = webDB.getDomPropertyFromElement(
					BankReceiptLocators.BankReceiptPendingReceivablesPartyNameInputField, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			if (GetTextPartyName.equals(PartyName)) {
//				for (int i = 0; i < VoucherNumber.size(); i++) {
				commonFunc.searchAndSelectValueInFilters(BankReceiptLocators.BankReceiptDocNumberLovButton,
						VoucherNumber);
				webDB.switchToFrame(BankReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(BankReceiptLocators.BankReceiptPendingReceivablesSearchButton,
						ElementType.Xpath);
				if (flag) {
					commonFunc.checkRefreshCellsDisappear();
					webDB.doubleClickAnElement(BankReceiptLocators.BankReceiptPendingReceivablesSearchButton,
							ElementType.Xpath);
					flag = webDB.waitForElement(
							BankReceiptLocators.BRPendingReceivablesDocumentSelectCheckBox(VoucherNumber),
							ElementType.Xpath);
					verifyPendingReceivablesSIDocumentMPSIWithDB(SheetName, VoucherNumber, i);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.clickAnElement(
								BankReceiptLocators.BRPendingReceivablesDocumentSelectCheckBox(VoucherNumber),
								ElementType.Xpath);
						commonFunc.hardWait(6000);
						flag = webDB.waitForElement(BankReceiptLocators.BankReceiptPendingReceivableScreenOkButton,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "In Pending Receivables Documents This Voucher Number " + VoucherNumber
									+ " Is Selected Successfully");
						}
					}
				}
//				}
				webDB.moveToElement(BankReceiptLocators.BankReceiptPendingReceivableScreenOkButton, ElementType.Xpath);
				webDB.javaScriptClickAnElement(BankReceiptLocators.BankReceiptPendingReceivableScreenOkButton,
						ElementType.Xpath);
				commonFunc.hardWait(3000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(BankReceiptLocators.BankReceiptHeaderCreateJournalButton,
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
	 * This Method Is Used to Select the Sales Invoice pending Records In the
	 * Pending Receivables In the bank Receipt Screen
	 * 
	 * @author Harikrishna _ RMITS
	 * @param PartyName
	 * @param VoucherNumber.get(i)
	 */
	public void selectSalesInvoiceDocumentInPendingBankReceiptMPMI(String PartyName, ArrayList<String> VoucherNumber,
			int i1) {
		flag = webDB.switchToFrame(BankReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
		if (flag) {
			String GetTextPartyName = webDB.getDomPropertyFromElement(
					BankReceiptLocators.BankReceiptPendingReceivablesPartyNameInputField, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			int a = i1 * 2;
			if (GetTextPartyName.equals(PartyName)) {
				for (int i = a - 1; i <= a; i++) {
					commonFunc.searchAndSelectValueInFilters(BankReceiptLocators.BankReceiptDocNumberLovButton,
							VoucherNumber.get(i - 1));
					webDB.switchToFrame(BankReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
					flag = webDB.waitForElement(BankReceiptLocators.BankReceiptPendingReceivablesSearchButton,
							ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.doubleClickAnElement(BankReceiptLocators.BankReceiptPendingReceivablesSearchButton,
								ElementType.Xpath);
						flag = webDB.waitForElement(BankReceiptLocators.BRPendingReceivablesDocumentSelectCheckBox(
								VoucherNumber.get(i - 1)), ElementType.Xpath);
						if (flag) {
							commonFunc.checkRefreshCellsDisappear();
							webDB.clickAnElement(BankReceiptLocators.BRPendingReceivablesDocumentSelectCheckBox(
									VoucherNumber.get(i - 1)), ElementType.Xpath);
							commonFunc.hardWait(6000);
							flag = webDB.waitForElement(BankReceiptLocators.BankReceiptPendingReceivableScreenOkButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "In Pending Receivables Documents This Voucher Number "
										+ VoucherNumber.get(i - 1) + " Is Selected Successfully");
							}
						}
					}
				}
				webDB.moveToElement(BankReceiptLocators.BankReceiptPendingReceivableScreenOkButton, ElementType.Xpath);
				webDB.javaScriptClickAnElement(BankReceiptLocators.BankReceiptPendingReceivableScreenOkButton,
						ElementType.Xpath);
				commonFunc.hardWait(3000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(BankReceiptLocators.BankReceiptHeaderCreateJournalButton,
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
	 * This Method Is Used To Click the Line Wise Add Button In The Bank Payment
	 * Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickBRLineAddButton() {
		flag = webDB.waitForElement(BankReceiptLocators.BankReceiptLineWiseAddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankReceiptLocators.BankReceiptLineWiseAddButton, ElementType.Xpath);
			flag = webDB.waitForElement(BankReceiptLocators.BankLineGLAccountField, ElementType.Xpath);
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
	public void addBankReceiptLineDetails(String GLAccount, String CPCcode) {
		flag = webDB.waitForElement(BankReceiptLocators.BankLineGLAccountField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankReceiptLocators.BankLineGLAccountField, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(BankReceiptLocators.BankLineGLAccountLovButton, GLAccount);
			webDB.pressTabKey();
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(BankReceiptLocators.BankLineCPCField, ElementType.Xpath);
			if (flag) {
//				commonFunc.checkRefreshCellsDisappear();
				String GetTextCPC = webDB.getTextFromElement(BankReceiptLocators.BankLineCPCField, ElementType.Xpath);
				logs.logging("INFO", "Get Text CPC Code Is : " + GetTextCPC);
				if (!GetTextCPC.equals(CPCcode)) {
					webDB.clickAnElement(BankReceiptLocators.BankLineCPCField, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(BankReceiptLocators.BankLineCPCLovButton, CPCcode);
					commonFunc.checkRefreshCellsDisappear();
					webDB.pressTabKey();
				}
				webDB.pressTabKey();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method is Used to Click The Line Wise Save Button In the Bank Payment
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickBRLineWiseSaveButton() {
		flag = webDB.waitForElement(BankReceiptLocators.BankLineWiseSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankReceiptLocators.BankLineWiseSaveButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElementUntilGivenTime(BankReceiptLocators.BankLineWiseDeleteButton,
					ElementType.CssSelector, 60);
			if (flag) {
				logs.logging("INFO", "Bank Receipt Line Wise Save button Is clicked Successfully");
				commonFunc.hardWait(500);
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Create JOurnal Button In The Header Of the
	 * Bank Receipt Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickCreateJournalButtonInBR() {
		flag = webDB.waitForElement(BankReceiptLocators.BankReceiptHeaderCreateJournalButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankReceiptLocators.BankReceiptHeaderCreateJournalButton, ElementType.Xpath);
			flag = webDB.waitForElement(BankReceiptLocators.BankHeaderViewJournalButton, ElementType.Xpath);
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
		flag = webDB.waitForElement(BankReceiptLocators.BankPostButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankReceiptLocators.BankPostButton, ElementType.Xpath);
			flag = webDB.waitForElement(BankReceiptLocators.BankWorkFlowApprovalFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Post Button Is Clicked Successfully");
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
	public void clickBRLineWiseAddButton(int i) {
		flag = webDB.waitForElement(BankReceiptLocators.BankLineAddButton(i), ElementType.CssSelector);
		if (flag) {
			webDB.clickAnElement(BankReceiptLocators.BankLineAddButton(i), ElementType.CssSelector);
			flag = webDB.waitForElement(BankReceiptLocators.BankPendingReceivablesButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Bank Receipt Line Wise Add Button Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Get Document Button In The Header of The
	 * Bank Payment Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickGetDocumentInBRHeader() {
		flag = webDB.waitForElement(BankReceiptLocators.BankPaymentGetDocumentButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankReceiptLocators.BankPaymentGetDocumentButton, ElementType.Xpath);
			flag = webDB.waitForElement(BankReceiptLocators.BankPendingReceivablesButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Get Document Button In The Bank Receipt Header Is Clicked Successfully");
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
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.searchAndSelectValueInFilters(BankReceiptLocators.BankReceiptDocNumberLovButton,
						VoucherNumber.get(i - 1));
				webDB.switchToFrame(BankReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(BankReceiptLocators.BankReceiptPendingReceivablesSearchButton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankReceiptLocators.BankReceiptPendingReceivablesSearchButton,
							ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(1000);
					String GetTextDocumentNumber = webDB.getTextFromElement(
							BankReceiptLocators.PendingReceivablesDocumentNumberField, ElementType.Xpath);
					logs.logging("INFO", "Get Text Document Number Is " + GetTextDocumentNumber);
					if (GetTextDocumentNumber.equals(VoucherNumber.get(i - 1))) {
						flag = webDB.waitForElement(BankReceiptLocators.BRPendingReceivablesDocumentSelectCheckBox(
								VoucherNumber.get(i - 1)), ElementType.Xpath);
//						verifyPendingReceivablesSIDocumentWithDB(SheetName, VoucherNumber.get(i));
						verifyPendingReceivablesSIDocumentThroughGetDocWithDB(SheetName, VoucherNumber.get(i - 1), i);
//						verifyPendingReceivablesSIDocumentThroughGetDocWithDB123(SheetName, VoucherNumber.get(i-1),i);
						if (flag) {
							webDB.clickAnElement(BankReceiptLocators.BRPendingReceivablesDocumentSelectCheckBox(
									VoucherNumber.get(i - 1)), ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(3000);
							flag = webDB.waitForElement(
									BankReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
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
			flag = webDB.waitForElement(BankReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
					ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(BankReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
						ElementType.Xpath);
				webDB.javaScriptClickAnElement(BankReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
						ElementType.Xpath);
				commonFunc.hardWait(500);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(BankReceiptLocators.BankReceiptHeaderCreateJournalButton,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Ok Button Is Clicked Successfully In The Pending Receivables Screen");
				}
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
				commonFunc.searchAndSelectValueInFilters(BankReceiptLocators.BankReceiptDocNumberLovButton,
						VoucherNumber.get(i - 1));
				webDB.switchToFrame(BankReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(BankReceiptLocators.BankReceiptPendingReceivablesSearchButton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankReceiptLocators.BankReceiptPendingReceivablesSearchButton,
							ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(1000);
					String GetTextDocumentNumber = webDB.getTextFromElement(
							BankReceiptLocators.PendingReceivablesDocumentNumberField, ElementType.Xpath);
					logs.logging("INFO", "Get Text Document Number Is " + GetTextDocumentNumber);
					if (GetTextDocumentNumber.equals(VoucherNumber.get(i - 1))) {
						flag = webDB.waitForElement(BankReceiptLocators.BRPendingReceivablesDocumentSelectCheckBox(
								VoucherNumber.get(i - 1)), ElementType.Xpath);
//						verifyPendingReceivablesSIDocumentWithDB(SheetName, VoucherNumber.get(i));
//						verifyPendingReceivablesSIDocumentThroughGetDocWithDB(SheetName, VoucherNumber.get(i-1),i);
						verifyPendingReceivablesSIDocumentThroughGetDocWithDB123(SheetName, VoucherNumber.get(i - 1),
								i);
						if (flag) {
							webDB.clickAnElement(BankReceiptLocators.BRPendingReceivablesDocumentSelectCheckBox(
									VoucherNumber.get(i - 1)), ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(3000);
							flag = webDB.waitForElement(
									BankReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
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
			flag = webDB.waitForElement(BankReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
					ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(BankReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
						ElementType.Xpath);
				webDB.javaScriptClickAnElement(BankReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
						ElementType.Xpath);
				commonFunc.hardWait(500);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(BankReceiptLocators.BankReceiptHeaderCreateJournalButton,
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
	public void chooseDocumentNumberInPendingPayablesScreen(ArrayList<String> DocumentNumber, String Type,
			String PayMode, String BankName, String CreationType, String SheetName) {
		flag = webDB.waitForElement(BankReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(BankReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
			for (int i = 1; i <= DocumentNumber.size(); i++) {
				commonFunc.searchAndSelectValueInFilters(
						BankReceiptLocators.PendingReceivablesDRDocumentNumberLOVButton, DocumentNumber.get(i - 1));
				webDB.switchToFrame(BankReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(BankReceiptLocators.BankReceiptPendingReceivablesSearchButton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankReceiptLocators.BankReceiptPendingReceivablesSearchButton,
							ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					String GetTextDocumentNumber = webDB.getTextFromElement(
							BankReceiptLocators.PendingReceivablesDocumentNumberField, ElementType.Xpath);
					if (GetTextDocumentNumber.equals(DocumentNumber.get(i - 1))) {
//						verifyPendingReceivablesSIDocumentWithDB(SheetName, DocumentNumber.get(i));
						verifyPendingReceivablesSIDocumentThroughGetDocWithDB(SheetName, DocumentNumber.get(i - 1), i);
//						verifyPendingReceivablesSIDocumentThroughGetDocWithDB123(SheetName, DocumentNumber.get(i - 1),
//								i);
						commonFunc.hardWait(1000);
						webDB.clickAnElement(BankReceiptLocators.BRPendingReceivablesDocumentSelectCheckBox(
								DocumentNumber.get(i - 1)), ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(1500);
						flag = webDB.waitForElement(BankReceiptLocators.PendingPayablesCreateButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "This Sales Invoice Document " + DocumentNumber.get(i - 1)
									+ " Is Selected Successfully");
							if (DocumentNumber.size() > 1) {
								commonFunc.checkRefreshCellsDisappear();
								webDB.clickAnElement(BankReceiptLocators.BankPendingPartyDocumentClearButton,
										ElementType.Xpath);
								flag = webDB.waitForElement(
										BankPaymentLocators.BankReceiptPendingReceivablesSearchButton,
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
			webDB.moveToElement(BankReceiptLocators.PendingPayablesCreateButton, ElementType.Xpath);
			webDB.javaScriptClickAnElement(BankReceiptLocators.PendingPayablesCreateButton, ElementType.Xpath);
			logs.logging("INFO", "Create Button Is Clicked Successfully");
			commonFunc.checkRefreshCellsDisappear();
			if (!CreationType.equals("Single")) {
				flag = webDB.waitForElement(BankReceiptLocators.PendingReceivablesMergeLabelInPending,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankReceiptLocators.PendingReceivablesMergeLabelInPending, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
				}
			}
			flag = webDB.waitForElement(BankReceiptLocators.PendingReceivablesTypeDropDown, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownByText(BankReceiptLocators.PendingReceivablesTypeDropDown, Type, ElementType.Xpath);
				logs.logging("INFO", "Type Successfully Choosen As " + Type);
				if (Type.equals("Bank")) {
					flag = webDB.waitForElement(BankReceiptLocators.PendingReceivablesPayModeDropDown,
							ElementType.Xpath);
					if (flag) {
						webDB.selectDropDownByText(BankReceiptLocators.PendingReceivablesPayModeDropDown, PayMode,
								ElementType.Xpath);
						logs.logging("INFO", "Pay Mode Successfully Choosen As " + PayMode);
					}
				}
				commonFunc.searchAndSelectValueInFilters(BankReceiptLocators.PendingReceivablesBankNameLOVBUtton,
						BankName);
				webDB.switchToFrame(BankReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(BankReceiptLocators.PendingReceivablesOkButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankReceiptLocators.PendingReceivablesOkButton, ElementType.Xpath);
					webDB.switchToDefaultContent();
					if (Type.equals("Bank")) {
						flag = webDB.waitForElement(BankReceiptLocators.BankReceiptScreenName, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"OK Button Is Clicked Successfully And Navigated To Bank Receipt Screen");
						}
					} else {
						flag = webDB.waitForElement(BankReceiptLocators.CashPaymentScreenName, ElementType.Xpath);
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
	public void chooseDocumentNumberInPendingReceivablesScreen(ArrayList<String> DocumentNumber, String Type,
			String PayMode, String BankName, String CreationType, String SheetName) {
		flag = webDB.waitForElement(BankReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(BankReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
			for (int i = 1; i <= DocumentNumber.size(); i++) {
				commonFunc.searchAndSelectValueInFilters(
						BankReceiptLocators.PendingReceivablesDRDocumentNumberLOVButton, DocumentNumber.get(i - 1));
				webDB.switchToFrame(BankReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(BankReceiptLocators.BankReceiptPendingReceivablesSearchButton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankReceiptLocators.BankReceiptPendingReceivablesSearchButton,
							ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					String GetTextDocumentNumber = webDB.getTextFromElement(
							BankReceiptLocators.PendingReceivablesDocumentNumberField, ElementType.Xpath);
					if (GetTextDocumentNumber.equals(DocumentNumber.get(i - 1))) {
//						verifyPendingReceivablesSIDocumentWithDB(SheetName, DocumentNumber.get(i));
//						verifyPendingReceivablesSIDocumentThroughGetDocWithDB(SheetName, DocumentNumber.get(i-1),i);
						verifyPendingReceivablesSIDocumentThroughGetDocWithDB123(SheetName, DocumentNumber.get(i - 1),
								i);
						commonFunc.hardWait(1000);
						webDB.clickAnElement(BankReceiptLocators.BRPendingReceivablesDocumentSelectCheckBox(
								DocumentNumber.get(i - 1)), ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(1500);
						flag = webDB.waitForElement(BankReceiptLocators.PendingPayablesCreateButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "This Sales Invoice Document " + DocumentNumber.get(i - 1)
									+ " Is Selected Successfully");
							if (DocumentNumber.size() > 1) {
								commonFunc.checkRefreshCellsDisappear();
								webDB.clickAnElement(BankReceiptLocators.BankPendingPartyDocumentClearButton,
										ElementType.Xpath);
								flag = webDB.waitForElement(
										BankReceiptLocators.BankReceiptPendingReceivablesSearchButton,
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
			webDB.moveToElement(BankReceiptLocators.PendingPayablesCreateButton, ElementType.Xpath);
			webDB.javaScriptClickAnElement(BankReceiptLocators.PendingPayablesCreateButton, ElementType.Xpath);
			logs.logging("INFO", "Create Button Is Clicked Successfully");
			commonFunc.checkRefreshCellsDisappear();
			if (!CreationType.equals("Single")) {
				flag = webDB.waitForElement(BankReceiptLocators.PendingReceivablesMergeLabelInPending,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankReceiptLocators.PendingReceivablesMergeLabelInPending, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
				}
			}
			flag = webDB.waitForElement(BankReceiptLocators.PendingReceivablesTypeDropDown, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownByText(BankReceiptLocators.PendingReceivablesTypeDropDown, Type, ElementType.Xpath);
				logs.logging("INFO", "Type Successfully Choosen As " + Type);
				if (Type.equals("Bank")) {
					flag = webDB.waitForElement(BankReceiptLocators.PendingReceivablesPayModeDropDown,
							ElementType.Xpath);
					if (flag) {
						webDB.selectDropDownByText(BankReceiptLocators.PendingReceivablesPayModeDropDown, PayMode,
								ElementType.Xpath);
						logs.logging("INFO", "Pay Mode Successfully Choosen As " + PayMode);
					}
				}
				commonFunc.searchAndSelectValueInFilters(BankReceiptLocators.PendingReceivablesBankNameLOVBUtton,
						BankName);
				webDB.switchToFrame(BankReceiptLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(BankReceiptLocators.PendingReceivablesOkButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankReceiptLocators.PendingReceivablesOkButton, ElementType.Xpath);
					webDB.switchToDefaultContent();
					if (Type.equals("Bank")) {
						flag = webDB.waitForElement(BankReceiptLocators.BankReceiptScreenName, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"OK Button Is Clicked Successfully And Navigated To Bank Receipt Screen");
						}
					} else {
						flag = webDB.waitForElement(BankReceiptLocators.CashPaymentScreenName, ElementType.Xpath);
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
	 * This method Is Used To Insert The Reference Number In The Bank Payment Screen
	 * Header
	 * 
	 * @author Harikrishna - RMITS
	 * @param ReferenceNumber
	 */
	public void sendReferenceNumberInBR(String ReferenceNumber) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(BankReceiptLocators.BankReceiptReferenceInputField, ElementType.Xpath);
		if (flag) {
			webDB.clearTextField(BankReceiptLocators.BankReceiptReferenceInputField, ElementType.Xpath);
			webDB.sendTextToAnElement(BankReceiptLocators.BankReceiptReferenceInputField, ReferenceNumber,
					ElementType.Xpath);
			webDB.pressTabKey();
			flag = webDB.waitForElement(BankReceiptLocators.BankHeaderWiseSaveButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Given Ref Number " + ReferenceNumber + " Is Inserted Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Header Wise Save Button In The Bank Payment
	 * Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickBRHeaderWiseSaveButton() {
		flag = webDB.waitForElement(BankReceiptLocators.BankHeaderWiseSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankReceiptLocators.BankHeaderWiseSaveButton, ElementType.Xpath);
			flag = webDB.waitForElement(BankReceiptLocators.BankReceiptHeaderCreateJournalButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Bank Payment Header Wise Save Button Is Clicked Successfully");
				commonFunc.checkRefreshCellsDisappear();
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
		flag = webDB.waitForElement(BankReceiptLocators.BankLineViewButton(i), ElementType.CssSelector);
		if (flag) {
			webDB.clickAnElement(BankReceiptLocators.BankLineViewButton(i), ElementType.CssSelector);
			flag = webDB.waitForElementUntilGivenTime(BankReceiptLocators.BankReceiptViewFrame, ElementType.Xpath, 20);
			if (flag) {
				webDB.switchToFrame(BankReceiptLocators.BankReceiptViewFrame, ElementType.Xpath);
				logs.logging("INFO", "Switch To Receipt Details Frame Successfully");
				flag = webDB.waitForElement(BankReceiptLocators.BankReceiptViewAddButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Bank Receipt Line Wise View Button Is clicked Successfully");
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
		flag = webDB.waitForElement(BankReceiptLocators.BankReceiptViewAddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankReceiptLocators.BankReceiptViewAddButton, ElementType.Xpath);
			flag = webDB.waitForElement(BankReceiptLocators.BankReceiptAgainstRefFieldName, ElementType.Xpath);
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
		salesOrdFunc.verifyClickAndEnterValueInTableFields(BankReceiptLocators.BankReceiptBillDocField,
				BankReceiptLocators.BankReceiptBillNumberInputField, BillNumber);
		webDB.pressTabKey();
		salesOrdFunc.verifyClickAndEnterValueInTableFields(BankReceiptLocators.BankReceiptPayAmountField,
				BankReceiptLocators.BankReceiptPayAmountInputField, PayAmount);
		webDB.pressTabKey();
	}

	/**
	 * This Method Is Used to Click The View Details save Button In The Bank Receipt
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickViewDetailsSaveButton() {
		flag = webDB.waitForElement(BankReceiptLocators.BankReceiptViewSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankReceiptLocators.BankReceiptViewSaveButton, ElementType.Xpath);
			flag = webDB.waitForElement(BankReceiptLocators.BankReceiptViewDeleteButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Bank View Save Button Is Clicked Successfully");
				webDB.switchToDefaultContent();
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
		flag = webDB.waitForElement(BankReceiptLocators.BankReceiptViewScreenCloseButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankReceiptLocators.BankReceiptViewScreenCloseButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(BankReceiptLocators.BankReceiptHeaderCreateJournalButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Bank Receipt View details Is Closed Successfully");
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
	public void BankReceiptThroughAddButtonMPSI(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		bankpaymentfunc.createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocModeBR"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		bankpaymentfunc.verifyBankPaymentDetailsWithDB(BankName,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String PartyName = BankReceipts[i][3];
			String EntityName = BankReceipts[i][5];
			String CPCCodeDesc = BankReceipts[i][6];
			String BillNumber = BankReceipts[i][7];
			String PayAmount = BankReceipts[i][8];
			String DRCR = BankReceipts[i][9];
			String CURCY = BankReceipts[i][10];
			String EXRate = BankReceipts[i][11];
			String TransCURCY = BankReceipts[i][12];
			String BaseCURCY = BankReceipts[i][13];
			String AssesableValue = BankReceipts[i][16];
			if (!GLAccountDesc.equals("")) {
				clickBRLineAddButton();
				addBankReceiptLineDetails(GLAccountDesc, CPCCodeDesc);
				clickBRLineWiseSaveButton();
				clickBRLineWiseAddButton(i);
				clickPendingReceivablesInBR();
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
				}
				selectSalesInvoiceDocumentInPendingBankReceiptMPSI(PartyName,
						VariableHandler.getMultipleSIDocs().get(i - 1), SheetName, i);
				verifyBankReceiptLineDetailsWithDB(SheetName, i);
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBRHeader(BaseCURCY);
			}
		}
		checkARMControlBankNameRqrdFlag(BankName);
		checkARMControlReceivedFromRqrdFlag();
		clickCreateJournalButtonInBR();
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
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
	public void BankReceiptThroughAddButton(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		bankpaymentfunc.createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocModeBR"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		bankpaymentfunc.verifyBankPaymentDetailsWithDB(BankName,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String PartyName = BankReceipts[i][3];
			String EntityName = BankReceipts[i][5];
			String CPCCodeDesc = BankReceipts[i][6];
			String BillNumber = BankReceipts[i][7];
			String PayAmount = BankReceipts[i][8];
			String DRCR = BankReceipts[i][9];
			String CURCY = BankReceipts[i][10];
			String EXRate = BankReceipts[i][11];
			String TransCURCY = BankReceipts[i][12];
			String BaseCURCY = BankReceipts[i][13];
			String AssesableValue = BankReceipts[i][16];
			if (!GLAccountDesc.equals("")) {
				clickBRLineAddButton();
				addBankReceiptLineDetails(GLAccountDesc, CPCCodeDesc);
				clickBRLineWiseSaveButton();
				clickBRLineWiseAddButton(i);
				clickPendingReceivablesInBR();
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
				}
				selectSalesInvoiceDocumentInPendingBankReceipt(PartyName, VariableHandler.getMultipleSIDocs(),
						SheetName);
				commonFunc.checkRefreshCellsDisappear();
				verifyBankReceiptLineDetailsWithDB(SheetName, i);
				if (Currency.equals("INR")) {
					if (BankName.equals("JP MORGAN CHASE")) {
						for (int k = 1; k <= VariableHandler.getMultipleSIDocs().size(); k++) {
							excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalINR"), 1, 5 + k,
									dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(k - 1),
											commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
							excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalINR"), 2, 5 + k,
									dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(k - 1),
											commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
						}
					} else {
						for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
							excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("Journal"), 1, 5 + j,
									dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
											commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
							excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("Journal"), 2, 5 + j,
									dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
											commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
						}
					}
				} else {
					for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
						excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalUSD"), 1, 5 + j,
								dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
										commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
						excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalUSD"), 2, 5 + j,
								dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
										commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					}
				}
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBRHeader(BaseCURCY);
			}
		}
		checkARMControlBankNameRqrdFlag(BankName);
		checkARMControlReceivedFromRqrdFlag();
		clickCreateJournalButtonInBR();
		if (Currency.equals("INR")) {
			if (BankName.equals("JP MORGAN CHASE")) {
				verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalINR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
				for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalINR"), 1, 5 + j, "0");
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalINR"), 2, 5 + j, "0");
				}
			} else {
				verifyJournalAmountWithDB(webDB.getDataFromProperties("Journal"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
				for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("Journal"), 1, 5 + j, "0");
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("Journal"), 2, 5 + j, "0");
				}
			}
		} else {
			verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalUSD"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
			for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalUSD"), 1, 5 + j, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalUSD"), 2, 5 + j, "0");
			}
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
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
	public void BankReceiptThroughGetDocument(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		bankpaymentfunc.createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocModeBR"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		bankpaymentfunc.verifyBankPaymentDetailsWithDB(BankName,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		clickGetDocumentInBRHeader();
		clickPendingReceivablesInBR();
		chooseDocumentNumberAndClickSearchButtonInPendingReceivables(VariableHandler.getMultipleSIDocs(), SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String BaseCURCY = BankReceipts[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalUSD"), i - 1, 10,
							dbCommonFunctions.ExchangeRateDetails(Currency,
									commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
				}
				verifyBankReceiptLineDetailsWithDB(SheetName, i);
				if (Currency.equals("INR")) {
					if (BankName.equals("JP MORGAN CHASE")) {
						for (int k = 1; k <= VariableHandler.getMultipleSIDocs().size(); k++) {
							excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalINR"), 1, 5 + k,
									dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(k - 1),
											commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
							excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalINR"), 2, 5 + k,
									dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(k - 1),
											commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
						}
					} else {
						for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
							excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("Journal"), 1, 5 + j,
									dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
											commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
							excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("Journal"), 2, 5 + j,
									dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
											commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
						}
					}
				} else {
					for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
						excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalUSD"), 1, 5 + j,
								dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
										commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
						excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalUSD"), 2, 5 + j,
								dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
										commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					}
				}
			}
			if (!Currency.equals("INR") || BankName.equals("JP MORGAN CHASE")) {
				enterRealizedAmountInBRHeader(BaseCURCY);
			}
		}
		checkARMControlBankNameRqrdFlag(BankName);
		checkARMControlReceivedFromRqrdFlag();
		clickCreateJournalButtonInBR();
		if (Currency.equals("INR")) {
			if (BankName.equals("JP MORGAN CHASE")) {
				verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalINR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
				for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalINR"), 1, 5 + j, "0");
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalINR"), 2, 5 + j, "0");
				}
			} else {
				verifyJournalAmountWithDB(webDB.getDataFromProperties("Journal"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
				for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("Journal"), 1, 5 + j, "0");
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("Journal"), 2, 5 + j, "0");
				}
			}
		} else {
			verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalUSD"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
			for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalUSD"), 1, 5 + j, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalUSD"), 2, 5 + j, "0");
			}
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
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
	public void BankReceiptThroughPendingDocs(String CreationType, String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingDRDocsButton();
		clickPendingRecivablesButtonInPendDRDocs();
		chooseDocumentNumberInPendingPayablesScreen(VariableHandler.getMultipleSIDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentType"), commonFunc.getDataFromTestDataSheet("BankPayMode"),
				BankName, CreationType, SheetName);
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		sendReferenceNumberInBR(commonFunc.getDataFromTestDataSheet("Reference Number"));
		clickBRHeaderWiseSaveButton();
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String BaseCURCY = BankReceipts[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalUSD"), i - 1, 10,
							dbCommonFunctions.ExchangeRateDetails(Currency,
									commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
				}
				verifyBankReceiptLineDetailsWithDB(SheetName, i);
				if (Currency.equals("INR")) {
					if (BankName.equals("JP MORGAN CHASE")) {
						for (int k = 1; k <= VariableHandler.getMultipleSIDocs().size(); k++) {
							excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalINR"), 1, 5 + k,
									dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(k - 1),
											commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
							excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalINR"), 2, 5 + k,
									dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(k - 1),
											commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
						}
					} else {
						for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
							excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("Journal"), 1, 5 + j,
									dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
											commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
							excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("Journal"), 2, 5 + j,
									dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
											commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
						}
					}
				} else {
					for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
						excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalUSD"), 1, 5 + j,
								dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
										commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
						excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalUSD"), 2, 5 + j,
								dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
										commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					}
				}
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBRHeader(BaseCURCY);
			}
		}
		checkARMControlBankNameRqrdFlag(BankName);
		checkARMControlReceivedFromRqrdFlag();
		clickCreateJournalButtonInBR();
		if (Currency.equals("INR")) {
			if (BankName.equals("JP MORGAN CHASE")) {
				verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalINR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
				for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalINR"), 1, 5 + j, "0");
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalINR"), 2, 5 + j, "0");
				}
			} else {
				verifyJournalAmountWithDB(webDB.getDataFromProperties("Journal"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
				for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("Journal"), 1, 5 + j, "0");
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("Journal"), 2, 5 + j, "0");
				}
			}
		} else {
			verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalUSD"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
			for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalUSD"), 1, 5 + j, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalUSD"), 2, 5 + j, "0");
			}
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
		}
	}

	/**
	 * This Method Is used To Add The Bank Receipt Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BankReceiptThroughAddButtonMPMI(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		bankpaymentfunc.createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocModeBR"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		bankpaymentfunc.verifyBankPaymentDetailsWithDB(BankName,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String PartyName = BankReceipts[i][3];
			String EntityName = BankReceipts[i][5];
			String CPCCodeDesc = BankReceipts[i][6];
			String BillNumber = BankReceipts[i][7];
			String PayAmount = BankReceipts[i][8];
			String DRCR = BankReceipts[i][9];
			String CURCY = BankReceipts[i][10];
			String EXRate = BankReceipts[i][11];
			String TransCURCY = BankReceipts[i][12];
			String BaseCURCY = BankReceipts[i][13];
			String AssesableValue = BankReceipts[i][16];
			if (!GLAccountDesc.equals("")) {
				clickBRLineAddButton();
				addBankReceiptLineDetails(GLAccountDesc, CPCCodeDesc);
				clickBRLineWiseSaveButton();
				clickBRLineWiseAddButton(i);
				clickPendingReceivablesInBR();
				selectSalesInvoiceDocumentInPendingBankReceiptMPMI(PartyName, VariableHandler.getMultipleSIDocs(), i);
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
				}
				verifyBankReceiptLineDetailsWithDB(SheetName, i);
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBRHeader(BaseCURCY);
			}
		}
		checkARMControlBankNameRqrdFlag(BankName);
		checkARMControlReceivedFromRqrdFlag();
		clickCreateJournalButtonInBR();
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is used To Add The Bank Receipt Through Add Button MPMI With New
	 * Reference
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BankReceiptThroughAddButtonMPMIWithNewRef(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		bankpaymentfunc.createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocModeBR"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		bankpaymentfunc.verifyBankPaymentDetailsWithDB(BankName,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String PartyName = BankReceipts[i][3];
			String EntityName = BankReceipts[i][5];
			String CPCCodeDesc = BankReceipts[i][6];
			String BillNumber = BankReceipts[i][7];
			String PayAmount = BankReceipts[i][8];
			String DRCR = BankReceipts[i][9];
			String CURCY = BankReceipts[i][10];
			String EXRate = BankReceipts[i][11];
			String TransCURCY = BankReceipts[i][12];
			String BaseCURCY = BankReceipts[i][13];
			String AssesableValue = BankReceipts[i][16];
			if (!GLAccountDesc.equals("")) {
				clickBRLineAddButton();
				addBankReceiptLineDetails(GLAccountDesc, CPCCodeDesc);
				clickBRLineWiseSaveButton();
				clickBRLineWiseAddButton(i);
				clickPendingReceivablesInBR();
				selectSalesInvoiceDocumentInPendingBankReceiptMPMI(PartyName, VariableHandler.getMultipleSIDocs(), i);
				clickLineWiseViewButton(i);
				clickAddButtonInViewDetails();
				addBillNumberAndPayAmount(BillNumber, PayAmount);
				clickViewDetailsSaveButton();
				clickBankViewDetailsCloseButton();
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
				}
				verifyBankReceiptLineDetailsWithDB(SheetName, i);
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBRHeader(BaseCURCY);
			}
		}
		checkARMControlBankNameRqrdFlag(BankName);
		checkARMControlReceivedFromRqrdFlag();
		clickCreateJournalButtonInBR();
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
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
	public void BankReceiptThroughGetDocumentMPSI(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		bankpaymentfunc.createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocModeBR"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		bankpaymentfunc.verifyBankPaymentDetailsWithDB(BankName,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		clickGetDocumentInBRHeader();
		clickPendingReceivablesInBR();
		chooseDocumentNumberAndClickSearchButtonInPendingReceivables(VariableHandler.getMultipleSIDocs(), SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String BaseCURCY = BankReceipts[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
				}
				verifyBankReceiptLineDetailsWithDB(SheetName, i);
				if (Currency.equals("INR")) {
					if (BankName.equals("JP MORGAN CHASE")) {
						excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMultiINR"), 1,
								5 + i, dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(i - 1),
										commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
						for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
							excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMultiINR"),
									j + 1, 6,
									dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
											commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
						}
					} else {
						excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMulti"), 1, 5 + i,
								dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(i - 1),
										commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
						for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
							excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMulti"), j + 1,
									6, dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
											commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
						}
					}
				}
			}
			if (!Currency.equals("INR") || BankName.equals("JP MORGAN CHASE")) {
				enterRealizedAmountInBRHeader(BaseCURCY);
			}
		}
		checkARMControlBankNameRqrdFlag(BankName);
		checkARMControlReceivedFromRqrdFlag();
		clickCreateJournalButtonInBR();
		if (Currency.equals("INR")) {
			if (BankName.equals("JP MORGAN CHASE")) {
				verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalMultiINR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
				for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMultiINR"), 1, 5 + j,
							"0");
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMultiINR"), j + 1, 6,
							"0");
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMultiINR"), j + 1, 7,
							"0");
				}
			} else {
				verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalMulti"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
				for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMulti"), 1, 5 + j, "0");
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMulti"), j + 1, 6, "0");
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMulti"), j + 1, 7, "0");
				}
			}
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
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
	public void BankReceiptThroughGetDocumentMPSIUSD(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		bankpaymentfunc.createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocModeBR"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		bankpaymentfunc.verifyBankPaymentDetailsWithDB(BankName,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		clickGetDocumentInBRHeader();
		clickPendingReceivablesInBR();
		chooseDocumentNumberAndClickSearchButtonInPendingReceivables(VariableHandler.getMultipleSIDocs(), SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String BaseCURCY = BankReceipts[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMultiUSD"), 0, 10,
							dbCommonFunctions.ExchangeRateDetails(Currency,
									commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
				}
				verifyBankReceiptLineDetailsWithDB(SheetName, i);
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMultiUSD"), 1, 5 + i,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(i - 1),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMultiUSD"), j + 1, 6,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				}
			}
			if (!Currency.equals("INR") || BankName.equals("JP MORGAN CHASE")) {
				enterRealizedAmountInBRHeader(BaseCURCY);
			}
		}
		clickCreateJournalButtonInBR();
		verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalMultiUSD"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
		for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMultiUSD"), 1, 5 + j, "0");
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMultiUSD"), j + 1, 6, "0");
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMultiUSD"), j + 1, 6, "0");
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
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
	public void BankReceiptThroughPendingDocsMPSI(String CreationType, String SheetName, String Currency,
			String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingDRDocsButton();
		clickPendingRecivablesButtonInPendDRDocs();
		chooseDocumentNumberInPendingPayablesScreen(VariableHandler.getMultipleSIDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentType"), commonFunc.getDataFromTestDataSheet("BankPayMode"),
				BankName, CreationType, SheetName);
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		sendReferenceNumberInBR(commonFunc.getDataFromTestDataSheet("Reference Number"));
		clickBRHeaderWiseSaveButton();
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String BaseCURCY = BankReceipts[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
				}
				verifyBankReceiptLineDetailsWithDB(SheetName, i);
				if (Currency.equals("INR")) {
					if (BankName.equals("JP MORGAN CHASE")) {
						excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMultiINR"), 1,
								5 + i, dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(i - 1),
										commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
						for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
							excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMultiINR"),
									j + 1, 6,
									dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
											commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
						}
					} else {
						excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMulti"), 1, 5 + i,
								dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(i - 1),
										commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
						for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
							excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMulti"), j + 1,
									6, dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
											commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
						}
					}
				}
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBRHeader(BaseCURCY);
			}
		}
		checkARMControlBankNameRqrdFlag(BankName);
		checkARMControlReceivedFromRqrdFlag();
		clickCreateJournalButtonInBR();
		if (Currency.equals("INR")) {
			if (BankName.equals("JP MORGAN CHASE")) {
				verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalMultiINR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
				for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMultiINR"), 1, 5 + j,
							"0");
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMultiINR"), j + 1, 6,
							"0");
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMultiINR"), j + 1, 6,
							"0");
				}
			} else {
				verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalMulti"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
				for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMulti"), 1, 5 + j, "0");
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMulti"), j + 1, 6, "0");
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMulti"), j + 1, 6, "0");
				}
			}
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
		}
	}

	/**
	 * This Method Is Used To Add The Document Through pending Documents
	 * 
	 * @author Harikrishna - RMITS
	 * @param CreationType
	 * @param SheetName
	 * @param Currency
	 */
	public void BankReceiptThroughPendingDocsMPSIUSD(String CreationType, String SheetName, String Currency,
			String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingDRDocsButton();
		clickPendingRecivablesButtonInPendDRDocs();
		chooseDocumentNumberInPendingPayablesScreen(VariableHandler.getMultipleSIDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentType"), commonFunc.getDataFromTestDataSheet("BankPayMode"),
				BankName, CreationType, SheetName);
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		sendReferenceNumberInBR(commonFunc.getDataFromTestDataSheet("Reference Number"));
		clickBRHeaderWiseSaveButton();
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String BaseCURCY = BankReceipts[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMultiUSD"), 0, 10,
							dbCommonFunctions.ExchangeRateDetails(Currency,
									commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
				}
				verifyBankReceiptLineDetailsWithDB(SheetName, i);
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMultiUSD"), 1, 5 + i,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(i - 1),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMultiUSD"), j + 1, 6,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(j - 1),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				}
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBRHeader(BaseCURCY);
			}
		}
		clickCreateJournalButtonInBR();
		verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalMultiUSD"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
		for (int j = 1; j <= VariableHandler.getMultipleSIDocs().size(); j++) {
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMultiUSD"), 1, 5 + j, "0");
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMultiUSD"), j + 1, 6, "0");
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMultiUSD"), j + 1, 6, "0");
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
		}
	}

	/**
	 * This Method Is Used To Add The Documents Through Get Document With MPMI
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 * @param Currency
	 */
	public void BankReceiptThroughGetDocumentMPMI(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		bankpaymentfunc.createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocModeBR"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		bankpaymentfunc.verifyBankPaymentDetailsWithDB(BankName,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		clickGetDocumentInBRHeader();
		clickPendingReceivablesInBR();
		chooseDocumentNumberAndClickSearchButtonInPendingReceivablesMPMI(VariableHandler.getMultipleSIDocs(),
				SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String BaseCURCY = BankReceipts[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
				}
				verifyBankReceiptLineDetailsWithDB(SheetName, i);
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBRHeader(BaseCURCY);
			}
			if (Currency.equals("INR")) {
				if (BankName.equals("JP MORGAN CHASE")) {
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIINR"), 2, 6,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIINR"), 2, 7,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(1),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIINR"), 3, 6,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(2),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIINR"), 3, 7,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(3),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				} else {
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMI"), 2, 6,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMI"), 2, 7,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(1),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMI"), 3, 6,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(2),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMI"), 3, 7,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(3),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				}
			}
		}
		checkARMControlBankNameRqrdFlag(BankName);
		checkARMControlReceivedFromRqrdFlag();
		clickCreateJournalButtonInBR();
		if (Currency.equals("INR")) {
			if (BankName.equals("JP MORGAN CHASE")) {
				verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalMPMIINR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIINR"), 2, 6, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIINR"), 2, 7, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIINR"), 3, 6, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIINR"), 3, 7, "0");
			} else {
				verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalMPMI"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMI"), 2, 6, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMI"), 2, 7, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMI"), 3, 6, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMI"), 3, 7, "0");
			}
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
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
	public void BankReceiptThroughGetDocumentMPMIUSD(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		bankpaymentfunc.createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocModeBR"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		bankpaymentfunc.verifyBankPaymentDetailsWithDB(BankName,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		clickGetDocumentInBRHeader();
		clickPendingReceivablesInBR();
		chooseDocumentNumberAndClickSearchButtonInPendingReceivablesMPMI(VariableHandler.getMultipleSIDocs(),
				SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String BaseCURCY = BankReceipts[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIUSD"), 0, 10,
							dbCommonFunctions.ExchangeRateDetails(Currency,
									commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
				}
				verifyBankReceiptLineDetailsWithDB(SheetName, i);
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBRHeader(BaseCURCY);
			}
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIUSD"), 2, 6,
					dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
							commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIUSD"), 2, 7,
					dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(1),
							commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIUSD"), 3, 6,
					dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(2),
							commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIUSD"), 3, 7,
					dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(3),
							commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
		}
		clickCreateJournalButtonInBR();
		verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalMPMIUSD"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
		excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIUSD"), 2, 6, "0");
		excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIUSD"), 2, 7, "0");
		excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIUSD"), 3, 6, "0");
		excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIUSD"), 3, 7, "0");
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The Documents With Pending Docs With MPMI
	 * 
	 * @author Harikrishna -RMITS
	 * @param CreationType
	 * @param SheetName
	 * @param Currency
	 */
	public void BankReceiptThroughPendingDocsMPMIUSD(String CreationType, String SheetName, String Currency,
			String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingDRDocsButton();
		clickPendingRecivablesButtonInPendDRDocs();
		chooseDocumentNumberInPendingReceivablesScreen(VariableHandler.getMultipleSIDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentType"), commonFunc.getDataFromTestDataSheet("BankPayMode"),
				BankName, CreationType, SheetName);
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		sendReferenceNumberInBR(commonFunc.getDataFromTestDataSheet("Reference Number"));
		clickBRHeaderWiseSaveButton();
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String BaseCURCY = BankReceipts[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIUSD"), 0, 10,
							dbCommonFunctions.ExchangeRateDetails(Currency,
									commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
				}
				verifyBankReceiptLineDetailsWithDB(SheetName, i);
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBRHeader(BaseCURCY);
			}
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIUSD"), 2, 6,
					dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
							commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIUSD"), 2, 7,
					dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(1),
							commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIUSD"), 3, 6,
					dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(2),
							commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIUSD"), 3, 7,
					dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(3),
							commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
		}
		clickCreateJournalButtonInBR();
		verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalMPMIUSD"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
		excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIUSD"), 2, 6, "0");
		excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIUSD"), 2, 7, "0");
		excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIUSD"), 3, 6, "0");
		excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIUSD"), 3, 7, "0");
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
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
	public void BankReceiptThroughPendingDocsMPMI(String CreationType, String SheetName, String Currency,
			String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingDRDocsButton();
		clickPendingRecivablesButtonInPendDRDocs();
		chooseDocumentNumberInPendingReceivablesScreen(VariableHandler.getMultipleSIDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentType"), commonFunc.getDataFromTestDataSheet("BankPayMode"),
				BankName, CreationType, SheetName);
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		sendReferenceNumberInBR(commonFunc.getDataFromTestDataSheet("Reference Number"));
		clickBRHeaderWiseSaveButton();
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String BaseCURCY = BankReceipts[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
				}
				verifyBankReceiptLineDetailsWithDB(SheetName, i);
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBRHeader(BaseCURCY);
			}
			if (Currency.equals("INR")) {
				if (BankName.equals("JP MORGAN CHASE")) {
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIINR"), 2, 6,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIINR"), 2, 7,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(1),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIINR"), 3, 6,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(2),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIINR"), 3, 7,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(3),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				} else {
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMI"), 2, 6,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMI"), 2, 7,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(1),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMI"), 3, 6,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(2),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMI"), 3, 7,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(3),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				}
			}
		}
		checkARMControlBankNameRqrdFlag(BankName);
		checkARMControlReceivedFromRqrdFlag();
		clickCreateJournalButtonInBR();
		if (Currency.equals("INR")) {
			if (BankName.equals("JP MORGAN CHASE")) {
				verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalMPMIINR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIINR"), 2, 6, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIINR"), 2, 7, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIINR"), 3, 6, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMIINR"), 3, 7, "0");
			} else {
				verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalMPMI"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMI"), 2, 6, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMI"), 2, 7, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMI"), 3, 6, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalMPMI"), 3, 7, "0");
			}
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
		}
	}

	/**
	 * This Method Is Used To Add The Documents Through Get Documents With New
	 * reference
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param Currency
	 */
	public void BankReceiptThroughGetDocumentWithNewRef(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		bankpaymentfunc.createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocModeBR"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		bankpaymentfunc.verifyBankPaymentDetailsWithDB(BankName,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		clickGetDocumentInBRHeader();
		clickPendingReceivablesInBR();
		chooseDocumentNumberAndClickSearchButtonInPendingReceivablesMPMI(VariableHandler.getMultipleSIDocs(),
				SheetName);
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String PartyName = BankReceipts[i][3];
			String EntityName = BankReceipts[i][5];
			String CPCCodeDesc = BankReceipts[i][6];
			String BillNumber = BankReceipts[i][7];
			String PayAmount = BankReceipts[i][8];
			String DRCR = BankReceipts[i][9];
			String CURCY = BankReceipts[i][10];
			String EXRate = BankReceipts[i][11];
			String TransCURCY = BankReceipts[i][12];
			String BaseCURCY = BankReceipts[i][13];
			String AssesableValue = BankReceipts[i][16];
			if (!GLAccountDesc.equals("")) {
				clickLineWiseViewButton(i);
				clickAddButtonInViewDetails();
				addBillNumberAndPayAmount(BillNumber, PayAmount);
				clickViewDetailsSaveButton();
				clickBankViewDetailsCloseButton();
				for (int j = 1; j < BankReceipts.length; j++) {
					String GLAccountDesc1 = BankReceipts[j][1];
					if (!GLAccountDesc1.equals("")) {
						if (!Currency.equals("INR")) {
							excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11,
									dbCommonFunctions.ExchangeRateDetails(Currency,
											commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
						} else {
							excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
						}
						verifyBankReceiptLineDetailsWithDB(SheetName, i);
					}
				}
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBRHeader(BaseCURCY);
			}
			if (Currency.equals("INR")) {
				if (BankName.equals("JP MORGAN CHASE")) {
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRINR"), 2, 6,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRINR"), 2, 7,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(1),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRINR"), 3, 6,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(2),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRINR"), 3, 7,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(3),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				} else {
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNR"), 2, 6,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNR"), 2, 7,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(1),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNR"), 3, 6,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(2),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNR"), 3, 7,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(3),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				}
			}
		}
		checkARMControlBankNameRqrdFlag(BankName);
		checkARMControlReceivedFromRqrdFlag();
		clickCreateJournalButtonInBR();
		if (Currency.equals("INR")) {
			if (BankName.equals("JP MORGAN CHASE")) {
				verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalNRINR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRINR"), 2, 6, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRINR"), 2, 7, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRINR"), 3, 6, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRINR"), 3, 7, "0");
			} else {
				verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalNR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNR"), 2, 6, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNR"), 2, 7, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNR"), 3, 6, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNR"), 3, 7, "0");
			}
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
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
	public void BankReceiptThroughGetDocumentWithNewRefUSD(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		bankpaymentfunc.createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocModeBR"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		bankpaymentfunc.verifyBankPaymentDetailsWithDB(BankName,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		clickGetDocumentInBRHeader();
		clickPendingReceivablesInBR();
		chooseDocumentNumberAndClickSearchButtonInPendingReceivablesMPMI(VariableHandler.getMultipleSIDocs(),
				SheetName);
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String PartyName = BankReceipts[i][3];
			String EntityName = BankReceipts[i][5];
			String CPCCodeDesc = BankReceipts[i][6];
			String BillNumber = BankReceipts[i][7];
			String PayAmount = BankReceipts[i][8];
			String DRCR = BankReceipts[i][9];
			String CURCY = BankReceipts[i][10];
			String EXRate = BankReceipts[i][11];
			String TransCURCY = BankReceipts[i][12];
			String BaseCURCY = BankReceipts[i][13];
			String AssesableValue = BankReceipts[i][16];
			if (!GLAccountDesc.equals("")) {
				clickLineWiseViewButton(i);
				clickAddButtonInViewDetails();
				addBillNumberAndPayAmount(BillNumber, PayAmount);
				clickViewDetailsSaveButton();
				clickBankViewDetailsCloseButton();
				for (int j = 1; j < BankReceipts.length; j++) {
					String GLAccountDesc1 = BankReceipts[j][1];
					if (!GLAccountDesc1.equals("")) {
						if (!Currency.equals("INR")) {
							excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11,
									dbCommonFunctions.ExchangeRateDetails(Currency,
											commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
							excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRUSD"), 0, 11,
									dbCommonFunctions.ExchangeRateDetails(Currency,
											commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
						} else {
							excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
						}
						verifyBankReceiptLineDetailsWithDB(SheetName, i);
					}
				}
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBRHeader(BaseCURCY);
			}
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRUSD"), 2, 6,
					dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
							commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRUSD"), 2, 7,
					dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(1),
							commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRUSD"), 3, 6,
					dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(2),
							commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRUSD"), 3, 7,
					dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(3),
							commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
		}
		clickCreateJournalButtonInBR();
		verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalNRUSD"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
		excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRUSD"), 2, 6, "0");
		excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRUSD"), 2, 7, "0");
		excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRUSD"), 3, 6, "0");
		excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRUSD"), 3, 7, "0");
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
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
	public void BankReceiptThroughPendingDocsWithNewRef(String CreationType, String SheetName, String Currency,
			String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingDRDocsButton();
		clickPendingRecivablesButtonInPendDRDocs();
		chooseDocumentNumberInPendingReceivablesScreen(VariableHandler.getMultipleSIDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentType"), commonFunc.getDataFromTestDataSheet("BankPayMode"),
				BankName, CreationType, SheetName);
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		sendReferenceNumberInBR(commonFunc.getDataFromTestDataSheet("Reference Number"));
		clickBRHeaderWiseSaveButton();
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String PartyName = BankReceipts[i][3];
			String EntityName = BankReceipts[i][5];
			String CPCCodeDesc = BankReceipts[i][6];
			String BillNumber = BankReceipts[i][7];
			String PayAmount = BankReceipts[i][8];
			String DRCR = BankReceipts[i][9];
			String CURCY = BankReceipts[i][10];
			String EXRate = BankReceipts[i][11];
			String TransCURCY = BankReceipts[i][12];
			String BaseCURCY = BankReceipts[i][13];
			String AssesableValue = BankReceipts[i][16];
			if (!GLAccountDesc.equals("")) {
				clickLineWiseViewButton(i);
				clickAddButtonInViewDetails();
				addBillNumberAndPayAmount(BillNumber, PayAmount);
				clickViewDetailsSaveButton();
				clickBankViewDetailsCloseButton();
				for (int j = 1; j < BankReceipts.length; j++) {
					String GLAccountDesc1 = BankReceipts[j][1];
					if (!GLAccountDesc1.equals("")) {
						if (!Currency.equals("INR")) {
							excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11,
									dbCommonFunctions.ExchangeRateDetails(Currency,
											commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
						} else {
							excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
						}
						verifyBankReceiptLineDetailsWithDB(SheetName, i);
					}
				}
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBRHeader(BaseCURCY);
			}
			if (Currency.equals("INR")) {
				if (BankName.equals("JP MORGAN CHASE")) {
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRINR"), 2, 6,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRINR"), 2, 7,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(1),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRINR"), 3, 6,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(2),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRINR"), 3, 7,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(3),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				} else {
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNR"), 2, 6,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNR"), 2, 7,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(1),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNR"), 3, 6,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(2),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNR"), 3, 7,
							dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(3),
									commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
				}
			}
		}
		checkARMControlBankNameRqrdFlag(BankName);
		checkARMControlReceivedFromRqrdFlag();
		clickCreateJournalButtonInBR();
		if (Currency.equals("INR")) {
			if (BankName.equals("JP MORGAN CHASE")) {
				verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalNRINR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRINR"), 2, 6, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRINR"), 2, 7, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRINR"), 3, 6, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRINR"), 3, 7, "0");
			} else {
				verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalNR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNR"), 2, 6, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNR"), 2, 7, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNR"), 3, 6, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNR"), 3, 7, "0");
			}
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
		}
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
	public void BankReceiptThroughPendingDocsWithNewRefUSD(String CreationType, String SheetName, String Currency,
			String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingDRDocsButton();
		clickPendingRecivablesButtonInPendDRDocs();
		chooseDocumentNumberInPendingReceivablesScreen(VariableHandler.getMultipleSIDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentType"), commonFunc.getDataFromTestDataSheet("BankPayMode"),
				BankName, CreationType, SheetName);
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		sendReferenceNumberInBR(commonFunc.getDataFromTestDataSheet("Reference Number"));
		clickBRHeaderWiseSaveButton();
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String PartyName = BankReceipts[i][3];
			String EntityName = BankReceipts[i][5];
			String CPCCodeDesc = BankReceipts[i][6];
			String BillNumber = BankReceipts[i][7];
			String PayAmount = BankReceipts[i][8];
			String DRCR = BankReceipts[i][9];
			String CURCY = BankReceipts[i][10];
			String EXRate = BankReceipts[i][11];
			String TransCURCY = BankReceipts[i][12];
			String BaseCURCY = BankReceipts[i][13];
			String AssesableValue = BankReceipts[i][16];
			if (!GLAccountDesc.equals("")) {
				clickLineWiseViewButton(i);
				clickAddButtonInViewDetails();
				addBillNumberAndPayAmount(BillNumber, PayAmount);
				clickViewDetailsSaveButton();
				clickBankViewDetailsCloseButton();
				for (int j = 1; j < BankReceipts.length; j++) {
					String GLAccountDesc1 = BankReceipts[j][1];
					if (!GLAccountDesc1.equals("")) {
						if (!Currency.equals("INR")) {
							excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11,
									dbCommonFunctions.ExchangeRateDetails(Currency,
											commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
							excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRUSD"), 0, 11,
									dbCommonFunctions.ExchangeRateDetails(Currency,
											commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
						} else {
							excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
						}
						verifyBankReceiptLineDetailsWithDB(SheetName, i);
					}
				}
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBRHeader(BaseCURCY);
			}
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRUSD"), 2, 6,
					dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
							commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRUSD"), 2, 7,
					dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(1),
							commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRUSD"), 3, 6,
					dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(2),
							commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRUSD"), 3, 7,
					dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(3),
							commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
		}
		clickCreateJournalButtonInBR();
		verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalNRUSD"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
		excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRUSD"), 2, 6, "0");
		excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRUSD"), 2, 7, "0");
		excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRUSD"), 3, 6, "0");
		excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalNRUSD"), 3, 7, "0");
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
		}
	}

	/**
	 * This Method Is Used to Create BR SPSI Through Add Button
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 */
	public void BRSinglePartySingleInvoiceThroughAddButton(String SheetName) {
		salesInvoiceFunc.verifyDirectSalesInvoiceBR(webDB.getDataFromProperties("BRSPSI"),
				commonFunc.getDataFromTestDataSheet("CustomerName"));
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR SPSI Through Get Document
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 */
	public void BRSinglePartySingleInvoiceThroughGetDocument(String SheetName) {
		salesInvoiceFunc.verifyDirectSalesInvoiceBR(webDB.getDataFromProperties("BRSPSI"),
				commonFunc.getDataFromTestDataSheet("CustomerName"));
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR SPSI Through pending Docs
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 */
	public void BRSinglePartySingleInvoiceThroughPendingDocs(String SheetName) {
		salesInvoiceFunc.verifyDirectSalesInvoiceBR(webDB.getDataFromProperties("BRSPSI"),
				commonFunc.getDataFromTestDataSheet("CustomerName"));
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"), commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR SPMI Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRSinglePartyMultiInvoiceThroughAddButton(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR SPMI Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRSinglePartyMultiInvoiceThroughGetDocument(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR SPMI Through Pending Docs
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRSinglePartyMultiInvoiceThroughPendingDocs(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"), commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR MPSI Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRMultiPartySingleInvoiceThroughAddButton(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBR(SISheetName, Customer);
				excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 25 + i,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(i - 1),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughAddButtonMPSI(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
//		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR MPSI Through Get Document
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 */
	public void BRMultiPartySingleInvoiceThroughGetDocument(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBR(SISheetName, Customer);
				excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 25 + i,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(i - 1),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughGetDocumentMPSI(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR MPSI Through Pending Docs
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRMultiPartySingleInvoiceThroughPendingDocs(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBR(SISheetName, Customer);
				excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 25 + i,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(i - 1),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughPendingDocsMPSI(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"), commonFunc.getDataFromTestDataSheet("BankName"));
//		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR With MPMI Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRMultiPartyMultiInvoiceThroughAddButton(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughAddButtonMPMI(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
//		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is USed to Create BR MPMI Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRMultiPartyMultiInvoiceThroughGetDocument(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughGetDocumentMPMI(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is USed To Create BR MPMI Through Penidng Docs
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRMultiPartyMultiInvoiceThroughPendingDocs(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughPendingDocsMPMI(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"), commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR MPMI Through Add Button With New Reference
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRMultiPartyMultiInvoiceThroughAddButtonWithNewRef(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughAddButtonMPMIWithNewRef(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR MPMI Through Get Document With New Reference
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRMultiPartyMultiInvoiceThroughGetDocumentWithNewref(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughGetDocumentWithNewRef(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR MPMI Through Pending Docs With New Reference
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRMultiPartyMultiInvoiceThroughPendingDocsWithNewRef(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughPendingDocsWithNewRef(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"), commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is USed To Verify Pending Receivables With DB
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param VoucherNumber
	 */
	public void verifyPendingReceivablesSIDocumentWithDB(String SheetName, String VoucherNumber) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String PartyID = BankReceipts[i][2];
			String PartyName = BankReceipts[i][3];
			String Entity = BankReceipts[i][5];
			String CPCDesc = BankReceipts[i][6];
			String Curcy = BankReceipts[i][10];
			String EXRate = BankReceipts[i][11];
			String Location = BankReceipts[i][23];
			String Unit = BankReceipts[i][24];
			String DrCr = BankReceipts[i][25];
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
	 * This Method Is Used To Verify Pending Receivables Through get Document With
	 * DB
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param VoucherNumber
	 * @param i1
	 */
	public void verifyPendingReceivablesSIDocumentThroughGetDocWithDB(String SheetName, String VoucherNumber, int i1) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String PartyID = BankReceipts[i][2];
			String PartyName = BankReceipts[i][3];
			String Entity = BankReceipts[i][5];
			String CPCDesc = BankReceipts[i][6];
			String Curcy = BankReceipts[i][10];
			String EXRate = BankReceipts[i][11];
			String Location = BankReceipts[i][23];
			String Unit = BankReceipts[i][24];
			String DrCr = BankReceipts[i][25];
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
				if (Curcy.equals("INR")) {
					salesOrdFunc
							.verifyAssertionBothValues(
									dbCommonFunctions.BankReceiptPendingReceivablesDetails(VoucherNumber,
											commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesExRate")),
									EXRate);
					logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
							+ " EX.Rate Is getting Matched With DB");
				} else {
					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankReceiptPendingReceivablesDetails(VoucherNumber,
									commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesExRate")),
							dbCommonFunctions.ExchangeRateDetails(Curcy,
									commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
					logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
							+ " EX.Rate Is getting Matched With DB");
				}
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
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
//		int a= i1*1;
		for (int i = (i1 + 1) / 2; i <= (i1 + 1) / 2; i++) {
			System.out.println(i);
			String PartyID = BankReceipts[i][2];
			String PartyName = BankReceipts[i][3];
			String Entity = BankReceipts[i][5];
			String CPCDesc = BankReceipts[i][6];
			String Curcy = BankReceipts[i][10];
			String EXRate = BankReceipts[i][11];
			String Location = BankReceipts[i][23];
			String Unit = BankReceipts[i][24];
			String DrCr = BankReceipts[i][25];
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

				if (Curcy.equals("INR")) {
					salesOrdFunc
							.verifyAssertionBothValues(
									dbCommonFunctions.BankReceiptPendingReceivablesDetails(VoucherNumber,
											commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesExRate")),
									EXRate);
					logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
							+ " EX.Rate Is getting Matched With DB");
				} else {
					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankReceiptPendingReceivablesDetails(VoucherNumber,
									commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesExRate")),
							dbCommonFunctions.ExchangeRateDetails(Curcy,
									commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
					logs.logging("INFO", "In Pending Receivables For This Document Number " + VoucherNumber
							+ " EX.Rate Is getting Matched With DB");
				}

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
	 * This Method IS USed To Verify Pending Receivables SI Document MPSI With DB
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param VoucherNumber
	 * @param i1
	 */
	public void verifyPendingReceivablesSIDocumentMPSIWithDB(String SheetName, String VoucherNumber, int i1) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String PartyID = BankReceipts[i][2];
			String PartyName = BankReceipts[i][3];
			String Entity = BankReceipts[i][5];
			String CPCDesc = BankReceipts[i][6];
			String Curcy = BankReceipts[i][10];
			String EXRate = BankReceipts[i][11];
			String Location = BankReceipts[i][23];
			String Unit = BankReceipts[i][24];
			String DrCr = BankReceipts[i][25];
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
	public void verifyBankReceiptLineDetailsWithDB(String SheetName, int i1) {

		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String PartyID = BankReceipts[i][2];
			String PartyName = BankReceipts[i][3];
			String Entity = BankReceipts[i][5];
			String CPCDesc = BankReceipts[i][6];
			String LineDrCr = BankReceipts[i][9];
			String Curcy = BankReceipts[i][10];
			String EXRate = BankReceipts[i][11];
			String AmtInTC = BankReceipts[i][12];
			String AmtInBC = BankReceipts[i][13];
			String HSNCode = BankReceipts[i][14];
			String AssesableValue = BankReceipts[i][16];
			String CGST = BankReceipts[i][17];
			String SGST = BankReceipts[i][18];
			String IGST = BankReceipts[i][19];
			String UTGST = BankReceipts[i][20];
			String CESSPCT = BankReceipts[i][21];
			String CESSAMT = BankReceipts[i][22];
			String Location = BankReceipts[i][23];
			String Unit = BankReceipts[i][24];
			String PendingDrCr = BankReceipts[i][25];
			String TotAmt = BankReceipts[i][28];
			if (!PartyID.equals("")) {
				String SeqNo = String.valueOf(i);
				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBGLAccountDescColumnName")),
						GLAccountDesc);
				logs.logging("INFO",
						" Line Wise GL Account Desc Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBSupplierDescColumnName")),
						PartyName);
				logs.logging("INFO",
						" Line Wise Party Name Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBEntityColumnName")),
						Entity);
				logs.logging("INFO",
						" Line Wise Entity Name Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBCPCcodeDescColumnName")),
						CPCDesc);
				logs.logging("INFO",
						" Line Wise CPC Desc Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBAmountTCColumnName")),
						AmtInTC);
				logs.logging("INFO",
						" Line Wise Amount In Transaction Currency Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBDCColumnName")),
						LineDrCr);
				logs.logging("INFO",
						" Line Wise Dr / Cr Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBBCColumnName")),
						AmtInBC);
				logs.logging("INFO",
						" Line Wise Amount In Base Currency Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBAssesableColumnName")),
						AssesableValue);
				logs.logging("INFO",
						" Line Wise Amount In Tax Assesable Value Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBExhangeRateColumnName")),
						EXRate);
				logs.logging("INFO",
						" Line Wise Exchange Rate Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBLineCurrencyColumnName")),
						Curcy);
				logs.logging("INFO",
						" Line Wise Currency Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());
				if (SheetName.equals("BankReceiptRevExpGST")) {
					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
									SeqNo, commonFunc.getDataFromTestDataSheet("BRDBLineHSNCode")),
							HSNCode);
					logs.logging("INFO",
							" Line Wise HSN Code Is Matched With DataBase Values Against This Bank Receipt Document "
									+ VariableHandler.getBankPaymentOrReceiptDocNumber());

					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
									SeqNo, commonFunc.getDataFromTestDataSheet("BRDBLineIGSTAmt")),
							IGST);
					logs.logging("INFO",
							" Line Wise IGST Amount Is Matched With DataBase Values Against This Bank Receipt Document "
									+ VariableHandler.getBankPaymentOrReceiptDocNumber());

					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
									SeqNo, commonFunc.getDataFromTestDataSheet("BRDBLineCGSTAmt")),
							CGST);
					logs.logging("INFO",
							" Line Wise CGST Amount Is Matched With DataBase Values Against This Bank Receipt Document "
									+ VariableHandler.getBankPaymentOrReceiptDocNumber());

					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
									SeqNo, commonFunc.getDataFromTestDataSheet("BRDBLineSGSTAmt")),
							SGST);
					logs.logging("INFO",
							" Line Wise SGST Amount Is Matched With DataBase Values Against This Bank Receipt Document "
									+ VariableHandler.getBankPaymentOrReceiptDocNumber());

					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
									SeqNo, commonFunc.getDataFromTestDataSheet("BRDBLineUTGSTAmt")),
							UTGST);
					logs.logging("INFO",
							" Line Wise UTGST Amount Is Matched With DataBase Values Against This Bank Receipt Document "
									+ VariableHandler.getBankPaymentOrReceiptDocNumber());

					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
									SeqNo, commonFunc.getDataFromTestDataSheet("BRDBLineCESSPercent")),
							CESSPCT);
					logs.logging("INFO",
							" Line Wise CESS Percentage Is Matched With DataBase Values Against This Bank Receipt Document "
									+ VariableHandler.getBankPaymentOrReceiptDocNumber());

					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
									SeqNo, commonFunc.getDataFromTestDataSheet("BRDBLineCESSAmt")),
							CESSAMT);
					logs.logging("INFO",
							" Line Wise CESS Amount Is Matched With DataBase Values Against This Bank Receipt Document "
									+ VariableHandler.getBankPaymentOrReceiptDocNumber());
				}
			}
		}
	}

	/**
	 * This Method Is Used To Verify Overall Header Amount With DB
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void verifyOverallHeaderAmountWithDB(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String ChargeAmt = BankReceipts[i][8];
			String AmtInTC = BankReceipts[i][12];
			String TotalAmount = BankReceipts[i][28];
			String ChargesFlag = BankReceipts[i][29];
			if (GLAccountDesc.equals("")) {
				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentHeaderDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
								commonFunc.getDataFromTestDataSheet("BPDBHeaderReceiptAmtTCColumnName")),
						AmtInTC);
				logs.logging("INFO",
						" Header Wise Receipt Amt (TC) Value Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentHeaderDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
								commonFunc.getDataFromTestDataSheet("BPDBHeaderRLZDAmtColumnName")),
						TotalAmount);
				logs.logging("INFO",
						" Header Wise RLZD Amt (Bank Curcy) Value Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentHeaderDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
								commonFunc.getDataFromTestDataSheet("BPDBHeaderNetAmtBCColumnName")),
						TotalAmount);
				logs.logging("INFO",
						" Header Wise Net Amt (Bank Curcy) Value Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());
				if (ChargesFlag.equals("Y")) {
					salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankPaymentHeaderDetails(
							VariableHandler.getBankPaymentOrReceiptDocNumber(),
							commonFunc.getDataFromTestDataSheet("BPDBHeaderChargeBCAmtColumnName")), ChargeAmt);
					logs.logging("INFO",
							" Header Wise Charges Amt (Bank Curcy) Value Is Matched With DataBase Values Against This Bank Receipt Document "
									+ VariableHandler.getBankPaymentOrReceiptDocNumber());
				}
			}
		}
	}

	/**
	 * This Method Is Used To Clear Data In The Excel
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void clearDataInExcel(String SheetName) {
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			if (!GLAccountDesc.equals("")) {
				for (int j = 1; j <= 2; j++) {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 25 + j, "0");
				}
			}
		}
	}

	/**
	 * This Method IS Used To Enter Realized Amount In The Header
	 * 
	 * @author Harikrishna - RMITS
	 * @param RealizedAmt
	 */
	public void enterRealizedAmountInBRHeader(String RealizedAmt) {
		flag = webDB.waitForElement(BankReceiptLocators.BankReceiptRealizedAmtInputField, ElementType.Xpath);
		if (flag) {
			webDB.clearTextField(BankReceiptLocators.BankReceiptRealizedAmtInputField, ElementType.Xpath);
			webDB.sendTextToAnElement(BankReceiptLocators.BankReceiptRealizedAmtInputField, RealizedAmt,
					ElementType.Xpath);
			flag = webDB.waitForElement(BankReceiptLocators.BankReceiptHeaderCreateJournalButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Realized Amount " + RealizedAmt + " Is Entered Successfully");
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
	public void checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(String Currency) {
		String BRVAutoClearFlag = dbCommonFunctions.GLMControl(commonFunc.getDataFromTestDataSheet("DBBU"),
				commonFunc.getDataFromTestDataSheet("GLMDBBankReceiptAutoClearFlagColumnName"));
		logs.logging("INFO", "Bank Receipt (RTGS / NEFT / TT ) Auto Clear Flag Is " + BRVAutoClearFlag);
		if (BRVAutoClearFlag.equals("Y") || Currency.equals("USD")) {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("BPClearedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
		} else {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("BRReceivedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
		}
	}

	/**
	 * This Method Is Used To Create BR With Single Party Single Invoice
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRSinglePartySingleInvoiceThroughAddButtonUSD(String SheetName) {
		salesInvoiceFunc.verifyDirectSalesInvoiceExportBR(webDB.getDataFromProperties("BRSPSIE"),
				commonFunc.getDataFromTestDataSheet("Export_Customer_Name"));
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR With Single Party Single Invoice
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRSinglePartySingleInvoiceThroughGetDocumentUSD(String SheetName) {
		salesInvoiceFunc.verifyDirectSalesInvoiceExportBR(webDB.getDataFromProperties("BRSPSIE"),
				commonFunc.getDataFromTestDataSheet("Export_Customer_Name"));
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR With Single Party Single Invoice
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRSinglePartySingleInvoiceThroughPendingDocsUSD(String SheetName) {
		salesInvoiceFunc.verifyDirectSalesInvoiceExportBR(webDB.getDataFromProperties("BRSPSIE"),
				commonFunc.getDataFromTestDataSheet("Export_Customer_Name"));
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR With Single Party Multi Invoice
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRSinglePartyMultiInvoiceThroughAddButtonUSD(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceExportBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR With Single Party Multi Invoice
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRSinglePartyMultiInvoiceThroughGetDocumentUSD(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceExportBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR With Single Party Multi Invoice
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRSinglePartyMultiInvoiceThroughPendingDocsUSD(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceExportBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR With Multi Party Single Invoice
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRMultiPartySingleInvoiceThroughAddButtonUSD(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceExportBR(SISheetName, Customer);
				excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 25 + i,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(i - 1),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughAddButtonMPSI(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR With Multi Party Single Invoice
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRMultiPartySingleInvoiceThroughGetDocumentUSD(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceExportBR(SISheetName, Customer);
				excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 25 + i,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(i - 1),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughGetDocumentMPSIUSD(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR With Multi Party Single Invoice
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRMultiPartySingleInvoiceThroughPendingDocsUSD(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceExportBR(SISheetName, Customer);
				excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 25 + i,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(i - 1),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughPendingDocsMPSIUSD(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR With Multi Party Multi Invoice
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRMultiPartyMultiInvoiceThroughAddButtonUSD(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceExportBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughAddButtonMPMI(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR With Multi Party Multi Invoice
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRMultiPartyMultiInvoiceThroughGetDocumentUSD(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceExportBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughGetDocumentMPMIUSD(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR With Multi Party Multi Invoice
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRMultiPartyMultiInvoiceThroughPendingDocsUSD(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceExportBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughPendingDocsMPMIUSD(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR With Multi Party Multi Invoice
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRMultiPartyMultiInvoiceThroughAddButtonWithNewRefUSD(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughAddButtonMPMIWithNewRef(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR With Multi Party Multi Invoice
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRMultiPartyMultiInvoiceThroughGetDocumentWithNewrefUSD(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughGetDocumentWithNewRefUSD(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR With Multi Party Multi Invoice
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRMultiPartyMultiInvoiceThroughPendingDocsWithNewRefUSD(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughPendingDocsWithNewRefUSD(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Click PDC Flag In BR
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickPDCFlagInBR() {
		commonFunc.checkRefreshCellsDisappear();
		webDB.pressTabKey();
		flag = webDB.waitForElement(BankReceiptLocators.BakReciptPDCLabelName, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankReceiptLocators.BakReciptPDCLabelName, ElementType.Xpath);
			if (webDB.isElementSelected(BankReceiptLocators.BankReceiptPDCFlag, ElementType.Xpath)) {
				logs.logging("INFO", " Post Dated Cheque Flag Is Selected ");
			} else {
				logs.logging("INFO", " Post Dated Cheque Flag Is Not Selected ");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Change The Ref Date In BR
	 * 
	 * @author Harikrishna - RMITS
	 * @param Date
	 */
	public void changeReferenceDateInBR(String Date) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(BankReceiptLocators.BakReciptPDCLabelName, ElementType.Xpath);
		if (flag) {
			if (webDB.isElementSelected(BankReceiptLocators.BankReceiptPDCFlag, ElementType.Xpath)) {
				logs.logging("INFO", " Post Dated Cheque Flag Is Selected ");
				flag = webDB.waitForElement(BankReceiptLocators.BankReciptRefDateInputField, ElementType.Xpath);
				if (flag) {
					webDB.clearTextField(BankReceiptLocators.BankReciptRefDateInputField, ElementType.Xpath);
					webDB.sendTextToAnElement(BankReceiptLocators.BankReciptRefDateInputField, Date, ElementType.Xpath);
					flag = webDB.waitForElement(BankReceiptLocators.BankHeaderWiseSaveButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Reference Date Is Inserted Successfully");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Create Bank Receipt With Post Dated Cheque
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param Currency
	 */
	public void bankReceiptWithPostDatedCheque(String SheetName, String Currency) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		createBankReceiptEntry(commonFunc.getDataFromTestDataSheet("BankDocModeBR"),
				commonFunc.getDataFromTestDataSheet("BankName"), Today,
				commonFunc.getDataFromTestDataSheet("BankPayModeCheque"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		bankpaymentfunc.verifyBankPaymentDetailsWithDB(commonFunc.getDataFromTestDataSheet("BankName"),
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		clickPDCFlagInBR();
		changeReferenceDateInBR(tomorrowDate);
		clickBRHeaderWiseSaveButton();
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String PartyName = BankReceipts[i][3];
			String EntityName = BankReceipts[i][5];
			String CPCCodeDesc = BankReceipts[i][6];
			String BillNumber = BankReceipts[i][7];
			String PayAmount = BankReceipts[i][8];
			String DRCR = BankReceipts[i][9];
			String CURCY = BankReceipts[i][10];
			String EXRate = BankReceipts[i][11];
			String TransCURCY = BankReceipts[i][12];
			String BaseCURCY = BankReceipts[i][13];
			String AssesableValue = BankReceipts[i][16];
			if (!GLAccountDesc.equals("")) {
				clickBRLineAddButton();
				addBankReceiptLineDetails(GLAccountDesc, CPCCodeDesc);
				clickBRLineWiseSaveButton();
				clickLineWiseViewButton(i);
				clickAddButtonInViewDetails();
				addBillNumberAndPayAmount(BillNumber, PayAmount);
				clickViewDetailsSaveButton();
				clickBankViewDetailsCloseButton();
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
				}
				verifyBankReceiptLineDetailsWithDB(SheetName, i);
			}
			if (!Currency.equals("INR")) {
				enterRealizedAmountInBRHeader(BaseCURCY);
			}
		}
		checkARMControlBankNameRqrdFlag(commonFunc.getDataFromTestDataSheet("SBIBankName"));
		checkARMControlReceivedFromRqrdFlag();
		clickCreateJournalButtonInBR();
		verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalPDC"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
//			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
		}
	}

	/**
	 * This Method Is Used to Create A Bank Payment Entry In The Payments / Receipts
	 * Screen
	 * 
	 * @author Harikrishna - RMITS
	 * @param DocumentMode
	 * @param BankName
	 * @param VoucherDate
	 * @param PayMode
	 * @param ReferenceNumber
	 * @param ReferenceDate
	 * @param Prefix
	 * @param TransCurcy
	 */
	public void createBankReceiptEntry(String DocumentMode, String BankName, String VoucherDate, String PayMode,
			String ReferenceNumber, String ReferenceDate, String Prefix, String TransCurcy) {
		flag = webDB.waitForElement(BankPaymentLocators.BankModeDropDown, ElementType.Xpath);
		if (flag) {
			webDB.selectDropDownByText(BankPaymentLocators.BankModeDropDown, DocumentMode, ElementType.Xpath);
			logs.logging("INFO", "Document Mode Changed Successfully to " + DocumentMode);
			commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.BankLovbutton, BankName);
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(BankPaymentLocators.BankVoucherDateinputField, ElementType.Xpath);
			if (flag) {
				webDB.clearTextField(BankPaymentLocators.BankVoucherDateinputField, ElementType.Xpath);
				webDB.sendTextToAnElement(BankPaymentLocators.BankVoucherDateinputField, VoucherDate,
						ElementType.Xpath);
				flag = webDB.waitForElement(BankPaymentLocators.BankPayModeDropDown, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Bank Voucher Date Is Inserted Successfully");
					webDB.selectDropDownByText(BankPaymentLocators.BankPayModeDropDown, PayMode, ElementType.Xpath);
					logs.logging("INFO", "Payment Mode Changed Sucessfully To " + PayMode);
					flag = webDB.waitForElement(BankPaymentLocators.BankRefDateInputField, ElementType.Xpath);
					if (flag) {
						webDB.clearTextField(BankPaymentLocators.BankRefDateInputField, ElementType.Xpath);
						webDB.sendTextToAnElement(BankPaymentLocators.BankRefDateInputField, ReferenceDate,
								ElementType.Xpath);
						logs.logging("INFO", "Reference Date Is Inserted Successfully");
						commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.BankPrefixLovButton, Prefix);
						commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.BankTransCurrencyLovButton,
								TransCurcy);
						flag = webDB.waitForElement(BankPaymentLocators.BankRefInputField, ElementType.Xpath);
						if (flag) {
							webDB.clearTextField(BankPaymentLocators.BankRefInputField, ElementType.Xpath);
							webDB.sendTextToAnElement(BankPaymentLocators.BankRefInputField, ReferenceNumber,
									ElementType.Xpath);
						}
						flag = webDB.waitForElement(BankPaymentLocators.BankSaveButton, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(BankPaymentLocators.BankSaveButton, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							flag = webDB.waitForElement(BankPaymentLocators.BankPostButton, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO",
										"Save Button Is Clicked And Bank Payment Document is Created Successfully");
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Create BR SPSI Through pending Docs
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 */
	public void BankReceiptSIWithCharges(String SheetName) {
		salesInvoiceFunc.verifyDirectSalesInvoiceBR(webDB.getDataFromProperties("BRSPSI"),
				commonFunc.getDataFromTestDataSheet("CustomerName"));
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
		BankReceiptThroughAddButtonWithCharges(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create BR SPSI Through pending Docs
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 */
	public void BankReceiptSIWithChargesUSD(String SheetName) {
		salesInvoiceFunc.verifyDirectSalesInvoiceExportBR(webDB.getDataFromProperties("BRSPSIE"),
				commonFunc.getDataFromTestDataSheet("Export_Customer_Name"));
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
		BankReceiptThroughAddButtonWithCharges(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is used To Add The Bank Receipt Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return
	 */
	public void BankReceiptThroughAddButtonWithCharges(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		bankpaymentfunc.createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocModeBR"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		bankpaymentfunc.verifyBankPaymentDetailsWithDB(BankName,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		clickGetDocumentInBRHeader();
		clickPendingReceivablesInBR();
		chooseDocumentNumberAndClickSearchButtonInPendingReceivables(VariableHandler.getMultipleSIDocs(), SheetName);
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String PartyName = BankReceipts[i][3];
			String EntityName = BankReceipts[i][5];
			String CPCCodeDesc = BankReceipts[i][6];
			String BillNumber = BankReceipts[i][7];
			String PayAmount = BankReceipts[i][8];
			String DRCR = BankReceipts[i][9];
			String CURCY = BankReceipts[i][10];
			String EXRate = BankReceipts[i][11];
			String TransCURCY = BankReceipts[i][12];
			String BaseCURCY = BankReceipts[i][13];
			String AssesableValue = BankReceipts[i][16];
			String ChargesFlag = BankReceipts[i][29];
			if (!GLAccountDesc.equals("")) {
				if (ChargesFlag.equals("Y")) {
					clickBRLineAddButton();
					addBankReceiptLineDetails(GLAccountDesc, CPCCodeDesc);
					enterValueInAmtInTCField(PayAmount);
					chooseLineWiseDRCRInBR(commonFunc.getDataFromTestDataSheet("DrValue"));
					clickChargesFlagInBR();
					clickBRLineWiseSaveButton();
					if (!Currency.equals("INR")) {
						excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
								Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
					} else {
						excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
					}
				} else {
					if (!Currency.equals("INR")) {
						excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
								Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
						excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalBankUSD"), 0, 10,
								dbCommonFunctions.ExchangeRateDetails(Currency,
										commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
					} else {
						excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
					}
				}
				verifyBankReceiptLineDetailsWithDB(SheetName, i);
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBRHeader(BaseCURCY);
			}
			if (Currency.equals("INR")) {
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalBank"), 2, 6,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			} else {
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalBankUSD"), 2, 6,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			}

		}
		checkARMControlBankNameRqrdFlag(commonFunc.getDataFromTestDataSheet("SBIBankName"));
		checkARMControlReceivedFromRqrdFlag();
		clickCreateJournalButtonInBR();
		if (Currency.equals("INR")) {
			verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalBank"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalBank"), 2, 6, "0");
		} else {
			verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalBankUSD"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalBankUSD"), 2, 6, "0");
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Select The Line Wise Charges Flag In Bank Receipt
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickChargesFlagInBR() {
		flag = webDB.waitForElement(BankReceiptLocators.BankReceiptChargesFlag, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(BankReceiptLocators.BankReceiptChargesInputFlag, ElementType.Xpath);
			webDB.pressTabKey();
			if (webDB.isElementSelected(BankReceiptLocators.BankReceiptChargesInputFlag, ElementType.Xpath)) {
				logs.logging("INFO", "Line Wise Charges Flag Is Selected Successfully");
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
		salesOrdFunc.verifyClickAndEnterValueInTableFields(BankReceiptLocators.BankReceiptLineATCField,
				BankReceiptLocators.BankReceiptAmountInTcInputField, ChargeAmount);
		webDB.pressTabKey();
	}

	/**
	 * This Method Is Used To Choose Line Wise DR And CR In BR
	 * 
	 * @author Harikrishna - RMITS
	 * @param DRCR
	 */
	public void chooseLineWiseDRCRInBR(String DRCR) {
		flag = webDB.waitForElement(BankReceiptLocators.BanReceiptCRDRField, ElementType.Xpath);
		if (flag) {
//			webDB.clickAnElement(BankReceiptLocators.BanReceiptCRDRField, ElementType.Xpath);
			commonFunc.hardWait(200);
			webDB.selectDropDownByText(BankReceiptLocators.BankReceiptLineCRDRDropDown, DRCR, ElementType.Xpath);
			webDB.pressTabKey();
//			commonFunc.checkRefreshCellsDisappear();
			String GetTextDRCR = webDB.getTextFromElement(BankReceiptLocators.BanReceiptCRDRField, ElementType.Xpath);
			if (DRCR.equals(GetTextDRCR)) {
				logs.logging("INFO", "Line Wise DR / CR Is Choosen Successfully In Bank Receipt");
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
	public void BankReceiptWithExpAndRevenueWithoutGST(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		bankpaymentfunc.createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocModeBR"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		bankpaymentfunc.verifyBankPaymentDetailsWithDB(BankName,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String PartyName = BankReceipts[i][3];
			String EntityName = BankReceipts[i][5];
			String CPCCodeDesc = BankReceipts[i][6];
			String BillNumber = BankReceipts[i][7];
			String PayAmount = BankReceipts[i][8];
			String DRCR = BankReceipts[i][9];
			String CURCY = BankReceipts[i][10];
			String EXRate = BankReceipts[i][11];
			String TransCURCY = BankReceipts[i][12];
			String BaseCURCY = BankReceipts[i][13];
			String HSNCode = BankReceipts[i][14];
			String AssesableValue = BankReceipts[i][16];
			String LineWiseDRCR = BankReceipts[i][25];
			String ChargesFlag = BankReceipts[i][29];
			String StateCode = BankReceipts[i][30];
			String GSTSupplierName = BankReceipts[i][31];
			String GSTIN = BankReceipts[i][32];
			if (!GLAccountDesc.equals("")) {
				clickBRLineAddButton();
				addBankReceiptLineDetails(GLAccountDesc, CPCCodeDesc);
				enterValueInAmtInTCField(PayAmount);
				chooseLineWiseDRCRInBR(LineWiseDRCR);
				clickBRLineWiseSaveButton();
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalREVUSD"), 0, 10,
							dbCommonFunctions.ExchangeRateDetails(Currency,
									commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
				}
				verifyBankReceiptLineDetailsWithDB(SheetName, i);
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBRHeader(BaseCURCY);
			}
		}
		checkARMControlBankNameRqrdFlag(commonFunc.getDataFromTestDataSheet("SBIBankName"));
		checkARMControlReceivedFromRqrdFlag();
		clickCreateJournalButtonInBR();
		if (Currency.equals("INR")) {
			verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalREV"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
		} else {
			verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalREVUSD"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
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
	public void BankReceiptWithExpAndRevenueWithGST(String SheetName, String Currency) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		bankpaymentfunc.createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocModeBR"),
				commonFunc.getDataFromTestDataSheet("BankName"), Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		bankpaymentfunc.verifyBankPaymentDetailsWithDB(commonFunc.getDataFromTestDataSheet("BankName"),
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String PartyName = BankReceipts[i][3];
			String EntityName = BankReceipts[i][5];
			String CPCCodeDesc = BankReceipts[i][6];
			String BillNumber = BankReceipts[i][7];
			String PayAmount = BankReceipts[i][8];
			String DRCR = BankReceipts[i][9];
			String CURCY = BankReceipts[i][10];
			String EXRate = BankReceipts[i][11];
			String TransCURCY = BankReceipts[i][12];
			String BaseCURCY = BankReceipts[i][13];
			String HSNCode = BankReceipts[i][14];
			String AssesableValue = BankReceipts[i][16];
			String LineWiseDRCR = BankReceipts[i][25];
			String ChargesFlag = BankReceipts[i][29];
			String StateCode = BankReceipts[i][30];
			String GSTSupplierName = BankReceipts[i][31];
			String GSTIN = BankReceipts[i][32];
			if (!GLAccountDesc.equals("")) {
				clickBRLineAddButton();
				addBankReceiptLineDetails(GLAccountDesc, CPCCodeDesc);
				enterValueInAmtInTCField(PayAmount);
				chooseLineWiseDRCRInBR(LineWiseDRCR);
				chooseHSNCodeInBR(HSNCode);
				clickBRLineWiseSaveButton();
				clickLineWiseGSTIconInBR(i);
				enterValuesInGSTScreen(StateCode, GSTSupplierName, GSTIN, Today, BillNumber);
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
				}
				verifyBankReceiptLineDetailsWithDB(SheetName, i);
			}
			if (!Currency.equals("INR")) {
				enterRealizedAmountInBRHeader(BaseCURCY);
			}
		}
		checkARMControlBankNameRqrdFlag(commonFunc.getDataFromTestDataSheet("SBIBankName"));
		checkARMControlReceivedFromRqrdFlag();
		clickCreateJournalButtonInBR();
		verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalREVGST"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Choose HSN Code In BR
	 * 
	 * @author Harikrishna - RMITS
	 * @param HSNCode
	 */
	public void chooseHSNCodeInBR(String HSNCode) {
		flag = webDB.waitForElement(BankReceiptLocators.BankReceiptHSNCodeField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankReceiptLocators.BankReceiptHSNCodeField, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(BankReceiptLocators.BankReceiptLineHsnCodeLov, HSNCode);
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
	public void clickLineWiseGSTIconInBR(int i) {
		flag = webDB.waitForElement(BankReceiptLocators.BankLineGSTButton(i), ElementType.CssSelector);
		if (flag) {
			webDB.clickAnElement(BankReceiptLocators.BankLineGSTButton(i), ElementType.CssSelector);
			flag = webDB.waitForElement(BankReceiptLocators.BankReceiptGSTDetailsHeaderName, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Line Wise GST Details Button Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Enter Values In GST Screen
	 * 
	 * @author Harikrishna -RMITS
	 * @param StateCode
	 * @param GSTSupplierName
	 * @param GSTIN
	 * @param BillDate
	 * @param BillNumber
	 */
	public void enterValuesInGSTScreen(String StateCode, String GSTSupplierName, String GSTIN, String BillDate,
			String BillNumber) {
		flag = webDB.waitForElement(BankReceiptLocators.BankReceiptGSTFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(BankReceiptLocators.BankReceiptGSTFrame, ElementType.Xpath);
			flag = webDB.waitForElement(BankReceiptLocators.BankReceiptStateCodeLov, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Switched To Frame");
				commonFunc.searchAndSelectValueInFilters(BankReceiptLocators.BankReceiptStateCodeLov, StateCode);
				webDB.pressTabKey();
				webDB.switchToFrame(BankReceiptLocators.BankReceiptGSTFrame, ElementType.Xpath);
				flag = webDB.waitForElement(BankReceiptLocators.BankReceiptSupplierNameGST, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(BankReceiptLocators.BankReceiptSupplierNameGST, GSTSupplierName,
							ElementType.Xpath);
					webDB.pressTabKey();
					logs.logging("INFO", "GST Supplier Name " + GSTSupplierName + " Is Inserted Successfully");
					flag = webDB.waitForElement(BankReceiptLocators.BankReceiptGSTINFieldInput, ElementType.Xpath);
					if (flag) {
						webDB.sendTextToAnElement(BankReceiptLocators.BankReceiptGSTINFieldInput, GSTIN,
								ElementType.Xpath);
						webDB.pressTabKey();
						logs.logging("INFO", "GSTIN Number " + GSTIN + " Is Inserted Successfully");
						flag = webDB.waitForElement(BankReceiptLocators.BankReceiptBillDateGSTInput, ElementType.Xpath);
						if (flag) {
							webDB.sendTextToAnElement(BankReceiptLocators.BankReceiptBillDateGSTInput, BillDate,
									ElementType.Xpath);
							webDB.pressTabKey();
							logs.logging("INFO", "Bill Date " + BillDate + " Is Inserted Successfully");
							flag = webDB.waitForElement(BankReceiptLocators.BankReceiptGStBillNumberInputField,
									ElementType.Xpath);
							if (flag) {
								webDB.sendTextToAnElement(BankReceiptLocators.BankReceiptGStBillNumberInputField,
										BillNumber, ElementType.Xpath);
								webDB.pressTabKey();
								logs.logging("INFO", "Bill Number " + BillNumber + " Is inserted Successfully");
								flag = webDB.waitForElement(BankReceiptLocators.BankReceiptGSTDetailsSaveButton,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(BankReceiptLocators.BankReceiptGSTDetailsSaveButton,
											ElementType.Xpath);
									webDB.switchToDefaultContent();
									flag = webDB.waitForElement(BankReceiptLocators.BankReceiptGSTCloseButton,
											ElementType.Xpath);
									if (flag) {
										logs.logging("INFO",
												"Line Wise GST Details Save Button Is Clicked Successfully");
										webDB.clickAnElement(BankReceiptLocators.BankReceiptGSTCloseButton,
												ElementType.Xpath);
										flag = webDB.waitForElement(BankReceiptLocators.CreateJournal,
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
	public void BRSinglePartySingleInvoiceThroughAddButtonBankCurcyUSD(String SheetName) {
		salesInvoiceFunc.verifyDirectSalesInvoiceBR(webDB.getDataFromProperties("BRSPSI"),
				commonFunc.getDataFromTestDataSheet("CustomerName"));
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR SPSI Through Get Document
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 */
	public void BRSinglePartySingleInvoiceThroughGetDocumentBankCurcyUSD(String SheetName) {
		salesInvoiceFunc.verifyDirectSalesInvoiceBR(webDB.getDataFromProperties("BRSPSI"),
				commonFunc.getDataFromTestDataSheet("CustomerName"));
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR SPSI Through pending Docs
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 */
	public void BRSinglePartySingleInvoiceThroughPendingDocsBankCurcyUSD(String SheetName) {
		salesInvoiceFunc.verifyDirectSalesInvoiceBR(webDB.getDataFromProperties("BRSPSI"),
				commonFunc.getDataFromTestDataSheet("CustomerName"));
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR SPMI Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRSinglePartyMultiInvoiceThroughAddButtonBankCurcyUSD(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR SPMI Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRSinglePartyMultiInvoiceThroughGetDocumentBankCurcyUSD(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR SPMI Through Pending Docs
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRSinglePartyMultiInvoiceThroughPendingDocsBankCurcyUSD(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR MPSI Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRMultiPartySingleInvoiceThroughAddButtonBankCurcyUSD(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBR(SISheetName, Customer);
				excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 25 + i,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(i - 1),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughAddButtonMPSI(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR MPSI Through Get Document
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 */
	public void BRMultiPartySingleInvoiceThroughGetDocumentBankCurcyUSD(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBR(SISheetName, Customer);
				excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 25 + i,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(i - 1),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughGetDocumentMPSI(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR MPSI Through Pending Docs
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRMultiPartySingleInvoiceThroughPendingDocsBankCurcyUSD(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBR(SISheetName, Customer);
				excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 25 + i,
						dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(i - 1),
								commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughPendingDocsMPSI(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR With MPMI Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRMultiPartyMultiInvoiceThroughAddButtonBankCurcyUSD(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughAddButtonMPMI(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is USed to Create BR MPMI Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRMultiPartyMultiInvoiceThroughGetDocumentBankUSD(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughGetDocumentMPMI(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is USed To Create BR MPMI Through Penidng Docs
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRMultiPartyMultiInvoiceThroughPendingDocsBankCurcyUSD(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughPendingDocsMPMI(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR MPMI Through Add Button With New Reference
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRMultiPartyMultiInvoiceThroughAddButtonWithNewRefBankCurcyUSD(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughAddButtonMPMIWithNewRef(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR MPMI Through Get Document With New Reference
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRMultiPartyMultiInvoiceThroughGetDocumentWithNewrefBankCurcyUSD(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughGetDocumentWithNewRef(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR MPMI Through Pending Docs With New Reference
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BRMultiPartyMultiInvoiceThroughPendingDocsWithNewRefBankCurcyUSD(String SheetName) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Customer = BankReceipts[i][2];
			String SISheetName = BankReceipts[i][4];
			if (!Customer.equals("")) {
				salesInvoiceFunc.verifyDirectSalesInvoiceBRMulti(Customer, SISheetName, SheetName, i);
			}
		}
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughPendingDocsWithNewRef(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Create BR SPSI Through pending Docs
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 */
	public void BankReceiptSIWithChargesBankCurcyUSD(String SheetName) {
		salesInvoiceFunc.verifyDirectSalesInvoiceBR(webDB.getDataFromProperties("BRSPSI"),
				commonFunc.getDataFromTestDataSheet("CustomerName"));
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SIHeaderDetails(VariableHandler.getMultipleSIDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SalesInvoiceDBTotalAmount")));
		verifyCustomerDocumentWithDBAfterSI(VariableHandler.getMultipleSIDocs());
		BankReceiptThroughAddButtonWithCharges(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		verifyCustomerDocumentWithDBAfterBRV(VariableHandler.getBankPaymentOrReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Click The Pending Party Documents In Bank Payment
	 * Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickPendingReceivablesAdvnaceInBR() {
		flag = webDB.waitForElement(BankReceiptLocators.BankReceiptPendingReceivablesAdvButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankReceiptLocators.BankReceiptPendingReceivablesAdvButton, ElementType.Xpath);
			flag = webDB.waitForElement(BankReceiptLocators.BankReceiptPendingReceivablesAdvFrameName,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Bank Receipt Pending Receivables Advance Button Is Clicked Successfully");
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
	public void clickPendingRecivablesAdvanceButtonInPendDRDocs() {
		flag = webDB.waitForElement(BankReceiptLocators.PendingDRPendingreceivablesAdvanceButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankReceiptLocators.PendingDRPendingreceivablesAdvanceButton, ElementType.Xpath);
			flag = webDB.waitForElement(BankReceiptLocators.BankReceiptPendingReceivablesAdvFrameName,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO",
						"Pending Receivables Advance Button Is Clicked Successfully In The Pending CR Docs");
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
	public void selectAdvanceDocumentInPendingReceivablesAdvance(String PartyName, ArrayList<String> VoucherNumber,
			String SheetName) {
		flag = webDB.switchToFrame(BankReceiptLocators.BankReceiptPendingReceivablesAdvFrameName, ElementType.Xpath);
		if (flag) {
			String GetTextPartyName = webDB.getDomPropertyFromElement(
					BankReceiptLocators.BankReceiptPendingReceivablesAdvPartyNameInputField, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			if (GetTextPartyName.equals(PartyName)) {
				for (int i = 1; i <= VoucherNumber.size(); i++) {
					commonFunc.searchAndSelectValueInFilters(
							BankReceiptLocators.BankReceiptPendingReceivablesAdvanceDocNumberLovButton,
							VoucherNumber.get(i - 1));
					webDB.switchToFrame(BankReceiptLocators.BankReceiptPendingReceivablesAdvFrameName,
							ElementType.Xpath);
					flag = webDB.waitForElement(BankReceiptLocators.BankReceiptPendingReceivablesAdvSearchButton,
							ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.doubleClickAnElement(BankReceiptLocators.BankReceiptPendingReceivablesAdvSearchButton,
								ElementType.Xpath);
						flag = webDB.waitForElement(BankReceiptLocators.BRAdvPendingReceivablesDocumentSelectCheckBox(
								VoucherNumber.get(i - 1)), ElementType.Xpath);
						// TODO
//						verifyPendingReceivablesSIDocumentThroughGetDocWithDB(SheetName, VoucherNumber.get(i - 1), i);
						// TODO
						if (flag) {
							commonFunc.checkRefreshCellsDisappear();
							webDB.clickAnElement(BankReceiptLocators.BRAdvPendingReceivablesDocumentSelectCheckBox(
									VoucherNumber.get(i - 1)), ElementType.Xpath);
							commonFunc.hardWait(6000);
							flag = webDB.waitForElement(
									BankReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "In Pending Receivables Documents This Voucher Number "
										+ VoucherNumber.get(i - 1) + " Is Selected Successfully");
							}
						}
					}
				}
				webDB.moveToElement(BankReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
						ElementType.Xpath);
				webDB.javaScriptClickAnElement(BankReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
						ElementType.Xpath);
				commonFunc.hardWait(3000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(BankReceiptLocators.BankReceiptHeaderCreateJournalButton,
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
	public void chooseAdvanceDocumentNumberAndClickSearchButtonInPendingReceivablesAdvance(
			ArrayList<String> VoucherNumber, String SheetName) {
		flag = webDB.switchToFrame(BankReceiptLocators.BankReceiptPendingReceivablesAdvFrameName, ElementType.Xpath);
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
						BankReceiptLocators.BankReceiptPendingReceivablesAdvanceDocNumberLovButton,
						VoucherNumber.get(i - 1));
				webDB.switchToFrame(BankReceiptLocators.BankReceiptPendingReceivablesAdvFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(BankReceiptLocators.BankReceiptPendingReceivablesAdvSearchButton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankReceiptLocators.BankReceiptPendingReceivablesAdvSearchButton,
							ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(1000);
					String GetTextDocumentNumber = webDB.getTextFromElement(
							BankReceiptLocators.PendingReceivablesAdvanceDocumentNumberField, ElementType.Xpath);
					logs.logging("INFO", "Get Text Document Number Is " + GetTextDocumentNumber);
					if (GetTextDocumentNumber.equals(VoucherNumber.get(i - 1))) {
						flag = webDB.waitForElement(BankReceiptLocators.BRAdvPendingReceivablesDocumentSelectCheckBox(
								VoucherNumber.get(i - 1)), ElementType.Xpath);
//						verifyPendingReceivablesSIDocumentThroughGetDocWithDB(SheetName, VoucherNumber.get(i - 1), i);
						if (flag) {
							webDB.clickAnElement(BankReceiptLocators.BRAdvPendingReceivablesDocumentSelectCheckBox(
									VoucherNumber.get(i - 1)), ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(3000);
							flag = webDB.waitForElement(
									BankReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
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
			flag = webDB.waitForElement(BankReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
					ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(BankReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
						ElementType.Xpath);
				webDB.javaScriptClickAnElement(BankReceiptLocators.BankReceiptGetDocPendingReceivableScreenOkButton,
						ElementType.Xpath);
				commonFunc.hardWait(500);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(BankReceiptLocators.BankReceiptHeaderCreateJournalButton,
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
	public void chooseAdvanceDocumentNumberInPendingReceivablesAdvScreen(ArrayList<String> DocumentNumber, String Type,
			String PayMode, String BankName, String CreationType, String SheetName,
			ArrayList<String> SalesOrderNumber) {
		flag = webDB.waitForElement(BankReceiptLocators.BankReceiptPendingReceivablesAdvFrameName, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(BankReceiptLocators.BankReceiptPendingReceivablesAdvFrameName, ElementType.Xpath);
			for (int i = 1; i <= DocumentNumber.size(); i++) {
				commonFunc.searchAndSelectValueInFilters(
						BankReceiptLocators.BankReceiptPendingReceivablesAdvanceDocNumberLovButton,
						DocumentNumber.get(i - 1));
				webDB.switchToFrame(BankReceiptLocators.BankReceiptPendingReceivablesAdvFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(BankReceiptLocators.BankReceiptPendingReceivablesAdvSearchButton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankReceiptLocators.BankReceiptPendingReceivablesAdvSearchButton,
							ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					String GetTextDocumentNumber = webDB.getTextFromElement(
							BankReceiptLocators.PendingReceivablesAdvanceDocumentNumberField, ElementType.Xpath);
					if (GetTextDocumentNumber.equals(DocumentNumber.get(i - 1))) {
						verifyPendingReceivablesAdvanceSODocumentThroughGetDocWithDB(SheetName,
								DocumentNumber.get(i - 1), i, SalesOrderNumber.get(i - 1));
						commonFunc.hardWait(1000);
						webDB.clickAnElement(BankReceiptLocators.BRAdvPendingReceivablesDocumentSelectCheckBox(
								DocumentNumber.get(i - 1)), ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(1500);
						flag = webDB.waitForElement(BankReceiptLocators.PendingReceivablesAdvanceCreateButton,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "This Sales Invoice Document " + DocumentNumber.get(i - 1)
									+ " Is Selected Successfully");
							if (DocumentNumber.size() > 1) {
								commonFunc.checkRefreshCellsDisappear();
								webDB.clickAnElement(
										BankReceiptLocators.BankPendingReceivablesAdvancePartyDocumentClearButton,
										ElementType.Xpath);
								flag = webDB.waitForElement(
										BankReceiptLocators.BankReceiptPendingReceivablesAdvSearchButton,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(
											BankReceiptLocators.BankReceiptPendingReceivablesAdvSearchButton,
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
			webDB.moveToElement(BankReceiptLocators.PendingReceivablesAdvanceCreateButton, ElementType.Xpath);
			webDB.javaScriptClickAnElement(BankReceiptLocators.PendingReceivablesAdvanceCreateButton,
					ElementType.Xpath);
			logs.logging("INFO", "Create Button Is Clicked Successfully");
			commonFunc.checkRefreshCellsDisappear();
			if (!CreationType.equals("Single")) {
				flag = webDB.waitForElement(BankReceiptLocators.PendingReceivablesAdvanceMergeLabelInPending,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankReceiptLocators.PendingReceivablesAdvanceMergeLabelInPending,
							ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
				}
			}
			flag = webDB.waitForElement(BankReceiptLocators.PendingReceivablesAdvanceTypeDropDown, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownByText(BankReceiptLocators.PendingReceivablesAdvanceTypeDropDown, Type,
						ElementType.Xpath);
				logs.logging("INFO", "Type Successfully Choosen As " + Type);
				if (Type.equals("Bank")) {
					flag = webDB.waitForElement(BankReceiptLocators.PendingReceivablesAdvancePayModeDropDown,
							ElementType.Xpath);
					if (flag) {
						webDB.selectDropDownByText(BankReceiptLocators.PendingReceivablesAdvancePayModeDropDown,
								PayMode, ElementType.Xpath);
						logs.logging("INFO", "Pay Mode Successfully Choosen As " + PayMode);
					}
				}
				commonFunc.searchAndSelectValueInFilters(BankReceiptLocators.PendingReceivablesAdvanceBankNameLOVBUtton,
						BankName);
				webDB.switchToFrame(BankReceiptLocators.BankReceiptPendingReceivablesAdvFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(BankReceiptLocators.PendingReceivablesAdvanceOkButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankReceiptLocators.PendingReceivablesAdvanceOkButton, ElementType.Xpath);
					webDB.switchToDefaultContent();
					if (Type.equals("Bank")) {
						flag = webDB.waitForElement(BankReceiptLocators.BankReceiptScreenName, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"OK Button Is Clicked Successfully And Navigated To Bank Receipt Screen");
						}
					} else {
						flag = webDB.waitForElement(BankReceiptLocators.CashPaymentScreenName, ElementType.Xpath);
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
	public void AdvanceBankReceiptThroughAddButton(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		bankpaymentfunc.createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocModeBR"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		bankpaymentfunc.verifyBankPaymentDetailsWithDB(BankName,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String PartyName = BankReceipts[i][3];
			String EntityName = BankReceipts[i][5];
			String CPCCodeDesc = BankReceipts[i][6];
			String BillNumber = BankReceipts[i][7];
			String PayAmount = BankReceipts[i][8];
			String DRCR = BankReceipts[i][9];
			String CURCY = BankReceipts[i][10];
			String EXRate = BankReceipts[i][11];
			String TransCURCY = BankReceipts[i][12];
			String BaseCURCY = BankReceipts[i][13];
			String AssesableValue = BankReceipts[i][16];
			if (!GLAccountDesc.equals("")) {
				clickBRLineAddButton();
				addBankReceiptLineDetails(GLAccountDesc, CPCCodeDesc);
				clickBRLineWiseSaveButton();
				clickBRLineWiseAddButton(i);
				clickPendingReceivablesAdvnaceInBR();
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
				}
				selectAdvanceDocumentInPendingReceivablesAdvance(PartyName, VariableHandler.getMultipleAdvRecDocs(),
						SheetName);
				verifyBankReceiptLineDetailsWithDB(SheetName, i);
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBRHeader("68.31");
			}
		}
		checkARMControlBankNameRqrdFlag(BankName);
		checkARMControlReceivedFromRqrdFlag();
		clickCreateJournalButtonInBR();
//		verifyOverallHeaderAmountWithDB(SheetName);
//		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
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
	public void AdvanceBankReceiptThroughGetDocument(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		bankpaymentfunc.createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocModeBR"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		bankpaymentfunc.verifyBankPaymentDetailsWithDB(BankName,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		clickGetDocumentInBRHeader();
		clickPendingReceivablesAdvnaceInBR();
		chooseAdvanceDocumentNumberAndClickSearchButtonInPendingReceivablesAdvance(
				VariableHandler.getMultipleAdvRecDocs(), SheetName);
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String BaseCURCY = BankReceipts[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
				}
//				verifyBankReceiptLineDetailsWithDB(SheetName, i);
			}
			if (!Currency.equals("INR") || BankName.equals("JP MORGAN CHASE")) {
				enterRealizedAmountInBRHeader("68.31");
			}
		}
		checkARMControlBankNameRqrdFlag(BankName);
		checkARMControlReceivedFromRqrdFlag();
		clickCreateJournalButtonInBR();
//		verifyOverallHeaderAmountWithDB(SheetName);
//		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
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
	public void AdvanceBankReceiptThroughPendingDocs(String CreationType, String SheetName, String Currency,
			String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingDRDocsButton();
		clickPendingRecivablesAdvanceButtonInPendDRDocs();
		chooseAdvanceDocumentNumberInPendingReceivablesAdvScreen(VariableHandler.getMultipleAdvRecDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentType"), commonFunc.getDataFromTestDataSheet("BankPayMode"),
				BankName, CreationType, SheetName, VariableHandler.getMultipleSoDocs());
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		sendReferenceNumberInBR(commonFunc.getDataFromTestDataSheet("Reference Number"));
		clickBRHeaderWiseSaveButton();
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String BaseCURCY = BankReceipts[i][16];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
					for (int j = 1; j <= VariableHandler.getMultipleSoDocs().size(); j++) {
						verifyBankReceiptAdvanceLineDetailsWithDB(SheetName, i,
								VariableHandler.getMultipleSoDocs().get(i - 1));
						excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalUSD"), 1, 5 + j,
								dbCommonFunctions.SOAdvanceDetails(VariableHandler.getMultipleSoDocs().get(j - 1),
										commonFunc.getDataFromTestDataSheet("SODBAdvToBeReceivedColumnName")));
						excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalUSD"), 2, 5 + j,
								dbCommonFunctions.SOAdvanceDetails(VariableHandler.getMultipleSoDocs().get(j - 1),
										commonFunc.getDataFromTestDataSheet("SODBAdvToBeReceivedColumnName")));
					}
				} else {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
				}
				for (int j = 1; j <= VariableHandler.getMultipleSoDocs().size(); j++) {
					verifyBankReceiptAdvanceLineDetailsWithDB(SheetName, i,
							VariableHandler.getMultipleSoDocs().get(i - 1));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("Journal"), 1, 5 + j,
							dbCommonFunctions.SOAdvanceDetails(VariableHandler.getMultipleSoDocs().get(j - 1),
									commonFunc.getDataFromTestDataSheet("SODBAdvToBeReceivedColumnName")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("Journal"), 2, 5 + j,
							dbCommonFunctions.SOAdvanceDetails(VariableHandler.getMultipleSoDocs().get(j - 1),
									commonFunc.getDataFromTestDataSheet("SODBAdvToBeReceivedColumnName")));
				}
			}
//			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
//				enterRealizedAmountInBRHeader(BaseCURCY);
//			}
		}
		checkARMControlBankNameRqrdFlag(BankName);
		checkARMControlReceivedFromRqrdFlag();
		clickCreateJournalButtonInBR();
		if (Currency.equals("INR")) {
			verifyJournalAmountWithDB(webDB.getDataFromProperties("Journal"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
			for (int j = 1; j <= VariableHandler.getMultipleSoDocs().size(); j++) {
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("Journal"), 1, 5 + j, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("Journal"), 2, 5 + j, "0");
			}
		} else {
			verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalUSD"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
			for (int j = 1; j <= VariableHandler.getMultipleSoDocs().size(); j++) {
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalUSD"), 1, 5 + j, "0");
				excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalUSD"), 2, 5 + j, "0");
			}
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
		}
	}

	/**
	 * This Method Is Used To Add The Document Through pending Documents
	 * 
	 * @author Harikrishna - RMITS
	 * @param CreationType
	 * @param SheetName
	 * @param Currency
	 */
	public void AdvanceBankReceiptThroughPendingDocsBankUSD(String CreationType, String SheetName, String Currency,
			String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingDRDocsButton();
		clickPendingRecivablesAdvanceButtonInPendDRDocs();
		chooseAdvanceDocumentNumberInPendingReceivablesAdvScreen(VariableHandler.getMultipleAdvRecDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentType"), commonFunc.getDataFromTestDataSheet("BankPayMode"),
				BankName, CreationType, SheetName, VariableHandler.getMultipleSoDocs());
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		sendReferenceNumberInBR(commonFunc.getDataFromTestDataSheet("Reference Number"));
		clickBRHeaderWiseSaveButton();
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String BaseCURCY = BankReceipts[i][16];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
				}
				for (int j = 1; j <= VariableHandler.getMultipleSoDocs().size(); j++) {
					verifyBankReceiptAdvanceLineDetailsWithDB(SheetName, i,
							VariableHandler.getMultipleSoDocs().get(i - 1));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalINR"), 1, 5 + j,
							dbCommonFunctions.SOAdvanceDetails(VariableHandler.getMultipleSoDocs().get(j - 1),
									commonFunc.getDataFromTestDataSheet("SODBAdvToBeReceivedColumnName")));
					excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalINR"), 2, 5 + j,
							dbCommonFunctions.SOAdvanceDetails(VariableHandler.getMultipleSoDocs().get(j - 1),
									commonFunc.getDataFromTestDataSheet("SODBAdvToBeReceivedColumnName")));
				}
			}
//			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
//				enterRealizedAmountInBRHeader(BaseCURCY);
//			}
		}
		checkARMControlBankNameRqrdFlag(BankName);
		checkARMControlReceivedFromRqrdFlag();
		clickCreateJournalButtonInBR();
		verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalINR"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
		for (int j = 1; j <= VariableHandler.getMultipleSoDocs().size(); j++) {
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalINR"), 1, 5 + j, "0");
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("JournalINR"), 2, 5 + j, "0");
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
		}
	}

	/**
	 * This Method Is Used To Verify The Journal Amount With DataBase
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void verifyJournalAmountWithDB(String SheetName, String DocumentNumber, String FileName) {
		BankReceipts = excelUtils.readAllDataInExcel(FileName, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String Unit = BankReceipts[i][1];
			String GLAccountNumber = BankReceipts[i][2];
			String GLAccountDesc = BankReceipts[i][3];
			String CPC = BankReceipts[i][4];
			String CPCDesc = BankReceipts[i][5];
			String Debit = BankReceipts[i][8];
			String Credit = BankReceipts[i][9];
			if (!Unit.equals("")) {
				String SeqNo = String.valueOf(i);

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.applicationJournalViewDetails(DocumentNumber,
						SeqNo, commonFunc.getDataFromTestDataSheet("AJDBUnitColumnName")), Unit);
				logs.logging("INFO",
						" In The View Journal Screen Unit Id Is Matched With Database Values Against This Document Number "
								+ DocumentNumber + " Journal Seq Number " + i);

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.applicationJournalViewDetails(DocumentNumber,
						SeqNo, commonFunc.getDataFromTestDataSheet("AJDBGLAccountColumnName")), GLAccountNumber);
				logs.logging("INFO",
						" In The View Journal Screen GL Account Number Is Matched With Database Values Against This Document Number "
								+ DocumentNumber + " Journal Seq Number " + i);

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.applicationJournalViewDetails(DocumentNumber,
						SeqNo, commonFunc.getDataFromTestDataSheet("AJDBGLAccountDescColumnName")), GLAccountDesc);
				logs.logging("INFO",
						" In The View Journal Screen GL Account Number Description Is Matched With Database Values Against This Document Number "
								+ DocumentNumber + " Journal Seq Number " + i);

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.applicationJournalViewDetails(DocumentNumber,
						SeqNo, commonFunc.getDataFromTestDataSheet("AJDBDebitAmountColumnName")), Debit);
				logs.logging("INFO",
						" In The View Journal Screen Debit Amount Is Matched With Database Values Against This Document Number"
								+ DocumentNumber + " Journal Seq Number " + i);

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.applicationJournalViewDetails(DocumentNumber,
						SeqNo, commonFunc.getDataFromTestDataSheet("AJDBCreditAmountColumnName")), Credit);
				logs.logging("INFO",
						" In The View Journal Screen Credit Amount Is Matched With Database Values Against This Document Number"
								+ DocumentNumber + " Journal Seq Number " + i);
			}
		}
	}

	/**
	 * This Method Is Used to Create BR SPSI Through Add Button
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 * @throws IOException
	 */
	public void ABRSinglePartySingleSOThroughAddButton(String SheetName) throws IOException {
		salesOrdFunc.advanceSalesOrder();
		VariableHandler.setMultipleAdvRecDocs(salesOrdFunc.MultiAdvReceiptDocumentNumber);
		AdvanceBankReceiptThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create BR SPSI Through Get Document
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 * @throws IOException
	 */
	public void ABRSinglePartySingleSOThroughGetDocument(String SheetName) throws IOException {
		salesOrdFunc.advanceSalesOrder();
		VariableHandler.setMultipleAdvRecDocs(salesOrdFunc.MultiAdvReceiptDocumentNumber);
		AdvanceBankReceiptThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create BR SPSI Through pending Docs
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 * @throws IOException
	 */
	public void ABRSinglePartySingleSOThroughPendingDocs(String SheetName) throws IOException {
		salesOrdFunc.advanceSalesOrder();
		VariableHandler.setMultipleSoDocs(salesOrdFunc.MultipleSO);
		VariableHandler.setMultipleAdvRecDocs(salesOrdFunc.MultiAdvReceiptDocumentNumber);
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SOAdvanceDetails(VariableHandler.getMultipleSoDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SODBAdvToBeReceivedColumnName")));
		AdvanceBankReceiptThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"), commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used to Create BR SPSI Through Add Button
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 * @throws IOException
	 */
	public void ABRSinglePartyMultiSOThroughAddButton(String SheetName) throws IOException {
		for (int i = 1; i <= 2; i++) {
			salesOrdFunc.advanceSalesOrder();
		}
		VariableHandler.setMultipleAdvRecDocs(salesOrdFunc.MultiAdvReceiptDocumentNumber);
		AdvanceBankReceiptThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create BR SPSI Through Get Document
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 * @throws IOException
	 */
	public void ABRSinglePartyMultiSOThroughGetDocument(String SheetName) throws IOException {
		for (int i = 1; i <= 2; i++) {
			salesOrdFunc.advanceSalesOrder();
		}
		VariableHandler.setMultipleAdvRecDocs(salesOrdFunc.MultiAdvReceiptDocumentNumber);
		AdvanceBankReceiptThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create BR SPSI Through pending Docs
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 * @throws IOException
	 */
	public void ABRSinglePartyMultiSOThroughPendingDocs(String SheetName) throws IOException {
		for (int i = 1; i <= 2; i++) {
			salesOrdFunc.advanceSalesOrder();
		}
		VariableHandler.setMultipleSoDocs(salesOrdFunc.MultipleSO);
		VariableHandler.setMultipleAdvRecDocs(salesOrdFunc.MultiAdvReceiptDocumentNumber);
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SOAdvanceDetails(VariableHandler.getMultipleSoDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SODBAdvToBeReceivedColumnName")));
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 27,
				dbCommonFunctions.SOAdvanceDetails(VariableHandler.getMultipleSoDocs().get(1),
						commonFunc.getDataFromTestDataSheet("SODBAdvToBeReceivedColumnName")));
		AdvanceBankReceiptThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"), commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create BR With Single Party Single Invoice
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @throws IOException
	 */
	public void ABRSinglePartySingleSOThroughAddButtonUSD(String SheetName) throws IOException {
		salesOrdFunc.advanceSalesOrderExport();
		VariableHandler.setMultipleAdvRecDocs(salesOrdFunc.MultiAdvReceiptDocumentNumber);
		AdvanceBankReceiptThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create BR With Single Party Single Invoice
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @throws IOException
	 */
	public void ABRSinglePartySingleSOThroughGetDocumentUSD(String SheetName) throws IOException {
		salesOrdFunc.advanceSalesOrderExport();
		VariableHandler.setMultipleAdvRecDocs(salesOrdFunc.MultiAdvReceiptDocumentNumber);
		AdvanceBankReceiptThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create BR With Single Party Single Invoice
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @throws IOException
	 */
	public void ABRSinglePartySingleSOThroughPendingDocsUSD(String SheetName) throws IOException {
		salesOrdFunc.advanceSalesOrderExport();
		VariableHandler.setMultipleSoDocs(salesOrdFunc.MultipleSO);
		VariableHandler.setMultipleAdvRecDocs(salesOrdFunc.MultiAdvReceiptDocumentNumber);
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SOAdvanceDetails(VariableHandler.getMultipleSoDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SODBAdvToBeReceivedColumnName")));
		AdvanceBankReceiptThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
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
	public void verifyPendingReceivablesAdvanceSODocumentThroughGetDocWithDB(String SheetName, String VoucherNumber,
			int i1, String SalesOrder) {
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String PartyID = BankReceipts[i][2];
			String PartyName = BankReceipts[i][3];
			String Entity = BankReceipts[i][5];
			String CPCDesc = BankReceipts[i][6];
			String Curcy = BankReceipts[i][10];
			String EXRate = BankReceipts[i][11];
			String Location = BankReceipts[i][23];
			String Unit = BankReceipts[i][24];
			String DrCr = BankReceipts[i][25];
			if (!PartyID.equals("")) {
				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.bankReceiptPendingReceivablesAdvanceDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("BRDBAdvSONumberColumnName")),
						dbCommonFunctions.SOHeaderDetails(SalesOrder,
								commonFunc.getDataFromTestDataSheet("SODBDocNumberColumnName")));
				logs.logging("INFO", "In Pending Receivables Advance For This Advance Document Number " + VoucherNumber
						+ " Sales Order Number Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.bankReceiptPendingReceivablesAdvanceDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("BRDBAdvPartyColumnName")),
						dbCommonFunctions.SOHeaderDetails(SalesOrder,
								commonFunc.getDataFromTestDataSheet("SoDBCustomerIDColumnName")));
				logs.logging("INFO", "In Pending Receivables Advance For This Document Number " + VoucherNumber
						+ " Customer ID Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.bankReceiptPendingReceivablesAdvanceDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("BRDBAdvCPCColumnName")),
						dbCommonFunctions.SOHeaderDetails(SalesOrder,
								commonFunc.getDataFromTestDataSheet("SODBCpcCodeIDColumnName")));
				logs.logging("INFO", "In Pending Receivables Advance For This Document Number " + VoucherNumber
						+ " CPC ID Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.bankReceiptPendingReceivablesAdvanceDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("BRDBAdvCurrencyColumnName")),
						dbCommonFunctions.SOHeaderDetails(SalesOrder,
								commonFunc.getDataFromTestDataSheet("SoDBCusrrencyColumnName")));
				logs.logging("INFO", "In Pending Receivables Advance For This Document Number " + VoucherNumber
						+ " Currency Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.bankReceiptPendingReceivablesAdvanceDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("BRDBAdvPercentageColumnName")),
						dbCommonFunctions.SOAdvanceDetails(SalesOrder,
								commonFunc.getDataFromTestDataSheet("SODBAdvPercentageColumnName")));
				logs.logging("INFO", "In Pending Receivables Advance For This Document Number " + VoucherNumber
						+ " Advance Percentage Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.bankReceiptPendingReceivablesAdvanceDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("BRDBAdvAmountColumnName")),
						dbCommonFunctions.SOAdvanceDetails(SalesOrder,
								commonFunc.getDataFromTestDataSheet("SODBAdvToBeReceivedColumnName")));
				logs.logging("INFO", "In Pending Receivables Advance For This Document Number " + VoucherNumber
						+ " Advance Amount Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.bankReceiptPendingReceivablesAdvanceDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("BRDBAdvUnitColumnName")),
						dbCommonFunctions.SOHeaderDetails(SalesOrder,
								commonFunc.getDataFromTestDataSheet("SoDBUnitColumnName")));
				logs.logging("INFO", "In Pending Receivables Advance For This Document Number " + VoucherNumber
						+ " Unit ID Is getting Matched With DB");
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
	public void verifyBankReceiptAdvanceLineDetailsWithDB(String SheetName, int i1, String SalesOrder) {

		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String PartyID = BankReceipts[i][2];
			String PartyName = BankReceipts[i][3];
			String Entity = BankReceipts[i][5];
			String CPCDesc = BankReceipts[i][6];
			String LineDrCr = BankReceipts[i][9];
			String Curcy = BankReceipts[i][10];
			String EXRate = BankReceipts[i][11];
			String AmtInTC = BankReceipts[i][12];
			String AmtInBC = BankReceipts[i][13];
			String HSNCode = BankReceipts[i][14];
			String AssesableValue = BankReceipts[i][16];
			String CGST = BankReceipts[i][17];
			String SGST = BankReceipts[i][18];
			String IGST = BankReceipts[i][19];
			String UTGST = BankReceipts[i][20];
			String CESSPCT = BankReceipts[i][21];
			String CESSAMT = BankReceipts[i][22];
			String Location = BankReceipts[i][23];
			String Unit = BankReceipts[i][24];
			String PendingDrCr = BankReceipts[i][25];
			String TotAmt = BankReceipts[i][28];
			if (!PartyID.equals("")) {
				String SeqNo = String.valueOf(i);
				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBGLAccountDescColumnName")),
						GLAccountDesc);
				logs.logging("INFO",
						" Line Wise GL Account Desc Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBSupplierDescColumnName")),
						PartyName);
				logs.logging("INFO",
						" Line Wise Party Name Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBEntityColumnName")),
						Entity);
				logs.logging("INFO",
						" Line Wise Entity Name Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBCPCcodeDescColumnName")),
						CPCDesc);
				logs.logging("INFO",
						" Line Wise CPC Desc Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBAmountTCColumnName")),
						AmtInTC);
//						dbCommonFunctions.SOAdvanceDetails(SalesOrder,
//								commonFunc.getDataFromTestDataSheet("SODBAdvReceiptInProgressColumnName")));
				logs.logging("INFO",
						" Line Wise Amount In Transaction Currency Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBDCColumnName")),
						LineDrCr);
				logs.logging("INFO",
						" Line Wise Dr / Cr Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBBCColumnName")),
						AmtInBC);
//						dbCommonFunctions.SOAdvanceDetails(SalesOrder,
//								commonFunc.getDataFromTestDataSheet("SODBAdvReceiptInProgressColumnName")));
				logs.logging("INFO",
						" Line Wise Amount In Base Currency Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBAssesableColumnName")),
						AssesableValue);
//						dbCommonFunctions.SOAdvanceDetails(SalesOrder,
//								commonFunc.getDataFromTestDataSheet("SODBAdvReceiptInProgressColumnName")));
				logs.logging("INFO",
						" Line Wise Amount In Tax Assesable Value Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBExhangeRateColumnName")),
						EXRate);
				logs.logging("INFO",
						" Line Wise Exchange Rate Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBLineCurrencyColumnName")),
						Curcy);
				logs.logging("INFO",
						" Line Wise Currency Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());
				if (SheetName.equals("BankReceiptRevExpGST")) {
					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
									SeqNo, commonFunc.getDataFromTestDataSheet("BRDBLineHSNCode")),
							HSNCode);
					logs.logging("INFO",
							" Line Wise HSN Code Is Matched With DataBase Values Against This Bank Receipt Document "
									+ VariableHandler.getBankPaymentOrReceiptDocNumber());

					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
									SeqNo, commonFunc.getDataFromTestDataSheet("BRDBLineIGSTAmt")),
							IGST);
					logs.logging("INFO",
							" Line Wise IGST Amount Is Matched With DataBase Values Against This Bank Receipt Document "
									+ VariableHandler.getBankPaymentOrReceiptDocNumber());

					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
									SeqNo, commonFunc.getDataFromTestDataSheet("BRDBLineCGSTAmt")),
							CGST);
					logs.logging("INFO",
							" Line Wise CGST Amount Is Matched With DataBase Values Against This Bank Receipt Document "
									+ VariableHandler.getBankPaymentOrReceiptDocNumber());

					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
									SeqNo, commonFunc.getDataFromTestDataSheet("BRDBLineSGSTAmt")),
							SGST);
					logs.logging("INFO",
							" Line Wise SGST Amount Is Matched With DataBase Values Against This Bank Receipt Document "
									+ VariableHandler.getBankPaymentOrReceiptDocNumber());

					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
									SeqNo, commonFunc.getDataFromTestDataSheet("BRDBLineUTGSTAmt")),
							UTGST);
					logs.logging("INFO",
							" Line Wise UTGST Amount Is Matched With DataBase Values Against This Bank Receipt Document "
									+ VariableHandler.getBankPaymentOrReceiptDocNumber());

					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
									SeqNo, commonFunc.getDataFromTestDataSheet("BRDBLineCESSPercent")),
							CESSPCT);
					logs.logging("INFO",
							" Line Wise CESS Percentage Is Matched With DataBase Values Against This Bank Receipt Document "
									+ VariableHandler.getBankPaymentOrReceiptDocNumber());

					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
									SeqNo, commonFunc.getDataFromTestDataSheet("BRDBLineCESSAmt")),
							CESSAMT);
					logs.logging("INFO",
							" Line Wise CESS Amount Is Matched With DataBase Values Against This Bank Receipt Document "
									+ VariableHandler.getBankPaymentOrReceiptDocNumber());
				}
			}
		}
	}

	/**
	 * This Method Is Used To Create BR With Single Party Single Invoice
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @throws IOException
	 */
	public void ABRSinglePartyMultiSOThroughAddButtonUSD(String SheetName) throws IOException {
		for (int i = 1; i <= 2; i++) {
			salesOrdFunc.advanceSalesOrderExport();
		}
		VariableHandler.setMultipleAdvRecDocs(salesOrdFunc.MultiAdvReceiptDocumentNumber);
		AdvanceBankReceiptThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create BR With Single Party Single Invoice
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @throws IOException
	 */
	public void ABRSinglePartyMultiSOThroughGetDocumentUSD(String SheetName) throws IOException {
		for (int i = 1; i <= 2; i++) {
			salesOrdFunc.advanceSalesOrderExport();
		}
		VariableHandler.setMultipleAdvRecDocs(salesOrdFunc.MultiAdvReceiptDocumentNumber);
		AdvanceBankReceiptThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create BR With Single Party Single Invoice
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @throws IOException
	 */
	public void ABRSinglePartyMultiSOThroughPendingDocsUSD(String SheetName) throws IOException {
		for (int i = 1; i <= 2; i++) {
			salesOrdFunc.advanceSalesOrderExport();
		}
		VariableHandler.setMultipleSoDocs(salesOrdFunc.MultipleSO);
		VariableHandler.setMultipleAdvRecDocs(salesOrdFunc.MultiAdvReceiptDocumentNumber);
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SOAdvanceDetails(VariableHandler.getMultipleSoDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SODBAdvToBeReceivedColumnName")));
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 27,
				dbCommonFunctions.SOAdvanceDetails(VariableHandler.getMultipleSoDocs().get(1),
						commonFunc.getDataFromTestDataSheet("SODBAdvToBeReceivedColumnName")));
		AdvanceBankReceiptThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Check ARM Control Of Received From Required Flag
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void checkARMControlReceivedFromRqrdFlag() {
		String ReceivedFromReqrdFlag = dbCommonFunctions.ARMControl(commonFunc.getDataFromTestDataSheet("DBBU"),
				commonFunc.getDataFromTestDataSheet("ReceivedFromRequiredFlagInARControl"));
		logs.logging("INFO", "Received From Required Flag Is " + ReceivedFromReqrdFlag);
		if (ReceivedFromReqrdFlag.equals("Y")) {
			flag = webDB.waitForElement(BankReceiptLocators.ReceivedFromRequiredInputField, ElementType.Xpath);
			if (flag) {
				webDB.clearTextField(BankReceiptLocators.ReceivedFromRequiredInputField, ElementType.Xpath);
				webDB.sendTextToAnElement(BankReceiptLocators.ReceivedFromRequiredInputField,
						commonFunc.getDataFromTestDataSheet("CustomerName"), ElementType.Xpath);
//				flag = webDB.waitForElement(BankReceiptLocators.BankHeaderWiseSaveButton, ElementType.Xpath);
//				if (flag) {
//					webDB.clickAnElement(BankReceiptLocators.BankHeaderWiseSaveButton, ElementType.Xpath);
//				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Check ARM Control Of Received From Required Flag
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void checkARMControlBankNameRqrdFlag(String BankName) {
		String BankNameRqrdFlag = dbCommonFunctions.ARMControl(commonFunc.getDataFromTestDataSheet("DBBU"),
				commonFunc.getDataFromTestDataSheet("BankNameRequiredFlagInARControl"));
		logs.logging("INFO", "Bank Name Required Flag Is " + BankNameRqrdFlag);
		if (BankNameRqrdFlag.equals("Y")) {
			if (BankName.contains("STATE BANK OF INDIA")) {
				commonFunc.searchAndSelectValueInFilters(BankReceiptLocators.BankNameLovButtonInHeader,
						commonFunc.getDataFromTestDataSheet("SBIBankName"));
			} else {
				commonFunc.searchAndSelectValueInFilters(BankReceiptLocators.BankNameLovButtonInHeader,
						commonFunc.getDataFromTestDataSheet("USDBankName"));
			}
		}
//		assertTrue(flag);
	}

	public void verifyCustomerDocumentWithDBAfterSI(ArrayList<String> DocumentNumber) {

		for (int i = 1; i <= DocumentNumber.size(); i++) {

			salesOrdFunc.verifyAssertionBothValues(
					dbCommonFunctions.customerDocumentQuery(DocumentNumber.get(i - 1),
							commonFunc.getDataFromTestDataSheet("CDLocationColumnName")),
					dbCommonFunctions.SIHeaderQuery(DocumentNumber.get(i - 1),
							commonFunc.getDataFromTestDataSheet("SILocationIDColumnName")));
			logs.logging("INFO", " Location ID Is Matched With The Sales Invoice And Customer Document Report");

			salesOrdFunc.verifyAssertionBothValues(
					dbCommonFunctions.customerDocumentQuery(DocumentNumber.get(i - 1),
							commonFunc.getDataFromTestDataSheet("CDUnitColumnName")),
					dbCommonFunctions.SIHeaderQuery(DocumentNumber.get(i - 1),
							commonFunc.getDataFromTestDataSheet("SIUnitIDColumnName")));
			logs.logging("INFO", " Unit ID Is Matched With The Sales Invoice And Customer Document Report");

			salesOrdFunc.verifyAssertionBothValues(
					dbCommonFunctions.customerDocumentQuery(DocumentNumber.get(i - 1),
							commonFunc.getDataFromTestDataSheet("CDDocumentTypeColumnName")),
					commonFunc.getDataFromTestDataSheet("SIType"));
			logs.logging("INFO", " Document Type Is Matched With The Sales Invoice And Customer Document Report");

			salesOrdFunc.verifyAssertionBothValues(
					dbCommonFunctions.customerDocumentQuery(DocumentNumber.get(i - 1),
							commonFunc.getDataFromTestDataSheet("CDCurrencyColumnName")),
					dbCommonFunctions.SIHeaderQuery(DocumentNumber.get(i - 1),
							commonFunc.getDataFromTestDataSheet("SICurrencyColumnName")));
			logs.logging("INFO", " Currency Is Matched With The Sales Invoice And Customer Document Report");

			salesOrdFunc.verifyAssertionBothValues(
					dbCommonFunctions.customerDocumentQuery(DocumentNumber.get(i - 1),
							commonFunc.getDataFromTestDataSheet("CDExRateColumnName")),
					dbCommonFunctions.SIHeaderQuery(DocumentNumber.get(i - 1),
							commonFunc.getDataFromTestDataSheet("SIExRateColumnName")));
			logs.logging("INFO", " Exchange Rate Is Matched With The Sales Invoice And Customer Document Report");

			salesOrdFunc.verifyAssertionBothValues(
					dbCommonFunctions.customerDocumentQuery(DocumentNumber.get(i - 1),
							commonFunc.getDataFromTestDataSheet("CDSITotAmtColumnName")),
					dbCommonFunctions.SIHeaderQuery(DocumentNumber.get(i - 1),
							commonFunc.getDataFromTestDataSheet("SITotAmtColumnName")));
			logs.logging("INFO",
					" Sales Invoice Total Amount Is Matched With The Sales Invoice And Customer Document Report");

			salesOrdFunc.verifyAssertionBothValues(
					dbCommonFunctions.customerDocumentQuery(DocumentNumber.get(i - 1),
							commonFunc.getDataFromTestDataSheet("CDSIMatAmtColumnName")),
					dbCommonFunctions.SIHeaderQuery(DocumentNumber.get(i - 1),
							commonFunc.getDataFromTestDataSheet("SIMatAmtColumnName")));
			logs.logging("INFO",
					" Sales Invoice Material Amount Is Matched With The Sales Invoice And Customer Document Report");

			salesOrdFunc.verifyAssertionBothValues(
					dbCommonFunctions.customerDocumentQuery(DocumentNumber.get(i - 1),
							commonFunc.getDataFromTestDataSheet("CDSITaxAmtColumnName")),
					dbCommonFunctions.SIHeaderQuery(DocumentNumber.get(i - 1),
							commonFunc.getDataFromTestDataSheet("SITaxAmtColumnName")));
			logs.logging("INFO",
					" Sales Invoice Tax Amount Is Matched With The Sales Invoice And Customer Document Report");
		}
	}

	public void verifyCustomerDocumentWithDBAfterBRV(String DocumentNumber) {

		salesOrdFunc.verifyAssertionBothValues(
				dbCommonFunctions.customerDocumentQuery(DocumentNumber,
						commonFunc.getDataFromTestDataSheet("CDLocationColumnName")),
				dbCommonFunctions.BRVHeaderQuery(DocumentNumber,
						commonFunc.getDataFromTestDataSheet("BRVLocationIDColumnName")));
		logs.logging("INFO", " Location ID Is Matched With The Bank Receipt Voucher And Customer Document Report");

		salesOrdFunc.verifyAssertionBothValues(
				dbCommonFunctions.customerDocumentQuery(DocumentNumber,
						commonFunc.getDataFromTestDataSheet("CDUnitColumnName")),
				dbCommonFunctions.BRVHeaderQuery(DocumentNumber,
						commonFunc.getDataFromTestDataSheet("BRVUnitIDColumnName")));
		logs.logging("INFO", " Unit ID Is Matched With The Bank Receipt Voucher And Customer Document Report");

		salesOrdFunc.verifyAssertionBothValues(
				dbCommonFunctions.customerDocumentQuery(DocumentNumber,
						commonFunc.getDataFromTestDataSheet("CDDocumentTypeColumnName")),
				commonFunc.getDataFromTestDataSheet("BRVType"));
		logs.logging("INFO", " Document Type Is Matched With The Bank Receipt Voucher And Customer Document Report");

		salesOrdFunc.verifyAssertionBothValues(
				dbCommonFunctions.customerDocumentQuery(DocumentNumber,
						commonFunc.getDataFromTestDataSheet("CDCurrencyColumnName")),
				dbCommonFunctions.BRVHeaderQuery(DocumentNumber,
						commonFunc.getDataFromTestDataSheet("BRVCurrencyColumnName")));
		logs.logging("INFO", " Currency Is Matched With The Bank Receipt Voucher And Customer Document Report");

		salesOrdFunc.verifyAssertionBothValues(
				dbCommonFunctions.customerDocumentQuery(DocumentNumber,
						commonFunc.getDataFromTestDataSheet("CDExRateColumnName")),
				dbCommonFunctions.BRVHeaderQuery(DocumentNumber,
						commonFunc.getDataFromTestDataSheet("BRVExRateColumnName")));
		logs.logging("INFO", " Exchange Rate Is Matched With The Bank Receipt Voucher And Customer Document Report");

		salesOrdFunc.verifyAssertionBothValues(
				dbCommonFunctions.customerDocumentQueryTotalAMT(DocumentNumber,
						commonFunc.getDataFromTestDataSheet("CDSIMatAmtColumnNameHK")),
				dbCommonFunctions.BRVHeaderQuery(DocumentNumber,
						commonFunc.getDataFromTestDataSheet("BRVTransAmtColumnName")));
		logs.logging("INFO",
				" Sales Invoice Total Amount Is Matched With The Bank Receipt Voucher And Customer Document Report");

		salesOrdFunc.verifyAssertionBothValues(
				dbCommonFunctions.customerDocumentQueryTotalAMT(DocumentNumber,
						commonFunc.getDataFromTestDataSheet("CDSIMatAmtColumnNameHK")),
				dbCommonFunctions.BRVHeaderQuery(DocumentNumber,
						commonFunc.getDataFromTestDataSheet("BRVTransAmtColumnName")));
		logs.logging("INFO",
				" Sales Invoice Material Amount Is Matched With The Bank Receipt Voucher And Customer Document Report");
	}

	/**
	 * This Method Is Used to Create BR SPSI Through Add Button
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 * @throws IOException
	 */
	public void ABRSinglePartySingleSOThroughAddButtonBankUSD(String SheetName) throws IOException {
		salesOrdFunc.advanceSalesOrder();
		VariableHandler.setMultipleSoDocs(salesOrdFunc.MultipleSO);
		VariableHandler.setMultipleAdvRecDocs(salesOrdFunc.MultiAdvReceiptDocumentNumber);
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SOAdvanceDetails(VariableHandler.getMultipleSoDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SODBAdvToBeReceivedColumnName")));
		AdvanceBankReceiptThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
	}

	/**
	 * This Method Is Used To Create BR SPSI Through Get Document
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 * @throws IOException
	 */
	public void ABRSinglePartySingleSOThroughGetDocumentBankUSD(String SheetName) throws IOException {
		salesOrdFunc.advanceSalesOrder();
		VariableHandler.setMultipleSoDocs(salesOrdFunc.MultipleSO);
		VariableHandler.setMultipleAdvRecDocs(salesOrdFunc.MultiAdvReceiptDocumentNumber);
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SOAdvanceDetails(VariableHandler.getMultipleSoDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SODBAdvToBeReceivedColumnName")));
		AdvanceBankReceiptThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
	}

	/**
	 * This Method Is Used To Create BR SPSI Through pending Docs
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 * @throws IOException
	 */
	public void ABRSinglePartySingleSOThroughPendingDocsBankUSD(String SheetName) throws IOException {
		salesOrdFunc.advanceSalesOrder();
		VariableHandler.setMultipleSoDocs(salesOrdFunc.MultipleSO);
		VariableHandler.setMultipleAdvRecDocs(salesOrdFunc.MultiAdvReceiptDocumentNumber);
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SOAdvanceDetails(VariableHandler.getMultipleSoDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SODBAdvToBeReceivedColumnName")));
		AdvanceBankReceiptThroughPendingDocsBankUSD(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"),
				SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
	}

	/**
	 * This Method Is Used to Create BR SPSI Through Add Button
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 * @throws IOException
	 */
	public void ABRSinglePartyMultiSOThroughAddButtonBankUSD(String SheetName) throws IOException {
		for (int i = 1; i <= 2; i++) {
			salesOrdFunc.advanceSalesOrder();
		}
		VariableHandler.setMultipleSoDocs(salesOrdFunc.MultipleSO);
		VariableHandler.setMultipleAdvRecDocs(salesOrdFunc.MultiAdvReceiptDocumentNumber);
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SOAdvanceDetails(VariableHandler.getMultipleSoDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SODBAdvToBeReceivedColumnName")));
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 27,
				dbCommonFunctions.SOAdvanceDetails(VariableHandler.getMultipleSoDocs().get(1),
						commonFunc.getDataFromTestDataSheet("SODBAdvToBeReceivedColumnName")));
		AdvanceBankReceiptThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create BR SPSI Through Get Document
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 * @throws IOException
	 */
	public void ABRSinglePartyMultiSOThroughGetDocumentBankUSD(String SheetName) throws IOException {
		for (int i = 1; i <= 2; i++) {
			salesOrdFunc.advanceSalesOrder();
		}
		VariableHandler.setMultipleSoDocs(salesOrdFunc.MultipleSO);
		VariableHandler.setMultipleAdvRecDocs(salesOrdFunc.MultiAdvReceiptDocumentNumber);
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SOAdvanceDetails(VariableHandler.getMultipleSoDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SODBAdvToBeReceivedColumnName")));
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 27,
				dbCommonFunctions.SOAdvanceDetails(VariableHandler.getMultipleSoDocs().get(1),
						commonFunc.getDataFromTestDataSheet("SODBAdvToBeReceivedColumnName")));
		AdvanceBankReceiptThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create BR SPSI Through pending Docs
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 * @throws IOException
	 */
	public void ABRSinglePartyMultiSOThroughPendingDocsBankUSD(String SheetName) throws IOException {
		for (int i = 1; i <= 2; i++) {
			salesOrdFunc.advanceSalesOrder();
		}
		VariableHandler.setMultipleSoDocs(salesOrdFunc.MultipleSO);
		VariableHandler.setMultipleAdvRecDocs(salesOrdFunc.MultiAdvReceiptDocumentNumber);
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 26,
				dbCommonFunctions.SOAdvanceDetails(VariableHandler.getMultipleSoDocs().get(0),
						commonFunc.getDataFromTestDataSheet("SODBAdvToBeReceivedColumnName")));
		excelUtils.writeDataInExcel(BRFilepath, SheetName, 1, 27,
				dbCommonFunctions.SOAdvanceDetails(VariableHandler.getMultipleSoDocs().get(1),
						commonFunc.getDataFromTestDataSheet("SODBAdvToBeReceivedColumnName")));
		AdvanceBankReceiptThroughPendingDocsBankUSD(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"),
				SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
	}

	/**
	 * This Method Is used To Add The Bank Receipt Through Add Button MPMI With New
	 * Reference
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void AdvBankReceiptWithNewRef(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		bankpaymentfunc.clickAddButtonInPaymentsRecipts();
		bankpaymentfunc.createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocModeBR"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		bankpaymentfunc.getBankPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		bankpaymentfunc.verifyBankPaymentDetailsWithDB(BankName,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"), Currency);
		BankReceipts = excelUtils.readAllDataInExcel(BRFilepath, SheetName);
		for (int i = 1; i < BankReceipts.length; i++) {
			String GLAccountDesc = BankReceipts[i][1];
			String PartyName = BankReceipts[i][3];
			String EntityName = BankReceipts[i][5];
			String CPCCodeDesc = BankReceipts[i][6];
			String BillNumber = BankReceipts[i][7];
			String PayAmount = BankReceipts[i][8];
			String DRCR = BankReceipts[i][9];
			String CURCY = BankReceipts[i][10];
			String EXRate = BankReceipts[i][11];
			String TransCURCY = BankReceipts[i][12];
			String BaseCURCY = BankReceipts[i][13];
			String AssesableValue = BankReceipts[i][16];
			if (!GLAccountDesc.equals("")) {
				clickBRLineAddButton();
				addBankReceiptLineDetails(GLAccountDesc, CPCCodeDesc);
				clickBRLineWiseSaveButton();
//				clickBRLineWiseAddButton(i);
//				clickPendingReceivablesInBR();
//				selectSalesInvoiceDocumentInPendingBankReceiptMPMI(PartyName, VariableHandler.getMultipleSIDocs(), i);
				clickLineWiseViewButton(i);
				clickAddButtonInViewDetails();
				addBillNumberAndPayAmount(BillNumber, PayAmount);
				clickViewDetailsSaveButton();
				clickBankViewDetailsCloseButton();
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BRFilepath, SheetName, i, 11, "1");
				}
				verifyBankReceiptLineDetailsWithDB(SheetName, i);
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBRHeader(BaseCURCY);
			}
		}
		checkARMControlBankNameRqrdFlag(BankName);
		checkARMControlReceivedFromRqrdFlag();
		clickCreateJournalButtonInBR();
		if (Currency.equals("INR")) {
			if (BankName.equals("JP MORGAN CHASE")) {
				verifyJournalAmountWithDB(webDB.getDataFromProperties("JournalINRAdvNR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
			} else {
				verifyJournalAmountWithDB(webDB.getDataFromProperties("BRAdvJournalNR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
			}
		} else {
			excelUtils.writeDataInExcel(BRFilepath, webDB.getDataFromProperties("BRAdvJournalUSDNR"), 0, 10,
					dbCommonFunctions.ExchangeRateDetails(Currency,
							commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
			verifyJournalAmountWithDB(webDB.getDataFromProperties("BRAdvJournalUSDNR"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BRFilepath);
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = bankpaymentfunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
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