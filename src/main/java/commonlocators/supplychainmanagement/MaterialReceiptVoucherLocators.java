package commonlocators.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
public class MaterialReceiptVoucherLocators {

	/** @locatorType Xpath */
	public static final String Transactions = "//span[contains(text(),'Transaction')]";
	/** @locatorType Xpath */
	public static final String PendingInsurance = "//button[@id='B1648816358459529028']";
	/** @locatorType Xpath */
	public static final String VoucherNumberLovButton = "//button[@id='P93131177_VOU_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String SearchButton = "//button[@id='B1651762009575460806']";
	/** @locatorType Xpath */
	public static final String PendingSearchField = "//input[@id='PEND_search_field']";
	/** @locatorType Xpath */
	public static final String GoButton = "//button[@id='PEND_search_button']";
	/** @locatorType Xpath */
	public static final String MrvFrame = "//iframe[@title='Create MRV']";
	/** @locatorType Xpath */
	public static final String LocationId = "//span[@id='P9313117701_ISTHD_PLNT_LOC_ID_DISPLAY']";
	/** @locatorType Xpath */
	public static final String MRVNO = "//span[@id='P9313117701_ISTHD_DOC_NO_DISPLAY']";
	/** @locatorType Xpath */
	public static final String RecptorLocators = "//button[@id='B1869920545287966421']";
	/** @locatorType Xpath */
	public static final String PutAway = "//button[@id='B4715048519597271101']";
	/** @locatorType Xpath */
	public static final String TransferFrame = "//iframe[@title='Transfer']";
	/** @locatorType Xpath */
	public static final String CloseIcon = "//h1[text()='Transfer']/../button";
	/** @locatorType Xpath */
	public static final String HeaderTickIcon = "//button[@id='B4583439391238641038']";
	/** @locatorType Xpath */
	public static final String HeaderPostIcon = "//button[@id='B4670097921149921905']";

	/** @locatorType Xpath */
	public static final String MRVCheckBox(String GrnNo) {
		return "//td[text()='" + GrnNo + "']/../td[11]";
	}

	/** @locatorType Xpath */
	public static final String MaterialReceiptStatusTableText(String Mrno) {
		return "//a[text()='" + Mrno + "']/../../td[last()]";
	}
}
