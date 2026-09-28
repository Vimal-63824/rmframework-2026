package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks May 20, 2025
 */
import static org.testng.Assert.assertTrue;

import java.io.File;

import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.CustomerItemsLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class CustomerItemsFunctions {
	private boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	public static PricelistPageFunctions priceListPR = new PricelistPageFunctions();

	/**
	 * Customer Name Passed
	 * 
	 * @author Kowshik
	 * @param Customer
	 */
	public void insertCustomerFromLovField(String Customer) {
		commonFunc.searchAndSelectValueInFilters(CustomerItemsLocators.CustomerLovButton, Customer);
		logs.logging("Info", "Customer Name Passed");
		flag = webDB.waitForClickElement(CustomerItemsLocators.ItemFieldClick(), ElementType.Xpath);
		assertTrue(flag);
	}

	/**
	 * Insert Item in Customer Items After Customer Asso Flag
	 * 
	 * @author Kowshik
	 * @param ITEM
	 */
	public void insertIteminCustomerItemsAfterCustomerAssoFlag() {
		flag = webDB.waitForClickElement(CustomerItemsLocators.ItemFieldClick(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerItemsLocators.ItemFieldClick(), ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(CustomerItemsLocators.ItemLovButton,
					VariableHandler.getItemName());
		}
		assertTrue(flag);
	}

	/**
	 * Insert Items In Customer Items
	 * 
	 * @author Kowshik-RMITS
	 */
	public void insertIteminCustomerItemsAfterCustomerAssoFlagMultiplesItems() {
		flag = webDB.waitForClickElement(CustomerItemsLocators.ItemFieldClick(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerItemsLocators.ItemFieldClick(), ElementType.Xpath);
			flag = webDB.waitForClickElement(CustomerItemsLocators.ItemLovButton, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(CustomerItemsLocators.ItemLovButton,
						VariableHandler.getItemName());
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Select Price Class
	 * 
	 * @author Kowshik
	 * @param priceClassName
	 */
	public void priceClassSelectInCustomerItems(String priceClassName) {
		flag = webDB.waitForElement(CustomerItemsLocators.PriceClassSelectDropdown, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CustomerItemsLocators.PriceClassSelectDropdown, ElementType.Xpath);
			webDB.doubleClickAnElement(CustomerItemsLocators.PriceClassSelectDropdown, ElementType.Xpath);
			webDB.selectDropDownByText(CustomerItemsLocators.PriceClassSelectDropdown, priceClassName,
					ElementType.Xpath);
			commonFunc.hardWait(1000);
			flag = webDB.waitForElement(CustomerItemsLocators.PriceBasisSelectDropdown, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Successfully Value Is Selected");
			}
		}
		assertTrue(flag);
	}

	/**
	 * TO Verify Price Class Std Format
	 * 
	 * @author Kowshik-RMITS
	 * @param priceClassName
	 */
	public void priceClassSelectStdFormat(String priceClassName) {
		flag = webDB.waitForElement(CustomerItemsLocators.Pricecls(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerItemsLocators.Pricecls(), ElementType.Xpath);
			flag = webDB.selectDropDownByText(CustomerItemsLocators.PriceClassSelectDropdown, priceClassName,
					ElementType.Xpath);
			if (flag) {
				flag = webDB.waitForElement(CustomerItemsLocators.SaveButtonCustomer, ElementType.Xpath);
				if (flag) {
					logs.logging("Info", "Price Basis Changes Done Successfully" + priceClassName);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Changes a Qc Type
	 * 
	 * @author Kowshik-RMITS
	 * @param QcFlag
	 */
	public void changesNotApplicabeType(String QcFlag) {
		flag = webDB.waitForElement(CommonUiLocators.QualityTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.QualityTab, ElementType.Xpath);
			String value = webDB.getDomAttributeFromElement(CommonUiLocators.QualityTab, ElementType.Xpath,
					"aria-expanded");
			if (value.equalsIgnoreCase("false")) {
				webDB.javaScriptClickAnElement(CommonUiLocators.QualityTab, ElementType.Xpath);
				logs.logging("INFO", "Quality Tab Is Clicked ");
			}
			flag = webDB.waitForElement(CommonUiLocators.TypeDropDown, ElementType.Xpath);
			if (flag) {
				flag = webDB.selectDropDownByText(CommonUiLocators.TypeDropDown, QcFlag, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Successfully Changed a Type" + QcFlag);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Click Default Flag
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickDefaltFlag2() {
		flag = webDB.waitForElement(CustomerItemsLocators.DefaultFlagField(), ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CustomerItemsLocators.DefaultFlagField(), ElementType.Xpath);
			flag = webDB.javaScriptClickAnElement(CustomerItemsLocators.DefaultFlagField(), ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (flag) {
				logs.logging("INFO", "Default Flag Is Checked ");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Price Class Select
	 * 
	 * @author Kowshik-RMITS
	 */
	public void priceClassSelect() {
		System.out.println("a1");
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "PR_ITEMS.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("Items"));
		System.out.println("b1");
		for (int i = 1; i <= itemmultiple.length; i++) {
			String ITEMS = itemmultiple[i][0];
			String PriceClass = itemmultiple[i][1];
			if (!ITEMS.equals("")) {
				System.out.println("c1");
				addPlusInCustomerItemMultiples();
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForClickElement(CustomerItemsLocators.ItemFieldClick(), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CustomerItemsLocators.ItemFieldClick(), ElementType.Xpath);
					flag = webDB.waitForClickElement(CustomerItemsLocators.ItemLovButton, ElementType.Xpath);
					if (flag) {
						commonFunc.searchAndSelectValueInFilters(CustomerItemsLocators.ItemLovButton, ITEMS);
						commonFunc.hardWait(2000);
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(CustomerItemsLocators.Pricecls(), ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(CustomerItemsLocators.Pricecls(), ElementType.Xpath);
							System.out.println("d1");
							if (PriceClass.equals(commonFunc.getDataFromTestDataSheet("Dom"))) {
								commonFunc.hardWait(2000);
								commonFunc.checkRefreshCellsDisappear();
								flag = webDB.selectDropDownByText(CustomerItemsLocators.PriceClassSelectDropdown,
										commonFunc.getDataFromTestDataSheet("Dom"), ElementType.Xpath);
								logs.logging("Info", "Successfully Dom Is Selected");
							} else {
								commonFunc.hardWait(2000);
								commonFunc.checkRefreshCellsDisappear();
								flag = webDB.selectDropDownByText(CustomerItemsLocators.PriceClassSelectDropdown,
										commonFunc.getDataFromTestDataSheet("Inter"), ElementType.Xpath);
								logs.logging("Info", "Successfully Inter Is Selected");
							}
							flag = webDB.waitForElement(CustomerItemsLocators.PriceBasisCL(), ElementType.Xpath);
							if (flag) {
								logs.logging("Info", "Successfully Value Is Selected");
								priceBasisSelectInCustomerItems(
										commonFunc.getDataFromTestDataSheet("PriceBasisStandard"));
								saveButtoninCustomerItems();
								commonFunc.hardWait(3000);
								filltersaItemCustomerItemsMultiples(ITEMS);
								commonFunc.hardWait(3000);
								commonFunc.checkRefreshCellsDisappear();
								clickDefaltFlag();
								saveButtoninCustomerItems();
								commonFunc.checkRefreshCellsDisappear();
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Select a Price Basis
	 * 
	 * @author Kowshik
	 */
	public void priceBasisSelectInCustomerItems(String priceBasisValue) {
		flag = webDB.waitForElement(CustomerItemsLocators.PriceBasisCL(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerItemsLocators.PriceBasisCL(), ElementType.Xpath);
			flag = webDB.selectDropDownByText(CustomerItemsLocators.PriceBasisSelectDropdown, priceBasisValue,
					ElementType.Xpath);
			if (flag) {
				flag = webDB.waitForElement(CustomerItemsLocators.SaveButtonCustomer, ElementType.Xpath);
				if (flag) {
					logs.logging("Info", "Price Basis Changes Done Successfully" + priceBasisValue);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Catalogue Select In Customer Items
	 * 
	 * @author Kowshik To Select Catalogue
	 */
	public void catalogSelectInCustomerItems(String catalogName) {
		flag = webDB.waitForClickElement(CustomerItemsLocators.SaveButtonCustomer, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CustomerItemsLocators.CatalogSelectDropdown, ElementType.Xpath);
			webDB.selectDropDownByText(CustomerItemsLocators.CatalogSelectDropdown, catalogName, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Catalog Fixed Done Successfully" + catalogName);
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Click a Save Button
	 * 
	 * @author Kowshik
	 */
	public void saveButtoninCustomerItems() {
		flag = webDB.waitForClickElement(CustomerItemsLocators.SaveButtonCustomer, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerItemsLocators.SaveButtonCustomer, ElementType.Xpath);
			logs.logging("Info", "Save Button Clicked Successfully");
			flag = webDB.waitForElement(CustomerItemsLocators.ToastAlertMessageCustomerItems, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * Click Defalt Flag
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickDefaltFlag1() {
		flag = webDB.waitForElement(CustomerItemsLocators.DefaultFlagField(), ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(CustomerItemsLocators.DefaultFlagField(), ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			logs.logging("INFO", "Defalt Flag Checked Stage");
		}
		assertTrue(flag);
	}

	/**
	 * Add Plus Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void addPLusButton() {
		flag = webDB.waitForElement(CustomerItemsLocators.AddPLusButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerItemsLocators.AddPLusButton, ElementType.Xpath);
			flag = webDB.waitForElement(CustomerItemsLocators.CustomerLovButton, ElementType.Xpath);
			if (flag) {
				logs.logging("Info", "Add Button is Clicked ");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Add Plus In Customer Items Find Page
	 * 
	 * @author Kowshik-RMITS
	 */
	public void addPlusInCustomerItem() {
		flag = webDB.waitForElement(CustomerItemsLocators.AddPlusButtonItems, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerItemsLocators.AddPlusButtonItems, ElementType.Xpath);
			flag = webDB.waitForElement(CustomerItemsLocators.ItemFieldClick(), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Button is Clicked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Add Plus In Customer Items Find Page Multiples
	 * 
	 * @author Kowshik-RMITS
	 */
	public void addPlusInCustomerItemMultiples() {
		flag = webDB.waitForElement(CustomerItemsLocators.AddPlusButtonItems, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerItemsLocators.AddPlusButtonItems, ElementType.Xpath);
			flag = webDB.waitForElement(CustomerItemsLocators.ItemFieldClick(), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Button is Clicked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Click Default Flag
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickDefaltFlag() {
		flag = webDB.waitForElement(CustomerItemsLocators.DefaultFlagField(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CustomerItemsLocators.DefaultFlagField(), ElementType.Xpath);
			flag = webDB.waitForElement(CustomerItemsLocators.DefaultFlag, ElementType.Xpath);
			if (flag) {
				flag = webDB.clickAnElement(CustomerItemsLocators.DefaultFlag, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Default Flag is Checcked Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Select Item In Unit Field
	 * 
	 * @author Kowshik-RMITS
	 * @param Unit_100
	 */
	public void searchItemInUnitField(String Unit_100) {
		commonFunc.searchAndSelectValueInFilters(CommonUiLocators.unitLov, Unit_100);
		logs.logging("INFO", "Successfully Searched a unit" + Unit_100);
	}

	/**
	 * Search Items In Find Page
	 * 
	 * @author Kowshik-RMITS
	 */
	public void searchItemInFindPage() {
		commonFunc.searchAndSelectValueInFilters(CommonUiLocators.ItemLovA, VariableHandler.getItemName());
		logs.logging("INFO", "Item Inserted Sucessfully ");
		flag = webDB.waitForElement(CommonUiLocators.SearchButton, ElementType.Xpath);
		assertTrue(flag);
	}

	public void searchItemInFindPageMultiples() {
		flag = webDB.waitForElement(CommonUiLocators.ItemLovA, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(CommonUiLocators.ItemLovA, VariableHandler.getItemName());
			logs.logging("INFO", "Item Inserted Sucessfully ");
			flag = webDB.waitForElement(CommonUiLocators.SearchButton, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * Click Search Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickSearchButton() {
		flag = webDB.waitForElement(CommonUiLocators.SearchButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CommonUiLocators.SearchButton, ElementType.Xpath);
			webDB.clickAnElement(CommonUiLocators.SearchButton, ElementType.Xpath);
			webDB.waitForElement(CommonUiLocators.ItemAssoFindTable, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.checkRefreshCellsDisappear();
			logs.logging("INFO", "Successfully Fillter a Item In Find Page");
		}
		assertTrue(flag);
	}

	/**
	 * Searched Items
	 * 
	 * @author Kowshik-RMITS
	 */
	public void searchedItem() {
		flag = webDB.waitForElement(CommonUiLocators.SearchedItemDrillDown(), ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(CommonUiLocators.SearchedItemDrillDown(), ElementType.Xpath);
			webDB.clickAnElement(CommonUiLocators.SearchedItemDrillDown(), ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.SalesTab, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Clicked a Drilldown Item");
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Naviates Customer Sales Click Customer Asso Flag
	 * 
	 * @author Kowshik-RMITS
	 */
	public void customerAssoFlag() {
		flag = webDB.waitForElement(CommonUiLocators.SalesTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.SalesTab, ElementType.Xpath);
			String value = webDB.getDomAttributeFromElement(CommonUiLocators.SalesTab, ElementType.Xpath,
					"aria-expanded");
			if (value.equalsIgnoreCase("false")) {
				webDB.javaScriptClickAnElement(CommonUiLocators.SalesTab, ElementType.Xpath);
				logs.logging("INFO", "Sales Tab Is Clicked ");
			}
			flag = webDB.waitForElement(CommonUiLocators.CustomerAssoFlag, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Moved a Sales Tap ");
				flag = webDB.waitForElement(CommonUiLocators.CustomerAssoFlag, ElementType.Xpath);
				if (flag) {
					flag = webDB.clickAnElement(CommonUiLocators.CustomerAssoFlag, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Successfully Checked a Customer Assioate Flag");
					}
				}
			}
		}
		assertTrue(flag);
	}
	/**
	 * Click Active Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickedActiveButton() {
		flag = webDB.waitForElement(CommonUiLocators.activeButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.activeButton, ElementType.Xpath);
			logs.logging("INFO", "Item Actived Successfully");
			commonFunc.checkRefreshCellsDisappear();
		}
		assertTrue(flag);
	}

	/**
	 * Save Button Is Clicked
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickSaveButton() {
		flag = webDB.waitForElement(CommonUiLocators.SaveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.SaveButton, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
			logs.logging("INFO", "Save Button is Clicked");
		}
		assertTrue(flag);
	}

	/**
	 * Fiiter Item Customer Items
	 * 
	 * @author Kowshik-RMITS
	 */
	public void filltersaItemCustomerItems() {
		flag = webDB.waitForElement(CustomerItemsLocators.CustomerItemsSearchBox, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(CustomerItemsLocators.CustomerItemsSearchBox, VariableHandler.getItemName(),
					ElementType.Xpath);
			flag = webDB.waitForElement(CustomerItemsLocators.Gobutton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Searched Item in Customer screen ");
				flag = webDB.waitForElement(CustomerItemsLocators.Gobutton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(CustomerItemsLocators.Gobutton, ElementType.Xpath);
					flag = webDB.waitForElement(CustomerItemsLocators.SaveButtonCustomer, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Successfully Clicked a GO button");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Filler Item Customer Multiples
	 * 
	 * @author Kowshik-RMITS
	 * @param ITEMS
	 */
	public void filltersaItemCustomerItemsMultiples(String ITEMS) {
		flag = webDB.waitForElement(CustomerItemsLocators.CustomerItemsSearchBox, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(CustomerItemsLocators.CustomerItemsSearchBox, ITEMS, ElementType.Xpath);
			flag = webDB.waitForElement(CustomerItemsLocators.Gobutton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CustomerItemsLocators.Gobutton, ElementType.Xpath);
				logs.logging("INFO", "Searched Item in Customer screen ");
				flag = webDB.waitForElement(CustomerItemsLocators.SaveButtonCustomer, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Successfully Clicked a GO button");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * To Verify CustomerItems
	 * 
	 * @author Kowshik
	 */
	public void verifyAssociateItemToCustomeMappingOfCustomer() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage();
		priceListPR.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SK0001"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemAssoScreen();
		searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
		searchItemInFindPage();
		clickSearchButton();
		searchedItem();
		customerAssoFlag();
		changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
		clickSaveButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateCustomerItemScreen();
		addPLusButton();
		insertCustomerFromLovField(commonFunc.getDataFromTestDataSheet("catalogCustomer"));
		addPlusInCustomerItem();
		insertIteminCustomerItemsAfterCustomerAssoFlag();
		priceClassSelectStdFormat(commonFunc.getDataFromTestDataSheet("priceClassName"));
		priceBasisSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("PriceBasisStandard"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
		clickDefaltFlag2();
		saveButtoninCustomerItems();

	}

	/**
	 * Items Assoicate With Price List Items
	 * 
	 * @author Kowshik-RMITS
	 */
	public void itemAssociateWithPriceListItem() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage1();
		priceListPR.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SK0001"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemAssoScreen();
		searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
		searchItemInFindPage();
		clickSearchButton();
		searchedItem();
		customerAssoFlag();
		changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
		clickSaveButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateCustomerItemScreen();
		addPLusButton();
		insertCustomerFromLovField(commonFunc.getDataFromTestDataSheet("CustomerName"));
		addPlusInCustomerItem();
		insertIteminCustomerItemsAfterCustomerAssoFlag();
		priceClassSelectStdFormat(commonFunc.getDataFromTestDataSheet("priceClassName"));
		priceBasisSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("priceBasisPriceListValue"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
		clickDefaltFlag2();
		saveButtoninCustomerItems();
	}

	/**
	 * Creates Open So Item - Domestic Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createOpenSoItemOpenSoForDomesticCustomer() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		commonFunc.navigateItemPage1();
		priceListPR.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("Item_Different_UOM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemAssoScreen();
		searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
		searchItemInFindPage();
		clickSearchButton();
		searchedItem();
		customerAssoFlag();
		changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
		clickSaveButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateCustomerItemScreen();
		addPLusButton();
		insertCustomerFromLovField(commonFunc.getDataFromTestDataSheet("ERPPrivateLimitedName"));
		addPlusInCustomerItem();
		insertIteminCustomerItemsAfterCustomerAssoFlag();
		priceClassSelectStdFormat(commonFunc.getDataFromTestDataSheet("priceClassName"));
		priceBasisSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("PriceBasisOpenSo"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
		clickDefaltFlag2();
		saveButtoninCustomerItems();

	}

	/**
	 * Creates Open So Item - Domestic Customer -Price Class Change
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createOpenSoItemForDomesticCustomerForPriceClass() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage1();
		priceListPR.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("Item_Different_UOM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemAssoScreen();
		searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
		searchItemInFindPage();
		clickSearchButton();
		searchedItem();
		customerAssoFlag();
		changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
		clickSaveButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateCustomerItemScreen();
		addPLusButton();
		insertCustomerFromLovField(commonFunc.getDataFromTestDataSheet("ERPPrivateLimitedName"));
		addPlusInCustomerItem();
		insertIteminCustomerItemsAfterCustomerAssoFlag();
		priceClassSelectStdFormat(commonFunc.getDataFromTestDataSheet("priceClassName"));
		priceBasisSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("PriceBasisOpenSo"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
		clickDefaltFlag2();
		saveButtoninCustomerItems();
	}

	/**
	 * Creates Open So Item - Domestic Customer -Addition of Item
	 * 
	 * @author Kowshik-Rmits
	 */
	public void createOpenSoItemForDomesticCustomerForAdditionOfItem() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage1();
		priceListPR.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("Item_Different_UOM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemAssoScreen();
		searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
		searchItemInFindPage();
		clickSearchButton();
		searchedItem();
		customerAssoFlag();
		changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
		clickSaveButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateCustomerItemScreen();
		addPLusButton();
		insertCustomerFromLovField(commonFunc.getDataFromTestDataSheet("ERPPrivateLimitedName"));
		addPlusInCustomerItem();
		insertIteminCustomerItemsAfterCustomerAssoFlag();
		priceClassSelectStdFormat(commonFunc.getDataFromTestDataSheet("priceClassName"));
		priceBasisSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("PriceBasisOpenSo"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
		clickDefaltFlag2();
		saveButtoninCustomerItems();

	}

	/**
	 * Creates Open So Item - Interstate Customer -Addition of Item
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createOpenItemInterstateForAdditionOfItem() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage1();
		priceListPR.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("Item_Different_UOM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemAssoScreen();
		searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
		searchItemInFindPage();
		clickSearchButton();
		searchedItem();
		customerAssoFlag();
		changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
		clickSaveButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateCustomerItemScreen();
		addPLusButton();
		insertCustomerFromLovField(commonFunc.getDataFromTestDataSheet("Cus_Interstate"));
		addPlusInCustomerItem();
		insertIteminCustomerItemsAfterCustomerAssoFlag();
		priceClassSelectStdFormat(commonFunc.getDataFromTestDataSheet("priceClassName"));
		priceBasisSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("PriceBasisOpenSo"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
		clickDefaltFlag2();
		saveButtoninCustomerItems();

	}

	/**
	 * Creates Open So Item - Interstate Customer -Deletation of Item
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createOpenItemForInterStateForDeletaionOfProcess() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage1();
		priceListPR.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("Item_Different_UOM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemAssoScreen();
		searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
		searchItemInFindPage();
		clickSearchButton();
		searchedItem();
		customerAssoFlag();
		changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
		clickSaveButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateCustomerItemScreen();
		addPLusButton();
		insertCustomerFromLovField(commonFunc.getDataFromTestDataSheet("Cus_Interstate"));
		addPlusInCustomerItem();
		insertIteminCustomerItemsAfterCustomerAssoFlag();
		priceClassSelectStdFormat(commonFunc.getDataFromTestDataSheet("priceClassName"));
		priceBasisSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("PriceBasisOpenSo"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
		clickDefaltFlag2();
		saveButtoninCustomerItems();
		commonFunc.logoutUser();

	}

	/**
	 * Creates a Open So
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createOpenSoForExportCustomer() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage1();
		priceListPR.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("Item_Different_UOM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemAssoScreen();
		searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
		searchItemInFindPage();
		clickSearchButton();
		searchedItem();
		customerAssoFlag();
		changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
		clickSaveButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateCustomerItemScreen();
		addPLusButton();
		insertCustomerFromLovField(commonFunc.getDataFromTestDataSheet("Cus_Export"));
		addPlusInCustomerItem();
		insertIteminCustomerItemsAfterCustomerAssoFlag();
		priceClassSelectStdFormat(commonFunc.getDataFromTestDataSheet("priceClassName"));
		priceBasisSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("PriceBasisOpenSo"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
		clickDefaltFlag2();
		saveButtoninCustomerItems();
		commonFunc.logoutUser();

	}

	/**
	 * Creates a Open So For Local customer for Deletation of item
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createOpenSoForLocalCustomer() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage1();
		priceListPR.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("Item_Different_UOM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemAssoScreen();
		searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
		searchItemInFindPage();
		clickSearchButton();
		searchedItem();
		customerAssoFlag();
		changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
		clickSaveButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateCustomerItemScreen();
		addPLusButton();
		insertCustomerFromLovField(commonFunc.getDataFromTestDataSheet("Cus_Local"));
		addPlusInCustomerItem();
		insertIteminCustomerItemsAfterCustomerAssoFlag();
		priceClassSelectStdFormat(commonFunc.getDataFromTestDataSheet("priceClassName"));
		priceBasisSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("PriceBasisOpenSo"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
		clickDefaltFlag2();
		saveButtoninCustomerItems();
		commonFunc.logoutUser();

	}

	/**
	 * Creates a Open So For UTGST Customer Deletation Process
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createOpenForUTGSTCustomer() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage1();
		priceListPR.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("Item_Different_UOM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemAssoScreen();
		searchItemInUnitField(commonFunc.getDataFromTestDataSheet("UTGSTUnit1"));
		searchItemInFindPage();
		clickSearchButton();
		searchedItem();
		customerAssoFlag();
		changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
		clickSaveButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateCustomerItemScreen();
		addPLusButton();
		insertCustomerFromLovField(commonFunc.getDataFromTestDataSheet("UTGST_cus"));
		addPlusInCustomerItem();
		insertIteminCustomerItemsAfterCustomerAssoFlag();
		priceClassSelectStdFormat(commonFunc.getDataFromTestDataSheet("priceClassName"));
		priceBasisSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("PriceBasisOpenSo"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
		clickDefaltFlag2();
		saveButtoninCustomerItems();
		commonFunc.logoutUser();

	}

	/**
	 * Create Item Open So
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createOpenSoForUTGSTCustomer() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage1();
		priceListPR.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("Item_Different_UOM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemAssoScreen();
		searchItemInUnitField(commonFunc.getDataFromTestDataSheet("UTGSTUnit1"));
		searchItemInFindPage();
		clickSearchButton();
		searchedItem();
		customerAssoFlag();
		changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
		clickSaveButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateCustomerItemScreen();
		addPLusButton();
		insertCustomerFromLovField(commonFunc.getDataFromTestDataSheet("UTGST_cus"));
		addPlusInCustomerItem();
		insertIteminCustomerItemsAfterCustomerAssoFlag();
		priceClassSelectStdFormat(commonFunc.getDataFromTestDataSheet("priceClassName"));
		priceBasisSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("PriceBasisOpenSo"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
		clickDefaltFlag2();
		saveButtoninCustomerItems();

	}

	/**
	 * Creates Open So Item - Export Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createsOpenSoItemForExportCustomer() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage1();
		priceListPR.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("Item_Different_UOM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemAssoScreen();
		searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
		searchItemInFindPage();
		clickSearchButton();
		searchedItem();
		customerAssoFlag();
		changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
		clickSaveButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateCustomerItemScreen();
		addPLusButton();
		insertCustomerFromLovField(commonFunc.getDataFromTestDataSheet("catalogExportCustomer"));
		addPlusInCustomerItem();
		insertIteminCustomerItemsAfterCustomerAssoFlag();
		priceClassSelectStdFormat(commonFunc.getDataFromTestDataSheet("priceClassName"));
		priceBasisSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("PriceBasisOpenSo"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
		clickDefaltFlag2();
		saveButtoninCustomerItems();

	}

	/**
	 * Creates Open So Item - Domestic Customer - Cancel Stage
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createOpenSoItemOpenSoForDomesticCustomerForCancelStage() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage1();
		priceListPR.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("Item_Different_UOM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemAssoScreen();
		searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
		searchItemInFindPage();
		clickSearchButton();
		searchedItem();
		customerAssoFlag();
		changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
		clickSaveButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateCustomerItemScreen();
		addPLusButton();
		insertCustomerFromLovField(commonFunc.getDataFromTestDataSheet("ERPPrivateLimitedName"));
		addPlusInCustomerItem();
		insertIteminCustomerItemsAfterCustomerAssoFlag();
		priceClassSelectStdFormat(commonFunc.getDataFromTestDataSheet("priceClassName"));
		priceBasisSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("PriceBasisOpenSo"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
		clickDefaltFlag2();
		saveButtoninCustomerItems();
		commonFunc.logoutUser();

	}

	/**
	 * Items Asso Price Item Different UOM
	 * 
	 * @author Kowshik-RMITS
	 */
	public void itemAssociatePriceListItemDifferentUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage1();
		priceListPR.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("Item_Different_UOM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemAssoScreen();
		searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
		searchItemInFindPage();
		clickSearchButton();
		searchedItem();
		customerAssoFlag();
		changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
		clickSaveButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateCustomerItemScreen();
		addPLusButton();
		insertCustomerFromLovField(commonFunc.getDataFromTestDataSheet("CustomerName"));
		addPlusInCustomerItem();
		insertIteminCustomerItemsAfterCustomerAssoFlag();
		priceClassSelectStdFormat(commonFunc.getDataFromTestDataSheet("priceClassName"));
		priceBasisSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("priceBasisPriceListValue"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
		clickDefaltFlag2();
		saveButtoninCustomerItems();
	}

	/**
	 * Items Asso Price List Items Different Price Class
	 * 
	 * @author Kowshik-RMITS
	 */
	public void itemAssociatePriceListItemDifferentPriceClass() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage1();
		priceListPR.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("Item_Different_UOM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemAssoScreen();
		searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
		searchItemInFindPage();
		clickSearchButton();
		searchedItem();
		customerAssoFlag();
		changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
		clickSaveButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateCustomerItemScreen();
		addPLusButton();
		insertCustomerFromLovField(commonFunc.getDataFromTestDataSheet("CustomerName"));
		addPlusInCustomerItem();
		insertIteminCustomerItemsAfterCustomerAssoFlag();
		priceClassSelectStdFormat(commonFunc.getDataFromTestDataSheet("priceClassName"));
		priceBasisSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("priceBasisPriceListValue"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
		clickDefaltFlag2();
		saveButtoninCustomerItems();

	}

	/**
	 * Creates Cancel Price List Record
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createsCancelPriceListRecord() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage1();
		priceListPR.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("Item_Different_UOM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemAssoScreen();
		searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
		searchItemInFindPage();
		clickSearchButton();
		searchedItem();
		customerAssoFlag();
		changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
		clickSaveButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateCustomerItemScreen();
		addPLusButton();
		insertCustomerFromLovField(commonFunc.getDataFromTestDataSheet("CustomerName"));
		addPlusInCustomerItem();
		insertIteminCustomerItemsAfterCustomerAssoFlag();
		priceClassSelectStdFormat(commonFunc.getDataFromTestDataSheet("priceClassName"));
		priceBasisSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("priceBasisPriceListValue"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
		clickDefaltFlag2();
		saveButtoninCustomerItems();
	}

	/**
	 * Creates Catalogue Simailar UOM & Doestic Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createsCataloguePriceSimilarUOMAndDomesticCustomer() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage1();
		priceListPR.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("Item_Different_UOM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemAssoScreen();
		searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
		searchItemInFindPage();
		clickSearchButton();
		searchedItem();
		customerAssoFlag();
		commonFunc.hardWait(2000);
		changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
		commonFunc.hardWait(2000);
		clickSaveButton();
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateCustomerItemScreen();
		addPLusButton();
		insertCustomerFromLovField(commonFunc.getDataFromTestDataSheet("catalogCustomer"));
		addPlusInCustomerItem();
		insertIteminCustomerItemsAfterCustomerAssoFlag();
		priceClassSelectStdFormat(commonFunc.getDataFromTestDataSheet("priceClassName"));
		priceBasisSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("PriceBasisCatalogue"));
		catalogSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("catalogName"));
		commonFunc.hardWait(3000);
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
		clickDefaltFlag2();
		saveButtoninCustomerItems();
	}

	/**
	 * Creates Catalogue Price Dissimilar UOM & Domestic Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createsCataloguePriceDisSimilarUOMAndDomesticCustomer() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage1();
		priceListPR.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("Item_Different_UOM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemAssoScreen();
		searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
		searchItemInFindPage();
		clickSearchButton();
		searchedItem();
		customerAssoFlag();
		commonFunc.hardWait(2000);
		changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
		clickSaveButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateCustomerItemScreen();
		addPLusButton();
		insertCustomerFromLovField(commonFunc.getDataFromTestDataSheet("catalogCustomer"));
		addPlusInCustomerItem();
		insertIteminCustomerItemsAfterCustomerAssoFlag();
		priceClassSelectStdFormat(commonFunc.getDataFromTestDataSheet("priceClassName"));
		priceBasisSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("PriceBasisCatalogue"));
		catalogSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("catalogName"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
		clickDefaltFlag2();
		saveButtoninCustomerItems();
	}

	/**
	 * Creates Catalogue Price Similar UOM & Export Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createsCataloguePriceSimilarUOMAndExportCustomer() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage1();
		priceListPR.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("Item_Different_UOM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemAssoScreen();
		searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
		searchItemInFindPage();
		clickSearchButton();
		searchedItem();
		customerAssoFlag();
		commonFunc.hardWait(4000);
		changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
		clickSaveButton();
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateCustomerItemScreen();
		addPLusButton();
		insertCustomerFromLovField(commonFunc.getDataFromTestDataSheet("catalogExportCustomer"));
		addPlusInCustomerItem();
		insertIteminCustomerItemsAfterCustomerAssoFlag();
		priceClassSelectStdFormat(commonFunc.getDataFromTestDataSheet("priceClassName"));
		priceBasisSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("PriceBasisCatalogue"));
		catalogSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("catalogName"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
		clickDefaltFlag2();
		saveButtoninCustomerItems();
	}

	/**
	 * Creates Catalogue Price Dissimailar UOM & Export Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createsCataloguePriceDisSimilarUOMAndExportCustomer() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage1();
		priceListPR.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("Item_Different_UOM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemAssoScreen();
		searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
		searchItemInFindPage();
		clickSearchButton();
		searchedItem();
		customerAssoFlag();
		commonFunc.hardWait(2000);
		changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
		clickSaveButton();
		commonFunc.hardWait(1000);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateCustomerItemScreen();
		addPLusButton();
		insertCustomerFromLovField(commonFunc.getDataFromTestDataSheet("catalogExportCustomer"));
		addPlusInCustomerItem();
		insertIteminCustomerItemsAfterCustomerAssoFlag();
		priceClassSelectStdFormat(commonFunc.getDataFromTestDataSheet("priceClassName"));
		priceBasisSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("PriceBasisCatalogue"));
		catalogSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("catalogName"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
		clickDefaltFlag2();
		saveButtoninCustomerItems();
	}

	/**
	 * Creates Std Price With Similar Stock & Customer UOM Domestic Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createStandardPricewithSimilarStockandCustomerUOMDomesticCustomer() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage1();
		priceListPR.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("Item_Different_UOM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemAssoScreen();
		searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
		searchItemInFindPage();
		clickSearchButton();
		searchedItem();
		customerAssoFlag();
		changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
		clickSaveButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateCustomerItemScreen();
		addPLusButton();
		insertCustomerFromLovField(commonFunc.getDataFromTestDataSheet("catalogCustomer"));
		addPlusInCustomerItem();
		insertIteminCustomerItemsAfterCustomerAssoFlag();
		priceClassSelectStdFormat(commonFunc.getDataFromTestDataSheet("priceClassName"));
		priceBasisSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("PriceBasisStandard"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
		clickDefaltFlag2();
		saveButtoninCustomerItems();
	}

	/**
	 * Creates Std Price With Different Similar Stock & Customer UOM Domestic
	 * Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createStandardPricewithDiiferentSimilarStockandCustomerUOMDomesticCustomer() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage1();
		priceListPR.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("Item_Different_UOM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemAssoScreen();
		searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
		searchItemInFindPage();
		clickSearchButton();
		searchedItem();
		customerAssoFlag();
		changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
		clickSaveButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateCustomerItemScreen();
		addPLusButton();
		insertCustomerFromLovField(commonFunc.getDataFromTestDataSheet("catalogCustomer"));
		addPlusInCustomerItem();
		insertIteminCustomerItemsAfterCustomerAssoFlag();
		priceClassSelectStdFormat(commonFunc.getDataFromTestDataSheet("priceClassName"));
		priceBasisSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("PriceBasisStandard"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
		clickDefaltFlag2();
		saveButtoninCustomerItems();
	}

	/**
	 * Creates Std Price With Similar Stock Customer UOM Export Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createStandardPricewithSimilarStockandCustomerUOMExportCustomer() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage1();
		priceListPR.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("Item_Different_UOM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemAssoScreen();
		searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
		searchItemInFindPage();
		clickSearchButton();
		searchedItem();
		customerAssoFlag();
		changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
		clickSaveButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateCustomerItemScreen();
		addPLusButton();
		insertCustomerFromLovField(commonFunc.getDataFromTestDataSheet("catalogExportCustomer"));
		addPlusInCustomerItem();
		insertIteminCustomerItemsAfterCustomerAssoFlag();
		priceClassSelectStdFormat(commonFunc.getDataFromTestDataSheet("priceClassName"));
		priceBasisSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("PriceBasisStandard"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
		clickDefaltFlag2();
		saveButtoninCustomerItems();
	}

	/**
	 * Creates Std Price With Different Similar Stock & Customer UOM Export Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createStandardPricewithDifferentSimilarStockandCustomerUOMExportCustomer() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage1();
		priceListPR.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("Item_Different_UOM"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemAssoScreen();
		searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
		searchItemInFindPage();
		clickSearchButton();
		searchedItem();
		customerAssoFlag();
		changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
		clickSaveButton();
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.navigateCustomerItemScreen();
		addPLusButton();
		insertCustomerFromLovField(commonFunc.getDataFromTestDataSheet("catalogExportCustomer"));
		addPlusInCustomerItem();
		insertIteminCustomerItemsAfterCustomerAssoFlag();
		priceClassSelectStdFormat(commonFunc.getDataFromTestDataSheet("priceClassName"));
		priceBasisSelectInCustomerItems(commonFunc.getDataFromTestDataSheet("PriceBasisStandard"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
		clickDefaltFlag2();
		saveButtoninCustomerItems();
	}

	/**
	 * To Naviates Customer Sales Click Customer Asso Flag
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateQualityAndClickPreDispatch() {
		flag = webDB.waitForElement(CommonUiLocators.QualityTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.QualityTab, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.PreDispatchLabel, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Quality Tab Clicked Successfully");
				webDB.clickAnElement(CommonUiLocators.PreDispatchLabel, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.clickAnElement(CommonUiLocators.PreDispatchInputBox, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Successfully Checked a PreDispatch Flag");
				}
			}
		}
		assertTrue(flag);
	}
}
