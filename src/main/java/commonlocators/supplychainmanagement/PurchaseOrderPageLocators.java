package commonlocators.supplychainmanagement;

import commonfunctions.CommonFunctions;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
public class PurchaseOrderPageLocators {
	/** @locatorType Xpath */
	public static final String AddButtonPurchaseOrder = "//button[@data-testid='Add']";
	/** @locatorType Xpath */
	public static final String PurchaseOrderheader = "//h1[@id='ui-id-2']";
	/** @locatorType Xpath */
	public static final String LocationIdInput = "//input[contains(@data-testid,'POH_PLNT_LOC_ID')]";
	/** @locatorType Xpath */
	public static final String UnitIdInput = "//input[@id='P1615131061_POH_PLANT']";
	/** @locatorType Xpath */
	public static final String PoTypeSelect = "//select[@data-testid='P161513106101_POH_TYPE_1']";
	/** @locatorType Xpath */
	public static final String PoPfxInput = "//input[contains(@data-testid,'POH_ORDER_PFX_2')]";
	/** @locatorType Xpath */
	public static final String PoPfxLovBtn = "//input[contains(@data-testid,'POH_ORDER_PFX_2')]/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PoPfxSelect = "//select[@data-testid='P161513106101_POH_ORDER_PFX_2']";
	/** @locatorType Xpath */
	public static final String PoDate = "//*[@id='P1615131061_POH_ORDER_DATE_input']";
	/** @locatorType Xpath */
	public static final String SupplierName = "//input[contains(@data-testid,'POH_SUPLR_ID')]";
	/** @locatorType Xpath */
	public static final String Buyer = "//*[@id='P1615131061_POH_BUYER_ID']";
	/** @locatorType Xpath */
	public static final String LocationIdButton = "//input[@data-testid='P161513106101_POH_PLNT_LOC_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String LocationInputField = "//input[@data-testid='P161513106101_POH_PLNT_LOC_ID']";
	/** @locatorType Xpath */
	public static final String SupplierNameButton = "//input[@data-testid='P1615131061_POH_SUPLR_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String searchBar = "//input[@aria-label='Search']";
	/** @locatorType Xpath */
	public static final String SupplierheaderBar = "//*[@id='ui-id-5']";
	/** @locatorType Xpath */
	public static final String LocationheaderBar = "//*[@id='ui-id-4']";
	/** @locatorType Xpath */
	public static final String DPOTickButton = "//button[@data-testid='Create_PO_Form']";
	/** @locatorType Xpath */
	public static final String ItemAddButton = "//button[@data-testid='PO_Item_Add_Button']";

	/** @locatorType Xpath */
	public static final String AddTablePOScreen = "(//table[@data-testid='Reset_Region_Result_Data'])[2]";

