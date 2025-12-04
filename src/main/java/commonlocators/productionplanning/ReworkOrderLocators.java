package commonlocators.productionplanning;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks November 11, 2025
 */
public class ReworkOrderLocators {
	/** @locatorType Xpath */
	public static final String ReworkPendingIcon = "//button[@data-testid='Rework_Pending_Icon']";
	/** @locatorType Xpath */
	public static final String ReworkProdButton = "//button[@data-testid='Rework_Production_btn']";
	/** @locatorType Xpath */
	public static final String RewordProdPendingSearchBtn = "//button[@data-testid='RW_PROD_Search_Btn']";
	/** @locatorType Xpath */
	public static final String ReworkProdCreateIframe = "//iframe[@title='Quality Insp.- Cre. Rtn/Rwk.(Prod.)']";
	/** @locatorType Xpath */
	public static final String ReworkFindProdNoLov = "//input[@data-testid='RW_PROD_ORD_lov']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String ReworkProdCreateOkButton = "//button[@data-testid='RW_PROD_Date_ok_Btn']";
	/** @locatorType Xpath */
	public static final String ReworkProdShowMyRecordBtn = "//button[@id='B3389189866954864728']";
	/** @locatorType Xpath */
	public static final String ReworkProdSaveBtn = "//button[@data-testid='RW_PROD_Save_Btn']";
	/** @locatorType Xpath */
	public static final String ReworkProdCreationBtn = "//button[@data-testid='RW_PROD_Create_Btn']";
	/** @locatorType Xpath */
	public static final String ReworkPendingPrimaryRejQuantity = "(//div[@id='myGridRegion_ig_grid_vc']//table//tr/td[6])[1]";
	/** @locatorType Xpath */
	public static final String ReworkPendingPrimaryInsideQuantity = "(//div[@id='myGridRegion_ig_grid_vc']//table//tr/td[7])[1]";
	/** @locatorType Xpath */
	public static final String ReworkPendingPrimaryInsideQuantityInput = "(//div[@id='myGridRegion_ig_grid_vc']//table//tr/td[7])[1]//input";
	/** @locatorType Xpath */
	public static final String ReworkPendingPrimaryOutsideQuantity = "(//div[@id='myGridRegion_ig_grid_vc']//table//tr/td[8])[1]";
	/** @locatorType Xpath */
	public static final String ReworkPendingPrimaryOutsideQuantityInput = "(//div[@id='myGridRegion_ig_grid_vc']//table//tr/td[8])[1]//input";
	/** @locatorType Xpath */
	public static final String ReworkPendingSelectCheckBox = "(//div[@id='myGridRegion_ig_grid_vc']//table//tr/td[12])[1]//input";
	/** @locatorType Xpath */
	public static final String ReworkPendingPrimarySupplier = "(//div[@id='myGridRegion_ig_grid_vc']//table//tr/td[9])[1]";
	/** @locatorType Xpath */
	public static final String ReworkPendingPrimarySupplierLov = "(//div[@id='myGridRegion_ig_grid_vc']//table//tr/td[9])[1]//button";
	/** @locatorType Xpath */
	public static final String ReworkPendingSecondaryRejQuantity = "(//div[@id='myGridRegion_ig_grid_vc']//table//tr/td[13])[1]";
	/** @locatorType Xpath */
	public static final String ReworkPendingSecondaryInsideQuantity = "(//div[@id='myGridRegion_ig_grid_vc']//table//tr/td[14])[1]";
	/** @locatorType Xpath */
	public static final String ReworkPendingSecondaryInsideQuantityInput = "(//div[@id='myGridRegion_ig_grid_vc']//table//tr/td[14])[1]//input";
	/** @locatorType Xpath */
	public static final String ReworkPendingSecondaryOutsideQuantity = "(//div[@id='myGridRegion_ig_grid_vc']//table//tr/td[15])[1]";
	/** @locatorType Xpath */
	public static final String ReworkPendingSecondaryOutsideQuantityInput = "(//div[@id='myGridRegion_ig_grid_vc']//table//tr/td[15])[1]//input";
	/** @locatorType Xpath */
	public static final String ReworkPendingSecondarySupplier = "(//div[@id='myGridRegion_ig_grid_vc']//table//tr/td[16])[1]";
	/** @locatorType Xpath */
	public static final String ReworkPendingSecondarySupplierLov = "(//div[@id='myGridRegion_ig_grid_vc']//table//tr/td[16])[1]//button";
	/** @locatorType Xpath */
	public static final String DisplayRewordOrderNo = "//span[@data-testid='RW_ORD_NO']";
	/** @locatorType Xpath */
	public static final String RewordPostButton = "//button[@data-testid='RW_Post_btn']";
	/** @locatorType Xpath */
	public static final String RewordSaveButton = "//button[@data-testid='RW_Save_btn']";

