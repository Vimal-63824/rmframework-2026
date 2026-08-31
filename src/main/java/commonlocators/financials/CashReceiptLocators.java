package commonlocators.financials;

/**
 * @author Harikrishna - RMITS
 * @company RoadMap IT Solutions October 16, 2025
 */
public class CashReceiptLocators {
	/** @locatorType Xpath */
	public static final String BankAddButton = "//button[@data-testid='BankTrans']";
	/** @locatorType Xpath */
	public static final String CashPrefixLovButton = "//input[@data-testid='Prefix']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String CashModeDropDown = "//select[@data-testid='DocMode']";
	/** @locatorType Xpath */
	public static final String CashLovbutton = "//input[@data-testid='P341310009_BTRANS_BANK_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String CashVoucherDateinputField = "//input[@id='P341310009_BTRANS_PV_DATE_input']";
	/** @locatorType Xpath */
	public static final String BankPayModeDropDown = "//select[@data-testid='PayMode']";
	/** @locatorType Xpath */
	public static final String BankRefNumberLovButton = "//input[@data-testid='RefNo']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String BankRefDateInputField = "//input[@id='P341310009_BTRANS_CHQ_DATE_input']";
	/** @locatorType Xpath */
	public static final String BankTransCurrencyLovButton = "//button[@id='P341310009_BTRANS_TRANS_CURCY_lov_btn']";
	/** @locatorType Xpath */
	public static final String BankCyrrencyInputField = "//input[@data-testid='BankCurcy']";
	/** @locatorType Xpath */
	public static final String CashSaveButton = "//button[@data-testid='Add']";
	/** @locatorType Xpath */
	public static final String BankRefHiddenInputField = "//input[@id='P341310009_BTRANS_TRANS_REF_NO_HIDDENVALUE']";
	/** @locatorType Xpath */
	public static final String BankTransCurrencyHiddenInputField = "//input[@id='P341310009_BTRANS_TRANS_CURCY_HIDDENVALUE']";
	/** @locatorType Xpath */
	public static final String CashPostButton = "//button[@data-testid='Post']";
	/** @locatorType Xpath */
	public static final String BankRefInputField = "//input[@id='P341310009_BTRANS_TRANS_REF_NO1']";
	/** @locatorType Xpath */
	public static final String CashDocumentNumberField = "//span[@data-testid='VouNo']";
	/** @locatorType Xpath */
	public static final String BankAccHeaderField = "//span[@data-testid='BankAC']";
	/** @locatorType Xpath */
	public static final String BankVoucherPredixHeaderfield = "//span[@data-testid='VouPfx']";
	/** @locatorType Xpath */
	public static final String BankHeaderVoucherDateInputField = "//input[@id='P3413103001_BTRANS_PV_DATE_input']";
	/** @locatorType Xpath */
	public static final String BankHeaderRefDateInputField = "//input[@id='P3413103001_BTRANS_CHQ_DATE_input']";
	/** @locatorType Xpath */
	public static final String BankLineWiseAddButton = "//button[@id='B1217119641398022948']";
	/** @locatorType Xpath */
	public static final String BankLineGLAccountField = "//*[@id='ig_bank_recon_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[1]";
	/** @locatorType Xpath */
	public static final String BankLineGLAccountLovButton = "//input[@data-testid='BTDLN_ACCT']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String BankLineCPCField = "//*[@id='ig_bank_recon_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[4]";
	/** @locatorType Xpath */
	public static final String BankLineCPCLovButton = "//input[@data-testid='BTDLN_CC_DESC']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String BankLineWiseSaveButton = "//button[@data-testid='Save_Line']";
	/** @locatorType Xpath */
	public static final String BankLineWiseDeleteButton = "#ig_bank_recon_line_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr.a-GV-row.is-selected > td:nth-child(9) > a";
	/** @locatorType Xpath */
	public static final String BankLineWiseViewButton = "#ig_line_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr > td:nth-child(9) > a";
	/** @locatorType Xpath */
	public static final String BankReceiptViewAddButton = "//button[@title='Add']";
	/** @locatorType Xpath */
	public static final String BankReceiptViewFrame = "(//h1[contains(text(),'Receipt Details')]/ancestor::div/following-sibling::div)//iframe";
	/** @locatorType Xpath */
	public static final String BankReceiptBillNumberInputField = "//INPUT[@data-testid='BTR_BFCRY_DOC_NO']";
	/** @locatorType Xpath */
	public static final String BankReceiptPayAmountInputField = "//INPUT[@data-testid='BTR_TRANS_AMT']";
	/** @locatorType Xpath */
	public static final String BankReceiptAgainstRefFieldName = "//*[@id='line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[1]";
	/** @locatorType Xpath */
	public static final String BankReceiptBillDocField = "//*[@id='line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[2]";
	/** @locatorType Xpath */
	public static final String BankReceiptPayAmountField = "//*[@id='line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[4]";
	/** @locatorType Xpath */
	public static final String BankReceiptViewSaveButton = "//button[@data-testid='SAVE']";
	/** @locatorType Xpath */
	public static final String CashReceiptViewDeleteButton = "//*[@id='line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[24]/a";
	/** @locatorType Xpath */
	public static final String BankLineAmountInTCField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[5]";
	/** @locatorType Xpath */
	public static final String BankLineAssesableValueField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[19]";
	/** @locatorType Xpath */
	public static final String BankHeaderCreateJournalButton = "//button[@data-testid='CreJournal']";
	/** @locatorType Xpath */
	public static final String BankHeaderViewJournalButton = "//button[@data-testid='ViewJournal']";
	/** @locatorType Xpath */
	public static final String BankReceiptViewScreenCloseButton = "//h1[contains(text(),'Receipt Details')]/ancestor::div/child::div/button";
	/** @locatorType Xpath */
	public static final String BankWorkFlowApprovalFrame = "//iframe[@title='Workflow Approval']";
	/** @locatorType Xpath */
	public static final String BankLineAddButton = "#ig_line_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr > td:nth-child(8) > a";
	/** @locatorType Xpath */
	public static final String BankPendingReceivablesButton = "//button[@id='pendrec']";
	/** @locatorType Xpath */
	public static final String BankPendingPayablesAdvanceButton = "//button[@id='PEND_PAY_ADV']";
	/** @locatorType Xpath */
	public static final String BankPendingPartyDocumentsFrameName = "//iframe[@title='Pending Payables']";
	/** @locatorType Xpath */
	public static final String BankPendingPartyDocumentsPartyNameInputField = "//input[@data-testid='PartyName']";
	/** @locatorType Xpath */
	public static final String BankPendingPartyDocumentScreenOkButton = "//button[@id='B6']";
	/** @locatorType Xpath */
	public static final String BankPaymentGetDocumentButton = "//button[@data-testid='GetDoc']";
	/** @locatorType Xpath */
	public static final String BankPaymentPendingPartyDocumentPartyLovButton = "//button[@id='P3413101001_PART_NAME_lov_btn']";
	/** @locatorType Xpath */
	public static final String BankPaymentPendingPartyDocumentSearchButton = "//button[@id='B1891433450341546296']";
	/** @locatorType Xpath */
	public static final String BankPaymentPendingPartyNameColumnNameField = "//*[@id='2550108912065853115_orig']/tbody/tr[2]/td[2]";
	/** @locatorType Xpath */
	public static final String PendingCRDocsButton = "//span[contains(text(),'Pending Cr. Docs.')]/ancestor::button";
	/** @locatorType Xpath */
	public static final String PendingPayablesButton = "//button[@data-testid='PendingPayables']";
	/** @locatorType Xpath */
	public static final String PendingPayablesDocNumberLovButton = "//input[@data-testid='P3413101003_DOC_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PendingPayablesDocumentNumberField = "//*[@id='2550108912065853115_orig']/tbody/tr[2]/td[21]";
	/** @locatorType Xpath */
	public static final String PendingPayablesCreateButton = "//button[@data-testid='Create']";
	/** @locatorType Xpath */
	public static final String PendingReceivablesPayModeDropDown = "//select[@id='P3413101003_PAY_MODE']";
	/** @locatorType Xpath */
	public static final String PendingReceivablesBankNameLOVBUtton = "//button[@id='P3413101003_BANK_lov_btn']";
	/** @locatorType Xpath */
	public static final String PendingReceivablesOkButton = "//button[@data-testid='Ok']";
	/** @locatorType Xpath */
	public static final String PendingReceivablesTypeDropDown = "//select[@id='P3413101003_SUPHD_TYPE']";
	/** @locatorType Xpath */
	public static final String PendingPayablesTypeField = "//div[@id='P3413101001_SUPHD_TYPE_CONTAINER']";
	/** @locatorType Xpath */
	public static final String PendingpayablesPaymodeField = "//div[@id='P3413101001_SUPHD_PYMT_TYPE_CONTAINER']";
	/** @locatorType Xpath */
	public static final String CashPaymentScreenName = "//span[contains(text(),'Cash')]/ancestor::a";
	/** @locatorType Xpath */
	public static final String BankReceiptScreenName = "//span[contains(text(),'Bank Receipt')]/ancestor::a";
	/** @locatorType Xpath */
	public static final String BankReceiptReferenceInputField = "//input[@data-testid='RefNo']";
	/** @locatorType Xpath */
	public static final String BankHeaderWiseSaveButton = "//button[@data-testid='Save']";
	/** @locatorType Xpath */
	public static final String BankPartyDocNumberLovButton = "//button[@id='P3413101001_DOC_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String BankPendingPartyDocumentClearButton = "//button[@title='Cancel']/parent::div/button[@title='Clear']";
	/** @locatorType Xpath */
	public static final String PendingPurchaseBill = "//button[@data-testid='PendingBills']";
	/** @locatorType Xpath */
	public static final String PendingGrn = "//button[@data-testid='PendingGRN']";
	/** @locatorType Xpath */
	public static final String Iframe = "//iframe";
	/** @locatorType Xpath */
	public static final String GrnNo = "//input[@data-testid=\"P116131004_GRN_NO\"]/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SearchButton = "//button[@id='B1874148864248723410']";
	/** @locatorType Xpath */
	public static final String CreateDoc = "//button[@data-testid='CreateDoc']";
	/** @locatorType Xpath */
	public static final String OkButton = "//button[@data-testid='INV']";
	/** @locatorType Xpath */
	public static final String LocationId = "//span[@data-testid='LocID']";
	/** @locatorType Xpath */
	public static final String CreateJournal = "//button[@data-testid='CreateJournal']";
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
	public static final String OkButtonForward = "//button[@id='B1459367661417504650']";
	/** @locatorType Xpath */
	public static final String PBFindButton = "//button[@data-testid='SEARCHAP']";
	/** @locatorType Xpath */
	public static final String PBBillNumberInputField = "//INPUT[@DATA-TESTID='BillNo']";
	/** @locatorType Xpath */
	public static final String PendingDRDocsButton = "//span[contains(text(),'Pending Dr. Docs.')]/ancestor::button";
	/** @locatorType Xpath */
	public static final String PendingReceivables = "//button[@data-testid='PendingReceivable']";
	/** @locatorType Xpath */
	public static final String BankReceiptPendingReceivablesButton = "//button[@id='pendrec']";
	/** @locatorType Xpath */
	public static final String CashReceiptPendingReceivablesButton = "//h1[text()='Create Pending Doc.']/ancestor::div/following::div[8]/button";
	/** @locatorType Xpath */
	public static final String BankReceiptPendingReceivablesFrameName = "//iframe[@title='Pending Receivables']";
	/** @locatorType Xpath */
	public static final String BankReceiptPendingReceivablesPartyNameInputField = "//input[@id='P3413101003_PARTY_NAME']";
	/** @locatorType Xpath */
	public static final String BankReceiptDocNumberLovButton = "//input[@data-testid='P3413101003_DOC_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String BankReceiptPendingReceivablesSearchButton = "//span[text()='Search']/ancestor::button";
	/** @locatorType Xpath */
	public static final String BankReceiptPendingReceivableScreenOkButton = "//button[@id='B4']";
	/** @locatorType Xpath */
	public static final String BankReceiptGetDocPendingReceivableScreenOkButton = "//button[@id='B2']";
	/** @locatorType Xpath */
	public static final String CashReceiptHeaderCreateJournalButton = "//button[@data-testid='CreJournal']";
	/** @locatorType Xpath */
	public static final String BankReceiptLineWiseAddButton = "//button[@title='Add Line']";
	/** @locatorType Xpath */
	public static final String BankReceiptCreateJournalButton = "//button[@data-testid='CreateJournal']";
	/** @locatorType Xpath */
	public static final String PendingReceivablesDocumentNumberField = "//*[@id='3511002720712706517_orig']/tbody/tr[2]/td[19]";
	/** @locatorType Xpath */
	public static final String PendingReceivablesDRDocumentNumberLOVButton = "//button[@id='P3413101003_DOC_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String PendingReceivablesMergeLabelInPending = "//label[@for='P3413101003_GLOBAL_TYPE_1']";
	/** @locatorType Xpath */
	public static final String BankReceiptRealizedAmtInputField = "//input[@data-testid='RlzdAmtBankCurcy']";
	/** @locatorType Xpath */
	public static final String BankReceiptPDCFlag = "//INPUT[@data-testid='PDC']";
	/** @locatorType Xpath */
	public static final String BakReciptPDCLabelName = "//input[@data-testid='PDC']/following-sibling::label";
	/** @locatorType Xpath */
	public static final String BankReciptRefDateInputField = "//*[@data-testid='RefDate']/input";
	/** @locatorType Xpath */
	public static final String BankReceiptChargesFlag = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[14]";
	/** @locatorType Xpath */
	public static final String BankReceiptChargesInputFlag = "//INPUT[@id='C3749913169207752357']";
	/** @locatorType Xpath */
	public static final String BankReceiptAmountInTcInputField = "//input[@data-testid='BTDLN_DIST_AMT']";
	/** @locatorType Xpath */
	public static final String CashReceiptLineATCField = "//*[@id='ig_bank_recon_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[5]";
	/** @locatorType Xpath */
	public static final String BankReceiptLineCRDRDropDown = "//select[@data-testid='BTDLN_DR_CR']";
	/** @locatorType Xpath */
	public static final String CashReceiptCRDRField = "//*[@id='ig_bank_recon_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[6]";
	/** @locatorType Xpath */
	public static final String BankReceiptPDCVoucherNumberField = "//input[@id='P34131031_VOU_NO']";
	/** @locatorType Xpath */
	public static final String BankReceiptPDCSearchButton = "//button[@id='B802285788346712667']";
	/** @locatorType Xpath */
	public static final String BankReceiptPDCSelectAllButton = "//input[@id='check_all']";
	/** @locatorType Xpath */
	public static final String BankReceiptPDCPostButton = "//button[@id='B1']";
	/** @locatorType Xpath */
	public static final String CashReceiptHSNCodeField = "//*[@id='ig_bank_recon_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[19]";
	/** @locatorType Xpath */
	public static final String CashReceiptLineHsnCodeLov = "//button[@id='hsnsacCde_lov_btn']";
	/** @locatorType Xpath */
	public static final String BankReceiptLineWiseGSTButton = "#ig_line_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr > td:nth-child(13) > a";
	/** @locatorType Xpath */
	public static final String BankReceiptGSTDetailsHeaderName = "//h1[contains(text(),'GST Details')]";
	/** @locatorType Xpath */
	public static final String BankReceiptGSTFrame = "//h1[contains(text(),'GST Details')]/ancestor::div/following-sibling::div/iframe";
	/** @locatorType Xpath */
	public static final String BankReceiptStateCodeLov = "//button[@id='P3413103015_BTDLN_STATE_CODE_lov_btn']";
	/** @locatorType Xpath */
	public static final String BankReceiptSupplierNameGST = "//input[@id='P3413103015_BTDLN_GST_SUPLR']";
	/** @locatorType Xpath */
	public static final String BankReceiptGSTINFieldInput = "//input[@id='P3413103015_BTDLN_GSTIN_NO']";
	/** @locatorType Xpath */
	public static final String BankReceiptBillDateGSTInput = "//input[@id='P3413103015_BTDLN_BILL_DATE_input']";
	/** @locatorType Xpath */
	public static final String BankReceiptGStBillNumberInputField = "//input[@id='P3413103015_BTDLN_BILL_NO']";
	/** @locatorType Xpath */
	public static final String BankReceiptGSTDetailsSaveButton = "//button[@title='Save2']";
	/** @locatorType Xpath */
	public static final String BankReceiptGSTCloseButton = "//h1[contains(text(),'GST Details')]/following-sibling::button";
	/** @locatorType Xpath */
	public static final String CashReceiptAdvancebutton = "//h1[text()='Create Pending']/ancestor::div/following::div[9]/button/span[text()='Pending Receivable - Advance']";
	/** @locatorType Xpath */
	public static final String CashReceiptAdvanceFrame = "//iframe[@title='Pend. Receivable - Advance']";
	/** @locatorType Xpath */
	public static final String BankReceiptAdvPendingReceivablesPartyNameInputField = "//input[@id='P341310231_PARTY_NAME']";
	/** @locatorType Xpath */
	public static final String AdvPendingReceivablesDocumentNumberLovButton = "//button[@id='P341310231_DOC_NO_FND_lov_btn']";
	/** @locatorType Xpath */
	public static final String BankReceiptAdvPendingReceivablesSearchButton = "//span[text()='Search']/ancestor::button";
	/** @locatorType Xpath */
	public static final String CashReceiptAdvpendingReceivablesgetDocument = "//button[@id='B2908151062294806599']";
	/** @locatorType Xpath */
	public static final String AdvPendingReceivablesDocumentNumberField = "//*[@id='2995376176452410474_orig']/tbody/tr[2]/td[22]";
	/** @locatorType Xpath */
	public static final String PendingReceivablesAdvanceButton = "//button[@id='B2908151062294806599']";
	/** @locatorType Xpath */
	public static final String PendingReceivanlesAdvanceCreateButton = "//span[text()='Create']/ancestor::button";
	/** @locatorType Xpath */
	public static final String PendingReceivablesAdvancePendingDRDocs = "//h1[text()='Create Pending Doc.']/ancestor::div/following::div[9]/button/span[text()='Pending Receivable - Advance']";
	/** @locatorType Xpath */
	public static final String PendingReceivablesAdvMergeLabelInPending = "//label[@for='P341310231_GLOBAL_SCREEN_1']";
	/** @locatorType Xpath */
	public static final String PendingReceivablesAdvSingleLabelInPending = "//label[@for='P341310231_GLOBAL_SCREEN_0']";
	/** @locatorType Xpath */
	public static final String BankPendingAdvPartyDocumentClearButton = "//button[@title='Home']/parent::div/button[@title='Clear']";
	/** @locatorType Xpath */
	public static final String AdvPendingReceivablesBankNameLOVBUtton = "//button[@id='P341310231_BANK_DESC_lov_btn']";
	/** @locatorType Xpath */
	public static final String AdvancePendingReceivablesTypeDropDown = "//select[@id='P341310231_TYPE']";
	/** @locatorType Xpath */
	public static final String AdvPendingReceivablesOkButton = "//span[text()='Ok']";
	/** @locatorType Xpath */
	public static final String PendingReceivablesAdvancePendingDRDocs1 = "//h1[text()='Create Pending']/ancestor::div/following::div[9]/button/span[text()='Pending Receivable - Advance']";
	/** @locatorType Xpath */
	public static final String RowSelectedButton = "//button[@id='RSEL']/span/span";

