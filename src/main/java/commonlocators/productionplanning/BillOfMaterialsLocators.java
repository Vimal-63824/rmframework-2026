package commonlocators.productionplanning;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks March 11, 2025
 */
public class BillOfMaterialsLocators {
	/** @locatorType Xpath */
	public static final String BOM_FindSearchBtn = "//button[@data-testid='BOM_FindSearchBtn']";
	/** @locatorType Xpath */
	public static final String Find_BOMNoLovBtn = "//input[@data-testid='Find_BOMNoLovBtn']/following-sibling::button";

	/** @locatorType Xpath */
	public static final String Find_BOMStatusTableData(String BOMNo) {
		return "//table/tbody/tr/td/a[text()='" + BOMNo + "']/../../td/div[contains(@style,'color')]";
	}

	/** @locatorType Xpath */
	public static final String BOMNoTableData(String BOMNo) {
		return "(//table/tbody/tr/td/a[text()='" + BOMNo + "'])[1]";
	}

	/** @locatorType Xpath */
	public static final String BOM_CreateBtnPlusIcon = "//button[@data-testid='BOM_CreateBtnPlusIcon']";
	/** @locatorType Xpath */
	public static final String CreateBOMIframe = "//iframe[@title='Create BOM']";
	/** @locatorType Xpath */
	public static final String CreateBOM_UnitLovBtn = "//input[@data-testid='CreateBOM_UnitLovBtn']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String CreateBOM_ItemLovBtn = "//input[@data-testid='CreateBOM_ItemLovBtn']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String CreateBOM_TickIcon = "//button[@data-testid='CreateBOM_TickIcon']";
	/** @locatorType Xpath */
	public static final String CreateBOM_BOMNameInput = "//input[@data-testid='CreateBOM_BOMNameInput']";
	/** @locatorType Xpath */
	public static final String CreateBOM_EffFromInput = "//*[@data-testid='CreateBOM_EffFromInput']/input";
	/** @locatorType Xpath */
	public static final String CreateBOM_EffToInput = "//*[@data-testid='CreateBOM_EffToInput']/input";
	/** @locatorType Xpath */
	public static final String BOMSaveHeaderIcon = "//button[@data-testid='BOMSaveHeaderIcon'][2]";
	/** @locatorType Xpath */
	public static final String BOMPostBtn = "//button[@data-testid='BOMPostBtn']";
	/** @locatorType Xpath */
	public static final String DisplayBOMNumber = "//span[@data-testid='DisplayBOMNumber']";
	/** @locatorType Xpath */
	public static final String RoutingTab_Label = "//label[text()='Routing']";
	/** @locatorType Xpath */
	public static final String RoutingAddLineBtn = "//button[@data-testid='RoutingAddLineBtn']";
	/** @locatorType Xpath */
	public static final String RoutingSaveLineBtn = "//button[@data-testid='RoutingSaveLineBtn']";
	/** @locatorType Xpath */
	public static final String RoutingProcSeqNoData = "//div[@id='ROUTING_ig_grid_vc']//table/tbody/tr[1]/td[1]";
	/** @locatorType Xpath */
	public static final String RoutingProcSeqNoInput = "//div[@id='ROUTING_ig_grid_vc']//table/tbody/tr[1]/td[1]//input";
	/** @locatorType Xpath */
	public static final String RoutingOprnNoData = "//div[@id='ROUTING_ig_grid_vc']//table/tbody/tr[1]/td[2]";
	/** @locatorType Xpath */
	public static final String RoutingOprnNoInput = "//div[@id='ROUTING_ig_grid_vc']//table/tbody/tr[1]/td[2]//input";
	/** @locatorType Xpath */
	public static final String RoutingProcessIdData = "//div[@id='ROUTING_ig_grid_vc']//table/tbody/tr[1]/td[3]";
	/** @locatorType Xpath */
	public static final String RoutingProcessIdLovBtn = "//div[@id='ROUTING_ig_grid_vc']//table/tbody/tr[1]/td[3]//button";
	/** @locatorType Xpath */
	public static final String RoutingWorkCenterData = "//div[@id='ROUTING_ig_grid_vc']//table/tbody/tr[1]/td[6]";
	/** @locatorType Xpath */
	public static final String RoutingWorkCenterLovBtn = "//div[@id='ROUTING_ig_grid_vc']//table/tbody/tr[1]/td[6]//button";
	/** @locatorType Xpath */
	public static final String RoutingTypeData = "//div[@id='ROUTING_ig_grid_vc']//table/tbody/tr[1]/td[5]";
	/** @locatorType Xpath */
	public static final String RoutingTypeSelect = "//div[@id='ROUTING_ig_grid_vc']//table/tbody/tr[1]/td[5]//select";
	/** @locatorType Xpath */
	public static final String RoutingIRFlagData = "//div[@id='ROUTING_ig_grid_vc']//table/tbody/tr[1]/td[12]";

