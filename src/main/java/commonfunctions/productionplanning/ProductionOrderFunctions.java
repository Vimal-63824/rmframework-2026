package commonfunctions.productionplanning;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 20, 2025
 */
import static org.testng.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.payroll.CommonPayrollFunctions;
import commonfunctions.supplychainmanagement.ApprovalPageFunctions;
import commonfunctions.supplychainmanagement.DeliveryChallanFunctions;
import commonfunctions.supplychainmanagement.GrnScoPageFunctions;
import commonfunctions.supplychainmanagement.MaterialIssuanceVoucherFunctions;
import commonfunctions.supplychainmanagement.MaterialReceiptVoucherFunctions;
import commonfunctions.supplychainmanagement.MaterialRequestFunctions;
import commonfunctions.supplychainmanagement.SalesOrderFunctions;
import commonfunctions.supplychainmanagement.StockTransactionPageFunctions;
import commonfunctions.supplychainmanagement.SubcontractOrderFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.productionplanning.ProductionOrderLocators;
import commonlocators.supplychainmanagement.SalesOrderLocators;
import utils.ReportLoger;
import utils.RestUtils;
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

	/**
	 * This Method used to verify Status of Production Order with MRP Number
	 * 
	 * @author Sakthi-GWL
	 * @param MRPNo
	 * @param ExpectedStatus
	 */
	public void searchAndVerifyStatusOfMRPNo(String MRPNo, String ExpectedStatus) {
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
		commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.FindProdOrderNoLovBtn, ProdOrderNo);
		flag = webDB.waitForElement(ProductionOrderLocators.FindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionOrderLocators.FindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(ProductionOrderLocators.Find_TableData_ProdNo(ProdOrderNo), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button Clicked in Production Order Find Page");
				webDB.clickAnElement(ProductionOrderLocators.Find_TableData_ProdNo(ProdOrderNo), ElementType.Xpath);
				flag = webDB.waitForElement(ProductionOrderLocators.ProdOrderReferenceDisplay, ElementType.Xpath);
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
		flag = webDB.waitForElement(ProductionOrderLocators.RoutingProcessNameTableDataLists, ElementType.Xpath);
		if (flag) {
			List<WebElement> processName = webDB.getDriver()
					.findElements(By.xpath(ProductionOrderLocators.RoutingProcessNameTableDataLists));
			logs.logging("INFO", "Process Name Size: " + processName.size());
			List<WebElement> consumptionWH = webDB.getDriver()
					.findElements(By.xpath(ProductionOrderLocators.RoutingConsumptionWHTableDataLists));
			logs.logging("INFO", "Consumption Warehouse Size: " + consumptionWH.size());
			List<WebElement> recipientWH = webDB.getDriver()
					.findElements(By.xpath(ProductionOrderLocators.RoutingRecipientWHTableDataLists));
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
		flag = webDB.waitForElement(ProductionOrderLocators.RoutingProcessNameTableDataLists, ElementType.Xpath);
		if (flag) {
			List<WebElement> processName = webDB.getDriver()
					.findElements(By.xpath(ProductionOrderLocators.RoutingProcessNameTableDataLists));
			logs.logging("INFO", "Process Name Size: " + processName.size());
			List<WebElement> processType = webDB.getDriver()
					.findElements(By.xpath(ProductionOrderLocators.RoutingProcessTypeTableDataLists));
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
			flag = webDB.waitForElement(ProductionOrderLocators.RoutingTable, ElementType.Xpath);
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
			flag = webDB.waitForElement(ProductionOrderLocators.MaterialReqTable, ElementType.Xpath);
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
			flag = webDB.waitForElement(ProductionOrderLocators.MaterialReqRMItemTableDataLists, ElementType.Xpath);
			if (flag) {
				commonFunc.hardWait(2000);
				List<WebElement> ItemList = webDB.getDriver()
						.findElements(By.xpath(ProductionOrderLocators.MaterialReqRMItemTableDataLists));
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
			flag = webDB.waitForElement(ProductionOrderLocators.MaterialReqProcessTableDataLists, ElementType.Xpath);
			if (flag) {
				commonFunc.hardWait(2000);
				List<WebElement> ProcesList = webDB.getDriver()
						.findElements(By.xpath(ProductionOrderLocators.MaterialReqProcessTableDataLists));
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
			flag = webDB.waitForElement(ProductionOrderLocators.ResourcesProcessTableDataLists, ElementType.Xpath);
			if (flag) {
				commonFunc.hardWait(2000);
				List<WebElement> ProcesList = webDB.getDriver()
						.findElements(By.xpath(ProductionOrderLocators.ResourcesProcessTableDataLists));
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

	/**
	 * This Method used to save header and post Production Order
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickHeaderSaveAndPostProdOrder() {
		flag = webDB.waitForElement(ProductionOrderLocators.HeaderSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(ProductionOrderLocators.HeaderSaveIcon, ElementType.Xpath);
			webDB.clickAnElement(ProductionOrderLocators.HeaderSaveIcon, ElementType.Xpath);
			commonFunc.checkSuccessToastMessageDisappear();
			commonFunc.hardWait(5000);
			flag = webDB.waitForClickElement(ProductionOrderLocators.ProdOrderPostBtn, ElementType.Xpath);
			if (flag) {
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
				logs.logging("INFO", "Header Saved Successfully");
				flag = webDB.waitForClickElement(ProductionOrderLocators.ProdOrderPostBtn, ElementType.Xpath);
				if (flag) {
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
			List<WebElement> elements = webDB.getDriver()
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
		commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.CreateProdOrd_OrderTypeLovbtn, OrdType);
		logs.logging("INFO", "Order Type Selected in Prod Order: " + OrdType);
		switchToCreateProductionOrderFrame();
		commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.CreateProdOrd_PrefixLovbtn, Pfx);
		logs.logging("INFO", "Prefix Selected in Prod Order: " + Pfx);
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
		commonPayrollFunc.addDetailInTableWithoutLovButton(ProductionOrderLocators.CreateProdOrd_OrderQntyInput,
				ProductionOrderLocators.CreateProdOrd_OrderQntyInput, OrdQnty);
		logs.logging("INFO", "Order Quantity Entered in Prod Order: " + OrdQnty);
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
				commonPayrollFunc.addDetailInTableWithoutLovButton(ProductionOrderLocators.CreateProdOrd_OrderQntyInput,
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
		List<WebElement> TotalElement = webDB.getDriver().findElements(By.xpath(ProductionOrderLocators.TotalProcess));
		for (WebElement element : TotalElement) {
			TotalProcess.add(element.getText());
		}
		VariableHandler.setTotalProductionProcess(TotalProcess);
		flag = webDB.waitForElement(ProductionOrderLocators.ShowMore, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Show More Button Is Displayed");
			webDB.clickAnElement(ProductionOrderLocators.ShowMore, ElementType.Xpath);
			flag = webDB.waitForElement(ProductionOrderLocators.FirstOprSelect, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "First Opr Select DropDown Is Displayed");
				webDB.selectDropDownByText(ProductionOrderLocators.FirstOprSelect,
						commonFunc.getDataFromTestDataSheet("ComQty"), ElementType.Xpath);
				webDB.selectDropDownByText(ProductionOrderLocators.RestOprSelect,
						commonFunc.getDataFromTestDataSheet("ComQty"), ElementType.Xpath);
				flag = webDB.waitForElement(ProductionOrderLocators.ShowLess, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Show Less Button Is Displayed");
					webDB.clickAnElement(ProductionOrderLocators.ShowLess, ElementType.Xpath);
				}
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
		List<WebElement> elements = webDB.getDriver().findElements(By.xpath(ProductionOrderLocators.ProcessTypeList));
		for (int i = 1; i <= elements.size(); i++) {
			flag = webDB.waitForElement(ProductionOrderLocators.ProcessName(i), ElementType.Xpath);
			if (flag) {
				String ProcessName = webDB.getTextFromElement(ProductionOrderLocators.ProcessName(i),
						ElementType.Xpath);
				String ProcessType = elements.get(i - 1).getText();
				ProcessTypeMap.put(ProcessType, ProcessName);
			}
		}
		VariableHandler.setProductionProcess(ProcessTypeMap);
		flag = webDB.waitForElement(ProductionOrderLocators.MaterialRequestTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionOrderLocators.MaterialRequestTab, ElementType.Xpath);
			flag = webDB.waitForElement(ProductionOrderLocators.MaterialProcess, ElementType.Xpath);
			if (flag) {
				Map<String, String> ProcessItemMap = new HashMap<String, String>();
				List<WebElement> MaterialProcess = webDB.getDriver()
						.findElements(By.xpath(ProductionOrderLocators.MaterialProcess));
				for (int i = 1; i <= MaterialProcess.size(); i++) {
					flag = webDB.waitForElement(ProductionOrderLocators.ItemName(i), ElementType.Xpath);
					if (flag) {
						String ItemName = webDB.getTextFromElement(ProductionOrderLocators.ItemName(i),
								ElementType.Xpath);
						String ProcessName = MaterialProcess.get(i - 1).getText();
						ProcessItemMap.put(ProcessName, ItemName);
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
					// TODO Will Add Steps for Outside Operations Subcontract Steps
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
			flag = webDB.waitForElement(ProductionOrderLocators.FirstOprSelect, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "First Opr Select DropDown Is Displayed");
				flag = webDB.selectDropDownByText(ProductionOrderLocators.ProcessMoveType, MoveType, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Move Type Is Selected: " + MoveType);
					flag = webDB.selectDropDownByText(ProductionOrderLocators.FirstOprSelect, FirstOpr,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "First Opr Select Is: " + FirstOpr);
						flag = webDB.selectDropDownByText(ProductionOrderLocators.RestOprSelect, RestOpr,
								ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Rest Opr Select Is: " + RestOpr);
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
		List<WebElement> elements = webDB.getDriver().findElements(By.xpath(ProductionOrderLocators.ProcessTypeList));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
					// TODO Will Add Steps for Outside Operations Subcontract Steps
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
					approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
					approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
					approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
					// TODO Will Add Steps for Outside Operations Subcontract Steps
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Cancel Button Clicked in Production Order");
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
			flag = webDB.waitForElement(ProductionOrderLocators.RoutingProcessSeqTableInput, ElementType.Xpath);
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
					// TODO Will Add Steps for Outside Operations Subcontract Steps
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
		commonPayrollFunc.addDetailInTableWithoutLovButton(ProductionOrderLocators.RoutingProcessSeqTableData,
				ProductionOrderLocators.RoutingProcessSeqTableInput, ProcessNo);
		logs.logging("INFO", "Process No Entered: " + ProcessNo);
		commonPayrollFunc.addDetailInTableWithoutLovButton(ProductionOrderLocators.RoutingOprnNoTableData,
				ProductionOrderLocators.RoutingOprnNoTableInput, OprnNo);
		logs.logging("INFO", "Oprn No Entered: " + OprnNo);
		flag = webDB.waitForElement(ProductionOrderLocators.RoutingProcessTableData, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionOrderLocators.RoutingProcessTableData, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.RoutingProcessTableLovBtn, ProcessId);
			logs.logging("INFO", "Process Id Selected: " + ProcessId);
		}
		assertTrue(flag);
	}

	private void clickLineDetailsSelectLotSerialType() {
		flag = webDB.waitForElement(ProductionOrderLocators.RoutingLineDetailsIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(ProductionOrderLocators.RoutingLineDetailsIcon, ElementType.Xpath);
			webDB.clickAnElement(ProductionOrderLocators.RoutingLineDetailsIcon, ElementType.Xpath);
			flag = webDB.waitForElement(ProductionOrderLocators.LineDetailsIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Line Details Clicked");
				webDB.switchToFrame(ProductionOrderLocators.LineDetailsIframe, ElementType.Xpath);
				flag = webDB.waitForElement(ProductionOrderLocators.NonStandardLotSerialDetailTable, ElementType.Xpath);
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
								webDB.clickAnElement(ProductionOrderLocators.LineDetailsCloseIcon, ElementType.Xpath);
								flag = webDB.waitForClickElement(ProductionOrderLocators.RoutingLineDetailsIcon,
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
		flag = webDB.waitForElement(ProductionOrderLocators.RoutingTypeTableData, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionOrderLocators.RoutingTypeTableData, ElementType.Xpath);
			commonFunc.hardWait(500);
			flag = webDB.waitForClickElement(ProductionOrderLocators.RoutingTypeTableSelect, ElementType.Xpath);
			if (flag) {
				flag = webDB.selectDropDownByText(ProductionOrderLocators.RoutingTypeTableSelect, Type,
						ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Process Type Selected: " + Type);
					flag = webDB.waitForElement(ProductionOrderLocators.RoutingConsWHTableData, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(ProductionOrderLocators.RoutingConsWHTableData, ElementType.Xpath);
						commonFunc.hardWait(500);
						flag = webDB.waitForClickElement(ProductionOrderLocators.RoutingConsWHTableSelect,
								ElementType.Xpath);
						if (flag) {
							flag = webDB.selectDropDownByText(ProductionOrderLocators.RoutingConsWHTableSelect, ConsWH,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Cons.WH Selected: " + ConsWH);
								flag = webDB.waitForElement(ProductionOrderLocators.RoutingRcptWHTableData,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(ProductionOrderLocators.RoutingRcptWHTableData,
											ElementType.Xpath);
									commonFunc.hardWait(500);
									flag = webDB.waitForClickElement(ProductionOrderLocators.RoutingRcptWHTableSelect,
											ElementType.Xpath);
									if (flag) {
										flag = webDB.selectDropDownByText(
												ProductionOrderLocators.RoutingRcptWHTableSelect, RcptWH,
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
						// TODO Will Add Steps for Outside Operations Subcontract Steps
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
							System.out.println(VariableHandler.getMRNumber());
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
						grnScoPageFunc.addHeatNumberAndTestNumber();
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
			flag = webDB.waitForElement(ProductionOrderLocators.MRItemTableData, ElementType.Xpath);
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
		flag = webDB.waitForElement(ProductionOrderLocators.MRProcessTableData, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionOrderLocators.MRProcessTableData, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.MRProcessTableLovBtn, Process);
			logs.logging("INFO", "Process Selected in Material: " + Process);
			flag = webDB.waitForElement(ProductionOrderLocators.MRItemTableData, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(ProductionOrderLocators.MRItemTableData, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.MRItemTableLovBtn, Item);
				logs.logging("INFO", "Item Selected in Material: " + Item);
				commonFunc.verifyAddTableSpecs(ProductionOrderLocators.MRBOMQntyTableData,
						ProductionOrderLocators.MRBOMQntyTableInput, BomQnty);
				logs.logging("INFO", "BOM Quantity Entered: " + BomQnty);
//				webDB.doubleClickAnElement(ProductionOrderLocators.MRBOMQntyTableData, ElementType.Xpath);
				webDB.pressEnterKey();
				flag = webDB.waitForElement(ProductionOrderLocators.MRReqQntyTableData, ElementType.Xpath);
				if (flag) {
					webDB.moveToElement(ProductionOrderLocators.MRReqQntyTableData, ElementType.Xpath);
					webDB.clickAnElement(ProductionOrderLocators.MRReqQntyTableData, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					String ReqQnty = webDB.getTextFromElement(ProductionOrderLocators.MRReqQntyTableData,
							ElementType.Xpath);
//				commonPayrollFunc.addDetailInTableWithoutLovButton(ProductionOrderLocators.MRReqQntyTableData,
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
			flag = webDB.waitForElement(ProductionOrderLocators.ResourceProcessTableData, ElementType.Xpath);
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
		flag = webDB.waitForElement(ProductionOrderLocators.ResourceProcessTableData, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionOrderLocators.ResourceProcessTableData, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.ResourceProcessTableLovBtn, Process);
			logs.logging("INFO", "Process Selected In Resources: " + Process);
			flag = webDB.waitForElement(ProductionOrderLocators.ResourceTypeTableData, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(ProductionOrderLocators.ResourceTypeTableData, ElementType.Xpath);
				flag = webDB.waitForElement(ProductionOrderLocators.ResourceTypeTableSelect, ElementType.Xpath);
				if (flag) {
					flag = webDB.selectDropDownByText(ProductionOrderLocators.ResourceTypeTableSelect, Type,
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Type Seleced In Resources: " + Type);
						flag = webDB.waitForElement(ProductionOrderLocators.ResourceBasisTableData, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(ProductionOrderLocators.ResourceBasisTableData, ElementType.Xpath);
							flag = webDB.waitForElement(ProductionOrderLocators.ResourceBasisTableSelect,
									ElementType.Xpath);
							if (flag) {
								flag = webDB.selectDropDownByText(ProductionOrderLocators.ResourceBasisTableSelect,
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
		flag = webDB.waitForElement(ProductionOrderLocators.ResourceGroupTableData, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionOrderLocators.ResourceGroupTableData, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.ResourceGroupTableLovBtn, Resource);
			logs.logging("INFO", "Resource/Group Selected In Resources: " + Resource);
			commonPayrollFunc.addDetailInTableWithoutLovButton(ProductionOrderLocators.ResourceDateTableData,
					ProductionOrderLocators.ResourceDateTableInput, Date);
			logs.logging("INFO", "Date Entered In Resources: " + Date);
			commonPayrollFunc.addDetailInTableWithoutLovButton(ProductionOrderLocators.ResourceEffTableData,
					ProductionOrderLocators.ResourceEffTableInput, EffPerc);
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
		commonPayrollFunc.addDetailInTableWithoutLovButton(ProductionOrderLocators.ResourceSetupHrsTableData,
				ProductionOrderLocators.ResourceSetupHrsTableInput, SetupHrs);
		logs.logging("INFO", "SetupHrs Entered In Resources: " + SetupHrs);
		commonPayrollFunc.addDetailInTableWithoutLovButton(ProductionOrderLocators.ResourceSetupMinTableData,
				ProductionOrderLocators.ResourceSetupMinTableInput, SetupMins);
		logs.logging("INFO", "SetupMins Entered In Resources: " + SetupMins);
		commonPayrollFunc.addDetailInTableWithoutLovButton(ProductionOrderLocators.ResourcePriorityTableData,
				ProductionOrderLocators.ResourcePriorityTableInput, Priority);
		logs.logging("INFO", "Priority Entered In Resources: " + Priority);
		commonPayrollFunc.addDetailInTableWithoutLovButton(ProductionOrderLocators.ResourceLagHrsTableData,
				ProductionOrderLocators.ResourceLagHrsTableInput, LagHrs);
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
		commonPayrollFunc.addDetailInTableWithoutLovButton(ProductionOrderLocators.ResourceHrsTableData,
				ProductionOrderLocators.ResourceHrsTableInput, Hrs);
		logs.logging("INFO", "Hrs Entered In Resources: " + Hrs);
		commonPayrollFunc.addDetailInTableWithoutLovButton(ProductionOrderLocators.ResourceMinTableData,
				ProductionOrderLocators.ResourceMinTableInput, Mins);
		logs.logging("INFO", "Mins Entered In Resources: " + Mins);
		commonPayrollFunc.addDetailInTableWithoutLovButton(ProductionOrderLocators.ResourceSecTableData,
				ProductionOrderLocators.ResourceSecTableInput, Sec);
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
		int OrderReleaseCount = 0;
		String FirstOpr = commonFunc.getDataFromTestDataSheet("Orderreleases");
		String RestOpr = commonFunc.getDataFromTestDataSheet("Orderreleases");
		String StandardBom = commonFunc.getDataFromTestDataSheet("StandardBOM");
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderStandard"),
					commonFunc.getDataFromTestDataSheet("PROPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"), StandardBom,
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(FirstOpr, RestOpr,
					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			verifyProcessType(commonFunc.getDataFromTestDataSheet("InsideType"),
					commonFunc.getDataFromTestDataSheet("InsideType"));
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
						// TODO Will Add Steps for Outside Operations Subcontract Steps
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String Today = date.format(formatter);
		try {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateToProductionOrderInProdPlanning();
			clickAddIconInCreationProductionOrder();
			createProdOrderAndFillFormDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
					commonFunc.getDataFromTestDataSheet("ProdOrderNonStandard"),
					commonFunc.getDataFromTestDataSheet("PRODPrefix"),
					commonFunc.getDataFromTestDataSheet("ManufactureType"), FGItem,
					commonFunc.getDataFromTestDataSheet("AmdDisc_Value"));
			clickSaveIconInCreationProductionOrder();
			verifyProductionOrderMREventAndRestOP(commonFunc.getDataFromTestDataSheet("OrderReleaseOption"),
					commonFunc.getDataFromTestDataSheet("OrderReleaseOption"),
					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
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
//			navigateToResourcesSubTab();
//			clickAddLineIconInResourceTab();
//			fillProcessTypeBasisInResourceTab(commonFunc.getDataFromTestDataSheet("GwlProcess1"),
//					commonFunc.getDataFromTestDataSheet("ResourceText"),
//					commonFunc.getDataFromTestDataSheet("ItemType"));
//			fillResourceDateEffPercentInResourceTab(
//					commonFunc.getDataFromTestDataSheet("AutomationResource").toUpperCase(), Today, "0");
//			fillSetupHrsMinsAndPriorityLagHrsInResourceTab("0", "0", "1", "0");
//			fillHrsMinsAndSecInResourceTab("1", "0", "0");
//			clickSaveLineIconInResourceTab();
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("ROAU-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HariEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToProductionOrderInProdPlanning();
			searchAndVerifyStatusOfPONo(VariableHandler.getProductionOrderNumber(),
					commonFunc.getDataFromTestDataSheet("Inprogress"));
			webDB.navigateToRefresh();
			commonFunc.navigateToInventoryMaterialRequest();
			materialRequestFunc
					.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
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
			String Qnty = RandomStringUtils.randomNumeric(2);
			if (Qnty.equals("00")) {
				Qnty = "10";
			}
			String Cost = RandomStringUtils.randomNumeric(2);
			if (Cost.equals("00")) {
				Cost = "10";
			}
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
//			selectFirstRestOprEventvaluesAndSaveHeader(commonFunc.getDataFromTestDataSheet("OrderReleaseOption"),
//					commonFunc.getDataFromTestDataSheet("OrderReleaseOption"));
			verifyProductionOrderMREventAndRestOP(commonFunc.getDataFromTestDataSheet("OrderReleaseOption"),
					commonFunc.getDataFromTestDataSheet("OrderReleaseOption"),
					commonFunc.getDataFromTestDataSheet("Emp_MaritalStatus"));
			navigateToRoutingSubTab();
			storeRoutingProcessAndProcessType();
			storeRoutingProcessAndConsumptionAndRecipientWareHouse();
			navigateToMaterialRequirementSubTab();
			verifyItemInMaterialRequirement(new String[] { RMItem });
			verifyProcessInMaterialRequirement(new String[] { commonFunc.getDataFromTestDataSheet("GwlProcess1") });
			navigateToExceptionSubTab();
			validateNoExceptionsShowsInExceptionTab();
			clickHeaderSaveAndPostProdOrder();
			if (!VariableHandler.getApprovalFlag()) {
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getProductionOrderNumber(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
			}
			commonFunc.navigateToInventoryMaterialRequest();
			materialRequestFunc
					.storeMRNoAndCheckStatusDraftAndPostWithProdNo(VariableHandler.getProductionOrderNumber());
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
			searchAndVerifyStatusOfMRPNo(VariableHandler.getMRPNumber(),
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getScoNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			String Value = VariableHandler.getLastApprovalAPIHeaderText();
			Value = Value.replace("Material Request Created. : ", "").trim();
			Value = Value.replace("SUBCONTRACT ORDER Document is Approved.", "").trim();
			VariableHandler.setMRNumber(Value);
			System.out.println(VariableHandler.getMRNumber());
		}
		commonFunc.navigateToSubcontractOrder();
		subContractOrderFunc.verifyValidateSubContractDocumentsStatus(VariableHandler.getScoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.navigateToInventoryMaterialRequest();
		materialRequestFunc.verifyCreateMaterialRequestDocument(VariableHandler.getMRNumber());
		materialRequestFunc.verifyNavigateToLineDetail(VariableHandler.getScoNumber());
		materialRequestFunc.headerSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
		grnScoPageFunc.addHeatNumberAndTestNumber();
		grnScoPageFunc.checkConsumptionBeforeGenerationMat();
		grnScoPageFunc.verifyGenMatConsAndAllocate();
		grnScoPageFunc.checkConsumptionAfterGenerationMat();
		grnScoPageFunc.verifyViewLineDetails();
		grnScoPageFunc.saveHeaderAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
						// TODO Will Add Steps for Outside Operations Subcontract Steps
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
						// TODO Will Add Steps for Outside Operations Subcontract Steps
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
						// TODO Will Add Steps for Outside Operations Subcontract Steps
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
						// TODO Will Add Steps for Outside Operations Subcontract Steps
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
					approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
					approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
						// TODO Will Add Steps for Outside Operations Subcontract Steps
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						// -----------------------------------------------------------------------------------------------------
						// TODO Will Add Steps for Outside Operations Subcontract Steps
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
						// TODO Will Add Steps for Outside Operations Subcontract Steps
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						// -----------------------------------------------------------------------------------------------------
						// TODO Will Add Steps for Outside Operations Subcontract Steps
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
						// TODO Will Add Steps for Outside Operations Subcontract Steps
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						// -----------------------------------------------------------------------------------------------------
						// TODO Will Add Steps for Outside Operations Subcontract Steps
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
						// TODO Will Add Steps for Outside Operations Subcontract Steps
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						// -----------------------------------------------------------------------------------------------------
						// TODO Will Add Steps for Outside Operations Subcontract Steps
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
						// TODO Will Add Steps for Outside Operations Subcontract Steps
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						// -----------------------------------------------------------------------------------------------------
						// TODO Will Add Steps for Outside Operations Subcontract Steps
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
						// TODO Will Add Steps for Outside Operations Subcontract Steps
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						// -----------------------------------------------------------------------------------------------------
						// TODO Will Add Steps for Outside Operations Subcontract Steps
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
						// TODO Will Add Steps for Outside Operations Subcontract Steps
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						// -----------------------------------------------------------------------------------------------------
						// TODO Will Add Steps for Outside Operations Subcontract Steps
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
						// TODO Will Add Steps for Outside Operations Subcontract Steps
					} else if (Operation.equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("ComQty"))) {
						// -----------------------------------------------------------------------------------------------------
						// TODO Will Add Steps for Outside Operations Subcontract Steps
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
						// TODO Will Add Steps for Outside Operations Subcontract Steps
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
						// TODO Will Add Steps for Outside Operations Subcontract Steps
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
						// TODO Will Add Steps for Outside Operations Subcontract Steps
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
						// TODO Will Add Steps for Outside Operations Subcontract Steps
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
						// TODO Will Add Steps for Outside Operations Subcontract Steps
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
						// TODO Will Add Steps for Outside Operations Subcontract Steps
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
						// TODO Will Add Steps for Outside Operations Subcontract Steps
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
						// TODO Will Add Steps for Outside Operations Subcontract Steps
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
						// TODO Will Add Steps for Outside Operations Subcontract Steps
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
				approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
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
						// TODO Will Add Steps for Outside Operations Subcontract Steps
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
}
