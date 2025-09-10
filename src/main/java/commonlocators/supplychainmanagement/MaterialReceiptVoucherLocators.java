package commonlocators.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
public class MaterialReceiptVoucherLocators {

	/** @locatorType Xpath */
	public static final String Transactions = "//span[contains(text(),'Transaction')]";
	/** @locatorType Xpath */
	public static final String PendingInsurance = "//button[@data-testid='PendingInsurance']";
	/** @locatorType Xpath */
	public static final String VoucherNumberLovButton = "//input[@data-testid='VoucherNumberLovButton']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SearchButton = "//button[@data-testid='SearchButton']";
	/** @locatorType Xpath */
	public static final String PendingSearchField = "//input[@id='PEND_search_field']";
	/** @locatorType Xpath */
	public static final String GoButton = "//button[@id='PEND_search_button']";
	/** @locatorType Xpath */
	public static final String MrvFrame = "//iframe[@title='Create MRV']";
	/** @locatorType Xpath */
	public static final String LocationId = "//span[@data-testid='LocationId']";
	/** @locatorType Xpath */
	public static final String MRVNO = "//span[@data-testid='MRVNO']";
	/** @locatorType Xpath */
	public static final String RecptorLocators = "//button[@id='B1872975695598792964']";
	/** @locatorType Xpath */
	public static final String PutAway = "//button[@id='B4718103669908097644']";
	/** @locatorType Xpath */
	public static final String TransferFrame = "//iframe[@title='Transfer']";
	/** @locatorType Xpath */
	public static final String CloseIcon = "//h1[text()='Transfer']/../button";
	/** @locatorType Xpath */
	public static final String HeaderTickIcon = "//button[@id='B4586494541549467581']";
	/** @locatorType Xpath */
	public static final String HeaderPostIcon = "//button[@id='B4673153071460748448']";
	/** @locatorType Xpath */
	public static final String AllCheckBox = "//table[@id='1639124680070035577']//input[@id='check_all']";
	/** @locatorType Xpath */
	public static final String FindProdNoLovBtn = "//input[@data-testid='FindProdNoLovBtn']/following-sibling::button";

	/** @locatorType Xpath */
	public static final String MRVCheckBox(String GrnNo) {
		return "//td[text()='" + GrnNo + "']/../td[12]";
	}

	/** @locatorType Xpath */
	public static final String MaterialReceiptStatusTableText(String Mrno) {
		return "//a[text()='" + Mrno + "']/../../td/div[contains(@style,'Green')]";
	}

	/** @locatorType Xpath */
	public static final String PendingCreateMRV_TableRecipientWH(String CompletionProdNo) {
		return "//div[@id='PEND_data_panel']//table/tbody/tr/td[text()='" + CompletionProdNo + "']/../td[17]";
	}

	/** @locatorType Xpath */
	public static final String PendingCreateMRV_TableProcess(String CompletionProdNo) {
		return "//div[@id='PEND_data_panel']//table/tbody/tr/td[text()='" + CompletionProdNo + "']/../td[22]";
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
	public static final String CreateJournal = "//button[@id='B1651872154349355578']";
	/** @locatorType Xpath */
	public static final String ViewJournal = "//button[@id='B4673153369463748451']";
	/** @locatorType Xpath */
	public static final String MIVNumberLovButton = "//input[@data-testid='MIVNumberLovButton']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String MRVSearchButton = "//button[@data-testid='MRVSearchButton']";
	/** @locatorType Xpath */
	public static final String VoucherNoLov = "//input[@data-testid='VoucherNoLov']/following-sibling::button";
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
	public static final String VoucherNo = "//input[@data-testid='VoucherNoLov']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String MrvDocLovBtn = "//input[@data-testid='VoucherNumberLovButton']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String MrvDocNumberDrillDown = "//*[@id='2708497647223109870_orig']/tbody/tr[2]/td[5]/a";
	/** @locatorType Xpath */
	public static final String MrvDocNumberSearchBtn = "//button[@id='B1651762009575460806']";
	/** @locatorType Xpath */
	public static final String SourceVoucher = "//input[@id='P93131177_SOURCE_VOU_NO']";

	/** @locatorType Xpath */
	public static final String MrvNumber(String GrnNo) {
		return "(//td[text()='" + GrnNo + "']/../td/a)[2]";
	}

	/** @locatorType Xpath */
	public static final String UnitCost = "//div[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[9]";
	/** @locatorType Xpath */
	public static final String MrvDocStatus = "//*[@id=\"2711552797533936413_orig\"]/tbody/tr[2]/td[23]/div";
	/** @locatorType Xpath */
	public static final String MrvDocwiseCancelbutton = "//button[@id='B4673153207168748449']";

	/** @locatorType Xpath */
	public static final String LocId = "//button[@id='P93131177_LOCATION_lov_btn']";

	/** @locatorType Xpath */
	public static final String Unit = "//button[@id='P93131177_PLNT_lov_btn']";

	/** @locatorType Xpath */
	public static final String MRVNumber = "//button[@id='P93131177_VOU_NO_lov_btn']";

	/** @locatorType Xpath */
	public static final String ReceiverType = "//select[@id='P93131177_ISSUE_TYPE']";

	/** @locatorType Xpath */
	public static final String ProdordNumber = "//button[@id='P93131177_PROD_ORD_NO_lov_btn']";

	/** @locatorType Xpath */
	public static final String SourceVoucherNumber = "//input[@id='P93131177_SOURCE_VOU_NO']";

	/** @locatorType Xpath */
	public static final String ReceiverWHID = "//button[@id='P93131177_RCVR_WH_lov_btn']";

	/** @locatorType Xpath */
	public static final String ReceiverWHDesc = "//input[@id='P93131177_RCVR_WH_DESC']";

	/** @locatorType Xpath */
	public static final String ItemId = "//button[@id='P93131177_PROD_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String ItemDesc = "//input[@id='P93131177_PROD_DESC']";
	/** @locatorType Xpath */
	public static final String MRVStatus = "//select[@id='P93131177_STATUS_1']";
	/** @locatorType Xpath */
	public static final String HeaderCheckBox = "//label[contains(text(),'Header')]";
	/** @locatorType Xpath */
	public static final String OpenPoSearchButton = "//button/span[text()='Search']";
	/** @locatorType Xpath */
	public static final String LineCheckBox = "//label[text()='Line(s)      ']";
	/** @locatorType Xpath */
	public static final String ClearButton = "//button[@title='Clear']";
	/** @locatorType Xpath */
	public static final String MRVdateFrom = "//input[@id='P93131177_DATE_FROM_input']";
	/** @locatorType Xpath */
	public static final String MRVdateTo = "//input[@id='P93131177_DATE_TO_input']";
	/** @locatorType Xpath */
	public static final String AmendFindTable = "//*[@id=\"717935528327513976_orig\"]/tbody/tr[2]/td[2]";

	/** @locatorType Xpath */
	public static final String AmendLineFindTable = "//*[@id=\"2711552797533936413_orig\"]/tbody/tr[2]/td[2]";

	/** @locatorType Xpath */
	public static final String AmendFindTableData(int i) {
		return "//table[@id='717935528327513976_orig']/tbody/tr/td[" + i + "]";
	}

	/** @locatorType Xpath */
	public static final String AmendLineFindTableData(int i) {
		return "//table[@id='2711552797533936413_orig']/tbody/tr/td[" + i + "]";
	}

	/** @locatorType Xpath */
	public static final String CreateMRVButton = "//button[@id='B1']";
}
