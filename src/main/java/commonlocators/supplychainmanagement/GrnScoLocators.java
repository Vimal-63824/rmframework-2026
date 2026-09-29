package commonlocators.supplychainmanagement;

public class GrnScoLocators {

	/** @locatorType Xpath */
	public static final String PendingSubcontractOrder = "(//button[@data-testid='Pending_PO_SCO'])[last()]";
	/** @locatorType Xpath */
	public static final String PendingScoFrame = "//iframe[@title='Create SCO']";
	/** @locatorType Xpath */
	public static final String SCONoLovButton = "//input[@data-testid='P1615131101_PO_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SearchButton = "//button[@data-testid='RESULT']";
	/** @locatorType Xpath */
	public static final String CreateGrnButton = "//button[@data-testid='Create_Grn_PO']";
	/** @locatorType Xpath */
	public static final String SupplierBillNumber = "//input[@data-testid='P1615131101_SUPLR_BILL_NO']";
	/** @locatorType Xpath */
	public static final String DCNumber = "//input[@data-testid='P1615131101_DC_NO']";
	/** @locatorType Xpath */
	public static final String OkButton = "//button[@data-testid='OK']";
	/** @locatorType Xpath */
	public static final String ItemTable = "//div[@id='add_line_ig_grid_vc']//table/tbody/tr[1]/td[3]";
	/** @locatorType Xpath */
	public static final String LineProcess = "//div[@id='add_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[29]";
	/** @locatorType Xpath */
	public static final String SourceSFCode = "//div[@id='add_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[27]";
	/** @locatorType Xpath */
	public static final String TargetSFCode = "//div[@id='add_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[28]";
	/** @locatorType Xpath */
	public static final String Consmp = "//a[@data-action='LN_CONSMP']";
	/** @locatorType Xpath */
	public static final String ViewDetails = "//a[@data-action='LN_DTLS']";
	/** @locatorType Xpath */
	public static final String ConsumptionFrame = "//iframe[@title='Subcontract GRN Consumption Details']";
	/** @locatorType Xpath */
	public static final String DCDetails = "//div[@aria-label='DC Details']/div[3]/div[@aria-label='No data found']";
	/** @locatorType Xpath */
	public static final String ConsumptionDetails = "//div[@id='grn_cons_ig_grid_vc']/div[3]/div[@aria-label='No data found']";
	/** @locatorType Xpath */
	public static final String BatchDetails = "//div[@aria-label='Batch Details']/div[3]/div[@aria-label='No data found']";
	/** @locatorType Xpath */
	public static final String ConsumptionCloseButton = "//h1[text()='Subcontract GRN Consumption Details']/../button";
	/** @locatorType Xpath */
	public static final String GenMatCons = "//button[@data-testid='Gen_Mat_Cons']";
	/** @locatorType Xpath */
	public static final String Allocate = "//button[@data-testid='Alloc_Flag']";
	/** @locatorType Xpath */
	public static final String GrnNumber = "//input[@data-testid='P19315104001_PORH_RECEIPT_NO']";
	/** @locatorType Xpath */
	public static final String ConsumptionItem = "//div[@id='grn_cons_ig_grid_vc']//table/tbody/tr[1]/td[5]";
	/** @locatorType Xpath */
	public static final String AllatedQty = "//div[@id='grn_cons_ig_grid_vc']//table/tbody/tr[1]/td[14]";
	/** @locatorType Xpath */
	public static final String FrameLineDetails = "//iframe[@title='GRN SCO Line']";
	/** @locatorType Xpath */
	public static final String CloseLineDetials = "//h1[text()='GRN SCO Line']/../button";
	/** @locatorType Xpath */
	public static final String SCONumber = "//input[@data-testid='P1615131103011_PORL_PO_NO']";
	/** @locatorType Xpath */
	public static final String PONumber = "//input[@data-testid='P1615131103011_PORL_PROD_ORD_NO']";
	/** @locatorType Xpath */
	public static final String QcRequired = "//span[@data-testid='P1615131103011_PORL_QC_SEL_FLAG']";
	/** @locatorType Xpath */
	public static final String IGSTNumber = "//span[@data-testid='P1615131103011_PORL_IGST_AMT']";
	/** @locatorType Xpath */
	public static final String TickButton = "//button[@data-testid='SAVE']";
	/** @locatorType Xpath */
	public static final String PostButton = "//button[@data-testid='Post']";
	/** @locatorType Xpath */
	public static final String GenLotSerial = "//button[@data-testid='Gen_Lot_Serial']";

	/** @locatorType Xpath */
	public static final String ScoNumber(String SCONo) {
		return "//table[contains(@id,'orig')]//td[text()='" + SCONo + "']";
	}

	/** @locatorType Xpath */
	public static final String CheckBox(String SCONo) {
		return "(//td[text()='" + SCONo + "']/../td/input[@type='checkbox'])[1]";
	}

	/** @locatorType Xpath */
	public static final String DCNumber(int i) {
		return "//div[@aria-label='DC Details']//table/tbody/tr[" + i + "]/td[2]";
	}

	/** @locatorType Xpath */
	public static final String GRNLovButton = "//input[@data-testid='P193151040_GRN_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String GrnSearchButton = "//button[@data-testid='Line']";

	/** @locatorType Xpath */
	public static final String GRNNumberSelect(String Number) {
		return "//a[text()='" + Number + "']";
	}

