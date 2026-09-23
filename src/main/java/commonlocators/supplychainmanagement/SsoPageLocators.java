package commonlocators.supplychainmanagement;

import commonfunctions.CommonFunctions;

/**
 * @author Durai
 * @company Roadmap May 02, 2025
 */
public class SsoPageLocators {
	/** @locatorType Xpath */
	public static final String SsoAddBtn = "//button[@data-testid='Add']";
	/** @locatorType Xpath */
	public static final String SsoLocLov = "//input[@data-testid='P1615131026_SSHD_PLNT_LOC_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SsoSupplierLov = "//input[@data-testid='P1615131026_SSHD_SUPLR_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SsoDepartmentLov = "//input[@data-testid='P1615131026_DEPT_DESC']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SsoShipWHToLov = "//input[@data-testid='P1615131026_SSHD_STORE_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SsoCreate = "//button[@data-testid='CREATE']";
	/** @locatorType Xpath */
	public static final String SsoDocNum = "//input[@id='P1615131028_SSHD_DOC_NO']";
	/** @locatorType Xpath */
	public static final String SsoHeaderLoc = "//*[@id=\"P1615131028_SSHD_PLNT_LOC_ID_DISPLAY\"]";
	/** @locatorType Xpath */
	public static final String SsoHeaderSupplier = "//input[@id='P1615131028_SSHD_SUPLR_DESC']";
	/** @locatorType Xpath */
	public static final String SsoHeaderWH = "//*[@id=\"P1615131028_SSHD_STORE_ID_DISPLAY\"]";
	/** @locatorType Xpath */
	public static final String SsoPendingBtn = "//button[@data-testid='Create_SSO']";
	/** @locatorType Xpath */
	public static final String SsoPendingBtnNew = "//button[@data-testid='Create_PO']";
	/** @locatorType Xpath */
	public static final String PendingSso = "//span[text()='Create  SSO']";

	/** @locatorType Xpath */
	public static final String OPOAddBtn = "//button[@data-testid='Add']";
	/** @locatorType Xpath */
	public static final String OPORadioBtn = "//label[@for='P16151310256_TYPE_0']";
	/** @locatorType Xpath */
	public static final String OPOLocLov = "//input[@data-testid='P1615131025_PRCHD_PLNT_LOC_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String OpoFrame = "//iframe[@title='Create Open PO/RC ']";
	/** @locatorType Xpath */
	public static final String SsoFrame = "//iframe[@title='Create Supplier Schedule Order']";
	/** @locatorType Xpath */
	public static final String OPOSupplierLov = "//input[@data-testid='P1615131025_PRCHD_SUPLR_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String OPOCreateBtn = "//button[@data-testid='Create']";
	/** @locatorType Xpath */
	public static final String OPOLineAdd = "//button[@data-testid='Add']";
	/** @locatorType Xpath */
	public static final String OPOLineItemField = "//*[@id=\"ig_line_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr/td[2]";
	/** @locatorType Xpath */
	public static final String OPOLineItemInput = "//input[@id='C3897485352951750384']";
	/** @locatorType Xpath */
	public static final String OPOLineItemLov = "//input[@data-testid='DUM_PROD']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String OPOLineCostFiled = "//*[@id=\"ig_line_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr/td[6]";
	/** @locatorType Xpath */
	public static final String OPOLineCostInput = "//input[@data-testid='PRCLN_PRICE']";
	/** @locatorType Xpath */
	public static final String OPOLineDiscField = "//*[@id=\"ig_line_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr/td[7]";
	/** @locatorType Xpath */
	public static final String OPOLineDiscInput = "//input[@data-testid='PRCLN_DISC_PCT']";
	/** @locatorType Xpath */
	public static final String OPOLineHsnField = "//*[@id=\"ig_line_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr/td[9]";
	/** @locatorType Xpath */
	public static final String OPOLineHsnLov = "//input[@data-testid='PRCLN_HSN_CODE']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String OPOLineSave = "//button[@data-testid='Save']";
	/** @locatorType Xpath */
	public static final String OPOPost = "//button[@data-testid='Entry_Complete']";
	/** @locatorType Xpath */
	public static final String OPODocNum = "//span[@data-testid='P161513102501_PRCHD_PO_NO']";
	/** @locatorType Xpath */
	public static final String FetchOPOBtn = "//button[@data-testid='Fetch_Open_Order']";
	/** @locatorType Xpath */
	public static final String FetchOPOFrame = "//iframe[@title='Open Order Dtls.']";
	/** @locatorType Xpath */
	public static final String FetchOPOSearchField = "//input[@id='QC_search_field']";
	/** @locatorType Xpath */
	public static final String FetchOPOSearchOkBtn = "//button[@id='QC_search_button']";
	/** @locatorType Xpath */
	public static final String FetchOPOSelectall = "//table[contains(@aria-label,'PENDING')]/tbody/tr/td[11]/input";
	/** @locatorType Xpath */
	public static final String FetchOPOOKBtn = "//button[@data-testid='OK']";
	/** @locatorType Xpath */
	public static final String FetchOPODocNumChoose = "//*[@id=\"QC_sort_widget_rows\"]/a[1]";

	/** @locatorType Xpath */

