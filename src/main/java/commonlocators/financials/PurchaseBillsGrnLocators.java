package commonlocators.financials;

import commonfunctions.CommonFunctions;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks December 17, 2025
 */
public class PurchaseBillsGrnLocators {
	/** @locatorType Xpath */
	public static final String PendingPurchaseBill = "//button[@data-testid='PendingBills']";
	/** @locatorType Xpath */
	public static final String PendingGrn = "//button[@data-testid='PendingGRN']";
	/** @locatorType Xpath */
	public static final String Iframe = "//iframe";
	/** @locatorType Xpath */
	public static final String GrnNo = "//input[@data-testid='P116131004_GRN_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SearchButton = "//button[@data-testid='SearchGRN']";

	/** @locatorType Xpath */
	public static final String CheckBox(String GrnNo) {
		return "//td[contains(text(),'" + GrnNo + "')]/../td[14]";
	}

	/** @locatorType Xpath */
	public static final String CreateDoc = "//button[@data-testid='CreateDoc']";
	/** @locatorType Xpath */
	public static final String OkButton = "//button[@data-testid='INV']";
	/** @locatorType Xpath */
	public static final String LocationId = "//span[@data-testid='P11613101001_SUPHD_PLNT_LOC_ID']";
	/** @locatorType Xpath */
	public static final String CreateJournal = "//button[@data-testid='CreJournal']";
	/** @locatorType Xpath */
	public static final String PostButton = "//button[@data-testid='POST']";
	/** @locatorType Xpath */
	public static final String VoucherNumber = "//span[@data-testid='VouNo']";
	/** @locatorType Xpath */
	public static final String WorkFlowOkButton = "//button[@id='B1457704718951134857']";
	/** @locatorType Xpath */
	public static final String VoucherNumberLovButton = "//button[@id='P116131010_VOU_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String PurchaseBillSearchButton = "//button[@id='B1552825888922175484']";
	/** @locatorType Xpath */
	public static final String ApproveOkButton = "//button[@id='B1457704718951134857']";

	/** @locatorType Xpath */
	public static final String ApprovedStatus(String PbNo) {
		return "//a[text()='" + PbNo + "']/../../td[13]";
	}

