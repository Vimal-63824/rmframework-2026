package commonfunctions.productionplanning;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 20, 2025
 */
import static org.testng.Assert.assertTrue;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.productionplanning.ProductionOrderLocators;
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
			flag = webDB.waitForElement(ProductionOrderLocators.Find_TableData_ProdNo(MRPNo), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button Clicked in Production Order Find Page");
				String ProductionNo = webDB.getTextFromElement(ProductionOrderLocators.Find_TableData_ProdNo(MRPNo),
						ElementType.Xpath);
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

}
