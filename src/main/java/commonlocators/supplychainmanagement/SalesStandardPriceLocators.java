package commonlocators.supplychainmanagement;

public class SalesStandardPriceLocators {

	/** @locatorType Xpath */
	public static final String Standardprice = "//span[text()='Standard Price']";
	/** @locatorType Xpath */
	public static final String AddButton = "//BUTTON[@data-testid='AddButton']";
	/** @locatorType Xpath */
	public static final String LocutionIdClick = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[1]";
	/** @locatorType Xpath */
	public static final String LocutionIdLov = "//INPUT[@data-testid='LocutionIdClick']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String UnitDesc = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[2]";
	/** @locatorType Xpath */
	public static final String ItemIdLov = "//INPUT[@data-testid='ItemdIdField']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String ItemdIdField = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[3]";
	/** @locatorType Xpath */
	public static final String CurcyField = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[6]";
	/** @locatorType Xpath */
	public static final String CurcyLov = "//INPUT[@data-testid='CurcyField']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String MapPriceInput = "//input[@data-testid='SSP_MAP_PRICE']";
	/** @locatorType Xpath */
	public static final String MapPriceField = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[10]";
	/** @locatorType Xpath */
	public static final String MinPriceField = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[13]";
	/** @locatorType Xpath */
	public static final String MaxDiscField = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[14]";
	/** @locatorType Xpath */
	public static final String MaxDiscInput = "//input[@data-testid='MaxDiscField']";
	/** @locatorType Xpath */
	public static final String PriceClassClick = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[16]";
	/** @locatorType Xpath */
	public static final String PriceClassLov = "//input[@data-testid='PriceClassClick']/following-sibling::button";
	/** @locatorType Xpath */
	public static final String SaveButton = "//button[@data-testid='SaveButton']";

	/** @locatorType Xpath */
	public static final String LocutionIdClickField(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[1] ";
	}

	/** @locatorType Xpath */
	public static final String ItemIdField(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[3]  ";
	}

	/** @locatorType Xpath */
	public static final String MapPriceField(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[10] ";
	}

	/** @locatorType Xpath */
	public static final String LandingCostField(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[11] ";
	}

	/** @locatorType Xpath */
	public static final String LandingCostInput = "//input[@data-testid='Landed Cost']";

	/** @locatorType Xpath */
	public static final String Min_Disc(int i) {
		return "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[" + i + "]/td[13]";
	}

	/** @locatorType Xpath */
	public static final String min_DiscInput = "//input[@data-testid='MinPriceField']";
	/** @locatorType Xpath */
	public static final String ActiveButton = "(//span[@title='Active'])[1]";
	/** @locatorType Xpath */
	public static final String CancelButton = "#ig_line_ig_grid_vc > div.a-GV-bdy > div.a-GV-w-scroll > table > tbody > tr:nth-child(5) > td:nth-child(19) > a";
	/** @locatorType Xpath */
	public static final String CancelBtn = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr/td[19]/a/span";
	/** @locatorType Xpath */
	public static final String LocIdDb = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[1]";
	/** @locatorType Xpath */
	public static final String UnitIdDb = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[2]";
	/** @locatorType Xpath */
	public static final String ItemsIdDb = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[3]";
	/** @locatorType Xpath */
	public static final String ItemDesDb = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[5]";
	/** @locatorType Xpath */
	public static final String CurcyFieldDb = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[6]";
	/** @locatorType Xpath */
	public static final String PriceUOM = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[7]";
	/** @locatorType Xpath */
	public static final String EffFromDateDb = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[8]";
	/** @locatorType Xpath */
	public static final String EffToDateDb = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[9]";
	/** @locatorType Xpath */
	public static final String MapFieldDb = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[10]";
	/** @locatorType Xpath */
	public static final String LandCostDb = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[11]";
	/** @locatorType Xpath */
	public static final String SellingCostFieldDb = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[12]";
	/** @locatorType Xpath */
	public static final String MinDiscPctDb = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[13]";
	/** @locatorType Xpath */
	public static final String MaxDiscPctDb = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[14]";
	/** @locatorType Xpath */
	public static final String PriceClassDb = "//*[@id='ig_line_ig_grid_vc']/div[3]/div[3]/table/tbody/tr[1]/td[16]";
	/** @locatorType Xpath */
	public static final String SearchButton = "//button[@data-testid='Search']";
	/** @locatorType Xpath */
	public static final String ItemInputField = "//input[@data-testid='P1915131012_ITEM']";
	/** @locatorType Xpath */
	public static final String SearchFindPage = "//button[@data-testid='Find']";
	/** @locatorType Xpath */
	public static final String StandardHyderLinkinFindPage = " //a[text()='Standard']" ;

}