	public static final String SSOItemFieldLine = "//*[@id=\"LINES_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[1]/td[2]";
	/** @locatorType Xpath */
	public static final String SSOFirmQtyInput = "//input[@data-testid='SSLN_REVISED_QTY']";
	/** @locatorType Xpath */
	public static final String SSOTentQtyInput = "//input[@data-testid='SSLN_TEN_QTY']";
	/** @locatorType Xpath */
	public static final String SSOItemFieldInput = "//input[@data-testid='DUM_PROD']";
	/** @locatorType Xpath */
	public static final String SSOFirmDateFromInput = "//*[@data-testid='SSLN_FROM_DATE']/input";
	/** @locatorType Xpath */
	public static final String SSOFirmDateToInput = "//*[@data-testid='SSLN_TO_DATE']/input";
	/** @locatorType Xpath */
	public static final String SSOTentDateFromInput = "//*[@data-testid='SSLN_TEN_FROM_DATE']/input";
	/** @locatorType Xpath */
	public static final String SSOTentDateToInput = "//*[@data-testid='SSLN_TEN_TO_DATE']/input";
	/** @locatorType Xpath */
	public static final String SSOLineSave = "//button[@data-testid='SaveLineRow']";
	/** @locatorType Xpath */
	public static final String FetchPOSelectNUm = "//*[@id=\"QC_toolbar_controls\"]/div[1]/div/div[2]";
	/** @locatorType Xpath */
	public static final String FetchPOEneterNum = "//input[@id='QC_search_field']";
	/** @locatorType Xpath */
	public static final String SsoTentType = "//SELECT[@data-testid='SSLN_BUCKET_TYPE']";
	/** @locatorType Xpath */
	public static final String SsoFirmType = "//SELECT[@data-testid='SSLN_FRM_BKT_TYPE']";
	/** @locatorType Xpath */
	public static final String SsoGenSchdl = "//button[@data-testid='Generate_Schedule']";
	/** @locatorType Xpath */
	public static final String SsoPost = "//button[@data-testid='Post']";
	/** @locatorType Xpath */
	public static final String SsoSchdlToastMsg = "//*[@id=\"t_Alert_Success\"]/div/div[3]/button/span";
	/** @locatorType Xpath */
	public static final String SsoItemFrame = "//*[@id=\"apex_dialog_1\"]/iframe";
	/** @locatorType Xpath */
	public static final String ItmworkflowOK = "//button[@data-testid='Entry']";
	/** @locatorType Xpath */
	public static final String SsoLineCount = "//*[@id=\"LINES_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr/td[1]";
	/** @locatorType Xpath */
	public static final String SsoLineCancel = "//*[@id=\"LINES_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr/td[1]/a/span";
	/** @locatorType Xpath */
	public static final String SsoHeaderCancel = "//button[@data-testid='Cancel']";
	/** @locatorType Xpath */
	public static final String SsoFindSsoNumFiled = "//input[@id='P1615131026_DOC_NO']";
	/** @locatorType Xpath */
	public static final String SsoFindSearchBtn = "//button[@data-testid='Find']";
	/** @locatorType Xpath */
	public static final String SsoCanclStatus = "//table[@data-testid='H_Result_Data']/tbody/tr/td[16]";
	/** @locatorType Xpath */
	public static final String SsoDocNoLovFindPage = "//input[@data-testid='P1615132060_PO_SSO_NO']/following-sibling::button";

	/** @locatorType Xpath */
	public static final String SSOItemField(int i) {
		return "//*[@id=\'LINES_ig_grid_vc\']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[3]";
	}

	/** @locatorType Xpath */
	public static final String SSOFirmQtyField(int i) {
		return "//*[@id=\"LINES_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[" + i + "]/td[7]";
	}

	/** @locatorType Xpath */
	public static final String SSOTentQtyField(int i) {
		return "//*[@id=\"LINES_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[" + i + "]/td[8]";
	}

	/** @locatorType Xpath */
	public static final String SSOFirmDateFromField(int i) {
		return "//*[@id=\"LINES_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[" + i + "]/td[9]";
	}

	/** @locatorType Xpath */
	public static final String SSOFirmDateToField(int i) {
		return "//*[@id=\"LINES_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[" + i + "]/td[10]";
	}

	/** @locatorType Xpath */
	public static final String SSOTentDateFromField(int i) {
		return "//*[@id=\"LINES_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[" + i + "]/td[13]";
	}

	/** @locatorType Xpath */
	public static final String SSOTentDateToField(int i) {
		return "//*[@id=\"LINES_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[" + i + "]/td[14]";
	}
	
	/** @locatorType Xpath */
	public static final String SsoCancelOk = "//button[contains(text(),'OK')]";
	/** @locatorType Xpath */
	public static final String SsoCancelFindSearch = "//button[@data-testid='Search']";
	
	
	/** @locatorType Xpath */
	public static final String OPOTable = "(//table[@data-testid=\"ig_line_ig_report_settings_Result_Data\"])[2]";
	/** @locatorType Xpath */
	public static final String OPOItemDataLastRow() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(OPOTable, "Item");
	}
	/** @locatorType Xpath */
	public static final String OPOCostDataLastRow() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(OPOTable, "Unit Cost");
	}
	/** @locatorType Xpath */
	public static final String OPODisDataLastRow() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(OPOTable, "Disc %");
	}
	/** @locatorType Xpath */
	public static final String OPOHSNDataLastRow() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(OPOTable, "HSN / SAC Code");
	}
	
	/** @locatorType Xpath */
	public static final String OPOItemDataLastRowInput = OPOItemDataLastRow() + "//button";
	
	/** @locatorType Xpath */
	public static final String SSOTable = "(//table[@data-testid=\"R4139113351322702989_Result_Data\"])[2]";
	/** @locatorType Xpath */
	public static final String SSOItemDataLastRow() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(SSOTable, "Item");
	}
	
	/** @locatorType Xpath */
	public static final String SsoLineCancelNew = "//*[@id=\"t_PageBody\"]/ul/li[2]/a/span";
	
}
