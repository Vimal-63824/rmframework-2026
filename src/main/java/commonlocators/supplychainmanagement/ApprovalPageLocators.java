package commonlocators.supplychainmanagement;

/**
 * @author Sakthi-GWL
 */

public class ApprovalPageLocators {

	/** @locatorType Xpath */
	public static final String UserNotifyIcon = "//span[@class='t-Icon fa fa-bell']/parent::button";
	/** @locatorType Xpath */
	public static final String ApprovalNotifyOption = "//a[@class='a-Menu-label'][contains(text(),'Approvals')]";
	/** @locatorType Xpath */
	public static final String DocumentsTypeDropDown = "//select[contains(@class,'selectlist')]";
	/** @locatorType Xpath */
	public static final String SearchBoxContainer = "//div[@class='a-IRR-searchFieldContainer']";
	/** @locatorType Xpath */
	public static final String SearchBox = "//input[@id='PEND_search_field']";
	/** @locatorType Xpath */
	public static final String SearchBoxGoBtn = "//button[@id='PEND_search_button']";
	/** @locatorType Xpath */
	public static final String ProcessButton = "(//span[text()='Process']/parent::button)[1]";
	/** @locatorType Xpath */
	public static final String ApproveRadioBtn = "//label[text()='Approve']";
	/** @locatorType Xpath */
	public static final String ToastCloseIcon = "//button[@title='Close']";
	/** @locatorType Xpath */
	public static final String SearchTableResult = " //table[@id='26244491392720942738']/tbody/tr[2]/td[1]";
	/** @locatorType Id */
	public static final String CURRENCY = "P161513106044_POH_CURRENCY_DISPLAY";
	/** @locatorType Id */
	public static final String POTYPE = "P161513106044_POH_TYPE_DISPLAY";
	/** @locatorType Xpath */
	public static final String UOMTYPE = "(//*[@id='ig_po_lineitems_ig_grid_vc']/div/div/table[1])[2]/tbody/tr/td[5]";
	/** @locatorType Xpath */
	public static final String BackButton = "//button[@id='B720942771947006229']";
	/** @locatorType Xpath */
	public static final String ApproveProcessButton = "//button[@id='B6042422688967462464']";

	/** @locatorType Xpath */
	public static final String PODetailsInfoBtn(String PONumber) {
		return "//td[contains(text(),'" + PONumber + "')]/../td[3]/*";
	}

	/** @locatorType Xpath */
	public static final String POSelectCheckBox(String PONumber) {
		return "//td[contains(text(),'" + PONumber + "')]/../td[1]/*";
	}
}