	/** @locatorType Xpath */
	public static final String POPageItemTable() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddTablePOScreen, "Item ID");
	}

	/** @locatorType Xpath */
	public static final String ItemSelectButton = "//input[@data-testid='DUM_PROD_ID_REV']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String InputItemPO = "//input[@data-testid='DUM_PROD_ID_REV']";

	/** @locatorType Xpath */
	public static final String OrderedQuantity() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddTablePOScreen, "Ord. Qty.");
	}

	/** @locatorType Xpath */
	public static final String OrderedQuantityInput = "//input[@data-testid='POL_ORDERED_QTY']";

	/** @locatorType Xpath */
	public static final String UnitCost() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddTablePOScreen, "UnitCost");
	}

	/** @locatorType Xpath */
	public static final String UnitCostInput = "//input[@data-testid='POL_SC_UNIT_COST']";
	/** @locatorType Xpath */
	public static final String TickIconPO = "//button[@data-testid='PO_Item_Tick_Icon']";
	/** @locatorType Xpath */
	public static final String HeaderTickIconPO = "//button[@data-testid='SAVE']";
	/** @locatorType Xpath */
	public static final String HeaderTPostIcon = "//button[@data-testid='Post']";
	/** @locatorType Xpath */
	public static final String ForwardApproval = "//*[@id='P236131090_FWD_PERSON_lov_btn']";
	/** @locatorType Xpath */
	public static final String OkButtonForward = "//button[@data-testid='Entry']";
	/** @locatorType Xpath */
	public static final String PONumber = "//input[@id='P161513106041_POH_ORDER_NO']";
	/** @locatorType Xpath */
	public static final String CloseToastIcon = "//div[@class='t-Alert-buttons']/button[@title='Close']";
	/** @locatorType Xpath */
	public static final String WorkApprovalFrame = "//iframe[@title='Workflow Approval']";
	/** @locatorType Xpath */
	public static final String SearchButton = "//span[text()='Search']/../../button[1]";
	/** @locatorType Xpath */
	public static final String PONumberSelect = "//input[@data-testid='P1615132060_PO_SSO_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String CancelButton = "//button[@data-testid='Cancel']";

	/** @locatorType Xpath */
	public static final String DeleteLineItemPO(String ItemName) {
		return "//td[text()='" + ItemName + "']/../td/a/span[contains(@style,'tomato')]";
	}

	/** @locatorType Xpath */
	public static final String POStatusTableText(String POno) {
		return "(//a[text()='" + POno + "']/../../td/div[contains(@style,'color')])[1]";
	}

	/** @locatorType Xpath */
	public static final String PendingRFQBtn = "//button[@id='B1022828162906840804']";
	/** @locatorType Xpath */
	public static final String PendingPRBtn = "(//button[@data-testid='Pend_PR'])[1]";
	/** @locatorType Xpath */
	public static final String PendingPRiframe = "//iframe[@title='Pending PR']";
	/** @locatorType Xpath */
	public static final String PendingPR_FindPRNo = "//button[contains(@id,'PR_NO')]";
	/** @locatorType Xpath */
	public static final String PendingPR_SearchBtn = "//button[@data-testid='Search']";
	/** @locatorType Xpath */
	public static final String PendingPR_SelectAllCheckBox = "(//input[@id='check_all'])";
	/** @locatorType Xpath */
	public static final String PendingPR_CreatePO_Btn = "//button[@id='B1']";
	/** @locatorType Xpath */
	public static final String PendingPR_SupplierSelectBtn = "//button[@id='P16151310407_SUPPLIER_lov_btn']";
	/** @locatorType Xpath */
	public static final String PendingPR_CurrencySelectBtn = "//button[@id='P16151310407_CURRENCY_lov_btn']";
	/** @locatorType Xpath */
	public static final String PendingPR_OkBtn = "//button[@data-testid='OK']";

	/** @locatorType Xpath */
	public static final String LineAmount(String text) {
		return "//td[text()='" + text + "']/../td[9]";
	}

	/** @locatorType Xpath */
	public static final String TaxPercentage(String text) {
		return "//td[text()='" + text + "']/../td[11]";
	}

	/** @locatorType Xpath */
	public static final String TaxCGST(String text) {
		return "//td[text()='" + text + "']/../td[13]";
	}

	/** @locatorType Xpath */
	public static final String TaxSGST(String text) {
		return "//td[text()='" + text + "']/../td[14]";
	}

	/** @locatorType Xpath */
	public static final String POTotalAmount = "//input[@id='P161513106041_POH_TOTAL_AMT']";
	/** @locatorType Xpath */
	public static final String ItemChargesTab = "//label[@for='P161513106041_TAB_LINE_1']";
	/** @locatorType Xpath */
	public static final String AddItemCharges = "//button[@data-testid='Adds']";
	/** @locatorType Xpath */
	public static final String ItemChargesTable = "(//div[@aria-label='Charge Items']//table)[2]";

	/** @locatorType Xpath */
	public static final String ChargeItemTable() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ItemChargesTable, "Charge Item")
				+ ")[1]";
	}

	/** @locatorType Xpath */
	public static final String ChargeItemButton = "(//input[@data-testid='CHARGE_ITEM']/following-sibling::button)[1]";

	/** @locatorType Xpath */
	public static final String ChargeItemInput = "(//input[@data-testid='CHARGE_ITEM'])[1]";

	/** @locatorType Xpath */
	public static final String ChargePercentage() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ItemChargesTable, "%") + ")[1]";
	}

	/** @locatorType Xpath */
	public static final String ChargePercentageInput = "(//input[@data-testid='POTC_CHRG_PCT1'])[1]";

	/** @locatorType Xpath */
	public static final String ChargesTickIcon = "//button[@data-testid='Saves']";
	/** @locatorType Xpath */
	public static final String IncludeCharges = "//button[@data-testid='Include_Charge']";
	/** @locatorType Xpath */
	public static final String LineTab = "//label[@for='P161513106041_TAB_LINE_0']";
	/** @locatorType Xpath */
	public static final String LineAmountWE = "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr/td[9]";

	/** @locatorType Xpath */
	public static final String GLAccDesc() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ItemChargesTable, "GL Acct. Desc.")
				+ ")[1]";
	}

	/** @locatorType Xpath */
	public static final String GlAccDescButton = "(//input[@data-testid='CHARGE_PUR_ACT']/following-sibling::button)[1]";

	/** @locatorType Xpath */
	public static final String SACTableData() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ItemChargesTable, "SAC Code")
				+ ")[1]";
	}

	/** @locatorType Xpath */
	public static final String SACTableButton = "(//input[@data-testid='CHARGE_SAC_CODE']/following-sibling::button)[1]";

	/** @locatorType Xpath */
	public static final String LinesCustomMenuBtnThreeDots(int rowNumber) {
		return "(//table[@data-testid='Reset_Region_Result_Data']//tr/td[1]//span)[" + rowNumber + "]";
	}

	/** @locatorType Xpath */
	public static final String DetailsMenuThreeDots = "//ul[@class='custom-row-menu']//*[text()='Details']";
	/** @locatorType Xpath */
	public static final String CancelMenuThreeDots = "//ul[@class='custom-row-menu']//*[text()='Cancel']";
	/** @locatorType Xpath */
	public static final String LnDts_DiscountExpandButton = "//div[@aria-label='Discount Details']//button";

	/** @locatorType Xpath */
	public static final String ItemName(int i) {
		return "(//table[@data-testid='Reset_Region_Result_Data'])[2]/tbody/tr[" + i + "]/td[3]";
	}

	/** @locatorType Xpath */
	public static final String OrderedQty(int i) {
		return "(//table[@data-testid='Reset_Region_Result_Data'])[2]/tbody/tr[" + i + "]/td[6]";
	}

	/** @locatorType Xpath */
	public static final String UnitCostTable(int i) {
		return "(//table[@data-testid='Reset_Region_Result_Data'])[2]/tbody/tr[" + i + "]/td[7]";
	}

	/** @locatorType Xpath */
	public static final String UnitCostTable = CommonFunctions
			.checkAndReturnIndexForLocatorWithHeaderValue(AddTablePOScreen, "UnitCost");

	/** @locatorType Xpath */
	public static final String LineAmount(int i) {
		return "(//table[@data-testid='Reset_Region_Result_Data'])[2]/tbody/tr[" + i + "]/td[9]";
	}

	/** @locatorType Xpath */
	public static final String TaxAmount(int i, int j) {
		return "(//table[@data-testid='Reset_Region_Result_Data'])[2]/tbody/tr[" + i + "]/td[" + j + "]";
	}

	/** @locatorType Xpath */
	public static final String UOMTable = "(//table[@data-testid='Reset_Region_Result_Data'])[2]/tbody/tr/td[5]";
	/** @locatorType Xpath */
	public static final String PendingPRTable = "//table[contains(@aria-label,'PENDING')]/tbody/tr/td[1]";
	/** @locatorType Xpath */
	public static final String PoTypeHeader = "//span[@id='P161513106041_POH_TYPE_DISPLAY']";
	/** @locatorType Xpath */
	public static final String LocationHeaderId = "//span[@id='P161513106041_POH_PLNT_LOC_ID_DISPLAY']";
	/** @locatorType Xpath */
	public static final String SupplierHeaderName = "//input[@id='P161513106041_POH_SUPLR_NAME']";
	/** @locatorType Xpath */
	public static final String CurrencyHeader = "//input[@id='P161513106041_POH_CURRENCY_1']";
	/** @locatorType Xpath */
	public static final String POPrefixHeader = "//span[@id='P161513106041_POH_ORDER_PFX_DISPLAY']";

	/** @locatorType Xpath */
	public static final String ItemDescription(int i) {
		return "(//table[@data-testid='Reset_Region_Result_Data'])[2]/tbody/tr[" + i + "]/td[4]";
	}

	/** @locatorType Xpath */
	public static final String UOM(int i) {
		return "(//table[@data-testid='Reset_Region_Result_Data'])[2]/tbody/tr[" + i + "]/td[5]";
	}

	/** @locatorType Xpath */
	public static final String HSNSACCode(int i) {
		return "(//table[@data-testid='Reset_Region_Result_Data'])[2]/tbody/tr[" + i + "]/td[10]";
	}

	/** @locatorType Xpath */
	public static final String TaxPercent(int i) {
		return "(//table[@data-testid='Reset_Region_Result_Data'])[2]/tbody/tr[" + i + "]/td[11]";
	}

	/** @locatorType Xpath */
	public static final String LineDetailButton(int i) {
		return "(//table[@data-testid='Reset_Region_Result_Data'])[2]/tbody/tr[" + i + "]/td[1]";
	}

	/** @locatorType Xpath */
	public static final String LineDetailsHeader = "//h1[text()='Line Details']";
	/** @locatorType Xpath */
	public static final String SubClass = "//span[@id='P161513106045_POL_PROD_SUB_CLS_DISPLAY']";
	/** @locatorType Xpath */
	public static final String Class = "//input[@id='P161513106045_POL_PROD_CLS_DESC']";
	/** @locatorType Xpath */
	public static final String Wh_Dept_Name = "//input[@id='P161513106045_POL_STORE_NAME']";
	/** @locatorType Xpath */
	public static final String CostBasis = "//input[@id='P161513106045_POL_COST_BASIS']";
	/** @locatorType Xpath */
	public static final String StockUOM = "//input[@id='P161513106045_POL_PROD_UOM']";
	/** @locatorType Xpath */
	public static final String ConversionFactor = "//input[@id='P161513106045_POL_CONV_FACTOR']";
	/** @locatorType Xpath */
	public static final String Gl_Acc_No = "//input[@id='P161513106045_POL_PUR_ACCT']";
	/** @locatorType Xpath */
	public static final String Gl_Acc_Desc = "//input[@id='P161513106045_POL_PUR_ACCT_DESC']";
	/** @locatorType Xpath */
	public static final String CPC_Code = "//input[@id='P161513106045_POL_CC_CODE']";
	/** @locatorType Xpath */
	public static final String CPC_Desc = "//input[@id='P161513106045_POL_CC_CODE_DESC']";
	/** @locatorType Xpath */
	public static final String Supplier_Item = "//input[@id='P161513106045_POL_SUPLR_PROD_ID']";
	/** @locatorType Xpath */
	public static final String Supplier_Item_Desc = "//input[@id='P161513106045_POL_SUPLR_PROD_DESC']";
	/** @locatorType Xpath */
	public static final String LineDetailTwoButton = "//label[@for='P161513106045_TYPE_1']";
	/** @locatorType Xpath */
	public static final String FreeOfCostLabel = "//label[contains(@id,'POL_FOC_FLAG_LABEL')]";
	/** @locatorType Xpath */
	public static final String LnDts_ItemDetailsExpandButton = "//div[@aria-label='Item  Details']//button";
	/** @locatorType Xpath */
	public static final String FreeOfCostInput = "//input[contains(@id,'POL_FOC_FLAG_HIDDENVALUE')]";
	/** @locatorType Xpath */
	public static final String UnRegisterHeader = "//h2[text()='Unregister RCM Details']";
	/** @locatorType Xpath */
	public static final String CurrencyInput = "//input[@data-testid='P1615131061_POH_CURRENCY']";
	/** @locatorType Xpath */
	public static final String LineDetailsIframePO = "//iframe[@title='Line Details']";
	/** @locatorType Xpath */
	public static final String LineDetailsCloseIcons = "//h1[text()='Line Details']/following-sibling::button[@title='Close']";
	/** @locatorType Xpath */
	public static final String ContractId = "//*[@id='P161513106045_POL_CONTRACT_ID']";
	/** @locatorType Xpath */
	public static final String ContractPrefix = "//*[@id='P161513106045_POL_CONTR_PFX']";
	/** @locatorType Xpath */
	public static final String ContractAmendNumber = "//*[@id='P161513106045_POL_CONTR_AMD_NO']";
	/** @locatorType Xpath */
	public static final String POClsQuantity = "//*[@id='P161513106045_POL_CLS_QTY']";
	/** @locatorType Xpath */
	public static final String RCMCatId = "//div[@id='P161513106045_POL_RCM_CAT_ID_CONTAINER']/following::input[contains(@id,'P161513106045_POL_RCM_CAT_ID')]";
	/** @locatorType Xpath */
	public static final String TradePercentage = "//input[contains(@data-testid,'POL_TRD_DISC_PCT')]";
	/** @locatorType Xpath */
	public static final String TradePercentageField = "//*[@id=\"P161513106045_POL_TRD_DISC_PCT_CONTAINER\"]";
	/** @locatorType Xpath */
	public static final String POClassField = "//*[@id=\"P161513106045_POL_PROD_CLS_DESC\"]";

	/** @locatorType Xpath */
	public static final String TradeAssembleValue = "//input[@id='P161513106045_POL_TRD_ASSBL_VAL']";
	/** @locatorType Xpath */
	public static final String TradeAmount = "//input[@id='P161513106045_POL_TRD_DISC_AMT']";
	/** @locatorType Xpath */
	public static final String SpecialAssembleValue = "//input[@id='P161513106045_POL_SPL_ASSBL_VAL']";
	/** @locatorType Xpath */
	public static final String SpecialDiscountPercentage = "//input[@data-testid='POL_SPL_DISC_PCT']";
	/** @locatorType Xpath */
	public static final String SpecialDiscountAmount = "//input[@id='P161513106045_POL_SPL_DISC_AMT']";
	/** @locatorType Xpath */
	public static final String CashAssembleValue = "//input[@id='P161513106045_POL_CASH_ASSBL_VAL']";
	/** @locatorType Xpath */
	public static final String CashDiscountPercentage = "//input[@data-testid='POL_CASH_DISC_PCT']";
	/** @locatorType Xpath */
	public static final String CashDiscountAmount = "//input[@id='P161513106045_POL_CASH_DISC_AMT']";
	/** @locatorType Xpath */
	public static final String DiscAmountPerQty = "//input[@id='P161513106045_POL_DISC_UNIT_AMT']";
	/** @locatorType Xpath */
	public static final String GeneralTab = "//label[@for='P161513106041_TAB_LINE_2']";
	/** @locatorType Xpath */
	public static final String MatAmountLabel = "//label[@for='P161513106041_POH_MAT_AMT']";
	/** @locatorType Xpath */
	public static final String MatAmountInput = "//input[@id='P161513106041_POH_MAT_AMT']";
	/** @locatorType Xpath */
	public static final String TaxAmountInput = "//input[@id='P161513106041_POH_TAX_AMT']";
	/** @locatorType Xpath */
	public static final String DiscountAmountInput = "//input[@id='P161513106041_POH_DISC_AMT']";
	/** @locatorType Xpath */
	public static final String TotalAmountInput = "//input[@id='P161513106041_POH_TOT_AMT']";
	/** @locatorType Xpath */
	public static final String RoundOffInput = "//input[@id='P161513106041_POH_RND_AMT']";
	/** @locatorType Xpath */
	public static final String DiscountDetailHeader = "//h2[@id='R1467193047076536437_heading']";

	/** @locatorType Xpath */
	public static final String DiscountAmount(String item) {
		return "(//table[@data-testid='Reset_Region_Result_Data'])[2]/tbody/tr[" + item + "]/td[8]";
	}

	/** @locatorType Xpath */
	public static final String TickButtonLineOne = "//button[@data-testid='POL_DET_SAVE']";

	/** @locatorType Xpath */
	public static final String ServiceLineDetailButton(int i) {
		return "(//table[@data-testid='Reset_Region_Result_Data'])[2]/tbody/tr[" + i + "]/td[1]";
	}

	/** @locatorType Xpath */
	public static final String PoItemSelect = "//*[@id=\"PopupLov_161513106041_C3750778132967408970_dlg\"]/div[2]/div[2]/div[3]/table/tbody/tr/td[1]/span";
	/** @locatorType Xpath */
	public static final String POItemInput = "//*[@id=\"PopupLov_161513106041_C3750778132967408970_dlg\"]/div[1]/input";
	/** @locatorType Xpath */
	public static final String PoQtyInput = "//input[@data-testid='POL_ORDERED_QTY']";
	/** @locatorType Xpath */
	public static final String PoQtyFieldClick = "//*[@id=\"ig_po_lineitems_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr/td[6]";
	/** @locatorType Xpath */
	public static final String PoUnitCost = "//input[@id='cost']";
	/** @locatorType Xpath */
	public static final String PoUnitCostClick = "//*[@id=\"ig_po_lineitems_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr/td[7]";
	/** @locatorType Xpath */
	public static final String PolinewiseSave = "//button[@data-testid='PO_Item_Tick_Icon']";
	/** @locatorType Xpath */
	public static final String PoServiceWithGrn = "//*[@id='ig_po_lineitems_ig_grid_vc']//table/tbody/tr/td[33]";
	/** @locatorType Xpath */
	public static final String PoseriviceWithGrnSelectedValues = "//select[@data-testid='GRN_RQRD_FLAG']";
	/** @locatorType Xpath */
	public static final String OkButtonFwdForQcDocument = "//button[@data-testid='Entry']";
	/** @locatorType Xpath */
	public static final String SaleOrderDrillDown = "//table[@data-testid='H_Result_Data']/tbody/tr[2]/td[10]";

	/** @locatorType Xpath */
	public static final String CsQty(int i) {
		return "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr[" + i + "]/td[7]";
	}

	/** @locatorType Xpath */
	public static final String UomWithItem(String item) {
		return "//td[text()='" + item + "']/../td[5]";
	}

	/** @locatorType Xpath */
	public static final String ServiceType = "//select[@id='P161513106045_POL_SERV_IO_TYPE']";
	/** @locatorType Xpath */
	public static final String TradePercentageValue = "//span[@id='P161513106045_POL_TRD_DISC_PCT_DISPLAY']";
	/** @locatorType Xpath */
	public static final String ContractPrice = "//div[contains(text(),'Contract price not found.')]";
	/** @locatorType Xpath */
	public static final String ContractPriceOkButton = "//button[text()='OK']";
	/** @locatorType Xpath */
	public static final String LocId = "//input[@data-testid='P1615132060_LOCATION']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String Unit = "//input[@data-testid='P1615132060_UNIT']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PrNo = "//button[@id='P1615131061_PR_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String PONo = "//input[@data-testid='P1615132060_PO_SSO_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String ClassLovButton = "//button[@id='P1615131061_CLASS_lov_btn']";
	/** @locatorType Xpath */
	public static final String POTypeSelect = "//select[@id='P1615131061_DOC_TYPE']";
	/** @locatorType Xpath */
	public static final String SupplierNameLovButton = "//input[@data-testid='P1615132060_SUPLR_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SubClassLovButton = "//button[@id='P1615131061_SUB_CLASS_lov_btn']";
	/** @locatorType Xpath */
	public static final String PoDtFrom = "//input[@id='P1615131061_DATE_FRM_input']";
	/** @locatorType Xpath */
	public static final String PoDtTo = "//input[@id='P1615131061_DATE_TO_1_input']";
	/** @locatorType Xpath */
	public static final String BuyerLovButton = "//button[@id='P1615131061_BUYER_lov_btn']";
	/** @locatorType Xpath */
	public static final String ItemId = "//input[@data-testid='P1615132060_ITEM_1']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String ItemDesc = "//input[@data-testid='P1615132060_ITEM_DESC']";
	/** @locatorType Xpath */
	public static final String RequestDtFrom = "//input[@id='P1615131061_REQ_DATE_FM_input']";
	/** @locatorType Xpath */
	public static final String RequestDtTo = "//input[@id='P1615131061_REQ_DATE_TO_input']";
	/** @locatorType Xpath */
	public static final String PO_Status = "//select[@data-testid='P1615132060_STATUS']";
	/** @locatorType Xpath */
	public static final String AmendFindTable = "//table[contains(@id,'orig')]/tbody/tr[2]/td[1]";
	/** @locatorType Xpath */
	public static final String AmendLineFindTable = "//table[@class='a-IRR-table' and contains(@id,'orig')]/tbody/tr[2]/td[3]";

	/** @locatorType Xpath */
	public static final String AmendLineFindTableData(String i) {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(PoLineFindTable, i);
	}

	/** @locatorType Xpath */
	public static final String PoFindTable = "//table[@data-testid='H_Result_Data']/tbody/tr/th";
	/** @locatorType Xpath */
	public static final String PoLineFindTable = "//table[@data-testid='L_Result_Data']/tbody/tr/th";

	/** @locatorType Xpath */
	public static final String AmendFindTableData(String i) {
		return CommonFunctions.checkAndReturnLocatorForFindPageTable(PoFindTable, i);
	}

	/** @locatorType Xpath */
	public static final String HsnCode() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddTablePOScreen, "HSN /SAC Code");
	}

	/** @locatorType Xpath */
	public static final String HsnCodeLovButton = "//input[@data-testid='POL_HSN_CODE']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String HsnCodeInput = "//input[@data-testid='POL_HSN_CODE']";
	/** @locatorType Xpath */
	public static final String GlAccountNoLovButton = "//input[contains(@id,'POL_PUR_ACCT')]/following-sibling::button";

	/** @locatorType Xpath */
	public static final String HsnCodeTableWithItem(String item) {
		return "//table[.//td[normalize-space()='" + item + "']]//tr[td[normalize-space()='" + item
				+ "']]/td[count(//table[.//td[normalize-space()='" + item
				+ "']]//th[normalize-space()='HSN /SAC Code']/preceding-sibling::th) + 1]";
	}

	/** @locatorType Xpath */
	public static final String ServiceItemButton = "//button[contains(@id,'POL_SERV_PROD_ID_lov_btn')]";
	/** @locatorType Xpath */
	public static final String PurchaseOrderLineDetails = "//*[@id='ig_po_lineitems_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[19]";
	/** @locatorType Xpath */
	public static final String PurchaseOrderLineDetailsBtn = "//*[@id=\"ig_po_lineitems_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr/td[19]/a/span";
	/** @locatorType Xpath */
	public static final String POType = "//*[@id=\"P161513106041_POH_TYPE_DISPLAY\"]";
	/** @locatorType Xpath */
	public static final String PurchaseOrderLineDetailsForServiceItem = "//*[@id='ig_po_lineitems_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[20]";

	/** @locatorType Xpath */
	public static final String PurchaseOrderLineDetailForServiceItem(int i) {
		return "//*[@id='ig_po_lineitems_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[1]";
	}

	/** @locatorType Xpath */
	public static final String PurchaseOrderLineDetail(int i) {
		return "//*[@id='ig_po_lineitems_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[1]";
	}

	/** @locatorType Xpath */
	public static final String POSubClass = "//span[@id='P161513106045_POL_PROD_SUB_CLS_DISPLAY']";
	/** @locatorType Xpath */
	public static final String LineDeatilsSave = "//button[@data-testid='POL_DET_SAVE']";

	/** @locatorType Xpath */
	public static final String PrefixLovButton = "//button[@id='P16151310407_PFX_lov_btn']";
	/** @locatorType Xpath */
	public static final String locationDetails = "//span[@class='fa fa-indent']";
	/** @locatorType Xpath */
	public static final String NextPageButton = "//button[@class=\"a-GV-pageButton a-GV-pageButton--nav a-Button js-pg-next\"]";
	/** @locatorType Xpath */
	public static final String PurchaseOrderAddButtonframe = "//iframe[contains(@title,'Create')]";
	/** @locatorType Xpath */
	public static final String LineLabel = "//input[@data-display='Line(s)']/following-sibling::label";
	/** @locatorType Xpath */
	public static final String PurchaseOrderItemLov = "//input[@data-testid='DUM_PROD_ID_REV']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PurchaseOrderHsnLov = "//input[@data-testid='POL_HSN_CODE']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PurchaseOrderHsnInput = "//input[@data-testid='POL_HSN_CODE']";
	/** @locatorType Xpath */
	public static final String PurchaseOrderHsnField = "//*[@id='ig_po_lineitems_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[12]";
	/** @locatorType Xpath */
	public static final String PendingGE_SelectAllCheckBox = "(//input[@id='check_allge'])";
	/** @locatorType Xpath */
	public static final String DocAprovalIfrmae = "//iframe[@title='Workflow Approval']";
	/** @locatorType Xpath */
	public static final String GE_SelectAllCheckBox = "//input[@id='check_allge']";
	/** @locatorType Xpath */
	public static final String ForwardEmployeefield = "//input[@data-testid='P236131090_FWD_PERSON']";
	/** @locatorType Xpath */
	public static final String SelectFirstCheckBox = "((//table[contains(@aria-label,'GATEENTRY')])[2]/tbody/tr/td/input[@type='checkbox'])[1]";
	/** @locatorType Xpath */
	public static final String SelectFirstItem = "((//table[contains(@aria-label,'GATEENTRY')])[2]/tbody/tr/td)[9]";

	/** @locatorType Xpath */
	public static final String POpaymentTerms = "//select[@data-testid='P161513106041_POH_TERM_ID']";
	/** @locatorType Xpath */
	public static final String SelectCheckboxKow = "//*[@id=\"15090192687695006742\"]/tbody/tr[2]/td[15]";
	/** @locatorType Xpath */
	public static final String MultiCloseShortCheckBoxInputField = "//table[@id='15090192687695006742']/tbody/tr/td[15]/input";

	/** @locatorType Xpath */
	public static final String selectCheckBoxInMultiCloseShort(String PONumber, int i) {
		return "(//td[contains(text(),'" + PONumber + "')]/../td[15]/input)[" + i + "]";

	}

	/** @locatorType Xpath */
	public static final String SelectCheckboxAgainstPO(int i) {
		return "//*[@id='15090192687695006742']/tbody/tr[" + i + "]/td[15]";
	}

	/** @locatorType Xpath */
	public static final String GRNPendingPOSelectCheckBox(String VoucherNumber) {
		return "//td[contains(text(),'" + VoucherNumber + "')]/../td[15]/*";
	}

	/** @locatorType Xpath */
	public static final String OpenPODocs(String POno) {
		return "(//a[text()='" + POno + "'])";
	}

	/** @locatorType Xpath */
	public static final String MoqText = "//ul/li[contains(text(),'MOQ Qty.')]";
	/** @locatorType Xpath */
	public static final String CancelText = "//button[text()='Cancel']";
	/** @locatorType Xpath */
	public static final String ServiceSelect = "//select[@data-testid='P161513106045_POL_SERV_IO_TYPE']";
	/** @locatorType Xpath */
	public static final String PendingPRShowMyRecordBtn = "//button[@data-testid='Show']";
	/** @locatorType Xpath */
	public static final String PendingPRShowMyRecordHideBtn = "//button[@data-testid='Hide']";
	/** @locatorType Xpath */
	public static final String DetailsButton = "//a[@data-action='LN_DTLS']";
	/** @locatorType Xpath */
	public static final String DiscountButton = "//div[@data-testid='Discount Details']//button";
	/** @locatorType Xpath */
	public static final String ContractExpandButton = "//h2[text()='PR Details / Open Order/ RFQ']/../..//button";

	/** @locatorType Xpath */
	public static final String HsnField() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddTablePOScreen, "HSN /SAC Code");
	}

	/** @locatorType Xpath */
	public static final String GrnRequired() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddTablePOScreen, "GRN Rqrd.");
	}

	/** @locatorType Xpath */
	public static final String PoDetails = "//a[@data-action='LN_DTLS']";
	/** @locatorType Xpath */
	public static final String DiscountExpand = "(//div[@data-testid='Discount Details']//button)[1]";
	/** @locatorType Xpath */
	public static final String ExpandServiceButton = "(//div[@aria-label='Service / Maintenance']//button)[1]";

}
