package commonfunctions.supplychainmanagement;

import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
import commonlocators.supplychainmanagement.OpenSalesOrderLocators;
import commonlocators.supplychainmanagement.OpenSoAmdLocators;
import commonlocators.supplychainmanagement.SalesPriceListLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class SalesPriceListFunctions {

	private boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	private ApprovalPageFunctions approveFunc = new ApprovalPageFunctions();
	private static ItemDetailsHandler itemDetailHandler = new ItemDetailsHandler();
	private static OpenSalesOrderFunctions openso = new OpenSalesOrderFunctions();
	private static DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();
	private static OpenSoAmdFunctions opensoamd = new OpenSoAmdFunctions();
	private static CustomerItemsFunctions cusItems = new CustomerItemsFunctions();
	private CommonPayrollFunctions commonPayrollFunc = new CommonPayrollFunctions();

	/**
	 * Verifty Creates Price List Entry
	 * 
	 * @author Kowshik-RMITS
	 * @return
	 */
	public boolean veriftyCreatePriceListEntry() {

		String Sales_Value = RandomStringUtils.randomNumeric(3);
		if (Sales_Value.equals("000")) {
			Sales_Value = "100";
		}
		String Disc_Value = RandomStringUtils.randomNumeric(2);
		if (Disc_Value.equals("00")) {
			Disc_Value = "10";
		}

		cusItems.itemAssociateWithPriceListItem();

		commonFunc.logoutUser();

		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePriceList_StdPrice_CataloguePrice();
		clickAddButton();
		clickPriceListButton();
		clickPlusButton();
		insertLocationLov(commonFunc.getDataFromTestDataSheet("LOCATION100"));
		insertPricelistPrefix(commonFunc.getDataFromTestDataSheet("Prefix"));
		insertCustomerField(commonFunc.getDataFromTestDataSheet("CustomerName"));
		insertItemField();
		insertSalesPrice(Sales_Value, Disc_Value);
		clickSaveButton();
//		insertEffitiveDates(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
//				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		clickSaveButton();
		getPriceListDocumentno();
		PriceListDataBaseValidation();
		clickPostButton();
		verifyWaitingForApproval1();
//		commonFunc.hardWait(2000);
//		commonFunc.logoutUser();
//		commonFunc.hardWait(2000);
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"HariUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getPriceListDocno());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getPriceListDocno());
		flag = commonFunc.logoutUser();

		return flag;

	}

	/**
	 * Creates PriceList With Different UOM
	 * 
	 * @author Kowshik-RMITS
	 * @return
	 */
	public boolean CreatePriceListEntryWithDifferentUOM() {

		String Sales_Value = RandomStringUtils.randomNumeric(3);
		if (Sales_Value.equals("000")) {
			Sales_Value = "100";
		}
		String Disc_Value = RandomStringUtils.randomNumeric(2);
		if (Disc_Value.equals("00")) {
			Disc_Value = "10";
		}

		cusItems.itemAssociatePriceListItemDifferentUOM();

		commonFunc.logoutUser();

		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePriceList_StdPrice_CataloguePrice();
		clickAddButton();
		clickPriceListButton();
		clickPlusButton();
		insertLocationLov(commonFunc.getDataFromTestDataSheet("LOCATION100"));
		insertPricelistPrefix(commonFunc.getDataFromTestDataSheet("Prefix"));
		insertCustomerField(commonFunc.getDataFromTestDataSheet("CustomerName"));
		insertItemField();
		insertSalesPrice(Sales_Value, Disc_Value);
		clickSaveButton();
//		insertEffitiveDates(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
//				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		clickSaveButton();
		getPriceListDocumentno();
		PriceListDataBaseValidation();
		clickPostButton();
		verifyWaitingForApproval1();
//		commonFunc.hardWait(2000);
//		commonFunc.logoutUser();
//		commonFunc.hardWait(2000);
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"HariUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getPriceListDocno());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getPriceListDocno());
		flag = commonFunc.logoutUser();

		return flag;
	}

	/**
	 * Create Price List Entry With Different Price Classes
	 * 
	 * @author Kowshik-RMITS
	 * @return
	 */
	public boolean CreatePriceListEntryWithDifferentPriceClasses() {

		String Sales_Value = RandomStringUtils.randomNumeric(3);
		if (Sales_Value.equals("000")) {
			Sales_Value = "100";
		}
		String Disc_Value = RandomStringUtils.randomNumeric(2);
		if (Disc_Value.equals("00")) {
			Disc_Value = "10";
		}

		cusItems.itemAssociatePriceListItemDifferentPriceClass();

		commonFunc.logoutUser();

		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePriceList_StdPrice_CataloguePrice();
		clickAddButton();
		clickPriceListButton();
		clickPlusButton();
		insertLocationLov(commonFunc.getDataFromTestDataSheet("LOCATION100"));
		insertPricelistPrefix(commonFunc.getDataFromTestDataSheet("Prefix"));
		insertCustomerField(commonFunc.getDataFromTestDataSheet("CustomerName"));
		insertItemField();
		insertSalesPrice(Sales_Value, Disc_Value);
		clickSaveButton();
//		insertEffitiveDates(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
//				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		clickSaveButton();
		getPriceListDocumentno();
		PriceListDataBaseValidation();
		clickPostButton();
		verifyWaitingForApproval1();
//		commonFunc.hardWait(2000);
//		commonFunc.logoutUser();
//		commonFunc.hardWait(2000);
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"HariUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getPriceListDocno());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getPriceListDocno());
		flag = commonFunc.logoutUser();

		return flag;
	}

	/**
	 * Create PriceList Entry Approved With Amdend Record
	 * 
	 * @author Kowshik-RMITS
	 * @return
	 */
	public boolean CreatePriceListEntryApprovedWithAmendRecord() {

		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String Today = date.format(formatter);

		String Sales_Value = RandomStringUtils.randomNumeric(3);
		if (Sales_Value.equals("000")) {
			Sales_Value = "100";
		}
		String Disc_Value = RandomStringUtils.randomNumeric(2);
		if (Disc_Value.equals("00")) {
			Disc_Value = "10";
		}

		cusItems.itemAssociatePriceListItemDifferentPriceClass();

		commonFunc.logoutUser();

		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePriceList_StdPrice_CataloguePrice();
		clickAddButton();
		clickPriceListButton();
		clickPlusButton();
		insertLocationLov(commonFunc.getDataFromTestDataSheet("LOCATION100"));
		insertPricelistPrefix(commonFunc.getDataFromTestDataSheet("Prefix"));
		insertCustomerField(commonFunc.getDataFromTestDataSheet("CustomerName"));
		insertItemField();
		insertSalesPrice(Sales_Value, Disc_Value);
		clickSaveButton();
//		insertEffitiveDates(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
//				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		clickSaveButton();
		getPriceListDocumentno();
		PriceListDataBaseValidation();
		clickPostButton();
		verifyWaitingForApproval1();
//		commonFunc.hardWait(2000);
//		commonFunc.logoutUser();
//		commonFunc.hardWait(2000);
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"HariUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getPriceListDocno());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getPriceListDocno());
		flag = commonFunc.logoutUser();

		// amd the record
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePriceList_StdPrice_CataloguePrice();

		searchItemINFindPageForAmdendTheRecord(commonFunc.getDataFromTestDataSheet("LOCATION100"),
				commonFunc.getDataFromTestDataSheet("CUSTOMER_ID"));
		uncheckRadioButtonInFindPage();
		clickSearchButtonInFindPage();

		clickHyperLinkPriceLink();
		clickAmdendButton();
		insertCloseDateForAmend(Today);
		insertSalesPrice(Sales_Value, Disc_Value);
		clickSaveButton();
		getPriceListDocumentno();
		clickPostButton();
		verifyWaitingForApproval1();
//		commonFunc.hardWait(2000);
//		commonFunc.logoutUser();
//		commonFunc.hardWait(2000);
//		commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("LoginPassword"),
//				"HariUsername");
//		approveFunc.navigateToApprovals();
//		approveFunc.filterDocumentsforApproval(VariableHandler.getPriceListDocno());
//		approveFunc.verifyApproveWithDocNumber(VariableHandler.getPriceListDocno());
		flag = commonFunc.logoutUser();

		return flag;
	}

	/**
	 * Create Cancel PriceList Entry
	 * 
	 * @author Kowshik-RMITS
	 * @return
	 */
	public boolean createCancelPriceListEntry() {

		String Sales_Value = RandomStringUtils.randomNumeric(3);
		if (Sales_Value.equals("000")) {
			Sales_Value = "100";
		}
		String Disc_Value = RandomStringUtils.randomNumeric(2);
		if (Disc_Value.equals("00")) {
			Disc_Value = "10";
		}

		cusItems.createsCancelPriceListRecord();

		commonFunc.logoutUser();

		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePriceList_StdPrice_CataloguePrice();
		clickAddButton();
		clickPriceListButton();
		clickPlusButton();
		insertLocationLov(commonFunc.getDataFromTestDataSheet("LOCATION100"));
		insertPricelistPrefix(commonFunc.getDataFromTestDataSheet("Prefix"));
		insertCustomerField(commonFunc.getDataFromTestDataSheet("CustomerName"));
		insertItemField();
		insertSalesPrice(Sales_Value, Disc_Value);
		clickSaveButton();
//		insertEffitiveDates(commonFunc.getDataFromTestDataSheet("EFF_DATE"),
//				commonFunc.getDataFromTestDataSheet("EFF_TO"));
		clickSaveButton();
		getPriceListDocumentno();
		PriceListDataBaseValidation();
		clickCancelButtonRecord();
		clickSearchButton();
		searchRecordFindPage();

		commonFunc.logoutUser();

		return flag;
	}

	/**
	 * Verifty Waiting For Approval
	 * 
	 * @author Kowshik-RMITS
	 * @return
	 */
	private boolean verifyWaitingForApproval1() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(OpenSalesOrderLocators.OpsoWorkApprovalFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(OpenSalesOrderLocators.OpsoWorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(OpenSalesOrderLocators.OpsoForwardApproval,
					commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.hardWait(2000);
			webDB.switchToFrame(OpenSalesOrderLocators.OpsoWorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(OpenSalesOrderLocators.OpsoOkButtonForward, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(OpenSalesOrderLocators.OpsoOkButtonForward, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
			}
			commonFunc.hardWait(2000);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPriceListDocno(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			assertTrue(flag);
		} else {
			flag = false;
			VariableHandler.setApprovalFlag(flag);
		}
		return flag;
	}

	/**
	 * click PriceList Std Catalog Node
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickPriceListStdCatalogNode() {
		flag = webDB.waitForElement(OpenSoAmdLocators.Transactions, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenSoAmdLocators.Transactions, ElementType.Xpath);
			flag = webDB.waitForElement(SalesPriceListLocators.PRICEStdCatalogNode, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesPriceListLocators.PRICEStdCatalogNode, ElementType.Xpath);
				flag = webDB.waitForElement(SalesPriceListLocators.AddButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Successfully Entered Price list / Standard Price /  Catalogue Price ");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Click Add Button
	 * 
	 * @author KowshiK-RMITS
	 */

	public void clickAddButton() {
		flag = webDB.waitForElement(SalesPriceListLocators.AddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesPriceListLocators.AddButton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesPriceListLocators.PriceList, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Clicked Add Button");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Click Price List Button
	 * 
	 * @author Kowshik-RMITS
	 */

	public void clickPriceListButton() {
		flag = webDB.waitForElement(SalesPriceListLocators.PriceList, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesPriceListLocators.PriceList, ElementType.Xpath);
			flag = webDB.waitForClickElement(SalesPriceListLocators.PriceListSingle1, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.clickAnElement(SalesPriceListLocators.PriceListSingle1, ElementType.Xpath);
				flag = webDB.waitForElement(SalesPriceListLocators.PlusButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Successfully Clicked Price List");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Click Plus Button Price List Screen
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickPlusButton() {
		flag = webDB.waitForElement(SalesPriceListLocators.PlusButton, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(2000);
			webDB.moveToElement(SalesPriceListLocators.PlusButton, ElementType.Xpath);
			webDB.clickAnElement(SalesPriceListLocators.PlusButton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesPriceListLocators.LocationLovButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Clicked Plus Button");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Insert Loation Id
	 * 
	 * @author Kowshik-RMITS
	 * @param LOCATION100
	 */
	public void insertLocationLov(String LOCATION100) {
		commonFunc.searchAndSelectValueInFilters(SalesPriceListLocators.LocationLovButton, LOCATION100);
		flag = webDB.waitForElement(SalesPriceListLocators.PricePfxFiled, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Successfully Inserted Locution " + LOCATION100);
		}
		assertTrue(flag);
	}

	/**
	 * Insert Pricelist Prefix
	 * 
	 * @author Kowshik-RMITS
	 * @param Prefix
	 */
	public void insertPricelistPrefix(String Prefix) {
		flag = webDB.waitForElement(SalesPriceListLocators.PricePfxFiled, ElementType.Xpath);
		if (flag) {
			// webDB.pressTabKey();
			webDB.clickAnElement(SalesPriceListLocators.PricePfxFiled, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesPriceListLocators.PriceListPfx, Prefix);
			flag = webDB.waitForElement(SalesPriceListLocators.CustomerIdField, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Inserted Prefix" + Prefix);
			}
		}
		assertTrue(flag);
	}

	/**
	 * Insert Customer Field PriceList
	 * 
	 * @author Kowshik-RMITS
	 * @param CUSTOMER_ID
	 */
	public void insertCustomerField(String CUSTOMER_ID) {
		flag = webDB.waitForElement(SalesPriceListLocators.CustomerIdField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesPriceListLocators.CustomerIdField, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesPriceListLocators.CustomerFieldLov, CUSTOMER_ID);
			flag = webDB.waitForElement(SalesPriceListLocators.ItemField, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Inserted Customer Id" + CUSTOMER_ID);
			}
		}
		assertTrue(flag);
	}

	/**
	 * Inserted Item PriceList
	 * 
	 * @author Kowshik-RMITS
	 * @param ITEMPL
	 */

	public void insertItemField() {
		flag = webDB.waitForElement(SalesPriceListLocators.ItemField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesPriceListLocators.ItemField, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesPriceListLocators.ItemFieldLov,
					VariableHandler.getItemName());

			if (flag) {
				logs.logging("INFO", "Successfully Inseted Item" + VariableHandler.getItemName());
			}
		}
		assertTrue(flag);
	}

	/**
	 * Inserted Sales Price
	 * 
	 * @author Kowshik-RMITS
	 * @param Sales_Value
	 */
	public void insertSalesPrice(String Sales_Value, String Disc_Value) {
		verifyClickAndEnterValueInTableFields(SalesPriceListLocators.SalesPriceFields(1),
				SalesPriceListLocators.SalesPrice, Sales_Value);
		webDB.waitForElement(SalesPriceListLocators.SalesDiscFields(1), ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Successfully Entered a Sales Values");
			verifyClickAndEnterValueInTableFields(SalesPriceListLocators.SalesDiscFields(1),
					SalesPriceListLocators.SalesDiscPercent, Disc_Value);
			flag = webDB.waitForElement(SalesPriceListLocators.SaveButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Entered a Sales Values");
			}

		}
		assertTrue(flag);
	}

	/**
	 * Insert Sales Disc Percent
	 * 
	 * @author Kowshik-RMITS
	 * @param Disc_Value
	 */
	public void insertSalesDiscPercent(String Disc_Value) {
		flag = webDB.waitForElement(SalesPriceListLocators.SalesDiscPercent, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesPriceListLocators.SalesDiscPercent, ElementType.Xpath);
			webDB.sendTextToAnElement(SalesPriceListLocators.SalesDiscPercent, Disc_Value, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Inserted Disc Percent " + Disc_Value);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This is Insert a Efficitive From Date & Efficitive To Date post
	 * 
	 * @author Kowshik-RMITS
	 * @param EFF_DATE
	 * @param EFF_TO
	 * 
	 */
	public void insertEffitiveDates(String EFF_DATE, String EFF_TO) {
		verifyClickAndEnterValueInTableFields(SalesPriceListLocators.EffDateField(1),
				SalesPriceListLocators.EffiticeFrom, EFF_DATE);

		verifyClickAndEnterValueInTableFields(SalesPriceListLocators.EffDateTo(1), SalesPriceListLocators.EffiticeTo,
				EFF_TO);
		commonFunc.hardWait(2000);

	}

	/**
	 * Click a Save Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickSaveButton() {
		flag = webDB.waitForElement(SalesPriceListLocators.SaveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesPriceListLocators.SaveButton, ElementType.Xpath);
			logs.logging("INFO", "Successfully Save Button Record");
			commonFunc.hardWait(3000);

		}
	}

	/**
	 * Click Post Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickPostButton() {

		flag = webDB.waitForElement(SalesPriceListLocators.PR_Post_Btn, ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(2000);
			webDB.moveToElement(SalesPriceListLocators.PR_Post_Btn, ElementType.Xpath);

			webDB.clickAnElement(SalesPriceListLocators.PR_Post_Btn, ElementType.Xpath);
			flag = webDB.waitForElement(SalesPriceListLocators.PR_Workflow_Btn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "PriceList Post Button Clicked Sucessfully");
			}
		}

		assertTrue(flag);
	}

	/**
	 * Click Search Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickSearchButton() {
		flag = webDB.waitForElement(SalesPriceListLocators.SearchIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(SalesPriceListLocators.SearchIcon, ElementType.Xpath);
			webDB.javaScriptClickAnElement(SalesPriceListLocators.SearchIcon, ElementType.Xpath);
			logs.logging("INFO", "Successfully Clicked SerachBox");
			flag = webDB.waitForElement(SalesPriceListLocators.SearchButtoninFindPage, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * Click Search Button In Find Page
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickSearchButtonInFindPage() {
		flag = webDB.waitForElement(SalesPriceListLocators.searchBoxFindPage, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesPriceListLocators.SearchButtoninFindPage, ElementType.Xpath);
			logs.logging("INFO", "Successfully Clicked SearchBox");
		}
		assertTrue(flag);
	}

	/**
	 * Get Document Number Of Price List
	 * 
	 * @author Kowshik-RMITS
	 */
	public void getPriceListDocumentno() {
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(SalesPriceListLocators.Documentno(1), ElementType.Xpath);
		if (flag) {
			String PriceListdocno = webDB.getTextFromElement(SalesPriceListLocators.Documentno(1), ElementType.Xpath);
			VariableHandler.setPriceListDocno(PriceListdocno);
			logs.logging("info", "Get Price List Document no " + VariableHandler.getPriceListDocno());
			commonFunc.hardWait(3000);
		}
	}

	/**
	 * Serach Document In Find Page
	 * 
	 * @author Kowshik-RMITS
	 */

	public void searchDocumentNumebrFindPage() {
		flag = webDB.waitForElement(SalesPriceListLocators.SearchBoxFind, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(SalesPriceListLocators.SearchBoxFind, VariableHandler.getPriceListDocno(),
					ElementType.Xpath);
			flag = webDB.waitForElement(SalesPriceListLocators.SearchIcon, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesPriceListLocators.SearchIcon, ElementType.Xpath);
				logs.logging("INFO", "Successfully Populated a Docno");
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
	 * Search Item In Find Page For Amdend The Record
	 * 
	 * @author Kowshik-RMITS
	 * @param Unit_100
	 * @param CUSTOMER_ID
	 */
	public void searchItemINFindPageForAmdendTheRecord(String Unit_100, String CUSTOMER_ID) {

//		flag = webDB.waitForElement(SalesPriceListLocators.unitLovFindPage, ElementType.Xpath);
//		if (flag) {
//			commonFunc.checkRefreshCellsDisappear();
//			commonFunc.searchAndSelectValueInFilters(SalesPriceListLocators.unitLovFindPage, Unit_100);
//			flag = webDB.waitForElement(SalesPriceListLocators.CustomerLovFindPage, ElementType.Xpath);
//			if (flag) {
//				logs.logging("INFO", "UNIT IS SUCCESSFULLY POPULATED" + Unit_100);
//				if (flag) {
//					commonFunc.checkRefreshCellsDisappear();
//					commonFunc.searchAndSelectValueInFilters(SalesPriceListLocators.CustomerLovFindPage, CUSTOMER_ID);
		flag = webDB.waitForElement(SalesPriceListLocators.ItemLovFindPage, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Successfully Inserted a Customer Id" + CUSTOMER_ID);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.searchAndSelectValueInFilters(SalesPriceListLocators.ItemLovFindPage,
						VariableHandler.getItemName());

			}
			assertTrue(flag);
		}

	}
//			}
//		}

	/**
	 * Unchecked a Radio Button In Find Page
	 * 
	 * @author Kowshik-RMITS
	 */
	public void uncheckRadioButtonInFindPage() {
		flag = webDB.waitForElement(SalesPriceListLocators.CatalogueFlagFindPage, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesPriceListLocators.CatalogueFlagFindPage, ElementType.Xpath);
			flag = webDB.waitForElement(SalesPriceListLocators.PriceListFlagFindPage, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Unchecked Catalogue Flag");
				webDB.clickAnElement(SalesPriceListLocators.StandardFlagFindPage, ElementType.Xpath);
			}
		}
		assertTrue(flag);
	}

	/**
	 * Click HyperLink Price Link
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickHyperLinkPriceLink() {
		flag = webDB.waitForElement(SalesPriceListLocators.PriceListHyperLink2, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(SalesPriceListLocators.PriceListHyperLink2, ElementType.Xpath);
			webDB.javaScriptClickAnElement(SalesPriceListLocators.PriceListHyperLink2, ElementType.Xpath);
			flag = webDB.waitForClickElement(SalesPriceListLocators.AmdendButton2, ElementType.CssSelector);
			if (flag) {
				logs.logging("INFO", "Successfully Navigates a PriceList Document");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Click Amdend Button
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickAmdendButton() {
		flag = webDB.waitForElement(SalesPriceListLocators.AmdendButton2, ElementType.CssSelector);
		if (flag) {
			webDB.moveToElement(SalesPriceListLocators.AmdendButton2, ElementType.CssSelector);
			webDB.javaScriptClickAnElement(SalesPriceListLocators.AmdendButton2, ElementType.CssSelector);
			webDB.switchToFrame(SalesPriceListLocators.adjustmentFrame, ElementType.Xpath);
			flag = webDB.waitForElement(SalesPriceListLocators.CloseDataAmdend, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Successfully Clicked a AmdendButton");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Insert Close Date For Amend
	 * 
	 * @author Kowshik-RMITS
	 * @param Today
	 */
	public void insertCloseDateForAmend(String Today) {

		flag = webDB.waitForElement(SalesPriceListLocators.CloseDataAmdend, ElementType.Xpath);
		if (flag) {
			commonFunc.verifyAddTableSpecs(SalesPriceListLocators.CloseDataAmdend,
					SalesPriceListLocators.CloseDataAmdend, Today);
			logs.logging("INFO", "Close Date Successfully Inserted " + Today);
			flag = webDB.waitForElement(SalesPriceListLocators.okButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesPriceListLocators.okButton, ElementType.Xpath);
				logs.logging("INFO", "OK button is clicked successfully ");
				webDB.switchToDefaultContent();
			}
		}
		assertTrue(flag);
	}

	/**
	 * Click a Cancel Record In PriceList Entry
	 * 
	 * @author Kowshik-RMITS
	 */
	public void clickCancelButtonRecord() {
		flag = webDB.waitForElement(SalesPriceListLocators.CancelButton, ElementType.CssSelector);
		if (flag) {
			webDB.clickAnElement(SalesPriceListLocators.CancelButton, ElementType.CssSelector);
			flag = webDB.waitForElement(SalesPriceListLocators.SearchIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Price List Record Successfully Cancelled ");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Search Record Find Page Document
	 * 
	 * @author Kowshik-RMITS
	 */
	public void searchRecordFindPage() {
		flag = webDB.waitForElement(SalesPriceListLocators.SearchButtoninFindPage, ElementType.Xpath);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			webDB.javaScriptClickAnElement(SalesPriceListLocators.SearchButtoninFindPage, ElementType.Xpath);
			flag = webDB.waitForElement(SalesPriceListLocators.SearchBoxFind, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.javaScriptSendTextToAnElement(SalesPriceListLocators.SearchBoxFind,
						VariableHandler.getPriceListDocno(), ElementType.Xpath);
				flag = webDB.waitForElement(SalesPriceListLocators.GoButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Successfully Passed a Price List Document Number");
					flag = webDB.waitForElement(SalesPriceListLocators.GoButton, ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.javaScriptClickAnElement(SalesPriceListLocators.GoButton, ElementType.Xpath);
						logs.logging("INFO", "Successfully Populated a Cancelled Stage Document ");
						flag = webDB.waitForElement(SalesPriceListLocators.SearchButtoninFindPage, ElementType.Xpath);
						if (flag) {
							logs.logging("Info", "Sales Price List Cancelled Document Is Verified ");
						}
					}
				}
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
	 * Price List Screen Dat Base Validation
	 * 
	 * @author Kowshik-RMITS
	 */
	public void PriceListDataBaseValidation() {
		verifyAssertionGetTextValidation(SalesPriceListLocators.LocutionIdField,
				dbCommonFunctions.SalesPriceListdetails(VariableHandler.getPriceListDocno(),
						commonFunc.getDataFromTestDataSheet("PriceListDbLocId")));

		verifyAssertionGetTextValidation(SalesPriceListLocators.PriceListPfxField,
				dbCommonFunctions.SalesPriceListdetails(VariableHandler.getPriceListDocno(),
						commonFunc.getDataFromTestDataSheet("PriceListDbPLpfx")));

		verifyAssertionGetTextValidation(SalesPriceListLocators.CustomerIdFieldDb,
				dbCommonFunctions.SalesPriceListdetails(VariableHandler.getPriceListDocno(),
						commonFunc.getDataFromTestDataSheet("PriceListDbCustomerId")));

		verifyAssertionGetTextValidation(SalesPriceListLocators.CustomerDescDb, dbCommonFunctions.SalesPriceListdetails(
				VariableHandler.getPriceListDocno(), commonFunc.getDataFromTestDataSheet("PriceListDbCustomerDesc")));

		verifyAssertionGetTextValidation(SalesPriceListLocators.ItemIdDb, dbCommonFunctions.SalesPriceListdetails(
				VariableHandler.getPriceListDocno(), commonFunc.getDataFromTestDataSheet("PriceListDbItemId")));

		verifyAssertionGetTextValidation(SalesPriceListLocators.SalesUOMDb, dbCommonFunctions.SalesPriceListdetails(
				VariableHandler.getPriceListDocno(), commonFunc.getDataFromTestDataSheet("PriceListDbSalesUOM")));

		verifyAssertionGetTextValidation(SalesPriceListLocators.SalesPriceDb, dbCommonFunctions.SalesPriceListdetails(
				VariableHandler.getPriceListDocno(), commonFunc.getDataFromTestDataSheet("PriceListDbSalesPrice")));

		verifyAssertionGetTextValidation(SalesPriceListLocators.DiscPercentDb, dbCommonFunctions.SalesPriceListdetails(
				VariableHandler.getPriceListDocno(), commonFunc.getDataFromTestDataSheet("PriceListDbSalesDisc")));

		verifyAssertionGetTextValidation(SalesPriceListLocators.AmdNoDb, dbCommonFunctions.SalesPriceListdetails(
				VariableHandler.getPriceListDocno(), commonFunc.getDataFromTestDataSheet("PriceListDbAmdNo")));

	}

}
