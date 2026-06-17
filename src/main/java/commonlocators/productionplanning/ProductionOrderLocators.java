package commonlocators.productionplanning;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 20, 2025
 */
public class ProductionOrderLocators {

	/** @locatorType XPATH */
	public static final String FindMRPNoLovBtn = "//input[@data-testid='FindMRPNoLovBtn']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String FindSearchBtn = "//button[@data-testid='FindSearchBtn']";

	/** @locatorType XPATH */
	public static final String Find_TableData_ProdNoWithMRPNo(String MRPNo) {
		return "//*[contains(text(),'" + MRPNo + "')]/../td[6]/a";
	}

	/** @locatorType XPATH */
	public static final String Find_TableData_ProdNo(String ProdNo) {
		return "//*[contains(text(),'" + ProdNo + "')]/../../td[6]/a";
	}

	/** @locatorType XPATH */
	public static final String Find_TableData_Status(String DocNo) {
		return "//td[contains(text(),'" + DocNo + "')]/../td/div[contains(@style,'color')]";
	}

	/** @locatorType XPATH */
	public static final String FindProdOrderNoLovBtn = "//input[@data-testid='FindProdOrderNoLovBtn']/following-sibling::button";
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
	public static final String RoutingTable = "//div[@id='ROUTING_ig_grid_vc']";
	/** @locatorType XPATH */
	public static final String RoutingProcessNameTableDataLists = "(//div[@id='ROUTING_ig_grid_vc']//table)[2]/tbody/tr/td[4]";
	/** @locatorType XPATH */
	public static final String RoutingProcessTypeTableDataLists = "(//div[@id='ROUTING_ig_grid_vc']//table)[2]/tbody/tr/td[5]";
	/** @locatorType XPATH */
	public static final String RoutingConsumptionWHTableDataLists = "(//div[@id='ROUTING_ig_grid_vc']//table)[2]/tbody/tr/td[8]";
	/** @locatorType XPATH */
	public static final String RoutingRecipientWHTableDataLists = "(//div[@id='ROUTING_ig_grid_vc']//table)[2]/tbody/tr/td[9]";
	/** @locatorType XPATH */
	public static final String MaterialReqProcessTableDataLists = "(//div[@id='material_ig_grid_vc']//table)[2]/tbody/tr/td[2]";
	/** @locatorType XPATH */
	public static final String MaterialReqTable = "//div[@id='material_ig_grid_vc']";
	/** @locatorType XPATH */
	public static final String MaterialReqRMItemTableDataLists = "(//div[@id='material_ig_grid_vc']//table)[2]/tbody/tr/td[5]";
	/** @locatorType XPATH */
	public static final String ResourcesTable = "//div[@id='resource_ig_grid_vc']";
	/** @locatorType XPATH */
	public static final String ResourcesProcessTableDataLists = "(//div[@id='resource_ig_grid_vc']//table)[2]/tbody/tr/td[2]";
	/** @locatorType XPATH */
	public static final String HeaderSaveIcon = "(//button[@data-testid='HeaderSaveIcon'])[2]";
	/** @locatorType XPATH */
	public static final String ProdOrderPostBtn = "//button[@data-testid='ProdOrderPostBtn']";
	/** @locatorType XPATH */
	public static final String ExceptionTableContainer = "//div[@id='R1638784087061979684_ig']";
	/** @locatorType XPATH */
	public static final String ExceptionNoDataFoundContainer = "//div[@id='R1638784087061979684_ig']//span[text()='No data found']";
	/** @locatorType XPATH */
	public static final String DisplayOrderQuantityInput = "//input[@id='P19631010001_PROHD_ORDER_QTY']";
	/** @locatorType XPATH */
	public static final String CreateProdOrderAddIcon = "//button[@data-testid='CreateProdOrderAddIcon']";
	/** @locatorType XPATH */
	public static final String CreateProdOrderIframe = "//iframe[@title='Production Order']";
	/** @locatorType XPATH */
	public static final String CreateProdOrderIframeCloseIcon = "//h1[text()='Production Order']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String CreateProdOrd_LocationLovbtn = "//input[@data-testid='CreateProdOrd_LocationLovbtn']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String CreateProdOrd_SODescLovbtn = "//input[@data-testid='SoDesc']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String CreateProdOrd_OrderTypeLovbtn = "//input[@data-testid='CreateProdOrd_OrderTypeLovbtn']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String CreateProdOrd_PrefixLovbtn = "//input[@data-testid='CreateProdOrd_PrefixLovbtn']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String CreateProdOrd_ItemLovbtn = "//input[@data-testid='CreateProdOrd_ItemLovbtn']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String CreateProdOrd_BOMTypeSelect = "//select[@data-testid='CreateProdOrd_BOMTypeSelect']";
	/** @locatorType XPATH */
	public static final String CreateProdOrd_OrderQntyInput = "//input[@data-testid='CreateProdOrd_OrderQntyInput']";
	/** @locatorType XPATH */
	public static final String CreateProdOrd_TickIcon = "//button[@data-testid='CreateProdOrd_TickIcon']";
	/** @locatorType XPATH */
	public static final String DisplayProdOrderNo = "//span[@data-testid='DisplayProdOrderNo']";
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
	public static final String SoDesc = "//input[@data-testid='SoDesc']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String ShowMore = "//button[@data-testid='ShowMore']";
	/** @locatorType XPATH */
	public static final String FirstOprSelect = "//select[@data-testid='FirstOprMREventSelect']";
	/** @locatorType XPATH */
	public static final String RestOprSelect = "//select[@data-testid='RestOprMREventSelect']";
	/** @locatorType XPATH */
	public static final String ShowLess = "//button[@data-testid='ShowLess']";
	/** @locatorType XPATH */
	public static final String TotalProcess = "//div[@id='ROUTING_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[4]";

