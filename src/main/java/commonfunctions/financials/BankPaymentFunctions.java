package commonfunctions.financials;

/**
 * @author Harikrishna - RMITS
 * @company RoadMap IT Solutions October 16, 2025
 */
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.expectThrows;

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
import commonfunctions.supplychainmanagement.GrnPageFunctions;
import commonfunctions.supplychainmanagement.PurchaseOrderFunctions;
import commonfunctions.supplychainmanagement.SalesInvoiceFunctions;
import commonfunctions.supplychainmanagement.SalesOrderFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.financials.BankPaymentLocators;
import commonlocators.financials.BankReceiptLocators;
import commonlocators.financials.CashPaymentLocators;
import commonlocators.financials.PaymentReceiptsLocators;
import commonlocators.financials.PurchaseBillsLocators;
import commonlocators.supplychainmanagement.MaterialIssuanceVoucherLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import commonlocators.supplychainmanagement.SalesOrderLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class BankPaymentFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();
	public CommonFunctions commonFunc = new CommonFunctions();
	static ApprovalPageFunctions approvalPageFunc = new ApprovalPageFunctions();
	static SalesOrderFunctions salesOrdFunc = new SalesOrderFunctions();
	static DirectGrnPageFunctions Grnfunc = new DirectGrnPageFunctions();
	static SalesInvoiceFunctions salesInvoiceFunc = new SalesInvoiceFunctions();
	static BankReceiptFunctions bankreceiptfunc = new BankReceiptFunctions();
	static PurchaseOrderFunctions PurFunc = new PurchaseOrderFunctions();
	static CashPaymentFunctions cashPymtFunc = new CashPaymentFunctions();
	static GrnPageFunctions grnPageFunc = new GrnPageFunctions();
	ArrayList<String> MultiBankDocumentNumber = new ArrayList<String>();
	public ArrayList<String> MultiGRNDocs = new ArrayList<String>();

	LocalDate date = LocalDate.now();
	LocalDate previousdate = LocalDate.now().minusDays(1);
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	String Today = date.format(formatter);
	String Yesterday = previousdate.format(formatter);
	LocalDateTime myDateObj = LocalDateTime.now().plusDays(1);
	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	String tomorrowDate = myDateObj.format(myFormatObj);
	String[][] BankPayments = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
	ExcelUtils excelUtils = new ExcelUtils();
	String BPFilepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
			+ "Financials.xlsx";
	ArrayList<String> PBDocumentNumber = new ArrayList<String>();
	String BPGrnExcelFileName = "DirectGrnDetailsHK.xlsx";
