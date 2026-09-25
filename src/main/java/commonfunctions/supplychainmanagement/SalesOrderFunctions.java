package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.ItemDetailsHandler;
import commonfunctions.ItemDetailsHandler.SOCustomerDetailsName;
import commonfunctions.ItemDetailsHandler.SOLineDetailsName;
import commonfunctions.VariableHandler;
import commonfunctions.financials.BankReceiptFunctions;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.ApprovalPageLocators;
import commonlocators.supplychainmanagement.CustomerScheduleLocators;
import commonlocators.supplychainmanagement.SalesOrderLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Row;

public class SalesOrderFunctions {
	private boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	public ApprovalPageFunctions apprpagefunc = new ApprovalPageFunctions();
	private static PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();
	private static ItemDetailsHandler itemDetailHandler = new ItemDetailsHandler();
	private static DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();
	private static MaterialRequestFunctions MatReqFunc = new MaterialRequestFunctions();
	private static CustomerScheduleFunctions CSFunc = new CustomerScheduleFunctions();
	public BankReceiptFunctions bankreceiptfunc = new BankReceiptFunctions();
	private static ExcelUtils excelUtils = new ExcelUtils();
	public static String[] SOItems;
	public static String[] SOQty;
	public static String[] SOUnitCost;
	public static String[] SOHsnCode;
	String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "PR_ITEMS.xlsx";
	String[][] SOMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
	String Filepath1 = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
			+ "QA Scenario - Sales STD PRICE.xlsx";
	public ArrayList<String> MultipleSO = new ArrayList<String>();
	public ArrayList<String> MultiAdvReceiptDocumentNumber = new ArrayList<String>();

