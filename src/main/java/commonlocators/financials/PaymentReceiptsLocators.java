package commonlocators.financials;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
public class PaymentReceiptsLocators {
	/** @locatorType Xpath */
	public static final String PendingPayable = "//button[@data-testid='PendingPayables']";
	/** @locatorType Xpath */
	public static final String PendingPayableFrame = "//iframe[@title='Pending Payables']";
	/** @locatorType Xpath */
	public static final String PendDocumentsNoLovBtn = "//input[contains(@data-testid,'DOC_NO')]/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SearchButton = "//button[@data-testid='Search1']";
	/** @locatorType Xpath */
	public static final String CreateButton = "//button[@data-testid='Create']";
	/** @locatorType Xpath */
	public static final String OkButton = "//button[@data-testid='Ok']";
	/** @locatorType Xpath */
	public static final String PayMode = "//select[@id='P3413101001_SUPHD_PYMT_TYPE']";
	/** @locatorType Xpath */
	public static final String Bank_Cash_LovButton = "//button[@id='P3413101001_SUPHD_BANK_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String BankAC = "//span[@data-testid='BankAC']";
	/** @locatorType Xpath */
	public static final String DrCr = "//div[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[6]";
	/** @locatorType Xpath */
	public static final String OtherDetails = "//div[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[15]";
	/** @locatorType Xpath */
	public static final String AccountFrame = "//iframe";
	/** @locatorType Xpath */
	public static final String AccountNoLovButton = "//button[@id='P3413103015_BTDLN_ACCT_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String AccountTickIcon = "//button[@id='B2393348635282617538']";
	/** @locatorType Xpath */
	public static final String CloseIcon = "//h1[contains(text(),'Other Details ')]/../button";
	/** @locatorType Xpath */
	public static final String SaveButton = "//button[@data-testid='Save']";
	/** @locatorType Xpath */
	public static final String PostButton = "//button[@data-testid='Post']";
	/** @locatorType Xpath */
	public static final String BpvNumber = "//span[@data-testid='VouNo']";
	/** @locatorType Xpath */
	public static final String ReferenceInputNumber = "//input[@data-testid='RefNo1']";
	/** @locatorType Xpath */
	public static final String WorkApprovalOkButton = "//span[text()='Ok']/..";
	/** @locatorType Xpath */
	public static final String VoucherNumberLovButton = "//input[@data-testid='VoucherPfx']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String ApproveSearchButton = "//button[@data-testid='Search']";

	/** @locatorType Xpath */
	public static final String IssuedStatus(String BpvNumber) {
		return "(//a[text()='" + BpvNumber + "']/../../td/div[text()='Issued'])[last()]";
	}

	/** @locatorType Xpath */
	public static final String CheckBox(String PbNo) {
		return "//td[text()='" + PbNo + "']/../td[13]";
	}

	/** @locatorType Xpath */
	public static final String PendingDrDocsBtn = "//button[@data-testid='PendingDrDocs']";
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
	public static final String PendReceive_SearchBtn = "//button[@id='B1891436466362546326']";
	/** @locatorType Xpath */
	public static final String PendReceive_CreateBtn = "//button[@id='B5']";
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
	public static final String PendReceive_OkBtn = "//button[@id='B1549378504371790879']";
	/** @locatorType Xpath */
	public static final String BankReceipt_RefNoInput = "//input[@id='P3413103005_BTRANS_TRANS_REF_N']";
	/** @locatorType Xpath */
	public static final String BankReceipt_HeaderSaveBtn = "//button[@id='SAVE']";
	/** @locatorType Xpath */
	public static final String BankReceipt_CreateJournalBtn = "//button[@id='CREATE_JOURNAL']";
	/** @locatorType Xpath */
	public static final String BankReceipt_ViewJournalBtn = "//button[@id='B1271343870491554187']";
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

	/** @locatorType Xpath */
	public static final String ExpandButton = "//button[@id='t_Button_rightControlButton'][@aria-expanded='true']";
	/** @locatorType Xpath */
	public static final String SelectBank = "//select[@id='P3413101001_SUPHD_TYPE']";
}
