package commonlocators.supplychainmanagement;

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
	public static final String PendingSso = "//button[@id='B927512565196129620']";
	
	
	/** @locatorType Xpath */
	public static final String OPOAddBtn = "//button[@data-testid='Add']";
	/** @locatorType Xpath */
	public static final String OPORadioBtn = "//label[@for='P16151310256_TYPE_0']";
	/** @locatorType Xpath */
	public static final String OPOLocLov = "//input[@data-testid='P1615131025_PRCHD_PLNT_LOC_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String OpoFrame = "//iframe[@title='Open PO/RC ']";
	/** @locatorType Xpath */
	public static final String SsoFrame = "//iframe[@title='Supplier Schedule Order']";
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
	public static final String OPOLineItemLov = "//button[@id='C3900495350211548502_lov_btn']";
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
	public static final String OPOLineHsnLov = "//button[@id='C4506263904107339499_lov_btn']";
	/** @locatorType Xpath */
	public static final String OPOLineSave = "//button[@data-testid='Save']";
	/** @locatorType Xpath */
	public static final String OPOPost = "//button[@data-testid='Entry_Complete']";
	/** @locatorType Xpath */
	public static final String OPODocNum = "//span[@data-testid=\"P161513102501_PRCHD_PO_NO\"]";
	/** @locatorType Xpath */
	public static final String FetchOPOBtn = "//button[@id='B1055965137480215544']";
	/** @locatorType Xpath */
	public static final String FetchOPOFrame = "//iframe[@title='Open Order Dtls.']";
	/** @locatorType Xpath */
	public static final String FetchOPOSearchField = "//input[@id='QC_search_field']";
	/** @locatorType Xpath */
	public static final String FetchOPOSearchOkBtn = "//button[@id='QC_search_button']";
	/** @locatorType Xpath */
	public static final String FetchOPOSelectall = "//*[@id=\"1956799288720660936_orig\"]/tbody/tr[2]/td[11]";//input[@id='check_all']
	/** @locatorType Xpath */
	public static final String FetchOPOOKBtn = "//*[@id=\"B1\"]/span";
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
	public static final String SSOFirmDateFromInput = "//input[@id='C1056115776862222156_input']";
	/** @locatorType Xpath */
	public static final String SSOFirmDateToInput = "//input[@id='C1056115803025222157_input']";
	/** @locatorType Xpath */
	public static final String SSOTentDateFromInput = "//input[@id='C1056418322374047728_input']";
	/** @locatorType Xpath */
	public static final String SSOTentDateToInput = "//input[@id='C1056418447432047729_input']";
	/** @locatorType Xpath */
	public static final String SSOLineSave = "//button[@data-testid='SaveLineRow']";
	/** @locatorType Xpath */
	public static final String FetchPOSelectNUm = "//*[@id=\"C1956806366053661061\"]";
	/** @locatorType Xpath */
	public static final String FetchPOEneterNum = "//input[@id='QC_sort_widget_search_field']";
	/** @locatorType Xpath */
	public static final String SsoTentType = "//*[@id=\"C1056115492761222153\"]";
	/** @locatorType Xpath */
	public static final String SsoFirmType = "//*[@id=\"C1056419890013047743\"]";
	/** @locatorType Xpath */
	public static final String SsoGenSchdl = "//button[@data-testid='Generate_Schedule']";
	/** @locatorType Xpath */
	public static final String SsoPost = "//button[@data-testid='Post']";
	/** @locatorType Xpath */
	public static final String SsoSchdlToastMsg = "//*[@id=\"t_Alert_Success\"]/div/div[3]/button/span";
	/** @locatorType Xpath */
	public static final String SsoItemFrame = "//*[@id=\"apex_dialog_1\"]/iframe";
	/** @locatorType Xpath */
	public static final String ItmworkflowOK = "//button[@id='B1629660995946245032']";
	/** @locatorType Xpath */
	public static final String SsoLineCount = "//*[@id=\"LINES_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr/td[1]";
	/** @locatorType Xpath */
	public static final String SsoLineCancel = "//*[@id=\"LINES_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr/td[19]/a/span";
	/** @locatorType Xpath */                     
	public static final String SsoHeaderCancel = "//button[@data-testid='Cancel']";
	/** @locatorType Xpath */
	public static final String SsoFindSsoNumFiled = "//input[@id='P1615131026_DOC_NO']";
	/** @locatorType Xpath */
	public static final String SsoFindSearchBtn = "//*[@id=\"B1881642368782282543\"]/span";
	/** @locatorType Xpath */
	public static final String SsoCanclStatus = "//*[@id=\"7829747646252966879_orig\"]/tbody/tr[2]/td[33]/div";

	/** @locatorType Xpath */
	public static final String SSOItemField(int i) {
		return "//*[@id=\'LINES_ig_grid_vc\']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[2]";
	}

	/** @locatorType Xpath */
	public static final String SSOFirmQtyField(int i) {
		return "//*[@id=\"LINES_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[" + i + "]/td[6]";
	}

	/** @locatorType Xpath */
	public static final String SSOTentQtyField(int i) {
		return "//*[@id=\"LINES_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[" + i + "]/td[7]";
	}

	/** @locatorType Xpath */
	public static final String SSOFirmDateFromField(int i) {
		return "//*[@id=\"LINES_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[" + i + "]/td[8]";
	}

	/** @locatorType Xpath */
	public static final String SSOFirmDateToField(int i) {
		return "//*[@id=\"LINES_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[" + i + "]/td[9]";
	}

	/** @locatorType Xpath */
	public static final String SSOTentDateFromField(int i) {
		return "//*[@id=\"LINES_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[" + i + "]/td[12]";
	}

	/** @locatorType Xpath */
	public static final String SSOTentDateToField(int i) {
		return "//*[@id=\"LINES_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[" + i + "]/td[13]";
	}
}
