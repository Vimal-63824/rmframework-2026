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
	public static final String AllCheckBox = "//table[@id='1636069529759209034']//input[@id='check_all']";
	/** @locatorType Xpath */
	public static final String FindProdNoLovBtn = "//button[@id='P93131177_PROD_ORD_NO_lov_btn']";

	/** @locatorType Xpath */
	public static final String MRVCheckBox(String GrnNo) {
		return "//td[text()='" + GrnNo + "']/../td[11]";
	}

	/** @locatorType Xpath */
	public static final String MaterialReceiptStatusTableText(String Mrno) {
		return "//a[text()='" + Mrno + "']/../../td/div[contains(@style,'Green')]";
	}

	/** @locatorType Xpath */
	public static final String PendingCreateMRV_TableRecipientWH(String CompletionProdNo) {
		return "//div[@id='PEND_data_panel']//table/tbody/tr/td[text()='" + CompletionProdNo + "']/../td[16]";
	}

	/** @locatorType Xpath */
	public static final String PendingCreateMRV_TableProcess(String CompletionProdNo) {
		return "//div[@id='PEND_data_panel']//table/tbody/tr/td[text()='" + CompletionProdNo + "']/../td[21]";
	}

	/** @locatorType Xpath */
	public static final String ListItemTable = "//div[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[3]";

	/** @locatorType Xpath */
	public static final String ItemTable(int i) {
		return "//div[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[3]";
	}

	/** @locatorType Xpath */
	public static final String UnitcostTable(int i) {
		return "//div[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[9]";
	}

	/** @locatorType Xpath */
	public static final String CreateJournal = "//button[@id='B1648817004038529035']";
	/** @locatorType Xpath */
	public static final String ViewJournal = "//button[@id='B4670098219152921908']";
	/** @locatorType Xpath */
	public static final String MIVNumberLovButton = "//button[@id='P9313117703_DOC_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String MRVSearchButton = "//button[@id='B2057629649584279009']";
	/** @locatorType Xpath */
	public static final String VoucherNoLov = "//button[@id='P9313117703_VOU_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String MrvSelectAllBtn = "//input[@id='check_all']";
	/** @locatorType Xpath */
	public static final String MrvPendingVoucherSearchBtn = "//button[@id='B2057629649584279009']";
	/** @locatorType Xpath */
	public static final String CreateMrvBtn = "//button[@id='B1']";
	/** @locatorType Xpath */
	public static final String ReceiptLocatorfield = "//button[@id='B1869920545287966421']";
	/** @locatorType Xpath */
	public static final String MrvDocumentNumberToastAlert = "//h6[@class='succ_msg']";
	/** @locatorType Xpath */
	public static final String VoucherNo = "//button[@id='P9313117703_VOU_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String MrvDocLovBtn = "//button[@id='P93131177_VOU_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String MrvDocNumberDrillDown = "//*[@id=\"2708497647223109870_orig\"]/tbody/tr[2]/td[5]/a";
	/** @locatorType Xpath */
	public static final String MrvDocNumberSearchBtn = "//button[@id='B1651762009575460806']";
	/** @locatorType Xpath */
	public static final String SourceVoucher = "//input[@id='P93131177_SOURCE_VOU_NO']";

	/** @locatorType Xpath */
	public static final String MrvNumber(String GrnNo) {
		return "(//td[text()='" + GrnNo + "']/../td/a)[2]";
	}

}
