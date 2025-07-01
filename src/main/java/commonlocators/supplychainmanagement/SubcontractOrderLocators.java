package commonlocators.supplychainmanagement;

public class SubcontractOrderLocators {

	/** @locatorType XPATH */
	public static final String FrameProdPoc = "//iframe[@title='Pend. Prod. Proc(OS)']";
	/** @locatorType XPATH */
	public static final String ProdOrderNo = "//button[@id='P161713106509_FIND_PROD_ORD_NO_lov_btn']";
	/** @locatorType XPATH */
	public static final String SearchButton = "//button[@id='B1590764421812797731']";
	/** @locatorType XPATH */
	public static final String SubcontractorLovButton = "//button[@id='P161713106509_SUPPLIER_ID_lov_btn']";
	/** @locatorType XPATH */
	public static final String OkButton = "//button[@id='B3918275513757666307']";
	/** @locatorType XPATH */
	public static final String LocationId = "//span[@id='P161713106501_POH_PLNT_LOC_ID_DISPLAY']";
	/** @locatorType XPATH */
	public static final String UnitId = "//span[@id='P161713106501_POH_PLANT_DISPLAY']";
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
	public static final String ScoNumber = "//span[@id='P161713106501_POH_ORDER_NO_DISPLAY']";
	/** @locatorType XPATH */
	public static final String FrameDetails = "//iframe[@title='Details']";
	/** @locatorType XPATH */
	public static final String SourceSFCode = "//input[@id='P161713106504_POL_SF_CODE']";
	/** @locatorType XPATH */
	public static final String TargetSFCode = "//*[@id='P161713106504_POL_TARG_SF_CODE_DISPLAY']";
	/** @locatorType XPATH */
	public static final String TarProcessDetails = "//span[@id='P161713106504_POL_TAR_PROC_DESC_DISPLAY']";
	/** @locatorType XPATH */
	public static final String UnitCost = "//span[@id='P161713106504_POL_SC_UNIT_COST_DISPLAY']";
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
	public static final String GLAccNoLovButton = "//button[@id='C6506495583289402861_lov_btn']";
	/** @locatorType XPATH */
	public static final String IncludeCharges = "//button[@id='B1851851069499840446']";
	/** @locatorType XPATH */
	public static final String Save = "//button[@id='SAVE']";
	/** @locatorType XPATH */
	public static final String HeaderPost = "//button[@id='B4058381139260659645']";
	/** @locatorType XPATH */
	public static final String ScoNo = "//button[@id='P16171310651_DOC_NO_lov_btn']";
	/** @locatorType XPATH */
	public static final String ScoSearchButton = "//button[@id='B1477710683887489103']";
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
	public static final String LineSave = "//button[@id='B4098090563776069687']";
	/** @locatorType XPATH */
	public static final String HsnTable = "//div[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[9]";
	/** @locatorType XPATH */
	public static final String HsnLovButton = "//button[@id='C701173524295359719_lov_btn']";
	/** @locatorType XPATH */
	public static final String TickButton = "//button[@id='B4058642769109660982']";
	/** @locatorType XPATH */
	public static final String ProcessName = "//table[@id='5935146900431362256_orig']/tbody/tr[2]/td[10]";
	/** @locatorType XPATH */
	public static final String QueueQty = "//table[@id='5935146900431362256_orig']/tbody/tr[2]/td[11]";
	/** @locatorType XPATH */
	public static final String CompQty = "(//table[@id='5935146900431362256_orig']/tbody/tr[2]/td/input)[1]";
	/** @locatorType XPATH */
	public static final String OutsideQty = "//table[@id='5935146900431362256_orig']/tbody/tr[2]/td[15]";
}
