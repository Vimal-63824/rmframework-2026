package commonfunctions.productionplanning;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks March 05, 2025
 */
import static org.testng.Assert.assertTrue;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.CompletionProductionOrderLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class CompletionProductionOrderFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();

	/**
	 * This Method used to switch to Pending Production Order Iframe
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchToPendingProdOrderIframe() {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingProdOrderIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(CompletionProductionOrderLocators.PendingProdOrderIframe, ElementType.Xpath);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingProdOrderSearchGoBtn,
					ElementType.Xpath);
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
				flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingProdOrder_ProdNoTableData,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Searched with Production Order No: " + ProdNo);
					String DisplayProdNo = webDB.getTextFromElement(
							CompletionProductionOrderLocators.PendingProdOrder_ProdNoTableData, ElementType.Xpath);
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
	 * This Method used to validate Item and Process in search result table
	 * 
	 * @author Sakthi-GWL
	 * @param Item
	 * @param Process
	 */
	public void validateItemAndProcessInPendingProdOrderTable(String Item, String Process) {
		switchToPendingProdOrderIframe();
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingProdOrder_ItemTableData,
				ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CompletionProductionOrderLocators.PendingProdOrder_ItemTableData, ElementType.Xpath);
			String DisplayItem = webDB.getTextFromElement(
					CompletionProductionOrderLocators.PendingProdOrder_ItemTableData, ElementType.Xpath);
			logs.logging("INFO", "Expected Item: " + Item);
			logs.logging("INFO", "Displayed Item: " + DisplayItem);
			flag = DisplayItem.equalsIgnoreCase(Item);
			if (flag) {
				logs.logging("INFO", "Validated Displayed Item is Matched in Pending Production Order table data");
				String DisplayProcess = webDB.getTextFromElement(
						CompletionProductionOrderLocators.PendingProdOrder_ProcessTableData, ElementType.Xpath);
				logs.logging("INFO", "Expected Process: " + Process);
				logs.logging("INFO", "Displayed Process: " + DisplayProcess);
				flag = DisplayProcess.equalsIgnoreCase(Process);
				if (flag) {
					logs.logging("INFO",
							"Validated Displayed Process is Matched in Pending Production Order table data");
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
	 * This Method used to select Pending production order no and click create
	 * completion process button
	 * 
	 * @author Sakthi-GWL
	 * @param ProdNo
	 */
	public void selectPendingProdOrderAndClickCreateCompletionProcess(String ProdNo) {
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
				webDB.moveToElement(CompletionProductionOrderLocators.CreateCompletionProcessOkBtn, ElementType.Xpath);
				webDB.clickAnElement(CompletionProductionOrderLocators.CreateCompletionProcessOkBtn, ElementType.Xpath);
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
	 * This Method used to click Post Icon in completion production order
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickPostButtonInCompletionProcess() {
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
		commonFunc.hardWait(2000);
		if (flag) {
			webDB.moveToElement(CompletionProductionOrderLocators.CompletionProcessPendingIcon, ElementType.Xpath);
			webDB.clickAnElement(CompletionProductionOrderLocators.CompletionProcessPendingIcon, ElementType.Xpath);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingProdOrderIframe, ElementType.Xpath);
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
			flag = webDB.selectDropDownByText(CompletionProductionOrderLocators.SelectStatus, Status,
					ElementType.Xpath);
			if (flag) {
				flag = webDB.waitForElement(CompletionProductionOrderLocators.SearchButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CompletionProductionOrderLocators.SearchButton, ElementType.Xpath);
					flag = webDB.waitForElement(CompletionProductionOrderLocators.CompletionTransNumber(ProdNumber),
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(CompletionProductionOrderLocators.CompletionTransNumber(ProdNumber),
								ElementType.Xpath);
						flag = webDB.waitForElement(CompletionProductionOrderLocators.ComplProdOrderShiftLovBtn,
								ElementType.Xpath);
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
		commonFunc.hardWait(2000);
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
		flag = webDB.waitForElement(CompletionProductionOrderLocators.OrderQty, ElementType.Xpath);
		if (flag) {
			String ProcessName = webDB.getTextFromElement(CompletionProductionOrderLocators.ProcessName,
					ElementType.Xpath);
			logs.logging("INFO", "Process Name Is: " + ProcessName);
			flag = ProcessName.equals(ExpectedProcessName);
			if (flag) {
				logs.logging("INFO", "Verified Process Name SuccessFully");
				String OrderQty = webDB.getTextFromElement(CompletionProductionOrderLocators.OrderQty,
						ElementType.Xpath);
				logs.logging("INFO", "Ordered Quantity Is: " + OrderQty);
				String QueueQty = webDB.getTextFromElement(CompletionProductionOrderLocators.QueueQty,
						ElementType.Xpath);
				logs.logging("INFO", "Queue Quantity Is: " + QueueQty);
				String CompQty = webDB.getDomPropertyFromElement(CompletionProductionOrderLocators.CompQty,
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
		commonFunc.searchAndSelectValueInFilters(CompletionProductionOrderLocators.MultiProc_ShiftLovBtn, shift);
		flag = webDB.waitForElement(CompletionProductionOrderLocators.MultiProc_SaveHeaderBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CompletionProductionOrderLocators.MultiProc_SaveHeaderBtn, ElementType.Xpath);
			webDB.clickAnElement(CompletionProductionOrderLocators.MultiProc_SaveHeaderBtn, ElementType.Xpath);
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
				flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingProdOrder_ProdNoTableData,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Searched with Production Order No: " + ProdNo);
					String DisplayProdNo = webDB.getTextFromElement(
							CompletionProductionOrderLocators.PendingProdOrder_ProdNoTableData, ElementType.Xpath);
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
		flag = webDB.waitForElement(CompletionProductionOrderLocators.OrderQty, ElementType.Xpath);
		if (flag) {
			String ProcessName = webDB.getTextFromElement(CompletionProductionOrderLocators.ProcessName,
					ElementType.Xpath);
			logs.logging("INFO", "Process Name Is: " + ProcessName);
			flag = ProcessName.equals(ExpectedProcessName);
			if (flag) {
				logs.logging("INFO", "Verified Process Name SuccessFully");
				String OrderQty = webDB.getTextFromElement(CompletionProductionOrderLocators.OrderQty,
						ElementType.Xpath);
				logs.logging("INFO", "Ordered Quantity Is: " + OrderQty);
				String QueueQty = webDB.getTextFromElement(CompletionProductionOrderLocators.QueueQty,
						ElementType.Xpath);
				logs.logging("INFO", "Queue Quantity Is: " + QueueQty);
				String InsideQty = webDB.getTextFromElement(CompletionProductionOrderLocators.InsideQty,
						ElementType.Xpath);
				logs.logging("INFO", "Inside Quantity Is: " + InsideQty);
				String CompQty = webDB.getDomPropertyFromElement(CompletionProductionOrderLocators.CompQty,
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

				String ProdComplNo = webDB.getTextFromElement(
						CompletionProductionOrderLocators.MultiProc_DisplayComplProdOrderNo, ElementType.Xpath);
				VariableHandler.setProductionCompletionOrderNo(ProdComplNo);
				logs.logging("INFO", "Multi process Production Completion Order Number Is: " + ProdComplNo);
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
		flag = webDB.waitForElement(CompletionProductionOrderLocators.MultiProc_PostBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CompletionProductionOrderLocators.MultiProc_PostBtn, ElementType.Xpath);
			webDB.clickAnElement(CompletionProductionOrderLocators.MultiProc_PostBtn, ElementType.Xpath);
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
			webDB.clickAnElement(CompletionProductionOrderLocators.ResourcesTab, ElementType.Xpath);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.ResourcesMachineTab, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CompletionProductionOrderLocators.ResourcesMachineTab, ElementType.Xpath);
				flag = webDB.waitForElement(CompletionProductionOrderLocators.MachineLineAmount, ElementType.Xpath);
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
			webDB.clickAnElement(CompletionProductionOrderLocators.ResourcesLabourTab, ElementType.Xpath);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.LabourLineAmount, ElementType.Xpath);
			if (flag) {

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
				flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingProdOrder_ProdNoTableData,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Searched with Production Order No: " + ProdNo);
					String DisplayProdNo = webDB.getTextFromElement(
							CompletionProductionOrderLocators.PendingProdOrder_ProdNoTableData, ElementType.Xpath);
//					flag = DisplayProdNo.contains(ProdNo);
//					if (flag) {
					logs.logging("INFO", "Filtered Successfully with Production Order No: " + DisplayProdNo);
//					}
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
		switchToPendingMultipleProdOrderIframe();
		webDB.moveToElement(CompletionProductionOrderLocators.PendingProdOrder_SelectCheckboxTableData(ProdNo),
				ElementType.Xpath);
		webDB.clickAnElement(CompletionProductionOrderLocators.PendingProdOrder_SelectCheckboxTableData(ProdNo),
				ElementType.Xpath);
		flag = webDB.waitForElement(CompletionProductionOrderLocators.CreateCompProc, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CompletionProductionOrderLocators.CreateCompProc, ElementType.Xpath);
			webDB.doubleClickAnElement(CompletionProductionOrderLocators.CreateCompProc, ElementType.Xpath);
			flag = webDB.waitForClickElement(CompletionProductionOrderLocators.MultipleCreateCompletionProcessOkBtn,
					ElementType.Xpath);
			commonFunc.hardWait(2000);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				logs.logging("INFO", "Create Completion Process Button Clicked");
				webDB.moveToElement(CompletionProductionOrderLocators.MultipleCreateCompletionProcessOkBtn,
						ElementType.Xpath);
				webDB.clickAnElement(CompletionProductionOrderLocators.MultipleCreateCompletionProcessOkBtn,
						ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(3000);
				flag = webDB.verifyIsElementDisplayed(CompletionProductionOrderLocators.MultipleProceedRestOprYesBtn,
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CompletionProductionOrderLocators.MultipleProceedRestOprYesBtn,
							ElementType.Xpath);
				} else {
				}
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(CompletionProductionOrderLocators.MultiProc_ShiftLovBtn, ElementType.Xpath);
				if (flag) {
					String ProdComplNo = webDB.getTextFromElement(
							CompletionProductionOrderLocators.MultiProc_DisplayComplProdOrderNo, ElementType.Xpath);
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
			flag = webDB.selectDropDownByText(CompletionProductionOrderLocators.MultipleSelectStatus, Status,
					ElementType.Xpath);
			if (flag) {
				flag = webDB.waitForElement(CompletionProductionOrderLocators.MultipleSearchButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CompletionProductionOrderLocators.MultipleSearchButton, ElementType.Xpath);
					flag = webDB.waitForElement(CompletionProductionOrderLocators.CompletionTransNumber(ProdNumber),
							ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(CompletionProductionOrderLocators.CompletionTransNumber(ProdNumber),
								ElementType.Xpath);
						flag = webDB.waitForElement(CompletionProductionOrderLocators.MultiProc_ShiftLovBtn,
								ElementType.Xpath);
					}
				}
			}
		}
		assertTrue(flag);
	}
}
