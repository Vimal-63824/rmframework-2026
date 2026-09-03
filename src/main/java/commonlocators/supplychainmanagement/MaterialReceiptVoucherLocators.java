package commonlocators.supplychainmanagement;

import commonfunctions.CommonFunctions;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
public class MaterialReceiptVoucherLocators {

	/** @locatorType Xpath */
	public static final String Transactions = "//span[contains(text(),'Transaction')]";
	/** @locatorType Xpath */
	public static final String PendingIssuance = "//button[@data-testid='PendingInsurance']";
	/** @locatorType Xpath */
	public static final String VoucherNumberLovButton = "//button[@id='P93131177_VOU_NO_lov_btn']";
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
	public static final String CloseIcon = "//*[@id=\"t_PageBody\"]/div[18]/div[1]/button";
	/** @locatorType Xpath */
	public static final String HeaderTickIcon = "//button[@data-testid='Save']";
	/** @locatorType Xpath */
	public static final String HeaderPostIcon = "//button[@id='B5342121585954108719']";
	/** @locatorType Xpath */
	public static final String AllCheckBox = "//table[@id='2308093194563395848']//input[@id='check_all']";

	/** @locatorType Xpath */
	public static final String MRVCheckBox(String GrnNo) {
		return "//td[text()='" + GrnNo + "']/../td[12]";
	}

	/** @locatorType Xpath */
	public static final String MaterialReceiptStatusTableText(String Mrno) {
		return "//table[@id='3380521312027296684_orig']//a[text()='" + Mrno
				+ "']/../../td/div[contains(@style,'Green')]";
	}

	/** @locatorType Xpath */
	public static final String MaterialReceiptStatus(String ProdNo) {
		return "//table[@id='3380521312027296684_orig']//*[text()='" + ProdNo
				+ "']/../td/div[contains(@style,'Green')]";
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
	public static final String CreateJournal = "//button[@id='B1648817004038529035']";
	/** @locatorType Xpath */
	public static final String ViewJournal = "//button[@id='B4670098219152921908']";
	/** @locatorType Xpath */
	public static final String MIVNumberLovButton = "//button[@id='P9313117703_DOC_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String MRVSearchButton = "//button[@data-testid='MRVSearchButton']";
	/** @locatorType Xpath */
	public static final String VoucherNoLov = "//button[@id='P9313117703_VOU_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String MrvSelectAllBtn = "//input[@id='check_all']";
	/** @locatorType Xpath */
	public static final String MrvPendingVoucherSearchBtn = "//button[@data-testid='MRVSearchButton']";
	/** @locatorType Xpath */
	public static final String CreateMrvBtn = "//button[@data-testid='Create_Receipt_PI']";
	/** @locatorType Xpath */
	public static final String ReceiptLocatorfield = "//button[@id='B1872975695598792964']";
	/** @locatorType Xpath */
	public static final String MrvDocumentNumberToastAlert = "//h6[@class='succ_msg']";
	/** @locatorType Xpath */
	public static final String VoucherNo = "//button[@id='P9313117703_VOU_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String MrvDocLovBtn = "//button[@id='P93131177_VOU_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String MrvDocNumberDrillDown = "//*[@id=\"2711552797533936413_orig\"]/tbody/tr[2]/td[5]/a";
	/** @locatorType Xpath */
	public static final String MrvDocNumberSearchBtn = "//button[@data-testid='SearchButton']";
	/** @locatorType Xpath */
	public static final String MrvDocStatus = "//*[@id=\"2711552797533936413_orig\"]/tbody/tr[2]/td[23]/div";
	/** @locatorType Xpath */
	public static final String FindProdNoLovBtn = "//input[@data-testid='FindProdNoLovBtn']/following-sibling::button";

	/** @locatorType Xpath */
	public static final String MrvNumber(String GrnNo) {
		return "(//td[text()='" + GrnNo + "']/../td/a)[2]";
	}

	/** @locatorType Xpath */
	public static final String MrvDocwiseCancelbutton = "//button[@id='B5342121721662108720']";

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
	public static final String SourceVoucherNumber = "//input[@id=\"P93131177_SOURCE_VOU_NO\"]";
	/** @locatorType Xpath */
	public static final String SourceVoucherNumberField = "//div[@id='P93131177_SOURCE_VOU_NO_CONTAINER']";
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
	public static final String AmendFindTable = "//*[@id='2366033317127048661_orig']/tbody/tr[2]/td[2]";
	/** @locatorType Xpath */
	public static final String AmendLineFindTable = "//*[@id='4359650586333471098_orig']/tbody/tr[2]/td[2]";
	/** @locatorType Xpath */
	public static final String SourceVoucher = "//input[@id='P93131177_SOURCE_VOU_NO']";

	/** @locatorType Xpath */
	public static final String AmendFindTableData(int i) {
		return "//table[@id='1646882181744373474_orig']/tbody/tr/td[" + i + "]";
	}

	/** @locatorType Xpath */
	public static final String AmendLineFindTableData(int i) {
		return "//table[@id='3640499450950795911_orig']/tbody/tr/td[" + i + "]";
	}

	/** @locatorType XPATH */
	public static final String Pending_TableSelectAllCheckBox = "//table[@id='2308093194563395848']/tr/th//input[@id='check_all']";
	/** @locatorType XPATH */
	public static final String Pending_TableShowMyRecordBtn = "//button[@id='sow']";
	/** @locatorType XPATH */
	public static final String Pending_TableShowMyRecordBtnClicked = "//button[@id='hid']";
	/** @locatorType XPATH */
	public static final String Pending_TableNoDataSelected = "//div[@id='PEND_data_panel']//div[@class='a-IRR-noDataMsg-icon']";
	/** @locatorType XPATH */
	public static final String ShowMore = "//span[text()='Show More']";
	/** @locatorType Xpath */
	public static final String MRVFindTableData(String i) {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(MRVFindTable, i);
	}

	/** @locatorType Xpath */
	public static final String MRVFindTable = "//table[@data-testid=\"H_Result_Data\"]/tbody/tr/th";
	/** @locatorType Xpath */
	public static final String MRVLineFindTable = "//table[@data-testid=\"L_Result_Data\"]/tbody/tr/th";

	/** @locatorType Xpath */
	public static final String MRVLineFindTableData(String i) {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(MRVLineFindTable, i);
	}
	/** @locatorType Xpath */
	public static final String MrvLineFindTable = "//table[@data-testid='L_Result_Data']/tbody/tr[2]/td[2]";
	/** @locatorType Xpath */
	public static final String MrvFindTable = "//table[@data-testid='H_Result_Data']/tbody/tr[2]/td[2]";
}
