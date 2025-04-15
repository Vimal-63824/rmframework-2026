package commonlocators.productionplanning;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 20, 2025
 */
public class ProductionOrderLocators {

	/** @locatorType XPATH */
	public static final String FindMRPNoLovBtn = "//button[@id='P6419631010_FIND_SCH_MRP_NO_lov_btn']";
	/** @locatorType XPATH */
	public static final String FindSearchBtn = "//button[@id='B1131360267064276001']";

	/** @locatorType XPATH */
	public static final String Find_TableData_ProdNoWithMRPNo(String MRPNo) {
		return "//*[contains(text(),'" + MRPNo + "')]/../td[4]/a";
	}

	/** @locatorType XPATH */
	public static final String Find_TableData_ProdNo(String ProdNo) {
		return "//*[contains(text(),'" + ProdNo + "')]/../../td[4]/a";
	}

	/** @locatorType XPATH */
	public static final String Find_TableData_Status(String DocNo) {
		return "//td[contains(text(),'" + DocNo + "')]/../td[13]/div";
	}

	/** @locatorType XPATH */
	public static final String FindProdOrderNoLovBtn = "//button[@id='P_FIND_PROD_ORD_ORD_NO_lov_btn']";
	/** @locatorType XPATH */
	public static final String ProdOrderReferenceDisplay = "//input[@id='P19631010001_PROHD_REFERENCE']";
	/** @locatorType XPATH */
	public static final String RoutingLabel = "//label[text()='Routing']";
	/** @locatorType XPATH */
	public static final String MaterialRequirementLabel = "//label[text()='Material Requirement']";
	/** @locatorType XPATH */
	public static final String ResourcesLabel = "//label[text()='Resource']";
	/** @locatorType XPATH */
	public static final String ScrapByProductLabel = "//label[text()='Scrap / By Product']";
	/** @locatorType XPATH */
	public static final String MultipleResourcesLabel = "//label[text()='Multiple Resources']";
	/** @locatorType XPATH */
	public static final String ExceptionLabel = "//label[text()='Exception']";
	/** @locatorType XPATH */
	public static final String RoutingProcessNameTableDataLists = "(//div[@id='ROUTING_ig_grid_vc']//table)[2]/tbody/tr/td[4]";
	/** @locatorType XPATH */
	public static final String RoutingConsumptionWHTableDataLists = "(//div[@id='ROUTING_ig_grid_vc']//table)[2]/tbody/tr/td[8]";
	/** @locatorType XPATH */
	public static final String RoutingRecipientWHTableDataLists = "(//div[@id='ROUTING_ig_grid_vc']//table)[2]/tbody/tr/td[9]";
	/** @locatorType XPATH */
	public static final String MaterialReqProcessTableDataLists = "(//div[@id='material_ig_grid_vc']//table)[2]/tbody/tr/td[2]";
	/** @locatorType XPATH */
	public static final String MaterialReqRMItemTableDataLists = "(//div[@id='material_ig_grid_vc']//table)[2]/tbody/tr/td[5]";
	/** @locatorType XPATH */
	public static final String ResourcesProcessTableDataLists = "(//div[@id='resource_ig_grid_vc']//table)[2]/tbody/tr/td[2]";
	/** @locatorType XPATH */
	public static final String HeaderSaveIcon = "//button[@id='B862141228532932155']";
	/** @locatorType XPATH */
	public static final String ProdOrderPostBtn = "//button[@id='B944728616712869764']";
	/** @locatorType XPATH */
	public static final String ExceptionTableContainer = "//div[@id='R967011785583947865_ig']";
	/** @locatorType XPATH */
	public static final String ExceptionNoDataFoundContainer = "//div[@id='R967011785583947865_ig']//span[text()='No data found']";
	/** @locatorType XPATH */
	public static final String DisplayOrderQuantityInput = "//input[@id='P19631010001_PROHD_ORDER_QTY']";
	/** @locatorType XPATH */
	public static final String CreateProdOrderAddIcon = "//button[@id='B1126880384972304449']";
	/** @locatorType XPATH */
	public static final String CreateProdOrderIframe = "//iframe[@title='Production Order']";
	/** @locatorType XPATH */
	public static final String CreateProdOrderIframeCloseIcon = "//h1[text()='Production Order']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String CreateProdOrd_LocationLovbtn = "//button[@id='P64196310101_LOC_lov_btn']";
	/** @locatorType XPATH */
	public static final String CreateProdOrd_OrderTypeLovbtn = "//button[@id='P64196310101_TYPE_lov_btn']";
	/** @locatorType XPATH */
	public static final String CreateProdOrd_PrefixLovbtn = "//button[@id='P64196310101_PFX_lov_btn']";
	/** @locatorType XPATH */
	public static final String CreateProdOrd_ItemLovbtn = "//button[@id='P64196310101_ITEM_lov_btn']";
	/** @locatorType XPATH */
	public static final String CreateProdOrd_BOMTypeSelect = "//select[@id='P64196310101_BOM_TYPE_1']";
	/** @locatorType XPATH */
	public static final String CreateProdOrd_OrderQntyInput = "//input[@id='P64196310101_ORDER_QTY']";
	/** @locatorType XPATH */
	public static final String CreateProdOrd_TickIcon = "//button[@id='B1134381614013977530']";
	/** @locatorType XPATH */
	public static final String DisplayProdOrderNo = "//span[@id='P19631010001_PROHD_ORD_NO_DISPLAY']";
	/** @locatorType XPATH */
	public static final String ScrapProductTableContainer = "//div[@id='scrap_ig_grid_vc']";

