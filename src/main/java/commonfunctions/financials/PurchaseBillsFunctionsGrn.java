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
	public void VerifyCreatingPurchaseBillwithAdvance() throws IOException {
		VerifyRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviation();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		commonFunc.navigateToFinancialPaymentReceipt();
		VerifyCreatePaymentFromPurchaseBill(commonFunc.getDataFromTestDataSheet("PaymentType"),
				commonFunc.getDataFromTestDataSheet("BankPayMode"), commonFunc.getDataFromTestDataSheet("BankName"));
		BPFunc.getBankPaymentOrReceiptDocumentNumber();
//			BPFunc.checkBPDocumentStatusWithDB(commonFunc.getDataFromTestDataSheet("Draft"));
//			verifyBankPaymentHeaderDetailsWithUI(commonFunc.getDataFromTestDataSheet("BankName"),
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
	public void VerifyCreatePaymentFromPurchaseBill(String Type, String PayMode, String BankName) {
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

							flag = webDB.waitForElement(PurchaseBillsGrnLocators.BPPendingPartyDocumentSelectCheckBox(
									VariableHandler.getPbNumber()), ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(PurchaseBillsGrnLocators.BPPendingPartyDocumentSelectCheckBox(
										VariableHandler.getPbNumber()), ElementType.Xpath);
								logs.logging("INFO", "Document Is Selected");
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
		flag = webDB.waitForElement(BankPaymentLocators.BankLineViewButton(1), ElementType.CssSelector);
		if (flag) {
			webDB.clickAnElement(BankPaymentLocators.BankLineViewButton(1), ElementType.CssSelector);
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
	 * This Method Used To Creating Grn With Advance
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAgainstGRNWithDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySaveLine();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAgainstGRNWithDeviationAndExpense() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySaveLine();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifySaveLine();
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
	public void VerifyRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviationAndExpense() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAgainstGRNWithDeviationExpenseAndAdjustment() throws IOException {
//		VerifyCreatingPurchaseBillwithAdvance();
//		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySaveLine();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
//		VerifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
//		VerifyCreatingPurchaseBillwithAdvance();
//		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
//		VerifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySaveLine();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
		VerifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithDeviationAndExpense() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySaveLine();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
		VerifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithDeviationExpenseAndAdjustment()
			throws IOException {
//		VerifyCreatingPurchaseBillwithAdvance();
//		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySaveLine();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
//		VerifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		VerifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithoutDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
		VerifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithoutDeviationAndExpense() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
		VerifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAllowIgstAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
//		VerifyCreatingPurchaseBillwithAdvance();
//		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySaveLine();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifySaveLine();
//		VerifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		VerifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithoutDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
		VerifyClickingReferenceTabForRcmFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithoutDeviationAndExpense() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
		VerifyClickingReferenceTabForRcmFlag();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
//		VerifyCreatingPurchaseBillwithAdvance();
//		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
//		VerifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		VerifyClickingReferenceTabForRcmFlag();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySaveLine();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
		VerifyClickingReferenceTabForRcmFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithDeviationAndExpense() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySaveLine();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
		VerifyClickingReferenceTabForRcmFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAllowRcmAgainstGRNWithDeviationExpenseAndAdjustment()
			throws IOException {
//		VerifyCreatingPurchaseBillwithAdvance();
//		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySaveLine();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
//		VerifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		VerifyClickingReferenceTabForRcmFlag();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySaveLine();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		VerifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithDeviationAndExpense()
			throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySaveLine();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		VerifySaveLine();
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithDeviationExpenseAndAdjustment()
			throws IOException {
//		VerifyCreatingPurchaseBillwithAdvance();
//		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySaveLine();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		VerifySaveLine();
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
//		VerifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithoutDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		VerifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithoutDeviationAndExpense()
			throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		VerifySaveLine();
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTITCIneligibleAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
//		VerifyCreatingPurchaseBillwithAdvance();
//		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		VerifySaveLine();
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
//		VerifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySaveLine();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		VerifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
		VerifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithDeviationAndExpense()
			throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySaveLine();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		VerifySaveLine();
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
		VerifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithDeviationExpenseAndAdjustment()
			throws IOException {
//		VerifyCreatingPurchaseBillwithAdvance();
//		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySaveLine();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		VerifySaveLine();
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
//		VerifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		VerifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithoutDeviation()
			throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
		VerifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		VerifySaveLine();
		VerifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithoutDeviationAndExpense()
			throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		VerifySaveLine();
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
		VerifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAllowIgstITCIneligibleAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
//		VerifyCreatingPurchaseBillwithAdvance();
//		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySaveLine();
		VerifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		VerifySaveLine();
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifySaveLine();
//		VerifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		VerifyClickingReferenceTabForIgstFlag();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithoutDeviation()
			throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		VerifySaveLine();
		VerifyClickingReferenceTabForRcmFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithoutDeviationAndExpense()
			throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		VerifySaveLine();
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
		VerifyClickingReferenceTabForRcmFlag();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithoutDeviationExpenseAndAdjustment()
			throws IOException {
//		VerifyCreatingPurchaseBillwithAdvance();
//		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySaveLine();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		VerifySaveLine();
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
//		VerifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		VerifyClickingReferenceTabForRcmFlag();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithDeviation() throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySaveLine();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
		VerifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		VerifySaveLine();
		VerifyClickingReferenceTabForRcmFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithDeviationAndExpense()
			throws IOException {
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySaveLine();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		VerifySaveLine();
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
		VerifyClickingReferenceTabForRcmFlag();
		verifyCreateJournalAndPost();

		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Durai
	 * @throws IOException
	 */
	public void VerifyRegisteredSupplierwithLocalGSTAllowRcmITCIneligibleAgainstGRNWithDeviationExpenseAndAdjustment()
			throws IOException {
//		VerifyCreatingPurchaseBillwithAdvance();
//		commonFunc.logoutUser();
		DirectGRNFunc.verifyCreationOfDirectGrnSameUomGeneralTypeWithInspectionDPurchaseBill();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
		ValidateHeaderDetails();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySaveLine();
		ValidatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		VerifySelectTypeForIneligible(commonFunc.getDataFromTestDataSheet("PBITCInEligibleType"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSection"),
				commonFunc.getDataFromTestDataSheet("PBITCInEligibleSubSection"));
		VerifySaveLine();
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifySaveLine();
		verifyAddTheItemBasedOnTypeForAgainstGRN(webDB.getDataFromProperties("PurchaseBill"));
		VerifySaveLine();
//		VerifyClickingAdjustmentTab(VariableHandler.getBankPaymentOrReceiptDocNumber());
		VerifyClickingReferenceTabForRcmFlag();
		verifyCreateJournalAndPost();
		waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @author Deepan-GWL
	 * @param DocNo
	 */
	public void verifyPendingPurchaseBill(String DocNo, String TdsNo) {
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
							ValidatePurchaseBillsPendingGRNDetails();
							ValidatePurchaseBillsPendingGRNItemDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
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
		webDB.moveToElement(PurchaseBillsLocators.TDSU_S, ElementType.Xpath);
		flag = webDB.waitForElement(PurchaseBillsLocators.TDSU_S, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocators.TDSU_S, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocators.TDSLovButton, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocators.TDSLovButton, TdsNo);
				VerifySaveLine();
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
		verifyPendingPurchaseBill(VariableHandler.getGrnDocNumber(), commonFunc.getDataFromTestDataSheet("TDSValue"));
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
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
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
	 * This Method Used To Verify Pending Purchase Bill
	 * 
	 * @param SheetName
	 * @author Durai
	 */
	private void verifyChangeCostForDeviation(String SheetName) {
		String[][] GRNMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DirectGrnDetails.xlsx";
		GRNMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 22; i < GRNMultipleline.length; i++) {
			System.out.println(GRNMultipleline.length);
			String Cost = GRNMultipleline[i][6];
			if (!Cost.equals("")) {
				flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBCostField, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsGrnLocators.PBCostField, ElementType.Xpath);
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
	private void verifyAddExpenseType(String GLAccount, String Type) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBAddLine, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsGrnLocators.PBAddLine, ElementType.Xpath);
			logs.logging("INFO", "Add Button is Clicked");
			flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBAddTypeExp, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseBillsGrnLocators.PBAddTypeExp, ElementType.Xpath);
				webDB.selectDropDownByValue(PurchaseBillsGrnLocators.PBAddTypeExp, Type, ElementType.Xpath);
				logs.logging("INFO", "Field Is Clicked");
				flag = webDB.waitForElement(PurchaseBillsGrnLocators.GlAccDescField, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsGrnLocators.GlAccDescField, ElementType.Xpath);
					logs.logging("INFO", "Field Is Clicked");
					flag = webDB.waitForElement(PurchaseBillsGrnLocators.GlAccLov, ElementType.Xpath);
					if (flag) {
						commonFunc.searchAndSelectValueInFilters(PurchaseBillsGrnLocators.GlAccLov, GLAccount);
						logs.logging("INFO", "GL Account Is Entered:" + GLAccount);
						VerifyClickingHSNCode(commonFunc.getDataFromTestDataSheet("Hsn_code"));
						verifyChangeCostForDeviation(webDB.getDataFromProperties("DirectGrnGenUD"));
						commonFunc.checkRefreshCellsDisappear();
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
	private void VerifySaveLine() {
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
	private void VerifyClickingAdjustmentTab(String AdvanceNo) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBAdjustmentTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsGrnLocators.PBAdjustmentTab, ElementType.Xpath);
			logs.logging("INFO", "Adjustment tab is CLicked");
			flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBAdjustmentLoadBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseBillsGrnLocators.PBAdjustmentLoadBtn, ElementType.Xpath);
				logs.logging("INFO", "Load Button iS Clicked");
				VerifyAdjustmentFrame();
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
	private void VerifyAdjustmentFrame() {
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
	private void VerifyClickingReferenceTabForIgstFlag() {
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
	private void VerifyClickingReferenceTabForRcmFlag() {
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
	private void VerifySelectTypeForIneligible(String ITCType, String ITCSection, String ITCSubSection) {
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
	private void VerifyClickingHSNCode(String HSN) {
		flag = webDB.waitForElement(PurchaseBillsGrnLocators.ExpenseHsnField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsGrnLocators.ExpenseHsnField, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(PurchaseBillsGrnLocators.ExpenseHsnLovBtn, HSN);
			logs.logging("INFO", "HSN code IS Enterd:" + HSN);
		}

	}

	/**
	 * This Method Used To Verify Purchase Bill Header
	 * 
	 * @author Durai
	 */
	private void ValidateHeaderDetails() {

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
	private void ValidatePurchaseBillsPendingGRNDetails() {
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
	private void ValidatePurchaseBillsPendingGRNItemDetails(String SheetName) {
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
	private void ValidatePurchaseBillsLineDetails(String SheetName) {
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
	private void ValidatePurchaseBillsLineDetailsAfterDevaition(String SheetName) {
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
								VerifyToCreateExpenseType(GLAccount, Cost, HSN);
							} else if (Type.equals(commonFunc.getDataFromTestDataSheet("ItemType"))) {
								VerifyToCreatetheItemTypeDocument(Item, Cost, Qty, HSN, GLAccount, TrdDisc, SpclDisc,
										CashDisc);
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
	public void VerifyToCreateExpenseType(String GLAccount, String Cost, String HSN) {

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
							VerifySaveLine();

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
	public void VerifyToCreatetheItemTypeDocument(String Item, String Cost, String Qty, String HSN, String GLAccount,
			String TrdDisc, String SpclDisc, String CashDisc) {
		webDB.waitForElement(PurchaseBillsGrnLocators.PBItemField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsGrnLocators.PBItemField, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBItemLov, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(PurchaseBillsGrnLocators.PBItemLov, Item);
				logs.logging("INFO", "Item Is Selected SuccessFully:" + Item);
				flag = webDB.waitForElement(PurchaseBillsGrnLocators.GlAccDescField, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsGrnLocators.GlAccDescField, ElementType.Xpath);
					logs.logging("INFO", "Field Is Clicked");
					flag = webDB.waitForElement(PurchaseBillsGrnLocators.GlAccLov, ElementType.Xpath);
					if (flag) {
						commonFunc.searchAndSelectValueInFilters(PurchaseBillsGrnLocators.GlAccLov, GLAccount);
						logs.logging("INFO", "GL Account Is Entered:" + GLAccount);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBQtyField, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseBillsGrnLocators.PBQtyField, ElementType.Xpath);
							webDB.clearText(PurchaseBillsGrnLocators.PBQtyInput, ElementType.Xpath);
							webDB.sendTextToAnElement(PurchaseBillsGrnLocators.PBQtyInput, Qty, ElementType.Xpath);
							logs.logging("INFO", "Entered Qty Is:" + Qty);
							flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBCostField, ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(PurchaseBillsGrnLocators.PBCostField, ElementType.Xpath);
								webDB.clearText(PurchaseBillsGrnLocators.PBCostInput, ElementType.Xpath);
								webDB.sendTextToAnElement(PurchaseBillsGrnLocators.PBCostInput, Cost,
										ElementType.Xpath);
								logs.logging("INFO", "Entered Cost Is:" + Cost);
								flag = webDB.waitForElement(PurchaseBillsGrnLocators.ExpenseHsnField,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(PurchaseBillsGrnLocators.ExpenseHsnField, ElementType.Xpath);
									flag = webDB.waitForElement(PurchaseBillsGrnLocators.ExpenseHsnLovBtn,
											ElementType.Xpath);
									if (flag) {
										commonFunc.searchAndSelectValueInFilters(
												PurchaseBillsGrnLocators.ExpenseHsnLovBtn, HSN);
										logs.logging("INFO", "Entered Cost Is:" + HSN);
										commonFunc.checkRefreshCellsDisappear();
										webDB.pressTabKey();
										VerifyAddingTradeSpclCashDiscount(TrdDisc, SpclDisc, CashDisc);
										VerifySaveLine();
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

	private void VerifyAddingTradeSpclCashDiscount(String TrdDisc, String SpclDisc, String CashDisc) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBTrdDiscPercField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsGrnLocators.PBTrdDiscPercField, ElementType.Xpath);
			webDB.clearText(PurchaseBillsGrnLocators.PBTrdDiscPercInput, ElementType.Xpath);
			webDB.sendTextToAnElement(PurchaseBillsGrnLocators.PBTrdDiscPercInput, TrdDisc, ElementType.Xpath);
			logs.logging("INFO", "Trade Discount Percent Is Entered: " + TrdDisc);
			flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBSpclDiscPercField, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseBillsGrnLocators.PBSpclDiscPercField, ElementType.Xpath);
				webDB.clearText(PurchaseBillsGrnLocators.PBSpclDiscPercInput, ElementType.Xpath);
				webDB.sendTextToAnElement(PurchaseBillsGrnLocators.PBSpclDiscPercInput, SpclDisc, ElementType.Xpath);
				logs.logging("INFO", "Special Discount Percent Is Entered: " + SpclDisc);
				flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBCashDiscPercField, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsGrnLocators.PBCashDiscPercField, ElementType.Xpath);
					webDB.clearText(PurchaseBillsGrnLocators.PBCashDiscPercInput, ElementType.Xpath);
					webDB.sendTextToAnElement(PurchaseBillsGrnLocators.PBCashDiscPercInput, CashDisc,
							ElementType.Xpath);
					logs.logging("INFO", "Cash Discount Percent Is Entered: " + CashDisc);
					webDB.pressTabKey();

				}
			}
		}
	}
}
