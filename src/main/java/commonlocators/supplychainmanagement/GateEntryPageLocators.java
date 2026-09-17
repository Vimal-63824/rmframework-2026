package commonlocators.supplychainmanagement;

import commonfunctions.CommonFunctions;

/**
 * @author Durai
 * @company Roadmap May 02, 2025
 */
public class GateEntryPageLocators {

	/** @locatorType Xpath */
	public static final String ClickPendingButton = "//button[@data-testid='Create_GE']";
	/** @locatorType Xpath */
	public static final String ClickPendingPoButton = "//button[@data-testid='Pending_PO']";
	/** @locatorType Xpath */
	public static final String ClickPendingPoButtonLov = "//button[@id='P16151311001_PO_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String SearchAndSelectValue = "//*[@id=\"PopupLov_16151311001_P16151311001_PO_NO_dlg\"]/div[1]/input";
	/** @locatorType Xpath */
	public static final String PendingPOGIframe = "//iframe[@title='Pending ']";
	/** @locatorType Xpath */
	public static final String GateEntryPoSearchButton = "//button[@data-testid='Find']";
	/** @locatorType Xpath */
	public static final String GateEntryPendingPoSelectAll = "//input[contains(@id,'check_all')]";
	/** @locatorType Xpath */
	public static final String CreateGeFromPO = "//button[@data-testid='Create_GE']";
	/** @locatorType Xpath */
	public static final String SelectGateKeeper = "//select[contains(@data-testid,'GACDT_GATE_KEEPER')]";
	/** @locatorType Xpath */
	public static final String DcNumberField = "//input[contains(@data-testid,'GACDT_DC_NO')]";
	/** @locatorType Xpath */
	public static final String SupplierBillNumber = "//input[contains(@data-testid,'GACDT_INV_NO')]";
	/** @locatorType Xpath */
	public static final String VehicleNumber = "//input[contains(@data-testid,'GACDT_VEHICLE_NO')]";
	/** @locatorType Xpath */
	public static final String DriverName = "//input[contains(@data-testid,'GACDT_DRIVER_NAME')]";
	/** @locatorType Xpath */
	public static final String ReferenceField = "//input[contains(@data-testid,'GACDT_REF')]";
	/** @locatorType Xpath */
	public static final String OkButton = "//button[contains(@data-testid,'OK')]";
	/** @locatorType Xpath */
	public static final String EwayBillNumber = "//input[@data-testid='P751310201_GEHD_EWB_BILL_NO']";
	/** @locatorType Xpath */
	public static final String PostButton = "//button[@data-testid='Post']";
	/** @locatorType Xpath */
	public static final String OverAllSaveButton = "//button[@data-testid='SAVE']";
	/** @locatorType Xpath */
	public static final String DocNumber = "//span[@data-testid='P751310201_GEHD_DOC_NO']";
	/** @locatorType Xpath */
	public static final String PendDateInput = "//*[@data-testid='P16151311001_PO_DATE']/input";
	/** @locatorType Xpath */
	public static final String ClickPendingSsooButton = "//button[@data-testid='Pending_supplier_schedule']";
	/** @locatorType Xpath */
	public static final String PendingSsoGIframe = "//*[@id=\"apex_dialog_1\"]/iframe";
	/** @locatorType Xpath */
	public static final String PendDateInputSso = "//input[@id='P1615131109_DOC_DATE_input']";
	/** @locatorType Xpath */
	public static final String GateEntrySsoSearchButton = "//button[@data-testid='Find']";
	/** @locatorType Xpath */
	public static final String CreateGeFromSso = "//span[text()='Create GE']/ancestor::button";
	/** @locatorType Xpath */
	public static final String PendSupplierInput = "//input[@id='P1615131109_SUPPLIER']";
	/** @locatorType Xpath */
	public static final String SelectGateKeeperPo = "//select[@data-testid='P16151311001_GACDT_GATE_KEEPER']";
	/** @locatorType Xpath */
	public static final String SelectGateKeeperSso = "//select[@data-testid='P1615131109_GACDT_GATE_KEEPER']";
	/** @locatorType Xpath */
	public static final String DcNumberFieldPo = "//input[@data-testid='P16151311001_GACDT_DC_NO']";
	/** @locatorType Xpath */
	public static final String DcNumberFieldSso = "//input[@data-testid='P1615131109_GACDT_DC_NO']";
	/** @locatorType Xpath */
	public static final String SupplierBillNumberPo = "//input[@data-testid='P16151311001_GACDT_INV_NO']";
	/** @locatorType Xpath */
	public static final String SupplierBillNumberSso = "//input[@data-testid='P1615131109_GACDT_INV_NO']";
	/** @locatorType Xpath */
	public static final String VehicleNumberPo = "//input[@data-testid='P16151311001_GACDT_VEHICLE_NO']";
	/** @locatorType Xpath */
	public static final String VehicleNumberSso = "//input[@data-testid='P1615131109_GACDT_VEHICLE_NO']";
	/** @locatorType Xpath */
	public static final String DriverNamePo = "//input[@data-testid='P16151311001_GACDT_DRIVER_NAME']";
	/** @locatorType Xpath */
	public static final String DriverNameSso = "//input[@data-testid='P1615131109_GACDT_DRIVER_NAME']";
	/** @locatorType Xpath */
	public static final String ReferenceFieldPo = "//input[@data-testid='P16151311001_GACDT_REF']";
	/** @locatorType Xpath */
	public static final String ReferenceFieldSso = "//input[@data-testid='P1615131109_GACDT_REF']";
	/** @locatorType Xpath */
	public static final String OkButtonPo = "//button[@data-testid='OK_ge']";
	/** @locatorType Xpath */
	public static final String OkButtonSso = "//button[@data-testid='OK']";
	/** @locatorType Xpath */
	public static final String DateInputSso = "//*[@data-testid='P1615131109_DATE_1']/input";
	/** @locatorType Xpath */
	public static final String DateInputPo = "//*[@data-testid='P16151311001_DATE_1']/input";
	/** @locatorType Xpath */
	public static final String DocNumSso = "//input[@data-testid='P1615131109_DOC_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String GateKeeperSso = "//input[@data-testid='P751310201_GEHD_GK_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String GateEntryAdd = "//button[@data-testid='add']";
	/** @locatorType Xpath */
	public static final String GateEntryLocLov = "//*[@id='P7513102011_GEHD_PLNT_LOC_ID_lov_btn']/span";
	/** @locatorType Xpath */
	public static final String GateEntryType = "//select[@data-testid='P75131020_GEHD_MODE']";
	/** @locatorType Xpath */
	public static final String GateKeeperDirectLov = "//input[@data-testid='P75131020_GEHD_GK_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String DirectDriverNameGate = "//input[@data-testid='P75131020_GEHD_DRIVER_NAME']";
	/** @locatorType Xpath */
	public static final String DirectGateCreate = "//button[@id='B1072391168872991320']";
	/** @locatorType Xpath */
	public static final String DirectGateAddLine = "//button[@data-testid='Add_Line']";
	/** @locatorType Xpath */
	public static final String DirectGatePartyField = "//table[@class=\"a-GV-table\"]/tbody/tr/td[4]";
	/** @locatorType Xpath */
	public static final String DirectGatePartyLov = "//input[@data-testid='GELN_SUPLR_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String DirectGateLineSave = "//button[@data-testid='Save_Line']";
	/** @locatorType Xpath */
	public static final String DirectGateItemLineAdd = "//button[@data-testid='Add_Detail']";
	/** @locatorType Xpath */
	public static final String DirectGateItemLineField = "//*[@id=\"ge_dtl_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr/td[3]";
	/** @locatorType Xpath */
	public static final String DirectGateItemLineLov = "//input[@data-testid=\"GEDL_PROD_ID_REV\"]/following-sibling::button";
	/** @locatorType Xpath */
	public static final String DirectGateQtyInput = "//input[@data-testid='GEDL_QTY']";
	/** @locatorType Xpath */
	public static final String DirectGateQtyField = "//*[@id=\"ge_dtl_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr/td[10]";
	/** @locatorType Xpath */
	public static final String DirectGateCostField = "//*[@id=\"ge_dtl_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr/td[12]";
	/** @locatorType Xpath */
	public static final String DirectGateCostInput = "//input[@data-testid='GEDL_UNIT_COST']";
	/** @locatorType Xpath */
	public static final String DirectGateItemLineSave = "//button[@data-testid='Save_Detail']";
	/** @locatorType Xpath */
	public static final String DirectGateOverallSave = "//button[@data-testid='SAVE']";
	/** @locatorType Xpath */
	public static final String DirectGatePost = "//button[@data-testid='Post']";
	/** @locatorType Xpath */
	public static final String DirectGateUnloadBtn = "//label[contains(text(),'Unloading')]";
	/** @locatorType Xpath */
	public static final String DirectGateEWayNo = "//input[@data-testid='P751310201_GEHD_EWB_BILL_NO']";
	/** @locatorType Xpath */
	public static final String GateEntryDocNum = "//*[@id='P751310201_GEHD_DOC_NO_DISPLAY']";

