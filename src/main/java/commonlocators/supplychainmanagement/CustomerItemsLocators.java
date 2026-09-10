package commonlocators.supplychainmanagement;

import commonfunctions.CommonFunctions;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks May 20, 2025
 */
public class CustomerItemsLocators {

	/** Locator Xpath */
	public static final String CustomerItems = "//a[text()='Customer Item']";
	/** Locator Xpath */
	public static final String CustomerPlusIcon = "//button[contains(@id,'B2146629522453507346')]";
	/** Locator Xpath */
	public static final String CustomerLovButton = "//input[@data-testid='P36702113_SUPLR_SUPLR_ID']/following-sibling::button";
	/** Locator Xpath */
	public static final String AddButton = "//button[contains(@id,'B6072453502774090220')]";
	/** Locator Xpath */
	public static final String CustomerItemLinesTable = "(//div[@id='ig_line_ig_grid_vc']//table)[2]";

	/** Locator Xpath */
	public static final String ItemFieldClick() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(CustomerItemLinesTable, "Item")
				+ ")[1]";
	}

	/** Locator Xpath */
	public static final String ItemLovButton = "//input[@data-testid='ITEM_ID_REV']/following-sibling::button";

	/** Locator Xpath */
	public static final String Pricecls() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(CustomerItemLinesTable, "Price Class")
				+ ")[1]";
	}

	/** Locator Xpath */
	public static final String PriceClassSelectDropdown = "//select[@data-testid='CUSTP_CLASS_ID']";

	/** Locator Xpath */
	public static final String PriceBasisCL() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(CustomerItemLinesTable, "Price Basis")
				+ ")[1]";
	}

	/** Locator Xpath */
	public static final String PricebasisSelect(int i) {
		return "//*[@id=\'ig_line_ig_grid_vc\']/div[2]/div[3]/table/tbody/tr[" + 1 + "]/td[12]  ";
	}

	/** Locator Xpath */
	public static final String PriceBasisSelectDropdown = " //select[@data-testid='CUSTP_PRICE_BASIS']";
	/** Locator Xpath */
	public static final String CatalogSelectDropdown = "//select[@data-testid='CUSTP_CTLG_ID']";
	/** Locator Xpath */
	public static final String SaveButtonCustomer = "//button[@data-testid='Save']";
	/** Locator Xpath */
	public static final String DefaultFlagChecked = "//table[@class='a-GV-table']//tr[1]/td[14]";
	/** Locator Xpath */
	public static final String ToastAlertMessageCustomerItems = "//*[@id='t_Alert_Success']/div/div[2]/div/h6";
	// todo
	/** Locator Xpath */
	public static final String AddPLusButton = "//button[@data-testid='Add_CI']";
	/** Locator Xpath */
	public static final String AddPlusButtonItems = "//button[@data-testid='Add']";

	/** Locator Xpath */
	public static final String DefaultFlag(int i) {
		return "//*[@id=\"ig_line_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[ " + i + "]/td[15] ";
	}

	/** Locator Xpath */
	public static final String DefaultFlag = "//input[@aria-labelledby='C6072442655543065813_HDR']";

	/** Locator Xpath */
	public static final String DefaultFlagField() {
		return "(" + CommonFunctions.checkAndReturnIndexForLocatorWithHeaderValue(CustomerItemLinesTable, "Default")
				+ ")[1]//div";
	}

	/** Locator Xpath */
	public static final String CustomerItemsSearchBox = "//input[@id='ig_line_ig_toolbar_search_field']";
	/** Locator Xpath */
	public static final String Gobutton = "(//button[@class='a-Button a-Toolbar-item'])[1]";

	/** @locatorType Xpath */
	public static final String DefaultFlagItem(String item) {
		return "//td[normalize-space(text())='" + item + "']/ancestor::tr/td[14]//input[@type='checkbox']";
	}

}
