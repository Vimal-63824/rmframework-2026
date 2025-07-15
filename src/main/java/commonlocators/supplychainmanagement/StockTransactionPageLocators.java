package commonlocators.supplychainmanagement;

/**
 * @author Sakthi-GWL, Deepan-GWL
 * @company Galaxy Weblinks March 04, 2025
 */
public class StockTransactionPageLocators {

	/** @locatorType Xpath */
	public static final String VoucherNumber = "//input[@data-testid='P93132310_BS_VOU_NO']";
	/** @locatorType Xpath */
	public static final String GenerateButton = "//button[@data-testid='FIND']";
	/** @locatorType Xpath */
	public static final String ListOfElement = "//table[@id='2175420558094931827_orig']/tbody/tr/td[4]";

	/** @locatorType Xpath */
	public static final String VoucherNumber(int i) {
		return "//table[@id='2175420558094931827_orig']/tbody/tr[" + i + "]/td[4]";
	}

	/** @locatorType Xpath */
	public static final String ItemName(int i) {
		return "//table[@id='2175420558094931827_orig']/tbody/tr[" + i + "]/td[8]";
	}

	/** @locatorType Xpath */
	public static final String Quantity(int i) {
		return "//table[@id='2175420558094931827_orig']/tbody/tr[" + i + "]/td[12]";
	}

	/** @locatorType Xpath */
	public static final String Warehouse(int i) {
		return "//table[@id='2175420558094931827_orig']/tbody/tr[" + i + "]/td[7]";
	}

	/** @locatorType Xpath */
	public static final String SFGTransaction = "(//input[@data-testid='P93132310_BS_STOCK'])[3]/following-sibling::label";
	/** @locatorType Xpath */
	public static final String SearchField = "//input[@id='ContainerSFG_search_field']";
	/** @locatorType Xpath */
	public static final String GoButton = "//button[@id='ContainerSFG_search_button']";
	/** @locatorType Xpath */
	public static final String RemoveFilter = "//button[@data-setting='filter']";
	/** @locatorType Xpath */
	public static final String SFGWareHouse = "//table[@id='350559262420402743_orig']/tbody/tr/td[7]";
	/** @locatorType Xpath */
	public static final String SFGUnitCost = "//table[@id='350559262420402743_orig']/tbody/tr/td[17]";
	/** @locatorType Xpath */
	public static final String SFGGenerateButton = "// button[@data-testid='FIND']";

	/** @locatorType Xpath */
	public static final String SFGWareHouse(int No) {
		return "//table[@id='350559262420402743_orig']/tbody/tr[" + No + "]/td[7]";
	}

	/** @locatorType Xpath */
	public static final String SFGUnitCost(int No) {
		return "//table[@id='350559262420402743_orig']/tbody/tr[" + No + "]/td[17]";
	}

}
