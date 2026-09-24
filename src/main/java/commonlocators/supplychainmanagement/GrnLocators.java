package commonlocators.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
public class GrnLocators {
	/** @locatorType Xpath */
	public static final String ClickPendingButton = "//button[@data-testid='GRNP_PEND_BTN']";
	/** @locatorType Xpath */
	public static final String PendingPO = "//button[@data-testid='GRNP_PEND_PO']";
	/** @locatorType Xpath */
	public static final String PendingPOIframe = "//iframe[@title='Pending Details']";
	/** @locatorType Xpath */
	public static final String PendingPOheader = "//h2[contains(@id,'R2323283483953540029_heading')]/..";
	/** @locatorType Xpath */
	public static final String PendingPOHeaderSelectPONumberLOV = "//button[contains(@id,'P1615131100_PO_NO_lov_btn')]";
	/** @locatorType Xpath */
	public static final String PendingPOHeaderPONumberInput = "//button[contains(@id,'P1615131100_PO_NO_lov_btn')]";
	/** @locatorType Xpath */
	public static final String PendingPOHeaderPONumberSelectValue = "//button[contains(@id,'B1636066413123209003')]";
	/** @locatorType Xpath */
	public static final String PendingLocLov = "//button[contains(@id,'P1615131100_PLNT_LOC_lov_btn')]";
	/** @locatorType Xpath */
	public static final String PendingPOHeaderSearchButtonForStocktransfer = "//button[@id='BTN_GRN_STS']";
	/** @locatorType Xpath */
	public static final String PendingPOHeaderSearchButton = "//button[@data-testid='Search']";
	/** @locatorType Xpath */
	public static final String PendingPOSelectAllButton = "//input[contains(@id,'check_allsts')]";
	/** @locatorType Xpath */
	public static final String PendingPOCreateGRN = "//button[@data-testid='GRNP_PEND_PUR_CRT_GRN']";
	/** @locatorType Xpath */
	public static final String PendingPOClickSupplierBillnumber = "//input[@data-testid='GRNP_PEND_PUR_CLK_SUP_BILL_NUM']";
	/** @locatorType Xpath */
	public static final String PendingPOClickDCNumber = "//input[@data-testid='GRNP_PEND_PUR_CLK_DC_NUM']";
	/** @locatorType Xpath */
	public static final String PendingPOClickOKButton = "//button[@data-testid='GRNP_PEND_PUR_CLK_OK_BTN']";
	/** @locatorType Xpath */
	public static final String grnheader = "//*[@id='t_TreeNav_226']/div[2]/a";
	/** @locatorType Xpath */
	public static final String ClickGrnPurchase = "//span[text()='Supply Chain']/ancestor::li/*/li[1]/*/li[2]/*/li/*[2]/a[text()='GRN - Purchase']";
	/** @locatorType Xpath */
	public static final String Lov = "//button[contains(@id,'P1615131103_UNIT_LOCATION_lov_btn')]";
	/** @locatorType Xpath */
	public static final String LotSerial = "//button[contains(@id,'B3157814851916052373')]";
	/** @locatorType Xpath */
	public static final String OverAllSave = "//button[@data-testid='SAVE']";
	/** @locatorType Xpath */
	public static final String PostButton = "//button[@data-testid='Post']";
	/** @locatorType Xpath */
	public static final String GrnDocNumber = "//span[@data-testid='P161513110303_PORH_RECEIPT_NO']";
	/** @locatorType Xpath */
	public static final String DocAprovalIfrmae = "//iframe[@title='Workflow Approval']";
	/** @locatorType Xpath */
	public static final String LotSerailButton = "//button[@data-testid='Lot_Serial']";
	/** @locatorType Xpath */
	public static final String AddLotSerialItem = "//button[@data-testid='Add_Lot']";

