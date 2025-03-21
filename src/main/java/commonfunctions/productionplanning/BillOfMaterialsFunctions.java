package commonfunctions.productionplanning;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks March 11, 2025
 */
import static org.testng.Assert.assertTrue;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.payroll.CommonPayrollFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.productionplanning.BillOfMaterialsLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class BillOfMaterialsFunctions {
	private static boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static CommonPayrollFunctions commonPayrollFunc = new CommonPayrollFunctions();

	/**
	 * This Method used to search BOM Number and verify Status with Expected Status
	 * 
	 * @author Sakthi-GWL
	 * @param BOMNo
	 * @param ExpectStatus
	 */
	public void searchBOMNoAndVerifyStatus(String BOMNo, String ExpectStatus) {
		commonFunc.searchAndSelectValueInFilters(BillOfMaterialsLocators.Find_BOMNoLovBtn, BOMNo);
		logs.logging("INFO", "BOM Number Entered in Find");
		flag = webDB.waitForClickElement(BillOfMaterialsLocators.BOM_FindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.BOM_FindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForClickElement(BillOfMaterialsLocators.Find_BOMStatusTableData(BOMNo), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Find Search Button Clicked and Filtered");
				commonFunc.checkRefreshCellsDisappear();
				String DispStatus = webDB.getTextFromElement(BillOfMaterialsLocators.Find_BOMStatusTableData(BOMNo),
						ElementType.Xpath);
				logs.logging("INFO", "Expected Status: " + ExpectStatus);
				logs.logging("INFO", "Displayed Status: " + DispStatus);
				flag = DispStatus.equals(ExpectStatus);
				if (flag) {
					logs.logging("INFO", "BOM Status Verified");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to verify navigate to BOM by search and click BOM Number
	 * 
	 * @author Sakthi-GWL
	 * @param BOMNo
	 */
	public void navigateToBOMBySearchAndClickBOMNo(String BOMNo) {
		commonFunc.searchAndSelectValueInFilters(BillOfMaterialsLocators.Find_BOMNoLovBtn, BOMNo);
		logs.logging("INFO", "BOM Number Entered in Find");
		flag = webDB.waitForClickElement(BillOfMaterialsLocators.BOM_FindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.BOM_FindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForClickElement(BillOfMaterialsLocators.BOMNoTableData(BOMNo), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Find Search Button Clicked and Filtered");
				webDB.clickAnElement(BillOfMaterialsLocators.BOMNoTableData(BOMNo), ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForClickElement(BillOfMaterialsLocators.DisplayBOMNumber, ElementType.Xpath);
				if (flag) {
					String DisplayNo = webDB.getTextFromElement(BillOfMaterialsLocators.DisplayBOMNumber,
							ElementType.Xpath);
					logs.logging("INFO", "Displayed BOM No: " + DisplayNo);
					flag = DisplayNo.equals(BOMNo);
					if (flag) {
						logs.logging("INFO", "BOM Clicked And Navigated Successfully");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to switch to Create BOM Frame
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchToCreateBOMIframe() {
		flag = webDB.waitForElement(BillOfMaterialsLocators.CreateBOMIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(BillOfMaterialsLocators.CreateBOMIframe, ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.CreateBOM_UnitLovBtn, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click add icon and fill details in creation of BOM
	 * 
	 * @author Sakthi-GWL
	 * @param Unit
	 * @param BOMName
	 * @param Item
	 */
	public void clickAddIconAndFillUnitBOMNameAndItem(String Unit, String BOMName, String Item) {
		flag = webDB.waitForElement(BillOfMaterialsLocators.BOM_CreateBtnPlusIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.BOM_CreateBtnPlusIcon, ElementType.Xpath);
			switchToCreateBOMIframe();
			logs.logging("INFO", "Create New BOM Add Button Clicked");
			commonFunc.searchAndSelectValueInFilters(BillOfMaterialsLocators.CreateBOM_UnitLovBtn, Unit);
			logs.logging("INFO", "Unit Selected: " + Unit);
			switchToCreateBOMIframe();
			commonPayrollFunc.addDetailInTableWithoutLovButton(BillOfMaterialsLocators.CreateBOM_BOMNameInput,
					BillOfMaterialsLocators.CreateBOM_BOMNameInput, BOMName);
			logs.logging("INFO", "BOM Name Entered: " + BOMName);
			commonFunc.searchAndSelectValueInFilters(BillOfMaterialsLocators.CreateBOM_ItemLovBtn, Item);
			logs.logging("INFO", "Item Selected: " + Item);
			switchToCreateBOMIframe();
			flag = webDB.waitForElement(BillOfMaterialsLocators.CreateBOM_TickIcon, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(BillOfMaterialsLocators.CreateBOM_TickIcon, ElementType.Xpath);
				webDB.switchToDefaultContent();
				flag = webDB.waitForClickElement(BillOfMaterialsLocators.DisplayBOMNumber, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Tick Icon Clicked and BOM Created");
					commonFunc.hardWait(2000);
					String BOMNo = webDB.getTextFromElement(BillOfMaterialsLocators.DisplayBOMNumber,
							ElementType.Xpath);
					VariableHandler.setBOMNumber(BOMNo);
					logs.logging("INFO", "Displayed BOM Number after created: " + BOMNo);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to enter Effective From and To Date and Click Save Header
	 * Icon in BOM
	 * 
	 * @author Sakthi-GWL
	 * @param fromDate
	 * @param toDate
	 */
	public void enterEffFromToDateAndSaveHeader(String fromDate, String toDate) {
		commonPayrollFunc.addDetailInTableWithoutLovButton(BillOfMaterialsLocators.CreateBOM_EffFromInput,
				BillOfMaterialsLocators.CreateBOM_EffFromInput, fromDate);
		logs.logging("INFO", "Effective From Date Selected: " + fromDate);
		commonPayrollFunc.addDetailInTableWithoutLovButton(BillOfMaterialsLocators.CreateBOM_EffToInput,
				BillOfMaterialsLocators.CreateBOM_EffToInput, toDate);
		logs.logging("INFO", "Effective To Date Selected: " + toDate);
		flag = webDB.waitForElement(BillOfMaterialsLocators.BOMSaveHeaderIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(BillOfMaterialsLocators.BOMSaveHeaderIcon, ElementType.Xpath);
			webDB.clickAnElement(BillOfMaterialsLocators.BOMSaveHeaderIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Save Header Clicked and Toast Message Displayed");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to Click Save Header and click Post Button in BOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickHeaderSaveAndPostButtonInBOM() {
		flag = webDB.waitForElement(BillOfMaterialsLocators.BOMSaveHeaderIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(BillOfMaterialsLocators.BOMSaveHeaderIcon, ElementType.Xpath);
			webDB.clickAnElement(BillOfMaterialsLocators.BOMSaveHeaderIcon, ElementType.Xpath);
			logs.logging("INFO", "Save Header Clicked and Toast Message Displayed");
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(2000);
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForClickElement(BillOfMaterialsLocators.BOMPostBtn, ElementType.Xpath);
			if (flag) {
				commonFunc.checkSuccessToastMessageDisappear();
				webDB.moveToElement(BillOfMaterialsLocators.BOMPostBtn, ElementType.Xpath);
				webDB.clickAnElement(BillOfMaterialsLocators.BOMPostBtn, ElementType.Xpath);
				flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to navigate Routing Tab in BOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToRoutingSubTab() {
		flag = webDB.waitForElement(BillOfMaterialsLocators.RoutingTab_Label, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.RoutingTab_Label, ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.RoutingAddLineBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Routing Sub tab Navigated Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click add icon in Line details in routing tab in BOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAddLineIconInRoutingTab() {
		flag = webDB.waitForElement(BillOfMaterialsLocators.RoutingAddLineBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(BillOfMaterialsLocators.RoutingAddLineBtn, ElementType.Xpath);
			webDB.clickAnElement(BillOfMaterialsLocators.RoutingAddLineBtn, ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.RoutingProcessIdData, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Line Icon Clicked in Routing tab");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Save icon in Line details in routing tab in BOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickSaveLineIconInRoutingTab() {
		flag = webDB.waitForElement(BillOfMaterialsLocators.RoutingSaveLineBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(BillOfMaterialsLocators.RoutingSaveLineBtn, ElementType.Xpath);
			webDB.clickAnElement(BillOfMaterialsLocators.RoutingSaveLineBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				logs.logging("INFO", "Save Line Icon Clicked in Routing tab");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to fill ProcSeqNo, Oprn No and Process Id in Routing tab Add
	 * Line Details in BOM
	 * 
	 * @author Sakthi-GWL
	 * @param ProcessNo
	 * @param OprnNo
	 * @param ProcessId
	 */
	public void fillProcSeqNoOprnNoAndProcessIdInRouting(String ProcessNo, String OprnNo, String ProcessId) {
		commonPayrollFunc.addDetailInTableWithoutLovButton(BillOfMaterialsLocators.RoutingProcSeqNoData,
				BillOfMaterialsLocators.RoutingProcSeqNoInput, ProcessNo);
		logs.logging("INFO", "Process No Entered: " + ProcessNo);
//		commonPayrollFunc.addDetailInTableWithoutLovButton(BillOfMaterialsLocators.RoutingOprnNoData,
//				BillOfMaterialsLocators.RoutingOprnNoInput, OprnNo);
//		logs.logging("INFO", "Oprn No Entered: " + OprnNo);
		flag = webDB.waitForElement(BillOfMaterialsLocators.RoutingProcessIdData, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.RoutingProcessIdData, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(BillOfMaterialsLocators.RoutingProcessIdLovBtn, ProcessId);
			logs.logging("INFO", "Process Id Selected: " + ProcessId);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to fill Type and Work Center in Routing tab Add Line Details
	 * in BOM
	 * 
	 * @author Sakthi-GWL
	 * @param Type
	 * @param WorkCenter
	 */
	public void fillTypeAndWorkCenterInRouting(String Type, String WorkCenter) {
		flag = webDB.waitForElement(BillOfMaterialsLocators.RoutingTypeData, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.RoutingTypeData, ElementType.Xpath);
			commonFunc.hardWait(500);
			flag = webDB.waitForClickElement(BillOfMaterialsLocators.RoutingTypeSelect, ElementType.Xpath);
			if (flag) {
				flag = webDB.selectDropDownByText(BillOfMaterialsLocators.RoutingTypeSelect, Type, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Process Type Selected: " + Type);
					flag = webDB.waitForElement(BillOfMaterialsLocators.RoutingWorkCenterData, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(BillOfMaterialsLocators.RoutingWorkCenterData, ElementType.Xpath);
						commonFunc.searchAndSelectValueInFilters(BillOfMaterialsLocators.RoutingWorkCenterLovBtn,
								WorkCenter);
						logs.logging("INFO", "Work Center Selected: " + WorkCenter);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click View Details in Routing Tab for Particular Process
	 * 
	 * @author Sakthi-GWL
	 * @param Process
	 */
	public void clickDetailsForAddedProcessInBOM(String Process) {
		flag = webDB.waitForElement(BillOfMaterialsLocators.RoutingDetailsBtnWithProcessName(Process),
				ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(BillOfMaterialsLocators.RoutingDetailsBtnWithProcessName(Process), ElementType.Xpath);
			webDB.clickAnElement(BillOfMaterialsLocators.RoutingDetailsBtnWithProcessName(Process), ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.RoutingViewDetailsIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "View Details Clicked for Process: " + Process);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to switch to View Details Frame
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchToViewDetailsFrame() {
		flag = webDB.waitForElement(BillOfMaterialsLocators.RoutingViewDetailsIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(BillOfMaterialsLocators.RoutingViewDetailsIframe, ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.RoutingViewDts_ConsumptionWHLovBtn, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to Consumption And Receipt WH In View Details and click Ok
	 * button
	 * 
	 * @author Sakthi-GWL
	 * @param ConsumptionWH
	 * @param ReceiptWH
	 */
	public void chooseConsumptionAndReceiptWHInViewDetails(String ConsumptionWH, String ReceiptWH) {
		switchToViewDetailsFrame();
		commonFunc.searchAndSelectValueInFilters(BillOfMaterialsLocators.RoutingViewDts_ConsumptionWHLovBtn,
				ConsumptionWH);
		switchToViewDetailsFrame();
		commonFunc.searchAndSelectValueInFilters(BillOfMaterialsLocators.RoutingViewDts_ReceiptWHLovBtn, ReceiptWH);
		switchToViewDetailsFrame();
		flag = webDB.waitForClickElement(BillOfMaterialsLocators.RoutingViewDts_OkBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(BillOfMaterialsLocators.RoutingViewDts_OkBtn, ElementType.Xpath);
			webDB.clickAnElement(BillOfMaterialsLocators.RoutingViewDts_OkBtn, ElementType.Xpath);
			webDB.switchToDefaultContent();
			commonFunc.hardWait(2000);
			flag = webDB.waitForClickElement(BillOfMaterialsLocators.RoutingAddLineBtn, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to navigate Materials Tab in BOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToMaterialsSubTab() {
		flag = webDB.waitForElement(BillOfMaterialsLocators.MaterialTab_Label, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.MaterialTab_Label, ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.MaterialAddLineBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Materials Sub tab Navigated Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click add icon in Line details in material tab in BOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAddLineIconInMaterialTab() {
		flag = webDB.waitForElement(BillOfMaterialsLocators.MaterialAddLineBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(BillOfMaterialsLocators.MaterialAddLineBtn, ElementType.Xpath);
			webDB.clickAnElement(BillOfMaterialsLocators.MaterialAddLineBtn, ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.MaterialProcessDescData, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Line Icon Clicked in Material tab");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Save icon in Line details in material tab in BOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickSaveLineIconInMaterialTab() {
		flag = webDB.waitForElement(BillOfMaterialsLocators.MaterialSaveLineBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(BillOfMaterialsLocators.MaterialSaveLineBtn, ElementType.Xpath);
			webDB.clickAnElement(BillOfMaterialsLocators.MaterialSaveLineBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				logs.logging("INFO", "Save Line Icon Clicked in Material tab");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to fill Process Item and UOM in Material tab in BOM
	 * 
	 * @author Sakthi-GWL
	 * @param Process
	 * @param Item
	 * @param UOM
	 */
	public void fillProcessItemUOMInMaterial(String Process, String Item, String UOM) {
		commonFunc.searchAndSelectValueInFilters(BillOfMaterialsLocators.MaterialProcessDescLovBtn, Process);
		logs.logging("INFO", "Process Selected in Material: " + Process);
		flag = webDB.waitForElement(BillOfMaterialsLocators.MaterialItemData, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.MaterialItemData, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(BillOfMaterialsLocators.MaterialItemLovBtn, Item);
			logs.logging("INFO", "Item Selected in Material: " + Item);
			flag = webDB.waitForElement(BillOfMaterialsLocators.MaterialUOMData, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(BillOfMaterialsLocators.MaterialUOMData, ElementType.Xpath);
				flag = webDB.waitForElement(BillOfMaterialsLocators.MaterialUOMSelect, ElementType.Xpath);
				if (flag) {
					flag = webDB.selectDropDownByText(BillOfMaterialsLocators.MaterialUOMSelect, UOM,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "UOM Selected in Material: " + UOM);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to fill Request Quantity, Convention Factor and Source
	 * Warehouse in Material tab in BOM
	 * 
	 * @author Sakthi-GWL
	 * @param ReqQnty
	 * @param ConvFac
	 * @param WH
	 */
	public void fillReqQntyConvFactorAndSourceWHInMaterial(String ReqQnty, String ConvFac, String WH) {
		commonPayrollFunc.addDetailInTableWithoutLovButton(BillOfMaterialsLocators.MaterialRequestQntyData,
				BillOfMaterialsLocators.MaterialRequestQntyInput, ReqQnty);
		logs.logging("INFO", "Request Quantity Enterted in Material: " + ReqQnty);
		commonPayrollFunc.addDetailInTableWithoutLovButton(BillOfMaterialsLocators.MaterialConventionFactorData,
				BillOfMaterialsLocators.MaterialConventionFactorInput, ConvFac);
		logs.logging("INFO", "Convention Factor Enterted in Material: " + ConvFac);
		flag = webDB.waitForElement(BillOfMaterialsLocators.MaterialSourceWHData, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.MaterialSourceWHData, ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.MaterialSourceWHSelect, ElementType.Xpath);
			if (flag) {
				flag = webDB.selectDropDownByText(BillOfMaterialsLocators.MaterialSourceWHSelect, WH,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Source Warehouse Selected in Material: " + WH);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to navigate BOR Tab in BOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToBORSubTab() {
		flag = webDB.waitForElement(BillOfMaterialsLocators.BORTab_Label, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.BORTab_Label, ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.BORAddLineBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "BOR Sub tab Navigated Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click add icon in Line details in BOR tab in BOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAddLineIconInBORTab() {
		flag = webDB.waitForElement(BillOfMaterialsLocators.BORAddLineBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(BillOfMaterialsLocators.BORAddLineBtn, ElementType.Xpath);
			webDB.clickAnElement(BillOfMaterialsLocators.BORAddLineBtn, ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.BORProcessIdData, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Line Icon Clicked in BOR tab");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Save icon in Line details in BOR tab in BOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickSaveLineIconInBORTab() {
		flag = webDB.waitForElement(BillOfMaterialsLocators.BORSaveLineBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(BillOfMaterialsLocators.BORSaveLineBtn, ElementType.Xpath);
			webDB.clickAnElement(BillOfMaterialsLocators.BORSaveLineBtn, ElementType.Xpath);
			webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			logs.logging("INFO", "Save Line Icon Clicked in BOR tab");
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to fill Line Sno and Process Id in BOR tab in BOM
	 * 
	 * @author Sakthi-GWL
	 * @param LineSno
	 * @param ProcessId
	 */
	public void fillLineSnoAndProcessIdInBORTab(String LineSno, String ProcessId) {
		commonPayrollFunc.addDetailInTableWithoutLovButton(BillOfMaterialsLocators.BORLineSnoData,
				BillOfMaterialsLocators.BORLineSnoInput, LineSno);
		logs.logging("INFO", "Line Sno Entered: " + LineSno);
		flag = webDB.waitForElement(BillOfMaterialsLocators.BORProcessIdData, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.BORProcessIdData, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(BillOfMaterialsLocators.BORProcessIdLovBtn, ProcessId);
			logs.logging("INFO", "Process Id Selected: " + ProcessId);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to fill Resource Type and Resource in BOR tab in BOM
	 * 
	 * @author Sakthi-GWL
	 * @param type
	 * @param resource
	 */
	public void fillResourceTypeAndResourceInBORTab(String type, String resource) {
		flag = webDB.waitForElement(BillOfMaterialsLocators.BORResourceTypeData, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.BORResourceTypeData, ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.BORResourceTypeSelect, ElementType.Xpath);
			if (flag) {
				flag = webDB.selectDropDownByText(BillOfMaterialsLocators.BORResourceTypeSelect, type,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Resource Type Selected: " + type);
					flag = webDB.waitForElement(BillOfMaterialsLocators.BORResourceData, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(BillOfMaterialsLocators.BORResourceData, ElementType.Xpath);
						commonFunc.searchAndSelectValueInFilters(BillOfMaterialsLocators.BORResourceLovBtn, resource);
						logs.logging("INFO", "Resource Selected: " + resource);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to fill Setup Hrs Minutes and Setup Lot Quantity in BOR tab
	 * in BOM
	 * 
	 * @author Sakthi-GWL
	 * @param Hrs
	 * @param Min
	 * @param LotQnty
	 */
	public void fillSetupHrsMinAndSetupLotQntyInBORTab(String Hrs, String Min, String LotQnty) {
		commonPayrollFunc.addDetailInTableWithoutLovButton(BillOfMaterialsLocators.BORSetupHrsData,
				BillOfMaterialsLocators.BORSetupHrsInput, Hrs);
		commonPayrollFunc.addDetailInTableWithoutLovButton(BillOfMaterialsLocators.BORSetupMinsData,
				BillOfMaterialsLocators.BORSetupMinsInput, Min);
		commonPayrollFunc.addDetailInTableWithoutLovButton(BillOfMaterialsLocators.BORSetupLotQntyData,
				BillOfMaterialsLocators.BORSetupLotQntyInput, LotQnty);
	}

	/**
	 * This Method used to fill Cycle Time Unit per Hour and Cycle Time Hours &
	 * Minutes & Seconds in BOR tab in BOM
	 * 
	 * @author Sakthi-GWL
	 * @param TimeUnit
	 * @param Hrs
	 * @param Mins
	 * @param Sec
	 */
	public void fillCycleTimeUnitOrHrsMinAndSecInBORTab(String TimeUnit, String Hrs, String Mins, String Sec) {
		commonPayrollFunc.addDetailInTableWithoutLovButton(BillOfMaterialsLocators.BORCycleTimeUnitPerHourData,
				BillOfMaterialsLocators.BORCycleTimeUnitPerHourInput, TimeUnit);
		commonPayrollFunc.addDetailInTableWithoutLovButton(BillOfMaterialsLocators.BORCycleTimeHourData,
				BillOfMaterialsLocators.BORCycleTimeHourInput, Hrs);
		commonPayrollFunc.addDetailInTableWithoutLovButton(BillOfMaterialsLocators.BORCycleTimeMinData,
				BillOfMaterialsLocators.BORCycleTimeMinInput, Mins);
		commonPayrollFunc.addDetailInTableWithoutLovButton(BillOfMaterialsLocators.BORCycleTimeSecData,
				BillOfMaterialsLocators.BORCycleTimeSecInput, Sec);
	}

	/**
	 * This Method used to fill Cycle Time Batch Hours and Quantity in BOR tab in
	 * BOM
	 * 
	 * @author Sakthi-GWL
	 * @param BatchQnty
	 * @param BatchHrs
	 */
	public void fillCycleTimeBatchQntyAndHrsInBORTab(String BatchQnty, String BatchHrs) {
		commonPayrollFunc.addDetailInTableWithoutLovButton(BillOfMaterialsLocators.BORCycleTimeBatchQntyData,
				BillOfMaterialsLocators.BORCycleTimeBatchQntyInput, BatchQnty);
		commonPayrollFunc.addDetailInTableWithoutLovButton(BillOfMaterialsLocators.BORCycleTimeBatchHrsData,
				BillOfMaterialsLocators.BORCycleTimeBatchHrsInput, BatchHrs);
	}

	/**
	 * This Method used to fill Lag Priority and No Of Units in BOR tab in BOM
	 * 
	 * @author Sakthi-GWL
	 * @param Lag
	 * @param Priority
	 * @param NoOfUnit
	 */
	public void fillLagPriorityAndNoOfUnitsInBORTab(String Lag, String Priority, String NoOfUnit) {
		commonPayrollFunc.addDetailInTableWithoutLovButton(BillOfMaterialsLocators.BORLagHrsData,
				BillOfMaterialsLocators.BORLagHrsInput, Lag);
		commonPayrollFunc.addDetailInTableWithoutLovButton(BillOfMaterialsLocators.BORPriorityData,
				BillOfMaterialsLocators.BORPriorityInput, Priority);
		commonPayrollFunc.addDetailInTableWithoutLovButton(BillOfMaterialsLocators.BORNoOfUnitsData,
				BillOfMaterialsLocators.BORNoOfUnitsInput, NoOfUnit);
	}

	/**
	 * This Method used to navigate Scrap By Product Tab in BOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToScrapByProductSubTab() {
		flag = webDB.waitForElement(BillOfMaterialsLocators.ScrapByProductTab_Label, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.ScrapByProductTab_Label, ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.ScrapProductAddLineBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Scrap By Product Sub tab Navigated Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click add icon in Line details in Scrap By Product tab in
	 * BOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAddLineIconInScrapProductTab() {
		flag = webDB.waitForElement(BillOfMaterialsLocators.ScrapProductAddLineBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(BillOfMaterialsLocators.ScrapProductAddLineBtn, ElementType.Xpath);
			webDB.clickAnElement(BillOfMaterialsLocators.ScrapProductAddLineBtn, ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.ScrapProductLineSnoData, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Line Icon Clicked in Scrap By Product tab");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Save icon in Line details in Scrap By Product tab
	 * in BOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickSaveLineIconInScrapProductTab() {
		flag = webDB.waitForElement(BillOfMaterialsLocators.ScrapProductSaveLineBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(BillOfMaterialsLocators.ScrapProductSaveLineBtn, ElementType.Xpath);
			webDB.clickAnElement(BillOfMaterialsLocators.ScrapProductSaveLineBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				logs.logging("INFO", "Save Line Icon Clicked in Scrap By Product tab");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to fill Line Sno, Process And Item in Scrap By Product tab
	 * in BOM
	 * 
	 * @author Sakthi-GWL
	 * @param LineSno
	 * @param Process
	 * @param Item
	 */
	public void fillLineSnoProcessDescAndItemInScrapProductTab(String LineSno, String Process, String Item) {
		commonPayrollFunc.addDetailInTableWithoutLovButton(BillOfMaterialsLocators.ScrapProductLineSnoData,
				BillOfMaterialsLocators.ScrapProductLineSnoInput, LineSno);
		logs.logging("INFO", "Line Sno Entered: " + LineSno);
		flag = webDB.waitForElement(BillOfMaterialsLocators.ScrapProductProcessDescData, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.ScrapProductProcessDescData, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(BillOfMaterialsLocators.ScrapProductProcessDescLovBtn, Process);
			logs.logging("INFO", "Process Desc Selected: " + Process);
			flag = webDB.waitForElement(BillOfMaterialsLocators.ScrapProductItemData, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(BillOfMaterialsLocators.ScrapProductItemData, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(BillOfMaterialsLocators.ScrapProductItemLovBtn, Item);
				logs.logging("INFO", "Item Selected: " + Item);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to fill Scrap Warehouse and Quantity Weight in Scrap By
	 * Product tab in BOM
	 * 
	 * @author Sakthi-GWL
	 * @param WH
	 * @param QntyWeight
	 */
	public void fillWarehouseAndQntyWeightInScrapProductTab(String WH, String QntyWeight) {
		commonPayrollFunc.addDetailInTableWithoutLovButton(BillOfMaterialsLocators.ScrapProductQntyWeightData,
				BillOfMaterialsLocators.ScrapProductQntyWeightInput, QntyWeight);
		logs.logging("INFO", "Quantity Weight Entered: " + QntyWeight);
		flag = webDB.waitForElement(BillOfMaterialsLocators.ScrapProductWarehouseData, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.ScrapProductWarehouseData, ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.ScrapProductWarehouseSelect, ElementType.Xpath);
			if (flag) {
				flag = webDB.selectDropDownByText(BillOfMaterialsLocators.ScrapProductWarehouseSelect, WH,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Scrap Warehouse Selected: " + WH);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to select Process type in Scrap By Product tab in BOM
	 * 
	 * @author Sakthi-GWL
	 * @param ProcessType
	 */
	public void chooseProcessTypeRadioBtnInScrapProductTab(String ProcessType) {
		flag = webDB.waitForElement(BillOfMaterialsLocators.ScrapProductProcessTypeData, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.ScrapProductProcessTypeData, ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.ScrapProductProcessTypeLabel(ProcessType),
					ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(BillOfMaterialsLocators.ScrapProductProcessTypeLabel(ProcessType),
						ElementType.Xpath);
				commonFunc.hardWait(1000);
				webDB.clickAnElement(BillOfMaterialsLocators.ScrapProductWarehouseData, ElementType.Xpath);
				flag = webDB.getTextFromElement(BillOfMaterialsLocators.ScrapProductProcessTypeData, ElementType.Xpath)
						.equalsIgnoreCase(ProcessType);
				if (flag) {
					logs.logging("INFO", "Process Type Selected: " + ProcessType);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to choose BOM type and Save Header in BOM
	 * 
	 * @author Sakthi-GWL
	 * @param Type
	 */
	public void chooseBOMTypeAndSaveHeader(String Type) {
		flag = webDB.waitForElement(BillOfMaterialsLocators.Header_BOMTypeSelect, ElementType.Xpath);
		if (flag) {
			flag = webDB.selectDropDownByText(BillOfMaterialsLocators.Header_BOMTypeSelect, Type, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "BOM Type Selected: " + Type);
				flag = webDB.waitForElement(BillOfMaterialsLocators.BOMSaveHeaderIcon, ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(BillOfMaterialsLocators.BOMSaveHeaderIcon, ElementType.Xpath);
					webDB.clickAnElement(BillOfMaterialsLocators.BOMSaveHeaderIcon, ElementType.Xpath);
					flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Save Header Clicked and Toast Message Displayed");
						if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
							webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

}
