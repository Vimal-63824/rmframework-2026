package commonlocators.supplychainmanagement;

/**
 * @author Sakthi-GWL, Deepan-GWL
 * @company Galaxy Weblinks March 04, 2025
 */
public class MaterialRequestLocators {

	/** @locatorType Xpath */
	public static final String CreateLocationLov = "//input[@data-testid='CreateLocationLov']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String RequestType = "//select[@data-testid='RequestType']";
	/** @locatorType Xpath */
	public static final String RequestorLovButton = "//input[@data-testid='RequestorLovButton']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String EmployeeLovButton = "//input[@data-testid='EmployeeLovButton']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String TickIconMaterialRequest = "//button[@id='B1153042350755872583']";
	/** @locatorType Xpath */
	public static final String FindSearchBtn = "//button[@data-testid='FindSearchBtn']";
	/** @locatorType Xpath */
	public static final String FindProdNoLovBtn = "//input[@data-testid='FindProdNoLovBtn']/following-sibling::button";

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
	public static final String AddButton = "//button[@id='B3783049467789435069']";
	/** @locatorType Xpath */
	public static final String ItemTableButton = "//div[@id='line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[3]";
	/** @locatorType Xpath */
	public static final String ItemLovButton = "//button[@id='C3814737885731594943_lov_btn']";
	/** @locatorType Xpath */
	public static final String RequestQuantity = "//div[@id='line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[12]";
	/** @locatorType Xpath */
	public static final String RequestQuantityInput = "//input[@id='C5891825860273223431']";
	/** @locatorType Xpath */
	public static final String UOM = "//div[@id='line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[7]";
	/** @locatorType Xpath */
	public static final String Warehouse = "//div[@id='line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[8]";
	/** @locatorType Xpath */
	public static final String LineTickIcon = "//button[@id='B3783049747272435069']";
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
	public static final String HeaderTickIcon = "//button[@data-testid='HeaderTickIcon']";
	/** @locatorType Xpath */
	public static final String HeaderPostIcon = "//button[@data-testid='HeaderPostIcon']";
	/** @locatorType Xpath */
	public static final String RequestNumber = "//input[@data-testid='RequestNumber']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String ISSCodeButton = "//input[@data-testid='ISSCodeButton']/following-sibling::button";

	/** @locatorType Xpath */
	public static final String MRApproveStatusTable(String MRNumber) {
		return "(//a[text()='" + MRNumber + "']/../../td/div[contains(@style,'green')])[last()]";
	}

	/** @locatorType Xpath */
	public static final String ReqNo = "//input[@data-testid='RequestNumber']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String HeaderType = "//span[@data-testid='HeaderType']";

	/** @locatorType Xpath */
	public static final String RequestMRNo(String MRNumber) {
		return "//a[text()='" + MRNumber + "']";
	}

	/** @locatorType Xpath */
	public static final String OrderNo = "//span[@data-testid='OrderNo']";
	/** @locatorType Xpath */
	public static final String OtherDetailsHeader = "//h1[text()='Other Details']";
}