	/** @locatorType Xpath */
	public static final String LotSerialItemLov = "//input[@data-testid='PRLT_PROD_ID_REV']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String LotserialItemInput = "//input[contains(@class,'a-PopupLOV-search apex-item-text')]";
	/** @locatorType Xpath */
	public static final String LotNumberInput = "//input[@data-testid='PRLT_LOT_NO1']";
	/** @locatorType Xpath */
	public static final String SerialNumberInput = "//input[@data-testid='PRLT_START_SER_NO1']";
	/** @locatorType Xpath */
	public static final String LotSerialSaveButton = "//button[@data-testid='GRNP_SAVELOT_SER_BTN']";
	/** @locatorType Xpath */
	public static final String AportionLsQty = "//button[@data-testid='GRNP_APPORT_BTN']";
	/** @locatorType Xpath */
	public static final String LotSerialIframe = "//iframe[@title='GRN']";
	/** @locatorType Xpath */
	public static final String LotclickInput = "//*[@id='grn_lot_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[2]";
	/** @locatorType Xpath */
	public static final String LotSerialDate = "//*[@id='grn_lot_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[7]";
	/** @locatorType Xpath */
	public static final String DateInput = "//*[@data-testid='PRLT_EXPIRY_DATE']/input";
	/** @locatorType Xpath */
	public static final String LotSerialSave = "//button[@data-testid='Save_Lot']";
	/** @locatorType Xpath */
	public static final String ApportionLSQty = "//button[@data-testid='Apportion']";
	/** @locatorType Xpath */
	public static final String SerialClickInput = "//*[@id='grn_lot_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[3]";
	/** @locatorType Xpath */
	public static final String PendingTable = "//div[@id='PEND_content']/div[6]/div/table/tbody/tr/td[1]";

	/** @locatorType Xpath */
	public static final String CheckBox(String PONumber) {
		return "//td[text()='PO100/00708/24-25']/../td[15]";
	}

	/** @locatorType Xpath */
	public static final String GrnNumberSelect = "//button[@id='P1615131103_GRN_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String SearchButton = "//*[text()='Search']/..";
	/** @locatorType Xpath */
	public static final String ExpiryDate = "//input[@id='C1885842449099241908_input']";

	/** @locatorType Xpath */
	public static final String GrnStatusTableText(String Grnno) {
		return "//a[text()='" + Grnno + "']/../../td[17]";
	}

	/** @locatorType Xpath */
	public static final String row = "//*[@id='PopupLov_161513110304_C1869923325925966449_dlg']/div[2]/div[2]/div[3]/table/tbody/tr[1]";
	/** @locatorType Xpath */
	public static final String LotSerialItemSearch = "//button[@class='a-Button a-PopupLOV-doSearch']";
	/** @locatorType Xpath */
	public static final String LotserialitemsInput = "//input[contains(@class,'a-PopupLOV-search apex-item-text')]";
	/** @locatorType Xpath */
	public static final String SerialInputDisabled = "//input[@id='C1885842032286241904']";
	/** @locatorType Xpath */
	public static final String LotInputDisabled = "//input[@id='C1885841721969241901']";
	/** @locatorType Xpath */
	public static final String lotserialHeaderclick = "//*[@id='wwvFlowForm']/div[1]/div[2]/div";
	/** @locatorType Xpath */
	public static final String LotSerialQtyField = "//*[@id='grn_lot_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[4]";
	/** @locatorType Xpath */
	public static final String CustomerInvoiceInputNumber = "//input[@class='a-PopupLOV-search apex-item-text']";
	/** @locatorType Xpath */
	public static final String SelectInvoiceNumber = "//li[@class='a-IconList-item']";
	/** @locatorType Xpath */
	public static final String GrnHeaderLocation = "//*[@id='P161513110303_PORH_PLNT_LOC_ID_DISPLAY']";
	/** @locatorType Xpath */
	public static final String GrnStockTransferCreation = "//*[@id='BTN_GRN_STS']";
	/** @locatorType Xpath */
	public static final String LotserialItemSelect = "//div[contains(@class,'PopupLOV-results')]//table/tbody/tr/td[1]/span";
	/** @locatorType Xpath */
	public static final String PendingStockTransfer = "//button[@data-testid='Pending_ST']";
	/** @locatorType Xpath */
	public static final String CustomerInvoiceNumberLov = "//button[@id='P1615131100_INV_NO_lov_btn']";

