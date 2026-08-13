package commonlocators.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
public class StocksPageLocators {

	/** @locatorType Xpath */
	public static final String StockCheckBox = "(//input[@data-testid='P93132130_TYPE'])[1]";
	/** @locatorType Xpath */
	public static final String StockCost = "(//input[@data-testid='P93132130_TYPE'])[10]/../label";
	/** @locatorType Xpath */
	public static final String StockSerachField = "//input[@id='HUJ_sort_widget_search_field']";
	/** @locatorType Xpath */
	public static final String GoButton = "//button[@id='TF_search_button']";
	/** @locatorType Xpath */
	public static final String StockCostSerachField = "//input[@id='TF_search_field']";
	/** @locatorType Xpath */
	public static final String CostGoButton = "//button[@id='TF_search_button']";
	/** @locatorType Xpath */
	public static final String GenerateButton = "//button[@data-testid='FIND']";
	/** @locatorType Xpath */
	public static final String StockItem = "//div[@id='HUJ_data_panel']/div/div/div[@id='stickyTableHeader_1']/table/tr/th[3]";
	/** @locatorType Xpath */
	public static final String FilterItemsearch = "//input[@id='HUJ_sort_widget_search_field']";
	/** @locatorType Xpath */
	public static final String NoOfRow = "//table[contains(@aria-label,'STO_CK')]/tbody/tr/td[1]";
	/** @locatorType Xpath */
	public static final String CostNoOfRow = "//table[@id='2177268981129914442_orig']/tbody/tr/td[1]";
	/** @locatorType Xpath */
	public static final String RemoveFilter = "//button[@aria-label='Remove Filter']";
	/** @locatorType Xpath */
	public static final String StockCostItem = "//div[@id='TF_data_panel']/div/div/div[@id='stickyTableHeader_1']/table/tr/th[2]";
	/** @locatorType Xpath */
	public static final String FilterItemStockCost = "//input[@id='TF_sort_widget_search_field']";

	/** @locatorType Xpath */
	public static final String ItemRow(int i) {
		return "//table[contains(@aria-label,'STO_CK')]/tbody/tr[" + i + "]/td[3]";
	}

	/** @locatorType Xpath */
	public static final String CostItemRow(int i) {
		return "//table[contains(@aria-label,'STO_CK')]/tbody/tr[" + i + "]/td[3]";
	}

	/** @locatorType Xpath */
	public static final String SelectItem(String item) {
		return "//a[@data-return-value='" + item + "']";
	}

	/** @locatorType Xpath */
	public static final String WHRow(int i) {
		return "//table[contains(@aria-label,'STO_CK')]/tbody/tr[" + i + "]/td[1]";
	}

	/** @locatorType Xpath */
	public static final String CostWHRow(int i) {
		return "//table[contains(@aria-label,'STO_CK')]/tbody/tr[" + i + "]/td[1]";
	}

	/** @locatorType Xpath */
	public static final String StockQtyRow(int i) {
		return "//table[contains(@aria-label,'STO_CK')]/tbody/tr[" + i + "]/td[8]";
	}

	/** @locatorType Xpath */
	public static final String CostRow(int i) {
		return "//table[contains(@aria-label,'STO_CK')]/tbody/tr[" + i + "]/td[13]";
	}

}
