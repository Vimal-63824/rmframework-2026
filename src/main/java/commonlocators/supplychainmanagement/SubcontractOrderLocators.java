package commonlocators.supplychainmanagement;

public class SubcontractOrderLocators {

	/** @locatorType XPATH */
	public static final String FrameProdPoc = "//iframe[@title='Pend. Prod. Proc(OS)']";
	/** @locatorType XPATH */
	public static final String ProdOrderNo = "//input[@data-testid='P161713106509_FIND_PROD_ORD_NO']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String SearchButton = "//button[@data-testid='Search']";
	/** @locatorType XPATH */
	public static final String SubcontractorLovButton = "//input[@data-testid='P161713106509_SUPPLIER_ID']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String OkButton = "//button[@data-testid='Ok']";
	/** @locatorType XPATH */
	public static final String LocationId = "//span[@data-testid='P161713106501_POH_PLNT_LOC_ID']";
	/** @locatorType XPATH */
	public static final String UnitId = "//span[@data-testid='P161713106501_POH_PLANT']";
	/** @locatorType XPATH */
	public static final String SupplierName = "//span[@id='P161713106501_POH_SUPLR_NAME_DISPLAY']";
	/** @locatorType XPATH */
	public static final String MRPButton = "//div[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[1]/a";
	/** @locatorType XPATH */
	public static final String FrameMaterial = "//iframe[@title='Material']";
	/** @locatorType XPATH */
	public static final String Item = "//div[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[3]";
	/** @locatorType XPATH */
	public static final String Uom = "//div[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[6]";
	/** @locatorType XPATH */
	public static final String TarProcess = "//div[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[14]";
	/** @locatorType XPATH */
	public static final String CloseButton = "//h1[text()='Material']/../button";
	/** @locatorType XPATH */
	public static final String ScoNumber = "//span[@data-testid='P161713106501_POH_ORDER_NO']";
	/** @locatorType XPATH */
	public static final String FrameDetails = "//iframe[@title='Details']";
	/** @locatorType XPATH */
	public static final String SourceSFCode = "//input[@data-testid='P161713106504_POL_SF_CODE']";
	/** @locatorType XPATH */
	public static final String TargetSFCode = "//*[@data-testid='P161713106504_POL_TARG_SF_CODE']";
	/** @locatorType XPATH */
	public static final String TarProcessDetails = "//span[@data-testid='P161713106504_POL_TAR_PROC_DESC']";
	/** @locatorType XPATH */
	public static final String UnitCost = "//span[@data-testid='P161713106504_POL_SC_UNIT_COST']";
	/** @locatorType XPATH */
	public static final String CloseDetails = "//h1[text()='Details']/../button";
	/** @locatorType XPATH */
	public static final String LineDetails = "(//div[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td/a)[2]";
	/** @locatorType XPATH */
	public static final String OrderedQuantity = "//div[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[7]";
	/** @locatorType XPATH */
	public static final String LineUnitCost = "//div[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[8]";
	/** @locatorType XPATH */
	public static final String ConvFactor = "//div[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[7]";
	/** @locatorType XPATH */
	public static final String BatchQuantity = "//div[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[5]";
	/** @locatorType XPATH */
	public static final String ProcessQuantity = "//div[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[11]";
	/** @locatorType XPATH */
	public static final String BatchCost = "//div[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[6]";
	/** @locatorType XPATH */
	public static final String AssembleValue = "//div[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[14]";
	/** @locatorType XPATH */
	public static final String GLAccNo = "//div[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[21]";
	/** @locatorType XPATH */
	public static final String GLAccNoLovButton = "//input[@data-testid='SCOP_PUR_ACCT']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String IncludeCharges = "//button[@data-testid='Include_Charge']";
	/** @locatorType XPATH */
	public static final String Save = "//button[@data-testid='SAVE']";
	/** @locatorType XPATH */
	public static final String HeaderPost = "//button[@data-testid='Entry_Completed']";
	/** @locatorType XPATH */
	public static final String ScoNo = "//input[@data-testid='P16171310651_DOC_NO']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String ScoSearchButton = "//button[@data-testid='Find_Lines']";
	/** @locatorType XPATH */
	public static final String TaxPercentage = "//div[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[13]";
	/** @locatorType XPATH */
	public static final String IgstValue = "//div[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[15]";

	/** @locatorType XPATH */
	public static final String ApprovedStatus(String status) {
		return "//a[text()='" + status + "']/../../td/div[contains(@style,'green')]";
	}

	/** @locatorType XPATH */
	public static final String CheckBox(String PONumber) {
		return "//a[text()='" + PONumber + "']/../../td[13]";
	}

	/** @locatorType XPATH */
	public static final String CgstValue = "//div[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[16]";
	/** @locatorType XPATH */
	public static final String SgstValue = "//div[@id='ig_line4_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[17]";
	/** @locatorType XPATH */
	public static final String LineSave = "//button[@data-testid='Active']";
	/** @locatorType XPATH */
	public static final String HsnTable = "//div[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[9]";
	/** @locatorType XPATH */
	public static final String HsnLovButton = "//input[@data-testid='POL_HSN_CODE']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String TickButton = "//button[@data-testid='Save_1']";
	/** @locatorType XPATH */
	public static final String ProcessName = "//table[@id='5935146900431362256_orig']/tbody/tr[2]/td[10]";
	/** @locatorType XPATH */
	public static final String QueueQty = "//table[@id='5935146900431362256_orig']/tbody/tr[2]/td[11]";
	/** @locatorType XPATH */
	public static final String CompQty = "(//table[@id='5935146900431362256_orig']/tbody/tr[2]/td/input)[1]";
	/** @locatorType XPATH */
	public static final String OutsideQty = "//table[@id='5935146900431362256_orig']/tbody/tr[2]/td[15]";
}
