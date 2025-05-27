package commonlocators.productionplanning;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks March 11, 2025
 */
public class BillOfMaterialsLocators {
	/** @locatorType Xpath */
	public static final String BOM_FindSearchBtn = "//button[@id='B660949422212440106']";
	/** @locatorType Xpath */
	public static final String Find_BOMNoLovBtn = "//button[@id='P21513101012_BOM_NO_lov_btn']";

	/** @locatorType Xpath */
	public static final String Find_BOMStatusTableData(String BOMNo) {
		return "(//div[@id='detail_data_panel']//table/tbody/tr/td/a[text()='" + BOMNo + "']/../../td[10])[1]";
	}

	/** @locatorType Xpath */
	public static final String BOMNoTableData(String BOMNo) {
		return "(//div[@id='detail_data_panel']//table/tbody/tr/td/a[text()='" + BOMNo + "'])[1]";
	}

	/** @locatorType Xpath */
	public static final String BOM_CreateBtnPlusIcon = "//button[@id='B660949003370440106']";
	/** @locatorType Xpath */
	public static final String CreateBOMIframe = "//iframe[@title='Create BOM']";
	/** @locatorType Xpath */
	public static final String CreateBOM_UnitLovBtn = "//button[@id='P21513101013_UNIT_lov_btn']";
	/** @locatorType Xpath */
	public static final String CreateBOM_ItemLovBtn = "//button[@id='P21513101013_ITEM_REV_lov_btn']";
	/** @locatorType Xpath */
	public static final String CreateBOM_TickIcon = "//button[@id='B1134982271826399065']";
	/** @locatorType Xpath */
	public static final String CreateBOM_BOMNameInput = "//input[@id='P21513101013_BOM_NAME']";
	/** @locatorType Xpath */
	public static final String CreateBOM_EffFromInput = "//input[@id='P21513101001_BOMHD_EFF_FROM_input']";
	/** @locatorType Xpath */
	public static final String CreateBOM_EffToInput = "//input[@id='P21513101001_BOMHD_EFF_TO_input']";
	/** @locatorType Xpath */
	public static final String BOMSaveHeaderIcon = "//button[@id='B234994611759725781']";
	/** @locatorType Xpath */
	public static final String BOMPostBtn = "//button[@id='post']";
	/** @locatorType Xpath */
	public static final String DisplayBOMNumber = "//span[@id='P21513101001_BOMHD_BOM_NO_DISPLAY']";
	/** @locatorType Xpath */
	public static final String RoutingTab_Label = "//label[text()='Routing']";
	/** @locatorType Xpath */
	public static final String RoutingAddLineBtn = "//button[@id='B234901408095725364']";
	/** @locatorType Xpath */
	public static final String RoutingSaveLineBtn = "//button[@id='B234901812899725364']";
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
	public static final String RoutingDetailsBtnWithProcessName(String ProcessName) {
		return "//div[@id='ROUTING_ig_grid_vc']//table/tbody/tr/td[text()='" + ProcessName + "']/../td[14]/a";
	}

	/** @locatorType Xpath */
	public static final String RoutingViewDetailsIframe = "//iframe[@title='View Details']";
	/** @locatorType Xpath */
	public static final String RoutingViewDetailsIframeCloseIcon = "//h1[text()='View Details']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String RoutingViewDts_ConsumptionWHLovBtn = "//button[@id='P215131010002_CONS_WH_DESC_lov_btn']";
	/** @locatorType Xpath */
	public static final String RoutingViewDts_ReceiptWHLovBtn = "//button[@id='P215131010002_REC_WH_DESC_lov_btn']";
	/** @locatorType Xpath */
	public static final String RoutingViewDts_OkBtn = "//button[@id='B1589076839269499147']";
	/** @locatorType Xpath */
	public static final String MaterialTab_Label = "//label[text()='Material']";
	/** @locatorType Xpath */
	public static final String MaterialAddLineBtn = "//button[@id='B1187162180938226534']";
	/** @locatorType Xpath */
	public static final String MaterialSaveLineBtn = "//button[@id='B1187162498089226537']";
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
	public static final String BORAddLineBtn = "//button[@id='B1233902479052410959']";
	/** @locatorType Xpath */
	public static final String BORSaveLineBtn = "//button[@id='B1233902849434410967']";
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
	public static final String ScrapProductAddLineBtn = "//button[@id='B1233216372959825673']";
	/** @locatorType Xpath */
	public static final String ScrapProductSaveLineBtn = "//button[@id='B1233216769069825684']";
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
	public static final String Header_BOMTypeSelect = "//select[@id='P21513101001_BOMHD_PRIMARY']";

