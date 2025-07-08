package commonlocators.supplychainmanagement;

public class SalesCatalogueLocators {
	/** @locatorType Xpath */
	public static final String CataloguePriceButton = "//span[text()='Catalog Price']";
	/** @locatorType Xpath */
	public static final String AddButtonCatalogue = "//span[@class='t-Icon fa fa-plus']";
	/** @locatorType Xpath */
	public static final String CatalogueItemLov = "//BUTTON[@id='C1129614657095049809_lov_btn']";
	/** @locatorType Xpath */
	public static final String EffToDateClick = "//input[@id='C1129620739968049819_input']";
	/** @locatorType Xpath */
	public static final String Savebutton = "//button[@id='B1253631565513396533']";
	/** @locatorType Xpath */
	public static final String PostButton = "#sales_price_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr.a-GV-row.is-selected > td:nth-child(15) > a";
	/** @locatorType Xpath */
	public static final String CatalogueItemField = "//*[@id=\"sales_price_ig_grid_vc\"]/div[2]/div[3]/table/tbody/tr[1]/td[2]";
	/** @locatorType Xpath */
	public static final String CatalogueField = "//*[@id='sales_price_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[6]";
	/** @locatorType Xpath */
	public static final String CatalogueFieldLov = "//button[@id='C1129623687949049827_lov_btn']";
	/** @locatorType Xpath */
	public static final String CurcyField = "//*[@id='sales_price_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[7]";
	/** @locatorType Xpath */
	public static final String CurcyFieldLov = "//button[@id='C1129621731141049824_lov_btn']";
	/** @locatorType Xpath */
	public static final String PriceField = "//input[@data-testid='Cataog_Price']";
	/** @locatorType Xpath */
	public static final String MinDiscInsert = "//input[@data-testid='SCTLN_DISC_PCT_MIN']";
	/** @locatorType Xpath */
	public static final String MaxDiscInsert = "//input[@data-testid='SCTLN_DISC_PCT_MAX']";
	/** @locatorType Xpath */
	public static final String EffFromDateClick = "//input[@id='C1129619691772049818_input']";
	/** @locatorType Xpath */
	public static final String SearchButton = "//button[@id='B1720968400321831118']";
	/** @locatorType Xpath */
	public static final String DeleteButton = "#sales_price_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr > td:nth-child(18) > a";
	/** @locatorType Xpath */
	public static final String DocNODb = "//*[@id='sales_price_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[1]";
	/** @locatorType Xpath */
	public static final String ItemsIdDb = "//*[@id='sales_price_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[2]";
	/** @locatorType Xpath */
	public static final String ItemsDescDb = "//*[@id='sales_price_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[4]";
	/** @locatorType Xpath */
	public static final String SalesUOMdB = "//*[@id='sales_price_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[5]";
	/** @locatorType Xpath */
	public static final String CatalogueDb = "//*[@id='sales_price_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[6]";
	/** @locatorType Xpath */
	public static final String CurcyDb = "//*[@id='sales_price_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[7]";
	/** @locatorType Xpath */
	public static final String SalesPrice = "//*[@id='sales_price_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[9]";
	/** @locatorType Xpath */
	public static final String MinDiscPercentDb = "//*[@id='sales_price_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[10]";
	/** @locatorType Xpath */
	public static final String MaxDiscPercentDb = "//*[@id='sales_price_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[11]";
	/** @locatorType Xpath */
	public static final String EffFromDateDb = "//*[@id='sales_price_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[12]";
	/** @locatorType Xpath */
	public static final String EffToDateDb = "//*[@id='sales_price_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[13]";
	/** @locatorType Xpath */
	public static final String StatusDb = "//*[@id='sales_price_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[1]/td[14]";
	/** @locatorType Xpath */
	public static final String PriceFieldClick = "//*[@id='sales_price_ig_grid_vc']//tr[1]/td[9]";

	/** @locatorType Xpath */
	public static final String CatalogueItemField(int i) {
		return "//*[@id='sales_price_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[2]";
	}

	/** @locatorType Xpath */
	public static final String PriceFieldClick(int i) {
		return "//*[@id='sales_price_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[ " + i + "]/td[9]";
	}

	/** @locatorType Xpath */
	public static final String CurcyFieldIn(int i) {
		return "//*[@id='sales_price_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + " ]/td[7] ";
	}

	/** @locatorType Xpath */
	public static final String MinDiscountPercentField(int i) {
		return "//*[@id=\'sales_price_ig_grid_vc\']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[10]";
	}

	/** @locatorType Xpath */
	public static final String MaxDiscountPercentField(int i) {
		return "//*[@id=\'sales_price_ig_grid_vc\']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[11] ";
	}

	/** @locatorType Xpath */
	public static final String EffitiveFromDateField(int i) {
		return " //*[@id='sales_price_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[12] ";
	}

	/** @locatorType Xpath */
	public static final String EffToDateField(int i) {
		return " //*[@id='sales_price_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[13]";
	}

	/** @locatorType Xpath */
	public static final String DocumentNoField(int i) {
		return "//*[@id='sales_price_ig_grid_vc']/div[2]/div[3]/table/tbody/tr[" + i + "]/td[1] ";
	}
}
