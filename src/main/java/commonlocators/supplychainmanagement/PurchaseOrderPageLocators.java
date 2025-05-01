package commonlocators.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
public class PurchaseOrderPageLocators {

	/** @locatorType Xpath */
	public static final String AddButtonPurchaseOrder = "//button[@id='B162751031089221705']";
	/** @locatorType Xpath */
	public static final String PurchaseOrderheader = "//h1[@id='ui-id-2']";
	/** @locatorType Xpath */
	public static final String LocationIdInput = "//input[@id='P1615131061_POH_PLNT_LOC_ID']";
	/** @locatorType Xpath */
	public static final String UnitIdInput = "//input[@id='P1615131061_POH_PLANT']";
	/** @locatorType Xpath */
	public static final String PoTypeSelect = "//*[@id='P1615131061_POH_TYPE_1']";
	/** @locatorType Xpath */
	public static final String PoPfxSelect = "//*[@id='P1615131061_POH_ORDER_PFX_1']";
	/** @locatorType Xpath */
	public static final String PoDate = "//*[@id='P1615131061_POH_ORDER_DATE_input']";
	/** @locatorType Xpath */
	public static final String SupplierName = "//*[@id='P1615131061_POH_SUPLR_ID']";
	/** @locatorType Xpath */
	public static final String Buyer = "//*[@id='P1615131061_POH_BUYER_ID']";
	/** @locatorType Xpath */
	public static final String LocationIdButton = "//*[@id='P1615131061_POH_PLNT_LOC_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String SupplierNameButton = "//button[@id='P1615131061_POH_SUPLR_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String searchBar = "//input[@aria-label='Search']";
	/** @locatorType Xpath */
	public static final String SupplierheaderBar = "//*[@id='ui-id-5']";
	/** @locatorType Xpath */
	public static final String LocationheaderBar = "//*[@id='ui-id-4']";
	/** @locatorType Xpath */
	public static final String DPOTickButton = "//button[@id='B1183449865401852803']";
	/** @locatorType Xpath */
	public static final String ItemAddButton = "//button[@id='B3059239047971031990']";
	/** @locatorType Xpath */
	public static final String POPageItemTable = "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr/td[2]";
	/** @locatorType Xpath */
	public static final String ItemSelectButton = "//*[@id='C3750778132967408970_lov_btn']";
	/** @locatorType Xpath */
	public static final String InputItemPO = "//input[@id='C3750778132967408970']";
	/** @locatorType Xpath */
	public static final String OrderedQuantity = "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr/td[6]";
	/** @locatorType Xpath */
	public static final String OrderedQuantityInput = "//*[@id='C3135022162127842865']";
	/** @locatorType Xpath */
	public static final String UnitCost = "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr/td[7]";
	/** @locatorType Xpath */
	public static final String UnitCostInput = "//*[@id='cost']";
	/** @locatorType Xpath */
	public static final String TickIconPO = "//*[@id='B3146205337803717780']";
	/** @locatorType Xpath */
	public static final String HeaderTickIconPO = "//*[@id='B3052796012707209524']";
	/** @locatorType Xpath */
	public static final String HeaderTPostIcon = "//*[@id='B3159242797041273061']";
	/** @locatorType Xpath */
	public static final String ForwardApproval = "//*[@id='P236131090_FWD_PERSON_lov_btn']";
	/** @locatorType Xpath */
	public static final String OkButtonForward = "//*[@id='B1612675370193662596']";
	/** @locatorType Xpath */
	public static final String PONumber = "//*[@id='P161513106041_POH_ORDER_NO']";
	/** @locatorType Xpath */
	public static final String CloseToastIcon = "//div[@class='t-Alert-buttons']/button[@title='Close']";
	/** @locatorType Xpath */
	public static final String WorkApprovalFrame = "//iframe[@title='Workflow Approval']";
	/** @locatorType Xpath */
	public static final String SearchButton = "//button[@id='B162750960319221704']";
	/** @locatorType Xpath */
	public static final String PONumberSelect = "//button[@id='P1615131061_DOC_NO_lov_btn']/span";
	/** @locatorType Xpath */
	public static final String CancelButton = "//button[@id='B3159242843298273062']";

	/** @locatorType Xpath */
	public static final String DeleteLineItemPO(String ItemName) {
		return "//td[text()='" + ItemName + "']/../td/a/span[contains(@style,'tomato')]";
	}