	/** @locatorType Xpath */
	public static final String ItemName(int i) {
		return "//*[@id=\"ge_dtl_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr[" + i + "]/td[3]";
	}

	/** @locatorType Xpath */
	public static final String QtyField(int i) {
		return "//*[@id=\"ge_dtl_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr[" + i + "]/td[10]";
	}

	/** @locatorType Xpath */
	public static final String CostField(int i) {
		return "//*[@id=\"ge_dtl_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr[" + i + "]/td[12]";
	}

	/** @locatorType Xpath */
	public static final String LocId = "//input[@data-testid='P75131020_LOC_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String Unit = "//input[@data-testid='P75131020_PLNT_1']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String GENNo = "//input[@data-testid='P75131020_DOC_NO_1']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SupplierNameLovButton = "//input[@data-testid='P75131020_PARTY_1']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String GateKeeperLovButton = "//input[@data-testid='P75131020_GATE_KEEPER_1']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String ItemId = "//input[@data-testid='P75131020_PROD_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String ItemDesc = "//input[@data-testid='P75131020_ITEM_DESC']";
	/** @locatorType Xpath */
	public static final String GRN_Status = "//select[@data-testid='P75131020_SRCH_STATUS']";
	/** @locatorType Xpath */
	public static final String HeaderCheckBox = "(//input[@data-testid='P75131020_RADIO_GRP']/following-sibling::label)[1]";
	/** @locatorType Xpath */
	public static final String LineCheckBox = "(//input[@data-testid='P75131020_RADIO_GRP']/following-sibling::label)[2]";
	/** @locatorType Xpath */
	public static final String GENSearchBtn = "//button[@data-testid='Find_header']";
	/** @locatorType Xpath */
	public static final String AmendFindTable = "//table[@data-testid=\"H_Result_Data\"]/tbody/tr/td[2]";
	/** @locatorType Xpath */
	public static final String AmendLineFindTable = "//table[@data-testid=\"L_Result_Data\"]/tbody/tr/td[3]";
	/** @locatorType Xpath */
	public static final String GENPendBtn = "//button[@data-testid='Create_GE']";