	/** @locatorType Xpath */
	public static final String SupplierLotNumber = "//div[@id='grn_lot_ig_grid_vc']//table/tbody/tr/td[2]";
	/** @locatorType Xpath */
	public static final String HeatNumber = "//div[@id='grn_lot_ig_grid_vc']//table/tbody/tr/td[3]";
	/** @locatorType Xpath */
	public static final String HeatNumberInput = "//input[@data-testid='PRCLS_HEAT_NO']";
	/** @locatorType Xpath */
	public static final String TestNumber = "//div[@id='grn_lot_ig_grid_vc']//table/tbody/tr/td[4]";
	/** @locatorType Xpath */
	public static final String TestNumberInput = "//input[@data-testid='PRCLS_TEST_NO']";
	/** @locatorType Xpath */
	public static final String SaveButton = "//button[@data-testid='Save_Lot_ser']";
	/** @locatorType Xpath */
	public static final String UP_Type_Field = "(//input[@type='number'])[2]";
	/** @locatorType Xpath */
	public static final String UP_Type_Input = "(//input[@type='number'])[2]";
	/** @locatorType Xpath */
	public static final String PR_Type_Field = "(//input[@type='number'])[1]";
	/** @locatorType Xpath */
	public static final String US_Type_Field = "(//input[@style='text-align:right;width:70px'])[5]";
	/** @locatorType Xpath */
	public static final String CS_Type_Field = "(//input[@type='number'])[3]";
	/** @locatorType Xpath */
	public static final String PendingGateEntry = "//button[@data-testid='Pending_GE']";
	/** @locatorType Xpath */
	public static final String PendingGateEntryDocNo = "//input[@data-testid='P1615131101_GE_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PendingGateEntry_SelectAll = "//*[@id='check_allge']";
	/** @locatorType Xpath */
	public static final String Pending_CreateGateEntry = "//button[@data-testid='Create_GRN_Gate']";
	/** @locatorType Xpath */
	public static final String Pending_CreateGateEntry_OkButton = "//button[@data-testid='GE_OK']";
	/** @locatorType Xpath */
	public static final String GRN_SCO_AddIcon = "//button[@data-testid='add']";
	/** @locatorType Xpath */
	public static final String GRN_SCO_Add_Iframe = "//iframe[@title='Create GRN - SCO']";
	/** @locatorType Xpath */
	public static final String GRN_SCO_Add_Loc_Lov = "//input[@data-testid='P193151040_PORH_PLNT_LOC_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String GRN_SCO_Add_Supl_Lov = "//input[@data-testid='P193151040_PORH_SUPLR_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String GRN_SCO_Add_SuplBill_Input = "//input[@data-testid='P193151040_PORH_SUPLR_DOC_N']";
	/** @locatorType Xpath */
	public static final String GRN_SCO_Add_DC_Date = "//*[@id='P193151041_PORH_DC_DATE_1_dialog']/div/div[3]/div/button";
	/** @locatorType Xpath */
	public static final String GRN_SCO_Add_DC_DateInput = "//input[@data-testid='P193151040_PORH_DC_NO_1']";
	/** @locatorType Xpath */
	public static final String GRN_SCO_FindCreateIcon = "//button[@data-testid='CREATE']";
	/** @locatorType Xpath */
	public static final String GRN_SCO_FetchButton = "//button[@data-testid='Fetch_Line']";
	/** @locatorType Xpath */
	public static final String Add_Grn_Line = "//button[@data-testid='Add_GRN_Line']";
	/** @locatorType Xpath */
	public static final String Get_GRN_SuplLotNo = "//*[@id='grn_lot_ig_grid_vc']//table/tbody/tr/td[2]";
	/** @locatorType Xpath */
	public static final String GRN_Heat_No = "//*[@id='grn_lot_ig_grid_vc']//table/tbody/tr/td[3]";
	/** @locatorType Xpath */
	public static final String GRN_Test_No = "//*[@id='grn_lot_ig_grid_vc']//table/tbody/tr/td[4]";
	/** @locatorType Xpath */
	public static final String GRN_Heat_No_input = "//input[@data-testid='PRCLS_HEAT_NO']";
	/** @locatorType Xpath */
	public static final String GRN_Test_No_Input = "//input[@data-testid='PRCLS_TEST_NO']";
	/** @locatorType Xpath */
	public static final String GRN_SCO_LotSerial_Save = "//button[@data-testid='Save_Lot_ser']";
	/** @locatorType Xpath */
	public static final String SDS_Type_Field = "(//input[@type='number'])[2]";
	/** @locatorType Xpath */
	public static final String FirstItemOption = "//div[@id='add_line_ig_grid_vc']//table/tbody/tr[1]/td[1]/a";

	/** @locatorType Xpath */
	public static final String GRNNumberStatus(String Number) {
		return "//a[text()='" + Number + "']/../../td/div[contains(@style,'color')]";
	}

	/** @locatorType Xpath */
	public static final String SourceDocuments = "//h2[text()='Source Document']/../..//button";
	/** @locatorType Xpath */
	public static final String OthersButton = "//h2[text()='Others']/../..//button";
	/** @locatorType Xpath */
	public static final String GRN_SCO_Add_GRNPfx_Lov = "//input[@data-testid='P193151040_PORH_RECEIPT_PFX_1']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String GRN_SCO_Add_GRNDate = "//*[@id=\"P193151041_PORH_GRN_DATE_dialog\"]/div/div[3]/div/button";
	/** @locatorType Xpath */
	public static final String GRNSCO_ImportLR = "//button[@data-testid='Import_LR']";
	/** @locatorType Xpath */
	public static final String GRNSCO_ImportLR_Input = "//input[@data-testid='P19315104001_PID_BOE_NO']";
	/** @locatorType Xpath */
	public static final String GRNSCO_ImportLR_date = "//*[@id=\"P19315104001_PID_BOE_DATE_dialog\"]/div/div[3]/div/button";
	/** @locatorType Xpath */
	public static final String GRNSCO_ImportLR_ok_btn = "//button[@data-testid='OK_import_LR']";
}