	/** @locatorType XPATH */
	public static final String TableData_Status(String DocNo) {
		return "//td/a[contains(text(),'" + DocNo + "')]/../../td/div";
	}

	/** @locatorType XPATH */
	public static final String ShowMoreHeaderBtn = "//button[@id='show']";
	/** @locatorType XPATH */
	public static final String FirstOprMREventSelect = "//select[@data-testid='FirstOprMREventSelect']";
	/** @locatorType XPATH */
	public static final String RestOprMREventSelect = "//select[@data-testid='RestOprMREventSelect']";

	/** @locatorType XPATH */
	public static final String FindTableStatusWithProdOrderNo(String DocNo) {
		return "//a[contains(text(),'" + DocNo + "')]/../../td/div";
	}

	/** @locatorType XPATH */
	public static final String ProcessMoveType = "//select[@data-testid='ProcessMoveType']";
	/** @locatorType XPATH */
	public static final String ProcessTypeList = "//div[@id='ROUTING_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[5]";

	/** @locatorType XPATH */
	public static final String ProcessType(int i) {
		return "//div[@id='ROUTING_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[5]";
	}

	/** @locatorType XPATH */
	public static final String Routing = "//label[@for='P19631010001_TAB_0']";
	/** @locatorType XPATH */
	public static final String MaterialRequestTab = "//label[@for='P19631010001_TAB_1']";

	/** @locatorType XPATH */
	public static final String ProcessName(int i) {
		return "//div[@id='ROUTING_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[4]";
	}

	/** @locatorType XPATH */
	public static final String MaterialProcess = "//div[@id='material_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[2]";

	/** @locatorType XPATH */
	public static final String MaterialProcess(int i) {
		return "//div[@id='material_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[2]";
	}

	/** @locatorType XPATH */
	public static final String ItemName(int i) {
		return "//div[@id='material_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[5]";
	}

