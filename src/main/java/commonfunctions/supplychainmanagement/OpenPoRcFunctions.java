package commonfunctions.supplychainmanagement;

/**
 * @author Deepan-GWL, Sakthi-GWL
 * @company Galaxy Weblinks May 01, 2025
 */
import static org.testng.Assert.assertTrue;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import commonfunctions.CommonFunctions;
import commonfunctions.VariableHandler;
import commonlocators.CommonUiLocators;
import commonlocators.supplychainmanagement.OpenPoRcLocators;
import commonlocators.supplychainmanagement.PurchaseOrderPageLocators;
import utils.ExcelUtils;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class OpenPoRcFunctions {
	boolean flag = false;
	private static WebDriverBase webDB = new WebDriverBase();
	private static ReportLoger logs = new ReportLoger();
	public CommonFunctions commonFunc = new CommonFunctions();
	private PricelistPageFunctions pricelistPageFunctions = new PricelistPageFunctions();
	private PurchaseOrderFunctions purchaseOrderFunc = new PurchaseOrderFunctions();
	public ApprovalPageFunctions approvalFunc = new ApprovalPageFunctions();
	public PurchaseOrderFunctions purchaseOrderFunctions = new PurchaseOrderFunctions();

	/**
	 * This Method Is Used To Click open Po/ Rc Button
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyClickOpenPORcButton() {
		flag = webDB.waitForElement(CommonUiLocators.OpenPO_RCButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Ope Po Rc Button Is Displayed");
			webDB.clickAnElement(CommonUiLocators.OpenPO_RCButton, ElementType.Xpath);
			flag = webDB.waitForElement(OpenPoRcLocators.CreateButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Create Button Is Displayed");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Create Open PO Doc
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateOpenPoDoc(String Location, String OpenPOType, String Supplier) {
		flag = webDB.waitForElement(OpenPoRcLocators.CreateButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenPoRcLocators.CreateButton, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(OpenPoRcLocators.LocationId, Location);
			webDB.selectDropDownByText(OpenPoRcLocators.PoTypeSelect, OpenPOType, ElementType.Xpath);
			logs.logging("INFO", "Select Text Po Type Is: " + OpenPOType);
			commonFunc.searchAndSelectValueInFilters(OpenPoRcLocators.SupplierButton, Supplier);
			logs.logging("INFO", "Supplier Button Is Displayed" + Supplier);
			flag = webDB.waitForElement(OpenPoRcLocators.TickButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(OpenPoRcLocators.TickButton, ElementType.Xpath);
				flag = webDB.waitForElement(OpenPoRcLocators.OpenPoNo, ElementType.Xpath);
				if (flag) {
					String OpenPONumber = webDB.getTextFromElement(OpenPoRcLocators.OpenPoNo, ElementType.Xpath);
					logs.logging("INFO", "Get Text Open PO Number Is: " + OpenPONumber);
					VariableHandler.setOpenPoNumber(OpenPONumber);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Create - Standard - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateStandardDomesticWithStockAndSupplierSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPORcButton();
		verifyCreateOpenPoDoc(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("StandardPO"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		verifyAddItemInOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyAddAggrDateAndAggrNumber();
		verifyHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getOpenPoNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getOpenPoNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyUnitCostValidation(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("ZeroValue"));
		ExcelUtils excelUtils = new ExcelUtils();
		String Filepath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator
				+ "OpenPOSheet.xlsx";
		excelUtils.writeDataInExcel(Filepath, commonFunc.getDataFromTestDataSheet("OpenPoSheetName"), 3, 1,
				VariableHandler.getItemName());
		excelUtils.writeDataInExcel(Filepath, commonFunc.getDataFromTestDataSheet("OpenPoSheetName"), 3, 2,
				VariableHandler.getItemName());
		excelUtils.writeDataInExcel(Filepath, commonFunc.getDataFromTestDataSheet("OpenPoSheetName"), 3, 5,
				commonFunc.getDataFromTestDataSheet("QuantityValue"));
		excelUtils.writeDataInExcel(Filepath, commonFunc.getDataFromTestDataSheet("OpenPoSheetName"), 3, 6,
				commonFunc.getDataFromTestDataSheet("UnitCostValue"));
		excelUtils.writeDataInExcel(Filepath, commonFunc.getDataFromTestDataSheet("OpenPoSheetName"), 3, 8,
				commonFunc.getDataFromTestDataSheet("QuantityValue"));
		excelUtils.writeDataInExcel(Filepath, commonFunc.getDataFromTestDataSheet("OpenPoSheetName"), 3, 19,
				commonFunc.getDataFromTestDataSheet("HsnCode_PO"));
		excelUtils.writeDataInExcel(Filepath, commonFunc.getDataFromTestDataSheet("OpenPoSheetName"), 3, 20,
				commonFunc.getDataFromTestDataSheet("EighteenPercent"));
	}

	/**
	 * This Method Is Used To Verify Add Item In OpenPo Line
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 * @param UnitCost
	 * @param DiscPercentage
	 * @param HSNCode
	 */
	public void verifyAddItemInOpenPOLine(String Item, String UnitCost, String DiscPercentage, String HSNCode) {
		addItemButton(Item);
		purchaseOrderFunc.verifyAddTableSpecs(OpenPoRcLocators.UnitCost, OpenPoRcLocators.UnitCostInput, UnitCost);
		purchaseOrderFunc.verifyAddTableSpecs(OpenPoRcLocators.DiscPercentage, OpenPoRcLocators.DiscPercentageInput,
				DiscPercentage);
		verifyHSNDetailsUpdation(HSNCode);
		verifyItemTickButton();

	}

	/**
	 * This Method Is Used To Add Item Button
	 * 
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 */
	public void addItemButton(String Item) {
		flag = webDB.waitForElement(OpenPoRcLocators.AddItem, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenPoRcLocators.AddItem, ElementType.Xpath);
			flag = webDB.waitForElement(OpenPoRcLocators.ItemTable, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(OpenPoRcLocators.ItemTable, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(OpenPoRcLocators.ItemLovButton, Item);
//				flag = webDB.waitForElement(OpenPoRcLocators.ItemTable, ElementType.Xpath);
//				if (flag) {
//					String value = webDB.getTextFromElement(OpenPoRcLocators.ItemTable, ElementType.Xpath);
//					logs.logging("INFO", "Get Text Item Name Value from Table Is: " + value);
//					flag = value.contains(Item);
//					if (flag) {
//						logs.logging("INFO", "Verified Item Value Successfully");
//					}
//				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Hsn Details Updation
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyHSNDetailsUpdation(String HSNCode) {
		webDB.moveToElement(OpenPoRcLocators.HsnCodeTable, ElementType.Xpath);
		flag = webDB.waitForElement(OpenPoRcLocators.HsnCodeTable, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "HSN Code In Table Is Displayed");
			webDB.clickAnElement(OpenPoRcLocators.HsnCodeTable, ElementType.Xpath);
			commonFunc.searchAndSelectValueInFilters(OpenPoRcLocators.HSNCode, HSNCode);
		}
		assertTrue(flag);

	}

	/**
	 * This Method Is Used To Verify Item Tick Button
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyItemTickButton() {
		flag = webDB.waitForElement(OpenPoRcLocators.ItemTickButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenPoRcLocators.ItemTickButton, ElementType.Xpath);
			commonFunc.hardWait(1000);
			if (webDB.verifyIsElementDisplayed(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath)) {
				logs.logging("INFO", "Item Tick Icon Is Clicked");
				webDB.clickAnElement(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath);
				logs.logging("INFO", "Toast Close Icon Is Clicked");
			}
			flag = webDB.waitForElement(OpenPoRcLocators.HeaderTickIcon, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Enter Agg Date & Agg Number
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAddAggrDateAndAggrNumber() {
		flag = webDB.waitForElement(OpenPoRcLocators.AggregateDate, ElementType.Xpath);
		if (flag) {
			LocalDate today = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			webDB.clickAnElement(OpenPoRcLocators.AggregateDate, ElementType.Xpath);
			commonFunc.hardWait(1000);
			webDB.clickAnElement(OpenPoRcLocators.AmendAggrCalendarDate, ElementType.Xpath);
			webDB.clickAnElement(OpenPoRcLocators.OpenPoNo, ElementType.Xpath);
			webDB.sendTextToAnElement(OpenPoRcLocators.AggregateNumber, VariableHandler.getOpenPoNumber(),
					ElementType.Xpath);
			logs.logging("INFO", "Aggregate Number Is SendText Successfully");
			webDB.clearText(OpenPoRcLocators.EffFrom, ElementType.Xpath);
			webDB.clickAnElement(OpenPoRcLocators.OpenPoNo, ElementType.Xpath);
			webDB.sendTextToAnElement(OpenPoRcLocators.EffFrom, today.format(formatter), ElementType.Xpath);
			logs.logging("INFO", "Send Text Effective Date Value Is: " + today.format(formatter));
			String EffValue = webDB.getDomPropertyFromElement(OpenPoRcLocators.EffFrom, ElementType.Xpath,
					commonFunc.getDataFromTestDataSheet("Value"));
			if (EffValue.equals(today.format(formatter))) {
				logs.logging("INFO", "Verified Effective From Date Successfully");
				webDB.clickAnElement(OpenPoRcLocators.OpenPoNo, ElementType.Xpath);
				webDB.clearText(OpenPoRcLocators.EffTo, ElementType.Xpath);
				webDB.clickAnElement(OpenPoRcLocators.OpenPoNo, ElementType.Xpath);
				webDB.sendTextToAnElement(OpenPoRcLocators.EffTo, commonFunc.getDataFromTestDataSheet("EffToDate"),
						ElementType.Xpath);
				logs.logging("INFO",
						"Send Text Effective To Date Is: " + commonFunc.getDataFromTestDataSheet("EffToDate"));
				String EffToValue = webDB.getDomPropertyFromElement(OpenPoRcLocators.EffTo, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value"));
				if (EffToValue.equals(commonFunc.getDataFromTestDataSheet("EffToDate"))) {
					logs.logging("INFO", "Verified Effective Date Successfully");
					webDB.clickAnElement(OpenPoRcLocators.OpenPoNo, ElementType.Xpath);
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Header Save And Post
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyHeaderSaveAndPost() {
		flag = webDB.waitForElement(OpenPoRcLocators.HeaderTickIcon, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Header Tick Icon Is Displayed");
			webDB.clickAnElement(OpenPoRcLocators.HeaderTickIcon, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(5000);
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForClickElement(OpenPoRcLocators.HeaderPostButton, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.clickAnElement(OpenPoRcLocators.HeaderPostButton, ElementType.Xpath);
				flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
				if (flag) {
					logs.logging("INFO", "Post Button Clicked Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify OpenPo Approval Status
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyOpenPoApprovalStatus(String DocNo, String ExpStatus) {
		commonFunc.searchAndSelectValueInFilters(OpenPoRcLocators.OpenPoLovButton, DocNo);
		flag = webDB.waitForElement(OpenPoRcLocators.HeaderCheckBox, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenPoRcLocators.HeaderCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(OpenPoRcLocators.OpenPoSearchButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Header CheckBox Is Clicked");
				webDB.clickAnElement(OpenPoRcLocators.OpenPoSearchButton, ElementType.Xpath);
				logs.logging("INFO", "Search Button Is Clicked");
				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(OpenPoRcLocators.OpenPoStatus(DocNo), ElementType.Xpath);
				if (flag) {
					String Status = webDB.getTextFromElement(OpenPoRcLocators.OpenPoStatus(DocNo), ElementType.Xpath);
					flag = Status.contains(ExpStatus);
					if (flag) {
						logs.logging("INFO", "Expected Status: " + ExpStatus);
						logs.logging("INFO", "Verified Status ,Displayed Status: " + ExpStatus);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Unit Cost Validation
	 * 
	 * @author Deepan-GWL
	 * @param quantity
	 * @param UnitCost
	 * @param DiscValue
	 * @param AmendValue
	 */
	public void verifyUnitCostValidation(String quantity, String UnitCost, String DiscValue, String AmendValue) {
		commonFunc.checkRefreshCellsDisappear();
		verifyAddTableSpecs(PurchaseOrderPageLocators.OrderedQuantity, PurchaseOrderPageLocators.OrderedQuantityInput,
				quantity);
		webDB.pressEnterKey();
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(PurchaseOrderPageLocators.UnitCostTable(1), ElementType.Xpath);
		if (flag) {
			commonFunc.hardWait(3000);
			String Text = webDB.getTextFromElement(PurchaseOrderPageLocators.UnitCostTable(1), ElementType.Xpath);
			logs.logging("INFO", "Get Text Value Unit Cost Is: " + Text);
			flag = Text.equals(UnitCost);
			if (flag) {
				purchaseOrderFunc.verifyTickIconcreateItem();
				purchaseOrderFunc.navigateToLineDetailPage(VariableHandler.getItemName());
				webDB.switchToFrame(PurchaseOrderPageLocators.LineDetailsIframePO, ElementType.Xpath);
				verifyTradeDiscountPercentage(DiscValue);
				purchaseOrderFunc.verifyNavigateLineDetailTwo();
				verifyOpenPODocumentsValidation(AmendValue);
			}

		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Trade Discount Percentage
	 * 
	 * @author Deepan-GWL
	 * @param DiscountValue
	 */
	public void verifyTradeDiscountPercentage(String DiscountValue) {
		String DiscValue = webDB.getTextFromElement(PurchaseOrderPageLocators.TradePercentageValue, ElementType.Xpath);
		purchaseOrderFunc.verifyAssertionBothValues(DiscValue, DiscountValue);
	}

	/**
	 * This Method Is Used To Verify Open PO Documents Validation
	 * 
	 * @author Deepna-GWL
	 * @param AmendValue
	 */
	public void verifyOpenPODocumentsValidation(String AmendValue) {
		String ContractNumber = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.ContractId, ElementType.Xpath,
				commonFunc.getDataFromTestDataSheet("Value"));
		purchaseOrderFunc.verifyAssertionBothValues(ContractNumber, VariableHandler.getOpenPoNumber());
		String ContractAmendNumber = webDB.getDomPropertyFromElement(PurchaseOrderPageLocators.ContractAmendNumber,
				ElementType.Xpath, commonFunc.getDataFromTestDataSheet("Value"));
		purchaseOrderFunc.verifyAssertionBothValues(ContractAmendNumber, AmendValue);
		webDB.switchToDefaultContent();
		flag = webDB.waitForElement(PurchaseOrderPageLocators.LineDetailsCloseIcons, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(PurchaseOrderPageLocators.LineDetailsCloseIcons, ElementType.Xpath);
			flag = webDB.waitForElement(PurchaseOrderPageLocators.ItemAddButton, ElementType.Xpath);
			logs.logging("INFO", "Verified Navigate To Purchase Order Page");
		}
		assertTrue(flag);
	}

	/**
	 * Create - Standard - Domestic Supplier with Create - Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateStandardDomesticWithStockAndSupplierDissimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPORcButton();
		verifyCreateOpenPoDoc(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("StandardPO"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		verifyAddItemInOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyAddAggrDateAndAggrNumber();
		verifyHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getOpenPoNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getOpenPoNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyUnitCostValidation(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("ZeroValue"));

	}

	/**
	 * Create - Standard - Import Supplier with Create - Stock and Supplier UOM
	 * similar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateStandardImportWithStockAndSupplierSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPORcButton();
		verifyCreateOpenPoDoc(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("StandardPO"),
				commonFunc.getDataFromTestDataSheet("GoogleSupplier"));
		verifyAddItemInOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyAddAggrDateAndAggrNumber();
		verifyHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getOpenPoNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getOpenPoNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GoogleSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyUnitCostValidation(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("ZeroValue"));

	}

	/**
	 * Create - Standard - Import Supplier with Create - Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateStandardImportWithStockAndSupplierDissimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPORcButton();
		verifyCreateOpenPoDoc(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("StandardPO"),
				commonFunc.getDataFromTestDataSheet("GoogleSupplier"));
		verifyAddItemInOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyAddAggrDateAndAggrNumber();
		verifyHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getOpenPoNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getOpenPoNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GoogleSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyUnitCostValidation(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("ZeroValue"));

	}

	/**
	 * Verify Add Table Specs
	 * 
	 * @author Deepan-GWL
	 * @param Tablespec
	 * @param InputParameter
	 * @param Enteredvalue
	 */
	public void verifyAddTableSpecs(String Tablespec, String InputParameter, String Enteredvalue) {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(Tablespec, ElementType.Xpath);
		if (flag) {
			webDB.doubleClickAnElement(Tablespec, ElementType.Xpath);
			logs.logging("INFO", "Table Input Box Is Clicked");
			webDB.clearText(InputParameter, ElementType.Xpath);
			logs.logging("INFO", "Clear Text Field The Input Value");
			flag = webDB.waitForElement(Tablespec, ElementType.Xpath);
			if (flag) {
				commonFunc.hardWait(1000);
				webDB.clickAnElement(Tablespec, ElementType.Xpath);
				webDB.sendTextToAnElement(InputParameter, Enteredvalue, ElementType.Xpath);
				logs.logging("INFO", "Entered Value Is: " + Enteredvalue);
				String value = webDB.getAttributeFromElement(InputParameter, ElementType.Xpath,
						commonFunc.getDataFromTestDataSheet("Value"));
				logs.logging("INFO", "Get Attribute Value Is: " + value);
				flag = value.contains(Enteredvalue);
				if (flag) {
					logs.logging("INFO", "Verified Entered Value With Get Attribute Value");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Create - Stock transfer - Domestic Supplier with Stock and Supplier UOM
	 * similar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateStockTransferDomesticWithStockAndSupplierSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPORcButton();
		verifyCreateOpenPoDoc(commonFunc.getDataFromTestDataSheet("Loc16"),
				commonFunc.getDataFromTestDataSheet("StockTransferPO"),
				commonFunc.getDataFromTestDataSheet("StockTransferSupplierOpenPO"));
		verifyAddItemInOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyAddAggrDateAndAggrNumber();
		verifyHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getOpenPoNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getOpenPoNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc16"),
				commonFunc.getDataFromTestDataSheet("StockTransferSupplierOpenPO"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyUnitCostValidation(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("ZeroValue"));
	}

	/**
	 * Create - Stock transfer - Domestic Supplier with Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateStockTransferDomesticWithStockAndSupplierDissimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPORcButton();
		verifyCreateOpenPoDoc(commonFunc.getDataFromTestDataSheet("Loc16"),
				commonFunc.getDataFromTestDataSheet("StockTransferPO"),
				commonFunc.getDataFromTestDataSheet("StockTransferSupplierOpenPO"));
		verifyAddItemInOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyAddAggrDateAndAggrNumber();
		verifyHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getOpenPoNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getOpenPoNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc16"),
				commonFunc.getDataFromTestDataSheet("StockTransferSupplierOpenPO"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyUnitCostValidation(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("ZeroValue"));

	}

	/**
	 * Create - Service - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCreateServiceDomesticWithStockAndSupplierSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("GwlItem_Service"));
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPORcButton();
		verifyCreateOpenPoDoc(commonFunc.getDataFromTestDataSheet("L0001"),
				commonFunc.getDataFromTestDataSheet("StandardService"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		verifyAddItemInOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyAddAggrDateAndAggrNumber();
		verifyHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getOpenPoNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getOpenPoNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0001"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyUnitCostValidation(commonFunc.getDataFromTestDataSheet("QuantityValue"),
				commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("ZeroValue"));

	}

	/**
	 * This Method Is Used To Verify OpenPO Cancel Line
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 */
	public void verifyPOAmendmentCancelLine() {
		commonFunc.checkRefreshCellsDisappear();
		flag = webDB.waitForElement(OpenPoRcLocators.LineCancelButton, ElementType.Xpath);
		if (flag) {
			webDB.moveToElement(OpenPoRcLocators.ItemTable, ElementType.Xpath);
			webDB.moveToElement(OpenPoRcLocators.LineCancelButton, ElementType.Xpath);
			webDB.clickAnElement(OpenPoRcLocators.LineCancelButton, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Toast Message Displayed, Line Deleted Successfully");
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
		webDB.moveToElement(OpenPoRcLocators.HeaderCancelButton, ElementType.Xpath);
		flag = webDB.waitForElement(OpenPoRcLocators.HeaderCancelButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenPoRcLocators.HeaderCancelButton, ElementType.Xpath);
			flag = webDB.waitForElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Toast Message Displayed, OpenPO Cancelled Successfully");
				if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
					webDB.clickAnElement(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
				}
			}
		}

		assertTrue(flag);
	}

	/**
	 * Open Purchase Order - Line Wise Cancel Open Purchase Order document Cancel
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyCancelOpenPODocuments() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPORcButton();
		verifyCreateOpenPoDoc(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("StandardPO"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		verifyAddItemInOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyPOAmendmentCancelLine();
		verifyCancelDocuments();
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("CancelStatus"));
		commonFunc.logoutUser();
	}

	/**
	 * This Method Is Used To Verify Filter Search OpenPO
	 * 
	 * @author Deepna-GWL
	 * @param LovButton
	 * @param Value
	 * @param i
	 */
	public void verifyFilterSearchOpenPO(String LovButton, String Value, int i) {
		webDB.scrollToAnElement(LovButton, ElementType.Xpath);
		commonFunc.searchAndSelectValueInFilters(LovButton, Value);
		clickSearchButton();
		commonFunc.hardWait(3000);
		verifyTableValue(i, Value);
		clearSearchFilter();
	}

	/**
	 * This Method Is Used To Click Search Button
	 * 
	 * @author Deepan-GWL
	 */
	public void clickSearchButton() {
		flag = webDB.waitForElement(OpenPoRcLocators.HeaderCheckBox, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenPoRcLocators.HeaderCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(OpenPoRcLocators.OpenPoSearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(OpenPoRcLocators.OpenPoSearchButton, ElementType.Xpath);
				logs.logging("INFO", "Search Button Is Clicked");

				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(OpenPoRcLocators.OpenPOTable, ElementType.Xpath);
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Table Value
	 * 
	 * @author Deepan-GWL
	 * @param i
	 * @param Value
	 */
	public void verifyTableValue(int i, String Value) {
		List<WebElement> elements = webDB.getDriver().findElements(By.xpath(OpenPoRcLocators.FindTableData(i)));
		for (WebElement element : elements) {
			if (element.isDisplayed()) {
				flag = element.getText().contains(Value);
				System.out.println(element.getText());
				if (!flag) {
					break;
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Clear Search Filter
	 * 
	 * @author Deepan-GWL
	 */
	public void clearSearchFilter() {
		flag = webDB.waitForElement(OpenPoRcLocators.ClearButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenPoRcLocators.ClearButton, ElementType.Xpath);
			logs.logging("INFO", "Clear Button Is Clicked");
			commonFunc.hardWait(1000);
			commonFunc.checkRefreshCellsDisappear();
		}
	}

	/**
	 * This Method Is Used To Verify Filter Open PO Table Data
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyFilterOpenPOTableData() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPORcButton();
		verifyFilterSearchOpenPO(OpenPoRcLocators.FindOpenPoNoButton,
				commonFunc.getDataFromTestDataSheet("SampleOpenPONumber"), 6);
		verifyFilterSearchOpenPO(OpenPoRcLocators.FindLocationButton, commonFunc.getDataFromTestDataSheet("Loc_100"),
				3);
		verifyFilterSearchOpenPO(OpenPoRcLocators.FindUnitButton, commonFunc.getDataFromTestDataSheet("Unit_100"), 4);
		verifyFilterSearchOpenPO(OpenPoRcLocators.FindSupplierButton,
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"), 12);

		commonFunc.logoutUser();
	}

	/**
	 * Amend - Standard - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAmendStandardDomesticWithStockAndSupplierSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPORcButton();
		verifyCreateOpenPoDoc(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("StandardPO"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		verifyAddItemInOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyAddAggrDateAndAggrNumber();
		verifyHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getOpenPoNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getOpenPoNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddItemInAmendOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("SO_Qty"),
				commonFunc.getDataFromTestDataSheet("SixValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getOpenPOAmendNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getOpenPOAmendNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyUnitCostValidation(commonFunc.getDataFromTestDataSheet("SixValue"),
				commonFunc.getDataFromTestDataSheet("SO_Qty"), commonFunc.getDataFromTestDataSheet("SixValue"),
				commonFunc.getDataFromTestDataSheet("SO_Cash_Disc"));
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Click open Amendment Button
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyClickOpenPOAmendmentButton() {
		flag = webDB.waitForElement(OpenPoRcLocators.OpenPOAmendmentLabel, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Ope Po Rc Button Is Displayed");
			webDB.clickAnElement(OpenPoRcLocators.OpenPOAmendmentLabel, ElementType.Xpath);
			flag = webDB.waitForElement(OpenPoRcLocators.AmendOpenPoButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Amend Open Po Button Is Displayed");
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Craete Open Po Amendment Documents
	 * 
	 * @author Deepan-GWL
	 */
	public void createOpenPoAmendDocuments() {
		flag = webDB.waitForElement(OpenPoRcLocators.AmendOpenPoButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenPoRcLocators.AmendOpenPoButton, ElementType.Xpath);
			flag = webDB.waitForElement(OpenPoRcLocators.AmendOpenPOLovButton, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Amend OpenPO Button Is Clicked Successfully");
				commonFunc.searchAndSelectValueInFilters(OpenPoRcLocators.AmendOpenPOLovButton,
						VariableHandler.getOpenPoNumber());
				flag = webDB.waitForElement(OpenPoRcLocators.AmendTickButton, ElementType.Xpath);
				if (flag) {
					webDB.clickAnElement(OpenPoRcLocators.AmendTickButton, ElementType.Xpath);
					commonFunc.checkRefreshCellsDisappear();
					flag = webDB.waitForElement(OpenPoRcLocators.CreateAmendButton(VariableHandler.getOpenPoNumber()),
							ElementType.Xpath);
					if (flag) {
						logs.logging("INFO", "Tick Button IS Clicked Successfully");
						commonFunc.hardWait(1500);
						webDB.clickAnElement(OpenPoRcLocators.CreateAmendButton(VariableHandler.getOpenPoNumber()),
								ElementType.Xpath);
						flag = webDB.waitForElement(OpenPoRcLocators.CreateOpenPOAmendHeader, ElementType.Xpath);
						if (flag) {
							logs.logging("INFO", "Create Amend Button IS Clicked");
							webDB.switchToFrame(OpenPoRcLocators.AmendCreateFrame, ElementType.Xpath);
							commonFunc.searchAndSelectValueInFilters(OpenPoRcLocators.AmendReasonButton,
									commonFunc.getDataFromTestDataSheet("AmendTestReason"));
							logs.logging("INFO", "Amendment Document Reason Is: "
									+ commonFunc.getDataFromTestDataSheet("AmendTestReason"));
							webDB.switchToFrame(OpenPoRcLocators.AmendCreateFrame, ElementType.Xpath);
							webDB.clickAnElement(OpenPoRcLocators.AmendOkButton, ElementType.Xpath);
							webDB.switchToDefaultContent();
							storeOpenPoAmendNumber();
						}
					}
				}
			}

		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify Store OpenPo Amend Number
	 * 
	 * @author Deepan-GWL
	 */
	public void storeOpenPoAmendNumber() {
		flag = webDB.waitForElement(OpenPoRcLocators.AmendOPONo, ElementType.Xpath);
		if (flag) {
			String Number = webDB.getTextFromElement(OpenPoRcLocators.AmendOPONo, ElementType.Xpath);
			logs.logging("INFO", "Get Text Open Po Amendment Number Is: " + Number);
			VariableHandler.setOpenPOAmendNumber(Number);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Add Amend Item Button
	 * 
	 * @author Deepan-GWL
	 * @param Item
	 */
	public void addAmendItemButton(String Item) {
		flag = webDB.waitForElement(OpenPoRcLocators.AmendAddButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenPoRcLocators.AmendAddButton, ElementType.Xpath);
			flag = webDB.waitForElement(OpenPoRcLocators.AmendItemTable, ElementType.Xpath);
			if (flag) {
				logs.logging("INFO", "Add Button IS Clicked");
				webDB.clickAnElement(OpenPoRcLocators.AmendItemTable, ElementType.Xpath);
				commonFunc.searchAndSelectValueInFilters(OpenPoRcLocators.AmendItemLovButton, Item);
			}
		}
		assertTrue(flag);
	}

	/**
	 * Verify Hsn Details Updation
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAmendHSNDetailsUpdation(String HSNCode) {
		webDB.moveToElement(OpenPoRcLocators.AmendHSNCodeTable, ElementType.Xpath);
		flag = webDB.waitForElement(OpenPoRcLocators.AmendHSNCodeTable, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenPoRcLocators.AmendHSNCodeTable, ElementType.Xpath);
			logs.logging("INFO", "Hsn Code Table Is Clicked");
			commonFunc.searchAndSelectValueInFilters(OpenPoRcLocators.HsnCodeLovButton, HSNCode);
		}
		assertTrue(flag);

	}

	/**
	 * This Method Is USed To Verify Add Item In Amend Open Po Line
	 * 
	 * @param Item
	 * @param UnitCost
	 * @param DiscPercentage
	 * @param HSNCode
	 */
	public void verifyAddItemInAmendOpenPOLine(String Item, String UnitCost, String DiscPercentage, String HSNCode) {
		addAmendItemButton(Item);
		purchaseOrderFunc.verifyAddTableSpecs(OpenPoRcLocators.AmendNewUnitCostTable,
				OpenPoRcLocators.AmendNewUnitCostInput, UnitCost);
		purchaseOrderFunc.verifyAddTableSpecs(OpenPoRcLocators.AmendDiscTable, OpenPoRcLocators.AmendDiscInput,
				DiscPercentage);
		verifyAmendHSNDetailsUpdation(HSNCode);
		verifyAmendItemTickButton();

	}

	/**
	 * This Method Is Used To Verify Item Tick Button
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAmendItemTickButton() {
		flag = webDB.waitForElement(OpenPoRcLocators.AmendItemSaveButton, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenPoRcLocators.AmendItemSaveButton, ElementType.Xpath);
			commonFunc.hardWait(1000);
			if (webDB.verifyIsElementDisplayed(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath)) {
				logs.logging("INFO", "Item Tick Icon Is Clicked");
				webDB.clickAnElement(PurchaseOrderPageLocators.CloseToastIcon, ElementType.Xpath);
				logs.logging("INFO", "Toast Close Icon Is Clicked");
			}
			flag = webDB.waitForElement(OpenPoRcLocators.AmendHeaderSaveButton, ElementType.Xpath);
		}
		assertTrue(flag);
	}

	/**
	 * This Method Is Used To Verify Open Po Amend Header Save And Post
	 * 
	 */
	public void verifyOpenPoAmendHeaderSaveAndPost() {
		flag = webDB.waitForElement(OpenPoRcLocators.AmendHeaderSaveButton, ElementType.Xpath);
		if (flag) {
			logs.logging("INFO", "Header Tick Icon Is Displayed");
			webDB.clickAnElement(OpenPoRcLocators.AmendHeaderSaveButton, ElementType.Xpath);
			commonFunc.checkRefreshCellsDisappear();
			commonFunc.hardWait(5000);
			if (webDB.isElementDisplayed(CommonUiLocators.ToastCloseIcon, ElementType.Xpath)) {
				webDB.clickAnElementWithoutException(CommonUiLocators.ToastCloseIcon, ElementType.Xpath);
			}
			commonFunc.checkSuccessToastMessageDisappear();
			flag = webDB.waitForClickElement(OpenPoRcLocators.AmendHeaderPostButton, ElementType.Xpath);
			if (flag) {
				commonFunc.checkRefreshCellsDisappear();
				webDB.clickAnElement(OpenPoRcLocators.AmendHeaderPostButton, ElementType.Xpath);
				flag = commonFunc.checkAutoApprovalOrForwardedWhilePost();
				if (flag) {
					logs.logging("INFO", "Post Button Clicked Successfully");
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * This Method Used To Verify OpenPo Amendment Status
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyOpenPoAmendApprovalStatus(String DocNo, String ExpStatus) {
		commonFunc.searchAndSelectValueInFilters(OpenPoRcLocators.AmendOOPANoLovButton, DocNo);
		flag = webDB.waitForElement(OpenPoRcLocators.HeaderCheckBox, ElementType.Xpath);
		if (flag) {
			webDB.clickAnElement(OpenPoRcLocators.HeaderCheckBox, ElementType.Xpath);
			flag = webDB.waitForElement(OpenPoRcLocators.AmendSearchButton, ElementType.Xpath);
			if (flag) {
				webDB.clickAnElement(OpenPoRcLocators.AmendSearchButton, ElementType.Xpath);
				logs.logging("INFO", "Search Button Is Clicked");

				commonFunc.hardWait(1000);
				commonFunc.checkRefreshCellsDisappear();
				flag = webDB.waitForElement(OpenPoRcLocators.AmendOpenPoStatus(DocNo), ElementType.Xpath);
				if (flag) {
					String Status = webDB.getTextFromElement(OpenPoRcLocators.AmendOpenPoStatus(DocNo),
							ElementType.Xpath);
					flag = Status.contains(ExpStatus);
					if (flag) {
						logs.logging("INFO", "Expected Status: " + ExpStatus);
						logs.logging("INFO", "Verified Status ,Displayed Status: " + ExpStatus);
					}
				}
			}
		}
		assertTrue(flag);
	}

	/**
	 * Amend - Standard - Domestic Supplier with Amend - Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAmendStandardDomesticWithStockAndSupplierDissimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPORcButton();
		verifyCreateOpenPoDoc(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("StandardPO"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		verifyAddItemInOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyAddAggrDateAndAggrNumber();
		verifyHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getOpenPoNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getOpenPoNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddItemInAmendOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("SO_Qty"),
				commonFunc.getDataFromTestDataSheet("SixValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getOpenPOAmendNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getOpenPOAmendNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyUnitCostValidation(commonFunc.getDataFromTestDataSheet("SixValue"),
				commonFunc.getDataFromTestDataSheet("SO_Qty"), commonFunc.getDataFromTestDataSheet("SixValue"),
				commonFunc.getDataFromTestDataSheet("SO_Cash_Disc"));
		assertTrue(flag);
	}

	/**
	 * Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM
	 * similar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAmendStandardImportWithStockAndSupplierSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPORcButton();
		verifyCreateOpenPoDoc(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("StandardPO"),
				commonFunc.getDataFromTestDataSheet("GoogleSupplier"));
		verifyAddItemInOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyAddAggrDateAndAggrNumber();
		verifyHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getOpenPoNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getOpenPoNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddItemInAmendOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("SO_Qty"),
				commonFunc.getDataFromTestDataSheet("SixValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getOpenPOAmendNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getOpenPOAmendNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GoogleSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyUnitCostValidation(commonFunc.getDataFromTestDataSheet("SixValue"),
				commonFunc.getDataFromTestDataSheet("SO_Qty"), commonFunc.getDataFromTestDataSheet("SixValue"),
				commonFunc.getDataFromTestDataSheet("SO_Cash_Disc"));
	}

	/**
	 * Amend - Standard - Import Supplier with Amend - Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAmendStandardImportWithStockAndSupplierDissimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPORcButton();
		verifyCreateOpenPoDoc(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("StandardPO"),
				commonFunc.getDataFromTestDataSheet("GoogleSupplier"));
		verifyAddItemInOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyAddAggrDateAndAggrNumber();
		verifyHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getOpenPoNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getOpenPoNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddItemInAmendOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("SO_Qty"),
				commonFunc.getDataFromTestDataSheet("SixValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getOpenPOAmendNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getOpenPOAmendNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc_100"),
				commonFunc.getDataFromTestDataSheet("GoogleSupplier"),
				commonFunc.getDataFromTestDataSheet("GeneralValue"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyUnitCostValidation(commonFunc.getDataFromTestDataSheet("SixValue"),
				commonFunc.getDataFromTestDataSheet("SO_Qty"), commonFunc.getDataFromTestDataSheet("SixValue"),
				commonFunc.getDataFromTestDataSheet("SO_Cash_Disc"));
	}

	/**
	 * Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM
	 * similar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAmendStockTransferDomesticWithStockAndSupplierSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPORcButton();
		verifyCreateOpenPoDoc(commonFunc.getDataFromTestDataSheet("Loc16"),
				commonFunc.getDataFromTestDataSheet("StockTransferPO"),
				commonFunc.getDataFromTestDataSheet("StockTransferSupplierOpenPO"));
		verifyAddItemInOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyAddAggrDateAndAggrNumber();
		verifyHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getOpenPoNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getOpenPoNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("SimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddItemInAmendOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("SO_Qty"),
				commonFunc.getDataFromTestDataSheet("SixValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getOpenPOAmendNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getOpenPOAmendNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc16"),
				commonFunc.getDataFromTestDataSheet("StockTransferSupplierOpenPO"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyUnitCostValidation(commonFunc.getDataFromTestDataSheet("SixValue"),
				commonFunc.getDataFromTestDataSheet("SO_Qty"), commonFunc.getDataFromTestDataSheet("SixValue"),
				commonFunc.getDataFromTestDataSheet("SO_Cash_Disc"));
		assertTrue(flag);
	}

	/**
	 * Amend - Stock transfer - Domestic Supplier with Stock and Supplier UOM
	 * dissimilar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAmendStockTransferDomesticWithStockAndSupplierDissimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPORcButton();
		verifyCreateOpenPoDoc(commonFunc.getDataFromTestDataSheet("Loc16"),
				commonFunc.getDataFromTestDataSheet("StockTransferPO"),
				commonFunc.getDataFromTestDataSheet("StockTransferSupplierOpenPO"));
		verifyAddItemInOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyAddAggrDateAndAggrNumber();
		verifyHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getOpenPoNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getOpenPoNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem2(commonFunc.getDataFromTestDataSheet("DissimItem_ID"));
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddItemInAmendOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("SO_Qty"),
				commonFunc.getDataFromTestDataSheet("SixValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getOpenPOAmendNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getOpenPOAmendNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("Loc16"),
				commonFunc.getDataFromTestDataSheet("StockTransferSupplierOpenPO"),
				commonFunc.getDataFromTestDataSheet("StockTransferSelect"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyUnitCostValidation(commonFunc.getDataFromTestDataSheet("SixValue"),
				commonFunc.getDataFromTestDataSheet("SO_Qty"), commonFunc.getDataFromTestDataSheet("SixValue"),
				commonFunc.getDataFromTestDataSheet("SO_Cash_Disc"));
		assertTrue(flag);
	}

	/**
	 * Amend - Service - Domestic Supplier with Stock and Supplier UOM similar
	 * 
	 * @author Deepan-GWL
	 */
	public void verifyAmendSeviceDomesticWithStockAndSupplierSimilarUOM() {
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("GwlItem_Service"));
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPORcButton();
		verifyCreateOpenPoDoc(commonFunc.getDataFromTestDataSheet("L0001"),
				commonFunc.getDataFromTestDataSheet("StandardService"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"));
		verifyAddItemInOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("UnitCostValue"),
				commonFunc.getDataFromTestDataSheet("QuantityValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyAddAggrDateAndAggrNumber();
		verifyHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getOpenPoNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getOpenPoNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPORcButton();
		}
		verifyOpenPoApprovalStatus(VariableHandler.getOpenPoNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateItemPage();
		pricelistPageFunctions.verifySearchOfItem(commonFunc.getDataFromTestDataSheet("GwlItem_Service"));
		commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
				"TvsUsername");
		commonFunc.navigateOpenPORCPage();
		verifyClickOpenPOAmendmentButton();
		createOpenPoAmendDocuments();
		verifyAddItemInAmendOpenPOLine(VariableHandler.getItemName(), commonFunc.getDataFromTestDataSheet("SO_Qty"),
				commonFunc.getDataFromTestDataSheet("SixValue"), commonFunc.getDataFromTestDataSheet("HSN2022"));
		verifyOpenPoAmendHeaderSaveAndPost();
		if (!VariableHandler.getApprovalFlag()) {
			approvalFunc.waitingForApprovalWithFordwarder(commonFunc.getDataFromTestDataSheet("HodValue"));
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
					"HodUsername");
			approvalFunc.navigateToApprovals();
			approvalFunc.filterDocumentsforApproval(VariableHandler.getOpenPOAmendNumber());
			approvalFunc.verifyApproveWithDocNumber(VariableHandler.getOpenPOAmendNumber());
			commonFunc.logoutUser();
			commonFunc.validCommonLogin(System.getProperty("TvsUsername"), System.getProperty("TvsPassword"),
					"TvsUsername");
			commonFunc.navigateOpenPORCPage();
			verifyClickOpenPOAmendmentButton();
		}
		verifyOpenPoAmendApprovalStatus(VariableHandler.getOpenPOAmendNumber(),
				commonFunc.getDataFromTestDataSheet("ApproveStatus"));
		commonFunc.logoutUser();
		commonFunc.validCommonLogin(System.getProperty("HodUsername"), System.getProperty("HodPassword"),
				"HodUsername-Admin");
		commonFunc.navigatePurchaseOrderPage();
		commonFunc.clickCreateIcon();
		purchaseOrderFunc.verifyCreateDirectPOAddingParameters(commonFunc.getDataFromTestDataSheet("L0001"),
				commonFunc.getDataFromTestDataSheet("PraxairSupplier"),
				commonFunc.getDataFromTestDataSheet("ServiceValue"));
		purchaseOrderFunc.verifyCreateDirectPO();
		purchaseOrderFunc.verifyCreateItemInPo(VariableHandler.getItemName());
		verifyUnitCostValidation(commonFunc.getDataFromTestDataSheet("SixValue"),
				commonFunc.getDataFromTestDataSheet("SO_Qty"), commonFunc.getDataFromTestDataSheet("SixValue"),
				commonFunc.getDataFromTestDataSheet("SO_Cash_Disc"));
		assertTrue(flag);
	}

}
