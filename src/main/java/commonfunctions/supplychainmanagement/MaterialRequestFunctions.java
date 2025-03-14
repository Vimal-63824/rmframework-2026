package commonfunctions.supplychainmanagement;

import static org.testng.Assert.assertTrue;

/**
 * @author Sakthi-GWL
 * @company Galaxy Weblinks February 28, 2025
 */
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.productionplanning.ProductionOrderLocators;
import commonlocators.supplychainmanagement.MaterialRequestLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class MaterialRequestFunctions {
	private static boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();

	/**
	 * This method used to store Material Request Number And Validate Status With
	 * Production Order Number
	 * 
	 * @author Sakthi-GWL
	 * @param ProdNo
	 * @param ExpectedStatus
	 */
	public void storeMRNoAndCheckStatusOfMRWithProdNo(String ProdNo, String ExpectedStatus) {
		commonFunc.searchAndSelectValueInFilters(MaterialRequestLocators.FindProdNoLovBtn, ProdNo);
		flag = webDB.waitForElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(MaterialRequestLocators.Find_Status_TableData(ProdNo), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button clicked and MRP No: " + ProdNo + " Displayed");
				String Status = webDB.getTextFromElement(MaterialRequestLocators.Find_Status_TableData(ProdNo),
						ElementType.Xpath);
				logs.logging("INFO", "Production Number: '" + ProdNo + "' searched and displaying status: " + Status);
				flag = Status.equalsIgnoreCase(ExpectedStatus);
				if (flag) {
					logs.logging("INFO", "Search Button clicked and MRP No: " + ProdNo + " Displayed");
					String MRNo = webDB.getTextFromElement(MaterialRequestLocators.Find_MRNo_TableData(ProdNo),
							ElementType.Xpath);
					VariableHandler.setMaterialRequestNo(MRNo);
					logs.logging("INFO", "Material Number: '" + VariableHandler.getMaterialRequestNo() + "'");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method used to validate Item Warehouse Requester Name and Request
	 * Quantity by search with Material Request Number
	 * 
	 * @author Sakthi-GWL
	 * @param MRNo
	 * @param Item
	 * @param Warehouse
	 * @param ReqName
	 * @param ReqQuantity
	 */
	public void validateItemWHReqNameQuantityWithProdNo(String MRNo, String Item, String Warehouse, String ReqName) {
		commonFunc.searchAndSelectValueInFilters(MaterialRequestLocators.FindProdNoLovBtn, MRNo);
		flag = webDB.waitForElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(MaterialRequestLocators.FindSearchBtn, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(MaterialRequestLocators.Find_Item_TableData(MRNo), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button clicked and MR No: " + MRNo + " Displayed");
				String DisplItem = webDB.getTextFromElement(MaterialRequestLocators.Find_Item_TableData(MRNo),
						ElementType.Xpath);
				logs.logging("INFO", "Displayed Item: " + DisplItem);
				flag = Item.equalsIgnoreCase(DisplItem);
				if (flag) {
					logs.logging("INFO", "Item Validated Successfully In Material Request");
					String DisplWH = webDB.getTextFromElement(MaterialRequestLocators.Find_Warehouse_TableData(MRNo),
							ElementType.Xpath);
					logs.logging("INFO", "Displayed WareHouse: " + DisplWH);
					flag = Warehouse.equalsIgnoreCase(DisplWH);
					if (flag) {
						logs.logging("INFO", "Warehouse Validated Successfully In Material Request");
						String DisplReqName = webDB.getTextFromElement(
								MaterialRequestLocators.Find_RequesterName_TableData(MRNo), ElementType.Xpath);
						logs.logging("INFO", "Displayed Requester Name: " + DisplReqName);
						flag = ReqName.equalsIgnoreCase(DisplReqName);
						if (flag) {
							logs.logging("INFO", "Requester Name Validated Successfully In Material Request");
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

}
