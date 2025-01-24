package commonlocators.supplychainmanagement;

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
	public static final String PONumberSelect = "//button[@id='P1615131061_DOC_NO_lov_btn']";

	/** @locatorType Xpath */
	public static final String DeleteLineItemPO(String ItemName) {
		return "//td[text()='" + ItemName + "']/../td[17]/a";
	}

}