	/** @locatorType Xpath */
	public static final String GENLineFindTableData(int i) {
		return "//table[@id='2047983734453989109_orig']/tbody/tr/td[" + i + "]";
	}

	/** @locatorType Xpath */
	public static final String GENFindTableData(int i) {
		return "//table[@id='23264372453714718596_orig']/tbody/tr/td[" + i + "]";
	}

	/** @locatorType Xpath */
	public static final String FindGateEntryNoLovBtn = "//input[@data-testid='P75131020_DOC_NO_1']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SearchButton = "//button[@data-testid='Find_header']";
	/** @locatorType Xpath */
	public static final String HeaderLabelButton = "(//input[@data-testid='P75131020_RADIO_GRP']/following-sibling::label)[1]";

	/** @locatorType Xpath */
	public static final String FindGateEntryNumber(String value) {
		return "(//a[text()='" + value + "']/../../td/div[contains(@style,'color')])[last()]";
	}

	/** @locatorType Xpath */
	public static final String OpoAddFrame = "//iframe[@title='Open PO/RC ']";
	/** @locatorType Xpath */
	public static final String GateDirectFrame = "//iframe[@title='Create Gate Entry ']";
	/** @locatorType Xpath */
	public static final String PendingStockTransfer = "//button[@data-testid='Pending_ST']";
	/** @locatorType Xpath */
	public static final String InvoiceNumberLovBtn = "//input[@data-testid='P1615131100_PO_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String IframePendingST = "//iframe[@title='Pending Stock Transfer']";
	/** @locatorType Xpath */
	public static final String PendingMoveGE = "//button[@data-testid='Create_GE_Stock']";
	/** @locatorType Xpath */
	public static final String SelectAll = "//input[@id='check_st']";
	/** @locatorType Xpath */
	public static final String StDriverName = "//input[@data-testid='P751310201_GEHD_DRIVER_NAME']";
	/** @locatorType Xpath */
	public static final String PendingSupplierSchedule = "//button[@data-testid=\"Pending_supplier_schedule\"]";
	/** @locatorType Xpath */
	public static final String IFrameSSO = "//iframe[@title='Pend. Suplr. Schd.']";
	/** @locatorType Xpath */
	public static final String SSODocumentLov = "//button[contains(@id,'DOC_NO_lov_btn')]";
	/** @locatorType Xpath */
	public static final String SSOSearchButton = "//button[@data-testid='Find']";
	/** @locatorType Xpath */
	public static final String CreateGEButton = "//button[@id='B1']";
	/** @locatorType Xpath */
	public static final String DriverNameInput = "//input[contains(@data-testid,'GEHD_DRIVER_NAME')]";
	/** @locatorType Xpath */
	public static final String QtyValue = "(//input[@type='number'])[1]";
	/** @locatorType Xpath */
	public static final String FirstCheckBox = "(//input[@type='number']/../../td[14]/input)[1]";
	/** @locatorType Xpath */
	public static final String GENO = "//span[contains(@data-testid,'GEHD_DOC_NO')]";
	/** @locatorType Xpath */
	public static final String GECorrection = "//button[@data-testid='Correction']";
	/** @locatorType Xpath */
	public static final String CancelButton = "//button[@data-testid='Cancel']";
	/** @locatorType Xpath */
	public static final String ReasonLovButton = "//button[contains(@id,'REASON_lov_btn')]";
	/** @locatorType Xpath */
	public static final String ReasonOkButton = "//button[@data-testid='OK']";

