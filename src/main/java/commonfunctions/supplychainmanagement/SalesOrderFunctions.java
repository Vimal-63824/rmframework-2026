package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi
 * @company Galaxy Weblinks Feburary 12, 2025
 */
import static org.testng.Assert.assertTrue;

import java.math.BigDecimal;

import commonfunctions.CommonFunctions;
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

	/**
	 * @author Harikrishna -RMITS This Method Is used to Verify the Domestic
	 *         SOGeneral With SimilarStock And CustomerUOM
	 * @return boolean
	 */
	public boolean verifyDomesticSOGeneralSimilarStockAndCustomerUOM() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		addItemsInSO(commonFunc.getDataFromTestDataSheet("SO_Similar_Item"));
		addQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("SO_Qty"),
				commonFunc.getDataFromTestDataSheet("SO_UnitCost"));
		chooseHsnCodeAndClickLineWiseSaveButton(commonFunc.getDataFromTestDataSheet("SO_HSNCode"));
		clickLineWiseDetailsButton();
		validateItemPriceBasis();
		enterSODiscountDetails(SalesOrderLocators.SOTradeDiscountpercentageField,
				commonFunc.getDataFromTestDataSheet("SO_Trade_Disc"));
		enterSODiscountDetails(SalesOrderLocators.SOSpecialDiscountpercentageField,
				commonFunc.getDataFromTestDataSheet("SO_Special_Disc"));
		enterSODiscountDetails(SalesOrderLocators.SOCashDiscountpercentageField,
				commonFunc.getDataFromTestDataSheet("SO_Cash_Disc"));
		clickLineWiseDetailsSaveButton();
		getSalesOrderTotalAmount();
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
				validateSalesOrderDocumentStatus(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used to Verify the Domestic
	 *         SOGeneral With DisSimilar Stock And CustomerUOM
	 * @return boolean
	 */
	public boolean verifyDomesticSOGeneralDisSimilarStockAndCustomerUOM() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		addItemsInSO(commonFunc.getDataFromTestDataSheet("SO_DisSimilarItem"));
		addQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("SO_Qty"),
				commonFunc.getDataFromTestDataSheet("SO_UnitCost"));
		chooseHsnCodeAndClickLineWiseSaveButton(commonFunc.getDataFromTestDataSheet("SO_HSNCode"));
		clickLineWiseDetailsButton();
		validateItemPriceBasis();
		enterSODiscountDetails(SalesOrderLocators.SOTradeDiscountpercentageField,
				commonFunc.getDataFromTestDataSheet("SO_Trade_Disc"));
		enterSODiscountDetails(SalesOrderLocators.SOSpecialDiscountpercentageField,
				commonFunc.getDataFromTestDataSheet("SO_Special_Disc"));
		enterSODiscountDetails(SalesOrderLocators.SOCashDiscountpercentageField,
				commonFunc.getDataFromTestDataSheet("SO_Cash_Disc"));
		clickLineWiseDetailsSaveButton();
		getSalesOrderTotalAmount();
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
				validateSalesOrderDocumentStatus(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used to Verify the Export SOGeneral
	 *         With SimilarStock And CustomerUOM
	 * @return boolean
	 */
	public boolean verifyExportSOGeneralSimilarStockAndCustomerUOM() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		addItemsInSO(commonFunc.getDataFromTestDataSheet("SO_Similar_Item"));
		addQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("SO_Qty"),
				commonFunc.getDataFromTestDataSheet("SO_UnitCost"));
		chooseHsnCodeAndClickLineWiseSaveButton(commonFunc.getDataFromTestDataSheet("SO_HSNCode"));
		clickLineWiseDetailsButton();
		validateItemPriceBasis();
		enterSODiscountDetails(SalesOrderLocators.SOTradeDiscountpercentageField,
				commonFunc.getDataFromTestDataSheet("SO_Trade_Disc"));
		enterSODiscountDetails(SalesOrderLocators.SOSpecialDiscountpercentageField,
				commonFunc.getDataFromTestDataSheet("SO_Special_Disc"));
		enterSODiscountDetails(SalesOrderLocators.SOCashDiscountpercentageField,
				commonFunc.getDataFromTestDataSheet("SO_Cash_Disc"));
		clickLineWiseDetailsSaveButton();
		getSalesOrderTotalAmount();
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
				validateSalesOrderDocumentStatus(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used to Verify the Export SOGeneral
	 *         With DisSimilar Stock And CustomerUOM
	 * @return boolean
	 */
	public boolean verifyExportSOGeneralDisSimilarStockAndCustomerUOM() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		addItemsInSO(commonFunc.getDataFromTestDataSheet("SO_DisSimilarItem"));
		addQuantityAndUnitCost(commonFunc.getDataFromTestDataSheet("SO_Qty"),
				commonFunc.getDataFromTestDataSheet("SO_UnitCost"));
		chooseHsnCodeAndClickLineWiseSaveButton(commonFunc.getDataFromTestDataSheet("SO_HSNCode"));
		clickLineWiseDetailsButton();
		validateItemPriceBasis();
		enterSODiscountDetails(SalesOrderLocators.SOTradeDiscountpercentageField,
				commonFunc.getDataFromTestDataSheet("SO_Trade_Disc"));
		enterSODiscountDetails(SalesOrderLocators.SOSpecialDiscountpercentageField,
				commonFunc.getDataFromTestDataSheet("SO_Special_Disc"));
		enterSODiscountDetails(SalesOrderLocators.SOCashDiscountpercentageField,
				commonFunc.getDataFromTestDataSheet("SO_Cash_Disc"));
		clickLineWiseDetailsSaveButton();
		getSalesOrderTotalAmount();
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
				validateSalesOrderDocumentStatus(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used to Verify the Domestic
	 *         SOGeneral Rate Contract With Similar Stock And CustomerUOM
	 * @return
	 */
	public boolean verifyDomesticSOGeneralRateContractSimilarStockAndCustomerUOM() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("Customer"));
		addItemsInSO(commonFunc.getDataFromTestDataSheet("SO_Similar_Item_Rate_Contract"));
		addLineItemQty(commonFunc.getDataFromTestDataSheet("SO_Qty"));
		clickLineWiseSaveAndGetTotAmt();
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
				validateSalesOrderDocumentStatus(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
	}

	/**
	 * @author Harikrishna -RMITS This Method Is used to Verify the Export SOGeneral
	 *         Rate Contract With Similar Stock And CustomerUOM
	 * @return
	 */
	public boolean verifyExportSOGeneralRateContractSimilarStockAndCustomerUOM() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateSalesOrderPage();
		verifyCreationOfSalesOrderHeader(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("Unit_100"), commonFunc.getDataFromTestDataSheet("SO_General"),
				commonFunc.getDataFromTestDataSheet("SO_Export_Customer"));
		addItemsInSO(commonFunc.getDataFromTestDataSheet("SO_Similar_Item_Rate_Contract"));
		addLineItemQty(commonFunc.getDataFromTestDataSheet("SO_Qty"));
		clickLineWiseSaveAndGetTotAmt();
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
				validateSalesOrderDocumentStatus(VariableHandler.getSONumber(),
						commonFunc.getDataFromTestDataSheet("ApproveStatus"));
				flag = commonFunc.logoutUser();
			}
		}
		return flag;
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
		flag = webDB.waitForElement(SalesOrderLocators.LineWiseAddIcon, ElementType.Xpath);
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
		commonFunc.checkRefreshCellsDisappear();
		verifyClickAndEnterValueInTableFields(SalesOrderLocators.UnitCostField, SalesOrderLocators.UnitCostInputField,
				UnitCost);
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
			webDB.clickAnElement(SalesOrderLocators.HSNCodeField, ElementType.Xpath);
			logs.logging("INFO", "HSC Code Field Is Clicked");
			commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.HSNCodeLOV, HSNCode);
			flag = webDB.waitForElement(SalesOrderLocators.HSNCodeInputField, ElementType.Xpath);
			if (flag) {
				String Value = webDB.getDomPropertyFromElement(SalesOrderLocators.HSNCodeInputField, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value"));
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
						flag = webDB.waitForElement(SalesOrderLocators.SOTotalAmountFieldTrans, ElementType.Xpath);
						if (flag) {
							String SOTotAmt = webDB.getDomPropertyFromElement(
									SalesOrderLocators.SOTotalAmountFieldTrans, ElementType.Xpath,
									commonFunc.getDataFromTestDataSheet("Value"));
							VariableHandler.setSOTotalAmount(SOTotAmt);
							logs.logging("INFO", "SalesOrder Total Amount is :" + VariableHandler.getSOTotalAmount());
						}
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
		commonFunc.checkSuccessToastMessageDisappear();
		flag = webDB.waitForClickElement(SalesOrderLocators.PostButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(SalesOrderLocators.PostButton, ElementType.Xpath);
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
	 * @return
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
					logs.logging("INFO", "Verified Navigate To PO Home Page");
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
	public void validateSalesOrderDocumentStatus(String SONumber, String ExpStatus) {
		flag = webDB.waitForElement(SalesOrderLocators.FindSONumberLOV, ElementType.Xpath);
		if (flag) {
			commonFunc.searchAndSelectValueInFilters(SalesOrderLocators.FindSONumberLOV, SONumber);
			flag = webDB.waitForElement(SalesOrderLocators.FindSearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(SalesOrderLocators.FindSearchButton, ElementType.Xpath);
				commonFunc.hardWait(2000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(SalesOrderLocators.SOStatusTableText(SONumber), ElementType.Xpath);
				if (flag) {
					String Status = webDB.getTextFromElement(SalesOrderLocators.SOStatusTableText(SONumber),
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
							logs.logging("INFO", "GetText Customer Desc is :" + Customer);
							flag = Customer.equals(ExpCustId);
							if (flag) {
								logs.logging("INFO", "Verified Expected And Actual Customer Description");
								verifyTotalSOAmountCalculationInDocApproval();
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
		webDB.switchToFrame(SalesOrderLocators.LineDetailsFrame, ElementType.Xpath);
		flag = webDB.waitForElement(Locator, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(Locator, ElementType.Xpath);
			webDB.sendTextToAnElement(Locator, InputValue, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			webDB.pressTabKey();
		}
		webDB.switchToDefaultContent();
		assertTrue(flag);
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
				if (webDB.verifyIsElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
			if (flag) {
				logs.logging("INFO", "Line Wise Details are saved sucessfully");
			}
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
	public void checkSaleOrderTaxCalculationForLastAddedItem(String Unit, String Cost) {
		logs.logging("INFO", "Sales Order Tax Calculation!!!");
		BigDecimal decimal_Unit = new BigDecimal(Unit);
		decimal_Unit = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(decimal_Unit);
		BigDecimal decimal_Cost = new BigDecimal(Cost);
		decimal_Cost = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(decimal_Cost);
		BigDecimal totalCostWithoutTax = decimal_Unit.multiply(decimal_Cost);
		totalCostWithoutTax = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(totalCostWithoutTax);
		logs.logging("INFO", "Unit and Cost Multiply value without Cost: " + totalCostWithoutTax);
		flag = webDB.waitForElement(SalesOrderLocators.TaxPercentField, ElementType.Xpath);
		if (flag) {
			BigDecimal taxValue = new BigDecimal(
					webDB.getTextFromElement(SalesOrderLocators.TaxPercentField, ElementType.Xpath));
			taxValue = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(taxValue);
			logs.logging("INFO", "Displayed tax Percentage: " + taxValue.intValue() + "%");
			BigDecimal TaxAfterTax = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(
					totalCostWithoutTax.divide(new BigDecimal("100")).multiply(taxValue));
			logs.logging("INFO", "Expected value After tax calculation: " + TaxAfterTax);
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
			taxAmount = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(taxAmount);
			logs.logging("INFO", "After Adding Displayed GST Values: " + taxAmount);
			flag = taxAmount.equals(TaxAfterTax);
			if (flag) {
				logs.logging("INFO", "Tax Amount Verified, Tax added value: " + taxAmount);
				logs.logging("INFO", "verifing total amount after calculating tax amount!!!");
				BigDecimal totalAmount = new BigDecimal(VariableHandler.getSOTotalAmount());
				totalAmount = purchaseOrderFunc.verifyRoundOffBigDecimalValueWithReturnBigDecimal(totalAmount);
				logs.logging("INFO", "Displayed Total Amount: " + totalAmount);
				BigDecimal calculationAmount = purchaseOrderFunc
						.verifyRoundOffBigDecimalValueWithReturnBigDecimal(totalCostWithoutTax.add(taxAmount));
				logs.logging("INFO", "Calculated Total Amount: " + calculationAmount);
				flag = totalAmount.equals(calculationAmount);
				if (flag) {
					logs.logging("INFO", "Verified Total Amount Displayed and Calculated Total Amount Matched");
				}
			}
		}
		assertTrue(flag);
	}
}