	/** @locatorType Xpath */
	public static final String RoutingIRDiv(String ProcessName) {
		return "//td[text()='" + ProcessName + "']/../td[12]/div";
	}

	/** @locatorType Xpath */
	public static final String RoutingIRInputBox(String ProcessName) {
		return "//td[text()='" + ProcessName + "']/../td[12]//input";
	}

	/** @locatorType Xpath */
	public static final String RoutingDetailsBtnWithProcessName(String ProcessName) {
		return "//div[@id='ROUTING_ig_grid_vc']//table/tbody/tr/td[text()='" + ProcessName + "']/../td[14]/a";
	}

	/** @locatorType Xpath */
	public static final String RoutingViewDetailsIframe = "//iframe[@title='View Details']";
	/** @locatorType Xpath */
	public static final String RoutingViewDetailsIframeCloseIcon = "//h1[text()='View Details']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String RoutingViewDts_ConsumptionWHLovBtn = "//input[@data-testid='RoutingViewDts_ConsumptionWHLovBtn']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String RoutingViewDts_ReceiptWHLovBtn = "//input[@data-testid='RoutingViewDts_ReceiptWHLovBtn']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String RoutingViewDts_OkBtn = "//button[@data-testid='RoutingViewDts_OkBtn']";
	/** @locatorType Xpath */
	public static final String MaterialTab_Label = "//label[text()='Material']";
	/** @locatorType Xpath */
	public static final String MaterialAddLineBtn = "//button[@data-testid='MaterialAddLineBtn']";
	/** @locatorType Xpath */
	public static final String MaterialSaveLineBtn = "//button[@data-testid='MaterialSaveLineBtn']";
	/** @locatorType Xpath */
	public static final String MaterialProcessDescData = "//div[@id='BOM_LN_STATIC_ig_grid_vc']//table/tbody/tr[1]/td[2]";
	/** @locatorType Xpath */
	public static final String MaterialProcessDescLovBtn = "//div[@id='BOM_LN_STATIC_ig_grid_vc']//table/tbody/tr[1]/td[2]//button";
	/** @locatorType Xpath */
	public static final String MaterialItemData = "//div[@id='BOM_LN_STATIC_ig_grid_vc']//table/tbody/tr[1]/td[5]";
	/** @locatorType Xpath */
	public static final String MaterialItemLovBtn = "//div[@id='BOM_LN_STATIC_ig_grid_vc']//table/tbody/tr[1]/td[5]//button";
	/** @locatorType Xpath */
	public static final String MaterialUOMData = "//div[@id='BOM_LN_STATIC_ig_grid_vc']//table/tbody/tr[1]/td[8]";
	/** @locatorType Xpath */
	public static final String MaterialUOMSelect = "//div[@id='BOM_LN_STATIC_ig_grid_vc']//table/tbody/tr[1]/td[8]//select";
	/** @locatorType Xpath */
	public static final String MaterialRequestQntyData = "//div[@id='BOM_LN_STATIC_ig_grid_vc']//table/tbody/tr[1]/td[9]";
	/** @locatorType Xpath */
	public static final String MaterialRequestQntyInput = "//div[@id='BOM_LN_STATIC_ig_grid_vc']//table/tbody/tr[1]/td[9]//input";
	/** @locatorType Xpath */
	public static final String MaterialConventionFactorData = "//div[@id='BOM_LN_STATIC_ig_grid_vc']//table/tbody/tr[1]/td[10]";
	/** @locatorType Xpath */
	public static final String MaterialConventionFactorInput = "//div[@id='BOM_LN_STATIC_ig_grid_vc']//table/tbody/tr[1]/td[10]//input";
	/** @locatorType Xpath */
	public static final String MaterialSourceWHData = "//div[@id='BOM_LN_STATIC_ig_grid_vc']//table/tbody/tr[1]/td[11]";
	/** @locatorType Xpath */
	public static final String MaterialSourceWHSelect = "//div[@id='BOM_LN_STATIC_ig_grid_vc']//table/tbody/tr[1]/td[11]//select";

