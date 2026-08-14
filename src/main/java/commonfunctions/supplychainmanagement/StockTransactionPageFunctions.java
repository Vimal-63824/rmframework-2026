package commonfunctions.supplychainmanagement;

/**
 * @author Sakthi-GWL, Deepan-GWL
 * @company Galaxy Weblinks March 04, 2025
 */
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.supplychainmanagement.StockTransactionPageLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class StockTransactionPageFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();

	/**
	 * This Method Is Used To Verify MRV Stock Transaction
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyMRVStockTransaction(String Warehouse) {
		flag = webDB.waitForElement(StockTransactionPageLocators.VoucherNumber, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Voucher Number Is Displayed");
			webDB.sendTextToAnElement(StockTransactionPageLocators.VoucherNumber, VariableHandler.getMIVNumber(),
					ElementType.Xpath);
			flag = webDB.waitForElement(StockTransactionPageLocators.GenerateButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Generate Button Is Displayed");
				webDB.clickAnElement(StockTransactionPageLocators.GenerateButton, ElementType.Xpath);
				List<WebElement> Listelements = webDB.getDriver()
						.findElements(By.xpath(StockTransactionPageLocators.ListOfElement));
				ExcelUtils excelUtils = new ExcelUtils();
				String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
				String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
						+ "ItemDetails.xlsx";
				itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("MRTMRVSheet"));
				for (int i = 1; i <= Listelements.size(); i++) {
					if (flag) {
						for (int j = 1; j < itemmultiple.length; j++) {
							if (StockTransactionPageLocators.VoucherNumber(i).equals(VariableHandler.getMIVNumber())) {
								if (StockTransactionPageLocators.ItemName(i).equals(itemmultiple[j][0])) {
									if (StockTransactionPageLocators.Warehouse(i).equals(itemmultiple[j][1])) {
										logs.logging("INFO",
												"For MRV Number " + StockTransactionPageLocators.VoucherNumber(i)
														+ " Item " + StockTransactionPageLocators.ItemName(i)
														+ " WareHouse " + StockTransactionPageLocators.Warehouse(i)
														+ " Quantity Is: " + StockTransactionPageLocators.Quantity(i));
										flag = StockTransactionPageLocators.Quantity(i)
												.equals(commonFunc.getDataFromTestDataSheet("MinusTwo"));
										break;
									} else if (StockTransactionPageLocators.Warehouse(i).equals(Warehouse)) {
										logs.logging("INFO",
												"For MRV Number " + StockTransactionPageLocators.VoucherNumber(i)
														+ " Item " + StockTransactionPageLocators.ItemName(i)
														+ " WareHouse " + StockTransactionPageLocators.Warehouse(i)
														+ " Quantity Is: " + StockTransactionPageLocators.Quantity(i));
										flag = StockTransactionPageLocators.Quantity(i)
												.equals(commonFunc.getDataFromTestDataSheet("Two"));
										break;
									}
								} else {
									flag = false;
								}
							} else {
								flag = false;
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify MRV Stock Transaction
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyStockTransactionWithDocNumber(String Warehouse, String DocNo) {
		flag = webDB.waitForElement(StockTransactionPageLocators.VoucherNumber, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Voucher Number Is Displayed");
			commonFunc.searchAndSelectValueInFilters(StockTransactionPageLocators.VoucherNumber, DocNo);
			flag = webDB.waitForElement(StockTransactionPageLocators.GenerateButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Generate Button Is Displayed");
				webDB.clickAnElement(StockTransactionPageLocators.GenerateButton, ElementType.Xpath);
				List<WebElement> Listelements = webDB.getDriver()
						.findElements(By.xpath(StockTransactionPageLocators.ListOfElement));
				ExcelUtils excelUtils = new ExcelUtils();
				String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
				String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
						+ "ItemDetails.xlsx";
				itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("MRTMRVSheet"));
				for (int i = 1; i <= Listelements.size(); i++) {
					if (flag) {
						for (int j = 1; j < itemmultiple.length; j++) {
							if (StockTransactionPageLocators.VoucherNumber(i).equals(VariableHandler.getMrvNumber())) {
								if (StockTransactionPageLocators.ItemName(i).equals(itemmultiple[j][0])) {
									if (StockTransactionPageLocators.Warehouse(i).equals(itemmultiple[j][1])) {
										logs.logging("INFO",
												"For MRV Number " + StockTransactionPageLocators.VoucherNumber(i)
														+ " Item " + StockTransactionPageLocators.ItemName(i)
														+ " WareHouse " + StockTransactionPageLocators.Warehouse(i)
														+ " Quantity Is: " + StockTransactionPageLocators.Quantity(i));
										flag = StockTransactionPageLocators.Quantity(i)
												.equals(commonFunc.getDataFromTestDataSheet("MinusTwo"));
										break;
									} else if (StockTransactionPageLocators.Warehouse(i).equals(Warehouse)) {
										logs.logging("INFO",
												"For MRV Number " + StockTransactionPageLocators.VoucherNumber(i)
														+ " Item " + StockTransactionPageLocators.ItemName(i)
														+ " WareHouse " + StockTransactionPageLocators.Warehouse(i)
														+ " Quantity Is: " + StockTransactionPageLocators.Quantity(i));
										flag = StockTransactionPageLocators.Quantity(i)
												.equals(commonFunc.getDataFromTestDataSheet("Two"));
										break;
									}
								} else {
									flag = false;
								}
							} else {
								flag = false;
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Validate Stock Transaction
	 * 
	 * @author Deepan-GWL
	 */
	public void validateStockTransaction() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToInventoryStocksTransactionTab();
		verifyMRVStockTransaction(commonFunc.getDataFromTestDataSheet("FGWareHouse"));
		commonFunc.logoutUser();
	}

	/**
	 * This Method Is Used To Verify MRV Stock Transaction
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDocumentNoStockTransaction(String Warehouse, String DocNo) {
		flag = webDB.waitForElement(StockTransactionPageLocators.VoucherNumber, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Voucher Number Is Displayed");
			commonFunc.searchAndSelectValueInFilters(StockTransactionPageLocators.VoucherNumber, DocNo);
			flag = webDB.waitForElement(StockTransactionPageLocators.GenerateButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Generate Button Is Displayed");
				webDB.clickAnElement(StockTransactionPageLocators.GenerateButton, ElementType.Xpath);
				List<WebElement> Listelements = webDB.getDriver()
						.findElements(By.xpath(StockTransactionPageLocators.ListOfElement));
				ExcelUtils excelUtils = new ExcelUtils();
				String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
				String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
						+ "ItemDetails.xlsx";
				itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("MRTMRVSheet"));
				for (int i = 1; i <= Listelements.size(); i++) {
					if (flag) {
						for (int j = 1; j < itemmultiple.length; j++) {
							if (StockTransactionPageLocators.VoucherNumber(i).equals(DocNo)) {
								if (StockTransactionPageLocators.ItemName(i).equals(itemmultiple[j][0])) {
									if (StockTransactionPageLocators.Warehouse(i).equals(itemmultiple[j][1])) {
										logs.logging("INFO",
												"For MRV Number " + StockTransactionPageLocators.VoucherNumber(i)
														+ " Item " + StockTransactionPageLocators.ItemName(i)
														+ " WareHouse " + StockTransactionPageLocators.Warehouse(i)
														+ " Quantity Is: " + StockTransactionPageLocators.Quantity(i));
										flag = StockTransactionPageLocators.Quantity(i)
												.equals(commonFunc.getDataFromTestDataSheet("MinusTwo"));
										break;
									} else if (StockTransactionPageLocators.Warehouse(i).equals(Warehouse)) {
										logs.logging("INFO",
												"For MRV Number " + StockTransactionPageLocators.VoucherNumber(i)
														+ " Item " + StockTransactionPageLocators.ItemName(i)
														+ " WareHouse " + StockTransactionPageLocators.Warehouse(i)
														+ " Quantity Is: " + StockTransactionPageLocators.Quantity(i));
										flag = StockTransactionPageLocators.Quantity(i)
												.equals(commonFunc.getDataFromTestDataSheet("Two"));
										break;
									}
								} else {
									flag = false;
								}
							} else {
								flag = false;
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Stock SFG Transaction
	 * 
	 * @author Deepan-GWL
	 * @param Number
	 */
	public void verifyStockSFGTransaction(String Number) {
		flag = webDB.waitForElement(StockTransactionPageLocators.SFGTransaction, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(StockTransactionPageLocators.SFGTransaction, ElementType.Xpath);
			flag = webDB.waitForElement(StockTransactionPageLocators.SFGGenerateButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(StockTransactionPageLocators.SFGGenerateButton, ElementType.Xpath);
				flag = webDB.waitForElement(StockTransactionPageLocators.SearchField, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(StockTransactionPageLocators.SearchField, Number, ElementType.Xpath);
					flag = webDB.waitForElement(StockTransactionPageLocators.GoButton, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(StockTransactionPageLocators.GoButton, ElementType.Xpath);
						flag = webDB.waitForElement(StockTransactionPageLocators.RemoveFilter, ElementType.Xpath);
						if (flag) {
							List<WebElement> element = webDB.getDriver()
									.findElements(By.xpath(StockTransactionPageLocators.SFGWareHouse));
							List<WebElement> UnitCost = webDB.getDriver()
									.findElements(By.xpath(StockTransactionPageLocators.SFGUnitCost));
							flag = false;
							for (int i = 0; i < element.size(); i++) {
								if (element.get(i).getText()
										.equals(commonFunc.getDataFromTestDataSheet("WIPU100_WH"))) {
									if (UnitCost.get(i).getText().equals(VariableHandler.getCompletionFgUnitCost())) {
										flag = true;
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

}