	/** @locatorType Xpath */
	public static final String OkButtonForward = "//button[@data-testid='OK']";
	/** @locatorType Xpath */
	public static final String PBCostInput = "//input[@id='amt']";
	/** @locatorType Xpath */
	public static final String PBCostField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[10]";
	/** @locatorType Xpath */
	public static final String PBAddLine = "//button[@data-testid='ADDLN']";
	/** @locatorType Xpath */
	public static final String GlAccDescField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[6]";
	/** @locatorType Xpath */
	public static final String GlAccLov = "//input[@data-testid='SUPLN_ACCT_DESC']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PBAddOverallSave = "//button[@data-testid='SAVEHD']";
	/** @locatorType Xpath */
	public static final String PBAddSave = "//button[@data-testid='IN_SAVE']";
	/** @locatorType Xpath */
	public static final String PBAddTypeExp = "//select[@data-testid='SUPLN_TYPE']";
	/** @locatorType Xpath */
	public static final String PBAdjustmentTab = "//label[@for='P11613101001_TABS_2']";
	/** @locatorType Xpath */
	public static final String PBAdjustmentLoadBtn = "//button[@id='loadadj']";
	/** @locatorType Xpath */
	public static final String PBAdjustmentFrame = "//iframe[@title='Advance Adjustment ']";
	/** @locatorType Xpath */
	public static final String PBAdjustDocSelect = "//table[@id='2060107643985105878_orig']/tbody/tr/td[11]";
	/** @locatorType Xpath */
	public static final String PBAdjustValueEnterField = "//table[@id='2060107643985105878_orig']/tbody/tr/td[10]";
	/** @locatorType Xpath */
	public static final String PBAdjustValueEnterInput = "//input[@type='number']";
	/** @locatorType Xpath */
	public static final String PBAdjustmentOk = "//button[@id='B1']";
	/** @locatorType Xpath */
	public static final String PBReferenceTab = "//label[@for='P11613101001_TABS_3']";
	/** @locatorType Xpath */
	public static final String PBIGSTFlag = "//label[@for='P11613101001_ALOW_IGST_CSGST']";
	/** @locatorType Xpath */
	public static final String PBRCMFlag = "//label[@for='P11613101001_SUHD_ALOW_RCM_TAX']";
	/** @locatorType Xpath */
	public static final String PBItcSelect = "//select[@id='C2386871711424680771']";
	/** @locatorType Xpath */
	public static final String PBItcSelectField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[24]";
	/** @locatorType Xpath */
	public static final String PBItcSectionSelect = "//select[@id='C2386874327766680748']";
	/** @locatorType Xpath */
	public static final String PBItcSectionField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[63]";
	/** @locatorType Xpath */
	public static final String PBItcSubSectionSelect = "//select[@id='C2386874439302680749']";
	/** @locatorType Xpath */
	public static final String PBItcSubSectionField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[64]";
	/** @locatorType Xpath */
	public static final String PBAdjustmentSearchField = "//input[@id='PEND_search_field']";
	/** @locatorType Xpath */
	public static final String PBAdjustmentSearchGoBtn = "//button[@id='PEND_search_button']";
	/** @locatorType Xpath */
	public static final String PBBPPendingCrDoc = "//span[contains(text(),'Pending Cr. Docs.')]";
	/** @locatorType Xpath */
	public static final String PBBPPendingPayables = "//button[@data-testid='PendingPayables']";
	/** @locatorType Xpath */
	public static final String PBBPPendingFrame = "//iframe[@title='Pending Payables']";
	/** @locatorType Xpath */
	public static final String PBBPPendingDocNoLov = "//input[@data-testid='P3413101001_DOC_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PBGetDocNo = "//span[@data-testid='VouNo']";
	/** @locatorType Xpath */
	public static final String PendingPayablesSearchBtn = "//button[@data-testid='Search1']";
	/** @locatorType Xpath */
	public static final String PendingPayableSelectDoc = "//input[@type='checkbox']";
	/** @locatorType Xpath */
	public static final String PendingPayableCreateBtn = "//button[@data-testid='Create']";
	/** @locatorType Xpath */
	public static final String PendingPayablePayMode = "//select[@data-testid='P3413101001_SUPHD_PYMT_TYPE']";
	/** @locatorType Xpath */
	public static final String ExpenseHsnLovBtn = "//input[@data-testid='SUPLN_HSN_CODE']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String ExpenseHsnField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[14]";
	/** @locatorType Xpath */
	public static final String PBQtyField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[9]";
	/** @locatorType Xpath */
	public static final String PBQtyInput = "//input[@data-testid='SUPLN_INV_QTY']";
	/** @locatorType Xpath */
	public static final String PBItemField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[3]";
	/** @locatorType Xpath */
	public static final String PBItemLov = "//input[@data-testid='PROD_ID_REV']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PBTrdDiscPercField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[33]";
	/** @locatorType Xpath */
	public static final String PBTrdDiscPercInput = "//input[@data-testid='SUPLN_TRD_DISC_PCT']";
	/** @locatorType Xpath */
	public static final String PBSpclDiscPercField = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[36]";
	/** @locatorType Xpath */
	public static final String PBSpclDiscPercInput = "//input[@data-testid='SUPLN_SPL_DISC_PCT']";
	/** @locatorType Xpath */
	public static final String PBCashDiscPercField = "//*[@id=\'IN_ig_grid_vc\']/div[2]/div[3]/table/tbody/tr[1]/td[39]";
	/** @locatorType Xpath */
	public static final String PBCashDiscPercInput = "//input[@data-testid='SUPLN_CASH_DISC_PCT']";

	/** @locatorType Xpath */
	public static final String BPPendingPartyDocumentSelectCheckBox(String VoucherNumber) {
		return "//td[contains(text(),'" + VoucherNumber + "')]/../td[13]/*";
	}

