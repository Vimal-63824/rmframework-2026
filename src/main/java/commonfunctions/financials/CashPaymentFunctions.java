package commonfunctions.financials;

/**
 * @author HariKrishna-RMITS
 * @company Galaxy Weblinks October 16, 2025
 */
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.expectThrows;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.commons.lang3.RandomStringUtils;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonfunctions.supplychainmanagement.DirectGrnPageFunctions;
import commonfunctions.supplychainmanagement.PurchaseOrderFunctions;
import commonfunctions.supplychainmanagement.SalesInvoiceFunctions;
import commonfunctions.supplychainmanagement.SalesOrderFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.financials.CashPaymentLocators;
import commonlocators.financials.CashPaymentLocators;
import commonlocators.financials.CashPaymentLocators;
import commonlocators.financials.CashPaymentLocators;
import commonlocators.financials.CashPaymentLocators;
import commonlocators.financials.CashPaymentLocators;
import commonlocators.financials.CashPaymentLocators;
import commonlocators.financials.BankPaymentLocators;
import commonlocators.financials.BankReceiptLocators;
import commonlocators.financials.CashReceiptLocators;
import commonlocators.financials.PurchaseBillsLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import commonlocators.supplychainmanagement.SalesOrderLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class CashPaymentFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	private static DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();
	static ApprovalPageFunctions approvalPageFunc = new ApprovalPageFunctions();
	static SalesOrderFunctions salesOrdFunc = new SalesOrderFunctions();
	static DirectGrnPageFunctions Grnfunc = new DirectGrnPageFunctions();
	static SalesInvoiceFunctions salesInvoiceFunc = new SalesInvoiceFunctions();
	static BankPaymentFunctions bankpaymentfunc = new BankPaymentFunctions();
	static BankReceiptFunctions bankreceiptfunc = new BankReceiptFunctions();
	static PurchaseOrderFunctions PurFunc = new PurchaseOrderFunctions();

	LocalDate date = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	String Today = date.format(formatter);
	LocalDateTime myDateObj = LocalDateTime.now().plusDays(1);
	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	String tomorrowDate = myDateObj.format(myFormatObj);
	String[][] CashPayments = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
	ExcelUtils excelUtils = new ExcelUtils();
	String FilepathCP = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
			+ "Financials2.xlsx";
	ArrayList<String> PBDocumentNumber = new ArrayList<String>();
	public ArrayList<String> MultiAdvPaymentDocumentNumber = new ArrayList<String>();
	String CPGrnExcelFileName = "DirectGrnDetailsHK2.xlsx";

	/**
	 * This Method Used To Verify Store Purchase Voucher Number
	 * 
	 * @author Deepan-GWL
	 */
	public void storePurchaseVoucherNumber() {
		String Number = webDB.getTextFromElement(CashPaymentLocators.VoucherNumber, ElementType.Xpath);
		VariableHandler.setPbNumber(Number);
		PBDocumentNumber.add(VariableHandler.getPbNumber());
		logs.logging("INFO", "Get Text Purchase Bill Document Number is : " + VariableHandler.getPbNumber());

	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Deepan-GWL
	 * @param DocNo
	 */
	public void verifyPendingPurchaseBill(String DocNo) {
		flag = webDB.waitForElement(CashPaymentLocators.PendingPurchaseBill, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Purchase Bill Is Displayed");
			webDB.clickAnElement(CashPaymentLocators.PendingPurchaseBill, ElementType.Xpath);
			flag = webDB.waitForElement(CashPaymentLocators.PendingGrn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Grn Is Displayed");
				webDB.clickAnElement(CashPaymentLocators.PendingGrn, ElementType.Xpath);
				flag = webDB.waitForElement(CashPaymentLocators.Iframe, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Iframe Is Displayed");
					webDB.switchToFrame(CashPaymentLocators.Iframe, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(CashPaymentLocators.GrnNo, DocNo);
					flag = webDB.waitForElement(CashPaymentLocators.Iframe, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(CashPaymentLocators.Iframe, ElementType.Xpath);
						flag = webDB.waitForElement(CashPaymentLocators.SearchButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Switched To Iframe");
							webDB.clickAnElement(CashPaymentLocators.SearchButton, ElementType.Xpath);
							flag = webDB.waitForElement(CashPaymentLocators.CheckBox(DocNo), ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "CheckBox IS Displayed");
								commonFunc.hardWait(1000);
								webDB.clickAnElement(CashPaymentLocators.CheckBox(DocNo), ElementType.Xpath);
								flag = webDB.waitForElement(CashPaymentLocators.CreateDoc, ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Create Documents Is Displayed");
									commonFunc.hardWait(2000);
									webDB.clickAnElement(CashPaymentLocators.CreateDoc, ElementType.Xpath);
									commonFunc.hardWait(500);
									flag = webDB.waitForElement(CashPaymentLocators.OkButton, ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Okay Button Is Displayed");
										webDB.clickAnElement(CashPaymentLocators.OkButton, ElementType.Xpath);
										webDB.switchToDefaultContent();
										flag = webDB.waitForElement(CashPaymentLocators.LocationId, ElementType.Xpath);
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
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Deepan-GWL
	 * @param DocNo
	 */
	public void verifyPendingPurchaseBillMultiUnits(String DocNo, String Unit) {
		flag = webDB.waitForElement(CashPaymentLocators.PendingPurchaseBill, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Purchase Bill Is Displayed");
			webDB.clickAnElement(CashPaymentLocators.PendingPurchaseBill, ElementType.Xpath);
			flag = webDB.waitForElement(CashPaymentLocators.PendingGrn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Grn Is Displayed");
				webDB.clickAnElement(CashPaymentLocators.PendingGrn, ElementType.Xpath);
				flag = webDB.waitForElement(CashPaymentLocators.Iframe, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Iframe Is Displayed");
					webDB.switchToFrame(CashPaymentLocators.Iframe, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(CashPaymentLocators.GrnNo, DocNo);
					flag = webDB.waitForElement(CashPaymentLocators.Iframe, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(CashPaymentLocators.Iframe, ElementType.Xpath);
						flag = webDB.waitForElement(CashPaymentLocators.SearchButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Switched To Iframe");
							webDB.clickAnElement(CashPaymentLocators.SearchButton, ElementType.Xpath);
							flag = webDB.waitForElement(CashPaymentLocators.CheckBox(DocNo), ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "CheckBox IS Displayed");
								commonFunc.hardWait(1000);
								webDB.clickAnElement(CashPaymentLocators.CheckBox(DocNo), ElementType.Xpath);
								flag = webDB.waitForElement(CashPaymentLocators.CreateDoc, ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Create Documents Is Displayed");
									commonFunc.hardWait(2000);
									webDB.clickAnElement(CashPaymentLocators.CreateDoc, ElementType.Xpath);
									if (Unit.equals("U50")) {
										flag = webDB.waitForElement(CashPaymentLocators.OkButton, ElementType.Xpath);
										if (flag) {
											commonFunc.searchAndSelectValueInFilters(
													CashPaymentLocators.CashPaymentPBPrefixLov,
													commonFunc.getDataFromTestDataSheet("PBU50prefix"));
											webDB.pressTabKey();
											flag = webDB.waitForElement(CashPaymentLocators.Iframe, ElementType.Xpath);
											if (flag) {
												logs.logging("INFO", "Iframe Is Displayed");
												webDB.switchToFrame(CashPaymentLocators.Iframe, ElementType.Xpath);
											}
										}
									}
									flag = webDB.waitForElement(CashPaymentLocators.OkButton, ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Okay Button Is Displayed");
										webDB.clickAnElement(CashPaymentLocators.OkButton, ElementType.Xpath);
										webDB.switchToDefaultContent();
										flag = webDB.waitForElement(CashPaymentLocators.LocationId, ElementType.Xpath);
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
	 * This Method Used To Verify To Create Journal And Post
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateJournalAndPost() {
		flag = webDB.waitForElement(CashPaymentLocators.CreateJournal, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CashPaymentLocators.CreateJournal, ElementType.Xpath);
			logs.logging("INFO", "Create Journal Button Is Displayed");
			webDB.clickAnElement(CashPaymentLocators.CreateJournal, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(1500);
			flag = webDB.waitForElement(CashPaymentLocators.PostButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Post Button Is Displayed");
				webDB.clickAnElement(CashPaymentLocators.PostButton, ElementType.Xpath);
				flag = (webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 10)
						|| webDB.waitForElement(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath));
			}
		}
		assertTrue(flag);
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
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Deepan-GWL
	 * @param DocNo
	 * @throws IOException
	 */
	public void createPurchaseBillsFromPendingGRNMultiUnits(String DocumentNumber, String Units) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBillMultiUnits(DocumentNumber, Units);
		verifyCreateJournalAndPost();
		VariableHandler.setMultiplePBDocs(PBDocumentNumber);
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
					webDB.getDataFromProperties("HodEmpId"), Units, System.getProperty("HariUsername"));
		}
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
			String ForwardEmployee = webDB.getDomPropertyFromElement(CashPaymentLocators.FrwdPersonField,
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
	 * This Method Is Used To Click the Add Button In The Payment / Receipts Find
	 * Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickAddButtonInPaymentsRecipts() {
		flag = webDB.waitForElement(CashPaymentLocators.BankAddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashPaymentLocators.BankAddButton, ElementType.Xpath);
			flag = webDB.waitForElement(CashPaymentLocators.BankPrefixLovButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Payments / Receipts Add Button Is clicked Sucessfully");
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
	 * This Method Is Used To Create The Cash Receipt Header Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param DocumentMode
	 * @param CashierName
	 * @param VoucherDate
	 * @param Prefix
	 */
	public void createCashPaymentEntry(String DocumentMode, String CashierName, String VoucherDate, String Prefix) {
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
	 * This Method Is Used to Verify Bank Payment Header details With DataBase
	 * Values
	 * 
	 * @author Harikrishna - RMITS
	 * 
	 * @param BankName
	 * @param Prefix
	 * @param Curcy
	 */
	public void verifyCashPaymentDetailsWithDB(String CashierName, String Prefix, String Curcy) {
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
	public void clickCPLineAddButton() {
		flag = webDB.waitForElement(CashPaymentLocators.BankLineWiseAddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashPaymentLocators.BankLineWiseAddButton, ElementType.Xpath);
			flag = webDB.waitForElement(CashPaymentLocators.BankLineGLAccountField, ElementType.Xpath);
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
	public void addCashPaymentLineDetails(String GLAccount, String CPCcode) {
		flag = webDB.waitForElement(CashPaymentLocators.BankLineGLAccountField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashPaymentLocators.BankLineGLAccountField, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(CashPaymentLocators.BankLineGLAccountLovButton, GLAccount);
			webDB.pressTabKey();
//			commonFunc.hardWait(500);
//			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(CashPaymentLocators.BankLineCPCField, ElementType.Xpath);
			if (flag) {
//				String GetTextCPC = webDB.getTextFromElement(CashPaymentLocators.BankLineCPCField, ElementType.Xpath);
//				if (!GetTextCPC.equals(CPCcode)) {
				webDB.clickAnElement(CashPaymentLocators.BankLineCPCField, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(CashPaymentLocators.BankLineCPCLovButton, CPCcode);
//				}
//				commonFunc.checkRefreshCellsDisappear();
//				webDB.pressTabKey();
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

	public void chooseLineWiseDRCRInCP(String DRCR) {
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

	public void chooseHSNCodeInCP(String HSNCode) {
		flag = webDB.waitForElement(CashPaymentLocators.BankReceiptHSNCodeField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashPaymentLocators.BankReceiptHSNCodeField, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(CashPaymentLocators.BankReceiptLineHsnCodeLov, HSNCode);
			webDB.pressTabKey();
		}
		assertTrue(flag);
	}

	public void chooseTDSInCP(String TDSSection) {
		flag = webDB.waitForElement(CashPaymentLocators.BankPaymentTDSField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashPaymentLocators.BankPaymentTDSField, ElementType.Xpath);
			logs.logging("INFO", "Bank Payment Line Wise TDS Field Is Clicked Successfully");
			commonFunc.searchAndSelectValueInFilters(CashPaymentLocators.BankPaymentTDSLovButton, TDSSection);
			webDB.pressTabKey();
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(CashPaymentLocators.BankPaymentSourcePartyField, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CashPaymentLocators.BankPaymentSourcePartyField, ElementType.Xpath);
				logs.logging("INFO", "Bank Payment Line Wise Source Party Field Is Clicked Successfully");
				commonFunc.searchAndSelectValueInFilters(CashPaymentLocators.BankPaymentSourcePartyLovButton,
						commonFunc.getDataFromTestDataSheet("POSupplierName"));
				webDB.pressTabKey();
				commonFunc.checkRefreshCellsDisappear();
			}
		}
	}

	public void clickApplyDeductionButtonInHeader() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElementUntilGivenTime(CashPaymentLocators.BankPaymentApplyDeduction, ElementType.Xpath, 10);
		if (flag) {
			webDB.clickAnElement(CashPaymentLocators.BankPaymentApplyDeduction, ElementType.Xpath);
			commonFunc.hardWait(200);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(CashPaymentLocators.BankPayment2ndLine, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Apply Deduction Button Is Clicked Successfully");
			}
		}
	}

	public void clickLineWiseGSTIconInCP(int i) {
		flag = webDB.waitForElement(CashPaymentLocators.BankLineGSTButton(i), ElementType.CssSelector);
		if (flag) {
			webDB.clickAnElement(CashPaymentLocators.BankLineGSTButton(i), ElementType.CssSelector);
			flag = webDB.waitForElement(BankReceiptLocators.BankReceiptGSTDetailsHeaderName, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Line Wise GST Details Button Is Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

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
									commonFunc.hardWait(1000);
									webDB.switchToDefaultContent();
									flag = webDB.waitForElement(BankReceiptLocators.BankReceiptGSTCloseButton,
											ElementType.Xpath);
									if (flag) {
										logs.logging("INFO",
												"Line Wise GST Details Save Button Is Clicked Successfully");
										webDB.clickAnElement(BankReceiptLocators.BankReceiptGSTCloseButton,
												ElementType.Xpath);
										flag = webDB.waitForElement(
												CashPaymentLocators.BankReceiptHeaderCreateJournalButton,
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
	 * This Method is Used to Click The Line Wise Save Button In the Bank Payment
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickLineWiseSaveButton() {
		flag = webDB.waitForElement(CashPaymentLocators.BankLineWiseSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(CashPaymentLocators.BankLineWiseSaveButton, ElementType.Xpath);
			commonFunc.hardWait(1500);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(CashPaymentLocators.BankLineWiseDeleteButton, ElementType.CssSelector);
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
		flag = webDB.waitForElement(CashPaymentLocators.BankLineViewButton(i), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashPaymentLocators.BankLineViewButton(i), ElementType.Xpath);
			flag = webDB.waitForElementUntilGivenTime(CashPaymentLocators.BankViewFrame, ElementType.Xpath, 20);
			if (flag) {
				webDB.switchToFrame(CashPaymentLocators.BankViewFrame, ElementType.Xpath);
				logs.logging("INFO", "Switch To Payment Details Frame Successfully");
				flag = webDB.waitForElement(CashPaymentLocators.BankViewAddButton, ElementType.Xpath);
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
		flag = webDB.waitForElement(CashPaymentLocators.BankViewAddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashPaymentLocators.BankViewAddButton, ElementType.Xpath);
			flag = webDB.waitForElement(CashPaymentLocators.BankAgainstRefFieldName, ElementType.Xpath);
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
		salesOrdFunc.verifyClickAndEnterValueInTableFields(CashPaymentLocators.BankBillDocField,
				CashPaymentLocators.BankBillNumberInputField, BillNumber);
		webDB.pressTabKey();
		salesOrdFunc.verifyClickAndEnterValueInTableFields(CashPaymentLocators.BankPayAmountField,
				CashPaymentLocators.BankPayAmountInputField, PayAmount);
		webDB.pressTabKey();
	}

	/**
	 * This Method Is Used to Click The View Details save Button In The Bank
	 * Payments
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickViewDetailsSaveButton() {
		flag = webDB.waitForElement(CashPaymentLocators.BankViewSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashPaymentLocators.BankViewSaveButton, ElementType.Xpath);
			flag = webDB.waitForElement(CashPaymentLocators.BankViewDeleteButton, ElementType.CssSelector);
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
		flag = webDB.waitForElement(CashPaymentLocators.BankViewScreenCloseButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashPaymentLocators.BankViewScreenCloseButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(CashPaymentLocators.BankLineAmountInTCField, ElementType.Xpath);
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
		flag = webDB.waitForElement(CashPaymentLocators.BankHeaderCreateJournalButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashPaymentLocators.BankHeaderCreateJournalButton, ElementType.Xpath);
			commonFunc.hardWait(500);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(CashPaymentLocators.BankHeaderViewJournalButton, ElementType.Xpath);
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
		flag = webDB.waitForElement(CashPaymentLocators.BankPostButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashPaymentLocators.BankPostButton, ElementType.Xpath);
			flag = webDB.waitForElement(CashPaymentLocators.BankWorkFlowApprovalFrame, ElementType.Xpath);
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
	public void clickLineWiseAddButton(int i) {
		flag = webDB.waitForElement(CashPaymentLocators.BankLineAddButton(i), ElementType.CssSelector);
		if (flag) {
			webDB.clickAnElement(CashPaymentLocators.BankLineAddButton(i), ElementType.CssSelector);
			flag = webDB.waitForElement(CashPaymentLocators.BankPendingPartyDocumentsButton, ElementType.Xpath);
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
		flag = webDB.waitForElement(CashPaymentLocators.BankPendingPartyDocumentsButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashPaymentLocators.BankPendingPartyDocumentsButton, ElementType.Xpath);
			flag = webDB.waitForElement(CashPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
			if (flag) {
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
		flag = webDB.switchToFrame(CashPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
		if (flag) {
			String GetTextPartyName = webDB.getDomPropertyFromElement(
					CashPaymentLocators.BankPendingPartyDocumentsPartyNameInputField, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			if (GetTextPartyName.equals(PartyName)) {
				for (int i = 1; i <= VoucherNumber.size(); i++) {
					commonFunc.searchAndSelectValueInFilters(CashPaymentLocators.BankPartyDocNumberLovButton,
							VoucherNumber.get(i - 1));
					webDB.switchToFrame(CashPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
					flag = webDB.waitForElement(CashPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
							ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.doubleClickAnElement(CashPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
								ElementType.Xpath);
						commonFunc.hardWait(500);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(
								CashPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i - 1)),
								ElementType.Xpath);
						if (flag) {
							commonFunc.checkRefreshCellsDisappear();
							verifyPendingPayablesPBDocumentThroughGetDoc(SheetName, VoucherNumber.get(i - 1), i); // TODO
							webDB.clickAnElement(
									CashPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i - 1)),
									ElementType.Xpath);
							commonFunc.hardWait(6000);
							flag = webDB.waitForElement(CashPaymentLocators.BankPendingPartyDocumentScreenOkButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "In Pending Party Documents This Voucher Number "
										+ VoucherNumber.get(i - 1) + " Is Selected Successfully");
							}
						}
					}
				}
				webDB.moveToElement(CashPaymentLocators.BankPendingPartyDocumentScreenOkButton, ElementType.Xpath);
				webDB.javaScriptClickAnElement(CashPaymentLocators.BankPendingPartyDocumentScreenOkButton,
						ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(CashPaymentLocators.BankHeaderCreateJournalButton, ElementType.Xpath);
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
	public void verifyPendingPayablesPBDocumentThroughGetDoc(String SheetName, String VoucherNumber, int i1) {
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
//		int a= i1*1;
		for (int i = i1; i <= i1; i++) {
//		int innerValue = (i1 % 2 == 1) ? 1 : 2;
//		  for (int i = innerValue; i <= innerValue; i++) {
//			for (int i = (i1 + 1) / 2; i <= (i1 + 1) / 2; i++){
//			for(int j = 1; j<BankPayments.length;j++) {
			System.out.println(i);
			String PartyID = CashPayments[i][2];
			String PartyName = CashPayments[i][3];
			String Entity = CashPayments[i][5];
			String CPCDesc = CashPayments[i][6];
			String Curcy = CashPayments[i][10];
			String EXRate = CashPayments[i][11];
			String Location = CashPayments[i][23];
			String Unit = CashPayments[i][24];
			String DrCr = CashPayments[i][25];
			String PayableAmount = CashPayments[i][26];
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
	 * This Method Is Used To Verify Pending Receivables Through Get Document With
	 * DB
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param VoucherNumber
	 * @param i1
	 */
	public void verifyPendingAdvancePayablesThroughGetDoc(String SheetName, String VoucherNumber, int i1) {
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
//		int a= i1*1;
		for (int i = i1; i <= i1; i++) {
//		int innerValue = (i1 % 2 == 1) ? 1 : 2;
//		  for (int i = innerValue; i <= innerValue; i++) {
//			for (int i = (i1 + 1) / 2; i <= (i1 + 1) / 2; i++){
//			for(int j = 1; j<BankPayments.length;j++) {
			System.out.println(i);
			String PartyID = CashPayments[i][2];
			String PartyName = CashPayments[i][3];
			String Entity = CashPayments[i][5];
			String CPCDesc = CashPayments[i][6];
			String Curcy = CashPayments[i][10];
			String EXRate = CashPayments[i][11];
			String Location = CashPayments[i][23];
			String Unit = CashPayments[i][24];
			String DrCr = CashPayments[i][25];
			String PayableAmount = CashPayments[i][26];
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

				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.CashPaymentAdvancePendingPayablesDetails(VoucherNumber,
								commonFunc.getDataFromTestDataSheet("BRDBPendingAdvanceReceivablesInvAmt")),
						PayableAmount);
				logs.logging("INFO", "In Advance Pending Payables For This Document Number " + VoucherNumber
						+ " Payable Amount Is getting Matched With DB");

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
	public void verifyCashPaymentsLineDetailsWithDB(String SheetName, int i1) {

		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = i1; i <= i1; i++) {
			String GLAccountDesc = CashPayments[i][1];
			String PartyID = CashPayments[i][2];
			String PartyName = CashPayments[i][3];
			String Entity = CashPayments[i][5];
			String CPCDesc = CashPayments[i][6];
			String LineDrCr = CashPayments[i][9];
			String Curcy = CashPayments[i][10];
			String EXRate = CashPayments[i][11];
			String AmtInTC = CashPayments[i][12];
			String AmtInBC = CashPayments[i][13];
			String HSNCode = CashPayments[i][14];
			String AssesableValue = CashPayments[i][16];
			String CGST = CashPayments[i][17];
			String SGST = CashPayments[i][18];
			String IGST = CashPayments[i][19];
			String UTGST = CashPayments[i][20];
			String CESSPCT = CashPayments[i][21];
			String CESSAMT = CashPayments[i][22];
			String Location = CashPayments[i][23];
			String Unit = CashPayments[i][24];
			String PendingDrCr = CashPayments[i][25];
			String TotAmt = CashPayments[i][28];
			if (!GLAccountDesc.equals("")) {
				String SeqNo = String.valueOf(i);
				salesOrdFunc.verifyAssertionBothValues(
						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getCashPaymentOrReceiptDocNumber(),
								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBGLAccountDescColumnName")),
						GLAccountDesc);
				logs.logging("INFO",
						" Line Wise GL Account Desc Is Matched With DataBase Values Against This Bank Receipt Document "
								+ VariableHandler.getCashPaymentOrReceiptDocNumber());

//				salesOrdFunc.verifyAssertionBothValues(
//						dbCommonFunctions.BankPaymentLineDetails(VariableHandler.getBankPaymentOrReceiptDocNumber(),
//								SeqNo, commonFunc.getDataFromTestDataSheet("BPDBSupplierDescColumnName")),
//						PartyName);
//				logs.logging("INFO",
//						" Line Wise Party Name Is Matched With DataBase Values Against This Bank Receipt Document "
//								+ VariableHandler.getBankPaymentOrReceiptDocNumber());

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
				if (SheetName.equals("BankPaymentExpGST")) {
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
				if (SheetName.contains("TDS")) {
					String POSheetName = CashPayments[i][4];
					String TDS = CashPayments[i][29];
					String TDSSourceAcc = CashPayments[i][30];
					String TDSSourcePercentage = CashPayments[i][31];
					String TDSSourceAssVal = CashPayments[i][32];
					String TDSSourceAmt = CashPayments[i][33];

//					}
					if (!SheetName.equals("BankPaymentExpGSTTDS")) {
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
						}
					} else {
						String HSNCode1 = CashPayments[i][14];
						String TDS1 = CashPayments[i][33];
						String TDSSourceAcc1 = CashPayments[i][34];
						String TDSSourcePercentage1 = CashPayments[i][35];
						String TDSSourceAssVal1 = CashPayments[i][36];
						String TDSSourceAmt1 = CashPayments[i][37];
						if (!HSNCode1.equals("")) {

							salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankPaymentLineDetails(
									VariableHandler.getCashPaymentOrReceiptDocNumber(), SeqNo,
									commonFunc.getDataFromTestDataSheet("TDSColumnName")), TDS1);
							logs.logging("INFO",
									" Line Wise TDS ID Is Matched With DataBase Values Against This Cash Receipt Document "
											+ VariableHandler.getCashPaymentOrReceiptDocNumber());

							salesOrdFunc.verifyAssertionBothValues(
									dbCommonFunctions.BankPaymentLineDetails(
											VariableHandler.getCashPaymentOrReceiptDocNumber(), SeqNo,
											commonFunc.getDataFromTestDataSheet("TDSSourceAccountColumnName")),
									TDSSourceAcc1);
							logs.logging("INFO",
									" Line Wise TDS Source Account Is Matched With DataBase Values Against This Cash Receipt Document "
											+ VariableHandler.getCashPaymentOrReceiptDocNumber());

							salesOrdFunc.verifyAssertionBothValues(
									dbCommonFunctions.BankPaymentLineDetails(
											VariableHandler.getCashPaymentOrReceiptDocNumber(), SeqNo,
											commonFunc.getDataFromTestDataSheet("TDSSourcePctColumnName")),
									TDSSourcePercentage1);
							logs.logging("INFO",
									" Line Wise TDS Source Account Is Matched With DataBase Values Against This Cash Receipt Document "
											+ VariableHandler.getCashPaymentOrReceiptDocNumber());

							salesOrdFunc.verifyAssertionBothValues(
									dbCommonFunctions.BankPaymentLineDetails(
											VariableHandler.getCashPaymentOrReceiptDocNumber(), SeqNo,
											commonFunc.getDataFromTestDataSheet("TDSSourceAssValueColumnName")),
									TDSSourceAssVal1);
							logs.logging("INFO",
									" Line Wise TDS Source Assesable Value Is Matched With DataBase Values Against This Cash Receipt Document "
											+ VariableHandler.getCashPaymentOrReceiptDocNumber());

							salesOrdFunc.verifyAssertionBothValues(
									dbCommonFunctions.BankPaymentLineDetails(
											VariableHandler.getCashPaymentOrReceiptDocNumber(), SeqNo,
											commonFunc.getDataFromTestDataSheet("TDSSourceAmtColumnName")),
									TDSSourceAmt1);
							logs.logging("INFO",
									" Line Wise TDS Source Amount Value Is Matched With DataBase Values Against This Cash Receipt Document "
											+ VariableHandler.getCashPaymentOrReceiptDocNumber());

						}
					}
//					else {
//						salesOrdFunc.verifyAssertionBothValues(
//								dbCommonFunctions.BankPaymentLineDetails(
//										VariableHandler.getCashPaymentOrReceiptDocNumber(), SeqNo,
//										commonFunc.getDataFromTestDataSheet("TDSSourcePctColumnName")),
//								TDSSourcePercentage);
//						logs.logging("INFO",
//								" Line Wise TDS Source Account Is Matched With DataBase Values Against This Cash Receipt Document "
//										+ VariableHandler.getCashPaymentOrReceiptDocNumber());
//
//						salesOrdFunc.verifyAssertionBothValues(
//								dbCommonFunctions.BankPaymentLineDetails(
//										VariableHandler.getCashPaymentOrReceiptDocNumber(), SeqNo,
//										commonFunc.getDataFromTestDataSheet("TDSSourceAssValueColumnName")),
//								TDSSourceAssVal);
//						logs.logging("INFO",
//								" Line Wise TDS Source Assesable Value Is Matched With DataBase Values Against This Cash Receipt Document "
//										+ VariableHandler.getCashPaymentOrReceiptDocNumber());
//
//						salesOrdFunc.verifyAssertionBothValues(dbCommonFunctions.BankPaymentLineDetails(
//								VariableHandler.getCashPaymentOrReceiptDocNumber(), SeqNo,
//								commonFunc.getDataFromTestDataSheet("TDSSourceAmtColumnName")), TDSSourceAmt);
//						logs.logging("INFO",
//								" Line Wise TDS Source Amount Value Is Matched With DataBase Values Against This Cash Receipt Document "
//										+ VariableHandler.getCashPaymentOrReceiptDocNumber());
//					}
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
			flag = webDB.waitForElement(CashPaymentLocators.BankHeaderCreateJournalButton, ElementType.Xpath);
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
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String GLAccountDesc = CashPayments[i][1];
			String ChargeAmt = CashPayments[i][8];
			String AmtInTC = CashPayments[i][12];
			String TotalAmount = CashPayments[i][28];
			String ChargesFlag = CashPayments[i][29];
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
		for (int i = 1; i < CashPayments.length; i++) {
			String GLAccountDesc = CashPayments[i][1];
			if (!GLAccountDesc.equals("")) {
				for (int j = 1; j <= 2; j++) {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 25 + j, "0");
				}
			}
		}
	}

	/**
	 * This Method Is Used To Click The Get Document Button In The Header of The
	 * Bank Payment Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickGetDocumentInBPHeader() {
		flag = webDB.waitForElement(CashPaymentLocators.BankPaymentGetDocumentButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashPaymentLocators.BankPaymentGetDocumentButton, ElementType.Xpath);
			flag = webDB.waitForElement(CashPaymentLocators.BankPendingPartyDocumentsButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Get Document Button In The Bank Payment Header Is Clicked Successfully");
			}
		}
		assertTrue(flag);
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
	 * This Method Is Used To Check GLM Control Of BPV Auto Clear Flag And verify
	 * Bank Payment Document Status
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void VerifyCPVDocumentStatus() {
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("CRVApprovedStatus"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		logs.logging("INFO",
				"Verified Cash Receipt Voucher Document Status Is Approved Against This Cash Receipt Voucher Document "
						+ VariableHandler.getCashPaymentOrReceiptDocNumber());
		;
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
		flag = webDB.switchToFrame(CashPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
		System.out.println(VoucherNumber.size());
		if (flag) {
			for (int i = 1; i <= VoucherNumber.size(); i++) {
				System.out.println(2);
//				commonFunc.searchAndSelectValueInFilters(
//						CashPaymentLocators.BankPaymentPendingPartyDocumentPartyLovButton, PartyName);
//				webDB.switchToFrame(CashPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
//				flag = webDB.waitForElement(CashPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
//						ElementType.Xpath);
//				if (flag) {
//					webDB.clickAnElement(CashPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
//							ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.searchAndSelectValueInFilters(CashPaymentLocators.BankPartyDocNumberLovButton,
						VoucherNumber.get(i - 1));
				webDB.switchToFrame(CashPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(CashPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CashPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
							ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					String GetTextDocumentNumber = webDB.getTextFromElement(
							CashPaymentLocators.PendingPayablesDocumentNumberField, ElementType.Xpath);
					logs.logging("INFO", "Get Text Document Number Is " + GetTextDocumentNumber);
					if (GetTextDocumentNumber.equals(VoucherNumber.get(i - 1))) {
						verifyPendingPayablesPBDocumentThroughAddButtonMPMB(SheetName, VoucherNumber.get(i - 1), i);
						flag = webDB.waitForElement(
								CashPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i - 1)),
								ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(
									CashPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i - 1)),
									ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(3000);
							flag = webDB.waitForElement(CashPaymentLocators.BankPendingPartyDocumentScreenOkButton,
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
			flag = webDB.waitForElement(CashPaymentLocators.BankPendingPartyDocumentScreenOkButton, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(CashPaymentLocators.BankPendingPartyDocumentScreenOkButton, ElementType.Xpath);
				webDB.javaScriptClickAnElement(CashPaymentLocators.BankPendingPartyDocumentScreenOkButton,
						ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(CashPaymentLocators.BankHeaderCreateJournalButton, ElementType.Xpath);
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
	public void choosePartyNameAndClickSearchButtonInPendingPartyDocs(ArrayList<String> VoucherNumber,
			String SheetName) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.switchToFrame(CashPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
		System.out.println(VoucherNumber.size());
		if (flag) {
			for (int i = 1; i <= VoucherNumber.size(); i++) {
//				System.out.println(2);
//				commonFunc.searchAndSelectValueInFilters(
//						CashPaymentLocators.BankPaymentPendingPartyDocumentPartyLovButton, PartyName);
//				webDB.switchToFrame(CashPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
//				flag = webDB.waitForElement(CashPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
//						ElementType.Xpath);
//				if (flag) {
//					webDB.clickAnElement(CashPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
//							ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.searchAndSelectValueInFilters(CashPaymentLocators.BankPartyDocNumberLovButton,
						VoucherNumber.get(i - 1));
				webDB.switchToFrame(CashPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(CashPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CashPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
							ElementType.Xpath);
					commonFunc.hardWait(500);
					commonFunc.checkRefreshCellsDisappear();
					String GetTextDocumentNumber = webDB.getTextFromElement(
							CashPaymentLocators.PendingPayablesDocumentNumberField, ElementType.Xpath);
					logs.logging("INFO", "Get Text Document Number Is " + GetTextDocumentNumber);
					if (GetTextDocumentNumber.equals(VoucherNumber.get(i - 1))) {
						verifyPendingPayablesPBDocumentThroughGetDoc(SheetName, VoucherNumber.get(i - 1), i);
						flag = webDB.waitForElement(
								CashPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i - 1)),
								ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(
									CashPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i - 1)),
									ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(3000);
							flag = webDB.waitForElement(CashPaymentLocators.BankPendingPartyDocumentScreenOkButton,
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
			flag = webDB.waitForElement(CashPaymentLocators.BankPendingPartyDocumentScreenOkButton, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(CashPaymentLocators.BankPendingPartyDocumentScreenOkButton, ElementType.Xpath);
				webDB.javaScriptClickAnElement(CashPaymentLocators.BankPendingPartyDocumentScreenOkButton,
						ElementType.Xpath);
				commonFunc.hardWait(500);
				commonFunc.checkRefreshCellsDisappear();
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(CashPaymentLocators.BankHeaderCreateJournalButton, ElementType.Xpath);
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
	public void chooseDocumentNumberInPendingPayablesScreenMPMB(ArrayList<String> DocumentNumber, String Type,
			String PayMode, String BankName, String CreationType, String SheetName) {
		flag = webDB.waitForElement(CashPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(CashPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
			for (int i = 1; i <= DocumentNumber.size(); i++) {
				commonFunc.searchAndSelectValueInFilters(CashPaymentLocators.PendingPayablesDocNumberLovButton,
						DocumentNumber.get(i - 1));
				webDB.switchToFrame(CashPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(CashPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CashPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
							ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					String GetTextDocumentNumber = webDB.getTextFromElement(
							CashPaymentLocators.PendingPayablesDocumentNumberField, ElementType.Xpath);
					if (GetTextDocumentNumber.equals(DocumentNumber.get(i - 1))) {
						commonFunc.hardWait(1000);
						verifyPendingPayablesPBDocumentThroughAddButtonMPMB(SheetName, DocumentNumber.get(i - 1), i);
						webDB.clickAnElement(
								CashPaymentLocators.BPPendingPartyDocumentSelectCheckBox(DocumentNumber.get(i - 1)),
								ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(1500);
						flag = webDB.waitForElement(CashPaymentLocators.PendingPayablesCreateButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "This Purchase Bills Document " + DocumentNumber.get(i - 1)
									+ " Is Selected Successfully");
							if (DocumentNumber.size() > 1) {
								webDB.clickAnElement(CashPaymentLocators.BankPendingPartyDocumentClearButton,
										ElementType.Xpath);
								flag = webDB.waitForElement(
										CashPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(
											CashPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
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
			webDB.moveToElement(CashPaymentLocators.PendingPayablesCreateButton, ElementType.Xpath);
			webDB.javaScriptClickAnElement(CashPaymentLocators.PendingPayablesCreateButton, ElementType.Xpath);
			logs.logging("INFO", "Create Button Is Clicked Successfully");
			commonFunc.checkRefreshCellsDisappear();
			if (!CreationType.equals("Single")) {
				flag = webDB.waitForElement(CashPaymentLocators.PendingPayablesMergeLabelInPending, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CashPaymentLocators.PendingPayablesMergeLabelInPending, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
				}
			}
			flag = webDB.waitForElement(CashPaymentLocators.PendingPayablesTypeField, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownByText(CashPaymentLocators.PendingPayablesTypeDropDown, Type, ElementType.Xpath);
				logs.logging("INFO", "Type Successfully Choosen As " + Type);
				if (Type.equals("Bank")) {
					flag = webDB.waitForElement(CashPaymentLocators.PendingpayablesPaymodeField, ElementType.Xpath);
					if (flag) {
						webDB.selectDropDownByText(CashPaymentLocators.PendingPayablesPayModeDropDown, PayMode,
								ElementType.Xpath);
						logs.logging("INFO", "Pay Mode Successfully Choosen As " + PayMode);
					}
				}
				commonFunc.searchAndSelectValueInFilters(CashPaymentLocators.PendingPayablesBankNameLOVBUtton,
						BankName);
				webDB.switchToFrame(CashPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(CashPaymentLocators.PendingPayablesOkButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CashPaymentLocators.PendingPayablesOkButton, ElementType.Xpath);
					webDB.switchToDefaultContent();
					if (Type.equals("Bank")) {
						flag = webDB.waitForElement(CashPaymentLocators.BankPaymentScreenName, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"OK Button Is Clicked Successfully And Navigated To Bank Payment Screen");
						}
					} else {
						flag = webDB.waitForElement(CashPaymentLocators.CashPaymentScreenName, ElementType.Xpath);
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
	 * This Method Is Used To Click The Pending CR Docs Button In The Payment /
	 * Receipts Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickPendingCRDocsButton() {
		flag = webDB.waitForElement(CashPaymentLocators.PendingCRDocsButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashPaymentLocators.PendingCRDocsButton, ElementType.Xpath);
			flag = webDB.waitForElement(CashPaymentLocators.PendingPayablesButton, ElementType.Xpath);
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
		flag = webDB.waitForElement(CashPaymentLocators.PendingPayablesButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashPaymentLocators.PendingPayablesButton, ElementType.Xpath);
			flag = webDB.waitForElement(CashPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
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
		flag = webDB.waitForElement(CashPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(CashPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
			for (int i = 1; i <= DocumentNumber.size(); i++) {
				commonFunc.searchAndSelectValueInFilters(CashPaymentLocators.PendingPayablesDocNumberLovButton,
						DocumentNumber.get(i - 1));
				webDB.switchToFrame(CashPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(CashPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CashPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
							ElementType.Xpath);
					commonFunc.hardWait(500);
					commonFunc.checkRefreshCellsDisappear();
					String GetTextDocumentNumber = webDB.getTextFromElement(
							CashPaymentLocators.PendingPayablesDocumentNumberField, ElementType.Xpath);
					if (GetTextDocumentNumber.equals(DocumentNumber.get(i - 1))) {
						commonFunc.hardWait(1000);
						verifyPendingPayablesPBDocumentThroughGetDoc(SheetName, DocumentNumber.get(i - 1), i);
						webDB.clickAnElement(
								CashPaymentLocators.BPPendingPartyDocumentSelectCheckBox(DocumentNumber.get(i - 1)),
								ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(1500);
						flag = webDB.waitForElement(CashPaymentLocators.PendingPayablesCreateButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "This Purchase Bills Document " + DocumentNumber.get(i - 1)
									+ " Is Selected Successfully");
							if (DocumentNumber.size() > 1) {
								webDB.clickAnElement(CashPaymentLocators.BankPendingPartyDocumentClearButton,
										ElementType.Xpath);
								flag = webDB.waitForElement(
										CashPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(
											CashPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
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
			webDB.moveToElement(CashPaymentLocators.PendingPayablesCreateButton, ElementType.Xpath);
			webDB.javaScriptClickAnElement(CashPaymentLocators.PendingPayablesCreateButton, ElementType.Xpath);
			logs.logging("INFO", "Create Button Is Clicked Successfully");
			commonFunc.checkRefreshCellsDisappear();
			if (!CreationType.equals("Single")) {
				flag = webDB.waitForElement(CashPaymentLocators.PendingPayablesMergeLabelInPending, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CashPaymentLocators.PendingPayablesMergeLabelInPending, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
				}
			}
			flag = webDB.waitForElement(CashPaymentLocators.PendingPayablesTypeField, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownByText(CashPaymentLocators.PendingPayablesTypeDropDown, Type, ElementType.Xpath);
				logs.logging("INFO", "Type Successfully Choosen As " + Type);
				if (Type.equals("Bank")) {
					flag = webDB.waitForElement(CashPaymentLocators.PendingpayablesPaymodeField, ElementType.Xpath);
					if (flag) {
						webDB.selectDropDownByText(CashPaymentLocators.PendingPayablesPayModeDropDown, PayMode,
								ElementType.Xpath);
						logs.logging("INFO", "Pay Mode Successfully Choosen As " + PayMode);
					}
				}
				commonFunc.searchAndSelectValueInFilters(CashPaymentLocators.PendingPayablesBankNameLOVBUtton,
						BankName);
				webDB.switchToFrame(CashPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(CashPaymentLocators.PendingPayablesOkButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CashPaymentLocators.PendingPayablesOkButton, ElementType.Xpath);
					webDB.switchToDefaultContent();
					if (Type.equals("Bank")) {
						flag = webDB.waitForElement(CashPaymentLocators.BankPaymentScreenName, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"OK Button Is Clicked Successfully And Navigated To Bank Payment Screen");
						}
					} else {
						flag = webDB.waitForElement(CashPaymentLocators.CashPaymentScreenName, ElementType.Xpath);
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
	public void sendReferenceNumberInCP(String ReferenceNumber) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(CashPaymentLocators.BankPaymentReferenceInputField, ElementType.Xpath);
		if (flag) {
			webDB.clearTextField(CashPaymentLocators.BankPaymentReferenceInputField, ElementType.Xpath);
			webDB.sendTextToAnElement(CashPaymentLocators.BankPaymentReferenceInputField, ReferenceNumber,
					ElementType.Xpath);
			webDB.pressTabKey();
			flag = webDB.waitForElement(CashPaymentLocators.BankHeaderWiseSaveButton, ElementType.Xpath);
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
	public void clickCPHeaderWiseSaveButton() {
		flag = webDB.waitForElement(CashPaymentLocators.BankHeaderWiseSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashPaymentLocators.BankHeaderWiseSaveButton, ElementType.Xpath);
			flag = webDB.waitForElement(CashPaymentLocators.BankHeaderCreateJournalButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Bank Payment Header Wise Save Button Is Clicked Successfully");
				commonFunc.checkRefreshCellsDisappear();
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
	public void selectPartyDocumentBasedOnMultiPartySinglePB(String PartyName, String VoucherNumber, String SheetName,
			int i) {
		flag = webDB.switchToFrame(CashPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
		if (flag) {
			String GetTextPartyName = webDB.getDomPropertyFromElement(
					CashPaymentLocators.BankPendingPartyDocumentsPartyNameInputField, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			if (GetTextPartyName.equals(PartyName)) {
//				for (int i = 0; i < VoucherNumber.size(); i++) {
				commonFunc.searchAndSelectValueInFilters(CashPaymentLocators.BankPartyDocNumberLovButton,
						VoucherNumber);
				webDB.switchToFrame(CashPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(CashPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
						ElementType.Xpath);
				if (flag) {
					commonFunc.checkRefreshCellsDisappear();
					webDB.doubleClickAnElement(CashPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
							ElementType.Xpath);
					flag = webDB.waitForElement(CashPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber),
							ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						verifyPendingPayablesPBDocumentThroughGetDoc(SheetName, VoucherNumber, i); // TODO
						webDB.clickAnElement(CashPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber),
								ElementType.Xpath);
						commonFunc.hardWait(5000);
						flag = webDB.waitForElement(CashPaymentLocators.BankPendingPartyDocumentScreenOkButton,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "In Pending Party Documents This Voucher Number " + VoucherNumber
									+ " Is Selected Successfully");
						}
					}
				}
//				}
				webDB.moveToElement(CashPaymentLocators.BankPendingPartyDocumentScreenOkButton, ElementType.Xpath);
				webDB.javaScriptClickAnElement(CashPaymentLocators.BankPendingPartyDocumentScreenOkButton,
						ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(CashPaymentLocators.BankHeaderCreateJournalButton, ElementType.Xpath);
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
	public void verifyPendingPayablesPBDocumentThroughGetDocMPMB(String SheetName, String VoucherNumber, int i1) {
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
//		int a= i1*1;
//		for (int i = i1; i <= i1; i++) {
		int innerValue = (i1 % 2 == 1) ? 1 : 2;
		for (int i = innerValue; i <= innerValue; i++) {
//			for (int i = (i1 + 1) / 2; i <= (i1 + 1) / 2; i++){
//			for(int j = 1; j<BankPayments.length;j++) {
			System.out.println(i);
			String PartyID = CashPayments[i][2];
			String PartyName = CashPayments[i][3];
			String Entity = CashPayments[i][5];
			String CPCDesc = CashPayments[i][6];
			String Curcy = CashPayments[i][10];
			String EXRate = CashPayments[i][11];
			String Location = CashPayments[i][23];
			String Unit = CashPayments[i][24];
			String DrCr = CashPayments[i][25];
			String PayableAmount = CashPayments[i][26];
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
	 * This Method Is Used To Verify Pending Receivables Through Get Document With
	 * DB
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param VoucherNumber
	 * @param i1
	 */
	public void verifyPendingPayablesPBDocumentThroughAddButtonMPMB(String SheetName, String VoucherNumber, int i1) {
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
//		int a= i1*1;
		for (int i = i1; i <= i1; i++) {
//		int innerValue = (i1 % 2 == 1) ? 1 : 2;
//		for (int i = innerValue; i <= innerValue; i++) {
//			for (int i = (i1 + 1) / 2; i <= (i1 + 1) / 2; i++){
//			for(int j = 1; j<BankPayments.length;j++) {
			System.out.println(i);
			if (i <= 2) {
				String PartyID = CashPayments[1][2];
				String PartyName = CashPayments[1][3];
				String Entity = CashPayments[1][5];
				String CPCDesc = CashPayments[1][6];
				String Curcy = CashPayments[1][10];
				String EXRate = CashPayments[1][11];
				String Location = CashPayments[1][23];
				String Unit = CashPayments[1][24];
				String DrCr = CashPayments[1][25];
				String PayableAmount = CashPayments[1][26];
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

					salesOrdFunc
							.verifyAssertionBothValues(
									dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
											commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesExRate")),
									EXRate);
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

					salesOrdFunc.verifyAssertionBothValues(
							dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
									commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesDocNumber")),
							VoucherNumber);
					logs.logging("INFO", "In Pending Payables For This Document Number " + VoucherNumber
							+ " Source Document Is getting Matched With DB");
				}
			} else {
				String PartyID = CashPayments[2][2];
				String PartyName = CashPayments[2][3];
				String Entity = CashPayments[2][5];
				String CPCDesc = CashPayments[2][6];
				String Curcy = CashPayments[2][10];
				String EXRate = CashPayments[2][11];
				String Location = CashPayments[2][23];
				String Unit = CashPayments[2][24];
				String DrCr = CashPayments[2][25];
				String PayableAmount = CashPayments[2][26];
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

					salesOrdFunc
							.verifyAssertionBothValues(
									dbCommonFunctions.BankPaymentPendingPayablesDetails(VoucherNumber,
											commonFunc.getDataFromTestDataSheet("BRDBPendingReceivablesExRate")),
									EXRate);
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
	 * This Method Is Used to Select the Bank Payment pending Records In the Pending
	 * Party Documnets IN the bank Payment Screen
	 * 
	 * @author Harikrishna _ RMITS
	 * @param PartyName
	 * @param VoucherNumber.get(i)
	 */
	public void selectPartyDocumentBasedOnPBThroughAdd(String PartyName, ArrayList<String> VoucherNumber, int i1,
			String SheetName) {
		flag = webDB.switchToFrame(CashPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
		if (flag) {
			String GetTextPartyName = webDB.getDomPropertyFromElement(
					CashPaymentLocators.BankPendingPartyDocumentsPartyNameInputField, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			int a = i1 * 2;
			if (GetTextPartyName.equals(PartyName)) {
				for (int i = a - 1; i <= a; i++) {
					commonFunc.searchAndSelectValueInFilters(CashPaymentLocators.BankPartyDocNumberLovButton,
							VoucherNumber.get(i - 1));
					webDB.switchToFrame(CashPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
					flag = webDB.waitForElement(CashPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
							ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.doubleClickAnElement(CashPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
								ElementType.Xpath);
						flag = webDB.waitForElement(
								CashPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i - 1)),
								ElementType.Xpath);
						if (flag) {
							commonFunc.checkRefreshCellsDisappear();
							verifyPendingPayablesPBDocumentThroughAddButtonMPMB(SheetName, VoucherNumber.get(i - 1), i);
							webDB.clickAnElement(
									CashPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i - 1)),
									ElementType.Xpath);
							commonFunc.hardWait(6000);
							flag = webDB.waitForElement(CashPaymentLocators.BankPendingPartyDocumentScreenOkButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "In Pending Party Documents This Voucher Number "
										+ VoucherNumber.get(i - 1) + " Is Selected Successfully");
							}
						}
					}
				}
				webDB.moveToElement(CashPaymentLocators.BankPendingPartyDocumentScreenOkButton, ElementType.Xpath);
				webDB.javaScriptClickAnElement(CashPaymentLocators.BankPendingPartyDocumentScreenOkButton,
						ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(CashPaymentLocators.BankHeaderCreateJournalButton, ElementType.Xpath);
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
	public void CPThroughAddButtonWithNewRef(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createCashPaymentEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCP"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashPaymentDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"), Currency);
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String GLAccountDesc = CashPayments[i][1];
			String PartyName = CashPayments[i][3];
			String EntityName = CashPayments[i][5];
			String CPCCodeDesc = CashPayments[i][6];
			String BillNumber = CashPayments[i][7];
			String PayAmount = CashPayments[i][8];
			String DRCR = CashPayments[i][9];
			String CURCY = CashPayments[i][10];
			String EXRate = CashPayments[i][11];
			String TransCURCY = CashPayments[i][12];
			String BaseCURCY = CashPayments[i][13];
			String AssesableValue = CashPayments[i][16];
			if (!GLAccountDesc.equals("")) {
				clickCPLineAddButton();
				addCashPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
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
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ExportSalesEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, "1");
				}
				verifyCashPaymentsLineDetailsWithDB(SheetName, i);
			}
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalNR"), 1, 6,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalNR"), 1, 7,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalNR"), 2, 6,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalNR"), 2, 7,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		clickCreateJournalButton();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CPJournalNR"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
		excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalNR"), 1, 6, "0");
		excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalNR"), 1, 7, "0");
		excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalNR"), 2, 6, "0");
		excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalNR"), 2, 7, "0");
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCPVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The Bank Payment Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void CPThroughAddButtonMultiPartyMultiBills(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createCashPaymentEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCP"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashPaymentDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"), Currency);
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String GLAccountDesc = CashPayments[i][1];
			String PartyName = CashPayments[i][3];
			String EntityName = CashPayments[i][5];
			String CPCCodeDesc = CashPayments[i][6];
			String BillNumber = CashPayments[i][7];
			String PayAmount = CashPayments[i][8];
			String DRCR = CashPayments[i][9];
			String CURCY = CashPayments[i][10];
			String EXRate = CashPayments[i][11];
			String TransCURCY = CashPayments[i][12];
			String BaseCURCY = CashPayments[i][13];
			String AssesableValue = CashPayments[i][16];
			if (!GLAccountDesc.equals("")) {
				clickCPLineAddButton();
				addCashPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
				clickLineWiseSaveButton();
				clickLineWiseAddButton(i);
				clickPendingPartyDocumentsInBP();
				selectPartyDocumentBasedOnPBThroughAdd(PartyName, VariableHandler.getMultiplePBDocs(), i, SheetName);
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, "1");
				}
				verifyCashPaymentsLineDetailsWithDB(SheetName, i);
			}
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMPMI"), 1, 6,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMPMI"), 1, 7,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMPMI"), 2, 6,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMPMI"), 2, 7,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		clickCreateJournalButton();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CPJournalMPMI"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
		excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMPMI"), 1, 6, "0");
		excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMPMI"), 1, 7, "0");
		excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMPMI"), 2, 6, "0");
		excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMPMI"), 2, 7, "0");
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCPVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The Bank Payment Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void CPThroughAddButtonMultiPartySingleBills(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createCashPaymentEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCP"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashPaymentDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"), Currency);
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String GLAccountDesc = CashPayments[i][1];
			String PartyName = CashPayments[i][3];
			String EntityName = CashPayments[i][5];
			String CPCCodeDesc = CashPayments[i][6];
			String BillNumber = CashPayments[i][7];
			String PayAmount = CashPayments[i][8];
			String DRCR = CashPayments[i][9];
			String CURCY = CashPayments[i][10];
			String EXRate = CashPayments[i][11];
			String TransCURCY = CashPayments[i][12];
			String BaseCURCY = CashPayments[i][13];
			String AssesableValue = CashPayments[i][16];
			if (!GLAccountDesc.equals("")) {
				clickCPLineAddButton();
				addCashPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
				clickLineWiseSaveButton();
				clickLineWiseAddButton(i);
				clickPendingPartyDocumentsInBP();
				selectPartyDocumentBasedOnMultiPartySinglePB(PartyName, VariableHandler.getMultiplePBDocs().get(i - 1),
						SheetName, i);
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, "1");
				}
				verifyCashPaymentsLineDetailsWithDB(SheetName, i);
				excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMulti"), 3, 5 + i,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				for (int j = 0; j < VariableHandler.getMultiplePBDocs().size(); j++) {
					excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMulti"), j + 1, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				}
			}
		}
		clickCreateJournalButton();
		clearDataInExcel(SheetName);
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CPJournalMulti"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
		for (int j = 0; j < VariableHandler.getMultiplePBDocs().size(); j++) {
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMulti"), 3, 6 + j, "0");
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMulti"), j + 1, 6, "0");
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMulti"), j + 1, 7, "0");
		}
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCPVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The Bank Payment Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void CPThroughAddButton(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createCashPaymentEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCP"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashPaymentDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"), Currency);
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String GLAccountDesc = CashPayments[i][1];
			String PartyName = CashPayments[i][3];
			String EntityName = CashPayments[i][5];
			String CPCCodeDesc = CashPayments[i][6];
			String BillNumber = CashPayments[i][7];
			String PayAmount = CashPayments[i][8];
			String DRCR = CashPayments[i][9];
			String CURCY = CashPayments[i][10];
			String EXRate = CashPayments[i][11];
			String TransCURCY = CashPayments[i][12];
			String BaseCURCY = CashPayments[i][13];
			String AssesableValue = CashPayments[i][16];
			if (!GLAccountDesc.equals("")) {
				clickCPLineAddButton();
				addCashPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
				clickLineWiseSaveButton();
				clickLineWiseAddButton(i);
				clickPendingPartyDocumentsInBP();
				selectPartyDocumentBasedOnPB(PartyName, VariableHandler.getMultiplePBDocs(), SheetName);
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, "1");
				}
				verifyCashPaymentsLineDetailsWithDB(SheetName, i);
				for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
					excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournal"), 1, 5 + j,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournal"), 2, 5 + j,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				}
			}
		}
		commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.CFdescLOV, "OF");
		flag = webDB.waitForElement(CashPaymentLocators.CashHeaderWiseSaveButton1, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashPaymentLocators.CashHeaderWiseSaveButton1, ElementType.Xpath);
			logs.logging("INFO", "Cash Payment Header Wise Save Button Is Clicked Successfully");
		}
		clickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CPJournal"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
		for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournal"), 1, 5 + j, "0");
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournal"), 2, 5 + j, "0");
		}
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCPVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used to Create CP through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param Currency
	 * @param CashierName
	 */
	public void CPThroughGetDocumentWithNewReference(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createCashPaymentEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCP"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashPaymentDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"), Currency);
		clickGetDocumentInBPHeader();
		clickPendingPartyDocumentsInBP();
		choosePartyNameAndClickSearchButtonInPendingPartyDocsMPMB(VariableHandler.getMultiplePBDocs(), SheetName);
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, webDB.getDataFromProperties("BankPaymentINRMPMBNR"));
		for (int i = 1; i < CashPayments.length; i++) {
			String GLAccountDesc = CashPayments[i][1];
			String PartyName = CashPayments[i][3];
			String EntityName = CashPayments[i][5];
			String CPCCodeDesc = CashPayments[i][6];
			String BillNumber = CashPayments[i][7];
			String PayAmount = CashPayments[i][8];
			String DRCR = CashPayments[i][9];
			String CURCY = CashPayments[i][10];
			String EXRate = CashPayments[i][11];
			String TransCURCY = CashPayments[i][12];
			String BaseCURCY = CashPayments[i][13];
			String AssesableValue = CashPayments[i][16];
			if (!GLAccountDesc.equals("")) {
				clickLineWiseViewButton(i);
				clickAddButtonInViewDetails();
				addBillNumberAndPayAmount(BillNumber, PayAmount);
				clickViewDetailsSaveButton();
				clickBankViewDetailsCloseButton();
				for (int j = 1; j < CashPayments.length; j++) {
					String GLAccountDesc1 = CashPayments[j][1];
					if (!GLAccountDesc1.equals("")) {
						if (!Currency.equals("INR")) {
							excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11,
									dbCommonFunctions.ExchangeRateDetails(Currency,
											commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
						} else {
							excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, "1");
						}
						verifyCashPaymentsLineDetailsWithDB(SheetName, i);
					}
				}
			}
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalNR"), 1, 6,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalNR"), 1, 7,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalNR"), 2, 6,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalNR"), 2, 7,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		clickCreateJournalButton();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CPJournalNR"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
		excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalNR"), 1, 6, "0");
		excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalNR"), 1, 7, "0");
		excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalNR"), 2, 6, "0");
		excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalNR"), 2, 7, "0");
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCPVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Create get Document MPMB
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param Currency
	 * @param CashierName
	 */
	public void CPThroughGetDocumentMPMB(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createCashPaymentEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCP"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashPaymentDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"), Currency);
		clickGetDocumentInBPHeader();
		clickPendingPartyDocumentsInBP();
		choosePartyNameAndClickSearchButtonInPendingPartyDocsMPMB(VariableHandler.getMultiplePBDocs(), SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String GLAccountDesc = CashPayments[i][1];
			String BaseCURCY = CashPayments[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, "1");
				}
				verifyCashPaymentsLineDetailsWithDB(SheetName, i);
			}
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMPMI"), 1, 6,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMPMI"), 1, 7,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMPMI"), 2, 6,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMPMI"), 2, 7,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		clickCreateJournalButton();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CPJournalMPMI"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
		excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMPMI"), 1, 6, "0");
		excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMPMI"), 1, 7, "0");
		excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMPMI"), 2, 6, "0");
		excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMPMI"), 2, 7, "0");
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCPVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To create CP Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param Currency
	 * @param CashierName
	 */
	public void CPThroughGetDocument(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createCashPaymentEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCP"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashPaymentDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"), Currency);
		clickGetDocumentInBPHeader();
		clickPendingPartyDocumentsInBP();
		choosePartyNameAndClickSearchButtonInPendingPartyDocs(VariableHandler.getMultiplePBDocs(), SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String GLAccountDesc = CashPayments[i][1];
			String BaseCURCY = CashPayments[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, "1");
				}
				verifyCashPaymentsLineDetailsWithDB(SheetName, i);
				for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
					excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournal"), 1, 5 + j,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournal"), 2, 5 + j,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				}
			}
		}
		commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.CFdescLOV, "OF");
		flag = webDB.waitForElement(CashPaymentLocators.CashHeaderWiseSaveButton1, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashPaymentLocators.CashHeaderWiseSaveButton1, ElementType.Xpath);
			logs.logging("INFO", "Cash Payment Header Wise Save Button Is Clicked Successfully");
		}
		clickCreateJournalButton();
		clearDataInExcel(SheetName);
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CPJournal"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
		for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournal"), 1, 5 + j, "0");
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournal"), 2, 5 + j, "0");
		}
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCPVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To create CP Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param Currency
	 * @param CashierName
	 */
	public void CPThroughGetDocumentMPSB(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createCashPaymentEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCP"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashPaymentDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"), Currency);
		clickGetDocumentInBPHeader();
		clickPendingPartyDocumentsInBP();
		choosePartyNameAndClickSearchButtonInPendingPartyDocs(VariableHandler.getMultiplePBDocs(), SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String GLAccountDesc = CashPayments[i][1];
			String BaseCURCY = CashPayments[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, "1");
				}
				verifyCashPaymentsLineDetailsWithDB(SheetName, i);
				excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMulti"), 3, 5 + i,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				for (int j = 0; j < VariableHandler.getMultiplePBDocs().size(); j++) {
					excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMulti"), j + 1, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				}
			}
		}
		clickCreateJournalButton();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CPJournalMulti"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
		for (int j = 0; j < VariableHandler.getMultiplePBDocs().size(); j++) {
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMulti"), 3, 6 + j, "0");
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMulti"), j + 1, 6, "0");
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMulti"), j + 1, 7, "0");
		}
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCPVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Create CP Through Pending Wioth New ref
	 * 
	 * @author Harikrishna - RMITS
	 * @param CreationType
	 * @param SheetName
	 * @param Currency
	 * @param CashierName
	 */
	public void CPThroughPendingDocsWithNewReference(String CreationType, String SheetName, String Currency,
			String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingCRDocsButton();
		clickPendingPayablesButtonInPendCRDocs();
		chooseDocumentNumberInPendingPayablesScreenMPMB(VariableHandler.getMultiplePBDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentTypeCash"),
				commonFunc.getDataFromTestDataSheet("BankPayMode"), CashierName, CreationType, SheetName);
		getCashPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, webDB.getDataFromProperties("BankPaymentMultiNewRef"));
		for (int i = 1; i < CashPayments.length; i++) {
			String GLAccountDesc = CashPayments[i][1];
			String PartyName = CashPayments[i][2];
			String EntityName = CashPayments[i][3];
			String CPCCodeDesc = CashPayments[i][4];
			String BillNumber = CashPayments[i][5];
			String PayAmount = CashPayments[i][6];
			String DRCR = CashPayments[i][7];
			String CURCY = CashPayments[i][8];
			String EXRate = CashPayments[i][9];
			String TransCURCY = CashPayments[i][10];
			String BaseCURCY = CashPayments[i][11];
			String AssesableValue = CashPayments[i][14];
			if (!GLAccountDesc.equals("")) {
				clickLineWiseViewButton(i);
				clickAddButtonInViewDetails();
				addBillNumberAndPayAmount(BillNumber, PayAmount);
				clickViewDetailsSaveButton();
				clickBankViewDetailsCloseButton();
				for (int j = 1; j < CashPayments.length; j++) {
					String GLAccountDesc1 = CashPayments[j][1];
					if (!GLAccountDesc1.equals("")) {
						if (!Currency.equals("INR")) {
							excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11,
									dbCommonFunctions.ExchangeRateDetails(Currency,
											commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
						} else {
							excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, "1");
						}
						verifyCashPaymentsLineDetailsWithDB(SheetName, i);
					}
				}
			}
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalNR"), 1, 6,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalNR"), 1, 7,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalNR"), 2, 6,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalNR"), 2, 7,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		clickCreateJournalButton();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CPJournalNR"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
		excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalNR"), 1, 6, "0");
		excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalNR"), 1, 7, "0");
		excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalNR"), 2, 6, "0");
		excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalNR"), 2, 7, "0");
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCPVDocumentStatus();
		}
	}

	/**
	 * This Method Is Used To Create CP Thriugh Pending Docs
	 * 
	 * @author Harikrishna - RMITS
	 * @param CreationType
	 * @param SheetName
	 * @param Currency
	 * @param CashierName
	 */
	public void CPThroughPendingDocsMPMB(String CreationType, String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingCRDocsButton();
		clickPendingPayablesButtonInPendCRDocs();
		chooseDocumentNumberInPendingPayablesScreen(VariableHandler.getMultiplePBDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentTypeCash"),
				commonFunc.getDataFromTestDataSheet("BankPayMode"), CashierName, CreationType, SheetName);
		getCashPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		for (int i = 1; i < CashPayments.length; i++) {
			String GLAccountDesc = CashPayments[i][1];
			String BaseCURCY = CashPayments[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, "1");
				}
				verifyCashPaymentsLineDetailsWithDB(SheetName, i);
			}
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMPMI"), 1, 6,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(0),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMPMI"), 1, 7,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(1),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMPMI"), 2, 6,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(2),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMPMI"), 2, 7,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(3),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		clickCreateJournalButton();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CPJournalMPMI"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
		excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMPMI"), 1, 6, "0");
		excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMPMI"), 1, 7, "0");
		excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMPMI"), 2, 6, "0");
		excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMPMI"), 2, 7, "0");
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getBankPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCPVDocumentStatus();
		}
	}

	/**
	 * This Method Is Used to Create CP Through Pending docs
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void CPThroughPendingDocs(String CreationType, String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingCRDocsButton();
		clickPendingPayablesButtonInPendCRDocs();
		chooseDocumentNumberInPendingPayablesScreen(VariableHandler.getMultiplePBDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentTypeCash"),
				commonFunc.getDataFromTestDataSheet("BankPayMode"), CashierName, CreationType, SheetName);
		getCashPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		for (int i = 1; i < CashPayments.length; i++) {
			String GLAccountDesc = CashPayments[i][1];
			String BaseCURCY = CashPayments[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, "1");
				}
				verifyCashPaymentsLineDetailsWithDB(SheetName, i);
				for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
					excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournal"), 1, 5 + j,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
					excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournal"), 2, 5 + j,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				}
			}
		}
		clickCreateJournalButton();
		clearDataInExcel(SheetName);
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CPJournal"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
		for (int j = 1; j <= VariableHandler.getMultiplePBDocs().size(); j++) {
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournal"), 1, 5 + j, "0");
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournal"), 2, 5 + j, "0");
		}
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCPVDocumentStatus();
		}
	}

	/**
	 * This Method Is Used to Create CP Through Pending docs
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void CPThroughPendingDocsMPSB(String CreationType, String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingCRDocsButton();
		clickPendingPayablesButtonInPendCRDocs();
		chooseDocumentNumberInPendingPayablesScreen(VariableHandler.getMultiplePBDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentTypeCash"),
				commonFunc.getDataFromTestDataSheet("BankPayMode"), CashierName, CreationType, SheetName);
		getCashPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		for (int i = 1; i < CashPayments.length; i++) {
			String GLAccountDesc = CashPayments[i][1];
			String BaseCURCY = CashPayments[i][13];
			if (!GLAccountDesc.equals("")) {
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, "1");
				}
				verifyCashPaymentsLineDetailsWithDB(SheetName, i);
				excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMulti"), 3, 5 + i,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				for (int j = 0; j < VariableHandler.getMultiplePBDocs().size(); j++) {
					excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMulti"), j + 1, 6,
							dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j),
									commonFunc.getDataFromTestDataSheet("PBNetAmount")));
				}
			}
		}
		clickCreateJournalButton();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CPJournalMulti"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
		for (int j = 0; j < VariableHandler.getMultiplePBDocs().size(); j++) {
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMulti"), 3, 6 + j, "0");
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMulti"), j + 1, 6, "0");
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalMulti"), j + 1, 7, "0");
		}
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCPVDocumentStatus();
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
	public boolean CreateBankPaymentThroughLineAddButtonSingleBills(String SheetName) throws IOException {
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String SupplierID = CashPayments[i][2];
			String GRNSheetName = CashPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
						CPGrnExcelFileName);
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
			excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 25 + i,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i - 1),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		CPThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
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
	public boolean CreateCashPaymentThroughGetDocumentSingleBills(String SheetName) throws IOException {
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String SupplierID = CashPayments[i][2];
			String GRNSheetName = CashPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
						CPGrnExcelFileName);
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
			excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 25 + i,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i - 1),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		CPThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
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
	public boolean CreateCashPaymentThroughPendingDocsSingleBills(String SheetName) throws IOException {
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String SupplierID = CashPayments[i][2];
			String GRNSheetName = CashPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
						CPGrnExcelFileName);
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
			excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 25 + i,
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i - 1),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		CPThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
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
	public boolean CreateCashPaymentThroughLineAddButtonMultiBills(String SheetName) throws IOException {
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i <= 2; i++) {
			String SupplierID = CashPayments[1][2];
			String GRNSheetName = CashPayments[1][4];
			Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
					CPGrnExcelFileName);
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(FilepathCP, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		CPThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
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
	public boolean CreateCashPaymentThroughGetDocumentMultiBills(String SheetName) throws IOException {
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i <= 2; i++) {
			String SupplierID = CashPayments[1][2];
			String GRNSheetName = CashPayments[1][4];
			Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
					CPGrnExcelFileName);
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(FilepathCP, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		CPThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
		return flag;
	}

	/**
	 * This Method Is Used To Create Cash Payment Through pending Docs
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return
	 * @throws IOException
	 */
	public boolean CreateCashPaymentThroughPendingDocsMultiBills(String SheetName) throws IOException {
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i <= 2; i++) {
			String SupplierID = CashPayments[1][2];
			String GRNSheetName = CashPayments[1][4];
			Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
					CPGrnExcelFileName);
		}
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(FilepathCP, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		CPThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
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
	public boolean CreateCashPaymentThroughLineAddButtonMultiPartySingleBill(String SheetName) throws IOException {
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String SupplierID = CashPayments[i][2];
			String GRNSheetName = CashPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
						CPGrnExcelFileName);
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
			excelUtils.writeDataInExcel(FilepathCP, SheetName, i + 1, 25 + (i + 1),
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		CPThroughAddButtonMultiPartySingleBills(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
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
	public boolean CreateCashPaymentThroughGetDocumentMultiPartySingleBill(String SheetName) throws IOException {
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String SupplierID = CashPayments[i][2];
			String GRNSheetName = CashPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
						CPGrnExcelFileName);
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
			excelUtils.writeDataInExcel(FilepathCP, SheetName, i + 1, 25 + (i + 1),
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		CPThroughGetDocumentMPSB(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
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
	public boolean CreateCashPaymentThroughPendingDocumentMultiPartySingleBill(String SheetName) throws IOException {
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String SupplierID = CashPayments[i][2];
			String GRNSheetName = CashPayments[i][4];
			if (!SupplierID.equals("")) {
				Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
						CPGrnExcelFileName);
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
			excelUtils.writeDataInExcel(FilepathCP, SheetName, i + 1, 25 + (i + 1),
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		CPThroughPendingDocsMPSB(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
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
	public boolean CreateCashPaymentThroughLineAddButtonMultiPartyMultiBill(String SheetName) throws IOException {
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String SupplierID = CashPayments[i][2];
			String GRNSheetName = CashPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
							CPGrnExcelFileName);
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
				excelUtils.writeDataInExcel(FilepathCP, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(FilepathCP, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		CPThroughAddButtonMultiPartyMultiBills(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
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
	public boolean CreateCashPaymentThroughGetDocumentMultiPartyMultiBill(String SheetName) throws IOException {
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String SupplierID = CashPayments[i][2];
			String GRNSheetName = CashPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
							CPGrnExcelFileName);
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
				excelUtils.writeDataInExcel(FilepathCP, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(FilepathCP, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		CPThroughGetDocumentMPMB(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
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
	public boolean CreateCashPaymentThroughPendingMultiPartyMultiBill(String SheetName) throws IOException {
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String SupplierID = CashPayments[i][2];
			String GRNSheetName = CashPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
							CPGrnExcelFileName);
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
				excelUtils.writeDataInExcel(FilepathCP, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(FilepathCP, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		CPThroughPendingDocsMPMB(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
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
	public boolean CreateCashPaymentThroughGetDocumentMultiPartyMultiBillWithNewRef(String SheetName)
			throws IOException {
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String SupplierID = CashPayments[i][2];
			String GRNSheetName = CashPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
							CPGrnExcelFileName);
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
				excelUtils.writeDataInExcel(FilepathCP, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(FilepathCP, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		CPThroughGetDocumentWithNewReference(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
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
	public boolean CreateCashPaymentThroughPendingDocumentMultiPartyMultiBill(String SheetName) throws IOException {
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String SupplierID = CashPayments[i][2];
			String GRNSheetName = CashPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
							CPGrnExcelFileName);
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
				excelUtils.writeDataInExcel(FilepathCP, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(FilepathCP, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		CPThroughPendingDocsWithNewReference(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
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
	public boolean CreateCashPaymentThroughAddButtonMultiPartyMultiBillWithNewRef(String SheetName) throws IOException {
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String SupplierID = CashPayments[i][2];
			String GRNSheetName = CashPayments[i][4];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHK(SupplierID, GRNSheetName,
							CPGrnExcelFileName);
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
				excelUtils.writeDataInExcel(FilepathCP, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(FilepathCP, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		CPThroughAddButtonWithNewRef(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
		return flag;
	}

	/**
	 * This Method Is used To Add The Bank Receipt Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return
	 */
	public void CashPaymentWithExpAndRevenueWithoutGST(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createCashPaymentEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCP"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashPaymentDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"), Currency);
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String GLAccountDesc = CashPayments[i][1];
			String PartyName = CashPayments[i][3];
			String EntityName = CashPayments[i][5];
			String CPCCodeDesc = CashPayments[i][6];
			String BillNumber = CashPayments[i][7];
			String PayAmount = CashPayments[i][8];
			String DRCR = CashPayments[i][9];
			String CURCY = CashPayments[i][10];
			String EXRate = CashPayments[i][11];
			String TransCURCY = CashPayments[i][12];
			String BaseCURCY = CashPayments[i][13];
			String HSNCode = CashPayments[i][14];
			String AssesableValue = CashPayments[i][16];
			String LineWiseDRCR = CashPayments[i][25];
			String ChargesFlag = CashPayments[i][29];
			String StateCode = CashPayments[i][30];
			String GSTSupplierName = CashPayments[i][31];
			String GSTIN = CashPayments[i][32];
			if (!GLAccountDesc.equals("")) {
				clickCPLineAddButton();
				addCashPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
				enterValueInAmtInTCField(PayAmount);
				chooseLineWiseDRCRInCP(LineWiseDRCR);
				clickLineWiseSaveButton();
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, "1");
				}
				verifyCashPaymentsLineDetailsWithDB(SheetName, i);
			}
		}
//		flag = webDB.waitForElement(CashPaymentLocators.BankPaymentFavorOfInputField, ElementType.Xpath);
//		if (flag) {
//			webDB.sendTextToAnElement(CashPaymentLocators.BankPaymentFavorOfInputField, "HARI TRADERS",
//					ElementType.Xpath);
//		}
		clickCreateJournalButton();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CPJournalREV"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCPVDocumentStatus();
//			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
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
	public void CashPaymentWithExpAndRevenueWithGST(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createCashPaymentEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCP"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashPaymentDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"), Currency);
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String GLAccountDesc = CashPayments[i][1];
			String PartyName = CashPayments[i][3];
			String EntityName = CashPayments[i][5];
			String CPCCodeDesc = CashPayments[i][6];
			String BillNumber = CashPayments[i][7];
			String PayAmount = CashPayments[i][8];
			String DRCR = CashPayments[i][9];
			String CURCY = CashPayments[i][10];
			String EXRate = CashPayments[i][11];
			String TransCURCY = CashPayments[i][12];
			String BaseCURCY = CashPayments[i][13];
			String HSNCode = CashPayments[i][14];
			String AssesableValue = CashPayments[i][16];
			String LineWiseDRCR = CashPayments[i][25];
			String ChargesFlag = CashPayments[i][29];
			String StateCode = CashPayments[i][30];
			String GSTSupplierName = CashPayments[i][31];
			String GSTIN = CashPayments[i][32];
			if (!GLAccountDesc.equals("")) {
				clickCPLineAddButton();
				addCashPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
				enterValueInAmtInTCField(PayAmount);
				chooseLineWiseDRCRInCP(LineWiseDRCR);
				chooseHSNCodeInCP(HSNCode);
				clickLineWiseSaveButton();
				clickLineWiseGSTIconInCP(i);
				enterValuesInGSTScreen(StateCode, GSTSupplierName, GSTIN, Today, BillNumber);
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, "1");
				}
				verifyCashPaymentsLineDetailsWithDB(SheetName, i);
			}
		}
		clickCreateJournalButton();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CPJournalREVGST"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCPVDocumentStatus();
//			checkGLMControlBRVAutoClearAndVerifyBRVDocumentStatus(Currency);
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
	public void CashPaymentWithExpAndRevenueWithGSTAndTDS(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createCashPaymentEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCP"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashPaymentDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"), Currency);
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String GLAccountDesc = CashPayments[i][1];
			String PartyName = CashPayments[i][3];
			String EntityName = CashPayments[i][5];
			String CPCCodeDesc = CashPayments[i][6];
			String BillNumber = CashPayments[i][7];
			String PayAmount = CashPayments[i][8];
			String DRCR = CashPayments[i][9];
			String CURCY = CashPayments[i][10];
			String EXRate = CashPayments[i][11];
			String TransCURCY = CashPayments[i][12];
			String BaseCURCY = CashPayments[i][13];
			String HSNCode = CashPayments[i][14];
			String AssesableValue = CashPayments[i][16];
			String LineWiseDRCR = CashPayments[i][25];
			String ChargesFlag = CashPayments[i][29];
			String StateCode = CashPayments[i][30];
			String GSTSupplierName = CashPayments[i][31];
			String GSTIN = CashPayments[i][32];
			if (!GLAccountDesc.equals("")) {
				if (i <= 1) {
					clickCPLineAddButton();
					addCashPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
					enterValueInAmtInTCField(PayAmount);
					chooseLineWiseDRCRInCP(LineWiseDRCR);
					chooseHSNCodeInCP(HSNCode);
					chooseTDSInCP(commonFunc.getDataFromTestDataSheet("TDSSection"));
					clickLineWiseSaveButton();
					clickLineWiseGSTIconInCP(i);
					enterValuesInGSTScreen(StateCode, GSTSupplierName, GSTIN, Today, BillNumber);
//					flag = webDB.waitForElementUntilGivenTime(BankPaymentLocators.BankPaymentFavorOfInputField,
//							ElementType.Xpath, 10);
//					if (flag) {
//						webDB.sendTextToAnElement(BankPaymentLocators.BankPaymentFavorOfInputField, "HARI TRADERS",
//								ElementType.Xpath);
//					}
					clickApplyDeductionButtonInHeader();
					if (!Currency.equals("INR")) {
						excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
								Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
					} else {
						excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, "1");
					}
				}
				verifyCashPaymentsLineDetailsWithDB(SheetName, i);
			}
		}
		clickCreateJournalButton();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CPJournalREVGSTTDS"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCPVDocumentStatus();
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
	public boolean CreateCashPaymentThroughLineAddButtonMultiBillsMultiUnits(String SheetName) throws IOException {

		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String SupplierID = CashPayments[i][2];
			String GRNSheetName = CashPayments[i][4];
			String Location = CashPayments[i][23];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHKMultiUnits(SupplierID,
							GRNSheetName, Location, CPGrnExcelFileName);
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
				excelUtils.writeDataInExcel(FilepathCP, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(FilepathCP, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}

//		Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHKMultiUnits(
//				commonFunc.getDataFromTestDataSheet("POSupplier"), webDB.getDataFromProperties("DirectGrnGenUD"),
//				commonFunc.getDataFromTestDataSheet("Loc_100"));
//
//		Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHKMultiUnits(
//				commonFunc.getDataFromTestDataSheet("POSupplier"), webDB.getDataFromProperties("DirectGrnGenUD50"),
//				commonFunc.getDataFromTestDataSheet("Location50"));
//
//		for (int i = 1; i <= VariableHandler.getMultipleGRNDocs().size(); i++) {
//			createPurchaseBillsFromPendingGRNMultiUnits(VariableHandler.getMultipleGRNDocs().get(i - 1),
//					dbCommonFunctions.GRNPBHeaderQuery(VariableHandler.getMultipleGRNDocs().get(i - 1),
//							commonFunc.getDataFromTestDataSheet("GRNHeaderLocationColumn")));
//			excelUtils.writeDataInExcel(Filepath, SheetName, 1, 25 + i,
//					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i - 1),
//							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
//		}
		CPThroughAddButtonMultiPartyMultiBills(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
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
	public boolean CreateCashPaymentThroughGetDocumentMultiBillsMultiUnits(String SheetName) throws IOException {
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String SupplierID = CashPayments[i][2];
			String GRNSheetName = CashPayments[i][4];
			String Location = CashPayments[i][23];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHKMultiUnits(SupplierID,
							GRNSheetName, Location, CPGrnExcelFileName);
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
				excelUtils.writeDataInExcel(FilepathCP, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(FilepathCP, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		CPThroughGetDocumentMPMB(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
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
	public boolean CreateCashPaymentThroughPendingDocsMultiBillsMultiUnits(String SheetName) throws IOException {
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String SupplierID = CashPayments[i][2];
			String GRNSheetName = CashPayments[i][4];
			String Location = CashPayments[i][23];
			if (!SupplierID.equals("")) {
				for (int i1 = 1; i1 <= 2; i1++) {
					Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHKMultiUnits(SupplierID,
							GRNSheetName, Location, CPGrnExcelFileName);
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
				excelUtils.writeDataInExcel(FilepathCP, SheetName, 1, 25 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			} else {
				excelUtils.writeDataInExcel(FilepathCP, SheetName, 2, 23 + j,
						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(j - 1),
								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
			}
		}
		CPThroughPendingDocsMPMB(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
		return flag;
	}

//	/**
//	 * This Method Is Used To Create a Bank Payment Through Add Button Multi Party
//	 * Single Bill
//	 * 
//	 * @author Harikrishna - RMITS
//	 * @param SheetName
//	 * @return boolean flag
//	 * @throws IOException
//	 */
//	public boolean CreateCashPaymentThroughLineAddButtonMultiPartyMultiBillWithMultiUnits(String SheetName)
//			throws IOException {
//		CashPayments = excelUtils.readAllDataInExcel(Filepath, SheetName);
//		for (int i = 1; i < CashPayments.length; i++) {
//			String SupplierID = CashPayments[i][2];
//			Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHKMultiUnits(SupplierID,
//					webDB.getDataFromProperties("DirectGrnGenUDMulti"), commonFunc.getDataFromTestDataSheet("Loc_100"));
//
//			Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHKMultiUnits(SupplierID,
//					webDB.getDataFromProperties("DirectGrnGenUDMulti50"),
//					commonFunc.getDataFromTestDataSheet("Location50"));
//		}
//
//		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("HKFinUsername"));
//		commonFunc.navigateToFinancialPurchaseBill();
//		for (int i1 = 1; i1 <= 2; i1++) {
//			for (int i = 1; i <= VariableHandler.getMultipleGRNDocs().size(); i++) {
//				createPurchaseBillsFromPendingGRNMultiUnits(VariableHandler.getMultipleGRNDocs().get(i - 1),
//						dbCommonFunctions.GRNPBHeaderQuery(VariableHandler.getMultipleGRNDocs().get(i - 1),
//								commonFunc.getDataFromTestDataSheet("GRNHeaderLocationColumn")));
//				excelUtils.writeDataInExcel(Filepath, SheetName, i1, 25 + i,
//						dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i - 1),
//								commonFunc.getDataFromTestDataSheet("PBNetAmount")));
//			}
//		}
//		CPThroughAddButtonMultiPartyMultiBills(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
//				commonFunc.getDataFromTestDataSheet("CashierName"));
//		return flag;
//	}

	/**
	 * This Method Is Used To Create a Bank Payment Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean CreateCashPaymentThroughLineAddButtonSPMBMultiUnits(String SheetName) throws IOException {

		Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHKMultiUnits(
				commonFunc.getDataFromTestDataSheet("GRNSupplier"), webDB.getDataFromProperties("DirectGrnGenUD"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), CPGrnExcelFileName);

		Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHKMultiUnits(
				commonFunc.getDataFromTestDataSheet("GRNSupplier"), webDB.getDataFromProperties("DirectGrnGenUD50"),
				commonFunc.getDataFromTestDataSheet("Location50"), CPGrnExcelFileName);

		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(FilepathCP, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		CPThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
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
	public boolean CreateCashPaymentThroughGetDocumentSPMBMultiUnits(String SheetName) throws IOException {

		Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHKMultiUnits(
				commonFunc.getDataFromTestDataSheet("GRNSupplier"), webDB.getDataFromProperties("DirectGrnGenUD"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), CPGrnExcelFileName);

		Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHKMultiUnits(
				commonFunc.getDataFromTestDataSheet("GRNSupplier"), webDB.getDataFromProperties("DirectGrnGenUD50"),
				commonFunc.getDataFromTestDataSheet("Location50"), CPGrnExcelFileName);

		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(FilepathCP, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		CPThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
		return flag;
	}

	/**
	 * This Method Is Used To Create Cash Payment Through pending Docs
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @return
	 * @throws IOException
	 */
	public boolean CreateCashPaymentThroughPendingDocsSPMBMultiUnits(String SheetName) throws IOException {

		Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHKMultiUnits(
				commonFunc.getDataFromTestDataSheet("GRNSupplier"), webDB.getDataFromProperties("DirectGrnGenUD"),
				commonFunc.getDataFromTestDataSheet("Loc_100"), CPGrnExcelFileName);

		Grnfunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionHKMultiUnits(
				commonFunc.getDataFromTestDataSheet("GRNSupplier"), webDB.getDataFromProperties("DirectGrnGenUD50"),
				commonFunc.getDataFromTestDataSheet("Location50"), CPGrnExcelFileName);
		for (int i = 0; i < VariableHandler.getMultipleGRNDocs().size(); i++) {
			String PbDocsNum = dbCommonFunctions.PBDocQuery(VariableHandler.getMultipleGRNDocs().get(i),
					commonFunc.getDataFromTestDataSheet("PBDocumentNoColumnName"));
			VariableHandler.setPbNumber(PbDocsNum);
			PBDocumentNumber.add(VariableHandler.getPbNumber());
			logs.logging("INFO", "Purchase Bill Document Number : " + VariableHandler.getPbNumber()
					+ " Against This GRN Document Is : " + VariableHandler.getMultipleGRNDocs().get(i));
			VariableHandler.setMultiplePBDocs(PBDocumentNumber);
			excelUtils.writeDataInExcel(FilepathCP, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.BankPaymentPBHeaderDetails(VariableHandler.getMultiplePBDocs().get(i),
							commonFunc.getDataFromTestDataSheet("PBNetAmount")));
		}
		CPThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
		return flag;
	}

	/**
	 * This Method Is Used To Click The Pending Party Documents In Bank Payment
	 * Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickPendingAdvancePartyDocumentsInBP() {
		flag = webDB.waitForElement(CashPaymentLocators.BankPendingAdvPartyDocumentsButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashPaymentLocators.BankPendingAdvPartyDocumentsButton, ElementType.Xpath);
			flag = webDB.waitForElement(CashPaymentLocators.BankPendingAdvPartyDocumentsFrameName, ElementType.Xpath);
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
	public void clickPendingAdvancePartyDocumentsInPendingCrDocsBP() {
		flag = webDB.waitForElement(CashPaymentLocators.PendingAdvancePayablesInPend, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashPaymentLocators.PendingAdvancePayablesInPend, ElementType.Xpath);
			flag = webDB.waitForElement(CashPaymentLocators.BankPendingAdvPartyDocumentsFrameName, ElementType.Xpath);
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
		flag = webDB.switchToFrame(CashPaymentLocators.BankPendingAdvPartyDocumentsFrameName, ElementType.Xpath);
		if (flag) {
			String GetTextPartyName = webDB.getDomPropertyFromElement(
					CashPaymentLocators.BankPendingPartyDocumentsPartyNameInputField, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			if (GetTextPartyName.equals(PartyName)) {
				for (int i = 1; i <= VoucherNumber.size(); i++) {
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
							verifyPendingAdvancePayablesThroughGetDoc(SheetName, VoucherNumber.get(i - 1), i); // TODO
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
				flag = webDB.waitForElement(CashPaymentLocators.BankHeaderCreateJournalButton, ElementType.Xpath);
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
	 * This Method Is used To get Advance Receipt Document Number From DB Values
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void getAdvPaymentDocumentFromDataBase() {
		String AdvPaymentDocumentNumber = dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("AdvancePODocumentNoColumnName"));
		VariableHandler.setAdvPaymentDocNumber(AdvPaymentDocumentNumber);
		logs.logging("INFO",
				"Advance Payment Document number In Purchase Order Is:" + VariableHandler.getAdvPaymentDocNumber());
		MultiAdvPaymentDocumentNumber.add(VariableHandler.getAdvPaymentDocNumber());
	}

	/**
	 * This Methos Is Used To Choose Party Name And Select The Purchase Bills In The
	 * Pending Party Documents
	 * 
	 * @author Harikrishna - RMITS
	 * @param PartyName
	 * @param VoucherNumber
	 */
	public void choosePartyNameAndClickSearchButtonInPendingAdvPartyDocs(ArrayList<String> VoucherNumber,
			String SheetName) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.switchToFrame(CashPaymentLocators.BankPendingAdvPartyDocumentsFrameName, ElementType.Xpath);
		System.out.println(VoucherNumber.size());
		if (flag) {
			for (int i = 1; i <= VoucherNumber.size(); i++) {
//				System.out.println(2);
//				commonFunc.searchAndSelectValueInFilters(
//						CashPaymentLocators.BankPaymentPendingPartyDocumentPartyLovButton, PartyName);
//				webDB.switchToFrame(CashPaymentLocators.BankPendingPartyDocumentsFrameName, ElementType.Xpath);
//				flag = webDB.waitForElement(CashPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
//						ElementType.Xpath);
//				if (flag) {
//					webDB.clickAnElement(CashPaymentLocators.BankPaymentPendingPartyDocumentSearchButton,
//							ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.searchAndSelectValueInFilters(CashPaymentLocators.BankReceiptDocNumberLovButton,
						VoucherNumber.get(i - 1));
				webDB.switchToFrame(CashPaymentLocators.BankPendingAdvPartyDocumentsFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(CashPaymentLocators.BankPaymentAdvPendingPartyDocumentSearchButton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CashPaymentLocators.BankPaymentAdvPendingPartyDocumentSearchButton,
							ElementType.Xpath);
					commonFunc.hardWait(500);
					commonFunc.checkRefreshCellsDisappear();
					String GetTextDocumentNumber = webDB.getTextFromElement(
							CashPaymentLocators.PendingAdvPayablesDocumentNumberField, ElementType.Xpath);
					logs.logging("INFO", "Get Text Document Number Is " + GetTextDocumentNumber);
					if (GetTextDocumentNumber.equals(VoucherNumber.get(i - 1))) {
						verifyPendingAdvancePayablesThroughGetDoc(SheetName, VoucherNumber.get(i - 1), i);
						flag = webDB.waitForElement(
								CashPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i - 1)),
								ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(
									CashPaymentLocators.BPPendingPartyDocumentSelectCheckBox(VoucherNumber.get(i - 1)),
									ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(3000);
							flag = webDB.waitForElement(CashPaymentLocators.BankPendingAdvPartyDocumentScreenOkButton,
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
			flag = webDB.waitForElement(CashPaymentLocators.BankPendingAdvPartyDocumentScreenOkButton,
					ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(CashPaymentLocators.BankPendingAdvPartyDocumentScreenOkButton, ElementType.Xpath);
				webDB.javaScriptClickAnElement(CashPaymentLocators.BankPendingAdvPartyDocumentScreenOkButton,
						ElementType.Xpath);
				commonFunc.hardWait(500);
				commonFunc.checkRefreshCellsDisappear();
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(CashPaymentLocators.BankHeaderCreateJournalButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Ok Button Is Clicked Successfully");
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
	public void CPAdvThroughAddButton(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createCashPaymentEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCP"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashPaymentDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"), Currency);
		if (!SheetName.contains("TDS")) {
			CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
			for (int i = 1; i < CashPayments.length; i++) {
				String GLAccountDesc = CashPayments[i][1];
				String PartyName = CashPayments[i][3];
				String EntityName = CashPayments[i][5];
				String CPCCodeDesc = CashPayments[i][6];
				String BillNumber = CashPayments[i][7];
				String PayAmount = CashPayments[i][8];
				String DRCR = CashPayments[i][9];
				String CURCY = CashPayments[i][10];
				String EXRate = CashPayments[i][11];
				String TransCURCY = CashPayments[i][12];
				String BaseCURCY = CashPayments[i][13];
				String AssesableValue = CashPayments[i][16];
				if (!GLAccountDesc.equals("")) {
					clickCPLineAddButton();
					addCashPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
					clickLineWiseSaveButton();
					clickLineWiseAddButton(i);
					clickPendingAdvancePartyDocumentsInBP();
					selectAdvPartyDocumentBasedOnPB(PartyName, VariableHandler.getMultipleAdvPymtDocs(), SheetName);
					if (!Currency.equals("INR")) {
						excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
								Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
					} else {
						excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, "1");
					}
					verifyCashPaymentsLineDetailsWithDB(SheetName, i);
					for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
						excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalADV"), 1, 5 + j,
								dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
										commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
						excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalADV"), 2, 5 + j,
								dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
										commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
					}
				}
			}
		} else {
			CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
			for (int i = 1; i < CashPayments.length; i++) {
				String GLAccountDesc = CashPayments[i][1];
				String PartyName = CashPayments[i][3];
				String POSheetName = CashPayments[i][4];
				String EntityName = CashPayments[i][5];
				String CPCCodeDesc = CashPayments[i][6];
				String BillNumber = CashPayments[i][7];
				String PayAmount = CashPayments[i][8];
				String DRCR = CashPayments[i][9];
				String CURCY = CashPayments[i][10];
				String EXRate = CashPayments[i][11];
				String TransCURCY = CashPayments[i][12];
				String BaseCURCY = CashPayments[i][13];
				String AssesableValue = CashPayments[i][16];
				if (!POSheetName.equals("")) {
					clickCPLineAddButton();
					addCashPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
					clickLineWiseSaveButton();
					clickLineWiseAddButton(i);
					clickPendingAdvancePartyDocumentsInBP();
					selectAdvPartyDocumentBasedOnPB(PartyName, VariableHandler.getMultipleAdvPymtDocs(), SheetName);
					if (!Currency.equals("INR")) {
						excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
								Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
					} else {
						excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, "1");
					}
				}
			}
			chooseLineWiseTDS(commonFunc.getDataFromTestDataSheet("CPTDS"), 1);
			clickApplyDeductionButton();
			for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
				excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("TDSCPJournalADV"), 1, 5 + j,
						dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
								commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
				excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("TDSCPJournalADV"), 2, 5 + j,
						dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
								commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
			}
			for (int i = 1; i < CashPayments.length; i++) {
				String GLAccountDesc = CashPayments[i][1];
				String BaseCURCY = CashPayments[i][13];
				if (!GLAccountDesc.equals("")) {
					verifyCashPaymentsLineDetailsWithDB(SheetName, i);
				}
			}
		}
		clickCreateJournalButton();
		if (SheetName.contains("TDS")) {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("TDSCPJournalADV"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
			for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
				excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("TDSCPJournalADV"), 1, 5 + j, "0");
				excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("TDSCPJournalADV"), 2, 5 + j, "0");
			}
			clearDataInExcel(SheetName);
		} else {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CPJournalADV"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
			for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
				excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalADV"), 1, 5 + j, "0");
				excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalADV"), 2, 5 + j, "0");
			}
			clearDataInExcel(SheetName);
		}
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCPVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To create CP Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param Currency
	 * @param CashierName
	 */
	public void CPAdvThroughGetDocument(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createCashPaymentEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCP"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashPaymentDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"), Currency);
		clickGetDocumentInBPHeader();
		clickPendingAdvancePartyDocumentsInBP();
		choosePartyNameAndClickSearchButtonInPendingAdvPartyDocs(VariableHandler.getMultipleAdvPymtDocs(), SheetName);
		if (!SheetName.contains("TDS")) {
			CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
			for (int i = 1; i < CashPayments.length; i++) {
				String GLAccountDesc = CashPayments[i][1];
				String BaseCURCY = CashPayments[i][13];
				if (!GLAccountDesc.equals("")) {
					if (!Currency.equals("INR")) {
						excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
								Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
					} else {
						excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, "1");
					}
					verifyCashPaymentsLineDetailsWithDB(SheetName, i);
					for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
						excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalADV"), 1, 5 + j,
								dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
										commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
						excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalADV"), 2, 5 + j,
								dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
										commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
					}
				}
			}
		} else {
			CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
			for (int i = 1; i < CashPayments.length; i++) {
				String GLAccountDesc = CashPayments[i][1];
				String BaseCURCY = CashPayments[i][13];
				if (!GLAccountDesc.equals("")) {
					if (!Currency.equals("INR")) {
						excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
								Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
					} else {
						excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, "1");
					}
				}
			}
			chooseLineWiseTDS(commonFunc.getDataFromTestDataSheet("CPTDS"), 1);
			clickApplyDeductionButton();
			for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
				excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("TDSCPJournalADV"), 1, 5 + j,
						dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
								commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
				excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("TDSCPJournalADV"), 2, 5 + j,
						dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
								commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
			}
			for (int i = 1; i < CashPayments.length; i++) {
				String GLAccountDesc = CashPayments[i][1];
				String BaseCURCY = CashPayments[i][13];
				if (!GLAccountDesc.equals("")) {
					verifyCashPaymentsLineDetailsWithDB(SheetName, i);
				}
			}
		}
		clickCreateJournalButton();
		if (SheetName.contains("TDS")) {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("TDSCPJournalADV"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
			for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
				excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("TDSCPJournalADV"), 1, 5 + j, "0");
				excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("TDSCPJournalADV"), 2, 5 + j, "0");
			}
			clearDataInExcel(SheetName);
		} else {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CPJournalADV"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
			for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
				excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalADV"), 1, 5 + j, "0");
				excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalADV"), 2, 5 + j, "0");
			}
			clearDataInExcel(SheetName);
		}
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCPVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used to Create CP Through Pending docs
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void CPAdvThroughPendingDocs(String CreationType, String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingCRDocsButton();
		clickPendingAdvancePartyDocumentsInPendingCrDocsBP();
		chooseDocumentNumberInAdvPendingPayablesScreen(VariableHandler.getMultipleAdvPymtDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentTypeCash"),
				commonFunc.getDataFromTestDataSheet("BankPayMode"), CashierName, CreationType, SheetName);
		getCashPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		if (!SheetName.contains("TDS")) {
			CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
			for (int i = 1; i < CashPayments.length; i++) {
				String GLAccountDesc = CashPayments[i][1];
				String BaseCURCY = CashPayments[i][13];
				if (!GLAccountDesc.equals("")) {
					if (!Currency.equals("INR")) {
						excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
								Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
					} else {
						excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, "1");
					}
					verifyCashPaymentsLineDetailsWithDB(SheetName, i);
					for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
						excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalADV"), 1, 5 + j,
								dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
										commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
						excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalADV"), 2, 5 + j,
								dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
										commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
					}
				}
			}
		} else {
			CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
			for (int i = 1; i < CashPayments.length; i++) {
				String GLAccountDesc = CashPayments[i][1];
				String BaseCURCY = CashPayments[i][13];
				if (!GLAccountDesc.equals("")) {
					if (!Currency.equals("INR")) {
						excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
								Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
					} else {
						excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, "1");
					}
				}
			}
			chooseLineWiseTDS(commonFunc.getDataFromTestDataSheet("CPTDS"), 1);
			clickApplyDeductionButton();
			for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
				excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("TDSCPJournalADV"), 1, 5 + j,
						dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
								commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
				excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("TDSCPJournalADV"), 2, 5 + j,
						dbCommonFunctions.POAdvanceDetails(VariableHandler.getPONumber(),
								commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
			}
			for (int i = 1; i < CashPayments.length; i++) {
				String GLAccountDesc = CashPayments[i][1];
				String BaseCURCY = CashPayments[i][13];
				if (!GLAccountDesc.equals("")) {
					verifyCashPaymentsLineDetailsWithDB(SheetName, i);
				}
			}
		}
		clickCreateJournalButton();
		if (SheetName.contains("TDS")) {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("TDSCPJournalADV"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
			for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
				excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("TDSCPJournalADV"), 1, 5 + j, "0");
				excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("TDSCPJournalADV"), 2, 5 + j, "0");
			}
			clearDataInExcel(SheetName);
		} else {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CPJournalADV"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
			for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
				excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalADV"), 1, 5 + j, "0");
				excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalADV"), 2, 5 + j, "0");
			}
			clearDataInExcel(SheetName);
		}
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCPVDocumentStatus();
		}
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
	public void chooseDocumentNumberInAdvPendingPayablesScreen(ArrayList<String> DocumentNumber, String Type,
			String PayMode, String BankName, String CreationType, String SheetName) {
		flag = webDB.waitForElement(CashPaymentLocators.BankPendingAdvPartyDocumentsFrameName, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(CashPaymentLocators.BankPendingAdvPartyDocumentsFrameName, ElementType.Xpath);
			for (int i = 1; i <= DocumentNumber.size(); i++) {
				commonFunc.searchAndSelectValueInFilters(CashPaymentLocators.BankReceiptDocNumberLovButton,
						DocumentNumber.get(i - 1));
				webDB.switchToFrame(CashPaymentLocators.BankPendingAdvPartyDocumentsFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(CashPaymentLocators.PendAdvPayableSearchButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CashPaymentLocators.PendAdvPayableSearchButton, ElementType.Xpath);
					commonFunc.hardWait(500);
					commonFunc.checkRefreshCellsDisappear();
					String GetTextDocumentNumber = webDB.getTextFromElement(
							CashPaymentLocators.PendingAdvancePayablesDocumentNumberField, ElementType.Xpath);
					if (GetTextDocumentNumber.equals(DocumentNumber.get(i - 1))) {
						commonFunc.hardWait(1000);
						verifyPendingAdvancePayablesThroughGetDoc(SheetName, DocumentNumber.get(i - 1), i);
						webDB.clickAnElement(
								CashPaymentLocators.BPPendingPartyDocumentSelectCheckBox(DocumentNumber.get(i - 1)),
								ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(1500);
						flag = webDB.waitForElement(CashPaymentLocators.PendingAdvanceCreateButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "This Purchase Bills Document " + DocumentNumber.get(i - 1)
									+ " Is Selected Successfully");
							if (DocumentNumber.size() > 1) {
								webDB.clickAnElement(CashPaymentLocators.CashPendingPartyDocumentClearButton,
										ElementType.Xpath);
								flag = webDB.waitForElement(CashPaymentLocators.PendAdvPayableSearchButton,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(CashPaymentLocators.PendAdvPayableSearchButton,
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
			webDB.moveToElement(CashPaymentLocators.PendingAdvanceCreateButton, ElementType.Xpath);
			webDB.javaScriptClickAnElement(CashPaymentLocators.PendingAdvanceCreateButton, ElementType.Xpath);
			logs.logging("INFO", "Create Button Is Clicked Successfully");
			commonFunc.checkRefreshCellsDisappear();
			if (!CreationType.equals("Single")) {
				flag = webDB.waitForElement(CashPaymentLocators.PendingAdvPayablesMergeLabelInPending,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CashPaymentLocators.PendingAdvPayablesMergeLabelInPending, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
				}
			}
			flag = webDB.waitForElement(CashPaymentLocators.PendingAdvPayablesTypeField, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownByText(CashPaymentLocators.PendingAdvPayablesTypeDropDown, Type, ElementType.Xpath);
				logs.logging("INFO", "Type Successfully Choosen As " + Type);
				if (Type.equals("Bank")) {
					flag = webDB.waitForElement(CashPaymentLocators.PendingAdvpayablesPaymodeField, ElementType.Xpath);
					if (flag) {
						webDB.selectDropDownByText(CashPaymentLocators.PendingAdvpayablesPaymodeField, PayMode,
								ElementType.Xpath);
						logs.logging("INFO", "Pay Mode Successfully Choosen As " + PayMode);
					}
				}
				commonFunc.searchAndSelectValueInFilters(CashPaymentLocators.PendingAdvPayablesBankNameLOVBUtton,
						BankName);
				webDB.switchToFrame(CashPaymentLocators.BankPendingAdvPartyDocumentsFrameName, ElementType.Xpath);
				flag = webDB.waitForElement(CashPaymentLocators.BankPendingCRdocsAdvPartyDocumentScreenOkButton,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CashPaymentLocators.BankPendingCRdocsAdvPartyDocumentScreenOkButton,
							ElementType.Xpath);
					webDB.switchToDefaultContent();
					if (Type.equals("Bank")) {
						flag = webDB.waitForElement(CashPaymentLocators.BankPaymentScreenName, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"OK Button Is Clicked Successfully And Navigated To Bank Payment Screen");
						}
					} else {
						flag = webDB.waitForElement(CashPaymentLocators.CashPaymentScreenName, ElementType.Xpath);
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
	 * This Method Is Used To Create Advance PO Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void advancePOThroughAddButton(String SheetName) {
		PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK();
		getAdvPaymentDocumentFromDataBase();
		VariableHandler.setMultipleAdvPymtDocs(MultiAdvPaymentDocumentNumber);
		excelUtils.writeDataInExcel(FilepathCP, SheetName, 1, 26, dbCommonFunctions.POAdvanceDetails(
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		CPAdvThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
	}

	/**
	 * This Method Is Used To Create Advance PO Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void advancePOThroughGetDocument(String SheetName) {
		PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK();
		getAdvPaymentDocumentFromDataBase();
		VariableHandler.setMultipleAdvPymtDocs(MultiAdvPaymentDocumentNumber);
		excelUtils.writeDataInExcel(FilepathCP, SheetName, 1, 26, dbCommonFunctions.POAdvanceDetails(
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		CPAdvThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
	}

	/**
	 * This Method Is Used To Create Advance PO Through Pending Documents
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void advancePOThroughPendingDocument(String SheetName) {
		PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK();
		getAdvPaymentDocumentFromDataBase();
		VariableHandler.setMultipleAdvPymtDocs(MultiAdvPaymentDocumentNumber);
		excelUtils.writeDataInExcel(FilepathCP, SheetName, 1, 26, dbCommonFunctions.POAdvanceDetails(
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		CPAdvThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
	}

	/**
	 * This Method Is Used To Create Multiple Advance PO Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void advancePOMultipleThroughAddButton(String SheetName) {
		for (int i = 0; i < 2; i++) {
			PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK();
			getAdvPaymentDocumentFromDataBase();
			excelUtils.writeDataInExcel(FilepathCP, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(i),
							commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		}
		VariableHandler.setMultipleAdvPymtDocs(MultiAdvPaymentDocumentNumber);
		CPAdvThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
	}

	/**
	 * This Method Is Used To Create Multiple Advance PO Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void advancePOMultipleThroughGetDocument(String SheetName) {
		for (int i = 0; i < 2; i++) {
			PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK();
			getAdvPaymentDocumentFromDataBase();
			excelUtils.writeDataInExcel(FilepathCP, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(i),
							commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		}
		VariableHandler.setMultipleAdvPymtDocs(MultiAdvPaymentDocumentNumber);
		CPAdvThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
	}

	/**
	 * This Method Is Used To Create Multiple Advance PO Through Pending Documents
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void advancePOMultipleThroughPendingDocument(String SheetName) {
		for (int i = 0; i < 2; i++) {
			PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK();
			getAdvPaymentDocumentFromDataBase();
			excelUtils.writeDataInExcel(FilepathCP, SheetName, 1, 25 + (i + 1),
					dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(i),
							commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		}
		VariableHandler.setMultipleAdvPymtDocs(MultiAdvPaymentDocumentNumber);
		CPAdvThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
	}

	/**
	 * This Method Is Used To Create Advance PO Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void advancePOThroughAddButtonWithTDS(String SheetName) {
		PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK();
		getAdvPaymentDocumentFromDataBase();
		VariableHandler.setMultipleAdvPymtDocs(MultiAdvPaymentDocumentNumber);
		excelUtils.writeDataInExcel(FilepathCP, SheetName, 1, 26, dbCommonFunctions.POAdvanceDetails(
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		CPAdvThroughAddButton(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
	}

	/**
	 * This Method Is Used To Create Advance PO Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void advancePOThroughGetDocumentWithTDS(String SheetName) {
		PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK();
		getAdvPaymentDocumentFromDataBase();
		VariableHandler.setMultipleAdvPymtDocs(MultiAdvPaymentDocumentNumber);
		excelUtils.writeDataInExcel(FilepathCP, SheetName, 1, 26, dbCommonFunctions.POAdvanceDetails(
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		CPAdvThroughGetDocument(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
	}

	/**
	 * This Method Is Used To Create Advance PO Through Pending Documents
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void advancePOThroughPendingDocumentWithTDS(String SheetName) {
		PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK();
		getAdvPaymentDocumentFromDataBase();
		VariableHandler.setMultipleAdvPymtDocs(MultiAdvPaymentDocumentNumber);
		excelUtils.writeDataInExcel(FilepathCP, SheetName, 1, 26, dbCommonFunctions.POAdvanceDetails(
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		CPAdvThroughPendingDocs(commonFunc.getDataFromTestDataSheet("CreationTypeSingle"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
	}

	/**
	 * This Methos Is Used To Click The Apply Deduction Button In The Cash Payment
	 * Header
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickApplyDeductionButton() {
		flag = webDB.waitForElement(CashPaymentLocators.CashPaymentApplyDeductionButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CashPaymentLocators.CashPaymentApplyDeductionButton, ElementType.Xpath);
			flag = webDB.waitForElement(CashPaymentLocators.CashPaymentApplyDeductionCheckedButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "In Cash Payment Apply Deduction Button Is Clicked Successfully");
			}
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
		flag = webDB.waitForElement(CashPaymentLocators.LineWiseTDS(i), ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(CashPaymentLocators.LineWiseTDS(i), ElementType.Xpath);
			flag = webDB.waitForElement(CashPaymentLocators.CashPaymentTDSLovButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Line Wise TDS Field Is Clicked Sucessfully");
				commonFunc.searchAndSelectValueInFilters(CashPaymentLocators.CashPaymentTDSLovButton, TDS);
				webDB.pressTabKey();

			}
		}
	}

	/**
	 * This Method Is Used To Create Multiple Advance PO Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void advancePOMultipleThroughAddButtonWithMultipleTDS(String SheetName) {
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String SupplierID = CashPayments[i][2];
			String POSheetName = CashPayments[i][4];
			if (!POSheetName.equals("")) {
				PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK1(SupplierID,
						POSheetName);
				getAdvPaymentDocumentFromDataBase();
				excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 26,
						dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(i - 1),
								commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
			}
		}
		VariableHandler.setMultipleAdvPymtDocs(MultiAdvPaymentDocumentNumber);
		CPAdvThroughAddButtonWithMultiTDS(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
	}

	/**
	 * This Method Is Used To Create Multiple Advance PO Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void advancePOMultipleThroughGetDocumentWithMultipleTDS(String SheetName) {
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String SupplierID = CashPayments[i][2];
			String POSheetName = CashPayments[i][4];
			if (!POSheetName.equals("")) {
				PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK1(SupplierID,
						POSheetName);
				getAdvPaymentDocumentFromDataBase();
				excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 26,
						dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(i - 1),
								commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
			}
		}
		VariableHandler.setMultipleAdvPymtDocs(MultiAdvPaymentDocumentNumber);
		CPAdvThroughGetDocumentWithMultiTDS(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
	}

	/**
	 * This Method Is Used To Create Multiple Advance PO Through Pending Documents
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void advancePOMultipleThroughPendingDocumentWithMultipleTDS(String SheetName) {
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String SupplierID = CashPayments[i][2];
			String POSheetName = CashPayments[i][4];
			if (!POSheetName.equals("")) {
				PurFunc.verifyDirectPODomesticStockTransferSimilarUOMWithMultipleItemWithExcelSheetHK1(SupplierID,
						POSheetName);
				getAdvPaymentDocumentFromDataBase();
				excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 26,
						dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(i - 1),
								commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
			}
		}
		VariableHandler.setMultipleAdvPymtDocs(MultiAdvPaymentDocumentNumber);
		CPAdvThroughPendingDocsWithMultiTDS(commonFunc.getDataFromTestDataSheet("CreationTypeMerge"), SheetName,
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
	}

	/**
	 * This Method Is Used To create CP Through Get Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param Currency
	 * @param CashierName
	 */
	public void CPAdvThroughGetDocumentWithMultiTDS(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createCashPaymentEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCP"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashPaymentDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"), Currency);
		clickGetDocumentInBPHeader();
		clickPendingAdvancePartyDocumentsInBP();
		choosePartyNameAndClickSearchButtonInPendingAdvPartyDocs(VariableHandler.getMultipleAdvPymtDocs(), SheetName);
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String POSheetName = CashPayments[i][4];
			String BaseCURCY = CashPayments[i][13];
			String TDS = CashPayments[i][29];
			if (!Currency.equals("INR")) {
				excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
						Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
			} else {
				excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, "1");
			}
			if (!POSheetName.equals("")) {
				chooseLineWiseTDS(TDS, i);
			}
		}
		clickApplyDeductionButton();
		for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("TDSCPJournalADVMulti"), j, 6,
					dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(j - 1),
							commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		}
		for (int i = 1; i < CashPayments.length; i++) {
			String GLAccountDesc = CashPayments[i][1];
			String BaseCURCY = CashPayments[i][13];
			if (!GLAccountDesc.equals("")) {
				verifyCashPaymentsLineDetailsWithDB(SheetName, i);
			}
		}
		clickCreateJournalButton();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("TDSCPJournalADVMulti"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
		for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("TDSCPJournalADVMulti"), 1, 5 + j, "0");
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("TDSCPJournalADVMulti"), 2, 5 + j, "0");
		}
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCPVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used to Create CP Through Pending docs
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void CPAdvThroughPendingDocsWithMultiTDS(String CreationType, String SheetName, String Currency,
			String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingCRDocsButton();
		clickPendingAdvancePartyDocumentsInPendingCrDocsBP();
		chooseDocumentNumberInAdvPendingPayablesScreen(VariableHandler.getMultipleAdvPymtDocs(),
				commonFunc.getDataFromTestDataSheet("PaymentTypeCash"),
				commonFunc.getDataFromTestDataSheet("BankPayMode"), CashierName, CreationType, SheetName);
		getCashPaymentOrReceiptDocumentNumber();
		commonFunc.checkRefreshCellsDisappear();
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String POSheetName = CashPayments[i][4];
			String BaseCURCY = CashPayments[i][13];
			String TDS = CashPayments[i][29];
			if (!Currency.equals("INR")) {
				excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
						Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
			} else {
				excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, "1");
			}
			if (!POSheetName.equals("")) {
				chooseLineWiseTDS(TDS, i);
			}
		}
		clickApplyDeductionButton();
		for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("TDSCPJournalADVMulti"), j, 6,
					dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(j - 1),
							commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		}
		for (int i = 1; i < CashPayments.length; i++) {
			String GLAccountDesc = CashPayments[i][1];
			String BaseCURCY = CashPayments[i][13];
			if (!GLAccountDesc.equals("")) {
				verifyCashPaymentsLineDetailsWithDB(SheetName, i);
			}
		}
		clickCreateJournalButton();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("TDSCPJournalADVMulti"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
		for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("TDSCPJournalADVMulti"), 1, 5 + j, "0");
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("TDSCPJournalADVMulti"), 2, 5 + j, "0");
		}
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCPVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The Bank Payment Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void CPAdvThroughAddButtonWithMultiTDS(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createCashPaymentEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCP"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashPaymentDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"), Currency);
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String GLAccountDesc = CashPayments[i][1];
			String POSheetName = CashPayments[i][4];
			String PartyName = CashPayments[i][3];
			String EntityName = CashPayments[i][5];
			String CPCCodeDesc = CashPayments[i][6];
			String BillNumber = CashPayments[i][7];
			String PayAmount = CashPayments[i][8];
			String DRCR = CashPayments[i][9];
			String CURCY = CashPayments[i][10];
			String EXRate = CashPayments[i][11];
			String TransCURCY = CashPayments[i][12];
			String BaseCURCY = CashPayments[i][13];
			String AssesableValue = CashPayments[i][16];
			String TDS = CashPayments[i][29];
			if (!POSheetName.equals("")) {
				clickCPLineAddButton();
				addCashPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
				clickLineWiseSaveButton();
				clickLineWiseAddButton(i);
				clickPendingAdvancePartyDocumentsInBP();
				selectAdvPartyDocumentBasedOnPBMultiTDS(PartyName, VariableHandler.getMultipleAdvPymtDocs(), SheetName,
						i);
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, "1");
				}
				chooseLineWiseTDS(TDS, i);
			}
		}
		clickApplyDeductionButton();
		for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("TDSCPJournalADVMulti"), j, 6,
					dbCommonFunctions.POAdvanceDetails(VariableHandler.getMultiplePODocs().get(j - 1),
							commonFunc.getDataFromTestDataSheet("AdvancePOPayableColumnName")));
		}
		for (int k = 1; k < CashPayments.length; k++) {
			String GLAccountDesc1 = CashPayments[k][1];
			String BaseCURCY1 = CashPayments[k][13];
			if (!GLAccountDesc1.equals("")) {
				verifyCashPaymentsLineDetailsWithDB(SheetName, k);
			}
		}
		clickCreateJournalButton();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("TDSCPJournalADVMulti"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
		for (int j = 1; j <= VariableHandler.getMultipleAdvPymtDocs().size(); j++) {
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("TDSCPJournalADVMulti"), 1, 5 + j, "0");
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("TDSCPJournalADVMulti"), 2, 5 + j, "0");
		}
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCPVDocumentStatus();
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
	public void selectAdvPartyDocumentBasedOnPBMultiTDS(String PartyName, ArrayList<String> VoucherNumber,
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
							verifyPendingAdvancePayablesThroughGetDoc(SheetName, VoucherNumber.get(i - 1), i); // TODO
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
				flag = webDB.waitForElement(CashPaymentLocators.BankHeaderCreateJournalButton, ElementType.Xpath);
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
	public void CPadvanceWithNewRef(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createCashPaymentEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCP"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashPaymentDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"), Currency);
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String GLAccountDesc = CashPayments[i][1];
			String PartyName = CashPayments[i][3];
			String EntityName = CashPayments[i][5];
			String CPCCodeDesc = CashPayments[i][6];
			String BillNumber = CashPayments[i][7];
			String PayAmount = CashPayments[i][8];
			String DRCR = CashPayments[i][9];
			String CURCY = CashPayments[i][10];
			String EXRate = CashPayments[i][11];
			String TransCURCY = CashPayments[i][12];
			String BaseCURCY = CashPayments[i][13];
			String AssesableValue = CashPayments[i][16];
			String TDS = CashPayments[i][29];
			if (!TDS.equals("")) {
				clickCPLineAddButton();
				addCashPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
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
//			}
				verifyCashPaymentsLineDetailsWithDB(SheetName, i);
				if (SheetName.contains("TDS")) {
					if (!TDS.equals("")) {
						excelUtils.writeDataInExcel(FilepathCP,
								webDB.getDataFromProperties("TDSCPJournalADVWithNewRef"), 1, 6, PayAmount);
						excelUtils.writeDataInExcel(FilepathCP,
								webDB.getDataFromProperties("TDSCPJournalADVWithNewRef"), 2, 6, PayAmount);
					}
				} else {
					excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalADVWithNewRef"), 1, 6,
							PayAmount);
					excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalADVWithNewRef"), 2, 6,
							PayAmount);
				}
			}
		}
		clickCreateJournalButton();
		commonFunc.checkRefreshCellsDisappear();
		if (SheetName.contains("TDS")) {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("TDSCPJournalADVWithNewRef"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("TDSCPJournalADVWithNewRef"), 1, 6,
					"0");
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("TDSCPJournalADVWithNewRef"), 2, 6,
					"0");
			clearDataInExcel(SheetName);
		} else {
			bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("CPJournalADVWithNewRef"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalADVWithNewRef"), 1, 6, "0");
			excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("CPJournalADVWithNewRef"), 2, 6, "0");
			clearDataInExcel(SheetName);
		}
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.logoutUser();
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCPVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The Bank Payment Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void CPAdvNewRefWithMultiTDS(String SheetName, String Currency, String CashierName) {
		commonFunc.validCommonLogin(System.getProperty("HKFinUsername"), System.getProperty("LoginPassword"),
				System.getProperty("HKFinUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		clickAddButtonInPaymentsRecipts();
		createCashPaymentEntry(commonFunc.getDataFromTestDataSheet("CashDocModeCP"), CashierName, Today,
				commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"));
		getCashPaymentOrReceiptDocumentNumber();
		bankpaymentfunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"),
				VariableHandler.getCashPaymentOrReceiptDocNumber());
		verifyCashPaymentDetailsWithDB(CashierName, commonFunc.getDataFromTestDataSheet("CashPaymentPrefix"), Currency);
		CashPayments = excelUtils.readAllDataInExcel(FilepathCP, SheetName);
		for (int i = 1; i < CashPayments.length; i++) {
			String GLAccountDesc = CashPayments[i][1];
			String POSheetName = CashPayments[i][4];
			String PartyName = CashPayments[i][3];
			String EntityName = CashPayments[i][5];
			String CPCCodeDesc = CashPayments[i][6];
			String BillNumber = CashPayments[i][7];
			String PayAmount = CashPayments[i][8];
			String DRCR = CashPayments[i][9];
			String CURCY = CashPayments[i][10];
			String EXRate = CashPayments[i][11];
			String TransCURCY = CashPayments[i][12];
			String BaseCURCY = CashPayments[i][13];
			String AssesableValue = CashPayments[i][16];
			String TDS = CashPayments[i][29];
			if (!POSheetName.equals("")) {
				clickCPLineAddButton();
				addCashPaymentLineDetails(GLAccountDesc, CPCCodeDesc);
				clickLineWiseSaveButton();
				clickLineWiseViewButton(i);
				clickAddButtonInViewDetails();
				addBillNumberAndPayAmount(BillNumber, PayAmount);
				clickViewDetailsSaveButton();
				clickBankViewDetailsCloseButton();
				if (!Currency.equals("INR")) {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, dbCommonFunctions.ExchangeRateDetails(
							Currency, commonFunc.getDataFromTestDataSheet("ImportPurchaseEXRateColumnName")));
				} else {
					excelUtils.writeDataInExcel(FilepathCP, SheetName, i, 11, "1");
				}
				chooseLineWiseTDS(TDS, i);
				excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("TDSCPJournalADVMultiNewref"), i, 6,
						PayAmount);
			}
		}
		clickApplyDeductionButton();
		for (int k = 1; k < CashPayments.length; k++) {
			String GLAccountDesc1 = CashPayments[k][1];
			String BaseCURCY1 = CashPayments[k][13];
			if (!GLAccountDesc1.equals("")) {
				verifyCashPaymentsLineDetailsWithDB(SheetName, k);
			}
		}
		clickCreateJournalButton();
		bankreceiptfunc.verifyJournalAmountWithDB(webDB.getDataFromProperties("TDSCPJournalADVMultiNewref"),
				VariableHandler.getCashPaymentOrReceiptDocNumber(), FilepathCP);
		excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("TDSCPJournalADVMultiNewref"), 1, 6, "0");
		excelUtils.writeDataInExcel(FilepathCP, webDB.getDataFromProperties("TDSCPJournalADVMultiNewref"), 2, 6, "0");
		clearDataInExcel(SheetName);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"),
					VariableHandler.getCashPaymentOrReceiptDocNumber());
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getCashPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			VerifyCPVDocumentStatus();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Create Advance PO Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void advanceWithNewRef(String SheetName) {
		CPadvanceWithNewRef(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
	}

	/**
	 * This Method Is Used To Create Advance PO Through Add Button
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void advanceWithNewRefMultiTDS(String SheetName) {
		CPAdvNewRefWithMultiTDS(SheetName, commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				commonFunc.getDataFromTestDataSheet("CashierName"));
	}

	/**
	 * This Method Is Used to set the Variable Handler To Zero After Finishing One
	 * Test Scenarios
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void verifyAfterMethod() {
		Grnfunc.MultiGRNDocs.clear();
		PBDocumentNumber.clear();
		VariableHandler.setMultipleGRNDocs(null);
		webDB.navigateToRefresh();
		webDB.switchToDefaultContent();
		commonFunc.enterUrl();
	}

}