	/** @locatorType XPATH */
	public static final String MaterialRqrdmntDisplayBOMQty(String Process, String RMItem) {
		return "//td[text()='" + Process + "']/../td[text()='" + RMItem + "']/../td[10]";
	}

	/** @locatorType XPATH */
	public static final String MaterialRqrdmntDisplayRequiredQty(String Process, String RMItem) {
		return "//td[text()='" + Process + "']/../td[text()='" + RMItem + "']/../td[12]";
	}

	/** @locatorType XPATH */
	public static final String ScrapReceiptDisplayRqrdQty(String Process, String Item) {
		return "//td[text()='" + Process + "']/../td[text()='" + Item + "']/../td[9]";
	}

	/** @locatorType XPATH */
	public static final String ScrapReceiptDisplayRcptQty(String Process, String Item) {
		return "//td[text()='" + Process + "']/../td[text()='" + Item + "']/../td[10]";
	}

	/** @locatorType XPATH */
	public static final String SoDesc = "//button[@id='P64196310101_P_SO_SCHLD_DESC_lov_btn']";
	/** @locatorType XPATH */
	public static final String ShowMore = "//button[@id='show']";
	/** @locatorType XPATH */
	public static final String FirstOprSelect = "//select[@id='P19631010001_PROHD_FO_OPRN_MR_']";
	/** @locatorType XPATH */
	public static final String RestOprSelect = "//select[@id='P19631010001_PROHD_RO_OPRN_MR_']";
	/** @locatorType XPATH */
	public static final String ShowLess = "//button[@id='hide']";
	/** @locatorType XPATH */
	public static final String TotalProcess = "//div[@id='ROUTING_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[4]";

	/** @locatorType XPATH */
	public static final String TableData_Status(String DocNo) {
		return "//td/a[contains(text(),'" + DocNo + "')]/../../td[12]/div";
	}

	/** @locatorType XPATH */
	public static final String ShowMoreHeaderBtn = "//button[@id='show']";
	/** @locatorType XPATH */
	public static final String FirstOprMREventSelect = "//select[@id='P19631010001_PROHD_FO_OPRN_MR_']";
	/** @locatorType XPATH */
	public static final String RestOprMREventSelect = "//select[@id='P19631010001_PROHD_RO_OPRN_MR_']";

	/** @locatorType XPATH */
	public static final String FindTableStatusWithProdOrderNo(String DocNo) {
		return "//a[contains(text(),'" + DocNo + "')]/../../td[12]/div";
	}

}
