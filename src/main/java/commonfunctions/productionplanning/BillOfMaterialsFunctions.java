package commonfunctions.productionplanning;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks March 11, 2025
 */
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.RandomStringUtils;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.payroll.CommonPayrollFunctions;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonfunctions.supplychainmanagement.PricelistPageFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.productionplanning.BillOfMaterialsLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class BillOfMaterialsFunctions {
	private boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static CommonPayrollFunctions commonPayrollFunc = new CommonPayrollFunctions();
	private static ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	private static PricelistPageFunctions priceListFunc = new PricelistPageFunctions();
	private static DBCommonFunctions dbFunction = new DBCommonFunctions();

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
		commonFunc.expandFindTable();
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
			commonFunc.verifyAddTableSpecs(BillOfMaterialsLocators.CreateBOM_BOMNameInput,
					BillOfMaterialsLocators.CreateBOM_BOMNameInput, BOMName);
			logs.logging("INFO", "BOM Name Entered: " + BOMName);
			commonFunc.searchAndSelectValueInFilters(BillOfMaterialsLocators.CreateBOM_ItemLovBtn, Item);
			logs.logging("INFO", "Item Selected: " + Item);
			switchToCreateBOMIframe();
			flag = webDB.waitForElement(BillOfMaterialsLocators.CreateBOM_TickIcon, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(BillOfMaterialsLocators.CreateBOM_TickIcon, ElementType.Xpath);
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.checkSuccessToastMessageDisappear();
				webDB.waitUntilElementToNotBeStale(BillOfMaterialsLocators.DisplayBOMNumber, ElementType.Xpath);
				flag = webDB.waitForElement(BillOfMaterialsLocators.DisplayBOMNumber, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Tick Icon Clicked and BOM Created");
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
		commonFunc.verifyAddTableSpecs(BillOfMaterialsLocators.CreateBOM_EffFromInput,
				BillOfMaterialsLocators.CreateBOM_EffFromInput, fromDate);
		logs.logging("INFO", "Effective From Date Selected: " + fromDate);
		commonFunc.verifyAddTableSpecs(BillOfMaterialsLocators.CreateBOM_EffToInput,
				BillOfMaterialsLocators.CreateBOM_EffToInput, toDate);
		logs.logging("INFO", "Effective To Date Selected: " + toDate);
		flag = webDB.waitForElement(BillOfMaterialsLocators.BOMSaveHeaderIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(BillOfMaterialsLocators.BOMSaveHeaderIcon, ElementType.Xpath);
			flag = webDB.clickAnElement(BillOfMaterialsLocators.BOMSaveHeaderIcon, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Save Header Clicked and Toast Message Displayed");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				commonFunc.checkSuccessToastMessageDisappear();
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
			commonFunc.checkSuccessToastMessageDisappear();
			webDB.moveToElement(BillOfMaterialsLocators.BOMSaveHeaderIcon, ElementType.Xpath);
			webDB.clickAnElement(BillOfMaterialsLocators.BOMSaveHeaderIcon, ElementType.Xpath);
			logs.logging("INFO", "Save Header Clicked and Toast Message Displayed");
			commonFunc.checkRefreshCellsDisappear();
			webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 5);
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
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
			flag = webDB.waitForElement(BillOfMaterialsLocators.RoutingProcessIdData(), ElementType.Xpath);
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
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				commonFunc.checkSuccessToastMessageDisappear();
				logs.logging("INFO", "Save Line Icon Clicked in Routing tab");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Inspection Required in Line details in routing tab
	 * in BOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickIRFlagInRoutingTabForProcess(String process) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(BillOfMaterialsLocators.RoutingIRFlagData(), ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(BillOfMaterialsLocators.RoutingIRDiv(process), ElementType.Xpath);
			flag = webDB.javaScriptClickAnElement(BillOfMaterialsLocators.RoutingIRDiv(process), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Inspection Required Checkbox Selected");
			} else {
				logs.logging("INFO", "Inspection Required Checkbox Not Selected.. Check Manually");
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
		commonFunc.verifyAddTableSpecs(BillOfMaterialsLocators.RoutingProcSeqNoData(),
				BillOfMaterialsLocators.RoutingProcSeqNoInput(), ProcessNo);
		logs.logging("INFO", "Process No Entered: " + ProcessNo);
		commonFunc.verifyAddTableSpecs(BillOfMaterialsLocators.RoutingOprnNoData(),
				BillOfMaterialsLocators.RoutingOprnNoInput(), OprnNo);
		logs.logging("INFO", "Oprn No Entered: " + OprnNo);
		flag = webDB.waitForElement(BillOfMaterialsLocators.RoutingProcessIdData(), ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(BillOfMaterialsLocators.RoutingProcessIdData(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(BillOfMaterialsLocators.RoutingProcessIdLovBtn(), ProcessId);
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
		flag = webDB.waitForElement(BillOfMaterialsLocators.RoutingTypeData(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.RoutingTypeData(), ElementType.Xpath);
			commonFunc.hardWait(500);
			flag = webDB.waitForClickElement(BillOfMaterialsLocators.RoutingTypeSelect(), ElementType.Xpath);
			if (flag) {
				flag = webDB.selectDropDownByText(BillOfMaterialsLocators.RoutingTypeSelect(), Type, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Process Type Selected: " + Type);
					flag = webDB.waitForElement(BillOfMaterialsLocators.RoutingWorkCenterData(), ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(BillOfMaterialsLocators.RoutingWorkCenterData(), ElementType.Xpath);
						commonFunc.searchAndSelectValueInFilters(BillOfMaterialsLocators.RoutingWorkCenterLovBtn(),
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
			webDB.javaScriptClickAnElement(BillOfMaterialsLocators.RoutingDetailsBtnWithProcessName(Process),
					ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.Details_RoutingThreeDotsMenu, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Three Dots Menu Clicked for Process: " + Process);
				webDB.moveToElement(BillOfMaterialsLocators.Details_RoutingThreeDotsMenu, ElementType.Xpath);
				webDB.clickAnElement(BillOfMaterialsLocators.Details_RoutingThreeDotsMenu, ElementType.Xpath);
				flag = webDB.waitForElement(BillOfMaterialsLocators.RoutingViewDetailsIframe, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "View Details Clicked for Process: " + Process);
				}
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
			commonFunc.checkSuccessToastMessageDisappear();
			commonFunc.checkRefreshCellsDisappear();
			webDB.waitUntilElementToNotBeStale(BillOfMaterialsLocators.RoutingAddLineBtn, ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.RoutingAddLineBtn, ElementType.Xpath);
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
			flag = webDB.waitForElement(BillOfMaterialsLocators.MaterialProcessDescData(), ElementType.Xpath);
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
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				commonFunc.checkSuccessToastMessageDisappear();
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
		commonFunc.searchAndSelectValueInFilters(BillOfMaterialsLocators.MaterialProcessDescLovBtn(), Process);
		logs.logging("INFO", "Process Selected in Material: " + Process);
		flag = webDB.waitForElement(BillOfMaterialsLocators.MaterialItemData(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.MaterialItemData(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(BillOfMaterialsLocators.MaterialItemLovBtn(), Item);
			logs.logging("INFO", "Item Selected in Material: " + Item);
			flag = webDB.waitForElement(BillOfMaterialsLocators.MaterialUOMData(), ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(BillOfMaterialsLocators.MaterialUOMData(), ElementType.Xpath);
				flag = webDB.waitForElement(BillOfMaterialsLocators.MaterialUOMSelect(), ElementType.Xpath);
				if (flag) {
					flag = webDB.selectDropDownByText(BillOfMaterialsLocators.MaterialUOMSelect(), UOM,
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
		commonFunc.verifyAddTableSpecs(BillOfMaterialsLocators.MaterialRequestQntyData(),
				BillOfMaterialsLocators.MaterialRequestQntyInput(), ReqQnty);
		logs.logging("INFO", "Request Quantity Enterted in Material: " + ReqQnty);
		commonFunc.verifyAddTableSpecs(BillOfMaterialsLocators.MaterialConventionFactorData(),
				BillOfMaterialsLocators.MaterialConventionFactorInput(), ConvFac);
		logs.logging("INFO", "Convention Factor Enterted in Material: " + ConvFac);
		flag = webDB.waitForElement(BillOfMaterialsLocators.MaterialSourceWHData(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.MaterialSourceWHData(), ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.MaterialSourceWHSelect(), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Source Warehouse Selected in Material: " + WH);
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
			flag = webDB.waitForElement(BillOfMaterialsLocators.BORProcessIdData(), ElementType.Xpath);
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
			commonFunc.checkRefreshCellsDisappear();
			webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
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
		commonFunc.verifyAddTableSpecs(BillOfMaterialsLocators.BORLineSnoData(),
				BillOfMaterialsLocators.BORLineSnoInput(), LineSno);
		logs.logging("INFO", "Line Sno Entered: " + LineSno);
		flag = webDB.waitForElement(BillOfMaterialsLocators.BORProcessIdData(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.BORProcessIdData(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(BillOfMaterialsLocators.BORProcessIdLovBtn(), ProcessId);
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
		flag = webDB.waitForElement(BillOfMaterialsLocators.BORResourceTypeData(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.BORResourceTypeData(), ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.BORResourceTypeSelect(), ElementType.Xpath);
			if (flag) {
				flag = webDB.selectDropDownByText(BillOfMaterialsLocators.BORResourceTypeSelect(), type,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Resource Type Selected: " + type);
					flag = webDB.waitForElement(BillOfMaterialsLocators.BORResourceData(), ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(BillOfMaterialsLocators.BORResourceData(), ElementType.Xpath);
						commonFunc.searchAndSelectValueInFilters(BillOfMaterialsLocators.BORResourceLovBtn(), resource);
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
		commonFunc.verifyAddTableSpecs(BillOfMaterialsLocators.BORSetupHrsData(),
				BillOfMaterialsLocators.BORSetupHrsInput(), Hrs);
		commonFunc.verifyAddTableSpecs(BillOfMaterialsLocators.BORSetupMinsData(),
				BillOfMaterialsLocators.BORSetupMinsInput(), Min);
		commonFunc.verifyAddTableSpecs(BillOfMaterialsLocators.BORSetupLotQntyData(),
				BillOfMaterialsLocators.BORSetupLotQntyInput(), LotQnty);
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
		commonFunc.verifyAddTableSpecs(BillOfMaterialsLocators.BORCycleTimeUnitPerHourData(),
				BillOfMaterialsLocators.BORCycleTimeUnitPerHourInput(), TimeUnit);
		commonFunc.verifyAddTableSpecs(BillOfMaterialsLocators.BORCycleTimeHourData(),
				BillOfMaterialsLocators.BORCycleTimeHourInput(), Hrs);
		commonFunc.verifyAddTableSpecs(BillOfMaterialsLocators.BORCycleTimeMinData(),
				BillOfMaterialsLocators.BORCycleTimeMinInput(), Mins);
		commonFunc.verifyAddTableSpecs(BillOfMaterialsLocators.BORCycleTimeSecData(),
				BillOfMaterialsLocators.BORCycleTimeSecInput(), Sec);
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
		commonFunc.verifyAddTableSpecs(BillOfMaterialsLocators.BORCycleTimeBatchQntyData(),
				BillOfMaterialsLocators.BORCycleTimeBatchQntyInput(), BatchQnty);
		commonFunc.verifyAddTableSpecs(BillOfMaterialsLocators.BORCycleTimeBatchHrsData(),
				BillOfMaterialsLocators.BORCycleTimeBatchHrsInput(), BatchHrs);
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
		commonFunc.verifyAddTableSpecs(BillOfMaterialsLocators.BORLagHrsData(),
				BillOfMaterialsLocators.BORLagHrsInput(), Lag);
		commonFunc.verifyAddTableSpecs(BillOfMaterialsLocators.BORPriorityData(),
				BillOfMaterialsLocators.BORPriorityInput(), Priority);
		commonFunc.verifyAddTableSpecs(BillOfMaterialsLocators.BORNoOfUnitsData(),
				BillOfMaterialsLocators.BORNoOfUnitsInput(), NoOfUnit);
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
		commonFunc.verifyAddTableSpecs(BillOfMaterialsLocators.ScrapProductLineSnoData,
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
		commonFunc.verifyAddTableSpecs(BillOfMaterialsLocators.ScrapProductQntyWeightData,
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
					flag = webDB.clickAnElement(BillOfMaterialsLocators.BOMSaveHeaderIcon, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
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

	/**
	 * This method used to click Add Icon in BOM Migration
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAddIconInBOMMigration() {
		flag = webDB.waitForElement(BillOfMaterialsLocators.BOMMigrationAddBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.BOMMigrationAddBtn, ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.BOMMig_CreateIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "BOM Migration Create Icon Clicked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to switch Create Frame in BOM Migration
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchToCreateBOMMigrationFrame() {
		flag = webDB.waitForElement(BillOfMaterialsLocators.BOMMig_CreateIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(BillOfMaterialsLocators.BOMMig_CreateIframe, ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.BOMMig_CreateUnitLovBtn, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to fill details in BOM Migration Creation
	 * 
	 * @param Unit
	 * @param Ref
	 */
	public void fillDetailsInBOMMigrationCreation(String Unit, String Ref) {
		switchToCreateBOMMigrationFrame();
		logs.logging("INFO", "Create Iframe Switched in BOM Migration");
		commonFunc.searchAndSelectValueInFilters(BillOfMaterialsLocators.BOMMig_CreateUnitLovBtn, Unit);
		logs.logging("INFO", "Unit Selected in BOM Migration Creation: " + Unit);
		switchToCreateBOMMigrationFrame();
		flag = webDB.waitForElement(BillOfMaterialsLocators.BOMMig_CreateRefInput, ElementType.Xpath);
		if (flag) {
			flag = webDB.sendTextToAnElement(BillOfMaterialsLocators.BOMMig_CreateRefInput, Ref, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Ref Entered in BOM Migration Creation: " + Ref);
				webDB.moveToElement(BillOfMaterialsLocators.BOMMig_CreateTickIcon, ElementType.Xpath);
				webDB.clickAnElement(BillOfMaterialsLocators.BOMMig_CreateTickIcon, ElementType.Xpath);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(BillOfMaterialsLocators.BOMMig_DisplayDocNumber, ElementType.Xpath);
				if (flag) {
					String DisplayDocNo = webDB.getDomPropertyFromElement(
							BillOfMaterialsLocators.BOMMig_DisplayDocNumber, ElementType.Xpath,
							commonFunc.getDataFromTestDataSheet("Value"));
					VariableHandler.setBOMNumber(DisplayDocNo);
					logs.logging("INFO", "Displayed BOM Migration Doc Number: " + DisplayDocNo);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to upload template in BOM migration
	 * 
	 * @author Sakthi-GWL
	 */
	public void uploadTemplateInEmployeeAllowanceMigration() {
		flag = webDB.waitForElement(BillOfMaterialsLocators.BOMMig_UploadIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(BillOfMaterialsLocators.BOMMig_UploadIcon, ElementType.Xpath);
			webDB.clickAnElement(BillOfMaterialsLocators.BOMMig_UploadIcon, ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.BOMMig_UploadInput, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				logs.logging("INFO", "Upload Icon Clicked and Input Box Displayed");
				String file1 = webDB.getDataFromProperties("BOMMigrationFileName");
				File file = new File(VariableHandler.testdataDir + file1);
				flag = webDB.sendTextToAnElement(BillOfMaterialsLocators.BOMMig_UploadInput, file.getAbsolutePath(),
						ElementType.Xpath);
				if (flag) {
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForClickElement(BillOfMaterialsLocators.BOMMig_UploadBtn, ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.clickAnElement(BillOfMaterialsLocators.BOMMig_UploadBtn, ElementType.Xpath);
						commonFunc.hardWait(2000);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.isElementDisplayed(BillOfMaterialsLocators.BOMMig_DisplayDocNumber,
								ElementType.Xpath)
								&& webDB.isElementNotDisplayed(BillOfMaterialsLocators.BOMMig_UploadBtn,
										ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "File Uploaded Succesfully for BOM Migration");
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click Load Routing Button in BOM migration
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickLoadRoutingBtnInBOMMigration() {
		flag = webDB.waitForElement(BillOfMaterialsLocators.BOMMig_LoadRoutingBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(BillOfMaterialsLocators.BOMMig_LoadRoutingBtn, ElementType.Xpath);
			webDB.clickAnElement(BillOfMaterialsLocators.BOMMig_LoadRoutingBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Load Routing Button Clicked Successfully");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click Create BOM Button in BOM migration
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickCreateBOMInBOMMigration() {
		flag = webDB.waitForElement(BillOfMaterialsLocators.BOMMig_CreateBOMBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(BillOfMaterialsLocators.BOMMig_CreateBOMBtn, ElementType.Xpath);
			webDB.clickAnElement(BillOfMaterialsLocators.BOMMig_CreateBOMBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Create BOM Clicked and BOM Created Successfully");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to Navigate to Other Details Sub Tab in BOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToOtherDetailsSubTab() {
		flag = webDB.waitForElement(BillOfMaterialsLocators.OtherDetailsTab_Label, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.OtherDetailsTab_Label, ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.CopyBOMBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Other Details Sub tab Navigated Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to verify displaying Status in BOM
	 * 
	 * @author Sakthi-GWL
	 * @param Status
	 */
	public void checkDisplayStatusInsideBOM(String Status) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(BillOfMaterialsLocators.BOMDisplayStatus, ElementType.Xpath);
		if (flag) {
			String DisplayStatus = webDB.getTextFromElement(BillOfMaterialsLocators.BOMDisplayStatus,
					ElementType.Xpath);
			logs.logging("INFO", "Displayed Status in BOM: " + DisplayStatus);
			logs.logging("INFO", "Expected Status : " + Status);
			flag = DisplayStatus.equalsIgnoreCase(Status);
			if (flag) {
				logs.logging("INFO", "BOM Status Verified");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to switch Correction Frame in BOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchToCorrectionFrameInBOM() {
		flag = webDB.waitForElement(BillOfMaterialsLocators.CorrectionIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(BillOfMaterialsLocators.CorrectionIframe, ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.CorrectionRefInput, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This method used to switch Inactivate Frame in BOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchToInactivateFrameInBOM() {
		flag = webDB.waitForElement(BillOfMaterialsLocators.InactivateIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(BillOfMaterialsLocators.InactivateIframe, ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.InactivateRefInput, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This method used to switch Cre. revision Frame in BOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchToRevisionFrameInBOM() {
		flag = webDB.waitForElement(BillOfMaterialsLocators.CreRevisionIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(BillOfMaterialsLocators.CreRevisionIframe, ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.CreRevisionRefInput, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This method used to switch Copy BOM Frame in BOM
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchToCopyBOMFrameInBOM() {
		flag = webDB.waitForElement(BillOfMaterialsLocators.CopyBOMIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(BillOfMaterialsLocators.CopyBOMIframe, ElementType.Xpath);
			flag = webDB.waitForElement(BillOfMaterialsLocators.CopyBOMItemLovBtn, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click and verify Correction BOM Option
	 * 
	 * @author Sakthi-GWL
	 * @param Ref
	 */
	public void clickAndVerifyCorrectBOMOption(String Ref) {
		flag = webDB.waitForElement(BillOfMaterialsLocators.CorrectionBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.CorrectionBtn, ElementType.Xpath);
			switchToCorrectionFrameInBOM();
			logs.logging("INFO", "Correction Button Clicked");
			flag = webDB.sendTextToAnElement(BillOfMaterialsLocators.CorrectionRefInput, Ref, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Reference Entered in Correction: " + Ref);
				flag = webDB.waitForElement(BillOfMaterialsLocators.CorrectionTickIcon, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BillOfMaterialsLocators.CorrectionTickIcon, ElementType.Xpath);
					webDB.switchToDefaultContent();
					flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Correction Processed Successfully");
						if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
							webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click and verify Inactive BOM Option
	 * 
	 * @author Sakthi-GWL
	 * @param Ref
	 */
	public void clickAndVerifyInactivateBOMOption(String Ref) {
		flag = webDB.waitForElement(BillOfMaterialsLocators.InactivateBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.InactivateBtn, ElementType.Xpath);
			switchToInactivateFrameInBOM();
			logs.logging("INFO", "Inactivate Button Clicked");
			flag = webDB.sendTextToAnElement(BillOfMaterialsLocators.InactivateRefInput, Ref, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Reference Entered in Inactivate: " + Ref);
				flag = webDB.waitForElement(BillOfMaterialsLocators.InactivateTickIcon, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BillOfMaterialsLocators.InactivateTickIcon, ElementType.Xpath);
					webDB.switchToDefaultContent();
					flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Inactivate Processed Successfully");
						if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
							webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
						}
						commonFunc.checkRefreshCellsDisappear();
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click and verify Revision BOM Option
	 * 
	 * @author Sakthi-GWL
	 * @param Ref
	 * @param effFrom
	 * @param effTo
	 */
	public void clickAndVerifyRevisionBOMOption(String Ref, String effFrom, String effTo) {
		flag = webDB.waitForElement(BillOfMaterialsLocators.CreRevisionBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.CreRevisionBtn, ElementType.Xpath);
			switchToRevisionFrameInBOM();
			logs.logging("INFO", "Cre. Revision Button Clicked");
			flag = webDB.sendTextToAnElement(BillOfMaterialsLocators.CreRevisionRefInput, Ref, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Reference Entered in Cre. Revision: " + Ref);
				commonFunc.verifyAddTableSpecs(BillOfMaterialsLocators.CreRevisionEffFromInput,
						BillOfMaterialsLocators.CreRevisionEffFromInput, effFrom);
				logs.logging("INFO", "Effective From Date Entered: " + effFrom);
				commonFunc.verifyAddTableSpecs(BillOfMaterialsLocators.CreRevisionEffToInput,
						BillOfMaterialsLocators.CreRevisionEffToInput, effTo);
				logs.logging("INFO", "Effective To Date Entered: " + effTo);
				flag = webDB.waitForElement(BillOfMaterialsLocators.CreRevisionTickIcon, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BillOfMaterialsLocators.CreRevisionTickIcon, ElementType.Xpath);
					webDB.switchToDefaultContent();
					flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Cre. Revision Processed Successfully");
						if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
							webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
						}
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
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click and verify Copy BOM Option
	 * 
	 * @author Sakthi-GWL
	 * @param Item
	 * @param effFrom
	 * @param effTo
	 * @param BOMName
	 */
	public void clickAndVerifyCopyBOMOption(String Item, String effFrom, String effTo, String BOMName) {
		flag = webDB.waitForElement(BillOfMaterialsLocators.CopyBOMBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(BillOfMaterialsLocators.CopyBOMBtn, ElementType.Xpath);
			switchToCopyBOMFrameInBOM();
			logs.logging("INFO", "Copy BOM Button Clicked");
			commonFunc.verifyAddTableSpecs(BillOfMaterialsLocators.CopyBOMEffFromInput,
					BillOfMaterialsLocators.CopyBOMEffFromInput, effFrom);
			logs.logging("INFO", "Effective From Date Entered: " + effFrom);
			commonFunc.verifyAddTableSpecs(BillOfMaterialsLocators.CopyBOMEffToInput,
					BillOfMaterialsLocators.CopyBOMEffToInput, effTo);
			logs.logging("INFO", "Effective To Date Entered: " + effTo);
			commonFunc.searchAndSelectValueInFilters(BillOfMaterialsLocators.CopyBOMItemLovBtn, Item);
			logs.logging("INFO", "Item Selected in Copy BOM: " + Item);
			switchToCopyBOMFrameInBOM();
			webDB.clearText(BillOfMaterialsLocators.CopyBOMNameInput, ElementType.Xpath);
			flag = webDB.sendTextToAnElement(BillOfMaterialsLocators.CopyBOMNameInput, BOMName, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "BOM Name Entered in Copy BOM: " + BOMName);
				flag = webDB.waitForElement(BillOfMaterialsLocators.CopyBOMTickIcon, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(BillOfMaterialsLocators.CopyBOMTickIcon, ElementType.Xpath);
					webDB.switchToDefaultContent();
					flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Copy BOM Processed Successfully");
						if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
							webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
						}
						flag = webDB.waitForClickElement(BillOfMaterialsLocators.DisplayBOMNumber, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Tick Icon Clicked and BOM Created");
							commonFunc.hardWait(2000);
							String BOMNo = webDB.getTextFromElement(BillOfMaterialsLocators.DisplayBOMNumber,
									ElementType.Xpath);
							VariableHandler.setBOMNumber(BOMNo);
							logs.logging("INFO", "Displayed BOM Number after copied: " + BOMNo);
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Validation of Routing Details in BOM with Data Base
	 * 
	 * @author Sakthi-GWL
	 * @param i
	 * @param Process
	 * @param ProcessType
	 * @param WorkCenter
	 * @param ConsWarehouse
	 * @param ReceiptWarehouse
	 */
	public void validateRoutingDetailsWithDB(String i, String Process, String ProcessType, String WorkCenter,
			String ConsWarehouse, String ReceiptWarehouse) {
		commonFunc.verifyAssertionBothValues(
				dbFunction.validateBOMRoutingDetails(i, commonFunc.getDataFromTestDataSheet("BOMRoutingProcess")),
				Process);
		logs.logging("INFO", "Verified Process and DB Value Matched: " + Process);
		ProcessType = String.valueOf(ProcessType.charAt(0)).toUpperCase();
		commonFunc.verifyAssertionBothValues(
				dbFunction.validateBOMRoutingDetails(i, commonFunc.getDataFromTestDataSheet("BOMRoutingSourceType")),
				ProcessType);
		logs.logging("INFO", "Verified ProcessType and DB Value Matched: " + ProcessType);
//		commonFunc.verifyAssertionBothValues(
//				dbFunction.validateBOMRoutingDetails(i, commonFunc.getDataFromTestDataSheet("BOMRoutingOprnId")),
//				WorkCenter);
//		logs.logging("INFO", "Verified WorkCenter and DB Value Matched: " + WorkCenter);
		commonFunc.verifyAssertionBothValues(
				dbFunction.validateBOMRoutingDetails(i, commonFunc.getDataFromTestDataSheet("BOMRoutingConsWH")),
				ConsWarehouse);
		logs.logging("INFO", "Verified ConsWarehouse and DB Value Matched: " + ConsWarehouse);
		commonFunc.verifyAssertionBothValues(
				dbFunction.validateBOMRoutingDetails(i, commonFunc.getDataFromTestDataSheet("BOMRoutingRcptWH")),
				ReceiptWarehouse);
		logs.logging("INFO", "Verified ReceiptWarehouse and DB Value Matched: " + ReceiptWarehouse);
	}

	/**
	 * Validation of Header Details in BOM with Data Base
	 * 
	 * @author Sakthi-GWL
	 * @param Item
	 * @param BOMName
	 * @param EffFrom
	 * @param EffTo
	 */
	public void validateHeaderDetailsWithDB(String Item, String BOMName, String EffFrom, String EffTo) {
		commonFunc.verifyAssertionBothValues(
				dbFunction.validateBOMHeaderDetails(commonFunc.getDataFromTestDataSheet("BOMHeaderItem")), Item);
		logs.logging("INFO", "Verified Item and DB Value Matched: " + Item);
		commonFunc.verifyAssertionBothValues(
				dbFunction.validateBOMHeaderDetails(commonFunc.getDataFromTestDataSheet("BOMHeaderName")), BOMName);
		logs.logging("INFO", "Verified BOMName and DB Value Matched: " + BOMName);
//		commonFunc.verifyAssertionBothValues(
//				dbFunction.validateBOMHeaderDetails(commonFunc.getDataFromTestDataSheet("BOMHeaderEffFrom")), EffFrom);
//		logs.logging("INFO", "Verified Effective From Date and DB Value Matched: " + EffFrom);
//		commonFunc.verifyAssertionBothValues(
//				dbFunction.validateBOMHeaderDetails(commonFunc.getDataFromTestDataSheet("BOMHeaderEffTo")), EffTo);
//		logs.logging("INFO", "Verified Effective To Date and DB Value Matched: " + EffTo);
	}

	/**
	 * Validation of Lines Raw Material Details in BOM with Data Base
	 * 
	 * @author Sakthi-GWL
	 * @param OprnNo
	 * @param i
	 * @param Process
	 * @param RawMaterial
	 * @param SourceWH
	 * @param UOM
	 */
	public void validateLineDetailsWithDB(String OprnNo, String i, String Process, String RawMaterial, String SourceWH,
			String UOM) {
		commonFunc.verifyAssertionBothValues(
				dbFunction.validateBOMLinesDetails(OprnNo, i, commonFunc.getDataFromTestDataSheet("BOMMatProcess")),
				Process);
		logs.logging("INFO", "Verified Raw Material Process and DB Value Matched: " + Process);
		commonFunc.verifyAssertionBothValues(
				dbFunction.validateBOMLinesDetails(OprnNo, i, commonFunc.getDataFromTestDataSheet("BOMMatRMName")),
				RawMaterial);
		logs.logging("INFO", "Verified Raw Material and DB Value Matched: " + RawMaterial);
		commonFunc.verifyAssertionBothValues(
				dbFunction.validateBOMLinesDetails(OprnNo, i, commonFunc.getDataFromTestDataSheet("BOMMatSourceWH")),
				SourceWH);
		logs.logging("INFO", "Verified Raw Material SourceWH and DB Value Matched: " + SourceWH);
		commonFunc.verifyAssertionBothValues(
				dbFunction.validateBOMLinesDetails(OprnNo, i, commonFunc.getDataFromTestDataSheet("BOMMaterialUOM")),
				UOM);
		logs.logging("INFO", "Verified Raw Material UOM and DB Value Matched: " + UOM);
	}

	/**
	 * This method used to How to do BOM Migration?
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyBOMMigration() {
		try {
			commonFunc.navigateToBOMMigrationTab();
			clickAddIconInBOMMigration();
			fillDetailsInBOMMigrationCreation(commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("RefText_Test"));
			uploadTemplateInEmployeeAllowanceMigration();
			clickLoadRoutingBtnInBOMMigration();
			clickCreateBOMInBOMMigration();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyBOMMigration': " + e);
			return false;
		}
	}

	/**
	 * This method used to How to create Bill of materials for the Item with single
	 * process and assembly
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCreateBOMItemSingleProcessAssembly() {
		String BOMName = commonFunc.getDataFromTestDataSheet("BomNamePreposition")
				+ RandomStringUtils.randomAlphanumeric(4);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String Today = date.format(formatter);
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		try {
			commonFunc.navigateItemPage();
			priceListFunc.searchItemAndCopyWithName(commonFunc.getDataFromTestDataSheet("GwlBomItem1001"),
					"GWL - BOM ITEM - ");
			commonFunc.navigateToBillOfMaterialsPage();
			clickAddIconAndFillUnitBOMNameAndItem(commonFunc.getDataFromTestDataSheet("Unit_100"), BOMName,
					VariableHandler.getItemName());
			enterEffFromToDateAndSaveHeader(Today, Today);
			validateHeaderDetailsWithDB(VariableHandler.getItemName(), BOMName, Today, Today);
			navigateToRoutingSubTab();
			clickAddLineIconInRoutingTab();
			fillProcSeqNoOprnNoAndProcessIdInRouting("1", "1", commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			fillTypeAndWorkCenterInRouting(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"));
			clickSaveLineIconInRoutingTab();
			clickDetailsForAddedProcessInBOM(commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			chooseConsumptionAndReceiptWHInViewDetails(commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			validateRoutingDetailsWithDB("1", commonFunc.getDataFromTestDataSheet("GwlProcess1"),
					commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			navigateToMaterialsSubTab();
			clickAddLineIconInMaterialTab();
			fillProcessItemUOMInMaterial(commonFunc.getDataFromTestDataSheet("GwlProcess1Desc"), RMItem,
					commonFunc.getDataFromTestDataSheet("Kgs"));
			fillReqQntyConvFactorAndSourceWHInMaterial("3", "1", commonFunc.getDataFromTestDataSheet("RMU100_WH"));
			clickSaveLineIconInMaterialTab();
			validateLineDetailsWithDB("1", "1", commonFunc.getDataFromTestDataSheet("GwlProcess1"), RMItem,
					commonFunc.getDataFromTestDataSheet("RMU100_WH"), commonFunc.getDataFromTestDataSheet("Kgs"));
			navigateToBORSubTab();
			clickAddLineIconInBORTab();
			fillLineSnoAndProcessIdInBORTab("1", commonFunc.getDataFromTestDataSheet("GwlProcess1Desc"));
			fillResourceTypeAndResourceInBORTab(commonFunc.getDataFromTestDataSheet("ResourceText"),
					commonFunc.getDataFromTestDataSheet("AutomationResource"));
			fillSetupHrsMinAndSetupLotQntyInBORTab("0", "15", "0");
			fillCycleTimeUnitOrHrsMinAndSecInBORTab("3", "0", "0", "0");
			fillCycleTimeBatchQntyAndHrsInBORTab("0", "0");
			fillLagPriorityAndNoOfUnitsInBORTab("0", "1", "3");
			clickSaveLineIconInBORTab();
			clickHeaderSaveAndPostButtonInBOM();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getBOMNumber(),
						webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToBillOfMaterialsPage();
			searchBOMNoAndVerifyStatus(VariableHandler.getBOMNumber(),
					commonFunc.getDataFromTestDataSheet("ActiveStatus"));
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyCreateBOMItemSingleProcessAssembly': " + e);
			return false;
		}
	}

	/**
	 * This method used to How to create Bill of materials for the Item with
	 * multiple Sub assembly?
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCreateBOMItemMultipleSubAssembly() {
		String BOMName = commonFunc.getDataFromTestDataSheet("BomNamePreposition")
				+ RandomStringUtils.randomAlphanumeric(4);
		String BOMName2 = commonFunc.getDataFromTestDataSheet("BomNamePreposition")
				+ RandomStringUtils.randomAlphanumeric(4);
		String BOMName3 = commonFunc.getDataFromTestDataSheet("BomNamePreposition")
				+ RandomStringUtils.randomAlphanumeric(4);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String Today = date.format(formatter);
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		try {
			commonFunc.navigateItemPage();
			priceListFunc.searchItemAndCopyWithName(commonFunc.getDataFromTestDataSheet("GwlBomItem1001"),
					"GWL - BOM ITEM - ");
			commonFunc.navigateToBillOfMaterialsPage();
			clickAddIconAndFillUnitBOMNameAndItem(commonFunc.getDataFromTestDataSheet("Unit_100"), BOMName,
					VariableHandler.getItemName());
			enterEffFromToDateAndSaveHeader(Today, Today);
			validateHeaderDetailsWithDB(VariableHandler.getItemName(), BOMName, Today, Today);
			navigateToRoutingSubTab();
			clickAddLineIconInRoutingTab();
			fillProcSeqNoOprnNoAndProcessIdInRouting("1", "1", commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			fillTypeAndWorkCenterInRouting(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"));
			clickSaveLineIconInRoutingTab();
			clickDetailsForAddedProcessInBOM(commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			chooseConsumptionAndReceiptWHInViewDetails(commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			validateRoutingDetailsWithDB("1", commonFunc.getDataFromTestDataSheet("GwlProcess1"),
					commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			navigateToMaterialsSubTab();
			clickAddLineIconInMaterialTab();
			fillProcessItemUOMInMaterial(commonFunc.getDataFromTestDataSheet("GwlProcess1Desc"), RMItem,
					commonFunc.getDataFromTestDataSheet("Kgs"));
			fillReqQntyConvFactorAndSourceWHInMaterial("3", "1", commonFunc.getDataFromTestDataSheet("RMU100_WH"));
			clickSaveLineIconInMaterialTab();
			validateLineDetailsWithDB("1", "1", commonFunc.getDataFromTestDataSheet("GwlProcess1"), RMItem,
					commonFunc.getDataFromTestDataSheet("RMU100_WH"), commonFunc.getDataFromTestDataSheet("Kgs"));
			clickHeaderSaveAndPostButtonInBOM();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getBOMNumber(),
						webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			navigateToBOMBySearchAndClickBOMNo(VariableHandler.getBOMNumber());
			chooseBOMTypeAndSaveHeader(commonFunc.getDataFromTestDataSheet("PrimaryType"));
			String BOMItem1 = VariableHandler.getItemName();
			commonFunc.navigateItemPage();
			priceListFunc.searchItemAndCopyWithName(commonFunc.getDataFromTestDataSheet("GwlBomItem1001"),
					"GWL - BOM ITEM - ");
			commonFunc.navigateToBillOfMaterialsPage();
			clickAddIconAndFillUnitBOMNameAndItem(commonFunc.getDataFromTestDataSheet("Unit_100"), BOMName2,
					VariableHandler.getItemName());
			enterEffFromToDateAndSaveHeader(Today, Today);
			validateHeaderDetailsWithDB(VariableHandler.getItemName(), BOMName2, Today, Today);
			navigateToRoutingSubTab();
			clickAddLineIconInRoutingTab();
			fillProcSeqNoOprnNoAndProcessIdInRouting("1", "1", commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			fillTypeAndWorkCenterInRouting(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"));
			clickSaveLineIconInRoutingTab();
			clickDetailsForAddedProcessInBOM(commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			chooseConsumptionAndReceiptWHInViewDetails(commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			validateRoutingDetailsWithDB("1", commonFunc.getDataFromTestDataSheet("GwlProcess1"),
					commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			navigateToMaterialsSubTab();
			clickAddLineIconInMaterialTab();
			fillProcessItemUOMInMaterial(commonFunc.getDataFromTestDataSheet("GwlProcess1Desc"), RMItem,
					commonFunc.getDataFromTestDataSheet("Kgs"));
			fillReqQntyConvFactorAndSourceWHInMaterial("3", "1", commonFunc.getDataFromTestDataSheet("RMU100_WH"));
			clickSaveLineIconInMaterialTab();
			validateLineDetailsWithDB("1", "1", commonFunc.getDataFromTestDataSheet("GwlProcess1"), RMItem,
					commonFunc.getDataFromTestDataSheet("RMU100_WH"), commonFunc.getDataFromTestDataSheet("Kgs"));
			clickHeaderSaveAndPostButtonInBOM();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getBOMNumber(),
						webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			navigateToBOMBySearchAndClickBOMNo(VariableHandler.getBOMNumber());
			chooseBOMTypeAndSaveHeader(commonFunc.getDataFromTestDataSheet("PrimaryType"));
			String BOMItem2 = VariableHandler.getItemName();
			commonFunc.navigateItemPage();
			priceListFunc.searchItemAndCopyWithName(commonFunc.getDataFromTestDataSheet("GwlBomItem1001"),
					"GWL - BOM ITEM - ");
			commonFunc.navigateToBillOfMaterialsPage();
			clickAddIconAndFillUnitBOMNameAndItem(commonFunc.getDataFromTestDataSheet("Unit_100"), BOMName3,
					VariableHandler.getItemName());
			enterEffFromToDateAndSaveHeader(Today, Today);
			validateHeaderDetailsWithDB(VariableHandler.getItemName(), BOMName3, Today, Today);
			navigateToRoutingSubTab();
			clickAddLineIconInRoutingTab();
			fillProcSeqNoOprnNoAndProcessIdInRouting("1", "1", commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			fillTypeAndWorkCenterInRouting(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"));
			clickSaveLineIconInRoutingTab();
			clickDetailsForAddedProcessInBOM(commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			chooseConsumptionAndReceiptWHInViewDetails(commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			validateRoutingDetailsWithDB("1", commonFunc.getDataFromTestDataSheet("GwlProcess1"),
					commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			navigateToMaterialsSubTab();
			clickAddLineIconInMaterialTab();
			fillProcessItemUOMInMaterial(commonFunc.getDataFromTestDataSheet("GwlProcess1Desc"), BOMItem1,
					commonFunc.getDataFromTestDataSheet("UomNos"));
			fillReqQntyConvFactorAndSourceWHInMaterial("3", "1", commonFunc.getDataFromTestDataSheet("FinishedGoodWH"));
			clickSaveLineIconInMaterialTab();
			validateLineDetailsWithDB("1", "1", commonFunc.getDataFromTestDataSheet("GwlProcess1"), BOMItem1,
					commonFunc.getDataFromTestDataSheet("FinishedGoodWH"),
					commonFunc.getDataFromTestDataSheet("UomNos"));
			clickAddLineIconInMaterialTab();
			fillProcessItemUOMInMaterial(commonFunc.getDataFromTestDataSheet("GwlProcess1Desc"), BOMItem2,
					commonFunc.getDataFromTestDataSheet("UomNos"));
			fillReqQntyConvFactorAndSourceWHInMaterial("3", "1", commonFunc.getDataFromTestDataSheet("FinishedGoodWH"));
			clickSaveLineIconInMaterialTab();
			validateLineDetailsWithDB("1", "2", commonFunc.getDataFromTestDataSheet("GwlProcess1"), BOMItem2,
					commonFunc.getDataFromTestDataSheet("FinishedGoodWH"),
					commonFunc.getDataFromTestDataSheet("UomNos"));
			clickHeaderSaveAndPostButtonInBOM();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getBOMNumber(),
						webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToBillOfMaterialsPage();
			searchBOMNoAndVerifyStatus(VariableHandler.getBOMNumber(),
					commonFunc.getDataFromTestDataSheet("ActiveStatus"));
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyCreateBOMItemMultipleSubAssembly': " + e);
			return false;
		}
	}

	/**
	 * This method used to How to Create Multiple BOM for the Same Item?
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCreateMultipleBOMSameItem() {
		String BOMName = commonFunc.getDataFromTestDataSheet("BomNamePreposition")
				+ RandomStringUtils.randomAlphanumeric(4);
		String BOMName2 = commonFunc.getDataFromTestDataSheet("BomNamePreposition")
				+ RandomStringUtils.randomAlphanumeric(4);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String Today = date.format(formatter);
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		try {
			commonFunc.navigateItemPage();
			priceListFunc.searchItemAndCopyWithName(commonFunc.getDataFromTestDataSheet("GwlBomItem1001"),
					"GWL - BOM ITEM - ");
			commonFunc.navigateToBillOfMaterialsPage();
			clickAddIconAndFillUnitBOMNameAndItem(commonFunc.getDataFromTestDataSheet("Unit_100"), BOMName,
					VariableHandler.getItemName());
			enterEffFromToDateAndSaveHeader(Today, Today);
			validateHeaderDetailsWithDB(VariableHandler.getItemName(), BOMName, Today, Today);
			navigateToRoutingSubTab();
			clickAddLineIconInRoutingTab();
			fillProcSeqNoOprnNoAndProcessIdInRouting("1", "1", commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			fillTypeAndWorkCenterInRouting(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"));
			clickSaveLineIconInRoutingTab();
			clickDetailsForAddedProcessInBOM(commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			chooseConsumptionAndReceiptWHInViewDetails(commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			validateRoutingDetailsWithDB("1", commonFunc.getDataFromTestDataSheet("GwlProcess1"),
					commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			navigateToMaterialsSubTab();
			clickAddLineIconInMaterialTab();
			fillProcessItemUOMInMaterial(commonFunc.getDataFromTestDataSheet("GwlProcess1Desc"), RMItem,
					commonFunc.getDataFromTestDataSheet("Kgs"));
			fillReqQntyConvFactorAndSourceWHInMaterial("3", "1", commonFunc.getDataFromTestDataSheet("RMU100_WH"));
			clickSaveLineIconInMaterialTab();
			validateLineDetailsWithDB("1", "1", commonFunc.getDataFromTestDataSheet("GwlProcess1"), RMItem,
					commonFunc.getDataFromTestDataSheet("RMU100_WH"), commonFunc.getDataFromTestDataSheet("Kgs"));
			navigateToBORSubTab();
			clickAddLineIconInBORTab();
			fillLineSnoAndProcessIdInBORTab("1", commonFunc.getDataFromTestDataSheet("GwlProcess1Desc"));
			fillResourceTypeAndResourceInBORTab(commonFunc.getDataFromTestDataSheet("ResourceText"),
					commonFunc.getDataFromTestDataSheet("AutomationResource"));
			fillSetupHrsMinAndSetupLotQntyInBORTab("0", "15", "0");
			fillCycleTimeUnitOrHrsMinAndSecInBORTab("3", "0", "0", "0");
			fillCycleTimeBatchQntyAndHrsInBORTab("0", "0");
			fillLagPriorityAndNoOfUnitsInBORTab("0", "1", "3");
			clickSaveLineIconInBORTab();
			clickHeaderSaveAndPostButtonInBOM();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getBOMNumber(),
						webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToBillOfMaterialsPage();
			clickAddIconAndFillUnitBOMNameAndItem(commonFunc.getDataFromTestDataSheet("Unit_100"), BOMName2,
					VariableHandler.getItemName());
			enterEffFromToDateAndSaveHeader(Today, Today);
			validateHeaderDetailsWithDB(VariableHandler.getItemName(), BOMName2, Today, Today);
			navigateToRoutingSubTab();
			clickAddLineIconInRoutingTab();
			fillProcSeqNoOprnNoAndProcessIdInRouting("1", "1", commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			fillTypeAndWorkCenterInRouting(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"));
			clickSaveLineIconInRoutingTab();
			clickDetailsForAddedProcessInBOM(commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			chooseConsumptionAndReceiptWHInViewDetails(commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			validateRoutingDetailsWithDB("1", commonFunc.getDataFromTestDataSheet("GwlProcess1"),
					commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			navigateToMaterialsSubTab();
			clickAddLineIconInMaterialTab();
			fillProcessItemUOMInMaterial(commonFunc.getDataFromTestDataSheet("GwlProcess1Desc"), RMItem,
					commonFunc.getDataFromTestDataSheet("Kgs"));
			fillReqQntyConvFactorAndSourceWHInMaterial("3", "1", commonFunc.getDataFromTestDataSheet("RMU100_WH"));
			clickSaveLineIconInMaterialTab();
			validateLineDetailsWithDB("1", "1", commonFunc.getDataFromTestDataSheet("GwlProcess1"), RMItem,
					commonFunc.getDataFromTestDataSheet("RMU100_WH"), commonFunc.getDataFromTestDataSheet("Kgs"));
			navigateToBORSubTab();
			clickAddLineIconInBORTab();
			fillLineSnoAndProcessIdInBORTab("1", commonFunc.getDataFromTestDataSheet("GwlProcess1Desc"));
			fillResourceTypeAndResourceInBORTab(commonFunc.getDataFromTestDataSheet("ResourceText"),
					commonFunc.getDataFromTestDataSheet("AutomationResource"));
			fillSetupHrsMinAndSetupLotQntyInBORTab("0", "15", "0");
			fillCycleTimeUnitOrHrsMinAndSecInBORTab("3", "0", "0", "0");
			fillCycleTimeBatchQntyAndHrsInBORTab("0", "0");
			fillLagPriorityAndNoOfUnitsInBORTab("0", "1", "3");
			clickSaveLineIconInBORTab();
			clickHeaderSaveAndPostButtonInBOM();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getBOMNumber(),
						webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToBillOfMaterialsPage();
			searchBOMNoAndVerifyStatus(VariableHandler.getBOMNumber(),
					commonFunc.getDataFromTestDataSheet("ActiveStatus"));
			navigateToBOMBySearchAndClickBOMNo(VariableHandler.getBOMNumber());
			chooseBOMTypeAndSaveHeader(commonFunc.getDataFromTestDataSheet("PrimaryType"));
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyCreateMultipleBOMSameItem': " + e);
			return false;
		}
	}

	/**
	 * This method used to How to Correct the Bill of Materials?
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCorrectBillOfMaterials() {
		String BOMName = commonFunc.getDataFromTestDataSheet("BomNamePreposition")
				+ RandomStringUtils.randomAlphanumeric(4);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String Today = date.format(formatter);
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		try {
			commonFunc.navigateItemPage();
			priceListFunc.searchItemAndCopyWithName(commonFunc.getDataFromTestDataSheet("GwlBomItem1001"),
					"GWL - BOM ITEM - ");
			commonFunc.navigateToBillOfMaterialsPage();
			clickAddIconAndFillUnitBOMNameAndItem(commonFunc.getDataFromTestDataSheet("Unit_100"), BOMName,
					VariableHandler.getItemName());
			enterEffFromToDateAndSaveHeader(Today, Today);
			validateHeaderDetailsWithDB(VariableHandler.getItemName(), BOMName, Today, Today);
			navigateToRoutingSubTab();
			clickAddLineIconInRoutingTab();
			fillProcSeqNoOprnNoAndProcessIdInRouting("1", "1", commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			fillTypeAndWorkCenterInRouting(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"));
			clickSaveLineIconInRoutingTab();
			clickDetailsForAddedProcessInBOM(commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			chooseConsumptionAndReceiptWHInViewDetails(commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			validateRoutingDetailsWithDB("1", commonFunc.getDataFromTestDataSheet("GwlProcess1"),
					commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			navigateToMaterialsSubTab();
			clickAddLineIconInMaterialTab();
			fillProcessItemUOMInMaterial(commonFunc.getDataFromTestDataSheet("GwlProcess1Desc"), RMItem,
					commonFunc.getDataFromTestDataSheet("Kgs"));
			fillReqQntyConvFactorAndSourceWHInMaterial("3", "1", commonFunc.getDataFromTestDataSheet("RMU100_WH"));
			clickSaveLineIconInMaterialTab();
			validateLineDetailsWithDB("1", "1", commonFunc.getDataFromTestDataSheet("GwlProcess1"), RMItem,
					commonFunc.getDataFromTestDataSheet("RMU100_WH"), commonFunc.getDataFromTestDataSheet("Kgs"));
			clickHeaderSaveAndPostButtonInBOM();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getBOMNumber(),
						webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToBillOfMaterialsPage();
			searchBOMNoAndVerifyStatus(VariableHandler.getBOMNumber(),
					commonFunc.getDataFromTestDataSheet("ActiveStatus"));
			navigateToBOMBySearchAndClickBOMNo(VariableHandler.getBOMNumber());
			navigateToOtherDetailsSubTab();
			clickAndVerifyCorrectBOMOption(commonFunc.getDataFromTestDataSheet("RefText_Test"));
			checkDisplayStatusInsideBOM(commonFunc.getDataFromTestDataSheet("Draft"));
			clickHeaderSaveAndPostButtonInBOM();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getBOMNumber(),
						webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToBillOfMaterialsPage();
			searchBOMNoAndVerifyStatus(VariableHandler.getBOMNumber(),
					commonFunc.getDataFromTestDataSheet("ActiveStatus"));
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyCorrectBillOfMaterials': " + e);
			return false;
		}
	}

	/**
	 * This method used to How to Copy the BOM?
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCopyBillOfMaterials() {
		String BOMName = commonFunc.getDataFromTestDataSheet("BomNamePreposition")
				+ RandomStringUtils.randomAlphanumeric(4);
		String BOMName2 = commonFunc.getDataFromTestDataSheet("BomNamePreposition")
				+ RandomStringUtils.randomAlphanumeric(4);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String Today = date.format(formatter);
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		try {
			commonFunc.navigateItemPage();
			priceListFunc.searchItemAndCopyWithName(commonFunc.getDataFromTestDataSheet("GwlBomItem1001"),
					"GWL - BOM ITEM - ");
			commonFunc.navigateToBillOfMaterialsPage();
			clickAddIconAndFillUnitBOMNameAndItem(commonFunc.getDataFromTestDataSheet("Unit_100"), BOMName,
					VariableHandler.getItemName());
			enterEffFromToDateAndSaveHeader(Today, Today);
			validateHeaderDetailsWithDB(VariableHandler.getItemName(), BOMName, Today, Today);
			navigateToRoutingSubTab();
			clickAddLineIconInRoutingTab();
			fillProcSeqNoOprnNoAndProcessIdInRouting("1", "1", commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			fillTypeAndWorkCenterInRouting(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"));
			clickSaveLineIconInRoutingTab();
			clickDetailsForAddedProcessInBOM(commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			chooseConsumptionAndReceiptWHInViewDetails(commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			validateRoutingDetailsWithDB("1", commonFunc.getDataFromTestDataSheet("GwlProcess1"),
					commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			navigateToMaterialsSubTab();
			clickAddLineIconInMaterialTab();
			fillProcessItemUOMInMaterial(commonFunc.getDataFromTestDataSheet("GwlProcess1Desc"), RMItem,
					commonFunc.getDataFromTestDataSheet("Kgs"));
			fillReqQntyConvFactorAndSourceWHInMaterial("3", "1", commonFunc.getDataFromTestDataSheet("RMU100_WH"));
			clickSaveLineIconInMaterialTab();
			validateLineDetailsWithDB("1", "1", commonFunc.getDataFromTestDataSheet("GwlProcess1"), RMItem,
					commonFunc.getDataFromTestDataSheet("RMU100_WH"), commonFunc.getDataFromTestDataSheet("Kgs"));
			clickHeaderSaveAndPostButtonInBOM();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getBOMNumber(),
						webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToBillOfMaterialsPage();
			searchBOMNoAndVerifyStatus(VariableHandler.getBOMNumber(),
					commonFunc.getDataFromTestDataSheet("ActiveStatus"));
			navigateToBOMBySearchAndClickBOMNo(VariableHandler.getBOMNumber());
			navigateToOtherDetailsSubTab();
			clickAndVerifyCopyBOMOption(commonFunc.getDataFromTestDataSheet("BomItem-2244"), Today, Today, BOMName2);
			validateHeaderDetailsWithDB(commonFunc.getDataFromTestDataSheet("BomItem-2244"), BOMName2, Today, Today);
			checkDisplayStatusInsideBOM(commonFunc.getDataFromTestDataSheet("Draft"));
			validateRoutingDetailsWithDB("1", commonFunc.getDataFromTestDataSheet("GwlProcess1"),
					commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			validateLineDetailsWithDB("1", "1", commonFunc.getDataFromTestDataSheet("GwlProcess1"), RMItem,
					commonFunc.getDataFromTestDataSheet("RMU100_WH"), commonFunc.getDataFromTestDataSheet("Kgs"));
			clickHeaderSaveAndPostButtonInBOM();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getBOMNumber(),
						webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToBillOfMaterialsPage();
			searchBOMNoAndVerifyStatus(VariableHandler.getBOMNumber(),
					commonFunc.getDataFromTestDataSheet("ActiveStatus"));
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyCopyBillOfMaterials': " + e);
			return false;
		}
	}

	/**
	 * This method used to How to revise the BOM?
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyReviseBillOfMaterials() {
		int rev = 0;
		String BOMName = commonFunc.getDataFromTestDataSheet("BomNamePreposition")
				+ RandomStringUtils.randomAlphanumeric(4);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		LocalDate date1 = LocalDate.now().plusDays(1);
		String Today = date.format(formatter);
		String Tomorrow = date1.format(formatter);
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		try {
			commonFunc.navigateItemPage();
			priceListFunc.searchItemAndCopyWithName(commonFunc.getDataFromTestDataSheet("GwlBomItem1001"),
					"GWL - BOM ITEM - ");
			commonFunc.navigateToBillOfMaterialsPage();
			clickAddIconAndFillUnitBOMNameAndItem(commonFunc.getDataFromTestDataSheet("Unit_100"), BOMName,
					VariableHandler.getItemName());
			enterEffFromToDateAndSaveHeader(Today, Today);
			validateHeaderDetailsWithDB(VariableHandler.getItemName(), BOMName, Today, Today);
			navigateToRoutingSubTab();
			clickAddLineIconInRoutingTab();
			fillProcSeqNoOprnNoAndProcessIdInRouting("1", "1", commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			fillTypeAndWorkCenterInRouting(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"));
			clickSaveLineIconInRoutingTab();
			clickDetailsForAddedProcessInBOM(commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			chooseConsumptionAndReceiptWHInViewDetails(commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			validateRoutingDetailsWithDB("1", commonFunc.getDataFromTestDataSheet("GwlProcess1"),
					commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			navigateToMaterialsSubTab();
			clickAddLineIconInMaterialTab();
			fillProcessItemUOMInMaterial(commonFunc.getDataFromTestDataSheet("GwlProcess1Desc"), RMItem,
					commonFunc.getDataFromTestDataSheet("Kgs"));
			fillReqQntyConvFactorAndSourceWHInMaterial("3", "1", commonFunc.getDataFromTestDataSheet("RMU100_WH"));
			clickSaveLineIconInMaterialTab();
			validateLineDetailsWithDB("1", "1", commonFunc.getDataFromTestDataSheet("GwlProcess1"), RMItem,
					commonFunc.getDataFromTestDataSheet("RMU100_WH"), commonFunc.getDataFromTestDataSheet("Kgs"));
			clickHeaderSaveAndPostButtonInBOM();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getBOMNumber(),
						webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToBillOfMaterialsPage();
			searchBOMNoAndVerifyStatus(VariableHandler.getBOMNumber(),
					commonFunc.getDataFromTestDataSheet("ActiveStatus"));
			navigateToBOMBySearchAndClickBOMNo(VariableHandler.getBOMNumber());
			navigateToOtherDetailsSubTab();
			clickAndVerifyRevisionBOMOption(commonFunc.getDataFromTestDataSheet("RefText_Test"), Today, Tomorrow);
			rev++;
			BOMName = BOMName.concat("-").concat(String.valueOf(rev));
			validateHeaderDetailsWithDB(VariableHandler.getItemName(), BOMName, Today, Tomorrow);
			checkDisplayStatusInsideBOM(commonFunc.getDataFromTestDataSheet("Draft"));
			clickHeaderSaveAndPostButtonInBOM();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getBOMNumber(),
						webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToBillOfMaterialsPage();
			searchBOMNoAndVerifyStatus(VariableHandler.getBOMNumber(),
					commonFunc.getDataFromTestDataSheet("ActiveStatus"));
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyReviseBillOfMaterials': " + e);
			return false;
		}
	}

	/**
	 * This method used to How to Inactivate the BOM?
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyInactivateBillOfMaterials() {
		String BOMName = commonFunc.getDataFromTestDataSheet("BomNamePreposition")
				+ RandomStringUtils.randomAlphanumeric(4);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String Today = date.format(formatter);
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		try {
			commonFunc.navigateItemPage();
			priceListFunc.searchItemAndCopyWithName(commonFunc.getDataFromTestDataSheet("GwlBomItem1001"),
					"GWL - BOM ITEM - ");
			commonFunc.navigateToBillOfMaterialsPage();
			clickAddIconAndFillUnitBOMNameAndItem(commonFunc.getDataFromTestDataSheet("Unit_100"), BOMName,
					VariableHandler.getItemName());
			enterEffFromToDateAndSaveHeader(Today, Today);
			validateHeaderDetailsWithDB(VariableHandler.getItemName(), BOMName, Today, Today);
			navigateToRoutingSubTab();
			clickAddLineIconInRoutingTab();
			fillProcSeqNoOprnNoAndProcessIdInRouting("1", "1", commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			fillTypeAndWorkCenterInRouting(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"));
			clickSaveLineIconInRoutingTab();
			clickDetailsForAddedProcessInBOM(commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			chooseConsumptionAndReceiptWHInViewDetails(commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			validateRoutingDetailsWithDB("1", commonFunc.getDataFromTestDataSheet("GwlProcess1"),
					commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			navigateToMaterialsSubTab();
			clickAddLineIconInMaterialTab();
			fillProcessItemUOMInMaterial(commonFunc.getDataFromTestDataSheet("GwlProcess1Desc"), RMItem,
					commonFunc.getDataFromTestDataSheet("Kgs"));
			fillReqQntyConvFactorAndSourceWHInMaterial("3", "1", commonFunc.getDataFromTestDataSheet("RMU100_WH"));
			clickSaveLineIconInMaterialTab();
			validateLineDetailsWithDB("1", "1", commonFunc.getDataFromTestDataSheet("GwlProcess1"), RMItem,
					commonFunc.getDataFromTestDataSheet("RMU100_WH"), commonFunc.getDataFromTestDataSheet("Kgs"));
			clickHeaderSaveAndPostButtonInBOM();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"), VariableHandler.getBOMNumber(),
						webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToBillOfMaterialsPage();
			searchBOMNoAndVerifyStatus(VariableHandler.getBOMNumber(),
					commonFunc.getDataFromTestDataSheet("ActiveStatus"));
			navigateToBOMBySearchAndClickBOMNo(VariableHandler.getBOMNumber());
			navigateToOtherDetailsSubTab();
			clickAndVerifyInactivateBOMOption(commonFunc.getDataFromTestDataSheet("RefText_Test"));
			commonFunc.navigateToBillOfMaterialsPage();
			searchBOMNoAndVerifyStatus(VariableHandler.getBOMNumber(),
					commonFunc.getDataFromTestDataSheet("InactiveStatus"));
			navigateToBOMBySearchAndClickBOMNo(VariableHandler.getBOMNumber());
			checkDisplayStatusInsideBOM(commonFunc.getDataFromTestDataSheet("InactiveStatus"));
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyInactivateBillOfMaterials': " + e);
			return false;
		}
	}

}
