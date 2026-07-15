package commonlocators.financials;

/**
 * @author Harikrishna - RMITS
 * @company RoadMap IT Solutions October 16, 2025
 */
public class CashPaymentLocators {
	/** @locatorType Xpath */
	public static final String BankAddButton = "//button[@data-testid='BankTrans']";
	/** @locatorType Xpath */
	public static final String BankPrefixLovButton = "//input[@data-testid='Prefix']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String BankModeDropDown = "//select[@data-testid='DocMode']";
	/** @locatorType Xpath */
	public static final String BankLovbutton = "//input[@data-testid='P341310009_BTRANS_BANK_ID']/following-sibling::button";
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
	public static final String BankLineWiseAddButton = "//button[@id='AddLine']";
	/** @locatorType Xpath */
	public static final String BankLineGLAccountField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[1]";
	/** @locatorType Xpath */
	public static final String BankLineGLAccountLovButton = "//INPUT[@data-testid='BTDLN_ACCT']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String BankLineCPCField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[4]";
	/** @locatorType Xpath */
	public static final String BankLineCPCLovButton = "//INPUT[@data-testid='BTDLN_CC_DESC']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String BankLineWiseSaveButton = "//button[@id='SaveLine']";
	/** @locatorType Xpath */
	public static final String BankLineWiseDeleteButton = "#ig_line_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr > td:nth-child(10) > a";
	/** @locatorType Xpath */
	public static final String BankLineWiseViewButton = "#ig_line_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr > td:nth-child(9) > a";
	/** @locatorType Xpath */
	public static final String BankViewAddButton = "//button[@title='Add']";
	/** @locatorType Xpath */
	public static final String BankViewFrame = "(//h1[contains(text(),'Payment Details')]/ancestor::div/following-sibling::div)//iframe";
	/** @locatorType Xpath */
	public static final String BankBillNumberInputField = "//input[@id='C2704515064031309570']";
	/** @locatorType Xpath */
	public static final String BankPayAmountInputField = "//input[@id='C2704509090913309563']";
	/** @locatorType Xpath */
	public static final String BankAgainstRefFieldName = "//*[@id='ig_line1_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[1]";
	/** @locatorType Xpath */
	public static final String BankBillDocField = "//*[@id='ig_line1_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[2]";
	/** @locatorType Xpath */
	public static final String BankPayAmountField = "//*[@id='ig_line1_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[4]";
	/** @locatorType Xpath */
	public static final String BankViewSaveButton = "//button[@id='B2717316769741773314']";
	/** @locatorType Xpath */
	public static final String BankViewDeleteButton = "#ig_line1_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr > td:nth-child(27) > a";
	/** @locatorType Xpath */
	public static final String BankLineAmountInTCField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[5]";
	/** @locatorType Xpath */
	public static final String BankLineAssesableValueField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[19]";
	/** @locatorType Xpath */
	public static final String BankHeaderCreateJournalButton = "//button[@data-testid='CreateJournal']";
	/** @locatorType Xpath */
	public static final String BankHeaderViewJournalButton = "//button[@data-testid='ViewJournal']";
	/** @locatorType Xpath */
	public static final String BankViewScreenCloseButton = "//h1[contains(text(),'Payment Details')]/ancestor::div/child::div/button";
	/** @locatorType Xpath */
	public static final String BankWorkFlowApprovalFrame = "//iframe[@title='Workflow Approval']";
	/** @locatorType Xpath */
	public static final String BankLineAddButton = "#ig_line_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr > td:nth-child(8) > a";
	/** @locatorType Xpath */
	public static final String BankPendingPartyDocumentsButton = "//button[@id='PEND_PAY']";
	/** @locatorType Xpath */
	public static final String BankPendingPayablesAdvanceButton = "//button[@id='PEND_PAY_ADV']";
	/** @locatorType Xpath */
	public static final String BankPendingPartyDocumentsFrameName = "//iframe[@title='Pending Payables']";
	/** @locatorType Xpath */
	public static final String BankPendingPartyDocumentsPartyNameInputField = "//input[@data-testid='PartyName']";
	/** @locatorType Xpath */
	public static final String BankPendingPartyDocumentScreenOkButton = "//span[text()='OK']/ancestor::button";
	/** @locatorType Xpath */
	public static final String BankPaymentGetDocumentButton = "//button[@data-testid='GetDoc']";
	/** @locatorType Xpath */
	public static final String BankPaymentPendingPartyDocumentPartyLovButton = "//button[@id='P3413101001_PART_NAME_lov_btn']";
	/** @locatorType Xpath */
	public static final String BankPaymentPendingPartyDocumentSearchButton = "//span[text()='Search']/ancestor::button";
	/** @locatorType Xpath */
	public static final String BankPaymentPendingPartyNameColumnNameField = "//*[@id='2550108912065853115_orig']/tbody/tr[2]/td[2]";
	/** @locatorType Xpath */
	public static final String PendingCRDocsButton = "//span[contains(text(),'Pending Cr. Docs.')]/ancestor::button";
	/** @locatorType Xpath */
	public static final String PendingPayablesButton = "//button[@data-testid='PendingPayables']";
	/** @locatorType Xpath */
	public static final String PendingPayablesDocNumberLovButton = "//input[@data-testid='P3413101001_DOC_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PendingPayablesDocumentNumberField = "//table[contains(@aria-label,'Pending Payables')]/tbody/tr/td[21]";
	/** @locatorType Xpath */
	public static final String PendingPayablesCreateButton = "//button[@data-testid='Create']";
	/** @locatorType Xpath */
	public static final String PendingPayablesPayModeDropDown = "//select[@id='P3413101001_SUPHD_PYMT_TYPE']";
	/** @locatorType Xpath */
	public static final String PendingPayablesBankNameLOVBUtton = "//button[@id='P3413101001_SUPHD_BANK_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String PendingPayablesOkButton = "//button[@data-testid='Ok']";
	/** @locatorType Xpath */
	public static final String PendingPayablesTypeDropDown = "//select[@id='P3413101001_SUPHD_TYPE']";
	/** @locatorType Xpath */
	public static final String PendingPayablesTypeField = "//div[@id='P3413101001_SUPHD_TYPE_CONTAINER']";
	/** @locatorType Xpath */
	public static final String PendingpayablesPaymodeField = "//div[@id='P3413101001_SUPHD_PYMT_TYPE_CONTAINER']";
	/** @locatorType Xpath */
	public static final String CashPaymentScreenName = "//span[contains(text(),'Cash Payment')]/ancestor::a";
	/** @locatorType Xpath */
	public static final String BankPaymentScreenName = "//span[contains(text(),'Bank Payment')]/ancestor::a";
	/** @locatorType Xpath */
	public static final String BankPaymentReferenceInputField = "//input[@data-testid='RefNo1']";
	/** @locatorType Xpath */
	public static final String BankHeaderWiseSaveButton = "//button[@data-testid='Save']";
	/** @locatorType Xpath */
	public static final String BankPartyDocNumberLovButton = "//input[@data-testid='P3413101001_DOC_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String BankPendingPartyDocumentClearButton = "//button[@data-testid='Clear12']";
	/** @locatorType Xpath */
	public static final String PendingPurchaseBill = "//button[@data-testid='PendingBills']";
	/** @locatorType Xpath */
	public static final String PendingGrn = "//button[@data-testid='PendingGRN']";
	/** @locatorType Xpath */
	public static final String Iframe = "//iframe";
	/** @locatorType Xpath */
	public static final String GrnNo = "//input[@data-testid=\"P116131004_GRN_NO\"]/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SearchButton = "//button[@data-testid='SearchGRN']";
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
	public static final String BankReceiptDocNumberLovButton = "//input[@data-testid='DocNo']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String BankReceiptPendingReceivablesSearchButton = "//button[@id='B1891436466362546326']";
	/** @locatorType Xpath */
	public static final String BankReceiptPendingReceivableScreenOkButton = "//button[@id='B4']";
	/** @locatorType Xpath */
	public static final String BankReceiptHeaderCreateJournalButton = "//button[@data-testid='CreateJournal']";
	/** @locatorType Xpath */
	public static final String BankReceiptLineWiseAddButton = "//button[@id='B1217119641398022948']";
	/** @locatorType Xpath */
	public static final String PendingPayablesMergeLabelInPending = "//label[@for='P3413101001_TYPE_1']";
	/** @locatorType Xpath */
	public static final String FrwdPersonField = "//input[@id='P236131090_FWD_PERSON']";
	/** @locatorType Xpath */
	public static final String BankReceiptPDCFlag = "//INPUT[@data-testid='PDC']";
	/** @locatorType Xpath */
	public static final String BakReciptPDCLabelName = "//input[@data-testid='PDC']/following-sibling::label";
	/** @locatorType Xpath */
	public static final String BankReciptRefDateInputField = "//*[@data-testid='P3413103001_BTRANS_CHQ_DATE']/input";
	/** @locatorType Xpath */
	public static final String BankpaymentChequeLovButton = "//input[@data-testid='P341310009_BTRANS_TRANS_REF_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String BankPaymentFavorOfInputField = "//input[@data-testid='Favorof']";
	/** @locatorType Xpath */
	public static final String BankReceiptHSNCodeField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[15]";
	/** @locatorType Xpath */
	public static final String BankReceiptLineHsnCodeLov = "//input[@data-testid='BTDLN_HSN_CODE']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String BankReceiptLineWiseGSTButton = "#ig_line_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr > td:nth-child(13) > a";
	/** @locatorType Xpath */
	public static final String BankPaymentChargesFlag = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[14]";
	/** @locatorType Xpath */
	public static final String BankPaymentChargesInputFlag = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[12]/div/input";
	/** @locatorType Xpath */
	public static final String BankPaymentTDSField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[24]";
	/** @locatorType Xpath */
	public static final String BankPaymentTDSLovButton = "//button[@id='tdsUS_lov_btn']";
	/** @locatorType Xpath */
	public static final String BankPaymentApplyDeduction = "//button[@data-testid='ApplyDeduction']";
	/** @locatorType Xpath */
	public static final String BankPaymentSourcePartyField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[32]";
	/** @locatorType Xpath */
	public static final String BankPaymentSourcePartyLovButton = "//button[@id='C6076125392744124607_lov_btn']";
	/** @locatorType Xpath */
	public static final String BankPayment2ndLine = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[2]/td[1]";
	/** @locatorType Xpath */
	public static final String CashPaymentPBPrefixLov = "//input[@data-testid='P116131004_INV_PFX']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String BankPendingAdvPartyDocumentsButton = "//span[text()='Pending  Payables Advance']/ancestor::button";
	/** @locatorType Xpath */
	public static final String BankPendingAdvPartyDocumentsFrameName = "//iframe[@title='Pend. Payables Advance']";
	/** @locatorType Xpath */
	public static final String BankPartyAdvDocNumberLovButton = "//input[@data-testid='DocNo']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String BankPaymentAdvPendingPartyDocumentSearchButton = "//span[text()='Search']/ancestor::button";
	/** @locatorType Xpath */
	public static final String BankPendingAdvPartyDocumentScreenOkButton = "//span[text()='OK']/ancestor::button";
	/** @locatorType Xpath */
	public static final String PendingAdvPayablesDocumentNumberField = "//*[@id='3229896933884208618_orig']/tbody/tr[2]/td[20]";
	/** @locatorType Xpath */
	public static final String PendingAdvancePayablesInPend = "//span[text()='Pending  Payables - Advance']/ancestor::button";
	/** @locatorType Xpath */
	public static final String PendAdvPayableSearchButton = "//button[@id='B3541963422209746679']";
	/** @locatorType Xpath */
	public static final String PendingAdvancePayablesDocumentNumberField = "//*[@id='3229896933884208618_orig']/tbody/tr[2]/td[20]";
	/** @locatorType Xpath */
	public static final String PendingAdvanceCreateButton = "//span[text()='Create']/ancestor::button";
	/** @locatorType Xpath */
	public static final String CashPendingPartyDocumentClearButton = "//button[@title='Home']/parent::div/button[@title='Clear']";
	/** @locatorType Xpath */
	public static final String PendingAdvPayablesMergeLabelInPending = "//label[@for='P34131022_PAY_TYPE_1']";
	/** @locatorType Xpath */
	public static final String PendingAdvPayablesTypeField = "//label[@id='P34131022_BCV_DOC_TYPE_LABEL']";
	/** @locatorType Xpath */
	public static final String PendingAdvPayablesTypeDropDown = "//select[@id='P34131022_BCV_DOC_TYPE']";
	/** @locatorType Xpath */
	public static final String PendingAdvpayablesPaymodeField = "//select[@id='P34131022_BCV_PAY_TYPE']";
	/** @locatorType Xpath */
	public static final String PendingAdvPayablesBankNameLOVBUtton = "//button[@id='P34131022_BCV_BC_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String BankPendingCRdocsAdvPartyDocumentScreenOkButton = "//span[text()='Ok']/ancestor::button";
	/** @locatorType Xpath */
	public static final String CashPaymentLineWiseTDSField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[24]";
	/** @locatorType Xpath */
	public static final String CashPaymentTDSLovButton = "//button[@id='tdsUS_lov_btn']";
	/** @locatorType Xpath */
	public static final String CashPaymentApplyDeductionButton = "//button[@id='Appl_Ded']";
	/** @locatorType Xpath */
	public static final String CashPaymentApplyDeductionCheckedButton = "//button[@id='Appl_Ded_1']";
	/** @locatorType Xpath */
	public static final String BankHeaderAdvCreateJournalButton = "//button[@data-testid='CreJournal']";

	public static final String CashHeaderWiseSaveButton1 = "//button[@data-testid='Save1']";

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
		return "//td[contains(text(),'" + GrnNo + "')]/../td[13]";
	}

	/** @locatorType Css Selector */
	public static final String BankLineAddButton(int i) {
		return "#ig_line_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr:nth-child(" + i
				+ ") > td:nth-child(8) > a";
	}

	/** @locatorType Css Selector */
	public static final String BankLineViewButton(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[9]/button";
	}

	/** @locatorType Css Selector */
	public static final String BankLineGSTButton(int i) {
		return "#ig_line_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr:nth-child(" + i
				+ ") > td:nth-child(14) > a";
	}

	/** @locatorType Xpath */
	public static final String LineWiseTDS(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[24]";
	}

}
