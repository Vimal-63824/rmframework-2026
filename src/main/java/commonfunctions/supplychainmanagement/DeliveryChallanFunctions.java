package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks March 13, 2025
 */
import static org.testng.Assert.assertTrue;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.supplychainmanagement.DeliveryChallanLocators;
import commonlocators.supplychainmanagement.OpenPoRcLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class DeliveryChallanFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();

	/**
	 * This Method Is Used To Verify Status Of
	 * 
	 * @param DocNo
	 * @param ExpectedStatus
	 */
	public void verifyStatusOfDeliveryChallan(String DocNo, String ExpectedStatus) {
		commonFunc.clearFiltersInFindPage();
		commonFunc.searchAndSelectValueInFilters(DeliveryChallanLocators.DCNumberLovButton, DocNo);
		flag = webDB.waitForElement(OpenPoRcLocators.LineCheckBox, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Line CheckBox Is Displayed");
			webDB.clickAnElement(OpenPoRcLocators.LineCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(DeliveryChallanLocators.SearchButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "MIV Search Button IS Displayed");
				webDB.clickAnElement(DeliveryChallanLocators.SearchButton, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(DeliveryChallanLocators.StatusDcNumber(DocNo), ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Search Button clicked and DC No: " + DocNo + " Displayed");
					String Status = webDB.getTextFromElement(DeliveryChallanLocators.StatusDcNumber(DocNo),
							ElementType.Xpath);
					logs.logging("INFO", "DC Number: '" + DocNo + "' searched and displaying status: " + Status);
					flag = Status.equalsIgnoreCase(ExpectedStatus);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Delivery Challan
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDeliveryChallan() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateToGateEntryDeliveryChallan();
		verifyTableData(webDB.getDataFromProperties("MRTMRVSheet"));
		verifyStatusOfDeliveryChallan(VariableHandler.getDCNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
	}

	/**
	 * This Method Is Used To Verify Table Data
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyTableData(String Sheet) {
		commonFunc.searchAndSelectValueInFilters(DeliveryChallanLocators.DCNumberLovButton,
				VariableHandler.getDCNumber());
		flag = webDB.waitForElement(OpenPoRcLocators.LineCheckBox, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Line CheckBox Is Displayed");
			webDB.clickAnElement(OpenPoRcLocators.LineCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(DeliveryChallanLocators.SearchButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "MIV Search Button IS Displayed");
				webDB.clickAnElement(DeliveryChallanLocators.SearchButton, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(DeliveryChallanLocators.DCNumber(VariableHandler.getDCNumber()),
						ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(DeliveryChallanLocators.DCNumber(VariableHandler.getDCNumber()),
							ElementType.Xpath);
					BigDecimal IndividualTotal;
					BigDecimal FinalTotal = new BigDecimal("0");
					ExcelUtils excelUtils = new ExcelUtils();
					String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
					String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
							+ "ItemDetails.xlsx";
					itemmultiple = excelUtils.readAllDataInExcel(Filepath, Sheet);
					for (int i = 1; i < itemmultiple.length; i++) {
						List<WebElement> TableList = webDB.getDriver()
								.findElements(By.xpath(DeliveryChallanLocators.ListItem));
						if (flag) {
							for (int j = 1; j <= TableList.size(); j++) {
								String ItemText = webDB.getTextFromElement(DeliveryChallanLocators.ItemTable(j),
										ElementType.Xpath);
								String ItemCost = webDB.getTextFromElement(DeliveryChallanLocators.ItemCostTable(j),
										ElementType.Xpath);
								if (ItemText.equals(itemmultiple[i][0])) {
									if (ItemCost.equals(VariableHandler.getStockCost().get(itemmultiple[i][0]))) {
										logs.logging("INFO", "Verified Item " + ItemText + "  With Item Cost "
												+ ItemCost + " With Table Data");
										IndividualTotal = new BigDecimal(ItemCost).multiply(new BigDecimal("2"));
										FinalTotal = FinalTotal.add(IndividualTotal);
										flag = true;
										break;
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
//				if (flag) {
//					flag = webDB.waitForElement(DeliveryChallanLocators.POTotal, ElementType.Xpath);
//					if (flag) {
//						String text = webDB.getTextFromElement(DeliveryChallanLocators.POTotal, ElementType.Xpath);
//						flag = text.equals(FinalTotal.toString());
//						System.out.println(FinalTotal.toString() + "To String");
//					}
//					assertTrue(flag);
//				} else {
//					flag = false;
//					assertTrue(flag);
//				}
//				assertTrue(flag);
				flag = webDB.waitForElement(DeliveryChallanLocators.BackButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(DeliveryChallanLocators.BackButton, ElementType.Xpath);
					flag = webDB.waitForElement(DeliveryChallanLocators.SearchButton, ElementType.Xpath);
				}
			}
		}
		assertTrue(flag);
	}

}
