package commonfunctions.supplychainmanagement;

/**
 * @author Vimal - RMITS
 * @company RoadMap IT Solutions Oct 16, 2025
 */
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.VariableHandler;
import commonfunctions.productionplanning.ProductionAndShopFloorSetupFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.SalesInvoiceLocators;
import commonlocators.supplychainmanagement.SubcontractOrderLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class SubcontractSetUpFunctions {

	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	private ApprovalPageFunctions approveFunc = new ApprovalPageFunctions();
	public ProductionAndShopFloorSetupFunctions prodsetupFunc = new ProductionAndShopFloorSetupFunctions();
	SalesOrderFunctions salesOrderFunctions = new SalesOrderFunctions();
	DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();
	private static ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();

	/**
	 * This Method Used For Clicking Open SCO Find Screen Add Icon
	 * 
	 * @author Vimal - RMITS
	 */
	public void clickingOpenSCO_FindAddIcon() {
		flag = webDB.waitForElement(SubcontractOrderLocators.Open_SCO_Find_AddIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SubcontractOrderLocators.Open_SCO_Find_AddIcon, ElementType.Xpath);
			flag = webDB.waitForElement(SubcontractOrderLocators.OP_Iframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Open SCO Price Add Icon Clicked Sucessfully");
				webDB.switchToFrame(SubcontractOrderLocators.OP_Iframe, ElementType.Xpath);
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(SubcontractOrderLocators.OpenSCO_LocID_Input, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Open SCO Price Frame Find Sucessfully");
					webDB.switchToDefaultContent();
					commonFunc.hardWait(1000);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For check Open SCO Price Header Details DataBase Values With
	 * UIValues SIP
	 * 
	 * @author Vimal - RMITS
	 */
	public void checkOpenSCOPriceHeaderDetailsDataBaseValuesWithUIValuesSIP() {
		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SubcontractOrderLocators.SCO_LocID, ElementType.Xpath),
				dbCommonFunctions.OSPHeaderDetails(VariableHandler.getoscdocno(),
						commonFunc.getDataFromTestDataSheet("OSPDBLOCATIONCOLUMN")));
		logs.logging("INFO", "Header Detail LocationID Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getDomPropertyFromElement(SubcontractOrderLocators.SCO_Pfx, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value")),
				dbCommonFunctions.OSPHeaderDetails(VariableHandler.getoscdocno(),
						commonFunc.getDataFromTestDataSheet("OSPDBPFXCOLUMN")));
		logs.logging("INFO", "Header Detail Prefix Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SubcontractOrderLocators.SCO_Type, ElementType.Xpath),
				dbCommonFunctions.OSPHeaderDetails(VariableHandler.getoscdocno(),
						commonFunc.getDataFromTestDataSheet("OSPDBTYPECOLUMN")));
		logs.logging("INFO", "Header Detail LevelType Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SubcontractOrderLocators.SCO_Item, ElementType.Xpath),
				dbCommonFunctions.OSPHeaderDetails(VariableHandler.getoscdocno(),
						commonFunc.getDataFromTestDataSheet("OSPDBITEMCOLUMN")));
		logs.logging("INFO", "Header Detail Item Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SubcontractOrderLocators.SCO_SupplierID, ElementType.Xpath),
				dbCommonFunctions.OSPHeaderDetails(VariableHandler.getoscdocno(),
						commonFunc.getDataFromTestDataSheet("OSPDBSUPPLIERIDCOLUMN")));
		logs.logging("INFO", "Header Detail SupplierID Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SubcontractOrderLocators.SCO_Status, ElementType.Xpath),
				dbCommonFunctions.OSPHeaderDetails(VariableHandler.getoscdocno(),
						commonFunc.getDataFromTestDataSheet("OSPDBSTATUSCOLUMN")));
		logs.logging("INFO", "Header Detail UnitID Matched With DataBase Values");
	}

	/**
	 * This Method Used For check Open SCO Price Header Details DataBase Values With
	 * UIValues SP
	 * 
	 * @author Vimal - RMITS
	 */
	public void checkOpenSCOPriceHeaderDetailsDataBaseValuesWithUIValuesSP() {
		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SubcontractOrderLocators.SCO_LocID, ElementType.Xpath),
				dbCommonFunctions.OSPHeaderDetails(VariableHandler.getoscdocno(),
						commonFunc.getDataFromTestDataSheet("OSPDBLOCATIONCOLUMN")));
		logs.logging("INFO", "Header Detail LocationID Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getDomPropertyFromElement(SubcontractOrderLocators.SCO_Pfx, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value")),
				dbCommonFunctions.OSPHeaderDetails(VariableHandler.getoscdocno(),
						commonFunc.getDataFromTestDataSheet("OSPDBPFXCOLUMN")));
		logs.logging("INFO", "Header Detail Prefix Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SubcontractOrderLocators.SCO_Type, ElementType.Xpath),
				dbCommonFunctions.OSPHeaderDetails(VariableHandler.getoscdocno(),
						commonFunc.getDataFromTestDataSheet("OSPDBTYPECOLUMN")));
		logs.logging("INFO", "Header Detail LevelType Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SubcontractOrderLocators.SCO_SupplierID, ElementType.Xpath),
				dbCommonFunctions.OSPHeaderDetails(VariableHandler.getoscdocno(),
						commonFunc.getDataFromTestDataSheet("OSPDBSUPPLIERIDCOLUMN")));
		logs.logging("INFO", "Header Detail SupplierID Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SubcontractOrderLocators.SCO_Status, ElementType.Xpath),
				dbCommonFunctions.OSPHeaderDetails(VariableHandler.getoscdocno(),
						commonFunc.getDataFromTestDataSheet("OSPDBSTATUSCOLUMN")));
		logs.logging("INFO", "Header Detail UnitID Matched With DataBase Values");
	}

	/**
	 * This Method Used For check Open SCO Price Header Details DataBase Values With
	 * UIValues IP
	 * 
	 * @author Vimal - RMITS
	 */
	public void checkOpenSCOPriceHeaderDetailsDataBaseValuesWithUIValuesIP() {
		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SubcontractOrderLocators.SCO_LocID, ElementType.Xpath),
				dbCommonFunctions.OSPHeaderDetails(VariableHandler.getoscdocno(),
						commonFunc.getDataFromTestDataSheet("OSPDBLOCATIONCOLUMN")));
		logs.logging("INFO", "Header Detail LocationID Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getDomPropertyFromElement(SubcontractOrderLocators.SCO_Pfx, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value")),
				dbCommonFunctions.OSPHeaderDetails(VariableHandler.getoscdocno(),
						commonFunc.getDataFromTestDataSheet("OSPDBPFXCOLUMN")));
		logs.logging("INFO", "Header Detail Prefix Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SubcontractOrderLocators.SCO_Type, ElementType.Xpath),
				dbCommonFunctions.OSPHeaderDetails(VariableHandler.getoscdocno(),
						commonFunc.getDataFromTestDataSheet("OSPDBTYPECOLUMN")));
		logs.logging("INFO", "Header Detail LevelType Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SubcontractOrderLocators.SCO_Item, ElementType.Xpath),
				dbCommonFunctions.OSPHeaderDetails(VariableHandler.getoscdocno(),
						commonFunc.getDataFromTestDataSheet("OSPDBITEMCOLUMN")));
		logs.logging("INFO", "Header Detail Item Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SubcontractOrderLocators.SCO_Status, ElementType.Xpath),
				dbCommonFunctions.OSPHeaderDetails(VariableHandler.getoscdocno(),
						commonFunc.getDataFromTestDataSheet("OSPDBSTATUSCOLUMN")));
		logs.logging("INFO", "Header Detail UnitID Matched With DataBase Values");
		commonFunc.hardWait(2000);
	}

	/**
	 * This Method Used For check Open SCO Price Header Details DataBase Values With
	 * UIValues Process
	 * 
	 * @author Vimal - RMITS
	 */
	public void checkOpenSCOPriceHeaderDetailsDataBaseValuesWithUIValuesProcess() {
		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SubcontractOrderLocators.SCO_LocID, ElementType.Xpath),
				dbCommonFunctions.OSPHeaderDetails(VariableHandler.getoscdocno(),
						commonFunc.getDataFromTestDataSheet("OSPDBLOCATIONCOLUMN")));
		logs.logging("INFO", "Header Detail LocationID Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getDomPropertyFromElement(SubcontractOrderLocators.SCO_Pfx, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value")),
				dbCommonFunctions.OSPHeaderDetails(VariableHandler.getoscdocno(),
						commonFunc.getDataFromTestDataSheet("OSPDBPFXCOLUMN")));
		logs.logging("INFO", "Header Detail Prefix Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SubcontractOrderLocators.SCO_Type, ElementType.Xpath),
				dbCommonFunctions.OSPHeaderDetails(VariableHandler.getoscdocno(),
						commonFunc.getDataFromTestDataSheet("OSPDBTYPECOLUMN")));
		logs.logging("INFO", "Header Detail LevelType Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SubcontractOrderLocators.SCO_Status, ElementType.Xpath),
				dbCommonFunctions.OSPHeaderDetails(VariableHandler.getoscdocno(),
						commonFunc.getDataFromTestDataSheet("OSPDBSTATUSCOLUMN")));
		logs.logging("INFO", "Header Detail UnitID Matched With DataBase Values");
		commonFunc.hardWait(2000);
	}

	/**
	 * This Method Used For check Open SCO Price Line Details DataBase Values With
	 * UIValues
	 * 
	 * @author Vimal - RMITS
	 */
	public void checkOpenSCOPriceLineDetailsDataBaseValuesWithUIValues() {
		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SubcontractOrderLocators.OSP_ProcessField, ElementType.Xpath),
				dbCommonFunctions.OSPLineDetails(VariableHandler.getoscdocno(),
						commonFunc.getDataFromTestDataSheet("OSPDBPROCESSCOLUMN")));
		logs.logging("INFO", "Line Process Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SubcontractOrderLocators.OSP_UOMField, ElementType.Xpath),
				dbCommonFunctions.OSPLineDetails(VariableHandler.getoscdocno(),
						commonFunc.getDataFromTestDataSheet("OSPDBUOMCOLUMN")));
		logs.logging("INFO", "Line UOM Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SubcontractOrderLocators.OSP_BatchqtyField, ElementType.Xpath),
				dbCommonFunctions.OSPLineDetails(VariableHandler.getoscdocno(),
						commonFunc.getDataFromTestDataSheet("OSPDBBATCHQTYCOLUMN")));
		logs.logging("INFO", "Line Batch Qty Matched With DataBase Values");

		salesOrderFunctions.verifyAssertionBothValues(
				webDB.getTextFromElement(SubcontractOrderLocators.OSP_BatchCostField, ElementType.Xpath),
				dbCommonFunctions.OSPLineDetails(VariableHandler.getoscdocno(),
						commonFunc.getDataFromTestDataSheet("OSPDBBATCHCOSTCOLUMN")));
		logs.logging("INFO", "Line Batch Cost Matched With DataBase Values");
	}

	/**
	 * Verify Create Sales Invoice Adding Parameter Supplier + Item + Process
	 * 
	 * @author Vimal - RMITS
	 * @param LocationId
	 * @param SelectType
	 * @param OpenPriceItem
	 * @param OpenPriceSupplier
	 * @param OpenPriceTodateInput
	 */
	public void addingParametersinOpenSCO(String LocationId, String SelectTypeSIP, String OpenPriceItem,
			String OpenPriceSupplier, String OpenPriceTodateInput) {
		webDB.switchToFrame(SubcontractOrderLocators.OP_Iframe, ElementType.Xpath);
		commonFunc.hardWait(1000);
		flag = webDB.waitForElement(SubcontractOrderLocators.OpenSCO_LocID_Input, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.OpenSCO_LocID_Input, LocationId);
			webDB.switchToFrame(SubcontractOrderLocators.OP_Iframe, ElementType.Xpath);
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(SubcontractOrderLocators.Select_Type_field, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownByText(SubcontractOrderLocators.Select_Type_field, SelectTypeSIP,
						ElementType.Xpath);
				commonFunc.hardWait(1000);
				commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.OpenSCO_ItemID_Input, OpenPriceItem);
				webDB.switchToFrame(SubcontractOrderLocators.OP_Iframe, ElementType.Xpath);
				commonFunc.hardWait(1000);
				commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.OpenSCO_Supl_Input,
						OpenPriceSupplier);
				webDB.switchToFrame(SubcontractOrderLocators.OP_Iframe, ElementType.Xpath);
				commonFunc.hardWait(1000);
				webDB.clickAnElement(SubcontractOrderLocators.Open_SCO_FROMDATE_Field, ElementType.Xpath);
				commonFunc.hardWait(1000);
				webDB.switchToDefaultContent();
				webDB.clickAnElement(SubcontractOrderLocators.Open_SCO_FROMDATE_input, ElementType.Xpath);
				webDB.pressTabKey();
				webDB.switchToFrame(SubcontractOrderLocators.OP_Iframe, ElementType.Xpath);
				commonFunc.hardWait(1000);
				webDB.sendTextToAnElement(SubcontractOrderLocators.Open_SCO_ToDATE_input, OpenPriceTodateInput,
						ElementType.Xpath);
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(SubcontractOrderLocators.OpenScoCreate_TickIcon, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SubcontractOrderLocators.OpenScoCreate_TickIcon, ElementType.Xpath);
					webDB.switchToDefaultContent();
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(1000);
					if (webDB.verifyIsElementDisplayed(SubcontractOrderLocators.OpenSCO_NoteAlert_Close,
							ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(SubcontractOrderLocators.OpenSCO_NoteAlert_Close,
								ElementType.Xpath);
						commonFunc.hardWait(2000);
						String oscdocno = webDB.getTextFromElement(SubcontractOrderLocators.OpenPriceDocNO,
								ElementType.Xpath);
						logs.logging("INFO", "Get OSC DocNo Is: " + oscdocno);
						VariableHandler.setoscdocno(oscdocno);
						commonFunc.hardWait(1000);
						flag = webDB.waitForElement(SubcontractOrderLocators.OP_LineAdd_Icon, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Open SCO Price Header Created Sucessfully");
							checkOpenSCOPriceHeaderDetailsDataBaseValuesWithUIValuesSIP();
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Sales Invoice Adding Parameter Item + Process
	 * 
	 * @author Vimal - RMITS
	 * @param LocationId
	 * @param SelectType
	 * @param OpenPriceItem
	 * @param OpenPriceSupplier
	 * @param OpenPriceTodateInput
	 */
	public void addingParametersinOpenSCOForItem_Process(String LocationId, String SelectTypeIP, String OpenPriceItem,
			String OpenPriceSupplier, String OpenPriceTodateInput) {
		webDB.switchToFrame(SubcontractOrderLocators.OP_Iframe, ElementType.Xpath);
		commonFunc.hardWait(1000);
		flag = webDB.waitForElement(SubcontractOrderLocators.OpenSCO_LocID_Input, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.OpenSCO_LocID_Input, LocationId);
			webDB.switchToFrame(SubcontractOrderLocators.OP_Iframe, ElementType.Xpath);
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(SubcontractOrderLocators.Select_Type_field, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownByText(SubcontractOrderLocators.Select_Type_field, SelectTypeIP, ElementType.Xpath);
				commonFunc.hardWait(1000);
				commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.OpenSCO_ItemID_Input, OpenPriceItem);
				commonFunc.hardWait(1000);
//				commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.OpenSCO_Supl_Input,
//						OpenPriceSupplier);
//				commonFunc.hardWait(1000);
				webDB.switchToFrame(SubcontractOrderLocators.OP_Iframe, ElementType.Xpath);
				commonFunc.hardWait(1000);
				webDB.clickAnElement(SubcontractOrderLocators.Open_SCO_FROMDATE_Field, ElementType.Xpath);
				commonFunc.hardWait(1000);
				webDB.switchToDefaultContent();
				webDB.clickAnElement(SubcontractOrderLocators.Open_SCO_FROMDATE_input, ElementType.Xpath);
				webDB.pressTabKey();
				webDB.switchToFrame(SubcontractOrderLocators.OP_Iframe, ElementType.Xpath);
				commonFunc.hardWait(1000);
				webDB.sendTextToAnElement(SubcontractOrderLocators.Open_SCO_ToDATE_input, OpenPriceTodateInput,
						ElementType.Xpath);
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(SubcontractOrderLocators.OpenScoCreate_TickIcon, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SubcontractOrderLocators.OpenScoCreate_TickIcon, ElementType.Xpath);
					webDB.switchToDefaultContent();
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(1000);
					if (webDB.verifyIsElementDisplayed(SubcontractOrderLocators.OpenSCO_NoteAlert_Close,
							ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(SubcontractOrderLocators.OpenSCO_NoteAlert_Close,
								ElementType.Xpath);
						commonFunc.hardWait(1000);
						String oscdocno = webDB.getTextFromElement(SubcontractOrderLocators.OpenPriceDocNO,
								ElementType.Xpath);
						logs.logging("INFO", "Get OSC DocNo Is: " + oscdocno);
						VariableHandler.setoscdocno(oscdocno);
						commonFunc.hardWait(2000);
						flag = webDB.waitForElement(SubcontractOrderLocators.OP_LineAdd_Icon, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Open SCO Price Header Created Sucessfully");
							checkOpenSCOPriceHeaderDetailsDataBaseValuesWithUIValuesIP();
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Sales Invoice Adding Parameter Supplier + Process
	 * 
	 * @author Vimal - RMITS
	 * @param LocationId
	 * @param SelectTypeSP
	 * @param OpenPriceItem
	 * @param OpenPriceSupplier
	 * @param OpenPriceTodateInput
	 */
	public void addingParametersinOpenSCOForSupplier_Process(String LocationId, String SelectTypeSP,
			String OpenPriceItem, String OpenPriceSupplier, String OpenPriceTodateInput) {
		webDB.switchToFrame(SubcontractOrderLocators.OP_Iframe, ElementType.Xpath);
		commonFunc.hardWait(1000);
		flag = webDB.waitForElement(SubcontractOrderLocators.OpenSCO_LocID_Input, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.OpenSCO_LocID_Input, LocationId);
			webDB.switchToFrame(SubcontractOrderLocators.OP_Iframe, ElementType.Xpath);
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(SubcontractOrderLocators.Select_Type_field, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownByText(SubcontractOrderLocators.Select_Type_field, SelectTypeSP, ElementType.Xpath);
				commonFunc.hardWait(1000);
				// commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.OpenSCO_ItemID_Input,
				// OpenPriceItem);
				commonFunc.hardWait(1000);
				commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.OpenSCO_Supl_Input,
						OpenPriceSupplier);
				webDB.switchToFrame(SubcontractOrderLocators.OP_Iframe, ElementType.Xpath);
				commonFunc.hardWait(1000);
				webDB.clickAnElement(SubcontractOrderLocators.Open_SCO_FROMDATE_Field, ElementType.Xpath);
				commonFunc.hardWait(1000);
				webDB.switchToDefaultContent();
				webDB.clickAnElement(SubcontractOrderLocators.Open_SCO_FROMDATE_input, ElementType.Xpath);
				webDB.pressTabKey();
				webDB.switchToFrame(SubcontractOrderLocators.OP_Iframe, ElementType.Xpath);
				commonFunc.hardWait(1000);
				webDB.sendTextToAnElement(SubcontractOrderLocators.Open_SCO_ToDATE_input, OpenPriceTodateInput,
						ElementType.Xpath);
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(SubcontractOrderLocators.OpenScoCreate_TickIcon, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SubcontractOrderLocators.OpenScoCreate_TickIcon, ElementType.Xpath);
					webDB.switchToDefaultContent();
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(1000);
					if (webDB.verifyIsElementDisplayed(SubcontractOrderLocators.OpenSCO_NoteAlert_Close,
							ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(SubcontractOrderLocators.OpenSCO_NoteAlert_Close,
								ElementType.Xpath);
						commonFunc.hardWait(2000);
						String oscdocno = webDB.getTextFromElement(SubcontractOrderLocators.OpenPriceDocNO,
								ElementType.Xpath);
						logs.logging("INFO", "Get OSC DocNo Is: " + oscdocno);
						VariableHandler.setoscdocno(oscdocno);
						commonFunc.hardWait(1000);
						flag = webDB.waitForElement(SubcontractOrderLocators.OP_LineAdd_Icon, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Open SCO Price Header Created Sucessfully");
							checkOpenSCOPriceHeaderDetailsDataBaseValuesWithUIValuesSP();
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Sales Invoice Adding Parameter Process
	 * 
	 * @author Vimal - RMITS
	 * @param LocationId
	 * @param SelectTypeP
	 * @param OpenPriceItem
	 * @param OpenPriceSupplier
	 * @param OpenPriceTodateInput
	 */
	public void addingParametersinOpenSCOForProcess(String LocationId, String SelectTypeP, String OpenPriceItem,
			String OpenPriceSupplier, String OpenPriceTodateInput) {
		webDB.switchToFrame(SubcontractOrderLocators.OP_Iframe, ElementType.Xpath);
		commonFunc.hardWait(1000);
		flag = webDB.waitForElement(SubcontractOrderLocators.OpenSCO_LocID_Input, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.OpenSCO_LocID_Input, LocationId);
			webDB.switchToFrame(SubcontractOrderLocators.OP_Iframe, ElementType.Xpath);
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(SubcontractOrderLocators.Select_Type_field, ElementType.Xpath);
			if (flag) {
				webDB.selectDropDownByText(SubcontractOrderLocators.Select_Type_field, SelectTypeP, ElementType.Xpath);
				commonFunc.hardWait(1000);
//				commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.OpenSCO_ItemID_Input, OpenPriceItem);
//				commonFunc.hardWait(1000);
//				commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.OpenSCO_Supl_Input,
//						OpenPriceSupplier);
				webDB.switchToFrame(SubcontractOrderLocators.OP_Iframe, ElementType.Xpath);
				commonFunc.hardWait(1000);
				webDB.clickAnElement(SubcontractOrderLocators.Open_SCO_FROMDATE_Field, ElementType.Xpath);
				commonFunc.hardWait(1000);
				webDB.switchToDefaultContent();
				webDB.clickAnElement(SubcontractOrderLocators.Open_SCO_FROMDATE_input, ElementType.Xpath);
				webDB.pressTabKey();
				webDB.switchToFrame(SubcontractOrderLocators.OP_Iframe, ElementType.Xpath);
				commonFunc.hardWait(1000);
				webDB.sendTextToAnElement(SubcontractOrderLocators.Open_SCO_ToDATE_input, OpenPriceTodateInput,
						ElementType.Xpath);
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(SubcontractOrderLocators.OpenScoCreate_TickIcon, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SubcontractOrderLocators.OpenScoCreate_TickIcon, ElementType.Xpath);
					webDB.switchToDefaultContent();
					commonFunc.checkRefreshCellsDisappear();
					commonFunc.hardWait(1000);
					if (webDB.verifyIsElementDisplayed(SubcontractOrderLocators.OpenSCO_NoteAlert_Close,
							ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(SubcontractOrderLocators.OpenSCO_NoteAlert_Close,
								ElementType.Xpath);
						commonFunc.hardWait(2000);
						String oscdocno = webDB.getTextFromElement(SubcontractOrderLocators.OpenPriceDocNO,
								ElementType.Xpath);
						logs.logging("INFO", "Get OSC DocNo Is: " + oscdocno);
						VariableHandler.setoscdocno(oscdocno);
						commonFunc.hardWait(2000);
						flag = webDB.waitForElement(SubcontractOrderLocators.OP_LineAdd_Icon, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Open SCO Price Header Created Sucessfully");
							checkOpenSCOPriceHeaderDetailsDataBaseValuesWithUIValuesProcess();
							commonFunc.checkRefreshCellsDisappear();
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Adding Process, Batch Qty, Batch Cost Line From
	 * ExcelSheet
	 * 
	 * @author Vimal - RMITS
	 * @param SheetName
	 */
	public void addingProcessLineFromExcelSheet(String SheetName) {
		String[][] SOMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";
		SOMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 4; i < SOMultipleline.length; i++) {
			String ProcessID = SOMultipleline[i][1];
			String BatchQty = SOMultipleline[i][2];
			String BatchCost = SOMultipleline[i][3];
			if (!ProcessID.equals("")) {
				addingProcessLine(ProcessID, BatchQty, BatchCost);
			}
		}
		flag = webDB.waitForElement(SubcontractOrderLocators.OP_LineWise_Save, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SubcontractOrderLocators.OP_LineWise_Save, ElementType.Xpath);
			commonFunc.hardWait(2000);
			if (webDB.verifyIsElementDisplayed(SubcontractOrderLocators.OpenSCO_NoteAlert_Close, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(SubcontractOrderLocators.OpenSCO_NoteAlert_Close,
						ElementType.Xpath);
				commonFunc.hardWait(1000);
				checkOpenSCOPriceLineDetailsDataBaseValuesWithUIValues();
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(SubcontractOrderLocators.OP_Post_Btn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SubcontractOrderLocators.OP_Post_Btn, ElementType.Xpath);
					flag = webDB.waitForElement(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Post Button Clicked Sucessfully");

					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For After Revise Adding Process, Batch Qty, Batch Cost Line
	 * From ExcelSheet
	 * 
	 * @author Vimal - RMITS
	 * @param SheetName
	 */
	public void afterReviseaddingProcessLineFromExcelSheet(String SheetName) {
		String[][] SOMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";
		SOMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 4; i < SOMultipleline.length; i++) {
			String BatchQty = SOMultipleline[i][2];
			String BatchCost = SOMultipleline[i][3];
			if (!BatchQty.equals("")) {
				afterRevisechangingBatchQtyAndBatchCost(BatchQty, BatchCost, i - 3);
			}
		}
		flag = webDB.waitForElement(SubcontractOrderLocators.OP_LineWise_Save, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SubcontractOrderLocators.OP_LineWise_Save, ElementType.Xpath);
			commonFunc.hardWait(2000);
			if (webDB.verifyIsElementDisplayed(SubcontractOrderLocators.OpenSCO_NoteAlert_Close, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(SubcontractOrderLocators.OpenSCO_NoteAlert_Close,
						ElementType.Xpath);
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(SubcontractOrderLocators.OP_Post_Btn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SubcontractOrderLocators.OP_Post_Btn, ElementType.Xpath);
					flag = webDB.waitForElement(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Post Button Clicked Sucessfully");

					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Adding Process, Batch Qty, Batch Cost Line From
	 * ExcelSheet And Cancelled The Document
	 * 
	 * @author Vimal - RMITS
	 * @param SheetName
	 */
	public void addingProcessLineFromExcelSheetAndCancelledTheDocument(String SheetName) {
		String[][] SOMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";
		SOMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 4; i < SOMultipleline.length; i++) {
			String ProcessID = SOMultipleline[i][1];
			String BatchQty = SOMultipleline[i][2];
			String BatchCost = SOMultipleline[i][3];
			if (!ProcessID.equals("")) {
				addingProcessLine(ProcessID, BatchQty, BatchCost);
			}
		}
		flag = webDB.waitForElement(SubcontractOrderLocators.OP_LineWise_Save, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SubcontractOrderLocators.OP_LineWise_Save, ElementType.Xpath);
			commonFunc.hardWait(2000);
			if (webDB.verifyIsElementDisplayed(SubcontractOrderLocators.OpenSCO_NoteAlert_Close, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(SubcontractOrderLocators.OpenSCO_NoteAlert_Close,
						ElementType.Xpath);
				commonFunc.hardWait(1000);
				flag = webDB.waitForElement(SubcontractOrderLocators.OP_Cancel_Btn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SubcontractOrderLocators.OP_Cancel_Btn, ElementType.Xpath);
					commonFunc.hardWait(1000);
					flag = webDB.waitForElement(SubcontractOrderLocators.OP_Cancel_okBtn, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SubcontractOrderLocators.OP_Cancel_okBtn, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(3000);
						flag = webDB.waitForElement(SubcontractOrderLocators.Open_SCO_ReFindIcon, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Document Cancelled Sucessfully");

						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For Adding Process, Batch Qty, Batch Cost
	 * 
	 * @author Vimal - RMITS
	 * @param ProcessID
	 * @param BatchQty
	 * @param BatchCost
	 */
	public void addingProcessLine(String ProcessID, String BatchQty, String BatchCost) {
		flag = webDB.waitForElement(SubcontractOrderLocators.OP_LineAdd_Icon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SubcontractOrderLocators.OP_LineAdd_Icon, ElementType.Xpath);
			commonFunc.hardWait(1000);
			webDB.pressTabKey();
			commonFunc.hardWait(1000);
			commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.OpenSCO_Proc_Input, ProcessID);
			commonFunc.hardWait(1000);
			webDB.pressTabKey();
			commonFunc.hardWait(1000);
			webDB.pressTabKey();
			commonFunc.hardWait(1000);
			webDB.sendTextToAnElement(SubcontractOrderLocators.OP_BatchQty_Input, BatchQty, ElementType.Xpath);
			commonFunc.hardWait(1000);
			webDB.pressTabKey();
			webDB.sendTextToAnElement(SubcontractOrderLocators.OP_BatchCost_Input, BatchCost, ElementType.Xpath);
		}
		commonFunc.hardWait(1000);
		assertTrue(flag);
	}

	/**
	 * Verify TableSpec InputParameter
	 * 
	 * @author Vimal - RMITS
	 * @param Tablespec
	 * @param InputParameter
	 * @param Enteredvalue
	 */
	public void verifyAddTableSpecs(String Tablespec, String InputParameter, String Enteredvalue) {
		flag = webDB.waitForElement(Tablespec, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(Tablespec, ElementType.Xpath);
			logs.logging("INFO", "Table Input Box Is Clicked");
			webDB.clearTextField(InputParameter, ElementType.Xpath);
			logs.logging("INFO", "Clear Text Field The Input Value");
			flag = webDB.waitForElement(Tablespec, ElementType.Xpath);
			if (flag) {
				webDB.doubleClickAnElement(Tablespec, ElementType.Xpath);
				webDB.sendTextToAnElement(InputParameter, Enteredvalue, ElementType.Xpath);
				logs.logging("INFO", "Entered Value Is: " + Enteredvalue);
				String value = webDB.getDomPropertyFromElement(InputParameter, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value"));
				logs.logging("INFO", "Get Attribute Value Is: " + value);
				flag = value.contains(Enteredvalue);
				if (flag) {
					logs.logging("INFO", "Verified Entered Value With Get Attribute Value");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For After Revise Changing Process, Batch Qty, Batch Cost
	 * 
	 * @author Vimal - RMITS
	 * @param BatchQty
	 * @param BatchCost
	 */
	public void afterRevisechangingBatchQtyAndBatchCost(String BatchQty, String BatchCost, int i) {
		commonFunc.hardWait(1000);
		verifyAddTableSpecs(SubcontractOrderLocators.BatchQtyField(i), SubcontractOrderLocators.OP_BatchQty_Input,
				BatchQty);
		webDB.pressTabKey();
		commonFunc.hardWait(2000);
		verifyAddTableSpecs(SubcontractOrderLocators.BatchCostField(i), SubcontractOrderLocators.OP_BatchCost_Input,
				BatchCost);
		commonFunc.hardWait(1000);
	}

	/**
	 * Verify forwardForApproval
	 * 
	 * @author Vimal - RMITS
	 */
	public void openSCOforwardForApproval() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(SalesInvoiceLocators.ForwardApprovalLov,
					commonFunc.getDataFromTestDataSheet("SI_HOD"));
			commonFunc.hardWait(2000);
			webDB.switchToFrame(SalesInvoiceLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(SubcontractOrderLocators.OP_Forward_Ok_Btn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SubcontractOrderLocators.OP_Forward_Ok_Btn, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(SubcontractOrderLocators.Open_SCO_Find_AddIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Verified Navigate To Open SCO Price Home Page");
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

	/**
	 * This Method Used For Check Open SCO Header Approved Status Details DataBase
	 * Values WithUIValues
	 * 
	 * @author Vimal - RMITS
	 */
	public void checkOpenSCOPriceApprovedStatusDetailsDataBaseValuesWithUIValues() {
		String OSPDocumentStatus = dbCommonFunctions.OSPHeaderDetails(VariableHandler.getoscdocno(),
				commonFunc.getDataFromTestDataSheet("OSPDBSTATUSCOLUMN"));
		salesOrderFunctions.verifyAssertionBothValues(OSPDocumentStatus,
				commonFunc.getDataFromTestDataSheet("OpenSCOApprovedstatus"));
		logs.logging("INFO", "Verified Open SCO Price Document Status Is " + OSPDocumentStatus
				+ " Against This Open SCO Price Number " + VariableHandler.getoscdocno());
		commonFunc.hardWait(1000);
	}

	/**
	 * This Method Used For Check Open SCO Header Cancelled Status Details DataBase
	 * Values WithUIValues
	 * 
	 * @author Vimal - RMITS
	 */
	public void checkOpenSCOPriceCancelledStatusDetailsDataBaseValuesWithUIValues() {
		String OSPDocumentStatus = dbCommonFunctions.OSPHeaderDetails(VariableHandler.getoscdocno(),
				commonFunc.getDataFromTestDataSheet("OSPDBSTATUSCOLUMN"));
		salesOrderFunctions.verifyAssertionBothValues(OSPDocumentStatus,
				commonFunc.getDataFromTestDataSheet("OpenSCOCancelledstatus"));
		logs.logging("INFO", "Verified Open SCO Price Document Status Is " + OSPDocumentStatus
				+ " Against This Open SCO Price Number " + VariableHandler.getoscdocno());
		commonFunc.hardWait(1000);
	}

	/**
	 * This Method Used For Check Open SCO Header Revised Status Details DataBase
	 * Values WithUIValues
	 * 
	 * @author Vimal - RMITS
	 */
	public void checkOpenSCOPriceRevisedStatusDetailsDataBaseValuesWithUIValues() {
		String OSPDocumentStatus = dbCommonFunctions.OSPHeaderDetails(VariableHandler.getoscdocno(),
				commonFunc.getDataFromTestDataSheet("OSPDBSTATUSCOLUMN"));
		salesOrderFunctions.verifyAssertionBothValues(OSPDocumentStatus,
				commonFunc.getDataFromTestDataSheet("OpenSCORevisedstatus"));
		logs.logging("INFO", "Verified Open SCO Price Document Status Is " + OSPDocumentStatus
				+ " Against This Open SCO Price Number " + VariableHandler.getoscdocno());
		commonFunc.hardWait(1000);
	}

	/**
	 * This Method Used For Creating Open SCO Price Level Type Supplier + Item +
	 * Process
	 * 
	 * @author Vimal - RMITS
	 * @return boolean @
	 */
	public boolean creatingOpenSCOpriceForSupplier_Item_Process() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		prodsetupFunc.howtodefineOutsideTypeProcessAndAssociatedTheProcess();
		commonFunc.navigateToOpenPriceSCO();
		clickingOpenSCO_FindAddIcon();
		addingParametersinOpenSCO(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SelectTypeSIP"),
				commonFunc.getDataFromTestDataSheet("OpenPriceItem"),
				commonFunc.getDataFromTestDataSheet("OpenPriceSupplier"),
				commonFunc.getDataFromTestDataSheet("OpenPriceTodateInput"));
		addingProcessLineFromExcelSheet(webDB.getDataFromProperties("OpenSCO"));
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getoscdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		commonFunc.checkRefreshCellsDisappear();
		checkOpenSCOPriceApprovedStatusDetailsDataBaseValuesWithUIValues();
		return flag;
	}

	/**
	 * This Method Used For Creating Open SCO Price Level Type Item + Process
	 * 
	 * @author Vimal - RMITS
	 * @return boolean @
	 */
	public boolean creatingOpenSCOpriceForItem_Process() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		prodsetupFunc.howtodefineOutsideTypeProcessAndAssociatedTheProcess();
		commonFunc.navigateToOpenPriceSCO();
		clickingOpenSCO_FindAddIcon();
		addingParametersinOpenSCOForItem_Process(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SelectTypeIP"),
				commonFunc.getDataFromTestDataSheet("OpenPriceItem"),
				commonFunc.getDataFromTestDataSheet("OpenPriceSupplier"),
				commonFunc.getDataFromTestDataSheet("OpenPriceTodateInput"));
		addingProcessLineFromExcelSheet(webDB.getDataFromProperties("OpenSCO"));
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getoscdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		commonFunc.checkRefreshCellsDisappear();
		checkOpenSCOPriceApprovedStatusDetailsDataBaseValuesWithUIValues();
		return flag;
	}

	/**
	 * This Method Used For Creating Open SCO Price Level Type Supplier + Process
	 * 
	 * @author Vimal - RMITS
	 * @return boolean @
	 */
	public boolean creatingOpenSCOpriceForSupplier_Process() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		prodsetupFunc.howtodefineOutsideTypeProcessAndAssociatedTheProcess();
		commonFunc.navigateToOpenPriceSCO();
		clickingOpenSCO_FindAddIcon();
		addingParametersinOpenSCOForSupplier_Process(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SelectTypeSP"),
				commonFunc.getDataFromTestDataSheet("OpenPriceItem"),
				commonFunc.getDataFromTestDataSheet("OpenPriceSupplier"),
				commonFunc.getDataFromTestDataSheet("OpenPriceTodateInput"));
		addingProcessLineFromExcelSheet(webDB.getDataFromProperties("OpenSCO"));
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getoscdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		commonFunc.checkRefreshCellsDisappear();
		checkOpenSCOPriceApprovedStatusDetailsDataBaseValuesWithUIValues();
		return flag;
	}

	/**
	 * This Method Used For Creating Open SCO Price Level Type Process
	 * 
	 * @author Vimal - RMITS
	 * @return boolean @
	 */
	public boolean creatingOpenSCOpriceForProcess() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		prodsetupFunc.howtodefineOutsideTypeProcessAndAssociatedTheProcess();
		commonFunc.navigateToOpenPriceSCO();
		clickingOpenSCO_FindAddIcon();
		addingParametersinOpenSCOForProcess(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SelectTypeP"),
				commonFunc.getDataFromTestDataSheet("OpenPriceItem"),
				commonFunc.getDataFromTestDataSheet("OpenPriceSupplier"),
				commonFunc.getDataFromTestDataSheet("OpenPriceTodateInput"));
		addingProcessLineFromExcelSheet(webDB.getDataFromProperties("OpenSCO"));
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getoscdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		commonFunc.checkRefreshCellsDisappear();
		checkOpenSCOPriceApprovedStatusDetailsDataBaseValuesWithUIValues();
		return flag;
	}

	/**
	 * This Method Used For Find Screen Filtering The Document And Revering The
	 * Document
	 * 
	 * @author Vimal - RMITS
	 * @param OpenPriceTodateInput
	 */
	public void inFindFilterTheDocumentAndDrillDown(String OpenPriceTodateInput, String oscdocno) {
		commonFunc.hardWait(2000);
		commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.OP_Find_DocNO, oscdocno);
		commonFunc.hardWait(1000);
		flag = webDB.waitForElement(SubcontractOrderLocators.OP_Find_Search_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SubcontractOrderLocators.OP_Find_Search_Btn, ElementType.Xpath);
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(SubcontractOrderLocators.OP_DrillDown, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SubcontractOrderLocators.OP_DrillDown, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(SubcontractOrderLocators.OP_Reverse_Btn, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SubcontractOrderLocators.OP_Reverse_Btn, ElementType.Xpath);
					commonFunc.hardWait(2000);
					flag = webDB.waitForElement(SubcontractOrderLocators.OP_Reverse_CloseDate_Btn, ElementType.Xpath);
					if (flag) {
						commonFunc.hardWait(1000);
						webDB.clickAnElement(SubcontractOrderLocators.OP_Reverse_CloseDate_input, ElementType.Xpath);
						commonFunc.hardWait(1000);
						webDB.pressTabKey();
						commonFunc.hardWait(1000);
						webDB.clickAnElement(SubcontractOrderLocators.OP_Reverse_FromDate_input, ElementType.Xpath);
						commonFunc.hardWait(1000);
						webDB.pressTabKey();
						commonFunc.hardWait(1000);
						webDB.clickAnElement(SubcontractOrderLocators.OP_Reverse_ToDate_input, ElementType.Xpath);
						commonFunc.hardWait(1000);
						flag = webDB.waitForElement(SubcontractOrderLocators.OP_Reverse_OK_Btn, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(SubcontractOrderLocators.OP_Reverse_OK_Btn, ElementType.Xpath);
							flag = webDB.waitForElement(SubcontractOrderLocators.OP_AfterReverse_NewDoc_NoteAlert,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Document Reversed Successfully");
								commonFunc.hardWait(1000);
								extractOpenSCODocNumber();
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is To Extract the Voucher Number
	 * 
	 * @author PremKumar.K-RMITS
	 */
	public void getOpenSCOPriceNumberFromToastMessage() {
//		webDB.switchToDefaultContent();
		flag = webDB.waitForElement(SubcontractOrderLocators.OP_AfterReverse_NewDoc_NoteAlert, ElementType.Xpath);
		if (flag) {
			String oscDocumentNumber = webDB
					.getTextFromElement(SubcontractOrderLocators.OP_AfterReverse_NewDoc_NoteAlert, ElementType.Xpath);
			VariableHandler.setRevdocno(oscDocumentNumber);
			logs.logging("INFO", "Open SCO Toast Alert:" + oscDocumentNumber);
			// Updated regex to match MV100 pattern
			// String regex = "MV100/\\d{5}/\\d{2}-\\d{2}";
			String regex = "(OOS\\d+/\\d+/\\d+-\\d+)";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(oscDocumentNumber);
			ArrayList<String> documentNumbers = new ArrayList<>();
			while (matcher.find()) {
				documentNumbers.add(matcher.group());
			}
			if (documentNumbers.size() == 2) {
				String fromDocument = documentNumbers.get(0);
				String toDocument = documentNumbers.get(1);
				System.out.println("From Document: " + fromDocument);
				System.out.println("To Document: " + toDocument);
				String fromNum = fromDocument.split("/")[1];
				String toNum = toDocument.split("/")[1];
				// Convert the document numbers to integers to calculate the range
				int startNum = Integer.parseInt(fromNum);
				int endNum = Integer.parseInt(toNum);
				ArrayList<String> allDocumentNumbers = new ArrayList<>();
				// Generate the list of document numbers in the range
				for (int i = startNum; i <= endNum; i++) {
					String generatedDocumentNumber = "MV100/" + String.format("%05d", i) + "/25-26";
					allDocumentNumbers.add(generatedDocumentNumber);
				}
				// Set the generated document numbers for further use
				VariableHandler.setAllDocumentNumber(allDocumentNumbers);
				System.out.println("Generated Document Numbers:");
				for (String documentNumber : allDocumentNumbers) {
					System.out.println(documentNumber);
				}
			} else {
				System.out.println("Invalid format, unable to extract From and To document numbers.");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Get Extract Open SCO Document Document
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean extractOpenSCODocNumber() {
		flag = webDB.waitForElement(SubcontractOrderLocators.OP_AfterReverse_NewDoc_NoteAlert, ElementType.Xpath);
		if (flag) {
			String text = webDB.getTextFromElement(SubcontractOrderLocators.OP_AfterReverse_NewDoc_NoteAlert,
					ElementType.Xpath);
			logs.logging("INFO", "Toast Alert Text: " + text);
			String regex = "Rate Contract Document Revised\\.-(OSP1-\\d{4}-\\d{4})";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(text);
			if (matcher.find()) {
				String extractedDocNumber = matcher.group(1);
				logs.logging("INFO", "Extracted Rate Contract Document Number: " + extractedDocNumber);
				VariableHandler.setRevdocno(extractedDocNumber);
			} else {
				logs.logging("INFO", "No match found for Rate Contract Document Number.");
				flag = false;
			}
		}

		return flag;
	}

	/**
	 * This Method Used For After Revise The Document And Drill Down
	 * 
	 * @author Vimal - RMITS
	 * @param OSCOdocNumber
	 */
	public void afterReviseTheDocumentAndDrillDown(String OSCOdocNumber) {
		commonFunc.hardWait(1000);
		commonFunc.searchAndSelectValueInFilters(SubcontractOrderLocators.OP_Find_DocNO, OSCOdocNumber);
		commonFunc.hardWait(1000);
		flag = webDB.waitForElement(SubcontractOrderLocators.OP_Find_Search_Btn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SubcontractOrderLocators.OP_Find_Search_Btn, ElementType.Xpath);
			commonFunc.hardWait(2000);
			flag = webDB.waitForElement(SubcontractOrderLocators.OP_DrillDown, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SubcontractOrderLocators.OP_DrillDown, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(1000);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To ExtractStocktransferDocNumber
	 * 
	 * @author PremKumar.K-RMITS
	 */

	public boolean ExtractStocktransferDocNumber() {
		flag = webDB.waitForElement(SubcontractOrderLocators.OP_AfterReverse_NewDoc_NoteAlert, ElementType.Xpath);
		if (flag) {
			String text = webDB.getTextFromElement(SubcontractOrderLocators.OP_AfterReverse_NewDoc_NoteAlert,
					ElementType.Xpath);
			logs.logging("INFO", "Get Text From Note Alert Is :" + text);

			// Updated Regex for new format
			String regex = "Document number \\/\\/Rate Contract Document Revised.-OOS\\d{2}\\/\\d{5}\\/\\d{2}-\\d{2}";
			Pattern pattern = Pattern.compile(regex);
			Matcher OpenSCORevDocNumber = pattern.matcher(text);

			if (OpenSCORevDocNumber.find()) {
				String Revdocno = OpenSCORevDocNumber.group();
				VariableHandler.setRevdocno(Revdocno);
				logs.logging("INFO", "Sales Order number Is :" + VariableHandler.getRevdocno());
			} else {
				logs.logging("INFO", "No match found for Sale Order Number in Extract Stock transfer.");
			}
		}
		return flag;
	}

	/**
	 * This Method Used For Reversing The Process Type Document
	 * 
	 * @author Vimal - RMITS
	 * @return boolean @
	 */
	public boolean reversingTheProcessTypeDocument() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		prodsetupFunc.howtodefineOutsideTypeProcessAndAssociatedTheProcessForOpenscoReversing();
		commonFunc.navigateToOpenPriceSCO();
		clickingOpenSCO_FindAddIcon();
		addingParametersinOpenSCOForProcess(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SelectTypeP"),
				commonFunc.getDataFromTestDataSheet("OpenPriceItem"),
				commonFunc.getDataFromTestDataSheet("OpenPriceSupplier"),
				commonFunc.getDataFromTestDataSheet("OpenPriceTodateInput"));
		addingProcessLineFromExcelSheet(webDB.getDataFromProperties("OpenSCORevise"));
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getoscdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		commonFunc.navigateToOpenPriceSCO();
		inFindFilterTheDocumentAndDrillDown(commonFunc.getDataFromTestDataSheet("OpenPriceTodateInput"),
				VariableHandler.getoscdocno());
		afterReviseTheDocumentAndDrillDown(VariableHandler.getRevdocno());
		afterReviseaddingProcessLineFromExcelSheet(webDB.getDataFromProperties("OpenSCO"));
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getRevdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkOpenSCOPriceRevisedStatusDetailsDataBaseValuesWithUIValues();
		return flag;
	}

	/**
	 * This Method used to Reversing Item + Process Type Document
	 * 
	 * @author Vimal - RMITS
	 * @return boolean @
	 */
	public boolean reveringItem_ProcessTypeDocument() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		prodsetupFunc.howtodefineOutsideTypeProcessAndAssociatedTheProcessForOpenscoReversing();
		commonFunc.navigateToOpenPriceSCO();
		clickingOpenSCO_FindAddIcon();
		addingParametersinOpenSCOForItem_Process(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SelectTypeIP"),
				commonFunc.getDataFromTestDataSheet("OpenPriceItem"),
				commonFunc.getDataFromTestDataSheet("OpenPriceSupplier"),
				commonFunc.getDataFromTestDataSheet("OpenPriceTodateInput"));
		addingProcessLineFromExcelSheet(webDB.getDataFromProperties("OpenSCORevise"));
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getoscdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		commonFunc.navigateToOpenPriceSCO();
		inFindFilterTheDocumentAndDrillDown(commonFunc.getDataFromTestDataSheet("OpenPriceTodateInput"),
				VariableHandler.getoscdocno());
		afterReviseTheDocumentAndDrillDown(VariableHandler.getRevdocno());
		afterReviseaddingProcessLineFromExcelSheet(webDB.getDataFromProperties("OpenSCO"));
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getRevdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		commonFunc.checkRefreshCellsDisappear();
		checkOpenSCOPriceRevisedStatusDetailsDataBaseValuesWithUIValues();
		return flag;
	}

	/**
	 * This Method used to Reversing Supplier + Item + Process Type Document
	 * 
	 * @author Vimal - RMITS
	 * @return boolean @
	 */
	public boolean reveringSupplier_Item_ProcessTypeDocument() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		prodsetupFunc.howtodefineOutsideTypeProcessAndAssociatedTheProcessForOpenscoReversing();
		commonFunc.navigateToOpenPriceSCO();
		clickingOpenSCO_FindAddIcon();
		addingParametersinOpenSCO(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SelectTypeSIP"),
				commonFunc.getDataFromTestDataSheet("OpenPriceItem"),
				commonFunc.getDataFromTestDataSheet("OpenPriceSupplier"),
				commonFunc.getDataFromTestDataSheet("OpenPriceTodateInput"));
		addingProcessLineFromExcelSheet(webDB.getDataFromProperties("OpenSCORevise"));
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getoscdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		commonFunc.navigateToOpenPriceSCO();
		inFindFilterTheDocumentAndDrillDown(commonFunc.getDataFromTestDataSheet("OpenPriceTodateInput"),
				VariableHandler.getoscdocno());
		afterReviseTheDocumentAndDrillDown(VariableHandler.getRevdocno());
		afterReviseaddingProcessLineFromExcelSheet(webDB.getDataFromProperties("OpenSCO"));
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getRevdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		checkOpenSCOPriceRevisedStatusDetailsDataBaseValuesWithUIValues();
		return flag;
	}

	/**
	 * This Method used to Reversing Supplier + Process Type Document
	 * 
	 * @author Vimal - RMITS
	 * @return boolean
	 */
	public boolean reveringSupplier_Process_TypeDocument() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		prodsetupFunc.howtodefineOutsideTypeProcessAndAssociatedTheProcessForOpenscoReversing();
		commonFunc.navigateToOpenPriceSCO();
		clickingOpenSCO_FindAddIcon();
		addingParametersinOpenSCOForSupplier_Process(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SelectTypeSP"),
				commonFunc.getDataFromTestDataSheet("OpenPriceItem"),
				commonFunc.getDataFromTestDataSheet("OpenPriceSupplier"),
				commonFunc.getDataFromTestDataSheet("OpenPriceTodateInput"));
		addingProcessLineFromExcelSheet(webDB.getDataFromProperties("OpenSCORevise"));
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getoscdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		commonFunc.navigateToOpenPriceSCO();
		inFindFilterTheDocumentAndDrillDown(commonFunc.getDataFromTestDataSheet("OpenPriceTodateInput"),
				VariableHandler.getoscdocno());
		afterReviseTheDocumentAndDrillDown(VariableHandler.getRevdocno());
		afterReviseaddingProcessLineFromExcelSheet(webDB.getDataFromProperties("OpenSCO"));
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getRevdocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		commonFunc.checkRefreshCellsDisappear();
		checkOpenSCOPriceRevisedStatusDetailsDataBaseValuesWithUIValues();
		return flag;
	}

	/**
	 * This Method Used For Creating Open SCO Price Level Type Process Type And
	 * Cancelled The Document
	 * 
	 * @author Vimal - RMITS
	 * @return boolean @
	 */
	public boolean creatingOpenSCOpriceForProcessTypeAndCancelledTheDocument() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		prodsetupFunc.howtodefineOutsideTypeProcessAndAssociatedTheProcess();
		commonFunc.navigateToOpenPriceSCO();
		clickingOpenSCO_FindAddIcon();
		addingParametersinOpenSCOForProcess(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SelectTypeP"),
				commonFunc.getDataFromTestDataSheet("OpenPriceItem"),
				commonFunc.getDataFromTestDataSheet("OpenPriceSupplier"),
				commonFunc.getDataFromTestDataSheet("OpenPriceTodateInput"));
		addingProcessLineFromExcelSheetAndCancelledTheDocument(webDB.getDataFromProperties("OpenSCO"));
		flag = commonFunc.logoutUser();
		commonFunc.checkRefreshCellsDisappear();
		checkOpenSCOPriceCancelledStatusDetailsDataBaseValuesWithUIValues();
		return flag;
	}

	/**
	 * This Method Used For Creating Open SCO Price Level Type Supplier + Item +
	 * Process And Cancelled The Document
	 * 
	 * @author Vimal - RMITS
	 * @return boolean @
	 */
	public boolean creatingOpenSCOpriceForSupplier_Item_ProcessTypeAndCancelledTheDocument() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		prodsetupFunc.howtodefineOutsideTypeProcessAndAssociatedTheProcess();
		commonFunc.navigateToOpenPriceSCO();
		clickingOpenSCO_FindAddIcon();
		addingParametersinOpenSCO(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SelectTypeSIP"),
				commonFunc.getDataFromTestDataSheet("OpenPriceItem"),
				commonFunc.getDataFromTestDataSheet("OpenPriceSupplier"),
				commonFunc.getDataFromTestDataSheet("OpenPriceTodateInput"));
		addingProcessLineFromExcelSheetAndCancelledTheDocument(webDB.getDataFromProperties("OpenSCO"));
		flag = commonFunc.logoutUser();
		commonFunc.checkRefreshCellsDisappear();
		checkOpenSCOPriceCancelledStatusDetailsDataBaseValuesWithUIValues();
		return flag;
	}

	/**
	 * This Method Used For Creating Open SCO Price Level Type Item + Process And
	 * Cancelled The Document
	 * 
	 * @author Vimal - RMITS
	 * @return boolean @
	 */
	public boolean creatingOpenSCOpriceForItem_ProcessTypeAndCancelledTheDocument() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		prodsetupFunc.howtodefineOutsideTypeProcessAndAssociatedTheProcess();
		commonFunc.navigateToOpenPriceSCO();
		clickingOpenSCO_FindAddIcon();
		addingParametersinOpenSCOForItem_Process(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SelectTypeIP"),
				commonFunc.getDataFromTestDataSheet("OpenPriceItem"),
				commonFunc.getDataFromTestDataSheet("OpenPriceSupplier"),
				commonFunc.getDataFromTestDataSheet("OpenPriceTodateInput"));
		addingProcessLineFromExcelSheetAndCancelledTheDocument(webDB.getDataFromProperties("OpenSCO"));
		flag = commonFunc.logoutUser();
		commonFunc.checkRefreshCellsDisappear();
		checkOpenSCOPriceCancelledStatusDetailsDataBaseValuesWithUIValues();
		return flag;
	}

	/**
	 * This Method Used For Creating Open SCO Price Level Type Supplier + Process
	 * 
	 * @author Vimal - RMITS
	 * @return boolean @
	 */
	public boolean creatingOpenSCOpriceForSupplier_ProcessTypeAndCancelledTheDocument() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		prodsetupFunc.howtodefineOutsideTypeProcessAndAssociatedTheProcess();
		commonFunc.navigateToOpenPriceSCO();
		clickingOpenSCO_FindAddIcon();
		addingParametersinOpenSCOForSupplier_Process(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SelectTypeSP"),
				commonFunc.getDataFromTestDataSheet("OpenPriceItem"),
				commonFunc.getDataFromTestDataSheet("OpenPriceSupplier"),
				commonFunc.getDataFromTestDataSheet("OpenPriceTodateInput"));
		addingProcessLineFromExcelSheetAndCancelledTheDocument(webDB.getDataFromProperties("OpenSCO"));
		flag = commonFunc.logoutUser();
		commonFunc.checkRefreshCellsDisappear();
		checkOpenSCOPriceCancelledStatusDetailsDataBaseValuesWithUIValues();
		return flag;
	}
}