	/** @locatorType Xpath */
	public static final String grn_row(int i) {
		return "//*[contains(@id,'PopupLov')]/div[2]/div[2]/div[3]/table/tbody/tr[" + i + "]";
	}

	/** @locatorType Xpath */
	public static final String FindPageGrnNoLov = "//button[@id='P1615131103_GRN_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String GrnNumberInput = "//*[@id='PopupLov_1615131103_P1615131103_GRN_NO_dlg']/div[1]/input";
	/** @locatorType Xpath */
	public static String GrnSearchButton = "//button[@data-testid='Line']";
	/** @locatorType Xpath */
	public static String GrnProcessFlowStatus = "//*[@id='12242177025893385969']/tbody/tr[2]/td[16]/div";
	/** @locatorType Xpath */
	public static String GrnHeaderRadioBtn = "//label[contains(text(),'Header')]";
	/** @locatorType Xpath */
	public static String GrnChargesBtn = "//button[@data-testid='Charges']";
	/** @locatorType Xpath */
	public static String GrnChargesAddbtn = "//button[@data-testid='GRNP_ADD_CHARGES_BTN']";
	/** @locatorType Xpath */
	public static String GrnChargesItemInput = "//input[@data-testid='PROTC_PROD_ID']";
	/** @locatorType Xpath */
	public static String GrnCharges = "//*[@id='chrg_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[3]";
	/** @locatorType Xpath */
	public static String GrnChargesIFrame = "//iframe[@title='GRN Charges']";
	/** @locatorType Xpath */
	public static String GrnItemChargesLov = "//input[@data-testid='PROTC_PROD_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static String GrnChargesAmountValue = "//*[@id='chrg_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[7]";
	/** @locatorType Xpath */
	public static String GrnChargesAmountValueInput = "//input[@data-testid='PROTC_CHRG_AMT']";
	/** @locatorType Xpath */
	public static String GrnChargesGLAccount = "//*[@id='chrg_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[11]";
	/** @locatorType Xpath */
	public static String GrnChargesGLAccountLov = "//input[@data-testid='PROTC_PUR_ACCT']/following-sibling::button";
	/** @locatorType Xpath */
	public static String GrnChargesBasis = "//*[@id='chrg_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[2]";
	/** @locatorType Xpath */
	public static String GrnChargesSelect = "//select[@data-testid='PROTC_CHRG_BASIS']";
	/** @locatorType Xpath */
	public static String GrnChargesSaveBtn = "//button[@data-testid='GRNP_SAVE_CHARGES_BTN']";
	/** @locatorType Xpath */
	public static String GrnPerncentageField = "//*[@id='chrg_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[6]";
	/** @locatorType Xpath */
	public static String GrnPerncentageFieldInput = "//input[@data-testid='PROTC_CHRG_PCT']";
	/** @locatorType Xpath */
	public static String ToastAlertClosed = "//button[@title='Close']";
	/** @locatorType Xpath */
	public static String GrnIncludecharge = "//button[@data-testid='GRNP_INC_CHARGE_BTN']";
	/** @locatorType Xpath */
	public static String GrnChargesCloseBtn = "//button[@title='Close']";
	/** @locatorType Xpath */
	public static final String GrnAddBtn = "//*[@id='active']/span";
	/** @locatorType Xpath */
	public static final String GrnLocLov = "//*[@id='P1615131103_PORH_PLNT_LOC_ID_lov_btn']/span";
	/** @locatorType Xpath */
	public static final String GrnType = "//*[@id='P1615131103_PORH_TYPE_1']";
	/** @locatorType Xpath */
	public static final String GrnSupplierLov = "//*[@id='P1615131103_PORH_SUPLR_ID_lov_btn']/span";
	/** @locatorType Xpath */
	public static final String GrnSupplierBillNo = "//input[@id='P1615131103_PORH_SUPLR_DOC_N']";
	/** @locatorType Xpath */
	public static final String GrnDcNo = "//input[@id='P1615131103_PORH_DC_NO_1']";
	/** @locatorType Xpath */
	public static final String GrnCreate = "//*[@id='B1180480092076026456']";
	/** @locatorType Xpath */
	public static final String GrnLineAdd = "//*[@id='B3117851147407946274']";
	/** @locatorType Xpath */
	public static final String GrnLineItemLov = "//*[@id='C3725691278921531582_lov_btn']/span";
	/** @locatorType Xpath */
	public static final String GrnLineItemField = "//*[@id='add_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[3]";
	/** @locatorType Xpath */
	public static final String GrnLineHsnField = "//*[@id='add_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[7]";
	/** @locatorType Xpath */
	public static final String GrnLineHsnLov = "//*[@id='C3090171122980030596_lov_btn']/span";
	/** @locatorType Xpath */
	public static final String GrnLineSupQtyInput = "//input[@id='C3090137076748030305']";
	/** @locatorType Xpath */
	public static final String GrnLineSupQty = "//*[@id='add_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[8]";
	/** @locatorType Xpath */
	public static final String GrnLineRcpQty = "//*[@id='add_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[9]";
	/** @locatorType Xpath */
	public static final String GrnLineRcpQtyInput = "//input[@id='C3090171230897030597']";
	/** @locatorType Xpath */
	public static final String GrnLineCost = "//*[@id='add_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[13]";
	/** @locatorType Xpath */
	public static final String GrnLineCostInput = "//input[@id='cost']";
	/** @locatorType Xpath */
	public static final String GrnLineSave = "//*[@id='B3117851411103946277']";
	/** @locatorType Xpath */
	public static final String GrnLineNo = "//*[@id='add_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[1]";

