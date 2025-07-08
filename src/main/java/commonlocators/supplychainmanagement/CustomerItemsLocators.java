package commonlocators.supplychainmanagement;

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
	public static final String CustomerLovButton = "//button[contains(@id,'P36702113_SUPLR_SUPLR_ID_lov_btn')]";
	/** Locator Xpath */
	public static final String AddButton = "//button[contains(@id,'B6072453502774090220')]";
	/** Locator Xpath */
	public static final String ItemFieldClick = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr/td[2]";
	/** Locator Xpath */
	public static final String ItemLovButton = "//button[@id='C903814840346873296_lov_btn']";
	/** Locator Xpath */
	public static final String Pricecls = "//*[@id=\"ig_line_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[1]/td[11]";

	/** Locator Xpath */
	public static final String PriceClassSelectDropdown = "//select[contains(@id,'C6074344993356576307')]";

	/** Locator Xpath */
	public static final String PriceBasisCL = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[12]";

	/** Locator Xpath */
	public static final String PricebasisSelect(int i) {
		return "//*[@id=\'ig_line_ig_grid_vc\']/div[2]/div[3]/table/tbody/tr[" + 1 + "]/td[12]  ";
	}

	/** Locator Xpath */
	public static final String PriceBasisSelectDropdown = " //select[@id='C6074344139927576298']";
	/** Locator Xpath */
	public static final String CatalogSelectDropdown = "//select[contains(@id,'C6074344188841576299')]";
	/** Locator Xpath */
	public static final String SaveButtonCustomer = "//button[@id='B6074354939381600705']/span";
	/** Locator Xpath */
	public static final String DefaultFlagChecked = "//table[@class='a-GV-table']//tr[1]/td[14]";
	/** Locator Xpath */
	public static final String ToastAlertMessageCustomerItems = "//*[@id='t_Alert_Success']/div/div[2]/div/h6";
	// todo
	/** Locator Xpath */
	public static final String AddPLusButton = "//span[@class='t-Icon fa  fa-plus']";
	/** Locator Xpath */
	public static final String AddPlusButtonItems = "//button[@id='B6074354848320600704']";

	/** Locator Xpath */
	public static final String DefaultFlag(int i) {
		return "//*[@id=\"ig_line_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[ " + i + "]/td[15] ";
	}

	/** Locator Xpath */
	public static final String DefaultFlag = "//input[@aria-labelledby='C6072442655543065813_HDR']";
	/** Locator Xpath */
	public static final String DefaultFlagField = "//*[@id='ig_line_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[15]/div";

	/** Locator Xpath */
	public static final String CustomerItemsSearchBox = "//input[@id='ig_line_ig_toolbar_search_field']";
	/** Locator Xpath */
	public static final String Gobutton = "(//button[@class='a-Button a-Toolbar-item'])[1]";


}