	/** @locatorType XPATH */
	public static final String ProdOrderCancelBtn = "//button[@data-testid='ProdOrderCancelBtn']";
	/** @locatorType XPATH */
	public static final String RoutingAddBtn = "//button[@data-testid='RoutingAddBtn']";
	/** @locatorType XPATH */
	public static final String RoutingSaveBtn = "//button[@data-testid='RoutingSaveBtn']";
	/** @locatorType XPATH */
	public static final String RoutingProcessSeqTableData = "(//div[@id='ROUTING_ig_grid_vc']//table//tr/td[1])[1]";
	/** @locatorType XPATH */
	public static final String RoutingProcessSeqTableInput = "(//div[@id='ROUTING_ig_grid_vc']//table//tr/td[1])[1]//input";
	/** @locatorType XPATH */
	public static final String RoutingOprnNoTableData = "(//div[@id='ROUTING_ig_grid_vc']//table//tr/td[2])[1]";
	/** @locatorType XPATH */
	public static final String RoutingOprnNoTableInput = "(//div[@id='ROUTING_ig_grid_vc']//table//tr/td[2])[1]//input";
	/** @locatorType XPATH */
	public static final String RoutingProcessTableData = "(//div[@id='ROUTING_ig_grid_vc']//table//tr/td[3])[1]";
	/** @locatorType XPATH */
	public static final String RoutingProcessTableLovBtn = "(//div[@id='ROUTING_ig_grid_vc']//table//tr/td[3])[1]//button";
	/** @locatorType XPATH */
	public static final String RoutingTypeTableData = "(//div[@id='ROUTING_ig_grid_vc']//table//tr/td[5])[1]";
	/** @locatorType XPATH */
	public static final String RoutingTypeTableSelect = "(//div[@id='ROUTING_ig_grid_vc']//table//tr/td[5])[1]//select";
	/** @locatorType XPATH */
	public static final String RoutingWorkCenterTableData = "(//div[@id='ROUTING_ig_grid_vc']//table//tr/td[6])[1]";
	/** @locatorType XPATH */
	public static final String RoutingWorkCenterTableSelect = "(//div[@id='ROUTING_ig_grid_vc']//table//tr/td[6])[1]//select";
	/** @locatorType XPATH */
	public static final String RoutingConsWHTableData = "(//div[@id='ROUTING_ig_grid_vc']//table//tr/td[8])[1]";
	/** @locatorType XPATH */
	public static final String RoutingConsWHTableSelect = "(//div[@id='ROUTING_ig_grid_vc']//table//tr/td[8])[1]//select";
	/** @locatorType XPATH */
	public static final String RoutingRcptWHTableData = "(//div[@id='ROUTING_ig_grid_vc']//table//tr/td[9])[1]";
	/** @locatorType XPATH */
	public static final String RoutingRcptWHTableSelect = "(//div[@id='ROUTING_ig_grid_vc']//table//tr/td[9])[1]//select";
	/** @locatorType XPATH */
	public static final String RoutingLineDetailsIcon = "(//div[@id='ROUTING_ig_grid_vc']//table//tr/td[11])[1]//a";
	/** @locatorType XPATH */
	public static final String LineDetailsIframe = "//iframe";
	/** @locatorType XPATH */
	public static final String MaterialReqAddBtn = "//button[@data-testid='MaterialReqAddBtn']";
	/** @locatorType XPATH */
	public static final String MaterialReqSaveBtn = "//button[@data-testid='MaterialReqSaveBtn']";
	/** @locatorType XPATH */
	public static final String MRProcessTableData = "(//div[@id='material_ig_grid_vc']//table//tr/td[2])[1]";
	/** @locatorType XPATH */
	public static final String MRProcessTableLovBtn = "(//div[@id='material_ig_grid_vc']//table//tr/td[2])[1]//button";
	/** @locatorType XPATH */
	public static final String MRItemTableData = "(//div[@id='material_ig_grid_vc']//table//tr/td[5])[1]";
	/** @locatorType XPATH */
	public static final String MRItemTableLovBtn = "(//div[@id='material_ig_grid_vc']//table//tr/td[5])[1]//button";
	/** @locatorType XPATH */
	public static final String MRBOMQntyTableData = "(//div[@id='material_ig_grid_vc']//table//tr/td[10])[1]";
	/** @locatorType XPATH */
	public static final String MRBOMQntyTableInput = "(//div[@id='material_ig_grid_vc']//table//tr/td[10])[1]//input";
	/** @locatorType XPATH */
	public static final String MRReqQntyTableData = "(//div[@id='material_ig_grid_vc']//table//tr/td[12])[1]";
	/** @locatorType XPATH */
	public static final String MRReqQntyTableInput = "(//div[@id='material_ig_grid_vc']//table//tr/td[12])[1]//input";
	/** @locatorType XPATH */
	public static final String ResourcesAddBtn = "//button[@data-testid='ResourcesAddBtn']";
	/** @locatorType XPATH */
	public static final String ResourcesSaveBtn = "//button[@data-testid='ResourcesSaveBtn']";
	/** @locatorType XPATH */
	public static final String ResourceProcessTableData = "(//div[@id='resource_ig_grid_vc']//table//tr/td[2])[1]";
	/** @locatorType XPATH */
	public static final String ResourceProcessTableLovBtn = "(//div[@id='resource_ig_grid_vc']//table//tr/td[2])[1]//button";
	/** @locatorType XPATH */
	public static final String ResourceGroupTableData = "(//div[@id='resource_ig_grid_vc']//table//tr/td[6])[1]";
	/** @locatorType XPATH */
	public static final String ResourceGroupTableLovBtn = "(//div[@id='resource_ig_grid_vc']//table//tr/td[6])[1]//button";
	/** @locatorType XPATH */
	public static final String ResourceTypeTableData = "(//div[@id='resource_ig_grid_vc']//table//tr/td[4])[1]";
	/** @locatorType XPATH */
	public static final String ResourceTypeTableSelect = "(//div[@id='resource_ig_grid_vc']//table//tr/td[4])[1]//select";
	/** @locatorType XPATH */
	public static final String ResourceBasisTableData = "(//div[@id='resource_ig_grid_vc']//table//tr/td[5])[1]";
	/** @locatorType XPATH */
	public static final String ResourceBasisTableSelect = "(//div[@id='resource_ig_grid_vc']//table//tr/td[5])[1]//select";
	/** @locatorType XPATH */
	public static final String ResourceDateTableData = "(//div[@id='resource_ig_grid_vc']//table//tr/td[7])[1]";
	/** @locatorType XPATH */
	public static final String ResourceDateTableInput = "(//div[@id='resource_ig_grid_vc']//table//tr/td[7])[1]//input";
	/** @locatorType XPATH */
	public static final String ResourceEffTableData = "(//div[@id='resource_ig_grid_vc']//table//tr/td[8])[1]";
	/** @locatorType XPATH */
	public static final String ResourceEffTableInput = "(//div[@id='resource_ig_grid_vc']//table//tr/td[8])[1]//input";
	/** @locatorType XPATH */
	public static final String ResourceSetupHrsTableData = "(//div[@id='resource_ig_grid_vc']//table//tr/td[9])[1]";
	/** @locatorType XPATH */
	public static final String ResourceSetupHrsTableInput = "(//div[@id='resource_ig_grid_vc']//table//tr/td[9])[1]//input";
	/** @locatorType XPATH */
	public static final String ResourceSetupMinTableData = "(//div[@id='resource_ig_grid_vc']//table//tr/td[10])[1]";
	/** @locatorType XPATH */
	public static final String ResourceSetupMinTableInput = "(//div[@id='resource_ig_grid_vc']//table//tr/td[10])[1]//input";
	/** @locatorType XPATH */
	public static final String ResourceHrsTableData = "(//div[@id='resource_ig_grid_vc']//table//tr/td[11])[1]";
	/** @locatorType XPATH */
	public static final String ResourceHrsTableInput = "(//div[@id='resource_ig_grid_vc']//table//tr/td[11])[1]//input";
	/** @locatorType XPATH */
	public static final String ResourceMinTableData = "(//div[@id='resource_ig_grid_vc']//table//tr/td[12])[1]";
	/** @locatorType XPATH */
	public static final String ResourceMinTableInput = "(//div[@id='resource_ig_grid_vc']//table//tr/td[12])[1]//input";
	/** @locatorType XPATH */
	public static final String ResourceSecTableData = "(//div[@id='resource_ig_grid_vc']//table//tr/td[13])[1]";
	/** @locatorType XPATH */
	public static final String ResourceSecTableInput = "(//div[@id='resource_ig_grid_vc']//table//tr/td[13])[1]//input";
	/** @locatorType XPATH */
	public static final String ResourcePriorityTableData = "(//div[@id='resource_ig_grid_vc']//table//tr/td[17])[1]";
	/** @locatorType XPATH */
	public static final String ResourcePriorityTableInput = "(//div[@id='resource_ig_grid_vc']//table//tr/td[17])[1]//input";
	/** @locatorType XPATH */
	public static final String ResourceLagHrsTableData = "(//div[@id='resource_ig_grid_vc']//table//tr/td[19])[1]";
	/** @locatorType XPATH */
	public static final String ResourceLagHrsTableInput = "(//div[@id='resource_ig_grid_vc']//table//tr/td[19])[1]//input";
	/** @Locator XPATH */
	public static final String WorkApprovalFrame = "//iframe[@title='Approval']";
	/** @Locator XPATH */
	public static final String ForwardApprovalLov = "//*[@id='P236131090_FWD_PERSON_lov_btn']";
	/** @Locator XPATH */
	public static final String OkButtonForward = "//BUTTON[@ID='B479819303601188310']";
	/** @Locator XPATH */
	public static final String NonStandardLotSerialDetailTable = "//div[@id='detail_ig_grid_vc']//table//tr/td[11]";
	/** @Locator XPATH */
	public static final String NonStandardLotSerialDetailSelect = "//div[@id='detail_ig_grid_vc']//table//tr/td[11]//select";
	/** @Locator XPATH */
	public static final String LineDetailsSaveIcon = "//button[@id='B1611356104066403183']";
	/** @Locator XPATH */
	public static final String LineDetailsCloseIcon = "//div[@id='ROUTING_ig_grid_vc_floatingItem_dialog']/following-sibling::div//button[@title='Close']";
	/** @Locator XPATH */
	public static final String FromSerial_Input = "//input[@id='P64196310101_FROM_SERIAL']";
	/** @Locator XPATH */
	public static final String RawMaterialSaveButton = "//button[@title='Cons Add']/following-sibling::button[@aria-label='Save']";
	/** @Locator XPATH */
	public static final String CreateMrButton = "//button[@id='Comp']";

}
