package commonfunctions.supplychainmanagement;

/**
 * @author Durai
 * @company Roadmap May 02, 2025
 */
import static org.testng.Assert.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.ApprovalPageLocators;
import commonlocators.supplychainmanagement.PricelistPageLocators;
import commonlocators.supplychainmanagement.RfqLocators;
import commonlocators.supplychainmanagement.SsoPageLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class SsoPageFunctions {

	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	private static CommonFunctions commonFunc = new CommonFunctions();
	private static PurchaseRequestFunctions PrFunc = new PurchaseRequestFunctions();
	private static PricelistPageFunctions PrlFunc = new PricelistPageFunctions();
	public ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	private static DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();
	String Today = null;
	String tomorrowDate = null;

	public SsoPageFunctions() {
		LocalDate date = LocalDate.now();
		LocalDateTime date2 = LocalDateTime.now().plusDays(1);
		DayOfWeek day = date.getDayOfWeek();
		if (day == DayOfWeek.SATURDAY) {
			date = date.minusDays(2);
			date2 = date2.minusDays(1);
		} else if (day == DayOfWeek.SUNDAY) {
			date = date.minusDays(3);
			date2 = date2.minusDays(3);
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Today = date.format(formatter).toUpperCase();
		tomorrowDate = date2.format(formatter).toUpperCase();
		System.out.println("Today Date: " + Today);
		System.out.println("Tomorrow Date: " + tomorrowDate);
	}

	/**
	 * This Methods Is Used To Verify Creation Of Item
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfItemforPriceList() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage();
		verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
		waitingForApproval();
		commonFunc.approveItemIdDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getItemName(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of DisSimilar Item
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfItemforPriceListDisSimilarItem() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateItemPage();
		verifySearchOfItemDis(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
		waitingForApproval();
		commonFunc.approveItemIdDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getItemName(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of Open Purchase Order Domestic
	 * Supplier
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfOpenPurchaseOrderDomesticSupplier() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateOpenPoOrderPage();
		verifyCreationOfOPO();
		verifyAddingFillDetailsOfOPO(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Opo_SupId_ID"));
		verifyClickingOpoCreate();
		verifyCreatingItemForOpo(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("SO_Special_Disc"),
				commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		commonFunc.hardWait(3000);
		openPurchaseOrderHeaderValidation();
		openPurchaseOrderHeaderSupplierValidation();
		openPurchaseOrderHeaderItemValidation();
		openPurchaseOrderHeaderCostValidation();
		openPurchaseOrderHeaderDiscountValidation();
		openPurchaseOrderHeaderHsnValidation();
		verifyClickingOpoPost();
		waitingForApprovalOpo();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getOPODocNum(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of Open Purchase Order Import
	 * Supplier
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfOpenPurchaseOrderImportSupplier() {
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateOpenPoOrderPage();
		verifyCreationOfOPO();
		verifyAddingFillDetailsOfOPO(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ImpSupplier_ID"));
		verifyClickingOpoCreate();
		verifyCreatingItemForOpo(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("SO_Special_Disc"),
				commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		commonFunc.hardWait(3000);
		commonFunc.checkRefreshCellsDisappear();
		openPurchaseOrderHeaderValidation();
		openPurchaseOrderHeaderImportSupplierValidation();
		openPurchaseOrderHeaderItemValidation();
		openPurchaseOrderHeaderCostValidation();
		openPurchaseOrderHeaderDiscountValidation();
		openPurchaseOrderHeaderHsnValidation();
		verifyClickingOpoPost();
		waitingForApprovalOpo();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getOPODocNum(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of Supplier Schedule Order Domestic
	 * Supplier
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfSsoDomesticSupplierSimilarUom() {
		verifyCreationOfItemforPriceList();
		verifyCreationOfOpenPurchaseOrderDomesticSupplier();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSupplierScheduleOrderPage();
		verifyCreationOfItemforSso(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Opo_SupId"), commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("WH_To"));
		verifyClickingSsoCreate();
		verifyGetTextDocNum();
		supplierScheduleHeaderValidationD();
		validateLocationInSsoHeader(commonFunc.getDataFromTestDataSheet("Loc_100"));
		supplierScheduleHeaderSupplierValidationD();
		validateSupplierInSsoHeader(commonFunc.getDataFromTestDataSheet("Opo_SupId"));
		supplierScheduleHeaderWarehouseValidationD();
		validateWarehouseInSsoHeader(commonFunc.getDataFromTestDataSheet("WH_To"));
		verifyFetchOpenPoButton();
		verifyFetchOpenPoDocNumberEnetring();
		verifyLineDetails(commonFunc.getDataFromTestDataSheet("Firm_Qty"),
				commonFunc.getDataFromTestDataSheet("Tent_Qty"), commonFunc.getDataFromTestDataSheet("Firm_DateFrom"),
				commonFunc.getDataFromTestDataSheet("Firm_DateTo"));
		verifySsoLineSave();
		verifySsoGenerateSchedule();
		verifySsoPost();
		waitingForApprovalOpo();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSsoDocNum(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of Supplier Schedule Order Domestic
	 * Supplier For Gate Entry
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfSsoDomesticSupplierSimilarUomGate() {
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSupplierScheduleOrderPage();
		verifyCreationOfItemforSso(commonFunc.getDataFromTestDataSheet("L49"),
				commonFunc.getDataFromTestDataSheet("Opo_SupId"), commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("RAW MATERIAL WH -49-4"));
		verifyClickingSsoCreate();
		verifyGetTextDocNum();
		supplierScheduleHeaderValidation();
		validateLocationInSsoHeader(commonFunc.getDataFromTestDataSheet("L49ID"));
		supplierScheduleHeaderSupplierValidation();
		validateSupplierInSsoHeader(commonFunc.getDataFromTestDataSheet("Opo_SupId"));
		supplierScheduleHeaderWarehouseValidation();
		validateWarehouseInSsoHeader(commonFunc.getDataFromTestDataSheet("RAW MATERIAL WH -49-4"));
		verifyFetchOpenPoButton();
		verifyFetchOpenPoDocNumberEnetring();
		verifyLineDetails(commonFunc.getDataFromTestDataSheet("Firm_Qty"),
				commonFunc.getDataFromTestDataSheet("Tent_Qty"), commonFunc.getDataFromTestDataSheet("Firm_DateFrom"),
				commonFunc.getDataFromTestDataSheet("Firm_DateTo"));
		verifySsoLineSave();
		verifySsoGenerateSchedule();
		verifySsoPost();
		waitingForApprovalOpo();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSsoDocNum(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of Supplier Schedule Order Import
	 * Supplier Similar UOM
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfSsoImportSupplierSimilarUom() {
		verifyCreationOfItemforPriceList();
		verifyCreationOfOpenPurchaseOrderImportSupplier();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSupplierScheduleOrderPage();
		verifyCreationOfItemforSso(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ImpSupplier_ID"),
				commonFunc.getDataFromTestDataSheet("PR_Department"), commonFunc.getDataFromTestDataSheet("WH_To"));
		verifyClickingSsoCreate();
		verifyGetTextDocNum();
		supplierScheduleHeaderValidationUnit1();
		supplierScheduleHeaderImportSupplierValidation();
		supplierScheduleHeaderWarehouseValidationUnit1();
		verifyFetchOpenPoButton();
		verifyFetchOpenPoDocNumberEnetring();
		verifyLineDetails(commonFunc.getDataFromTestDataSheet("Firm_Qty"),
				commonFunc.getDataFromTestDataSheet("Tent_Qty"), commonFunc.getDataFromTestDataSheet("Firm_DateFrom"),
				commonFunc.getDataFromTestDataSheet("Firm_DateTo"));
		verifySsoLineSave();
		verifySsoGenerateSchedule();
		verifySsoPost();
		waitingForApprovalOpo();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSsoDocNum(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of Supplier Schedule Order Domestic
	 * Supplier DisSimilar UOM
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfSsoDomesticSupplierDisSimilarUom() {
		verifyCreationOfItemforPriceListDisSimilarItem();
		verifyCreationOfOpenPurchaseOrderDomesticSupplier();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSupplierScheduleOrderPage();
		verifyCreationOfItemforSso(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Opo_SupId"), commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("WH_To"));
		verifyClickingSsoCreate();
		verifyGetTextDocNum();
		supplierScheduleHeaderValidationUnit1();
		validateLocationInSsoHeader(commonFunc.getDataFromTestDataSheet("Loc_100"));
		supplierScheduleHeaderSupplierValidation();
		validateSupplierInSsoHeader(commonFunc.getDataFromTestDataSheet("Opo_SupId"));
		supplierScheduleHeaderWarehouseValidationUnit1();
		validateWarehouseInSsoHeader(commonFunc.getDataFromTestDataSheet("WH_To"));
		verifyFetchOpenPoButton();
		verifyFetchOpenPoDocNumberEnetring();
		verifyLineDetails(commonFunc.getDataFromTestDataSheet("Firm_Qty"),
				commonFunc.getDataFromTestDataSheet("Tent_Qty"), commonFunc.getDataFromTestDataSheet("Firm_DateFrom"),
				commonFunc.getDataFromTestDataSheet("Firm_DateTo"));
		verifySsoLineSave();
		verifySsoGenerateSchedule();
		verifySsoPost();
		waitingForApprovalOpo();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSsoDocNum(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of Supplier Schedule Order Import
	 * Supplier DisSimilar UOM
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfSsoImportSupplierDisSimilarUom() {
		verifyCreationOfItemforPriceListDisSimilarItem();
		verifyCreationOfOpenPurchaseOrderImportSupplier();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSupplierScheduleOrderPage();
		verifyCreationOfItemforSso(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("ImpSupplier_ID"),
				commonFunc.getDataFromTestDataSheet("PR_Department"), commonFunc.getDataFromTestDataSheet("WH_To"));
		verifyClickingSsoCreate();
		verifyGetTextDocNum();
		commonFunc.hardWait(2000);
		supplierScheduleHeaderValidationUnit1();
		supplierScheduleHeaderImportSupplierValidation();
		supplierScheduleHeaderWarehouseValidationUnit1();
		verifyFetchOpenPoButton();
		verifyFetchOpenPoDocNumberEnetring();
		verifyLineDetails(commonFunc.getDataFromTestDataSheet("Firm_Qty"),
				commonFunc.getDataFromTestDataSheet("Tent_Qty"), commonFunc.getDataFromTestDataSheet("Firm_DateFrom"),
				commonFunc.getDataFromTestDataSheet("Firm_DateTo"));
		verifySsoLineSave();
		verifySsoGenerateSchedule();
		verifySsoPost();
		waitingForApprovalOpo();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getSsoDocNum(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To cancel Supplier Schedule Order Lines
	 * 
	 * @author Durai-RMITS
	 */
	public void cancelSupplierScheduleOrderLines() {
		verifyCreationOfSsoDomesticSupplierSimilarUomCancel();
	}

	/**
	 * This Methods Is Used To cancel Supplier Schedule Order Document
	 * 
	 * @author Durai-RMITS
	 */
	public void cancelSupplierScheduleOrderDocument() {
		verifyCreationOfSsoDomesticSupplierSimilarUomCancelHeader();
	}

	/**
	 * This Methods Is Used To Verify Creation Of Supplier Schedule Order Domestic
	 * Supplier Similar UOM Cancel
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfSsoDomesticSupplierSimilarUomCancel() {
		verifyCreationOfItemforPriceList();
		verifyCreationOfOpenPurchaseOrderDomesticSupplier();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSupplierScheduleOrderPage();
		verifyCreationOfItemforSso(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Opo_SupId"), commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("WH_To"));
		verifyClickingSsoCreate();
		verifyGetTextDocNum();
		supplierScheduleHeaderValidationUnit1();
		validateLocationInSsoHeader(commonFunc.getDataFromTestDataSheet("Loc_100"));
		supplierScheduleHeaderSupplierValidation();
		validateSupplierInSsoHeader(commonFunc.getDataFromTestDataSheet("Opo_SupId"));
		supplierScheduleHeaderWarehouseValidationUnit1();
		validateWarehouseInSsoHeader(commonFunc.getDataFromTestDataSheet("WH_To"));
		verifyFetchOpenPoButton();
		verifyFetchOpenPoDocNumberEnetring();
		verifyLineDetails(commonFunc.getDataFromTestDataSheet("Firm_Qty"),
				commonFunc.getDataFromTestDataSheet("Tent_Qty"), commonFunc.getDataFromTestDataSheet("Firm_DateFrom"),
				commonFunc.getDataFromTestDataSheet("Firm_DateTo"));
		verifySsoLineSave();
		verifySsoGenerateSchedule();
		verifySsoCancelLines();
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of Supplier Schedule Order Domestic
	 * Supplier Similar UOM Cancel Header
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfSsoDomesticSupplierSimilarUomCancelHeader() {
		verifyCreationOfItemforPriceList();
		verifyCreationOfOpenPurchaseOrderDomesticSupplier();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigateSupplierScheduleOrderPage();
		verifyCreationOfItemforSso(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Opo_SupId"), commonFunc.getDataFromTestDataSheet("PR_Department"),
				commonFunc.getDataFromTestDataSheet("WH_To"));
		verifyClickingSsoCreate();
		verifyGetTextDocNum();
		supplierScheduleHeaderValidationUnit1();
		validateLocationInSsoHeader(commonFunc.getDataFromTestDataSheet("Loc_100"));
		supplierScheduleHeaderSupplierValidation();
		validateSupplierInSsoHeader(commonFunc.getDataFromTestDataSheet("Opo_SupId"));
		supplierScheduleHeaderWarehouseValidationUnit1();
		validateWarehouseInSsoHeader(commonFunc.getDataFromTestDataSheet("WH_To"));
		verifyFetchOpenPoButton();
		verifyFetchOpenPoDocNumberEnetring();
		verifyLineDetails(commonFunc.getDataFromTestDataSheet("Firm_Qty"),
				commonFunc.getDataFromTestDataSheet("Tent_Qty"), commonFunc.getDataFromTestDataSheet("Firm_DateFrom"),
				commonFunc.getDataFromTestDataSheet("Firm_DateTo"));
		verifySsoLineSave();
		verifySsoGenerateSchedule();
		verifySsoCancelHeader();
		flag = commonFunc.logoutUser();
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of Add Item For Supplier Schedule
	 * Order
	 * 
	 * @author Durai-RMITS
	 * 
	 */
	public void verifyCreationOfItemforSso(String Location, String Supplier, String Dept, String WH) {
		flag = webDB.waitForElement(SsoPageLocators.SsoAddBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SsoPageLocators.SsoAddBtn, ElementType.Xpath);
			logs.logging("INFO", "Add Button is clicked");
			flag = webDB.waitForElement(SsoPageLocators.PendingSso, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SsoPageLocators.PendingSso, ElementType.Xpath);
				logs.logging("INFO", "Sso Button is clicked");
				flag = webDB.waitForElement(SsoPageLocators.SsoFrame, ElementType.Xpath);
				if (flag) {
					webDB.switchToFrame(SsoPageLocators.SsoFrame, ElementType.Xpath);
					logs.logging("INFO", "Switched to Frame");
					flag = webDB.waitForElement(SsoPageLocators.SsoLocLov, ElementType.Xpath);
					if (flag) {
						commonFunc.searchAndSelectValueInFilters(SsoPageLocators.SsoLocLov, Location);
						logs.logging("INFO", "Location is Entered:" + Location);
						flag = webDB.waitForElement(SsoPageLocators.SsoFrame, ElementType.Xpath);
						if (flag) {
							webDB.switchToFrame(SsoPageLocators.SsoFrame, ElementType.Xpath);
							logs.logging("INFO", "Switched to Frame");
							flag = webDB.waitForElement(SsoPageLocators.SsoSupplierLov, ElementType.Xpath);
							if (flag) {
								commonFunc.searchAndSelectValueInFilters(SsoPageLocators.SsoSupplierLov, Supplier);
								logs.logging("INFO", "Supplier is Entered:" + Supplier);
								flag = webDB.waitForElement(SsoPageLocators.SsoFrame, ElementType.Xpath);
								if (flag) {
									webDB.switchToFrame(SsoPageLocators.SsoFrame, ElementType.Xpath);
									logs.logging("INFO", "Switched to Frame");
									flag = webDB.waitForElement(SsoPageLocators.SsoDepartmentLov, ElementType.Xpath);
									if (flag) {
										commonFunc.searchAndSelectValueInFilters(SsoPageLocators.SsoDepartmentLov,
												Dept);
										logs.logging("INFO", "Department is Entered:" + Dept);
										flag = webDB.waitForElement(SsoPageLocators.SsoFrame, ElementType.Xpath);
										if (flag) {
											webDB.switchToFrame(SsoPageLocators.SsoFrame, ElementType.Xpath);
											logs.logging("INFO", "Switched to Frame");
											flag = webDB.waitForElement(SsoPageLocators.SsoShipWHToLov,
													ElementType.Xpath);
											if (flag) {
												commonFunc.searchAndSelectValueInFilters(SsoPageLocators.SsoShipWHToLov,
														WH);
												logs.logging("INFO", "Wharehouse is Entered:" + WH);
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
	 * This Methods Is Used To Verify Clicking Supplier Schedule Order Create Button
	 * 
	 * @author Durai-RMITS
	 * 
	 */
	public void verifyClickingSsoCreate() {
		flag = webDB.waitForElement(SsoPageLocators.SsoFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(SsoPageLocators.SsoFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched to Frame");
			flag = webDB.waitForElement(SsoPageLocators.SsoCreate, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SsoPageLocators.SsoCreate, ElementType.Xpath);
				logs.logging("INFO", "SSO Doc Is Created");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify GetText Document Number
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyGetTextDocNum() {
		webDB.switchToDefaultContent();
		flag = webDB.waitForElement(SsoPageLocators.SsoDocNum, ElementType.Xpath);
		if (flag) {
			String SsoDocNum = webDB.getDomPropertyFromElement(SsoPageLocators.SsoDocNum, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			VariableHandler.setSsoDocNum(SsoDocNum);
			logs.logging("INFO", "Document Number is:" + VariableHandler.getSsoDocNum());
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Fetch Open Purchase Order
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyFetchOpenPoButton() {
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.hardWait(4000);
		flag = webDB.waitForElement(SsoPageLocators.FetchOPOBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SsoPageLocators.FetchOPOBtn, ElementType.Xpath);
			logs.logging("INFO", "Fetch PO Button is clicked");
			commonFunc.hardWait(4000);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(SsoPageLocators.FetchOPOFrame, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(SsoPageLocators.FetchOPOFrame, ElementType.Xpath);
				logs.logging("INFO", "Switched to frame");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of Fetching Open Po Number Entering
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyFetchOpenPoDocNumberEnetring() {

		flag = webDB.waitForElement(SsoPageLocators.FetchPOSelectNUm, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SsoPageLocators.FetchPOSelectNUm, ElementType.Xpath);
			logs.logging("INFO", "Po Num Select Filed is Clicked");
			commonFunc.hardWait(5000);
			flag = webDB.waitForElement(SsoPageLocators.FetchPOEneterNum, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SsoPageLocators.FetchPOEneterNum, ElementType.Xpath);
				webDB.sendTextToAnElement(SsoPageLocators.FetchPOEneterNum, VariableHandler.getOPODocNum(),
						ElementType.Xpath);
				webDB.pressEnterKey();
				commonFunc.hardWait(3000);
				flag = webDB.waitForElement(SsoPageLocators.FetchOPOSelectall, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SsoPageLocators.FetchOPOSelectall, ElementType.Xpath);
					logs.logging("INFO", "Select all is clicked");
					commonFunc.hardWait(3000);
					flag = webDB.waitForElement(SsoPageLocators.FetchOPOOKBtn, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SsoPageLocators.FetchOPOOKBtn, ElementType.Xpath);
						logs.logging("INFO", "Ok Button is clicked");
						webDB.switchToDefaultContent();
						logs.logging("INFO", "Switched to default content");
						commonFunc.hardWait(5000);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Validate the Line Details Filed is correctly entered
	 * 
	 * @author Durai-RMITS
	 * @param FirmQTY
	 * @param TentQTy
	 * @param FirmDateFrom
	 * @param FirmDateTo
	 */
	public void verifyLineDetails(String FirmQTY, String TentQTY, String FirmDateFrom, String FirmDateTo) {
		flag = webDB.waitForElement(SsoPageLocators.SsoHeaderSupplier, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(SsoPageLocators.SsoHeaderSupplier, ElementType.Xpath);
			logs.logging("INFO", "TEST ");
			List<WebElement> LineNumber = webDB.getDriver().findElements(By.xpath(SsoPageLocators.SsoLineCount));
			System.out.println(LineNumber.size());
			for (int i = 1; i <= LineNumber.size(); i++) {
				flag = webDB.waitForElement(SsoPageLocators.SSOItemField(i), ElementType.Xpath);
				if (flag) {
					webDB.doubleClickAnElement(SsoPageLocators.SSOItemField(i), ElementType.Xpath);
					webDB.clickAnElement(SsoPageLocators.SSOItemFieldInput, ElementType.Xpath);
					logs.logging("INFO", "Item Field is clicked ");
					flag = webDB.waitForElement(SsoPageLocators.SSOFirmQtyField(i), ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SsoPageLocators.SSOFirmQtyField(i), ElementType.Xpath);
						webDB.clearText(SsoPageLocators.SSOFirmQtyInput, ElementType.Xpath);
						webDB.sendTextToAnElement(SsoPageLocators.SSOFirmQtyInput, FirmQTY, ElementType.Xpath);

						flag = webDB.waitForElement(SsoPageLocators.SSOTentQtyField(i), ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(SsoPageLocators.SSOTentQtyField(i), ElementType.Xpath);
							webDB.clearText(SsoPageLocators.SSOTentQtyInput, ElementType.Xpath);
							webDB.sendTextToAnElement(SsoPageLocators.SSOTentQtyInput, TentQTY, ElementType.Xpath);
							flag = webDB.waitForElement(SsoPageLocators.SSOFirmDateFromField(i), ElementType.Xpath);
							if (flag) {
								webDB.doubleClickAnElement(SsoPageLocators.SSOFirmDateFromField(i), ElementType.Xpath);
								webDB.clearText(SsoPageLocators.SSOFirmDateFromInput, ElementType.Xpath);
								webDB.sendTextToAnElement(SsoPageLocators.SSOFirmDateFromInput, Today,
										ElementType.Xpath);
								commonFunc.checkRefreshCellsDisappear();
								flag = webDB.waitForElement(SsoPageLocators.SSOFirmDateToField(i), ElementType.Xpath);
								if (flag) {
									webDB.doubleClickAnElement(SsoPageLocators.SSOFirmDateToField(i),
											ElementType.Xpath);
									webDB.sendTextToAnElement(SsoPageLocators.SSOFirmDateToInput, Today,
											ElementType.Xpath);
									commonFunc.hardWait(1000);
									webDB.pressTabKey();
									flag = webDB.waitForElement(SsoPageLocators.SSOTentDateFromField(i),
											ElementType.Xpath);
									if (flag) {
										webDB.doubleClickAnElement(SsoPageLocators.SSOTentDateFromField(i),
												ElementType.Xpath);
										webDB.clearText(SsoPageLocators.SSOTentDateFromInput, ElementType.Xpath);
										webDB.sendTextToAnElement(SsoPageLocators.SSOTentDateFromInput, Today,
												ElementType.Xpath);
										commonFunc.hardWait(1000);
										flag = webDB.waitForElement(SsoPageLocators.SSOTentDateToField(i),
												ElementType.Xpath);
										if (flag) {
											webDB.doubleClickAnElement(SsoPageLocators.SSOTentDateToField(i),
													ElementType.Xpath);
											webDB.sendTextToAnElement(SsoPageLocators.SSOTentDateToInput, Today,
													ElementType.Xpath);
											webDB.pressTabKey();

											flag = webDB.waitForElement(SsoPageLocators.SsoTentType, ElementType.Xpath);
											if (flag) {
												webDB.clickAnElement(SsoPageLocators.SsoTentType, ElementType.Xpath);
												webDB.selectDropDownByValue(SsoPageLocators.SsoTentType, "D",
														ElementType.Xpath);
												webDB.pressTabKey();
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
	 * This Methods Is Used To Verify Creation Of Supplier Schedule Order Line Save
	 * 
	 * @author Durai-RMITS
	 */
	public void verifySsoLineSave() {
		flag = webDB.waitForElement(SsoPageLocators.SSOLineSave, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SsoPageLocators.SSOLineSave, ElementType.Xpath);
			logs.logging("INFO", "Line is Saved");
			commonFunc.hardWait(5000);
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of supplier Schedule Generate
	 * 
	 * @author Durai-RMITS
	 */
	public void verifySsoGenerateSchedule() {
		flag = webDB.waitForElement(SsoPageLocators.SsoGenSchdl, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SsoPageLocators.SsoGenSchdl, ElementType.Xpath);
			logs.logging("INFO", "Schedule is Generated");
			commonFunc.hardWait(7000);
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Cancel Supplier Schedule Lines
	 * 
	 * @author Durai-RMITS
	 */
	public void verifySsoCancelLines() {
		flag = webDB.waitForElement(SsoPageLocators.SsoLineCancel, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SsoPageLocators.SsoLineCancel, ElementType.Xpath);
			logs.logging("INFO", "Line is Cancelled");

			flag = webDB.waitForElement(SsoPageLocators.SsoLineCancelNew, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SsoPageLocators.SsoLineCancelNew, ElementType.Xpath);
				logs.logging("INFO", "Cancel Button is Clicked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Cancel Supplier Schedule Header
	 * 
	 * @author Durai-RMITS
	 */
	public void verifySsoCancelHeader() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(SsoPageLocators.SsoHeaderCancel, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SsoPageLocators.SsoHeaderCancel, ElementType.Xpath);
			logs.logging("INFO", "Header is Cancelled");
			flag = webDB.waitForElement(SsoPageLocators.SsoCancelOk, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SsoPageLocators.SsoCancelOk, ElementType.Xpath);
				logs.logging("INFO", "Cancel Ok Button is Clicked");
				commonFunc.hardWait(3000);
				flag = webDB.waitForElement(SsoPageLocators.SsoCancelFindSearch, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SsoPageLocators.SsoCancelFindSearch, ElementType.Xpath);
					logs.logging("INFO", "Search Button is Clicked");
					flag = webDB.waitForElement(SsoPageLocators.SsoDocNoLovFindPage, ElementType.Xpath);
					if (flag) {
//				webDB.clickAnElement(SsoPageLocators.SsoDocNoLovFindPage, ElementType.Xpath);
						commonFunc.searchAndSelectValueInFilters(SsoPageLocators.SsoDocNoLovFindPage,
								VariableHandler.getSsoDocNum());
						logs.logging("INFO", "Document No Is Entered");
					}
					flag = webDB.waitForElement(SsoPageLocators.SsoFindSearchBtn, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SsoPageLocators.SsoFindSearchBtn, ElementType.Xpath);
						logs.logging("INFO", "Search Button Is Clicked");
						commonFunc.checkRefreshCellsDisappear();
						commonFunc.hardWait(1000);
						String canclsts = webDB.getTextFromElement(SsoPageLocators.SsoCanclStatus, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Expected Text: " + "Cancelled" + " Displayed: " + canclsts);
							flag = canclsts.equalsIgnoreCase("Cancelled");
							if (flag) {
								logs.logging("INFO", "verified Displayed Status is: " + canclsts);
							} else {
								logs.logging("INFO", "Verification failed in Status");
								logs.logging("INFO", "Expected Text: " + "Cancelled" + " Displayed: " + canclsts);
							}
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Cancel Status In Supplier Schedule Order
	 * 
	 * @author Durai-RMITS
	 */
	public void verifySsoCancelStatus() {
		flag = webDB.waitForElement(SsoPageLocators.SsoFindSsoNumFiled, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SsoPageLocators.SsoFindSsoNumFiled, ElementType.Xpath);
			webDB.sendTextToAnElement(SsoPageLocators.SsoFindSsoNumFiled, VariableHandler.getSsoDocNum(),
					ElementType.Xpath);
			logs.logging("INFO", "Sso Number is entered");
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Supplier Schedule Order Post
	 * 
	 * @author Durai-RMITS
	 */
	public void verifySsoPost() {
		flag = webDB.waitForElement(SsoPageLocators.SsoPost, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SsoPageLocators.SsoPost, ElementType.Xpath);
			logs.logging("INFO", "Schedule is Posted");
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Validate the Location Filed is correctly entered
	 * 
	 * @author Durai-RMITS
	 * @param Location
	 */

	private void validateLocationInSsoHeader(String Location) {
		flag = webDB.waitForElement(SsoPageLocators.SsoHeaderLoc, ElementType.Xpath);
		if (flag) {
			String DisplayText = webDB.getTextFromElement(SsoPageLocators.SsoHeaderLoc, ElementType.Xpath);
			logs.logging("INFO", "Expected Text: " + Location + " Displayed: " + DisplayText);
			flag = DisplayText.equalsIgnoreCase(Location);
			if (flag) {
				logs.logging("INFO", "verified Displayed Location in SSo Header: " + DisplayText);
			} else {
				logs.logging("INFO", "Verification failed in Location SSo Header");
				logs.logging("INFO", "Expected Text: " + Location + " Displayed: " + DisplayText);
			}

		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Validate the Supplier Filed is correctly entered
	 * 
	 * @author Durai-RMITS
	 * @param Supplier
	 */

	private void validateSupplierInSsoHeader(String Supplier) {
		flag = webDB.waitForElement(SsoPageLocators.SsoHeaderSupplier, ElementType.Xpath);
		if (flag) {
			String DisplayText = webDB.getDomPropertyFromElement(SsoPageLocators.SsoHeaderSupplier, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			logs.logging("INFO", "Expected Text: " + Supplier + " Displayed: " + DisplayText);
			flag = DisplayText.equalsIgnoreCase(Supplier);
			if (flag) {
				logs.logging("INFO", "verified Displayed Supplier in SSo Header: " + DisplayText);
			} else {
				logs.logging("INFO", "Verification failed in Supplier SSo Header");
				logs.logging("INFO", "Expected Text: " + Supplier + " Displayed: " + DisplayText);
			}

		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Validate the WH Filed is correctly entered
	 * 
	 * @author Durai-RMITS
	 * @param WH
	 */

	private void validateWarehouseInSsoHeader(String WH) {
		flag = webDB.waitForElement(SsoPageLocators.SsoHeaderWH, ElementType.Xpath);
		if (flag) {
			String DisplayText = webDB.getTextFromElement(SsoPageLocators.SsoHeaderWH, ElementType.Xpath);
			logs.logging("INFO", "Expected Text: " + WH + " Displayed: " + DisplayText);
			flag = DisplayText.equalsIgnoreCase(WH);
			if (flag) {
				logs.logging("INFO", "verified Displayed Warehouse in SSo Header: " + DisplayText);
			} else {
				logs.logging("INFO", "Verification failed in Warehouse SSo Header");
				logs.logging("INFO", "Expected Text: " + WH + " Displayed: " + DisplayText);
			}

		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Searching Item in Find Screen
	 * 
	 * @author Durai-RMITS
	 * @param Item1
	 */
	public void verifySearchOfItem(String Item1) {
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(PricelistPageLocators.FindScreenItemField, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(PricelistPageLocators.FindScreenItemField, Item1, ElementType.Xpath);
			logs.logging("INFO", "Search is clicked");
			flag = webDB.waitForElement(PricelistPageLocators.ItemSearchBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button is clicked ");
				verifySearchButtonClicking();
				verifyClickingGeneralTab();
				verifyGenerateItemSim(commonFunc.getDataFromTestDataSheet("Sim"));
				verifyItemPost();
			}
		}

		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Searching Item in Find Screen
	 * 
	 * @author Durai-RMITS
	 * @param Item1
	 */
	public void verifySearchOfItemDis(String Item1) {
		commonFunc.hardWait(2000);
		flag = webDB.waitForElement(PricelistPageLocators.FindScreenItemField, ElementType.Xpath);
		if (flag) {
			webDB.sendTextToAnElement(PricelistPageLocators.FindScreenItemField, Item1, ElementType.Xpath);
			logs.logging("INFO", "Search is clicked");
			flag = webDB.waitForElement(PricelistPageLocators.ItemSearchBtn, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Search Button is clicked ");
				verifySearchButtonClicking();
				verifyClickingGeneralTab();
				verifyGenerateItemSim(commonFunc.getDataFromTestDataSheet("Dis"));
				verifyItemPost();
			}
		}

		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Search Button Clicking
	 * 
	 * @author Durai-RMITS
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
	 * This Methods Is Used To Clicking General Tab
	 * 
	 * @author Durai-RMITS
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
	 * This Methods Is Used To Verify Generate Item for similar Uom
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyGenerateItemSim(String Itm) {
		String ItemSeqNo = RandomStringUtils.randomNumeric(6);
		if (ItemSeqNo.equals("00000")) {
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
						logs.logging("INFO", "Item ID is:" + VariableHandler.getItemName());
					}
				}
			}
		}

		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Post Item
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyItemPost() {
		flag = webDB.waitForElement(PricelistPageLocators.ItemPost, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.ItemPost, ElementType.Xpath);
			logs.logging("INFO", "Item is Posted");
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Clicking Find Button
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyClickingFindBtnOfItem() {
		commonFunc.hardWait(4000);
		flag = webDB.waitForElement(PricelistPageLocators.ItemFindBtn, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PricelistPageLocators.ItemFindBtn, ElementType.Xpath);
			logs.logging("INFO", "Find Button is clicked");
			webDB.waitForElement(PricelistPageLocators.ItemMasterAdd, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Item Add Button is Clicked");
			}
		}

		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Of Open Purchase Order
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyCreationOfOPO() {
		flag = webDB.waitForElement(SsoPageLocators.OPORadioBtn, ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(SsoPageLocators.OPORadioBtn, ElementType.Xpath);
			logs.logging("INFO", "OPO Radio Button is Clicked");

			flag = webDB.waitForElement(SsoPageLocators.OPOAddBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SsoPageLocators.OPOAddBtn, ElementType.Xpath);
				logs.logging("INFO", "Add Button is Clicked");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Adding Details Open Purchase Order
	 * 
	 * @author Durai-RMITS
	 * @param OPOLocation
	 * @param OPOSupplier
	 */
	public void verifyAddingFillDetailsOfOPO(String OPOLocation, String OPOSupplier) {
		flag = webDB.waitForElement(SsoPageLocators.OpoFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(SsoPageLocators.OpoFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched to Frame");
			flag = webDB.waitForElement(SsoPageLocators.OPOLocLov, ElementType.Xpath);
			if (flag) {
				commonFunc.searchAndSelectValueInFilters(SsoPageLocators.OPOLocLov, OPOLocation);
				logs.logging("INFO", "LOcation Entered is:" + OPOLocation);
				flag = webDB.waitForElement(SsoPageLocators.OpoFrame, ElementType.Xpath);
				if (flag) {
					webDB.switchToFrame(SsoPageLocators.OpoFrame, ElementType.Xpath);
					logs.logging("INFO", "Switched to Frame");

					flag = webDB.waitForElement(SsoPageLocators.OPOSupplierLov, ElementType.Xpath);
					if (flag) {
						commonFunc.searchAndSelectValueInFilters(SsoPageLocators.OPOSupplierLov, OPOSupplier);
						logs.logging("INFO", "Supplier Entered is:" + OPOSupplier);

					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Clicking Open Purchase Order Create
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyClickingOpoCreate() {
		flag = webDB.waitForElement(SsoPageLocators.OpoFrame, ElementType.Xpath);
		if (flag) {
			webDB.switchToFrame(SsoPageLocators.OpoFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched to Frame");
			flag = webDB.waitForElement(SsoPageLocators.OPOCreateBtn, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SsoPageLocators.OPOCreateBtn, ElementType.Xpath);
				logs.logging("INFO", "OPO Doc Is Created");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Creation Item For Open Purchase Order
	 * 
	 * @author Durai-RMITS
	 * @param Item
	 * @param Cost
	 * @param Disc
	 * @param HSN
	 */
	public void verifyCreatingItemForOpo(String Item, String Cost, String Disc, String HSN) {
		commonFunc.hardWait(4000);
		webDB.switchToDefaultContent();
		flag = webDB.waitForElement(SsoPageLocators.OPODocNum, ElementType.Xpath);
		if (flag) {
			String OPODocNum = webDB.getTextFromElement(SsoPageLocators.OPODocNum, ElementType.Xpath);
			VariableHandler.setOPODocNum(OPODocNum);
			logs.logging("INFO", "OPO Doc num is:" + VariableHandler.getOPODocNum());
			flag = webDB.waitForElement(SsoPageLocators.OPOLineAdd, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SsoPageLocators.OPOLineAdd, ElementType.Xpath);
				logs.logging("INFO", "Line Wise Add Button is Clicked");
				flag = webDB.waitForElement(SsoPageLocators.OPOItemDataLastRow(), ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SsoPageLocators.OPOItemDataLastRow(), ElementType.Xpath);
					commonFunc.searchAndSelectValueInFilters(SsoPageLocators.OPOLineItemLov, Item);
					logs.logging("INFO", "Item is Entered:" + Item);
					flag = webDB.waitForElement(SsoPageLocators.OPOCostDataLastRow(), ElementType.Xpath);
					if (flag) {
						webDB.doubleClickAnElement(SsoPageLocators.OPOCostDataLastRow(), ElementType.Xpath);
						webDB.clearText(SsoPageLocators.OPOLineCostInput, ElementType.Xpath);
						webDB.sendTextToAnElement(SsoPageLocators.OPOLineCostInput, Cost, ElementType.Xpath);
						logs.logging("INFO", "Cost is Entered:" + Cost);
						flag = webDB.waitForElement(SsoPageLocators.OPODisDataLastRow(), ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(SsoPageLocators.OPODisDataLastRow(), ElementType.Xpath);
							webDB.clearText(SsoPageLocators.OPOLineDiscInput, ElementType.Xpath);
							webDB.sendTextToAnElement(SsoPageLocators.OPOLineDiscInput, Disc, ElementType.Xpath);
							logs.logging("INFO", "Discount is Entered:" + Disc);

							flag = webDB.waitForElement(SsoPageLocators.OPOHSNDataLastRow(), ElementType.Xpath);
							if (flag) {
								webDB.clickAnElement(SsoPageLocators.OPOHSNDataLastRow(), ElementType.Xpath);
								commonFunc.searchAndSelectValueInFilters(SsoPageLocators.OPOLineHsnLov, HSN);
								logs.logging("INFO", "Hsn Code is Entered:" + HSN);

								flag = webDB.waitForElement(SsoPageLocators.OPOLineSave, ElementType.Xpath);
								if (flag) {
									webDB.clickAnElement(SsoPageLocators.OPOLineSave, ElementType.Xpath);
									logs.logging("INFO", "Line is Saved");
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
	 * This Methods Is Used To Verify Creation Of Open Purchase Order Post
	 * 
	 * @author Durai-RMITS
	 */
	public void verifyClickingOpoPost() {
		commonFunc.hardWait(4000);
		flag = webDB.waitForElement(SsoPageLocators.OPOPost, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(SsoPageLocators.OPOPost, ElementType.Xpath);
			logs.logging("INFO", "OPO Doc Is Posted");
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Waiting for Approval Dialog Box
	 * 
	 * @author Durai-RMITS
	 */
	public void waitingForApproval() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(RfqLocators.RfqWorkflowFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(RfqLocators.RfqWorkflowFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(RfqLocators.PRForwardlov,
					commonFunc.getDataFromTestDataSheet("AshokValue"));
			commonFunc.hardWait(2000);
			webDB.switchToFrame(RfqLocators.RfqWorkflowFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(SsoPageLocators.ItmworkflowOK, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SsoPageLocators.ItmworkflowOK, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				commonFunc.hardWait(7000);
			}
		} else {
			flag = false;
			VariableHandler.setApprovalFlag(flag);
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Waiting for Approval Dialog Box
	 * 
	 * @author Durai-RMITS
	 */
	public void waitingForApprovalOpo() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(RfqLocators.RfqWorkflowFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(RfqLocators.RfqWorkflowFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(RfqLocators.PRForwardlov,
					commonFunc.getDataFromTestDataSheet("AshokValue"));
			commonFunc.hardWait(2000);
			webDB.switchToFrame(RfqLocators.RfqWorkflowFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(RfqLocators.PRworkflowOK, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(RfqLocators.PRworkflowOK, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				commonFunc.hardWait(7000);
			}
		} else {
			flag = false;
			VariableHandler.setApprovalFlag(flag);
		}

		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Forward Screen is Opening
	 * 
	 * @author Durai-RMITS
	 */
	public void forwardApprovalScreen() {
		commonFunc.hardWait(1000);
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		approvalFunc.navigateToApprovals();
		flag = webDB.waitForElement(ApprovalPageLocators.UserNotifyIcon, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "User Notify Icon is Clicked");
		}
		assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Approve the document
	 * 
	 * @author Durai-RMITS
	 * @param Number
	 */
	public void filterDocumentsForPriceListApproval(String Number) {
		flag = webDB.waitForElement(ApprovalPageLocators.SearchBox, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Search Box IS Displayed");
			webDB.clickAnElement(ApprovalPageLocators.SearchBoxContainer, ElementType.Xpath);
			flag = webDB.waitForElement(ApprovalPageLocators.SearchBox, ElementType.Xpath);
			if (flag) {
				webDB.clearTextField(ApprovalPageLocators.SearchBox, ElementType.Xpath);
				logs.logging("INFO", "Clear Text Search Box");
				webDB.sendTextToAnElement(ApprovalPageLocators.SearchBox, Number, ElementType.Xpath);
				logs.logging("INFO", "Entered Document Number Is: " + Number);
				flag = webDB.waitForClickElement(ApprovalPageLocators.SearchBoxGoBtn, ElementType.Xpath);
				if (flag) {
//					webDB.clickAnElement(ApprovalPageLocators.SearchBoxGoBtn, ElementType.Xpath);
//					flag = webDB.waitForElement(ApprovalPageLocators.SearchTableResult, ElementType.Xpath);
//					if (flag) {
//						logs.logging("INFO", "Search Go Button Is Clicked");
//					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Methods Is Used To Verify Approve The Document
	 * 
	 * @author Durai-RMITS
	 * @param Number
	 */
	public void verifyApproveWithDocNumber(String Number) {
		commonFunc.hardWait(1000);
		flag = webDB.waitForClickElement(PricelistPageLocators.PRLISTSelectCheckBox(Number), ElementType.Xpath);
		if (flag) {
			webDB.javaScriptClickAnElement(PricelistPageLocators.PRLISTSelectCheckBox(Number), ElementType.Xpath);
			logs.logging("INFO", "Select Box is Checked");
			flag = webDB.waitForClickElement(ApprovalPageLocators.ProcessButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "CheckBox Is Clicked And Process Button IS Displayed");
				webDB.javaScriptClickAnElement(ApprovalPageLocators.ProcessButton, ElementType.Xpath);
				flag = webDB.waitForElement(ApprovalPageLocators.ApproveRadioBtn, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO",
							"CheckBox select for Document Number " + Number + " and Process button clicked");
					commonFunc.checkRefreshCellsDisappear();
					webDB.clickAnElement(ApprovalPageLocators.ApproveRadioBtn, ElementType.Xpath);
					flag = webDB.waitForElement(ApprovalPageLocators.ApproveProcessButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Approval Process Button IS Displayed");
						webDB.clickAnElement(ApprovalPageLocators.ApproveProcessButton, ElementType.Xpath);
						flag = webDB.waitForElement(CommonUiLocators.PostToastMessage, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Toast Message Displayed, Posted Successfully");
							if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
								webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
							}
						}
					}
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * Check Assertion Both Values
	 * 
	 * @author Durai-RMITS
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
	 * Supplier Schedule Order Screen Validation For Location
	 * 
	 * @author Durai-RMITS
	 */
	public void supplierScheduleHeaderValidation() {
		verifyAssertionBothValues(
				dbCommonFunctions.SSOdetails(VariableHandler.getSsoDocNum(),
						commonFunc.getDataFromTestDataSheet("SSODbLocationColumnName")),
				commonFunc.getDataFromTestDataSheet("L49ID"));
		logs.logging("INFO", "Header Location Details Matched With DataBase Values");
	}

	/**
	 * Supplier Schedule Order Screen Validation For Location
	 * 
	 * @author Durai-RMITS
	 */
	public void supplierScheduleHeaderValidationUnit1() {
		verifyAssertionBothValues(
				dbCommonFunctions.SSOdetails(VariableHandler.getSsoDocNum(),
						commonFunc.getDataFromTestDataSheet("SSODbLocationColumnName")),
				commonFunc.getDataFromTestDataSheet("Loc_100"));
		logs.logging("INFO", "Header Location Details Matched With DataBase Values");
	}

	/**
	 * Supplier Schedule Order Screen Validation For Location
	 * 
	 * @author Durai-RMITS
	 */
	public void supplierScheduleHeaderValidationD() {
		verifyAssertionBothValues(
				dbCommonFunctions.SSOdetails(VariableHandler.getSsoDocNum(),
						commonFunc.getDataFromTestDataSheet("SSODbLocationColumnName")),
				commonFunc.getDataFromTestDataSheet("Loc_100"));
		logs.logging("INFO", "Header Location Details Matched With DataBase Values");
	}

	/**
	 * Supplier Schedule Order Screen Validation For Location
	 * 
	 * @author Durai-RMITS
	 */
	public void supplierScheduleHeaderImportSupplierValidation() {
		verifyAssertionBothValues(
				dbCommonFunctions.SSOdetails(VariableHandler.getSsoDocNum(),
						commonFunc.getDataFromTestDataSheet("SSODbSupplierColumnName")),
				commonFunc.getDataFromTestDataSheet("ImpSupplier"));
		logs.logging("INFO", "Header Supplier Details Matched With DataBase Values");
	}

	/**
	 * Supplier Schedule Order Screen Validation For Location
	 * 
	 * @author Durai-RMITS
	 */
	public void supplierScheduleHeaderSupplierValidation() {
		verifyAssertionBothValues(
				dbCommonFunctions.SSOdetails(VariableHandler.getSsoDocNum(),
						commonFunc.getDataFromTestDataSheet("SSODbSupplierColumnName")),
				commonFunc.getDataFromTestDataSheet("Opo_SupId_ID"));
		logs.logging("INFO", "Header Supplier Details Matched With DataBase Values");
	}

	/**
	 * Supplier Schedule Order Screen Validation For Location
	 * 
	 * @author Durai-RMITS
	 */
	public void supplierScheduleHeaderSupplierValidationD() {
		verifyAssertionBothValues(
				dbCommonFunctions.SSOdetails(VariableHandler.getSsoDocNum(),
						commonFunc.getDataFromTestDataSheet("SSODbSupplierColumnName")),
				commonFunc.getDataFromTestDataSheet("Opo_SupId_ID"));
		logs.logging("INFO", "Header Supplier Details Matched With DataBase Values");
	}

	/**
	 * Supplier Schedule Order Screen Validation For Location
	 * 
	 * @author Durai-RMITS
	 */
	public void supplierScheduleHeaderWarehouseValidation() {
		verifyAssertionBothValues(
				dbCommonFunctions.SSOdetails(VariableHandler.getSsoDocNum(),
						commonFunc.getDataFromTestDataSheet("SSODbWHToColumnName")),
				commonFunc.getDataFromTestDataSheet("RAW MATERIAL WH -49-4"));
		logs.logging("INFO", "Header Warehouse Details Matched With DataBase Values");
	}

	/**
	 * Supplier Schedule Order Screen Validation For Location
	 * 
	 * @author Durai-RMITS
	 */
	public void supplierScheduleHeaderWarehouseValidationUnit1() {
		verifyAssertionBothValues(
				dbCommonFunctions.SSOdetails(VariableHandler.getSsoDocNum(),
						commonFunc.getDataFromTestDataSheet("SSODbWHToColumnName")),
				commonFunc.getDataFromTestDataSheet("WH_To_Id"));
		logs.logging("INFO", "Header Warehouse Details Matched With DataBase Values");
	}

	/**
	 * Supplier Schedule Order Screen Validation For Location
	 * 
	 * @author Durai-RMITS
	 */
	public void supplierScheduleHeaderWarehouseValidationD() {
		verifyAssertionBothValues(
				dbCommonFunctions.SSOdetails(VariableHandler.getSsoDocNum(),
						commonFunc.getDataFromTestDataSheet("SSODbWHToColumnName")),
				commonFunc.getDataFromTestDataSheet("WH_To_Id"));
		logs.logging("INFO", "Header Warehouse Details Matched With DataBase Values");
	}

	/**
	 * Open purchase Order Screen Validation For Location
	 * 
	 * @author Durai-RMITS
	 */
	public void openPurchaseOrderHeaderValidation() {
		verifyAssertionBothValues(
				dbCommonFunctions.OPOdetails(VariableHandler.getOPODocNum(),
						commonFunc.getDataFromTestDataSheet("OPODbLocationColumnName")),
				commonFunc.getDataFromTestDataSheet("Unit_100"));
		logs.logging("INFO", "Header Location Details Matched With DataBase Values");
	}

	/**
	 * Open purchase Order Screen Validation For Location
	 * 
	 * @author Durai-RMITS
	 * @param location
	 */
	public void openPurchaseOrderHeaderSupplierValidation() {
		verifyAssertionBothValues(
				dbCommonFunctions.OPOdetails(VariableHandler.getOPODocNum(),
						commonFunc.getDataFromTestDataSheet("OPODbSupplierColumnName")),
				commonFunc.getDataFromTestDataSheet("Opo_SupId_ID"));
		logs.logging("INFO", "Header Supplier Details Matched With DataBase Values");
	}

	/**
	 * Open purchase Order Screen Validation For Location
	 * 
	 * @author Durai-RMITS
	 * @param location
	 */
	public void openPurchaseOrderHeaderImportSupplierValidation() {
		verifyAssertionBothValues(
				dbCommonFunctions.OPOdetails(VariableHandler.getOPODocNum(),
						commonFunc.getDataFromTestDataSheet("OPODbSupplierColumnName")),
				commonFunc.getDataFromTestDataSheet("ImpSupplier"));
		logs.logging("INFO", "Header Supplier Details Matched With DataBase Values");
	}

	/**
	 * Open purchase Order Screen Validation For Location
	 * 
	 * @author Durai-RMITS
	 * @param location
	 */
	public void openPurchaseOrderHeaderItemValidation() {
		verifyAssertionBothValues(dbCommonFunctions.OPOdetails(VariableHandler.getOPODocNum(),
				commonFunc.getDataFromTestDataSheet("OPODbItemColumnName")), VariableHandler.getItemName());
		logs.logging("INFO", "Header Item Details Matched With DataBase Values");
	}

	/**
	 * Open purchase Order Screen Validation For Location
	 * 
	 * @author Durai-RMITS
	 * @param location
	 */
	public void openPurchaseOrderHeaderCostValidation() {
		verifyAssertionBothValues(
				dbCommonFunctions.OPOdetails(VariableHandler.getOPODocNum(),
						commonFunc.getDataFromTestDataSheet("OPODbCostColumnName")),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		logs.logging("INFO", "Header Cost Details Matched With DataBase Values");
	}

	/**
	 * Open purchase Order Screen Validation For Location
	 * 
	 * @author Durai-RMITS
	 * @param location
	 */
	public void openPurchaseOrderHeaderDiscountValidation() {
		verifyAssertionBothValues(
				dbCommonFunctions.OPOdetails(VariableHandler.getOPODocNum(),
						commonFunc.getDataFromTestDataSheet("OPODbDiscColumnName")),
				commonFunc.getDataFromTestDataSheet("SO_Special_Disc"));
		logs.logging("INFO", "Header Discount Details Matched With DataBase Values");
	}

	/**
	 * Open purchase Order Screen Validation For Location
	 * 
	 * @author Durai-RMITS
	 * @param location
	 */
	public void openPurchaseOrderHeaderHsnValidation() {
		verifyAssertionBothValues(
				dbCommonFunctions.OPOdetails(VariableHandler.getOPODocNum(),
						commonFunc.getDataFromTestDataSheet("OPODbHsnColumnName")),
				commonFunc.getDataFromTestDataSheet("Hsn_Code"));
		logs.logging("INFO", "Header HSN Details Matched With DataBase Values");
	}
}