	/** @locatorType Xpath */
	public static final String GrnLineNo(int i) {
		return "//*[@id='ig_po_lineitems_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[22]";
	}

	/** @locatorType Xpath */
	public static final String GrnLineNos(int i) {
		return "//*[@id='add_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[1]";
	}

	/** @locatorType Xpath */
	public static final String GrnAcceptQtyForGrid(int i) {
		return "//*[@id='add_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[10]";
	}

	/** @locatorType Xpath */
	public static final String GrnAcceptQty = "//input[@id='ACPT_QTY']";
	/** @locatorType Xpath */
	public static final String GrnLotAcceptQtyGrid = "//*[@id='grn_lot_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[10]";
	/** @locatorType Xpath */
	public static final String GrnLotAcceptQty = "//*[@id='qty']";
	/** @locatorType Xpath */
	public static final String GrnLinewiseSave = "//*[@id='B3117851411103946277']";
	/** @locatorType Xpath */
	public static final String GrnLotLinewisesave = "//*[@id='B3151453560144901368']";
	/** @locatorType Xpath */
	public static final String GrnLotLinewiseHeatNumberInput = "//input[@id='C3133645641001377504']";
	/** @locatorType Xpath */
	public static final String GrnLotLinewiseHeatNumberGrid = "//*[@id='grn_lot_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[6]";
	/** @locatorType Xpath */
	public static final String GrnLotLinewiseTestNumberInput = "//input[@id='C3133645521330377503']";
	/** @locatorType Xpath */
	public static final String GrnLotLinewiseTestNumberGrid = "//*[@id='grn_lot_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[7]";
	/** @locatorType Xpath */
	public static final String GrnLandedCostBtn = "//button[@data-testid='GRNP_LANDEDCOST_TAB']";
	/** @locatorType Xpath */
	public static final String GrnLandedCostFrame = "//iframe[@title='Landed Cost']";
	/** @locatorType Xpath */
	public static final String GrnLandedCostItemAddbtn = "//button[@data-testid='Add_LC']";
	/** @locatorType Xpath */
	public static final String GrnLandedCostItemLov = "//input[@data-testid='PRLC_TC_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String GrnLandedCostSuppliergrid = "//*[@id='grn_lc_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[5]";
	/** @locatorType Xpath */
	public static final String GrnLandedCostSupplierLov = "//button[@id='LC_SUPLR_lov_btn']";
	/** @locatorType Xpath */
	public static final String GrnLandedCostAmountGrid = "//*[@id='grn_lc_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[10]";
	/** @locatorType Xpath */
	public static final String GrnLandedCostAmountValue = "//input[@data-testid='PRLC_TC_AMT']";
	/** @locatorType Xpath */
	public static final String GrnLandedCostDocumentGrid = "//*[@id='grn_lc_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[11]";
	/** @locatorType Xpath */
	public static final String GrnLandedCostDocumentNumberField = "//input[@id='LC_DOC_NO']";
	/** @locatorType Xpath */
	public static final String GrnLandedCostDateField = "//input[@id='LC_DOC_DATE_input']";
	/** @locatorType Xpath */
	public static final String GrnLandedCostAIcGrid = "//*[@id='grn_lc_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[13]";
	/** @locatorType Xpath */
	public static final String GrnLandedCostSelectAic = "//select[@data-testid='PRLC_CHRG_FLAG']";
	/** @locatorType Xpath */
	public static final String GrnLandedCostLineLevelSave = "//button[@data-testid='Save_LC']";
	/** @locatorType Xpath */
	public static final String GrnLandedCostDateGrid = "//*[@id='grn_lc_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[12]";
	/** @locatorType Xpath */
	public static final String GrnLandedCostTodayDate = "//*[@id='LC_DOC_DATE_dialog']/div/div[3]/div/button";
	/** @locatorType Xpath */
	public static final String PendingPOItemWiseSearch = "//button[@id='P1615131100_PROD_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String PendingPoExcessQty = "//input[@type='number']";
	/** @locatorType Xpath */
	public static final String GrnLineWiseDeleteButton = "//a[text()='Cancel']";
	// span[@title="Delete"]
	/** @locatorType Xpath */
	public static final String GrnLineWiseStatus = "//table[@id='8497051965135905024_orig']/tbody[1]/tr[2]/td[92]";
	/** @locatorType Xpath */
	public static final String GrnDocumentwiseCancelButton = "//button[@data-testid='Cancel_GRN']";
	/** @locatorType Xpath */
	public static final String GrnDocumentwiseCancelReasonLov = "//button[@id='P161513110303_PORH_CANCEL_REAS_lov_btn']";
	/** @locatorType Xpath */
	public static final String GrnDocumentCancelledReasonOKButton = "//button[@data-testid='Cancel_Reason']";
	/** @locatorType Xpath */
	public static final String ImportLR = "//button[@id='B133454763451225428']";
	/** @locatorType Xpath */
	public static final String BillOfEntryNumber = "//input[@id='P161513110303_PID_BOE_NO']";
	/** @locatorType Xpath */
	public static final String BillOfDate = "//input[@id='P161513110303_PID_BOE_DATE_input']";
	/** @locatorType Xpath */
	public static final String ImportLROk = "//button[@id='B133455060464225431']";
	/** @locatorType Xpath */
	public static final String PendingGE = "//button[@data-testid='GRNP_PEND_GE']/span[text()='Pending Gate Entry']/..";
	/** @locatorType Xpath */
	public static final String FindGELovButton = "//input[@name='P1615131100_GE_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String OkButton = "//h1[text()='GRN Date']/../..//span[text()='OK']/parent::button";
	/** @locatorType Xpath */
	public static final String CreateGeButton = "//button[@id='BTN_GE']";
	/** @locatorType Xpath */
	public static final String GRN_LotSerialButton = "//button[@data-testid='GRNP_GEN_LOT_SER_BTN']";
	/** @locatorType Xpath */
	public static final String GstSupplySelect = "//select[@data-testid='PRLC_GST_EXEMPT_TYPE']";
	/** @locatorType Xpath */
	public static final String GrnLandedGstSupplyergrid = "//*[@id=\"grn_lc_ig_grid_vc\"]/div[3]/div[3]/table/tbody/tr/td[16]";
	/** @locatorType Xpath */
	public static final String ImportLrButton = "//span[text()='Import/LR']/..";
	/** @locatorType Xpath */
	public static final String BillNumber = "//input[contains(@id,'PID_BOE_NO')]";
	/** @locatorType Xpath */
	public static final String BillDate = "//input[contains(@id,'PID_BOE_DATE')]";
	/** @locatorType Xpath */
	public static final String BillOkButton = "//button[@data-testid='OK_import_LR']";
	/** @locatorType Xpath */
	public static final String ItemTable = "//div[@aria-label='Lot/Serial ']/div[3]/div[3]/table/tbody/tr/td[1]";
	/** @locatorType Xpath */
	public static final String AddLineButton = "//span[text()='Add Line']/..";
	/** @locatorType Xpath */
	public static final String CreateGeOneButton = "//button[@id='BTN_GE1']";
	/** @locatorType Xpath */
	public static final String GrnSecondItem = "//div[@id='add_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[2]/td[3]";
	/** @locatorType Xpath */
	public static final String PoInputField = "//input[@id='P1615131100_PO_NO']";
	/** @locatorType Xpath */
	public static final String RevGrnButton = "//span[text()='Rev. GRN']/..";
	/** @locatorType Xpath */
	public static final String OkayButton = "//span[text()='OK']/..";
	/** @locatorType Xpath */
	public static final String ReverseIframe = "//iframe[@title='Doc. Reversal']";
	/** @locatorType Xpath */
	public static final String ForawardPerson = "//button[@id='P236131090_FWD_PERSON_lov_btn']";
	/** @locatorType Xpath */
	public static final String OkButtonEntry = "//button[contains(@onclick,'Entry')]";
	/** @locatorType Xpath */
	public static final String WfLog = "//span[text()='WF Log']";
	/** @locatorType Xpath */
	public static final String LotSerialTable = "//div[@id='grn_lot_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[1]";
	/** @locatorType Xpath */
	public static final String LotSerialRecptQtyTable = "//div[@id='grn_lot_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[7]";
	/** @locatorType Xpath */
	public static final String ReceptInput = LotSerialRecptQtyTable + "//input";
	/** @locatorType Xpath */
	public static final String LineSaveButton = "(//button[@title='Save'])[3]";

