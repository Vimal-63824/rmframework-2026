package commonlocators.supplychainmanagement;

import commonfunctions.CommonFunctions;

/**
 * @author Harikrishna - RMITS
 * @Company RoadMap It Solutions October 08, 2025
 */
public class StockAdjustmentLocators {

	/** @Locator Xpath */
	public static final String FindAddButton = "//button[@title='Add']";
	/** @Locator Xpath */
	public static final String LoactionLovButton = "//button[@id='P93131091_SATHD_PLNT_LOC_ID_lov_btn']";
	/** @Locator Xpath */
	public static final String DateInputField = "//input[@id='P93131091_SATHD_ORD_DATE_input']";
	/** @Locator Xpath */
	public static final String WarehouseLovbutton = "//button[@id='P93131091_SATHD_STORE_ID_lov_btn']";
	/** @Locator Xpath */
	public static final String NarrationInputField = "//input[@id='P93131091_SATHD_REFERENCE']";
	/** @Locator Xpath */
	public static final String SaveButton = "//button[@title='Save']";
	/** @Locator Xpath */
	public static final String StockAdjFrame = "//iframe[contains(@title,'Create')]";
	/** @Locator Xpath */
	public static final String PostButton = "//span[text()='Post']/parent::button";
	/** @Locator Xpath */
	public static final String CancelButton = "//span[text()='Cancel']/parent::button";
	/** @Locator Xpath */
	public static final String LocationField = "//span[@id='P9313109001_SATHD_PLNT_LOC_ID_DISPLAY']";
	/** @Locator Xpath */
	public static final String UnitField = "//span[@id='P9313109001_SATHD_PLNT_DISPLAY']";
	/** @Locator Xpath */
	public static final String DateField = "//input[@id='P9313109001_SATHD_ORD_DATE_input']";
	/** @Locator Xpath */
	public static final String DocNumberField = "//input[@id='P9313109001_SATHD_ORD_NO']";
	/** @Locator Xpath */
	public static final String WarehouseField = "//span[@id='P9313109001_SATHD_STORE_ID_DISPLAY']";
	/** @Locator Xpath */
	public static final String StatusField = "//span[@id='P9313109001_SATHD_STATUS_DISPLAY']";
	/** @Locator Xpath */
	public static final String NarrationField = "//input[@id='P9313109001_SATHD_REFERENCE']";
	/** @Locator Xpath */
	public static final String LineWiseAddButton = "//div[@id='line_ig']//button[@title='Add']";
	/** @Locator Xpath */
	public static final String StkAdLinesTable = "(//div[@id='line_ig_ig_grid_vc']//table)[2]";

	/** @Locator Xpath */
	public static final String LinePrefixField() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(StkAdLinesTable, "Pfx.") + ")[1]";
	}

	/** @Locator Xpath */
	public static final String LinePrefixLovButton() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(StkAdLinesTable, "Pfx.")
				+ ")[1]//button";
	}

	/** @Locator Xpath */
	public static final String LineItemField() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(StkAdLinesTable, "Item") + ")[1]";
	}

	/** @Locator Xpath */
	public static final String LineItemLovButton() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(StkAdLinesTable, "Item")
				+ ")[1]//button";
	}

	/** @Locator Xpath */
	public static final String LineQtyField() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(StkAdLinesTable, "Quantity") + ")[1]";
	}

	/** @Locator Xpath */
	public static final String LineQtyInputField() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(StkAdLinesTable, "Quantity")
				+ ")[1]//input";
	}

	/** @Locator Xpath */
	public static final String LineUnitCostField() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(StkAdLinesTable, "Unit Cost")
				+ ")[1]";
	}

	/** @Locator Xpath */
	public static final String LineUnitCostInputField() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(StkAdLinesTable, "Unit Cost")
				+ ")[1]//input";
	}

	/** @Locator Xpath */
	public static final String LineWiseSaveButton = "//div[@id='line_ig']//button[@title='Save']";
	/** @Locator Xpath */
	public static final String LotSerialAddButton = "//div[@id='LS']//button[@title='Add']";
	/** @Locator Xpath */
	public static final String StkAdLotSerialTable = "(//div[@id='LS_ig_grid_vc']//table)[2]";

	/** @Locator Xpath */
	public static final String LotNumberField() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(StkAdLotSerialTable, "Lot No.")
				+ ")[1]";
	}

	/** @Locator Xpath */
	public static final String LotQtyField() {
		return "("
				+ CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(StkAdLotSerialTable, "Lot/Serial Qty.")
				+ ")[1]";
	}

	/** @Locator Xpath */
	public static final String LotNumberInputField() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(StkAdLotSerialTable, "Lot No.")
				+ ")[1]//input";
	}

	/** @Locator Xpath */
	public static final String LotQtyInputField() {
		return "("
				+ CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(StkAdLotSerialTable, "Lot/Serial Qty.")
				+ ")[1]//input";
	}

	/** @Locator Xpath */
	public static final String LotWiseSaveButton = "//div[@id='LS']//button[@title='Save']";

	/** @Locator Xpath */
	public static final String LotExpiryField() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(StkAdLotSerialTable, "Expiry Date") + ")[1]";
	}

	/** @Locator Xpath */
	public static final String LotExpiryInputField() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(StkAdLotSerialTable, "Expiry Date")
				+ ")[1]//input";
	}

	/** @Locator Xpath */
	public static final String GenerateSerialButton = "//button[@id='B1125821607361253974']";
	/** @Locator Xpath */
	public static final String SerialItemLovButton = "//button[@id='P9313109001_LN_PROD_ID_lov_btn']";
	/** @Locator Xpath */
	public static final String SerialItemLocatorLov = "//button[@id='P9313109001_FROM_BIN_ID_lov_btn']";
	/** @Locator Xpath */
	public static final String SerialItemStartSerialInputField = "//input[@id='P9313109001_START_SERIAL_NO']";
	/** @Locator Xpath */
	public static final String SerialItemQtyInputField = "//input[@id='P9313109001_SERIAL_QTY']";
	/** @Locator Xpath */
	public static final String SerialItemExpiryDateInputField = "//input[@id='P9313109001_EXP_DATE_input']";
	/** @Locator Xpath */
	public static final String SerialItemGenerateButton = "//button[@id='B1125822168936253980']";

	/** @locatorType Xpath */
	public static final String LineItem(int i) {
		return "//*[@id='line_ig_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[1]";
	}

}
