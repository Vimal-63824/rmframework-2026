package commonfunctions.supplychainmanagement;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks May 20, 2025
 */
import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.ItemDetailsHandler;
import commonfunctions.VariableHandler;
import commonfunctions.payroll.CommonPayrollFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.PricelistPageLocators;
import commonlocators.supplychainmanagement.SalesPriceListLocators;
import commonlocators.supplychainmanagement.SalesStandardPriceLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class SalesStandardPriceFunctions {

	private final long[] Name = null;
	private boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	private ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	private static ItemDetailsHandler itemDetailHandler = new ItemDetailsHandler();
	private static OpenSalesOrderFunctions openso = new OpenSalesOrderFunctions();
	private static DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();
	private static OpenSoAmdFunctions opensoamd = new OpenSoAmdFunctions();
	private static CustomerItemsFunctions cusItems = new CustomerItemsFunctions();
	private CommonPayrollFunctions commonPayrollFunc = new CommonPayrollFunctions();
	private static SalesPriceListFunctions SalesPriceList = new SalesPriceListFunctions();
	public static PricelistPageFunctions priceListPR = new PricelistPageFunctions();
	public static PricelistPageLocators priceListLoc = new PricelistPageLocators();

	/**
	 * Creates Std Price With Similar Stoock & Customer Domestic Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	public void CreateStandardPriceWithSimilarStockandCustomerUOMDomesticCustomer() {

		String Map_Value = RandomStringUtils.randomNumeric(3);
		if (Map_Value.equals("000")) {
			Map_Value = "100";
		}
		String Landing_Cost = RandomStringUtils.randomNumeric(1);
		if (Landing_Cost.equals("0")) {
			Landing_Cost = "10";
		}

		String Min_Disc_Value = RandomStringUtils.randomNumeric(1);
		if (Min_Disc_Value.equals("0")) {
			Min_Disc_Value = "10";
		}

		String Max_Disc_Value = RandomStringUtils.randomNumeric(2);
		if (Max_Disc_Value.equals("00")) {
			Max_Disc_Value = "10";
		}

		cusItems.createStandardPricewithSimilarStockandCustomerUOMDomesticCustomer();

		commonFunc.logoutUser();

		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePriceList_StdPrice_CataloguePrice();
		SalesPriceList.clickAddButton();
		clickStandardPriceButton();
		clickAddbutton();
		clickLocutionField(commonFunc.getDataFromTestDataSheet("Loc_100"));
		insertItemIdStandardPrice();
		insertCurcyField(commonFunc.getDataFromTestDataSheet("CatalogueCurcy"));
		insertMapPriceStdPrice(Map_Value);
		insertLandingCost(Landing_Cost);
		insertMaxDisc(Max_Disc_Value);
		insertPriceClass(commonFunc.getDataFromTestDataSheet("priceClassName"));
		clickSaveButton();
		StdPriceDbValidation();
		clickActiveButton();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
	}

	/**
	 * Creates Std Price With Different Stock & Customer UOM Domestic Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	public void CreateStandardPriceWithDifferentStockandCustomerUOMDomesticCustomer() {

		String Map_Value = RandomStringUtils.randomNumeric(3);
		if (Map_Value.equals("000")) {
			Map_Value = "100";
		}
		String Landing_Cost = RandomStringUtils.randomNumeric(1);
		if (Landing_Cost.equals("0")) {
			Landing_Cost = "10";
		}

		String Min_Disc_Value = RandomStringUtils.randomNumeric(1);
		if (Min_Disc_Value.equals("0")) {
			Min_Disc_Value = "10";
		}

		String Max_Disc_Value = RandomStringUtils.randomNumeric(2);
		if (Max_Disc_Value.equals("00")) {
			Max_Disc_Value = "10";
		}

		cusItems.createStandardPricewithDiiferentSimilarStockandCustomerUOMDomesticCustomer();

		commonFunc.logoutUser();

		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePriceList_StdPrice_CataloguePrice();
		SalesPriceList.clickAddButton();
		clickStandardPriceButton();
		clickAddbutton();
		clickLocutionField(commonFunc.getDataFromTestDataSheet("Loc_100"));
		insertItemIdStandardPrice();
		insertCurcyField(commonFunc.getDataFromTestDataSheet("CatalogueCurcy"));
		insertMapPriceStdPrice(Map_Value);
		insertLandingCost(Landing_Cost);
		insertMaxDisc(Max_Disc_Value);
		insertPriceClass(commonFunc.getDataFromTestDataSheet("priceClassName"));
		clickSaveButton();
		webDB.navigateToRefresh();
		commonFunc.checkRefreshCellsDisappear();
		StdPriceDbValidation();

		clickActiveButton();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
	}

	/**
	 * Creates Std Price With Similar Stock & Customer UOM Export Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	public void CreateStandardPricewithSimilarStockandCustomerUOMExportCustomer() {

		String Map_Value = RandomStringUtils.randomNumeric(3);
		if (Map_Value.equals("000")) {
			Map_Value = "100";
		}
		String Landing_Cost = RandomStringUtils.randomNumeric(1);
		if (Landing_Cost.equals("0")) {
			Landing_Cost = "0";
		}

		String Min_Disc_Value = RandomStringUtils.randomNumeric(1);
		if (Min_Disc_Value.equals("0")) {
			Min_Disc_Value = "10";
		}

		String Max_Disc_Value = RandomStringUtils.randomNumeric(2);
		if (Max_Disc_Value.equals("00")) {
			Max_Disc_Value = "10";
		}

		cusItems.createStandardPricewithSimilarStockandCustomerUOMExportCustomer();

		commonFunc.logoutUser();

		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePriceList_StdPrice_CataloguePrice();
		SalesPriceList.clickAddButton();
		clickStandardPriceButton();
		clickAddbutton();
		clickLocutionField(commonFunc.getDataFromTestDataSheet("Loc_100"));
		insertItemIdStandardPrice();
		insertCurcyField(commonFunc.getDataFromTestDataSheet("CatalogueCurcy"));
		insertMapPriceStdPrice(Map_Value);
		insertLandingCost(Landing_Cost);
		insertMaxDisc(Max_Disc_Value);
		insertPriceClass(commonFunc.getDataFromTestDataSheet("priceClassName"));
		clickSaveButton();
		StdPriceDbValidation();
		clickActiveButton();
		commonFunc.logoutUser();
	}

	/**
	 * Creates Std Price With Different Stock & Customer UOM Export Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	public void CreateStandardPricewithDifferSimilarStockandCustomerUOMExportCustomer() {

		String Map_Value = RandomStringUtils.randomNumeric(3);
		if (Map_Value.equals("000")) {
			Map_Value = "100";
		}
		String Landing_Cost = RandomStringUtils.randomNumeric(1);
		if (Landing_Cost.equals("0")) {
			Landing_Cost = "10";
		}

		String Min_Disc_Value = RandomStringUtils.randomNumeric(1);
		if (Min_Disc_Value.equals("0")) {
			Min_Disc_Value = "10";
		}

		String Max_Disc_Value = RandomStringUtils.randomNumeric(2);
		if (Max_Disc_Value.equals("00")) {
			Max_Disc_Value = "10";
		}

		cusItems.createStandardPricewithDifferentSimilarStockandCustomerUOMExportCustomer();

		commonFunc.logoutUser();

		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePriceList_StdPrice_CataloguePrice();
		SalesPriceList.clickAddButton();
		clickStandardPriceButton();
		clickAddbutton();
		clickLocutionField(commonFunc.getDataFromTestDataSheet("Loc_100"));
		insertItemIdStandardPrice();
		insertCurcyField(commonFunc.getDataFromTestDataSheet("CatalogueCurcy"));
		insertMapPriceStdPrice(Map_Value);
		insertLandingCost(Landing_Cost);
		insertMaxDisc(Max_Disc_Value);
		insertPriceClass(commonFunc.getDataFromTestDataSheet("priceClassName"));
		clickSaveButton();
		StdPriceDbValidation();
		clickActiveButton();
		commonFunc.hardWait(2000);
		commonFunc.logoutUser();
	}

	/**
	 * Create Cancel Std Price
	 * 
	 * @author Kowshik-RMITS
	 */
	public void CancelStandardPrice() {

		String Map_Value = RandomStringUtils.randomNumeric(3);
		if (Map_Value.equals("000")) {
			Map_Value = "100";
		}
		String Landing_Cost = RandomStringUtils.randomNumeric(1);
		if (Landing_Cost.equals("0")) {
			Landing_Cost = "10";
		}

		String Min_Disc_Value = RandomStringUtils.randomNumeric(1);
		if (Min_Disc_Value.equals("0")) {
			Min_Disc_Value = "10";
		}

		String Max_Disc_Value = RandomStringUtils.randomNumeric(2);
		if (Max_Disc_Value.equals("00")) {
			Max_Disc_Value = "10";
		}

		cusItems.createStandardPricewithSimilarStockandCustomerUOMDomesticCustomer();

		commonFunc.logoutUser();

		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePriceList_StdPrice_CataloguePrice();
		SalesPriceList.clickAddButton();
		clickStandardPriceButton();
		clickAddbutton();
		clickLocutionField(commonFunc.getDataFromTestDataSheet("Loc_100"));
		insertItemIdStandardPrice();
		insertCurcyField(commonFunc.getDataFromTestDataSheet("CatalogueCurcy"));
		insertMapPriceStdPrice(Map_Value);
		insertLandingCost(Landing_Cost);
		insertMaxDisc(Max_Disc_Value);
		insertPriceClass(commonFunc.getDataFromTestDataSheet("priceClassName"));
		clickSaveButton();
		StdPriceDbValidation();
		clickCancelButton();
		clickSearchInStandardPrice();
		takenStandardPriceRecordInFindPage();
		commonFunc.logoutUser();

	}

	/**
	 * Create Multiples Std Price
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createsMultiplesItemsStdPrice() {
		createStandardPricewithSamePriceClassAndMultipleItems(commonFunc.getDataFromTestDataSheet("Item_Different_UOM"),
				2);
	}

	/**
	 * Create Multiples Std Price With Different Price Class
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createsMultiplesItemStdPriceDifferentPriceClass() {
		createsMultipesItemDifferPriceClassWithCustomerItems(commonFunc.getDataFromTestDataSheet("Item_Different_UOM"));
		commonFunc.logoutUser();

	}

	/**
	 * To Click Cancel Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickCancelButton() {
		commonFunc.hardWait(7000);
		flag = webDB.waitForElement(SalesStandardPriceLocators.CancelBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesStandardPriceLocators.CancelBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Click Cancel Item Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Click a Search Button In Std Price
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickSearchInStandardPrice() {
		flag = webDB.waitForElement(SalesStandardPriceLocators.SearchButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(SalesStandardPriceLocators.SearchButton, ElementType.Xpath);
			webDB.javaScriptClickAnElement(SalesStandardPriceLocators.SearchButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sucessfully Clicked a Search Button");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Taken Standard Price Item In Find Page
	 * 
	 * @author Kowshik-RMITS
	 */
	public void takenStandardPriceRecordInFindPage() {
		flag = webDB.waitForElement(SalesPriceListLocators.PriceListFlagFindPage, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesPriceListLocators.PriceListFlagFindPage, ElementType.Xpath);
			flag = webDB.waitForElement(SalesPriceListLocators.CatalogueFlagFindPage, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesPriceListLocators.CatalogueFlagFindPage, ElementType.Xpath);
				flag = webDB.waitForElement(SalesStandardPriceLocators.ItemInputField, ElementType.Xpath);
				if (flag) {
					webDB.sendTextToAnElement(SalesStandardPriceLocators.ItemInputField, VariableHandler.getItemName(),
							ElementType.Xpath);
					flag = webDB.waitForElement(SalesStandardPriceLocators.SearchFindPage, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SalesStandardPriceLocators.SearchFindPage, ElementType.Xpath);
					}
				}
			}

		}
		assertTrue(flag);
	}

	/**
	 * Click Standard Price
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickStandardPriceButton() {
		flag = webDB.waitForElement(SalesStandardPriceLocators.Standardprice, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesStandardPriceLocators.Standardprice, ElementType.Xpath);
			flag = webDB.waitForElement(SalesStandardPriceLocators.AddButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Clicked a Standard Price");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Click Add Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickAddbutton() {
		flag = webDB.waitForElement(SalesStandardPriceLocators.AddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesStandardPriceLocators.AddButton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesStandardPriceLocators.LocutionIdClick, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Clicked a AddButton");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Click Locution Id
	 * 
	 * @author Kowshik-RMITS
	 * @param LOCATION100
	 */
	public void clickLocutionField(String L100) {

		flag = webDB.waitForElement(SalesStandardPriceLocators.LocutionIdClick, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SalesStandardPriceLocators.LocutionIdLov, L100);
			if (flag) {
				logs.logging("Info", "Successfully Insert a Locution Id" + L100);
			}
		}
		assertTrue(flag);
	}

	/**
	 * Insert Item Standard Price
	 * 
	 * @author Kowshik-RMIS
	 */
	public void insertItemIdStandardPrice() {

		flag = webDB.waitForElement(SalesStandardPriceLocators.ItemIdField(1), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesStandardPriceLocators.ItemIdField(1), ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(SalesStandardPriceLocators.ItemIdLov,
						VariableHandler.getItemName());
				webDB.waitForElement(SalesStandardPriceLocators.CurcyField, ElementType.Xpath);
			}
		}
		assertTrue(flag);
	}

	/**
	 * Insert Currcy Field
	 * 
	 * @author Kowshik-RMITS
	 * @param CatalogueCurcy
	 */
	public void insertCurcyField(String CatalogueCurcy) {

		flag = webDB.waitForElement(SalesStandardPriceLocators.CurcyField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesStandardPriceLocators.CurcyField, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesStandardPriceLocators.CurcyLov, CatalogueCurcy);
			webDB.pressTabKey();
			webDB.waitForElement(SalesStandardPriceLocators.MapPriceField, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", " Successfully Inserted a Curcy Field " + CatalogueCurcy);
			}
		}
		assertTrue(flag);
	}

	/**
	 * Insert a Map Price In Standard Price
	 * 
	 * @author Kowshik-RMITS
	 * @param Map_Value
	 */
	public void insertMapPriceStdPrice(String Map_Value) {
		webDB.waitForElement(SalesStandardPriceLocators.MapPriceField, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(SalesStandardPriceLocators.MapPriceField, ElementType.Xpath);
			webDB.waitForElement(SalesStandardPriceLocators.MapPriceInput, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(SalesStandardPriceLocators.MapPriceInput, ElementType.Xpath);
				verifyClickAndEnterValueInTableFields(SalesStandardPriceLocators.MapPriceField(1),
						SalesStandardPriceLocators.MapPriceInput, Map_Value);
				if (flag) {
					logs.logging("INFO", "Successfully Insert Map Value" + Map_Value);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Insert Landing Cost
	 * 
	 * @author Kowshik-RMITS
	 * @param Landing_Cost
	 */
	public void insertLandingCost(String Landing_Cost) {
		webDB.waitForElement(SalesStandardPriceLocators.LandingCostField(1), ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(SalesStandardPriceLocators.LandingCostInput, ElementType.Xpath);
			verifyClickAndEnterValueInTableFields(SalesStandardPriceLocators.LandingCostField(1),
					SalesStandardPriceLocators.LandingCostInput, Landing_Cost);
			if (flag) {
				logs.logging("INFO", "Successfully Insert Landing Cost" + Landing_Cost);
			}
		}
		assertTrue(flag);
	}
	
	/**This Methed to used to Click a Search Button in Transaction Page
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickSearchButtoninStdPrice() {
		flag = webDB.waitForElement(SalesStandardPriceLocators.SearchButton, ElementType.Xpath);
		if(flag) {
			webDB.moveToElement(SalesStandardPriceLocators.SearchButton, ElementType.Xpath);
			webDB.javaScriptClickAnElement(SalesStandardPriceLocators.SearchButton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesPriceListLocators.CatalogueFlagFindPage, ElementType.Xpath);
			if(flag) {
				logs.logging("info","Search Button is Clicked");
			}
		}
		assertTrue(flag);
	}
	
	/**This Method is Used to Uncheck Flag in Find Page 
	 * 
	 * @author Kowshik-RMITS
	 */
	public void uncheckaRadioButtonOfCatalogandPriceListFlag() {
		flag = webDB.waitForElement(SalesPriceListLocators.CatalogueFlagFindPage, ElementType.Xpath);
		if(flag) {
			webDB.javaScriptClickAnElement(SalesPriceListLocators.CatalogueFlagFindPage, ElementType.Xpath);
			flag = webDB.waitForElement(SalesPriceListLocators.PriceListFlagFindPage, ElementType.Xpath);
			if(flag) {
				logs.logging("info", "Uncheck a Catalog price flag");
				flag = webDB.waitForElement(SalesPriceListLocators.PriceListFlagFindPage, ElementType.Xpath);
				if(flag) {
					webDB.clickAnElement(SalesPriceListLocators.PriceListFlagFindPage, ElementType.Xpath);
					flag =webDB.waitForElement(SalesStandardPriceLocators.ItemInputField, ElementType.Xpath);
					if(flag) {
						logs.logging("Info", "Uncheck a Price List Flag ");
					}
				}
			}
		}
		assertTrue(flag);
	}
	
	/**This Is Method is Used to Search Std price Item
	 * 
	 * @author Kowshik-RMITS
	 */
	public void passingStdPriceItemToBeFillterInFindPage() {
		flag =webDB.waitForElement(SalesStandardPriceLocators.ItemInputField, ElementType.Xpath);
		if(flag) {
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(SalesPriceListLocators.ItemLovFindPage,
						VariableHandler.getItemName());
				flag = webDB.waitForElement(SalesPriceListLocators.searchBoxFindPage, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SalesPriceListLocators.SearchButtoninFindPage, ElementType.Xpath);
					logs.logging("INFO", "Successfully Clicked SearchBox");
				}
			}
		}
		assertTrue(flag);
	}
	
	/**Click Standard Hyder Link
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickHyderLinkInFindPage() {
		flag = webDB.waitForElement(SalesStandardPriceLocators.StandardHyderLinkinFindPage, ElementType.Xpath );
		if(flag) {
			webDB.javaScriptClickAnElement(SalesStandardPriceLocators.StandardHyderLinkinFindPage, ElementType.Xpath);
			flag = webDB.waitForElement(SalesStandardPriceLocators.ActiveButton, ElementType.Xpath);
			if(flag) {
				logs.logging("INFO", "Successfully Clicked Standard Hyder Link");
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna -RMITS This Method Is Used To Click the Table Field And
	 *         Pass The Input Value
	 * 
	 * @param Field
	 * @param InputField
	 * @param InputValue
	 */
	public void verifyClickAndEnterValueInTableFields(String Field, String InputField, String InputValue) {
		flag = webDB.waitForElement(Field, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(Field, ElementType.Xpath);
			logs.logging("INFO", "Table Input Field is Clicked");
			webDB.clearTextField(InputField, ElementType.Xpath);
			logs.logging("INFO", "Input text Is Cleared");
			flag = webDB.waitForElement(Field, ElementType.Xpath);
			if (flag) {
				webDB.doubleClickAnElement(Field, ElementType.Xpath);
				webDB.sendTextToAnElement(InputField, InputValue, ElementType.Xpath);
				logs.logging("INFO", "Input Value Is :" + InputValue);
				String Value = webDB.getDomPropertyFromElement(InputField, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value"));
				logs.logging("INFO", "Get Attribute Value Is :" + Value);
				flag = Value.contains(InputValue);
				if (flag) {
					logs.logging("INFO", "Verified Input Value From Get Attribute Value");
				} else {
					logs.logging("INFO", "Verification failed for Input Value From Get Attribute Value");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Insert Successfully Min Disc Percent
	 * 
	 * @author Kowshik-RMITS
	 * @param Min_Disc_Value
	 */
	public void insertMinDisc(String Min_Disc_Value) {
		flag = webDB.waitForElement(SalesStandardPriceLocators.MinPriceField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesStandardPriceLocators.MinPriceField, ElementType.Xpath);
			webDB.doubleClickAnElement(SalesStandardPriceLocators.MinPriceField, ElementType.Xpath);

			flag = webDB.waitForElement(SalesStandardPriceLocators.min_DiscInput, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(SalesStandardPriceLocators.min_DiscInput, ElementType.Xpath);
				webDB.clickAnElement(SalesStandardPriceLocators.min_DiscInput, ElementType.Xpath);
				verifyClickAndEnterValueInTableFields(SalesStandardPriceLocators.Min_Disc(2),
						SalesStandardPriceLocators.min_DiscInput, Min_Disc_Value);
				if (flag) {
					logs.logging("INFO", "Successfully Inserted  Min Disc " + Min_Disc_Value);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Insert Successfully Max Disc Percent
	 * 
	 * @author Kowshik-RMITS
	 * @param Max_Disc_Value
	 */
	public void insertMaxDisc(String Max_Disc_Value) {
		flag = webDB.waitForElement(SalesStandardPriceLocators.MaxDiscField, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(SalesStandardPriceLocators.MaxDiscField, ElementType.Xpath);
			flag = webDB.waitForElement(SalesStandardPriceLocators.MaxDiscInput, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(SalesStandardPriceLocators.MaxDiscInput, ElementType.Xpath);
				verifyClickAndEnterValueInTableFields(SalesStandardPriceLocators.MaxDiscField,
						SalesStandardPriceLocators.MaxDiscInput, Max_Disc_Value);
				if (flag) {
					logs.logging("INFO", "Successfully Inserted  Min Disc " + Max_Disc_Value);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Insert Successfully Price Class Against To The STD Price Class
	 * 
	 * @author Kowshik-RMITS
	 * @param Max_Disc_Value
	 */
	public void insertPriceClass(String priceClassName) {

		flag = webDB.waitForElement(SalesStandardPriceLocators.PriceClassClick, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesStandardPriceLocators.PriceClassClick, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesStandardPriceLocators.PriceClassLov, priceClassName);
			webDB.waitForElement(SalesStandardPriceLocators.SaveButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", " Successfully Inserted a Price Class Field " + priceClassName);
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Click Save Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickSaveButton() {
		flag = webDB.waitForElement(SalesStandardPriceLocators.SaveButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(SalesStandardPriceLocators.SaveButton, ElementType.Xpath);
			webDB.javaScriptClickAnElement(SalesStandardPriceLocators.SaveButton, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Saved a Record ");
				commonFunc.hardWait(2000);
				clickSearchButtoninStdPrice();
				uncheckaRadioButtonOfCatalogandPriceListFlag();
				passingStdPriceItemToBeFillterInFindPage();
				clickHyderLinkInFindPage();
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Click Active Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickActiveButton() {
//		commonFunc.hardWait(2000);
//		commonFunc.checkRefreshCellsDisappear();
		webDB.navigateToRefresh();
		flag = webDB.waitForElement(SalesStandardPriceLocators.ActiveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesStandardPriceLocators.ActiveButton, ElementType.Xpath);
			logs.logging("INFO", "Succeessfully Active Record");
			commonFunc.checkRefreshCellsDisappear();
		}
		assertTrue(flag);
	}

	/**
	 * Verify Search Button Clicking
	 * 
	 * @author Kowshik-RMITS
	 */
	public void verifySearchButtonClicking() {
		flag = webDB.waitForElement(PricelistPageLocators.ItemSearchBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.ItemSearchBtn, ElementType.Xpath);
			logs.logging("INFO", "Search Button is clicked");
			flag = webDB.waitForElement(PricelistPageLocators.ItemDrillDownBtn(), ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PricelistPageLocators.ItemDrillDownBtn(), ElementType.Xpath);
				logs.logging("INFO", "Drill Down item");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Clicking General Tab
	 * 
	 * @author Kowshik-RMITS
	 */
	public void verifyClickingGeneralTab() {
		flag = webDB.waitForElement(PricelistPageLocators.ItemGeneral, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.ItemGeneral, ElementType.Xpath);
			logs.logging("INFO", "General  Button is clicked");
			flag = webDB.waitForElement(PricelistPageLocators.ItemCopy, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PricelistPageLocators.ItemCopy, ElementType.Xpath);
				logs.logging("INFO", "Item Copy is Clicked");
			}
		}

		assertTrue(flag);
	}

	/**
	 * Verify Genrate Item
	 * 
	 * @author Kowshik-RMITS
	 * @param Itm
	 * @param SheetName
	 * @param i
	 * @param Map_Value
	 * @param Landing_Cost
	 * @param Max_Disc_Value
	 */
	public void verifyGenerateItemSim1(String Itm, String SheetName, int i, String Map_Value, String Landing_Cost,
			String Max_Disc_Value) {

		String ItemSeqNo = RandomStringUtils.randomNumeric(6);
		if (ItemSeqNo.equals("000")) {
			ItemSeqNo = "10";
		}
		String SimItemId = Itm + ItemSeqNo;

		flag = webDB.waitForElement(PricelistPageLocators.ItemIdInputCopy, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(PricelistPageLocators.ItemIdInputCopy, SimItemId, ElementType.Xpath);
			logs.logging("INFO", "Item ID is Entered");

			flag = webDB.waitForElement(PricelistPageLocators.ItemDescInputCopy, ElementType.Xpath);
			if (flag) {
				webDB.sendTextToAnElement(PricelistPageLocators.ItemDescInputCopy, SimItemId, ElementType.Xpath);
				logs.logging("INFO", "Item Desc is Entered");

				flag = webDB.waitForElement(PricelistPageLocators.ItemOkCopy, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PricelistPageLocators.ItemOkCopy, ElementType.Xpath);
					logs.logging("INFO", "Ok Button is clicked ");

					flag = webDB.waitForElement(PricelistPageLocators.ItemGetText, ElementType.Xpath);
					if (flag) {
						String SimItemName = webDB.getDomPropertyFromElement(PricelistPageLocators.ItemGetText,
								ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
						VariableHandler.setItemName(SimItemName);

						verifyItemPost(VariableHandler.getItemName());

						commonFunc.checkRefreshCellsDisappear();
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
						// item Asoo
						commonFunc.navigateItemAssoScreen();
						cusItems.searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
						cusItems.searchItemInFindPageMultiples();
						cusItems.clickSearchButton();
						cusItems.searchedItem();
						cusItems.customerAssoFlag();
						cusItems.changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
						cusItems.clickSaveButton();
						commonFunc.logoutUser();
						commonFunc.checkRefreshCellsDisappear();

						// to Insert Customer Items
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
						commonFunc.navigateCustomerItemScreen();
						cusItems.addPLusButton();
						cusItems.insertCustomerFromLovField(commonFunc.getDataFromTestDataSheet("catalogCustomer"));
						cusItems.addPlusInCustomerItem();
						cusItems.insertIteminCustomerItemsAfterCustomerAssoFlag();
						cusItems.priceClassSelectStdFormat(commonFunc.getDataFromTestDataSheet("priceClassName"));
						cusItems.priceBasisSelectInCustomerItems(
								commonFunc.getDataFromTestDataSheet("PriceBasisStandard"));
						commonFunc.hardWait(3000);
						commonFunc.checkRefreshCellsDisappear();
						cusItems.clickDefaltFlag2();
						cusItems.saveButtoninCustomerItems();
						commonFunc.logoutUser();
						commonFunc.checkRefreshCellsDisappear();

						// to insert a Std Price
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
						commonFunc.navigatePriceList_StdPrice_CataloguePrice();
						SalesPriceList.clickAddButton();
						clickStandardPriceButton();
						clickAddbutton();
						clickLocutionField(commonFunc.getDataFromTestDataSheet("Loc_100"));
						insertItemIdStandardPrice();
						insertCurcyField(commonFunc.getDataFromTestDataSheet("CatalogueCurcy"));
						insertMapPriceStdPrice(Map_Value);
						insertLandingCost(Landing_Cost);
						insertMaxDisc(Max_Disc_Value);
						insertPriceClass(commonFunc.getDataFromTestDataSheet("priceClassName"));
						clickSaveButton();
						StdPriceDbValidation();
						clickActiveButton();
						commonFunc.hardWait(2000);
						commonFunc.logoutUser();
						commonFunc.checkRefreshCellsDisappear();

					}
				}

			}
		}
		assertTrue(flag);
	}

	public List<String> getGeneratedItems() {
		return getGeneratedItems();
	}

	/**
	 * Verify Item Post Button
	 * 
	 * @author Kowshik-RMITS
	 * @param item
	 */
	public void verifyItemPost(String item) {
		flag = webDB.waitForElement(PricelistPageLocators.ItemPost, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.ItemPost, ElementType.Xpath);
			logs.logging("INFO", "Item is Posted");
			commonFunc.hardWait(3000);
			flag = webDB.waitForElementUntilGivenTime(PricelistPageLocators.ItemPostSucessMsg, ElementType.Xpath, 15);
			if (flag) {
				logs.logging("INFO", "Item Add Button is Clicked");
			} else {
				flag = webDB.waitForElement(CommonUiLocators.WorkApprovalFrame, ElementType.Xpath);
				if (flag) {
					approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("AshokValue"));
					commonFunc.logoutUser();
					commonFunc.validCommonLogin(System.getProperty("AshokUsername"),
							System.getProperty("AshokPassword"), "AshokUsername");
					approvalFunc.navigateToApprovals();
					approvalFunc.filterDocumentsforApproval(item);
					approvalFunc.verifyApproveWithDocNumber(item);
					commonFunc.logoutUser();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Creates Std Price With Same Price Class & Multiples Items
	 * 
	 * @author Kowshik-RMITS
	 * @param Item1
	 * @param i
	 */
	public void createStandardPricewithSamePriceClassAndMultipleItems(String Item1, int i) {

		String Landing_Cost = RandomStringUtils.randomNumeric(1);
		if (Landing_Cost.equals("0")) {
			Landing_Cost = "10";
		}

		String Min_Disc_Value = RandomStringUtils.randomNumeric(1);
		if (Min_Disc_Value.equals("0")) {
			Min_Disc_Value = "10";
		}

		String Max_Disc_Value = RandomStringUtils.randomNumeric(2);
		if (Max_Disc_Value.equals("00")) {
			Max_Disc_Value = "10";
		}

		int maxAttempts = 1;
		boolean success = false;
		ArrayList<String> GeneratedItem = new ArrayList<String>();

		for (int attempt = 1; attempt <= maxAttempts; attempt++) {
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateItemPage1();

			flag = webDB.waitForElement(PricelistPageLocators.FindScreenItemField, ElementType.Xpath);

			if (flag) {
				webDB.sendTextToAnElement(PricelistPageLocators.FindScreenItemField, Item1, ElementType.Xpath);
				logs.logging("INFO", "Search is clicked");

				flag = webDB.waitForElement(PricelistPageLocators.ItemSearchBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Search Button is clicked ");
					verifySearchButtonClicking();
					verifyClickingGeneralTab();
					verifyGenerateItemSim1(commonFunc.getDataFromTestDataSheet("Sim"),
							commonFunc.getDataFromTestDataSheet("Items"), attempt, Landing_Cost, Max_Disc_Value,
							Min_Disc_Value);

					GeneratedItem.add(VariableHandler.getItemName());
					success = true;
				}
			}

		}

		System.out.println(GeneratedItem.size());
		for (int j = 1; j <= GeneratedItem.size(); j++) {
			ExcelUtils excelUtils = new ExcelUtils();
			String SheetName1 = "Items";
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
					+ "PR_ITEMS.xlsx";
			excelUtils.writeDataInExcel(Filepath, SheetName1, j, 0, GeneratedItem.get(j - 1));

		}

	}

	/**
	 * Creates Multiples Item Different With Customer Items
	 * 
	 * @author Kowshik-RMITS
	 * @param Item1
	 */
	public void createsMultipesItemDifferPriceClassWithCustomerItems(String Item1) {
		int maxAttempts = 2;
		boolean success = false;
		ArrayList<String> GeneratedItem = new ArrayList<String>();

		for (int attempt = 1; attempt <= maxAttempts; attempt++) {
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigateItemPage1();

			flag = webDB.waitForElement(PricelistPageLocators.FindScreenItemField, ElementType.Xpath);

			if (flag) {
				webDB.sendTextToAnElement(PricelistPageLocators.FindScreenItemField, Item1, ElementType.Xpath);
				logs.logging("INFO", "Search is clicked");

				flag = webDB.waitForElement(PricelistPageLocators.ItemSearchBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Search Button is clicked ");
					verifySearchButtonClicking();
					verifyClickingGeneralTab();
					verifyGenerateItemSim2(commonFunc.getDataFromTestDataSheet("Sim"));

					GeneratedItem.add(VariableHandler.getItemName());
					success = true;
				}
			}
		}
		System.out.println(GeneratedItem.size());
		for (int j = 1; j <= GeneratedItem.size(); j++) {
			ExcelUtils excelUtils = new ExcelUtils();
			String SheetName2 = "Items";
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
					+ "PR_ITEMS.xlsx";
			excelUtils.writeDataInExcel(Filepath, SheetName2, j, 0, GeneratedItem.get(j - 1));

		}
	}

	/**
	 * Verify Genrerate Item
	 * 
	 * @author Kowshik-RMITS
	 * @param Itm
	 */
	public void verifyGenerateItemSim2(String Itm) {

		String ItemSeqNo = RandomStringUtils.randomNumeric(6);
		if (ItemSeqNo.equals("000")) {
			ItemSeqNo = "10";
		}
		String SimItemId = Itm + ItemSeqNo;

		flag = webDB.waitForElement(PricelistPageLocators.ItemIdInputCopy, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(PricelistPageLocators.ItemIdInputCopy, SimItemId, ElementType.Xpath);
			logs.logging("INFO", "Item ID is Entered");

			flag = webDB.waitForElement(PricelistPageLocators.ItemDescInputCopy, ElementType.Xpath);
			if (flag) {
				webDB.sendTextToAnElement(PricelistPageLocators.ItemDescInputCopy, SimItemId, ElementType.Xpath);
				logs.logging("INFO", "Item Desc is Entered");

				flag = webDB.waitForElement(PricelistPageLocators.ItemOkCopy, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(PricelistPageLocators.ItemOkCopy, ElementType.Xpath);
					logs.logging("INFO", "Ok Button is clicked ");

					flag = webDB.waitForElement(PricelistPageLocators.ItemGetText, ElementType.Xpath);
					if (flag) {
						String SimItemName = webDB.getDomPropertyFromElement(PricelistPageLocators.ItemGetText,
								ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
						VariableHandler.setItemName(SimItemName);

						verifyItemPost(VariableHandler.getItemName());

						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(2000);
						commonFunc.logoutUser();
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
						// item Asso
						commonFunc.navigateItemAssoScreen();
						cusItems.searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
						cusItems.searchItemInFindPage();
						cusItems.clickSearchButton();
						cusItems.searchedItem();
						cusItems.customerAssoFlag();
						cusItems.changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
						cusItems.clickSaveButton();
						commonFunc.hardWait(2000);
						commonFunc.logoutUser();
						commonFunc.hardWait(2000);
						commonFunc.checkRefreshCellsDisappear();

					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Insert Customer Items
	 * 
	 * @author Kowshik-RMITS
	 */

	public void toInsertCustomerItems() {

		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateCustomerItemScreen();
		cusItems.addPLusButton();
		cusItems.insertCustomerFromLovField(commonFunc.getDataFromTestDataSheet("catalogCustomer"));
		cusItems.addPlusInCustomerItem();
		cusItems.insertIteminCustomerItemsAfterCustomerAssoFlag();
		cusItems.priceClassSelect();
		cusItems.priceBasisSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("PriceBasisStandard"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
		cusItems.clickDefaltFlag2();
		cusItems.saveButtoninCustomerItems();
		commonFunc.logoutUser();
		commonFunc.checkRefreshCellsDisappear();

		createsMultiplesItemsStandardPrice();

	}

	/**
	 * Creates Multiples Item Std Price
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createsMultiplesItemsStandardPrice() {

		String Landing_Cost = RandomStringUtils.randomNumeric(1);
		if (Landing_Cost.equals("0")) {
			Landing_Cost = "10";
		}

		String Min_Disc_Value = RandomStringUtils.randomNumeric(1);
		if (Min_Disc_Value.equals("0")) {
			Min_Disc_Value = "10";
		}

		String Max_Disc_Value = RandomStringUtils.randomNumeric(2);
		if (Max_Disc_Value.equals("00")) {
			Max_Disc_Value = "10";
		}
		String Map_Value = RandomStringUtils.randomNumeric(1);
		if (Map_Value.equals("0")) {
			Map_Value = "10";
		}
		ExcelUtils excelUtils = new ExcelUtils();
		String[][] itemMultiples = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "PR_ITEMS.xlsx";

		itemMultiples = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("Items"));
		System.out.println(itemMultiples.length);
		for (int i = 1; i < itemMultiples.length; i++) {
			String Item1 = itemMultiples[i][0];
			// to insert a Std Price
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
					"AdminUsername");
			commonFunc.navigatePriceList_StdPrice_CataloguePrice();
			SalesPriceList.clickAddButton();
			clickStandardPriceButton();
			clickAddbutton();
			clickLocutionField(commonFunc.getDataFromTestDataSheet("Loc_100"));
			insertItemIdStandardPrice();
			insertCurcyField(commonFunc.getDataFromTestDataSheet("CatalogueCurcy"));
			insertMapPriceStdPrice(Map_Value);
			insertLandingCost(Landing_Cost);
//			insertMinDisc(Min_Disc_Value);
			insertMaxDisc(Max_Disc_Value);
			insertPriceClass(commonFunc.getDataFromTestDataSheet("priceClassName"));
			clickSaveButton();
			StdPriceDbValidation();
			clickActiveButton();
			commonFunc.logoutUser();
			commonFunc.checkRefreshCellsDisappear();

		}

	}

	/**
	 * DataBase Validation Of Text Type
	 * 
	 * @author Kowshik-RMITS
	 * @param Location
	 * @param ExpectedDBValue
	 */
	public void verifyAssertionGetTextValidation(String Location, String ExpectedDBValue) {
		flag = webDB.waitForElement(Location, ElementType.Xpath);
		if (flag) {
			String text = webDB.getTextFromElement(Location, ElementType.Xpath);
			logs.logging("INFO", "Get Text Actual UI Value Is: " + text);
			logs.logging("INFO", "Expected DataBase Value Is: " + ExpectedDBValue);
			flag = text.equals(ExpectedDBValue);
			if (flag) {
				logs.logging("INFO",
						"Verified Actual UI Value : " + text + " With Expected DataBase Value :" + ExpectedDBValue);
			} else {
				logs.logging("INFO", "Verification Failed Expected DataBase Value : " + ExpectedDBValue
						+ "Get Text Actual UI Value : " + text);
			}
		}
		assertTrue(flag);
	}

	/**
	 * DataBase Validation Of Dom Property Type
	 * 
	 * @author Kowshik - RMITS
	 * @param UIFieldLocator
	 * @param ExpectedDBValue
	 */
	public void verifyAssertionDomPropertyValidation(String UIFieldLocator, String ExpectedDBValue) {
		flag = webDB.waitForElement(UIFieldLocator, ElementType.Xpath);
		if (flag) {
			String text = webDB.getDomPropertyFromElement(UIFieldLocator, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			logs.logging("INFO", "Get Text Actual UI Value Is: " + text);
			logs.logging("INFO", "Expected DataBase Value Is: " + ExpectedDBValue);
			flag = text.equals(ExpectedDBValue);
			if (flag) {
				logs.logging("INFO",
						"Verified Actual UI Value : " + text + " With Expected DataBase Value :" + ExpectedDBValue);
			} else {
				logs.logging("INFO", "Verification Failed Expected DataBase Value : " + ExpectedDBValue
						+ "Get Text Actual UI Value : " + text);
			}
		}
		assertTrue(flag);
	}

	/**
	 * DataBase Validation Of Both Value Type
	 * 
	 * @author Kowshik - RMITS
	 * @param Actual
	 * @param Expected
	 */
	public void verifyAssertionBothValues(String Actual, String Expected) {
		logs.logging("Info", "Actual Value Is " + Actual);
		logs.logging("Info", "Expected Value Is " + Expected);
		flag = Actual.contains(Expected);
		if (flag) {
			logs.logging("Info", "Verified Actual And Expected Values");
		} else {
			logs.logging("INFO", "Mismatch With Actual And Expected");
		}
		assertTrue(flag);
	}

	public void StdPriceDbValidation() {
		verifyAssertionGetTextValidation(SalesStandardPriceLocators.LocIdDb, dbCommonFunctions.SalesStandardPrice(
				VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("StdPriceIocDb")));

		verifyAssertionGetTextValidation(SalesStandardPriceLocators.UnitIdDb, dbCommonFunctions.SalesStandardPrice(
				VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("StdPriceUnitDb")));

		verifyAssertionGetTextValidation(SalesStandardPriceLocators.ItemsIdDb, dbCommonFunctions.SalesStandardPrice(
				VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("StdPriceItemIdDb")));

		verifyAssertionGetTextValidation(SalesStandardPriceLocators.ItemDesDb, dbCommonFunctions.SalesStandardPrice(
				VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("StdPriceItemDescDb")));

		verifyAssertionGetTextValidation(SalesStandardPriceLocators.CurcyFieldDb, dbCommonFunctions.SalesStandardPrice(
				VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("StdPriceCurcyFieldDb")));

		verifyAssertionGetTextValidation(SalesStandardPriceLocators.PriceUOM, dbCommonFunctions.SalesStandardPrice(
				VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("StdPriceUOMFieldDb")));

		verifyAssertionGetTextValidation(SalesStandardPriceLocators.MapFieldDb, dbCommonFunctions.SalesStandardPrice(
				VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("StdPriceMapFieldDb")));

		verifyAssertionGetTextValidation(SalesStandardPriceLocators.LandCostDb, dbCommonFunctions.SalesStandardPrice(
				VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("StdPriceLandCostDb")));

		verifyAssertionGetTextValidation(SalesStandardPriceLocators.SellingCostFieldDb,
				dbCommonFunctions.SalesStandardPrice(VariableHandler.getItemName(),
						commonFunc.getDataFromTestDataSheet("StdPriceSellingPriceDb")));

		verifyAssertionGetTextValidation(SalesStandardPriceLocators.MinDiscPctDb, dbCommonFunctions.SalesStandardPrice(
				VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("StdPriceMinDiscPct")));

		verifyAssertionGetTextValidation(SalesStandardPriceLocators.MaxDiscPctDb, dbCommonFunctions.SalesStandardPrice(
				VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("StdPriceMaxDiscPct")));

	}

}
