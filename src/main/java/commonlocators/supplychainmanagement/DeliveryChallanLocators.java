package commonlocators.supplychainmanagement;
/**
 * @author Sakthi-GWL, Deepan-GWL
 * @company Galaxy Weblinks March 04, 2025
 */
public class DeliveryChallanLocators {

	/** @locatorType Xpath */
	public static final String DCNumberLovButton = "//button[@id='P75131101_DC_NO_lov_btn']";
	/** @locatorType Xpath */
	public static final String SearchButton = "//button[@id='B182955682519758259']";
	/** @locatorType Xpath */
	public static final String ListItem = "//div[@id='dc_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[3]";
	/** @locatorType Xpath */
	public static final String BackButton = "//button[@id='back']";
	/** @locatorType Xpath */
	public static final String POTotal = "//span[@id='P751311006_DCHD_TOT_VALUE_DISPLAY']";

	/** @locatorType Xpath */
	public static final String DCNumber(String DCNumber) {
		return "(//table[@id='141851938041998744_orig']/tbody/tr/td[4]/a[text()='" + DCNumber + "'])[last()]";
	}

	/** @locatorType Xpath */
	public static final String StatusDcNumber(String DCNumber) {
		return "(//a[text()='" + DCNumber + "']/../../td/div[contains(@style,'Green')])[last()]";
	}
	
	/** @locatorType Xpath */
	public static final String ItemTable(int i) {
		return "//div[@id='dc_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr["+i+"]/td[3]";
	}

	/** @locatorType Xpath */
	public static final String ItemCostTable(int i) {
		return "//div[@id='dc_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr["+i+"]/td[9]";
	}
}
