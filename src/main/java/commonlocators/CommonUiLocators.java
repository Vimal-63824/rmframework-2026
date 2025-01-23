package commonlocators;

public class CommonUiLocators {
	// Login Locators
	/** @locatorType Xpath */
	public static final String LoginForm = "//div[contains(@class,'t-Login-region t-Form')]";
	/** @locatorType Xpath */
	public static final String LoginEmailTextBox = "//input[contains(@id,'USERNAME')][@placeholder='Username']";
	/** @locatorType Xpath */
	public static final String LoginPasswordTextBox = "//input[contains(@id,'PASSWORD')][@placeholder='Password']";
	/** @locatorType Xpath */
	public static final String RememberMeLabel = "//label[contains(@for,'REMEMBER')][text()='Remember username']";
	/** @locatorType Xpath */
	public static final String RememberMeInput = "//input[contains(@id,'REMEMBER')]";
	/** @locatorType Xpath */
	public static final String LoginBtn = "//button[@id='loginbtn']/span";
	/** @locatorType Xpath */
	public static final String InfoRememberIcon = "//button[contains(@title,'Remember Username')]";
	/** @locatorType Xpath */
	public static final String CloseInfoRememberIcon = "//button[@title='Close']/span";
	/** @locatorType Xpath */
	public static final String LogoutIcon = "//span[@class='t-Icon fa fa-sign-out']";

	// Other Common Locators
	/** @locatorType Xpath */
	public static final String PageHeaderText = "//div[@class='t-Header-logo']//span";
	/** @locatorType Xpath */
	public static final String LeftSideAddIcon = "//button[contains(@title,'Add')]";
	/** @locatorType Xpath */
	public static final String LeftSidePendingIcon = "//button[contains(@class,'warning')]";
	/** @locatorType Xpath */
	public static final String CreateTickIcon = "//span[contains(@class,'check')]/parent::button[contains(@title,'Create')]";
	/** @locatorType Xpath */
	public static final String PostToastMessage = "//h6[@class='succ_msg']";
	/** @locatorType Xpath */
	public static final String searchBar = "//input[@aria-label='Search']";
	/** @locatorType Xpath */
	public static final String FailedNotifyMessage = "//div[contains(@class,'Notification--error')]";
	/** @locatorType Xpath */
	public static final String ToastCloseIcon = "//button[@title='Close']";
	/** @locatorType Xpath */
	public static final String EntryTableRefreshCellImage = "//span[contains(@class,'u-Processing-spinner')]";
	/** @locatorType Xpath */
	public static final String LeftNavigationBar = "//button[@id='t_Button_navControl']";
	/** @locatorType Xpath */
	public static final String SupplyChainTab = "//span[text()='Supply Chain']/ancestor::li";
	/** @locatorType Xpath */
	public static final String PurchaseTab = "//span[text()='Supply Chain']/ancestor::li/*/li[1]";
	/** @locatorType Xpath */
	public static final String PurchaseTransactionTab = "//span[text()='Supply Chain']/ancestor::li/*/li[1]/*/li[2]";
	/** @locatorType Xpath */
	public static final String PurchaseOrderTab = "//span[text()='Supply Chain']/ancestor::li/*/li[1]/*/li[2]/*/li[9]";
	/** @locatorType Xpath */
	public static final String PurchaseRequestTab = "//span[text()='Supply Chain']/ancestor::li/*/li[1]/*/li[2]/*/li[6]";

	/** @locatorType Xpath */
	public static String convertXPath(String text) {
		return "(//*[text()='" + text + "'])[1]";
	}
	
	/**
	 * first commit by harikrishna 
	 */

}