	// Rework Order Completion
	/** @locatorType Xpath */
	public static final String RewordCompPendingBtn = "//button[@data-testid='RWC_Pend_Btn']";
	/** @locatorType Xpath */
	public static final String RewordCompPendingSearchBtn = "//button[@data-testid='RWC_Pend_Search_btn']";
	/** @locatorType Xpath */
	public static final String RewordCompPendingSaveBtn = "//button[@data-testid='RWC_Pend_Save_btn']";
	/** @locatorType Xpath */
	public static final String RewordCompPendingCreateBtn = "//button[@data-testid='RWC_Pend_Create_btn']";
	/** @locatorType Xpath */
	public static final String RewordCompPendingCreateOkBtn = "//button[@data-testid='RWC_Pend_Ok_btn']";
	/** @locatorType Xpath */
	public static final String ReworkCompFindReworkNoLov = "//input[@data-testid='RWC_Pend_Ord_lov']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String ReworkCompFindProdNoLov = "//input[@data-testid='RWC_Pend_Prod_Ord_lov']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String ReworkCompSelectCheckBox = "(//div[@id='pendrework_ig_grid_vc']//table//tr/td[14])[1]//input";
	/** @locatorType Xpath */
	public static final String ReworkCompRepairQuantity = "(//div[@id='pendrework_ig_grid_vc']//table//tr/td[11])[1]";
	/** @locatorType Xpath */
	public static final String ReworkCompRepairQuantityInput = "(//div[@id='pendrework_ig_grid_vc']//table//tr/td[11])[1]//input";
	/** @locatorType Xpath */
	public static final String ReworkCompScrapQuantity = "(//div[@id='pendrework_ig_grid_vc']//table//tr/td[12])[1]";
	/** @locatorType Xpath */
	public static final String ReworkCompScrapQuantityInput = "(//div[@id='pendrework_ig_grid_vc']//table//tr/td[12])[1]//input";
	/** @locatorType Xpath */
	public static final String ReworkCompDisassembleQuantity = "(//div[@id='pendrework_ig_grid_vc']//table//tr/td[13])[1]";
	/** @locatorType Xpath */
	public static final String ReworkCompDisassembleQuantityInput = "(//div[@id='pendrework_ig_grid_vc']//table//tr/td[13])[1]//input";
	/** @locatorType Xpath */
	public static final String ReworkCompResourceUsageTab = "//label[text()='Resource Usage']";
	/** @locatorType Xpath */
	public static final String ReworkCompDisassembleDetailsTab = "//label[text()='Disassemble Details']";
	/** @locatorType Xpath */
	public static final String ReworkCompResourceAddBtn = "//button[@data-testid='RWC_Res_Add_btn']";
	/** @locatorType Xpath */
	public static final String ReworkCompRWC_DisassembleAddBtn = "//button[@data-testid='RWC_Dis_Add_Btn']";
	/** @locatorType Xpath */
	public static final String ReworkCompRWC_DisassembleSaveBtn = "//button[@data-testid='RWC_Dis_Save_Btn']";
	/** @locatorType Xpath */
	public static final String ReworkCompResourceSaveBtn = "//button[@data-testid='RWC_Res_Save_btn']";
	/** @locatorType Xpath */
	public static final String ReworkCompAllocateBtn = "//button[@data-testid='RWC_Alloc_btn']";
	/** @locatorType Xpath */
	public static final String ReworkCompSaveBtn = "//button[@data-testid='RWC_Save_btn']";
	/** @locatorType Xpath */
	public static final String ReworkCompPostBtn = "//button[@data-testid='RWC_Post_btn']";
	/** @locatorType Xpath */
	public static final String ReworkCompResourceRWSeqNo = "(//div[@id='Resource_ig_grid_vc']//table//tr/td[2])[1]";
	/** @locatorType Xpath */
	public static final String ReworkCompResourceRWSeqNoLov = "(//div[@id='Resource_ig_grid_vc']//table//tr/td[2])[1]//button";
	/** @locatorType Xpath */
	public static final String ReworkCompWorkCenter = "(//div[@id='Resource_ig_grid_vc']//table//tr/td[3])[1]";
	/** @locatorType Xpath */
	public static final String ReworkCompWorkCenterLov = "(//div[@id='Resource_ig_grid_vc']//table//tr/td[3])[1]//button";
	/** @locatorType Xpath */
	public static final String ReworkCompProcess = "(//div[@id='Resource_ig_grid_vc']//table//tr/td[4])[1]";
	/** @locatorType Xpath */
	public static final String ReworkCompProcessLov = "(//div[@id='Resource_ig_grid_vc']//table//tr/td[4])[1]//button";
	/** @locatorType Xpath */
	public static final String ReworkCompResources = "(//div[@id='Resource_ig_grid_vc']//table//tr/td[5])[1]";
	/** @locatorType Xpath */
	public static final String ReworkCompResourcesLov = "(//div[@id='Resource_ig_grid_vc']//table//tr/td[5])[1]//button";
	/** @locatorType Xpath */
	public static final String ReworkCompFromDate = "(//div[@id='Resource_ig_grid_vc']//table//tr/td[6])[1]";
	/** @locatorType Xpath */
	public static final String ReworkCompFromDateInput = "(//div[@id='Resource_ig_grid_vc']//table//tr/td[6])[1]//input";
	/** @locatorType Xpath */
	public static final String ReworkCompToDate = "(//div[@id='Resource_ig_grid_vc']//table//tr/td[7])[1]";
	/** @locatorType Xpath */
	public static final String ReworkCompToDateInput = "(//div[@id='Resource_ig_grid_vc']//table//tr/td[7])[1]//input";
	/** @locatorType Xpath */
	public static final String ReworkCompUnits = "(//div[@id='Resource_ig_grid_vc']//table//tr/td[9])[1]";
	/** @locatorType Xpath */
	public static final String ReworkCompHrlyRate = "(//div[@id='Resource_ig_grid_vc']//table//tr/td[10])[1]";
	/** @locatorType Xpath */
	public static final String ReworkCompExtRate = "(//div[@id='Resource_ig_grid_vc']//table//tr/td[11])[1]";
	/** @locatorType Xpath */
	public static final String ReworkDisAssRWSeqNo = "(//div[@id='dis_asm_ig_grid_vc']//table//tr/td[2])[1]";
	/** @locatorType Xpath */
	public static final String ReworkDisAssRWSeqNoLov = "(//div[@id='dis_asm_ig_grid_vc']//table//tr/td[2])[1]//button";
	/** @locatorType Xpath */
	public static final String ReworkDisAssWarehouse = "(//div[@id='dis_asm_ig_grid_vc']//table//tr/td[3])[1]";
	/** @locatorType Xpath */
	public static final String ReworkDisAssWarehouseLov = "(//div[@id='dis_asm_ig_grid_vc']//table//tr/td[3])[1]//button";
	/** @locatorType Xpath */
	public static final String ReworkDisAssItem = "(//div[@id='dis_asm_ig_grid_vc']//table//tr/td[4])[1]";
	/** @locatorType Xpath */
	public static final String ReworkDisAssItemLov = "(//div[@id='dis_asm_ig_grid_vc']//table//tr/td[4])[1]//button";
	/** @locatorType Xpath */
	public static final String ReworkDisAssAcceptQnty = "(//div[@id='dis_asm_ig_grid_vc']//table//tr/td[8])[1]";
	/** @locatorType Xpath */
	public static final String ReworkDisAssAcceptQntyInput = "(//div[@id='dis_asm_ig_grid_vc']//table//tr/td[8])[1]//input";
	/** @locatorType Xpath */
	public static final String ReworkDisAssRejectQnty = "(//div[@id='dis_asm_ig_grid_vc']//table//tr/td[9])[1]";
	/** @locatorType Xpath */
	public static final String ReworkDisAssRejectQntyInput = "(//div[@id='dis_asm_ig_grid_vc']//table//tr/td[9])[1]//input";
	/** @locatorType Xpath */
	public static final String ReworkDisAssScrapQnty = "(//div[@id='dis_asm_ig_grid_vc']//table//tr/td[10])[1]";
	/** @locatorType Xpath */
	public static final String ReworkDisAssScrapQntyInput = "(//div[@id='dis_asm_ig_grid_vc']//table//tr/td[10])[1]//input";
	/** @locatorType Xpath */
	public static final String ReworkDisAssUnitCostQnty = "(//div[@id='dis_asm_ig_grid_vc']//table//tr/td[11])[1]";
	/** @locatorType Xpath */
	public static final String ReworkDisAssUnitCostQntyInput = "(//div[@id='dis_asm_ig_grid_vc']//table//tr/td[11])[1]//input";
	/** @locatorType Xpath */
	public static final String ReworkDisAssLotNo = "(//div[@id='dis_asm_ig_grid_vc']//table//tr/td[12])[1]";
	/** @locatorType Xpath */
	public static final String ReworkDisAssLotNoInput = "(//div[@id='dis_asm_ig_grid_vc']//table//tr/td[12])[1]//input";
}