	// BOM Migration Std Screen Locators
	/** @locatorType Xpath */
	public static final String BOMMigrationGenerateBtn = "//button[@id='B1534456505648284810']";
	/** @locatorType Xpath */
	public static final String FindBOMMigrationDocLovBtn = "//button[@id='P21513113203_DOCUMENT_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String BOMMigrationAddBtn = "//button[@id='B1534456468300284809']";
	/** @locatorType Xpath */
	public static final String BOMMig_CreateIframe = "//iframe[@title='BOM Migration']";
	/** @locatorType Xpath */
	public static final String BOMMig_CreateUnitLovBtn = "//button[@id='P21513113204_UNIT_lov_btn']";
	/** @locatorType Xpath */
	public static final String BOMMig_CreateRefInput = "//input[@id='P21513113204_REFERENCE']";
	/** @locatorType Xpath */
	public static final String BOMMig_CreateTickIcon = "//button[@id='B1615508531818429817']";
	/** @locatorType Xpath */
	public static final String BOMMig_DisplayDocNumber = "//input[@id='P21513113205_MBH_DOC_NO']";
	/** @locatorType Xpath */
	public static final String BOMMig_UploadIcon = "//button[@id='B1755900166540261131']";
	/** @locatorType Xpath */
	public static final String BOMMig_UploadInput = "//input[@id='P21513113205_FILE_input']";
	/** @locatorType Xpath */
	public static final String BOMMig_UploadBtn = "//button[@id='B1756043691806149964']";
	/** @locatorType Xpath */
	public static final String BOMMig_LoadRoutingBtn = "//button[@id='B1755900496552261134']";
	/** @locatorType Xpath */
	public static final String BOMMig_CreateBOMBtn = "//button[@id='B1755901438901261144']";
	/** @locatorType Xpath */
	public static final String OtherDetailsTab_Label = "//label[text()='Other Details']";
	/** @locatorType Xpath */
	public static final String CorrectionBtn = "//button[@id='B234988091906725768']";
	/** @locatorType Xpath */
	public static final String CorrectionIframe = "//iframe[@title='Correction']";
	/** @locatorType Xpath */
	public static final String CorrectionRefInput = "//input[@id='P21513101026_REFRENCE']";
	/** @locatorType Xpath */
	public static final String CorrectionTickIcon = "//button[@id='B1033009382015451530']";
	/** @locatorType Xpath */
	public static final String BOMDisplayStatus = "//span[@id='P21513101001_BOMHD_STATUS_DISPLAY']";
	/** @locatorType Xpath */
	public static final String InactivateBtn = "//button[@id='B234988800140725770']";
	/** @locatorType Xpath */
	public static final String InactivateIframe = "//iframe[@title='Inactivate']";
	/** @locatorType Xpath */
	public static final String InactivateRefInput = "//input[@id='P21513101027_REFRENCE']";
	/** @locatorType Xpath */
	public static final String InactivateTickIcon = "//button[@id='B1151198551635941777']";
	/** @locatorType Xpath */
	public static final String CreRevisionBtn = "//button[@id='B234989204418725770']";
	/** @locatorType Xpath */
	public static final String CreRevisionIframe = "//iframe[@title='BOM Rev.']";
	/** @locatorType Xpath */
	public static final String CreRevisionRefInput = "//input[@id='P21513101007_REFRENCE']";
	/** @locatorType Xpath */
	public static final String CreRevisionEffFromInput = "//input[@id='P21513101007_BOMHD_EFF_FROM_input']";
	/** @locatorType Xpath */
	public static final String CreRevisionEffToInput = "//input[@id='P21513101007_BOMHD_EFF_TO_input']";
	/** @locatorType Xpath */
	public static final String CreRevisionTickIcon = "//button[@id='B1162274097567080087']";
	/** @locatorType Xpath */
	public static final String CopyBOMBtn = "//button[@id='B234988405167725768']";
	/** @locatorType Xpath */
	public static final String CopyBOMIframe = "//iframe[@title='Copy BOM']";
	/** @locatorType Xpath */
	public static final String CopyBOMItemLovBtn = "//button[@id='P21513101008_COPY_PROD_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String CopyBOMEffFromInput = "//input[@id='P21513101008_COPY_EFF_TO_input']";
	/** @locatorType Xpath */
	public static final String CopyBOMEffToInput = "//input[@id='P21513101008_COPY_EFF_TO_input']";
	/** @locatorType Xpath */
	public static final String CopyBOMNameInput = "//input[@id='P21513101008_COPY_BOM_NAME']";
	/** @locatorType Xpath */
	public static final String CopyBOMTickIcon = "//button[@id='B1176576408788278702']";
}