//	SOMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
//	for (int i = 4; i < SOMultipleline.length; i++) {

	/**
	 * This Method Is Used To Click the Add Button In The Payment / Receipts Find
	 * Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickAddButtonInPaymentsRecipts() {
		flag = webDB.waitForElement(BankPaymentLocators.BankAddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BankAddButton, ElementType.Xpath);
			flag = webDB.waitForElement(BankPaymentLocators.BankPrefixLovButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Payments / Receipts Add Button Is clicked Sucessfully");
			}
		}
		assertTrue(flag);
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
	public void createBankPaymentEntry(String DocumentMode, String BankName, String VoucherDate, String PayMode,
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
						if (!PayMode.equals("Cheque")) {
							flag = webDB.waitForElement(BankPaymentLocators.BankRefInputField, ElementType.Xpath);
							if (flag) {
								webDB.clearTextField(BankPaymentLocators.BankRefInputField, ElementType.Xpath);
								webDB.sendTextToAnElement(BankPaymentLocators.BankRefInputField, ReferenceNumber,
										ElementType.Xpath);
							}
						} else {
							flag = webDB.waitForElement(BankPaymentLocators.BankpaymentChequeLovButton,
									ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(BankPaymentLocators.BankpaymentChequeLovButton, ElementType.Xpath);
								commonFunc.hardWait(500);
								commonFunc.checkRefreshCellsDisappear();
								webDB.pressDownArrowKey();
								webDB.pressDownArrowKey();
								webDB.pressEnterKey();
							}
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
	 * This Method is Used To Get the BankPayment Document Number
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void getBankPaymentOrReceiptDocumentNumber() {
		flag = webDB.waitForElement(BankPaymentLocators.BankDocumentNumberField, ElementType.Xpath);
		if (flag) {
			String GetTextBankPaymentOrReceiptDocNumber = webDB
					.getTextFromElement(BankPaymentLocators.BankDocumentNumberField, ElementType.Xpath);
			VariableHandler.setBankPaymentOrReceiptDocNumber(GetTextBankPaymentOrReceiptDocNumber);
			logs.logging("INFO",
					"Get Text Bank Payment Document Number Is " + VariableHandler.getBankPaymentOrReceiptDocNumber());
			MultiBankDocumentNumber.add(VariableHandler.getBankPaymentOrReceiptDocNumber());
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify BankPayment Header Details With UI Values
	 * 
	 * @author Harikrishna - RMITS
	 * @param BankName
	 * @param Prefix
	 * @param VoucherDate
	 * @param RefDate
	 */
	public void verifyBankPaymentHeaderDetailsWithUI(String BankName, String Prefix, String VoucherDate,
			String RefDate) {
		flag = webDB.waitForElement(BankPaymentLocators.BankAccHeaderField, ElementType.Xpath);
		if (flag) {
			salesOrdFunc.verifyAssertionGetTextValidation(BankPaymentLocators.BankAccHeaderField, BankName);
			salesOrdFunc.verifyAssertionGetTextValidation(BankPaymentLocators.BankVoucherPredixHeaderfield, Prefix);
			salesOrdFunc.verifyAssertionDomPropertyValidation(BankPaymentLocators.BankHeaderVoucherDateInputField,
					VoucherDate);
			salesOrdFunc.verifyAssertionDomPropertyValidation(BankPaymentLocators.BankHeaderRefDateInputField, RefDate);
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
	public void verifyBankPaymentDetailsWithDB(String BankName, String Prefix, String Curcy) {
		salesOrdFunc.verifyAssertionBothValues(
				dbCommonFunctions.BankPaymentHeaderDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
						commonFunc.getDataFromTestDataSheet("BPDBBankColumnName")),
				BankName);

		salesOrdFunc.verifyAssertionBothValues(
				dbCommonFunctions.BankPaymentHeaderDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
						commonFunc.getDataFromTestDataSheet("BPDBPrefixColumnName")),
				Prefix);

		salesOrdFunc.verifyAssertionBothValues(
				dbCommonFunctions.BankPaymentHeaderDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
						commonFunc.getDataFromTestDataSheet("BPDBCurcyColumnName")),
				Curcy);
	}

	/**
	 * This Method Is Used To Click the Line Wise Add Button In The Bank Payment
	 * Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickBPLineAddButton() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(BankPaymentLocators.BankLineWiseAddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BankLineWiseAddButton, ElementType.Xpath);
			flag = webDB.waitForElement(BankPaymentLocators.BankLineGLAccountField, ElementType.Xpath);
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
	public void addBankPaymentLineDetails(String GLAccount, String CPCcode) {
		flag = webDB.waitForElement(BankPaymentLocators.BankLineGLAccountField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BankLineGLAccountField, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.BankLineGLAccountLovButton, GLAccount);
			commonFunc.hardWait(100);
			webDB.pressTabKey();
			commonFunc.hardWait(200);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(BankPaymentLocators.BankLineCPCField, ElementType.Xpath);
			if (flag) {
				String GetTextCPC = webDB.getTextFromElement(BankPaymentLocators.BankLineCPCField, ElementType.Xpath);
				if (!GetTextCPC.equals(CPCcode)) {
					webDB.clickAnElement(BankPaymentLocators.BankLineCPCField, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.BankLineCPCLovButton, CPCcode);
				}
//				commonFunc.checkRefreshCellsDisappear();
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
	public void clickLineWiseSaveButton() {
		flag = webDB.waitForElement(BankPaymentLocators.BankLineWiseSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BankLineWiseSaveButton, ElementType.Xpath);
			commonFunc.hardWait(1500);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(BankPaymentLocators.BankLineWiseDeleteButton, ElementType.CssSelector);
			if (flag) {
				logs.logging("INFO", "Bank Payment Line Wise Save button Is clicked Successfully");
				commonFunc.hardWait(500);
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
		flag = webDB.waitForElement(BankPaymentLocators.BankLineViewButton(i), ElementType.CssSelector);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BankLineViewButton(i), ElementType.CssSelector);
			flag = webDB.waitForElementUntilGivenTime(BankPaymentLocators.BankViewFrame, ElementType.Xpath, 20);
			if (flag) {
				webDB.switchToFrame(BankPaymentLocators.BankViewFrame, ElementType.Xpath);
				logs.logging("INFO", "Switch To Payment Details Frame Successfully");
				flag = webDB.waitForElement(BankPaymentLocators.BankViewAddButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Bank Payment Line Wise View Button Is clicked Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Add Button In the Bank Payment View Details
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickAddButtonInViewDetails() {
		flag = webDB.waitForElement(BankPaymentLocators.BankViewAddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BankViewAddButton, ElementType.Xpath);
			flag = webDB.waitForElement(BankPaymentLocators.BankAgainstRefFieldName, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Button Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The Bill Number And Pay Amount In The Bank Payment
	 * View Details
	 * 
	 * @author Harikrishna - RMITS
	 * @param BillNumber
	 * @param PayAmount
	 */
	public void addBillNumberAndPayAmount(String BillNumber, String PayAmount) {
		salesOrdFunc.verifyClickAndEnterValueInTableFields(BankPaymentLocators.BankBillDocField,
				BankPaymentLocators.BankBillNumberInputField, BillNumber);
		webDB.pressTabKey();
		salesOrdFunc.verifyClickAndEnterValueInTableFields(BankPaymentLocators.BankPayAmountField,
				BankPaymentLocators.BankPayAmountInputField, PayAmount);
		webDB.pressTabKey();
	}

	/**
	 * This Methos Is Used To Change The Date In The View details Of Bank Payment
	 * 
	 * @author Harikrishna - RMITS
	 * @param VoucherDate
	 */
	public void changeDateInViewDetails(String VoucherDate) {
		flag = webDB.waitForElement(BankPaymentLocators.BankBillDateField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BankBillDateField, ElementType.Xpath);
			flag = webDB.waitForElement(BankPaymentLocators.BankBillDateInputField, ElementType.Xpath);
			if (flag) {
				webDB.clearTextField(BankPaymentLocators.BankBillDateInputField, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				webDB.clickAnElement(BankPaymentLocators.BankBillDateField, ElementType.Xpath);
				webDB.sendTextToAnElement(BankPaymentLocators.BankBillDateInputField, VoucherDate, ElementType.Xpath);
				webDB.pressTabKey();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used to Click The View Details save Button In The Bank
	 * Payments
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickViewDetailsSaveButton() {
		flag = webDB.waitForElement(BankPaymentLocators.BankViewSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BankViewSaveButton, ElementType.Xpath);
			flag = webDB.waitForElement(BankPaymentLocators.BankViewDeleteButton, ElementType.CssSelector);
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
		flag = webDB.waitForElement(BankPaymentLocators.BankViewScreenCloseButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BankViewScreenCloseButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(BankPaymentLocators.BankLineAmountInTCField, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Bank View details Is Closed Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Create JOurnal Button In The Header Of the
	 * Bank Payment Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickCreateJournalButton() {
		flag = webDB.waitForElement(BankPaymentLocators.BankHeaderCreateJournalButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BankHeaderCreateJournalButton, ElementType.Xpath);
			commonFunc.hardWait(200);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(BankPaymentLocators.BankHeaderViewJournalButton, ElementType.Xpath);
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
		flag = webDB.waitForElement(BankPaymentLocators.BankPostButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BankPostButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(BankPaymentLocators.BankWorkFlowApprovalFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Post Button Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

//	/**
//	 * This Method Is Used To Verify Bank Payments Line Details With DB
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @param GLAccountDesc
//	 * @param PartyName
//	 * @param Entity
//	 * @param CPCDesc
//	 * @param AmountInTC
//	 * @param DebitorsORCreditors
//	 * @param AmountInBC
//	 * @param AssesableValue
//	 */
//	public void verifyBankPaymentLineDetailsWithDB(String GLAccountDesc, String PartyName, String Entity,
//			String CPCDesc, String AmountInTC, String DebitorsORCreditors, String AmountInBC, String AssesableValue,
//			String ExchangeRate, String CURCY) {
//
//		salesOrdFunc.verifyAssertionBothValues(
//				dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
//						commonFunc.getDataFromTestDataSheet("BPDBGLAccountDescColumnName")),
//				GLAccountDesc);
//
//		salesOrdFunc.verifyAssertionBothValues(
//				dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
//						commonFunc.getDataFromTestDataSheet("BPDBSupplierDescColumnName")),
//				PartyName);
//
//		salesOrdFunc.verifyAssertionBothValues(
//				dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
//						commonFunc.getDataFromTestDataSheet("BPDBEntityColumnName")),
//				Entity);
//
//		salesOrdFunc.verifyAssertionBothValues(
//				dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
//						commonFunc.getDataFromTestDataSheet("BPDBCPCcodeDescColumnName")),
//				CPCDesc);
//
//		salesOrdFunc.verifyAssertionBothValues(
//				dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
//						commonFunc.getDataFromTestDataSheet("BPDBAmountTCColumnName")),
//				AmountInTC);
//
//		salesOrdFunc.verifyAssertionBothValues(
//				dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
//						commonFunc.getDataFromTestDataSheet("BPDBDCColumnName")),
//				DebitorsORCreditors);
//
//		salesOrdFunc.verifyAssertionBothValues(
//				dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
//						commonFunc.getDataFromTestDataSheet("BPDBBCColumnName")),
//				AmountInBC);
//
//		salesOrdFunc.verifyAssertionBothValues(
//				dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
//						commonFunc.getDataFromTestDataSheet("BPDBAssesableColumnName")),
//				AssesableValue);
//
//		salesOrdFunc.verifyAssertionBothValues(
//				dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
//						commonFunc.getDataFromTestDataSheet("BPDBExhangeRateColumnName")),
//				ExchangeRate);
//
//		salesOrdFunc.verifyAssertionBothValues(
//				dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
//						commonFunc.getDataFromTestDataSheet("BPDBLineCurrencyColumnName")),
//				CURCY);
//	}

	/**
	 * This Method Is Used To Check GLM Control Of BPV Auto Clear Flag And verify
	 * Bank Payment Document Status
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(String BankName, String Currency) {
		String BPVAutoClearFlag = dbCommonFunctions.GLMControl(commonFunc.getDataFromTestDataSheet("DBBU"),
				commonFunc.getDataFromTestDataSheet("GLMDBBankPaymentAutoClearFlagColumnName"));
		logs.logging("INFO", "Bank Payment (RTGS / NEFT / TT ) Auto Clear Flag Is " + BPVAutoClearFlag);
		if (BPVAutoClearFlag.equals("Y") || BankName.equals("JP MORGAN CHASE")) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("BPClearedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
		} else {
			if (Currency.equals("USD")) {
				checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("BPClearedStatus"),
						VariableHandler.getBankPaymentOrReceiptDocNumber());
			} else {
				checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("BPIssuedStatus"),
						VariableHandler.getBankPaymentOrReceiptDocNumber());
			}
		}
	}

	/**
	 * This Method Is Used To Check The Bank Payment Document Status With DB
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void checkBPDocumentStatusWithDB(String ExpectedStatus, String DocumentNumber) {
		String BPVStatus = dbCommonFunctions.BankPaymentHeaderDetails(DocumentNumber,
				commonFunc.getDataFromTestDataSheet("BPDBHeaderStatusColumnName"));
		salesOrdFunc.verifyAssertionBothValues(BPVStatus, ExpectedStatus);
		logs.logging("INFO", "Verified Bank Payment Voucher Document Status Is " + BPVStatus
				+ " Against This Bank Payment Document " + DocumentNumber);
	}

	/**
	 * This Method Is Used To Create Bank Payment Single Bill With New Reference
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return
	 */
	public boolean CreateBankPaymentSingleBillWithNewRef(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"),
				commonFunc.getDataFromTestDataSheet("BankName"), Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"));
		getBankPaymentOrReceiptDocumentNumber();
		checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyBankPaymentHeaderDetailsWithUI(commonFunc.getDataFromTestDataSheet("BankName"),
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
		verifyBankPaymentDetailsWithDB(commonFunc.getDataFromTestDataSheet("BankName"),
				commonFunc.getDataFromTestDataSheet("BankPrefix"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"));
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String PartyName = BankPayments[i][2];
			String EntityName = BankPayments[i][3];
			String CPCCodeDesc = BankPayments[i][4];
			String BillNumber = BankPayments[i][5];
			String PayAmount = BankPayments[i][6];
			String DRCR = BankPayments[i][7];
			String CURCY = BankPayments[i][8];
			String EXRate = BankPayments[i][9];
			String TransCURCY = BankPayments[i][10];
			String BaseCURCY = BankPayments[i][11];
			String AssesableValue = BankPayments[i][14];
			if (!GLAccountDesc.equals("")) {
				clickBPLineAddButton();
				addBankPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
				clickLineWiseSaveButton();
				clickLineWiseViewButton(i);
				clickAddButtonInViewDetails();
				addBillNumberAndPayAmount(BillNumber, PayAmount);
				clickViewDetailsSaveButton();
				clickBankViewDetailsCloseButton();
//				verifyBankPaymentLineDetailsWithDB(GLAccountDesc, PartyName, EntityName, CPCCodeDesc, TransCURCY, DRCR,
//						BaseCURCY, AssesableValue, EXRate, CURCY);
			}
		}
		clickCreateJournalButton();
		clickPostButton();
		flag = salesOrdFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(commonFunc.getDataFromTestDataSheet("BankName"),
					commonFunc.getDataFromTestDataSheet("BankTransCurcy"));
		}
		return flag;
	}

	/**
	 * This Method Is Used To Click The Line Wise Add Button In The Bank Payment
	 * Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickLineWiseAddButton(int i) {
		flag = webDB.waitForElement(BankPaymentLocators.BankLineAddButton(i), ElementType.CssSelector);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BankLineAddButton(i), ElementType.CssSelector);
			flag = webDB.waitForElement(BankPaymentLocators.BankPendingPartyDocumentsButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Bank Payment Line Wise Add Button Is Clicked Successfully");
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
	public void clickPendingPartyDocumentsInBP() {
		flag = webDB.waitForElement(BankPaymentLocators.BankPendingPartyDocumentsButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BankPendingPartyDocumentsButton, ElementType.Xpath);
			flag = webDB.waitForElement(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				logs.logging("INFO", "Bank Payment Pending Party Document Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used to Select the Bank Payment pending Records In the Pending
	 * Party Documnets IN the bank Payment Screen
	 * 
	 * @author Harikrishna _ RMITS
	 * @param PartyName
	 * @param VoucherNumber.get(i)
	 */
	public void selectPartyDocumentBasedOnPB(String PartyName, ArrayList<String> VoucherNumber, String SheetName) {
		flag = webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
		if (flag) {
			String GetTextPartyName = webDB.getDomPropertyFromElement(
					BankPaymentLocators.BankPendingPartyDocumentsPartyNameInputField, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			if (GetTextPartyName.equals(PartyName)) {
				for (int i = 1; i <= VoucherNumber.size(); i++) {
					commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.BankPartyDocNumberLovButton,
							VoucherNumber.get(i - 1));
					webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
					flag = webDB.waitForElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
							ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.doubleClickAnElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
								ElementType.Xpath);
						flag = webDB.waitForElement(
								BankPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i - 1)),
								ElementType.Xpath);
						if (flag) {
							commonFunc.checkRefreshCellsDisappear();
							verifyPendingPayablesPBDocumentThroughGetDoc(SheetName, VoucherNumber.get(i - 1), i); // TODO
							webDB.clickAnElement(
									BankPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i - 1)),
									ElementType.Xpath);
							commonFunc.hardWait(6000);
							flag = webDB.waitForElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "In Pending Party Documents This Voucher Number "
										+ VoucherNumber.get(i - 1) + " Is Selected Successfully");
							}
						}
					}
				}
				webDB.moveToElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton, ElementType.Xpath);
				webDB.javaScriptClickAnElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton,
						ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(BankPaymentLocators.BankHeaderCreateJournalButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "OK Button Is Clicked Successfully In The Pending Party Documents");
				}
			} else {
				logs.logging("INFO",
						"Bank Payment Line Wise Party Name And Pending Party Documents Party Name Is not Getting Matched");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public void CreateBankPaymentThroughLineAddButtonSingleBills(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
						BPGrnExcelFileName);
			}
		}
		for (int i = 1; i <= VariableHandler.getMultipleGRNDocs().size(); i++) {
//			createPurchaseBillsFromPendingGRN(VariableHandler.getMultipleGRNDocs().get(i - 1));
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(0),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number is : " + VariableHandler.getPbNumber());
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 25 + i,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i - 1),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BPThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
//		return flag;
	}

	/**
	 * This Method Is Used To Click The Get Document Button In The Header of The
	 * Bank Payment Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickGetDocumentInBPHeader() {
		flag = webDB.waitForElement(BankPaymentLocators.BankPaymentGetDocumentButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BankPaymentGetDocumentButton, ElementType.Xpath);
			flag = webDB.waitForElement(BankPaymentLocators.BankPendingPartyDocumentsButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Get Document Button In The Bank Payment Header Is Clicked Successfully");
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
	public void choosePartyNameAndClickSearchButtonInPendingPartyDocs(ArrayList<String> VoucherNumber,
			String SheetName) {
		commonFunc.hardWait(500);
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
		System.out.println(VoucherNumber.size());
		if (flag) {
			for (int i = 1; i <= VoucherNumber.size(); i++) {
				System.out.println(2);
//				commonFunc.searchAndSelectValueInFilters(
//						BankPaymentLocators.BankPaymentPendingPartyDocumentPartyLovButton, PartyName);
//				webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
//				flag = webDB.waitForElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
//						ElementType.Xpath);
//				if (flag) {
//					webDB.clickAnElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
//							ElementType.Xpath);
				commonFunc.hardWait(200);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.BankPartyDocNumberLovButton,
						VoucherNumber.get(i - 1));
				webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
							ElementType.Xpath);
					commonFunc.hardWait(1000);
					commonFunc.checkRefreshCellsDisappear();
					String GetTextDocumentNumber = webDB.getTextFromElement(
							BankPaymentLocators.PendingPayablesDocumentNumberField, ElementType.Xpath);
					logs.logging("INFO", "Get Text Document Number Is " + GetTextDocumentNumber);
					commonFunc.hardWait(500);
					commonFunc.checkRefreshCellsDisappear();
					if (GetTextDocumentNumber.equals(VoucherNumber.get(i - 1))) {
						verifyPendingPayablesPBDocumentThroughGetDoc(SheetName, VoucherNumber.get(i - 1), i);
						flag = webDB.waitForElement(
								BankPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i - 1)),
								ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(
									BankPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i - 1)),
									ElementType.Xpath);
							commonFunc.hardWait(200);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(3000);
							flag = webDB.waitForElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "In Pending Party Documents This Voucher Number "
										+ VoucherNumber.get(i - 1) + " Is Selected Successfully");
							}
						}
					} else {
						logs.logging("INFO",
								"Bank Payment Pending Line Wise Party Document Number And Pending Party Documents Number Is not Getting Matched");
					}
				}
			}
			commonFunc.hardWait(1500);
			flag = webDB.waitForElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton, ElementType.Xpath);
				webDB.javaScriptClickAnElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton,
						ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(BankPaymentLocators.BankHeaderCreateJournalButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Ok Button Is Clicked Successfully");
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
	public void choosePartyNameAndClickSearchButtonInPendingPartyDocsMPMB(ArrayList<String> VoucherNumber,
			String SheetName) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
		System.out.println(VoucherNumber.size());
		if (flag) {
			for (int i = 1; i <= VoucherNumber.size(); i++) {
				System.out.println(2);
//				commonFunc.searchAndSelectValueInFilters(
//						BankPaymentLocators.BankPaymentPendingPartyDocumentPartyLovButton, PartyName);
//				webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
//				flag = webDB.waitForElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
//						ElementType.Xpath);
//				if (flag) {
//					webDB.clickAnElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
//							ElementType.Xpath);
				commonFunc.hardWait(200);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.BankPartyDocNumberLovButton,
						VoucherNumber.get(i - 1));
				webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
							ElementType.Xpath);
					commonFunc.hardWait(300);
					commonFunc.checkRefreshCellsDisappear();
					String GetTextDocumentNumber = webDB.getTextFromElement(
							BankPaymentLocators.PendingPayablesDocumentNumberField, ElementType.Xpath);
					logs.logging("INFO", "Get Text Document Number Is " + GetTextDocumentNumber);
					commonFunc.hardWait(1000);
					commonFunc.checkRefreshCellsDisappear();
					if (GetTextDocumentNumber.equals(VoucherNumber.get(i - 1))) {
						verifyPendingPayablesPBDocumentThroughAddButton(SheetName, VoucherNumber.get(i - 1), i);
						flag = webDB.waitForElement(
								BankPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i - 1)),
								ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(
									BankPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i - 1)),
									ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(3000);
							flag = webDB.waitForElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "In Pending Party Documents This Voucher Number "
										+ VoucherNumber.get(i - 1) + " Is Selected Successfully");
							}
						}
					} else {
						logs.logging("INFO",
								"Bank Payment Pending Line Wise Party Document Number And Pending Party Documents Number Is not Getting Matched");
					}
				}
			}
			commonFunc.hardWait(1500);
			flag = webDB.waitForElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton, ElementType.Xpath);
				webDB.javaScriptClickAnElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton,
						ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(BankPaymentLocators.BankHeaderCreateJournalButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Ok Button Is Clicked Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughGetDocumentSingleBills(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
						BPGrnExcelFileName);
			}
		}
		for (int i = 1; i <= VariableHandler.getMultipleGRNDocs().size(); i++) {
//			createPurchaseBillsFromPendingGRN(VariableHandler.getMultipleGRNDocs().get(i - 1));
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(0),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number is : " + VariableHandler.getPbNumber());
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 25 + i,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i - 1),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BPThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughGetDocumentMultiBills(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i <= 2; i++) {
			String SupplierID = BankPayments[1][2];
			String GRNSheetName = BankPayments[1][4];
			Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
					BPGrnExcelFileName);
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BPThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used To Click The Pending CR Docs Button In The Payment /
	 * Receipts Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickPendingCRDocsButton() {
		flag = webDB.waitForElement(BankPaymentLocators.PendingCRDocsButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.PendingCRDocsButton, ElementType.Xpath);
			flag = webDB.waitForElement(BankPaymentLocators.PendingPayablesButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending CR Docs Button Is Clicked Successfully");
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
	public void clickPendingPayablesButtonInPendCRDocs() {
		flag = webDB.waitForElement(BankPaymentLocators.PendingPayablesButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.PendingPayablesButton, ElementType.Xpath);
			flag = webDB.waitForElement(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
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
	public void chooseDocumentNumberInPendingPayablesScreen(ArrayList<String> DocumentNumber, String Type,
			String PayMode, String BankName, String CreationType, String SheetName) {
		commonFunc.hardWait(500);
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
			for (int i = 1; i <= DocumentNumber.size(); i++) {
				commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.PendingPayablesDocNumberLovButton,
						DocumentNumber.get(i - 1));
				webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
							ElementType.Xpath);
					commonFunc.hardWait(1000);
					commonFunc.checkRefreshCellsDisappear();
					String GetTextDocumentNumber = webDB.getTextFromElement(
							BankPaymentLocators.PendingPayablesDocumentNumberField, ElementType.Xpath);
					if (GetTextDocumentNumber.equals(DocumentNumber.get(i - 1))) {
						commonFunc.hardWait(1000);
						verifyPendingPayablesPBDocumentThroughGetDoc(SheetName, DocumentNumber.get(i - 1), i);
						webDB.clickAnElement(
								BankPaymentLocators.BPPendingPartyDocumentSelectCheckBox(DocumentNumber.get(i - 1)),
								ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(1500);
						flag = webDB.waitForElement(BankPaymentLocators.PendingPayablesCreateButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "This Purchase Bills Document " + DocumentNumber.get(i - 1)
									+ " Is Selected Successfully");
							if (DocumentNumber.size() > 1) {
								webDB.clickAnElement(BankPaymentLocators.BankPendingPartyDocumentClearButton,
										ElementType.Xpath);
								flag = webDB.waitForElement(
										BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(
											BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
											ElementType.Xpath);
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
			webDB.moveToElement(BankPaymentLocators.PendingPayablesCreateButton, ElementType.Xpath);
			webDB.javaScriptClickAnElement(BankPaymentLocators.PendingPayablesCreateButton, ElementType.Xpath);
			logs.logging("INFO", "Create Button Is Clicked Successfully");
			commonFunc.checkRefreshCellsDisappear();
			if (!CreationType.equals("Single")) {
				flag = webDB.waitForElement(BankPaymentLocators.PendingPayablesMergeLabelInPending, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankPaymentLocators.PendingPayablesMergeLabelInPending, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
				}
			}
			flag = webDB.waitForElement(BankPaymentLocators.PendingPayablesTypeField, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownByText(BankPaymentLocators.PendingPayablesTypeDropDown, Type, ElementType.Xpath);
				logs.logging("INFO", "Type Successfully Choosen As " + Type);
				if (Type.equals("Bank")) {
					flag = webDB.waitForElement(BankPaymentLocators.PendingpayablesPaymodeField, ElementType.Xpath);
					if (flag) {
						webDB.selectDropDownByText(BankPaymentLocators.PendingPayablesPayModeDropDown, PayMode,
								ElementType.Xpath);
						logs.logging("INFO", "Pay Mode Successfully Choosen As " + PayMode);
					}
				}
				commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.PendingPayablesBankNameLOVBUtton,
						BankName);
				webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(BankPaymentLocators.PendingPayablesOkButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankPaymentLocators.PendingPayablesOkButton, ElementType.Xpath);
					webDB.switchToDefaultContent();
					if (Type.equals("Bank")) {
						flag = webDB.waitForElement(BankPaymentLocators.BankPaymentScreenName, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"OK Button Is Clicked Successfully And Navigated To Bank Payment Screen");
						}
					} else {
						flag = webDB.waitForElement(BankPaymentLocators.CashPaymentScreenName, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"OK Button Is Clicked Successfully And Navigated To Cash Payment Screen");
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
	public void chooseDocumentNumberInPendingPayablesScreenMPMB(ArrayList<String> DocumentNumber, String Type,
			String PayMode, String BankName, String CreationType, String SheetName) {
		flag = webDB.waitForElement(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
			for (int i = 1; i <= DocumentNumber.size(); i++) {
				commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.PendingPayablesDocNumberLovButton,
						DocumentNumber.get(i - 1));
				webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
							ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					String GetTextDocumentNumber = webDB.getTextFromElement(
							BankPaymentLocators.PendingPayablesDocumentNumberField, ElementType.Xpath);
					commonFunc.hardWait(1000);
					commonFunc.checkRefreshCellsDisappear();
					if (GetTextDocumentNumber.equals(DocumentNumber.get(i - 1))) {
						commonFunc.hardWait(1000);
						verifyPendingPayablesPBDocumentThroughAddButton(SheetName, DocumentNumber.get(i - 1), i);
						webDB.clickAnElement(
								BankPaymentLocators.BPPendingPartyDocumentSelectCheckBox(DocumentNumber.get(i - 1)),
								ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(1500);
						flag = webDB.waitForElement(BankPaymentLocators.PendingPayablesCreateButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "This Purchase Bills Document " + DocumentNumber.get(i - 1)
									+ " Is Selected Successfully");
							if (DocumentNumber.size() > 1) {
								webDB.clickAnElement(BankPaymentLocators.BankPendingPartyDocumentClearButton,
										ElementType.Xpath);
								flag = webDB.waitForElement(
										BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(
											BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
											ElementType.Xpath);
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
			webDB.moveToElement(BankPaymentLocators.PendingPayablesCreateButton, ElementType.Xpath);
			webDB.javaScriptClickAnElement(BankPaymentLocators.PendingPayablesCreateButton, ElementType.Xpath);
			logs.logging("INFO", "Create Button Is Clicked Successfully");
			commonFunc.checkRefreshCellsDisappear();
			if (!CreationType.equals("Single")) {
				flag = webDB.waitForElement(BankPaymentLocators.PendingPayablesMergeLabelInPending, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankPaymentLocators.PendingPayablesMergeLabelInPending, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
				}
			}
			flag = webDB.waitForElement(BankPaymentLocators.PendingPayablesTypeField, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownByText(BankPaymentLocators.PendingPayablesTypeDropDown, Type, ElementType.Xpath);
				logs.logging("INFO", "Type Successfully Choosen As " + Type);
				if (Type.equals("Bank")) {
					flag = webDB.waitForElement(BankPaymentLocators.PendingpayablesPaymodeField, ElementType.Xpath);
					if (flag) {
						webDB.selectDropDownByText(BankPaymentLocators.PendingPayablesPayModeDropDown, PayMode,
								ElementType.Xpath);
						logs.logging("INFO", "Pay Mode Successfully Choosen As " + PayMode);
					}
				}
				commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.PendingPayablesBankNameLOVBUtton,
						BankName);
				webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(BankPaymentLocators.PendingPayablesOkButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankPaymentLocators.PendingPayablesOkButton, ElementType.Xpath);
					webDB.switchToDefaultContent();
					if (Type.equals("Bank")) {
						flag = webDB.waitForElement(BankPaymentLocators.BankPaymentScreenName, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"OK Button Is Clicked Successfully And Navigated To Bank Payment Screen");
						}
					} else {
						flag = webDB.waitForElement(BankPaymentLocators.CashPaymentScreenName, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"OK Button Is Clicked Successfully And Navigated To Cash Payment Screen");
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
	public void sendReferenceNumberInBP(String ReferenceNumber) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(BankPaymentLocators.BankPaymentReferenceInputField, ElementType.Xpath);
		if (flag) {
			webDB.clearTextField(BankPaymentLocators.BankPaymentReferenceInputField, ElementType.Xpath);
			webDB.sendTextToAnElement(BankPaymentLocators.BankPaymentReferenceInputField, ReferenceNumber,
					ElementType.Xpath);
			webDB.pressTabKey();
//			commonFunc.hardWait(500);
			flag = webDB.waitForElement(BankPaymentLocators.BankHeaderWiseSaveButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Given Ref Number " + ReferenceNumber + " Is Inserted Successfully");
//				commonFunc.checkRefreshCellsDisappear();
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
	public void clickBPHeaderWiseSaveButton() {
		flag = webDB.waitForElement(BankPaymentLocators.BankHeaderWiseSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BankHeaderWiseSaveButton, ElementType.Xpath);
			flag = webDB.waitForElement(BankPaymentLocators.BankHeaderCreateJournalButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Bank Payment Header Wise Save Button Is Clicked Successfully");
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughPendingDocsSingleBills(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
						BPGrnExcelFileName);
			}
		}
		for (int i = 1; i <= VariableHandler.getMultipleGRNDocs().size(); i++) {
//			createPurchaseBillsFromPendingGRN(VariableHandler.getMultipleGRNDocs().get(i - 1));
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(0),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number is : " + VariableHandler.getPbNumber());
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 25 + i,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i - 1),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BPThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"), commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used to Create BP Thriugh Pending Docs
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughPendingDocsMultiBills(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i <= 2; i++) {
			String SupplierID = BankPayments[1][2];
			String GRNSheetName = BankPayments[1][4];
			Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
					BPGrnExcelFileName);
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BPThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"), commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used To Add The Bank Payment Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPThroughAddButton(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		getBankPaymentOrReceiptDocumentNumber();
		checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyBankPaymentHeaderDetailsWithUI(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
		verifyBankPaymentDetailsWithDB(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String PartyName = BankPayments[i][3];
			String EntityName = BankPayments[i][5];
			String CPCCodeDesc = BankPayments[i][6];
			String BillNumber = BankPayments[i][7];
			String PayAmount = BankPayments[i][8];
			String DRCR = BankPayments[i][9];
			String CURCY = BankPayments[i][10];
			String EXRate = BankPayments[i][11];
			String TransCURCY = BankPayments[i][12];
			String BaseCURCY = BankPayments[i][13];
			String AssesableValue = BankPayments[i][16];
			if (!GLAccountDesc.equals("")) {
				clickBPLineAddButton();
				addBankPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
				clickLineWiseSaveButton();
				clickLineWiseAddButton(i);
				clickPendingPartyDocumentsInBP();
				selectPartyDocumentBasedOnPB(PartyName, VariableHandler.getMultiplePBDocs(), SheetName);
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
				}
				verifyBankPaymentsLineDetailsWithDB(SheetName, i);
				if (Currency.equals("INR")) {
					if (BankName.equals("JP MORGAN CHASE")) {
						for (int k = 1; k <= VariableHandler.getMultiplePBDocs().size(); k++) {
							excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalINR"), 1,
									5 + k,
									dbCommonFunctions.BankPaymentPBHeaderDetails(
											VariableHandler.getMultiplePBDocs().get(k - 1),
											commonFunc.getDataFromTestDataSheet("PBNetAmount")));
							excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalINR"), 2,
									5 + k,
									dbCommonFunctions.BankPaymentPBHeaderDetails(
											VariableHandler.getMultiplePBDocs().get(k - 1),
											commonFunc.getDataFromTestDataSheet("PBNetAmount")));
						}
					} else {
						for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
							excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournal"), 1, 5 + j,
									dbCommonFunctions.BankPaymentPBHeaderDetails(
											VariableHandler.getMultiplePBDocs().get(j - 1),
											commonFunc.getDataFromTestDataSheet("PBNetAmount")));
							excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournal"), 2, 5 + j,
									dbCommonFunctions.BankPaymentPBHeaderDetails(
											VariableHandler.getMultiplePBDocs().get(j - 1),
											commonFunc.getDataFromTestDataSheet("PBNetAmount")));
						}
					}
				} else {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalUSD"), 0, 10,
							dbCommonFunctions.ExchangeRateDetails(Currency,
									commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
					for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalUSD"), 1, 5 + j,
								dbCommonFunctions.BankPaymentPBHeaderDetails(
										VariableHandler.getMultiplePBDocs().get(j - 1),
										commonFunc.getDataFromTestDataSheet("PBNetAmount")));
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalUSD"), 2, 5 + j,
								dbCommonFunctions.BankPaymentPBHeaderDetails(
										VariableHandler.getMultiplePBDocs().get(j - 1),
										commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					}
				}
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBPHeader(BaseCURCY);
			}
		}
		clickCreateJournalButton();
		if (Currency.equals("INR")) {
			if (BankName.equals("JP MORGAN CHASE")) {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalINR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalINR"), 1, 5 + j, "0");
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalINR"), 2, 5 + j, "0");
				}
			} else {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournal"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournal"), 1, 5 + j, "0");
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournal"), 2, 5 + j, "0");
				}
			}
		} else {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalUSD"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
			for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalUSD"), 1, 5 + j, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalUSD"), 2, 5 + j, "0");
			}
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used to Create BP Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param Currency
	 * @param BankName
	 */
	public void BPThroughGetDocument(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		getBankPaymentOrReceiptDocumentNumber();
		checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyBankPaymentHeaderDetailsWithUI(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
		verifyBankPaymentDetailsWithDB(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		clickGetDocumentInBPHeader();
		clickPendingPartyDocumentsInBP();
		choosePartyNameAndClickSearchButtonInPendingPartyDocs(VariableHandler.getMultiplePBDocs(), SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String BaseCURCY = BankPayments[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
				}
				verifyBankPaymentsLineDetailsWithDB(SheetName, i);
				if (Currency.equals("INR")) {
					if (BankName.equals("JP MORGAN CHASE")) {
						for (int k = 1; k <= VariableHandler.getMultiplePBDocs().size(); k++) {
							excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalINR"), 1,
									5 + k,
									dbCommonFunctions.BankPaymentPBHeaderDetails(
											VariableHandler.getMultiplePBDocs().get(k - 1),
											commonFunc.getDataFromTestDataSheet("PBNetAmount")));
							excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalINR"), 2,
									5 + k,
									dbCommonFunctions.BankPaymentPBHeaderDetails(
											VariableHandler.getMultiplePBDocs().get(k - 1),
											commonFunc.getDataFromTestDataSheet("PBNetAmount")));
						}
					} else {
						for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
							excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournal"), 1, 5 + j,
									dbCommonFunctions.BankPaymentPBHeaderDetails(
											VariableHandler.getMultiplePBDocs().get(j - 1),
											commonFunc.getDataFromTestDataSheet("PBNetAmount")));
							excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournal"), 2, 5 + j,
									dbCommonFunctions.BankPaymentPBHeaderDetails(
											VariableHandler.getMultiplePBDocs().get(j - 1),
											commonFunc.getDataFromTestDataSheet("PBNetAmount")));
						}
					}
				} else {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalUSD"), 0, 10,
							dbCommonFunctions.ExchangeRateDetails(Currency,
									commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
					for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalUSD"), 1, 5 + j,
								dbCommonFunctions.BankPaymentPBHeaderDetails(
										VariableHandler.getMultiplePBDocs().get(j - 1),
										commonFunc.getDataFromTestDataSheet("PBNetAmount")));
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalUSD"), 2, 5 + j,
								dbCommonFunctions.BankPaymentPBHeaderDetails(
										VariableHandler.getMultiplePBDocs().get(j - 1),
										commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					}
				}
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBPHeader(BaseCURCY);
			}
		}
		clickCreateJournalButton();
		if (Currency.equals("INR")) {
			if (BankName.equals("JP MORGAN CHASE")) {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalINR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalINR"), 1, 5 + j, "0");
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalINR"), 2, 5 + j, "0");
				}
			} else {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournal"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournal"), 1, 5 + j, "0");
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournal"), 2, 5 + j, "0");
				}
			}
		} else {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalUSD"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
			for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalUSD"), 1, 5 + j, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalUSD"), 2, 5 + j, "0");
			}
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used to Create BP Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param Currency
	 * @param BankName
	 */
	public void BPThroughGetDocumentMPSI(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		getBankPaymentOrReceiptDocumentNumber();
		checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyBankPaymentHeaderDetailsWithUI(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
		verifyBankPaymentDetailsWithDB(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		clickGetDocumentInBPHeader();
		clickPendingPartyDocumentsInBP();
		choosePartyNameAndClickSearchButtonInPendingPartyDocs(VariableHandler.getMultiplePBDocs(), SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String BaseCURCY = BankPayments[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
				}
				verifyBankPaymentsLineDetailsWithDB(SheetName, i);
				if (Currency.equals("INR")) {
					if (BankName.equals("JP MORGAN CHASE")) {
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiINR"), 3,
								5 + i,
								dbCommonFunctions.BankPaymentPBHeaderDetails(
										VariableHandler.getMultiplePBDocs().get(i - 1),
										commonFunc.getDataFromTestDataSheet("PBNetAmount")));
						for (int j = 0; j < VariableHandler.getMultiplePBDocs().size(); j++) {
							excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiINR"),
									j + 1, 6,
									dbCommonFunctions.BankPaymentPBHeaderDetails(
											VariableHandler.getMultiplePBDocs().get(j),
											commonFunc.getDataFromTestDataSheet("PBNetAmount")));
						}
					} else {
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMulti"), 3, 5 + i,
								dbCommonFunctions.BankPaymentPBHeaderDetails(
										VariableHandler.getMultiplePBDocs().get(i - 1),
										commonFunc.getDataFromTestDataSheet("PBNetAmount")));
						for (int j = 0; j < VariableHandler.getMultiplePBDocs().size(); j++) {
							excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMulti"),
									j + 1, 6,
									dbCommonFunctions.BankPaymentPBHeaderDetails(
											VariableHandler.getMultiplePBDocs().get(j),
											commonFunc.getDataFromTestDataSheet("PBNetAmount")));
						}
					}
				} else {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiUSD"), 0, 10,
							dbCommonFunctions.ExchangeRateDetails(Currency,
									commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiUSD"), 3, 5 + i,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i - 1),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					for (int j = 0; j < VariableHandler.getMultiplePBDocs().size(); j++) {
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiUSD"), j + 1,
								6,
								dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j),
										commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					}
				}
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBPHeader(BaseCURCY);
			}
		}
		clickCreateJournalButton();
		if (Currency.equals("INR")) {
			if (BankName.equals("JP MORGAN CHASE")) {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalMultiINR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				for (int j = 0; j < VariableHandler.getMultiplePBDocs().size(); j++) {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiINR"), 3, 6 + j,
							"0");
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiINR"), j + 1, 6,
							"0");
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiINR"), j + 1, 7,
							"0");
				}
			} else {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalMulti"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				for (int j = 0; j < VariableHandler.getMultiplePBDocs().size(); j++) {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMulti"), 3, 6 + j,
							"0");
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMulti"), j + 1, 6,
							"0");
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMulti"), j + 1, 7,
							"0");
				}
			}
		} else {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalMultiUSD"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
			for (int j = 0; j < VariableHandler.getMultiplePBDocs().size(); j++) {
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiUSD"), 3, 6 + j,
						"0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiUSD"), j + 1, 6,
						"0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiUSD"), j + 1, 7,
						"0");
			}
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
		}
		assertTrue(flag);
	}

	/**
	 * This Method is used to Create BP Through Pending DOcs
	 * 
	 * @author Harikrishna - RMITS
	 * @param CreationType
	 * @param SheetName
	 * @param Currency
	 * @param BankName
	 */
	public void BPThroughPendingDocs(String CreationType, String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingCRDocsButton();
		clickPendingPayablesButtonInPendCRDocs();
		chooseDocumentNumberInPendingPayablesScreen(VariableHandler.getMultiplePBDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentType"), commonFunc.getDataFromTestDataSheet("BankPayMode"),
				BankName, CreationType, SheetName);
		getBankPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		sendReferenceNumberInBP(commonFunc.getDataFromTestDataSheet("Reference Number"));
		clickBPHeaderWiseSaveButton();
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String BaseCURCY = BankPayments[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
				}
				verifyBankPaymentsLineDetailsWithDB(SheetName, i);
				if (Currency.equals("INR")) {
					if (BankName.equals("JP MORGAN CHASE")) {
						for (int k = 1; k <= VariableHandler.getMultiplePBDocs().size(); k++) {
							excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalINR"), 1,
									5 + k,
									dbCommonFunctions.BankPaymentPBHeaderDetails(
											VariableHandler.getMultiplePBDocs().get(k - 1),
											commonFunc.getDataFromTestDataSheet("PBNetAmount")));
							excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalINR"), 2,
									5 + k,
									dbCommonFunctions.BankPaymentPBHeaderDetails(
											VariableHandler.getMultiplePBDocs().get(k - 1),
											commonFunc.getDataFromTestDataSheet("PBNetAmount")));
						}
					} else {
						for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
							excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournal"), 1, 5 + j,
									dbCommonFunctions.BankPaymentPBHeaderDetails(
											VariableHandler.getMultiplePBDocs().get(j - 1),
											commonFunc.getDataFromTestDataSheet("PBNetAmount")));
							excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournal"), 2, 5 + j,
									dbCommonFunctions.BankPaymentPBHeaderDetails(
											VariableHandler.getMultiplePBDocs().get(j - 1),
											commonFunc.getDataFromTestDataSheet("PBNetAmount")));
						}
					}
				} else {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalUSD"), 0, 10,
							dbCommonFunctions.ExchangeRateDetails(Currency,
									commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
					for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalUSD"), 1, 5 + j,
								dbCommonFunctions.BankPaymentPBHeaderDetails(
										VariableHandler.getMultiplePBDocs().get(j - 1),
										commonFunc.getDataFromTestDataSheet("PBNetAmount")));
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalUSD"), 2, 5 + j,
								dbCommonFunctions.BankPaymentPBHeaderDetails(
										VariableHandler.getMultiplePBDocs().get(j - 1),
										commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					}
				}
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBPHeader(BaseCURCY);
			}
		}
		clickCreateJournalButton();
		if (Currency.equals("INR")) {
			if (BankName.equals("JP MORGAN CHASE")) {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalINR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalINR"), 1, 5 + j, "0");
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalINR"), 2, 5 + j, "0");
				}
			} else {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournal"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournal"), 1, 5 + j, "0");
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournal"), 2, 5 + j, "0");
				}
			}
		} else {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalUSD"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
			for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalUSD"), 1, 5 + j, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalUSD"), 2, 5 + j, "0");
			}
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
		}
	}

	/**
	 * This Method is used to Create BP Through Pending DOcs
	 * 
	 * @author Harikrishna - RMITS
	 * @param CreationType
	 * @param SheetName
	 * @param Currency
	 * @param BankName
	 */
	public void BPThroughPendingDocsMPSI(String CreationType, String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingCRDocsButton();
		clickPendingPayablesButtonInPendCRDocs();
		chooseDocumentNumberInPendingPayablesScreen(VariableHandler.getMultiplePBDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentType"), commonFunc.getDataFromTestDataSheet("BankPayMode"),
				BankName, CreationType, SheetName);
		getBankPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		sendReferenceNumberInBP(commonFunc.getDataFromTestDataSheet("Reference Number"));
		clickBPHeaderWiseSaveButton();
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String BaseCURCY = BankPayments[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
				}
				verifyBankPaymentsLineDetailsWithDB(SheetName, i);
				if (Currency.equals("INR")) {
					if (BankName.equals("JP MORGAN CHASE")) {
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiINR"), 3,
								5 + i,
								dbCommonFunctions.BankPaymentPBHeaderDetails(
										VariableHandler.getMultiplePBDocs().get(i - 1),
										commonFunc.getDataFromTestDataSheet("PBNetAmount")));
						for (int j = 0; j < VariableHandler.getMultiplePBDocs().size(); j++) {
							excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiINR"),
									j + 1, 6,
									dbCommonFunctions.BankPaymentPBHeaderDetails(
											VariableHandler.getMultiplePBDocs().get(j),
											commonFunc.getDataFromTestDataSheet("PBNetAmount")));
						}
					} else {
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMulti"), 3, 5 + i,
								dbCommonFunctions.BankPaymentPBHeaderDetails(
										VariableHandler.getMultiplePBDocs().get(i - 1),
										commonFunc.getDataFromTestDataSheet("PBNetAmount")));
						for (int j = 0; j < VariableHandler.getMultiplePBDocs().size(); j++) {
							excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMulti"),
									j + 1, 6,
									dbCommonFunctions.BankPaymentPBHeaderDetails(
											VariableHandler.getMultiplePBDocs().get(j),
											commonFunc.getDataFromTestDataSheet("PBNetAmount")));
						}
					}
				} else {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiUSD"), 0, 10,
							dbCommonFunctions.ExchangeRateDetails(Currency,
									commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiUSD"), 3, 5 + i,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i - 1),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					for (int j = 0; j < VariableHandler.getMultiplePBDocs().size(); j++) {
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiUSD"), j + 1,
								6,
								dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j),
										commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					}
				}
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBPHeader(BaseCURCY);
			}
		}
		clickCreateJournalButton();
		if (Currency.equals("INR")) {
			if (BankName.equals("JP MORGAN CHASE")) {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalMultiINR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				for (int j = 0; j < VariableHandler.getMultiplePBDocs().size(); j++) {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiINR"), 3, 6 + j,
							"0");
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiINR"), j + 1, 6,
							"0");
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiINR"), j + 1, 7,
							"0");
				}
			} else {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalMulti"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				for (int j = 0; j < VariableHandler.getMultiplePBDocs().size(); j++) {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMulti"), 3, 6 + j,
							"0");
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMulti"), j + 1, 6,
							"0");
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMulti"), j + 1, 7,
							"0");
				}
			}
		} else {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalMultiUSD"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
			for (int j = 0; j < VariableHandler.getMultiplePBDocs().size(); j++) {
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiUSD"), 3, 6 + j,
						"0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiUSD"), j + 1, 6,
						"0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiUSD"), j + 1, 7,
						"0");
			}
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
		}
	}

	/**
	 * This Method Is Used To Create BP Through get Document MPMB
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param Currency
	 * @param BankName
	 */
	public void BPThroughGetDocumentMPMB(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		getBankPaymentOrReceiptDocumentNumber();
		checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyBankPaymentHeaderDetailsWithUI(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
		verifyBankPaymentDetailsWithDB(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		clickGetDocumentInBPHeader();
		clickPendingPartyDocumentsInBP();
		choosePartyNameAndClickSearchButtonInPendingPartyDocsMPMB(VariableHandler.getMultiplePBDocs(), SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String BaseCURCY = BankPayments[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
				}
				verifyBankPaymentsLineDetailsWithDB(SheetName, i);
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBPHeader(BaseCURCY);
			}
			if (Currency.equals("INR")) {
				if (BankName.equals("JP MORGAN CHASE")) {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIINR"), 1, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIINR"), 1, 7,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIINR"), 2, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIINR"), 2, 7,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				} else {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMI"), 1, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMI"), 1, 7,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMI"), 2, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMI"), 2, 7,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				}
			} else {
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIUSD"), 1, 6,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIUSD"), 1, 7,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIUSD"), 2, 6,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIUSD"), 2, 7,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		clickCreateJournalButton();
		if (Currency.equals("INR")) {
			if (BankName.equals("JP MORGAN CHASE")) {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalMPMIINR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIINR"), 1, 6, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIINR"), 1, 7, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIINR"), 2, 6, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIINR"), 2, 7, "0");
			} else {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalMPMI"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMI"), 1, 6, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMI"), 1, 7, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMI"), 2, 6, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMI"), 2, 7, "0");
			}
		} else {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalMPMIUSD"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIUSD"), 1, 6, "0");
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIUSD"), 1, 7, "0");
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIUSD"), 2, 6, "0");
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIUSD"), 2, 7, "0");
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Create Document Through Pending MPMB
	 * 
	 * @author Harikrishna - RMITS
	 * @param CreationType
	 * @param SheetName
	 * @param Currency
	 * @param BankName
	 */
	public void BPThroughPendingDocsMPMB(String CreationType, String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingCRDocsButton();
		clickPendingPayablesButtonInPendCRDocs();
		chooseDocumentNumberInPendingPayablesScreenMPMB(VariableHandler.getMultiplePBDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentType"), commonFunc.getDataFromTestDataSheet("BankPayMode"),
				BankName, CreationType, SheetName);
		getBankPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		sendReferenceNumberInBP(commonFunc.getDataFromTestDataSheet("Reference Number"));
		clickBPHeaderWiseSaveButton();
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String BaseCURCY = BankPayments[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
				}
				verifyBankPaymentsLineDetailsWithDB(SheetName, i);
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBPHeader(BaseCURCY);
			}
			if (Currency.equals("INR")) {
				if (BankName.equals("JP MORGAN CHASE")) {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIINR"), 1, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIINR"), 1, 7,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIINR"), 2, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIINR"), 2, 7,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				} else {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMI"), 1, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMI"), 1, 7,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMI"), 2, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMI"), 2, 7,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				}
			} else {
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIUSD"), 1, 6,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIUSD"), 1, 7,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIUSD"), 2, 6,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIUSD"), 2, 7,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		clickCreateJournalButton();
		if (Currency.equals("INR")) {
			if (BankName.equals("JP MORGAN CHASE")) {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalMPMIINR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIINR"), 1, 6, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIINR"), 1, 7, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIINR"), 2, 6, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIINR"), 2, 7, "0");
			} else {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalMPMI"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMI"), 1, 6, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMI"), 1, 7, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMI"), 2, 6, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMI"), 2, 7, "0");
			}
		} else {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalMPMIUSD"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIUSD"), 1, 6, "0");
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIUSD"), 1, 7, "0");
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIUSD"), 2, 6, "0");
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIUSD"), 2, 7, "0");
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
		}
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughLineAddButtonMultiBills(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i <= 2; i++) {
			String SupplierID = BankPayments[1][2];
			String GRNSheetName = BankPayments[1][4];
			Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
					BPGrnExcelFileName);
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BPThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Deepan-GWL
	 * @param DocNo
	 */
	public void verifyPendingPurchaseBill(String DocNo) {
		flag = webDB.waitForElement(BankPaymentLocators.PendingPurchaseBill, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Purchase Bill Is Displayed");
			webDB.clickAnElement(BankPaymentLocators.PendingPurchaseBill, ElementType.Xpath);
			flag = webDB.waitForElement(BankPaymentLocators.PendingGrn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Grn Is Displayed");
				webDB.clickAnElement(BankPaymentLocators.PendingGrn, ElementType.Xpath);
				flag = webDB.waitForElement(BankPaymentLocators.Iframe, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Iframe Is Displayed");
					webDB.switchToFrame(BankPaymentLocators.Iframe, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.GrnNo, DocNo);
					flag = webDB.waitForElement(BankPaymentLocators.Iframe, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(BankPaymentLocators.Iframe, ElementType.Xpath);
						flag = webDB.waitForElement(BankPaymentLocators.SearchButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Switched To Iframe");
							webDB.clickAnElement(BankPaymentLocators.SearchButton, ElementType.Xpath);
							flag = webDB.waitForElement(BankPaymentLocators.CheckBox(DocNo), ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "CheckBox IS Displayed");
								commonFunc.hardWait(1000);
								webDB.clickAnElement(BankPaymentLocators.CheckBox(DocNo), ElementType.Xpath);
								flag = webDB.waitForElement(BankPaymentLocators.CreateDoc, ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Create Documents Is Displayed");
									commonFunc.hardWait(2000);
									webDB.clickAnElement(BankPaymentLocators.CreateDoc, ElementType.Xpath);
									flag = webDB.waitForElement(BankPaymentLocators.OkButton, ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Okay Button Is Displayed");
										webDB.clickAnElement(BankPaymentLocators.OkButton, ElementType.Xpath);
										webDB.switchToDefaultContent();
										flag = webDB.waitForElement(BankPaymentLocators.LocationId, ElementType.Xpath);
										storePurchaseVoucherNumber();
//										verifyAddTDSNumber();
										if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon,
												ElementType.Xpath)) {
											webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
													ElementType.Xpath);
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
	 * This Method Used To Verify Store Purchase Voucher Number
	 * 
	 * @author Deepan-GWL
	 */
	public void storePurchaseVoucherNumber() {
		String Number = webDB.getTextFromElement(BankPaymentLocators.VoucherNumber, ElementType.Xpath);
		VariableHandler.setPbNumber(Number);
		PBDocumentNumber.add(VariableHandler.getPbNumber());
		logs.logging("INFO", "Get Text Purchase Bill Document Number is : " + VariableHandler.getPbNumber());

	}

	/**
	 * verify Add TDS Number
	 * 
	 * @author Deepan
	 */
	public void verifyAddTDSNumber() {
		if (webDB.verifyIsElementDisplayed(PurchaseBillsLocators.ExpandButton, ElementType.Xpath)) {
			webDB.clickAnElement(PurchaseBillsLocators.ExpandButton, ElementType.Xpath);
		}
		webDB.moveToElement(PurchaseBillsLocators.TableData, ElementType.Xpath);
		webDB.moveToElement(PurchaseBillsLocators.TDSU_S, ElementType.Xpath);
		flag = webDB.waitForElement(PurchaseBillsLocators.TDSU_S, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocators.TDSU_S, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocators.TDSLovButton, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.TDSLovButton,
						commonFunc.getDataFromTestDataSheet("TDSValue"));
			}
		}
	}

	/**
	 * This Method Used To Verify To Create Journal And Post
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateJournalAndPost() {
		flag = webDB.waitForElement(BankPaymentLocators.CreateJournal, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(BankPaymentLocators.CreateJournal, ElementType.Xpath);
			logs.logging("INFO", "Create Journal Button Is Displayed");
			webDB.clickAnElement(BankPaymentLocators.CreateJournal, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(1500);
			flag = webDB.waitForElement(BankPaymentLocators.PostButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Post Button Is Displayed");
				webDB.clickAnElement(BankPaymentLocators.PostButton, ElementType.Xpath);
				flag = (webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 10)
						|| webDB.waitForElement(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath));
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS This Method Is Used to Forward the Doucmnet from
	 *         One User To Another User
	 * @return boolean flag
	 */
	public boolean forwardForApproval(String ForwardToEmployee) {
		commonFunc.hardWait(500);
		if (webDB.verifyIsElementDisplayed(SalesOrderLocators.WorkApprovalFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(SalesOrderLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			String ForwardEmployee = webDB.getDomPropertyFromElement(BankPaymentLocators.FrwdPersonField,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			if (!ForwardEmployee.equalsIgnoreCase(ForwardToEmployee)) {
				commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.ForwardApprovalLov, ForwardToEmployee);
				commonFunc.hardWait(1000);
				webDB.switchToFrame(SalesOrderLocators.WorkApprovalFrame, ElementType.Xpath);
				logs.logging("INFO", "Switched To WorkFlow Frame");
			}
			flag = webDB.waitForElement(CommonUiLocators.WorkAppoval_OkBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CommonUiLocators.WorkAppoval_OkBtn, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
			}
			webDB.switchToDefaultContent();
			assertTrue(flag);
		} else {
			flag = false;
			VariableHandler.setApprovalFlag(flag);
		}
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Add Button Multi Party
	 * Single Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughLineAddButtonMultiPartySingleBill(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
						BPGrnExcelFileName);
			}
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, i + 1, 25 + (i + 1),
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BPThroughAddButtonMultiPartySingleBills(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used to Select the Bank Payment pending Records In the Pending
	 * Party Documnets IN the bank Payment Screen
	 * 
	 * @author Harikrishna _ RMITS
	 * @param PartyName
	 * @param VoucherNumber.get(i)
	 */
	public void selectPartyDocumentBasedOnMultiPartySinglePB(String PartyName, String VoucherNumber, String SheetName,
			int i) {
		flag = webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
		if (flag) {
			String GetTextPartyName = webDB.getDomPropertyFromElement(
					BankPaymentLocators.BankPendingPartyDocumentsPartyNameInputField, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			if (GetTextPartyName.equals(PartyName)) {
//				for (int i = 0; i < VoucherNumber.size(); i++) {
				commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.BankPartyDocNumberLovButton,
						VoucherNumber);
				webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
						ElementType.Xpath);
				if (flag) {
					commonFunc.checkRefreshCellsDisappear();
					webDB.doubleClickAnElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
							ElementType.Xpath);
					flag = webDB.waitForElement(BankPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber),
							ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						verifyPendingPayablesPBDocumentThroughGetDoc(SheetName, VoucherNumber, i); // TODO
						webDB.clickAnElement(BankPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber),
								ElementType.Xpath);
						commonFunc.hardWait(5000);
						flag = webDB.waitForElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "In Pending Party Documents This Voucher Number " + VoucherNumber
									+ " Is Selected Successfully");
						}
					}
				}
//				}
				webDB.moveToElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton, ElementType.Xpath);
				webDB.javaScriptClickAnElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton,
						ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(BankPaymentLocators.BankHeaderCreateJournalButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "OK Button Is Clicked Successfully In The Pending Party Documents");
				}
			} else {
				logs.logging("INFO",
						"Bank Payment Line Wise Party Name And Pending Party Documents Party Name Is not Getting Matched");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The Bank Payment Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPThroughAddButtonMultiPartySingleBills(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		getBankPaymentOrReceiptDocumentNumber();
		checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyBankPaymentHeaderDetailsWithUI(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
		verifyBankPaymentDetailsWithDB(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String PartyName = BankPayments[i][3];
			String EntityName = BankPayments[i][5];
			String CPCCodeDesc = BankPayments[i][6];
			String BillNumber = BankPayments[i][7];
			String PayAmount = BankPayments[i][8];
			String DRCR = BankPayments[i][9];
			String CURCY = BankPayments[i][10];
			String EXRate = BankPayments[i][11];
			String TransCURCY = BankPayments[i][12];
			String BaseCURCY = BankPayments[i][13];
			String AssesableValue = BankPayments[i][16];
			if (!GLAccountDesc.equals("")) {
				clickBPLineAddButton();
				addBankPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
				clickLineWiseSaveButton();
				clickLineWiseAddButton(i);
				clickPendingPartyDocumentsInBP();
				selectPartyDocumentBasedOnMultiPartySinglePB(PartyName, VariableHandler.getMultiplePBDocs().get(i - 1),
						SheetName, i);
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
				}
				verifyBankPaymentsLineDetailsWithDB(SheetName, i);
				if (Currency.equals("INR")) {
					if (BankName.equals("JP MORGAN CHASE")) {
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiINR"), 3,
								5 + i,
								dbCommonFunctions.BankPaymentPBHeaderDetails(
										VariableHandler.getMultiplePBDocs().get(i - 1),
										commonFunc.getDataFromTestDataSheet("PBNetAmount")));
						for (int j = 0; j < VariableHandler.getMultiplePBDocs().size(); j++) {
							excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiINR"),
									j + 1, 6,
									dbCommonFunctions.BankPaymentPBHeaderDetails(
											VariableHandler.getMultiplePBDocs().get(j),
											commonFunc.getDataFromTestDataSheet("PBNetAmount")));
						}
					} else {
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMulti"), 3, 5 + i,
								dbCommonFunctions.BankPaymentPBHeaderDetails(
										VariableHandler.getMultiplePBDocs().get(i - 1),
										commonFunc.getDataFromTestDataSheet("PBNetAmount")));
						for (int j = 0; j < VariableHandler.getMultiplePBDocs().size(); j++) {
							excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMulti"),
									j + 1, 6,
									dbCommonFunctions.BankPaymentPBHeaderDetails(
											VariableHandler.getMultiplePBDocs().get(j),
											commonFunc.getDataFromTestDataSheet("PBNetAmount")));
						}
					}
				} else {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiUSD"), 0, 10,
							dbCommonFunctions.ExchangeRateDetails(Currency,
									commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiUSD"), 3, 5 + i,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i - 1),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					for (int j = 0; j < VariableHandler.getMultiplePBDocs().size(); j++) {
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiUSD"), j + 1,
								6,
								dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j),
										commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					}
				}
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBPHeader(BaseCURCY);
			}
		}
		clickCreateJournalButton();
		if (Currency.equals("INR")) {
			if (BankName.equals("JP MORGAN CHASE")) {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalMultiINR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				for (int j = 0; j < VariableHandler.getMultiplePBDocs().size(); j++) {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiINR"), 3, 6 + j,
							"0");
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiINR"), j + 1, 6,
							"0");
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiINR"), j + 1, 7,
							"0");
				}
			} else {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalMulti"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				for (int j = 0; j < VariableHandler.getMultiplePBDocs().size(); j++) {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMulti"), 3, 6 + j,
							"0");
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMulti"), j + 1, 6,
							"0");
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMulti"), j + 1, 7,
							"0");
				}
			}
		} else {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalMultiUSD"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
			for (int j = 0; j < VariableHandler.getMultiplePBDocs().size(); j++) {
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiUSD"), 3, 6 + j,
						"0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiUSD"), j + 1, 6,
						"0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMultiUSD"), j + 1, 7,
						"0");
			}
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Multi Party
	 * Single Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughGetDocumentMultiPartySingleBill(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
						BPGrnExcelFileName);
			}
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, i + 1, 25 + (i + 1),
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BPThroughGetDocumentMPSI(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Multi Party
	 * Single Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughPendingDocumentMultiPartySingleBill(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
						BPGrnExcelFileName);
			}
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, i + 1, 25 + (i + 1),
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BPThroughPendingDocsMPSI(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"), commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used To Add The Bank Payment Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPThroughAddButtonMultiPartyMultiBills(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		getBankPaymentOrReceiptDocumentNumber();
		checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyBankPaymentHeaderDetailsWithUI(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
		verifyBankPaymentDetailsWithDB(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String PartyName = BankPayments[i][3];
			String EntityName = BankPayments[i][5];
			String CPCCodeDesc = BankPayments[i][6];
			String BillNumber = BankPayments[i][7];
			String PayAmount = BankPayments[i][8];
			String DRCR = BankPayments[i][9];
			String CURCY = BankPayments[i][10];
			String EXRate = BankPayments[i][11];
			String TransCURCY = BankPayments[i][12];
			String BaseCURCY = BankPayments[i][13];
			String AssesableValue = BankPayments[i][16];
			if (!GLAccountDesc.equals("")) {
				clickBPLineAddButton();
				addBankPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
				clickLineWiseSaveButton();
				clickLineWiseAddButton(i);
				clickPendingPartyDocumentsInBP();
				selectPartyDocumentBasedOnPBThroughAdd(PartyName, VariableHandler.getMultiplePBDocs(), i, SheetName);
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
				}
				verifyBankPaymentsLineDetailsWithDB(SheetName, i);
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBPHeader(BaseCURCY);
			}
			if (Currency.equals("INR")) {
				if (BankName.equals("JP MORGAN CHASE")) {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIINR"), 1, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIINR"), 1, 7,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIINR"), 2, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIINR"), 2, 7,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				} else {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMI"), 1, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMI"), 1, 7,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMI"), 2, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMI"), 2, 7,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				}
			} else {
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIUSD"), 1, 6,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIUSD"), 1, 7,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIUSD"), 2, 6,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIUSD"), 2, 7,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		clickCreateJournalButton();
		if (Currency.equals("INR")) {
			if (BankName.equals("JP MORGAN CHASE")) {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalMPMIINR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIINR"), 1, 6, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIINR"), 1, 7, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIINR"), 2, 6, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIINR"), 2, 7, "0");
			} else {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalMPMI"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMI"), 1, 6, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMI"), 1, 7, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMI"), 2, 6, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMI"), 2, 7, "0");
			}
		} else {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalMPMIUSD"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIUSD"), 1, 6, "0");
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIUSD"), 1, 7, "0");
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIUSD"), 2, 6, "0");
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalMPMIUSD"), 2, 7, "0");
		}
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used to Select the Bank Payment pending Records In the Pending
	 * Party Documnets IN the bank Payment Screen
	 * 
	 * @author Harikrishna _ RMITS
	 * @param PartyName
	 * @param VoucherNumber.get(i)
	 */
	public void selectPartyDocumentBasedOnPBSingle(int i, ArrayList<String> VoucherNumber) {
		flag = webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
//		if (flag) {
//			String GetTextPartyName = webDB.getDomPropertyFromElement(
//					BankPaymentLocators.BankPendingPartyDocumentsPartyNameInputField, ElementType.Xpath,
//					commonFunc.getDataFromTestDataSheet("Value"));
//			if (GetTextPartyName.equals(PartyName)) {
//				for (int i = 0; i < VoucherNumber.size(); i++) {
		commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.BankPartyDocNumberLovButton, VoucherNumber.get(i));
		webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
		flag = webDB.waitForElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton, ElementType.Xpath);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			webDB.doubleClickAnElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
					ElementType.Xpath);
			flag = webDB.waitForElement(BankPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i)),
					ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.clickAnElement(BankPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i)),
						ElementType.Xpath);
				commonFunc.hardWait(6000);
				flag = webDB.waitForElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "In Pending Party Documents This Voucher Number " + VoucherNumber.get(i)
							+ " Is Selected Successfully");
				}
			}
		}