	/** @locatorType Xpath */
	public static final String MaterialSubstituteItemWithProcess(String ProcessName) {
		return "//div[@id='BOM_LN_STATIC_ig_grid_vc']//table/tbody/tr[1]/td[text()='" + ProcessName
				+ "']/../td//span[@title='Substitute']";
	}

	/** @locatorType Xpath */
	public static final String MaterialItemSubsIframe = "//iframe[@title='Substitute Item Details']";
	/** @locatorType Xpath */
	public static final String MaterialItemSubsIframeCloseIcon = "//h1[text()='Substitute Item Details']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String BORTab_Label = "//label[text()='BOR']";
	/** @locatorType Xpath */
	public static final String BORAddLineBtn = "//button[@data-testid='BORAddLineBtn']";
	/** @locatorType Xpath */
	public static final String BORSaveLineBtn = "//button[@data-testid='BORSaveLineBtn']";
	/** @locatorType Xpath */
	public static final String BORLineSnoData = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[1]";
	/** @locatorType Xpath */
	public static final String BORLineSnoInput = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[1]//input";
	/** @locatorType Xpath */
	public static final String BORProcessIdData = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[3]";
	/** @locatorType Xpath */
	public static final String BORProcessIdLovBtn = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[3]//button";
	/** @locatorType Xpath */
	public static final String BORResourceTypeData = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[5]";
	/** @locatorType Xpath */
	public static final String BORResourceTypeSelect = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[5]//select";
	/** @locatorType Xpath */
	public static final String BORResourceData = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[6]";
	/** @locatorType Xpath */
	public static final String BORResourceLovBtn = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[6]//button";
	/** @locatorType Xpath */
	public static final String BORSetupHrsData = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[9]";
	/** @locatorType Xpath */
	public static final String BORSetupHrsInput = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[9]//input";
	/** @locatorType Xpath */
	public static final String BORSetupMinsData = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[10]";
	/** @locatorType Xpath */
	public static final String BORSetupMinsInput = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[10]//input";
	/** @locatorType Xpath */
	public static final String BORSetupLotQntyData = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[11]";
	/** @locatorType Xpath */
	public static final String BORSetupLotQntyInput = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[11]//input";
	/** @locatorType Xpath */
	public static final String BORCycleTimeUnitPerHourData = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[12]";
	/** @locatorType Xpath */
	public static final String BORCycleTimeUnitPerHourInput = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[12]//input";
	/** @locatorType Xpath */
	public static final String BORCycleTimeHourData = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[13]";
	/** @locatorType Xpath */
	public static final String BORCycleTimeHourInput = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[13]//input";
	/** @locatorType Xpath */
	public static final String BORCycleTimeMinData = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[14]";
	/** @locatorType Xpath */
	public static final String BORCycleTimeMinInput = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[14]//input";
	/** @locatorType Xpath */
	public static final String BORCycleTimeSecData = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[15]";
	/** @locatorType Xpath */
	public static final String BORCycleTimeSecInput = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[15]//input";
	/** @locatorType Xpath */
	public static final String BORCycleTimeBatchQntyData = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[16]";
	/** @locatorType Xpath */
	public static final String BORCycleTimeBatchQntyInput = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[16]//input";
	/** @locatorType Xpath */
	public static final String BORCycleTimeBatchHrsData = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[17]";
	/** @locatorType Xpath */
	public static final String BORCycleTimeBatchHrsInput = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[17]//input";
	/** @locatorType Xpath */
	public static final String BORLagHrsData = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[18]";
	/** @locatorType Xpath */
	public static final String BORLagHrsInput = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[18]//input";
	/** @locatorType Xpath */
	public static final String BORPriorityData = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[19]";
	/** @locatorType Xpath */
	public static final String BORPriorityInput = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[19]//input";
	/** @locatorType Xpath */
	public static final String BORNoOfUnitsData = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[20]";
	/** @locatorType Xpath */
	public static final String BORNoOfUnitsInput = "//div[@id='BOR_ig_grid_vc']//table/tbody/tr[1]/td[20]//input";
	/** @locatorType Xpath */
	public static final String ScrapByProductTab_Label = "//label[text()='Scrap / By Product']";
	/** @locatorType Xpath */
	public static final String ScrapProductAddLineBtn = "//button[@data-testid='ScrapProductAddLineBtn']";
	/** @locatorType Xpath */
	public static final String ScrapProductSaveLineBtn = "//button[@data-testid='ScrapProductSaveLineBtn']";
	/** @locatorType Xpath */
	public static final String ScrapProductLineSnoData = "//div[@id='SBP_ig_grid_vc']//table/tbody/tr[1]/td[1]";
	/** @locatorType Xpath */
	public static final String ScrapProductLineSnoInput = "//div[@id='SBP_ig_grid_vc']//table/tbody/tr[1]/td[1]//input";
	/** @locatorType Xpath */
	public static final String ScrapProductProcessDescData = "//div[@id='SBP_ig_grid_vc']//table/tbody/tr[1]/td[2]";
	/** @locatorType Xpath */
	public static final String ScrapProductProcessDescLovBtn = "//div[@id='SBP_ig_grid_vc']//table/tbody/tr[1]/td[2]//button";
	/** @locatorType Xpath */
	public static final String ScrapProductItemData = "//div[@id='SBP_ig_grid_vc']//table/tbody/tr[1]/td[5]";
	/** @locatorType Xpath */
	public static final String ScrapProductItemLovBtn = "//div[@id='SBP_ig_grid_vc']//table/tbody/tr[1]/td[5]//button";
	/** @locatorType Xpath */
	public static final String ScrapProductWarehouseData = "//div[@id='SBP_ig_grid_vc']//table/tbody/tr[1]/td[9]";
	/** @locatorType Xpath */
	public static final String ScrapProductWarehouseSelect = "//div[@id='SBP_ig_grid_vc']//table/tbody/tr[1]/td[9]//select";
	/** @locatorType Xpath */
	public static final String ScrapProductQntyWeightData = "//div[@id='SBP_ig_grid_vc']//table/tbody/tr[1]/td[10]";
	/** @locatorType Xpath */
	public static final String ScrapProductQntyWeightInput = "//div[@id='SBP_ig_grid_vc']//table/tbody/tr[1]/td[10]//input";
	/** @locatorType Xpath */
	public static final String ScrapProductProcessTypeData = "//div[@id='SBP_ig_grid_vc']//table/tbody/tr[1]/td[11]";

