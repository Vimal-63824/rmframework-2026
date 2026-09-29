package commonlocators.supplychainmanagement;

/**
 * @author Sakthi-GWL, Deepan-GWL
 * @company Galaxy Weblinks March 04, 2025
 */
public class MaterialIssuanceVoucherLocators {

	/** @locatorType XPATH */
	public static final String PendingMRLinesIframe = "//iframe[@title='Pending MR Lines']";
	/** @locatorType XPATH */
	public static final String PendingMRIframeCloseIcon = "//h1[text()='Pending MR Lines']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String PendingMRSearchBar = "//input[@id='PEND_search_field']";
	/** @locatorType XPATH */
	public static final String PendingMRSearchGoBtn = "//button[@id='PEND_search_button']";
	/** @locatorType XPATH */
	public static final String PendingMRCreateMIBtn = "//button[@data-testid='PendingMRCreateMIBtn']";

	/** @locatorType XPATH */
	public static final String SearchResult_Item_TableData(String MRNo) {
		return "//div[@id='PEND_content']//table/tbody/tr/td[text()='" + MRNo + "']/../td[5]";
	}

	/** @locatorType XPATH */
	public static final String SearchResult_Warehouse_TableData(String MRNo) {
		return "//div[@id='PEND_content']//table/tbody/tr/td[text()='" + MRNo + "']/../td[10]";
	}

	/** @locatorType XPATH */
	public static final String SearchResult_ProdOrderNo_TableData(String MRNo) {
		return "//div[@id='PEND_content']//table/tbody/tr/td[text()='" + MRNo + "']/../td[21]";
	}

	/** @locatorType XPATH */
	public static final String SearchResult_MRNo_TableData(String MRNo) {
		return "//div[@id='PEND_content']//table/tbody/tr/td[text()='" + MRNo + "']/../td[2]";
	}

	/** @locatorType XPATH */
	public static final String SearchResult_FGItem_TableData(String MRNo) {
		return "//div[@id='PEND_content']//table/tbody/tr/td[text()='" + MRNo + "']/../td[22]";
	}

	/** @locatorType XPATH */
	public static final String SearchResult_TableSelectBox(String MRNo) {
		return "//div[@id='PEND_content']//table/tbody/tr/td[text()='" + MRNo + "']/../td[13]/input";
	}

	/** @locatorType XPATH */
	public static final String DisplayMIVNo = "//span[@data-testid='MIVNo']";
	/** @locatorType XPATH */
	public static final String DisplayReceiverWarehouse = "//span[contains(@id,'STORE_DESC_DISPLAY')]";
	/** @locatorType XPATH */
	public static final String SaveHeaderIcon = "//button[@data-testid='HeaderTickIcon']";
	/** @locatorType XPATH */
	public static final String MIVPostBtn = "//button[@data-testid='HeaderPostIcon']";

	/** @locatorType XPATH */
	public static final String PendingMrIframe = "//iframe[@title='Pending MR Lines']";
	/** @locatorType XPATH */
	public static final String MRLovButton = "//input[@data-testid='MRLovButton']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String SearchButton = "//button[@data-testid=' PendingMRSearchBar']";
	/** @locatorType XPATH */

	public static final String CheckBox = "(//table//tr/th//input[@id='check_all'])[1]";
	/** @locatorType XPATH */
	public static final String CreateMiButton = "//button[@data-testid='PendingMRCreateMIBtn']";
	/** @locatorType XPATH */
	public static final String MILocationId = "//span[@data-testid='MILocationId']";
	/** @locatorType XPATH */
	public static final String MIVNo = "//span[@data-testid='MIVNo']";
	/** @locatorType XPATH */
	public static final String ReceiverWH = "//span[contains(@id,'STORE_DESC_DISPLAY')]";
	/** @locatorType XPATH */
	public static final String CreateJnl = "//button[@data-testid='CreateJnl']";
	/** @locatorType XPATH */
	public static final String ViewJournal = "//button[@data-testid='ViewJournal']";
	/** @locatorType XPATH */
	public static final String DebitTable = "//table[contains(@id,'orig')]/tbody/tr[last()]/td[last()-1]";
	/** @locatorType XPATH */
	public static final String CreditTable = "//table[contains(@id,'orig')]/tbody/tr[last()]/td[last()]";
	/** @locatorType XPATH */
	public static final String ViewJournalFrame = "//iframe[@title='View Journal']";
	/** @locatorType XPATH */
	public static final String CloseJournal = "//h1[text()='View Journal']/../button[@title='Close']";
	/** @locatorType XPATH */
	public static final String ListElements = "//div[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[3]";
	/** @locatorType XPATH */
	public static final String HeaderTickIcon = "//button[@data-testid='HeaderTickIcon']";
	/** @locatorType XPATH */
	public static final String HeaderPostIcon = "//button[@data-testid='HeaderPostIcon']";
	/** @locatorType XPATH */
	public static final String VoucherNoLovButton = "//input[@data-testid='VoucherNoLovButton']/following-sibling::button";
	/** @locatorType XPATH */

	public static final String MivSearchButton = "//button[@data-testid='find_miv']";

	/** @locatorType XPATH */
	public static final String TypeText = "//span[contains(@id,'ISTHD_ISSUETO_TYPE_DISPLAY')]";

	/** @locatorType XPATH */
	public static final String PostedMiv(String MINO) {
		return "(//a[text()='" + MINO + "']/../../td/div[contains(@style,'Green')])[1]";
	}

	/** @locatorType XPATH */
	public static final String ItemTable(int i) {
		return "//div[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[3]";
	}

	/** @locatorType XPATH */
	public static final String ItemCostTable(int i) {
		return "//div[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[14]";
	}

	/** @locatorType XPATH */
	public static final String LinesTableViewDetailsIcon(String Name) {
		return "//table//tr/td[text()='" + Name + "']/../td[last()-1]/a";
	}

	/** @locatorType XPATH */
	public static final String LineDetailsIframe = "//iframe[@title='Line Details']";
	/** @locatorType XPATH */
	public static final String LineDetailsSaveIcon = "//button[@data-testid='LineDetailsSaveIcon']";
	/** @locatorType XPATH */
	public static final String LineDetailsMachineLovBtn = "//input[@data-testid='LineDetailsMachineLovBtn']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String LineDetailsIframeCloseIcon = "//iframe[@title='Line Details']/ancestor::div//button";
	/** @locatorType XPATH */
	public static final String CreateMIVButton = "//div[@id='CRE_MIV']//table//button";
	/** @locatorType XPATH */
	public static final String PendingCreateMIV = "//button[@data-testid='btn_cre_miv']";
	/** @locatorType XPATH */
	public static final String Pending_TableShowMyRecordBtn = "//button[@data-testid='Show_my_Record']";
	/** @locatorType XPATH */
	public static final String Pending_TableShowMyRecordBtnClicked = "//button[@data-testid='Show_my_Record_1']";
	/** @locatorType XPATH */
	public static final String Pending_TableNoDataSelected = "//div[@id='PEND_data_panel']//div[@class='a-IRR-noDataMsg-icon']";
	/** @locatorType XPATH */
	public static final String Pending_TableSelectAllCheckBox = "//table[contains(@aria-label,'PENDINGMIV')]//tr/th//input[@id='check_all']";
}
