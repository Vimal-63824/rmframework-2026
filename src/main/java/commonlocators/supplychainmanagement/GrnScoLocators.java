package commonlocators.supplychainmanagement;

public class GrnScoLocators {

	/** @locatorType Xpath */
	public static final String PendingSubcontractOrder = "//button[@id='B1636155047592353257']";
	/** @locatorType Xpath */
	public static final String PendingScoFrame = "//iframe[@title='Pending SCO']";
	/** @locatorType Xpath */
	public static final String SCONoLovButton = "//button[@id='P1615131101_PO_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String SearchButton = "//button[@id='B65134744849086189']";
	/** @locatorType Xpath */
	public static final String CreateGrnButton = "//button[@id='B1']";
	/** @locatorType Xpath */
	public static final String SupplierBillNumber = "//input[@id='P1615131101_SUPLR_BILL_NO']";
	/** @locatorType Xpath */
	public static final String DCNumber = "//input[@id='P1615131101_DC_NO']";
	/** @locatorType Xpath */
	public static final String OkButton = "//button[@id='B1733022588955801954']";
	/** @locatorType Xpath */
	public static final String ItemTable = "//div[@id='add_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[3]";
	/** @locatorType Xpath */
	public static final String LineProcess = "//div[@id='add_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[29]";
	/** @locatorType Xpath */
	public static final String SourceSFCode = "//div[@id='add_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[27]";
	/** @locatorType Xpath */
	public static final String TargetSFCode = "//div[@id='add_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[28]";
	/** @locatorType Xpath */
	public static final String Consmp = "//div[@id='add_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[30]";
	/** @locatorType Xpath */
	public static final String ViewDetails = "//div[@id='add_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[31]";
	/** @locatorType Xpath */
	public static final String ConsumptionFrame = "//iframe[@title='Subcontract GRN Consumption Details']";
	/** @locatorType Xpath */
	public static final String DCDetails = "//div[@id='R4641908672885934946_ig_grid_vc']/div[3]/div[@aria-label='No data found']";
	/** @locatorType Xpath */
	public static final String ConsumptionDetails = "//div[@id='grn_cons_ig_grid_vc']/div[3]/div[@aria-label='No data found']";
	/** @locatorType Xpath */
	public static final String BatchDetails = "//div[@id='R4641851069081897697_ig_grid_vc']/div[3]/div[@aria-label='No data found']";
	/** @locatorType Xpath */
	public static final String ConsumptionCloseButton = "//h1[text()='Subcontract GRN Consumption Details']/../button";
	/** @locatorType Xpath */
	public static final String GenMatCons = "//button[@id='B1199781663539983664']";
	/** @locatorType Xpath */
	public static final String Allocate = "//button[@id='B1199780068377983662']";
	/** @locatorType Xpath */
	public static final String GrnNumber = "//input[@id='P19315104001_PORH_RECEIPT_NO']";
	/** @locatorType Xpath */
	public static final String ConsumptionItem = "//div[@id='grn_cons_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[5]";
	/** @locatorType Xpath */
	public static final String AllatedQty = "//div[@id='grn_cons_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[14]";
	/** @locatorType Xpath */
	public static final String FrameLineDetails = "//iframe[@title='GRN SCO Line']";
	/** @locatorType Xpath */
	public static final String CloseLineDetials = "//h1[text()='GRN SCO Line']/../button";
	/** @locatorType Xpath */
	public static final String SCONumber = "//input[@id='P1615131103011_PORL_PO_NO']";
	/** @locatorType Xpath */
	public static final String PONumber = "//input[@id='P1615131103011_PORL_PROD_ORD_NO']";
	/** @locatorType Xpath */
	public static final String QcRequired = "//span[@id='P1615131103011_PORL_QC_SEL_FLAG_DISPLAY']";
	/** @locatorType Xpath */
	public static final String IGSTNumber = "//span[@id='P1615131103011_PORL_IGST_AMT_DISPLAY']";
	/** @locatorType Xpath */
	public static final String TickButton = "//button[@id='B1199777628433983659']";
	/** @locatorType Xpath */
	public static final String PostButton = "//button[@id='B1199782803085983665']";
	/** @locatorType Xpath */
	public static final String GenLotSerial = "//button[@id='B1199780886030983662']";

	/** @locatorType Xpath */
	public static final String ScoNumber(String SCONo) {
		return "//td[text()='" + SCONo + "']";
	}

	/** @locatorType Xpath */
	public static final String CheckBox(String SCONo) {
		return "(//td[text()='" + SCONo + "']/../td/input[@type='checkbox'])[1]";
	}

	/** @locatorType Xpath */
	public static final String DCNumber(int i) {
		return "//div[@id='R4641908672885934946_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[3]";
	}

	/** @locatorType Xpath */
	public static final String GRNLovButton = "//button[@id='P193151040_GRN_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String GrnSearchButton = "//button[@id='B1188356911522632446']";

	/** @locatorType Xpath */
	public static final String GRNNumberSelect(String Number) {
		return "//a[text()='" + Number + "']";
	}

	/** @locatorType Xpath */
	public static final String GRNNumberStatus(String Number) {
		return "//a[text()='" + Number + "']/../../td/div[contains(@style,'green')]";
	}

	/** @locatorType Xpath */
	public static final String SupplierLotNumber = "//div[@id='grn_lot_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[2]";
	/** @locatorType Xpath */
	public static final String HeatNumber = "//div[@id='grn_lot_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[3]";
	/** @locatorType Xpath */
	public static final String HeatNumberInput = "//input[@data-testid='PRCLS_HEAT_NO']";
	/** @locatorType Xpath */
	public static final String TestNumber = "//div[@id='grn_lot_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[4]";
	/** @locatorType Xpath */
	public static final String TestNumberInput = "//input[@data-testid='PRCLS_TEST_NO']";
	/** @locatorType Xpath */
	public static final String SaveButton = "//button[@id='B1201434080648209753']";

}