	/** @locatorType Xpath */
	public static final String ScrapProductProcessTypeLabel(String SelectionText) {
		return "//div[@id='C2203229019156977451']//label[text()='" + SelectionText + "']";
	}

	/** @locatorType Xpath */
	public static final String Header_BOMTypeSelect = "//select[@data-testid='Header_BOMTypeSelect']";

	// BOM Migration Std Screen Locators
	/** @locatorType Xpath */
	public static final String BOMMigrationGenerateBtn = "//button[@data-testid='BOMMigrationGenerateBtn']";
	/** @locatorType Xpath */
	public static final String FindBOMMigrationDocLovBtn = "//input[@data-testid='FindBOMMigrationDocLovBtn']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String BOMMigrationAddBtn = "//button[@data-testid='BOMMigrationAddBtn']";
	/** @locatorType Xpath */
	public static final String BOMMig_CreateIframe = "//iframe[@title='BOM Migration']";
	/** @locatorType Xpath */
	public static final String BOMMig_CreateUnitLovBtn = "//input[@data-testid='BOMMig_CreateUnitLovBtn']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String BOMMig_CreateRefInput = "//input[@data-testid='BOMMig_CreateRefInput']";
	/** @locatorType Xpath */
	public static final String BOMMig_CreateTickIcon = "//button[@data-testid='BOMMig_CreateTickIcon']";
	/** @locatorType Xpath */
	public static final String BOMMig_DisplayDocNumber = "//input[@data-testid='BOMMig_DisplayDocNumber']";
	/** @locatorType Xpath */
	public static final String BOMMig_UploadIcon = "//button[@data-testid='BOMMig_UploadIcon']";
	/** @locatorType Xpath */
	public static final String BOMMig_UploadInput = "//*[@data-testid='BOMMig_UploadInput']/input";
	/** @locatorType Xpath */
	public static final String BOMMig_UploadBtn = "//button[@data-testid='BOMMig_UploadBtn']";
	/** @locatorType Xpath */
	public static final String BOMMig_LoadRoutingBtn = "//button[@data-testid='BOMMig_LoadRoutingBtn']";
	/** @locatorType Xpath */
	public static final String BOMMig_CreateBOMBtn = "//button[@data-testid='BOMMig_CreateBOMBtn']";
	/** @locatorType Xpath */
	public static final String OtherDetailsTab_Label = "//label[text()='Other Details']";
	/** @locatorType Xpath */
	public static final String CorrectionBtn = "//button[@data-testid='CorrectionBtn']";
	/** @locatorType Xpath */
	public static final String CorrectionIframe = "//iframe[@title='Correction']";
	/** @locatorType Xpath */
	public static final String CorrectionRefInput = "//input[@data-testid='CorrectionRefInput']";
	/** @locatorType Xpath */
	public static final String CorrectionTickIcon = "//button[@data-testid='CorrectionTickIcon']";
	/** @locatorType Xpath */
	public static final String BOMDisplayStatus = "//span[@data-testid='BOMDisplayStatus']";
	/** @locatorType Xpath */
	public static final String InactivateBtn = "//button[@data-testid='InactivateBtn']";
	/** @locatorType Xpath */
	public static final String InactivateIframe = "//iframe[@title='Inactivate']";
	/** @locatorType Xpath */
	public static final String InactivateRefInput = "//input[@data-testid='InactivateRefInput']";
	/** @locatorType Xpath */
	public static final String InactivateTickIcon = "//button[@data-testid='InactivateTickIcon']";
	/** @locatorType Xpath */
	public static final String CreRevisionBtn = "//button[@data-testid='CreRevisionBtn']";
	/** @locatorType Xpath */
	public static final String CreRevisionIframe = "//iframe[@title='BOM Rev.']";
	/** @locatorType Xpath */
	public static final String CreRevisionRefInput = "//input[@data-testid='CreRevisionRefInput']";
	/** @locatorType Xpath */
	public static final String CreRevisionEffFromInput = "//*[@data-testid='CreRevisionEffFromInput']/input";
	/** @locatorType Xpath */
	public static final String CreRevisionEffToInput = "//*[@data-testid='CreRevisionEffToInput']/input";
	/** @locatorType Xpath */
	public static final String CreRevisionTickIcon = "//button[@data-testid='CreRevisionTickIcon']";
	/** @locatorType Xpath */
	public static final String CopyBOMBtn = "//button[@data-testid='CopyBOMBtn']";
	/** @locatorType Xpath */
	public static final String CopyBOMIframe = "//iframe[@title='Copy BOM']";
	/** @locatorType Xpath */
	public static final String CopyBOMItemLovBtn = "//input[@data-testid='CopyBOMItemLovBtn']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String CopyBOMEffFromInput = "//*[@data-testid='CopyBOMEffFromInput']/input";
	/** @locatorType Xpath */
	public static final String CopyBOMEffToInput = "//*[@data-testid='CopyBOMEffToInput']/input";
	/** @locatorType Xpath */
	public static final String CopyBOMNameInput = "//input[@data-testid='CopyBOMNameInput']";
	/** @locatorType Xpath */
	public static final String CopyBOMTickIcon = "//button[@data-testid='CopyBOMTickIcon']";
}