	/** @locatorType Xpath */
	public static final String BPPendingPartyDocumentSelectCheckBox(String VoucherNumber) {
		return "//td[contains(text(),'" + VoucherNumber + "')]/../td[13]/*";
	}

	/** @locatorType Xpath */
	public static final String BRPendingReceivablesDocumentSelectCheckBox(String VoucherNumber) {
		return "//td[contains(text(),'" + VoucherNumber + "')]/../td[13]/*";
	}

	/** @locatorType Xpath */
	public static final String ApprovedStatus(String PbNo) {
		return "//a[text()='" + PbNo + "']/../../td[13]";
	}

	/** @locatorType Xpath */
	public static final String CheckBox(String GrnNo) {
		return "//td[contains(text(),'" + GrnNo + "')]/../td[12]";
	}

	/** @locatorType Css Selector */
	public static final String CashLineAddButton(int i) {
		return "#ig_bank_recon_line_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr:nth-child(" + i
				+ ") > td:nth-child(7) > a";
	}

	/** @locatorType Css Selector */
	public static final String CashLineViewButton(int i) {
		return "//*[@id='ig_bank_recon_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[8]/button";
	}

	/** @locatorType Css Selector */
	public static final String CashLineGSTButton(int i) {
		return "#ig_bank_recon_line_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr:nth-child(" + i
				+ ") > td:nth-child(11) > a";
	}

	/** @locatorType Xpath */
	public static final String BRAdvPendingReceivablesDocumentSelectCheckBox(String VoucherNumber) {
		return "//td[contains(text(),'" + VoucherNumber + "')]/../td[14]/*";
	}
}
