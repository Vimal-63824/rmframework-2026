package commonfunctions.productionplanning;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 20, 2025
 */
import static org.testng.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.payroll.CommonPayrollFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.productionplanning.MRPLocators;
import commonlocators.productionplanning.ProductionOrderLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class ProductionOrderFunctions {
	private static boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static CommonPayrollFunctions commonPayrollFunc = new CommonPayrollFunctions();

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
	 * This Method used to navigate Routing Tab in Production Order
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToRoutingSubTab() {
		flag = webDB.waitForElement(ProductionOrderLocators.RoutingLabel, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(ProductionOrderLocators.RoutingLabel, ElementType.Xpath);
			flag = webDB.waitForElement(ProductionOrderLocators.RoutingProcessNameTableDataLists, ElementType.Xpath);
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
			flag = webDB.waitForElement(ProductionOrderLocators.MaterialReqRMItemTableDataLists, ElementType.Xpath);
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
			flag = webDB.waitForElement(ProductionOrderLocators.ResourcesProcessTableDataLists, ElementType.Xpath);
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
		flag = webDB.waitForElement(ProductionOrderLocators.CreateProdOrd_BOMTypeSelect, ElementType.Xpath);
		if (flag) {
			flag = webDB.selectDropDownByText(ProductionOrderLocators.CreateProdOrd_BOMTypeSelect, BOMType,
					ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "BOM Type Selected in Prod Order: " + BOMType);
				commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.CreateProdOrd_ItemLovbtn, Item);
				logs.logging("INFO", "Item Selected in Prod Order: " + Item);
				switchToCreateProductionOrderFrame();
				commonPayrollFunc.addDetailInTableWithoutLovButton(ProductionOrderLocators.CreateProdOrd_OrderQntyInput,
						ProductionOrderLocators.CreateProdOrd_OrderQntyInput, OrdQnty);
				logs.logging("INFO", "Order Quantity Entered in Prod Order: " + OrdQnty);
			}
		}
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
				commonPayrollFunc.addDetailInTableWithoutLovButton(ProductionOrderLocators.CreateProdOrd_OrderQntyInput,
						ProductionOrderLocators.CreateProdOrd_OrderQntyInput, OrdQnty);
				logs.logging("INFO", "Order Quantity Entered in Prod Order: " + OrdQnty);
				commonFunc.searchAndSelectValueInFilters(ProductionOrderLocators.SoDesc, SoDesc);
				logs.logging("INFO", "So Desc Entered in Prod Order: " + SoDesc);
				switchToCreateProductionOrderFrame();
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

	}

	/**
	 * This Method used to verify Status of Production Order with MRP Number
	 * 
	 * @author Sakthi-GWL
	 * @param MRPNo
	 * @param ExpectedStatus
	 */
	public void searchAndVerifyStatusOfPONo(String PONo, String ExpectedStatus) {
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

}
