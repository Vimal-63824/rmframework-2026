package commonfunctions.financials;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks December 17, 2025
 */
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonfunctions.supplychainmanagement.DirectGrnPageFunctions;
import commonfunctions.supplychainmanagement.SalesOrderFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.financials.BankPaymentLocators;
import commonlocators.financials.PurchaseBillsGrnLocators;
import commonlocators.financials.PurchaseBillsLocators;
import commonlocators.supplychainmanagement.GrnLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class PurchaseBillsFunctionsGrn {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	static ApprovalPageFunctions approvalPageFunc = new ApprovalPageFunctions();
	static DirectGrnPageFunctions DirectGRNFunc = new DirectGrnPageFunctions();
	public ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	static SalesOrderFunctions SalesFunc = new SalesOrderFunctions();
	static BankPaymentFunctions BPFunc = new BankPaymentFunctions();
	static SalesOrderFunctions salesOrdFunc = new SalesOrderFunctions();
	private static DBCommonFunctions dbCommonFunc = new DBCommonFunctions();

	String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
	ExcelUtils excelUtils = new ExcelUtils();
	String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
			+ "DirectGrnDetails.xlsx";

	/**
	 * This Method Is Used To Add The Bill Number And Pay Amount In The Bank Payment
	 * View Details
	 * 
	 * @author Harikrishna - RMITS
	 * @param BillNumber
	 * @param PayAmount
	 */
	public void verifyCreatingPurchaseBillwithAdvance() throws IOException {
		verifyRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviation();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				System.getProperty("DuraiUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		verifyCreatePaymentFromPurchaseBill(commonFunc.getDataFromTestDataSheet("PaymentType"),
				commonFunc.getDataFromTestDataSheet("BankPayMode"), commonFunc.getDataFromTestDataSheet("BankName"));
		BPFunc.getBankPaymentOrReceiptDocumentNumber();
//			BPFunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"));
//			VerifyBankPaymentHeaderDetailsWithUI(commonFunc.getDataFromTestDataSheet("BankName"),
//					commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
//			BPFunc.verifyBankPaymentDetailsWithDB(commonFunc.getDataFromTestDataSheet("BankName"),
//					commonFunc.getDataFromTestDataSheet("BankPrefix"),
//					commonFunc.getDataFromTestDataSheet("BankTransCurcy"));
		clickLineWiseViewButtonForAdvance();
		BPFunc.clickAddButtonInViewDetails();
		addBillNumberAndPayAmountForAdvance();
		BPFunc.clickViewDetailsSaveButton();
		BPFunc.clickBankViewDetailsCloseButton();
		BPFunc.sendReferenceNumberInBP(commonFunc.getDataFromTestDataSheet("ReferenceNumber"));
		BPFunc.clickBPHeaderWiseSaveButton();
		BPFunc.clickCreateJournalButton();
		BPFunc.clickPostButton();
		flag = BPFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		if (flag) {
//			BPFunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		}
	}

	/**
	 * This Method Is Used To Add The Bill Number And Pay Amount In The Bank Payment
	 * View Details
	 * 
	 * @author Harikrishna - RMITS
	 * @param BillNumber
	 * @param PayAmount
	 */
	public void verifyCreatePaymentFromPurchaseBill(String Type, String PayMode, String BankName) {
		flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBBPPendingCrDoc, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsGrnLocators.PBBPPendingCrDoc, ElementType.Xpath);
			logs.logging("INFO", "Pending Cr Doc Is Clicked");

			flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBBPPendingPayables, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseBillsGrnLocators.PBBPPendingPayables, ElementType.Xpath);
				logs.logging("INFO", "Pending Payables Is Clicked");

				flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBBPPendingFrame, ElementType.Xpath);
				if (flag) {
					webDB.switchToFrame(PurchaseBillsGrnLocators.PBBPPendingFrame, ElementType.Xpath);
					logs.logging("INFO", "Switched To Frame");

					commonFunc.searchAndSelectValueInFilters(PurchaseBillsGrnLocators.PBBPPendingDocNoLov,
							VariableHandler.getPbNumber());
					flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBBPPendingFrame, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(PurchaseBillsGrnLocators.PBBPPendingFrame, ElementType.Xpath);
						logs.logging("INFO", "Switched To Frame");

						flag = webDB.waitForElement(PurchaseBillsGrnLocators.PendingPayablesSearchBtn,
								ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseBillsGrnLocators.PendingPayablesSearchBtn, ElementType.Xpath);
							logs.logging("INFO", "Search Button Is Clicked");
							flag = webDB.waitForElement(PurchaseBillsGrnLocators.PendingPayableSelectAll,
									ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(PurchaseBillsGrnLocators.PendingPayableSelectAll,
										ElementType.Xpath);
								logs.logging("INFO", "Document Is Selected");

//							flag = webDB.waitForElement(PurchaseBillsGrnLocators.BPPendingPartyDocumentSelectCheckBox(
//									VariableHandler.getPbNumber()), ElementType.Xpath);
//							if (flag) {
//								webDB.clickAnElement(PurchaseBillsGrnLocators.BPPendingPartyDocumentSelectCheckBox(
//										VariableHandler.getPbNumber()), ElementType.Xpath);
//								logs.logging("INFO", "Document Is Selected");
								commonFunc.checkRefreshCellsDisappear();
								commonFunc.hardWait(1500);
								flag = webDB.waitForElement(PurchaseBillsGrnLocators.PendingPayableCreateBtn,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(PurchaseBillsGrnLocators.PendingPayableCreateBtn,
											ElementType.Xpath);
									logs.logging("INFO", "Document Is Created");
									commonFunc.checkRefreshCellsDisappear();
									flag = webDB.waitForElement(BankPaymentLocators.PendingPayablesTypeField,
											ElementType.Xpath);
									if (flag) {
										webDB.selectDropDownByText(BankPaymentLocators.PendingPayablesTypeDropDown,
												Type, ElementType.Xpath);
										logs.logging("INFO", "Type Successfully Choosen As " + Type);
										if (Type.equals("Bank")) {
											flag = webDB.waitForElement(BankPaymentLocators.PendingpayablesPaymodeField,
													ElementType.Xpath);
											if (flag) {
												webDB.selectDropDownByText(
														BankPaymentLocators.PendingPayablesPayModeDropDown, PayMode,
														ElementType.Xpath);
												logs.logging("INFO", "Pay Mode Successfully Choosen As " + PayMode);
											}
										}
										commonFunc.searchAndSelectValueInFilters(
												BankPaymentLocators.PendingPayablesBankNameLOVBUtton, BankName);
										webDB.switchToFrame(BankPaymentLocators.BankPendingPartyDocumentsFrameName,
												ElementType.Xpath);
										flag = webDB.waitForElement(BankPaymentLocators.PendingPayablesOkButton,
												ElementType.Xpath);
										if (flag) {
											webDB.clickAnElement(BankPaymentLocators.PendingPayablesOkButton,
													ElementType.Xpath);
											webDB.switchToDefaultContent();
											if (Type.equals("Bank")) {
												flag = webDB.waitForElement(BankPaymentLocators.BankPaymentScreenName,
														ElementType.Xpath);
												if (flag) {
													logs.logging("INFO",
															"OK Button Is Clicked Successfully And Navigated To Bank Payment Screen");
												}
											} else {
												flag = webDB.waitForElement(BankPaymentLocators.CashPaymentScreenName,
														ElementType.Xpath);
												if (flag) {
													logs.logging("INFO",
															"OK Button Is Clicked Successfully And Navigated To Cash Payment Screen");
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

	}

	/**
	 * This Method Is Used To Add The Bill Number And Pay Amount In The Bank Payment
	 * View Details
	 * 
	 * @author Harikrishna - RMITS
	 * @param BillNumber
	 * @param PayAmount
	 */
	public void addBillNumberAndPayAmountForAdvance() {
		salesOrdFunc.verifyClickAndEnterValueInTableFields(BankPaymentLocators.BankBillDocField,
				BankPaymentLocators.BankBillNumberInputField, "BILL100001");
		webDB.pressTabKey();
		salesOrdFunc.verifyClickAndEnterValueInTableFields(BankPaymentLocators.BankPayAmountField,
				BankPaymentLocators.BankPayAmountInputField, "5000");
		webDB.pressTabKey();
	}

	/**
	 * This Method Is Used to Click The Line Wise View Button
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickLineWiseViewButtonForAdvance() {
		flag = webDB.waitForElement(BankPaymentLocators.BPAddLineTable, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BPAddLineTable, ElementType.Xpath);
			logs.logging("INFO", "Line Details Button is Clicked");
			flag = webDB.waitForElement(BankPaymentLocators.BPLineDetailViewBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(BankPaymentLocators.BPLineDetailViewBtn, ElementType.Xpath);
				logs.logging("INFO", "Line Details View Button is Clicked");
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
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Creating Grn With Advance
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAgainstGRNWithDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyCreateJournal();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(1000);
//		verifyJournalAmountWithDB(webDB.getDataFromProperties("PurchaseBillGRN"),VariableHandler.getPbNumber());
		verifyCreatePost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAgainstGRNWithDeviationAndExpense() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
//		validatePurchaseBillsLineDetailsAfterDevaition(webDB.getDataFromProperties("PurchaseBill"));
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviationAndExpense() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAgainstGRNWithDeviationExpenseAndAdjustment() throws IOException {
		verifyCreatingPurchaseBillwithAdvance();
		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		storePurchaseBillProcesTabProcessQtyAndUnitCostAgainstGrn();
		clickingReferenceTabAndValidating();
		getMatchedAmtAndGRNAmtandWriteInExcelSheetForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournal();
		deviationCalculationCompareExcelSheetWithDataBaseForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyCreatePost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
		verifyCreatingPurchaseBillwithAdvance();
		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		storePurchaseBillProcesTabProcessQtyAndUnitCostAgainstGrn();
		clickingReferenceTabAndValidating();
		getMatchedAmtAndGRNAmtandWriteInExcelSheetForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournal();
		deviationCalculationCompareExcelSheetWithDataBaseForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyCreatePost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithDeviationAndExpense() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithDeviationExpenseAndAdjustment()
			throws IOException {
		verifyCreatingPurchaseBillwithAdvance();
		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		storePurchaseBillProcesTabProcessQtyAndUnitCostAgainstGrn();
		clickingReferenceTabAndValidating();
		getMatchedAmtAndGRNAmtandWriteInExcelSheetForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyClickingReferenceTabForIgstFlag();
		verifyCreateJournal();
		deviationCalculationCompareExcelSheetWithDataBaseForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyCreatePost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithoutDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithoutDeviationAndExpense() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
		verifyCreatingPurchaseBillwithAdvance();
		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		storePurchaseBillProcesTabProcessQtyAndUnitCostAgainstGrn();
		clickingReferenceTabAndValidating();
		getMatchedAmtAndGRNAmtandWriteInExcelSheetForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyClickingReferenceTabForIgstFlag();
		verifyCreateJournal();
		deviationCalculationCompareExcelSheetWithDataBaseForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyCreatePost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithoutDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingReferenceTabForRcmFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithoutDeviationAndExpense() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingReferenceTabForRcmFlag();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
		verifyCreatingPurchaseBillwithAdvance();
		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		storePurchaseBillProcesTabProcessQtyAndUnitCostAgainstGrn();
		clickingReferenceTabAndValidating();
		getMatchedAmtAndGRNAmtandWriteInExcelSheetForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyClickingReferenceTabForRcmFlag();
		verifyCreateJournal();
		deviationCalculationCompareExcelSheetWithDataBaseForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyCreatePost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingReferenceTabForRcmFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithDeviationAndExpense() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingReferenceTabForRcmFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithDeviationExpenseAndAdjustment()
			throws IOException {
		verifyCreatingPurchaseBillwithAdvance();
		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		storePurchaseBillProcesTabProcessQtyAndUnitCostAgainstGrn();
		clickingReferenceTabAndValidating();
		getMatchedAmtAndGRNAmtandWriteInExcelSheetForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyClickingReferenceTabForRcmFlag();
		verifyCreateJournal();
		deviationCalculationCompareExcelSheetWithDataBaseForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyCreatePost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithDeviationAndExpense()
			throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		verifySaveLine();
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithDeviationExpenseAndAdjustment()
			throws IOException {
		verifyCreatingPurchaseBillwithAdvance();
		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		storePurchaseBillProcesTabProcessQtyAndUnitCostAgainstGrn();
		clickingReferenceTabAndValidating();
		getMatchedAmtAndGRNAmtandWriteInExcelSheetForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		verifySaveLine();
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournal();
		deviationCalculationCompareExcelSheetWithDataBaseForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyCreatePost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithoutDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithoutDeviationAndExpense()
			throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		verifySaveLine();
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
		verifyCreatingPurchaseBillwithAdvance();
		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		storePurchaseBillProcesTabProcessQtyAndUnitCostAgainstGrn();
		clickingReferenceTabAndValidating();
		getMatchedAmtAndGRNAmtandWriteInExcelSheetForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		verifySaveLine();
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournal();
		deviationCalculationCompareExcelSheetWithDataBaseForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyCreatePost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithDeviationAndExpense()
			throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		verifySaveLine();
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithDeviationExpenseAndAdjustment()
			throws IOException {
		verifyCreatingPurchaseBillwithAdvance();
		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		storePurchaseBillProcesTabProcessQtyAndUnitCostAgainstGrn();
		clickingReferenceTabAndValidating();
		getMatchedAmtAndGRNAmtandWriteInExcelSheetForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		verifySaveLine();
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyClickingReferenceTabForIgstFlag();
		verifyCreateJournal();
		deviationCalculationCompareExcelSheetWithDataBaseForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyCreatePost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithoutDeviation()
			throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		verifySaveLine();
		verifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithoutDeviationAndExpense()
			throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		verifySaveLine();
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
		verifyCreatingPurchaseBillwithAdvance();
		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		storePurchaseBillProcesTabProcessQtyAndUnitCostAgainstGrn();
		clickingReferenceTabAndValidating();
		getMatchedAmtAndGRNAmtandWriteInExcelSheetForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		verifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		verifySaveLine();
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyClickingReferenceTabForIgstFlag();
		verifyCreateJournal();
		deviationCalculationCompareExcelSheetWithDataBaseForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyCreatePost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithoutDeviation()
			throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		verifySaveLine();
		verifyClickingReferenceTabForRcmFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithoutDeviationAndExpense()
			throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		verifySaveLine();
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingReferenceTabForRcmFlag();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
		verifyCreatingPurchaseBillwithAdvance();
		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		storePurchaseBillProcesTabProcessQtyAndUnitCostAgainstGrn();
		clickingReferenceTabAndValidating();
		getMatchedAmtAndGRNAmtandWriteInExcelSheetForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		verifySaveLine();
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyClickingReferenceTabForRcmFlag();
		verifyCreateJournal();
		deviationCalculationCompareExcelSheetWithDataBaseForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyCreatePost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		verifySaveLine();
		verifyClickingReferenceTabForRcmFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithDeviationAndExpense()
			throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		verifySaveLine();
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingReferenceTabForRcmFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithDeviationExpenseAndAdjustment()
			throws IOException {
		verifyCreatingPurchaseBillwithAdvance();
		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		storePurchaseBillProcesTabProcessQtyAndUnitCostAgainstGrn();
		clickingReferenceTabAndValidating();
		getMatchedAmtAndGRNAmtandWriteInExcelSheetForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		verifySaveLine();
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyClickingReferenceTabForRcmFlag();
		verifyCreateJournal();
		deviationCalculationCompareExcelSheetWithDataBaseForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyCreatePost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Creating Grn With Advance
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithInterstateGSTAgainstGRNWithDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillInterstate();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNIGST"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithInterstateGSTAgainstGRNWithDeviationAndExpense() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillInterstate();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNIGST"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
//		validatePurchaseBillsLineDetailsAfterDevaition(webDB.getDataFromProperties("PurchaseBill"));
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithInterstateGSTAgainstGRNWithDeviationExpenseAndAdjustment()
			throws IOException {
		verifyCreatingPurchaseBillwithAdvance();
		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillInterstate();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNIGST"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		storePurchaseBillProcesTabProcessQtyAndUnitCostAgainstGrn();
		clickingReferenceTabAndValidating();
		getMatchedAmtAndGRNAmtandWriteInExcelSheetForGrn(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournal();
		deviationCalculationCompareExcelSheetWithDataBaseForGrn(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyCreatePost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithInterstateGSTAgainstGRNWithoutDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillInterstate();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNIGST"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithInterstateGSTAgainstGRNWithoutDeviationAndExpense() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillInterstate();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNIGST"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithInterstateGSTAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
		verifyCreatingPurchaseBillwithAdvance();
		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillInterstate();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNIGST"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		storePurchaseBillProcesTabProcessQtyAndUnitCostAgainstGrn();
		clickingReferenceTabAndValidating();
		getMatchedAmtAndGRNAmtandWriteInExcelSheetForGrn(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournal();
		deviationCalculationCompareExcelSheetWithDataBaseForGrn(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyCreatePost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithInterstateAllowRcmAgainstGRNWithDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillInterstate();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNIGST"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingReferenceTabForRcmFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithInterstateAllowRcmAgainstGRNWithDeviationAndExpense() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillInterstate();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNIGST"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingReferenceTabForRcmFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithInterstateAllowRcmAgainstGRNWithDeviationExpenseAndAdjustment()
			throws IOException {
		verifyCreatingPurchaseBillwithAdvance();
		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillInterstate();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNIGST"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		storePurchaseBillProcesTabProcessQtyAndUnitCostAgainstGrn();
		clickingReferenceTabAndValidating();
		getMatchedAmtAndGRNAmtandWriteInExcelSheetForGrn(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyClickingReferenceTabForRcmFlag();
		verifyCreateJournal();
		deviationCalculationCompareExcelSheetWithDataBaseForGrn(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyCreatePost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithInterstateAllowRcmAgainstGRNWithoutDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillInterstate();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNIGST"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingReferenceTabForRcmFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithInterstateAllowRcmAgainstGRNWithoutDeviationAndExpense()
			throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillInterstate();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNIGST"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingReferenceTabForRcmFlag();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithInterstateAllowRcmAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
		verifyCreatingPurchaseBillwithAdvance();
		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillInterstate();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNIGST"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		storePurchaseBillProcesTabProcessQtyAndUnitCostAgainstGrn();
		clickingReferenceTabAndValidating();
		getMatchedAmtAndGRNAmtandWriteInExcelSheetForGrn(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBillSerIGST"));
		verifySaveLine();
		verifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyClickingReferenceTabForRcmFlag();
		verifyCreateJournal();
		deviationCalculationCompareExcelSheetWithDataBaseForGrn(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyCreatePost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Creating Grn With Advance
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyUnRegisteredSupplierwithLocalGSTAgainstGRNWithDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillUnRegisteredSupplier();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyUnRegisteredSupplierwithLocalGSTAgainstGRNWithDeviationAndExpense() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillUnRegisteredSupplier();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
//		ValidatePurchaseBillsLineDetailsAfterDevaition(webDB.getDataFromProperties("PurchaseBill"));
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyUnRegisteredSupplierwithLocalGSTAgainstGRNWithDeviationExpenseAndAdjustment() throws IOException {
		verifyCreatingPurchaseBillwithAdvance();
		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillUnRegisteredSupplier();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		storePurchaseBillProcesTabProcessQtyAndUnitCostAgainstGrn();
		clickingReferenceTabAndValidating();
		getMatchedAmtAndGRNAmtandWriteInExcelSheetForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournal();
		deviationCalculationCompareExcelSheetWithDataBaseForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyCreatePost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyUnRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillUnRegisteredSupplier();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyUnRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviationAndExpense() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillUnRegisteredSupplier();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyUnRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
		verifyCreatingPurchaseBillwithAdvance();
		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillUnRegisteredSupplier();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		storePurchaseBillProcesTabProcessQtyAndUnitCostAgainstGrn();
		clickingReferenceTabAndValidating();
		getMatchedAmtAndGRNAmtandWriteInExcelSheetForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournal();
		deviationCalculationCompareExcelSheetWithDataBaseForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyCreatePost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyUnRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillUnRegisteredSupplier();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyUnRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithDeviationAndExpense() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillUnRegisteredSupplier();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyUnRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithDeviationExpenseAndAdjustment()
			throws IOException {
		verifyCreatingPurchaseBillwithAdvance();
		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillUnRegisteredSupplier();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		storePurchaseBillProcesTabProcessQtyAndUnitCostAgainstGrn();
		clickingReferenceTabAndValidating();
		getMatchedAmtAndGRNAmtandWriteInExcelSheetForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyClickingReferenceTabForIgstFlag();
		verifyCreateJournal();
		deviationCalculationCompareExcelSheetWithDataBaseForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyCreatePost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyUnRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithoutDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillUnRegisteredSupplier();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyUnRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithoutDeviationAndExpense()
			throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillUnRegisteredSupplier();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		verifySaveLine();
		verifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyUnRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
		verifyCreatingPurchaseBillwithAdvance();
		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillUnRegisteredSupplier();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRN"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		storePurchaseBillProcesTabProcessQtyAndUnitCostAgainstGrn();
		clickingReferenceTabAndValidating();
		getMatchedAmtAndGRNAmtandWriteInExcelSheetForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyClickingReferenceTabForIgstFlag();
		verifyCreateJournal();
		deviationCalculationCompareExcelSheetWithDataBaseForGrn(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyCreatePost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Creating Grn With Advance
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyUnRegisteredSupplierwithInterstateGSTAgainstGRNWithDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillUnRegisteredInterstate();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNIGST"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBillSerIGST"));
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyUnRegisteredSupplierwithInterstateGSTAgainstGRNWithDeviationAndExpense() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillUnRegisteredInterstate();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNIGST"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBillSerIGST"));
		verifySaveLine();
		verifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyUnRegisteredSupplierwithInterstateGSTAgainstGRNWithDeviationExpenseAndAdjustment()
			throws IOException {
		verifyCreatingPurchaseBillwithAdvanceInterstate();
		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillUnRegisteredInterstate();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNIGST"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		storePurchaseBillProcesTabProcessQtyAndUnitCostAgainstGrn();
		clickingReferenceTabAndValidating();
		getMatchedAmtAndGRNAmtandWriteInExcelSheetForGrn(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifySaveLine();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBillSerIGST"));
		verifySaveLine();
		verifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyClickingReferenceTabForIgstFlag();
		verifyCreateJournal();
		deviationCalculationCompareExcelSheetWithDataBaseForGrn(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyCreatePost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyUnRegisteredSupplierwithInterstateGSTAgainstGRNWithoutDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillUnRegisteredInterstate();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNIGST"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBillSerIGST"));
		verifySaveLine();
		verifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyUnRegisteredSupplierwithInterstateGSTAgainstGRNWithoutDeviationAndExpense() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillUnRegisteredInterstate();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNIGST"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBillSerIGST"));
		verifySaveLine();
		verifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyUnRegisteredSupplierwithInterstateGSTAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
		verifyCreatingPurchaseBillwithAdvanceInterstate();
		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillUnRegisteredInterstate();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNIGST"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		storePurchaseBillProcesTabProcessQtyAndUnitCostAgainstGrn();
		clickingReferenceTabAndValidating();
		getMatchedAmtAndGRNAmtandWriteInExcelSheetForGrn(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(
				webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifySaveLine();
//		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
		verifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyClickingReferenceTabForIgstFlag();
		verifyCreateJournal();
		deviationCalculationCompareExcelSheetWithDataBaseForGrn(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
		verifyCreatePost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyUnregisteredSupplierimportedwithoutdeviationwithoutGST() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillImportSupplier();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNImport"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNImport"));
//		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		commonFunc.checkRefreshCellsDisappear();

		verifySaveLine();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyUnregisteredSupplierimportedwithdeviationwithoutGST() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillImportSupplier();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNImport"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNImport"));
//		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		commonFunc.checkRefreshCellsDisappear();
		verifySaveLine();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyUnregisteredSupplierimportedwithdeviationandExpensewithoutGST() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillImportSupplier();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNImport"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
//		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		commonFunc.checkRefreshCellsDisappear();
		verifySaveLine();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyUnregisteredSupplierimportedwithdeviationExpenseAndAdjustmentwithoutGST() throws IOException {
		verifyCreatingPurchaseBillwithAdvanceInterstate();
		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillImportSupplier();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNImport"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
//		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		commonFunc.checkRefreshCellsDisappear();
		verifySaveLine();
		verifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyNonResidentSupplierimportedwithoutdeviationwithoutGST() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillNonResidentImportSupplier();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNImport"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNImport"));
//		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		commonFunc.checkRefreshCellsDisappear();
		verifySaveLine();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyNonResidentSupplierimportedwithdeviationwithoutGST() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillNonResidentImportSupplier();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNImport"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNImport"));
//		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		commonFunc.checkRefreshCellsDisappear();
		verifySaveLine();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyNonResidentSupplierimportedwithdeviationandExpensewithoutGST() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillNonResidentImportSupplier();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNImport"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
//		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		commonFunc.checkRefreshCellsDisappear();
		verifySaveLine();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyNonResidentSupplierimportedwithdeviationExpenseAndAdjustmentwithoutGST() throws IOException {
		verifyCreatingPurchaseBillwithAdvanceInterstateNonResident();
		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillNonResidentImportSupplier();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNImport"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNImport"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"), commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifySaveLine();
//		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		commonFunc.checkRefreshCellsDisappear();
		verifySaveLine();
		verifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyDomesticRegisteredSupplierWithLocalFreightGST() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillSupplierLocalFreight();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("SupplierLocalFreight"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("SupplierLocalFreight"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("SupplierLocalFreight"));
		verifySaveLine();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyDomesticUnRegisteredSupplierWithLocalFreightGST() throws IOException {
		DirectGRNFunc
				.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillSupplierLocalFreightUnRegistered();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("UnregSupplierLocalFreight"),
				commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("UnregSupplierLocalFreight"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("UnregSupplierLocalFreight"));
		verifySaveLine();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyDomesticRegisteredSupplierWithSEZUnitGST() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillSupplierSupplierSEZunit();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("SupplierSEZunit"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("SupplierSEZunit"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("SupplierSEZunit"));
		verifySaveLine();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyDomesticUnRegisteredSupplierWithSEZUnitGST() throws IOException {
		DirectGRNFunc
				.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillSupplierSupplierSEZunitUnRegistered();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("UnregSupplierSEZunit"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("UnregSupplierSEZunit"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("UnregSupplierSEZunit"));
		verifySaveLine();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void DomesticRegisteredSupplierWithunionTerritoryGST() throws IOException {
		DirectGRNFunc
				.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillSupplierSupplierUnionTerritory();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("SupplierUnionTerritory"),
				commonFunc.getDataFromTestDataSheet("PBUTPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("SupplierUnionTerritory"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("SupplierUnionTerritory"));
		verifySaveLine();
		verifyCreateJournalAndPost();
		waitingForApprovalUnit62(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void DomesticUnRegisteredSupplierWithunionTerritoryGST() throws IOException {
		DirectGRNFunc
				.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillSupplierSupplierUnionTerritoryUnRegistered();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("SupplierUnionTerritory"),
				commonFunc.getDataFromTestDataSheet("PBUTPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("UnregSupplierUnionTerritoryUT"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("UnregSupplierUnionTerritoryUT"));
		verifySaveLine();
		verifyCreateJournalAndPost();
		waitingForApprovalUnit62(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void DomesticCompositionSupplierWitLocalGST() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillCompositionSupplierLocal();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("RegSupplierComposition"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("RegSupplierComposition"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("RegSupplierComposition"));
		verifySaveLine();
		verifyCreateJournalAndPost();
		waitingForApprovalUnit62(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void DomesticCompositionSupplierWitLocalFreightGST() throws IOException {
		DirectGRNFunc
				.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillSupplierSupplierLocalFreightComposition();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("LocalFreightComposition"),
				commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("LocalFreightComposition"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("LocalFreightComposition"));
		verifySaveLine();
		verifyCreateJournalAndPost();
		waitingForApprovalUnit62(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Deepan-GWL
	 * @param DocNo
	 */
	public void verifyPendingPurchaseBill(String DocNo, String TdsNo, String Pending, String PBPrefix) {
		flag = webDB.waitForElement(PurchaseBillsGrnLocators.PendingPurchaseBill, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Purchase Bill Is Displayed");
			webDB.clickAnElement(PurchaseBillsGrnLocators.PendingPurchaseBill, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsGrnLocators.PendingGrn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Grn Is Displayed");
				webDB.clickAnElement(PurchaseBillsGrnLocators.PendingGrn, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseBillsGrnLocators.Iframe, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Iframe Is Displayed");
					webDB.switchToFrame(PurchaseBillsGrnLocators.Iframe, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.searchAndSelectValueInFilters(PurchaseBillsGrnLocators.GrnNo, DocNo);
					flag = webDB.waitForElement(PurchaseBillsGrnLocators.Iframe, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(PurchaseBillsGrnLocators.Iframe, ElementType.Xpath);
						flag = webDB.waitForElement(PurchaseBillsGrnLocators.SearchButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Switched To Iframe");
							webDB.clickAnElement(PurchaseBillsGrnLocators.SearchButton, ElementType.Xpath);
							validatePurchaseBillsPendingGRNDetails();
							validatePurchaseBillsPendingGRNItemDetailsIGST(Pending);
							flag = webDB.waitForElement(PurchaseBillsGrnLocators.CheckBox(DocNo), ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "CheckBox IS Displayed");
								commonFunc.hardWait(3000);
								webDB.clickAnElement(PurchaseBillsGrnLocators.CheckBox(DocNo), ElementType.Xpath);
								flag = webDB.waitForElement(PurchaseBillsGrnLocators.CreateDoc, ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Create Documents Is Displayed");
									commonFunc.hardWait(5000);
									webDB.clickAnElement(PurchaseBillsGrnLocators.CreateDoc, ElementType.Xpath);
									flag = webDB.waitForElement(PurchaseBillsGrnLocators.PendingPBPrefixLov,
											ElementType.Xpath);
									if (flag) {
//										webDB.clickAnElement(PurchaseBillsGrnLocators.PendingPBPrefixLov, ElementType.Xpath);
										commonFunc.searchAndSelectValueInFilters(
												PurchaseBillsGrnLocators.PendingPBPrefixLov, PBPrefix);
										logs.logging("INFO", "Prefixn is Selected:" + PBPrefix);
										webDB.switchToFrame(PurchaseBillsGrnLocators.Iframe, ElementType.Xpath);
										flag = webDB.waitForElement(PurchaseBillsGrnLocators.OkButton,
												ElementType.Xpath);
										if (flag) {
											logs.logging("INFO", "Okay Button Is Displayed");
											webDB.clickAnElement(PurchaseBillsGrnLocators.OkButton, ElementType.Xpath);
											webDB.switchToDefaultContent();
											flag = webDB.waitForElement(PurchaseBillsGrnLocators.LocationId,
													ElementType.Xpath);
											storePurchaseVoucherNumber();
											verifyAddTDSNumber(TdsNo);
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
		}
		assertTrue(flag);
	}

	/**
	 * verify Add TDS Number
	 * 
	 * @author Deepan
	 */
	public void verifyAddTDSNumber(String TdsNo) {
		if (webDB.verifyIsElementDisplayed(PurchaseBillsLocators.ExpandButton, ElementType.Xpath)) {
			webDB.clickAnElement(PurchaseBillsLocators.ExpandButton, ElementType.Xpath);
		}
		webDB.moveToElement(PurchaseBillsLocators.TableData, ElementType.Xpath);
		webDB.moveToElement(PurchaseBillsLocators.PurchaseBillsTransLocGstTds(), ElementType.Xpath);
		flag = webDB.waitForElement(PurchaseBillsLocators.PurchaseBillsTransLocGstTds(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocators.PurchaseBillsTransLocGstTds(), ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocators.TDSLovButton, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.TDSLovButton, TdsNo);
				webDB.pressTabKey();
				verifySaveLine();
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
		flag = webDB.waitForElement(PurchaseBillsGrnLocators.CreateJournal, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseBillsGrnLocators.CreateJournal, ElementType.Xpath);
			logs.logging("INFO", "Create Journal Button Is Displayed");
			webDB.clickAnElement(PurchaseBillsGrnLocators.CreateJournal, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(5000);
			flag = webDB.waitForElement(PurchaseBillsGrnLocators.PostButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Post Button Is Displayed");
				webDB.clickAnElement(PurchaseBillsGrnLocators.PostButton, ElementType.Xpath);
				flag = (webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 10)
						|| webDB.waitForElement(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath));
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
		String Number = webDB.getTextFromElement(PurchaseBillsGrnLocators.VoucherNumber, ElementType.Xpath);
		VariableHandler.setPbNumber(Number);
		logs.logging("INFO", VariableHandler.getPbNumber());
		;
	}

	/**
	 * waiting for approval
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyWaitingForApproval() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath)) {
			webDB.switchToFrame(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			flag = webDB.waitForElement(PurchaseBillsGrnLocators.ApproveOkButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseBillsGrnLocators.ApproveOkButton, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Purchase Bills Approval
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPurchaseBillsApproval() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("LoginPassword"),
				"Sathi Ganesh");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNIGST"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		verifyCreateJournalAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("HodPassword"),
				"HariUsername-Admin");
		approvalPageFunc.navigateToApprovals();
		approvalPageFunc.filterDocumentsforApproval(VariableHandler.getPbNumber());
		approvalPageFunc.verifyApproveWithDocNumber(VariableHandler.getPbNumber());
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
					"Sathi Ganesh");
			commonFunc.navigateToFinancialPurchaseBill();
			verifyPurchaseBillApprovalStatus(VariableHandler.getPbNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Purchase Bill Approval Status
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPurchaseBillApprovalStatus(String DocNo, String ExpStatus) {
		commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.VoucherNumberLovButton, DocNo);
		flag = webDB.waitForElement(PurchaseBillsLocators.PurchaseBillSearchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocators.PurchaseBillSearchButton, ElementType.Xpath);
			logs.logging("INFO", "Search Button Is Clicked");
			commonFunc.hardWait(1000);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(PurchaseBillsLocators.ApprovedStatus(DocNo), ElementType.Xpath);
			if (flag) {
				String Status = webDB.getTextFromElement(PurchaseBillsLocators.ApprovedStatus(DocNo),
						ElementType.Xpath);
				flag = Status.contains(ExpStatus);
				if (flag) {
					logs.logging("INFO", "Expected Status: " + ExpStatus);
					logs.logging("INFO", "Verified Status ,Displayed Status: " + ExpStatus);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Pending SCO Purchase Bill
	 * 
	 * @author Deepan-GWL
	 * @param DocNo
	 */
	public void verifyPendingScoPurchaseBill(String DocNo) {
		flag = webDB.waitForElement(CommonUiLocators.PendingPurchaseBill, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Purchase Bill Is Displayed");
			webDB.clickAnElement(CommonUiLocators.PendingPurchaseBill, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocators.PendingSCO, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending SCO Grn Is Displayed");
				webDB.clickAnElement(PurchaseBillsLocators.PendingSCO, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseBillsLocators.ScoFrame, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Iframe Is Displayed");
					webDB.switchToFrame(PurchaseBillsLocators.ScoFrame, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.GrnLovButton, DocNo);
					flag = webDB.waitForElement(PurchaseBillsLocators.ScoFrame, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(PurchaseBillsLocators.ScoFrame, ElementType.Xpath);
						flag = webDB.waitForElement(PurchaseBillsLocators.ScoSearchButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Switched To Iframe");
							webDB.clickAnElement(PurchaseBillsLocators.ScoSearchButton, ElementType.Xpath);
							flag = webDB.waitForElement(PurchaseBillsLocators.ScoCheckBox(DocNo), ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "CheckBox IS Displayed");
								commonFunc.hardWait(3000);
								webDB.clickAnElement(PurchaseBillsLocators.ScoCheckBox(DocNo), ElementType.Xpath);
								flag = webDB.waitForElement(PurchaseBillsLocators.CreateDoc, ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Create Documents Is Displayed");
									verifyEnableCreateButton();
									webDB.clickAnElement(PurchaseBillsLocators.CreateDoc, ElementType.Xpath);
									flag = webDB.waitForElement(PurchaseBillsLocators.ScoOkButton, ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Okay Button Is Displayed");
										webDB.clickAnElement(PurchaseBillsLocators.ScoOkButton, ElementType.Xpath);
										webDB.switchToDefaultContent();
										flag = webDB.waitForElement(PurchaseBillsLocators.LocationId,
												ElementType.Xpath);
										storeSCOPurchaseVoucherNumber();
										if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon,
												ElementType.Xpath)) {
											webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
													ElementType.Xpath);
											if (webDB.verifyIsElementDisplayed(PurchaseBillsLocators.ExpandButton,
													ElementType.Xpath)) {
												webDB.clickAnElement(PurchaseBillsLocators.ExpandButton,
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
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Store SCO Purchase Voucher Number
	 * 
	 * @author Deepan-GWL
	 */
	public void storeSCOPurchaseVoucherNumber() {
		String Number = webDB.getTextFromElement(PurchaseBillsLocators.VoucherNumber, ElementType.Xpath);
		VariableHandler.setScoPurchaseNumber(Number);
	}

	/**
	 * This Method Used To Verify Enable Create Button
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyEnableCreateButton() {
		for (int i = 0; i < 60; i++) {
			commonFunc.hardWait(1000);
			if (webDB.isElementEnabled(PurchaseBillsLocators.CreateDoc, ElementType.Xpath)) {
				flag = true;
				break;
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used for Approve the Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void waitingForApproval(String FrwdPerson) {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(GrnLocators.DocAprovalIfrmae, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.ForwardApproval, FrwdPerson);
			commonFunc.hardWait(2000);
			webDB.switchToFrame(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(PurchaseBillsGrnLocators.OkButtonForward, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseBillsGrnLocators.OkButtonForward, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				flag = webDB.waitForClickElement(CommonUiLocators.LogoutIcon, ElementType.Xpath);
				if (flag) {
					commonFunc.hardWait(3000);
					commonFunc.logoutUser();
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
							webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
							System.getProperty("HariUsername"));
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used for Approve the Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void waitingForApprovalUnit62(String FrwdPerson) {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(GrnLocators.DocAprovalIfrmae, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.ForwardApproval, FrwdPerson);
			commonFunc.hardWait(2000);
			webDB.switchToFrame(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(PurchaseBillsGrnLocators.OkButtonForward, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseBillsGrnLocators.OkButtonForward, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				flag = webDB.waitForClickElement(CommonUiLocators.LogoutIcon, ElementType.Xpath);
				if (flag) {
					commonFunc.hardWait(3000);
					commonFunc.logoutUser();
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPbNumber(),
							webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("UTUnit"),
							System.getProperty("HariUsername"));
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @param SheetName
	 * @author Durai
	 */
	public void verifyChangeCostForDeviation(String SheetName) {
//		String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
//		ExcelUtils excelUtils = new ExcelUtils();
//		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
//				+ "DirectGrnDetails.xlsx";
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < GRNMultipleline.length; i++) {
			System.out.println(GRNMultipleline.length);
			String Cost = GRNMultipleline[i][6];
			if (!Cost.equals("")) {
				flag = webDB.waitForElement(PurchaseBillsGrnLocators.PurchaseBillsTransLocunitprice(), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsGrnLocators.PurchaseBillsTransLocunitprice(), ElementType.Xpath);
					webDB.clearText(PurchaseBillsGrnLocators.PBCostInput, ElementType.Xpath);
					webDB.sendTextToAnElement(PurchaseBillsGrnLocators.PBCostInput, Cost, ElementType.Xpath);
					logs.logging("INFO", "Entered Deviation Cost Is:" + Cost);
					commonFunc.hardWait(3000);
					webDB.pressTabKey();

				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @param GLAccount
	 * @param Type
	 * @author Durai
	 */
	private void verifyAddExpenseType(String GLAccount, String Type, String TdsNo) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBAddLine, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsGrnLocators.PBAddLine, ElementType.Xpath);
			logs.logging("INFO", "Add Button is Clicked");
			flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBAddTypeExp, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseBillsGrnLocators.PBAddTypeExp, ElementType.Xpath);
				webDB.selectDropDownByValue(PurchaseBillsGrnLocators.PBAddTypeExp, Type, ElementType.Xpath);
				logs.logging("INFO", "Type Is Entered:" + Type);
				flag = webDB.waitForElement(PurchaseBillsGrnLocators.PurchaseBillsTransLocGLAccnt(), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsGrnLocators.PurchaseBillsTransLocGLAccnt(), ElementType.Xpath);
					logs.logging("INFO", "Field Is Clicked");
					flag = webDB.waitForElement(PurchaseBillsGrnLocators.GlAccLov, ElementType.Xpath);
					if (flag) {
						commonFunc.searchAndSelectValueInFilters(PurchaseBillsGrnLocators.GlAccLov, GLAccount);
						logs.logging("INFO", "GL Account Is Entered:" + GLAccount);
						commonFunc.checkRefreshCellsDisappear();
						verifyClickingHSNCode(commonFunc.getDataFromTestDataSheet("Hsn_code"));
						verifyChangeCostForDeviation(webDB.getDataFromProperties("DirectGrnGenUD"));
						commonFunc.checkRefreshCellsDisappear();
						verifyAddTDSNumber(TdsNo);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 */
	public void verifySaveLine() {
		flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBAddSave, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsGrnLocators.PBAddSave, ElementType.Xpath);
			logs.logging("INFO", "Line is Saved");
			commonFunc.hardWait(3000);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 */
	private void verifyClickingAdjustmentTab(String AdvanceNo) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBAdjustmentTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsGrnLocators.PBAdjustmentTab, ElementType.Xpath);
			logs.logging("INFO", "Adjustment tab is CLicked");
			flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBAdjustmentLoadBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseBillsGrnLocators.PBAdjustmentLoadBtn, ElementType.Xpath);
				logs.logging("INFO", "Load Button iS Clicked");
				verifyAdjustmentFrame();
				flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBAdjustmentSearchField, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsGrnLocators.PBAdjustmentSearchField, ElementType.Xpath);
					webDB.sendTextToAnElement(PurchaseBillsGrnLocators.PBAdjustmentSearchField, AdvanceNo,
							ElementType.Xpath);
					logs.logging("INFO", "Adjustment Search Field iS Clicked");
					flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBAdjustmentSearchGoBtn, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseBillsGrnLocators.PBAdjustmentSearchGoBtn, ElementType.Xpath);
						logs.logging("INFO", "Adjustment Go Button iS Clicked");
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBAdjustValueEnterField,
								ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseBillsGrnLocators.PBAdjustValueEnterField, ElementType.Xpath);
							logs.logging("INFO", "Value Field Is Clicked");
							webDB.clickAnElement(PurchaseBillsGrnLocators.PBAdjustValueEnterInput, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							webDB.clearText(PurchaseBillsGrnLocators.PBAdjustValueEnterInput, ElementType.Xpath);
							webDB.sendTextToAnElement(PurchaseBillsGrnLocators.PBAdjustValueEnterInput, "5",
									ElementType.Xpath);
							logs.logging("INFO", "Value Is Entered:");
							flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBAdjustDocSelect, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(PurchaseBillsGrnLocators.PBAdjustDocSelect, ElementType.Xpath);
								logs.logging("INFO", "Document Is Selected");
								flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBAdjustmentOk, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(PurchaseBillsGrnLocators.PBAdjustmentOk, ElementType.Xpath);
									logs.logging("INFO", "Ok Button IS Clicked ");
									webDB.switchToDefaultContent();
								}
							}
						}
					}
				}
			}
			assertTrue(flag);
		}

	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 */
	private void verifyAdjustmentFrame() {
		flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBAdjustmentFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(PurchaseBillsGrnLocators.PBAdjustmentFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Adjustment Frame");
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 */
	public void verifyClickingReferenceTabForIgstFlag() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBReferenceTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsGrnLocators.PBReferenceTab, ElementType.Xpath);
			logs.logging("INFO", "Reference Tab Is Clicked");
			flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBIGSTFlag, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseBillsGrnLocators.PBIGSTFlag, ElementType.Xpath);
				logs.logging("INFO", "IGST Flag Is Clicked ");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 */
	private void verifyClickingReferenceTabForRcmFlag() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBReferenceTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsGrnLocators.PBReferenceTab, ElementType.Xpath);
			logs.logging("INFO", "Reference Tab Is Clicked");
			flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBRCMFlag, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseBillsGrnLocators.PBRCMFlag, ElementType.Xpath);
				logs.logging("INFO", "RCM Flag Is Clicked ");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @param ITCType
	 * @param ITCSection
	 * @param ITCSubSection
	 * @author Durai
	 */
	private void verifySelectTypeForIneligible(String ITCType, String ITCSection, String ITCSubSection) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBItcSelectField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsGrnLocators.PBItcSelectField, ElementType.Xpath);
			logs.logging("INFO", "ITC Type Field Is Clicked");
			flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBItcSelect, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownByValue(PurchaseBillsGrnLocators.PBItcSelect, ITCType, ElementType.Xpath);
				logs.logging("INFO", "Type Is Selected:" + ITCType);
				flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBItcSectionField, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsGrnLocators.PBItcSectionField, ElementType.Xpath);
					logs.logging("INFO", "ITC Section Field Is Clicked");
					flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBItcSectionSelect, ElementType.Xpath);
					if (flag) {
						webDB.selectDropDownByValue(PurchaseBillsGrnLocators.PBItcSectionSelect, ITCSection,
								ElementType.Xpath);
						logs.logging("INFO", "Section Is Selected:" + ITCSection);
						flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBItcSubSectionField, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseBillsGrnLocators.PBItcSubSectionField, ElementType.Xpath);
							logs.logging("INFO", "ITC Section Field Is Clicked");
							flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBItcSubSectionSelect,
									ElementType.Xpath);
							if (flag) {
								webDB.selectDropDownByValue(PurchaseBillsGrnLocators.PBItcSubSectionSelect,
										ITCSubSection, ElementType.Xpath);
								logs.logging("INFO", "Section Is Selected:" + ITCSubSection);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * This Method Used To Verify Purchase Bill Header
	 * 
	 * @author Durai
	 */
	public void verifyClickingHSNCode(String HSN) {
		flag = webDB.waitForElement(PurchaseBillsGrnLocators.PurchaseBillsTransLocHsnCode(), ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			webDB.clickAnElement(PurchaseBillsGrnLocators.PurchaseBillsTransLocHsnCode(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(PurchaseBillsGrnLocators.ExpenseHsnLovBtn, HSN);
			logs.logging("INFO", "HSN code IS Enterd:" + HSN);
			webDB.pressTabKey();
			commonFunc.hardWait(2000);
		}
	}

	/**
	 * This Method Used To Verify Purchase Bill Header
	 * 
	 * @author Durai
	 */
	public void validateHeaderDetails() {

		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderLocation")),
				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("PBHeaderLocation")));
		logs.logging("INFO", "Location Is Matched For GRN and PB ");
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderUnit")),
				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("PBHeaderUnit")));
		logs.logging("INFO", "Unit Is Matched For GRN and PB ");
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderSupplierID")),
				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("PBHeaderSupplierID")));
		logs.logging("INFO", "Supplier ID Is Matched For GRN and PB ");
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderDCNO")),
				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("PBHeaderDCNO")));
		logs.logging("INFO", "DC No Is Matched For GRN and PB ");
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderDCDate")),
				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("PBHeaderDCDate")));
		logs.logging("INFO", "DC Date Is Matched For GRN and PB ");
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderGrnDate")),
				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("PBHeaderGrnDate")));
		logs.logging("INFO", "GRN Date Is Matched For GRN and PB ");
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderGrnNo")),
				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("PBHeaderGrnNo")));
		logs.logging("INFO", "GRN No Is Matched For GRN and PB ");
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderGrnNo")),
				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("PBHeaderGrnNo")));
		logs.logging("INFO", "GRN Value Is Matched For GRN and PB ");
	}

	/**
	 * This Method Used To Verify Purchase Bill Header
	 * 
	 * @author Durai
	 */
	public void validatePurchaseBillsPendingGRNDetails() {
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderUnit")),
				dbCommonFunc.PBPendingGRNQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("PendingGRNHeaderUnit")));
		logs.logging("INFO", "Pending Unit Is Matched For GRN and PendingPB ");
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderLocation")),
				dbCommonFunc.PBPendingGRNQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("PendingGRNHeaderLocation")));
		logs.logging("INFO", "Pending Location Is Matched For GRN and PendingPB ");
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderSupplierID")),
				dbCommonFunc.PBPendingGRNQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("PendingGRNSupplierID")));
		logs.logging("INFO", "Pending Supplier ID Is Matched For GRN and PendingPB ");
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderCurrency")),
				dbCommonFunc.PBPendingGRNQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("PendingGRNCurrency")));
		logs.logging("INFO", "Pending Currency Is Matched For GRN and PendingPB ");
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderDocNo")),
				dbCommonFunc.PBPendingGRNQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("PendingGRNDocNo")));
		logs.logging("INFO", "Pending GRN Document No Is Matched For GRN and PendingPB ");
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderDocDate")),
				dbCommonFunc.PBPendingGRNQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("PendingGRNDocDate")));
		logs.logging("INFO", "Pending GRN Document Date Is Matched For GRN and PendingPB ");
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("PGGRNPrfx")),
				dbCommonFunc.PBPendingGRNQuery(VariableHandler.getGrnDocNumber(),
						commonFunc.getDataFromTestDataSheet("PendingGRNPrfx")));
		logs.logging("INFO", "Pending GRN Prefix Is Matched For GRN and PendingPB ");

	}

	/**
	 * This Method Used To Verify Purchase Bill Header
	 * 
	 * @author Durai
	 */
	public void validatePurchaseBillsPendingGRNItemDetails(String SheetName) {
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < GRNMultipleline.length; i++) {
			String SeqNumber = Integer.toString(i - 21);
			String ItemId = GRNMultipleline[i][1];
			String ItemDesc = GRNMultipleline[i][2];
			String QTY = GRNMultipleline[i][5];
			String COST = GRNMultipleline[i][6];
			String UOM = GRNMultipleline[i][3];
			String LINEAMNT = GRNMultipleline[i][18];
			String DISCAMNT = GRNMultipleline[i][17];
			if (!ItemId.equals("")) {
				verifyAssertionBothValues(dbCommonFunc.PBPendingGRNLineQuery(VariableHandler.getGrnDocNumber(),
						SeqNumber, commonFunc.getDataFromTestDataSheet("PendingGRNItemID")), ItemId);
				logs.logging("INFO", "GRN ItemID IS Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunc.PBPendingGRNLineQuery(VariableHandler.getGrnDocNumber(),
						SeqNumber, commonFunc.getDataFromTestDataSheet("PendingGRNItemDesc")), ItemDesc);
				logs.logging("INFO", "GRN Item Description IS Matched With DB Values For This Item " + ItemDesc);
				verifyAssertionBothValues(dbCommonFunc.PBPendingGRNLineQuery(VariableHandler.getGrnDocNumber(),
						SeqNumber, commonFunc.getDataFromTestDataSheet("PendingGRNQty")), QTY);
				logs.logging("INFO", "GRN Quantity IS Matched With DB Values For This Item " + QTY);
				verifyAssertionBothValues(dbCommonFunc.PBPendingGRNLineQuery(VariableHandler.getGrnDocNumber(),
						SeqNumber, commonFunc.getDataFromTestDataSheet("PendingGRNCost")), COST);
				logs.logging("INFO", "GRN Item Cost IS Matched With DB Values For This Item " + COST);
				verifyAssertionBothValues(dbCommonFunc.PBPendingGRNLineQuery(VariableHandler.getGrnDocNumber(),
						SeqNumber, commonFunc.getDataFromTestDataSheet("PendingGRNUOM")), UOM);
				logs.logging("INFO", "GRN UOM IS Matched With DB Values For This Item " + UOM);
//				verifyAssertionBothValues(dbCommonFunc.PBPendingGRNLineQuery(VariableHandler.getGrnDocNumber(),
//						SeqNumber, commonFunc.getDataFromTestDataSheet("PendingGRNLineAmnt")), LINEAMNT);
//				logs.logging("INFO", "GRN Line Amount IS Matched With DB Values For This Item " + LINEAMNT);
				verifyAssertionBothValues(dbCommonFunc.PBPendingGRNLineQuery(VariableHandler.getGrnDocNumber(),
						SeqNumber, commonFunc.getDataFromTestDataSheet("PendingGRNDiscAmnt")), DISCAMNT);
				logs.logging("INFO", "GRN Discount Amount IS Matched With DB Values For This Item " + DISCAMNT);
			}
		}
	}

	/**
	 * This Method Used To Verify Purchase Bill Header
	 * 
	 * @author Durai
	 */
	public void validatePurchaseBillsPendingGRNItemDetailsIGST(String SheetName) {
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < GRNMultipleline.length; i++) {
			String SeqNumber = Integer.toString(i - 21);
			String ItemId = GRNMultipleline[i][1];
			String ItemDesc = GRNMultipleline[i][2];
			String QTY = GRNMultipleline[i][5];
			String COST = GRNMultipleline[i][6];
			String UOM = GRNMultipleline[i][3];
			String LINEAMNT = GRNMultipleline[i][18];
			String DISCAMNT = GRNMultipleline[i][17];
			if (!ItemId.equals("")) {
				verifyAssertionBothValues(dbCommonFunc.PBPendingGRNLineQuery(VariableHandler.getGrnDocNumber(),
						SeqNumber, commonFunc.getDataFromTestDataSheet("PendingGRNItemID")), ItemId);
				logs.logging("INFO", "GRN ItemID IS Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunc.PBPendingGRNLineQuery(VariableHandler.getGrnDocNumber(),
						SeqNumber, commonFunc.getDataFromTestDataSheet("PendingGRNItemDesc")), ItemDesc);
				logs.logging("INFO", "GRN Item Description IS Matched With DB Values For This Item " + ItemDesc);
				verifyAssertionBothValues(dbCommonFunc.PBPendingGRNLineQuery(VariableHandler.getGrnDocNumber(),
						SeqNumber, commonFunc.getDataFromTestDataSheet("PendingGRNQty")), QTY);
				logs.logging("INFO", "GRN Quantity IS Matched With DB Values For This Item " + QTY);
				verifyAssertionBothValues(dbCommonFunc.PBPendingGRNLineQuery(VariableHandler.getGrnDocNumber(),
						SeqNumber, commonFunc.getDataFromTestDataSheet("PendingGRNCost")), COST);
				logs.logging("INFO", "GRN Item Cost IS Matched With DB Values For This Item " + COST);
				verifyAssertionBothValues(dbCommonFunc.PBPendingGRNLineQuery(VariableHandler.getGrnDocNumber(),
						SeqNumber, commonFunc.getDataFromTestDataSheet("PendingGRNUOM")), UOM);
				logs.logging("INFO", "GRN UOM IS Matched With DB Values For This Item " + UOM);
//				verifyAssertionBothValues(dbCommonFunc.PBPendingGRNLineQuery(VariableHandler.getGrnDocNumber(),
//						SeqNumber, commonFunc.getDataFromTestDataSheet("PendingGRNLineAmnt")), LINEAMNT);
//				logs.logging("INFO", "GRN Line Amount IS Matched With DB Values For This Item " + LINEAMNT);
				verifyAssertionBothValues(dbCommonFunc.PBPendingGRNLineQuery(VariableHandler.getGrnDocNumber(),
						SeqNumber, commonFunc.getDataFromTestDataSheet("PendingGRNDiscAmnt")), DISCAMNT);
				logs.logging("INFO", "GRN Discount Amount IS Matched With DB Values For This Item " + DISCAMNT);
			}
		}
	}

	/**
	 * This Method Used To Verify Purchase Bill Lines
	 * 
	 * @author Durai
	 */
	public void validatePurchaseBillsLineDetails(String SheetName) {
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < GRNMultipleline.length; i++) {
			String SeqNumber = Integer.toString(i - 21);
			String ItemId = GRNMultipleline[i][1];
			String ItemDesc = GRNMultipleline[i][2];
			String QTY = GRNMultipleline[i][5];
			String COST = GRNMultipleline[i][6];
			String UOM = GRNMultipleline[i][3];
			String LINEAMNT = GRNMultipleline[i][18];
			String DISCAMNT = GRNMultipleline[i][17];
			String TradeDiscountAmount = GRNMultipleline[i][12];
			String SpecialDiscountAmount = GRNMultipleline[i][14];
			String CashDiscountAmount = GRNMultipleline[i][16];
			String TradeAssessbleValue = GRNMultipleline[i][11];
			String SpecialAssessbleValue = GRNMultipleline[i][13];
			String CashAssessbleValue = GRNMultipleline[i][15];
			String TaxAmountCGST = GRNMultipleline[i][21];
			String TaxAmountSGST = GRNMultipleline[i][22];
			String TaxAmountIGST = GRNMultipleline[i][23];
			String TaxAmountUTGST = GRNMultipleline[i][24];
			String TaxAmountCESS = GRNMultipleline[i][26];
			if (!ItemId.equals("")) {
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
						commonFunc.getDataFromTestDataSheet("PBLineGRNItemID")), ItemId);
				logs.logging("INFO", "GRN ItemID IS Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
						commonFunc.getDataFromTestDataSheet("PBLineGRNItemDesc")), ItemDesc);
				logs.logging("INFO", "GRN Item Description IS Matched With DB Values For This Item " + ItemDesc);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
						commonFunc.getDataFromTestDataSheet("PBLineGRNIInvQty")), QTY);
				logs.logging("INFO", "GRN Item Quantity IS Matched With DB Values For This Item " + QTY);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
						commonFunc.getDataFromTestDataSheet("PBLineGRNIInvCost")), COST);
				logs.logging("INFO", "GRN Item Cost IS Matched With DB Values For This Item " + COST);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
						commonFunc.getDataFromTestDataSheet("PBLineGRNLineAmnt")), LINEAMNT);
				logs.logging("INFO", "GRN Item Line Amount IS Matched With DB Values For This Item " + LINEAMNT);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
						commonFunc.getDataFromTestDataSheet("PBLineGRNDisAmnt")), DISCAMNT);
				logs.logging("INFO", "GRN Item Discount Amount IS Matched With DB Values For This Item " + DISCAMNT);

				verifyAssertionBothValues(
						dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
								commonFunc.getDataFromTestDataSheet("PurchaseBillLineTradeDisAmnt")),
						TradeDiscountAmount);
				logs.logging("INFO", "GRN Item Trade Discount Amount IS Matched With DB Values For This Item "
						+ TradeDiscountAmount);
				verifyAssertionBothValues(
						dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
								commonFunc.getDataFromTestDataSheet("PurchaseBillLineSpecialDisAmnt")),
						SpecialDiscountAmount);
				logs.logging("INFO", "GRN Item Special Discount Amount IS Matched With DB Values For This Item "
						+ SpecialDiscountAmount);
				verifyAssertionBothValues(
						dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
								commonFunc.getDataFromTestDataSheet("PurchaseBillLineCashDisAmnt")),
						CashDiscountAmount);
				logs.logging("INFO",
						"GRN Item Cash Discount Amount IS Matched With DB Values For This Item " + CashDiscountAmount);
				verifyAssertionBothValues(
						dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
								commonFunc.getDataFromTestDataSheet("PurchaseBillLineTradeAssblVal")),
						TradeAssessbleValue);
				logs.logging("INFO", "GRN Item Trade Assessble Value IS Matched With DB Values For This Item "
						+ TradeAssessbleValue);
				verifyAssertionBothValues(
						dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
								commonFunc.getDataFromTestDataSheet("PurchaseBillLineSpecialAssblVal")),
						SpecialAssessbleValue);
				logs.logging("INFO", "GRN Item Special Assessble Value IS Matched With DB Values For This Item "
						+ SpecialAssessbleValue);
				verifyAssertionBothValues(
						dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
								commonFunc.getDataFromTestDataSheet("PurchaseBillLineCashAssblVal")),
						CashAssessbleValue);
				logs.logging("INFO",
						"GRN Item Cash Assessble Value IS Matched With DB Values For This Item " + CashAssessbleValue);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
						commonFunc.getDataFromTestDataSheet("PurchaseBillLineCGSTAmnt")), TaxAmountCGST);
				logs.logging("INFO",
						"GRN Item Tax Amount CGST IS Matched With DB Values For This Item " + TaxAmountCGST);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
						commonFunc.getDataFromTestDataSheet("PurchaseBillLineSGSTAmnt")), TaxAmountSGST);
				logs.logging("INFO",
						"GRN Item Tax Amount SGST IS Matched With DB Values For This Item " + TaxAmountSGST);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
						commonFunc.getDataFromTestDataSheet("PurchaseBillLineIGSTAmnt")), TaxAmountIGST);
				logs.logging("INFO",
						"GRN Item Tax Amount IGST IS Matched With DB Values For This Item " + TaxAmountIGST);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
						commonFunc.getDataFromTestDataSheet("PurchaseBillLineUTGSTAmnt")), TaxAmountUTGST);
				logs.logging("INFO",
						"GRN Item Tax Amount UTGST IS Matched With DB Values For This Item " + TaxAmountUTGST);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
						commonFunc.getDataFromTestDataSheet("PurchaseBillLineCESSAmnt")), TaxAmountCESS);
				logs.logging("INFO",
						"GRN Item Tax Amount CESS IS Matched With DB Values For This Item " + TaxAmountCESS);
				verifyAssertionBothValues(
						dbCommonFunc.GRNPBLineQueryHist(VariableHandler.getGrnDocNumber(), SeqNumber,
								commonFunc.getDataFromTestDataSheet("GRNCPC")),
						dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
								commonFunc.getDataFromTestDataSheet("PBCPC")));
				logs.logging("INFO", "CPC Code Is Matched For GRN and PendingPB ");
			}
		}
	}

	/**
	 * This Method Used To Verify Purchase Bill Lines
	 * 
	 * @author Durai
	 */
	public void validatePurchaseBillsLineDetailsAfterDevaition(String SheetName) {
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < GRNMultipleline.length; i++) {
			String SeqNumber = Integer.toString(i - 19);
			String ItemId = GRNMultipleline[i][1];
			String ItemDesc = GRNMultipleline[i][2];
			String QTY = GRNMultipleline[i][5];
			String COST = GRNMultipleline[i][6];
			String UOM = GRNMultipleline[i][3];
			String LINEAMNT = GRNMultipleline[i][18];
			String DISCAMNT = GRNMultipleline[i][17];
			String TradeDiscountAmount = GRNMultipleline[i][12];
			String SpecialDiscountAmount = GRNMultipleline[i][14];
			String CashDiscountAmount = GRNMultipleline[i][16];
			String TradeAssessbleValue = GRNMultipleline[i][11];
			String SpecialAssessbleValue = GRNMultipleline[i][13];
			String CashAssessbleValue = GRNMultipleline[i][15];
			String TaxAmountCGST = GRNMultipleline[i][21];
			String TaxAmountSGST = GRNMultipleline[i][22];
			String TaxAmountIGST = GRNMultipleline[i][23];
			String TaxAmountUTGST = GRNMultipleline[i][24];
			String TaxAmountCESS = GRNMultipleline[i][26];
			if (!ItemId.equals("")) {
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
						commonFunc.getDataFromTestDataSheet("PBLineGRNItemID")), ItemId);
				logs.logging("INFO", "GRN ItemID IS Matched With DB Values For This Item " + ItemId);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
						commonFunc.getDataFromTestDataSheet("PBLineGRNItemDesc")), ItemDesc);
				logs.logging("INFO", "GRN Item Description IS Matched With DB Values For This Item " + ItemDesc);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
						commonFunc.getDataFromTestDataSheet("PBLineGRNIInvQty")), QTY);
				logs.logging("INFO", "GRN Item Quantity IS Matched With DB Values For This Item " + QTY);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
						commonFunc.getDataFromTestDataSheet("PBLineGRNIInvCost")), COST);
				logs.logging("INFO", "GRN Item Cost IS Matched With DB Values For This Item " + COST);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
						commonFunc.getDataFromTestDataSheet("PBLineGRNDisAmnt")), DISCAMNT);
				logs.logging("INFO", "GRN Item Discount Amount IS Matched With DB Values For This Item " + DISCAMNT);

				verifyAssertionBothValues(
						dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
								commonFunc.getDataFromTestDataSheet("PurchaseBillLineTradeDisAmnt")),
						TradeDiscountAmount);
				logs.logging("INFO", "GRN Item Trade Discount Amount IS Matched With DB Values For This Item "
						+ TradeDiscountAmount);
				verifyAssertionBothValues(
						dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
								commonFunc.getDataFromTestDataSheet("PurchaseBillLineSpecialDisAmnt")),
						SpecialDiscountAmount);
				logs.logging("INFO", "GRN Item Special Discount Amount IS Matched With DB Values For This Item "
						+ SpecialDiscountAmount);
				verifyAssertionBothValues(
						dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
								commonFunc.getDataFromTestDataSheet("PurchaseBillLineCashDisAmnt")),
						CashDiscountAmount);
				logs.logging("INFO",
						"GRN Item Cash Discount Amount IS Matched With DB Values For This Item " + CashDiscountAmount);
				verifyAssertionBothValues(
						dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
								commonFunc.getDataFromTestDataSheet("PurchaseBillLineTradeAssblVal")),
						TradeAssessbleValue);
				logs.logging("INFO", "GRN Item Trade Assessble Value IS Matched With DB Values For This Item "
						+ TradeAssessbleValue);
				verifyAssertionBothValues(
						dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
								commonFunc.getDataFromTestDataSheet("PurchaseBillLineSpecialAssblVal")),
						SpecialAssessbleValue);
				logs.logging("INFO", "GRN Item Special Assessble Value IS Matched With DB Values For This Item "
						+ SpecialAssessbleValue);
				verifyAssertionBothValues(
						dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
								commonFunc.getDataFromTestDataSheet("PurchaseBillLineCashAssblVal")),
						CashAssessbleValue);
				logs.logging("INFO",
						"GRN Item Cash Assessble Value IS Matched With DB Values For This Item " + CashAssessbleValue);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
						commonFunc.getDataFromTestDataSheet("PurchaseBillLineCGSTAmnt")), TaxAmountCGST);
				logs.logging("INFO",
						"GRN Item Tax Amount CGST IS Matched With DB Values For This Item " + TaxAmountCGST);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
						commonFunc.getDataFromTestDataSheet("PurchaseBillLineSGSTAmnt")), TaxAmountSGST);
				logs.logging("INFO",
						"GRN Item Tax Amount SGST IS Matched With DB Values For This Item " + TaxAmountSGST);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
						commonFunc.getDataFromTestDataSheet("PurchaseBillLineIGSTAmnt")), TaxAmountIGST);
				logs.logging("INFO",
						"GRN Item Tax Amount IGST IS Matched With DB Values For This Item " + TaxAmountIGST);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
						commonFunc.getDataFromTestDataSheet("PurchaseBillLineUTGSTAmnt")), TaxAmountUTGST);
				logs.logging("INFO",
						"GRN Item Tax Amount UTGST IS Matched With DB Values For This Item " + TaxAmountUTGST);
				verifyAssertionBothValues(dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
						commonFunc.getDataFromTestDataSheet("PurchaseBillLineCESSAmnt")), TaxAmountCESS);
				logs.logging("INFO",
						"GRN Item Tax Amount CESS IS Matched With DB Values For This Item " + TaxAmountCESS);
				verifyAssertionBothValues(
						dbCommonFunc.GRNPBLineQueryHist(VariableHandler.getGrnDocNumber(), SeqNumber,
								commonFunc.getDataFromTestDataSheet("GRNCPC")),
						dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
								commonFunc.getDataFromTestDataSheet("PBCPC")));
				logs.logging("INFO", "CPC Code Is Matched For GRN and PendingPB ");
			}
		}
	}

	/**
	 * Check Assertion Both Values
	 * 
	 * @author Deepan-GWL
	 * @param Actual
	 * @param Expected
	 */
	public void verifyAssertionBothValues(String Actual, String Expected) {
		logs.logging("INFO", "Actual DataBase Value Is: " + Actual);
		logs.logging("INFO", "Expected Value Is: " + Expected);
		flag = Actual.equals(Expected);
		if (flag) {
			logs.logging("INFO", "Verified Actual DataBase Value 8: " + Actual + " And Expected Values : " + Expected);
		} else {
			logs.logging("INFO",
					"Mismatch With Actual DataBase Value : " + Actual + " And Expected Values " + Expected);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Percentage Write In
	 * Excel UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */

	public void verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(String SheetName) {
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		logs.logging("INFO", "Unit Cost For This Item");
		for (int i = 22; i < GRNMultipleline.length; i++) {
			String ItemId = GRNMultipleline[i][1];
			String SeqNumber = Integer.toString(i - 21);
			if (!ItemId.equals("")) {
				String Cost = dbCommonFunc.PurchaseBillLineQuery(VariableHandler.getPbNumber(), SeqNumber,
						commonFunc.getDataFromTestDataSheet("PBLineGRNIInvCost"));
				System.out.println(Cost);
				commonFunc.hardWait(2000);
				BigDecimal value = new BigDecimal(Cost).add(new BigDecimal("100"));
				logs.logging("INFO", "Rate Contract Price For This Item " + ItemId + "Is : " + Cost);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 6, value.toString());
			}
		}

	}

	/**
	 * This Methods Is Used for Approve the Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	private void verifyAddTheItemBasedOnTypeForAgainstGRN(String SheetName) {

		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < GRNMultipleline.length; i++) {
			System.out.println(GRNMultipleline.length);
			String Type = GRNMultipleline[i][71];
			String Cost = GRNMultipleline[i][6];
			String GLAccount = GRNMultipleline[i][70];
			String Item = GRNMultipleline[i][1];
			String Qty = GRNMultipleline[i][5];
			String HSN = GRNMultipleline[i][68];
			String TrdDisc = GRNMultipleline[i][8];
			String SpclDisc = GRNMultipleline[i][9];
			String CashDisc = GRNMultipleline[i][10];
			String TdsNo = GRNMultipleline[i][72];
			String TaxAmountCGST = GRNMultipleline[i][21];
			String TaxAmountSGST = GRNMultipleline[i][22];
			String TaxAmountIGST = GRNMultipleline[i][23];
//			String GSTSupply = GRNMultipleline[i][74];
			String SeqNumber = GRNMultipleline[i][0];
			String PBLineAmount = GRNMultipleline[i][18];
			String PBDiscAmount = GRNMultipleline[i][17];
			if (!Type.equals("")) {
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBAddLine, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsGrnLocators.PBAddLine, ElementType.Xpath);
					logs.logging("INFO", "Add Button is Clicked");
					flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBAddTypeExp, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseBillsGrnLocators.PBAddTypeExp, ElementType.Xpath);
						webDB.selectDropDownByText(PurchaseBillsGrnLocators.PBAddTypeExp, Type, ElementType.Xpath);
						logs.logging("INFO", "Field Is Clicked");
						if (flag) {
							if (Type.equals(commonFunc.getDataFromTestDataSheet("Expensetype"))) {
								verifyToCreateExpenseType(GLAccount, Cost, HSN);
							} else if (Type.equals(commonFunc.getDataFromTestDataSheet("ItemType"))) {
								verifyToCreatetheItemTypeDocument(Item, Cost, Qty, HSN, GLAccount, TrdDisc, SpclDisc,
										CashDisc, TdsNo, TaxAmountCGST, TaxAmountSGST, TaxAmountIGST, PBLineAmount,
										PBDiscAmount);
							}
						}
					}
				}
			}
		}

		assertTrue(flag);
	}

	/**
	 * This Methods To Create Expense Type Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToCreateExpenseType(String GLAccount, String Cost, String HSN) {

		flag = webDB.waitForElement(PurchaseBillsGrnLocators.GlAccDescField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsGrnLocators.GlAccDescField, ElementType.Xpath);
			logs.logging("INFO", "Field Is Clicked");
			flag = webDB.waitForElement(PurchaseBillsGrnLocators.GlAccLov, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(PurchaseBillsGrnLocators.GlAccLov, GLAccount);
				logs.logging("INFO", "GL Account Is Entered:" + GLAccount);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBCostField, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsGrnLocators.PBCostField, ElementType.Xpath);
					webDB.clearText(PurchaseBillsGrnLocators.PBCostInput, ElementType.Xpath);
					webDB.sendTextToAnElement(PurchaseBillsGrnLocators.PBCostInput, Cost, ElementType.Xpath);
					logs.logging("INFO", "Entered Cost Is:" + Cost);
					flag = webDB.waitForElement(PurchaseBillsGrnLocators.ExpenseHsnField, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseBillsGrnLocators.ExpenseHsnField, ElementType.Xpath);
						flag = webDB.waitForElement(PurchaseBillsGrnLocators.ExpenseHsnField, ElementType.Xpath);
						if (flag) {
							commonFunc.searchAndSelectValueInFilters(PurchaseBillsGrnLocators.ExpenseHsnField, HSN);
							logs.logging("INFO", "Entered Cost Is:" + HSN);
							commonFunc.checkRefreshCellsDisappear();
							webDB.pressTabKey();
							verifySaveLine();

						}

					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods To Create Item Type Document
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void verifyToCreatetheItemTypeDocument(String Item, String Cost, String Qty, String HSN, String GLAccount,
			String TrdDisc, String SpclDisc, String CashDisc, String TdsNo, String TaxAmountCGST, String TaxAmountSGST,
			String TaxAmountIGST, String PBLineAmount, String PBDiscAmount) {
		webDB.waitForElement(PurchaseBillsGrnLocators.PurchaseBillsTransLocGstItem(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsGrnLocators.PurchaseBillsTransLocGstItem(), ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBItemLov, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(PurchaseBillsGrnLocators.PBItemLov, Item);
				logs.logging("INFO", "Item Is Selected SuccessFully:" + Item);
				flag = webDB.waitForElement(PurchaseBillsGrnLocators.PurchaseBillsTransLocGLAccnt(), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsGrnLocators.PurchaseBillsTransLocGLAccnt(), ElementType.Xpath);
					logs.logging("INFO", "Field Is Clicked");
					flag = webDB.waitForElement(PurchaseBillsGrnLocators.GlAccLov, ElementType.Xpath);
					if (flag) {
						commonFunc.searchAndSelectValueInFilters(PurchaseBillsGrnLocators.GlAccLov, GLAccount);
						logs.logging("INFO", "GL Account Is Entered:" + GLAccount);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(PurchaseBillsGrnLocators.PurchaseBillsTransLocGstQty(), ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseBillsGrnLocators.PurchaseBillsTransLocGstQty(), ElementType.Xpath);
							webDB.clearText(PurchaseBillsGrnLocators.PBQtyInput, ElementType.Xpath);
							webDB.sendTextToAnElement(PurchaseBillsGrnLocators.PBQtyInput, Qty, ElementType.Xpath);
							logs.logging("INFO", "Entered Qty Is:" + Qty);
							flag = webDB.waitForElement(PurchaseBillsGrnLocators.PurchaseBillsTransLocunitprice(), ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(PurchaseBillsGrnLocators.PurchaseBillsTransLocunitprice(), ElementType.Xpath);
								webDB.clearText(PurchaseBillsGrnLocators.PBCostInput, ElementType.Xpath);
								webDB.sendTextToAnElement(PurchaseBillsGrnLocators.PBCostInput, Cost,
										ElementType.Xpath);
								logs.logging("INFO", "Entered Cost Is:" + Cost);
								flag = webDB.waitForElement(PurchaseBillsGrnLocators.PurchaseBillsTransLocHsnCode(),
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(PurchaseBillsGrnLocators.PurchaseBillsTransLocHsnCode(), ElementType.Xpath);
									flag = webDB.waitForElement(PurchaseBillsGrnLocators.ExpenseHsnLovBtn,
											ElementType.Xpath);
									if (flag) {
										commonFunc.searchAndSelectValueInFilters(
												PurchaseBillsGrnLocators.ExpenseHsnLovBtn, HSN);
										logs.logging("INFO", "Entered Cost Is:" + HSN);
										commonFunc.checkRefreshCellsDisappear();
										webDB.pressTabKey();
										verifyAddTDSNumber(TdsNo);
										verifyAddingTradeSpclCashDiscount(TrdDisc, SpclDisc, CashDisc);
										verifySaveLine();
//										VerifyToValidateStockTransferItemTypeValidate(Item, TaxAmountCGST,
//												TaxAmountSGST, TaxAmountIGST, PBLineAmount, PBDiscAmount);
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

	private void verifyAddingTradeSpclCashDiscount(String TrdDisc, String SpclDisc, String CashDisc) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsGrnLocators.PurchaseBillsTransLocGstTrdDis(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsGrnLocators.PurchaseBillsTransLocGstTrdDis(), ElementType.Xpath);
			webDB.clearText(PurchaseBillsGrnLocators.PBTrdDiscPercInput, ElementType.Xpath);
			webDB.sendTextToAnElement(PurchaseBillsGrnLocators.PBTrdDiscPercInput, TrdDisc, ElementType.Xpath);
			logs.logging("INFO", "Trade Discount Percent Is Entered: " + TrdDisc);
			flag = webDB.waitForElement(PurchaseBillsGrnLocators.PurchaseBillsTransLocGstSpclDis(), ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseBillsGrnLocators.PurchaseBillsTransLocGstSpclDis(), ElementType.Xpath);
				webDB.clearText(PurchaseBillsGrnLocators.PBSpclDiscPercInput, ElementType.Xpath);
				webDB.sendTextToAnElement(PurchaseBillsGrnLocators.PBSpclDiscPercInput, SpclDisc, ElementType.Xpath);
				logs.logging("INFO", "Special Discount Percent Is Entered: " + SpclDisc);
				flag = webDB.waitForElement(PurchaseBillsGrnLocators.PurchaseBillsTransLocGstCashDis(), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsGrnLocators.PurchaseBillsTransLocGstCashDis(), ElementType.Xpath);
					webDB.clearText(PurchaseBillsGrnLocators.PBCashDiscPercInput, ElementType.Xpath);
					webDB.sendTextToAnElement(PurchaseBillsGrnLocators.PBCashDiscPercInput, CashDisc,
							ElementType.Xpath);
					logs.logging("INFO", "Cash Discount Percent Is Entered: " + CashDisc);
					webDB.pressTabKey();

				}
			}
		}
	}

	/**
	 * This Method Is Used To Verify The Journal Amount With DataBase
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void verifyJournalAmountWithDB(String SheetName, String DocumentNumber) {
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 1; i < GRNMultipleline.length; i++) {
			String Unit = GRNMultipleline[i][1];
			String GLAccountNumber = GRNMultipleline[i][2];
			String GLAccountDesc = GRNMultipleline[i][3];
			String CPC = GRNMultipleline[i][4];
			String CPCDesc = GRNMultipleline[i][5];
			String Debit = GRNMultipleline[i][8];
			String Credit = GRNMultipleline[i][9];
			if (!Unit.equals("")) {
				String SeqNo = String.valueOf(i);

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunc.ApplicationJournalViewDetails(DocumentNumber, SeqNo,
						commonFunc.getDataFromTestDataSheet("AJDBUnitColumnName")), Unit);
				logs.logging("INFO",
						" In The View Journal Screen Unit Id Is Matched With Database Values Against This Document Number "
								+ DocumentNumber + " Journal Seq Number " + i);

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunc.ApplicationJournalViewDetails(DocumentNumber, SeqNo,
						commonFunc.getDataFromTestDataSheet("AJDBGLAccountColumnName")), GLAccountNumber);
				logs.logging("INFO",
						" In The View Journal Screen GL Account Number Is Matched With Database Values Against This Document Number "
								+ DocumentNumber + " Journal Seq Number " + i);

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunc.ApplicationJournalViewDetails(DocumentNumber, SeqNo,
						commonFunc.getDataFromTestDataSheet("AJDBGLAccountDescColumnName")), GLAccountDesc);
				logs.logging("INFO",
						" In The View Journal Screen GL Account Number Description Is Matched With Database Values Against This Document Number "
								+ DocumentNumber + " Journal Seq Number " + i);

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunc.ApplicationJournalViewDetails(DocumentNumber, SeqNo,
						commonFunc.getDataFromTestDataSheet("AJDBDebitAmountColumnName")), Debit);
				logs.logging("INFO",
						" In The View Journal Screen Debit Amount Is Matched With Database Values Against This Document Number"
								+ DocumentNumber + " Journal Seq Number " + i);

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunc.ApplicationJournalViewDetails(DocumentNumber, SeqNo,
						commonFunc.getDataFromTestDataSheet("AJDBCreditAmountColumnName")), Credit);
				logs.logging("INFO",
						" In The View Journal Screen Credit Amount Is Matched With Database Values Against This Document Number"
								+ DocumentNumber + " Journal Seq Number " + i);
			}
		}
	}

	/**
	 * This Method Used To Verify To Create Journal And Post
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateJournal() {
		flag = webDB.waitForElement(PurchaseBillsGrnLocators.CreateJournal, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseBillsGrnLocators.CreateJournal, ElementType.Xpath);
			logs.logging("INFO", "Create Journal Button Is Displayed");
			webDB.clickAnElement(PurchaseBillsGrnLocators.CreateJournal, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}

		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify To Create Journal And Post
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreatePost() {
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(5000);
		flag = webDB.waitForElement(PurchaseBillsGrnLocators.PostButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Post Button Is Displayed");
			webDB.clickAnElement(PurchaseBillsGrnLocators.PostButton, ElementType.Xpath);
			flag = (webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 10)
					|| webDB.waitForElement(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath));
		}
	}

	/**
	 * This Method Is Used To Verify Unit Cost and Discount Percentage Write In
	 * Excel UOM(General)
	 * 
	 * @author PremKumar.K-RM ITS
	 * 
	 */
	public void VerifyToValidateStockTransferItemTypeValidate(String ITEM_ID, String TaxAmountCGST,
			String TaxAmountSGST, String TaxAmountIGST, String PBLineAmount, String PBDiscAmount) {
		verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
				commonFunc.getDataFromTestDataSheet("PurchaseBillCGSTAmount")), TaxAmountCGST);
		logs.logging("INFO", "CGST Matched With DB Values For This Item " + ITEM_ID);
		verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
				commonFunc.getDataFromTestDataSheet("PurchaseBillSGSTAmount")), TaxAmountSGST);
		logs.logging("INFO", "SGST Matched With DB Values For This Item " + ITEM_ID);
		verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
				commonFunc.getDataFromTestDataSheet("PurchaseBillIGSTAmount")), TaxAmountIGST);
		logs.logging("INFO", "IGST Matched With DB Values For This Item " + ITEM_ID);
		verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
				commonFunc.getDataFromTestDataSheet("PBLineAmount")), PBLineAmount);
		logs.logging("INFO", "Line Amount Matched With DB Values For This Item " + ITEM_ID);
		verifyAssertionBothValues(dbCommonFunc.PBLineLevelQuery(VariableHandler.getPbNumber(), ITEM_ID,
				commonFunc.getDataFromTestDataSheet("PBDiscAmount")), PBDiscAmount);
		logs.logging("INFO", "Discount Amount Matched With DB Values For This Item " + ITEM_ID);

	}

	/**
	 * verify Add TDS Number
	 * 
	 * @author Deepan
	 */
	public void verifyAddTDSNumberTest(String TdsNo) {
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
				commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.TDSLovButton, TdsNo);
				verifySaveLine();
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
	public void verifyPendingPurchaseBillTest(String DocNo) {
		flag = webDB.waitForElement(PurchaseBillsGrnLocators.PendingPurchaseBill, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Purchase Bill Is Displayed");
			webDB.clickAnElement(PurchaseBillsGrnLocators.PendingPurchaseBill, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsGrnLocators.PendingGrn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Grn Is Displayed");
				webDB.clickAnElement(PurchaseBillsGrnLocators.PendingGrn, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseBillsGrnLocators.Iframe, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Iframe Is Displayed");
					webDB.switchToFrame(PurchaseBillsGrnLocators.Iframe, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(PurchaseBillsGrnLocators.GrnNo, DocNo);
					flag = webDB.waitForElement(PurchaseBillsGrnLocators.Iframe, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(PurchaseBillsGrnLocators.Iframe, ElementType.Xpath);
						flag = webDB.waitForElement(PurchaseBillsGrnLocators.SearchButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Switched To Iframe");
							webDB.clickAnElement(PurchaseBillsGrnLocators.SearchButton, ElementType.Xpath);
							validatePurchaseBillsPendingGRNDetails();
							validatePurchaseBillsPendingGRNItemDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
							flag = webDB.waitForElement(PurchaseBillsGrnLocators.CheckBox(DocNo), ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "CheckBox IS Displayed");
								commonFunc.hardWait(3000);
								webDB.clickAnElement(PurchaseBillsGrnLocators.CheckBox(DocNo), ElementType.Xpath);
								flag = webDB.waitForElement(PurchaseBillsGrnLocators.CreateDoc, ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Create Documents Is Displayed");
									commonFunc.hardWait(5000);
									webDB.clickAnElement(PurchaseBillsGrnLocators.CreateDoc, ElementType.Xpath);
									flag = webDB.waitForElement(PurchaseBillsGrnLocators.OkButton, ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Okay Button Is Displayed");
										webDB.clickAnElement(PurchaseBillsGrnLocators.OkButton, ElementType.Xpath);
										webDB.switchToDefaultContent();
//										flag = webDB.waitForElement(PurchaseBillsGrnLocators.LocationId,
//												ElementType.Xpath);
//										storePurchaseVoucherNumber();
////										verifyAddTDSNumber(TdsNo);
//										if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon,
//												ElementType.Xpath)) {
//											webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
//													ElementType.Xpath);
									}
								}
							}
						}
					}
				}
			}
		}
//		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Pending Subcontract Against Purchase Bill Header
	 * 
	 * @author Vimal - RMITS
	 */
	public void pendingSubcontractAgainstvalidateHeaderDetails() {

		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getScoGrnNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderLocation")),
				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("PBHeaderLocation")));
		logs.logging("INFO", "Location Is Matched For GRN and PB ");
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getScoGrnNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderUnit")),
				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("PBHeaderUnit")));
		logs.logging("INFO", "Unit Is Matched For GRN and PB ");
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getScoGrnNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderSupplierID")),
				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("PBHeaderSupplierID")));
		logs.logging("INFO", "Supplier ID Is Matched For GRN and PB ");
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getScoGrnNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderDCNO")),
				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("PBHeaderDCNO")));
		logs.logging("INFO", "DC No Is Matched For GRN and PB ");
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getScoGrnNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderDCDate")),
				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("PBHeaderDCDate")));
		logs.logging("INFO", "DC Date Is Matched For GRN and PB ");
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getScoGrnNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderGrnDate")),
				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("PBHeaderGrnDate")));
		logs.logging("INFO", "GRN Date Is Matched For GRN and PB ");
		verifyAssertionBothValues(
				dbCommonFunc.GRNPBHeaderQuery(VariableHandler.getScoGrnNumber(),
						commonFunc.getDataFromTestDataSheet("GRNHeaderGrnNo")),
				dbCommonFunc.PBQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("PBHeaderGrnNo")));
		logs.logging("INFO", "GRN No Is Matched For GRN and PB ");
	}

	/**
	 * This Method Is Used To Add The Bill Number And Pay Amount In The Bank Payment
	 * View Details
	 * 
	 * @author Harikrishna - RMITS
	 * @param BillNumber
	 * @param PayAmount
	 */
	public void verifyCreatingPurchaseBillwithAdvanceInterstate() throws IOException {
		verifyRegisteredSupplierwithInterstateGSTAgainstGRNWithoutDeviation();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				System.getProperty("DuraiUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		verifyCreatePaymentFromPurchaseBill(commonFunc.getDataFromTestDataSheet("PaymentType"),
				commonFunc.getDataFromTestDataSheet("BankPayMode"), commonFunc.getDataFromTestDataSheet("BankName"));
		BPFunc.getBankPaymentOrReceiptDocumentNumber();
//			BPFunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"));
//			VerifyBankPaymentHeaderDetailsWithUI(commonFunc.getDataFromTestDataSheet("BankName"),
//					commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
//			BPFunc.verifyBankPaymentDetailsWithDB(commonFunc.getDataFromTestDataSheet("BankName"),
//					commonFunc.getDataFromTestDataSheet("BankPrefix"),
//					commonFunc.getDataFromTestDataSheet("BankTransCurcy"));
		clickLineWiseViewButtonForAdvance();
		BPFunc.clickAddButtonInViewDetails();
		addBillNumberAndPayAmountForAdvance();
		BPFunc.clickViewDetailsSaveButton();
		BPFunc.clickBankViewDetailsCloseButton();
		BPFunc.sendReferenceNumberInBP(commonFunc.getDataFromTestDataSheet("ReferenceNumber"));
		BPFunc.clickBPHeaderWiseSaveButton();
		BPFunc.clickCreateJournalButton();
		BPFunc.clickPostButton();
		flag = BPFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		if (flag) {
//			BPFunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		}
	}

	/**
	 * This Method Is Used To Add The Bill Number And Pay Amount In The Bank Payment
	 * View Details
	 * 
	 * @author Harikrishna - RMITS
	 * @param BillNumber
	 * @param PayAmount
	 */
	public void verifyCreatingPurchaseBillwithAdvanceImport() throws IOException {
		verifyRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviationForAdvanceInterstate();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				System.getProperty("DuraiUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		verifyCreatePaymentFromPurchaseBill(commonFunc.getDataFromTestDataSheet("PaymentType"),
				commonFunc.getDataFromTestDataSheet("BankPayMode"), commonFunc.getDataFromTestDataSheet("BankName"));
		BPFunc.getBankPaymentOrReceiptDocumentNumber();
//			BPFunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"));
//			VerifyBankPaymentHeaderDetailsWithUI(commonFunc.getDataFromTestDataSheet("BankName"),
//					commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
//			BPFunc.verifyBankPaymentDetailsWithDB(commonFunc.getDataFromTestDataSheet("BankName"),
//					commonFunc.getDataFromTestDataSheet("BankPrefix"),
//					commonFunc.getDataFromTestDataSheet("BankTransCurcy"));
		clickLineWiseViewButtonForAdvance();
		BPFunc.clickAddButtonInViewDetails();
		addBillNumberAndPayAmountForAdvance();
		BPFunc.clickViewDetailsSaveButton();
		BPFunc.clickBankViewDetailsCloseButton();
		BPFunc.sendReferenceNumberInBP(commonFunc.getDataFromTestDataSheet("ReferenceNumber"));
		BPFunc.clickBPHeaderWiseSaveButton();
		BPFunc.clickCreateJournalButton();
		BPFunc.clickPostButton();
		flag = BPFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		if (flag) {
//			BPFunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		}
	}

	/**
	 * This Method Is Used To Add The Bill Number And Pay Amount In The Bank Payment
	 * View Details
	 * 
	 * @author Harikrishna - RMITS
	 * @param BillNumber
	 * @param PayAmount
	 */
	public void verifyCreatingPurchaseBillwithAdvanceInterstateNonResident() throws IOException {
		VerifyNonResidentSupplierimportedwithoutdeviationwithoutGST();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				System.getProperty("DuraiUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		verifyCreatePaymentFromPurchaseBill(commonFunc.getDataFromTestDataSheet("PaymentType"),
				commonFunc.getDataFromTestDataSheet("BankPayMode"), commonFunc.getDataFromTestDataSheet("BankName"));
		BPFunc.getBankPaymentOrReceiptDocumentNumber();
//			BPFunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"));
//			VerifyBankPaymentHeaderDetailsWithUI(commonFunc.getDataFromTestDataSheet("BankName"),
//					commonFunc.getDataFromTestDataSheet("BankPrefix"), Today, Today);
//			BPFunc.verifyBankPaymentDetailsWithDB(commonFunc.getDataFromTestDataSheet("BankName"),
//					commonFunc.getDataFromTestDataSheet("BankPrefix"),
//					commonFunc.getDataFromTestDataSheet("BankTransCurcy"));
		clickLineWiseViewButtonForAdvance();
		BPFunc.clickAddButtonInViewDetails();
		addBillNumberAndPayAmountForAdvance();
		BPFunc.clickViewDetailsSaveButton();
		BPFunc.clickBankViewDetailsCloseButton();
		BPFunc.sendReferenceNumberInBP(commonFunc.getDataFromTestDataSheet("ReferenceNumber"));
		BPFunc.clickBPHeaderWiseSaveButton();
		BPFunc.clickCreateJournalButton();
		BPFunc.clickPostButton();
		flag = BPFunc.forwardForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		if (flag) {
//			BPFunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		}
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void verifyRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviationForAdvanceInterstate()
			throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBillUnRegisteredInterstate();
		commonFunc.validCommonLogin(System.getProperty("DuraiUsername"), System.getProperty("LoginPassword"),
				"DuraiUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"),
				webDB.getDataFromProperties("PurchaseBillGRNIGST"), commonFunc.getDataFromTestDataSheet("PBPrefix"));
		validateHeaderDetails();
		validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRNIGST"));
//		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBillSerIGST"));
		verifySaveLine();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Navigate to Reference Tab
	 * 
	 * @author Vimal - RMITS
	 */
	public void clickingReferenceTabAndValidating() {
		flag = webDB.waitForElement(PurchaseBillsGrnLocators.PB_ReferenceTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsGrnLocators.PB_ReferenceTab, ElementType.Xpath);
			logs.logging("INFO", "Navigate to Reference Tab");
			commonFunc.hardWait(2000);
			storePurchaseBillMatchedAmtAndGRNAmtAndDeviationAmt();
			flag = webDB.waitForElement(PurchaseBillsGrnLocators.PB_InvoiceTab, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseBillsGrnLocators.PB_InvoiceTab, ElementType.Xpath);
				logs.logging("INFO", "Navigate to Invoice Tab");
				commonFunc.hardWait(2000);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Used To store PurchaseBill Proces Tab Process Qty And UnitCost
	 * 
	 * @author Vimal - RMITS
	 */
	public void storePurchaseBillProcesTabProcessQtyAndUnitCostAgainstGrn() {
		commonFunc.hardWait(1000);
		String PBProcQty = webDB.getTextFromElement(PurchaseBillsGrnLocators.PBQty, ElementType.Xpath);
		logs.logging("INFO", "Get PBProcQty Amt Is: " + PBProcQty);
		VariableHandler.setPBProcQty(PBProcQty);
		commonFunc.hardWait(1000);
		String PBUnitcost = webDB.getTextFromElement(PurchaseBillsGrnLocators.PBUnitCost, ElementType.Xpath);
		logs.logging("INFO", "Get PBUnitcost Is: " + PBUnitcost);
		VariableHandler.setPBUnitcost(PBUnitcost);
		commonFunc.hardWait(1000);
	}

	/**
	 * This Methods Used To Get BatchQty, BatchCost and Write In Excel Sheet
	 * 
	 * @author Vimal - RMITS
	 */
	public void getMatchedAmtAndGRNAmtandWriteInExcelSheetForGrn(String Sheetname) {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DirectGrnDetails.xlsx";
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, Sheetname);
		logs.logging("INFO", "Before Process Id ");
		for (int i = 22; i < ItemMultiple.length; i++) {
			String MatchedAmt = ItemMultiple[i][73];
			String GRNAmt = ItemMultiple[i][74];
			String PBUnitcost = ItemMultiple[i][76];
			String PBProcQty = ItemMultiple[i][75];
			if (!MatchedAmt.equals("")) {
				logs.logging("INFO", "Before MatchedAmt  " + MatchedAmt + "Is : " + VariableHandler.getMatchedAmt());
//				excelUtils.writeDataInExcel(Filepath, Sheetname, i, 73, VariableHandler.getMatchedAmt());
				String Matchamount = VariableHandler.getMatchedAmt().replace(",", "");
				System.out.println(Matchamount);
				excelUtils.writeDataInExcel(Filepath, Sheetname, i, 73, Matchamount);
				logs.logging("INFO", "Before GRNAmt  " + GRNAmt + "Is : " + VariableHandler.getGRNAmt());
//				excelUtils.writeDataInExcel(Filepath, Sheetname, i, 74, VariableHandler.getGRNAmt());
				String Grnamount = VariableHandler.getGRNAmt().replace(",", "");
				System.out.println(Grnamount);
				excelUtils.writeDataInExcel(Filepath, Sheetname, i, 74, Grnamount);
				logs.logging("INFO", "Before PBUnitcost  " + PBUnitcost + "Is : " + VariableHandler.getPBUnitcost());
				excelUtils.writeDataInExcel(Filepath, Sheetname, i, 76, VariableHandler.getPBUnitcost());
				logs.logging("INFO", "Before PBProcQty  " + PBProcQty + "Is : " + VariableHandler.getPBProcQty());
				excelUtils.writeDataInExcel(Filepath, Sheetname, i, 75, VariableHandler.getPBProcQty());
			}
		}
	}

	/**
	 * This Method Used To Verify To Create Journal And Verify Deviation Post
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyCreateJournalAndVerifyDeviationAmtAndPost() {
		flag = webDB.waitForElement(PurchaseBillsGrnLocators.CreateJournal, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(PurchaseBillsGrnLocators.CreateJournal, ElementType.Xpath);
			logs.logging("INFO", "Create Journal Button Is Displayed");
			webDB.clickAnElement(PurchaseBillsGrnLocators.CreateJournal, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(5000);
			deviationCalculationCompareExcelSheetWithDataBaseForGrn(
					webDB.getDataFromProperties("PurchaseBillDeviation"));
			flag = webDB.waitForElement(PurchaseBillsGrnLocators.PostButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Post Button Is Displayed");
				webDB.clickAnElement(PurchaseBillsGrnLocators.PostButton, ElementType.Xpath);
				flag = (webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 10)
						|| webDB.waitForElement(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath));
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Process Calculation Compare Excel Sheet With DataBase
	 * 
	 * @author Vimal-RMITS
	 */
	public void deviationCalculationCompareExcelSheetWithDataBaseForGrn(String Sheetname) {
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DirectGrnDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, Sheetname);
		System.out.println(itemmultiple.length);
		for (int i = 22; i < itemmultiple.length; i++) {
			String MatchedAmt = itemmultiple[i][77];
			String GRNAmt = itemmultiple[i][75];
			String deviationAmt = itemmultiple[i][78];
			System.out.println(MatchedAmt);
			System.out.println(deviationAmt);
			if (!MatchedAmt.equals("")) {
				salesOrdFunc.verifyAssertionBothValues(dbCommonFunc.PBReferenceQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("PBDBREFMATCHEDAMTCOLUMN")), MatchedAmt);
				System.out.println(commonFunc.getDataFromTestDataSheet("PBDBREFMATCHEDAMTCOLUMN"));
				logs.logging("INFO", "MatchedAmt Details Matched With DataBase Values");

//				salesOrderFunctions
//						.verifyAssertionBothValues(dbCommonFunctions.PBReferenceQuery(VariableHandler.getPbNumber(),
//								commonFunc.getDataFromTestDataSheet("PBDBREFGRNAMTCOLUMN")), GRNAmt);
//				logs.logging("INFO", "GRNAmt Details Matched With DataBase Values");

				salesOrdFunc.verifyAssertionBothValues(dbCommonFunc.PBReferenceQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("PBDBREFDEVIATIONAMTCOLUMN")), deviationAmt);

				logs.logging("INFO", "PBUnitcost Details Matched With DataBase Values");
				commonFunc.hardWait(2000);
			}
		}
	}

	/**
	 * This Methods Used To store PurchaseBill Matched Amt And GRNAmt And Deviation
	 * Amt
	 * 
	 * @author Vimal - RMITS
	 */
	public void storePurchaseBillMatchedAmtAndGRNAmtAndDeviationAmt() {
		commonFunc.hardWait(1000);
		String MatchedAmt = webDB.getDomPropertyFromElement(PurchaseBillsGrnLocators.PB_Reference_MatchedAmt,
				ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get Matched Amt Is: " + MatchedAmt);
		VariableHandler.setMatchedAmt(MatchedAmt);
		commonFunc.hardWait(1000);
		String GRNAmt = webDB.getDomPropertyFromElement(PurchaseBillsGrnLocators.PB_Reference_GRNAmt, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get SCO GRN Amt Is: " + GRNAmt);
		VariableHandler.setGRNAmt(GRNAmt);
		commonFunc.hardWait(1000);
		String deviationAmt = webDB.getDomPropertyFromElement(PurchaseBillsGrnLocators.PB_Reference_DeviationAmt,
				ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
		logs.logging("INFO", "Get SCO OrderQty Is: " + deviationAmt);
		VariableHandler.setdeviationAmt(deviationAmt);
		commonFunc.hardWait(2000);
	}

}
