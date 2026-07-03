package commonlocators.supplychainmanagement;

/**
 * @author Sakthi-GWL
 */

public class ApprovalPageLocators {

	/** @locatorType Xpath */
	public static final String UserNotifyIcon = "//span[@class='t-Icon fa fa-bell']/parent::button";
	/** @locatorType Xpath */
	public static final String ApprovalNotifyOption = "//a[@class='a-Menu-label'][contains(text(),'Approvals')]";
	/** @locatorType Xpath */
	public static final String DocumentsTypeDropDown = "//select[contains(@class,'selectlist')]";
	/** @locatorType Xpath */
	public static final String SearchBoxContainer = "//div[@class='a-IRR-searchFieldContainer']";
	/** @locatorType Xpath */
	public static final String SearchBox = "//input[@id='PEND_search_field']";
	/** @locatorType Xpath */
	public static final String SearchBoxGoBtn = "//button[@id='PEND_search_button']";
	/** @locatorType Xpath */
	public static final String ProcessButton = "(//span[text()='Process']/parent::button)[1]";
	/** @locatorType Xpath */
	public static final String ApproveRadioBtn = "//label[text()='Approve']";
	/** @locatorType Xpath */
	public static final String ToastCloseIcon = "//button[@title='Close']";
	/** @locatorType Xpath */
	public static final String SearchTableResult = "//*[@class='a-IRR-table']/tbody/tr[2]/td[1]";
	/** @locatorType Id */
	public static final String CURRENCY = "P161513106044_POH_CURRENCY_DISPLAY";
	/** @locatorType Id */
	public static final String POTYPE = "P161513106044_POH_TYPE_DISPLAY";
	/** @locatorType Xpath */
	public static final String UOMTYPE = "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr/td[5]";
	/** @locatorType Xpath */
	public static final String BackButton = "//button[@id='B720942771947006229']";
	/** @locatorType Xpath */
	public static final String ApproveProcessButton = "//td//span[text()='Process']/parent::button";
	/** @locatorType Xpath */
	public static final String Backicon = "//button[@id='B723952769206804347']";

	/** @locatorType Xpath */
	public static final String PODetailsInfoBtn(String PONumber) {
		return "//td[contains(text(),'" + PONumber + "')]/../td[3]/*";
	}

	/** @locatorType Xpath */
	public static final String POSelectCheckBox(String PONumber) {
		return "//td[contains(text(),'" + PONumber + "')]/../td[1]/*";
	}

	/** @locatorType Xpath */
	public static final String SODetailsInfoBtn(String SONumber) {
		return "//td[contains(text(),'" + SONumber + "')]/../td[3]/*";
	}

	/** @locatorType Xpath */
	public static final String SOSelectCheckBox(String SONumber) {
		return "//td[contains(text(),'" + SONumber + "')]/../td[1]/*";
	}

	/** @locatorType Xpath */
	public static final String AjaxMultiDocSelectWarning = "//div[@role='alertdialog']";
	/** @locatorType Xpath */
	public static final String MRLocationID = "//span[@id='P9313101002_IMRHD_PLNT_LOC_ID_DISPLAY']";
	/** @locatorType Xpath */
	public static final String MRUnitId = "//span[@id='P9313101002_IMRHD_PLNT_DISPLAY']";
	/** @locatorType Xpath */
	public static final String MRRequestor = "//span[@id='P9313101002_IMRHD_RQSTBY_ID_DISPLAY']";
	/** @locatorType Xpath */
	public static final String MREmployee = "//span[@id='P9313101002_IMRHD_EMP_ID_DISPLAY']";
	/** @locatorType Xpath */
	public static final String MRSource = "//span[@id='P9313101002_IMRHD_REC_SOURCE_DISPLAY']";
	/** @locatorType Xpath */
	public static final String MRStatus = "//span[@id='P9313101002_STATUS_DISPLAY']";
	/** @locatorType Xpath */
	public static final String ApproveErrorToast = "//div[contains(text(),'error has occurred')]";
	/** @locatorType Xpath */
	public static final String ApprovalTitle = "//iframe/ancestor::div//h1[contains(normalize-space(), 'Approval')]";
	/** @locatorType Xpath */
	public static final String ReturnRadioBtn = "//label[text()='Return']";
	/** @locatorType Xpath */
	public static final String ReturnPersonLovBtn = "//button[@id='P236131010_WFDC_NXT_FWD_PERS_1_lov_btn']";
	/** @locatorType Xpath */
	public static final String WfTypeLovButton = "//button[@id='P236131010_TYPE_lov_btn']";
	/** @locatorType Xpath */
	public static final String SearchButtonWorkFLow = "//button[@id='BUT']";
	/** @locatorType Xpath */
	public static final String SalesCataogField = "//span[text()='SALES CATALOG PRICE']";
	/** @locatorType Xpath */
	public static final String SearchBoxWorkType = "//*[@id='PopupLov_236131010_P236131010_TYPE_dlg']/div[1]/input";
	/** @locatorType Xpath */
	public static final String SearchButton = "//button[@id='BUT']";
	/** @locatorType Xpath */
	public static final String VoucherDocNo = "//input[@id='P236131010_DOC_NO']";
	/** @locatorType Xpath */
	public static final String FindSearchButton = "//button[@id='SEARCH']";

}
