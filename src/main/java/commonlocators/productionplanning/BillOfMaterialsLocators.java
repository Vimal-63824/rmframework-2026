package commonlocators.productionplanning;

import commonfunctions.CommonFunctions;

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
	public static final String CreateBOMIframe = "//iframe[contains(@title,'Create')]";
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
	public static final String RoutingTable = "(//div[@id='ROUTING_ig_grid_vc']//table)[2]";

	/** @locatorType Xpath */
	public static String RoutingProcSeqNoData() {
		return checkAndReturnIndexForRoutingLocatorWithHeaderValue(RoutingTable, "Proc. Seq. No");
	}

	/** @locatorType Xpath */
	public static String RoutingProcSeqNoInput() {
		return checkAndReturnIndexForRoutingLocatorWithHeaderValue(RoutingTable, "Proc. Seq. No") + "//input";
	}

	/** @locatorType Xpath */
	public static String RoutingOprnNoData() {
		return checkAndReturnIndexForRoutingLocatorWithHeaderValue(RoutingTable, "Oprn. No.");
	}

	/** @locatorType Xpath */
	public static String RoutingOprnNoInput() {
		return checkAndReturnIndexForRoutingLocatorWithHeaderValue(RoutingTable, "Oprn. No.") + "//input";
	}

	/** @locatorType Xpath */
	public static String RoutingProcessIdData() {
		return checkAndReturnIndexForRoutingLocatorWithHeaderValue(RoutingTable, "Process. ID");
	}

	/** @locatorType Xpath */
	public static String RoutingProcessIdLovBtn() {
		return checkAndReturnIndexForRoutingLocatorWithHeaderValue(RoutingTable, "Process. ID") + "//button";
	}

	/** @locatorType Xpath */
	public static String RoutingWorkCenterData() {
		return checkAndReturnIndexForRoutingLocatorWithHeaderValue(RoutingTable, "Work Center");
	}

	/** @locatorType Xpath */
	public static String RoutingWorkCenterLovBtn() {
		return checkAndReturnIndexForRoutingLocatorWithHeaderValue(RoutingTable, "Work Center") + "//button";
	}

	/** @locatorType Xpath */
	public static String RoutingTypeData() {
		return checkAndReturnIndexForRoutingLocatorWithHeaderValue(RoutingTable, "Type");
	}

	/** @locatorType Xpath */
	public static String RoutingTypeSelect() {
		return checkAndReturnIndexForRoutingLocatorWithHeaderValue(RoutingTable, "Type") + "//select";
	}

	/** @locatorType Xpath */
	public static String RoutingIRFlagData() {
		return checkAndReturnIndexForRoutingLocatorWithHeaderValue(RoutingTable, "IR");
	}

	/** @locatorType Xpath */
	public static final String RoutingIRDiv(String ProcessName) {
		return "//td[text()='" + ProcessName + "']/../td[13]/div";
	}

	/** @locatorType Xpath */
	public static final String RoutingIRInputBox(String ProcessName) {
		return "//td[text()='" + ProcessName + "']/../td[12]//input";
	}

	/** @locatorType Xpath */
	public static final String RoutingDetailsBtnWithProcessName(String ProcessName) {
		return "(//div[@id='ROUTING_ig_grid_vc']//table/tbody/tr/td[text()='" + ProcessName + "']/../td[1])[1]//a";
	}

	/** @locatorType Xpath */
	public static final String Details_RoutingThreeDotsMenu = "//ul[@class='custom-row-menu']//*[text()='Details']";
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
	public static final String MaterialTable = "(//div[@id='BOM_LN_STATIC_ig_grid_vc']//table)[2]";

	/** @locatorType Xpath */
	public static String MaterialProcessDescData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(MaterialTable, "Process Desc.");
	}

	/** @locatorType Xpath */
	public static String MaterialProcessDescLovBtn() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(MaterialTable, "Process Desc.")
				+ "// button";
	}

	/** @locatorType Xpath */
	public static String MaterialItemData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(MaterialTable, "Item");
	}

	/** @locatorType Xpath */
	public static String MaterialItemLovBtn() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(MaterialTable, "Item") + "//button";
	}

	/** @locatorType Xpath */
	public static String MaterialUOMData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(MaterialTable, "UOM");
	}

	/** @locatorType Xpath */
	public static String MaterialUOMSelect() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(MaterialTable, "UOM") + "//select";
	}

	/** @locatorType Xpath */
	public static String MaterialRequestQntyData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(MaterialTable, "Req. Qty.");
	}

	/** @locatorType Xpath */
	public static String MaterialRequestQntyInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(MaterialTable, "Req. Qty.") + "//input";
	}

	/** @locatorType Xpath */
	public static String MaterialConventionFactorData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(MaterialTable, "Conv. Fact.");
	}

	/** @locatorType Xpath */
	public static String MaterialConventionFactorInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(MaterialTable, "Conv. Fact.") + "//input";
	}

	/** @locatorType Xpath */
	public static String MaterialSourceWHData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(MaterialTable, "Sou. WH");
	}

	/** @locatorType Xpath */
	public static String MaterialSourceWHSelect() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(MaterialTable, "Sou. WH") + "//select";
	}

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
	public static final String BORTable = "(//div[@id='BOR_ig_grid_vc']//table)[2]";

	/** @locatorType Xpath */
	public static String BORLineSnoData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Line");
	}

	/** @locatorType Xpath */
	public static String BORLineSnoInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Line") + "//input";
	}

	/** @locatorType Xpath */
	public static String BORProcessIdData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Process. ID");
	}

	/** @locatorType Xpath */
	public static String BORProcessIdLovBtn() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Process. ID") + "//button";
	}

	/** @locatorType Xpath */
	public static String BORResourceTypeData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Res. Type");
	}

	/** @locatorType Xpath */
	public static String BORResourceTypeSelect() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Res. Type") + "//select";
	}

	/** @locatorType Xpath */
	public static String BORResourceData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Resource Group/Resource");
	}

	/** @locatorType Xpath */
	public static String BORResourceLovBtn() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Resource Group/Resource")
				+ "//button";
	}

	/** @locatorType Xpath */
	public static String BORSetupHrsData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Setup Hrs.");
	}

	/** @locatorType Xpath */
	public static String BORSetupHrsInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Setup Hrs.") + "//input";
	}

	/** @locatorType Xpath */
	public static String BORSetupMinsData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Setup Mins.");
	}

	/** @locatorType Xpath */
	public static String BORSetupMinsInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Setup Mins.") + "//input";
	}

	/** @locatorType Xpath */
	public static String BORSetupLotQntyData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Setup Lot Qty.");
	}

	/** @locatorType Xpath */
	public static String BORSetupLotQntyInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Setup Lot Qty.") + "//input";
	}

	/** @locatorType Xpath */
	public static String BORCycleTimeUnitPerHourData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Cycle Time Units/Hrs.");
	}

	/** @locatorType Xpath */
	public static String BORCycleTimeUnitPerHourInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Cycle Time Units/Hrs.")
				+ "//input";
	}

	/** @locatorType Xpath */
	public static String BORCycleTimeHourData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Cycle Time Hrs.");
	}

	/** @locatorType Xpath */
	public static String BORCycleTimeHourInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Cycle Time Hrs.") + "//input";
	}

	/** @locatorType Xpath */
	public static String BORCycleTimeMinData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Cycle Time Mins.");
	}

	/** @locatorType Xpath */
	public static String BORCycleTimeMinInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Cycle Time Mins.") + "//input";
	}

	/** @locatorType Xpath */
	public static String BORCycleTimeSecData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Cycle Time Secs.");
	}

	/** @locatorType Xpath */
	public static String BORCycleTimeSecInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Cycle Time Secs.") + "//input";
	}

	/** @locatorType Xpath */
	public static String BORCycleTimeBatchQntyData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Cycle Time Batch Qty.");
	}

	/** @locatorType Xpath */
	public static String BORCycleTimeBatchQntyInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Cycle Time Batch Qty.")
				+ "//input";
	}

	/** @locatorType Xpath */
	public static String BORCycleTimeBatchHrsData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Cycle Time Batch Hrs.");
	}

	/** @locatorType Xpath */
	public static String BORCycleTimeBatchHrsInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Cycle Time Batch Hrs.")
				+ "//input";
	}

	/** @locatorType Xpath */
	public static String BORLagHrsData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Lag Hrs.");
	}

	/** @locatorType Xpath */
	public static String BORLagHrsInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Lag Hrs.") + "//input";
	}

	/** @locatorType Xpath */
	public static String BORPriorityData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Priority");
	}

	/** @locatorType Xpath */
	public static String BORPriorityInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "Priority") + "//input";
	}

	/** @locatorType Xpath */
	public static String BORNoOfUnitsData() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "No. Of Units");
	}

	/** @locatorType Xpath */
	public static String BORNoOfUnitsInput() {
		return CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(BORTable, "No. Of Units") + "//input";
	}

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

	public static String checkAndReturnIndexForRoutingLocatorWithHeaderValue(String Locator, String HeaderValue) {
		Locator = Locator + "/tbody/tr/*[count(" + Locator + "/thead/tr[2]/th[normalize-space()='" + HeaderValue
				+ "']/preceding-sibling::th) + 1]";
		return Locator;
	}
}
