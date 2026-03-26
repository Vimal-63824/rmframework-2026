package commonlocators.supplychainmanagement;

/**
 * @author Sakthi-GWL, Deepan-GWL
 * @company Galaxy Weblinks December 23, 2025
 */
public class SupplierScheduleRequestLocators {

	/** @locatorType XPATH */
	public static final String CreateSSR = "//button[@data-testid='ADD_SSR']";
	/** @locatorType XPATH */
	public static final String SSRHeader = "//h1[text()='Supplier Schedule Request']";
	/** @locatorType XPATH */
	public static final String LocId = "//button[contains(@id,'SSRH_PLNT_LOC_ID_lov_btn')]";
	/** @locatorType XPATH */
	public static final String SelectWareHouse = "//select[contains(@id,'SSRH_STORE_ID')]";
	/** @locatorType XPATH */
	public static final String SSRIframe = "//iframe[@title='Supplier Schedule Request']";
	/** @locatorType XPATH */
	public static final String TickIcon = "//button[@title='Create' and @id='B2566537848175335328']";
	/** @locatorType XPATH */
	public static final String RefInput = "//input[@id='P16151310322_SSRH_REF']";
	/** @locatorType XPATH */
	public static final String AddItemButton = "//button[@title='Add'  and @onclick=\"add_row('LN')\"]";
	/** @locatorType XPATH */
	public static final String ItemTable = "//div[@id='LN_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[2]";
	/** @locatorType XPATH */
	public static final String ItemLovButton = "//div[@id='LN_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[2]//button";
	/** @locatorType XPATH */
	public static final String FirmQuantity = "//div[@id='LN_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[6]";
	/** @locatorType XPATH */
	public static final String FirmQtyInput = "//div[@id='LN_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[6]//input";
	/** @locatorType XPATH */
	public static final String FromDate = "//div[@id='LN_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[8]";
	/** @locatorType XPATH */
	public static final String FromDateInput = "//div[@id='LN_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[8]//input";
	/** @locatorType XPATH */
	public static final String ToDate = "//div[@id='LN_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[9]";
	/** @locatorType XPATH */
	public static final String ToDateInput = "//div[@id='LN_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[9]//input";
	/** @locatorType XPATH */
	public static final String LineSaveButton = "//button[@onclick=\"save_row('LN')\"]";
	/** @locatorType XPATH */
	public static final String HeaderSaveButton = "//button[contains(@onclick,\"request:'SAVE'\")]";
	/** @locatorType XPATH */
	public static final String HeaderPostButton = "//span[text()='Post']/..";
	/** @locatorType XPATH */
	public static final String SSRDocumentNumber = "//span[contains(@id,'SSRH_RQST_NO_DISPLAY')]";
	/** @locatorType XPATH */
	public static final String SSRLovButton = "//button[contains(@id,'DOC_NO_lov_btn')]";
	/** @locatorType XPATH */
	public static final String HeaderCheckBox = "//label[text()='Header']";

	/** @locatorType Xpath */
	public static final String ApprovedStatus(String SSRNumber) {
		return "//a[text()='" + SSRNumber + "']/../../td/div[contains(@style,'COLOR:GREEN')]";
	}

}
