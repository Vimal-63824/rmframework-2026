package commonfunctions.financials;

/**
 * @author Vimal - RMITS
 * @company RoadMap IT Solutions Feb 10, 2026
 */
import static org.testng.Assert.assertTrue;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonfunctions.supplychainmanagement.PurchaseOrderFunctions;
import commonfunctions.supplychainmanagement.SalesInvoiceFunctions;
import commonfunctions.supplychainmanagement.SalesOrderFunctions;
import commonfunctions.supplychainmanagement.SubcontractOrderFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.financials.PurchaseBillsGrnLocators;
import commonlocators.financials.PurchaseBillsLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class SubcontractPurchaseBills {

	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	static ApprovalPageFunctions approvalPageFunc = new ApprovalPageFunctions();
	private static SalesOrderFunctions SalesordFunc = new SalesOrderFunctions();
	private ApprovalPageFunctions approveFunc = new ApprovalPageFunctions();
	static SalesInvoiceFunctions SalesInv = new SalesInvoiceFunctions();
	private static DBCommonFunctions dbCommonFunc = new DBCommonFunctions();
	private String GetTextLocationValue;
	static PurchaseOrderFunctions POfunc = new PurchaseOrderFunctions();
	static BankPaymentFunctions BPFunc = new BankPaymentFunctions();
	static SubcontractOrderFunctions SubcontractFunc = new SubcontractOrderFunctions();
	static PurchaseBillsFunctionsGrn PurBillFunc = new PurchaseBillsFunctionsGrn();
	static PurchaseBillsFunctions purchaseBillsFunc = new PurchaseBillsFunctions();

	/**
	 * This Method Used To Verify Pending SCO Purchase Bill
	 * 
	 * @author Vimal - RMITS
	 * @param DocNo
	 */
	public void verifyPendingSCOPurchaseBill(String DocNo, String TdsNo) {
		flag = webDB.waitForElement(PurchaseBillsGrnLocators.PendingPurchaseBill, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Purchase Bill Is Displayed");
			webDB.clickAnElement(PurchaseBillsGrnLocators.PendingPurchaseBill, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsGrnLocators.PB_PendSCOField, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Grn Is Displayed");
				webDB.clickAnElement(PurchaseBillsGrnLocators.PB_PendSCOField, ElementType.Xpath);
				flag = webDB.waitForElement(PurchaseBillsGrnLocators.Iframe, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Iframe Is Displayed");
					webDB.switchToFrame(PurchaseBillsGrnLocators.Iframe, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(PurchaseBillsGrnLocators.SCONoField, DocNo);
					flag = webDB.waitForElement(PurchaseBillsGrnLocators.Iframe, ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(PurchaseBillsGrnLocators.Iframe, ElementType.Xpath);
						flag = webDB.waitForElement(PurchaseBillsGrnLocators.SCO_SearchButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Switched To Iframe");
							webDB.clickAnElement(PurchaseBillsGrnLocators.SCO_SearchButton, ElementType.Xpath);
							// validatePurchaseBillsPendingGRNDetails();
							// validatePurchaseBillsPendingGRNItemDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
							commonFunc.hardWait(3000);
							flag = webDB.waitForElement(PurchaseBillsGrnLocators.SCO_CheckBox, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "CheckBox IS Displayed");
								commonFunc.hardWait(3000);
								webDB.clickAnElement(PurchaseBillsGrnLocators.SCO_CheckBox, ElementType.Xpath);
								commonFunc.hardWait(6000);
								flag = webDB.waitForElement(PurchaseBillsGrnLocators.CreateDoc, ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Create Documents Is Displayed");
									webDB.clickAnElement(PurchaseBillsGrnLocators.CreateDoc, ElementType.Xpath);
									flag = webDB.waitForElement(PurchaseBillsGrnLocators.OkButton, ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Okay Button Is Displayed");
										webDB.clickAnElement(PurchaseBillsGrnLocators.OkButton, ElementType.Xpath);
										webDB.switchToDefaultContent();
										flag = webDB.waitForElement(PurchaseBillsGrnLocators.LocationId,
												ElementType.Xpath);
										PurBillFunc.storePurchaseVoucherNumber();
										PurBillFunc.verifyAddTDSNumber(TdsNo);
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
	 * This Method used For Registered Supplier with Local GST- Against Subcontract
	 * With Deviation and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalGSTAgainstSubcontractWithDeviationandExpensesAccount() {
		SubcontractFunc.directSubcontractorderToGRNTypePRWithTDS();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		PurBillFunc.pendingSubcontractAgainstvalidateHeaderDetails();
//		PurBillFunc.validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
//		PurBillFunc.verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
//		PurBillFunc.verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
//		PurBillFunc.verifySaveLine();
//		PurBillFunc.validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		verifyAddExpenseType(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		PurBillFunc.verifyCreateJournalAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Clicking Linewise Process Details
	 * 
	 * @author Vimal - RMITS
	 */
	public void clickingLinewiseProcessDetails(String TdsNo) {
		webDB.moveToElement(PurchaseBillsGrnLocators.PB_LineProcess_Button, ElementType.Xpath);
		webDB.clickAnElement(PurchaseBillsGrnLocators.PB_LineProcess_Button, ElementType.Xpath);
		webDB.switchToFrame(PurchaseBillsGrnLocators.PB_ProcessFrame, ElementType.Xpath);
		webDB.moveToElement(PurchaseBillsGrnLocators.PB_Process_TDS_Lov, ElementType.Xpath);
		flag = webDB.waitForElement(PurchaseBillsGrnLocators.PB_Process_TDS_Lov, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(PurchaseBillsGrnLocators.PB_Process_TDS_Lov, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(PurchaseBillsGrnLocators.ProcessTDSLovButton, TdsNo);
			webDB.switchToFrame(PurchaseBillsGrnLocators.PB_ProcessFrame, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsGrnLocators.PB_Process_Save, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseBillsGrnLocators.PB_Process_Save, ElementType.Xpath);
				commonFunc.hardWait(3000);
				flag = webDB.waitForElement(PurchaseBillsGrnLocators.PB_Process_Close, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsGrnLocators.PB_Process_Close, ElementType.Xpath);
					webDB.switchToDefaultContent();
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(1000);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Expense Type in Purchase Bill
	 * 
	 * @param GLAccount
	 * @param Type
	 * @author Vimal - RMITS
	 */
	public void verifyAddExpenseType(String GLAccount, String Type) {
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
						PurBillFunc.verifyClickingHSNCode(commonFunc.getDataFromTestDataSheet("Hsn_code"));
						PurBillFunc.verifyChangeCostForDeviation(webDB.getDataFromProperties("DirectGrnGenUD"));
						PurBillFunc.verifySaveLine();
						commonFunc.checkRefreshCellsDisappear();
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used For Registered Supplier with Local GST- Against Subcontract
	 * Without Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalGSTAgainstSubcontractWithoutDeviation() {
		SubcontractFunc.directSubcontractorderToGRNTypePRWithTDS();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		PurBillFunc.pendingSubcontractAgainstvalidateHeaderDetails();
//		PurBillFunc.validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
//		PurBillFunc.verifyTowritePriceAdditionToWriteInExcelForPurchasebillDocument(webDB.getDataFromProperties("PurchaseBillGRN"));
//		PurBillFunc.verifyChangeCostForDeviation(webDB.getDataFromProperties("PurchaseBillGRN"));
//		PurBillFunc.verifySaveLine();
//		PurBillFunc.validatePurchaseBillsLineDetails(webDB.getDataFromProperties("PurchaseBillGRN"));
		PurBillFunc.verifyCreateJournalAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * Registered Supplier with Local GST- Against Subcontract Without Deviation and
	 * Expenses Account And adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalGSTAgainstSubcontractWithoutDeviationandExpensesAccountAndadjustment() {
		SubcontractFunc.directSubcontractorderToGRNTypePRWithTDS();
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustment(commonFunc.getDataFromTestDataSheet("BankName"),
				commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankPaymentSingle"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		purchaseBillsFunc.clickPostButtonApprovalRecordBankPayments();	
	}
}