	/** @locatorType Xpath */
	public static final String POStatusTableText(String POno) {
		return "//a[text()='" + POno + "']/../../td[last()]";
	}

	/** @locatorType Xpath */
	public static final String PendingRFQBtn = "//button[@id='B1022828162906840804']";
	/** @locatorType Xpath */
	public static final String PendingPRBtn = "//button[@id='B2145315718133643750']";
	/** @locatorType Xpath */
	public static final String PendingPRiframe = "//iframe[@title='Pending PR']";
	/** @locatorType Xpath */
	public static final String PendingPR_FindPRNo = "//button[contains(@id,'PR_NO')]";
	/** @locatorType Xpath */
	public static final String PendingPR_SearchBtn = "//button[@id='B2403556419484961039']";
	/** @locatorType Xpath */
	public static final String PendingPR_SelectAllCheckBox = "(//input[@id='check_all'])";
	/** @locatorType Xpath */
	public static final String PendingPR_CreatePO_Btn = "//button[@id='B1']";
	/** @locatorType Xpath */
	public static final String PendingPR_SupplierSelectBtn = "//button[@id='P16151310407_SUPPLIER_lov_btn']";
	/** @locatorType Xpath */
	public static final String PendingPR_CurrencySelectBtn = "//button[@id='P16151310407_CURRENCY_lov_btn']";
	/** @locatorType Xpath */
	public static final String PendingPR_OkBtn = "//button[@id='B2327883803940919344']";

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
	public static final String AddItemCharges = "//button[@id='B1474679171989649967']";
	/** @locatorType Xpath */
	public static final String ChargeItemTable = "//tbody/tr[@data-id='t1000']/td[3]";
	/** @locatorType Xpath */
	public static final String ChargeItemInput = "//input[@id='C5323519417424678619']";
	/** @locatorType Xpath */
	public static final String ChargePercentage = "//tbody/tr[@data-id='t1000']/td[7]";
	/** @locatorType Xpath */
	public static final String ChargePercentageInput = "//input[@id='C2717147067612688516']";
	/** @locatorType Xpath */
	public static final String ChargeItemButton = "//button[@id='C5323519417424678619_lov_btn']";
	/** @locatorType Xpath */
	public static final String ChargesTickIcon = "//button[@id='B1474679446874649975']";
	/** @locatorType Xpath */
	public static final String IncludeCharges = "//button[@id='B1822917544074308705']";
	/** @locatorType Xpath */
	public static final String LineTab = "//label[@for='P161513106041_TAB_LINE_0']";
	/** @locatorType Xpath */
	public static final String LineAmountWE = "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr/td[9]";
	/** @locatorType Xpath */
	public static final String GLAccDesc = "//tbody/tr[@data-id='t1000']/td[11]";
	/** @locatorType Xpath */
	public static final String GlAccDescButton = "//*[@id='C5323521734029678642_lov_btn']";

	/** @locatorType Xpath */
	public static final String ItemName(int i) {
		return "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr[" + i + "]/td[2]";
	}

	/** @locatorType Xpath */
	public static final String OrderedQty(int i) {
		return "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr[" + i + "]/td[6]";
	}

	/** @locatorType Xpath */
	public static final String UnitCostTable(int i) {
		return "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr[" + i + "]/td[7]";
	}

	/** @locatorType Xpath */
	public static final String LineAmount(int i) {
		return "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr[" + i + "]/td[9]";
	}

	/** @locatorType Xpath */
	public static final String TaxAmount(int i, int j) {
		return "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr[" + i + "]/td[" + j + "]";
	}

