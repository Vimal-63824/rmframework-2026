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
	public static final String DisplayMIVNo = "//span[@id='P931311701_ISTHD_DOC_NO_DISPLAY']";
	/** @locatorType XPATH */
	public static final String DisplayReceiverWarehouse = "//span[@id='P931311701_STORE_DESC_DISPLAY']";
	/** @locatorType XPATH */
	public static final String SaveHeaderIcon = "//button[@id='B3110868619550418445']";
	/** @locatorType XPATH */
	public static final String MIVPostBtn = "//button[@id='B4710641233459306429']";
	/** @locatorType Xpath */
	public static final String VoucherNoLovButton = "//button[@id='P93131628_REQ_DOC_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String MivSearchButton = "//button[@id='B166256573623807506']";

	/** @locatorType Xpath */
	public static final String PostedMiv(String MINO) {
		return "//div[@id='ld_data_panel']//table/tbody//td/a[text()='" + MINO + "']/../../td/div";
	}

}
