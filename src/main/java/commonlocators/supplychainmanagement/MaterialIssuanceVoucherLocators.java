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
	public static final String PendingMRCreateMIBtn = "//button[@id='B1']";

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
		return "//div[@id='PEND_content']//table/tbody/tr/td[text()='" + MRNo + "']/../td[22]";
	}

	/** @locatorType XPATH */
	public static final String SearchResult_MRNo_TableData(String MRNo) {
		return "//div[@id='PEND_content']//table/tbody/tr/td[text()='" + MRNo + "']/../td[2]";
	}

	/** @locatorType XPATH */
	public static final String SearchResult_FGItem_TableData(String MRNo) {
		return "//div[@id='PEND_content']//table/tbody/tr/td[text()='" + MRNo + "']/../td[23]";
	}

	/** @locatorType XPATH */
	public static final String SearchResult_TableSelectBox(String MRNo) {
		return "//div[@id='PEND_content']//table/tbody/tr/td[text()='" + MRNo + "']/../td[13]/input";
	}

	/** @locatorType XPATH */
	public static final String DisplayMIVNo = "//span[contains(@id,'ISTHD_DOC_NO_DISPLAY')]";
	/** @locatorType XPATH */
	public static final String DisplayReceiverWarehouse = "//span[contains(@id,'STORE_DESC_DISPLAY')]";
	/** @locatorType XPATH */
	public static final String SaveHeaderIcon = "//button[@id='B3110868619550418445']";
	/** @locatorType XPATH */
	public static final String MIVPostBtn = "//button[@id='B4710641233459306429']";

	/** @locatorType Xpath */
	public static final String PendingMrIframe = "//iframe[@title='Pending MR Lines']";
	/** @locatorType Xpath */
	public static final String MRLovButton = "//button[@id='P93131020_MR_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String SearchButton = "//button[@id='B2247675926419084803']";
	/** @locatorType Xpath */
	public static final String CheckBox = "//input[@id='check_all']";
	/** @locatorType Xpath */
	public static final String CreateMiButton = "//button[@id='B1']";
	/** @locatorType Xpath */
	public static final String MILocationId = "//span[contains(@id,'ISTHD_PLNT_LOC_ID_DISPLAY')]";
	/** @locatorType Xpath */
	public static final String MIVNo = "//span[contains(@id,'ISTHD_DOC_NO_DISPLAY')]";
	/** @locatorType Xpath */
	public static final String ReceiverWH = "//span[contains(@id,'STORE_DESC_DISPLAY')]";
	/** @locatorType Xpath */
	public static final String CreateJnl = "//button[@id='B4710641419071306431']";
	/** @locatorType Xpath */
	public static final String ViewJournal = "//button[@id='B4710641635025306433']";
	/** @locatorType Xpath */
	public static final String DebitTable = "//table[@id='4579198317374253820_orig']/tbody/tr[last()]/td[last()-1]";
	/** @locatorType Xpath */
	public static final String CreditTable = "//table[@id='4579198317374253820_orig']/tbody/tr[last()]/td[last()]";
	/** @locatorType Xpath */
	public static final String ViewJournalFrame = "//iframe[@title='View Journal']";
	/** @locatorType Xpath */
	public static final String CloseJournal = "//h1[text()='View Journal']/../button[@title='Close']";
	/** @locatorType Xpath */
	public static final String ListElements = "//div[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[3]";
	/** @locatorType Xpath */
	public static final String HeaderTickIcon = "//button[@id='B3110868619550418445']";
	/** @locatorType Xpath */
	public static final String HeaderPostIcon = "//button[@id='B4710641233459306429']";
	/** @locatorType Xpath */
	public static final String VoucherNoLovButton = "//button[@id='P93131628_REQ_DOC_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String MivSearchButton = "//button[@id='B166256573623807506']";

	/** @locatorType Xpath */
	public static final String TypeText = "//span[contains(@id,'ISTHD_ISSUETO_TYPE_DISPLAY')]";

	/** @locatorType Xpath */
	public static final String PostedMiv(String MINO) {
		return "(//a[text()='" + MINO + "']/../../td/div[contains(@style,'Green')])[1]";
	}

	/** @locatorType Xpath */
	public static final String ItemTable(int i) {
		return "//div[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[3]";
	}

	/** @locatorType Xpath */
	public static final String ItemCostTable(int i) {
		return "//div[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[14]";
	}

	/** @locatorType Xpath */
	public static final String LinesTableViewDetailsIcon(String Name) {
		return "//table//tr/td[text()='" + Name + "']/../td[last()-1]/a";
	}

	/** @locatorType Xpath */
	public static final String LineDetailsIframe = "//iframe[@title='Line Details']";
	/** @locatorType Xpath */
	public static final String LineDetailsSaveIcon = "//button[@id='B1687788247186826859']";
	/** @locatorType Xpath */
	public static final String LineDetailsMachineLovBtn = "//button[@id='P931311721_ISTLN_RES_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String LineDetailsIframeCloseIcon = "//iframe[@title='Line Details']/ancestor::div//button";
}
