package commonlocators.productionplanning;

import commonfunctions.CommonFunctions;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks March 05, 2025
 */
public class CompletionProductionOrderLocators {

	/** @locatorType XPATH */
	public static final String PendingProdOrderIframe = "//iframe";// "//iframe[contains(@title,'Pend. Prod. Ord.')]";
	/** @locatorType XPATH */
	public static final String PendingProdOrderIframeCloseIcon = "//h1[text()='Pend. Prod. Ord.']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String PendingProdOrderSearchBar = "//input[@id='PEND_search_field']";
	/** @locatorType XPATH */
	public static final String PendingProdOrderSearchGoBtn = "//button[@id='PEND_search_button']";
	/** @locatorType XPATH */
	public static final String PendingInsideCompletionTable = "(//table[contains(@aria-label,'PEND_PROD_PROC_IS')])[2]";

	/** @locatorType XPATH */
	public static final String PendingProdOrder_ProdNoTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PendingInsideCompletionTable,
				"Prod. Ord. No");
	}

	/** @locatorType XPATH */
	public static final String PendingProdOrder_ItemTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PendingInsideCompletionTable, "Item");
	}

	/** @locatorType XPATH */
	public static final String PendingProdOrder_ProcessTableData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PendingInsideCompletionTable, "Process ID");
	}

	/** @locatorType XPATH */
	public static final String PendingProdOrder_SelectCheckboxTableData(String ProdNo) {
		return "(//div[@id='PEND_content']//table/tbody/tr/td[text()='" + ProdNo + "']/../td/input)[2]";
	}

	/** @locatorType XPATH */
	public static final String CreateCompletionProcessBtn = "//button[@data-testid='CreateCompletionProcessBtn']";
	/** @locatorType XPATH */
	public static final String CreateCompletionProcessOkBtn = "//button[@data-testid='CreateCompletionProcessOkBtn']";
	/** @locatorType XPATH */
	public static final String ComplProdOrderAllocateDeAllocateBtn = "//span[text()='Allocate/Deallocate']/parent::button";
	/** @locatorType XPATH */
	public static final String ComplProdOrderShiftLovBtn = "//input[@data-testid='ComplProdOrderShiftLovBtn']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String ComplProdOrderDisplayProcess = "//span[@data-testid='ComplProdOrderDisplayProcess']";
	/** @locatorType XPATH */
	public static final String ComplProdOrderDisplayCompletionProdOrderNo = "//span[@data-testid='ComplProdOrderDisplayCompletionProdOrderNo']";
	/** @locatorType XPATH */
	public static final String ComplProdOrderDisplayProdutionOrderNo = "//span[@data-testid='ComplProdOrderDisplayProdutionOrderNo']";
	/** @locatorType XPATH */
	public static final String ComplProdOrderCompQuantityInput = "//input[@data-testid='ComplProdOrderCompQuantityInput']";
	/** @locatorType XPATH */
	public static final String ComplProdOrderAcceptQuantityInput = "//input[@data-testid='ComplProdOrderAcceptQuantityInput']";
	/** @locatorType XPATH */
	public static final String ConsumptionWH_TableData = "//div[@id='consumption_ig_content_container']//table/tbody/tr/td[2]";
	/** @locatorType XPATH */
	public static final String ComplProdOrderRejectQuantityInput = "//input[@data-testid='ComplProdOrderRejectQuantityInput']";
	/** @locatorType XPATH */
	public static final String ComplProdHeaderSaveIcon = "//button[@data-testid='ComplProdHeaderSaveIcon']";
	/** @locatorType XPATH */
	public static final String ComplProdPostBtn = "//button[@data-testid='ComplProdPostBtn']";
	/** @locatorType XPATH */
	public static final String CompletionProcessPendingIcon = "//button[@data-testid='CompletionProcessPendingIcon']";
	/** @locatorType XPATH */
	public static final String CompletionProcessSingleLabel = "//label[text()='Single Process']";
	/** @locatorType XPATH */
	public static final String CompletionProcessMultipleLabel = "//label[text()='Multi Process']";
	/** @locatorType XPATH */
	public static final String ProductionOrderNumber = "//input[@data-testid='ProductionOrderNumber']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String SearchButton = "//button[@data-testid='SearchButton']";
	/** @locatorType XPATH */
	public static final String SelectStatus = "//select[@data-testid='SelectStatus']";
	/** @locatorType XPATH */
	public static final String TransLovButton = "//input[@data-testid='TransLovButton']/following-sibling::button";

	/** @locatorType XPATH */
	public static final String CompletionTransNumber(String Number) {
		return "//div[@id='SL_data_panel']//table[contains(@id,'orig')]//a[text()='" + Number + "']";
	}

	/** @locatorType XPATH */
	public static final String MultiProc_PendingIcon = "//button[@data-testid='MultiProc_PendingIcon']";
	/** @locatorType XPATH */
	public static final String MultiProc_PendingIframe = "//iframe[@title='Pend. Prod. Ord.']";
	/** @locatorType XPATH */
	public static final String MultiProc_SelectAllCheckBox = "//div[@id='PEND_content']//table/tbody/tr/th//input[@id='check_all']";
	/** @locatorType XPATH */
	public static final String MultiProc_ShiftLovBtn = "//input[@data-testid='MultiProc_ShiftLovBtn']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String MultiProc_SaveHeaderBtn = "//button[@data-testid='MultiProc_SaveHeaderBtn']";
	/** @locatorType XPATH */
	public static final String MultiProc_PostBtn = "//button[@data-testid='MultiProc_PostBtn']";
	/** @locatorType XPATH */
	public static final String MultiProc_DisplayComplProdOrderNo = "//span[@data-testid='MultiProc_DisplayComplProdOrderNo']";

	/** @locatorType XPATH */
	public static final String ProcessName() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PendingInsideCompletionTable,
				"Process Desc.");
	}

	/** @locatorType XPATH */
	public static final String OrderQty() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PendingInsideCompletionTable, "Order Qty");
	}

	/** @locatorType XPATH */
	public static final String QueueQty() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PendingInsideCompletionTable, "Queue Qty.");
	}

	/** @locatorType XPATH */
	public static final String InsideQty() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PendingInsideCompletionTable,
				"Inside Qty.");
	}

	/** @locatorType XPATH */
	public static final String CompQty() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(PendingInsideCompletionTable, "Comp. Qty.")
				+ "/input";
	}

	/** @locatorType XPATH */
	public static final String OperatorLovButton = "//button[@id='P19631031010_PT_OPT_ID_lov_btn']";
	/** @locatorType XPATH */
	public static final String MachineLovButton = "//button[@id='P19631031010_PT_MACH_ID_lov_btn']";
	/** @locatorType XPATH */
	public static final String ResourcesTab = "//label[@for='P19631031010_TAB_2']";
	/** @locatorType XPATH */
	public static final String ResourcesMachineTab = "//label[@for='P19631031010_RESOURCES_0']";
	/** @locatorType XPATH */
	public static final String ResourcesLabourTab = "//label[@for='P19631031010_RESOURCES_1']";
	/** @locatorType XPATH */
	public static final String MachineLineAmount = "//div[@id='ST_MACH_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[7]";
	/** @locatorType XPATH */
	public static final String MachineUnitRate = "//div[@id='ST_MACH_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[6]";
	/** @locatorType XPATH */
	public static final String MachineUnit = "//div[@id='ST_MACH_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[5]";
	/** @locatorType XPATH */
	public static final String LabourQtyHrs = "//div[@id='LAB_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[6]";
	/** @locatorType XPATH */
	public static final String LabourUnitRate = "//div[@id='LAB_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[7]";
	/** @locatorType XPATH */
	public static final String LabourLineAmount = "//div[@id='LAB_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[8]";
	/** @locatorType XPATH */
	public static final String HeaderProcessName = "//span[@data-testid='ComplProdOrderDisplayProcess']";
	/** @locatorType XPATH */
	public static final String MultiProdNo = "//button[@id='P80_FIND_PROD_NO_lov_btn']";
	/** @locatorType XPATH */
	public static final String PendingSearchButtonMulti = "//button[@id='B807324816757716737']";
	/** @locatorType XPATH */
	public static final String CreateCompProc = "//button[@id='B1']";
	/** @locatorType XPATH */
	public static final String MultipleCreateCompletionProcessOkBtn = "//button[@data-testid='CreateCompletionProcessOkBtn']";
	/** @locatorType XPATH */
	public static final String MultipleProceedRestOprYesBtn = "//button[text()='Yes']";
	/** @locatorType XPATH */
	public static final String MultipleProceedRestOprNoBtn = "//button[text()='No']";
	/** @locatorType XPATH */
	public static final String MultipleTransLovButton = "//button[@id='P196310316_TRANS_NO_lov_btn']";
	/** @locatorType XPATH */
	public static final String MultipleSelectStatus = "//select[@id='P196310316_STATUS']";
	/** @locatorType XPATH */
	public static final String MultipleSearchButton = "//button[@id='B136234895074248868']";
	/** @locatorType XPATH */
	public static final String MultipleMachineAddButton = "//button[@id='B1243193033311894963']";
	/** @locatorType XPATH */
	public static final String MultipleMachine = "//button[@id='B1243193033311894963']";

	/** @locatorType XPATH */
	public static final String ProcessHeader = "//h1[text()='Process Details']";
	/** @locatorType XPATH */
	public static final String CloseButton = "(//iframe[@scrolling='auto'])[2]/../../div[1]/h1/following::button[@title='Close']";
	/** @locatorType XPATH */
	public static final String MultiplePendingMovingLocId = "//input[@data-testid='SingleLocId']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String MultipleProcessDetailIframe = "(//iframe[@scrolling='auto'])[2]";

	/** @locatorType XPATH */
	public static final String MultipleProcessDetails(String ProdNo) {
		return "//td[text()='" + ProdNo + "']/../td[22]/a";
	}

	/** @locatorType XPATH */
	public static final String MultipleProductionOrderNo(String ProdNo) {
		return "//td[text()='" + ProdNo + "']";
	}

	/** @locatorType XPATH */
	public static final String MultipleProcessCreateCheckBox(String No) {
		return "//table[contains(@aria-label,'Process Details')]/tbody//td[text()='" + No
				+ "']/../td/input[@type='checkbox']";
	}

	/** @locatorType XPATH */
	public static final String MultipleProcessCheckBox(int No) {
		return "//table[@id='2731233389405342880_orig']/tbody/tr[" + No + "]/td[7]/input";
	}

	/** @locatorType XPATH */
	public static final String MaterialUnitCost(int i) {
		return "//div[@id='consumption_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[14]";
	}

	/** @locatorType XPATH */
	public static final String TotalMaterialUnitCost = "//div[@id='consumption_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[14]";
	/** @locatorType XPATH */
	public static final String MaterialNextButton = "//div[@aria-label='Pagination']/button[@title='Next']";
	/** @locatorType XPATH */
	public static final String CostTab = "//label[@for='P19631031001_TABS_5']";
	/** @locatorType XPATH */
	public static final String CostElementTab = "//label[@for='P19631031001_COST_0']";
	/** @locatorType XPATH */
	public static final String SubElementTab = "//label[@for='P19631031001_COST_1']";
	/** @locatorType XPATH */
	public static final String CostElementTable = "//table[@id='2709599923838646133_orig']/tbody/tr/td[5]";
	/** @locatorType XPATH */
	public static final String SubElementTableUnitCost = "//table[@id='2709600758706646142_orig']/tbody/tr/td[5]";
	/** @locatorType XPATH */
	public static final String SubElementTableValue = "//table[@id='2709600758706646142_orig']/tbody/tr/td[6]";

	/** @locatorType XPATH */
	public static final String CostElementTable(int i) {
		return "//table[@id='2709599923838646133_orig']/tbody/tr[" + i + "]/td[5]";
	}

	/** @locatorType XPATH */
	public static final String SubElementTableUnitCost(int i) {
		return "//table[@id='2709600758706646142_orig']/tbody/tr[" + i + "]/td[5]";
	}

	/** @locatorType XPATH */
	public static final String SubElementTableValue(int i) {
		return "//table[@id='2709600758706646142_orig']/tbody/tr[" + i + "]/td[6]";
	}

	/** @locatorType XPATH */
	public static final String SingleLocId = "//button[@id='P33_LOC_ID_lov_btn']";
	/** @locatorType XPATH */
	public static final String Consumption = "//input[@data-display='Consumption']/following-sibling::label";
	/** @locatorType XPATH */
	public static final String FinishProcessName = "//span[@id='P19631031001_PT_PROCESS_DISPLAY']";
	/** @locatorType XPATH */
	public static final String CompMultipleProcessName = "//div[@id='R6395198950450638961_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[3]";
	/** @locatorType XPATH */
	public static final String CompMultipleMachineAddButton = "//button[@title='Add']";
	/** @locatorType XPATH */
	public static final String CompMultipleMachineSaveButton = "//button[@id='B1243193467708894964']";
	/** @locatorType XPATH */
	public static final String CompMultipleMachineTable = "//div[@id='ST_MACH_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[1]";
	/** @locatorType XPATH */
	public static final String CompMultipleMachineNameTable = "//div[@id='ST_MACH_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[2]";
	/** @locatorType XPATH */
	public static final String CompMultipleMachineLovButton = "//input[@id='C2761760820481593017']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String CompMultipleLabourAddButton = "//button[@title='Add Labar']";
	/** @locatorType XPATH */
	public static final String CompMultipleLabourSaveButton = "//button[@title='Save Labour']";
	/** @locatorType XPATH */
	public static final String CompMultipleLabourTable = "//div[@id='LAB_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[2]";
	/** @locatorType XPATH */
	public static final String CompMultipleLabourNameTable = "//div[@id='LAB_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[2]";
	/** @locatorType XPATH */
	public static final String CompMultipleLabourLovButton = "//input[@id='C2760687462192087715']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String ComplMultipleConsumptionTab = "//input[@id='P1963103102_TAB_1']/following-sibling::label";
	/** @locatorType XPATH */
	public static final String ComplMultipleConsumptionNextButton = "//div[@id='consumption_ig_grid_vc_floatingItem_dialog']/following::div[@aria-label='Pagination']/button[@title='Next']";
	/** @locatorType XPATH */
	public static final String Pend_ProdOrderLovBtn = "//button[@id='P33_FIND_PROD_NO_lov_btn']";
	/** @locatorType XPATH */
	public static final String PendingSearchBtn = "//span[text()='Search']/parent::button";
	/** @locatorType XPATH */
	public static final String SearchResultStatusData = "//table[contains(@aria-label,'Result (s)')]//tbody/tr/td/div[contains(@style,'color')]";
	/** @locatorType XPATH */
	public static final String Pending_TableSelectAllCheckBox = "(//table[contains(@aria-label,'PEND_PROD_PROC')]//tr/td//input)[2]";
	/** @locatorType XPATH */
	public static final String Pending_TableShowMyRecordBtn = "(//span[text()='Show My Record']/parent::button)[1]";
	/** @locatorType XPATH */
	public static final String Pending_TableShowMyRecordBtnClicked = "(//span[text()='Show My Record']/parent::button)[2]";
	/** @locatorType XPATH */
	public static final String Pending_TableNoDataSelected = "//div[@class='a-IRR-noDataMsg-icon']";
	/** @locatorType XPATH */
	public static final String Pending_MRCreateBtn = "//button[@data-testid='CompletionProcessPendingIconmr']";
	/** @locatorType XPATH */
	public static final String Pending_MRCreateShowRecordBtn = "(//button[@data-testid='CreateCompletionProcessBtn']/following-sibling::button)[1]";
	/** @locatorType XPATH */
	public static final String Pending_MRCreateShowRecordClickedBtn = "(//button[@data-testid='CreateCompletionProcessBtn']/following-sibling::button)[2]";
	/** @locatorType XPATH */
	public static final String PendingMRCreateIframe = "//iframe[@title='Pend. Prod. Ord. MR']";
	/** @locatorType XPATH */
	public static final String PendingMRFindProdNoLov = "//*[@data-testid='Pend_ProdOrderLovBtn']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String PendingMRFindSearchBtn = "//button[@data-testid='PendingProdOrderSearchGoBtn']";
	/** @locatorType XPATH */
	public static final String PendingMRSelectAllBtn = "//table[@data-testid='L_Result_Title']//th//input[@id='check_all']";

	/** @locatorType XPATH */
	public static final String PendingMRSelectAllBtn(String prodNo) {
		return "//table/tbody//td[text()='" + prodNo + "']/../td/input[contains(@id,'checkbox')]";
	}

	/** @locatorType XPATH */
	public static final String PendingMRQuantityInut(String prodNo) {
		return "//table/tbody//td[text()='" + prodNo + "']/../td/input[contains(@id,'inputField')]";
	}

	/** @locatorType XPATH */
	public static final String PendingMRProcessDetailIcon(String prodNo) {
		return "(//table/tbody//td[text()='" + prodNo + "']/../td//span[@class='fa fa-file-text-o'])[1]";
	}

	/** @locatorType XPATH */
	public static final String PendingMRProcessDetailSelectIcon(String ProcessName) {
		return "//table[contains(@aria-label,'Process Details')]/tbody//td[text()='" + ProcessName + "']/../td/input";
	}

	/** @locatorType XPATH */
	public static final String PendingMRProcessDetailSelectAllIcon = "//table[contains(@aria-label,'Process Details')]/tbody//td//input";
	/** @locatorType XPATH */
	public static final String PendingMRProcessDetailsIframe = "//iframe[contains(@src,'process-details')]";
	/** @locatorType XPATH */
	public static final String CreateMRButton = "//button[@data-testid='CreateCompletionProcessBtn']";
	/** @locatorType XPATH */
	public static final String CreateMRLocIdLov = "//*[@data-testid='SingleLocId']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String CreateMROkButton = "//button[@data-testid='CreateCompletionProcessOkBtn']";
	/** @locatorType XPATH */
	public static final String Pending_InsideProcCreateBtn = "//div[@id='PEND_ORD']//button[@data-testid='CompletionProcessPendingIcon']";
	/** @locatorType XPATH */
	public static final String AllowExceptionButton = "//span[text()='Allow Exception']/parent::button";
	/** @locatorType XPATH */
	public static final String AllowExceptionIframe = "//iframe[@title='Prod. Comp. Entry (Process Wise) Allow Exception']";
	/** @locatorType XPATH */
	public static final String PendingSearchInput = "//input[@id='PEND_search_field']";
	/** @locatorType XPATH */
	public static final String PendingSearchGoButton = "//button[@id='PEND_search_button']";
	/** @locatorType XPATH */
	public static final String AllowExceptionPostButton = "//span[text()='Post']/parent::button";
	/** @locatorType XPATH */
	public static final String ActualConsTableDataInLines = "//div[@id='consumption_ig_grid_vc']//table/tbody/tr/td[11]";
	/** @locatorType XPATH */
	public static final String ActualConsTableNextIconPagination = "//div[@aria-label='Pagination']//button[@title='Next']";
	/** @locatorType XPATH */
	public static final String ProdCompRejectionProcessInput = "//input[contains(@id,'PT_PRE_PROCESS_REJ')]";
	/** @locatorType XPATH */
	public static final String ProdCompAcceptQtyInput = "//input[contains(@id,'PT_ACCEPT_QTY')]";
	/** @locatorType XPATH */
	public static final String ProdCompRejectPrimaryQtyInput = "//input[contains(@id,'PT_PRIM_REJ_QTY')]";
	/** @locatorType XPATH */
	public static final String ProdCompRejectSecondaryQtyInput = "//input[contains(@id,'PT_SECON_REJ_QTY')]";
	/** @locatorType XPATH */
	public static final String ProdCompAddDefectButton = "//button[@title='Add Defect']";
	/** @locatorType XPATH */
	public static final String ProdCompAddScrapButton = "//button[@title='Scrap Add']";
	/** @locatorType XPATH */
	public static final String ProdCompSaveDefectButton = "//button[@title='Save Defect']";
	/** @locatorType XPATH */
	public static final String ProdCompSaveScrapButton = "//button[@title='Scrap Save']";
	/** @locatorType XPATH */
	public static final String ProdCompDefectTypeTableData = "(//div[@id='defect_ig_grid_vc']//table//tbody/tr/td[2])[1]";
	/** @locatorType XPATH */
	public static final String ProdCompDefectTypeTableLovBtn = "(//div[@id='defect_ig_grid_vc']//table//tbody/tr/td[2])[1]//button";
	/** @locatorType XPATH */
	public static final String ProdCompDefectQtyTableData = "(//div[@id='defect_ig_grid_vc']//table//tbody/tr/td[3])[1]";
	/** @locatorType XPATH */
	public static final String ProdCompDefectQtyTableInput = "(//div[@id='defect_ig_grid_vc']//table//tbody/tr/td[3])[1]//input";
	/** @locatorType XPATH */
	public static final String ProdCompRepairQtyTableData = "(//div[@id='defect_ig_grid_vc']//table//tbody/tr/td[4])[1]";
	/** @locatorType XPATH */
	public static final String ProdCompRepairQtyTableInput = "(//div[@id='defect_ig_grid_vc']//table//tbody/tr/td[4])[1]//input";
	/** @locatorType XPATH */
	public static final String ProdCompScrapQtyTableData = "(//div[@id='defect_ig_grid_vc']//table//tbody/tr/td[5])[1]";
	/** @locatorType XPATH */
	public static final String ProdCompScrapQtyTableInput = "(//div[@id='defect_ig_grid_vc']//table//tbody/tr/td[5])[1]//input";
	/** @locatorType XPATH */
	public static final String ProdCompScrapByProductItemTableData = "(//div[@id='SCRAP_ig_grid_vc']//table//tbody/tr/td[2])[1]";
	/** @locatorType XPATH */
	public static final String ProdCompScrapByProductItemTableLovBtn = "(//div[@id='SCRAP_ig_grid_vc']//table//tbody/tr/td[2])[1]//button";
	/** @locatorType XPATH */
	public static final String ProdCompScrapByProductWHTableData = "(//div[@id='SCRAP_ig_grid_vc']//table//tbody/tr/td[5])[1]";
	/** @locatorType XPATH */
	public static final String ProdCompScrapByProductWHTableLovBtn = "(//div[@id='SCRAP_ig_grid_vc']//table//tbody/tr/td[5])[1]//button";
	/** @locatorType XPATH */
	public static final String ProdCompScrapByProductQtyTableData = "(//div[@id='SCRAP_ig_grid_vc']//table//tbody/tr/td[8])[1]";
	/** @locatorType XPATH */
	public static final String ProdCompScrapByProductQtyTableInput = "(//div[@id='SCRAP_ig_grid_vc']//table//tbody/tr/td[8])[1]//input";
	/** @locatorType XPATH */
	public static final String ProdCompScrapByProductAddConsCheckBox = "(//div[@id='SCRAP_ig_grid_vc']//table//tbody/tr/td[9])[1]//input[@type='checkbox']";

	/** @locatorType XPATH */
	public static final String AllowExceptionSelectCheckBox(String ProdOrder) {
		return "//table[contains(@aria-label,'PROD_TRANS_HIST_VIEW')]//tbody/tr/td[text()='" + ProdOrder
				+ "']/../td/input";
	}

	public static final String ProdCompLineActualConsumptionQtyData = "(//div[@id='consumption_ig_grid_vc']//table//tbody/tr/td[11])[1]";
	/** @locatorType XPATH */
	public static final String ProdCompLineActualConsumptionQtyInput = "(//div[@id='consumption_ig_grid_vc']//table//tbody/tr/td[11]//input)[1]";
	/** @locatorType XPATH */
	public static final String ProdCompConsumptionAddIcon = "//button[@title='Cons Add']";
	/** @locatorType XPATH */
	public static final String ProdCompConsumptionSaveIcon = "//button[@title='Cons Add']/following-sibling::button[@title='Save']";
	/** @locatorType XPATH */
	public static final String ProdCompLineRequiredQtyData = "(//div[@id='consumption_ig_grid_vc']//table//tbody/tr/td[10])[1]";
	/** @locatorType XPATH */
	public static final String ProdCompLineRequiredQtyInput = "(//div[@id='consumption_ig_grid_vc']//table//tbody/tr/td[10]//input)[1]";
	/** @locatorType XPATH */
	public static final String ProdCompLineConsumptionWHData = "(//div[@id='consumption_ig_grid_vc']//table//tbody/tr/td[2])[1]";
	/** @locatorType XPATH */
	public static final String ProdCompLineConsumptionWHLovBtn = "(//div[@id='consumption_ig_grid_vc']//table//tbody/tr/td[2]//button)[1]";
	/** @locatorType XPATH */
	public static final String ProdCompLineItemsData = "(//div[@id='consumption_ig_grid_vc']//table//tbody/tr/td[3])[1]";
	/** @locatorType XPATH */
	public static final String ProdCompLineItemsLovBtn = "(//div[@id='consumption_ig_grid_vc']//table//tbody/tr/td[3]//button)[1]";
	/** @locatorType XPATH */
	public static final String ProdCompLineUOMData = "(//div[@id='consumption_ig_grid_vc']//table//tbody/tr/td[6])[1]";
	/** @locatorType XPATH */
	public static final String ProdCompLineUOMLovBtn = "(//div[@id='consumption_ig_grid_vc']//table//tbody/tr/td[6]//button)[1]";

}