	/** @locatorType Xpath */
	public static final String UOMTable = "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr/td[5]";
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
		return "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr[" + i + "]/td[4]";
	}

	/** @locatorType Xpath */
	public static final String UOM(int i) {
		return "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr[" + i + "]/td[5]";
	}

	/** @locatorType Xpath */
	public static final String HSNSACCode(int i) {
		return "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr[" + i + "]/td[10]";
	}

	/** @locatorType Xpath */
	public static final String TaxPercent(int i) {
		return "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr[" + i + "]/td[11]";
	}

	/** @locatorType Xpath */
	public static final String LineDetailButton(int i) {
		return "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr[" + i
				+ "]/td/a/span[contains(@style,'Orange')]";
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
	public static final String UnRegisterHeader = "//h2[text()='Unregister RCM Details']";
	/** @locatorType Xpath */
	public static final String CurrencyInput = "//*[@id='P1615131061_POH_CURRENCY']";
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
	public static final String TradePercentage = "//input[@id='P161513106045_POL_TRD_DISC_PCT']";
	/** @locatorType Xpath */
	public static final String TradeAssembleValue = "//input[@id='P161513106045_POL_TRD_ASSBL_VAL']";
	/** @locatorType Xpath */
	public static final String TradeAmount = "//input[@id='P161513106045_POL_TRD_DISC_AMT']";
	/** @locatorType Xpath */
	public static final String SpecialAssembleValue = "//input[@id='P161513106045_POL_SPL_ASSBL_VAL']";
	/** @locatorType Xpath */
	public static final String SpecialDiscountPercentage = "//input[@id='P161513106045_POL_SPL_DISC_PCT']";
	/** @locatorType Xpath */
	public static final String SpecialDiscountAmount = "//input[@id='P161513106045_POL_SPL_DISC_AMT']";
	/** @locatorType Xpath */
	public static final String CashAssembleValue = "//input[@id='P161513106045_POL_CASH_ASSBL_VAL']";
	/** @locatorType Xpath */
	public static final String CashDiscountPercentage = "//input[@id='P161513106045_POL_CASH_DISC_PCT']";
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
	public static final String DiscountAmount(String Item) {
		return "//td[text()='" + Item + "']/../td[8]";
	}

	/** @locatorType Xpath */
	public static final String TickButtonLineOne = "//button[@id='B1467193366803536440']";

	/** @locatorType Xpath */
	public static final String ServiceLineDetailButton(int i) {
		return "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr[" + i
				+ "]/td/a/span[contains(@style,'Orange')]";
	}

	/** @locatorType Xpath */
	public static final String PoItemSelect = "//*[@id=\"PopupLov_161513106041_C3750778132967408970_dlg\"]/div[2]/div[2]/div[3]/table/tbody/tr/td[1]/span";
	/** @locatorType Xpath */
	public static final String POItemInput = "//*[@id=\"PopupLov_161513106041_C3750778132967408970_dlg\"]/div[1]/input";
	/** @locatorType Xpath */
	public static final String PoQtyInput = "//input[@id='C3135022162127842865']";
	/** @locatorType Xpath */
	public static final String PoQtyFieldClick = "//*[@id=\"ig_po_lineitems_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr/td[6]";
	/** @locatorType Xpath */
	public static final String PoUnitCost = "//input[@id='cost']";
	/** @locatorType Xpath */
	public static final String PoUnitCostClick = "//*[@id=\"ig_po_lineitems_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr/td[7]";
	/** @locatorType Xpath */
	public static final String PolinewiseSave = "//button[@id='B3146205337803717780']";
	/** @locatorType Xpath */
	public static final String PoServiceWithGrn = "//*[@id=\"ig_po_lineitems_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[1]/td[20]";
	/** @locatorType Xpath */
	public static final String PoseriviceWithGrnSelectedValues = "//*[@id=\"C2522028662001049402\"]";
	/** @locatorType Xpath */
	public static final String OkButtonFwdForQcDocument = "//button[@id='B1652647721744321354']";
	/** @locatorType Xpath */
	public static final String SaleOrderDrillDown = "//*[@id='1960290421519866117_orig']/tbody/tr[2]/td[8]/a";
	/** @locatorType Xpath */
	public static final String PrefixLovButton = "//button[@id='P16151310407_PFX_lov_btn']";

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
	public static final String PurchaseOrderLineDetailForServiceItem(int i) {
		return "//*[@id='ig_po_lineitems_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[22]";
	}

	/** @locatorType Xpath */
	public static final String PurchaseOrderLineDetail(int i) {
		return "//*[@id='ig_po_lineitems_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[21]";
	}

	public static final String POType = "//*[@id=\"P161513106041_POH_TYPE_DISPLAY\"]";

	/** @locatorType Xpath */
	public static final String PurchaseOrderLineDetailsForServiceItem = "//*[@id='ig_po_lineitems_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[20]";

	/** @locatorType Xpath */
	public static final String POSubClass = "//span[@id='P161513106045_POL_PROD_SUB_CLS_DISPLAY']";
	/** @locatorType Xpath */
	public static final String LineDeatilsSave = "//button[@id='B1467193366803536440']";
	/** @locatorType Xpath */
	public static final String locationDetails = "//button[@id='B1466431803058327039']";
}