	/** @locatorType Xpath */
	public static final String GrnDocNo(String DocNo) {
		return "//a[text()='" + DocNo + "']";
	}

	/** @locatorType Xpath */
	public static final String AcceptQtyTable = "//div[@id='add_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[10]";
	/** @locatorType Xpath */
	public static final String AcceptQtyInput = "//input[@id='ACPT_QTY']";
	/** @locatorType Xpath */
	public static final String AcceptQuantityInput = "//div[@id='grn_lot_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[9]//input";
	/** @locatorType Xpath */
	public static final String AcceptQuantity = "//div[@id='grn_lot_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[9]";
	/** @locatorType Xpath */
	public static final String GrnDocumentStatus = "//table[@data-testid='L_Result_Data']/tbody/tr/td[18]";
	/** @locatorType Xpath */
	public static final String CancelAlertForLineWise = "//button[contains(text(),'OK')]";
	/** @locatorType Xpath */
	public static final String LineWiseRadioBtn = "(//input[@value=\"L\"])[7]";
	/** @locatorType Xpath */
	public static final String PendingGEShowMyRecordBtn = "//button[@id='ge_sel']";
	/** @locatorType Xpath */
	public static final String PendingGEShowMyRecordHideBtn = "//button[@id='ge_unsel']";

	/** @locatorType Xpath */
	public static final String GRNStatusTableText(String GRNNo) {
		return "//a[text()='" + GRNNo + "']/../../td/div[contains(@style,'color:brown')]";
	}

	/** @locatorType Xpath */
	public static final String DetailsCancel = "//div[@id='add_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[1]";
	
	public static final String LotSerialAcceptQtyTable = "//div[@id='grn_lot_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[7]";
	
	/** @locatorType Xpath */
	public static final String AcceptInput = LotSerialAcceptQtyTable + "//input";
	/** @locatorType Xpath */
	public static final String PendingPOShowMyRecordBtn = "//button[@id='po_sel']";
	/** @locatorType Xpath */
	public static final String PendingPOShowMyRecordHideBtn = "//button[@id='po_unsel']";
	/** @locatorType Xpath */
	public static final String PendingPoStShowmyRecordBtn = "//button[@id='sst_sel']";
	/** @locatorType Xpath */
	public static final String PendingPoStHidemyRecordBtn = "//button[@id='sst_unsel']";
}
