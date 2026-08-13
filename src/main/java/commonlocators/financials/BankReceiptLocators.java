package commonlocators.financials;

/**
 * @author Harikrishna - RMITS
 * @company RoadMap IT Solutions October 16, 2025
 */
public class BankReceiptLocators {
	/** @locatorType Xpath */
	public static final String BankAddButton = "//button[@data-testid='BankTrans']";
	/** @locatorType Xpath */
	public static final String BankPrefixLovButton = "//input[@data-testid='Prefix']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String BankModeDropDown = "//select[@data-testid='DocMode']";
	/** @locatorType Xpath */
	public static final String BankLovbutton = "//button[@id='P341310009_BTRANS_BANK_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String BankVoucherDateinputField = "//input[@id='P341310009_BTRANS_PV_DATE_input']";
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
	public static final String BankSaveButton = "//button[@data-testid='Add']";
	/** @locatorType Xpath */
	public static final String BankRefHiddenInputField = "//input[@id='P341310009_BTRANS_TRANS_REF_NO_HIDDENVALUE']";
	/** @locatorType Xpath */
	public static final String BankTransCurrencyHiddenInputField = "//input[@id='P341310009_BTRANS_TRANS_CURCY_HIDDENVALUE']";
	/** @locatorType Xpath */
	public static final String BankPostButton = "//button[@data-testid='Post']";
	/** @locatorType Xpath */
	public static final String BankRefInputField = "//input[@id='P341310009_BTRANS_TRANS_REF_NO1']";
	/** @locatorType Xpath */
	public static final String BankDocumentNumberField = "//span[@data-testid='VouNo']";
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
	public static final String BankLineGLAccountField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[1]";
	/** @locatorType Xpath */
	public static final String BankLineGLAccountLovButton = "//input[@data-testid='BTDLN_ACCT']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String BankLineCPCField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[4]";
	/** @locatorType Xpath */
	public static final String BankLineCPCLovButton = "//input[@data-testid='BTDLN_CC_DESC']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String BankLineWiseSaveButton = "//button[@data-testid='Save_Line']";
	/** @locatorType Xpath */
	public static final String BankLineWiseDeleteButton = "#ig_line_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr > td:nth-child(10) > a";
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
	public static final String BankReceiptViewDeleteButton = "//*[@id='line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[28]/a";
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
	public static final String CashPaymentScreenName = "//span[contains(text(),'Cash Payment')]/ancestor::a";
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
	public static final String BankReceiptHeaderCreateJournalButton = "//button[@data-testid='CreateJournal']";
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
	public static final String BankReceiptChargesInputFlag = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[14]/div/input";
	/** @locatorType Xpath */
	public static final String BankReceiptAmountInTcInputField = "//input[@data-testid='BTDLN_DIST_AMT']";
	/** @locatorType Xpath */
	public static final String BankReceiptLineATCField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[5]";
	/** @locatorType Xpath */
	public static final String BankReceiptLineCRDRDropDown = "//select[@data-testid='BTDLN_DR_CR']";
	/** @locatorType Xpath */
	public static final String BanReceiptCRDRField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[6]";
	/** @locatorType Xpath */
	public static final String BankReceiptPDCVoucherNumberField = "//input[@id='P34131031_VOU_NO']";
	/** @locatorType Xpath */
	public static final String BankReceiptPDCSearchButton = "//button[@id='B802285788346712667']";
	/** @locatorType Xpath */
	public static final String BankReceiptPDCSelectAllButton = "//input[@id='check_all']";
	/** @locatorType Xpath */
	public static final String BankReceiptPDCPostButton = "//button[@id='B1']";
	/** @locatorType Xpath */
	public static final String BankReceiptHSNCodeField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[16]";
	/** @locatorType Xpath */
	public static final String BankReceiptLineHsnCodeLov = "//input[@data-testid='BTDLN_HSN_CODE']/following-sibling::button";
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
	public static final String BankReceiptPendingReceivablesAdvButton = "//button[@id='pend_rec_adv']";
	/** @locatorType Xpath */
	public static final String BankReceiptPendingReceivablesAdvFrameName = "//iframe[@title='Pend. Receivable - Advance']";
	/** @locatorType Xpath */
	public static final String BankReceiptPendingReceivablesAdvPartyNameInputField = "//input[@id='P341310231_PARTY_NAME']";
	/** @locatorType Xpath */
	public static final String BankReceiptPendingReceivablesAdvanceDocNumberLovButton = "//button[@id='P341310231_DOC_NO_FND_lov_btn']";
	/** @locatorType Xpath */
	public static final String BankReceiptPendingReceivablesAdvSearchButton = "//span[text()='Search']/ancestor::button";
	/** @locatorType Xpath */
	public static final String PendingReceivablesAdvanceDocumentNumberField = "//*[@id='2995376176452410474_orig']/tbody/tr[2]/td[22]";
	/** @locatorType Xpath */
	public static final String PendingDRPendingreceivablesAdvanceButton = "//span[text()='Pending Receivable - Advance']/ancestor::button";
	/** @locatorType Xpath */
	public static final String PendingReceivablesAdvanceCreateButton = "//SPAN[text()='Create']/ancestor::button";
	/** @locatorType Xpath */
	public static final String BankPendingReceivablesAdvancePartyDocumentClearButton = "//button[@title='Home']/parent::div/button[@title='Clear']";
	/** @locatorType Xpath */
	public static final String PendingReceivablesAdvanceMergeLabelInPending = "//label[@for='P341310231_GLOBAL_SCREEN_1']";
	/** @locatorType Xpath */
	public static final String PendingReceivablesAdvanceTypeDropDown = "//select[@id='P341310231_TYPE']";
	/** @locatorType Xpath */
	public static final String PendingReceivablesAdvancePayModeDropDown = "//select[@id='P341310231_BANK_TYPE']";
	/** @locatorType Xpath */
	public static final String PendingReceivablesAdvanceBankNameLOVBUtton = "//button[@id='P341310231_BANK_DESC_lov_btn']";
	/** @locatorType Xpath */
	public static final String PendingReceivablesAdvanceOkButton = "//span[text()='Ok']/ancestor::button";
	/** @locatorType Xpath */
	public static final String ReceivedFromRequiredInputField = "//input[@data-testid='ReceivedFrom']";
	/** @locatorType Xpath */
	public static final String BankNameLovButtonInHeader = "//input[@data-testid=' BankName']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String BankNameInputField = "//input[@data-testid=' BankName']";

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
	public static final String BankLineAddButton(int i) {
		return "#ig_line_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr:nth-child(" + i
				+ ") > td:nth-child(8) > a";
	}

	/** @locatorType Css Selector */
	public static final String BankLineViewButton(int i) {
		return "#ig_line_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr:nth-child(" + i
				+ ") > td:nth-child(9) > a";
	}

	/** @locatorType Css Selector */
	public static final String BankLineGSTButton(int i) {
		return "#ig_line_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr:nth-child(" + i
				+ ") > td:nth-child(13) > a";
	}

	/** @locatorType Xpath */
	public static final String BRAdvPendingReceivablesDocumentSelectCheckBox(String VoucherNumber) {
		return "//td[contains(text(),'" + VoucherNumber + "')]/../td[14]/*";
	}

	/** @locatorType Xpath */
	public static final String SaveButton = "//button[@id='SAVE_BTN']";
}
