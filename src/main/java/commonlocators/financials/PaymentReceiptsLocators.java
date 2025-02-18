package commonlocators.financials;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
public class PaymentReceiptsLocators {
	/** @locatorType Xpath */
	public static final String PendingPayable = "//button[@id='B1540712450699548671']";
	/** @locatorType Xpath */
	public static final String PendingPayableFrame = "//iframe[@title='Pending Payables']";
	/** @locatorType Xpath */
	public static final String PendDocumentsNoLovBtn = "//button[@id='P3413101001_DOC_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String SearchButton = "//button[@id='B1889770507875176503']";
	/** @locatorType Xpath */
	public static final String CreateButton = "//button[@id='B5']";
	/** @locatorType Xpath */
	public static final String OkButton = "//button[@id='B1325850142310803053']";
	/** @locatorType Xpath */
	public static final String PayMode = "//select[@id='P3413101001_SUPHD_PYMT_TYPE']";
	/** @locatorType Xpath */
	public static final String Bank_Cash_LovButton = "//button[@id='P3413101001_SUPHD_BANK_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String BankAC = "//span[@id='P3413103001_BTRANS_BANK_ID_DISPLAY']";
	/** @locatorType Xpath */
	public static final String DrCr = "//div[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[6]";
	/** @locatorType Xpath */
	public static final String OtherDetails = "//div[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[15]";
	/** @locatorType Xpath */
	public static final String AccountFrame = "//iframe";
	/** @locatorType Xpath */
	public static final String AccountNoLovButton = "//button[@id='P3413103015_BTDLN_ACCT_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String AccountTickIcon = "//button[@id='B2391685692816247745']";
	/** @locatorType Xpath */
	public static final String CloseIcon = "//h1[contains(text(),'Other Details ')]/../button";
	/** @locatorType Xpath */
	public static final String SaveButton = "//button[@id='SAVE_BTN']";
	/** @locatorType Xpath */
	public static final String PostButton = "//button[@id='POST']";
	/** @locatorType Xpath */
	public static final String BpvNumber = "//span[@id='P3413103001_BTRANS_ORD_NO_DISPLAY']";
	/** @locatorType Xpath */
	public static final String ReferenceInputNumber = "//input[@id='P3413103001_TRANS_REF_NO']";
	/** @locatorType Xpath */
	public static final String WorkApprovalOkButton = "//button[@id='B1457704718951134857']";
	/** @locatorType Xpath */
	public static final String VoucherNumberLovButton = "//button[@id='P341310009_ORD_PFXNO_lov_btn']";
	/** @locatorType Xpath */
	public static final String ApproveSearchButton = "//button[@id='B175959722172441265']";

	/** @locatorType Xpath */
	public static final String IssuedStatus(String BpvNumber) {
		return "(//a[text()='" + BpvNumber + "']/../../td/div[text()='Issued'])";
	}

	/** @locatorType Xpath */
	public static final String CheckBox(String PbNo) {
		return "//td[text()='" + PbNo + "']/../td[13]";
	}

	/** @locatorType Xpath */
	public static final String PendingDrDocsBtn = "//button[@id='B1918502607122484020']";
	/** @locatorType Xpath */
	public static final String PendingReceivablesBtn = "//span[text()='Pending Receivable']/parent::button";
	/** @locatorType Xpath */
	public static final String PendingReceivablesIframe = "//iframe[@title='Pending Receivables']";
	/** @locatorType Xpath */
	public static final String PendReceive_FindInvoiceNoLovBtn = "//button[@id='P3413101003_DOC_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String PendReceive_FindDocTypeSelect = "//select[@id='P3413101003_DOC_TYPE']";
	/** @locatorType Xpath */
	public static final String PendReceive_SearchBar = "//input[@id='PEND_search_field']";
	/** @locatorType Xpath */
	public static final String PendReceive_SearchGoBtn = "//button[@id='PEND_search_button']";
	/** @locatorType Xpath */
	public static final String PendReceive_SearchBtn = "//button[@id='B1889773523896176533']";
	/** @locatorType Xpath */
	public static final String PendReceive_CreateBtn = "//button[@id='B1']";
	/** @locatorType Xpath */
	public static final String PendReceive_SingleRadioBtn = "//label[@for='P3413101003_GLOBAL_TYPE_0']";
	/** @locatorType Xpath */
	public static final String PendReceive_MergeRadioBtn = "//label[@for='P3413101003_GLOBAL_TYPE_1']";
	/** @locatorType Xpath */
	public static final String PendReceive_PaymentType = "//select[@id='P3413101003_SUPHD_TYPE']";
	/** @locatorType Xpath */
	public static final String PendReceive_PaymentMode = "//select[@id='P3413101003_PAY_MODE']";
	/** @locatorType Xpath */
	public static final String PendReceive_BankCashierLovBtn = "//button[@id='P3413101003_BANK_lov_btn']";
	/** @locatorType Xpath */
	public static final String PendReceive_OkBtn = "//button[@id='B1547715561905421086']";
	/** @locatorType Xpath */
	public static final String BankReceipt_RefNoInput = "//input[@id='P3413103005_BTRANS_TRANS_REF_N']";
	/** @locatorType Xpath */
	public static final String BankReceipt_HeaderSaveBtn = "//button[@id='SAVE']";
	/** @locatorType Xpath */
	public static final String BankReceipt_CreateJournalBtn = "//button[@id='CREATE_JOURNAL']";
	/** @locatorType Xpath */
	public static final String BankReceipt_ViewJournalBtn = "//button[@id='B1269680928025184394']";
	/** @locatorType Xpath */
	public static final String BankReceipt_PostBtn = "//button[@id='POST']";
	/** @locatorType Xpath */
	public static final String BankReceipt_ViewJournalIframe = "//iframe[@title='View Journal']";
	/** @locatorType Xpath */
	public static final String ViewJournal_TotalAmountDebitField = "(//td[@headers='db'])[last()]/span[@class='a-IRR-aggregate-value']";
	/** @locatorType Xpath */
	public static final String ViewJournal_TotalAmountCreditField = "(//td[@headers='cr'])[last()]/span[@class='a-IRR-aggregate-value']";
	/** @locatorType Xpath */
	public static final String ViewJournal_CloseIcon = "//h1[text()='View Journal']/../child::button";
	/** @locatorType Xpath */
	public static final String BankReceipt_TableDRCRvalue = "(//table//tbody/tr[contains(@class,'a-GV-row')]/td[6])[1]";
	/** @locatorType Xpath */
	public static final String BankReceipt_TotalTCvalue = "//span[@id='tc']";
	/** @locatorType Xpath */
	public static final String BankReceipt_TotalBCvalue = "//span[@id='bc']";
	/** @locatorType Xpath */
	public static final String BankReceipt_VoucherNoText = "//span[@id='P3413103005_BTRANS_ORD_NO_DISPLAY']";

	/** @locatorType Xpath */
	public static final String PendReceive_CheckBox(String text) {
		return "//td[text()='" + text + "']/../td[13]";
	}
}
