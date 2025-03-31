package commonlocators.supplychainmanagement;

/**
 * @author Sakthi-GWL, Deepan-GWL
 * @company Galaxy Weblinks March 04, 2025
 */
public class MaterialRequestLocators {

	/** @locatorType Xpath */
	public static final String CreateLocationLov = "//button[@id='P93131019_IMRHD_PLNT_LOC_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String RequestType = "//select[@id='P93131019_IMRHD_RQSTBY_TYPE']";
	/** @locatorType Xpath */
	public static final String RequestorLovButton = "//button[@id='P93131019_IMRHD_RQSTBY_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String EmployeeLovButton = "//button[@id='P93131019_IMRHD_EMP_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String TickIconMaterialRequest = "//button[@id='B1149987200445046040']";
	/** @locatorType Xpath */
	public static final String FindSearchBtn = "//button[@id='B166177493378780619']";
	/** @locatorType Xpath */
	public static final String FindProdNoLovBtn = "//button[@id='P93131019_PROD_ORD_NO_lov_btn']";

	/** @locatorType Xpath */
	public static final String Find_Status_TableData(String Text) {
		return "//*[contains(text(),'" + Text + "')]/../td[29]/div";
	}

	/** @locatorType Xpath */
	public static final String Find_MRNo_TableData(String Text) {
		return "//*[contains(text(),'" + Text + "')]/../td[5]/a";
	}

	/** @locatorType Xpath */
	public static final String Find_RequestQnty_TableData(String Text) {
		return "//*[contains(text(),'" + Text + "')]/../td[14]";
	}

	/** @locatorType Xpath */
	public static final String Find_RequesterName_TableData(String Text) {
		return "//*[contains(text(),'" + Text + "')]/../td[7]";
	}

	/** @locatorType Xpath */
	public static final String Find_Warehouse_TableData(String Text) {
		return "//*[contains(text(),'" + Text + "')]/../td[8]";
	}

	/** @locatorType Xpath */
	public static final String Find_Item_TableData(String Text) {
		return "//*[contains(text(),'" + Text + "')]/../td[9]";
	}

	public static final String HeaderLocationId = "//span[@id='P9313101001_IMRHD_PLNT_LOC_ID_DISPLAY']";
	/** @locatorType Xpath */
	public static final String HeaderUnitId = "//input[@id='P9313101001_IMRHD_PLNT']";
	/** @locatorType Xpath */
	public static final String HeaderRequestor = "//span[@id='P9313101001_IMRHD_RQSTBY_ID_DISPLAY']";
	/** @locatorType Xpath */
	public static final String HeaderEmployee = "//input[@id='P9313101001_EMP_ID']";
	/** @locatorType Xpath */
	public static final String MaterialRequestDocumentNo = "//span[@id='P9313101001_IMRHD_RQST_NO_DISPLAY']";
	/** @locatorType Xpath */
	public static final String HeaderSource = "//span[@id='P9313101001_IMRHD_REC_SOURCE_DISPLAY']";
	/** @locatorType Xpath */
	public static final String AddButton = "//button[@id='B3779994317478608526']";
	/** @locatorType Xpath */
	public static final String ItemTableButton = "//div[@id='line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[3]";
	/** @locatorType Xpath */
	public static final String ItemLovButton = "//button[@id='C3811682735420768400_lov_btn']";
	/** @locatorType Xpath */
	public static final String RequestQuantity = "//div[@id='line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[12]";
	/** @locatorType Xpath */
	public static final String RequestQuantityInput = "//input[@id='C5888770709962396888']";
	/** @locatorType Xpath */
	public static final String UOM = "//div[@id='line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[7]";
	/** @locatorType Xpath */
	public static final String Warehouse = "//div[@id='line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[8]";
	/** @locatorType Xpath */
	public static final String LineTickIcon = "//button[@id='B3779994596961608526']";
	/** @locatorType Xpath */
	public static final String ViewLineDetails = "(//div[@id='line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[last()]/td/a/span[contains(@class,'list')])";
	/** @locatorType Xpath */
	public static final String IFrame = "//iframe[@title='Other Details']";
	/** @locatorType Xpath */
	public static final String UnitCostLineDetail = "//input[@id='P9313101003_IMRLN_UNIT_COST']";
	/** @locatorType Xpath */
	public static final String LineDetailLineAmount = "//input[@id='P9313101003_IMRL_TOT_AMT']";
	/** @locatorType Xpath */
	public static final String CloseLineDetail = "//h1[text()='Other Details']/../button";
	/** @locatorType Xpath */
	public static final String HeaderTickIcon = "//button[@id='B3780051431886608662']";
	/** @locatorType Xpath */
	public static final String HeaderPostIcon = "//button[@id='B3780051817563608662']";
	/** @locatorType Xpath */
	public static final String RequestNumber = "//button[@id='P93131019_REQ_DOC_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String ISSCodeButton = "//button[@id='P9313101001_IMRHD_ISS_CODE_lov_btn']";

	/** @locatorType Xpath */
	public static final String MRApproveStatusTable(String MRNumber) {
		return "(//a[text()='" + MRNumber + "']/../../td/div[contains(@style,'green')])[last()]";
	}

	/** @locatorType Xpath */
	public static final String ReqNo = "//button[@id='P93131019_REQ_DOC_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String HeaderType = "//span[@id='P9313101001_IMRHD_RQSTBY_TYPE_DISPLAY']";

	/** @locatorType Xpath */
	public static final String RequestMRNo(String MRNumber) {
		return "//a[text()='" + MRNumber + "']";
	}

	/** @locatorType Xpath */
	public static final String OrderNo = "//span[@id='P9313101003_IMRLN_ORD_NO_DISPLAY']";
}
