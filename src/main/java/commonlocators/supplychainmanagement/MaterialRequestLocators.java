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
	public static final String TickIconMaterialRequest = "//button[@title='Create']";
	/** @locatorType Xpath */
	public static final String FindSearchLinesRadioBtn = "//label[text()='Line(s)']";
	/** @locatorType Xpath */
	public static final String FindSearchBtn = "//button[@data-testid='FindSearchBtn']";
	/** @locatorType Xpath */
	public static final String FindProdNoLovBtn = "//input[@data-testid='FindProdNoLovBtn']/following-sibling::button";

	/** @locatorType Xpath */
	public static final String Find_Status_TableData(String Text) {
		return "//table[contains(@id,'orig')]//*[contains(text(),'" + Text + "')]/../td/div";
	}

	/** @locatorType Xpath */
	public static final String Find_MRNo_TableData(String Text) {
		return "//table[contains(@id,'orig')]//*[contains(text(),'" + Text + "')]/../td[5]/a";
	}

	/** @locatorType Xpath */
	public static final String Find_RequestQnty_TableData(String Text) {
		return "//table[contains(@id,'orig')]//*[contains(text(),'" + Text + "')]/../td[18]";
	}

	/** @locatorType Xpath */
	public static final String Find_RequesterName_TableData(String Text) {
		return "//table[contains(@id,'orig')]//*[contains(text(),'" + Text + "')]/../td[9]";
	}

	/** @locatorType Xpath */
	public static final String Find_Warehouse_TableData(String Text) {
		return "//table[contains(@id,'orig')]//*[contains(text(),'" + Text + "')]/../td[12]";
	}

	/** @locatorType Xpath */
	public static final String Find_Item_TableData(String Text) {
		return "//table[contains(@id,'orig')]//*[contains(text(),'" + Text + "')]/../td[13]";
	}

	public static final String HeaderLocationId = "//span[@id='P9313101001_IMRHD_PLNT_LOC_ID_DISPLAY']";
	/** @locatorType Xpath */
	public static final String HeaderUnitId = "//input[@id='P9313101001_IMRHD_PLNT']";
	/** @locatorType Xpath */
	public static final String HeaderRequestor = "//span[@id='P9313101001_IMRHD_RQSTBY_ID_DISPLAY']";
	/** @locatorType Xpath */
	public static final String HeaderEmployee = "//input[@id='P9313101001_EMP_ID']";
	/** @locatorType Xpath */
	public static final String MaterialRequestDocumentNo = "//span[@data-testid='P9313101001_IMRHD_RQST_NO']";
	/** @locatorType Xpath */
	public static final String HeaderSource = "//span[@id='P9313101001_IMRHD_REC_SOURCE_DISPLAY']";
	/** @locatorType Xpath */
	public static final String AddButton = "//button[@data-testid='ADD']";
	/** @locatorType Xpath */
	public static final String ItemTableButton = "//div[@id='line_ig_grid_vc']//table/tbody/tr[1]/td[3]";
	/** @locatorType Xpath */
	public static final String ProductionOrderNo = "//div[@id='line_ig_grid_vc']//table/tbody/tr[1]/td[10]";
	/** @locatorType Xpath */
	public static final String ProductionOrderNoLovButton = "//div[@id='line_ig_grid_vc']//table/tbody/tr[1]/td[10]//button";
	/** @locatorType Xpath */
	public static final String ItemLovButton = "//div[@id='line_ig_grid_vc']//table/tbody/tr[1]/td[3]//button";
	/** @locatorType Xpath */
	public static final String RequestQuantity = "//div[@id='line_ig_grid_vc']//table/tbody/tr[1]/td[12]";
	/** @locatorType Xpath */
	public static final String RequestQuantityInput = "//div[@id='line_ig_grid_vc']//table/tbody/tr[1]/td[12]//input";
	/** @locatorType Xpath */
	public static final String UOM = "//div[@id='line_ig_grid_vc']//table/tbody/tr[1]/td[7]";
	/** @locatorType Xpath */
	public static final String Warehouse = "//div[@id='line_ig_grid_vc']//table/tbody/tr[1]/td[8]";
	/** @locatorType Xpath */
	public static final String LineTickIcon = "//button[@data-testid='SAVE1']";
	/** @locatorType Xpath */
	public static final String ViewLineDetails = "//*[@id='line_ig_grid_vc']//table/tbody/tr/td[1]/a/span";
	/** @locatorType Xpath */
	public static final String IFrame = "//iframe[@title='Details']";
	/** @locatorType Xpath */
	public static final String UnitCostLineDetail = "//input[@data-testid='P9313101003_IMRLN_UNIT_COST']";
	/** @locatorType Xpath */
	public static final String LineDetailLineAmount = "//input[@data-testid='P9313101003_IMRL_TOT_AMT']";
	/** @locatorType Xpath */
	public static final String CloseLineDetail = "//h1[text()='Details']/../button";
	/** @locatorType Xpath */
	public static final String HeaderTickIcon = "//button[@data-testid='SAVE']";
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
		return "//table[@data-testid='L_Result_Data']//a[text()='" + MRNumber + "']";
	}

	/** @locatorType Xpath */
	public static final String OrderNo = "//span[@data-testid='OrderNo']";
	/** @locatorType Xpath */
	public static final String OtherDetailsHeader = "//h1[text()='Other Details']";
	/** @locatorType Xpath */
	public static final String FindLocationInputField = "//input[@data-testid='P93131019_LOCATION']";
	/** @locatorType Xpath */
	public static final String FindUnitInputField = "//input[@data-testid='P93131019_PLNT']";
	/** @locatorType Xpath */
	public static final String FindSubVoucherTypeSelectField = "//select[@data-testid='P93131019_REQ_DOC_TYPE']";
	/** @locatorType Xpath */
	public static final String FindMRNumberInputField = "//input[@data-testid='RequestNumber']";
	/** @locatorType Xpath */
	public static final String FindProductionOrderNumberInputField = "//input[@data-testid='FindProdNoLovBtn']";
	/** @locatorType Xpath */
	public static final String FindFromDateInputField = "//input[@id='P93131019_REQ_DOC_DATE_FROM_input']";
	/** @locatorType Xpath */
	public static final String FindToDateInputField = "//input[@id='P93131019_REQ_DOC_DATE_TO_input']";
	/** @locatorType Xpath */
	public static final String FindRequestorInputField = "//input[@data-testid='P93131019_TO_WH']";
	/** @locatorType Xpath */
	public static final String FindSOPRJRefInputField = "//input[@data-testid='P93131019_SO_REF']";
	/** @locatorType Xpath */
	public static final String FindStatusSelectField = "//select[@data-testid='P93131019_STATUS']";
	/** @locatorType Xpath */
	public static final String FindItemIDInputField = "//input[@data-testid='P93131019_PROD_ID']";
	/** @locatorType Xpath */
	public static final String FindItemDescInputField = "//input[@data-testid='P93131019_PROD_DESC']";
	/** @locatorType Xpath */
	public static final String FindSourceSelectField = "//select[@data-testid='P93131019_SOURCE']";
	/** @locatorType Xpath */
	public static final String FindSearchButton = "//button[@data-testid='FindSearchBtn']";
	/** @locatorType Xpath */
	public static final String FindBrushIcon = "//button[@id='Clear1']";
	/** @locatorType Xpath */
	public static final String MRFrame = "//iframe[contains(@title,'Create')]";
	/** @locatorType Xpath */
	public static final String ShiftLovButton = "//button[@id='P93131011_IMRHD_SHIFT_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String HeaderShiftField = "//span[@id='P9313101001_IMRHD_SHIFT_ID_DISPLAY']";

	/** @locatorType Xpath */
	public static final String MRLineDetails(int i) {
		return "//*[@id='line_ig_grid_vc']//table/tbody/tr[" + i + "]/td[13]";
	}

	/** @locatorType Xpath */
	public static final String ViewLineDetails_Orderdetail = "//h2[text()='SO & Order Details']/../..//button";
}