	/**
	 * This Method Is used to Verify the Domestic SOGeneral With (SimilarStock And
	 * CustomerUOM) And (DisSimilar Stock And CustomerUOM)
	 * 
	 * @author Harikrishna -RMITS
	 * @return boolean flag
	 */
	public boolean verifyDomesticSOGeneralSimilarStockAndCustomerUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		salesOrderDBHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("Customer"),
				commonFunc.getDataFromTestDataSheet("CustomerDesc"), commonFunc.getDataFromTestDataSheet("SO_General"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();
		addSalesOrderLineThroughExcelUD(webDB.getDataFromProperties("SODomUserDefined"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SI_HSNCode"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * This Method Is used to Verify the Export SOGeneral With (SimilarStock And
	 * CustomerUOM) And (DisSimilar Stock And CustomerUOM)
	 * 
	 * @author Harikrishna - RMITS
	 * @return boolean flag
	 */
	public boolean verifyExportSOGeneralSimilarStockAndCustomerUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();
		addSalesOrderLineThroughExcelUD(webDB.getDataFromProperties("SOExpUserDefined"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SO_HSNCode"),
				commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used to Verify the Domestic
	 *         SOGeneral Rate Contract With Similar Stock And CustomerUOM
	 * @return boolean flag
	 */
	public boolean verifyDomesticSOGeneralRateContractSimilarStockAndCustomerUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();
		addSalesOrderLineThroughExcelOtherThanUD(webDB.getDataFromProperties("SODomRateContract"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SO_HSNCode"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used to Verify the Export SOGeneral
	 *         Rate Contract With Similar Stock And CustomerUOM
	 * @return boolean flag
	 */
	public boolean verifyExportSOGeneralRateContractSimilarStockAndCustomerUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();
		addSalesOrderLineThroughExcelOtherThanUD(webDB.getDataFromProperties("SOExpRateContract"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SO_HSNCode"),
				commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used to Verify the Domestic
	 *         SOGeneral Standard Price With Similar Stock And CustomerUOM
	 * @return boolean flag
	 */
	public boolean verifyDomesticSOGeneralStandardPriceSimilarStockAndCustomerUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();
		addSalesOrderLineThroughExcelOtherThanUD(webDB.getDataFromProperties("SODomStandardPrice"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SO_HSNCode"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used to Verify the Export SOGeneral
	 *         Standard Price With Similar Stock And CustomerUOM
	 * @return boolean flag
	 */
	public boolean verifyExportSOGeneralStandardPriceSimilarStockAndCustomerUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();
		addSalesOrderLineThroughExcelOtherThanUD(webDB.getDataFromProperties("SOExpStandardPrice"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SO_HSNCode"),
				commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used to Verify the Domestic Sales
	 *         Order - price basis is Catalog Price- Similar Stock and Customer UOM
	 * @return boolean flag
	 */
	public boolean verifyDomesticSOGeneralCatalogPriceSimilarStockAndCustomerUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();
		addSalesOrderLineThroughExcelOtherThanUD(webDB.getDataFromProperties("SODomCatalogprice"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SO_HSNCode"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used to Verify the Export Sales
	 *         Order - price basis is Catalog Price- Similar Stock and Customer UOM
	 * @return boolean flag
	 */
	public boolean verifyExportSOGeneralCatalogPriceSimilarStockAndCustomerUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();
		addSalesOrderLineThroughExcelOtherThanUD(webDB.getDataFromProperties("SOExpCatalogprice"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SO_HSNCode"),
				commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used to Verify the Domestic Sales
	 *         Order - price basis is PriceList- Similar Stock and Customer UOM
	 * @return boolean flag
	 */
	public boolean verifyDomesticSOGeneralPriceListSimilarStockAndCustomerUOM() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();
		addSalesOrderLineThroughExcelOtherThanUD(webDB.getDataFromProperties("SODomPriceList"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SO_HSNCode"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used to Verify the Export Sales
	 *         Order - price basis is PriceList- Similar Stock and Customer UOM
	 * @return boolean flag
	 */
	public boolean verifyExportSOGeneralPriceListSimilarStockAndCustomerUOM() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();
		addSalesOrderLineThroughExcelOtherThanUD(webDB.getDataFromProperties("SOExpPriceList"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SO_HSNCode"),
				commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * @author Harikrishna -RMITS This Method Is Used To Click the Header Wise
	 *         Cancel Button And Validate The Sales Order Document status
	 * 
	 * @return boolean flag
	 */
	public boolean verifyCancelSalesOrder() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();
//		customerDetailsVerification(commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		clickCancelButtonInSalesOrderHeader();
		verifySalesOrderDocumentStatusCancelledWithDB();
		validateSalesOrderHeaderDocumentStatus(VariableHandler.getSONumber(),
				commonFunc.getDataFromTestDataSheet("CancelStatus"));
		flag = commonFunc.logoutUser();
		return flag;
	}

	/**
	 * @author Harikrishna - RMITS This Method Is used To Click The Sales order
	 *         Cancel Button
	 */
	public void clickCancelButtonInSalesOrderHeader() {
		flag = webDB.waitForElement(SalesOrderLocators.HeaderCancelButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.HeaderCancelButton, ElementType.Xpath);
			flag = webDB.waitForElementUntilGivenTime(SalesOrderLocators.SOCancelAlert, ElementType.Xpath, 30);
			if (flag) {
				webDB.clickAnElement(SalesOrderLocators.SOCancelAlert, ElementType.Xpath);
				flag = webDB.waitForElementUntilGivenTime(SalesOrderLocators.NoteAlertMessage, ElementType.Xpath, 45);
				if (flag) {
					logs.logging("INFO", "Note Alert Is Displayed And Sales Order Document Is Cancelled.");
					if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS This Method Is Used to Validate The Sales Order
	 *         Header Document Status
	 * @param SONumber
	 * @param ExpStatus
	 */
	public void validateSalesOrderHeaderDocumentStatus(String SONumber, String ExpStatus) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(SalesOrderLocators.FindSONumberLOV, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.FindSONumberLOV, SONumber);
			flag = webDB.waitForElement(SalesOrderLocators.FindHeaderRadioButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesOrderLocators.FindHeaderRadioButton, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(SalesOrderLocators.FindSearchButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SalesOrderLocators.FindSearchButton, ElementType.Xpath);
					commonFunc.hardWait(2000);
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(SalesOrderLocators.SOStatusHeaderTableText(SONumber),
							ElementType.Xpath);
					if (flag) {
						String Status = webDB.getTextFromElement(SalesOrderLocators.SOStatusHeaderTableText(SONumber),
								ElementType.Xpath);
						flag = Status.contains(ExpStatus);
						if (flag) {
							logs.logging("INFO", "Expected Status :" + ExpStatus);
							logs.logging("INFO", "Verified Sales Order Document Status");
						} else {
							logs.logging("INFO", "Sales Order Doucment Status Verification Failed");
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method is Used to click the Add Button in the Sales order Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void verifyClickAddButtonInSo() {
		flag = webDB.waitForElement(SalesOrderLocators.AddButtonSalesOrder, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.AddButtonSalesOrder, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.SOButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesOrderLocators.SOButton, ElementType.Xpath);
				flag = webDB.waitForElement(SalesOrderLocators.SOFrame, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Add button is Clicked");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS This Method is Used to Create a Sales Order
	 *         Header Records
	 * 
	 * @param Location
	 * @param Unit
	 * @param SubVoucherType
	 * @param Customer
	 */
	public void verifyCreationOfSalesOrderHeader(String Location, String Unit, String SubVoucherType, String Customer) {
//		verifyClickAddButtonInSo();
//		FrameSwitchingInSOCreation();
		String LocationValue = webDB.getDomPropertyFromElement(SalesOrderLocators.SOLocationField, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		System.out.println(LocationValue);
		if (!LocationValue.equalsIgnoreCase(Location)) {
			commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.LocationLOV, Location);
			commonFunc.checkRefreshCellsDisappear();
			FrameSwitchingInSOCreation();
		}
		String SalesVoucherType = webDB.getDomPropertyFromElement(SalesOrderLocators.SOSubVoucherType,
				ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
		System.out.println(SalesVoucherType);
		if (!SalesVoucherType.equalsIgnoreCase(SubVoucherType)) {
			commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.SalesSubVoucherTypeLOV, SubVoucherType);
			commonFunc.checkRefreshCellsDisappear();
			FrameSwitchingInSOCreation();
		}
		commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.CustomerLov, Customer);
		commonFunc.checkRefreshCellsDisappear();
		FrameSwitchingInSOCreation();
		clickTickIcon();
		verifySalesOrderDocumentStatusDraftWithDB();
		validateLoactionInSOHeader(Location);
		validateUnitInSOHeader(Unit);
//		validateCustomerInSOHeader(Customer);
	}

	/**
	 * This Method Is Used To Switch Frame In Sales Order Header creation
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void FrameSwitchingInSOCreation() {
		flag = webDB.waitForElement(SalesOrderLocators.SOFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(SalesOrderLocators.SOFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Frame Sucessfully");
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS This Method is Used to click the Tick Icon in the
	 *         Sales Order Page
	 * 
	 */
	private void clickTickIcon() {
		flag = webDB.waitForElement(SalesOrderLocators.TickIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.TickIcon, ElementType.Xpath);
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(SalesOrderLocators.SoNumber, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "TickIcon Is Clicked");
				String SONum = webDB.getTextFromElement(SalesOrderLocators.SoNumber, ElementType.Xpath);
				VariableHandler.setSONumber(SONum);
				logs.logging("INFO", "Sales Order Number Is :" + VariableHandler.getSONumber());
				MultipleSO.add(VariableHandler.getSONumber());
				if (webDB.isElementDisplayed(SalesOrderLocators.NoteAlertCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(SalesOrderLocators.NoteAlertCloseIcon, ElementType.Xpath);
				}
			}
		}
		commonFunc.checkRefreshCellsDisappear();
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS This Method is used to validate the Loaction Id
	 *         in the Sales order (Header) Transaction Screen
	 * 
	 * @param Location
	 */
	private void validateLoactionInSOHeader(String Location) {
		flag = webDB.waitForElement(SalesOrderLocators.HeaderLocationField, ElementType.Xpath);
		if (flag) {
			String DisplayText = webDB.getTextFromElement(SalesOrderLocators.HeaderLocationField, ElementType.Xpath);
			flag = DisplayText.equalsIgnoreCase(Location);
			if (flag) {
				logs.logging("INFO",
						"Location In Sales Order Header Is Verified, Verified Location ID :" + DisplayText);
			} else {
				logs.logging("INFO", "Location Verification failed in Sales order Header");
				logs.logging("INFO", "Expected Location ID :" + Location + "Actual Location ID :" + DisplayText);
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS This Method is used to validate the Unit Id in
	 *         the Sales order (Header) Transaction Screen
	 * 
	 * @param Unit
	 */
	private void validateUnitInSOHeader(String Unit) {
		flag = webDB.waitForElement(SalesOrderLocators.HeaderUnitField, ElementType.Xpath);
		if (flag) {
			String DisplayText = webDB.getTextFromElement(SalesOrderLocators.HeaderUnitField, ElementType.Xpath);
			flag = DisplayText.equalsIgnoreCase(Unit);
			if (flag) {
				logs.logging("INFO", "Unit in Sales Order Header IS Verified, Verified Unit Id :" + DisplayText);
			} else {
				logs.logging("INFO", "Unit Verification failed in Sales Order Header");
				logs.logging("INFO", "Expected Unit Id :" + Unit + "Actual Unit Id :" + DisplayText);
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS This Method is used to validate the Customer in
	 *         the Sales order (Header) Transaction Screen
	 * 
	 * @param Customer
	 */
	private void validateCustomerInSOHeader(String Customer) {
		flag = webDB.waitForElement(SalesOrderLocators.HeaderCustIDField, ElementType.Xpath);
		if (flag) {
			String DisplayText = webDB.getTextFromElement(SalesOrderLocators.HeaderCustIDField, ElementType.Xpath);
			flag = DisplayText.equalsIgnoreCase(Customer);
			if (flag) {
				logs.logging("INFO",
						"Customer in Sales Order Header Is Verified , Verified Customer Id :" + DisplayText);
			} else {
				logs.logging("INFO", "Customer Verification failed In Sales Order Header");
				logs.logging("INFO", "Expected Customer Id :" + Customer + "Actual Customer Id :" + DisplayText);
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS This Method Is used To Add Items In Sales order
	 *         Lines
	 * @param Item
	 */
	public void addItemsInSO(String Item) {
		flag = webDB.waitForElementUntilGivenTime(SalesOrderLocators.LineWiseAddIcon, ElementType.Xpath, 30);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.LineWiseAddIcon, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.verifyAddTableLovValue(SalesOrderLocators.ItemField(), SalesOrderLocators.ItemLov(), Item);
			logs.logging("INFO", "Line Wise Add Button is Clicked");
		}
		commonFunc.checkRefreshCellsDisappear();
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
	 * @author Harikrishna - RMITS This Method Is used to Add the Sales Qty And Unit
	 *         Cost For An Item In Line Wise
	 * @param Qty
	 * @param UnitCost
	 */
	public void addQuantityAndUnitCost(String Qty, String UnitCost) {
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.verifyAddTableSpecs(SalesOrderLocators.QtyField(), SalesOrderLocators.QtyInputField(), Qty);
		logs.logging("INFO", "Quantity Entered: " + Qty);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.verifyAddTableSpecs(SalesOrderLocators.UnitCostField(), SalesOrderLocators.UnitCostInputField(),
				UnitCost);
		logs.logging("INFO", "Unit Cost Entered: " + UnitCost);
	}

	/**
	 * @author Harikrishna - RMITS This Method Is Used to Choose the HSNCode And
	 *         Click the Line Wise Save Icon In Sales Order Page
	 * 
	 * @param HSNCode
	 */
	public void chooseHsnCodeAndClickLineWiseSaveButton(String HSNCode) {
		String text = webDB.getTextFromElement(SalesOrderLocators.HSNCodeField(), ElementType.Xpath);
		if (text == null || text.trim().isEmpty()) {
			commonFunc.verifyAddTableLovValue(SalesOrderLocators.HSNCodeField(), SalesOrderLocators.HSNCodeLOV(),
					HSNCode);
			logs.logging("INFO", "HSC Code Field Is Clicked");
		}
	}

	public void clickLineWiseSaveButton() {
		flag = webDB.waitForElement(SalesOrderLocators.LineWiseSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.LineWiseSaveIcon, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.PostButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Line Wise Save Button is Clicked");
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.checkRefreshCellsDisappear();
//				webDB.waitForElementUntilGivenTime(SalesOrderLocators.SOTotalAmountFieldTrans, ElementType.Xpath, 60);
//				flag = webDB.waitForElement(SalesOrderLocators.SOTotalAmountFieldTrans, ElementType.Xpath);
//				if (flag) {
//					String SOTotAmt = webDB.getDomPropertyFromElement(SalesOrderLocators.SOTotalAmountFieldTrans,
//							ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
//					VariableHandler.setSOTotalAmount(SOTotAmt);
//					logs.logging("INFO", "SalesOrder Total Amount is :" + VariableHandler.getSOTotalAmount());
//				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna -RMITS This Method Is Used To click the Post Button In
	 *         the Header
	 */
	public void clickPostButton() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(SalesOrderLocators.PostButton, ElementType.Xpath);
		if (flag) {
			commonFunc.checkSuccessToastMessageDisappear();
			webDB.clickAnElement(SalesOrderLocators.PostButton, ElementType.Xpath);
			flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
			if (flag) {
				logs.logging("INFO", "Post Button Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS This Method Is Used to Forward the Doucmnet from
	 *         One User To Another User
	 * @return boolean flag
	 */
	public boolean forwardForApproval(String ForwardToEmployee) {
		commonFunc.hardWait(500);
		if (webDB.verifyIsElementDisplayed(SalesOrderLocators.WorkApprovalFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(SalesOrderLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			String ForwardEmployee = webDB.getDomPropertyFromElement(SalesOrderLocators.ForwardPersonField,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			if (!ForwardEmployee.equalsIgnoreCase(ForwardToEmployee)) {
				commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.ForwardApprovalLov, ForwardToEmployee);
				commonFunc.hardWait(500);
				webDB.switchToFrame(SalesOrderLocators.WorkApprovalFrame, ElementType.Xpath);
				logs.logging("INFO", "Switched To WorkFlow Frame");
			}
			flag = webDB.waitForElement(CommonUiLocators.WorkAppoval_OkBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(CommonUiLocators.WorkAppoval_OkBtn, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(CommonUiLocators.ClearIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Verified Navigate To SO Home Page");
				}
			}
			webDB.switchToDefaultContent();
			assertTrue(flag);
		} else {
			flag = false;
			VariableHandler.setApprovalFlag(flag);
		}
		return flag;
	}

	/**
	 * @author Harikrishna - RMITS This Method is Used to Verify the Sales Order
	 *         Document is Approved / Not
	 * @param SONumber
	 * @param ExpStatus
	 */
	public void validateSalesOrderLineDocumentStatus(String SONumber, String ExpStatus) {
		flag = webDB.waitForElement(SalesOrderLocators.FindSONumberLOV, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.FindSONumberLOV, SONumber);
			flag = webDB.waitForElement(SalesOrderLocators.FindSearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesOrderLocators.FindSearchButton, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(SalesOrderLocators.SOStatusLineTableText(SONumber), ElementType.Xpath);
				if (flag) {
					String Status = webDB.getTextFromElement(SalesOrderLocators.SOStatusLineTableText(SONumber),
							ElementType.Xpath);
					logs.logging("INFO", "Displayed Status :" + Status);
					logs.logging("INFO", "Expected Status :" + ExpStatus);
					flag = Status.contains(ExpStatus);
					if (flag) {
						logs.logging("INFO", "Verified Sales Order Document Status");

					} else {
						logs.logging("INFO", "Sales Order Doucment Status Verification Failed");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS This Method is used to Validate the Sales order
	 *         Details in the Document Approval Screen
	 * @param SONumber
	 * @param ExpLocation
	 * @param ExpUnit
	 * @param ExpCustDesc
	 */
	public void validateSalesOrderDetailsInDocApproval(String SONumber, String ExpLocation, String ExpUnit,
			String ExpCustId) {
		flag = webDB.waitForClickElement(ApprovalPageLocators.SODetailsInfoBtn(SONumber), ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(ApprovalPageLocators.SODetailsInfoBtn(SONumber), ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.HeaderLocationField, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "View Details Button is Clicked");
				String DocNum = webDB.getTextFromElement(SalesOrderLocators.SoNumber, ElementType.Xpath);
				logs.logging("INFO", "GetText SalesOrder Documnet Number is :" + DocNum);
				flag = DocNum.equals(VariableHandler.getSONumber());
				if (flag) {
					logs.logging("INFO", "Verified Expected and Actual Sales Order Document Number");
				} else {
					logs.logging("INFO",
							"Verification Failed, Expected :" + VariableHandler.getSONumber() + "Actual :" + DocNum);
				}
				flag = webDB.waitForElement(SalesOrderLocators.HeaderLocationField, ElementType.Xpath);
				if (flag) {
					String LocId = webDB.getTextFromElement(SalesOrderLocators.HeaderLocationField, ElementType.Xpath);
					logs.logging("INFO", "GetText Location Id is :" + LocId);
					flag = LocId.equals(ExpLocation);
					if (flag) {
						logs.logging("INFO", "Verified Expected And Actual Location Id");
					} else {
						logs.logging("INFO", "Verification Failed, Expected :" + ExpLocation + "Actual :" + LocId);
					}
					flag = webDB.waitForElement(SalesOrderLocators.HeaderUnitField, ElementType.Xpath);
					if (flag) {
						String Unit = webDB.getTextFromElement(SalesOrderLocators.HeaderUnitField, ElementType.Xpath);
						logs.logging("INFO", "GetText Unit Id is :" + Unit);
						flag = Unit.equals(ExpUnit);
						if (flag) {
							logs.logging("INFO", "Verified Expected And Actual Unit Id");
						} else {
							logs.logging("INFO", "Verification Failed, Expected :" + ExpUnit + "Actual " + Unit);
						}
						flag = webDB.waitForElement(SalesOrderLocators.HeaderCustDescFieldQuery, ElementType.Xpath);
						if (flag) {
							String Customer = webDB.getTextFromElement(SalesOrderLocators.HeaderCustIDFieldQuery,
									ElementType.Xpath);
							logs.logging("INFO", "GetText Customer Id is :" + Customer);
							flag = Customer.equals(ExpCustId);
							if (flag) {
								logs.logging("INFO", "Verified Expected And Actual Customer Description");
//								verifyTotalSOAmountCalculationInDocApproval();
//								clickPrintButton();
								apprpagefunc.verifyNavigateback();
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS This Method is Used To Verify the Sales Order
	 *         Total Amount in Doucmnet Approval screen
	 */
	public void verifyTotalSOAmountCalculationInDocApproval() {
		flag = webDB.waitForElement(SalesOrderLocators.SOTotalAmountFieldQuery, ElementType.Xpath);
		if (flag) {
			String SOTotalAmount = webDB.getTextFromElement(SalesOrderLocators.SOTotalAmountFieldQuery,
					ElementType.Xpath);
			logs.logging("INFO", "GetText Total Sales Order Amount is :" + SOTotalAmount);
			flag = SOTotalAmount.contains(VariableHandler.getSOTotalAmount());
			if (flag) {
				logs.logging("INFO", "Verified Sales order Total Amount Calculation");
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMIT This Method is Used to Click the Line Wise details
	 *         Button And Validate the Price Basis Of the Item
	 */
	public void clickLineWiseDetailsButton() {
		flag = webDB.waitForElement(SalesOrderLocators.SOLineWiseDetailsButton(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.SOLineWiseDetailsButton(), ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.LinewisedetailsHeader, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sales order Line Wise Details Button is Clicked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method is used to click Total Discount Button
	 * 
	 * @author Sakthi-GWL
	 */
	public void clickTotalDiscountForLineDetails() {
		flag = webDB.waitForElement(SalesOrderLocators.SOTotalDiscountData(), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.SOTotalDiscountData(), ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.DiscountEntryIframe, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sales order Line Wise Details Button is Clicked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna -RMITS This Method Is Used to Validate the Price Basis of
	 *         the Item
	 */
	public void validateItemPriceBasis() {
		flag = webDB.waitForElement(SalesOrderLocators.LineDetailsFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(SalesOrderLocators.LineDetailsFrame, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.PriceBasisField, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Switched to Line Wise Details frame");
				String pricebasis = webDB.getDomPropertyFromElement(SalesOrderLocators.PriceBasisField,
						ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
				logs.logging("INFO", "GetText PriceBasis of the Item is :" + pricebasis);
				flag = pricebasis.equalsIgnoreCase("User defined");
				if (flag) {
					logs.logging("INFO", "Verified Price Basis of the Item is User Defined");
				} else if (pricebasis.equalsIgnoreCase("Open Order")) {
					logs.logging("INFO", "Verified Price Basis of the Item is Rate Contract");
				} else if (pricebasis.equalsIgnoreCase("Standard Price")) {
					logs.logging("INFO", "Verified Price Basis of the Item is Standard Price");
				} else if (pricebasis.equalsIgnoreCase("Cataloug")) {
					logs.logging("INFO", "Verified Price Basis of the Item is Cataloug Price");
				} else if (pricebasis.equalsIgnoreCase("Price List")) {
					logs.logging("INFO", "Verified Price Basis of the Item is Price List");
				} else {
					logs.logging("INFO", "Price Basis Verification Failed for the Item");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna -RMITS This Method is Used to Enter The Discounts In
	 *         Sales Order Screen
	 * @param Locator
	 * @param InputValue
	 */
	public void enterSODiscountDetails(String Locator, String InputValue) {
		flag = webDB.waitForElement(Locator, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(Locator, ElementType.Xpath);
			webDB.sendTextToAnElement(Locator, InputValue, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			webDB.pressTabKey();
			commonFunc.checkRefreshCellsDisappear();
		}
	}

	/**
	 * @author Harikrishna -RMITS This Method is Used To Click the Line Wise Details
	 *         Save Button
	 */
	public void clickLineWiseDetailsSaveButton() {
		flag = webDB.waitForClickElement(SalesOrderLocators.LineWiseDetailsSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.LineWiseDetailsSaveIcon, ElementType.Xpath);
			webDB.switchToDefaultContent();
			if (webDB.verifyIsElementDisplayed(SalesOrderLocators.DetailsCloseButton, ElementType.Xpath)) {
				webDB.clickAnElement(SalesOrderLocators.DetailsCloseButton, ElementType.Xpath);
				flag = webDB.waitForElement(SalesOrderLocators.PostButton, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			if (flag) {
				logs.logging("INFO", "Line Wise Details are saved sucessfully");
			}
			commonFunc.checkRefreshCellsDisappear();
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS This Method is Used to Get The Sales Order Total
	 *         Amount
	 */
	public void getSalesOrderTotalAmount() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(SalesOrderLocators.SOTotalAmountFieldTrans, ElementType.Xpath);
		if (flag) {
			String SOTotAmt = webDB.getDomPropertyFromElement(SalesOrderLocators.SOTotalAmountFieldTrans,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			VariableHandler.setSOTotalAmount(SOTotAmt);
			logs.logging("INFO", "SalesOrder Total Amount is :" + VariableHandler.getSOTotalAmount());
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna -RMITS This Method is Used to Click the Line Wise Save
	 *         Button And Get The Total Sales Order Amount
	 * @param Qty
	 */
	public void clickLineWiseSaveAndGetTotAmt() {
		flag = webDB.waitForElement(SalesOrderLocators.LineWiseSaveIcon, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(SalesOrderLocators.LineWiseSaveIcon, ElementType.Xpath);
			webDB.clickAnElement(SalesOrderLocators.LineWiseSaveIcon, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.PostButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Line Wise Save Button is Clicked");
				commonFunc.checkSuccessToastMessageDisappear();
				webDB.waitForElementUntilGivenTime(SalesOrderLocators.SOTotalAmountFieldTrans, ElementType.Xpath, 60);
				flag = webDB.waitForClickElement(SalesOrderLocators.SOTotalAmountFieldTrans, ElementType.Xpath);
				if (flag) {
					String SOTotAmt = webDB.getDomPropertyFromElement(SalesOrderLocators.SOTotalAmountFieldTrans,
							ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
					VariableHandler.setSOTotalAmount(SOTotAmt);
					logs.logging("INFO", "SalesOrder Total Amount is :" + VariableHandler.getSOTotalAmount());
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna -RMITS This Method is Used to Add the Line Item Quantity
	 * @param Qty
	 */
	public void addLineItemQty(String Qty) {
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.verifyAddTableSpecs(SalesOrderLocators.QtyField(), SalesOrderLocators.QtyInputField(), Qty);
		webDB.pressTabKey();
		commonFunc.checkRefreshCellsDisappear();
	}

	/**
	 * This Method used to switch Line Details iframe in Sale Order
	 * 
	 * @author Sakthi-GWL
	 */
	public void switchToLineDetailFrameInSaleOrder() {
		flag = webDB.waitForElement(SalesOrderLocators.LineDetailsFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(SalesOrderLocators.LineDetailsFrame, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.LineWiseDetailsSaveIcon, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method used to click Pending Dr And Pending receivables in Payment
	 * Receipts
	 * 
	 * @author Sakthi-GWL
	 * @param Unit
	 * @param Cost
	 */
	public void checkSaleOrderTaxCalculationForLastAddedItem() {
		logs.logging("INFO", "Sales Order Tax Calculation!!!");
		flag = webDB.waitForClickElement(SalesOrderLocators.QtyField(), ElementType.Xpath);
		if (flag) {
			BigDecimal decimal_Qnty = new BigDecimal(
					webDB.getTextFromElement(SalesOrderLocators.QtyField(), ElementType.Xpath));
			decimal_Qnty = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(decimal_Qnty);
			logs.logging("INFO", "Quantity Passed for Calculation without tax: " + decimal_Qnty);
			BigDecimal decimal_Cost = new BigDecimal(
					webDB.getTextFromElement(SalesOrderLocators.UnitCostField(), ElementType.Xpath));
			decimal_Cost = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(decimal_Cost);
			logs.logging("INFO", "Cost Passed for Calculation without tax: " + decimal_Cost);
			BigDecimal totalCostWithoutTax = decimal_Qnty.multiply(decimal_Cost);
			logs.logging("INFO", "Multiply value Cost and Quantity without tax: " + totalCostWithoutTax);
			flag = webDB.waitForElement(SalesOrderLocators.DiscountAmountField, ElementType.Xpath);
			if (flag) {
				BigDecimal DiscountAmnt = new BigDecimal(
						webDB.getTextFromElement(SalesOrderLocators.DiscountAmountField, ElementType.Xpath));
				DiscountAmnt = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(DiscountAmnt);
				logs.logging("INFO", "Discount Amount Displayed without tax: " + DiscountAmnt);
				totalCostWithoutTax = totalCostWithoutTax.subtract(DiscountAmnt);
				logs.logging("INFO",
						"After Subtract Discount in Calculation Amount without tax: " + totalCostWithoutTax);
				BigDecimal PerValue = new BigDecimal(
						webDB.getTextFromElement(SalesOrderLocators.TaxPercentField, ElementType.Xpath));
				PerValue = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(PerValue);
				logs.logging("INFO", "Displayed tax Percentage: " + PerValue.intValue() + "%");

				BigDecimal IGSTAmount = new BigDecimal(
						webDB.getTextFromElement(SalesOrderLocators.IGSTTaxAmountField, ElementType.Xpath));
				IGSTAmount = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(IGSTAmount);
				BigDecimal CGSTAmount = new BigDecimal(
						webDB.getTextFromElement(SalesOrderLocators.CGSTTaxAmountField, ElementType.Xpath));
				CGSTAmount = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(CGSTAmount);
				BigDecimal SGSTAmount = new BigDecimal(
						webDB.getTextFromElement(SalesOrderLocators.SGSTTaxAmountField, ElementType.Xpath));
				SGSTAmount = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(SGSTAmount);
				BigDecimal UTGSTAmount = new BigDecimal(
						webDB.getTextFromElement(SalesOrderLocators.UTGSTTaxAmountField, ElementType.Xpath));
				UTGSTAmount = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(UTGSTAmount);
				BigDecimal taxAmount = IGSTAmount.add(CGSTAmount).add(SGSTAmount).add(UTGSTAmount);
				logs.logging("INFO", "After Adding Displayed GST Values: " + taxAmount);

				BigDecimal TaxValue = totalCostWithoutTax.divide(new BigDecimal("100")).multiply(PerValue);
				if (!IGSTAmount.equals(taxAmount)) {
					BigDecimal IndividualTax = purchaseOrderFunc
							.verifyRoundOffBigDecimalValueWithReturnBigDecimal(TaxValue.divide(new BigDecimal("2")));
					logs.logging("INFO", "Individual Tax Amount: " + IndividualTax);
					TaxValue = IndividualTax.multiply(new BigDecimal("2"));
				} else {
					TaxValue = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(TaxValue);
				}
				logs.logging("INFO", "Expected Tax Value After Tax calculation: " + TaxValue);
				flag = taxAmount.equals(TaxValue);
				if (flag) {
					logs.logging("INFO", "Tax Amount Verified, Tax added value: " + taxAmount);
					logs.logging("INFO", "verifing total amount after calculating tax amount!!!");
					webDB.waitForClickElement(SalesOrderLocators.SOTotalAmountFieldTrans, ElementType.Xpath);
					String Value = webDB.getDomPropertyFromElement(SalesOrderLocators.SOTotalAmountFieldTrans,
							ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
					if (Value.contains(",")) {
						Value = Value.replace(",", "").trim();
					}
					BigDecimal DisplayLineAmnt = new BigDecimal(Value);
					DisplayLineAmnt = purchaseOrderFunc
							.verifyRoundOffBigDecimalValueWithReturnBigDecimal(DisplayLineAmnt);
					logs.logging("INFO", "Displayed Total Amount: " + DisplayLineAmnt);

					BigDecimal TotalAmountAddedTax = purchaseOrderFunc
							.verifyRoundOffBigDecimalValueWithReturnBigDecimal(totalCostWithoutTax.add(taxAmount));
					logs.logging("INFO", "Calculated Total Amount with Added Tax: " + TotalAmountAddedTax);
					flag = DisplayLineAmnt.equals(TotalAmountAddedTax);
					if (flag) {
						logs.logging("INFO", "Verified Total Amount Displayed and Calculated Total Amount Matched");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used to Verify The GetText
	 *         Validations(Template)
	 * @param locators
	 * @param Value
	 */
	public void verifyGetTextValidation(String locators, String Value) {
		flag = webDB.waitForElement(locators, ElementType.Xpath);
		if (flag) {
			String text = webDB.getTextFromElement(locators, ElementType.Xpath);
			logs.logging("INFO", "Get Text Value Is: " + text);
			logs.logging("INFO", "Expected Value Is: " + Value);
			flag = text.equals(Value);
			if (flag) {
				logs.logging("INFO", "Verified Actual Value With Expected Value");
			} else {
				logs.logging("INFO", "Verification Failed Expected Value :" + Value + "Get Text Value :" + text);
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS This Method Is Used To Verify the DOM Property
	 *         Validations(Template)
	 * @param locators
	 * @param Value
	 */
	public void verifyDomPropertyValidation(String locators, String Value) {
		flag = webDB.waitForElement(locators, ElementType.Xpath);
		if (flag) {
			String text = webDB.getDomPropertyFromElement(locators, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			logs.logging("INFO", "Get Text Value Is: " + text);
			logs.logging("INFO", "Expected Value Is: " + Value);
			flag = text.equals(Value);
			if (flag) {
				logs.logging("INFO", "Verified Actual Value With Expected Value");
			} else {
				logs.logging("INFO", "Verification Failed Expected Value :" + Value + "Get Text Value :" + text);
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna -RMITS This Method Is Used To Calculate the Line Amount
	 *         Of Each Line In the Sales Order Screen
	 */
	public void verifyItemCalculationWithLineAmount() {
		String Item;
		commonFunc.checkRefreshCellsDisappear();
		List<WebElement> OrderedQtyList = webDB.getDriver().findElements(By.xpath(SalesOrderLocators.QtyField()));
		for (int i = 1; i <= OrderedQtyList.size(); i++) {
			Item = webDB.getDriver().findElement(By.xpath(SalesOrderLocators.ItemName(i))).getText();
			logs.logging("INFO", "Get Text Item For Calculation Is: " + Item);
			String OrderedQty = webDB.getDriver().findElement(By.xpath(SalesOrderLocators.OrderedQty(i))).getText();
			logs.logging("INFO", "Get Text Ordered Quantity Is: " + OrderedQty);
			String UnitCost = webDB.getDriver().findElement(By.xpath(SalesOrderLocators.UnitCostTable(i))).getText();
			logs.logging("INFO", "Get Text Unit Cost Is: " + UnitCost);
			BigDecimal Quantityd = new BigDecimal(OrderedQty);
			BigDecimal Costd = new BigDecimal(UnitCost);
			BigDecimal LineAmountd = Quantityd.multiply(Costd);
			BigDecimal ExpectedLineAmountd = verifyRoundOffBigDecimalValueWithReturnBigDecimal(LineAmountd);
			VariableHandler.setSOLineAmount(ExpectedLineAmountd);
			VariableHandler.setSOTotalLineAmount(VariableHandler.getSOTotalLineAmount().add(ExpectedLineAmountd));
			String ExpectedLineAmount = verifyRoundOffBigDecimalValueWithReturnString(LineAmountd);
			logs.logging("INFO", "Expected Line Amount Is: " + ExpectedLineAmount);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Round Off Big Decimal With Return Big Decimal
	 * 
	 * @author Deepan-GWL
	 * @param number
	 * @param example
	 * @return double ExpectedNumber
	 */
	public BigDecimal verifyRoundOffBigDecimalValueWithReturnBigDecimal(BigDecimal number) {
		BigDecimal roundedResult = number.setScale(2, RoundingMode.HALF_UP);
		return roundedResult;
	}

	/**
	 * This Method Is Used To Verify Round Off Big Decimal Number With Return String
	 * 
	 * @author Deepan-GWL
	 * @param number
	 * @param example
	 * @return String ExpectedNumber
	 */
	public String verifyRoundOffBigDecimalValueWithReturnString(BigDecimal number) {
		BigDecimal roundedResult = number.setScale(2, RoundingMode.HALF_UP);
		String Expectednumber = roundedResult.toString();
		return Expectednumber;
	}

	/**
	 * Verify Discount Amount Calculations
	 * 
	 * @author Deepan-GWL
	 * @param TotalAmount
	 * @param Percentage
	 * @return
	 */
	public BigDecimal verifyDiscountAmountCalculation(BigDecimal TotalAmount, BigDecimal Percentage) {
		BigDecimal DiscountAmount = (TotalAmount.multiply(Percentage)).divide(new BigDecimal("100"));
		DiscountAmount = verifyRoundOffBigDecimalValueWithReturnBigDecimal(DiscountAmount);
		return DiscountAmount;
	}

	/**
	 * @author Deepan-GWL , @author Harikrishna - RMITS This Method Is used To
	 *         Verify the Discount Calculation
	 * @param quantity
	 */
	public void discountCalculationVerification(String quantity, String TradeDiscount, String SpecialDiscount,
			String CashDiscount) {
		commonFunc.checkRefreshCellsDisappear();
		BigDecimal DiscAmountPerQty = new BigDecimal("0");
		BigDecimal TotalDiscountAmount = new BigDecimal("0");
		BigDecimal LineAmount = VariableHandler.getSOLineAmount();
		logs.logging("INFO", "Sales Order Line Amount :" + LineAmount);
		String PriceBasis = verifyItemPriceBasis();
		if (PriceBasis.equalsIgnoreCase("Open Order") || PriceBasis.equalsIgnoreCase("Price List")) {
			flag = webDB.waitForElement(SalesOrderLocators.SOTradeDiscountPercentageReadOnlyField, ElementType.Xpath);
			if (flag) {
				String TradeDiscountPercentage = webDB.getDomPropertyFromElement(
						SalesOrderLocators.SOTradeDiscountPercentageReadOnlyField, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value"));
				logs.logging("INFO", "Get Text Trade Discount Percentage is " + TradeDiscountPercentage);
				BigDecimal TradeAmountOA = verifyDiscountAmountCalculation(LineAmount,
						new BigDecimal(TradeDiscountPercentage));
				VariableHandler.setSOTradeDiscountAmount(TradeAmountOA);
				BigDecimal SpecAssesableValue = LineAmount.subtract(TradeAmountOA);
				logs.logging("INFO", "Expected Special Assesable Value :" + SpecAssesableValue);

				SpecAssesableValue = verifyRoundOffBigDecimalValueWithReturnBigDecimal(SpecAssesableValue);
				VariableHandler.setSOSpecialAssesableValue(SpecAssesableValue);
				BigDecimal SplDiscAmount = verifyDiscountAmountCalculation(SpecAssesableValue,
						new BigDecimal(SpecialDiscount));
				logs.logging("INFO", "Expected Special Discount Amount :" + SplDiscAmount);
				logs.logging("INFO", "Expected Special Discount Percentage :" + SpecialDiscount);
				VariableHandler.setSOSpecialDiscountAmount(SplDiscAmount);

				BigDecimal CashAssesableValue = SpecAssesableValue.subtract(SplDiscAmount);
				logs.logging("INFO", "Expected Cash Assesable Value : " + CashAssesableValue);

				CashAssesableValue = verifyRoundOffBigDecimalValueWithReturnBigDecimal(CashAssesableValue);
				VariableHandler.setSOCashAssesableValue(CashAssesableValue);
				BigDecimal cashDiscountAmount = verifyDiscountAmountCalculation(CashAssesableValue,
						new BigDecimal(CashDiscount));
				logs.logging("INFO", "Expected Cash Discount Amount :" + cashDiscountAmount);
				logs.logging("INFO", "Expected Cash Discount Percentage :" + CashDiscount);
				VariableHandler.setSOCashDiscountAmount(cashDiscountAmount);

				BigDecimal Qty = new BigDecimal(quantity);

				DiscAmountPerQty = (TradeAmountOA.add(SplDiscAmount).add(cashDiscountAmount)).divide(Qty, 2,
						RoundingMode.HALF_UP);
				logs.logging("INFO", "Expected Discount Amt Per Qty :" + DiscAmountPerQty);
				VariableHandler.setSODiscountAmountPerQty(DiscAmountPerQty);

				TotalDiscountAmount = (TradeAmountOA.add(SplDiscAmount).add(cashDiscountAmount));
				VariableHandler.setSODiscountAmount(TotalDiscountAmount);
				VariableHandler
						.setSOTotalDiscountAmount(VariableHandler.getSOTotalDiscountAmount().add(TotalDiscountAmount));
				logs.logging("INFO", "Total Discount amount Is : " + VariableHandler.getSOTotalDiscountAmount());

				BigDecimal GrossAmount1 = VariableHandler.getSOLineAmount()
						.subtract(VariableHandler.getSOTotalDiscountAmount());
				logs.logging("INFO", "Gross Amount Is :" + GrossAmount1);
				String GrossAmount = verifyRoundOffBigDecimalValueWithReturnString(GrossAmount1);
				VariableHandler.setSOGrossAmount(GrossAmount1);
				VariableHandler.setSOTotalGrossAmount(VariableHandler.getSOTotalGrossAmount().add(GrossAmount1));
				logs.logging("INFO", "Total Gross amount Is : " + VariableHandler.getSOTotalGrossAmount());

				verifyDomPropertyValidation(SalesOrderLocators.SOTradeAssesableValueField,
						LineAmount.stripTrailingZeros().toPlainString());
				logs.logging("INFO", "Trade Assesable Value is Verified");
				verifyDomPropertyValidation(SalesOrderLocators.SOSpecialAssesableValueField,
						SpecAssesableValue.stripTrailingZeros().toPlainString());
				logs.logging("INFO", "Special Assesable Value is Verified");
				verifyDomPropertyValidation(SalesOrderLocators.SOCashAssesableValueField,
						CashAssesableValue.stripTrailingZeros().toPlainString());
				logs.logging("INFO", "Cash Assesable Value is Verified");
				verifyDomPropertyValidation(SalesOrderLocators.SOTradeDiscountAmountField,
						TradeAmountOA.stripTrailingZeros().toPlainString());
				logs.logging("INFO", "Trade Discount Amount is Verified");
				verifyDomPropertyValidation(SalesOrderLocators.SOSpecialDiscountAmountField,
						SplDiscAmount.stripTrailingZeros().toPlainString());
				logs.logging("INFO", "Special Discount Amount is Verified");
				verifyDomPropertyValidation(SalesOrderLocators.SOCashDiscountAmountField,
						cashDiscountAmount.stripTrailingZeros().toPlainString());
				logs.logging("INFO", "Cash Discount Amount is Verified");

				flag = webDB.waitForElement(SalesOrderLocators.DiscountAmtPerQty, ElementType.Xpath);
				if (flag) {
					String ActDiscAmtPerQty = webDB.getDomPropertyFromElement(SalesOrderLocators.DiscountAmtPerQty,
							ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value")).replace(",", "");
					logs.logging("INFO", "Get Text Discount Amount Per Qty Is :" + ActDiscAmtPerQty);
					flag = ActDiscAmtPerQty.equals(DiscAmountPerQty.toPlainString());
					if (flag) {
						logs.logging("INFO", "Discount Amount Per Qty :" + DiscAmountPerQty
								+ " is Verified With Actual Discount Amount Per Qty :" + ActDiscAmtPerQty);
					} else {
						logs.logging("INFO", "Not Verified Expected Discount Amount Per Qty :" + GrossAmount
								+ "Actual Discount Amount Per Qty :" + ActDiscAmtPerQty);
					}
				}
//				verifyDomPropertyValidationWithReplaceOfComma(SalesOrderLocators.DiscountAmtPerQty,
//						DiscAmountPerQty.toPlainString());
//				logs.logging("INFO", "Discount Amount Per Qty is Verified");

				flag = webDB.waitForElement(SalesOrderLocators.SOGrossAmountField, ElementType.Xpath);
				if (flag) {
					String ActGrossAmt = webDB.getDomPropertyFromElement(SalesOrderLocators.SOGrossAmountField,
							ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value")).replace(",", "");
					logs.logging("INFO", "Get text Gross Amount Is :" + ActGrossAmt);
					flag = ActGrossAmt.equals(GrossAmount);
					if (flag) {
						logs.logging("INFO", "Gross Amount :" + GrossAmount + " is Verified With Actual Gross Amount :"
								+ ActGrossAmt);
					} else {
						logs.logging("INFO", "Not Verified Expected Gross Amount :" + GrossAmount
								+ "Actual Gross Amount :" + ActGrossAmt);
					}
				}
//				verifyDomPropertyValidation(SalesOrderLocators.SOGrossAmountField, GrossAmount.toPlainString());
//				logs.logging("INFO", "Gross Amount is Verified");
			}
		} else {
			commonFunc.checkRefreshCellsDisappear();

			BigDecimal TradeAmount = verifyDiscountAmountCalculation(LineAmount, new BigDecimal(TradeDiscount));
			logs.logging("INFO", "Expected Trade Discount Amount :" + TradeAmount);
			logs.logging("INFO", "Expected Trade Discount Percentage :" + TradeDiscount);
			VariableHandler.setSOTradeDiscountAmount(TradeAmount);

			BigDecimal SpecAssesableValue = LineAmount.subtract(TradeAmount);
			logs.logging("INFO", "Expected Special Assesable Value :" + SpecAssesableValue);

			SpecAssesableValue = verifyRoundOffBigDecimalValueWithReturnBigDecimal(SpecAssesableValue);
			VariableHandler.setSOSpecialAssesableValue(SpecAssesableValue);
			BigDecimal SplDiscAmount = verifyDiscountAmountCalculation(SpecAssesableValue,
					new BigDecimal(SpecialDiscount));
			logs.logging("INFO", "Expected Special Discount Amount :" + SplDiscAmount);
			logs.logging("INFO", "Expected Special Discount Percentage :" + SpecialDiscount);
			VariableHandler.setSOSpecialDiscountAmount(SplDiscAmount);

			BigDecimal CashAssesableValue = SpecAssesableValue.subtract(SplDiscAmount);
			logs.logging("INFO", "Expected Cash Assesable Value : " + CashAssesableValue);

			CashAssesableValue = verifyRoundOffBigDecimalValueWithReturnBigDecimal(CashAssesableValue);
			VariableHandler.setSOCashAssesableValue(CashAssesableValue);
			BigDecimal cashDiscountAmount = verifyDiscountAmountCalculation(CashAssesableValue,
					new BigDecimal(CashDiscount));
			logs.logging("INFO", "Expected Cash Discount Amount :" + cashDiscountAmount);
			logs.logging("INFO", "Expected Cash Discount Percentage :" + CashDiscount);
			VariableHandler.setSOCashDiscountAmount(cashDiscountAmount);

			BigDecimal Qty = new BigDecimal(quantity);

			DiscAmountPerQty = (TradeAmount.add(SplDiscAmount).add(cashDiscountAmount)).divide(Qty, 2,
					RoundingMode.HALF_UP);
			logs.logging("INFO", "Expected Discount Amt Per Qty :" + DiscAmountPerQty);
			VariableHandler.setSODiscountAmountPerQty(DiscAmountPerQty);

			TotalDiscountAmount = (TradeAmount.add(SplDiscAmount).add(cashDiscountAmount));
			VariableHandler.setSODiscountAmount(TotalDiscountAmount);
			VariableHandler
					.setSOTotalDiscountAmount(VariableHandler.getSOTotalDiscountAmount().add(TotalDiscountAmount));
			logs.logging("INFO", "Total Discount amount Is : " + VariableHandler.getSOTotalDiscountAmount());

			BigDecimal GrossAmount1 = VariableHandler.getSOLineAmount()
					.subtract(VariableHandler.getSOTotalDiscountAmount());
			logs.logging("INFO", "Gross Amount Is :" + GrossAmount1);
			String GrossAmount = verifyRoundOffBigDecimalValueWithReturnString(GrossAmount1);
			VariableHandler.setSOGrossAmount(GrossAmount1);
			VariableHandler.setSOTotalGrossAmount(VariableHandler.getSOTotalGrossAmount().add(GrossAmount1));
			logs.logging("INFO", "Total Gross amount Is : " + VariableHandler.getSOTotalGrossAmount());

			verifyDomPropertyValidation(SalesOrderLocators.SOTradeAssesableValueField,
					LineAmount.stripTrailingZeros().toPlainString());
			logs.logging("INFO", "Trade Assesable Value is Verified");
			verifyDomPropertyValidation(SalesOrderLocators.SOSpecialAssesableValueField,
					SpecAssesableValue.stripTrailingZeros().toPlainString());
			logs.logging("INFO", "Special Assesable Value is Verified");
			verifyDomPropertyValidation(SalesOrderLocators.SOCashAssesableValueField,
					CashAssesableValue.stripTrailingZeros().toPlainString());
			logs.logging("INFO", "Cash Assesable Value is Verified");
			verifyDomPropertyValidation(SalesOrderLocators.SOTradeDiscountAmountField,
					TradeAmount.stripTrailingZeros().toPlainString());
			logs.logging("INFO", "Trade Discount Amount is Verified");
			verifyDomPropertyValidation(SalesOrderLocators.SOSpecialDiscountAmountField,
					SplDiscAmount.stripTrailingZeros().toPlainString());
			logs.logging("INFO", "Special Discount Amount is Verified");
			verifyDomPropertyValidation(SalesOrderLocators.SOCashDiscountAmountField,
					cashDiscountAmount.stripTrailingZeros().toPlainString());
			logs.logging("INFO", "Cash Discount Amount is Verified");

			flag = webDB.waitForElement(SalesOrderLocators.DiscountAmtPerQty, ElementType.Xpath);
			if (flag) {
				String ActDiscAmtPerQty = webDB.getDomPropertyFromElement(SalesOrderLocators.DiscountAmtPerQty,
						ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value")).replace(",", "");
				logs.logging("INFO", "Get Text Discount Amount Per Qty Is :" + ActDiscAmtPerQty);
				flag = ActDiscAmtPerQty.equals(DiscAmountPerQty.toPlainString());
				if (flag) {
					logs.logging("INFO", "Discount Amount Per Qty :" + DiscAmountPerQty
							+ " is Verified With Actual Discount Amount Per Qty :" + ActDiscAmtPerQty);
				} else {
					logs.logging("INFO", "Not Verified Expected Discount Amount Per Qty :" + GrossAmount
							+ "Actual Discount Amount Per Qty :" + ActDiscAmtPerQty);
				}
			}

//			verifyDomPropertyValidationWithReplaceOfComma(SalesOrderLocators.DiscountAmtPerQty,
//					DiscAmountPerQty.toPlainString());
//			logs.logging("INFO", "Discount Amount Per Qty is Verified");
			flag = webDB.waitForElement(SalesOrderLocators.SOGrossAmountField, ElementType.Xpath);
			if (flag) {
				String ActGrossAmt = webDB.getDomPropertyFromElement(SalesOrderLocators.SOGrossAmountField,
						ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value")).replace(",", "");
				logs.logging("INFO", "Get text Gross Amount Is :" + ActGrossAmt);
				flag = ActGrossAmt.equals(GrossAmount);
				if (flag) {
					logs.logging("INFO",
							"Gross Amount :" + GrossAmount + " is Verified With Actual Gross Amount :" + ActGrossAmt);
				} else {
					logs.logging("INFO", "Not Verified Expected Gross Amount :" + GrossAmount + "Actual Gross Amount :"
							+ ActGrossAmt);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used To Verify The Sales Order Line
	 *         Items Details And Currency
	 * @param Item
	 * @param Currency
	 */
	public void verifyLineSODetailsValidations(String Item, String Currency) {
		verifyGetTextValidation(SalesOrderLocators.ItemName(1),
				itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.ITEM_ID));
		logs.logging("INFO", "Item ID is Verified Sucessfully");
		verifyGetTextValidation(SalesOrderLocators.ItemRev(1),
				itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.ITEM_REV));
		logs.logging("INFO", "Item Rev is Verified Sucessfully");
		verifyGetTextValidation(SalesOrderLocators.ItemDescription(1),
				itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.ITEM_DESC));
		logs.logging("INFO", "Item Description is Verified Sucessfully");
		verifyGetTextValidation(SalesOrderLocators.UOM(1),
				itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.SALE_UOM));
		logs.logging("INFO", "Sales UOM For This Item : " + Item + " is Verified Sucessfully");
		verifyGetTextValidation(SalesOrderLocators.HSNSACCode(1),
				itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.HSN_CODE));
		logs.logging("INFO", "HSN Code For This Item : " + Item + " is Verified Sucessfully");
		if (Currency.equals(commonFunc.getDataFromTestDataSheet("CurrencyInr"))) {
			verifyGetTextValidation(SalesOrderLocators.TaxPercent(1),
					itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.TAX_PCT));
			logs.logging("INFO", "Tax Percentage For This HSN Code : " + SOLineDetailsName.HSN_CODE
					+ " Against This Item : " + Item + " is Verified Sucessfully");
			navigateToLineDetailsPage();
		} else {
			navigateToLineDetailsPage();
			verifyDomPropertyValidation(SalesOrderLocators.TaxAmt,
					commonFunc.getDataFromTestDataSheet("DoubleDecimal"));
			logs.logging("INFO", "TAX Amount is Verified Sucessfully");
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used To Verify The Line Items And
	 *         Line Details (1) and (2)
	 * @param Item
	 * @param Currency
	 * @param Quantity
	 */
	public void lineDetailVerification(String Item, String Currency, String Quantity) {
		verifyLineSODetailsValidations(Item, Currency);
		verifySOlinedetails1(Item, Quantity);
		navigateToLineDetailsPage2();
		verifySOLineDetailsPage2(Item);
	}

	/**
	 * @author Harikrishna -RMITS This Method is Used To GetText The Currency in the
	 *         Header of the Sales Order Screen And Return As String
	 * @return String headerCurrency
	 */
	public String getTextCurrencyInHeader() {
		String HeaderCurrency = null;
		flag = webDB.waitForElement(SalesOrderLocators.Currency, ElementType.Xpath);
		if (flag) {
			HeaderCurrency = webDB.getTextFromElement(SalesOrderLocators.Currency, ElementType.Xpath);
			logs.logging("INFO", "Get Text Header Currency Is : " + HeaderCurrency);
			VariableHandler.setHeaderCurrency(HeaderCurrency);
		}
		return HeaderCurrency;
	}

	/**
	 * @author Harikrishna -RMITS This Method is Used To GetText The Currency in the
	 *         Header of the Sales Order Screen And Return As String
	 * @return String HeaderCurrencyAfterSaveTheLine
	 */
	public String getTextCurrencyInHeaderAfterSaveTheLine() {
		String HeaderCurrencyAfterSaveTheLine = null;
		flag = webDB.waitForElement(SalesOrderLocators.AfterLineSaveCurrency, ElementType.Xpath);
		if (flag) {
			HeaderCurrencyAfterSaveTheLine = webDB.getTextFromElement(SalesOrderLocators.AfterLineSaveCurrency,
					ElementType.Xpath);
			logs.logging("INFO", "Get Text Header Currency Is : " + HeaderCurrencyAfterSaveTheLine);
			VariableHandler.setHeaderCurrencyAfterSaveTheLine(HeaderCurrencyAfterSaveTheLine);
		}
		return HeaderCurrencyAfterSaveTheLine;
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used To Navigate The Line Details
	 *         Page (1)
	 */
	public void navigateToLineDetailsPage() {
		webDB.moveToElement(SalesOrderLocators.LineDetails(1), ElementType.Xpath);
		flag = webDB.waitForElement(SalesOrderLocators.LineDetails(1), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.LineDetails(1), ElementType.Xpath);
		}
		flag = webDB.waitForElement(SalesOrderLocators.LinewisedetailsHeader, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Navigated to Line wise Details");
			webDB.switchToFrame(SalesOrderLocators.LineDetailsFrame, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna -RMITS This Method Is Used To Get The PriceBasis Of the
	 *         Item And Return As String
	 * @return String PriceBasis
	 */
	public String verifyItemPriceBasis() {
		String PriceBasis = null;
		flag = webDB.waitForElement(SalesOrderLocators.PriceDetails, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.PriceDetails, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.PriceBasisField, ElementType.Xpath);
			if (flag) {
				PriceBasis = webDB.getDomPropertyFromElement(SalesOrderLocators.PriceBasisField, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value"));
				logs.logging("INFO", "Get Text PriceBasis Of the Item Is: " + PriceBasis);
			}
		}
		return PriceBasis;
	}

	/**
	 * @author Harikrishna - RMITS This Method Is Used To verify The Sales Order
	 *         Line Details (1)
	 * @param Item
	 * @param Qty
	 */
	public void verifySOlinedetails1(String Item, String Quantity) {
		String TradeDiscount = RandomStringUtils.randomNumeric(1);
		String SpecialDiscount = RandomStringUtils.randomNumeric(1);
		String CashDiscount = RandomStringUtils.randomNumeric(1);

		String PriceBasis = verifyItemPriceBasis();
		flag = PriceBasis
				.equals(itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.PRICE_BASIS_TYPE));
		if (flag) {
			logs.logging("INFO", "Price Basis is " + PriceBasis + " For This Item :" + Item + "is Verified");
		} else {
			logs.logging("INFO",
					"Verification Failed Expected Value :"
							+ itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.PRICE_BASIS_TYPE)
							+ "Get Text Value :" + PriceBasis);
		}
		if ((!"Open Order".equals(PriceBasis)) && (!"Price List".equals(PriceBasis))) {
			enterSODiscountDetails(SalesOrderLocators.SOTradeDiscountpercentageField, TradeDiscount);
			commonFunc.checkRefreshCellsDisappear();
		}
		enterSODiscountDetails(SalesOrderLocators.SOSpecialDiscountpercentageField, SpecialDiscount);
		commonFunc.checkRefreshCellsDisappear();
		enterSODiscountDetails(SalesOrderLocators.SOCashDiscountpercentageField, CashDiscount);
		commonFunc.checkRefreshCellsDisappear();
		discountCalculationVerification(Quantity, TradeDiscount, SpecialDiscount, CashDiscount);
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna -RMITS This Method Is Used To navigate To the Line
	 *         Details Page (2)
	 */
	public void navigateToLineDetailsPage2() {
		flag = webDB.waitForElement(SalesOrderLocators.LinedetailsTab2, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.LinedetailsTab2, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.CustItemID, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sucessfully Navigated To Line Details 2");
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used To Verify the Sales order Line
	 *         Details (2)
	 * @param Item
	 */
	public void verifySOLineDetailsPage2(String Item) {
		verifyDomPropertyValidation(SalesOrderLocators.CustItemID,
				itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.CUST_ITEM_ID));
		logs.logging("INFO", "Customer Item ID Verified Sucessfully");
		verifyDomPropertyValidation(SalesOrderLocators.CustIdDesc,
				itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.CUST_ITEM_DESC));
		logs.logging("INFO", "Customer Item Description Verified Sucessfully");
		verifyDomPropertyValidation(SalesOrderLocators.WarehouseDesc,
				itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.WAREHOUSE_DESC));
		logs.logging("INFO", "Warehouse Description Verified Sucessfully");
		verifyDomPropertyValidation(SalesOrderLocators.SubClass,
				itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.SUB_CLASS));
		logs.logging("INFO", "SUBCLASS For This Item : " + Item + "is Verified");
		verifyDomPropertyValidation(SalesOrderLocators.Class,
				itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.CLASS));
		logs.logging("INFO", "CLASS For This Item : " + Item + "is Verified");
		verifyDomPropertyValidation(SalesOrderLocators.Promotional,
				itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.PROMOTIONAL));
		logs.logging("INFO", "Sales Promotional Type For This Item : " + Item + "is Verified");
		verifyDomPropertyValidation(SalesOrderLocators.PriceClass,
				itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.PRICECLASS));
		logs.logging("INFO", "Price Class Type For This Item : " + Item + "is Verified");
		verifyDomPropertyValidation(SalesOrderLocators.StockUOM,
				itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.STOCK_UOM));
		logs.logging("INFO", "Stock UOM For This Item : " + Item + "is Verified");
		verifyDomPropertyValidation(SalesOrderLocators.ConvFact,
				itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.CONV_FACT));
		logs.logging("INFO", "Conversion Factor For This Item : " + Item + "is Verified");
		verifyGetTextValidation(SalesOrderLocators.ToleranceType,
				itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.TOLERANCE_TYPE));
		logs.logging("INFO", "Sales Tolerance Type For This Item : " + Item + "is Verified");
	}

	/**
	 * @author Harikrishna -RMITS This Method is Used To Verify the Customer Details
	 *         In the Sales Order Screen
	 * @param customer
	 */
	public void customerDetailsVerification(String customer) {
		verifySOCustomerDetailsValidations(customer);
		navigateToLocationDetails();
		verifyLocationDetails(customer);
		navigateToGeneralTab();
		verifyGeneralTabDetails1(customer);
		navigateToLineTab();
	}

	/**
	 * @author Harikrishna - RMITS This Method Is Used Verify The Sales Order
	 *         Customer Details Validations
	 * @param Customer
	 */
	public void verifySOCustomerDetailsValidations(String Customer) {
		verifyGetTextValidation(SalesOrderLocators.HeaderCustIDField,
				itemDetailHandler.getSOCustomerDetails(Customer, SOCustomerDetailsName.CUST_ID));
		logs.logging("INFO", "Customer ID Verified Sucessfully");
		verifyGetTextValidation(SalesOrderLocators.HeaderCustDescField,
				itemDetailHandler.getSOCustomerDetails(Customer, SOCustomerDetailsName.CUST_DESC));
		logs.logging("INFO", "Customer Description Is Verified Sucessfully");
	}

	/**
	 * @author Harikrishna - RMITS This Method Is used To Click / Navigate To the
	 *         Location Details Button
	 */
	public void navigateToLocationDetails() {
		flag = webDB.waitForElement(SalesOrderLocators.LocationDetails, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.LocationDetails, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.LocationDetailsHeaderName, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated To Location Details Tab");
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna -RMITS This Method Is Used to Verify The Datas of the
	 *         Customer in the Location Details Button
	 * @param Customer
	 */
	public void verifyLocationDetails(String Customer) {
		webDB.switchToFrame(SalesOrderLocators.locationDetailsFrame, ElementType.Xpath);
		flag = webDB.waitForElement(SalesOrderLocators.SalesGSTtype, ElementType.Xpath);
		if (flag) {
			commonFunc.checkRefreshCellsDisappear();
			verifyDomPropertyValidation(SalesOrderLocators.GSTtype,
					itemDetailHandler.getSOCustomerDetails(Customer, SOCustomerDetailsName.GST_TYPE));
			logs.logging("INFO", "GST Type For This Customer :" + Customer + "is Verified");
			verifyDomPropertyValidation(SalesOrderLocators.SalesGSTtype,
					itemDetailHandler.getSOCustomerDetails(Customer, SOCustomerDetailsName.SALES_GST_TYPE));
			logs.logging("INFO", "Sales GST Type For This Customer :" + Customer + "is Verified");
			verifyDomPropertyValidation(SalesOrderLocators.GSTClassification,
					itemDetailHandler.getSOCustomerDetails(Customer, SOCustomerDetailsName.GST_CLASSIFICATION));
			logs.logging("INFO", "GST Classification For This Customer :" + Customer + "is Verified");
			flag = webDB.waitForElement(SalesOrderLocators.LocationDetailSave, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesOrderLocators.LocationDetailSave, ElementType.Xpath);
				logs.logging("INFO", "Location Details Save Icon Is Clicked Sucessfully");
			}
		}
		webDB.switchToDefaultContent();
		commonFunc.hardWait(2000);
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna -RMITS This Method is Used To Navigate General Tab in the
	 *         Transaction Screen
	 */
	public void navigateToGeneralTab() {
		flag = webDB.waitForElement(SalesOrderLocators.GeneralTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.GeneralTab, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(SalesOrderLocators.SOGeneralAdvanceButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated Sucessfully To General Tab");
				webDB.clickAnElement(SalesOrderLocators.SOGeneralAdvanceButton, ElementType.Xpath);
				logs.logging("INFO", "Advance ( + ) Button Is clicked Successfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna -RMITS This Method is Used to Verify the Datas of the
	 *         Customer In the General Tab Details (1)
	 * @param Customer
	 */
	public void verifyGeneralTabDetails1(String Customer) {
		flag = webDB.waitForElement(SalesOrderLocators.PrintCustDesc, ElementType.Xpath);
		if (flag) {
			verifyDomPropertyValidation(SalesOrderLocators.PrintCustDesc,
					itemDetailHandler.getSOCustomerDetails(Customer, SOCustomerDetailsName.CUST_PRINT_NAME));
			logs.logging("INFO",
					"Print Customer Description Field Data For This Customer :" + Customer + "is Verified");
			verifyDomPropertyValidation(SalesOrderLocators.CustSubGroup,
					itemDetailHandler.getSOCustomerDetails(Customer, SOCustomerDetailsName.CUST_SUB_GRP));
			logs.logging("INFO", "SUBGROUP For This Customer :" + Customer + "is Verified");
			verifyDomPropertyValidation(SalesOrderLocators.CustGroup,
					itemDetailHandler.getSOCustomerDetails(Customer, SOCustomerDetailsName.CUST_GRP));
			logs.logging("INFO", "GROUP For This Customer :" + Customer + "is Verified");
			verifyDomPropertyValidation(SalesOrderLocators.SalesZone,
					itemDetailHandler.getSOCustomerDetails(Customer, SOCustomerDetailsName.SALES_ZONE));
			logs.logging("INFO", "Sales Zone For This Customer :" + Customer + "is Verified");
			verifyDomPropertyValidation(SalesOrderLocators.SalesRegion,
					itemDetailHandler.getSOCustomerDetails(Customer, SOCustomerDetailsName.SALES_REGION));
			logs.logging("INFO", "Sales Region For This Customer :" + Customer + "is Verified");
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna -RMITS This Method Is Used To Navigate To the Line Tab
	 */
	public void navigateToLineTab() {
		flag = webDB.waitForElement(SalesOrderLocators.LineTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.LineTab, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.LineWiseAddIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated Sucessfully To Line Tab");
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS This Method Is used To Click the Print Button in
	 *         the Transaction Screen
	 * 
	 */
	public void clickPrintButton() {
		flag = webDB.waitForElementUntilGivenTime(SalesOrderLocators.PrintButton, ElementType.Xpath, 45);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.PrintButton, ElementType.Xpath);
			logs.logging("INFO", "Print button is Clicked Sucessfully");
			flag = webDB.switchToChildWindow();
			commonFunc.hardWait(1000);
			if (flag) {
				logs.logging("INFO", "Switched To Child Window");
				flag = webDB.closeExistingTab();
				if (flag) {
					logs.logging("INFO", "Existing Tab is Closed Sucessfully");
					flag = webDB.switchToMainTab();
					if (flag) {
						logs.logging("INFO", "Switch Back To Main Tab Sucessfully");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS This Methos is Used to Click the Print Button In
	 *         the Find Screen And then Auto Closes the Tab And Then Switch Back to
	 *         the main Tab
	 */
	public void clickPrintButtonInFindScreen() {
		flag = webDB.waitForElement(SalesOrderLocators.PrintIconInFind, ElementType.CssSelector);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.PrintIconInFind, ElementType.CssSelector);
			flag = webDB.switchToChildWindow();
			commonFunc.hardWait(1000);
			if (flag) {
				logs.logging("INFO", "Switched To Child Window");
				flag = webDB.closeExistingTab();
				if (flag) {
					logs.logging("INFO", "Existing Tab is Closed Sucessfully");
					flag = webDB.switchToMainTab();
					if (flag) {
						logs.logging("INFO", "Switch Back To Main Tab Sucessfully");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS This Method Is used to Get The Trade Discount
	 *         (Assesable Value, Percentage and Discount Amount)
	 */
	public void getTradeDiscountValues() {
		flag = webDB.waitForElement(SalesOrderLocators.SOTradeAssesableValueField, ElementType.Xpath);
		if (flag) {
			String TradeAssesableValue = webDB.getDomPropertyFromElement(SalesOrderLocators.SOTradeAssesableValueField,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			logs.logging("INFO", "Get Text Trade Assesable Value is " + TradeAssesableValue);
			flag = webDB.waitForElement(SalesOrderLocators.SOTradeDiscountPercentageReadOnlyField, ElementType.Xpath);
			if (flag) {
				String TradeDiscountPercentage = webDB.getDomPropertyFromElement(
						SalesOrderLocators.SOTradeDiscountPercentageReadOnlyField, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value"));
				logs.logging("INFO", "Get Text Trade Discount Percentage is " + TradeDiscountPercentage);
				flag = webDB.waitForElement(SalesOrderLocators.SOTradeDiscountAmountField, ElementType.Xpath);
				if (flag) {
					String TradeDiscountAmount = webDB.getDomPropertyFromElement(
							SalesOrderLocators.SOTradeDiscountAmountField, ElementType.Xpath,
							commonFunc.getDataFromTestDataSheet("Value"));
					logs.logging("INFO", "Get Text Trade Discount Amount is " + TradeDiscountAmount);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS This Method Is Used To Verify the Sales Order Tax
	 *         Calculation
	 */
	public void verifySalesOrderTaxCalculation() {
		commonFunc.checkRefreshCellsDisappear();
		BigDecimal TotalGrossAmount = VariableHandler.getSOTotalGrossAmount();
		logs.logging("INFO", "Total Gross Amount Is :" + VariableHandler.getSOTotalGrossAmount());
		BigDecimal TotalDiscountAmount1 = VariableHandler.getSOTotalDiscountAmount();
		String TotalDiscountAmount = verifyRoundOffBigDecimalValueWithReturnString(TotalDiscountAmount1);
		logs.logging("INFO", "Total Discount Amount Is :" + TotalDiscountAmount);
		if (getTextCurrencyInHeader().equalsIgnoreCase(commonFunc.getDataFromTestDataSheet("CurrencyInr"))) {
			flag = webDB.waitForElement(SalesOrderLocators.TaxPercent(1), ElementType.Xpath);
			if (flag) {
				BigDecimal taxPercentage = new BigDecimal(
						webDB.getTextFromElement(SalesOrderLocators.TaxPercent(1), ElementType.Xpath));
				taxPercentage = verifyRoundOffBigDecimalValueWithReturnBigDecimal(taxPercentage);
				logs.logging("INFO", "Displayed tax Percentage: " + taxPercentage);
				BigDecimal ExpTaxAmt = verifyRoundOffBigDecimalValueWithReturnBigDecimal(
						TotalGrossAmount.divide(new BigDecimal("100")).multiply(taxPercentage));
				logs.logging("INFO", "Expected Tax Amount : " + ExpTaxAmt);
				VariableHandler.setSOTaxAmount(ExpTaxAmt);

				BigDecimal IGSTAmount = new BigDecimal(
						webDB.getTextFromElement(SalesOrderLocators.IGSTtable, ElementType.Xpath));
				IGSTAmount = verifyRoundOffBigDecimalValueWithReturnBigDecimal(IGSTAmount);

				BigDecimal CGSTAmount = new BigDecimal(
						webDB.getTextFromElement(SalesOrderLocators.CGSTtable, ElementType.Xpath));
				CGSTAmount = verifyRoundOffBigDecimalValueWithReturnBigDecimal(CGSTAmount);

				BigDecimal SGSTAmount = new BigDecimal(
						webDB.getTextFromElement(SalesOrderLocators.SGSTtable, ElementType.Xpath));
				SGSTAmount = verifyRoundOffBigDecimalValueWithReturnBigDecimal(SGSTAmount);

				BigDecimal UTGSTAmount = new BigDecimal(
						webDB.getTextFromElement(SalesOrderLocators.UTGSTtable, ElementType.Xpath));
				UTGSTAmount = verifyRoundOffBigDecimalValueWithReturnBigDecimal(UTGSTAmount);

				BigDecimal taxAmount = IGSTAmount.add(CGSTAmount).add(SGSTAmount).add(UTGSTAmount);
				taxAmount = verifyRoundOffBigDecimalValueWithReturnBigDecimal(taxAmount);
				logs.logging("INFO", "After Adding Displayed Tax Amounts: " + taxAmount);

				flag = taxAmount.equals(ExpTaxAmt);
				if (flag) {
					logs.logging("INFO", "Tax Amount Verified, Tax added value: " + taxAmount);
					logs.logging("INFO", "verifing total amount after calculating tax amount!!!");
					BigDecimal totalAmount = new BigDecimal(VariableHandler.getSOTotalAmount());
					totalAmount = verifyRoundOffBigDecimalValueWithReturnBigDecimal(totalAmount);
					logs.logging("INFO", "Displayed Total Amount : " + totalAmount);
					BigDecimal calculationAmount = verifyRoundOffBigDecimalValueWithReturnBigDecimal(
							VariableHandler.getSOTotalGrossAmount().add(taxAmount));
					logs.logging("INFO", "Calculated Total Amount : " + calculationAmount);
					VariableHandler.setSOEXPTotalOrderAmount(calculationAmount);
					flag = totalAmount.equals(calculationAmount);
					if (flag) {
						logs.logging("INFO", "Verified Total Amount Displayed and Calculated Total Amount Matched");
					}
				}
			}
		} else {
			logs.logging("INFO", "Tax Is Not Applicable For Foreign Currency " + getTextCurrencyInHeader());
			commonFunc.checkRefreshCellsDisappear();
			BigDecimal totalAmount = new BigDecimal(VariableHandler.getSOTotalAmount());
			totalAmount = verifyRoundOffBigDecimalValueWithReturnBigDecimal(totalAmount);
			logs.logging("INFO", "Displayed Total Amount : " + totalAmount);
			BigDecimal calculationAmount = verifyRoundOffBigDecimalValueWithReturnBigDecimal(
					VariableHandler.getSOTotalGrossAmount());
			logs.logging("INFO", "Calculated Total Amount : " + calculationAmount);
			VariableHandler.setSOEXPTotalOrderAmount(calculationAmount);
			flag = totalAmount.equals(calculationAmount);
			if (flag) {
				logs.logging("INFO", "Verified Total Amount Displayed and Calculated Total Amount Matched");
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna -RMITS This Method Is Used to set the Variable Handler To
	 *         Zero After Finishing One Test Scenarios
	 */
	public void verifyAfterMethod() {
		VariableHandler.setSOEXPTotalOrderAmount(BigDecimal.ZERO);
		VariableHandler.setSOTaxAmount(BigDecimal.ZERO);
		VariableHandler.setSOTotalDiscountAmount(BigDecimal.ZERO);
		VariableHandler.setSOTotalGrossAmount(BigDecimal.ZERO);
		VariableHandler.setSOTotalLineAmount(BigDecimal.ZERO);
		MultipleSO.clear();
		webDB.navigateToRefresh();
		webDB.switchToDefaultContent();
//		commonFunc.enterUrl();
	}

	/**
	 * Check Assertion Both Values
	 * 
	 * @author Deepan-GWL
	 * @param Actual
	 * @param Expected
	 */
	public void verifyAssertionBothValues(String Actual, String Expected) {
		logs.logging("INFO", "Actual DataBase Value Is: " + Actual);
		logs.logging("INFO", "Expected Value Is: " + Expected);
		flag = Actual.equals(Expected);
		if (flag) {
			logs.logging("INFO", "Verified Actual DataBase Value : " + Actual + " And Expected Values : " + Expected);
		} else {
			logs.logging("INFO",
					"Mismatch With Actual DataBase Value : " + Actual + " And Expected Values " + Expected);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify The Header Creation Datas With Database Values
	 * 
	 * @author Harikrishna - RMITS
	 * @param Location
	 * @param Unit
	 * @param CustomerID
	 * @param CustomerDesc
	 * @param SubVoucherType
	 */
	public void salesOrderDBHeaderValidation(String Location, String Unit, String CustomerID, String CustomerDesc,
			String SubVoucherType) {
		verifyAssertionBothValues(dbCommonFunctions.SOHeaderDetails(VariableHandler.getSONumber(),
				commonFunc.getDataFromTestDataSheet("SoDBLocationColumnName")), Location);
		verifyAssertionBothValues(dbCommonFunctions.SOHeaderDetails(VariableHandler.getSONumber(),
				commonFunc.getDataFromTestDataSheet("SoDBUnitColumnName")), Unit);
		verifyAssertionBothValues(dbCommonFunctions.SOHeaderDetails(VariableHandler.getSONumber(),
				commonFunc.getDataFromTestDataSheet("SoDBCustomerIDColumnName")), CustomerID);
		verifyAssertionBothValues(dbCommonFunctions.SOHeaderDetails(VariableHandler.getSONumber(),
				commonFunc.getDataFromTestDataSheet("SoDBCustomerDescColumnName")), CustomerDesc);
		verifyAssertionBothValues(dbCommonFunctions.SOHeaderDetails(VariableHandler.getSONumber(),
				commonFunc.getDataFromTestDataSheet("SOSubVoucherTypeDBColumnName")), SubVoucherType);
	}

	/**
	 * This Method Is Used To Compare UI Values With the Database Column Name
	 * 
	 * @author Harikrishna - RMITS
	 * @param ActualUIvalue
	 * @param ExpectedDBValue
	 */
	public void verifyAssertionUIValuesAndDBValues(String ActualUIvalue, String ExpectedDBValue) {
		logs.logging("INFO", "Get Text Actual UI Value Is: " + ActualUIvalue);
		logs.logging("INFO", "Expected DataBase Value Is: " + ExpectedDBValue);
		flag = ActualUIvalue.equals(ExpectedDBValue);
		if (flag) {
			logs.logging("INFO", "Verified Actual DataBase Value And Expected Values");
		} else {
			logs.logging("INFO", "Mismatch With Actual DataBase And Expected Values");
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Get The Text From UI And Compare With the Database
	 * Column Name
	 * 
	 * @author Harikrishna - RMITS
	 * @param UIFieldLocator
	 * @param ExpectedDBValue
	 */
	public void verifyAssertionGetTextValidation(String UIFieldLocator, String ExpectedDBValue) {
		flag = webDB.waitForElement(UIFieldLocator, ElementType.Xpath);
		if (flag) {
			String text = webDB.getTextFromElement(UIFieldLocator, ElementType.Xpath);
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
	 * This Method Is Used To Get The Text From An Locator Attribute And Compare
	 * With the Database Column Name
	 * 
	 * @author Harikrishna - RMITS
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
	 * @author Harikrishna -RMITS This Method Is used to Verify The GetText
	 *         Validations(Template)
	 * @param locators
	 * @param Value
	 */
	public void verifyGetTextValidationWithReplaceOfComma(String locators, String Value) {
		flag = webDB.waitForElement(locators, ElementType.Xpath);
		if (flag) {
			String text = webDB.getTextFromElement(locators, ElementType.Xpath).replace(",", "");
			logs.logging("INFO", "Get Text Value Is: " + text);
			logs.logging("INFO", "Expected Value Is: " + Value);
			flag = text.equals(Value);
			if (flag) {
				logs.logging("INFO", "Verified Actual Value With Expected Value");
			} else {
				logs.logging("INFO", "Verification Failed Expected Value :" + Value + "Get Text Value :" + text);
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna - RMITS This Method Is Used To Verify the DOM Property
	 *         Validations(Template)
	 * @param locators
	 * @param Value
	 */
	public void verifyDomPropertyValidationWithReplaceOfComma(String locators, String Value) {
		flag = webDB.waitForElement(locators, ElementType.Xpath);
		if (flag) {
			String text = webDB.getDomPropertyFromElement(locators, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value").replace(",", ""));
			logs.logging("INFO", "Get Text Value Is: " + text);
			logs.logging("INFO", "Expected Value Is: " + Value);
			flag = text.equals(Value);
			if (flag) {
				logs.logging("INFO", "Verified Actual Value With Expected Value");
			} else {
				logs.logging("INFO", "Verification Failed Expected Value :" + Value + "Get Text Value :" + text);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify The Header Details UI Values With Database
	 * Values
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void checkSOHeaderDetailsDataBaseValuesWithUIValues() {
		verifyAssertionGetTextValidation(SalesOrderLocators.HeaderLocationField, dbCommonFunctions.SOHeaderDetails(
				VariableHandler.getSONumber(), commonFunc.getDataFromTestDataSheet("SoDBLocationColumnName")));
		logs.logging("INFO", "Header Location Details Matched With DataBase Values");

		verifyAssertionGetTextValidation(SalesOrderLocators.HeaderUnitField, dbCommonFunctions.SOHeaderDetails(
				VariableHandler.getSONumber(), commonFunc.getDataFromTestDataSheet("SoDBUnitColumnName")));
		logs.logging("INFO", "Header Unit Details Matched With DataBase Values");

		verifyAssertionGetTextValidation(SalesOrderLocators.HeaderSubVoucherType, dbCommonFunctions.SOHeaderDetails(
				VariableHandler.getSONumber(), commonFunc.getDataFromTestDataSheet("SOSubVoucherTypeDBColumnName")));
		logs.logging("INFO", "Header Sales Order SubVoucher Type Matched With DataBase Values");

		verifyAssertionGetTextValidation(SalesOrderLocators.HeaderSOPrefixField, dbCommonFunctions.SOHeaderDetails(
				VariableHandler.getSONumber(), commonFunc.getDataFromTestDataSheet("SODBPrefixColumnName")));
		logs.logging("INFO", "Header SOPrefix Details Matched With DataBase Values");

		verifyAssertionGetTextValidation(SalesOrderLocators.HeaderSONumberField, dbCommonFunctions.SOHeaderDetails(
				VariableHandler.getSONumber(), commonFunc.getDataFromTestDataSheet("SODBDocNumberColumnName")));
		logs.logging("INFO", "Header SO Document Number Details Matched With DataBase Values");

		verifyAssertionGetTextValidation(SalesOrderLocators.Currency, dbCommonFunctions.SOHeaderDetails(
				VariableHandler.getSONumber(), commonFunc.getDataFromTestDataSheet("SODBCurrencyColumnName")));
		logs.logging("INFO", "Header Currency Details Matched With DataBase Values");

		verifyAssertionGetTextValidation(SalesOrderLocators.HeaderExchangeRateField, dbCommonFunctions.SOHeaderDetails(
				VariableHandler.getSONumber(), commonFunc.getDataFromTestDataSheet("SODBExchangeRateColumnName")));
		logs.logging("INFO", "Header ExchangeRate Details Matched With DataBase Values");

		verifyAssertionDomPropertyValidation(SalesOrderLocators.HeaderPaymentTermField,
				dbCommonFunctions.SOHeaderDetails(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("SODBPaymentTermColumnName")));
		logs.logging("INFO", "Header Payment Term Details Matched With DataBase Values");

		verifyAssertionDomPropertyValidation(SalesOrderLocators.HeaderINCOTermField, dbCommonFunctions.SOHeaderDetails(
				VariableHandler.getSONumber(), commonFunc.getDataFromTestDataSheet("SODBINCOTermColumnName")));
		logs.logging("INFO", "Header INCO Term Details Matched With DataBase Values");

		verifyAssertionDomPropertyValidation(SalesOrderLocators.HeaderShipViaField, dbCommonFunctions.SOHeaderDetails(
				VariableHandler.getSONumber(), commonFunc.getDataFromTestDataSheet("SODBShipViaColumnName")));
		logs.logging("INFO", "Header Ship Via Details Matched With DataBase Values");

//		verifyAssertionDomPropertyValidation(SalesOrderLocators.HeaderCPCcodeIDField, dbCommonFunctions.SOHeaderDetails(
//				VariableHandler.getSONumber(), commonFunc.getDataFromTestDataSheet("SODBCpcCodeIDColumnName")));
//		logs.logging("INFO", "Header CPCcode ID Details Matched With DataBase Values");

//		verifyAssertionDomPropertyValidation(SalesOrderLocators.HeaderCPCcodeDescField,
//				dbCommonFunctions.SOHeaderDetails(VariableHandler.getSONumber(),
//						commonFunc.getDataFromTestDataSheet("SODBCpcCodeDescColumnName")));
//		logs.logging("INFO", "Header CPCcode Desc Details Matched With DataBase Values");

//		verifyAssertionGetTextValidation(SalesOrderLocators.HeaderCustIDField, dbCommonFunctions.SOHeaderDetails(
//				VariableHandler.getSONumber(), commonFunc.getDataFromTestDataSheet("SoDBCustomerIDColumnName")));
//		logs.logging("INFO", "Header Customer ID Details Matched With DataBase Values");

		verifyAssertionDomPropertyValidation(SalesOrderLocators.HeaderCustDescField, dbCommonFunctions.SOHeaderDetails(
				VariableHandler.getSONumber(), commonFunc.getDataFromTestDataSheet("SoDBCustomerDescColumnName")));
		logs.logging("INFO", "Header Customer Description Details Matched With DataBase Values");
	}

	/**
	 * This Method Is Used To Verify The Line Details UI Values With Database Values
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void checkSOLineDetailsDataBaseValuesWithUIValues() {
		verifyAssertionGetTextValidation(SalesOrderLocators.ItemName(1), dbCommonFunctions.SOLineDetails(
				VariableHandler.getSONumber(), commonFunc.getDataFromTestDataSheet("SODBItemIDColumnName")));
		logs.logging("INFO", "Line ItemID Details Matched With DataBase Values");

		verifyAssertionGetTextValidation(SalesOrderLocators.ItemRev(1), dbCommonFunctions.SOLineDetails(
				VariableHandler.getSONumber(), commonFunc.getDataFromTestDataSheet("SODBItemRevColumnName")));
		logs.logging("INFO", "Line Item Rev Details Matched With DataBase Values");

		verifyAssertionGetTextValidation(SalesOrderLocators.ItemDescription(1), dbCommonFunctions.SOLineDetails(
				VariableHandler.getSONumber(), commonFunc.getDataFromTestDataSheet("SODBItemDescColumnName")));
		logs.logging("INFO", "Line Item Description Details Matched With DataBase Values");

		verifyAssertionGetTextValidation(SalesOrderLocators.UOM(1), dbCommonFunctions.SOLineDetails(
				VariableHandler.getSONumber(), commonFunc.getDataFromTestDataSheet("SODBSaleUOMColumnName")));
		logs.logging("INFO", "Line Sale UOM Details Matched With DataBase Values");

		verifyAssertionGetTextValidation(SalesOrderLocators.HSNSACCode(1), dbCommonFunctions.SOLineDetails(
				VariableHandler.getSONumber(), commonFunc.getDataFromTestDataSheet("SODBHSNCodeColumnName")));
		logs.logging("INFO", "Line HSN Code Details Matched With DataBase Values");
	}

	/**
	 * This Method Is Used To Verify The Expected Discount,Gross,Tax And Total Sales
	 * Order Amount With Database Values
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void checkSODiscountTaxAndTotalAmountDetailsDataBaseValuesWithExpectedValues() {
		BigDecimal TradeAssesableValue = VariableHandler.getSOLineAmount();
		BigDecimal SpecAssesableValue = VariableHandler.getSOSpecialAssesableValue();
		BigDecimal CashAssesableValue = VariableHandler.getSOCashAssesableValue();
		BigDecimal TradeAmount = VariableHandler.getSOTradeDiscountAmount();
		BigDecimal SplDiscAmount = VariableHandler.getSOSpecialDiscountAmount();
		BigDecimal cashDiscountAmount = VariableHandler.getSOCashDiscountAmount();
		BigDecimal DiscountAmountPerQty = VariableHandler.getSODiscountAmountPerQty();
		BigDecimal TotalGrossAmount = VariableHandler.getSOTotalGrossAmount();
		BigDecimal TotalDiscountAmount = VariableHandler.getSOTotalDiscountAmount();
		BigDecimal TaxAmount = VariableHandler.getSOTaxAmount();
		BigDecimal TotalOrderAmount = VariableHandler.getSOEXPTotalOrderAmount();

		verifyAssertionBothValues(
				dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("SODBTradeAssesableValueColumnName")),
				TradeAssesableValue.stripTrailingZeros().toPlainString());
		logs.logging("INFO", "Database Value Matched With Expected Trade Assesable Value Against This Sales Order "
				+ VariableHandler.getSONumber());

		verifyAssertionBothValues(
				dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("SODBSpecialAssesableValueColumnName")),
				SpecAssesableValue.stripTrailingZeros().toPlainString());
		logs.logging("INFO", "Database Value Matched With Expected Special Assesable Value Against This Sales Order "
				+ VariableHandler.getSONumber());

		verifyAssertionBothValues(
				dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("SODBCashAssesableValueColumnName")),
				CashAssesableValue.stripTrailingZeros().toPlainString());
		logs.logging("INFO", "Database Value Matched With Expected Cash Assesable Value Against This Sales Order "
				+ VariableHandler.getSONumber());

		verifyAssertionBothValues(
				dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("SODBTradeDiscountAmountColumnName")),
				TradeAmount.stripTrailingZeros().toPlainString());
		logs.logging("INFO", "Database Value Matched With Expected Trade Discount Amount Against This Sales Order "
				+ VariableHandler.getSONumber());

		verifyAssertionBothValues(
				dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("SODBSpecialDiscountAmountColumnName")),
				SplDiscAmount.stripTrailingZeros().toPlainString());
		logs.logging("INFO", "Database Value Matched With Expected Special Discount Amount Against This Sales Order "
				+ VariableHandler.getSONumber());

		verifyAssertionBothValues(
				dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("SODBCashDiscountAmountColumnName")),
				cashDiscountAmount.stripTrailingZeros().toPlainString());
		logs.logging("INFO", "Database Value Matched With Expected Cash Discount Amount Against This Sales Order "
				+ VariableHandler.getSONumber());

		verifyAssertionBothValues(
				dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("SODBDiscountAmountPerQtyColumnName")),
				DiscountAmountPerQty.stripTrailingZeros().toPlainString());
		logs.logging("INFO", "Database Value Matched With Expected Discount Amount Per Qty Against This Sales Order "
				+ VariableHandler.getSONumber());

		verifyAssertionBothValues(
				dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("SODBGrossAmountColumnName")),
				TotalGrossAmount.stripTrailingZeros().toPlainString());
		logs.logging("INFO", "Database Value Matched With Expected Gross Amount Against This Sales Order "
				+ VariableHandler.getSONumber());

		verifyAssertionBothValues(
				dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("SODBTotalDiscountAmountColumnName")),
				TotalDiscountAmount.stripTrailingZeros().toPlainString());
		logs.logging("INFO", "Database Value Matched With Expected Total Discount Amount Against This Sales Order "
				+ VariableHandler.getSONumber());

		verifyAssertionBothValues(
				dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("SODBTaxAmountColumnName")),
				TaxAmount.stripTrailingZeros().toPlainString());
		logs.logging("INFO", "Database Value Matched With Expected Tax Amount Against This Sales Order "
				+ VariableHandler.getSONumber());

		verifyAssertionBothValues(
				dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("SODBNetAmountColumnName")),
				TotalOrderAmount.stripTrailingZeros().toPlainString());
		logs.logging("INFO", "Database Value Matched With Expected Total Order Amount Against This Sales Order "
				+ VariableHandler.getSONumber());
	}

	/**
	 * @author Harikrishna -RMITS This Method Is Used To Verify Advance Sales Order
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean verifyAdvanceSalesOrderWithAdvancePaymentTerm() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		salesOrderDBHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("Customer"),
				commonFunc.getDataFromTestDataSheet("CustomerDesc"), commonFunc.getDataFromTestDataSheet("SO_General"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();
		changePaymentTermInSOHeader(commonFunc.getDataFromTestDataSheet("AdvancePaymentTerm"));
		addSalesOrderLineThroughExcelUD(webDB.getDataFromProperties("SODomUserDefined"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SO_HSNCode"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		navigateToGeneralTab();
		verifyIncludeAdvAmtFlagIsChecked();
		navigateToLineTab();
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
				commonFunc.hardWait(1000);
				getAdvReceiptDocumentFromDataBase();
//				verifyExpectedAdvanceReceiptWithDataBaseValues(commonFunc.getDataFromTestDataSheet("Unit_100"),
//						commonFunc.getDataFromTestDataSheet("Customer"));
				verifyCreationOfPaymentReceiptsReceivables(VariableHandler.getAdvReceiptDocNumber(),
						commonFunc.getDataFromTestDataSheet("BankName"),
						commonFunc.getDataFromTestDataSheet("Reference Number"));
//				verifyExpectedReceivedAdvAmountWithDBValues();
//				verifyAssertionBothValues(
//						dbCommonFunctions.SOAdvanceDetails(VariableHandler.getSONumber(),
//								commonFunc.getDataFromTestDataSheet("SODBAdvanceReceivedColumnName")),
//						VariableHandler.getExpectedAdvToBeReceivedInSO());
//				logs.logging("INFO",
//						"Expected Advance Received Amount Matched With DataBase Values In Sales Order Screen Against This Sales Order Document :"
//								+ VariableHandler.getSONumber());
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Change the Payment Term In the Sales order Header
	 * 
	 * @author Harikrishna -RMITS
	 * @param PaymentTerm
	 */
	public void changePaymentTermInSOHeader(String PaymentTerm) {
		flag = webDB.waitForElement(SalesOrderLocators.PaymentTermLov, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.PaymentTermLov, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.PaymentTermLOVSearchField, ElementType.Xpath);
			if (flag) {
				webDB.sendTextToAnElement(SalesOrderLocators.PaymentTermLOVSearchField, PaymentTerm, ElementType.Xpath);
				flag = webDB.waitForElement(SalesOrderLocators.PaymentTermClickSearchIcon, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SalesOrderLocators.PaymentTermClickSearchIcon, ElementType.Xpath);
					flag = webDB.waitForElement(SalesOrderLocators.PaymentTermClickHighLightText, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SalesOrderLocators.PaymentTermClickHighLightText, ElementType.Xpath);
						flag = webDB.waitForElement(SalesOrderLocators.SalesOrderHeaderSaveButton, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(SalesOrderLocators.SalesOrderHeaderSaveButton, ElementType.Xpath);
							flag = webDB.waitForElement(SalesOrderLocators.SalesOrderHeaderFindButton,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "Payment Term Is Changed Sucessfully");
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Header Wise Save Button In The Sales Order
	 * Screen
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void clickHeaderWiseSaveButton() {
		flag = webDB.waitForElement(SalesOrderLocators.SalesOrderHeaderSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.SalesOrderHeaderSaveButton, ElementType.Xpath);
			logs.logging("INFO", "Heade Wise Save Icon Is Clicked Sucessfully");
			commonFunc.checkRefreshCellsDisappear();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To verify The Include Advance Amount Flag Is Checked
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void verifyIncludeAdvAmtFlagIsChecked() {
//		verifyAdvPercentageWithUIAndDBValues();
		flag = webDB.waitForElement(SalesOrderLocators.SalesOrderincludeAdvAmtCheckBox, ElementType.Xpath);
		if (flag) {
			if (webDB.isElementSelected(SalesOrderLocators.SalesOrderInputIncludeAdvAmtCheckBox, ElementType.Xpath)) {
				logs.logging("INFO", "verified Include Advance Amount Flag Is Checked Based On the PaymentTerm");
//				verifyCheckIncludeAdvAmtFlag();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify The SO Document Status As Approved With DB
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void verifySalesOrderDocumentStatusApprovedWithDB() {
		String SODocumentStatus = dbCommonFunctions.SOHeaderDetails(VariableHandler.getSONumber(),
				commonFunc.getDataFromTestDataSheet("SODBHeaderDocumentStatusColumnName"));
		verifyAssertionBothValues(SODocumentStatus, commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		logs.logging("INFO", "Verified Sales Order Document Status Is " + SODocumentStatus
				+ " Against This Sales Order Number " + VariableHandler.getSONumber());
	}

	/**
	 * This Method Is Used To Verify The SO Document Status As Approved With DB
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void verifySalesOrderDocumentStatusCancelledWithDB() {
		String SODocumentStatus = dbCommonFunctions.SOHeaderDetails(VariableHandler.getSONumber(),
				commonFunc.getDataFromTestDataSheet("SODBHeaderDocumentStatusColumnName"));
		verifyAssertionBothValues(SODocumentStatus, commonFunc.getDataFromTestDataSheet("CancelStatus"));
		logs.logging("INFO", "Verified Sales Order Document Status Is " + SODocumentStatus
				+ " Against This Sales Order Number " + VariableHandler.getSONumber());
	}

	/**
	 * This Method Is Used To Verify The SO Document Status As CloseShort With DB
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void verifySalesOrderDocumentHeaderStatusCloseshortWithDB() {
		String SODocumentStatus = dbCommonFunctions.SOHeaderDetails(VariableHandler.getSONumber(),
				commonFunc.getDataFromTestDataSheet("SODBHeaderDocumentStatusColumnName"));
		verifyAssertionBothValues(SODocumentStatus, commonFunc.getDataFromTestDataSheet("CloseShortStatus"));
		logs.logging("INFO", "Verified Sales Order Header Document Status Is " + SODocumentStatus
				+ " Against This Sales Order Number " + VariableHandler.getSONumber());
	}

	/**
	 * This Method Is used To get Advance Receipt Document Number From DB Values
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void getAdvReceiptDocumentFromDataBase() {
		String AdvReceiptDocumentNumber = dbCommonFunctions.SOAdvanceDetails(VariableHandler.getSONumber(),
				commonFunc.getDataFromTestDataSheet("SODBAdvDocumentNumberColumnName"));
		VariableHandler.setAdvReceiptDocNumber(AdvReceiptDocumentNumber);
		logs.logging("INFO",
				"Advance Receipt Document number In Sales Order Is:" + VariableHandler.getAdvReceiptDocNumber());
		MultiAdvReceiptDocumentNumber.add(VariableHandler.getAdvReceiptDocNumber());

	}

	/**
	 * This Method Is Used To Verify The Advance Percentage With UI And Db Values
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void verifyAdvPercentageWithUIAndDBValues() {
		commonFunc.hardWait(2000);
		getPaymentTermAdvpercentagesInDB();
		commonFunc.checkRefreshCellsDisappear();
		verifyAssertionDomPropertyValidation(SalesOrderLocators.AdvMaterialPercentage,
				dbCommonFunctions.SOPaymentTermAdvPercentage(commonFunc.getDataFromTestDataSheet("AdvancePaymentTerm"),
						commonFunc.getDataFromTestDataSheet("SODBPaymentTermMaterialPercentageColumnName")));
		logs.logging("INFO", "Advance Payment Term Material Percentage is Matched With UI And DataBase");

		verifyAssertionDomPropertyValidation(SalesOrderLocators.AdvTaxPercentage,
				dbCommonFunctions.SOPaymentTermAdvPercentage(commonFunc.getDataFromTestDataSheet("AdvancePaymentTerm"),
						commonFunc.getDataFromTestDataSheet("SODBPaymentTermTaxPercentageColumnName")));
		logs.logging("INFO", "Advance Payment Term Tax Percentage is Matched With UI And DataBase");
	}

	/**
	 * This Method Is Used To get The Payment term Advance Percentages From Db
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void getPaymentTermAdvpercentagesInDB() {
		String ExpectedAdvMatPercentage = dbCommonFunctions.SOPaymentTermAdvPercentage(
				commonFunc.getDataFromTestDataSheet("AdvancePaymentTerm"),
				commonFunc.getDataFromTestDataSheet("SODBPaymentTermMaterialPercentageColumnName"));
		VariableHandler.setPaymentTermAdvMatPercentage(ExpectedAdvMatPercentage);
		logs.logging("INFO", "Expected Payment Term Advance Material Percentage From Payment Term Master Is : "
				+ ExpectedAdvMatPercentage);

		String ExpectedAdvTaxPercentage = dbCommonFunctions.SOPaymentTermAdvPercentage(
				commonFunc.getDataFromTestDataSheet("AdvancePaymentTerm"),
				commonFunc.getDataFromTestDataSheet("SODBPaymentTermTaxPercentageColumnName"));
		VariableHandler.setPaymentTermAdvTaxPercentage(ExpectedAdvTaxPercentage);
		logs.logging("INFO", "Expected Payment Term Advance Tax Percentage From Payment Term Master Is : "
				+ ExpectedAdvTaxPercentage);

	}

	/**
	 * This Method Is Used To Verify Expected Advance receipt With DataBase Values
	 * 
	 * @author Harikrishna -RMITS
	 * @param Unit
	 * @param CustomerID
	 */
	public void verifyExpectedAdvanceReceiptWithDataBaseValues(String Unit, String CustomerID) {

//		verifyExpectedAdvanceToBeReceivedAmount();

		verifyAssertionBothValues(
				dbCommonFunctions.FinanceAdvanceReceipt(VariableHandler.getAdvReceiptDocNumber(),
						commonFunc.getDataFromTestDataSheet("AdvReceiptDBDOCNumberColumnName")),
				VariableHandler.getAdvReceiptDocNumber());

		verifyAssertionBothValues(dbCommonFunctions.FinanceAdvanceReceipt(VariableHandler.getAdvReceiptDocNumber(),
				commonFunc.getDataFromTestDataSheet("AdvReceiptDBHeaderUnitColumnName")), Unit);

		verifyAssertionBothValues(
				dbCommonFunctions.FinanceAdvanceReceipt(VariableHandler.getAdvReceiptDocNumber(),
						commonFunc.getDataFromTestDataSheet("AdvReceiptDBHeaderOrderNumberColumnName")),
				VariableHandler.getSONumber());

		verifyAssertionBothValues(dbCommonFunctions.FinanceAdvanceReceipt(VariableHandler.getAdvReceiptDocNumber(),
				commonFunc.getDataFromTestDataSheet("AdvReceiptDBHeaderPartyIDColumnName")), CustomerID);

		verifyAssertionBothValues(
				dbCommonFunctions.FinanceAdvanceReceipt(VariableHandler.getAdvReceiptDocNumber(),
						commonFunc.getDataFromTestDataSheet("AdvReceiptDBHeaderPercentageColumnName")),
				VariableHandler.getPaymentTermAdvMatPercentage());

//		String ActualAdvReceiptAmountDB = dbCommonFunctions.FinanceAdvanceReceipt(
//				VariableHandler.getAdvReceiptDocNumber(),
//				commonFunc.getDataFromTestDataSheet("AdvReceiptDBHeaderAdvAmountColumnName"));

//		BigDecimal ActualAdvReceiptAmountDB1 = new BigDecimal(ActualAdvReceiptAmountDB);
//
//		String ActualAdvReceiptAmountDB2 = verifyRoundOffBigDecimalValueWithReturnString(ActualAdvReceiptAmountDB1);
//
//		verifyAssertionBothValues(ActualAdvReceiptAmountDB2, VariableHandler.getExpectedAdvToBeReceivedInSO());

		verifyAssertionBothValues(
				dbCommonFunctions.FinanceAdvanceReceipt(VariableHandler.getAdvReceiptDocNumber(),
						commonFunc.getDataFromTestDataSheet("AdvReceiptDBHeaderCurrencyColumnName")),
				VariableHandler.getHeaderCurrency());

//		verifyAssertionBothValues(
//				dbCommonFunctions.FinanceAdvanceReceipt(VariableHandler.getAdvReceiptDocNumber(),
//						commonFunc.getDataFromTestDataSheet("AdvReceiptDBHeaderOrderAmountColumnName")),
//				VariableHandler.getSOEXPTotalOrderAmount().stripTrailingZeros().toPlainString());

	}

	/**
	 * This Method Is Used To Verify The Creation Of Payment Receipt Receivables
	 * 
	 * @author Harikrishna -RMITS
	 * @param DocNumber
	 * @param BankName
	 * @param RefNumber
	 * @return boolean flag
	 */
	public boolean verifyCreationOfPaymentReceiptsReceivables(String DocNumber, String BankName, String RefNumber) {
		commonFunc.navigateToFinancialPaymentReceipt();
		clickPendingReceiveablesAdvance(DocNumber, BankName);
		verifyBankReceiptDocument(RefNumber);
		forwardForApprovalbankReceipts(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
					VariableHandler.getBankReceiptDocNumber(), webDB.getDataFromProperties("HodEmpId"),
					commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
		}
		return flag;
	}

	/**
	 * This Method Is Used To Verify Expected Received Advance Amount With DB Values
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void verifyExpectedReceivedAdvAmountWithDBValues() {
		String ActualAdvReceivedAmountDB = dbCommonFunctions.FinanceAdvanceReceipt(
				VariableHandler.getAdvReceiptDocNumber(),
				commonFunc.getDataFromTestDataSheet("AdvReceiptDBHeaderReceivedAmountColumnName"));

		BigDecimal ActualAdvReceivedAmountDB1 = new BigDecimal(ActualAdvReceivedAmountDB);

		String ActualAdvReceivedAmountDB2 = verifyRoundOffBigDecimalValueWithReturnString(ActualAdvReceivedAmountDB1);

		verifyAssertionBothValues(ActualAdvReceivedAmountDB2, VariableHandler.getExpectedAdvToBeReceivedInSO());
		logs.logging("INFO",
				"Expected Advance Received Amount Matched With DataBase Values In Advance Receipt Screen Against This Document Number :"
						+ VariableHandler.getAdvReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Verify Expected Advance To Be Received With DB Values
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void verifyExpectedAdvanceToBeReceivedAmount() {
		BigDecimal SOTotalAmount = VariableHandler.getSOEXPTotalOrderAmount();
		logs.logging("INFO", "Sales Order Total Amount Is :" + SOTotalAmount);

		BigDecimal AdvPercentage1 = new BigDecimal(VariableHandler.getPaymentTermAdvMatPercentage());
		logs.logging("INFO", "Advance Percentage Is :" + AdvPercentage1);

		BigDecimal ExpectedAdvToBeReceived = verifyAdvanceAmountCalculation(SOTotalAmount, AdvPercentage1);

		String ExpectedAdvToBeReceived1 = verifyRoundOffBigDecimalValueWithReturnString(ExpectedAdvToBeReceived);
		VariableHandler.setExpectedAdvToBeReceivedInSO(ExpectedAdvToBeReceived1);
		logs.logging("INFO",
				"Expected Advance Amount To Be Received Is :" + VariableHandler.getExpectedAdvToBeReceivedInSO());
	}

	/**
	 * This Method Is Used to Create The Bank Receipt Document From The Pending
	 * Screen
	 * 
	 * @author Harikrishna -RMITS
	 * @param AdvReceiptDocNo
	 * @param BankName
	 */
	public void clickPendingReceiveablesAdvance(String AdvReceiptDocNo, String BankName) {
		clickPendingDRDocs();
		flag = webDB.waitForElement(SalesOrderLocators.PendingReceivablesAdvance, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.PendingReceivablesAdvance, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.PendingReceivableFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Receivables Frame is Visible");
				webDB.switchToFrame(SalesOrderLocators.PendingReceivableFrame, ElementType.Xpath);
				flag = webDB.waitForElement(SalesOrderLocators.DocumentNumberLov, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.DocumentNumberLov, AdvReceiptDocNo);
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(SalesOrderLocators.PendingReceivableFrame, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Pending Receivables Frame is Visible");
						webDB.switchToFrame(SalesOrderLocators.PendingReceivableFrame, ElementType.Xpath);
						flag = webDB.waitForElement(SalesOrderLocators.PaymentReceiptSearchButton, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(SalesOrderLocators.PaymentReceiptSearchButton, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							flag = webDB.waitForElement(
									SalesOrderLocators.PendingPaymentReceiptCheckBox(AdvReceiptDocNo),
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "CheckBox Is Displayed");
								commonFunc.hardWait(3000);
								webDB.clickAnElement(SalesOrderLocators.PendingPaymentReceiptCheckBox(AdvReceiptDocNo),
										ElementType.Xpath);
								flag = webDB.waitForElement(SalesOrderLocators.PayementReceiptCreateButton,
										ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(SalesOrderLocators.PayementReceiptCreateButton,
											ElementType.Xpath);
									flag = webDB.waitForElement(SalesOrderLocators.PaymentsReceiptModeField,
											ElementType.Xpath);
									if (flag) {
//										webDB.pressTabKey();
										commonFunc.checkRefreshCellsDisappear();
										webDB.clickAnElement(SalesOrderLocators.PaymentsReceiptModeField,
												ElementType.Xpath);
										webDB.pressTabKey();
										commonFunc.searchAndSelectValueInFilters(
												SalesOrderLocators.BankReceiptsLovButton, BankName);
										flag = webDB.waitForElement(SalesOrderLocators.PendingReceivableFrame,
												ElementType.Xpath);
										commonFunc.checkRefreshCellsDisappear();
										if (flag) {
											logs.logging("INFO", "Pending Receivables Frame is Visible");
											webDB.switchToFrame(SalesOrderLocators.PendingReceivableFrame,
													ElementType.Xpath);
											flag = webDB.waitForElement(SalesOrderLocators.BankReceiptsOkButton,
													ElementType.Xpath);
											if (flag) {
												webDB.clickAnElement(SalesOrderLocators.BankReceiptsOkButton,
														ElementType.Xpath);
												webDB.switchToDefaultContent();
												flag = webDB.waitForElement(SalesOrderLocators.BankReceiptpostButton,
														ElementType.Xpath);
												if (flag) {
													logs.logging("INFO",
															"Navigated To Bank Receipt Transaction Screen");
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click the Pending DR Docs Button
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void clickPendingDRDocs() {
		flag = webDB.waitForElement(CommonUiLocators.PendingDrDocs, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(CommonUiLocators.PendingDrDocs, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.PendingReceivablesAdvance, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Pending Dr Docs Is Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method IS Used To Verify the Bank Receipt Document Screen
	 * 
	 * @author Harikrishnan -RMITS
	 * @param RefNumber
	 */
	public void verifyBankReceiptDocument(String RefNumber) {
		flag = webDB.waitForElement(SalesOrderLocators.BankReceiptDocumentNumber, ElementType.Xpath);
		if (flag) {
//			verifyExpectedAdvanceInProgressWithDataBaseValues();
//			verifyExpectedAdvAmtInprogressWithDBValues();
			String BankReceiptDocNumber = webDB.getTextFromElement(SalesOrderLocators.BankReceiptDocumentNumber,
					ElementType.Xpath);
			VariableHandler.setBankReceiptDocNumber(BankReceiptDocNumber);
			logs.logging("INFO", "Bank Receipt document Number Is :" + VariableHandler.getBankReceiptDocNumber());
			flag = webDB.waitForClickElement(SalesOrderLocators.BankReceiptsRefNumber, ElementType.Xpath);
			if (flag) {
				webDB.sendTextToAnElement(SalesOrderLocators.BankReceiptsRefNumber, RefNumber, ElementType.Xpath);
				bankreceiptfunc.checkARMControlBankNameRqrdFlag(commonFunc.getDataFromTestDataSheet("BankName"));
				bankreceiptfunc.checkARMControlReceivedFromRqrdFlag();
				commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.CFdescLOV, "OF");
				webDB.pressTabKey();
				flag = webDB.waitForElement(SalesOrderLocators.BankReceiptCreateJournalButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SalesOrderLocators.BankReceiptCreateJournalButton, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(SalesOrderLocators.BankReceiptpostButton, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SalesOrderLocators.BankReceiptpostButton, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
					}
				}
//				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To verify Expected Advance In progress Amount With DB
	 * Values
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void verifyExpectedAdvanceInProgressWithDataBaseValues() {
		verifyAssertionBothValues(
				dbCommonFunctions.SOAdvanceDetails(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("SODBAdvReceiptInProgressColumnName")),
				VariableHandler.getExpectedAdvToBeReceivedInSO());
		logs.logging("INFO",
				"After Creating The Bank Receipt Voucher Advance In progress Amount Is Matched Against This Sales Order Document"
						+ VariableHandler.getSONumber());
	}

	/**
	 * This Method Is Used to Forward the Doucmnet from One User To Another User
	 * 
	 * @author Harikrishna - RMITS
	 * @return boolean flag
	 */
	public boolean forwardForApprovalbankReceipts(String FrwdEmployee) {
		commonFunc.hardWait(1000);
		if (webDB.verifyIsElementDisplayed(SalesOrderLocators.WorkApprovalFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(SalesOrderLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			String GetTextFrwdPerson = webDB.getDomPropertyFromElement(SalesOrderLocators.ForwardEmpInputField,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			if (!GetTextFrwdPerson.equals(FrwdEmployee)) {
				commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.ForwardApprovalLov, FrwdEmployee);
				commonFunc.hardWait(1000);
				webDB.switchToFrame(SalesOrderLocators.WorkApprovalFrame, ElementType.Xpath);
				logs.logging("INFO", "Switched To WorkFlow Frame");
			}
			flag = webDB.waitForElement(SalesOrderLocators.AdvancereceiptsworkflowOkButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesOrderLocators.AdvancereceiptsworkflowOkButton, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				commonFunc.checkRefreshCellsDisappear();
			}
			webDB.switchToDefaultContent();
			commonFunc.hardWait(1000);
			assertTrue(flag);
		} else {
			flag = false;
			VariableHandler.setApprovalFlag(flag);
		}
		return flag;
	}

	/**
	 * This Method Is Used to Verify Expected Advance Amount In progress With DB
	 * values
	 * 
	 * @author Harikrishna RMITS
	 */
	public void verifyExpectedAdvAmtInprogressWithDBValues() {
		String ActualAdvReceiptInProgressAmountDB = dbCommonFunctions.FinanceAdvanceReceipt(
				VariableHandler.getAdvReceiptDocNumber(),
				commonFunc.getDataFromTestDataSheet("AdvReceiptDBHeaderInProgressAmountColumnName"));

		BigDecimal ActualAdvReceiptInProgressAmountDB1 = new BigDecimal(ActualAdvReceiptInProgressAmountDB);

		String ActualAdvReceiptInProgressAmountDB2 = verifyRoundOffBigDecimalValueWithReturnString(
				ActualAdvReceiptInProgressAmountDB1);

		verifyAssertionBothValues(ActualAdvReceiptInProgressAmountDB2,
				VariableHandler.getExpectedAdvToBeReceivedInSO());

		logs.logging("INFO",
				"Expected Advance Receipt In Progress Amount Matched With DataBase Values In Advance Receipt Screen Against This Document Number :"
						+ VariableHandler.getAdvReceiptDocNumber());
	}

	/**
	 * This Method Is Used To Verify The Advance Amount Calculation
	 * 
	 * @author Harikrishna -RMITS
	 * @param TotalAmount
	 * @param Percentage
	 * @return BigDecimal - DiscountAmount
	 */
	public BigDecimal verifyAdvanceAmountCalculation(BigDecimal TotalAmount, BigDecimal Percentage) {
		BigDecimal DiscountAmount = (TotalAmount.multiply(Percentage)).divide(new BigDecimal("100"));
		DiscountAmount = verifyRoundOffBigDecimalValueWithReturnBigDecimal(DiscountAmount);
		return DiscountAmount;
	}

	/**
	 * This Method Is Used To Verify Create SO - Domestic - Interstate Customer
	 * 
	 * @author Harikrishna -RMITS
	 * @return flag
	 * @throws IOException
	 */
	public boolean SoGeneral() throws IOException {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		salesOrderDBHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("Customer"),
				commonFunc.getDataFromTestDataSheet("CustomerDesc"), commonFunc.getDataFromTestDataSheet("SO_General"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();// TODO
		addSalesOrderLineThroughExcelUD(webDB.getDataFromProperties("SODomUserDefined"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SO_HSNCode"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Verify the Line Details In Sales Order
	 * 
	 * @author Harikrishna - RMITS
	 * @param Item
	 * @param Currency
	 * @param Quantity
	 * @param i
	 * @param TD
	 * @param SD
	 * @param CD
	 * @param SOType
	 * @param Promotional
	 * @param FilePath
	 * @param SheetName
	 * @param PriceClass
	 * @param Customer
	 */
	public void lineDetailVerification12(String Item, String Currency, String Quantity, int i, String TD, String SD,
			String CD, String SOType, String Promotional, String FilePath, String SheetName, String PriceClass,
			String Customer) {
		verifyLineSODetailsValidations122(Item, Currency, i);
//		verifyItemPriceBasis();
		if (!SOType.equalsIgnoreCase("SO - FREE SAMPLE") && !SOType.equalsIgnoreCase("SO - FREE REPLACEMENT")
				&& !SOType.equalsIgnoreCase("SO - DEMO / EXHN.")) {
			verifySOlinedetails12(Item, Quantity, TD, SD, CD, FilePath, SheetName, i, PriceClass, Customer);
		}
		navigateToLineDetailsPage23(Promotional);
//		verifySOLineDetailsPage21(Item, SOType, SheetName, i, FilePath, PriceClass);
	}

	/**
	 * This Method Is Used To Verify The Line Details With Excel And UI
	 * 
	 * @author Harikrishna -RMITS
	 * @param Item
	 * @param Currency
	 * @param i
	 */
	public void verifyLineSODetailsValidations122(String Item, String Currency, int i) {
		verifyGetTextValidation(SalesOrderLocators.ItemName(i - 3),
				itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.ITEM_ID));
		logs.logging("INFO", "Item ID is Verified Sucessfully");
		verifyGetTextValidation(SalesOrderLocators.ItemRev(i - 3),
				itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.ITEM_REV));
		logs.logging("INFO", "Item Rev is Verified Sucessfully");
		verifyGetTextValidation(SalesOrderLocators.ItemDescription(i - 3),
				itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.ITEM_DESC));
		logs.logging("INFO", "Item Description is Verified Sucessfully");
		verifyGetTextValidation(SalesOrderLocators.UOM(i - 3),
				itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.SALE_UOM));
		logs.logging("INFO", "Sales UOM For This Item : " + Item + " is Verified Sucessfully");
		verifyGetTextValidation(SalesOrderLocators.HSNSACCode(i - 3),
				itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.HSN_CODE));
		logs.logging("INFO", "HSN Code For This Item : " + Item + " is Verified Sucessfully");
		if (Currency.equals(commonFunc.getDataFromTestDataSheet("CurrencyInr"))) {
//			verifyGetTextValidation(SalesOrderLocators.TaxPercent(i - 3),
//					itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.TAX_PCT));
//			logs.logging("INFO", "Tax Percentage For This HSN Code : " + SOLineDetailsName.HSN_CODE
//					+ " Against This Item : " + Item + " is Verified Sucessfully");
			navigateToLineDetailsPage1(i - 3);
		} else {
			navigateToLineDetailsPage1(i - 3);
//			verifyDomPropertyValidation(SalesOrderLocators.TaxAmt,
//					commonFunc.getDataFromTestDataSheet("DoubleDecimal"));
//			logs.logging("INFO", "TAX Amount is Verified Sucessfully");
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify The Item Calculation with Line Amount
	 * 
	 * @author Harikrishna -RMITS
	 * @param Item
	 */
	public void verifyItemCalculationWithLineAmount12(String Item) {
		commonFunc.checkRefreshCellsDisappear();
		List<WebElement> OrderedQtyList = webDB.getDriver().findElements(By.xpath(SalesOrderLocators.QtyField()));
		for (int i = 1; i <= OrderedQtyList.size(); i++) {
			Item = webDB.getDriver().findElement(By.xpath(SalesOrderLocators.ItemName(i))).getText();
			logs.logging("INFO", "Get Text Item For Calculation Is: " + Item);
			String OrderedQty = webDB.getDriver().findElement(By.xpath(SalesOrderLocators.OrderedQty(i))).getText();
			logs.logging("INFO", "Get Text Ordered Quantity Is: " + OrderedQty);
			String UnitCost = webDB.getDriver().findElement(By.xpath(SalesOrderLocators.UnitCostTable(i))).getText();
			logs.logging("INFO", "Get Text Unit Cost Is: " + UnitCost);
			BigDecimal Quantityd = new BigDecimal(OrderedQty);
			BigDecimal Costd = new BigDecimal(UnitCost);
			BigDecimal LineAmountd = Quantityd.multiply(Costd);
			BigDecimal ExpectedLineAmountd = verifyRoundOffBigDecimalValueWithReturnBigDecimal(LineAmountd);
			VariableHandler.setSOLineAmount(ExpectedLineAmountd);
			VariableHandler.setSOTotalLineAmount(VariableHandler.getSOTotalLineAmount().add(ExpectedLineAmountd));
			String ExpectedLineAmount = verifyRoundOffBigDecimalValueWithReturnString(LineAmountd);
			logs.logging("INFO", "Expected Line Amount Is: " + ExpectedLineAmount);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify The Line Details 1 of the Sales Order
	 * 
	 * @author Harikrishna - RMITS
	 * @param Item
	 * @param Quantity
	 * @param TD
	 * @param SD
	 * @param CD
	 */
	public void verifySOlinedetails12(String Item, String Quantity, String TD, String SD, String CD, String FilePath,
			String SheetName, int i, String PriceClass, String Customer) {
//		checkSOLineDetails1And2DataBaseValuesWithUIValues();

		String PriceBasis = verifyItemPriceBasis();
		VariableHandler.setSOPriceBasis(PriceBasis);
//		flag = PriceBasis
//				.equals(itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.PRICE_BASIS_TYPE));
//		if (flag) {
		logs.logging("INFO",
				"Price Basis is " + VariableHandler.getSOPriceBasis() + " For This Item :" + Item + "is Verified");
//		} else {
//			logs.logging("INFO",
//					"Verification Failed Expected Value :"
//							+ itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.PRICE_BASIS_TYPE)
//							+ "Get Text Value :" + PriceBasis);
//		}
	}

	/**
	 * This Method Is Used To Verify the SO Line Details Amount In DB With Given
	 * Excel File
	 * 
	 * @author Harikrishna RMITS
	 * @param i1
	 * @param Item
	 * @param SheetName
	 */
	public void verifyExpectedLineItemCalculationWithDBValues(int i1, String Item, String SheetName) {
		commonFunc.checkRefreshCellsDisappear();
		String[][] SODiscountDetails = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Sales STD PRICE.xlsx";
		SODiscountDetails = excelUtils.readAllDataInExcel(Filepath, SheetName);

		for (int i = i1; i <= i1; i++) {
			String TradeAssesableValue = SODiscountDetails[i1][12];
			String TradeDiscountAmount = SODiscountDetails[i1][13];
			String SpecialAssesableValue = SODiscountDetails[i1][14];
			String SpecialDiscountAmount = SODiscountDetails[i1][15];
			String CashAssesableValue = SODiscountDetails[i1][16];
			String CashDiscountAmount = SODiscountDetails[i1][17];
			String DiscountAmount = SODiscountDetails[i1][18];
			String DiscountAmtPerQty = SODiscountDetails[i1][19];
			String GrossAmount = SODiscountDetails[i1][20];
			String CGST = SODiscountDetails[i1][23];
			String SGST = SODiscountDetails[i1][24];
			String IGST = SODiscountDetails[i1][25];
			String UTGST = SODiscountDetails[i1][26];
			String TaxAmount = SODiscountDetails[i1][29];
			String NetAmount = SODiscountDetails[i1][30];
			String CessPercentage = SODiscountDetails[i1][40];
			String CessAmount = SODiscountDetails[i1][41];

			verifyAssertionBothValues(
					dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
							commonFunc.getDataFromTestDataSheet("SODBTradeAssesableValueColumnName")),
					TradeAssesableValue);
			logs.logging("INFO", "Expected Trade Assesable Value Matched With DataBase Values For This Item " + Item
					+ " Against This Sales Order Document " + VariableHandler.getSONumber());

			verifyAssertionBothValues(
					dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
							commonFunc.getDataFromTestDataSheet("SODBTradeDiscountAmountColumnName")),
					TradeDiscountAmount);
			logs.logging("INFO", "Expected Trade Discount Amount Matched With DataBase Values For This Item " + Item
					+ " Against This Sales Order Document " + VariableHandler.getSONumber());

			verifyAssertionBothValues(
					dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
							commonFunc.getDataFromTestDataSheet("SODBSpecialAssesableValueColumnName")),
					SpecialAssesableValue);
			logs.logging("INFO", "Expected Special Assesable Value Matched With DataBase Values For This Item " + Item
					+ " Against This Sales Order Document " + VariableHandler.getSONumber());

			verifyAssertionBothValues(
					dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
							commonFunc.getDataFromTestDataSheet("SODBSpecialDiscountAmountColumnName")),
					SpecialDiscountAmount);
			logs.logging("INFO", "Expected Special Discount Amount Matched With DataBase Values For This Item " + Item
					+ " Against This Sales Order Document " + VariableHandler.getSONumber());

			verifyAssertionBothValues(
					dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
							commonFunc.getDataFromTestDataSheet("SODBCashAssesableValueColumnName")),
					CashAssesableValue);
			logs.logging("INFO", "Expected Cash Assesable Value Matched With DataBase Values For This Item " + Item
					+ " Against This Sales Order Document " + VariableHandler.getSONumber());

			verifyAssertionBothValues(
					dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
							commonFunc.getDataFromTestDataSheet("SODBCashDiscountAmountColumnName")),
					CashDiscountAmount);
			logs.logging("INFO", "Expected Cash Discount Amount Matched With DataBase Values For This Item " + Item
					+ " Against This Sales Order Document " + VariableHandler.getSONumber());

			verifyAssertionBothValues(dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("SODBTotalDiscountAmountColumnName")), DiscountAmount);
			logs.logging("INFO", "Expected Total Discount Amount Matched With DataBase Values For This Item " + Item
					+ " Against This Sales Order Document " + VariableHandler.getSONumber());

			verifyAssertionBothValues(
					dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
							commonFunc.getDataFromTestDataSheet("SODBDiscountAmountPerQtyColumnName")),
					DiscountAmtPerQty);
			logs.logging("INFO", "Expected Discount Amount Per Qty Is Matched With DataBase Values For This Item "
					+ Item + " Against This Sales Order Document " + VariableHandler.getSONumber());

			verifyAssertionBothValues(dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("SODBGrossAmountColumnName")), GrossAmount);
			logs.logging("INFO", "Expected Gross Amount Matched With DataBase Values For This Item " + Item
					+ " Against This Sales Order Document " + VariableHandler.getSONumber());

			verifyAssertionBothValues(dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("SODBCGSTColumnName")), CGST);
			logs.logging("INFO", "Expected CGST Amount Matched With DataBase Values For This Item " + Item
					+ " Against This Sales Order Document " + VariableHandler.getSONumber());

			verifyAssertionBothValues(dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("SODBSGSTColumnName")), SGST);
			logs.logging("INFO", "Expected SGST Amount Matched With DataBase Values For This Item " + Item
					+ " Against This Sales Order Document " + VariableHandler.getSONumber());

			verifyAssertionBothValues(dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("SODBIGSTColumnName")), IGST);
			logs.logging("INFO", "Expected IGST Amount Matched With DataBase Values For This Item " + Item
					+ " Against This Sales Order Document " + VariableHandler.getSONumber());

			verifyAssertionBothValues(dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("SODBUTGSTColumnName")), UTGST);
			logs.logging("INFO", "Expected UTGST Amount Matched With DataBase Values For This Item " + Item
					+ " Against This Sales Order Document " + VariableHandler.getSONumber());

			verifyAssertionBothValues(dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("SODBTaxAmountColumnName")), TaxAmount);
			logs.logging("INFO", "Expected Tax Amount Matched With DataBase Values For This Item " + Item
					+ " Against This Sales Order Document " + VariableHandler.getSONumber());

			verifyAssertionBothValues(dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("SODBNetAmountColumnName")), NetAmount);
			logs.logging("INFO", "Expected Net Amount Matched With DataBase Values For This Item " + Item
					+ " Against This Sales Order Document " + VariableHandler.getSONumber());

			verifyAssertionBothValues(dbCommonFunctions.SOLineDetails1(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("SODBCESSPercentageColumnName")), CessPercentage);
			logs.logging("INFO", "Expected CESS Percentage Matched With DataBase Values For This Item " + Item
					+ " Against This Sales Order Document " + VariableHandler.getSONumber());

			verifyAssertionBothValues(dbCommonFunctions.SOLineDetails1(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("SODBCESSAmountColumnName")), CessAmount);
			logs.logging("INFO", "Expected CESS Amount Matched With DataBase Values For This Item " + Item
					+ " Against This Sales Order Document " + VariableHandler.getSONumber());
		}
	}

	/**
	 * This Method Is Used To Write The Before Stock Qty In the Given Excel File
	 * 
	 * @author Harikrishna -RMITS
	 * @param i1
	 * @param ArrayList
	 * @param SheetName
	 * @param SOType
	 */
	public void StockQtyUpdateInExcel(int i1, ArrayList<String> ArrayList, String SheetName, String SOType) {
		String[][] SODiscountDetails = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Sales STD PRICE.xlsx";
		SODiscountDetails = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String ItemId = SODiscountDetails[i1][1];
			String WareHouse = SODiscountDetails[i1][35];
			if (!ItemId.equals("")) {
				if (!SOType.equalsIgnoreCase("SO - SERVICE")) {
					String BeforeStockQty = dbCommonFunctions.StockQuery1(WareHouse, ItemId,
							commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
					logs.logging("INFO", "Before Stock Qty For This Item " + ItemId + "Is : " + BeforeStockQty);

					String BeforeStockCost = dbCommonFunctions.StockQuery1(WareHouse, ItemId,
							commonFunc.getDataFromTestDataSheet("StockCostDBColumnName"));
					logs.logging("INFO", "Before Stock Cost For This Item " + ItemId + "Is : " + BeforeStockCost);
					excelUtils.writeDataInExcel(Filepath, SheetName, i, 32, BeforeStockQty);
					excelUtils.writeDataInExcel(Filepath, SheetName, i, 33, BeforeStockCost);
					ExcelStockQtyFormulaEvaluatorSI(i1, ItemId, ArrayList, SheetName);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used For After Sales Rtn The Document Expected Stk Qty
	 * 
	 * @author Vimal - RMITS
	 * @param SheetName
	 */
	public void verifyAfterExpectedReturnStockQtyWithDBValuesAfterInvoice(String SheetName) {
		String[][] SOItemDetails = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";
		SOItemDetails = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 4; i < SOItemDetails.length; i++) {
			String ItemId = SOItemDetails[i][1];
			String WareHouse = SOItemDetails[i][34];
			String AfterRtnStockQty = SOItemDetails[i][37];
			if (!ItemId.equals("")) {
				String AfterStockQty = dbCommonFunctions.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
				logs.logging("INFO",
						"After Invoiced Actual Stock Qty For This Item " + ItemId + "Is : " + AfterStockQty);
				verifyAssertionBothValues(AfterStockQty, AfterRtnStockQty);
				logs.logging("INFO", "Verified Sales Rtn Expected Stock Qty For This Item " + ItemId
						+ " With Database Values After Sales Rtn The Document");
			}
		}
	}

	/**
	 * This Method Used For Reading DataBase Value For Before Stock Quantity
	 * 
	 * @author Vimal - RMITS
	 * @param i1
	 * @param ArrayList
	 * @param SheetName
	 */
	public void StockQtyUpdateInExcelSI(int i1, ArrayList<String> ArrayList, String SheetName) {
		String[][] SODiscountDetails = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";
		SODiscountDetails = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String ItemId = SODiscountDetails[i1][1];
			String WareHouse = SODiscountDetails[i1][34];
			if (!ItemId.equals("")) {
				String BeforeStockQty = dbCommonFunctions.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
				logs.logging("INFO", "Before Stock Qty For This Item " + ItemId + "Is : " + BeforeStockQty);
//				double stockQtyValue = Double.parseDouble(BeforeStockQty);
//				DecimalFormat df = new DecimalFormat("0.00");
//				String formattedNumber = df.format(stockQtyValue);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 32, BeforeStockQty);
				ExcelStockQtyFormulaEvaluatorSI(i1, ItemId, ArrayList, SheetName);
			}
		}
	}

	/**
	 * This Method Is Used To verify The Expected Stock Qty With Excel Values After
	 * Invoice The Document
	 * 
	 * @author Harikrishna RMITS
	 */
	public void verifyExpectedStockQtyWithDBValuesAfterInvoice(String SheetName) {
		String[][] SOItemDetails = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";
		SOItemDetails = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 4; i < SOItemDetails.length; i++) {
			String ItemId = SOItemDetails[i][1];
			String WareHouse = SOItemDetails[i][34];
			if (!ItemId.equals("")) {
				String AfterStockQty = dbCommonFunctions.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
				logs.logging("INFO",
						"After Invoiced Actual Stock Qty For This Item " + ItemId + "Is : " + AfterStockQty);
				verifyAssertionBothValues(AfterStockQty, VariableHandler.getExpectedStockQty2().get(i - 4));
				logs.logging("INFO", "Verified Expected Stock Qty For This Item " + ItemId
						+ " With Database Values After Invoiced The Document");
			}
		}
	}

	/**
	 * This Method Is used To Verify the Sales order Line Details (2)
	 * 
	 * @author Harikrishna -RMITS
	 * @param Item
	 * @param SOType
	 * @param SheetName
	 * @param i1
	 * @param FilePath
	 * @param PriceClass
	 */
	public void verifySOLineDetailsPage21(String Item, String SOType, String SheetName, int i1, String FilePath,
			String PriceClass) {
		for (int i = i1; i <= i1; i++) {
			verifyDomPropertyValidation(SalesOrderLocators.CustItemID,
					itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.CUST_ITEM_ID));
			logs.logging("INFO", "Customer Item ID Verified Sucessfully");
			verifyDomPropertyValidation(SalesOrderLocators.CustIdDesc,
					itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.CUST_ITEM_DESC));
			logs.logging("INFO", "Customer Item Description Verified Sucessfully");
//			verifyDomPropertyValidation(SalesOrderLocators.WarehouseDesc,
//					itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.WAREHOUSE_DESC));
//			logs.logging("INFO", "Warehouse Description Verified Sucessfully");
			verifyDomPropertyValidation(SalesOrderLocators.SubClass,
					itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.SUB_CLASS));
			logs.logging("INFO", "SUBCLASS For This Item : " + Item + "is Verified");
			verifyDomPropertyValidation(SalesOrderLocators.Class,
					itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.CLASS));
			logs.logging("INFO", "CLASS For This Item : " + Item + "is Verified");
			if (!SOType.equalsIgnoreCase("SO - FREE SAMPLE") && !SOType.equalsIgnoreCase("SO - FREE REPLACEMENT")) {
				verifyDomPropertyValidation(SalesOrderLocators.Promotional,
						itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.PROMOTIONAL));
				logs.logging("INFO", "Sales Promotional Type For This Item : " + Item + "is Verified");
			} else {
				verifyGetTextValidation(SalesOrderLocators.Promotional1,
						itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.PROMOTIONAL));
				logs.logging("INFO", "Sales Promotional Type For This Item : " + Item + "is Verified");
			}
			verifyDomPropertyValidation(SalesOrderLocators.PriceClass,
					itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.PRICECLASS));
			logs.logging("INFO", "Price Class Type For This Item : " + Item + "is Verified");
			verifyDomPropertyValidation(SalesOrderLocators.StockUOM,
					itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.STOCK_UOM));
			logs.logging("INFO", "Stock UOM For This Item : " + Item + "is Verified");
			verifyDomPropertyValidation(SalesOrderLocators.ConvFact,
					itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.CONV_FACT));
			logs.logging("INFO", "Conversion Factor For This Item : " + Item + "is Verified");
			verifyGetTextValidation(SalesOrderLocators.ToleranceType,
					itemDetailHandler.getSOLineDetailsforGivenItem(Item, SOLineDetailsName.TOLERANCE_TYPE));
			logs.logging("INFO", "Sales Tolerance Type For This Item : " + Item + "is Verified");
			if (PriceClass.equalsIgnoreCase("SPARE")) {
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.PriceClassLov, PriceClass);
				webDB.switchToFrame(SalesOrderLocators.LineDetailsFrame, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				String DBUnitCost = dbCommonFunctions.CurrentRateContractQuery(
						commonFunc.getDataFromTestDataSheet("Customer"), Item, PriceClass,
						commonFunc.getDataFromTestDataSheet("SODBRateContractUnitPriceColumnName"));
				String DBPercentage = dbCommonFunctions.CurrentRateContractQuery(
						commonFunc.getDataFromTestDataSheet("Customer"), Item, PriceClass,
						commonFunc.getDataFromTestDataSheet("SODBRateContractDiscountPerColumnName"));
				excelUtils.writeDataInExcel(FilePath, SheetName, i, 7, DBUnitCost);
				excelUtils.writeDataInExcel(FilePath, SheetName, i, 9, DBPercentage);
			}
		}
	}

	/**
	 * This Method Is Used To navigate To the Line Details Page (2)
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void navigateToLineDetailsPage23(String Promotional) {
		flag = webDB.waitForElement(SalesOrderLocators.ItemDetails, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.ItemDetails, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.ItemTypeFieldName, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", " Sucessfully Clicked The Item Details (+) Button ");
				if (Promotional.equalsIgnoreCase("Promotional")) {
					webDB.clickAnElement(SalesOrderLocators.PromotionalLov, ElementType.Xpath);
					webDB.switchToDefaultContent();
					flag = webDB.waitForElement(CommonUiLocators.searchBar, ElementType.Xpath);
					if (flag) {
						webDB.sendTextToAnElement(CommonUiLocators.searchBar, Promotional, ElementType.Xpath);
						flag = webDB.waitForElement(SalesOrderLocators.LovPopupHighlight, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(SalesOrderLocators.LovPopupHighlight, ElementType.Xpath);
						}
					}
					webDB.switchToFrame(SalesOrderLocators.LineDetailsFrame, ElementType.Xpath);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify create Free Sample type of Sales Order
	 * 
	 * @author Harikrishna -RMITS
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean SoFreeSample() throws IOException {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_FREE_SAMPLE"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		salesOrderDBHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("Customer"),
				commonFunc.getDataFromTestDataSheet("CustomerDesc"),
				commonFunc.getDataFromTestDataSheet("SO_FREE_SAMPLE"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();// TODO
		addSalesOrderLineThroughExcelUD(webDB.getDataFromProperties("SOFreeSample"),
				commonFunc.getDataFromTestDataSheet("SO_FREE_SAMPLE"),
				commonFunc.getDataFromTestDataSheet("SO_HSNCode"), commonFunc.getDataFromTestDataSheet("Customer"));
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Verify create Free Replacement type of Sales Order
	 * 
	 * @author Harikrishna -RMITS
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean SoFreeReplacement() throws IOException {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("SO_FREE_REPLACEMENT"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		salesOrderDBHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("Customer"),
				commonFunc.getDataFromTestDataSheet("CustomerDesc"),
				commonFunc.getDataFromTestDataSheet("SO_FREE_REPLACEMENT"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();// TODO
		addSalesOrderLineThroughExcelUD(webDB.getDataFromProperties("SOFreeReplacement"),
				commonFunc.getDataFromTestDataSheet("SO_FREE_REPLACEMENT"),
				commonFunc.getDataFromTestDataSheet("SO_HSNCode"), commonFunc.getDataFromTestDataSheet("Customer"));
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Verify create Scrap type of Sales Order
	 * 
	 * @author Harikrishna -RMITS
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean SoScrap() throws IOException {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_SCRAP"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		salesOrderDBHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("Customer"),
				commonFunc.getDataFromTestDataSheet("CustomerDesc"), commonFunc.getDataFromTestDataSheet("SO_SCRAP"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();// TODO
		addSalesOrderLineThroughExcelUD(webDB.getDataFromProperties("SOScrap"),
				commonFunc.getDataFromTestDataSheet("SO_SCRAP"), commonFunc.getDataFromTestDataSheet("SO_HSNCode"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Verify create Service type of Sales Order
	 * 
	 * @author Harikrishna -RMITS
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean SoService() throws IOException {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_SERVICE"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		salesOrderDBHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("Customer"),
				commonFunc.getDataFromTestDataSheet("CustomerDesc"), commonFunc.getDataFromTestDataSheet("SO_SERVICE"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();// TODO
		addSalesOrderLineThroughExcelUD(webDB.getDataFromProperties("SOService"),
				commonFunc.getDataFromTestDataSheet("SO_SERVICE"), commonFunc.getDataFromTestDataSheet("SO_HSNCode"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Verify Create SO with Promotional Items
	 * 
	 * @author Harikrishna -RMITS
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean SoPromotional() throws IOException {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		salesOrderDBHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("Customer"),
				commonFunc.getDataFromTestDataSheet("CustomerDesc"), commonFunc.getDataFromTestDataSheet("SO_General"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();// TODO
		addSalesOrderLineThroughExcelUD(webDB.getDataFromProperties("SOPromotional"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SO_HSNCode"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Verify Create SO - Domestic - Local Customer
	 * 
	 * @author Harikrishna -RMITS
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean SoGeneralLocalCustomer() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("SO_Local_Customer_Desc"));
		salesOrderDBHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("SO_Local_Customer"),
				commonFunc.getDataFromTestDataSheet("SO_Local_Customer_Desc"),
				commonFunc.getDataFromTestDataSheet("SO_General"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();// TODO
		addSalesOrderLineThroughExcelUD(webDB.getDataFromProperties("SOLocal"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SO_HSNCode"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Verify Create SO - Domestic - SEZ Customer
	 * 
	 * @author Harikrishna -RMITS
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean SoGeneralSEZCustomer() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("SO_SEZ_CUSTOMER_DESC"));
		salesOrderDBHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_SEZ_CUSTOMER"),
				commonFunc.getDataFromTestDataSheet("SO_SEZ_CUSTOMER_DESC"),
				commonFunc.getDataFromTestDataSheet("SO_General"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();// TODO
		addSalesOrderLineThroughExcelUD(webDB.getDataFromProperties("SOSEZ"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SO_HSNCode"),
				commonFunc.getDataFromTestDataSheet("SO_SEZ_CUSTOMER"));
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Verify Demo Exhibition Type Of Sales Order
	 * 
	 * @author Harikrishna -RMITS
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean SoDemoExhibition() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("SO_Demo_Exhibition"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		salesOrderDBHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("Customer"),
				commonFunc.getDataFromTestDataSheet("CustomerDesc"),
				commonFunc.getDataFromTestDataSheet("SO_Demo_Exhibition"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();// TODO
		addSalesOrderLineThroughExcelUD(webDB.getDataFromProperties("SODemoExhibition"),
				commonFunc.getDataFromTestDataSheet("SO_Demo_Exhibition"),
				commonFunc.getDataFromTestDataSheet("SO_HSNCode"), commonFunc.getDataFromTestDataSheet("Customer"));
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Verify Create SO - Domestic - CESS Customer
	 * 
	 * @author Harikrishna -RMITS
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean SoCESS() throws IOException {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		salesOrderDBHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("Customer"),
				commonFunc.getDataFromTestDataSheet("CustomerDesc"), commonFunc.getDataFromTestDataSheet("SO_General"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();// TODO
		addSalesOrderLineThroughExcelUD(webDB.getDataFromProperties("SOCESS"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("CESS_HSN_CODE"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Verify The Sales Order With Charges
	 * 
	 * @author Harikrishna RMITS
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean SoCharges() throws IOException {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		salesOrderDBHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("Customer"),
				commonFunc.getDataFromTestDataSheet("CustomerDesc"), commonFunc.getDataFromTestDataSheet("SO_General"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();// TODO
		addSalesOrderLineThroughExcelUD(webDB.getDataFromProperties("SOCharges"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SO_HSNCode"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		addChargesInSalesOrder(webDB.getDataFromProperties("SOCharges"));
		clickIncludechargesButton();
		verifyExpectedTotalValueWithCharges(webDB.getDataFromProperties("SOCharges"));
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
//			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used to Verify The Charge Item Calculation With DB Values
	 * 
	 * @author Harikrishna -RMITS
	 * @param i1
	 * @param Item
	 * @param SheetName
	 */
	public void verifyExpectedChargesLineItemCalculationWithDBValues(int i1, String Item, String SheetName) {
		commonFunc.checkRefreshCellsDisappear();
		String[][] SOChargesDetails = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Sales STD PRICE.xlsx";
		SOChargesDetails = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String ChargeAmount = SOChargesDetails[i1][8];
			String TaxRate = SOChargesDetails[i1][10];
			String CGST = SOChargesDetails[i1][11];
			String SGST = SOChargesDetails[i1][12];
			String IGST = SOChargesDetails[i1][13];

			verifyAssertionBothValues(dbCommonFunctions.SOChargesDetails(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("SODBChargeAmountColumnName")), ChargeAmount);
			logs.logging("INFO", "Charge Amount For This Charge Item " + Item + " Is Verified With DataBase Values");

			verifyAssertionBothValues(dbCommonFunctions.SOChargesDetails(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("SODBChargeCGSTColumnName")), CGST);
			logs.logging("INFO", "TaxAmount CGST For This Charge Item " + Item + " Is Verified With DataBase Values");

			verifyAssertionBothValues(dbCommonFunctions.SOChargesDetails(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("SODBChargeSGSTColumnName")), SGST);
			logs.logging("INFO", "TaxAmount SGST For This Charge Item " + Item + " Is Verified With DataBase Values");

			verifyAssertionBothValues(dbCommonFunctions.SOChargesDetails(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("SODBChargeIGSTColumnName")), IGST);
			logs.logging("INFO", "TaxAmount IGST For This Charge Item " + Item + " Is Verified With DataBase Values");
		}
	}

	/**
	 * This Method Is Used To Verify the Document Status As EntryCompleted With DB
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void verifySalesOrderDocumentStatusEntryCompletedWithDB() {
		String SODocumentStatus = dbCommonFunctions.SOHeaderDetails(VariableHandler.getSONumber(),
				commonFunc.getDataFromTestDataSheet("SODBHeaderDocumentStatusColumnName"));

		verifyAssertionBothValues(SODocumentStatus, commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"));
		logs.logging("INFO", "Verified Sales Order Document Status Is " + SODocumentStatus
				+ " Against This Sales Order Number " + VariableHandler.getSONumber());
	}

	/**
	 * This Method Is Used To Verify the Document Status As Draft With DB
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void verifySalesOrderDocumentStatusDraftWithDB() {
		String SODocumentStatus = dbCommonFunctions.SOHeaderDetails(VariableHandler.getSONumber(),
				commonFunc.getDataFromTestDataSheet("SODBHeaderDocumentStatusColumnName"));
		verifyAssertionBothValues(SODocumentStatus, commonFunc.getDataFromTestDataSheet("Draft"));
		logs.logging("INFO", "Verified Sales Order Document Status Is " + SODocumentStatus
				+ " Against This Sales Order Number " + VariableHandler.getSONumber());
	}

	/**
	 * This method Is Used to Click The Include Charges Button
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickIncludechargesButton() {
		flag = webDB.waitForElement(SalesOrderLocators.IncludeChargesButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.IncludeChargesButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.isElementDisplayed(SalesOrderLocators.NoteAlertCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElement(SalesOrderLocators.NoteAlertCloseIcon, ElementType.Xpath);
				logs.logging("INFO", "Include Charges Button Is Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is used To Add the Charges in Sales Order Screen
	 * 
	 * @author Harikrishna -RMITS
	 * @param ChargeItemBasis
	 * @param ChargeItem
	 * @param ChargeItemPercentage
	 * @param ChargeItemAmount
	 */
	public void addChargesInSalesOrder(String SheetName) {
		String[][] SOMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Sales STD PRICE.xlsx";
		SOMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 10; i < SOMultipleline.length; i++) {
			String CHARGEITEM_ID = SOMultipleline[i][3];
			String CHARGE_ITEMBASIS = SOMultipleline[i][6];
			String Charges_Percentage = SOMultipleline[i][7];
			String Charges_Amount = SOMultipleline[i][8];
			if (!CHARGEITEM_ID.equals("")) {
				navigateToChargesTab();
				addChargeItemInSo(CHARGEITEM_ID, CHARGE_ITEMBASIS);
				addChargeItemPercentage(Charges_Percentage, Charges_Amount);
				clickChargeItemSaveButton();
				verifyExpectedChargesLineItemCalculationWithDBValues(i, CHARGEITEM_ID, SheetName);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method is used To Navigate Charges Tab in Sales Order Screen
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void navigateToChargesTab() {
		flag = webDB.waitForElement(SalesOrderLocators.ChargesTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.ChargesTab, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.ChargesAddButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated To Charges Tab");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Add button In the Charges Tab And Choose the
	 * Charge Item
	 * 
	 * @author Harikrishna - RMITS
	 * @param ChargeItem
	 * @param ChargeItemBasis
	 */
	public void addChargeItemInSo(String ChargeItem, String ChargeItemBasis) {
		// TODO
		flag = webDB.waitForElement(SalesOrderLocators.ChargesAddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.ChargesAddButton, ElementType.Xpath);
			chooseChargeItemBasis(ChargeItemBasis);
			flag = webDB.waitForElement(SalesOrderLocators.ChargeItemField, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Charge Item Add Button Is Clicked");
				webDB.clickAnElement(SalesOrderLocators.ChargeItemField, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.ChargeItemLov, ChargeItem);
				flag = webDB.waitForElement(SalesOrderLocators.ChargeItemInputField, ElementType.Xpath);
				if (flag) {
					verifyDomPropertyValidation(SalesOrderLocators.ChargeItemInputField, ChargeItem);
					webDB.pressTabKey();
					commonFunc.checkRefreshCellsDisappear();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Choose the Charge Item Basis (& or Value) Using Drop
	 * Down Function
	 * 
	 * @author Harikrishna - RMITS
	 * @param Value
	 */
	public void chooseChargeItemBasis(String Value) {
		flag = webDB.waitForElement(SalesOrderLocators.ChargeBasisField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.ChargeBasisField, ElementType.Xpath);
			webDB.selectDropDownByText(SalesOrderLocators.ChargeBasisDropDown, Value, ElementType.Xpath);
			logs.logging("INFO", "Charge Item Basis Is Choosen Sucessfully");
			webDB.pressTabKey();
			commonFunc.checkRefreshCellsDisappear();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Enter the Value in Both Percentage And Value Field
	 * Based On the Charge Item Basis (% or Value)
	 * 
	 * @author Harikrishna -RMITS
	 * @param Percentage
	 * @param Amount
	 */
	public void addChargeItemPercentage(String Percentage, String Amount) {
		String ChargeBasis = getTextChargeItemBais();
		if (ChargeBasis.equals("%")) {
			commonFunc.verifyAddTableSpecs(SalesOrderLocators.Chargepercentagefield,
					SalesOrderLocators.ChargePercentageInputField, Percentage);
			webDB.pressTabKey();
			commonFunc.checkRefreshCellsDisappear();
		} else {
			commonFunc.verifyAddTableSpecs(SalesOrderLocators.ChargeValueField,
					SalesOrderLocators.ChargeValueInputField, Amount);
			webDB.pressTabKey();
			commonFunc.checkRefreshCellsDisappear();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click the Charges Tab Line Save Button
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void clickChargeItemSaveButton() {
		flag = webDB.waitForElement(SalesOrderLocators.ChargeSavebutton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.ChargeSavebutton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.ChargeItemLineDeletebutton, ElementType.CssSelector);
			if (flag) {
				logs.logging("INFO", "Charge Item Added And Saved Sucessfully");
				commonFunc.checkRefreshCellsDisappear();
			}
		}
		assertTrue(flag);
	}

	/**
	 * This method Is Used To Get the Text Of the Charge Item Basis
	 * 
	 * @author Harikrishna - RMITS
	 * @return String - ChargeItemBasis
	 */
	public String getTextChargeItemBais() {
		String ChargeItemBasis = webDB.getTextFromElement(SalesOrderLocators.ChargeBasisField, ElementType.Xpath);
		logs.logging("INFO", "Get Text Is :" + ChargeItemBasis);
		return ChargeItemBasis;
	}

	/**
	 * This Method Is Used To Navigate To the Line Details Of the Sales Order Page 1
	 * 
	 * @author Harikrishna -RMITS
	 * @param i
	 */
	public void navigateToLineDetailsPage1(int i) {
//		webDB.moveToElement(SalesOrderLocators.LineDetails(i), ElementType.Xpath);
		flag = webDB.waitForElement(SalesOrderLocators.LineDetails(i), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.LineDetails(i), ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.DetailsButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesOrderLocators.DetailsButton, ElementType.Xpath);
			}
		}
		flag = webDB.waitForElement(SalesOrderLocators.LinewisedetailsHeader, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Navigated to Line wise Details");
			webDB.switchToFrame(SalesOrderLocators.LineDetailsFrame, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used to Verify the CESS Amount From DB
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void checkSOLineDetails1And2DataBaseValuesWithUIValues() {
		verifyAssertionDomPropertyValidation(SalesOrderLocators.CESSAmt, dbCommonFunctions.SOLineDetails1(
				VariableHandler.getSONumber(), commonFunc.getDataFromTestDataSheet("SODBCessAmtColumnName")));
	}

	/**
	 * This Method Is used To get The Stock Qty From The Formula Cell
	 * 
	 * @author Harikrishna - RMITS
	 * @param i
	 * @param Item
	 * @param ArrayList
	 * @param SheetName
	 */
	public void ExcelStockQtyFormulaEvaluatorSI(int i, String Item, ArrayList<String> ArrayList, String SheetName) {
		try {
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
					+ "DBSales.xlsx";
			FileInputStream fis = new FileInputStream(new File(Filepath));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(SheetName);
			FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
			Row row = sheet.getRow(i); // Row 0 (first row)
			Cell cell = row.getCell(33); // Column 0 (first column, A1)
			if (cell.getCellType() == CellType.FORMULA) {
				CellValue cellValue = formulaEvaluator.evaluate(cell);
				if (cellValue.getCellType() == CellType.NUMERIC) {
					double cellNumericValue = cellValue.getNumberValue();
					if (cellNumericValue == Math.floor(cellNumericValue)) {
						// It's a whole number
						System.out.println("Formula result: " + (int) cellNumericValue);
						int ExpectedStockQtyInt = (int) cellNumericValue;
						String ExpectedStockQtyAsString = String.valueOf(ExpectedStockQtyInt);
						VariableHandler.setExpectedStockQty(ExpectedStockQtyAsString);
						logs.logging("INFO",
								"Expected Stock Qty After Posting the Sales Invoice Document For This Item " + Item
										+ " Is : " + VariableHandler.getExpectedStockQty());
						ArrayList.add(VariableHandler.getExpectedStockQty());
					} else {
						System.out.println("Formula result: " + cellNumericValue);
						String ExpectedStockQtyAsString = String.valueOf(cellNumericValue);
						VariableHandler.setExpectedStockQty(ExpectedStockQtyAsString);
						logs.logging("INFO",
								"Expected Stock Qty After Posting the Sales Invoice Document For This Item " + Item
										+ " Is : " + VariableHandler.getExpectedStockQty());
						ArrayList.add(VariableHandler.getExpectedStockQty());
					}
				}
			} else {
				System.out.println("Cell does not contain a formula.");
			}
			workbook.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(flag);
		}
	}

	/**
	 * This Method Is Used to verify The Sales Order Line Details Cancel
	 * 
	 * @author Harikrishna -RMITS
	 * @return boolean flag
	 */
	public void verifyCancelSalesOrderLines() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		salesOrderDBHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("Customer"),
				commonFunc.getDataFromTestDataSheet("CustomerDesc"), commonFunc.getDataFromTestDataSheet("SO_General"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();
		addSalesOrderLineThroughExcelUD(webDB.getDataFromProperties("SOLineCancel"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SO_HSNCode"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		verifySalesOrderLinesStatusDraftWithDB();
		flag = webDB.waitForElement(SalesOrderLocators.LineDetails(1), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.LineDetails(1), ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.CancelLineButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesOrderLocators.CancelLineButton, ElementType.Xpath);
				flag = webDB.waitForElementUntilGivenTime(SalesOrderLocators.CancelAlert, ElementType.Xpath, 30);
				if (flag) {
					webDB.clickAnElement(SalesOrderLocators.CancelAlert, ElementType.Xpath);
					flag = webDB.waitForElementUntilGivenTime(SalesOrderLocators.NoteAlertMessage, ElementType.Xpath,
							45);
					if (flag) {
						logs.logging("INFO", "Note Alert Is Displayed And Sales Order Line Document Is Cancelled.");
						if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
							webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
						}
					}
				}
			}
		}
		verifySalesOrderLinesStatusCancelledWithDB(webDB.getDataFromProperties("SOLineCancel"));
//		clickLineWiseDeleteButton();
	}

	/**
	 * This Method Is Used To Click the line Wise Delete button In Sales order
	 * Screen
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void clickLineWiseDeleteButton() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(SalesOrderLocators.LineWiseDeleteButton, ElementType.CssSelector);
		if (flag) {
			webDB.moveToElement(SalesOrderLocators.LineWiseDeleteButton, ElementType.CssSelector);
			webDB.clickAnElement(SalesOrderLocators.LineWiseDeleteButton, ElementType.CssSelector);
			flag = webDB.waitForElement(SalesOrderLocators.NoteAlertMessage, ElementType.Xpath);
			if (flag) {
				String AlertMsg = webDB.getTextFromElement(SalesOrderLocators.NoteAlertMessage, ElementType.Xpath);
				logs.logging("INFO", "Get Text Alert Message " + AlertMsg);
				flag = AlertMsg.equalsIgnoreCase("Line Cancelled.");
				if (flag) {
					logs.logging("INFO", "Sales Order Line Details Cancelled");
				} else {
					logs.logging("INFO", "Failed To Cancel Sales Order Line Details");
				}
				verifySalesOrderLinesStatusCancelledWithDB(webDB.getDataFromProperties("SOLineCancel"));
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The SO Lines Through Excel(User Defined)
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param SOType
	 * @param HSNCODE
	 * @param Customer
	 */
	public void addSalesOrderLineThroughExcelUD(String SheetName, String SOType, String HSNCODE, String Customer) {
		ArrayList<String> ArrayList = new ArrayList<String>();
		String[][] SOMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Sales STD PRICE.xlsx";
		SOMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 4; i < SOMultipleline.length; i++) {
			String ITEM_ID = SOMultipleline[i][1];
			String QUANTITY = SOMultipleline[i][6];
			String UNIT_COST = SOMultipleline[i][7];
			String Trade_Disc = SOMultipleline[i][9];
			String Spl_Disc = SOMultipleline[i][10];
			String Cash_Disc = SOMultipleline[i][11];
			String Promotional = SOMultipleline[i][39];
			String PriceClass = SOMultipleline[i][42];
//			String ShipFromLocID = SOMultipleline[i][43];
			if (!ITEM_ID.equals("")) {
				addItemsInSO(ITEM_ID);
				addQuantityAndUnitCost(QUANTITY, UNIT_COST);
				chooseHsnCodeAndClickLineWiseSaveButton(HSNCODE);
				clickLineWiseSaveButton();
//				webDB.waitForElement(SalesOrderLocators.ShipFromLocIDField, ElementType.Xpath);
//				webDB.doubleClickAnElement(SalesOrderLocators.ShipFromLocIDField, ElementType.Xpath);
//				String GetShipFromLoc = webDB.getTextFromElement(SalesOrderLocators.ShipFromLocIDField,
//						ElementType.Xpath);
//				System.out.println(GetShipFromLoc);
//				if (!ShipFromLocID.equalsIgnoreCase(GetShipFromLoc)) {
//					commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.ShipFromLocLovButton, ShipFromLocID);
//					clickLineWiseSaveButton();
//				}
				verifyItemCalculationWithLineAmount();
				if (SheetName.equals(webDB.getDataFromProperties("SOShipSet"))) {
					flag = webDB.waitForElement(SalesOrderLocators.LineDetails(i - 3), ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SalesOrderLocators.LineDetails(i - 3), ElementType.Xpath);
						flag = webDB.waitForElement(SalesOrderLocators.ShipsetItemButton, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(SalesOrderLocators.ShipsetItemButton, ElementType.Xpath);
							flag = webDB.waitForElement(SalesOrderLocators.ShipsetFrameName, ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", " Sucessfully Clicked The ShipSet Item Button ");
							}
						}
					}
					VerifyShipsetDetails(ITEM_ID);
				}
				lineDetailVerification12(ITEM_ID, getTextCurrencyInHeaderAfterSaveTheLine(), QUANTITY, i, Trade_Disc,
						Spl_Disc, Cash_Disc, SOType, Promotional, Filepath, SheetName, PriceClass, Customer);
				clickLineWiseDetailsSaveButton();
				if (!SOType.equalsIgnoreCase("SO - FREE SAMPLE") && !SOType.equalsIgnoreCase("SO - FREE REPLACEMENT")
						&& !SOType.equalsIgnoreCase("SO - DEMO / EXHN.")
						&& !Promotional.equalsIgnoreCase("Promotional")) {
					enterDiscount(Trade_Disc, Spl_Disc, Cash_Disc, Customer, ITEM_ID, PriceClass, Filepath, SheetName,
							i);
					verifyExpectedLineItemCalculationWithDBValues(i, ITEM_ID, SheetName);
				}
			}
		}
		verifySOTotalAmountWithExcelAndDB(SheetName);
		VariableHandler.setExpectedStockQty2(ArrayList);

	}

	/**
	 * This Method Is Used To Add The SO Lines Through Excel(Other Than User
	 * Defined)
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param SOType
	 * @param HSNCODE
	 * @param Customer
	 */
	public void addSalesOrderLineThroughExcelOtherThanUD(String SheetName, String SOType, String HSNCODE,
			String Customer) {
		ArrayList<String> ArrayList = new ArrayList<String>();
		String[][] SOMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Sales STD PRICE.xlsx";
		SOMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 4; i < SOMultipleline.length; i++) {
			String ITEM_ID = SOMultipleline[i][1];
			String QUANTITY = SOMultipleline[i][6];
			String Trade_Disc = SOMultipleline[i][9];
			String Spl_Disc = SOMultipleline[i][10];
			String Cash_Disc = SOMultipleline[i][11];
			String Promotional = SOMultipleline[i][39];
			String PriceClass = SOMultipleline[i][42];
			if (!ITEM_ID.equals("")) {
				addItemsInSO(ITEM_ID);
				addLineItemQty(QUANTITY);
				chooseHsnCodeAndClickLineWiseSaveButton(HSNCODE);
				clickLineWiseSaveButton();
				writeUnitPriceInExcel(Filepath, SheetName, i, ITEM_ID);
				verifyItemCalculationWithLineAmount();
//				if (webDB.verifyIsElementDisplayed(SalesOrderLocators.ShipSetDetails(i - 3), ElementType.Xpath)) {
//					commonFunc.checkRefreshCellsDisappear();
//					webDB.clickAnElement(SalesOrderLocators.ShipSetDetails(i - 3), ElementType.Xpath);
//					VerifyShipsetDetails(ITEM_ID);
//				}
				lineDetailVerification12(ITEM_ID, getTextCurrencyInHeaderAfterSaveTheLine(), QUANTITY, i, Trade_Disc,
						Spl_Disc, Cash_Disc, SOType, Promotional, Filepath, SheetName, PriceClass, Customer);
				clickLineWiseDetailsSaveButton();
				enterDiscount(Trade_Disc, Spl_Disc, Cash_Disc, Customer, ITEM_ID, PriceClass, Filepath, SheetName, i);
				verifyExpectedLineItemCalculationWithDBValues(i, ITEM_ID, SheetName);
			}
		}
		verifySOTotalAmountWithExcelAndDB(SheetName);
		VariableHandler.setExpectedStockQty2(ArrayList);
	}

	/**
	 * This Method Is Used To Verify The ShipSetDetails From Item Master
	 * 
	 * @author Harikrishna -RMITS
	 * @param Item
	 */
	public void VerifyShipsetDetails(String Item) {
		webDB.switchToFrame(SalesOrderLocators.ShipSetFrame, ElementType.Xpath);
		logs.logging("INFO", "Switched To ShipSet Item Frame");
		commonFunc.checkRefreshCellsDisappear();
		List<WebElement> ShipSetUOM = webDB.getDriver().findElements(By.xpath(SalesOrderLocators.ShipSetUOM));
		for (int i = 1; i <= ShipSetUOM.size(); i++) {
			String ShipSetItem = webDB.getDriver().findElement(By.xpath(SalesOrderLocators.ShipSetItemID(i))).getText();
			logs.logging("INFO", "Get Text ShipSet Item Is: " + ShipSetItem);
			String ShipSetItemUOM = webDB.getDriver().findElement(By.xpath(SalesOrderLocators.ShipSetItemUOM(i)))
					.getText();
			logs.logging("INFO", "Get Text ShipSet Item UOM Is: " + ShipSetItemUOM);
			String ShipSetQty = webDB.getDriver().findElement(By.xpath(SalesOrderLocators.ShipSetItemQty(i))).getText();
			logs.logging("INFO", "Get Text ShipSet Item Qty Is: " + ShipSetQty);
			String SeqNo = String.valueOf(i);
			verifyAssertionBothValues(dbCommonFunctions.ItemMasterShipSetDetails(Item, SeqNo,
					commonFunc.getDataFromTestDataSheet("SODBShipSetProdIDColumnName")), ShipSetItem);
			logs.logging("INFO", "ShipSet Item Id Is Verified With DB And UI Values");
			verifyAssertionBothValues(dbCommonFunctions.ItemMasterShipSetDetails(Item, SeqNo,
					commonFunc.getDataFromTestDataSheet("SODBShipSetUOMColumnName")), ShipSetItemUOM);
			logs.logging("INFO", "ShipSet Item UOM Is Verified With DB And UI Values");
			verifyAssertionBothValues(dbCommonFunctions.ItemMasterShipSetDetails(Item, SeqNo,
					commonFunc.getDataFromTestDataSheet("SODBShipSetQtyColumnName")), ShipSetQty);
			logs.logging("INFO", "ShipSet Item Qty Is Verified With DB And UI Values");
		}
		webDB.switchToDefaultContent();
		webDB.pressEscapeKey();
	}

	/**
	 * This Method Is Used To Verify The SO Total Amount With DataBase
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void verifySOTotalAmountWithExcelAndDB(String SheetName) {
		String[][] SOMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Sales STD PRICE.xlsx";
		SOMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 4; i < SOMultipleline.length; i++) {
			String ITEM_ID = SOMultipleline[i][1];
			String SO_Total_Amount = SOMultipleline[i][30];
			if (ITEM_ID.equals("")) {
				if (!SO_Total_Amount.equals("")) {
					verifyAssertionBothValues(dbCommonFunctions.SOHeaderDetails(VariableHandler.getSONumber(),
							commonFunc.getDataFromTestDataSheet("SODBTotalAmountColumnName")), SO_Total_Amount);
					logs.logging("INFO",
							"Expected Sales Order Total Amount Is Matched With DataBase Values For This Sales Order Document "
									+ VariableHandler.getSONumber());
				}
			}
		}
	}

	/**
	 * This Method Is Used To Write Unit Price In The Excel Sheet
	 * 
	 * @author Harikrishna - RMITS
	 * @param FilePath
	 * @param SheetName
	 * @param i
	 * @param Item
	 */
	public void writeUnitPriceInExcel(String FilePath, String SheetName, int i, String Item) {
		commonFunc.checkRefreshCellsDisappear();
		String UnitCost = webDB.getDriver().findElement(By.xpath(SalesOrderLocators.UnitCostTable(i - 3))).getText();
		logs.logging("INFO", "Get Text Unit Cost For This Item  " + Item + " Is : " + UnitCost);
		excelUtils.writeDataInExcel(FilePath, SheetName, i, 7, UnitCost);
		commonFunc.checkRefreshCellsDisappear();
	}

	public void clickPendingButtonOfSoAmendment() {
		flag = webDB.waitForElement(SalesOrderLocators.SOAmendmentPendingButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.SOAmendmentPendingButton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.SingleSOAmendment, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesOrderLocators.SingleSOAmendment, ElementType.Xpath);
				flag = webDB.waitForElement(SalesOrderLocators.UpdateCloseshortButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Navigated To Single Amendment / CloseShort Transaction Screen");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Create SO Amendment Header Details
	 * 
	 * @author Harikrishna - RMITS
	 * @param Location
	 * @param Prefix
	 * @param SONumber
	 */
	public void CreateSoAmdHeaderDetails(String Location, String Prefix, String SONumber) {
		flag = webDB.waitForElement(SalesOrderLocators.SoAmdlocationLov, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.SoAmdlocationLov, Location);
			flag = webDB.waitForElement(SalesOrderLocators.SoAmdPrefixLov, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.SoAmdPrefixLov, Prefix);
				flag = webDB.waitForElement(SalesOrderLocators.SoAmdSoNumberlov, ElementType.Xpath);
				if (flag) {
					commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.SoAmdSoNumberlov, SONumber);
					flag = webDB.waitForElement(SalesOrderLocators.SoAmdHeaderWiseSavebutton, ElementType.Xpath);
					if (flag) {
						commonFunc.checkRefreshCellsDisappear();
						webDB.clickAnElement(SalesOrderLocators.SoAmdHeaderWiseSavebutton, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						if (webDB.isElementDisplayed(SalesOrderLocators.NoteAlertCloseIcon, ElementType.Xpath)) {
							webDB.clickAnElement(SalesOrderLocators.NoteAlertCloseIcon, ElementType.Xpath);
							logs.logging("INFO", "Sales Order Amendment Header Record Created Sucessfully");
						}
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(SalesOrderLocators.SoAmdDocNumber, ElementType.Xpath);
						if (flag) {
							String SOAmndDocNumber = webDB.getTextFromElement(SalesOrderLocators.SoAmdDocNumber,
									ElementType.Xpath);
							VariableHandler.setSOAmdNumber(SOAmndDocNumber);
							logs.logging("INFO",
									"Sales Order Amendment Document Number Is : " + VariableHandler.getSOAmdNumber());
							commonFunc.checkRefreshCellsDisappear();
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Fetch Button And Select The Records And
	 * Inserted Into The Lines
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void clickFetchButton() {
		flag = webDB.waitForElement(SalesOrderLocators.SoAmdFetchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.SoAmdFetchButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			webDB.switchToFrame(SalesOrderLocators.SoAmdfetchFrame, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.SoAmdFetchOkbutton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sales Order Amendment Header Wise Fetch Button Is Clicked Sucessfully");
				flag = webDB.waitForElement(SalesOrderLocators.SoAmdFetchSelectAllbutton, ElementType.Xpath);
				if (flag) {
					commonFunc.checkRefreshCellsDisappear();
					webDB.clickAnElement(SalesOrderLocators.SoAmdFetchSelectAllbutton, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(SalesOrderLocators.SoAmdFetchOkbutton, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SalesOrderLocators.SoAmdFetchOkbutton, ElementType.Xpath);
						webDB.switchToDefaultContent();
						commonFunc.checkRefreshCellsDisappear();
						flag = webDB.waitForElement(SalesOrderLocators.SoAmdpostbutton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO",
									"Sales Order Amendment Line Details Are Added Sucessfully Through Fetch Button");
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Change The Unit Price In Sales Order Amendment Screen
	 * 
	 * @author Harikrishna - RMITS
	 * @param NewUnitPrice
	 * @param j
	 * @param Item
	 */
	public void changeunitPrice(String NewUnitPrice, int j, String Item) {
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.verifyAddTableSpecs(SalesOrderLocators.SoAmdUnitCostField(j - 3),
				SalesOrderLocators.SoAmdNewUnitPriceInputField, NewUnitPrice);
		webDB.pressTabKey();
		commonFunc.hardWait(1000);
		logs.logging("INFO", "New UnitPrice Is Changed For This Item " + Item
				+ "Against This Sales Order Amendment Document " + VariableHandler.getSOAmdNumber());
		commonFunc.checkRefreshCellsDisappear();
	}

	/**
	 * This Method Is Used To Change The Qty In Sales Order Amendment Screen
	 * 
	 * @param NewQty
	 * @param j
	 * @param Item
	 */
	public void changeQty(String NewQty, int j, String Item) {
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.verifyAddTableSpecs(SalesOrderLocators.SoAmdQtyField(j - 3),
				SalesOrderLocators.SoAmdNewQtyInputField, NewQty);
		logs.logging("INFO", "New UnitQty Is Changed For This Item " + Item
				+ "Against This Sales Order Amendment Document " + VariableHandler.getSOAmdNumber());
		commonFunc.hardWait(500);
		webDB.pressTabKey();
		commonFunc.hardWait(500);
		commonFunc.checkRefreshCellsDisappear();
	}

	/**
	 * This Method Is Used To Add The Unit Price In Sales Order Amendment Screen
	 * 
	 * @author Harikrishna - RMITS
	 * @param NewUnitPrice
	 * @param j
	 * @param Item
	 */
	public void additionOfUnitPrice(String NewUnitPrice, int j, String Item) {
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.verifyAddTableSpecs(SalesOrderLocators.SoAmdUnitCostField(j / j),
				SalesOrderLocators.SoAmdNewUnitPriceInputField, NewUnitPrice);
		webDB.pressTabKey();
		logs.logging("INFO", "New UnitPrice Is Changed For This Item " + Item
				+ "Against This Sales Order Amendment Document " + VariableHandler.getSOAmdNumber());
		commonFunc.checkRefreshCellsDisappear();
	}

	/**
	 * This Method Is Used To Add The Qty In Sales Order Amendment Screen
	 * 
	 * @author Harikrishna - RMITS
	 * @param NewQty
	 * @param j
	 * @param Item
	 */
	public void additionOfQty(String NewQty, int j, String Item) {
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.verifyAddTableSpecs(SalesOrderLocators.SoAmdQtyField(j / j),
				SalesOrderLocators.SoAmdNewQtyInputField, NewQty);
		webDB.pressTabKey();
		logs.logging("INFO", "New UnitQty Is Changed For This Item " + Item
				+ "Against This Sales Order Amendment Document " + VariableHandler.getSOAmdNumber());
		commonFunc.checkRefreshCellsDisappear();
	}

	/**
	 * This Method Is Used To Click The Sales Order Amendment Line Wise Save Button
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickSoAmdLineWiseSaveButton() {
		flag = webDB.waitForElement(SalesOrderLocators.SoAmdLineWiseSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.SoAmdLineWiseSaveButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(SalesOrderLocators.SoAmdpostbutton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Line Wise Save Button Is Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Sales Order Amendment Post Button
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickSOAmdPostButton() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(SalesOrderLocators.SoAmdpostbutton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.SoAmdpostbutton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.WorkApprovalFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Post Button Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used For Price Change Scenarios
	 * 
	 * @author Harikrishna- RMITS
	 * @param Customer
	 * @param CustomerDesc
	 * @param SOAmdSheetName
	 * @param SOSheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean SalesOrderAmendmentPriceChange(String Customer, String CustomerDesc, String SOAmdSheetName,
			String SOSheetName, String SOALocation, String SOAPrefix, String SOAUnit) throws IOException {
		SOMultipleline = excelUtils.readAllDataInExcel(Filepath1, SOAmdSheetName);
		SalesOrder(Customer, CustomerDesc, SOSheetName);
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateSalesOrderAmendmentPage();
		clickPendingButtonOfSoAmendment();
		CreateSoAmdHeaderDetails(SOALocation, SOAPrefix, VariableHandler.getSONumber());
		verifySalesOrderAmendmentHeaderDetailsWithDB(SOALocation, SOAUnit, SOAPrefix, Customer, CustomerDesc);
		clickFetchButton();
		for (int j = 4; j < SOMultipleline.length; j++) {
			String ITEM_ID = SOMultipleline[j][1];
			String NewUnitPrice = SOMultipleline[j][44];
			if (!ITEM_ID.equals("")) {
				changeunitPrice(NewUnitPrice, j, ITEM_ID);
			}
		}
		clickSoAmdLineWiseSaveButton();
		verifySalesOrderAmendmentLineDetailsWithDB(SOAmdSheetName);
		verifySOATotalAmountWithExcelAndDB(SOAmdSheetName);
		clickSOAmdPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderAmendmentDocEntryCompletedStatusWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSOAmdNumber(),
					webDB.getDataFromProperties("HodEmpId"), SOAUnit, System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderAmendmentDocApprovedStatusWithDB();
				verifySOLineDetailsAfterAmendment(SOAmdSheetName);
				verifySOTotalAmountAfterAmendment(SOAmdSheetName);
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used For Price Change Scenarios
	 * 
	 * @author Harikrishna- RMITS
	 * @param Customer
	 * @param CustomerDesc
	 * @param SOAmdSheetName
	 * @param SOSheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean SalesOrderAmendmentPriceChangeUTGST(String Customer, String CustomerDesc, String SOAmdSheetName,
			String SOSheetName, String SOALocation, String SOAPrefix, String SOAUnit) throws IOException {
		SOMultipleline = excelUtils.readAllDataInExcel(Filepath1, SOAmdSheetName);
		SOUTGST();
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateSalesOrderAmendmentPage();
		clickPendingButtonOfSoAmendment();
		CreateSoAmdHeaderDetails(SOALocation, SOAPrefix, VariableHandler.getSONumber());
		verifySalesOrderAmendmentHeaderDetailsWithDB(SOALocation, SOAUnit, SOAPrefix, Customer, CustomerDesc);
		clickFetchButton();
		for (int j = 4; j < SOMultipleline.length; j++) {
			String ITEM_ID = SOMultipleline[j][1];
			String NewUnitPrice = SOMultipleline[j][44];
			if (!ITEM_ID.equals("")) {
				changeunitPrice(NewUnitPrice, j, ITEM_ID);
			}
		}
		clickSoAmdLineWiseSaveButton();
		verifySalesOrderAmendmentLineDetailsWithDB(SOAmdSheetName);
		verifySOATotalAmountWithExcelAndDB(SOAmdSheetName);
		clickSOAmdPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderAmendmentDocEntryCompletedStatusWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSOAmdNumber(),
					webDB.getDataFromProperties("HodEmpId"), SOAUnit, System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderAmendmentDocApprovedStatusWithDB();
				verifySOLineDetailsAfterAmendment(SOAmdSheetName);
				verifySOTotalAmountAfterAmendment(SOAmdSheetName);
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used For Qty Change Scenarios
	 * 
	 * @author Harikrishna -RMITS
	 * @param Customer
	 * @param CustomerDesc
	 * @param SOAmdSheetName
	 * @param SOSheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean SalesOrderAmendmentQtyChange(String Customer, String CustomerDesc, String SOAmdSheetName,
			String SOSheetName, String SOALocation, String SOAPrefix, String SOAUnit) throws IOException {
		SOMultipleline = excelUtils.readAllDataInExcel(Filepath1, SOAmdSheetName);
		SalesOrder(Customer, CustomerDesc, SOSheetName);
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateSalesOrderAmendmentPage();
		clickPendingButtonOfSoAmendment();
		CreateSoAmdHeaderDetails(SOALocation, SOAPrefix, VariableHandler.getSONumber());
		verifySalesOrderAmendmentHeaderDetailsWithDB(SOALocation, SOAUnit, SOAPrefix, Customer, CustomerDesc);
		clickFetchButton();
		for (int j = 4; j < SOMultipleline.length; j++) {
			String ITEM_ID = SOMultipleline[j][1];
			String NewQty = SOMultipleline[j][43];
			if (!ITEM_ID.equals("")) {
				changeQty(NewQty, j, ITEM_ID);
			}
		}
		commonFunc.hardWait(500);
		commonFunc.checkRefreshCellsDisappear();
		clickSoAmdLineWiseSaveButton();
		verifySalesOrderAmendmentLineDetailsWithDB(SOAmdSheetName);
		verifySOATotalAmountWithExcelAndDB(SOAmdSheetName);
		clickSOAmdPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderAmendmentDocEntryCompletedStatusWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSOAmdNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderAmendmentDocApprovedStatusWithDB();
				verifySOLineDetailsAfterAmendment(SOAmdSheetName);
				verifySOTotalAmountAfterAmendment(SOAmdSheetName);
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used For Qty Change Scenarios
	 * 
	 * @author Harikrishna -RMITS
	 * @param Customer
	 * @param CustomerDesc
	 * @param SOAmdSheetName
	 * @param SOSheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean SalesOrderAmendmentQtyChangeUTGST(String Customer, String CustomerDesc, String SOAmdSheetName,
			String SOSheetName, String SOALocation, String SOAPrefix, String SOAUnit) throws IOException {
		SOMultipleline = excelUtils.readAllDataInExcel(Filepath1, SOAmdSheetName);
		SOUTGST();
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateSalesOrderAmendmentPage();
		clickPendingButtonOfSoAmendment();
		CreateSoAmdHeaderDetails(SOALocation, SOAPrefix, VariableHandler.getSONumber());
		verifySalesOrderAmendmentHeaderDetailsWithDB(SOALocation, SOAUnit, SOAPrefix, Customer, CustomerDesc);
		clickFetchButton();
		for (int j = 4; j < SOMultipleline.length; j++) {
			String ITEM_ID = SOMultipleline[j][1];
			String NewQty = SOMultipleline[j][43];
			if (!ITEM_ID.equals("")) {
				changeQty(NewQty, j, ITEM_ID);
			}
		}
		clickSoAmdLineWiseSaveButton();
		verifySalesOrderAmendmentLineDetailsWithDB(SOAmdSheetName);
		verifySOATotalAmountWithExcelAndDB(SOAmdSheetName);
		clickSOAmdPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderAmendmentDocEntryCompletedStatusWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSOAmdNumber(),
					webDB.getDataFromProperties("HodEmpId"), SOAUnit, System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderAmendmentDocApprovedStatusWithDB();
				verifySOLineDetailsAfterAmendment(SOAmdSheetName);
				verifySOTotalAmountAfterAmendment(SOAmdSheetName);
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used For Discount Change Scenarios In So Amendment
	 * 
	 * @author Harikrishna - RMITS
	 * @param Customer
	 * @param CustomerDesc
	 * @param SOAmdSheetName
	 * @param SOSheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean SalesOrderAmendmentDiscountChange(String Customer, String CustomerDesc, String SOAmdSheetName,
			String SOSheetName, String SOALocation, String SOAPrefix, String SOAUnit) throws IOException {
		SOMultipleline = excelUtils.readAllDataInExcel(Filepath1, SOAmdSheetName);
		SalesOrder(Customer, CustomerDesc, SOSheetName);
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateSalesOrderAmendmentPage();
		clickPendingButtonOfSoAmendment();
		CreateSoAmdHeaderDetails(SOALocation, SOAPrefix, VariableHandler.getSONumber());
		verifySalesOrderAmendmentHeaderDetailsWithDB(SOALocation, SOAUnit, SOAPrefix, Customer, CustomerDesc);
		clickFetchButton();
		for (int j = 4; j < SOMultipleline.length; j++) {
			String ITEM_ID = SOMultipleline[j][1];
			String TradeDiscount = SOMultipleline[j][46];
			String SpecialDiscount = SOMultipleline[j][47];
			String CashDiscount = SOMultipleline[j][48];
			if (!ITEM_ID.equals("")) {
				clickSoAmdLineWiseDetails(j - 3);
				changeSoAmdDiscountDetails(TradeDiscount, SpecialDiscount, CashDiscount);
				clickSoAmdLineWiseDetailSaveButton();
			}
		}
		verifySalesOrderAmendmentLineDetailsWithDB(SOAmdSheetName);
		verifySOATotalAmountWithExcelAndDB(SOAmdSheetName);
		clickSOAmdPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderAmendmentDocEntryCompletedStatusWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSOAmdNumber(),
					webDB.getDataFromProperties("HodEmpId"), SOAUnit, System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderAmendmentDocApprovedStatusWithDB();
				verifySOLineDetailsAfterAmendment(SOAmdSheetName);
				verifySOTotalAmountAfterAmendment(SOAmdSheetName);
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used For Discount Change Scenarios In So Amendment
	 * 
	 * @author Harikrishna - RMITS
	 * @param Customer
	 * @param CustomerDesc
	 * @param SOAmdSheetName
	 * @param SOSheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean SalesOrderAmendmentDiscountChangeUTGST(String Customer, String CustomerDesc, String SOAmdSheetName,
			String SOSheetName, String SOALocation, String SOAPrefix, String SOAUnit) throws IOException {
		SOMultipleline = excelUtils.readAllDataInExcel(Filepath1, SOAmdSheetName);
		SOUTGST();
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateSalesOrderAmendmentPage();
		clickPendingButtonOfSoAmendment();
		CreateSoAmdHeaderDetails(SOALocation, SOAPrefix, VariableHandler.getSONumber());
		verifySalesOrderAmendmentHeaderDetailsWithDB(SOALocation, SOAUnit, SOAPrefix, Customer, CustomerDesc);
		clickFetchButton();
		for (int j = 4; j < SOMultipleline.length; j++) {
			String ITEM_ID = SOMultipleline[j][1];
			String TradeDiscount = SOMultipleline[j][46];
			String SpecialDiscount = SOMultipleline[j][47];
			String CashDiscount = SOMultipleline[j][48];
			if (!ITEM_ID.equals("")) {
				clickSoAmdLineWiseDetails(j - 3);
				changeSoAmdDiscountDetails(TradeDiscount, SpecialDiscount, CashDiscount);
				clickSoAmdLineWiseDetailSaveButton();
			}
		}
		verifySalesOrderAmendmentLineDetailsWithDB(SOAmdSheetName);
		verifySOATotalAmountWithExcelAndDB(SOAmdSheetName);
		clickSOAmdPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderAmendmentDocEntryCompletedStatusWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSOAmdNumber(),
					webDB.getDataFromProperties("HodEmpId"), SOAUnit, System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderAmendmentDocApprovedStatusWithDB();
				verifySOLineDetailsAfterAmendment(SOAmdSheetName);
				verifySOTotalAmountAfterAmendment(SOAmdSheetName);
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used For Addition Of Lines In Sales Order Amendment
	 * 
	 * @author Harikrishna - RMITS
	 * @param Customer
	 * @param CustomerDesc
	 * @param SOAmdSheetName
	 * @param SOSheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean SalesOrderAmendmentAdditionOfLines(String Customer, String CustomerDesc, String SOAmdSheetName,
			String SOSheetName, String SOALocation, String SOAPrefix, String SOAUnit) throws IOException {
		SOMultipleline = excelUtils.readAllDataInExcel(Filepath1, SOAmdSheetName);
		SalesOrder(Customer, CustomerDesc, SOSheetName);
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateSalesOrderAmendmentPage();
		clickPendingButtonOfSoAmendment();
		CreateSoAmdHeaderDetails(SOALocation, SOAPrefix, VariableHandler.getSONumber());
		verifySalesOrderAmendmentHeaderDetailsWithDB(SOALocation, SOAUnit, SOAPrefix, Customer, CustomerDesc);
		clickFetchButton();
		for (int j = 4; j < SOMultipleline.length; j++) {
			String ITEM_ID = SOMultipleline[j][1];
			String Qty = SOMultipleline[j][43];
			String UnitPrice = SOMultipleline[j][44];
			String TradeDiscount = SOMultipleline[j][46];
			String SpecialDiscount = SOMultipleline[j][47];
			String CashDiscount = SOMultipleline[j][48];
			String SoAmdType = SOMultipleline[j][72];
			if (!ITEM_ID.equals("")) {
				if (SoAmdType.equalsIgnoreCase("Addition")) {
					additionOfSoAmendmentLines(ITEM_ID, Qty, UnitPrice, j);
					clickSoAmdLineWiseDetails(j - 3);
					changeSoAmdDiscountDetails(TradeDiscount, SpecialDiscount, CashDiscount);
					clickSoAmdLineWiseDetailSaveButton();
				}
			}
		}
		verifySalesOrderAmendmentLineDetailsWithDB(SOAmdSheetName);
		verifySOATotalAmountWithExcelAndDB(SOAmdSheetName);
		clickSOAmdPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderAmendmentDocEntryCompletedStatusWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSOAmdNumber(),
					webDB.getDataFromProperties("HodEmpId"), SOAUnit, System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderAmendmentDocApprovedStatusWithDB();
				verifySOLineDetailsAfterAmendment(SOAmdSheetName);
				verifySOTotalAmountAfterAmendment(SOAmdSheetName);
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used For Addition Of Lines In Sales Order Amendment
	 * 
	 * @author Harikrishna - RMITS
	 * @param Customer
	 * @param CustomerDesc
	 * @param SOAmdSheetName
	 * @param SOSheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean SalesOrderAmendmentAdditionOfLinesUTGST(String Customer, String CustomerDesc, String SOAmdSheetName,
			String SOSheetName, String SOALocation, String SOAPrefix, String SOAUnit) throws IOException {
		SOMultipleline = excelUtils.readAllDataInExcel(Filepath1, SOAmdSheetName);
		SOUTGST();
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateSalesOrderAmendmentPage();
		clickPendingButtonOfSoAmendment();
		CreateSoAmdHeaderDetails(SOALocation, SOAPrefix, VariableHandler.getSONumber());
		verifySalesOrderAmendmentHeaderDetailsWithDB(SOALocation, SOAUnit, SOAPrefix, Customer, CustomerDesc);
		clickFetchButton();
		for (int j = 4; j < SOMultipleline.length; j++) {
			String ITEM_ID = SOMultipleline[j][1];
			String Qty = SOMultipleline[j][43];
			String UnitPrice = SOMultipleline[j][44];
			String TradeDiscount = SOMultipleline[j][46];
			String SpecialDiscount = SOMultipleline[j][47];
			String CashDiscount = SOMultipleline[j][48];
			String SoAmdType = SOMultipleline[j][72];
			if (!ITEM_ID.equals("")) {
				if (SoAmdType.equalsIgnoreCase("Addition")) {
					additionOfSoAmendmentLines(ITEM_ID, Qty, UnitPrice, j);
					clickSoAmdLineWiseDetails(j - 3);
					changeSoAmdDiscountDetails(TradeDiscount, SpecialDiscount, CashDiscount);
					clickSoAmdLineWiseDetailSaveButton();
				}
			}
		}
		verifySalesOrderAmendmentLineDetailsWithDB(SOAmdSheetName);
		verifySOATotalAmountWithExcelAndDB(SOAmdSheetName);
		clickSOAmdPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderAmendmentDocEntryCompletedStatusWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSOAmdNumber(),
					webDB.getDataFromProperties("HodEmpId"), SOAUnit, System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderAmendmentDocApprovedStatusWithDB();
				verifySOLineDetailsAfterAmendment(SOAmdSheetName);
				verifySOTotalAmountAfterAmendment(SOAmdSheetName);
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used For So Amendment With Different Price Class
	 * 
	 * @author Harikrishna - RMITS
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean SalesOrderAmendmentWithDifferentPriceClass() throws IOException {
		SOMultipleline = excelUtils.readAllDataInExcel(Filepath1, webDB.getDataFromProperties("SOAmendmentDPC"));
		SalesOrder(commonFunc.getDataFromTestDataSheet("Customer"), commonFunc.getDataFromTestDataSheet("CustomerDesc"),
				webDB.getDataFromProperties("SODomRateContract"));
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateSalesOrderAmendmentPage();
		clickPendingButtonOfSoAmendment();
		CreateSoAmdHeaderDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SOAmendmentPrefix"), VariableHandler.getSONumber());
		clickFetchButton();
		for (int j = 4; j < SOMultipleline.length; j++) {
			String ITEM_ID = SOMultipleline[j][1];
			String PriceClass = SOMultipleline[j][64];
			if (!ITEM_ID.equals("")) {
				clickSoAmdLineWiseDetails(j - 3);
				clickSoAmdLineWiseDetails2();
				changePriceClassInSoAmendment(PriceClass);
				clickSoAmdLineWiseDetail2SaveButton();
			}
		}
		clickSOAmdPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderAmendmentDocEntryCompletedStatusWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSOAmdNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderAmendmentDocApprovedStatusWithDB();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used For Single Closeshort
	 * 
	 * @author Harikrishna - RMITS
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean SalesOrderAmendmentSingleCloseshort() throws IOException {
		SoGeneral();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderAmendmentPage();
		clickPendingButtonOfSoAmendment();
		CreateSoAmdHeaderDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SOAmendmentPrefix"), VariableHandler.getSONumber());
		clickFetchButton();
		clickUpdateCloseshortButton();
		clickSOAmdPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderAmendmentDocEntryCompletedStatusWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSOAmdNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
//			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderAmendmentDocApprovedStatusWithDB();
				verifySalesOrderDocumentHeaderStatusCloseshortWithDB();
				verifySalesOrderLinesStatusCloseShortWithDB(webDB.getDataFromProperties("SODomUserDefined"));
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used For So Amendment With Charges
	 * 
	 * @author Harikrishna - RMITS
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean SalesOrderAmendmentWithCharges() throws IOException {
		SoCharges();
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateSalesOrderAmendmentPage();
		clickPendingButtonOfSoAmendment();
		CreateSoAmdHeaderDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("SOAmendmentPrefix"), VariableHandler.getSONumber());
		navigateToChargesTabInSoAmendment();
		SOMultipleline = excelUtils.readAllDataInExcel(Filepath1, webDB.getDataFromProperties("SOAmendWithCharges"));
		for (int j = 10; j < SOMultipleline.length; j++) {
			String CHARGE_ITEM_ID = SOMultipleline[j][3];
			String CHARGE_VALUE = SOMultipleline[j][15];
			String ChargeAmdType = SOMultipleline[j][22];
			if (!CHARGE_ITEM_ID.equals("")) {
				if (ChargeAmdType.equalsIgnoreCase("AOI")) {
					System.out.println(4);
					addChargesInSoAmendement(CHARGE_ITEM_ID, CHARGE_VALUE);
				}
				if (ChargeAmdType.equals("Modify")) {
					System.out.println(3);
					commonFunc.verifyAddTableSpecs(SalesOrderLocators.SoAmdChargeUnitPriceField(j - 9),
							SalesOrderLocators.SoAmdChargesAmountInputField, CHARGE_VALUE);
					webDB.pressTabKey();
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(SalesOrderLocators.SoAmdChargeSaveButton, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SalesOrderLocators.SoAmdChargeSaveButton, ElementType.Xpath);
						commonFunc.checkRefreshCellsDisappear();
						logs.logging("INFO", "Charges Line Wise Save Button Is Clicked Successfully");
					}
				}
			}
		}
		clickSOAmdPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderAmendmentDocEntryCompletedStatusWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSOAmdNumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderAmendmentDocApprovedStatusWithDB();
				verifySOTotalAmountAfterAmendmentWithCharges(webDB.getDataFromProperties("SOAmendWithCharges"));
			}
		}
		return flag;
	}

	/**
	 * Sales Order Multiple With Different Price Class
	 * 
	 * @author Harikrishna -RMITS
	 * @return boolean flag
	 */
	public boolean SOMultipleWithDifferentPriceClass() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();
		addSalesOrderLineThroughExcelOtherThanUD(webDB.getDataFromProperties("SODifferentPriceClass"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SO_HSNCode"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * Create SO with Shipset Items
	 * 
	 * @author Harikrishna -RMITS
	 * @return boolean flag
	 */
	public boolean SOShipSet() {
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();
		addSalesOrderLineThroughExcelUD(webDB.getDataFromProperties("SOShipSet"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SO_HSNCode"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * Create SO - Domestic - UTGST Customer
	 * 
	 * @author Harikrishna -RMITS
	 * @return boolean flag
	 */
	public boolean SOUTGST() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("UTGSTLocation1"),
				commonFunc.getDataFromTestDataSheet("UTGSTUnit1"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("UTGSTCustomer"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();
		addSalesOrderLineThroughExcelUD(webDB.getDataFromProperties("SOUTGST"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SI_HSNCode"),
				commonFunc.getDataFromTestDataSheet("UTGSTCustomer"));
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("UTGSTUnit1"),
					System.getProperty("HariUsername"));
//			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Click The So Amendment Line Wise Details
	 * 
	 * @author Harikrishna - RMITS
	 * @param i
	 */
	public void clickSoAmdLineWiseDetails(int i) {
		flag = webDB.waitForElement(SalesOrderLocators.SOAmdLineDetails(i), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.SOAmdLineDetails(i), ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.SOAmdDetailsButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesOrderLocators.SOAmdDetailsButton, ElementType.Xpath);
				flag = webDB.waitForElement(SalesOrderLocators.SoAmdDetailsFrame, ElementType.Xpath);
				if (flag) {
					webDB.switchToFrame(SalesOrderLocators.SoAmdDetailsFrame, ElementType.Xpath);
					flag = webDB.waitForElement(SalesOrderLocators.SOAmdDiscountDetails, ElementType.Xpath);
					logs.logging("INFO", "Sales Order Amendment Line Wise Details Button Is Clicked Sucessfully");
					if (flag) {
						webDB.clickAnElement(SalesOrderLocators.SOAmdDiscountDetails, ElementType.Xpath);
						flag = webDB.waitForElement(SalesOrderLocators.SOAmdNewDiscDetailsButton, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(SalesOrderLocators.SOAmdNewDiscDetailsButton, ElementType.Xpath);
							flag = webDB.waitForElement(SalesOrderLocators.SOAmdTDDiscLabel,
									ElementType.Xpath);
							if (flag) {
								logs.logging("INFO", "New Discount Amount (+) Button Is Clicked Successfully");
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The SO Amendment Line Wise Details 2
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickSoAmdLineWiseDetails2() {
//		webDB.switchToFrame(SalesOrderLocators.SoAmdDetailsFrame, ElementType.Xpath);
		flag = webDB.waitForElement(SalesOrderLocators.SOAmdSourceDetailsButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.SOAmdSourceDetailsButton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.SoAmdPriceClassLov, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sales Order Amendment Line Wise Details 2 Button Is Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Change The Price Class In So Amendment
	 * 
	 * @author Harikrishna - RMITS
	 * @param PriceClass
	 */
	public void changePriceClassInSoAmendment(String PriceClass) {
		commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.SoAmdPriceClassLov, PriceClass);
		webDB.pressTabKey();
	}

	/**
	 * This Method Is used To Change The So Amendment Discount Details
	 * 
	 * @author Harikrishna - RMITS
	 * @param New_TD
	 * @param New_SD
	 * @param New_CD
	 */
	public void changeSoAmdDiscountDetails(String New_TD, String New_SD, String New_CD) {
//		webDB.switchToFrame(SalesOrderLocators.SoAmdDetailsFrame, ElementType.Xpath);
		commonFunc.checkRefreshCellsDisappear();
		enterSODiscountDetails(SalesOrderLocators.SoAmdTradeDiscountPercentage, New_TD);
		logs.logging("INFO", "New Trade Discount Percentage " + New_TD + " is Entered Sucessfully");
		enterSODiscountDetails(SalesOrderLocators.SoAmdSpecialDicountPercentage, New_SD);
		logs.logging("INFO", "New Special Discount Percentage " + New_SD + " is Entered Sucessfully");
		enterSODiscountDetails(SalesOrderLocators.SoAmdCashDiscountPercentage, New_CD);
		logs.logging("INFO", "New Cash Discount Percentage " + New_CD + " is Entered Sucessfully");
		webDB.switchToDefaultContent();
	}

	/**
	 * This Method Is Used To Click The SO Amendment Line Wise Details Save Button
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickSoAmdLineWiseDetailSaveButton() {
		commonFunc.checkRefreshCellsDisappear();
		webDB.switchToFrame(SalesOrderLocators.SoAmdDetailsFrame, ElementType.Xpath);
		flag = webDB.waitForElement(SalesOrderLocators.SoAmdLineWiseDetailSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.SoAmdLineWiseDetailSaveButton, ElementType.Xpath);
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(SalesOrderLocators.SoAmdpostbutton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sales Order Amendment Line Wise Detail Save Button Is Clicked Sucessfully");
			}
		}
		commonFunc.checkRefreshCellsDisappear();
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The SO Amendment Line Wise Details Save Button 2
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickSoAmdLineWiseDetail2SaveButton() {
		webDB.switchToFrame(SalesOrderLocators.SoAmdDetailsFrame, ElementType.Xpath);
		flag = webDB.waitForElement(SalesOrderLocators.SoAmdLineDetails2SaveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.SoAmdLineDetails2SaveButton, ElementType.Xpath);
			webDB.switchToDefaultContent();
			flag = webDB.waitForElement(SalesOrderLocators.SoAmdpostbutton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sales Order Amendment Line Wise Detail 2 Save Button Is Clicked Sucessfully");
			}
		}
		commonFunc.checkRefreshCellsDisappear();
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The So Amendment Cancel Button
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickSoAmdCancelButton() {
		flag = webDB.waitForElement(SalesOrderLocators.SoAmdCancelButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.SoAmdCancelButton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.SoAmdDocNumber, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sales Order Amendment Cancel Button Is Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Update CloseShort Button in SoAmendment
	 * Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickUpdateCloseshortButton() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(SalesOrderLocators.SoAmdUpdateCloseshortButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.SoAmdUpdateCloseshortButton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.SoAmdpostbutton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sales Order Amendment Update Closeshort Button Is Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Update Price Button In Sales Order Amendment
	 * Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickUpdatePriceButton() {
		flag = webDB.waitForElement(SalesOrderLocators.SoAmdUpdatePriceButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.SoAmdUpdatePriceButton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.SoAmdpostbutton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sales Order Amendment Update Price Button Is Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The So Amendment Line Wise Add Button
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickSoAmdLineWiseAddButton() {
		flag = webDB.waitForElement(SalesOrderLocators.SoAmdLineWiseAddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.SoAmdLineWiseAddButton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.SoAmdItemField, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sales Order Amendment Line Wise Add Button Is Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is USed To Add the Lines In So Amendment Lines
	 * 
	 * @author Harikrishna - RMITS
	 * @param ItemID
	 * @param NewQty
	 * @param NewUnitPrice
	 * @param j
	 */
	public void additionOfSoAmendmentLines(String ItemID, String NewQty, String NewUnitPrice, int j) {
		clickSoAmdLineWiseAddButton();
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(SalesOrderLocators.SoAmdItemField, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.SoAmdItemField, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.SoAmdItemIDLovField, ItemID);
			webDB.pressTabKey();
			commonFunc.checkRefreshCellsDisappear();
			additionOfQty(NewQty, j, ItemID);
			additionOfUnitPrice(NewUnitPrice, j, ItemID);
			clickSoAmdLineWiseSaveButton();
			commonFunc.checkRefreshCellsDisappear();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Navigate Charges tab In The So Amendment
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void navigateToChargesTabInSoAmendment() {
		flag = webDB.waitForElement(SalesOrderLocators.SoAmdChargesTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.SoAmdChargesTab, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.SoAmdAddCharges, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated Sucessfully To Sales Order Amendment Charges Tab");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The charges In So Amnemdment
	 * 
	 * @author Harikrishna - RMITS
	 * @param ChargeItem
	 * @param ChargeAmount
	 */
	public void addChargesInSoAmendement(String ChargeItem, String ChargeAmount) {
		flag = webDB.waitForElement(SalesOrderLocators.SoAmdAddCharges, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.SoAmdAddCharges, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(SalesOrderLocators.SoAmdChargeItemField, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesOrderLocators.SoAmdChargeItemField, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.SoAmdChargeItemLov, ChargeItem);
				webDB.pressTabKey();
				commonFunc.verifyAddTableSpecs(SalesOrderLocators.SoAmdChargesAmountField,
						SalesOrderLocators.SoAmdChargesAmountInputField, ChargeAmount);
				webDB.pressTabKey();
				flag = webDB.waitForElement(SalesOrderLocators.SoAmdChargeSaveButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SalesOrderLocators.SoAmdChargeSaveButton, ElementType.Xpath);
					flag = webDB.waitForElement(SalesOrderLocators.SoAmdpostbutton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Charges Item Is Added And Saved Sucessfully");
						commonFunc.checkRefreshCellsDisappear();
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Sales Order _ General
	 * 
	 * @author Harikrishna - RMITS
	 * @param Customer
	 * @param CustomerDesc
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean SalesOrder(String Customer, String CustomerDesc, String SheetName) throws IOException {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				Customer);
		salesOrderDBHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), Customer, CustomerDesc,
				commonFunc.getDataFromTestDataSheet("SO_General"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();// TODO
		if (SheetName.equals(webDB.getDataFromProperties("SODomRateContract"))) {
			addSalesOrderLineThroughExcelOtherThanUD(SheetName, commonFunc.getDataFromTestDataSheet("SO_General"),
					commonFunc.getDataFromTestDataSheet("SO_HSNCode"), commonFunc.getDataFromTestDataSheet("Customer"));
		} else {
			addSalesOrderLineThroughExcelUD(SheetName, commonFunc.getDataFromTestDataSheet("SO_General"),
					commonFunc.getDataFromTestDataSheet("SO_HSNCode"), Customer);
		}

		clickPostButton();
		forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
//			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * Sales Order _ General
	 * 
	 * @author Harikrishna - RMITS
	 * @param Customer
	 * @param CustomerDesc
	 * @param SheetName
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean MultipleSalesOrder(String Customer, String CustomerDesc, String SheetName) throws IOException {
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				Customer);
		salesOrderDBHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), Customer, CustomerDesc,
				commonFunc.getDataFromTestDataSheet("SO_General"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();// TODO
		addSalesOrderLineThroughExcelUD(SheetName, commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("SO_HSNCode"), Customer);
		clickPostButton();
		forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used to Verify The Sales Order Amendmnet Header Details With
	 * The Database
	 * 
	 * @author Harikrishna - RMITS
	 * @param Location
	 * @param Unit
	 * @param SOAPrefix
	 * @param CustomerID
	 * @param CustomerDesc
	 */
	public void verifySalesOrderAmendmentHeaderDetailsWithDB(String Location, String Unit, String SOAPrefix,
			String CustomerID, String CustomerDesc) {
		commonFunc.checkRefreshCellsDisappear();

		verifyAssertionBothValues(dbCommonFunctions.SOAHeaderDetails(VariableHandler.getSOAmdNumber(),
				commonFunc.getDataFromTestDataSheet("SOADBheaderLocationColumnName")), Location);
		logs.logging("INFO", "Header Location Is Verified With DataBase Values");

		verifyAssertionBothValues(dbCommonFunctions.SOAHeaderDetails(VariableHandler.getSOAmdNumber(),
				commonFunc.getDataFromTestDataSheet("SOADBheaderUnitColumnName")), Unit);
		logs.logging("INFO", "Header Unit Is Verified With DataBase Values");

		verifyAssertionBothValues(dbCommonFunctions.SOAHeaderDetails(VariableHandler.getSOAmdNumber(),
				commonFunc.getDataFromTestDataSheet("SOADBheaderDocPrefixColumnName")), SOAPrefix);
		logs.logging("INFO", "Header Sales Order Amendment Prefix Is Verified With DataBase Values");

		verifyAssertionBothValues(
				dbCommonFunctions.SOAHeaderDetails(VariableHandler.getSOAmdNumber(),
						commonFunc.getDataFromTestDataSheet("SOADBheaderDocNumberColumnName")),
				VariableHandler.getSOAmdNumber());
		logs.logging("INFO", "Header Sales Order Amendment Document Number Is Verified With DataBase Values");

//		verifyAssertionBothValues(
//				dbCommonFunctions.SOAHeaderDetails(VariableHandler.getSOAmdNumber(),
//						commonFunc.getDataFromTestDataSheet("SOADBheaderSODocNumberColumnName")),
//				VariableHandler.getSONumber());
//		logs.logging("INFO", "Header Sales Order Document Number Is Verified With DataBase Values");

		verifyAssertionBothValues(dbCommonFunctions.SOAHeaderDetails(VariableHandler.getSOAmdNumber(),
				commonFunc.getDataFromTestDataSheet("SOADBheaderCustomerIDColumnName")), CustomerID);
		logs.logging("INFO", "Header Customer ID Is Verified With DataBase Values");

		verifyAssertionBothValues(dbCommonFunctions.SOAHeaderDetails(VariableHandler.getSOAmdNumber(),
				commonFunc.getDataFromTestDataSheet("SOADBheaderCustomerDescColumnName")), CustomerDesc);
		logs.logging("INFO", "Header Customer Description Is Verified With DataBase Values");

		String Currency = webDB.getDomPropertyFromElement(SalesOrderLocators.SoAmdHeaderCurrencyField,
				ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));

//		verifyAssertionBothValues(dbCommonFunctions.SOAHeaderDetails(VariableHandler.getSOAmdNumber(),
//				commonFunc.getDataFromTestDataSheet("SOADBheaderCurrencyColumnName")), Currency);
//		logs.logging("INFO", "Header Currency Field Is Verified With DataBase Values");

		verifyAssertionBothValues(
				dbCommonFunctions.SOAHeaderDetails(VariableHandler.getSOAmdNumber(),
						commonFunc.getDataFromTestDataSheet("SOADBheaderStatusColumnName")),
				commonFunc.getDataFromTestDataSheet("Draft"));
		logs.logging("INFO", "Header Document Status Is Verified With DataBase Values");
	}

	/**
	 * This Method Is Used To Verify The Sales Order Amendment Line Wise Details
	 * With DataBase
	 * 
	 * @author Harikrishna - RMITS
	 * @param SOAmdSheetName
	 */
	public void verifySalesOrderAmendmentLineDetailsWithDB(String SOAmdSheetName) {
		commonFunc.checkRefreshCellsDisappear();
		SOMultipleline = excelUtils.readAllDataInExcel(Filepath1, SOAmdSheetName);
		for (int i = 4; i < SOMultipleline.length; i++) {
			String ITEM_ID = SOMultipleline[i][1];
			String SaleUOM = SOMultipleline[i][5];
			String OldQty = SOMultipleline[i][6];
			String OldUnitPrice = SOMultipleline[i][7];
			String OldTD = SOMultipleline[i][9];
			String OldSD = SOMultipleline[i][10];
			String OldCD = SOMultipleline[i][11];
			String OldTradeAssesableValue = SOMultipleline[i][12];
			String OldTradeDiscountAmount = SOMultipleline[i][13];
			String OldSpecialAssesableValue = SOMultipleline[i][14];
			String OldSpecialDiscountAmount = SOMultipleline[i][15];
			String OldCashAssesableValue = SOMultipleline[i][16];
			String OldCashDiscountAmount = SOMultipleline[i][17];
			String OldDiscountAmount = SOMultipleline[i][18];
			String OldDiscountAmtPerQty = SOMultipleline[i][19];
			String OldHSNCode = SOMultipleline[i][21];
			String OldCGST = SOMultipleline[i][23];
			String OldSGST = SOMultipleline[i][24];
			String OldIGST = SOMultipleline[i][25];
			String OldUTGST = SOMultipleline[i][26];
			String PromotionalType = SOMultipleline[i][39];
			String PriceClass = SOMultipleline[i][42];
			String NewQty = SOMultipleline[i][43];
			String NewUnitPrice = SOMultipleline[i][44];
			String NewTD = SOMultipleline[i][46];
			String NewSD = SOMultipleline[i][47];
			String NewCD = SOMultipleline[i][48];
			String NewTradeAssesableValue = SOMultipleline[i][49];
			String NewTradeDiscountAmount = SOMultipleline[i][50];
			String NewSpecialAssesableValue = SOMultipleline[i][51];
			String NewSpecialDiscountAmount = SOMultipleline[i][52];
			String NewCashAssesableValue = SOMultipleline[i][53];
			String NewCashDiscountAmount = SOMultipleline[i][54];
			String NewDiscountAmount = SOMultipleline[i][55];
			String NewDiscountAmtPerQty = SOMultipleline[i][56];
			String NewHSNCode = SOMultipleline[i][58];
			String NewCGST = SOMultipleline[i][60];
			String NewSGST = SOMultipleline[i][61];
			String NewIGST = SOMultipleline[i][62];
			String NewUTGST = SOMultipleline[i][63];
			if (!ITEM_ID.equals("")) {
				String SeqNo = String.valueOf(i - 3);
				commonFunc.checkRefreshCellsDisappear();

				verifyAssertionBothValues(dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SOADBLineItemIdColumnName")), ITEM_ID);
				logs.logging("INFO", "For This Line Item Id " + ITEM_ID + " Is Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SOADBLineSaleUOMColumnName")), SaleUOM);
				logs.logging("INFO", "For This Line Item " + ITEM_ID + " Sale UOM Is Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SOADBLineOldSOQtyColumnName")), OldQty);
				logs.logging("INFO", "For This Line Item " + ITEM_ID + " Old SO Qty Is Matched With DataBase Values");

				verifyAssertionBothValues(
						dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
								commonFunc.getDataFromTestDataSheet("SOADBLineOldSOUnitPriceColumnName")),
						OldUnitPrice);
				logs.logging("INFO",
						"For This Line Item " + ITEM_ID + " Old Unit Price Is Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SOADBLineNewSOQtyColumnName")), NewQty);
				logs.logging("INFO", "For This Line Item " + ITEM_ID + " New Qty Is Matched With DataBase Values");

				verifyAssertionBothValues(
						dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
								commonFunc.getDataFromTestDataSheet("SOADBLineNewSOUnitPriceColumnName")),
						NewUnitPrice);
				logs.logging("INFO",
						"For This Line Item " + ITEM_ID + " New Unit Price Is Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SOADBLineOldTDPColumnName")), OldTD);
				logs.logging("INFO", "For This Line Item " + ITEM_ID
						+ " Old Trade Discount Percentage Is Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SOADBLineOldSDPColumnName")), OldSD);
				logs.logging("INFO", "For This Line Item " + ITEM_ID
						+ " Old Special Discount Percentage Is Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SOADBLineOldCDPColumnName")), OldCD);
				logs.logging("INFO", "For This Line Item " + ITEM_ID
						+ " Old Cash Discount Percentage Is Matched With DataBase Values");

				verifyAssertionBothValues(
						dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
								commonFunc.getDataFromTestDataSheet("SOADBLineOldTAVColumnName")),
						OldTradeAssesableValue);
				logs.logging("INFO",
						"For This Line Item " + ITEM_ID + " Old Trade Assesable Value Is Matched With DataBase Values");

				verifyAssertionBothValues(
						dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
								commonFunc.getDataFromTestDataSheet("SOADBLineOldTDAColumnName")),
						OldTradeDiscountAmount);
				logs.logging("INFO",
						"For This Line Item " + ITEM_ID + " Old Trade Discount Amount Is Matched With DataBase Values");

				verifyAssertionBothValues(
						dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
								commonFunc.getDataFromTestDataSheet("SOADBLineOldSAVColumnName")),
						OldSpecialAssesableValue);
				logs.logging("INFO", "For This Line Item " + ITEM_ID
						+ " Old Special Assesable Value Is Matched With DataBase Values");

				verifyAssertionBothValues(
						dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
								commonFunc.getDataFromTestDataSheet("SOADBLineOldSDAColumnName")),
						OldSpecialDiscountAmount);
				logs.logging("INFO", "For This Line Item " + ITEM_ID
						+ " Old Special Discount Amount Is Matched With DataBase Values");

				verifyAssertionBothValues(
						dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
								commonFunc.getDataFromTestDataSheet("SOADBLineOldCAVColumnName")),
						OldCashAssesableValue);
				logs.logging("INFO",
						"For This Line Item " + ITEM_ID + " Old Cash Assesable Value Is Matched With DataBase Values");

				verifyAssertionBothValues(
						dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
								commonFunc.getDataFromTestDataSheet("SOADBLineOldCDAColumnName")),
						OldCashDiscountAmount);
				logs.logging("INFO",
						"For This Line Item " + ITEM_ID + " Old Cash Discount Amount Is Matched With DataBase Values");

				verifyAssertionBothValues(
						dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
								commonFunc.getDataFromTestDataSheet("SOADBLineOldDAPQColumnName")),
						OldDiscountAmtPerQty);
				logs.logging("INFO", "For This Line Item " + ITEM_ID
						+ " Old Discount Amount Per Qty Is Matched With DataBase Values");

//				verifyAssertionBothValues(dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
//						commonFunc.getDataFromTestDataSheet("SOADBLineOldHSNColumnName")), OldHSNCode);
//				logs.logging("INFO", "For This Line Item " + ITEM_ID + " Old HSN Code Is Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SOADBLineOldCGSTColumnName")), OldCGST);
				logs.logging("INFO",
						"For This Line Item " + ITEM_ID + " Old CGST Tax Amount Is Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SOADBLineOldSGSTColumnName")), OldSGST);
				logs.logging("INFO",
						"For This Line Item " + ITEM_ID + " Old SGST Tax Amount Is Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SOADBLineOldIGSTColumnName")), OldIGST);
				logs.logging("INFO",
						"For This Line Item " + ITEM_ID + " Old IGST Tax Amount Is Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SOADBLineOldUTGSTColumnName")), OldUTGST);
				logs.logging("INFO",
						"For This Line Item " + ITEM_ID + " Old UTGST Tax Amount Is Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SOADBLineNewTDPColumnName")), NewTD);
				logs.logging("INFO", "For This Line Item " + ITEM_ID
						+ " New Trade Discount Percentage Is Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SOADBLineNewSDPColumnName")), NewSD);
				logs.logging("INFO", "For This Line Item " + ITEM_ID
						+ " New Special Discount Percentage Is Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SOADBLineNewCDPColumnName")), NewCD);
				logs.logging("INFO", "For This Line Item " + ITEM_ID
						+ " New Cash Discount Percentage Is Matched With DataBase Values");

				verifyAssertionBothValues(
						dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
								commonFunc.getDataFromTestDataSheet("SOADBLineNewTAVColumnName")),
						NewTradeAssesableValue);
				logs.logging("INFO",
						"For This Line Item " + ITEM_ID + " New Trade Assesable Value Is Matched With DataBase Values");

				verifyAssertionBothValues(
						dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
								commonFunc.getDataFromTestDataSheet("SOADBLineNewTDAColumnName")),
						NewTradeDiscountAmount);
				logs.logging("INFO",
						"For This Line Item " + ITEM_ID + " New Trade Discount Amount Is Matched With DataBase Values");

				verifyAssertionBothValues(
						dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
								commonFunc.getDataFromTestDataSheet("SOADBLineNewSAVColumnName")),
						NewSpecialAssesableValue);
				logs.logging("INFO", "For This Line Item " + ITEM_ID
						+ " New Special Assesable Value Is Matched With DataBase Values");

				verifyAssertionBothValues(
						dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
								commonFunc.getDataFromTestDataSheet("SOADBLineNewSDAColumnName")),
						NewSpecialDiscountAmount);
				logs.logging("INFO", "For This Line Item " + ITEM_ID
						+ " New Special Discount Amount Is Matched With DataBase Values");

				verifyAssertionBothValues(
						dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
								commonFunc.getDataFromTestDataSheet("SOADBLineNewCAVColumnName")),
						NewCashAssesableValue);
				logs.logging("INFO",
						"For This Line Item " + ITEM_ID + " New Cash Assesable Value Is Matched With DataBase Values");

				verifyAssertionBothValues(
						dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
								commonFunc.getDataFromTestDataSheet("SOADBLineNewCDAColumnName")),
						NewCashDiscountAmount);
				logs.logging("INFO",
						"For This Line Item " + ITEM_ID + " New Cash Discount Amount Is Matched With DataBase Values");

				verifyAssertionBothValues(
						dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
								commonFunc.getDataFromTestDataSheet("SOADBLineNewDAPQColumnName")),
						NewDiscountAmtPerQty);
				logs.logging("INFO", "For This Line Item " + ITEM_ID
						+ " New Discount Amount Per Qty Is Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SOADBLineNewHSNColumnName")), NewHSNCode);
				logs.logging("INFO", "For This Line Item " + ITEM_ID + " New HSN Code Is Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SOADBLineNewCGSTColumnName")), NewCGST);
				logs.logging("INFO",
						"For This Line Item " + ITEM_ID + " New CGST Tax Amount Is Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SOADBLineNewSGSTColumnName")), NewSGST);
				logs.logging("INFO",
						"For This Line Item " + ITEM_ID + " New SGST Tax Amount Is Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SOADBLineNewIGSTColumnName")), NewIGST);
				logs.logging("INFO",
						"For This Line Item " + ITEM_ID + " New IGST Tax Amount Is Matched With DataBase Values");

				verifyAssertionBothValues(dbCommonFunctions.SOALineDetails(VariableHandler.getSOAmdNumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SOADBLineNewUTGSTColumnName")), NewUTGST);
				logs.logging("INFO",
						"For This Line Item " + ITEM_ID + " New UTGST Tax Amount Is Matched With DataBase Values");
			}
		}
	}

	/**
	 * This Method Is Used To Verify Sales Order Amendment Status EntryCompleted
	 * With Database
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void verifySalesOrderAmendmentDocEntryCompletedStatusWithDB() {
		verifyAssertionBothValues(
				dbCommonFunctions.SOAHeaderDetails(VariableHandler.getSOAmdNumber(),
						commonFunc.getDataFromTestDataSheet("SOADBheaderStatusColumnName")),
				commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"));
		logs.logging("INFO", "Verified Sales Order Amendment Document " + VariableHandler.getSOAmdNumber()
				+ "Status Is Entry Completed With DataBase Values ");
	}

	/**
	 * This Method Is Used To Verify Sales Order Amendment Status Approved With
	 * Database
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void verifySalesOrderAmendmentDocApprovedStatusWithDB() {
		verifyAssertionBothValues(
				dbCommonFunctions.SOAHeaderDetails(VariableHandler.getSOAmdNumber(),
						commonFunc.getDataFromTestDataSheet("SOADBheaderStatusColumnName")),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		logs.logging("INFO", "Verified Sales Order Amendment Document " + VariableHandler.getSOAmdNumber()
				+ "Status Is Approved With DataBase Values ");
	}

	/**
	 * This Method Is Used To Verify The So Amendment Total Amount With Database
	 * Values
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void verifySOATotalAmountWithExcelAndDB(String SheetName) {
		SOMultipleline = excelUtils.readAllDataInExcel(Filepath1, SheetName);
		for (int i = 4; i < SOMultipleline.length; i++) {
			String ITEM_ID = SOMultipleline[i][1];
			String SOA_Total_Amount = SOMultipleline[i][67];
			if (ITEM_ID.equals("")) {
				if (!SOA_Total_Amount.equals("")) {
					verifyAssertionBothValues(
							dbCommonFunctions.SOAHeaderDetails(VariableHandler.getSOAmdNumber(),
									commonFunc.getDataFromTestDataSheet("SOADBTotalAmountColumnName")),
							SOA_Total_Amount);
					logs.logging("INFO",
							"Expected Sales Order Amendment Total Amount Is Matched With DataBase Values For This Sales Order Amendment Document "
									+ VariableHandler.getSOAmdNumber());
				}
			}
		}
	}

	/**
	 * This Method Is used to Verify the Domestic SOGeneral With (SimilarStock And
	 * CustomerUOM) And (DisSimilar Stock And CustomerUOM)Sales Invoice
	 * 
	 * @author Harikrishna -RMITS
	 * @return boolean flag
	 */
	public boolean verifyDomesticSOGeneralSimilarStockAndCustomerUOMSalesInvoice() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		salesOrderDBHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("Customer"),
				commonFunc.getDataFromTestDataSheet("CustomerDesc"), commonFunc.getDataFromTestDataSheet("SO_General"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();
		SIaddSalesOrderLineThroughExcelUD(webDB.getDataFromProperties("SODomUserDefined"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SO_HSNCode"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		clickPostButton();
		forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		flag = commonFunc.logoutUser();
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("HodPassword"),
					"HariUsername");
			apprpagefunc.navigateToApprovals();
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			validateSalesOrderDetailsInDocApproval(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Customer"));
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			apprpagefunc.verifyApproveWithDocNumber(VariableHandler.getSONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Add The SO Lines Through Excel(User Defined)
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 * @param SOType
	 * @param HSNCODE
	 * @param Customer
	 */
	public void SIaddSalesOrderLineThroughExcelUD(String SheetName, String SOType, String HSNCODE, String Customer) {
		ArrayList<String> ArrayList = new ArrayList<String>();
		String[][] SOMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Sales STD PRICE.xlsx";
		SOMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 4; i < SOMultipleline.length; i++) {
			String ITEM_ID = SOMultipleline[i][1];
			String QUANTITY = SOMultipleline[i][6];
			String UNIT_COST = SOMultipleline[i][7];
			String Trade_Disc = SOMultipleline[i][9];
			String Spl_Disc = SOMultipleline[i][10];
			String Cash_Disc = SOMultipleline[i][11];
			String Promotional = SOMultipleline[i][36];
			String PriceClass = SOMultipleline[i][39];
			if (!ITEM_ID.equals("")) {
				SalesOrderToInvoiceStockQtyUpdateInExcelSI(i, ArrayList, SheetName);
				addItemsInSO(ITEM_ID);
				addQuantityAndUnitCost(QUANTITY, UNIT_COST);
				VariableHandler.setOrderStockQty(QUANTITY);
				chooseHsnCodeAndClickLineWiseSaveButton(HSNCODE);
				verifyItemCalculationWithLineAmount();
				if (webDB.verifyIsElementDisplayed(SalesOrderLocators.ShipSetDetails(i - 3), ElementType.Xpath)) {
					commonFunc.checkRefreshCellsDisappear();
					webDB.clickAnElement(SalesOrderLocators.ShipSetDetails(i - 3), ElementType.Xpath);
					VerifyShipsetDetails(ITEM_ID);
				}
				lineDetailVerification12(ITEM_ID, getTextCurrencyInHeader(), QUANTITY, i, Trade_Disc, Spl_Disc,
						Cash_Disc, SOType, Promotional, Filepath, SheetName, PriceClass, Customer);
				clickLineWiseDetailsSaveButton();
				verifyExpectedLineItemCalculationWithDBValues(i, ITEM_ID, SheetName);
			}
		}
		verifySOTotalAmountWithExcelAndDB(SheetName);
		VariableHandler.setExpectedStockQty2(ArrayList);
	}

	/**
	 * This Method Used For Reading DataBase Value For Before Stock Quantity
	 * 
	 * @author Vimal - RMITS
	 * @param i1
	 * @param ArrayList
	 * @param SheetName
	 */
	public void SalesOrderToInvoiceStockQtyUpdateInExcelSI(int i1, ArrayList<String> ArrayList, String SheetName) {
		String[][] SODiscountDetails = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Sales STD PRICE.xlsx";
		SODiscountDetails = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String ItemId = SODiscountDetails[i1][1];
			String WareHouse = SODiscountDetails[i1][35];
			if (!ItemId.equals("")) {
				String BeforeStockQty = dbCommonFunctions.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
				logs.logging("INFO", "Before Stock Qty For This Item " + ItemId + "Is : " + BeforeStockQty);
				VariableHandler.setBeforeStockQty(BeforeStockQty);
//				double stockQtyValue = Double.parseDouble(BeforeStockQty);
//				DecimalFormat df = new DecimalFormat("0.00");
//				String formattedNumber = df.format(stockQtyValue);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 32, BeforeStockQty);
				SalesOrderToSIExcelStockQtyFormulaEvaluatorSI(i1, ItemId, ArrayList, SheetName);
			}
		}
	}

	/**
	 * This Method Is used To get The Stock Qty From The Formula Cell
	 * 
	 * @author Harikrishna - RMITS
	 * @param i
	 * @param Item
	 * @param ArrayList
	 * @param SheetName
	 */
	public void SalesOrderToSIExcelStockQtyFormulaEvaluatorSI(int i, String Item, ArrayList<String> ArrayList,
			String SheetName) {
		try {
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
					+ "QA Scenario - Sales STD PRICE.xlsx";
			FileInputStream fis = new FileInputStream(new File(Filepath));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(SheetName);
			FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
			Row row = sheet.getRow(i); // Row 0 (first row)
			Cell cell = row.getCell(34); // Column 0 (first column, A1)
			if (cell.getCellType() == CellType.FORMULA) {
				CellValue cellValue = formulaEvaluator.evaluate(cell);
				if (cellValue.getCellType() == CellType.NUMERIC) {
					double cellNumericValue = cellValue.getNumberValue();
					if (cellNumericValue == Math.floor(cellNumericValue)) {
						// It's a whole number
						System.out.println("Formula result: " + (int) cellNumericValue);
						int ExpectedStockQtyInt = (int) cellNumericValue;
						String ExpectedStockQtyAsString = String.valueOf(ExpectedStockQtyInt);
						VariableHandler.setExpectedStockQty(ExpectedStockQtyAsString);
						logs.logging("INFO",
								"Expected Stock Qty After Posting the Sales Invoice Document For This Item " + Item
										+ " Is : " + VariableHandler.getExpectedStockQty());
						ArrayList.add(VariableHandler.getExpectedStockQty());
					} else {
						System.out.println("Formula result: " + cellNumericValue);
						String ExpectedStockQtyAsString = String.valueOf(cellNumericValue);
						VariableHandler.setExpectedStockQty(ExpectedStockQtyAsString);
						logs.logging("INFO",
								"Expected Stock Qty After Posting the Sales Invoice Document For This Item " + Item
										+ " Is : " + VariableHandler.getExpectedStockQty());
						ArrayList.add(VariableHandler.getExpectedStockQty());
					}
				}
			} else {
				System.out.println("Cell does not contain a formula.");
			}
			workbook.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(flag);
		}
	}

	/**
	 * This Method Is Used To verify The Expected Stock Qty With Excel Values After
	 * Invoice The Document
	 * 
	 * @author Harikrishna RMITS
	 */
	public void SIverifyExpectedStockQtyWithDBValuesAfterInvoice(String SheetName) {
		String[][] SOItemDetails = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Sales STD PRICE.xlsx";
		SOItemDetails = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 4; i < SOItemDetails.length; i++) {
			String ItemId = SOItemDetails[i][1];
			String WareHouse = SOItemDetails[i][35];
			if (!ItemId.equals("")) {
				String AfterStockQty = dbCommonFunctions.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
				logs.logging("INFO",
						"After Invoiced Actual Stock Qty For This Item " + ItemId + "Is : " + AfterStockQty);
				verifyAssertionBothValues(AfterStockQty, VariableHandler.getExpectedStockQty2().get(i - 4));
				logs.logging("INFO", "Verified Expected Stock Qty For This Item " + ItemId
						+ " With Database Values After Invoiced The Document");
			}
		}
	}

	/**
	 * This Method Is used to Verify the Export SOGeneral With (SimilarStock And
	 * CustomerUOM) And (DisSimilar Stock And CustomerUOM)
	 * 
	 * @author Harikrishna - RMITS
	 * @return boolean flag
	 */
	public boolean verifyExportSOGeneralSimilarStockAndCustomerUOMSalesInvoice() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();
		SIaddSalesOrderLineThroughExcelUD(webDB.getDataFromProperties("SOExpUserDefined"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SO_HSNCode"),
				commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		clickPostButton();
		forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		flag = commonFunc.logoutUser();
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.validCommonLogin(System.getProperty("HariUsername"), System.getProperty("HodPassword"),
					"HariUsername");
			apprpagefunc.navigateToApprovals();
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			validateSalesOrderDetailsInDocApproval(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			apprpagefunc.verifyApproveWithDocNumber(VariableHandler.getSONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * This Method Used For Reading DataBase Value For Before Stock Quantity For
	 * Scarp
	 * 
	 * @author Vimal - RMITS
	 * @param i1
	 * @param ArrayList
	 * @param SheetName
	 */
	public void ScrapStockQtyUpdateInExcelSI(int i1, ArrayList<String> ArrayList, String SheetName) {
		String[][] SODiscountDetails = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";
		SODiscountDetails = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String ItemId = SODiscountDetails[i1][38];
			String WareHouse = SODiscountDetails[i1][39];
			if (!ItemId.equals("")) {
				String BeforeStockQty = dbCommonFunctions.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
				logs.logging("INFO", "Before Stock Qty For This Item " + ItemId + "Is : " + BeforeStockQty);
				VariableHandler.setBeforeStockQty(BeforeStockQty);
//				double stockQtyValue = Double.parseDouble(BeforeStockQty);
//				DecimalFormat df = new DecimalFormat("0.00");
//				String formattedNumber = df.format(stockQtyValue);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 40, BeforeStockQty);
				ScrapExcelStockQtyFormulaEvaluatorSI(i1, ItemId, ArrayList, SheetName);
			}
		}
	}

	/**
	 * This Method Is used To get The Scrap Stock Qty From The Formula Cell
	 * 
	 * @author Vimal - RMITS
	 * @param i
	 * @param Item
	 * @param ArrayList
	 * @param SheetName
	 */
	public void ScrapExcelStockQtyFormulaEvaluatorSI(int i, String Item, ArrayList<String> ArrayList,
			String SheetName) {
		try {
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
					+ "DBSales.xlsx";
			FileInputStream fis = new FileInputStream(new File(Filepath));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(SheetName);
			FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
			Row row = sheet.getRow(i); // Row 0 (first row)
			Cell cell = row.getCell(41); // Column 0 (first column, A1)
			if (cell.getCellType() == CellType.FORMULA) {
				CellValue cellValue = formulaEvaluator.evaluate(cell);
				if (cellValue.getCellType() == CellType.NUMERIC) {
					double cellNumericValue = cellValue.getNumberValue();
					if (cellNumericValue == Math.floor(cellNumericValue)) {
						// It's a whole number
						System.out.println("Formula result: " + (int) cellNumericValue);
						int ExpectedStockQtyInt = (int) cellNumericValue;
						String ExpectedStockQtyAsString = String.valueOf(ExpectedStockQtyInt);
						VariableHandler.setExpectedStockQty(ExpectedStockQtyAsString);
						logs.logging("INFO", "Expected Stock Qty After Posting the MRV Document For This Item " + Item
								+ " Is : " + VariableHandler.getExpectedStockQty());
						ArrayList.add(VariableHandler.getExpectedStockQty());
					} else {
						System.out.println("Formula result: " + cellNumericValue);
						String ExpectedStockQtyAsString = String.valueOf(cellNumericValue);
						VariableHandler.setExpectedStockQty(ExpectedStockQtyAsString);
						logs.logging("INFO", "Expected Stock Qty After Posting the MRV Document For This Item " + Item
								+ " Is : " + VariableHandler.getExpectedStockQty());
						ArrayList.add(VariableHandler.getExpectedStockQty());
					}
				}
			} else {
				System.out.println("Cell does not contain a formula.");
			}
			workbook.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(flag);
		}
	}

	/**
	 * This Method Used For After Scrap The Document Expected Stk Qty
	 * 
	 * @author Vimal - RMITS
	 * @param SheetName
	 */
	public void verifyScrapAfterExpectedReturnStockQtyWithDBValuesAfterInvoice(String SheetName) {
		String[][] SOItemDetails = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "DBSales.xlsx";
		SOItemDetails = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 4; i < SOItemDetails.length; i++) {
			String ItemId = SOItemDetails[i][38];
			String WareHouse = SOItemDetails[i][39];
			String AfterRtnStockQty = SOItemDetails[i][41];
			if (!ItemId.equals("")) {
				String AfterStockQty = dbCommonFunctions.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
				logs.logging("INFO", "After MRV Actual Stock Qty For This Item " + ItemId + "Is : " + AfterStockQty);
				verifyAssertionBothValues(AfterStockQty, AfterRtnStockQty);
				logs.logging("INFO", "Verified MRV Expected Stock Qty For This Item " + ItemId
						+ " With Database Values After MRV The Document");
			}
		}
	}

	/**
	 * This Method Is Used To verify The Sales Order Line Details After Amend The SO
	 * Document
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void verifySOLineDetailsAfterAmendment(String SheetName) {
		commonFunc.checkRefreshCellsDisappear();
		String[][] SOLineDetails = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		SOLineDetails = excelUtils.readAllDataInExcel(Filepath1, SheetName);
		for (int i = 4; i < SOLineDetails.length; i++) {

			String Item = SOLineDetails[i][1];
			String NewQty = SOLineDetails[i][43];
			String NewUnitPrice = SOLineDetails[i][44];
			String NewTD = SOLineDetails[i][46];
			String NewSD = SOLineDetails[i][47];
			String NewCD = SOLineDetails[i][48];
			String NewTradeAssesableValue = SOLineDetails[i][49];
			String NewTradeDiscountAmount = SOLineDetails[i][50];
			String NewSpecialAssesableValue = SOLineDetails[i][51];
			String NewSpecialDiscountAmount = SOLineDetails[i][52];
			String NewCashAssesableValue = SOLineDetails[i][53];
			String NewCashDiscountAmount = SOLineDetails[i][54];
			String NewDiscountAmount = SOLineDetails[i][55];
			String NewDiscountAmtPerQty = SOLineDetails[i][56];
			String GrossAmount = SOLineDetails[i][57];
			String NewCGST = SOLineDetails[i][60];
			String NewSGST = SOLineDetails[i][61];
			String NewIGST = SOLineDetails[i][62];
			String NewUTGST = SOLineDetails[i][63];
			String TaxAmount = SOLineDetails[i][66];
			String NetAmount = SOLineDetails[i][67];

			if (!Item.equals("")) {
				String SeqNo = String.valueOf(i - 3);
				commonFunc.checkRefreshCellsDisappear();
				verifyAssertionBothValues(dbCommonFunctions.SOLineDetailsAfterSOA(VariableHandler.getSONumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SODBItemIDColumnName")), Item);
				logs.logging("INFO", "After SO Amendment Sales Order Expected Line Item ID " + Item
						+ " Is Matched With Database values");

				verifyAssertionBothValues(dbCommonFunctions.SOLineDetailsAfterSOA(VariableHandler.getSONumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SODBQtyColumnName")), NewQty);
				logs.logging("INFO", "After SO Amendment Sales Order Expected Line Qty " + NewQty + " For This Item "
						+ Item + " Is Matched With Database values");

				verifyAssertionBothValues(dbCommonFunctions.SOLineDetailsAfterSOA(VariableHandler.getSONumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SODBUnitPriceColumnName")), NewUnitPrice);
				logs.logging("INFO", "After SO Amendment Sales Order Expected Unit Price " + NewUnitPrice
						+ " For This Item " + Item + " Is Matched With Database values");

				verifyAssertionBothValues(dbCommonFunctions.SOLineDetailsAfterSOA(VariableHandler.getSONumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SODBTradeDiscountPercentageColumnName")), NewTD);
				logs.logging("INFO", "After SO Amendment Sales Order Expected Trade Discount Percentage " + NewTD
						+ " For This Item " + Item + " Is Matched With Database values");

				verifyAssertionBothValues(dbCommonFunctions.SOLineDetailsAfterSOA(VariableHandler.getSONumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SODBSpecialDiscountPercentageColumnName")), NewSD);
				logs.logging("INFO", "After SO Amendment Sales Order Expected Special Discount Percentage " + NewSD
						+ " For This Item " + Item + " Is Matched With Database values");

				verifyAssertionBothValues(dbCommonFunctions.SOLineDetailsAfterSOA(VariableHandler.getSONumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SODBCashDiscountPercentageColumnName")), NewCD);
				logs.logging("INFO", "After SO Amendment Sales Order Expected Cash Discount Percentage " + NewCD
						+ " For This Item " + Item + " Is Matched With Database values");

				verifyAssertionBothValues(
						dbCommonFunctions.SOLineDetailsAfterSOA(VariableHandler.getSONumber(), SeqNo,
								commonFunc.getDataFromTestDataSheet("SODBTradeAssesableValueColumnName")),
						NewTradeAssesableValue);
				logs.logging("INFO", "After SO Amendment Sales Order Expected Trade Assesable Value "
						+ NewTradeAssesableValue + " For This Item " + Item + " Is Matched With Database values");

				verifyAssertionBothValues(
						dbCommonFunctions.SOLineDetailsAfterSOA(VariableHandler.getSONumber(), SeqNo,
								commonFunc.getDataFromTestDataSheet("SODBTradeDiscountAmountColumnName")),
						NewTradeDiscountAmount);
				logs.logging("INFO", "After SO Amendment Sales Order Expected Trade Discount Amount "
						+ NewTradeDiscountAmount + " For This Item " + Item + " Is Matched With Database values");

				verifyAssertionBothValues(
						dbCommonFunctions.SOLineDetailsAfterSOA(VariableHandler.getSONumber(), SeqNo,
								commonFunc.getDataFromTestDataSheet("SODBSpecialAssesableValueColumnName")),
						NewSpecialAssesableValue);
				logs.logging("INFO", "After SO Amendment Sales Order Expected Special Assesable Value "
						+ NewSpecialAssesableValue + " For This Item " + Item + " Is Matched With Database values");

				verifyAssertionBothValues(
						dbCommonFunctions.SOLineDetailsAfterSOA(VariableHandler.getSONumber(), SeqNo,
								commonFunc.getDataFromTestDataSheet("SODBSpecialDiscountAmountColumnName")),
						NewSpecialDiscountAmount);
				logs.logging("INFO", "After SO Amendment Sales Order Expected Special Discount Amount "
						+ NewSpecialDiscountAmount + " For This Item " + Item + " Is Matched With Database values");

				verifyAssertionBothValues(
						dbCommonFunctions.SOLineDetailsAfterSOA(VariableHandler.getSONumber(), SeqNo,
								commonFunc.getDataFromTestDataSheet("SODBCashAssesableValueColumnName")),
						NewCashAssesableValue);
				logs.logging("INFO", "After SO Amendment Sales Order Expected Cash Assesable Value "
						+ NewCashAssesableValue + " For This Item " + Item + " Is Matched With Database values");

				verifyAssertionBothValues(
						dbCommonFunctions.SOLineDetailsAfterSOA(VariableHandler.getSONumber(), SeqNo,
								commonFunc.getDataFromTestDataSheet("SODBCashDiscountAmountColumnName")),
						NewCashDiscountAmount);
				logs.logging("INFO", "After SO Amendment Sales Order Expected Cash Discount Amount "
						+ NewCashDiscountAmount + " For This Item " + Item + " Is Matched With Database values");

				verifyAssertionBothValues(
						dbCommonFunctions.SOLineDetailsAfterSOA(VariableHandler.getSONumber(), SeqNo,
								commonFunc.getDataFromTestDataSheet("SODBTotalDiscountAmountColumnName")),
						NewDiscountAmount);
				logs.logging("INFO", "After SO Amendment Sales Order Expected Total Discount Amount "
						+ NewDiscountAmount + " For This Item " + Item + " Is Matched With Database values");

				verifyAssertionBothValues(
						dbCommonFunctions.SOLineDetailsAfterSOA(VariableHandler.getSONumber(), SeqNo,
								commonFunc.getDataFromTestDataSheet("SODBDiscountAmountPerQtyColumnName")),
						NewDiscountAmtPerQty);
				logs.logging("INFO", "After SO Amendment Sales Order Expected Total Discount Amount Per Qty "
						+ NewDiscountAmtPerQty + " For This Item " + Item + " Is Matched With Database values");

				verifyAssertionBothValues(dbCommonFunctions.SOLineDetailsAfterSOA(VariableHandler.getSONumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SODBGrossAmountColumnName")), GrossAmount);
				logs.logging("INFO", "After SO Amendment Sales Order Expected Gross Amount " + GrossAmount
						+ " For This Item " + Item + " Is Matched With Database values");

				verifyAssertionBothValues(dbCommonFunctions.SOLineDetailsAfterSOA(VariableHandler.getSONumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SODBCGSTColumnName")), NewCGST);
				logs.logging("INFO", "After SO Amendment Sales Order Expected CGST Amount " + NewCGST
						+ " For This Item " + Item + " Is Matched With Database values");

				verifyAssertionBothValues(dbCommonFunctions.SOLineDetailsAfterSOA(VariableHandler.getSONumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SODBSGSTColumnName")), NewSGST);
				logs.logging("INFO", "After SO Amendment Sales Order Expected SGST Amount " + NewSGST
						+ " For This Item " + Item + " Is Matched With Database values");

				verifyAssertionBothValues(dbCommonFunctions.SOLineDetailsAfterSOA(VariableHandler.getSONumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SODBIGSTColumnName")), NewIGST);
				logs.logging("INFO", "After SO Amendment Sales Order Expected IGST Amount " + NewIGST
						+ " For This Item " + Item + " Is Matched With Database values");

				verifyAssertionBothValues(dbCommonFunctions.SOLineDetailsAfterSOA(VariableHandler.getSONumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SODBUTGSTColumnName")), NewUTGST);
				logs.logging("INFO", "After SO Amendment Sales Order Expected UTGST Amount " + NewUTGST
						+ " For This Item " + Item + " Is Matched With Database values");

				verifyAssertionBothValues(dbCommonFunctions.SOLineDetailsAfterSOA(VariableHandler.getSONumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SODBTaxAmountColumnName")), TaxAmount);
				logs.logging("INFO", "After SO Amendment Sales Order Expected Tax Amount " + TaxAmount
						+ " For This Item " + Item + " Is Matched With Database values");

				verifyAssertionBothValues(dbCommonFunctions.SOLineDetailsAfterSOA(VariableHandler.getSONumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SODBNetAmountColumnName")), NetAmount);
				logs.logging("INFO", "After SO Amendment Sales Order Expected Net Amount " + NetAmount
						+ " For This Item " + Item + " Is Matched With Database values");
			}
		}
	}

	/**
	 * This Method Is Used To Verify The SO Total Amount With DataBase After
	 * Amendment The SO
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void verifySOTotalAmountAfterAmendment(String SheetName) {
		String[][] SOTotalAmount = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Sales STD PRICE.xlsx";
		SOTotalAmount = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 4; i < SOTotalAmount.length; i++) {
			String ITEM_ID = SOTotalAmount[i][1];
			String SO_Total_Amount = SOTotalAmount[i][67];
			if (ITEM_ID.equals("")) {
				if (!SO_Total_Amount.equals("")) {
					verifyAssertionBothValues(dbCommonFunctions.SOHeaderDetails(VariableHandler.getSONumber(),
							commonFunc.getDataFromTestDataSheet("SODBTotalAmountColumnName")), SO_Total_Amount);
					logs.logging("INFO",
							"After SO Amendment Expected Sales Order Total Amount Is Matched With DataBase Values For This Sales Order Document "
									+ VariableHandler.getSONumber());
				}
			}
		}
	}

	/**
	 * This Method IS Used To Click the MultiCloseShort Bulk Auto In Pending Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickMultiCloseshortOfSoAmendmentPendingScreen() {
		flag = webDB.waitForElement(SalesOrderLocators.SOAmendmentPendingButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.SOAmendmentPendingButton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.MultiSoAmendment, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesOrderLocators.MultiSoAmendment, ElementType.Xpath);
				flag = webDB.waitForElement(SalesOrderLocators.MultiCloseShortAddButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Navigated To Bulk CloseShort - Auto Transaction Screen");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click the Add Button in The Multi CloseShort Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickAddButtonInMultiCloseShortInSOAmendment() {
		flag = webDB.waitForElement(SalesOrderLocators.MultiCloseShortAddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.MultiCloseShortAddButton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.MultiCloseShortLocationLov, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				logs.logging("INFO", "Multi CloseShort Add Button IS Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add The MultiCloseShort Header Details
	 * 
	 * @author Harikrishna - RMITS
	 * @param Location
	 * @param Customer
	 * @param DocPfx
	 */
	public void addMultiCloseShortHeaderDetails(String Location, String Customer, String DocPfx) {
		flag = webDB.waitForElement(SalesOrderLocators.MultiCloseShortLocationLov, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.MultiCloseShortLocationLov, Location);
			webDB.pressTabKey();
			commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.MultiCloseShortCustomerLov, Customer);
			webDB.pressTabKey();
			commonFunc.checkRefreshCellsDisappear();
			String GetText = webDB.getDomPropertyFromElement(SalesOrderLocators.MultiCloseShortDocPrefixField,
					ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
			if (!GetText.equalsIgnoreCase(DocPfx)) {
				commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.MultiCloseShortDocPrefix, DocPfx);
				webDB.pressTabKey();
			}
			flag = webDB.waitForElement(SalesOrderLocators.MultiCloseShortHeaderWiseSaveButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesOrderLocators.MultiCloseShortHeaderWiseSaveButton, ElementType.Xpath);
				flag = webDB.waitForElement(SalesOrderLocators.MultiCloseShortPendingSoButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Multi CloseShort Header Details Created Sucessfully");
					commonFunc.checkRefreshCellsDisappear();
					String DocNum = webDB.getDomPropertyFromElement(SalesOrderLocators.MultiCloseShortDocNumber,
							ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
					VariableHandler.setSOMultiCloseShortDocNo(DocNum);
					logs.logging("INFO", "Sales Order Multi CloseShort Document Number Is : "
							+ VariableHandler.getSOMultiCloseShortDocNo());
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Pending SO Button In The Multi CloseShort
	 * Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickPendingSOButtonInMultiCloseShort() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(SalesOrderLocators.MultiCloseShortPendingSoButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.MultiCloseShortPendingSoButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (flag) {
				for (int i = 0; i < MultipleSO.size(); i++) {
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(SalesOrderLocators.SearchBoxContainer1, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Search Box IS Displayed");
						webDB.clickAnElement(SalesOrderLocators.SearchBoxContainer1, ElementType.Xpath);
						flag = webDB.waitForElement(SalesOrderLocators.SearchBox1, ElementType.Xpath);
						if (flag) {
							webDB.clearTextField(SalesOrderLocators.SearchBox1, ElementType.Xpath);
							logs.logging("INFO", "Clear Text Search Box");
							webDB.sendTextToAnElement(SalesOrderLocators.SearchBox1,
									VariableHandler.getMultipleSoDocs().get(i), ElementType.Xpath);
							logs.logging("INFO",
									"Entered Document Number Is: " + VariableHandler.getMultipleSoDocs().get(i));
							commonFunc.hardWait(500);
							webDB.pressTabKey();
							commonFunc.hardWait(500);
							webDB.pressEnterKey();
//							flag = webDB.waitForElement(SalesOrderLocators.MultiCloseShortGOButton, ElementType.Xpath);
//							if (flag) {
//								webDB.clickAnElement(SalesOrderLocators.MultiCloseShortGOButton, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(1000);
							List<WebElement> RowCount = webDB.getDriver()
									.findElements(By.xpath(SalesOrderLocators.MultiCloseShortCheckBoxInputField));
							for (int j = 1; j <= RowCount.size(); j++) {
								webDB.clickAnElement(SalesOrderLocators.selectCheckBoxInMultiCloseShort(
										VariableHandler.getMultipleSoDocs().get(i), j), ElementType.Xpath);
								flag = webDB.isElementSelected(SalesOrderLocators.selectCheckBoxInMultiCloseShort(
										VariableHandler.getMultipleSoDocs().get(i), j), ElementType.Xpath);
								if (flag == true)
									logs.logging("INFO",
											"For This Sales Order Document "
													+ VariableHandler.getMultipleSoDocs().get(i) + " Line Number " + j
													+ " Is Selected Sucessfully ");
								commonFunc.checkRefreshCellsDisappear();
								if (!flag) {
									webDB.moveToElement(SalesOrderLocators.selectCheckBoxInMultiCloseShort(

											VariableHandler.getMultipleSoDocs().get(i), j), ElementType.Xpath);
									webDB.clickAnElement(SalesOrderLocators.selectCheckBoxInMultiCloseShort(

											VariableHandler.getMultipleSoDocs().get(i), j), ElementType.Xpath);
									commonFunc.checkRefreshCellsDisappear();
								}
							}
//								flag = webDB.waitForElement(SalesOrderLocators.MultiCloseShortFilterRefreshButton,
//										ElementType.Xpath);
//								if (flag) {
//									webDB.moveToElement(SalesOrderLocators.MultiCloseShortFilterRefreshButton,
//											ElementType.Xpath);
//									webDB.javaScriptClickAnElement(
//											SalesOrderLocators.MultiCloseShortFilterRefreshButton, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(1500);
						}
					}
				}
			}
		}
	}
//		}
//	}

	/**
	 * This Method Is Used To Click The Post Button In the Sales Order Multi
	 * CloseShort Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickPostButtonInSOMultiCloseShort() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(SalesOrderLocators.MultiCloseShortPostButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.MultiCloseShortPostButton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.WorkApprovalFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Post Button Clicked Sucessfully");
			}
		}
	}

	/**
	 * This Method Is Used To CloseShort Multiple Sales Order
	 * 
	 * @author Harikrishna - RMITS
	 * 
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean closeshortMultipleSalesOrder(String Customer, String CustomerDesc, String SOSheetName)
			throws IOException {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		for (int i = 0; i < 2; i++) {
			MultipleSalesOrder(Customer, CustomerDesc, SOSheetName);
		}
		VariableHandler.setMultipleSoDocs(MultipleSO);
		System.out.println(VariableHandler.getMultipleSoDocs().get(0));
		System.out.println(VariableHandler.getMultipleSoDocs().get(1));
//		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
//				"AdminUsername");
		commonFunc.navigateSalesOrderAmendmentPage();
		clickMultiCloseshortOfSoAmendmentPendingScreen();
		clickAddButtonInMultiCloseShortInSOAmendment();
		addMultiCloseShortHeaderDetails(commonFunc.getDataFromTestDataSheet("Loc_100"), Customer,
				commonFunc.getDataFromTestDataSheet("MultiCloseShortPfx"));
		clickPendingSOButtonInMultiCloseShort();
		clickPostButtonInSOMultiCloseShort();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			commonFunc.hardWait(1000);
			flag = commonFunc.logoutUser();
//			verifySalesOrderAmendmentMultiDocEntryCompletedStatusWithDB();
			if (flag) {
				commonFunc.hardWait(1000);
				commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"),
						VariableHandler.getSOMultiCloseShortDocNo(), webDB.getDataFromProperties("HodEmpId"),
						commonFunc.getDataFromTestDataSheet("Unit_100"), System.getProperty("HariUsername"));
//				verifySalesOrderAmendmentMultiDocApprovedStatusWithDB();
			}
		}
		return flag;
	}

	/**
	 * This Method Is used to Verify the Domestic SOGeneral With (SimilarStock And
	 * CustomerUOM) And (DisSimilar Stock And CustomerUOM)
	 * 
	 * @author Harikrishna -RMITS
	 * @return boolean flag
	 */
	public boolean verifyDomesticSOLocationChange() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		salesOrderDBHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("Customer"),
				commonFunc.getDataFromTestDataSheet("CustomerDesc"), commonFunc.getDataFromTestDataSheet("SO_General"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();
		addSalesOrderLineThroughExcelUD(webDB.getDataFromProperties("SODomUserDefined"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SO_HSNCode"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used to Set The Credit Limit For the Customer
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void setCreditLimitForCustomer() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateCustomerMasterScreen();
		commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.CustomerIDLovButton, "CL-100001");
		flag = webDB.waitForElement(SalesOrderLocators.CustomerMasterSearchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.CustomerMasterSearchButton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.CustomerDrillDown, ElementType.CssSelector);
			if (flag) {
				webDB.clickAnElement(SalesOrderLocators.CustomerDrillDown, ElementType.CssSelector);
				flag = webDB.waitForElement(SalesOrderLocators.CustomerUpdateButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Drill Down Sucessfully To That customer");
					clickUpdateButtonInCustomerMaster();
					addCreditLimitForExistingCustomer();
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click the Update Button In the Customer Master Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickUpdateButtonInCustomerMaster() {
		flag = webDB.waitForElement(SalesOrderLocators.CustomerUpdateButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.CustomerUpdateButton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.CustomerUpdDetailsFrame, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesOrderLocators.CustomerUpdDetailsFrame, ElementType.Xpath);
				flag = webDB.waitForElement(SalesOrderLocators.CustomerUpdateCreditLimitButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SalesOrderLocators.CustomerUpdateCreditLimitButton, ElementType.Xpath);
					flag = webDB.waitForElement(SalesOrderLocators.CustomerCLAddButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Update Credit Limit Button Is Clicked Sucessfully");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used to Add the CreditLimit For The existing Customer
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void addCreditLimitForExistingCustomer() {
		flag = webDB.waitForElement(SalesOrderLocators.CustomerCLAddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.CustomerCLAddButton, ElementType.Xpath);
			webDB.switchToFrame(SalesOrderLocators.UpdateCustomerDetailsFrame, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.UpdateCustomerDetailsSaveButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Customer Credit Limit Button Is Clicked Sucessfully");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used to Add The Effective From And Effective To Date
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void addEffectiveFromAndToDateInCL() {
		flag = webDB.waitForElement(SalesOrderLocators.UpdCLEffFromDateInputField, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(SalesOrderLocators.UpdCLEffFromDateInputField, "01-04-2025", ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify The SO Line Status As Draft With DB
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void verifySalesOrderLinesStatusDraftWithDB() {
//		String[][] SOMultipleline = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
//		ExcelUtils excelUtils = new ExcelUtils();
//		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
//				+ "QA Scenario - Sales STD PRICE.xlsx";
//		SOMultipleline = excelUtils.readAllDataInExcel(Filepath, SheetName);
//		for (int i = 4; i < SOMultipleline.length; i++) {
		String SODocumentStatus = dbCommonFunctions.SOLineDetails(VariableHandler.getSONumber(),
				commonFunc.getDataFromTestDataSheet("SODBLineWiseStatusColumn"));
		verifyAssertionBothValues(SODocumentStatus, commonFunc.getDataFromTestDataSheet("DBLineDraftStatus"));
		logs.logging("INFO", "Verified Sales Order Lines Status Is " + SODocumentStatus
				+ " Against This Sales Order Number " + VariableHandler.getSONumber());
	}
//	}

	/**
	 * This Method Is Used To Verify The SO Line Status As Cancelled With DB
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void verifySalesOrderLinesStatusCancelledWithDB(String SheetName) {
		String[][] SOLineDetails = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		SOLineDetails = excelUtils.readAllDataInExcel(Filepath1, SheetName);
		for (int i = 4; i < SOLineDetails.length; i++) {
			String Item = SOLineDetails[i][1];
			if (!Item.equals("")) {
				String SeqNo = String.valueOf(i - 3);

				String SODocumentStatus = dbCommonFunctions.SOLineDetailsAfterSOA(VariableHandler.getSONumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SODBLineWiseStatusColumn"));
				verifyAssertionBothValues(SODocumentStatus,
						commonFunc.getDataFromTestDataSheet("DBLineCancelledStatus"));
				logs.logging("INFO", "Verified Sales Order Lines Status Is " + SODocumentStatus
						+ " Against This Sales Order Number " + VariableHandler.getSONumber() + "Line Number " + SeqNo);
			}
		}
	}

	/**
	 * This Method Is Used To Verify The SO Line Status As CloseShort With DB
	 * 
	 * @author Harikrishna -RMITS
	 */
	public void verifySalesOrderLinesStatusCloseShortWithDB(String SheetName) {
		String[][] SOLineDetails = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		SOLineDetails = excelUtils.readAllDataInExcel(Filepath1, SheetName);
		for (int i = 4; i < SOLineDetails.length; i++) {
			String Item = SOLineDetails[i][1];
			if (!Item.equals("")) {
				String SeqNo = String.valueOf(i - 3);

				String SODocumentStatus = dbCommonFunctions.SOLineDetailsAfterSOA(VariableHandler.getSONumber(), SeqNo,
						commonFunc.getDataFromTestDataSheet("SODBLineWiseStatusColumn"));
				verifyAssertionBothValues(SODocumentStatus, commonFunc.getDataFromTestDataSheet("CloseShortStatus"));
				logs.logging("INFO",
						"After Sales Order Amendment Verified Sales Order Lines Status Is " + SODocumentStatus
								+ " Against This Sales Order Number " + VariableHandler.getSONumber() + "Line Number "
								+ SeqNo);
			}
		}
	}

	/**
	 * This method used to select CPC code in Header and Click Save Header Icon
	 *
	 * @author Sakthi-GWL
	 * @param CPCCode
	 */
	public void chooseHeaderCPCCodeAndSaveHeader(String CPCCode) {
		flag = webDB.waitForClickElement(SalesOrderLocators.HeaderCPCcodeLovBtn, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.HeaderCPCcodeLovBtn, CPCCode);
			logs.logging("INFO", "Header CPC Code Selected: " + CPCCode);
			flag = webDB.waitForElement(SalesOrderLocators.SalesOrderHeaderSaveButton, ElementType.Xpath);
			if (flag) {
				webDB.moveToElement(SalesOrderLocators.SalesOrderHeaderSaveButton, ElementType.Xpath);
				webDB.clickAnElement(SalesOrderLocators.SalesOrderHeaderSaveButton, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Header Save Icon Clicked Successfully");
					if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Validate The SOM Controls Of Allow SO And Allow CS
	 * 
	 * @author Harikrishna - RMITS
	 * 
	 */
	public void checkSOMControl(String ScreenName) {
		String SO = dbCommonFunctions.SOMControl(commonFunc.getDataFromTestDataSheet("DBBU"),
				commonFunc.getDataFromTestDataSheet("DBSOMCNTRLALLOWSO"));
		logs.logging("INFO", "Allow Sales Order Flag Is " + SO);
		String CS = dbCommonFunctions.SOMControl(commonFunc.getDataFromTestDataSheet("DBBU"),
				commonFunc.getDataFromTestDataSheet("DBSOMCNTRLALLOWCS"));
		logs.logging("INFO", "Allow Customer Schedule Flag Is " + CS);
		flag = webDB.waitForElement(SalesOrderLocators.SOSearchButton, ElementType.Xpath);
		if (flag) {
			if (SO.equals("Y") && CS.equals("N")) {
				webDB.clickAnElement(SalesOrderLocators.AddButtonSalesOrder, ElementType.Xpath);
				flag = webDB.waitForElement(SalesOrderLocators.SOFrame, ElementType.Xpath);
				if (flag) {
					MatReqFunc.FrameSwitching(SalesOrderLocators.SOFrame);
					logs.logging("INFO", "Sales Order Add Screen Opened Successfully");
				} else {
					logs.logging("INFO", "Failed To Open Sales Order Add Screen");
				}
			} else if (SO.equals("N") && CS.equals("Y")) {
				webDB.clickAnElement(SalesOrderLocators.AddButtonSalesOrder, ElementType.Xpath);
				flag = webDB.waitForElement(CustomerScheduleLocators.CSFrame, ElementType.Xpath);
				if (flag) {
					MatReqFunc.FrameSwitching(CustomerScheduleLocators.CSFrame);
					logs.logging("INFO", "Customer Schedule Add Screen Opened Successfully");
				} else {
					logs.logging("INFO", "Failed To Open Customer Schedule Add Screen");
				}
			} else if (SO.equals("N") && CS.equals("N")) {
				flag = webDB.isElementNotDisplayed(SalesOrderLocators.AddButtonSalesOrder, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Add Button Is Not Getting Visible");
				} else {
					logs.logging("INFO", "Add Button Is Getting Visible");
				}
			} else if (SO.equals("Y") && CS.equals("Y")) {
				webDB.clickAnElement(SalesOrderLocators.AddButtonSalesOrder, ElementType.Xpath);
				flag = webDB.waitForElement(CustomerScheduleLocators.CSButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Create SO / CS Sub Screen Is Opened Successfully");
					if (ScreenName.equals("SO")) {
						clickSalesOrderButton();
					} else {
						CSFunc.clickCustomerScheduleButton();
					}
				} else {
					logs.logging("INFO", "Failed To open SO / CS Sub Screen");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click The Sales Order Button In The Add Sub Screen
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void clickSalesOrderButton() {
		flag = webDB.waitForElement(SalesOrderLocators.SOButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.SOButton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.SOFrame, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sales Order Button Is clicked Sucessfully");
				MatReqFunc.FrameSwitching(SalesOrderLocators.SOFrame);
				flag = webDB.waitForElement(SalesOrderLocators.SOLocationField, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Navigated To Sales Order Add Screen");
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify The Total Sales Order Amount With DB After
	 * Including The Charges
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void verifyExpectedTotalValueWithCharges(String SheetName) {
		commonFunc.checkRefreshCellsDisappear();
		SOMultipleline = excelUtils.readAllDataInExcel(Filepath1, SheetName);
		for (int i = 10; i < SOMultipleline.length; i++) {
			String CHARGEITEM_ID = SOMultipleline[i][3];
			String Charges_Amount = SOMultipleline[i][16];
			if (CHARGEITEM_ID.equals("")) {
				if (!Charges_Amount.equals("")) {
					verifyAssertionBothValues(dbCommonFunctions.SOHeaderDetails(VariableHandler.getSONumber(),
							commonFunc.getDataFromTestDataSheet("SODBTotalAmountColumnName")), Charges_Amount);
					logs.logging("INFO",
							"Overall Sales Order Amount Is Matched After Including The Charges Against This Order "
									+ VariableHandler.getSONumber());
				}
			}
		}
	}

	/**
	 * This Method Is Used To Verify The SO Total Amount With DataBase After
	 * Amendment The SO
	 * 
	 * @author Harikrishna - RMITS
	 * @param SheetName
	 */
	public void verifySOTotalAmountAfterAmendmentWithCharges(String SheetName) {
		String[][] SOTotalAmount = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Sales STD PRICE.xlsx";
		SOTotalAmount = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = 10; i < SOTotalAmount.length; i++) {
			String ITEM_ID = SOTotalAmount[i][3];
			String SO_Total_Amount = SOTotalAmount[i][24];
			if (ITEM_ID.equals("")) {
				if (!SO_Total_Amount.equals("")) {
					verifyAssertionBothValues(dbCommonFunctions.SOHeaderDetails(VariableHandler.getSONumber(),
							commonFunc.getDataFromTestDataSheet("SODBTotalAmountColumnName")), SO_Total_Amount);
					logs.logging("INFO",
							"After SO Amendment Expected Sales Order Total Amount Is Matched With DataBase Values For This Sales Order Document "
									+ VariableHandler.getSONumber());
				}
			}
		}
	}

	/**
	 * This Method Is used to SO for MRP
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifySalesOrderCreationForMRP() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		salesOrderDBHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("Customer"),
				commonFunc.getDataFromTestDataSheet("CustomerDesc"), commonFunc.getDataFromTestDataSheet("SO_General"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();
		addSalesOrderLineThroughExcelUD(webDB.getDataFromProperties("SOforMRP"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SI_HSNCode"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To Verify Sales Order Amendment Status EntryCompleted
	 * With Database
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void verifySalesOrderAmendmentMultiDocEntryCompletedStatusWithDB() {
		verifyAssertionBothValues(
				dbCommonFunctions.SOAHeaderDetails(VariableHandler.getSOMultiCloseShortDocNo(),
						commonFunc.getDataFromTestDataSheet("SOADBheaderStatusColumnName")),
				commonFunc.getDataFromTestDataSheet("EntryCompletedStatus"));
		logs.logging("INFO", "Verified Sales Order Amendment Multi Document "
				+ VariableHandler.getSOMultiCloseShortDocNo() + "Status Is Entry Completed With DataBase Values ");
	}

	/**
	 * This Method Is Used To Verify Sales Order Amendment Status Approved With
	 * Database
	 * 
	 * @author Harikrishna - RMITS
	 */
	public void verifySalesOrderAmendmentMultiDocApprovedStatusWithDB() {
		verifyAssertionBothValues(
				dbCommonFunctions.SOAHeaderDetails(VariableHandler.getSOMultiCloseShortDocNo(),
						commonFunc.getDataFromTestDataSheet("SOADBheaderStatusColumnName")),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		logs.logging("INFO", "Verified Sales Order Amendment Multi Document "
				+ VariableHandler.getSOMultiCloseShortDocNo() + "Status Is Approved With DataBase Values ");
	}

	/**
	 * @author Harikrishna -RMITS This Method Is Used To Verify Advance Sales Order
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean advanceSalesOrder() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		salesOrderDBHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("Customer"),
				commonFunc.getDataFromTestDataSheet("CustomerDesc"), commonFunc.getDataFromTestDataSheet("SO_General"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();
		changePaymentTermInSOHeader(commonFunc.getDataFromTestDataSheet("AdvancePaymentTerm"));
		addSalesOrderLineThroughExcelUD(webDB.getDataFromProperties("SOAdvance"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SO_HSNCode"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		navigateToGeneralTab();
		verifyIncludeAdvAmtFlagIsChecked();
		navigateToLineTab();
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
				getAdvReceiptDocumentFromDataBase();
//				verifyCreationOfPaymentReceiptsReceivables(VariableHandler.getAdvReceiptDocNumber(),
//						commonFunc.getDataFromTestDataSheet("BankName"),
//						commonFunc.getDataFromTestDataSheet("Reference Number"));
			}
		}
		return flag;
	}

	/**
	 * @author Harikrishna -RMITS This Method Is Used To Verify Advance Sales Order
	 * @return boolean flag
	 * @throws IOException
	 */
	public boolean advanceSalesOrderExport() throws IOException {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Export_Customer_Name"));
		salesOrderDBHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"),
				commonFunc.getDataFromTestDataSheet("SO_Export_Customer"),
				commonFunc.getDataFromTestDataSheet("Export_Customer_Name"),
				commonFunc.getDataFromTestDataSheet("SO_General"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();
		changePaymentTermInSOHeader(commonFunc.getDataFromTestDataSheet("AdvancePaymentTerm"));
		addSalesOrderLineThroughExcelUD(webDB.getDataFromProperties("SOAdvanceExp"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SO_HSNCode"),
				commonFunc.getDataFromTestDataSheet("Export_Customer_Name"));
		navigateToGeneralTab();
		verifyIncludeAdvAmtFlagIsChecked();
		navigateToLineTab();
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
				getAdvReceiptDocumentFromDataBase();
//				verifyCreationOfPaymentReceiptsReceivables(VariableHandler.getAdvReceiptDocNumber(),
//						commonFunc.getDataFromTestDataSheet("BankName"),
//						commonFunc.getDataFromTestDataSheet("Reference Number"));
			}
		}
		return flag;
	}

	/**
	 * This Method Is Used To navigate To the Line Details Page (2) and Add BOM No
	 * 
	 * @author Sakthi-GWL
	 */
	public void navigateToLineDetails2AndAddBOMNo(String BOMNo) {
		flag = webDB.waitForElement(SalesOrderLocators.LinedetailsTab2, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.LinedetailsTab2, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.CustItemID, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Sucessfully Navigated To Line Details 2");
				commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.LineDetailBOMNoLov, BOMNo);
				webDB.switchToFrame(SalesOrderLocators.LineDetailsFrame, ElementType.Xpath);
				webDB.waitForElement(SalesOrderLocators.LineWiseDetailsSaveIcon, ElementType.Xpath);
				webDB.clickAnElement(SalesOrderLocators.LineWiseDetailsSaveIcon, ElementType.Xpath);
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(SalesOrderLocators.SalesOrderHeaderSaveButton, ElementType.Xpath);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is used to SO for MRP & Prod Order
	 * 
	 * @author Sakthi-GWL
	 * @return boolean flag
	 */
	public boolean verifySalesOrderCreationForMRPAndProdOrder(int ReqQty, String BOMNo) {
		excelUtils.writeDataInExcel(Filepath1, webDB.getDataFromProperties("ProdOrderWithSO"), 4, 6,
				String.valueOf(ReqQty));
		excelUtils.writeDataInExcel(Filepath1, webDB.getDataFromProperties("ProdOrderWithSO"), 4, 43, BOMNo);
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				System.getProperty("AdminUsername"));
		commonFunc.navigateSalesOrderPage();
		checkSOMControl(webDB.getDataFromProperties("So"));
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		salesOrderDBHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("Customer"),
				commonFunc.getDataFromTestDataSheet("CustomerDesc"), commonFunc.getDataFromTestDataSheet("SO_General"));
		checkSOHeaderDetailsDataBaseValuesWithUIValues();
		addSalesOrderLineThroughExcelUD(webDB.getDataFromProperties("ProdOrderWithSO"),
				commonFunc.getDataFromTestDataSheet("SO_General"), commonFunc.getDataFromTestDataSheet("SI_HSNCode"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		navigateToLineDetailsPage1(1);
		navigateToLineDetails2AndAddBOMNo(BOMNo);
		clickPostButton();
		flag = forwardForApproval(commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
		if (flag) {
			verifySalesOrderDocumentStatusEntryCompletedWithDB();
			commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSONumber(),
					webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					System.getProperty("HariUsername"));
			flag = commonFunc.logoutUser();
			if (flag) {
				verifySalesOrderDocumentStatusApprovedWithDB();
			}
		}
		return flag;
	}

	/**
	 * This Method Used For Reading DataBase Value For Before Stock Quantity
	 * 
	 * @author Vimal - RMITS
	 * @param i1
	 * @param ArrayList
	 * @param SheetName
	 */
	public void StockQtyUpdateInExcelSI(int i1, ArrayList<String> ArrayList, String SheetName, String FileName) {
		String[][] SODiscountDetails = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
		SODiscountDetails = excelUtils.readAllDataInExcel(Filepath, SheetName);
		for (int i = i1; i <= i1; i++) {
			String ItemId = SODiscountDetails[i1][1];
			String WareHouse = SODiscountDetails[i1][34];
			if (!ItemId.equals("")) {
				String BeforeStockQty = dbCommonFunctions.StockQuery1(WareHouse, ItemId,
						commonFunc.getDataFromTestDataSheet("StockQtyDBColumnName"));
				logs.logging("INFO", "Before Stock Qty For This Item " + ItemId + "Is : " + BeforeStockQty);
//				double stockQtyValue = Double.parseDouble(BeforeStockQty);
//				DecimalFormat df = new DecimalFormat("0.00");
//				String formattedNumber = df.format(stockQtyValue);
				excelUtils.writeDataInExcel(Filepath, SheetName, i, 32, BeforeStockQty);
				ExcelStockQtyFormulaEvaluatorSI(i1, ItemId, ArrayList, SheetName, FileName);
			}
		}
	}

	/**
	 * This Method Is used To get The Stock Qty From The Formula Cell
	 * 
	 * @author Harikrishna - RMITS
	 * @param i
	 * @param Item
	 * @param ArrayList
	 * @param SheetName
	 */
	public void ExcelStockQtyFormulaEvaluatorSI(int i, String Item, ArrayList<String> ArrayList, String SheetName,
			String FileName) {
		try {
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + FileName;
			FileInputStream fis = new FileInputStream(new File(Filepath));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(SheetName);
			FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
			Row row = sheet.getRow(i); // Row 0 (first row)
			Cell cell = row.getCell(33); // Column 0 (first column, A1)
			if (cell.getCellType() == CellType.FORMULA) {
				CellValue cellValue = formulaEvaluator.evaluate(cell);
				if (cellValue.getCellType() == CellType.NUMERIC) {
					double cellNumericValue = cellValue.getNumberValue();
					if (cellNumericValue == Math.floor(cellNumericValue)) {
						// It's a whole number
						System.out.println("Formula result: " + (int) cellNumericValue);
						int ExpectedStockQtyInt = (int) cellNumericValue;
						String ExpectedStockQtyAsString = String.valueOf(ExpectedStockQtyInt);
						VariableHandler.setExpectedStockQty(ExpectedStockQtyAsString);
						logs.logging("INFO",
								"Expected Stock Qty After Posting the Sales Invoice Document For This Item " + Item
										+ " Is : " + VariableHandler.getExpectedStockQty());
						ArrayList.add(VariableHandler.getExpectedStockQty());
					} else {
						System.out.println("Formula result: " + cellNumericValue);
						String ExpectedStockQtyAsString = String.valueOf(cellNumericValue);
						VariableHandler.setExpectedStockQty(ExpectedStockQtyAsString);
						logs.logging("INFO",
								"Expected Stock Qty After Posting the Sales Invoice Document For This Item " + Item
										+ " Is : " + VariableHandler.getExpectedStockQty());
						ArrayList.add(VariableHandler.getExpectedStockQty());
					}
				}
			} else {
				System.out.println("Cell does not contain a formula.");
			}
			workbook.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(flag);
		}
	}

	/**
	 * This Method is Used to Create a Sales OrderHeader Records
	 * 
	 * @author Sakthi-GWL
	 * @param Location
	 * @param Unit
	 * @param SubVoucherType
	 * @param Customer
	 */
	public void createSalesOrderWithHeaderDetails(String Location, String Unit, String SubVoucherType,
			String Customer) {
		String LocationValue = webDB.getDomPropertyFromElement(SalesOrderLocators.SOLocationField, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		if (!LocationValue.equalsIgnoreCase(Location)) {
			commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.LocationLOV, Location);
			commonFunc.checkRefreshCellsDisappear();
			FrameSwitchingInSOCreation();
		}
		String SalesVoucherType = webDB.getDomPropertyFromElement(SalesOrderLocators.SOSubVoucherType,
				ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
		if (!SalesVoucherType.equalsIgnoreCase(SubVoucherType)) {
			commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.SalesSubVoucherTypeLOV, SubVoucherType);
			commonFunc.checkRefreshCellsDisappear();
			FrameSwitchingInSOCreation();
		}
		commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.CustomerLov, Customer);
		commonFunc.checkRefreshCellsDisappear();
		FrameSwitchingInSOCreation();
		clickTickIcon();
		validateLoactionInSOHeader(Location);
		validateUnitInSOHeader(Unit);
	}

	/**
	 * This Method Is Used To navigate to SO Or CS
	 * 
	 * @author Sakthi-GWL
	 * 
	 */
	public void navigateToSalesOrderOrCustomerSchedule(String ScreenName) {
		flag = webDB.waitForElement(SalesOrderLocators.AddButtonSalesOrder, ElementType.Xpath);
		if (flag) {
			flag = webDB.clickAnElement(SalesOrderLocators.AddButtonSalesOrder, ElementType.Xpath);
			logs.logging("INFO", "Create SO / CS Sub Screen Is Opened Successfully");
			if (ScreenName.equals("SO")) {
				clickSalesOrderButton();
			} else {
				CSFunc.clickCustomerScheduleButton();
			}
		}
		Assert.assertTrue(flag);
	}

	public void enterDiscount(String TD, String SD, String CD, String Customer, String Item, String PriceClass,
			String FilePath, String SheetName, int i) {
		String PriceBasis = VariableHandler.getSOPriceBasis();
		flag = webDB.waitForElement(SalesOrderLocators.DiscountTable(i - 3), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.DiscountTable(i - 3), ElementType.Xpath);
			webDB.waitForElement(SalesOrderLocators.SODiscountFrameName, ElementType.Xpath);
			webDB.switchToFrame(SalesOrderLocators.SODiscountLineDetailsFrame, ElementType.Xpath);
			if ((!"Open Order".equals(PriceBasis)) && (!"Price List".equals(PriceBasis))) {
				enterSODiscountDetails(SalesOrderLocators.SOTradeDiscountpercentageField, TD);
//			commonFunc.checkRefreshCellsDisappear();
			} else {
				flag = webDB.waitForElement(SalesOrderLocators.SOTradeDiscountpercentageField, ElementType.Xpath);
				if (flag) {
					String TradeDiscountPercentage = webDB.getDomPropertyFromElement(
							SalesOrderLocators.SOTradeDiscountpercentageField, ElementType.Xpath,
							commonFunc.getDataFromTestDataSheet("Value"));
					logs.logging("INFO", "Get Text Trade Discount Percentage is " + TradeDiscountPercentage);
					String DBTradeDiscount = dbCommonFunctions.CurrentRateContractQuery(Customer, Item, PriceClass,
							commonFunc.getDataFromTestDataSheet("SODBRateContractDiscountPerColumnName"));
					excelUtils.writeDataInExcel(FilePath, SheetName, i, 9, TradeDiscountPercentage);
				}
			}
			enterSODiscountDetails(SalesOrderLocators.SOSpecialDiscountpercentageField, SD);
//		commonFunc.checkRefreshCellsDisappear();
			enterSODiscountDetails(SalesOrderLocators.SOCashDiscountpercentageField, CD);
//		commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(SalesOrderLocators.LineWiseDetailsSaveIcon, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesOrderLocators.LineWiseDetailsSaveIcon, ElementType.Xpath);
				commonFunc.checkRefreshCellsDisappear();
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(SalesOrderLocators.PostButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Discount Details Saved Successfully");
				}
			}
		}
	}
}
