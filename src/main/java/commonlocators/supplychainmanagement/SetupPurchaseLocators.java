package commonlocators.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks May 14, 2025
 */
public class SetupPurchaseLocators {

	/** @locatorType XPATH */
	public static final String AddBuyerButton = "//button[@data-testid='BTN_ADD_BUYER']";
	/** @locatorType XPATH */
	public static final String TickBuyerButton = "//button[@data-testid='BTN_SAVE_BUYER']";
	/** @locatorType XPATH */
	public static final String BuyerNameTable = "//div[@id='ig_line_buy_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[1]";
	/** @locatorType XPATH */
	public static final String EmployeeIdTable = "//div[@id='ig_line_buy_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[2]";
	/** @locatorType XPATH */
	public static final String EffFromTable = "//div[@id='ig_line_buy_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[4]";
	/** @locatorType XPATH */
	public static final String BuyerTable = "//div[@id='ig_line_buy_ig_grid_vc']/div[2]/div[3]/table/thead/tr/th[3]";
	/** @locatorType XPATH */
	public static final String EffToIdTable = "//div[@id='ig_line_buy_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[5]";
	/** @locatorType XPATH */
	public static final String BuyerNameInput = "//input[@data-testid='BUYER_ID']";
	/** @locatorType XPATH */
	public static final String EmployeeLovButton = "//input[@data-testid='BUYER_EMP_ID']/following-sibling::button";
	/** @locatorType XPATH */
	public static final String EffFromToday = "//a-date-picker[@data-testid='BUYER_EFF_FROM']/input";
	/** @locatorType XPATH */
	public static final String TableTop = "//div[@id='C']/table/tbody/tr/td[1]";
	/** @locatorType XPATH */
	public static final String EffToInput = "//a-date-picker[@data-testid='BUYER_EFF_TO']/input";
	/** @locatorType XPATH */
	public static final String EffToToday = "//div[@role='region']/button[text()='Today']";
	/** @locatorType XPATH */
	public static final String BuyerSearch = "//input[@aria-label='Search']";
	/** @locatorType XPATH */
	public static final String UpdateButton = "//div[@id='ig_line_buy_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[9]/a";
	/** @locatorType XPATH */
	public static final String AdminCheckbox = "//div[@id='ig_line_buy_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[7]/div/input";
	/** @locatorType XPATH */
	public static final String ActiveCheckbox = "//div[@id='ig_line_buy_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[8]/div/input";
	/** @locatorType XPATH */
	public static final String RemoveFilter = "//button[@title='Remove Filter']";
	/** @locatorType XPATH */
	public static final String FirstBuyerName = "//div[@id='ig_line_buy_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[1]";
	/** @locatorType XPATH */
	public static final String UpdateHeader = "//h1[text()='Update']";
	/** @locatorType XPATH */
	public static final String UpdateIframe = "//iframe[@title='Update']";
	/** @locatorType XPATH */
	public static final String EmployeeNameSelect = "//select[@id='P36701802101_EMPLOYEE']";
	/** @locatorType XPATH */
	public static final String EmployeeEffFrom = "//input[@id='P36701802101_EFF_FROM_input']";
	/** @locatorType XPATH */
	public static final String EmployeeEffTo = "//input[@id='P36701802101_EFF_TO_input']";
	/** @locatorType XPATH */
	public static final String OkButton = "//span[text()='OK']/..";

	/** @locatorType XPATH */
	public static final String BuyerName(String Name) {
		return "//a[text()='" + Name + "']";
	}

}
