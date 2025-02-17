package commonlocators.financials;

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
}
