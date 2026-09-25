package commonlocators.productionplanning;

import commonfunctions.CommonFunctions;

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
		return "(//*[contains(text(),'" + MRPNo + "')]/../td/a[contains(@href,'prod')])[2]";
	}

	/** @locatorType XPATH */
	public static final String Find_TableData_ProdNo(String ProdNo) {
		return "(//*[contains(text(),'" + ProdNo + "')]/../../td/a[contains(@href,'prod')])[2]";
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
	public static final String RoutingTable = "(//div[@id='ROUTING_ig_grid_vc']//table)[2]";

	/** @locatorType XPATH */
	public static String RoutingProcessNameTableDataLists() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(RoutingTable, "Process ID");
	}

	/** @locatorType XPATH */
	public static String RoutingProcessTypeTableDataLists() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(RoutingTable, "Type");
	}

	/** @locatorType XPATH */
	public static String RoutingConsumptionWHTableDataLists() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(RoutingTable, "Cons. WH");
	}

	/** @locatorType XPATH */
	public static String RoutingRecipientWHTableDataLists() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(RoutingTable, "Rcpt. WH");
	}

	/** @locatorType XPATH */
	public static final String MaterialReqTable = "(//div[@id='material_ig_grid_vc']//table)[2]";

	/** @locatorType XPATH */
	public static String MaterialReqProcessTableDataLists() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(MaterialReqTable, "Process");
	}

	/** @locatorType XPATH */
	public static String MaterialReqRMItemTableDataLists() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(MaterialReqTable, "Item");
	}

	/** @locatorType XPATH */
	public static final String ResourcesTable = "(//div[@id='resource_ig_grid_vc']//table)[2]";

	/** @locatorType XPATH */
	public static String ResourcesProcessTableDataLists() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourcesTable, "Process");
	}

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
	public static final String CreateProdOrderIframe = "//iframe[@title='Create Production Order']";
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
	public static final String CreateProdOrd_OrderQntyInput = "(//input[@data-testid='CreateProdOrd_OrderQntyInput'])[2]";
	/** @locatorType XPATH */
	public static final String CreateProdOrd_NoOfBatchInput = "(//input[@data-testid='CreateProdOrd_OrderQntyInput'])[1]";
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
	public static final String ShowMore = "//button[@id='show']";
	/** @locatorType XPATH */
	public static final String FirstOprSelect = "//select[@data-testid='FirstOprMREventSelect']";
	/** @locatorType XPATH */
	public static final String RestOprSelect = "//select[@data-testid='RestOprMREventSelect']";
	/** @locatorType XPATH */
	public static final String ShowLess = "//button[@id='hide']";
	/** @locatorType XPATH */
	public static final String AddedRoutingTable = "(//div[@aria-label='Routing']//table[@data-testid='ROUTING_Result_Data'])[2]";

	/** @locatorType XPATH */
	public static final String TotalProcess() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddedRoutingTable, "Process Desc.");
	}

	/** @locatorType XPATH */
	public static final String ProcessTypeList() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddedRoutingTable, "Type");
	}

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
	public static final String ProcessType(int i) {
		return "//div[@id='ROUTING_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[6]";
	}

	/** @locatorType XPATH */
	public static final String Routing = "//label[@for='P19631010001_TAB_0']";
	/** @locatorType XPATH */
	public static final String MaterialRequestTab = "//label[@for='P19631010001_TAB_1']";

	/** @locatorType XPATH */
	public static final String ProcessName(int i) {
		return "//div[@id='ROUTING_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[5]";
	}

	/** @locatorType XPATH */
	public static final String AddedMaterialTable = "//div[contains(@aria-label,'Material Requirement')]//table";

	/** @locatorType XPATH */
	public static final String MaterialProcess() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddedMaterialTable, "Process");
	}

	/** @locatorType XPATH */
	public static final String MaterialProcess(int i) {
		return "//div[@id='material_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[2]";
	}

	/** @locatorType XPATH */
	public static final String ItemName(int i) {
		return "//div[@id='material_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[6]";
	}

	/** @locatorType XPATH */
	public static final String ProdOrderCancelBtn = "//button[@data-testid='ProdOrderCancelBtn']";
	/** @locatorType XPATH */
	public static final String RoutingAddBtn = "//button[@data-testid='RoutingAddBtn']";
	/** @locatorType XPATH */
	public static final String RoutingSaveBtn = "//button[@data-testid='RoutingSaveBtn']";
	/** @locatorType XPATH */
	public static final String AddRoutingTable = "(//div[@id='ROUTING_ig_grid_vc']//table)[2]";

	/** @locatorType XPATH */
	public static final String RoutingProcessSeqTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddRoutingTable, "Proc. Seq. No.");
	}

	/** @locatorType XPATH */
	public static final String RoutingProcessSeqTableInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddRoutingTable, "Proc. Seq. No.")
				+ "//input";
	}

	/** @locatorType XPATH */
	public static final String RoutingOprnNoTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddRoutingTable, "Oprn. No.");
	}

	/** @locatorType XPATH */
	public static final String RoutingOprnNoTableInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddRoutingTable, "Oprn. No.") + "//input";
	}

	/** @locatorType XPATH */
	public static final String RoutingProcessTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddRoutingTable, "Process ID");
	}

	/** @locatorType XPATH */
	public static final String RoutingProcessTableLovBtn() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddRoutingTable, "Process ID") + "//button";
	}

	/** @locatorType XPATH */
	public static final String RoutingTypeTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddRoutingTable, "Type");
	}

	/** @locatorType XPATH */
	public static final String RoutingTypeTableSelect() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddRoutingTable, "Type") + "//select";
	}

	/** @locatorType XPATH */
	public static final String RoutingWorkCenterTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddRoutingTable, "Work Center");
	}

	/** @locatorType XPATH */
	public static final String RoutingWorkCenterTableSelect() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddRoutingTable, "Work Center")
				+ "//select";
	}

	/** @locatorType XPATH */
	public static final String RoutingConsWHTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddRoutingTable, "Cons. WH");
	}

	/** @locatorType XPATH */
	public static final String RoutingConsWHTableSelect() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddRoutingTable, "Cons. WH") + "//select";
	}

	/** @locatorType XPATH */
	public static final String RoutingRcptWHTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddRoutingTable, "Rcpt. WH");
	}

	/** @locatorType XPATH */
	public static final String RoutingRcptWHTableSelect() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddRoutingTable, "Rcpt. WH") + "//select";
	}

	/** @locatorType XPATH */
	public static final String RoutingLineDetailsIcon() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(AddRoutingTable, "Type") + "/../td[1]//a";
	}

	/** @locatorType XPATH */
	public static final String LineDetailsIframe = "//iframe";
	/** @locatorType Xpath */
	public static final String DetailsMenuThreeDots = "//ul[@class='custom-row-menu']//*[text()='Details']";
	/** @locatorType XPATH */
	public static final String MaterialReqAddBtn = "//button[@data-testid='MaterialReqAddBtn']";
	/** @locatorType XPATH */
	public static final String MaterialReqSaveBtn = "//button[@data-testid='MaterialReqSaveBtn']";
	/** @locatorType XPATH */
	public static final String MaterialRequestTable = "(//div[@id='material_ig_grid_vc']//table)[2]";

	/** @locatorType XPATH */
	public static final String MRProcessTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(MaterialRequestTable, "Process");
	}

	/** @locatorType XPATH */
	public static final String MRProcessTableLovBtn() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(MaterialRequestTable, "Process")
				+ "//button";
	}

	/** @locatorType XPATH */
	public static final String MRItemTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(MaterialRequestTable, "Item ID");
	}

	/** @locatorType XPATH */
	public static final String MRItemTableLovBtn() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(MaterialRequestTable, "Item ID") + "//button";
	}

	/** @locatorType XPATH */
	public static final String MRBOMQntyTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(MaterialRequestTable, "BOM Qty.");
	}

	/** @locatorType XPATH */
	public static final String MRBOMQntyTableInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(MaterialRequestTable, "BOM Qty.")
				+ "//input";
	}

	/** @locatorType XPATH */
	public static final String MRReqQntyTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(MaterialRequestTable, "Required Qty.");
	}

	/** @locatorType XPATH */
	public static final String MRReqQntyTableInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(MaterialRequestTable, "Required Qty.")
				+ "//input";
	}

	/** @locatorType XPATH */
	public static final String ResourcesAddBtn = "//button[@data-testid='ResourcesAddBtn']";
	/** @locatorType XPATH */
	public static final String ResourcesSaveBtn = "//button[@data-testid='ResourcesSaveBtn']";
	/** @locatorType XPATH */
	public static final String ResourceTable = "(//div[@id='resource_ig_grid_vc']//table)[1]";

	/** @locatorType XPATH */
	public static final String ResourceProcessTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourceTable, "Process");
	}

	/** @locatorType XPATH */
	public static final String ResourceProcessTableLovBtn() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourceTable, "Process") + "//button";
	}

	/** @locatorType XPATH */
	public static final String ResourceGroupTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourceTable, "Resource Group / Resource");
	}

	/** @locatorType XPATH */
	public static final String ResourceGroupTableLovBtn() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourceTable, "Resource Group / Resource")
				+ "//button";
	}

	/** @locatorType XPATH */
	public static final String ResourceTypeTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourceTable, "Type");
	}

	/** @locatorType XPATH */
	public static final String ResourceTypeTableSelect() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourceTable, "Type") + "//select";
	}

	/** @locatorType XPATH */
	public static final String ResourceBasisTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourceTable, "Basis");
	}

	/** @locatorType XPATH */
	public static final String ResourceBasisTableSelect() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourceTable, "Basis") + "//select";
	}

	/** @locatorType XPATH */
	public static final String ResourceDateTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourceTable, "Date");
	}

	/** @locatorType XPATH */
	public static final String ResourceDateTableInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourceTable, "Date") + "//input";
	}

	/** @locatorType XPATH */
	public static final String ResourceEffTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourceTable, "Eff. %");
	}

	/** @locatorType XPATH */
	public static final String ResourceEffTableInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourceTable, "Eff. %") + "//input";
	}

	/** @locatorType XPATH */
	public static final String ResourceSetupHrsTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourceTable, "Setup Hrs.");
	}

	/** @locatorType XPATH */
	public static final String ResourceSetupHrsTableInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourceTable, "Setup Hrs.") + "//input";
	}

	/** @locatorType XPATH */
	public static final String ResourceSetupMinTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourceTable, "Setup Mins.");
	}

	/** @locatorType XPATH */
	public static final String ResourceSetupMinTableInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourceTable, "Setup Mins.") + "//input";
	}

	/** @locatorType XPATH */
	public static final String ResourceHrsTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourceTable, "Hrs.");
	}

	/** @locatorType XPATH */
	public static final String ResourceHrsTableInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourceTable, "Hrs.") + "//input";
	}

	/** @locatorType XPATH */
	public static final String ResourceMinTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourceTable, "Mins.");
	}

	/** @locatorType XPATH */
	public static final String ResourceMinTableInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourceTable, "Mins.") + "//input";
	}

	/** @locatorType XPATH */
	public static final String ResourceSecTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourceTable, "Secs.");
	}

	/** @locatorType XPATH */
	public static final String ResourceSecTableInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourceTable, "Secs.") + "//input";
	}

	/** @locatorType XPATH */
	public static final String ResourcePriorityTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourceTable, "Priority");
	}

	/** @locatorType XPATH */
	public static final String ResourcePriorityTableInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourceTable, "Priority") + "//input";
	}

	/** @locatorType XPATH */
	public static final String ResourceLagHrsTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourceTable, "Lag Hrs.");
	}

	/** @locatorType XPATH */
	public static final String ResourceLagHrsTableInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(ResourceTable, "Lag Hrs.") + "//input";
	}

	/** @Locator XPATH */
	public static final String WorkApprovalFrame = "//iframe[@title='Approval']";
	/** @Locator XPATH */
	public static final String ForwardApprovalLov = "//*[@id='P236131090_FWD_PERSON_lov_btn']";
	/** @Locator XPATH */
	public static final String OkButtonForward = "//BUTTON[@ID='B479819303601188310']";
	/** @Locator XPATH */
	public static final String NonStandardLotSerialDetailTable = "//div[@id='detail_ig_grid_vc']//table[@data-testid='detail_Result_Data']//tr/td[11]";
	/** @Locator XPATH */
	public static final String NonStandardLotSerialDetailSelect = "//div[@id='detail_ig_grid_vc']//table[@data-testid='detail_Result_Data']//tr/td[11]//select";
	/** @Locator XPATH */
	public static final String LineDetailsSaveIcon = "//div[@id='detail_ig']/..//button[@title='Save']";
	/** @Locator XPATH */
	public static final String LineDetailsCloseIcon = "//div[@id='ROUTING_ig_grid_vc_floatingItem_dialog']/following-sibling::div//button[@title='Close']";
	/** @Locator XPATH */
	public static final String RawMaterialSaveButton = "//button[@title='Cons Add']/following-sibling::button[@aria-label='Save']";
	/** @Locator XPATH */
	public static final String CreateMrButton = "//button[@id='Comp']";

}
