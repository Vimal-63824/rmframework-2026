package commonlocators.supplychainmanagement;

/**
 * @author Sakthi-GWL, Deepan-GWL
 * @company Galaxy Weblinks March 04, 2025
 */
public class DeliveryChallanLocators {

	/** @locatorType Xpath */
	public static final String DCNumberLovButton = "//input[@data-testid='P75131101_DC_NO']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SearchButton = "//button[@data-testid='Find_line']";
	/** @locatorType Xpath */
	public static final String ListItem = "//div[@id='dc_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[3]";
	/** @locatorType Xpath */
	public static final String BackButton = "//button[@data-testid='Back']";
	/** @locatorType Xpath */
	public static final String POTotal = "//span[@data-testid='P751311006_DCHD_TOT_VALUE']";

	/** @locatorType Xpath */
	public static final String DCNumber(String DCNumber) {
		return "(//a[text()='" + DCNumber + "'])[last()]";
	}

	/** @locatorType Xpath */
	public static final String StatusDcNumber(String DCNumber) {
		return "(//a[text()='" + DCNumber + "']/../../td/div[contains(@style,'Green')])[last()]";
	}

	/** @locatorType Xpath */
	public static final String ItemTable(int i) {
		return "//div[@id='dc_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[3]";
	}

	/** @locatorType Xpath */
	public static final String ItemCostTable(int i) {
		return "//div[@id='dc_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[9]";
	}
}
