package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 10, 2025
 */
import static org.testng.Assert.assertTrue;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import commonfunctions.CommonFunctions;
import commonfunctions.DBCommonFunctions;
import commonfunctions.ItemDetailsHandler;
import commonfunctions.VariableHandler;
import commonfunctions.ItemDetailsHandler.POALineDetailsName;
import commonfunctions.ItemDetailsHandler.POLineDetailsName;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.OpenPoRcLocators;
import commonlocators.supplychainmanagement.POAmendmentLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import commonlocators.supplychainmanagement.PurchaseRequestLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class POAmendmentFunctions {
	private boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	public ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	private static PurchaseOrderFunctions purchaseOrderFunctions = new PurchaseOrderFunctions();
	private static ItemDetailsHandler itemDetailHandler = new ItemDetailsHandler();
	private static DBCommonFunctions dbCommonFunctions = new DBCommonFunctions();

	/**
	 * Verify Direct PO Domestic GEneral With Similar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticGeneralWithSimilarStockAndSupplierUOM() {
		purchaseOrderFunctions.verifyDirectPODomesticGeneralSimilarUOM();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyPOAmendmentHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyCreateItemInPoAmendment(commonFunc.getDataFromTestDataSheet("Similar_Item_02"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		LineDetailValidation(commonFunc.getDataFromTestDataSheet("Similar_Item_02"));
		navigateToLineDetailPage();
		verifyEnterAmountForDiscountCalculation(commonFunc.getDataFromTestDataSheet("FiveValue"));
		discountCalculationVerification(commonFunc.getDataFromTestDataSheet("FiveValue"));
		verifyLineDetailsUpdation();
		webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
		verifyNavigateLineDetailTwoPage();
		verifyLineDetailsTwoVerifications(commonFunc.getDataFromTestDataSheet("Similar_Item_02"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();

		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			makingTotalAmountCalculationToZero();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			String[] Totalitem = { commonFunc.getDataFromTestDataSheet("Similar_Item_02"),
					commonFunc.getDataFromTestDataSheet("GwlItem_Serial") };

			verifyPurchaseOrderValidationWithAmendment(commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("QuantityValue"),
					commonFunc.getDataFromTestDataSheet("QuantityValue"), Totalitem);
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * Verify Direct PO Domestic Material With Similar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticMaterialWithSimilarStockAndSupplierUOM() {
		purchaseOrderFunctions.verifyDirectPODomesticMaterialSimilarUOM();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyPOAmendmentHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyCreateItemInPoAmendment(commonFunc.getDataFromTestDataSheet("Similar_Item_02"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		LineDetailValidation(commonFunc.getDataFromTestDataSheet("Similar_Item_02"));
		navigateToLineDetailPage();
		verifyEnterAmountForDiscountCalculation(commonFunc.getDataFromTestDataSheet("FiveValue"));
		discountCalculationVerification(commonFunc.getDataFromTestDataSheet("FiveValue"));
		verifyLineDetailsUpdation();
		webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
		verifyNavigateLineDetailTwoPage();
		verifyLineDetailsTwoVerifications(commonFunc.getDataFromTestDataSheet("Similar_Item_02"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			makingTotalAmountCalculationToZero();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			String[] Totalitem = { commonFunc.getDataFromTestDataSheet("Similar_Item_02"),
					commonFunc.getDataFromTestDataSheet("GwlItem_Serial") };

			verifyPurchaseOrderValidationWithAmendment(commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("QuantityValue"),
					commonFunc.getDataFromTestDataSheet("QuantityValue"), Totalitem);
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * Verify Direct PO Import General With Similar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPOImportGeneralWithSimilarStockAndSupplierUOM() {
		purchaseOrderFunctions.verifyDirectPOImportGeneralSimilarUOM();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		verifyPOAmendmentHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyCreateItemInPoAmendment(commonFunc.getDataFromTestDataSheet("Similar_Item_02"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		LineDetailValidation(commonFunc.getDataFromTestDataSheet("Similar_Item_02"));
		navigateToLineDetailPage();
		verifyEnterAmountForDiscountCalculation(commonFunc.getDataFromTestDataSheet("FiveValue"));
		discountCalculationVerification(commonFunc.getDataFromTestDataSheet("FiveValue"));
		verifyLineDetailsUpdation();
		webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
		verifyNavigateLineDetailTwoPage();
		verifyLineDetailsTwoVerifications(commonFunc.getDataFromTestDataSheet("Similar_Item_02"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();

		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			makingTotalAmountCalculationToZero();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			String[] Totalitem = { commonFunc.getDataFromTestDataSheet("Similar_Item_02"),
					commonFunc.getDataFromTestDataSheet("GwlItem_Serial") };

			verifyPurchaseOrderValidationWithAmendment(commonFunc.getDataFromTestDataSheet("CurrencyUsd"),
					commonFunc.getDataFromTestDataSheet("QuantityValue"),
					commonFunc.getDataFromTestDataSheet("QuantityValue"), Totalitem);
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * Verify Direct Po Import Material With Similar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPOImportMaterialWithSimilarStockAndSupplierUOM() {
		purchaseOrderFunctions.verifyDirectPOImportMaterialSimilarUOM();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		verifyPOAmendmentHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyCreateItemInPoAmendment(commonFunc.getDataFromTestDataSheet("Similar_Item_02"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		LineDetailValidation(commonFunc.getDataFromTestDataSheet("Similar_Item_02"));
		navigateToLineDetailPage();
		verifyEnterAmountForDiscountCalculation(commonFunc.getDataFromTestDataSheet("FiveValue"));
		discountCalculationVerification(commonFunc.getDataFromTestDataSheet("FiveValue"));
		verifyLineDetailsUpdation();
		webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
		verifyNavigateLineDetailTwoPage();
		verifyLineDetailsTwoVerifications(commonFunc.getDataFromTestDataSheet("Similar_Item_02"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();

		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			makingTotalAmountCalculationToZero();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			String[] Totalitem = { commonFunc.getDataFromTestDataSheet("Similar_Item_02"),
					commonFunc.getDataFromTestDataSheet("GwlItem_Serial") };

			verifyPurchaseOrderValidationWithAmendment(commonFunc.getDataFromTestDataSheet("CurrencyUsd"),
					commonFunc.getDataFromTestDataSheet("QuantityValue"),
					commonFunc.getDataFromTestDataSheet("QuantityValue"), Totalitem);
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Pending Po Amendment
	 * 
	 * @author Deepan-GWL
	 */
	public void createPendingPOAmendment() {
		flag = webDB.waitForElement(POAmendmentLocators.POPendingIcon, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(POAmendmentLocators.POPendingIcon, ElementType.Xpath);
			flag = webDB.waitForElement(POAmendmentLocators.SingleAmendButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(POAmendmentLocators.SingleAmendButton, ElementType.Xpath);
				flag = webDB.waitForElement(POAmendmentLocators.POLocationLovButton, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Navigated To Single PO Amendment Page");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Update Single PO Amendment Details
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyUpdateSinglePoAmendmentDetails(String Location, String PoNumber, String Supplier,
			String Currency) {
		commonFunc.searchAndSelectValueInFilters(POAmendmentLocators.POLocationLovButton, Location);
		commonFunc.searchAndSelectValueInFilters(POAmendmentLocators.PoPrefixLovButton, VariableHandler.getPONumber());
		commonFunc.hardWait(4000);
		commonFunc.checkRefreshCellsDisappear();
		purchaseOrderFunctions.verifyDomPropertyValidation(POAmendmentLocators.LocationIdInput, Location);
		purchaseOrderFunctions.verifyDomPropertyValidation(POAmendmentLocators.PONumberInput, PoNumber);
//		purchaseOrderFunctions.verifyDomPropertyValidation(POAmendmentLocators.SupplierName, Supplier);
		purchaseOrderFunctions.verifyDomPropertyValidation(POAmendmentLocators.CurrencyInput, Currency);
	}

	/**
	 * Verify Tick Icon Single Amendment
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyTickIconSingleAmendment() {
		flag = webDB.waitForElement(POAmendmentLocators.TickButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(POAmendmentLocators.TickButton, ElementType.Xpath);
			flag = webDB.waitForElement(POAmendmentLocators.HeaderLocationId, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * Verify PO Amendment Header Validation
	 * 
	 * @author Deepan-GWL
	 * @param Location
	 * @param PoNumber
	 * @param Supplier
	 * @param Currency
	 */
	public void verifyPOAmendmentHeaderValidation(String Location, String PoNumber, String Supplier, String Currency) {
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.HeaderLocationId, Location);
		purchaseOrderFunctions.verifyDomPropertyValidation(POAmendmentLocators.CurrencyHeaderInput, Currency);
		purchaseOrderFunctions.verifyDomPropertyValidation(POAmendmentLocators.PONumberHeaderInput, PoNumber);
		purchaseOrderFunctions.verifyDomPropertyValidation(POAmendmentLocators.SupplierNameHeaderInput, Supplier);
	}

	/**
	 * Verify Create Item In Po Amendment
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 */
	public void verifyCreateItemInPoAmendment(String Item) {
		flag = webDB.waitForElement(POAmendmentLocators.AddItemButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Item Add Button Is Displayed Successfully");
			webDB.clickAnElement(POAmendmentLocators.AddItemButton, ElementType.Xpath);
			flag = webDB.waitForElement(POAmendmentLocators.ItemTable(1), ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Item Add Button Is Clicked");
				webDB.clickAnElement(POAmendmentLocators.ItemTable(1), ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(POAmendmentLocators.ItemInputButton, Item);
				flag = webDB.waitForElement(POAmendmentLocators.ItemInput, ElementType.Xpath);
				if (flag) {
					String value = webDB.getAttributeFromElement(POAmendmentLocators.ItemInput, ElementType.Xpath,
							commonFunc.getDataFromTestDataSheet("Value"));
					logs.logging("INFO", "Get Text Item Name Value from Table Is: " + value);
					flag = value.contains(Item);
					if (flag) {
						logs.logging("INFO", "Verified Item Value Successfully");
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Create Item In Po
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAddItemInPOWithQuantityAndUnitCost(String quantity, String unitcost) {
		try {
			commonFunc.hardWait(1000);
			purchaseOrderFunctions.verifyAddTableSpecs(POAmendmentLocators.NewQuantity(1),
					POAmendmentLocators.NewQuantity, quantity);
			webDB.pressEnterKey();
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(5000);
			purchaseOrderFunctions.verifyAddTableSpecs(POAmendmentLocators.NewUnitCost(1),
					POAmendmentLocators.NewUnitCost, unitcost);
			webDB.pressEnterKey();
			commonFunc.checkRefreshCellsDisappear();
			String POANumbervalue = webDB.getDomPropertyFromElement(POAmendmentLocators.POANumber, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			commonFunc.hardWait(3000);
			logs.logging("INFO", "Get PO Number Is: " + POANumbervalue);
			VariableHandler.setPOANumber(POANumbervalue);
		} catch (Exception e) {
		}
	}

	/**
	 * Verify Tick Icon Create Item
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyTickIconcreateItem() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(POAmendmentLocators.ItemTickButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(POAmendmentLocators.ItemTickButton, ElementType.Xpath);
			commonFunc.hardWait(2500);
			while (webDB.isElementDisplayed("(//button[text()='OK'])[last()]", ElementType.Xpath)) {
				webDB.clickAnElement("(//button[text()='OK'])[last()]", ElementType.Xpath);
			}
			flag = webDB.waitForElement(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Item Tick Icon Is Clicked");
				webDB.clickAnElement(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath);
			}
			logs.logging("INFO", "Toast Close Icon Is Clicked");
			flag = webDB.waitForElement(POAmendmentLocators.HeaderTickButton, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * Line Details Validation
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 */
	public void LineDetailValidation(String Item) {
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.ItemTable(1),
				itemDetailHandler.getPOALineDetailsforGivenItem(Item, POALineDetailsName.ITEMID));
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.ItemDesc(1),
				itemDetailHandler.getPOALineDetailsforGivenItem(Item, POALineDetailsName.ITEM_DESCRIPTION));
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.UOM(1),
				itemDetailHandler.getPOALineDetailsforGivenItem(Item, POALineDetailsName.UOM));
	}

	/**
	 * Navigate To Line Details Page
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void navigateToLineDetailPage() {
		webDB.moveToElement(POAmendmentLocators.ItemTable(1), ElementType.Xpath);
		webDB.moveToElement(POAmendmentLocators.Details(1), ElementType.Xpath);
		flag = webDB.waitForElement(POAmendmentLocators.Details(1), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(POAmendmentLocators.Details(1), ElementType.Xpath);
			flag = webDB.waitForElement(POAmendmentLocators.Details, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
				logs.logging("INFO", "Navigated To Line Details Page");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Enter Amunt For Discount Calculation
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyEnterAmountForDiscountCalculation(String Tradevalue, String SplValue, String CashValue) {
		flag = webDB.waitForElement(POAmendmentLocators.NewTradeAssemblePct, ElementType.Xpath);
		if (flag) {
			webDB.clearText(POAmendmentLocators.NewTradeAssemblePct, ElementType.Xpath);
			webDB.sendTextToAnElement(POAmendmentLocators.NewTradeAssemblePct, Tradevalue, ElementType.Xpath);
			webDB.pressEnterKey();
			commonFunc.checkRefreshCellsDisappear();
			purchaseOrderFunctions.verifyDomPropertyValidation(POAmendmentLocators.NewTradeAssemblePct, Tradevalue);
			webDB.clearText(POAmendmentLocators.NewSplAssemblePct, ElementType.Xpath);
			webDB.sendTextToAnElement(POAmendmentLocators.NewSplAssemblePct, SplValue, ElementType.Xpath);
			webDB.pressEnterKey();
			commonFunc.checkRefreshCellsDisappear();
			purchaseOrderFunctions.verifyDomPropertyValidation(POAmendmentLocators.NewSplAssemblePct, SplValue);
			webDB.clearText(POAmendmentLocators.NewCashAssemblePct, ElementType.Xpath);
			webDB.sendTextToAnElement(POAmendmentLocators.NewCashAssemblePct, CashValue, ElementType.Xpath);
			webDB.pressEnterKey();
			commonFunc.checkRefreshCellsDisappear();
			purchaseOrderFunctions.verifyDomPropertyValidation(POAmendmentLocators.NewCashAssemblePct, CashValue);
		}
		assertTrue(flag);
	}

	/**
	 * Discount Calculation Verification
	 * 
	 * @author Deepan-GWL
	 */
	public void discountCalculationVerification(String Value) {
		BigDecimal TotalDiscountAmount = new BigDecimal("0");
		BigDecimal LineAmount = VariableHandler.getPOALineAmount();
		BigDecimal TradeAmount = purchaseOrderFunctions.verifyDiscountAmountCalculation(LineAmount,
				new BigDecimal(Value));
		BigDecimal SpecAssembleValue = LineAmount.subtract(TradeAmount);
		SpecAssembleValue = purchaseOrderFunctions.verifyRoundOffBigDecimalValueWithReturnBigDecimal(SpecAssembleValue);
		BigDecimal SplDiscAmount = purchaseOrderFunctions.verifyDiscountAmountCalculation(SpecAssembleValue,
				new BigDecimal(Value));
		BigDecimal CashAssembleValue = SpecAssembleValue.subtract(SplDiscAmount);
		CashAssembleValue = purchaseOrderFunctions.verifyRoundOffBigDecimalValueWithReturnBigDecimal(CashAssembleValue);
		BigDecimal cashDiscount = purchaseOrderFunctions.verifyDiscountAmountCalculation(CashAssembleValue,
				new BigDecimal(Value));
		TotalDiscountAmount = (TradeAmount.add(SplDiscAmount).add(cashDiscount));

		VariableHandler.setPOADiscountAmount(TotalDiscountAmount);
		VariableHandler.setTotalPOADiscountAmount(VariableHandler.getTotalDiscountAmount().add(TotalDiscountAmount));
		purchaseOrderFunctions.verifyDomPropertyValidation(POAmendmentLocators.NewTradeAssembleValue,
				LineAmount.stripTrailingZeros().toPlainString());
		purchaseOrderFunctions.verifyDomPropertyValidation(POAmendmentLocators.NewSplAssembleValue,
				SpecAssembleValue.stripTrailingZeros().toPlainString());
		purchaseOrderFunctions.verifyDomPropertyValidation(POAmendmentLocators.NewCashAssembleValue,
				CashAssembleValue.stripTrailingZeros().toPlainString());
		purchaseOrderFunctions.verifyDomPropertyValidation(POAmendmentLocators.NewTradeAssembleAmount,
				TradeAmount.stripTrailingZeros().toPlainString());
		purchaseOrderFunctions.verifyDomPropertyValidation(POAmendmentLocators.NewSplAssembleAmount,
				SplDiscAmount.stripTrailingZeros().toPlainString());
		purchaseOrderFunctions.verifyDomPropertyValidation(POAmendmentLocators.NewCashAssembleAmount,
				cashDiscount.stripTrailingZeros().toPlainString());

	}

	/**
	 * This Method Is Used To verify Item Calculation
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 */
	public void verifyItemCalculationWithLineAmount() {
		int i = 1;
		String Item;
		commonFunc.checkRefreshCellsDisappear();
//		List<WebElement> OrderedQtyList = webDB.getDriver().findElements(By.xpath(POAmendmentLocators.ListQuantity));
//		for (int i = 1; i <= OrderedQtyList.size(); i++) {
		Item = webDB.getDriver().findElement(By.xpath(POAmendmentLocators.ItemTable(i))).getText();
		logs.logging("INFO", "Get Text Item For Calculation Is: " + Item);
		String OrderedQty = webDB.getDriver().findElement(By.xpath(POAmendmentLocators.NewQuantity(i))).getText();
		logs.logging("INFO", "Get Text Ordered Quantity Is: " + OrderedQty);
		String UnitCost = webDB.getDriver().findElement(By.xpath(POAmendmentLocators.NewUnitCost(i))).getText();
		logs.logging("INFO", "Get Text Unit Cost Is: " + UnitCost);

		BigDecimal Quantityd = new BigDecimal(OrderedQty);
		BigDecimal Costd = new BigDecimal(UnitCost);
		BigDecimal LineAmountd = Costd.multiply(Quantityd);
		BigDecimal ExpectedLineAmountd = purchaseOrderFunctions
				.verifyRoundOffBigDecimalValueWithReturnBigDecimal(LineAmountd);
		VariableHandler.setPOALineAmount(ExpectedLineAmountd);
//			VariableHandler.setTotalLineAmount(VariableHandler.getTotalLineAmount().add(ExpectedLineAmountd));
//		}
		assertTrue(flag);
	}

	/**
	 * verify Line Details Two Verification
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 */
	public void verifyLineDetailsTwoVerifications(String Item) {
		purchaseOrderFunctions.verifyDomPropertyValidation(POAmendmentLocators.WareHouseName,
				itemDetailHandler.getPOALineDetailsforGivenItem(Item, POALineDetailsName.WH_DEPTNAME));
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.PriceBasis,
				itemDetailHandler.getPOALineDetailsforGivenItem(Item, POALineDetailsName.COST_BASIS));
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.SupplierUOM,
				itemDetailHandler.getPOALineDetailsforGivenItem(Item, POALineDetailsName.SUPL_UOM));
		purchaseOrderFunctions.verifyDomPropertyValidation(POAmendmentLocators.ConvFactor,
				itemDetailHandler.getPOALineDetailsforGivenItem(Item, POALineDetailsName.CONVERSION_FACTOR));
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.GL_Acc_No,
				itemDetailHandler.getPOALineDetailsforGivenItem(Item, POALineDetailsName.GL_ACC_NO));
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.GL_Acc_Desc,
				itemDetailHandler.getPOALineDetailsforGivenItem(Item, POALineDetailsName.GL_ACC_DESC));
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.CPCCode,
				itemDetailHandler.getPOALineDetailsforGivenItem(Item, POALineDetailsName.CPC_CODE));
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.CPC_Desc,
				itemDetailHandler.getPOALineDetailsforGivenItem(Item, POALineDetailsName.CPC_DESC));

		flag = webDB.waitForElement(POAmendmentLocators.LineTickButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(POAmendmentLocators.LineTickButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();

		}
		webDB.switchToDefaultContent();
		try {
			WebElement element = webDB.getDriver().findElement(By.xpath(POAmendmentLocators.Line_CloseButton));
			WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(20));
			wait.until(ExpectedConditions.invisibilityOf(element));
		} catch (Exception e) {
		}
		flag = webDB.waitForElement(POAmendmentLocators.HeaderLocationId, ElementType.Xpath);
		assertTrue(flag);
	}

	/**
	 * Verify Header Tick And Post
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyHeaderTickAndPost() {
		flag = webDB.waitForElement(POAmendmentLocators.HeaderTickButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Header Tick Icon Is Displayed");
			webDB.clickAnElement(POAmendmentLocators.HeaderTickButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForClickElement(POAmendmentLocators.HeaderPostIcon, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.clickAnElement(POAmendmentLocators.HeaderPostIcon, ElementType.Xpath);
				logs.logging("INFO", "Header Post Icon Is Clicked");
				flag = (webDB.waitForElementUntilGivenTime(CommonUiLocators.PostToastMessage, ElementType.Xpath, 10)
						|| webDB.waitForElement(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath));
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Line Details Updation
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyLineDetailsUpdation(String... hsncode) {
		webDB.moveToElement(POAmendmentLocators.NewCashAssembleAmount, ElementType.Xpath);
		webDB.moveToElement(POAmendmentLocators.HSNCode, ElementType.Xpath);
		flag = webDB.waitForElement(POAmendmentLocators.HSNCode, ElementType.Xpath);
		if (flag) {
			if (hsncode.length > 0) {
				commonFunc.searchAndSelectValueInFilters(POAmendmentLocators.HSNCode,
						commonFunc.getDataFromTestDataSheet("HsnCode75052200"));
			} else {
				commonFunc.searchAndSelectValueInFilters(POAmendmentLocators.HSNCode,
						commonFunc.getDataFromTestDataSheet("HsnCode75052200"));
			}

		}
		assertTrue(flag);

	}

	/**
	 * Verify Navigate Line Detail Two
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void verifyNavigateLineDetailTwo() {
		webDB.moveToElement(POAmendmentLocators.LineDetailsTwo, ElementType.Xpath);
		flag = webDB.waitForElement(POAmendmentLocators.LineDetailsTwo, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(POAmendmentLocators.LineDetailsTwo, ElementType.Xpath);
			flag = webDB.waitForElement(POAmendmentLocators.Supplier_Item, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated To Line Details Two Page");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Waiting For Approval
	 * 
	 * @author Deepan-GWL
	 */
	public boolean verifyWaitingForApproval() {
		commonFunc.hardWait(3000);
		if (webDB.verifyIsElementDisplayed(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath)) {
			flag = true;
			VariableHandler.setApprovalFlag(flag);
			webDB.switchToFrame(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To Workflow Frame");
			commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.ForwardApproval,
					commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.hardWait(2000);
			webDB.switchToFrame(PurchaseOrderPageLocators.WorkApprovalFrame, ElementType.Xpath);
			logs.logging("INFO", "Switched To WorkFlow Frame");
			flag = webDB.waitForElement(PurchaseOrderPageLocators.OkButtonForward, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(PurchaseOrderPageLocators.OkButtonForward, ElementType.Xpath);
				logs.logging("INFO", "Ok Button Is Clicked");
				webDB.switchToDefaultContent();
				commonFunc.hardWait(2000);
				webDB.waitUntilElementToNotBeStale(POAmendmentLocators.POPendingIcon, ElementType.Xpath);
				flag = webDB.waitForElement(POAmendmentLocators.POPendingIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Verified Navigate To POA Home Page");
				}
			}
			assertTrue(flag);
		} else {
			flag = false;
			VariableHandler.setApprovalFlag(flag);
		}
		return flag;
	}

	/**
	 * Verify Search PO and Validate PO Status
	 * 
	 * @author Deepan-GWL
	 * @param POno
	 * @param ExpectedStatus
	 */
	public void verifyValidatePOAStatus(String POno, String ExpectedStatus) {
		commonFunc.searchAndSelectValueInFilters(POAmendmentLocators.POANumberLovButton, POno);
		flag = webDB.waitForElement(OpenPoRcLocators.HeaderCheckBox, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenPoRcLocators.HeaderCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(POAmendmentLocators.SearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(POAmendmentLocators.SearchButton, ElementType.Xpath);
				logs.logging("INFO", "Search Button Is Clicked");
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				commonFunc.hardWait(5000);
				webDB.clickAnElementWithoutException("//button[@aria-label='Remove Filter']", ElementType.Xpath);
				flag = webDB.waitForElement(POAmendmentLocators.POANumberStatus(POno), ElementType.Xpath);
				if (flag) {

					String Status = webDB.getTextFromElement(POAmendmentLocators.POANumberStatus(POno),
							ElementType.Xpath);
					flag = Status.contains(ExpectedStatus);
					if (flag) {
						logs.logging("INFO", "Expected Status: " + ExpectedStatus);
						logs.logging("INFO", "Verified Status ,Displayed Status: " + Status);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Purchase OOrder Validation With Amendment
	 * 
	 * @author Deepan-GWL
	 * @param currency
	 * @param Quantity
	 */
	public void verifyPurchaseOrderValidationWithAmendment(String currency, String Quantity, String discount,
			String[] item, boolean... Potypeflag) {
		flag = true;
		List<WebElement> OrderedQtyList = webDB.getDriver()
				.findElements(By.xpath(PurchaseOrderPageLocators.OrderedQuantity));
		if (OrderedQtyList.size() == 2) {

			for (int i = 1; i <= OrderedQtyList.size(); i++) {
				if (flag) {
					a: for (String singleitem : item) {

						String Item = webDB.getDriver().findElement(By.xpath(PurchaseOrderPageLocators.ItemName(i)))
								.getText();
						if (singleitem.equals(Item)) {
							flag = true;
							verifyItemCalculationWithLineAmount(i);
							verifyPoUOM(i, Item);
							verifyLineDetailVerification(Item, Quantity, i, discount, currency, Potypeflag);
							verifyTaxCalculationAfterDiscount(currency, Item, i);
							verifyPOTotalAmountCalculation();
							verifyTotalAmountCalculation();
							flag = true;
							break a;
						} else {
							flag = false;
						}
					}

				}
			}
		}
		verifyGeneralTabTotalAmountCalculation();
	}

	/**
	 * This Method Is Used To verify Item Calculation
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 */
	public void verifyItemCalculationWithLineAmount(int i) {
		String Item = webDB.getDriver().findElement(By.xpath(PurchaseOrderPageLocators.ItemName(i))).getText();
		logs.logging("INFO", "Get Text Item For Calculation Is: " + Item);
		String OrderedQty;
		String CSQty = webDB.getDriver().findElement(By.xpath(PurchaseOrderPageLocators.CsQty(i))).getText();
		if (CSQty.equals("0")) {
			OrderedQty = webDB.getDriver().findElement(By.xpath(PurchaseOrderPageLocators.OrderedQty(i))).getText();
			logs.logging("INFO", "Get Text Ordered Quantity Is: " + OrderedQty);
		} else {
			OrderedQty = CSQty;
			logs.logging("INFO", "Get Text Ordered Quantity Is: " + OrderedQty);
		}

		String UnitCost = webDB.getDriver().findElement(By.xpath(POAmendmentLocators.UnitCostTable(i))).getText();
		logs.logging("INFO", "Get Text Unit Cost Is: " + UnitCost);
		BigDecimal Quantityd = new BigDecimal(OrderedQty);
		BigDecimal Costd = new BigDecimal(UnitCost);
		BigDecimal LineAmountd = Costd.multiply(Quantityd);
		BigDecimal ExpectedLineAmountd = purchaseOrderFunctions
				.verifyRoundOffBigDecimalValueWithReturnBigDecimal(LineAmountd);
		VariableHandler.setLineAmount(ExpectedLineAmountd);
		VariableHandler.setTotalLineAmount(VariableHandler.getTotalLineAmount().add(ExpectedLineAmountd));
		System.out.println("Expected Double Line Amount IS: " + ExpectedLineAmountd);
//		String ExpectedLineAmount = purchaseOrderFunctions.verifyRoundOffBigDecimalValueWithReturnString(LineAmountd);
//			String ExpectedLineAmount = String.format("%.2f", LineAmountd);

		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify PO UOM
	 * 
	 * @author Deepan-GWL
	 * @param ExpectedUOM
	 */
	public void verifyPoUOM(int i, String Item) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseOrderPageLocators.UOM(i), ElementType.Xpath);
		if (flag) {
			String ActualUOM = webDB.getTextFromElement(PurchaseOrderPageLocators.UOM(i), ElementType.Xpath);
			String ExpectedUOM = itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.UOM);
			logs.logging("INFO", "Expected UOM Is: " + ExpectedUOM);
			logs.logging("INFO", "Actual Get Text UOM Is: " + ActualUOM);
			flag = ActualUOM.equals(ExpectedUOM);
			if (flag) {
				logs.logging("INFO", "Verified Actual UOM With Expected UOM");
			}

		}
		assertTrue(flag);
	}

	/**
	 * Navigate To Line Details Page
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void navigateToLineDetailPage(int i, boolean... Potypeflag) {
		webDB.moveToElement(PurchaseOrderPageLocators.ItemName(i), ElementType.Xpath);
		if (Potypeflag.length == 0) {
			webDB.moveToElement(POAmendmentLocators.ServiceLineDetailButton(i), ElementType.Xpath);
			flag = webDB.waitForElement(POAmendmentLocators.ServiceLineDetailButton(i), ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(POAmendmentLocators.ServiceLineDetailButton(i), ElementType.Xpath);
			}
		} else {
			webDB.moveToElement(POAmendmentLocators.LineDetailButton(i), ElementType.Xpath);
			flag = webDB.waitForElement(POAmendmentLocators.LineDetailButton(i), ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(POAmendmentLocators.LineDetailButton(i), ElementType.Xpath);
			}
		}
		flag = webDB.waitForElement(PurchaseOrderPageLocators.LineDetailsHeader, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Navigated To Line Details Page");
		}
		assertTrue(flag);
	}

	/**
	 * Verify Line Detail Two
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 */
	public void verifyLineDetailTwo(String Item) {
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.Supplier_Item,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.ITEMID));
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.Supplier_Item_Desc,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.ITEM_DESCRIPTION));
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.ContractId,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.CONTRACT_NO));
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.ContractPrefix,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.CONTRACT_PRFX));
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.ContractAmendNumber,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.CONTRACT_AMEND_NO));
//		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.POClsQuantity,
//				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.PO_CLS_QTY));

		webDB.switchToDefaultContent();
		flag = webDB.waitForElement(PurchaseOrderPageLocators.LineDetailsCloseIcons, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.LineDetailsCloseIcons, ElementType.Xpath);

		}
		flag = webDB.waitForElement(POAmendmentLocators.LocationHeaderId, ElementType.Xpath);
		assertTrue(flag);
	}

	/**
	 * Verify Line Details Verification
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 * @param Quantity
	 */
	public void verifyLineDetailVerification(String Item, String Quantity, int i, String discount, String Currency,
			boolean... potypeflag) {
		verifyLinePODetailsValidations(Item, i, Currency);
		navigateToLineDetailPage(i, potypeflag);
		verifyLineDetailOne(Item, Quantity, discount);
		verifyNavigateLineDetailTwo();
		verifyLineDetailTwo(Item);
	}

	/**
	 * Verify Line PO Details Validation
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 */
	public void verifyLinePODetailsValidations(String Item, int i, String Currency) {
		purchaseOrderFunctions.verifyGetTextValidation(PurchaseOrderPageLocators.ItemName(i),
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.ITEMID));
		purchaseOrderFunctions.verifyGetTextValidation(PurchaseOrderPageLocators.ItemDescription(i),
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.ITEM_DESCRIPTION));
		purchaseOrderFunctions.verifyGetTextValidation(PurchaseOrderPageLocators.UOM(i),
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.UOM));
		if (Currency.equals(commonFunc.getDataFromTestDataSheet("CurrencyInr"))) {
			purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.HSNSACCode(i),
					itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.HSN_CODE));
		}
		if (Currency.equals(commonFunc.getDataFromTestDataSheet("CurrencyInr"))) {
			purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.TaxPercent(i),
					itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.TAX));
		} else {

		}
	}

	/**
	 * Verify Line Detail One
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 * @param Quantity
	 */
	public void verifyLineDetailOne(String Item, String Quantity, String discount) {
		webDB.switchToFrame(PurchaseOrderPageLocators.LineDetailsIframePO, ElementType.Xpath);
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.SubClass,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.SUBCLASS));
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.Class,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.CLASS));
//		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.Wh_Dept_Name,
//				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.WH_DEPTNAME));
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.CostBasis,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.COST_BASIS));
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.StockUOM,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.STOCK_UOM));
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.ConversionFactor,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.CONVERSION_FACTOR));
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.Gl_Acc_No,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.GL_ACC_NO));
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.Gl_Acc_Desc,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.GL_ACC_DESC));
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.CPC_Code,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.CPC_CODE));
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.CPCDesc,
				itemDetailHandler.getPOLineDetailsforGivenItem(Item, POLineDetailsName.CPC_DESC));
		discountCalculationVerification(Quantity, discount);
	}

	/**
	 * Discount Calculation Verification
	 * 
	 * @author Deepan-GWL
	 * @param quantity
	 */
	public void discountCalculationVerification(String quantity, String discount) {
		BigDecimal DiscAmountPerQty = new BigDecimal("0.00");
		BigDecimal TotalDiscountAmount = new BigDecimal("0");
		BigDecimal LineAmount = VariableHandler.getLineAmount();
		BigDecimal TradeAmount = new BigDecimal("0");
		BigDecimal SpecAssembleValue = new BigDecimal("0");
		BigDecimal SplDiscAmount = new BigDecimal("0");
		BigDecimal CashAssembleValue = new BigDecimal("0");
		BigDecimal cashDiscount = new BigDecimal("0");
		BigDecimal Qty;
		discount = webDB.getTextFromElement(POAmendmentLocators.TradePercentage, ElementType.Xpath);
		if (!discount.equals("0")) {
			TradeAmount = purchaseOrderFunctions.verifyDiscountAmountCalculation(LineAmount, new BigDecimal(discount));
			SpecAssembleValue = LineAmount.subtract(TradeAmount);
			SpecAssembleValue = purchaseOrderFunctions
					.verifyRoundOffBigDecimalValueWithReturnBigDecimal(SpecAssembleValue);
			SplDiscAmount = purchaseOrderFunctions.verifyDiscountAmountCalculation(SpecAssembleValue,
					new BigDecimal(discount));
			CashAssembleValue = SpecAssembleValue.subtract(SplDiscAmount);
			CashAssembleValue = purchaseOrderFunctions
					.verifyRoundOffBigDecimalValueWithReturnBigDecimal(CashAssembleValue);
			cashDiscount = purchaseOrderFunctions.verifyDiscountAmountCalculation(CashAssembleValue,
					new BigDecimal(discount));
			Qty = new BigDecimal(quantity);
			DiscAmountPerQty = (TradeAmount.add(SplDiscAmount).add(cashDiscount)).divide(Qty, 2, RoundingMode.HALF_UP);
			DiscAmountPerQty = purchaseOrderFunctions
					.verifyRoundOffBigDecimalValueWithReturnBigDecimal(DiscAmountPerQty);
			TotalDiscountAmount = (TradeAmount.add(SplDiscAmount).add(cashDiscount));
			VariableHandler.setDiscountAmount(TotalDiscountAmount);
			VariableHandler.setTotalDiscountAmount(VariableHandler.getTotalDiscountAmount().add(TotalDiscountAmount));
		} else {
		}
		if (!discount.equals("0")) {
			purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.TradeAssembleValue,
					LineAmount.stripTrailingZeros().toPlainString());
		} else {

		}
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.SpecialAssembleValue,
				SpecAssembleValue.stripTrailingZeros().toPlainString());
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.CashAssembleValue,
				CashAssembleValue.stripTrailingZeros().toPlainString());
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.TradeAmount,
				TradeAmount.stripTrailingZeros().toPlainString());
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.SpecialDiscountAmount,
				SplDiscAmount.stripTrailingZeros().toPlainString());
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.CashDiscountAmount,
				cashDiscount.stripTrailingZeros().toPlainString());
