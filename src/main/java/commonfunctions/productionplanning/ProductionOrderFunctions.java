package commonfunctions.productionplanning;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 20, 2025
 */
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.payroll.CommonPayrollFunctions;
import commonfunctions.qualitycontrol.InspectionDocumentFunctions;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonfunctions.supplychainmanagement.DeliveryChallanFunctions;
import commonfunctions.supplychainmanagement.GrnScoPageFunctions;
import commonfunctions.supplychainmanagement.MaterialIssuanceVoucherFunctions;
import commonfunctions.supplychainmanagement.MaterialReceiptVoucherFunctions;
import commonfunctions.supplychainmanagement.MaterialRequestFunctions;
import commonfunctions.supplychainmanagement.PricelistPageFunctions;
import commonfunctions.supplychainmanagement.SalesOrderFunctions;
import commonfunctions.supplychainmanagement.StockTransactionPageFunctions;
import commonfunctions.supplychainmanagement.SubcontractOrderFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.productionplanning.CompletionProductionOrderLocators;
import commonlocators.productionplanning.ProductionOrderLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class ProductionOrderFunctions {
	private boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static CommonPayrollFunctions commonPayrollFunc = new CommonPayrollFunctions();
	private static ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	private static MaterialRequestFunctions materialRequestFunc = new MaterialRequestFunctions();
	private static MaterialIssuanceVoucherFunctions materialIssuanceVoucherFunc = new MaterialIssuanceVoucherFunctions();
	private static MaterialReceiptVoucherFunctions materialReceiptFunctions = new MaterialReceiptVoucherFunctions();
	private static CompletionProductionOrderFunctions completionProductionOrderFunc = new CompletionProductionOrderFunctions();
	private static SubcontractOrderFunctions subContractOrderFunc = new SubcontractOrderFunctions();
	private static GrnScoPageFunctions grnScoPageFunc = new GrnScoPageFunctions();
	private static DeliveryChallanFunctions deliveryChallanFunc = new DeliveryChallanFunctions();
	private static MRPFunctions mrpFunc = new MRPFunctions();
	private static SalesOrderFunctions saleOrderFunc = new SalesOrderFunctions();
	private static StockTransactionPageFunctions stockTransactionPageFunc = new StockTransactionPageFunctions();
	private static DBCommonFunctions dbFunction = new DBCommonFunctions();
	private static InspectionDocumentFunctions qcFunc = new InspectionDocumentFunctions();
	private static BillOfMaterialsFunctions bomFunc = new BillOfMaterialsFunctions();
	private static PricelistPageFunctions priceListFunc = new PricelistPageFunctions();

	/**
	 * This method used to click production completion in Inside
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickPendingProdCompletion() {
		flag = webDB.waitForElement(CompletionProductionOrderLocators.Pending_InsideProcCreateBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CompletionProductionOrderLocators.Pending_InsideProcCreateBtn, ElementType.Xpath);
			webDB.clickAnElement(CompletionProductionOrderLocators.Pending_InsideProcCreateBtn, ElementType.Xpath);
			flag = webDB.waitForElement(CompletionProductionOrderLocators.PendingProdOrderIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Production Completion Clicked");
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method used to verify Status of Production Order with MRP Number
	 * 
	 * @author Sakthi-GWL
	 * @param MRPNo
	 * @param ExpectedStatus
	 */
	public void searchAndVerifyStatusOfMRPNo(String MRPNo, String ExpectedStatus) {
		commonFunc.expandFindTable();
		commonFunc.clearFiltersInFindPage();
		commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.FindMRPNoLovBtn, MRPNo);
		flag = webDB.waitForElement(ProductionOrderLocators.FindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionOrderLocators.FindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(ProductionOrderLocators.Find_TableData_ProdNoWithMRPNo(MRPNo),
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button Clicked in Production Order Find Page");
				String ProductionNo = webDB.getTextFromElement(
						ProductionOrderLocators.Find_TableData_ProdNoWithMRPNo(MRPNo), ElementType.Xpath);
				VariableHandler.setProductionOrderNumber(ProductionNo);
				logs.logging("INFO", "Searched with MRP No, Displayed Production Order No: " + ProductionNo);
				String DisplayedStatus = webDB.getTextFromElement(ProductionOrderLocators.Find_TableData_Status(MRPNo),
						ElementType.Xpath);
				logs.logging("INFO", "Displayed Status: " + DisplayedStatus);
				flag = DisplayedStatus.equalsIgnoreCase(ExpectedStatus);
				if (flag) {
					logs.logging("INFO", "Production Order Status Verified");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Production Order with Production Order no
	 * 
	 * @author Sakthi-GWL
	 * @param ProdOrderNo
	 */
	public void searchAndSelectProductionOrderWithDocNo(String ProdOrderNo) {
		commonFunc.expandFindTable();
		commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.FindProdOrderNoLovBtn, ProdOrderNo);
		flag = webDB.waitForElement(ProductionOrderLocators.FindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionOrderLocators.FindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(ProductionOrderLocators.Find_TableData_ProdNo(ProdOrderNo), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button Clicked in Production Order Find Page");
				webDB.clickAnElement(ProductionOrderLocators.Find_TableData_ProdNo(ProdOrderNo), ElementType.Xpath);
				flag = webDB.waitForElement(ProductionOrderLocators.DisplayProdOrderNo, ElementType.Xpath);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to Store process and Warehouses in variable handler in
	 * Production Order
	 * 
	 * @author Sakthi-GWL
	 * 
	 */
	public void storeRoutingProcessAndConsumptionAndRecipientWareHouse() {
		flag = webDB.waitForElement(ProductionOrderLocators.RoutingProcessNameTableDataLists(), ElementType.Xpath);
		if (flag) {
			List<WebElement> processName = WebDriverBase.getDriver()
					.findElements(By.xpath(ProductionOrderLocators.RoutingProcessNameTableDataLists()));
			logs.logging("INFO", "Process Name Size: " + processName.size());
			List<WebElement> consumptionWH = WebDriverBase.getDriver()
					.findElements(By.xpath(ProductionOrderLocators.RoutingConsumptionWHTableDataLists()));
			logs.logging("INFO", "Consumption Warehouse Size: " + consumptionWH.size());
			List<WebElement> recipientWH = WebDriverBase.getDriver()
					.findElements(By.xpath(ProductionOrderLocators.RoutingRecipientWHTableDataLists()));
			logs.logging("INFO", "Recipient Warehouse Size: " + recipientWH.size());
			flag = (processName.size() == consumptionWH.size()) && (processName.size() == recipientWH.size());
			if (flag) {
				logs.logging("INFO", "Verified 'Processes & Consumption WH and Recipient WH' Sizes are Equal..");
				String[][] ProcessAndWarehousesArray = new String[(processName.size())][3];
				try {
					for (int i = 0; i < processName.size(); i++) {
						ProcessAndWarehousesArray[i][0] = processName.get(i).getText();
						ProcessAndWarehousesArray[i][1] = consumptionWH.get(i).getText();
						ProcessAndWarehousesArray[i][2] = recipientWH.get(i).getText();
					}
					// To Verify the String[] store value
					for (String[] row : ProcessAndWarehousesArray) {
						logs.logging("INFO", String.join(",", row));
					}
					VariableHandler.setProcessAndWarehousesBOM(ProcessAndWarehousesArray);
				} catch (Exception e) {
					logs.logging("INFO", "Exception while store Process and Warehouses: " + e);
					flag = false;
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to Store process and Process type in variable handler in
	 * Production Order
	 * 
	 * @author Sakthi-GWL
	 * 
	 */
	public void storeRoutingProcessAndProcessType() {
		flag = webDB.waitForElement(ProductionOrderLocators.RoutingProcessNameTableDataLists(), ElementType.Xpath);
		if (flag) {
			List<WebElement> processName = WebDriverBase.getDriver()
					.findElements(By.xpath(ProductionOrderLocators.RoutingProcessNameTableDataLists()));
			logs.logging("INFO", "Process Name Size: " + processName.size());
			List<WebElement> processType = WebDriverBase.getDriver()
					.findElements(By.xpath(ProductionOrderLocators.RoutingProcessTypeTableDataLists()));
			logs.logging("INFO", "Process Type Size: " + processType.size());
			flag = (processName.size() == processType.size());
			if (flag) {
				logs.logging("INFO", "Verified 'Processes & Process Type' Sizes are Equal..");
				String[][] ProcessAndTypeArray = new String[(processName.size())][3];
				try {
					for (int i = 0; i < processName.size(); i++) {
						ProcessAndTypeArray[i][0] = processName.get(i).getText();
						ProcessAndTypeArray[i][1] = processType.get(i).getText();
					}
					// To Verify the String[] store value
					for (String[] row : ProcessAndTypeArray) {
						logs.logging("INFO", String.join(",", row));
					}
					VariableHandler.setProcessAndProcessType(ProcessAndTypeArray);
				} catch (Exception e) {
					logs.logging("INFO", "Exception while store Process and Process Type: " + e);
					flag = false;
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to navigate Routing Tab in Production Order
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToRoutingSubTab() {
		flag = webDB.waitForElement(ProductionOrderLocators.RoutingLabel, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionOrderLocators.RoutingLabel, ElementType.Xpath);
			flag = webDB.waitForElement(ProductionOrderLocators.RoutingAddBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Routing Sub tab Navigated Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to navigate Material Requirement Tab in Production Order
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToMaterialRequirementSubTab() {
		flag = webDB.waitForElement(ProductionOrderLocators.MaterialRequirementLabel, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionOrderLocators.MaterialRequirementLabel, ElementType.Xpath);
			flag = webDB.waitForElement(ProductionOrderLocators.MaterialReqAddBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Routing Sub tab Navigated Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to navigate Resources Tab in Production Order
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToResourcesSubTab() {
		flag = webDB.waitForElement(ProductionOrderLocators.ResourcesLabel, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionOrderLocators.ResourcesLabel, ElementType.Xpath);
			flag = webDB.waitForElement(ProductionOrderLocators.ResourcesTable, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Resources Sub tab Navigated Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to navigate Exception Tab in Production Order
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToExceptionSubTab() {
		flag = webDB.waitForElement(ProductionOrderLocators.ExceptionLabel, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionOrderLocators.ExceptionLabel, ElementType.Xpath);
			flag = webDB.waitForElement(ProductionOrderLocators.ExceptionTableContainer, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Exception Sub tab Navigated Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to verify Raw Material Items in Material Requirement
	 * 
	 * @author Sakthi-GWL
	 * @param Item
	 */
	public void verifyItemInMaterialRequirement(String[] Item) {
		try {
			flag = webDB.waitForElement(ProductionOrderLocators.MaterialReqRMItemTableDataLists(), ElementType.Xpath);
			if (flag) {
				commonFunc.hardWait(2000);
				List<WebElement> ItemList = WebDriverBase.getDriver()
						.findElements(By.xpath(ProductionOrderLocators.MaterialReqRMItemTableDataLists()));
				flag = ItemList.size() == Item.length;
				if (flag) {
					for (int i = 0; i < Item.length; i++) {
						if (flag) {
							b: for (int j = 0; j < ItemList.size(); j++) {
								String DisplayedText = ItemList.get(j).getText();
								if (Item[i].toString().equalsIgnoreCase(DisplayedText)) {
									flag = true;
									logs.logging("INFO",
											"Item Displayed in Material Requirement: " + Item[i].toString());
									break b;
								} else {
									flag = false;
								}
							}
						} else {
							logs.logging("INFO", "Item Not Displayed in Material Requirement Sub tab");
						}
					}
				}
			}
		} catch (Exception e) {
			flag = false;
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to verify Process in Material Requirement
	 * 
	 * @author Sakthi-GWL
	 * @param Process
	 */
	public void verifyProcessInMaterialRequirement(String[] Process) {
		try {
			flag = webDB.waitForElement(ProductionOrderLocators.MaterialReqProcessTableDataLists(), ElementType.Xpath);
			if (flag) {
				commonFunc.hardWait(2000);
				List<WebElement> ProcesList = WebDriverBase.getDriver()
						.findElements(By.xpath(ProductionOrderLocators.MaterialReqProcessTableDataLists()));
				flag = ProcesList.size() == Process.length;
				if (flag) {
					for (int i = 0; i < Process.length; i++) {
						if (flag) {
							b: for (int j = 0; j < ProcesList.size(); j++) {
								String DisplayedText = ProcesList.get(j).getText();
								if (Process[i].toString().equalsIgnoreCase(DisplayedText)) {
									flag = true;
									logs.logging("INFO",
											"Process Displayed in Material Requirement: " + Process[i].toString());
									break b;
								} else {
									flag = false;
								}
							}
						} else {
							logs.logging("INFO", "Process Not Displayed in Material Requirement Sub tab");
						}
					}
				}
			}
		} catch (Exception e) {
			flag = false;
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to verify Process in Resources tab
	 * 
	 * @author Sakthi-GWL
	 * @param Process
	 */
	public void verifyProcessInResources(String[] Process) {
		try {
			flag = webDB.waitForElement(ProductionOrderLocators.ResourcesProcessTableDataLists(), ElementType.Xpath);
			if (flag) {
				commonFunc.hardWait(2000);
				List<WebElement> ProcesList = WebDriverBase.getDriver()
						.findElements(By.xpath(ProductionOrderLocators.ResourcesProcessTableDataLists()));
				flag = ProcesList.size() == Process.length;
				if (flag) {
					for (int i = 0; i < Process.length; i++) {
						if (flag) {
							b: for (int j = 0; j < ProcesList.size(); j++) {
								String DisplayedText = ProcesList.get(j).getText();
								if (Process[i].toString().equalsIgnoreCase(DisplayedText)) {
									flag = true;
									logs.logging("INFO", "Process Displayed in Resources: " + Process[i].toString());
									break b;
								} else {
									flag = false;
								}
							}
						} else {
							logs.logging("INFO", "Process Not Displayed in Resources Sub tab");
						}
					}
				}
			}
		} catch (Exception e) {
			flag = false;
		}
		assertTrue(flag);
	}

	public void clickHeaderSaveAndPostProdOrder() {
		flag = webDB.waitForElement(ProductionOrderLocators.HeaderSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(ProductionOrderLocators.HeaderSaveIcon, ElementType.Xpath);
			webDB.clickAnElement(ProductionOrderLocators.HeaderSaveIcon, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
//			commonFunc.hardWait(5000);
			flag = webDB.waitForClickElement(ProductionOrderLocators.ProdOrderPostBtn, ElementType.Xpath);
			if (flag) {
				commonFunc.checkSuccessToastMessageDisappear();
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				logs.logging("INFO", "Header Saved Successfully");
				flag = webDB.waitForElement(ProductionOrderLocators.ProdOrderPostBtn, ElementType.Xpath);
				if (flag) {
					commonFunc.checkRefreshCellsDisappear();
					webDB.moveToElement(ProductionOrderLocators.ProdOrderPostBtn, ElementType.Xpath);
					webDB.clickAnElement(ProductionOrderLocators.ProdOrderPostBtn, ElementType.Xpath);
					flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
					if (flag) {
						logs.logging("INFO", "Post Button Clicked Successfully");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to save header in Production Order
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickHeaderSaveInProdOrder() {
		flag = webDB.waitForElement(ProductionOrderLocators.HeaderSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(ProductionOrderLocators.HeaderSaveIcon, ElementType.Xpath);
			webDB.clickAnElement(ProductionOrderLocators.HeaderSaveIcon, ElementType.Xpath);
			flag = webDB.waitForClickElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				commonFunc.checkSuccessToastMessageDisappear();
				logs.logging("INFO", "Header Saved Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to validate No Exceptions in Exception tab
	 * 
	 * @author Sakthi-GWL
	 */
	public void validateNoExceptionsShowsInExceptionTab() {
		flag = webDB.waitForElement(ProductionOrderLocators.ExceptionNoDataFoundContainer, ElementType.Xpath);
		if (flag) {
			List<WebElement> elements = WebDriverBase.getDriver()
					.findElements(By.xpath(ProductionOrderLocators.ExceptionNoDataFoundContainer));
			a: for (WebElement webElement : elements) {
				if (webElement.isDisplayed()) {
					flag = true;
					break a;
				} else {
					flag = false;
				}
			}
			if (flag) {
				logs.logging("INFO", "Verified No Data Found Container Displayed No Exception Occurs");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to validate Raw Material of Process Required Quantity with
	 * BOM Quantity and Order Quantity
	 * 
	 * @author Sakthi-GWL
	 * @param Process
	 * @param RMItem
	 */
	public void validateMaterialReqrdQuantityWithRMItemProcess(String Process, String RMItem) {
		flag = webDB.waitForElement(ProductionOrderLocators.DisplayOrderQuantityInput, ElementType.Xpath);
		if (flag) {
			String OrderQty = webDB.getDomPropertyFromElement(ProductionOrderLocators.DisplayOrderQuantityInput,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			logs.logging("INFO", "Displayed Order Quantity: " + OrderQty);
			BigDecimal OrderQntyDecimal = new BigDecimal(OrderQty);
			flag = webDB.waitForElement(ProductionOrderLocators.MaterialRqrdmntDisplayBOMQty(Process, RMItem),
					ElementType.Xpath);
			if (flag) {
				String BOMQty = webDB.getTextFromElement(
						ProductionOrderLocators.MaterialRqrdmntDisplayBOMQty(Process, RMItem), ElementType.Xpath);
				logs.logging("INFO", "Displayed BOM Quantity in Material Requirement: " + BOMQty);
				BigDecimal BomQntyDecimal = new BigDecimal(BOMQty);
				BigDecimal ExpectRqrdQnty = (BomQntyDecimal.multiply(OrderQntyDecimal));
				logs.logging("INFO",
						"Expected Required Quantity in Material Requirement by Multiply Order and BOM Quantity: "
								+ ExpectRqrdQnty);
				String RqrdQty = webDB.getTextFromElement(
						ProductionOrderLocators.MaterialRqrdmntDisplayRequiredQty(Process, RMItem), ElementType.Xpath);
				logs.logging("INFO", "Displayed Required Quantity in Material Requirement: " + RqrdQty);
				BigDecimal RqrdQntyDecimal = new BigDecimal(RqrdQty);
				flag = ExpectRqrdQnty.equals(RqrdQntyDecimal);
				if (flag) {
					logs.logging("INFO",
							"Verified Material Required Qnty for Process: " + Process + " and Material: " + RMItem);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to validate Scrap Product of Process Receipt Quantity with
	 * Required Quantity and Order Quantity
	 * 
	 * @author Sakthi-GWL
	 * @param Process
	 * @param RMItem
	 */
	public void validateScrapReceiptQuantityWithItemProcess(String Process, String Item) {
		flag = webDB.waitForElement(ProductionOrderLocators.DisplayOrderQuantityInput, ElementType.Xpath);
		if (flag) {
			String OrderQty = webDB.getDomPropertyFromElement(ProductionOrderLocators.DisplayOrderQuantityInput,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			logs.logging("INFO", "Displayed Order Quantity: " + OrderQty);
			BigDecimal OrderQntyDecimal = new BigDecimal(OrderQty);
			flag = webDB.waitForElement(ProductionOrderLocators.ScrapReceiptDisplayRqrdQty(Process, Item),
					ElementType.Xpath);
			if (flag) {
				String RqrdQty = webDB.getTextFromElement(
						ProductionOrderLocators.ScrapReceiptDisplayRqrdQty(Process, Item), ElementType.Xpath);
				logs.logging("INFO", "Displayed Rqrd Quantity in Scrap Product: " + RqrdQty);
				BigDecimal RqrdQntyDecimal = new BigDecimal(RqrdQty);
				BigDecimal ExpectRcptQnty = (RqrdQntyDecimal.multiply(OrderQntyDecimal));
				logs.logging("INFO",
						"Expected Receipt Quantity by Multiply Order and Rqrd Quantity: " + ExpectRcptQnty);
				String RcptQty = webDB.getTextFromElement(
						ProductionOrderLocators.ScrapReceiptDisplayRcptQty(Process, Item), ElementType.Xpath);
				logs.logging("INFO", "Displayed Receipt Quantity in Scrap Product: " + RcptQty);
				BigDecimal RcptQntyDecimal = new BigDecimal(RcptQty);
				flag = ExpectRcptQnty.equals(RcptQntyDecimal);
				if (flag) {
					logs.logging("INFO",
							"Verified Scrap Product Required Qnty for Process: " + Process + " and Material: " + Item);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to switch Create Production Order Iframe
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchToCreateProductionOrderFrame() {
		flag = webDB.waitForElement(ProductionOrderLocators.CreateProdOrderIframe, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(ProductionOrderLocators.CreateProdOrderIframe, ElementType.Xpath);
			flag = webDB.waitForElement(ProductionOrderLocators.CreateProdOrd_LocationLovbtn, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Add Icon in Production Order Creation
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAddIconInCreationProductionOrder() {
		flag = webDB.waitForElement(ProductionOrderLocators.CreateProdOrderAddIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionOrderLocators.CreateProdOrderAddIcon, ElementType.Xpath);
			flag = webDB.waitForElement(ProductionOrderLocators.CreateProdOrderIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Production Order Add Icon Clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Save Icon in Production Order Creation
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickSaveIconInCreationProductionOrder() {
		switchToCreateProductionOrderFrame();
		flag = webDB.waitForElement(ProductionOrderLocators.CreateProdOrd_TickIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionOrderLocators.CreateProdOrd_TickIcon, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			webDB.switchToDefaultContent();
			flag = webDB.waitForClickElement(ProductionOrderLocators.DisplayProdOrderNo, ElementType.Xpath);
			if (flag) {
				commonFunc.checkSuccessToastMessageDisappear();
				logs.logging("INFO", "Production Order Add Icon Clicked Successfully");
				String DisplayProdOrdNo = webDB.getTextFromElement(ProductionOrderLocators.DisplayProdOrderNo,
						ElementType.Xpath);
				VariableHandler.setProductionOrderNumber(DisplayProdOrdNo);
				logs.logging("INFO", "Displayed Production Order Number: " + DisplayProdOrdNo);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to fill form details in Production Order Creation
	 * 
	 * @author Sakthi-GWL
	 * @param Loc
	 * @param OrdType
	 * @param Pfx
	 * @param BOMType
	 * @param Item
	 * @param OrdQnty
	 */
	public void createProdOrderAndFillFormDetails(String Loc, String OrdType, String Pfx, String BOMType, String Item,
			String OrdQnty) {
		switchToCreateProductionOrderFrame();
		commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.CreateProdOrd_LocationLovbtn, Loc);
		logs.logging("INFO", "Location Selected in Prod Order: " + Loc);
		switchToCreateProductionOrderFrame();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.CreateProdOrd_OrderTypeLovbtn, OrdType);
		logs.logging("INFO", "Order Type Selected in Prod Order: " + OrdType);
		switchToCreateProductionOrderFrame();
		commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.CreateProdOrd_PrefixLovbtn, Pfx);
		logs.logging("INFO", "Prefix Selected in Prod Order: " + Pfx);
		commonFunc.checkRefreshCellsDisappear();
		switchToCreateProductionOrderFrame();
		if (!OrdType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ProdOrderNonStandard"))) {
			flag = webDB.waitForElement(ProductionOrderLocators.CreateProdOrd_BOMTypeSelect, ElementType.Xpath);
			if (flag) {
				flag = webDB.selectDropDownByText(ProductionOrderLocators.CreateProdOrd_BOMTypeSelect, BOMType,
						ElementType.Xpath);
			}
		}
		logs.logging("INFO", "BOM Type Selected in Prod Order: " + BOMType);
		commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.CreateProdOrd_ItemLovbtn, Item);
		logs.logging("INFO", "Item Selected in Prod Order: " + Item);
		switchToCreateProductionOrderFrame();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.verifyAddTableSpecs(ProductionOrderLocators.CreateProdOrd_OrderQntyInput,
				ProductionOrderLocators.CreateProdOrd_OrderQntyInput, OrdQnty);
		logs.logging("INFO", "Order Quantity Entered in Prod Order: " + OrdQnty);
		webDB.pressTabKey();
		commonFunc.checkRefreshCellsDisappear();
		webDB.switchToDefaultContent();
		assertTrue(flag);
	}

	/**
	 * This Method used to navigate Scrap Product Tab in Production Order
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToScrapByProductSubTab() {
		flag = webDB.waitForElement(ProductionOrderLocators.ScrapByProductLabel, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionOrderLocators.ScrapByProductLabel, ElementType.Xpath);
			flag = webDB.waitForElement(ProductionOrderLocators.ScrapProductTableContainer, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Scrap / Product Sub tab Navigated Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to fill form details in Production Order Creation
	 * 
	 * @author Sakthi-GWL
	 * @param Loc
	 * @param OrdType
	 * @param Pfx
	 * @param BOMType
	 * @param Item
	 * @param OrdQnty
	 */
	public void createProdOrderAndFillFormDetailsWithSORef(String Loc, String OrdType, String Pfx, String BOMType,
			String Item, String OrdQnty, String SoDesc) {
		switchToCreateProductionOrderFrame();
		commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.CreateProdOrd_LocationLovbtn, Loc);
		logs.logging("INFO", "Location Selected in Prod Order: " + Loc);
		switchToCreateProductionOrderFrame();
		commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.CreateProdOrd_OrderTypeLovbtn, OrdType);
		logs.logging("INFO", "Order Type Selected in Prod Order: " + OrdType);
		switchToCreateProductionOrderFrame();
		commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.CreateProdOrd_PrefixLovbtn, Pfx);
		logs.logging("INFO", "Prefix Selected in Prod Order: " + Pfx);
		switchToCreateProductionOrderFrame();
		flag = webDB.waitForElement(ProductionOrderLocators.CreateProdOrd_BOMTypeSelect, ElementType.Xpath);
		if (flag) {
			flag = webDB.selectDropDownByText(ProductionOrderLocators.CreateProdOrd_BOMTypeSelect, BOMType,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "BOM Type Selected in Prod Order: " + BOMType);
				commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.CreateProdOrd_ItemLovbtn, Item);
				logs.logging("INFO", "Item Selected in Prod Order: " + Item);
				switchToCreateProductionOrderFrame();
				commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.SoDesc, SoDesc);
				logs.logging("INFO", "So Desc Entered in Prod Order: " + SoDesc);
				switchToCreateProductionOrderFrame();
				commonFunc.verifyAddTableSpecs(ProductionOrderLocators.CreateProdOrd_OrderQntyInput,
						ProductionOrderLocators.CreateProdOrd_OrderQntyInput, OrdQnty);
				logs.logging("INFO", "Order Quantity Entered in Prod Order: " + OrdQnty);
				webDB.switchToDefaultContent();
//				
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Select Ordered releases In Po
	 * 
	 * @author Deepan-GWL
	 */
	public void verifySelectOrderedReleasesInPO() {
		ArrayList<String> TotalProcess = new ArrayList<>();
		List<WebElement> TotalElement = WebDriverBase.getDriver()
				.findElements(By.xpath(ProductionOrderLocators.TotalProcess()));
		for (WebElement element : TotalElement) {
			TotalProcess.add(element.getText());
		}
		VariableHandler.setTotalProductionProcess(TotalProcess);
		flag = webDB.waitForElement(ProductionOrderLocators.ShowMore, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Show More Button Is Displayed");
			webDB.clickAnElement(ProductionOrderLocators.ShowMore, ElementType.Xpath);
			flag = webDB.waitForElement(ProductionOrderLocators.ShowLess, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Show Less Button Is Displayed");
				webDB.clickAnElement(ProductionOrderLocators.ShowLess, ElementType.Xpath);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to verify Status of Production Order with MRP Number
	 * 
	 * @author Sakthi-GWL
	 * @param MRPNo
	 * @param ExpectedStatus
	 */
	public void searchAndVerifyStatusOfPONo(String PONo, String ExpectedStatus) {
		commonFunc.clearFiltersInFindPage();
		commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.FindProdOrderNoLovBtn, PONo);
		flag = webDB.waitForElement(ProductionOrderLocators.FindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionOrderLocators.FindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(ProductionOrderLocators.TableData_Status(PONo), ElementType.Xpath);
			if (flag) {
				String DisplayedStatus = webDB.getTextFromElement(ProductionOrderLocators.TableData_Status(PONo),
						ElementType.Xpath);
				logs.logging("INFO", "Displayed Status: " + DisplayedStatus);
				flag = DisplayedStatus.equalsIgnoreCase(ExpectedStatus);
				if (flag) {
					logs.logging("INFO", "Production Order Status Verified");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to select First and Rest Operation Event values And Save
	 * Header in Production Order
	 * 
	 * @author Sakthi-GWL
	 * @param FOpr
	 * @param ROpr
	 */
	public void selectFirstRestOprEventvaluesAndSaveHeader(String FOpr, String ROpr) {
		flag = webDB.waitForClickElement(ProductionOrderLocators.ShowMoreHeaderBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(ProductionOrderLocators.ShowMoreHeaderBtn, ElementType.Xpath);
			webDB.clickAnElement(ProductionOrderLocators.ShowMoreHeaderBtn, ElementType.Xpath);
			flag = webDB.waitForElement(ProductionOrderLocators.FirstOprMREventSelect, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Show more button clicked and First Operation Event Select Displayed");
				flag = webDB.selectDropDownByText(ProductionOrderLocators.FirstOprMREventSelect, FOpr,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "First Operation Event Selected: " + FOpr);
					flag = webDB.selectDropDownByText(ProductionOrderLocators.RestOprMREventSelect, ROpr,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Rest Operation Event Selected: " + ROpr);
						flag = webDB.waitForElement(ProductionOrderLocators.HeaderSaveIcon, ElementType.Xpath);
						if (flag) {
							webDB.moveToElement(ProductionOrderLocators.HeaderSaveIcon, ElementType.Xpath);
							webDB.clickAnElement(ProductionOrderLocators.HeaderSaveIcon, ElementType.Xpath);
							flag = webDB.waitForClickElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
							if (flag) {
								if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon,
										ElementType.Xpath)) {
									webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon,
											ElementType.Xpath);
								}
								logs.logging("INFO", "Header Save Icon Clicked and Toast Displayed");
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to search and Verify Production Order Status with Production
	 * Order no
	 * 
	 * @author Sakthi-GWL
	 * @param ProdOrderNo
	 * @param ExpectedStatus
	 */
	public void searchAndVerifyStatusWithProdOrderNo(String ProdOrderNo, String ExpectedStatus) {
		commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.FindProdOrderNoLovBtn, ProdOrderNo);
		flag = webDB.waitForElement(ProductionOrderLocators.FindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionOrderLocators.FindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(ProductionOrderLocators.FindTableStatusWithProdOrderNo(ProdOrderNo),
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button Clicked in Production Order Find Page");
				String DisplayStatus = webDB.getTextFromElement(
						ProductionOrderLocators.FindTableStatusWithProdOrderNo(ProdOrderNo), ElementType.Xpath);
				logs.logging("INFO", "Displayed Status: " + DisplayStatus);
				logs.logging("INFO", "Expected Status: " + ExpectedStatus);
				flag = ExpectedStatus.equalsIgnoreCase(DisplayStatus);
				if (flag) {
					logs.logging("INFO", "Status verified with expected and displayed");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Store Process And Raw Material
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyStoreProcessAndRawMaterial() {
		Map<String, String> ProcessTypeMap = new HashMap<String, String>();
		List<WebElement> elements = WebDriverBase.getDriver()
				.findElements(By.xpath(ProductionOrderLocators.ProcessTypeList()));
		for (int i = 1; i <= elements.size(); i++) {
			flag = webDB.waitForElement(ProductionOrderLocators.ProcessName(i), ElementType.Xpath);
			if (flag) {
				String ProcessName = webDB.getTextFromElement(ProductionOrderLocators.ProcessName(i),
						ElementType.Xpath);
				String ProcessType = elements.get(i - 1).getText();
				ProcessTypeMap.put(ProcessType, ProcessName);
				logs.logging("INFO", i + ". ProcessType: " + ProcessType + ",ProcessName: " + ProcessName);
			}
		}
		VariableHandler.setProductionProcess(ProcessTypeMap);
		flag = webDB.waitForElement(ProductionOrderLocators.MaterialRequestTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionOrderLocators.MaterialRequestTab, ElementType.Xpath);
			flag = webDB.waitForElement(ProductionOrderLocators.MaterialProcess(), ElementType.Xpath);
			if (flag) {
				Map<String, String> ProcessItemMap = new HashMap<String, String>();
				List<WebElement> MaterialProcess = WebDriverBase.getDriver()
						.findElements(By.xpath(ProductionOrderLocators.MaterialProcess()));
				for (int i = 1; i <= MaterialProcess.size(); i++) {
					flag = webDB.waitForElement(ProductionOrderLocators.ItemName(i), ElementType.Xpath);
					if (flag) {
						String ItemName = webDB.getTextFromElement(ProductionOrderLocators.ItemName(i),
								ElementType.Xpath);
						String ProcessName = MaterialProcess.get(i - 1).getText();
						ProcessItemMap.put(ProcessName, ItemName);
						logs.logging("INFO", i + ". ProcessName: " + ProcessName + ",ItemName: " + ItemName);
					}
				}
				VariableHandler.setProductionProcessItem(ProcessItemMap);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Manual Production Order Without SO First
	 * Process Remaining Inside
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyManualProductionOrderWithoutSoFirstProcessOutsideRemainingInsideCQOR() {
		int OrderReleaseCount = 0;
		String FirstOpr = commonFunc.getDataFromTestDataSheet("ComQty");
		String RestOpr = commonFunc.getDataFromTestDataSheet("Orderreleases");
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToProductionOrderInProdPlanning();
		clickAddIconInCreationProductionOrder();
		createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
				commonFunc.getDataFromTestDataSheet("PROPrefix"),
				commonFunc.getDataFromTestDataSheet("ManufactureType"),
				commonFunc.getDataFromTestDataSheet("BomNumberOutsideAndInside"),
				commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
		clickSaveIconInCreationProductionOrder();
		verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
				commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
		verifyProcessType(commonFunc.getDataFromTestDataSheet("Outside"),
				commonFunc.getDataFromTestDataSheet("InsideType"));
		verifyStoreProcessAndRawMaterial();
		navigateToRoutingSubTab();
		storeRoutingProcessAndProcessType();
		storeRoutingProcessAndConsumptionAndRecipientWareHouse();
		clickHeaderSaveAndPostProdOrder();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		}
		commonFunc.navigateToProductionOrderInProdPlanning();
		searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
				commonFunc.getDataFromTestDataSheet("Inprogress"));
		for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
			String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
			String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
			String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
			String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
			String Operation = null;
			if (i == 0) {
				Operation = FirstOpr;
			} else {
				Operation = RestOpr;
			}
			logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
			logs.logging("INFO", "ProcessType: " + ProcessType);
			logs.logging("INFO", "Operation Type for Process: " + Operation);
			logs.logging("INFO", "RecipientWH: " + RecipientWH);
			logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
			if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
					|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
				if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
					if (OrderReleaseCount == 0) {
						orderReleaseOperationsProcessesInsideType();
						OrderReleaseCount++;
					}
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
					materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
					completionQuantityOperationsProcessesInsideSingleType();
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
							System.getProperty("LoginPassword"), "AdminUsername");
				}
			} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Outside"))) {
				if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
					// -----------------------------------------------------------------------------------------------------

				} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
					completionQuantityOperationsProcessesOutsideType();
				}
			}
		}
		commonFunc.navigateToProductionOrderInProdPlanning();
		searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Select Ordered releases In Po
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyProductionOrderMREventAndRestOP(String FirstOpr, String RestOpr, String MoveType) {
		flag = webDB.waitForElement(ProductionOrderLocators.ShowMore, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Show More Button Is Displayed");
			webDB.moveToElement(ProductionOrderLocators.ShowMore, ElementType.Xpath);
			webDB.clickAnElement(ProductionOrderLocators.ShowMore, ElementType.Xpath);
			flag = webDB.waitForElement(ProductionOrderLocators.ProcessMoveType, ElementType.Xpath);
			if (flag) {
				flag = webDB.selectDropDownByText(ProductionOrderLocators.ProcessMoveType, MoveType, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Move Type Is Selected: " + MoveType);
					flag = webDB.waitForElement(ProductionOrderLocators.ShowLess, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Show Less Button Is Displayed");
						webDB.moveToElement(ProductionOrderLocators.ShowLess, ElementType.Xpath);
						webDB.clickAnElement(ProductionOrderLocators.ShowLess, ElementType.Xpath);
						flag = webDB.waitForElement(ProductionOrderLocators.ShowMore, ElementType.Xpath);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Process Type
	 * 
	 * @author Deepan-GWL
	 * @param FirstProcessType
	 * @param OtherProcessType
	 */
	public void verifyProcessType(String FirstProcessType, String OtherProcessType) {
		ArrayList<String> Process = new ArrayList<>();
		List<WebElement> elements = WebDriverBase.getDriver()
				.findElements(By.xpath(ProductionOrderLocators.ProcessTypeList()));
		for (int i = 1; i <= elements.size(); i++) {
			flag = webDB.waitForElement(ProductionOrderLocators.ProcessType(i), ElementType.Xpath);
			if (flag) {
				String ProcessTypeValue = webDB.getTextFromElement(ProductionOrderLocators.ProcessType(i),
						ElementType.Xpath);
				Process.add(ProcessTypeValue);
			}
		}
		VariableHandler.setProductionProcess(Process);
		for (int i = 1; i <= 1; i++) {
			flag = webDB.waitForElement(ProductionOrderLocators.ProcessType(i), ElementType.Xpath);
			if (flag) {
				String FirstProcessTypeValue = webDB.getTextFromElement(ProductionOrderLocators.ProcessType(i),
						ElementType.Xpath);
				flag = FirstProcessTypeValue.equals(FirstProcessType);
				if (!flag) {
					assertTrue(false);
				}
			}
		}
		for (int i = 2; i <= elements.size(); i++) {
			flag = webDB.waitForElement(ProductionOrderLocators.ProcessType(i), ElementType.Xpath);
			if (flag) {
				String OtherProcessTypeValue = webDB.getTextFromElement(ProductionOrderLocators.ProcessType(i),
						ElementType.Xpath);
				flag = OtherProcessTypeValue.equals(OtherProcessType);
				if (!flag) {
					assertTrue(false);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
	 * Event as CQ - Move type Single
	 *
	 * @author Deepan-GWL
	 */
	public void verifyManualProductionOrderWithoutSoFirstProcessOutsideRemainingInsideWithCQCQMoveTypeSingle() {
		int OrderReleaseCount = 0;
		String FirstOpr = commonFunc.getDataFromTestDataSheet("ComQty");
		String RestOpr = commonFunc.getDataFromTestDataSheet("ComQty");
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToProductionOrderInProdPlanning();
		clickAddIconInCreationProductionOrder();
		createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
				commonFunc.getDataFromTestDataSheet("PROPrefix"),
				commonFunc.getDataFromTestDataSheet("ManufactureType"),
				commonFunc.getDataFromTestDataSheet("BomNumberOutsideAndInside"),
				commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
		clickSaveIconInCreationProductionOrder();
		verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
				commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
		verifyProcessType(commonFunc.getDataFromTestDataSheet("Outside"),
				commonFunc.getDataFromTestDataSheet("InsideType"));
		verifyStoreProcessAndRawMaterial();
		navigateToRoutingSubTab();
		storeRoutingProcessAndProcessType();
		storeRoutingProcessAndConsumptionAndRecipientWareHouse();
		clickHeaderSaveAndPostProdOrder();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		}
		commonFunc.navigateToProductionOrderInProdPlanning();
		searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
				commonFunc.getDataFromTestDataSheet("Inprogress"));
		for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
			String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
			String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
			String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
			String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
			String Operation = null;
			if (i == 0) {
				Operation = FirstOpr;
			} else {
				Operation = RestOpr;
			}
			logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
			logs.logging("INFO", "ProcessType: " + ProcessType);
			logs.logging("INFO", "Operation Type for Process: " + Operation);
			logs.logging("INFO", "RecipientWH: " + RecipientWH);
			logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
			if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
					|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
				if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
					if (OrderReleaseCount == 0) {
						orderReleaseOperationsProcessesInsideType();
						OrderReleaseCount++;
					}
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
					materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
					completionQuantityOperationsProcessesInsideSingleType();
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
							System.getProperty("LoginPassword"), "AdminUsername");
				}
			} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Outside"))) {
				if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
					// -----------------------------------------------------------------------------------------------------

				} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
					completionQuantityOperationsProcessesOutsideType();
				}
			}
		}
		commonFunc.navigateToProductionOrderInProdPlanning();
		searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	public void verifyMoveTypeSingleProcessing(String RestOpr) {
		for (String currentProcess : VariableHandler.getProductionProcess()) {
			if (currentProcess.equals(commonFunc.getDataFromTestDataSheet("InsideType"))
					&& (RestOpr.equals(commonFunc.getDataFromTestDataSheet("Orderreleases")))) {
				if (VariableHandler.getProductionProcessItem()
						.get((VariableHandler.getProductionProcessType().get(currentProcess))) != null) {
					// ----------------------------------------------------------------------------------------
					commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
							System.getProperty("LoginPassword"), "AdminUsername");
					webDB.navigateToRefresh();
					commonFunc.navigateToInventoryMaterialRequest();
					materialRequestFunc
							.storeMRNoAndCheckStatusApproveWithProdNo(VariableHandler.getProductionOrderNumber());
					commonFunc.navigateToMaterialIssuanceVoucherTab();
					materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
					materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
					commonFunc.navigateToMaterialIssuanceVoucherTab();
					materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
							commonFunc.getDataFromTestDataSheet("PostedStatus"));
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
							"HariUsername-Admin");
					commonFunc.navigateSetupInventoryPage();
					flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
							System.getProperty("LoginPassword"), "AdminUsername");
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {
						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.logoutUser();
				}
			}
		}
		// ----------------------------------------------------------------------------------
		for (String currentProcess : VariableHandler.getProductionProcess()) {
			if ((currentProcess.equals(commonFunc.getDataFromTestDataSheet("InsideType")))
					| (currentProcess.equals(commonFunc.getDataFromTestDataSheet("BothType")))) {

				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc
						.searchProductionOrderNoAndVerifyFilter(VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc.selectPendingProdOrderAndClickCreateCompletionProcess(
						VariableHandler.getProductionOrderNumber(), true);
				if (currentProcess.equals(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| (currentProcess.equals(commonFunc.getDataFromTestDataSheet("BothType")))) {
					if (RestOpr.equals(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						if (VariableHandler.getProductionProcessItem()
								.get((VariableHandler.getProductionProcessType().get(currentProcess))) != null) {
							commonFunc.logoutUser();
							commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
									System.getProperty("LoginPassword"), "AdminUsername");
							webDB.navigateToRefresh();
							commonFunc.navigateToInventoryMaterialRequest();
							materialRequestFunc.storeMRNoAndCheckStatusApproveWithProdNo(
									VariableHandler.getProductionOrderNumber());
							commonFunc.navigateToMaterialIssuanceVoucherTab();
							materialIssuanceVoucherFunc
									.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
							materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
							commonFunc.navigateToMaterialIssuanceVoucherTab();
							materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(
									VariableHandler.getMIVNumber(),
									commonFunc.getDataFromTestDataSheet("PostedStatus"));
							commonFunc.logoutUser();
							commonFunc.validCommonLogin(System.getProperty("HariUsername"),
									System.getProperty("LoginPassword"), "HariUsername-Admin");
							commonFunc.navigateSetupInventoryPage();
							flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
							commonFunc.logoutUser();
							commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
									System.getProperty("LoginPassword"), "AdminUsername");
							commonFunc.navigateToMaterialReceiptVoucher();
							if (flag) {
								materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
										VariableHandler.getProductionOrderNumber());
							} else {
								materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
							}
							materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
							materialReceiptFunctions.clickTickAndPostIcon();
							commonFunc.logoutUser();
							commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
									System.getProperty("LoginPassword"), "AdminUsername");
							commonFunc.navigateToCompletionProductionOrderInProdPlanning();
							completionProductionOrderFunc.verifySearchProductionOrdeNumberAndOpenDesiredStatus(
									VariableHandler.getProductionCompletionOrderNo(),
									commonFunc.getDataFromTestDataSheet("Draft"));
						}
					}
				}
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToMaterialReceiptVoucher();
				commonFunc.clickPendingIcon();
				materialReceiptFunctions.clickPendingInssuanceBtn();
				materialReceiptFunctions.searchWithVoucherNumber(VariableHandler.getProductionCompletionOrderNo());
				materialReceiptFunctions
						.selectVoucherNoAndClickCreateReceipt(VariableHandler.getProductionCompletionOrderNo());
				materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
				materialReceiptFunctions.clickTickAndPostIcon();
				commonFunc.logoutUser();
				// ---------------------------------------------------------------------------------------
			} else if (currentProcess.equals(commonFunc.getDataFromTestDataSheet("Outside"))) {
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToSubcontractOrder();
				subContractOrderFunc.verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
				subContractOrderFunc.verifyHeaderValidation(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
						commonFunc.getDataFromTestDataSheet("Unit_100"),
						commonFunc.getDataFromTestDataSheet("SubcontractorName"));
				subContractOrderFunc.verifyProcessQuantityCalculation();
				subContractOrderFunc.verifyAddHsnCode();
				subContractOrderFunc.verifyAddGlAccNumberValue();
				subContractOrderFunc.verifyIncludeChargesAndPostIcon();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
							"HariUsername");
					approvalFunc.navigateToApprovals();
					approvalFunc.filterDocumentsforApproval(VariableHandler.getScoNumber());
					subContractOrderFunc.verifyApproveWithDocNumber(VariableHandler.getScoNumber());
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
							System.getProperty("LoginPassword"), "AdminUsername");
					commonFunc.navigateToSubcontractOrder();
				}
				subContractOrderFunc.verifyValidateSubContractDocumentsStatus(VariableHandler.getScoNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
				materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getScoNumber());
				materialRequestFunc.headerSaveAndPost();
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
						"HariUsername-Admin");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc.checkStatusOfMRNo(VariableHandler.getMRNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToMaterialIssuanceVoucher();
				materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMRNumber());
				materialIssuanceVoucherFunc.headerTickAndPostIcon();
				materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceNumber(VariableHandler.getMIVNumber(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToGateEntryDeliveryChallan();
				deliveryChallanFunc.verifyStatusOfDeliveryChallan(VariableHandler.getDCNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getScoNumber());
				grnScoPageFunc.addHeatNumberAndTestNumber();
				grnScoPageFunc.checkConsumptionBeforeGenerationMat();
				grnScoPageFunc.verifyGenMatConsAndAllocate();
				grnScoPageFunc.checkConsumptionAfterGenerationMat();
				grnScoPageFunc.verifyViewLineDetails();
				grnScoPageFunc.saveHeaderAndPost();
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
						"HariUsername-Admin");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.verifyValidateGrnScoStatus(VariableHandler.getScoGrnNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.logoutUser();

				commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
						"HariUsername-Admin");
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToMaterialReceiptVoucher();
				if (flag) {
					materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocuments(VariableHandler.getScoGrnNumber());
				} else {
					commonFunc.clickPendingIcon();
					materialReceiptFunctions.clickPendingInssuanceBtn();
					materialReceiptFunctions.searchWithVoucherNumber(VariableHandler.getScoGrnNumber());
					materialReceiptFunctions.selectVoucherNoAndClickCreateReceipt(VariableHandler.getScoGrnNumber());
				}
				materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
				materialReceiptFunctions.clickTickAndPostIcon();
				materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
						commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				commonFunc.logoutUser();
			}

		}
	}

	/**
	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
	 * Event as OR - Move Type - Multiple
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyManualProductionOrderWithoutSoFirstProcessOutsideRemainingInsideWithFoCqRoOrMoveTypeMultiple() {
		String FirstOpr = commonFunc.getDataFromTestDataSheet("ComQty");
		String RestOpr = commonFunc.getDataFromTestDataSheet("Orderreleases");
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToProductionOrderInProdPlanning();
		clickAddIconInCreationProductionOrder();
		createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
				commonFunc.getDataFromTestDataSheet("PROPrefix"),
				commonFunc.getDataFromTestDataSheet("ManufactureType"),
				commonFunc.getDataFromTestDataSheet("BomNumberOutsideAndInside"),
				commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
		clickSaveIconInCreationProductionOrder();
		verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr, commonFunc.getDataFromTestDataSheet("MultipleType"));
		verifyProcessType(commonFunc.getDataFromTestDataSheet("Outside"),
				commonFunc.getDataFromTestDataSheet("InsideType"));
		verifyStoreProcessAndRawMaterial();
		clickHeaderSaveAndPostProdOrder();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
					"HariUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getProductionOrderNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getProductionOrderNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
		}
		searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
				commonFunc.getDataFromTestDataSheet("Inprogress"));
		commonFunc.logoutUser();

		for (String currentProcess : VariableHandler.getProductionProcess()) {
			if (currentProcess.equals(commonFunc.getDataFromTestDataSheet("InsideType"))
					&& (RestOpr.equals(commonFunc.getDataFromTestDataSheet("Orderreleases")))) {
				if (VariableHandler.getProductionProcessItem()
						.get((VariableHandler.getProductionProcessType().get(currentProcess))) != null) {
					// ----------------------------------------------------------------------------------------
					commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
							System.getProperty("LoginPassword"), "AdminUsername");
					webDB.navigateToRefresh();
					commonFunc.navigateToInventoryMaterialRequest();
					materialRequestFunc
							.storeMRNoAndCheckStatusApproveWithProdNo(VariableHandler.getProductionOrderNumber());
					commonFunc.navigateToMaterialIssuanceVoucherTab();
					materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
					materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
					commonFunc.navigateToMaterialIssuanceVoucherTab();
					materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
							commonFunc.getDataFromTestDataSheet("PostedStatus"));
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
							"HariUsername-Admin");
					commonFunc.navigateSetupInventoryPage();
					flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
							System.getProperty("LoginPassword"), "AdminUsername");
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {

						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());

					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.logoutUser();
				}
			}
		}
		// ----------------------------------------------------------------------------------
		for (String currentProcess : VariableHandler.getProductionProcess()) {
			if ((currentProcess.equals(commonFunc.getDataFromTestDataSheet("InsideType")))
					| (currentProcess.equals(commonFunc.getDataFromTestDataSheet("BothType")))) {
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToCompletionProductionOrderMultipleProcessInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionMultiProcess();
				completionProductionOrderFunc
						.searchMultipleProductionOrderNoAndVerifyFilter(VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc.selectPendingMultipleProdOrderAndClickCreateCompletionProcess(
						VariableHandler.getProductionOrderNumber());
				commonFunc.logoutUser();
				if (currentProcess.equals(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& (RestOpr.equals(commonFunc.getDataFromTestDataSheet("ComQty")))) {
					if (VariableHandler.getProductionProcessItem()
							.get((VariableHandler.getProductionProcessType().get(currentProcess))) != null) {
						// ----------------------------------------------------------------------------------------
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
						webDB.navigateToRefresh();
						commonFunc.navigateToInventoryMaterialRequest();
						materialRequestFunc
								.storeMRNoAndCheckStatusApproveWithProdNo(VariableHandler.getProductionOrderNumber());
						commonFunc.navigateToMaterialIssuanceVoucherTab();
						materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
						materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
						commonFunc.navigateToMaterialIssuanceVoucherTab();
						materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("HariUsername"),
								System.getProperty("LoginPassword"), "HariUsername-Admin");
						commonFunc.navigateSetupInventoryPage();
						flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
						commonFunc.navigateToMaterialReceiptVoucher();
						if (flag) {
							materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
									VariableHandler.getProductionOrderNumber());
						} else {
							materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
						}
						materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
						materialReceiptFunctions.clickTickAndPostIcon();
						commonFunc.logoutUser();
					}
				}
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToCompletionProductionOrderMultipleProcessInProdPlanning();
				completionProductionOrderFunc.verifySearchMultipleProductionOrdeNumberAndOpenDesiredStatus(
						VariableHandler.getProductionCompletionOrderNo(), commonFunc.getDataFromTestDataSheet("Draft"));
				completionProductionOrderFunc.selectShiftAndSaveHeaderInCompletionMultiProcess(
						commonFunc.getDataFromTestDataSheet("Shift_100"));
				completionProductionOrderFunc.clickPostButtonInCompletionMultiProcess();

				commonFunc.logoutUser();

				commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
						"HariUsername-Admin");
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToMaterialReceiptVoucher();
				if (flag) {
					materialReceiptFunctions
							.verifyOpenAlreadyCreatedMRVDocuments(VariableHandler.getProductionCompletionOrderNo());
				} else {
					commonFunc.clickPendingIcon();
					materialReceiptFunctions.clickPendingInssuanceBtn();
					materialReceiptFunctions.searchWithVoucherNumber(VariableHandler.getProductionCompletionOrderNo());
					materialReceiptFunctions
							.selectVoucherNoAndClickCreateReceipt(VariableHandler.getProductionCompletionOrderNo());
				}
				materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
				materialReceiptFunctions.clickTickAndPostIcon();
				commonFunc.logoutUser();
				// ---------------------------------------------------------------------------------------
			} else if (currentProcess.equals(commonFunc.getDataFromTestDataSheet("Outside"))) {
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToSubcontractOrder();
				subContractOrderFunc.verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
				subContractOrderFunc.verifyHeaderValidation(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
						commonFunc.getDataFromTestDataSheet("Unit_100"),
						commonFunc.getDataFromTestDataSheet("SubcontractorName"));
				subContractOrderFunc.verifyProcessQuantityCalculation();
				subContractOrderFunc.verifyAddHsnCode();
				subContractOrderFunc.verifyAddGlAccNumberValue();
				subContractOrderFunc.verifyIncludeChargesAndPostIcon();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
							"HariUsername");
					approvalFunc.navigateToApprovals();
					approvalFunc.filterDocumentsforApproval(VariableHandler.getScoNumber());
					subContractOrderFunc.verifyApproveWithDocNumber(VariableHandler.getScoNumber());
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
							System.getProperty("LoginPassword"), "AdminUsername");
					commonFunc.navigateToSubcontractOrder();
				}
				subContractOrderFunc.verifyValidateSubContractDocumentsStatus(VariableHandler.getScoNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
				materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getScoNumber());
				materialRequestFunc.headerSaveAndPost();
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
						"HariUsername-Admin");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc.checkStatusOfMRNo(VariableHandler.getMRNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToMaterialIssuanceVoucher();
				materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMRNumber());
				materialIssuanceVoucherFunc.headerTickAndPostIcon();
				materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceNumber(VariableHandler.getMIVNumber(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToGateEntryDeliveryChallan();
				deliveryChallanFunc.verifyStatusOfDeliveryChallan(VariableHandler.getDCNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getScoNumber());
				grnScoPageFunc.addHeatNumberAndTestNumber();
				grnScoPageFunc.checkConsumptionBeforeGenerationMat();
				grnScoPageFunc.verifyGenMatConsAndAllocate();
				grnScoPageFunc.checkConsumptionAfterGenerationMat();
				grnScoPageFunc.verifyViewLineDetails();
				grnScoPageFunc.saveHeaderAndPost();
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
						"HariUsername-Admin");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.verifyValidateGrnScoStatus(VariableHandler.getScoGrnNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.logoutUser();

				commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
						"HariUsername-Admin");
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToMaterialReceiptVoucher();
				if (flag) {
					materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocuments(VariableHandler.getScoGrnNumber());
				} else {
					commonFunc.clickPendingIcon();
					materialReceiptFunctions.clickPendingInssuanceBtn();
					materialReceiptFunctions.searchWithVoucherNumber(VariableHandler.getScoGrnNumber());
					materialReceiptFunctions.selectVoucherNoAndClickCreateReceipt(VariableHandler.getScoGrnNumber());
				}
				materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
				materialReceiptFunctions.clickTickAndPostIcon();
				materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
						commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				commonFunc.logoutUser();
			}

		}

		// -----------------------------------------------------------------------------------------------------

		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToProductionOrderInProdPlanning();

		searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		flag = commonFunc.logoutUser();

		assertTrue(flag);
	}

	/**
	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
	 * Event as CQ - Move type Single
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyManualProductionOrderWithoutSoFirstProcessOutsideRemainingInsideWithFoCqRoCqMoveTypeMultiple() {
		String FirstOpr = commonFunc.getDataFromTestDataSheet("ComQty");
		String RestOpr = commonFunc.getDataFromTestDataSheet("ComQty");
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToProductionOrderInProdPlanning();
		clickAddIconInCreationProductionOrder();
		createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
				commonFunc.getDataFromTestDataSheet("PROPrefix"),
				commonFunc.getDataFromTestDataSheet("ManufactureType"),
				commonFunc.getDataFromTestDataSheet("BomNumberOutsideAndInside"),
				commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
		clickSaveIconInCreationProductionOrder();
		verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr, commonFunc.getDataFromTestDataSheet("MultipleType"));
		verifyProcessType(commonFunc.getDataFromTestDataSheet("Outside"),
				commonFunc.getDataFromTestDataSheet("InsideType"));
		verifyStoreProcessAndRawMaterial();
		clickHeaderSaveAndPostProdOrder();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
					"HariUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getProductionOrderNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getProductionOrderNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
		}
		searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
				commonFunc.getDataFromTestDataSheet("Inprogress"));
		commonFunc.logoutUser();

		for (String currentProcess : VariableHandler.getProductionProcess()) {
			if (currentProcess.equals(commonFunc.getDataFromTestDataSheet("InsideType"))
					&& (RestOpr.equals(commonFunc.getDataFromTestDataSheet("Orderreleases")))) {
				if (VariableHandler.getProductionProcessItem()
						.get((VariableHandler.getProductionProcessType().get(currentProcess))) != null) {
					// ----------------------------------------------------------------------------------------
					commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
							System.getProperty("LoginPassword"), "AdminUsername");
					webDB.navigateToRefresh();
					commonFunc.navigateToInventoryMaterialRequest();
					materialRequestFunc
							.storeMRNoAndCheckStatusApproveWithProdNo(VariableHandler.getProductionOrderNumber());
					commonFunc.navigateToMaterialIssuanceVoucherTab();
					materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
					materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
					commonFunc.navigateToMaterialIssuanceVoucherTab();
					materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
							commonFunc.getDataFromTestDataSheet("PostedStatus"));
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
							"HariUsername-Admin");
					commonFunc.navigateSetupInventoryPage();
					flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
							System.getProperty("LoginPassword"), "AdminUsername");
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {
						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.logoutUser();
				}
			}
		}
		// ----------------------------------------------------------------------------------
		for (String currentProcess : VariableHandler.getProductionProcess()) {
			if ((currentProcess.equals(commonFunc.getDataFromTestDataSheet("InsideType")))
					|| (currentProcess.equals(commonFunc.getDataFromTestDataSheet("BothType")))) {
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToCompletionProductionOrderMultipleProcessInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionMultiProcess();
				completionProductionOrderFunc
						.searchMultipleProductionOrderNoAndVerifyFilter(VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc.selectPendingMultipleProdOrderAndClickCreateCompletionProcess(
						VariableHandler.getProductionOrderNumber());
				commonFunc.logoutUser();
				if (currentProcess.equals(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& (RestOpr.equals(commonFunc.getDataFromTestDataSheet("ComQty")))) {
					if (VariableHandler.getProductionProcessItem()
							.get((VariableHandler.getProductionProcessType().get(currentProcess))) != null) {
						// ----------------------------------------------------------------------------------------
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
						webDB.navigateToRefresh();
						commonFunc.navigateToInventoryMaterialRequest();
						materialRequestFunc
								.storeMRNoAndCheckStatusApproveWithProdNo(VariableHandler.getProductionOrderNumber());
						commonFunc.navigateToMaterialIssuanceVoucherTab();
						materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
						materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
						commonFunc.navigateToMaterialIssuanceVoucherTab();
						materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("HariUsername"),
								System.getProperty("LoginPassword"), "HariUsername-Admin");
						commonFunc.navigateSetupInventoryPage();
						flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
						commonFunc.navigateToMaterialReceiptVoucher();
						if (flag) {
							materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
									VariableHandler.getProductionOrderNumber());
						} else {
							materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
						}
						materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
						materialReceiptFunctions.clickTickAndPostIcon();
						commonFunc.logoutUser();
					}
				}
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToCompletionProductionOrderMultipleProcessInProdPlanning();
				completionProductionOrderFunc.verifySearchMultipleProductionOrdeNumberAndOpenDesiredStatus(
						VariableHandler.getProductionCompletionOrderNo(), commonFunc.getDataFromTestDataSheet("Draft"));
				completionProductionOrderFunc.selectShiftAndSaveHeaderInCompletionMultiProcess(
						commonFunc.getDataFromTestDataSheet("Shift_100"));
				completionProductionOrderFunc.clickPostButtonInCompletionMultiProcess();

				commonFunc.logoutUser();

				commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
						"HariUsername-Admin");
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToMaterialReceiptVoucher();
				if (flag) {
					materialReceiptFunctions
							.verifyOpenAlreadyCreatedMRVDocuments(VariableHandler.getProductionCompletionOrderNo());
				} else {
					commonFunc.clickPendingIcon();
					materialReceiptFunctions.clickPendingInssuanceBtn();
					materialReceiptFunctions.searchWithVoucherNumber(VariableHandler.getProductionCompletionOrderNo());
					materialReceiptFunctions
							.selectVoucherNoAndClickCreateReceipt(VariableHandler.getProductionCompletionOrderNo());
				}

//				commonFunc.logoutUser();
//				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//						"AdminUsername");
//				commonFunc.navigateToMaterialReceiptVoucher();
//				commonFunc.clickPendingIcon();
//				materialReceiptFunctions.clickPendingInssuanceBtn();
//				materialReceiptFunctions.searchWithVoucherNumber(VariableHandler.getProductionCompletionOrderNo());
//				materialReceiptFunctions
//						.selectVoucherNoAndClickCreateReceipt(VariableHandler.getProductionCompletionOrderNo());
				materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
				materialReceiptFunctions.clickTickAndPostIcon();
				commonFunc.logoutUser();
				// ---------------------------------------------------------------------------------------
			} else if (currentProcess.equals(commonFunc.getDataFromTestDataSheet("Outside"))) {
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToSubcontractOrder();
				subContractOrderFunc.verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
				subContractOrderFunc.verifyHeaderValidation(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
						commonFunc.getDataFromTestDataSheet("Unit_100"),
						commonFunc.getDataFromTestDataSheet("SubcontractorName"));
				subContractOrderFunc.verifyProcessQuantityCalculation();
				subContractOrderFunc.verifyAddHsnCode();
				subContractOrderFunc.verifyAddGlAccNumberValue();
				subContractOrderFunc.verifyIncludeChargesAndPostIcon();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
							"HariUsername");
					approvalFunc.navigateToApprovals();
					approvalFunc.filterDocumentsforApproval(VariableHandler.getScoNumber());
					subContractOrderFunc.verifyApproveWithDocNumber(VariableHandler.getScoNumber());
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
							System.getProperty("LoginPassword"), "AdminUsername");
					commonFunc.navigateToSubcontractOrder();
				}
				subContractOrderFunc.verifyValidateSubContractDocumentsStatus(VariableHandler.getScoNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
				materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getScoNumber());
				materialRequestFunc.headerSaveAndPost();
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
						"HariUsername-Admin");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc.checkStatusOfMRNo(VariableHandler.getMRNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToMaterialIssuanceVoucher();
				materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMRNumber());
				materialIssuanceVoucherFunc.headerTickAndPostIcon();
				materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceNumber(VariableHandler.getMIVNumber(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToGateEntryDeliveryChallan();
				deliveryChallanFunc.verifyStatusOfDeliveryChallan(VariableHandler.getDCNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getScoNumber());
				grnScoPageFunc.addHeatNumberAndTestNumber();
				grnScoPageFunc.checkConsumptionBeforeGenerationMat();
				grnScoPageFunc.verifyGenMatConsAndAllocate();
				grnScoPageFunc.checkConsumptionAfterGenerationMat();
				grnScoPageFunc.verifyViewLineDetails();
				grnScoPageFunc.saveHeaderAndPost();
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
						"HariUsername-Admin");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.verifyValidateGrnScoStatus(VariableHandler.getScoGrnNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.logoutUser();

				commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
						"HariUsername-Admin");
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToMaterialReceiptVoucher();
				if (flag) {
					materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocuments(VariableHandler.getScoGrnNumber());
				} else {
					commonFunc.clickPendingIcon();
					materialReceiptFunctions.clickPendingInssuanceBtn();
					materialReceiptFunctions.searchWithVoucherNumber(VariableHandler.getScoGrnNumber());
					materialReceiptFunctions.selectVoucherNoAndClickCreateReceipt(VariableHandler.getScoGrnNumber());
				}
				materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
				materialReceiptFunctions.clickTickAndPostIcon();
				materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
						commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				commonFunc.logoutUser();
			}

		}

		// -----------------------------------------------------------------------------------------------------

		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToProductionOrderInProdPlanning();

		searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		flag = commonFunc.logoutUser();

		assertTrue(flag);
	}

	/**
	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
	 * Event as OR - Move Type - Item
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyManualProductionOrderWithoutSoFirstProcessOutsideRemainingInsideWithFoCqRoOrMoveTypeItem() {
		String RestOpr = commonFunc.getDataFromTestDataSheet("Orderreleases");
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToProductionOrderInProdPlanning();
		clickAddIconInCreationProductionOrder();
		createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
				commonFunc.getDataFromTestDataSheet("PROPrefix"),
				commonFunc.getDataFromTestDataSheet("ManufactureType"),
				commonFunc.getDataFromTestDataSheet("BomNumberOutsideAndInside"),
				commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
		clickSaveIconInCreationProductionOrder();
		verifyProductionOrderMREventAndRestOP(commonFunc.getDataFromTestDataSheet("ComQty"), RestOpr,
				commonFunc.getDataFromTestDataSheet("ItemType"));
		verifyProcessType(commonFunc.getDataFromTestDataSheet("Outside"),
				commonFunc.getDataFromTestDataSheet("InsideType"));
		clickHeaderSaveAndPostProdOrder();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
					"HariUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getProductionOrderNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getProductionOrderNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
		}
		searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
				commonFunc.getDataFromTestDataSheet("Inprogress"));
		commonFunc.logoutUser();
		verifyMoveTypeItemProcessing(RestOpr);
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToProductionOrderInProdPlanning();
		searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
	 * Event as CQ - Move type Item
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyManualProductionOrderWithoutSoFirstProcessOutsideRemainingInsideWithFoCqRoCqMoveTypeItem() {
		String RestOpr = commonFunc.getDataFromTestDataSheet("ComQty");
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToProductionOrderInProdPlanning();
		clickAddIconInCreationProductionOrder();
		createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
				commonFunc.getDataFromTestDataSheet("PROPrefix"),
				commonFunc.getDataFromTestDataSheet("ManufactureType"),
				commonFunc.getDataFromTestDataSheet("BomOutsideAndInsideWithMaterial"),
				commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
		clickSaveIconInCreationProductionOrder();
		verifyProductionOrderMREventAndRestOP(commonFunc.getDataFromTestDataSheet("ComQty"),
				commonFunc.getDataFromTestDataSheet("ComQty"), commonFunc.getDataFromTestDataSheet("ItemType"));
		verifyProcessType(commonFunc.getDataFromTestDataSheet("Outside"),
				commonFunc.getDataFromTestDataSheet("InsideType"));
		verifyStoreProcessAndRawMaterial();
		clickHeaderSaveAndPostProdOrder();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
					"HariUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getProductionOrderNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getProductionOrderNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
		}
		searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
				commonFunc.getDataFromTestDataSheet("Inprogress"));
		commonFunc.logoutUser();
		verifyMoveTypeItemProcessing(RestOpr);
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToProductionOrderInProdPlanning();

		searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Manual Production Order Without SO First
	 * Process Remaining Inside
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyManualProductionOrderWithoutSoFirstProcessOutsideRemainingBothWithCQCQMoveTypeSingle() {
		int OrderReleaseCount = 0;
		String FirstOpr = commonFunc.getDataFromTestDataSheet("ComQty");
		String RestOpr = commonFunc.getDataFromTestDataSheet("ComQty");
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToProductionOrderInProdPlanning();
		clickAddIconInCreationProductionOrder();
		createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
				commonFunc.getDataFromTestDataSheet("PROPrefix"),
				commonFunc.getDataFromTestDataSheet("ManufactureType"),
				commonFunc.getDataFromTestDataSheet("BOMNumberOutsideAndBoth"),
				commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
		clickSaveIconInCreationProductionOrder();
		verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
				commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
		verifyProcessType(commonFunc.getDataFromTestDataSheet("Outside"),
				commonFunc.getDataFromTestDataSheet("BothType"));
		verifyStoreProcessAndRawMaterial();
		navigateToRoutingSubTab();
		storeRoutingProcessAndProcessType();
		storeRoutingProcessAndConsumptionAndRecipientWareHouse();
		clickHeaderSaveAndPostProdOrder();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		}
		commonFunc.navigateToProductionOrderInProdPlanning();
		searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
				commonFunc.getDataFromTestDataSheet("Inprogress"));
		for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
			String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
			String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
			String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
			String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
			String Operation = null;
			if (i == 0) {
				Operation = FirstOpr;
			} else {
				Operation = RestOpr;
			}
			logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
			logs.logging("INFO", "ProcessType: " + ProcessType);
			logs.logging("INFO", "Operation Type for Process: " + Operation);
			logs.logging("INFO", "RecipientWH: " + RecipientWH);
			logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
			if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
					|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
				if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
					if (OrderReleaseCount == 0) {
						orderReleaseOperationsProcessesInsideType();
						OrderReleaseCount++;
					}
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
					materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
					completionQuantityOperationsProcessesInsideSingleType();
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
							System.getProperty("LoginPassword"), "AdminUsername");
				}
			} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Outside"))) {
				if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
					// -----------------------------------------------------------------------------------------------------

				} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
					completionQuantityOperationsProcessesOutsideType();
				}
			}
		}
		commonFunc.navigateToProductionOrderInProdPlanning();
		searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
	 * Event as CQ - Move type Item
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyManualProductionOrderWithoutSoFirstProcessOutsideRemainingBothWithFoCqRoCqMoveTypeItem() {
		String FirstOpr = commonFunc.getDataFromTestDataSheet("ComQty");
		String RestOpr = commonFunc.getDataFromTestDataSheet("ComQty");
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToProductionOrderInProdPlanning();
		clickAddIconInCreationProductionOrder();
		createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
				commonFunc.getDataFromTestDataSheet("PROPrefix"),
				commonFunc.getDataFromTestDataSheet("ManufactureType"),
				commonFunc.getDataFromTestDataSheet("BOMNumberOutsideAndBoth"),
				commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
		clickSaveIconInCreationProductionOrder();
		verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr, commonFunc.getDataFromTestDataSheet("ItemType"));
		verifyProcessType(commonFunc.getDataFromTestDataSheet("Outside"),
				commonFunc.getDataFromTestDataSheet("BothType"));
		verifyStoreProcessAndRawMaterial();
		clickHeaderSaveAndPostProdOrder();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
					"HariUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getProductionOrderNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getProductionOrderNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
		}
		searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
				commonFunc.getDataFromTestDataSheet("Inprogress"));
		commonFunc.logoutUser();
		verifyMoveTypeItemProcessing(RestOpr);
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToProductionOrderInProdPlanning();
		searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This method used to Click Cancel Button in Production Order
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickAndVerifyCancelInProductionOrder() {
		flag = webDB.waitForClickElement(ProductionOrderLocators.ProdOrderCancelBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(ProductionOrderLocators.ProdOrderCancelBtn, ElementType.Xpath);
			webDB.clickAnElement(ProductionOrderLocators.ProdOrderCancelBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			logs.logging("INFO", "Cancel Button Clicked");
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.OkayButton, ElementType.Xpath)) {
				webDB.clickAnElement(CommonUiLocators.OkayButton, ElementType.Xpath);
			}
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Cancel Ok Button Clicked in Production Order");
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click add icon in Line details in routing tab
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickAddLineIconInRoutingTab() {
		flag = webDB.waitForElement(ProductionOrderLocators.RoutingAddBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(ProductionOrderLocators.RoutingAddBtn, ElementType.Xpath);
			webDB.clickAnElement(ProductionOrderLocators.RoutingAddBtn, ElementType.Xpath);
			flag = webDB.waitForElement(ProductionOrderLocators.RoutingProcessSeqTableInput(), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Line Icon Clicked in Routing tab");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Manual Production Order Without SO All Process
	 * Both
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyManualProductionOrderWithoutSoAllProcessBothCQCQWithSingleType() {
		int OrderReleaseCount = 0;
		String FirstOpr = commonFunc.getDataFromTestDataSheet("ComQty");
		String RestOpr = commonFunc.getDataFromTestDataSheet("ComQty");
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToProductionOrderInProdPlanning();
		clickAddIconInCreationProductionOrder();
		createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
				commonFunc.getDataFromTestDataSheet("PROPrefix"),
				commonFunc.getDataFromTestDataSheet("ManufactureType"),
				commonFunc.getDataFromTestDataSheet("BomAllProcessBoth"),
				commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
		clickSaveIconInCreationProductionOrder();
		verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
				commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
		verifyProcessType(commonFunc.getDataFromTestDataSheet("BothType"),
				commonFunc.getDataFromTestDataSheet("BothType"));
		verifyStoreProcessAndRawMaterial();
		navigateToRoutingSubTab();
		storeRoutingProcessAndProcessType();
		storeRoutingProcessAndConsumptionAndRecipientWareHouse();
		clickHeaderSaveAndPostProdOrder();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		}
		commonFunc.navigateToProductionOrderInProdPlanning();
		searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
				commonFunc.getDataFromTestDataSheet("Inprogress"));
		for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
			String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
			String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
			String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
			String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
			String Operation = null;
			if (i == 0) {
				Operation = FirstOpr;
			} else {
				Operation = RestOpr;
			}
			logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
			logs.logging("INFO", "ProcessType: " + ProcessType);
			logs.logging("INFO", "Operation Type for Process: " + Operation);
			logs.logging("INFO", "RecipientWH: " + RecipientWH);
			logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
			if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
					|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
				if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
					if (OrderReleaseCount == 0) {
						orderReleaseOperationsProcessesInsideType();
						OrderReleaseCount++;
					}
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
					materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
					completionQuantityOperationsProcessesInsideSingleType();
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
							System.getProperty("LoginPassword"), "AdminUsername");
				}
			} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Outside"))) {
				if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
					// -----------------------------------------------------------------------------------------------------

				} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
					completionQuantityOperationsProcessesOutsideType();
				}
			}
		}
		commonFunc.navigateToProductionOrderInProdPlanning();
		searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Method used to fill ProcSeqNo, Oprn No and Process Id in Routing tab Add
	 * Line Details
	 * 
	 * @author Sakthi-GWL
	 * @param ProcessNo
	 * @param OprnNo
	 * @param ProcessId
	 */
	private void fillProcSeqNoOprnNoAndProcessIdInRouting(String ProcessNo, String OprnNo, String ProcessId) {
		commonFunc.verifyAddTableSpecs(ProductionOrderLocators.RoutingProcessSeqTableData(),
				ProductionOrderLocators.RoutingProcessSeqTableInput(), ProcessNo);
		logs.logging("INFO", "Process No Entered: " + ProcessNo);
		commonFunc.verifyAddTableSpecs(ProductionOrderLocators.RoutingOprnNoTableData(),
				ProductionOrderLocators.RoutingOprnNoTableInput(), OprnNo);
		logs.logging("INFO", "Oprn No Entered: " + OprnNo);
		flag = webDB.waitForElement(ProductionOrderLocators.RoutingProcessTableData(), ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(ProductionOrderLocators.RoutingProcessTableData(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.RoutingProcessTableLovBtn(), ProcessId);
			logs.logging("INFO", "Process Id Selected: " + ProcessId);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Line Details and Select Lot Serial Type in Routing
	 * tab
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickLineDetailsSelectLotSerialType() {
		flag = webDB.waitForElement(ProductionOrderLocators.RoutingLineDetailsIcon(), ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(ProductionOrderLocators.RoutingLineDetailsIcon(), ElementType.Xpath);
			webDB.javaScriptClickAnElement(ProductionOrderLocators.RoutingLineDetailsIcon(), ElementType.Xpath);
			flag = webDB.waitForElement(ProductionOrderLocators.DetailsMenuThreeDots, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Three Dots Clicked in Lines");
				webDB.clickAnElement(ProductionOrderLocators.DetailsMenuThreeDots, ElementType.Xpath);
				flag = webDB.waitForElement(ProductionOrderLocators.LineDetailsIframe, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Line Details Clicked");
					webDB.switchToFrame(ProductionOrderLocators.LineDetailsIframe, ElementType.Xpath);
					flag = webDB.waitForElement(ProductionOrderLocators.NonStandardLotSerialDetailTable,
							ElementType.Xpath);
					if (flag) {
						webDB.doubleClickAnElement(ProductionOrderLocators.NonStandardLotSerialDetailTable,
								ElementType.Xpath);
						flag = webDB.waitForElement(ProductionOrderLocators.NonStandardLotSerialDetailSelect,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Lot Serial table Clicked");
							flag = webDB.selectDropDownByValue(ProductionOrderLocators.NonStandardLotSerialDetailSelect,
									"Y", ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Lot Serial 'System' Selected");
								webDB.moveToElement(ProductionOrderLocators.LineDetailsSaveIcon, ElementType.Xpath);
								webDB.clickAnElement(ProductionOrderLocators.LineDetailsSaveIcon, ElementType.Xpath);
								webDB.switchToDefaultContent();
								flag = webDB.waitForElement(ProductionOrderLocators.LineDetailsCloseIcon,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(ProductionOrderLocators.LineDetailsCloseIcon,
											ElementType.Xpath);
									flag = webDB.waitForClickElement(ProductionOrderLocators.RoutingLineDetailsIcon(),
											ElementType.Xpath);
									if (flag) {
										logs.logging("INFO", "Line Details Saved and closed");
									}
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
	 * This Method Is Used To Verify Manual Production Order Without SO All Process
	 * Both
	 * 
	 * @author Deepan-GWL
	 */

	public void verifyManualProductionOrderWithoutSoAllProcessBothFOCQRoCqWithItemType() {
		String RestOpr = commonFunc.getDataFromTestDataSheet("ComQty");
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToProductionOrderInProdPlanning();
		clickAddIconInCreationProductionOrder();
		createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
				commonFunc.getDataFromTestDataSheet("PROPrefix"),
				commonFunc.getDataFromTestDataSheet("ManufactureType"),
				commonFunc.getDataFromTestDataSheet("BomAllProcessBoth"),
				commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
		clickSaveIconInCreationProductionOrder();
		verifyProductionOrderMREventAndRestOP(commonFunc.getDataFromTestDataSheet("ComQty"), RestOpr,
				commonFunc.getDataFromTestDataSheet("ItemType"));
		verifyProcessType(commonFunc.getDataFromTestDataSheet("BothType"),
				commonFunc.getDataFromTestDataSheet("BothType"));
		verifyStoreProcessAndRawMaterial();
		clickHeaderSaveAndPostProdOrder();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
					"HariUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getProductionOrderNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getProductionOrderNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
		}
		searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
				commonFunc.getDataFromTestDataSheet("Inprogress"));
		commonFunc.logoutUser();
		verifyMoveTypeItemProcessing(RestOpr);
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToProductionOrderInProdPlanning();

		searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Method used to fill Type and Work Center in Routing tab Add Line Details
	 * 
	 * @author Sakthi-GWL
	 * @param Type
	 * @param ConsWH
	 * @param RcptWH
	 */
	private void fillTypeAndWarehouseInRouting(String Type, String ConsWH, String RcptWH) {
		flag = webDB.waitForElement(ProductionOrderLocators.RoutingTypeTableData(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionOrderLocators.RoutingTypeTableData(), ElementType.Xpath);
			flag = webDB.waitForClickElement(ProductionOrderLocators.RoutingTypeTableSelect(), ElementType.Xpath);
			if (flag) {
				flag = webDB.selectDropDownByText(ProductionOrderLocators.RoutingTypeTableSelect(), Type,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Process Type Selected: " + Type);
					flag = webDB.waitForElement(ProductionOrderLocators.RoutingConsWHTableData(), ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(ProductionOrderLocators.RoutingConsWHTableData(), ElementType.Xpath);
						flag = webDB.waitForClickElement(ProductionOrderLocators.RoutingConsWHTableSelect(),
								ElementType.Xpath);
						if (flag) {
							flag = webDB.selectDropDownByText(ProductionOrderLocators.RoutingConsWHTableSelect(),
									ConsWH, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Cons.WH Selected: " + ConsWH);
								flag = webDB.waitForElement(ProductionOrderLocators.RoutingRcptWHTableData(),
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(ProductionOrderLocators.RoutingRcptWHTableData(),
											ElementType.Xpath);
									flag = webDB.waitForClickElement(ProductionOrderLocators.RoutingRcptWHTableSelect(),
											ElementType.Xpath);
									if (flag) {
										flag = webDB.selectDropDownByText(
												ProductionOrderLocators.RoutingRcptWHTableSelect(), RcptWH,
												ElementType.Xpath);
										if (flag) {
											logs.logging("INFO", "Rcpt.WH Selected: " + RcptWH);
										}
									}
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
	 * This Method Is Used To Verify Manual Production Order Pending Queue Inside
	 * And Outside
	 *
	 * @author Deepan-GWL
	 */

	public boolean verifyManualProductionOrderPendingQueueInsideAndOutside() {
		try {
			int OrderReleaseCount = 0;
			String FirstOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			String RestOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomOutsideAndInsideWithMaterial"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("OutsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						if (OrderReleaseCount == 0) {
							orderReleaseOperationsProcessesInsideType();
							OrderReleaseCount++;
						}
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionProcess();
						completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
								commonFunc.getDataFromTestDataSheet("Loc_100"),
								VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc
								.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionProcess();
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
								VariableHandler.getProductionCompletionOrderNo(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						commonFunc.navigateSetupInventoryPage();
						flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
						if (!VariableHandler.getAutoPostMRVFlag()) {
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
							materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
							materialReceiptFunctions.clickTickAndPostIcon();
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
									VariableHandler.getMrvNumber(),
									commonFunc.getDataFromTestDataSheet("IssuedStatus"));
						}
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						// Before Verify
						completionProductionOrderFunc.clickPendingIconInCompletionProcess();
						completionProductionOrderFunc
								.searchProductionOrderNoAndVerifyFilter(VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc.verifyProductionOrderPendingQueueInside(ProcessName);
						completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
								commonFunc.getDataFromTestDataSheet("Loc_100"),
								VariableHandler.getProductionOrderNumber());
						commonFunc.navigateToInventoryMaterialRequest();
						materialRequestFunc
								.storeMRNoAndCheckStatusApproveWithProdNo(VariableHandler.getProductionOrderNumber());
						commonFunc.navigateToMaterialIssuanceVoucherTab();
						materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
						materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
						commonFunc.navigateToMaterialIssuanceVoucherTab();
						materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						commonFunc.navigateSetupInventoryPage();
						flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
						if (!VariableHandler.getAutoPostMRVFlag()) {
							commonFunc.navigateToMaterialReceiptVoucher();
							if (flag) {
								materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
										VariableHandler.getProductionOrderNumber());
							} else {
								materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
							}
							materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
							materialReceiptFunctions.clickTickAndPostIcon();
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
									VariableHandler.getMrvNumber(),
									commonFunc.getDataFromTestDataSheet("IssuedStatus"));
						}
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.verifySearchProductionOrdeNumberAndOpenDesiredStatus(
								VariableHandler.getProductionCompletionOrderNo(),
								commonFunc.getDataFromTestDataSheet("Draft"));
						completionProductionOrderFunc
								.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionProcess();
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
								VariableHandler.getProductionCompletionOrderNo(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						// After Verify
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionProcess();
						completionProductionOrderFunc
								.searchProductionOrderNoAndVerifyFilter(VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc.verifyProductionOrderPendingQueueAfterInsideProcess(
								VariableHandler.getProductionProcessType().get(ProcessName));
						commonFunc.navigateSetupInventoryPage();
						flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
						if (!VariableHandler.getAutoPostMRVFlag()) {
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
							materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
							materialReceiptFunctions.clickTickAndPostIcon();
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
									VariableHandler.getMrvNumber(),
									commonFunc.getDataFromTestDataSheet("IssuedStatus"));
						}
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						commonFunc.navigateToSubcontractOrder();
						// Before Verify
						subContractOrderFunc
								.verifySearchPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
						subContractOrderFunc.verifyQueueQuantityForSubcontract(ProcessName);
						commonFunc.navigateToSubcontractOrder();
						subContractOrderFunc.verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
						subContractOrderFunc.verifyHeaderValidation(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
								commonFunc.getDataFromTestDataSheet("Unit_100"),
								commonFunc.getDataFromTestDataSheet("SubcontractorName"));
						subContractOrderFunc.verifyAddHsnCode();
						subContractOrderFunc.verifyAddGlAccNumberValue();
						subContractOrderFunc.verifyIncludeChargesAndPostIcon();
						if (!VariableHandler.getApprovalFlag()) {
							approvalFunc
									.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
							commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
									VariableHandler.getScoNumber(), webDB.getDataFromProperties("HodEmpId"),
									commonFunc.getDataFromTestDataSheet("Unit_100"),
									System.getProperty("HariUsername"));
							String Value = VariableHandler.getLastApprovalAPIHeaderText();
							Value = Value.replace("Material Request Created. : ", "").trim();
							Value = Value.replace("SUBCONTRACT ORDER Document is Approved.", "").trim();
							VariableHandler.setMRNumber(Value);
						}
						commonFunc.navigateToSubcontractOrder();
						subContractOrderFunc.verifyValidateSubContractDocumentsStatus(VariableHandler.getScoNumber(),
								commonFunc.getDataFromTestDataSheet("ApproveStatus"));
						// After Verify
						subContractOrderFunc
								.verifySearchPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
						subContractOrderFunc.verifyQueueQuantityForSubcontractAfterOutside(ProcessName);
						commonFunc.navigateToInventoryMaterialRequest();
						materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
						materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getScoNumber());
						materialRequestFunc.headerSaveAndPost();
						if (!VariableHandler.getApprovalFlag()) {
							approvalFunc
									.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
							commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
									VariableHandler.getMRNumber(), webDB.getDataFromProperties("HodEmpId"),
									commonFunc.getDataFromTestDataSheet("Unit_100"),
									System.getProperty("HariUsername"));
						}
						commonFunc.navigateToInventoryMaterialRequest();
						materialRequestFunc.checkStatusOfMRNo(VariableHandler.getMRNumber(),
								commonFunc.getDataFromTestDataSheet("ApproveStatus"));
						commonFunc.navigateToMaterialIssuanceVoucher();
						materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMRNumber());
						materialIssuanceVoucherFunc.headerTickAndPostIcon();
						materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceNumber(VariableHandler.getMIVNumber(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
//						commonFunc.navigateToGateEntryDeliveryChallan();
//						deliveryChallanFunc.verifyStatusOfDeliveryChallan(VariableHandler.getDCNumber(),
//								commonFunc.getDataFromTestDataSheet("ApproveStatus"));
						commonFunc.navigateToGrnSco();
						grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getScoNumber());
//						grnScoPageFunc.addHeatNumberAndTestNumber();
						grnScoPageFunc.checkConsumptionBeforeGenerationMat();
						grnScoPageFunc.verifyGenMatConsAndAllocate();
						grnScoPageFunc.checkConsumptionAfterGenerationMat();
						grnScoPageFunc.verifyViewLineDetails();
						grnScoPageFunc.saveHeaderAndPost();
						if (!VariableHandler.getApprovalFlag()) {
							approvalFunc
									.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
							commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
									VariableHandler.getScoGrnNumber(), webDB.getDataFromProperties("HodEmpId"),
									commonFunc.getDataFromTestDataSheet("Unit_100"),
									System.getProperty("HariUsername"));
						}
						commonFunc.navigateToGrnSco();
						grnScoPageFunc.verifyValidateGrnScoStatus(VariableHandler.getScoGrnNumber(),
								commonFunc.getDataFromTestDataSheet("ApproveStatus"));
						commonFunc.navigateSetupInventoryPage();
						flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
						if (!VariableHandler.getAutoPostMRVFlag()) {
							commonFunc.navigateToMaterialReceiptVoucher();
							if (flag) {
								materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
										VariableHandler.getProductionOrderNumber());
							} else {
								materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
							}
							materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
							materialReceiptFunctions.clickTickAndPostIcon();
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
									VariableHandler.getMrvNumber(),
									commonFunc.getDataFromTestDataSheet("IssuedStatus"));
						}
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllInsideOperCQORMoveTypeSingle': " + e);
			return false;
		}
	}

	/**
	 * This Method used to click Save icon in Line details in routing tab
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickSaveLineIconInRoutingTab() {
		flag = webDB.waitForElement(ProductionOrderLocators.RoutingSaveBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(ProductionOrderLocators.RoutingSaveBtn, ElementType.Xpath);
			webDB.clickAnElement(ProductionOrderLocators.RoutingSaveBtn, ElementType.Xpath);
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
	 * This Method used to click add icon in Line details in material tab
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickAddLineIconInMaterialTab() {
		flag = webDB.waitForElement(ProductionOrderLocators.MaterialReqAddBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(ProductionOrderLocators.MaterialReqAddBtn, ElementType.Xpath);
			webDB.clickAnElement(ProductionOrderLocators.MaterialReqAddBtn, ElementType.Xpath);
			flag = webDB.waitForElement(ProductionOrderLocators.MRItemTableData(), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Line Icon Clicked in Material tab");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to fill Process Item and Bom and Req Quantity in Material
	 * tab
	 * 
	 * @author Sakthi-GWL
	 * @param Process
	 * @param Item
	 * @param BomQnty
	 */
	private void fillProcessItemBomAndReqQntyInMR(String Process, String Item, String BomQnty) {
		flag = webDB.waitForElement(ProductionOrderLocators.MRProcessTableData(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionOrderLocators.MRProcessTableData(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.MRProcessTableLovBtn(), Process);
			logs.logging("INFO", "Process Selected in Material: " + Process);
			flag = webDB.waitForElement(ProductionOrderLocators.MRItemTableData(), ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(ProductionOrderLocators.MRItemTableData(), ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.MRItemTableLovBtn(), Item);
				logs.logging("INFO", "Item Selected in Material: " + Item);
				commonFunc.verifyAddTableSpecs(ProductionOrderLocators.MRBOMQntyTableData(),
						ProductionOrderLocators.MRBOMQntyTableInput(), BomQnty);
				logs.logging("INFO", "BOM Quantity Entered: " + BomQnty);
//				webDB.doubleClickAnElement(ProductionOrderLocators.MRBOMQntyTableData, ElementType.Xpath);
				webDB.pressEnterKey();
				flag = webDB.waitForElement(ProductionOrderLocators.MRReqQntyTableData(), ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(ProductionOrderLocators.MRReqQntyTableData(), ElementType.Xpath);
					webDB.clickAnElement(ProductionOrderLocators.MRReqQntyTableData(), ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					String ReqQnty = webDB.getTextFromElement(ProductionOrderLocators.MRReqQntyTableData(),
							ElementType.Xpath);
//				commonFunc.verifyAddTableSpecs(ProductionOrderLocators.MRReqQntyTableData,
//						ProductionOrderLocators.MRReqQntyTableInput, ReqQnty);
					logs.logging("INFO", "Required Quantity Displayed: " + ReqQnty);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Save icon in Line details in material tab
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickSaveLineIconInMaterialTab() {
		flag = webDB.waitForElement(ProductionOrderLocators.MaterialReqSaveBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(ProductionOrderLocators.MaterialReqSaveBtn, ElementType.Xpath);
			webDB.clickAnElement(ProductionOrderLocators.MaterialReqSaveBtn, ElementType.Xpath);
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
	 * This Method used to click add icon in Line details in resource tab
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickAddLineIconInResourceTab() {
		flag = webDB.waitForElement(ProductionOrderLocators.ResourcesAddBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(ProductionOrderLocators.ResourcesAddBtn, ElementType.Xpath);
			webDB.clickAnElement(ProductionOrderLocators.ResourcesAddBtn, ElementType.Xpath);
			flag = webDB.waitForElement(ProductionOrderLocators.ResourceProcessTableData(), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Line Icon Clicked in Resouces tab");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to fill Process Type and Basis in Line details in resource
	 * tab
	 * 
	 * @author Sakthi-GWL
	 * @param Process
	 * @param type
	 * @param Basis
	 */
	private void fillProcessTypeBasisInResourceTab(String Process, String Type, String Basis) {
		flag = webDB.waitForElement(ProductionOrderLocators.ResourceProcessTableData(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionOrderLocators.ResourceProcessTableData(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.ResourceProcessTableLovBtn(), Process);
			logs.logging("INFO", "Process Selected In Resources: " + Process);
			flag = webDB.waitForElement(ProductionOrderLocators.ResourceTypeTableData(), ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(ProductionOrderLocators.ResourceTypeTableData(), ElementType.Xpath);
				flag = webDB.waitForElement(ProductionOrderLocators.ResourceTypeTableSelect(), ElementType.Xpath);
				if (flag) {
					flag = webDB.selectDropDownByText(ProductionOrderLocators.ResourceTypeTableSelect(), Type,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Type Seleced In Resources: " + Type);
						flag = webDB.waitForElement(ProductionOrderLocators.ResourceBasisTableData(),
								ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(ProductionOrderLocators.ResourceBasisTableData(), ElementType.Xpath);
							flag = webDB.waitForElement(ProductionOrderLocators.ResourceBasisTableSelect(),
									ElementType.Xpath);
							if (flag) {
								flag = webDB.selectDropDownByText(ProductionOrderLocators.ResourceBasisTableSelect(),
										Basis, ElementType.Xpath);
								if (flag) {
									logs.logging("INFO", "Basis Seleced In Resources: " + Basis);
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
	 * This Method used to fill Resource Date and Eff Percent in Line details in
	 * resource tab
	 * 
	 * @author Sakthi-GWL
	 * @param Resource
	 * @param Date
	 * @param EffPerc
	 */
	private void fillResourceDateEffPercentInResourceTab(String Resource, String Date, String EffPerc) {
		flag = webDB.waitForElement(ProductionOrderLocators.ResourceGroupTableData(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionOrderLocators.ResourceGroupTableData(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.ResourceGroupTableLovBtn(), Resource);
			logs.logging("INFO", "Resource/Group Selected In Resources: " + Resource);
			commonFunc.verifyAddTableSpecs(ProductionOrderLocators.ResourceDateTableData(),
					ProductionOrderLocators.ResourceDateTableInput(), Date);
			logs.logging("INFO", "Date Entered In Resources: " + Date);
			commonFunc.verifyAddTableSpecs(ProductionOrderLocators.ResourceEffTableData(),
					ProductionOrderLocators.ResourceEffTableInput(), EffPerc);
			logs.logging("INFO", "Eff-% Entered In Resources: " + EffPerc);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to fill Setup Hrs, Mins, Priority and LagHrs in Line details
	 * in resource tab
	 * 
	 * @author Sakthi-GWL
	 * @param SetupHrs
	 * @param SetupMins
	 * @param Priority
	 * @param LagHrs
	 */
	private void fillSetupHrsMinsAndPriorityLagHrsInResourceTab(String SetupHrs, String SetupMins, String Priority,
			String LagHrs) {
		commonFunc.verifyAddTableSpecs(ProductionOrderLocators.ResourceSetupHrsTableData(),
				ProductionOrderLocators.ResourceSetupHrsTableInput(), SetupHrs);
		logs.logging("INFO", "SetupHrs Entered In Resources: " + SetupHrs);
		commonFunc.verifyAddTableSpecs(ProductionOrderLocators.ResourceSetupMinTableData(),
				ProductionOrderLocators.ResourceSetupMinTableInput(), SetupMins);
		logs.logging("INFO", "SetupMins Entered In Resources: " + SetupMins);
		commonFunc.verifyAddTableSpecs(ProductionOrderLocators.ResourcePriorityTableData(),
				ProductionOrderLocators.ResourcePriorityTableInput(), Priority);
		logs.logging("INFO", "Priority Entered In Resources: " + Priority);
		commonFunc.verifyAddTableSpecs(ProductionOrderLocators.ResourceLagHrsTableData(),
				ProductionOrderLocators.ResourceLagHrsTableInput(), LagHrs);
		logs.logging("INFO", "LagHrs Entered In Resources: " + LagHrs);
	}

	/**
	 * This Method used to fill Hrs, Mins and Seconds in Line details in resource
	 * tab
	 * 
	 * @author Sakthi-GWL
	 * @param Hrs
	 * @param Mins
	 * @param Sec
	 */
	private void fillHrsMinsAndSecInResourceTab(String Hrs, String Mins, String Sec) {
		commonFunc.verifyAddTableSpecs(ProductionOrderLocators.ResourceHrsTableData(),
				ProductionOrderLocators.ResourceHrsTableInput(), Hrs);
		logs.logging("INFO", "Hrs Entered In Resources: " + Hrs);
		commonFunc.verifyAddTableSpecs(ProductionOrderLocators.ResourceMinTableData(),
				ProductionOrderLocators.ResourceMinTableInput(), Mins);
		logs.logging("INFO", "Mins Entered In Resources: " + Mins);
		commonFunc.verifyAddTableSpecs(ProductionOrderLocators.ResourceSecTableData(),
				ProductionOrderLocators.ResourceSecTableInput(), Sec);
		logs.logging("INFO", "Seconds Entered In Resources: " + Sec);
	}

	/**
	 * This Method used to click Save icon in Line details in Resources tab in BOM
	 * 
	 * @author Sakthi-GWL
	 */
	private void clickSaveLineIconInResourceTab() {
		flag = webDB.waitForElement(ProductionOrderLocators.ResourcesSaveBtn, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(ProductionOrderLocators.ResourcesSaveBtn, ElementType.Xpath);
			webDB.clickAnElement(ProductionOrderLocators.ResourcesSaveBtn, ElementType.Xpath);
			webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			logs.logging("INFO", "Save Line Icon Clicked in Resource tab");
		}
		assertTrue(flag);
	}

	/**
	 * This method used to How to create Manual Standard Production order?
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCreateManualStandardProductionOrder() {
		String StandardBom = commonFunc.getDataFromTestDataSheet("StandardBOM");
		try {
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"), StandardBom,
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc
						.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc
						.clickCheckBoxTableAndCreateAndStoreMRNo(VariableHandler.getProductionOrderNumber());
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc
						.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
				commonFunc.navigateToMaterialIssuanceVoucherTab();
				materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
				materialIssuanceVoucherFunc.clickheaderSaveIconInMIV();
				materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {
						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
						commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc.clickAllocateDeallocateButtonInHeader();
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
						VariableHandler.getProductionCompletionOrderNo(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occurs while executing 'verifyCreateManualStandardProductionOrder': " + e);
			return false;
		}
	}

	/**
	 * How to cancel the Production order?
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCancelProductionOrder() {
		try {
			String FGItem = commonFunc.getDataFromTestDataSheet("FG-10001-Item-BOM");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"), FGItem,
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(commonFunc.getDataFromTestDataSheet("OrderReleaseOption"),
					commonFunc.getDataFromTestDataSheet("OrderReleaseOption"),
					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			clickAndVerifyCancelInProductionOrder();
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occur while executing 'verifyCancelProductionOrder': " + e);
			return false;
		}
	}

	/**
	 * How to create Manual Non Standard Production order?
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCreateManualNonStandardProductionOrder() {
		String FGItem = commonFunc.getDataFromTestDataSheet("BomItem-2244");
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		try {
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderNonStandard"),
					commonFunc.getDataFromTestDataSheet("PRODPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"), FGItem,
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			navigateToRoutingSubTab();
			clickAddLineIconInRoutingTab();
			fillProcSeqNoOprnNoAndProcessIdInRouting("1", "1", commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			fillTypeAndWarehouseInRouting(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			clickSaveLineIconInRoutingTab();
			clickLineDetailsSelectLotSerialType();
			clickHeaderSaveInProdOrder();
			navigateToMaterialRequirementSubTab();
			clickAddLineIconInMaterialTab();
			fillProcessItemBomAndReqQntyInMR(commonFunc.getDataFromTestDataSheet("GwlProcess1"), RMItem, "2");
			clickSaveLineIconInMaterialTab();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HariEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
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
				completionProductionOrderFunc
						.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc
						.clickCheckBoxTableAndCreateAndStoreMRNo(VariableHandler.getProductionOrderNumber());
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc
						.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
				commonFunc.navigateToMaterialIssuanceVoucherTab();
				materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
				materialIssuanceVoucherFunc.clickheaderSaveIconInMIV();
				materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {
						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
						commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc.clickAllocateDeallocateButtonInHeader();
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
						VariableHandler.getProductionCompletionOrderNo(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyCreateManualNonStandardProductionOrder': " + e);
			return false;
		}
	}

	/**
	 * This method used How to create Production order from MRP?
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCreateProductionOrderFromMRP() {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate date = LocalDate.now();
			String MRPName = "TEST MRP - " + RandomStringUtils.randomNumeric(3);
			String Today = date.format(formatter);
			LocalDate previousDate = LocalDate.now().minusDays(2);
			String YesterDay = previousDate.format(formatter);
			LocalDate nextmonthdate = LocalDate.now().plusMonths(1);
			String NextMonthToday = nextmonthdate.format(formatter);
			String FGItem = commonFunc.getDataFromTestDataSheet("FG-10001-Item");
			String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
			String SalesOrderNumber = commonFunc.getDataFromTestDataSheet("MRPSalesOrderNumber");
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.clickAddIconInMRP();
			mrpFunc.fillDetailsInMRPCreationAndClickTickIcon(Today, MRPName, Today, "ALL");
			mrpFunc.clickAddLinePlusIconInSources();
			mrpFunc.enterUnitCutOffDateInSourcesLine(commonFunc.getDataFromTestDataSheet("Unit_100"), NextMonthToday);
			mrpFunc.clickSalesOrderDemandCheckBoxInSourceLines();
			mrpFunc.clickSaveIconInSourcesTab();
			mrpFunc.clickLoadDemandButtonInMRP();
			mrpFunc.navigateToDemandTabInMRPScreen();
			mrpFunc.searchSONumberInDemandAndSelectSOItems(SalesOrderNumber);
			mrpFunc.clickCheckExceptionAndSaveHeaderInMRP();
			mrpFunc.navigateToExceptionTabInMRPScreen();
			mrpFunc.validateNoExceptionDataShownInTableData();
			mrpFunc.clickRunMRPButton();
			mrpFunc.navigateToResultsTabInMRPScreen();
			mrpFunc.verifyUnitResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100") });
			mrpFunc.verifyItemResultsDetails(new String[] { FGItem, RMItem });
			mrpFunc.verifyUOMResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("Kgs"),
					commonFunc.getDataFromTestDataSheet("Kgs") });
			mrpFunc.clickHeaderSaveAndClickPost();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRPNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			mrpFunc.searchAndClickMRPNoInFindPage(VariableHandler.getMRPNumber());
			mrpFunc.clickReleaseButtonInMRP();
			mrpFunc.chooseOrderTypeFromAndToDateInReleaseDetails("All", YesterDay, Today);
			mrpFunc.clickReleaseButtonInReleaseFrame();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ReleasedStatus"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfMRPNo(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("UnreleasedStatus"));
			webDB.navigateToRefresh();
			searchAndSelectProductionOrderWithDocNo(VariableHandler.getProductionOrderNumber());
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc
						.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc
						.clickCheckBoxTableAndCreateAndStoreMRNo(VariableHandler.getProductionOrderNumber());
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc
						.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
				commonFunc.navigateToMaterialIssuanceVoucherTab();
				materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
				materialIssuanceVoucherFunc.clickheaderSaveIconInMIV();
				materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {
						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
						commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc.clickAllocateDeallocateButtonInHeader();
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
						VariableHandler.getProductionCompletionOrderNo(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO", "Exception occurs while executing 'verifyCreateProductionOrderFromMRP': " + e);
			return false;
		}
	}

	/**
	 * This method used to Order Release Operations for Inside Type
	 * 
	 * @author Sakthi-GWL
	 */
	private void orderReleaseOperationsProcessesInsideType() {
		commonFunc.navigateToCompletionProductionOrderInProdPlanning();
		completionProductionOrderFunc.clickPendingIconInCompletionProcess();
		completionProductionOrderFunc
				.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
		completionProductionOrderFunc
				.clickCheckBoxTableAndCreateAndStoreMRNo(VariableHandler.getProductionOrderNumber());
		commonFunc.navigateToInventoryMaterialRequest();
		materialRequestFunc.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
		commonFunc.navigateToMaterialIssuanceVoucherTab();
		materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
		materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
		commonFunc.navigateToMaterialIssuanceVoucherTab();
		materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
				commonFunc.getDataFromTestDataSheet("PostedStatus"));
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			if (flag) {
				materialReceiptFunctions
						.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(VariableHandler.getProductionOrderNumber());
			} else {
				materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
			}
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
	}

	/**
	 * This method used to Completion Quantity Operations for Outside Type
	 * 
	 * @author Sakthi-GWL
	 */
	private void completionQuantityOperationsProcessesOutsideType() {
		commonFunc.navigateToSubcontractOrder();
		subContractOrderFunc.verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
		subContractOrderFunc.verifyHeaderValidation(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
				commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("SubcontractorName"));
		subContractOrderFunc.verifyAddHsnCode();
		subContractOrderFunc.verifyAddGlAccNumberValue();
		subContractOrderFunc.verifyIncludeChargesAndPostIcon();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getScoNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			String Value = VariableHandler.getLastApprovalAPIHeaderText();
			Value = Value.replace("Material Request Created. : ", "").trim();
			Value = Value.replace("SUBCONTRACT ORDER Document is Approved.", "").trim();
			VariableHandler.setMRNumber(Value);
		}
		commonFunc.navigateToSubcontractOrder();
		subContractOrderFunc.verifyValidateSubContractDocumentsStatus(VariableHandler.getScoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateToInventoryMaterialRequest();
		materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
		materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getScoNumber());
		materialRequestFunc.headerSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		commonFunc.navigateToInventoryMaterialRequest();
		materialRequestFunc.checkStatusOfMRNo(VariableHandler.getMRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateToMaterialIssuanceVoucher();
		materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMRNumber());
		materialIssuanceVoucherFunc.headerTickAndPostIcon();
		materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceNumber(VariableHandler.getMIVNumber(),
				commonFunc.getDataFromTestDataSheet("PostedStatus"));
//		commonFunc.navigateToGateEntryDeliveryChallan();
//		deliveryChallanFunc.verifyStatusOfDeliveryChallan(VariableHandler.getDCNumber(),
//				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateToGrnSco();
		grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getScoNumber());
//		grnScoPageFunc.addHeatNumberAndTestNumber();
		grnScoPageFunc.checkConsumptionBeforeGenerationMat();
		grnScoPageFunc.verifyGenMatConsAndAllocate();
		grnScoPageFunc.checkConsumptionAfterGenerationMat();
		grnScoPageFunc.verifyViewLineDetails();
		grnScoPageFunc.saveHeaderAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getScoGrnNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		commonFunc.navigateToGrnSco();
		grnScoPageFunc.verifyValidateGrnScoStatus(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			if (flag) {
				materialReceiptFunctions
						.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(VariableHandler.getProductionOrderNumber());
			} else {
				materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
			}
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
	}

	/**
	 * This method used to Completion Quantity Operations for Inside Type
	 * 
	 * @author Sakthi-GWL
	 */
	private void completionQuantityOperationsProcessesInsideSingleType() {
		commonFunc.navigateToCompletionProductionOrderInProdPlanning();
		completionProductionOrderFunc.clickPendingIconInCompletionProcess();
		completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
				commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getProductionOrderNumber());
		commonFunc.navigateToInventoryMaterialRequest();
		materialRequestFunc.storeMRNoAndCheckStatusApproveWithProdNo(VariableHandler.getProductionOrderNumber());
		commonFunc.navigateToMaterialIssuanceVoucherTab();
		materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
		materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
		commonFunc.navigateToMaterialIssuanceVoucherTab();
		materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
				commonFunc.getDataFromTestDataSheet("PostedStatus"));
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			if (flag) {
				materialReceiptFunctions
						.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(VariableHandler.getProductionOrderNumber());
			} else {
				materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
			}
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
		commonFunc.navigateToCompletionProductionOrderInProdPlanning();
		completionProductionOrderFunc.verifySearchProductionOrdeNumberAndOpenDesiredStatus(
				VariableHandler.getProductionCompletionOrderNo(), commonFunc.getDataFromTestDataSheet("Draft"));
		completionProductionOrderFunc
				.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
		completionProductionOrderFunc.clickPostButtonInCompletionProcess();
		commonFunc.navigateToCompletionProductionOrderInProdPlanning();
		completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
				VariableHandler.getProductionCompletionOrderNo(), commonFunc.getDataFromTestDataSheet("PostedStatus"));
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
	}

	/**
	 * This method used to Completion Quantity Operations for Inside Type
	 * 
	 * @author Sakthi-GWL
	 */
	private void completionQuantityOperationsProcessesInsideMultipleType() {
		commonFunc.navigateToCompletionProductionOrderMultipleProcessInProdPlanning();
		completionProductionOrderFunc.clickPendingIconInCompletionMultiProcess();
		completionProductionOrderFunc
				.searchMultipleProductionOrderNoAndVerifyFilter(VariableHandler.getProductionOrderNumber());
		completionProductionOrderFunc.selectPendingMultipleProdOrderAndClickCreateCompletionProcess(
				VariableHandler.getProductionOrderNumber());
		commonFunc.navigateToInventoryMaterialRequest();
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
		commonFunc.navigateToMaterialReceiptVoucher();
		materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
				commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		completionProductionOrderFunc.verifySearchMultipleProductionOrdeNumberAndOpenDesiredStatus(
				VariableHandler.getProductionCompletionOrderNo(), commonFunc.getDataFromTestDataSheet("Draft"));
		completionProductionOrderFunc
				.selectShiftAndSaveHeaderInCompletionMultiProcess(commonFunc.getDataFromTestDataSheet("Shift_100"));
		completionProductionOrderFunc.clickPostButtonInCompletionMultiProcess();
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
	}

	/**
	 * This method used to Manual Production order with first Operation MR Event -
	 * OR and Rest Op. MR Event as OR - Move Type - Single
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllInsideOperORORMoveTypeSingle() {
		try {
			int OrderReleaseCount = 0;
			String FirstOpr = commonFunc.getDataFromTestDataSheet("Orderreleases");
			String RestOpr = commonFunc.getDataFromTestDataSheet("Orderreleases");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
//			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
//					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
//				VariableHandler.setProductionOrderNumber("PROD-2526-1707");
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						if (OrderReleaseCount == 0) {
							orderReleaseOperationsProcessesInsideType();
							OrderReleaseCount++;
						}
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionProcess();
						completionProductionOrderFunc.clickPendingCompletionInsideProcess();
						completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
								commonFunc.getDataFromTestDataSheet("Loc_100"),
								VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc
								.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionProcess();
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
								VariableHandler.getProductionCompletionOrderNo(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						commonFunc.navigateSetupInventoryPage();
						flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
						if (!VariableHandler.getAutoPostMRVFlag()) {
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
							materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
							materialReceiptFunctions.clickTickAndPostIcon();
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
									VariableHandler.getMrvNumber(),
									commonFunc.getDataFromTestDataSheet("IssuedStatus"));
						}
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesInsideSingleType();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesOutsideType();
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllInsideOperORORMoveTypeSingle': " + e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with first Operation MR Event -
	 * OR and Rest Op. MR Event as CQ - Move Type - Single
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllInsideOperORCQMoveTypeSingle() {
		try {
			int OrderReleaseCount = 0;
			String FirstOpr = commonFunc.getDataFromTestDataSheet("Orderreleases");
			String RestOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						if (OrderReleaseCount == 0) {
							orderReleaseOperationsProcessesInsideType();
							OrderReleaseCount++;
						}
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionProcess();
						completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
								commonFunc.getDataFromTestDataSheet("Loc_100"),
								VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc
								.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionProcess();
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
								VariableHandler.getProductionCompletionOrderNo(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						commonFunc.navigateSetupInventoryPage();
						flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
						if (!VariableHandler.getAutoPostMRVFlag()) {
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
							materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
							materialReceiptFunctions.clickTickAndPostIcon();
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
									VariableHandler.getMrvNumber(),
									commonFunc.getDataFromTestDataSheet("IssuedStatus"));
						}
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesInsideSingleType();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesOutsideType();
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllInsideOperORCQMoveTypeSingle': " + e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with first Operation MR Event -
	 * CQ and Rest Op. MR Event as OR - Move Type - Single
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllInsideOperCQORMoveTypeSingle() {
		try {
			int OrderReleaseCount = 0;
			String FirstOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			String RestOpr = commonFunc.getDataFromTestDataSheet("Orderreleases");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						if (OrderReleaseCount == 0) {
							orderReleaseOperationsProcessesInsideType();
							OrderReleaseCount++;
						}
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionProcess();
						completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
								commonFunc.getDataFromTestDataSheet("Loc_100"),
								VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc
								.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionProcess();
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
								VariableHandler.getProductionCompletionOrderNo(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						commonFunc.navigateSetupInventoryPage();
						flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
						if (!VariableHandler.getAutoPostMRVFlag()) {
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
							materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
							materialReceiptFunctions.clickTickAndPostIcon();
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
									VariableHandler.getMrvNumber(),
									commonFunc.getDataFromTestDataSheet("IssuedStatus"));
						}
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesInsideSingleType();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesOutsideType();
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllInsideOperCQORMoveTypeSingle': " + e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with first Operation MR Event -
	 * CQ and Rest Op. MR Event as CQ - Move type Single
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllInsideOperCQCQMoveTypeSingle() {
		try {
			int OrderReleaseCount = 0;
			String FirstOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			String RestOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						if (OrderReleaseCount == 0) {
							orderReleaseOperationsProcessesInsideType();
							OrderReleaseCount++;
						}
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionProcess();
						completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
								commonFunc.getDataFromTestDataSheet("Loc_100"),
								VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc
								.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionProcess();
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
								VariableHandler.getProductionCompletionOrderNo(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						commonFunc.navigateSetupInventoryPage();
						flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
						if (!VariableHandler.getAutoPostMRVFlag()) {
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
							materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
							materialReceiptFunctions.clickTickAndPostIcon();
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
									VariableHandler.getMrvNumber(),
									commonFunc.getDataFromTestDataSheet("IssuedStatus"));
						}
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesInsideSingleType();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesOutsideType();
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllInsideOperCQCQMoveTypeSingle': " + e);
			return false;
		}
	}

	/**
	 * This Method Is Used To Verify Manual Production Order Without SO All Process
	 * Both
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyManualProductionOrderWithoutSoAllProcessBothFOCQRoCqWithMultipleType() {
		String FirstOpr = commonFunc.getDataFromTestDataSheet("ComQty");
		String RestOpr = commonFunc.getDataFromTestDataSheet("ComQty");
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToProductionOrderInProdPlanning();
		clickAddIconInCreationProductionOrder();
		createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
				commonFunc.getDataFromTestDataSheet("PROPrefix"),
				commonFunc.getDataFromTestDataSheet("ManufactureType"),
				commonFunc.getDataFromTestDataSheet("BomAllProcessBoth"),
				commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
		clickSaveIconInCreationProductionOrder();
		verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr, commonFunc.getDataFromTestDataSheet("MultipleType"));
		verifyProcessType(commonFunc.getDataFromTestDataSheet("BothType"),
				commonFunc.getDataFromTestDataSheet("BothType"));
		verifyStoreProcessAndRawMaterial();
		clickHeaderSaveAndPostProdOrder();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
					"HariUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getProductionOrderNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getProductionOrderNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
		}
		searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
				commonFunc.getDataFromTestDataSheet("Inprogress"));
		commonFunc.logoutUser();
		verifyMoveTypeMultipleProcessing(RestOpr);
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToProductionOrderInProdPlanning();
		searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Manual Production Order Without SO First
	 * Process Remaining Inside
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyManualProductionOrderWithoutSoFirstProcessOutsideRemainingBothWithFoCqRoCqMoveTypeMultiple() {
		String FirstOpr = commonFunc.getDataFromTestDataSheet("ComQty");
		String RestOpr = commonFunc.getDataFromTestDataSheet("ComQty");
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToProductionOrderInProdPlanning();
		clickAddIconInCreationProductionOrder();
		createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
				commonFunc.getDataFromTestDataSheet("PROPrefix"),
				commonFunc.getDataFromTestDataSheet("ManufactureType"),
				commonFunc.getDataFromTestDataSheet("BOMNumberOutsideAndBoth"),
				commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
		clickSaveIconInCreationProductionOrder();
		verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr, commonFunc.getDataFromTestDataSheet("MultipleType"));
		verifyProcessType(commonFunc.getDataFromTestDataSheet("Outside"),
				commonFunc.getDataFromTestDataSheet("BothType"));
		verifyStoreProcessAndRawMaterial();
		clickHeaderSaveAndPostProdOrder();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
					"HariUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getProductionOrderNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getProductionOrderNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
		}
		searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
				commonFunc.getDataFromTestDataSheet("Inprogress"));
		commonFunc.logoutUser();
		verifyMoveTypeMultipleProcessing(RestOpr);
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToProductionOrderInProdPlanning();

		searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
				commonFunc.getDataFromTestDataSheet("CompletedStatus"));
		flag = commonFunc.logoutUser();

		assertTrue(flag);
	}

	/**
	 * Verify Move Type Item Processing
	 * 
	 * @author Deepan-GWL
	 * @param RestOpr
	 */
	public void verifyMoveTypeItemProcessing(String RestOpr) {
		int attempt = 0;
		for (String currentProcess : VariableHandler.getProductionProcess()) {
			if (currentProcess.equals(commonFunc.getDataFromTestDataSheet("InsideType"))
					&& (RestOpr.equals(commonFunc.getDataFromTestDataSheet("Orderreleases")))) {
				if (VariableHandler.getProductionProcessItem()
						.get((VariableHandler.getProductionProcessType().get(currentProcess))) != null) {
					// ----------------------------------------------------------------------------------------
					commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
							System.getProperty("LoginPassword"), "AdminUsername");
					webDB.navigateToRefresh();
					commonFunc.navigateToInventoryMaterialRequest();
					materialRequestFunc
							.storeMRNoAndCheckStatusApproveWithProdNo(VariableHandler.getProductionOrderNumber());
					commonFunc.navigateToMaterialIssuanceVoucherTab();
					materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
					materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
					commonFunc.navigateToMaterialIssuanceVoucherTab();
					materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
							commonFunc.getDataFromTestDataSheet("PostedStatus"));
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
							"HariUsername-Admin");
					commonFunc.navigateSetupInventoryPage();
					flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
							System.getProperty("LoginPassword"), "AdminUsername");
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {

						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());

					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.logoutUser();
				}
			}
		}
		// ----------------------------------------------------------------------------------
		for (String currentProcess : VariableHandler.getProductionProcess()) {
			if ((currentProcess.equals(commonFunc.getDataFromTestDataSheet("InsideType")))
					|| (currentProcess.equals(commonFunc.getDataFromTestDataSheet("BothType")))) {
				if (attempt == 0) {
					commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
							System.getProperty("LoginPassword"), "AdminUsername");
					commonFunc.navigateToCompletionProductionOrderMultipleProcessInProdPlanning();
					completionProductionOrderFunc.clickPendingIconInCompletionMultiProcess();
					completionProductionOrderFunc
							.searchMultipleProductionOrderNoAndVerifyFilter(VariableHandler.getProductionOrderNumber());
					completionProductionOrderFunc.selectPendingMultipleProdOrderAndClickCreateCompletionProcess(
							VariableHandler.getProductionOrderNumber());
					commonFunc.logoutUser();
					if (currentProcess.equals(commonFunc.getDataFromTestDataSheet("InsideType"))
							|| (currentProcess.equals(commonFunc.getDataFromTestDataSheet("BothType")))) {
						if (RestOpr.equals(commonFunc.getDataFromTestDataSheet("ComQty"))) {
							if (VariableHandler.getProductionProcessItem()
									.get((VariableHandler.getProductionProcessType().get(currentProcess))) != null) {
								// ----------------------------------------------------------------------------------------
								commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
										System.getProperty("LoginPassword"), "AdminUsername");
								webDB.navigateToRefresh();
								commonFunc.navigateToInventoryMaterialRequest();
								materialRequestFunc.storeMRNoAndCheckStatusApproveWithProdNo(
										VariableHandler.getProductionOrderNumber());
								commonFunc.navigateToMaterialIssuanceVoucherTab();
								materialIssuanceVoucherFunc
										.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
								materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
								commonFunc.navigateToMaterialIssuanceVoucherTab();
								materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(
										VariableHandler.getMIVNumber(),
										commonFunc.getDataFromTestDataSheet("PostedStatus"));
								commonFunc.logoutUser();
								commonFunc.validCommonLogin(System.getProperty("HariUsername"),
										System.getProperty("LoginPassword"), "HariUsername-Admin");
								commonFunc.navigateSetupInventoryPage();
								flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
								commonFunc.logoutUser();
								commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
										System.getProperty("LoginPassword"), "AdminUsername");
								commonFunc.navigateToMaterialReceiptVoucher();
								if (flag) {
									materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
											VariableHandler.getProductionOrderNumber());
								} else {
									materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
								}
								materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
								materialReceiptFunctions.clickTickAndPostIcon();
								commonFunc.logoutUser();
							}
						}
					}
					commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
							System.getProperty("LoginPassword"), "AdminUsername");
					commonFunc.navigateToCompletionProductionOrderMultipleProcessInProdPlanning();
					completionProductionOrderFunc.verifySearchMultipleProductionOrdeNumberAndOpenDesiredStatus(
							VariableHandler.getProductionCompletionOrderNo(),
							commonFunc.getDataFromTestDataSheet("Draft"));
					completionProductionOrderFunc.selectShiftAndSaveHeaderInCompletionMultiProcess(
							commonFunc.getDataFromTestDataSheet("Shift_100"));
					completionProductionOrderFunc.clickPostButtonInCompletionMultiProcess();
					commonFunc.logoutUser();

					commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
							"HariUsername-Admin");
					commonFunc.navigateSetupInventoryPage();
					flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
							System.getProperty("LoginPassword"), "AdminUsername");
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions
								.verifyOpenAlreadyCreatedMRVDocuments(VariableHandler.getProductionCompletionOrderNo());
					} else {
						commonFunc.clickPendingIcon();
						materialReceiptFunctions.clickPendingInssuanceBtn();
						materialReceiptFunctions
								.searchWithVoucherNumber(VariableHandler.getProductionCompletionOrderNo());
						materialReceiptFunctions
								.selectVoucherNoAndClickCreateReceipt(VariableHandler.getProductionCompletionOrderNo());
					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.logoutUser();
				}
				attempt++;
			} else if (currentProcess.equals(commonFunc.getDataFromTestDataSheet("Outside"))) {
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToSubcontractOrder();
				subContractOrderFunc.verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
				subContractOrderFunc.verifyHeaderValidation(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
						commonFunc.getDataFromTestDataSheet("Unit_100"),
						commonFunc.getDataFromTestDataSheet("SubcontractorName"));
				subContractOrderFunc.verifyProcessQuantityCalculation();
				subContractOrderFunc.verifyAddHsnCode();
				subContractOrderFunc.verifyAddGlAccNumberValue();
				subContractOrderFunc.verifyIncludeChargesAndPostIcon();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
							"HariUsername");
					approvalFunc.navigateToApprovals();
					approvalFunc.filterDocumentsforApproval(VariableHandler.getScoNumber());
					subContractOrderFunc.verifyApproveWithDocNumber(VariableHandler.getScoNumber());
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
							System.getProperty("LoginPassword"), "AdminUsername");
					commonFunc.navigateToSubcontractOrder();
				}
				subContractOrderFunc.verifyValidateSubContractDocumentsStatus(VariableHandler.getScoNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
				materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getScoNumber());
				materialRequestFunc.headerSaveAndPost();
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
						"HariUsername-Admin");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc.checkStatusOfMRNo(VariableHandler.getMRNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToMaterialIssuanceVoucher();
				materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMRNumber());
				materialIssuanceVoucherFunc.headerTickAndPostIcon();
				materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceNumber(VariableHandler.getMIVNumber(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToGateEntryDeliveryChallan();
				deliveryChallanFunc.verifyStatusOfDeliveryChallan(VariableHandler.getDCNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getScoNumber());
				grnScoPageFunc.addHeatNumberAndTestNumber();
				grnScoPageFunc.checkConsumptionBeforeGenerationMat();
				grnScoPageFunc.verifyGenMatConsAndAllocate();
				grnScoPageFunc.checkConsumptionAfterGenerationMat();
				grnScoPageFunc.verifyViewLineDetails();
				grnScoPageFunc.saveHeaderAndPost();
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
						"HariUsername-Admin");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.verifyValidateGrnScoStatus(VariableHandler.getScoGrnNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.logoutUser();

				commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
						"HariUsername-Admin");
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToMaterialReceiptVoucher();
				if (flag) {
					materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocuments(VariableHandler.getScoGrnNumber());
				} else {
					commonFunc.clickPendingIcon();
					materialReceiptFunctions.clickPendingInssuanceBtn();
					materialReceiptFunctions.searchWithVoucherNumber(VariableHandler.getScoGrnNumber());
					materialReceiptFunctions.selectVoucherNoAndClickCreateReceipt(VariableHandler.getScoGrnNumber());
				}
				materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
				materialReceiptFunctions.clickTickAndPostIcon();
				materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
						commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				commonFunc.logoutUser();
			}

		}
	}

	/**
	 * Verify Move Type Item Processing
	 * 
	 * @author Deepan-GWL
	 * @param RestOpr
	 */
	public void verifyMoveTypeMultipleProcessing(String RestOpr) {
		int attempt = 0;
		for (String currentProcess : VariableHandler.getProductionProcess()) {
			if (currentProcess.equals(commonFunc.getDataFromTestDataSheet("InsideType"))
					&& (RestOpr.equals(commonFunc.getDataFromTestDataSheet("Orderreleases")))) {
				if (VariableHandler.getProductionProcessItem()
						.get((VariableHandler.getProductionProcessType().get(currentProcess))) != null) {
					// ----------------------------------------------------------------------------------------
					commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
							System.getProperty("LoginPassword"), "AdminUsername");
					webDB.navigateToRefresh();
					commonFunc.navigateToInventoryMaterialRequest();
					materialRequestFunc
							.storeMRNoAndCheckStatusApproveWithProdNo(VariableHandler.getProductionOrderNumber());
					commonFunc.navigateToMaterialIssuanceVoucherTab();
					materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
					materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
					commonFunc.navigateToMaterialIssuanceVoucherTab();
					materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
							commonFunc.getDataFromTestDataSheet("PostedStatus"));
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
							"HariUsername-Admin");
					commonFunc.navigateSetupInventoryPage();
					flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
							System.getProperty("LoginPassword"), "AdminUsername");
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {

						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());

					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.logoutUser();
				}
			}
		}
		// ----------------------------------------------------------------------------------
		for (String currentProcess : VariableHandler.getProductionProcess()) {
			if ((currentProcess.equals(commonFunc.getDataFromTestDataSheet("InsideType")))
					| (currentProcess.equals(commonFunc.getDataFromTestDataSheet("BothType")))) {
				if (!VariableHandler.getMultipleProcessFlag() && attempt != 1) {
					commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
							System.getProperty("LoginPassword"), "AdminUsername");
					commonFunc.navigateToCompletionProductionOrderMultipleProcessInProdPlanning();
					completionProductionOrderFunc.clickPendingIconInCompletionMultiProcess();
					completionProductionOrderFunc
							.searchMultipleProductionOrderNoAndVerifyFilter(VariableHandler.getProductionOrderNumber());
					if (attempt == 0) {
						completionProductionOrderFunc
								.selectPendingMultipleProdOrderAndClickCreateCompletionProcessWithNoButton(
										VariableHandler.getProductionOrderNumber());
					} else {
						completionProductionOrderFunc.selectPendingMultipleProdOrderAndClickCreateCompletionProcess(
								VariableHandler.getProductionOrderNumber());
					}
					commonFunc.logoutUser();
					if (currentProcess.equals(commonFunc.getDataFromTestDataSheet("InsideType"))
							|| (currentProcess.equals(commonFunc.getDataFromTestDataSheet("BothType")))) {
						if (RestOpr.equals(commonFunc.getDataFromTestDataSheet("ComQty"))) {
							if (VariableHandler.getProductionProcessItem()
									.get((VariableHandler.getProductionProcessType().get(currentProcess))) != null) {
								// ----------------------------------------------------------------------------------------
								commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
										System.getProperty("LoginPassword"), "AdminUsername");
								webDB.navigateToRefresh();
								commonFunc.navigateToInventoryMaterialRequest();
								materialRequestFunc.storeMRNoAndCheckStatusApproveWithProdNo(
										VariableHandler.getProductionOrderNumber());
								commonFunc.navigateToMaterialIssuanceVoucherTab();
								materialIssuanceVoucherFunc
										.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
								materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
								commonFunc.navigateToMaterialIssuanceVoucherTab();
								materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(
										VariableHandler.getMIVNumber(),
										commonFunc.getDataFromTestDataSheet("PostedStatus"));
								commonFunc.logoutUser();
								commonFunc.validCommonLogin(System.getProperty("HariUsername"),
										System.getProperty("LoginPassword"), "HariUsername-Admin");
								commonFunc.navigateSetupInventoryPage();
								flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
								commonFunc.logoutUser();
								commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
										System.getProperty("LoginPassword"), "AdminUsername");
								commonFunc.navigateToMaterialReceiptVoucher();
								if (flag) {
									materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
											VariableHandler.getProductionOrderNumber());
								} else {
									materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
								}
								materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
								materialReceiptFunctions.clickTickAndPostIcon();
								commonFunc.logoutUser();
							}
						}
					}
					commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
							System.getProperty("LoginPassword"), "AdminUsername");
					commonFunc.navigateToCompletionProductionOrderMultipleProcessInProdPlanning();
					completionProductionOrderFunc.verifySearchMultipleProductionOrdeNumberAndOpenDesiredStatus(
							VariableHandler.getProductionCompletionOrderNo(),
							commonFunc.getDataFromTestDataSheet("Draft"));
					completionProductionOrderFunc.selectShiftAndSaveHeaderInCompletionMultiProcess(
							commonFunc.getDataFromTestDataSheet("Shift_100"));
					completionProductionOrderFunc.clickPostButtonInCompletionMultiProcess();

					commonFunc.logoutUser();

					commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
							"HariUsername-Admin");
					commonFunc.navigateSetupInventoryPage();
					flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
							System.getProperty("LoginPassword"), "AdminUsername");
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions
								.verifyOpenAlreadyCreatedMRVDocuments(VariableHandler.getProductionCompletionOrderNo());
					} else {
						commonFunc.clickPendingIcon();
						materialReceiptFunctions.clickPendingInssuanceBtn();
						materialReceiptFunctions
								.searchWithVoucherNumber(VariableHandler.getProductionCompletionOrderNo());
						materialReceiptFunctions
								.selectVoucherNoAndClickCreateReceipt(VariableHandler.getProductionCompletionOrderNo());
					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.logoutUser();
				}
				attempt++;
			} else if (currentProcess.equals(commonFunc.getDataFromTestDataSheet("Outside"))) {
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToSubcontractOrder();
				subContractOrderFunc.verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
				subContractOrderFunc.verifyHeaderValidation(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
						commonFunc.getDataFromTestDataSheet("Unit_100"),
						commonFunc.getDataFromTestDataSheet("SubcontractorName"));
				subContractOrderFunc.verifyProcessQuantityCalculation();
				subContractOrderFunc.verifyAddHsnCode();
				subContractOrderFunc.verifyAddGlAccNumberValue();
				subContractOrderFunc.verifyIncludeChargesAndPostIcon();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
							"HariUsername");
					approvalFunc.navigateToApprovals();
					approvalFunc.filterDocumentsforApproval(VariableHandler.getScoNumber());
					subContractOrderFunc.verifyApproveWithDocNumber(VariableHandler.getScoNumber());
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
							System.getProperty("LoginPassword"), "AdminUsername");
					commonFunc.navigateToSubcontractOrder();
				}
				subContractOrderFunc.verifyValidateSubContractDocumentsStatus(VariableHandler.getScoNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
				materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getScoNumber());
				materialRequestFunc.headerSaveAndPost();
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
						"HariUsername-Admin");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getMRNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getMRNumber());
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc.checkStatusOfMRNo(VariableHandler.getMRNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToMaterialIssuanceVoucher();
				materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMRNumber());
				materialIssuanceVoucherFunc.headerTickAndPostIcon();
				materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceNumber(VariableHandler.getMIVNumber(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToGateEntryDeliveryChallan();
				deliveryChallanFunc.verifyStatusOfDeliveryChallan(VariableHandler.getDCNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getScoNumber());
				grnScoPageFunc.addHeatNumberAndTestNumber();
				grnScoPageFunc.checkConsumptionBeforeGenerationMat();
				grnScoPageFunc.verifyGenMatConsAndAllocate();
				grnScoPageFunc.checkConsumptionAfterGenerationMat();
				grnScoPageFunc.verifyViewLineDetails();
				grnScoPageFunc.saveHeaderAndPost();
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
						"HariUsername-Admin");
				approvalFunc.navigateToApprovals();
				approvalFunc.filterDocumentsforApproval(VariableHandler.getScoGrnNumber());
				approvalFunc.verifyApproveWithDocNumber(VariableHandler.getScoGrnNumber());
				flag = commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.verifyValidateGrnScoStatus(VariableHandler.getScoGrnNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.logoutUser();

				commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
						"HariUsername-Admin");
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				commonFunc.logoutUser();
				commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
						"AdminUsername");
				commonFunc.navigateToMaterialReceiptVoucher();
				if (flag) {
					materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocuments(VariableHandler.getScoGrnNumber());
				} else {
					commonFunc.clickPendingIcon();
					materialReceiptFunctions.clickPendingInssuanceBtn();
					materialReceiptFunctions.searchWithVoucherNumber(VariableHandler.getScoGrnNumber());
					materialReceiptFunctions.selectVoucherNoAndClickCreateReceipt(VariableHandler.getScoGrnNumber());
				}
				materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
				materialReceiptFunctions.clickTickAndPostIcon();
				materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
						commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				commonFunc.logoutUser();
			}

		}
	}

	/**
	 * This method used to Manual Production order with first Operation MR Event -
	 * OR and Rest Op. MR Event as OR - Move type Multiple
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllInsideOperORORMoveTypeMultiple() {
		int OrderReleaseCount = 0;
		try {
			String FirstOpr = commonFunc.getDataFromTestDataSheet("Orderreleases");
			String RestOpr = commonFunc.getDataFromTestDataSheet("Orderreleases");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
					commonFunc.getDataFromTestDataSheet("MultipleType"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < 2; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						if (OrderReleaseCount == 0) {
							orderReleaseOperationsProcessesInsideType();
							OrderReleaseCount++;
						}
						commonFunc.navigateToCompletionProductionOrderMultipleProcessInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionMultiProcess();
						completionProductionOrderFunc
								.selectPendingMultipleProdOrderAndClickCreateCompletionProcessWithNoButton(
										VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc.selectShiftAndSaveHeaderInCompletionMultiProcess(
								commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionMultiProcess();
						commonFunc.navigateToMaterialReceiptVoucher();
						materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
						materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
						materialReceiptFunctions.clickTickAndPostIcon();
						commonFunc.navigateToMaterialReceiptVoucher();
						materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
								VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesInsideMultipleType();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						// -----------------------------------------------------------------------------------------------------

					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllInsideOperORORMoveTypeMultiple': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with first Operation MR Event -
	 * OR and Rest Op. MR Event as CQ - Move type Multiple
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllInsideOperORCQMoveTypeMultiple() {
		int OrderReleaseCount = 0;
		try {
			String FirstOpr = commonFunc.getDataFromTestDataSheet("Orderreleases");
			String RestOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
					commonFunc.getDataFromTestDataSheet("MultipleType"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < 2; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						if (OrderReleaseCount == 0) {
							orderReleaseOperationsProcessesInsideType();
							OrderReleaseCount++;
						}
						commonFunc.navigateToCompletionProductionOrderMultipleProcessInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionMultiProcess();
						completionProductionOrderFunc
								.selectPendingMultipleProdOrderAndClickCreateCompletionProcessWithNoButton(
										VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc.selectShiftAndSaveHeaderInCompletionMultiProcess(
								commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionMultiProcess();
						commonFunc.navigateToMaterialReceiptVoucher();
						materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
						materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
						materialReceiptFunctions.clickTickAndPostIcon();
						commonFunc.navigateToMaterialReceiptVoucher();
						materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
								VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesInsideMultipleType();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						// -----------------------------------------------------------------------------------------------------

					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllInsideOperORCQMoveTypeMultiple': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with first Operation MR Event -
	 * CQ and Rest Op. MR Event as OR - Move type Multiple
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllInsideOperCQORMoveTypeMultiple() {
		int OrderReleaseCount = 0;
		try {
			String FirstOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			String RestOpr = commonFunc.getDataFromTestDataSheet("Orderreleases");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
					commonFunc.getDataFromTestDataSheet("MultipleType"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < 2; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						if (OrderReleaseCount == 0) {
							orderReleaseOperationsProcessesInsideType();
							OrderReleaseCount++;
						}
						commonFunc.navigateToCompletionProductionOrderMultipleProcessInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionMultiProcess();
						completionProductionOrderFunc
								.selectPendingMultipleProdOrderAndClickCreateCompletionProcessWithNoButton(
										VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc.selectShiftAndSaveHeaderInCompletionMultiProcess(
								commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionMultiProcess();
						commonFunc.navigateToMaterialReceiptVoucher();
						materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
						materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
						materialReceiptFunctions.clickTickAndPostIcon();
						commonFunc.navigateToMaterialReceiptVoucher();
						materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
								VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesInsideMultipleType();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						// -----------------------------------------------------------------------------------------------------

					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllInsideOperCQORMoveTypeMultiple': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with first Operation MR Event -
	 * CQ and Rest Op. MR Event as CQ - Move type Multiple
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllInsideOperCQCQMoveTypeMultiple() {
		int OrderReleaseCount = 0;
		try {
			String FirstOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			String RestOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
					commonFunc.getDataFromTestDataSheet("MultipleType"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < 2; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						if (OrderReleaseCount == 0) {
							orderReleaseOperationsProcessesInsideType();
							OrderReleaseCount++;
						}
						commonFunc.navigateToCompletionProductionOrderMultipleProcessInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionMultiProcess();
						completionProductionOrderFunc
								.selectPendingMultipleProdOrderAndClickCreateCompletionProcessWithNoButton(
										VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc.selectShiftAndSaveHeaderInCompletionMultiProcess(
								commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionMultiProcess();
						commonFunc.navigateToMaterialReceiptVoucher();
						materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
						materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
						materialReceiptFunctions.clickTickAndPostIcon();
						commonFunc.navigateToMaterialReceiptVoucher();
						materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
								VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesInsideMultipleType();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						// -----------------------------------------------------------------------------------------------------

					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllInsideOperCQCQMoveTypeMultiple': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with first Operation MR Event -
	 * OR and Rest Op. MR Event as OR - Move type Item
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllInsideOperORORMoveTypeItem() {
		try {
			String FirstOpr = commonFunc.getDataFromTestDataSheet("Orderreleases");
			String RestOpr = commonFunc.getDataFromTestDataSheet("Orderreleases");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr, commonFunc.getDataFromTestDataSheet("ItemType"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < 1; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
//						if (OrderReleaseCount == 0) {
//							orderReleaseOperationsProcessesInsideType();
//							OrderReleaseCount++;
//						}
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionProcess();
						completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
								commonFunc.getDataFromTestDataSheet("Loc_100"),
								VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc
								.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionProcess();
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
								VariableHandler.getProductionCompletionOrderNo(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						commonFunc.navigateToMaterialReceiptVoucher();
						materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
						materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
						materialReceiptFunctions.clickTickAndPostIcon();
						commonFunc.navigateToMaterialReceiptVoucher();
						materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
								VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesInsideSingleType();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						// -----------------------------------------------------------------------------------------------------

					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllInsideOperORORMoveTypeItem': " + e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with first Operation MR Event -
	 * OR and Rest Op. MR Event as CQ - Move type Item
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllInsideOperORCQMoveTypeItem() {
		try {
			String FirstOpr = commonFunc.getDataFromTestDataSheet("Orderreleases");
			String RestOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr, commonFunc.getDataFromTestDataSheet("ItemType"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < 1; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
//						if (OrderReleaseCount == 0) {
//							orderReleaseOperationsProcessesInsideType();
//							OrderReleaseCount++;
//						}
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionProcess();
						completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
								commonFunc.getDataFromTestDataSheet("Loc_100"),
								VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc
								.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionProcess();
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
								VariableHandler.getProductionCompletionOrderNo(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						commonFunc.navigateToMaterialReceiptVoucher();
						materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
						materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
						materialReceiptFunctions.clickTickAndPostIcon();
						commonFunc.navigateToMaterialReceiptVoucher();
						materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
								VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesInsideSingleType();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						// -----------------------------------------------------------------------------------------------------

					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllInsideOperORCQMoveTypeItem': " + e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with first Operation MR Event -
	 * CQ and Rest Op. MR Event as OR - Move type Item
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllInsideOperCQORMoveTypeItem() {
		try {
			String FirstOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			String RestOpr = commonFunc.getDataFromTestDataSheet("Orderreleases");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr, commonFunc.getDataFromTestDataSheet("ItemType"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < 1; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
//						if (OrderReleaseCount == 0) {
//							orderReleaseOperationsProcessesInsideType();
//							OrderReleaseCount++;
//						}
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionProcess();
						completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
								commonFunc.getDataFromTestDataSheet("Loc_100"),
								VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc
								.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionProcess();
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
								VariableHandler.getProductionCompletionOrderNo(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						commonFunc.navigateToMaterialReceiptVoucher();
						materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
						materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
						materialReceiptFunctions.clickTickAndPostIcon();
						commonFunc.navigateToMaterialReceiptVoucher();
						materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
								VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesInsideSingleType();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						// -----------------------------------------------------------------------------------------------------

					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllInsideOperCQORMoveTypeItem': " + e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with first Operation MR Event -
	 * CQ and Rest Op. MR Event as CQ - Move type Item
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllInsideOperCQCQMoveTypeItem() {
		try {
			String FirstOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			String RestOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr, commonFunc.getDataFromTestDataSheet("ItemType"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < 1; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
//						if (OrderReleaseCount == 0) {
//							orderReleaseOperationsProcessesInsideType();
//							OrderReleaseCount++;
//						}
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionProcess();
						completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
								commonFunc.getDataFromTestDataSheet("Loc_100"),
								VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc
								.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionProcess();
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
								VariableHandler.getProductionCompletionOrderNo(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						commonFunc.navigateToMaterialReceiptVoucher();
						materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
						materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
						materialReceiptFunctions.clickTickAndPostIcon();
						commonFunc.navigateToMaterialReceiptVoucher();
						materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
								VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesInsideSingleType();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						// -----------------------------------------------------------------------------------------------------

					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllInsideOperCQCQMoveTypeItem': " + e);
			return false;
		}
	}

	/**
	 * Verify forwardForApproval for Production
	 * 
	 * @author Vimal - RMITS
	 */
	public void forwardForApproval() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(ProductionOrderLocators.WorkApprovalFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(ProductionOrderLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.ForwardApprovalLov,
					commonFunc.getDataFromTestDataSheet("SI_HOD"));
			commonFunc.hardWait(2000);
			webDB.switchToFrame(ProductionOrderLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(ProductionOrderLocators.OkButtonForward, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(ProductionOrderLocators.OkButtonForward, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(ProductionOrderLocators.CreateProdOrderAddIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Verified Navigate To PO Home Page");
				}
			}
			webDB.switchToDefaultContent();
			assertTrue(flag);
		} else {
			flag = false;
			VariableHandler.setApprovalFlag(flag);
		}
		assertTrue(flag);
	}

	// ----------------- New Scenarios ---------------------------

	/**
	 * This method used to Manual Production order with first Operation MR Event -
	 * CQ and Rest Op. MR Event as CQ - Move type Single
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllOutsideOperCQCQMoveTypeSingle() {
		try {
			int OrderReleaseCount = 0;
			String FirstOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			String RestOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllOutside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("OutsideType"),
					commonFunc.getDataFromTestDataSheet("OutsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						if (OrderReleaseCount == 0) {
							orderReleaseOperationsProcessesInsideType();
							OrderReleaseCount++;
						}
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionProcess();
						completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
								commonFunc.getDataFromTestDataSheet("Loc_100"),
								VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc
								.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionProcess();
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
								VariableHandler.getProductionCompletionOrderNo(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						commonFunc.navigateSetupInventoryPage();
						flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
						if (!VariableHandler.getAutoPostMRVFlag()) {
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
							materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
							materialReceiptFunctions.clickTickAndPostIcon();
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
									VariableHandler.getMrvNumber(),
									commonFunc.getDataFromTestDataSheet("IssuedStatus"));
						}
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesInsideSingleType();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesOutsideType();
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllOutsideOperCQCQMoveTypeSingle': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to choose sales order desc in production order creation
	 * 
	 * @author Sakthi-GWL
	 * @param SoDesc
	 */
	public void fillSalesOrderDescInProdOrderCreation(String SoDesc) {
		switchToCreateProductionOrderFrame();
		commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.CreateProdOrd_SODescLovbtn, SoDesc);
		logs.logging("INFO", "Sales Order Desc Selected in Production Order" + SoDesc);
		webDB.switchToDefaultContent();
	}

	/**
	 * Manual Production order with first Operation MR Event - OR and Rest Op. MR
	 * Event as OR - Move Type - Single
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyManualProdOrderWithSOAllInsideORORMoveTypeSingle() {
		try {
			int OrderReleaseCount = 0;
			String FirstOpr = commonFunc.getDataFromTestDataSheet("Orderreleases");
			String RestOpr = commonFunc.getDataFromTestDataSheet("Orderreleases");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInsideSORef"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			fillSalesOrderDescInProdOrderCreation(commonFunc.getDataFromTestDataSheet("SoDescNumber"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						if (OrderReleaseCount == 0) {
							orderReleaseOperationsProcessesInsideType();
							OrderReleaseCount++;
						}
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionProcess();
						completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
								commonFunc.getDataFromTestDataSheet("Loc_100"),
								VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc
								.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionProcess();
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
								VariableHandler.getProductionCompletionOrderNo(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						commonFunc.navigateSetupInventoryPage();
						flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
						if (!VariableHandler.getAutoPostMRVFlag()) {
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
							materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
							materialReceiptFunctions.clickTickAndPostIcon();
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
									VariableHandler.getMrvNumber(),
									commonFunc.getDataFromTestDataSheet("IssuedStatus"));
						}
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesInsideSingleType();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesOutsideType();
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyManualProdOrderWithSOAllInsideORORMoveTypeSingle': " + e);
			return false;
		}
	}

	/**
	 * Manual Production order with first Operation MR Event - OR and Rest Op. MR
	 * Event as CQ - Move Type - Single
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyManualProdOrderWithSOAllInsideORCQMoveTypeSingle() {
		try {
			int OrderReleaseCount = 0;
			String FirstOpr = commonFunc.getDataFromTestDataSheet("Orderreleases");
			String RestOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInsideSORef"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			fillSalesOrderDescInProdOrderCreation(commonFunc.getDataFromTestDataSheet("SoDescNumber"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						if (OrderReleaseCount == 0) {
							orderReleaseOperationsProcessesInsideType();
							OrderReleaseCount++;
						}
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionProcess();
						completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
								commonFunc.getDataFromTestDataSheet("Loc_100"),
								VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc
								.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionProcess();
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
								VariableHandler.getProductionCompletionOrderNo(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						commonFunc.navigateSetupInventoryPage();
						flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
						if (!VariableHandler.getAutoPostMRVFlag()) {
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
							materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
							materialReceiptFunctions.clickTickAndPostIcon();
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
									VariableHandler.getMrvNumber(),
									commonFunc.getDataFromTestDataSheet("IssuedStatus"));
						}
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesInsideSingleType();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesOutsideType();
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyManualProdOrderWithSOAllInsideORCQMoveTypeSingle': " + e);
			return false;
		}
	}

	/**
	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
	 * Event as OR - Move Type - Single
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyManualProdOrderWithSOAllInsideCQORMoveTypeSingle() {
		try {
			int OrderReleaseCount = 0;
			String FirstOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			String RestOpr = commonFunc.getDataFromTestDataSheet("Orderreleases");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInsideSORef"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			fillSalesOrderDescInProdOrderCreation(commonFunc.getDataFromTestDataSheet("SoDescNumber"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						if (OrderReleaseCount == 0) {
							orderReleaseOperationsProcessesInsideType();
							OrderReleaseCount++;
						}
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionProcess();
						completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
								commonFunc.getDataFromTestDataSheet("Loc_100"),
								VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc
								.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionProcess();
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
								VariableHandler.getProductionCompletionOrderNo(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						commonFunc.navigateSetupInventoryPage();
						flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
						if (!VariableHandler.getAutoPostMRVFlag()) {
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
							materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
							materialReceiptFunctions.clickTickAndPostIcon();
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
									VariableHandler.getMrvNumber(),
									commonFunc.getDataFromTestDataSheet("IssuedStatus"));
						}
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesInsideSingleType();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesOutsideType();
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyManualProdOrderWithSOAllInsideCQORMoveTypeSingle': " + e);
			return false;
		}
	}

	/**
	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
	 * Event as CQ - Move Type - Single
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyManualProdOrderWithSOAllInsideCQCQMoveTypeSingle() {
		try {
			int OrderReleaseCount = 0;
			String FirstOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			String RestOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInsideSORef"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			fillSalesOrderDescInProdOrderCreation(commonFunc.getDataFromTestDataSheet("SoDescNumber"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						if (OrderReleaseCount == 0) {
							orderReleaseOperationsProcessesInsideType();
							OrderReleaseCount++;
						}
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionProcess();
						completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
								commonFunc.getDataFromTestDataSheet("Loc_100"),
								VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc
								.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionProcess();
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
								VariableHandler.getProductionCompletionOrderNo(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						commonFunc.navigateSetupInventoryPage();
						flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
						if (!VariableHandler.getAutoPostMRVFlag()) {
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
							materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
							materialReceiptFunctions.clickTickAndPostIcon();
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
									VariableHandler.getMrvNumber(),
									commonFunc.getDataFromTestDataSheet("IssuedStatus"));
						}
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesInsideSingleType();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesOutsideType();
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyManualProdOrderWithSOAllInsideCQCQMoveTypeSingle': " + e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with first Operation MR Event -
	 * OR and Rest Op. MR Event as CQ - Move type Single
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOFirstInsideRestOutsideOperORCQMoveTypeSingle() {
		try {
			int OrderReleaseCount = 0;
			String FirstOpr = commonFunc.getDataFromTestDataSheet("Orderreleases");
			String RestOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomFirstInsideRestOutside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("OutsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						if (OrderReleaseCount == 0) {
							orderReleaseOperationsProcessesInsideType();
							OrderReleaseCount++;
						}
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionProcess();
						completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
								commonFunc.getDataFromTestDataSheet("Loc_100"),
								VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc
								.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionProcess();
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
								VariableHandler.getProductionCompletionOrderNo(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						commonFunc.navigateSetupInventoryPage();
						flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
						if (!VariableHandler.getAutoPostMRVFlag()) {
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
							materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
							materialReceiptFunctions.clickTickAndPostIcon();
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
									VariableHandler.getMrvNumber(),
									commonFunc.getDataFromTestDataSheet("IssuedStatus"));
						}
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesInsideSingleType();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesOutsideType();
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOFirstInsideRestOutsideOperORCQMoveTypeSingle': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with first Operation MR Event -
	 * CQ and Rest Op. MR Event as CQ - Move type Single
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOFirstInsideRestOutsideOperCQCQMoveTypeSingle() {
		try {
			int OrderReleaseCount = 0;
			String FirstOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			String RestOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomFirstInsideRestOutside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("OutsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						if (OrderReleaseCount == 0) {
							orderReleaseOperationsProcessesInsideType();
							OrderReleaseCount++;
						}
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionProcess();
						completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
								commonFunc.getDataFromTestDataSheet("Loc_100"),
								VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc
								.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionProcess();
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
								VariableHandler.getProductionCompletionOrderNo(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						commonFunc.navigateSetupInventoryPage();
						flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
						if (!VariableHandler.getAutoPostMRVFlag()) {
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
							materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
							materialReceiptFunctions.clickTickAndPostIcon();
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
									VariableHandler.getMrvNumber(),
									commonFunc.getDataFromTestDataSheet("IssuedStatus"));
						}
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesInsideSingleType();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesOutsideType();
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOFirstInsideRestOutsideOperCQCQMoveTypeSingle': "
							+ e);
			return false;
		}
	}

	/**
	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
	 * Event as CQ - Move Type - Single
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyManualProdOrderWithSOAllOutsideCQCQMoveTypeSingle() {
		try {
			int OrderReleaseCount = 0;
			String FirstOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			String RestOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllOutsideSORef"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			fillSalesOrderDescInProdOrderCreation(commonFunc.getDataFromTestDataSheet("SoDescNumber"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("OutsideType"),
					commonFunc.getDataFromTestDataSheet("OutsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						if (OrderReleaseCount == 0) {
							orderReleaseOperationsProcessesInsideType();
							OrderReleaseCount++;
						}
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionProcess();
						completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
								commonFunc.getDataFromTestDataSheet("Loc_100"),
								VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc
								.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionProcess();
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
								VariableHandler.getProductionCompletionOrderNo(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						commonFunc.navigateSetupInventoryPage();
						flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
						if (!VariableHandler.getAutoPostMRVFlag()) {
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
							materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
							materialReceiptFunctions.clickTickAndPostIcon();
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
									VariableHandler.getMrvNumber(),
									commonFunc.getDataFromTestDataSheet("IssuedStatus"));
						}
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesInsideSingleType();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesOutsideType();
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyManualProdOrderWithSOAllOutsideCQCQMoveTypeSingle': " + e);
			return false;
		}
	}

	/**
	 * Manual Production order with first Operation MR Event - OR and Rest Op. MR
	 * Event as CQ - Move Type - Single
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyManualProdOrderWithSOFirstInsideRestOutsideORCQMoveTypeSingle() {
		try {
			int OrderReleaseCount = 0;
			String FirstOpr = commonFunc.getDataFromTestDataSheet("Orderreleases");
			String RestOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAFirstInsideRestOutsideSORef"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			fillSalesOrderDescInProdOrderCreation(commonFunc.getDataFromTestDataSheet("SoDescNumber"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("OutsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						if (OrderReleaseCount == 0) {
							orderReleaseOperationsProcessesInsideType();
							OrderReleaseCount++;
						}
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionProcess();
						completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
								commonFunc.getDataFromTestDataSheet("Loc_100"),
								VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc
								.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionProcess();
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
								VariableHandler.getProductionCompletionOrderNo(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						commonFunc.navigateSetupInventoryPage();
						flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
						if (!VariableHandler.getAutoPostMRVFlag()) {
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
							materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
							materialReceiptFunctions.clickTickAndPostIcon();
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
									VariableHandler.getMrvNumber(),
									commonFunc.getDataFromTestDataSheet("IssuedStatus"));
						}
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesInsideSingleType();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesOutsideType();
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyManualProdOrderWithSOFirstInsideRestOutsideORCQMoveTypeSingle': "
							+ e);
			return false;
		}
	}

	/**
	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
	 * Event as CQ - Move Type - Single
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyManualProdOrderWithSOFirstInsideRestOutsideCQCQMoveTypeSingle() {
		try {
			int OrderReleaseCount = 0;
			String FirstOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			String RestOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAFirstInsideRestOutsideSORef"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			fillSalesOrderDescInProdOrderCreation(commonFunc.getDataFromTestDataSheet("SoDescNumber"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("OutsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						if (OrderReleaseCount == 0) {
							orderReleaseOperationsProcessesInsideType();
							OrderReleaseCount++;
						}
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionProcess();
						completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
								commonFunc.getDataFromTestDataSheet("Loc_100"),
								VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc
								.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionProcess();
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
								VariableHandler.getProductionCompletionOrderNo(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						commonFunc.navigateSetupInventoryPage();
						flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
						if (!VariableHandler.getAutoPostMRVFlag()) {
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
							materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
							materialReceiptFunctions.clickTickAndPostIcon();
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
									VariableHandler.getMrvNumber(),
									commonFunc.getDataFromTestDataSheet("IssuedStatus"));
						}
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesInsideSingleType();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesOutsideType();
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyManualProdOrderWithSOFirstInsideRestOutsideCQCQMoveTypeSingle': "
							+ e);
			return false;
		}
	}

	/**
	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
	 * Event as OR - Move Type - Single
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyManualProdOrderWithSOFirstOutsideRestInsideCQORMoveTypeSingle() {
		try {
			int OrderReleaseCount = 0;
			String FirstOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			String RestOpr = commonFunc.getDataFromTestDataSheet("Orderreleases");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAFirstOutsideRestInsideSORef"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			fillSalesOrderDescInProdOrderCreation(commonFunc.getDataFromTestDataSheet("SoDescNumber"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("OutsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						if (OrderReleaseCount == 0) {
							orderReleaseOperationsProcessesInsideType();
							OrderReleaseCount++;
						}
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionProcess();
						completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
								commonFunc.getDataFromTestDataSheet("Loc_100"),
								VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc
								.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionProcess();
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
								VariableHandler.getProductionCompletionOrderNo(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						commonFunc.navigateSetupInventoryPage();
						flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
						if (!VariableHandler.getAutoPostMRVFlag()) {
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
							materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
							materialReceiptFunctions.clickTickAndPostIcon();
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
									VariableHandler.getMrvNumber(),
									commonFunc.getDataFromTestDataSheet("IssuedStatus"));
						}
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesInsideSingleType();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesOutsideType();
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyManualProdOrderWithSOFirstOutsideRestInsideCQORMoveTypeSingle': "
							+ e);
			return false;
		}
	}

	/**
	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
	 * Event as CQ - Move Type - Single
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyManualProdOrderWithSOFirstOutsideRestInsideCQCQMoveTypeSingle() {
		try {
			int OrderReleaseCount = 0;
			String FirstOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			String RestOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAFirstOutsideRestInsideSORef"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			fillSalesOrderDescInProdOrderCreation(commonFunc.getDataFromTestDataSheet("SoDescNumber"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("OutsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						if (OrderReleaseCount == 0) {
							orderReleaseOperationsProcessesInsideType();
							OrderReleaseCount++;
						}
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionProcess();
						completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
								commonFunc.getDataFromTestDataSheet("Loc_100"),
								VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc
								.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionProcess();
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
								VariableHandler.getProductionCompletionOrderNo(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						commonFunc.navigateSetupInventoryPage();
						flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
						if (!VariableHandler.getAutoPostMRVFlag()) {
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
							materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
							materialReceiptFunctions.clickTickAndPostIcon();
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
									VariableHandler.getMrvNumber(),
									commonFunc.getDataFromTestDataSheet("IssuedStatus"));
						}
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesInsideSingleType();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesOutsideType();
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyManualProdOrderWithSOFirstOutsideRestInsideCQCQMoveTypeSingle': "
							+ e);
			return false;
		}
	}

	/**
	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
	 * Event as OR - Move Type - Single
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyManualProdOrderWithSOFirstOutsideRestBothCQORMoveTypeSingle() {
		try {
			int OrderReleaseCount = 0;
			String FirstOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			String RestOpr = commonFunc.getDataFromTestDataSheet("Orderreleases");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAFirstInsideRestBothSORef"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			fillSalesOrderDescInProdOrderCreation(commonFunc.getDataFromTestDataSheet("SoDescNumber"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("OutsideType"),
					commonFunc.getDataFromTestDataSheet("BothType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						if (OrderReleaseCount == 0) {
							orderReleaseOperationsProcessesInsideType();
							OrderReleaseCount++;
						}
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionProcess();
						completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
								commonFunc.getDataFromTestDataSheet("Loc_100"),
								VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc
								.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionProcess();
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
								VariableHandler.getProductionCompletionOrderNo(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						commonFunc.navigateSetupInventoryPage();
						flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
						if (!VariableHandler.getAutoPostMRVFlag()) {
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
							materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
							materialReceiptFunctions.clickTickAndPostIcon();
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
									VariableHandler.getMrvNumber(),
									commonFunc.getDataFromTestDataSheet("IssuedStatus"));
						}
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesInsideSingleType();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesOutsideType();
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyManualProdOrderWithSOFirstOutsideRestBothCQORMoveTypeSingle': "
							+ e);
			return false;
		}
	}

	/**
	 * Manual Production order with first Operation MR Event - CQ and Rest Op. MR
	 * Event as CQ - Move Type - Single
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyManualProdOrderWithSOFirstOutsideRestBothCQCQMoveTypeSingle() {
		try {
			int OrderReleaseCount = 0;
			String FirstOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			String RestOpr = commonFunc.getDataFromTestDataSheet("ComQty");
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAFirstInsideRestBothSORef"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			fillSalesOrderDescInProdOrderCreation(commonFunc.getDataFromTestDataSheet("SoDescNumber"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("OutsideType"),
					commonFunc.getDataFromTestDataSheet("BothType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				String RecipientWH = VariableHandler.getProcessAndWarehousesBOM()[i][1];
				String ConsumptionWH = VariableHandler.getProcessAndWarehousesBOM()[i][2];
				String Operation = null;
				if (i == 0) {
					Operation = FirstOpr;
				} else {
					Operation = RestOpr;
				}
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				logs.logging("INFO", "Operation Type for Process: " + Operation);
				logs.logging("INFO", "RecipientWH: " + RecipientWH);
				logs.logging("INFO", "ConsumptionWH: " + ConsumptionWH);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						|| ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("BothType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						if (OrderReleaseCount == 0) {
							orderReleaseOperationsProcessesInsideType();
							OrderReleaseCount++;
						}
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.clickPendingIconInCompletionProcess();
						completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
								commonFunc.getDataFromTestDataSheet("Loc_100"),
								VariableHandler.getProductionOrderNumber());
						completionProductionOrderFunc
								.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
						completionProductionOrderFunc.clickPostButtonInCompletionProcess();
						commonFunc.navigateToCompletionProductionOrderInProdPlanning();
						completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
								VariableHandler.getProductionCompletionOrderNo(),
								commonFunc.getDataFromTestDataSheet("PostedStatus"));
						commonFunc.navigateSetupInventoryPage();
						flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
						if (!VariableHandler.getAutoPostMRVFlag()) {
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
							materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
							materialReceiptFunctions.clickTickAndPostIcon();
							commonFunc.navigateToMaterialReceiptVoucher();
							materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
									VariableHandler.getMrvNumber(),
									commonFunc.getDataFromTestDataSheet("IssuedStatus"));
						}
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesInsideSingleType();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Orderreleases"))) {
						// -----------------------------------------------------------------------------------------------------

					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						completionQuantityOperationsProcessesOutsideType();
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyManualProdOrderWithSOFirstOutsideRestBothCQCQMoveTypeSingle': "
							+ e);
			return false;
		}
	}

	// -------------------- New Scenarios -----------------------

	/**
	 * This method used to validate the Header Details in Production Order
	 * 
	 * @author Sakthi-GWL
	 * @param Unit
	 * @param OrderType
	 * @param BOMType
	 * @param FGItem
	 * @param BOMNo
	 * @param OrderQnty
	 */
	public void verifyHeaderValidationInProductionOrderWithDB(String FileName, String SheetName) {
		logs.logging("INFO", "****Production DB Validation - Header****");
		String Unit, OrderType, BOMType, FGItem, BOMNo, OrderQnty = null;
		String[][] TotalData = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		TotalData = excelUtils.readAllDataInExcel(Filepath, SheetName);
		Unit = TotalData[2][1];
		OrderType = TotalData[2][2];
		BOMType = TotalData[2][3];
		BOMNo = TotalData[2][4];
		FGItem = TotalData[2][5];
		OrderQnty = TotalData[2][7];
		commonFunc.verifyAssertionBothValues(dbFunction.validateProductionOrderHeaderDetails(
				commonFunc.getDataFromTestDataSheet("ProductionHeaderUnit")), Unit);
		logs.logging("INFO", "Verified Unit and DB Value Matched: " + Unit);
		BOMType = String.valueOf(BOMType.charAt(0)).toUpperCase();
		OrderType = String.valueOf(OrderType.charAt(0)).toUpperCase();
		commonFunc.verifyAssertionBothValues(dbFunction.validateProductionOrderHeaderDetails(
				commonFunc.getDataFromTestDataSheet("ProductionHeaderBomType")), BOMType);
		logs.logging("INFO", "Verified BOMType and DB Value Matched: " + BOMType);
		commonFunc.verifyAssertionBothValues(dbFunction.validateProductionOrderHeaderDetails(
				commonFunc.getDataFromTestDataSheet("ProductionHeaderOrderType")), OrderType);
		logs.logging("INFO", "Verified OrderType and DB Value Matched: " + OrderType);
		commonFunc.verifyAssertionBothValues(dbFunction.validateProductionOrderHeaderDetails(
				commonFunc.getDataFromTestDataSheet("ProductionHeaderFGItem")), FGItem);
		logs.logging("INFO", "Verified FGItem and DB Value Matched: " + FGItem);
		commonFunc.verifyAssertionBothValues(dbFunction.validateProductionOrderHeaderDetails(
				commonFunc.getDataFromTestDataSheet("ProductionHeaderBOMNo")), BOMNo);
		logs.logging("INFO", "Verified BOMNo and DB Value Matched: " + BOMNo);
		commonFunc.verifyAssertionBothValues(dbFunction.validateProductionOrderHeaderDetails(
				commonFunc.getDataFromTestDataSheet("ProductionHeaderReqQnty")), OrderQnty);
		logs.logging("INFO", "Verified OrderQnty and DB Value Matched: " + OrderQnty);
	}

	/**
	 * This method used to validate the Routing Details in Production Order
	 * 
	 * @author Sakthi-GWL
	 * @param FileName
	 * @param SheetName
	 */
	public void verifyRoutingValidationInProductionOrderWithDB(String FileName, String SheetName) {
		logs.logging("INFO", "****Production DB Validation - Routing****");
		String SeqNo, OprnNo, Process, Type, WorkCenter, ConsWHId, ConsWHDesc, RcptWHId, RcptWHDesc, TSAFlag = null;
		String[][] TotalData = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		TotalData = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 5; i <= 7; i++) {
			SeqNo = TotalData[i][0];
			OprnNo = TotalData[i][1];
			Process = TotalData[i][2];
			Type = TotalData[i][4];
			WorkCenter = TotalData[i][5];
			ConsWHId = TotalData[i][7];
			ConsWHDesc = TotalData[i][8];
			RcptWHId = TotalData[i][9];
			RcptWHDesc = TotalData[i][10];
			TSAFlag = TotalData[i][11];
			if (Process.equals("")) {
				break;
			}
			commonFunc.verifyAssertionBothValues(dbFunction.validateProductionOrderRoutingDetails(SeqNo,
					commonFunc.getDataFromTestDataSheet("ProductionRoutingSeqNo")), SeqNo);
			logs.logging("INFO", "Verified SeqNo and DB Value Matched: " + SeqNo);
			commonFunc.verifyAssertionBothValues(dbFunction.validateProductionOrderRoutingDetails(SeqNo,
					commonFunc.getDataFromTestDataSheet("ProductionRoutingOprnNo")), OprnNo);
			logs.logging("INFO", "Verified OprnNo and DB Value Matched: " + OprnNo);
			commonFunc.verifyAssertionBothValues(dbFunction.validateProductionOrderRoutingDetails(SeqNo,
					commonFunc.getDataFromTestDataSheet("ProductionRoutingProcess")), Process);
			logs.logging("INFO", "Verified Process and DB Value Matched: " + Process);
			Type = String.valueOf(Type.charAt(0)).toUpperCase();
			commonFunc.verifyAssertionBothValues(dbFunction.validateProductionOrderRoutingDetails(SeqNo,
					commonFunc.getDataFromTestDataSheet("ProductionRoutingSrcType")), Type);
			logs.logging("INFO", "Verified Type and DB Value Matched: " + Type);
			commonFunc.verifyAssertionBothValues(dbFunction.validateProductionOrderRoutingDetails(SeqNo,
					commonFunc.getDataFromTestDataSheet("ProductionRoutingConsWH")), ConsWHId);
			logs.logging("INFO", "Verified ConsWHId and DB Value Matched: " + ConsWHId);
			commonFunc.verifyAssertionBothValues(dbFunction.validateProductionOrderRoutingDetails(SeqNo,
					commonFunc.getDataFromTestDataSheet("ProductionRoutingConsWHDesc")), ConsWHDesc);
			logs.logging("INFO", "Verified ConsWHDesc and DB Value Matched: " + ConsWHDesc);
			commonFunc.verifyAssertionBothValues(dbFunction.validateProductionOrderRoutingDetails(SeqNo,
					commonFunc.getDataFromTestDataSheet("ProductionRoutingTSAFlag")), TSAFlag);
			logs.logging("INFO", "Verified TSAFlag and DB Value Matched: " + TSAFlag);
			commonFunc.verifyAssertionBothValues(dbFunction.validateProductionOrderRoutingDetails(SeqNo,
					commonFunc.getDataFromTestDataSheet("ProductionRoutingRcptWH")), RcptWHId);
			logs.logging("INFO", "Verified RcptWHId and DB Value Matched: " + RcptWHId);
			commonFunc.verifyAssertionBothValues(dbFunction.validateProductionOrderRoutingDetails(SeqNo,
					commonFunc.getDataFromTestDataSheet("ProductionRoutingRcptWHDesc")), RcptWHDesc);
			logs.logging("INFO", "Verified RcptWHDesc and DB Value Matched: " + RcptWHDesc);
			commonFunc.verifyAssertionBothValues(dbFunction.validateProductionOrderRoutingDetails(SeqNo,
					commonFunc.getDataFromTestDataSheet("ProductionRoutingWorkCenter")), WorkCenter);
			logs.logging("INFO", "Verified WorkCenter and DB Value Matched: " + WorkCenter);
		}
	}

	/**
	 * This method used to validate the Raw Material Details in Production Order
	 * 
	 * @author Sakthi-GWL
	 * @param FileName
	 * @param SheetName
	 */
	public void verifyRawMaterialsValidationInProductionOrderWithDB(String FileName, String SheetName) {
		logs.logging("INFO", "****Production DB Validation - Raw Materials****");
		String SeqNo, ConsNo, Process, RMName, UOM, SourceWH, BOMQnty, ReqQnty = null;
		String[][] TotalData = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		TotalData = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 10; i <= 30; i++) {
			SeqNo = TotalData[i][0];
			ConsNo = TotalData[i][1];
			Process = TotalData[i][2];
			RMName = TotalData[i][4];
			UOM = TotalData[i][5];
			SourceWH = TotalData[i][6];
			BOMQnty = TotalData[i][10];
			ReqQnty = TotalData[i][11];
			if (SeqNo.equals("")) {
				break;
			}
			commonFunc.verifyAssertionBothValues(dbFunction.validateProductionOrderMaterialDetails(SeqNo, ConsNo,
					commonFunc.getDataFromTestDataSheet("ProductionMaterialSeqNo")), SeqNo);
			logs.logging("INFO", "Verified SeqNo and DB Value Matched: " + SeqNo);
			commonFunc.verifyAssertionBothValues(dbFunction.validateProductionOrderMaterialDetails(SeqNo, ConsNo,
					commonFunc.getDataFromTestDataSheet("ProductionMaterialConsNo")), ConsNo);
			logs.logging("INFO", "Verified ConsNo and DB Value Matched: " + ConsNo);
			commonFunc.verifyAssertionBothValues(dbFunction.validateProductionOrderMaterialDetails(SeqNo, ConsNo,
					commonFunc.getDataFromTestDataSheet("ProductionMaterialProcess")), Process);
			logs.logging("INFO", "Verified Process and DB Value Matched: " + Process);
			commonFunc.verifyAssertionBothValues(dbFunction.validateProductionOrderMaterialDetails(SeqNo, ConsNo,
					commonFunc.getDataFromTestDataSheet("ProductionMaterialRMName")), RMName);
			logs.logging("INFO", "Verified RMName and DB Value Matched: " + RMName);
			commonFunc.verifyAssertionBothValues(dbFunction.validateProductionOrderMaterialDetails(SeqNo, ConsNo,
					commonFunc.getDataFromTestDataSheet("ProductionMaterialUOM")), UOM);
			logs.logging("INFO", "Verified UOM and DB Value Matched: " + UOM);
			commonFunc.verifyAssertionBothValues(dbFunction.validateProductionOrderMaterialDetails(SeqNo, ConsNo,
					commonFunc.getDataFromTestDataSheet("ProductionMaterialSourceWH")), SourceWH);
			logs.logging("INFO", "Verified SourceWH and DB Value Matched: " + SourceWH);
			commonFunc.verifyAssertionBothValues(dbFunction.validateProductionOrderMaterialDetails(SeqNo, ConsNo,
					commonFunc.getDataFromTestDataSheet("ProductionMaterialBOMQnty")), BOMQnty);
			logs.logging("INFO", "Verified BOMQnty and DB Value Matched: " + BOMQnty);
			commonFunc.verifyAssertionBothValues(dbFunction.validateProductionOrderMaterialDetails(SeqNo, ConsNo,
					commonFunc.getDataFromTestDataSheet("ProductionMaterialReqQnty")), ReqQnty);
			logs.logging("INFO", "Verified ReqQnty and DB Value Matched: " + ReqQnty);
		}
	}

	/**
	 * This method used to update stock quantity and cost in test data sheet
	 * 
	 * @author Sakthi-GWL
	 * @param FileName
	 * @param SheetName
	 */
	public void verifyWriteBeforeStockForFGAndRawMaterials(String FileName, String SheetName) {
		logs.logging("INFO", "****Writing Before Stock Unit Cost And  Stock Quantity DB Validation For RM and FG****");
		String[][] TotalData = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		TotalData = excelUtils.readAllDataInExcel(Filepath, SheetName);
		String FGItem = TotalData[2][5];
		String FGWH = commonFunc.getDataFromTestDataSheet("FG WH");

		String StockQuantity = dbFunction.validateStockCostAndStockQuantityWithItemWarehouse(FGItem, FGWH,
				commonFunc.getDataFromTestDataSheet("StockQtyHandDBHeader"));
		logs.logging("INFO", "For FGItem: " + FGItem + " Stock Quanity Before = " + StockQuantity);
		excelUtils.writeDataInExcel(Filepath, SheetName, 2, 8, StockQuantity);

		String StockCost = dbFunction.validateStockCostAndStockQuantityWithItemWarehouse(FGItem, FGWH,
				commonFunc.getDataFromTestDataSheet("StockUpdCostDBHeader"));
		logs.logging("INFO", "For FGItem: " + FGItem + " Stock Cost Before = " + StockCost);
		excelUtils.writeDataInExcel(Filepath, SheetName, 2, 10, StockCost);

		for (int i = 10; i < 31; i++) {
			String RMItem = TotalData[i][4];
			String RMWH = commonFunc.getDataFromTestDataSheet("RM WH");
			StockQuantity = dbFunction.validateStockCostAndStockQuantityWithItemWarehouse(RMItem, RMWH,
					commonFunc.getDataFromTestDataSheet("StockQtyHandDBHeader"));
			logs.logging("INFO", "For RMItem: " + RMItem + " Stock Quanity Before = " + StockQuantity);
			excelUtils.writeDataInExcel(Filepath, SheetName, i, 7, StockQuantity);

			String AllocQuantity = dbFunction.validateStockCostAndStockQuantityWithItemWarehouse(RMItem, RMWH,
					commonFunc.getDataFromTestDataSheet("StockUpdAllocQtyDBHeader"));
			logs.logging("INFO", "For RMItem: " + RMItem + " Stock Allocate Quanity Before = " + AllocQuantity);
			excelUtils.writeDataInExcel(Filepath, SheetName, i, 8, AllocQuantity);
		}
	}

	/**
	 * This method used to update allocate stock quantity in test data sheet
	 * 
	 * @author Sakthi-GWL
	 * @param FileName
	 * @param SheetName
	 */
	public void verifyWriteAllocateStockForRawMaterials(String FileName, String SheetName, int StartIndex) {
		logs.logging("INFO", "****Writing Before Allocate Stock Quantity DB Validation****");
		String[][] TotalData = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		TotalData = excelUtils.readAllDataInExcel(Filepath, SheetName);

		for (int i = StartIndex; i <= (StartIndex + 6); i++) {
			String RMItem = TotalData[i][4];
			String RMWH = commonFunc.getDataFromTestDataSheet("RM WH");
			String AllocQuantity = dbFunction.validateStockCostAndStockQuantityWithItemWarehouse(RMItem, RMWH,
					commonFunc.getDataFromTestDataSheet("StockUpdAllocQtyDBHeader"));
			logs.logging("INFO", "For RMItem: " + RMItem + " Stock Allocate Quanity Before = " + AllocQuantity);
			excelUtils.writeDataInExcel(Filepath, SheetName, i, 8, AllocQuantity);
		}
	}

	/**
	 * This method used to validate unit cost and quantity after update in FG Item
	 * 
	 * @author Sakthi-GWL
	 * @param FileName
	 * @param SheetName
	 */
	public void verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(String FileName, String SheetName) {
		logs.logging("INFO", "****After Completed FG Item Quantity And UnitCost DB Validation****");
		String[][] TotalData = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		TotalData = excelUtils.readAllDataInExcel(Filepath, SheetName);
		String AfterCost = TotalData[2][11];
		String AfterStockQty = TotalData[2][9];
		String FGItem = TotalData[2][5];
		String FGWH = commonFunc.getDataFromTestDataSheet("FG WH");

		String StockQuantity = dbFunction.validateStockCostAndStockQuantityWithItemWarehouse(FGItem, FGWH,
				commonFunc.getDataFromTestDataSheet("StockQtyHandDBHeader"));
		commonFunc.verifyAssertionBothValues(StockQuantity, AfterStockQty);
		logs.logging("INFO", "For FGItem: " + FGItem + " Stock Quanity After = " + StockQuantity);

		String StockCost = dbFunction.validateStockCostAndStockQuantityWithItemWarehouse(FGItem, FGWH,
				commonFunc.getDataFromTestDataSheet("StockUpdCostDBHeader"));
		commonFunc.verifyAssertionBothValues(StockCost, AfterCost);
		logs.logging("INFO", "For FGItem: " + FGItem + " Stock Quanity After = " + StockCost);
	}

	/**
	 * This method is a following steps to complete Prod order as inside with Single
	 * type
	 * 
	 * @author Sakthi-GWL
	 * @param FileName
	 * @param SheetName
	 */
	public void productionOrderInsideSingleMoveType(int StartIndex, int AllocateIndex, String FileName,
			String SheetName) {
		commonFunc.navigateToCompletionProductionOrderInProdPlanning();
		completionProductionOrderFunc.clickPendingIconInCompletionProcess();
		completionProductionOrderFunc
				.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
		completionProductionOrderFunc
				.clickCheckBoxTableAndCreateAndStoreMRNo(VariableHandler.getProductionOrderNumber());
		commonFunc.navigateToInventoryMaterialRequest();
		materialRequestFunc.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
		materialRequestFunc.verifyHeaderValidationInMaterialRequestWithDB(FileName, SheetName, StartIndex);
		materialIssuanceVoucherFunc.verifyWriteBeforeLotQuantityInLotForRawMaterials(FileName, SheetName, StartIndex);
		commonFunc.navigateToMaterialIssuanceVoucherTab();
		materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
		materialIssuanceVoucherFunc.clickheaderSaveIconInMIV();
		verifyWriteAllocateStockForRawMaterials(FileName, SheetName, AllocateIndex);
		materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
		materialIssuanceVoucherFunc.verifyHeaderAndLinesValidationInMaterialIssuanceVoucherWithDB(FileName, SheetName,
				StartIndex);
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			if (flag) {
				materialReceiptFunctions
						.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(VariableHandler.getProductionOrderNumber());
			} else {
				materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
			}
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
		materialIssuanceVoucherFunc.verifyAfterLotQuantityInLotSerialValidationForRawMaterials(FileName, SheetName,
				StartIndex);
		commonFunc.navigateToCompletionProductionOrderInProdPlanning();
		completionProductionOrderFunc.clickPendingIconInCompletionProcess();
		completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
				commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getProductionOrderNumber());
		completionProductionOrderFunc.clickAllocateDeallocateButtonInHeader();
		completionProductionOrderFunc
				.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
		completionProductionOrderFunc.verifyLinesWithRMValidationInCompletionProcessWithDB(FileName, SheetName,
				StartIndex);
		completionProductionOrderFunc.clickPostButtonInCompletionProcess();
		completionProductionOrderFunc.verifyHeaderAndLinesValidationInCompletionProcessWithDB(FileName, SheetName,
				StartIndex);
		completionProductionOrderFunc.verifyStockTransactionValidationInCompletionForRawMaterials(FileName, SheetName,
				StartIndex);
		commonFunc.navigateToCompletionProductionOrderInProdPlanning();
		completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
				VariableHandler.getProductionCompletionOrderNo(), commonFunc.getDataFromTestDataSheet("PostedStatus"));
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
	}

	/**
	 * This method is a following steps to complete Prod order as inside with
	 * Multiple type
	 * 
	 * @author Sakthi-GWL
	 * @param i
	 * @param StartIndex
	 * @param AllocateIndex
	 * @param FileName
	 * @param SheetName
	 */
	public void productionOrderInsideMultipleMoveType(int i, int StartIndex, int AllocateIndex, String FileName,
			String SheetName) {
		commonFunc.navigateToCompletionProductionOrderMultipleProcessInProdPlanning();
		completionProductionOrderFunc.clickPendingIconInCompletionProcess();
		completionProductionOrderFunc
				.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
		if (!(i == 0)) {
			completionProductionOrderFunc
					.clickProcessDetailsAndSelectAllProcess(VariableHandler.getProductionOrderNumber());
		}
		completionProductionOrderFunc
				.clickCheckBoxTableAndCreateAndStoreMRNo(VariableHandler.getProductionOrderNumber());
		commonFunc.navigateToInventoryMaterialRequest();
		materialRequestFunc.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
		materialRequestFunc.verifyHeaderValidationInMaterialRequestWithDB(FileName, SheetName, StartIndex);
		materialIssuanceVoucherFunc.verifyWriteBeforeLotQuantityInLotForRawMaterials(FileName, SheetName, StartIndex);
		commonFunc.navigateToMaterialIssuanceVoucherTab();
		materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
		materialIssuanceVoucherFunc.clickheaderSaveIconInMIV();
		verifyWriteAllocateStockForRawMaterials(FileName, SheetName, AllocateIndex);
		materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
		materialIssuanceVoucherFunc.verifyHeaderAndLinesValidationInMaterialIssuanceVoucherWithDB(FileName, SheetName,
				StartIndex);
		materialIssuanceVoucherFunc.verifyStockTransactionValidationInMIVForRawMaterials(FileName, SheetName,
				StartIndex);
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			if (flag) {
				materialReceiptFunctions
						.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(VariableHandler.getProductionOrderNumber());
			} else {
				materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
			}
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
		materialIssuanceVoucherFunc.verifyAfterLotQuantityInLotSerialValidationForRawMaterials(FileName, SheetName,
				StartIndex);
		commonFunc.navigateToCompletionProductionOrderMultipleProcessInProdPlanning();
		completionProductionOrderFunc.clickPendingIconInCompletionProcess();
		if (i == 0) {
			completionProductionOrderFunc.selectPendingMultipleProdOrderAndClickCreateCompletionProcessWithNoButton(
					VariableHandler.getProductionOrderNumber());
		} else {
			completionProductionOrderFunc.selectPendingMultipleProdOrderAndClickCreateCompletionProcess(
					VariableHandler.getProductionOrderNumber());
		}
		completionProductionOrderFunc.clickAllocateDeallocateButtonInHeader();
		completionProductionOrderFunc
				.selectShiftAndSaveHeaderInCompletionMultiProcess(commonFunc.getDataFromTestDataSheet("Shift_100"));
		completionProductionOrderFunc.verifyLinesWithRMValidationInCompletionProcessWithDB(FileName, SheetName,
				StartIndex);
		completionProductionOrderFunc.clickPostButtonInCompletionProcess();
		completionProductionOrderFunc.verifyHeaderAndLinesValidationInCompletionProcessWithDB(FileName, SheetName,
				StartIndex);
		completionProductionOrderFunc.verifyStockTransactionValidationInCompletionForRawMaterials(FileName, SheetName,
				StartIndex);
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
	}

	/**
	 * This method is a following steps to complete Prod order as inside with Item
	 * type
	 * 
	 * @author Sakthi-GWL
	 * @param StartIndex
	 * @param AllocateIndex
	 * @param FileName
	 * @param SheetName
	 */
	public void productionOrderInsideItemMoveType(int StartIndex, int AllocateIndex, String FileName,
			String SheetName) {
		commonFunc.navigateToCompletionProductionOrderMultipleProcessInProdPlanning();
		completionProductionOrderFunc.clickPendingIconInCompletionProcess();
		completionProductionOrderFunc
				.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
		completionProductionOrderFunc
				.clickProcessDetailsAndSelectAllProcess(VariableHandler.getProductionOrderNumber());
		completionProductionOrderFunc
				.clickCheckBoxTableAndCreateAndStoreMRNo(VariableHandler.getProductionOrderNumber());
		commonFunc.navigateToInventoryMaterialRequest();
		materialRequestFunc.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
		materialRequestFunc.verifyHeaderValidationInMaterialRequestWithDB(FileName, SheetName, StartIndex);
		materialIssuanceVoucherFunc.verifyWriteBeforeLotQuantityInLotForRawMaterials(FileName, SheetName, StartIndex);
		commonFunc.navigateToMaterialIssuanceVoucherTab();
		materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
		materialIssuanceVoucherFunc.clickheaderSaveIconInMIV();
		verifyWriteAllocateStockForRawMaterials(FileName, SheetName, AllocateIndex);
		materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
		materialIssuanceVoucherFunc.verifyHeaderAndLinesValidationInMaterialIssuanceVoucherWithDB(FileName, SheetName,
				StartIndex);
		materialIssuanceVoucherFunc.verifyStockTransactionValidationInMIVForRawMaterials(FileName, SheetName,
				StartIndex);
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			if (flag) {
				materialReceiptFunctions
						.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(VariableHandler.getProductionOrderNumber());
			} else {
				materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
			}
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
		materialIssuanceVoucherFunc.verifyAfterLotQuantityInLotSerialValidationForRawMaterials(FileName, SheetName,
				StartIndex);
		commonFunc.navigateToCompletionProductionOrderMultipleProcessInProdPlanning();
		completionProductionOrderFunc.clickPendingIconInCompletionProcess();
		completionProductionOrderFunc.selectPendingMultipleProdOrderAndClickCreateCompletionProcess(
				VariableHandler.getProductionOrderNumber());
		completionProductionOrderFunc.clickAllocateDeallocateButtonInHeader();
		completionProductionOrderFunc
				.selectShiftAndSaveHeaderInCompletionMultiProcess(commonFunc.getDataFromTestDataSheet("Shift_100"));
		completionProductionOrderFunc.verifyLinesWithRMValidationInCompletionProcessWithDB(FileName, SheetName,
				StartIndex);
		completionProductionOrderFunc.clickPostButtonInCompletionProcess();
		completionProductionOrderFunc.verifyHeaderAndLinesValidationInCompletionProcessWithDB(FileName, SheetName,
				StartIndex);
		completionProductionOrderFunc.verifyStockTransactionValidationInCompletionForRawMaterials(FileName, SheetName,
				StartIndex);
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
	}

	/**
	 * This method is a following steps to complete Prod order as outside with
	 * Single type
	 * 
	 * @author Sakthi-GWL
	 * @param FileName
	 * @param SheetName
	 */
	public void productionOrderOutsideSingleMoveType(int StartIndex, int AllocateIndex, String FileName,
			String SheetName) {
		commonFunc.navigateToSubcontractOrder();
		subContractOrderFunc.verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
		subContractOrderFunc.verifyAddHsnCode();
		subContractOrderFunc.verifyAddGlAccNumberValue();
		subContractOrderFunc.checkSucontractOrderHeaderDetailsDataBaseValuesWithUIValues();
		subContractOrderFunc.clickLocationDetailsButtonValidationwithDataBase();
		subContractOrderFunc.checkSucontractOrderLineDetailsDataBaseValuesWithUIValues();
		subContractOrderFunc.storeBatchQtyAndBtachCost();
		subContractOrderFunc.getBatchQtyBatchCostandWriteInExcelSheet(FileName, SheetName, StartIndex);
		subContractOrderFunc.processCalculationCompareExcelSheetWithDataBase(FileName, SheetName, StartIndex);
		subContractOrderFunc.headerTaxAmtAndTotalAmtCompareExcelSheetWithDataBase(FileName, SheetName, StartIndex);
		subContractOrderFunc.verifyIncludeChargesAndPostIcon();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getScoNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			String Text = VariableHandler.getLastApprovalAPIHeaderText().replace("Material Request Created. :", "")
					.trim();
			Text = Text.replace("SUBCONTRACT ORDER Document is Approved.", "").trim();
			VariableHandler.setMRNumber(Text);
			VariableHandler.setMaterialRequestNo(Text);
		}
		commonFunc.navigateToInventoryMaterialRequest();
		materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
		materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getScoNumber());
		materialRequestFunc.headerSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		commonFunc.navigateToInventoryMaterialRequest();
		materialRequestFunc.checkStatusOfMRNo(VariableHandler.getMRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		materialRequestFunc.verifyHeaderValidationInMaterialRequestWithDB(FileName, SheetName, StartIndex);
		materialIssuanceVoucherFunc.verifyWriteBeforeLotQuantityInLotForRawMaterials(FileName, SheetName, StartIndex);
		verifyWriteAllocateStockForRawMaterials(FileName, SheetName, AllocateIndex);
		commonFunc.navigateToMaterialIssuanceVoucher();
		materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMRNumber());
		materialIssuanceVoucherFunc.headerTickAndPostIcon();
		materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceNumber(VariableHandler.getMIVNumber(),
				commonFunc.getDataFromTestDataSheet("PostedStatus"));
		materialIssuanceVoucherFunc.verifyHeaderAndLinesValidationInMaterialIssuanceVoucherWithDB(FileName, SheetName,
				StartIndex);
		commonFunc.navigateToGateEntryDeliveryChallan();
		deliveryChallanFunc.verifyStatusOfDeliveryChallan(VariableHandler.getDCNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateToGrnSco();
		grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getScoNumber());
		grnScoPageFunc.addHeatNumberAndTestNumber();
		grnScoPageFunc.checkConsumptionBeforeGenerationMat();
		grnScoPageFunc.verifyGenMatConsAndAllocate();
		grnScoPageFunc.checkConsumptionAfterGenerationMat();
		grnScoPageFunc.verifyViewLineDetails();
		grnScoPageFunc.saveHeaderAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getScoGrnNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		commonFunc.navigateToGrnSco();
		grnScoPageFunc.verifyValidateGrnScoStatus(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("BRVDocumentStatus"));
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			if (flag) {
				materialReceiptFunctions
						.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(VariableHandler.getProductionOrderNumber());
			} else {
				materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
			}
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
	}

	/**
	 * This method is a following steps to complete Prod order as outside with
	 * Multiple type
	 * 
	 * @author Sakthi-GWL
	 * @param i
	 * @param StartIndex
	 * @param FileName
	 * @param SheetName
	 */
	public void productionOrderOutsideMultipleMoveType(int i, int StartIndex, int AllocateIndex, String FileName,
			String SheetName) {
		commonFunc.navigateToSubcontractOrder();
		if (!(i == 0)) {
			subContractOrderFunc.verifySearchPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
			subContractOrderFunc.clickProcessDetailsAndSelectAllProcess(VariableHandler.getProductionOrderNumber());
		}
		subContractOrderFunc.verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
		subContractOrderFunc.verifyAddHsnCode();
		subContractOrderFunc.verifyAddGlAccNumberValue();
		subContractOrderFunc.checkSucontractOrderHeaderDetailsDataBaseValuesWithUIValues();
		subContractOrderFunc.clickLocationDetailsButtonValidationwithDataBase();
		subContractOrderFunc.checkSucontractOrderLineDetailsDataBaseValuesWithUIValues();
		subContractOrderFunc.storeBatchQtyAndBtachCost();
		subContractOrderFunc.getBatchQtyBatchCostandWriteInExcelSheet(FileName, SheetName, StartIndex);
		subContractOrderFunc.processCalculationCompareExcelSheetWithDataBase(FileName, SheetName, StartIndex);
		subContractOrderFunc.headerTaxAmtAndTotalAmtCompareExcelSheetWithDataBase(FileName, SheetName, StartIndex);
		subContractOrderFunc.verifyIncludeChargesAndPostIcon();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getScoNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			String Text = VariableHandler.getLastApprovalAPIHeaderText().replace("Material Request Created. :", "")
					.trim();
			Text = Text.replace("SUBCONTRACT ORDER Document is Approved.", "").trim();
			VariableHandler.setMRNumber(Text);
			VariableHandler.setMaterialRequestNo(Text);
		}
		commonFunc.navigateToInventoryMaterialRequest();
		materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
		materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getScoNumber());
		materialRequestFunc.headerSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		commonFunc.navigateToInventoryMaterialRequest();
		materialRequestFunc.checkStatusOfMRNo(VariableHandler.getMRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		materialRequestFunc.verifyHeaderValidationInMaterialRequestWithDB(FileName, SheetName, StartIndex);
		materialIssuanceVoucherFunc.verifyWriteBeforeLotQuantityInLotForRawMaterials(FileName, SheetName, StartIndex);
		verifyWriteAllocateStockForRawMaterials(FileName, SheetName, AllocateIndex);
		commonFunc.navigateToMaterialIssuanceVoucher();
		materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMRNumber());
		materialIssuanceVoucherFunc.headerTickAndPostIcon();
		materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceNumber(VariableHandler.getMIVNumber(),
				commonFunc.getDataFromTestDataSheet("PostedStatus"));
		materialIssuanceVoucherFunc.verifyHeaderAndLinesValidationInMaterialIssuanceVoucherWithDB(FileName, SheetName,
				StartIndex);
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			if (flag) {
				materialReceiptFunctions
						.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(VariableHandler.getProductionOrderNumber());
			} else {
				materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
			}
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
		commonFunc.navigateToGateEntryDeliveryChallan();
		deliveryChallanFunc.verifyStatusOfDeliveryChallan(VariableHandler.getDCNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateToGrnSco();
		grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getScoNumber());
		grnScoPageFunc.addHeatNumberAndTestNumber();
		grnScoPageFunc.checkConsumptionBeforeGenerationMat();
		grnScoPageFunc.verifyGenMatConsAndAllocate();
		grnScoPageFunc.checkConsumptionAfterGenerationMat();
		grnScoPageFunc.verifyViewLineDetails();
		grnScoPageFunc.saveHeaderAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getScoGrnNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		commonFunc.navigateToGrnSco();
		grnScoPageFunc.verifyValidateGrnScoStatus(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("BRVDocumentStatus"));
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			if (flag) {
				materialReceiptFunctions
						.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(VariableHandler.getProductionOrderNumber());
			} else {
				materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
			}
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
	}

	/**
	 * This method is a following steps to complete Prod order as outside with Item
	 * type
	 * 
	 * @author Sakthi-GWL
	 * @param FileName
	 * @param SheetName
	 */
	public void productionOrderOutsideItemMoveType(int StartIndex, int AllocateIndex, String FileName,
			String SheetName) {
		commonFunc.navigateToSubcontractOrder();
		subContractOrderFunc.verifySearchPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
		subContractOrderFunc.clickProcessDetailsAndSelectAllProcess(VariableHandler.getProductionOrderNumber());
		subContractOrderFunc.verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
		subContractOrderFunc.verifyAddHsnCode();
		subContractOrderFunc.verifyAddGlAccNumberValue();
		subContractOrderFunc.checkSucontractOrderHeaderDetailsDataBaseValuesWithUIValues();
		subContractOrderFunc.clickLocationDetailsButtonValidationwithDataBase();
		subContractOrderFunc.checkSucontractOrderLineDetailsDataBaseValuesWithUIValues();
		subContractOrderFunc.storeBatchQtyAndBtachCost();
		subContractOrderFunc.getBatchQtyBatchCostandWriteInExcelSheet(FileName, SheetName, StartIndex);
		subContractOrderFunc.processCalculationCompareExcelSheetWithDataBase(FileName, SheetName, StartIndex);
		subContractOrderFunc.headerTaxAmtAndTotalAmtCompareExcelSheetWithDataBase(FileName, SheetName, StartIndex);
		subContractOrderFunc.verifyIncludeChargesAndPostIcon();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getScoNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			String Text = VariableHandler.getLastApprovalAPIHeaderText().replace("Material Request Created. :", "")
					.trim();
			Text = Text.replace("SUBCONTRACT ORDER Document is Approved.", "").trim();
			VariableHandler.setMRNumber(Text);
			VariableHandler.setMaterialRequestNo(Text);
		}
		subContractOrderFunc.verifyValidateSubContractDocumentsStatus(VariableHandler.getScoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateToInventoryMaterialRequest();
		materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
		materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getScoNumber());
		materialRequestFunc.headerSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		commonFunc.navigateToInventoryMaterialRequest();
		materialRequestFunc.checkStatusOfMRNo(VariableHandler.getMRNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		materialRequestFunc.verifyHeaderValidationInMaterialRequestWithDB(FileName, SheetName, StartIndex);
		materialIssuanceVoucherFunc.verifyWriteBeforeLotQuantityInLotForRawMaterials(FileName, SheetName, StartIndex);
		verifyWriteAllocateStockForRawMaterials(FileName, SheetName, AllocateIndex);
		commonFunc.navigateToMaterialIssuanceVoucher();
		materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMRNumber());
		materialIssuanceVoucherFunc.headerTickAndPostIcon();
		materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceNumber(VariableHandler.getMIVNumber(),
				commonFunc.getDataFromTestDataSheet("PostedStatus"));
		materialIssuanceVoucherFunc.verifyHeaderAndLinesValidationInMaterialIssuanceVoucherWithDB(FileName, SheetName,
				StartIndex);
		commonFunc.navigateToGateEntryDeliveryChallan();
		deliveryChallanFunc.verifyStatusOfDeliveryChallan(VariableHandler.getDCNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateToGrnSco();
		grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getScoNumber());
		grnScoPageFunc.addHeatNumberAndTestNumber();
		grnScoPageFunc.checkConsumptionBeforeGenerationMat();
		grnScoPageFunc.verifyGenMatConsAndAllocate();
		grnScoPageFunc.checkConsumptionAfterGenerationMat();
		grnScoPageFunc.verifyViewLineDetails();
		grnScoPageFunc.saveHeaderAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getScoGrnNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
		}
		commonFunc.navigateToGrnSco();
		grnScoPageFunc.verifyValidateGrnScoStatus(VariableHandler.getScoGrnNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			if (flag) {
				materialReceiptFunctions
						.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(VariableHandler.getProductionOrderNumber());
			} else {
				materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
			}
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Single
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllInsideMoveTypeSingle() {
		try {
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Single"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Single"));
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Single"));
			verifyRawMaterialsValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Single"));
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HariEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
			int AllocStartIndex = 0;
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndexSheet = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
				AllocStartIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				productionOrderInsideSingleMoveType(StartIndexSheet, AllocStartIndex,
						webDB.getDataFromProperties("PMFFileName"),
						webDB.getDataFromProperties("AllInsideSheetName-Single"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Single"));
			return true;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllInsideMoveTypeSingle': " + e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Multiple
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllInsideMoveTypeMultiple() {
		try {
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Multiple"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Multiple"));
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("MultipleType"));
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Multiple"));
			verifyRawMaterialsValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Multiple"));
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
			int AllocStartIndex = 0;
			for (int i = 0; i < 2; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndexSheet = (i == 0) ? 35 : (i == 1) ? 45 : 0;
				AllocStartIndex = (i == 0) ? 10 : (i == 1) ? 17 : 0;
				productionOrderInsideMultipleMoveType(i, StartIndexSheet, AllocStartIndex,
						webDB.getDataFromProperties("PMFFileName"),
						webDB.getDataFromProperties("AllInsideSheetName-Multiple"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Multiple"));
			return true;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllInsideMoveTypeMultiple': " + e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Item
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllInsideMoveTypeItem() {
		try {
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Item"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Item"));
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("ItemType"));
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Item"));
			verifyRawMaterialsValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Item"));
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
			int AllocStartIndex = 0;
			for (int i = 0; i < 1; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndexSheet = (i == 0) ? 35 : 0;
				AllocStartIndex = (i == 0) ? 10 : 0;
				productionOrderInsideItemMoveType(StartIndexSheet, AllocStartIndex,
						webDB.getDataFromProperties("PMFFileName"),
						webDB.getDataFromProperties("AllInsideSheetName-Item"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Item"));
			return true;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllInsideMoveTypeItem': " + e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Single
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllOutsideMoveTypeSingle() {
		try {
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Single"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllOutside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Single"));
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Single"));
			verifyRawMaterialsValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Single"));
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
			int AllocStartIndex = 0;
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndexSheet = (i == 0) ? 35 : 0;
				AllocStartIndex = (i == 0) ? 10 : 0;
				productionOrderOutsideSingleMoveType(StartIndexSheet, AllocStartIndex,
						webDB.getDataFromProperties("PMFFileName"),
						webDB.getDataFromProperties("AllOutsideSheetName-Single"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Single"));
			return true;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllOutsideMoveTypeSingle': " + e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Multiple
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllOutsideMoveTypeMultiple() {
		try {
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Multiple"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllOutside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Multiple"));
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("MultipleType"));
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Multiple"));
			verifyRawMaterialsValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Multiple"));
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
			int AllocStartIndex = 0;
			for (int i = 0; i < 2; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				StartIndexSheet = (i == 0) ? 35 : 0;
				AllocStartIndex = (i == 0) ? 10 : 0;
				productionOrderOutsideSingleMoveType(StartIndexSheet, AllocStartIndex,
						webDB.getDataFromProperties("PMFFileName"),
						webDB.getDataFromProperties("AllOutsideSheetName-Single"));
				if (!(i == 0)) {
					productionOrderOutsideMultipleMoveType(i, StartIndexSheet, AllocStartIndex,
							webDB.getDataFromProperties("PMFFileName"),
							webDB.getDataFromProperties("AllOutsideSheetName-Multiple"));
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Multiple"));
			return true;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllOutsideMoveTypeMultiple': " + e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Item
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllOutsideMoveTypeItem() {
		try {
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Item"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllOutside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Item"));
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("ItemType"));
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Item"));
			verifyRawMaterialsValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Item"));
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndex = 0;
			int AllocIndex = 0;
			for (int i = 0; i < 1; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
				AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				productionOrderOutsideItemMoveType(StartIndex, AllocIndex, webDB.getDataFromProperties("PMFFileName"),
						webDB.getDataFromProperties("AllOutsideSheetName-Item"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Item"));
			return true;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllOutsideMoveTypeItem': " + e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Single
	 * 
	 * @author Sakthi-GWL
	 * @return flag
	 */
	public boolean verifyProdOrderWithoutSOFirstInsideRestOutsideMoveTypeSingle() {
		String MoveType = commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus");
		String FileName = webDB.getDataFromProperties("PMFFileName");
		String SheetName = webDB.getDataFromProperties("FirstInRestOutSheetName-Single");
		try {
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("FirstInRestOutSheetName-Single"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomFirstInRestOut"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(FileName, SheetName);
			verifyProductionOrderMREventAndRestOP(null, null, MoveType);
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(FileName, SheetName);
			verifyRawMaterialsValidationInProductionOrderWithDB(FileName, SheetName);
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndex = 0;
			int AllocIndex = 0;
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
				AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))) {
					productionOrderInsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				}
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					productionOrderOutsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(FileName, SheetName);
			return true;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOFirstInsideRestOutsideMoveTypeSingle': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Multiple
	 * 
	 * @author Sakthi-GWL
	 * @return flag
	 */
	public boolean verifyProdOrderWithoutSOFirstInsideRestOutsideMoveTypeMultiple() {
		String MoveType = commonFunc.getDataFromTestDataSheet("MultipleType");
		String FileName = webDB.getDataFromProperties("PMFFileName");
		String SheetName = webDB.getDataFromProperties("FirstInRestOutSheetName-Multiple");
		try {
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("FirstInRestOutSheetName-Multiple"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomFirstInRestOut"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(FileName, SheetName);
			verifyProductionOrderMREventAndRestOP(null, null, MoveType);
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(FileName, SheetName);
			verifyRawMaterialsValidationInProductionOrderWithDB(FileName, SheetName);
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndex = 0;
			int AllocIndex = 0;
			for (int i = 0; i < 2; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
				AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))) {
					productionOrderInsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
				}
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					productionOrderOutsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(FileName, SheetName);
			return true;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOFirstInsideRestOutsideMoveTypeMultiple': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Item
	 * 
	 * @author Sakthi-GWL
	 * @return flag
	 */
	public boolean verifyProdOrderWithoutSOFirstInsideRestOutsideMoveTypeItem() {
		String MoveType = commonFunc.getDataFromTestDataSheet("ItemType");
		String FileName = webDB.getDataFromProperties("PMFFileName");
		String SheetName = webDB.getDataFromProperties("FirstInRestOutSheetName-Item");
		try {
			commonFunc.navigateToProductionOrderInProdPlanning();
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("FirstInRestOutSheetName-Item"));
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomFirstInRestOut"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(FileName, SheetName);
			verifyProductionOrderMREventAndRestOP(null, null, MoveType);
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(FileName, SheetName);
			verifyRawMaterialsValidationInProductionOrderWithDB(FileName, SheetName);
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndex = 0;
			int AllocIndex = 0;
			for (int i = 0; i < 2; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))) {
					StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
				}
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					productionOrderOutsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(FileName, SheetName);
			return true;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOFirstInsideRestOutsideMoveTypeItem': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Single
	 * 
	 * @author Sakthi-GWL
	 * @return flag
	 */
	public boolean verifyProdOrderWithoutSOFirstOutsideRestInsideMoveTypeSingle() {
		String MoveType = commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus");
		String FileName = webDB.getDataFromProperties("PMFFileName");
		String SheetName = webDB.getDataFromProperties("FirstOutRestInSheetName-Single");
		try {
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("FirstInRestOutSheetName-Single"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomFirstOutRestIn"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(FileName, SheetName);
			verifyProductionOrderMREventAndRestOP(null, null, MoveType);
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(FileName, SheetName);
			verifyRawMaterialsValidationInProductionOrderWithDB(FileName, SheetName);
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndex = 0;
			int AllocIndex = 0;
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : 0;
				AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))) {
					productionOrderInsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				}
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					productionOrderOutsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(FileName, SheetName);
			return true;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOFirstOutsideRestInsideMoveTypeSingle': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Multiple
	 * 
	 * @author Sakthi-GWL
	 * @return flag
	 */
	public boolean verifyProdOrderWithoutSOFirstOutsideRestInsideMoveTypeMultiple() {
		String MoveType = commonFunc.getDataFromTestDataSheet("MultipleType");
		String FileName = webDB.getDataFromProperties("PMFFileName");
		String SheetName = webDB.getDataFromProperties("FirstOutRestInSheetName-Multiple");
		try {
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("FirstInRestOutSheetName-Single"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomFirstOutRestIn"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(FileName, SheetName);
			verifyProductionOrderMREventAndRestOP(null, null, MoveType);
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(FileName, SheetName);
			verifyRawMaterialsValidationInProductionOrderWithDB(FileName, SheetName);
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndex = 0;
			int AllocIndex = 0;
			for (int i = 0; i < 2; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : 0;
				AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))) {
					productionOrderInsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
				}
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					productionOrderOutsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(FileName, SheetName);
			return true;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOFirstOutsideRestInsideMoveTypeMultiple': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Item
	 * 
	 * @author Sakthi-GWL
	 * @return flag
	 */
	public boolean verifyProdOrderWithoutSOFirstOutsideRestInsideMoveTypeItem() {
		String MoveType = commonFunc.getDataFromTestDataSheet("ItemType");
		String FileName = webDB.getDataFromProperties("PMFFileName");
		String SheetName = webDB.getDataFromProperties("FirstOutRestInSheetName-Item");
		try {
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("FirstInRestOutSheetName-Single"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomFirstOutRestIn"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(FileName, SheetName);
			verifyProductionOrderMREventAndRestOP(null, null, MoveType);
			verifyProcessType(commonFunc.getDataFromTestDataSheet("OutsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(FileName, SheetName);
			verifyRawMaterialsValidationInProductionOrderWithDB(FileName, SheetName);
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndex = 0;
			int AllocIndex = 0;
			for (int i = 0; i < 2; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : 0;
				AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))) {
					productionOrderInsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
				}
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))) {
					productionOrderOutsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(FileName, SheetName);
			return true;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOFirstOutsideRestInsideMoveTypeItem': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Single
	 * 
	 * @author Sakthi-GWL
	 * @return flag
	 */
	public boolean verifyProdOrderWithoutSOFirstOutsideRestBothMoveTypeSingle() {
		String MoveType = commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus");
		String FileName = webDB.getDataFromProperties("PMFFileName");
		String SheetName = webDB.getDataFromProperties("FirstOutRestBothSheetName-Single");
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("FirstOutRestBothSheetName-Single"));
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomFirstOutRestBoth"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(FileName, SheetName);
			verifyProductionOrderMREventAndRestOP(null, null, MoveType);
			verifyProcessType(commonFunc.getDataFromTestDataSheet("OutsideType"),
					commonFunc.getDataFromTestDataSheet("BothType"));
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(FileName, SheetName);
			verifyRawMaterialsValidationInProductionOrderWithDB(FileName, SheetName);
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndex = 0;
			int AllocIndex = 0;
			a: for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				if (ProcessType == commonFunc.getDataFromTestDataSheet("BothType")) {
					Random random = new Random();
					boolean randomValue = random.nextBoolean();
					if (randomValue == true) {
						ProcessType = commonFunc.getDataFromTestDataSheet("InsideType");
					} else {
						ProcessType = commonFunc.getDataFromTestDataSheet("OutsideType");
					}
				}
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"))) {
					StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("MultipleType"))) {
					StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
					if (i > 1) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ItemType"))) {
					StartIndex = (i == 0) ? 35 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideItemMoveType(StartIndex, AllocIndex, FileName, SheetName);
					if (i > 0) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"))) {
					productionOrderOutsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("MultipleType"))) {
					productionOrderOutsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
					if (i > 1) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ItemType"))) {
					productionOrderOutsideItemMoveType(StartIndex, AllocIndex, FileName, SheetName);
					if (i > 0) {
						break a;
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(FileName, SheetName);
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOFirstOutsideRestBothMoveTypeSingle': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Multiple
	 * 
	 * @author Sakthi-GWL
	 * @return flag
	 */
	public boolean verifyProdOrderWithoutSOFirstOutsideRestBothMoveTypeMultiple() {
		String MoveType = commonFunc.getDataFromTestDataSheet("MultipleType");
		String FileName = webDB.getDataFromProperties("PMFFileName");
		String SheetName = webDB.getDataFromProperties("FirstOutRestBothSheetName-Multiple");
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("FirstOutRestBothSheetName-Single"));
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomFirstOutRestBoth"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(FileName, SheetName);
			verifyProductionOrderMREventAndRestOP(null, null, MoveType);
			verifyProcessType(commonFunc.getDataFromTestDataSheet("OutsideType"),
					commonFunc.getDataFromTestDataSheet("BothType"));
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(FileName, SheetName);
			verifyRawMaterialsValidationInProductionOrderWithDB(FileName, SheetName);
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndex = 0;
			int AllocIndex = 0;
			a: for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				if (ProcessType == commonFunc.getDataFromTestDataSheet("BothType")) {
					Random random = new Random();
					boolean randomValue = random.nextBoolean();
					if (randomValue == true) {
						ProcessType = commonFunc.getDataFromTestDataSheet("InsideType");
					} else {
						ProcessType = commonFunc.getDataFromTestDataSheet("OutsideType");
					}
				}
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"))) {
					StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("MultipleType"))) {
					StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
					if (i > 1) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ItemType"))) {
					StartIndex = (i == 0) ? 35 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideItemMoveType(StartIndex, AllocIndex, FileName, SheetName);
					if (i > 0) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"))) {
					productionOrderOutsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("MultipleType"))) {
					productionOrderOutsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
					if (i > 1) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ItemType"))) {
					productionOrderOutsideItemMoveType(StartIndex, AllocIndex, FileName, SheetName);
					if (i > 0) {
						break a;
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(FileName, SheetName);
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOFirstOutsideRestBothMoveTypeMultiple': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Item
	 * 
	 * @author Sakthi-GWL
	 * @return flag
	 */
	public boolean verifyProdOrderWithoutSOFirstOutsideRestBothMoveTypeItem() {
		String MoveType = commonFunc.getDataFromTestDataSheet("ItemType");
		String FileName = webDB.getDataFromProperties("PMFFileName");
		String SheetName = webDB.getDataFromProperties("FirstOutRestBothSheetName-Item");
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("FirstInRestOutSheetName-Single"));
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomFirstOutRestBoth"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(FileName, SheetName);
			verifyProductionOrderMREventAndRestOP(null, null, MoveType);
			verifyProcessType(commonFunc.getDataFromTestDataSheet("OutsideType"),
					commonFunc.getDataFromTestDataSheet("BothType"));
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(FileName, SheetName);
			verifyRawMaterialsValidationInProductionOrderWithDB(FileName, SheetName);
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndex = 0;
			int AllocIndex = 0;
			a: for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				if (ProcessType == commonFunc.getDataFromTestDataSheet("BothType")) {
					Random random = new Random();
					boolean randomValue = random.nextBoolean();
					if (randomValue == true) {
						ProcessType = commonFunc.getDataFromTestDataSheet("InsideType");
					} else {
						ProcessType = commonFunc.getDataFromTestDataSheet("OutsideType");
					}
				}
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"))) {
					StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("MultipleType"))) {
					StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
					if (i > 1) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ItemType"))) {
					StartIndex = (i == 0) ? 35 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideItemMoveType(StartIndex, AllocIndex, FileName, SheetName);
					if (i > 0) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"))) {
					productionOrderOutsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("MultipleType"))) {
					productionOrderOutsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
					if (i > 1) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ItemType"))) {
					productionOrderOutsideItemMoveType(StartIndex, AllocIndex, FileName, SheetName);
					if (i > 0) {
						break a;
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(FileName, SheetName);
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOFirstOutsideRestBothMoveTypeItem': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Single
	 * 
	 * @author Sakthi-GWL
	 * @return flag
	 */
	public boolean verifyProdOrderWithoutSOAllBothPartialQuantityMoveTypeSingle() {
		String MoveType = commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus");
		String FileName = webDB.getDataFromProperties("PMFFileName");
		String SheetName = webDB.getDataFromProperties("AllBothParQuantitySheetName-Single");
		int ReqQuantity = 4;
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			verifyWriteBeforeStockForFGAndRawMaterials(FileName, SheetName);
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllBoth"), String.valueOf(ReqQuantity));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(FileName, SheetName);
			verifyProductionOrderMREventAndRestOP(null, null, MoveType);
			verifyProcessType(commonFunc.getDataFromTestDataSheet("BothType"),
					commonFunc.getDataFromTestDataSheet("BothType"));
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(FileName, SheetName);
			verifyRawMaterialsValidationInProductionOrderWithDB(FileName, SheetName);
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndex = 0;
			int AllocIndex = 0;
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc
						.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc.enterRequestQuantityInPendingMRCreation(
						VariableHandler.getProductionOrderNumber(), String.valueOf((ReqQuantity / 2)));
				completionProductionOrderFunc
						.clickCheckBoxTableAndCreateAndStoreMRNo(VariableHandler.getProductionOrderNumber());
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc
						.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
				materialRequestFunc.verifyHeaderValidationInMaterialRequestWithDB(FileName, SheetName, StartIndex);
				materialIssuanceVoucherFunc.verifyWriteBeforeLotQuantityInLotForRawMaterials(FileName, SheetName,
						StartIndex);
				commonFunc.navigateToMaterialIssuanceVoucherTab();
				materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
				materialIssuanceVoucherFunc.clickheaderSaveIconInMIV();
				verifyWriteAllocateStockForRawMaterials(FileName, SheetName, AllocIndex);
				materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
				commonFunc.navigateToMaterialIssuanceVoucherTab();
				materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				materialIssuanceVoucherFunc.verifyHeaderAndLinesValidationInMaterialIssuanceVoucherWithDB(FileName,
						SheetName, StartIndex);
				materialIssuanceVoucherFunc.verifyStockTransactionValidationInMIVForRawMaterials(FileName, SheetName,
						StartIndex);
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {
						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
				materialIssuanceVoucherFunc.verifyAfterLotQuantityInLotSerialValidationForRawMaterials(FileName,
						SheetName, StartIndex);
				materialIssuanceVoucherFunc.verifyStockTransactionValidationInMIVForRawMaterials(FileName, SheetName,
						StartIndex);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
						commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc.clickAllocateDeallocateButtonInHeader();
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
				completionProductionOrderFunc.verifyLinesWithRMValidationInCompletionProcessWithDB(FileName, SheetName,
						StartIndex);
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
				completionProductionOrderFunc.verifyHeaderAndLinesValidationInCompletionProcessWithDB(FileName,
						SheetName, StartIndex);
				completionProductionOrderFunc.verifyStockTransactionValidationInCompletionForRawMaterials(FileName,
						SheetName, StartIndex);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
						VariableHandler.getProductionCompletionOrderNo(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
				// Outside
				commonFunc.navigateToSubcontractOrder();
				subContractOrderFunc.verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
				subContractOrderFunc.verifyHeaderValidation(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
						commonFunc.getDataFromTestDataSheet("Unit_100"),
						commonFunc.getDataFromTestDataSheet("SubcontractorName"));
				subContractOrderFunc.verifyProcessQuantityCalculation();
				subContractOrderFunc.verifyAddHsnCode();
				subContractOrderFunc.verifyAddGlAccNumberValue();
				subContractOrderFunc.verifyIncludeChargesAndPostIcon();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getScoNumber(),
							webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
							System.getProperty("HariUsername"));
					String Text = VariableHandler.getLastApprovalAPIHeaderText()
							.replace("Material Request Created. :", "").trim();
					Text = Text.replace("SUBCONTRACT ORDER Document is Approved.", "").trim();
					VariableHandler.setMRNumber(Text);
					VariableHandler.setMaterialRequestNo(Text);
				}
				subContractOrderFunc.verifyValidateSubContractDocumentsStatus(VariableHandler.getScoNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
				materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getScoNumber());
				materialRequestFunc.headerSaveAndPost();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRNumber(),
							webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
							System.getProperty("HariUsername"));
				}
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc.checkStatusOfMRNo(VariableHandler.getMRNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.navigateToMaterialIssuanceVoucher();
				materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMRNumber());
				materialIssuanceVoucherFunc.headerTickAndPostIcon();
				materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceNumber(VariableHandler.getMIVNumber(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				commonFunc.navigateToGateEntryDeliveryChallan();
				deliveryChallanFunc.verifyStatusOfDeliveryChallan(VariableHandler.getDCNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getScoNumber());
				grnScoPageFunc.addHeatNumberAndTestNumber();
				grnScoPageFunc.checkConsumptionBeforeGenerationMat();
				grnScoPageFunc.verifyGenMatConsAndAllocate();
				grnScoPageFunc.checkConsumptionAfterGenerationMat();
				grnScoPageFunc.verifyViewLineDetails();
				grnScoPageFunc.saveHeaderAndPost();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
							VariableHandler.getScoGrnNumber(), webDB.getDataFromProperties("HodEmpId"),
							commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
				}
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.verifyValidateGrnScoStatus(VariableHandler.getScoGrnNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {
						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(FileName, SheetName);
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllBothPartialQuantityMoveTypeSingle': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Multiple
	 * 
	 * @author Sakthi-GWL
	 * @return flag
	 */
	public boolean verifyProdOrderWithoutSOAllBothPartialQuantityMoveTypeMultiple() {
		String MoveType = commonFunc.getDataFromTestDataSheet("MultipleType");
		String FileName = webDB.getDataFromProperties("PMFFileName");
		String SheetName = webDB.getDataFromProperties("AllBothParQuantitySheetName-Multiple");
		int ReqQuantity = 4;
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllBothParQuantitySheetName-Multiple"));
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllBoth"), String.valueOf(ReqQuantity));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(FileName, SheetName);
			verifyProductionOrderMREventAndRestOP(null, null, MoveType);
			verifyProcessType(commonFunc.getDataFromTestDataSheet("BothType"),
					commonFunc.getDataFromTestDataSheet("BothType"));
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(FileName, SheetName);
			verifyRawMaterialsValidationInProductionOrderWithDB(FileName, SheetName);
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndex = 0;
			int AllocIndex = 0;
			a: for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : 0;
				AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				// Inside
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc.enterRequestQuantityInPendingMRCreation(
						VariableHandler.getProductionOrderNumber(), String.valueOf((ReqQuantity / 2)));
				completionProductionOrderFunc
						.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
				if (!(i == 0)) {
					completionProductionOrderFunc
							.clickProcessDetailsAndSelectAllProcess(VariableHandler.getProductionOrderNumber());
				}
				completionProductionOrderFunc
						.clickCheckBoxTableAndCreateAndStoreMRNo(VariableHandler.getProductionOrderNumber());
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc
						.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
				materialRequestFunc.verifyHeaderValidationInMaterialRequestWithDB(FileName, SheetName, StartIndex);
				materialIssuanceVoucherFunc.verifyWriteBeforeLotQuantityInLotForRawMaterials(FileName, SheetName,
						StartIndex);
				commonFunc.navigateToMaterialIssuanceVoucherTab();
				materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
				materialIssuanceVoucherFunc.clickheaderSaveIconInMIV();
				if (!(i == 0)) {
					AllocIndex = 61;
				}
				verifyWriteAllocateStockForRawMaterials(FileName, SheetName, AllocIndex);
				materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
				commonFunc.navigateToMaterialIssuanceVoucherTab();
				materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				if (!(i == 0)) {
					StartIndex = 61;
				}
				materialIssuanceVoucherFunc.verifyHeaderAndLinesValidationInMaterialIssuanceVoucherWithDB(FileName,
						SheetName, StartIndex);
				materialIssuanceVoucherFunc.verifyStockTransactionValidationInMIVForRawMaterials(FileName, SheetName,
						StartIndex);
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {
						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
				materialIssuanceVoucherFunc.verifyAfterLotQuantityInLotSerialValidationForRawMaterials(FileName,
						SheetName, StartIndex);
				materialIssuanceVoucherFunc.verifyStockTransactionValidationInMIVForRawMaterials(FileName, SheetName,
						StartIndex);
				commonFunc.navigateToCompletionProductionOrderMultipleProcessInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionMultiProcess();
				if (i == 0) {
					completionProductionOrderFunc
							.selectPendingMultipleProdOrderAndClickCreateCompletionProcessWithNoButton(
									VariableHandler.getProductionOrderNumber());
				} else {
					completionProductionOrderFunc.selectPendingMultipleProdOrderAndClickCreateCompletionProcess(
							VariableHandler.getProductionOrderNumber());
				}
				completionProductionOrderFunc.clickAllocateDeallocateButtonInHeader();
				completionProductionOrderFunc.selectShiftAndSaveHeaderInCompletionMultiProcess(
						commonFunc.getDataFromTestDataSheet("Shift_100"));
				completionProductionOrderFunc.verifyLinesWithRMValidationInCompletionProcessWithDB(FileName, SheetName,
						StartIndex);
				completionProductionOrderFunc.clickPostButtonInCompletionMultiProcess();
				completionProductionOrderFunc.verifyHeaderAndLinesValidationInCompletionProcessWithDB(FileName,
						SheetName, StartIndex);
				completionProductionOrderFunc.verifyStockTransactionValidationInCompletionForRawMaterials(FileName,
						SheetName, StartIndex);
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
				if (i > 1) {
					break a;
				}
				// Outside
				commonFunc.navigateToSubcontractOrder();
				if (!(i == 0)) {
					subContractOrderFunc
							.verifySearchPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
					subContractOrderFunc
							.clickProcessDetailsAndSelectAllProcess(VariableHandler.getProductionOrderNumber());
				}
				subContractOrderFunc.verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
				subContractOrderFunc.verifyHeaderValidation(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
						commonFunc.getDataFromTestDataSheet("Unit_100"),
						commonFunc.getDataFromTestDataSheet("SubcontractorName"));
				subContractOrderFunc.verifyProcessQuantityCalculation();
				subContractOrderFunc.verifyAddHsnCode();
				subContractOrderFunc.verifyAddGlAccNumberValue();
				subContractOrderFunc.checkSucontractOrderHeaderDetailsDataBaseValuesWithUIValues();
				subContractOrderFunc.clickLocationDetailsButtonValidationwithDataBase();
				subContractOrderFunc.checkSucontractOrderLineDetailsDataBaseValuesWithUIValues();
				subContractOrderFunc.storeBatchQtyAndBtachCost();
				subContractOrderFunc.getBatchQtyBatchCostandWriteInExcelSheet(FileName, SheetName, StartIndex);
				subContractOrderFunc.processCalculationCompareExcelSheetWithDataBase(FileName, SheetName, StartIndex);
				subContractOrderFunc.clickingMRPDetailsTabVerificationWithBOMAgainst();
				subContractOrderFunc.ValueAdditionProcessSourceAndTargetSFcode();
				subContractOrderFunc.pendingProdNoandSOrefsubcontractStandardTypeclickingIncludeChargesAndPostIcon();
				subContractOrderFunc.headerTaxAmtAndTotalAmtCompareExcelSheetWithDataBase(FileName, SheetName,
						StartIndex);
				subContractOrderFunc.verifyIncludeChargesAndPostIcon();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getScoNumber(),
							webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
							System.getProperty("HariUsername"));
					String Text = VariableHandler.getLastApprovalAPIHeaderText()
							.replace("Material Request Created. :", "").trim();
					Text = Text.replace("SUBCONTRACT ORDER Document is Approved.", "").trim();
					VariableHandler.setMRNumber(Text);
					VariableHandler.setMaterialRequestNo(Text);
				}
				subContractOrderFunc.verifyValidateSubContractDocumentsStatus(VariableHandler.getScoNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
				materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getScoNumber());
				materialRequestFunc.headerSaveAndPost();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRNumber(),
							webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
							System.getProperty("HariUsername"));
				}
				materialRequestFunc.verifyHeaderValidationInMaterialRequestWithDB(FileName, SheetName, StartIndex);
				commonFunc.navigateToMaterialIssuanceVoucherTab();
				materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
				materialIssuanceVoucherFunc.clickheaderSaveIconInMIV();
				materialIssuanceVoucherFunc.headerTickAndPostIcon();
				commonFunc.navigateToMaterialIssuanceVoucherTab();
				materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {
						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
				materialIssuanceVoucherFunc.verifyAfterLotQuantityInLotSerialValidationForRawMaterials(FileName,
						SheetName, StartIndex);
				materialIssuanceVoucherFunc.verifyStockTransactionValidationInMIVForRawMaterials(FileName, SheetName,
						StartIndex);
				commonFunc.navigateToGateEntryDeliveryChallan();
				deliveryChallanFunc.verifyStatusOfDeliveryChallan(VariableHandler.getDCNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getScoNumber());
				grnScoPageFunc.addHeatNumberAndTestNumber();
				grnScoPageFunc.checkConsumptionBeforeGenerationMat();
				grnScoPageFunc.verifyGenMatConsAndAllocate();
				grnScoPageFunc.checkConsumptionAfterGenerationMat();
				grnScoPageFunc.verifyViewLineDetails();
				grnScoPageFunc.saveHeaderAndPost();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
							VariableHandler.getScoGrnNumber(), webDB.getDataFromProperties("HodEmpId"),
							commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
				}
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.verifyValidateGrnScoStatus(VariableHandler.getScoGrnNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {
						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(FileName, SheetName);
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllBothPartialQuantityMoveTypeMultiple': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Item
	 * 
	 * @author Sakthi-GWL
	 * @return flag
	 */
	public boolean verifyProdOrderWithoutSOAllBothPartialQuantityMoveTypeItem() {
		String MoveType = commonFunc.getDataFromTestDataSheet("ItemType");
		String FileName = webDB.getDataFromProperties("PMFFileName");
		String SheetName = webDB.getDataFromProperties("AllBothParQuantitySheetName-Item");
		int ReqQuantity = 4;
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllBothParQuantitySheetName-Item"));
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllBoth"), String.valueOf(ReqQuantity));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(FileName, SheetName);
			verifyProductionOrderMREventAndRestOP(null, null, MoveType);
			verifyProcessType(commonFunc.getDataFromTestDataSheet("BothType"),
					commonFunc.getDataFromTestDataSheet("BothType"));
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(FileName, SheetName);
			verifyRawMaterialsValidationInProductionOrderWithDB(FileName, SheetName);
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndex = 0;
			int AllocIndex = 0;
			a: for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc
						.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc.enterRequestQuantityInPendingMRCreation(
						VariableHandler.getProductionOrderNumber(), String.valueOf((ReqQuantity / 2)));
				completionProductionOrderFunc
						.clickProcessDetailsAndSelectAllProcess(VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc
						.clickCheckBoxTableAndCreateAndStoreMRNo(VariableHandler.getProductionOrderNumber());
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc
						.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
				materialRequestFunc.verifyHeaderValidationInMaterialRequestWithDB(FileName, SheetName, StartIndex);
				materialIssuanceVoucherFunc.verifyWriteBeforeLotQuantityInLotForRawMaterials(FileName, SheetName,
						StartIndex);
				commonFunc.navigateToMaterialIssuanceVoucherTab();
				materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
				materialIssuanceVoucherFunc.clickheaderSaveIconInMIV();
				verifyWriteAllocateStockForRawMaterials(FileName, SheetName, AllocIndex);
				materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
				commonFunc.navigateToMaterialIssuanceVoucherTab();
				materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				int MivStartIndex = 62;
				materialIssuanceVoucherFunc.verifyHeaderAndLinesValidationInMaterialIssuanceVoucherWithDB(FileName,
						SheetName, MivStartIndex);
				materialIssuanceVoucherFunc.verifyStockTransactionValidationInMIVForRawMaterials(FileName, SheetName,
						MivStartIndex);
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {
						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
				materialIssuanceVoucherFunc.verifyAfterLotQuantityInLotSerialValidationForRawMaterials(FileName,
						SheetName, StartIndex);
				materialIssuanceVoucherFunc.verifyStockTransactionValidationInMIVForRawMaterials(FileName, SheetName,
						StartIndex);
				commonFunc.navigateToCompletionProductionOrderMultipleProcessInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionMultiProcess();
				completionProductionOrderFunc
						.searchMultipleProductionOrderNoAndVerifyFilter(VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc.selectPendingMultipleProdOrderAndClickCreateCompletionProcess(
						VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
				completionProductionOrderFunc.verifyLinesWithRMValidationInCompletionProcessWithDB(FileName, SheetName,
						StartIndex);
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
				completionProductionOrderFunc.verifyHeaderAndLinesValidationInCompletionProcessWithDB(FileName,
						SheetName, StartIndex);
				completionProductionOrderFunc.verifyStockTransactionValidationInCompletionForRawMaterials(FileName,
						SheetName, StartIndex);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
						VariableHandler.getProductionCompletionOrderNo(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
				StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : 0;
				AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				productionOrderInsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
				if (i > 1) {
					break a;
				}
				// Outside
				commonFunc.navigateToSubcontractOrder();
				subContractOrderFunc.verifySearchPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
				subContractOrderFunc.clickProcessDetailsAndSelectAllProcess(VariableHandler.getProductionOrderNumber());
				subContractOrderFunc.verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
				subContractOrderFunc.verifyHeaderValidation(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
						commonFunc.getDataFromTestDataSheet("Unit_100"),
						commonFunc.getDataFromTestDataSheet("SubcontractorName"));
				subContractOrderFunc.verifyProcessQuantityCalculation();
				subContractOrderFunc.verifyAddHsnCode();
				subContractOrderFunc.verifyAddGlAccNumberValue();
				subContractOrderFunc.verifyIncludeChargesAndPostIcon();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getScoNumber(),
							webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
							System.getProperty("HariUsername"));
					String Text = VariableHandler.getLastApprovalAPIHeaderText()
							.replace("Material Request Created. :", "").trim();
					Text = Text.replace("SUBCONTRACT ORDER Document is Approved.", "").trim();
					VariableHandler.setMRNumber(Text);
					VariableHandler.setMaterialRequestNo(Text);
				}
				subContractOrderFunc.verifyValidateSubContractDocumentsStatus(VariableHandler.getScoNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
				materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getScoNumber());
				materialRequestFunc.headerSaveAndPost();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRNumber(),
							webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
							System.getProperty("HariUsername"));
				}
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc.checkStatusOfMRNo(VariableHandler.getMRNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.navigateToMaterialIssuanceVoucher();
				materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMRNumber());
				materialIssuanceVoucherFunc.headerTickAndPostIcon();
				materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceNumber(VariableHandler.getMIVNumber(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				commonFunc.navigateToGateEntryDeliveryChallan();
				deliveryChallanFunc.verifyStatusOfDeliveryChallan(VariableHandler.getDCNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getScoNumber());
				grnScoPageFunc.addHeatNumberAndTestNumber();
				grnScoPageFunc.checkConsumptionBeforeGenerationMat();
				grnScoPageFunc.verifyGenMatConsAndAllocate();
				grnScoPageFunc.checkConsumptionAfterGenerationMat();
				grnScoPageFunc.verifyViewLineDetails();
				grnScoPageFunc.saveHeaderAndPost();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
							VariableHandler.getScoGrnNumber(), webDB.getDataFromProperties("HodEmpId"),
							commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
				}
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.verifyValidateGrnScoStatus(VariableHandler.getScoGrnNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {
						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(FileName, SheetName);
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllBothPartialQuantityMoveTypeItem': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Production Order against sales Order with move type -
	 * single?
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderAgainstSOAllInsideMoveTypeSingle() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String MRPName = "TEST MRP - " + RandomStringUtils.randomNumeric(3);
		String Today = date.format(formatter);
		LocalDate nextmonthdate = LocalDate.now().plusMonths(1);
		String NextMonthToday = nextmonthdate.format(formatter);
		String FGItem = commonFunc.getDataFromTestDataSheet("FG-10001-Item");
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		LocalDate previousDate = LocalDate.now().minusDays(1);
		String YesterDay = previousDate.format(formatter);
		int ReqQuantity = 4;
		String BOMNo = commonFunc.getDataFromTestDataSheet("BomAllInside");
		try {
			saleOrderFunc.verifySalesOrderCreationForMRPAndProdOrder(ReqQuantity, BOMNo);
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.clickAddIconInMRP();
			mrpFunc.fillDetailsInMRPCreationAndClickTickIcon(Today, MRPName, Today, "ALL");
			mrpFunc.clickAddLinePlusIconInSources();
			mrpFunc.enterUnitCutOffDateInSourcesLine(commonFunc.getDataFromTestDataSheet("Unit_100"), NextMonthToday);
			mrpFunc.clickSalesOrderDemandCheckBoxInSourceLines();
			mrpFunc.clickSaveIconInSourcesTab();
			mrpFunc.clickLoadDemandButtonInMRP();
			mrpFunc.navigateToDemandTabInMRPScreen();
			mrpFunc.searchSONumberInDemandAndSelectSOItems(VariableHandler.getSONumber());
			mrpFunc.clickCheckExceptionAndSaveHeaderInMRP();
			mrpFunc.navigateToExceptionTabInMRPScreen();
			mrpFunc.validateNoExceptionDataShownInTableData();
			mrpFunc.clickRunMRPButton();
			mrpFunc.navigateToResultsTabInMRPScreen();
			mrpFunc.verifyUnitResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100") });
			mrpFunc.verifyItemResultsDetails(new String[] { FGItem, RMItem });
			mrpFunc.verifyUOMResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("UomNos"),
					commonFunc.getDataFromTestDataSheet("Kgs") });
			mrpFunc.clickHeaderSaveAndClickPost();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRPNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			mrpFunc.searchAndClickMRPNoInFindPage(VariableHandler.getMRPNumber());
			mrpFunc.clickReleaseButtonInMRP();
			mrpFunc.chooseOrderTypeFromAndToDateInReleaseDetails("All", YesterDay, Today);
			mrpFunc.clickReleaseButtonInReleaseFrame();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ReleasedStatus"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfMRPNo(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("UnreleasedStatus"));
			webDB.navigateToRefresh();
			searchAndSelectProductionOrderWithDocNo(VariableHandler.getProductionOrderNumber());
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Single"));
			verifyHeaderValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Single"));
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyRoutingValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Single"));
			verifyRawMaterialsValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Single"));
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
			int AllocStartIndex = 0;
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndexSheet = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
				AllocStartIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				productionOrderInsideSingleMoveType(StartIndexSheet, AllocStartIndex,
						webDB.getDataFromProperties("PMFFileName"),
						webDB.getDataFromProperties("AllInsideSheetName-Single"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Single"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyProdOrderAgainstSOAllInsideMoveTypeSingle': " + e);
			return false;
		}
	}

	/**
	 * This method used to Production Order against sales Order with move type -
	 * multiple?
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderAgainstSOAllInsideMoveTypeMultiple() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String MRPName = "TEST MRP - " + RandomStringUtils.randomNumeric(3);
		String Today = date.format(formatter);
		LocalDate nextmonthdate = LocalDate.now().plusMonths(1);
		String NextMonthToday = nextmonthdate.format(formatter);
		String FGItem = commonFunc.getDataFromTestDataSheet("FG-10001-Item");
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		LocalDate previousDate = LocalDate.now().minusDays(1);
		String YesterDay = previousDate.format(formatter);
		int ReqQuantity = 4;
		String BOMNo = commonFunc.getDataFromTestDataSheet("BomAllInside");
		try {
			saleOrderFunc.verifySalesOrderCreationForMRPAndProdOrder(ReqQuantity, BOMNo);
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.clickAddIconInMRP();
			mrpFunc.fillDetailsInMRPCreationAndClickTickIcon(Today, MRPName, Today, "ALL");
			mrpFunc.clickAddLinePlusIconInSources();
			mrpFunc.enterUnitCutOffDateInSourcesLine(commonFunc.getDataFromTestDataSheet("Unit_100"), NextMonthToday);
			mrpFunc.clickSalesOrderDemandCheckBoxInSourceLines();
			mrpFunc.clickSaveIconInSourcesTab();
			mrpFunc.clickLoadDemandButtonInMRP();
			mrpFunc.navigateToDemandTabInMRPScreen();
			mrpFunc.searchSONumberInDemandAndSelectSOItems(VariableHandler.getSONumber());
			mrpFunc.clickCheckExceptionAndSaveHeaderInMRP();
			mrpFunc.navigateToExceptionTabInMRPScreen();
			mrpFunc.validateNoExceptionDataShownInTableData();
			mrpFunc.clickRunMRPButton();
			mrpFunc.navigateToResultsTabInMRPScreen();
			mrpFunc.verifyUnitResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100") });
			mrpFunc.verifyItemResultsDetails(new String[] { FGItem, RMItem });
			mrpFunc.verifyUOMResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("UomNos"),
					commonFunc.getDataFromTestDataSheet("Kgs") });
			mrpFunc.clickHeaderSaveAndClickPost();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRPNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			mrpFunc.searchAndClickMRPNoInFindPage(VariableHandler.getMRPNumber());
			mrpFunc.clickReleaseButtonInMRP();
			mrpFunc.chooseOrderTypeFromAndToDateInReleaseDetails("All", YesterDay, Today);
			mrpFunc.clickReleaseButtonInReleaseFrame();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ReleasedStatus"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfMRPNo(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("UnreleasedStatus"));
			webDB.navigateToRefresh();
			searchAndSelectProductionOrderWithDocNo(VariableHandler.getProductionOrderNumber());
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Multiple"));
			verifyHeaderValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Multiple"));
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("MultipleType"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyRoutingValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Multiple"));
			verifyRawMaterialsValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Multiple"));
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
			int AllocStartIndex = 0;
			for (int i = 0; i < 2; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndexSheet = (i == 0) ? 35 : (i == 1) ? 45 : 0;
				AllocStartIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				productionOrderInsideMultipleMoveType(i, StartIndexSheet, AllocStartIndex,
						webDB.getDataFromProperties("PMFFileName"),
						webDB.getDataFromProperties("AllInsideSheetName-Multiple"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Multiple"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyProdOrderAgainstSOAllInsideMoveTypeMultiple': " + e);
			return false;
		}
	}

	/**
	 * This method used to Production Order against sales Order with move type -
	 * Item?
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderAgainstSOAllInsideMoveTypeItem() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String MRPName = "TEST MRP - " + RandomStringUtils.randomNumeric(3);
		String Today = date.format(formatter);
		LocalDate nextmonthdate = LocalDate.now().plusMonths(1);
		String NextMonthToday = nextmonthdate.format(formatter);
		String FGItem = commonFunc.getDataFromTestDataSheet("FG-10001-Item");
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		LocalDate previousDate = LocalDate.now().minusDays(1);
		String YesterDay = previousDate.format(formatter);
		int ReqQuantity = 4;
		String BOMNo = commonFunc.getDataFromTestDataSheet("BomAllInside");
		try {
			saleOrderFunc.verifySalesOrderCreationForMRPAndProdOrder(ReqQuantity, BOMNo);
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.clickAddIconInMRP();
			mrpFunc.fillDetailsInMRPCreationAndClickTickIcon(Today, MRPName, Today, "ALL");
			mrpFunc.clickAddLinePlusIconInSources();
			mrpFunc.enterUnitCutOffDateInSourcesLine(commonFunc.getDataFromTestDataSheet("Unit_100"), NextMonthToday);
			mrpFunc.clickSalesOrderDemandCheckBoxInSourceLines();
			mrpFunc.clickSaveIconInSourcesTab();
			mrpFunc.clickLoadDemandButtonInMRP();
			mrpFunc.navigateToDemandTabInMRPScreen();
			mrpFunc.searchSONumberInDemandAndSelectSOItems(VariableHandler.getSONumber());
			mrpFunc.clickCheckExceptionAndSaveHeaderInMRP();
			mrpFunc.navigateToExceptionTabInMRPScreen();
			mrpFunc.validateNoExceptionDataShownInTableData();
			mrpFunc.clickRunMRPButton();
			mrpFunc.navigateToResultsTabInMRPScreen();
			mrpFunc.verifyUnitResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100") });
			mrpFunc.verifyItemResultsDetails(new String[] { FGItem, RMItem });
			mrpFunc.verifyUOMResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("UomNos"),
					commonFunc.getDataFromTestDataSheet("Kgs") });
			mrpFunc.clickHeaderSaveAndClickPost();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRPNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			mrpFunc.searchAndClickMRPNoInFindPage(VariableHandler.getMRPNumber());
			mrpFunc.clickReleaseButtonInMRP();
			mrpFunc.chooseOrderTypeFromAndToDateInReleaseDetails("All", YesterDay, Today);
			mrpFunc.clickReleaseButtonInReleaseFrame();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ReleasedStatus"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfMRPNo(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("UnreleasedStatus"));
			webDB.navigateToRefresh();
			searchAndSelectProductionOrderWithDocNo(VariableHandler.getProductionOrderNumber());
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Item"));
			verifyHeaderValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Item"));
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("ItemType"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyRoutingValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Item"));
			verifyRawMaterialsValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Item"));
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
			int AllocStartIndex = 0;
			for (int i = 0; i < 1; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndexSheet = (i == 0) ? 35 : 0;
				AllocStartIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				productionOrderInsideItemMoveType(StartIndexSheet, AllocStartIndex,
						webDB.getDataFromProperties("PMFFileName"),
						webDB.getDataFromProperties("AllInsideSheetName-Item"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Item"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyProdOrderAgainstSOAllInsideMoveTypeItem': " + e);
			return false;
		}
	}

	/**
	 * This method used to Production Order against sales Order with move type -
	 * single?
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderAgainstSOAllOutsideMoveTypeSingle() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String MRPName = "TEST MRP - " + RandomStringUtils.randomNumeric(3);
		String Today = date.format(formatter);
		LocalDate nextmonthdate = LocalDate.now().plusMonths(1);
		String NextMonthToday = nextmonthdate.format(formatter);
		String FGItem = commonFunc.getDataFromTestDataSheet("FG-10001-Item");
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		LocalDate previousDate = LocalDate.now().minusDays(1);
		String YesterDay = previousDate.format(formatter);
		int ReqQuantity = 4;
		String BOMNo = commonFunc.getDataFromTestDataSheet("BomAllOutside");
		try {
			saleOrderFunc.verifySalesOrderCreationForMRPAndProdOrder(ReqQuantity, BOMNo);
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.clickAddIconInMRP();
			mrpFunc.fillDetailsInMRPCreationAndClickTickIcon(Today, MRPName, Today, "ALL");
			mrpFunc.clickAddLinePlusIconInSources();
			mrpFunc.enterUnitCutOffDateInSourcesLine(commonFunc.getDataFromTestDataSheet("Unit_100"), NextMonthToday);
			mrpFunc.clickSalesOrderDemandCheckBoxInSourceLines();
			mrpFunc.clickSaveIconInSourcesTab();
			mrpFunc.clickLoadDemandButtonInMRP();
			mrpFunc.navigateToDemandTabInMRPScreen();
			mrpFunc.searchSONumberInDemandAndSelectSOItems(VariableHandler.getSONumber());
			mrpFunc.clickCheckExceptionAndSaveHeaderInMRP();
			mrpFunc.navigateToExceptionTabInMRPScreen();
			mrpFunc.validateNoExceptionDataShownInTableData();
			mrpFunc.clickRunMRPButton();
			mrpFunc.navigateToResultsTabInMRPScreen();
			mrpFunc.verifyUnitResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100") });
			mrpFunc.verifyItemResultsDetails(new String[] { FGItem, RMItem });
			mrpFunc.verifyUOMResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("UomNos"),
					commonFunc.getDataFromTestDataSheet("Kgs") });
			mrpFunc.clickHeaderSaveAndClickPost();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRPNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			mrpFunc.searchAndClickMRPNoInFindPage(VariableHandler.getMRPNumber());
			mrpFunc.clickReleaseButtonInMRP();
			mrpFunc.chooseOrderTypeFromAndToDateInReleaseDetails("All", YesterDay, Today);
			mrpFunc.clickReleaseButtonInReleaseFrame();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ReleasedStatus"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfMRPNo(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("UnreleasedStatus"));
			webDB.navigateToRefresh();
			searchAndSelectProductionOrderWithDocNo(VariableHandler.getProductionOrderNumber());
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Single"));
			verifyHeaderValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Single"));
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("OutsideType"),
					commonFunc.getDataFromTestDataSheet("OutsideType"));
			verifyRoutingValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Single"));
			verifyRawMaterialsValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Single"));
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
			int AllocStartIndex = 0;
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndexSheet = (i == 0) ? 35 : 0;
				AllocStartIndex = (i == 0) ? 10 : 0;
				productionOrderOutsideSingleMoveType(StartIndexSheet, AllocStartIndex,
						webDB.getDataFromProperties("PMFFileName"),
						webDB.getDataFromProperties("AllOutsideSheetName-Single"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Single"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyProdOrderAgainstSOAllOutsideMoveTypeSingle': " + e);
			return false;
		}
	}

	/**
	 * This method used to Production Order against sales Order with move type -
	 * multiple?
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderAgainstSOAllOutsideMoveTypeMultiple() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String MRPName = "TEST MRP - " + RandomStringUtils.randomNumeric(3);
		String Today = date.format(formatter);
		LocalDate nextmonthdate = LocalDate.now().plusMonths(1);
		String NextMonthToday = nextmonthdate.format(formatter);
		String FGItem = commonFunc.getDataFromTestDataSheet("FG-10001-Item");
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		LocalDate previousDate = LocalDate.now().minusDays(1);
		String YesterDay = previousDate.format(formatter);
		int ReqQuantity = 4;
		String BOMNo = commonFunc.getDataFromTestDataSheet("BomAllOutside");
		try {
			saleOrderFunc.verifySalesOrderCreationForMRPAndProdOrder(ReqQuantity, BOMNo);
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.clickAddIconInMRP();
			mrpFunc.fillDetailsInMRPCreationAndClickTickIcon(Today, MRPName, Today, "ALL");
			mrpFunc.clickAddLinePlusIconInSources();
			mrpFunc.enterUnitCutOffDateInSourcesLine(commonFunc.getDataFromTestDataSheet("Unit_100"), NextMonthToday);
			mrpFunc.clickSalesOrderDemandCheckBoxInSourceLines();
			mrpFunc.clickSaveIconInSourcesTab();
			mrpFunc.clickLoadDemandButtonInMRP();
			mrpFunc.navigateToDemandTabInMRPScreen();
			mrpFunc.searchSONumberInDemandAndSelectSOItems(VariableHandler.getSONumber());
			mrpFunc.clickCheckExceptionAndSaveHeaderInMRP();
			mrpFunc.navigateToExceptionTabInMRPScreen();
			mrpFunc.validateNoExceptionDataShownInTableData();
			mrpFunc.clickRunMRPButton();
			mrpFunc.navigateToResultsTabInMRPScreen();
			mrpFunc.verifyUnitResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100") });
			mrpFunc.verifyItemResultsDetails(new String[] { FGItem, RMItem });
			mrpFunc.verifyUOMResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("UomNos"),
					commonFunc.getDataFromTestDataSheet("Kgs") });
			mrpFunc.clickHeaderSaveAndClickPost();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRPNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			mrpFunc.searchAndClickMRPNoInFindPage(VariableHandler.getMRPNumber());
			mrpFunc.clickReleaseButtonInMRP();
			mrpFunc.chooseOrderTypeFromAndToDateInReleaseDetails("All", YesterDay, Today);
			mrpFunc.clickReleaseButtonInReleaseFrame();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ReleasedStatus"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfMRPNo(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("UnreleasedStatus"));
			webDB.navigateToRefresh();
			searchAndSelectProductionOrderWithDocNo(VariableHandler.getProductionOrderNumber());
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Multiple"));
			verifyHeaderValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Multiple"));
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("MultipleType"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("OutsideType"),
					commonFunc.getDataFromTestDataSheet("OutsideType"));
			verifyRoutingValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Multiple"));
			verifyRawMaterialsValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Multiple"));
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
			int AllocStartIndex = 0;
			for (int i = 0; i < 2; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				StartIndexSheet = (i == 0) ? 35 : 0;
				AllocStartIndex = (i == 0) ? 10 : 0;
				productionOrderOutsideMultipleMoveType(i, StartIndexSheet, AllocStartIndex,
						webDB.getDataFromProperties("PMFFileName"),
						webDB.getDataFromProperties("AllOutsideSheetName-Multiple"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Multiple"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyProdOrderAgainstSOAllOutsideMoveTypeMultiple': " + e);
			return false;
		}
	}

	/**
	 * This method used to Production Order against sales Order with move type -
	 * item?
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderAgainstSOAllOutsideMoveTypeItem() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String MRPName = "TEST MRP - " + RandomStringUtils.randomNumeric(3);
		String Today = date.format(formatter);
		LocalDate nextmonthdate = LocalDate.now().plusMonths(1);
		String NextMonthToday = nextmonthdate.format(formatter);
		String FGItem = commonFunc.getDataFromTestDataSheet("FG-10001-Item");
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		LocalDate previousDate = LocalDate.now().minusDays(1);
		String YesterDay = previousDate.format(formatter);
		int ReqQuantity = 4;
		String BOMNo = commonFunc.getDataFromTestDataSheet("BomAllOutside");
		try {
			saleOrderFunc.verifySalesOrderCreationForMRPAndProdOrder(ReqQuantity, BOMNo);
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.clickAddIconInMRP();
			mrpFunc.fillDetailsInMRPCreationAndClickTickIcon(Today, MRPName, Today, "ALL");
			mrpFunc.clickAddLinePlusIconInSources();
			mrpFunc.enterUnitCutOffDateInSourcesLine(commonFunc.getDataFromTestDataSheet("Unit_100"), NextMonthToday);
			mrpFunc.clickSalesOrderDemandCheckBoxInSourceLines();
			mrpFunc.clickSaveIconInSourcesTab();
			mrpFunc.clickLoadDemandButtonInMRP();
			mrpFunc.navigateToDemandTabInMRPScreen();
			mrpFunc.searchSONumberInDemandAndSelectSOItems(VariableHandler.getSONumber());
			mrpFunc.clickCheckExceptionAndSaveHeaderInMRP();
			mrpFunc.navigateToExceptionTabInMRPScreen();
			mrpFunc.validateNoExceptionDataShownInTableData();
			mrpFunc.clickRunMRPButton();
			mrpFunc.navigateToResultsTabInMRPScreen();
			mrpFunc.verifyUnitResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100") });
			mrpFunc.verifyItemResultsDetails(new String[] { FGItem, RMItem });
			mrpFunc.verifyUOMResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("UomNos"),
					commonFunc.getDataFromTestDataSheet("Kgs") });
			mrpFunc.clickHeaderSaveAndClickPost();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRPNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			mrpFunc.searchAndClickMRPNoInFindPage(VariableHandler.getMRPNumber());
			mrpFunc.clickReleaseButtonInMRP();
			mrpFunc.chooseOrderTypeFromAndToDateInReleaseDetails("All", YesterDay, Today);
			mrpFunc.clickReleaseButtonInReleaseFrame();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ReleasedStatus"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfMRPNo(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("UnreleasedStatus"));
			webDB.navigateToRefresh();
			searchAndSelectProductionOrderWithDocNo(VariableHandler.getProductionOrderNumber());
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Item"));
			verifyHeaderValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Item"));
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("ItemType"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("OutsideType"),
					commonFunc.getDataFromTestDataSheet("OutsideType"));
			verifyRoutingValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Item"));
			verifyRawMaterialsValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Item"));
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndex = 0;
			int AllocIndex = 0;
			for (int i = 0; i < 1; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
				AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				productionOrderOutsideItemMoveType(StartIndex, AllocIndex, webDB.getDataFromProperties("PMFFileName"),
						webDB.getDataFromProperties("AllOutsideSheetName-Item"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllOutsideSheetName-Item"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyProdOrderAgainstSOAllOutsideMoveTypeItem': " + e);
			return false;
		}
	}

	/**
	 * This method used to Production Order against sales Order with move type -
	 * single
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderAgainstSOFirstInsideRestOutsideMoveTypeSingle() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String MRPName = "TEST MRP - " + RandomStringUtils.randomNumeric(3);
		String Today = date.format(formatter);
		LocalDate nextmonthdate = LocalDate.now().plusMonths(1);
		String NextMonthToday = nextmonthdate.format(formatter);
		String FGItem = commonFunc.getDataFromTestDataSheet("FG-10001-Item");
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		LocalDate previousDate = LocalDate.now().minusDays(1);
		String YesterDay = previousDate.format(formatter);
		int ReqQuantity = 4;
		String BOMNo = commonFunc.getDataFromTestDataSheet("BomFirstInRestOut");
		String MoveType = commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus");
		String FileName = webDB.getDataFromProperties("PMFFileName");
		String SheetName = webDB.getDataFromProperties("FirstInRestOutSheetName-Single");
		try {
			saleOrderFunc.verifySalesOrderCreationForMRPAndProdOrder(ReqQuantity, BOMNo);
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.clickAddIconInMRP();
			mrpFunc.fillDetailsInMRPCreationAndClickTickIcon(Today, MRPName, Today, "ALL");
			mrpFunc.clickAddLinePlusIconInSources();
			mrpFunc.enterUnitCutOffDateInSourcesLine(commonFunc.getDataFromTestDataSheet("Unit_100"), NextMonthToday);
			mrpFunc.clickSalesOrderDemandCheckBoxInSourceLines();
			mrpFunc.clickSaveIconInSourcesTab();
			mrpFunc.clickLoadDemandButtonInMRP();
			mrpFunc.navigateToDemandTabInMRPScreen();
			mrpFunc.searchSONumberInDemandAndSelectSOItems(VariableHandler.getSONumber());
			mrpFunc.clickCheckExceptionAndSaveHeaderInMRP();
			mrpFunc.navigateToExceptionTabInMRPScreen();
			mrpFunc.validateNoExceptionDataShownInTableData();
			mrpFunc.clickRunMRPButton();
			mrpFunc.navigateToResultsTabInMRPScreen();
			mrpFunc.verifyUnitResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100") });
			mrpFunc.verifyItemResultsDetails(new String[] { FGItem, RMItem });
			mrpFunc.verifyUOMResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("UomNos"),
					commonFunc.getDataFromTestDataSheet("Kgs") });
			mrpFunc.clickHeaderSaveAndClickPost();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRPNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			mrpFunc.searchAndClickMRPNoInFindPage(VariableHandler.getMRPNumber());
			mrpFunc.clickReleaseButtonInMRP();
			mrpFunc.chooseOrderTypeFromAndToDateInReleaseDetails("All", YesterDay, Today);
			mrpFunc.clickReleaseButtonInReleaseFrame();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ReleasedStatus"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfMRPNo(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("UnreleasedStatus"));
			webDB.navigateToRefresh();
			searchAndSelectProductionOrderWithDocNo(VariableHandler.getProductionOrderNumber());
			verifyWriteBeforeStockForFGAndRawMaterials(FileName, SheetName);
			verifyHeaderValidationInProductionOrderWithDB(FileName, SheetName);
			verifyProductionOrderMREventAndRestOP(null, null, MoveType);
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("OutsideType"));
			verifyRoutingValidationInProductionOrderWithDB(FileName, SheetName);
			verifyRawMaterialsValidationInProductionOrderWithDB(FileName, SheetName);
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndex = 0;
			int AllocIndex = 0;
			a: for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"))) {
					StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("MultipleType"))) {
					StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
					if (i > 1) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ItemType"))) {
					StartIndex = (i == 0) ? 35 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideItemMoveType(StartIndex, AllocIndex, FileName, SheetName);
					if (i > 0) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"))) {
					productionOrderOutsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("MultipleType"))) {
					productionOrderOutsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
					if (i > 1) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ItemType"))) {
					productionOrderOutsideItemMoveType(StartIndex, AllocIndex, FileName, SheetName);
					if (i > 0) {
						break a;
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(FileName, SheetName);
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyProdOrderAgainstSOFirstInsideRestOutsideMoveTypeSingle': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Production Order against sales Order with move type -
	 * multiple
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderAgainstSOFirstInsideRestOutsideMoveTypeMultiple() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String MRPName = "TEST MRP - " + RandomStringUtils.randomNumeric(3);
		String Today = date.format(formatter);
		LocalDate nextmonthdate = LocalDate.now().plusMonths(1);
		String NextMonthToday = nextmonthdate.format(formatter);
		String FGItem = commonFunc.getDataFromTestDataSheet("FG-10001-Item");
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		LocalDate previousDate = LocalDate.now().minusDays(1);
		String YesterDay = previousDate.format(formatter);
		int ReqQuantity = 4;
		String BOMNo = commonFunc.getDataFromTestDataSheet("BomFirstInRestOut");
		String MoveType = commonFunc.getDataFromTestDataSheet("MultipleType");
		String FileName = webDB.getDataFromProperties("PMFFileName");
		String SheetName = webDB.getDataFromProperties("FirstInRestOutSheetName-Multiple");
		try {
			saleOrderFunc.verifySalesOrderCreationForMRPAndProdOrder(ReqQuantity, BOMNo);
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.clickAddIconInMRP();
			mrpFunc.fillDetailsInMRPCreationAndClickTickIcon(Today, MRPName, Today, "ALL");
			mrpFunc.clickAddLinePlusIconInSources();
			mrpFunc.enterUnitCutOffDateInSourcesLine(commonFunc.getDataFromTestDataSheet("Unit_100"), NextMonthToday);
			mrpFunc.clickSalesOrderDemandCheckBoxInSourceLines();
			mrpFunc.clickSaveIconInSourcesTab();
			mrpFunc.clickLoadDemandButtonInMRP();
			mrpFunc.navigateToDemandTabInMRPScreen();
			mrpFunc.searchSONumberInDemandAndSelectSOItems(VariableHandler.getSONumber());
			mrpFunc.clickCheckExceptionAndSaveHeaderInMRP();
			mrpFunc.navigateToExceptionTabInMRPScreen();
			mrpFunc.validateNoExceptionDataShownInTableData();
			mrpFunc.clickRunMRPButton();
			mrpFunc.navigateToResultsTabInMRPScreen();
			mrpFunc.verifyUnitResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100") });
			mrpFunc.verifyItemResultsDetails(new String[] { FGItem, RMItem });
			mrpFunc.verifyUOMResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("UomNos"),
					commonFunc.getDataFromTestDataSheet("Kgs") });
			mrpFunc.clickHeaderSaveAndClickPost();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRPNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			mrpFunc.searchAndClickMRPNoInFindPage(VariableHandler.getMRPNumber());
			mrpFunc.clickReleaseButtonInMRP();
			mrpFunc.chooseOrderTypeFromAndToDateInReleaseDetails("All", YesterDay, Today);
			mrpFunc.clickReleaseButtonInReleaseFrame();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ReleasedStatus"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfMRPNo(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("UnreleasedStatus"));
			webDB.navigateToRefresh();
			searchAndSelectProductionOrderWithDocNo(VariableHandler.getProductionOrderNumber());
			verifyWriteBeforeStockForFGAndRawMaterials(FileName, SheetName);
			verifyHeaderValidationInProductionOrderWithDB(FileName, SheetName);
			verifyProductionOrderMREventAndRestOP(null, null, MoveType);
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("OutsideType"));
			verifyRoutingValidationInProductionOrderWithDB(FileName, SheetName);
			verifyRawMaterialsValidationInProductionOrderWithDB(FileName, SheetName);
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndex = 0;
			int AllocIndex = 0;
			a: for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"))) {
					StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("MultipleType"))) {
					StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
					if (i > 1) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ItemType"))) {
					StartIndex = (i == 0) ? 35 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideItemMoveType(StartIndex, AllocIndex, FileName, SheetName);
					if (i > 0) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"))) {
					productionOrderOutsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("MultipleType"))) {
					productionOrderOutsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
					if (i > 1) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ItemType"))) {
					productionOrderOutsideItemMoveType(StartIndex, AllocIndex, FileName, SheetName);
					if (i > 0) {
						break a;
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(FileName, SheetName);
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyProdOrderAgainstSOFirstInsideRestOutsideMoveTypeMultiple': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Production Order against sales Order with move type -
	 * item
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderAgainstSOFirstInsideRestOutsideMoveTypeItem() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String MRPName = "TEST MRP - " + RandomStringUtils.randomNumeric(3);
		String Today = date.format(formatter);
		LocalDate nextmonthdate = LocalDate.now().plusMonths(1);
		String NextMonthToday = nextmonthdate.format(formatter);
		String FGItem = commonFunc.getDataFromTestDataSheet("FG-10001-Item");
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		LocalDate previousDate = LocalDate.now().minusDays(1);
		String YesterDay = previousDate.format(formatter);
		int ReqQuantity = 4;
		String BOMNo = commonFunc.getDataFromTestDataSheet("BomFirstInRestOut");
		String MoveType = commonFunc.getDataFromTestDataSheet("ItemType");
		String FileName = webDB.getDataFromProperties("PMFFileName");
		String SheetName = webDB.getDataFromProperties("FirstInRestOutSheetName-Item");
		try {
			saleOrderFunc.verifySalesOrderCreationForMRPAndProdOrder(ReqQuantity, BOMNo);
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.clickAddIconInMRP();
			mrpFunc.fillDetailsInMRPCreationAndClickTickIcon(Today, MRPName, Today, "ALL");
			mrpFunc.clickAddLinePlusIconInSources();
			mrpFunc.enterUnitCutOffDateInSourcesLine(commonFunc.getDataFromTestDataSheet("Unit_100"), NextMonthToday);
			mrpFunc.clickSalesOrderDemandCheckBoxInSourceLines();
			mrpFunc.clickSaveIconInSourcesTab();
			mrpFunc.clickLoadDemandButtonInMRP();
			mrpFunc.navigateToDemandTabInMRPScreen();
			mrpFunc.searchSONumberInDemandAndSelectSOItems(VariableHandler.getSONumber());
			mrpFunc.clickCheckExceptionAndSaveHeaderInMRP();
			mrpFunc.navigateToExceptionTabInMRPScreen();
			mrpFunc.validateNoExceptionDataShownInTableData();
			mrpFunc.clickRunMRPButton();
			mrpFunc.navigateToResultsTabInMRPScreen();
			mrpFunc.verifyUnitResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100") });
			mrpFunc.verifyItemResultsDetails(new String[] { FGItem, RMItem });
			mrpFunc.verifyUOMResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("UomNos"),
					commonFunc.getDataFromTestDataSheet("Kgs") });
			mrpFunc.clickHeaderSaveAndClickPost();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRPNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			mrpFunc.searchAndClickMRPNoInFindPage(VariableHandler.getMRPNumber());
			mrpFunc.clickReleaseButtonInMRP();
			mrpFunc.chooseOrderTypeFromAndToDateInReleaseDetails("All", YesterDay, Today);
			mrpFunc.clickReleaseButtonInReleaseFrame();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ReleasedStatus"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfMRPNo(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("UnreleasedStatus"));
			webDB.navigateToRefresh();
			searchAndSelectProductionOrderWithDocNo(VariableHandler.getProductionOrderNumber());
			verifyWriteBeforeStockForFGAndRawMaterials(FileName, SheetName);
			verifyHeaderValidationInProductionOrderWithDB(FileName, SheetName);
			verifyProductionOrderMREventAndRestOP(null, null, MoveType);
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("OutsideType"));
			verifyRoutingValidationInProductionOrderWithDB(FileName, SheetName);
			verifyRawMaterialsValidationInProductionOrderWithDB(FileName, SheetName);
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndex = 0;
			int AllocIndex = 0;
			a: for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"))) {
					StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("MultipleType"))) {
					StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
					if (i > 1) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ItemType"))) {
					StartIndex = (i == 0) ? 35 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideItemMoveType(StartIndex, AllocIndex, FileName, SheetName);
					if (i > 0) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"))) {
					productionOrderOutsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("MultipleType"))) {
					productionOrderOutsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
					if (i > 1) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ItemType"))) {
					productionOrderOutsideItemMoveType(StartIndex, AllocIndex, FileName, SheetName);
					if (i > 0) {
						break a;
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(FileName, SheetName);
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyProdOrderAgainstSOFirstInsideRestOutsideMoveTypeMultiple': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Production Order against sales Order with move type -
	 * single
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderAgainstSOFirstOutsideRestInsideMoveTypeSingle() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String MRPName = "TEST MRP - " + RandomStringUtils.randomNumeric(3);
		String Today = date.format(formatter);
		LocalDate nextmonthdate = LocalDate.now().plusMonths(1);
		String NextMonthToday = nextmonthdate.format(formatter);
		String FGItem = commonFunc.getDataFromTestDataSheet("FG-10001-Item");
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		LocalDate previousDate = LocalDate.now().minusDays(1);
		String YesterDay = previousDate.format(formatter);
		int ReqQuantity = 4;
		String BOMNo = commonFunc.getDataFromTestDataSheet("BomFirstOutRestIn");
		String MoveType = commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus");
		String FileName = webDB.getDataFromProperties("PMFFileName");
		String SheetName = webDB.getDataFromProperties("FirstOutRestInSheetName-Single");
		try {
			saleOrderFunc.verifySalesOrderCreationForMRPAndProdOrder(ReqQuantity, BOMNo);
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.clickAddIconInMRP();
			mrpFunc.fillDetailsInMRPCreationAndClickTickIcon(Today, MRPName, Today, "ALL");
			mrpFunc.clickAddLinePlusIconInSources();
			mrpFunc.enterUnitCutOffDateInSourcesLine(commonFunc.getDataFromTestDataSheet("Unit_100"), NextMonthToday);
			mrpFunc.clickSalesOrderDemandCheckBoxInSourceLines();
			mrpFunc.clickSaveIconInSourcesTab();
			mrpFunc.clickLoadDemandButtonInMRP();
			mrpFunc.navigateToDemandTabInMRPScreen();
			mrpFunc.searchSONumberInDemandAndSelectSOItems(VariableHandler.getSONumber());
			mrpFunc.clickCheckExceptionAndSaveHeaderInMRP();
			mrpFunc.navigateToExceptionTabInMRPScreen();
			mrpFunc.validateNoExceptionDataShownInTableData();
			mrpFunc.clickRunMRPButton();
			mrpFunc.navigateToResultsTabInMRPScreen();
			mrpFunc.verifyUnitResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100") });
			mrpFunc.verifyItemResultsDetails(new String[] { FGItem, RMItem });
			mrpFunc.verifyUOMResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("UomNos"),
					commonFunc.getDataFromTestDataSheet("Kgs") });
			mrpFunc.clickHeaderSaveAndClickPost();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRPNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			mrpFunc.searchAndClickMRPNoInFindPage(VariableHandler.getMRPNumber());
			mrpFunc.clickReleaseButtonInMRP();
			mrpFunc.chooseOrderTypeFromAndToDateInReleaseDetails("All", YesterDay, Today);
			mrpFunc.clickReleaseButtonInReleaseFrame();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ReleasedStatus"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfMRPNo(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("UnreleasedStatus"));
			webDB.navigateToRefresh();
			searchAndSelectProductionOrderWithDocNo(VariableHandler.getProductionOrderNumber());
			verifyWriteBeforeStockForFGAndRawMaterials(FileName, SheetName);
			verifyHeaderValidationInProductionOrderWithDB(FileName, SheetName);
			verifyProductionOrderMREventAndRestOP(null, null, MoveType);
			verifyProcessType(commonFunc.getDataFromTestDataSheet("OutsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyRoutingValidationInProductionOrderWithDB(FileName, SheetName);
			verifyRawMaterialsValidationInProductionOrderWithDB(FileName, SheetName);
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndex = 0;
			int AllocIndex = 0;
			a: for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"))) {
					StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("MultipleType"))) {
					StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
					if (i > 1) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ItemType"))) {
					StartIndex = (i == 0) ? 35 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideItemMoveType(StartIndex, AllocIndex, FileName, SheetName);
					if (i > 0) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"))) {
					productionOrderOutsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("MultipleType"))) {
					productionOrderOutsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
					if (i > 1) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ItemType"))) {
					productionOrderOutsideItemMoveType(StartIndex, AllocIndex, FileName, SheetName);
					if (i > 0) {
						break a;
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(FileName, SheetName);
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyProdOrderAgainstSOFirstOutsideRestInsideMoveTypeSingle': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Production Order against sales Order with move type -
	 * multiple
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderAgainstSOFirstOutsideRestInsideMoveTypeMultiple() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String MRPName = "TEST MRP - " + RandomStringUtils.randomNumeric(3);
		String Today = date.format(formatter);
		LocalDate nextmonthdate = LocalDate.now().plusMonths(1);
		String NextMonthToday = nextmonthdate.format(formatter);
		String FGItem = commonFunc.getDataFromTestDataSheet("FG-10001-Item");
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		LocalDate previousDate = LocalDate.now().minusDays(1);
		String YesterDay = previousDate.format(formatter);
		int ReqQuantity = 4;
		String BOMNo = commonFunc.getDataFromTestDataSheet("BomFirstOutRestIn");
		String MoveType = commonFunc.getDataFromTestDataSheet("MultipleType");
		String FileName = webDB.getDataFromProperties("PMFFileName");
		String SheetName = webDB.getDataFromProperties("FirstOutRestInSheetName-Multiple");
		try {
			saleOrderFunc.verifySalesOrderCreationForMRPAndProdOrder(ReqQuantity, BOMNo);
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.clickAddIconInMRP();
			mrpFunc.fillDetailsInMRPCreationAndClickTickIcon(Today, MRPName, Today, "ALL");
			mrpFunc.clickAddLinePlusIconInSources();
			mrpFunc.enterUnitCutOffDateInSourcesLine(commonFunc.getDataFromTestDataSheet("Unit_100"), NextMonthToday);
			mrpFunc.clickSalesOrderDemandCheckBoxInSourceLines();
			mrpFunc.clickSaveIconInSourcesTab();
			mrpFunc.clickLoadDemandButtonInMRP();
			mrpFunc.navigateToDemandTabInMRPScreen();
			mrpFunc.searchSONumberInDemandAndSelectSOItems(VariableHandler.getSONumber());
			mrpFunc.clickCheckExceptionAndSaveHeaderInMRP();
			mrpFunc.navigateToExceptionTabInMRPScreen();
			mrpFunc.validateNoExceptionDataShownInTableData();
			mrpFunc.clickRunMRPButton();
			mrpFunc.navigateToResultsTabInMRPScreen();
			mrpFunc.verifyUnitResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100") });
			mrpFunc.verifyItemResultsDetails(new String[] { FGItem, RMItem });
			mrpFunc.verifyUOMResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("UomNos"),
					commonFunc.getDataFromTestDataSheet("Kgs") });
			mrpFunc.clickHeaderSaveAndClickPost();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRPNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			mrpFunc.searchAndClickMRPNoInFindPage(VariableHandler.getMRPNumber());
			mrpFunc.clickReleaseButtonInMRP();
			mrpFunc.chooseOrderTypeFromAndToDateInReleaseDetails("All", YesterDay, Today);
			mrpFunc.clickReleaseButtonInReleaseFrame();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ReleasedStatus"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfMRPNo(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("UnreleasedStatus"));
			webDB.navigateToRefresh();
			searchAndSelectProductionOrderWithDocNo(VariableHandler.getProductionOrderNumber());
			verifyWriteBeforeStockForFGAndRawMaterials(FileName, SheetName);
			verifyHeaderValidationInProductionOrderWithDB(FileName, SheetName);
			verifyProductionOrderMREventAndRestOP(null, null, MoveType);
			verifyProcessType(commonFunc.getDataFromTestDataSheet("OutsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyRoutingValidationInProductionOrderWithDB(FileName, SheetName);
			verifyRawMaterialsValidationInProductionOrderWithDB(FileName, SheetName);
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndex = 0;
			int AllocIndex = 0;
			a: for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"))) {
					StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("MultipleType"))) {
					StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
					if (i > 1) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ItemType"))) {
					StartIndex = (i == 0) ? 35 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideItemMoveType(StartIndex, AllocIndex, FileName, SheetName);
					if (i > 0) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"))) {
					productionOrderOutsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("MultipleType"))) {
					productionOrderOutsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
					if (i > 1) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ItemType"))) {
					productionOrderOutsideItemMoveType(StartIndex, AllocIndex, FileName, SheetName);
					if (i > 0) {
						break a;
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(FileName, SheetName);
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyProdOrderAgainstSOFirstOutsideRestInsideMoveTypeMultiple': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Production Order against sales Order with move type -
	 * item
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderAgainstSOFirstOutsideRestInsideMoveTypeItem() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String MRPName = "TEST MRP - " + RandomStringUtils.randomNumeric(3);
		String Today = date.format(formatter);
		LocalDate nextmonthdate = LocalDate.now().plusMonths(1);
		String NextMonthToday = nextmonthdate.format(formatter);
		String FGItem = commonFunc.getDataFromTestDataSheet("FG-10001-Item");
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		LocalDate previousDate = LocalDate.now().minusDays(1);
		String YesterDay = previousDate.format(formatter);
		int ReqQuantity = 4;
		String BOMNo = commonFunc.getDataFromTestDataSheet("BomFirstOutRestIn");
		String MoveType = commonFunc.getDataFromTestDataSheet("ItemType");
		String FileName = webDB.getDataFromProperties("PMFFileName");
		String SheetName = webDB.getDataFromProperties("FirstOutRestInSheetName-Item");
		try {
			saleOrderFunc.verifySalesOrderCreationForMRPAndProdOrder(ReqQuantity, BOMNo);
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.clickAddIconInMRP();
			mrpFunc.fillDetailsInMRPCreationAndClickTickIcon(Today, MRPName, Today, "ALL");
			mrpFunc.clickAddLinePlusIconInSources();
			mrpFunc.enterUnitCutOffDateInSourcesLine(commonFunc.getDataFromTestDataSheet("Unit_100"), NextMonthToday);
			mrpFunc.clickSalesOrderDemandCheckBoxInSourceLines();
			mrpFunc.clickSaveIconInSourcesTab();
			mrpFunc.clickLoadDemandButtonInMRP();
			mrpFunc.navigateToDemandTabInMRPScreen();
			mrpFunc.searchSONumberInDemandAndSelectSOItems(VariableHandler.getSONumber());
			mrpFunc.clickCheckExceptionAndSaveHeaderInMRP();
			mrpFunc.navigateToExceptionTabInMRPScreen();
			mrpFunc.validateNoExceptionDataShownInTableData();
			mrpFunc.clickRunMRPButton();
			mrpFunc.navigateToResultsTabInMRPScreen();
			mrpFunc.verifyUnitResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100") });
			mrpFunc.verifyItemResultsDetails(new String[] { FGItem, RMItem });
			mrpFunc.verifyUOMResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("UomNos"),
					commonFunc.getDataFromTestDataSheet("Kgs") });
			mrpFunc.clickHeaderSaveAndClickPost();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRPNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			mrpFunc.searchAndClickMRPNoInFindPage(VariableHandler.getMRPNumber());
			mrpFunc.clickReleaseButtonInMRP();
			mrpFunc.chooseOrderTypeFromAndToDateInReleaseDetails("All", YesterDay, Today);
			mrpFunc.clickReleaseButtonInReleaseFrame();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ReleasedStatus"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfMRPNo(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("UnreleasedStatus"));
			webDB.navigateToRefresh();
			searchAndSelectProductionOrderWithDocNo(VariableHandler.getProductionOrderNumber());
			verifyWriteBeforeStockForFGAndRawMaterials(FileName, SheetName);
			verifyHeaderValidationInProductionOrderWithDB(FileName, SheetName);
			verifyProductionOrderMREventAndRestOP(null, null, MoveType);
			verifyProcessType(commonFunc.getDataFromTestDataSheet("OutsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyRoutingValidationInProductionOrderWithDB(FileName, SheetName);
			verifyRawMaterialsValidationInProductionOrderWithDB(FileName, SheetName);
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndex = 0;
			int AllocIndex = 0;
			a: for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"))) {
					StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("MultipleType"))) {
					StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
					if (i > 1) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ItemType"))) {
					StartIndex = (i == 0) ? 35 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideItemMoveType(StartIndex, AllocIndex, FileName, SheetName);
					if (i > 0) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"))) {
					productionOrderOutsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("MultipleType"))) {
					productionOrderOutsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
					if (i > 1) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ItemType"))) {
					productionOrderOutsideItemMoveType(StartIndex, AllocIndex, FileName, SheetName);
					if (i > 0) {
						break a;
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(FileName, SheetName);
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyProdOrderAgainstSOFirstOutsideRestInsideMoveTypeMultiple': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Production Order against sales Order with move type -
	 * single
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderAgainstSOFirstOutsideRestBothMoveTypeSingle() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String MRPName = "TEST MRP - " + RandomStringUtils.randomNumeric(3);
		String Today = date.format(formatter);
		LocalDate nextmonthdate = LocalDate.now().plusMonths(1);
		String NextMonthToday = nextmonthdate.format(formatter);
		String FGItem = commonFunc.getDataFromTestDataSheet("FG-10001-Item");
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		LocalDate previousDate = LocalDate.now().minusDays(1);
		String YesterDay = previousDate.format(formatter);
		int ReqQuantity = 4;
		String BOMNo = commonFunc.getDataFromTestDataSheet("BomFirstOutRestBoth");
		String MoveType = commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus");
		String FileName = webDB.getDataFromProperties("PMFFileName");
		String SheetName = webDB.getDataFromProperties("FirstOutRestBothSheetName-Single");
		try {
			saleOrderFunc.verifySalesOrderCreationForMRPAndProdOrder(ReqQuantity, BOMNo);
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.clickAddIconInMRP();
			mrpFunc.fillDetailsInMRPCreationAndClickTickIcon(Today, MRPName, Today, "ALL");
			mrpFunc.clickAddLinePlusIconInSources();
			mrpFunc.enterUnitCutOffDateInSourcesLine(commonFunc.getDataFromTestDataSheet("Unit_100"), NextMonthToday);
			mrpFunc.clickSalesOrderDemandCheckBoxInSourceLines();
			mrpFunc.clickSaveIconInSourcesTab();
			mrpFunc.clickLoadDemandButtonInMRP();
			mrpFunc.navigateToDemandTabInMRPScreen();
			mrpFunc.searchSONumberInDemandAndSelectSOItems(VariableHandler.getSONumber());
			mrpFunc.clickCheckExceptionAndSaveHeaderInMRP();
			mrpFunc.navigateToExceptionTabInMRPScreen();
			mrpFunc.validateNoExceptionDataShownInTableData();
			mrpFunc.clickRunMRPButton();
			mrpFunc.navigateToResultsTabInMRPScreen();
			mrpFunc.verifyUnitResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100") });
			mrpFunc.verifyItemResultsDetails(new String[] { FGItem, RMItem });
			mrpFunc.verifyUOMResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("UomNos"),
					commonFunc.getDataFromTestDataSheet("Kgs") });
			mrpFunc.clickHeaderSaveAndClickPost();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRPNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			mrpFunc.searchAndClickMRPNoInFindPage(VariableHandler.getMRPNumber());
			mrpFunc.clickReleaseButtonInMRP();
			mrpFunc.chooseOrderTypeFromAndToDateInReleaseDetails("All", YesterDay, Today);
			mrpFunc.clickReleaseButtonInReleaseFrame();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ReleasedStatus"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfMRPNo(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("UnreleasedStatus"));
			webDB.navigateToRefresh();
			searchAndSelectProductionOrderWithDocNo(VariableHandler.getProductionOrderNumber());
			verifyWriteBeforeStockForFGAndRawMaterials(FileName, SheetName);
			verifyHeaderValidationInProductionOrderWithDB(FileName, SheetName);
			verifyProductionOrderMREventAndRestOP(null, null, MoveType);
			verifyProcessType(commonFunc.getDataFromTestDataSheet("OutsideType"),
					commonFunc.getDataFromTestDataSheet("BothType"));
			verifyRoutingValidationInProductionOrderWithDB(FileName, SheetName);
			verifyRawMaterialsValidationInProductionOrderWithDB(FileName, SheetName);
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndex = 0;
			int AllocIndex = 0;
			a: for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"))) {
					StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("MultipleType"))) {
					StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
					if (i > 1) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ItemType"))) {
					StartIndex = (i == 0) ? 35 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideItemMoveType(StartIndex, AllocIndex, FileName, SheetName);
					if (i > 0) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"))) {
					productionOrderOutsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("MultipleType"))) {
					productionOrderOutsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
					if (i > 1) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ItemType"))) {
					productionOrderOutsideItemMoveType(StartIndex, AllocIndex, FileName, SheetName);
					if (i > 0) {
						break a;
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(FileName, SheetName);
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyProdOrderAgainstSOFirstOutsideRestBothMoveTypeSingle': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Production Order against sales Order with move type -
	 * multiple
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderAgainstSOFirstOutsideRestBothMoveTypeMultiple() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String MRPName = "TEST MRP - " + RandomStringUtils.randomNumeric(3);
		String Today = date.format(formatter);
		LocalDate nextmonthdate = LocalDate.now().plusMonths(1);
		String NextMonthToday = nextmonthdate.format(formatter);
		String FGItem = commonFunc.getDataFromTestDataSheet("FG-10001-Item");
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		LocalDate previousDate = LocalDate.now().minusDays(1);
		String YesterDay = previousDate.format(formatter);
		int ReqQuantity = 4;
		String BOMNo = commonFunc.getDataFromTestDataSheet("BomFirstOutRestBoth");
		String MoveType = commonFunc.getDataFromTestDataSheet("MultipleType");
		String FileName = webDB.getDataFromProperties("PMFFileName");
		String SheetName = webDB.getDataFromProperties("FirstOutRestBothSheetName-Multiple");
		try {
			saleOrderFunc.verifySalesOrderCreationForMRPAndProdOrder(ReqQuantity, BOMNo);
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.clickAddIconInMRP();
			mrpFunc.fillDetailsInMRPCreationAndClickTickIcon(Today, MRPName, Today, "ALL");
			mrpFunc.clickAddLinePlusIconInSources();
			mrpFunc.enterUnitCutOffDateInSourcesLine(commonFunc.getDataFromTestDataSheet("Unit_100"), NextMonthToday);
			mrpFunc.clickSalesOrderDemandCheckBoxInSourceLines();
			mrpFunc.clickSaveIconInSourcesTab();
			mrpFunc.clickLoadDemandButtonInMRP();
			mrpFunc.navigateToDemandTabInMRPScreen();
			mrpFunc.searchSONumberInDemandAndSelectSOItems(VariableHandler.getSONumber());
			mrpFunc.clickCheckExceptionAndSaveHeaderInMRP();
			mrpFunc.navigateToExceptionTabInMRPScreen();
			mrpFunc.validateNoExceptionDataShownInTableData();
			mrpFunc.clickRunMRPButton();
			mrpFunc.navigateToResultsTabInMRPScreen();
			mrpFunc.verifyUnitResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100") });
			mrpFunc.verifyItemResultsDetails(new String[] { FGItem, RMItem });
			mrpFunc.verifyUOMResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("UomNos"),
					commonFunc.getDataFromTestDataSheet("Kgs") });
			mrpFunc.clickHeaderSaveAndClickPost();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRPNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			mrpFunc.searchAndClickMRPNoInFindPage(VariableHandler.getMRPNumber());
			mrpFunc.clickReleaseButtonInMRP();
			mrpFunc.chooseOrderTypeFromAndToDateInReleaseDetails("All", YesterDay, Today);
			mrpFunc.clickReleaseButtonInReleaseFrame();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ReleasedStatus"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfMRPNo(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("UnreleasedStatus"));
			webDB.navigateToRefresh();
			searchAndSelectProductionOrderWithDocNo(VariableHandler.getProductionOrderNumber());
			verifyWriteBeforeStockForFGAndRawMaterials(FileName, SheetName);
			verifyHeaderValidationInProductionOrderWithDB(FileName, SheetName);
			verifyProductionOrderMREventAndRestOP(null, null, MoveType);
			verifyProcessType(commonFunc.getDataFromTestDataSheet("OutsideType"),
					commonFunc.getDataFromTestDataSheet("BothType"));
			verifyRoutingValidationInProductionOrderWithDB(FileName, SheetName);
			verifyRawMaterialsValidationInProductionOrderWithDB(FileName, SheetName);
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndex = 0;
			int AllocIndex = 0;
			a: for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"))) {
					StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("MultipleType"))) {
					StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
					if (i > 1) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ItemType"))) {
					StartIndex = (i == 0) ? 35 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideItemMoveType(StartIndex, AllocIndex, FileName, SheetName);
					if (i > 0) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"))) {
					productionOrderOutsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("MultipleType"))) {
					productionOrderOutsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
					if (i > 1) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ItemType"))) {
					productionOrderOutsideItemMoveType(StartIndex, AllocIndex, FileName, SheetName);
					if (i > 0) {
						break a;
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(FileName, SheetName);
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyProdOrderAgainstSOFirstOutsideRestBothMoveTypeMultiple': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Production Order against sales Order with move type -
	 * item
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderAgainstSOFirstOutsideRestBothMoveTypeItem() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String MRPName = "TEST MRP - " + RandomStringUtils.randomNumeric(3);
		String Today = date.format(formatter);
		LocalDate nextmonthdate = LocalDate.now().plusMonths(1);
		String NextMonthToday = nextmonthdate.format(formatter);
		String FGItem = commonFunc.getDataFromTestDataSheet("FG-10001-Item");
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		LocalDate previousDate = LocalDate.now().minusDays(1);
		String YesterDay = previousDate.format(formatter);
		int ReqQuantity = 4;
		String BOMNo = commonFunc.getDataFromTestDataSheet("BomFirstOutRestBoth");
		String MoveType = commonFunc.getDataFromTestDataSheet("ItemType");
		String FileName = webDB.getDataFromProperties("PMFFileName");
		String SheetName = webDB.getDataFromProperties("FirstOutRestBothSheetName-Item");
		try {
			saleOrderFunc.verifySalesOrderCreationForMRPAndProdOrder(ReqQuantity, BOMNo);
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.clickAddIconInMRP();
			mrpFunc.fillDetailsInMRPCreationAndClickTickIcon(Today, MRPName, Today, "ALL");
			mrpFunc.clickAddLinePlusIconInSources();
			mrpFunc.enterUnitCutOffDateInSourcesLine(commonFunc.getDataFromTestDataSheet("Unit_100"), NextMonthToday);
			mrpFunc.clickSalesOrderDemandCheckBoxInSourceLines();
			mrpFunc.clickSaveIconInSourcesTab();
			mrpFunc.clickLoadDemandButtonInMRP();
			mrpFunc.navigateToDemandTabInMRPScreen();
			mrpFunc.searchSONumberInDemandAndSelectSOItems(VariableHandler.getSONumber());
			mrpFunc.clickCheckExceptionAndSaveHeaderInMRP();
			mrpFunc.navigateToExceptionTabInMRPScreen();
			mrpFunc.validateNoExceptionDataShownInTableData();
			mrpFunc.clickRunMRPButton();
			mrpFunc.navigateToResultsTabInMRPScreen();
			mrpFunc.verifyUnitResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100") });
			mrpFunc.verifyItemResultsDetails(new String[] { FGItem, RMItem });
			mrpFunc.verifyUOMResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("UomNos"),
					commonFunc.getDataFromTestDataSheet("Kgs") });
			mrpFunc.clickHeaderSaveAndClickPost();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRPNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			mrpFunc.searchAndClickMRPNoInFindPage(VariableHandler.getMRPNumber());
			mrpFunc.clickReleaseButtonInMRP();
			mrpFunc.chooseOrderTypeFromAndToDateInReleaseDetails("All", YesterDay, Today);
			mrpFunc.clickReleaseButtonInReleaseFrame();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ReleasedStatus"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfMRPNo(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("UnreleasedStatus"));
			webDB.navigateToRefresh();
			searchAndSelectProductionOrderWithDocNo(VariableHandler.getProductionOrderNumber());
			verifyWriteBeforeStockForFGAndRawMaterials(FileName, SheetName);
			verifyHeaderValidationInProductionOrderWithDB(FileName, SheetName);
			verifyProductionOrderMREventAndRestOP(null, null, MoveType);
			verifyProcessType(commonFunc.getDataFromTestDataSheet("OutsideType"),
					commonFunc.getDataFromTestDataSheet("BothType"));
			verifyRoutingValidationInProductionOrderWithDB(FileName, SheetName);
			verifyRawMaterialsValidationInProductionOrderWithDB(FileName, SheetName);
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndex = 0;
			int AllocIndex = 0;
			a: for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"))) {
					StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("MultipleType"))) {
					StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
					if (i > 1) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("InsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ItemType"))) {
					StartIndex = (i == 0) ? 35 : 0;
					AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
					productionOrderInsideItemMoveType(StartIndex, AllocIndex, FileName, SheetName);
					if (i > 0) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"))) {
					productionOrderOutsideSingleMoveType(StartIndex, AllocIndex, FileName, SheetName);
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("MultipleType"))) {
					productionOrderOutsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
					if (i > 1) {
						break a;
					}
				} else if (ProcessType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("OutsideType"))
						&& MoveType.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ItemType"))) {
					productionOrderOutsideItemMoveType(StartIndex, AllocIndex, FileName, SheetName);
					if (i > 0) {
						break a;
					}
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(FileName, SheetName);
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occur while executing 'verifyProdOrderAgainstSOFirstOutsideRestBothMoveTypeItem': " + e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Single
	 * 
	 * @author Sakthi-GWL
	 * @return flag
	 */
	public boolean verifyProdOrderAgainstSOAllBothPartialQuantityMoveTypeSingle() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String MRPName = "TEST MRP - " + RandomStringUtils.randomNumeric(3);
		String Today = date.format(formatter);
		LocalDate nextmonthdate = LocalDate.now().plusMonths(1);
		String NextMonthToday = nextmonthdate.format(formatter);
		String FGItem = commonFunc.getDataFromTestDataSheet("FG-10001-Item");
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		LocalDate previousDate = LocalDate.now().minusDays(1);
		String YesterDay = previousDate.format(formatter);
		int ReqQuantity = 4;
		String BOMNo = commonFunc.getDataFromTestDataSheet("BomAllBoth");
		String MoveType = commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus");
		String FileName = webDB.getDataFromProperties("PMFFileName");
		String SheetName = webDB.getDataFromProperties("AllBothParQuantitySheetName-Single");
		try {
			saleOrderFunc.verifySalesOrderCreationForMRPAndProdOrder(ReqQuantity, BOMNo);
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.clickAddIconInMRP();
			mrpFunc.fillDetailsInMRPCreationAndClickTickIcon(Today, MRPName, Today, "ALL");
			mrpFunc.clickAddLinePlusIconInSources();
			mrpFunc.enterUnitCutOffDateInSourcesLine(commonFunc.getDataFromTestDataSheet("Unit_100"), NextMonthToday);
			mrpFunc.clickSalesOrderDemandCheckBoxInSourceLines();
			mrpFunc.clickSaveIconInSourcesTab();
			mrpFunc.clickLoadDemandButtonInMRP();
			mrpFunc.navigateToDemandTabInMRPScreen();
			mrpFunc.searchSONumberInDemandAndSelectSOItems(VariableHandler.getSONumber());
			mrpFunc.clickCheckExceptionAndSaveHeaderInMRP();
			mrpFunc.navigateToExceptionTabInMRPScreen();
			mrpFunc.validateNoExceptionDataShownInTableData();
			mrpFunc.clickRunMRPButton();
			mrpFunc.navigateToResultsTabInMRPScreen();
			mrpFunc.verifyUnitResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100") });
			mrpFunc.verifyItemResultsDetails(new String[] { FGItem, RMItem });
			mrpFunc.verifyUOMResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("UomNos"),
					commonFunc.getDataFromTestDataSheet("Kgs") });
			mrpFunc.clickHeaderSaveAndClickPost();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRPNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			mrpFunc.searchAndClickMRPNoInFindPage(VariableHandler.getMRPNumber());
			mrpFunc.clickReleaseButtonInMRP();
			mrpFunc.chooseOrderTypeFromAndToDateInReleaseDetails("All", YesterDay, Today);
			mrpFunc.clickReleaseButtonInReleaseFrame();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ReleasedStatus"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfMRPNo(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("UnreleasedStatus"));
			webDB.navigateToRefresh();
			searchAndSelectProductionOrderWithDocNo(VariableHandler.getProductionOrderNumber());
			verifyWriteBeforeStockForFGAndRawMaterials(FileName, SheetName);
			verifyHeaderValidationInProductionOrderWithDB(FileName, SheetName);
			verifyProductionOrderMREventAndRestOP(null, null, MoveType);
			verifyProcessType(commonFunc.getDataFromTestDataSheet("BothType"),
					commonFunc.getDataFromTestDataSheet("BothType"));
			verifyRoutingValidationInProductionOrderWithDB(FileName, SheetName);
			verifyRawMaterialsValidationInProductionOrderWithDB(FileName, SheetName);
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndex = 0;
			int AllocIndex = 0;
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc
						.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc.enterRequestQuantityInPendingMRCreation(
						VariableHandler.getProductionOrderNumber(), String.valueOf((ReqQuantity / 2)));
				completionProductionOrderFunc
						.clickCheckBoxTableAndCreateAndStoreMRNo(VariableHandler.getProductionOrderNumber());
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc
						.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
				materialRequestFunc.verifyHeaderValidationInMaterialRequestWithDB(FileName, SheetName, StartIndex);
				materialIssuanceVoucherFunc.verifyWriteBeforeLotQuantityInLotForRawMaterials(FileName, SheetName,
						StartIndex);
				commonFunc.navigateToMaterialIssuanceVoucherTab();
				materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
				materialIssuanceVoucherFunc.clickheaderSaveIconInMIV();
				verifyWriteAllocateStockForRawMaterials(FileName, SheetName, AllocIndex);
				materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
				commonFunc.navigateToMaterialIssuanceVoucherTab();
				materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				materialIssuanceVoucherFunc.verifyHeaderAndLinesValidationInMaterialIssuanceVoucherWithDB(FileName,
						SheetName, StartIndex);
				materialIssuanceVoucherFunc.verifyStockTransactionValidationInMIVForRawMaterials(FileName, SheetName,
						StartIndex);
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {
						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
				materialIssuanceVoucherFunc.verifyAfterLotQuantityInLotSerialValidationForRawMaterials(FileName,
						SheetName, StartIndex);
				materialIssuanceVoucherFunc.verifyStockTransactionValidationInMIVForRawMaterials(FileName, SheetName,
						StartIndex);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
						commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc.clickAllocateDeallocateButtonInHeader();
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
				completionProductionOrderFunc.verifyLinesWithRMValidationInCompletionProcessWithDB(FileName, SheetName,
						StartIndex);
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
				completionProductionOrderFunc.verifyHeaderAndLinesValidationInCompletionProcessWithDB(FileName,
						SheetName, StartIndex);
				completionProductionOrderFunc.verifyStockTransactionValidationInCompletionForRawMaterials(FileName,
						SheetName, StartIndex);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
						VariableHandler.getProductionCompletionOrderNo(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
				// Outside
				commonFunc.navigateToSubcontractOrder();
				subContractOrderFunc.verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
				subContractOrderFunc.verifyHeaderValidation(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
						commonFunc.getDataFromTestDataSheet("Unit_100"),
						commonFunc.getDataFromTestDataSheet("SubcontractorName"));
				subContractOrderFunc.verifyProcessQuantityCalculation();
				subContractOrderFunc.verifyAddHsnCode();
				subContractOrderFunc.verifyAddGlAccNumberValue();
				subContractOrderFunc.verifyIncludeChargesAndPostIcon();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getScoNumber(),
							webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
							System.getProperty("HariUsername"));
					String Text = VariableHandler.getLastApprovalAPIHeaderText()
							.replace("Material Request Created. :", "").trim();
					Text = Text.replace("SUBCONTRACT ORDER Document is Approved.", "").trim();
					VariableHandler.setMRNumber(Text);
					VariableHandler.setMaterialRequestNo(Text);
				}
				subContractOrderFunc.verifyValidateSubContractDocumentsStatus(VariableHandler.getScoNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
				materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getScoNumber());
				materialRequestFunc.headerSaveAndPost();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRNumber(),
							webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
							System.getProperty("HariUsername"));
				}
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc.checkStatusOfMRNo(VariableHandler.getMRNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.navigateToMaterialIssuanceVoucher();
				materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMRNumber());
				materialIssuanceVoucherFunc.headerTickAndPostIcon();
				materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceNumber(VariableHandler.getMIVNumber(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				commonFunc.navigateToGateEntryDeliveryChallan();
				deliveryChallanFunc.verifyStatusOfDeliveryChallan(VariableHandler.getDCNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getScoNumber());
				grnScoPageFunc.addHeatNumberAndTestNumber();
				grnScoPageFunc.checkConsumptionBeforeGenerationMat();
				grnScoPageFunc.verifyGenMatConsAndAllocate();
				grnScoPageFunc.checkConsumptionAfterGenerationMat();
				grnScoPageFunc.verifyViewLineDetails();
				grnScoPageFunc.saveHeaderAndPost();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
							VariableHandler.getScoGrnNumber(), webDB.getDataFromProperties("HodEmpId"),
							commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
				}
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.verifyValidateGrnScoStatus(VariableHandler.getScoGrnNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {
						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(FileName, SheetName);
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderAgainstSOAllBothPartialQuantityMoveTypeSingle': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Multiple
	 * 
	 * @author Sakthi-GWL
	 * @return flag
	 */
	public boolean verifyProdOrderAgainstSOAllBothPartialQuantityMoveTypeMultiple() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String MRPName = "TEST MRP - " + RandomStringUtils.randomNumeric(3);
		String Today = date.format(formatter);
		LocalDate nextmonthdate = LocalDate.now().plusMonths(1);
		String NextMonthToday = nextmonthdate.format(formatter);
		String FGItem = commonFunc.getDataFromTestDataSheet("FG-10001-Item");
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		LocalDate previousDate = LocalDate.now().minusDays(1);
		String YesterDay = previousDate.format(formatter);
		int ReqQuantity = 4;
		String BOMNo = commonFunc.getDataFromTestDataSheet("BomAllBoth");
		String MoveType = commonFunc.getDataFromTestDataSheet("MultipleType");
		String FileName = webDB.getDataFromProperties("PMFFileName");
		String SheetName = webDB.getDataFromProperties("AllBothParQuantitySheetName-Multiple");
		try {
			saleOrderFunc.verifySalesOrderCreationForMRPAndProdOrder(ReqQuantity, BOMNo);
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.clickAddIconInMRP();
			mrpFunc.fillDetailsInMRPCreationAndClickTickIcon(Today, MRPName, Today, "ALL");
			mrpFunc.clickAddLinePlusIconInSources();
			mrpFunc.enterUnitCutOffDateInSourcesLine(commonFunc.getDataFromTestDataSheet("Unit_100"), NextMonthToday);
			mrpFunc.clickSalesOrderDemandCheckBoxInSourceLines();
			mrpFunc.clickSaveIconInSourcesTab();
			mrpFunc.clickLoadDemandButtonInMRP();
			mrpFunc.navigateToDemandTabInMRPScreen();
			mrpFunc.searchSONumberInDemandAndSelectSOItems(VariableHandler.getSONumber());
			mrpFunc.clickCheckExceptionAndSaveHeaderInMRP();
			mrpFunc.navigateToExceptionTabInMRPScreen();
			mrpFunc.validateNoExceptionDataShownInTableData();
			mrpFunc.clickRunMRPButton();
			mrpFunc.navigateToResultsTabInMRPScreen();
			mrpFunc.verifyUnitResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100") });
			mrpFunc.verifyItemResultsDetails(new String[] { FGItem, RMItem });
			mrpFunc.verifyUOMResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("UomNos"),
					commonFunc.getDataFromTestDataSheet("Kgs") });
			mrpFunc.clickHeaderSaveAndClickPost();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRPNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			mrpFunc.searchAndClickMRPNoInFindPage(VariableHandler.getMRPNumber());
			mrpFunc.clickReleaseButtonInMRP();
			mrpFunc.chooseOrderTypeFromAndToDateInReleaseDetails("All", YesterDay, Today);
			mrpFunc.clickReleaseButtonInReleaseFrame();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ReleasedStatus"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfMRPNo(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("UnreleasedStatus"));
			webDB.navigateToRefresh();
			searchAndSelectProductionOrderWithDocNo(VariableHandler.getProductionOrderNumber());
			verifyWriteBeforeStockForFGAndRawMaterials(FileName, SheetName);
			verifyHeaderValidationInProductionOrderWithDB(FileName, SheetName);
			verifyProductionOrderMREventAndRestOP(null, null, MoveType);
			verifyProcessType(commonFunc.getDataFromTestDataSheet("BothType"),
					commonFunc.getDataFromTestDataSheet("BothType"));
			verifyRoutingValidationInProductionOrderWithDB(FileName, SheetName);
			verifyRawMaterialsValidationInProductionOrderWithDB(FileName, SheetName);
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndex = 0;
			int AllocIndex = 0;
			a: for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : 0;
				AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				// Inside
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc.enterRequestQuantityInPendingMRCreation(
						VariableHandler.getProductionOrderNumber(), String.valueOf((ReqQuantity / 2)));
				completionProductionOrderFunc
						.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
				if (!(i == 0)) {
					completionProductionOrderFunc
							.clickProcessDetailsAndSelectAllProcess(VariableHandler.getProductionOrderNumber());
				}
				completionProductionOrderFunc
						.clickCheckBoxTableAndCreateAndStoreMRNo(VariableHandler.getProductionOrderNumber());
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc
						.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
				materialRequestFunc.verifyHeaderValidationInMaterialRequestWithDB(FileName, SheetName, StartIndex);
				materialIssuanceVoucherFunc.verifyWriteBeforeLotQuantityInLotForRawMaterials(FileName, SheetName,
						StartIndex);
				commonFunc.navigateToMaterialIssuanceVoucherTab();
				materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
				materialIssuanceVoucherFunc.clickheaderSaveIconInMIV();
				if (!(i == 0)) {
					AllocIndex = 61;
				}
				verifyWriteAllocateStockForRawMaterials(FileName, SheetName, AllocIndex);
				materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
				commonFunc.navigateToMaterialIssuanceVoucherTab();
				materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				if (!(i == 0)) {
					StartIndex = 61;
				}
				materialIssuanceVoucherFunc.verifyHeaderAndLinesValidationInMaterialIssuanceVoucherWithDB(FileName,
						SheetName, StartIndex);
				materialIssuanceVoucherFunc.verifyStockTransactionValidationInMIVForRawMaterials(FileName, SheetName,
						StartIndex);
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {
						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
				materialIssuanceVoucherFunc.verifyAfterLotQuantityInLotSerialValidationForRawMaterials(FileName,
						SheetName, StartIndex);
				materialIssuanceVoucherFunc.verifyStockTransactionValidationInMIVForRawMaterials(FileName, SheetName,
						StartIndex);
				commonFunc.navigateToCompletionProductionOrderMultipleProcessInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionMultiProcess();
				if (i == 0) {
					completionProductionOrderFunc
							.selectPendingMultipleProdOrderAndClickCreateCompletionProcessWithNoButton(
									VariableHandler.getProductionOrderNumber());
				} else {
					completionProductionOrderFunc.selectPendingMultipleProdOrderAndClickCreateCompletionProcess(
							VariableHandler.getProductionOrderNumber());
				}
				completionProductionOrderFunc.clickAllocateDeallocateButtonInHeader();
				completionProductionOrderFunc.selectShiftAndSaveHeaderInCompletionMultiProcess(
						commonFunc.getDataFromTestDataSheet("Shift_100"));
				completionProductionOrderFunc.verifyLinesWithRMValidationInCompletionProcessWithDB(FileName, SheetName,
						StartIndex);
				completionProductionOrderFunc.clickPostButtonInCompletionMultiProcess();
				completionProductionOrderFunc.verifyHeaderAndLinesValidationInCompletionProcessWithDB(FileName,
						SheetName, StartIndex);
				completionProductionOrderFunc.verifyStockTransactionValidationInCompletionForRawMaterials(FileName,
						SheetName, StartIndex);
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
				if (i > 1) {
					break a;
				}
				// Outside
				commonFunc.navigateToSubcontractOrder();
				if (!(i == 0)) {
					subContractOrderFunc
							.verifySearchPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
					subContractOrderFunc
							.clickProcessDetailsAndSelectAllProcess(VariableHandler.getProductionOrderNumber());
				}
				subContractOrderFunc.verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
				subContractOrderFunc.verifyHeaderValidation(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
						commonFunc.getDataFromTestDataSheet("Unit_100"),
						commonFunc.getDataFromTestDataSheet("SubcontractorName"));
				subContractOrderFunc.verifyProcessQuantityCalculation();
				subContractOrderFunc.verifyAddHsnCode();
				subContractOrderFunc.verifyAddGlAccNumberValue();
				subContractOrderFunc.checkSucontractOrderHeaderDetailsDataBaseValuesWithUIValues();
				subContractOrderFunc.clickLocationDetailsButtonValidationwithDataBase();
				subContractOrderFunc.checkSucontractOrderLineDetailsDataBaseValuesWithUIValues();
				subContractOrderFunc.storeBatchQtyAndBtachCost();
				subContractOrderFunc.getBatchQtyBatchCostandWriteInExcelSheet(FileName, SheetName, StartIndex);
				subContractOrderFunc.processCalculationCompareExcelSheetWithDataBase(FileName, SheetName, StartIndex);
				subContractOrderFunc.clickingMRPDetailsTabVerificationWithBOMAgainst();
				subContractOrderFunc.ValueAdditionProcessSourceAndTargetSFcode();
				subContractOrderFunc.pendingProdNoandSOrefsubcontractStandardTypeclickingIncludeChargesAndPostIcon();
				subContractOrderFunc.headerTaxAmtAndTotalAmtCompareExcelSheetWithDataBase(FileName, SheetName,
						StartIndex);
				subContractOrderFunc.verifyIncludeChargesAndPostIcon();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getScoNumber(),
							webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
							System.getProperty("HariUsername"));
					String Text = VariableHandler.getLastApprovalAPIHeaderText()
							.replace("Material Request Created. :", "").trim();
					Text = Text.replace("SUBCONTRACT ORDER Document is Approved.", "").trim();
					VariableHandler.setMRNumber(Text);
					VariableHandler.setMaterialRequestNo(Text);
				}
				subContractOrderFunc.verifyValidateSubContractDocumentsStatus(VariableHandler.getScoNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
				materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getScoNumber());
				materialRequestFunc.headerSaveAndPost();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRNumber(),
							webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
							System.getProperty("HariUsername"));
				}
				materialRequestFunc.verifyHeaderValidationInMaterialRequestWithDB(FileName, SheetName, StartIndex);
				commonFunc.navigateToMaterialIssuanceVoucherTab();
				materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
				materialIssuanceVoucherFunc.clickheaderSaveIconInMIV();
				materialIssuanceVoucherFunc.headerTickAndPostIcon();
				commonFunc.navigateToMaterialIssuanceVoucherTab();
				materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {
						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
				materialIssuanceVoucherFunc.verifyAfterLotQuantityInLotSerialValidationForRawMaterials(FileName,
						SheetName, StartIndex);
				materialIssuanceVoucherFunc.verifyStockTransactionValidationInMIVForRawMaterials(FileName, SheetName,
						StartIndex);
				commonFunc.navigateToGateEntryDeliveryChallan();
				deliveryChallanFunc.verifyStatusOfDeliveryChallan(VariableHandler.getDCNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getScoNumber());
				grnScoPageFunc.addHeatNumberAndTestNumber();
				grnScoPageFunc.checkConsumptionBeforeGenerationMat();
				grnScoPageFunc.verifyGenMatConsAndAllocate();
				grnScoPageFunc.checkConsumptionAfterGenerationMat();
				grnScoPageFunc.verifyViewLineDetails();
				grnScoPageFunc.saveHeaderAndPost();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
							VariableHandler.getScoGrnNumber(), webDB.getDataFromProperties("HodEmpId"),
							commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
				}
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.verifyValidateGrnScoStatus(VariableHandler.getScoGrnNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {
						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(FileName, SheetName);
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllBothPartialQuantityMoveTypeMultiple': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Item
	 * 
	 * @author Sakthi-GWL
	 * @return flag
	 */
	public boolean verifyProdOrderAgainstSOAllBothPartialQuantityMoveTypeItem() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String MRPName = "TEST MRP - " + RandomStringUtils.randomNumeric(3);
		String Today = date.format(formatter);
		LocalDate nextmonthdate = LocalDate.now().plusMonths(1);
		String NextMonthToday = nextmonthdate.format(formatter);
		String FGItem = commonFunc.getDataFromTestDataSheet("FG-10001-Item");
		String RMItem = commonFunc.getDataFromTestDataSheet("RM-10001-Item");
		LocalDate previousDate = LocalDate.now().minusDays(1);
		String YesterDay = previousDate.format(formatter);
		int ReqQuantity = 4;
		String BOMNo = commonFunc.getDataFromTestDataSheet("BomAllBoth");
		String MoveType = commonFunc.getDataFromTestDataSheet("ItemType");
		String FileName = webDB.getDataFromProperties("PMFFileName");
		String SheetName = webDB.getDataFromProperties("AllBothParQuantitySheetName-Item");
		try {
			saleOrderFunc.verifySalesOrderCreationForMRPAndProdOrder(ReqQuantity, BOMNo);
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.clickAddIconInMRP();
			mrpFunc.fillDetailsInMRPCreationAndClickTickIcon(Today, MRPName, Today, "ALL");
			mrpFunc.clickAddLinePlusIconInSources();
			mrpFunc.enterUnitCutOffDateInSourcesLine(commonFunc.getDataFromTestDataSheet("Unit_100"), NextMonthToday);
			mrpFunc.clickSalesOrderDemandCheckBoxInSourceLines();
			mrpFunc.clickSaveIconInSourcesTab();
			mrpFunc.clickLoadDemandButtonInMRP();
			mrpFunc.navigateToDemandTabInMRPScreen();
			mrpFunc.searchSONumberInDemandAndSelectSOItems(VariableHandler.getSONumber());
			mrpFunc.clickCheckExceptionAndSaveHeaderInMRP();
			mrpFunc.navigateToExceptionTabInMRPScreen();
			mrpFunc.validateNoExceptionDataShownInTableData();
			mrpFunc.clickRunMRPButton();
			mrpFunc.navigateToResultsTabInMRPScreen();
			mrpFunc.verifyUnitResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Unit_100") });
			mrpFunc.verifyItemResultsDetails(new String[] { FGItem, RMItem });
			mrpFunc.verifyUOMResultsDetails(new String[] { commonFunc.getDataFromTestDataSheet("UomNos"),
					commonFunc.getDataFromTestDataSheet("Kgs") });
			mrpFunc.clickHeaderSaveAndClickPost();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRPNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToMRPScreenInProdPlanning();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			mrpFunc.searchAndClickMRPNoInFindPage(VariableHandler.getMRPNumber());
			mrpFunc.clickReleaseButtonInMRP();
			mrpFunc.chooseOrderTypeFromAndToDateInReleaseDetails("All", YesterDay, Today);
			mrpFunc.clickReleaseButtonInReleaseFrame();
			mrpFunc.checkStatusOfMRP(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("ReleasedStatus"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfMRPNo(VariableHandler.getMRPNumber(),
					commonFunc.getDataFromTestDataSheet("UnreleasedStatus"));
			webDB.navigateToRefresh();
			searchAndSelectProductionOrderWithDocNo(VariableHandler.getProductionOrderNumber());
			verifyWriteBeforeStockForFGAndRawMaterials(FileName, SheetName);
			verifyHeaderValidationInProductionOrderWithDB(FileName, SheetName);
			verifyProductionOrderMREventAndRestOP(null, null, MoveType);
			verifyProcessType(commonFunc.getDataFromTestDataSheet("BothType"),
					commonFunc.getDataFromTestDataSheet("BothType"));
			verifyRoutingValidationInProductionOrderWithDB(FileName, SheetName);
			verifyRawMaterialsValidationInProductionOrderWithDB(FileName, SheetName);
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndex = 0;
			int AllocIndex = 0;
			a: for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc
						.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc.enterRequestQuantityInPendingMRCreation(
						VariableHandler.getProductionOrderNumber(), String.valueOf((ReqQuantity / 2)));
				completionProductionOrderFunc
						.clickProcessDetailsAndSelectAllProcess(VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc
						.clickCheckBoxTableAndCreateAndStoreMRNo(VariableHandler.getProductionOrderNumber());
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc
						.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
				materialRequestFunc.verifyHeaderValidationInMaterialRequestWithDB(FileName, SheetName, StartIndex);
				materialIssuanceVoucherFunc.verifyWriteBeforeLotQuantityInLotForRawMaterials(FileName, SheetName,
						StartIndex);
				commonFunc.navigateToMaterialIssuanceVoucherTab();
				materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
				materialIssuanceVoucherFunc.clickheaderSaveIconInMIV();
				verifyWriteAllocateStockForRawMaterials(FileName, SheetName, AllocIndex);
				materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
				commonFunc.navigateToMaterialIssuanceVoucherTab();
				materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				int MivStartIndex = 62;
				materialIssuanceVoucherFunc.verifyHeaderAndLinesValidationInMaterialIssuanceVoucherWithDB(FileName,
						SheetName, MivStartIndex);
				materialIssuanceVoucherFunc.verifyStockTransactionValidationInMIVForRawMaterials(FileName, SheetName,
						MivStartIndex);
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {
						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
				materialIssuanceVoucherFunc.verifyAfterLotQuantityInLotSerialValidationForRawMaterials(FileName,
						SheetName, StartIndex);
				materialIssuanceVoucherFunc.verifyStockTransactionValidationInMIVForRawMaterials(FileName, SheetName,
						StartIndex);
				commonFunc.navigateToCompletionProductionOrderMultipleProcessInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionMultiProcess();
				completionProductionOrderFunc
						.searchMultipleProductionOrderNoAndVerifyFilter(VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc.selectPendingMultipleProdOrderAndClickCreateCompletionProcess(
						VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
				completionProductionOrderFunc.verifyLinesWithRMValidationInCompletionProcessWithDB(FileName, SheetName,
						StartIndex);
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
				completionProductionOrderFunc.verifyHeaderAndLinesValidationInCompletionProcessWithDB(FileName,
						SheetName, StartIndex);
				completionProductionOrderFunc.verifyStockTransactionValidationInCompletionForRawMaterials(FileName,
						SheetName, StartIndex);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
						VariableHandler.getProductionCompletionOrderNo(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
				StartIndex = (i == 0) ? 35 : (i == 1) ? 45 : 0;
				AllocIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				productionOrderInsideMultipleMoveType(i, StartIndex, AllocIndex, FileName, SheetName);
				if (i > 1) {
					break a;
				}
				// Outside
				commonFunc.navigateToSubcontractOrder();
				subContractOrderFunc.verifySearchPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
				subContractOrderFunc.clickProcessDetailsAndSelectAllProcess(VariableHandler.getProductionOrderNumber());
				subContractOrderFunc.verifyPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
				subContractOrderFunc.verifyHeaderValidation(commonFunc.getDataFromTestDataSheet("PI_Loc_Id"),
						commonFunc.getDataFromTestDataSheet("Unit_100"),
						commonFunc.getDataFromTestDataSheet("SubcontractorName"));
				subContractOrderFunc.verifyProcessQuantityCalculation();
				subContractOrderFunc.verifyAddHsnCode();
				subContractOrderFunc.verifyAddGlAccNumberValue();
				subContractOrderFunc.verifyIncludeChargesAndPostIcon();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getScoNumber(),
							webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
							System.getProperty("HariUsername"));
					String Text = VariableHandler.getLastApprovalAPIHeaderText()
							.replace("Material Request Created. :", "").trim();
					Text = Text.replace("SUBCONTRACT ORDER Document is Approved.", "").trim();
					VariableHandler.setMRNumber(Text);
					VariableHandler.setMaterialRequestNo(Text);
				}
				subContractOrderFunc.verifyValidateSubContractDocumentsStatus(VariableHandler.getScoNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
				materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getScoNumber());
				materialRequestFunc.headerSaveAndPost();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getMRNumber(),
							webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
							System.getProperty("HariUsername"));
				}
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc.checkStatusOfMRNo(VariableHandler.getMRNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.navigateToMaterialIssuanceVoucher();
				materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMRNumber());
				materialIssuanceVoucherFunc.headerTickAndPostIcon();
				materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceNumber(VariableHandler.getMIVNumber(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				commonFunc.navigateToGateEntryDeliveryChallan();
				deliveryChallanFunc.verifyStatusOfDeliveryChallan(VariableHandler.getDCNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.createPendingGrnScoDoc(VariableHandler.getScoNumber());
				grnScoPageFunc.addHeatNumberAndTestNumber();
				grnScoPageFunc.checkConsumptionBeforeGenerationMat();
				grnScoPageFunc.verifyGenMatConsAndAllocate();
				grnScoPageFunc.checkConsumptionAfterGenerationMat();
				grnScoPageFunc.verifyViewLineDetails();
				grnScoPageFunc.saveHeaderAndPost();
				if (!VariableHandler.getApprovalFlag()) {
					approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
					commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
							VariableHandler.getScoGrnNumber(), webDB.getDataFromProperties("HodEmpId"),
							commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
				}
				commonFunc.navigateToGrnSco();
				grnScoPageFunc.verifyValidateGrnScoStatus(VariableHandler.getScoGrnNumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {
						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(FileName, SheetName);
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderAgainstSOAllBothPartialQuantityMoveTypeItem': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Single
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllInsideMoveTypeSingleWithInspectionPlan() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"), VariableHandler.getBOMNumber(),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
			int AllocStartIndex = 0;
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndexSheet = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
				AllocStartIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				productionOrderInsideSingleMoveTypeWithInspectionDocu(StartIndexSheet, AllocStartIndex,
						webDB.getDataFromProperties("PMFFileName"),
						webDB.getDataFromProperties("AllInsideSheetName-Single"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllInsideMoveTypeSingle': " + e);
			return false;
		}
	}

	/**
	 * This method is a following steps to complete Prod order as inside with Single
	 * type
	 * 
	 * @author Deepan-GWL
	 * @param FileName
	 * @param SheetName
	 */
	public void productionOrderInsideSingleMoveTypeWithInspectionDocu(int StartIndex, int AllocateIndex,
			String FileName, String SheetName) {
		commonFunc.navigateToCompletionProductionOrderInProdPlanning();
		completionProductionOrderFunc.clickPendingIconInCompletionProcess();
		completionProductionOrderFunc
				.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
		completionProductionOrderFunc
				.clickCheckBoxTableAndCreateAndStoreMRNo(VariableHandler.getProductionOrderNumber());
		commonFunc.navigateToInventoryMaterialRequest();
		materialRequestFunc.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
		materialIssuanceVoucherFunc.verifyWriteBeforeLotQuantityInLotForRawMaterials(FileName, SheetName, StartIndex);
		commonFunc.navigateToMaterialIssuanceVoucherTab();
		materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
		materialIssuanceVoucherFunc.clickheaderSaveIconInMIV();
		verifyWriteAllocateStockForRawMaterials(FileName, SheetName, AllocateIndex);
		materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
		commonFunc.navigateToMaterialIssuanceVoucherTab();
		materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
				commonFunc.getDataFromTestDataSheet("PostedStatus"));
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			if (flag) {
				materialReceiptFunctions
						.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(VariableHandler.getProductionOrderNumber());
			} else {
				materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
			}
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
		commonFunc.navigateToCompletionProductionOrderInProdPlanning();
		completionProductionOrderFunc.clickPendingIconInCompletionProcess();
		completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
				commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getProductionOrderNumber());
		completionProductionOrderFunc.clickAllocateDeallocateButtonInHeader();
		completionProductionOrderFunc
				.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
		completionProductionOrderFunc.clickPostButtonInCompletionProcess();
//		commonFunc.navigateToCompletionProductionOrderInProdPlanning();
//		completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
//				VariableHandler.getProductionCompletionOrderNo(), commonFunc.getDataFromTestDataSheet("PostedStatus"));
		qcFunc.inspectionDocumentWithInspectionPlanForDifferentValuesForProductionType();
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
	}

	/**
	 * This method is a following steps to complete Prod order as inside with Single
	 * type
	 * 
	 * @author Deepan-GWL
	 * @param FileName
	 * @param SheetName
	 */
	public void productionOrderInsideSingleMoveTypeWithFinalInspection(int StartIndex, int AllocateIndex,
			String FileName, String SheetName) {
		commonFunc.navigateToCompletionProductionOrderInProdPlanning();
		completionProductionOrderFunc.clickPendingIconInCompletionProcess();
		completionProductionOrderFunc
				.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
		completionProductionOrderFunc
				.clickCheckBoxTableAndCreateAndStoreMRNo(VariableHandler.getProductionOrderNumber());
		commonFunc.navigateToInventoryMaterialRequest();
		materialRequestFunc.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
		materialIssuanceVoucherFunc.verifyWriteBeforeLotQuantityInLotForRawMaterials(FileName, SheetName, StartIndex);
		commonFunc.navigateToMaterialIssuanceVoucherTab();
		materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
		materialIssuanceVoucherFunc.clickheaderSaveIconInMIV();
		verifyWriteAllocateStockForRawMaterials(FileName, SheetName, AllocateIndex);
		materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
		commonFunc.navigateToMaterialIssuanceVoucherTab();
		materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
				commonFunc.getDataFromTestDataSheet("PostedStatus"));
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			if (flag) {
				materialReceiptFunctions
						.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(VariableHandler.getProductionOrderNumber());
			} else {
				materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
			}
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
		commonFunc.navigateToCompletionProductionOrderInProdPlanning();
		completionProductionOrderFunc.clickPendingIconInCompletionProcess();
		completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
				commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getProductionOrderNumber());
		completionProductionOrderFunc.clickAllocateDeallocateButtonInHeader();
		completionProductionOrderFunc
				.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
		completionProductionOrderFunc.clickPostButtonInCompletionProcess();
//		commonFunc.navigateToCompletionProductionOrderInProdPlanning();
//		completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
//				VariableHandler.getProductionCompletionOrderNo(), commonFunc.getDataFromTestDataSheet("PostedStatus"));
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
		qcFunc.inspectionDocumentWithInspectionPlanForDifferentValuesForProductionType();
	}

	/**
	 * This method used to Manual Production order with Move Type - Single
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllInsideMoveTypeSingleWithFinalInspection() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"), VariableHandler.getBOMNumber(),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
			int AllocStartIndex = 0;
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndexSheet = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
				AllocStartIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				productionOrderInsideSingleMoveTypeWithFinalInspection(StartIndexSheet, AllocStartIndex,
						webDB.getDataFromProperties("PMFFileName"),
						webDB.getDataFromProperties("AllInsideSheetName-Single"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllInsideMoveTypeSingle': " + e);
			return false;
		}
	}

	/**
	 * This method used to Pending Material Request-Against Without MR Control
	 * (Create Material Request for Production Order-Single)
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyPendingMaterialRequestAgainstWithMRControlMoveTypeSingle() {
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
			bomFunc.clickHeaderSaveAndPostButtonInBOM();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getBOMNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToBillOfMaterialsPage();
			bomFunc.searchBOMNoAndVerifyStatus(VariableHandler.getBOMNumber(),
					commonFunc.getDataFromTestDataSheet("ActiveStatus"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"), VariableHandler.getBOMNumber(), "3");
			clickSaveIconInCreationProductionOrder();
			navigateToRoutingSubTab();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			navigateToMaterialRequirementSubTab();
			verifyItemInMaterialRequirement(new String[] { RMItem });
			verifyProcessInMaterialRequirement(new String[] { commonFunc.getDataFromTestDataSheet("GwlProcess1") });
			validateMaterialReqrdQuantityWithRMItemProcess(commonFunc.getDataFromTestDataSheet("GwlProcess1"), RMItem);
			navigateToExceptionSubTab();
			validateNoExceptionsShowsInExceptionTab();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
//			int AllocStartIndex = 0;
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndexSheet = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
//				AllocStartIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc
						.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc
						.clickCheckBoxTableAndCreateAndStoreMRNo(VariableHandler.getProductionOrderNumber());
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc
						.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
				materialRequestFunc.verifyHeaderValidationInMaterialRequestWithDB(
						webDB.getDataFromProperties("PMFFileName"),
						webDB.getDataFromProperties("AllInsideSheetName-Single"), StartIndexSheet);
			}
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyPendingMaterialRequestAgainstWithMRControlMoveTypeSingle': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Pending Material Request-Against With MR Control (Create
	 * Material Request for Production Order-Multiple)
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyPendingMaterialRequestAgainstWithMRControlMoveTypeMultiple() {
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
			bomFunc.clickSaveLineIconInRoutingTab();
			bomFunc.clickDetailsForAddedProcessInBOM(commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			bomFunc.chooseConsumptionAndReceiptWHInViewDetails(commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			bomFunc.clickAddLineIconInRoutingTab();
			bomFunc.fillProcSeqNoOprnNoAndProcessIdInRouting("2", "2",
					commonFunc.getDataFromTestDataSheet("GwlProcess2"));
			bomFunc.fillTypeAndWorkCenterInRouting(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"));
			bomFunc.clickSaveLineIconInRoutingTab();
			bomFunc.clickDetailsForAddedProcessInBOM(commonFunc.getDataFromTestDataSheet("GwlProcess2"));
			bomFunc.chooseConsumptionAndReceiptWHInViewDetails(commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			bomFunc.clickAddLineIconInRoutingTab();
			bomFunc.fillProcSeqNoOprnNoAndProcessIdInRouting("3", "3",
					commonFunc.getDataFromTestDataSheet("GwlProcess3"));
			bomFunc.fillTypeAndWorkCenterInRouting(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"));
			bomFunc.clickSaveLineIconInRoutingTab();
			bomFunc.clickDetailsForAddedProcessInBOM(commonFunc.getDataFromTestDataSheet("GwlProcess3"));
			bomFunc.chooseConsumptionAndReceiptWHInViewDetails(commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			bomFunc.navigateToMaterialsSubTab();
			bomFunc.clickAddLineIconInMaterialTab();
			bomFunc.fillProcessItemUOMInMaterial(commonFunc.getDataFromTestDataSheet("GwlProcess1"), RMItem,
					commonFunc.getDataFromTestDataSheet("Kgs"));
			bomFunc.fillReqQntyConvFactorAndSourceWHInMaterial("3", "1",
					commonFunc.getDataFromTestDataSheet("RMU100_WH"));
			bomFunc.clickSaveLineIconInMaterialTab();
			bomFunc.clickAddLineIconInMaterialTab();
			bomFunc.fillProcessItemUOMInMaterial(commonFunc.getDataFromTestDataSheet("GwlProcess2"), RMItem,
					commonFunc.getDataFromTestDataSheet("Kgs"));
			bomFunc.fillReqQntyConvFactorAndSourceWHInMaterial("3", "1",
					commonFunc.getDataFromTestDataSheet("RMU100_WH"));
			bomFunc.clickSaveLineIconInMaterialTab();
			bomFunc.clickAddLineIconInMaterialTab();
			bomFunc.fillProcessItemUOMInMaterial(commonFunc.getDataFromTestDataSheet("GwlProcess3"), RMItem,
					commonFunc.getDataFromTestDataSheet("Kgs"));
			bomFunc.fillReqQntyConvFactorAndSourceWHInMaterial("3", "1",
					commonFunc.getDataFromTestDataSheet("RMU100_WH"));
			bomFunc.clickSaveLineIconInMaterialTab();
			bomFunc.clickHeaderSaveAndPostButtonInBOM();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getBOMNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToBillOfMaterialsPage();
			bomFunc.searchBOMNoAndVerifyStatus(VariableHandler.getBOMNumber(),
					commonFunc.getDataFromTestDataSheet("ActiveStatus"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"), VariableHandler.getBOMNumber(), "3");
			clickSaveIconInCreationProductionOrder();
			navigateToRoutingSubTab();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			navigateToMaterialRequirementSubTab();
			verifyItemInMaterialRequirement(new String[] { RMItem });
			verifyProcessInMaterialRequirement(new String[] { commonFunc.getDataFromTestDataSheet("GwlProcess1") });
			validateMaterialReqrdQuantityWithRMItemProcess(commonFunc.getDataFromTestDataSheet("GwlProcess1"), RMItem);
			navigateToExceptionSubTab();
			validateNoExceptionsShowsInExceptionTab();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
//			int AllocStartIndex = 0;
			for (int i = 0; i < 2; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndexSheet = (i == 0) ? 35 : (i == 1) ? 45 : 0;
//				AllocStartIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc
						.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
				if (!(i == 0)) {
					completionProductionOrderFunc
							.clickProcessDetailsAndSelectAllProcess(VariableHandler.getProductionOrderNumber());
				}
				completionProductionOrderFunc
						.clickCheckBoxTableAndCreateAndStoreMRNo(VariableHandler.getProductionOrderNumber());
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc
						.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
				materialRequestFunc.verifyHeaderValidationInMaterialRequestWithDB(
						webDB.getDataFromProperties("PMFFileName"),
						webDB.getDataFromProperties("AllInsideSheetName-Multiple"), StartIndexSheet);
			}
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyPendingMaterialRequestAgainstWithMRControlMoveTypeMultiple': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Pending Material Request-Against Without MR Control
	 * (Create Material Request for Production Order-Single)
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyPendingMaterialRequestAgainstWithoutMRControlMoveTypeSingle() {
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
			bomFunc.clickAddIconAndFillUnitBOMNameAndItem(commonFunc.getDataFromTestDataSheet("L3"), BOMName,
					VariableHandler.getItemName());
			bomFunc.enterEffFromToDateAndSaveHeader(Today, Today);
			bomFunc.navigateToRoutingSubTab();
			bomFunc.clickAddLineIconInRoutingTab();
			bomFunc.fillProcSeqNoOprnNoAndProcessIdInRouting("1", "1",
					commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			bomFunc.fillTypeAndWorkCenterInRouting(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"));
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
			bomFunc.clickHeaderSaveAndPostButtonInBOM();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getBOMNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("L3"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToBillOfMaterialsPage();
			bomFunc.searchBOMNoAndVerifyStatus(VariableHandler.getBOMNumber(),
					commonFunc.getDataFromTestDataSheet("ActiveStatus"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("L49ID"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix49"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"), VariableHandler.getBOMNumber(), "3");
			clickSaveIconInCreationProductionOrder();
			navigateToRoutingSubTab();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			navigateToMaterialRequirementSubTab();
			verifyItemInMaterialRequirement(new String[] { RMItem });
			verifyProcessInMaterialRequirement(new String[] { commonFunc.getDataFromTestDataSheet("GwlProcess1") });
			validateMaterialReqrdQuantityWithRMItemProcess(commonFunc.getDataFromTestDataSheet("GwlProcess1"), RMItem);
			navigateToExceptionSubTab();
			validateNoExceptionsShowsInExceptionTab();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("L3"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
//			int AllocStartIndex = 0;
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndexSheet = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
//				AllocStartIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc
						.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc
						.clickCheckBoxTableAndCreateAndStoreMRNo(VariableHandler.getProductionOrderNumber());
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc
						.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
				materialRequestFunc.verifyHeaderValidationInMaterialRequestWithDB(
						webDB.getDataFromProperties("PMFFileName"),
						webDB.getDataFromProperties("AllInsideSheetName-Single"), StartIndexSheet);
			}
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyPendingMaterialRequestAgainstWithoutMRControlMoveTypeSingle': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Pending Material Request-Against Without MR Control
	 * (Create Material Request for Production Order-Multiple)
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyPendingMaterialRequestAgainstWithoutMRControlMoveTypeMultiple() {
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
			bomFunc.clickAddIconAndFillUnitBOMNameAndItem(commonFunc.getDataFromTestDataSheet("L3"), BOMName,
					VariableHandler.getItemName());
			bomFunc.enterEffFromToDateAndSaveHeader(Today, Today);
			bomFunc.navigateToRoutingSubTab();
			bomFunc.clickAddLineIconInRoutingTab();
			bomFunc.fillProcSeqNoOprnNoAndProcessIdInRouting("1", "1",
					commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			bomFunc.fillTypeAndWorkCenterInRouting(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"));
			bomFunc.clickSaveLineIconInRoutingTab();
			bomFunc.clickDetailsForAddedProcessInBOM(commonFunc.getDataFromTestDataSheet("GwlProcess1"));
			bomFunc.chooseConsumptionAndReceiptWHInViewDetails(commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			bomFunc.clickAddLineIconInRoutingTab();
			bomFunc.fillProcSeqNoOprnNoAndProcessIdInRouting("2", "2",
					commonFunc.getDataFromTestDataSheet("GwlProcess2"));
			bomFunc.fillTypeAndWorkCenterInRouting(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"));
			bomFunc.clickSaveLineIconInRoutingTab();
			bomFunc.clickDetailsForAddedProcessInBOM(commonFunc.getDataFromTestDataSheet("GwlProcess2"));
			bomFunc.chooseConsumptionAndReceiptWHInViewDetails(commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			bomFunc.clickAddLineIconInRoutingTab();
			bomFunc.fillProcSeqNoOprnNoAndProcessIdInRouting("3", "3",
					commonFunc.getDataFromTestDataSheet("GwlProcess3"));
			bomFunc.fillTypeAndWorkCenterInRouting(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("GwlAutomationArea"));
			bomFunc.clickSaveLineIconInRoutingTab();
			bomFunc.clickDetailsForAddedProcessInBOM(commonFunc.getDataFromTestDataSheet("GwlProcess3"));
			bomFunc.chooseConsumptionAndReceiptWHInViewDetails(commonFunc.getDataFromTestDataSheet("WIPU100_WH"),
					commonFunc.getDataFromTestDataSheet("WIPU100_WH"));
			bomFunc.navigateToMaterialsSubTab();
			bomFunc.clickAddLineIconInMaterialTab();
			bomFunc.fillProcessItemUOMInMaterial(commonFunc.getDataFromTestDataSheet("GwlProcess1"), RMItem,
					commonFunc.getDataFromTestDataSheet("Kgs"));
			bomFunc.fillReqQntyConvFactorAndSourceWHInMaterial("3", "1",
					commonFunc.getDataFromTestDataSheet("RMU100_WH"));
			bomFunc.clickSaveLineIconInMaterialTab();
			bomFunc.clickAddLineIconInMaterialTab();
			bomFunc.fillProcessItemUOMInMaterial(commonFunc.getDataFromTestDataSheet("GwlProcess2"), RMItem,
					commonFunc.getDataFromTestDataSheet("Kgs"));
			bomFunc.fillReqQntyConvFactorAndSourceWHInMaterial("3", "1",
					commonFunc.getDataFromTestDataSheet("RMU100_WH"));
			bomFunc.clickSaveLineIconInMaterialTab();
			bomFunc.clickAddLineIconInMaterialTab();
			bomFunc.fillProcessItemUOMInMaterial(commonFunc.getDataFromTestDataSheet("GwlProcess3"), RMItem,
					commonFunc.getDataFromTestDataSheet("Kgs"));
			bomFunc.fillReqQntyConvFactorAndSourceWHInMaterial("3", "1",
					commonFunc.getDataFromTestDataSheet("RMU100_WH"));
			bomFunc.clickSaveLineIconInMaterialTab();
			bomFunc.clickHeaderSaveAndPostButtonInBOM();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getBOMNumber(),
						webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("L3"),
						System.getProperty("HariUsername"));
			}
			commonFunc.navigateToBillOfMaterialsPage();
			bomFunc.searchBOMNoAndVerifyStatus(VariableHandler.getBOMNumber(),
					commonFunc.getDataFromTestDataSheet("ActiveStatus"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("L49ID"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix49"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"), VariableHandler.getBOMNumber(), "3");
			clickSaveIconInCreationProductionOrder();
			navigateToRoutingSubTab();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			navigateToMaterialRequirementSubTab();
			verifyItemInMaterialRequirement(new String[] { RMItem });
			verifyProcessInMaterialRequirement(new String[] { commonFunc.getDataFromTestDataSheet("GwlProcess1") });
			validateMaterialReqrdQuantityWithRMItemProcess(commonFunc.getDataFromTestDataSheet("GwlProcess1"), RMItem);
			navigateToExceptionSubTab();
			validateNoExceptionsShowsInExceptionTab();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("L3"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
//			int AllocStartIndex = 0;
			for (int i = 0; i < 2; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndexSheet = (i == 0) ? 35 : (i == 1) ? 45 : 0;
//				AllocStartIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc
						.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
				if (!(i == 0)) {
					completionProductionOrderFunc
							.clickProcessDetailsAndSelectAllProcess(VariableHandler.getProductionOrderNumber());
				}
				completionProductionOrderFunc
						.clickCheckBoxTableAndCreateAndStoreMRNo(VariableHandler.getProductionOrderNumber());
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc
						.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
				materialRequestFunc.verifyHeaderValidationInMaterialRequestWithDB(
						webDB.getDataFromProperties("PMFFileName"),
						webDB.getDataFromProperties("AllInsideSheetName-Multiple"), StartIndexSheet);
			}
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyPendingMaterialRequestAgainstWithoutMRControlMoveTypeMultiple': "
							+ e);
			return false;
		}
	}

	/**
	 * How Pend. Prod. Queue (IS) is updated?
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean veridyHowPendingProductionQueueUpdatedInside() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"), VariableHandler.getBOMNumber(),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < 1; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc
						.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc
						.clickCheckBoxTableAndCreateAndStoreMRNo(VariableHandler.getProductionOrderNumber());
				commonFunc.navigateToInventoryMaterialRequest();
				materialRequestFunc
						.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
				commonFunc.navigateToMaterialIssuanceVoucherTab();
				materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
				materialIssuanceVoucherFunc.clickheaderSaveIconInMIV();
				materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
				commonFunc.navigateToMaterialIssuanceVoucherTab();
				materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
						commonFunc.getDataFromTestDataSheet("PostedStatus"));
				commonFunc.navigateSetupInventoryPage();
				flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
				if (!VariableHandler.getAutoPostMRVFlag()) {
					commonFunc.navigateToMaterialReceiptVoucher();
					if (flag) {
						materialReceiptFunctions.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(
								VariableHandler.getProductionOrderNumber());
					} else {
						materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
					}
					materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
					materialReceiptFunctions.clickTickAndPostIcon();
					commonFunc.navigateToMaterialReceiptVoucher();
					materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(
							VariableHandler.getMrvNumber(), commonFunc.getDataFromTestDataSheet("IssuedStatus"));
				}
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc
						.searchProductionOrderNoAndVerifyFilter(VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc.verifyProductionOrderPendingQueueAfterInsideProcess(ProcessName);
			}
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'veridyHowPendingProductionQueueUpdatedInside': " + e);
			return false;
		}
	}

	/**
	 * How Pend. Prod. Queue (OS) is updated?
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyHowPendingProductionQueueUpdatedOutside() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllOutside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("OutsideType"),
					commonFunc.getDataFromTestDataSheet("OutsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < 1; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				commonFunc.navigateToSubcontractOrder();
				subContractOrderFunc.verifySearchPendingSubcontractOrder(VariableHandler.getProductionOrderNumber());
				subContractOrderFunc.verifyQueueQuantityForSubcontractAfterOutside(ProcessName);
			}
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyHowPendingProductionQueueUpdatedOutside': " + e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Single With
	 * Resources With MR Control
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllInsideMoveTypeSingleWithResourcesWithMRControl() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("POWRWOMRC"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
			int AllocStartIndex = 0;
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndexSheet = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
				AllocStartIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				productionOrderInsideSingleMoveTypeWithResourcesWithMaterialRequest(StartIndexSheet, AllocStartIndex);
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllInsideMoveTypeSingleWithResourcesWithMRControl': "
							+ e);
			return false;
		}
	}

	/**
	 * This method is a following steps to complete Prod order as inside with Single
	 * type
	 * 
	 * @author Deepan-GWL
	 * @param FileName
	 * @param SheetName
	 */
	public void productionOrderInsideSingleMoveTypeWithResourcesWithMaterialRequest(int StartIndex, int AllocateIndex) {
		commonFunc.navigateToCompletionProductionOrderInProdPlanning();
		completionProductionOrderFunc.clickPendingIconInCompletionProcess();
		completionProductionOrderFunc
				.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
		completionProductionOrderFunc
				.clickCheckBoxTableAndCreateAndStoreMRNo(VariableHandler.getProductionOrderNumber());
		commonFunc.navigateToInventoryMaterialRequest();
		materialRequestFunc.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
		commonFunc.navigateToMaterialIssuanceVoucherTab();
		materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
		materialIssuanceVoucherFunc.clickheaderSaveIconInMIV();
		materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
		commonFunc.navigateToMaterialIssuanceVoucherTab();
		materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
				commonFunc.getDataFromTestDataSheet("PostedStatus"));
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			if (flag) {
				materialReceiptFunctions
						.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(VariableHandler.getProductionOrderNumber());
			} else {
				materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
			}
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
		commonFunc.navigateToCompletionProductionOrderInProdPlanning();
		completionProductionOrderFunc.clickPendingIconInCompletionProcess();
		completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
				commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getProductionOrderNumber());
		completionProductionOrderFunc.clickAllocateDeallocateButtonInHeader();
		completionProductionOrderFunc
				.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
		completionProductionOrderFunc.clickPostButtonInCompletionProcess();
//		commonFunc.navigateToCompletionProductionOrderInProdPlanning();
//		completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
//				VariableHandler.getProductionCompletionOrderNo(), commonFunc.getDataFromTestDataSheet("PostedStatus"));
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Single With
	 * Resources With MR Control
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllInsideMoveTypeSingleWithResourcesWithOutMRControl() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("POWRWOMRC"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
			int AllocStartIndex = 0;
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndexSheet = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
				AllocStartIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				productionOrderInsideSingleMoveTypeWithResourcesWithMaterialRequest(StartIndexSheet, AllocStartIndex);
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllInsideMoveTypeSingleWithResourcesWithOutMRControl': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Single
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllInsideMoveTypeSingleWithoutResources() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("ProdOrder-Single(Inside)WoResSheet"),
					commonFunc.getDataFromTestDataSheet("WH"));
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInsideWoRes"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("ProdOrder-Single(Inside)WoResSheet"));
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("ProdOrder-Single(Inside)WoResSheet"));
			verifyRawMaterialsValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("ProdOrder-Single(Inside)WoResSheet"));
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
			int AllocStartIndex = 0;
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndexSheet = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
				AllocStartIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				productionOrderInsideSingleMoveType(StartIndexSheet, AllocStartIndex,
						webDB.getDataFromProperties("PMFFileName2"),
						webDB.getDataFromProperties("ProdOrder-Single(Inside)WoResSheet"),
						commonFunc.getDataFromTestDataSheet("WH"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("ProdOrder-Single(Inside)WoResSheet"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllInsideMoveTypeSingleWithoutResources': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to update stock quantity and cost in test data sheet
	 * 
	 * @author Deepan-GWL
	 * @param FileName
	 * @param SheetName
	 */
	public void verifyWriteBeforeStockForFGAndRawMaterials(String FileName, String SheetName, String WareHouse) {
		logs.logging("INFO", "****Writing Before Stock Unit Cost And  Stock Quantity DB Validation For RM and FG****");
		String[][] TotalData = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		TotalData = excelUtils.readAllDataInExcel(Filepath, SheetName);
		String FGItem = TotalData[2][5];
		String FGWH = commonFunc.getDataFromTestDataSheet("FG WH");

		String StockQuantity = dbFunction.validateStockCostAndStockQuantityWithItemWarehouse(FGItem, FGWH,
				commonFunc.getDataFromTestDataSheet("StockQtyHandDBHeader"));
		logs.logging("INFO", "For FGItem: " + FGItem + " Stock Quanity Before = " + StockQuantity);
		excelUtils.writeDataInExcel(Filepath, SheetName, 2, 8, StockQuantity);

		String StockCost = dbFunction.validateStockCostAndStockQuantityWithItemWarehouse(FGItem, FGWH,
				commonFunc.getDataFromTestDataSheet("StockUpdCostDBHeader"));
		logs.logging("INFO", "For FGItem: " + FGItem + " Stock Quanity Before = " + StockCost);
		excelUtils.writeDataInExcel(Filepath, SheetName, 2, 10, StockCost);

		for (int i = 10; i <= 16; i++) {
			String RMItem = TotalData[i][4];
			String RMWH = WareHouse;
			StockQuantity = dbFunction.validateStockCostAndStockQuantityWithItemWarehouse(RMItem, RMWH,
					commonFunc.getDataFromTestDataSheet("StockQtyHandDBHeader"));
			logs.logging("INFO", "For RMItem: " + FGItem + " Stock Quanity Before = " + StockQuantity);
			excelUtils.writeDataInExcel(Filepath, SheetName, i, 7, StockQuantity);

			String AllocQuantity = dbFunction.validateStockCostAndStockQuantityWithItemWarehouse(RMItem, RMWH,
					commonFunc.getDataFromTestDataSheet("StockUpdAllocQtyDBHeader"));
			logs.logging("INFO", "For RGItem: " + FGItem + " Stock Allocate Quanity Before = " + AllocQuantity);
			excelUtils.writeDataInExcel(Filepath, SheetName, i, 8, AllocQuantity);
		}
	}

	/**
	 * This method is a following steps to complete Prod order as inside with Single
	 * type
	 * 
	 * @author Deepan-GWL
	 * @param FileName
	 * @param SheetName
	 */
	public void productionOrderInsideSingleMoveType(int StartIndex, int AllocateIndex, String FileName,
			String SheetName, String Warehouse) {
		commonFunc.navigateToCompletionProductionOrderInProdPlanning();
		completionProductionOrderFunc.clickPendingIconInCompletionProcess();
		completionProductionOrderFunc
				.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
		completionProductionOrderFunc
				.clickCheckBoxTableAndCreateAndStoreMRNo(VariableHandler.getProductionOrderNumber());
		commonFunc.navigateToInventoryMaterialRequest();
		materialRequestFunc.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
		materialRequestFunc.verifyHeaderValidationInMaterialRequestWithDB(FileName, SheetName, StartIndex);
		materialIssuanceVoucherFunc.verifyWriteBeforeLotQuantityInLotForRawMaterials(FileName, SheetName, StartIndex,
				commonFunc.getDataFromTestDataSheet("WH"));
		commonFunc.navigateToMaterialIssuanceVoucherTab();
		materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
		materialIssuanceVoucherFunc.clickheaderSaveIconInMIV();
		verifyWriteAllocateStockForRawMaterials(FileName, SheetName, AllocateIndex);
		materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
		commonFunc.navigateToMaterialIssuanceVoucherTab();
		materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
				commonFunc.getDataFromTestDataSheet("PostedStatus"));
		materialIssuanceVoucherFunc.verifyHeaderAndLinesValidationInMaterialIssuanceVoucherWithDB(FileName, SheetName,
				StartIndex);
		materialIssuanceVoucherFunc.verifyStockTransactionValidationInMIVForRawMaterials(FileName, SheetName,
				StartIndex);
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			if (flag) {
				materialReceiptFunctions
						.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(VariableHandler.getProductionOrderNumber());
			} else {
				materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
			}
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
		materialIssuanceVoucherFunc.verifyAfterLotQuantityInLotSerialValidationForRawMaterials(FileName, SheetName,
				StartIndex, Warehouse);
		materialIssuanceVoucherFunc.verifyStockTransactionValidationInMIVForRawMaterials(FileName, SheetName,
				StartIndex);
		commonFunc.navigateToCompletionProductionOrderInProdPlanning();
		completionProductionOrderFunc.clickPendingIconInCompletionProcess();
		completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
				commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getProductionOrderNumber());
		completionProductionOrderFunc.clickAllocateDeallocateButtonInHeader();
		completionProductionOrderFunc
				.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
		completionProductionOrderFunc.verifyLinesWithRMValidationInCompletionProcessWithDB(FileName, SheetName,
				StartIndex);
		completionProductionOrderFunc.clickPostButtonInCompletionProcess();
		completionProductionOrderFunc.verifyHeaderAndLinesValidationInCompletionProcessWithDB(FileName, SheetName,
				StartIndex);
		completionProductionOrderFunc.verifyStockTransactionValidationInCompletionForRawMaterials(FileName, SheetName,
				StartIndex);
		commonFunc.navigateToCompletionProductionOrderInProdPlanning();
		completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
				VariableHandler.getProductionCompletionOrderNo(), commonFunc.getDataFromTestDataSheet("PostedStatus"));
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Multiple
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllInsideMoveTypeMultipleWithoutResourcesWithMRControl() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("POWORWMRCPMSheet"), commonFunc.getDataFromTestDataSheet("WH"));
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInsideWoResMT"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("POWORWMRCPMSheet"));
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("MultipleType"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("POWORWMRCPMSheet"));
			verifyRawMaterialsValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("POWORWMRCPMSheet"));
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
			int AllocStartIndex = 0;
			for (int i = 0; i < 2; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndexSheet = (i == 0) ? 35 : (i == 1) ? 45 : 0;
				AllocStartIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				System.out.println(
						"Outer Start Index " + StartIndexSheet + " Alloc Index " + AllocStartIndex + " i " + i);
				productionOrderInsideMultipleMoveType(i, StartIndexSheet, AllocStartIndex,
						webDB.getDataFromProperties("PMFFileName2"), webDB.getDataFromProperties("POWORWMRCPMSheet"),
						commonFunc.getDataFromTestDataSheet("WH"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("POWORWMRCPMSheet"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllInsideMoveTypeMultipleWithoutResourcesWithMRControl': "
							+ e);
			return false;
		}
	}

	/**
	 * This method is a following steps to complete Prod order as inside with
	 * Multiple type
	 * 
	 * @author Deepan-GWL
	 * @param i
	 * @param StartIndex
	 * @param AllocateIndex
	 * @param FileName
	 * @param SheetName
	 */
	public void productionOrderInsideMultipleMoveType(int i, int StartIndex, int AllocateIndex, String FileName,
			String SheetName, String WareHouse) {
		commonFunc.navigateToCompletionProductionOrderInProdPlanning();
		completionProductionOrderFunc.clickPendingIconInCompletionProcess();
		completionProductionOrderFunc
				.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
		if (!(i == 0)) {
			completionProductionOrderFunc
					.clickProcessDetailsAndSelectAllProcess(VariableHandler.getProductionOrderNumber());
		}
		completionProductionOrderFunc
				.clickCheckBoxTableAndCreateAndStoreMRNo(VariableHandler.getProductionOrderNumber());
		commonFunc.navigateToInventoryMaterialRequest();
		materialRequestFunc.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
		materialRequestFunc.verifyHeaderValidationInMaterialRequestWithDB(FileName, SheetName, StartIndex);
		materialIssuanceVoucherFunc.verifyWriteBeforeLotQuantityInLotForRawMaterials(FileName, SheetName, StartIndex,
				WareHouse);
		commonFunc.navigateToMaterialIssuanceVoucherTab();
		materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
		materialIssuanceVoucherFunc.clickheaderSaveIconInMIV();
//		if (!(i == 0)) {
//			AllocateIndex = 61;
//		}
		verifyWriteAllocateStockForRawMaterials(FileName, SheetName, AllocateIndex, WareHouse);
		materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
		commonFunc.navigateToMaterialIssuanceVoucherTab();
		materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
				commonFunc.getDataFromTestDataSheet("PostedStatus"));
//		if (!(i == 0)) {
//			StartIndex = 61;
//		}
		materialIssuanceVoucherFunc.verifyHeaderAndLinesValidationInMaterialIssuanceVoucherWithDB(FileName, SheetName,
				StartIndex);
		materialIssuanceVoucherFunc.verifyStockTransactionValidationInMIVForRawMaterials(FileName, SheetName,
				StartIndex);
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			if (flag) {
				materialReceiptFunctions
						.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(VariableHandler.getProductionOrderNumber());
			} else {
				materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
			}
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
		materialIssuanceVoucherFunc.verifyAfterLotQuantityInLotSerialValidationForRawMaterials(FileName, SheetName,
				StartIndex, WareHouse);
		materialIssuanceVoucherFunc.verifyStockTransactionValidationInMIVForRawMaterials(FileName, SheetName,
				StartIndex);
		commonFunc.navigateToCompletionProductionOrderMultipleProcessInProdPlanning();
		completionProductionOrderFunc.clickPendingIconInCompletionMultiProcess();
		if (i == 0) {
			completionProductionOrderFunc.selectPendingMultipleProdOrderAndClickCreateCompletionProcessWithNoButton(
					VariableHandler.getProductionOrderNumber());

		} else {
			completionProductionOrderFunc.selectPendingMultipleProdOrderAndClickCreateCompletionProcess(
					VariableHandler.getProductionOrderNumber());
		}
		completionProductionOrderFunc.clickAllocateDeallocateButtonInHeader();
		completionProductionOrderFunc
				.selectShiftAndSaveHeaderInCompletionMultiProcess(commonFunc.getDataFromTestDataSheet("Shift_100"));
		completionProductionOrderFunc.verifyLinesWithRMValidationInCompletionProcessWithDB(FileName, SheetName,
				StartIndex);
		System.out.println("13913 Allloc Index " + StartIndex + " Start Index " + StartIndex + " i " + i);
		completionProductionOrderFunc.clickPostButtonInCompletionMultiProcess();
		commonFunc.hardWait(3000);
		System.out.println("13916 Allloc Index " + StartIndex + " Start Index " + StartIndex + " i " + i);
		completionProductionOrderFunc.verifyHeaderAndLinesValidationInCompletionProcessWithDB(FileName, SheetName,
				StartIndex);
		completionProductionOrderFunc.verifyStockTransactionValidationInCompletionForRawMaterials(FileName, SheetName,
				StartIndex);
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
	}

	/**
	 * This method used to update allocate stock quantity in test data sheet
	 * 
	 * @author Deepan-GWL
	 * @param FileName
	 * @param SheetName
	 */
	public void verifyWriteAllocateStockForRawMaterials(String FileName, String SheetName, int StartIndex,
			String RMWH) {
		logs.logging("INFO", "****Writing Before Allocate Stock Quantity DB Validation****");
		String[][] TotalData = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		TotalData = excelUtils.readAllDataInExcel(Filepath, SheetName);

		for (int i = StartIndex; i <= (StartIndex + 6); i++) {
			String RMItem = TotalData[i][4];
//			String RMWH = Warehouse;
			String AllocQuantity = dbFunction.validateStockCostAndStockQuantityWithItemWarehouse(RMItem, RMWH,
					commonFunc.getDataFromTestDataSheet("StockUpdAllocQtyDBHeader"));
			logs.logging("INFO", "For RMItem: " + RMItem + " Stock Allocate Quanity Before = " + AllocQuantity);
			excelUtils.writeDataInExcel(Filepath, SheetName, i, 8, AllocQuantity);
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Item
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllInsideMoveTypeItemWithoutResourcesWithMRControl() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("POWORWMRCPISheet"), commonFunc.getDataFromTestDataSheet("WH"));
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInsideWoResMT"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("POWORWMRCPISheet"));
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("ItemType"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("POWORWMRCPISheet"));
			verifyRawMaterialsValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("POWORWMRCPISheet"));
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
			int AllocStartIndex = 0;
			for (int i = 0; i < 1; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndexSheet = (i == 0) ? 35 : 0;
				AllocStartIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				productionOrderInsideItemMoveType(StartIndexSheet, AllocStartIndex,
						webDB.getDataFromProperties("PMFFileName2"), webDB.getDataFromProperties("POWORWMRCPISheet"),
						commonFunc.getDataFromTestDataSheet("WH"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("POWORWMRCPISheet"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllInsideMoveTypeItemWithoutResourcesWithMRControl': "
							+ e);
			return false;
		}
	}

	/**
	 * This method is a following steps to complete Prod order as inside with Item
	 * type
	 * 
	 * @author Deepan-GWL
	 * @param StartIndex
	 * @param AllocateIndex
	 * @param FileName
	 * @param SheetName
	 */
	public void productionOrderInsideItemMoveType(int StartIndex, int AllocateIndex, String FileName, String SheetName,
			String Warehouse) {
		commonFunc.navigateToCompletionProductionOrderInProdPlanning();
		completionProductionOrderFunc.clickPendingIconInCompletionProcess();
		completionProductionOrderFunc
				.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
		completionProductionOrderFunc
				.clickProcessDetailsAndSelectAllProcess(VariableHandler.getProductionOrderNumber());
		completionProductionOrderFunc
				.clickCheckBoxTableAndCreateAndStoreMRNo(VariableHandler.getProductionOrderNumber());
		commonFunc.navigateToInventoryMaterialRequest();
		materialRequestFunc.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
		materialRequestFunc.verifyHeaderValidationInMaterialRequestWithDB(FileName, SheetName, StartIndex);
		materialIssuanceVoucherFunc.verifyWriteBeforeLotQuantityInLotForRawMaterials(FileName, SheetName, StartIndex,
				Warehouse);
		commonFunc.navigateToMaterialIssuanceVoucherTab();
		materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
		materialIssuanceVoucherFunc.clickheaderSaveIconInMIV();
		verifyWriteAllocateStockForRawMaterials(FileName, SheetName, AllocateIndex, Warehouse);
		materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
		commonFunc.navigateToMaterialIssuanceVoucherTab();
		materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
				commonFunc.getDataFromTestDataSheet("PostedStatus"));
//		int MivStartIndex =62;
		materialIssuanceVoucherFunc.verifyHeaderAndLinesValidationInMaterialIssuanceVoucherWithDB(FileName, SheetName,
				StartIndex);
		materialIssuanceVoucherFunc.verifyStockTransactionValidationInMIVForRawMaterials(FileName, SheetName,
				StartIndex);
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			if (flag) {
				materialReceiptFunctions
						.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(VariableHandler.getProductionOrderNumber());
			} else {
				materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
			}
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
		materialIssuanceVoucherFunc.verifyAfterLotQuantityInLotSerialValidationForRawMaterials(FileName, SheetName,
				StartIndex, Warehouse);
		materialIssuanceVoucherFunc.verifyStockTransactionValidationInMIVForRawMaterials(FileName, SheetName,
				StartIndex);
		commonFunc.navigateToCompletionProductionOrderMultipleProcessInProdPlanning();
		completionProductionOrderFunc.clickPendingIconInCompletionMultiProcess();
		completionProductionOrderFunc
				.searchMultipleProductionOrderNoAndVerifyFilter(VariableHandler.getProductionOrderNumber());
		completionProductionOrderFunc.selectPendingMultipleProdOrderAndClickCreateCompletionProcess(
				VariableHandler.getProductionOrderNumber());
		completionProductionOrderFunc
				.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
		completionProductionOrderFunc.verifyLinesWithRMValidationInCompletionProcessWithDB(FileName, SheetName,
				StartIndex);
		completionProductionOrderFunc.clickPostButtonInCompletionProcess();
		completionProductionOrderFunc.verifyHeaderAndLinesValidationInCompletionProcessWithDB(FileName, SheetName,
				StartIndex);
		completionProductionOrderFunc.verifyStockTransactionValidationInCompletionForRawMaterials(FileName, SheetName,
				StartIndex);
		commonFunc.navigateToCompletionProductionOrderInProdPlanning();
		completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
				VariableHandler.getProductionCompletionOrderNo(), commonFunc.getDataFromTestDataSheet("PostedStatus"));
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Single With
	 * Child Lot
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllInsideMoveTypeSingleWithoutResourcesWithChildLot() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("POWORWMRCCLSheet"), commonFunc.getDataFromTestDataSheet("WH"));
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomPoCl"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("POWORWMRCCLSheet"));
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("POWORWMRCCLSheet"));
			verifyRawMaterialsValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("POWORWMRCCLSheet"));
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
			int AllocStartIndex = 0;
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndexSheet = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
				AllocStartIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				productionOrderInsideSingleMoveType(StartIndexSheet, AllocStartIndex,
						webDB.getDataFromProperties("PMFFileName2"), webDB.getDataFromProperties("POWORWMRCCLSheet"),
						commonFunc.getDataFromTestDataSheet("WH"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("POWORWMRCCLSheet"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllInsideMoveTypeSingleWithoutResourcesWithChildLot': "
							+ e);
			return false;
		}
	}

	/**
	 * This Method Is Used To Verify Create Material Request
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateMaterialRequest() {
		flag = webDB.waitForElement(ProductionOrderLocators.CreateMrButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionOrderLocators.CreateMrButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.checkSuccessToastMessageDisappear();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Create Material Request Validation
	 *
	 * @author Deepan-GWL
	 */
	public void verifyCreateMaterialRequestValidation(String FileName, String SheetName, int StartIndex) {
		commonFunc.navigateToInventoryMaterialRequest();
		materialRequestFunc.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
		materialIssuanceVoucherFunc.verifyWriteBeforeLotQuantityInLotForRawMaterials(FileName, SheetName, StartIndex,
				commonFunc.getDataFromTestDataSheet("WH"));
		commonFunc.navigateToMaterialIssuanceVoucherTab();
		materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
		materialIssuanceVoucherFunc.clickheaderSaveIconInMIV();
		materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
		commonFunc.navigateToMaterialIssuanceVoucherTab();
		materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
				commonFunc.getDataFromTestDataSheet("PostedStatus"));
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			if (flag) {
				materialReceiptFunctions
						.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(VariableHandler.getProductionOrderNumber());
			} else {
				materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
			}
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
		commonFunc.navigateToCompletionProductionOrderInProdPlanning();
		completionProductionOrderFunc.verifySearchProductionOrdeNumberAndOpenDesiredStatus(
				VariableHandler.getProductionCompletionOrderNo(), "Draft");
	}

	/**
	 * This method used to Manual Production order with Move Type - Single With
	 * Child Lot
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutSOAllInsideMoveTypeSingleWithoutResourcesWithItemAndExcessConsumption() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("POWORWMRCEQSheet"), commonFunc.getDataFromTestDataSheet("WH"));
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomPoEq"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("POWORWMRCEQSheet"));
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("POWORWMRCEQSheet"));
			verifyRawMaterialsValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("POWORWMRCEQSheet"));
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				int StartIndexSheet = (i == 0) ? 18 : 0;
				int AllocStartIndex = (i == 0) ? 8 : 0;
				productionOrderInsideSingleMoveTypeForItemExcessConsumption(StartIndexSheet, AllocStartIndex,
						webDB.getDataFromProperties("PMFFileName2"), webDB.getDataFromProperties("POWORWMRCEQSheet"),
						commonFunc.getDataFromTestDataSheet("WH"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("POWORWMRCEQSheet"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutSOAllInsideMoveTypeSingleWithoutResourcesWithItemAndExcessConsumption': "
							+ e);
			return false;
		}
	}

	/**
	 * This method is a following steps to complete Prod order as inside with Single
	 * type
	 * 
	 * @author Deepan-GWL
	 * @param FileName
	 * @param SheetName
	 */
	public void productionOrderInsideSingleMoveTypeForItemExcessConsumption(int StartIndex, int AllocateIndex,
			String FileName, String SheetName, String Warehouse) {
		commonFunc.navigateToCompletionProductionOrderInProdPlanning();
		completionProductionOrderFunc.clickPendingIconInCompletionProcess();
		completionProductionOrderFunc
				.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
		completionProductionOrderFunc
				.clickCheckBoxTableAndCreateAndStoreMRNo(VariableHandler.getProductionOrderNumber());
		commonFunc.navigateToInventoryMaterialRequest();
		materialRequestFunc.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
		materialRequestFunc.verifyHeaderValidationInMaterialRequestWithDB(FileName, SheetName, StartIndex);
		materialIssuanceVoucherFunc.verifyWriteBeforeLotQuantityInLotForRawMaterials(FileName, SheetName, StartIndex,
				commonFunc.getDataFromTestDataSheet("WH"));
		commonFunc.navigateToMaterialIssuanceVoucherTab();
		materialIssuanceVoucherFunc.CreatePendingMIVDocument(VariableHandler.getMaterialRequestNo());
		materialIssuanceVoucherFunc.clickheaderSaveIconInMIV();
		verifyWriteAllocateStockForRawMaterials(FileName, SheetName, AllocateIndex, Warehouse);
		materialIssuanceVoucherFunc.clickSaveHeaderAndPostBtnInMIV();
		commonFunc.navigateToMaterialIssuanceVoucherTab();
		materialIssuanceVoucherFunc.checkStatusOfMaterialIssuanceVoucher(VariableHandler.getMIVNumber(),
				commonFunc.getDataFromTestDataSheet("PostedStatus"));
		materialIssuanceVoucherFunc.verifyHeaderAndLinesValidationInMaterialIssuanceVoucherWithDB(FileName, SheetName,
				StartIndex);
		materialIssuanceVoucherFunc.verifyStockTransactionValidationInMIVForRawMaterials(FileName, SheetName,
				StartIndex);
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoCreateMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			if (flag) {
				materialReceiptFunctions
						.verifyOpenAlreadyCreatedMRVDocumentsWithProdNo(VariableHandler.getProductionOrderNumber());
			} else {
				materialReceiptFunctions.verifyPendingMRVUsingMIV(VariableHandler.getMIVNumber());
			}
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
		materialIssuanceVoucherFunc.verifyAfterLotQuantityInLotSerialValidationForRawMaterials(FileName, SheetName,
				StartIndex, Warehouse);
		materialIssuanceVoucherFunc.verifyStockTransactionValidationInMIVForRawMaterials(FileName, SheetName,
				StartIndex);
		commonFunc.navigateToCompletionProductionOrderInProdPlanning();
		completionProductionOrderFunc.clickPendingIconInCompletionProcess();
		completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
				commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getProductionOrderNumber());
		completionProductionOrderFunc.clickAddIconConsumptionInProdCompletion();
		completionProductionOrderFunc.fillConsumptionLinesInProductionCompletion("WORK IN PROGRESS WAREHOUSE",
				"GWLD-NA-MAC-10002(RM)", "KGS", "3", "3");
		completionProductionOrderFunc.clickSaveIconConsumptionInProdCompletion();
		completionProductionOrderFunc.fillActuaConsumptionAsExcessInProductionCompletion();
		verifyCreateMaterialRequest();
		StartIndex = 30;
		verifyCreateMaterialRequestValidation(FileName, SheetName, StartIndex);
		completionProductionOrderFunc.clickAllocateDeallocateButtonInHeader();
		completionProductionOrderFunc
				.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100"));
		completionProductionOrderFunc.verifyLinesWithRMValidationInCompletionProcessWithDB(FileName, SheetName,
				StartIndex);
		completionProductionOrderFunc.clickPostButtonInCompletionProcess();
		completionProductionOrderFunc.verifyHeaderAndLinesValidationInCompletionProcessWithDB(FileName, SheetName,
				StartIndex);
		completionProductionOrderFunc.verifyStockTransactionValidationInCompletionForRawMaterials(FileName, SheetName,
				StartIndex);
		commonFunc.navigateToCompletionProductionOrderInProdPlanning();
		completionProductionOrderFunc.verifyCompletionProductionOrdeNumberWithDesiredStatus(
				VariableHandler.getProductionCompletionOrderNo(), commonFunc.getDataFromTestDataSheet("PostedStatus"));
		commonFunc.navigateSetupInventoryPage();
		flag = materialReceiptFunctions.autoPostMRVCheckboxIsSelected();
		if (!VariableHandler.getAutoPostMRVFlag()) {
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyPendingMRV(VariableHandler.getProductionCompletionOrderNo());
			materialReceiptFunctions.verifyReceiptLocatorsAndPutAway();
			materialReceiptFunctions.clickTickAndPostIcon();
			commonFunc.navigateToMaterialReceiptVoucher();
			materialReceiptFunctions.verifyValidateMaterialReceiptDocumentsStatus(VariableHandler.getMrvNumber(),
					commonFunc.getDataFromTestDataSheet("IssuedStatus"));
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Single
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutResourcesWithoutMaterialControlForProcessTypeSingle() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("ProdOrder-Single(Inside)WoResSheet"),
					commonFunc.getDataFromTestDataSheet("WH"));
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInsideWoRes"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("ProdOrder-Single(Inside)WoResSheet"));
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("ProdOrder-Single(Inside)WoResSheet"));
			verifyRawMaterialsValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("ProdOrder-Single(Inside)WoResSheet"));
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
			int AllocStartIndex = 0;
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndexSheet = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
				AllocStartIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				productionOrderInsideSingleMoveType(StartIndexSheet, AllocStartIndex,
						webDB.getDataFromProperties("PMFFileName2"),
						webDB.getDataFromProperties("ProdOrder-Single(Inside)WoResSheet"),
						commonFunc.getDataFromTestDataSheet("WH"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("ProdOrder-Single(Inside)WoResSheet"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutResourcesWithoutMaterialControlForProcessTypeSingle': "
							+ e);
			return false;
		}
	}

	/**
	 * This method used to Manual Production order with Move Type - Multiple
	 * 
	 * @author Deepan-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdOrderWithoutResourcsesWithoutMaterialControlWithMoveTypMultiple() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("POWORWMRCPMSheet"), commonFunc.getDataFromTestDataSheet("WH"));
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInsideWoResMT"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("POWORWMRCPMSheet"));
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("MultipleType"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("POWORWMRCPMSheet"));
			verifyRawMaterialsValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("POWORWMRCPMSheet"));
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
			int AllocStartIndex = 0;
			for (int i = 0; i < 2; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndexSheet = (i == 0) ? 35 : (i == 1) ? 45 : 0;
				AllocStartIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				System.out.println(
						"Outer Start Index " + StartIndexSheet + " Alloc Index " + AllocStartIndex + " i " + i);
				productionOrderInsideMultipleMoveType(i, StartIndexSheet, AllocStartIndex,
						webDB.getDataFromProperties("PMFFileName2"), webDB.getDataFromProperties("POWORWMRCPMSheet"),
						commonFunc.getDataFromTestDataSheet("WH"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(webDB.getDataFromProperties("PMFFileName2"),
					webDB.getDataFromProperties("POWORWMRCPMSheet"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdOrderWithoutResourcsesWithoutMaterialControlWithMoveTypMultiple': "
							+ e);
			return false;
		}
	}

	/**
	 * Production Completion without Resources-With Allow Exception (How to create
	 * Process Wise production completion for Single process without resources?-With
	 * Allow Exception)
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdCompSingleWithoutResourcesWithAllowException() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("L49ID"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix49"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
						commonFunc.getDataFromTestDataSheet("L49ID"), VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100-L49"));
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
			}
			commonFunc.navigateToCompletionProductionOrderInProdPlanning();
			completionProductionOrderFunc.clickAllowExceptionIconInProdCompletion();
			completionProductionOrderFunc
					.searchProdOrderNoAndSelectCheckBoxInAllowException(VariableHandler.getProductionOrderNumber());
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdCompSingleWithoutResourcesWithAllowException': " + e);
			return false;
		}
	}

	/**
	 * Production Completion without Resources-With Allow Exception (How to create
	 * Process Wise production completion for Multiple process without
	 * resources?-With Allow Exception)
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdCompMultipleWithoutResourcesWithAllowException() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("L49ID"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix49"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < 1; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				commonFunc.navigateToCompletionProductionOrderMultipleProcessInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionMultiProcess();
				completionProductionOrderFunc.selectPendingMultipleProdOrderAndClickCreateCompletionProcessWithNoButton(
						VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc.clickAllocateDeallocateButtonInHeader();
				completionProductionOrderFunc.selectShiftAndSaveHeaderInCompletionMultiProcess(
						commonFunc.getDataFromTestDataSheet("Shift_100"));
				completionProductionOrderFunc.clickPostButtonInCompletionMultiProcess();
			}
			commonFunc.navigateToCompletionProductionOrderInProdPlanning();
			completionProductionOrderFunc.clickAllowExceptionIconInProdCompletion();
			completionProductionOrderFunc
					.searchProdOrderNoAndSelectCheckBoxInAllowException(VariableHandler.getProductionOrderNumber());
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdCompMultipleWithoutResourcesWithAllowException': "
							+ e);
			return false;
		}
	}

	/**
	 * Production Completion without Resources-With Allow Exception (How to create
	 * Process Wise production completion for Item process without resources?-With
	 * Allow Exception)
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdCompItemWithoutResourcesWithAllowException() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("L49ID"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix49"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < 1; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc
						.clickMaterialRequestCreationAndSearchProdNo(VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc
						.clickProcessDetailsAndSelectAllProcess(VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc
						.clickCheckBoxTableAndCreateAndStoreMRNo(VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc.verifySearchProductionOrdeNumberAndOpenDesiredStatus(
						VariableHandler.getProductionCompletionOrderNo(),
						commonFunc.getDataFromTestDataSheet("DraftStatus"));
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100-L49"));
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
			}
			commonFunc.navigateToCompletionProductionOrderInProdPlanning();
			completionProductionOrderFunc.clickAllowExceptionIconInProdCompletion();
			completionProductionOrderFunc
					.searchProdOrderNoAndSelectCheckBoxInAllowException(VariableHandler.getProductionOrderNumber());
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdCompItemWithoutResourcesWithAllowException': " + e);
			return false;
		}
	}

	/**
	 * Production Completion without Resources-With Allow Exception (How to create
	 * MR for the Excess consumption Qty?-With Allow Exception and How to create MR
	 * for the newly added consumption Item?-With Allow Exception)
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCreateMRExcessConsumptionQtyNewlyAddedConsumptionItemWithAllowException() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("L49ID"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix49"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
						commonFunc.getDataFromTestDataSheet("L49ID"), VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100-L49"));
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
			}
			commonFunc.navigateToCompletionProductionOrderInProdPlanning();
			completionProductionOrderFunc.clickAllowExceptionIconInProdCompletion();
			completionProductionOrderFunc
					.searchProdOrderNoAndSelectCheckBoxInAllowException(VariableHandler.getProductionOrderNumber());
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdCompItemWithoutResourcesWithAllowException': " + e);
			return false;
		}
	}

	/**
	 * Production Completion without Resources-With Allow Exception (How to create
	 * process rejection)
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCreateProcessRejectionWithAllowException() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("L49ID"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix49"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"), "10");
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
						commonFunc.getDataFromTestDataSheet("L49ID"), VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc
						.fillProcessRejectInHeader(commonFunc.getDataFromTestDataSheet("ProcessRejection"), "5", "5");
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100-L49"));
				completionProductionOrderFunc.clickAddIconAndFillRejectionDefectsInCompletion(
						commonFunc.getDataFromTestDataSheet("DamageDefect"), "5", "0", "5");
				completionProductionOrderFunc.clickAddInScrapProductInCompletion(
						commonFunc.getDataFromTestDataSheet("ScrapItem-HKTVS1001"),
						commonFunc.getDataFromTestDataSheet("ScrapWH-L49"), "5");
				completionProductionOrderFunc.clickSaveHeaderInCompletion();
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
			}
			commonFunc.navigateToCompletionProductionOrderInProdPlanning();
			completionProductionOrderFunc.clickAllowExceptionIconInProdCompletion();
			completionProductionOrderFunc
					.searchProdOrderNoAndSelectCheckBoxInAllowException(VariableHandler.getProductionOrderNumber());
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyCreateProcessRejectionWithAllowException': " + e);
			return false;
		}
	}

	/**
	 * Production Completion without Resources-With Allow Exception (How to create
	 * Fully Rejection )
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCreateProcessFullyRejectionWithAllowException() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("L49ID"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix49"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"), "10");
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
						commonFunc.getDataFromTestDataSheet("L49ID"), VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc
						.fillProcessRejectInHeader(commonFunc.getDataFromTestDataSheet("ProcessRejection"), "10", "10");
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100-L49"));
				completionProductionOrderFunc.clickAddIconAndFillRejectionDefectsInCompletion(
						commonFunc.getDataFromTestDataSheet("DamageDefect"), "10", "0", "10");
				completionProductionOrderFunc.clickAddInScrapProductInCompletion(
						commonFunc.getDataFromTestDataSheet("ScrapItem-HKTVS1001"),
						commonFunc.getDataFromTestDataSheet("ScrapWH-L49"), "10");
				completionProductionOrderFunc.clickSaveHeaderInCompletion();
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
			}
			commonFunc.navigateToCompletionProductionOrderInProdPlanning();
			completionProductionOrderFunc.clickAllowExceptionIconInProdCompletion();
			completionProductionOrderFunc
					.searchProdOrderNoAndSelectCheckBoxInAllowException(VariableHandler.getProductionOrderNumber());
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyCreateProcessFullyRejectionWithAllowException': " + e);
			return false;
		}
	}

	/**
	 * Production Completion without Resources-With Allow Exception (How to create
	 * accept and reject(Fully Repair)-Primary Rej)
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCreatePrimaryRejectionFullyRepairWithAllowException() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("L49ID"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix49"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"), "10");
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
						commonFunc.getDataFromTestDataSheet("L49ID"), VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc
						.fillProcessRejectInHeader(commonFunc.getDataFromTestDataSheet("PrimaryRejection"), "0", "10");
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100-L49"));
				completionProductionOrderFunc.clickAddIconAndFillRejectionDefectsInCompletion(
						commonFunc.getDataFromTestDataSheet("DamageDefect"), "10", "10", "0");
				completionProductionOrderFunc.clickSaveHeaderInCompletion();
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
			}
			commonFunc.navigateToCompletionProductionOrderInProdPlanning();
			completionProductionOrderFunc.clickAllowExceptionIconInProdCompletion();
			completionProductionOrderFunc
					.searchProdOrderNoAndSelectCheckBoxInAllowException(VariableHandler.getProductionOrderNumber());
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyCreatePrimaryRejectionFullyRepairWithAllowException': "
							+ e);
			return false;
		}
	}

	/**
	 * Production Completion without Resources-With Allow Exception (How to create
	 * accept and reject(Fully Scrap)-Primary Rej)
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCreatePrimaryRejectionFullyScrapWithAllowException() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("L49ID"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix49"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"), "10");
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
						commonFunc.getDataFromTestDataSheet("L49ID"), VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc
						.fillProcessRejectInHeader(commonFunc.getDataFromTestDataSheet("PrimaryRejection"), "0", "10");
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100-L49"));
				completionProductionOrderFunc.clickAddIconAndFillRejectionDefectsInCompletion(
						commonFunc.getDataFromTestDataSheet("DamageDefect"), "10", "0", "10");
				completionProductionOrderFunc.clickAddInScrapProductInCompletion(
						commonFunc.getDataFromTestDataSheet("ScrapItem-HKTVS1001"),
						commonFunc.getDataFromTestDataSheet("ScrapWH-L49"), "10");
				completionProductionOrderFunc.clickSaveHeaderInCompletion();
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
			}
			commonFunc.navigateToCompletionProductionOrderInProdPlanning();
			completionProductionOrderFunc.clickAllowExceptionIconInProdCompletion();
			completionProductionOrderFunc
					.searchProdOrderNoAndSelectCheckBoxInAllowException(VariableHandler.getProductionOrderNumber());
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyCreatePrimaryRejectionFullyScrapWithAllowException': "
							+ e);
			return false;
		}
	}

	/**
	 * Production Completion without Resources-With Allow Exception (How to create
	 * accept and reject(Repair and Scrap)-Primary Rej)
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCreatePrimaryRejectionRepairScrapWithAllowException() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("L49ID"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix49"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"), "10");
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
						commonFunc.getDataFromTestDataSheet("L49ID"), VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc
						.fillProcessRejectInHeader(commonFunc.getDataFromTestDataSheet("PrimaryRejection"), "0", "10");
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100-L49"));
				completionProductionOrderFunc.clickAddIconAndFillRejectionDefectsInCompletion(
						commonFunc.getDataFromTestDataSheet("DamageDefect"), "10", "5", "5");
				completionProductionOrderFunc.clickAddInScrapProductInCompletion(
						commonFunc.getDataFromTestDataSheet("ScrapItem-HKTVS1001"),
						commonFunc.getDataFromTestDataSheet("ScrapWH-L49"), "5");
				completionProductionOrderFunc.clickSaveHeaderInCompletion();
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
			}
			commonFunc.navigateToCompletionProductionOrderInProdPlanning();
			completionProductionOrderFunc.clickAllowExceptionIconInProdCompletion();
			completionProductionOrderFunc
					.searchProdOrderNoAndSelectCheckBoxInAllowException(VariableHandler.getProductionOrderNumber());
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyCreatePrimaryRejectionRepairScrapWithAllowException': "
							+ e);
			return false;
		}
	}

	/**
	 * Production Completion without Resources-With Allow Exception (How to create
	 * accept and reject(Fully Repair) -Secondary Rej)
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCreateSecondaryRejectionFullyRepairWithAllowException() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("L49ID"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix49"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"), "10");
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
						commonFunc.getDataFromTestDataSheet("L49ID"), VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc.fillProcessRejectInHeader(
						commonFunc.getDataFromTestDataSheet("SecondaryRejection"), "0", "10");
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100-L49"));
				completionProductionOrderFunc.clickAddIconAndFillRejectionDefectsInCompletion(
						commonFunc.getDataFromTestDataSheet("DamageDefect"), "10", "10", "0");
				completionProductionOrderFunc.clickSaveHeaderInCompletion();
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
			}
			commonFunc.navigateToCompletionProductionOrderInProdPlanning();
			completionProductionOrderFunc.clickAllowExceptionIconInProdCompletion();
			completionProductionOrderFunc
					.searchProdOrderNoAndSelectCheckBoxInAllowException(VariableHandler.getProductionOrderNumber());
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyCreateSecondaryRejectionFullyRepairWithAllowException': "
							+ e);
			return false;
		}
	}

	/**
	 * Production Completion without Resources-With Allow Exception (How to create
	 * accept and reject(Fully Scrap)-Secondary Rej)
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCreateSecondaryRejectionFullyScrapWithAllowException() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("L49ID"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix49"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"), "10");
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
						commonFunc.getDataFromTestDataSheet("L49ID"), VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc.fillProcessRejectInHeader(
						commonFunc.getDataFromTestDataSheet("SecondaryRejection"), "0", "10");
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100-L49"));
				completionProductionOrderFunc.clickAddIconAndFillRejectionDefectsInCompletion(
						commonFunc.getDataFromTestDataSheet("DamageDefect"), "10", "0", "10");
				completionProductionOrderFunc.clickAddInScrapProductInCompletion(
						commonFunc.getDataFromTestDataSheet("ScrapItem-HKTVS1001"),
						commonFunc.getDataFromTestDataSheet("ScrapWH-L49"), "10");
				completionProductionOrderFunc.clickSaveHeaderInCompletion();
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
			}
			commonFunc.navigateToCompletionProductionOrderInProdPlanning();
			completionProductionOrderFunc.clickAllowExceptionIconInProdCompletion();
			completionProductionOrderFunc
					.searchProdOrderNoAndSelectCheckBoxInAllowException(VariableHandler.getProductionOrderNumber());
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyCreateSecondaryRejectionFullyScrapWithAllowException': "
							+ e);
			return false;
		}
	}

	/**
	 * Production Completion without Resources-With Allow Exception (How to create
	 * accept and reject(Repair and Scrap)-Secondary Rej)
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCreateSecondaryRejectionRepairScrapWithAllowException() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("L49ID"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix49"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"), "10");
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
						commonFunc.getDataFromTestDataSheet("L49ID"), VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc.fillProcessRejectInHeader(
						commonFunc.getDataFromTestDataSheet("SecondaryRejection"), "0", "10");
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100-L49"));
				completionProductionOrderFunc.clickAddIconAndFillRejectionDefectsInCompletion(
						commonFunc.getDataFromTestDataSheet("DamageDefect"), "10", "5", "5");
				completionProductionOrderFunc.clickAddInScrapProductInCompletion(
						commonFunc.getDataFromTestDataSheet("ScrapItem-HKTVS1001"),
						commonFunc.getDataFromTestDataSheet("ScrapWH-L49"), "5");
				completionProductionOrderFunc.clickSaveHeaderInCompletion();
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
			}
			commonFunc.navigateToCompletionProductionOrderInProdPlanning();
			completionProductionOrderFunc.clickAllowExceptionIconInProdCompletion();
			completionProductionOrderFunc
					.searchProdOrderNoAndSelectCheckBoxInAllowException(VariableHandler.getProductionOrderNumber());
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyCreateSecondaryRejectionRepairScrapWithAllowException': "
							+ e);
			return false;
		}
	}

	/**
	 * Production Completion without Resources-With Allow Exception (How to create
	 * With Scrap By Product with Add In Consumption)
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCreateScrapByProductWithAddInConsumptionWithAllowException() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("L49ID"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix49"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"), "10");
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
						commonFunc.getDataFromTestDataSheet("L49ID"), VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc
						.fillProcessRejectInHeader(commonFunc.getDataFromTestDataSheet("PrimaryRejection"), "0", "10");
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100-L49"));
				completionProductionOrderFunc.clickAddIconAndFillRejectionDefectsInCompletion(
						commonFunc.getDataFromTestDataSheet("DamageDefect"), "10", "0", "10");
				completionProductionOrderFunc.clickAddInScrapProductInCompletion(
						commonFunc.getDataFromTestDataSheet("ScrapItem-HKTVS1001"),
						commonFunc.getDataFromTestDataSheet("ScrapWH-L49"), "10");
				completionProductionOrderFunc.clickAddInConsumptionInScrapProduct();
				completionProductionOrderFunc.clickSaveHeaderInCompletion();
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
			}
			commonFunc.navigateToCompletionProductionOrderInProdPlanning();
			completionProductionOrderFunc.clickAllowExceptionIconInProdCompletion();
			completionProductionOrderFunc
					.searchProdOrderNoAndSelectCheckBoxInAllowException(VariableHandler.getProductionOrderNumber());
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyCreateScrapByProductWithAddInConsumptionWithAllowException': "
							+ e);
			return false;
		}
	}

	/**
	 * Production Completion without Resources-With Allow Exception (How to create
	 * With Scrap By Product without Add In Consumption)
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyCreateScrapByProductWithoutAddInConsumptionWithAllowException() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("L49ID"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix49"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"), "10");
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				commonFunc.navigateToCompletionProductionOrderInProdPlanning();
				completionProductionOrderFunc.clickPendingIconInCompletionProcess();
				completionProductionOrderFunc.selectComplProdOrderDocUsingProdNoInPending(
						commonFunc.getDataFromTestDataSheet("L49ID"), VariableHandler.getProductionOrderNumber());
				completionProductionOrderFunc
						.fillProcessRejectInHeader(commonFunc.getDataFromTestDataSheet("PrimaryRejection"), "0", "10");
				completionProductionOrderFunc
						.selectShiftAndSaveHeaderInCompletion(commonFunc.getDataFromTestDataSheet("Shift_100-L49"));
				completionProductionOrderFunc.clickAddIconAndFillRejectionDefectsInCompletion(
						commonFunc.getDataFromTestDataSheet("DamageDefect"), "10", "0", "10");
				completionProductionOrderFunc.clickAddInScrapProductInCompletion(
						commonFunc.getDataFromTestDataSheet("ScrapItem-HKTVS1001"),
						commonFunc.getDataFromTestDataSheet("ScrapWH-L49"), "10");
				completionProductionOrderFunc.clickSaveHeaderInCompletion();
				completionProductionOrderFunc.clickPostButtonInCompletionProcess();
			}
			commonFunc.navigateToCompletionProductionOrderInProdPlanning();
			completionProductionOrderFunc.clickAllowExceptionIconInProdCompletion();
			completionProductionOrderFunc
					.searchProdOrderNoAndSelectCheckBoxInAllowException(VariableHandler.getProductionOrderNumber());
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyCreateScrapByProductWithoutAddInConsumptionWithAllowException': "
							+ e);
			return false;
		}
	}

	/**
	 * Production Completion without Resources-With Allow Exception (How to create
	 * Process Wise production completion for Single process without
	 * resources?-Without Allow Exception)
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdCompSingleWithoutResourcesWithoutAllowException() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Single"));
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Single"));
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Single"));
			verifyRawMaterialsValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Single"));
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
			int AllocStartIndex = 0;
			for (int i = 0; i < VariableHandler.getProcessAndWarehousesBOM().length; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndexSheet = (i == 0) ? 35 : (i == 1) ? 45 : (i == 2) ? 56 : 0;
				AllocStartIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				productionOrderInsideSingleMoveType(StartIndexSheet, AllocStartIndex,
						webDB.getDataFromProperties("PMFFileName"),
						webDB.getDataFromProperties("AllInsideSheetName-Single"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Single"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdCompSingleWithoutResourcesWithoutAllowException': "
							+ e);
			return false;
		}
	}

	/**
	 * Production Completion without Resources-With Allow Exception (How to create
	 * Process Wise production completion for Multiple process without
	 * resources?-Without Allow Exception)
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdCompMultipleWithoutResourcesWithoutAllowException() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Multiple"));
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Multiple"));
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("MultipleType"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Multiple"));
			verifyRawMaterialsValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Multiple"));
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
			int AllocStartIndex = 0;
			for (int i = 0; i < 2; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndexSheet = (i == 0) ? 35 : (i == 1) ? 45 : 0;
				AllocStartIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				productionOrderInsideMultipleMoveType(i, StartIndexSheet, AllocStartIndex,
						webDB.getDataFromProperties("PMFFileName"),
						webDB.getDataFromProperties("AllInsideSheetName-Multiple"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Multiple"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdCompMultipleWithoutResourcesWithoutAllowException': "
							+ e);
			return false;
		}
	}

	/**
	 * Production Completion without Resources-With Allow Exception (How to create
	 * Process Wise production completion for Item process without
	 * resources?-Without Allow Exception)
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifyProdCompItemWithoutResourcesWithoutAllowException() {
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			verifyWriteBeforeStockForFGAndRawMaterials(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Item"));
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"),
					commonFunc.getDataFromTestDataSheet("BomAllInside"),
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyHeaderValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Item"));
			verifyProductionOrderMREventAndRestOP(null, null, commonFunc.getDataFromTestDataSheet("ItemType"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			verifyStoreProcessAndRawMaterial();
			verifyRoutingValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Item"));
			verifyRawMaterialsValidationInProductionOrderWithDB(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Item"));
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(webDB.getDataFromProperties("HariEmpId"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			int StartIndexSheet = 0;
			int AllocStartIndex = 0;
			for (int i = 0; i < 1; i++) {
				String ProcessName = VariableHandler.getProcessAndProcessType()[i][0];
				String ProcessType = VariableHandler.getProcessAndProcessType()[i][1];
				logs.logging("INFO", "Executing Process: " + (i + 1) + ". " + ProcessName);
				logs.logging("INFO", "ProcessType: " + ProcessType);
				StartIndexSheet = (i == 0) ? 35 : 0;
				AllocStartIndex = (i == 0) ? 10 : (i == 1) ? 17 : (i == 2) ? 24 : 0;
				productionOrderInsideItemMoveType(StartIndexSheet, AllocStartIndex,
						webDB.getDataFromProperties("PMFFileName"),
						webDB.getDataFromProperties("AllInsideSheetName-Item"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("CompletedStatus"));
			verifyValidationOfAfterStockUnitCostAndQuantityInFGItem(webDB.getDataFromProperties("PMFFileName"),
					webDB.getDataFromProperties("AllInsideSheetName-Item"));
			flag = commonFunc.logoutUser();
			return flag;
		} catch (Exception e) {
			logs.logging("INFO",
					"Exception occurs while executing 'verifyProdCompItemWithoutResourcesWithoutAllowException': " + e);
			return false;
		}
	}
}
