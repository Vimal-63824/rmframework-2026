package commonfunctions.productionplanning;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 20, 2025
 */
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
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
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
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
}
