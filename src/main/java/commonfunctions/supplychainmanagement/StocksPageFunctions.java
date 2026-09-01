package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.StocksPageLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;
import static org.testng.Assert.assertTrue;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class StocksPageFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	private static PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();

	/**
	 * This method Used to Verify Get Item Stock Value
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 * @param Warehouse
	 */
	public void getItemStockValue(String Item, String Warehouse) {
		flag = webDB.waitForElement(CommonUiLocators.ItemLovButton, ElementType.Xpath);
		commonFunc.searchAndSelectValueInFilters(CommonUiLocators.ItemLovButton, Item);
		flag = webDB.waitForElement(CommonUiLocators.StocksCheckBox, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Stock Check Box Is Displayed");
			webDB.clickAnElement(StocksPageLocators.StockCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(StocksPageLocators.GenerateButton, ElementType.Xpath);
			if (flag) {
				commonFunc.hardWait(2000);
				webDB.clickAnElement(StocksPageLocators.GenerateButton, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
//				flag = webDB.waitForElement(StocksPageLocators.StockItem, ElementType.Xpath);
//				if (flag) {
//					commonFunc.checkRefreshCellsDisappear();
//					commonFunc.hardWait(2000);
//					logs.logging("INFO", "Stock Item Header Is Displayed");
//					webDB.clickAnElement(StocksPageLocators.StockItem, ElementType.Xpath);
//					flag = webDB.waitForElement(StocksPageLocators.StockSerachField, ElementType.Xpath);
//					if (flag) {
//						webDB.sendTextToAnElement(StocksPageLocators.StockSerachField, Item, ElementType.Xpath);
//						logs.logging("INFO", "Entered Item Value Is: " + Item);
//						webDB.pressEnterKey();
//						flag = webDB.waitForElement(StocksPageLocators.SelectItem(Item), ElementType.Xpath);
//						if (flag) {
//							webDB.clickAnElement(StocksPageLocators.SelectItem(Item), ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(StocksPageLocators.NoOfRow, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "No Of Item In Row Is Displayed");
					int totalvalue = webDB.getDriver().findElements(By.xpath(StocksPageLocators.NoOfRow)).size();
					System.out.println("int total value" + totalvalue);
					for (int i = 2; i <= totalvalue + 1; i++) {
						flag = webDB.waitForElement(StocksPageLocators.ItemRow(i), ElementType.Xpath);

						if (flag) {
							flag = webDB.getTextFromElement(StocksPageLocators.WHRow(i), ElementType.Xpath)
									.contains(Warehouse);
							if (flag) {
								String StockQty = webDB.getTextFromElement(StocksPageLocators.StockQtyRow(i),
										ElementType.Xpath);
								logs.logging("INFO", "Stock Quantity For Item " + Item + " WareHouse " + Warehouse
										+ " Is: " + StockQty);
								Map<String, String> map = VariableHandler.getStockQty();
								map.put(Item + Warehouse, StockQty);
								VariableHandler.setStockQty(map);
								flag = true;
								break;
							}
						}
//								}
					}
				}
//						}
			}
//				}
//			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Remove Filter Stock
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void removeFilterStock() {
		List<WebElement> removeFilter = WebDriverBase.getDriver()
				.findElements(By.xpath(StocksPageLocators.RemoveFilter));
		for (WebElement element : removeFilter) {
			if (element.isDisplayed()) {
				element.click();
				break;
			}
		}
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.isElementNotDisplayed(StocksPageLocators.RemoveFilter, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Verified Filter Removed Successfully");
		}
		assertTrue(flag);
	}

	/**
	 * This method Used to Verify Get Item Stock Cost
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 * @param Warehouse
	 */
	public void getItemStockCost(String Item, String Warehouse) {
		flag = webDB.waitForElement(CommonUiLocators.ItemLovButton, ElementType.Xpath);
		commonFunc.searchAndSelectValueInFilters(CommonUiLocators.ItemLovButton, Item);
		flag = webDB.waitForElement(CommonUiLocators.StocksCheckBox, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Stock Check Box Is Displayed");
			webDB.clickAnElement(CommonUiLocators.StocksCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(StocksPageLocators.GenerateButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(StocksPageLocators.GenerateButton, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
//				flag = webDB.waitForElement(StocksPageLocators.StockItem, ElementType.Xpath);
//				if (flag) {
//					commonFunc.checkRefreshCellsDisappear();
//					commonFunc.hardWait(2000);
//					logs.logging("INFO", "Stock Item Header Is Displayed");
//					webDB.clickAnElement(StocksPageLocators.StockItem, ElementType.Xpath);
//					flag = webDB.waitForElement(StocksPageLocators.StockSerachField, ElementType.Xpath);
//					if (flag) {
//						webDB.sendTextToAnElement(StocksPageLocators.StockSerachField, Item, ElementType.Xpath);
//						logs.logging("INFO", "Entered Item Value Is: " + Item);
//						webDB.pressEnterKey();
//						flag = webDB.waitForElement(StocksPageLocators.SelectItem(Item), ElementType.Xpath);
//						if (flag) {
//							webDB.clickAnElement(StocksPageLocators.SelectItem(Item), ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(StocksPageLocators.NoOfRow, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "No Of Item In Row Is Displayed");
					int totalvalue = webDB.getDriver().findElements(By.xpath(StocksPageLocators.NoOfRow)).size();
					for (int i = 2; i <= totalvalue + 1; i++) {
						flag = webDB.waitForElement(StocksPageLocators.CostItemRow(i), ElementType.Xpath);
						if (flag) {
							if (webDB.getTextFromElement(StocksPageLocators.CostItemRow(i), ElementType.Xpath)
									.equals(Item)) {
								if (webDB.getTextFromElement(StocksPageLocators.CostWHRow(i), ElementType.Xpath)
										.equals(Warehouse)) {
									String cost = webDB.getTextFromElement(StocksPageLocators.CostRow(i),
											ElementType.Xpath);
									logs.logging("INFO", "Stock Cost For Item " + Item + " In WareHouse " + Warehouse
											+ " Is: " + cost);
									Map<String, String> map = VariableHandler.getStockCost();
									map.put(Item, cost);
									VariableHandler.setStockCost(map);
									flag = true;
									break;
								}
							}
						}

//							}
//						}
					}
//					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method Used to Verify Get Item After Stock Value
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 * @param Warehouse
	 */
	public void getItemAfterStockValue(String Item, String Warehouse) {
		flag = webDB.waitForElement(CommonUiLocators.ItemLovButton, ElementType.Xpath);
		commonFunc.searchAndSelectValueInFilters(CommonUiLocators.ItemLovButton, Item);
		flag = webDB.waitForElement(CommonUiLocators.StocksCheckBox, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Stock Check Box Is Displayed");
			webDB.clickAnElement(StocksPageLocators.StockCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(StocksPageLocators.GenerateButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(StocksPageLocators.GenerateButton, ElementType.Xpath);
//				flag = webDB.waitForElement(StocksPageLocators.StockItem, ElementType.Xpath);
//				if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.checkRefreshCellsDisappear();
//					commonFunc.hardWait(2000);
//					logs.logging("INFO", "Stock Item Header Is Displayed");
//					webDB.clickAnElement(StocksPageLocators.StockItem, ElementType.Xpath);
//					flag = webDB.waitForElement(StocksPageLocators.StockSerachField, ElementType.Xpath);
//					if (flag) {
//						webDB.sendTextToAnElement(StocksPageLocators.StockSerachField, Item, ElementType.Xpath);
//						logs.logging("INFO", "Entered Item Value Is: " + Item);
//						webDB.pressEnterKey();
//						flag = webDB.waitForElement(StocksPageLocators.SelectItem(Item), ElementType.Xpath);
//						if (flag) {
//							webDB.clickAnElement(StocksPageLocators.SelectItem(Item), ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(StocksPageLocators.NoOfRow, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "No Of Item In Row Is Displayed");
					int totalvalue = webDB.getDriver().findElements(By.xpath(StocksPageLocators.NoOfRow)).size();
					for (int i = 2; i <= totalvalue + 1; i++) {
						flag = webDB.waitForElement(StocksPageLocators.ItemRow(i), ElementType.Xpath);

						if (flag) {
							flag = webDB.getTextFromElement(StocksPageLocators.WHRow(i), ElementType.Xpath)
									.equals(Warehouse);
							if (flag) {
								String StockQty = webDB.getTextFromElement(StocksPageLocators.StockQtyRow(i),
										ElementType.Xpath);
								logs.logging("INFO", "Stock Quantity For Item " + Item + " In WareHouse " + Warehouse
										+ "Is: " + StockQty);
								Map<String, String> map = VariableHandler.getAfterStockQty();
								map.put(Item + Warehouse, StockQty);
								VariableHandler.setAfterStockQty(map);
								flag = true;
								break;
							}
						}
//								}
					}
				}
			}
		}
//			}
//	}

		assertTrue(flag);
	}

	/**
	 * This method Used to Verify Get Item After Stock Cost
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 * @param Warehouse
	 */
	public void getItemAfterStockCost(String Item, String Warehouse) {
		flag = webDB.waitForElement(CommonUiLocators.ItemLovButton, ElementType.Xpath);
		commonFunc.searchAndSelectValueInFilters(CommonUiLocators.ItemLovButton, Item);
		flag = webDB.waitForElement(CommonUiLocators.StocksCheckBox, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Stock Check Box Is Displayed");
			webDB.clickAnElement(CommonUiLocators.StocksCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(StocksPageLocators.GenerateButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(StocksPageLocators.GenerateButton, ElementType.Xpath);
//				commonFunc.checkRefreshCellsDisappear();
//				flag = webDB.waitForElement(StocksPageLocators.StockItem, ElementType.Xpath);
//				if (flag) {
//					commonFunc.checkRefreshCellsDisappear();
//					commonFunc.checkRefreshCellsDisappear();
//					commonFunc.checkRefreshCellsDisappear();
//					commonFunc.hardWait(2000);
//					logs.logging("INFO", "Stock Item Header Is Displayed");
//					webDB.clickAnElement(StocksPageLocators.StockItem, ElementType.Xpath);
//					flag = webDB.waitForElement(StocksPageLocators.StockSerachField, ElementType.Xpath);
//					if (flag) {
//						webDB.sendTextToAnElement(StocksPageLocators.StockSerachField, Item, ElementType.Xpath);
//						logs.logging("INFO", "Entered Item Value Is: " + Item);
//
////						flag = webDB.waitForElement(StocksPageLocators.SelectItem(Item), ElementType.Xpath);
////						if (flag) {
////							webDB.clickAnElement(StocksPageLocators.SelectItem(Item), ElementType.Xpath);
//						webDB.pressEnterKey();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(StocksPageLocators.NoOfRow, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "No Of Item In Row Is Displayed");
					int totalvalue = webDB.getDriver().findElements(By.xpath(StocksPageLocators.NoOfRow)).size();
					commonFunc.hardWait(4000);
					for (int i = 2; i <= totalvalue + 1; i++) {
						flag = webDB.waitForElement(StocksPageLocators.CostItemRow(i), ElementType.Xpath);
						if (flag) {
							if (webDB.getTextFromElement(StocksPageLocators.CostItemRow(i), ElementType.Xpath)
									.equals(Item)) {
								if (webDB.getTextFromElement(StocksPageLocators.CostWHRow(i), ElementType.Xpath)
										.equals(Warehouse)) {
									String cost = webDB.getTextFromElement(StocksPageLocators.CostRow(i),
											ElementType.Xpath);
									logs.logging("INFO", "Stock Cost For Item " + Item + " In WareHouse " + Warehouse
											+ " Is: " + cost);
									Map<String, String> map = VariableHandler.getAfterStockCost();
									map.put(Item, cost);
									VariableHandler.setAfterStockCost(map);
									flag = true;
									break;
								}

							}
						}
//								}
					}
				}
//			}
//		}
			}

		}

		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Validate Stock Cost
	 * 
	 * @author Deepan-GWL
	 */
	public void validateStockCount(String Sheet) {
		ExcelUtils excelUtils = new ExcelUtils();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "ItemDetails.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, Sheet);
		for (int j = 1; j < itemmultiple.length; j++) {
			flag = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(
					new BigDecimal(VariableHandler.getStockQty().get(itemmultiple[j][0] + itemmultiple[j][1]))
							.subtract(new BigDecimal(
									VariableHandler.getAfterStockQty().get(itemmultiple[j][0] + itemmultiple[j][1]))))
					.equals(purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(new BigDecimal("2")));
		}
		assertTrue(flag);
	}
}
