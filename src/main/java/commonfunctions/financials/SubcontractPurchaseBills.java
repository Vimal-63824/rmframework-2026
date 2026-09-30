package commonfunctions.financials;

/**
 * @author Vimal - RMITS
 * @company RoadMap IT Solutions Feb 10, 2026
 */
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

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
import commonlocators.financials.PurchaseBillsLocatorsGetDocument;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import commonlocators.supplychainmanagement.SalesInvoiceLocators;
import commonlocators.supplychainmanagement.SubcontractOrderLocators;
import utils.ExcelUtils;
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
	public ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	SalesOrderFunctions salesOrderFunctions = new SalesOrderFunctions();
	DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();
	PurchaseBillsFunctionsGetDocument purBillGetDoc = new PurchaseBillsFunctionsGetDocument();

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
							flag = webDB.waitForElement(PurchaseBillsGrnLocators.SCO_CheckBox, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "CheckBox IS Displayed");
								webDB.clickAnElement(PurchaseBillsGrnLocators.SCO_CheckBox, ElementType.Xpath);
								flag = webDB.waitForElement(PurchaseBillsGrnLocators.CreateDoc, ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Create Documents Is Displayed");
									webDB.clickAnElement(PurchaseBillsGrnLocators.CreateDoc, ElementType.Xpath);
									flag = webDB.waitForElement(PurchaseBillsGrnLocators.OkButton, ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Okay Button Is Displayed");
										webDB.clickAnElement(PurchaseBillsGrnLocators.OkButton, ElementType.Xpath);
										webDB.switchToDefaultContent();
										commonFunc.checkRefreshCellsDisappear();
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
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("PurchaseBillDeviationandExp"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		verifyCreateJournalAndVerifyDeviationAndExpAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method Used To Clicking Linewise Process Details
	 * 
	 * @author Vimal - RMITS
	 */
	public void clickingLinewiseProcessDetails(String TdsNo) {
		webDB.waitForElement(PurchaseBillsGrnLocators.PB_Line_Detail_Btn, ElementType.Xpath);
		webDB.moveToElement(PurchaseBillsGrnLocators.PB_Line_Detail_Btn, ElementType.Xpath);
		webDB.clickAnElement(PurchaseBillsGrnLocators.PB_Line_Detail_Btn, ElementType.Xpath);
		flag = webDB.waitForElement(PurchaseBillsGrnLocators.PB_LineProcess_Button, ElementType.Xpath);
		if (flag) {
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
					storePurchaseBillProcesTabProcessQtyAndUnitCost();
					flag = webDB.waitForElement(PurchaseBillsGrnLocators.PB_Process_Close, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseBillsGrnLocators.PB_Process_Close, ElementType.Xpath);
						webDB.switchToDefaultContent();
						commonFunc.checkRefreshCellsDisappear();
					}
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
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"), commonFunc.getDataFromTestDataSheet("SupplierForTDS"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		PurBillFunc.pendingSubcontractAgainstvalidateHeaderDetails();
		purchaseBillsFunc
				.verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("PurchaseBills_ExAcc"));
		purchaseBillsFunc.ClickSaveButton();
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		purchaseBillsFunc.createJvForRecord();
		PurBillFunc.verifyCreatePost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
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
			logs.logging("INFO", "Verified Actual DataBase Value : " + Actual + " And Expected Values : " + Expected);
		} else {
			logs.logging("INFO",
					"Mismatch With Actual DataBase Value : " + Actual + " And Expected Values " + Expected);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Verify SCO GRN Total Amount And Purchase Bill Matched
	 * Amount
	 * 
	 * @author Vimal-RMITS
	 */
	public void verifySCOGRNTotalAmountAndPurchaseBillMatchedAmount() {
		verifyAssertionBothValues(
				dbCommonFunc.SCOGRNLINEWISE(VariableHandler.getScoGrnNumber(),
						commonFunc.getDataFromTestDataSheet("SCODBMATAMTCOLUMN")),
				dbCommonFunc.PBReferenceQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("PBDBREFMATCHEDAMTCOLUMN")));
		logs.logging("INFO", "GRN Amount Matched With PurchaseBill Matched Amt ");
		commonFunc.hardWait(1000);
	}

	/**
	 * This Method Used For Verify SCO GRN Total Amount And Purchase Bill GRN Total
	 * Amount
	 * 
	 * @author Vimal-RMITS
	 */
	public void verifySCOGRNTotalAmountAndPurchaseBillGRNAmount() {
		verifyAssertionBothValues(
				dbCommonFunc.SCOGRNLINEWISE(VariableHandler.getScoGrnNumber(),
						commonFunc.getDataFromTestDataSheet("SCODBMATAMTCOLUMN")),
				dbCommonFunc.PBReferenceQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("PBDBREFGRNAMTCOLUMN")));
		logs.logging("INFO", "GRN Amount Matched With PurchaseBill Matched Amt ");
		commonFunc.hardWait(1000);
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

	/**
	 * This Methods Used To store PurchaseBill Proces Tab Process Qty And UnitCost
	 * 
	 * @author Vimal - RMITS
	 */
	public void storePurchaseBillProcesTabProcessQtyAndUnitCost() {
		commonFunc.hardWait(1000);
		String PBProcQty = webDB.getTextFromElement(PurchaseBillsGrnLocators.PB_ProcessTab_ProcQty, ElementType.Xpath);
		logs.logging("INFO", "Get PBProcQty Amt Is: " + PBProcQty);
		VariableHandler.setPBProcQty(PBProcQty);
		commonFunc.hardWait(1000);
		String PBUnitcost = webDB.getTextFromElement(PurchaseBillsGrnLocators.PB_ProcessTab_UnitCost,
				ElementType.Xpath);
		logs.logging("INFO", "Get PBUnitcost Is: " + PBUnitcost);
		VariableHandler.setPBUnitcost(PBUnitcost);
		commonFunc.hardWait(1000);
	}

	/**
	 * This Methods Used To Get BatchQty, BatchCost and Write In Excel Sheet
	 * 
	 * @author Vimal - RMITS
	 */
	public void getMatchedAmtAndGRNAmtandWriteInExcelSheet(String Sheetname) {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, Sheetname);
		logs.logging("INFO", "Before Process Id ");
		for (int i = 4; i < ItemMultiple.length; i++) {
			String MatchedAmt = ItemMultiple[i][0];
			String GRNAmt = ItemMultiple[i][1];
			String PBUnitcost = ItemMultiple[i][2];
			String PBProcQty = ItemMultiple[i][3];
			if (!MatchedAmt.equals("")) {
				logs.logging("INFO", "Before MatchedAmt  " + MatchedAmt + "Is : " + VariableHandler.getMatchedAmt());
				excelUtils.writeDataInExcel(Filepath, Sheetname, i, 0, VariableHandler.getMatchedAmt());
				logs.logging("INFO", "Before GRNAmt  " + GRNAmt + "Is : " + VariableHandler.getGRNAmt());
				excelUtils.writeDataInExcel(Filepath, Sheetname, i, 1, VariableHandler.getGRNAmt());
				logs.logging("INFO", "Before PBUnitcost  " + PBUnitcost + "Is : " + VariableHandler.getPBUnitcost());
				excelUtils.writeDataInExcel(Filepath, Sheetname, i, 2, VariableHandler.getPBUnitcost());
				logs.logging("INFO", "Before PBProcQty  " + PBProcQty + "Is : " + VariableHandler.getPBProcQty());
				excelUtils.writeDataInExcel(Filepath, Sheetname, i, 3, VariableHandler.getPBProcQty());
			}
		}
	}

	/**
	 * This Method Used For Process Calculation Compare Excel Sheet With DataBase
	 * 
	 * @author Vimal-RMITS
	 */
	public void deviationCalculationCompareExcelSheetWithDataBase(String Sheetname) {
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, Sheetname);
		System.out.println(itemmultiple.length);
		for (int i = 4; i < itemmultiple.length; i++) {
			String MatchedAmt = itemmultiple[i][4];
			String GRNAmt = itemmultiple[i][1];
			String deviationAmt = itemmultiple[i][5];
			System.out.println(MatchedAmt);
			if (!MatchedAmt.equals("")) {
				salesOrderFunctions
						.verifyAssertionBothValues(dbCommonFunctions.PBReferenceQuery(VariableHandler.getPbNumber(),
								commonFunc.getDataFromTestDataSheet("PBDBREFMATCHEDAMTCOLUMN")), MatchedAmt);
				System.out.println(commonFunc.getDataFromTestDataSheet("PBDBREFMATCHEDAMTCOLUMN"));
				logs.logging("INFO", "MatchedAmt Details Matched With DataBase Values");

//				salesOrderFunctions
//						.verifyAssertionBothValues(dbCommonFunctions.PBReferenceQuery(VariableHandler.getPbNumber(),
//								commonFunc.getDataFromTestDataSheet("PBDBREFGRNAMTCOLUMN")), GRNAmt);
//				logs.logging("INFO", "GRNAmt Details Matched With DataBase Values");

				salesOrderFunctions
						.verifyAssertionBothValues(
								dbCommonFunctions.PBReferenceQuery(VariableHandler.getPbNumber(),
										commonFunc.getDataFromTestDataSheet("PBDBREFDEVIATIONAMTCOLUMN")),
								deviationAmt);
				logs.logging("INFO", "PBUnitcost Details Matched With DataBase Values");
				commonFunc.hardWait(2000);
			}
		}
	}

	/**
	 * This Method Used For Process Calculation Compare Excel Sheet With DataBase
	 * 
	 * @author Vimal-RMITS
	 */
	public void deviationWithTaxCalculationCompareExcelSheetWithDataBase(String Sheetname) {
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, Sheetname);
		System.out.println(itemmultiple.length);
		for (int i = 4; i < itemmultiple.length; i++) {
			String MatchedAmt = itemmultiple[i][14];
			String GRNAmt = itemmultiple[i][1];
			String deviationAmt = itemmultiple[i][5];
			String NetAmt = itemmultiple[i][14];
			System.out.println(MatchedAmt);
			if (!MatchedAmt.equals("")) {
				salesOrderFunctions
						.verifyAssertionBothValues(dbCommonFunctions.PBReferenceQuery(VariableHandler.getPbNumber(),
								commonFunc.getDataFromTestDataSheet("PBDBREFMATCHEDAMTCOLUMN")), MatchedAmt);
				System.out.println(commonFunc.getDataFromTestDataSheet("PBDBREFMATCHEDAMTCOLUMN"));
				logs.logging("INFO", "MatchedAmt Details Matched With DataBase Values");

//				salesOrderFunctions
//						.verifyAssertionBothValues(dbCommonFunctions.PBReferenceQuery(VariableHandler.getPbNumber(),
//								commonFunc.getDataFromTestDataSheet("PBDBREFGRNAMTCOLUMN")), GRNAmt);
//				logs.logging("INFO", "GRNAmt Details Matched With DataBase Values");

				salesOrderFunctions
						.verifyAssertionBothValues(
								dbCommonFunctions.PBReferenceQuery(VariableHandler.getPbNumber(),
										commonFunc.getDataFromTestDataSheet("PBDBREFDEVIATIONAMTCOLUMN")),
								deviationAmt);
				logs.logging("INFO", "Devaiation Amt Details Matched With DataBase Values");

				salesOrderFunctions.verifyAssertionBothValues(dbCommonFunctions.PBQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("PBDBNetAmountCOLUMN")), NetAmt);
				logs.logging("INFO", "Net Amount Matched With DataBase Values");
				commonFunc.hardWait(2000);
			}
		}
	}

	/**
	 * Verify TableSpec InputParameter
	 * 
	 * @author Vimal - RMITS
	 * @param Tablespec
	 * @param InputParameter
	 * @param Enteredvalue
	 */
	public void verifyAddTableSpecs(String Tablespec, String InputParameter, String Enteredvalue) {
		flag = webDB.waitForElement(Tablespec, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(Tablespec, ElementType.Xpath);
			logs.logging("INFO", "Table Input Box Is Clicked");
			webDB.clearTextField(InputParameter, ElementType.Xpath);
			logs.logging("INFO", "Clear Text Field The Input Value");
			flag = webDB.waitForElement(Tablespec, ElementType.Xpath);
			if (flag) {
				webDB.doubleClickAnElement(Tablespec, ElementType.Xpath);
				webDB.sendTextToAnElement(InputParameter, Enteredvalue, ElementType.Xpath);
				logs.logging("INFO", "Entered Value Is: " + Enteredvalue);
				String value = webDB.getDomPropertyFromElement(InputParameter, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value"));
				logs.logging("INFO", "Get Attribute Value Is: " + value);
				flag = value.contains(Enteredvalue);
				if (flag) {
					logs.logging("INFO", "Verified Entered Value With Get Attribute Value");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Clicking Linewise Process Details Changing Qty and
	 * Unitcost
	 * 
	 * @author Vimal - RMITS
	 */
	public void clickingLinewiseProcessDetailsChangingQtyandUnitcost(String TdsNo, String PBProcessQtyInput) {
		webDB.waitForElement(PurchaseBillsGrnLocators.PB_Line_Detail_Btn, ElementType.Xpath);
		webDB.moveToElement(PurchaseBillsGrnLocators.PB_Line_Detail_Btn, ElementType.Xpath);
		webDB.clickAnElement(PurchaseBillsGrnLocators.PB_Line_Detail_Btn, ElementType.Xpath);
		flag = webDB.waitForElement(PurchaseBillsGrnLocators.PB_LineProcess_Button, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsGrnLocators.PB_LineProcess_Button, ElementType.Xpath);
			commonFunc.hardWait(1000);
			webDB.switchToFrame(PurchaseBillsGrnLocators.PB_ProcessFrame, ElementType.Xpath);
			commonFunc.hardWait(1000);
			verifyAddTableSpecs(PurchaseBillsGrnLocators.PB_ProcessTab_ProcQty,
					PurchaseBillsGrnLocators.PB_ProcessTab_procQtyField, PBProcessQtyInput);
			commonFunc.hardWait(1000);
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
					storePurchaseBillProcesTabProcessQtyAndUnitCost();
					commonFunc.hardWait(1000);
					flag = webDB.waitForElement(PurchaseBillsGrnLocators.PB_Process_Close, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseBillsGrnLocators.PB_Process_Close, ElementType.Xpath);
						webDB.switchToDefaultContent();
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(1000);
					}
				}
			}
		}
		assertTrue(flag);
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
			deviationCalculationCompareExcelSheetWithDataBase(webDB.getDataFromProperties("PurchaseBillDeviation"));
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
	 * This Method Used To Verify To Create Journal And Verify Deviation Post
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyCreateJournalAndVerifyDeviationAmtWithTaxCalculationAndPost(String Sheetname) {
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
			deviationWithTaxCalculationCompareExcelSheetWithDataBase(Sheetname);
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
	 * This Method used For Registered Supplier with Local GST- Against Subcontract
	 * With Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalGSTAgainstSubcontractWithDeviation() {
		SubcontractFunc.directSubcontractorderToGRNTypePRWithTDS();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("PurchaseBillDeviation"));
		verifyCreateJournalAndVerifyDeviationAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Methods Used To Get UnitCost In Excel Sheet
	 * 
	 * @author Vimal - RMITS
	 */
	public void getUnitcostInExcelSheet(String Sheetname) {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, Sheetname);
		for (int i = 5; i < ItemMultiple.length; i++) {
			String Unitcost = ItemMultiple[i][2];
			if (!Unitcost.equals("")) {
				flag = webDB.waitForElement(PurchaseBillsGrnLocators.PBCostField, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsGrnLocators.PBCostField, ElementType.Xpath);
					webDB.clearText(PurchaseBillsGrnLocators.PBCostInput, ElementType.Xpath);
					webDB.sendTextToAnElement(PurchaseBillsGrnLocators.PBCostInput, Unitcost, ElementType.Xpath);
					logs.logging("INFO", "Entered Deviation Cost Is:" + Unitcost);
					commonFunc.hardWait(3000);
//					webDB.pressTabKey();
				}
			}
		}
	}

	/**
	 * This Method Used To Verify Expense Type in Purchase Bill
	 * 
	 * @param GLAccount
	 * @param Type
	 * @author Vimal - RMITS
	 */
	public void verifyAddExpenseTypeUnitcostinExcelSheet(String GLAccount, String Type) {
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
					commonFunc.searchAndSelectValueInFilters(PurchaseBillsGrnLocators.GlAccLov, GLAccount);
					logs.logging("INFO", "GL Account Is Entered:" + GLAccount);
					getUnitcostInExcelSheet(webDB.getDataFromProperties("PurchaseBillDeviationandExp"));
					PurBillFunc.verifyClickingHSNCode(commonFunc.getDataFromTestDataSheet("Hsn_code"));
					PurBillFunc.verifySaveLine();
					commonFunc.checkRefreshCellsDisappear();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify To Create Journal And Verify Deviation And Exp
	 * Post
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyCreateJournalAndVerifyDeviationAndExpAmtAndPost() {
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
			commonFunc.hardWait(2000);
			deviationAndExpCalculationCompareExcelSheetWithDataBase(
					webDB.getDataFromProperties("PurchaseBillDeviationandExp"));
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
	 * This Method Used For deviation And Exp Calculation Compare Excel Sheet With
	 * DataBase
	 * 
	 * @author Vimal-RMITS
	 */
	public void deviationAndExpCalculationCompareExcelSheetWithDataBase(String Sheetname) {
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, Sheetname);
		System.out.println(itemmultiple.length);
		for (int i = 6; i < itemmultiple.length; i++) {
			String MatchedAmt = itemmultiple[i][4];
			String GRNAmt = itemmultiple[i][1];
			String deviationAmt = itemmultiple[i][5];
			System.out.println(MatchedAmt);
			if (!MatchedAmt.equals("")) {
				salesOrderFunctions
						.verifyAssertionBothValues(dbCommonFunctions.PBReferenceQuery(VariableHandler.getPbNumber(),
								commonFunc.getDataFromTestDataSheet("PBDBREFMATCHEDAMTCOLUMN")), MatchedAmt);
				System.out.println(commonFunc.getDataFromTestDataSheet("PBDBREFMATCHEDAMTCOLUMN"));
				logs.logging("INFO", "MatchedAmt Details Matched With DataBase Values");

//				salesOrderFunctions
//						.verifyAssertionBothValues(dbCommonFunctions.PBReferenceQuery(VariableHandler.getPbNumber(),
//								commonFunc.getDataFromTestDataSheet("PBDBREFGRNAMTCOLUMN")), GRNAmt);
//				logs.logging("INFO", "GRNAmt Details Matched With DataBase Values");

				salesOrderFunctions
						.verifyAssertionBothValues(
								dbCommonFunctions.PBReferenceQuery(VariableHandler.getPbNumber(),
										commonFunc.getDataFromTestDataSheet("PBDBREFDEVIATIONAMTCOLUMN")),
								deviationAmt);
				logs.logging("INFO", "PBUnitcost Details Matched With DataBase Values");
				commonFunc.hardWait(2000);
			}
		}
	}

	/**
	 * Registered Supplier with Local GST- Against Subcontract With Deviation and
	 * Expenses Account And adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalGSTAgainstSubcontractWithDeviationandExpensesAccountAndadjustment() {
		SubcontractFunc.directSubcontractorderToGRNTypePRWithTDS();
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"), commonFunc.getDataFromTestDataSheet("SupplierForTDS"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("PurchaseBillDeviationandExp"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		purchaseBillsFunc.ClickSaveButton();
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndVerifyDeviationAndExpAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Registered Supplier with Local GST- Against Subcontract
	 * Without Deviation and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalGSTAgainstSubcontractWithoutDeviationandExpensesAccount() {
		SubcontractFunc.directSubcontractorderToGRNTypePRWithTDS();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		PurBillFunc.verifyCreatePost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * Navigate To Reference Tab And Validating RCM Flag
	 * 
	 * @author Vimal - RMITS
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @return
	 * @throws Exception
	 */
	public boolean VerifyToClickReferenceFieldAndClickRcmFlag(String SheetName) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.ReferenceTab, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Grn Is Displayed");
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.ReferenceTab, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.AllowRcmflag, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "RCM Flag Displayed");

				webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.AllowRcmflag, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.OverAllSaveBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "RCMt Flag Successfully Clicked");
					validAllowRcmCheckToWriteExcelValue(SheetName);
					commonFunc.checkRefreshCellsDisappear();
					webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.OverAllSaveBtn, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.InvoiceLineTab, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "OverAll Save Button Successfully Clicked");

						webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.InvoiceLineTab, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBAddLine, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Invoice Tab Successfully Clicked");
						}
					}
				}
			}
		}
		assertTrue(flag);
		return flag;
	}

	/**
	 * Navigate To Reference Tab And Validating IGST Flag
	 * 
	 * @author Vimal - RMITS
	 */
	public boolean VerifyToClickReferenceFieldAndClickIGSTFlag(String SheetName) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.ReferenceTab, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Grn Is Displayed");
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.ReferenceTab, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.AllowIgstFlag, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "RCM Flag Displayed");

				webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.AllowIgstFlag, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.OverAllSaveBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "IGST Flag Successfully Clicked");
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(2000);
					validAllowIGSTCheckToWriteExcelValue(SheetName);
					commonFunc.checkRefreshCellsDisappear();
					webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.OverAllSaveBtn, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.InvoiceLineTab, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "OverAll Save Button Successfully Clicked");
						webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.InvoiceLineTab, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBAddLine, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Invoice Tab Successfully Clicked");
						}
					}
				}
			}
		}
		assertTrue(flag);
		return flag;
	}

	/**
	 * Navigate To Reference Tab And Validating UnRegistered IGST Flag
	 * 
	 * @author Vimal - RMITS
	 */
	public boolean VerifyToClickReferenceFieldAndClickUnRegisteredIGSTFlag(String SheetName) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.ReferenceTab, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Grn Is Displayed");
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.ReferenceTab, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.AllowIgstFlag, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "RCM Flag Displayed");

				webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.AllowIgstFlag, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.OverAllSaveBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "IGST Flag Successfully Clicked");
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(2000);
					validAllowUnregisterdIGSTCheckToWriteExcelValue(SheetName);
					commonFunc.checkRefreshCellsDisappear();
					webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.OverAllSaveBtn, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.InvoiceLineTab, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "OverAll Save Button Successfully Clicked");
						webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.InvoiceLineTab, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBAddLine, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Invoice Tab Successfully Clicked");
						}
					}
				}
			}
		}
		assertTrue(flag);
		return flag;
	}

	/**
	 * Valid Allow RCM Check To Write Excel Value
	 * 
	 * @author Vimal - RMITS
	 */
	public void validAllowRcmCheckToWriteExcelValue(String SheetName) {
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.AllowRcmflag, ElementType.Xpath);
		if (flag) {
			boolean isIgstChecked = webDB.isElementSelected(PurchaseBillsLocatorsGetDocument.AllowRCMFlagEnabled,
					ElementType.Xpath);
			if (isIgstChecked) {
				logs.logging("INFO", "IGST checkbox is checked");
				VerifyToWriteExcelValueInNumberRcmField(SheetName);
				logs.logging("INFO", "SucessFully Written");
			} else {
				logs.logging("INFO", "Tax Amount" + "Not Checked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Valid Allow IGST Check To Write Excel Value
	 * 
	 * @author Vimal - RMITS
	 */
	public void validAllowIGSTCheckToWriteExcelValue(String SheetName) {
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.AllowIgstFlag, ElementType.Xpath);
		if (flag) {
			boolean isIgstChecked = webDB.isElementSelected(PurchaseBillsLocatorsGetDocument.AllowIgstFlagEnabled,
					ElementType.Xpath);
			if (isIgstChecked) {
				logs.logging("INFO", "IGST checkbox is checked");
				VerifyToWriteExcelValueInNumberIGSTField(SheetName);
				logs.logging("INFO", "SucessFully Written");
			} else {
				logs.logging("INFO", "Tax Amount" + "Not Checked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Valid Allow Unregistered IGST Check To Write Excel Value
	 * 
	 * @author Vimal - RMITS
	 */
	public void validAllowUnregisterdIGSTCheckToWriteExcelValue(String SheetName) {
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.AllowIgstFlag, ElementType.Xpath);
		if (flag) {
			boolean isIgstChecked = webDB.isElementSelected(PurchaseBillsLocatorsGetDocument.AllowIgstFlagEnabled,
					ElementType.Xpath);
			if (isIgstChecked) {
				logs.logging("INFO", "IGST checkbox is checked");
				VerifyToWriteExcelValueInNumberUnregisteredIGSTField(SheetName);
				logs.logging("INFO", "SucessFully Written");
			} else {
				logs.logging("INFO", "Tax Amount" + "Not Checked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Valid Allow IGST Check To Write Excel Value
	 * 
	 * @author Vimal - RMITS
	 */
	public void validAllowRCMCheckToWriteExcelValue(String SheetName) {
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.AllowRcmflag, ElementType.Xpath);
		if (flag) {
			boolean isIgstChecked = webDB.isElementSelected(PurchaseBillsLocatorsGetDocument.AllowRCMFlagEnabled,
					ElementType.Xpath);
			if (isIgstChecked) {
				logs.logging("INFO", "IGST checkbox is checked");
				VerifyToWriteExcelValueInNumberRcmField(SheetName);
				logs.logging("INFO", "SucessFully Written");
			} else {
				logs.logging("INFO", "Tax Amount" + "Not Checked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify To Write Excel Value In Number Rcm Field
	 * 
	 * @author Vimal - RMITS
	 */

	public void VerifyToWriteExcelValueInNumberIGSTField(String SheetName) {
//		ExcelUtils excelUtils = new ExcelUtils();
//		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
//				+ "DBSales.xlsx";
//		excelUtils.writeDataInExcel(Filepath, SheetName, 1, 3, "2");
		String filePath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";

		writeDataAndForceExcelRecalc(filePath, SheetName, 1, 3, 2);
	}

	/**
	 * Verify To Write Excel Value In Number Rcm Field
	 * 
	 * @author Vimal - RMITS
	 */

	public void VerifyToWriteExcelValueInNumberUnregisteredIGSTField(String SheetName) {
//		ExcelUtils excelUtils = new ExcelUtils();
//		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
//				+ "DBSales.xlsx";
//		excelUtils.writeDataInExcel(Filepath, SheetName, 1, 3, "2");
		String filePath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";

		writeDataAndForceExcelRecalc(filePath, SheetName, 1, 9, 2);
	}

	/**
	 * Verify To Write Excel Value In Number Rcm Field
	 * 
	 * @author Vimal - RMITS
	 */

	public void VerifyToWriteExcelValueInNumberRcmField(String SheetName) {
//		ExcelUtils excelUtils = new ExcelUtils();
//		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
//				+ "DBSales.xlsx";
//		excelUtils.writeDataInExcel(Filepath, SheetName, 1, 3, "2");
		String filePath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";

		writeDataAndForceExcelRecalc(filePath, SheetName, 1, 9, 2);
	}

	/**
	 * Verify Create Direct PO Adding Parameter
	 * 
	 * @author PremKumar.K RMIT
	 * @param LocationId
	 * @param SupplierName
	 * @param POType
	 * @throws Exception
	 */

	public void writeDataAndForceExcelRecalc(String filePath, String sheetName, int rowNum, int colNum, double value) {
		try {
			FileInputStream fis = new FileInputStream(filePath);
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheet(sheetName);

			Row row = sheet.getRow(rowNum);
			if (row == null)
				row = sheet.createRow(rowNum);

			Cell cell = row.getCell(colNum);
			if (cell == null)
				cell = row.createCell(colNum);

			cell.setCellValue(value);

			workbook.setForceFormulaRecalculation(true);

			fis.close();

			FileOutputStream fos = new FileOutputStream(filePath);
			workbook.write(fos);
			fos.close();
			workbook.close();

			System.out.println("Successfully written and marked for recalculation");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Registered Supplier with Local GST (Allow IGST tax) - Against Subcontract
	 * With Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithDeviation() {
		SubcontractFunc.createProductionOrderFromPendingSubcontractorderLocalRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		VerifyToClickReferenceFieldAndClickIGSTFlag(webDB.getDataFromProperties("DevTaxCalculation"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("DevTaxCalculation"));
		verifyCreateJournalAndVerifyDeviationAmtWithTaxCalculationAndPost(
				webDB.getDataFromProperties("DevTaxCalculation"));
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * Registered Supplier with Local GST (Allow IGST tax) - Against Subcontract
	 * With Deviation and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithDeviationandExpensesAccount() {
		SubcontractFunc.createProductionOrderFromPendingSubcontractorderLocalRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		VerifyToClickReferenceFieldAndClickIGSTFlag(webDB.getDataFromProperties("DevandExpTaxCalculation"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("DevandExpTaxCalculation"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		verifyCreateJournalAndVerifyDeviationAmtWithTaxCalculationAndPost(
				webDB.getDataFromProperties("DevandExpTaxCalculation"));
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * Registered Supplier with Local GST (Allow IGST tax) - Against Subcontract
	 * With Deviation and Expenses Account And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithDeviationandExpensesAccountAndAdjustment() {
		SubcontractFunc.createProductionOrderFromPendingSubcontractorderLocalRegistered();
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalRegistered"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		getBankPaymentAmountandWriteInExcelSheet(webDB.getDataFromProperties("DevandExpTaxCalculation"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		VerifyToClickReferenceFieldAndClickIGSTFlag(webDB.getDataFromProperties("DevandExpTaxCalculation"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("DevandExpTaxCalculation"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		purchaseBillsFunc.ClickSaveButton();
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndVerifyDeviationAmtWithTaxCalculationAndPost(
				webDB.getDataFromProperties("DevandExpTaxCalculation"));
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		afterPurchaseBillPostedVerifyingBalanceAmount(webDB.getDataFromProperties("DevandExpTaxCalculation"));
	}

	/**
	 * Registered Supplier with Local GST (Allow IGST tax) - Against Subcontract
	 * Without Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithoutDeviation() {
		SubcontractFunc.createProductionOrderFromPendingSubcontractorderLocalRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		VerifyToClickReferenceFieldAndClickIGSTFlag(webDB.getDataFromProperties("DevTaxCalculation"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("DevTaxCalculation"));
		verifyCreateJournalAndVerifyDeviationAmtWithTaxCalculationAndPost(
				webDB.getDataFromProperties("DevTaxCalculation"));
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * Registered Supplier with Local GST (Allow IGST tax) - Against Subcontract
	 * Without Deviation and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithoutDeviationandExpensesAccount() {
		SubcontractFunc.createProductionOrderFromPendingSubcontractorderLocalRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		VerifyToClickReferenceFieldAndClickIGSTFlag(webDB.getDataFromProperties("DevandExpTaxCalculation"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("DevandExpTaxCalculation"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		verifyCreateJournalAndVerifyDeviationAmtWithTaxCalculationAndPost(
				webDB.getDataFromProperties("DevandExpTaxCalculation"));
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * Registered Supplier with Local GST (Allow IGST tax) - Against Subcontract
	 * Without Deviation and Expenses Account And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithoutDeviationandExpensesAccountAndAdjustment() {
		SubcontractFunc.createProductionOrderFromPendingSubcontractorderLocalRegistered();
		getSupplierGLAcctFromDB(commonFunc.getDataFromTestDataSheet("SupplierLocalRegistered"));
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalRegistered"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		getBankPaymentAmountandWriteInExcelSheet(webDB.getDataFromProperties("DevandExpTaxCalculation"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		VerifyToClickReferenceFieldAndClickIGSTFlag(webDB.getDataFromProperties("DevandExpTaxCalculation"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("DevandExpTaxCalculation"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		purchaseBillsFunc.ClickSaveButton();
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndVerifyDeviationAmtWithTaxCalculationAndPost(
				webDB.getDataFromProperties("DevandExpTaxCalculation"));
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		afterPurchaseBillPostedVerifyingBalanceAmount(webDB.getDataFromProperties("DevandExpTaxCalculation"));
	}

	/**
	 * Navigate To Reference Tab And Validating RCM Flag
	 * 
	 * @author Vimal - RMITS
	 */
	public boolean VerifyToClickReferenceFieldAndClickRCMFlag(String SheetName) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.ReferenceTab, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Grn Is Displayed");
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.ReferenceTab, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.AllowRcmflag, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "RCM Flag Displayed");

				webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.AllowRcmflag, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.OverAllSaveBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "RCM Flag Successfully Clicked");
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(2000);
					validAllowRCMCheckToWriteExcelValue(SheetName);
					commonFunc.checkRefreshCellsDisappear();
					webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.OverAllSaveBtn, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.InvoiceLineTab, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "OverAll Save Button Successfully Clicked");
						webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.InvoiceLineTab, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBAddLine, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Invoice Tab Successfully Clicked");
						}
					}
				}
			}
		}
		assertTrue(flag);
		return flag;
	}

	/**
	 * Registered Supplier with Local GST (Allow RCM tax) - Against Subcontract With
	 * Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalAllowRCMtaxAgainstSubcontractWithDeviation() {
		SubcontractFunc.createProductionOrderFromPendingSubcontractorderLocalRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		VerifyToClickReferenceFieldAndClickRCMFlag(webDB.getDataFromProperties("DevRCMTaxCalculation"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("DevRCMTaxCalculation"));
		verifyCreateJournalAndVerifyDeviationAmtWithTaxCalculationAndPost(
				webDB.getDataFromProperties("DevRCMTaxCalculation"));
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * Registered Supplier with Local GST (Allow RCM tax) - Against Subcontract With
	 * Deviation and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalGSTAllowRCMtaxAgainstSubcontractWithDeviationandExpensesAccount() {
		SubcontractFunc.createProductionOrderFromPendingSubcontractorderLocalRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		VerifyToClickReferenceFieldAndClickRCMFlag(webDB.getDataFromProperties("DevandExpRCMTaxCalculation"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("DevandExpRCMTaxCalculation"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		verifyCreateJournalAndVerifyDeviationAmtWithTaxCalculationAndPost(
				webDB.getDataFromProperties("DevandExpRCMTaxCalculation"));
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * Registered Supplier with Local GST (Allow RCM tax) - Against Subcontract With
	 * Deviation and Expenses Account And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalGSTAllowRCMtaxAgainstSubcontractWithDeviationandExpensesAccountAndAdjustment() {
		SubcontractFunc.createProductionOrderFromPendingSubcontractorderLocalRegistered();
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalRegistered"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		getBankPaymentAmountandWriteInExcelSheet(webDB.getDataFromProperties("DevandExpRCMTaxCalculation"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		VerifyToClickReferenceFieldAndClickRCMFlag(webDB.getDataFromProperties("DevandExpRCMTaxCalculation"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("DevandExpRCMTaxCalculation"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		purchaseBillsFunc.ClickSaveButton();
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndVerifyDeviationAmtWithTaxCalculationAndPost(
				webDB.getDataFromProperties("DevandExpRCMTaxCalculation"));
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		afterPurchaseBillPostedVerifyingBalanceAmount(webDB.getDataFromProperties("DevandExpRCMTaxCalculation"));
	}

	/**
	 * Registered Supplier with Local GST (Allow RCM tax) - Against Subcontract
	 * Without Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalGSTAllowRCMtaxAgainstSubcontractWithoutDeviation() {
		SubcontractFunc.createProductionOrderFromPendingSubcontractorderLocalRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		VerifyToClickReferenceFieldAndClickRCMFlag(webDB.getDataFromProperties("DevRCMTaxCalculation"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("DevRCMTaxCalculation"));
		verifyCreateJournalAndVerifyDeviationAmtWithTaxCalculationAndPost(
				webDB.getDataFromProperties("DevRCMTaxCalculation"));
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * Registered Supplier with Local GST (Allow RCM tax) - Against Subcontract
	 * Without Deviation and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalGSTAllowRCMtaxAgainstSubcontractWithoutDeviationandExpensesAccount() {
		SubcontractFunc.createProductionOrderFromPendingSubcontractorderLocalRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		VerifyToClickReferenceFieldAndClickRCMFlag(webDB.getDataFromProperties("DevandExpRCMTaxCalculation"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("DevandExpRCMTaxCalculation"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		verifyCreateJournalAndVerifyDeviationAmtWithTaxCalculationAndPost(
				webDB.getDataFromProperties("DevandExpRCMTaxCalculation"));
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * Registered Supplier with Local GST (Allow RCM tax) - Against Subcontract
	 * Without Deviation and Expenses Account And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalGSTAllowRCMtaxAgainstSubcontractWithoutDeviationandExpensesAccountAndAdjustment() {
		SubcontractFunc.createProductionOrderFromPendingSubcontractorderLocalRegistered();
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalRegistered"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		getBankPaymentAmountandWriteInExcelSheet(webDB.getDataFromProperties("DevandExpRCMTaxCalculation"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		VerifyToClickReferenceFieldAndClickRCMFlag(webDB.getDataFromProperties("DevandExpRCMTaxCalculation"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("DevandExpRCMTaxCalculation"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		purchaseBillsFunc.ClickSaveButton();
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndVerifyDeviationAmtWithTaxCalculationAndPost(
				webDB.getDataFromProperties("DevandExpRCMTaxCalculation"));
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		afterPurchaseBillPostedVerifyingBalanceAmount(webDB.getDataFromProperties("DevandExpRCMTaxCalculation"));
	}

	/**
	 * This Method used For Registered Supplier with Interstate GST- Against
	 * Subcontract With Deviation and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithInterstateGSTAgainstSubcontractWithDeviationandExpensesAccount() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInterstateRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("PurchaseBillDeviationandExp"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		verifyCreateJournalAndVerifyDeviationAndExpAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Registered Supplier with Interstate GST- Against
	 * Subcontract Without Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithInterstateGSTAgainstSubcontractWithoutDeviation() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInterstateRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		PurBillFunc.pendingSubcontractAgainstvalidateHeaderDetails();
		PurBillFunc.verifyCreateJournalAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * Registered Supplier with Interstate GST- Against Subcontract Without
	 * Deviation and Expenses Account And adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithInterstateGSTAgainstSubcontractWithoutDeviationandExpensesAccountAndadjustment() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInterstateRegistered();
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"),
				commonFunc.getDataFromTestDataSheet("SuncontractInterstateSupplier"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		PurBillFunc.pendingSubcontractAgainstvalidateHeaderDetails();
		purchaseBillsFunc
				.verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("PurchaseBills_ExAcc"));
		purchaseBillsFunc.ClickSaveButton();
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		purchaseBillsFunc.createJvForRecord();
		PurBillFunc.verifyCreatePost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Registered Supplier with Interstate GST- Against
	 * Subcontract With Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithInterstateGSTAgainstSubcontractWithDeviation() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInterstateRegistered();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("PurchaseBillDeviation"));
		verifyCreateJournalAndVerifyDeviationAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * Registered Supplier with Interstate GST- Against Subcontract With Deviation
	 * and Expenses Account And adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithInterstateGSTAgainstSubcontractWithDeviationandExpensesAccountAndadjustment() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInterstateRegistered();
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"),
				commonFunc.getDataFromTestDataSheet("SuncontractInterstateSupplier"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("PurchaseBillDeviationandExp"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		purchaseBillsFunc.ClickSaveButton();
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndVerifyDeviationAndExpAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Registered Supplier with Interstate GST- Against
	 * Subcontract Without Deviation and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithInterstateGSTAgainstSubcontractWithoutDeviationandExpensesAccount() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInterstateRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		PurBillFunc.verifyCreatePost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * Registered Supplier with Interstate GST (Allow RCM tax) - Against Subcontract
	 * With Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithInterstateAllowRCMtaxAgainstSubcontractWithDeviation() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInterstateRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		VerifyToClickReferenceFieldAndClickRCMFlag(webDB.getDataFromProperties("DevInterRCMTaxCalculation"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("DevInterRCMTaxCalculation"));
		verifyCreateJournalAndVerifyDeviationAmtWithTaxCalculationAndPost(
				webDB.getDataFromProperties("DevInterRCMTaxCalculation"));
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * Registered Supplier with Local GST (Allow RCM tax) - Against Subcontract With
	 * Deviation and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithInterstateGSTAllowRCMtaxAgainstSubcontractWithDeviationandExpensesAccount() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInterstateRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		VerifyToClickReferenceFieldAndClickRCMFlag(webDB.getDataFromProperties("DevandExpInterRCMTaxCalculation"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("DevandExpInterRCMTaxCalculation"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		verifyCreateJournalAndVerifyDeviationAmtWithTaxCalculationAndPost(
				webDB.getDataFromProperties("DevandExpInterRCMTaxCalculation"));
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * Registered Supplier with Interstate GST (Allow RCM tax) - Against Subcontract
	 * With Deviation and Expenses Account And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithInterstateGSTAllowRCMtaxAgainstSubcontractWithDeviationandExpensesAccountAndAdjustment() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInterstateRegistered();
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"),
				commonFunc.getDataFromTestDataSheet("SuncontractInterstateSupplier"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		getBankPaymentAmountandWriteInExcelSheet(webDB.getDataFromProperties("DevandExpInterRCMTaxCalculation"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		VerifyToClickReferenceFieldAndClickRCMFlag(webDB.getDataFromProperties("DevandExpInterRCMTaxCalculation"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("DevandExpInterRCMTaxCalculation"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		purchaseBillsFunc.ClickSaveButton();
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndVerifyDeviationAmtWithTaxCalculationAndPost(
				webDB.getDataFromProperties("DevandExpInterRCMTaxCalculation"));
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		afterPurchaseBillPostedVerifyingBalanceAmount(webDB.getDataFromProperties("DevandExpInterRCMTaxCalculation"));
	}

	/**
	 * Registered Supplier with Interstate GST (Allow RCM tax) - Against Subcontract
	 * Without Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithInterstateGSTAllowRCMtaxAgainstSubcontractWithoutDeviation() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInterstateRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		VerifyToClickReferenceFieldAndClickRCMFlag(webDB.getDataFromProperties("DevInterRCMTaxCalculation"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("DevInterRCMTaxCalculation"));
		verifyCreateJournalAndVerifyDeviationAmtWithTaxCalculationAndPost(
				webDB.getDataFromProperties("DevInterRCMTaxCalculation"));
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * Registered Supplier with Interstate GST (Allow RCM tax) - Against Subcontract
	 * Without Deviation and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithInterstateGSTAllowRCMtaxAgainstSubcontractWithoutDeviationandExpensesAccount() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInterstateRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		VerifyToClickReferenceFieldAndClickRCMFlag(webDB.getDataFromProperties("DevandExpInterRCMTaxCalculation"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("DevandExpInterRCMTaxCalculation"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		verifyCreateJournalAndVerifyDeviationAmtWithTaxCalculationAndPost(
				webDB.getDataFromProperties("DevandExpInterRCMTaxCalculation"));
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * Registered Supplier with Interstate GST (Allow RCM tax) - Against Subcontract
	 * Without Deviation and Expenses Account And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithInterstateGSTAllowRCMtaxAgainstSubcontractWithoutDeviationandExpensesAccountAndAdjustment() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInterstateRegistered();
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"),
				commonFunc.getDataFromTestDataSheet("SuncontractInterstateSupplier"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		getBankPaymentAmountandWriteInExcelSheet(webDB.getDataFromProperties("DevandExpInterRCMTaxCalculation"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		VerifyToClickReferenceFieldAndClickRCMFlag(webDB.getDataFromProperties("DevandExpInterRCMTaxCalculation"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("DevandExpInterRCMTaxCalculation"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		purchaseBillsFunc.ClickSaveButton();
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndVerifyDeviationAmtWithTaxCalculationAndPost(
				webDB.getDataFromProperties("DevandExpInterRCMTaxCalculation"));
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		afterPurchaseBillPostedVerifyingBalanceAmount(webDB.getDataFromProperties("DevandExpInterRCMTaxCalculation"));
	}

	/**
	 * This Method used For Unregistered Supplier with Local GST- Against
	 * Subcontract With Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	public void unregisteredSupplierwithLocalGSTAgainstSubcontractWithDeviation() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeLocalUnRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("PurchaseBillDeviation"));
		verifyCreateJournalAndVerifyDeviationAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Unregistered Supplier with Local GST- Against
	 * Subcontract With Deviation and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	public void unregisteredSupplierwithLocalGSTAgainstSubcontractWithDeviationandExpensesAccount() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeLocalUnRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("PurchaseBillDeviationandExp"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		verifyCreateJournalAndVerifyDeviationAndExpAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * Unregistered Supplier with Local GST- Against Subcontract With Deviation and
	 * Expenses Account And adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	public void unregisteredSupplierwithLocalGSTAgainstSubcontractWithDeviationandExpensesAccountAndadjustment() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeLocalUnRegistered();
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalUnRegistered"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("PurchaseBillDeviationandExp"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		purchaseBillsFunc.ClickSaveButton();
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndVerifyDeviationAndExpAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For UnRegistered Supplier with Local GST- Against
	 * Subcontract Without Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	public void unregisteredSupplierwithLocalGSTAgainstSubcontractWithoutDeviation() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeLocalUnRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		PurBillFunc.pendingSubcontractAgainstvalidateHeaderDetails();
		PurBillFunc.verifyCreateJournalAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For UnRegistered Supplier with Local GST- Against
	 * Subcontract Without Deviation and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	public void unregisteredSupplierwithLocalGSTAgainstSubcontractWithoutDeviationandExpensesAccount() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeLocalUnRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		PurBillFunc.verifyCreatePost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * UnRegistered Supplier with Local GST- Against Subcontract Without Deviation
	 * and Expenses Account And adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	public void unregisteredSupplierwithLocalGSTAgainstSubcontractWithoutDeviationandExpensesAccountAndadjustment() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeLocalUnRegistered();
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalUnRegistered"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		PurBillFunc.pendingSubcontractAgainstvalidateHeaderDetails();
		purchaseBillsFunc
				.verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("PurchaseBills_ExAcc"));
		purchaseBillsFunc.ClickSaveButton();
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		purchaseBillsFunc.createJvForRecord();
		PurBillFunc.verifyCreatePost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * UnRegistered Supplier with Local GST (Allow IGST tax) - Against Subcontract
	 * With Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	public void unregisteredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithDeviation() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeLocalUnRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		VerifyToClickReferenceFieldAndClickUnRegisteredIGSTFlag(
				webDB.getDataFromProperties("DevTaxLocalUnregCalculation"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("DevTaxLocalUnregCalculation"));
		verifyCreateJournalAndVerifyDeviationAmtWithTaxCalculationAndPost(
				webDB.getDataFromProperties("DevTaxLocalUnregCalculation"));
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * UnRegistered Supplier with Local GST (Allow IGST tax) - Against Subcontract
	 * With Deviation and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	public void unregisteredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithDeviationandExpensesAccount() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeLocalUnRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		VerifyToClickReferenceFieldAndClickUnRegisteredIGSTFlag(
				webDB.getDataFromProperties("DevandExpTaxLocUnregCalculation"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("DevandExpTaxLocUnregCalculation"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		verifyCreateJournalAndVerifyDeviationAmtWithTaxCalculationAndPost(
				webDB.getDataFromProperties("DevandExpTaxLocUnregCalculation"));
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * UnRegistered Supplier with Local GST (Allow IGST tax) - Against Subcontract
	 * With Deviation and Expenses Account And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	public void unregisteredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithDeviationandExpensesAccountAndAdjustment() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeLocalUnRegistered();
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalUnRegistered"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		getBankPaymentAmountandWriteInExcelSheet(webDB.getDataFromProperties("DevandExpTaxLocUnregCalculation"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		VerifyToClickReferenceFieldAndClickUnRegisteredIGSTFlag(
				webDB.getDataFromProperties("DevandExpTaxLocUnregCalculation"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("DevandExpTaxLocUnregCalculation"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		purchaseBillsFunc.ClickSaveButton();
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndVerifyDeviationAmtWithTaxCalculationAndPost(
				webDB.getDataFromProperties("DevandExpTaxLocUnregCalculation"));
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		afterPurchaseBillPostedVerifyingBalanceAmount(webDB.getDataFromProperties("DevandExpTaxLocUnregCalculation"));
	}

	/**
	 * UnRegistered Supplier with Local GST (Allow IGST tax) - Against Subcontract
	 * Without Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	public void unregisteredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithoutDeviation() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeLocalUnRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		VerifyToClickReferenceFieldAndClickUnRegisteredIGSTFlag(
				webDB.getDataFromProperties("DevTaxLocalUnregCalculation"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("DevTaxLocalUnregCalculation"));
		verifyCreateJournalAndVerifyDeviationAmtWithTaxCalculationAndPost(
				webDB.getDataFromProperties("DevTaxLocalUnregCalculation"));
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * UnRegistered Supplier with Local GST (Allow IGST tax) - Against Subcontract
	 * Without Deviation and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	public void unregisteredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithoutDeviationandExpensesAccount() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeLocalUnRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		VerifyToClickReferenceFieldAndClickUnRegisteredIGSTFlag(
				webDB.getDataFromProperties("DevandExpTaxLocUnregCalculation"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("DevandExpTaxLocUnregCalculation"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		verifyCreateJournalAndVerifyDeviationAmtWithTaxCalculationAndPost(
				webDB.getDataFromProperties("DevandExpTaxLocUnregCalculation"));
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * UnRegistered Supplier with Local GST (Allow IGST tax) - Against Subcontract
	 * Without Deviation and Expenses Account And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	public void unregisteredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractWithoutDeviationandExpensesAccountAndAdjustment() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeLocalUnRegistered();
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalUnRegistered"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		getBankPaymentAmountandWriteInExcelSheet(webDB.getDataFromProperties("DevandExpTaxLocUnregCalculation"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		VerifyToClickReferenceFieldAndClickUnRegisteredIGSTFlag(
				webDB.getDataFromProperties("DevandExpTaxLocUnregCalculation"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("DevandExpTaxLocUnregCalculation"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		purchaseBillsFunc.ClickSaveButton();
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndVerifyDeviationAmtWithTaxCalculationAndPost(
				webDB.getDataFromProperties("DevandExpTaxLocUnregCalculation"));
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		afterPurchaseBillPostedVerifyingBalanceAmount(webDB.getDataFromProperties("DevandExpTaxLocUnregCalculation"));
	}

	/**
	 * This Method used For Unregistered Supplier with Interstate GST- Against
	 * Subcontract With Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	public void unregisteredSupplierwithInterstateGSTAgainstSubcontractWithDeviation() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInterstateUnRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("PurchaseBillDeviation"));
		verifyCreateJournalAndVerifyDeviationAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Unregistered Supplier with Interstate GST- Against
	 * Subcontract With Deviation and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	public void unregisteredSupplierwithInterstateGSTAgainstSubcontractWithDeviationandExpensesAccount() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInterstateUnRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("PurchaseBillDeviationandExp"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		verifyCreateJournalAndVerifyDeviationAndExpAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * Unregistered Supplier with Interstate GST- Against Subcontract With Deviation
	 * and Expenses Account And adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	public void unregisteredSupplierwithInterstateGSTAgainstSubcontractWithDeviationandExpensesAccountAndadjustment() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInterstateUnRegistered();
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"),
				commonFunc.getDataFromTestDataSheet("SupplierInterstateUnRegistered"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("PurchaseBillDeviationandExp"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		purchaseBillsFunc.ClickSaveButton();
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndVerifyDeviationAndExpAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For UnRegistered Supplier with Interstate GST- Against
	 * Subcontract Without Deviation
	 * 
	 * @author Vimal - RMITS
	 */
	public void unregisteredSupplierwithInterstateGSTAgainstSubcontractWithoutDeviation() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInterstateUnRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		PurBillFunc.pendingSubcontractAgainstvalidateHeaderDetails();
		PurBillFunc.verifyCreateJournalAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For UnRegistered Supplier with Interstate GST- Against
	 * Subcontract Without Deviation and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	public void unregisteredSupplierwithInterstateGSTAgainstSubcontractWithoutDeviationandExpensesAccount() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInterstateUnRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		PurBillFunc.verifyCreatePost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * UnRegistered Supplier with Interstate GST- Against Subcontract Without
	 * Deviation and Expenses Account And adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	public void unregisteredSupplierwithInterstateGSTAgainstSubcontractWithoutDeviationandExpensesAccountAndadjustment() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInterstateUnRegistered();
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"),
				commonFunc.getDataFromTestDataSheet("SupplierInterstateUnRegistered"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		PurBillFunc.pendingSubcontractAgainstvalidateHeaderDetails();
		purchaseBillsFunc
				.verifyAddTheItemBasedOnTypeForStockTransfer(webDB.getDataFromProperties("PurchaseBills_ExAcc"));
		purchaseBillsFunc.ClickSaveButton();
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		purchaseBillsFunc.createJvForRecord();
		PurBillFunc.verifyCreatePost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Unregistered Supplier imported with deviation(without
	 * GST)
	 * 
	 * @author Vimal - RMITS
	 */
	public void unregisteredSupplierwithImportedGSTAgainstSubcontractWithDeviation() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeImportedUnRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("PurchaseBillDeviation"));
		verifyCreateJournalAndVerifyDeviationAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Unregistered Supplier imported without deviation(without
	 * GST)
	 * 
	 * @author Vimal - RMITS
	 */
	public void unregisteredSupplierwithImportedGSTAgainstSubcontractWithoutDeviation() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeImportedUnRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		PurBillFunc.pendingSubcontractAgainstvalidateHeaderDetails();
		PurBillFunc.verifyCreateJournalAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Unregistered Supplier imported with deviation and
	 * expenses account(without GST)
	 * 
	 * @author Vimal - RMITS
	 */
	public void unregisteredSupplierwithImportedGSTAgainstSubcontractWithDeviationandExpensesAccount() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeImportedUnRegistered();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("PurchaseBillDeviationandExp"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		verifyCreateJournalAndVerifyDeviationAndExpAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Unregistered Supplier imported with deviation and
	 * expenses account And Adjustment(without GST)
	 * 
	 * @author Vimal - RMITS
	 */
	public void unregisteredSupplierwithImportedGSTAgainstSubcontractWithDeviationandExpensesAccountAndadjustment() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeImportedUnRegistered();
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"),
				commonFunc.getDataFromTestDataSheet("SupplierImportedUnRegistered"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("PurchaseBillDeviationandExp"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		purchaseBillsFunc.ClickSaveButton();
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndVerifyDeviationAndExpAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For NonResident Supplier imported with deviation(without
	 * GST)
	 * 
	 * @author Vimal - RMITS
	 */
	public void nonResidentSupplierwithImportedGSTAgainstSubcontractWithDeviation() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeImportedNonresident();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("PurchaseBillDeviation"));
		verifyCreateJournalAndVerifyDeviationAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For NonResident Supplier imported without deviation(without
	 * GST)
	 * 
	 * @author Vimal - RMITS
	 */
	public void nonResidentSupplierwithImportedGSTAgainstSubcontractWithoutDeviation() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeImportedNonresident();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		PurBillFunc.pendingSubcontractAgainstvalidateHeaderDetails();
		PurBillFunc.verifyCreateJournalAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For NonResident Supplier imported with deviation and
	 * expenses account(without GST)
	 * 
	 * @author Vimal - RMITS
	 */
	public void nonResidentSupplierwithImportedGSTAgainstSubcontractWithDeviationandExpensesAccount() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeImportedNonresident();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("PurchaseBillDeviationandExp"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		verifyCreateJournalAndVerifyDeviationAndExpAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For NonResident Supplier imported with deviation and
	 * expenses account And Adjustment(without GST)
	 * 
	 * @author Vimal - RMITS
	 */
	public void nonResidentSupplierwithImportedGSTAgainstSubcontractWithDeviationandExpensesAccountAndadjustment() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeImportedNonresident();
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"),
				commonFunc.getDataFromTestDataSheet("SupplierImportedNonResident"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("PurchaseBillDeviationandExp"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		purchaseBillsFunc.ClickSaveButton();
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndVerifyDeviationAndExpAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Domestic Registered Supplier - With Local Freight GST
	 * 
	 * @author Vimal - RMITS
	 */
	public void domesticRegisteredSupplierWithLocalFreightGST() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeLocalFreight();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		PurBillFunc.pendingSubcontractAgainstvalidateHeaderDetails();
		PurBillFunc.verifyCreateJournalAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Domestic Registered Supplier - With Union Territory
	 * 
	 * @author Vimal - RMITS
	 */
	public void domesticRegisteredSupplierWithUnionTerritory() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeUnionTerritory();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		PurBillFunc.pendingSubcontractAgainstvalidateHeaderDetails();
		PurBillFunc.verifyCreateJournalAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Domestic Registered Supplier - With Unregistered Local
	 * Freight
	 * 
	 * @author Vimal - RMITS
	 */
	public void domesticRegisteredSupplierWithUnregisteredLocalFreight() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeUnregisteredLocalFreight();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		PurBillFunc.pendingSubcontractAgainstvalidateHeaderDetails();
		PurBillFunc.verifyCreateJournalAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Domestic UnRegistered Supplier - With Union Territory
	 * 
	 * @author Vimal - RMITS
	 */
	public void domesticUnRegisteredSupplierWithUnionTerritory() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeUnregisteredUnionTerritory();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		PurBillFunc.pendingSubcontractAgainstvalidateHeaderDetails();
		PurBillFunc.verifyCreateJournalAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Domestic UnRegistered Supplier - With Composition
	 * 
	 * @author Vimal - RMITS
	 */
	public void domesticRegisteredSupplierWithComposition() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeComposition();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		PurBillFunc.pendingSubcontractAgainstvalidateHeaderDetails();
		PurBillFunc.verifyCreateJournalAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Domestic LocalFreight Supplier - With Composition
	 * 
	 * @author Vimal - RMITS
	 */
	public void domesticRegisteredSupplierWithLocalFreightComposition() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeLocalFreightComposition();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		PurBillFunc.pendingSubcontractAgainstvalidateHeaderDetails();
		PurBillFunc.verifyCreateJournalAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Domestic Interstate Supplier - With Composition
	 * 
	 * @author Vimal - RMITS
	 */
	public void domesticRegisteredSupplierWithInterstateComposition() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInterstateRegisteredComposition();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		PurBillFunc.pendingSubcontractAgainstvalidateHeaderDetails();
		PurBillFunc.verifyCreateJournalAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Domestic UnionTerritory Supplier - With Composition
	 * 
	 * @author Vimal - RMITS
	 */
	public void domesticRegisteredSupplierWithUnionTerritoryComposition() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeUnionTerritoryComposition();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		PurBillFunc.pendingSubcontractAgainstvalidateHeaderDetails();
		PurBillFunc.verifyCreateJournalAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Domestic Interstate Supplier - With CasualPerson
	 * 
	 * @author Vimal - RMITS
	 */
	public void domesticRegisteredSupplierWithInterstateCasualPerson() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInterstateCasualPerson();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		PurBillFunc.pendingSubcontractAgainstvalidateHeaderDetails();
		PurBillFunc.verifyCreateJournalAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Domestic UnionTerritory Supplier - With CasualPerson
	 * 
	 * @author Vimal - RMITS
	 */
	public void domesticRegisteredSupplierWithUnionTerritoryCasualPerson() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeUnionTerritoryCasualPerson();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		PurBillFunc.pendingSubcontractAgainstvalidateHeaderDetails();
		PurBillFunc.verifyCreateJournalAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Domestic Interstate Supplier - With NonResident
	 * 
	 * @author Vimal - RMITS
	 */
	public void domesticRegisteredSupplierWithInterstateNonResident() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeInterstateNonResident();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		PurBillFunc.pendingSubcontractAgainstvalidateHeaderDetails();
		PurBillFunc.verifyCreateJournalAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Domestic UnionTerritory Supplier - With NonResident
	 * 
	 * @author Vimal - RMITS
	 */
	public void domesticRegisteredSupplierWithUnionTerritoryNonResident() {
		SubcontractFunc.createDirectSubcontractOrderWithBOMForStandardTypeUnionTerritoryNonResident();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetails(commonFunc.getDataFromTestDataSheet("TDSValue"));
		PurBillFunc.pendingSubcontractAgainstvalidateHeaderDetails();
		PurBillFunc.verifyCreateJournalAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Methods Used To store Bank Paymen tAmount
	 * 
	 * @author Vimal - RMITS
	 */
	public void storeBankPaymentAmount() {
		commonFunc.hardWait(1000);
		String BPAmount = webDB.getTextFromElement(PurchaseBillsGrnLocators.BankPaymentAmountField, ElementType.Xpath);
		logs.logging("INFO", "Get BPAmount Amt Is: " + BPAmount);
		VariableHandler.setBPAmount(BPAmount);
		commonFunc.hardWait(1000);
	}

	/**
	 * This Methods Used To get Bank Payment Amount and Write In Excel Sheet
	 * 
	 * @author Vimal - RMITS
	 */
	public void getBankPaymentAmountandWriteInExcelSheet(String Sheetname) {
		String[][] ItemMultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";
		ItemMultiple = excelUtils.readAllDataInExcel(Filepath, Sheetname);
		logs.logging("INFO", "Before Process Id ");
		for (int i = 4; i < ItemMultiple.length; i++) {
			String BankPaymentAmt = ItemMultiple[i][0];
			if (!BankPaymentAmt.equals("")) {
				logs.logging("INFO",
						"Before BankPaymentAmt  " + BankPaymentAmt + "Is : " + VariableHandler.getBPAmount());
				excelUtils.writeDataInExcel(Filepath, Sheetname, i, 16, VariableHandler.getBPAmount());
			}
		}
	}

	/**
	 * This Method Used For After PurchaseBill Posted Verifying Balance Amount
	 * 
	 * @author Vimal-RMITS
	 */
	public void afterPurchaseBillPostedVerifyingBalanceAmount(String Sheetname) {
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, Sheetname);
		System.out.println(itemmultiple.length);
		for (int i = 4; i < itemmultiple.length; i++) {
			String BankPaymentAmt = itemmultiple[i][17];
			System.out.println(BankPaymentAmt);
			if (!BankPaymentAmt.equals("")) {
				salesOrderFunctions.verifyAssertionBothValues(dbCommonFunctions.PBQuery(VariableHandler.getPbNumber(),
						commonFunc.getDataFromTestDataSheet("PBDBbBalanceQtyCOLUMN")), BankPaymentAmt);
				System.out.println(commonFunc.getDataFromTestDataSheet("PBDBbBalanceQtyCOLUMN"));
				logs.logging("INFO", "BalanceAmt Details Matched With DataBase Values");
				commonFunc.hardWait(2000);
			}
		}
	}

	/**
	 * This Method Used For Get Supplier GL Acct From DB
	 * 
	 * @author Vimal-RMITS
	 */
	public void getSupplierGLAcctFromDB(String SupplierID) {
		String GlAcct = dbCommonFunc.SupplierGLaccountQuery(SupplierID,
				commonFunc.getDataFromTestDataSheet("SupplierGLacctCOLUMN"));
		logs.logging("INFO", "Get GL Acct :" + GlAcct);
		VariableHandler.setSupGlAccount(GlAcct);
		VariableHandler.getSupGlAccount();
		System.out.println("GetGLAccount" + VariableHandler.getSupGlAccount());

	}

	/**
	 * @author Premkumar.K
	 * @param BillNo
	 * @param PartyID
	 */
	public void createDirectPurchaseBillSubcontractGRNAgainst(String BillNo, String PartyID, String PBREf) {
		String SupBillNo = RandomStringUtils.randomNumeric(6);
		if (SupBillNo.equals("000")) {
			SupBillNo = "10";
		}
		String DCNO = BillNo + SupBillNo;
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.DocAddBtn, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Document Add Button Shown");
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.DocAddBtn, ElementType.Xpath);
			purBillGetDoc.verifyCLickPurchaseBillsFrame();
			commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.SubVouType,
					commonFunc.getDataFromTestDataSheet("PBSubVoucherType"));
			purBillGetDoc.verifyCLickPurchaseBillsFrame();
			commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.LocID,
					commonFunc.getDataFromTestDataSheet("Loc_100"));
			purBillGetDoc.verifyCLickPurchaseBillsFrame();
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBRefType, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownByValue(PurchaseBillsLocatorsGetDocument.PBRefType, PBREf, ElementType.Xpath);
				logs.logging("INFO", "Type Is Entered:" + PBREf);
				commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.PartyID, PartyID);
				purBillGetDoc.verifyCLickPurchaseBillsFrame();
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.BillNumInput, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Document Voucher Type Shown");
					webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.BillNumInput, ElementType.Xpath);
					flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.BillNumInput, ElementType.Xpath);
					if (flag) {
						webDB.sendTextToAnElement(PurchaseBillsLocatorsGetDocument.BillNumInput, DCNO,
								ElementType.Xpath);
						logs.logging("INFO", "Bill Number Inserted SuccessFully");
						flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PurchaseBillTickIcon,
								ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PurchaseBillTickIcon,
									ElementType.Xpath);
							logs.logging("INFO", "Tick Icon Button Clicked Successfully");
							webDB.switchToDefaultContent();
							commonFunc.checkRefreshCellsDisappear();
							flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.GetDoc, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Document Created Successfully");
								PurBillFunc.storePurchaseVoucherNumber();
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used For Inserting Subcontract Process Details
	 * 
	 * @author Vimal - RMITS
	 */
	public void insertingSubcontractProcessDetails(String ProcQty, String ProcCost) {
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBSubcontractTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBSubcontractTab, ElementType.Xpath);
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBSubcontractTabAddIcon, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBSubcontractTabAddIcon, ElementType.Xpath);
				commonFunc.hardWait(1000);
				commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.PBSubcontractTabProcLOV,
						commonFunc.getDataFromTestDataSheet("PBSubcontractProc"));
				commonFunc.hardWait(1000);
				verifyAddTableSpecs(PurchaseBillsLocatorsGetDocument.PBSubcontractTabProcQTy,
						PurchaseBillsLocatorsGetDocument.PBSubcontractTabProcQTyInput, ProcQty);
				commonFunc.checkRefreshCellsDisappear();
				verifyAddTableSpecs(PurchaseBillsLocatorsGetDocument.PBSubcontractTabProcUnitCost,
						PurchaseBillsLocatorsGetDocument.PBSubcontractTabProcCostInput, ProcCost);
				commonFunc.checkRefreshCellsDisappear();
				webDB.pressTabKey();
				commonFunc.hardWait(1000);
				commonFunc.searchAndSelectValueInFilters(PurchaseBillsLocatorsGetDocument.PBSubcontractTabMFGitem,
						commonFunc.getDataFromTestDataSheet("SCOItemID"));
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBSubcontractTabPurchaseAcct,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBSubcontractTabPurchaseAcct,
							ElementType.Xpath);
					commonFunc.hardWait(1000);
					commonFunc.searchAndSelectValueInFilters(
							PurchaseBillsLocatorsGetDocument.PBSubcontractTabPurchaseAcctLOV,
							commonFunc.getDataFromTestDataSheet("ProcessGLacct"));
					commonFunc.hardWait(1000);
					flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBSubcontractTabSAC,
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBSubcontractTabSAC, ElementType.Xpath);
						commonFunc.searchAndSelectValueInFilters(
								PurchaseBillsLocatorsGetDocument.PBSubcontractTabSACLoV,
								commonFunc.getDataFromTestDataSheet("PBSACCode"));
						commonFunc.hardWait(1000);
						webDB.pressTabKey();
						commonFunc.hardWait(1000);
						commonFunc.searchAndSelectValueInFilters(
								PurchaseBillsLocatorsGetDocument.PBSubcontractTabTDSLoV,
								commonFunc.getDataFromTestDataSheet("SubcontractTDS"));
						commonFunc.hardWait(1000);
						webDB.pressTabKey();
						commonFunc.hardWait(2000);
						flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBSubcontractTabSaveIcon,
								ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBSubcontractTabSaveIcon,
									ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(1000);
							flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBSubcontractTabOKbutton,
									ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.PBSubcontractTabOKbutton,
										ElementType.Xpath);
								commonFunc.checkRefreshCellsDisappear();
								commonFunc.hardWait(1000);
								flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBInvoiceTablineWiseDelete,
										ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Subcontract Process Inserted Successfully");
									commonFunc.hardWait(2000);
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
	 * This Method Used For Matched Amount Calculation Compare ExcelSheet With
	 * DataBase Compare Excel Sheet With DataBase
	 * 
	 * @author Vimal-RMITS
	 */
	public void matchedAmountCalculationCompareExcelSheetWithDataBase(String Sheetname) {
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, Sheetname);
		System.out.println(itemmultiple.length);
		for (int i = 4; i < itemmultiple.length; i++) {
			String MatchedAmt = itemmultiple[i][13];
			System.out.println(MatchedAmt);
			if (!MatchedAmt.equals("")) {
				salesOrderFunctions
						.verifyAssertionBothValues(dbCommonFunctions.PBReferenceQuery(VariableHandler.getPbNumber(),
								commonFunc.getDataFromTestDataSheet("PBDBREFMATCHEDAMTCOLUMN")), MatchedAmt);
				System.out.println(commonFunc.getDataFromTestDataSheet("PBDBREFMATCHEDAMTCOLUMN"));
				logs.logging("INFO", "MatchedAmt Details Matched With DataBase Values");
				commonFunc.hardWait(2000);
			}
		}
	}

	/**
	 * This Method Used To Verify To Create Journal And Verify Matched Amount Post
	 * 
	 * @author Vimal - RMITS
	 */
	public void verifyCreateJournalAndVerifyMatchedAmtAndPost() {
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
			commonFunc.hardWait(3000);
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
	 * This Method used For Registered Supplier with Local GST- Subcontract Direct
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalGSTSubcontractDirect() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalRegistered"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		matchedAmountCalculationCompareExcelSheetWithDataBase(webDB.getDataFromProperties("DevTaxLocalRegCalculation"));
	}

	/**
	 * This Method used For Registered Supplier with Local GST- Subcontract Direct
	 * With ExpensesAccount
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalGSTSubcontractDirectWithExpensesAccount() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalRegistered"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		matchedAmountCalculationCompareExcelSheetWithDataBase(
				webDB.getDataFromProperties("DevandExpTaxLocRegCalculation"));
	}

	/**
	 * This Method used For Registered Supplier with Local GST- Subcontract Direct
	 * With ExpensesAccount And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalGSTSubcontractDirectWithExpensesAccountandAdjustment() {
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalRegistered"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalRegistered"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		matchedAmountCalculationCompareExcelSheetWithDataBase(
				webDB.getDataFromProperties("DevandExpTaxLocRegCalculation"));
	}

	/**
	 * Navigate To Reference Tab And Validating IGST Flag
	 * 
	 * @author Vimal - RMITS
	 */
	public boolean VerifyToClickReferenceFieldAndVerifyClickIGSTFlag(String SheetName) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.SCODirectReferenceTab, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Grn Is Displayed");
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.SCODirectReferenceTab, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.AllowIgstFlag, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "RCM Flag Displayed");

				webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.AllowIgstFlag, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.OverAllSaveBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "IGST Flag Successfully Clicked");
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(2000);
					validAllowIGSTCheckToWriteExcelValue(SheetName);
					commonFunc.checkRefreshCellsDisappear();
					webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.OverAllSaveBtn, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.InvoiceLineTab, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "OverAll Save Button Successfully Clicked");
						webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.InvoiceLineTab, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBAddLine, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Invoice Tab Successfully Clicked");
						}
					}
				}
			}
		}
		assertTrue(flag);
		return flag;
	}

	/**
	 * Registered Supplier with Local GST (Allow IGST tax) - Against Subcontract
	 * Direct
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractDirect() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalRegistered"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		VerifyToClickReferenceFieldAndVerifyClickIGSTFlag(webDB.getDataFromProperties("DevTaxCalculation"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		matchedAmountCalculationCompareExcelSheetWithDataBase(webDB.getDataFromProperties("DevTaxCalculation"));
	}

	/**
	 * This Method used For Registered Supplier with Local GST (Allow IGST tax) -
	 * Against Subcontract Direct With ExpensesAccount
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractDirectWithExpensesAccount() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalRegistered"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifyToClickReferenceFieldAndVerifyClickIGSTFlag(webDB.getDataFromProperties("DevandExpTaxCalculation"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		matchedAmountCalculationCompareExcelSheetWithDataBase(webDB.getDataFromProperties("DevandExpTaxCalculation"));
	}

	/**
	 * This Method used For Registered Supplier with Local GST (Allow IGST tax) -
	 * Against Subcontract Direct With ExpensesAccount And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalGSTAllowIGSTtaxAgainstSubcontractDirectWithExpensesAccountandAdjustment() {
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalRegistered"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalRegistered"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		VerifyToClickReferenceFieldAndVerifyClickIGSTFlag(webDB.getDataFromProperties("DevandExpTaxCalculation"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		matchedAmountCalculationCompareExcelSheetWithDataBase(webDB.getDataFromProperties("DevandExpTaxCalculation"));
	}

	/**
	 * Navigate To Reference Tab And Validating RCM Flag
	 * 
	 * @author Vimal - RMITS
	 */
	public boolean VerifyToClickReferenceFieldAndVerifyClickRCMFlag(String SheetName) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.SCODirectReferenceTab, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Grn Is Displayed");
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.SCODirectReferenceTab, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.AllowRcmflag, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "RCM Flag Displayed");

				webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.AllowRcmflag, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.OverAllSaveBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "RCM Flag Successfully Clicked");
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(2000);
					validAllowRCMCheckToWriteExcelValue(SheetName);
					commonFunc.checkRefreshCellsDisappear();
					webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.OverAllSaveBtn, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.InvoiceLineTab, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "OverAll Save Button Successfully Clicked");
						webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.InvoiceLineTab, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBAddLine, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Invoice Tab Successfully Clicked");
						}
					}
				}
			}
		}
		assertTrue(flag);
		return flag;
	}

	/**
	 * Registered Supplier with Local GST (Allow RCM tax) - Against Subcontract
	 * Direct
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalGSTAllowRCMtaxAgainstSubcontractDirect() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalRegistered"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		VerifyToClickReferenceFieldAndVerifyClickRCMFlag(webDB.getDataFromProperties("DirectDevRCMTaxCalculation"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		matchedAmountCalculationCompareExcelSheetWithDataBase(
				webDB.getDataFromProperties("DirectDevRCMTaxCalculation"));
	}

	/**
	 * This Method used For Registered Supplier with Local GST (Allow IGST tax) -
	 * Against Subcontract Direct With ExpensesAccount
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalGSTAllowRCMtaxAgainstSubcontractDirectWithExpensesAccount() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalRegistered"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifyToClickReferenceFieldAndVerifyClickRCMFlag(webDB.getDataFromProperties("DevandExpRCMTaxCalculation"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		matchedAmountCalculationCompareExcelSheetWithDataBase(
				webDB.getDataFromProperties("DevandExpRCMTaxCalculation"));
	}

	/**
	 * This Method used For Registered Supplier with Local GST (Allow RCM tax) -
	 * Against Subcontract Direct With ExpensesAccount And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithLocalGSTAllowRCMtaxAgainstSubcontractDirectWithExpensesAccountandAdjustment() {
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalRegistered"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalRegistered"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		VerifyToClickReferenceFieldAndVerifyClickRCMFlag(webDB.getDataFromProperties("DevandExpRCMTaxCalculation"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		matchedAmountCalculationCompareExcelSheetWithDataBase(
				webDB.getDataFromProperties("DevandExpRCMTaxCalculation"));
	}

	/**
	 * This Method used For Registered Supplier with Interstate GST- Subcontract
	 * Direct
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithInterstateGSTSubcontractDirect() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SuncontractInterstateSupplier"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		matchedAmountCalculationCompareExcelSheetWithDataBase(
				webDB.getDataFromProperties("DevTaxInterstateRegCalculation"));
	}

	/**
	 * This Method used For Registered Supplier with Interstate GST- Against
	 * Subcontract Direct and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithInterstateGSTAgainstSubcontractDirectandExpensesAccount() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SuncontractInterstateSupplier"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		matchedAmountCalculationCompareExcelSheetWithDataBase(
				webDB.getDataFromProperties("DevandExpTaxInterstatregCalcula"));
	}

	/**
	 * This Method used For This Method used For Registered Supplier with Interstate
	 * GST- Against Subcontract Direct With ExpensesAccount And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithInterstateGSTAgainstSubcontractDirectWithExpensesAccountandAdjustment() {
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"),
				commonFunc.getDataFromTestDataSheet("SuncontractInterstateSupplier"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SuncontractInterstateSupplier"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		matchedAmountCalculationCompareExcelSheetWithDataBase(
				webDB.getDataFromProperties("DevandExpTaxInterstatregCalcula"));
	}

	/**
	 * Registered Supplier with Interstate GST (Allow RCM tax) - Against Subcontract
	 * Direct
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithInterstateAllowRCMtaxAgainstSubcontractDirect() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SuncontractInterstateSupplier"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		VerifyToClickReferenceFieldAndVerifyClickRCMFlag(webDB.getDataFromProperties("DevInterRegRCMTaxCalculation"));
		clickingReferenceTabAndValidating();
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		matchedAmountCalculationCompareExcelSheetWithDataBase(
				webDB.getDataFromProperties("DevInterRegRCMTaxCalculation"));
	}

	/**
	 * This Method used For Registered Supplier with Interstate GST (Allow RCM tax)-
	 * Against Subcontract Direct and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithInterstateAllowRCMtaxAgainstSubcontractDirectandExpensesAccount() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SuncontractInterstateSupplier"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifyToClickReferenceFieldAndVerifyClickRCMFlag(webDB.getDataFromProperties("DevandExpInterRegRCMTaxCal"));
		clickingReferenceTabAndValidating();
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		matchedAmountCalculationCompareExcelSheetWithDataBase(
				webDB.getDataFromProperties("DevandExpInterRegRCMTaxCal"));
	}

	/**
	 * This Method used For This Method used For Registered Supplier with Interstate
	 * GST(Allow RCM tax)- Against Subcontract Direct With ExpensesAccount And
	 * Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	public void registeredSupplierwithInterstateAllowRCMtaxAgainstSubcontractDirectandExpensesAccountandAdjustment() {
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"),
				commonFunc.getDataFromTestDataSheet("SuncontractInterstateSupplier"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SuncontractInterstateSupplier"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		VerifyToClickReferenceFieldAndVerifyClickRCMFlag(webDB.getDataFromProperties("DevandExpInterRegRCMTaxCal"));
		clickingReferenceTabAndValidating();
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		matchedAmountCalculationCompareExcelSheetWithDataBase(
				webDB.getDataFromProperties("DevandExpInterRegRCMTaxCal"));
	}

	/**
	 * This Method used For UnRegistered Supplier with Local GST- Subcontract Direct
	 * 
	 * @author Vimal - RMITS
	 */
	public void unRegisteredSupplierwithLocalGSTSubcontractDirect() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalUnRegistered"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		matchedAmountCalculationCompareExcelSheetWithDataBase(
				webDB.getDataFromProperties("DevTaxLocalUnregCalculation"));
	}

	/**
	 * This Method used For Registered Supplier with Local GST- Against Subcontract
	 * Direct and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	public void unRegisteredSupplierwithLocalGSTSubcontractDirectDirectandExpensesAccount() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalUnRegistered"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		matchedAmountCalculationCompareExcelSheetWithDataBase(
				webDB.getDataFromProperties("DevandExpTaxLocUnregCalculation"));
	}

	/**
	 * This Method used For Registered Supplier with Local GST- Against Subcontract
	 * Direct and Expenses Account And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	public void unRegisteredSupplierwithLocalGSTSubcontractDirectDirectandExpensesAccountandAdjustment() {
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalUnRegistered"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalUnRegistered"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		matchedAmountCalculationCompareExcelSheetWithDataBase(
				webDB.getDataFromProperties("DevandExpTaxLocUnregCalculation"));
	}

	/**
	 * This Method used For Unregistered Supplier with Local GST (Allow IGST tax)-
	 * Subcontract Direct
	 * 
	 * @author Vimal - RMITS
	 */
	public void unRegisteredSupplierwithLocalGSTAllowIGSTTaxSubcontractDirect() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalRegistered"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		VerifyToClickReferenceFieldAndClickIGSTFlag(webDB.getDataFromProperties("DevTaxInterstateUnRegCal"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		matchedAmountCalculationCompareExcelSheetWithDataBase(webDB.getDataFromProperties("DevTaxInterstateUnRegCal"));
	}

	/**
	 * This Method used For Registered Supplier with Local GST- Against Subcontract
	 * Direct and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	public void unRegisteredSupplierwithLocalGSTAllowIGSTTaxSubcontractDirectandExpensesAccount() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalRegistered"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifyToClickReferenceFieldAndClickIGSTFlag(webDB.getDataFromProperties("DevandExpTaxInterstateUnregCal"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		matchedAmountCalculationCompareExcelSheetWithDataBase(
				webDB.getDataFromProperties("DevandExpTaxInterstateUnregCal"));
	}

	/**
	 * This Method used For Registered Supplier with Local GST- Against Subcontract
	 * Direct and Expenses Account And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	public void unRegisteredSupplierwithLocalGSTAllowIGSTTaxSubcontractDirectandExpensesAccountandAdjustment() {
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalUnRegistered"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalUnRegistered"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		VerifyToClickReferenceFieldAndClickIGSTFlag(webDB.getDataFromProperties("DevandExpTaxInterstateUnregCal"));
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		matchedAmountCalculationCompareExcelSheetWithDataBase(
				webDB.getDataFromProperties("DevandExpTaxInterstateUnregCal"));
	}

	/**
	 * This Method used For Unregistered Supplier with Interstate GST- Subcontract
	 * Direct
	 * 
	 * @author Vimal - RMITS
	 */
	public void unRegisteredSupplierwithInterstateGSTSubcontractDirect() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SupplierInterstateUnRegistered"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		matchedAmountCalculationCompareExcelSheetWithDataBase(webDB.getDataFromProperties("DevTaxInterstateUnRegCal"));
	}

	/**
	 * This Method used For Unregistered Supplier with Interstate GST- Subcontract
	 * Direct and Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	public void unRegisteredSupplierwithInterstateGSTSubcontractDirectandExpensesAccount() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SupplierInterstateUnRegistered"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		matchedAmountCalculationCompareExcelSheetWithDataBase(
				webDB.getDataFromProperties("DevandExpTaxInterstateUnregCal"));
	}

	/**
	 * This Method used For Unregistered Supplier with Interstate GST- Subcontract
	 * Direct and Expenses Account And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	public void unRegisteredSupplierwithInterstateGSTSubcontractDirectandExpensesAccountandAdjustment() {
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"),
				commonFunc.getDataFromTestDataSheet("SupplierInterstateUnRegistered"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SupplierInterstateUnRegistered"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
		matchedAmountCalculationCompareExcelSheetWithDataBase(
				webDB.getDataFromProperties("DevandExpTaxInterstateUnregCal"));
	}

	/**
	 * This Method used For Unregistered Supplier imported (without GST)
	 * 
	 * @author Vimal - RMITS
	 */
	public void unregisteredSupplierimportedwithoutGST() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SupplierImportedUnRegistered"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Unregistered Supplier imported (without GST) and
	 * Expenses Account
	 * 
	 * @author Vimal - RMITS
	 */
	public void unregisteredSupplierimportedwithoutGSTandExpensesAccount() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SupplierImportedUnRegistered"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Unregistered Supplier imported (without GST) and
	 * Expenses Account And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	public void unregisteredSupplierimportedwithoutGSTandExpensesAccountandAdjustment() {
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"),
				commonFunc.getDataFromTestDataSheet("SupplierImportedUnRegistered"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SupplierImportedUnRegistered"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Non-resident Supplier imported (without GST)
	 * 
	 * @author Vimal - RMITS
	 */
	public void nonresidentSupplierimportedwithoutdeviationwithoutGST() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SupplierImportedNonResident"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Non-resident Supplier imported (without GST) Expenses
	 * Account
	 * 
	 * @author Vimal - RMITS
	 */
	public void nonresidentSupplierimportedwithoutdeviationwithoutGSTandExpensesAccount() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SupplierImportedNonResident"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used ForNon-resident Supplier imported (without GST) and Expenses
	 * Account And Adjustment
	 * 
	 * @author Vimal - RMITS
	 */
	public void nonresidentSupplierimportedwithoutdeviationwithoutGSTandExpensesAccountandAdjustment() {
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"),
				commonFunc.getDataFromTestDataSheet("SupplierImportedNonResident"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SupplierImportedNonResident"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Domestic Registered Supplier - With Local Freight GST
	 * 
	 * @author Vimal - RMITS
	 */
	public void directdomesticRegisteredSupplierWithLocalFreightGST() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SupplierLocalFreight"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Domestic Registered Supplier - With Union Territory GST
	 * 
	 * @author Vimal - RMITS
	 */
	public void directdomesticRegisteredSupplierWithUnionTerritoryGST() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("SupplierUnionTerritory"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Domestic Unregistered Supplier - With Local Freight GST
	 * 
	 * @author Vimal - RMITS
	 */
	public void directdomesticRegisteredSupplierWithUnregisteredLocalFreight() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("UnregSupplierLocalFreight"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Domestic Unregistered Supplier - With Union Territory
	 * GST
	 * 
	 * @author Vimal - RMITS
	 */
	public void directdomesticUnRegisteredSupplierWithUnionTerritory() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("UnregSupplierUnionTerritory"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Domestic LocalFreight Supplier - With Composition
	 * 
	 * @author Vimal - RMITS
	 */
	public void directdomesticRegisteredSupplierWithLocalFreightComposition() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("LocalFreightComposition"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Domestic Interstate Supplier - With Composition
	 * 
	 * @author Vimal - RMITS
	 */
	public void directdomesticRegisteredSupplierWithInterstateComposition() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("InterstateComposition"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Domestic UnionTerritory Supplier - With Composition
	 * 
	 * @author Vimal - RMITS
	 */
	public void directdomesticRegisteredSupplierWithUnionTerritoryComposition() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("UnionTerritoryComposition"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Domestic Interstate Supplier - With CasualPerson
	 * 
	 * @author Vimal - RMITS
	 */
	public void directdomesticRegisteredSupplierWithInterstateCasualPerson() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("CasualPersonInterstate"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Domestic UnionTerritory Supplier - With CasualPerson
	 * 
	 * @author Vimal - RMITS
	 */
	public void directdomesticRegisteredSupplierWithUnionTerritoryCasualPerson() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("CasualPersonUnionTerritory"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Domestic Interstate Supplier - With NonResident
	 * 
	 * @author Vimal - RMITS
	 */
	public void directdomesticRegisteredSupplierWithInterstateNonResident() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("InterstateNonResident"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Domestic UnionTerritory Supplier - With NonResident
	 * 
	 * @author Vimal - RMITS
	 */
	public void directdomesticRegisteredSupplierWithUnionTerritoryNonResident() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToFinancialPurchaseBill();
		createDirectPurchaseBillSubcontractGRNAgainst(commonFunc.getDataFromTestDataSheet("BillNo"),
				commonFunc.getDataFromTestDataSheet("UnionTerritoryNonResident"),
				commonFunc.getDataFromTestDataSheet("PBRefType"));
		insertingSubcontractProcessDetails(commonFunc.getDataFromTestDataSheet("ProcQty"),
				commonFunc.getDataFromTestDataSheet("ProcCost"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * Navigate To Reference Tab And Validating IGST Flag
	 * 
	 * @author Vimal - RMITS
	 */
	public boolean VerifyToClickReferenceFieldAndClickExemptedFlag() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.ReferenceTab, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Pending Grn Is Displayed");
			webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.ReferenceTab, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.AllowExemptedFlag, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "RCM Flag Displayed");

				webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.AllowExemptedFlag, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.OverAllSaveBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "IGST Flag Successfully Clicked");
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(2000);
					webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.OverAllSaveBtn, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.InvoiceLineTab, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "OverAll Save Button Successfully Clicked");
						webDB.clickAnElement(PurchaseBillsLocatorsGetDocument.InvoiceLineTab, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(PurchaseBillsLocatorsGetDocument.PBAddLine, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Invoice Tab Successfully Clicked");
						}
					}
				}
			}
		}
		assertTrue(flag);
		return flag;
	}

	/**
	 * This Method used For Domestic Registered Supplier - With TDS (Exempted)
	 * 
	 * @author Vimal - RMITS
	 */
	public void domesticRegisteredSupplierWithTDSExempted() {
		SubcontractFunc.directSubcontractorderToGRNTypePRWithTDS();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		VerifyToClickReferenceFieldAndClickExemptedFlag();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Domestic Registered Supplier - With TDS Single Under
	 * section
	 * 
	 * @author Vimal - RMITS
	 */
	public void domesticRegisteredSupplierWithTDSSingleUndersection() {
		SubcontractFunc.directSubcontractorderToGRNTypePRWithTDS();
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * This Method used For Clicking Apply Deduction Button
	 * 
	 * @author Vimal - RMITS
	 */
	public void clickApplyDeductionButton() {
		flag = webDB.waitForElement(PurchaseBillsGrnLocators.PB_ApplyDeduction, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseBillsGrnLocators.PB_ApplyDeduction, ElementType.Xpath);
			commonFunc.hardWait(1000);
			webDB.pressEnterKey();
			logs.logging("INFO", "Apply Deduction Buttton Clicked");
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(1000);
		}
		assertTrue(flag);
	}

	/**
	 * Domestic Registered Supplier - With Advance TDS single under section (Single
	 * PO)
	 * 
	 * @author Vimal - RMITS
	 */
	public void domesticRegisteredSupplierWithAdvanceTDSsingleundersectionSinglePO() {
		SubcontractFunc.directSubcontractorderToGRNTypePRWithTDS();
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"), commonFunc.getDataFromTestDataSheet("SupplierForTDS"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("PurchaseBillDeviationandExp"));
//		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
//				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		purchaseBillsFunc.ClickSaveButton();
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		clickApplyDeductionButton();
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}

	/**
	 * Domestic Registered Supplier - With Advance TDS single under section (Single
	 * PO with Single New reference)
	 * 
	 * @author Vimal - RMITS
	 */
	public void domesticRegisteredSupplierWithAdvanceTDSsingleundersectionSinglePOWithSingleNewreference() {
		SubcontractFunc.directSubcontractorderToGRNTypePRWithTDS();
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustmentAndGetGLAcct(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"), commonFunc.getDataFromTestDataSheet("SupplierForTDS"));
		purchaseBillsFunc.addNewRefPaymentsAgainstTotheParty(commonFunc.getDataFromTestDataSheet("BillsBank"),
				commonFunc.getDataFromTestDataSheet("PayAmount1"));
		BPFunc.clickPostButton();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
				VariableHandler.getBankPaymentOrReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		commonFunc.navigateToFinancialPurchaseBill();
		verifyPendingSCOPurchaseBill(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("PurchaseBillDeviationandExp"));
//		verifyAddExpenseTypeUnitcostinExcelSheet(commonFunc.getDataFromTestDataSheet("PBGLAccount"),
//				commonFunc.getDataFromTestDataSheet("ExpenseType"));
		purchaseBillsFunc.ClickSaveButton();
		purchaseBillsFunc.loadAdjustmentForSupplierToAddedInParty(VariableHandler.getBankPaymentOrReceiptDocNumber());
		clickApplyDeductionButton();
		verifyCreateJournalAndVerifyMatchedAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}
}
