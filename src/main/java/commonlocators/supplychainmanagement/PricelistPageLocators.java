package commonlocators.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
public class PricelistPageLocators {
	/** @locatorType Xpath */
	public static final String AddPriceList = "//button[contains(@id,'B1782709203889982539')]";
	/** @locatorType Xpath */
	public static final String FindSearchButton = "//button[contains(@id,'B1453500858078283101')]";
	/** @locatorType Xpath */
	public static final String LocationLov = "//button[contains(@id,'P1615131025212_PSPL_PLNT_LOC_ID_lov_btn')]";
	/** @locatorType Xpath */
	public static final String LocationSearch = "//input[@class='a-PopupLOV-search apex-item-text']";
	/** @locatorType Xpath */
	public static final String SuppliernameLov = "//button[contains(@id,'P1615131025212_PSPL_SUPLR_ID_lov_btn')]";
	/** @locatorType Xpath */
	public static final String ItemLov = "//button[contains(@id,'P1615131025212_PROD_ID_lov_btn')]";
	/** @locatorType Xpath */
	public static final String ItemSearch = "//div[@class='a-PopupLOV-searchBar a-PopupLOV--incremental']";
	/** @locatorType Xpath */
	public static final String PriceField = "(//input[contains(@id,'P1615131025212_PSPL_UNIT_COST')])[1]";
	public static final String PRICE = "100";
	/** @locatorType Xpath */
	public static final String EffFrom = "//input[@id='P1615131025212_PSPL_EFF_DATE_FROM_input']";
	public static final String FromDate = "06-02-2025";
	/** @locatorType Xpath */
	public static final String EffTo = "//input[@id='P1615131025212_PSPL_EFF_DATE_TO_input']";
	public static final String ToDate = "06-03-2025";
	/** @locatorType Xpath */
	public static final String FindCreateButton = "//button[contains(@id,'B1783345246822368803')]";
	/** @locatorType Xpath */
	public static final String DiscountField = "//div[@id='price_ig_grid_vc']/*[3]/*[3]/table/tbody/tr/td[14]";
	public static final String Discountinput = "//input[@id='C3685113618006001624']";
	public static final String Discount = "2";
	/** @locatorType Xpath */
	public static final String SAVEBtn = "//button[contains(@id,'B3685651971804190870')]";
	/** @locatorType Xpath */
	public static final String ActiveButton = "//td[contains(@class,'a-GV-cell u-tC is-readonly')]";
	/** @locatorType Xpath */
	public static final String PriceListWorkflowFrame = "//iframe[@title='Workflow Approval']";
	/** @locatorType Xpath */
	public static final String PricelistForwardlov = "//*[@id='P236131090_FWD_PERSON_lov_btn']";
	/** @locatorType Xpath */
	public static final String PricelistworkflowOK = "//button[@id='B1612675370193662596']/span";

	/** @locatorType Xpath */
	public static final String Pricelist_HeaderLoc = "//td[@class='a-GV-cell u-tS is-readonly']";
	/** @locatorType Xpath */
	public static final String Pricelist_HeaderSupplier = "//td[@class='a-GV-cell u-tS'][3]";
	/** @locatorType Xpath */
	public static final String Pricelist_HeaderItem = "//td[@class='a-GV-cell u-tS'][4]";
	/** @locatorType Xpath */
	public static final String PricelistDocNum = "//div[@id='price_ig_grid_vc']/*[3]/*[3]/table/tbody/tr/td[4]";
	/** @locatorType Xpath */
	public static final String Successmsg = "//*[@id=\"t_Alert_Success\"]/div/div[2]/div/h6";

	public static final String StopAlert = "//li[@class='a-Notification-item htmldbStdErr']";

	/** @locatorType Xpath */
	public static final String PRLISTDetailsInfoBtn(String PRLISTNum) {
		return "//td[contains(text(),'" + PRLISTNum + "')]/../td[3]/*";
	}

	/** @locatorType Xpath */
	public static final String PRLISTSelectCheckBox(String PRLISTNum) {
		return "//td[contains(text(),'" + PRLISTNum + "')]/../td[1]/*";
	}

}