	/** @locatorType Xpath */
	public static final String GateEntryDocNo(String DocNo) {
		return "(//a[text()='" + DocNo + "'])[last()]";
	}

	/** @locatorType Xpath */
	public static final String PendingGatePOLov = "//input[@data-testid=\"P16151311001_PO_NO\"]/following-sibling::button";
	/** @locatorType Xpath */
	public static final String GateEntryPendingPoSelect = "//table[contains(@aria-label,'PENDING')]/tbody/tr/td[15]";
	/** @locatorType Xpath */
	public static final String GateEntryPoClearButton = "//button[@data-testid='Find']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String GateEntryHeaderSearchBar = "(//input[@title='Search Report'])[last()]";
	/** @locatorType Xpath */
	public static final String GateEntryHeaderSearchGoBtn = "(//input[@title='Search Report'])[last()]/../following-sibling::div/button";

	/** @locatorType Xpath */
	public static final String GateAmendFindTableData(String i) {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(GateFindTable, i);
	}

	/** @locatorType Xpath */
	public static final String GateFindTable = "//table[@data-testid='H_Result_Data']/tr/th";
	/** @locatorType Xpath */
	public static final String GateLineFindTable = "//table[@data-testid='L_Result_Data']/tbody/tr/th";

	/** @locatorType Xpath */
	public static final String GateAmendLineFindTableData(String i) {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(GateLineFindTable, i);
	}

	/** @locatorType Xpath */
	public static final String AddTableGateScreen = "(//table[@data-testid=\"ge_line_Result_Data\"])[2]";
	/** @locatorType Xpath */
	public static final String PendingPOShowMyRecordBtn = "//button[@data-testid='Show_My_Record_Show']";
	/** @locatorType Xpath */
	public static final String PendingPOShowMyRecordHideBtn = "//button[@data-testid='Show_My_Record_Hide']";
	/** @locatorType Xpath */
	public static final String ShowMore = "//span[text()='Show More']/..";

	/** @locatorType Xpath */
	public static final String GatePagePartyTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddTableGateScreen, "Party ID");
	}
}