//		if (!discount.equals("0")) {
//			purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.DiscAmountPerQty,
//					DiscAmountPerQty.stripTrailingZeros().toPlainString());
//		} else {
//			purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.DiscAmountPerQty,
//					DiscAmountPerQty.toString());
//		}
	}

	/**
	 * Verify Search PO and Validate PO Status
	 * 
	 * @author Deepan-GWL
	 * @param POno
	 * @param ExpectedStatus
	 */
	public void verifyOpenPONumber(String POno) {
		webDB.navigateToRefresh();
//		commonFunc.searchAndSelectValueInFilters(PurchaseOrderPageLocators.PONumberSelect, POno);
//		flag = webDB.waitForElement(OpenPoRcLocators.HeaderCheckBox, ElementType.Xpath);
//		if (flag) {
//			logs.logging("INFO", "Header CheckBox Is Displayed");
//			webDB.clickAnElement(OpenPoRcLocators.HeaderCheckBox, ElementType.Xpath);
//			flag = webDB.waitForElement(PurchaseOrderPageLocators.SearchButton, ElementType.Xpath);
//			if (flag) {
//				webDB.clickAnElement(PurchaseOrderPageLocators.SearchButton, ElementType.Xpath);
//				logs.logging("INFO", "Search Button Is Clicked");
//				commonFunc.hardWait(1000);
//				commonFunc.checkRefreshCellsDisappear();
//				flag = webDB.waitForElement(POAmendmentLocators.PONumber(POno), ElementType.Xpath);
//				if (flag) {
//					webDB.clickAnElement(POAmendmentLocators.PONumber(POno), ElementType.Xpath);
//					flag = webDB.waitForElement(PurchaseOrderPageLocators.UOMTable, ElementType.Xpath);
//				} else {
////					webDB.navigateToRefresh();
////					verifyOpenPONumber(POno);
//				}
//			}
//		}
		assertTrue(flag);
	}

	/**
	 * Verify Tax Calculation After Discount
	 * 
	 * @author Deepan-GWL
	 * @param Currency
	 * @param Item
	 */
	public void verifyTaxCalculationAfterDiscount(String Currency, String Item, int i) {
		BigDecimal TaxcalAmount = new BigDecimal("0");
		BigDecimal TotalTaxAmountd = new BigDecimal("0");
		BigDecimal LineAmountAfterDiscount = VariableHandler.getLineAmount()
				.subtract(VariableHandler.getDiscountAmount());
		commonFunc.checkRefreshCellsDisappear();
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.DiscountAmount(i),
				VariableHandler.getDiscountAmount().toString());
		if (Currency.equals(commonFunc.getDataFromTestDataSheet("CurrencyInr"))) {
			flag = webDB.waitForElement(POAmendmentLocators.TaxPercentage(Item), ElementType.Xpath);
			if (flag) {
				String TaxPercentage = webDB.getTextFromElement(POAmendmentLocators.TaxPercentage(Item),
						ElementType.Xpath);
				logs.logging("INFO", "Get Text Tax Percentage For Item " + Item + " Is: " + TaxPercentage);
				BigDecimal TotalTaxAmount = (LineAmountAfterDiscount.multiply(new BigDecimal(TaxPercentage)))
						.divide(new BigDecimal(100));
				BigDecimal IndividualTax = purchaseOrderFunctions
						.verifyRoundOffBigDecimalValueWithReturnBigDecimal(TotalTaxAmount.divide(new BigDecimal("2")));
				TotalTaxAmount = IndividualTax.multiply(new BigDecimal("2"));
				for (int j = 13; j <= 16; j++) {
					String TaxAmount = webDB.getTextFromElement(PurchaseOrderPageLocators.TaxAmount(i, j),
							ElementType.Xpath);
					logs.logging("INFO", "Get Text Tax Amount Is: " + TaxAmount);
					BigDecimal TaxAmountd = new BigDecimal(TaxAmount);
					TotalTaxAmountd = TaxcalAmount.add(TaxAmountd);
					TaxcalAmount = purchaseOrderFunctions
							.verifyRoundOffBigDecimalValueWithReturnBigDecimal(TotalTaxAmountd);
				}
				logs.logging("INFO", "Total Tax Amount Is: " + TotalTaxAmount);
				System.out.println(TaxcalAmount);
				flag = TotalTaxAmount.equals(TaxcalAmount);
				if (flag) {
					VariableHandler.setIndividualAmount(TaxcalAmount);
					VariableHandler.setTaxAmount(VariableHandler.getTaxAmount().add(TaxcalAmount));
				}
				assertTrue(flag);
			}
		} else {
			for (int j = 13; j <= 16; j++) {
				String TaxAmount = webDB.getTextFromElement(PurchaseOrderPageLocators.TaxAmount(i, j),
						ElementType.Xpath);
				logs.logging("INFO", "Get Text Tax Amount Is: " + TaxAmount);
				flag = TaxAmount.equals(commonFunc.getDataFromTestDataSheet("ZeroValue"));
				if (flag) {

				} else {
					assertTrue(false);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify PO Total Amount Calculation
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPOTotalAmountCalculation() {
		BigDecimal TotalLineAmountd = new BigDecimal("0");
		BigDecimal TotalTaxAmountd = new BigDecimal("0");
		BigDecimal LinecalAmount = new BigDecimal("0");
		List<WebElement> LineAmountWE = webDB.getDriver().findElements(By.xpath(POAmendmentLocators.LineAmountWE));
		for (int i = 1; i <= LineAmountWE.size(); i++) {
			String LineAmount = webDB.getTextFromElement(POAmendmentLocators.LineAmount(i), ElementType.Xpath);
			logs.logging("INFO", "Get Text Line Amount Is: " + LineAmount);
			BigDecimal LineAmountd = new BigDecimal(LineAmount.replace(",", ""));
			TotalLineAmountd = LinecalAmount.add(LineAmountd);
			logs.logging("INFO", "Calculated Total Line Amount Is: " + TotalLineAmountd);
			LinecalAmount = purchaseOrderFunctions.verifyRoundOffBigDecimalValueWithReturnBigDecimal(TotalLineAmountd);
		}
		BigDecimal TaxcalAmount = new BigDecimal("0");
		for (int i = 1; i <= LineAmountWE.size(); i++) {
			for (int j = 13; j <= 16; j++) {
				String TaxAmount = webDB.getTextFromElement(PurchaseOrderPageLocators.TaxAmount(i, j),
						ElementType.Xpath);
				logs.logging("INFO", "Get Text Tax Amount Is: " + TaxAmount);
				BigDecimal TaxAmountd = new BigDecimal(TaxAmount);
				TotalTaxAmountd = TaxcalAmount.add(TaxAmountd);
				TaxcalAmount = purchaseOrderFunctions
						.verifyRoundOffBigDecimalValueWithReturnBigDecimal(TotalTaxAmountd);
			}
			logs.logging("INFO", "Total Tax Amount For Line " + i + " Is: " + TotalTaxAmountd);
		}
		logs.logging("INFO", "Total Tax Amount Calculated Is: " + TotalTaxAmountd);
		BigDecimal POTotalAmount = LinecalAmount.add(TaxcalAmount);
		BigDecimal Totalvalue = purchaseOrderFunctions.verifyRoundOffBigDecimalValueWithReturnBigDecimal(POTotalAmount);
		logs.logging("INFO", "Expected Total Po Value Is: " + Totalvalue.stripTrailingZeros().toPlainString());
		VariableHandler.setPOTotalAmount(Totalvalue.stripTrailingZeros().toPlainString());
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Total Amount Calculation
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyTotalAmountCalculation() {
		flag = webDB.waitForElement(POAmendmentLocators.POTotalAmount, ElementType.Xpath);
		if (flag) {
			String POTotalAmount = webDB.getTextFromElement(POAmendmentLocators.POTotalAmount, ElementType.Xpath);
			logs.logging("INFO", "Actual Get Text Value Is: " + POTotalAmount);
			flag = POTotalAmount.equals(VariableHandler.getPOTotalAmount());

		}
		assertTrue(flag);
	}

	/**
	 * Verify General Tab Total Amount Calculation
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyGeneralTabTotalAmountCalculation() {
		BigDecimal TotalAmount = VariableHandler.getTotalLineAmount().subtract(VariableHandler.getTotalDiscountAmount())
				.add(VariableHandler.getTaxAmount());
		BigDecimal TotalAmountroundoff = purchaseOrderFunctions
				.verifyRoundOffBigDecimalValueWithReturnBigDecimal(TotalAmount, 0);
		BigDecimal RoundOffValue = TotalAmountroundoff.subtract(TotalAmount);
		verifyNavigateToGeneraTab();
		commonFunc.hardWait(15000);
		System.out.println("Variable Handler Line Amount"
				+ VariableHandler.getTotalLineAmount().stripTrailingZeros().toPlainString());
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.MatAmountInput,
				VariableHandler.getTotalLineAmount().stripTrailingZeros().toPlainString());
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.TaxAmountInput,
				VariableHandler.getTaxAmount().stripTrailingZeros().toPlainString());
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.DiscountAmountInput,
				VariableHandler.getTotalDiscountAmount().stripTrailingZeros().toPlainString());
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.TotalAmountInput,
				TotalAmountroundoff.toString());
		purchaseOrderFunctions.verifyGetTextValidation(POAmendmentLocators.RoundOffInput,
				RoundOffValue.stripTrailingZeros().toPlainString().replace("0.", "."));
	}

	/**
	 * Verify Navigate To General Tab
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyNavigateToGeneraTab() {
		flag = webDB.waitForElement(POAmendmentLocators.GeneralTab, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(POAmendmentLocators.GeneralTab, ElementType.Xpath);
			flag = webDB.waitForElement(POAmendmentLocators.MatAmountLabel, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * Verify Navigate Line Detail Two
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void verifyNavigateLineDetailTwoPage() {
		webDB.moveToElement(POAmendmentLocators.LineDetailsTwo, ElementType.Xpath);
		flag = webDB.waitForElement(POAmendmentLocators.LineDetailsTwo, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(POAmendmentLocators.LineDetailsTwo, ElementType.Xpath);
			flag = webDB.waitForElement(POAmendmentLocators.Old_Supplier_Item, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Navigated To Line Details Two Page");
			}
		}
		assertTrue(flag);
	}

	/**
	 * Making zero Variable For Calculation
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void makingTotalAmountCalculationToZero() {
		VariableHandler.setTotalLineAmount(BigDecimal.ZERO);
		VariableHandler.setTotalDiscountAmount(BigDecimal.ZERO);
		VariableHandler.setTaxAmount(BigDecimal.ZERO);
	}

	/**
	 * Verify Direct PO Domestic Material With Similar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticServiceWithSimilarStockAndSupplierUOM() {
		purchaseOrderFunctions.verifyDirectPODomesticServiceSimilarUOM();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyPOAmendmentHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyCreateItemInPoAmendment(commonFunc.getDataFromTestDataSheet("Gwl_Service_Item_03"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		LineDetailValidation(commonFunc.getDataFromTestDataSheet("Gwl_Service_Item_03"));
		navigateToLineDetailPage();
		verifyEnterAmountForDiscountCalculation(commonFunc.getDataFromTestDataSheet("FiveValue"));
		discountCalculationVerification(commonFunc.getDataFromTestDataSheet("FiveValue"));
		verifyLineDetailsUpdation();
		webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
		verifyNavigateLineDetailTwoPage();
		verifyLineDetailsTwoVerifications(commonFunc.getDataFromTestDataSheet("Gwl_Service_Item_03"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			makingTotalAmountCalculationToZero();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			String[] Totalitem = { commonFunc.getDataFromTestDataSheet("Gwl_Service_Item_03"),
					commonFunc.getDataFromTestDataSheet("GwlItem_Service") };
			verifyPurchaseOrderValidationWithAmendment(commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("QuantityValue"),
					commonFunc.getDataFromTestDataSheet("QuantityValue"), Totalitem, true);
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * Verify Direct PO Domestic GEneral With Similar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPODomesticGeneralWithSimilarStockAndSupplierUOM() {
		purchaseOrderFunctions.verifyDomesticGeneralSimilarUOMFromPRtoPO();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyPOAmendmentHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyCreateItemInPoAmendment(commonFunc.getDataFromTestDataSheet("Similar_Item_02"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		LineDetailValidation(commonFunc.getDataFromTestDataSheet("Similar_Item_02"));
		navigateToLineDetailPage();
		verifyEnterAmountForDiscountCalculation(commonFunc.getDataFromTestDataSheet("FiveValue"));
		discountCalculationVerification(commonFunc.getDataFromTestDataSheet("FiveValue"));
		verifyLineDetailsUpdation();
		webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
		verifyNavigateLineDetailTwoPage();
		verifyLineDetailsTwoVerifications(commonFunc.getDataFromTestDataSheet("Similar_Item_02"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();

		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			makingTotalAmountCalculationToZero();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			String[] Totalitem = { commonFunc.getDataFromTestDataSheet("Similar_Item_02"),
					commonFunc.getDataFromTestDataSheet("GwlItem_Serial") };

			verifyPurchaseOrderValidationWithAmendment(commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("QuantityValue"),
					commonFunc.getDataFromTestDataSheet("ZeroValue"), Totalitem);
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * Verify PR To PO Import General With Similar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPOImportGeneralWithSimilarStockAndSupplierUOM() {
		purchaseOrderFunctions.verifyImportGeneralSimilarUOMFromPRtoPO();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		verifyPOAmendmentHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyCreateItemInPoAmendment(commonFunc.getDataFromTestDataSheet("Similar_Item_02"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		LineDetailValidation(commonFunc.getDataFromTestDataSheet("Similar_Item_02"));
		navigateToLineDetailPage();
		verifyEnterAmountForDiscountCalculation(commonFunc.getDataFromTestDataSheet("FiveValue"));
		discountCalculationVerification(commonFunc.getDataFromTestDataSheet("FiveValue"));
		verifyLineDetailsUpdation();
		webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
		verifyNavigateLineDetailTwoPage();
		verifyLineDetailsTwoVerifications(commonFunc.getDataFromTestDataSheet("Similar_Item_02"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			makingTotalAmountCalculationToZero();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			String[] Totalitem = { commonFunc.getDataFromTestDataSheet("Similar_Item_02"),
					commonFunc.getDataFromTestDataSheet("GwlItem_Serial") };

			verifyPurchaseOrderValidationWithAmendment(commonFunc.getDataFromTestDataSheet("CurrencyUsd"),
					commonFunc.getDataFromTestDataSheet("QuantityValue"),
					commonFunc.getDataFromTestDataSheet("ZeroValue"), Totalitem);
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * Verify Direct PO Domestic Material With Similar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPODomesticServiceWithSimilarStockAndSupplierUOM() {
		purchaseOrderFunctions.verifyDomesticServiceSimilarUOMFromPRtoPO();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyPOAmendmentHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyCreateItemInPoAmendment(commonFunc.getDataFromTestDataSheet("Gwl_Service_Item_03"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		LineDetailValidation(commonFunc.getDataFromTestDataSheet("Gwl_Service_Item_03"));
		navigateToLineDetailPage();
		verifyEnterAmountForDiscountCalculation(commonFunc.getDataFromTestDataSheet("FiveValue"));
		discountCalculationVerification(commonFunc.getDataFromTestDataSheet("FiveValue"));
		verifyLineDetailsUpdation();
		webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
		verifyNavigateLineDetailTwoPage();
		verifyLineDetailsTwoVerifications(commonFunc.getDataFromTestDataSheet("Gwl_Service_Item_03"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			makingTotalAmountCalculationToZero();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			String[] Totalitem = { commonFunc.getDataFromTestDataSheet("Gwl_Service_Item_03"),
					commonFunc.getDataFromTestDataSheet("GwlItem_Service") };

			verifyPurchaseOrderValidationWithAmendment(commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("QuantityValue"),
					commonFunc.getDataFromTestDataSheet("QuantityValue"), Totalitem, true);
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * Verify Direct PO Domestic General With Dissimilar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticGeneralWithDissimilarStockAndSupplierUOM() {
		purchaseOrderFunctions.verifyDirectPODomesticGeneralDissimilarUOM();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyPOAmendmentHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyCreateItemInPoAmendment(commonFunc.getDataFromTestDataSheet("GwlDissimilarItem"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		LineDetailValidation(commonFunc.getDataFromTestDataSheet("GwlDissimilarItem"));
		navigateToLineDetailPage();
		verifyLineDetailsUpdation();
		webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
		verifyNavigateLineDetailTwoPage();
		verifyLineDetailsTwoVerifications(commonFunc.getDataFromTestDataSheet("GwlDissimilarItem"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();

		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			makingTotalAmountCalculationToZero();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			String[] Totalitem = { commonFunc.getDataFromTestDataSheet("GwlDissimilarItem"),
					commonFunc.getDataFromTestDataSheet("DissimilarItem") };
			verifyPurchaseOrderValidationWithAmendment(commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("QuantityValue"),
					commonFunc.getDataFromTestDataSheet("ZeroValue"), Totalitem);
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * Verify Direct PO Domestic Material With Dissimilar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticMaterialWithDissimilarStockAndSupplierUOM() {
		purchaseOrderFunctions.verifyDirectPODomesticMaterialDissimilarUOM();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyPOAmendmentHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyCreateItemInPoAmendment(commonFunc.getDataFromTestDataSheet("GwlDissimilarItem"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		LineDetailValidation(commonFunc.getDataFromTestDataSheet("GwlDissimilarItem"));
		navigateToLineDetailPage();
		verifyLineDetailsUpdation();
		webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
		verifyNavigateLineDetailTwoPage();
		verifyLineDetailsTwoVerifications(commonFunc.getDataFromTestDataSheet("GwlDissimilarItem"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			makingTotalAmountCalculationToZero();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			String[] Totalitem = { commonFunc.getDataFromTestDataSheet("GwlDissimilarItem"),
					commonFunc.getDataFromTestDataSheet("DissimilarItem") };
			verifyPurchaseOrderValidationWithAmendment(commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("QuantityValue"),
					commonFunc.getDataFromTestDataSheet("ZeroValue"), Totalitem);
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * Verify Direct PO Import General With Dissimilar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPOImportGeneralWithDissimilarStockAndSupplierUOM() {
		purchaseOrderFunctions.verifyDirectPOImportGeneralDissimilarUOM();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		verifyPOAmendmentHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyCreateItemInPoAmendment(commonFunc.getDataFromTestDataSheet("GwlDissimilarItem"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		LineDetailValidation(commonFunc.getDataFromTestDataSheet("GwlDissimilarItem"));
		navigateToLineDetailPage();
		verifyLineDetailsUpdation();
		webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
		verifyNavigateLineDetailTwoPage();
		verifyLineDetailsTwoVerifications(commonFunc.getDataFromTestDataSheet("GwlDissimilarItem"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();

		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			makingTotalAmountCalculationToZero();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			String[] Totalitem = { commonFunc.getDataFromTestDataSheet("GwlDissimilarItem"),
					commonFunc.getDataFromTestDataSheet("DissimilarItem") };
			verifyPurchaseOrderValidationWithAmendment(commonFunc.getDataFromTestDataSheet("CurrencyUsd"),
					commonFunc.getDataFromTestDataSheet("QuantityValue"),
					commonFunc.getDataFromTestDataSheet("ZeroValue"), Totalitem);
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * Verify Direct PO Import Material With Dissimilar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPOImportMaterialWithDissimilarStockAndSupplierUOM() {
		purchaseOrderFunctions.verifyDirectPOImportMaterialDissimilarUOM();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		verifyPOAmendmentHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyCreateItemInPoAmendment(commonFunc.getDataFromTestDataSheet("GwlDissimilarItem"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		LineDetailValidation(commonFunc.getDataFromTestDataSheet("GwlDissimilarItem"));
		navigateToLineDetailPage();
		verifyLineDetailsUpdation();
		webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
		verifyNavigateLineDetailTwoPage();
		verifyLineDetailsTwoVerifications(commonFunc.getDataFromTestDataSheet("GwlDissimilarItem"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();

		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			makingTotalAmountCalculationToZero();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			String[] Totalitem = { commonFunc.getDataFromTestDataSheet("GwlDissimilarItem"),
					commonFunc.getDataFromTestDataSheet("DissimilarItem") };
			verifyPurchaseOrderValidationWithAmendment(commonFunc.getDataFromTestDataSheet("CurrencyUsd"),
					commonFunc.getDataFromTestDataSheet("QuantityValue"),
					commonFunc.getDataFromTestDataSheet("ZeroValue"), Totalitem);
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * Verify PR To PO Domestic General With Dissimilar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPODomesticGeneralWithDissimilarStockAndSupplierUOM() {
		purchaseOrderFunctions.verifyDomesticGeneralDissimilarUOMFromPRtoPO();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyPOAmendmentHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyCreateItemInPoAmendment(commonFunc.getDataFromTestDataSheet("GwlDissimilarItem"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		LineDetailValidation(commonFunc.getDataFromTestDataSheet("GwlDissimilarItem"));
		navigateToLineDetailPage();
		verifyLineDetailsUpdation();
		webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
		verifyNavigateLineDetailTwoPage();
		verifyLineDetailsTwoVerifications(commonFunc.getDataFromTestDataSheet("GwlDissimilarItem"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();

		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			makingTotalAmountCalculationToZero();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			String[] Totalitem = { commonFunc.getDataFromTestDataSheet("GwlDissimilarItem"),
					commonFunc.getDataFromTestDataSheet("DissimilarItem") };
			verifyPurchaseOrderValidationWithAmendment(commonFunc.getDataFromTestDataSheet("CurrencyInr"),
					commonFunc.getDataFromTestDataSheet("QuantityValue"),
					commonFunc.getDataFromTestDataSheet("ZeroValue"), Totalitem);
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * Verify PR To PO Import General With Dissimilar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPOImportGeneralWithDissimilarStockAndSupplierUOM() {
		purchaseOrderFunctions.verifyImportGeneralDissimilarUOMFromPRtoPO();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		verifyPOAmendmentHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyCreateItemInPoAmendment(commonFunc.getDataFromTestDataSheet("GwlDissimilarItem"));
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		LineDetailValidation(commonFunc.getDataFromTestDataSheet("GwlDissimilarItem"));
		navigateToLineDetailPage();
		verifyLineDetailsUpdation();
		webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
		verifyNavigateLineDetailTwoPage();
		verifyLineDetailsTwoVerifications(commonFunc.getDataFromTestDataSheet("GwlDissimilarItem"));
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();

		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			makingTotalAmountCalculationToZero();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			String[] Totalitem = { commonFunc.getDataFromTestDataSheet("GwlDissimilarItem"),
					commonFunc.getDataFromTestDataSheet("DissimilarItem") };
			verifyPurchaseOrderValidationWithAmendment(commonFunc.getDataFromTestDataSheet("CurrencyUsd"),
					commonFunc.getDataFromTestDataSheet("QuantityValue"),
					commonFunc.getDataFromTestDataSheet("ZeroValue"), Totalitem);
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic General With
	 * Similar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticGeneralWithSimilarStockAndSupplierUOM() {
		purchaseOrderFunctions.verifyDirectPODomesticGeneralSimilarUOMWithMultipleItemsWithExcelInput();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyPOAmendmentHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyAddPoLineItemForModify();
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		String Item = webDB.getTextFromElement(POAmendmentLocators.ItemTable(1), ElementType.Xpath);
		if (Item.equals(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"))) {
			LineDetailValidation(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
			navigateToLineDetailPage();
			verifyEnterAmountForDiscountCalculation(commonFunc.getDataFromTestDataSheet("SixValue"));
			discountCalculationVerification(commonFunc.getDataFromTestDataSheet("SixValue"));
			verifyLineDetailsUpdation();
			webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
			verifyNavigateLineDetailTwoPage();
			verifyLineDetailsTwoVerifications(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
			verifyHeaderTickAndPost();
			verifyWaitingForApproval();
			commonFunc.logoutUser();
		}
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			makingTotalAmountCalculationToZero();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
			ExcelUtils excelUtils = new ExcelUtils();
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
					+ "ItemDetails.xlsx";
			itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("SimilarItemSheet"));
			for (int i = 1; i < itemmultiple.length; i++) {
				String item = itemmultiple[i][0];
				if (!item.equals("")) {
					String Quantity = itemmultiple[i][4];
					String discount = itemmultiple[i][5];
					verifyPurchaseOrderValidationWithAmendmentForModify(
							commonFunc.getDataFromTestDataSheet("CurrencyInr"), Quantity, discount, item);
				}
			}
//			verifyGeneralTabTotalAmountCalculation();
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method IS Used To Verify Add PO Line Item For Modify
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAddPoLineItemForModify() {
		flag = webDB.waitForElement(POAmendmentLocators.AddPoLine, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Add Po Line Button Is Displayed");
			webDB.clickAnElement(POAmendmentLocators.AddPoLine, ElementType.Xpath);
			flag = webDB.waitForElement(POAmendmentLocators.AddLinesHeader, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(POAmendmentLocators.AddPoLineFrame, ElementType.Xpath);
				webDB.moveToElement(POAmendmentLocators.CheckBox, ElementType.Xpath);
				flag = webDB.waitForElement(POAmendmentLocators.CheckBox, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "CheckBox Is Displayed");
					commonFunc.hardWait(3000);
					webDB.clickAnElement(POAmendmentLocators.CheckBox, ElementType.Xpath);
					flag = webDB.waitForElement(POAmendmentLocators.OKButton, ElementType.Xpath);
					if (flag) {
						commonFunc.hardWait(3000);
						logs.logging("INFO", "CheckBox IS Clicked");
						webDB.clickAnElement(POAmendmentLocators.OKButton, ElementType.Xpath);
						webDB.switchToDefaultContent();
						flag = webDB.waitForElement(POAmendmentLocators.AddPoLine, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Added Po Lines Successfully");
						}
					}

				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct Po Domestic Material
	 * With Similar Stock And Supplier UOM
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticMaterialWithSimilarStockAndSupplierUOM() {
		purchaseOrderFunctions.verifyDirectPODomesticMaterialSimilarUOMWithMultipleItems();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyPOAmendmentHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyAddPoLineItemForModify();
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		String Item = webDB.getTextFromElement(POAmendmentLocators.ItemTable(1), ElementType.Xpath);
		if (Item.equals(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"))) {
			LineDetailValidation(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
			navigateToLineDetailPage();
			verifyEnterAmountForDiscountCalculation(commonFunc.getDataFromTestDataSheet("SixValue"));
			discountCalculationVerification(commonFunc.getDataFromTestDataSheet("SixValue"));
			verifyLineDetailsUpdation();
			webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
			verifyNavigateLineDetailTwoPage();
			verifyLineDetailsTwoVerifications(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
			verifyHeaderTickAndPost();
			verifyWaitingForApproval();
			commonFunc.logoutUser();
		}
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			makingTotalAmountCalculationToZero();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
			ExcelUtils excelUtils = new ExcelUtils();
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
					+ "ItemDetails.xlsx";
			itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("SimilarItemSheet"));
			for (int i = 1; i < itemmultiple.length; i++) {
				String item = itemmultiple[i][0];
				if (!item.equals("")) {
					String Quantity = itemmultiple[i][4];
					String discount = itemmultiple[i][5];
					verifyPurchaseOrderValidationWithAmendmentForModify(
							commonFunc.getDataFromTestDataSheet("CurrencyInr"), Quantity, discount, item);
				}
			}
//			verifyGeneralTabTotalAmountCalculation();
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * Verify Purchase OOrder Validation With Amendment
	 * 
	 * @author Deepan-GWL
	 * @param currency
	 * @param Quantity
	 */
	public void verifyPurchaseOrderValidationWithAmendmentForModify(String currency, String Quantity, String discount,
			String Item, boolean... Potypeflag) {
		List<WebElement> OrderedQtyList = webDB.getDriver()
				.findElements(By.xpath(PurchaseOrderPageLocators.OrderedQuantity));
		if (OrderedQtyList.size() == 2) {
			for (int i = 1; i <= OrderedQtyList.size(); i++) {
				String ItemName = webDB.getDriver().findElement(By.xpath(PurchaseOrderPageLocators.ItemName(i)))
						.getText();
				if (Item.equals(ItemName)) {
					verifyItemCalculationWithLineAmount(i);
					verifyPoUOM(i, Item);
					verifyLineDetailVerification(Item, Quantity, i, discount, currency, Potypeflag);
					verifyTaxCalculationAfterDiscount(currency, Item, i);
					verifyPOTotalAmountCalculation();
					verifyTotalAmountCalculation();
				}
			}
		}

		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct Po Import General With
	 * Similar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPOImportGeneralWithSimilarStockAndSupplierUOM() {
		purchaseOrderFunctions.verifyDirectPOImportGeneralSimilarUOMWithMultipleItems();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		verifyPOAmendmentHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyAddPoLineItemForModify();
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		String Item = webDB.getTextFromElement(POAmendmentLocators.ItemTable(1), ElementType.Xpath);
		if (Item.equals(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"))) {
			LineDetailValidation(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
			navigateToLineDetailPage();
			verifyEnterAmountForDiscountCalculation(commonFunc.getDataFromTestDataSheet("SixValue"));
			discountCalculationVerification(commonFunc.getDataFromTestDataSheet("SixValue"));
			verifyLineDetailsUpdation();
			webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
			verifyNavigateLineDetailTwoPage();
			verifyLineDetailsTwoVerifications(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
			verifyHeaderTickAndPost();
			verifyWaitingForApproval();
			commonFunc.logoutUser();
		}
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			makingTotalAmountCalculationToZero();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
			ExcelUtils excelUtils = new ExcelUtils();
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
					+ "ItemDetails.xlsx";
			itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("SimilarItemSheet"));
			for (int i = 1; i < itemmultiple.length; i++) {
				String item = itemmultiple[i][0];
				if (!item.equals("")) {
					String Quantity = itemmultiple[i][4];
					String discount = itemmultiple[i][5];
					verifyPurchaseOrderValidationWithAmendmentForModify(
							commonFunc.getDataFromTestDataSheet("CurrencyUsd"), Quantity, discount, item);
				}
			}
//			verifyGeneralTabTotalAmountCalculation();
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Import Material With
	 * Similar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void verifyPoAmendModifyDirectPOImportMaterialWithSimilarStockAndSupplierUOM() {
		purchaseOrderFunctions.verifyDirectPOImportMaterialSimilarUOMWithMultipleItems();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		verifyPOAmendmentHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyAddPoLineItemForModify();
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		String Item = webDB.getTextFromElement(POAmendmentLocators.ItemTable(1), ElementType.Xpath);
		if (Item.equals(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"))) {
			LineDetailValidation(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
			navigateToLineDetailPage();
			verifyEnterAmountForDiscountCalculation(commonFunc.getDataFromTestDataSheet("SixValue"));
			discountCalculationVerification(commonFunc.getDataFromTestDataSheet("SixValue"));
			verifyLineDetailsUpdation();
			webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
			verifyNavigateLineDetailTwoPage();
			verifyLineDetailsTwoVerifications(commonFunc.getDataFromTestDataSheet("GwlItem_Serial"));
			verifyHeaderTickAndPost();
			verifyWaitingForApproval();
			commonFunc.logoutUser();
		}
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			makingTotalAmountCalculationToZero();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
			ExcelUtils excelUtils = new ExcelUtils();
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
					+ "ItemDetails.xlsx";
			itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("SimilarItemSheet"));
			for (int i = 1; i < itemmultiple.length; i++) {
				String item = itemmultiple[i][0];
				if (!item.equals("")) {
					String Quantity = itemmultiple[i][4];
					String discount = itemmultiple[i][5];
					verifyPurchaseOrderValidationWithAmendmentForModify(
							commonFunc.getDataFromTestDataSheet("CurrencyUsd"), Quantity, discount, item);
				}
			}
//			verifyGeneralTabTotalAmountCalculation();
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic Service With
	 * Similar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticServiceWithSimilarStockAndSupplierUOM() {
		purchaseOrderFunctions.verifyDirectPODomesticServiceSimilarUOMWithMultipleItems();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyPOAmendmentHeaderValidation(commonFunc.getDataFromTestDataSheet("Loc_100"), VariableHandler.getPONumber(),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyAddPoLineItemForModify();
		verifyAddItemInPOWithQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		verifyTickIconcreateItem();
		verifyItemCalculationWithLineAmount();
		String Item = webDB.getTextFromElement(POAmendmentLocators.ItemTable(1), ElementType.Xpath);
		if (Item.equals(commonFunc.getDataFromTestDataSheet("GwlItem_Service"))) {
			LineDetailValidation(commonFunc.getDataFromTestDataSheet("GwlItem_Service"));
			navigateToLineDetailPage();
			verifyEnterAmountForDiscountCalculation(commonFunc.getDataFromTestDataSheet("SixValue"));
			discountCalculationVerification(commonFunc.getDataFromTestDataSheet("SixValue"));
			verifyLineDetailsUpdation();
			webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
			verifyNavigateLineDetailTwoPage();
			verifyLineDetailsTwoVerifications(commonFunc.getDataFromTestDataSheet("GwlItem_Service"));
			verifyHeaderTickAndPost();
			verifyWaitingForApproval();
			commonFunc.logoutUser();
		}
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			makingTotalAmountCalculationToZero();
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
			ExcelUtils excelUtils = new ExcelUtils();
			String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
					+ "ItemDetails.xlsx";
			itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POAServiceSheet"));
			for (int i = 1; i < itemmultiple.length; i++) {
				String item = itemmultiple[i][0];
				if (!item.equals("")) {
					String Quantity = itemmultiple[i][4];
					String discount = itemmultiple[i][5];
					verifyPurchaseOrderValidationWithAmendmentForModify(
							commonFunc.getDataFromTestDataSheet("CurrencyInr"), Quantity, discount, item);
				}
			}
//			verifyGeneralTabTotalAmountCalculation();
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * Verify Direct PO Domestic GEneral With Similar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItem() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("POASimilarItemSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POASimilarItemSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = itemmultiple[i][6];

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Discount Calculation Verification
	 * 
	 * @author Deepan-GWL
	 */
	public void discountCalculationVerification(String Item, String TradeAssembleVal, String TDAmt, String SDAssblVal,
			String SDAmt, String CDAssblVal, String CDAmt) {
		purchaseOrderFunctions.verifyAssertionBothValues(TradeAssembleVal,
				dbCommonFunctions.poaLineDetailsQuery(VariableHandler.getPOANumber(), Item, CDAmt));
		purchaseOrderFunctions.verifyAssertionBothValues(TDAmt,
				dbCommonFunctions.poaLineDetailsQuery(VariableHandler.getPOANumber(), Item, CDAmt));
		purchaseOrderFunctions.verifyAssertionBothValues(SDAssblVal,
				dbCommonFunctions.poaLineDetailsQuery(VariableHandler.getPOANumber(), Item, CDAmt));
		purchaseOrderFunctions.verifyAssertionBothValues(SDAmt,
				dbCommonFunctions.poaLineDetailsQuery(VariableHandler.getPOANumber(), Item, CDAmt));
		purchaseOrderFunctions.verifyAssertionBothValues(CDAssblVal,
				dbCommonFunctions.poaLineDetailsQuery(VariableHandler.getPOANumber(), Item, CDAmt));
		purchaseOrderFunctions.verifyAssertionBothValues(CDAmt,
				dbCommonFunctions.poaLineDetailsQuery(VariableHandler.getPOANumber(), Item, CDAmt));

	}

	/**
	 * Verify Enter Amunt For Discount Calculation
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyEnterAmountForDiscountCalculation(String value) {
		flag = webDB.waitForElement(POAmendmentLocators.NewTradeAssemblePct, ElementType.Xpath);
		if (flag) {
			webDB.clearText(POAmendmentLocators.NewTradeAssemblePct, ElementType.Xpath);
			webDB.sendTextToAnElement(POAmendmentLocators.NewTradeAssemblePct, value, ElementType.Xpath);
			webDB.pressEnterKey();
			commonFunc.checkRefreshCellsDisappear();
			purchaseOrderFunctions.verifyDomPropertyValidation(POAmendmentLocators.NewTradeAssemblePct, value);
			webDB.clearText(POAmendmentLocators.NewSplAssemblePct, ElementType.Xpath);
			webDB.sendTextToAnElement(POAmendmentLocators.NewSplAssemblePct, value, ElementType.Xpath);
			webDB.pressEnterKey();
			commonFunc.checkRefreshCellsDisappear();
			purchaseOrderFunctions.verifyDomPropertyValidation(POAmendmentLocators.NewSplAssemblePct, value);
			webDB.clearText(POAmendmentLocators.NewCashAssemblePct, ElementType.Xpath);
			webDB.sendTextToAnElement(POAmendmentLocators.NewCashAssemblePct, value, ElementType.Xpath);
			webDB.pressEnterKey();
			commonFunc.checkRefreshCellsDisappear();
			purchaseOrderFunctions.verifyDomPropertyValidation(POAmendmentLocators.NewCashAssemblePct, value);
		}
		assertTrue(flag);
	}

	/**
	 * Navigate To Line Details Page
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void navigateToLineDetailPage(String Item) {
		webDB.moveToElement(POAmendmentLocators.ItemTable(1), ElementType.Xpath);
		webDB.moveToElement(POAmendmentLocators.LineDetails(Item), ElementType.Xpath);
		flag = webDB.waitForElement(POAmendmentLocators.LineDetails(Item), ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(POAmendmentLocators.LineDetails(Item), ElementType.Xpath);
			flag = webDB.waitForElement(POAmendmentLocators.Details, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
				logs.logging("INFO", "Navigated To Line Details Page");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Navigate To POA Page
	 * 
	 * @author Deepan-GWL
	 */
	public void navigateToPoaPage() {
		flag = webDB.waitForElement(POAmendmentLocators.LineTickButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(POAmendmentLocators.LineTickButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();

		}
		webDB.switchToDefaultContent();
		flag = webDB.waitForElement(POAmendmentLocators.Line_CloseButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(POAmendmentLocators.Line_CloseButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();

		}
		try {
			WebElement element = webDB.getDriver().findElement(By.xpath(POAmendmentLocators.Line_CloseButton));
			WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(20));
			wait.until(ExpectedConditions.invisibilityOf(element));
		} catch (Exception e) {
		}
		flag = webDB.waitForElement(POAmendmentLocators.HeaderLocationId, ElementType.Xpath);
		assertTrue(flag);
	}

	/**
	 * Verify Direct PO Domestic Material With Similar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItem() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("POASimilarItemSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POASimilarItemSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Domestic Service With Similar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItem() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"),
				webDB.getDataFromProperties("POAServiceItemSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POAServiceItemSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify PR To PO Domestic General With Similar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItem() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheet(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_General"),
				webDB.getDataFromProperties("POASimilarItemPRTOPOSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POASimilarItemPRTOPOSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Import GEneral With Similar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPOImportGeneralWithSimilarStockAndSupplierUOMWithMultipleItem() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("POASimilarItemImportSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POASimilarItemImportSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Import Material With Similar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPOImportMaterialWithSimilarStockAndSupplierUOMWithMultipleItem() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("POASimilarItemImportSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POASimilarItemImportSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify PR To PO Import General With Similar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPOImportGeneralWithSimilarStockAndSupplierUOMWithMultipleItem() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheet(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_General"),
				webDB.getDataFromProperties("POASimilarItemPRTOPOImportSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POASimilarItemPRTOPOImportSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify PR To PO Domestic Service With Similar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItem() {
		purchaseOrderFunctions.verifyServiceFromPRtoPOWithMultipleItemInExcelSheet(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_Service"),
				webDB.getDataFromProperties("POAServiceItemPRTOPOSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PRGWL"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POAServiceItemPRTOPOSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Domestic GEneral With Dissimilar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItem() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("POADissimilarItemSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POADissimilarItemSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Domestic Material With Dissimilar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItem() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("POADissimilarItemSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POADissimilarItemSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Import General With Dissimilar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPOImportGeneralWithDissimilarStockAndSupplierUOMWithMultipleItem() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("POADissimilarItemImportSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POADissimilarItemImportSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Import Material With Dissimilar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPOImportMaterialWithDissimilarStockAndSupplierUOMWithMultipleItem() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("POADissimilarItemImportSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POADissimilarItemImportSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify PR To PO Domestic GEneral With Dissimilar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItem() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheet(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_General"),
				webDB.getDataFromProperties("POADissimilarItemPRTOPOSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POADissimilarItemPRTOPOSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify PR To PO Import General With Dissimilar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPOImportGeneralWithDissimilarStockAndSupplierUOMWithMultipleItem() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheet(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_General"),
				webDB.getDataFromProperties("POADissimilarItemPRTOPOImportSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POADissimilarItemPRTOPOImportSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic General With
	 * Similar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("ModifyPOASimilarItemSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOASimilarItemSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * Verify Add Item In Po With Quantity And Unit Cost With Item Name
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAddItemInPOWithQuantityAndUnitCostWithItemName(String quantity, String unitcost, String Item) {
		try {
			commonFunc.hardWait(1000);
			purchaseOrderFunctions.verifyAddTableSpecs(POAmendmentLocators.NewQuantity(Item),
					POAmendmentLocators.NewQuantity, quantity);
			webDB.pressEnterKey();
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(5000);

			purchaseOrderFunctions.verifyAddTableSpecs(POAmendmentLocators.NewUnitCost(Item),
					POAmendmentLocators.NewUnitCost, unitcost);
			String POANumbervalue = webDB.getDomPropertyFromElement(POAmendmentLocators.POANumber, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			commonFunc.hardWait(3000);
			logs.logging("INFO", "Get PO Number Is: " + POANumbervalue);
			VariableHandler.setPOANumber(POANumbervalue);
		} catch (Exception e) {
		}
	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic Material
	 * With Similar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("ModifyPOASimilarItemSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOASimilarItemSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Import General With
	 * Similar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPOImportGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("ModifyPOASimilarItemImportSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOASimilarItemImportSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Import Material With
	 * Similar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPOImportMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("ModifyPOASimilarItemImportSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOASimilarItemImportSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic Service With
	 * Similar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"),
				webDB.getDataFromProperties("ModifyPOAServiceItemSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOAServiceItemSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify PR To PO Domestic General With
	 * Similar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyPrToPoDomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheet(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_General"),
				webDB.getDataFromProperties("ModifyPOASimilarItemPRTOPOSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOASimilarItemPRTOPOSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
//							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify PR To PO Domestic Service With
	 * Similar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyPrToPoDomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyPRtoPOServiceWithMultipleItemInExcelSheet(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_Service"),
				webDB.getDataFromProperties("ModifyPOAServiceItemPRTOPOSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PRGWL"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOAServiceItemPRTOPOSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
//							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic Material
	 * With Dissimilar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("ModifyPOADissimilarItemSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOADissimilarItemSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic General With
	 * Dissimilar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("ModifyPOADissimilarItemSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOADissimilarItemSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify PR To PO Import General With
	 * Similar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyPrToPoImportGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheet(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_General"),
				webDB.getDataFromProperties("ModifyPOASimilarPRTOPOImportSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOASimilarPRTOPOImportSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
//							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Import General With
	 * Dissimilar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPOImportGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("ModifyPOADissimilarItemImportSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOADissimilarItemImportSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Import Material With
	 * Dissimilar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPOImportMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("ModifyPOADissimilarItemImportSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOADissimilarItemImportSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method IS Used To Verify Add PO Line Item For Closeshort
	 * 
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAddPoLineItemForCloseShort(String Item) {
		flag = webDB.waitForElement(POAmendmentLocators.AddPoLine, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Add Po Line Button Is Displayed");
			String POANumbervalue = webDB.getDomPropertyFromElement(POAmendmentLocators.POANumber, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			commonFunc.hardWait(3000);
			logs.logging("INFO", "Get PO Number Is: " + POANumbervalue);
			VariableHandler.setPOANumber(POANumbervalue);
			webDB.clickAnElement(POAmendmentLocators.AddPoLine, ElementType.Xpath);
			flag = webDB.waitForElement(POAmendmentLocators.AddLinesHeader, ElementType.Xpath);
			if (flag) {
				webDB.switchToFrame(POAmendmentLocators.AddPoLineFrame, ElementType.Xpath);
				webDB.moveToElement(POAmendmentLocators.CheckBox(Item), ElementType.Xpath);
				flag = webDB.waitForElement(POAmendmentLocators.CheckBox(Item), ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "CheckBox Is Displayed");
					commonFunc.hardWait(3000);
					webDB.clickAnElement(POAmendmentLocators.CheckBox(Item), ElementType.Xpath);
					flag = webDB.waitForElement(POAmendmentLocators.OKButton, ElementType.Xpath);
					if (flag) {
						commonFunc.hardWait(3000);
						logs.logging("INFO", "CheckBox IS Clicked");
						webDB.clickAnElement(POAmendmentLocators.OKButton, ElementType.Xpath);
						webDB.switchToDefaultContent();
						flag = webDB.waitForElement(POAmendmentLocators.CloseShort, ElementType.Xpath);
						if (flag) {
							webDB.clickAnElement(POAmendmentLocators.CloseShort, ElementType.Xpath);
							commonFunc.checkRefreshCellsDisappear();
							commonFunc.hardWait(2000);
							if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
								webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
							}
							flag = webDB.waitForElement(POAmendmentLocators.CloseshortType(Item), ElementType.Xpath);
							logs.logging("INFO", "Item Is Closeshorts Successfully " + Item);
						}
					}

				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Po Amend CloseShort Direct PO Domestic General
	 * With Similar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendCloseshortDirectPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("CloseshortPOASimilarItemSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("CloseshortPOASimilarItemSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						verifyAddPoLineItemForCloseShort(item);
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend CloseShort Direct PO Domestic Material
	 * With Similar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendCloseshortDirectPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("CloseshortPOASimilarItemSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("CloseshortPOASimilarItemSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						verifyAddPoLineItemForCloseShort(item);
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend CloseShort Direct PO Import General
	 * With Similar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendCloseshortDirectPOImportGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("CloseshortPOASimilarItemImportSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("CloseshortPOASimilarItemImportSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						verifyAddPoLineItemForCloseShort(item);
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend CloseShort Direct PO Import Material
	 * With Similar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendCloseshortDirectPOImportMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("CloseshortPOASimilarItemImportSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("CloseshortPOASimilarItemImportSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						verifyAddPoLineItemForCloseShort(item);
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend CloseShort Direct PO Domestic Service
	 * With Similar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendCloseshortDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"),
				webDB.getDataFromProperties("CloseshortPOAServiceItemSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("CloseshortPOAServiceItemSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						verifyAddPoLineItemForCloseShort(item);
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend CloseShort PR To PO Domestic General
	 * With Similar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendCloseshortPRToPODomesticGenaeralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheet(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_General"),
				webDB.getDataFromProperties("CloseshortPOASimilarItemPRTOPOSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("CloseshortPOASimilarItemPRTOPOSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						verifyAddPoLineItemForCloseShort(item);
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend CloseShort PR To PO Domestic Service
	 * With Similar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendCloseshortPRToPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyPRtoPOServiceWithMultipleItemInExcelSheet(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_Service"),
				webDB.getDataFromProperties("CloseshortPOAServiceItemPRTOPOSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PRGWL"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("CloseshortPOAServiceItemPRTOPOSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						verifyAddPoLineItemForCloseShort(item);
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend CloseShort PR To PO Import General
	 * With Similar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendCloseshortPRToPOImportGenaeralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheet(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_General"),
				webDB.getDataFromProperties("CloseshortPOASimilarPRTOPOImporSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("CloseshortPOASimilarPRTOPOImporSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						verifyAddPoLineItemForCloseShort(item);
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend CloseShort Direct PO Domestic General
	 * With Dissimilar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendCloseshortDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("CloseshortPOADissimilarItemSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("CloseshortPOADissimilarItemSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						verifyAddPoLineItemForCloseShort(item);
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend CloseShort Direct PO Domestic Material
	 * With Dissimilar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendCloseshortDirectPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("CloseshortPOADissimilarItemSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("CloseshortPOADissimilarItemSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						verifyAddPoLineItemForCloseShort(item);
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend CloseShort Direct PO Import General
	 * With Dissimilar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendCloseshortDirectPOImportGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("CloseshortPOADissimilarImportSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("CloseshortPOADissimilarImportSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						verifyAddPoLineItemForCloseShort(item);
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend CloseShort Direct PO Import Material
	 * With Dissimilar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendCloseshortDirectPOImportMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("CloseshortPOADissimilarImportSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("CloseshortPOADissimilarImportSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						verifyAddPoLineItemForCloseShort(item);
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend CloseShort PR To PO Domestic General
	 * With Dissimilar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendCloseshortPRToPODomesticGenaeralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheet(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_General"),
				webDB.getDataFromProperties("CloseshortPOADissimilarPRTOPOSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("CloseshortPOADissimilarPRTOPOSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						verifyAddPoLineItemForCloseShort(item);
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amendment Cancel Documents And Cancel Lines
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendmentCancelDocumentsAndCancelLine() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("ModifyPOASimilarItemSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOASimilarItemSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						verifyPOAmendmentCancelLine(item);

					} else {
						break;
					}
				}
			}
		}
		verifyCancelDocuments();
		flag = webDB.waitForElement(POAmendmentLocators.HeaderCheckbox, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Header CheckBox Is Clicked");
			webDB.clickAnElement(POAmendmentLocators.HeaderCheckbox, ElementType.Xpath);
			verifyValidatePOACancelStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("CancelStatus"));
			flag = commonFunc.logoutUser();
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Po Amendment Cancel Line
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 */
	public void verifyPOAmendmentCancelLine(String Item) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(POAmendmentLocators.CancelLine(Item), ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(POAmendmentLocators.Item(Item), ElementType.Xpath);
			webDB.moveToElement(POAmendmentLocators.CancelLine(Item), ElementType.Xpath);
			webDB.clickAnElement(POAmendmentLocators.CancelLine(Item), ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Toast Message Displayed, Line Deleted Successfully with value: " + Item);
				if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}
		Assert.assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Cancel Documents
	 * 
	 * @author Deepan-GWL
	 * 
	 */
	public void verifyCancelDocuments() {
		flag = webDB.waitForElement(POAmendmentLocators.AddPoLine, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Add Po Line Button Is Displayed");
			String POANumbervalue = webDB.getDomPropertyFromElement(POAmendmentLocators.POANumber, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			commonFunc.hardWait(3000);
			logs.logging("INFO", "Get PO Number Is: " + POANumbervalue);
			VariableHandler.setPOANumber(POANumbervalue);
			webDB.moveToElement(POAmendmentLocators.CancelDoc, ElementType.Xpath);
			flag = webDB.waitForElement(POAmendmentLocators.CancelDoc, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(POAmendmentLocators.CancelDoc, ElementType.Xpath);
				flag = webDB.waitForElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				if (flag) {
					logs.logging("INFO", "Toast Message Displayed, POA Cancelled Successfully");
					if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
						webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Search PO and Validate PO Status
	 * 
	 * @author Deepan-GWL
	 * @param POno
	 * @param ExpectedStatus
	 */
	public void verifyValidatePOACancelStatus(String POno, String ExpectedStatus) {
		commonFunc.searchAndSelectValueInFilters(POAmendmentLocators.POANumberLovButton, POno);
		flag = webDB.waitForElement(POAmendmentLocators.SearchButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(POAmendmentLocators.SearchButton, ElementType.Xpath);
			logs.logging("INFO", "Search Button Is Clicked");
			commonFunc.hardWait(1000);
			commonFunc.checkRefreshCellsDisappear();
			flag = webDB.waitForElement(POAmendmentLocators.POANumberCancelStatus(POno), ElementType.Xpath);
			if (flag) {
				String Status = webDB.getTextFromElement(POAmendmentLocators.POANumberCancelStatus(POno),
						ElementType.Xpath);
				flag = Status.contains(ExpectedStatus);
				if (flag) {
					logs.logging("INFO", "Expected Status: " + ExpectedStatus);
					logs.logging("INFO", "Verified Status ,Displayed Status: " + Status);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Po Amend Modify PR To PO Domestic General With
	 * Dissimilar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyPrToPoDomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheet(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_General"),
				webDB.getDataFromProperties("ModifyPOADissimilarItemPRTOPOSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOADissimilarItemPRTOPOSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
//							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify PR To PO Import General With
	 * Similar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyPrToPoImportGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheet(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_General"),
				webDB.getDataFromProperties("ModifyPOADissimilarImportPRTOPOSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOADissimilarImportPRTOPOSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
//							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * Verify PR To PO Domestic Material With Similar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItem() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheetForMaterial(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("PRMaterialType"),
				webDB.getDataFromProperties("POASimilarItemPRTOPOMaterialSheet"),
				commonFunc.getDataFromTestDataSheet("IrpPrefix"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("L0001"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POASimilarItemPRTOPOMaterialSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						commonFunc.hardWait(2500);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(commonFunc.getDataFromTestDataSheet("(10059030)-15%"));
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("DevaValue"));
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		verifyValidatePOAStatus(VariableHandler.getPOANumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		verifyOpenPONumber(VariableHandler.getPONumber());
		flag = commonFunc.logoutUser();
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String cgst = itemmultiple[i][21];
					String item = itemmultiple[i][1];
					String LineAmount = itemmultiple[i][18];
					String TaxPercent = itemmultiple[i][20];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
						purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify PR To PO Domestic Material With Dissimilar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItem() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheetForMaterial(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("PRMaterialType"),
				webDB.getDataFromProperties("POADissimilarItemPRTOPOMaterialSheet"),
				commonFunc.getDataFromTestDataSheet("IrpPrefix"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("L0001"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AsianLeathers"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POADissimilarItemPRTOPOMaterialSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						commonFunc.hardWait(2500);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(commonFunc.getDataFromTestDataSheet("(10059030)-15%"));
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("DevaValue"));
		commonFunc.approveDocuments(commonFunc.getDataFromTestDataSheet("Kare"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("U1"),
				System.getProperty("HariUsername"));
		verifyValidatePOAStatus(VariableHandler.getPOANumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigatePurchaseOrderPage();
		verifyOpenPONumber(VariableHandler.getPONumber());
		flag = commonFunc.logoutUser();
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String cgst = itemmultiple[i][21];
					String item = itemmultiple[i][1];
					String LineAmount = itemmultiple[i][18];
					String TaxPercent = itemmultiple[i][20];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
						purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Direct PO - Import Supplier - Material Items with Similar Stock and Supplier
	 * UOM?
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPOImportMaterialWithSimilarStockAndSupplierUOMWithMultipleItem() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheetForMaterial(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("PRMaterialType"),
				webDB.getDataFromProperties("POASimilarItemPRTOPOMaterialImpSheet"),
				commonFunc.getDataFromTestDataSheet("IrpPrefix"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("L0001"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("HatsunSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POASimilarItemPRTOPOMaterialImpSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						commonFunc.hardWait(2500);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(commonFunc.getDataFromTestDataSheet("(10059030)-15%"));
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("DevaValue"));
		commonFunc.approveDocuments(commonFunc.getDataFromTestDataSheet("Kare"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("U1"),
				System.getProperty("HariUsername"));
		verifyValidatePOAStatus(VariableHandler.getPOANumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigatePurchaseOrderPage();
		verifyOpenPONumber(VariableHandler.getPONumber());
		flag = commonFunc.logoutUser();
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String cgst = itemmultiple[i][21];
					String item = itemmultiple[i][1];
					String LineAmount = itemmultiple[i][18];
					String TaxPercent = itemmultiple[i][20];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
						purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify PR To PO Import Material With Dissimilar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPOImportMaterialWithDissimilarStockAndSupplierUOMWithMultipleItem() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheetForMaterial(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("PRMaterialType"),
				webDB.getDataFromProperties("POADissimilarItemPRTOPOMatImpSheet"),
				commonFunc.getDataFromTestDataSheet("IrpPrefix"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("L0001"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("HatsunSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POADissimilarItemPRTOPOMatImpSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						commonFunc.hardWait(2500);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(commonFunc.getDataFromTestDataSheet("(10059030)-15%"));
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("DevaValue"));
		commonFunc.approveDocuments(commonFunc.getDataFromTestDataSheet("Kare"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("U1"),
				System.getProperty("HariUsername"));
		verifyValidatePOAStatus(VariableHandler.getPOANumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigatePurchaseOrderPage();
		verifyOpenPONumber(VariableHandler.getPONumber());
		flag = commonFunc.logoutUser();
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String cgst = itemmultiple[i][21];
					String item = itemmultiple[i][1];
					String LineAmount = itemmultiple[i][18];
					String TaxPercent = itemmultiple[i][20];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
						purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To verify Po Amend Modify Pr To Po Domestic Material With
	 * Similar Stock And Supplier UOM With Multiple Item With Excel Input
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyPrToPoDomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheetForMaterial(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("PRMaterialType"),
				webDB.getDataFromProperties("ModifyPOASimilarItemPRTOPOMatSheet"),
				commonFunc.getDataFromTestDataSheet("IrpPrefix"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("L0001"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOASimilarItemPRTOPOMatSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation(commonFunc.getDataFromTestDataSheet("(10059030)-15%"));
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("DevaValue"));
		commonFunc.approveDocuments(commonFunc.getDataFromTestDataSheet("Kare"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("U1"),
				System.getProperty("HariUsername"));
		verifyValidatePOAStatus(VariableHandler.getPOANumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigatePurchaseOrderPage();
		verifyOpenPONumber(VariableHandler.getPONumber());

		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String cgst = itemmultiple[i][21];
					String item = itemmultiple[i][1];
					String LineAmount = itemmultiple[i][18];
					String TaxPercent = itemmultiple[i][20];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
//							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
						purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
					}
				}
			}
		}
		flag = commonFunc.logoutUser();
	}

	/**
	 * This Method Is Used To verify Po Amend Modify From PR to PO - Import Supplier
	 * - Material Items with Similar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyPrToPoImportMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheetForMaterial(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("PRMaterialType"),
				webDB.getDataFromProperties("ModifyPOASimilarPRTOPOMatImpSheet"),
				commonFunc.getDataFromTestDataSheet("IrpPrefix"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("L0001"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("HatsunSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOASimilarPRTOPOMatImpSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation(commonFunc.getDataFromTestDataSheet("(10059030)-15%"));
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("DevaValue"));
		commonFunc.approveDocuments(commonFunc.getDataFromTestDataSheet("Kare"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("U1"),
				System.getProperty("HariUsername"));
		verifyValidatePOAStatus(VariableHandler.getPOANumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigatePurchaseOrderPage();
		verifyOpenPONumber(VariableHandler.getPONumber());

		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String cgst = itemmultiple[i][21];
					String item = itemmultiple[i][1];
					String LineAmount = itemmultiple[i][18];
					String TaxPercent = itemmultiple[i][20];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
//							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
						purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
					}
				}
			}
		}
		flag = commonFunc.logoutUser();
	}

	/**
	 * This Method Is Used To verify Po Amend Modify Pr To Po Domestic Material With
	 * Dissimilar Stock And Supplier UOM With Multiple Item With Excel Input
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyPrToPoDomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheetForMaterial(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("PRMaterialType"),
				webDB.getDataFromProperties("ModifyPOADissimilarPRTOPOMatSheet"),
				commonFunc.getDataFromTestDataSheet("IrpPrefix"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("L0001"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOADissimilarPRTOPOMatSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation(commonFunc.getDataFromTestDataSheet("(10059030)-15%"));
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("DevaValue"));
		commonFunc.approveDocuments(commonFunc.getDataFromTestDataSheet("Kare"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("U1"),
				System.getProperty("HariUsername"));
		verifyValidatePOAStatus(VariableHandler.getPOANumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigatePurchaseOrderPage();
		verifyOpenPONumber(VariableHandler.getPONumber());

		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String cgst = itemmultiple[i][21];
					String item = itemmultiple[i][1];
					String LineAmount = itemmultiple[i][18];
					String TaxPercent = itemmultiple[i][20];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
//							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
						purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
					}
				}
			}
		}
		flag = commonFunc.logoutUser();
	}

	/**
	 * This Method Is Used To verify Po Amend Modify Pr To Po Domestic Material With
	 * Similar Stock And Supplier UOM With Multiple Item With Excel Input
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyPrToPoImportMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheetForMaterial(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("PRMaterialType"),
				webDB.getDataFromProperties("ModifyPOADissimilarPRTOPOImpMatSheet"),
				commonFunc.getDataFromTestDataSheet("IrpPrefix"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("L0001"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOADissimilarPRTOPOImpMatSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation(commonFunc.getDataFromTestDataSheet("(10059030)-15%"));
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("DevaValue"));
		commonFunc.approveDocuments(commonFunc.getDataFromTestDataSheet("Kare"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("U1"),
				System.getProperty("HariUsername"));
		verifyValidatePOAStatus(VariableHandler.getPOANumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigatePurchaseOrderPage();
		verifyOpenPONumber(VariableHandler.getPONumber());

		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String cgst = itemmultiple[i][21];
					String item = itemmultiple[i][1];
					String LineAmount = itemmultiple[i][18];
					String TaxPercent = itemmultiple[i][20];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
//							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
						purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
					}
				}
			}
		}
		flag = commonFunc.logoutUser();
	}

	/**
	 * This Method Is Used To Verify Po Amend CloseShort From PR to PO - Import
	 * Supplier - Material Items with Similar Stock and Supplier UOM?
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendCloseshortPRToPOImportMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheetForMaterial(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("PRMaterialType"),
				webDB.getDataFromProperties("CloseshortPOASimilarPRTOMatImpSheet"),
				commonFunc.getDataFromTestDataSheet("IrpPrefix"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("L0001"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("HatsunSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("CloseshortPOASimilarPRTOMatImpSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						verifyAddPoLineItemForCloseShort(item);
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("DevaValue"));
		commonFunc.approveDocuments(commonFunc.getDataFromTestDataSheet("Kare"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("U1"),
				System.getProperty("HariUsername"));
		verifyValidatePOAStatus(VariableHandler.getPOANumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigatePurchaseOrderPage();
		verifyOpenPONumber(VariableHandler.getPONumber());

		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String cgst = itemmultiple[i][21];
					String item = itemmultiple[i][1];
					String LineAmount = itemmultiple[i][18];
					String TaxPercent = itemmultiple[i][20];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
						purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
					}
				}
			}
		}
		flag = commonFunc.logoutUser();
	}

	/**
	 * This Method Is Used To Verify Po Amend CloseShort PR To PO Domestic Material
	 * With Similar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendCloseshortPRToPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheetForMaterial(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("PRMaterialType"),
				webDB.getDataFromProperties("CloseshortPOASimilarItemPRTOMatSheet"),
				commonFunc.getDataFromTestDataSheet("IrpPrefix"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("L0001"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("CloseshortPOASimilarItemPRTOMatSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						verifyAddPoLineItemForCloseShort(item);
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("DevaValue"));
		commonFunc.approveDocuments(commonFunc.getDataFromTestDataSheet("Kare"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("U1"),
				System.getProperty("HariUsername"));
		verifyValidatePOAStatus(VariableHandler.getPOANumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigatePurchaseOrderPage();
		verifyOpenPONumber(VariableHandler.getPONumber());

		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String cgst = itemmultiple[i][21];
					String item = itemmultiple[i][1];
					String LineAmount = itemmultiple[i][18];
					String TaxPercent = itemmultiple[i][20];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
						purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
					}
				}
			}
		}
		flag = commonFunc.logoutUser();
	}

	/**
	 * This Method Is Used To Verify Po Amend CloseShort PR To PO Import Material
	 * With Dissimilar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendCloseshortPRToPOImportMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheetForMaterial(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("PRMaterialType"),
				webDB.getDataFromProperties("CSPOADissimilarPRTOPOMatImpSheet"),
				commonFunc.getDataFromTestDataSheet("IrpPrefix"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("L0001"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("HatsunSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("CSPOADissimilarPRTOPOMatImpSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						verifyAddPoLineItemForCloseShort(item);
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("DevaValue"));
		commonFunc.approveDocuments(commonFunc.getDataFromTestDataSheet("Kare"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("U1"),
				System.getProperty("HariUsername"));
		verifyValidatePOAStatus(VariableHandler.getPOANumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigatePurchaseOrderPage();
		verifyOpenPONumber(VariableHandler.getPONumber());

		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String cgst = itemmultiple[i][21];
					String item = itemmultiple[i][1];
					String LineAmount = itemmultiple[i][18];
					String TaxPercent = itemmultiple[i][20];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
						purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
					}
				}
			}
		}
		flag = commonFunc.logoutUser();
	}

	/**
	 * This Method Is Used To Verify Po Amend CloseShort PR To PO Domestic Material
	 * With Dissimilar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendCloseshortPRToPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInput() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheetForMaterial(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("PRMaterialType"),
				webDB.getDataFromProperties("CloseshortPOADissimilarPRTOPMatSheet"),
				commonFunc.getDataFromTestDataSheet("IrpPrefix"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("L0001"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AsianLeathers"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("CloseshortPOADissimilarPRTOPMatSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						verifyAddPoLineItemForCloseShort(item);
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("DevaValue"));
		commonFunc.approveDocuments(commonFunc.getDataFromTestDataSheet("Kare"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HariEmpId"), commonFunc.getDataFromTestDataSheet("U1"),
				System.getProperty("HariUsername"));
		verifyValidatePOAStatus(VariableHandler.getPOANumber(), commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		flag = commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
		commonFunc.navigatePurchaseOrderPage();
		verifyOpenPONumber(VariableHandler.getPONumber());

		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String cgst = itemmultiple[i][21];
					String item = itemmultiple[i][1];
					String LineAmount = itemmultiple[i][18];
					String TaxPercent = itemmultiple[i][20];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
						purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
					}
				}
			}
		}
		flag = commonFunc.logoutUser();
	}

	/**
	 * Verify Direct PO Domestic With Local Reg General With Similar Stock And
	 * Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPOGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithLocalReg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInput(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("POASimilarItemSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POASimilarItemSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String cgst = itemmultiple[i][21];
						String item = itemmultiple[i][1];
						String LineAmount = itemmultiple[i][18];
						String TaxPercent = itemmultiple[i][20];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.taxValidation(item, TaxPercent, cgst);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Line Details Updation
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyLineDetailsUpdation(String HsnCode) {
		webDB.moveToElement(POAmendmentLocators.NewCashAssembleAmount, ElementType.Xpath);
		webDB.moveToElement(POAmendmentLocators.HSNCode, ElementType.Xpath);
		flag = webDB.waitForElement(POAmendmentLocators.HSNCode, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(POAmendmentLocators.HSNCode, HsnCode);
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Domestic With Local Registered GEneral With Similar Stock
	 * And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithLocalRg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("POASimilarItemLocalRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POASimilarItemLocalRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Domestic With Interstate Registered GEneral With Similar
	 * Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithInterstateRg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("AgrCeramics"), commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("POASimilarItemInterstateRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AgrCeramics"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POASimilarItemInterstateRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Domestic With Local UnRegistered GEneral With Similar Stock
	 * And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithLocaUnRg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("POASimilarItemLocalUnRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POASimilarItemLocalUnRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Domestic With Interstate UnRegistered GEneral With Similar
	 * Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithInterstateUnRg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("POASimilarItemInterstateUnRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POASimilarItemInterstateUnRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Domestic With Local registered GEneral With Dissimilar Stock
	 * And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithLocalRg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("POADissimilarItemLocalRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POADissimilarItemLocalRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Domestic With Interstate Registered GEneral With Dissimilar
	 * Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithInterstateRg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("AgrCeramics"), commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("POADissimilarItemInterstateRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AgrCeramics"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POADissimilarItemInterstateRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Domestic With Local UnRegistered GEneral With Dissimilar
	 * Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithLocaUnRg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("POADissimilarItemLocalUnRegSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POADissimilarItemLocalUnRegSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Domestic With interstate Registered GEneral With Dissimilar
	 * Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithInterstateUnRg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("POADissimilarItemInterstateUnRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POADissimilarItemInterstateUnRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Direct PO Domestic With Local Registered Material With Similar Stock
	 * And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithLocalRg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("POASimilarItemLocalRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POASimilarItemLocalRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Domestic With Interstate Registered Material With Similar
	 * Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithInterstateRg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("AgrCeramics"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("POASimilarItemInterstateRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AgrCeramics"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POASimilarItemInterstateRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Domestic With Local UnRegistered Material With Similar Stock
	 * And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithLocaUnRg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("POASimilarItemLocalUnRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POASimilarItemLocalUnRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Domestic With Interstate Unregistered Material With Similar
	 * Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithInterstateUnRg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("POASimilarItemInterstateUnRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POASimilarItemInterstateUnRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Domestic With Local Registered Material With Dissimilar
	 * Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithLocalRg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("POADissimilarItemLocalRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POADissimilarItemLocalRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Domestic With Interstate registered Material With Dissimilar
	 * Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithInterstateRg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("AgrCeramics"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("POADissimilarItemInterstateRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AgrCeramics"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POADissimilarItemInterstateRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Domestic With Local Unregistered Material With Dissimilar
	 * Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithLocaUnRg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("POADissimilarItemLocalUnRegSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POADissimilarItemLocalUnRegSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Domestic With Interstate UnRegistered Material With
	 * Dissimilar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithInterstateUnRg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("POADissimilarItemInterstateUnRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POADissimilarItemInterstateUnRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Direct PO Domestic With Local Registered Service With Similar Stock
	 * And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithLocalRg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputWithTaxValidation(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"),
				webDB.getDataFromProperties("POAServiceLocalRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POAServiceLocalRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Domestic With Interstate Registered Service With Similar
	 * Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithInterstateRg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputWithTaxValidation(
				commonFunc.getDataFromTestDataSheet("AgrCeramics"), commonFunc.getDataFromTestDataSheet("ServiceValue"),
				webDB.getDataFromProperties("POAServiceInterstateRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AgrCeramics"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POAServiceInterstateRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Domestic With Local Un Registered Service With Similar Stock
	 * And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithLocaUnRg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputWithTaxValidation(
				commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"),
				webDB.getDataFromProperties("POAServiceLocalUnRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POAServiceLocalUnRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Domestic With Interstate UnRegistered Service With Similar
	 * Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithInterstateUnRg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputWithTaxValidation(
				commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"),
				webDB.getDataFromProperties("POAServiceInterstateUnRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POAServiceInterstateUnRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Import General With Similar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPOGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithImport() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("POASimilarImportSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POASimilarImportSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Import GEneral With Dissimilar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPOGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithImport() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("POADissimilarImportSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POADissimilarImportSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Direct PO Import Material With Dissimilar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPOMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithImport() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("POADissimilarImportSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POADissimilarImportSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Direct PO Import Material With Similar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPOMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithImport() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("POASimilarImportSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POASimilarImportSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify Direct PO Import Service With Similar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyDirectPOServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithImport() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputWithTaxValidation(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"),
				webDB.getDataFromProperties("POAServiceImportSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("POAServiceImportSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify PR To PO Domestic With Local registered General With Similar Stock And
	 * Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithLocalReg() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheetWithTaxValidation(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_General"),
				webDB.getDataFromProperties("POASimilarLocalRgPRTPOSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POASimilarLocalRgPRTPOSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify PR To PO Domestic With Interstate registered General With Similar
	 * Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithInterstateReg() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheetWithTaxValidation(
				commonFunc.getDataFromTestDataSheet("AgrCeramics"), commonFunc.getDataFromTestDataSheet("PR_General"),
				webDB.getDataFromProperties("POASimilarInterstateRgPRTPOSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POASimilarInterstateRgPRTPOSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify PR To PO Domestic With Local Unregistered General With Similar Stock
	 * And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithLocalUnReg() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheetWithTaxValidation(
				commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_General"),
				webDB.getDataFromProperties("POASimilarLocalUnRgPRTPOSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POASimilarLocalUnRgPRTPOSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify PR To PO Domestic With Interstate Unregistered General With Similar
	 * Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithInterstateUnReg() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheetWithTaxValidation(
				commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_General"),
				webDB.getDataFromProperties("POASimilarInterstateUnRgPRTPOSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POASimilarInterstateUnRgPRTPOSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify PR To PO Import General With Similar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPOGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithImport() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheetWithTaxValidation(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_General"),
				webDB.getDataFromProperties("POASimilarImportPRTPOSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POASimilarImportPRTPOSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify PR To PO Domestic With Local registered General With Dissimilar Stock
	 * And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithLocalReg() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheetWithTaxValidation(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_General"),
				webDB.getDataFromProperties("POADissimilarLocalRegPRTPOSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POADissimilarLocalRegPRTPOSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify PR To PO Domestic With Interstate registered General With Dissimilar
	 * Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithInterstateReg() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheetWithTaxValidation(
				commonFunc.getDataFromTestDataSheet("AgrCeramics"), commonFunc.getDataFromTestDataSheet("PR_General"),
				webDB.getDataFromProperties("POADissimilarInterstRegPRTPOSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POADissimilarInterstRegPRTPOSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify PR To PO Domestic With Local Unregistered General With Dissimilar
	 * Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithLocalUnReg() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheetWithTaxValidation(
				commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_General"),
				webDB.getDataFromProperties("POADissimilarLocalUnRegPRTPOSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POADissimilarLocalUnRegPRTPOSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify PR To PO Domestic With Interstate Unregistered General With Dissimilar
	 * Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithInterstateUnReg() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheetWithTaxValidation(
				commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_General"),
				webDB.getDataFromProperties("POADissimilarInterstUnRegPRTPOSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POADissimilarInterstUnRegPRTPOSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify PR To PO Import General With Dissimilar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPOGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithImport() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheetWithTaxValidation(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_General"),
				webDB.getDataFromProperties("POADissimilarImportPRTPOSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POADissimilarImportPRTPOSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify PR To PO Domestic With Local Unregistered Service With Similar Stock
	 * And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithLocalReg() {
		purchaseOrderFunctions.verifyServiceFromPRtoPOWithMultipleItemInExcelSheetWithTaxValidation(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_Service"),
				webDB.getDataFromProperties("POAServiceLocalRgPRTPOSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PRGWL"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POAServiceLocalRgPRTPOSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify PR To PO Domestic With Interstate registered Service With Similar
	 * Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithInterstateReg() {
		purchaseOrderFunctions.verifyServiceFromPRtoPOWithMultipleItemInExcelSheetWithTaxValidation(
				commonFunc.getDataFromTestDataSheet("AgrCeramics"), commonFunc.getDataFromTestDataSheet("PR_Service"),
				webDB.getDataFromProperties("POAServiceInterstateRgPRTPOSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PRGWL"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POAServiceInterstateRgPRTPOSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify PR To PO Domestic With Local Unregistered Service With Similar Stock
	 * And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithLocalUnReg() {
		purchaseOrderFunctions.verifyServiceFromPRtoPOWithMultipleItemInExcelSheetWithTaxValidation(
				commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_Service"),
				webDB.getDataFromProperties("POAServiceLocalUnRgPRTPOSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PRGWL"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POAServiceLocalUnRgPRTPOSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify PR To PO Domestic With Interstate Unregistered Service With Similar
	 * Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithInterstateUnReg() {
		purchaseOrderFunctions.verifyServiceFromPRtoPOWithMultipleItemInExcelSheetWithTaxValidation(
				commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_Service"),
				webDB.getDataFromProperties("POAServiceInterstateUnRgPRTPOSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PRGWL"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POAServiceInterstateUnRgPRTPOSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * Verify PR To PO Import Service With Similar Stock And Supplier UOM
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPRToPOServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithImport() {
		purchaseOrderFunctions.verifyServiceFromPRtoPOWithMultipleItemInExcelSheetWithTaxValidation(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_Service"),
				webDB.getDataFromProperties("POAServiceImportPRTPOSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PRGWL"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("POAServiceImportPRTPOSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String HsnCode = commonFunc.getDataFromTestDataSheet("HsnCode80200");

						verifyCreateItemInPoAmendment(item);
						verifyAddItemInPOWithQuantityAndUnitCost(Quantity, UnitCost);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyLineDetailsUpdation(HsnCode);
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());
			flag = commonFunc.logoutUser();
			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
		}
		assertTrue(flag);

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic General With
	 * Similar Stock And Supplier With Local registered
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputLocalReg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("ModifyPOASimilarLocalRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOASimilarLocalRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic General With
	 * Similar Stock And Supplier With Interstate registered
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateReg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("AgrCeramics"), commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("ModifyPOASimilarInterstateRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AgrCeramics"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOASimilarInterstateRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic General With
	 * Similar Stock And Supplier With Interstate Unregistered
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateUnReg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("ModifyPOASimilarInterstateUnRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOASimilarInterstateUnRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic General With
	 * Similar Stock And Supplier With Local Unregistered
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputLocalUnReg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("ModifyPOASimilarLocalUnRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOASimilarLocalUnRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Import General With
	 * Similar Stock And Supplier With Local registered
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPOGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputImport() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("ModifyPOASimilarImportSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOASimilarImportSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic Material
	 * With Similar Stock And Supplier With Local registered
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputLocalReg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("ModifyPOASimilarLocalRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOASimilarLocalRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic Material
	 * With Similar Stock And Supplier With Interstate registered
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateReg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("AgrCeramics"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("ModifyPOASimilarInterstateRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AgrCeramics"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOASimilarInterstateRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic Material
	 * With Similar Stock And Supplier With Interstate Unregistered
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateUnReg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("ModifyPOASimilarInterstateUnRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOASimilarInterstateUnRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic Material
	 * With Similar Stock And Supplier With Local Unregistered
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputLocalUnReg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("ModifyPOASimilarLocalUnRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOASimilarLocalUnRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Import Material With
	 * Similar Stock And Supplier With Local registered
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPOMaterialWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputImport() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("ModifyPOASimilarImportSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOASimilarImportSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic Service With
	 * Similar Stock And Supplier With Local registered
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputLocalReg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputWithTaxValidation(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"),
				webDB.getDataFromProperties("ModifyPOAServiceLocalRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOAServiceLocalRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic Service With
	 * Similar Stock And Supplier With Interstate registered
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateReg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputWithTaxValidation(
				commonFunc.getDataFromTestDataSheet("AgrCeramics"), commonFunc.getDataFromTestDataSheet("ServiceValue"),
				webDB.getDataFromProperties("ModifyPOAServiceInterstateRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AgrCeramics"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOAServiceInterstateRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic Service With
	 * Similar Stock And Supplier With Interstate Unregistered
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateUnReg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputWithTaxValidation(
				commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"),
				webDB.getDataFromProperties("ModifyPOAServiceInterstateUnRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOAServiceInterstateUnRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic Service With
	 * Similar Stock And Supplier With Local Unregistered
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputLocalUnReg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputWithTaxValidation(
				commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"),
				webDB.getDataFromProperties("ModifyPOAServiceLocalUnRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOAServiceLocalUnRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Import Service With
	 * Similar Stock And Supplier With Local registered
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPOServiceWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputImport() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputWithTaxValidation(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"),
				webDB.getDataFromProperties("ModifyPOAServiceImportSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOAServiceImportSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic General With
	 * Dissimilar Stock And Supplier With Local registered
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputLocalReg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("ModifyPOADissimilarLocalUnRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOADissimilarLocalUnRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic General With
	 * Dissimilar Stock And Supplier With Interstate registered
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateReg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("AgrCeramics"), commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("ModifyPOADissimilarInterstateRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AgrCeramics"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOADissimilarInterstateRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic General With
	 * Dissimilar Stock And Supplier With Interstate Unregistered
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateUnReg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("ModifyPOADissimilarInterUnRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOADissimilarInterUnRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic General With
	 * Dissimilar Stock And Supplier With Local Unregistered
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputLocalUnReg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("ModifyPOADissimilarLocalUnRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOADissimilarLocalUnRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Import General With
	 * Dissimilar Stock And Supplier With Local registered
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPOGeneralWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputImport() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("ModifyPOADissimilarImportSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOADissimilarImportSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic Material
	 * With Dissimilar Stock And Supplier With Local registered
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputLocalReg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("ModifyPOADissimilarLocalRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOADissimilarLocalRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic Material
	 * With Dissimilar Stock And Supplier With Interstate registered
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateReg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("AgrCeramics"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("ModifyPOADissimilarInterstateRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("AgrCeramics"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOADissimilarInterstateRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic Material
	 * With Dissimilar Stock And Supplier With Interstate Unregistered
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateUnReg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("ModifyPOADissimilarInterUnRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOADissimilarInterUnRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic Material
	 * With Dissimilar Stock And Supplier With Local Unregistered
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPODomesticMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputLocalUnReg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("ModifyPOADissimilarLocalUnRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("UnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOADissimilarLocalUnRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Import Material With
	 * Similar Stock And Supplier With Local registered
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyDirectPOMaterialWithDissimilarStockAndSupplierUOMWithMultipleItemWithExcelInputImport() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("MaterialValue"),
				webDB.getDataFromProperties("ModifyPOADissimilarImportSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("WalmartSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyUsd"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOADissimilarImportSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend Modify Direct PO Domestic General With
	 * Similar Stock And Supplier With Interstate Unregistered
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendModifyPrToPoDomesticGeneralWithSimilarStockAndSupplierUOMWithMultipleItemWithExcelInputInterstateUnReg() {
		purchaseOrderFunctions.verifyPRtoPOWithMultipleItemInExcelSheetWithTaxValidation(
				commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("PR_General"),
				webDB.getDataFromProperties("ModifyPOASimilarIsURPrToPoSheet"),
				commonFunc.getDataFromTestDataSheet("Prefix_PR100"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("InterstateUnregisterSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		verifyAddPoLineItemForModify();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath,
				webDB.getDataFromProperties("ModifyPOASimilarIsURPrToPoSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddItemInPOWithQuantityAndUnitCostWithItemName(Quantity, UnitCost, item);
						verifyTickIconcreateItem();
						navigateToLineDetailPage(item);
						verifyEnterAmountForDiscountCalculation(TradePercentage, SplDiscPercentage, CashDiscPercentage);
						verifyLineDetailsUpdation();
						webDB.switchToFrame(POAmendmentLocators.LineDetailsIframe, ElementType.Xpath);
						navigateToPoaPage();
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}

	/**
	 * This Method Is Used To Verify Po Amend CloseShort Direct PO Domestic General
	 * With Similar Stock And Supplier
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyPoAmendCloseshortDirectPODomesticGeneralWithSimilarStockAndSupplierUOMWithLocalReg() {
		purchaseOrderFunctions.verifyDirectPOWithMultipleItemsWithExcelInputTaxValidation(
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"),
				webDB.getDataFromProperties("CSPOASimilarLocalRgSheet"));
		commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"),
				"AdminUsername-Admin");
		commonFunc.navigatePoAmendmentPage();
		createPendingPOAmendment();
		verifyUpdateSinglePoAmendmentDetails(commonFunc.getDataFromTestDataSheet("Loc_100"),
				VariableHandler.getPONumber(), commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("CurrencyInr"));
		verifyTickIconSingleAmendment();
		String[][] itemmultiple = new String[ExcelUtils.getRowCount()][ExcelUtils.getColCount()];
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "QA Scenario - Purchase.xlsx";
		itemmultiple = excelUtils.readAllDataInExcel(Filepath, webDB.getDataFromProperties("CSPOASimilarLocalRgSheet"));
		for (int j = 3; j < itemmultiple.length; j++) {
			if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("PurchaseOrderAmendment"))) {
				for (int i = j + 3; i < itemmultiple.length; i++) {
					String item = itemmultiple[i][1];
					if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
						String Quantity = itemmultiple[i][5];
						String UnitCost = itemmultiple[i][6];
						String TradePercentage = itemmultiple[i][8];
						String SplDiscPercentage = itemmultiple[i][9];
						String CashDiscPercentage = itemmultiple[i][10];
						verifyAddPoLineItemForCloseShort(item);
					} else {
						break;
					}
				}
			}
		}
		verifyHeaderTickAndPost();
		verifyWaitingForApproval();
		commonFunc.logoutUser();
		commonFunc.approveDocuments(webDB.getDataFromProperties("TvsA-BU"), VariableHandler.getPOANumber(),
				webDB.getDataFromProperties("HodEmpId"), commonFunc.getDataFromTestDataSheet("Unit_100"),
				System.getProperty("HariUsername"));
		if (flag) {
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePoAmendmentPage();
			verifyValidatePOAStatus(VariableHandler.getPOANumber(),
					commonFunc.getDataFromTestDataSheet("ApproveStatus"));
			flag = commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("AdminUsername"), System.getProperty("LoginPassword"));
			commonFunc.navigatePurchaseOrderPage();
			verifyOpenPONumber(VariableHandler.getPONumber());

			for (int j = 3; j < itemmultiple.length; j++) {
				if (itemmultiple[j][0].equals(commonFunc.getDataFromTestDataSheet("FinalPO"))) {
					for (int i = j + 3; i < itemmultiple.length; i++) {
						String item = itemmultiple[i][1];
						if (!(item.equals("") || item.equals(commonFunc.getDataFromTestDataSheet("Total")))) {
							String LineAmount = itemmultiple[i][18];
							String TaxPercent = itemmultiple[i][20];
							String cgst = itemmultiple[i][21];
							String sgst = itemmultiple[i][22];
							String Igst = itemmultiple[i][23];
							String Utgst = itemmultiple[i][24];
							String CessPercent = itemmultiple[i][25];
							String Cess = itemmultiple[i][26];
							purchaseOrderFunctions.verifyLineAmountValidationAndPom(item, LineAmount);
							purchaseOrderFunctions.verifyTaxValueValidation(item, TaxPercent, cgst, sgst, Igst, Utgst,
									CessPercent, Cess);
						}
					}
				}
			}
			flag = commonFunc.logoutUser();
		}

	}
}
