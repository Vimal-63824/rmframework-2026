package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
import static org.testng.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import commonfunctions.CommonFunctions;
import commonfunctions.ItemDetailsHandler;
import commonfunctions.ItemDetailsHandler.SOCustomerDetailsName;
import commonfunctions.ItemDetailsHandler.SOLineDetailsName;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.ApprovalPageLocators;
import commonlocators.supplychainmanagement.SalesOrderLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class SalesOrderFunctions {
	private static boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	public ApprovalPageFunctions apprpagefunc = new ApprovalPageFunctions();
	private static PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();
	private static ItemDetailsHandler itemDetailHandler = new ItemDetailsHandler();

	/**
	 * @author Harikrishna -RMITS This Method Is used to Verify the Domestic
	 *         SOGeneral With SimilarStock And CustomerUOM
	 * @return boolean flag
	 */
	public boolean verifyDomesticSOGeneralSimilarStockAndCustomerUOM() {
		String Qty = RandomStringUtils.randomNumeric(3);
		if (Qty.equals("000")) {
			Qty = "10";
		}
		String UnitCost = RandomStringUtils.randomNumeric(3);
		if (UnitCost.equals("000")) {
			UnitCost = "10";
		}
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		customerDetailsVerification(commonFunc.getDataFromTestDataSheet("Customer"));
		addItemsInSO(commonFunc.getDataFromTestDataSheet("SO_Similar_Item"));
		addQuantityAndUnitCost(Qty, UnitCost);
		chooseHsnCodeAndClickLineWiseSaveButton(commonFunc.getDataFromTestDataSheet("SO_HSNCode"));
		verifyItemCalculationWithLineAmount();
		lineDetailVerification(commonFunc.getDataFromTestDataSheet("SO_Similar_Item"), getTextCurrencyInHeader(), Qty);
		clickLineWiseDetailsSaveButton();
		getSalesOrderTotalAmount();
		verifySalesOrderTaxCalculation();
//		clickPrintButton();
		clickPostButton();
		forwardForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			apprpagefunc.navigateToApprovals();
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			validateSalesOrderDetailsInDocApproval(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Customer"));
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			apprpagefunc.verifyApproveWithDocNumber(VariableHandler.getSONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateSalesOrderPage();
				validateSalesOrderLineDocumentStatus(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
//				clickPrintButtonInFindScreen();
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used to Verify the Domestic
	 *         SOGeneral With DisSimilar Stock And CustomerUOM
	 * @return boolean flag
	 */
	public boolean verifyDomesticSOGeneralDisSimilarStockAndCustomerUOM() {
		String Qty = RandomStringUtils.randomNumeric(2);
		if (Qty.equals("00")) {
			Qty = "10";
		}
		String UnitCost = RandomStringUtils.randomNumeric(3);
		if (UnitCost.equals("000")) {
			UnitCost = "10";
		}
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		customerDetailsVerification(commonFunc.getDataFromTestDataSheet("Customer"));
		addQuantityAndUnitCost(Qty, UnitCost);
		chooseHsnCodeAndClickLineWiseSaveButton(commonFunc.getDataFromTestDataSheet("SO_HSNCode"));
		verifyItemCalculationWithLineAmount();
		lineDetailVerification(commonFunc.getDataFromTestDataSheet("SO_DisSimilarItem"), getTextCurrencyInHeader(),
				Qty);
		clickLineWiseDetailsSaveButton();
		getSalesOrderTotalAmount();
		verifySalesOrderTaxCalculation();
//		clickPrintButton();
		clickPostButton();
		forwardForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			apprpagefunc.navigateToApprovals();
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			apprpagefunc.validateSalesOrderDetailsInDocApproval(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Customer"));
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			apprpagefunc.verifyApproveWithDocNumber(VariableHandler.getSONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateSalesOrderPage();
				validateSalesOrderLineDocumentStatus(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
//				clickPrintButtonInFindScreen();
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used to Verify the Export SOGeneral
	 *         With SimilarStock And CustomerUOM
	 * @return boolean flag
	 */
	public boolean verifyExportSOGeneralSimilarStockAndCustomerUOM() {
		String Qty = RandomStringUtils.randomNumeric(2);
		if (Qty.equals("00")) {
			Qty = "10";
		}
		String UnitCost = RandomStringUtils.randomNumeric(3);
		if (UnitCost.equals("000")) {
			UnitCost = "10";
		}
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		customerDetailsVerification(commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		addItemsInSO(commonFunc.getDataFromTestDataSheet("SO_Similar_Item"));
		addQuantityAndUnitCost(Qty, UnitCost);
		chooseHsnCodeAndClickLineWiseSaveButton(commonFunc.getDataFromTestDataSheet("SO_HSNCode"));
		verifyItemCalculationWithLineAmount();
		lineDetailVerification(commonFunc.getDataFromTestDataSheet("SO_Similar_Item"), getTextCurrencyInHeader(), Qty);
		clickLineWiseDetailsSaveButton();
		getSalesOrderTotalAmount();
		verifySalesOrderTaxCalculation();
//		clickPrintButton();
		clickPostButton();
		forwardForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			apprpagefunc.navigateToApprovals();
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			validateSalesOrderDetailsInDocApproval(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			apprpagefunc.verifyApproveWithDocNumber(VariableHandler.getSONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateSalesOrderPage();
				validateSalesOrderLineDocumentStatus(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
//				clickPrintButtonInFindScreen();
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used to Verify the Export SOGeneral
	 *         With DisSimilar Stock And CustomerUOM
	 * @return boolean flag
	 */
	public boolean verifyExportSOGeneralDisSimilarStockAndCustomerUOM() {
		String Qty = RandomStringUtils.randomNumeric(2);
		if (Qty.equals("00")) {
			Qty = "10";
		}
		String UnitCost = RandomStringUtils.randomNumeric(3);
		if (UnitCost.equals("000")) {
			UnitCost = "10";
		}
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		customerDetailsVerification(commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		addItemsInSO(commonFunc.getDataFromTestDataSheet("SO_DisSimilarItem"));
		addQuantityAndUnitCost(Qty, UnitCost);
		chooseHsnCodeAndClickLineWiseSaveButton(commonFunc.getDataFromTestDataSheet("SO_HSNCode"));
		verifyItemCalculationWithLineAmount();
		lineDetailVerification(commonFunc.getDataFromTestDataSheet("SO_DisSimilarItem"), getTextCurrencyInHeader(),
				Qty);
		clickLineWiseDetailsSaveButton();
		getSalesOrderTotalAmount();
		verifySalesOrderTaxCalculation();
//		clickPrintButton();
		clickPostButton();
		forwardForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			apprpagefunc.navigateToApprovals();
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			validateSalesOrderDetailsInDocApproval(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			apprpagefunc.verifyApproveWithDocNumber(VariableHandler.getSONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateSalesOrderPage();
				validateSalesOrderLineDocumentStatus(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
//				clickPrintButtonInFindScreen();
				flag = commonFunc.logoutUser();
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
		String Qty = RandomStringUtils.randomNumeric(2);
		if (Qty.equals("00")) {
			Qty = "10";
		}
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		customerDetailsVerification(commonFunc.getDataFromTestDataSheet("Customer"));
		addItemsInSO(commonFunc.getDataFromTestDataSheet("SO_Similar_Item_Rate_Contract"));
		addLineItemQty(Qty);
		chooseHsnCodeAndClickLineWiseSaveButton(commonFunc.getDataFromTestDataSheet("SO_HSNCode"));
		verifyItemCalculationWithLineAmount();
		lineDetailVerification(commonFunc.getDataFromTestDataSheet("SO_Similar_Item_Rate_Contract"),
				getTextCurrencyInHeader(), Qty);
		clickLineWiseDetailsSaveButton();
		getSalesOrderTotalAmount();
		verifySalesOrderTaxCalculation();
//		clickPrintButton();
		clickPostButton();
		forwardForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			apprpagefunc.navigateToApprovals();
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			validateSalesOrderDetailsInDocApproval(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Customer"));
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			apprpagefunc.verifyApproveWithDocNumber(VariableHandler.getSONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateSalesOrderPage();
				validateSalesOrderLineDocumentStatus(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
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
		String Qty = RandomStringUtils.randomNumeric(2);
		if (Qty.equals("00")) {
			Qty = "10";
		}
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		customerDetailsVerification(commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		addItemsInSO(commonFunc.getDataFromTestDataSheet("SO_Similar_Item_Rate_Contract"));
		addLineItemQty(Qty);
		chooseHsnCodeAndClickLineWiseSaveButton(commonFunc.getDataFromTestDataSheet("SO_HSNCode"));
		verifyItemCalculationWithLineAmount();
		lineDetailVerification(commonFunc.getDataFromTestDataSheet("SO_Similar_Item_Rate_Contract"),
				getTextCurrencyInHeader(), Qty);
		clickLineWiseDetailsSaveButton();
		getSalesOrderTotalAmount();
		verifySalesOrderTaxCalculation();
//		clickPrintButton();
		clickPostButton();
		forwardForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			apprpagefunc.navigateToApprovals();
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			validateSalesOrderDetailsInDocApproval(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			apprpagefunc.verifyApproveWithDocNumber(VariableHandler.getSONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateSalesOrderPage();
				validateSalesOrderLineDocumentStatus(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used to Verify the Domestic
	 *         SOGeneral Rate Contract With DisSimilar Stock And CustomerUOM
	 * @return boolean flag
	 */
	public boolean verifyDomesticSOGeneralRateContractDisSimilarStockAndCustomerUOM() {
		String Qty = RandomStringUtils.randomNumeric(2);
		if (Qty.equals("00")) {
			Qty = "10";
		}
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		customerDetailsVerification(commonFunc.getDataFromTestDataSheet("Customer"));
		addItemsInSO(commonFunc.getDataFromTestDataSheet("SO_DisSimilar_Item_Rate_Contract"));
		addLineItemQty(Qty);
		chooseHsnCodeAndClickLineWiseSaveButton(commonFunc.getDataFromTestDataSheet("SO_HSNCode"));
		verifyItemCalculationWithLineAmount();
		lineDetailVerification(commonFunc.getDataFromTestDataSheet("SO_DisSimilar_Item_Rate_Contract"),
				getTextCurrencyInHeader(), Qty);
		clickLineWiseDetailsSaveButton();
		getSalesOrderTotalAmount();
		verifySalesOrderTaxCalculation();
//		clickPrintButton();
		clickPostButton();
		forwardForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			apprpagefunc.navigateToApprovals();
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			validateSalesOrderDetailsInDocApproval(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Customer"));
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			apprpagefunc.verifyApproveWithDocNumber(VariableHandler.getSONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateSalesOrderPage();
				validateSalesOrderLineDocumentStatus(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used to Verify the Export SOGeneral
	 *         Rate Contract With Similar Stock And CustomerUOM
	 * @return boolean flag
	 */
	public boolean verifyExportSOGeneralRateContractDisSimilarStockAndCustomerUOM() {
		String Qty = RandomStringUtils.randomNumeric(2);
		if (Qty.equals("00")) {
			Qty = "10";
		}
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		customerDetailsVerification(commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		addItemsInSO(commonFunc.getDataFromTestDataSheet("SO_DisSimilar_Item_Rate_Contract"));
		addLineItemQty(Qty);
		chooseHsnCodeAndClickLineWiseSaveButton(commonFunc.getDataFromTestDataSheet("SO_HSNCode"));
		verifyItemCalculationWithLineAmount();
		lineDetailVerification(commonFunc.getDataFromTestDataSheet("SO_DisSimilar_Item_Rate_Contract"),
				getTextCurrencyInHeader(), Qty);
		clickLineWiseDetailsSaveButton();
		getSalesOrderTotalAmount();
		verifySalesOrderTaxCalculation();
//		clickPrintButton();
		clickPostButton();
		forwardForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			apprpagefunc.navigateToApprovals();
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			validateSalesOrderDetailsInDocApproval(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			apprpagefunc.verifyApproveWithDocNumber(VariableHandler.getSONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateSalesOrderPage();
				validateSalesOrderLineDocumentStatus(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
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
		String Qty = RandomStringUtils.randomNumeric(2);
		if (Qty.equals("00")) {
			Qty = "10";
		}
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		customerDetailsVerification(commonFunc.getDataFromTestDataSheet("Customer"));
		addItemsInSO(commonFunc.getDataFromTestDataSheet("SO_Similar_Item_StandardPrice"));
		addLineItemQty(Qty);
		chooseHsnCodeAndClickLineWiseSaveButton(commonFunc.getDataFromTestDataSheet("SO_HSNCode"));
		verifyItemCalculationWithLineAmount();
		lineDetailVerification(commonFunc.getDataFromTestDataSheet("SO_Similar_Item_StandardPrice"),
				getTextCurrencyInHeader(), Qty);
		clickLineWiseDetailsSaveButton();
		getSalesOrderTotalAmount();
		verifySalesOrderTaxCalculation();
//		clickPrintButton();
		clickPostButton();
		forwardForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			apprpagefunc.navigateToApprovals();
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			validateSalesOrderDetailsInDocApproval(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Customer"));
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			apprpagefunc.verifyApproveWithDocNumber(VariableHandler.getSONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateSalesOrderPage();
				validateSalesOrderLineDocumentStatus(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
//				clickPrintButtonInFindScreen();
				flag = commonFunc.logoutUser();
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
		String Qty = RandomStringUtils.randomNumeric(2);
		if (Qty.equals("00")) {
			Qty = "10";
		}
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		customerDetailsVerification(commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		addItemsInSO(commonFunc.getDataFromTestDataSheet("SO_Similar_Item_StandardPrice"));
		addLineItemQty(Qty);
		chooseHsnCodeAndClickLineWiseSaveButton(commonFunc.getDataFromTestDataSheet("SO_HSNCode"));
		verifyItemCalculationWithLineAmount();
		lineDetailVerification(commonFunc.getDataFromTestDataSheet("SO_Similar_Item_StandardPrice"),
				getTextCurrencyInHeader(), Qty);
		clickLineWiseDetailsSaveButton();
		getSalesOrderTotalAmount();
		verifySalesOrderTaxCalculation();
//		clickPrintButton();
		clickPostButton();
		forwardForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			apprpagefunc.navigateToApprovals();
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			validateSalesOrderDetailsInDocApproval(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			apprpagefunc.verifyApproveWithDocNumber(VariableHandler.getSONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateSalesOrderPage();
				validateSalesOrderLineDocumentStatus(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
//				clickPrintButtonInFindScreen();
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used to Verify the Domestic
	 *         SOGeneral Standard Price With DisSimilar Stock And CustomerUOM
	 * @return boolean flag
	 */
	public boolean verifyDomesticSOGeneralStandardPriceDisSimilarStockAndCustomerUOM() {
		String Qty = RandomStringUtils.randomNumeric(2);
		if (Qty.equals("00")) {
			Qty = "10";
		}
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		customerDetailsVerification(commonFunc.getDataFromTestDataSheet("Customer"));
		addItemsInSO(commonFunc.getDataFromTestDataSheet("SO_DisSimilar_Item_StandardPrice"));
		addLineItemQty(Qty);
		chooseHsnCodeAndClickLineWiseSaveButton(commonFunc.getDataFromTestDataSheet("SO_HSNCode"));
		verifyItemCalculationWithLineAmount();
		lineDetailVerification(commonFunc.getDataFromTestDataSheet("SO_DisSimilar_Item_StandardPrice"),
				getTextCurrencyInHeader(), Qty);
		clickLineWiseDetailsSaveButton();
		getSalesOrderTotalAmount();
		verifySalesOrderTaxCalculation();
//		clickPrintButton();
		clickPostButton();
		forwardForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			apprpagefunc.navigateToApprovals();
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			validateSalesOrderDetailsInDocApproval(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Customer"));
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			apprpagefunc.verifyApproveWithDocNumber(VariableHandler.getSONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateSalesOrderPage();
				validateSalesOrderLineDocumentStatus(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
//				clickPrintButtonInFindScreen();
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used to Verify the Export SOGeneral
	 *         Standard Price With DisSimilar Stock And CustomerUOM
	 * @return boolean flag
	 */
	public boolean verifyExportSOGeneralStandardPriceDisSimilarStockAndCustomerUOM() {
		String Qty = RandomStringUtils.randomNumeric(2);
		if (Qty.equals("00")) {
			Qty = "10";
		}
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		customerDetailsVerification(commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		addItemsInSO(commonFunc.getDataFromTestDataSheet("SO_DisSimilar_Item_StandardPrice"));
		addLineItemQty(Qty);
		chooseHsnCodeAndClickLineWiseSaveButton(commonFunc.getDataFromTestDataSheet("SO_HSNCode"));
		verifyItemCalculationWithLineAmount();
		lineDetailVerification(commonFunc.getDataFromTestDataSheet("SO_DisSimilar_Item_StandardPrice"),
				getTextCurrencyInHeader(), Qty);
		clickLineWiseDetailsSaveButton();
		getSalesOrderTotalAmount();
		verifySalesOrderTaxCalculation();
//		clickPrintButton();
		clickPostButton();
		forwardForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			apprpagefunc.navigateToApprovals();
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			validateSalesOrderDetailsInDocApproval(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			apprpagefunc.verifyApproveWithDocNumber(VariableHandler.getSONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateSalesOrderPage();
				validateSalesOrderLineDocumentStatus(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
//				clickPrintButtonInFindScreen();
				flag = commonFunc.logoutUser();
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
		String Qty = RandomStringUtils.randomNumeric(2);
		if (Qty.equals("00")) {
			Qty = "10";
		}
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		customerDetailsVerification(commonFunc.getDataFromTestDataSheet("Customer"));
		addItemsInSO(commonFunc.getDataFromTestDataSheet("SO_Similar_Item_Catalog"));
		addLineItemQty(Qty);
		chooseHsnCodeAndClickLineWiseSaveButton(commonFunc.getDataFromTestDataSheet("SO_HSNCode"));
		verifyItemCalculationWithLineAmount();
		lineDetailVerification(commonFunc.getDataFromTestDataSheet("SO_Similar_Item_Catalog"),
				getTextCurrencyInHeader(), Qty);
		clickLineWiseDetailsSaveButton();
		getSalesOrderTotalAmount();
		verifySalesOrderTaxCalculation();
//		clickPrintButton();
		clickPostButton();
		forwardForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			apprpagefunc.navigateToApprovals();
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			validateSalesOrderDetailsInDocApproval(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Customer"));
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			apprpagefunc.verifyApproveWithDocNumber(VariableHandler.getSONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateSalesOrderPage();
				validateSalesOrderLineDocumentStatus(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
//				clickPrintButtonInFindScreen();
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used to Verify the Domestic Sales
	 *         Order - price basis is Catalog Price- DisSimilar Stock and Customer
	 *         UOM
	 * @return boolean flag
	 */
	public boolean verifyDomesticSOGeneralCatalogPriceDisSimilarStockAndCustomerUOM() {
		String Qty = RandomStringUtils.randomNumeric(2);
		if (Qty.equals("00")) {
			Qty = "10";
		}
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		customerDetailsVerification(commonFunc.getDataFromTestDataSheet("Customer"));
		addItemsInSO(commonFunc.getDataFromTestDataSheet("SO_DisSimilar_Item_Catalog"));
		addLineItemQty(Qty);
		chooseHsnCodeAndClickLineWiseSaveButton(commonFunc.getDataFromTestDataSheet("SO_HSNCode"));
		verifyItemCalculationWithLineAmount();
		lineDetailVerification(commonFunc.getDataFromTestDataSheet("SO_DisSimilar_Item_Catalog"),
				getTextCurrencyInHeader(), Qty);
		clickLineWiseDetailsSaveButton();
		getSalesOrderTotalAmount();
		verifySalesOrderTaxCalculation();
//		clickPrintButton();
		clickPostButton();
		forwardForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			apprpagefunc.navigateToApprovals();
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			validateSalesOrderDetailsInDocApproval(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Customer"));
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			apprpagefunc.verifyApproveWithDocNumber(VariableHandler.getSONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateSalesOrderPage();
				validateSalesOrderLineDocumentStatus(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
//				clickPrintButtonInFindScreen();
				flag = commonFunc.logoutUser();
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
		String Qty = RandomStringUtils.randomNumeric(2);
		if (Qty.equals("00")) {
			Qty = "10";
		}
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		customerDetailsVerification(commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		addItemsInSO(commonFunc.getDataFromTestDataSheet("SO_Similar_Item_Catalog"));
		addLineItemQty(Qty);
		chooseHsnCodeAndClickLineWiseSaveButton(commonFunc.getDataFromTestDataSheet("SO_HSNCode"));
		verifyItemCalculationWithLineAmount();
		lineDetailVerification(commonFunc.getDataFromTestDataSheet("SO_Similar_Item_Catalog"),
				getTextCurrencyInHeader(), Qty);
		clickLineWiseDetailsSaveButton();
		getSalesOrderTotalAmount();
		verifySalesOrderTaxCalculation();
//		clickPrintButton();
		clickPostButton();
		forwardForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			apprpagefunc.navigateToApprovals();
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			validateSalesOrderDetailsInDocApproval(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			apprpagefunc.verifyApproveWithDocNumber(VariableHandler.getSONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateSalesOrderPage();
				validateSalesOrderLineDocumentStatus(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
//				clickPrintButtonInFindScreen();
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used to Verify the Export Sales
	 *         Order - price basis is Catalog Price- DisSimilar Stock and Customer
	 *         UOM
	 * @return boolean flag
	 */
	public boolean verifyExportSOGeneralCatalogPriceDisSimilarStockAndCustomerUOM() {
		String Qty = RandomStringUtils.randomNumeric(2);
		if (Qty.equals("00")) {
			Qty = "10";
		}
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		customerDetailsVerification(commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		addItemsInSO(commonFunc.getDataFromTestDataSheet("SO_DisSimilar_Item_Catalog"));
		addLineItemQty(Qty);
		chooseHsnCodeAndClickLineWiseSaveButton(commonFunc.getDataFromTestDataSheet("SO_HSNCode"));
		verifyItemCalculationWithLineAmount();
		lineDetailVerification(commonFunc.getDataFromTestDataSheet("SO_DisSimilar_Item_Catalog"),
				getTextCurrencyInHeader(), Qty);
		clickLineWiseDetailsSaveButton();
		getSalesOrderTotalAmount();
		verifySalesOrderTaxCalculation();
//		clickPrintButton();
		clickPostButton();
		forwardForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			apprpagefunc.navigateToApprovals();
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			validateSalesOrderDetailsInDocApproval(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			apprpagefunc.verifyApproveWithDocNumber(VariableHandler.getSONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateSalesOrderPage();
				validateSalesOrderLineDocumentStatus(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
//				clickPrintButtonInFindScreen();
				flag = commonFunc.logoutUser();
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
		String Qty = RandomStringUtils.randomNumeric(2);
		if (Qty.equals("00")) {
			Qty = "10";
		}
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		customerDetailsVerification(commonFunc.getDataFromTestDataSheet("Customer"));
		addItemsInSO(commonFunc.getDataFromTestDataSheet("SO_Similar_Item_PriceList"));
		addLineItemQty(Qty);
		chooseHsnCodeAndClickLineWiseSaveButton(commonFunc.getDataFromTestDataSheet("SO_HSNCode"));
		verifyItemCalculationWithLineAmount();
		lineDetailVerification(commonFunc.getDataFromTestDataSheet("SO_Similar_Item_PriceList"),
				getTextCurrencyInHeader(), Qty);
		clickLineWiseDetailsSaveButton();
		getSalesOrderTotalAmount();
		verifySalesOrderTaxCalculation();
//		clickPrintButton();
		clickPostButton();
		forwardForApproval();
		flag = commonFunc.logoutUser();
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			apprpagefunc.navigateToApprovals();
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			validateSalesOrderDetailsInDocApproval(VariableHandler.getSONumber(),
					commonFunc.getDataFromTestDataSheet("Loc_100"), commonFunc.getDataFromTestDataSheet("Unit_100"),
					commonFunc.getDataFromTestDataSheet("Customer"));
			apprpagefunc.filterDocumentsforApproval(VariableHandler.getSONumber());
			apprpagefunc.verifyApproveWithDocNumber(VariableHandler.getSONumber());
			flag = commonFunc.logoutUser();
			if (flag) {
				commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
						"TvsUsername");
				commonFunc.navigateSalesOrderPage();
				validateSalesOrderLineDocumentStatus(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
//				clickPrintButtonInFindScreen();
				flag = commonFunc.logoutUser();
			}
		}
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
			flag = webDB.waitForElementUntilGivenTime(SalesOrderLocators.NoteAlertMessage, ElementType.Xpath, 45);
			if (flag) {
				logs.logging("INFO", "Note Alert Is Displayed And Sales Order Document Is Cancelled.");
				if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * @author Harikrishna -RMITS This Method Is Used To Click the Header Wise
	 *         Cancel Button And Validate The Sales Order Document status
	 * 
	 * @return boolean flag
	 */
	public boolean verifyCancelSalesOrder() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		customerDetailsVerification(commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		clickCancelButtonInSalesOrderHeader();
		validateSalesOrderHeaderDocumentStatus(VariableHandler.getSONumber(),
				commonFunc.getDataFromTestDataSheet("CancelStatus"));
		flag = commonFunc.logoutUser();
		return flag;
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
						System.out.println(Status);
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
			flag = webDB.waitForElement(SalesOrderLocators.SalesOrderHeader, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add button is Clicked");
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
		verifyClickAddButtonInSo();
		commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.LocationLOV, Location);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.SalesSubVoucherTypeLOV, SubVoucherType);
		commonFunc.checkRefreshCellsDisappear();
		commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.CustomerLov, Customer);
		commonFunc.checkRefreshCellsDisappear();
		clickTickIcon();
		validateLoactionInSOHeader(Location);
		validateUnitInSOHeader(Unit);
		validateCustomerInSOHeader(Customer);
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
			flag = webDB.waitForElement(SalesOrderLocators.SoNumber, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "TickIcon Is Clicked");
				String SONum = webDB.getTextFromElement(SalesOrderLocators.SoNumber, ElementType.Xpath);
				VariableHandler.setSONumber(SONum);
				logs.logging("INFO", "Sales Order Number Is :" + VariableHandler.getSONumber());
				if (webDB.isElementDisplayed(SalesOrderLocators.NoteAlertCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(SalesOrderLocators.NoteAlertCloseIcon, ElementType.Xpath);
				}
			}
		}
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
			flag = webDB.waitForElement(SalesOrderLocators.ItemField, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Line Wise Add Button is Clicked");
				webDB.clickAnElement(SalesOrderLocators.ItemField, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.ItemLov, Item);
				flag = webDB.waitForElement(SalesOrderLocators.ItemInputField, ElementType.Xpath);
				if (flag) {
					String Value = webDB.getAttributeFromElement(SalesOrderLocators.ItemInputField, ElementType.Xpath,
							commonFunc.getDataFromTestDataSheet("Value"));
					logs.logging("INFO", "Get Text Item Id : " + Value);
					flag = Value.contains(Item);
					if (flag) {
						logs.logging("INFO", "Verified Item Id Successfully");
					} else {
						logs.logging("INFO", "Item Id Verification Failed In Sales Order Lines");
						logs.logging("INFO", "Expected Item Id :" + Item + "Actual Item Id :" + Value);
					}
				}
			}
		}
		webDB.pressTabKey();
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
		flag = webDB.waitForElement(SalesOrderLocators.QtyField, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(Field, ElementType.Xpath);
			logs.logging("INFO", "Table Input Field is Clicked");
			webDB.clearTextField(InputField, ElementType.Xpath);
			logs.logging("INFO", "Input text Is Cleared");
			flag = webDB.waitForElement(SalesOrderLocators.QtyField, ElementType.Xpath);
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
		verifyClickAndEnterValueInTableFields(SalesOrderLocators.QtyField, SalesOrderLocators.QtyInputField, Qty);
		webDB.pressTabKey();
		commonFunc.checkRefreshCellsDisappear();
		verifyClickAndEnterValueInTableFields(SalesOrderLocators.UnitCostField, SalesOrderLocators.UnitCostInputField,
				UnitCost);
		webDB.pressTabKey();
		commonFunc.checkRefreshCellsDisappear();
	}

	/**
	 * @author Harikrishna - RMITS This Method Is Used to Choose the HSNCode And
	 *         Click the Line Wise Save Icon In Sales Order Page
	 * 
	 * @param HSNCode
	 */
	public void chooseHsnCodeAndClickLineWiseSaveButton(String HSNCode) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(SalesOrderLocators.HSNCodeField, ElementType.Xpath);
		if (flag) {
			String text = webDB.getTextFromElement(SalesOrderLocators.HSNCodeField, ElementType.Xpath);
			if (text == null || text.trim().isEmpty()) {
				webDB.clickAnElement(SalesOrderLocators.HSNCodeField, ElementType.Xpath);
				logs.logging("INFO", "HSC Code Field Is Clicked");
				commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.HSNCodeLOV, HSNCode);
				flag = webDB.waitForElement(SalesOrderLocators.HSNCodeInputField, ElementType.Xpath);
				if (flag) {
					String Value = webDB.getDomPropertyFromElement(SalesOrderLocators.HSNCodeInputField,
							ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
					logs.logging("INFO", "Get text HSNCode Is :" + Value);
					flag = Value.contains(HSNCode);
					if (flag) {
						logs.logging("INFO", "Verified HSNCode Sucessfully");
					} else {
						logs.logging("INFO", "HSNCode Verification Failed in Sales OrderLines");
						logs.logging("INFO", "Expected HSNCode :" + HSNCode + "Actual HSNCode :" + Value);
					}
					flag = webDB.waitForElement(SalesOrderLocators.LineWiseSaveIcon, ElementType.Xpath);
					if (flag) {
						webDB.clickAnElement(SalesOrderLocators.LineWiseSaveIcon, ElementType.Xpath);
						flag = webDB.waitForElement(SalesOrderLocators.PostButton, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Line Wise Save Button is Clicked");
						}
					}
				}
			} else {
				flag = webDB.waitForElement(SalesOrderLocators.LineWiseSaveIcon, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(SalesOrderLocators.LineWiseSaveIcon, ElementType.Xpath);
					flag = webDB.waitForElement(SalesOrderLocators.PostButton, ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Line Wise Save Button is Clicked");
					}
				}
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
			webDB.clickAnElement(SalesOrderLocators.PostButton, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.WorkApprovalFrame, ElementType.Xpath);
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
	public boolean forwardForApproval() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(SalesOrderLocators.WorkApprovalFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(SalesOrderLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.ForwardApprovalLov,
					commonFunc.getDataFromTestDataSheet("SO_HOD_Value"));
			commonFunc.hardWait(2000);
			webDB.switchToFrame(SalesOrderLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(SalesOrderLocators.OkButtonForward, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesOrderLocators.OkButtonForward, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				flag = webDB.waitForElement(SalesOrderLocators.AddButtonSalesOrder, ElementType.Xpath);
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
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(SalesOrderLocators.SOStatusLineTableText(SONumber), ElementType.Xpath);
				if (flag) {
					String Status = webDB.getTextFromElement(SalesOrderLocators.SOStatusLineTableText(SONumber),
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
						flag = webDB.waitForElement(SalesOrderLocators.HeaderCustDescField, ElementType.Xpath);
						if (flag) {
							String Customer = webDB.getTextFromElement(SalesOrderLocators.HeaderCustIDField,
									ElementType.Xpath);
							logs.logging("INFO", "GetText Customer Id is :" + Customer);
							flag = Customer.equals(ExpCustId);
							if (flag) {
								logs.logging("INFO", "Verified Expected And Actual Customer Description");
								verifyTotalSOAmountCalculationInDocApproval();
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
		flag = webDB.waitForElement(SalesOrderLocators.SOLineWiseDetailsButton, ElementType.CssSelector);
		if (flag) {
			webDB.clickAnElement(SalesOrderLocators.SOLineWiseDetailsButton, ElementType.CssSelector);
			flag = webDB.waitForElement(SalesOrderLocators.LinewisedetailsHeader, ElementType.Xpath);
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
			if (webDB.isElementDisplayed(SalesOrderLocators.NoteAlertCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElement(SalesOrderLocators.NoteAlertCloseIcon, ElementType.Xpath);
				flag = webDB.waitForElement(SalesOrderLocators.PostButton, ElementType.Xpath);
			}
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
			webDB.clickAnElement(SalesOrderLocators.LineWiseSaveIcon, ElementType.Xpath);
			flag = webDB.waitForElement(SalesOrderLocators.PostButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Line Wise Save Button is Clicked");
				commonFunc.checkSuccessToastMessageDisappear();
				commonFunc.hardWait(2000);
				flag = webDB.waitForElement(SalesOrderLocators.SOTotalAmountFieldTrans, ElementType.Xpath);
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
		verifyClickAndEnterValueInTableFields(SalesOrderLocators.QtyField, SalesOrderLocators.QtyInputField, Qty);
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
		BigDecimal decimal_Qnty = new BigDecimal(
				webDB.getTextFromElement(SalesOrderLocators.QtyField, ElementType.Xpath));
		decimal_Qnty = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(decimal_Qnty);
		logs.logging("INFO", "Quantity Passed for Calculation without tax: " + decimal_Qnty);
		BigDecimal decimal_Cost = new BigDecimal(
				webDB.getTextFromElement(SalesOrderLocators.UnitCostField, ElementType.Xpath));
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
			logs.logging("INFO", "After Subtract Discount in Calculation Amount without tax: " + totalCostWithoutTax);

			BigDecimal PerValue = new BigDecimal(
					webDB.getTextFromElement(SalesOrderLocators.TaxPercentField, ElementType.Xpath));
			PerValue = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(PerValue);
			logs.logging("INFO", "Displayed tax Percentage: " + PerValue.intValue() + "%");

			BigDecimal TaxValue = totalCostWithoutTax.divide(new BigDecimal("100")).multiply(PerValue);
			BigDecimal IndividualTax = purchaseOrderFunc
					.verifyRoundOffBigDecimalValueWithReturnBigDecimal(TaxValue.divide(new BigDecimal("2")));
			logs.logging("INFO", "Individual Tax Amount: " + IndividualTax);
			TaxValue = IndividualTax.multiply(new BigDecimal("2"));
			logs.logging("INFO", "Expected Tax Value After Tax calculation: " + TaxValue);

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

			flag = taxAmount.equals(TaxValue);
			if (flag) {
				logs.logging("INFO", "Tax Amount Verified, Tax added value: " + taxAmount);
				logs.logging("INFO", "verifing total amount after calculating tax amount!!!");
				BigDecimal DisplayLineAmnt = new BigDecimal(
						webDB.getDomPropertyFromElement(SalesOrderLocators.SOTotalAmountFieldTrans, ElementType.Xpath,
								commonFunc.getDataFromTestDataSheet("Value")));
				DisplayLineAmnt = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(DisplayLineAmnt);
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
		List<WebElement> OrderedQtyList = webDB.getDriver().findElements(By.xpath(SalesOrderLocators.QtyField));
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
				String TradeDiscountPercentage = webDB.getTextFromElement(
						SalesOrderLocators.SOTradeDiscountPercentageReadOnlyField, ElementType.Xpath);
				logs.logging("INFO", "Get Text Trade Discount Percentage is " + TradeDiscountPercentage);
				BigDecimal TradeAmountOA = verifyDiscountAmountCalculation(LineAmount,
						new BigDecimal(TradeDiscountPercentage));
				BigDecimal SpecAssesableValue = LineAmount.subtract(TradeAmountOA);
				logs.logging("INFO", "Expected Special Assesable Value :" + SpecAssesableValue);

				SpecAssesableValue = verifyRoundOffBigDecimalValueWithReturnBigDecimal(SpecAssesableValue);
				BigDecimal SplDiscAmount = verifyDiscountAmountCalculation(SpecAssesableValue,
						new BigDecimal(SpecialDiscount));
				logs.logging("INFO", "Expected Special Discount Amount :" + SplDiscAmount);
				logs.logging("INFO", "Expected Special Discount Percentage :" + SpecialDiscount);

				BigDecimal CashAssesableValue = SpecAssesableValue.subtract(SplDiscAmount);
				logs.logging("INFO", "Expected Cash Assesable Value : " + CashAssesableValue);

				CashAssesableValue = verifyRoundOffBigDecimalValueWithReturnBigDecimal(CashAssesableValue);
				BigDecimal cashDiscountAmount = verifyDiscountAmountCalculation(CashAssesableValue,
						new BigDecimal(CashDiscount));
				logs.logging("INFO", "Expected Cash Discount Amount :" + cashDiscountAmount);
				logs.logging("INFO", "Expected Cash Discount Percentage :" + CashDiscount);

				BigDecimal Qty = new BigDecimal(quantity);

				DiscAmountPerQty = (TradeAmountOA.add(SplDiscAmount).add(cashDiscountAmount)).divide(Qty, 2,
						RoundingMode.HALF_UP);
				logs.logging("INFO", "Expected Discount Amt Per Qty :" + DiscAmountPerQty);

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
				verifyDomPropertyValidation(SalesOrderLocators.DiscountAmtPerQty, DiscAmountPerQty.toPlainString());
				logs.logging("INFO", "Discount Amount Per Qty is Verified");
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
			}
		} else {
			commonFunc.checkRefreshCellsDisappear();

			BigDecimal TradeAmount = verifyDiscountAmountCalculation(LineAmount, new BigDecimal(TradeDiscount));
			logs.logging("INFO", "Expected Trade Discount Amount :" + TradeAmount);
			logs.logging("INFO", "Expected Trade Discount Percentage :" + TradeDiscount);

			BigDecimal SpecAssesableValue = LineAmount.subtract(TradeAmount);
			logs.logging("INFO", "Expected Special Assesable Value :" + SpecAssesableValue);

			SpecAssesableValue = verifyRoundOffBigDecimalValueWithReturnBigDecimal(SpecAssesableValue);
			BigDecimal SplDiscAmount = verifyDiscountAmountCalculation(SpecAssesableValue,
					new BigDecimal(SpecialDiscount));
			logs.logging("INFO", "Expected Special Discount Amount :" + SplDiscAmount);
			logs.logging("INFO", "Expected Special Discount Percentage :" + SpecialDiscount);

			BigDecimal CashAssesableValue = SpecAssesableValue.subtract(SplDiscAmount);
			logs.logging("INFO", "Expected Cash Assesable Value : " + CashAssesableValue);

			CashAssesableValue = verifyRoundOffBigDecimalValueWithReturnBigDecimal(CashAssesableValue);
			BigDecimal cashDiscountAmount = verifyDiscountAmountCalculation(CashAssesableValue,
					new BigDecimal(CashDiscount));
			logs.logging("INFO", "Expected Cash Discount Amount :" + cashDiscountAmount);
			logs.logging("INFO", "Expected Cash Discount Percentage :" + CashDiscount);

			BigDecimal Qty = new BigDecimal(quantity);

			DiscAmountPerQty = (TradeAmount.add(SplDiscAmount).add(cashDiscountAmount)).divide(Qty, 2,
					RoundingMode.HALF_UP);
			logs.logging("INFO", "Expected Discount Amt Per Qty :" + DiscAmountPerQty);

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
			verifyDomPropertyValidation(SalesOrderLocators.DiscountAmtPerQty, DiscAmountPerQty.toPlainString());
			logs.logging("INFO", "Discount Amount Per Qty is Verified");
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
	public static String getTextCurrencyInHeader() {
		String HeaderCurrency = null;
		flag = webDB.waitForElement(SalesOrderLocators.Currency, ElementType.Xpath);
		if (flag) {
			HeaderCurrency = webDB.getTextFromElement(SalesOrderLocators.Currency, ElementType.Xpath);
			logs.logging("INFO", "Get Text Header Currency Is : " + HeaderCurrency);
		}
		return HeaderCurrency;
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
		flag = webDB.waitForElement(SalesOrderLocators.PriceBasisField, ElementType.Xpath);
		if (flag) {
			PriceBasis = webDB.getDomPropertyFromElement(SalesOrderLocators.PriceBasisField, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			logs.logging("INFO", "Get Text PriceBasis Of the Item Is: " + PriceBasis);
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
			flag = webDB.waitForElement(SalesOrderLocators.PrintCustDesc, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated Sucessfully To General Tab");
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
				String TradeDiscountPercentage = webDB.getTextFromElement(
						SalesOrderLocators.SOTradeDiscountPercentageReadOnlyField, ElementType.Xpath);
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
		VariableHandler.setSOTotalDiscountAmount(BigDecimal.ZERO);
		VariableHandler.setSOTotalGrossAmount(BigDecimal.ZERO);
		VariableHandler.setSOTotalLineAmount(BigDecimal.ZERO);
		webDB.navigateToRefresh();
		webDB.switchToDefaultContent();
//			commonFunc.enterUrl();
	}
}
