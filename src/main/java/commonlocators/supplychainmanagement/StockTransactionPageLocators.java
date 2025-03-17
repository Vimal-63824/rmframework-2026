package commonlocators.supplychainmanagement;

/**
 * @author Sakthi-GWL, Deepan-GWL
 * @company Galaxy Weblinks March 04, 2025
 */
public class StockTransactionPageLocators {

	/** @locatorType Xpath */
	public static final String VoucherNumber = "//button[@id='P93132310_BS_VOU_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String GenerateButton = "//button[@id='B2174703010114270614']";
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

}
