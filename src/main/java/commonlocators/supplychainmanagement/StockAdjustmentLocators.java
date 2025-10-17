package commonlocators.supplychainmanagement;

/**
 * @author Harikrishna - RMITS
 * @Company RoadMap It Solutions October 08, 2025
 */
public class StockAdjustmentLocators {

	/** @Locator Xpath */
	public static final String FindAddButton = "//button[@id='B3925995802821860842']";
	/** @Locator Xpath */
	public static final String LoactionLovButton = "//button[@id='P93131091_SATHD_PLNT_LOC_ID_lov_btn']";
	/** @Locator Xpath */
	public static final String DateInputField = "//input[@id='P93131091_SATHD_ORD_DATE_input']";
	/** @Locator Xpath */
	public static final String WarehouseLovbutton = "//button[@id='P93131091_SATHD_STORE_ID_lov_btn']";
	/** @Locator Xpath */
	public static final String NarrationInputField = "//input[@id='P93131091_SATHD_REFERENCE']";
	/** @Locator Xpath */
	public static final String SaveButton = "//button[@id='B902803369716642400']";
	/** @Locator Xpath */
	public static final String StockAdjFrame = "//iframe[@title='Stock Adjustment']";
	/** @Locator Xpath */
	public static final String PostButton = "//button[@id='B3320865005112971691']";
	/** @Locator Xpath */
	public static final String CancelButton = "//button[@id='B3341186838056512919']";
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
	public static final String LineWiseAddButton = "//button[@id='B3341555976784669148']";
	/** @Locator Xpath */
	public static final String LinePrefixField = "//*[@id='line_ig_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[2]";
	/** @Locator Xpath */
	public static final String LinePrefixLovButton = "//button[@id='C3341557087606669159_lov_btn']";
	/** @Locator Xpath */
	public static final String LineItemField = "//*[@id='line_ig_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[3]";
	/** @Locator Xpath */
	public static final String LineItemLovButton = "//button[@id='C2730256548692805492_lov_btn']";
	/** @Locator Xpath */
	public static final String LineQtyField = "//*[@id='line_ig_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[9]";
	/** @Locator Xpath */
	public static final String LineQtyInputField = "//input[@id='C3341557711821669166']";
	/** @Locator Xpath */
	public static final String LineUnitCostField = "//*[@id='line_ig_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[10]";
	/** @Locator Xpath */
	public static final String LineUnitCostInputField = "//input[@id='unitcost']";
	/** @Locator Xpath */
	public static final String LineWiseSaveButton = "//button[@id='B3341556253575669151']";
	/** @Locator Xpath */
	public static final String LotSerialAddButton = "//button[@id='B3789868778399673372']";
	/** @Locator Xpath */
	public static final String LotNumberField = "//*[@id='LS_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[3]";
	/** @Locator Xpath */
	public static final String LotQtyField = "//*[@id='LS_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[7]";
	/** @Locator Xpath */
	public static final String LotNumberInputField = "//input[@id='LS_LOT']";
	/** @Locator Xpath */
	public static final String LotQtyInputField = "//input[@id='C3789604797237654465']";
	/** @Locator Xpath */
	public static final String LotWiseSaveButton = "//button[@id='B3789869055087673375']";
	/** @Locator Xpath */
	public static final String LotExpiryField = "//*[@id='LS_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[12]";
	/** @Locator Xpath */
	public static final String LotExpiryInputField = "//input[@id='C2726176967127730875_input']";
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
