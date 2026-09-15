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
import commonlocators.supplychainmanagement.OpenSalesOrderLocators;
import commonlocators.supplychainmanagement.PricelistPageLocators;
import commonlocators.supplychainmanagement.SalesCatalogueLocators;
import commonlocators.supplychainmanagement.SalesPriceListLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class SalesCataloguePrice {

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

	/**
	 * Creates Cataogue Price Similar UOM & Domestioc Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createsCataloguePriceSimilarUomAndDomesticCustomer(String min_Disc, String max_Disc) {
		String Sales_Value = RandomStringUtils.randomNumeric(3);
		if (Sales_Value.equals("000")) {
			Sales_Value = "100";
		}
//		String min_Disc = RandomStringUtils.randomNumeric(1);
//		if (min_Disc.equals("0")) {
//			min_Disc = "10";
//		}
//		String max_Disc = RandomStringUtils.randomNumeric(2);
//		if (max_Disc.equals("00")) {
//			max_Disc = "10";
//		}
		cusItems.createsCataloguePriceSimilarUOMAndDomesticCustomer();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePriceList_StdPrice_CataloguePrice();
		SalesPriceList.clickAddButton();
		clickCataloguePriceButton();
		clickAddButtonCatalogueScreen();
		commonFunc.hardWait(2000);
		insertItemCataloguePrice();
		insertCatalogueAgainstToItem(commonFunc.getDataFromTestDataSheet("catalogName"));
		insertCurcyAgainstToItem(commonFunc.getDataFromTestDataSheet("CatalogueCurcy"));
		insertCataloguePrice(Sales_Value, min_Disc, max_Disc);
		insertEffFromAndEffToDatesCataloguePrice(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		clickSaveButtonCataloguePrice();
		getCataloguePriceDocumentno();
		CataloguePriceDbValidation();
		clickCPPostButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getCatalogDocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
//		// forwardForApproval();
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		commonFunc.logoutUser();
//		commonFunc.hardWait(2000);
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"HariUsername");
//		approvalFunc.navigateToApprovals();
//		approvalFunc.filterDocumentsforApproval(VariableHandler.getCatalogDocno());
//		approvalFunc.verifyWorkFlowTypeForCataloguePrice(commonFunc.getDataFromTestDataSheet("WorkFlowType"));
//		approvalFunc.verifyWorkFlowDocForCatalog();
//		approvalFunc.verifyApproveWithDocNumber(VariableHandler.getCatalogDocno());
		flag = commonFunc.logoutUser();
	}

	/**
	 * Creates Catalogue Price Dissimilar UOM & Domestic Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createsCataloguePriceDisSimilarUomAndDomesticCustomer(String min_Disc, String max_Disc) {
		String Sales_Value = RandomStringUtils.randomNumeric(3);
		if (Sales_Value.equals("000")) {
			Sales_Value = "100";
		}
//		String min_Disc = RandomStringUtils.randomNumeric(1);
//		if (min_Disc.equals("0")) {
//			min_Disc = "10";
//		}
//		String max_Disc = RandomStringUtils.randomNumeric(2);
//		if (max_Disc.equals("00")) {
//			max_Disc = "10";
//		}
		cusItems.createsCataloguePriceDisSimilarUOMAndDomesticCustomer();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePriceList_StdPrice_CataloguePrice();
		SalesPriceList.clickAddButton();
		clickCataloguePriceButton();
		clickAddButtonCatalogueScreen();
		commonFunc.hardWait(2000);
		insertItemCataloguePrice();
		insertCatalogueAgainstToItem(commonFunc.getDataFromTestDataSheet("catalogName"));
		insertCurcyAgainstToItem(commonFunc.getDataFromTestDataSheet("CatalogueCurcy"));
		insertCataloguePrice(Sales_Value, min_Disc, max_Disc);
		insertEffFromAndEffToDatesCataloguePrice(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		clickSaveButtonCataloguePrice();
		getCataloguePriceDocumentno();
		CataloguePriceDbValidation();
		clickCPPostButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getCatalogDocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		commonFunc.logoutUser();
//		commonFunc.hardWait(2000);
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"HariUsername");
//		approvalFunc.navigateToApprovals();
//		approvalFunc.verifyWorkFlowTypeForCataloguePrice(commonFunc.getDataFromTestDataSheet("WorkFlowType"));
//		approvalFunc.verifyWorkFlowDocForCatalog();
//		approvalFunc.filterDocumentsforApproval(VariableHandler.getCatalogDocno());
//		approvalFunc.verifyApproveWithDocNumber(VariableHandler.getCatalogDocno());
		flag = commonFunc.logoutUser();
	}

	/**
	 * Creates Catalogue Price Similar & Export Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createsCataloguePriceSimilarUomAndExportCustomer(String min_Disc, String max_Disc) {
		String Sales_Value = RandomStringUtils.randomNumeric(3);
		if (Sales_Value.equals("000")) {
			Sales_Value = "100";
		}
//		String min_Disc = RandomStringUtils.randomNumeric(1);
//		if (min_Disc.equals("0")) {
//			min_Disc = "10";
//		}
//		String max_Disc = RandomStringUtils.randomNumeric(2);
//		if (max_Disc.equals("00")) {
//			max_Disc = "10";
//		}
		cusItems.createsCataloguePriceSimilarUOMAndExportCustomer();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePriceList_StdPrice_CataloguePrice();
		SalesPriceList.clickAddButton();
		clickCataloguePriceButton();
		clickAddButtonCatalogueScreen();
		commonFunc.hardWait(2000);
		insertItemCataloguePrice();
		insertCatalogueAgainstToItem(commonFunc.getDataFromTestDataSheet("catalogName"));
		insertCurcyAgainstToItem(commonFunc.getDataFromTestDataSheet("CatalogueCurcy"));
		insertCataloguePrice(Sales_Value, min_Disc, max_Disc);
		insertEffFromAndEffToDatesCataloguePrice(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		clickSaveButtonCataloguePrice();
		getCataloguePriceDocumentno();
		CataloguePriceDbValidation();
		clickCPPostButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getCatalogDocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));

//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		commonFunc.logoutUser();
//		commonFunc.hardWait(2000);
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"HariUsername");
//		approvalFunc.navigateToApprovals();
//		approvalFunc.verifyWorkFlowTypeForCataloguePrice(commonFunc.getDataFromTestDataSheet("WorkFlowType"));
//		approvalFunc.verifyWorkFlowDocForCatalog();
//		approvalFunc.filterDocumentsforApproval(VariableHandler.getCatalogDocno());
//		approvalFunc.verifyApproveWithDocNumber(VariableHandler.getCatalogDocno());
		flag = commonFunc.logoutUser();
	}

	/**
	 * Creates Catalogue Price Dissimailar UOM & Export Customer
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createsCataloguePriceDisSimilarUomAndExportCustomer(String min_Disc, String max_Disc) {
		String Sales_Value = RandomStringUtils.randomNumeric(3);
		if (Sales_Value.equals("000")) {
			Sales_Value = "100";
		}
//		String min_Disc = RandomStringUtils.randomNumeric(1);
//		if (min_Disc.equals("0")) {
//			min_Disc = "10";
//		}
//		String max_Disc = RandomStringUtils.randomNumeric(2);
//		if (max_Disc.equals("00")) {
//			max_Disc = "10";
//		}
		cusItems.createsCataloguePriceDisSimilarUOMAndExportCustomer();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePriceList_StdPrice_CataloguePrice();
		SalesPriceList.clickAddButton();
		clickCataloguePriceButton();
		clickAddButtonCatalogueScreen();
		commonFunc.hardWait(2000);
		insertItemCataloguePrice();
		insertCatalogueAgainstToItem(commonFunc.getDataFromTestDataSheet("catalogName"));
		insertCurcyAgainstToItem(commonFunc.getDataFromTestDataSheet("CatalogueCurcy"));
		insertCataloguePrice(Sales_Value, min_Disc, max_Disc);
		insertEffFromAndEffToDatesCataloguePrice(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		clickSaveButtonCataloguePrice();
		getCataloguePriceDocumentno();
		CataloguePriceDbValidation();
		clickCPPostButton();
		commonFunc.checkRefreshCellsDisappear();
		verifyWaitingForApproval1();
		commonFunc.hardWait(2000);
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getCatalogDocno(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
//		commonFunc.checkRefreshCellsDisappear();
//		commonFunc.hardWait(2000);
//		commonFunc.logoutUser();
//		commonFunc.hardWait(2000);
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"HariUsername");
//		approvalFunc.navigateToApprovals();
//		approvalFunc.verifyWorkFlowTypeForCataloguePrice(commonFunc.getDataFromTestDataSheet("WorkFlowType"));
//		approvalFunc.verifyWorkFlowDocForCatalog();
//		approvalFunc.filterDocumentsforApproval(VariableHandler.getCatalogDocno());
//		approvalFunc.verifyApproveWithDocNumber(VariableHandler.getCatalogDocno());
		flag = commonFunc.logoutUser();
	}

	/**
	 * Cancel Catalog price
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createsCancelCatalogPrice(String min_Disc, String max_Disc) {
		String Sales_Value = RandomStringUtils.randomNumeric(3);
		if (Sales_Value.equals("000")) {
			Sales_Value = "100";
		}
//		String min_Disc = RandomStringUtils.randomNumeric(1);
//		if (min_Disc.equals("0")) {
//			min_Disc = "10";
//		}
//		String max_Disc = RandomStringUtils.randomNumeric(2);
//		if (max_Disc.equals("00")) {
//			max_Disc = "10";
//		}
		cusItems.createsCataloguePriceDisSimilarUOMAndExportCustomer();
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePriceList_StdPrice_CataloguePrice();
		SalesPriceList.clickAddButton();
		clickCataloguePriceButton();
		clickAddButtonCatalogueScreen();
		commonFunc.hardWait(2000);
		insertItemCataloguePrice();
		insertCatalogueAgainstToItem(commonFunc.getDataFromTestDataSheet("catalogName"));
		insertCurcyAgainstToItem(commonFunc.getDataFromTestDataSheet("CatalogueCurcy"));
		insertCataloguePrice(Sales_Value, min_Disc, max_Disc);
		insertEffFromAndEffToDatesCataloguePrice(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		clickSaveButtonCataloguePrice();
		getCataloguePriceDocumentno();
		CataloguePriceDbValidation();
		clickDeleteButton();
		commonFunc.hardWait(2000);
		clickSearchButton();
		takenCatalogPriceRecordInFindPage();
		commonFunc.logoutUser();
	}

	/**
	 * Creates Multiples Catalogue Price
	 * 
	 * @author Kowshik-RMITS
	 */
	public void createsMultiplesCataloguePrice() {
		createCataloguePricewithSamePriceClassAndMultipleItems(
				commonFunc.getDataFromTestDataSheet("Item_Different_UOM"), 2);
	}

	/**
	 * Click Delete Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickDeleteButton() {
		flag = webDB.waitForElement(SalesCatalogueLocators.DeleteButton, ElementType.CssSelector);
		if (flag) {
			webDB.clickAnElement(SalesCatalogueLocators.DeleteButton, ElementType.CssSelector);
			flag = webDB.waitForElement(SalesCatalogueLocators.SearchButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Clicked a Deleted Record ");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Search Button Click
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickSearchButton() {
		flag = webDB.waitForElement(SalesCatalogueLocators.SearchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesCatalogueLocators.SearchButton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesPriceListLocators.PriceListFlagFindPage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Clicked a Search Button");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Taken Catalog Price Cancelled Record
	 * 
	 * @author Kowshik-RMITS
	 */
	public void takenCatalogPriceRecordInFindPage() {
		flag = webDB.waitForElement(SalesPriceListLocators.PriceListFlagFindPage, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesPriceListLocators.PriceListFlagFindPage, ElementType.Xpath);
			flag = webDB.waitForElement(SalesPriceListLocators.StandardFlagFindPage, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesPriceListLocators.StandardFlagFindPage, ElementType.Xpath);
				flag = webDB.waitForElement(SalesPriceListLocators.SearchButtoninFindPage, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SalesPriceListLocators.SearchButtoninFindPage, ElementType.Xpath);
					flag = webDB.waitForElement(SalesPriceListLocators.SearchBoxFind, ElementType.Xpath);
					if (flag) {
						webDB.sendTextToAnElement(SalesPriceListLocators.SearchBoxFind,
								VariableHandler.getCatalogDocno(), ElementType.Xpath);
						flag = webDB.waitForElement(SalesPriceListLocators.GoButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Successfully Populated a Cancelled Record");

						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Click Catalogue Price Button in Find Page
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickCataloguePriceButton() {
		flag = webDB.waitForElement(SalesCatalogueLocators.CataloguePriceButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesCatalogueLocators.CataloguePriceButton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesCatalogueLocators.AddButtonCatalogue, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Clicked Catalogue Price");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Inserted Catalogue Price Item
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickAddButtonCatalogueScreen() {
		flag = webDB.waitForElement(SalesCatalogueLocators.AddButtonCatalogue, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesCatalogueLocators.AddButtonCatalogue, ElementType.Xpath);
			flag = webDB.waitForElement(SalesCatalogueLocators.CatalogueItemField, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Clicked a Item Lov");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Inserted a Item In Catalogue Price Master Screen
	 * 
	 * @author Kowshik-RMITS
	 */
	public void insertItemCataloguePrice() {
		flag = webDB.waitForElement(SalesCatalogueLocators.CatalogueItemField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesCatalogueLocators.CatalogueItemField, ElementType.Xpath);
			webDB.moveToElement(SalesCatalogueLocators.CatalogueItemField, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesCatalogueLocators.CatalogueItemLov,
					VariableHandler.getItemName());
			if (flag) {
				logs.logging("INFO", "Successfully Inserted a Catalogue Item " + VariableHandler.getItemName());
			}
		}
		assertTrue(flag);
	}

	/**
	 * TO Inserted a Catalogue Against To Items
	 * 
	 * @author Kowshik-Rmits
	 * @param catalogName
	 */
	public void insertCatalogueAgainstToItem(String catalogName) {
		flag = webDB.waitForElement(SalesCatalogueLocators.CatalogueField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesCatalogueLocators.CatalogueField, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesCatalogueLocators.CatalogueFieldLov, catalogName);
			if (flag) {
				logs.logging("INFO", "Successfully Inserted a Catalogue Price" + catalogName);
				webDB.pressTabKey();
			}
		}
		assertTrue(flag);
	}

	/**
	 * Insert Curcy Against To Item
	 * 
	 * @author Kowshik-RMITS
	 * @param CatalogueCurcy
	 */
	public void insertCurcyAgainstToItem(String CatalogueCurcy) {
		commonFunc.searchAndSelectValueInFilters(SalesCatalogueLocators.CurcyFieldLov, CatalogueCurcy);
		webDB.pressEnterKey();
		flag = webDB.waitForElement(SalesCatalogueLocators.PriceFieldClick, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Successfully Inserted a Curcy " + CatalogueCurcy);
		}
		assertTrue(flag);
	}

	/**
	 * Insert a Catalogue Price
	 * 
	 * @author Kowshik-RMITS
	 */
	public void insertCataloguePrice(String Sales_Value, String min_Disc, String max_Disc) {
		verifyClickAndEnterValueInTableFields(SalesCatalogueLocators.PriceFieldClick, SalesCatalogueLocators.PriceField,
				Sales_Value);
		if (flag) {
			commonFunc.hardWait(1500);
			logs.logging("INFO", "Successfully Inserted a Sales Prices " + Sales_Value);
			verifyClickAndEnterValueInTableFields(SalesCatalogueLocators.MinDiscountPercentField(1),
					SalesCatalogueLocators.MinDiscInsert, min_Disc);
			if (flag) {
				commonFunc.hardWait(1500);
				logs.logging("INFO", " Successfully Inseted a Mini Disc Percent" + min_Disc);
				verifyClickAndEnterValueInTableFields(SalesCatalogueLocators.MaxDiscountPercentField(1),
						SalesCatalogueLocators.MaxDiscInsert, max_Disc);
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
	 * Insert a Efficitove date & Efficitive To
	 * 
	 * @author Kowshik-RMITS
	 * @param EFF_DATE
	 * @param EFF_TO
	 */
	public void insertEffFromAndEffToDatesCataloguePrice(String EFF_DATE, String EFF_TO) {
		verifyClickAndEnterValueInTableFields(SalesCatalogueLocators.EffitiveFromDateField(1),
				SalesCatalogueLocators.EffFromDateClick, EFF_DATE);
		verifyClickAndEnterValueInTableFields(SalesCatalogueLocators.EffToDateField(1),
				SalesCatalogueLocators.EffToDateClick, EFF_TO);
		commonFunc.hardWait(2000);
	}

	/**
	 * Clicked a Save Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickSaveButtonCataloguePrice() {
		flag = webDB.waitForElement(SalesCatalogueLocators.Savebutton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesCatalogueLocators.Savebutton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesCatalogueLocators.PostButton, ElementType.CssSelector);
			if (flag) {
				logs.logging("INFO", "Successfully Clicked a SaveButton");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Click PostButton
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickCPPostButton() {
//		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(SalesCatalogueLocators.PostButtonField(1), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesCatalogueLocators.PostButtonField(1), ElementType.Xpath);
			logs.logging("INFO ", "Successfully Click The Post Button ");
		}
		assertTrue(flag);
	}

	/**
	 * Waiting For Approval
	 * 
	 * @author Kowshik-RMITS
	 * @return boolean flag
	 */
	private boolean verifyWaitingForApproval1() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(OpenSalesOrderLocators.OpsoWorkApprovalFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(OpenSalesOrderLocators.OpsoWorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			// commonFunc.searchAndSelectValueInFilters(OpenSalesOrderLocators.OpsoForwardApproval,
			// commonFunc.getDataFromTestDataSheet("HodValue"));
			// commonFunc.hardWait(2000);
			// webDB.switchToFrame(OpenSalesOrderLocators.OpsoWorkApprovalFrame,
			// ElementType.Xpath);
			// logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(OpenSalesOrderLocators.OpsoOkButtonForwardCat, ElementType.Xpath);
			if (flag) {
				webDB.javaScriptClickAnElement(OpenSalesOrderLocators.OpsoOkButtonForwardCat, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
			}
			assertTrue(flag);
		} else {
			flag = false;
			VariableHandler.setApprovalFlag(flag);
		}
		return flag;
	}

	/**
	 * Catalogue Price Document Numeber
	 * 
	 * @author Kowshik-RMITS
	 */
	public void getCataloguePriceDocumentno() {
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(SalesCatalogueLocators.DocumentNoField(1), ElementType.Xpath);
		if (flag) {
			String CatalogDocNo = webDB.getTextFromElement(SalesCatalogueLocators.DocumentNoField(1),
					ElementType.Xpath);
			VariableHandler.setCatalogDocno(CatalogDocNo);
			logs.logging("info", "Get Price List Document no " + VariableHandler.getCatalogDocno());
			commonFunc.hardWait(2000);
		}
		assertTrue(flag);
	}

	/**
	 * Creates Catalogue Price With Same Price Class & Multiples Items
	 * 
	 * @author Kowshik-RMITS
	 * @param Item1
	 * @param i
	 */
	public void createCataloguePricewithSamePriceClassAndMultipleItems(String Item1, int i) {
		String Sales_Value1 = RandomStringUtils.randomNumeric(3);
		if (Sales_Value1.equals("000")) {
			Sales_Value1 = "100";
		}
		String min_Disc1 = RandomStringUtils.randomNumeric(1);
		if (min_Disc1.equals("0")) {
			min_Disc1 = "10";
		}
		String max_Disc1 = RandomStringUtils.randomNumeric(2);
		if (max_Disc1.equals("00")) {
			max_Disc1 = "10";
		}
		int maxAttempts = 1;
		boolean success = false;
		ArrayList<String> GeneratedItem = new ArrayList<String>();
		for (int attempt = 1; attempt <= maxAttempts; attempt++) {
//		        commonFunc.hardWait(4000);
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
							commonFunc.getDataFromTestDataSheet("Items"), attempt, Sales_Value1, min_Disc1, max_Disc1);
					GeneratedItem.add(VariableHandler.getItemName());
					success = true;
				}
			}
			// logs.logging("WARN", "Attempt " + attempt + " failed. Retrying...");
		}
		System.out.println(GeneratedItem.size());
		for (int j = 1; j <= GeneratedItem.size(); j++) {
			ExcelUtils excelUtils = new ExcelUtils();
			String SheetName1 = "Items";
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
					+ "PR_ITEMS.xlsx";
			excelUtils.writeDataInExcel(Filepath, SheetName1, j, 0, GeneratedItem.get(j - 1));
		}
		assertTrue(flag);
	}

	/**
	 * Verify Generate items
	 * 
	 * @author Kowshik-RMITS
	 * @param Itm
	 * @param SheetName
	 * @param i
	 * @param Sales_Value
	 * @param min_Disc
	 * @param max_Disc
	 */
	public void verifyGenerateItemSim1(String Itm, String SheetName, int i, String Sales_Value, String min_Disc,
			String max_Disc) {
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
						// item Asso
						commonFunc.navigateItemAssoScreen();
						cusItems.searchItemInUnitField(commonFunc.getDataFromTestDataSheet("Unit_100"));
						cusItems.searchItemInFindPageMultiples();
						cusItems.clickSearchButton();
						cusItems.searchedItem();
						cusItems.customerAssoFlag();
						cusItems.changesNotApplicabeType(commonFunc.getDataFromTestDataSheet("QcFlag"));
						cusItems.clickSaveButton();
						// cusItems.clickedActiveButton();
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
						cusItems.saveButtoninCustomerItems();
						commonFunc.hardWait(3000);
						commonFunc.checkRefreshCellsDisappear();
						cusItems.clickDefaltFlag2();
						cusItems.saveButtoninCustomerItems();
						commonFunc.logoutUser();
						commonFunc.checkRefreshCellsDisappear();
						// to insert a Catalogue Price
						commonFunc.validCommonLogin(System.getProperty("AdminUsername"),
								System.getProperty("LoginPassword"), "AdminUsername");
						commonFunc.navigatePriceList_StdPrice_CataloguePrice();
						SalesPriceList.clickAddButton();
						clickCataloguePriceButton();
						clickAddButtonCatalogueScreen();
						commonFunc.hardWait(2000);
						insertItemCataloguePrice();
						insertCatalogueAgainstToItem(commonFunc.getDataFromTestDataSheet("catalogName"));
						insertCurcyAgainstToItem(commonFunc.getDataFromTestDataSheet("CatalogueCurcy"));
						insertCataloguePrice(Sales_Value, min_Disc, max_Disc);
						insertEffFromAndEffToDatesCataloguePrice(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
								commonFunc.getDataFromTestDataSheet("EFF_TO"));
						clickSaveButtonCataloguePrice();
						getCataloguePriceDocumentno();
						CataloguePriceDbValidation();
						clickCPPostButton();
						commonFunc.checkRefreshCellsDisappear();
						verifyWaitingForApproval1();
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(2000);
						commonFunc.logoutUser();
						commonFunc.hardWait(2000);
						commonFunc.validCommonLogin(System.getProperty("HariUsername"),
								System.getProperty("LoginPassword"), "HariUsername");
						approvalFunc.navigateToApprovals();
						approvalFunc.verifyWorkFlowTypeForCataloguePrice(
								commonFunc.getDataFromTestDataSheet("WorkFlowType"));
						approvalFunc.verifyWorkFlowDocForCatalog();
						approvalFunc.filterDocumentsforApproval(VariableHandler.getCatalogDocno());
						approvalFunc.verifyApproveWithDocNumber(VariableHandler.getCatalogDocno());
						flag = commonFunc.logoutUser();
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Generate The List Of Items
	 * 
	 * @author Kowshik-RMITS
	 * @return String
	 */
	public List<String> getGeneratedItems() {
		return getGeneratedItems();
	}

	/**
	 * Verify Items Post
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
					commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
							"HariUsername");
					approvalFunc.navigateToApprovals();
					approvalFunc.filterDocumentsforApproval(item);
					approvalFunc.verifyApproveWithDocNumber(item);
					commonFunc.logoutUser();
//					commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//							"AdminUsername");
//					commonFunc.navigateItemPage();
				}
			}
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

	/**
	 * DataBase Validation Of Both Value Type
	 * 
	 * @author Kowshik-RMITS
	 */
	public void CataloguePriceDbValidation() {
		verifyAssertionGetTextValidation(SalesCatalogueLocators.DocNODb, dbCommonFunctions.SalesCataloguePrice(
				VariableHandler.getCatalogDocno(), commonFunc.getDataFromTestDataSheet("CatPriceDbDocno")));
		verifyAssertionGetTextValidation(SalesCatalogueLocators.ItemsIdDb, dbCommonFunctions.SalesCataloguePrice(
				VariableHandler.getCatalogDocno(), commonFunc.getDataFromTestDataSheet("CatPriceDbItemId")));
		verifyAssertionGetTextValidation(SalesCatalogueLocators.ItemsDescDb, dbCommonFunctions.SalesCataloguePrice(
				VariableHandler.getCatalogDocno(), commonFunc.getDataFromTestDataSheet("CatPriceDbItemDesc")));
		verifyAssertionGetTextValidation(SalesCatalogueLocators.SalesUOMdB, dbCommonFunctions.SalesCataloguePrice(
				VariableHandler.getCatalogDocno(), commonFunc.getDataFromTestDataSheet("CatPriceDbSalesUom")));
		verifyAssertionGetTextValidation(SalesCatalogueLocators.CurcyDb, dbCommonFunctions.SalesCataloguePrice(
				VariableHandler.getCatalogDocno(), commonFunc.getDataFromTestDataSheet("CatPriceDbCurcyId")));
		verifyAssertionGetTextValidation(SalesCatalogueLocators.SalesPrice, dbCommonFunctions.SalesCataloguePrice(
				VariableHandler.getCatalogDocno(), commonFunc.getDataFromTestDataSheet("CatPriceDbSalesPrice")));
		verifyAssertionGetTextValidation(SalesCatalogueLocators.MinDiscPercentDb, dbCommonFunctions.SalesCataloguePrice(
				VariableHandler.getCatalogDocno(), commonFunc.getDataFromTestDataSheet("CatPriceDbMinDisc")));
		verifyAssertionGetTextValidation(SalesCatalogueLocators.MaxDiscPercentDb, dbCommonFunctions.SalesCataloguePrice(
				VariableHandler.getCatalogDocno(), commonFunc.getDataFromTestDataSheet("CatPriceDbMaxiDisc")));
		verifyAssertionGetTextValidation(SalesCatalogueLocators.EffFromDateDb, dbCommonFunctions.SalesCataloguePrice(
				VariableHandler.getCatalogDocno(), commonFunc.getDataFromTestDataSheet("CatPriceDbEffDate")));
//		verifyAssertionGetTextValidation(SalesCatalogueLocators.EffToDateDb, dbCommonFunctions.SalesCataloguePrice(
//				VariableHandler.getCatalogDocno(), commonFunc.getDataFromTestDataSheet("CatPriceEffToDate")));
		verifyAssertionGetTextValidation(SalesCatalogueLocators.StatusDb, dbCommonFunctions.SalesCataloguePrice(
				VariableHandler.getCatalogDocno(), commonFunc.getDataFromTestDataSheet("CatPriceDbStatus")));
	}
}
