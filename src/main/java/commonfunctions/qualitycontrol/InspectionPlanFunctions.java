package commonfunctions.qualitycontrol;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks October 27, 2025
 */
import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.RandomStringUtils;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.payroll.CommonPayrollFunctions;
import commonfunctions.productionplanning.BillOfMaterialsFunctions;
import commonfunctions.productionplanning.CompletionProductionOrderFunctions;
import commonfunctions.productionplanning.MRPFunctions;
import commonfunctions.productionplanning.ProductionOrderFunctions;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonfunctions.supplychainmanagement.CustomerItemsFunctions;
import commonfunctions.supplychainmanagement.MaterialIssuanceVoucherFunctions;
import commonfunctions.supplychainmanagement.MaterialReceiptVoucherFunctions;
import commonfunctions.supplychainmanagement.MaterialRequestFunctions;
import commonfunctions.supplychainmanagement.PricelistPageFunctions;
import commonfunctions.supplychainmanagement.SalesInvoiceFunctions;
import commonfunctions.supplychainmanagement.SalesOrderFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.qualitycontrol.InspectionPlanLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class InspectionPlanFunctions {
	private boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static CommonPayrollFunctions commonPayrollFunc = new CommonPayrollFunctions();
	private static ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	private static SalesOrderFunctions saleOrderFunc = new SalesOrderFunctions();
	private static SalesInvoiceFunctions saleInvoiceFunc = new SalesInvoiceFunctions();
	private static MRPFunctions mrpFunc = new MRPFunctions();
	private static ProductionOrderFunctions productionOrderFunc = new ProductionOrderFunctions();
	private static CompletionProductionOrderFunctions completionProductionOrderFunc = new CompletionProductionOrderFunctions();
	private static MaterialRequestFunctions materialRequestFunc = new MaterialRequestFunctions();
	private static MaterialIssuanceVoucherFunctions materialIssuanceVoucherFunc = new MaterialIssuanceVoucherFunctions();
	private static BillOfMaterialsFunctions bomFunc = new BillOfMaterialsFunctions();
	private static PricelistPageFunctions priceListFunc = new PricelistPageFunctions();
	private static InspectionDocumentFunctions insDocumentFunc = new InspectionDocumentFunctions();
	private static InspectionRequestFunctions insRequestFunc = new InspectionRequestFunctions();
	private static MaterialReceiptVoucherFunctions materialReceiptFunctions = new MaterialReceiptVoucherFunctions();
	private static CustomerItemsFunctions customerItemFunc = new CustomerItemsFunctions();

	/**
	 * This method used to switch to creating iframe
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchInsPlanCreateIframe() {
		flag = webDB.waitForElement(InspectionPlanLocators.CreateInspectionIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(InspectionPlanLocators.CreateInspectionIframe, ElementType.Xpath);
			flag = webDB.waitForElement(InspectionPlanLocators.CreateInprocessButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Inspection Plan Creation Iframe Switched");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click add and Inprocess Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAddIconAndClickInprocessInInsPlan() {
		flag = webDB.waitForElement(InspectionPlanLocators.AddIconInFindPage, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(InspectionPlanLocators.AddIconInFindPage, ElementType.Xpath);
			switchInsPlanCreateIframe();
			flag = webDB.waitForElement(InspectionPlanLocators.CreateInprocessButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(InspectionPlanLocators.CreateInprocessButton, ElementType.Xpath);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(InspectionPlanLocators.HeaderLocationLovBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Inspection Plan 'Inprocess' Type Created Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click add and Final Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAddIconAndFinalInspectionInInsPlan() {
		flag = webDB.waitForElement(InspectionPlanLocators.AddIconInFindPage, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(InspectionPlanLocators.AddIconInFindPage, ElementType.Xpath);
			switchInsPlanCreateIframe();
			flag = webDB.waitForElement(InspectionPlanLocators.CreateFinalInspectionButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(InspectionPlanLocators.CreateFinalInspectionButton, ElementType.Xpath);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(InspectionPlanLocators.HeaderLocationLovBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Inspection Plan 'Final Inspection' Type Created Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click add and Pre Dispatch Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAddIconAndPreDispatchInInsPlan() {
		flag = webDB.waitForElement(InspectionPlanLocators.AddIconInFindPage, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(InspectionPlanLocators.AddIconInFindPage, ElementType.Xpath);
			switchInsPlanCreateIframe();
			flag = webDB.waitForElement(InspectionPlanLocators.CreatePreDispatchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(InspectionPlanLocators.CreatePreDispatchButton, ElementType.Xpath);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(InspectionPlanLocators.HeaderLocationLovBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Inspection Plan 'Pre Dispatch' Type Created Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to fill Details in Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 * @param Location
	 * @param Item
	 * @param PlanName
	 */
	public void fillLocationItemAndPlanNameInHeader(String Location, String Item, String PlanName) {
		commonFunc.searchAndSelectValueInFilters(InspectionPlanLocators.HeaderLocationLovBtn, Location);
		logs.logging("INFO", "Location Selected in header of Inspection Plan: " + Location);
		commonFunc.searchAndSelectValueInFilters(InspectionPlanLocators.HeaderItemIdLovBtn, Item);
		logs.logging("INFO", "Item Id Selected in header of Inspection Plan: " + Item);
		flag = webDB.waitForElement(InspectionPlanLocators.HeaderPlanNameInput, ElementType.Xpath);
		if (flag) {
			flag = webDB.sendTextToAnElement(InspectionPlanLocators.HeaderPlanNameInput, PlanName, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Plan Name Entered in header of Inspection Plan: " + PlanName);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to fill Details in Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 * @param FromDate
	 * @param ToDate
	 * @param Type
	 */
	public void fillFromToDateAndInspectionType(String FromDate, String ToDate, String Type) {
		commonFunc.verifyAddTableSpecs(InspectionPlanLocators.HeaderDateFromInput,
				InspectionPlanLocators.HeaderDateFromInput, FromDate);
		logs.logging("INFO", "From Date Entered in Header of Inspection Plan: " + FromDate);
		commonFunc.verifyAddTableSpecs(InspectionPlanLocators.HeaderDateToInput,
				InspectionPlanLocators.HeaderDateToInput, ToDate);
		logs.logging("INFO", "To Date Entered in Header of Inspection Plan: " + ToDate);
		flag = webDB.waitForElement(InspectionPlanLocators.HeaderPlanTypeSelect, ElementType.Xpath);
		if (flag) {
			flag = webDB.selectDropDownByText(InspectionPlanLocators.HeaderPlanTypeSelect, Type, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Plan Type Selected in header of Inspection Plan: " + Type);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click creation tick Icon in Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickCreationTickIconInHeader() {
		flag = webDB.waitForElement(InspectionPlanLocators.CreationTickButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(InspectionPlanLocators.CreationTickButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				commonFunc.checkSuccessToastMessageDisappear();
				flag = webDB.waitForElement(InspectionPlanLocators.DisplayPlanNo, ElementType.Xpath);
				if (flag) {
					String DocNo = webDB.getDomAttributeFromElement(InspectionPlanLocators.DisplayPlanNo,
							ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
					logs.logging("INFO", "Displayed Inspection Document Number: " + DocNo);
					VariableHandler.setInspectionPlanDocNo(DocNo);
				}
				logs.logging("INFO", "Inspection Plan Create Icon Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click add lines in Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAddLinesInspectionPlan() {
		flag = webDB.waitForElement(InspectionPlanLocators.LineAddIcon, ElementType.Xpath);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			webDB.moveToElement(InspectionPlanLocators.LineAddIcon, ElementType.Xpath);
			webDB.clickAnElement(InspectionPlanLocators.LineAddIcon, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(InspectionPlanLocators.CharacteristicsTableData, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Lines Icon Clicked in Inspection Plan");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to fill Details in Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 * @param Character
	 * @param ProcessId
	 * @param Mod
	 */
	public void fillCharacteristicsProcessIdModOfIns(String Character, String ProcessId, String Mod) {
		flag = webDB.waitForElement(InspectionPlanLocators.CharacteristicsTableData, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(InspectionPlanLocators.CharacteristicsTableData, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(InspectionPlanLocators.CharacteristicsTableLovButton, Character);
			logs.logging("INFO", "Character Selected in Lines of Inspection Plan: " + Character);
//			flag = webDB.waitForElement(InspectionPlanLocators.ProcessIdTableData, ElementType.Xpath);
//			if (flag) {
//				webDB.doubleClickAnElement(InspectionPlanLocators.ProcessIdTableData, ElementType.Xpath);
//				commonFunc.searchAndSelectValueInFilters(InspectionPlanLocators.ProcessIdTableLovButton, ProcessId);
			logs.logging("INFO", "Process Id Selected in Lines of Inspection Plan: " + ProcessId);
			commonFunc.verifyAddTableSpecs(InspectionPlanLocators.ModInspectionTableData,
					InspectionPlanLocators.ModInspectionTableInput, Mod);
			logs.logging("INFO", "Mod of Inspection Entered in Lines of Inspection Plan: " + Mod);
		}
//		}
		assertTrue(flag);
	}

	/**
	 * This method used to fill Details in Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 * @param UOM
	 * @param Attribute
	 */
	public void fillUnitofMeasureAndAttributeSpec(String UOM, String Attribute) {
		flag = webDB.waitForElement(InspectionPlanLocators.AttributeSpecTableData, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(InspectionPlanLocators.AttributeSpecTableData, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(InspectionPlanLocators.AttributeSpecTableLovButton, Attribute);
			logs.logging("INFO", "Attribute Spec Selected in Lines of Inspection Plan: " + Attribute);
			flag = webDB.waitForElement(InspectionPlanLocators.UOMTableData, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(InspectionPlanLocators.UOMTableData, ElementType.Xpath);
				flag = webDB.waitForElement(InspectionPlanLocators.UOMTableSelect, ElementType.Xpath);
				if (flag) {
					flag = webDB.selectDropDownByText(InspectionPlanLocators.UOMTableSelect, UOM, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "UOM Selected in Lines of Inspection Plan: " + UOM);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to fill Details in Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 * @param UOM
	 * @param Attribute
	 */
	public void fillNoOfSampleAndObservations(String sampleCount, String ObservationCount) {
		commonFunc.verifyAddTableSpecs(InspectionPlanLocators.NoOfSampleData, InspectionPlanLocators.NoOfSampleInput,
				sampleCount);
		logs.logging("INFO", "Sample Count entered in Lines of Inspection Plan: " + sampleCount);
		commonFunc.verifyAddTableSpecs(InspectionPlanLocators.NoOfObservationData,
				InspectionPlanLocators.NoOfObservationInput, ObservationCount);
		logs.logging("INFO", "Observation Count entered in Lines of Inspection Plan: " + ObservationCount);
		assertTrue(flag);
	}

	/**
	 * This method used to fill Details in Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 * @param UOM
	 * @param StdOperator
	 * @param SpecValue
	 */
	public void fillUnitofMeasureAndStdOperatorAndSpecValue(String UOM, String StdOperator, String SpecValue) {
		flag = webDB.waitForElement(InspectionPlanLocators.UOMTableData, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(InspectionPlanLocators.UOMTableData, ElementType.Xpath);
			flag = webDB.waitForElement(InspectionPlanLocators.UOMTableSelect, ElementType.Xpath);
			if (flag) {
				flag = webDB.selectDropDownByText(InspectionPlanLocators.UOMTableSelect, UOM, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "UOM Selected in Lines of Inspection Plan: " + UOM);
					flag = webDB.waitForElement(InspectionPlanLocators.StdOperatorData, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(InspectionPlanLocators.StdOperatorData, ElementType.Xpath);
						flag = webDB.waitForElement(InspectionPlanLocators.StdOperatorSelect, ElementType.Xpath);
						if (flag) {
							flag = webDB.selectDropDownByValue(InspectionPlanLocators.StdOperatorSelect, StdOperator,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO",
										"Std Operator Selected in Lines of Inspection Plan: " + StdOperator);
								commonFunc.verifyAddTableSpecs(InspectionPlanLocators.SpecValueTableData,
										InspectionPlanLocators.SpecValueTableInput, SpecValue);
								logs.logging("INFO", "Spec Value Entered in Lines of Inspection Plan: " + SpecValue);
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to fill Details in Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 * @param UOM
	 * @param StdValText
	 */
	public void fillUnitofMeasureAndStdValText(String UOM, String StdValText) {
		flag = webDB.waitForElement(InspectionPlanLocators.UOMTableData, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(InspectionPlanLocators.UOMTableData, ElementType.Xpath);
			flag = webDB.waitForElement(InspectionPlanLocators.UOMTableSelect, ElementType.Xpath);
			if (flag) {
				flag = webDB.selectDropDownByText(InspectionPlanLocators.UOMTableSelect, UOM, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "UOM Selected in Lines of Inspection Plan: " + UOM);
					commonFunc.verifyAddTableSpecs(InspectionPlanLocators.StdValTextTableData,
							InspectionPlanLocators.StdValTextTableInput, StdValText);
					logs.logging("INFO", "Std Value Text Entered in Lines of Inspection Plan: " + StdValText);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click save lines in Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickSaveLinesInspectionPlan() {
		flag = webDB.waitForElement(InspectionPlanLocators.LineSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(InspectionPlanLocators.LineSaveIcon, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				logs.logging("INFO", "Save Lines Icon Clicked in Inspection Plan");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click Save header Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickSaveHeaderInInspectionPlan() {
		flag = webDB.waitForElement(InspectionPlanLocators.HeaderSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(InspectionPlanLocators.HeaderSaveIcon, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				commonFunc.checkSuccessToastMessageDisappear();
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				logs.logging("INFO", "Save Header Icon Clicked in Inspection Plan");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click Post button Inspection Plan
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickPostButtonInInspectionPlan() {
		flag = webDB.waitForElement(InspectionPlanLocators.PostButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(InspectionPlanLocators.PostButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
			if (flag) {
				commonFunc.checkSuccessToastMessageDisappear();
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				logs.logging("INFO", "Post Button Clicked in Inspection Plan");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Define Inspection Plan - In Process for manufacturing process ?
	 * 
	 * @author Sakthi-GWL
	 * @return flag
	 */
	public boolean verifyInspectionPlanInProcessForManufacturingProcess() {
		String BOMName = commonFunc.getDataFromTestDataSheet("BomNamePreposition") + RandomStringUtils.randomNumeric(4);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String Today = date.format(formatter);
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateItemPage();
			priceListFunc.searchItemAndCopyWithName(commonFunc.getDataFromTestDataSheet("GwlBomItem1001"),
					"GWL - BOM ITEM - ");
			commonFunc.navigateToBillOfMaterialsPage();
			bomFunc.clickAddIconAndFillUnitBOMNameAndItem(commonFunc.getDataFromTestDataSheet("Unit_100"), BOMName,
					VariableHandler.getItemName());
			bomFunc.enterEffFromToDateAndSaveHeader(Today, Today);
			bomFunc.navigateToRoutingSubTab();
			bomFunc.clickAddLineIconInRoutingTab();
			bomFunc.fillProcSeqNoOprnNoAndProcessIdInRouting("1", "1",
					commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			bomFunc.fillTypeAndWorkCenterInRouting(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"));
			bomFunc.clickIRFlagInRoutingTabForProcess(commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			bomFunc.clickSaveLineIconInRoutingTab();
			bomFunc.clickDetailsForAddedProcessInBOM(commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			bomFunc.chooseConsumptionAndReceiptWHInViewDetails(commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			bomFunc.navigateToMaterialsSubTab();
			bomFunc.clickAddLineIconInMaterialTab();
			bomFunc.fillProcessItemUOMInMaterial(commonFunc.getDataFromTestDataSheet("GwlProcess1"), RMItem,
					commonFunc.getDataFromTestDataSheet("Kgs"));
			bomFunc.fillReqQntyConvFactorAndSourceWHInMaterial("3", "1",
					commonFunc.getDataFromTestDataSheet("RMU100_WH"));
			bomFunc.clickSaveLineIconInMaterialTab();
			bomFunc.navigateToBORSubTab();
			bomFunc.clickAddLineIconInBORTab();
			bomFunc.fillLineSnoAndProcessIdInBORTab("1", commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			bomFunc.fillResourceTypeAndResourceInBORTab(commonFunc.getDataFromTestDataSheet("ResourceText"),
					commonFunc.getDataFromTestDataSheet("AutomationResource"));
			bomFunc.fillSetupHrsMinAndSetupLotQntyInBORTab("0", "15", "0");
			bomFunc.fillCycleTimeUnitOrHrsMinAndSecInBORTab("3", "0", "0", "0");
			bomFunc.fillCycleTimeBatchQntyAndHrsInBORTab("0", "0");
			bomFunc.fillLagPriorityAndNoOfUnitsInBORTab("0", "1", "3");
			bomFunc.clickSaveLineIconInBORTab();
			bomFunc.clickHeaderSaveAndPostButtonInBOM();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getBOMNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToBillOfMaterialsPage();
			bomFunc.searchBOMNoAndVerifyStatus(VariableHandler.getBOMNumber(),
					commonFunc.getDataFromTestDataSheet("ActiveStatus"));
			commonFunc.navigateInspectionPlan();
			clickAddIconAndClickInprocessInInsPlan();
			fillLocationItemAndPlanNameInHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
					VariableHandler.getBOMNumber(), commonFunc.getDataFromTestDataSheet("AutomationPlanText"));
			fillFromToDateAndInspectionType(Today, Today, commonFunc.getDataFromTestDataSheet("SingleItemText"));
			clickCreationTickIconInHeader();
			clickAddLinesInspectionPlan();
			fillCharacteristicsProcessIdModOfIns(commonFunc.getDataFromTestDataSheet("Radical_CharText"),
					commonFunc.getDataFromTestDataSheet("GwlProcess1"),
					commonFunc.getDataFromTestDataSheet("RefText_Test"));
			fillUnitofMeasureAndStdOperatorAndSpecValue(commonFunc.getDataFromTestDataSheet("Kgs"), "ET", "30");
			fillNoOfSampleAndObservations("1", "1");
			clickSaveLinesInspectionPlan();
			clickAddLinesInspectionPlan();
			fillCharacteristicsProcessIdModOfIns(commonFunc.getDataFromTestDataSheet("VisualQC_CharText"),
					commonFunc.getDataFromTestDataSheet("GwlProcess1"),
					commonFunc.getDataFromTestDataSheet("RefText_Test"));
			fillUnitofMeasureAndStdValText(commonFunc.getDataFromTestDataSheet("Kgs"), "30");
			fillNoOfSampleAndObservations("1", "1");
			clickSaveLinesInspectionPlan();
			clickAddLinesInspectionPlan();
			fillCharacteristicsProcessIdModOfIns(commonFunc.getDataFromTestDataSheet("Surface_CharText"),
					commonFunc.getDataFromTestDataSheet("GwlProcess1"),
					commonFunc.getDataFromTestDataSheet("RefText_Test"));
			fillUnitofMeasureAndAttributeSpec(commonFunc.getDataFromTestDataSheet("Kgs"),
					commonFunc.getDataFromTestDataSheet("RubberTypeText"));
			fillNoOfSampleAndObservations("1", "1");
			clickSaveLinesInspectionPlan();
			clickSaveHeaderInInspectionPlan();
			clickPostButtonInInspectionPlan();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getInspectionPlanDocNo(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Loc_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			productionOrderFunc.clickAddIconInCreationProductionOrder();
			productionOrderFunc.createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"), VariableHandler.getBOMNumber(), "3");
			productionOrderFunc.clickSaveIconInCreationProductionOrder();
			productionOrderFunc.selectFirstRestOprEventvaluesAndSaveHeader(
					commonFunc.getDataFromTestDataSheet("OrderReleaseOption"),
					commonFunc.getDataFromTestDataSheet("OrderReleaseOption"));
			productionOrderFunc.navigateToRoutingSubTab();
			productionOrderFunc.storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			productionOrderFunc.navigateToMaterialRequirementSubTab();
			productionOrderFunc.verifyItemInMaterialRequirement(new String[] { RMItem });
			productionOrderFunc.verifyProcessInMaterialRequirement(
					new String[] { commonFunc.getDataFromTestDataSheet("GwlProcess1") });
			productionOrderFunc.validateMaterialReqrdQuantityWithRMItemProcess(
					commonFunc.getDataFromTestDataSheet("GwlProcess1"), RMItem);
			productionOrderFunc.navigateToResourcesSubTab();
			productionOrderFunc
					.verifyProcessInResources(new String[] { commonFunc.getDataFromTestDataSheet("GwlProcess1") });
			productionOrderFunc.navigateToExceptionSubTab();
			productionOrderFunc.validateNoExceptionsShowsInExceptionTab();
			productionOrderFunc.clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			webDB.navigateToRefresh();
			commonFunc.navigateToInventoryMaterialRequest();
			materialRequestFunc.validateItemWHReqNameQuantityWithProdNo(VariableHandler.getProductionOrderNumber(),
					RMItem, commonFunc.getDataFromTestDataSheet("RMU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			materialRequestFunc.storeMRNoAndCheckStatusApproveWithProdNo(VariableHandler.getProductionOrderNumber());
			commonFunc.navigateToMaterialIssuanceVoucherTab();
			materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
			materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
			commonFunc.navigateToMaterialIssuanceVoucherTab();
			materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
					commonFunc.getDataFromTestDataSheet("PostedStatus"));
			commonFunc.navigateSetupInventoryPage();
			flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
			commonFunc.navigateToMaterialReceiptVoucher();
			if (flag) {
				materialReceiptFunctions
						.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(VariableHandler.getProductionOrderNumber());
			} else {
				materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
			}
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndWarehousesBOM()[i][0];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				if (i == (VariableHandler.getProcessAndWarehousesBOM().length - 1)) {
					ConsumptionWH = commonFunc.getDataFromTestDataSheet("FG-WH-KD");
				}
				logs.logging("INFO", "ProcessName: " + ProcessName);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
						commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
						VariableHandler.getProductionCompletionOrderNo(),
						commonFunc.getDataFromTestDataSheet("ForwardQCtatus"));
				commonFunc.navigateInspectionDocumentsPage();
				insDocumentFunc.verifyCreatePendingInspectionDocumentWithVoucherNumber(
						VariableHandler.getProductionCompletionOrderNo());
				insDocumentFunc.clickTickAndPostIcon();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getIDNumber(),
							webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
							System.getProperty("HariUsername"));
				}
				commonFunc.navigateInspectionDocumentsPage();
				insDocumentFunc.verifyValidateInspectionDocumentsStatus(VariableHandler.getIDNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.navigateToMaterialReceiptVoucher();
				commonFunc.clickPendingIcon();
				materialReceiptFunctions.clickPendingInssuanceBtn();
				materialReceiptFunctions.searchWithVoucherNumber(VariableHandler.getProductionCompletionOrderNo());
				materialReceiptFunctions.validateProcessRecipientWarehouseWithCompletionProdNo(
						VariableHandler.getProductionCompletionOrderNo(), ProcessName, ConsumptionWH);
				materialReceiptFunctions
						.selectVoucherNoAndClickCreateReceipt(VariableHandler.getProductionCompletionOrderNo());
				materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
				materialReceiptFunctions.clickTickAndPostIcon();
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			productionOrderFunc.searchAndVerifyStatusWithProdOrderNo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyInspectionPlanInProcessForManufacturingProcess': " + e);
			return false;
		}
	}

	/**
	 * Define Inspection Plan - Pre Dispatch?
	 * 
	 * @author Sakthi-GWL
	 * @return flag
	 */
	public boolean verifyInspectionPlanPreDispatch() {
		String BOMName = commonFunc.getDataFromTestDataSheet("BomNamePreposition") + RandomStringUtils.randomNumeric(4);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String Today = date.format(formatter);
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateItemPage();
			priceListFunc.searchItemAndCopyWithName(commonFunc.getDataFromTestDataSheet("PreDispatchItem"),
					(commonFunc.getDataFromTestDataSheet("PreDispatchItem").concat(" - ")));
			commonFunc.navigateItemAssoScreen();
			customerItemFunc.searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
			customerItemFunc.searchItemInFindPage();
			customerItemFunc.clickSearchButton();
			customerItemFunc.searchedItem();
			customerItemFunc.navigateQualityAndClickPreDispatch();
			customerItemFunc.clickSaveButton();
			commonFunc.navigateToBillOfMaterialsPage();
			bomFunc.clickAddIconAndFillUnitBOMNameAndItem(commonFunc.getDataFromTestDataSheet("Unit_100"), BOMName,
					VariableHandler.getItemName());
			bomFunc.enterEffFromToDateAndSaveHeader(Today, Today);
			bomFunc.navigateToRoutingSubTab();
			bomFunc.clickAddLineIconInRoutingTab();
			bomFunc.fillProcSeqNoOprnNoAndProcessIdInRouting("1", "1",
					commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			bomFunc.fillTypeAndWorkCenterInRouting(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"));
			bomFunc.clickIRFlagInRoutingTabForProcess(commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			bomFunc.clickSaveLineIconInRoutingTab();
			bomFunc.clickDetailsForAddedProcessInBOM(commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			bomFunc.chooseConsumptionAndReceiptWHInViewDetails(commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			bomFunc.navigateToMaterialsSubTab();
			bomFunc.clickAddLineIconInMaterialTab();
			bomFunc.fillProcessItemUOMInMaterial(commonFunc.getDataFromTestDataSheet("GwlProcess1"), RMItem,
					commonFunc.getDataFromTestDataSheet("Kgs"));
			bomFunc.fillReqQntyConvFactorAndSourceWHInMaterial("3", "1",
					commonFunc.getDataFromTestDataSheet("RMU100_WH"));
			bomFunc.clickSaveLineIconInMaterialTab();
			bomFunc.navigateToBORSubTab();
			bomFunc.clickAddLineIconInBORTab();
			bomFunc.fillLineSnoAndProcessIdInBORTab("1", commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			bomFunc.fillResourceTypeAndResourceInBORTab(commonFunc.getDataFromTestDataSheet("ResourceText"),
					commonFunc.getDataFromTestDataSheet("AutomationResource"));
			bomFunc.fillSetupHrsMinAndSetupLotQntyInBORTab("0", "15", "0");
			bomFunc.fillCycleTimeUnitOrHrsMinAndSecInBORTab("3", "0", "0", "0");
			bomFunc.fillCycleTimeBatchQntyAndHrsInBORTab("0", "0");
			bomFunc.fillLagPriorityAndNoOfUnitsInBORTab("0", "1", "3");
			bomFunc.clickSaveLineIconInBORTab();
			bomFunc.clickHeaderSaveAndPostButtonInBOM();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getBOMNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToBillOfMaterialsPage();
			bomFunc.searchBOMNoAndVerifyStatus(VariableHandler.getBOMNumber(),
					commonFunc.getDataFromTestDataSheet("ActiveStatus"));
			bomFunc.navigateToBOMBySearchAndClickBOMNo(VariableHandler.getBOMNumber());
			bomFunc.chooseBOMTypeAndSaveHeader(commonFunc.getDataFromTestDataSheet("PrimaryType"));
			commonFunc.navigateInspectionPlan();
			clickAddIconAndPreDispatchInInsPlan();
			fillLocationItemAndPlanNameInHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
					VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("AutomationPlanText"));
			fillFromToDateAndInspectionType(Today, Today, commonFunc.getDataFromTestDataSheet("SingleItemText"));
			clickSaveHeaderInInspectionPlan();
			clickAddLinesInspectionPlan();
			fillCharacteristicsProcessIdModOfIns(commonFunc.getDataFromTestDataSheet("Surface_CharText"),
					commonFunc.getDataFromTestDataSheet("GwlProcess1"),
					commonFunc.getDataFromTestDataSheet("RefText_Test"));
			fillUnitofMeasureAndAttributeSpec(commonFunc.getDataFromTestDataSheet("Kgs"),
					commonFunc.getDataFromTestDataSheet("RubberTypeText"));
			fillNoOfSampleAndObservations("1", "1");
			clickSaveLinesInspectionPlan();
			clickAddLinesInspectionPlan();
			fillCharacteristicsProcessIdModOfIns(commonFunc.getDataFromTestDataSheet("Radical_CharText"),
					commonFunc.getDataFromTestDataSheet("GwlProcess1"),
					commonFunc.getDataFromTestDataSheet("RefText_Test"));
			fillUnitofMeasureAndStdOperatorAndSpecValue(commonFunc.getDataFromTestDataSheet("Kgs"), "ET", "30");
			fillNoOfSampleAndObservations("1", "1");
			clickSaveLinesInspectionPlan();
			clickAddLinesInspectionPlan();
			fillCharacteristicsProcessIdModOfIns(commonFunc.getDataFromTestDataSheet("VisualQC_CharText"),
					commonFunc.getDataFromTestDataSheet("GwlProcess1"),
					commonFunc.getDataFromTestDataSheet("RefText_Test"));
			fillUnitofMeasureAndStdValText(commonFunc.getDataFromTestDataSheet("Kgs"), "30");
			fillNoOfSampleAndObservations("1", "1");
			clickSaveLinesInspectionPlan();
			clickSaveHeaderInInspectionPlan();
			clickPostButtonInInspectionPlan();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getInspectionPlanDocNo(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Loc_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			productionOrderFunc.clickAddIconInCreationProductionOrder();
			productionOrderFunc.createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"), VariableHandler.getBOMNumber(), "3");
			productionOrderFunc.clickSaveIconInCreationProductionOrder();
			productionOrderFunc.selectFirstRestOprEventvaluesAndSaveHeader(
					commonFunc.getDataFromTestDataSheet("OrderReleaseOption"),
					commonFunc.getDataFromTestDataSheet("OrderReleaseOption"));
			productionOrderFunc.navigateToRoutingSubTab();
			productionOrderFunc.storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			productionOrderFunc.navigateToMaterialRequirementSubTab();
			productionOrderFunc.verifyItemInMaterialRequirement(new String[] { RMItem });
			productionOrderFunc.verifyProcessInMaterialRequirement(
					new String[] { commonFunc.getDataFromTestDataSheet("GwlProcess1") });
			productionOrderFunc.validateMaterialReqrdQuantityWithRMItemProcess(
					commonFunc.getDataFromTestDataSheet("GwlProcess1"), RMItem);
			productionOrderFunc.navigateToResourcesSubTab();
			productionOrderFunc
					.verifyProcessInResources(new String[] { commonFunc.getDataFromTestDataSheet("GwlProcess1") });
			productionOrderFunc.navigateToExceptionSubTab();
			productionOrderFunc.validateNoExceptionsShowsInExceptionTab();
			productionOrderFunc.clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			webDB.navigateToRefresh();
			commonFunc.navigateToInventoryMaterialRequest();
			materialRequestFunc.validateItemWHReqNameQuantityWithProdNo(VariableHandler.getProductionOrderNumber(),
					RMItem, commonFunc.getDataFromTestDataSheet("RMU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			materialRequestFunc.storeMRNoAndCheckStatusApproveWithProdNo(VariableHandler.getProductionOrderNumber());
			commonFunc.navigateToMaterialIssuanceVoucherTab();
			materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
			materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
			commonFunc.navigateToMaterialIssuanceVoucherTab();
			materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
					commonFunc.getDataFromTestDataSheet("PostedStatus"));
			commonFunc.navigateSetupInventoryPage();
			flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
			commonFunc.navigateToMaterialReceiptVoucher();
			if (flag) {
				materialReceiptFunctions
						.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(VariableHandler.getProductionOrderNumber());
			} else {
				materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
			}
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndWarehousesBOM()[i][0];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				if (i == (VariableHandler.getProcessAndWarehousesBOM().length - 1)) {
					ConsumptionWH = commonFunc.getDataFromTestDataSheet("FG-WH-KD");
				}
				logs.logging("INFO", "ProcessName: " + ProcessName);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
						commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
						VariableHandler.getProductionCompletionOrderNo(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				commonFunc.navigateToMaterialReceiptVoucher();
				commonFunc.clickPendingIcon();
				materialReceiptFunctions.clickPendingInssuanceBtn();
				materialReceiptFunctions.searchWithVoucherNumber(VariableHandler.getProductionCompletionOrderNo());
				materialReceiptFunctions.validateProcessRecipientWarehouseWithCompletionProdNo(
						VariableHandler.getProductionCompletionOrderNo(), ProcessName, ConsumptionWH);
				materialReceiptFunctions
						.selectVoucherNoAndClickCreateReceipt(VariableHandler.getProductionCompletionOrderNo());
				materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
				materialReceiptFunctions.clickTickAndPostIcon();
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			productionOrderFunc.searchAndVerifyStatusWithProdOrderNo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			commonFunc.navigateInspectionRequest();
			insRequestFunc.clickAddIconInInspectionRequest();
			insRequestFunc.fillDateVoucherTypeAndPrefixInCreationFrame(Today,
					commonFunc.getDataFromTestDataSheet("PreDispatchText"),
					commonFunc.getDataFromTestDataSheet("PreDispatchPrefix"));
			insRequestFunc.fillWarehouseAndInspectorInCreationFrame(
					commonFunc.getDataFromTestDataSheet("FinishedGoodWH"),
					commonFunc.getDataFromTestDataSheet("GWL-RInspector"));
			insRequestFunc.clickLoadButtonInInspectionRequest();
			insRequestFunc.searchItemNameInPendingLoadAndCreate(VariableHandler.getItemName(), "3");
			insRequestFunc.clickSaveHeaderAndPostDocument();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getInspectionReqNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateInspectionDocumentsPage();
			insDocumentFunc
					.verifyCreatePendingInspectionDocumentWithInsReqNumber(VariableHandler.getInspectionReqNumber());
			insDocumentFunc.clickTickAndPostIcon();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getIDNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyInspectionPlanPreDispatch': " + e);
			return false;
		}
	}

	/**
	 * Define Inspection Plan - Final Inspection?
	 * 
	 * @author Sakthi-GWL
	 * @return flag
	 */
	public boolean verifyInspectionPlanFinalInspection() {
		String BOMName = commonFunc.getDataFromTestDataSheet("BomNamePreposition") + RandomStringUtils.randomNumeric(4);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String Today = date.format(formatter);
		String FGItem = commonFunc.getDataFromTestDataSheet("FinalInspectionItem");
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			priceListFunc.searchItemAndCopyWithName(FGItem, (FGItem.concat(" - ")));
			commonFunc.navigateToBillOfMaterialsPage();
			bomFunc.clickAddIconAndFillUnitBOMNameAndItem(commonFunc.getDataFromTestDataSheet("Unit_100"), BOMName,
					VariableHandler.getItemName());
			bomFunc.enterEffFromToDateAndSaveHeader(Today, Today);
			bomFunc.navigateToRoutingSubTab();
			bomFunc.clickAddLineIconInRoutingTab();
			bomFunc.fillProcSeqNoOprnNoAndProcessIdInRouting("1", "1",
					commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			bomFunc.fillTypeAndWorkCenterInRouting(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"));
			bomFunc.clickIRFlagInRoutingTabForProcess(commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			bomFunc.clickSaveLineIconInRoutingTab();
			bomFunc.clickDetailsForAddedProcessInBOM(commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			bomFunc.chooseConsumptionAndReceiptWHInViewDetails(commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			bomFunc.navigateToMaterialsSubTab();
			bomFunc.clickAddLineIconInMaterialTab();
			bomFunc.fillProcessItemUOMInMaterial(commonFunc.getDataFromTestDataSheet("GwlProcess1"), RMItem,
					commonFunc.getDataFromTestDataSheet("Kgs"));
			bomFunc.fillReqQntyConvFactorAndSourceWHInMaterial("3", "1",
					commonFunc.getDataFromTestDataSheet("RMU100_WH"));
			bomFunc.clickSaveLineIconInMaterialTab();
			bomFunc.navigateToBORSubTab();
			bomFunc.clickAddLineIconInBORTab();
			bomFunc.fillLineSnoAndProcessIdInBORTab("1", commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			bomFunc.fillResourceTypeAndResourceInBORTab(commonFunc.getDataFromTestDataSheet("ResourceText"),
					commonFunc.getDataFromTestDataSheet("AutomationResource"));
			bomFunc.fillSetupHrsMinAndSetupLotQntyInBORTab("0", "15", "0");
			bomFunc.fillCycleTimeUnitOrHrsMinAndSecInBORTab("3", "0", "0", "0");
			bomFunc.fillCycleTimeBatchQntyAndHrsInBORTab("0", "0");
			bomFunc.fillLagPriorityAndNoOfUnitsInBORTab("0", "1", "3");
			bomFunc.clickSaveLineIconInBORTab();
			bomFunc.clickHeaderSaveAndPostButtonInBOM();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getBOMNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToBillOfMaterialsPage();
			bomFunc.searchBOMNoAndVerifyStatus(VariableHandler.getBOMNumber(),
					commonFunc.getDataFromTestDataSheet("ActiveStatus"));
			bomFunc.navigateToBOMBySearchAndClickBOMNo(VariableHandler.getBOMNumber());
			bomFunc.chooseBOMTypeAndSaveHeader(commonFunc.getDataFromTestDataSheet("PrimaryType"));
			commonFunc.navigateInspectionPlan();
			clickAddIconAndFinalInspectionInInsPlan();
			fillLocationItemAndPlanNameInHeader(commonFunc.getDataFromTestDataSheet("Loc_100"), FGItem,
					commonFunc.getDataFromTestDataSheet("AutomationPlanText"));
			fillFromToDateAndInspectionType(Today, Today, commonFunc.getDataFromTestDataSheet("SingleItemText"));
			clickSaveHeaderInInspectionPlan();
			clickAddLinesInspectionPlan();
			fillCharacteristicsProcessIdModOfIns(commonFunc.getDataFromTestDataSheet("Surface_CharText"),
					commonFunc.getDataFromTestDataSheet("GwlProcess1"),
					commonFunc.getDataFromTestDataSheet("RefText_Test"));
			fillUnitofMeasureAndAttributeSpec(commonFunc.getDataFromTestDataSheet("Kgs"),
					commonFunc.getDataFromTestDataSheet("RubberTypeText"));
			fillNoOfSampleAndObservations("1", "1");
			clickSaveLinesInspectionPlan();
			clickAddLinesInspectionPlan();
			fillCharacteristicsProcessIdModOfIns(commonFunc.getDataFromTestDataSheet("Radical_CharText"),
					commonFunc.getDataFromTestDataSheet("GwlProcess1"),
					commonFunc.getDataFromTestDataSheet("RefText_Test"));
			fillUnitofMeasureAndStdOperatorAndSpecValue(commonFunc.getDataFromTestDataSheet("Kgs"), "ET", "30");
			fillNoOfSampleAndObservations("1", "1");
			clickSaveLinesInspectionPlan();
			clickAddLinesInspectionPlan();
			fillCharacteristicsProcessIdModOfIns(commonFunc.getDataFromTestDataSheet("VisualQC_CharText"),
					commonFunc.getDataFromTestDataSheet("GwlProcess1"),
					commonFunc.getDataFromTestDataSheet("RefText_Test"));
			fillUnitofMeasureAndStdValText(commonFunc.getDataFromTestDataSheet("Kgs"), "30");
			fillNoOfSampleAndObservations("1", "1");
			clickSaveLinesInspectionPlan();
			clickSaveHeaderInInspectionPlan();
			clickPostButtonInInspectionPlan();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getInspectionPlanDocNo(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Loc_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			productionOrderFunc.clickAddIconInCreationProductionOrder();
			productionOrderFunc.createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"), VariableHandler.getBOMNumber(), "3");
			productionOrderFunc.clickSaveIconInCreationProductionOrder();
			productionOrderFunc.selectFirstRestOprEventvaluesAndSaveHeader(
					commonFunc.getDataFromTestDataSheet("OrderReleaseOption"),
					commonFunc.getDataFromTestDataSheet("OrderReleaseOption"));
			productionOrderFunc.navigateToRoutingSubTab();
			productionOrderFunc.storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			productionOrderFunc.navigateToMaterialRequirementSubTab();
			productionOrderFunc.verifyItemInMaterialRequirement(new String[] { RMItem });
			productionOrderFunc.verifyProcessInMaterialRequirement(
					new String[] { commonFunc.getDataFromTestDataSheet("GwlProcess1") });
			productionOrderFunc.validateMaterialReqrdQuantityWithRMItemProcess(
					commonFunc.getDataFromTestDataSheet("GwlProcess1"), RMItem);
			productionOrderFunc.navigateToResourcesSubTab();
			productionOrderFunc
					.verifyProcessInResources(new String[] { commonFunc.getDataFromTestDataSheet("GwlProcess1") });
			productionOrderFunc.navigateToExceptionSubTab();
			productionOrderFunc.validateNoExceptionsShowsInExceptionTab();
			productionOrderFunc.clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			webDB.navigateToRefresh();
			commonFunc.navigateToInventoryMaterialRequest();
			materialRequestFunc.validateItemWHReqNameQuantityWithProdNo(VariableHandler.getProductionOrderNumber(),
					RMItem, commonFunc.getDataFromTestDataSheet("RMU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			materialRequestFunc.storeMRNoAndCheckStatusApproveWithProdNo(VariableHandler.getProductionOrderNumber());
			commonFunc.navigateToMaterialIssuanceVoucherTab();
			materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
			materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
			commonFunc.navigateToMaterialIssuanceVoucherTab();
			materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
					commonFunc.getDataFromTestDataSheet("PostedStatus"));
			commonFunc.navigateSetupInventoryPage();
			flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
			commonFunc.navigateToMaterialReceiptVoucher();
			if (flag) {
				materialReceiptFunctions
						.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(VariableHandler.getProductionOrderNumber());
			} else {
				materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
			}
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndWarehousesBOM()[i][0];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				if (i == (VariableHandler.getProcessAndWarehousesBOM().length - 1)) {
					ConsumptionWH = commonFunc.getDataFromTestDataSheet("FG-WH-KD");
				}
				logs.logging("INFO", "ProcessName: " + ProcessName);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
						commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
						VariableHandler.getProductionCompletionOrderNo(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				commonFunc.navigateInspectionDocumentsPage();
				insDocumentFunc.verifyCreatePendingInspectionDocumentWithProdNumber(
						VariableHandler.getProductionOrderNumber());
				insDocumentFunc.clickTickAndPostIcon();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getIDNumber(),
							webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
							System.getProperty("HariUsername"));
				}
				commonFunc.navigateInspectionDocumentsPage();
				insDocumentFunc.verifyValidateInspectionDocumentsStatus(VariableHandler.getIDNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.navigateToMaterialReceiptVoucher();
				commonFunc.clickPendingIcon();
				materialReceiptFunctions.clickPendingInssuanceBtn();
				materialReceiptFunctions.searchWithVoucherNumber(VariableHandler.getProductionCompletionOrderNo());
				materialReceiptFunctions.validateProcessRecipientWarehouseWithCompletionProdNo(
						VariableHandler.getProductionCompletionOrderNo(), ProcessName, ConsumptionWH);
				materialReceiptFunctions
						.selectVoucherNoAndClickCreateReceipt(VariableHandler.getProductionCompletionOrderNo());
				materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
				materialReceiptFunctions.clickTickAndPostIcon();
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			productionOrderFunc.searchAndVerifyStatusWithProdOrderNo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			insRequestFunc.clickAddIconInInspectionRequest();
			insRequestFunc.fillDateVoucherTypeAndPrefixInCreationFrame(Today,
					commonFunc.getDataFromTestDataSheet("StockQcText"),
					commonFunc.getDataFromTestDataSheet("StockQcPrefix"));
			insRequestFunc.fillWarehouseAndInspectorInCreationFrame(
					commonFunc.getDataFromTestDataSheet("FinishedGoodWH"),
					commonFunc.getDataFromTestDataSheet("GWL-RInspector"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyInspectionPlanFinalInspection': " + e);
			return false;
		}
	}

	/**
	 * This Method Is Used To Verify Create Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateInspectionPlan() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String Today = date.format(formatter);
		commonFunc.navigateInspectionPlan();
		clickAddIconAndClickIncomingInInsPlan();
		fillLocationItemAndPlanNameInHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("AutomationPlanText"));
		fillFromToDateAndInspectionType(Today, Today, commonFunc.getDataFromTestDataSheet("SingleItemText"));
		clickCreationTickIconInHeader();
		clickAddLinesInspectionPlan();
		fillCharacteristicsProcessIdModOfIns(commonFunc.getDataFromTestDataSheet("Radical_CharText"),
				commonFunc.getDataFromTestDataSheet("GwlProcess1"),
				commonFunc.getDataFromTestDataSheet("RefText_Test"));
		fillUnitofMeasureAndStdOperatorAndSpecValue(commonFunc.getDataFromTestDataSheet("Kgs"), "ET", "30");
		fillNoOfSampleAndObservations("1", "1");
		clickSaveLinesInspectionPlan();
		clickAddLinesInspectionPlan();
		fillCharacteristicsProcessIdModOfIns(commonFunc.getDataFromTestDataSheet("VisualQC_CharText"),
				commonFunc.getDataFromTestDataSheet("GwlProcess1"),
				commonFunc.getDataFromTestDataSheet("RefText_Test"));
		fillUnitofMeasureAndStdValText(commonFunc.getDataFromTestDataSheet("Kgs"), "30");
		fillNoOfSampleAndObservations("1", "1");
		clickSaveLinesInspectionPlan();
		clickAddLinesInspectionPlan();
		fillCharacteristicsProcessIdModOfIns(commonFunc.getDataFromTestDataSheet("Surface_CharText"),
				commonFunc.getDataFromTestDataSheet("GwlProcess1"),
				commonFunc.getDataFromTestDataSheet("RefText_Test"));
		fillUnitofMeasureAndAttributeSpec(commonFunc.getDataFromTestDataSheet("Kgs"),
				commonFunc.getDataFromTestDataSheet("RubberTypeText"));
		fillNoOfSampleAndObservations("1", "1");
		clickSaveLinesInspectionPlan();
		clickSaveHeaderInInspectionPlan();
		clickPostButtonInInspectionPlan();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getInspectionPlanDocNo(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Loc_100"), System.getProperty("HariUsername"));
		}
	}

	/**
	 * This method used to click add and Incoming Inspection Plan
	 * 
	 * @author Deepan-GWL
	 */
	public void clickAddIconAndClickIncomingInInsPlan() {
		flag = webDB.waitForElement(InspectionPlanLocators.AddIconInFindPage, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(InspectionPlanLocators.AddIconInFindPage, ElementType.Xpath);
			switchInsPlanCreateIframe();
			flag = webDB.waitForElement(InspectionPlanLocators.CreateIncomingButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(InspectionPlanLocators.CreateIncomingButton, ElementType.Xpath);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(InspectionPlanLocators.HeaderLocationLovBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Inspection Plan 'Inprocess' Type Created Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Inspection Plan For Inprocess
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateInspectionPlanForInprocess() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String Today = date.format(formatter);
		commonFunc.navigateInspectionPlan();
		clickAddIconAndClickInprocessInInsPlan();
		fillLocationItemAndPlanNameInHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("AutomationPlanText"));
		fillFromToDateAndInspectionTypeInProcess(Today, Today, commonFunc.getDataFromTestDataSheet("SingleItemText"));
		clickCreationTickIconInHeader();
		clickAddLinesInspectionPlan();
		fillCharacteristicsProcessIdModOfInsForInProcess(commonFunc.getDataFromTestDataSheet("Radical_CharText"),
				commonFunc.getDataFromTestDataSheet("GwlProcess1"),
				commonFunc.getDataFromTestDataSheet("RefText_Test"));
		fillUnitofMeasureAndStdOperatorAndSpecValueForInProcess(commonFunc.getDataFromTestDataSheet("Kgs"), "ET", "30");
		fillNoOfSampleAndObservationsForInProcess("1", "1");
		clickSaveLinesInspectionPlan();
		clickAddLinesInspectionPlan();
		fillCharacteristicsProcessIdModOfInsForInProcess(commonFunc.getDataFromTestDataSheet("VisualQC_CharText"),
				commonFunc.getDataFromTestDataSheet("GwlProcess1"),
				commonFunc.getDataFromTestDataSheet("RefText_Test"));
		fillUnitofMeasureAndStdValTextForInProcess(commonFunc.getDataFromTestDataSheet("Kgs"), "30");
		fillNoOfSampleAndObservationsForInProcess("1", "1");
		clickSaveLinesInspectionPlan();
//		clickAddLinesInspectionPlan();
//		fillCharacteristicsProcessIdModOfInsForInProcess(commonFunc.getDataFromTestDataSheet("Surface_CharText"),
//				commonFunc.getDataFromTestDataSheet("GwlProcess1"),
//				commonFunc.getDataFromTestDataSheet("RefText_Test"));
//		fillUnitofMeasureAndAttributeSpecForInProcess(commonFunc.getDataFromTestDataSheet("Kgs"),
//				commonFunc.getDataFromTestDataSheet("RubberTypeText"));
//		fillNoOfSampleAndObservationsForInProcess("1", "1");
//		clickSaveLinesInspectionPlan();
		clickSaveHeaderInInspectionPlan();
		clickPostButtonInInspectionPlan();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getInspectionPlanDocNo(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Loc_100"), System.getProperty("HariUsername"));
		}
	}

	/**
	 * This method used to fill Details in Inspection Plan For In Process
	 * 
	 * @author Deepan-GWL
	 * @param FromDate
	 * @param ToDate
	 * @param Type
	 */
	public void fillFromToDateAndInspectionTypeInProcess(String FromDate, String ToDate, String Type) {
		commonFunc.verifyAddTableSpecs(InspectionPlanLocators.HeaderDateFromInput,
				InspectionPlanLocators.HeaderDateFromInput, FromDate);
		logs.logging("INFO", "From Date Entered in Header of Inspection Plan: " + FromDate);
		commonFunc.verifyAddTableSpecs(InspectionPlanLocators.HeaderDateToInput,
				InspectionPlanLocators.HeaderDateToInput, ToDate);
		logs.logging("INFO", "To Date Entered in Header of Inspection Plan: " + ToDate);
		flag = webDB.waitForElement(InspectionPlanLocators.HeaderPlanTypeSelectInProcess, ElementType.Xpath);
		if (flag) {
			flag = webDB.selectDropDownByText(InspectionPlanLocators.HeaderPlanTypeSelectInProcess, "Single item",
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Plan Type Selected in header of Inspection Plan: " + Type);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to fill Details in Inspection Plan For InProcess
	 * 
	 * @author Deepan-GWL
	 * @param Character
	 * @param ProcessId
	 * @param Mod
	 */
	public void fillCharacteristicsProcessIdModOfInsForInProcess(String Character, String ProcessId, String Mod) {
		flag = webDB.waitForElement(InspectionPlanLocators.CharacteristicsTableData, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(InspectionPlanLocators.CharacteristicsTableData, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(InspectionPlanLocators.CharacteristicsTableLovButton, Character);
			logs.logging("INFO", "Character Selected in Lines of Inspection Plan: " + Character);
			flag = webDB.waitForElement(InspectionPlanLocators.ProcessIdTableData, ElementType.Xpath);
			if (flag) {
				webDB.doubleClickAnElement(InspectionPlanLocators.ProcessIdTableData, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(InspectionPlanLocators.ProcessIdTableLovButton, ProcessId);
				logs.logging("INFO", "Process Id Selected in Lines of Inspection Plan: " + ProcessId);
				commonFunc.verifyAddTableSpecs(InspectionPlanLocators.ModInspectionTableData,
						InspectionPlanLocators.ModInspectionTableInput, Mod);
				logs.logging("INFO", "Mod of Inspection Entered in Lines of Inspection Plan: " + Mod);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to fill Details in Inspection Plan For InProcess
	 * 
	 * @author Deepan-GWL
	 * @param UOM
	 * @param StdOperator
	 * @param SpecValue
	 */
	public void fillUnitofMeasureAndStdOperatorAndSpecValueForInProcess(String UOM, String StdOperator,
			String SpecValue) {
		flag = webDB.waitForElement(InspectionPlanLocators.UOMTableDataInProcess, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(InspectionPlanLocators.UOMTableDataInProcess, ElementType.Xpath);
			flag = webDB.waitForElement(InspectionPlanLocators.UOMTableSelectInProcess, ElementType.Xpath);
			if (flag) {
				flag = webDB.selectDropDownByText(InspectionPlanLocators.UOMTableSelectInProcess, UOM,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "UOM Selected in Lines of Inspection Plan: " + UOM);
					flag = webDB.waitForElement(InspectionPlanLocators.StdOperatorDataInProcess, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(InspectionPlanLocators.StdOperatorDataInProcess, ElementType.Xpath);
						flag = webDB.waitForElement(InspectionPlanLocators.StdOperatorSelectInProcess,
								ElementType.Xpath);
						if (flag) {
							flag = webDB.selectDropDownByValue(InspectionPlanLocators.StdOperatorSelectInProcess,
									StdOperator, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO",
										"Std Operator Selected in Lines of Inspection Plan: " + StdOperator);
								commonFunc.verifyAddTableSpecs(InspectionPlanLocators.SpecValueTableDataInProcess,
										InspectionPlanLocators.SpecValueTableInputInProcess, SpecValue);
								logs.logging("INFO", "Spec Value Entered in Lines of Inspection Plan: " + SpecValue);
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to fill Details in Inspection Plan For In Process
	 * 
	 * @author Deepan-GWL
	 * @param UOM
	 * @param StdValText
	 */
	public void fillUnitofMeasureAndStdValTextForInProcess(String UOM, String StdValText) {
		flag = webDB.waitForElement(InspectionPlanLocators.UOMTableDataInProcess, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(InspectionPlanLocators.UOMTableDataInProcess, ElementType.Xpath);
			flag = webDB.waitForElement(InspectionPlanLocators.UOMTableSelectInProcess, ElementType.Xpath);
			if (flag) {
				flag = webDB.selectDropDownByText(InspectionPlanLocators.UOMTableSelectInProcess, UOM,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "UOM Selected in Lines of Inspection Plan: " + UOM);
					commonFunc.verifyAddTableSpecs(InspectionPlanLocators.StdValTextTableDataInProcess,
							InspectionPlanLocators.StdValTextTableInputInProcess, StdValText);
					logs.logging("INFO", "Std Value Text Entered in Lines of Inspection Plan: " + StdValText);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to fill Details in Inspection Plan
	 * 
	 * @author Deepan-GWL
	 * @param UOM
	 * @param Attribute
	 */
	public void fillUnitofMeasureAndAttributeSpecForInProcess(String UOM, String Attribute) {
		flag = webDB.waitForElement(InspectionPlanLocators.AttributeSpecTableDataInProcess, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(InspectionPlanLocators.AttributeSpecTableDataInProcess, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(InspectionPlanLocators.AttributeSpecTableLovButtonInProcess,
					Attribute);
			logs.logging("INFO", "Attribute Spec Selected in Lines of Inspection Plan: " + Attribute);
			flag = webDB.waitForElement(InspectionPlanLocators.UOMTableDataInProcess, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(InspectionPlanLocators.UOMTableDataInProcess, ElementType.Xpath);
				flag = webDB.waitForElement(InspectionPlanLocators.UOMTableSelectInProcess, ElementType.Xpath);
				if (flag) {
					flag = webDB.selectDropDownByText(InspectionPlanLocators.UOMTableSelectInProcess, UOM,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "UOM Selected in Lines of Inspection Plan: " + UOM);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Entering Higher And Lower Spec Value
	 * 
	 * @author Deepan-GWL
	 */
	public void enteringHigherAndLowerSpecValue() {
		commonFunc.verifyAddTableSpecs(InspectionPlanLocators.HigherMaxTableData,
				InspectionPlanLocators.HigherMaxTableInput, "20");
		commonFunc.verifyAddTableSpecs(InspectionPlanLocators.LowerMinTableData,
				InspectionPlanLocators.LowerMinTableInput, "20");
	}

	/**
	 * This method used to fill Details in Inspection Plan For In Process
	 * 
	 * @author Deepan-GWL
	 * @param UOM
	 * @param Attribute
	 */
	public void fillNoOfSampleAndObservationsForInProcess(String sampleCount, String ObservationCount) {
		commonFunc.verifyAddTableSpecs(InspectionPlanLocators.NoOfSampleDataInProcess,
				InspectionPlanLocators.NoOfSampleInputInProcess, sampleCount);
		logs.logging("INFO", "Sample Count entered in Lines of Inspection Plan: " + sampleCount);
		commonFunc.verifyAddTableSpecs(InspectionPlanLocators.NoOfObservationDataInProcess,
				InspectionPlanLocators.NoOfObservationInputInProcess, ObservationCount);
		logs.logging("INFO", "Observation Count entered in Lines of Inspection Plan: " + ObservationCount);
		assertTrue(flag);
	}
}
