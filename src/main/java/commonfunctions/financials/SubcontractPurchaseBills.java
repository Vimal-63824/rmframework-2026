package commonfunctions.financials;

/**
 * @author Vimal - RMITS
 * @company RoadMap IT Solutions Feb 10, 2026
 */
import static org.testng.Assert.assertTrue;

import java.io.File;

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
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
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
								commonFunc.hardWait(8000);
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
		purchaseBillsFunc.verifyPurchaseBillsAgainstPurchaseBillsForAdjustment(
				commonFunc.getDataFromTestDataSheet("BankName"), commonFunc.getDataFromTestDataSheet("BankTransCurcy"),
				webDB.getDataFromProperties("BankRecptGlAcct"));
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
//		purchaseBillsFunc.verifyLineDetailsWithDBvalidationInGrnPurchaseType("PurchaseBills_ExAcc");
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
		webDB.moveToElement(PurchaseBillsGrnLocators.PB_LineProcess_Button, ElementType.Xpath);
		webDB.clickAnElement(PurchaseBillsGrnLocators.PB_LineProcess_Button, ElementType.Xpath);
		webDB.switchToFrame(PurchaseBillsGrnLocators.PB_ProcessFrame, ElementType.Xpath);
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
		verifyPendingSCOPurchaseBill("GRNS-2526-1552", commonFunc.getDataFromTestDataSheet("TDSValue"));
		clickingReferenceTabAndValidating();
		clickingLinewiseProcessDetailsChangingQtyandUnitcost(commonFunc.getDataFromTestDataSheet("TDSValue"),
				commonFunc.getDataFromTestDataSheet("PBProcessQtyInput"));
		getMatchedAmtAndGRNAmtandWriteInExcelSheet(webDB.getDataFromProperties("PurchaseBillDeviation"));
		verifyCreateJournalAndVerifyDeviationAmtAndPost();
		PurBillFunc.waitingForApproval(commonFunc.getDataFromTestDataSheet("HodValue"));
	}
}