//				}
		webDB.moveToElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton, ElementType.Xpath);
		webDB.javaScriptClickAnElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton, ElementType.Xpath);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(2000);
		webDB.switchToDefaultContent();
		flag = webDB.waitForElement(BankPaymentLocators.BankHeaderCreateJournalButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "OK Button Is Clicked Successfully In The Pending Party Documents");
		}
//			} else {
//				logs.logging("INFO",
//						"Bank Payment Line Wise Party Name And Pending Party Documents Party Name Is not Getting Matched");
//			}
//		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Add Button Multi Party
	 * Single Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughLineAddButtonMultiPartyMultiBill(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
							BPGrnExcelFileName);
				}
			}
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
		}
		for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
			if (j <= 2) {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		BPThroughAddButtonMultiPartyMultiBills(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Multi Party
	 * Single Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughGetDocumentMultiPartyMultiBillWithNewRef(String SheetName)
			throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
							BPGrnExcelFileName);
				}
			}
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
		}
		for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
			if (j <= 2) {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		BPThroughGetDocumentWithNewReference(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Multi Party
	 * Single Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughPendingDocumentMultiPartyMultiBill(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
							BPGrnExcelFileName);
				}
			}
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
		}
		for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
			if (j <= 2) {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		BPThroughPendingDocsWithNewReference(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"), commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Multi Party
	 * Single Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughPendingMultiPartyMultiBill(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
							BPGrnExcelFileName);
				}
			}
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
		}
		for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
			if (j <= 2) {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		BPThroughPendingDocsMPMB(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"), commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Multi Party
	 * Single Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughGetDocumentMultiPartyMultiBill(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
							BPGrnExcelFileName);
				}
			}
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
		}
		for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
			if (j <= 2) {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		BPThroughGetDocumentMPMB(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This method Is Used To Get Document Through BP
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param Currency
	 * @param BankName
	 */
	public void BPThroughGetDocumentWithNewReference(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		getBankPaymentOrReceiptDocumentNumber();
		checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyBankPaymentHeaderDetailsWithUI(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
		verifyBankPaymentDetailsWithDB(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		clickGetDocumentInBPHeader();
		clickPendingPartyDocumentsInBP();
		choosePartyNameAndClickSearchButtonInPendingPartyDocsMPMB(VariableHandler.getMultiplePBDocs(), SheetName);
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, webDB.getDataFromProperties("BankPaymentINRMPMBNR"));
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String PartyName = BankPayments[i][3];
			String EntityName = BankPayments[i][5];
			String CPCCodeDesc = BankPayments[i][6];
			String BillNumber = BankPayments[i][7];
			String PayAmount = BankPayments[i][8];
			String DRCR = BankPayments[i][9];
			String CURCY = BankPayments[i][10];
			String EXRate = BankPayments[i][11];
			String TransCURCY = BankPayments[i][12];
			String BaseCURCY = BankPayments[i][13];
			String AssesableValue = BankPayments[i][16];
			if (!GLAccountDesc.equals("")) {
				clickLineWiseViewButton(i);
				clickAddButtonInViewDetails();
				addBillNumberAndPayAmount(BillNumber, PayAmount);
				clickViewDetailsSaveButton();
				clickBankViewDetailsCloseButton();
				for (int j = 1; j < BankPayments.length; j++) {
					String GLAccountDesc1 = BankPayments[j][1];
					if (!GLAccountDesc1.equals("")) {
						if (!Currency.equals("INR")) {
							excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11,
									dbCommonFunctions.ExchangeRateDetails(Currency,
											commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
						} else {
							excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
						}
						verifyBankPaymentsLineDetailsWithDB(SheetName, i);
					}
				}
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBPHeader(BaseCURCY);
			}
			if (Currency.equals("INR")) {
				if (BankName.equals("JP MORGAN CHASE")) {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRINR"), 1, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRINR"), 1, 7,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRINR"), 2, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRINR"), 2, 7,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				} else {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNR"), 1, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNR"), 1, 7,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNR"), 2, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNR"), 2, 7,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				}
			} else {
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), 0, 11,
						dbCommonFunctions.ExchangeRateDetails(Currency,
								commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));

				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), 1, 6,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), 1, 7,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), 2, 6,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), 2, 7,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		clickCreateJournalButton();
		if (Currency.equals("INR")) {
			if (BankName.equals("JP MORGAN CHASE")) {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalNRINR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRINR"), 1, 6, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRINR"), 1, 7, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRINR"), 2, 6, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRINR"), 2, 7, "0");
			} else {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalNR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNR"), 1, 6, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNR"), 1, 7, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNR"), 2, 6, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNR"), 2, 7, "0");
			}
		} else {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalNRUSD"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), 1, 6, "0");
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), 1, 7, "0");
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), 2, 6, "0");
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), 2, 7, "0");
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify BP Thriugh Pending Docs with new Ref
	 * 
	 * @author Harikrishna - RMITS
	 * @param CreationType
	 * @param SheetName
	 * @param Currency
	 * @param BankName
	 */

	public void BPThroughPendingDocsWithNewReference(String CreationType, String SheetName, String Currency,
			String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingCRDocsButton();
		clickPendingPayablesButtonInPendCRDocs();
		chooseDocumentNumberInPendingPayablesScreenMPMB(VariableHandler.getMultiplePBDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentType"), commonFunc.getDataFromTestDataSheet("BankPayMode"),
				BankName, CreationType, SheetName);
		getBankPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		sendReferenceNumberInBP(commonFunc.getDataFromTestDataSheet("Reference Number"));
		commonFunc.checkRefreshCellsDisappear();
		clickBPHeaderWiseSaveButton();
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, webDB.getDataFromProperties("BankPaymentMultiNewRef"));
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String PartyName = BankPayments[i][2];
			String EntityName = BankPayments[i][3];
			String CPCCodeDesc = BankPayments[i][4];
			String BillNumber = BankPayments[i][5];
			String PayAmount = BankPayments[i][6];
			String DRCR = BankPayments[i][7];
			String CURCY = BankPayments[i][8];
			String EXRate = BankPayments[i][9];
			String TransCURCY = BankPayments[i][10];
			String BaseCURCY = BankPayments[i][11];
			String AssesableValue = BankPayments[i][14];
			if (!GLAccountDesc.equals("")) {
				clickLineWiseViewButton(i);
				clickAddButtonInViewDetails();
				addBillNumberAndPayAmount(BillNumber, PayAmount);
				clickViewDetailsSaveButton();
				clickBankViewDetailsCloseButton();
				for (int j = 1; j < BankPayments.length; j++) {
					String GLAccountDesc1 = BankPayments[j][1];
					if (!GLAccountDesc1.equals("")) {
						if (!Currency.equals("INR")) {
							excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11,
									dbCommonFunctions.ExchangeRateDetails(Currency,
											commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
						} else {
							excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
						}
						verifyBankPaymentsLineDetailsWithDB(SheetName, i);
					}
				}
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBPHeader(BaseCURCY);
			}
			if (Currency.equals("INR")) {
				if (BankName.equals("JP MORGAN CHASE")) {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRINR"), 1, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRINR"), 1, 7,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRINR"), 2, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRINR"), 2, 7,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				} else {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNR"), 1, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNR"), 1, 7,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNR"), 2, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNR"), 2, 7,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				}
			} else {
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), 0, 11,
						dbCommonFunctions.ExchangeRateDetails(Currency,
								commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));

				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), 1, 6,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), 1, 7,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), 2, 6,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), 2, 7,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		clickCreateJournalButton();
		if (Currency.equals("INR")) {
			if (BankName.equals("JP MORGAN CHASE")) {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalNRINR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRINR"), 1, 6, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRINR"), 1, 7, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRINR"), 2, 6, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRINR"), 2, 7, "0");
			} else {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalNR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNR"), 1, 6, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNR"), 1, 7, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNR"), 2, 6, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNR"), 2, 7, "0");
			}
		} else {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalNRUSD"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), 1, 6, "0");
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), 1, 7, "0");
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), 2, 6, "0");
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), 2, 7, "0");
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
		}
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Multi Party
	 * Single Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughAddButtonMultiPartyMultiBillWithNewRef(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
							BPGrnExcelFileName);
				}
			}
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
		}
		for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
			if (j <= 2) {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		BPThroughAddButtonWithNewRef(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used To Add The Bank Payment Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPThroughAddButtonWithNewRef(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		getBankPaymentOrReceiptDocumentNumber();
		checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyBankPaymentHeaderDetailsWithUI(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
		verifyBankPaymentDetailsWithDB(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String PartyName = BankPayments[i][3];
			String EntityName = BankPayments[i][5];
			String CPCCodeDesc = BankPayments[i][6];
			String BillNumber = BankPayments[i][7];
			String PayAmount = BankPayments[i][8];
			String DRCR = BankPayments[i][9];
			String CURCY = BankPayments[i][10];
			String EXRate = BankPayments[i][11];
			String TransCURCY = BankPayments[i][12];
			String BaseCURCY = BankPayments[i][13];
			String AssesableValue = BankPayments[i][16];
			if (!GLAccountDesc.equals("")) {
				clickBPLineAddButton();
				addBankPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
				clickLineWiseSaveButton();
				clickLineWiseAddButton(i);
				clickPendingPartyDocumentsInBP();
				selectPartyDocumentBasedOnPBThroughAdd(PartyName, VariableHandler.getMultiplePBDocs(), i, SheetName);
				clickLineWiseViewButton(i);
				clickAddButtonInViewDetails();
				addBillNumberAndPayAmount(BillNumber, PayAmount);
				clickViewDetailsSaveButton();
				clickBankViewDetailsCloseButton();
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
				}
				verifyBankPaymentsLineDetailsWithDB(SheetName, i);
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBPHeader(BaseCURCY);
			}
			if (Currency.equals("INR")) {
				if (BankName.equals("JP MORGAN CHASE")) {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRINR"), 1, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRINR"), 1, 7,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRINR"), 2, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRINR"), 2, 7,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				} else {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNR"), 1, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNR"), 1, 7,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNR"), 2, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNR"), 2, 7,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				}
			} else {
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), i, 11,
						dbCommonFunctions.ExchangeRateDetails(Currency,
								commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));

				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), 1, 6,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), 1, 7,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), 2, 6,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), 2, 7,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		clickCreateJournalButton();
		if (Currency.equals("INR")) {
			if (BankName.equals("JP MORGAN CHASE")) {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalNRINR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRINR"), 1, 6, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRINR"), 1, 7, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRINR"), 2, 6, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRINR"), 2, 7, "0");
			} else {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalNR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNR"), 1, 6, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNR"), 1, 7, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNR"), 2, 6, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNR"), 2, 7, "0");
			}
		} else {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalNRUSD"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), 1, 6, "0");
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), 1, 7, "0");
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), 2, 6, "0");
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalNRUSD"), 2, 7, "0");
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used to Select the Bank Payment pending Records In the Pending
	 * Party Documnets IN the bank Payment Screen
	 * 
	 * @author Harikrishna _ RMITS
	 * @param PartyName
	 * @param VoucherNumber.get(i)
	 */
	public void selectPartyDocumentBasedOnPBThroughAdd(String PartyName, ArrayList<String> VoucherNumber, int i1,
			String SheetName) {
		flag = webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
		if (flag) {
			String GetTextPartyName = webDB.getDomPropertyFromElement(
					BankPaymentLocators.BankPendingPartyDocumentsPartyNameInputField, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			int a = i1 * 2;
			if (GetTextPartyName.equals(PartyName)) {
				for (int i = a - 1; i <= a; i++) {
					commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.BankPartyDocNumberLovButton,
							VoucherNumber.get(i - 1));
					webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
					flag = webDB.waitForElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
							ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.doubleClickAnElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
								ElementType.Xpath);
						flag = webDB.waitForElement(
								BankPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i - 1)),
								ElementType.Xpath);
						if (flag) {
							commonFunc.checkRefreshCellsDisappear();
							verifyPendingPayablesPBDocumentThroughAddButton(SheetName, VoucherNumber.get(i - 1), i);
							webDB.clickAnElement(
									BankPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i - 1)),
									ElementType.Xpath);
							commonFunc.hardWait(6000);
							flag = webDB.waitForElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "In Pending Party Documents This Voucher Number "
										+ VoucherNumber.get(i - 1) + " Is Selected Successfully");
							}
						}
					}
				}
				webDB.moveToElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton, ElementType.Xpath);
				webDB.javaScriptClickAnElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton,
						ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(BankPaymentLocators.BankHeaderCreateJournalButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "OK Button Is Clicked Successfully In The Pending Party Documents");
				}
			} else {
				logs.logging("INFO",
						"Bank Payment Line Wise Party Name And Pending Party Documents Party Name Is not Getting Matched");
			}
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
		cashPymtFunc.MultiAdvPaymentDocumentNumber.clear();
		Grnfunc.MultiGRNDocs.clear();
		PBDocumentNumber.clear();
		MultiBankDocumentNumber.clear();
		VariableHandler.setMultipleGRNDocs(null);
		webDB.navigateToRefresh();
		webDB.switchToDefaultContent();
		commonFunc.enterUrl();
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Deepan-GWL
	 * @param DocNo
	 * @throws IOException
	 */
	public void createPurchaseBillsFromPendingGRN(String DocumentNumber) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(DocumentNumber);
		verifyCreateJournalAndPost();
		VariableHandler.setMultiplePBDocs(PBDocumentNumber);
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
	}

	/**
	 * This Method Is used To Verify Bank Payment Line Details
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void verifyBankPaymentLineDetailsWithDB(String SheetName) {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String PartyName = BankPayments[i][3];
			String EntityName = BankPayments[i][5];
			String CPCCodeDesc = BankPayments[i][6];
			String BillNumber = BankPayments[i][7];
			String PayAmount = BankPayments[i][8];
			String DRCR = BankPayments[i][9];
			String CURCY = BankPayments[i][10];
			String EXRate = BankPayments[i][11];
			String TransCURCY = BankPayments[i][12];
			String BaseCURCY = BankPayments[i][13];
			String AssesableValue = BankPayments[i][16];
			if (!GLAccountDesc.equals("")) {
				clickBPLineAddButton();
				addBankPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
				clickLineWiseSaveButton();
				clickLineWiseAddButton(i);
				clickPendingPartyDocumentsInBP();
				selectPartyDocumentBasedOnPB(PartyName, VariableHandler.getMultiplePBDocs(), SheetName);
			}
		}
	}

//	/*------------------------------------------BANK RECEIPT------------------------------------------------------------/*

	/**
	 * This Method Is Used To Click The Pending CR Docs Button In The Payment /
	 * Receipts Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickPendingDRDocsButton() {
		flag = webDB.waitForElement(BankPaymentLocators.PendingDRDocsButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.PendingDRDocsButton, ElementType.Xpath);
			flag = webDB.waitForElement(BankPaymentLocators.PendingReceivables, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending DR Docs Button Is Clicked Successfully");
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
		flag = webDB.waitForElement(BankPaymentLocators.BankReceiptPendingReceivablesButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BankReceiptPendingReceivablesButton, ElementType.Xpath);
			flag = webDB.waitForElement(BankPaymentLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Bank Receipt Pending Receivables Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used to Select the Bank Payment pending Records In the Pending
	 * Party Documnets IN the bank Payment Screen
	 * 
	 * @author Harikrishna _ RMITS
	 * @param PartyName
	 * @param VoucherNumber.get(i)
	 */
	public void selectSalesInvoiceDocumentInPendingBankReceipt(String PartyName, ArrayList<String> VoucherNumber) {
		flag = webDB.switchToFrame(BankPaymentLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
		if (flag) {
			String GetTextPartyName = webDB.getDomPropertyFromElement(
					BankPaymentLocators.BankReceiptPendingReceivablesPartyNameInputField, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			if (GetTextPartyName.equals(PartyName)) {
				for (int i = 0; i < VoucherNumber.size(); i++) {
					commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.BankReceiptDocNumberLovButton,
							VoucherNumber.get(i));
					webDB.switchToFrame(BankPaymentLocators.BankReceiptPendingReceivablesFrameName, ElementType.Xpath);
					flag = webDB.waitForElement(BankPaymentLocators.BankReceiptPendingReceivablesSearchButton,
							ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.doubleClickAnElement(BankPaymentLocators.BankReceiptPendingReceivablesSearchButton,
								ElementType.Xpath);
						flag = webDB.waitForElement(
								BankPaymentLocators.BRPendingReceivablesDocumentSelectCheckBox(VoucherNumber.get(i)),
								ElementType.Xpath);
						if (flag) {
							commonFunc.checkRefreshCellsDisappear();
							webDB.clickAnElement(BankPaymentLocators.BRPendingReceivablesDocumentSelectCheckBox(
									VoucherNumber.get(i)), ElementType.Xpath);
							commonFunc.hardWait(6000);
							flag = webDB.waitForElement(BankPaymentLocators.BankReceiptPendingReceivableScreenOkButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "In Pending Receivables Documents This Voucher Number "
										+ VoucherNumber.get(i) + " Is Selected Successfully");
							}
						}
					}
				}
				webDB.moveToElement(BankPaymentLocators.BankReceiptPendingReceivableScreenOkButton, ElementType.Xpath);
				webDB.javaScriptClickAnElement(BankPaymentLocators.BankReceiptPendingReceivableScreenOkButton,
						ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(BankPaymentLocators.BankReceiptHeaderCreateJournalButton,
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
		flag = webDB.waitForElement(BankPaymentLocators.BankReceiptLineWiseAddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BankReceiptLineWiseAddButton, ElementType.Xpath);
			flag = webDB.waitForElement(BankPaymentLocators.BankLineGLAccountField, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Bank Payment Line Wise Add Button Is Clicked Successfully");
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
	public boolean BankReceiptThroughAddButton(String SheetName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocModeBR"),
				commonFunc.getDataFromTestDataSheet("BankName"), Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"));
		getBankPaymentOrReceiptDocumentNumber();
		checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyBankPaymentDetailsWithDB(commonFunc.getDataFromTestDataSheet("BankName"),
				commonFunc.getDataFromTestDataSheet("BankReceiptPrefix"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"));
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String PartyName = BankPayments[i][3];
			String EntityName = BankPayments[i][5];
			String CPCCodeDesc = BankPayments[i][6];
			String BillNumber = BankPayments[i][7];
			String PayAmount = BankPayments[i][8];
			String DRCR = BankPayments[i][9];
			String CURCY = BankPayments[i][10];
			String EXRate = BankPayments[i][11];
			String TransCURCY = BankPayments[i][12];
			String BaseCURCY = BankPayments[i][13];
			String AssesableValue = BankPayments[i][16];
			if (!GLAccountDesc.equals("")) {
				clickBPLineAddButton();
				addBankPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
				clickLineWiseSaveButton();
				clickLineWiseAddButton(i);
				clickPendingReceivablesInBR();
				selectSalesInvoiceDocumentInPendingBankReceipt(PartyName, VariableHandler.getMultiplePBDocs());
			}
		}
		clickCreateJournalButton();
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(commonFunc.getDataFromTestDataSheet("BankName"),
					commonFunc.getDataFromTestDataSheet("BankTransCurcy"));
		}
		return flag;
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
	public void verifyPendingPayablesPBDocumentThroughGetDoc(String SheetName, String VoucherNumber, int i1) {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
//		int a= i1*1;
		for (int i = i1; i <= i1; i++) {
//		int innerValue = (i1 % 2 == 1) ? 1 : 2;
//		  for (int i = innerValue; i <= innerValue; i++) {
//			for (int i = (i1 + 1) / 2; i <= (i1 + 1) / 2; i++){
//			for(int j = 1; j<BankPayments.length;j++) {
			System.out.println(i);
			String PartyID = BankPayments[i][2];
			String PartyName = BankPayments[i][3];
			String Entity = BankPayments[i][5];
			String CPCDesc = BankPayments[i][6];
			String Curcy = BankPayments[i][10];
			String EXRate = BankPayments[i][11];
			String Location = BankPayments[i][23];
			String Unit = BankPayments[i][24];
			String DrCr = BankPayments[i][25];
			String PayableAmount = BankPayments[i][26];
			if (!PartyID.equals("")) {
				salesOrdFunc
						.verifyAssertionBothValues(
								dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
										commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesPartyName")),
								PartyName);
				logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
						+ " PartyName Is getting Matched With DB");

				salesOrdFunc
						.verifyAssertionBothValues(
								dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
										commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesLocationID")),
								Location);
				logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
						+ " Location ID Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankPaymentPendingPayablesDetails(
						VoucherNumber, commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesUnitID")), Unit);
				logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
						+ " Unit ID Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankPaymentPendingPayablesDetails(
						VoucherNumber, commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesCurcy")), Curcy);
				logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
						+ " Currency Is getting Matched With DB");
				if (Curcy.equals("INR")) {
					salesOrdFunc
							.verifyAssertionBothValues(
									dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
											commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesExRate")),
									EXRate);
					logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
							+ " EX.Rate Is getting Matched With DB");
				} else {
					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
									commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesExRate")),
							dbCommonFunctions.ExchangeRateDetails(Curcy,
									commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
					logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
							+ " EX.Rate Is getting Matched With DB");
				}
				salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankPaymentPendingPayablesDetails(
						VoucherNumber, commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesDROrCR")), DrCr);
				logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
						+ " Dr / Cr Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesInvAmt")),
						dbCommonFunctions.BankPaymentPBHeaderDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
						+ " Payable Amount Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesDueDays")),
						dbCommonFunctions.SOPaymentTermAdvPercentage(
								commonFunc.getDataFromTestDataSheet("SupplierPaymentTerm"),
								commonFunc.getDataFromTestDataSheet("PaymentTermDueDays")));
				logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
						+ " Due Days Is getting Matched With DB");

				salesOrdFunc
						.verifyAssertionBothValues(
								dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
										commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesDocNumber")),
								VoucherNumber);
				logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
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
	public void verifyPendingPayablesPBDocumentThroughAddButton(String SheetName, String VoucherNumber, int i1) {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
//		int a= i1*1;
		for (int i = i1; i <= i1; i++) {
//		int innerValue = (i1 % 2 == 1) ? 1 : 2;
//		  for (int i = innerValue; i <= innerValue; i++) {
//			for (int i = (i1 + 1) / 2; i <= (i1 + 1) / 2; i++){
//			for(int j = 1; j<BankPayments.length;j++) {
			System.out.println(i);
			if (i <= 2) {
				String PartyID = BankPayments[1][2];
				String PartyName = BankPayments[1][3];
				String Entity = BankPayments[1][5];
				String CPCDesc = BankPayments[1][6];
				String Curcy = BankPayments[1][10];
				String EXRate = BankPayments[1][11];
				String Location = BankPayments[1][23];
				String Unit = BankPayments[1][24];
				String DrCr = BankPayments[1][25];
				String PayableAmount = BankPayments[1][26];
				if (!PartyID.equals("")) {
					salesOrdFunc
							.verifyAssertionBothValues(
									dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
											commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesPartyName")),
									PartyName);
					logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
							+ " PartyName Is getting Matched With DB");

					salesOrdFunc
							.verifyAssertionBothValues(
									dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
											commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesLocationID")),
									Location);
					logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
							+ " Location ID Is getting Matched With DB");

					salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankPaymentPendingPayablesDetails(
							VoucherNumber, commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesUnitID")), Unit);
					logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
							+ " Unit ID Is getting Matched With DB");

					salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankPaymentPendingPayablesDetails(
							VoucherNumber, commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesCurcy")), Curcy);
					logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
							+ " Currency Is getting Matched With DB");

					if (Curcy.equals("INR")) {
						salesOrdFunc
								.verifyAssertionBothValues(
										dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
												commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesExRate")),
										EXRate);
						logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
								+ " EX.Rate Is getting Matched With DB");
					} else {
						salesOrdFunc.verifyAssertionBothValues(
								dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
										commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesExRate")),
								dbCommonFunctions.ExchangeRateDetails(Curcy,
										commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
						logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
								+ " EX.Rate Is getting Matched With DB");
					}

					salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankPaymentPendingPayablesDetails(
							VoucherNumber, commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesDROrCR")), DrCr);
					logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
							+ " Dr / Cr Is getting Matched With DB");

					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
									commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesInvAmt")),
							dbCommonFunctions.BankPaymentPBHeaderDetails(VoucherNumber,
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
							+ " Payable Amount Is getting Matched With DB");

					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
									commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesDueDays")),
							dbCommonFunctions.SOPaymentTermAdvPercentage(
									commonFunc.getDataFromTestDataSheet("SupplierPaymentTerm"),
									commonFunc.getDataFromTestDataSheet("PaymentTermDueDays")));
					logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
							+ " Due Days Is getting Matched With DB");

					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
									commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesDocNumber")),
							VoucherNumber);
					logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
							+ " Source Document Is getting Matched With DB");
				}
			} else {
				String PartyID = BankPayments[2][2];
				String PartyName = BankPayments[2][3];
				String Entity = BankPayments[2][5];
				String CPCDesc = BankPayments[2][6];
				String Curcy = BankPayments[2][10];
				String EXRate = BankPayments[2][11];
				String Location = BankPayments[2][23];
				String Unit = BankPayments[2][24];
				String DrCr = BankPayments[2][25];
				String PayableAmount = BankPayments[2][26];
				if (!PartyID.equals("")) {
					salesOrdFunc
							.verifyAssertionBothValues(
									dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
											commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesPartyName")),
									PartyName);
					logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
							+ " PartyName Is getting Matched With DB");

					salesOrdFunc
							.verifyAssertionBothValues(
									dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
											commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesLocationID")),
									Location);
					logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
							+ " Location ID Is getting Matched With DB");

					salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankPaymentPendingPayablesDetails(
							VoucherNumber, commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesUnitID")), Unit);
					logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
							+ " Unit ID Is getting Matched With DB");

					salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankPaymentPendingPayablesDetails(
							VoucherNumber, commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesCurcy")), Curcy);
					logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
							+ " Currency Is getting Matched With DB");

					if (Curcy.equals("INR")) {
						salesOrdFunc
								.verifyAssertionBothValues(
										dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
												commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesExRate")),
										EXRate);
						logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
								+ " EX.Rate Is getting Matched With DB");
					} else {
						salesOrdFunc.verifyAssertionBothValues(
								dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
										commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesExRate")),
								dbCommonFunctions.ExchangeRateDetails(Curcy,
										commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
						logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
								+ " EX.Rate Is getting Matched With DB");
					}

					salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankPaymentPendingPayablesDetails(
							VoucherNumber, commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesDROrCR")), DrCr);
					logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
							+ " Dr / Cr Is getting Matched With DB");

					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
									commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesInvAmt")),
							dbCommonFunctions.BankPaymentPBHeaderDetails(VoucherNumber,
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
							+ " Payable Amount Is getting Matched With DB");

					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
									commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesDueDays")),
							dbCommonFunctions.SOPaymentTermAdvPercentage(
									commonFunc.getDataFromTestDataSheet("SupplierPaymentTerm"),
									commonFunc.getDataFromTestDataSheet("PaymentTermDueDays")));
					logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
							+ " Due Days Is getting Matched With DB");

					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
									commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesDocNumber")),
							VoucherNumber);
					logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
							+ " Source Document Is getting Matched With DB");
				}
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
	public void verifyPendingPayablesPBDocumentThroughGetDocMPMB(String SheetName, String VoucherNumber, int i1) {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
//		int a= i1*1;
//		for (int i = i1; i <= i1; i++) {
		int innerValue = (i1 % 2 == 1) ? 1 : 2;
		for (int i = innerValue; i <= innerValue; i++) {
//			for (int i = (i1 + 1) / 2; i <= (i1 + 1) / 2; i++){
//			for(int j = 1; j<BankPayments.length;j++) {
			System.out.println(i);
			String PartyID = BankPayments[i][2];
			String PartyName = BankPayments[i][3];
			String Entity = BankPayments[i][5];
			String CPCDesc = BankPayments[i][6];
			String Curcy = BankPayments[i][10];
			String EXRate = BankPayments[i][11];
			String Location = BankPayments[i][23];
			String Unit = BankPayments[i][24];
			String DrCr = BankPayments[i][25];
			String PayableAmount = BankPayments[i][26];
			if (!PartyID.equals("")) {
				salesOrdFunc
						.verifyAssertionBothValues(
								dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
										commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesPartyName")),
								PartyName);
				logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
						+ " PartyName Is getting Matched With DB");

				salesOrdFunc
						.verifyAssertionBothValues(
								dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
										commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesLocationID")),
								Location);
				logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
						+ " Location ID Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankPaymentPendingPayablesDetails(
						VoucherNumber, commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesUnitID")), Unit);
				logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
						+ " Unit ID Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankPaymentPendingPayablesDetails(
						VoucherNumber, commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesCurcy")), Curcy);
				logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
						+ " Currency Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankPaymentPendingPayablesDetails(
						VoucherNumber, commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesExRate")), EXRate);
				logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
						+ " EX.Rate Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankPaymentPendingPayablesDetails(
						VoucherNumber, commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesDROrCR")), DrCr);
				logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
						+ " Dr / Cr Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesInvAmt")),
						dbCommonFunctions.BankPaymentPBHeaderDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
						+ " Payable Amount Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesDueDays")),
						dbCommonFunctions.SOPaymentTermAdvPercentage(
								commonFunc.getDataFromTestDataSheet("SupplierPaymentTerm"),
								commonFunc.getDataFromTestDataSheet("PaymentTermDueDays")));
				logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
						+ " Due Days Is getting Matched With DB");

				salesOrdFunc
						.verifyAssertionBothValues(
								dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
										commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesDocNumber")),
								VoucherNumber);
				logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
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
	public void verifyBankPaymentsLineDetailsWithDB(String SheetName, int i1) {

		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String PartyID = BankPayments[i][2];
			String PartyName = BankPayments[i][3];
			String Entity = BankPayments[i][5];
			String CPCDesc = BankPayments[i][6];
			String LineDrCr = BankPayments[i][9];
			String Curcy = BankPayments[i][10];
			String EXRate = BankPayments[i][11];
			String AmtInTC = BankPayments[i][12];
			String AmtInBC = BankPayments[i][13];
			String HSNCode = BankPayments[i][14];
			String AssesableValue = BankPayments[i][16];
			String CGST = BankPayments[i][17];
			String SGST = BankPayments[i][18];
			String IGST = BankPayments[i][19];
			String UTGST = BankPayments[i][20];
			String CESSPCT = BankPayments[i][21];
			String CESSAMT = BankPayments[i][22];
			String Location = BankPayments[i][23];
			String Unit = BankPayments[i][24];
			String PendingDrCr = BankPayments[i][25];
			String TotAmt = BankPayments[i][28];
			if (!GLAccountDesc.equals("")) {
				String SeqNo = String.valueOf(i);
				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBGLAccountDescColumnName")),
						GLAccountDesc);
				logs.logging("INFO",
						" Line Wise GL Account Desc Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());

//				salesOrdFunc.verifyAssertionBothValues(
//						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
//								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBSupplierDescColumnName")),
//						PartyName);
//				logs.logging("INFO",
//						" Line Wise Party Name Is Matched With DataBase Values Against This Bank Receipt Document "
//								+ VariableHandler.getBankPaymentOrReceiptDocNumber());

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
				if (SheetName.equals("BankPaymentExpGST")) {
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

					if (SheetName.contains("TDS")) {
						String POSheetName = BankPayments[i][4];
						String TDS = BankPayments[i][29];
						String TDSSourceAcc = BankPayments[i][30];
						String TDSSourcePercentage = BankPayments[i][31];
						String TDSSourceAssVal = BankPayments[i][32];
						String TDSSourceAmt = BankPayments[i][33];

						if (!POSheetName.equals("")) {
							salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankPaymentLineDetails(
									VariableHandler.getCashPaymentOrReceiptDocNumber(), SeqNo,
									commonFunc.getDataFromTestDataSheet("TDSColumnName")), TDS);
							logs.logging("INFO",
									" Line Wise TDS ID Is Matched With DataBase Values Against This Cash Receipt Document "
											+ VariableHandler.getCashPaymentOrReceiptDocNumber());

							salesOrdFunc.verifyAssertionBothValues(
									dbCommonFunctions.BankPaymentLineDetails(
											VariableHandler.getCashPaymentOrReceiptDocNumber(), SeqNo,
											commonFunc.getDataFromTestDataSheet("TDSSourceAccountColumnName")),
									TDSSourceAcc);
							logs.logging("INFO",
									" Line Wise TDS Source Account Is Matched With DataBase Values Against This Cash Receipt Document "
											+ VariableHandler.getCashPaymentOrReceiptDocNumber());

							salesOrdFunc.verifyAssertionBothValues(
									dbCommonFunctions.BankPaymentLineDetails(
											VariableHandler.getCashPaymentOrReceiptDocNumber(), SeqNo,
											commonFunc.getDataFromTestDataSheet("TDSSourcePctColumnName")),
									TDSSourcePercentage);
							logs.logging("INFO",
									" Line Wise TDS Source Account Is Matched With DataBase Values Against This Cash Receipt Document "
											+ VariableHandler.getCashPaymentOrReceiptDocNumber());

							salesOrdFunc.verifyAssertionBothValues(
									dbCommonFunctions.BankPaymentLineDetails(
											VariableHandler.getCashPaymentOrReceiptDocNumber(), SeqNo,
											commonFunc.getDataFromTestDataSheet("TDSSourceAssValueColumnName")),
									TDSSourceAssVal);
							logs.logging("INFO",
									" Line Wise TDS Source Assesable Value Is Matched With DataBase Values Against This Cash Receipt Document "
											+ VariableHandler.getCashPaymentOrReceiptDocNumber());

							salesOrdFunc.verifyAssertionBothValues(
									dbCommonFunctions.BankPaymentLineDetails(
											VariableHandler.getCashPaymentOrReceiptDocNumber(), SeqNo,
											commonFunc.getDataFromTestDataSheet("TDSSourceAmtColumnName")),
									TDSSourceAmt);
							logs.logging("INFO",
									" Line Wise TDS Source Amount Value Is Matched With DataBase Values Against This Cash Receipt Document "
											+ VariableHandler.getCashPaymentOrReceiptDocNumber());
						} else {
							salesOrdFunc.verifyAssertionBothValues(
									dbCommonFunctions.BankPaymentLineDetails(
											VariableHandler.getCashPaymentOrReceiptDocNumber(), SeqNo,
											commonFunc.getDataFromTestDataSheet("TDSSourcePctColumnName")),
									TDSSourcePercentage);
							logs.logging("INFO",
									" Line Wise TDS Source Account Is Matched With DataBase Values Against This Cash Receipt Document "
											+ VariableHandler.getCashPaymentOrReceiptDocNumber());

							salesOrdFunc.verifyAssertionBothValues(
									dbCommonFunctions.BankPaymentLineDetails(
											VariableHandler.getCashPaymentOrReceiptDocNumber(), SeqNo,
											commonFunc.getDataFromTestDataSheet("TDSSourceAssValueColumnName")),
									TDSSourceAssVal);
							logs.logging("INFO",
									" Line Wise TDS Source Assesable Value Is Matched With DataBase Values Against This Cash Receipt Document "
											+ VariableHandler.getCashPaymentOrReceiptDocNumber());

							salesOrdFunc.verifyAssertionBothValues(
									dbCommonFunctions.BankPaymentLineDetails(
											VariableHandler.getCashPaymentOrReceiptDocNumber(), SeqNo,
											commonFunc.getDataFromTestDataSheet("TDSSourceAmtColumnName")),
									TDSSourceAmt);
							logs.logging("INFO",
									" Line Wise TDS Source Amount Value Is Matched With DataBase Values Against This Cash Receipt Document "
											+ VariableHandler.getCashPaymentOrReceiptDocNumber());
						}
					}
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
	public void enterRealizedAmountInBPHeader(String RealizedAmt) {
		flag = webDB.waitForElement(BankReceiptLocators.BankReceiptRealizedAmtInputField, ElementType.Xpath);
		if (flag) {
			webDB.clearTextField(BankReceiptLocators.BankReceiptRealizedAmtInputField, ElementType.Xpath);
			webDB.sendTextToAnElement(BankReceiptLocators.BankReceiptRealizedAmtInputField, RealizedAmt,
					ElementType.Xpath);
			flag = webDB.waitForElement(BankPaymentLocators.BankHeaderCreateJournalButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Realized Amount " + RealizedAmt + " Is Entered Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Overall Header Amount With DB
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void verifyOverallHeaderAmountWithDB(String SheetName) {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String ChargeAmt = BankPayments[i][8];
			String AmtInTC = BankPayments[i][12];
			String TotalAmount = BankPayments[i][28];
			String ChargesFlag = BankPayments[i][29];
			if (GLAccountDesc.equals("")) {
				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentHeaderDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
								commonFunc.getDataFromTestDataSheet("BPDBHeaderReceiptAmtTCColumnName")),
						AmtInTC);
				logs.logging("INFO",
						" Header Wise Receipt Amt (TC) Value Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());

				if (ChargesFlag.equals("Y")) {
					salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankPaymentHeaderDetails(
							VariableHandler.getBankPaymentOrReceiptDocNumber(),
							commonFunc.getDataFromTestDataSheet("BPDBHeaderRLZDAmtColumnName")), AmtInTC);
				} else {
					salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankPaymentHeaderDetails(
							VariableHandler.getBankPaymentOrReceiptDocNumber(),
							commonFunc.getDataFromTestDataSheet("BPDBHeaderRLZDAmtColumnName")), TotalAmount);
					logs.logging("INFO",
							" Header Wise RLZD Amt (Bank Curcy) Value Is Matched With DataBase Values Against This Bank Receipt Document "
									+ VariableHandler.getBankPaymentOrReceiptDocNumber());
				}

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
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			if (!GLAccountDesc.equals("")) {
				for (int j = 1; j <= 2; j++) {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 25 + j, "0");
				}
			}
		}
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughLineAddButtonSingleBillsUSD(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeImportSupplierHK(SupplierID, GRNSheetName,
						BPGrnExcelFileName);
			}
		}
		for (int i = 1; i <= VariableHandler.getMultipleGRNDocs().size(); i++) {
//			createPurchaseBillsFromPendingGRN(VariableHandler.getMultipleGRNDocs().get(i - 1));
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(0),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number is : " + VariableHandler.getPbNumber());
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 25 + i,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i - 1),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BPThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughGetDocumentSingleBillsUSD(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeImportSupplierHK(SupplierID, GRNSheetName,
						BPGrnExcelFileName);
			}
		}
		for (int i = 1; i <= VariableHandler.getMultipleGRNDocs().size(); i++) {
//			createPurchaseBillsFromPendingGRN(VariableHandler.getMultipleGRNDocs().get(i - 1));
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(0),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number is : " + VariableHandler.getPbNumber());
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 25 + i,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i - 1),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BPThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughPendingDocsSingleBillsUSD(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeImportSupplierHK(SupplierID, GRNSheetName,
						BPGrnExcelFileName);
			}
		}
		for (int i = 1; i <= VariableHandler.getMultipleGRNDocs().size(); i++) {
//			createPurchaseBillsFromPendingGRN(VariableHandler.getMultipleGRNDocs().get(i - 1));
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(0),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number is : " + VariableHandler.getPbNumber());
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 25 + i,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i - 1),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BPThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughLineAddButtonMultiBillsUSD(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i <= 2; i++) {
			String SupplierID = BankPayments[1][2];
			String GRNSheetName = BankPayments[1][4];
			Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeImportSupplierHK(SupplierID, GRNSheetName,
					BPGrnExcelFileName);
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BPThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughGetDocumentMultiBillsUSD(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i <= 2; i++) {
			String SupplierID = BankPayments[1][2];
			String GRNSheetName = BankPayments[1][4];
			Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeImportSupplierHK(SupplierID, GRNSheetName,
					BPGrnExcelFileName);
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BPThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used To Create BP Thriugh pending docs
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughPendingDocsMultiBillsUSD(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i <= 2; i++) {
			String SupplierID = BankPayments[1][2];
			String GRNSheetName = BankPayments[1][4];
			Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeImportSupplierHK(SupplierID, GRNSheetName,
					BPGrnExcelFileName);
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BPThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Add Button Multi Party
	 * Single Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughLineAddButtonMultiPartySingleBillUSD(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeImportSupplierHK(SupplierID, GRNSheetName,
						BPGrnExcelFileName);
			}
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, i + 1, 25 + (i + 1),
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BPThroughAddButtonMultiPartySingleBills(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Multi Party
	 * Single Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughGetDocumentMultiPartySingleBillUSD(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeImportSupplierHK(SupplierID, GRNSheetName,
						BPGrnExcelFileName);
			}
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, i + 1, 25 + (i + 1),
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BPThroughGetDocumentMPSI(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Multi Party
	 * Single Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughPendingDocumentMultiPartySingleBillUSD(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeImportSupplierHK(SupplierID, GRNSheetName,
						BPGrnExcelFileName);
			}
		}
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String BillSeqNo = RandomStringUtils.randomNumeric(6);
			if (BillSeqNo.equals("000")) {
				BillSeqNo = "10";
			}
			String PBBillNumber = "PB" + BillSeqNo;
			verifyPendingPurchaseBill(VariableHandler.getMultipleGRNDocs().get(i));
			flag = webDB.waitForElement(BankPaymentLocators.PBBillNumberInputField, ElementType.Xpath);
			if (flag) {
				webDB.doubleClickAnElement(BankPaymentLocators.PBBillNumberInputField, ElementType.Xpath);
				webDB.clearTextField(BankPaymentLocators.PBBillNumberInputField, ElementType.Xpath);
				webDB.sendTextToAnElement(BankPaymentLocators.PBBillNumberInputField, PBBillNumber, ElementType.Xpath);
				verifyCreateJournalAndPost();
				VariableHandler.setMultiplePBDocs(PBDocumentNumber);
				flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
				if (flag) {
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
							webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
							System.getProperty("HariUsername"));
					flag = webDB.waitForElement(BankPaymentLocators.PBFindButton, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(BankPaymentLocators.PBFindButton, ElementType.Xpath);
						flag = webDB.waitForElement(BankPaymentLocators.PendingPurchaseBill, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Find Button Is clicked Successfully");
						}
					}
				}
			}
			excelUtils.writeDataInExcel(BPFilepath, SheetName, i + 1, 25 + i + 1,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		commonFunc.logoutUser();
		BPThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Add Button Multi Party
	 * Single Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughLineAddButtonMultiPartyMultiBillUSD(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeImportSupplierHK(SupplierID, GRNSheetName,
							BPGrnExcelFileName);
				}
			}
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
		}
		for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
			if (j <= 2) {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		BPThroughAddButtonMultiPartyMultiBills(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Multi Party
	 * Single Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughGetDocumentMultiPartyMultiBillUSD(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeImportSupplierHK(SupplierID, GRNSheetName,
							BPGrnExcelFileName);
				}
			}
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
		}
		for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
			if (j <= 2) {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		BPThroughGetDocumentMPMB(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Pending Document Multi
	 * Party Multi Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughPendingMultiPartyMultiBillUSD(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeImportSupplierHK(SupplierID, GRNSheetName,
							BPGrnExcelFileName);
				}
			}
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
		}
		for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
			if (j <= 2) {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		BPThroughPendingDocsMPMB(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Multi Party
	 * Multi Bill With New Ref
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughGetDocumentMultiPartyMultiBillWithNewRefUSD(String SheetName)
			throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeImportSupplierHK(SupplierID, GRNSheetName,
							BPGrnExcelFileName);
				}
			}
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
		}
		for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
			if (j <= 2) {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		BPThroughGetDocumentWithNewReference(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Pending Document Multi
	 * Party Multi Bill With New Ref
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughPendingDocumentMultiPartyMultiBillUSD(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeImportSupplierHK(SupplierID, GRNSheetName,
							BPGrnExcelFileName);
				}
			}
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
		}
		for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
			if (j <= 2) {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		BPThroughPendingDocsWithNewReference(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Add Button Multi Party
	 * Multi Bill With New Ref
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughAddButtonMultiPartyMultiBillWithNewRefUSD(String SheetName)
			throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeImportSupplierHK(SupplierID, GRNSheetName,
							BPGrnExcelFileName);
				}
			}
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
		}
		for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
			if (j <= 2) {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		BPThroughAddButtonWithNewRef(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
		return flag;
	}

	/**
	 * This Method Is Used To Create BP With PDC
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param Currency
	 */
	public void bankPaymentWithPostDatedCheque(String SheetName, String Currency) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"),
				commonFunc.getDataFromTestDataSheet("BankName"), Yesterday,
				commonFunc.getDataFromTestDataSheet("BankPayModeCheque"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		getBankPaymentOrReceiptDocumentNumber();
		checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyBankPaymentHeaderDetailsWithUI(commonFunc.getDataFromTestDataSheet("BankName"),
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Yesterday, Today);
		verifyBankPaymentDetailsWithDB(commonFunc.getDataFromTestDataSheet("BankName"),
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		clickPDCFlagInBP();
//		changeReferenceDateInBP(tomorrowDate);
		clickBPHeaderWiseSaveButton();
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String PartyName = BankPayments[i][3];
			String EntityName = BankPayments[i][5];
			String CPCCodeDesc = BankPayments[i][6];
			String BillNumber = BankPayments[i][7];
			String PayAmount = BankPayments[i][8];
			String DRCR = BankPayments[i][9];
			String CURCY = BankPayments[i][10];
			String EXRate = BankPayments[i][11];
			String TransCURCY = BankPayments[i][12];
			String BaseCURCY = BankPayments[i][13];
			String AssesableValue = BankPayments[i][16];
			if (!GLAccountDesc.equals("")) {
				clickBPLineAddButton();
				addBankPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
				clickLineWiseSaveButton();
				clickLineWiseViewButton(i);
				clickAddButtonInViewDetails();
				addBillNumberAndPayAmount(BillNumber, PayAmount);
				changeDateInViewDetails(Yesterday);
				clickViewDetailsSaveButton();
				clickBankViewDetailsCloseButton();
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
				}
				verifyBankPaymentsLineDetailsWithDB(SheetName, i);
			}
			if (!Currency.equals("INR")) {
				enterRealizedAmountInBPHeader(BaseCURCY);
			}
		}
		clickCreateJournalButton();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalPDC"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
//			commonFunc.logoutUser();
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			verifyGLMCntrlPDCPaymentDate(commonFunc.getDataFromTestDataSheet("BankName"), Currency);
//			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
		}
	}

	/**
	 * This Method Is Used To Click The PDC Flag In The BP
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void clickPDCFlagInBP() {
		commonFunc.checkRefreshCellsDisappear();
		webDB.pressTabKey();
		flag = webDB.waitForElement(BankPaymentLocators.BakReciptPDCLabelName, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BakReciptPDCLabelName, ElementType.Xpath);
			if (webDB.isElementSelected(BankPaymentLocators.BankReceiptPDCFlag, ElementType.Xpath)) {
				logs.logging("INFO", " Post Dated Cheque Flag Is Selected ");
			} else {
				logs.logging("INFO", " Post Dated Cheque Flag Is Not Selected ");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Change The Reference Date In BP
	 * 
	 * @author Harikrishna - RMITS
	 * @param Date
	 */
	public void changeReferenceDateInBP(String Date) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(BankPaymentLocators.BakReciptPDCLabelName, ElementType.Xpath);
		if (flag) {
			if (webDB.isElementSelected(BankPaymentLocators.BankReceiptPDCFlag, ElementType.Xpath)) {
				logs.logging("INFO", " Post Dated Cheque Flag Is Selected ");
				flag = webDB.waitForElement(BankPaymentLocators.BankReciptRefDateInputField, ElementType.Xpath);
				if (flag) {
					webDB.clearTextField(BankPaymentLocators.BankReciptRefDateInputField, ElementType.Xpath);
					webDB.sendTextToAnElement(BankPaymentLocators.BankReciptRefDateInputField, Date, ElementType.Xpath);
					flag = webDB.waitForElement(BankPaymentLocators.BankHeaderWiseSaveButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Reference Date Is Inserted Successfully");
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
	public void BankPaymentWithExpAndRevenueWithoutGST(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		getBankPaymentOrReceiptDocumentNumber();
		checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyBankPaymentHeaderDetailsWithUI(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
		verifyBankPaymentDetailsWithDB(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String PartyName = BankPayments[i][3];
			String EntityName = BankPayments[i][5];
			String CPCCodeDesc = BankPayments[i][6];
			String BillNumber = BankPayments[i][7];
			String PayAmount = BankPayments[i][8];
			String DRCR = BankPayments[i][9];
			String CURCY = BankPayments[i][10];
			String EXRate = BankPayments[i][11];
			String TransCURCY = BankPayments[i][12];
			String BaseCURCY = BankPayments[i][13];
			String HSNCode = BankPayments[i][14];
			String AssesableValue = BankPayments[i][16];
			String LineWiseDRCR = BankPayments[i][25];
			String ChargesFlag = BankPayments[i][29];
			String StateCode = BankPayments[i][30];
			String GSTSupplierName = BankPayments[i][31];
			String GSTIN = BankPayments[i][32];
			if (!GLAccountDesc.equals("")) {
				clickBPLineAddButton();
				addBankPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
				enterValueInAmtInTCField(PayAmount);
				chooseLineWiseDRCRInBP(LineWiseDRCR);
				clickLineWiseSaveButton();
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalREVUSD"), 0, 10,
							dbCommonFunctions.ExchangeRateDetails(Currency,
									commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
				}
				verifyBankPaymentsLineDetailsWithDB(SheetName, i);
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBPHeader(BaseCURCY);
			}
		}
		flag = webDB.waitForElement(BankPaymentLocators.BankPaymentFavorOfInputField, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(BankPaymentLocators.BankPaymentFavorOfInputField, "HARI TRADERS",
					ElementType.Xpath);
		}
		clickCreateJournalButton();
		if (Currency.equals("INR")) {
			if (BankName.equals("JP MORGAN CHASE")) {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalREVINR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
			} else {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalREV"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
			}
		} else {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalREVUSD"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
//			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
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

	public void chooseLineWiseDRCRInBP(String DRCR) {
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
	public void BankPaymentWithExpAndRevenueWithGST(String SheetName, String Currency) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"),
				commonFunc.getDataFromTestDataSheet("BankName"), Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		getBankPaymentOrReceiptDocumentNumber();
		checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyBankPaymentHeaderDetailsWithUI(commonFunc.getDataFromTestDataSheet("BankName"),
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
		verifyBankPaymentDetailsWithDB(commonFunc.getDataFromTestDataSheet("BankName"),
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String PartyName = BankPayments[i][3];
			String EntityName = BankPayments[i][5];
			String CPCCodeDesc = BankPayments[i][6];
			String BillNumber = BankPayments[i][7];
			String PayAmount = BankPayments[i][8];
			String DRCR = BankPayments[i][9];
			String CURCY = BankPayments[i][10];
			String EXRate = BankPayments[i][11];
			String TransCURCY = BankPayments[i][12];
			String BaseCURCY = BankPayments[i][13];
			String HSNCode = BankPayments[i][14];
			String AssesableValue = BankPayments[i][16];
			String LineWiseDRCR = BankPayments[i][25];
			String ChargesFlag = BankPayments[i][29];
			String StateCode = BankPayments[i][30];
			String GSTSupplierName = BankPayments[i][31];
			String GSTIN = BankPayments[i][32];
			if (!GLAccountDesc.equals("")) {
				clickBPLineAddButton();
				addBankPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
				enterValueInAmtInTCField(PayAmount);
				chooseLineWiseDRCRInBP(LineWiseDRCR);
				chooseHSNCodeInBP(HSNCode);
				clickLineWiseSaveButton();
				clickLineWiseGSTIconInBP(i);
				enterValuesInGSTScreen(StateCode, GSTSupplierName, GSTIN, Today, BillNumber);
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
				}
				verifyBankPaymentsLineDetailsWithDB(SheetName, i);
			}
			if (!Currency.equals("INR")) {
				enterRealizedAmountInBPHeader(BaseCURCY);
			}
		}
		flag = webDB.waitForElement(BankPaymentLocators.BankPaymentFavorOfInputField, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(BankPaymentLocators.BankPaymentFavorOfInputField, "HARI TRADERS",
					ElementType.Xpath);
		}
		clickCreateJournalButton();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalREVGST"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
//			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Choose HSN Code In BP
	 * 
	 * @author Harikrishna - RMITS
	 * @param HSNCode
	 */
	public void chooseHSNCodeInBP(String HSNCode) {
		flag = webDB.waitForElement(BankPaymentLocators.BankReceiptHSNCodeField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BankReceiptHSNCodeField, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.BankReceiptLineHsnCodeLov, HSNCode);
			webDB.pressTabKey();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Choose TDS In BP
	 * 
	 * @author Harikrishna - RMITS
	 * @param TDSSection
	 */
	public void chooseTDSInBP(String TDSSection) {
		flag = webDB.waitForElement(BankPaymentLocators.BankPaymentTDSField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BankPaymentTDSField, ElementType.Xpath);
			logs.logging("INFO", "Bank Payment Line Wise TDS Field Is Clicked Successfully");
			commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.BankPaymentTDSLovButton, TDSSection);
			webDB.pressTabKey();
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(BankPaymentLocators.BankPaymentSourcePartyField, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(BankPaymentLocators.BankPaymentSourcePartyField, ElementType.Xpath);
				logs.logging("INFO", "Bank Payment Line Wise Source Party Field Is Clicked Successfully");
				commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.BankPaymentSourcePartyLovButton,
						commonFunc.getDataFromTestDataSheet("POSupplierName"));
				webDB.pressTabKey();
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Apply Deduction Button In The Header
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickApplyDeductionButtonInHeader() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElementUntilGivenTime(BankPaymentLocators.BankPaymentApplyDeduction, ElementType.Xpath, 10);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BankPaymentApplyDeduction, ElementType.Xpath);
			commonFunc.hardWait(200);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(BankPaymentLocators.BankPayment2ndLine, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Apply Deduction Button Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click Line Wise GST Icon In BR
	 * 
	 * @author Harikrishna - RMITS
	 * @param i
	 */
	public void clickLineWiseGSTIconInBP(int i) {
		flag = webDB.waitForElement(BankPaymentLocators.BankLineGSTButton(i), ElementType.CssSelector);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BankLineGSTButton(i), ElementType.CssSelector);
			flag = webDB.waitForElement(BankReceiptLocators.BankReceiptGSTDetailsHeaderName, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Line Wise GST Details Button Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Enter Values in The GST Screen
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
										flag = webDB.waitForElement(BankPaymentLocators.CreateJournal,
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
	 * This Method Is used To Add The Bank Receipt Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return
	 */
	public void BankPaymentThroughGetDocumentWithCharges(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		getBankPaymentOrReceiptDocumentNumber();
		checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyBankPaymentHeaderDetailsWithUI(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
		verifyBankPaymentDetailsWithDB(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		clickGetDocumentInBPHeader();
		clickPendingPartyDocumentsInBP();
		choosePartyNameAndClickSearchButtonInPendingPartyDocs(VariableHandler.getMultiplePBDocs(), SheetName);
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String PartyName = BankPayments[i][3];
			String EntityName = BankPayments[i][5];
			String CPCCodeDesc = BankPayments[i][6];
			String BillNumber = BankPayments[i][7];
			String PayAmount = BankPayments[i][8];
			String DRCR = BankPayments[i][9];
			String CURCY = BankPayments[i][10];
			String EXRate = BankPayments[i][11];
			String TransCURCY = BankPayments[i][12];
			String BaseCURCY = BankPayments[i][13];
			String AssesableValue = BankPayments[i][16];
			String ChargesFlag = BankPayments[i][29];
			if (!GLAccountDesc.equals("")) {
				if (ChargesFlag.equals("Y")) {
					clickBPLineAddButton();
					addBankPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
					enterValueInAmtInTCField(PayAmount);
					chooseLineWiseDRCRInBP(commonFunc.getDataFromTestDataSheet("DrValue"));
					clickChargesFlagInBP();
					clickLineWiseSaveButton();
					if (!CURCY.equals("INR")) {
						excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
								Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
					} else {
						excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
					}
				} else {
					if (!Currency.equals("INR")) {
						excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
								Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
					} else {
						if (BankName.equals("JP MORGAN CHASE")) {
							excelUtils.writeDataInExcel(BPFilepath, SheetName, 2, 11,
									dbCommonFunctions.ExchangeRateDetails("USD",
											commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
						} else {
							excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
						}
					}
				}
				verifyBankPaymentsLineDetailsWithDB(SheetName, i);
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBPHeader(BaseCURCY);
			}
			if (Currency.equals("INR")) {
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalBank"), 1, 6,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalBankUSD"), 1, 6,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		clickCreateJournalButton();
		if (Currency.equals("INR")) {
			if (BankName.equals("JP MORGAN CHASE")) {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalBankINR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalBankINR"), 1, 6, "0");
			} else {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalBank"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalBank"), 1, 6, "0");
			}
		} else {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalBankUSD"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalBankUSD"), 1, 6, "0");
		}
//		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
//			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Select The Line Wise Charges Flag In Bank Receipt
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickChargesFlagInBP() {
		flag = webDB.waitForElement(BankPaymentLocators.BankPaymentChargesFlag, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(BankPaymentLocators.BankPaymentChargesInputFlag, ElementType.Xpath);
			webDB.pressTabKey();
			if (webDB.isElementSelected(BankPaymentLocators.BankPaymentChargesInputFlag, ElementType.Xpath)) {
				logs.logging("INFO", "Line Wise Charges Flag Is Selected Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Create BR SPSI Through pending Docs
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 * @throws IOException
	 */
	public void BankPaymentWithCharges(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
						BPGrnExcelFileName);
			}
		}
		for (int i = 1; i <= VariableHandler.getMultipleGRNDocs().size(); i++) {
//			createPurchaseBillsFromPendingGRN(VariableHandler.getMultipleGRNDocs().get(i - 1));
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(0),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number is : " + VariableHandler.getPbNumber());
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 25 + i,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i - 1),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BankPaymentThroughGetDocumentWithCharges(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public void BankPaymentWithChargesUSD(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeImportSupplierHK(SupplierID, GRNSheetName,
						BPGrnExcelFileName);
			}
		}
		for (int i = 1; i <= VariableHandler.getMultipleGRNDocs().size(); i++) {
//			createPurchaseBillsFromPendingGRN(VariableHandler.getMultipleGRNDocs().get(i - 1));
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(0),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number is : " + VariableHandler.getPbNumber());
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 25 + i,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i - 1),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BankPaymentThroughGetDocumentWithCharges(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is used To Add The Bank Receipt Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return
	 */
	public void BankPaymentWithExpAndRevenueWithGSTAndTDS(String SheetName, String Currency) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"),
				commonFunc.getDataFromTestDataSheet("BankName"), Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		getBankPaymentOrReceiptDocumentNumber();
		checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyBankPaymentHeaderDetailsWithUI(commonFunc.getDataFromTestDataSheet("BankName"),
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
		verifyBankPaymentDetailsWithDB(commonFunc.getDataFromTestDataSheet("BankName"),
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String PartyName = BankPayments[i][3];
			String EntityName = BankPayments[i][5];
			String CPCCodeDesc = BankPayments[i][6];
			String BillNumber = BankPayments[i][7];
			String PayAmount = BankPayments[i][8];
			String DRCR = BankPayments[i][9];
			String CURCY = BankPayments[i][10];
			String EXRate = BankPayments[i][11];
			String TransCURCY = BankPayments[i][12];
			String BaseCURCY = BankPayments[i][13];
			String HSNCode = BankPayments[i][14];
			String AssesableValue = BankPayments[i][16];
			String LineWiseDRCR = BankPayments[i][25];
			String ChargesFlag = BankPayments[i][29];
			String StateCode = BankPayments[i][30];
			String GSTSupplierName = BankPayments[i][31];
			String GSTIN = BankPayments[i][32];
			if (!GLAccountDesc.equals("")) {
				if (i <= 1) {
					clickBPLineAddButton();
					addBankPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
					enterValueInAmtInTCField(PayAmount);
					chooseLineWiseDRCRInBP(LineWiseDRCR);
					chooseHSNCodeInBP(HSNCode);
					chooseTDSInBP(commonFunc.getDataFromTestDataSheet("TDSSection"));
					clickLineWiseSaveButton();
					clickLineWiseGSTIconInBP(i);
					enterValuesInGSTScreen(StateCode, GSTSupplierName, GSTIN, Today, BillNumber);
					flag = webDB.waitForElementUntilGivenTime(BankPaymentLocators.BankPaymentFavorOfInputField,
							ElementType.Xpath, 10);
					if (flag) {
						webDB.sendTextToAnElement(BankPaymentLocators.BankPaymentFavorOfInputField, "HARI TRADERS",
								ElementType.Xpath);
					}
					clickApplyDeductionButtonInHeader();
					if (!Currency.equals("INR")) {
						excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
								Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
					} else {
						excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
					}
				}
				verifyBankPaymentsLineDetailsWithDB(SheetName, i);
			}
			if (!Currency.equals("INR")) {
				enterRealizedAmountInBPHeader(BaseCURCY);
			}
		}
		clickCreateJournalButton();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalREVGSTTDS"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
//			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughLineAddButtonSingleBillsBankCurcyUSD(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
						BPGrnExcelFileName);
			}
		}
		for (int i = 1; i <= VariableHandler.getMultipleGRNDocs().size(); i++) {
//			createPurchaseBillsFromPendingGRN(VariableHandler.getMultipleGRNDocs().get(i - 1));
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(0),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number is : " + VariableHandler.getPbNumber());
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 25 + i,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i - 1),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BPThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughGetDocumentSingleBillsBankCurcyUSD(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
						BPGrnExcelFileName);
			}
		}
		for (int i = 1; i <= VariableHandler.getMultipleGRNDocs().size(); i++) {
//			createPurchaseBillsFromPendingGRN(VariableHandler.getMultipleGRNDocs().get(i - 1));
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(0),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number is : " + VariableHandler.getPbNumber());
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 25 + i,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i - 1),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BPThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughPendingDocsSingleBillsBankCurcyUSD(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
						BPGrnExcelFileName);
			}
		}
		for (int i = 1; i <= VariableHandler.getMultipleGRNDocs().size(); i++) {
//			createPurchaseBillsFromPendingGRN(VariableHandler.getMultipleGRNDocs().get(i - 1));
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(0),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number is : " + VariableHandler.getPbNumber());
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 25 + i,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i - 1),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BPThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughLineAddButtonMultiBillsBankCurcyUSD(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i <= 2; i++) {
			String SupplierID = BankPayments[1][2];
			String GRNSheetName = BankPayments[1][4];
			Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
					BPGrnExcelFileName);
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BPThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughGetDocumentMultiBillsBankCurcyUSD(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i <= 2; i++) {
			String SupplierID = BankPayments[1][2];
			String GRNSheetName = BankPayments[1][4];
			Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
					BPGrnExcelFileName);
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BPThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		return flag;
	}

	/**
	 * This Method Is Used To Create BP Through Pending Docs Multi Bills
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughPendingDocsMultiBillsBankCurcyUSD(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i <= 2; i++) {
			String SupplierID = BankPayments[1][2];
			String GRNSheetName = BankPayments[1][4];
			Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
					BPGrnExcelFileName);
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BPThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Add Button Multi Party
	 * Single Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughLineAddButtonMultiPartySingleBillBankCurcyUSD(String SheetName)
			throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
						BPGrnExcelFileName);
			}
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, i + 1, 25 + (i + 1),
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BPThroughAddButtonMultiPartySingleBills(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Multi Party
	 * Single Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughGetDocumentMultiPartySingleBillBankCurcyUSD(String SheetName)
			throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
						BPGrnExcelFileName);
			}
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, i + 1, 25 + (i + 1),
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BPThroughGetDocumentMPSI(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Multi Party
	 * Single Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughPendingDocumentMultiPartySingleBillBankCurcyUSD(String SheetName)
			throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
						BPGrnExcelFileName);
			}
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, i + 1, 25 + (i + 1),
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BPThroughPendingDocsMPSI(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Add Button Multi Party
	 * Single Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughLineAddButtonMultiPartyMultiBillBankCurcyUSD(String SheetName)
			throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
							BPGrnExcelFileName);
				}
			}
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
		}
		for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
			if (j <= 2) {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		BPThroughAddButtonMultiPartyMultiBills(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Multi Party
	 * Single Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughGetDocumentMultiPartyMultiBillBankCurcyUSD(String SheetName)
			throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
							BPGrnExcelFileName);
				}
			}
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
		}
		for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
			if (j <= 2) {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		BPThroughGetDocumentMPMB(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Multi Party
	 * Single Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughPendingMultiPartyMultiBillBankCurcyUSD(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
							BPGrnExcelFileName);
				}
			}
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
		}
		for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
			if (j <= 2) {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		BPThroughPendingDocsMPMB(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Multi Party
	 * Single Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughAddButtonMultiPartyMultiBillWithNewRefBankCurcyUSD(String SheetName)
			throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
							BPGrnExcelFileName);
				}
			}
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
		}
		for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
			if (j <= 2) {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		BPThroughAddButtonWithNewRef(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Multi Party
	 * Single Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughGetDocumentMultiPartyMultiBillWithNewRefBankCurcyUSD(String SheetName)
			throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
							BPGrnExcelFileName);
				}
			}
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
		}
		for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
			if (j <= 2) {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		BPThroughGetDocumentWithNewReference(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Multi Party
	 * Single Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateBankPaymentThroughPendingDocumentMultiPartyMultiBillBankCurcyUSD(String SheetName)
			throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
							BPGrnExcelFileName);
				}
			}
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
		}
		for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
			if (j <= 2) {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		BPThroughPendingDocsWithNewReference(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
		return flag;
	}

	/**
	 * This Method Is Used To Create BR SPSI Through pending Docs
	 * 
	 * @author Harikrishna -RMITS
	 * @param SheetName
	 * @throws IOException
	 */
	public void BankPaymentWithChargesBankCurcyUSD(String SheetName) throws IOException {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String GRNSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
						BPGrnExcelFileName);
			}
		}
		for (int i = 1; i <= VariableHandler.getMultipleGRNDocs().size(); i++) {
//			createPurchaseBillsFromPendingGRN(VariableHandler.getMultipleGRNDocs().get(i - 1));
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(0),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number is : " + VariableHandler.getPbNumber());
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 25 + i,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i - 1),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		BankPaymentThroughGetDocumentWithCharges(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
	}

	/**
	 * This Method Is Used To Verify The GLM Control PDC Payment Date
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void verifyGLMCntrlPDCPaymentDate(String BankName, String Currency) {

		String PDCPaymentDate = dbCommonFunctions.GLMControl(commonFunc.getDataFromTestDataSheet("DBBU"),
				commonFunc.getDataFromTestDataSheet("GLMPaymentPDCControlColumnName"));
		logs.logging("INFO", "Post Dated Cheque Payment Date Control Is : " + PDCPaymentDate);

		if (!PDCPaymentDate.equals("C")) {
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
		} else {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("PostedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			String PDCRowCount = dbCommonFunctions.PDCCount(VariableHandler.getBankPaymentOrReceiptDocNumber(),
					commonFunc.getDataFromTestDataSheet("RowCountColumnName"));
			int rowCount = Integer.parseInt(PDCRowCount);
			if (rowCount > 0) {
				logs.logging("INFO",
						"Post Dated Cheque Payment Date Control Is " + PDCPaymentDate
								+ " In The Post Dated Cheque Screen Against This Bank Payment Voucher "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber() + "  " + PDCRowCount
								+ " Record Is Inserted");
				commonFunc.navigateToPostDatedCheque();
				commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.PDCVoucherNumberLovButton,
						VariableHandler.getBankPaymentOrReceiptDocNumber());
				flag = webDB.waitForElement(BankPaymentLocators.PDCSearchButton, ElementType.Xpath);
				if (flag) {
					webDB.doubleClickAnElement(BankPaymentLocators.PDCSearchButton, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					logs.logging("INFO", "In Post Dated Cheque Screen Search Button Is Clicked Successfully");
					flag = webDB.waitForElement(
							BankPaymentLocators.PDCSelectCheckBox(VariableHandler.getBankPaymentOrReceiptDocNumber()),
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Post Dated Cheque Record is Shown Against This Bank Payment Voucher "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());
						webDB.clickAnElement(BankPaymentLocators.PDCSelectCheckBox(
								VariableHandler.getBankPaymentOrReceiptDocNumber()), ElementType.Xpath);
						flag = webDB.waitForElement(BankPaymentLocators.PDCPOstButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"In Post Dated Cheque Against This Bank Payment Voucher "
											+ VariableHandler.getBankPaymentOrReceiptDocNumber()
											+ " Record Is selected Sucessfully");
							webDB.clickAnElement(BankPaymentLocators.PDCPOstButton, ElementType.Xpath);
							commonFunc.hardWait(500);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(500);
							checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The Bank Payment Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPAdvNewRefWithAndWithoutTDS(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		getBankPaymentOrReceiptDocumentNumber();
		checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyBankPaymentHeaderDetailsWithUI(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
		verifyBankPaymentDetailsWithDB(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String PartyName = BankPayments[i][3];
			String EntityName = BankPayments[i][5];
			String CPCCodeDesc = BankPayments[i][6];
			String BillNumber = BankPayments[i][7];
			String PayAmount = BankPayments[i][8];
			String DRCR = BankPayments[i][9];
			String CURCY = BankPayments[i][10];
			String EXRate = BankPayments[i][11];
			String TransCURCY = BankPayments[i][12];
			String BaseCURCY = BankPayments[i][13];
			String AssesableValue = BankPayments[i][16];
			String TDS = BankPayments[i][29];
			if (!TDS.equals("")) {
				clickBPLineAddButton();
				addBankPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
				clickLineWiseSaveButton();
				clickLineWiseViewButton(i);
				clickAddButtonInViewDetails();
				addBillNumberAndPayAmount(BillNumber, PayAmount);
				clickViewDetailsSaveButton();
				clickBankViewDetailsCloseButton();
				if (SheetName.contains("TDS")) {
					chooseLineWiseTDS(TDS, i);
					clickApplyDeductionButton();
				}
			}
			verifyBankPaymentsLineDetailsWithDB(SheetName, i);
		}
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int j = 1; j < BankPayments.length; j++) {
			String TDS = BankPayments[j][29];
			String PayAmount = BankPayments[j][8];
			if (!TDS.equals("")) {
				if (SheetName.contains("TDS")) {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADVWithNewRef"), 1,
							6, PayAmount);
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADVWithNewRef"), 2,
							6, PayAmount);
				} else {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVWithNewRef"), 1, 6,
							PayAmount);
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVWithNewRef"), 2, 6,
							PayAmount);
				}
			}
		}
		clickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		if (SheetName.contains("TDS")) {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("TDSBPJournalADVWithNewRef"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADVWithNewRef"), 1, 6,
					"0");
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADVWithNewRef"), 2, 6,
					"0");
			clearDataInExcel(SheetName);
		} else {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalADVWithNewRef"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVWithNewRef"), 1, 6, "0");
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVWithNewRef"), 2, 6, "0");
			clearDataInExcel(SheetName);
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The Bank Payment Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPAdvNewRefWithMultiTDS(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		getBankPaymentOrReceiptDocumentNumber();
		checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyBankPaymentHeaderDetailsWithUI(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
		verifyBankPaymentDetailsWithDB(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String POSheetName = BankPayments[i][4];
			String PartyName = BankPayments[i][3];
			String EntityName = BankPayments[i][5];
			String CPCCodeDesc = BankPayments[i][6];
			String BillNumber = BankPayments[i][7];
			String PayAmount = BankPayments[i][8];
			String DRCR = BankPayments[i][9];
			String CURCY = BankPayments[i][10];
			String EXRate = BankPayments[i][11];
			String TransCURCY = BankPayments[i][12];
			String BaseCURCY = BankPayments[i][13];
			String AssesableValue = BankPayments[i][16];
			String TDS = BankPayments[i][29];
			if (!POSheetName.equals("")) {
				clickBPLineAddButton();
				addBankPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
				clickLineWiseSaveButton();
				clickLineWiseViewButton(i);
				clickAddButtonInViewDetails();
				addBillNumberAndPayAmount(BillNumber, PayAmount);
				clickViewDetailsSaveButton();
				clickBankViewDetailsCloseButton();
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
				}
				chooseLineWiseTDS(TDS, i);
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADVMultiNewref"), i, 6,
						PayAmount);
			}
		}
		clickApplyDeductionButton();
		for (int k = 1; k < BankPayments.length; k++) {
			String GLAccountDesc1 = BankPayments[k][1];
			String BaseCURCY1 = BankPayments[k][13];
			if (!GLAccountDesc1.equals("")) {
				verifyBankPaymentsLineDetailsWithDB(SheetName, k);
			}
		}
		clickCreateJournalButton();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("TDSBPJournalADVMultiNewref"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
		excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADVMultiNewref"), 1, 6, "0");
		excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADVMultiNewref"), 2, 6, "0");
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
		}
		assertTrue(flag);
	}

	/**
	 * This Methos Is Used To Click The The Line Wise TDS Field And Choose The TDS
	 * Under Section
	 * 
	 * @author Harikrishna - RMITS
	 * @param TDS
	 */
	public void chooseLineWiseTDS(String TDS, int i) {
		flag = webDB.waitForElement(BankPaymentLocators.LineWiseTDS(i), ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(BankPaymentLocators.LineWiseTDS(i), ElementType.Xpath);
			flag = webDB.waitForElement(BankPaymentLocators.BankPaymentTDSLovButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Line Wise TDS Field Is Clicked Sucessfully");
				commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.BankPaymentTDSLovButton, TDS);
				webDB.pressTabKey();
				flag = webDB.waitForElement(BankPaymentLocators.BankLineWiseSaveButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankPaymentLocators.BankLineWiseSaveButton, ElementType.Xpath);
					logs.logging("INFO", "Bank Line Wise Save Button Is Clicked Successfully");
					commonFunc.checkRefreshCellsDisappear();
				}
			}
		}
	}

	/**
	 * This Methos Is Used To Click The Apply Deduction Button In The Bank Payment
	 * Header
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickApplyDeductionButton() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(BankPaymentLocators.BankPaymentApplyDeduction, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BankPaymentApplyDeduction, ElementType.Xpath);
			flag = webDB.waitForElement(BankPaymentLocators.BankPaymentApplyDeductionCheckedButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "In Bank Payment Apply Deduction Button Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The Bank Payment Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPAdvWithNewRef(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		getBankPaymentOrReceiptDocumentNumber();
		checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyBankPaymentHeaderDetailsWithUI(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
		verifyBankPaymentDetailsWithDB(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String PartyName = BankPayments[i][3];
			String EntityName = BankPayments[i][5];
			String CPCCodeDesc = BankPayments[i][6];
			String BillNumber = BankPayments[i][7];
			String PayAmount = BankPayments[i][8];
			String DRCR = BankPayments[i][9];
			String CURCY = BankPayments[i][10];
			String EXRate = BankPayments[i][11];
			String TransCURCY = BankPayments[i][12];
			String BaseCURCY = BankPayments[i][13];
			String AssesableValue = BankPayments[i][16];
			if (!GLAccountDesc.equals("")) {
				clickBPLineAddButton();
				addBankPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
				clickLineWiseSaveButton();
				clickLineWiseViewButton(i);
				clickAddButtonInViewDetails();
				addBillNumberAndPayAmount(BillNumber, PayAmount);
				clickViewDetailsSaveButton();
				clickBankViewDetailsCloseButton();
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
				}
				verifyBankPaymentsLineDetailsWithDB(SheetName, i);
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBPHeader(BaseCURCY);
			}
		}
		clickCreateJournalButton();
		if (Currency.equals("INR")) {
			if (BankName.equals("JP MORGAN CHASE")) {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalINRAdvNR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
			} else {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BRAdvJournalNR"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
			}
		} else {
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPAdvJournalUSDNR"), 0, 10,
					dbCommonFunctions.ExchangeRateDetails(Currency,
							commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPAdvJournalUSDNR"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Create Advance PO Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPadvancePOThroughAddButton(String SheetName) {
		PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK();
		cashPymtFunc.getAdvPaymentDocumentFromDataBase();
		VariableHandler.setMultipleAdvPymtDocs(cashPymtFunc.MultiAdvPaymentDocumentNumber);
		excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 26, dbCommonFunctions.POAdvanceDetails(
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		BPAdvThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create Advance PO Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPadvancePOThroughGetDocument(String SheetName) {
		PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK();
		cashPymtFunc.getAdvPaymentDocumentFromDataBase();
		VariableHandler.setMultipleAdvPymtDocs(cashPymtFunc.MultiAdvPaymentDocumentNumber);
		excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 26, dbCommonFunctions.POAdvanceDetails(
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		BPAdvThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create Advance PO Through Pending Documents
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPadvancePOThroughPendingDocument(String SheetName) {
		PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK();
		cashPymtFunc.getAdvPaymentDocumentFromDataBase();
		VariableHandler.setMultipleAdvPymtDocs(cashPymtFunc.MultiAdvPaymentDocumentNumber);
		excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 26, dbCommonFunctions.POAdvanceDetails(
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		BPAdvThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"), commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create Multiple Advance PO Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPadvancePOMultipleThroughAddButton(String SheetName) {
		for (int i = 0; i < 2; i++) {
			PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK();
			cashPymtFunc.getAdvPaymentDocumentFromDataBase();
			excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(i),
							commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		}
		VariableHandler.setMultipleAdvPymtDocs(cashPymtFunc.MultiAdvPaymentDocumentNumber);
		BPAdvThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create Multiple Advance PO Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPadvancePOMultipleThroughGetDocument(String SheetName) {
		for (int i = 0; i < 2; i++) {
			PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK();
			cashPymtFunc.getAdvPaymentDocumentFromDataBase();
			excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(i),
							commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		}
		VariableHandler.setMultipleAdvPymtDocs(cashPymtFunc.MultiAdvPaymentDocumentNumber);
		BPAdvThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create Multiple Advance PO Through Pending Documents
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPadvancePOMultipleThroughPendingDocument(String SheetName) {
		for (int i = 0; i < 2; i++) {
			PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK();
			cashPymtFunc.getAdvPaymentDocumentFromDataBase();
			excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(i),
							commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		}
		VariableHandler.setMultipleAdvPymtDocs(cashPymtFunc.MultiAdvPaymentDocumentNumber);
		BPAdvThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"), commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create Multiple Advance PO Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPadvancePOMultipleThroughAddButtonWithMultipleTDS(String SheetName) {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String POSheetName = BankPayments[i][4];
			if (!POSheetName.equals("")) {
				PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK1(SupplierID,
						POSheetName);
				cashPymtFunc.getAdvPaymentDocumentFromDataBase();
				excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 26,
						dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(i - 1),
								commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
			}
		}
		VariableHandler.setMultipleAdvPymtDocs(cashPymtFunc.MultiAdvPaymentDocumentNumber);
		BPAdvThroughAddButtonWithMultiTDS(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create Multiple Advance PO Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPadvancePOMultipleThroughGetDocumentWithMultipleTDS(String SheetName) {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String POSheetName = BankPayments[i][4];
			if (!POSheetName.equals("")) {
				PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK1(SupplierID,
						POSheetName);
				cashPymtFunc.getAdvPaymentDocumentFromDataBase();
				excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 26,
						dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(i - 1),
								commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
			}
		}
		VariableHandler.setMultipleAdvPymtDocs(cashPymtFunc.MultiAdvPaymentDocumentNumber);
		BPAdvThroughGetDocumentWithMultiTDS(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create Multiple Advance PO Through Pending Documents
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPadvancePOMultipleThroughPendingDocumentWithMultipleTDS(String SheetName) {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String POSheetName = BankPayments[i][4];
			if (!POSheetName.equals("")) {
				PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK1(SupplierID,
						POSheetName);
				cashPymtFunc.getAdvPaymentDocumentFromDataBase();
				excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 26,
						dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(i - 1),
								commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
			}
		}
		VariableHandler.setMultipleAdvPymtDocs(cashPymtFunc.MultiAdvPaymentDocumentNumber);
		BPAdvThroughPendingDocsWithMultiTDS(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"), commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create Advance PO Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPadvancePOThroughAddButtonBankCurcyUSD(String SheetName) {
		PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK();
		cashPymtFunc.getAdvPaymentDocumentFromDataBase();
		VariableHandler.setMultipleAdvPymtDocs(cashPymtFunc.MultiAdvPaymentDocumentNumber);
		excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 26, dbCommonFunctions.POAdvanceDetails(
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		BPAdvThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
	}

	/**
	 * This Method Is Used To Create Advance PO Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPadvancePOThroughGetDocumentBankCurcyUSD(String SheetName) {
		PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK();
		cashPymtFunc.getAdvPaymentDocumentFromDataBase();
		VariableHandler.setMultipleAdvPymtDocs(cashPymtFunc.MultiAdvPaymentDocumentNumber);
		excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 26, dbCommonFunctions.POAdvanceDetails(
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		BPAdvThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
	}

	/**
	 * This Method Is Used To Create Advance PO Through Pending Documents
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPadvancePOThroughPendingDocumentBankCurcyUSD(String SheetName) {
		PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK();
		cashPymtFunc.getAdvPaymentDocumentFromDataBase();
		VariableHandler.setMultipleAdvPymtDocs(cashPymtFunc.MultiAdvPaymentDocumentNumber);
		excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 26, dbCommonFunctions.POAdvanceDetails(
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		BPAdvThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
	}

	/**
	 * This Method Is Used To Create Multiple Advance PO Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPadvancePOMultipleThroughAddButtonBankCurcyUSD(String SheetName) {
		for (int i = 0; i < 2; i++) {
			PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK();
			cashPymtFunc.getAdvPaymentDocumentFromDataBase();
			excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(i),
							commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		}
		VariableHandler.setMultipleAdvPymtDocs(cashPymtFunc.MultiAdvPaymentDocumentNumber);
		BPAdvThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
	}

	/**
	 * This Method Is Used To Create Multiple Advance PO Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPadvancePOMultipleThroughGetDocumentBankCurcyUSD(String SheetName) {
		for (int i = 0; i < 2; i++) {
			PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK();
			cashPymtFunc.getAdvPaymentDocumentFromDataBase();
			excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(i),
							commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		}
		VariableHandler.setMultipleAdvPymtDocs(cashPymtFunc.MultiAdvPaymentDocumentNumber);
		BPAdvThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
	}

	/**
	 * This Method Is Used To Create Multiple Advance PO Through Pending Documents
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPadvancePOMultipleThroughPendingDocumentBankCurcyUSD(String SheetName) {
		for (int i = 0; i < 2; i++) {
			PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK();
			cashPymtFunc.getAdvPaymentDocumentFromDataBase();
			excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(i),
							commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		}
		VariableHandler.setMultipleAdvPymtDocs(cashPymtFunc.MultiAdvPaymentDocumentNumber);
		BPAdvThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("BankNameUSD"));
	}

	/**
	 * This Method Is Used To Create Advance PO Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPadvancePOThroughAddButtonUSD(String SheetName) {
		PurFunc.verifyDirectPOImportSimilarUOMWithSingleItemWithExcelSheetHK();
		cashPymtFunc.getAdvPaymentDocumentFromDataBase();
		VariableHandler.setMultipleAdvPymtDocs(cashPymtFunc.MultiAdvPaymentDocumentNumber);
		excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 26, dbCommonFunctions.POAdvanceDetails(
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		BPAdvThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create Advance PO Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPadvancePOThroughGetDocumentUSD(String SheetName) {
		PurFunc.verifyDirectPOImportSimilarUOMWithSingleItemWithExcelSheetHK();
		cashPymtFunc.getAdvPaymentDocumentFromDataBase();
		VariableHandler.setMultipleAdvPymtDocs(cashPymtFunc.MultiAdvPaymentDocumentNumber);
		excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 26, dbCommonFunctions.POAdvanceDetails(
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		BPAdvThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create Advance PO Through Pending Documents
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPadvancePOThroughPendingDocumentUSD(String SheetName) {
		PurFunc.verifyDirectPOImportSimilarUOMWithSingleItemWithExcelSheetHK();
		cashPymtFunc.getAdvPaymentDocumentFromDataBase();
		VariableHandler.setMultipleAdvPymtDocs(cashPymtFunc.MultiAdvPaymentDocumentNumber);
		excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 26, dbCommonFunctions.POAdvanceDetails(
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		BPAdvThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create Multiple Advance PO Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPadvancePOMultipleThroughAddButtonUSD(String SheetName) {
		for (int i = 0; i < 2; i++) {
			PurFunc.verifyDirectPOImportSimilarUOMWithSingleItemWithExcelSheetHK();
			cashPymtFunc.getAdvPaymentDocumentFromDataBase();
			excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(i),
							commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		}
		VariableHandler.setMultipleAdvPymtDocs(cashPymtFunc.MultiAdvPaymentDocumentNumber);
		BPAdvThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create Multiple Advance PO Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPadvancePOMultipleThroughGetDocumentUSD(String SheetName) {
		for (int i = 0; i < 2; i++) {
			PurFunc.verifyDirectPOImportSimilarUOMWithSingleItemWithExcelSheetHK();
			cashPymtFunc.getAdvPaymentDocumentFromDataBase();
			excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(i),
							commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		}
		VariableHandler.setMultipleAdvPymtDocs(cashPymtFunc.MultiAdvPaymentDocumentNumber);
		BPAdvThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create Multiple Advance PO Through Pending Documents
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPadvancePOMultipleThroughPendingDocumentUSD(String SheetName) {
		for (int i = 0; i < 2; i++) {
			PurFunc.verifyDirectPOImportSimilarUOMWithSingleItemWithExcelSheetHK();
			cashPymtFunc.getAdvPaymentDocumentFromDataBase();
			excelUtils.writeDataInExcel(BPFilepath, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(i),
							commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		}
		VariableHandler.setMultipleAdvPymtDocs(cashPymtFunc.MultiAdvPaymentDocumentNumber);
		BPAdvThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcyUSD"),
				commonFunc.getDataFromTestDataSheet("BankName"));
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Multi Party
	 * Single Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws Exception
	 */
	public boolean CreateBankPaymentThroughAddButtonMultiPartyMultiBillAdvanceWithNewRef(String SheetName,
			String Currency, String BankName) throws Exception {
		int docIndex = 0; // global counter
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String POSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK1(SupplierID,
							POSheetName);
					cashPymtFunc.getAdvPaymentDocumentFromDataBase();
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 26 + i1,
							dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(docIndex),
									commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 28 + i1,
							dbCommonFunctions.PurchaseOrderHeaderDetails(
									VariableHandler.getMultiplePODocs().get(docIndex),
									commonFunc.getDataFromTestDataSheet("POTotalAmount")));
					docIndex++; // ✅ increment after every use
				}
			}
		}
		VariableHandler.setMultipleAdvPymtDocs(cashPymtFunc.MultiAdvPaymentDocumentNumber);
		BPAdvThroughAddButtonWithMPMB(SheetName, Currency, BankName);
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.naviagteGrnPage();
		for (int i = 0; i < VariableHandler.getMultiplePODocs().size(); i++) {
			grnPageFunc.PendPO(VariableHandler.getMultiplePODocs().get(i));
			Grnfunc.clickGenLotSerialButtonInHeader();
			if (Currency.equals("USD")) {
				grnPageFunc.verifyImportLrBillNumber();
			}
			grnPageFunc.clickPostButton();
			MultiGRNDocs.add(VariableHandler.getGrnDocNumber());// TODO
			grnPageFunc.waitingForApprovalHK(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
			grnPageFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		}
		VariableHandler.setMultipleGRNDocs(MultiGRNDocs);
		System.out.println(VariableHandler.getMultipleGRNDocs().get(0));
		for (int j = 0; j < VariableHandler.getMultipleGRNDocs().size(); j++) {
			VariableHandler.getMultipleGRNDocs().get(j);
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(j),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(j));
		}
		VariableHandler.setMultiplePBDocs(PBDocumentNumber);
		BPThroughAddButtonAdvanceWithNewRef(SheetName, Currency, BankName);
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Multi Party
	 * Single Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws Exception
	 */
	public boolean CreateBankPaymentThroughGetDocumentMultiPartyMultiBillAdvanceWithNewRef(String SheetName,
			String Currency, String BankName) throws Exception {
		int docIndex = 0; // global counter
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String POSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK1(SupplierID,
							POSheetName);
					cashPymtFunc.getAdvPaymentDocumentFromDataBase();
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 26 + i1,
							dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(docIndex),
									commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 28 + i1,
							dbCommonFunctions.PurchaseOrderHeaderDetails(
									VariableHandler.getMultiplePODocs().get(docIndex),
									commonFunc.getDataFromTestDataSheet("POTotalAmount")));
					docIndex++; // ✅ increment after every use
				}
			}
		}
		VariableHandler.setMultipleAdvPymtDocs(cashPymtFunc.MultiAdvPaymentDocumentNumber);
		BPAdvThroughAddButtonWithMPMB(SheetName, Currency, BankName);
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.naviagteGrnPage();
		for (int i = 0; i < VariableHandler.getMultiplePODocs().size(); i++) {
			grnPageFunc.PendPO(VariableHandler.getMultiplePODocs().get(i));
			Grnfunc.clickGenLotSerialButtonInHeader();
			if (Currency.equals("USD")) {
				grnPageFunc.verifyImportLrBillNumber();
			}
			grnPageFunc.clickPostButton();
			MultiGRNDocs.add(VariableHandler.getGrnDocNumber());// TODO
			grnPageFunc.waitingForApprovalHK(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
			grnPageFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		}
		VariableHandler.setMultipleGRNDocs(MultiGRNDocs);
		System.out.println(VariableHandler.getMultipleGRNDocs().get(0));
		for (int j = 0; j < VariableHandler.getMultipleGRNDocs().size(); j++) {
			VariableHandler.getMultipleGRNDocs().get(j);
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(j),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(j));
		}
		VariableHandler.setMultiplePBDocs(PBDocumentNumber);
		BPThroughGetDocumentAdvanceWithNewReference(SheetName, Currency, BankName);
		return flag;
	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Get Document Multi Party
	 * Single Bill
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws Exception
	 */
	public boolean CreateBankPaymentThroughPendingDocumentMultiPartyMultiBillAdvanceWithNewRef(String SheetName,
			String Currency, String BankName) throws Exception {
		int docIndex = 0; // global counter
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String SupplierID = BankPayments[i][2];
			String POSheetName = BankPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK1(SupplierID,
							POSheetName);
					cashPymtFunc.getAdvPaymentDocumentFromDataBase();
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 26 + i1,
							dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(docIndex),
									commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 28 + i1,
							dbCommonFunctions.PurchaseOrderHeaderDetails(
									VariableHandler.getMultiplePODocs().get(docIndex),
									commonFunc.getDataFromTestDataSheet("POTotalAmount")));
					docIndex++; // ✅ increment after every use
				}
			}
		}
		VariableHandler.setMultipleAdvPymtDocs(cashPymtFunc.MultiAdvPaymentDocumentNumber);
		BPAdvThroughAddButtonWithMPMB(SheetName, Currency, BankName);
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.naviagteGrnPage();
		for (int i = 0; i < VariableHandler.getMultiplePODocs().size(); i++) {
			grnPageFunc.PendPO(VariableHandler.getMultiplePODocs().get(i));
			Grnfunc.clickGenLotSerialButtonInHeader();
			if (Currency.equals("USD")) {
				grnPageFunc.verifyImportLrBillNumber();
			}
			grnPageFunc.clickPostButton();
			MultiGRNDocs.add(VariableHandler.getGrnDocNumber());// TODO
			grnPageFunc.waitingForApprovalHK(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
			grnPageFunc.aafterVerifyTheStatusDocumentMoveToQcOrMrv();
		}
		VariableHandler.setMultipleGRNDocs(MultiGRNDocs);
		System.out.println(VariableHandler.getMultipleGRNDocs().get(0));
		for (int j = 0; j < VariableHandler.getMultipleGRNDocs().size(); j++) {
			VariableHandler.getMultipleGRNDocs().get(j);
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(j),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(j));
		}
		VariableHandler.setMultiplePBDocs(PBDocumentNumber);
		BPThroughPendingDocsAdvanceWithNewReference(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				Currency, BankName);
		return flag;
	}

	/**
	 * This Method Is Used To Add The Bank Payment Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPAdvThroughAddButton(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		getBankPaymentOrReceiptDocumentNumber();
		checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyBankPaymentHeaderDetailsWithUI(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
		verifyBankPaymentDetailsWithDB(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		if (!SheetName.contains("TDS")) {
			BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
			for (int i = 1; i < BankPayments.length; i++) {
				String GLAccountDesc = BankPayments[i][1];
				String PartyName = BankPayments[i][3];
				String EntityName = BankPayments[i][5];
				String CPCCodeDesc = BankPayments[i][6];
				String BillNumber = BankPayments[i][7];
				String PayAmount = BankPayments[i][8];
				String DRCR = BankPayments[i][9];
				String CURCY = BankPayments[i][10];
				String EXRate = BankPayments[i][11];
				String TransCURCY = BankPayments[i][12];
				String BaseCURCY = BankPayments[i][13];
				String AssesableValue = BankPayments[i][16];
				if (!GLAccountDesc.equals("")) {
					clickBPLineAddButton();
					addBankPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
					clickLineWiseSaveButton();
					clickLineWiseAddButton(i);
					clickPendingAdvancePartyDocumentsInBP();
					selectAdvPartyDocumentBasedOnPB(PartyName, VariableHandler.getMultipleAdvPymtDocs(), SheetName);
					if (!Currency.equals("INR")) {
						excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
								Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
					} else {
						excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
					}
					verifyBankPaymentsLineDetailsWithDB(SheetName, i);
					if (Currency.equals("INR")) {
						if (BankName.equals("JP MORGAN CHASE")) {
							for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
								excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVINR"),
										1, 5 + j, dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
												commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
								excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVINR"),
										2, 5 + j, dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
												commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
							}
						} else {
							for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
								excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADV"), 1,
										5 + j, dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
												commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
								excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADV"), 2,
										5 + j, dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
												commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
							}
						}
					} else {
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVUSD"), 0, 10,
								dbCommonFunctions.ExchangeRateDetails(Currency,
										commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
						for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
							excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVUSD"), 1,
									5 + j, dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
											commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
							excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVUSD"), 2,
									5 + j, dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
											commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
						}
					}
				}
				if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
					enterRealizedAmountInBPHeader(BaseCURCY);
				}
			}
		} else {
			BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
			for (int i = 1; i < BankPayments.length; i++) {
				String GLAccountDesc = BankPayments[i][1];
				String POSheetName = BankPayments[i][4];
				String PartyName = BankPayments[i][3];
				String EntityName = BankPayments[i][5];
				String CPCCodeDesc = BankPayments[i][6];
				String BillNumber = BankPayments[i][7];
				String PayAmount = BankPayments[i][8];
				String DRCR = BankPayments[i][9];
				String CURCY = BankPayments[i][10];
				String EXRate = BankPayments[i][11];
				String TransCURCY = BankPayments[i][12];
				String BaseCURCY = BankPayments[i][13];
				String AssesableValue = BankPayments[i][16];
				if (!POSheetName.equals("")) {
					clickBPLineAddButton();
					addBankPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
					clickLineWiseSaveButton();
					clickLineWiseAddButton(i);
					clickPendingAdvancePartyDocumentsInBP();
					selectAdvPartyDocumentBasedOnPB(PartyName, VariableHandler.getMultipleAdvPymtDocs(), SheetName);
					if (!Currency.equals("INR")) {
						excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
								Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
					} else {
						excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
					}
					chooseLineWiseTDS(commonFunc.getDataFromTestDataSheet("CPTDS"), 1);
					clickApplyDeductionButton();
					commonFunc.checkRefreshCellsDisappear();
					for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADV"), 1,
								5 + j, dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
										commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADV"), 2,
								5 + j, dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
										commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
					}
				}
				if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
					enterRealizedAmountInBPHeader(BaseCURCY);
				}
			}
			for (int i = 1; i < BankPayments.length; i++) {
				String GLAccountDesc = BankPayments[i][1];
				String BaseCURCY = BankPayments[i][13];
				if (!GLAccountDesc.equals("")) {
					verifyBankPaymentsLineDetailsWithDB(SheetName, i);
				}
			}
		}
		clickCreateJournalButton();
		if (SheetName.contains("TDS")) {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("TDSBPJournalADV"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
			for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADV"), 1, 5 + j, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADV"), 2, 5 + j, "0");
			}
		} else {
			if (Currency.equals("INR")) {
				if (BankName.equals("JP MORGAN CHASE")) {
					bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalADVINR"),
							VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
					for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVINR"), 1,
								5 + j, "0");
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVINR"), 2,
								5 + j, "0");
					}
				} else {
					bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalADV"),
							VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
					for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADV"), 1, 5 + j,
								"0");
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADV"), 2, 5 + j,
								"0");
					}
				}
			} else {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalADVUSD"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVUSD"), 1, 5 + j,
							"0");
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVUSD"), 2, 5 + j,
							"0");
				}
			}
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used to Create BP Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param Currency
	 * @param BankName
	 */
	public void BPAdvThroughGetDocument(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		getBankPaymentOrReceiptDocumentNumber();
		checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyBankPaymentHeaderDetailsWithUI(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
		verifyBankPaymentDetailsWithDB(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		clickGetDocumentInBPHeader();
		clickPendingAdvancePartyDocumentsInBP();
		chooseAdvPartyNameAndClickSearchButtonInPendingPartyDocs(VariableHandler.getMultipleAdvPymtDocs(), SheetName);
		if (!SheetName.contains("TDS")) {
			BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
			for (int i = 1; i < BankPayments.length; i++) {
				String GLAccountDesc = BankPayments[i][1];
				String BaseCURCY = BankPayments[i][13];
				if (!GLAccountDesc.equals("")) {
					if (!Currency.equals("INR")) {
						excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
								Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
					} else {
						excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
					}
					verifyBankPaymentsLineDetailsWithDB(SheetName, i);
					if (Currency.equals("INR")) {
						if (BankName.equals("JP MORGAN CHASE")) {
							for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
								excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVINR"),
										1, 5 + j, dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
												commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
								excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVINR"),
										2, 5 + j, dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
												commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
							}
						} else {
							for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
								excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADV"), 1,
										5 + j, dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
												commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
								excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADV"), 2,
										5 + j, dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
												commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
							}
						}
					} else {
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVUSD"), 0, 10,
								dbCommonFunctions.ExchangeRateDetails(Currency,
										commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
						for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
							excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVUSD"), 1,
									5 + j, dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
											commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
							excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVUSD"), 2,
									5 + j, dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
											commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
						}
					}
					if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
						enterRealizedAmountInBPHeader(BaseCURCY);
					}
				}
			}
		} else {
			BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
			for (int i = 1; i < BankPayments.length; i++) {
				String GLAccountDesc = BankPayments[i][1];
				String BaseCURCY = BankPayments[i][13];
				if (!GLAccountDesc.equals("")) {
					if (!Currency.equals("INR")) {
						excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
								Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
					} else {
						excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
					}
				}
			}
			chooseLineWiseTDS(commonFunc.getDataFromTestDataSheet("CPTDS"), 1);
			clickApplyDeductionButton();
			for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADV"), 1, 5 + j,
						dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
								commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADV"), 2, 5 + j,
						dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
								commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
			}
			for (int i = 1; i < BankPayments.length; i++) {
				String GLAccountDesc = BankPayments[i][1];
				String BaseCURCY = BankPayments[i][13];
				if (!GLAccountDesc.equals("")) {
					verifyBankPaymentsLineDetailsWithDB(SheetName, i);
				}
			}
		}
		clickCreateJournalButton();
		if (SheetName.contains("TDS")) {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("TDSBPJournalADV"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
			for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADV"), 1, 5 + j, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADV"), 2, 5 + j, "0");
			}
		} else {
			if (Currency.equals("INR")) {
				if (BankName.equals("JP MORGAN CHASE")) {
					bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalADVINR"),
							VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
					for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVINR"), 1,
								5 + j, "0");
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVINR"), 2,
								5 + j, "0");
					}
				} else {
					bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalADV"),
							VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
					for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADV"), 1, 5 + j,
								"0");
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADV"), 2, 5 + j,
								"0");
					}
				}
			} else {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalADVUSD"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVUSD"), 1, 5 + j,
							"0");
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVUSD"), 2, 5 + j,
							"0");
				}
			}
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
		}
		assertTrue(flag);
	}

	/**
	 * This Method is used to Create BP Through Pending DOcs
	 * 
	 * @author Harikrishna - RMITS
	 * @param CreationType
	 * @param SheetName
	 * @param Currency
	 * @param BankName
	 */
	public void BPAdvThroughPendingDocs(String CreationType, String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingCRDocsButton();
		clickPendingAdvancePartyDocumentsInPendingCRDocs();
		chooseAdvDocumentNumberInPendingPayablesScreen(VariableHandler.getMultipleAdvPymtDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentType"), commonFunc.getDataFromTestDataSheet("BankPayMode"),
				BankName, CreationType, SheetName);
		getBankPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		sendReferenceNumberInBP(commonFunc.getDataFromTestDataSheet("Reference Number"));
		clickBPHeaderWiseSaveButton();
		if (!SheetName.contains("TDS")) {
			BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
			for (int i = 1; i < BankPayments.length; i++) {
				String GLAccountDesc = BankPayments[i][1];
				String BaseCURCY = BankPayments[i][13];
				if (!GLAccountDesc.equals("")) {
					if (!Currency.equals("INR")) {
						excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
								Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
					} else {
						excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
					}
					verifyBankPaymentsLineDetailsWithDB(SheetName, i);
					if (Currency.equals("INR")) {
						if (BankName.equals("JP MORGAN CHASE")) {
							for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
								excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVINR"),
										1, 5 + j, dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
												commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
								excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVINR"),
										2, 5 + j, dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
												commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
							}
						} else {
							for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
								excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADV"), 1,
										5 + j, dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
												commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
								excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADV"), 2,
										5 + j, dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
												commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
							}
						}
					} else {
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVUSD"), 0, 10,
								dbCommonFunctions.ExchangeRateDetails(Currency,
										commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
						for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
							excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVUSD"), 1,
									5 + j, dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
											commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
							excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVUSD"), 2,
									5 + j, dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
											commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
						}
					}
					if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
						enterRealizedAmountInBPHeader(BaseCURCY);
					}
				}
			}
		} else {
			BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
			for (int i = 1; i < BankPayments.length; i++) {
				String GLAccountDesc = BankPayments[i][1];
				String BaseCURCY = BankPayments[i][13];
				if (!GLAccountDesc.equals("")) {
					if (!Currency.equals("INR")) {
						excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
								Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
					} else {
						excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
					}
				}
			}
			chooseLineWiseTDS(commonFunc.getDataFromTestDataSheet("CPTDS"), 1);
			clickApplyDeductionButton();
			for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADV"), 1, 5 + j,
						dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
								commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADV"), 2, 5 + j,
						dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
								commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
			}
			for (int i = 1; i < BankPayments.length; i++) {
				String GLAccountDesc = BankPayments[i][1];
				String BaseCURCY = BankPayments[i][13];
				if (!GLAccountDesc.equals("")) {
					verifyBankPaymentsLineDetailsWithDB(SheetName, i);
				}
			}
		}
		clickCreateJournalButton();
		if (SheetName.contains("TDS")) {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("TDSBPJournalADV"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
			for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADV"), 1, 5 + j, "0");
				excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADV"), 2, 5 + j, "0");
			}
		} else {
			if (Currency.equals("INR")) {
				if (BankName.equals("JP MORGAN CHASE")) {
					bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalADVINR"),
							VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
					for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVINR"), 1,
								5 + j, "0");
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVINR"), 2,
								5 + j, "0");
					}
				} else {
					bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalADV"),
							VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
					for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADV"), 1, 5 + j,
								"0");
						excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADV"), 2, 5 + j,
								"0");
					}
				}
			} else {
				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalADVUSD"),
						VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
				for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVUSD"), 1, 5 + j,
							"0");
					excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("BPJournalADVUSD"), 2, 5 + j,
							"0");
				}
			}
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
		}
	}

	/**
	 * This Method Is Used To Add The Bank Payment Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPAdvThroughAddButtonWithMultiTDS(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		getBankPaymentOrReceiptDocumentNumber();
		checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyBankPaymentHeaderDetailsWithUI(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
		verifyBankPaymentDetailsWithDB(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String POSheetName = BankPayments[i][4];
			String PartyName = BankPayments[i][3];
			String EntityName = BankPayments[i][5];
			String CPCCodeDesc = BankPayments[i][6];
			String BillNumber = BankPayments[i][7];
			String PayAmount = BankPayments[i][8];
			String DRCR = BankPayments[i][9];
			String CURCY = BankPayments[i][10];
			String EXRate = BankPayments[i][11];
			String TransCURCY = BankPayments[i][12];
			String BaseCURCY = BankPayments[i][13];
			String AssesableValue = BankPayments[i][16];
			String TDS = BankPayments[i][29];
			if (!POSheetName.equals("")) {
				clickBPLineAddButton();
				addBankPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
				clickLineWiseSaveButton();
				clickLineWiseAddButton(i);
				clickPendingAdvancePartyDocumentsInBP();
				selectBPAdvPartyDocumentBasedOnPBMultiTDS(PartyName, VariableHandler.getMultipleAdvPymtDocs(),
						SheetName, i);
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
				}
				chooseLineWiseTDS(TDS, i);
			}
		}
		clickApplyDeductionButton();
		for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADVMulti"), j, 6,
					dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(j - 1),
							commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		}
		for (int k = 1; k < BankPayments.length; k++) {
			String GLAccountDesc1 = BankPayments[k][1];
			String BaseCURCY1 = BankPayments[k][13];
			if (!GLAccountDesc1.equals("")) {
				verifyBankPaymentsLineDetailsWithDB(SheetName, k);
			}
		}
		clickCreateJournalButton();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("TDSBPJournalADVMulti"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
		for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADVMulti"), 1, 5 + j, "0");
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADVMulti"), 2, 5 + j, "0");
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used to Create BP Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param Currency
	 * @param BankName
	 */
	public void BPAdvThroughGetDocumentWithMultiTDS(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		getBankPaymentOrReceiptDocumentNumber();
		checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyBankPaymentHeaderDetailsWithUI(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
		verifyBankPaymentDetailsWithDB(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		clickGetDocumentInBPHeader();
		clickPendingAdvancePartyDocumentsInBP();
		chooseAdvPartyNameAndClickSearchButtonInPendingPartyDocs(VariableHandler.getMultipleAdvPymtDocs(), SheetName);
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String POSheetName = BankPayments[i][4];
			String BaseCURCY = BankPayments[i][13];
			String TDS = BankPayments[i][29];
			if (!Currency.equals("INR")) {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
						Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
			} else {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
			}
			if (!POSheetName.equals("")) {
				chooseLineWiseTDS(TDS, i);
			}
		}
		clickApplyDeductionButton();
		for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADVMulti"), j, 6,
					dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(j - 1),
							commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		}
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String BaseCURCY = BankPayments[i][13];
			if (!GLAccountDesc.equals("")) {
				verifyBankPaymentsLineDetailsWithDB(SheetName, i);
			}
		}
		clickCreateJournalButton();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("TDSBPJournalADVMulti"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
		for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADVMulti"), 1, 5 + j, "0");
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADVMulti"), 2, 5 + j, "0");
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
		}
		assertTrue(flag);
	}

	/**
	 * This Method is used to Create BP Through Pending DOcs
	 * 
	 * @author Harikrishna - RMITS
	 * @param CreationType
	 * @param SheetName
	 * @param Currency
	 * @param BankName
	 */
	public void BPAdvThroughPendingDocsWithMultiTDS(String CreationType, String SheetName, String Currency,
			String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingCRDocsButton();
		clickPendingAdvancePartyDocumentsInPendingCRDocs();
		chooseAdvDocumentNumberInPendingPayablesScreen(VariableHandler.getMultipleAdvPymtDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentType"), commonFunc.getDataFromTestDataSheet("BankPayMode"),
				BankName, CreationType, SheetName);
		getBankPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		sendReferenceNumberInBP(commonFunc.getDataFromTestDataSheet("Reference Number"));
		clickBPHeaderWiseSaveButton();
		commonFunc.checkRefreshCellsDisappear();
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String POSheetName = BankPayments[i][4];
			String BaseCURCY = BankPayments[i][13];
			String TDS = BankPayments[i][29];
			if (!Currency.equals("INR")) {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
						Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
			} else {
				excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 11, "1");
			}
			if (!POSheetName.equals("")) {
				chooseLineWiseTDS(TDS, i);
			}
		}
		clickApplyDeductionButton();
		for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADVMulti"), j, 6,
					dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(j - 1),
							commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		}
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String BaseCURCY = BankPayments[i][13];
			if (!GLAccountDesc.equals("")) {
				verifyBankPaymentsLineDetailsWithDB(SheetName, i);
			}
		}
		clickCreateJournalButton();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("TDSBPJournalADVMulti"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), BPFilepath);
		for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADVMulti"), 1, 5 + j, "0");
			excelUtils.writeDataInExcel(BPFilepath, webDB.getDataFromProperties("TDSBPJournalADVMulti"), 2, 5 + j, "0");
		}
		verifyOverallHeaderAmountWithDB(SheetName);
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
		}
	}

	/**
	 * This Method Is Used To Click The Pending Party Documents In Bank Payment
	 * Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickPendingAdvancePartyDocumentsInBP() {
		flag = webDB.waitForElement(BankPaymentLocators.BankPendingAdvPartyDocumentsButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BankPendingAdvPartyDocumentsButton, ElementType.Xpath);
			flag = webDB.waitForElement(BankPaymentLocators.BankPendingAdvPartyDocumentsFrameName, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Bank Payment Pending Advance Party Document Is Clicked Successfully");
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
	public void clickPendingAdvancePartyDocumentsInPendingCRDocs() {
		flag = webDB.waitForElement(BankPaymentLocators.BankPendingAdvPartyDocumentsPendingCRButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BankPendingAdvPartyDocumentsPendingCRButton, ElementType.Xpath);
			flag = webDB.waitForElement(BankPaymentLocators.BankPendingAdvPartyDocumentsFrameName, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Bank Payment Pending Advance Party Document Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used to Select the Bank Payment pending Records In the Pending
	 * Party Documnets IN the bank Payment Screen
	 * 
	 * @author Harikrishna _ RMITS
	 * @param PartyName
	 * @param VoucherNumber.get(i)
	 */
	public void selectAdvPartyDocumentBasedOnPB(String PartyName, ArrayList<String> VoucherNumber, String SheetName) {
		flag = webDB.switchToFrame(BankPaymentLocators.BankPendingAdvPartyDocumentsFrameName, ElementType.Xpath);
		if (flag) {
			String GetTextPartyName = webDB.getDomPropertyFromElement(
					BankPaymentLocators.BankPendingPartyDocumentsPartyNameInputField, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			if (GetTextPartyName.equals(PartyName)) {
				for (int i = 1; i <= VoucherNumber.size(); i++) {
					commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.BankPartyAdvDocNumberLovButton,
							VoucherNumber.get(i - 1));
					webDB.switchToFrame(BankPaymentLocators.BankPendingAdvPartyDocumentsFrameName, ElementType.Xpath);
					flag = webDB.waitForElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
							ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.doubleClickAnElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
								ElementType.Xpath);
						flag = webDB.waitForElement(
								BankPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i - 1)),
								ElementType.Xpath);
						if (flag) {
							commonFunc.checkRefreshCellsDisappear();
							cashPymtFunc.verifyPendingAdvancePayablesThroughGetDoc(SheetName, VoucherNumber.get(i - 1),
									i); // TODO
							webDB.clickAnElement(
									BankPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i - 1)),
									ElementType.Xpath);
							commonFunc.hardWait(6000);
							flag = webDB.waitForElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "In Pending Advance Party Documents This Voucher Number "
										+ VoucherNumber.get(i - 1) + " Is Selected Successfully");
							}
						}
					}
				}
				webDB.moveToElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton, ElementType.Xpath);
				webDB.javaScriptClickAnElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton,
						ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(BankPaymentLocators.BankHeaderCreateJournalButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "OK Button Is Clicked Successfully In The Pending Party Documents");
				}
			} else {
				logs.logging("INFO",
						"Bank Payment Line Wise Party Name And Pending Party Documents Party Name Is not Getting Matched");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used to Select the Bank Payment pending Records In the Pending
	 * Party Documnets IN the bank Payment Screen
	 * 
	 * @author Harikrishna _ RMITS
	 * @param PartyName
	 * @param VoucherNumber.get(i)
	 */
	public void selectBPAdvPartyDocumentBasedOnPBMultiTDS(String PartyName, ArrayList<String> VoucherNumber,
			String SheetName, int i1) {
		flag = webDB.switchToFrame(CashPaymentLocators.BankPendingAdvPartyDocumentsFrameName, ElementType.Xpath);
		if (flag) {
			String GetTextPartyName = webDB.getDomPropertyFromElement(
					CashPaymentLocators.BankPendingPartyDocumentsPartyNameInputField, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			if (GetTextPartyName.equals(PartyName)) {
				for (int i = i1; i <= i1; i++) {
					commonFunc.searchAndSelectValueInFilters(CashPaymentLocators.BankPartyAdvDocNumberLovButton,
							VoucherNumber.get(i - 1));
					webDB.switchToFrame(CashPaymentLocators.BankPendingAdvPartyDocumentsFrameName, ElementType.Xpath);
					flag = webDB.waitForElement(CashPaymentLocators.BankPaymentAdvPendingPartyDocumentSearchButton,
							ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.doubleClickAnElement(CashPaymentLocators.BankPaymentAdvPendingPartyDocumentSearchButton,
								ElementType.Xpath);
						commonFunc.hardWait(500);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(
								CashPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i - 1)),
								ElementType.Xpath);
						if (flag) {
							commonFunc.checkRefreshCellsDisappear();
							cashPymtFunc.verifyPendingAdvancePayablesThroughGetDoc(SheetName, VoucherNumber.get(i - 1),
									i); // TODO
							webDB.clickAnElement(
									CashPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i - 1)),
									ElementType.Xpath);
							commonFunc.hardWait(6000);
							flag = webDB.waitForElement(CashPaymentLocators.BankPendingAdvPartyDocumentScreenOkButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "In Pending Party Documents This Voucher Number "
										+ VoucherNumber.get(i - 1) + " Is Selected Successfully");
							}
						}
					}
				}
				webDB.moveToElement(CashPaymentLocators.BankPendingAdvPartyDocumentScreenOkButton, ElementType.Xpath);
				webDB.javaScriptClickAnElement(CashPaymentLocators.BankPendingAdvPartyDocumentScreenOkButton,
						ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(CashPaymentLocators.BankHeaderAdvCreateJournalButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "OK Button Is Clicked Successfully In The Pending Party Documents");
				}
			} else {
				logs.logging("INFO",
						"Bank Payment Line Wise Party Name And Pending Party Documents Party Name Is not Getting Matched");
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
	public void chooseAdvPartyNameAndClickSearchButtonInPendingPartyDocs(ArrayList<String> VoucherNumber,
			String SheetName) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.switchToFrame(BankPaymentLocators.BankPendingAdvPartyDocumentsFrameName, ElementType.Xpath);
		System.out.println(VoucherNumber.size());
		if (flag) {
			for (int i = 1; i <= VoucherNumber.size(); i++) {
				System.out.println(2);
//				commonFunc.searchAndSelectValueInFilters(
//						BankPaymentLocators.BankPaymentPendingPartyDocumentPartyLovButton, PartyName);
//				webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
//				flag = webDB.waitForElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
//						ElementType.Xpath);
//				if (flag) {
//					webDB.clickAnElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
//							ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.BankPartyAdvDocNumberLovButton,
						VoucherNumber.get(i - 1));
				webDB.switchToFrame(BankPaymentLocators.BankPendingAdvPartyDocumentsFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
							ElementType.Xpath);
					commonFunc.hardWait(1000);
					commonFunc.checkRefreshCellsDisappear();
					String GetTextDocumentNumber = webDB.getTextFromElement(
							BankPaymentLocators.PendingAdvPayablesDocumentNumberField, ElementType.Xpath);
					logs.logging("INFO", "Get Text Document Number Is " + GetTextDocumentNumber);
					if (GetTextDocumentNumber.equals(VoucherNumber.get(i - 1))) {
						cashPymtFunc.verifyPendingAdvancePayablesThroughGetDoc(SheetName, VoucherNumber.get(i - 1), i);
						flag = webDB.waitForElement(
								BankPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i - 1)),
								ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(
									BankPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i - 1)),
									ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(3000);
							flag = webDB.waitForElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "In Pending Party Documents This Voucher Number "
										+ VoucherNumber.get(i - 1) + " Is Selected Successfully");
							}
						}
					} else {
						logs.logging("INFO",
								"Bank Payment Pending Line Wise Party Document Number And Pending Party Documents Number Is not Getting Matched");
					}
				}
			}
			commonFunc.hardWait(1500);
			flag = webDB.waitForElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton, ElementType.Xpath);
				webDB.javaScriptClickAnElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton,
						ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(BankPaymentLocators.BankHeaderCreateJournalButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Ok Button Is Clicked Successfully");
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
	public void chooseAdvDocumentNumberInPendingPayablesScreen(ArrayList<String> DocumentNumber, String Type,
			String PayMode, String BankName, String CreationType, String SheetName) {
		flag = webDB.waitForElement(BankPaymentLocators.BankPendingAdvPartyDocumentsFrameName, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(BankPaymentLocators.BankPendingAdvPartyDocumentsFrameName, ElementType.Xpath);
			for (int i = 1; i <= DocumentNumber.size(); i++) {
				commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.BankPartyAdvDocNumberLovButton,
						DocumentNumber.get(i - 1));
				webDB.switchToFrame(BankPaymentLocators.BankPendingAdvPartyDocumentsFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(BankPaymentLocators.BankPaymentAdvPendingPartyDocumentSearchButton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankPaymentLocators.BankPaymentAdvPendingPartyDocumentSearchButton,
							ElementType.Xpath);
					commonFunc.hardWait(1000);
					commonFunc.checkRefreshCellsDisappear();
					String GetTextDocumentNumber = webDB.getTextFromElement(
							BankPaymentLocators.PendingAdvancePayablesDocumentNumberField, ElementType.Xpath);
					if (GetTextDocumentNumber.equals(DocumentNumber.get(i - 1))) {
						commonFunc.hardWait(1000);
						cashPymtFunc.verifyPendingAdvancePayablesThroughGetDoc(SheetName, DocumentNumber.get(i - 1), i);
						webDB.clickAnElement(
								BankPaymentLocators.BPPendingPartyDocumentSelectCheckBox(DocumentNumber.get(i - 1)),
								ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(1500);
						flag = webDB.waitForElement(BankPaymentLocators.PendingPayablesCreateButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "This Purchase Bills Document " + DocumentNumber.get(i - 1)
									+ " Is Selected Successfully");
							if (DocumentNumber.size() > 1) {
								webDB.clickAnElement(BankPaymentLocators.BankPendingPartyDocumentClearButton,
										ElementType.Xpath);
								flag = webDB.waitForElement(
										BankPaymentLocators.BankPaymentAdvPendingPartyDocumentSearchButton,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(
											BankPaymentLocators.BankPaymentAdvPendingPartyDocumentSearchButton,
											ElementType.Xpath);
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
			webDB.moveToElement(BankPaymentLocators.PendingPayablesCreateButton, ElementType.Xpath);
			webDB.javaScriptClickAnElement(BankPaymentLocators.PendingPayablesCreateButton, ElementType.Xpath);
			logs.logging("INFO", "Create Button Is Clicked Successfully");
			commonFunc.checkRefreshCellsDisappear();
			if (!CreationType.equals("Single")) {
				flag = webDB.waitForElement(BankPaymentLocators.PendingPayablesMergeLabelInPending, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankPaymentLocators.PendingPayablesMergeLabelInPending, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
				}
			}
			flag = webDB.waitForElement(BankPaymentLocators.PendingAdvPayablesTypeField, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownByText(BankPaymentLocators.PendingAdvPayablesTypeDropDown, Type, ElementType.Xpath);
				logs.logging("INFO", "Type Successfully Choosen As " + Type);
				if (Type.equals("Bank")) {
					flag = webDB.waitForElement(BankPaymentLocators.PendingAdvpayablesPaymodeField, ElementType.Xpath);
					if (flag) {
						webDB.selectDropDownByText(BankPaymentLocators.PendingAdvPayablesPayModeDropDown, PayMode,
								ElementType.Xpath);
						logs.logging("INFO", "Pay Mode Successfully Choosen As " + PayMode);
					}
				}
				commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.PendingAdvPayablesBankNameLOVBUtton,
						BankName);
				webDB.switchToFrame(BankPaymentLocators.BankPendingAdvPartyDocumentsFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(BankPaymentLocators.PendingPayablesOkButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankPaymentLocators.PendingPayablesOkButton, ElementType.Xpath);
					webDB.switchToDefaultContent();
					if (Type.equals("Bank")) {
						flag = webDB.waitForElement(BankPaymentLocators.BankPaymentScreenName, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"OK Button Is Clicked Successfully And Navigated To Bank Payment Screen");
						}
					} else {
						flag = webDB.waitForElement(BankPaymentLocators.CashPaymentScreenName, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"OK Button Is Clicked Successfully And Navigated To Cash Payment Screen");
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The Bank Payment Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPAdvThroughAddButtonWithMPMB(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		getBankPaymentOrReceiptDocumentNumber();
		VariableHandler.setMultipleBankDocs(MultiBankDocumentNumber);
		checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyBankPaymentHeaderDetailsWithUI(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
		verifyBankPaymentDetailsWithDB(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String POSheetName = BankPayments[i][4];
			String PartyName = BankPayments[i][3];
			String EntityName = BankPayments[i][6];
			String CPCCodeDesc = BankPayments[i][7];
			String BillNumber = BankPayments[i][8];
			String PayAmount = BankPayments[i][9];
			String DRCR = BankPayments[i][10];
			String CURCY = BankPayments[i][11];
			String EXRate = BankPayments[i][12];
			String TransCURCY = BankPayments[i][13];
			String BaseCURCY = BankPayments[i][34];
			String AssesableValue = BankPayments[i][17];
//			String TDS = BankPayments[i][29];
			if (!POSheetName.equals("")) {
				clickBPLineAddButton();
				addBankPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
				clickLineWiseSaveButton();
				clickLineWiseAddButton(i);
				clickPendingAdvancePartyDocumentsInBP();
				selectBPAdvPartyDocumentMPMB(PartyName, VariableHandler.getMultipleAdvPymtDocs(), SheetName, i);
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 12, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 12, "1");
				}
//				chooseLineWiseTDS(TDS, i);
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBPHeader(BaseCURCY);
			}
			verifyBankPaymentsLineDetailsWithDBAdvMPMB(SheetName, i);
		}

//		clickApplyDeductionButton();
//		for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
//			excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalADVMulti"), j, 6,
//					dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(j - 1),
//							commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
//		}
//		for (int k = 1; k < BankPayments.length; k++) {
//			String GLAccountDesc1 = BankPayments[k][1];
//			String BaseCURCY1 = BankPayments[k][13];
//			if (!GLAccountDesc1.equals("")) {
//				verifyBankPaymentsLineDetailsWithDB(SheetName, k);
//			}
//		}
		clickCreateJournalButton();
//		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalADVMulti"),
//				VariableHandler.getBankPaymentOrReceiptDocNumber());
//		for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
//			excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalADVMulti"), 1, 5 + j, "0");
//			excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalADVMulti"), 2, 5 + j, "0");
//		}
//		verifyOverallHeaderAmountWithDB(SheetName);
//		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used to Select the Bank Payment pending Records In the Pending
	 * Party Documnets IN the bank Payment Screen
	 * 
	 * @author Harikrishna _ RMITS
	 * @param PartyName
	 * @param VoucherNumber.get(i)
	 */
	public void selectBPAdvPartyDocumentMPMB(String PartyName, ArrayList<String> VoucherNumber, String SheetName,
			int i1) {
		flag = webDB.switchToFrame(CashPaymentLocators.BankPendingAdvPartyDocumentsFrameName, ElementType.Xpath);
		if (flag) {
			String GetTextPartyName = webDB.getDomPropertyFromElement(
					CashPaymentLocators.BankPendingPartyDocumentsPartyNameInputField, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			int a = i1 * 2;
			if (GetTextPartyName.equals(PartyName)) {
				for (int i = a - 1; i <= a; i++) {
					commonFunc.searchAndSelectValueInFilters(CashPaymentLocators.BankPartyAdvDocNumberLovButton,
							VoucherNumber.get(i - 1));
					webDB.switchToFrame(CashPaymentLocators.BankPendingAdvPartyDocumentsFrameName, ElementType.Xpath);
					flag = webDB.waitForElement(CashPaymentLocators.BankPaymentAdvPendingPartyDocumentSearchButton,
							ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.doubleClickAnElement(CashPaymentLocators.BankPaymentAdvPendingPartyDocumentSearchButton,
								ElementType.Xpath);
						commonFunc.hardWait(500);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(
								CashPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i - 1)),
								ElementType.Xpath);
						if (flag) {
							commonFunc.checkRefreshCellsDisappear();
							verifyPendingAdvancePayablesThroughGetDoc(SheetName, VoucherNumber.get(i - 1), i1); // TODO
							webDB.clickAnElement(
									CashPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i - 1)),
									ElementType.Xpath);
							commonFunc.hardWait(6000);
							flag = webDB.waitForElement(CashPaymentLocators.BankPendingAdvPartyDocumentScreenOkButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "In Pending Party Documents This Voucher Number "
										+ VoucherNumber.get(i - 1) + " Is Selected Successfully");
							}
						}
					}
				}
				webDB.moveToElement(CashPaymentLocators.BankPendingAdvPartyDocumentScreenOkButton, ElementType.Xpath);
				webDB.javaScriptClickAnElement(CashPaymentLocators.BankPendingAdvPartyDocumentScreenOkButton,
						ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(CashPaymentLocators.BankHeaderAdvCreateJournalButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "OK Button Is Clicked Successfully In The Pending Party Documents");
				}
			} else {
				logs.logging("INFO",
						"Bank Payment Line Wise Party Name And Pending Party Documents Party Name Is not Getting Matched");
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
	public void verifyPendingAdvancePayablesThroughGetDoc(String SheetName, String VoucherNumber, int i1) {
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
//		int a= i1*1;
		int count = 0;
		for (int i = i1; i <= i1; i++) {
//		int innerValue = (i1 % 2 == 1) ? 1 : 2;
//		  for (int i = innerValue; i <= innerValue; i++) {
//			for (int i = (i1 + 1) / 2; i <= (i1 + 1) / 2; i++){
//			for(int j = 1; j<BankPayments.length;j++) {
			System.out.println(i);
			String PartyID = BankPayments[i][2];
			String PartyName = BankPayments[i][3];
			String Entity = BankPayments[i][6];
			String CPCDesc = BankPayments[i][7];
			String Curcy = BankPayments[i][11];
			String EXRate = BankPayments[i][12];
			String Location = BankPayments[i][24];
			String Unit = BankPayments[i][25];
			String DrCr = BankPayments[i][26];
			String PayableAmount = BankPayments[i][27];
			String PayableAmount1 = BankPayments[i][28];
			if (!PartyID.equals("")) {
				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.CashPaymentAdvancePendingPayablesDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("BRDBPendingAdvanceReceivablesPartyName")),
						PartyName);
				logs.logging("INFO", "In Advance Pending Payables For This Document Number " + VoucherNumber
						+ " PartyName Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.CashPaymentAdvancePendingPayablesDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("BRDBPendingAdvanceReceivablesLocationID")),
						Location);
				logs.logging("INFO", "In Advance Pending Payables For This Document Number " + VoucherNumber
						+ " Location ID Is getting Matched With DB");

				salesOrdFunc
						.verifyAssertionBothValues(
								dbCommonFunctions.CashPaymentAdvancePendingPayablesDetails(VoucherNumber,
										commonFunc.getDataFromTestDataSheet("BRDBPendingAdvanceReceivablesUnitID")),
								Unit);
				logs.logging("INFO", "In Advance Pending Payables For This Document Number " + VoucherNumber
						+ " Unit ID Is getting Matched With DB");

				salesOrdFunc
						.verifyAssertionBothValues(
								dbCommonFunctions.CashPaymentAdvancePendingPayablesDetails(VoucherNumber,
										commonFunc.getDataFromTestDataSheet("BRDBPendingAdvanceReceivablesCurcy")),
								Curcy);
				logs.logging("INFO", "In Advance Pending Payables For This Document Number " + VoucherNumber
						+ " Currency Is getting Matched With DB");

				salesOrdFunc
						.verifyAssertionBothValues(
								dbCommonFunctions.CashPaymentAdvancePendingPayablesDetails(VoucherNumber,
										commonFunc.getDataFromTestDataSheet("BRDBPendingAdvanceReceivablesExRate")),
								EXRate);
				logs.logging("INFO", "In Advance Pending Payables For This Document Number " + VoucherNumber
						+ " EX.Rate Is getting Matched With DB");

//				salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankPaymentPendingPayablesDetails(
//						VoucherNumber, commonFunc.getDataFromTestDataSheet("BRDBPendingAdvanceReceivablesDROrCR")), DrCr);
//				logs.logging("INFO", "In Advance Pending Payables For This Document Number " + VoucherNumber
//						+ " Dr / Cr Is getting Matched With DB");
				if (count >= 1) {
					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.CashPaymentAdvancePendingPayablesDetails(VoucherNumber,
									commonFunc.getDataFromTestDataSheet("BRDBPendingAdvanceReceivablesInvAmt")),
							PayableAmount1);
					logs.logging("INFO", "In Advance Pending Payables For This Document Number " + VoucherNumber
							+ " Payable Amount Is getting Matched With DB");
				} else {
					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.CashPaymentAdvancePendingPayablesDetails(VoucherNumber,
									commonFunc.getDataFromTestDataSheet("BRDBPendingAdvanceReceivablesInvAmt")),
							PayableAmount);
					logs.logging("INFO", "In Advance Pending Payables For This Document Number " + VoucherNumber
							+ " Payable Amount Is getting Matched With DB");
				}

//				salesOrdFunc.verifyAssertionBothValues(
//						dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
//								commonFunc.getDataFromTestDataSheet("BRDBPendingAdvanceReceivablesDueDays")),
//						dbCommonFunctions.SOPaymentTermAdvPercentage(
//								commonFunc.getDataFromTestDataSheet("AdvanceSupplierPaymentTerm"),
//								commonFunc.getDataFromTestDataSheet("PaymentTermDueDays")));
//				logs.logging("INFO", "In Advance Pending Payables For This Document Number " + VoucherNumber
//						+ " Due Days Is getting Matched With DB");

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.CashPaymentAdvancePendingPayablesDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("BRDBPendingAdvanceReceivablesDocNumber")),
						VoucherNumber);
				logs.logging("INFO", "In Advance Pending Payables For This Document Number " + VoucherNumber
						+ " Source Document Is getting Matched With DB");
			}
			count++;
		}
	}

	/**
	 * This Method Is Used To Add The Bank Payment Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void BPThroughAddButtonAdvanceWithNewRef(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		getBankPaymentOrReceiptDocumentNumber();
		checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyBankPaymentHeaderDetailsWithUI(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
		verifyBankPaymentDetailsWithDB(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String PartyName = BankPayments[i][3];
			String EntityName = BankPayments[i][6];
			String CPCCodeDesc = BankPayments[i][7];
			String BillNumber = BankPayments[i][8];
			String PayAmount = BankPayments[i][9];
			String DRCR = BankPayments[i][10];
			String CURCY = BankPayments[i][11];
			String EXRate = BankPayments[i][12];
			String TransCURCY = BankPayments[i][13];
			String BaseCURCY = BankPayments[i][34];
			String AssesableValue = BankPayments[i][17];
			if (!GLAccountDesc.equals("")) {
				clickBPLineAddButton();
				addBankPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
				clickLineWiseSaveButton();
				clickLineWiseAddButton(i);
				clickPendingPartyDocumentsInBP();
				selectAdvancePartyDocumentBasedOnPBThroughAdd(PartyName, VariableHandler.getMultiplePBDocs(), i,
						SheetName);
				clickLineWiseViewButton(i);
				clickAddButtonInViewDetails();
				addBillNumberAndPayAmount(BillNumber, PayAmount);
				clickViewDetailsSaveButton();
				clickBankViewDetailsCloseButton();
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 12, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(BPFilepath, SheetName, i, 12, "1");
				}
//				verifyBankPaymentsLineDetailsWithDB(SheetName, i);
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBPHeader(BaseCURCY);
			}
//			if (Currency.equals("INR")) {
//				if (BankName.equals("JP MORGAN CHASE")) {
//					excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNRINR"), 1, 6,
//							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
//									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
//					excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNRINR"), 1, 7,
//							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
//									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
//					excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNRINR"), 2, 6,
//							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
//									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
//					excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNRINR"), 2, 7,
//							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
//									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
//				} else {
//					excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNR"), 1, 6,
//							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
//									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
//					excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNR"), 1, 7,
//							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
//									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
//					excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNR"), 2, 6,
//							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
//									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
//					excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNR"), 2, 7,
//							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
//									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
//				}
//			}
		}
		clickCreateJournalButton();
//		if (Currency.equals("INR")) {
//			if (BankName.equals("JP MORGAN CHASE")) {
//				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalNRINR"),
//						VariableHandler.getBankPaymentOrReceiptDocNumber());
//				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNRINR"), 1, 6, "0");
//				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNRINR"), 1, 7, "0");
//				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNRINR"), 2, 6, "0");
//				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNRINR"), 2, 7, "0");
//			} else {
//				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalNR"),
//						VariableHandler.getBankPaymentOrReceiptDocNumber());
//				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNR"), 1, 6, "0");
//				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNR"), 1, 7, "0");
//				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNR"), 2, 6, "0");
//				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNR"), 2, 7, "0");
//			}
//		}
//		verifyOverallHeaderAmountWithDB(SheetName);
//		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
		}
		assertTrue(flag);
	}

	/**
	 * This method Is Used To Get Document Through BP
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param Currency
	 * @param BankName
	 */
	public void BPThroughGetDocumentAdvanceWithNewReference(String SheetName, String Currency, String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createBankPaymentEntry(commonFunc.getDataFromTestDataSheet("BankDocMode"), BankName, Today,
				commonFunc.getDataFromTestDataSheet("BankPayMode"),
				commonFunc.getDataFromTestDataSheet("BankRefNumber"), Today,
				commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		getBankPaymentOrReceiptDocumentNumber();
		checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyBankPaymentHeaderDetailsWithUI(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
		verifyBankPaymentDetailsWithDB(BankName, commonFunc.getDataFromTestDataSheet("BankPrefix"), Currency);
		clickGetDocumentInBPHeader();
		clickPendingPartyDocumentsInBP();
		chooseAdvancePartyNameAndClickSearchButtonInPendingPartyDocsMPMB(VariableHandler.getMultiplePBDocs(),
				SheetName);
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String PartyName = BankPayments[i][3];
			String EntityName = BankPayments[i][5];
			String CPCCodeDesc = BankPayments[i][6];
			String BillNumber = BankPayments[i][7];
			String PayAmount = BankPayments[i][8];
			String DRCR = BankPayments[i][9];
			String CURCY = BankPayments[i][10];
			String EXRate = BankPayments[i][11];
			String TransCURCY = BankPayments[i][12];
			String BaseCURCY = BankPayments[i][34];
			String AssesableValue = BankPayments[i][16];
			if (!GLAccountDesc.equals("")) {
				clickLineWiseViewButton(i);
				clickAddButtonInViewDetails();
				addBillNumberAndPayAmount(BillNumber, PayAmount);
				clickViewDetailsSaveButton();
				clickBankViewDetailsCloseButton();
//				for (int j = 1; j < BankPayments.length; j++) {
//					String GLAccountDesc1 = BankPayments[j][1];
//					if (!GLAccountDesc1.equals("")) {
//						if (!Currency.equals("INR")) {
//							excelUtils.writeDataInExcel(Filepath, SheetName, i, 11,
//									dbCommonFunctions.ExchangeRateDetails(Currency,
//											commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
//						} else {
//							excelUtils.writeDataInExcel(Filepath, SheetName, i, 11, "1");
//						}
//						verifyBankPaymentsLineDetailsWithDB(SheetName, i);
//					}
//				}
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBPHeader(BaseCURCY);
			}
//			if (Currency.equals("INR")) {
//				if (BankName.equals("JP MORGAN CHASE")) {
//					excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNRINR"), 1, 6,
//							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
//									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
//					excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNRINR"), 1, 7,
//							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
//									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
//					excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNRINR"), 2, 6,
//							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
//									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
//					excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNRINR"), 2, 7,
//							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
//									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
//				} else {
//					excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNR"), 1, 6,
//							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
//									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
//					excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNR"), 1, 7,
//							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
//									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
//					excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNR"), 2, 6,
//							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
//									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
//					excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNR"), 2, 7,
//							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
//									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
//				}
//			}
		}
		clickCreateJournalButton();
//		if (Currency.equals("INR")) {
//			if (BankName.equals("JP MORGAN CHASE")) {
//				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalNRINR"),
//						VariableHandler.getBankPaymentOrReceiptDocNumber());
//				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNRINR"), 1, 6, "0");
//				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNRINR"), 1, 7, "0");
//				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNRINR"), 2, 6, "0");
//				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNRINR"), 2, 7, "0");
//			} else {
//				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalNR"),
//						VariableHandler.getBankPaymentOrReceiptDocNumber());
//				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNR"), 1, 6, "0");
//				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNR"), 1, 7, "0");
//				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNR"), 2, 6, "0");
//				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNR"), 2, 7, "0");
//			}
//		}
//		verifyOverallHeaderAmountWithDB(SheetName);
//		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify BP Thriugh Pending Docs with new Ref
	 * 
	 * @author Harikrishna - RMITS
	 * @param CreationType
	 * @param SheetName
	 * @param Currency
	 * @param BankName
	 */

	public void BPThroughPendingDocsAdvanceWithNewReference(String CreationType, String SheetName, String Currency,
			String BankName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingCRDocsButton();
		clickPendingPayablesButtonInPendCRDocs();
		chooseDocumentNumberInPendingPayablesScreenAdvanceMPMB(VariableHandler.getMultiplePBDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentType"), commonFunc.getDataFromTestDataSheet("BankPayMode"),
				BankName, CreationType, SheetName);
		getBankPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		sendReferenceNumberInBP(commonFunc.getDataFromTestDataSheet("Reference Number"));
		commonFunc.checkRefreshCellsDisappear();
		clickBPHeaderWiseSaveButton();
		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = 1; i < BankPayments.length; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String PartyName = BankPayments[i][2];
			String EntityName = BankPayments[i][3];
			String CPCCodeDesc = BankPayments[i][4];
			String BillNumber = BankPayments[i][5];
			String PayAmount = BankPayments[i][6];
			String DRCR = BankPayments[i][7];
			String CURCY = BankPayments[i][8];
			String EXRate = BankPayments[i][9];
			String TransCURCY = BankPayments[i][10];
			String BaseCURCY = BankPayments[i][34];
			String AssesableValue = BankPayments[i][14];
			if (!GLAccountDesc.equals("")) {
				clickLineWiseViewButton(i);
				clickAddButtonInViewDetails();
				addBillNumberAndPayAmount(BillNumber, PayAmount);
				clickViewDetailsSaveButton();
				clickBankViewDetailsCloseButton();
//				for (int j = 1; j < BankPayments.length; j++) {
//					String GLAccountDesc1 = BankPayments[j][1];
//					if (!GLAccountDesc1.equals("")) {
//						if (!Currency.equals("INR")) {
//							excelUtils.writeDataInExcel(Filepath, SheetName, i, 11,
//									dbCommonFunctions.ExchangeRateDetails(Currency,
//											commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
//						} else {
//							excelUtils.writeDataInExcel(Filepath, SheetName, i, 11, "1");
//						}
//						verifyBankPaymentsLineDetailsWithDB(SheetName, i);
//					}
//				}
			}
			if (!Currency.equals("INR") || (BankName.equals("JP MORGAN CHASE"))) {
				enterRealizedAmountInBPHeader(BaseCURCY);
			}
//			if (Currency.equals("INR")) {
//				if (BankName.equals("JP MORGAN CHASE")) {
//					excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNRINR"), 1, 6,
//							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
//									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
//					excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNRINR"), 1, 7,
//							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
//									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
//					excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNRINR"), 2, 6,
//							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
//									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
//					excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNRINR"), 2, 7,
//							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
//									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
//				} else {
//					excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNR"), 1, 6,
//							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
//									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
//					excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNR"), 1, 7,
//							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
//									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
//					excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNR"), 2, 6,
//							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
//									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
//					excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNR"), 2, 7,
//							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
//									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
//				}
//			}
		}
		clickCreateJournalButton();
//		if (Currency.equals("INR")) {
//			if (BankName.equals("JP MORGAN CHASE")) {
//				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalNRINR"),
//						VariableHandler.getBankPaymentOrReceiptDocNumber());
//				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNRINR"), 1, 6, "0");
//				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNRINR"), 1, 7, "0");
//				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNRINR"), 2, 6, "0");
//				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNRINR"), 2, 7, "0");
//			} else {
//				bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("BPJournalNR"),
//						VariableHandler.getBankPaymentOrReceiptDocNumber());
//				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNR"), 1, 6, "0");
//				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNR"), 1, 7, "0");
//				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNR"), 2, 6, "0");
//				excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("BPJournalNR"), 2, 7, "0");
//			}
//		}
//		verifyOverallHeaderAmountWithDB(SheetName);
//		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			checkGLMControlBPVAutoClearAndVerifyBPVDocumentStatus(BankName, Currency);
		}
	}

	/**
	 * This Method Is Used to Select the Bank Payment pending Records In the Pending
	 * Party Documnets IN the bank Payment Screen
	 * 
	 * @author Harikrishna _ RMITS
	 * @param PartyName
	 * @param VoucherNumber.get(i)
	 */
	public void selectAdvancePartyDocumentBasedOnPBThroughAdd(String PartyName, ArrayList<String> VoucherNumber, int i1,
			String SheetName) {
		flag = webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
		if (flag) {
			String GetTextPartyName = webDB.getDomPropertyFromElement(
					BankPaymentLocators.BankPendingPartyDocumentsPartyNameInputField, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			int a = i1 * 2;
			if (GetTextPartyName.equals(PartyName)) {
				for (int i = a - 1; i <= a; i++) {
					commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.BankPartyDocNumberLovButton,
							VoucherNumber.get(i - 1));
					webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
					flag = webDB.waitForElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
							ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.doubleClickAnElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
								ElementType.Xpath);
						flag = webDB.waitForElement(BankPaymentLocators.OverAllSelectCheckBox, ElementType.Xpath);
						if (flag) {
							commonFunc.checkRefreshCellsDisappear();
//							verifyPendingPayablesPBDocumentThroughAddButton(SheetName, VoucherNumber.get(i - 1), i);
							webDB.clickAnElement(BankPaymentLocators.OverAllSelectCheckBox, ElementType.Xpath);
							commonFunc.hardWait(6000);
							flag = webDB.waitForElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "In Pending Party Documents This Voucher Number "
										+ VoucherNumber.get(i - 1) + " Is Selected Successfully");
							}
						}
					}
				}
				commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.BankPartyDocNumberLovButton,
						VariableHandler.getMultipleBankDocs().get(0));
				webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
						ElementType.Xpath);
				if (flag) {
					commonFunc.checkRefreshCellsDisappear();
					webDB.doubleClickAnElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
							ElementType.Xpath);
					flag = webDB.waitForElement(BankPaymentLocators.OverAllSelectCheckBox, ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
//						verifyPendingPayablesPBDocumentThroughAddButton(SheetName, VariableHandler.getMultipleBankDocs().get(0), 1);
						webDB.clickAnElement(BankPaymentLocators.OverAllSelectCheckBox, ElementType.Xpath);
						commonFunc.hardWait(6000);
						flag = webDB.waitForElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "In Pending Party Documents This Voucher Number "
									+ VariableHandler.getMultipleBankDocs().get(0) + " Is Selected Successfully");
						}
					}
				}
				webDB.moveToElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton, ElementType.Xpath);
				webDB.javaScriptClickAnElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton,
						ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(BankPaymentLocators.BankHeaderCreateJournalButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "OK Button Is Clicked Successfully In The Pending Party Documents");
				}
			} else {
				logs.logging("INFO",
						"Bank Payment Line Wise Party Name And Pending Party Documents Party Name Is not Getting Matched");
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
	public void chooseAdvancePartyNameAndClickSearchButtonInPendingPartyDocsMPMB(ArrayList<String> VoucherNumber,
			String SheetName) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
		System.out.println(VoucherNumber.size());
		if (flag) {
			for (int i = 1; i <= VoucherNumber.size(); i++) {
				System.out.println(2);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.BankPartyDocNumberLovButton,
						VoucherNumber.get(i - 1));
				webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
							ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					String GetTextDocumentNumber = webDB.getTextFromElement(
							BankPaymentLocators.PendingPayablesDocumentNumberField, ElementType.Xpath);
					logs.logging("INFO", "Get Text Document Number Is " + GetTextDocumentNumber);
					commonFunc.hardWait(1000);
					commonFunc.checkRefreshCellsDisappear();
					if (GetTextDocumentNumber.equals(VoucherNumber.get(i - 1))) {
//						verifyPendingPayablesPBDocumentThroughAddButton(SheetName, VoucherNumber.get(i - 1), i);
						flag = webDB.waitForElement(BankPaymentLocators.OverAllSelectCheckBox, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(BankPaymentLocators.OverAllSelectCheckBox, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(3000);
							flag = webDB.waitForElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "In Pending Party Documents This Voucher Number "
										+ VoucherNumber.get(i - 1) + " Is Selected Successfully");
							}
						}
					} else {
						logs.logging("INFO",
								"Bank Payment Pending Line Wise Party Document Number And Pending Party Documents Number Is not Getting Matched");
					}
				}
			}
			commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.BankPartyDocNumberLovButton,
					VariableHandler.getMultipleBankDocs().get(0));
			webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
			flag = webDB.waitForElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
					ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
						ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				String GetTextDocumentNumber = webDB
						.getTextFromElement(BankPaymentLocators.PendingPayablesDocumentNumberField, ElementType.Xpath);
				logs.logging("INFO", "Get Text Document Number Is " + GetTextDocumentNumber);
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				if (GetTextDocumentNumber.contains(VariableHandler.getMultipleBankDocs().get(0))) {
//					verifyPendingPayablesPBDocumentThroughAddButton(SheetName, VariableHandler.getMultipleBankDocs().get(0), i);
					flag = webDB.waitForElement(BankPaymentLocators.OverAllSelectCheckBox, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(BankPaymentLocators.OverAllSelectCheckBox, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(3000);
						flag = webDB.waitForElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "In Pending Party Documents This Voucher Number "
									+ VariableHandler.getMultipleBankDocs().get(0) + " Is Selected Successfully");
						}
					}
				} else {
					logs.logging("INFO",
							"Bank Payment Pending Line Wise Party Document Number And Pending Party Documents Number Is not Getting Matched");
				}
			}
			commonFunc.hardWait(1500);
			flag = webDB.waitForElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton, ElementType.Xpath);
				webDB.javaScriptClickAnElement(BankPaymentLocators.BankPendingPartyDocumentScreenOkButton,
						ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(BankPaymentLocators.BankHeaderCreateJournalButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Ok Button Is Clicked Successfully");
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
	public void chooseDocumentNumberInPendingPayablesScreenAdvanceMPMB(ArrayList<String> DocumentNumber, String Type,
			String PayMode, String BankName, String CreationType, String SheetName) {
		flag = webDB.waitForElement(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
			for (int i = 1; i <= DocumentNumber.size(); i++) {
				commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.PendingPayablesDocNumberLovButton,
						DocumentNumber.get(i - 1));
				webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
							ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					String GetTextDocumentNumber = webDB.getTextFromElement(
							BankPaymentLocators.PendingPayablesDocumentNumberField, ElementType.Xpath);
					commonFunc.hardWait(1000);
					commonFunc.checkRefreshCellsDisappear();
					if (GetTextDocumentNumber.equals(DocumentNumber.get(i - 1))) {
						commonFunc.hardWait(1000);
//						verifyPendingPayablesPBDocumentThroughAddButton(SheetName, DocumentNumber.get(i - 1), i);
						webDB.clickAnElement(BankPaymentLocators.OverAllSelectCheckBox, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(1500);
						flag = webDB.waitForElement(BankPaymentLocators.PendingPayablesCreateButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "This Purchase Bills Document " + DocumentNumber.get(i - 1)
									+ " Is Selected Successfully");
							if (DocumentNumber.size() > 1) {
								webDB.clickAnElement(BankPaymentLocators.BankPendingPartyDocumentClearButton,
										ElementType.Xpath);
								flag = webDB.waitForElement(
										BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(
											BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
											ElementType.Xpath);
									commonFunc.checkRefreshCellsDisappear();
								}
							}
						}
					} else {
						logs.logging("INFO", "Some Other Document Is Filtered");
					}
				}
			}
			commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.PendingPayablesDocNumberLovButton,
					VariableHandler.getMultipleBankDocs().get(0));
			webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
			flag = webDB.waitForElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
					ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
						ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				String GetTextDocumentNumber = webDB
						.getTextFromElement(BankPaymentLocators.PendingPayablesDocumentNumberField, ElementType.Xpath);
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				if (GetTextDocumentNumber.contains(VariableHandler.getMultipleBankDocs().get(0))) {
					commonFunc.hardWait(1000);
//					verifyPendingPayablesPBDocumentThroughAddButton(SheetName, DocumentNumber.get(i - 1), i);
					webDB.clickAnElement(BankPaymentLocators.OverAllSelectCheckBox, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(1500);
					flag = webDB.waitForElement(BankPaymentLocators.PendingPayablesCreateButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "This Purchase Bills Document "
								+ VariableHandler.getMultipleBankDocs().get(0) + " Is Selected Successfully");
						if (DocumentNumber.size() > 1) {
							webDB.clickAnElement(BankPaymentLocators.BankPendingPartyDocumentClearButton,
									ElementType.Xpath);
							flag = webDB.waitForElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
									ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(BankPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
										ElementType.Xpath);
								commonFunc.checkRefreshCellsDisappear();
							}
						}
					}
				} else {
					logs.logging("INFO", "Some Other Document Is Filtered");
				}
			}
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(2000);
			webDB.moveToElement(BankPaymentLocators.PendingPayablesCreateButton, ElementType.Xpath);
			webDB.javaScriptClickAnElement(BankPaymentLocators.PendingPayablesCreateButton, ElementType.Xpath);
			logs.logging("INFO", "Create Button Is Clicked Successfully");
			commonFunc.checkRefreshCellsDisappear();
			if (!CreationType.equals("Single")) {
				flag = webDB.waitForElement(BankPaymentLocators.PendingPayablesMergeLabelInPending, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankPaymentLocators.PendingPayablesMergeLabelInPending, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
				}
			}
			flag = webDB.waitForElement(BankPaymentLocators.PendingPayablesTypeField, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownByText(BankPaymentLocators.PendingPayablesTypeDropDown, Type, ElementType.Xpath);
				logs.logging("INFO", "Type Successfully Choosen As " + Type);
				if (Type.equals("Bank")) {
					flag = webDB.waitForElement(BankPaymentLocators.PendingpayablesPaymodeField, ElementType.Xpath);
					if (flag) {
						webDB.selectDropDownByText(BankPaymentLocators.PendingPayablesPayModeDropDown, PayMode,
								ElementType.Xpath);
						logs.logging("INFO", "Pay Mode Successfully Choosen As " + PayMode);
					}
				}
				commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.PendingPayablesBankNameLOVBUtton,
						BankName);
				webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(BankPaymentLocators.PendingPayablesOkButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BankPaymentLocators.PendingPayablesOkButton, ElementType.Xpath);
					webDB.switchToDefaultContent();
					if (Type.equals("Bank")) {
						flag = webDB.waitForElement(BankPaymentLocators.BankPaymentScreenName, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"OK Button Is Clicked Successfully And Navigated To Bank Payment Screen");
						}
					} else {
						flag = webDB.waitForElement(BankPaymentLocators.CashPaymentScreenName, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"OK Button Is Clicked Successfully And Navigated To Cash Payment Screen");
						}
					}
				}
			}
		}
		assertTrue(flag);
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
	public void verifyBankPaymentsLineDetailsWithDBAdvMPMB(String SheetName, int i1) {

		BankPayments = excelUtils.readAllDataInExcel(BPFilepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String GLAccountDesc = BankPayments[i][1];
			String PartyID = BankPayments[i][2];
			String PartyName = BankPayments[i][3];
			String Entity = BankPayments[i][6];
			String CPCDesc = BankPayments[i][7];
			String LineDrCr = BankPayments[i][10];
			String Curcy = BankPayments[i][11];
			String EXRate = BankPayments[i][12];
			String AmtInTC = BankPayments[i][13];
			String AmtInBC = BankPayments[i][14];
			String HSNCode = BankPayments[i][15];
			String AssesableValue = BankPayments[i][17];
			String CGST = BankPayments[i][18];
			String SGST = BankPayments[i][19];
			String IGST = BankPayments[i][20];
			String UTGST = BankPayments[i][21];
			String CESSPCT = BankPayments[i][22];
			String CESSAMT = BankPayments[i][23];
			String Location = BankPayments[i][24];
			String Unit = BankPayments[i][25];
			String PendingDrCr = BankPayments[i][26];
			String TotAmt = BankPayments[i][33];
			if (!GLAccountDesc.equals("")) {
				String SeqNo = String.valueOf(i);
				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBGLAccountDescColumnName")),
						GLAccountDesc);
				logs.logging("INFO",
						" Line Wise GL Account Desc Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getBankPaymentOrReceiptDocNumber());

//				salesOrdFunc.verifyAssertionBothValues(
//						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
//								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBSupplierDescColumnName")),
//						PartyName);
//				logs.logging("INFO",
//						" Line Wise Party Name Is Matched With DataBase Values Against This Bank Receipt Document "
//								+ VariableHandler.getBankPaymentOrReceiptDocNumber());

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
				if (SheetName.equals("BankPaymentExpGST")) {
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

					if (SheetName.contains("TDS")) {
						String POSheetName = BankPayments[i][4];
						String TDS = BankPayments[i][29];
						String TDSSourceAcc = BankPayments[i][30];
						String TDSSourcePercentage = BankPayments[i][31];
						String TDSSourceAssVal = BankPayments[i][32];
						String TDSSourceAmt = BankPayments[i][33];

						if (!POSheetName.equals("")) {
							salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankPaymentLineDetails(
									VariableHandler.getCashPaymentOrReceiptDocNumber(), SeqNo,
									commonFunc.getDataFromTestDataSheet("TDSColumnName")), TDS);
							logs.logging("INFO",
									" Line Wise TDS ID Is Matched With DataBase Values Against This Cash Receipt Document "
											+ VariableHandler.getCashPaymentOrReceiptDocNumber());

							salesOrdFunc.verifyAssertionBothValues(
									dbCommonFunctions.BankPaymentLineDetails(
											VariableHandler.getCashPaymentOrReceiptDocNumber(), SeqNo,
											commonFunc.getDataFromTestDataSheet("TDSSourceAccountColumnName")),
									TDSSourceAcc);
							logs.logging("INFO",
									" Line Wise TDS Source Account Is Matched With DataBase Values Against This Cash Receipt Document "
											+ VariableHandler.getCashPaymentOrReceiptDocNumber());

							salesOrdFunc.verifyAssertionBothValues(
									dbCommonFunctions.BankPaymentLineDetails(
											VariableHandler.getCashPaymentOrReceiptDocNumber(), SeqNo,
											commonFunc.getDataFromTestDataSheet("TDSSourcePctColumnName")),
									TDSSourcePercentage);
							logs.logging("INFO",
									" Line Wise TDS Source Account Is Matched With DataBase Values Against This Cash Receipt Document "
											+ VariableHandler.getCashPaymentOrReceiptDocNumber());

							salesOrdFunc.verifyAssertionBothValues(
									dbCommonFunctions.BankPaymentLineDetails(
											VariableHandler.getCashPaymentOrReceiptDocNumber(), SeqNo,
											commonFunc.getDataFromTestDataSheet("TDSSourceAssValueColumnName")),
									TDSSourceAssVal);
							logs.logging("INFO",
									" Line Wise TDS Source Assesable Value Is Matched With DataBase Values Against This Cash Receipt Document "
											+ VariableHandler.getCashPaymentOrReceiptDocNumber());

							salesOrdFunc.verifyAssertionBothValues(
									dbCommonFunctions.BankPaymentLineDetails(
											VariableHandler.getCashPaymentOrReceiptDocNumber(), SeqNo,
											commonFunc.getDataFromTestDataSheet("TDSSourceAmtColumnName")),
									TDSSourceAmt);
							logs.logging("INFO",
									" Line Wise TDS Source Amount Value Is Matched With DataBase Values Against This Cash Receipt Document "
											+ VariableHandler.getCashPaymentOrReceiptDocNumber());
						} else {
							salesOrdFunc.verifyAssertionBothValues(
									dbCommonFunctions.BankPaymentLineDetails(
											VariableHandler.getCashPaymentOrReceiptDocNumber(), SeqNo,
											commonFunc.getDataFromTestDataSheet("TDSSourcePctColumnName")),
									TDSSourcePercentage);
							logs.logging("INFO",
									" Line Wise TDS Source Account Is Matched With DataBase Values Against This Cash Receipt Document "
											+ VariableHandler.getCashPaymentOrReceiptDocNumber());

							salesOrdFunc.verifyAssertionBothValues(
									dbCommonFunctions.BankPaymentLineDetails(
											VariableHandler.getCashPaymentOrReceiptDocNumber(), SeqNo,
											commonFunc.getDataFromTestDataSheet("TDSSourceAssValueColumnName")),
									TDSSourceAssVal);
							logs.logging("INFO",
									" Line Wise TDS Source Assesable Value Is Matched With DataBase Values Against This Cash Receipt Document "
											+ VariableHandler.getCashPaymentOrReceiptDocNumber());

							salesOrdFunc.verifyAssertionBothValues(
									dbCommonFunctions.BankPaymentLineDetails(
											VariableHandler.getCashPaymentOrReceiptDocNumber(), SeqNo,
											commonFunc.getDataFromTestDataSheet("TDSSourceAmtColumnName")),
									TDSSourceAmt);
							logs.logging("INFO",
									" Line Wise TDS Source Amount Value Is Matched With DataBase Values Against This Cash Receipt Document "
											+ VariableHandler.getCashPaymentOrReceiptDocNumber());
						}
					}
				}
			}
		}
	}

	/**
	 * This Method Is Used To Add the Bank Payment Line Details
	 * 
	 * @author Vimal - RMITS
	 * @param GLAccount
	 * @param CPCcode
	 */
	public void addBankPaymentLineDetailsGlaactAndCPC(String GLAccount, String CPCcode) {
		flag = webDB.waitForElement(BankPaymentLocators.BankLineGLAccountField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BankLineGLAccountField, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.BankLineGLAccountLovButton, GLAccount);
			webDB.pressTabKey();
//			commonFunc.hardWait(500);
//			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(BankPaymentLocators.BankLineCPCField, ElementType.Xpath);
			if (flag) {
//				String GetTextCPC = webDB.getTextFromElement(BankPaymentLocators.BankLineCPCField, ElementType.Xpath);
//				if (!GetTextCPC.equals(CPCcode)) {
				webDB.clickAnElement(BankPaymentLocators.BankLineCPCField, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(BankPaymentLocators.BankLineCPCLovButton, CPCcode);
//				}
//				commonFunc.checkRefreshCellsDisappear();
				webDB.pressTabKey();
			}
		}
		assertTrue(flag);
	}

}