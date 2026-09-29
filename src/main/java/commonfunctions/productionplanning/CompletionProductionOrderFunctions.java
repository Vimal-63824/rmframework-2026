package commonfunctions.productionplanning;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks March 05, 2025
 */
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.productionplanning.CompletionProductionOrderLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class CompletionProductionOrderFunctions {
	private boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();

	/**
	 * This Method used to click Allocate Deallocate Button in Header
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAllocateDeallocateButtonInHeader() {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.ComplProdOrderAllocateDeAllocateBtn,
				ElementType.Xpath);
		if (flag) {
			String value = webDB.getAttributeFromElement(
					CompletionProductionOrderLocators.ComplProdOrderAllocateDeAllocateBtn, ElementType.Xpath,
					"onclick");
			System.out.println("Value " + value);
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			if (value.contains("Allocate")) {
				webDB.moveToElement(CompletionProductionOrderLocators.ComplProdOrderAllocateDeAllocateBtn,
						ElementType.Xpath);
				webDB.clickAnElement(CompletionProductionOrderLocators.ComplProdOrderAllocateDeAllocateBtn,
						ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to switch to Pending Production Order Iframe
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchToPendingProdOrderIframe() {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingProdOrderIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(CompletionProductionOrderLocators.PendingProdOrderIframe, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingProdOrderSearchGoBtn,
					ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to switch to Allow Exception Iframe
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchToAllowExceptionIframe() {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.AllowExceptionIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(CompletionProductionOrderLocators.AllowExceptionIframe, ElementType.Xpath);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.AllowExceptionPostButton, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Allow Exception button in Production Completion
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAllowExceptionIconInProdCompletion() {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.AllowExceptionButton, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(CompletionProductionOrderLocators.AllowExceptionButton, ElementType.Xpath);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.AllowExceptionIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Allow Exception Button Clicked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to search Production Order Number in Pending Allow Exception
	 * and Click CheckBox
	 * 
	 * @author Sakthi-GWL
	 * @param ProdOrderNo
	 */
	public void searchProdOrderNoAndSelectCheckBoxInAllowException(String ProdOrderNo) {
		switchToAllowExceptionIframe();
		commonFunc.verifyAddTableSpecs(CompletionProductionOrderLocators.PendingSearchInput,
				CompletionProductionOrderLocators.PendingSearchInput, ProdOrderNo);
		logs.logging("INFO", "Pending Search Input Entered for Allow Exception: " + ProdOrderNo);
		flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingSearchGoButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CompletionProductionOrderLocators.PendingSearchGoButton, ElementType.Xpath);
			webDB.clickAnElement(CompletionProductionOrderLocators.PendingSearchGoButton, ElementType.Xpath);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.AllowExceptionSelectCheckBox(ProdOrderNo),
					ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(CompletionProductionOrderLocators.AllowExceptionSelectCheckBox(ProdOrderNo),
						ElementType.Xpath);
				flag = webDB.clickAnElement(CompletionProductionOrderLocators.AllowExceptionSelectCheckBox(ProdOrderNo),
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Production Order CheckBox Clicked in Allow Exception: " + ProdOrderNo);
				}
			}
		}
		webDB.switchToDefaultContent();
		assertTrue(flag);
	}

	/**
	 * This method used to fill Rejection Quantity as per rejection types in
	 * completion header
	 * 
	 * @author Sakthi-GWL
	 * @param RejectionType
	 * @param count
	 */
	public void fillProcessRejectInHeader(String RejectionType, String AcceptQuantity, String RejQty) {
		if (!RejectionType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ProcessRejection"))) {
			commonFunc.verifyAddTableSpecs(CompletionProductionOrderLocators.ProdCompAcceptQtyInput,
					CompletionProductionOrderLocators.ProdCompAcceptQtyInput, AcceptQuantity);
			logs.logging("INFO", "Quantity: '" + AcceptQuantity + "' Entered in Accept Quantity");
		}
		if (RejectionType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("PrimaryRejection"))) {
			commonFunc.verifyAddTableSpecs(CompletionProductionOrderLocators.ProdCompRejectPrimaryQtyInput,
					CompletionProductionOrderLocators.ProdCompRejectPrimaryQtyInput, RejQty);
			logs.logging("INFO", "RejQty: '" + RejQty + "' Entered in Primary Rejection");
		} else if (RejectionType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("SecondaryRejection"))) {
			commonFunc.verifyAddTableSpecs(CompletionProductionOrderLocators.ProdCompRejectPrimaryQtyInput,
					CompletionProductionOrderLocators.ProdCompRejectPrimaryQtyInput, "0");
			commonFunc.verifyAddTableSpecs(CompletionProductionOrderLocators.ProdCompRejectSecondaryQtyInput,
					CompletionProductionOrderLocators.ProdCompRejectSecondaryQtyInput, RejQty);
			logs.logging("INFO", "RejQty: '" + RejQty + "' Entered in Secondary Rejection");
		} else if (RejectionType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ProcessRejection"))) {
			commonFunc.verifyAddTableSpecs(CompletionProductionOrderLocators.ProdCompRejectionProcessInput,
					CompletionProductionOrderLocators.ProdCompRejectionProcessInput, RejQty);
			logs.logging("INFO", "RejQty: '" + RejQty + "' Entered in Process Rejection");
		} else {
			logs.logging("INFO", "Invalid Process Rejection Type... Please Enter Valid Rejection Type");
			Assert.fail("Invalid Process Rejection Type in Completion Header... Please Enter Valid Rejection Type");
		}
	}

	/**
	 * This method used to click add icon and fill rejection defects in completion
	 * production
	 * 
	 * @author Sakthi-GWL
	 * @param DefectType
	 * @param DefectQty
	 * @param RepairQty
	 * @param ScrapQty
	 */
	public void clickAddIconAndFillRejectionDefectsInCompletion(String DefectType, String DefectQty, String RepairQty,
			String ScrapQty) {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.ProdCompAddDefectButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CompletionProductionOrderLocators.ProdCompAddDefectButton, ElementType.Xpath);
			webDB.clickAnElement(CompletionProductionOrderLocators.ProdCompAddDefectButton, ElementType.Xpath);
			commonFunc.verifyAddTableLovValue(CompletionProductionOrderLocators.ProdCompDefectTypeTableData,
					CompletionProductionOrderLocators.ProdCompDefectTypeTableLovBtn, DefectType);
			logs.logging("INFO", "Completion Defects Add Icon Clicked");
			logs.logging("INFO", "Defect Type Selected in Rejection Defects: " + DefectType);
			commonFunc.verifyAddTableSpecs(CompletionProductionOrderLocators.ProdCompDefectQtyTableData,
					CompletionProductionOrderLocators.ProdCompDefectQtyTableInput, DefectQty);
			logs.logging("INFO", "Defect Quantity Entered in Rejection Defects: " + DefectQty);
			commonFunc.verifyAddTableSpecs(CompletionProductionOrderLocators.ProdCompRepairQtyTableData,
					CompletionProductionOrderLocators.ProdCompRepairQtyTableInput, RepairQty);
			logs.logging("INFO", "Repair Quantity Entered in Rejection Defects: " + RepairQty);
			commonFunc.verifyAddTableSpecs(CompletionProductionOrderLocators.ProdCompScrapQtyTableData,
					CompletionProductionOrderLocators.ProdCompScrapQtyTableInput, ScrapQty);
			logs.logging("INFO", "Scrap Quantity Entered in Rejection Defects: " + ScrapQty);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.ProdCompSaveDefectButton, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(CompletionProductionOrderLocators.ProdCompSaveDefectButton, ElementType.Xpath);
				webDB.clickAnElement(CompletionProductionOrderLocators.ProdCompSaveDefectButton, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Defects Added and Saved Successfully");
					if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
					commonFunc.checkSuccessToastMessageDisappear();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click add and fill scrap products in completion
	 * production
	 * 
	 * @author Sakthi-GWL
	 * @param ItemName
	 * @param ScrapWH
	 * @param ScrapQty
	 */
	public void clickAddInScrapProductInCompletion(String ItemName, String ScrapWH, String ScrapQty) {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.ProdCompAddScrapButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CompletionProductionOrderLocators.ProdCompAddScrapButton, ElementType.Xpath);
			webDB.clickAnElement(CompletionProductionOrderLocators.ProdCompAddScrapButton, ElementType.Xpath);
			commonFunc.verifyAddTableLovValue(CompletionProductionOrderLocators.ProdCompScrapByProductItemTableData,
					CompletionProductionOrderLocators.ProdCompScrapByProductItemTableLovBtn, ItemName);
			logs.logging("INFO", "Completion Scrap Products Add Icon Clicked");
			logs.logging("INFO", "Scrap Item Selected in Scrap Products: " + ItemName);
			commonFunc.verifyAddTableLovValue(CompletionProductionOrderLocators.ProdCompScrapByProductWHTableData,
					CompletionProductionOrderLocators.ProdCompScrapByProductWHTableLovBtn, ScrapWH);
			logs.logging("INFO", "Scrap Warehouse Selected in Scrap Products: " + ScrapWH);
			commonFunc.verifyAddTableSpecs(CompletionProductionOrderLocators.ProdCompScrapByProductQtyTableData,
					CompletionProductionOrderLocators.ProdCompScrapByProductQtyTableInput, ScrapQty);
			logs.logging("INFO", "Scrap Quantity Entered in Scrap Products: " + ScrapQty);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.ProdCompSaveScrapButton, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(CompletionProductionOrderLocators.ProdCompSaveScrapButton, ElementType.Xpath);
				webDB.clickAnElement(CompletionProductionOrderLocators.ProdCompSaveScrapButton, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Scrap Product Added and Saved Successfully");
					if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
					commonFunc.checkSuccessToastMessageDisappear();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click Add In Consumption In Scrap Product and Save
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAddInConsumptionInScrapProduct() {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.ProdCompScrapByProductAddConsCheckBox,
				ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CompletionProductionOrderLocators.ProdCompScrapByProductAddConsCheckBox,
					ElementType.Xpath);
			webDB.clickAnElement(CompletionProductionOrderLocators.ProdCompScrapByProductAddConsCheckBox,
					ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.isElementSelected(CompletionProductionOrderLocators.ProdCompScrapByProductAddConsCheckBox,
					ElementType.Xpath);
			webDB.waitForElement(CompletionProductionOrderLocators.ProdCompSaveScrapButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add In Consumption checkbox clicked in Scrap Product");
				webDB.moveToElement(CompletionProductionOrderLocators.ProdCompSaveScrapButton, ElementType.Xpath);
				webDB.clickAnElement(CompletionProductionOrderLocators.ProdCompSaveScrapButton, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Scrap Product Saved Successfully");
					if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
					commonFunc.checkSuccessToastMessageDisappear();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to search Production Order No in Search Bar
	 * 
	 * @author Sakthi-GWL
	 * @param ProdNo
	 */
	public void searchProductionOrderNoAndVerifyFilter(String ProdNo) {
		switchToPendingProdOrderIframe();
		commonFunc.clearFiltersInFindPage();
		commonFunc.searchAndSelectValueInFilters(CompletionProductionOrderLocators.Pend_ProdOrderLovBtn, ProdNo);
		switchToPendingProdOrderIframe();
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CompletionProductionOrderLocators.PendingSearchBtn, ElementType.Xpath);
			webDB.clickAnElement(CompletionProductionOrderLocators.PendingSearchBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingProdOrder_ProdNoTableData(),
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Searched with Production Order No: " + ProdNo);
				String DisplayProdNo = webDB.getTextFromElement(
						CompletionProductionOrderLocators.PendingProdOrder_ProdNoTableData(), ElementType.Xpath);
				flag = DisplayProdNo.contains(ProdNo);
				if (flag) {
					logs.logging("INFO", "Filtered Successfully with Production Order No: " + DisplayProdNo);
				}
			}
		}
		webDB.switchToDefaultContent();
		assertTrue(flag);
	}

	/**
	 * This Method used to validate Item and Process in search result table
	 * 
	 * @author Sakthi-GWL
	 * @param Item
	 * @param Process
	 */
	public void validateItemAndProcessInPendingProdOrderTable(String Item, String Process) {
		switchToPendingProdOrderIframe();
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingProdOrder_ItemTableData(),
				ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CompletionProductionOrderLocators.PendingProdOrder_ItemTableData(), ElementType.Xpath);
			String DisplayItem = webDB.getTextFromElement(
					CompletionProductionOrderLocators.PendingProdOrder_ItemTableData(), ElementType.Xpath);
			logs.logging("INFO", "Expected Item: " + Item);
			logs.logging("INFO", "Displayed Item: " + DisplayItem);
			flag = DisplayItem.equalsIgnoreCase(Item);
			if (flag) {
				logs.logging("INFO", "Validated Displayed Item is Matched in Pending Production Order table data");
				String DisplayProcess = webDB.getTextFromElement(
						CompletionProductionOrderLocators.PendingProdOrder_ProcessTableData(), ElementType.Xpath);
				logs.logging("INFO", "Expected Process: " + Process);
				logs.logging("INFO", "Displayed Process: " + DisplayProcess);
				flag = DisplayProcess.equalsIgnoreCase(Process);
				if (flag) {
					logs.logging("INFO",
							"Validated Displayed Process is Matched in Pending Production Order table data");
				}
			}
		}
		webDB.switchToDefaultContent();
		assertTrue(flag);
	}

	/**
	 * This Method used to select Pending production order no and click create
	 * completion process button
	 * 
	 * @author Sakthi-GWL
	 * @param ProdNo
	 */
	public void selectPendingProdOrderAndClickCreateCompletionProcess(String ProdNo, boolean allProcess) {
		switchToPendingProdOrderIframe();
		webDB.moveToElement(CompletionProductionOrderLocators.PendingProdOrder_SelectCheckboxTableData(ProdNo),
				ElementType.Xpath);
		webDB.clickAnElement(CompletionProductionOrderLocators.PendingProdOrder_SelectCheckboxTableData(ProdNo),
				ElementType.Xpath);
		flag = webDB.waitForElement(CompletionProductionOrderLocators.CreateCompletionProcessBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CompletionProductionOrderLocators.CreateCompletionProcessBtn, ElementType.Xpath);
			webDB.doubleClickAnElement(CompletionProductionOrderLocators.CreateCompletionProcessBtn, ElementType.Xpath);
			flag = webDB.waitForClickElement(CompletionProductionOrderLocators.CreateCompletionProcessOkBtn,
					ElementType.Xpath);
			commonFunc.hardWait(2000);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				logs.logging("INFO", "Create Completion Process Button Clicked");
				commonFunc.searchAndSelectValueInFilters(CompletionProductionOrderLocators.SingleLocId,
						commonFunc.getDataFromTestDataSheet("Loc_100"));
				switchToPendingProdOrderIframe();
				webDB.moveToElement(CompletionProductionOrderLocators.CreateCompletionProcessOkBtn, ElementType.Xpath);
				webDB.clickAnElement(CompletionProductionOrderLocators.CreateCompletionProcessOkBtn, ElementType.Xpath);
				webDB.switchToDefaultContent();
				if (allProcess) {
					if (webDB.verifyIsElementDisplayed(CompletionProductionOrderLocators.MultipleProceedRestOprYesBtn,
							ElementType.Xpath)) {
						flag = webDB.clickAnElement(CompletionProductionOrderLocators.MultipleProceedRestOprYesBtn,
								ElementType.Xpath);
					}
				} else if (!allProcess) {
					if (webDB.verifyIsElementDisplayed(CompletionProductionOrderLocators.MultipleProceedRestOprNoBtn,
							ElementType.Xpath)) {
						flag = webDB.clickAnElement(CompletionProductionOrderLocators.MultipleProceedRestOprNoBtn,
								ElementType.Xpath);
						logs.logging("INFO", "Create Completion Process clicked with all process No");
					}
				}
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(CompletionProductionOrderLocators.ComplProdOrderShiftLovBtn,
						ElementType.Xpath);
				if (flag) {
					String ProdComplNo = webDB.getTextFromElement(
							CompletionProductionOrderLocators.ComplProdOrderDisplayCompletionProdOrderNo,
							ElementType.Xpath);
					VariableHandler.setProductionCompletionOrderNo(ProdComplNo);
					logs.logging("INFO", "Production Completion Order Number Is: " + ProdComplNo);
					logs.logging("INFO", "Create Completion Process Ok Button Clicked");
					if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to select shift and save header in completion production
	 * order
	 * 
	 * @author Sakthi-GWL
	 * @param shift
	 */
	public void selectShiftAndSaveHeaderInCompletion(String shift) {
		commonFunc.searchAndSelectValueInFilters(CompletionProductionOrderLocators.ComplProdOrderShiftLovBtn, shift);
		flag = webDB.waitForElement(CompletionProductionOrderLocators.ComplProdHeaderSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CompletionProductionOrderLocators.ComplProdHeaderSaveIcon, ElementType.Xpath);
			webDB.clickAnElement(CompletionProductionOrderLocators.ComplProdHeaderSaveIcon, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Header Saved Succesfully");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				String ProdComplNo = webDB.getTextFromElement(
						CompletionProductionOrderLocators.ComplProdOrderDisplayCompletionProdOrderNo,
						ElementType.Xpath);
				VariableHandler.setProductionCompletionOrderNo(ProdComplNo);
				logs.logging("INFO", "Production Completion Order Number Is: " + ProdComplNo);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to select shift and save header in completion production
	 * order
	 * 
	 * @author Sakthi-GWL
	 * @param shift
	 */
	public void clickSaveHeaderInCompletion() {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.ComplProdHeaderSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CompletionProductionOrderLocators.ComplProdHeaderSaveIcon, ElementType.Xpath);
			webDB.clickAnElement(CompletionProductionOrderLocators.ComplProdHeaderSaveIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Header Saved Succesfully");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Post Icon in completion production order
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickPostButtonInCompletionProcess() {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.ComplProdPostBtn, ElementType.Xpath);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.checkSuccessToastMessageDisappear();
			webDB.moveToElement(CompletionProductionOrderLocators.ComplProdPostBtn, ElementType.Xpath);
			flag = webDB.clickAnElement(CompletionProductionOrderLocators.ComplProdPostBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
//			flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
			if (flag) {
				logs.logging("INFO", "Post Button Clicked Succesfully");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to validate Process and Consumption warehouse in completion
	 * Production Order
	 * 
	 * @author Sakthi-GWL
	 * @param Process
	 * @param warehouse
	 */
	public void validateProcessConsumptionWarehouse(String Process, String warehouse) {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.ComplProdOrderDisplayProcess, ElementType.Xpath);
		if (flag) {
			String DisplayProcess = webDB.getTextFromElement(
					CompletionProductionOrderLocators.ComplProdOrderDisplayProcess, ElementType.Xpath);
			flag = DisplayProcess.equalsIgnoreCase(Process);
			logs.logging("INFO", "Expected Process: " + Process);
			logs.logging("INFO", "Displayed Status: " + DisplayProcess);
			if (flag) {
				logs.logging("INFO", "Verified Process Displayed in Completion Screen");
				flag = webDB.waitForElement(CompletionProductionOrderLocators.ConsumptionWH_TableData,
						ElementType.Xpath);
				if (flag) {
					String DisplayWarehouse = webDB.getTextFromElement(
							CompletionProductionOrderLocators.ConsumptionWH_TableData, ElementType.Xpath);
					flag = DisplayWarehouse.equalsIgnoreCase(warehouse);
					logs.logging("INFO", "Expected Consumption Warehouse: " + warehouse);
					logs.logging("INFO", "Displayed Consumption Warehouse: " + DisplayWarehouse);
					if (flag) {
						logs.logging("INFO", "Verified Consumption Warehouse Displayed in Completion Screen");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click pending icon in Completion screen
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickPendingIconInCompletionProcess() {
		flag = webDB.waitForClickElement(CompletionProductionOrderLocators.CompletionProcessPendingIcon,
				ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CompletionProductionOrderLocators.CompletionProcessPendingIcon, ElementType.Xpath);
			webDB.clickAnElement(CompletionProductionOrderLocators.CompletionProcessPendingIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.Pending_MRCreateBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Production Planning - Pending Icon Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Search Prod Number And Open Desired Status
	 * 
	 * @author Deepan-GWL
	 * @param ProdNumber
	 * @param Status
	 */
	public void verifySearchProductionOrdeNumberAndOpenDesiredStatus(String ProdNumber, String Status) {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.ProductionOrderNumber, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(CompletionProductionOrderLocators.TransLovButton, ProdNumber);
			logs.logging("INFO", "Entered Completion Production Ordered Number Is: " + ProdNumber);
			flag = webDB.selectDropDownByText(CompletionProductionOrderLocators.SelectStatus, Status,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Entered Status Is: " + Status);
				flag = webDB.waitForElement(CompletionProductionOrderLocators.SearchButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CompletionProductionOrderLocators.SearchButton, ElementType.Xpath);
					webDB.waitUntilElementToNotBeStale(
							CompletionProductionOrderLocators.CompletionTransNumber(ProdNumber), ElementType.Xpath);
					flag = webDB.waitForElement(CompletionProductionOrderLocators.CompletionTransNumber(ProdNumber),
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Verified Completion Trans Number Is Displayed");
						webDB.clickAnElement(CompletionProductionOrderLocators.CompletionTransNumber(ProdNumber),
								ElementType.Xpath);
						flag = webDB.waitForElement(CompletionProductionOrderLocators.Consumption, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Verified Navigate To Completion Screen");
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click pending icon in Completion Multi Process screen
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickPendingIconInCompletionMultiProcess() {
		flag = webDB.waitForClickElement(CompletionProductionOrderLocators.MultiProc_PendingIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CompletionProductionOrderLocators.MultiProc_PendingIcon, ElementType.Xpath);
			webDB.clickAnElement(CompletionProductionOrderLocators.MultiProc_PendingIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.MultiProc_PendingIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Completion Prod Order Multi Process- Pending Icon Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Production Order Pending Queue Inside
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyProductionOrderPendingQueueInside(String ExpectedProcessName) {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.OrderQty(), ElementType.Xpath);
		if (flag) {
			String ProcessName = webDB.getTextFromElement(CompletionProductionOrderLocators.ProcessName(),
					ElementType.Xpath);
			logs.logging("INFO", "Process Name Is: " + ProcessName);
			flag = ProcessName.equals(ExpectedProcessName);
			if (flag) {
				logs.logging("INFO", "Verified Process Name SuccessFully");
				String OrderQty = webDB.getTextFromElement(CompletionProductionOrderLocators.OrderQty(),
						ElementType.Xpath);
				logs.logging("INFO", "Ordered Quantity Is: " + OrderQty);
				String QueueQty = webDB.getTextFromElement(CompletionProductionOrderLocators.QueueQty(),
						ElementType.Xpath);
				logs.logging("INFO", "Queue Quantity Is: " + QueueQty);
				String CompQty = webDB.getDomPropertyFromElement(CompletionProductionOrderLocators.CompQty(),
						ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
				logs.logging("INFO", "Completion Qunatity Is: " + CompQty);
				flag = OrderQty.equals(QueueQty);
				if (flag) {
					logs.logging("INFO", "Verified Ordered Quantity And Queue Quantity");
					flag = OrderQty.equals(CompQty);
					if (flag) {
						logs.logging("INFO", "Verified Order Qunatity And Completion Quantity");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to select shift and save header in completion Multi Process
	 * production order
	 * 
	 * @author Sakthi-GWL
	 * @param shift
	 */
	public void selectShiftAndSaveHeaderInCompletionMultiProcess(String shift) {
		commonFunc.searchAndSelectValueInFilters(CompletionProductionOrderLocators.ComplProdOrderShiftLovBtn, shift);
		flag = webDB.waitForElement(CompletionProductionOrderLocators.ComplProdHeaderSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CompletionProductionOrderLocators.ComplProdHeaderSaveIcon, ElementType.Xpath);
			webDB.clickAnElement(CompletionProductionOrderLocators.ComplProdHeaderSaveIcon, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to search Production Order No in Search Bar
	 * 
	 * @author Sakthi-GWL
	 * @param ProdNo
	 */

	public void searchProductionOrderNoAndVerifyFilterAfterInside(String ProdNo) {
		switchToPendingProdOrderIframe();
		commonFunc.hardWait(2000);
		flag = webDB.waitForClickElement(CompletionProductionOrderLocators.PendingProdOrderSearchBar,
				ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(CompletionProductionOrderLocators.PendingProdOrderSearchBar, ElementType.Xpath);
			webDB.sendTextToAnElement(CompletionProductionOrderLocators.PendingProdOrderSearchBar, ProdNo,
					ElementType.Xpath);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingProdOrderSearchGoBtn,
					ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CompletionProductionOrderLocators.PendingProdOrderSearchGoBtn, ElementType.Xpath);
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingProdOrder_ProdNoTableData(),
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Searched with Production Order No: " + ProdNo);
					String DisplayProdNo = webDB.getTextFromElement(
							CompletionProductionOrderLocators.PendingProdOrder_ProdNoTableData(), ElementType.Xpath);
					flag = DisplayProdNo.equalsIgnoreCase(ProdNo);
					if (flag) {
						logs.logging("INFO", "Filtered Successfully with Production Order No: " + DisplayProdNo);
					}
				}
			}
		}
		if (flag) {
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingProdOrderIframeCloseIcon,
					ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Production Order Pending Queue Inside
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyProductionOrderPendingQueueAfterInsideProcess(String ExpectedProcessName) {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.OrderQty(), ElementType.Xpath);
		if (flag) {
			String ProcessName = webDB.getTextFromElement(CompletionProductionOrderLocators.ProcessName(),
					ElementType.Xpath);
			logs.logging("INFO", "Process Name Is: " + ProcessName);
			flag = ProcessName.equals(ExpectedProcessName);
			if (flag) {
				logs.logging("INFO", "Verified Process Name SuccessFully");
				String OrderQty = webDB.getTextFromElement(CompletionProductionOrderLocators.OrderQty(),
						ElementType.Xpath);
				logs.logging("INFO", "Ordered Quantity Is: " + OrderQty);
				String QueueQty = webDB.getTextFromElement(CompletionProductionOrderLocators.QueueQty(),
						ElementType.Xpath);
				logs.logging("INFO", "Queue Quantity Is: " + QueueQty);
				String InsideQty = webDB.getTextFromElement(CompletionProductionOrderLocators.InsideQty(),
						ElementType.Xpath);
				logs.logging("INFO", "Inside Quantity Is: " + InsideQty);
				String CompQty = webDB.getDomPropertyFromElement(CompletionProductionOrderLocators.CompQty(),
						ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
				logs.logging("INFO", "Completion Qunatity Is: " + CompQty);
				flag = QueueQty.equals(commonFunc.getDataFromTestDataSheet("ZeroValue"));
				if (flag) {
					logs.logging("INFO", "Verified Queue Quantity Is Zero Value");
					flag = OrderQty.equals(InsideQty);
					if (flag) {
						logs.logging("INFO", "Verified Ordered Quantity And Inside Quantity");
						flag = OrderQty.equals(CompQty);
						if (flag) {
							logs.logging("INFO", "Verified Order Qunatity And Completion Quantity");
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Select Machine And Operator
	 * 
	 * @author Deepan-GWL
	 * @param MachineName
	 * @param Operator
	 */
	public void selectMachineAndOperator(String MachineName, String Operator) {
		commonFunc.searchAndSelectValueInFilters(CompletionProductionOrderLocators.MachineLovButton, MachineName);
		commonFunc.searchAndSelectValueInFilters(CompletionProductionOrderLocators.OperatorLovButton, Operator);
		flag = webDB.waitForElement(CompletionProductionOrderLocators.ComplProdHeaderSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CompletionProductionOrderLocators.ComplProdHeaderSaveIcon, ElementType.Xpath);
			webDB.clickAnElement(CompletionProductionOrderLocators.ComplProdHeaderSaveIcon, ElementType.Xpath);

			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Header Saved Succesfully");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}

//				String ProdComplNo = webDB.getTextFromElement(
//						CompletionProductionOrderLocators.MultiProc_DisplayComplProdOrderNo, ElementType.Xpath);
//				VariableHandler.setProductionCompletionOrderNo(ProdComplNo);
//				logs.logging("INFO", "Multi process Production Completion Order Number Is: " + ProdComplNo);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Post Icon in completion Multi Process production
	 * order
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickPostButtonInCompletionMultiProcess() {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.ComplProdPostBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CompletionProductionOrderLocators.ComplProdPostBtn, ElementType.Xpath);
			webDB.clickAnElement(CompletionProductionOrderLocators.ComplProdPostBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Post Button Clicked Succesfully");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Navigate To Resources Machine Tab
	 * 
	 * @author Deepan-GWL
	 */

	public void verifyNavigateToResourcesMachineTab() {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.ResourcesTab, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Resource Tab Is Displayed");
			webDB.clickAnElement(CompletionProductionOrderLocators.ResourcesTab, ElementType.Xpath);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.ResourcesMachineTab, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Resources Machine Tab Is Displayed");
				webDB.clickAnElement(CompletionProductionOrderLocators.ResourcesMachineTab, ElementType.Xpath);
				flag = webDB.waitForElement(CompletionProductionOrderLocators.MachineLineAmount, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Verified Navigate To Resources Machine Tab");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To verify Navigate To Resources Labour Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyNavigateToResourcesLabourTab() {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.ResourcesLabourTab, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Resources Labour Tab Is Displayed");
			webDB.clickAnElement(CompletionProductionOrderLocators.ResourcesLabourTab, ElementType.Xpath);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.LabourLineAmount, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Verified Navigate To Labour Tab");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Machine Unit Rate Line Amount
	 * 
	 * @author Deepan-GWL
	 * @param MachineUnit
	 * @param MachineUnitRate
	 * @param MachineLineAmount
	 */
	public void verifyMachineUnitRateLineAmountCalculation(String MachineUnit, String MachineUnitRate,
			String MachineLineAmount, String ProcessName) {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.MachineUnit, ElementType.Xpath);
		if (flag) {
			String HeaderProcessName = webDB.getTextFromElement(CompletionProductionOrderLocators.HeaderProcessName,
					ElementType.Xpath);
			String UnitRate = webDB.getTextFromElement(CompletionProductionOrderLocators.MachineUnitRate,
					ElementType.Xpath);
			String Unit = webDB.getTextFromElement(CompletionProductionOrderLocators.MachineUnit, ElementType.Xpath);
			String LineAmount = webDB.getTextFromElement(CompletionProductionOrderLocators.MachineLineAmount,
					ElementType.Xpath);
			commonFunc.verifyAssertionBothValues(Unit, MachineUnit);
			commonFunc.verifyAssertionBothValues(UnitRate, MachineUnitRate);
			commonFunc.verifyAssertionBothValues(LineAmount, MachineLineAmount);
			commonFunc.verifyAssertionBothValues(HeaderProcessName, ProcessName);

		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Labour Unit Rate Line Amount Calculation
	 * 
	 * @author Deepan-GWL
	 * @param LabourQtyHrs
	 * @param LabourUnitRate
	 * @param LabourLineAmount
	 */
	public void verifyLabourUnitRateLineAmountCalculation(String LabourQtyHrs, String LabourUnitRate,
			String LabourLineAmount, String ProcessName) {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.LabourLineAmount, ElementType.Xpath);
		if (flag) {
			String HeaderProcessName = webDB.getTextFromElement(CompletionProductionOrderLocators.HeaderProcessName,
					ElementType.Xpath);
			String UnitRate = webDB.getTextFromElement(CompletionProductionOrderLocators.LabourUnitRate,
					ElementType.Xpath);
			String QtyHrs = webDB.getTextFromElement(CompletionProductionOrderLocators.LabourQtyHrs, ElementType.Xpath);
			String LineAmount = webDB.getTextFromElement(CompletionProductionOrderLocators.LabourLineAmount,
					ElementType.Xpath);
			commonFunc.verifyAssertionBothValues(QtyHrs, LabourQtyHrs);
			commonFunc.verifyAssertionBothValues(UnitRate, LabourUnitRate);
			commonFunc.verifyAssertionBothValues(LineAmount, LabourLineAmount);
			commonFunc.verifyAssertionBothValues(HeaderProcessName, ProcessName);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to search Multiple Production Order No in Search Bar
	 * 
	 * @author Sakthi-GWL
	 * @param ProdNo
	 */
	public void searchMultipleProductionOrderNoAndVerifyFilter(String ProdNo) {
		switchToPendingMultipleProdOrderIframe();
		flag = webDB.waitForClickElement(CompletionProductionOrderLocators.PendingProdOrderSearchBar,
				ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(CompletionProductionOrderLocators.PendingProdOrderSearchBar, ElementType.Xpath);
			webDB.sendTextToAnElement(CompletionProductionOrderLocators.PendingProdOrderSearchBar, ProdNo,
					ElementType.Xpath);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingProdOrderSearchGoBtn,
					ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CompletionProductionOrderLocators.PendingProdOrderSearchGoBtn, ElementType.Xpath);
				flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingProdOrder_ProdNoTableData(),
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Searched with Production Order No: " + ProdNo);
					String DisplayProdNo = webDB.getTextFromElement(
							CompletionProductionOrderLocators.PendingProdOrder_ProdNoTableData(), ElementType.Xpath);
					logs.logging("INFO", "Filtered Successfully with Production Order No: " + DisplayProdNo);
				}
			}
		}
		if (flag) {
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingProdOrderIframeCloseIcon,
					ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to switch to Pending Multiple Production Order Iframe
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchToPendingMultipleProdOrderIframe() {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.MultiProc_PendingIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(CompletionProductionOrderLocators.MultiProc_PendingIframe, ElementType.Xpath);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingProdOrderSearchGoBtn,
					ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to select Pending Multiple production order no and click
	 * create completion process button
	 * 
	 * @author Sakthi-GWL
	 * @param ProdNo
	 */
	public void selectPendingMultipleProdOrderAndClickCreateCompletionProcess(String ProdNo) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(CompletionProductionOrderLocators.Pending_InsideProcCreateBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CompletionProductionOrderLocators.Pending_InsideProcCreateBtn, ElementType.Xpath);
			webDB.clickAnElement(CompletionProductionOrderLocators.Pending_InsideProcCreateBtn, ElementType.Xpath);
			switchToPendingProdOrderIframe();
			commonFunc.checkShowMyRecordAndUnselectAll(CompletionProductionOrderLocators.Pending_TableShowMyRecordBtn,
					CompletionProductionOrderLocators.Pending_TableShowMyRecordBtnClicked,
					CompletionProductionOrderLocators.Pending_TableSelectAllCheckBox);
			commonFunc.expandFindTable();
			commonFunc.clearFiltersInFindPage();
			commonFunc.searchAndSelectValueInFilters(CompletionProductionOrderLocators.Pend_ProdOrderLovBtn, ProdNo);
			switchToPendingProdOrderIframe();
			flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingProdOrderSearchGoBtn,
					ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CompletionProductionOrderLocators.PendingProdOrderSearchGoBtn, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.expandFindTable();
				flag = webDB.waitForElement(
						CompletionProductionOrderLocators.PendingProdOrder_SelectCheckboxTableData(ProdNo),
						ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(
							CompletionProductionOrderLocators.PendingProdOrder_SelectCheckboxTableData(ProdNo),
							ElementType.Xpath);
					webDB.clickAnElement(
							CompletionProductionOrderLocators.PendingProdOrder_SelectCheckboxTableData(ProdNo),
							ElementType.Xpath);
					flag = webDB.waitForElement(CompletionProductionOrderLocators.CreateCompProc, ElementType.Xpath);
					if (flag) {
						webDB.moveToElement(CompletionProductionOrderLocators.CreateCompProc, ElementType.Xpath);
						webDB.doubleClickAnElement(CompletionProductionOrderLocators.CreateCompProc, ElementType.Xpath);
						logs.logging("INFO", "Create Completion Process Button Clicked");
						commonFunc.searchAndSelectValueInFilters(
								CompletionProductionOrderLocators.MultiplePendingMovingLocId,
								commonFunc.getDataFromTestDataSheet("Loc_100"));
						switchToPendingMultipleProdOrderIframe();
						webDB.moveToElement(CompletionProductionOrderLocators.MultipleCreateCompletionProcessOkBtn,
								ElementType.Xpath);
						webDB.clickAnElement(CompletionProductionOrderLocators.MultipleCreateCompletionProcessOkBtn,
								ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.checkRefreshCellsDisappear();
						webDB.switchToDefaultContent();
						if (webDB.verifyIsElementDisplayed(
								CompletionProductionOrderLocators.MultipleProceedRestOprYesBtn, ElementType.Xpath)) {
							webDB.clickAnElement(CompletionProductionOrderLocators.MultipleProceedRestOprYesBtn,
									ElementType.Xpath);
							VariableHandler.setMultipleProcessFlag(true);
						}
						flag = webDB.waitForElement(
								CompletionProductionOrderLocators.ComplProdOrderDisplayCompletionProdOrderNo,
								ElementType.Xpath);
						if (flag) {
							String ProdComplNo = webDB.getTextFromElement(
									CompletionProductionOrderLocators.ComplProdOrderDisplayCompletionProdOrderNo,
									ElementType.Xpath);
							VariableHandler.setProductionCompletionOrderNo(ProdComplNo);
							logs.logging("INFO", "Production Completion Order Number Is: " + ProdComplNo);
							logs.logging("INFO", "Create Completion Process Ok Button Clicked");
							if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
								webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
										ElementType.Xpath);
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Search Prod Number And Open Desired Status
	 * 
	 * @author Deepan-GWL
	 * @param ProdNumber
	 * @param Status
	 */
	public void verifySearchMultipleProductionOrdeNumberAndOpenDesiredStatus(String ProdNumber, String Status) {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.MultipleTransLovButton, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(CompletionProductionOrderLocators.MultipleTransLovButton,
					ProdNumber);
			logs.logging("INFO", "Searched Production Order Number Is: " + ProdNumber);
			flag = webDB.selectDropDownByText(CompletionProductionOrderLocators.MultipleSelectStatus, Status,
					ElementType.Xpath);
			logs.logging("INFO", "Selected Status Is: " + Status);
			if (flag) {
				flag = webDB.waitForElement(CompletionProductionOrderLocators.MultipleSearchButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CompletionProductionOrderLocators.MultipleSearchButton, ElementType.Xpath);
					flag = webDB.waitForElement(CompletionProductionOrderLocators.CompletionTransNumber(ProdNumber),
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Search Button Is Clicked");
						webDB.clickAnElement(CompletionProductionOrderLocators.CompletionTransNumber(ProdNumber),
								ElementType.Xpath);
						flag = webDB.waitForElement(CompletionProductionOrderLocators.MultiProc_ShiftLovBtn,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Completion Production Multiple Order Page Is Displayed");
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Select Process Checkbox In Process Details Page
	 * 
	 * @author Deepan-GWL
	 * @param ProdNo
	 */
	public void verifySelectProcessCheckBoxInProcessDetailsPage(String ProdNo) {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.MultipleProductionOrderNo(ProdNo),
				ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CompletionProductionOrderLocators.MultipleProductionOrderNo(ProdNo), ElementType.Xpath);
			logs.logging("INFO", "Multiple Process Details Button Is Displayed");
			webDB.clickAnElement(CompletionProductionOrderLocators.MultipleProcessDetails(ProdNo), ElementType.Xpath);
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(CompletionProductionOrderLocators.CloseButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Close Button Is Displayed");
				webDB.switchToFrame(CompletionProductionOrderLocators.MultipleProcessDetailIframe, ElementType.Xpath);
				flag = webDB.waitForElement(CompletionProductionOrderLocators.ProcessHeader, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Switched To Iframe Process Detail");
					commonFunc.hardWait(1000);
					webDB.javaScriptClickAnElement(CompletionProductionOrderLocators.MultipleProcessCheckBox(3),
							ElementType.Xpath);
					logs.logging("INFO", "check Box Is Clicked");
					commonFunc.hardWait(1000);
					webDB.switchToDefaultContent();
					flag = webDB.waitForElement(CompletionProductionOrderLocators.CloseButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Switched To Default Content");
						webDB.clickAnElement(CompletionProductionOrderLocators.CloseButton, ElementType.Xpath);
						switchToPendingMultipleProdOrderIframe();
						flag = webDB.waitForElement(CompletionProductionOrderLocators.MultipleProcessDetails(ProdNo),
								ElementType.Xpath);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to select Pending Multiple production order no and click
	 * create completion process button
	 * 
	 * @author Deepan-GWL
	 * @param ProdNo
	 */
	public void selectPendingMultipleProdOrderAndClickCreateCompletionProcessWithNoButton(String ProdNo) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(CompletionProductionOrderLocators.Pending_InsideProcCreateBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CompletionProductionOrderLocators.Pending_InsideProcCreateBtn, ElementType.Xpath);
			webDB.clickAnElement(CompletionProductionOrderLocators.Pending_InsideProcCreateBtn, ElementType.Xpath);
			switchToPendingProdOrderIframe();
			commonFunc.checkShowMyRecordAndUnselectAll(CompletionProductionOrderLocators.Pending_TableShowMyRecordBtn,
					CompletionProductionOrderLocators.Pending_TableShowMyRecordBtnClicked,
					CompletionProductionOrderLocators.Pending_TableSelectAllCheckBox);
			commonFunc.expandFindTable();
			commonFunc.clearFiltersInFindPage();
			commonFunc.searchAndSelectValueInFilters(CompletionProductionOrderLocators.Pend_ProdOrderLovBtn, ProdNo);
			switchToPendingProdOrderIframe();
			flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingProdOrderSearchGoBtn,
					ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CompletionProductionOrderLocators.PendingProdOrderSearchGoBtn, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.expandFindTable();
				flag = webDB.waitForElement(
						CompletionProductionOrderLocators.PendingProdOrder_SelectCheckboxTableData(ProdNo),
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Production Order Number Searched: " + ProdNo);
					webDB.moveToElement(
							CompletionProductionOrderLocators.PendingProdOrder_SelectCheckboxTableData(ProdNo),
							ElementType.Xpath);
					webDB.clickAnElement(
							CompletionProductionOrderLocators.PendingProdOrder_SelectCheckboxTableData(ProdNo),
							ElementType.Xpath);
					flag = webDB.waitForElement(CompletionProductionOrderLocators.CreateCompProc, ElementType.Xpath);
					if (flag) {
						webDB.moveToElement(CompletionProductionOrderLocators.CreateCompProc, ElementType.Xpath);
						webDB.doubleClickAnElement(CompletionProductionOrderLocators.CreateCompProc, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						logs.logging("INFO", "Create Completion Process Button Clicked");
						commonFunc.searchAndSelectValueInFilters(
								CompletionProductionOrderLocators.MultiplePendingMovingLocId,
								commonFunc.getDataFromTestDataSheet("Loc_100"));
						switchToPendingMultipleProdOrderIframe();
						webDB.moveToElement(CompletionProductionOrderLocators.MultipleCreateCompletionProcessOkBtn,
								ElementType.Xpath);
						webDB.clickAnElement(CompletionProductionOrderLocators.MultipleCreateCompletionProcessOkBtn,
								ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.checkRefreshCellsDisappear();
						webDB.switchToDefaultContent();
						if (webDB.verifyIsElementDisplayed(
								CompletionProductionOrderLocators.MultipleProceedRestOprNoBtn, ElementType.Xpath)) {
							webDB.clickAnElement(CompletionProductionOrderLocators.MultipleProceedRestOprNoBtn,
									ElementType.Xpath);
							logs.logging("INFO", "Completion remaining process No Button Is Clicked");
							VariableHandler.setMultipleProcessFlag(false);
						} else {
							logs.logging("INFO", "Completion remaining process No Button Is Not Displayed");
							VariableHandler.setMultipleProcessFlag(true);
						}
						flag = webDB.waitForElement(
								CompletionProductionOrderLocators.ComplProdOrderDisplayCompletionProdOrderNo,
								ElementType.Xpath);
						if (flag) {
							String ProdComplNo = webDB.getTextFromElement(
									CompletionProductionOrderLocators.ComplProdOrderDisplayCompletionProdOrderNo,
									ElementType.Xpath);
							VariableHandler.setProductionCompletionOrderNo(ProdComplNo);
							logs.logging("INFO", "Production Completion Order Number Is: " + ProdComplNo);
							logs.logging("INFO", "Create Completion Process Ok Button Clicked");
							if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
								webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
										ElementType.Xpath);
							}
						}
						if (!flag) {
							String TotalMsg = webDB.getTextFromElement(CommonUiLocators.PostToastMessage,
									ElementType.Xpath);
							System.out.println("Before Text: " + TotalMsg);
							TotalMsg = TotalMsg.replace("Process Completion Record Created.", "").trim();
							TotalMsg = TotalMsg.replace("-", "").trim();
							System.out.println(TotalMsg);
							System.out.println("After Text: " + TotalMsg);
							VariableHandler.setProductionCompletionOrderNo(TotalMsg);
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Addition Consumption Raw Material
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyMaterialUnitCostAddition(int RowCount, int Colcount) {

		ArrayList<String> Unitcost = new ArrayList<>();
		List<WebElement> TotalUnitCost1 = webDB.getDriver()
				.findElements(By.xpath(CompletionProductionOrderLocators.TotalMaterialUnitCost));
		for (WebElement element : TotalUnitCost1) {
			String IndividualUnitCost = element.getText();
			Unitcost.add(IndividualUnitCost);
		}
		do {
			webDB.moveToElement(CompletionProductionOrderLocators.MaterialNextButton, ElementType.Xpath);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.MaterialNextButton, ElementType.Xpath);
			if (flag) {
				flag = webDB.isElementEnabled(CompletionProductionOrderLocators.MaterialNextButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CompletionProductionOrderLocators.MaterialNextButton, ElementType.Xpath);
					flag = webDB.waitForElement(CompletionProductionOrderLocators.MaterialNextButton,
							ElementType.Xpath);
					List<WebElement> TotalUnitCost = webDB.getDriver()
							.findElements(By.xpath(CompletionProductionOrderLocators.TotalMaterialUnitCost));
					for (WebElement element : TotalUnitCost) {
						String IndividualUnitCost = element.getText();
						Unitcost.add(IndividualUnitCost);
					}
				}
			}
		} while (webDB.isElementEnabled(CompletionProductionOrderLocators.MaterialNextButton, ElementType.Xpath));
		BigDecimal TotalNumber = new BigDecimal(0);
		for (String IndividualUnitCost : Unitcost) {
			TotalNumber = TotalNumber.add(new BigDecimal(IndividualUnitCost));
		}
		VariableHandler.setRawMaterialCost(TotalNumber.multiply(new BigDecimal(3)).toPlainString());
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ProductionCalculations.xlsx";
		excelUtils.writeDataInExcel(Filepath, webDB.getDataFromProperties("ProductionWithResourcesSheet"), RowCount,
				Colcount, VariableHandler.getRawMaterialCost());
		assertTrue(flag);
	}

	/**
	 * This method used to create Compl Document using Prod No in Pending
	 * 
	 * @author Sakthi-GWL
	 * @param Location
	 * @param ProdNo
	 */
	public void selectComplProdOrderDocUsingProdNoInPending(String Location, String ProdNo) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(CompletionProductionOrderLocators.Pending_InsideProcCreateBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CompletionProductionOrderLocators.Pending_InsideProcCreateBtn, ElementType.Xpath);
			webDB.clickAnElement(CompletionProductionOrderLocators.Pending_InsideProcCreateBtn, ElementType.Xpath);
			switchToPendingProdOrderIframe();
			commonFunc.checkShowMyRecordAndUnselectAll(CompletionProductionOrderLocators.Pending_TableShowMyRecordBtn,
					CompletionProductionOrderLocators.Pending_TableShowMyRecordBtnClicked,
					CompletionProductionOrderLocators.Pending_TableSelectAllCheckBox);
			commonFunc.expandFindTable();
			commonFunc.clearFiltersInFindPage();
			commonFunc.searchAndSelectValueInFilters(CompletionProductionOrderLocators.Pend_ProdOrderLovBtn, ProdNo);
			switchToPendingProdOrderIframe();
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingSearchBtn, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(CompletionProductionOrderLocators.PendingSearchBtn, ElementType.Xpath);
				webDB.clickAnElement(CompletionProductionOrderLocators.PendingSearchBtn, ElementType.Xpath);
				commonFunc.expandFindTable();
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(CompletionProductionOrderLocators.Pending_TableSelectAllCheckBox,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Pending Prod no Searched");
					webDB.moveToElement(CompletionProductionOrderLocators.Pending_TableSelectAllCheckBox,
							ElementType.Xpath);
					webDB.clickAnElement(CompletionProductionOrderLocators.Pending_TableSelectAllCheckBox,
							ElementType.Xpath);
					flag = webDB.waitForElement(CompletionProductionOrderLocators.CreateCompletionProcessBtn,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Pending Prod No Checkbox Clicked");
						webDB.moveToElement(CompletionProductionOrderLocators.CreateCompletionProcessBtn,
								ElementType.Xpath);
						webDB.doubleClickAnElement(CompletionProductionOrderLocators.CreateCompletionProcessBtn,
								ElementType.Xpath);
						commonFunc.searchAndSelectValueInFilters(CompletionProductionOrderLocators.SingleLocId,
								Location);
						switchToPendingProdOrderIframe();
						flag = webDB.waitForClickElement(CompletionProductionOrderLocators.CreateCompletionProcessOkBtn,
								ElementType.Xpath);
						if (flag) {
							commonFunc.checkRefreshCellsDisappear();
							logs.logging("INFO", "Create Completion Process Button Clicked");
							webDB.moveToElement(CompletionProductionOrderLocators.CreateCompletionProcessOkBtn,
									ElementType.Xpath);
							webDB.clickAnElement(CompletionProductionOrderLocators.CreateCompletionProcessOkBtn,
									ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							if (webDB.verifyIsElementDisplayed(
									CompletionProductionOrderLocators.MultipleProceedRestOprNoBtn, ElementType.Xpath)) {
								flag = webDB.clickAnElement(
										CompletionProductionOrderLocators.MultipleProceedRestOprNoBtn,
										ElementType.Xpath);
								logs.logging("INFO", "Create Completion Process clicked with all process No");
							}
							webDB.switchToDefaultContent();
							flag = webDB.waitForElement(CompletionProductionOrderLocators.ComplProdOrderShiftLovBtn,
									ElementType.Xpath);
							if (flag) {
								String ProdComplNo = webDB.getTextFromElement(
										CompletionProductionOrderLocators.ComplProdOrderDisplayCompletionProdOrderNo,
										ElementType.Xpath);
								VariableHandler.setProductionCompletionOrderNo(ProdComplNo);
								logs.logging("INFO", "Production Completion Order Number Is: " + ProdComplNo);
								logs.logging("INFO", "Create Completion Process Ok Button Clicked");
								if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon,
										ElementType.Xpath)) {
									webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
											ElementType.Xpath);
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Search Prod Number Status
	 * 
	 * @author Sakthi-GWL
	 * @param ProdNumber
	 * @param Status
	 */
	public void verifyCompletionProductionOrdeNumberWithDesiredStatus(String ProdNumber, String Status) {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.ProductionOrderNumber, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(CompletionProductionOrderLocators.TransLovButton, ProdNumber);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.SearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CompletionProductionOrderLocators.SearchButton, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				webDB.waitUntilElementToNotBeStale(CompletionProductionOrderLocators.SearchResultStatusData,
						ElementType.Xpath);
				flag = webDB.waitForElement(CompletionProductionOrderLocators.SearchResultStatusData,
						ElementType.Xpath);
				if (flag) {
					String DisplayStatus = webDB.getTextFromElement(
							CompletionProductionOrderLocators.SearchResultStatusData, ElementType.Xpath);
					logs.logging("INFO", "Displayed Prod.Comp Status: " + DisplayStatus);
					logs.logging("INFO", "Expected Prod.Comp Status: " + Status);
					flag = DisplayStatus.equalsIgnoreCase(Status);
					if (flag) {
						logs.logging("INFO", "Completion Prod Order Document Status Verified");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify click Create MR
	 * 
	 * @author Sakthi-GWL
	 * @param ProdNumber
	 */
	public void clickMaterialRequestCreationAndSearchProdNo(String ProdNumber) {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.Pending_MRCreateBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CompletionProductionOrderLocators.Pending_MRCreateBtn, ElementType.Xpath);
			webDB.clickAnElement(CompletionProductionOrderLocators.Pending_MRCreateBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingMRCreateIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending MR Creation Clicked");
				webDB.switchToFrame(CompletionProductionOrderLocators.PendingMRCreateIframe, ElementType.Xpath);
				commonFunc.checkShowMyRecordAndUnselectAll(
						CompletionProductionOrderLocators.Pending_MRCreateShowRecordBtn,
						CompletionProductionOrderLocators.Pending_MRCreateShowRecordClickedBtn,
						CompletionProductionOrderLocators.PendingMRSelectAllBtn);
				commonFunc.expandFindTable();
				commonFunc.clearFiltersInFindPage();
				commonFunc.searchAndSelectValueInFilters(CompletionProductionOrderLocators.PendingMRFindProdNoLov,
						ProdNumber);
				logs.logging("INFO", "Production Number Entered: " + ProdNumber);
				flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingMRCreateIframe, ElementType.Xpath);
				if (flag) {
					webDB.switchToFrame(CompletionProductionOrderLocators.PendingMRCreateIframe, ElementType.Xpath);
					flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingMRFindSearchBtn,
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(CompletionProductionOrderLocators.PendingMRFindSearchBtn,
								ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.expandFindTable();
						flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingMRSelectAllBtn(ProdNumber),
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Pending MR Find Search Button Clicked and Result Found");
						}
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify click Pending Inside Completion
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickPendingCompletionInsideProcess() {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.Pending_InsideProcCreateBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CompletionProductionOrderLocators.Pending_InsideProcCreateBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingProdOrderIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Process Inside Button Clicked");
			}
		}
		webDB.switchToDefaultContent();
		assertTrue(flag);
	}

	/**
	 * This method used to click select all and create MR
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickCheckBoxTableAndCreateAndStoreMRNo(String ProdNumber) {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingMRCreateIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(CompletionProductionOrderLocators.PendingMRCreateIframe, ElementType.Xpath);
			commonFunc.expandFindTable();
			flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingMRFindSearchBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CompletionProductionOrderLocators.PendingMRFindSearchBtn, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.expandFindTable();
				flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingMRSelectAllBtn(ProdNumber),
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Pending MR Iframe Switched");
					webDB.scrollToAnElement(CompletionProductionOrderLocators.PendingMRSelectAllBtn(ProdNumber),
							ElementType.Xpath);
					webDB.moveToElement(CompletionProductionOrderLocators.PendingMRSelectAllBtn(ProdNumber),
							ElementType.Xpath);
					webDB.clickAnElement(CompletionProductionOrderLocators.PendingMRSelectAllBtn(ProdNumber),
							ElementType.Xpath);
					flag = webDB.waitForElement(CompletionProductionOrderLocators.CreateMRButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Pending MR Select All Button Clicked");
						webDB.moveToElement(CompletionProductionOrderLocators.CreateMRButton, ElementType.Xpath);
						webDB.clickAnElement(CompletionProductionOrderLocators.CreateMRButton, ElementType.Xpath);
						flag = webDB.waitForElement(CompletionProductionOrderLocators.CreateMROkButton,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Create MR Button Clicked");
							webDB.clickAnElement(CompletionProductionOrderLocators.CreateMROkButton, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							webDB.switchToDefaultContent();
							flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage,
									ElementType.Xpath, 60);
							if (flag) {
								logs.logging("INFO", "Material Request Created Successfully");
								String Message = webDB.getTextFromElement(CommonUiLocators.PostToastMessage,
										ElementType.Xpath);
								logs.logging("INFO", "Success Message: " + Message);
								Message = Message.replace("Material Request Created-", "").trim();
								logs.logging("INFO", "After Split MR No: " + Message);
								VariableHandler.setMaterialRequestNo(Message);
								VariableHandler.setMRNumber(Message);
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to click select all and create MR
	 * 
	 * @author Sakthi-GWL
	 * @param String LocId
	 * @param String ProdNumber
	 */
	public void clickCheckBoxTableAndCreateAndStoreMRNo(String LocId, String ProdNumber) {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingMRCreateIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(CompletionProductionOrderLocators.PendingMRCreateIframe, ElementType.Xpath);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingMRSelectAllBtn(ProdNumber),
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending MR Iframe Switched");
				webDB.moveToElement(CompletionProductionOrderLocators.PendingMRSelectAllBtn(ProdNumber),
						ElementType.Xpath);
				webDB.clickAnElement(CompletionProductionOrderLocators.PendingMRSelectAllBtn(ProdNumber),
						ElementType.Xpath);
				flag = webDB.waitForElement(CompletionProductionOrderLocators.CreateMRButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Pending MR Select All Button Clicked");
					webDB.moveToElement(CompletionProductionOrderLocators.CreateMRButton, ElementType.Xpath);
					webDB.clickAnElement(CompletionProductionOrderLocators.CreateMRButton, ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(CompletionProductionOrderLocators.CreateMRLocIdLov, LocId);
					webDB.waitForElement(CompletionProductionOrderLocators.PendingMRCreateIframe, ElementType.Xpath);
					webDB.switchToFrame(CompletionProductionOrderLocators.PendingMRCreateIframe, ElementType.Xpath);
					flag = webDB.waitForElement(CompletionProductionOrderLocators.CreateMROkButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Create MR Button Clicked");
						webDB.clickAnElement(CompletionProductionOrderLocators.CreateMROkButton, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						webDB.switchToDefaultContent();
						flag = webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath,
								60);
						if (flag) {
							logs.logging("INFO", "Material Request Created Successfully");
							String Message = webDB.getTextFromElement(CommonUiLocators.PostToastMessage,
									ElementType.Xpath);
							logs.logging("INFO", "Success Message: " + Message);
							Message = Message.replace("Material Request Created-", "").trim();
							logs.logging("INFO", "After Split MR No: " + Message);
							VariableHandler.setMaterialRequestNo(Message);
							VariableHandler.setMRNumber(Message);
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to enter Request Quantity In Pending MR Creation
	 * 
	 * @author Sakthi-GWL
	 * @param ProdNumber
	 * @param Quantity
	 */
	public void enterRequestQuantityInPendingMRCreation(String ProdNumber, String Quantity) {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingMRCreateIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(CompletionProductionOrderLocators.PendingMRCreateIframe, ElementType.Xpath);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingMRQuantityInut(ProdNumber),
					ElementType.Xpath);
			if (flag) {
				commonFunc.verifyAddTableSpecs(CompletionProductionOrderLocators.PendingMRQuantityInut(ProdNumber),
						CompletionProductionOrderLocators.PendingMRQuantityInut(ProdNumber), Quantity);
				logs.logging("INFO", "Request Quantity Entered: " + Quantity);
			}
		}
		webDB.switchToDefaultContent();
		assertTrue(flag);
	}

	/**
	 * This method used to click Process Details and Select Process in Pending MR
	 * 
	 * @author Sakthi-GWL
	 * @param ProdNo
	 * @param ProcessName
	 */
	public void clickProcessDetailsAndSelectProcess(String ProdNo, String ProcessName) {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingMRCreateIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(CompletionProductionOrderLocators.PendingMRCreateIframe, ElementType.Xpath);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingMRProcessDetailIcon(ProdNo),
					ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(CompletionProductionOrderLocators.PendingMRProcessDetailIcon(ProdNo),
						ElementType.Xpath);
				webDB.clickAnElement(CompletionProductionOrderLocators.PendingMRProcessDetailIcon(ProdNo),
						ElementType.Xpath);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingMRProcessDetailsIframe,
						ElementType.Xpath);
				if (flag) {
					webDB.switchToFrame(CompletionProductionOrderLocators.PendingMRProcessDetailsIframe,
							ElementType.Xpath);
					flag = webDB.waitForElement(
							CompletionProductionOrderLocators.PendingMRProcessDetailSelectIcon(ProcessName),
							ElementType.Xpath);
					if (flag) {
						webDB.moveToElement(
								CompletionProductionOrderLocators.PendingMRProcessDetailSelectIcon(ProcessName),
								ElementType.Xpath);
						flag = webDB.clickAnElement(
								CompletionProductionOrderLocators.PendingMRProcessDetailSelectIcon(ProcessName),
								ElementType.Xpath);
						webDB.pressEscapeKey();
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		assertTrue(flag);
	}

	/**
	 * This method used to click Process Details and Select Process in Pending MR
	 * 
	 * @author Sakthi-GWL
	 * @param ProdNo
	 * @param ProcessName
	 */
	public void clickProcessDetailsAndSelectAllProcess(String ProdNo) {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingMRCreateIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(CompletionProductionOrderLocators.PendingMRCreateIframe, ElementType.Xpath);
			logs.logging("INFO", "Production Number Entered: " + ProdNo);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingMRProcessDetailIcon(ProdNo),
					ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(CompletionProductionOrderLocators.PendingMRProcessDetailIcon(ProdNo),
						ElementType.Xpath);
				webDB.clickAnElement(CompletionProductionOrderLocators.PendingMRProcessDetailIcon(ProdNo),
						ElementType.Xpath);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingMRProcessDetailsIframe,
						ElementType.Xpath);
				if (flag) {
					webDB.switchToFrame(CompletionProductionOrderLocators.PendingMRProcessDetailsIframe,
							ElementType.Xpath);
					List<WebElement> elements = webDB.getListOfElements(
							CompletionProductionOrderLocators.PendingMRProcessDetailSelectAllIcon, ElementType.Xpath);
					System.out.println("Size: " + elements.size());
					for (WebElement webElement : elements) {
						commonFunc.checkRefreshCellsDisappear();
						if (!webElement.isSelected()) {
							Actions action = new Actions(WebDriverBase.getDriver());
							action.moveToElement(webElement).click().build().perform();
						}
					}
					webDB.pressEscapeKey();
					webDB.switchToDefaultContent();
					flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingMRCreateIframe,
							ElementType.Xpath);
					if (flag) {
						webDB.switchToFrame(CompletionProductionOrderLocators.PendingMRCreateIframe, ElementType.Xpath);
						flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingMRFindSearchBtn,
								ElementType.Xpath);
						commonFunc.expandFindTable();
						commonFunc.checkRefreshCellsDisappear();
					}
				}
			}
		}
		webDB.switchToDefaultContent();
		assertTrue(flag);
	}

	/**
	 * This method used to validate the Lines Details with Raw material in
	 * Completion Process
	 * 
	 * @author Sakthi-GWL
	 * @param FileName
	 * @param SheetName
	 * @param StartIndex
	 */
	public void verifyLinesWithRMValidationInCompletionProcessWithDB(String FileName, String SheetName,
			int StartIndex) {
		logs.logging("INFO", "****Completion Production Order DB Validation - Lines & RM Details****");
		String SeqNo, OprnNo, Unit, RMName, UOM, UnitCost, ReceiverWH, ItemCost, RqrdQnty, TotalConsQnty, AcceptQnty,
				ProcessId, ProdOrderNo, AllocQnty = null;
		String[][] TotalData = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		TotalData = excelUtils.readAllDataInExcel(Filepath, SheetName);
		a: for (int i = StartIndex; i < TotalData.length; i++) {
			SeqNo = TotalData[i][1];
			OprnNo = TotalData[i][0];
			Unit = TotalData[2][1];
			ProcessId = TotalData[i][2];
			ReceiverWH = TotalData[i][7];
			RMName = TotalData[i][4];
			UOM = TotalData[i][5];
			UnitCost = TotalData[i][11];
			ItemCost = TotalData[i][12];
			AllocQnty = TotalData[i][13];
			RqrdQnty = TotalData[i][8];
			TotalConsQnty = TotalData[i][9];
			AcceptQnty = TotalData[i][10];
			ProdOrderNo = VariableHandler.getProductionOrderNumber();
			if (OprnNo == null || OprnNo.equals("") || OprnNo.isBlank() || OprnNo.isEmpty()
					|| OprnNo.equalsIgnoreCase("Completion") || OprnNo.equalsIgnoreCase("SFG")) {
				break a;
			}
			if (Integer.parseInt(SeqNo) > 7) {
				SeqNo = String.valueOf(((Integer.parseInt(SeqNo) - 1) % 7) + 1);
			}
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateCompProductionOrderLinesDetails(SeqNo,
					OprnNo, commonFunc.getDataFromTestDataSheet("CompProcessLinesUnit")), Unit);
			logs.logging("INFO", "Verified Unit and DB Value Matched: " + Unit);
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateCompProductionOrderLinesDetails(SeqNo,
					OprnNo, commonFunc.getDataFromTestDataSheet("CompProcessLinesRcptWH")), ReceiverWH);
			logs.logging("INFO", "Verified ReceiverWH and DB Value Matched: " + ReceiverWH);
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateCompProductionOrderLinesDetails(SeqNo,
					OprnNo, commonFunc.getDataFromTestDataSheet("CompProcessLinesRMName")), RMName);
			logs.logging("INFO", "Verified RMName and DB Value Matched: " + RMName);
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateCompProductionOrderLinesDetails(SeqNo,
					OprnNo, commonFunc.getDataFromTestDataSheet("CompProcessLinesRMUom")), UOM);
			logs.logging("INFO", "Verified RM UOM and DB Value Matched: " + UOM);
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateCompProductionOrderLinesDetails(SeqNo,
					OprnNo, commonFunc.getDataFromTestDataSheet("CompProcessLinesProcessId")), ProcessId);
			logs.logging("INFO", "Verified Process and DB Value Matched: " + ProcessId);
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateCompProductionOrderLinesDetails(SeqNo,
					OprnNo, commonFunc.getDataFromTestDataSheet("CompProcessLinesRqrdQty")), RqrdQnty);
			logs.logging("INFO", "Verified RqrdQnty and DB Value Matched: " + RqrdQnty);
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateCompProductionOrderLinesDetails(SeqNo,
					OprnNo, commonFunc.getDataFromTestDataSheet("CompProcessLinesConsQty")), TotalConsQnty);
			logs.logging("INFO", "Verified TotalConsQnty and DB Value Matched: " + TotalConsQnty);
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateCompProductionOrderLinesDetails(SeqNo,
					OprnNo, commonFunc.getDataFromTestDataSheet("CompProcessLinesAllocQty")), AllocQnty);
			logs.logging("INFO", "Verified AllocQnty and DB Value Matched: " + AllocQnty);
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateCompProductionOrderLinesDetails(SeqNo,
					OprnNo, commonFunc.getDataFromTestDataSheet("CompProcessLinesAcptQty")), AcceptQnty);
			logs.logging("INFO", "Verified Accept Qnty and DB Value Matched: " + AcceptQnty);
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateCompProductionOrderLinesDetails(SeqNo,
					OprnNo, commonFunc.getDataFromTestDataSheet("CompProcessLinesUnitCost")), UnitCost);
			logs.logging("INFO", "Verified Unit Cost and DB Value Matched: " + UnitCost);
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateCompProductionOrderLinesDetails(SeqNo,
					OprnNo, commonFunc.getDataFromTestDataSheet("CompProcessLinesItemCost")), ItemCost);
			logs.logging("INFO", "Verified Item Cost and DB Value Matched: " + ItemCost);
			commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateCompProductionOrderLinesDetails(SeqNo,
					OprnNo, commonFunc.getDataFromTestDataSheet("CompProcessLinesProdOrderNo")), ProdOrderNo);
			logs.logging("INFO", "Verified Production Order No and DB Value Matched: " + ProdOrderNo);
		}
	}

	/**
	 * This method used to validate the Header and Lines Details in
	 * CompletionProcess
	 * 
	 * @author Sakthi-GWL
	 * @param FileName
	 * @param SheetName
	 * @param StartIndex
	 */
	public void verifyHeaderAndLinesValidationInCompletionProcessWithDB(String FileName, String SheetName,
			int StartIndex) {
		logs.logging("INFO", "****Completion Production Order DB Validation - Headers After Post****");
		logs.logging("INFO", "Start Index: " + StartIndex);
		String Shift, Process, Unit, DMCost, UnitCost, CompQty, AccptQty, ProdOrderNo, ProdId, UOM, RcptWH,
				TargetSFCode = null;
		String[][] TotalData = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		TotalData = excelUtils.readAllDataInExcel(Filepath, SheetName);
		a: for (int i = StartIndex; i < TotalData.length; i++) {
			String SeqNo = TotalData[i][0];
			Unit = TotalData[2][1];
			Shift = TotalData[2][6];
			Process = TotalData[i][2];
			UOM = TotalData[2][0];
			DMCost = TotalData[i][12];
			RcptWH = TotalData[i][7];
			CompQty = TotalData[i][8];
			AccptQty = TotalData[i][9];
			ProdId = TotalData[2][5];
			TargetSFCode = TotalData[i][13];
			UnitCost = TotalData[i][11];
			ProdOrderNo = VariableHandler.getProductionOrderNumber();
			if (SeqNo.equalsIgnoreCase("Completion")) {
				commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateCompProductionOrderHeaderDetails(
						commonFunc.getDataFromTestDataSheet("CompProcessHeaderUnit")), Unit);
				logs.logging("INFO", "Verified Header Unit and DB Value Matched: " + Unit);
				commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateCompProductionOrderHeaderDetails(
						commonFunc.getDataFromTestDataSheet("CompProcessHeaderShift")), Shift);
				logs.logging("INFO", "Verified Header Shift and DB Value Matched: " + Shift);
				commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateCompProductionOrderHeaderDetails(
						commonFunc.getDataFromTestDataSheet("CompProcessHeaderProcessId")), Process);
				logs.logging("INFO", "Verified Header Target Process and DB Value Matched: " + Process);
				commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateCompProductionOrderHeaderDetails(
						commonFunc.getDataFromTestDataSheet("CompProcessHeaderFGUom")), UOM);
				logs.logging("INFO", "Verified Header FG UOM and DB Value Matched: " + UOM);
//		commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateCompProductionOrderHeaderDetails(
//				commonFunc.getDataFromTestDataSheet("TargetSFCode")), TargetSFCode);
//		logs.logging("INFO", "Verified TargetSFCode and DB Value Matched: " + TargetSFCode);
				commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateCompProductionOrderHeaderDetails(
						commonFunc.getDataFromTestDataSheet("CompProcessHeaderUnitCost")), UnitCost);
				logs.logging("INFO", "Verified Process - (Unit Cost) and DB Value Matched: " + UnitCost);
				commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateCompProductionOrderHeaderDetails(
						commonFunc.getDataFromTestDataSheet("CompProcessHeaderDMCost")), DMCost);
				logs.logging("INFO", "Verified FG Item UOM and DB Value Matched: " + DMCost);
				commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateCompProductionOrderHeaderDetails(
						commonFunc.getDataFromTestDataSheet("CompProcessHeaderRcptWarehouse")), RcptWH);
				logs.logging("INFO", "Verified Receipt Warehouse and DB Value Matched: " + RcptWH);
				System.out.println("I value " + i + " completionqty " + CompQty);
				commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateCompProductionOrderHeaderDetails(
						commonFunc.getDataFromTestDataSheet("CompProcessHeaderCompQty")), CompQty);
				logs.logging("INFO", "Verified Process Completion Quantity and DB Value Matched: " + CompQty);
				commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateCompProductionOrderHeaderDetails(
						commonFunc.getDataFromTestDataSheet("CompProcessHeaderAccptQty")), AccptQty);
				logs.logging("INFO", "Verified Process Accept Quantity and DB Value Matched: " + AccptQty);
				commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateCompProductionOrderHeaderDetails(
						commonFunc.getDataFromTestDataSheet("CompProcessHeaderFGName")), ProdId);
				logs.logging("INFO", "Verified FG Item Name and DB Value Matched: " + ProdId);
				commonFunc.verifyAssertionBothValues(dbCommonFunctions.validateCompProductionOrderHeaderDetails(
						commonFunc.getDataFromTestDataSheet("CompProcessHeaderProdNo")), ProdOrderNo);
				logs.logging("INFO", "Verified Header Production Order and DB Value Matched: " + ProdOrderNo);
				break a;
			}
		}
	}

	/**
	 * This method used to validate stock transaction with Completion number
	 * 
	 * @author Sakthi-GWL
	 * @param FileName
	 * @param SheetName
	 */
	public void verifyStockTransactionValidationInCompletionForRawMaterials(String FileName, String SheetName,
			int StartIndex) {
		logs.logging("INFO", "****Completion Production Order Stock Transaction DB Validation****");
		logs.logging("INFO", "Start Index: " + StartIndex);
		String[][] TotalData = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		TotalData = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = StartIndex; i <= (StartIndex + 6); i++) {
			String QOHValue = TotalData[i][16];
			String RMItem = TotalData[i][4];
			String WH = TotalData[i][7];
			String DBQOHValve = dbCommonFunctions.validateStockTransactionWithRMInCompletion(RMItem,
					commonFunc.getDataFromTestDataSheet("QOHValue"),
					commonFunc.getDataFromTestDataSheet("TransQuantityHeader"));
			commonFunc.verifyAssertionBothValues(DBQOHValve, QOHValue);
			logs.logging("INFO", "Verified QOH Value in Stock Transaction and DB Value Matched: " + QOHValue);
			String DBStoreValve = dbCommonFunctions.validateStockTransactionWithRMInCompletion(RMItem,
					commonFunc.getDataFromTestDataSheet("QOHValue"),
					commonFunc.getDataFromTestDataSheet("StockTransactionStoreId"));
			commonFunc.verifyAssertionBothValues(DBStoreValve, WH);
			logs.logging("INFO", "Verified Store Id in Stock Transaction and DB Value Matched: " + WH);
			String DBRMName = dbCommonFunctions.validateStockTransactionWithRMInCompletion(RMItem,
					commonFunc.getDataFromTestDataSheet("QOHValue"),
					commonFunc.getDataFromTestDataSheet("StockTransactionProdId"));
			commonFunc.verifyAssertionBothValues(DBRMName, RMItem);
			logs.logging("INFO", "Verified Prod Id in Stock Transaction and DB Value Matched: " + RMItem);
		}
	}

	/**
	 * This method used to fill consumptions line details in production completion
	 *
	 * @author Deepan-GWL
	 */
	public void fillActuaConsumptionAsExcessInProductionCompletion() {
		webDB.waitForElement(CompletionProductionOrderLocators.ProdCompLineActualConsumptionQtyData, ElementType.Xpath);
		List<WebElement> RowElement = webDB
				.getListOfElements(CompletionProductionOrderLocators.ActualConsTableDataInLines, ElementType.Xpath);
		commonFunc.checkRefreshCellsDisappear();
		for (int i = 0; i < RowElement.size(); i++) {
			WebElement element = RowElement.get(i);
			int value = Integer.parseInt(element.getText()) + 10;
			System.out.println(value);
			try {
				Actions action = new Actions(WebDriverBase.getDriver());
				action.scrollToElement(element).moveToElement(element).doubleClick(element).build().perform();
			} catch (Exception e) {

			}
			flag = webDB.waitForElement(CompletionProductionOrderLocators.ProdCompLineActualConsumptionQtyInput,
					ElementType.Xpath);
			if (flag) {
				webDB.clearText(CompletionProductionOrderLocators.ProdCompLineActualConsumptionQtyInput,
						ElementType.Xpath);
				flag = webDB.sendTextToAnElement(
						CompletionProductionOrderLocators.ProdCompLineActualConsumptionQtyInput, String.valueOf(value),
						ElementType.Xpath);
			}
			commonFunc.checkRefreshCellsDisappear();
			if (i == 4) {
				clickSaveIconConsumptionInProdCompletion();
				webDB.moveToElement(CompletionProductionOrderLocators.ActualConsTableNextIconPagination,
						ElementType.Xpath);
				webDB.clickAnElement(CompletionProductionOrderLocators.ActualConsTableNextIconPagination,
						ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.checkRefreshCellsDisappear();
				RowElement = webDB.getListOfElements(CompletionProductionOrderLocators.ActualConsTableDataInLines,
						ElementType.Xpath);
				i = -1;
			}
		}
		clickSaveIconConsumptionInProdCompletion();
		assertTrue(flag);
	}

	/**
	 * This Method used to click Add Icon in Consumption Lines Production Completion
	 *
	 * @author Deepan-GWL
	 */
	public void clickAddIconConsumptionInProdCompletion() {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.ProdCompConsumptionAddIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CompletionProductionOrderLocators.ProdCompConsumptionAddIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.ProdCompLineConsumptionWHData,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Icon Clicked in Consumption Lines");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Save Icon in Consumption Lines Production
	 * Completion
	 *
	 * @author Deepan-GWL
	 */
	public void clickSaveIconConsumptionInProdCompletion() {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.ProdCompConsumptionSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CompletionProductionOrderLocators.ProdCompConsumptionSaveIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				commonFunc.checkSuccessToastMessageDisappear();
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				logs.logging("INFO", "Save Icon Clicked in Consumption Lines");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to fill consumptions line details in production completion
	 *
	 * @author Deepan-GWL
	 * @param WH
	 * @param Item
	 * @param UOM
	 * @param ReqrdQty
	 * @param ActualConsQty
	 */
	public void fillConsumptionLinesInProductionCompletion(String WH, String Item, String UOM, String ReqrdQty,
			String ActualConsQty) {
		commonFunc.verifyAddTableLovValue(CompletionProductionOrderLocators.ProdCompLineConsumptionWHData,
				CompletionProductionOrderLocators.ProdCompLineConsumptionWHLovBtn, WH);
		logs.logging("INFO", "Cons.Warehouse selected in Consumption: " + WH);
		commonFunc.verifyAddTableLovValue(CompletionProductionOrderLocators.ProdCompLineItemsData,
				CompletionProductionOrderLocators.ProdCompLineItemsLovBtn, Item);
		logs.logging("INFO", "Item selected in Consumption: " + Item);
		commonFunc.verifyAddTableLovValue(CompletionProductionOrderLocators.ProdCompLineUOMData,
				CompletionProductionOrderLocators.ProdCompLineUOMLovBtn, UOM);
		logs.logging("INFO", "UOM selected in Consumption: " + UOM);
//		commonFunc.verifyAddTableSpecs(CompletionProductionOrderLocators.ProdCompLineRequiredQtyData,
//				CompletionProductionOrderLocators.ProdCompLineRequiredQtyInput, ReqrdQty);
//		logs.logging("INFO", "Required Quantity Entered in Consumption: " + ReqrdQty);
		commonFunc.verifyAddTableSpecs(CompletionProductionOrderLocators.ProdCompLineActualConsumptionQtyData,
				CompletionProductionOrderLocators.ProdCompLineActualConsumptionQtyData, ActualConsQty);
		logs.logging("INFO", "Actual ConsumpFtion Quantity Entered in Consumption: " + ActualConsQty);
	}
}
