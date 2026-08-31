package commonlocators.supplychainmanagement;

public class SalesPriceListLocators {

	/** @locatorType Xpath */
	public static final String PRICEStdCatalogNode = "//a[text()='Price List/Standard/Catalog']";
	/** @locatorType Xpath */
	public static final String AddButton = "//button[@data-testid='Add_Button']";
	/** @locatorType Xpath */
	public static final String PriceList = "//span[text()='Price List']";
	/** @locatorType Xpath */
	public static final String PriceListSingle = "//button[@id='B3141129564108607806']";
	/** @locatorType Xpath */
	public static final String PriceListMulti = "//span[text()='Price List Multi']";
	/** @locatorType Xpath */
	public static final String PlusButton = "//button[@data-testid='add']";
	/** @locatorType Xpath */
	public static final String MultiAddButton = "//button[@title='Add']";
	/** @locatorType Xpath */
	public static final String LocationLovButton = "//INPUT[@data-testid='CPL_PLNT_LOC_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PriceListPfx = "//INPUT[@data-testid='CPL_DOC_PFX']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String PricePfxFiled = "//*[@id='SO_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[2]";
	/** @locatorType Xpath */
	public static final String CustomerIdField = "//*[@id='SO_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[4]";
	/** @locatorType Xpath */
	public static final String CustomerFieldLov = "//INPUT[@data-testid='CPL_CUST_ID']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String ItemField = "//*[@id='SO_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[7]";
	/** @locatorType Xpath */
	public static final String ItemFieldLov = "//INPUT[@data-testid='PROD_DUM']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SalesPrice = "//input[@data-testid='CPL_SALES_PRICE']";
	/** @locatorType Xpath */
	public static final String SalesDiscPercent = " //input[@data-testid='CPL_DISC_PCT']";
	/** @locatorType Xpath */
	public static final String EffiticeFrom = "//input[@id='C8574962564039875189_input']";
	/** @locatorType Xpath */
	public static final String EffiticeTo = "//input[@id='C8574963592556875189_input']";

	/** @locatorType Xpath */
	public static final String SalesPriceFields(int i) {
		return "//*[@id='SO_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[12] ";
	}

	/** @locatorType Xpath */
	public static final String SalesDiscFields(int i) {
		return " //*[@id=\'SO_ig_grid_vc\']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[13] ";

	}

	/** @locatorType Xpath */
	public static final String EffDateField(int i) {
		return "//*[@id='SO_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[16]";

	}

	/** @locatorType Xpath */
	public static final String EffDateTo(int i) {
		return "//*[@id='SO_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[17]";

	}

	/** @locatorType Xpath */
	public static final String SaveButton = "//button[@title='Save']";
	/** @locatorType Xpath */

	/** @locatorType Xpath */
	public static final String SearchIcon = "//button[@data-testid='Search']";

	/** @locatorType Xpath */
	public static final String Documentno(int i) {
		return "//*[@id='SO_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[3]";
	}

	/** @locatorType Xpath */
	public static final String postButton(int i) {
		return " //*[@id='SO_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[13] ";

	}

	/** @locatorType Xpath */
	public static final String postBtnField = "//*[@id='SO_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[13]";
	/** @locatorType Xpath */
	public static final String postBtnn = "//*[@id='SO_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[13]/a/span";
	/** @locatorType Xpath */
	public static final String Docno = "//*[@id='C6925606525131363033']";
	/** @locatorType Xpath */
	public static final String SearchButtoninFindPage = "//button[@data-testid='Find']";
	/** @locatorType Xpath */
	public static final String SearchBoxFind = "//input[@id='PCS_search_field']";
	/** @locatorType Xpath */
	public static final String GoButton = "//button[@id='PCS_search_button']";
	/** @locatorType Xpath */
	public static final String PR_Post_Btn = "//span[@class='fa fa-send']";
	/** @locatorType Xpath */
	public static final String PR_Workflow_Btn = "//iframe[@title='Workflow Approval']";
	/** @locatorType Xpath */
	public static final String unitLovFindPage = "//button[@id='P1915131012_UNIT_lov_btn'] ";
	/** @locatorType Xpath */
	public static final String CustomerLovFindPage = "//button[@id='P1915131012_CUST_ID_lov_btn']";
	/** @locatorType Xpath */
	public static final String ItemLovFindPage = "//button[@id='P1915131012_ITEM_lov_btn']";
	/** @locatorType Xpath */
	public static final String CatalogueFlagFindPage = "//label[@id='P1915131012_CATALOG_PRICE_LABEL']";
	/** @locatorType Xpath */
	public static final String StandardFlagFindPage = "//label[@id='P1915131012_STANDARD_PRICE_LABEL']";
	/** @locatorType Xpath */
	public static final String PriceListFlagFindPage = "//label[@id='P1915131012_PRICE_LIST_LABEL']";
	/** @locatorType Xpath */
	public static final String PriceListHyperLink = "(//table[@id='1253627943780396496_orig']//tr/td[4])[1]";
	/** @locatorType Xpath */
	public static final String PriceListHyperLink2 = "//a[text()='Price List']";
	/** @locatorType Xpath */
	public static final String AmdendButton = "//*[@id='SO_ig_grid_vc']//div[3]/table/tbody/tr/td[14]/a";
	/** @locatorType Xpath */
	public static final String AmdendButton2 = "#SO_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr > td:nth-child(15) > a";
	/** @locatorType Xpath */
	public static final String CloseDataAmdend = "//*[@id='P3670210201_DA_AMD_DATE_input']";
	/** @locatorType Xpath */
	public static final String adjustmentFrame = "//*[@id='apex_dialog_1']/iframe";
	/** @locatorType Xpath */
	public static final String okButton = "//button[@data-testid='OK_Amnt']";
	/** @locatorType Xpath */
	public static final String CancelButton = "#SO_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr > td:nth-child(21) > a";
	/** @locatorType Xpath */
	public static final String searchBoxFindPage = "(//span[text()='Search'])[1]";
	/** @locatorType Xpath */
	public static final String LocutionIdField = "//*[@id='SO_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[1]";
	/** @locatorType Xpath */
	public static final String PriceListPfxField = "//*[@id='SO_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[2]";
	/** @Locator XPATH */
	public static final String CustomerIdFieldDb = "//*[@id='SO_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[4]";
	/** @Locator XPATH */
	public static final String CustomerDescDb = "//*[@id='SO_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[5]";
	/** @Locator XPATH */
	public static final String ItemIdDb = "//*[@id='SO_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[7]";
	/** @Locator XPATH */
	public static final String SalesUOMDb = "//*[@id='SO_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[11]";
	/** @Locator XPATH */
	public static final String SalesPriceDb = "//*[@id='SO_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[12]";
	/** @Locator XPATH */
	public static final String DiscPercentDb = "//*[@id='SO_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[13]";
	/** @Locator XPATH */
	public static final String AmdNoDb = "//*[@id='SO_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[19]";
	/** @Locator XPATH */
	public static final String PriceListSingle1 = "(//span[text()='Price List'])[2]" ;
}