	/** @locatorType Xpath */
	public static final String PB_PendSCOField = "//button[@data-testid='PendingSCO']";
	/** @locatorType Xpath */
	public static final String SCO_SearchButton = "//button[@data-testid='SearchSCO']";
	/** @locatorType Xpath */
	public static final String SCONoField = "//input[@data-testid='P116131006_GRN_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SCO_CheckBox = "(//input[@id='check_all'])[1]";
	/** @locatorType Xpath */
	public static final String PB_LineProcess_Button = "(//span[@class='fa fa-info'])[1]";
	/** @locatorType Xpath */
	public static final String PB_ProcessFrame = "//iframe[@title='Supplier Bill - Process']";
	/** @locatorType Xpath */
	public static final String PB_Process_TDS_Lov = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[28]";
	/** @locatorType Xpath */
	public static final String ProcessTDSLovButton = "//input[@data-testid='SDPLH_TDS_US_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PB_Process_Save = "//button[@title='Save']";
	/** @locatorType Xpath */
	public static final String PB_Process_Close = "//button[@id='B2362517250755170401']";
	/** @locatorType Xpath */
	public static final String PendingPayableSelectAll = "//input[@id='check_all']";
	/** @locatorType Xpath */
	public static final String PB_Reference_MatchedAmt = "//input[@id='P11613101001_SUPHD_BILL_AMT']";
	/** @locatorType Xpath */
	public static final String PB_Reference_GRNAmt = "//input[@id='P11613101001_SUPHD_SC_GRN_TOT_']";
	/** @locatorType Xpath */
	public static final String PB_Reference_DeviationAmt = "//input[@id='P11613101001_GRN_BAL_AMT']";
	/** @locatorType Xpath */
	public static final String PB_ProcessTab_ProcQty = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[7]";
	/** @locatorType Xpath */
	public static final String PB_ProcessTab_UnitCost = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[8]";
	/** @locatorType Xpath */
	public static final String PB_ProcessTab_procQtyField = "//input[@data-testid='SDPL_PROC_QTY']";
	/** @locatorType Xpath */
	public static final String PB_ReferenceTab = "//label[@for='P11613101001_TABS_3']";
	/** @locatorType Xpath */
	public static final String PB_InvoiceTab = "//label[@for='P11613101001_TABS_0']";
	/** @locatorType Xpath */
	public static final String PBQty = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[9]";
	/** @locatorType Xpath */
	public static final String PBUnitCost = "//*[@id='IN_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[10]";
	/** @locatorType Xpath */
	public static final String BankPaymentAmountField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[7]";
	/** @locatorType Xpath */
	public static final String PendingPBPrefixLov = "//input[@data-testid='P116131004_INV_PFX']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PB_ApplyDeduction = "//button[@data-testid='ApplyDeductions']";
	/** @locatorType Xpath */
	public static final String PB_ApplyDeduction_Yes = "//*[@id='t_PageBody']/div[35]/div[3]/div/button[2]";

	/** @locatorType Xpath */
	public static final String PBAddLineTable = "(//TABLE[@data-testid='IN_Result_Data'])[2]";
	/** @locatorType Xpath */
	public static final String PB_Line_Detail_Btn = "//table[@data-testid='IN_Result_Data']/tbody/tr/td[1]";

	/** @locatorType Xpath */
	public static final String PurchaseBillsTransLocGLAccnt() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PBAddLineTable, "GL Acct. Desc.");
	}

	/** @locatorType Xpath */
	public static final String PurchaseBillsTransLocunitprice() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PBAddLineTable, "Unit Cost");
	}

	/** @locatorType Xpath */
	public static final String PurchaseBillsTransLocHsnCode() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PBAddLineTable, "HSN/SAC Code");
	}

	/** @locatorType Xpath */
	public static final String PurchaseBillsTransLocGstType() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PBAddLineTable, "Type");
	}

	/** @locatorType Xpath */
	public static final String PurchaseBillsTransLocGstInelSection() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PBAddLineTable, "Inel. Section");
	}

	/** @locatorType Xpath */
	public static final String PurchaseBillsTransLocGstTds() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PBAddLineTable, "TDS u/s ID");
	}

	/** @locatorType Xpath */
	public static final String PurchaseBillsTransLocGstItem() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PBAddLineTable, "Item");
	}

	/** @locatorType Xpath */
	public static final String PurchaseBillsTransLocGstQty() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PBAddLineTable, "Inv. Qty.");
	}

	/** @locatorType Xpath */
	public static final String PurchaseBillsTransLocGstTrdDis() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PBAddLineTable, "Trd. Disc. %");
	}

	/** @locatorType Xpath */
	public static final String PurchaseBillsTransLocGstSpclDis() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PBAddLineTable, "Spl. Disc. %");
	}

	/** @locatorType Xpath */
	public static final String PurchaseBillsTransLocGstCashDis() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PBAddLineTable, "Cash Disc. %");
	}

}
